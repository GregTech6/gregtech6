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

package gregtech.loaders.c;

import ganymedes01.etfuturum.recipes.BlastFurnaceRecipes;
import ganymedes01.etfuturum.recipes.SmokerRecipes;
import gregapi.data.*;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import static gregapi.data.CS.*;

public class Loader_Recipes_Furnace implements Runnable {
	public static boolean RUNNING = F;
	
	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void run() {
		Map<ItemStack, ItemStack>[] tMaps = new Map[] {FurnaceRecipes.smelting().getSmeltingList(), null, null};
		if (MD.EtFu.mLoaded) try {
			tMaps[1] = ((Map)UT.Reflection.getFieldContent(SmokerRecipes      .smelting(), "smeltingList", T, D1));
			tMaps[2] = ((Map)UT.Reflection.getFieldContent(BlastFurnaceRecipes.smelting(), "smeltingList", T, D1));
		} catch(Throwable e) {/**/}
		boolean tFurnace = T;
		for (Map<ItemStack, ItemStack> tMap : tMaps) if (tMap != null) {
			Iterator<Entry<ItemStack, ItemStack>> tIterator = tMap.entrySet().iterator();
			while (tIterator.hasNext()) {
				Entry<ItemStack, ItemStack> tEntry = tIterator.next();
				String tReg1 = ST.regName(tEntry.getKey());
				OreDictItemData tData1 = OM.anydata(tEntry.getKey()), tData2 = OM.anydata(tEntry.getValue());
				// Unification of the Smelting Result.
				tEntry.setValue(OM.get(tEntry.getValue()));
				
				// Lots of RotaryCraft balance fixes and more Recipe Compat.
				if (tFurnace && MD.RoC.owns(tReg1, "extracts")) {
					if (tData2 != null && tData2.nonemptyData()) {
						ItemStack tDust = OM.dust(tData2.mMaterial.mMaterial.mTargetCrushing.mMaterial, UT.Code.units(tData2.mMaterial.mAmount * tEntry.getValue().stackSize, U, tData2.mMaterial.mMaterial.mTargetCrushing.mAmount, F));
						if (ST.invalid(tDust) && tDust.stackSize <= 0) tDust = null;
						
						if (tDust == null) {
							// Output the random Items.
							RM.ic2_extractor(tEntry.getKey(), tEntry.getValue());
							RM.Sifting.addRecipe1(F, 16, 200, tEntry.getKey(), tEntry.getValue());
							continue;
						}
						// Making sure that things like Aluminium get a Dust Replacement too.
						if (tData2.mMaterial.mMaterial.mTargetCrushing.mMaterial != tData2.mMaterial.mMaterial) tEntry.setValue(tDust);
						
						// Basic Shredding Recipes.
						RM.pulverizing(tEntry.getKey(), tDust);
						RM.Mortar  .addRecipe1(F, 16,  32, tEntry.getKey(), tDust);
						RM.Shredder.addRecipe1(F, 16,  32, tEntry.getKey(), tDust);
						
						if (tData2.mPrefix.contains(TD.Prefix.DUST_BASED)) {
							RM.Sifting.addRecipe1(F, 16, 200, tEntry.getKey(), tDust);
							continue;
						}
						if (tData2.mPrefix.contains(TD.Prefix.INGOT_BASED)) {
							// Only change the Flake Recipes that output Ingots which do not belong to the Furnace.
							if (!tData2.mMaterial.mMaterial.contains(TD.Processing.FURNACE)) tEntry.setValue(tDust);
							RM.Sifting.addRecipe1(F, 16, 200, tEntry.getKey(), tDust);
							continue;
						}
						// Output Gems and the other random Items.
						RM.ic2_extractor(tEntry.getKey(), tEntry.getValue());
						RM.Sifting.addRecipe1(F, 16, 200, tEntry.getKey(), tEntry.getValue());
						continue;
					}
					// Output unknown Items.
					RM.ic2_extractor(tEntry.getKey(), tEntry.getValue());
					RM.Sifting.addRecipe1(F, 16, 200, tEntry.getKey(), tEntry.getValue());
					continue;
				}
				
				if (tData2 != null && tData2.nonemptyData()) {
					if (tData1 != null && tData1.nonemptyData()) {
						// Just outright remove all Furnace Recipes, that both Input and Output valid OreDicted Stuff at the same time, we add the proper ones below anyways.
						tIterator.remove();
						if (MD.EtFu.mLoaded) try {
							SmokerRecipes      .smelting().smeltingBlacklist.add(tEntry.getKey());
							BlastFurnaceRecipes.smelting().smeltingBlacklist.add(tEntry.getKey());
						} catch(Throwable e) {/**/}
						continue;
					}
					if (MD.HBM.owns(tReg1, "powder") || MD.HBM.owns(tReg1, "tile.ore")) {
						// Older Versions of NTM require this Part.
						tIterator.remove();
						if (MD.EtFu.mLoaded) try {
							SmokerRecipes      .smelting().smeltingBlacklist.add(tEntry.getKey());
							BlastFurnaceRecipes.smelting().smeltingBlacklist.add(tEntry.getKey());
						} catch(Throwable e) {/**/}
						continue;
					}
					if (tData2.mMaterial.mMaterial.contains(TD.Processing.NEVER_FURNACE)) {
						// Unsmelt things that really do not belong in Furnace Recipes.
						tEntry.setValue(OP.scrapGt.mat(tData2.mMaterial, (tData2.mMaterial.mAmount * tEntry.getValue().stackSize) / OP.scrapGt.mAmount));
						if (MD.EtFu.mLoaded) try {
							SmokerRecipes      .smelting().smeltingBlacklist.add(tEntry.getKey());
							BlastFurnaceRecipes.smelting().smeltingBlacklist.add(tEntry.getKey());
						} catch(Throwable e) {/**/}
						continue;
					}
				}
			}
			tFurnace = F;
		}
		
		RUNNING = T;
		OP.scrapGt               .addListener(new Listener_Furnace_Smelting(   -1, F));
		OP.dust                  .addListener(new Listener_Furnace_Smelting(   -1, F));
		OP.dustSmall             .addListener(new Listener_Furnace_Smelting(   -1, F));
		OP.dustTiny              .addListener(new Listener_Furnace_Smelting(   -1, F));
		OP.dustImpure            .addListener(new Listener_Furnace_Smelting(   -1, F));
		OP.dustPure              .addListener(new Listener_Furnace_Smelting(   -1, F));
		OP.dustRefined           .addListener(new Listener_Furnace_Smelting(   -1, F));
		OP.gemChipped            .addListener(new Listener_Furnace_Smelting(   -1, F));
		OP.gemFlawed             .addListener(new Listener_Furnace_Smelting(   -1, F));
		OP.gem                   .addListener(new Listener_Furnace_Smelting(   -1, F));
		OP.gemFlawless           .addListener(new Listener_Furnace_Smelting(   -1, F));
		OP.gemExquisite          .addListener(new Listener_Furnace_Smelting(   -1, F));
		OP.gemLegendary          .addListener(new Listener_Furnace_Smelting(   -1, F));
		OP.rockGt                .addListener(new Listener_Furnace_Smelting(   -1, T));
		OP.rawOreChunk           .addListener(new Listener_Furnace_Smelting(   -1, T));
		OP.oreRaw                .addListener(new Listener_Furnace_Smelting(U    , T));
		OP.chunk                 .addListener(new Listener_Furnace_Smelting(U * 2, T));
		OP.rubble                .addListener(new Listener_Furnace_Smelting(U * 2, T));
		OP.pebbles               .addListener(new Listener_Furnace_Smelting(U * 3, T));
		OP.cluster               .addListener(new Listener_Furnace_Smelting(U * 3, T));
		OP.cleanGravel           .addListener(new Listener_Furnace_Smelting(   -1, T));
		OP.dirtyGravel           .addListener(new Listener_Furnace_Smelting(   -1, T));
		OP.crystalline           .addListener(new Listener_Furnace_Smelting(   -1, T));
		OP.reduced               .addListener(new Listener_Furnace_Smelting(   -1, T));
		OP.crushed               .addListener(new Listener_Furnace_Smelting(   -1, T));
		OP.crushedTiny           .addListener(new Listener_Furnace_Smelting(   -1, T));
		OP.crushedPurified       .addListener(new Listener_Furnace_Smelting(   -1, T));
		OP.crushedPurifiedTiny   .addListener(new Listener_Furnace_Smelting(   -1, T));
		OP.crushedCentrifuged    .addListener(new Listener_Furnace_Smelting(   -1, T));
		OP.crushedCentrifugedTiny.addListener(new Listener_Furnace_Smelting(   -1, T));
		
		for (OreDictMaterial tMat : ANY.Clay.mToThis) {
			RM.add_smelting(OP.dust       .mat(tMat, 1), OP.dust       .mat(MT.Ceramic, 1), F, F, T);
			RM.add_smelting(OP.dustSmall  .mat(tMat, 1), OP.dustSmall  .mat(MT.Ceramic, 1), F, F, T);
			RM.add_smelting(OP.dustTiny   .mat(tMat, 1), OP.dustTiny   .mat(MT.Ceramic, 1), F, F, T);
			RM.add_smelting(OP.dustDiv72  .mat(tMat, 1), OP.dustDiv72  .mat(MT.Ceramic, 1), F, F, T);
			RM.add_smelting(OP.plate      .mat(tMat, 1), OP.plate      .mat(MT.Ceramic, 1), F, F, T);
			RM.add_smelting(OP.plateTiny  .mat(tMat, 1), OP.plateTiny  .mat(MT.Ceramic, 1), F, F, T);
			RM.add_smelting(OP.plateCurved.mat(tMat, 1), OP.plateCurved.mat(MT.Ceramic, 1), F, F, T);
			RM.add_smelting(OP.ingot      .mat(tMat, 1), ST.make(Items.brick, 1, 0)       , F, F, T);
		}
		
		RUNNING = F;
	}
	
