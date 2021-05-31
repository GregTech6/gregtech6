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

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetSelectedBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.ITileEntityCrucible;
import gregapi.tileentity.machines.ITileEntityMold;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCrossing extends TileEntityBase07Paintable implements ITileEntityCrucible, ITileEntityQuickObstructionCheck, IMTE_GetCollisionBoundingBoxFromPool, IMTE_SetBlockBoundsBasedOnState, IMTE_GetSelectedBoundingBoxFromPool {
	public boolean mLock = F, mRedstone = F;
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			mLock = F;
			if (mBlockUpdated || SERVER_TIME % 50 == 0) {
				if (getRedstoneIncoming(SIDE_TOP) > 0 || getRedstoneIncoming(SIDE_BOTTOM) > 0) {
					if (!mRedstone) {mRedstone = T; causeBlockUpdate();}
				} else {
					if ( mRedstone) {mRedstone = F; causeBlockUpdate();}
				}
			}
		}
	}
	
	@Override
	public boolean fillMoldAtSide(ITileEntityMold aMold, byte aSide, byte aSideOfMold) {
		if (mLock) return F;
		mLock = T;
		boolean rReturn = F;
		for (byte tSide : ALL_SIDES_HORIZONTAL) if (tSide != aSide) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
			if (tDelegator.mTileEntity instanceof ITileEntityCrucible) {
				rReturn = ((ITileEntityCrucible)tDelegator.mTileEntity).fillMoldAtSide(aMold, tDelegator.mSideOfTileEntity, aSideOfMold);
				if (rReturn) break;
			}
		}
		mLock = F;
		return rReturn;
	}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTexture = BlockTextureDefault.get(mMaterial, OP.blockSolid, UT.Code.getRGBaArray(mRGBa), mMaterial.contains(TD.Properties.GLOWING));
		return 11;
	}
	
	@Override public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {return T;}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		default: return box(aBlock, PX_P[ 6], PX_P[ 1], PX_P[ 0], PX_N[ 6], PX_N[14], PX_N[ 0]);
		case  1: return box(aBlock, PX_P[ 0], PX_P[ 1], PX_P[ 6], PX_N[10], PX_N[14], PX_N[ 6]);
		case  2: return box(aBlock, PX_P[10], PX_P[ 1], PX_P[ 6], PX_N[ 0], PX_N[14], PX_N[ 6]);
		case  3: return box(aBlock, PX_P[ 5], PX_P[ 2], PX_P[ 0], PX_N[10], PX_N[10], PX_N[11]);
		case  4: return box(aBlock, PX_P[ 5], PX_P[ 2], PX_P[11], PX_N[10], PX_N[10], PX_N[ 0]);
		case  5: return box(aBlock, PX_P[ 0], PX_P[ 2], PX_P[ 5], PX_N[10], PX_N[10], PX_N[10]);
		case  6: return box(aBlock, PX_P[10], PX_P[ 2], PX_P[ 5], PX_N[ 0], PX_N[10], PX_N[10]);
		case  7: return box(aBlock, PX_P[10], PX_P[ 2], PX_P[ 0], PX_N[ 5], PX_N[10], PX_N[11]);
		case  8: return box(aBlock, PX_P[10], PX_P[ 2], PX_P[11], PX_N[ 5], PX_N[10], PX_N[ 0]);
		case  9: return box(aBlock, PX_P[ 0], PX_P[ 2], PX_P[10], PX_N[10], PX_N[10], PX_N[ 5]);
		case 10: return box(aBlock, PX_P[10], PX_P[ 2], PX_P[10], PX_N[ 0], PX_N[10], PX_N[ 5]);
		}
	}
	
	private ITexture mTexture;
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return mTexture;}
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box(PX_P[ 0], PX_P[ 1], PX_P[ 0], PX_N[ 0], PX_N[10], PX_N[ 0]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(PX_P[ 0], PX_P[ 1], PX_P[ 0], PX_N[ 0], PX_N[10], PX_N[ 0]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock)  {box(aBlock, PX_P[ 0], PX_P[ 1], PX_P[ 0], PX_N[ 0], PX_N[10], PX_N[ 0]);}
	
	@Override public byte isProvidingWeakPower2(byte aSide) {return (byte)(mRedstone && SIDES_HORIZONTAL[aSide] ? 1 : 0);}
	
	@Override public float getSurfaceSize           (byte aSide) {return 0;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return 0;}
	@Override public float getSurfaceDistance       (byte aSide) {return SIDES_TOP[aSide]?PX_P[10]:0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean attachCoversFirst      (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return F;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.smeltery.crossing";}
}
