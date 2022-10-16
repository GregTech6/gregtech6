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

package gregtech.items;

import gregapi.data.*;
import gregapi.item.CreativeTab;
import gregapi.item.IItemRottable;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.item.multiitem.behaviors.*;
import gregapi.item.multiitem.food.FoodStat;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import static gregapi.data.CS.*;

public class MultiItemFood extends MultiItemRandom implements IItemRottable {
	public MultiItemFood(String aModID, String aUnlocalized) {
		super(aModID, aUnlocalized);
		setCreativeTab(new CreativeTab(getUnlocalizedName(), "GregTech: Nature & Foods", this, (short)12000));
	}

	@Override
	public void addItems() {
		int tLastID = 0;

		IL.Grass       .set(addItem(tLastID = 12000, "Grass"       , "Make 9 of this into a Bale in order to dry it", Behavior_FeedGrass.INSTANCE, OD.itemGrass         , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1)));
		IL.Grass_Dry   .set(addItem(tLastID = 12001, "Dry Grass"   , "Useful for making a simple Fire Starter"      , Behavior_FeedGrass.INSTANCE, OD.itemGrassDry      , TICKS_PER_SMELT / 2, TC.stack(TC.MESSIS, 1)));
		IL.Grass_Moldy .set(addItem(tLastID = 12002, "Moldy Grass" , ""                                                                          , OD.itemGrassMoldy    , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1)));
		IL.Grass_Rotten.set(addItem(tLastID = 12003, "Rotten Grass", ""                                                                          , OD.itemGrassRotten   , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1)));
		IL.Crop_Rye    .set(addItem(tLastID = 12004, "Rye"         , ""                                             , Behavior_FeedGrass.INSTANCE, "cropRye"            , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1)));
		IL.Crop_Oats   .set(addItem(tLastID = 12005, "Oats"        , ""                                             , Behavior_FeedGrass.INSTANCE, "cropOats"           , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1)));
		IL.Crop_Barley .set(addItem(tLastID = 12006, "Barley"      , ""                                             , Behavior_FeedGrass.INSTANCE, "cropBarley"         , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1)));
		IL.Crop_Rice   .set(addItem(tLastID = 12007, "Rice"        , ""                                             , Behavior_FeedGrass.INSTANCE, "cropRice"           , TICKS_PER_SMELT / 4, TC.stack(TC.MESSIS, 1)));
		RM.replicateOrganic( 1,  2, IL.Grass      .get(1));
		RM.replicateOrganic( 1,  3, IL.Crop_Wheat .get(1));
		RM.replicateOrganic( 1,  4, IL.Crop_Rye   .get(1));
		RM.replicateOrganic( 1,  5, IL.Crop_Oats  .get(1));
		RM.replicateOrganic( 1,  6, IL.Crop_Barley.get(1));
		RM.replicateOrganic( 1,  7, IL.Crop_Rice  .get(1));
		CR.shaped(IL.Bale           .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', OD.itemGrass);
		CR.shaped(IL.Bale_Dry       .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', OD.itemGrassDry);
		CR.shaped(IL.Bale_Moldy     .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', OD.itemGrassMoldy);
		CR.shaped(IL.Bale_Rotten    .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', OD.itemGrassRotten);
		CR.shaped(IL.Bale_Rye       .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', "cropRye");
		CR.shaped(IL.Bale_Oats      .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', "cropOats");
		CR.shaped(IL.Bale_Barley    .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', "cropBarley");
		CR.shaped(IL.Bale_Rice      .get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', "cropRice");
		CR.shapeless(IL.Grass       .get(1), CR.DEF_NCC, new Object[] {OD.itemGrassTall});
		CR.shapeless(IL.Grass       .get(9), CR.DEF_NCC, new Object[] {OD.baleGrass});
		CR.shapeless(IL.Grass_Dry   .get(9), CR.DEF_NCC, new Object[] {OD.baleGrassDry});
		CR.shapeless(IL.Grass_Moldy .get(9), CR.DEF_NCC, new Object[] {OD.baleGrassMoldy});
		CR.shapeless(IL.Grass_Rotten.get(9), CR.DEF_NCC, new Object[] {OD.baleGrassRotten});
		CR.shapeless(IL.Crop_Rye    .get(9), CR.DEF_NCC, new Object[] {"baleRye"});
		CR.shapeless(IL.Crop_Oats   .get(9), CR.DEF_NCC, new Object[] {"baleOats"});
		CR.shapeless(IL.Crop_Barley .get(9), CR.DEF_NCC, new Object[] {"baleBarley"});
		CR.shapeless(IL.Crop_Rice   .get(9), CR.DEF_NCC, new Object[] {"baleRice"});
		ItemsGT.addNEIRedirects(IL.Bale       .get(1), IL.Grass       .get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Dry   .get(1), IL.Grass_Dry   .get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Moldy .get(1), IL.Grass_Moldy .get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Rotten.get(1), IL.Grass_Rotten.get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Rye   .get(1), IL.Crop_Rye    .get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Oats  .get(1), IL.Crop_Oats   .get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Barley.get(1), IL.Crop_Barley .get(1));
		ItemsGT.addNEIRedirects(IL.Bale_Rice  .get(1), IL.Crop_Rice   .get(1));
		RM.DidYouKnow.addFakeRecipe(F, ST.array(ST.make(ToolsGT.sMetaTool, 1, ToolsGT.KNIFE, "Just cut Tall Grass with this"), ST.make(ToolsGT.sMetaTool, 1, ToolsGT.SENSE, "Or cut Tall Grass with this"), ST.make(Blocks.tallgrass, 1, 1)), ST.array(IL.Grass.get(1), IL.Bale.get(1)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		RM.DidYouKnow.addFakeRecipe(F, ST.array(IL.Bale.getWithName(1, "Put the Bale Outside")), ST.array(IL.Bale_Dry.getWithName(1, "When its dry Outside"), IL.Bale_Moldy.getWithName(1, "When its wet Outside"), IL.Bale_Rotten.getWithName(1, "When its wet Outside and more time passed"), IL.Grass_Dry.getWithName(1, "When its dry Outside"), IL.Grass_Moldy.getWithName(1, "When its wet Outside"), IL.Grass_Rotten.getWithName(1, "When its wet Outside and more time passed")), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		
		
		
		IL.Cerublossom     .set(addItem(tLastID = 12010, "Cerublossom"      , "Used for magical Purposes"              , new Behavior_Turn_Into(IL.ARS_Cerublossom ), IL.ARS_Cerublossom.exists() ? TD.Creative.HIDDEN : "flowerCerublossom", TC.stack(TC.HERBA, 1), TC.stack(TC.PRAECANTIO, 1), TC.stack(TC.LUX, 1)));
		IL.DesertNova      .set(addItem(tLastID = 12011, "Desert Nova"      , "Used for magical Purposes"              , new Behavior_Turn_Into(IL.ARS_DesertNova  ), IL.ARS_DesertNova .exists() ? TD.Creative.HIDDEN : "flowerDesertNova" , TC.stack(TC.HERBA, 1), TC.stack(TC.PRAECANTIO, 1), TC.stack(TC.LUX, 1)));
		RM.replicateOrganic( 2,  3, IL.ARS_Cerublossom.exists() ? IL.ARS_Cerublossom.get(1) : IL.Cerublossom.get(1));
		RM.replicateOrganic( 2,  4, IL.ARS_DesertNova .exists() ? IL.ARS_DesertNova .get(1) : IL.DesertNova .get(1));
		
		
		IL.Resin           .set(addItem(tLastID = 12050, "Rubber Resin"     , ""                                       , new Behavior_Turn_Into(IL.IC2_Resin       ), IL.IC2_Resin      .exists() ? TD.Creative.HIDDEN : OD.itemResin       , TC.stack(TC.LIMUS, 1), TICKS_PER_SMELT / 2));
		RM.replicateOrganic( 2,  5, IL.IC2_Resin.exists() ? IL.IC2_Resin.get(1) : IL.Resin.get(1));
		
		
		IL.Slimeball_Borax .set(addItem(tLastID = 12099, "Slimeball"        , "Borax mixed with Glue"                  , OD.slimeball, OD.slimeballBorax, TC.stack(TC.LIMUS, 2)));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.OREMATS.Borax), FL.Glue.make(250), NF, IL.Slimeball_Borax.get(1));
		
		
		IL.Remains_Plant   .set(addItem(tLastID = 12100, "Plant Remains"    , ""                                       , OD.itemPlantRemains, TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1)));
		IL.Remains_Fruit   .set(addItem(tLastID = 12101, "Fruit Remains"    , ""                                       , OD.itemPlantRemains, TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1)));
		IL.Remains_Veggie  .set(addItem(tLastID = 12102, "Vegetable Remains", ""                                       , OD.itemPlantRemains, TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1)));
		IL.Remains_Nut     .set(addItem(tLastID = 12103, "Nut Remains"      , ""                                       , OD.itemPlantRemains, TICKS_PER_SMELT / 2, TC.stack(TC.HERBA, 1)));
		
		
		IL.Bark_Dry        .set(addItem(tLastID = 12201, "Dry Bark"         , "Useful for making a simple Fire Starter", OD.itemBarkDry, TICKS_PER_SMELT / 4, TC.stack(TC.ARBOR, 1), new OreDictItemData(MT.Bark, U2)));
		RM.replicateOrganic( 2,  6, IL.Bark_Dry.get(1));
		
		
		IL.Mud_Ball        .set(addItem(tLastID = 12300, "Mud"              , ""                                       , OD.itemMud , TC.stack(TC.TERRA, 1)                                         )); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", last());
		IL.Clay_Ball_Brown .set(addItem(tLastID = 12310, "Brown Clay"       , "Contains small amounts of Lithium"      , OD.itemClay, TC.stack(TC.TERRA, 1), new OreDictItemData(MT.ClayBrown   , U))); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", last());
		IL.Clay_Ball_Red   .set(addItem(tLastID = 12311, "Red Clay"         , "Perfectly Balanced With No Exploits!"   , OD.itemClay, TC.stack(TC.TERRA, 1), new OreDictItemData(MT.ClayRed     , U))); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", last());
		IL.Clay_Ball_Yellow.set(addItem(tLastID = 12312, "Yellow Clay"      , "Bentonite Clay"                         , OD.itemClay, TC.stack(TC.TERRA, 1), new OreDictItemData(MT.Bentonite   , U))); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", last());
		IL.Clay_Ball_Blue  .set(addItem(tLastID = 12313, "Blue Clay"        , "Palygorskite Clay / Fullers Earth"      , OD.itemClay, TC.stack(TC.TERRA, 1), new OreDictItemData(MT.Palygorskite, U))); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", last());
		IL.Clay_Ball_White .set(addItem(tLastID = 12314, "White Clay"       , "Kaolinite Clay / Porcelain Clay"        , OD.itemClay, TC.stack(TC.TERRA, 1), new OreDictItemData(MT.Kaolinite   , U))); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", last());
		CR.remove(ST.make(Items.clay_ball, 1, 0), ST.make(Items.clay_ball, 1, 0), NI, ST.make(Items.clay_ball, 1, 0), ST.make(Items.clay_ball, 1, 0));
		RM.generify(IL.Clay_Ball_Brown .get(1), ST.make(Items.clay_ball, 1, 0));
		RM.generify(IL.Clay_Ball_Red   .get(1), ST.make(Items.clay_ball, 1, 0));
		RM.generify(IL.Clay_Ball_Yellow.get(1), ST.make(Items.clay_ball, 1, 0));
		RM.generify(IL.Clay_Ball_Blue  .get(1), ST.make(Items.clay_ball, 1, 0));
		RM.generify(IL.Clay_Ball_White .get(1), ST.make(Items.clay_ball, 1, 0));
		RM.add_smelting(IL.Clay_Ball_Brown .get(1), ST.make(Items.brick, 1, 0), F, F, T);
		RM.add_smelting(IL.Clay_Ball_Red   .get(1), ST.make(Items.brick, 1, 0), F, F, T);
		RM.add_smelting(IL.Clay_Ball_Yellow.get(1), ST.make(Items.brick, 1, 0), F, F, T);
		RM.add_smelting(IL.Clay_Ball_Blue  .get(1), ST.make(Items.brick, 1, 0), F, F, T);
		RM.add_smelting(IL.Clay_Ball_White .get(1), ST.make(Items.brick, 1, 0), F, F, T);
		CR.shaped    (OP.plate.mat(MT.Clay         , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', Items.clay_ball);
		CR.shaped    (OP.plate.mat(MT.ClayBrown    , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', IL.Clay_Ball_Brown);
		CR.shaped    (OP.plate.mat(MT.ClayRed      , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', IL.Clay_Ball_Red);
		CR.shaped    (OP.plate.mat(MT.Bentonite    , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', IL.Clay_Ball_Yellow);
		CR.shaped    (OP.plate.mat(MT.Palygorskite , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', IL.Clay_Ball_Blue);
		CR.shaped    (OP.plate.mat(MT.Kaolinite    , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', IL.Clay_Ball_White);
		CR.shaped    (ST.make(BlocksGT.Diggables, 1, 0), CR.DEF_NCC, "XX", "XX", 'X', IL.Mud_Ball);
		CR.shaped    (ST.make(BlocksGT.Diggables, 1, 1), CR.DEF_NCC, "XX", "XX", 'X', IL.Clay_Ball_Brown);
		CR.shaped    (ST.make(BlocksGT.Diggables, 1, 3), CR.DEF_NCC, "XX", "XX", 'X', IL.Clay_Ball_Red);
		CR.shaped    (ST.make(BlocksGT.Diggables, 1, 4), CR.DEF_NCC, "XX", "XX", 'X', IL.Clay_Ball_Yellow);
		CR.shaped    (ST.make(BlocksGT.Diggables, 1, 5), CR.DEF_NCC, "XX", "XX", 'X', IL.Clay_Ball_Blue);
		CR.shaped    (ST.make(BlocksGT.Diggables, 1, 6), CR.DEF_NCC, "XX", "XX", 'X', IL.Clay_Ball_White);
		CR.shaped    (ST.make(Blocks.clay       , 1, 0), CR.DEF_NCC, "XX", "XX", 'X', Items.clay_ball);
		CR.shapeless (IL.Mud_Ball               .get(4), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.Diggables, 1, 0)});
		CR.shapeless (IL.Clay_Ball_Brown        .get(4), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.Diggables, 1, 1)});
		CR.shapeless (IL.Clay_Ball_Red          .get(4), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.Diggables, 1, 3)});
		CR.shapeless (IL.Clay_Ball_Yellow       .get(4), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.Diggables, 1, 4)});
		CR.shapeless (IL.Clay_Ball_Blue         .get(4), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.Diggables, 1, 5)});
		CR.shapeless (IL.Clay_Ball_White        .get(4), CR.DEF_NCC, new Object[] {ST.make(BlocksGT.Diggables, 1, 6)});
		CR.shapeless (ST.make(Items.clay_ball   , 4, 0), CR.DEF_NCC, new Object[] {Blocks.clay});
		RM.packunpack(IL.Mud_Ball               .get(4), ST.make(BlocksGT.Diggables, 1, 0));
		RM.packunpack(IL.Clay_Ball_Brown        .get(4), ST.make(BlocksGT.Diggables, 1, 1));
		RM.packunpack(IL.Clay_Ball_Red          .get(4), ST.make(BlocksGT.Diggables, 1, 3));
		RM.packunpack(IL.Clay_Ball_Yellow       .get(4), ST.make(BlocksGT.Diggables, 1, 4));
		RM.packunpack(IL.Clay_Ball_Blue         .get(4), ST.make(BlocksGT.Diggables, 1, 5));
		RM.packunpack(IL.Clay_Ball_White        .get(4), ST.make(BlocksGT.Diggables, 1, 6));
		RM.packunpack(ST.make(Items.clay_ball   , 4, 0), ST.make(Blocks.clay       , 1, 0));
		RM.RollingMill.addRecipe1(T, 16, 32, ST.make(Items.clay_ball   , 1, 0), OP.plate.mat(MT.Clay        , 1));
		RM.RollingMill.addRecipe1(T, 16, 32, IL.Clay_Ball_Brown        .get(1), OP.plate.mat(MT.ClayBrown   , 1));
		RM.RollingMill.addRecipe1(T, 16, 32, IL.Clay_Ball_Red          .get(1), OP.plate.mat(MT.ClayRed     , 1));
		RM.RollingMill.addRecipe1(T, 16, 32, IL.Clay_Ball_Yellow       .get(1), OP.plate.mat(MT.Bentonite   , 1));
		RM.RollingMill.addRecipe1(T, 16, 32, IL.Clay_Ball_Blue         .get(1), OP.plate.mat(MT.Palygorskite, 1));
		RM.RollingMill.addRecipe1(T, 16, 32, IL.Clay_Ball_White        .get(1), OP.plate.mat(MT.Kaolinite   , 1));
		RM.Compressor .addRecipe1(T, 16, 32, IL.Mud_Ball               .get(4), ST.make(BlocksGT.Diggables, 1, 0));
		RM.Compressor .addRecipe1(T, 16, 32, ST.make(Items.clay_ball   , 4, 0), ST.make(Blocks.clay       , 1, 0));
		RM.Compressor .addRecipe1(T, 16, 32, IL.Clay_Ball_Brown        .get(4), ST.make(BlocksGT.Diggables, 1, 1));
		RM.Compressor .addRecipe1(T, 16, 32, IL.Clay_Ball_Red          .get(4), ST.make(BlocksGT.Diggables, 1, 3));
		RM.Compressor .addRecipe1(T, 16, 32, IL.Clay_Ball_Yellow       .get(4), ST.make(BlocksGT.Diggables, 1, 4));
		RM.Compressor .addRecipe1(T, 16, 32, IL.Clay_Ball_Blue         .get(4), ST.make(BlocksGT.Diggables, 1, 5));
		RM.Compressor .addRecipe1(T, 16, 32, IL.Clay_Ball_White        .get(4), ST.make(BlocksGT.Diggables, 1, 6));
		RM.Compressor .addRecipe1(T, 16, 32, OM.dust(MT.Clay           , 4* U), ST.make(Blocks.clay       , 1, 0));
		RM.Compressor .addRecipe1(T, 16, 32, OM.dust(MT.ClayBrown      , 4* U), ST.make(BlocksGT.Diggables, 1, 1));
		RM.Compressor .addRecipe1(T, 16, 32, OM.dust(MT.ClayRed        , 4* U), ST.make(BlocksGT.Diggables, 1, 3));
		RM.Compressor .addRecipe1(T, 16, 32, OM.dust(MT.Bentonite      , 4* U), ST.make(BlocksGT.Diggables, 1, 4));
		RM.Compressor .addRecipe1(T, 16, 32, OM.dust(MT.Palygorskite   , 4* U), ST.make(BlocksGT.Diggables, 1, 5));
		RM.Compressor .addRecipe1(T, 16, 32, OM.dust(MT.Kaolinite      , 4* U), ST.make(BlocksGT.Diggables, 1, 6));
		RM.Mortar     .addRecipe1(T, 16, 16, ST.make(Items.clay_ball   , 1, W), OM.dust(MT.Clay));
		RM.Mortar     .addRecipe1(T, 16, 16, IL.Clay_Ball_Brown        .get(1), OM.dust(MT.ClayBrown));
		RM.Mortar     .addRecipe1(T, 16, 16, IL.Clay_Ball_Red          .get(1), OM.dust(MT.ClayRed));
		RM.Mortar     .addRecipe1(T, 16, 16, IL.Clay_Ball_Yellow       .get(1), OM.dust(MT.Bentonite));
		RM.Mortar     .addRecipe1(T, 16, 16, IL.Clay_Ball_Blue         .get(1), OM.dust(MT.Palygorskite));
		RM.Mortar     .addRecipe1(T, 16, 16, IL.Clay_Ball_White        .get(1), OM.dust(MT.Kaolinite));
		RM.Mortar     .addRecipe1(T, 16, 64, ST.make(Blocks.clay       , 1, W), OM.dust(MT.Clay, U*4));
		RM.Mortar     .addRecipe1(T, 16, 64, ST.make(BlocksGT.Diggables, 1, 1), OM.dust(MT.ClayBrown, U*4));
		RM.Mortar     .addRecipe1(T, 16, 64, ST.make(BlocksGT.Diggables, 1, 3), OM.dust(MT.ClayRed, U*4));
		RM.Mortar     .addRecipe1(T, 16, 64, ST.make(BlocksGT.Diggables, 1, 4), OM.dust(MT.Bentonite, U*4));
		RM.Mortar     .addRecipe1(T, 16, 64, ST.make(BlocksGT.Diggables, 1, 5), OM.dust(MT.Palygorskite, U*4));
		RM.Mortar     .addRecipe1(T, 16, 64, ST.make(BlocksGT.Diggables, 1, 6), OM.dust(MT.Kaolinite, U*4));
		for (FluidStack tWater : FL.waters(125, 100)) {
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.Ceramic      ), FL.mul(tWater, 5), NF, ST.make(Items.clay_ball, 1, 0));
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.Clay         ),        tWater    , NF, ST.make(Items.clay_ball, 1, 0));
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.ClayRed      ),        tWater    , NF, IL.Clay_Ball_Red.get(1));
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.ClayBrown    ),        tWater    , NF, IL.Clay_Ball_Brown.get(1));
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.Bentonite    ),        tWater    , NF, IL.Clay_Ball_Yellow.get(1));
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.Palygorskite ),        tWater    , NF, IL.Clay_Ball_Blue.get(1));
		RM.Bath       .addRecipe1(T,  0, 16, OM.dust(MT.Kaolinite    ),        tWater    , NF, IL.Clay_Ball_White.get(1));
		}
		
		IL.Comb_Honey   .set(addItem(tLastID = 30000, "Honey Comb"   , "", OD.beeComb, OD.materialHoneycomb, "foodFilledhoneycomb", TC.stack(TC.LIMUS, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.SANO, 1), new FoodStat( 1, 0.1F, 20, C+37, 0.50F,  0,  0,  0, 20,  0, EnumAction.eat, null, F, T, F, T))); Sandwiches.INGREDIENTS.put(last(), (byte)22);
		IL.Comb_Water   .set(addItem(tLastID = 30001, "Water Comb"   , "", OD.beeComb, TC.stack(TC.AQUA, 2), new FoodStat( 1, 0.1F, 20, C+37, 0.50F,  0,  0,  0, 20,  0, EnumAction.eat, null, F, T, F, T))); Sandwiches.INGREDIENTS.put(last(), (byte)22);
		IL.Comb_Magic   .set(addItem(tLastID = 30002, "Magic Comb"   , "", OD.beeComb, TC.stack(TC.PRAECANTIO, 2)));
		IL.Comb_Nether  .set(addItem(tLastID = 30003, "Nether Comb"  , "", OD.beeComb, TC.stack(TC.IGNIS, 2)));
		IL.Comb_End     .set(addItem(tLastID = 30004, "End Comb"     , "", OD.beeComb, TC.stack(TC.ALIENIS, 2)));
		IL.Comb_Rock    .set(addItem(tLastID = 30005, "Rock Comb"    , "", OD.beeComb, TC.stack(TC.TERRA, 2)));
		IL.Comb_Jungle  .set(addItem(tLastID = 30006, "Jungle Comb"  , "", OD.beeComb, TC.stack(TC.HERBA, 2), new FoodStat( 1, 0.1F, 20, C+37, 0.50F,  0,  0,  0, 20,  0, EnumAction.eat, null, F, T, F, T))); Sandwiches.INGREDIENTS.put(last(), (byte)22);
		IL.Comb_Frozen  .set(addItem(tLastID = 30007, "Frozen Comb"  , "", OD.beeComb, TC.stack(TC.GELUM, 2)));
		IL.Comb_Shroom  .set(addItem(tLastID = 30008, "Shroomy Comb" , "", OD.beeComb, TC.stack(TC.VITIUM, 1), TC.stack(TC.HERBA, 1), new FoodStat( 1, 0.1F, 20, C+37, 0.50F,  0,  0,  0, 20,  0, EnumAction.eat, null, F, T, F, T))); Sandwiches.INGREDIENTS.put(last(), (byte)22);
		IL.Comb_Sandy   .set(addItem(tLastID = 30009, "Sandy Comb"   , "", OD.beeComb, TC.stack(TC.TERRA, 1), TC.stack(TC.IGNIS, 1)));
		
		IL.Comb_Clay    .set(addItem(tLastID = 30100, "Clay Comb"    , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.TERRA, 1), TC.stack(TC.AQUA, 1)));
		IL.Comb_Sticky  .set(addItem(tLastID = 30101, "Sticky Comb"  , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.LIMUS, 2)));
		IL.Comb_Royal   .set(addItem(tLastID = 30102, "Royal Comb"   , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.LIMUS, 1), TC.stack(TC.SANO, 1), new FoodStat( 1, 0.1F, 20, C+37, 0.50F,  0,  0,  0, 20,  0, EnumAction.eat, null, F, T, F, T))); Sandwiches.INGREDIENTS.put(last(), (byte)22);
		IL.Comb_Soul    .set(addItem(tLastID = 30103, "Soul Comb"    , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.SPIRITUS, 2)));
		IL.Comb_Amnesic .set(addItem(tLastID = 30104, "Amnesic Comb" , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.LIMUS, 1), TC.stack(TC.STRONTIO, 1)));
		IL.Comb_Military.set(addItem(tLastID = 30105, "Military Comb", "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.TELUM, 2)));
		
		IL.Comb_Pyro    .set(addItem(tLastID = 30200, "Pyro Comb"    , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.IGNIS, 2)));
		IL.Comb_Cryo    .set(addItem(tLastID = 30201, "Cryo Comb"    , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.GELUM, 2)));
		IL.Comb_Aero    .set(addItem(tLastID = 30202, "Aero Comb"    , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.AER  , 2)));
		IL.Comb_Tera    .set(addItem(tLastID = 30203, "Tera Comb"    , "", OD.beeComb, OD.beeCombCrossbred, TC.stack(TC.TERRA, 2)));
		
		
		
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Honey   .get(1), NF   , FL.Honey                                .make( 100), OM.dust(MT.WaxBee)                , IL.FR_Propolis.get(1));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Water   .get(1), NF   , FL.Water                                .make(1000), OM.dust(MT.WaxBee)                );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Magic   .get(1), NF   , FL.Ambrosia                             .make( 100), OM.dust(MT.WaxMagic)              );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Nether  .get(1), NF   , FL.Blaze                                .make(   L), OM.dust(MT.WaxRefractory)         );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000,  1000}           , IL.Comb_End     .get(1), NF   , FL.Dragon_Breath                        .make( 125), OM.dust(MT.Endstone)              , IL.FR_Propolis_Pulsating.get(1), IL.EtFu_Chorus_Fruit.get(1));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Rock    .get(1), NF   , FL.Concrete                             .make(   L), OM.dust(MT.Stone)                 );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Jungle  .get(1), NF   , MT.Chocolate                          .liquid(U, T), OM.dust(MT.Cocoa)                 , IL.FR_Propolis_Silky.get(1, ST.make(Items.string, 1, 0)));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Frozen  .get(1), NF   , FL.Ice                                  .make(1000), OM.dust(MT.Ice)                   );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] { 6000,  6000}                  , IL.Comb_Shroom  .get(1), NF   , FL.Soup_Mushroom                        .make(1000), ST.make(Blocks.red_mushroom_block, 1, 0), ST.make(Blocks.brown_mushroom_block, 1, 0));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Sandy   .get(1), NF   , FL.Juice_Cactus                         .make( 100), ST.make(Blocks.sand, 1, 0)        );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] { 2000,2000,2000,2000,2000,2000}, IL.Comb_Clay    .get(1), NF   , FL.Concrete                             .make(   L), OM.dust(MT.Clay)                  , OM.dust(MT.ClayBrown), OM.dust(MT.ClayRed), OM.dust(MT.Bentonite), OM.dust(MT.Palygorskite), OM.dust(MT.Kaolinite));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  3000}                  , IL.Comb_Sticky  .get(1), NF   , FL.Latex                                .make(   L), OM.dust(MT.WaxBee)                , IL.FR_Propolis_Sticky.get(1, IL.IC2_Resin.get(1, IL.Resin.get(1))));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000}                         , IL.Comb_Royal   .get(1), ZL_FS, FL.array(FL.Honey.make(50), FL.RoyalJelly.make(10)), OM.dust(MT.WaxBee)                );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  9000}                  , IL.Comb_Soul    .get(1), NF   , FL.Oil_Soulsand                         .make(  50), OM.dust(MT.WaxSoulful)            , OM.dust(MT.SoulSand));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Amnesic .get(1), NF   , FL                                      .lube(1000), OM.dust(MT.WaxAmnesic)            );
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,   500,   500,   250}    , IL.Comb_Military.get(1), NF   , FL.Potion_Harm_1                        .make(  50), OM.dust(MT.Bone)                  , ST.make(Items.bone, 1, 0), ST.make(Items.rotten_flesh, 1, 0), ST.make(Items.spider_eye, 1, 0));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Pyro    .get(1), NF   , FL.Blaze                                .make( L/2), OM.dust(MT.Blaze, U9)             , OP.stick.mat(MT.Blaze, 1));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Cryo    .get(1), NF   , FL.Ice                                  .make( 500), OM.dust(MT.Blizz, U9)             , OP.stick.mat(MT.Blizz, 1));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Aero    .get(1), NF   , FL.Dragon_Breath                        .make(  50), OM.dust(MT.Blitz, U9)             , OP.stick.mat(MT.Blitz, 1));
		RM.Centrifuge.addRecipe1(T, 16, 64, new long[] {10000,  1000}                  , IL.Comb_Tera    .get(1), NF   , FL.Concrete                             .make(   L), OM.dust(MT.Basalz, U9)            , OP.stick.mat(MT.Basalz, 1));
		
		
		IL.Food_Lemon                          .set(addItem(tLastID =     0, "Lemon"                                    , "Don't make Lemonade"         , "cropLemon"                   , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   4,   0, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_CONDUCTIVE, 300, 0, 70), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Lemon_Sliced                   .set(addItem(tLastID =     1, "Lemon Slice"                              , "Ideal to put on your Drink"                                  , new FoodStat( 0, 0.150F,   0, C+36,  0.30F,   0,   0,   0,   1,   0, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_CONDUCTIVE, 300, 0, 70), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)28);
		RM.replicateOrganic( 3,  4, IL.Food_Lemon.get(1));
		CR.shaped(IL.Food_Lemon_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropLemon");


		IL.Food_Tomato                         .set(addItem(tLastID =    10, "Tomato"                                   , "Solid Ketchup"               , "cropTomato"                  , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   4,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Tomato_Sliced                  .set(addItem(tLastID =    11, "Tomato Slice"                             , "Solid Ketchup"                                               , new FoodStat( 0, 0.150F,   0, C+36,  0.30F,   0,   0,   0,   1,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)36);
		RM.replicateOrganic( 4,  5, IL.Food_Tomato.get(1));
		CR.shaped(IL.Food_Tomato_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropTomato");
		
		
		IL.Food_MTomato                        .set(addItem(tLastID =    20, "Maxim Tomato"                             , "Ten Hearts in one Tomato"    , "cropTomato"                  , new FoodStat( 9, 1.000F,  50, C+36,  0.30F,   0,   0,   0,  10,   0, EnumAction.eat, null                                 , F, T, F, T, Potion.regeneration.id, 60, 4, 100), TC.stack(TC.MESSIS, 1), TC.stack(TC.SANO, 3), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 4,  6, IL.Food_MTomato.get(1));
		RM.food_can(IL.Food_MTomato.get(1),10, "Canned Max Tomato", IL.CANS_VEGGIE);
		
		
		IL.Food_Onion                          .set(addItem(tLastID =    30, "Onion"                                    , "Taking over the whole Taste" , "cropOnion"                   , new FoodStat( 1, 1.200F,   0, C+36,  0.30F,   0,   0,   0,   4,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Onion_Sliced                   .set(addItem(tLastID =    31, "Onion Slice"                              , "ONIONS, UNITE!"                                              , new FoodStat( 0, 0.300F,   0, C+36,  0.30F,   0,   0,   0,   1,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)31);
		RM.replicateOrganic( 4,  7, IL.Food_Onion.get(1));
		CR.shaped(IL.Food_Onion_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropOnion");
		
		
		IL.Food_Cucumber                       .set(addItem(tLastID =    40, "Cucumber"                              , "Not a Sea Cucumber!", "cropCucumber"                            , new FoodStat( 1, 1.200F,   0, C+36,  0.30F,   0,   0,   0,   4,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Cucumber_Sliced                .set(addItem(tLastID =    41, "Cucumber Slice"                        , "QUEWWWCUMMMBURRR!!!"                                            , new FoodStat( 0, 0.300F,   0, C+36,  0.30F,   0,   0,   0,   1,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)23);
		IL.Food_Pickle                         .set(addItem(tLastID =    42, "Pickle"                                , "Not a Sea Pickle! Not Rick either!", "cropPickle", "foodPickles", new FoodStat( 1, 1.200F,   0, C+36,  0.30F,   4,   0,   0,   4,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.VENENUM, 1)));
		IL.Food_Pickle_Sliced                  .set(addItem(tLastID =    43, "Pickle Slice"                          , "You seem to be in a Pickle."                                    , new FoodStat( 0, 0.300F,   0, C+36,  0.30F,   1,   0,   0,   1,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)41);
		RM.replicateOrganic( 4,  8, IL.Food_Cucumber.get(1));
		RM.replicateOrganic( 4, 13, IL.Food_Pickle.get(1));
		CR.shaped(IL.Food_Cucumber_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropCucumber");
		CR.shaped(IL.Food_Pickle_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropPickle");
		CR.shaped(IL.Food_Pickle_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "foodPickles");
		
		
		IL.Food_Chili_Pepper                   .set(addItem(tLastID =    50, "Chili Pepper"                             , "It is red and hot"           , "cropChilipepper"             , new FoodStat( 1, 1.200F, -10, C+40,  0.50F,   0,   0,  10,   0,   0, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_FLAMMABLE, 300, 0, 70, Potion.confusion.id, 200, 1, 40), TC.stack(TC.MESSIS, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)20);
		RM.replicateOrganic( 4,  9, IL.Food_Chili_Pepper.get(1));
		
		
		IL.Food_Grapes_Green                   .set(addItem(tLastID =    60, "Green Grapes"                             , "Source of Wine"              , "cropGrapeGreen"              , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Raisins_Green                  .set(addItem(tLastID =    61, "Green Raisins"                            , "Dried Grapes"                , "foodRaisins"                 , new FoodStat( 2, 0.600F,   0, C+37,  0.20F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 3,  5, IL.Food_Grapes_Green.get(1));
		
		IL.Food_Grapes_White                   .set(addItem(tLastID =    63, "White Grapes"                             , "Source of Wine"              , "cropGrapeWhite"              , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Raisins_White                  .set(addItem(tLastID =    64, "White Raisins"                            , "Dried Grapes"                , "foodRaisins"                 , new FoodStat( 2, 0.600F,   0, C+37,  0.20F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 3,  6, IL.Food_Grapes_White.get(1));
		
		IL.Food_Grapes_Red                     .set(addItem(tLastID =    66, "Red Grapes"                               , "Source of Wine"              , "cropGrapeRed"                , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Raisins_Red                    .set(addItem(tLastID =    67, "Red Raisins"                              , "Dried Grapes"                , "foodRaisins"                 , new FoodStat( 2, 0.600F,   0, C+37,  0.20F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 3,  7, IL.Food_Grapes_Red.get(1));
		
		IL.Food_Grapes_Purple                  .set(addItem(tLastID =    70, "Purple Grapes"                            , "Source of Wine"              , "cropGrapePurple"             , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Raisins_Purple                 .set(addItem(tLastID =    71, "Purple Raisins"                           , "Dried Grapes"                , "foodRaisins"                 , new FoodStat( 2, 0.600F,   0, C+37,  0.20F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 3,  8, IL.Food_Grapes_Purple.get(1));
		
		IL.Food_Raisins_Chocolate              .set(addItem(tLastID =    72, "Chocolate Raisins"                        , "Dried Grapes coated in Chocolate", "foodChocolateraisins"    , new FoodStat( 3, 1.200F,   0, C+37,  0.20F,   0,   0,   0,  40,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.SANO, 1)));
		
		
		IL.Food_Carrot                         .set(ST.make(Items.carrot, 1, 0)); FoodsGT.put(ST.make(Items.carrot, 1, W), 0, 0, 0, 8, 0);//                                            , new FoodStat( 5, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1), Behavior_FeedPig.INSTANCE));
		IL.Food_Carrot_Sliced                  .set(addItem(tLastID =    81, "Carrot Slice"                             , "Sliced Goku"                                                 , new FoodStat( 0, 0.300F,   0, C+37,  0.30F,   0,   0,   0,   2,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)18);
		RM.replicateOrganic( 4,  9, IL.Food_Carrot.get(1));
		CR.shaped(IL.Food_Carrot_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropCarrot");
		
		
		IL.Food_Potato_Poisonous               .set(ST.make(Items.poisonous_potato, 1, 0)); FoodsGT.put(ST.make(Items.poisonous_potato, 1, W), 0, 0, 0, 4, 0);
		RM.replicateOrganic( 4, 11, IL.Food_Potato_Poisonous.get(1));
		
		
		IL.Food_Potato                         .set(ST.make(Items.potato, 1, 0)); FoodsGT.put(ST.make(Items.potato, 1, W), 0, 0, 0, 4, 0);
		IL.Food_Potato_On_Stick                .set(addItem(tLastID = 32700, "Potato on a Stick"        , "Totally looks like a Crab Claw", new OreDictItemData(MT.Potato,U,ANY.Wood,U2), new FoodStat( 1, 0.600F,   0, C+37,  0.30F,   0,   0,   0,   4,   0, EnumAction.eat, IL.Stick.get(1), F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.ARBOR, 1)));
		RM.replicateOrganic( 4, 10, IL.Food_Potato.get(1));
		CR.shapeless(IL.Food_Potato_On_Stick.get(1), CR.DEF, new Object[] {OP.stick.dat(ANY.Wood), "cropPotato"});
		
		
		IL.Food_Potato_Baked                   .set(ST.make(Items.baked_potato, 1, 0)); FoodsGT.put(ST.make(Items.baked_potato, 1, W), 0, 0, 0, 4, 0);
		IL.Food_Potato_On_Stick_Roasted        .set(addItem(tLastID = 32701, "Roasted Potato on a Stick", "Still looks like a Crab Claw"  , new OreDictItemData(MT.Potato,U,ANY.Wood,U2), new FoodStat( 5, 1.200F,   0, C+38,  0.50F,   0,   0,   0,   4,   0, EnumAction.eat, IL.Stick.get(1), F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.ARBOR, 1), TC.stack(TC.IGNIS, 1)));
		RM.add_smelting(IL.Food_Potato_On_Stick.get(1), IL.Food_Potato_On_Stick_Roasted.get(1), F, T, F);
		CR.shapeless(IL.Food_Potato_On_Stick_Roasted.get(1), CR.DEF, new Object[] {OP.stick.dat(ANY.Wood), IL.Food_Potato_Baked});
		for (OreDictMaterial tMat : ANY.Wood.mToThis) {ItemStack tStick = OP.stick.mat(tMat, 1); if (ST.valid(tStick))
		RM.Boxinator.addRecipe2(T, 16, 16, IL.Food_Potato_Baked.get(1), tStick, IL.Food_Potato_On_Stick_Roasted.get(1));
		}
		
		IL.Food_Fries_Raw                      .set(addItem(tLastID =  8000, "Potato Strips"                            , "Long Potatoes"                                               , new FoodStat( 1, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   5,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), new OreDictItemData(MT.Potato, U))); setFluidContainerStats(tLastID, 0, 8);
		IL.Food_Fries                          .set(addItem(tLastID =  8010, "Fries"                                    , "Not to be confused with that Futurama Guy", "foodFries"      , new FoodStat( 7, 1.200F,   0, C+38,  0.50F,   0,   0,  10,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.IGNIS, 1)));             setFluidContainerStats(tLastID, 0, 8);
		IL.Food_Fries_Packaged                 .set(addItem(tLastID =  8011, "Fries"                                    , "Ketchup not included"                                        , new FoodStat( 7, 1.200F,   0, C+38,  0.50F,   0,   0,  10,  10,   0, EnumAction.eat, OP.scrapGt.mat(MT.Paper, 16)         , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.IGNIS, 1)));
		RM.Bath.addRecipe1(T,  0,   16, IL.Food_Fries_Raw.get(1), MT.FryingOilHot.liquid(U/100, T), NF, IL.Food_Fries.get(1));
		CR.shaped(IL.Food_Fries_Raw.get(1), CR.DEF_NCC, "k", "X", 'X', "cropPotato");
		CR.shapeless(IL.Food_Fries_Packaged.get(1), CR.DEF_NCC, new Object[] {OP.plateDouble.dat(MT.Paper), IL.Food_Fries});
		RM.Boxinator.addRecipe2(T, 16, 16, IL.Food_Fries.get(1), OP.plateDouble.mat(MT.Paper, 1), IL.Food_Fries_Packaged.get(1));
		RM.Unboxinator.addRecipe1(T, 16, 16, IL.Food_Fries_Packaged.get(1), IL.Food_Fries.get(1), OP.scrapGt.mat(MT.Paper, 16));
		
		
		IL.Food_PotatoChips_Raw                .set(addItem(tLastID =  9000, "Potato Chips (Raw)"                       , "Just like a Potato"                                          , new FoodStat( 1, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), new OreDictItemData(MT.Potato, U))); setFluidContainerStats(tLastID, 0, 8);
		IL.Food_PotatoChips                    .set(addItem(tLastID =  9010, "Potato Chips"                             , "Crunchy"                                                     , new FoodStat( 7, 1.200F,   0, C+37,  0.10F,   0,   0,  10,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.IGNIS, 1)));             setFluidContainerStats(tLastID, 0, 8);
		IL.Food_PotatoChips_Packaged           .set(addItem(tLastID =  9011, "Bag of Potato Chips"                      , "Full of delicious Air"                                       , new FoodStat( 7, 1.200F,   0, C+37,  0.10F,   0,   0,  10,  10,   0, EnumAction.eat, OP.scrapGt.mat(MT.Al, 2)             , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.IGNIS, 1)));
		RM.add_smelting(IL.Food_PotatoChips_Raw.get(1), IL.Food_PotatoChips.get(1), F, T, F);
		CR.shaped(IL.Food_PotatoChips_Raw.get(1), CR.DEF_NCC, "kX", 'X', "cropPotato");
		CR.shapeless(IL.Food_PotatoChips_Packaged.get(1), CR.DEF_NCC, new Object[] {OP.foil.dat(MT.Al), IL.Food_PotatoChips});
		RM.Boxinator.addRecipe2(T, 16, 16, IL.Food_PotatoChips.get(1), OP.foil.mat(MT.Al, 1), IL.Food_PotatoChips_Packaged.get(1));
		RM.Unboxinator.addRecipe1(T, 16, 16, IL.Food_PotatoChips_Packaged.get(1), IL.Food_PotatoChips.get(1), OP.scrapGt.mat(MT.Al, 2));
		
		
		IL.Food_ChiliChips                     .set(addItem(tLastID =  9020, "Chili Chips"                              , "Spicy"                                                       , new FoodStat( 7, 1.200F, -10, C+40,  0.30F,   0,   0,  30,  10,   0, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_FLAMMABLE, 300, 0, 70), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.IGNIS, 1)));             setFluidContainerStats(tLastID, 0, 8);
		IL.Food_ChiliChips_Packaged            .set(addItem(tLastID =  9021, "Bag of Chili Chips"                       , "Stop making noises Baj!"                                     , new FoodStat( 7, 1.200F, -10, C+40,  0.30F,   0,   0,  30,  10,   0, EnumAction.eat, OP.scrapGt.mat(MT.Al, 2)             , F, T, F, T, PotionsGT.ID_FLAMMABLE, 300, 0, 70), TC.stack(TC.FAMES, 1), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.IGNIS, 1)));
		CR.shapeless(IL.Food_ChiliChips_Packaged.get(1), CR.DEF_NCC, new Object[] {OP.foil.dat(MT.Al), IL.Food_ChiliChips});
		RM.Boxinator.addRecipe2(T, 16, 16, IL.Food_ChiliChips.get(1), OP.foil.mat(MT.Al, 1), IL.Food_ChiliChips_Packaged.get(1));
		RM.Unboxinator.addRecipe1(T, 16, 16, IL.Food_ChiliChips_Packaged.get(1), IL.Food_ChiliChips.get(1), OP.scrapGt.mat(MT.Al, 2));
		
		
		RM.replicateOrganic( 4, 12, ST.make(Blocks.pumpkin, 1, 0));
		
		
		IL.Food_Banana                         .set(addItem(tLastID =    90, "Banana"                                   , "For Scale"                   , "cropBanana"                  , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0,4,EnumAction.eat, null                                , F, T, F, T, PotionsGT.ID_SLIPPERY, 300, 1, 70), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Banana_Sliced                  .set(addItem(tLastID =    91, "Banana Slice"                             , "Food for Minions"                                            , new FoodStat( 0, 0.150F,   0, C+36,  0.30F,   0,   0,   0,   2,   0,1,EnumAction.eat, null                                , F, T, F, T, PotionsGT.ID_SLIPPERY, 300, 1, 70), TC.stack(TC.HERBA, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)17);
		RM.replicateOrganic( 3,  9, IL.Food_Banana.get(1));
		CR.shaped(IL.Food_Banana_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropBanana");
		
		
		IL.Food_Pomegranate                    .set(addItem(tLastID =   100, "Pomegranate"                              , "A seeded Apple"              , "cropPomegranate"             , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.BESTIA, 1)));
		IL.Food_Pomeraisins                    .set(addItem(tLastID =   101, "Pomeraisins"                              , "Lesser Dogs favourite Food"  , "foodRaisins"                 , new FoodStat( 2, 0.600F,   0, C+37,  0.20F,   0,   0,   0,   2,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.VACUOS, 1), TC.stack(TC.BESTIA, 1), Behavior_FeedDog.INSTANCE));
		RM.replicateOrganic( 3, 10, IL.Food_Pomegranate.get(1));
		
		
		IL.Food_Blueberry                      .set(addItem(tLastID =   110, "Blueberry"                                , ""                            , "cropBlueberry"               , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.SENSUS, 1)));
		RM.replicateOrganic( 5,  6, IL.Food_Blueberry.get(1));
		BushesGT.put(IL.Food_Blueberry.get(1), 0x22ff22, 0xffcccc, 0x6666dd, 0x0000ff);
		
		IL.Food_Gooseberry                     .set(addItem(tLastID =   120, "Gooseberry"                               , ""                            , "cropGooseberry"              , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 5,  7, IL.Food_Gooseberry.get(1));
		// Grows on Trees
		
		IL.Food_Candleberry                    .set(addItem(tLastID =   130, "Candleberry"                              , ""                            , "cropCandleberry"             , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_FLAMMABLE, 300, 0, 30), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.GELUM, 1)));
		RM.replicateOrganic( 5,  8, IL.Food_Candleberry.get(1));
		BushesGT.put(IL.Food_Candleberry.get(1), 0x44ff44, 0xccffcc, 0xaaffaa, 0xccffcc);
		
		IL.Food_Cranberry                      .set(addItem(tLastID =   140, "Cranberry"                                , ""                            , "cropCranberry"               , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 5,  9, IL.Food_Cranberry.get(1));
		BushesGT.put(IL.Food_Cranberry.get(1), 0x00dd00, 0xffcccc, 0x66ff66, 0xff0000);
		
		IL.Food_Currants_Black                 .set(addItem(tLastID =   150, "Black Currants"                           , ""                            , "cropCurrantsBlack"           , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   6,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.TENEBRAE, 1)));
		RM.replicateOrganic( 5, 10, IL.Food_Currants_Black.get(1));
		BushesGT.put(IL.Food_Currants_Black.get(1), 0x33ff33, 0xaaaaaa, 0x66ff66, 0x111111);
		
		IL.Food_Currants_White                 .set(addItem(tLastID =   160, "White Currants"                           , ""                            , "cropCurrantsWhite"           , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.ORDO, 1)));
		RM.replicateOrganic( 5, 11, IL.Food_Currants_White.get(1));
		BushesGT.put(IL.Food_Currants_White.get(1), 0x33ff33, 0xaaaaaa, 0x66ff66, 0xeeeedd);
		
		IL.Food_Currants_Red                   .set(addItem(tLastID =   170, "Red Currants"                             , ""                            , "cropCurrantsRed"             , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.SANO, 1)));
		RM.replicateOrganic( 5, 12, IL.Food_Currants_Red.get(1));
		BushesGT.put(IL.Food_Currants_Red.get(1), 0x33ff33, 0xaaaaaa, 0x66ff66, 0xee0000);
		
		IL.Food_Blackberry                     .set(addItem(tLastID =   180, "Blackberry"                               , ""                            , "cropBlackberry"              , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.TENEBRAE, 1)));
		RM.replicateOrganic( 5, 13, IL.Food_Blackberry.get(1));
		BushesGT.put(IL.Food_Blackberry.get(1), 0x11ff11, 0xffcccc, 0x663333, 0x331111);
		
		IL.Food_Raspberry                      .set(addItem(tLastID =   190, "Raspberry"                                , ""                            , "cropRaspberry"               , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.SANO, 1)));
		RM.replicateOrganic( 5, 14, IL.Food_Raspberry.get(1));
		BushesGT.put(IL.Food_Raspberry.get(1), 0x11ff11, 0xffcccc, 0x664444, 0xffaaaa);
		
		IL.Food_Strawberry                     .set(addItem(tLastID =   200, "Strawberry"                               , ""                            , "cropStrawberry"              , new FoodStat( 1, 0.600F,   0, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.HERBA, 1), TC.stack(TC.SANO, 1)));
		RM.replicateOrganic( 5, 15, IL.Food_Strawberry.get(1));
		// Grows on Weeds
		
		
		IL.Food_Apple_Green                    .set(addItem(tLastID =   210, "Apple"            , ""                                                    , "cropAppleGreen"              , new FoodStat( 4, 0.400F,  10, C+36,  0.30F,   0,   0,   0,   4,   0, EnumAction.eat, ST.make(this, 1, tLastID+2)          , F, T, F, T), TC.stack(TC.MESSIS, 2), TC.stack(TC.FAMES, 1), Behavior_FeedPig.INSTANCE));
		IL.Food_Apple_Green_Sliced             .set(addItem(tLastID =   211, "Apple Slice"      , ""                                                                                    , new FoodStat( 1, 0.400F,  10, C+36,  0.30F,   0,   0,   0,   1,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1)));
		IL.Food_Apple_Green_Core               .set(addItem(tLastID =   212, "Apple Core"       , "Not to be confused with the Mod"                     , "itemPlantRemains"            , TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1), Behavior_FeedPig.INSTANCE));
		RM.replicateOrganic( 3, 11, IL.Food_Apple_Green.get(1));
		CR.shaped(IL.Food_Apple_Green_Sliced    .get(4), CR.DEF_NCC, "kX", 'X', "cropAppleGreen");
		
		
		IL.Food_Apple_Yellow                   .set(addItem(tLastID =   220, "Apple"            , ""                                                    , "cropAppleYellow"             , new FoodStat( 5, 0.300F,   5, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, ST.make(this, 1, tLastID+2)          , F, T, F, T), TC.stack(TC.MESSIS, 2), TC.stack(TC.FAMES, 1), Behavior_FeedPig.INSTANCE));
		IL.Food_Apple_Yellow_Sliced            .set(addItem(tLastID =   221, "Apple Slice"      , ""                                                                                    , new FoodStat( 1, 0.300F,   5, C+36,  0.30F,   0,   0,   0,   2,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1)));
		IL.Food_Apple_Yellow_Core              .set(addItem(tLastID =   222, "Apple Core"       , "Not to be confused with the Mod"                     , "itemPlantRemains"            , TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1), Behavior_FeedPig.INSTANCE));
		RM.replicateOrganic( 3, 12, IL.Food_Apple_Yellow.get(1));
		CR.shaped(IL.Food_Apple_Yellow_Sliced   .get(4), CR.DEF_NCC, "kX", 'X', "cropAppleYellow");
		
		
		IL.Food_Apple_Red                      .set(ST.make(Items.apple, 1, 0)); FoodsGT.put(ST.make(Items.apple, 1, W), 0, 0, 0, 8, 0);//              , "cropAppleRed"                , new FoodStat( 4, 0.300F,   0, C+37,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, ST.make(this, 1, tLastID+2)          , F, T, F, T), TC.stack(TC.MESSIS, 2), TC.stack(TC.FAMES, 1), Behavior_FeedPig.INSTANCE));
		IL.Food_Apple_Red_Sliced               .set(addItem(tLastID =   231, "Apple Slice"      , ""                                                                                    , new FoodStat( 1, 0.300F,   0, C+37,  0.30F,   0,   0,   0,   2,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1)));
		IL.Food_Apple_Red_Core                 .set(addItem(tLastID =   232, "Apple Core"       , "Not to be confused with the Mod"                     , "itemPlantRemains"            , TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1), Behavior_FeedPig.INSTANCE));
		RM.replicateOrganic( 3, 13, IL.Food_Apple_Red.get(1));
		CR.shaped(IL.Food_Apple_Red_Sliced      .get(4), CR.DEF_NCC, "kX", 'X', "cropAppleRed");
		
		
		IL.Food_Apple_DarkRed                  .set(addItem(tLastID =   240, "Apple"            , ""                                                    , "cropAppleDarkRed"            , new FoodStat( 5, 0.400F,   5, C+37,  0.30F,   0,   0,   0,  12,   0, EnumAction.eat, ST.make(this, 1, tLastID+2)          , F, T, F, T), TC.stack(TC.MESSIS, 2), TC.stack(TC.FAMES, 1), Behavior_FeedPig.INSTANCE));
		IL.Food_Apple_DarkRed_Sliced           .set(addItem(tLastID =   241, "Apple Slice"      , ""                                                                                    , new FoodStat( 1, 0.400F,   5, C+37,  0.30F,   0,   0,   0,   3,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1)));
		IL.Food_Apple_DarkRed_Core             .set(addItem(tLastID =   242, "Apple Core"       , "Not to be confused with the Mod"                     , "itemPlantRemains"            , TICKS_PER_SMELT / 4, TC.stack(TC.HERBA, 1), Behavior_FeedPig.INSTANCE));
		RM.replicateOrganic( 3, 14, IL.Food_Apple_DarkRed.get(1));
		CR.shaped(IL.Food_Apple_DarkRed_Sliced  .get(4), CR.DEF_NCC, "kX", 'X', "cropAppleDarkRed");
		
		
		RM.replicateOrganic( 3, 15, ST.make(Blocks.melon_block, 1, 0));
		
		
		IL.Food_Peanut                         .set(addItem(tLastID =   250, "Peanut"           , "Deez Nutz"                                           , "cropPeanut"                  , new FoodStat( 2, 0.300F,   0, C+37,  0.10F,   0,   0,   0,   0,  16, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.GRANUM, 1), TC.stack(TC.COGNITIO, 1)));
		addItem(251, "", "", new Behavior_Turn_Into(IL.Food_Coconut), TD.Creative.HIDDEN); // Migrating a GT6U Mistake when assigning IDs. There are 10-ID-wide gaps for a reason.
		RM.replicateOrganic( 6,  7, IL.Food_Peanut.get(1));
		
		
		IL.Food_Hazelnut                       .set(addItem(tLastID =   260, "Hazelnut"         , ""                                                    , "cropHazelnut"                , new FoodStat( 2, 0.300F,   0, C+37,  0.10F,   0,   0,   0,   0,  16, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.GRANUM, 1), TC.stack(TC.PERFODIO, 1)));
		RM.replicateOrganic( 6,  8, IL.Food_Hazelnut.get(1));
		
		
		IL.Food_Ananas                         .set(addItem(tLastID =   270, "Ananas"           , "Who lives in a Pineapple under the the Sea?"         , "cropAnanas"                  , new FoodStat( 4, 0.300F,  10, C+36,  0.30F,   0,   0,   0,   8,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.ARBOR, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ananas_Sliced                  .set(addItem(tLastID =   271, "Ananas Slice"     , "Did Ted ever find out about the Mystery Pineapple?"                                  , new FoodStat( 1, 0.300F,  10, C+36,  0.30F,   0,   0,   0,   2,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.ARBOR, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)16);
		RM.replicateOrganic( 2,  8, IL.Food_Ananas.get(1));
		CR.shaped(IL.Food_Ananas_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "cropAnanas");
		
		
		IL.Food_Cinnamon                       .set(addItem(tLastID =   280, "Cinnamon Bark"    , "Don't let anyone Challenge you!"                     , "cropCinnamon"                , new FoodStat( 2, 0.300F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.ARBOR, 1), TC.stack(TC.FAMES, 1)));
		RM.replicateOrganic( 2,  7, IL.Food_Cinnamon.get(1));
		
		// Couldn't resist to put Lyrics from the Donkey Kong Rap there.
		IL.Food_Coconut                        .set(addItem(tLastID =   290, "Coconut"          , "His Coconut Gun can fire in spurts. If he shoots ya, it's gonna hurt!", "cropCoconut", new FoodStat( 2, 0.300F,   0, C+37,  0.10F,   0,   0,   0,   8,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.MESSIS, 1), TC.stack(TC.GRANUM, 1), TC.stack(TC.TUTAMEN, 1)));
		RM.replicateOrganic( 2,  9, IL.Food_Coconut.get(1));
		
		
		IL.Food_Cheese                         .set(addItem(tLastID =  1000, "Cheese"           , "Click the Cheese"                                    , "foodCheese"                  , new FoodStat( 2, 1.200F,   0, C+37,  0.10F,   0,   0,   8,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 2)));
		IL.Food_Cheese_Sliced                  .set(addItem(tLastID =  1001, "Cheese Slice"     , "ALIEN ATTACK!!!, throw the CHEEEEESE!!!"                                             , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   2,   0,   2, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), new OreDictItemData(MT.Cheese, U4))); Sandwiches.INGREDIENTS.put(last(), (byte)19);
		CR.shaped(IL.Food_Cheese_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "foodCheese");
		
		
		IL.Food_Brown_Egg                      .set(ST.make(Items.egg, 1, 0));
		IL.Food_White_Egg                      .set(addItem(tLastID =  1051, "Egg"              , "The Egg came before the Chicken!"                    , OD.itemEgg                    , TC.stack(TC.BESTIA, 1), TC.stack(TC.LIMUS, 1), TC.stack(TC.VICTUS, 1)));
		IL.Food_Brown_Egg_Boiled               .set(addItem(tLastID =  1060, "Boiled Egg"       , "Did you expect this to look different after boiling?", "foodBoiledegg"               , new FoodStat( 2, 1.200F,   0, C+37,  0.50F,   0,   0,   0,   4,   8, EnumAction.eat, null                                 , F, T, F, T                                                                    ), TC.stack(TC.FAMES, 2)));
		IL.Food_White_Egg_Boiled               .set(addItem(tLastID =  1061, "Boiled Egg"       , "Did you expect this to look different after boiling?", "foodBoiledegg"               , new FoodStat( 2, 1.200F,   0, C+37,  0.50F,   0,   0,   0,   4,   8, EnumAction.eat, null                                 , F, T, F, T                                                                    ), TC.stack(TC.FAMES, 2)));
		IL.Food_Egg_Fried                      .set(addItem(tLastID =  1070, "Fried Egg"        , ""                                                    , "foodFriedegg"                , new FoodStat( 2, 1.200F,   0, C+37,  0.50F,   0,   0,   0,   4,   8, EnumAction.eat, null                                 , F, T, F, T                                                                    ), TC.stack(TC.FAMES, 2))); Sandwiches.INGREDIENTS.put(last(), (byte)43);
		IL.Food_Egg_Scrambled                  .set(addItem(tLastID =  1071, "Scrambled Egg"    , ""                                                    , "foodScrambledegg"            , new FoodStat( 2, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   4,   8, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_SLIPPERY, 300, 0, 30, PotionsGT.ID_STICKY, 300, 0, 30), TC.stack(TC.FAMES, 2))); Sandwiches.INGREDIENTS.put(last(), (byte)(200+DYE_INDEX_Yellow));
		IL.Food_Egg_Sliced                     .set(addItem(tLastID =  1072, "Sliced Egg"       , "Eggcellent!"                                                                         , new FoodStat( 1, 0.600F,   0, C+37,  0.50F,   0,   0,   0,   1,   2, EnumAction.eat, null                                 , F, T, F, T                                                                    ), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)44);
		IL.Food_Egg_Yolk                       .set(addItem(tLastID =  1073, "Egg Yolk"         , "That's all, Yolks!"                                                                  , new FoodStat( 1, 1.200F,   0, C+37,  0.50F,   0,   0,   0,   2,   4, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_SLIPPERY, 300, 0, 30                                 ), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)(200+DYE_INDEX_Yellow));
		IL.Food_Egg_White                      .set(addItem(tLastID =  1074, "Egg White"        , ""                                                                                    , new FoodStat( 1, 1.200F,   0, C+37,  0.50F,   0,   0,   0,   2,   4, EnumAction.eat, null                                 , F, T, F, T, PotionsGT.ID_STICKY  , 300, 0, 30                                 ), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)(200+DYE_INDEX_White));
		CR.shaped(IL.Food_Egg_Sliced.get(4), CR.DEF_NCC, "kX", 'X', "foodBoiledegg");
		RM.generify(IL.Food_White_Egg.get(1), ST.make(Items.egg, 1, 0));
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Egg_White.get(1), IL.Food_Egg_Yolk.get(1), IL.Food_Egg_Scrambled.get(1));
		for (String tCookingOil : FluidsGT.COOKING_OIL) if (FL.exists(tCookingOil)) {
		for (String tFluid : FluidsGT.VINEGAR) if (FL.exists(tFluid))
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Egg_Yolk.get(1), FL.array(FL.make(tCookingOil, 100), FL.make(tFluid    , 100)), FL.Mayo.make(250), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Egg_Yolk.get(1), FL.array(FL.make(tCookingOil, 100), FL.Juice_Lemon.make(100)), FL.Mayo.make(250), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Egg_Yolk.get(1), FL.array(FL.make(tCookingOil, 100), FL.Juice_Lime .make(100)), FL.Mayo.make(250), ZL_IS);
		}
		
		FoodsGT.put(ST.make(Items.fish           , 1, W), 0, 0, 0, 0,12); Sandwiches.INGREDIENTS.put(ST.make(Items.fish           , 1, W), (byte)24);
		FoodsGT.put(ST.make(Items.cooked_fished  , 1, W), 0, 0, 0, 0,12); Sandwiches.INGREDIENTS.put(ST.make(Items.cooked_fished  , 1, W), (byte)25);
		
		FoodsGT.put(ST.make(Items.beef           , 1, W), 0, 0, 0, 0,16); Sandwiches.INGREDIENTS.put(ST.make(Items.beef           , 1, W), (byte)29);
		FoodsGT.put(ST.make(Items.cooked_beef    , 1, W), 0, 0, 0, 0,16); Sandwiches.INGREDIENTS.put(ST.make(Items.cooked_beef    , 1, W), (byte)30);
		
		FoodsGT.put(ST.make(Items.chicken        , 1, W), 0, 0, 0, 0,12); Sandwiches.INGREDIENTS.put(ST.make(Items.chicken        , 1, W), (byte)29);
		FoodsGT.put(ST.make(Items.cooked_chicken , 1, W), 0, 0, 0, 0,12); Sandwiches.INGREDIENTS.put(ST.make(Items.cooked_chicken , 1, W), (byte)30);
		
		FoodsGT.put(ST.make(Items.porkchop       , 1, W), 0, 0, 0, 0,16); Sandwiches.INGREDIENTS.put(ST.make(Items.porkchop       , 1, W), (byte)29);
		FoodsGT.put(ST.make(Items.cooked_porkchop, 1, W), 0, 0, 0, 0,16); Sandwiches.INGREDIENTS.put(ST.make(Items.cooked_porkchop, 1, W), (byte)30);
		
		FoodsGT.put(ST.make(Items.rotten_flesh   , 1, W),10, 0, 0, 0, 8); Sandwiches.INGREDIENTS.put(ST.make(Items.rotten_flesh   , 1, W), (byte)29);
		
		
		IL.Food_Ham_Raw                        .set(addItem(tLastID =  1100, "Raw Ham"                                  , "Dropped by Pigs and Boars"   , "foodHamraw"                  , new FoodStat( 3, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, U*2, MT.Bone, U4)));
		IL.Food_Ham_Cooked                     .set(addItem(tLastID =  1101, "Cooked Ham"                               , ""                            , "foodHamcooked"               , new FoodStat(10, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, U*2, MT.Bone, U4)));
		RM.add_smelting(IL.Food_Ham_Raw.get(1), IL.Food_Ham_Cooked.get(1), F, T, F);
		
		
		IL.Food_Ham_Slice_Raw                  .set(addItem(tLastID =  1102, "Raw Ham Slice"                            , ""                                                            , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,   6, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.CORPUS, 1), new OreDictItemData(MT.MeatRaw, U2))); Sandwiches.INGREDIENTS.put(last(), (byte)26);
		IL.Food_Ham_Slice_Cooked               .set(addItem(tLastID =  1103, "Cooked Ham Slice"                         , ""                                                            , new FoodStat( 3, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,   6, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.CORPUS, 1), new OreDictItemData(MT.MeatCooked, U2))); Sandwiches.INGREDIENTS.put(last(), (byte)27);
		RM.add_smelting(IL.Food_Ham_Slice_Raw.get(1), IL.Food_Ham_Slice_Cooked.get(1), F, T, F);
		CR.shaped(IL.Food_Ham_Slice_Raw.get(4), CR.DEF_NCC, "kX", 'X', "foodHamraw");
		CR.shaped(IL.Food_Ham_Slice_Cooked.get(4), CR.DEF_NCC, "kX", 'X', "foodHamcooked");
		
		
		IL.Food_Bacon_Raw                      .set(addItem(tLastID =  1112, "Raw Bacon"                                , "Dropped by Pigs and Boars"       , "foodBaconraw"            , new FoodStat( 1, 0.900F,   0, C+37,  0.10F,   0,   0,   0,   0,   6, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.CORPUS, 1), new OreDictItemData(MT.MeatRaw, U2))); Sandwiches.INGREDIENTS.put(last(), (byte)37);
		IL.Food_Bacon_Cooked                   .set(addItem(tLastID =  1113, "Grilled Bacon"                            , ""                                , "foodBaconcooked"         , new FoodStat( 3, 1.800F,   0, C+38,  0.50F,   0,   0,   0,   0,   6, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.CORPUS, 1), new OreDictItemData(MT.MeatCooked, U2))); Sandwiches.INGREDIENTS.put(last(), (byte)38);
		RM.add_smelting(IL.Food_Bacon_Raw.get(1), IL.Food_Bacon_Cooked.get(1), F, T, F);
		
		
		IL.Food_Rib_Raw                        .set(addItem(tLastID =  1200, "Raw Ribs"                                 , "Dropped by large Animals"        , "foodRibraw"              , new FoodStat( 3, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, U*2, MT.Bone, U), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Rib_Cooked                     .set(addItem(tLastID =  1201, "Grilled Ribs"                             , ""                                , "foodRibcooked"           , new FoodStat(10, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, U*2, MT.Bone, U))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		IL.Food_Rib_BBQ                        .set(addItem(tLastID =  1202, "Barbecue Ribs"                            , "High Quality Video Game Ribs"    , "foodRibbbq"              , new FoodStat(10, 1.600F,   0, C+38,  0.50F,   0,   0,   0,  30,  24, EnumAction.eat, null                                 , F, T, F, T, Potion.heal.id, 1, 0, 10, Potion.moveSpeed.id, 200, 0, 20), TC.stack(TC.FAMES, 2), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, U*2, MT.Bone, U))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		RM.add_smelting(IL.Food_Rib_Raw.get(1), IL.Food_Rib_Cooked.get(1), F, T, F);
		CR.shapeless(IL.Food_Rib_BBQ.get(1), CR.DEF_NCC, new Object[] {"foodRibcooked", "foodBarbecuesauce"});
		
		
		IL.Food_RibEyeSteak_Raw                .set(addItem(tLastID =  1210, "Raw Rib Eye Steak"                        , "Dropped by large Animals"        , "foodRibeyesteakraw"      , new FoodStat( 3, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 3), new OreDictItemData(MT.MeatRaw, U*3, MT.Bone, U4), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)32);
		IL.Food_RibEyeSteak_Cooked             .set(addItem(tLastID =  1211, "Grilled Rib Eye Steak"                    , "It is staring at you"            , "foodRibeyesteakcooked"   , new FoodStat(10, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 3), new OreDictItemData(MT.MeatCooked, U*3, MT.Bone, U4))); Sandwiches.INGREDIENTS.put(last(), (byte)33);
		RM.add_smelting(IL.Food_RibEyeSteak_Raw.get(1), IL.Food_RibEyeSteak_Cooked.get(1), F, T, F);
		
		
		IL.Food_DogMeat_Raw                    .set(addItem(tLastID =  1300, "Dogmeat"                                  , "Why didn't you use [MERCY]?"     , "foodDograw"              , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, U*2, MT.Bone, U9), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_DogMeat_Cooked                 .set(addItem(tLastID =  1301, "Grilled Dogmeat"                          , "You monster!"                    , "foodDogcooked"           , new FoodStat( 8, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, U*2, MT.Bone, U9))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		RM.add_smelting(IL.Food_DogMeat_Raw.get(1), IL.Food_DogMeat_Cooked.get(1), F, T, F);
		
		
		IL.Food_Mutton_Raw                     .set(addItem(tLastID =  1400, "Mutton"           , "Beep Beep, I'm a Sheep, I said: Beep Beep Imma Sheep"    , "foodMuttonraw"           , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 3), new OreDictItemData(MT.MeatRaw, U*2, MT.Bone, U4), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Mutton_Cooked                  .set(addItem(tLastID =  1401, "Grilled Mutton"                           , ""                                , "foodMuttoncooked"        , new FoodStat( 7, 2.000F,   0, C+38,  0.50F,   0,   0,   0,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 3), new OreDictItemData(MT.MeatCooked, U*2, MT.Bone, U4))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		RM.add_smelting(IL.Food_Mutton_Raw.get(1), IL.Food_Mutton_Cooked.get(1), F, T, F);
		
		
		IL.Food_Horse_Raw                      .set(addItem(tLastID =  1500, "Horse Meat"                               , ""                                , "foodHorseraw"            , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, U*2, MT.Bone, U4), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Horse_Cooked                   .set(addItem(tLastID =  1501, "Grilled Horse Meat"                       , ""                                , "foodHorsecooked"         , new FoodStat( 8, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, U*2, MT.Bone, U4))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		RM.add_smelting(IL.Food_Horse_Raw.get(1), IL.Food_Horse_Cooked.get(1), F, T, F);
		
		IL.Food_Mule_Raw                       .set(addItem(tLastID =  1510, "Mule Meat"                                , ""                                , "foodMuleraw"             , new FoodStat( 3, 0.800F,   0, C+37,  0.10F,   0,   0,   0,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, 5*U2, MT.Bone, U4), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Mule_Cooked                    .set(addItem(tLastID =  1511, "Grilled Mule Meat"                        , ""                                , "foodMulecooked"          , new FoodStat(10, 1.800F,   0, C+38,  0.50F,   0,   0,   0,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, 5*U2, MT.Bone, U4))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		RM.add_smelting(IL.Food_DogMeat_Raw.get(1), IL.Food_DogMeat_Cooked.get(1), F, T, F);
		
		IL.Food_Donkey_Raw                     .set(addItem(tLastID =  1520, "Donkey Meat"                              , ""                                , "foodDonkeyraw"           , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, 5*U2, MT.Bone, U9), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Donkey_Cooked                  .set(addItem(tLastID =  1521, "Grilled Donkey Meat"                      , ""                                , "foodDonkeycooked"        , new FoodStat( 8, 1.600F,   0, C+38,  0.50F,   0,   0,   0,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.FAMES, 1), TC.stack(TC.BESTIA, 1), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatCooked, 5*U2, MT.Bone, U9))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		RM.add_smelting(IL.Food_Donkey_Raw.get(1), IL.Food_Donkey_Cooked.get(1), F, T, F);
		
		
		IL.Food_Scrap_Meat                     .set(addItem(tLastID =  1998, "Scrap Meat"                               , "Parts people usually don't eat"  , "foodScrapmeat"           , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   4,   0,  12, EnumAction.eat, null                                 , F, T, F, T, Potion.hunger.id, 300, 0, 70, Potion.confusion.id, 300, 0, 10), TC.stack(TC.CORPUS, 2), new OreDictItemData(MT.MeatRaw, U*2), Behavior_FeedDog.INSTANCE)); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		
		IL.Food_Dough                          .set(addItem(tLastID = 32000, "Dough"                                , "For making Bread"                        , "foodDough"           , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Sugar                    .set(addItem(tLastID = 32001, "Sugary Dough"                         , "Don't eat the Dough before it is baken"  , "foodSugarDough"      , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Chocolate                .set(addItem(tLastID = 32002, "Chocolate Dough"                      , "I said don't eat the Dough!"             , "foodChocolateDough"  , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Egg                      .set(addItem(tLastID = 32003, "Egg Dough"                            , "For making Pasta"                        , "foodEggDough"        , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Sugar_Raisins            .set(addItem(tLastID = 32004, "Sugary Raisin Dough"                  , "Don't eat the Dough before it is baken"  , "foodSugarDough"      , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,  30,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Sugar_Chocolate_Raisins  .set(addItem(tLastID = 32005, "Sugary Chocolate Raisin Dough"        , "Almost looks like Chocolate Chips"       , "foodSugarDough"      , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,  40,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Abyssal                  .set(addItem(tLastID = 32006, "Abyssal Dough"                        , "For practicing netherlicious Bakery"     , "foodAbyssalDough"    , new FoodStat( 1, 1.000F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		if (IL.NeLi_Bread.exists()) RM.add_smelting(IL.Food_Dough_Abyssal.get(1), IL.NeLi_Bread.get(1), F, T, F);
		
		
		
		IL.Food_Chum                           .set(addItem(tLastID = 10000, "Chum"                                     , "Chum is Fum!"                            , "foodChum"        , new FoodStat( 5, 1.600F,   0, C+37,  0.10F,   0,   0,  20,   0,   0, EnumAction.eat, null                                 , T, F, T, T, Potion.hunger.id, 1000, 4, 100, Potion.confusion.id, 300, 1, 80), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)39);
		
		
		
		IL.Food_Cookie_Raw                     .set(addItem(tLastID =  2000, "Cookie shaped Dough"                      , "For baking Cookies"                                          , new FoodStat( 1, 0.200F,   0, C+37,  0.10F,   0,   0,   0,   5,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		RM.add_smelting(IL.Food_Cookie_Raw.get(1), ST.make(Items.cookie, 1, 0), F, T, F); FoodsGT.put(ST.make(Items.cookie, 1, W), 0, 0, 0,10, 0);
		RM.food_can(ST.make(Items.cookie, 6, W), 12, "Canned Cookies", IL.CANS_BREAD);
		CR.shaped(IL.Food_Cookie_Raw.get(4), CR.DEF_NCC, "kX", 'X', "foodChocolateDough");
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Dough_Chocolate.get(1), IL.Shape_Slicer_Flat.get(0), IL.Food_Cookie_Raw.get(4));
		
		
		IL.Food_Cookie_Raisins_Raw             .set(addItem(tLastID =  2002, "Cookie shaped Raisin Dough"               , "For baking Raisin Cookies"                                   , new FoodStat( 1, 0.200F,   0, C+37,  0.10F,   0,   0,   0,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Cookie_Raisins                 .set(addItem(tLastID =  2003, "Raisin Cookie"                   , "You don't like it? I don't care! It's delicious!", "foodRaisincookies", new FoodStat( 2, 0.200F,   0, C+37,  0.10F,   0,   0,   0,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		RM.add_smelting(IL.Food_Cookie_Raisins_Raw.get(1), IL.Food_Cookie_Raisins.get(1), F, T, F);
		RM.food_can(IL.Food_Cookie_Raisins.get(6), 12, "Canned Raisin Cookies", IL.CANS_BREAD);
		CR.shaped(IL.Food_Cookie_Raisins_Raw.get(4), CR.DEF_NCC, "kX", 'X', IL.Food_Dough_Sugar_Raisins);
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Dough_Sugar_Raisins.get(1), IL.Shape_Slicer_Flat.get(0), IL.Food_Cookie_Raisins_Raw.get(4));
		
		
		IL.Food_Cookie_Chocolate_Raisins_Raw   .set(addItem(tLastID =  2004, "Cookie shaped Chocolate Raisin Dough"     , "Almost looks like a regular Chocolate Chip Cookie >:D"       , new FoodStat( 1, 0.200F,   0, C+37,  0.10F,   0,   0,   0,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Cookie_Chocolate_Raisins       .set(addItem(tLastID =  2005, "Cookie"                                   , ""                                       , "foodRaisincookies", new FoodStat( 2, 0.200F,   0, C+37,  0.10F,   0,   0,   0,  25,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		RM.add_smelting(IL.Food_Cookie_Chocolate_Raisins_Raw.get(1), IL.Food_Cookie_Chocolate_Raisins.get(1), F, T, F);
		RM.food_can(IL.Food_Cookie_Chocolate_Raisins.get(6), 12, "Canned Chocolate Raisin Cookies", IL.CANS_BREAD);
		CR.shaped(IL.Food_Cookie_Chocolate_Raisins_Raw.get(4), CR.DEF_NCC, "kX", 'X', IL.Food_Dough_Sugar_Chocolate_Raisins);
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Dough_Sugar_Chocolate_Raisins.get(1), IL.Shape_Slicer_Flat.get(0), IL.Food_Cookie_Chocolate_Raisins_Raw.get(4));
		
		
		IL.Food_Cookie_Abyssal_Raw             .set(addItem(tLastID =  2006, "Cookie shaped Abyssal Dough"              , "For baking netherlicious Cookies"                            , new FoodStat( 1, 0.200F,   0, C+37,  0.10F,   0,   0,   0,   5,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		if (IL.NeLi_Cookie.exists()) {
		RM.add_smelting(IL.Food_Cookie_Abyssal_Raw.get(1), IL.NeLi_Cookie.get(1), F, T, F); FoodsGT.put(IL.NeLi_Cookie.get(1), 0, 0, 0,10, 0);
		RM.food_can(IL.NeLi_Cookie.get(6), 12, "Canned Abyssal Cookies", IL.CANS_BREAD);
		} else {
		RM.add_smelting(IL.Food_Cookie_Abyssal_Raw.get(1), ST.make(Items.cookie, 1, 0), F, T, F);
		}
		CR.shaped(IL.Food_Cookie_Abyssal_Raw.get(4), CR.DEF_NCC, "kX", 'X', "foodAbyssalDough");
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Dough_Abyssal.get(1), IL.Shape_Slicer_Flat.get(0), IL.Food_Cookie_Abyssal_Raw.get(4));
		
		
		IL.Food_CakeBottom_Raw                 .set(addItem(tLastID =  3000, "Raw Cake Bottom"                          , "For making Cake"                                             , new FoodStat( 2, 0.200F,   0, C+37,  0.10F,   0,   0,   0,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_CakeBottom                     .set(addItem(tLastID =  3001, "Cake Bottom"                              , "I know I promised you an actual Cake, but well..."           , new FoodStat( 3, 0.200F,   0, C+37,  0.10F,   0,   0,   0,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		RM.add_smelting(IL.Food_CakeBottom_Raw.get(1), IL.Food_CakeBottom.get(1), F, T, F);
		CR.shapeless(IL.Food_CakeBottom_Raw.get(1), CR.DEF_NCC, new Object[] {"foodSugarDough", "foodSugarDough", "foodSugarDough", "foodSugarDough"});
		CR.shaped(ST.make(Items.cake, 1, 0), CR.DEF_NCC, "C", "Z", 'Z', IL.Food_CakeBottom, 'C', "foodHeavycream");
		CR.delate(ST.make(Items.cake, 1, 0));
		
		
		IL.Food_Dough_Flat                     .set(addItem(tLastID =  4000, "Flattened Dough"                          , "For making Pizza"                                            , new FoodStat( 1, 0.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Dough_Flat_Ketchup             .set(addItem(tLastID =  4002, "Flat Dough with Ketchup"                  , "For making Pizza"                                            , new FoodStat( 2, 0.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		CR.shapeless(IL.Food_Dough_Flat.get(1), CR.DEF, new Object[] {"foodDough", OreDictToolNames.rollingpin});
		CR.shapeless(IL.Food_Dough_Flat_Ketchup.get(1), CR.DEF, new Object[] {"foodKetchup", IL.Food_Dough_Flat});
		CR.shapeless(IL.Food_Dough_Flat_Ketchup.get(2), CR.DEF, new Object[] {"foodKetchup", IL.Food_Dough_Flat, IL.Food_Dough_Flat});
		CR.shapeless(IL.Food_Dough_Flat_Ketchup.get(3), CR.DEF, new Object[] {"foodKetchup", IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat});
		CR.shapeless(IL.Food_Dough_Flat_Ketchup.get(4), CR.DEF, new Object[] {"foodKetchup", IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat});
		CR.shapeless(IL.Food_Dough_Flat_Ketchup.get(5), CR.DEF, new Object[] {"foodKetchup", IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat, IL.Food_Dough_Flat});
		
		
		IL.Food_Pizza_Cheese_Raw               .set(addItem(tLastID =  4010, "Raw Pizza Margherita"                     , "Into the Oven with it!"                                      , new FoodStat( 2, 0.300F,   0, C+37,  0.10F,   0,   0,   8,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 2), TC.stack(TC.IGNIS, 1)));
		IL.Food_Pizza_Cheese                   .set(addItem(tLastID =  4011, "Pizza Margherita"                         , "Cheese Pizza"                                                , new FoodStat( 6, 1.200F,   0, C+38,  0.50F,   0,   0,   8,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 2), TC.stack(TC.IGNIS, 1)));
		CR.shapeless(IL.Food_Pizza_Cheese_Raw.get(1), CR.DEF, new Object[] {IL.Food_Dough_Flat_Ketchup, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		RM.add_smelting(IL.Food_Pizza_Cheese_Raw.get(1), IL.Food_Pizza_Cheese.get(1), F, T, F);
		
		
		IL.Food_Pizza_Meat_Raw                 .set(addItem(tLastID =  4012, "Raw Mince Meat Pizza"                     , "Into the Oven with it!"                                      , new FoodStat( 2, 0.300F,   0, C+37,  0.10F,   0,   0,   4,   0,  16, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));
		IL.Food_Pizza_Meat                     .set(addItem(tLastID =  4013, "Mince Meat Pizza"                         , "Emo Pizza, it cuts itself!"                                  , new FoodStat( 7, 1.200F,   0, C+38,  0.50F,   0,   0,   4,   0,  16, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));
		CR.shapeless(IL.Food_Pizza_Meat_Raw.get(1), CR.DEF, new Object[] {IL.Food_Dough_Flat_Ketchup, OP.dust.dat(MT.MeatCooked)});
		RM.add_smelting(IL.Food_Pizza_Meat_Raw.get(1), IL.Food_Pizza_Meat.get(1), F, T, F);
		
		
		IL.Food_Pizza_Veggie_Raw               .set(addItem(tLastID =  4014, "Raw Veggie Pizza"                         , "Into the Oven with it!"                                      , new FoodStat( 1, 0.300F,   5, C+35,  0.30F,   0,   0,   0,  16,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Pizza_Veggie                   .set(addItem(tLastID =  4015, "Veggie Pizza"                             , "The next they want is Gluten Free Pizzas..."                 , new FoodStat( 5, 1.200F,   5, C+38,  0.50F,   0,   0,   0,  16,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		CR.shapeless(IL.Food_Pizza_Veggie_Raw.get(1), CR.DEF, new Object[] {IL.Food_Dough_Flat_Ketchup, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		RM.add_smelting(IL.Food_Pizza_Veggie_Raw.get(1), IL.Food_Pizza_Veggie.get(1), F, T, F);
		
		
		IL.Food_Pizza_Ananas_Raw               .set(addItem(tLastID =  4016, "Raw Pizza Hawaii"                         , "Did you seriously just put Pineapple on a Pizza?"            , new FoodStat( 2, 0.300F,   5, C+37,  0.10F,   0,   0,   4,   8,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.ARBOR, 1)));
		IL.Food_Pizza_Ananas                   .set(addItem(tLastID =  4017, "Pizza Hawaii"                             , "This is an Abomination! Who puts Pineapple on a Pizza!?"     , new FoodStat( 7, 1.200F,   5, C+38,  0.50F,   0,   0,   4,   8,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.ARBOR, 1)));
		CR.shapeless(IL.Food_Pizza_Ananas_Raw.get(1), CR.DEF, new Object[] {IL.Food_Dough_Flat_Ketchup, IL.Food_Ananas_Sliced, IL.Food_Ananas_Sliced, IL.Food_Ham_Slice_Cooked, IL.Food_Cheese_Sliced});
		RM.add_smelting(IL.Food_Pizza_Ananas_Raw.get(1), IL.Food_Pizza_Ananas.get(1), F, T, F);
		
		
		
		IL.Food_Bun_Raw                        .set(addItem(tLastID =  5000, "Dough (Bun)"                              , "In Bun Shape"                                                , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Bun                            .set(addItem(tLastID =  5001, "Bun"                                      , ""                                                            , new FoodStat( 2, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Bun_Sliced                     .set(addItem(tLastID =  5002, "Sliced Bun"                               , "Just half a Bun"                                             , new FoodStat( 1, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Buns_Sliced                    .set(addItem(tLastID =  5003, "Buns"                                     , "Pre Sliced"                                                  , new FoodStat( 2, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		RM.add_smelting(IL.Food_Bun_Raw.get(1), IL.Food_Bun.get(1), F, T, F);
		RM.food_can(IL.Food_Bun                .get(1), 2, "Canned Bread", IL.CANS_BREAD);
		RM.food_can(IL.Food_Bun_Sliced         .get(2), 2, "Canned Bread", IL.CANS_BREAD);
		RM.food_can(IL.Food_Buns_Sliced        .get(1), 2, "Canned Bread", IL.CANS_BREAD);
		CR.shaped(IL.Food_Bun_Sliced.get(2), CR.DEF_NCC, "kX", 'X', IL.Food_Bun);
		CR.shapeless(IL.Food_Bun_Raw.get(1), CR.DEF, new Object[] {"foodDough"});
		CR.shapeless(IL.Food_Buns_Sliced.get(1), CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced});
		CR.shapeless(IL.Food_Bun_Sliced.get(2), CR.DEF, new Object[] {IL.Food_Buns_Sliced});
		RM.packunpack(IL.Food_Bun_Sliced.get(2), IL.Food_Buns_Sliced.get(1));
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Bun.get(1), IL.Shape_Slicer_Split.get(0), IL.Food_Bun_Sliced.get(2));
		
		IL.Food_Burger_Veggie                  .set(addItem(tLastID =  5010, "Veggie Burger"                            , "No matter how you call this, this is NOT a Burger!"          , new FoodStat( 4, 1.200F,   0, C+35,  0.30F,   0,   0,   0,  12,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1)));
		IL.Food_Burger_Cheese                  .set(addItem(tLastID =  5011, "Cheese Burger"                            , "Cheesy!"                                                     , new FoodStat( 4, 1.400F,   0, C+38,  0.50F,   0,   0,   8,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 2)));
		IL.Food_Burger_Meat                    .set(addItem(tLastID =  5012, "Hamburger"                                , "The Mc Burger Queen Burger"                                  , new FoodStat( 6, 1.600F,   0, C+38,  0.50F,   0,   0,   4,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));
		IL.Food_Burger_Chum                    .set(addItem(tLastID =  5013, "Chum Burger"                              , "Fum is Chum!"                                                , new FoodStat( 6, 1.600F,   0, C+37,  0.10F,   0,   0,  20,   0,   0, EnumAction.eat, null                                 , T, F, T, T, Potion.hunger.id, 1000, 4, 100, Potion.confusion.id, 300, 1, 80), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));
		IL.Food_Burger_Tofu                    .set(addItem(tLastID =  5016, "Tofu Burger"                              , "Just a white thingy inside Buns"                             , new FoodStat( 4, 1.400F,   0, C+37,  0.10F,   0,   0,   4,   4,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1)));
		IL.Food_Burger_Soylent                 .set(addItem(tLastID =  5017, "Soylent Burger"                           , "Don't forget your Soylent Salad with Soylent Cola!"          , new FoodStat( 5, 1.400F,   0, C+37,  0.10F,   0,   0,   4,   0,  12, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));
		IL.Food_Burger_Fish                    .set(addItem(tLastID =  5018, "Fish Burger"                              , "Smells Fishy"                                                , new FoodStat( 6, 1.600F,   0, C+38,  0.50F,   0,   0,   3,   0,   8, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));
		CR.shapeless(IL.Food_Burger_Veggie.get(1)   , CR.DEF, new Object[] {IL.Food_Buns_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(IL.Food_Burger_Veggie.get(1)   , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(IL.Food_Burger_Cheese.get(1)   , CR.DEF, new Object[] {IL.Food_Buns_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		CR.shapeless(IL.Food_Burger_Cheese.get(1)   , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		CR.shapeless(IL.Food_Burger_Meat.get(1)     , CR.DEF, new Object[] {IL.Food_Buns_Sliced, OP.ingot.dat(MT.MeatCooked)});
		CR.shapeless(IL.Food_Burger_Meat.get(1)     , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, OP.ingot.dat(MT.MeatCooked)});
		CR.shapeless(IL.Food_Burger_Tofu.get(1)     , CR.DEF, new Object[] {IL.Food_Buns_Sliced, OP.ingot.dat(MT.Tofu)});
		CR.shapeless(IL.Food_Burger_Tofu.get(1)     , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, OP.ingot.dat(MT.Tofu)});
		CR.shapeless(IL.Food_Burger_Soylent.get(1)  , CR.DEF, new Object[] {IL.Food_Buns_Sliced, OP.ingot.dat(MT.SoylentGreen)});
		CR.shapeless(IL.Food_Burger_Soylent.get(1)  , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, OP.ingot.dat(MT.SoylentGreen)});
		CR.shapeless(IL.Food_Burger_Fish.get(1)     , CR.DEF, new Object[] {IL.Food_Buns_Sliced, OP.ingot.dat(MT.FishCooked)});
		CR.shapeless(IL.Food_Burger_Fish.get(1)     , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, OP.ingot.dat(MT.FishCooked)});
		CR.shapeless(IL.Food_Burger_Chum.get(1)     , CR.DEF, new Object[] {IL.Food_Buns_Sliced, "foodChum"});
		CR.shapeless(IL.Food_Burger_Chum.get(1)     , CR.DEF, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced, "foodChum"});
		
		
		
		IL.Food_Bread_Raw                      .set(addItem(tLastID =  6000, "Dough (Bread)"                            , "In Bread Shape"                                              , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Bread                          .set(ST.make(Items.bread, 1, 0)); // My OCD told me to do this, it is not my fault! XD                                                   , new FoodStat( 5, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Bread_Sliced                   .set(addItem(tLastID =  6002, "Sliced Bread"                             , ""                                                            , new FoodStat( 2, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Breads_Sliced                  .set(addItem(tLastID =  6003, "Breads"                                   , "Pre Sliced"                                                  , new FoodStat( 5, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		RM.add_smelting(IL.Food_Bread_Raw.get(1), IL.Food_Bread.get(1), F, T, F);
		RM.food_can(IL.Food_Bread              .get(1), 4, "Canned Bread", IL.CANS_BREAD);
		RM.food_can(IL.Food_Bread_Sliced       .get(1), 2, "Canned Bread", IL.CANS_BREAD);
		RM.food_can(IL.Food_Breads_Sliced      .get(1), 4, "Canned Bread", IL.CANS_BREAD);
		CR.shaped(IL.Food_Bread_Sliced.get(2), CR.DEF_NCC, "kX", 'X', IL.Food_Bread);
		CR.shapeless(IL.Food_Bread_Raw.get(1), CR.DEF, new Object[] {"foodDough", "foodDough"});
		CR.shapeless(IL.Food_Breads_Sliced.get(1), CR.DEF, new Object[] {IL.Food_Bread_Sliced, IL.Food_Bread_Sliced});
		CR.shapeless(IL.Food_Bread_Sliced.get(2), CR.DEF, new Object[] {IL.Food_Breads_Sliced});
		RM.packunpack(IL.Food_Bread_Sliced.get(2), IL.Food_Breads_Sliced.get(1));
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Bread.get(1), IL.Shape_Slicer_Split.get(0), IL.Food_Bread_Sliced.get(2));
		
		IL.Food_Sandwich_Veggie                .set(addItem(tLastID =  6010, "Veggie Sandwich"                          , "It's Canon, Guys! Season 4, Vegan Morty!"                    , new FoodStat( 7, 1.200F,   0, C+35,  0.30F,   0,   0,   0,  24,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1)));                                                  setFluidContainerStats(tLastID, 0, 32);
		IL.Food_Sandwich_Cheese                .set(addItem(tLastID =  6011, "Cheese Sandwich"                          , "Say Cheese!"                                                 , new FoodStat( 7, 1.400F,   0, C+38,  0.50F,   0,   0,  16,   0,  16, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 2)));                                                  setFluidContainerStats(tLastID, 0, 32);
		IL.Food_Sandwich_Bacon                 .set(addItem(tLastID =  6014, "Bacon Sandwich"                           , "The best Sandwich ever!"                                     , new FoodStat(10, 1.800F,   0, C+38,  0.50F,   0,   0,  12,   0,  18, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));                          setFluidContainerStats(tLastID, 0, 32);
		IL.Food_Sandwich_Steak                 .set(addItem(tLastID =  6015, "Steak Sandwich"                           , "Not a 'Steam Sandwich'"                                      , new FoodStat(10, 1.600F,   0, C+38,  0.50F,   0,   0,   8,   0,  16, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1)));                          setFluidContainerStats(tLastID, 0, 32);
		CR.shapeless(IL.Food_Sandwich_Veggie.get(1) , CR.DEF, new Object[] {IL.Food_Breads_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(IL.Food_Sandwich_Veggie.get(1) , CR.DEF, new Object[] {IL.Food_Bread_Sliced, IL.Food_Bread_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(IL.Food_Sandwich_Cheese.get(1) , CR.DEF, new Object[] {IL.Food_Breads_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		CR.shapeless(IL.Food_Sandwich_Cheese.get(1) , CR.DEF, new Object[] {IL.Food_Bread_Sliced, IL.Food_Bread_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		CR.shapeless(IL.Food_Sandwich_Bacon.get(1)  , CR.DEF, new Object[] {IL.Food_Breads_Sliced, "foodBaconcooked", "foodBaconcooked", "foodBaconcooked"});
		CR.shapeless(IL.Food_Sandwich_Bacon.get(1)  , CR.DEF, new Object[] {IL.Food_Bread_Sliced, IL.Food_Bread_Sliced, "foodBaconcooked", "foodBaconcooked", "foodBaconcooked"});
		CR.shapeless(IL.Food_Sandwich_Steak.get(1)  , CR.DEF, new Object[] {IL.Food_Breads_Sliced, Items.cooked_beef});
		CR.shapeless(IL.Food_Sandwich_Steak.get(1)  , CR.DEF, new Object[] {IL.Food_Bread_Sliced, IL.Food_Bread_Sliced, Items.cooked_beef});
		
		
		
		IL.Food_Baguette_Raw                   .set(addItem(tLastID =  7000, "Dough (Baguette)"                         , "In Baguette Shape"                                           , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Baguette                       .set(addItem(tLastID =  7001, "Baguette"                                 , "I teleported nothing BUT Bread!!!"                           , new FoodStat( 8, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Baguette_Sliced                .set(addItem(tLastID =  7002, "Sliced Baguette"                          , "Just half a Baguette"                                        , new FoodStat( 4, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Baguettes_Sliced               .set(addItem(tLastID =  7003, "Baguettes"                                , "Pre Sliced"                                                  , new FoodStat( 8, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		RM.add_smelting(IL.Food_Baguette_Raw.get(1), IL.Food_Baguette.get(1), F, T, F);
		RM.food_can(IL.Food_Baguette           .get(1), 8, "Canned Pain", IL.CANS_BREAD);
		RM.food_can(IL.Food_Baguette_Sliced    .get(1), 4, "Canned Pain", IL.CANS_BREAD);
		RM.food_can(IL.Food_Baguettes_Sliced   .get(1), 8, "Canned Pain", IL.CANS_BREAD);
		CR.shaped(IL.Food_Baguette_Sliced.get(2), CR.DEF_NCC, "kX", 'X', IL.Food_Baguette);
		CR.shapeless(IL.Food_Baguette_Raw.get(1), CR.DEF, new Object[] {"foodDough", "foodDough", "foodDough"});
		CR.shapeless(IL.Food_Baguettes_Sliced.get(1), CR.DEF, new Object[] {IL.Food_Baguette_Sliced, IL.Food_Baguette_Sliced});
		CR.shapeless(IL.Food_Baguette_Sliced.get(2), CR.DEF, new Object[] {IL.Food_Baguettes_Sliced});
		RM.packunpack(IL.Food_Baguette_Sliced.get(2), IL.Food_Baguette_Sliced.get(1));
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Baguette.get(1), IL.Shape_Slicer_Split.get(0), IL.Food_Baguette_Sliced.get(2));
		
		IL.Food_Large_Sandwich_Veggie          .set(addItem(tLastID =  7010, "Large Veggie Sandwich"                    , "Meatless"                                                    , new FoodStat(15, 2.200F,   0, C+35,  0.30F,   0,   0,   0,  36,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 3), TC.stack(TC.FAMES, 1)));                                                  setFluidContainerStats(tLastID, 0, 16);
		IL.Food_Large_Sandwich_Cheese          .set(addItem(tLastID =  7011, "Large Cheese Sandwich"                    , "I need another cheesy tooltip for this"                      , new FoodStat(15, 2.400F,   0, C+38,  0.50F,   0,   0,  24,   0,  24, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 3)));                                                  setFluidContainerStats(tLastID, 0, 16);
		IL.Food_Large_Sandwich_Bacon           .set(addItem(tLastID =  7014, "Large Bacon Sandwich"                     , "For Men! (and manly Women)"                                  , new FoodStat(20, 2.800F,   0, C+38,  0.50F,   0,   0,  16,   0,  36, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 2)));                          setFluidContainerStats(tLastID, 0, 16);
		IL.Food_Large_Sandwich_Steak           .set(addItem(tLastID =  7015, "Large Steak Sandwich"                     , "Yes, I once accidentially called it 'Steam Sandwich'"        , new FoodStat(20, 2.600F,   0, C+38,  0.50F,   0,   0,  12,   0,  32, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 2), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 2)));                          setFluidContainerStats(tLastID, 0, 16);
		CR.shapeless(IL.Food_Large_Sandwich_Veggie.get(1)   , CR.DEF, new Object[] {IL.Food_Baguettes_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Tomato_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(IL.Food_Large_Sandwich_Veggie.get(1)   , CR.DEF, new Object[] {IL.Food_Baguette_Sliced, IL.Food_Baguette_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Tomato_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(IL.Food_Large_Sandwich_Cheese.get(1)   , CR.DEF, new Object[] {IL.Food_Baguettes_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		CR.shapeless(IL.Food_Large_Sandwich_Cheese.get(1)   , CR.DEF, new Object[] {IL.Food_Baguette_Sliced, IL.Food_Baguette_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		CR.shapeless(IL.Food_Large_Sandwich_Bacon.get(1)    , CR.DEF, new Object[] {IL.Food_Baguettes_Sliced, "foodBaconcooked", "foodBaconcooked", "foodBaconcooked", "foodBaconcooked", "foodBaconcooked", "foodBaconcooked"});
		CR.shapeless(IL.Food_Large_Sandwich_Bacon.get(1)    , CR.DEF, new Object[] {IL.Food_Baguette_Sliced, IL.Food_Baguette_Sliced, "foodBaconcooked", "foodBaconcooked", "foodBaconcooked", "foodBaconcooked", "foodBaconcooked", "foodBaconcooked"});
		CR.shapeless(IL.Food_Large_Sandwich_Steak.get(1)    , CR.DEF, new Object[] {IL.Food_Baguettes_Sliced, Items.cooked_beef, Items.cooked_beef});
		CR.shapeless(IL.Food_Large_Sandwich_Steak.get(1)    , CR.DEF, new Object[] {IL.Food_Baguette_Sliced, IL.Food_Baguette_Sliced, Items.cooked_beef, Items.cooked_beef});
		
		
		
		IL.Food_Toast_Raw                       .set(addItem(tLastID = 14000, "Dough (Toast Loaf)"                       , "Shape of a Toast Loaf"                                       , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Toast                           .set(addItem(tLastID = 14001, "Loaf of Toast"                            , "Do not teleport Bread!"                                      , new FoodStat( 8, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1)));
		IL.Food_Toast_Sliced                    .set(addItem(tLastID = 14002, "Toast"                                    , "Best thing since sliced Bread, oh wait..."                   , new FoodStat( 1, 1.200F,   0, C+37,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)254);
		IL.Food_Toasted_Sliced                  .set(addItem(tLastID = 14003, "Toasted Toast"                            , ""                                                            , new FoodStat( 1, 1.200F,   0, C+39,  0.10F,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1), TC.stack(TC.IGNIS, 2))); Sandwiches.INGREDIENTS.put(last(), (byte)253);
		RM.add_smelting(IL.Food_Toast_Raw.get(1), IL.Food_Toast.get(1), F, T, F);
		RM.add_smelting(IL.Food_Toast_Sliced.get(1), IL.Food_Toasted_Sliced.get(1), F, T, F);
		RM.food_can(IL.Food_Toast_Raw           .get(1), 8, "Canned Bread", IL.CANS_BREAD);
		RM.food_can(IL.Food_Toast               .get(1), 8, "Canned Bread", IL.CANS_BREAD);
		RM.food_can(IL.Food_Toast_Sliced        .get(1), 1, "Canned Bread", IL.CANS_BREAD);
		CR.shaped(IL.Food_Toast_Sliced.get(8), CR.DEF_NCC, "kX", 'X', IL.Food_Toast);
		CR.shapeless(IL.Food_Toast_Raw.get(1), CR.DEF, new Object[] {"foodDough", "foodDough", "foodDough", "foodDough"});
		RM.Slicer.addRecipe2(T, 16, 16, IL.Food_Toast.get(1), IL.Shape_Slicer_Flat.get(0), IL.Food_Toast_Sliced.get(8));
		
		
		
		
		
		
		
		FoodsGT.put(ST.make(Items.mushroom_stew     , 1, W), 0,10, 0, 5, 0);
		
		
		
		
		IL.Food_Chum_On_Stick                  .set(addItem(tLastID = 10010, "Chum on a Stick"                          , "Don't forget to try our Chum-balaya"                         , new FoodStat( 5, 1.600F,   0, C+37,  0.10F,   0,   0,  20,   0,   0, EnumAction.eat, IL.Stick.get(1)                      , T, F, T, T, PotionsGT.ID_STICKY, 300, 0, 90, Potion.hunger.id, 1000, 4, 100, Potion.confusion.id, 300, 1, 80), TC.stack(TC.FAMES, 1), TC.stack(TC.CORPUS, 1), new OreDictItemData(ANY.Wood, U2)));
		CR.shapeless(IL.Food_Chum_On_Stick.get(1), CR.DEF_NCC, new Object[] {OP.stick.dat(ANY.Wood), "foodChum"});
		
		
		
		IL.Food_Dough_Egg_Flat                 .set(addItem(tLastID = 11000, "Flattened Egg Dough"                      , "For making Pasta"                                            , new FoodStat( 1, 0.600F,   0, C+37,  0.10F,   0,   0,   0,   5,   5, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1)));
		CR.shapeless(IL.Food_Dough_Egg_Flat.get(1), CR.DEF, new Object[] {IL.Food_Dough_Egg, OreDictToolNames.rollingpin});
		RM.RollingMill.addRecipe1(T, 16,   16, IL.Food_Dough_Egg.get(1), IL.Food_Dough_Egg_Flat.get(1));
		
		
		
		IL.Food_Ice_Cream                      .set(addItem(tLastID = 13000, "Ice Cream"                                , "Basic Milk Gelato"                               , "foodIcecream"                    , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Stracciatella        .set(addItem(tLastID = 13001, "Stracciatella Ice Cream"                  , "Fine Chocolate Chip Gelato"                      , "foodStracciatellaicecream"       , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Raisin               .set(addItem(tLastID = 13002, "Raisin Ice Cream"                         , "Dry and Grapey Gelato"                           , "foodRaisinicecream"              , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Vanilla              .set(addItem(tLastID = 13003, "Vanilla Ice Cream"                        , "Standard Vanilla Gelato"                         , "foodVanillaicecream"             , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Chocolate            .set(addItem(tLastID = 13004, "Chocolate Ice Cream"                      , "Standard Chocolate Gelato"                       , "foodChocolateicecream"           , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Mocha                .set(addItem(tLastID = 13005, "Mocha Ice Cream"                          , "Coffee based Gelato"                             , "foodMochaicecream"               , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,  20,   5,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Caramel              .set(addItem(tLastID = 13006, "Caramel Ice Cream"                        , "Sugary Gelato"                                   , "foodCaramelicecream"             , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  30,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Mint                 .set(addItem(tLastID = 13007, "Mint Ice Cream"                           , "Fresh smelling Gelato"                           , "foodMinticecream"                , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  10,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Mint_Chocolate_Chip  .set(addItem(tLastID = 13008, "Mint Chocolate Chip Ice Cream"            , "Minty Chocolate Chip Gelato"                     , "foodMintchocolatechipicecream"   , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Strawberry           .set(addItem(tLastID = 13009, "Strawberry Ice Cream"                     , "Fruity Red Gelato"                               , "foodStrawberryicecream"          , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Cherry               .set(addItem(tLastID = 13010, "Cherry Ice Cream"                         , "Fruity Red Gelato"                               , "foodCherryicecream"              , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Blueberry            .set(addItem(tLastID = 13011, "Blueberry Ice Cream"                      , "Smurfy Blue Gelato"                              , "foodBlueberryicecream"           , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Currant              .set(addItem(tLastID = 13012, "Currant Ice Cream"                        , "Most Current Gelato"                             , "foodCurranticecream"             , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Blackberry           .set(addItem(tLastID = 13013, "Blackberry Ice Cream"                     , "Fruity Dark Gelato"                              , "foodBlackberryicecream"          , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Raspberry            .set(addItem(tLastID = 13014, "Raspberry Ice Cream"                      , "Fruity Red Gelato"                               , "foodRaspberryicecream"           , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Cranberry            .set(addItem(tLastID = 13015, "Cranberry Ice Cream"                      , "Fruity Red Gelato"                               , "foodCranberryicecream"           , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Gooseberry           .set(addItem(tLastID = 13016, "Gooseberry Ice Cream"                     , "Fruity Yellow Gelato"                            , "foodGooseberryicecream"          , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Nutella              .set(addItem(tLastID = 13017, "Nutella Ice Cream"                        , "Perfectly flavoured Gelato"                      , "foodNutellaicecream"             , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  15,  15, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Lemon                .set(addItem(tLastID = 13018, "Lemon Ice Cream"                          , "Sour Yellow Gelato"                              , "foodLemonicecream"               , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Maple                .set(addItem(tLastID = 13019, "Maple Ice Cream"                          , "Maple flavoured Gelato"                          , "foodMapleicecream"               , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  30,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Pistachio            .set(addItem(tLastID = 13020, "Pistachio Ice Cream"                      , "Nut flavoured Gelato"                            , "foodPistachioicecream"           , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  10,  10, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Bacon                .set(addItem(tLastID = 13021, "Bacon Ice Cream"                          , "Manly Gelato"                                    , "foodBaconicecream"               , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.CORPUS, 1)));
		IL.Food_Ice_Cream_Kiwi                 .set(addItem(tLastID = 13022, "Kiwi Ice Cream"                           , "Cute Green Gelato"                               , "foodKiwiicecream"                , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Melon                .set(addItem(tLastID = 13023, "Melon Ice Cream"                          , "Watery Red Gelato"                               , "foodMelonicecream"               , new FoodStat( 1, 0.600F,  10, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Banana               .set(addItem(tLastID = 13024, "Banana Ice Cream"                         , "Minion Gelato"                                   , "foodBananaicecream"              , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15, 0,2, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Grape                .set(addItem(tLastID = 13025, "Grape Ice Cream"                          , "Watery Green Gelato"                             , "foodGrapeicecream"               , new FoodStat( 1, 0.600F,  10, C+35,  0.50F,   0,   0,   5,  20,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Apple                .set(addItem(tLastID = 13026, "Apple Ice Cream"                          , "Fruity Yellow Gelato"                            , "foodAppleicecream"               , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Peanut_Butter        .set(addItem(tLastID = 13027, "Peanut Butter Ice Cream"                  , "It's Peanut Butter Ice Cream Time!"              , "foodPeanutbuttericecream"        , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  15,  15, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Ananas               .set(addItem(tLastID = 13028, "Ananas Ice Cream"                         , "Pineapple Gelato"                                , "foodPineappleicecream"           , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.ARBOR, 1)));
		IL.Food_Ice_Cream_Chum                 .set(addItem(tLastID = 13029, "Chum Ice Cream"                           , "Fum is Fum!"                                     , "foodChumicecream"                , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,  20,  10,   0, EnumAction.eat, null                                 , T, F, T, T, Potion.hunger.id, 250, 4, 100, Potion.confusion.id, 100, 1, 80), TC.stack(TC.GELUM, 1), TC.stack(TC.CORPUS, 1)));
		IL.Food_Ice_Cream_Honey                .set(addItem(tLastID = 13030, "Honey Ice Cream"                          , "Bee the Gelato"                                  , "foodHoneyicecream"               , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  25,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.BESTIA, 1)));
		IL.Food_Ice_Cream_Bear                 .set(addItem(tLastID = 13989, "Bear Ice Cream"                           , "Gelato for Bears!"                               , "foodBearicecream"                , new FoodStat( 2, 1.200F,   0, C+35,  0.50F,   0,   0,   5,  10,   5, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.BESTIA, 1)));
		IL.Food_Ice_Cream_Neapolitan           .set(addItem(tLastID = 13995, "Neapolitan Ice Cream"                     , "Gelato mix of Strawberry, Vanilla and Chocolate" , "foodNeapolitanicecream"          , new FoodStat( 1, 0.600F,   2, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Spumoni_Chocolate    .set(addItem(tLastID = 13996, "Spumoni Chocolate Ice Cream"              , "Gelato mix of Cherry, Pistachio and Chocolate"   , "foodSpumoniicecream"             , new FoodStat( 1, 0.600F,   2, C+35,  0.50F,   0,   0,   5,  18,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Spumoni_Vanilla      .set(addItem(tLastID = 13997, "Spumoni Vanilla Ice Cream"                , "Gelato mix of Cherry, Pistachio and Vanilla"     , "foodSpumoniicecream"             , new FoodStat( 1, 0.600F,   2, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Superman             .set(addItem(tLastID = 13998, "Superman Ice Cream"                       , "Gelato that saves the Day!"                      , "foodSupermanicecream"            , new FoodStat( 1, 0.600F,   5, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T), TC.stack(TC.GELUM, 1), TC.stack(TC.FAMES, 1)));
		IL.Food_Ice_Cream_Rainbow              .set(addItem(tLastID = 13999, "Rainbow Ice Cream"                        , "Gelato of Friendship and Magic"                  , "foodRainbowicecream"             , new FoodStat( 1, 0.600F,   0, C+35,  0.50F,   0,   0,   5,  15,   0, EnumAction.eat, null                                 , F, T, F, T, Potion.regeneration.id, 10, 4, 100, Potion.digSpeed.id, 200, 2, 100), TC.stack(TC.GELUM, 1), TC.stack(TC.AURAM, 1)));
		
		for (FluidStack tWater : FL.waters(250)) {
		RM.CryoMixer.addRecipe1(T, 16, 16, OM.dust(MT.NaCl, U4), FL.array(tWater           , FL.Cream.make( 250)), ZL_FS, IL.Food_Ice_Cream.get(1));
		RM.CryoMixer.addRecipe1(T, 16, 64, OM.dust(MT.NaCl, U ), FL.array(FL.mul(tWater, 4), FL.Cream.make(1000)), ZL_FS, IL.Food_Ice_Cream.get(4));
		}
		
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Lemon             .make( 50), NF, IL.Food_Ice_Cream_Lemon.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Banana            .make( 50), NF, IL.Food_Ice_Cream_Banana.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Grape_Red         .make( 50), NF, IL.Food_Ice_Cream_Grape.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Grape_White       .make( 50), NF, IL.Food_Ice_Cream_Grape.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Grape_Green       .make( 50), NF, IL.Food_Ice_Cream_Grape.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Grape_Purple      .make( 50), NF, IL.Food_Ice_Cream_Grape.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Apple             .make( 50), NF, IL.Food_Ice_Cream_Apple.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_AppleGrC          .make( 50), NF, IL.Food_Ice_Cream_Apple.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Ananas            .make( 50), NF, IL.Food_Ice_Cream_Ananas.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Cherry            .make( 50), NF, IL.Food_Ice_Cream_Cherry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Cranberry         .make( 50), NF, IL.Food_Ice_Cream_Cranberry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Strawberry        .make( 50), NF, IL.Food_Ice_Cream_Strawberry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Kiwi              .make( 50), NF, IL.Food_Ice_Cream_Kiwi.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Melon             .make( 50), NF, IL.Food_Ice_Cream_Melon.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Currant           .make( 50), NF, IL.Food_Ice_Cream_Currant.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Raspberry         .make( 50), NF, IL.Food_Ice_Cream_Raspberry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Blackberry        .make( 50), NF, IL.Food_Ice_Cream_Blackberry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Blueberry         .make( 50), NF, IL.Food_Ice_Cream_Blueberry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Juice_Gooseberry        .make( 50), NF, IL.Food_Ice_Cream_Gooseberry.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.RoyalJelly              .make(  5), NF, IL.Food_Ice_Cream_Honey.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Honey                   .make( 50), NF, IL.Food_Ice_Cream_Honey.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.HoneyGrC                .make( 50), NF, IL.Food_Ice_Cream_Honey.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.HoneyBoP                .make( 50), NF, IL.Food_Ice_Cream_Honey.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), MT.Chocolate           .liquid(U4, T), NF, IL.Food_Ice_Cream_Chocolate.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Syrup_Maple             .make( 50), NF, IL.Food_Ice_Cream_Maple.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Cream_Nutella           .make( 50), NF, IL.Food_Ice_Cream_Nutella.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Nutbutter_Peanut        .make( 50), NF, IL.Food_Ice_Cream_Peanut_Butter.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, IL.Food_Ice_Cream.get(1), FL.Sap_Rainbow             .make( 50), NF, IL.Food_Ice_Cream_Rainbow.get(1));
		
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream.get(1), OM.dust(MT.Chocolate   , U4), IL.Food_Ice_Cream_Stracciatella.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream.get(4), OM.dust(MT.Chocolate   , U ), IL.Food_Ice_Cream_Stracciatella.get(4));
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream.get(1), OM.dust(MT.Vanilla     , U4), IL.Food_Ice_Cream_Vanilla.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream.get(4), OM.dust(MT.Vanilla     , U ), IL.Food_Ice_Cream_Vanilla.get(4));
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream.get(1), OM.dust(MT.Coffee      , U4), IL.Food_Ice_Cream_Mocha.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream.get(4), OM.dust(MT.Coffee      , U ), IL.Food_Ice_Cream_Mocha.get(4));
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream.get(1), OM.dust(MT.Mint        , U4), IL.Food_Ice_Cream_Mint.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream.get(4), OM.dust(MT.Mint        , U ), IL.Food_Ice_Cream_Mint.get(4));
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream.get(1), OM.dust(MT.Pistachio   , U4), IL.Food_Ice_Cream_Pistachio.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream.get(4), OM.dust(MT.Pistachio   , U ), IL.Food_Ice_Cream_Pistachio.get(4));
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream.get(1), OM.dust(MT.MeatCooked  , U4), IL.Food_Ice_Cream_Bear.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream.get(4), OM.dust(MT.MeatCooked  , U ), IL.Food_Ice_Cream_Bear.get(4));
		
		RM.Mixer.addRecipe2(T, 16, 16, IL.Food_Ice_Cream_Mint.get(1), OM.dust(MT.Chocolate  , U4), IL.Food_Ice_Cream_Mint_Chocolate_Chip.get(1));
		RM.Mixer.addRecipe2(T, 16, 64, IL.Food_Ice_Cream_Mint.get(4), OM.dust(MT.Chocolate  , U ), IL.Food_Ice_Cream_Mint_Chocolate_Chip.get(4));
		
		RM.Mixer.addRecipeX(T, 16, 48, ST.array(IL.Food_Ice_Cream_Strawberry.get(1), IL.Food_Ice_Cream_Vanilla  .get(1), IL.Food_Ice_Cream_Chocolate.get(1)), IL.Food_Ice_Cream_Neapolitan.get(3));
		RM.Mixer.addRecipeX(T, 16, 48, ST.array(IL.Food_Ice_Cream_Cherry    .get(1), IL.Food_Ice_Cream_Pistachio.get(1), IL.Food_Ice_Cream_Vanilla  .get(1)), IL.Food_Ice_Cream_Spumoni_Vanilla.get(3));
		RM.Mixer.addRecipeX(T, 16, 48, ST.array(IL.Food_Ice_Cream_Cherry    .get(1), IL.Food_Ice_Cream_Pistachio.get(1), IL.Food_Ice_Cream_Chocolate.get(1)), IL.Food_Ice_Cream_Spumoni_Chocolate.get(3));
		RM.Mixer.addRecipeX(T, 16, 48, ST.array(IL.Food_Ice_Cream_Cherry    .get(1), IL.Food_Ice_Cream_Blueberry.get(1), IL.Food_Ice_Cream_Lemon    .get(1)), IL.Food_Ice_Cream_Superman.get(3));
		
		
		
		
		
		
		
		
		
		
		IL.Pill_Empty                          .set(addItem(tLastID = 31000, "Empty Wax Pill"                           , "Placebo"                                                     , new FoodStat( 0, 0.000F,   0, C+37,  0.00F,   0,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , T, F, F, T                                     ), TC.stack(TC.SANO, 1), TC.stack(TC.VACUOS , 1))); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		IL.Pill_Iodine                         .set(addItem(tLastID = 31001, "Radaway"                                  , "Pill that cures Effects of Radiation"                        , new FoodStat( 0, 0.000F,   0, C+37,  0.00F,   0,   0,   0,   0,   0, -50, EnumAction.eat, null                                 , T, F, F, T, PotionsGT.ID_RADIATION, -1, -1, 100), TC.stack(TC.SANO, 3)                         )); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		IL.Pill_Mint                           .set(addItem(tLastID = 31002, "Peppermint"                               , "Take a fresh energetic Breath"                               , new FoodStat( 0, 0.000F,   0, C+37,  0.00F,   0,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , T, F, F, T, Potion.moveSlowdown.id, -1, -1, 100), TC.stack(TC.SANO, 1), TC.stack(TC.HERBA  , 1))); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		IL.Pill_Blue                           .set(addItem(tLastID = 31003, "Blue Pill"                                , "Ignore the nauseating Reality"                               , new FoodStat( 0, 0.000F,   0, C+37,  0.00F,   0,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , T, F, F, T, Potion.confusion   .id, -1, -1, 100), TC.stack(TC.SANO, 1), TC.stack(TC.CORPUS , 1))); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		IL.Pill_Red                            .set(addItem(tLastID = 31004, "Red Pill"                                 , "Open your Eyes to the Truth"                                 , new FoodStat( 0, 0.000F,   0, C+37,  0.00F,   0,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , T, F, F, T, Potion.blindness   .id, -1, -1, 100), TC.stack(TC.SANO, 1), TC.stack(TC.SENSUS , 1))); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		IL.Pill_Antidote                       .set(addItem(tLastID = 31005, "Antidote"                                 , "Pill that cures Poison"                                      , new FoodStat( 0, 0.000F,   0, C+37,  0.00F,   0,   0,   0,   0,   0,   0, EnumAction.eat, null                                 , T, F, F, T, Potion.poison      .id, -1, -1, 100), TC.stack(TC.SANO, 1), TC.stack(TC.VENENUM, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		RM.Boxinator    .addRecipe2(T, 16,   16, OM.dust(MT.I)                          , IL.Pill_Empty.get(1), IL.Pill_Iodine.get(1));
		RM.Boxinator    .addRecipe2(T, 16,   16, OM.dust(MT.Mint)                       , IL.Pill_Empty.get(1), IL.Pill_Mint.get(1));
		RM.Boxinator    .addRecipe2(T, 16,   16, ST.make(Blocks.brown_mushroom, 1, W)   , IL.Pill_Empty.get(1), IL.Pill_Antidote.get(1));
		
		IL.Pill_Cure_All                       .set(addItem(tLastID = 31999, "Cure All"                                 , "Cures everything you could imagine*"                         , new FoodStat( 0, 0.000F,   0, C+37,  1.00F,-999,-999,-999,-999,-999,-999, EnumAction.eat, null                                 , T, F, F, T, PotionsGT.ID_RADIATION, -1, -1, 100, PotionsGT.ID_HYPOTHERMIA, -1, -1, 100, PotionsGT.ID_HEATSTROKE, -1, -1, 100, PotionsGT.ID_FROSTBITE, -1, -1, 100, PotionsGT.ID_DEHYDRATION, -1, -1, 100, PotionsGT.ID_INSANITY, -1, -1, 100, PotionsGT.ID_FLAMMABLE, -1, -1, 100, PotionsGT.ID_SLIPPERY, -1, -1, 100, PotionsGT.ID_CONDUCTIVE, -1, -1, 100, PotionsGT.ID_STICKY, -1, -1, 100, Potion.digSlowdown.id, -1, -1, 100, Potion.moveSlowdown.id, -1, -1, 100, Potion.hunger.id, -1, -1, 100, Potion.harm.id, -1, -1, 100, Potion.confusion.id, -1, -1, 100, Potion.blindness.id, -1, -1, 100, Potion.weakness.id, -1, -1, 100, Potion.poison.id, -1, -1, 100, Potion.wither.id, -1, -1, 100, Potion.regeneration.id, 100, 100, 100, Potion.field_76443_y.id, 100, 100, 100).setMilk().setExtinguish(), new Behavior_CureZombie(500, F), TC.stack(TC.SANO, 10), TD.Creative.HIDDEN)); Sandwiches.INGREDIENTS.put(last(), (byte)42);
		
		
		
		IL.Food_Tofu                           .set(addItem(tLastID = 32101, "Tofu Bar"                                 , "Alternative for Meat"                , OP.ingot.dat(MT.Tofu)         , new FoodStat( 2, 1.400F,   0, C+37,  0.10F,   0,   0,   4,   4,   0, EnumAction.eat, null                         , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)35);
		IL.Food_SoylentGreen                   .set(addItem(tLastID = 32103, "Emerald Green Bar"                        , "Emerald Green is Villagers!"         , OP.ingot.dat(MT.SoylentGreen) , new FoodStat( 3, 1.400F,   0, C+37,  0.10F,   0,   0,   4,   0,  12, EnumAction.eat, null                         , F, T, F, T), TC.stack(TC.CORPUS, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)34);
		IL.Food_Meat_Raw                       .set(addItem(tLastID = 32105, "Raw Meat Bar"                             , "Don't eat raw Mince Meat"            , OP.ingot.dat(MT.MeatRaw)      , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   4,   0,  12, EnumAction.eat, null                         , F, T, F, T, Potion.hunger.id, 300, 0, 50), TC.stack(TC.CORPUS, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)29);
		IL.Food_Meat                           .set(addItem(tLastID = 32107, "Cooked Meat Bar"                          , "Compressed Meat"                     , OP.ingot.dat(MT.MeatCooked)   , new FoodStat( 2, 1.600F,   0, C+38,  0.50F,   0,   0,   4,   0,   4, EnumAction.eat, null                         , F, T, F, T), TC.stack(TC.CORPUS, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)30);
		IL.Food_Chocolate                      .set(addItem(tLastID = 32109, "Chocolate Bar"                            , "Not for Canines or Felines!"         , OP.ingot.dat(MT.Chocolate)    , new FoodStat( 4, 0.800F,   0, C+37,  0.10F,   0,   0,   0,  40,   0, EnumAction.eat, null                         , F, T, F, T), TC.stack(TC.HERBA, 1), TC.stack(TC.MOTUS, 1), TC.stack(TC.FAMES, 1), Behavior_FeedChocolate.INSTANCE)); OreDictManager.INSTANCE.setTarget_(OP.ingot, MT.Chocolate, ST.make(this, 1, tLastID)); Sandwiches.INGREDIENTS.put(last(), (byte)21);
		IL.Food_Cheese_Bar                     .set(addItem(tLastID = 32111, "Cheese Bar"                               , "Compact Cheese"                      , OP.ingot.dat(MT.Cheese)       , new FoodStat( 2, 1.200F,   0, C+37,  0.10F,   0,   0,   8,   0,   8, EnumAction.eat, null                         , F, T, F, T), TC.stack(TC.FAMES, 2)));
		IL.Food_Fish_Raw                       .set(addItem(tLastID = 32113, "Raw Fish Bar"                             , "Compressed Fish"                     , OP.ingot.dat(MT.FishRaw)      , new FoodStat( 2, 0.600F,   0, C+37,  0.10F,   0,   0,   3,   0,   8, EnumAction.eat, null                         , F, T, F, T, Potion.hunger.id, 300, 0, 50), TC.stack(TC.CORPUS, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)24);
		IL.Food_Fish                           .set(addItem(tLastID = 32115, "Cooked Fish Bar"                          , "Like Fish Sticks? You're a gay Fish" , OP.ingot.dat(MT.FishCooked)   , new FoodStat( 2, 1.600F,   0, C+38,  0.50F,   0,   0,   3,   0,   8, EnumAction.eat, null                         , F, T, F, T), TC.stack(TC.CORPUS, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.FAMES, 1))); Sandwiches.INGREDIENTS.put(last(), (byte)25);
		IL.Food_Butter                         .set(addItem(tLastID = 32117, "Butter"                                   , "A chunk of pure Fat"                 , OP.ingot.dat(MT.Butter)       , new FoodStat( 1, 4.000F,   0, C+37,  0.10F,   0,   0,   0,   0,  80, EnumAction.eat, null                         , F, T, F, T, PotionsGT.ID_SLIPPERY, 300, 1, 90), TC.stack(TC.FAMES, 3))); OreDictManager.INSTANCE.setTarget_(OP.ingot, MT.Butter      , ST.make(this, 1, tLastID)); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Yellow);
		IL.Food_Butter_Salted                  .set(addItem(tLastID = 32119, "Salted Butter"                            , "As if it wasn't unhealthy already"   , OP.ingot.dat(MT.ButterSalted) , new FoodStat( 1, 4.000F,   0, C+37,  0.10F,   0,   0,  40,   0,  80, EnumAction.eat, null                         , F, T, F, T, PotionsGT.ID_SLIPPERY, 300, 1, 90), TC.stack(TC.FAMES, 3))); OreDictManager.INSTANCE.setTarget_(OP.ingot, MT.ButterSalted, ST.make(this, 1, tLastID)); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Yellow);
		RM.replicateOrganic( 7,  8, IL.Food_Tofu.get(1));
		RM.replicateOrganic( 7,  9, IL.Food_SoylentGreen.get(1));
		RM.replicateOrganic( 7, 10, IL.Food_Meat_Raw.get(1));
		RM.replicateOrganic( 7, 11, IL.Food_Chocolate.get(1));
		RM.replicateOrganic( 7, 12, IL.Food_Cheese_Bar.get(1));
		RM.replicateOrganic( 7, 13, IL.Food_Fish_Raw.get(1));
		RM.replicateOrganic( 7, 14, IL.Food_Butter.get(1));
		
		RM.replicateOrganic( 8,  9, ST.make(Blocks.brown_mushroom_block, 1, 0));
		RM.replicateOrganic( 8, 10, ST.make(Blocks.red_mushroom_block, 1, 0));
		RM.replicateOrganic( 8, 11, ST.make(Items.nether_wart, 1, 0));
		RM.replicateOrganic( 8, 12, OP.plantGtWart.mat(MT.Milk, 1));
		RM.replicateOrganic( 8, 13, OP.plantGtWart.mat(MT.Glowstone, 1));
		
		RM.replicateOrganic( 9, 10, OP.plantGtBlossom.mat(MT.Tea, 1));
		RM.replicateOrganic( 9, 11, OP.plantGtBlossom.mat(MT.Mint, 1));
		RM.replicateOrganic( 9, 12, OP.plantGtBlossom.mat(MT.Indigo, 1));
		
		RM.replicateOrganic(10, 11, IL.Food_White_Egg.get(1));
		RM.replicateOrganic(10, 12, ST.make(Items.feather, 1, 0));
		RM.replicateOrganic(10, 13, ST.make(Items.leather, 1, 0));
		RM.replicateOrganic(10, 14, ST.make(Blocks.web, 1, 0));
		RM.replicateOrganic(10, 15, ST.make(Items.bone, 1, 0));
		RM.replicateOrganic(10, 16, ST.make(Items.slime_ball, 1, 0));
		RM.replicateOrganic(10, 17, ST.make(Items.spider_eye, 1, 0));
		RM.replicateOrganic(10, 18, ST.make(Items.ghast_tear, 1, 0));
		RM.replicateOrganic(10, 19, ST.make(Items.reeds, 1, 0));
		RM.replicateOrganic(10, 20, ST.make(Blocks.cactus, 1, 0));
		RM.replicateOrganic(10, 21, ST.make(Blocks.vine, 1, 0));
		RM.replicateOrganic(10, 22, ST.make(Blocks.waterlily, 1, 0));
		RM.replicateOrganic(10, 23, IL.Dye_SquidInk.get(1));
		RM.replicateOrganic(10, 24, IL.Dye_Cocoa.get(1));
	}
	
	@Override
	public ItemStack getRotten(ItemStack aStack) {
		short aMeta = ST.meta_(aStack);
		if (UT.Code.inside(    0,   999, aMeta)) return (IL.ENVM_Rotten_Food.exists()?IL.ENVM_Rotten_Food:IL.Remains_Plant).get(aStack.stackSize);
		if (UT.Code.inside( 1100,  1999, aMeta)) return ST.make(Items.rotten_flesh, aStack.stackSize, 0);
		if (UT.Code.inside(13000, 13999, aMeta)) return null;
		if (UT.Code.inside(31000, 31999, aMeta)) return aStack;
		
		switch(aMeta) {
		case 12000: return ST.make(this, aStack.stackSize, 12002, aStack.getTagCompound());
		case 12001: return ST.make(this, aStack.stackSize, 12002, aStack.getTagCompound());
		case 12002: return ST.make(this, aStack.stackSize, 12003, aStack.getTagCompound());
		case 12004: return ST.make(this, aStack.stackSize, 12002, aStack.getTagCompound());
		case 12005: return ST.make(this, aStack.stackSize, 12002, aStack.getTagCompound());
		case 12006: return ST.make(this, aStack.stackSize, 12002, aStack.getTagCompound());
		case 12007: return ST.make(this, aStack.stackSize, 12002, aStack.getTagCompound());
		case 32700: return (IL.ENVM_Rotten_Food.exists()?IL.ENVM_Rotten_Food:IL.Remains_Plant).get(aStack.stackSize);
		case 32701: return (IL.ENVM_Rotten_Food.exists()?IL.ENVM_Rotten_Food:IL.Remains_Plant).get(aStack.stackSize);
		case 32105: case 32107: return OP.ingot.mat(MT.MeatRotten, aStack.stackSize);
		case 32113: case 32115: return OP.ingot.mat(MT.FishRotten, aStack.stackSize);
		
		default: return ST.food(aStack) > 0 ? IL.ENVM_Rotten_Food.exists() ? IL.ENVM_Rotten_Food.get(aStack.stackSize) : null : aStack;
		}
	}
	
	@Override public ItemStack getRotten(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {return getRotten(aStack);}
}
