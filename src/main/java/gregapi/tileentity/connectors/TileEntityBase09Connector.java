/**
 * Copyright (c) 2021 GregTech-6 Team
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

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnPlaced;
import gregapi.block.multitileentity.MultiTileEntityContainer;
import gregapi.cover.ITileEntityCoverable;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.tileentity.ITileEntity;
import gregapi.tileentity.ITileEntityMachineBlockUpdateable;
import gregapi.tileentity.base.TileEntityBase08Directional;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase09Connector extends TileEntityBase08Directional implements ITileEntityConnector, IMTE_OnPlaced, IMTE_AddToolTips {
	protected byte mConnections = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_CONNECTION)) mConnections = (byte)(aNBT.getByte(NBT_CONNECTION) & 63);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_CONNECTION, mConnections);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_SET_CONNECTIONS_PRE) + LH.get(TOOL_LOCALISER_PREFIX + getFacingTool(), "Unknown") + LH.get(LH.TOOL_TO_SET_CONNECTIONS_POST));
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return 0;
		if (aTool.equals(getFacingTool())) {
			byte aTargetSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ);
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(aTargetSide);
			if (tDelegator.mTileEntity instanceof ITileEntity && !((ITileEntity)tDelegator.mTileEntity).allowInteraction(aPlayer)) return 0;
			return (connected(aTargetSide)?disconnect(aTargetSide, T):connect(aTargetSide, T))?10000:0;
		}
		return 0;
	}
	
	@Override
	public boolean onPlaced(ItemStack aStack, EntityPlayer aPlayer, MultiTileEntityContainer aMTEContainer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			aSide = OPOS[aSide];
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(aSide);
			if (tDelegator.mTileEntity instanceof ITileEntity && !((ITileEntity)tDelegator.mTileEntity).allowInteraction(aPlayer)) return T;
			connect(aSide, T);
			for (byte tSide : ALL_SIDES_VALID) {
				tDelegator = getAdjacentTileEntity(tSide);
				if (tDelegator.mTileEntity instanceof ITileEntityConnector && SIDES_VALID[tDelegator.mSideOfTileEntity] && UT.Code.haveOneCommonElement(((ITileEntityConnector)tDelegator.mTileEntity).getConnectorTypes(tDelegator.mSideOfTileEntity), getConnectorTypes(tSide))) {
					if (((ITileEntityConnector)tDelegator.mTileEntity).connected(tDelegator.mSideOfTileEntity)) connect(tSide, T);
				}
			}
		}
		return T;
	}
	
	@Override public byte getDirectionData() {return (byte)(mConnections & (byte)63);}
	@Override public void setDirectionData(byte aData) {mConnections = (byte)(aData & 63);}
	@Override public short getFacing() {return 0;}
	@Override public void setFacing(short aSide) {/**/}
	@Override public boolean wrenchCanSetFacing(EntityPlayer aPlayer, int aSide) {return F;}
	@Override public boolean isConnectedWrenchingOverlay(ItemStack aStack, byte aSide) {return connected(aSide);}
	
	@Override
	public boolean connected(byte aSide) {
		return FACE_CONNECTED[aSide][mConnections];
	}
	
	@Override
	public boolean connect(byte aSide, boolean aNotify) {
		if (SIDES_INVALID[aSide]) return F;
		if (connected(aSide)) return T;
		if (hasCovers() && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptConnect(aSide, mCovers)) return F;
		DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(aSide);
		if (tDelegator.mTileEntity instanceof ITileEntityConnector) {
			if (tDelegator.mTileEntity instanceof ITileEntityCoverable && ((ITileEntityCoverable)tDelegator.mTileEntity).getCoverData() != null && ((ITileEntityCoverable)tDelegator.mTileEntity).getCoverData().mBehaviours[tDelegator.mSideOfTileEntity] != null && ((ITileEntityCoverable)tDelegator.mTileEntity).getCoverData().mBehaviours[tDelegator.mSideOfTileEntity].interceptConnect(tDelegator.mSideOfTileEntity, mCovers)) return F;
			if (SIDES_VALID[tDelegator.mSideOfTileEntity] && UT.Code.haveOneCommonElement(((ITileEntityConnector)tDelegator.mTileEntity).getConnectorTypes(tDelegator.mSideOfTileEntity), getConnectorTypes(aSide))) {
				byte oConnections = mConnections;
				mConnections |= SBIT[aSide];
				updateClientData();
				causeBlockUpdate();
				onConnectionChange(oConnections);
				checkCoverValidity();
				doEnetUpdate();
				if (aNotify) ((ITileEntityConnector)tDelegator.mTileEntity).connect(tDelegator.mSideOfTileEntity, F);
				if (hasMultiBlockMachineRelevantData()) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(this, F);
				return T;
			}
			if (this instanceof ITileEntityRedstoneWire) {
				byte oConnections = mConnections;
				mConnections |= SBIT[aSide];
				updateClientData();
				causeBlockUpdate();
				onConnectionChange(oConnections);
				checkCoverValidity();
				doEnetUpdate();
				if (hasMultiBlockMachineRelevantData()) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(this, F);
				return T;
			}
		} else if (WD.air(tDelegator.mWorld, tDelegator.mX, tDelegator.mY, tDelegator.mZ) || WD.liquid(tDelegator.mWorld, tDelegator.mX, tDelegator.mY, tDelegator.mZ) || canConnect(aSide, tDelegator)) {
			byte oConnections = mConnections;
			mConnections |= SBIT[aSide];
			updateClientData();
			causeBlockUpdate();
			onConnectionChange(oConnections);
			checkCoverValidity();
			doEnetUpdate();
			if (hasMultiBlockMachineRelevantData()) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(this, F);
			return T;
		}
		return connected(aSide);
	}
	
	@Override
	public boolean disconnect(byte aSide, boolean aNotify) {
		if (SIDES_INVALID[aSide]) return F;
		if (!connected(aSide)) return T;
		DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(aSide);
		if (hasCovers() && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptDisconnect(aSide, mCovers)) return F;
		if (tDelegator.mTileEntity instanceof ITileEntityCoverable && ((ITileEntityCoverable)tDelegator.mTileEntity).getCoverData() != null && ((ITileEntityCoverable)tDelegator.mTileEntity).getCoverData().mBehaviours[tDelegator.mSideOfTileEntity] != null && ((ITileEntityCoverable)tDelegator.mTileEntity).getCoverData().mBehaviours[tDelegator.mSideOfTileEntity].interceptDisconnect(tDelegator.mSideOfTileEntity, mCovers)) return F;
		byte oConnections = mConnections;
		mConnections &= ~SBIT[aSide];
		updateClientData();
		causeBlockUpdate();
		onConnectionChange(oConnections);
		checkCoverValidity();
		doEnetUpdate();
		if (hasMultiBlockMachineRelevantData()) ITileEntityMachineBlockUpdateable.Util.causeMachineUpdate(this, F);
		if (aNotify && tDelegator.mTileEntity instanceof ITileEntityConnector && SIDES_VALID[tDelegator.mSideOfTileEntity] && UT.Code.haveOneCommonElement(((ITileEntityConnector)tDelegator.mTileEntity).getConnectorTypes(tDelegator.mSideOfTileEntity), getConnectorTypes(aSide))) ((ITileEntityConnector)tDelegator.mTileEntity).disconnect(tDelegator.mSideOfTileEntity, F);
		return T;
	}
	
	// Stuff to Override
	public void onConnectionChange(byte aPreviousConnections) {/**/}
	public boolean canConnect(byte aSide, DelegatorTileEntity<TileEntity> aDelegator) {return F;}
}
