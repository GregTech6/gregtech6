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

import gregapi.data.FL;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapFurnaceFuel extends RecipeMap {
	public RecipeMapFurnaceFuel(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower, boolean aUseBucketSizeIn, boolean aUseBucketSizeOut) {
		super(aRecipeList, aUnlocalizedName, aNameLocal + " (1 Smelt = 5000 GU)", aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, aUseBucketSizeIn, aUseBucketSizeOut);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (aInputs == null || aInputs.length < 1 || ST.invalid(aInputs[0]) || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		if (rRecipe == null) {
			long tFuelValue = ST.fuel(aInputs[0]);
			OreDictItemData tData = OM.anydata_(aInputs[0]);
			if (tFuelValue > 0 && !FL.contains(aInputs[0], FL.Lava.make(0), T) && !OM.materialcontains(tData, TD.Properties.EXPLOSIVE)) {
				ItemStack tContainer = ST.container(aInputs[0], T);
				if (tContainer == null) {
					OreDictMaterialStack tMaterial = null;
					if (tData != null) {
						if (tData.mPrefix == OP.oreRaw) {
							tMaterial = OM.stack(tData.mMaterial.mMaterial.mTargetBurning.mMaterial, tData.mMaterial.mMaterial.mTargetBurning.mAmount * tData.mMaterial.mMaterial.mOreMultiplier * tData.mMaterial.mMaterial.mOreProcessingMultiplier * 2);
						} else if (tData.mPrefix == OP.blockRaw) {
							tMaterial = OM.stack(tData.mMaterial.mMaterial.mTargetBurning.mMaterial, tData.mMaterial.mMaterial.mTargetBurning.mAmount * tData.mMaterial.mMaterial.mOreMultiplier * tData.mMaterial.mMaterial.mOreProcessingMultiplier * 20);
						} else for (OreDictMaterialStack aMaterial : tData.getAllMaterialStacks()) {
							if (tMaterial == null || tMaterial.mAmount <= 0) {
								tMaterial = OM.stack(aMaterial.mMaterial.mTargetBurning.mMaterial, UT.Code.units(aMaterial.mAmount, U, aMaterial.mMaterial.mTargetBurning.mAmount, F));
							} else if (tMaterial.mMaterial == aMaterial.mMaterial.mTargetBurning.mMaterial) {
								tMaterial.mAmount += UT.Code.units(aMaterial.mAmount, U, aMaterial.mMaterial.mTargetBurning.mAmount, F);
							}
						}
					}
					
					if (tMaterial == null || tMaterial.mAmount <= 0) {
						rRecipe = new Recipe(F, F, T, ST.array(ST.amount(1, aInputs[0])), null, null, null, null, null, tFuelValue * EU_PER_FURNACE_TICK, -1, 0);
					} else {
						for (int i = 1; i < 64; i++) {
							ItemStack tAshes = OM.dust(tMaterial.mMaterial, tMaterial.mAmount * i);
							if (ST.valid(tAshes)) {
								rRecipe = new Recipe(F, F, T, ST.array(ST.amount(i, aInputs[0])), ST.array(tAshes), null, null, null, null, tFuelValue * EU_PER_FURNACE_TICK, -1, 0);
								break;
							}
						}
					}
				} else {
					rRecipe = new Recipe(F, F, T, ST.array(ST.amount(1, aInputs[0])), ST.array(tContainer), null, null, null, null, tFuelValue * EU_PER_FURNACE_TICK, -1, 0);
				}
				rRecipe.mCanBeBuffered = (aInputs[0].getTagCompound() == null);
				if (rRecipe.mCanBeBuffered) addRecipe(rRecipe, F, F, T);
			}
		}
		return rRecipe;
	}
	
	@Override public boolean containsInput(ItemStack aStack, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return null != findRecipe(null, null, T, Long.MAX_VALUE, null, ZL_FS, aStack);}
}
