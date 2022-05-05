/**
 * Copyright (c) 2022 GregTech-6 Team
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

package gregtech.tileentity.batteries.eu;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetLightValue;
import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.code.TagData;
import gregapi.data.IL;
import gregapi.old.Textures.BlockIcons;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.energy.TileEntityBase08Battery;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityAneutronicFusion extends TileEntityBase08Battery implements IMTE_GetLightValue {
	@Override
	public ItemStack setEnergyStored(TagData aEnergyType, ItemStack aStack, long aAmount) {
		if ((aEnergyType != mType && aEnergyType != null) || ST.size(aStack) <= 0) return aStack;
		mEnergy = aAmount;
		if (mEnergy < mSizeMax) {
			mEnergy = 0;
			ST.set(aStack, IL.Aneutronic_Fusion_Empty.get(1), F, F);
		}
		UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		return ST.update_(aStack);
	}
	
	@Override
	public boolean getSubItems(MultiTileEntityBlockInternal aBlock, Item aItem, CreativeTabs aTab, List<ItemStack> aList, short aID) {
		if (mCapacity > 0)
		aList.add(setEnergyStored(mType, aBlock.mMultiTileEntityRegistry.getItem(aID), mCapacity));
		else
		aList.add(aBlock.mMultiTileEntityRegistry.getItem(aID));
		return F;
	}
	
	@Override public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 1;
	}
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return BlockTextureDefault.get(SIDES_VERTICAL[aSide]?SIDES_TOP[aSide]?BlockIcons.ANEUTRONIC_FUSION_TOP:BlockIcons.ANEUTRONIC_FUSION_BOTTOM:BlockIcons.ANEUTRONIC_FUSION_SIDES, mDisplayedEnergy == 0 ? UT.Code.getRGBInt(UT.Code.getR(mRGBa) / 2, UT.Code.getG(mRGBa) / 2, UT.Code.getB(mRGBa) / 2) : mRGBa, 120+mDisplayedEnergy*8);}
	
	@Override public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {return box(aBlock, PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[12], PX_N[ 4]);}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box( PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[12], PX_N[ 4]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box( PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[12], PX_N[ 4]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock  , PX_P[ 4], PX_P[ 0], PX_P[ 4], PX_N[ 4], PX_N[12], PX_N[ 4]);}
	
	@Override public byte getDisplayScaleMax() {return 15;}
	@Override public int getLightValue() {return mDisplayedEnergy;}
	@Override public long doEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoInject) {return 0;}
	@Override public boolean canEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize) {return F;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.aneutronicfusion";}
}
