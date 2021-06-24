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
import static gregapi.util.CR.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class Compat_Recipes_BiomesOPlenty extends CompatMods {
	public Compat_Recipes_BiomesOPlenty(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing BoP Recipes.");
		RM.add_smelting(IL.Mud_Ball    .get(1), IL.BoP_Mud_Brick.get(1), F, F, T);
		RM.add_smelting(IL.BoP_Mud_Ball.get(1), IL.BoP_Mud_Brick.get(1), F, F, T);
		
		RM.generify(IL.Mud_Ball.get(1), IL.BoP_Mud_Ball.get(1));
		RM.generify(IL.BoP_Mud_Ball.get(1), IL.Mud_Ball.get(1));
		
		RM.compactsmash(IL.BoP_Celestial.get(4), 4, IL.BoP_Celestial_Block.get(1));
		RM.compact     (IL.BoP_Flesh    .get(4), 4, IL.BoP_Flesh_Block.get(1));
		RM.compactsmash(IL.BoP_Mud_Brick.get(4), 4, IL.BoP_Mud_Bricks.get(1));
		RM.compact     (IL.BoP_Mud_Ball .get(4), 4, IL.BoP_Mud.get(1));
		RM.compact     (IL.BoP_Ashes    .get(4), 4, IL.BoP_Ashes_Block.get(1));
		
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
		RM.Mortar       .addRecipe1(T, 16, 16, IL.BoP_Bone_Small.get(1), IL.Dye_Bonemeal.get(2));
		RM.Mortar       .addRecipe1(T, 16, 32, IL.BoP_Bone_Medium.get(1), IL.Dye_Bonemeal.get(4));
		RM.Mortar       .addRecipe1(T, 16, 64, IL.BoP_Bone_Large.get(1), IL.Dye_Bonemeal.get(8));
		
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
		RM.Mortar       .addRecipe1(T, 16, 256, ST.make(MD.BoP, "hive", 1, 0), OM.dust(MT.WaxBee, U*4));
		RM.Mortar       .addRecipe1(T, 16, 256, ST.make(MD.BoP, "hive", 1, 2), OM.dust(MT.WaxBee, U*4));
		RM.Mortar       .addRecipe1(T, 16, 256, ST.make(MD.BoP, "hive", 1, 3), OM.dust(MT.WaxBee, U*4));
		
		RM.unpack(ST.make(MD.BoP, "hive", 1, 0), IL.BoP_Comb.get(4));
		RM.unpack(ST.make(MD.BoP, "hive", 1, 2), IL.BoP_Comb.get(4));
		RM.unpack(ST.make(MD.BoP, "hive", 1, 3), IL.BoP_HoneyComb.get(4));
		
		RM.Shredder.addRecipe1(T, 16, 16, ST.make(MD.BoP, "mushrooms"   , 1, 0), IL.BoP_ShroomPowder.get(2));
		
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
		
		
		
		RM.pulverizing(ST.make(MD.BoP, "mushrooms", 1, 0), IL.BoP_ShroomPowder.get(2));
		
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
