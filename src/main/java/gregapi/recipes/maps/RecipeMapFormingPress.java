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

import gregapi.data.IL;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import java.util.Collection;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapFormingPress extends RecipeMap {
	public RecipeMapFormingPress(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower, boolean aUseBucketSizeIn, boolean aUseBucketSizeOut) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, F, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, aUseBucketSizeIn, aUseBucketSizeOut);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (aInputs == null || aInputs.length < 2 || aInputs[0] == null || aInputs[1] == null || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		if (rRecipe == null) {
			if (IL.Shape_Mold_Name.equal(aInputs[0], F, T)) {
				ItemStack tOutput = ST.amount(1, aInputs[1]);
				tOutput.setStackDisplayName(aInputs[0].getDisplayName());
				return new Recipe(F, F, F, ST.array(IL.Shape_Mold_Name.get(0), ST.amount(1, aInputs[1])), ST.array(tOutput), null, null, null, null, 128, 8, 0);
			}
			if (IL.Shape_Mold_Name.equal(aInputs[1], F, T)) {
				ItemStack tOutput = ST.amount(1, aInputs[0]);
				tOutput.setStackDisplayName(aInputs[1].getDisplayName());
				return new Recipe(F, F, F, ST.array(IL.Shape_Mold_Name.get(0), ST.amount(1, aInputs[0])), ST.array(tOutput), null, null, null, null, 128, 8, 0);
			}
			return null;
		}
		for (ItemStack aMold : aInputs) {
			if (IL.Shape_Mold_Credit.equal(aMold, F, T)) {
				NBTTagCompound tNBT = aMold.getTagCompound();
				if (tNBT == null) tNBT = UT.NBT.make();
				if (!tNBT.hasKey("credit_security_id")) UT.NBT.setNumber(tNBT, "credit_security_id", System.nanoTime());
				UT.NBT.set(aMold, tNBT);
				
				rRecipe = rRecipe.copy();
				rRecipe.mCanBeBuffered = F;
				UT.NBT.set(rRecipe.mOutputs[0], tNBT);
				return rRecipe;
			}
		}
		return rRecipe;
	}
}
