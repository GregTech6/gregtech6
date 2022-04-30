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

package gregtech.loaders.c;

import blusunrize.immersiveengineering.api.crafting.ArcFurnaceRecipe;
import blusunrize.immersiveengineering.api.crafting.CrusherRecipe;
import forestry.api.recipes.ICentrifugeRecipe;
import forestry.api.recipes.ISqueezerRecipe;
import forestry.api.recipes.RecipeManagers;
import gregapi.data.*;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;

import java.util.Map;

import static gregapi.data.CS.*;

public class Loader_Recipes_Foreign implements Runnable {
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
		if (MD.IE.mLoaded) {
			try {
				// A lot of Recipes that cause problems are in there...
				ArcFurnaceRecipe.recipeList.clear();
				// Adding some Recipes to it, so it is not completely useless.
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Steel           , 1), OP.ingot.dat(MT.Fe           ).toString(), IL.IE_Slag.get(1), 400, 512, OP.dust.dat(ANY.Coal).toString());
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Steel           , 1), OP.dust .dat(MT.Fe           ).toString(), IL.IE_Slag.get(1), 400, 512, OP.dust.dat(ANY.Coal).toString());
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Steel           , 1), OP.ingot.dat(MT.WroughtIron  ).toString(), IL.IE_Slag.get(1), 400, 512, OP.dust.dat(ANY.Coal).toString());
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Steel           , 1), OP.dust .dat(MT.WroughtIron  ).toString(), IL.IE_Slag.get(1), 400, 512, OP.dust.dat(ANY.Coal).toString());
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.MeteoricSteel   , 1), OP.ingot.dat(MT.MeteoricIron ).toString(), IL.IE_Slag.get(1), 400, 512, OP.dust.dat(ANY.Coal).toString());
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.MeteoricSteel   , 1), OP.dust .dat(MT.MeteoricIron ).toString(), IL.IE_Slag.get(1), 400, 512, OP.dust.dat(ANY.Coal).toString());
				// Adding some Alloying Recipes too.
				ArcFurnaceRecipe.addRecipe(OP.chunkGt.mat(MT.Netherite     , 1), OP.ingot.dat(MT.AncientDebris).toString(), NI, 400, 512, OP.dust .dat(MT.Au           ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.chunkGt.mat(MT.Netherite     , 1), OP.dust .dat(MT.AncientDebris).toString(), NI, 400, 512, OP.dust .dat(MT.Au           ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.chunkGt.mat(MT.Netherite     , 1), OP.ingot.dat(MT.Au           ).toString(), NI, 400, 512, OP.dust .dat(MT.AncientDebris).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.chunkGt.mat(MT.Netherite     , 1), OP.dust .dat(MT.Au           ).toString(), NI, 400, 512, OP.dust .dat(MT.AncientDebris).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Constantan      , 2), OP.ingot.dat(ANY.Cu          ).toString(), NI, 100, 512, OP.dust .dat(MT.Ni           ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Constantan      , 2), OP.dust .dat(ANY.Cu          ).toString(), NI, 100, 512, OP.dust .dat(MT.Ni           ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Constantan      , 2), OP.ingot.dat(MT.Ni           ).toString(), NI, 100, 512, OP.dust .dat(ANY.Cu          ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Constantan      , 2), OP.dust .dat(MT.Ni           ).toString(), NI, 100, 512, OP.dust .dat(ANY.Cu          ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Electrum        , 2), OP.ingot.dat(MT.Au           ).toString(), NI, 100, 512, OP.dust .dat(MT.Ag           ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Electrum        , 2), OP.dust .dat(MT.Au           ).toString(), NI, 100, 512, OP.dust .dat(MT.Ag           ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Electrum        , 2), OP.ingot.dat(MT.Ag           ).toString(), NI, 100, 512, OP.dust .dat(MT.Au           ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Electrum        , 2), OP.dust .dat(MT.Ag           ).toString(), NI, 100, 512, OP.dust .dat(MT.Au           ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.TinAlloy        , 2), OP.ingot.dat(ANY.Fe          ).toString(), NI, 200, 512, OP.dust .dat(MT.Sn           ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.TinAlloy        , 2), OP.dust .dat(ANY.Fe          ).toString(), NI, 200, 512, OP.dust .dat(MT.Sn           ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.TinAlloy        , 2), OP.ingot.dat(MT.Sn           ).toString(), NI, 200, 512, OP.dust .dat(ANY.Fe          ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.TinAlloy        , 2), OP.dust .dat(MT.Sn           ).toString(), NI, 200, 512, OP.dust .dat(ANY.Fe          ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.PurpleAlloy     , 1), OP.ingot.dat(MT.RedAlloy     ).toString(), NI, 100, 512, OP.dust .dat(MT.BlueAlloy    ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.PurpleAlloy     , 1), OP.dust .dat(MT.RedAlloy     ).toString(), NI, 100, 512, OP.dust .dat(MT.BlueAlloy    ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.PurpleAlloy     , 1), OP.ingot.dat(MT.BlueAlloy    ).toString(), NI, 100, 512, OP.dust .dat(MT.RedAlloy     ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.PurpleAlloy     , 1), OP.dust .dat(MT.BlueAlloy    ).toString(), NI, 100, 512, OP.dust .dat(MT.RedAlloy     ).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Invar           , 3), OP.ingot.dat(MT.Ni           ).toString(), NI, 200, 512, OP.dust .dat(ANY.Fe          ).toString(), OP.dust .dat(ANY.Fe).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Invar           , 3), OP.dust .dat(MT.Ni           ).toString(), NI, 200, 512, OP.dust .dat(ANY.Fe          ).toString(), OP.dust .dat(ANY.Fe).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Bronze          , 4), OP.ingot.dat(MT.Sn           ).toString(), NI, 200, 512, OP.dust .dat(ANY.Cu          ).toString(), OP.dust .dat(ANY.Cu).toString(), OP.dust .dat(ANY.Cu).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Bronze          , 4), OP.dust .dat(MT.Sn           ).toString(), NI, 200, 512, OP.dust .dat(ANY.Cu          ).toString(), OP.dust .dat(ANY.Cu).toString(), OP.dust .dat(ANY.Cu).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Brass           , 4), OP.ingot.dat(MT.Zn           ).toString(), NI, 200, 512, OP.dust .dat(ANY.Cu          ).toString(), OP.dust .dat(ANY.Cu).toString(), OP.dust .dat(ANY.Cu).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.Brass           , 4), OP.dust .dat(MT.Zn           ).toString(), NI, 200, 512, OP.dust .dat(ANY.Cu          ).toString(), OP.dust .dat(ANY.Cu).toString(), OP.dust .dat(ANY.Cu).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.RedAlloy        , 1), OP.ingot.dat(ANY.Cu          ).toString(), NI, 100, 512, OP.dust .dat(MT.Redstone     ).toString(), OP.dust .dat(MT.Redstone).toString(), OP.dust .dat(MT.Redstone).toString(), OP.dust .dat(MT.Redstone).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.RedAlloy        , 1), OP.dust .dat(ANY.Cu          ).toString(), NI, 100, 512, OP.dust .dat(MT.Redstone     ).toString(), OP.dust .dat(MT.Redstone).toString(), OP.dust .dat(MT.Redstone).toString(), OP.dust .dat(MT.Redstone).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.BlueAlloy       , 1), OP.ingot.dat(MT.Ag           ).toString(), NI, 100, 512, OP.dust .dat(MT.Nikolite     ).toString(), OP.dust .dat(MT.Nikolite).toString(), OP.dust .dat(MT.Nikolite).toString(), OP.dust .dat(MT.Nikolite).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.BlueAlloy       , 1), OP.dust .dat(MT.Ag           ).toString(), NI, 100, 512, OP.dust .dat(MT.Nikolite     ).toString(), OP.dust .dat(MT.Nikolite).toString(), OP.dust .dat(MT.Nikolite).toString(), OP.dust .dat(MT.Nikolite).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.ElectrotineAlloy, 1), OP.ingot.dat(ANY.Fe          ).toString(), NI, 100, 512, OP.dust .dat(MT.Nikolite     ).toString(), OP.dust .dat(MT.Nikolite).toString(), OP.dust .dat(MT.Nikolite).toString(), OP.dust .dat(MT.Nikolite).toString()).setSpecialRecipeType("Alloying");
				ArcFurnaceRecipe.addRecipe(OP.ingot.mat(MT.ElectrotineAlloy, 1), OP.dust .dat(ANY.Fe          ).toString(), NI, 100, 512, OP.dust .dat(MT.Nikolite     ).toString(), OP.dust .dat(MT.Nikolite).toString(), OP.dust .dat(MT.Nikolite).toString(), OP.dust .dat(MT.Nikolite).toString()).setSpecialRecipeType("Alloying");
			} catch(Throwable e) {e.printStackTrace(ERR);}
			
			try {
				// Ores don't always crush into what they are supposed to result in, also somehow Ingots get crushed into multiple Gems sometimes, such as Coal.
				CrusherRecipe.recipeList.clear();
				// Adding some Recipes to it, so it is not completely useless.
				CrusherRecipe.addRecipe(ST.make(Blocks.gravel     , 1, 0), ST.make(Blocks.stone              , 1, 0), 1600);
				CrusherRecipe.addRecipe(ST.make(Blocks.sand       , 1, 0), ST.make(Blocks.cobblestone        , 1, 0), 1600);
				CrusherRecipe.addRecipe(ST.make(Items.flint       , 2, 0), ST.make(Blocks.gravel             , 1, 0), 1600);
				CrusherRecipe.addRecipe(OP.dust     .mat(MT.Glowstone, 4), ST.make(Blocks.glowstone          , 1, 0), 3200);
				CrusherRecipe.addRecipe(IL.Dye_Bonemeal           .get(4), ST.make(Items.bone                , 1, 0), 3200);
				CrusherRecipe.addRecipe(OP.blockDust.mat(MT.Glass    , 1), OD.blockGlass                 .toString(), 6400);
				CrusherRecipe.addRecipe(OP.dust     .mat(MT.Glass    , 1), OD.paneGlass                  .toString(), 1600);
				CrusherRecipe.addRecipe(OP.blockDust.mat(MT.Obsidian , 1), OP.blockSolid.dat(MT.Obsidian).toString(),14400);
				for (OreDictMaterial tMat : ANY.Coal.mToThis) {
				CrusherRecipe.addRecipe(OP.dust     .mat(tMat        , 1), OP.gem       .dat(tMat       ).toString(), 2400);
				CrusherRecipe.addRecipe(OP.dust     .mat(tMat        , 1), OP.ingot     .dat(tMat       ).toString(), 2400);
				}
				for (OreDictMaterial tMat : ANY.Blaze.mToThis) {
				CrusherRecipe.addRecipe(OP.dustTiny .mat(tMat        , 4), OP.stick     .dat(tMat       ).toString(), 3200);
				}
				for (int i = 0; i < 16; i++) {
				CrusherRecipe.addRecipe(ST.make(Items.string      , 4, 0), ST.make(Blocks.wool, 1, i)               , 3200);
				}
				for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Fe, MT.Au, MT.Ag, MT.Cu, MT.Sn, MT.Zn, MT.Ni, MT.Co, MT.Ardite, MT.Pt, MT.Pb, MT.Ge, MT.Os, MT.Ir, MT.W, MT.Al, MT.Ti, MT.Cr, MT.Mn, MT.Steel, MT.Bronze, MT.Brass, MT.Electrum, MT.Constantan, MT.TinAlloy, MT.Invar, MT.MeteoricIron, MT.MeteoricSteel, MT.Desh, MT.BlueAlloy, MT.ElectrotineAlloy, MT.RedAlloy, MT.PurpleAlloy, MT.AncientDebris, MT.Netherite}) {
				CrusherRecipe.addRecipe(OP.dust.mat(tMat             , 1), OP.ingot     .dat(tMat       ).toString(), 6400);
				}
			} catch(Throwable e) {e.printStackTrace(ERR);}
		}
	}
}
