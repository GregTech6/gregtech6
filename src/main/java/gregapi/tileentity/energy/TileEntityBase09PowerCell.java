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

package gregapi.tileentity.energy;

import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.code.TagData;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

import static gregapi.data.CS.F;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase09PowerCell extends TileEntityBase08Battery {
	public abstract ItemStack getEmptyPowerCell();
	
	@Override
	public ItemStack setEnergyStored(TagData aEnergyType, ItemStack aStack, long aAmount) {
		if ((aEnergyType != mType && aEnergyType != null) || ST.size(aStack) <= 0) return aStack;
		mEnergy = aAmount;
		if (mEnergy < mSizeMax) {
			mEnergy = 0;
			ST.set(aStack, getEmptyPowerCell(), F, F);
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
	
	@Override public long doEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoInject) {return 0;}
	@Override public boolean canEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize) {return F;}
}
