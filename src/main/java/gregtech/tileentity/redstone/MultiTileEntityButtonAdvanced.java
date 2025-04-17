/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregtech.tileentity.redstone;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_IgnorePlayerCollisionWhenPlacing;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.ITileEntityRemoteActivateable;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityButtonAdvanced extends TileEntityBase09FacingSingle implements ITileEntityQuickObstructionCheck, IMTE_IgnorePlayerCollisionWhenPlacing, ITileEntityRemoteActivateable, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	public boolean mActive = F, oActive = F, mInverted = F, mGlowInverted = F, mLampMode = F, mDoUnclickSound = F;
	public byte mStrength = 15, mType = 0, mIndex = 0;
	public long mLength = 0, mMaxLength = 20;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mInverted     = aNBT.getBoolean(NBT_MODE);
		mLampMode     = aNBT.getBoolean(NBT_MODE+".lamp");
		mGlowInverted = aNBT.getBoolean(NBT_VISUAL);
		mActive       = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_PROGRESS)) mLength = aNBT.getLong(NBT_PROGRESS);
		if (aNBT.hasKey(NBT_MAXPROGRESS)) mMaxLength = aNBT.getLong(NBT_MAXPROGRESS);
		if (aNBT.hasKey(NBT_REDSTONE)) mStrength = aNBT.getByte(NBT_REDSTONE);
		if (aNBT.hasKey(NBT_TEXTURE+".0")) mType = aNBT.getByte(NBT_TEXTURE+".0");
		if (aNBT.hasKey(NBT_TEXTURE+".1")) mIndex = aNBT.getByte(NBT_TEXTURE+".1");
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_MODE, mInverted);
		UT.NBT.setBoolean(aNBT, NBT_MODE+".lamp", mLampMode);
		UT.NBT.setBoolean(aNBT, NBT_VISUAL, mGlowInverted);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mActive);
		UT.NBT.setNumber (aNBT, NBT_PROGRESS, mLength);
		if (mMaxLength != 20) aNBT.setLong(NBT_MAXPROGRESS, mMaxLength);
		if (mStrength  != 15) aNBT.setByte(NBT_REDSTONE, mStrength);
		if (mType      !=  0) aNBT.setByte(NBT_TEXTURE+".0", mType);
		if (mIndex     !=  0) aNBT.setByte(NBT_TEXTURE+".1", mIndex);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		UT.NBT.setBoolean(aNBT, NBT_MODE, mInverted);
		UT.NBT.setBoolean(aNBT, NBT_MODE+".lamp", mLampMode);
		UT.NBT.setBoolean(aNBT, NBT_VISUAL, mGlowInverted);
		if (mMaxLength != 20) aNBT.setLong(NBT_MAXPROGRESS, mMaxLength);
		if (mStrength  != 15) aNBT.setByte(NBT_REDSTONE, mStrength);
		if (mType      !=  0) aNBT.setByte(NBT_TEXTURE+".0", mType);
		if (mIndex     !=  0) aNBT.setByte(NBT_TEXTURE+".1", mIndex);
		return aNBT;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_CUTTER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_MONKEY_WRENCH));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_TOGGLE_SOFT_HAMMER));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_CHANGE_DESIGN_CHISEL));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_HINT_USE_SNEAK));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		
		if (aTool.equals(TOOL_chisel)) {
			if (aSneaking) {
				mType = (byte)((mType + 1) % sTextures.length);
				mIndex = 0;
			} else {
				mIndex = (byte)((mIndex + 1) % sTextures[mType].length);
			}
			updateClientData();
			return 100;
		}
		if (aTool.equals(TOOL_screwdriver)) {
			if (aSneaking) mMaxLength+=20; else mMaxLength++;
			if (aChatReturn != null) aChatReturn.add("Signal Length = " + Math.abs(mMaxLength));
			return 100;
		}
		if (aTool.equals(TOOL_cutter)) {
			if (aSneaking) {
				if (--mStrength <= 0) mStrength = 15;
			} else {
				mStrength%=15; mStrength++;
			}
			if (aChatReturn != null) aChatReturn.add("Signal Strength = " + mStrength);
			causeBlockUpdate();
			return 100;
		}
		if (aTool.equals(TOOL_softhammer)) {
			if (aSneaking) {
				mGlowInverted = !mGlowInverted;
				if (aChatReturn != null) aChatReturn.add(mGlowInverted ? "Glows when unpressed!" : "Glows when pressed!");
				updateClientData();
				return 1000;
			}
			mInverted = !mInverted;
			mActive = !mActive;
			if (aChatReturn != null) aChatReturn.add(mInverted ? "Emits when unpressed!" : "Emits when pressed!");
			updateClientData();
			causeBlockUpdate();
			return 1000;
		}
		if (aTool.equals(TOOL_monkeywrench)) {
			if (mLampMode) {
				mLampMode = F;
			} else {
				if (mMaxLength > 0) {
					if (aSneaking) mLampMode = T;
					mMaxLength = 0;
				} else {
					if (aSneaking) mMaxLength=20; else mMaxLength=1;
				}
			}
			if (aChatReturn != null) aChatReturn.add(mLampMode ? "Lamp Mode" : mMaxLength > 0 ? "Button Mode" : "Switch Mode");
			return 1000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) {
				aChatReturn.add("Signal Strength = " + mStrength);
				if (mMaxLength > 0) aChatReturn.add("Signal Length = " + Math.abs(mMaxLength));
				aChatReturn.add(mLampMode ? "Lamp Mode" : mMaxLength > 0 ? "Button Mode" : "Switch Mode");
				aChatReturn.add(mInverted ? "Emits when unpressed!" : "Emits when pressed!");
				aChatReturn.add(mGlowInverted ? "Glows when unpressed!" : "Glows when pressed!");
			}
			return 1;
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && !mLampMode) {
			if (mMaxLength > 0) {
				mLength = mMaxLength;
				mActive = !mInverted;
			} else {
				mActive = !mActive;
			}
			causeBlockUpdate();
			UT.Sounds.send(SFX.MC_CLICK, 1.0F, mActive?1.5F:1.3F, this, F);
			mDoUnclickSound = T;
		}
		return !mLampMode;
	}
	
	@Override
	public boolean remoteActivate() {
		if (isServerSide() && !mLampMode) {
			if (mMaxLength > 0) {
				mLength = mMaxLength;
				mActive = !mInverted;
			} else {
				mActive = !mActive;
			}
			causeBlockUpdate();
			mDoUnclickSound = F;
		}
		return T;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (mLampMode) {
				if (mBlockUpdated) mActive = hasRedstoneIncoming();
			} else if (mActive != mInverted && mMaxLength > 0 && --mLength<0) {
				mActive = mInverted;
				causeBlockUpdate();
				if (mDoUnclickSound) UT.Sounds.send(SFX.MC_CLICK, 1.0F, mActive?1.5F:1.3F, this, F);
			}
		}
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return super.onTickCheck(aTimer) || mActive != oActive;
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oActive = mActive;
	}
	
	@Override
	public byte isProvidingWeakPower2(byte aSide) {
		return mActive && !mLampMode ? mStrength : 0;
	}
	
	@Override
	public byte isProvidingStrongPower2(byte aSide) {
		return aSide == mFacing && mActive && !mLampMode ? mStrength : 0;
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		return aSendAll ? getClientDataPacketByteArray(aSendAll, (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa), getVisualData(), getDirectionData(), mType, mIndex) : getClientDataPacketByte(aSendAll, getVisualData());
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[0]), UT.Code.unsignB(aData[1]), UT.Code.unsignB(aData[2])});
		setVisualData(aData[3]);
		setDirectionData(aData[4]);
		mType = aData[5];
		mIndex = aData[6];
		if (mType < 0 || mIndex < 0 || mType >= sTextures.length || mIndex >= sTextures[mType].length) mType = mIndex = 0;
		return T;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		box(aBlock, PX_P[SIDE_X_NEG==mFacing?14:SIDE_X_POS==mFacing?0:4], PX_P[SIDE_Y_NEG==mFacing?14:SIDE_Y_POS==mFacing?0:4], PX_P[SIDE_Z_NEG==mFacing?14:SIDE_Z_POS==mFacing?0:4], PX_N[SIDE_X_POS==mFacing?14:SIDE_X_NEG==mFacing?0:4], PX_N[SIDE_Y_POS==mFacing?14:SIDE_Y_NEG==mFacing?0:4], PX_N[SIDE_Z_POS==mFacing?14:SIDE_Z_NEG==mFacing?0:4]);
		return T;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide] && aSide==OPOS[mFacing]) return null;
		byte tOffset = (byte)(mActive == mGlowInverted ? 0 : 4);
		return BlockTextureMulti.get(BlockTextureDefault.get(sTextures[mType][mIndex][tOffset+0], mRGBa), BlockTextureDefault.get(sTextures[mType][mIndex][tOffset+1]), BlockTextureDefault.get(sTextures[mType][mIndex][tOffset+2], mRGBa, F, T, T, F), BlockTextureDefault.get(sTextures[mType][mIndex][tOffset+3], UNCOLOURED, F, T, T, F));
	}
	
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return null;}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[SIDE_X_NEG==mFacing?14:SIDE_X_POS==mFacing?0:4], PX_P[SIDE_Y_NEG==mFacing?14:SIDE_Y_POS==mFacing?0:4], PX_P[SIDE_Z_NEG==mFacing?14:SIDE_Z_POS==mFacing?0:4], PX_N[SIDE_X_POS==mFacing?14:SIDE_X_NEG==mFacing?0:4], PX_N[SIDE_Y_POS==mFacing?14:SIDE_Y_NEG==mFacing?0:4], PX_N[SIDE_Z_POS==mFacing?14:SIDE_Z_NEG==mFacing?0:4]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, PX_P[SIDE_X_NEG==mFacing?14:SIDE_X_POS==mFacing?0:4], PX_P[SIDE_Y_NEG==mFacing?14:SIDE_Y_POS==mFacing?0:4], PX_P[SIDE_Z_NEG==mFacing?14:SIDE_Z_POS==mFacing?0:4], PX_N[SIDE_X_POS==mFacing?14:SIDE_X_NEG==mFacing?0:4], PX_N[SIDE_Y_POS==mFacing?14:SIDE_Y_NEG==mFacing?0:4], PX_N[SIDE_Z_POS==mFacing?14:SIDE_Z_NEG==mFacing?0:4]);}
	
	@Override public float getSurfaceSize           (byte aSide) {return ALONG_AXIS[aSide][mFacing]?PX_P[14]:0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return ALONG_AXIS[aSide][mFacing]?PX_P[14]:0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return aSide==mFacing?PX_N[1]:0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return F;}
	@Override public boolean useSidePlacementRotation       () {return T;}
	@Override public boolean useInversePlacementRotation    () {return F;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public boolean ignorePlayerCollisionWhenPlacing() {return T;}
	
	@Override public byte getVisualData() {return (byte)((mActive?1:0)|(mInverted?2:0)|(mGlowInverted?4:0)|(mLampMode?8:0));}
	@Override public void setVisualData(byte aData) {mActive=((aData&1)!=0); mInverted=((aData&2)!=0); mGlowInverted=((aData&4)!=0); mLampMode=((aData&8)!=0);}
	
	public static final IIconContainer[][][] sTextures = new IIconContainer[][][] {
		  new IIconContainer[ 1][8]
		, new IIconContainer[10][8]
		, new IIconContainer[26][8]
		, new IIconContainer[11][8]
		, new IIconContainer[ 8][8]
		, new IIconContainer[16][8]
		, new IIconContainer[22][8]
	};
	
	static {
		for (int i = 0; i < sTextures.length; i++) {
			for (int j = 0; j < sTextures[i].length; j++) {
				sTextures[i][j][0] = new Textures.BlockIcons.CustomIcon("machines/redstone/buttons/advanced/"+i+"/"+j+"/colored_off");
				sTextures[i][j][1] = new Textures.BlockIcons.CustomIcon("machines/redstone/buttons/advanced/"+i+"/"+j+"/overlay_off");
				sTextures[i][j][2] = new Textures.BlockIcons.CustomIcon("machines/redstone/buttons/advanced/"+i+"/"+j+"/glowing_off");
				sTextures[i][j][3] = new Textures.BlockIcons.CustomIcon("machines/redstone/buttons/advanced/"+i+"/"+j+"/glowverlay_off");
				sTextures[i][j][4] = new Textures.BlockIcons.CustomIcon("machines/redstone/buttons/advanced/"+i+"/"+j+"/colored_on");
				sTextures[i][j][5] = new Textures.BlockIcons.CustomIcon("machines/redstone/buttons/advanced/"+i+"/"+j+"/overlay_on");
				sTextures[i][j][6] = new Textures.BlockIcons.CustomIcon("machines/redstone/buttons/advanced/"+i+"/"+j+"/glowing_on");
				sTextures[i][j][7] = new Textures.BlockIcons.CustomIcon("machines/redstone/buttons/advanced/"+i+"/"+j+"/glowverlay_on");
			}
		}
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.redstone.button.advanced";}
}
