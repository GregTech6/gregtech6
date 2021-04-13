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

package gregtech.tileentity.tools;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_IgnorePlayerCollisionWhenPlacing;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_IsLadder;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.old.Textures.BlockIcons;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityScaffold extends TileEntityBase09FacingSingle implements ITileEntityQuickObstructionCheck, IMTE_IgnorePlayerCollisionWhenPlacing, IMTE_IsLadder, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	protected byte mDesign = 1;
	protected boolean mBlockUpdatedLastTime = T;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_DESIGN)) mDesign = aNBT.getByte(NBT_DESIGN);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		aNBT.setByte(NBT_DESIGN, mDesign);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (mBlockUpdatedLastTime) onFacingChange(SIDE_UNKNOWN);
			mBlockUpdatedLastTime = mBlockUpdated;
		}
	}
	
	@Override
	public void onFacingChange(byte aPreviousFacing) {
		if (isServerSide()) {
			if (isConnectedVertically()) {
				setDesign(getAdjacentTileEntity(SIDE_UP).mTileEntity instanceof MultiTileEntityScaffold ? 2 : getAdjacentTileEntity(SIDE_DOWN).mTileEntity instanceof MultiTileEntityScaffold ? 1 : 3);
			} else {
				setDesign(0);
				if (!isConnectedToGround()) {popOff(); return;}
			}
		}
	}
	
	public boolean setDesign(long aDesign) {
		if (mDesign == aDesign) return F;
		mDesign = (byte)aDesign;
		checkCoverValidity();
		updateClientData();
		causeBlockUpdate();
		return T;
	}
	
	public boolean isConnectedVertically() {
		return worldObj == null || WD.opq(worldObj, xCoord, yCoord-1, zCoord, T, T) || getAdjacentTileEntity(SIDE_DOWN).mTileEntity instanceof MultiTileEntityScaffold;
	}
	public boolean isConnectedToGround() {
		if (isConnectedVertically()) return T;
		Block tBlock = getBlock(getCoords());
		for (byte tSide : SIDES_AXIS_X[mFacing] ? ALL_SIDES_X : ALL_SIDES_Z) for (int i = 1; i < 256; i++) if (tBlock == getBlockAtSideAndDistance(tSide, i)) {
			TileEntity tTileEntity = getTileEntityAtSideAndDistance(tSide, i);
			if (tTileEntity instanceof MultiTileEntityScaffold) {
				if (((MultiTileEntityScaffold)tTileEntity).isConnectedVertically()) return T;
			} else break;
		} else break;
		return F;
	}
	
	protected ITexture mTexturePlate, mTextureHatch, mTextureRod;
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTexturePlate = mTextureHatch = mTextureRod = BlockTextureDefault.get(BlockIcons.PLATE, mRGBa, mMaterial.contains(TD.Properties.GLOWING));
		if (mDesign == 3) return 1;
		mTextureRod = BlockTextureDefault.get(mMaterial, OP.blockSolid, UT.Code.getRGBaArray(mRGBa), mMaterial.contains(TD.Properties.GLOWING), F);
		if (mDesign == 0) return 4;
		if (mDesign == 2) return 7;
		mTextureHatch = BlockTextureMulti.get(mTexturePlate, BlockTextureDefault.get(BlockIcons.HATCH, mRGBa, mMaterial.contains(TD.Properties.GLOWING)));
		return 7;
	}
	
	@Override public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {return aRenderPass != 0 || mDesign != 2;}
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return mDesign == 3 ? aShouldSideBeRendered[aSide] ? mTexturePlate : null : aRenderPass == 0 || aRenderPass > 4 ? mDesign == 1 && SIDES_VERTICAL[aSide] ? mTextureHatch : mTexturePlate : mTextureRod;}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (mDesign == 3) return F;
		if (mDesign != 0) switch(aRenderPass) {
			default        : return box(aBlock, PX_P[ 0], PX_P[10], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
			case  1        : return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_N[mDesign==2?0:6], PX_N[14]);
			case  2        : return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[14], PX_N[14], PX_N[mDesign==2?0:6], PX_N[ 0]);
			case  3        : return box(aBlock, PX_P[14], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[mDesign==2?0:6], PX_N[14]);
			case  4        : return box(aBlock, PX_P[14], PX_P[ 0], PX_P[14], PX_N[ 0], PX_N[mDesign==2?0:6], PX_N[ 0]);
			case  5: switch(mFacing) {
			default        : return box(aBlock, PX_P[ 2], PX_P[ 4], PX_P[ 0], PX_N[ 2], PX_N[11], PX_N[12]);
			case SIDE_Z_NEG: return box(aBlock, PX_P[ 2], PX_P[ 4], PX_P[12], PX_N[ 2], PX_N[11], PX_N[ 0]);
			case SIDE_X_POS: return box(aBlock, PX_P[ 0], PX_P[ 4], PX_P[ 2], PX_N[12], PX_N[11], PX_N[ 2]);
			case SIDE_X_NEG: return box(aBlock, PX_P[12], PX_P[ 4], PX_P[ 2], PX_N[ 0], PX_N[11], PX_N[ 2]);
			}
			case  6: switch(mFacing) {
			default        : return box(aBlock, PX_P[ 2], PX_P[12], PX_P[ 0], PX_N[ 2], PX_N[ 3], PX_N[12]);
			case SIDE_Z_NEG: return box(aBlock, PX_P[ 2], PX_P[12], PX_P[12], PX_N[ 2], PX_N[ 3], PX_N[ 0]);
			case SIDE_X_POS: return box(aBlock, PX_P[ 0], PX_P[12], PX_P[ 2], PX_N[12], PX_N[ 3], PX_N[ 2]);
			case SIDE_X_NEG: return box(aBlock, PX_P[12], PX_P[12], PX_P[ 2], PX_N[ 0], PX_N[ 3], PX_N[ 2]);
			}
		}
		switch(aRenderPass) {
		default: return box(aBlock, PX_P[ 0], PX_P[14], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
		case  1: if (SIDES_AXIS_X[mFacing])
				 return box(aBlock, PX_P[ 0], PX_P[12], PX_P[ 2], PX_N[ 0], PX_N[ 2], PX_N[12]);
				 return box(aBlock, PX_P[ 2], PX_P[12], PX_P[ 0], PX_N[12], PX_N[ 2], PX_N[ 0]);
		case  2: if (SIDES_AXIS_X[mFacing])
				 return box(aBlock, PX_P[ 0], PX_P[10], PX_P[ 6], PX_N[ 0], PX_N[ 2], PX_N[ 6]);
				 return box(aBlock, PX_P[ 6], PX_P[10], PX_P[ 0], PX_N[ 6], PX_N[ 2], PX_N[ 0]);
		case  3: if (SIDES_AXIS_X[mFacing])
				 return box(aBlock, PX_P[ 0], PX_P[12], PX_P[12], PX_N[ 0], PX_N[ 2], PX_N[ 2]);
				 return box(aBlock, PX_P[12], PX_P[12], PX_P[ 0], PX_N[ 2], PX_N[ 2], PX_N[ 0]);
		}
	}
	
	@Override
	public void addCollisionBoxesToList2(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, Entity aEntity) {
		switch(mDesign) {
		default: box(aAABB, aList); return;
		case  0: box(aAABB, aList, PX_P[ 0], PX_P[14], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]); return;
		case  1:
			if (aEntity == null || (!aEntity.isSneaking() && aEntity.posY >= yCoord+1))
			box(aAABB, aList, PX_P[ 0], PX_P[14], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
		case  2:
			box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[15], PX_N[ 0], PX_N[15]);
			box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[15], PX_N[15], PX_N[ 0], PX_N[ 0]);
			box(aAABB, aList, PX_P[15], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[15]);
			box(aAABB, aList, PX_P[15], PX_P[ 0], PX_P[15], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
			
			switch(mFacing) {
			default        : box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[14]); break;
			case SIDE_Z_NEG: box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[14], PX_N[ 0], PX_N[ 0], PX_N[ 0]); break;
			case SIDE_X_POS: box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_N[ 0], PX_N[ 0]); break;
			case SIDE_X_NEG: box(aAABB, aList, PX_P[14], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]); break;
			}
			return;
		}
	}
	
	@Override public byte getVisualData() {return mDesign;}
	@Override public void setVisualData(byte aData) {mDesign = aData;}
	@Override public boolean addDefaultCollisionBoxToList() {return F;}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(0, mDesign == 0 ? PX_P[14] : 0, 0, 1, 1, 1);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(0, mDesign == 0 ? PX_P[14] : 0, 0, 1, 1, 1);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {         box(0, mDesign == 0 ? PX_P[14] : 0, 0, 1, 1, 1);}
	@Override public float getSurfaceSize          (byte aSide) {return SIDES_TOP[aSide] || mDesign == 3 ? 1 : 0;}
	@Override public float getSurfaceSizeAttachable(byte aSide) {return SIDES_TOP[aSide] || mDesign == 3 ? 1 : 0;}
	@Override public float getSurfaceDistance      (byte aSide) {return 0;}
	@Override public boolean isSurfaceSolid        (byte aSide) {return SIDES_TOP[aSide] || mDesign == 3;}
	@Override public boolean isSurfaceOpaque2      (byte aSide) {return SIDES_TOP[aSide] || mDesign == 3;}
	@Override public boolean isSideSolid2          (byte aSide) {return SIDES_TOP[aSide] || mDesign == 3;}
	@Override public boolean isCoverSurface        (byte aSide) {return SIDES_TOP[aSide] || mDesign == 3;}
	@Override public boolean allowCovers           (byte aSide) {return mDesign == 3;}
	@Override public boolean allowCoverHolders     (byte aSide) {return F;}
	@Override public boolean attachCoversFirst     (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt  (byte aSide) {return mDesign == 3;}
	@Override public boolean isLadder(EntityLivingBase aEntity) {return mDesign != 3;}
	@Override public boolean ignorePlayerCollisionWhenPlacing() {return T;}
	@Override public boolean useSidePlacementRotation        () {return F;}
	@Override public boolean ignorePlayerCollisionWhenPlacing(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {return aPlayer == null || !aPlayer.isSneaking();}
	@Override public boolean useSidePlacementRotation        (ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {return aPlayer != null &&  aPlayer.isSneaking();}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.scaffold";}
}
