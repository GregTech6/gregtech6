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

package gregtech.tileentity.tools;

import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.CS.*;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityRope extends TileEntityBase09FacingSingle implements ITileEntityQuickObstructionCheck, IMTE_IgnorePlayerCollisionWhenPlacing, IMTE_IsLadder, IMTE_OnBlockHarvested, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		ItemStack aStack = aPlayer.getCurrentEquippedItem();
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry(getMultiTileEntityRegistryID());
		if (tRegistry != null && ST.equal(aStack, toStack(), F)) {
			if (isServerSide()) for (int tY = yCoord-1; tY >= 0; tY--) {
				TileEntity tTileEntity = getTileEntity(xCoord, tY, zCoord);
				if (tTileEntity instanceof MultiTileEntityRope) {
					if (((MultiTileEntityRope)tTileEntity).getMultiTileEntityRegistryID() != getMultiTileEntityRegistryID()) return T;
					if (((MultiTileEntityRope)tTileEntity).getMultiTileEntityID() != getMultiTileEntityID()) return T;
					if (((MultiTileEntityRope)tTileEntity).mFacing != mFacing) return T;
					continue;
				}
				if (WD.air(worldObj, xCoord, tY, zCoord)) {
					tRegistry.mBlock.placeBlock(worldObj, xCoord, tY, zCoord, SIDE_ANY, getMultiTileEntityID(), UT.NBT.make(aStack.hasTagCompound()?(NBTTagCompound)aStack.getTagCompound().copy():null, NBT_FACING, mFacing), T, F);
					if (!UT.Entities.hasInfiniteItems(aPlayer)) aStack.stackSize--;
					UT.Sounds.send(SFX.MC_DIG_CLOTH, this, F);
				}
				return T;
			}
			return T;
		}
		return F;
	}
	
	@Override
	public void onBlockHarvested(int aMetaData, EntityPlayer aPlayer) {
		if (isServerSide() && aPlayer != null) {
			TileEntity tTileEntity = getTileEntityAtSideAndDistance(SIDE_UP, 1);
			if (!(tTileEntity instanceof MultiTileEntityRope)) for (int tY = yCoord-1; tY >= 0; tY--) {
				tTileEntity = getTileEntity(xCoord, tY, zCoord);
				if (tTileEntity instanceof MultiTileEntityRope && ((MultiTileEntityRope)tTileEntity).mFacing == mFacing && ((MultiTileEntityRope)tTileEntity).getMultiTileEntityRegistryID() == getMultiTileEntityRegistryID() && ((MultiTileEntityRope)tTileEntity).getMultiTileEntityID() == getMultiTileEntityID()) {
					((MultiTileEntityRope)tTileEntity).popOff(aPlayer);
					continue;
				}
				break;
			}
		}
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide && (mBlockUpdated || aTimer == 1) && !WD.opq(worldObj, getOffsetX(mFacing), getOffsetY(mFacing), getOffsetZ(mFacing), F, T) && !WD.opq(worldObj, xCoord, yCoord+1, zCoord, F, T) && !(getTileEntityAtSideAndDistance(SIDE_UP, 1) instanceof MultiTileEntityRope)) popOff();
	}
	
	@Override public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {return mFacing == SIDE_Y_NEG ? 2 : 1;}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case 0:
			switch(mFacing) {
			case SIDE_Z_POS: return box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[12], PX_N[ 6], PX_N[ 0], PX_N[ 0]);
			case SIDE_Z_NEG: return box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 0], PX_N[ 6], PX_N[ 0], PX_N[12]);
			case SIDE_X_POS: return box(aBlock, PX_P[12], PX_P[ 0], PX_P[ 6], PX_N[ 0], PX_N[ 0], PX_N[ 6]);
			case SIDE_X_NEG: return box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 6], PX_N[12], PX_N[ 0], PX_N[ 6]);
			case SIDE_Y_POS: return box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 0], PX_N[ 6]);
			case SIDE_Y_NEG: return box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[12], PX_N[ 2]);
			default        : return box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[12], PX_N[ 2]);
			}
		case 1:
			return box(aBlock, PX_P[ 4], PX_P[ 4], PX_P[ 4], PX_N[ 4], PX_N[ 8], PX_N[ 4]);
		default:
			return F;
		}
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return BlockTextureMulti.get(BlockTextureDefault.get(sColored, mRGBa), BlockTextureDefault.get(sOverlay));
	}
	
	// Icons
	public static IIconContainer sColored = new Textures.BlockIcons.CustomIcon("machines/tools/rope/colored"), sOverlay = new Textures.BlockIcons.CustomIcon("machines/tools/rope/overlay");
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool() {
		switch(mFacing) {
		case SIDE_Z_POS: return box(PX_P[ 7], PX_P[ 0], PX_P[12], PX_N[ 7], PX_N[ 0], PX_N[ 2]);
		case SIDE_Z_NEG: return box(PX_P[ 7], PX_P[ 0], PX_P[ 2], PX_N[ 7], PX_N[ 0], PX_N[12]);
		case SIDE_X_POS: return box(PX_P[12], PX_P[ 0], PX_P[ 7], PX_N[ 2], PX_N[ 0], PX_N[ 7]);
		case SIDE_X_NEG: return box(PX_P[ 2], PX_P[ 0], PX_P[ 7], PX_N[12], PX_N[ 0], PX_N[ 7]);
		case SIDE_Y_POS: return box(PX_P[ 7], PX_P[ 0], PX_P[ 7], PX_N[ 7], PX_N[ 0], PX_N[ 7]);
		case SIDE_Y_NEG: return box(PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[ 8], PX_N[ 2]);
		default        : return box(PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[ 8], PX_N[ 2]);
		}
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool() {
		switch(mFacing) {
		case SIDE_Z_POS: return box(PX_P[ 6], PX_P[ 0], PX_P[12], PX_N[ 6], PX_N[ 0], PX_N[ 0]);
		case SIDE_Z_NEG: return box(PX_P[ 6], PX_P[ 0], PX_P[ 0], PX_N[ 6], PX_N[ 0], PX_N[12]);
		case SIDE_X_POS: return box(PX_P[12], PX_P[ 0], PX_P[ 6], PX_N[ 0], PX_N[ 0], PX_N[ 6]);
		case SIDE_X_NEG: return box(PX_P[ 0], PX_P[ 0], PX_P[ 6], PX_N[12], PX_N[ 0], PX_N[ 6]);
		case SIDE_Y_POS: return box(PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 0], PX_N[ 6]);
		case SIDE_Y_NEG: return box(PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[ 8], PX_N[ 2]);
		default        : return box(PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[ 8], PX_N[ 2]);
		}
	}
	
	@Override
	public void setBlockBoundsBasedOnState(Block aBlock) {
		switch(mFacing) {
		case SIDE_Z_POS: box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[12], PX_N[ 6], PX_N[ 0], PX_N[ 0]); return;
		case SIDE_Z_NEG: box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 0], PX_N[ 6], PX_N[ 0], PX_N[12]); return;
		case SIDE_X_POS: box(aBlock, PX_P[12], PX_P[ 0], PX_P[ 6], PX_N[ 0], PX_N[ 0], PX_N[ 6]); return;
		case SIDE_X_NEG: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 6], PX_N[12], PX_N[ 0], PX_N[ 6]); return;
		case SIDE_Y_POS: box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 6], PX_N[ 6], PX_N[ 0], PX_N[ 6]); return;
		case SIDE_Y_NEG: box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[ 8], PX_N[ 2]); return;
		default        : box(aBlock, PX_P[ 2], PX_P[ 0], PX_P[ 2], PX_N[ 2], PX_N[ 8], PX_N[ 2]); return;
		}
	}
	
	@Override public float getSurfaceSize          (byte aSide) {return 0;}
	@Override public float getSurfaceSizeAttachable(byte aSide) {return 0;}
	@Override public float getSurfaceDistance      (byte aSide) {return 0;}
	@Override public boolean isSurfaceSolid        (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2      (byte aSide) {return F;}
	@Override public boolean isSideSolid2          (byte aSide) {return F;}
	@Override public boolean allowCovers           (byte aSide) {return F;}
	@Override public boolean attachCoversFirst     (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt  (byte aSide) {return F;}
	@Override public boolean isLadder(EntityLivingBase aEntity) {return T;}
	@Override public boolean ignorePlayerCollisionWhenPlacing() {return T;}
	@Override public boolean useSidePlacementRotation        () {return T;}
	@Override public boolean useInversePlacementRotation     () {return T;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public byte getDefaultSide() {return SIDE_Y_NEG;}
	@Override public boolean[] getValidSides() {return SIDES_VALID;}
	@Override public boolean isUsingWrenchingOverlay(ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public String getFacingTool() {return null;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.rope";}
}
