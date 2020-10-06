/**
 * Copyright (c) 2020 GregTech-6 Team
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
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityScaffold extends TileEntityBase09FacingSingle implements ITileEntityQuickObstructionCheck, IMTE_IgnorePlayerCollisionWhenPlacing, IMTE_IsLadder, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	@Override
	public void onTickFirst2(boolean aIsServerSide) {
		if (aIsServerSide && !isConnectedToGround()) popOff();
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			if (mBlockUpdatedLastTime && !isConnectedToGround()) popOff();
			mBlockUpdatedLastTime = mBlockUpdated;
		}
	}
	
	protected ITexture mTexture;
	protected byte mRenderValue = 0;
	protected boolean mBlockUpdatedLastTime = F;
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		if (isConnectedVertically()) {
			if (getAdjacentTileEntity(SIDE_UP).mTileEntity instanceof MultiTileEntityScaffold) {
				mRenderValue = 2;
			} else {
				mRenderValue = 1;
			}
		} else {
			mRenderValue = 0;
		}
		mTexture = BlockTextureDefault.get(mMaterial, OP.blockSolid, UT.Code.getRGBaArray(mRGBa), mMaterial.contains(TD.Properties.GLOWING), F);
		return mRenderValue == 0 ? 4 : 7;
	}
	
	public boolean isConnectedToGround() {
		if (isConnectedVertically()) return T;
		Block tBlock = getBlock(getCoords());
		if (SIDES_AXIS_X[mFacing]) {
			for (byte tSide : ALL_SIDES_X) for (int i = 1; i < 128; i++) if (tBlock == getBlockAtSideAndDistance(tSide, i)) {
				TileEntity tTileEntity = getTileEntityAtSideAndDistance(tSide, i);
				if (tTileEntity instanceof MultiTileEntityScaffold) {
					if (((MultiTileEntityScaffold)tTileEntity).isConnectedVertically()) return T;
				} else break;
			} else break;
		}
		if (SIDES_AXIS_Z[mFacing]) {
			for (byte tSide : ALL_SIDES_Z) for (int i = 1; i < 128; i++) if (tBlock == getBlockAtSideAndDistance(tSide, i)) {
				TileEntity tTileEntity = getTileEntityAtSideAndDistance(tSide, i);
				if (tTileEntity instanceof MultiTileEntityScaffold) {
					if (((MultiTileEntityScaffold)tTileEntity).isConnectedVertically()) return T;
				} else break;
			} else break;
		}
		return F;
	}
	public boolean isConnectedVertically() {
		return worldObj == null || WD.opq(worldObj, xCoord, yCoord-1, zCoord, T, T) || getAdjacentTileEntity(SIDE_DOWN).mTileEntity instanceof MultiTileEntityScaffold;
	}
	
	@Override public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {return aRenderPass != 0 || mRenderValue != 2;}
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return mTexture;}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (mRenderValue > 0) switch(aRenderPass) {
			default        : return box(aBlock, PX_P[ 0], PX_P[10], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
			case  1        : return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[14], PX_N[ 0], PX_N[14]);
			case  2        : return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[14], PX_N[14], PX_N[ 0], PX_N[ 0]);
			case  3        : return box(aBlock, PX_P[14], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[14]);
			case  4        : return box(aBlock, PX_P[14], PX_P[ 0], PX_P[14], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
			case  5: switch(mFacing) {
			default        : return box(aBlock, PX_P[ 1], PX_P[ 3], PX_P[ 1], PX_N[ 1], PX_N[11], PX_N[14]);
			case SIDE_Z_NEG: return box(aBlock, PX_P[ 1], PX_P[ 3], PX_P[14], PX_N[ 1], PX_N[11], PX_N[ 1]);
			case SIDE_X_POS: return box(aBlock, PX_P[ 1], PX_P[ 3], PX_P[ 1], PX_N[14], PX_N[11], PX_N[ 1]);
			case SIDE_X_NEG: return box(aBlock, PX_P[14], PX_P[ 3], PX_P[ 1], PX_N[ 1], PX_N[11], PX_N[ 1]);
			}
			case  6: switch(mFacing) {
			default        : return box(aBlock, PX_P[ 1], PX_P[11], PX_P[ 1], PX_N[ 1], PX_N[ 3], PX_N[14]);
			case SIDE_Z_NEG: return box(aBlock, PX_P[ 1], PX_P[11], PX_P[14], PX_N[ 1], PX_N[ 3], PX_N[ 1]);
			case SIDE_X_POS: return box(aBlock, PX_P[ 1], PX_P[11], PX_P[ 1], PX_N[14], PX_N[ 3], PX_N[ 1]);
			case SIDE_X_NEG: return box(aBlock, PX_P[14], PX_P[11], PX_P[ 1], PX_N[ 1], PX_N[ 3], PX_N[ 1]);
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
		if (isConnectedVertically()) {
			if (!(getAdjacentTileEntity(SIDE_UP).mTileEntity instanceof MultiTileEntityScaffold)) {
				if (aEntity == null || (!aEntity.isSneaking() && aEntity.posY >= yCoord+1)) {
					box(aAABB, aList, PX_P[ 0], PX_P[14], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
				}
			}
			
			box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[15], PX_N[ 0], PX_N[15]);
			box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[15], PX_N[15], PX_N[ 0], PX_N[ 0]);
			box(aAABB, aList, PX_P[15], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[15]);
			box(aAABB, aList, PX_P[15], PX_P[ 0], PX_P[15], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
			switch(mFacing) {
			default        : box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[15]); break;
			case SIDE_Z_NEG: box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[15], PX_N[ 0], PX_N[ 0], PX_N[ 0]); break;
			case SIDE_X_POS: box(aAABB, aList, PX_P[ 0], PX_P[ 0], PX_P[ 0], PX_N[15], PX_N[ 0], PX_N[ 0]); break;
			case SIDE_X_NEG: box(aAABB, aList, PX_P[15], PX_P[ 0], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]); break;
			}
		} else {
			box(aAABB, aList, PX_P[ 0], PX_P[14], PX_P[ 0], PX_N[ 0], PX_N[ 0], PX_N[ 0]);
		}
	}
	
	@Override public boolean addDefaultCollisionBoxToList() {return F;}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(0, isConnectedVertically() ? 0 : PX_P[12], 0, 1, 1, 1);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(0, isConnectedVertically() ? 0 : PX_P[12], 0, 1, 1, 1);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {         box(0, isConnectedVertically() ? 0 : PX_P[12], 0, 1, 1, 1);}
	@Override public float getSurfaceSize          (byte aSide) {return SIDES_TOP[aSide] ? 1 : 0;}
	@Override public float getSurfaceSizeAttachable(byte aSide) {return SIDES_TOP[aSide] ? 1 : 0;}
	@Override public float getSurfaceDistance      (byte aSide) {return 0;}
	@Override public boolean isSurfaceSolid        (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean isSurfaceOpaque2      (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean isSideSolid2          (byte aSide) {return SIDES_TOP[aSide];}
	@Override public boolean isCoverSurface        (byte aSide) {return F;}
	@Override public boolean allowCovers           (byte aSide) {return F;}
	@Override public boolean allowCoverHolders     (byte aSide) {return F;}
	@Override public boolean attachCoversFirst     (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt  (byte aSide) {return F;}
	@Override public boolean isLadder(EntityLivingBase aEntity) {return T;}
	@Override public boolean ignorePlayerCollisionWhenPlacing() {return T;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.scaffold";}
}
