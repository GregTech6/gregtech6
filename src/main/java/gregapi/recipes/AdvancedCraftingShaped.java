/**
 * Copyright (c) 2023 GregTech-6 Team
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

package gregapi.recipes;

import gregapi.code.TagData;
import gregapi.item.IItemEnergy;
import gregapi.item.IItemGTContainerTool;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class AdvancedCraftingShaped extends ShapedOreRecipe implements ICraftingRecipeGT {
	public final boolean mDismantleable, mRemovableByGT, mAutoCraftable, mKeepingNBT;
	private final Enchantment[] mEnchantmentsAdded;
	private final int[] mEnchantmentLevelsAdded;
	
	public AdvancedCraftingShaped(ItemStack aResult, boolean aDismantleAble, boolean aRemovableByGT, boolean aKeepingNBT, boolean aAutoCraftable, Enchantment[] aEnchantmentsAdded, int[] aEnchantmentLevelsAdded, Object... aRecipe) {
		super(aResult, aRecipe);
		mEnchantmentsAdded = aEnchantmentsAdded;
		mEnchantmentLevelsAdded = aEnchantmentLevelsAdded;
		mRemovableByGT = aRemovableByGT;
		mKeepingNBT = aKeepingNBT;
		mDismantleable = aDismantleAble;
		mAutoCraftable = aAutoCraftable;
	}
	
	@Override
	public boolean matches(InventoryCrafting aGrid, World aWorld) {
		if (mKeepingNBT) {
			ItemStack tStack = null;
			for (int i = 0; i < aGrid.getSizeInventory(); i++) {
				if (aGrid.getStackInSlot(i) != null && aGrid.getStackInSlot(i).hasTagCompound()) {
					if (tStack != null) {
						if ((tStack.hasTagCompound() != aGrid.getStackInSlot(i).hasTagCompound()) || (tStack.hasTagCompound() && !tStack.getTagCompound().equals(aGrid.getStackInSlot(i).getTagCompound()))) return F;
					}
					tStack = aGrid.getStackInSlot(i);
				}
			}
		}
		return super.matches(aGrid, aWorld);
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting aGrid) {
		ItemStack rStack = super.getCraftingResult(aGrid);
		if (rStack != null) {
			// Update the Stack
			ST.update(rStack);
			
			// Keeping NBT
			if (mKeepingNBT) for (int i = 0; i < aGrid.getSizeInventory(); i++) {
				if (aGrid.getStackInSlot(i) != null && aGrid.getStackInSlot(i).hasTagCompound()) {
					UT.NBT.set(rStack, (NBTTagCompound)aGrid.getStackInSlot(i).getTagCompound().copy());
					break;
				}
			}
			
			// GT Charge Values
			if (rStack.getItem() instanceof IItemEnergy) {
				for (TagData tEnergyType : ((IItemEnergy)rStack.getItem()).getEnergyTypes(rStack)) {
					long tCharge = 0;
					for (int i = 0; i < aGrid.getSizeInventory(); i++) if (aGrid.getStackInSlot(i) != null && aGrid.getStackInSlot(i).getItem() instanceof IItemEnergy && !(aGrid.getStackInSlot(i).getItem() instanceof IItemGTContainerTool)) {
						tCharge += ((IItemEnergy)aGrid.getStackInSlot(i).getItem()).getEnergyStored(tEnergyType, aGrid.getStackInSlot(i));
					}
					((IItemEnergy)rStack.getItem()).setEnergyStored(tEnergyType, rStack, tCharge);
				}
			}
			
			// Saving Ingredients inside the Item.
			if (mDismantleable) {
				NBTTagCompound rNBT = rStack.getTagCompound(), tNBT = UT.NBT.make();
				if (rNBT == null) rNBT = UT.NBT.make();
				for (int i = 0; i < 9; i++) {
					ItemStack tStack = aGrid.getStackInSlot(i);
					if (tStack != null && ST.container(tStack, T) == null && !(tStack.getItem() instanceof MultiItemTool)) {
						tStack = ST.amount(1, tStack);
						tNBT.setTag(""+i, ST.save(tStack));
					}
				}
				rNBT.setTag(NBT_RECYCLING_COMPS, tNBT);
				UT.NBT.set(rStack, rNBT);
			}
			
			// Add Enchantments
			for (int i = 0; i < mEnchantmentsAdded.length; i++) UT.NBT.addEnchantment(rStack, mEnchantmentsAdded[i], UT.NBT.getEnchantmentLevel(mEnchantmentsAdded[i], rStack) + mEnchantmentLevelsAdded[i]);
			
			// Update the Stack again
			ST.update(rStack);
		}
		return rStack;
	}
	
	@Override
	public boolean isRemovableByGT() {
		return mRemovableByGT;
	}
	
	@Override
	public boolean isAutocraftableByGT() {
		return mAutoCraftable;
	}
}
