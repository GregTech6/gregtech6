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

package gregtech.loaders.c;

import static gregapi.data.CS.*;

import java.util.Map;

import forestry.api.recipes.ICentrifugeRecipe;
import forestry.api.recipes.ISqueezerRecipe;
import forestry.api.recipes.RecipeManagers;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.RM;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

public class Loader_Recipes_Copy implements Runnable {
	@Override public void run() {
		for (Map<String, FluidContainerData> tMap : FL.EMPTY_TO_FLUID_TO_DATA.values()) for (FluidContainerData tData : tMap.values()) {
			ItemStack tEmpty = (tData.emptyContainer.getItem() == Items.bucket || tData.emptyContainer.stackSize < 1 ? ST.container(tData.filledContainer, F) : tData.emptyContainer);
			if (ST.valid(tEmpty)) RM.Canner.addRecipe1(T, 16, Math.max(tData.fluid.amount / 64, 16), tEmpty, tData.fluid, NF, tData.filledContainer);
		}
		for (FluidContainerData tData : FL.FULL_TO_DATA.values()) {
			RM.Canner.addRecipe1(T, 16, Math.max(tData.fluid.amount / 64, 16), tData.filledContainer, NF, tData.fluid, ST.container(tData.filledContainer, T));
			if (MD.FR.mLoaded) {
			if (IL.FR_TinCapsule       .equal(tData.emptyContainer)) RM.Squeezer.addRecipe1(T, 16, Math.max(tData.fluid.amount / 64, 16),  500, tData.filledContainer, NF, tData.fluid, OM.ingot(MT.Sn));
			if (IL.FR_WaxCapsule       .equal(tData.emptyContainer)) RM.Squeezer.addRecipe1(T, 16, Math.max(tData.fluid.amount / 64, 16), 1000, tData.filledContainer, NF, tData.fluid, OM.dust(MT.WaxBee));
			if (IL.FR_RefractoryCapsule.equal(tData.emptyContainer)) RM.Squeezer.addRecipe1(T, 16, Math.max(tData.fluid.amount / 64, 16), 1000, tData.filledContainer, NF, tData.fluid, OM.dust(MT.WaxRefractory));
			}
		}
		
		if (MD.FR.mLoaded) {
			OUT.println("GT_Mod: Copying all of the Forestry Centrifuge/Squeezer Recipes to GT Machines");
			try {
				for (ICentrifugeRecipe tRecipe : RecipeManagers.centrifugeManager.recipes()) {
					Map<ItemStack, Float> tMap = tRecipe.getAllProducts();
					ItemStack[] tOutput = new ItemStack[tMap.size()];
					if (tOutput.length > 0) {
						int i = 0;
						long[] tChances = new long[tOutput.length];
						for (Map.Entry<ItemStack, Float> tEntry : tMap.entrySet()) {
							tOutput[i] = tEntry.getKey();
							tChances[i++] = Math.max(1, (long)(10000*tEntry.getValue()));
						}
						RM.Centrifuge.addRecipe(T, ST.array(tRecipe.getInput()), tOutput, NI, tChances, null, null, tRecipe.getProcessingTime(), 16, 0);
					}
				}
			} catch(Throwable e) {e.printStackTrace(ERR);}
			try {
				for (ISqueezerRecipe tRecipe : RecipeManagers.squeezerManager.recipes()) {
					ItemStack[] tInput = tRecipe.getResources();
					if (tInput.length == 1 && FL.getFluid(tInput[0], T) == null) {
						RM.Squeezer.addRecipe(T, tInput, ST.array(tRecipe.getRemnants()), NI, new long[] {Math.max(1, (long)(10000*tRecipe.getRemnantsChance()))}, null, FL.array(tRecipe.getFluidOutput()), tRecipe.getProcessingTime(), 16, 0);
					}
				}
			} catch(Throwable e) {e.printStackTrace(ERR);}
		}
	}
}
