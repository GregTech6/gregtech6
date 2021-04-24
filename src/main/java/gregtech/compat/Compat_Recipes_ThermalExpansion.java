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

package gregtech.compat;

import static gregapi.data.CS.*;
import static gregapi.data.OP.*;
import static gregapi.data.TD.Atomic.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.ANY;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.oredict.event.OreDictListenerEvent_TwoNames;
import gregapi.recipes.handlers.RecipeMapHandlerPrefix;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;

public class Compat_Recipes_ThermalExpansion extends CompatMods {
	public Compat_Recipes_ThermalExpansion(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Thermal Foundation Recipes.");
		CR.remove(IL.TE_Rod_Blizz .get(1));
		CR.remove(IL.TE_Rod_Blitz .get(1));
		CR.remove(IL.TE_Rod_Basalz.get(1));
		CR.delate(MD.TE_FOUNDATION, "material", 512, 513, 514, 515);
		
		RM.Sluice.add(new RecipeMapHandlerPrefix(crushed    , 1, null, 0, MT.Petrotheum.liquid(9*U50, T), 16, 144, 0, NF, crushedPurified    , 1, crushedPurifiedTiny, 9, NI, OM.dust(MT.SluiceSand    ), T, F, T, ANTIMATTER.NOT).chances(10000, 5000, 10000));
		RM.Sluice.add(new RecipeMapHandlerPrefix(crushedTiny, 1, null, 0, MT.Petrotheum.liquid(  U50, T), 16,  16, 0, NF, crushedPurifiedTiny, 1, crushedPurifiedTiny, 1, NI, OM.dust(MT.SluiceSand, U9), T, F, T, ANTIMATTER.NOT).chances(10000, 5000, 10000));
		
		RM.Bath.addRecipe1(T,  0,  128, OP.crushed    .mat(MT.OREMATS.Cinnabar, 1), MT.Cryotheum.liquid(U2, T), NF, OP.gemFlawless.mat(MT.OREMATS.Cinnabar, 1));
		RM.Bath.addRecipe1(T,  0,  128, OP.crushedTiny.mat(MT.OREMATS.Cinnabar, 9), MT.Cryotheum.liquid(U2, T), NF, OP.gemFlawless.mat(MT.OREMATS.Cinnabar, 1));
		
		FluidStack tPyrotheum = MT.Pyrotheum.liquid(U2, T);
		if (!FL.Error.is(tPyrotheum)) {
		ItemStack tStack;
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,  3000}, OP.crushed.mat(MT.Au           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Au           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,  2250}, OP.crushed.mat(MT.Ni           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Ni           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,  1500}, OP.crushed.mat(MT.Co           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Co           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Pb           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Pb           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Fe           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Fe           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Sn           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Sn           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Cu           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Cu           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Pt           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Pt           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Ir           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Ir           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Os           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Os           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Hg           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Hg           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Cr           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Cr           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Mn           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Mn           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Mo           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Mo           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Nd           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Nd           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Ag           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Ag           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Bi           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Bi           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Zn           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Zn           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Al           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Al           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Cd           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Cd           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Pd           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Pd           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.In           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.In           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Si           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Si           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Ti           , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Ti           , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.W            , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.W            , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.FakeOsmium   , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.FakeOsmium   , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.AncientDebris, 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.AncientDebris, 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Electrum     , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Electrum     , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.AstralSilver , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.AstralSilver , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Midasium     , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Midasium     , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Mithril      , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Mithril      , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Cheese       , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Cheese       , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Ardite       , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Ardite       , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		RM.Bath             .addRecipe1(T,  0,   16, new long[] {10000,   750}, OP.crushed.mat(MT.Desh         , 1), tPyrotheum, NF, tStack = OP.chunkGt.mat(MT.Desh         , 1), tStack, tStack, tStack, tStack, IL.TE_Slag_Rich.get(1));
		}
		
		FL.set(new FluidContainerData(FL.Redstone.make(1440), ST.make(MD.TE_FOUNDATION, "bucket", 1, 0), ST.make(Items.bucket, 1, 0), F), F, F);
		FL.set(new FluidContainerData(FL.Ender   .make( 576), ST.make(MD.TE_FOUNDATION, "bucket", 1, 2), ST.make(Items.bucket, 1, 0), F), F, F);
		
		for (FluidStack tRedstone : FL.array(FL.Redstone.make(L), FL.Redstone_TE.make(100))) {
			RM.Injector     .addRecipe1(T, 16, 16, ST.make(Items.snowball, 1, W), FL.mul(tRedstone, 2), NF, OP.dustTiny.mat(MT.Blizz, 1));
			RM.Injector     .addRecipe1(T, 16, 16, OP.dustSmall.mat(MT.Snow, 1) , FL.mul(tRedstone, 2), NF, OP.dustTiny.mat(MT.Blizz, 1));
			RM.Injector     .addRecipe1(T, 16, 16, OP.dustSmall.mat(MT.Ice, 1)  , FL.mul(tRedstone, 2), NF, OP.dustTiny.mat(MT.Blizz, 1));
			RM.Injector     .addRecipe1(T, 16, 64, ST.make(Blocks.snow, 1, W)   , FL.mul(tRedstone, 8), NF, OP.dustTiny.mat(MT.Blizz, 4));
			RM.Injector     .addRecipe1(T, 16, 64, OP.dust.mat(MT.Snow, 1)      , FL.mul(tRedstone, 8), NF, OP.dustTiny.mat(MT.Blizz, 4));
			RM.Injector     .addRecipe1(T, 16, 64, OP.dust.mat(MT.Ice, 1)       , FL.mul(tRedstone, 8), NF, OP.dustTiny.mat(MT.Blizz, 4));
			for (OreDictMaterial tMat : ANY.SiO2.mToThis)
			RM.Injector     .addRecipe1(T, 16, 16, OM.dust(tMat)                , FL.mul(tRedstone, 2), NF, OP.dustTiny.mat(MT.Blitz, 1));
			RM.Injector     .addRecipe1(T, 16, 16, OP.dust.mat(MT.Stone, 1)     , FL.mul(tRedstone, 2), NF, OP.dustTiny.mat(MT.Blitz, 1));
			RM.Injector     .addRecipe1(T, 16, 16, ST.make(Blocks.sand, 1, W)   , FL.mul(tRedstone, 2), NF, OP.dustTiny.mat(MT.Blitz, 1));
			if (IL.AETHER_Sand.exists())
			RM.Injector     .addRecipe1(T, 16, 16, IL.AETHER_Sand.get(1)        , FL.mul(tRedstone, 2), NF, OP.dustTiny.mat(MT.Blitz, 1));
			
			RM.Injector     .addRecipe1(T, 16, 16, OP.dust.mat(MT.Obsidian, 1)  , FL.mul(tRedstone, 2), NF, OP.dustTiny.mat(MT.Basalz, 1));
		}
		
		if (MD.TE_DYNAMICS.mLoaded) {
			OUT.println("GT_Mod: Doing Thermal Dynamics Recipes.");
			for (FluidStack tEnder : FL.array(FL.Ender.make(L), FL.Ender_TE.make(250))) {
			RM.Injector         .addRecipe1(T, 16,   80, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 0)    , FL.mul(tEnder,  4), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 2));
			RM.Canner           .addRecipe1(T, 16,   80, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 0)    , FL.mul(tEnder,  4), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 2));
			}
			for (FluidStack tRedstone : FL.array(FL.Redstone.make(L), FL.Redstone_TE.make(100))) {
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 5)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 4));
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 3)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 2));
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 0)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 6));
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 1)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 7));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 5)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 4));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 3)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 2));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 0)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 6));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 1)    , FL.mul(tRedstone,  2), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 7));
			}
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 0)    , FL.Glowstone_TE.make(200), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 2));
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 1)    , FL.Glowstone_TE.make(200), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 3));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 0)    , FL.Glowstone_TE.make(200), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 2));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 1)    , FL.Glowstone_TE.make(200), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_32", 1, 3));
			
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 7)    , MT.Cryotheum.liquid(2*U, T), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 6));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 7)    , MT.Cryotheum.liquid(2*U, T), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_0" , 1, 6));
			
			RM.Injector         .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 3)    , MT.Aerotheum.gas(U5, T), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 0));
			RM.Canner           .addRecipe1(T, 16,   16, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 3)    , MT.Aerotheum.gas(U5, T), NF, ST.make(MD.TE_DYNAMICS, "ThermalDynamics_64", 1, 0));
		}
		if (MD.TE.mLoaded) {
			OUT.println("GT_Mod: Doing Thermal Expansion Recipes.");
			
			RM.Mixer            .addRecipe1(T, 16,   16, OP.dust.mat(MT.Obsidian, 2), MT.Pb    .liquid(U2, T), NF, IL.TE_ObsidiGlass.get(1));
			RM.Mixer            .addRecipe1(T, 16,   16, OP.dust.mat(MT.Obsidian, 2), MT.Pb    .liquid(U2, T), NF, IL.TE_ObsidiGlass.get(1));
			RM.Mixer            .addRecipe1(T, 16,   16, OP.dust.mat(MT.Obsidian, 2), MT.Lumium.liquid(U2, T), NF, IL.TE_LumiumGlass.get(1));
			RM.Mixer            .addRecipe1(T, 16,   16, OP.dust.mat(MT.Obsidian, 2), MT.Lumium.liquid(U2, T), NF, IL.TE_LumiumGlass.get(1));
			
			for (FluidStack tEnder : FL.array(FL.Ender.make(L), FL.Ender_TE.make(250))) {
			RM.Injector         .addRecipe1(T, 16,   80, ST.make(MD.TE, "Frame", 1,10)                          , FL.mul(tEnder,  4), NF, ST.make(MD.TE, "Frame", 1,11));
			RM.Injector         .addRecipe1(T, 16,   80, ST.make(MD.TE, "Plate", 1, 0)                          , FL.mul(tEnder,  4), NF, ST.make(MD.TE, "Plate", 1, 3));
			RM.Canner           .addRecipe1(T, 16,   80, ST.make(MD.TE, "Frame", 1,10)                          , FL.mul(tEnder,  4), NF, ST.make(MD.TE, "Frame", 1,11));
			RM.Canner           .addRecipe1(T, 16,   80, ST.make(MD.TE, "Plate", 1, 0)                          , FL.mul(tEnder,  4), NF, ST.make(MD.TE, "Plate", 1, 3));
			}
			for (FluidStack tRedstone : FL.array(FL.Redstone.make(L), FL.Redstone_TE.make(100))) {
			RM.Injector         .addRecipe1(T, 16,   16, OP.spring.mat(MT.Au        , 1)                        , FL.mul(tRedstone,  2), NF, ST.make(MD.TE, "material", 1, 1));
			RM.Injector         .addRecipe1(T, 16,   16, OP.spring.mat(MT.Ag        , 1)                        , FL.mul(tRedstone,  2), NF, ST.make(MD.TE, "material", 1, 2));
			RM.Injector         .addRecipe1(T, 16,   16, OP.spring.mat(MT.Electrum  , 1)                        , FL.mul(tRedstone,  2), NF, ST.make(MD.TE, "material", 1, 3));
			RM.Injector         .addRecipe1(T, 16,   80, ST.make(MD.TE, "Plate", 1, 0)                          , FL.mul(tRedstone, 10), NF, ST.make(MD.TE, "Plate", 1, 1));
			RM.Injector         .addRecipe1(T, 16,  360, ST.make(MD.TE, "Frame", 1, 6)                          , FL.mul(tRedstone, 40), NF, ST.make(MD.TE, "Frame", 1, 7));
			RM.Injector         .addRecipe1(T, 16,  360, ST.make(MD.TE, "Frame", 1, 8)                          , FL.mul(tRedstone, 40), NF, ST.make(MD.TE, "Frame", 1, 9));
			RM.Canner           .addRecipe1(T, 16,   16, OP.spring.mat(MT.Au        , 1)                        , FL.mul(tRedstone,  2), NF, ST.make(MD.TE, "material", 1, 1));
			RM.Canner           .addRecipe1(T, 16,   16, OP.spring.mat(MT.Ag        , 1)                        , FL.mul(tRedstone,  2), NF, ST.make(MD.TE, "material", 1, 2));
			RM.Canner           .addRecipe1(T, 16,   16, OP.spring.mat(MT.Electrum  , 1)                        , FL.mul(tRedstone,  2), NF, ST.make(MD.TE, "material", 1, 3));
			RM.Canner           .addRecipe1(T, 16,   80, ST.make(MD.TE, "Plate", 1, 0)                          , FL.mul(tRedstone, 10), NF, ST.make(MD.TE, "Plate", 1, 1));
			RM.Canner           .addRecipe1(T, 16,  360, ST.make(MD.TE, "Frame", 1, 6)                          , FL.mul(tRedstone, 40), NF, ST.make(MD.TE, "Frame", 1, 7));
			RM.Canner           .addRecipe1(T, 16,  360, ST.make(MD.TE, "Frame", 1, 8)                          , FL.mul(tRedstone, 40), NF, ST.make(MD.TE, "Frame", 1, 9));
			}
			RM.Injector         .addRecipe1(T, 16,   80, ST.make(MD.TE, "Plate", 1, 0)                          , FL.Glowstone_TE.make(1000), NF, ST.make(MD.TE, "Plate", 1, 2));
			RM.Injector         .addRecipe1(T, 16,   80, ST.make(MD.TE, "Frame", 1,12)                          , FL.Glowstone_TE.make(1000), NF, ST.make(MD.TE, "Light", 1, 0));
			RM.Canner           .addRecipe1(T, 16,   80, ST.make(MD.TE, "Plate", 1, 0)                          , FL.Glowstone_TE.make(1000), NF, ST.make(MD.TE, "Plate", 1, 2));
			RM.Canner           .addRecipe1(T, 16,   80, ST.make(MD.TE, "Frame", 1,12)                          , FL.Glowstone_TE.make(1000), NF, ST.make(MD.TE, "Light", 1, 0));

			for (FluidStack tWater : FL.array(FL.Water.make(1000), FL.SpDew.make(1000), FL.DistW.make(800))) {
			if (IL.FZ_Sludge.exists())
			RM.Mixer            .addRecipe2(T, 16,   16, IL.FZ_Sludge   .get(2), ST.make(Blocks.dirt, 1, W), tWater, NF, ST.make(Items.clay_ball, 4, 0));
			if (IL.IE_Slag.exists())
			RM.Mixer            .addRecipe2(T, 16,   16, IL.IE_Slag     .get(2), ST.make(Blocks.dirt, 1, W), tWater, NF, ST.make(Items.clay_ball, 4, 0));
			RM.Mixer            .addRecipe2(T, 16,   16, IL.TE_Slag     .get(2), ST.make(Blocks.dirt, 1, W), tWater, NF, ST.make(Items.clay_ball, 4, 0));
			RM.Mixer            .addRecipe2(T, 16,   16, IL.TE_Slag_Rich.get(1), ST.make(Blocks.dirt, 1, W), tWater, NF, ST.make(Items.clay_ball, 4, 0));
			}
			
			for (OreDictMaterial tMat : new OreDictMaterial[] {MT.KNO3, MT.NaNO3, MT.Niter}) {
			if (IL.FZ_Sludge.exists())
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.FZ_Sludge      .get(1), OM.dust(MT.Charcoal, U*1)), IL.TE_Phyto_Gro_Rich.get(32));
			if (IL.IE_Slag.exists())
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.IE_Slag        .get(1), OM.dust(MT.Charcoal, U*1)), IL.TE_Phyto_Gro_Rich.get(32));
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.TE_Slag_Rich   .get(1), OM.dust(MT.Charcoal, U*1)), IL.TE_Phyto_Gro_Rich.get(32));
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.TE_Slag        .get(1), OM.dust(MT.Charcoal, U*1)), IL.TE_Phyto_Gro.get(32));
			for (OreDictMaterial tWood : ANY.Wood.mToThis) {
				if (ANY.WoodDefault.mToThis.contains(tWood)) {
					if (IL.FZ_Sludge.exists())
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.FZ_Sludge      .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro_Rich.get( 8));
					if (IL.IE_Slag.exists())
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.IE_Slag        .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro_Rich.get( 8));
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.TE_Slag_Rich   .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro_Rich.get( 8));
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.TE_Slag        .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro.get(8));
				} else {
					if (IL.FZ_Sludge.exists())
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.FZ_Sludge      .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro_Rich.get(16));
					if (IL.IE_Slag.exists())
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.IE_Slag        .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro_Rich.get(16));
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.TE_Slag_Rich   .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro_Rich.get(16));
					RM.Mixer    .addRecipeX(T, 16,   16, ST.array(OM.dust(tMat, U*1), IL.TE_Slag        .get(1), OM.dust(tWood, U*2)), IL.TE_Phyto_Gro.get(16));
				}
			}}
			
			new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
			addListener(new OreDictListenerEvent_TwoNames(OP.dust.dat(ANY.Wood), OD.slimeball) {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
				if (IL.FZ_Sludge.exists())
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aStack1, IL.FZ_Sludge   .get(1), aStack2), ST.make(MD.TE, "florb", 4, 0));
				if (IL.IE_Slag.exists())
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aStack1, IL.IE_Slag     .get(1), aStack2), ST.make(MD.TE, "florb", 4, 0));
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aStack1, IL.TE_Slag     .get(1), aStack2), ST.make(MD.TE, "florb", 4, 0));
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aStack1, IL.TE_Slag_Rich.get(1), aStack2), ST.make(MD.TE, "florb", 4, 0));
				RM.Loom         .addRecipeX(T, 16,   16, ST.array(ST.amount(4, aStack1), ST.make(Items.string, 4, W), aStack2), ST.make(MD.TE, "Sponge", 1, 1));
			}});
			addListener(OP.dust.dat(ANY.Wood), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
				if (IL.FZ_Sludge.exists())
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aEvent.mStack, IL.FZ_Sludge   .get(1), ST.make(Items.magma_cream, 1, W)), ST.make(MD.TE, "florb", 4, 1));
				if (IL.IE_Slag.exists())
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aEvent.mStack, IL.IE_Slag     .get(1), ST.make(Items.magma_cream, 1, W)), ST.make(MD.TE, "florb", 4, 1));
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aEvent.mStack, IL.TE_Slag     .get(1), ST.make(Items.magma_cream, 1, W)), ST.make(MD.TE, "florb", 4, 1));
				RM.Mixer        .addRecipeX(T, 16,   16, ST.array(aEvent.mStack, IL.TE_Slag_Rich.get(1), ST.make(Items.magma_cream, 1, W)), ST.make(MD.TE, "florb", 4, 1));
				RM.Loom         .addRecipeX(T, 16,   16, ST.array(ST.amount(4, aEvent.mStack), ST.make(Items.string, 4, W), ST.make(Items.magma_cream, 1, W)), ST.make(MD.TE, "Sponge", 1, 2));
			}});
			}};
		}
	}
}
