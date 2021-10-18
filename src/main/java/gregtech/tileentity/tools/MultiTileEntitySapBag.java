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
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_SetBlockBoundsBasedOnState;
import gregapi.data.CS.GarbageGT;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.fluid.FluidTankGT;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.ITileEntityTreeHole;
import gregapi.tileentity.base.TileEntityBase09FacingSingle;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntitySapBag extends TileEntityBase09FacingSingle implements ITileEntityQuickObstructionCheck, IMTE_IgnorePlayerCollisionWhenPlacing, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool {
	public boolean mFull = F;
	public FluidTankGT mTank = new FluidTankGT(8000);
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_ACTIVE)) mFull = aNBT.getBoolean(NBT_ACTIVE);
		mTank.setCapacity(aNBT.getLong(NBT_TANK_CAPACITY));
		mTank.readFromNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE, mFull);
		mTank.writeToNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.ORANGE + LH.get(LH.NO_GUI_CLICK_TO_INVENTORY));
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(mFacing);
			if (tDelegator.mTileEntity instanceof ITileEntityTreeHole) {
				if (((ITileEntityTreeHole)tDelegator.mTileEntity).hasResin(tDelegator.mSideOfTileEntity)) {
					if (((ITileEntityTreeHole)tDelegator.mTileEntity).extractResin(tDelegator.mSideOfTileEntity)) {
						mTank.fill(((ITileEntityTreeHole)tDelegator.mTileEntity).getResinFluid(tDelegator.mSideOfTileEntity), T);
						addStackToSlot(0, ((ITileEntityTreeHole)tDelegator.mTileEntity).getResinItem(tDelegator.mSideOfTileEntity));
					}
				}
			}
			mFull = (mTank.has() || slotHas(0));
		}
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide()) {
			if (UT.Inventories.addStackToPlayerInventory(aPlayer, slot(0), T)) {
				slotKill(0);
				return T;
			}
			ItemStack aStack = aPlayer.getCurrentEquippedItem(), tStack;
			if (aStack != null) if ((tStack = FL.fill(mTank, ST.amount(1, aStack), T, T, T, T)) != null) {
				aStack.stackSize--;
				UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, tStack, T);
				return T;
			}
		}
		return T;
	}
	
	@Override
	public boolean breakBlock() {
		GarbageGT.trash(mTank);
		return super.breakBlock();
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(mFacing) {
		case SIDE_Z_NEG: box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[ 0], PX_N[ 5], PX_N[ 9], PX_N[10]); return T;
		case SIDE_Z_POS: box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[10], PX_N[ 5], PX_N[ 9], PX_N[ 0]); return T;
		case SIDE_X_NEG: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 5], PX_N[10], PX_N[ 9], PX_N[ 5]); return T;
		default        : box(aBlock, PX_P[10], PX_P[ 0], PX_P[ 5], PX_N[ 0], PX_N[ 9], PX_N[ 5]); return T;
		}
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return (aSide != mFacing && SIDES_TOP_HORIZONTAL[aSide]) || aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get((mFull?sOverlaysFull:sOverlays)[FACES_TBS[aSide]])) : null;
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tools/sapbag/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tools/sapbag/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/tools/sapbag/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tools/sapbag/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tools/sapbag/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/tools/sapbag/overlay/side"),
	}, sOverlaysFull[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tools/sapbag/overlay_full/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tools/sapbag/overlay_full/top"),
		new Textures.BlockIcons.CustomIcon("machines/tools/sapbag/overlay_full/side"),
	};
	
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return null;}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool() {
		switch(mFacing) {
		case SIDE_Z_NEG: return box(PX_P[ 5], PX_P[ 0], PX_P[ 0], PX_N[ 5], PX_N[ 9], PX_N[10]);
		case SIDE_Z_POS: return box(PX_P[ 5], PX_P[ 0], PX_P[10], PX_N[ 5], PX_N[ 9], PX_N[ 0]);
		case SIDE_X_NEG: return box(PX_P[ 0], PX_P[ 0], PX_P[ 5], PX_N[10], PX_N[ 9], PX_N[ 5]);
		default        : return box(PX_P[10], PX_P[ 0], PX_P[ 5], PX_N[ 0], PX_N[ 9], PX_N[ 5]);
		}
	}
	
	@Override
	public void setBlockBoundsBasedOnState(Block aBlock) {
		switch(mFacing) {
		case SIDE_Z_NEG: box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[ 0], PX_N[ 5], PX_N[ 9], PX_N[10]); break;
		case SIDE_Z_POS: box(aBlock, PX_P[ 5], PX_P[ 0], PX_P[10], PX_N[ 5], PX_N[ 9], PX_N[ 0]); break;
		case SIDE_X_NEG: box(aBlock, PX_P[ 0], PX_P[ 0], PX_P[ 5], PX_N[10], PX_N[ 9], PX_N[ 5]); break;
		default        : box(aBlock, PX_P[10], PX_P[ 0], PX_P[ 5], PX_N[ 0], PX_N[ 9], PX_N[ 5]); break;
		}
	}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[1];}
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ZL_INTEGER;}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	
	@Override public float getSurfaceSize           (byte aSide) {return 0;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return 0;}
	@Override public float getSurfaceDistance       (byte aSide) {return 0;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean attachCoversFirst      (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return aSide == mFacing;}
	@Override public boolean checkObstruction(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public boolean useSidePlacementRotation       () {return T;}
	@Override public boolean useInversePlacementRotation    () {return T;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	@Override public boolean isUsingWrenchingOverlay(ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public byte getVisualData() {return (byte)(mFull ? 1 : 0);}
	@Override public void setVisualData(byte aData) {mFull = (aData != 0);}
	@Override public boolean ignorePlayerCollisionWhenPlacing() {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.sapbag";}
}
