/**
 * Copyright (c) 2025 GregTech-6 Team
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

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.*;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static gregapi.data.CS.*;

public class Compat_Recipes_Ganys extends CompatMods {
	public Compat_Recipes_Ganys(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {
		if (MD.EtFu.mLoaded) {OUT.println("GT_Mod: Doing Recipes of the Future.");
			ItemStack tEtFuturumPurpur = ST.make(MD.EtFu, "purpur_block", 1, 0), tEtFuturumPurpurPillar = ST.make(MD.EtFu, "purpur_pillar", 1, 0);
			RM.sawing(16, 16, F, 100,               tEtFuturumPurpur, ST.make(MD.EtFu, "purpur_slab", 2, 0));
			RM.sawing(16, 16, F, 100,               tEtFuturumPurpurPillar, ST.make(MD.EtFu, "purpur_slab", 2, 0));
			CR.shapeless(                           tEtFuturumPurpur, new Object[] {tEtFuturumPurpurPillar});
			CR.shaped(                              IL.EtFu_Block_Honey.get(1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES | CR.DEL_OTHER_NATIVE_RECIPES, "XX", "XX", 'X', OD.container250honey);
			CR.shapeless(                           IL.EtFu_Block_Honey.get(1), new Object[] {OD.container1000honey});
			CR.shaped(                              ST.make(MD.EtFu, "slime", 1, 0), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES | CR.DEL_OTHER_NATIVE_RECIPES, "XXX", "XXX", "XXX", 'X', ST.make(Items.slime_ball, 1, W));
			RM.ic2_compressor(                      ST.make(Items.slime_ball, 9, 0), ST.make(MD.EtFu, "slime", 1, 0));
			RM.Compressor.addRecipe1(T, 16, 16,     ST.make(Items.slime_ball, 9, W), ST.make(MD.EtFu, "slime", 1, 0));
			RM.Boxinator.addRecipe2(T, 16, 16,      ST.make(Items.slime_ball, 9, W), ST.tag(9), ST.make(MD.EtFu, "slime", 1, 0));
			RM.Unboxinator.addRecipe1(T, 16, 16,    ST.make(MD.EtFu, "slime", 1, 0), ST.make(Items.slime_ball, 9, 0));
			RM.ic2_extractor(                       ST.make(MD.EtFu, "slime", 1, 0), ST.make(Items.slime_ball, 9, 0));
			RM.Squeezer.addRecipe1(T, 16, 16,       ST.make(MD.EtFu, "sponge", 1, 1), NF, FL.Water.make(125), ST.make(MD.EtFu, "sponge", 1, 0));
			RM.Drying.addRecipe1(T, 16, 16,         ST.make(MD.EtFu, "sponge", 1, 1), NF, FL.DistW.make(125), ST.make(MD.EtFu, "sponge", 1, 0));
			RM.smash(                               ST.make(MD.EtFu, "red_sandstone", 1, W), ST.make(Blocks.sand, 1, 1));
			RM.sawing(16, 16, F, 100,               ST.make(MD.EtFu, "red_sandstone", 1, W), ST.make(MD.EtFu, "red_sandstone_slab", 2, 0));
			RM.Compressor.addRecipe1(T, 16, 32,     ST.make(Blocks.sand, 4, 1), ST.make(MD.EtFu, "red_sandstone", 1, 0));
			RM.smash(                               IL.EtFu_Quartz_Bricks.get(1), OP.gem.mat(MT.NetherQuartz, 4));
			RM.compactsmash(                        OP.gem.mat(MT.Amethyst, 4), IL.EtFu_Amethyst.get(1));
			RM.generify(                            ST.make(Blocks.sponge, 1, W), ST.make(MD.EtFu, "sponge", 1, 0));
			RM.generify(                            ST.make(MD.EtFu, "sponge", 1, 0), ST.make(Blocks.sponge, 1, 0));
			RM.generify(                            ST.make(Blocks.double_plant, 1, 4), ST.make(MD.EtFu, "rose", 6, 0));
			RM.generify(                            ST.make(MD.EtFu, "rose", 6, 0), ST.make(Blocks.double_plant, 1, 4));
			RM.generify(                            tEtFuturumPurpurPillar, tEtFuturumPurpur);
			RM.Mixer.addRecipe1(T, 16, 16,          IL.EtFu_Chorus_Fruit.get(1), FL.Potion_FireResistance_1  .make(125), FL.Dragon_Breath.make(125), ZL_IS);
			RM.Mixer.addRecipe1(T, 16, 16,          IL.EtFu_Chorus_Fruit.get(1), FL.Potion_FireResistance_1L .make(125), FL.Dragon_Breath.make(125), ZL_IS);
			RM.Mixer.addRecipe1(T, 16, 16,          IL.EtFu_Chorus_Fruit.get(1), FL.Potion_FireResistance_1S .make(125), FL.Dragon_Breath.make(125), ZL_IS);
			RM.Mixer.addRecipe1(T, 16, 16,          IL.EtFu_Chorus_Fruit.get(1), FL.Potion_FireResistance_1LS.make(125), FL.Dragon_Breath.make(125), ZL_IS);
			RM.Mixer.addRecipe1(T, 16, 16,          IL.EtFu_Chorus_Fruit.get(1), FL.Potion_FireResistance_1D .make(125), FL.Dragon_Breath.make(125), ZL_IS);
			RM.Mixer.addRecipe1(T, 16, 16,          IL.EtFu_Chorus_Fruit.get(1), FL.Potion_FireResistance_1LD.make(125), FL.Dragon_Breath.make(125), ZL_IS);
			CR.shapeless(                           IL.Plank.get(7), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, IL.EtFu_Barrel});
			RM.sawing(16, 112, F, 100,              IL.EtFu_Barrel.get(1), IL.Plank.get(7));
			
			RM.Squeezer.addRecipe1(T, 16, 16,       ST.make(MD.EtFu, "rose"              , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
			RM.Juicer  .addRecipe1(T, 16, 16,       ST.make(MD.EtFu, "rose"              , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
			RM.ic2_extractor(                       ST.make(MD.EtFu, "rose"              , 1, 0), ST.make(Items.dye, 2, DYE_INDEX_Red));
			RM.Squeezer.addRecipe1(T, 16, 16,       ST.make(MD.EtFu, "lily_of_the_valley", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], ST.make(MD.EtFu, "dye", 1, 0));
			RM.Juicer  .addRecipe1(T, 16, 16,       ST.make(MD.EtFu, "lily_of_the_valley", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], ST.make(MD.EtFu, "dye", 1, 0));
			RM.ic2_extractor(                       ST.make(MD.EtFu, "lily_of_the_valley", 1, 0), ST.make(MD.EtFu, "dye", 2, 0));
			RM.Squeezer.addRecipe1(T, 16, 16,       ST.make(MD.EtFu, "cornflower"        , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], ST.make(MD.EtFu, "dye", 1, 1));
			RM.Juicer  .addRecipe1(T, 16, 16,       ST.make(MD.EtFu, "cornflower"        , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], ST.make(MD.EtFu, "dye", 1, 1));
			RM.ic2_extractor(                       ST.make(MD.EtFu, "cornflower"        , 1, 0), ST.make(MD.EtFu, "dye", 2, 1));
			RM.Squeezer.addRecipe1(T, 16, 16,       ST.make(MD.EtFu, "wither_rose"       , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Black], ST.make(MD.EtFu, "dye", 1, 3));
			RM.Juicer  .addRecipe1(T, 16, 16,       ST.make(MD.EtFu, "wither_rose"       , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Black], ST.make(MD.EtFu, "dye", 1, 3));
			RM.ic2_extractor(                       ST.make(MD.EtFu, "wither_rose"       , 1, 0), ST.make(MD.EtFu, "dye", 2, 3));
			
			RM.Distillery.addRecipe1(T, 16, 48, IL.EtFu_Wither_Rose.get(1), FL.Potion_Thick.make(750), FL.Potion_Harm_2.make(750), ZL_IS);
			
			RM.smash(ST.make(MD.EtFu, "brown_mushroom", 1, W), ST.make(Blocks.brown_mushroom, 1, 0));
			RM.smash(ST.make(MD.EtFu, "red_mushroom"  , 1, W), ST.make(Blocks.red_mushroom, 1, 0));
			RM.biomass(ST.make(MD.EtFu, "brown_mushroom"    , 8, W));
			RM.biomass(ST.make(MD.EtFu, "red_mushroom"      , 8, W));
			RM.biomass(ST.make(MD.EtFu, "rose"              , 8, W));
			RM.biomass(ST.make(MD.EtFu, "wither_rose"       , 8, W));
			RM.biomass(ST.make(MD.EtFu, "cornflower"        , 8, W));
			RM.biomass(ST.make(MD.EtFu, "lily_of_the_valley", 8, W));
			RM.biomass(IL.EtFu_Wart_Block_Nether.wild(1));
			
			RM.compact(ST.make(Items.nether_wart, 1, 0), 9, IL.EtFu_Wart_Block_Nether.get(1));
			
			CR.delate(MD.EtFu, "netherite_ingot", "netherite_sword", "netherite_pickaxe", "netherite_spade", "netherite_axe", "netherite_hoe", "netherite_helmet", "netherite_chestplate", "netherite_leggings", "netherite_boots", "copper_block", "cut_copper_slab", "cut_copper_stairs", "exposed_cut_copper_stairs", "weathered_cut_copper_stairs", "oxidized_cut_copper_stairs", "waxed_cut_copper_stairs", "waxed_exposed_cut_copper_stairs", "waxed_weathered_cut_copper_stairs", "waxed_oxidized_cut_copper_stairs");
			
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_sword     , 1, 0), MT.Netherite.liquid(2*U4, T), NF, ST.make(MD.EtFu, "netherite_sword"     , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_pickaxe   , 1, 0), MT.Netherite.liquid(3*U4, T), NF, ST.make(MD.EtFu, "netherite_pickaxe"   , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_shovel    , 1, 0), MT.Netherite.liquid(1*U4, T), NF, ST.make(MD.EtFu, "netherite_spade"     , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_axe       , 1, 0), MT.Netherite.liquid(3*U4, T), NF, ST.make(MD.EtFu, "netherite_axe"       , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_hoe       , 1, 0), MT.Netherite.liquid(2*U4, T), NF, ST.make(MD.EtFu, "netherite_hoe"       , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_helmet    , 1, 0), MT.Netherite.liquid(5*U4, T), NF, ST.make(MD.EtFu, "netherite_helmet"    , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_chestplate, 1, 0), MT.Netherite.liquid(8*U4, T), NF, ST.make(MD.EtFu, "netherite_chestplate", 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_leggings  , 1, 0), MT.Netherite.liquid(7*U4, T), NF, ST.make(MD.EtFu, "netherite_leggings"  , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_boots     , 1, 0), MT.Netherite.liquid(4*U4, T), NF, ST.make(MD.EtFu, "netherite_boots"     , 1, 0));
			
			
			if (IL.EtFu_Block_Copper.exists()) {
				CR.remove(IL.EtFu_Block_Copper.get(1));
				CR.remove(IL.EtFu_Block_Copper_Waxed.get(1));
				CR.shaped(IL.EtFu_Block_Copper_Cut.get(1), CR.DEF_MIR,  "X",  "X", 'X', IL.EtFu_Slabs_Copper_Cut);
				CR.shaped(IL.EtFu_Block_Copper_Cut.get(1), CR.DEF_MIR, "XX", "XX", 'X', OP.ingot.dat(ANY.Cu));
				CR.shaped(IL.EtFu_Slabs_Copper_Cut.get(1), CR.DEF_MIR,       "XX", 'X', OP.ingot.dat(ANY.Cu));
				CR.shaped(IL.EtFu_Stair_Copper_Cut.get(1), CR.DEF_MIR, " X", "XX", 'X', OP.ingot.dat(ANY.Cu));
				CR.shapeless(OP.ingot.mat(MT.Cu, 9), new Object[] {IL.EtFu_Block_Copper});
				CR.shapeless(OP.ingot.mat(MT.Cu, 9), new Object[] {IL.EtFu_Block_Copper_Waxed});
				CR.shapeless(OP.ingot.mat(MT.Cu, 4), new Object[] {IL.EtFu_Block_Copper_Cut});
				CR.shapeless(OP.ingot.mat(MT.Cu, 4), new Object[] {IL.EtFu_Block_Copper_Cut_Waxed});
				CR.shapeless(OP.ingot.mat(MT.Cu, 2), new Object[] {IL.EtFu_Slabs_Copper_Cut});
				CR.shapeless(OP.ingot.mat(MT.Cu, 2), new Object[] {IL.EtFu_Slabs_Copper_Cut_Waxed});
				CR.shapeless(OP.ingot.mat(MT.Cu, 3), new Object[] {IL.EtFu_Stair_Copper_Cut});
				CR.shapeless(OP.ingot.mat(MT.Cu, 3), new Object[] {IL.EtFu_Stair_Copper_Cut_Waxed});
				CR.shaped(IL.EtFu_Slabs_Copper_Cut                .get(2), CR.DEF_MIR, "sX"      , 'X', IL.EtFu_Block_Copper_Cut                );
				CR.shaped(IL.EtFu_Slabs_Copper_Cut_Exposed        .get(2), CR.DEF_MIR, "sX"      , 'X', IL.EtFu_Block_Copper_Cut_Exposed        );
				CR.shaped(IL.EtFu_Slabs_Copper_Cut_Weathered      .get(2), CR.DEF_MIR, "sX"      , 'X', IL.EtFu_Block_Copper_Cut_Weathered      );
				CR.shaped(IL.EtFu_Slabs_Copper_Cut_Oxidized       .get(2), CR.DEF_MIR, "sX"      , 'X', IL.EtFu_Block_Copper_Cut_Oxidized       );
				CR.shaped(IL.EtFu_Slabs_Copper_Cut_Waxed          .get(2), CR.DEF_MIR, "sX"      , 'X', IL.EtFu_Block_Copper_Cut_Waxed          );
				CR.shaped(IL.EtFu_Slabs_Copper_Cut_Exposed_Waxed  .get(2), CR.DEF_MIR, "sX"      , 'X', IL.EtFu_Block_Copper_Cut_Exposed_Waxed  );
				CR.shaped(IL.EtFu_Slabs_Copper_Cut_Weathered_Waxed.get(2), CR.DEF_MIR, "sX"      , 'X', IL.EtFu_Block_Copper_Cut_Weathered_Waxed);
				CR.shaped(IL.EtFu_Slabs_Copper_Cut_Oxidized_Waxed .get(2), CR.DEF_MIR, "sX"      , 'X', IL.EtFu_Block_Copper_Cut_Oxidized_Waxed );
				CR.shaped(IL.EtFu_Stair_Copper_Cut                .get(4), CR.DEF_MIR, "sX", "XX", 'X', IL.EtFu_Block_Copper_Cut                );
				CR.shaped(IL.EtFu_Stair_Copper_Cut_Exposed        .get(4), CR.DEF_MIR, "sX", "XX", 'X', IL.EtFu_Block_Copper_Cut_Exposed        );
				CR.shaped(IL.EtFu_Stair_Copper_Cut_Weathered      .get(4), CR.DEF_MIR, "sX", "XX", 'X', IL.EtFu_Block_Copper_Cut_Weathered      );
				CR.shaped(IL.EtFu_Stair_Copper_Cut_Oxidized       .get(4), CR.DEF_MIR, "sX", "XX", 'X', IL.EtFu_Block_Copper_Cut_Oxidized       );
				CR.shaped(IL.EtFu_Stair_Copper_Cut_Waxed          .get(4), CR.DEF_MIR, "sX", "XX", 'X', IL.EtFu_Block_Copper_Cut_Waxed          );
				CR.shaped(IL.EtFu_Stair_Copper_Cut_Exposed_Waxed  .get(4), CR.DEF_MIR, "sX", "XX", 'X', IL.EtFu_Block_Copper_Cut_Exposed_Waxed  );
				CR.shaped(IL.EtFu_Stair_Copper_Cut_Weathered_Waxed.get(4), CR.DEF_MIR, "sX", "XX", 'X', IL.EtFu_Block_Copper_Cut_Weathered_Waxed);
				if (IL.EtFu_Stair_Copper_Cut_Oxidized_Waxed.exists())
				CR.shaped(IL.EtFu_Stair_Copper_Cut_Oxidized_Waxed .get(4), CR.DEF_MIR, "sX", "XX", 'X', IL.EtFu_Block_Copper_Cut_Oxidized_Waxed );
				
				for (OreDictPrefix tPrefix : OP.array_dust_ingot_plate) {
					CR.shapeless(IL.EtFu_Block_Copper_Waxed              .get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Block_Copper              , tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Block_Copper_Exposed_Waxed      .get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Block_Copper_Exposed      , tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Block_Copper_Weathered_Waxed    .get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Block_Copper_Weathered    , tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Block_Copper_Oxidized_Waxed     .get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Block_Copper_Oxidized     , tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Block_Copper_Cut_Waxed          .get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Block_Copper_Cut          , tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Block_Copper_Cut_Exposed_Waxed  .get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Block_Copper_Cut_Exposed  , tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Block_Copper_Cut_Weathered_Waxed.get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Block_Copper_Cut_Weathered, tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Block_Copper_Cut_Oxidized_Waxed .get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Block_Copper_Cut_Oxidized , tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Slabs_Copper_Cut_Waxed          .get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Slabs_Copper_Cut          , tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Slabs_Copper_Cut_Exposed_Waxed  .get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Slabs_Copper_Cut_Exposed  , tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Slabs_Copper_Cut_Weathered_Waxed.get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Slabs_Copper_Cut_Weathered, tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Slabs_Copper_Cut_Oxidized_Waxed .get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Slabs_Copper_Cut_Oxidized , tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Stair_Copper_Cut_Waxed          .get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Stair_Copper_Cut          , tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Stair_Copper_Cut_Exposed_Waxed  .get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Stair_Copper_Cut_Exposed  , tPrefix.dat(ANY.Wax)});
					CR.shapeless(IL.EtFu_Stair_Copper_Cut_Weathered_Waxed.get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Stair_Copper_Cut_Weathered, tPrefix.dat(ANY.Wax)});
					if (IL.EtFu_Stair_Copper_Cut_Oxidized_Waxed.exists())
					CR.shapeless(IL.EtFu_Stair_Copper_Cut_Oxidized_Waxed .get(1), CR.DEF_NCC, new Object[] {IL.EtFu_Stair_Copper_Cut_Oxidized , tPrefix.dat(ANY.Wax)});
				}
				
				for (OreDictMaterial tMat : ANY.Cu.mToThis) {
					RM.pack(OP.ingot.mat(tMat, 2), IL.EtFu_Slabs_Copper_Cut.get(1));
					RM.pack(OP.ingot.mat(tMat, 3), IL.EtFu_Stair_Copper_Cut.get(1));
					RM.pack(OP.ingot.mat(tMat, 4), IL.EtFu_Block_Copper_Cut.get(1));
				}
				
				RM.unpack(IL.EtFu_Block_Copper                     .get(1), OP.ingot.mat(MT.Cu, 9));
				RM.unpack(IL.EtFu_Block_Copper_Exposed             .get(1), OP.ingot.mat(MT.Cu, 9));
				RM.unpack(IL.EtFu_Block_Copper_Weathered           .get(1), OP.ingot.mat(MT.Cu, 9));
				RM.unpack(IL.EtFu_Block_Copper_Oxidized            .get(1), OP.ingot.mat(MT.Cu, 9));
				RM.unpack(IL.EtFu_Block_Copper_Waxed               .get(1), OP.ingot.mat(MT.Cu, 9));
				RM.unpack(IL.EtFu_Block_Copper_Exposed_Waxed       .get(1), OP.ingot.mat(MT.Cu, 9));
				RM.unpack(IL.EtFu_Block_Copper_Weathered_Waxed     .get(1), OP.ingot.mat(MT.Cu, 9));
				RM.unpack(IL.EtFu_Block_Copper_Oxidized_Waxed      .get(1), OP.ingot.mat(MT.Cu, 9));
				RM.unpack(IL.EtFu_Block_Copper_Cut                 .get(1), OP.ingot.mat(MT.Cu, 4));
				RM.unpack(IL.EtFu_Block_Copper_Cut_Exposed         .get(1), OP.ingot.mat(MT.Cu, 4));
				RM.unpack(IL.EtFu_Block_Copper_Cut_Weathered       .get(1), OP.ingot.mat(MT.Cu, 4));
				RM.unpack(IL.EtFu_Block_Copper_Cut_Oxidized        .get(1), OP.ingot.mat(MT.Cu, 4));
				RM.unpack(IL.EtFu_Block_Copper_Cut_Waxed           .get(1), OP.ingot.mat(MT.Cu, 4));
				RM.unpack(IL.EtFu_Block_Copper_Cut_Exposed_Waxed   .get(1), OP.ingot.mat(MT.Cu, 4));
				RM.unpack(IL.EtFu_Block_Copper_Cut_Weathered_Waxed .get(1), OP.ingot.mat(MT.Cu, 4));
				RM.unpack(IL.EtFu_Block_Copper_Cut_Oxidized_Waxed  .get(1), OP.ingot.mat(MT.Cu, 4));
				RM.unpack(IL.EtFu_Slabs_Copper_Cut                .wild(1), OP.ingot.mat(MT.Cu, 2));
				RM.unpack(IL.EtFu_Stair_Copper_Cut                .wild(1), OP.ingot.mat(MT.Cu, 3));
				RM.unpack(IL.EtFu_Stair_Copper_Cut_Exposed        .wild(1), OP.ingot.mat(MT.Cu, 3));
				RM.unpack(IL.EtFu_Stair_Copper_Cut_Weathered      .wild(1), OP.ingot.mat(MT.Cu, 3));
				RM.unpack(IL.EtFu_Stair_Copper_Cut_Oxidized       .wild(1), OP.ingot.mat(MT.Cu, 3));
				RM.unpack(IL.EtFu_Stair_Copper_Cut_Waxed          .wild(1), OP.ingot.mat(MT.Cu, 3));
				RM.unpack(IL.EtFu_Stair_Copper_Cut_Exposed_Waxed  .wild(1), OP.ingot.mat(MT.Cu, 3));
				RM.unpack(IL.EtFu_Stair_Copper_Cut_Weathered_Waxed.wild(1), OP.ingot.mat(MT.Cu, 3));
				RM.unpack(IL.EtFu_Stair_Copper_Cut_Oxidized_Waxed .wild(1), OP.ingot.mat(MT.Cu, 3));
				
				RM.add_smelting(IL.EtFu_Block_Copper                     .get(1), OP.ingot.mat(MT.Cu, 9), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Exposed             .get(1), OP.ingot.mat(MT.Cu, 9), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Weathered           .get(1), OP.ingot.mat(MT.Cu, 9), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Oxidized            .get(1), OP.ingot.mat(MT.Cu, 9), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Waxed               .get(1), OP.ingot.mat(MT.Cu, 9), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Exposed_Waxed       .get(1), OP.ingot.mat(MT.Cu, 9), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Weathered_Waxed     .get(1), OP.ingot.mat(MT.Cu, 9), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Oxidized_Waxed      .get(1), OP.ingot.mat(MT.Cu, 9), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Cut                 .get(1), OP.ingot.mat(MT.Cu, 4), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Cut_Exposed         .get(1), OP.ingot.mat(MT.Cu, 4), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Cut_Weathered       .get(1), OP.ingot.mat(MT.Cu, 4), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Cut_Oxidized        .get(1), OP.ingot.mat(MT.Cu, 4), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Cut_Waxed           .get(1), OP.ingot.mat(MT.Cu, 4), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Cut_Exposed_Waxed   .get(1), OP.ingot.mat(MT.Cu, 4), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Cut_Weathered_Waxed .get(1), OP.ingot.mat(MT.Cu, 4), F, F, T);
				RM.add_smelting(IL.EtFu_Block_Copper_Cut_Oxidized_Waxed  .get(1), OP.ingot.mat(MT.Cu, 4), F, F, T);
				RM.add_smelting(IL.EtFu_Slabs_Copper_Cut                .wild(1), OP.ingot.mat(MT.Cu, 2), F, F, T);
				RM.add_smelting(IL.EtFu_Stair_Copper_Cut                .wild(1), OP.ingot.mat(MT.Cu, 3), F, F, T);
				RM.add_smelting(IL.EtFu_Stair_Copper_Cut_Exposed        .wild(1), OP.ingot.mat(MT.Cu, 3), F, F, T);
				RM.add_smelting(IL.EtFu_Stair_Copper_Cut_Weathered      .wild(1), OP.ingot.mat(MT.Cu, 3), F, F, T);
				RM.add_smelting(IL.EtFu_Stair_Copper_Cut_Oxidized       .wild(1), OP.ingot.mat(MT.Cu, 3), F, F, T);
				RM.add_smelting(IL.EtFu_Stair_Copper_Cut_Waxed          .wild(1), OP.ingot.mat(MT.Cu, 3), F, F, T);
				RM.add_smelting(IL.EtFu_Stair_Copper_Cut_Exposed_Waxed  .wild(1), OP.ingot.mat(MT.Cu, 3), F, F, T);
				RM.add_smelting(IL.EtFu_Stair_Copper_Cut_Weathered_Waxed.wild(1), OP.ingot.mat(MT.Cu, 3), F, F, T);
				RM.add_smelting(IL.EtFu_Stair_Copper_Cut_Oxidized_Waxed .wild(1), OP.ingot.mat(MT.Cu, 3), F, F, T);
				
				for (FluidStack tWater : FL.waters(100)) {
					RM.Bath.addRecipe1(T, 0, 64, IL.EtFu_Block_Copper              .get(1), tWater, NF, IL.EtFu_Block_Copper_Exposed      .get(1));
					RM.Bath.addRecipe1(T, 0, 64, IL.EtFu_Block_Copper_Exposed      .get(1), tWater, NF, IL.EtFu_Block_Copper_Weathered    .get(1));
					RM.Bath.addRecipe1(T, 0, 64, IL.EtFu_Block_Copper_Weathered    .get(1), tWater, NF, IL.EtFu_Block_Copper_Oxidized     .get(1));
					RM.Bath.addRecipe1(T, 0, 64, IL.EtFu_Block_Copper_Cut          .get(1), tWater, NF, IL.EtFu_Block_Copper_Cut_Exposed  .get(1));
					RM.Bath.addRecipe1(T, 0, 64, IL.EtFu_Block_Copper_Cut_Exposed  .get(1), tWater, NF, IL.EtFu_Block_Copper_Cut_Weathered.get(1));
					RM.Bath.addRecipe1(T, 0, 64, IL.EtFu_Block_Copper_Cut_Weathered.get(1), tWater, NF, IL.EtFu_Block_Copper_Cut_Oxidized .get(1));
					RM.Bath.addRecipe1(T, 0, 64, IL.EtFu_Stair_Copper_Cut          .get(1), tWater, NF, IL.EtFu_Stair_Copper_Cut_Exposed  .get(1));
					RM.Bath.addRecipe1(T, 0, 64, IL.EtFu_Stair_Copper_Cut_Exposed  .get(1), tWater, NF, IL.EtFu_Stair_Copper_Cut_Weathered.get(1));
					RM.Bath.addRecipe1(T, 0, 64, IL.EtFu_Stair_Copper_Cut_Weathered.get(1), tWater, NF, IL.EtFu_Stair_Copper_Cut_Oxidized .get(1));
					RM.Bath.addRecipe1(T, 0, 64, IL.EtFu_Slabs_Copper_Cut          .get(1), tWater, NF, IL.EtFu_Slabs_Copper_Cut_Exposed  .get(1));
					RM.Bath.addRecipe1(T, 0, 64, IL.EtFu_Slabs_Copper_Cut_Exposed  .get(1), tWater, NF, IL.EtFu_Slabs_Copper_Cut_Weathered.get(1));
					RM.Bath.addRecipe1(T, 0, 64, IL.EtFu_Slabs_Copper_Cut_Weathered.get(1), tWater, NF, IL.EtFu_Slabs_Copper_Cut_Oxidized .get(1));
				}
				
				for (OreDictMaterial tMat : ANY.Wax.mToThis) {
					RM.Laminator.addRecipe2(T, 16, 32, OP.plate.mat(tMat, 1), IL.EtFu_Block_Copper              .get(1), IL.EtFu_Block_Copper_Waxed              .get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.plate.mat(tMat, 1), IL.EtFu_Block_Copper_Exposed      .get(1), IL.EtFu_Block_Copper_Exposed_Waxed      .get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.plate.mat(tMat, 1), IL.EtFu_Block_Copper_Weathered    .get(1), IL.EtFu_Block_Copper_Weathered_Waxed    .get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.plate.mat(tMat, 1), IL.EtFu_Block_Copper_Oxidized     .get(1), IL.EtFu_Block_Copper_Oxidized_Waxed     .get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.plate.mat(tMat, 1), IL.EtFu_Block_Copper_Cut          .get(1), IL.EtFu_Block_Copper_Cut_Waxed          .get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.plate.mat(tMat, 1), IL.EtFu_Block_Copper_Cut_Exposed  .get(1), IL.EtFu_Block_Copper_Cut_Exposed_Waxed  .get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.plate.mat(tMat, 1), IL.EtFu_Block_Copper_Cut_Weathered.get(1), IL.EtFu_Block_Copper_Cut_Weathered_Waxed.get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.plate.mat(tMat, 1), IL.EtFu_Block_Copper_Cut_Oxidized .get(1), IL.EtFu_Block_Copper_Cut_Oxidized_Waxed .get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.plate.mat(tMat, 1), IL.EtFu_Slabs_Copper_Cut          .get(2), IL.EtFu_Slabs_Copper_Cut_Waxed          .get(2));
					RM.Laminator.addRecipe2(T, 16, 32, OP.plate.mat(tMat, 1), IL.EtFu_Slabs_Copper_Cut_Exposed  .get(2), IL.EtFu_Slabs_Copper_Cut_Exposed_Waxed  .get(2));
					RM.Laminator.addRecipe2(T, 16, 32, OP.plate.mat(tMat, 1), IL.EtFu_Slabs_Copper_Cut_Weathered.get(2), IL.EtFu_Slabs_Copper_Cut_Weathered_Waxed.get(2));
					RM.Laminator.addRecipe2(T, 16, 32, OP.plate.mat(tMat, 1), IL.EtFu_Slabs_Copper_Cut_Oxidized .get(2), IL.EtFu_Slabs_Copper_Cut_Oxidized_Waxed .get(2));
					RM.Laminator.addRecipe2(T, 16, 24, OP.plate.mat(tMat, 1), IL.EtFu_Stair_Copper_Cut          .get(1), IL.EtFu_Stair_Copper_Cut_Waxed          .get(1));
					RM.Laminator.addRecipe2(T, 16, 24, OP.plate.mat(tMat, 1), IL.EtFu_Stair_Copper_Cut_Exposed  .get(1), IL.EtFu_Stair_Copper_Cut_Exposed_Waxed  .get(1));
					RM.Laminator.addRecipe2(T, 16, 24, OP.plate.mat(tMat, 1), IL.EtFu_Stair_Copper_Cut_Weathered.get(1), IL.EtFu_Stair_Copper_Cut_Weathered_Waxed.get(1));
					if (IL.EtFu_Stair_Copper_Cut_Oxidized_Waxed.exists())
					RM.Laminator.addRecipe2(T, 16, 24, OP.plate.mat(tMat, 1), IL.EtFu_Stair_Copper_Cut_Oxidized .get(1), IL.EtFu_Stair_Copper_Cut_Oxidized_Waxed .get(1));
					
					RM.Laminator.addRecipe2(T, 16, 32, OP.foil .mat(tMat, 4), IL.EtFu_Block_Copper              .get(1), IL.EtFu_Block_Copper_Waxed              .get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.foil .mat(tMat, 4), IL.EtFu_Block_Copper_Exposed      .get(1), IL.EtFu_Block_Copper_Exposed_Waxed      .get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.foil .mat(tMat, 4), IL.EtFu_Block_Copper_Weathered    .get(1), IL.EtFu_Block_Copper_Weathered_Waxed    .get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.foil .mat(tMat, 4), IL.EtFu_Block_Copper_Oxidized     .get(1), IL.EtFu_Block_Copper_Oxidized_Waxed     .get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.foil .mat(tMat, 4), IL.EtFu_Block_Copper_Cut          .get(1), IL.EtFu_Block_Copper_Cut_Waxed          .get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.foil .mat(tMat, 4), IL.EtFu_Block_Copper_Cut_Exposed  .get(1), IL.EtFu_Block_Copper_Cut_Exposed_Waxed  .get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.foil .mat(tMat, 4), IL.EtFu_Block_Copper_Cut_Weathered.get(1), IL.EtFu_Block_Copper_Cut_Weathered_Waxed.get(1));
					RM.Laminator.addRecipe2(T, 16, 32, OP.foil .mat(tMat, 4), IL.EtFu_Block_Copper_Cut_Oxidized .get(1), IL.EtFu_Block_Copper_Cut_Oxidized_Waxed .get(1));
					RM.Laminator.addRecipe2(T, 16, 16, OP.foil .mat(tMat, 2), IL.EtFu_Slabs_Copper_Cut          .get(1), IL.EtFu_Slabs_Copper_Cut_Waxed          .get(1));
					RM.Laminator.addRecipe2(T, 16, 16, OP.foil .mat(tMat, 2), IL.EtFu_Slabs_Copper_Cut_Exposed  .get(1), IL.EtFu_Slabs_Copper_Cut_Exposed_Waxed  .get(1));
					RM.Laminator.addRecipe2(T, 16, 16, OP.foil .mat(tMat, 2), IL.EtFu_Slabs_Copper_Cut_Weathered.get(1), IL.EtFu_Slabs_Copper_Cut_Weathered_Waxed.get(1));
					RM.Laminator.addRecipe2(T, 16, 16, OP.foil .mat(tMat, 2), IL.EtFu_Slabs_Copper_Cut_Oxidized .get(1), IL.EtFu_Slabs_Copper_Cut_Oxidized_Waxed .get(1));
					RM.Laminator.addRecipe2(T, 16, 24, OP.foil .mat(tMat, 3), IL.EtFu_Stair_Copper_Cut          .get(1), IL.EtFu_Stair_Copper_Cut_Waxed          .get(1));
					RM.Laminator.addRecipe2(T, 16, 24, OP.foil .mat(tMat, 3), IL.EtFu_Stair_Copper_Cut_Exposed  .get(1), IL.EtFu_Stair_Copper_Cut_Exposed_Waxed  .get(1));
					RM.Laminator.addRecipe2(T, 16, 24, OP.foil .mat(tMat, 3), IL.EtFu_Stair_Copper_Cut_Weathered.get(1), IL.EtFu_Stair_Copper_Cut_Weathered_Waxed.get(1));
					if (IL.EtFu_Stair_Copper_Cut_Oxidized_Waxed.exists())
					RM.Laminator.addRecipe2(T, 16, 24, OP.foil .mat(tMat, 3), IL.EtFu_Stair_Copper_Cut_Oxidized .get(1), IL.EtFu_Stair_Copper_Cut_Oxidized_Waxed .get(1));
				}
			}
			
			BlocksGT.blockToDrop.put(IL.EtFu_Ancient_Debris     , IL.Ancient_Debris          .get(1));
			BlocksGT.blockToSilk.put(IL.EtFu_Ancient_Debris     , IL.Ancient_Debris          .get(1));
			
			if (MD.CHSL.mLoaded) {
				ItemStack tChiselPurpur = ST.make(MD.CHSL, "purpur", 1, 0);
				CR.delate(tChiselPurpur);
				RM.generify(tChiselPurpur, tEtFuturumPurpur);
				RM.generify(tEtFuturumPurpur, tChiselPurpur);
				CR.shapeless(tChiselPurpur, CR.DEF, new Object[] {tEtFuturumPurpur});
				CR.shapeless(tEtFuturumPurpur, CR.DEF, new Object[] {tChiselPurpur});
				CR.shaped(ST.make(MD.EtFu, "purpur_stairs", 4, 0), CR.DEF_MIR, new Object[] {"P  ", "PP ", "PPP", 'P', ST.make(MD.CHSL, "purpur", 1, W)});
				CR.shaped(ST.make(MD.EtFu, "purpur_slab", 6, 0), CR.DEF_MIR, new Object[] {"PPP", 'P', ST.make(MD.CHSL, "purpur", 1, W)});
				RM.sawing(16, 16, F, 100, tChiselPurpur, ST.make(MD.EtFu, "purpur_slab", 2, 0));
			}
		}
		if (MD.GaSu.mLoaded) {OUT.println("GT_Mod: Doing Recipes for Ganys Surface.");
			if (MD.EtFu.mLoaded) {
				CR.delate(MD.GaSu, "slimeBlock");
			} else {
				CR.shaped(                          ST.make(MD.GaSu, "slimeBlock", 1, 0), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES | CR.DEL_OTHER_NATIVE_RECIPES, "XXX", "XXX", "XXX", 'X', ST.make(Items.slime_ball, 1, W));
				RM.ic2_compressor(                  ST.make(Items.slime_ball, 9, 0), ST.make(MD.GaSu, "slimeBlock", 1, 0));
				RM.Compressor.addRecipe1(T, 16, 16, ST.make(Items.slime_ball, 9, W), ST.make(MD.GaSu, "slimeBlock", 1, 0));
				RM.Boxinator.addRecipe2(T, 16, 16,  ST.make(Items.slime_ball, 9, W), ST.tag(9), ST.make(MD.GaSu, "slimeBlock", 1, 0));
				RM.Unboxinator.addRecipe1(T, 16, 16,ST.make(MD.GaSu, "slimeBlock", 1, 0), ST.make(Items.slime_ball, 9, 0));
				RM.ic2_extractor(                   ST.make(MD.GaSu, "slimeBlock", 1, 0), ST.make(Items.slime_ball, 9, 0));
				CR.shapeless(                       ST.make(Items.slime_ball, 9, 0), CR.DEF, new Object[] {ST.make(MD.GaSu, "slimeBlock", 1, 0)});
			}
			
			RM.Boxinator.addRecipe2(T, 16, 16,      ST.make(Items.flint, 9, W), ST.tag(9), ST.make(MD.GaSu, "storage", 1, 0));
			RM.Unboxinator.addRecipe1(T, 16, 16,    ST.make(MD.GaSu, "storage", 1, 0), ST.make(Items.flint, 9, 0));
			RM.ic2_extractor(                       ST.make(MD.GaSu, "storage", 1, 0), ST.make(Items.flint, 9, 0));
			
			RM.Boxinator.addRecipe2(T, 16, 16,      ST.make(Items.carrot, 9, W), ST.tag(9), ST.make(MD.GaSu, "storage", 1, 1));
			RM.Unboxinator.addRecipe1(T, 16, 16,    ST.make(MD.GaSu, "storage", 1, 1), ST.make(Items.carrot, 9, 0));
			RM.ic2_extractor(                       ST.make(MD.GaSu, "storage", 1, 1), ST.make(Items.carrot, 9, 0));
		}
		if (MD.GaNe.mLoaded) {OUT.println("GT_Mod: Doing Recipes for Ganys Nether.");
			RM.mortarize(ST.make(MD.GaNe, "spectreWheatItem", 1, 0), ST.make(MD.GaNe, "spookyFlour", 1, 0));
			
			CR.remove(OP.nugget.mat(MT.Blaze, 1), NI, NI, OP.nugget.mat(MT.Blaze, 1), NI, NI, OP.nugget.mat(MT.Blaze, 1));
			
			RM.Mixer.addRecipe2(T, 16, 32, ST.make(MD.GaNe, "spookyFlour", 2, 0), ST.make(Blocks.sand, 1, W), ST.make(Blocks.soul_sand, 2, 0));
		}
		if (MD.GaEn.mLoaded) {OUT.println("GT_Mod: Doing Recipes for Ganys End.");
			//
		}
	}
}
