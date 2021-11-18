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

import static gregapi.compat.thaumcraft.ICompatTC.*;
import static gregapi.data.CS.*;

import java.util.Arrays;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.CS.FoodsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.data.TC;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class Compat_Recipes_Thaumcraft extends CompatMods {
	public Compat_Recipes_Thaumcraft(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing TC Recipes and Research.");
		RM.food_can(IL.TC_Nugget_Chicken    .wild(9), 8, "Canned Chicken Nuggets", IL.CANS_MEAT);
		RM.food_can(IL.TC_Nugget_Beef       .wild(9), 8, "Canned Beef Nuggets", IL.CANS_MEAT);
		RM.food_can(IL.TC_Nugget_Pork       .wild(9), 8, "Canned Pork Nuggets", IL.CANS_MEAT);
		RM.food_can(IL.TC_Nugget_Fish       .wild(9), 8, "Canned Fish Nuggets", IL.CANS_FISH);
		RM.food_can(IL.TC_Triple_Meat_Treat .wild(1), 6, "Canned Meat Treat", IL.CANS_MEAT);
		
		FoodsGT.put(IL.TC_Nugget_Chicken    .wild(1), 0, 0, 0, 0, 1);
		FoodsGT.put(IL.TC_Nugget_Beef       .wild(1), 0, 0, 0, 0, 1);
		FoodsGT.put(IL.TC_Nugget_Pork       .wild(1), 0, 0, 0, 0, 1);
		FoodsGT.put(IL.TC_Nugget_Fish       .wild(1), 0, 0, 0, 0, 1);
		FoodsGT.put(IL.TC_Triple_Meat_Treat .wild(1), 0, 0, 0,30,10);
		
		CR.shapeless(OP.ingot.mat(MT.Hg, 1), new Object[] {IL.Bottle_Mercury.get(1)});
		
		CR.shaped(IL.TC_Phial.get(8), CR.DEF_NCC, " C ", "G G", " G ", 'C', OD.itemClay, 'G', OD.blockGlassColorless);
		
		RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.string, 1, W), MT.Tallow.liquid(U*2, T), NF, ST.make(MD.TC, "blockCandle", 1, 0), ST.make(MD.TC, "blockCandle", 1, 0), ST.make(MD.TC, "blockCandle", 1, 0));
		
		RM.Mixer.addRecipeX(T, 16, 16, ST.array(OM.dust(MT.Sugar)               , IL.TC_Nugget_Chicken  .wild(1), IL.TC_Nugget_Fish.wild(1), IL.TC_Nugget_Beef.wild(1)), IL.TC_Triple_Meat_Treat.get(1));
		RM.Mixer.addRecipeX(T, 16, 16, ST.array(OM.dust(MT.Sugar)               , IL.TC_Nugget_Chicken  .wild(1), IL.TC_Nugget_Fish.wild(1), IL.TC_Nugget_Pork.wild(1)), IL.TC_Triple_Meat_Treat.get(1));
		RM.Mixer.addRecipeX(T, 16, 16, ST.array(OM.dust(MT.Sugar)               , IL.TC_Nugget_Chicken  .wild(1), IL.TC_Nugget_Beef.wild(1), IL.TC_Nugget_Pork.wild(1)), IL.TC_Triple_Meat_Treat.get(1));
		RM.Mixer.addRecipeX(T, 16, 16, ST.array(OM.dust(MT.Sugar)               , IL.TC_Nugget_Fish     .wild(1), IL.TC_Nugget_Beef.wild(1), IL.TC_Nugget_Pork.wild(1)), IL.TC_Triple_Meat_Treat.get(1));
		RM.Mixer.addRecipeX(T, 16, 16, ST.array(OP.gemChipped.mat(MT.Sugar, 4)  , IL.TC_Nugget_Chicken  .wild(1), IL.TC_Nugget_Fish.wild(1), IL.TC_Nugget_Beef.wild(1)), IL.TC_Triple_Meat_Treat.get(1));
		RM.Mixer.addRecipeX(T, 16, 16, ST.array(OP.gemChipped.mat(MT.Sugar, 4)  , IL.TC_Nugget_Chicken  .wild(1), IL.TC_Nugget_Fish.wild(1), IL.TC_Nugget_Pork.wild(1)), IL.TC_Triple_Meat_Treat.get(1));
		RM.Mixer.addRecipeX(T, 16, 16, ST.array(OP.gemChipped.mat(MT.Sugar, 4)  , IL.TC_Nugget_Chicken  .wild(1), IL.TC_Nugget_Beef.wild(1), IL.TC_Nugget_Pork.wild(1)), IL.TC_Triple_Meat_Treat.get(1));
		RM.Mixer.addRecipeX(T, 16, 16, ST.array(OP.gemChipped.mat(MT.Sugar, 4)  , IL.TC_Nugget_Fish     .wild(1), IL.TC_Nugget_Beef.wild(1), IL.TC_Nugget_Pork.wild(1)), IL.TC_Triple_Meat_Treat.get(1));
		
		RM.compact(ST.make(Items.rotten_flesh, 9, W), IL.TC_Block_Flesh.get(1));
		RM.unpack(IL.TC_Block_Flesh.get(1), ST.make(Items.rotten_flesh, 9, 0));
		
		RM.compact(IL.TC_Tallow.get(9), 9, IL.TC_Block_Tallow.get(1));
		RM.unpack(IL.TC_Block_Tallow.get(1), IL.TC_Tallow.get(9));
		if (MD.CHSL.mLoaded)
		RM.unpack(ST.make(MD.CHSL, "tallow", 1, W), IL.TC_Tallow.get(9));
		
		RM.ic2_compressor(                      OP.gem.mat(MT.Amber, 4), IL.TC_Block_Amber.get(1));
		RM.Compressor.addRecipe1(T, 16, 16,     OP.gem.mat(MT.Amber, 4), IL.TC_Block_Amber.get(1));
		RM.Boxinator.addRecipe2(T, 16, 16,      OP.gem.mat(MT.Amber, 4), ST.tag(4), IL.TC_Block_Amber_Bricks.get(1));
		RM.unpack(IL.TC_Block_Amber       .get(1), OP.gem.mat(MT.Amber, 4));
		RM.unpack(IL.TC_Block_Amber_Bricks.get(1), OP.gem.mat(MT.Amber, 4));
		
		Item tCrystal = ST.item(MD.TC, "blockCrystal"), tShard = ST.item(MD.TC, "ItemShard");
		for (int i = 0; i < 6; i++) {
		RM.compactsmash(ST.make(tShard, 6, i), 6, ST.make(tCrystal, 1, i));
		RM.unpack(ST.make(tCrystal, 1, i), ST.make(tShard, 6, i));
		}
		RM.Unboxinator.addRecipe1(T, 16, 16, ST.make(tCrystal, 1, 6), ST.make(tShard, 1, 0), ST.make(tShard, 1, 1), ST.make(tShard, 1, 2), ST.make(tShard, 1, 3), ST.make(tShard, 1, 4), ST.make(tShard, 1, 5));
		
		String tKey;
		OreDictMaterial tMat;
		
		tMat = MT.Charcoal;
		COMPAT_TC.addResearch(tKey = WOOD_TO_CHARCOAL
			, tMat.mNameLocal + " Transmutation", "Turning Logs into "+tMat.mNameLocal, new String[] {"ALUMENTUM"}, CATEGORY_ALCHEMY, OP.gem.mat(tMat, 1), 2, RESEARCH_TYPE_NORMAL, 13, 5
			, Arrays.asList(TC.stack(TC.ARBOR, 10), TC.stack(TC.VACUOS, 8), TC.stack(TC.IGNIS, 8))
			, null
			, LH.add("gt.research.page.1."+tKey, "You have discovered a way of making "+tMat.mNameLocal+" magically instead of using Coke Ovens for this purpose.<BR><BR>To create "+tMat.mNameLocal+" from Logs you first need an air-free environment, some vacuus essentia is needed for that, then you need to incinerate the Log using ignis essentia and wait until all the Water inside the Logs is burned away.<BR><BR>This method however doesn't create "+MT.Creosote.mNameLocal+" as byproduct.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OD.logWood, OP.gem.mat(tMat, 2), TC.stack(TC.VACUOS, 2), TC.stack(TC.IGNIS, 1))
		);
		COMPAT_TC.addResearch(tKey = FILL_WATER_BUCKET
			, "Water Transmutation", "Filling buckets with Water", null, CATEGORY_ALCHEMY, ST.make(Items.water_bucket, 1, 0), 2, RESEARCH_TYPE_NORMAL, 16, 5
			, Arrays.asList(TC.stack(TC.PERMUTATIO, 4), TC.stack(TC.AQUA, 4))
			, null
			, LH.add("gt.research.page.1." + tKey, "You have discovered a way of filling a Bucket with aqua essentia in order to simply get Water.")
			, COMPAT_TC.addCrucibleRecipe(tKey, ST.make(Items.bucket            , 1, W), ST.make(Items.water_bucket, 1, 0), TC.stack(TC.AQUA, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.Cell_Empty                   .get(1), IL.Cell_Water.get(1), TC.stack(TC.AQUA, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.FR_MagicCapsule              .get(1), FL.fill(FL.Water.make(16000), IL.FR_MagicCapsule              .get(1), F, F), TC.stack(TC.AQUA,10))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.FR_TinCapsule                .get(1), FL.fill(FL.Water.make( 1000), IL.FR_TinCapsule                .get(1), F, F), TC.stack(TC.AQUA, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.FR_WaxCapsule                .get(1), FL.fill(FL.Water.make( 1000), IL.FR_WaxCapsule                .get(1), F, F), TC.stack(TC.AQUA, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.FR_RefractoryCapsule         .get(1), FL.fill(FL.Water.make( 1000), IL.FR_RefractoryCapsule         .get(1), F, F), TC.stack(TC.AQUA, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.Wooden_Bucket_Copper         .get(1), FL.fill(FL.Water.make( 1000), IL.Wooden_Bucket_Copper         .get(1), F, F), TC.stack(TC.AQUA, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.Wooden_Bucket_Tin            .get(1), FL.fill(FL.Water.make( 1000), IL.Wooden_Bucket_Tin            .get(1), F, F), TC.stack(TC.AQUA, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.Wooden_Bucket_Zinc           .get(1), FL.fill(FL.Water.make( 1000), IL.Wooden_Bucket_Zinc           .get(1), F, F), TC.stack(TC.AQUA, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.Wooden_Bucket_Lead           .get(1), FL.fill(FL.Water.make( 1000), IL.Wooden_Bucket_Lead           .get(1), F, F), TC.stack(TC.AQUA, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.Wooden_Bucket_Bismuth        .get(1), FL.fill(FL.Water.make( 1000), IL.Wooden_Bucket_Bismuth        .get(1), F, F), TC.stack(TC.AQUA, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.Wooden_Bucket_Brass          .get(1), FL.fill(FL.Water.make( 1000), IL.Wooden_Bucket_Brass          .get(1), F, F), TC.stack(TC.AQUA, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.Wooden_Bucket_Bronze         .get(1), FL.fill(FL.Water.make( 1000), IL.Wooden_Bucket_Bronze         .get(1), F, F), TC.stack(TC.AQUA, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.Wooden_Bucket_BismuthBronze  .get(1), FL.fill(FL.Water.make( 1000), IL.Wooden_Bucket_BismuthBronze  .get(1), F, F), TC.stack(TC.AQUA, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, IL.Wooden_Bucket_Gold           .get(1), FL.fill(FL.Water.make( 1000), IL.Wooden_Bucket_Gold           .get(1), F, F), TC.stack(TC.AQUA, 4))
		);
		tMat = MT.Zn;
		COMPAT_TC.addResearch(tKey = TRANSZINC
			, tMat.mNameLocal + " Transmutation", "Transformation of Metals into "+tMat.mNameLocal, new String[] {"TRANSTIN"}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 2, RESEARCH_TYPE_SECONDARY, 9, 13
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.SANO, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "You have discovered a way to multiply "+tMat.mNameLocal+" by steeping "+tMat.mNameLocal+" Nuggets in Metallum harvested from other Metals.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(tMat), OP.nugget.mat(tMat, 3), TC.stack(TC.METALLUM, 2), TC.stack(TC.SANO, 1))
		);
		tMat = MT.Sb;
		COMPAT_TC.addResearch(tKey = TRANSANTIMONY
			, tMat.mNameLocal + " Transmutation", "Transformation of Metals into "+tMat.mNameLocal, new String[] {TRANSZINC, "TRANSLEAD"}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 2, RESEARCH_TYPE_SECONDARY, 9, 14
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.AQUA, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "You have discovered a way to multiply "+tMat.mNameLocal+" by steeping "+tMat.mNameLocal+" Nuggets in Metallum harvested from other Metals.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(tMat), OP.nugget.mat(tMat, 3), TC.stack(TC.METALLUM, 2), TC.stack(TC.AQUA, 1))
		);
		tMat = MT.Ni;
		COMPAT_TC.addResearch(tKey = TRANSNICKEL
			, tMat.mNameLocal + " Transmutation", "Transformation of Metals into "+tMat.mNameLocal, new String[] {"TRANSLEAD"}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 2, RESEARCH_TYPE_SECONDARY, 9, 15
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.IGNIS, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "You have discovered a way to multiply "+tMat.mNameLocal+" by steeping "+tMat.mNameLocal+" Nuggets in Metallum harvested from other Metals.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(tMat), OP.nugget.mat(tMat, 3), TC.stack(TC.METALLUM, 2), TC.stack(TC.IGNIS, 1))
		);
		tMat = MT.Co;
		COMPAT_TC.addResearch(tKey = TRANSCOBALT
			, tMat.mNameLocal + " Transmutation", "Transformation of Metals into "+tMat.mNameLocal, new String[] {TRANSNICKEL}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 2, RESEARCH_TYPE_SECONDARY, 9, 16
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.INSTRUMENTUM, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "You have discovered a way to multiply "+tMat.mNameLocal+" by steeping "+tMat.mNameLocal+" Nuggets in Metallum harvested from other Metals.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(tMat), OP.nugget.mat(tMat, 3), TC.stack(TC.METALLUM, 2), TC.stack(TC.INSTRUMENTUM, 1))
		);
		tMat = MT.Bi;
		COMPAT_TC.addResearch(tKey = TRANSBISMUTH
			, tMat.mNameLocal + " Transmutation", "Transformation of Metals into "+tMat.mNameLocal, new String[] {TRANSCOBALT}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 2, RESEARCH_TYPE_SECONDARY, 11, 17
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.INSTRUMENTUM, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "You have discovered a way to multiply "+tMat.mNameLocal+" by steeping "+tMat.mNameLocal+" Nuggets in Metallum harvested from other Metals.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(tMat), OP.nugget.mat(tMat, 3), TC.stack(TC.METALLUM, 2), TC.stack(TC.INSTRUMENTUM, 1))
		);
		tMat = MT.Steel;
		COMPAT_TC.addResearch(tKey = IRON_TO_STEEL
			, tMat.mNameLocal + " Transmutation", "Transforming "+MT.Fe.mNameLocal+" into "+MT.Steel.mNameLocal, new String[] {"TRANSIRON", WOOD_TO_CHARCOAL}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 3, RESEARCH_TYPE_NORMAL, 13, 8
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.ORDO, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "You have discovered a way of making "+MT.Fe.mNameLocal+" harder by just re-ordering its components.<BR><BR>This Method can be used to create a Material called "+tMat.mNameLocal+", which is used in many non-Thaumaturgic applications.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(MT.Fe), OP.nugget.mat(tMat, 1), TC.stack(TC.ORDO, 1))
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(MT.WroughtIron), OP.nugget.mat(tMat, 1), TC.stack(TC.ORDO, 1))
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(MT.MeteoricIron), OP.nugget.mat(MT.MeteoricSteel, 1), TC.stack(TC.ORDO, 1))
		);
		tMat = MT.Bronze;
		COMPAT_TC.addResearch(tKey = TRANSBRONZE
			, tMat.mNameLocal + " Transmutation", "Transformation of Metals into "+tMat.mNameLocal, new String[] {"TRANSTIN", "TRANSCOPPER"}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 2, RESEARCH_TYPE_NORMAL, 13, 11
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.INSTRUMENTUM, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "You have discovered a way of creating Alloys using the already known transmutations of "+MT.Cu.mNameLocal+" and "+MT.Sn.mNameLocal+".<BR><BR>This Method can be used to create "+tMat.mNameLocal+" directly without having to go through an alloying process.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(tMat), OP.nugget.mat(tMat, 3), TC.stack(TC.METALLUM, 2), TC.stack(TC.INSTRUMENTUM, 1))
		);
		tMat = MT.Electrum;
		COMPAT_TC.addResearch(tKey = TRANSELECTRUM
			, tMat.mNameLocal + " Transmutation", "Transformation of Metals into "+tMat.mNameLocal, new String[] {TRANSBRONZE, "TRANSGOLD", "TRANSSILVER"}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 2, RESEARCH_TYPE_SECONDARY, 11, 11
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.LUCRUM, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "Your discovery of "+MT.Bronze.mNameLocal+" Transmutation has lead you to the conclusion it works with other Alloys such as "+tMat.mNameLocal+" as well.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(tMat), OP.nugget.mat(tMat, 3), TC.stack(TC.METALLUM, 2), TC.stack(TC.LUCRUM, 1))
		);
		tMat = MT.Brass;
		COMPAT_TC.addResearch(tKey = TRANSBRASS
			, tMat.mNameLocal + " Transmutation", "Transformation of Metals into "+tMat.mNameLocal, new String[] {TRANSBRONZE, TRANSZINC}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 2, RESEARCH_TYPE_SECONDARY, 11, 12
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.INSTRUMENTUM, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "Your discovery of "+MT.Bronze.mNameLocal+" Transmutation has lead you to the conclusion it works with other Alloys such as "+tMat.mNameLocal+" as well.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(tMat), OP.nugget.mat(tMat, 3), TC.stack(TC.METALLUM, 2), TC.stack(TC.INSTRUMENTUM, 1))
		);
		tMat = MT.Invar;
		COMPAT_TC.addResearch(tKey = TRANSINVAR
			, tMat.mNameLocal + " Transmutation", "Transformation of Metals into "+tMat.mNameLocal, new String[] {TRANSBRONZE, TRANSNICKEL}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 2, RESEARCH_TYPE_SECONDARY, 11, 15
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.GELUM, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "Your discovery of "+MT.Bronze.mNameLocal+" Transmutation has lead you to the conclusion it works with other Alloys such as "+tMat.mNameLocal+" as well.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(tMat), OP.nugget.mat(tMat, 3), TC.stack(TC.METALLUM, 2), TC.stack(TC.GELUM, 1))
		);
		tMat = MT.Constantan;
		COMPAT_TC.addResearch(tKey = TRANSCONSTANTAN
			, tMat.mNameLocal + " Transmutation", "Transformation of Metals into "+tMat.mNameLocal, new String[] {TRANSBRONZE, TRANSNICKEL}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 2, RESEARCH_TYPE_SECONDARY, 11, 16
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.IGNIS, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "Your discovery of "+MT.Bronze.mNameLocal+" Transmutation has lead you to the conclusion it works with other Alloys such as "+tMat.mNameLocal+" as well.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(tMat), OP.nugget.mat(tMat, 3), TC.stack(TC.METALLUM, 2), TC.stack(TC.PERMUTATIO, 1), TC.stack(TC.IGNIS, 1))
		);
		tMat = MT.BatteryAlloy;
		COMPAT_TC.addResearch(tKey = TRANSBATTERYALLOY
			, tMat.mNameLocal + " Transmutation", "Transformation of Metals into "+tMat.mNameLocal, new String[] {TRANSBRONZE, TRANSANTIMONY}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 2, RESEARCH_TYPE_SECONDARY, 11, 13
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.IGNIS, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "Your discovery of "+MT.Bronze.mNameLocal+" Transmutation has lead you to the conclusion it works with other Alloys such as "+tMat.mNameLocal+" as well.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(tMat), OP.nugget.mat(tMat, 3), TC.stack(TC.METALLUM, 2), TC.stack(TC.AQUA, 1), TC.stack(TC.ORDO, 1))
		);
		tMat = MT.SolderingAlloy;
		COMPAT_TC.addResearch(tKey = TRANSSOLDERINGALLOY
			, tMat.mNameLocal + " Transmutation", "Transformation of Metals into "+tMat.mNameLocal, new String[] {TRANSBRONZE, TRANSANTIMONY}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 2, RESEARCH_TYPE_SECONDARY, 11, 14
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.IGNIS, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "Your discovery of "+MT.Bronze.mNameLocal+" Transmutation has lead you to the conclusion it works with other Alloys such as "+tMat.mNameLocal+" as well.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(tMat), OP.nugget.mat(tMat, 3), TC.stack(TC.METALLUM, 2), TC.stack(TC.AQUA, 1), TC.stack(TC.VITREUS, 1))
		);
		tMat = MT.DamascusSteel;
		COMPAT_TC.addResearch(tKey = ADVANCEDMETALLURGY
			, "Advanced Metallurgic Transmutation", "Mastering the basic Metals", new String[] {TRANSBISMUTH, IRON_TO_STEEL, TRANSSOLDERINGALLOY, TRANSBATTERYALLOY, TRANSBRASS, TRANSELECTRUM, TRANSCONSTANTAN, TRANSINVAR}, CATEGORY_ALCHEMY, OP.ingot.mat(tMat, 1), 4, RESEARCH_TYPE_HIDDEN, 16, 14
			, Arrays.asList(TC.stack(TC.METALLUM, 50), TC.stack(TC.PERMUTATIO, 20), TC.stack(TC.COGNITIO, 20), TC.stack(TC.PRAECANTIO, 20), TC.stack(TC.NEBRISUM, 20), TC.stack(TC.MAGNETO, 20))
			, ST.array(OP.dust.mat(tMat, 1), OP.ingot.mat(tMat, 1), OP.plate.mat(tMat, 1))
			, LH.add("gt.research.page.1." + tKey, "Now that you have discovered all basic Metals and found out about "+tMat.mNameLocal+", you can finally move on to the next Level of Magic Metallurgy and create more advanced Metals!")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(MT.Steel), OP.nugget.mat(tMat, 1), TC.stack(TC.ORDO, 1), TC.stack(TC.INSTRUMENTUM, 1), TC.stack(TC.TELUM, 1))
		);
		tMat = MT.Al;
		COMPAT_TC.addResearch(tKey = TRANSALUMINIUM
			, tMat.mNameLocal + " Transmutation", "Transformation of Metals into "+tMat.mNameLocal, new String[] {ADVANCEDMETALLURGY}, CATEGORY_ALCHEMY, OP.nugget.mat(tMat, 1), 4, RESEARCH_TYPE_NORMAL, 19, 14
			, Arrays.asList(TC.stack(TC.METALLUM, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.VOLATUS, 3), TC.stack(TC.ORDO, 3), TC.stack(TC.IGNIS, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "You have discovered a way to multiply "+tMat.mNameLocal+" by steeping "+tMat.mNameLocal+" Nuggets in Metallum harvested from other Metals.<BR><BR>This transmutation is slightly harder to achieve, because aluminium has special properties, which require more order to achieve the desired result.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.nugget.dat(tMat), OP.nugget.mat(tMat, 3), TC.stack(TC.METALLUM, 2), TC.stack(TC.VOLATUS, 1), TC.stack(TC.ORDO, 1), TC.stack(TC.LUCRUM, 1))
		);
		COMPAT_TC.addResearch(tKey = CRYSTALLISATION
			, "Shard Recrystallisation", "Fixing your precious Crystals", new String[] {"ALCHEMICALMANUFACTURE"}, CATEGORY_ALCHEMY, OP.gem.mat(MT.InfusedOrder, 1), 3, RESEARCH_TYPE_NORMAL, -11, -3
			, Arrays.asList(TC.stack(TC.VITREUS, 5), TC.stack(TC.PERMUTATIO, 3), TC.stack(TC.ORDO, 3))
			, null
			, LH.add("gt.research.page.1." + tKey, "Sometimes when processing your Crystal Shards, they become a pile of Dust instead of the mostly wanted Shard.<BR><BR>You have finally found a way to reverse this Process by using Vitreus Essentia for recrystallising the Shards.")
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.dust.dat(MT.Amber            ), OP.gem.mat(MT.Amber          , 1), TC.stack(TC.VITREUS, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.dust.dat(MT.InfusedOrder     ), OP.gem.mat(MT.InfusedOrder   , 1), TC.stack(TC.VITREUS, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.dust.dat(MT.InfusedEntropy   ), OP.gem.mat(MT.InfusedEntropy , 1), TC.stack(TC.VITREUS, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.dust.dat(MT.InfusedAir       ), OP.gem.mat(MT.InfusedAir     , 1), TC.stack(TC.VITREUS, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.dust.dat(MT.InfusedEarth     ), OP.gem.mat(MT.InfusedEarth   , 1), TC.stack(TC.VITREUS, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.dust.dat(MT.InfusedFire      ), OP.gem.mat(MT.InfusedFire    , 1), TC.stack(TC.VITREUS, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.dust.dat(MT.InfusedWater     ), OP.gem.mat(MT.InfusedWater   , 1), TC.stack(TC.VITREUS, 4))
			, COMPAT_TC.addCrucibleRecipe(tKey, OP.dust.dat(MT.InfusedBalance   ), OP.gem.mat(MT.InfusedBalance , 1), TC.stack(TC.VITREUS, 4))
		);
		/*
		tKey = IThaumcraftCompat.ADVANCEDENTROPICPROCESSING;
		LH.add("gt.research.page.1."+tKey, "You have to get some Dusts and don't want to use the Mortar or Macerator for doing that simple Task? Why don't you just use your knowledge of Entropic Processing to turn things into Dust?<BR><BR>With this brilliant Idea you are now capable of recycling almost everything using your Crucible and some Perditio Essentia.<BR><BR>Note: Does not work on Ores.");
		COMPAT_TC.addResearch(tKey
			, "Advanced Entropic Processing", "Turning everything into Dust", new String[] {"ENTROPICPROCESSING"}, CATEGORY_ALCHEMY, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron, 1), 3, IThaumcraftCompat.RESEARCH_TYPE_SECONDARY, -12, 1
			, Arrays.asList(TC.stack(TC_Aspects.PERDITIO, 10), TC.stack(TC_Aspects.PERFODIO, 6), TC.stack(TC_Aspects.FABRICO, 6), TC.stack(TC_Aspects.LUCRUM, 6), TC.stack(TC_Aspects.PRAECANTIO, 2))
			, N
			, LH.add("gt.research.page.1." + tKey, "")
		});*/
	}
}
