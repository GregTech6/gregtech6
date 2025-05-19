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
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static gregapi.data.CS.*;
import static gregapi.util.CR.DEF;

public class Compat_Recipes_BiomesOPlenty extends CompatMods {
	public Compat_Recipes_BiomesOPlenty(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing BoP Recipes.");
		RM.add_smelting(IL.BoP_Mud     .get(1), IL.BoP_Mud_Brick.get(1), T, F, T);
		
		RM.compactsmash (IL.BoP_Celestial.get(4), 4, IL.BoP_Celestial_Block.get(1));
		RM.compact      (IL.BoP_Flesh    .get(4), 4, IL.BoP_Flesh_Block.get(1));
		RM.compactsmash (IL.BoP_Mud_Brick.get(4), 4, IL.BoP_Mud_Bricks.get(1));
		RM.compactunpack(IL.BoP_Mud_Ball .get(4), 4, IL.BoP_Mud.get(1));
		RM.compact      (IL.BoP_Ashes    .get(4), 4, IL.BoP_Ashes_Block.get(1));
		
		CR.shapeless(IL.BoP_Mud_Ball.get(4), CR.DEF_NCC, new Object[] {IL.BoP_Mud});
		
		RM.biomass(ST.make(MD.BoP, "flowers", 16, W));
		RM.biomass(ST.make(MD.BoP, "flowers2", 16, W));
		RM.biomass(ST.make(MD.BoP, "plants", 16, W));
		RM.biomass(ST.make(MD.BoP, "foliage", 8, W));
		RM.biomass(ST.make(MD.BoP, "mushrooms", 16, W));
		RM.biomass(ST.make(MD.BoP, "coral1", 16, W));
		RM.biomass(ST.make(MD.BoP, "coral2", 16, W));
		RM.biomass(ST.make(MD.BoP, "lilyBop", 8, W));
		RM.biomass(IL.BoP_Pinecone.get(16));
		
		CR.remove(IL.BoP_Bone_Small.get(1));
		CR.remove(IL.BoP_Bone_Medium.get(1));
		CR.remove(IL.BoP_Bone_Large.get(1));
		CR.shaped(IL.Dye_Bonemeal.get(1), DEF, "h", "X", 'X', IL.BoP_Bone_Small);
		CR.shaped(IL.Dye_Bonemeal.get(1), DEF, "h", "X", 'X', IL.BoP_Bone_Medium);
		CR.shaped(IL.Dye_Bonemeal.get(1), DEF, "h", "X", 'X', IL.BoP_Bone_Large);
		CR.shapeless(OP.dust.mat(MT.White, 1), DEF, new Object[] {IL.BoP_Bone_Small});
		CR.shapeless(OP.dust.mat(MT.White, 2), DEF, new Object[] {IL.BoP_Bone_Medium});
		CR.shapeless(OP.dust.mat(MT.White, 4), DEF, new Object[] {IL.BoP_Bone_Large});
		RM.mortarize(2, IL.BoP_Bone_Small.get(1), IL.Dye_Bonemeal.get(2));
		RM.mortarize(4, IL.BoP_Bone_Medium.get(1), IL.Dye_Bonemeal.get(4));
		RM.mortarize(8, IL.BoP_Bone_Large.get(1), IL.Dye_Bonemeal.get(8));
		
		CR.shapeless(ST.make(MD.BoP, "dart", 8, 1), CR.DEF_NCC, new Object[] {OD.container1000poison, ST.make(MD.BoP, "dart", 1, 0), ST.make(MD.BoP, "dart", 1, 0), ST.make(MD.BoP, "dart", 1, 0), ST.make(MD.BoP, "dart", 1, 0), ST.make(MD.BoP, "dart", 1, 0), ST.make(MD.BoP, "dart", 1, 0), ST.make(MD.BoP, "dart", 1, 0), ST.make(MD.BoP, "dart", 1, 0)});
		CR.shapeless(ST.make(MD.BoP, "dart", 4, 1), CR.DEF_NCC, new Object[] {OD.container500poison , ST.make(MD.BoP, "dart", 1, 0), ST.make(MD.BoP, "dart", 1, 0), ST.make(MD.BoP, "dart", 1, 0), ST.make(MD.BoP, "dart", 1, 0)});
		CR.shapeless(ST.make(MD.BoP, "dart", 2, 1), CR.DEF_NCC, new Object[] {OD.container250poison , ST.make(MD.BoP, "dart", 1, 0), ST.make(MD.BoP, "dart", 1, 0)});
		CR.shapeless(ST.make(MD.BoP, "dart", 1, 1), CR.DEF_NCC, new Object[] {OD.itemPoison         , ST.make(MD.BoP, "dart", 1, 0)});
		RM.Bath         .addRecipe1(T, 16,  16, ST.make(MD.BoP, "dart", 1, 0), FL.Poison         .make(50), NF, ST.make(MD.BoP, "dart", 1, 1));
		RM.Bath         .addRecipe1(T, 16,  16, ST.make(MD.BoP, "dart", 1, 0), FL.Potion_Poison_2.make(50), NF, ST.make(MD.BoP, "dart", 1, 1));
		
		RM.smash(IL.BoP_Hard_Ice.get(1), OM.dust(MT.Ice, 2*U));
		RM.Squeezer     .addRecipe1(T, 16, 128, IL.BoP_Hard_Ice.get(1), NF, FL.Ice.make(2000), NI);
		RM.Juicer       .addRecipe1(T, 16, 128, IL.BoP_Hard_Ice.get(1), NF, FL.Ice.make(2000), NI);
		
		RM.Loom         .addRecipe2(T, 16, 16, ST.tag(0), ST.make(MD.BoP, "plants", 9, 7), ST.make(Blocks.wool, 1, 0));
		
		RM.Centrifuge   .addRecipe1(T, 16, 256, ST.make(MD.BoP, "hive", 1, 0), OM.dust(MT.WaxBee, U*4));
		RM.Centrifuge   .addRecipe1(T, 16, 256, ST.make(MD.BoP, "hive", 1, 2), OM.dust(MT.WaxBee, U*4));
		RM.Centrifuge   .addRecipe1(T, 16, 256, ST.make(MD.BoP, "hive", 1, 3), NF, FL.Honey.make(360), OM.dust(MT.WaxBee, U*4));
		RM.Squeezer     .addRecipe1(T, 16, 256, ST.make(MD.BoP, "hive", 1, 0), OM.dust(MT.WaxBee, U*4));
		RM.Squeezer     .addRecipe1(T, 16, 256, ST.make(MD.BoP, "hive", 1, 2), OM.dust(MT.WaxBee, U*4));
		RM.Squeezer     .addRecipe1(T, 16, 256, ST.make(MD.BoP, "hive", 1, 3), NF, FL.Honey.make(360), OM.dust(MT.WaxBee, U*4));
		RM.Juicer       .addRecipe1(T, 16, 256, ST.make(MD.BoP, "hive", 1, 0), OM.dust(MT.WaxBee, U*4));
		RM.Juicer       .addRecipe1(T, 16, 256, ST.make(MD.BoP, "hive", 1, 2), OM.dust(MT.WaxBee, U*4));
		RM.Juicer       .addRecipe1(T, 16, 256, ST.make(MD.BoP, "hive", 1, 3), NF, FL.Honey.make(360), OM.dust(MT.WaxBee, U*4));
		RM.mortarize(4, ST.make(MD.BoP, "hive", 1, 0), OM.dust(MT.WaxBee, U*4));
		RM.mortarize(4, ST.make(MD.BoP, "hive", 1, 2), OM.dust(MT.WaxBee, U*4));
		RM.mortarize(4, ST.make(MD.BoP, "hive", 1, 3), OM.dust(MT.WaxBee, U*4));
		RM.unpack(ST.make(MD.BoP, "hive", 1, 0), IL.BoP_Comb.get(4));
		RM.unpack(ST.make(MD.BoP, "hive", 1, 2), IL.BoP_Comb.get(4));
		RM.unpack(ST.make(MD.BoP, "hive", 1, 3), IL.BoP_HoneyComb.get(4));
		
		RM.mortarize(ST.make(MD.BoP, "mushrooms", 1, 0), IL.BoP_ShroomPowder.get(2));
		
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "mushrooms"   , 1, 0), IL.BoP_ShroomPowder.get(2));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "mushrooms"   , 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], IL.BoP_Dye_Blue.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "mushrooms"   , 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Lime], ST.make(Items.dye, 1, DYE_INDEX_Lime));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "mushrooms"   , 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Brown], IL.BoP_Dye_Brown.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "mushrooms"   , 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Black], IL.BoP_Dye_Black.get(1));
		
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "plants"      , 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Brown], IL.BoP_Dye_Brown.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "plants"      , 1,12), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Green], IL.Dye_Cactus.get(1));
		
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "moss"        , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Green], IL.BoP_Dye_Green.get(1));
		
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"     , 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Cyan], ST.make(Items.dye, 1, DYE_INDEX_Cyan));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"     , 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Black], IL.BoP_Dye_Black.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"     , 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], ST.make(Items.dye, 1, DYE_INDEX_LightBlue));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"     , 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange], ST.make(Items.dye, 1, DYE_INDEX_Orange));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"     , 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 1, DYE_INDEX_Pink));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"     , 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta], ST.make(Items.dye, 1, DYE_INDEX_Magenta));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"     , 1, 8), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"     , 1, 9), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], IL.BoP_Dye_White.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"     , 1,15), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray], ST.make(Items.dye, 1, DYE_INDEX_LightGray));
		
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"    , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 1, DYE_INDEX_Pink));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"    , 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], IL.BoP_Dye_White.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"    , 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange], ST.make(Items.dye, 1, DYE_INDEX_Orange));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"    , 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"    , 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], ST.make(Items.dye, 1, DYE_INDEX_Yellow));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"    , 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], IL.BoP_Dye_Blue.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"    , 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], ST.make(Items.dye, 1, DYE_INDEX_LightBlue));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"    , 1, 8), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
		
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "mushrooms"     , 1, 0), IL.BoP_ShroomPowder.get(2));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "mushrooms"     , 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], IL.BoP_Dye_Blue.get(1));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "mushrooms"     , 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Lime], ST.make(Items.dye, 1, DYE_INDEX_Lime));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "mushrooms"     , 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Brown], IL.BoP_Dye_Brown.get(1));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "mushrooms"     , 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Black], IL.BoP_Dye_Black.get(1));
		
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "plants"        , 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Brown], IL.BoP_Dye_Brown.get(1));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "plants"        , 1,12), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Green], IL.Dye_Cactus.get(1));
		
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "moss"          , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Green], IL.BoP_Dye_Green.get(1));
		
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"       , 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Cyan], ST.make(Items.dye, 1, DYE_INDEX_Cyan));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"       , 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Black], IL.BoP_Dye_Black.get(1));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"       , 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], ST.make(Items.dye, 1, DYE_INDEX_LightBlue));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"       , 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange], ST.make(Items.dye, 1, DYE_INDEX_Orange));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"       , 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 1, DYE_INDEX_Pink));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"       , 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta], ST.make(Items.dye, 1, DYE_INDEX_Magenta));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"       , 1, 8), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"       , 1, 9), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], IL.BoP_Dye_White.get(1));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers"       , 1,15), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray], ST.make(Items.dye, 1, DYE_INDEX_LightGray));
		
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"      , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 1, DYE_INDEX_Pink));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"      , 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], IL.BoP_Dye_White.get(1));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"      , 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange], ST.make(Items.dye, 1, DYE_INDEX_Orange));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"      , 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"      , 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], ST.make(Items.dye, 1, DYE_INDEX_Yellow));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"      , 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], IL.BoP_Dye_Blue.get(1));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"      , 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], ST.make(Items.dye, 1, DYE_INDEX_LightBlue));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.BoP, "flowers2"      , 1, 8), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
		
		
		RM.stonetypes(MT.STONES.Limestone, F, OP.rockGt.mat(MT.STONES.Limestone, 4), OP.blockDust.mat(MT.STONES.Limestone, 1)
		, ST.make(MD.BoP, "rocks", 1, 0)
		, NI
		, NI
		, NI
		, NI
		, ST.make(MD.BoP, "rocks", 1, 1)
		, NI
		, NI
		);
		
		RM.stonetypes(MT.STONES.Siltstone, F, OP.rockGt.mat(MT.STONES.Siltstone, 4), OP.blockDust.mat(MT.STONES.Siltstone, 1)
		, ST.make(MD.BoP, "rocks", 1, 2)
		, NI
		, NI
		, NI
		, NI
		, ST.make(MD.BoP, "rocks", 1, 3)
		, NI
		, NI
		);
		
		RM.stonetypes(MT.STONES.Shale, F, OP.rockGt.mat(MT.STONES.Shale, 4), OP.blockDust.mat(MT.STONES.Shale, 1)
		, ST.make(MD.BoP, "rocks", 1, 4)
		, NI
		, NI
		, NI
		, NI
		, ST.make(MD.BoP, "rocks", 1, 5)
		, NI
		, NI
		);
		
		
		if (ENABLE_ADDING_IC2_EXTRACTOR_RECIPES) {
		RM.ic2_extractor(ST.make(MD.BoP, "mushrooms"        , 1, 0), IL.BoP_ShroomPowder.get(2));
		RM.ic2_extractor(ST.make(MD.BoP, "mushrooms"        , 1, 2), IL.BoP_Dye_Blue.get(2));
		RM.ic2_extractor(ST.make(MD.BoP, "mushrooms"        , 1, 3), ST.make(Items.dye, 2, DYE_INDEX_Lime));
		RM.ic2_extractor(ST.make(MD.BoP, "mushrooms"        , 1, 4), IL.BoP_Dye_Brown.get(2));
		RM.ic2_extractor(ST.make(MD.BoP, "mushrooms"        , 1, 5), IL.BoP_Dye_Black.get(1));
		
		RM.ic2_extractor(ST.make(MD.BoP, "plants"       , 1, 7), IL.BoP_Dye_Brown.get(2));
		RM.ic2_extractor(ST.make(MD.BoP, "plants"       , 1,12), IL.Dye_Cactus.get(2));
		
		RM.ic2_extractor(ST.make(MD.BoP, "moss"         , 1, 0), IL.BoP_Dye_Green.get(2));
		
		RM.ic2_extractor(ST.make(MD.BoP, "flowers"      , 1, 1), ST.make(Items.dye, 2, DYE_INDEX_Cyan));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers"      , 1, 2), IL.BoP_Dye_Black.get(2));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers"      , 1, 4), ST.make(Items.dye, 2, DYE_INDEX_LightBlue));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers"      , 1, 5), ST.make(Items.dye, 2, DYE_INDEX_Orange));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers"      , 1, 6), ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers"      , 1, 7), ST.make(Items.dye, 2, DYE_INDEX_Magenta));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers"      , 1, 8), ST.make(Items.dye, 2, DYE_INDEX_Purple));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers"      , 1, 9), IL.BoP_Dye_White.get(2));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers"      , 1,15), ST.make(Items.dye, 2, DYE_INDEX_LightGray));
		
		RM.ic2_extractor(ST.make(MD.BoP, "flowers2"     , 1, 0), ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers2"     , 1, 1), IL.BoP_Dye_White.get(2));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers2"     , 1, 2), ST.make(Items.dye, 2, DYE_INDEX_Orange));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers2"     , 1, 3), ST.make(Items.dye, 2, DYE_INDEX_Purple));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers2"     , 1, 4), ST.make(Items.dye, 2, DYE_INDEX_Yellow));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers2"     , 1, 5), IL.BoP_Dye_Blue.get(2));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers2"     , 1, 7), ST.make(Items.dye, 2, DYE_INDEX_LightBlue));
		RM.ic2_extractor(ST.make(MD.BoP, "flowers2"     , 1, 8), ST.make(Items.dye, 2, DYE_INDEX_Red));
		}
	}
}
