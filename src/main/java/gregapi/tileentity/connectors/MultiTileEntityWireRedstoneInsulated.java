/**
 * Copyright (c) 2019 Gregorius Techneticies
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregapi.tileentity.connectors;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetComparatorInputOverride;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityWireRedstoneInsulated extends TileEntityBase10ConnectorRendered implements ITileEntityQuickObstructionCheck, ITileEntityRedstoneWire, ITileEntityProgress, ITileEntitySwitchableMode, IMTE_GetComparatorInputOverride {
	public static final int REDSTONE_ID = -1;
	
	public long mRedstone = 0, mLoss = 1;
	public byte mRenderType = 0, mReceived = SIDE_UNDEFINED, mMode = 0;
	public boolean mConnectedToNonWire = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey("gt.mreceived")) mReceived = aNBT.getByte("gt.mreceived");
		if (aNBT.hasKey("gt.mredstone")) mRedstone = aNBT.getByte("gt.mredstone");
		if (aNBT.hasKey(NBT_MODE)) mMode = aNBT.getByte(NBT_MODE);
		if (aNBT.hasKey(NBT_PIPELOSS)) mLoss = Math.max(1, aNBT.getLong(NBT_PIPELOSS));
		if (aNBT.hasKey(NBT_PIPERENDER)) mRenderType = aNBT.getByte(NBT_PIPERENDER);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		if (mMode != 0) aNBT.setByte(NBT_MODE, mMode);
		aNBT.setByte("gt.mreceived", mReceived);
		UT.NBT.setNumber(aNBT, "gt.mredstone", mRedstone);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get(LH.PIPE_STATS_RANGE) + (MAX_RANGE / mLoss));
		aList.add(Chat.CYAN + LH.get(LH.PIPE_STATS_BANDWIDTH) + 1);
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTickFirst2(boolean aIsServerSide) {
		super.onTickFirst2(aIsServerSide);
		updateConnectionStatus();
	}
	
	@Override
	public void onConnectionChange(byte aPreviousConnections) {
		super.onConnectionChange(aPreviousConnections);
		updateConnectionStatus();
		if (updateRedstone(REDSTONE_ID)) ITileEntityRedstoneWire.Util.doRedstoneUpdate(this, REDSTONE_ID);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		
		if (aIsServerSide) {
			if (mBlockUpdated) updateConnectionStatus();
			if (updateRedstone(REDSTONE_ID)) ITileEntityRedstoneWire.Util.doRedstoneUpdate(this, REDSTONE_ID);
		}
	}
	
	public long getRedstoneAtSide(byte aSide) {
		if (SIDES_INVALID[aSide]) return 0;
		DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(aSide);
		return tDelegator.mTileEntity instanceof ITileEntityRedstoneWire ? canAcceptRedstoneFromWire(aSide, REDSTONE_ID) && ((ITileEntityRedstoneWire)tDelegator.mTileEntity).canEmitRedstoneToWire(tDelegator.mSideOfTileEntity, REDSTONE_ID) ? ((ITileEntityRedstoneWire)tDelegator.mTileEntity).getRedstoneMinusLoss(tDelegator.mSideOfTileEntity, REDSTONE_ID) : 0 : canAcceptRedstoneFromVanilla(aSide) ? MAX_RANGE * getRedstoneIncoming(aSide) - mLoss : 0;
	}
	
	@Override
	public boolean updateRedstone(int aRedstoneID) {
		if (aRedstoneID != REDSTONE_ID) return F;
		long tRedstoneOld = mRedstone, tRedstone = mMode * MAX_RANGE - mLoss;
		byte tReceivedOld = mReceived;
		if ((mRedstone = getRedstoneAtSide(tReceivedOld)) <= tRedstone) {
			mRedstone = tRedstone;
			mReceived = SIDE_UNDEFINED;
		}
		for (byte tSide : ALL_SIDES_VALID_BUT[tReceivedOld]) if ((tRedstone = getRedstoneAtSide(tSide)) > mRedstone) {mRedstone = tRedstone; mReceived = tSide;}
		if (mRedstone != tRedstoneOld) {if (mConnectedToNonWire) causeBlockUpdate(); return T;}
		return F;
	}
	
	public void updateConnectionStatus() {
		mConnectedToNonWire = F;
		for (byte tSide : ALL_SIDES_VALID) if (canAcceptRedstoneFromVanilla(tSide) && !(getAdjacentTileEntity(tSide).mTileEntity instanceof ITileEntityRedstoneWire)) mConnectedToNonWire = T;
	}
	
	@Override
	public byte isProvidingWeakPower2(byte aSide) {
		if (!canEmitRedstoneToVanilla(aSide = OPPOSITES[aSide]) || mRedstone <= 0) return 0;
		Block tBlock = getBlockAtSide(aSide);
		return UT.Code.bind4(UT.Code.divup(mRedstone, MAX_RANGE) - (tBlock instanceof BlockRedstoneWire || tBlock.isNormalCube(worldObj, xCoord+OFFSETS_X[aSide], yCoord+OFFSETS_Y[aSide], zCoord+OFFSETS_Z[aSide]) ? 1 : 0));
	}
	
	@Override
	public byte isProvidingStrongPower2(byte aSide) {
		if (!canEmitRedstoneToVanilla(aSide = OPPOSITES[aSide]) || mRedstone <= 0) return 0;
		Block tBlock = getBlockAtSide(aSide);
		return UT.Code.bind4(UT.Code.divup(mRedstone, MAX_RANGE) - (tBlock instanceof BlockRedstoneWire || tBlock.isNormalCube(worldObj, xCoord+OFFSETS_X[aSide], yCoord+OFFSETS_Y[aSide], zCoord+OFFSETS_Z[aSide]) ? 1 : 0));
	}
	
	@Override
	public int getComparatorInputOverride(byte aSide) {
		return UT.Code.bind4(mRedstone / MAX_RANGE);
	}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public boolean isObstructingBlockAt(byte aSide) {return F;} // Btw, Wires have this but Pipes don't. This is because Wires are flexible, while Pipes aren't.
	
	public boolean canEmitRedstoneToVanilla                 (byte aSide) {return aSide != mReceived && connected(aSide) && !(getAdjacentTileEntity(aSide).mTileEntity instanceof ITileEntityRedstoneWire);}
	public boolean canAcceptRedstoneFromVanilla             (byte aSide) {return connected(aSide);}
	
	@Override public boolean canEmitRedstoneToWire          (byte aSide, int aRedstoneID) {return aRedstoneID == REDSTONE_ID && connected(aSide);}
	@Override public boolean canAcceptRedstoneFromWire      (byte aSide, int aRedstoneID) {return aRedstoneID == REDSTONE_ID && connected(aSide);}
	
	@Override public long getRedstoneLoss                   (int aRedstoneID) {return aRedstoneID == REDSTONE_ID ? mLoss : MAX_RANGE;}
	@Override public long getRedstoneValue                  (byte aSide, int aRedstoneID) {return aRedstoneID == REDSTONE_ID ? mRedstone         : 0;}
	@Override public long getRedstoneMinusLoss              (byte aSide, int aRedstoneID) {return aRedstoneID == REDSTONE_ID ? mRedstone - mLoss : 0;}
	
	@Override public boolean canConnect                     (byte aSide, DelegatorTileEntity<TileEntity> aDelegator) {return T;}
	
	@Override public long getProgressValue                  (byte aSide) {return (1000*mRedstone)/MAX_RANGE;}
	@Override public long getProgressMax                    (byte aSide) {return 16000;}
	
	@Override public byte setStateMode                      (byte aMode) {mMode = aMode; return mMode;}
	@Override public byte getStateMode                      () {return mMode;}
	
	@Override public Collection<TagData> getConnectorTypes  (byte aSide) {return TD.Connectors.WIRE_REDSTONE.AS_LIST;}
	
	@Override public String getFacingTool                   () {return TOOL_cutter;}
	
	@Override public ITexture getTextureSide                (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureDefault.get(Textures.BlockIcons.INSULATION_FULL, isPainted()?mRGBa:UT.Code.getRGBInt(96, 64, 64));}
	@Override public ITexture getTextureConnected           (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return BlockTextureMulti.get(BlockTextureDefault.get(mMaterial, getIconIndexConnected(aSide, aConnections, aDiameter, aRenderPass), mIsGlowing), BlockTextureDefault.get(aDiameter<0.37F?Textures.BlockIcons.INSULATION_TINY:aDiameter<0.49F?Textures.BlockIcons.INSULATION_SMALL:aDiameter<0.74F?Textures.BlockIcons.INSULATION_MEDIUM:aDiameter<0.99F?Textures.BlockIcons.INSULATION_LARGE:Textures.BlockIcons.INSULATION_HUGE, isPainted()?mRGBa:UT.Code.getRGBInt(96, 64, 64)));}
	
	@Override public int getIconIndexSide                   (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return OP.wire.mIconIndexBlock;}
	@Override public int getIconIndexConnected              (byte aSide, byte aConnections, float aDiameter, int aRenderPass) {return OP.wire.mIconIndexBlock;}
	
	@Override public String getTileEntityName               () {return "gt.multitileentity.connector.wire.redstone.insulated";}
}
