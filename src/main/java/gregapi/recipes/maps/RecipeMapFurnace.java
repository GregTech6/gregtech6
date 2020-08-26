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

package gregapi.recipes.maps;

import static gregapi.data.CS.*;

import java.util.Collection;

import gregapi.data.FL;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.oredict.OreDictItemData;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapFurnace extends RecipeMapNonGTRecipes {
	public RecipeMapFurnace(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		if (aInputs == null || aInputs.length <= 0 || aInputs[0] == null) return null;
		if (aRecipe != null && aRecipe.isRecipeInputEqual(F, T, aFluids, aInputs)) return aRecipe;
		ItemStack tOutput = RM.get_smelting(aInputs[0], F, null);
		if (tOutput == null) return null;
		if (FL.XP.exists()) {
			FluidStack tFluid = null;
			if (tOutput.getItem() == Items.brick || tOutput.getItem() == Items.netherbrick || tOutput.getItem() == Items.dye) {
				tFluid = FL.XP.make(tOutput.stackSize);
			} else if (tOutput.getItem() == Items.coal) {
				tFluid = FL.XP.make(tOutput.stackSize * 2);
			} else {
				Block tBlock = ST.block(tOutput);
				if (tBlock == Blocks.glass) {
					tFluid = FL.XP.make(tOutput.stackSize);
				} else if (tBlock == Blocks.brick_block || tBlock == Blocks.nether_brick) {
					tFluid = FL.XP.make(tOutput.stackSize * 3);
				} else {
					OreDictItemData tData = OM.anydata_(aInputs[0]);
					if (tData != null && tData.hasValidPrefixMaterialData() && tData.mPrefix.containsAny(TD.Prefix.ORE, TD.Prefix.ORE_PROCESSING_BASED)) {
						tData = OM.anydata_(tOutput);
						if (tData != null && tData.hasValidPrefixMaterialData()) {
							long tXP = tOutput.stackSize * (3+tData.mMaterial.mMaterial.mToolQuality);
							if (tData.mMaterial.mMaterial.contains(TD.Properties.VALUABLE)) tXP *= 2;
							if (tData.mPrefix.mAmount > 0) {
								tFluid = FL.XP.make(UT.Code.divup(tData.mPrefix.mAmount * tXP, U));
							} else {
								tFluid = FL.XP.make(tXP);
							}
						} else {
							tFluid = FL.XP.make(5);
						}
					} else {
						tFluid = FL.XP.make(UT.Code.roundUp(tOutput.stackSize * 20 * FurnaceRecipes.smelting().func_151398_b(tOutput)));
					}
				}
			}
			if (tFluid != null && tFluid.amount > 0) {
				return new Recipe(F, F, T, ST.array(ST.amount(1, aInputs[0])), ST.array(tOutput), null, null, ZL_FS, new FluidStack[] {tFluid}, 16, 16, 0);
			}
		}
		return new Recipe(F, F, T, ST.array(ST.amount(1, aInputs[0])), ST.array(tOutput), null, null, ZL_FS, ZL_FS, 16, 16, 0);
	}
	
	@Override public boolean containsInput(ItemStack aStack, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return RM.get_smelting(aStack, F, null) != null;}
}