	public static class Listener_Furnace_Smelting implements IOreDictListenerEvent {
		private final long mTargetAmount;
		private final boolean mExp;
		
		public Listener_Furnace_Smelting(long aTargetAmount, boolean aExp) {
			mTargetAmount = aTargetAmount;
			mExp = aExp;
		}
		
		@Override
		public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (aEvent.mMaterial.contains(TD.Processing.FURNACE) && !aEvent.mMaterial.contains(TD.Properties.UNUSED_MATERIAL)) {
				long aTargetAmount = UT.Code.units(UT.Code.units(aEvent.mMaterial.mTargetSmelting.mAmount, U, aEvent.mMaterial.mTargetSmelting.mMaterial.mTargetSolidifying.mAmount, F), U, mTargetAmount<0?aEvent.mPrefix.mAmount:mTargetAmount, F);
				if (aEvent.mMaterial.contains(TD.Properties.FOOD)) {
					RM.add_smelting(aEvent.mStack, OM.ingot(aEvent.mMaterial.mTargetSmelting.mMaterial.mTargetSolidifying.mMaterial, aTargetAmount), mExp ? UT.Code.units(aTargetAmount, U, aEvent.mMaterial.mToolQuality+1, T) : 0, !RUNNING, T, F);
				} else {
					RM.add_smelting(aEvent.mStack, OM.ingot(aEvent.mMaterial.mTargetSmelting.mMaterial.mTargetSolidifying.mMaterial, aTargetAmount), mExp ? UT.Code.units(aTargetAmount, U, aEvent.mMaterial.mToolQuality+1, T) : 0, !RUNNING, F, T);
				}
			}
		}
	}
}
