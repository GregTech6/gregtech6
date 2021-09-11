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

package gregapi.recipes.maps;

import static gregapi.data.CS.*;

import java.util.Collection;

import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IIndividual;
import gregapi.data.CS.FluidsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.bumble.IItemBumbleBee;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapBumblelyzer extends RecipeMap {
	public RecipeMapBumblelyzer(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower, boolean aUseBucketSizeIn, boolean aUseBucketSizeOut) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, aUseBucketSizeIn, aUseBucketSizeOut);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (aInputs == null || aFluids == null || aFluids.length < 1 || aFluids[0] == null || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		if (rRecipe == null) {
			if (FluidsGT.HONEY.contains(aFluids[0].getFluid().getName()) || FL.Honeydew.is(aFluids[0])) {
				for (ItemStack aInput : aInputs) if (ST.valid(aInput)) {
					aInput = ST.amount(1, aInput);
					if (aInput.getItem() instanceof IItemBumbleBee) {
						if (((IItemBumbleBee)aInput.getItem()).bumbleType(aInput) < 5)
						return new Recipe(F, F, F, ST.array(aInput, OP.plateTiny.mat(MT.Paper, aInput.stackSize)), ST.array(((IItemBumbleBee)aInput.getItem()).bumbleScan(aInput)), null, null, FL.array(FL.amount(aFluids[0], 10)), null, 64, 16, 0);
						return new Recipe(F, F, F, ST.array(aInput), ST.array(aInput), null, null, null, null, 1, 16, 0);
					}
					if (IL.FR_Bee_Drone.equal(aInput, T, T) || IL.FR_Bee_Princess.equal(aInput, T, T) || IL.FR_Bee_Queen.equal(aInput, T, T)) try {
						Object tIndividual = AlleleManager.alleleRegistry.getIndividual(aInput);
						if (tIndividual == null || !((IIndividual)tIndividual).analyze()) return new Recipe(F, F, F, ST.array(aInput), ST.array(aInput), null, null, null, null, 1, 16, 0);
						ItemStack rOutput = ST.copy(aInput);
						((IIndividual)tIndividual).writeToNBT(UT.NBT.getOrCreate(rOutput));
						return new Recipe(F, F, F, ST.array(aInput), ST.array(rOutput), null, null, FL.array(FL.amount(aFluids[0], 50)), null, 64, 16, 0);
					} catch(Throwable e) {e.printStackTrace(ERR);}
				}
			}
		}
		return rRecipe;
	}
}
