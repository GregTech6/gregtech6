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

package gregapi.recipes.maps;

import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.tileentity.base.TileEntityBase01Root;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Collection;

import static gregapi.data.CS.F;
import static gregapi.data.CS.T;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapMicrowave extends RecipeMapNonGTRecipes {
	public RecipeMapMicrowave(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower, boolean aUseBucketSizeIn, boolean aUseBucketSizeOut) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, aUseBucketSizeIn, aUseBucketSizeOut);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		if (aInputs == null || aInputs.length <= 0 || aInputs[0] == null) return null;
		if (aRecipe != null && aRecipe.isRecipeInputEqual(F, T, aFluids, aInputs)) return aRecipe;
		ItemStack tOutput = RM.get_smelting(aInputs[0]);
		
		if (ST.equal(aInputs[0], Items.book)) {
			return new Recipe(F, F, T, ST.array(ST.amount(1, aInputs[0])), ST.array(ST.book("Manual_Microwave")), null, null, null, null, 32, 4, 0);
		}
		
		// Check Container Item of Input since it is around the Input, then the Input itself, then Container Item of Output and last check the Output itself
		for (ItemStack tStack : ST.array(ST.container(aInputs[0], T), aInputs[0], ST.container(tOutput, T), tOutput)) if (ST.valid(tStack)) {
			if (ST.equal(tStack, Blocks.netherrack)
			 || ST.equal(tStack, Blocks.tnt)
			 || ST.equal(tStack, Items.egg)
			 || ST.equal(tStack, Items.firework_charge)
			 || ST.equal(tStack, Items.fireworks)
			 || ST.equal(tStack, Items.fire_charge)
			) {
				if (aTileEntity instanceof TileEntityBase01Root) ((TileEntityBase01Root)aTileEntity).overcharge(aSize * 4, TD.Energy.EU);
				return null;
			}
			
			OreDictItemData tData = OM.anydata_(tStack);
			
			if (tData != null) {
				if (tData.mMaterial != null) {
					if (tData.mMaterial.mMaterial.containsAny(TD.Atomic.METAL, TD.Properties.EXPLOSIVE)) {
						if (aTileEntity instanceof TileEntityBase01Root) ((TileEntityBase01Root)aTileEntity).overcharge(aSize * 4, TD.Energy.EU);
						return null;
					}
					if (tData.mMaterial.mMaterial.contains(TD.Properties.FLAMMABLE)) {
						if (aTileEntity instanceof TileEntityBase01Root) ((TileEntityBase01Root)aTileEntity).setOnFire();
						return null;
					}
				}
				for (OreDictMaterialStack tMaterial : tData.mByProducts) if (tMaterial != null) {
					if (tMaterial.mMaterial.containsAny(TD.Atomic.METAL, TD.Properties.EXPLOSIVE)) {
						if (aTileEntity instanceof TileEntityBase01Root) ((TileEntityBase01Root)aTileEntity).overcharge(aSize * 4, TD.Energy.EU);
						return null;
					}
					if (tMaterial.mMaterial.contains(TD.Properties.FLAMMABLE)) {
						if (aTileEntity instanceof TileEntityBase01Root) ((TileEntityBase01Root)aTileEntity).setOnFire();
						return null;
					}
				}
			}
			if (ST.fuel(tStack) > 0) {
				if (aTileEntity instanceof TileEntityBase01Root) ((TileEntityBase01Root)aTileEntity).setOnFire();
				return null;
			}
			
		}
		
		return tOutput == null ? null : new Recipe(F, F, T, ST.array(ST.amount(1, aInputs[0])), ST.array(tOutput), null, null, null, null, 32, 4, 0);
	}
	
	@Override public boolean containsInput(ItemStack aStack, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return RM.get_smelting(aStack) != null;}
}
