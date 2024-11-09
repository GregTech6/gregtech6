/**
 * Copyright (c) 2024 GregTech-6 Team
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

import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;

import java.util.Collection;

import static gregapi.data.CS.F;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapCutter extends RecipeMap {
	public RecipeMapCutter(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower, boolean aUseBucketSizeIn, boolean aUseBucketSizeOut) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, F, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, aUseBucketSizeIn, aUseBucketSizeOut);
	}
	/*
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		
		if (rRecipe != null || aInputs == null || aInputs.length <= 0 || aInputs[0] == null || aFluids == null || aFluids.length <= 0 || aFluids[0] == null || !API.mFinishedServerStarted) return rRecipe;
		
		if (IL.FR_Logs.equal(aInputs[0], T, T)) {
			if (UT.Fluids.equal(MT.Lubricant.liquid(U, T), aFluids[0])) rRecipe = new Recipe(F, ST.array(UT.Stacks.amount_(1, aInputs[0])}, ST.array(UT.Stacks.create(IL.FR_Planks.getWithDamage(              6, UT.Stacks.meta(aInputs[0])), aInputs[0].getTagCompound()), dust.mat(MT.Wood, 1)}, null, null, FL.array(MT.Lubricant.liquid(U/200, T)}, ZL_FLUIDSTACK, 128, 16, 0); else
			if (UT.Fluids.equal(MT.Water.liquid(U, T), aFluids[0]))     rRecipe = new Recipe(F, ST.array(UT.Stacks.amount_(1, aInputs[0])}, ST.array(UT.Stacks.create(IL.FR_Planks.getWithDamage(NERFED_WOOD?4:5, UT.Stacks.meta(aInputs[0])), aInputs[0].getTagCompound()), dust.mat(MT.Wood, 2)}, null, null, FL.array(MT.Water    .liquid(U/ 50, T)}, ZL_FLUIDSTACK, 256, 16, 0);
		} else
		if (IL.FR_Logs_Fireproof.equal(aInputs[0], T, T)) {
			if (UT.Fluids.equal(MT.Lubricant.liquid(U, T), aFluids[0])) rRecipe = new Recipe(F, ST.array(UT.Stacks.amount_(1, aInputs[0])}, ST.array(UT.Stacks.create(IL.FR_Planks_Fireproof.getWithDamage(              6, UT.Stacks.meta(aInputs[0])), aInputs[0].getTagCompound()), dust.mat(MT.Wood, 1)}, null, null, FL.array(MT.Lubricant.liquid(U/200, T)}, ZL_FLUIDSTACK, 128, 16, 0); else
			if (UT.Fluids.equal(MT.Water.liquid(U, T), aFluids[0]))     rRecipe = new Recipe(F, ST.array(UT.Stacks.amount_(1, aInputs[0])}, ST.array(UT.Stacks.create(IL.FR_Planks_Fireproof.getWithDamage(NERFED_WOOD?4:5, UT.Stacks.meta(aInputs[0])), aInputs[0].getTagCompound()), dust.mat(MT.Wood, 2)}, null, null, FL.array(MT.Water    .liquid(U/ 50, T)}, ZL_FLUIDSTACK, 256, 16, 0);
		} else
		if (IL.FR_Planks.equal(aInputs[0], T, T)) {
			if (UT.Fluids.equal(MT.Lubricant.liquid(U, T), aFluids[0])) rRecipe = new Recipe(F, ST.array(UT.Stacks.amount_(1, aInputs[0])}, ST.array(UT.Stacks.create(IL.FR_Slabs.getWithDamage(2, UT.Stacks.meta(aInputs[0])), aInputs[0].getTagCompound())}, null, null, FL.array(MT.Lubricant.liquid(U/1000, T)}, ZL_FLUIDSTACK,  24, 16, 0); else
			if (UT.Fluids.equal(MT.Water.liquid(U, T), aFluids[0]))     rRecipe = new Recipe(F, ST.array(UT.Stacks.amount_(1, aInputs[0])}, ST.array(UT.Stacks.create(IL.FR_Slabs.getWithDamage(2, UT.Stacks.meta(aInputs[0])), aInputs[0].getTagCompound())}, null, null, FL.array(MT.Water    .liquid(U/ 100, T)}, ZL_FLUIDSTACK,  96, 16, 0);
		} else
		if (IL.FR_Planks_Fireproof.equal(aInputs[0], T, T)) {
			if (UT.Fluids.equal(MT.Lubricant.liquid(U, T), aFluids[0])) rRecipe = new Recipe(F, ST.array(UT.Stacks.amount_(1, aInputs[0])}, ST.array(UT.Stacks.create(IL.FR_Slabs_Fireproof.getWithDamage(2, UT.Stacks.meta(aInputs[0])), aInputs[0].getTagCompound())}, null, null, FL.array(MT.Lubricant.liquid(U/1000, T)}, ZL_FLUIDSTACK,  24, 16, 0); else
			if (UT.Fluids.equal(MT.Water.liquid(U, T), aFluids[0]))     rRecipe = new Recipe(F, ST.array(UT.Stacks.amount_(1, aInputs[0])}, ST.array(UT.Stacks.create(IL.FR_Slabs_Fireproof.getWithDamage(2, UT.Stacks.meta(aInputs[0])), aInputs[0].getTagCompound())}, null, null, FL.array(MT.Water    .liquid(U/ 100, T)}, ZL_FLUIDSTACK,  96, 16, 0);
		}
		
		if (rRecipe != null) rRecipe.mCanBeBuffered = F;
		
		return rRecipe;
	}
	
	@Override public boolean containsInput(ItemStack aStack) {return super.containsInput(aStack) || IL.FR_Logs.equal(aStack, T, T) || IL.FR_Logs_Fireproof.equal(aStack, T, T) || IL.FR_Planks.equal(aStack, T, T) || IL.FR_Planks_Fireproof.equal(aStack, T, T);}
*/  }
