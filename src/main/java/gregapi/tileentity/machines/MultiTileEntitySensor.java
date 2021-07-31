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

package gregapi.tileentity.machines;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_IgnorePlayerCollisionWhenPlacing;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SyncDataShort;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.base.TileEntityBase10FacingDouble;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntitySensor extends TileEntityBase10FacingDouble implements ITileEntityQuickObstructionCheck, IMTE_IgnorePlayerCollisionWhenPlacing, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IMTE_SyncDataShort {
	protected byte mMode = 0;
	protected int mDisplayedNumber = 0, oDisplayedNumber = 0, mSetNumber = 0;
	protected byte mRedstone = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_MODE)) mMode = aNBT.getByte(NBT_MODE);
		if (aNBT.hasKey(NBT_VISUAL)) mDisplayedNumber = UT.Code.unsignS(aNBT.getShort(NBT_VISUAL));
		if (aNBT.hasKey(NBT_VALUE)) mSetNumber = UT.Code.unsignS(aNBT.getShort(NBT_VALUE)); else mSetNumber = mDisplayedNumber;
		if (aNBT.hasKey(NBT_CONNECTION)) mSecondFacing = aNBT.getByte(NBT_CONNECTION);
		if (aNBT.hasKey(NBT_REDSTONE)) mRedstone = aNBT.getByte(NBT_REDSTONE);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		aNBT.setShort(NBT_VISUAL, (short)mDisplayedNumber);
		aNBT.setShort(NBT_VALUE, (short)mSetNumber);
		aNBT.setByte(NBT_MODE, mMode);
		aNBT.setByte(NBT_REDSTONE, mRedstone);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		aNBT.setShort(NBT_VALUE, (short)mSetNumber);
		aNBT.setByte(NBT_MODE, mMode);
		return aNBT;
	}
	
	static {LH.add("gt.tooltip.sensor.screwdrive.buttons"   , "Use Screwdriver on Buttons to enable Averaging Mode.");}
	static {LH.add("gt.tooltip.sensor.screwdrive.display"   , "Use Screwdriver on Display toggle Hexadecimal Display.");}
	static {LH.add("gt.tooltip.sensor.screwdrive.modes"     , "Use Screwdriver on anything else to switch Modes.");}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + getSensorDescription());
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_CLICK_TO_INTERACT));
		aList.add(Chat.DGRAY    + LH.get("gt.tooltip.sensor.screwdrive.buttons"));
		aList.add(Chat.DGRAY    + LH.get("gt.tooltip.sensor.screwdrive.display"));
		aList.add(Chat.DGRAY    + LH.get("gt.tooltip.sensor.screwdrive.modes"));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_SET_INPUT_MONKEY_WRENCH));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_SET_FACING_PRE) + LH.get(TOOL_LOCALISER_PREFIX + getFacingTool(), "Unknown") + LH.get(LH.TOOL_TO_SET_FACING_POST));
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return 0;
		if (aTool.equals(TOOL_wrench      )) {byte aTargetSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ); if (SIDES_VALID[aTargetSide]                           ) {mSecondFacing = OPOS[mFacing = aTargetSide];    updateClientData(); causeBlockUpdate(); return 10000;}}
		if (aTool.equals(TOOL_monkeywrench)) {byte aTargetSide = UT.Code.getSideWrenching(aSide, aHitX, aHitY, aHitZ); if (SIDES_VALID[aTargetSide] && aTargetSide != mFacing ) {mSecondFacing = aTargetSide;                         updateClientData(); causeBlockUpdate(); return 10000;}}
		return 0;
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		mDisplayedNumber = UT.Code.bind16(mDisplayedNumber);
		return super.onTickCheck(aTimer) || Math.abs(mDisplayedNumber - oDisplayedNumber) > (SYNC_SECOND ? 0 : 49);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oDisplayedNumber = mDisplayedNumber;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		if (aSendAll) return getClientDataPacketByteArray(T, UT.Code.toByteS((short)mDisplayedNumber, 0), UT.Code.toByteS((short)mDisplayedNumber, 1), (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getDirectionData(), mMode);
		return getClientDataPacketShort(F, (short)mDisplayedNumber);
	}
	
	@Override
	public boolean receiveDataShort(short aData, INetworkHandler aNetworkHandler) {
		mDisplayedNumber = UT.Code.unsignS(aData);
		return T;
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mDisplayedNumber = UT.Code.unsignS(UT.Code.combine(aData[0], aData[1]));
		if (aData.length >= 7) {
			mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[2]), UT.Code.unsignB(aData[3]), UT.Code.unsignB(aData[4])});
			setDirectionData(aData[5]);
			mMode = aData[6];
		}
		return T;
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 7;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0) {
			box(aBlock, PX_P[SIDE_X_NEG==mFacing?14: 0], PX_P[SIDE_Y_NEG==mFacing?14: 0], PX_P[SIDE_Z_NEG==mFacing?14: 0], PX_N[SIDE_X_POS==mFacing?14: 0], PX_N[SIDE_Y_POS==mFacing?14: 0], PX_N[SIDE_Z_POS==mFacing?14: 0]);
			return T;
		}
		
		if (mFacing == SIDE_X_NEG) {
			switch (aRenderPass) {
			case 1: box(aBlock, PX_P[14]-PX_OFFSET, PX_P[12], PX_P[ 2], PX_N[ 0], PX_N[ 2], PX_N[12]); return T;
			case 2: box(aBlock, PX_P[14]-PX_OFFSET, PX_P[12], PX_P[ 4], PX_N[ 0], PX_N[ 2], PX_N[10]); return T;
			case 3: box(aBlock, PX_P[14]-PX_OFFSET, PX_P[12], PX_P[ 6], PX_N[ 0], PX_N[ 2], PX_N[ 8]); return T;
			case 4: box(aBlock, PX_P[14]-PX_OFFSET, PX_P[12], PX_P[ 8], PX_N[ 0], PX_N[ 2], PX_N[ 6]); return T;
			case 5: box(aBlock, PX_P[14]-PX_OFFSET, PX_P[12], PX_P[10], PX_N[ 0], PX_N[ 2], PX_N[ 4]); return T;
			case 6: box(aBlock, PX_P[14]-PX_OFFSET, PX_P[12], PX_P[12], PX_N[ 0], PX_N[ 2], PX_N[ 2]); return T;
			}
		}
		if (mFacing == SIDE_X_POS) {
			switch (aRenderPass) {
			case 1: box(aBlock, PX_P[ 0], PX_P[12], PX_P[12], PX_N[14]+PX_OFFSET, PX_N[ 2], PX_N[ 2]); return T;
			case 2: box(aBlock, PX_P[ 0], PX_P[12], PX_P[10], PX_N[14]+PX_OFFSET, PX_N[ 2], PX_N[ 4]); return T;
			case 3: box(aBlock, PX_P[ 0], PX_P[12], PX_P[ 8], PX_N[14]+PX_OFFSET, PX_N[ 2], PX_N[ 6]); return T;
			case 4: box(aBlock, PX_P[ 0], PX_P[12], PX_P[ 6], PX_N[14]+PX_OFFSET, PX_N[ 2], PX_N[ 8]); return T;
			case 5: box(aBlock, PX_P[ 0], PX_P[12], PX_P[ 4], PX_N[14]+PX_OFFSET, PX_N[ 2], PX_N[10]); return T;
			case 6: box(aBlock, PX_P[ 0], PX_P[12], PX_P[ 2], PX_N[14]+PX_OFFSET, PX_N[ 2], PX_N[12]); return T;
			}
		}
		if (mFacing == SIDE_Z_NEG) {
			switch (aRenderPass) {
			case 1: box(aBlock, PX_P[12], PX_P[12], PX_P[14]-PX_OFFSET, PX_N[ 2], PX_N[ 2], PX_N[ 0]); return T;
			case 2: box(aBlock, PX_P[10], PX_P[12], PX_P[14]-PX_OFFSET, PX_N[ 4], PX_N[ 2], PX_N[ 0]); return T;
			case 3: box(aBlock, PX_P[ 8], PX_P[12], PX_P[14]-PX_OFFSET, PX_N[ 6], PX_N[ 2], PX_N[ 0]); return T;
			case 4: box(aBlock, PX_P[ 6], PX_P[12], PX_P[14]-PX_OFFSET, PX_N[ 8], PX_N[ 2], PX_N[ 0]); return T;
			case 5: box(aBlock, PX_P[ 4], PX_P[12], PX_P[14]-PX_OFFSET, PX_N[10], PX_N[ 2], PX_N[ 0]); return T;
			case 6: box(aBlock, PX_P[ 2], PX_P[12], PX_P[14]-PX_OFFSET, PX_N[12], PX_N[ 2], PX_N[ 0]); return T;
			}
		}
		if (mFacing == SIDE_Z_POS) {
			switch (aRenderPass) {
			case 1: box(aBlock, PX_P[ 2], PX_P[12], PX_P[ 0], PX_N[12], PX_N[ 2], PX_N[14]+PX_OFFSET); return T;
			case 2: box(aBlock, PX_P[ 4], PX_P[12], PX_P[ 0], PX_N[10], PX_N[ 2], PX_N[14]+PX_OFFSET); return T;
			case 3: box(aBlock, PX_P[ 6], PX_P[12], PX_P[ 0], PX_N[ 8], PX_N[ 2], PX_N[14]+PX_OFFSET); return T;
			case 4: box(aBlock, PX_P[ 8], PX_P[12], PX_P[ 0], PX_N[ 6], PX_N[ 2], PX_N[14]+PX_OFFSET); return T;
			case 5: box(aBlock, PX_P[10], PX_P[12], PX_P[ 0], PX_N[ 4], PX_N[ 2], PX_N[14]+PX_OFFSET); return T;
			case 6: box(aBlock, PX_P[12], PX_P[12], PX_P[ 0], PX_N[ 2], PX_N[ 2], PX_N[14]+PX_OFFSET); return T;
			}
		}
		if (mFacing == SIDE_Y_NEG) {
			switch (aRenderPass) {
			case 1: box(aBlock, PX_P[ 2], PX_P[14]-PX_OFFSET, PX_P[12], PX_N[12], PX_N[ 0], PX_N[ 2]); return T;
			case 2: box(aBlock, PX_P[ 4], PX_P[14]-PX_OFFSET, PX_P[12], PX_N[10], PX_N[ 0], PX_N[ 2]); return T;
			case 3: box(aBlock, PX_P[ 6], PX_P[14]-PX_OFFSET, PX_P[12], PX_N[ 8], PX_N[ 0], PX_N[ 2]); return T;
			case 4: box(aBlock, PX_P[ 8], PX_P[14]-PX_OFFSET, PX_P[12], PX_N[ 6], PX_N[ 0], PX_N[ 2]); return T;
			case 5: box(aBlock, PX_P[10], PX_P[14]-PX_OFFSET, PX_P[12], PX_N[ 4], PX_N[ 0], PX_N[ 2]); return T;
			case 6: box(aBlock, PX_P[12], PX_P[14]-PX_OFFSET, PX_P[12], PX_N[ 2], PX_N[ 0], PX_N[ 2]); return T;
			}
		}
		if (mFacing == SIDE_Y_POS) {
			switch (aRenderPass) {
			case 1: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[12], PX_N[14]+PX_OFFSET, PX_N[12]); return T;
			case 2: box(aBlock, PX_P[ 4], PX_P[ 0], PX_P[ 2], PX_N[10], PX_N[14]+PX_OFFSET, PX_N[12]); return T;
			case 3: box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 2], PX_N[ 8], PX_N[14]+PX_OFFSET, PX_N[12]); return T;
			case 4: box(aBlock, PX_P[ 8], PX_P[ 0], PX_P[ 2], PX_N[ 6], PX_N[14]+PX_OFFSET, PX_N[12]); return T;
			case 5: box(aBlock, PX_P[10], PX_P[ 0], PX_P[ 2], PX_N[ 4], PX_N[14]+PX_OFFSET, PX_N[12]); return T;
			case 6: box(aBlock, PX_P[12], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[14]+PX_OFFSET, PX_N[12]); return T;
			}
		}
		return T;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (aSide == mFacing) {
			if (aRenderPass == 0) return BlockTextureMulti.get(BlockTextureDefault.get(getTextureFront(), mRGBa), BlockTextureDefault.get(getOverlayFront()));
			return BlockTextureDefault.get(getCharacterIcon(aRenderPass-1), getCharacterColor(aRenderPass-1), F, T, T, T);
		}
		if (aShouldSideBeRendered[aSide] && aRenderPass == 0) {
			if (aSide == OPOS[mFacing]) return BlockTextureMulti.get(BlockTextureDefault.get(getTextureBack(), mRGBa), BlockTextureDefault.get(getOverlayBack()));
			return BlockTextureMulti.get(BlockTextureDefault.get(getTextureSide(), mRGBa), BlockTextureDefault.get(getOverlaySide()));
		}
		return null;
	}
	
	public boolean hasHitDisplay(byte aSide, float aHitX, float aHitY, float aHitZ) {
		float[] tCoords = UT.Code.getFacingCoordsClicked(aSide, aHitX, aHitY, aHitZ);
		return tCoords[0] >= PX_P[2] && tCoords[0] <= PX_N[2] && tCoords[1] >= PX_P[2] && tCoords[1] <= PX_P[4];
	}
	
	public short[] getCharacterColor(int aIndex) {return CA_WHITE;}
	public abstract IIconContainer getCharacterIcon(int aIndex);
	public abstract IIconContainer getTextureFront();
	public abstract IIconContainer getTextureBack();
	public abstract IIconContainer getTextureSide();
	public abstract IIconContainer getOverlayFront();
	public abstract IIconContainer getOverlayBack();
	public abstract IIconContainer getOverlaySide();
	public abstract String getSensorDescription();
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_WATER;}
	
	@Override public byte isProvidingWeakPower2(byte aSide) {return aSide == OPOS[mSecondFacing] ? 0 : mRedstone;}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[SIDE_X_NEG==mFacing?14: 0], PX_P[SIDE_Y_NEG==mFacing?14: 0], PX_P[SIDE_Z_NEG==mFacing?14: 0], PX_N[SIDE_X_POS==mFacing?14: 0], PX_N[SIDE_Y_POS==mFacing?14: 0], PX_N[SIDE_Z_POS==mFacing?14: 0]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[SIDE_X_NEG==mFacing?14: 0], PX_P[SIDE_Y_NEG==mFacing?14: 0], PX_P[SIDE_Z_NEG==mFacing?14: 0], PX_N[SIDE_X_POS==mFacing?14: 0], PX_N[SIDE_Y_POS==mFacing?14: 0], PX_N[SIDE_Z_POS==mFacing?14: 0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock,  PX_P[SIDE_X_NEG==mFacing?14: 0], PX_P[SIDE_Y_NEG==mFacing?14: 0], PX_P[SIDE_Z_NEG==mFacing?14: 0], PX_N[SIDE_X_POS==mFacing?14: 0], PX_N[SIDE_Y_POS==mFacing?14: 0], PX_N[SIDE_Z_POS==mFacing?14: 0]);}
	
	@Override public float getSurfaceSize           (byte aSide) {return ALONG_AXIS[aSide][mFacing]?1.0F:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return ALONG_AXIS[aSide][mFacing]?1.0F:0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return aSide==mFacing?PX_N[2]:0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return aSide==OPOS[mFacing];}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return aSide==OPOS[mFacing];}
	@Override public boolean isSideSolid2           (byte aSide) {return aSide==OPOS[mFacing];}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return aSide==OPOS[mFacing];}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return aSide==OPOS[mFacing];}
	@Override public boolean[] getValidSecondSides() {return SIDES_ANY_BUT[mFacing];}
	@Override public boolean ignorePlayerCollisionWhenPlacing() {return T;}
}
