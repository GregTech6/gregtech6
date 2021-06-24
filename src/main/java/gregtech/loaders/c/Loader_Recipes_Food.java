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
import static gregapi.data.OP.*;

import gregapi.data.ANY;
import gregapi.data.CS.FluidsGT;
import gregapi.data.CS.FoodsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.OreDictPrefix;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.oredict.event.OreDictListenerEvent_TwoNames;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Loader_Recipes_Food implements Runnable {
	@Override public void run() {
		RM.food_can(ST.make(Items.rotten_flesh  , 1, W), 4, "Canned Meat", IL.CANS_ROTTEN);
		RM.food_can(ST.make(Items.spider_eye    , 1, W), 2, "Canned Spider Eye", IL.CANS_ROTTEN);
		
		for (ItemStack tStack : ST.array(dustTiny.mat(MT.FishCooked, 9), dustSmall.mat(MT.FishCooked, 4), dust.mat(MT.FishCooked, 1), nugget.mat(MT.FishCooked, 9), chunkGt.mat(MT.FishCooked, 4), ingot.mat(MT.FishCooked, 1)))
		RM.food_can(tStack, 2, "Canned Fish", IL.CANS_FISH);
		for (ItemStack tStack : ST.array(dustTiny.mat(MT.MeatCooked, 9), dustSmall.mat(MT.MeatCooked, 4), dust.mat(MT.MeatCooked, 1), nugget.mat(MT.MeatCooked, 9), chunkGt.mat(MT.MeatCooked, 4), ingot.mat(MT.MeatCooked, 1)))
		RM.food_can(tStack, 2, "Canned Meat", IL.CANS_MEAT);
		for (ItemStack tStack : ST.array(dustTiny.mat(MT.Tofu, 9), dustSmall.mat(MT.Tofu, 4), dust.mat(MT.Tofu, 1), nugget.mat(MT.Tofu, 9), chunkGt.mat(MT.Tofu, 4), ingot.mat(MT.Tofu, 1)))
		RM.food_can(tStack, 2, "Canned Tofu", IL.CANS_VEGGIE);
		for (ItemStack tStack : ST.array(dustTiny.mat(MT.SoylentGreen, 9), dustSmall.mat(MT.SoylentGreen, 4), dust.mat(MT.SoylentGreen, 1), nugget.mat(MT.SoylentGreen, 9), chunkGt.mat(MT.SoylentGreen, 4), ingot.mat(MT.SoylentGreen, 1)))
		RM.food_can(tStack, 2, "Canned Emerald Green", IL.CANS_VEGGIE);
		
		OreDictPrefix[] tPrefixListA = new OreDictPrefix[] {dustTiny, dustSmall, dust}, tPrefixListB = new OreDictPrefix[] {nugget, chunkGt, ingot};
		OreDictMaterial[] tMaterialList = new OreDictMaterial[] {MT.MeatRotten, MT.MeatRaw, MT.MeatCooked, MT.FishRotten, MT.FishRaw, MT.FishCooked, MT.SoylentGreen, MT.Tofu};
		
		for (int i = 0; i < tPrefixListA.length; i++) for (int j = 0; j < tMaterialList.length; j++) {
			RM.Compressor.addRecipe1(T, 16, 16, tPrefixListA[i].mat(tMaterialList[j], 1), tPrefixListB[i].mat(tMaterialList[j], 1));
			RM.ic2_compressor(tPrefixListA[i].mat(tMaterialList[j], 1), tPrefixListB[i].mat(tMaterialList[j], 1));
		}
		
		RM.Fermenter        .addRecipe1(T, 16,   16, dustTiny   .mat(MT.MeatRaw     , 1), dustTiny  .mat(MT.MeatRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   36, dustSmall  .mat(MT.MeatRaw     , 1), dustSmall .mat(MT.MeatRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,  144, dust       .mat(MT.MeatRaw     , 1), dust      .mat(MT.MeatRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   16, nugget     .mat(MT.MeatRaw     , 1), nugget    .mat(MT.MeatRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   36, chunkGt    .mat(MT.MeatRaw     , 1), chunkGt   .mat(MT.MeatRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,  144, ingot      .mat(MT.MeatRaw     , 1), ingot     .mat(MT.MeatRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   16, dustTiny   .mat(MT.MeatCooked  , 1), dustTiny  .mat(MT.MeatRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   36, dustSmall  .mat(MT.MeatCooked  , 1), dustSmall .mat(MT.MeatRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,  144, dust       .mat(MT.MeatCooked  , 1), dust      .mat(MT.MeatRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   16, nugget     .mat(MT.MeatCooked  , 1), nugget    .mat(MT.MeatRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   36, chunkGt    .mat(MT.MeatCooked  , 1), chunkGt   .mat(MT.MeatRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,  144, ingot      .mat(MT.MeatCooked  , 1), ingot     .mat(MT.MeatRotten, 1));
		
		RM.Fermenter        .addRecipe1(T, 16,   16, dustTiny   .mat(MT.FishRaw     , 1), dustTiny  .mat(MT.FishRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   36, dustSmall  .mat(MT.FishRaw     , 1), dustSmall .mat(MT.FishRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,  144, dust       .mat(MT.FishRaw     , 1), dust      .mat(MT.FishRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   16, nugget     .mat(MT.FishRaw     , 1), nugget    .mat(MT.FishRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   36, chunkGt    .mat(MT.FishRaw     , 1), chunkGt   .mat(MT.FishRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,  144, ingot      .mat(MT.FishRaw     , 1), ingot     .mat(MT.FishRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   16, dustTiny   .mat(MT.FishCooked  , 1), dustTiny  .mat(MT.FishRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   36, dustSmall  .mat(MT.FishCooked  , 1), dustSmall .mat(MT.FishRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,  144, dust       .mat(MT.FishCooked  , 1), dust      .mat(MT.FishRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   16, nugget     .mat(MT.FishCooked  , 1), nugget    .mat(MT.FishRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,   36, chunkGt    .mat(MT.FishCooked  , 1), chunkGt   .mat(MT.FishRotten, 1));
		RM.Fermenter        .addRecipe1(T, 16,  144, ingot      .mat(MT.FishCooked  , 1), ingot     .mat(MT.FishRotten, 1));
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		
		addListener(dust.dat(MT.Wheat)          , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.rem_smelting(aEvent.mStack);}});
		addListener(dustSmall.dat(MT.Wheat)     , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.rem_smelting(aEvent.mStack);}});
		addListener(dustTiny.dat(MT.Wheat)      , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.rem_smelting(aEvent.mStack);}});
		
		addListener(ingot.dat(MT.MeatRaw)       , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.ingot(MT.MeatCooked    ), F, T, F);}});
		addListener(chunkGt.dat(MT.MeatRaw)     , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.ingot(MT.MeatCooked, U4), F, T, F);}});
		addListener(nugget.dat(MT.MeatRaw)      , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.ingot(MT.MeatCooked, U9), F, T, F);}});
		addListener(dust.dat(MT.MeatRaw)        , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.dust (MT.MeatCooked    ), F, T, F);}});
		addListener(dustSmall.dat(MT.MeatRaw)   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.dust (MT.MeatCooked, U4), F, T, F);}});
		addListener(dustTiny.dat(MT.MeatRaw)    , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.dust (MT.MeatCooked, U9), F, T, F);}});
		
		addListener(ingot.dat(MT.FishRaw)       , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.ingot(MT.FishCooked    ), F, T, F);}});
		addListener(chunkGt.dat(MT.FishRaw)     , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.ingot(MT.FishCooked, U4), F, T, F);}});
		addListener(nugget.dat(MT.FishRaw)      , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.ingot(MT.FishCooked, U9), F, T, F);}});
		addListener(dust.dat(MT.FishRaw)        , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.dust (MT.FishCooked    ), F, T, F);}});
		addListener(dustSmall.dat(MT.FishRaw)   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.dust (MT.FishCooked, U4), F, T, F);}});
		addListener(dustTiny.dat(MT.FishRaw)    , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.dust (MT.FishCooked, U9), F, T, F);}});
		
		addListener("foodVanilla", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OM.prefixcontains(aEvent.mStack, TD.Prefix.DUST_BASED)) {
			RM.Shredder         .addRecipe1(T, 16,   16, aEvent.mStack, OM.dust(MT.Vanilla));
			RM.Mortar           .addRecipe1(T, 16,   16, aEvent.mStack, OM.dust(MT.Vanilla, U2));
			}
		}});
		addListener("foodCheese", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Slicer           .addRecipe2(T, 16,   16, aEvent.mStack, IL.Shape_Slicer_Flat.get(0), IL.Food_Cheese_Sliced.get(4));
			if (!OM.prefixcontains(aEvent.mStack, TD.Prefix.DUST_BASED)) {
			RM.Shredder         .addRecipe1(T, 16,   16, aEvent.mStack, OM.dust(MT.Cheese));
			RM.Mortar           .addRecipe1(T, 16,   16, aEvent.mStack, OM.dust(MT.Cheese, U2));
			}
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 8, 0, 8);
		}});
		addListener("foodDough", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.rem_smelting(aEvent.mStack);
			RM.RollingMill      .addRecipe1(T, 16,   16, aEvent.mStack, IL.Food_Dough_Flat.get(1));
			RM.Mixer            .addRecipe2(T, 16,   16, aEvent.mStack, gemChipped.mat(MT.Sugar, 4      ), IL.Food_Dough_Sugar.get(2));
			RM.Mixer            .addRecipe2(T, 16,   16, aEvent.mStack, OM.dust(MT.Sugar                ), IL.Food_Dough_Sugar.get(2));
			RM.Mixer            .addRecipe2(T, 16,   16, aEvent.mStack, OM.dust(MT.Cocoa                ), IL.Food_Dough_Chocolate.get(1));
			RM.Mixer            .addRecipe2(T, 16,   16, aEvent.mStack, OM.dust(MT.Chocolate            ), IL.Food_Dough_Chocolate.get(2));
			
			
			RM.Press            .addRecipe2(T, 16,   16, ST.amount(1, aEvent.mStack), IL.Shape_Foodmold_Bun.get(0)      , IL.Food_Bun_Raw       .get(1));
			RM.Press            .addRecipe2(T, 16,   32, ST.amount(2, aEvent.mStack), IL.Shape_Foodmold_Bread.get(0)    , IL.Food_Bread_Raw     .get(1));
			RM.Press            .addRecipe2(T, 16,   48, ST.amount(3, aEvent.mStack), IL.Shape_Foodmold_Baguette.get(0) , IL.Food_Baguette_Raw  .get(1));
		}});
		addListener("foodSugarDough", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Press            .addRecipe2(T, 16,   64, ST.amount(4, aEvent.mStack), IL.Shape_Foodmold_Cylinder.get(0), IL.Food_CakeBottom_Raw.get(1));
		}});
		addListener("foodRaisins", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Bath             .addRecipe1(T,  0,   16, aEvent.mStack, MT.Chocolate.liquid(U4, T), NF, IL.Food_Raisins_Chocolate.get(1));
			RM.Mixer            .addRecipe2(T, 16,   16, IL.Food_Ice_Cream.get(1), aEvent.mStack, NF, NF, IL.Food_Ice_Cream_Raisin.get(1));
		}});
		addListener("foodCaramel", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer            .addRecipe2(T, 16,   16, IL.Food_Ice_Cream.get(1), aEvent.mStack, NF, NF, IL.Food_Ice_Cream_Caramel.get(1));
		}});
		addListener("foodBaconcooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack)) {
			RM.Mixer            .addRecipe2(T, 16,   16, IL.Food_Ice_Cream.get(1), aEvent.mStack, NF, NF, IL.Food_Ice_Cream_Bacon.get(1));
			}
		}});
		addListener("foodChum", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack)) {
			RM.food_can(aEvent.mStack, 4, "SPAM", IL.CANS_CHUM);
			RM.Mixer            .addRecipe2(T, 16,   16, IL.Food_Ice_Cream.get(4), aEvent.mStack, NF, NF, IL.Food_Ice_Cream_Chum.get(4));
			for (OreDictMaterial tMat : ANY.Wood.mToThis) {ItemStack tStick = OP.stick.mat(tMat, 1); if (ST.valid(tStick)) {
			RM.Boxinator        .addRecipe2(T, 16,   16, aEvent.mStack, tStick, IL.Food_Chum_On_Stick.get(1));
			}}
			}
		}});
		
		
		addListener(new OreDictListenerEvent_TwoNames("foodSugarDough", "foodRaisins") {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
			if (!IL.Food_Dough_Sugar_Raisins.equal(aStack1, F, T) && !IL.Food_Dough_Sugar_Chocolate_Raisins.equal(aStack1, F, T))
			RM.Mixer            .addRecipe2(T, 16,   16, aStack1, aStack2, IL.Food_Dough_Sugar_Raisins.get(1));
		}});
		addListener(new OreDictListenerEvent_TwoNames("foodSugarDough", "foodChocolateraisins") {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
			if (!IL.Food_Dough_Sugar_Raisins.equal(aStack1, F, T) && !IL.Food_Dough_Sugar_Chocolate_Raisins.equal(aStack1, F, T))
			RM.Mixer            .addRecipe2(T, 16,   16, aStack1, aStack2, IL.Food_Dough_Sugar_Chocolate_Raisins.get(1));
		}});
		
		
		addListener("materialHoneycomb", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Centrifuge       .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Honey.make(90), OM.dust(MT.WaxBee));
			RM.Squeezer         .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Honey.make(90), OM.dust(MT.WaxBee));
			RM.Juicer           .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Honey.make(90), OM.dust(MT.WaxBee));
			RM.Mortar           .addRecipe1(T, 16,   64, aEvent.mStack, OM.dust(MT.WaxBee));
		}});
		addListener("materialWaxcomb", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Centrifuge       .addRecipe1(T, 16,   64, aEvent.mStack, OM.dust(MT.WaxBee));
			RM.Squeezer         .addRecipe1(T, 16,   64, aEvent.mStack, OM.dust(MT.WaxBee));
			RM.Juicer           .addRecipe1(T, 16,   64, aEvent.mStack, OM.dust(MT.WaxBee));
			RM.Mortar           .addRecipe1(T, 16,   64, aEvent.mStack, OM.dust(MT.WaxBee));
		}});
		
		
		addListener("listAllegg", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack)) {
			for (OreDictMaterial tMat : ANY.Flour.mToThis)
			RM.Mixer.addRecipe2(T, 16,   16, aEvent.mStack, OM.dust(tMat), IL.Food_Dough_Egg.get(1));
			
			for (String tCookingOil : FluidsGT.COOKING_OIL) if (FL.exists(tCookingOil)) for (String tFluid : FluidsGT.VINEGAR) if (FL.exists(tFluid)) {
			RM.Mixer.addRecipe1(T, 16,   16, aEvent.mStack, FL.array(FL.make(tCookingOil, 100), FL.make(tFluid, 100)), FL.Mayo.make(250), ZL_IS);
			RM.Mixer.addRecipe1(T, 16,   16, aEvent.mStack, FL.array(FL.make(tCookingOil, 100), FL.Juice_Lemon.make(100)), FL.Mayo.make(250), ZL_IS);
			RM.Mixer.addRecipe1(T, 16,   16, aEvent.mStack, FL.array(FL.make(tCookingOil, 100), FL.Juice_Lime.make(100)), FL.Mayo.make(250), ZL_IS);
			}
			
			}
		}});
		
		
		addListener("listAllmeatraw", "foodScrapmeat", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OD.listAllmeatsubstitute.is_(aEvent.mStack) || OM.is_("listAllfishraw", aEvent.mStack) || ST.container(aEvent.mStack, T) != null) return;
			OreDictItemData tData = OM.anydata_(aEvent.mStack);
			if (tData == null) {
				RM.Fermenter.addRecipe1(T, 16, 288, aEvent.mStack, ST.make(Items.rotten_flesh, 1, 0));
				RM.Mortar.addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.MeatRaw));
			} else if (tData.mPrefix == null) {
				RM.Fermenter.addRecipe1(T, 16, 288, aEvent.mStack, ST.make(Items.rotten_flesh, 1, 0));
				OreDictMaterial tMeat = MT.MeatRaw;
				long tAmount = 0;
				for (OreDictMaterialStack tMaterialStack : tData.getAllMaterialStacks()) {
					if (tMaterialStack.mMaterial.contains(TD.Properties.MEAT)) tAmount += tMaterialStack.mAmount;
					if (tMaterialStack.mMaterial.contains(TD.Properties.ROTTEN)) tMeat = MT.MeatRotten;
				}
				if (tAmount > 0) RM.Mortar.addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(tMeat, tAmount));
			}
		}});
		
		addListener("listAllfishraw", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OD.listAllmeatsubstitute.is_(aEvent.mStack) || ST.container(aEvent.mStack, T) != null) return;
			OreDictItemData tData = OM.anydata_(aEvent.mStack);
			
			RM.generify(aEvent.mStack, ST.make(Items.fish, 1, 0));
			
			if (tData == null) {
				RM.Mortar.addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.FishRaw));
			} else if (tData.mPrefix == null) {
				OreDictMaterial tMeat = MT.FishRaw;
				long tAmount = 0;
				for (OreDictMaterialStack tMaterialStack : tData.getAllMaterialStacks()) {
					if (tMaterialStack.mMaterial.contains(TD.Properties.MEAT)) tAmount += tMaterialStack.mAmount;
					if (tMaterialStack.mMaterial.contains(TD.Properties.ROTTEN)) tMeat = MT.FishRotten;
				}
				if (tAmount > 0) RM.Mortar.addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(tMeat, tAmount));
			}
		}});
		
		
		addListener("foodScrapmeat", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 4, 0,12);
			
			RM.Mixer.addRecipeX(T, 16,  256, ST.array(aEvent.mStack, ST.make(Items.fermented_spider_eye, 1, W), OM.dust(MT.MeatRotten), OM.dust(MT.Bone), IL.Food_Potato_Poisonous.get(1), IL.IC2_Scrap.get(1, ST.make(Blocks.red_mushroom, 1, W))), FL.array(FL.Purple_Drink.make(1000), FL.Oil_Fish.make(1000)), FL.Sludge.make(1000), IL.Food_Chum.get(8));
			RM.Mixer.addRecipeX(T, 16,  256, ST.array(aEvent.mStack, ST.make(Items.fermented_spider_eye, 1, W), OM.dust(MT.FishRotten), OM.dust(MT.Bone), IL.Food_Potato_Poisonous.get(1), IL.IC2_Scrap.get(1, ST.make(Blocks.red_mushroom, 1, W))), FL.array(FL.Purple_Drink.make(1000), FL.Oil_Fish.make(1000)), FL.Sludge.make(1000), IL.Food_Chum.get(8));
			
			if (IL.FZ_Sludge.exists()) {
			RM.Mixer.addRecipeX(T, 16,  256, ST.array(aEvent.mStack, ST.make(Items.fermented_spider_eye, 1, W), OM.dust(MT.MeatRotten), OM.dust(MT.Bone), IL.FZ_Sludge            .get(1), IL.IC2_Scrap.get(1, ST.make(Blocks.red_mushroom, 1, W))), FL.array(FL.Purple_Drink.make(1000), FL.Oil_Fish.make(1000)), FL.Sludge.make(1000), IL.Food_Chum.get(8));
			RM.Mixer.addRecipeX(T, 16,  256, ST.array(aEvent.mStack, ST.make(Items.fermented_spider_eye, 1, W), OM.dust(MT.FishRotten), OM.dust(MT.Bone), IL.FZ_Sludge            .get(1), IL.IC2_Scrap.get(1, ST.make(Blocks.red_mushroom, 1, W))), FL.array(FL.Purple_Drink.make(1000), FL.Oil_Fish.make(1000)), FL.Sludge.make(1000), IL.Food_Chum.get(8));
			}
			if (IL.IE_Slag.exists()) {
			RM.Mixer.addRecipeX(T, 16,  256, ST.array(aEvent.mStack, ST.make(Items.fermented_spider_eye, 1, W), OM.dust(MT.MeatRotten), OM.dust(MT.Bone), IL.IE_Slag              .get(1), IL.IC2_Scrap.get(1, ST.make(Blocks.red_mushroom, 1, W))), FL.array(FL.Purple_Drink.make(1000), FL.Oil_Fish.make(1000)), FL.Sludge.make(1000), IL.Food_Chum.get(8));
			RM.Mixer.addRecipeX(T, 16,  256, ST.array(aEvent.mStack, ST.make(Items.fermented_spider_eye, 1, W), OM.dust(MT.FishRotten), OM.dust(MT.Bone), IL.IE_Slag              .get(1), IL.IC2_Scrap.get(1, ST.make(Blocks.red_mushroom, 1, W))), FL.array(FL.Purple_Drink.make(1000), FL.Oil_Fish.make(1000)), FL.Sludge.make(1000), IL.Food_Chum.get(8));
			}
			if (IL.TE_Slag.exists()) {
			RM.Mixer.addRecipeX(T, 16,  256, ST.array(aEvent.mStack, ST.make(Items.fermented_spider_eye, 1, W), OM.dust(MT.MeatRotten), OM.dust(MT.Bone), IL.TE_Slag              .get(1), IL.IC2_Scrap.get(1, ST.make(Blocks.red_mushroom, 1, W))), FL.array(FL.Purple_Drink.make(1000), FL.Oil_Fish.make(1000)), FL.Sludge.make(1000), IL.Food_Chum.get(8));
			RM.Mixer.addRecipeX(T, 16,  256, ST.array(aEvent.mStack, ST.make(Items.fermented_spider_eye, 1, W), OM.dust(MT.FishRotten), OM.dust(MT.Bone), IL.TE_Slag              .get(1), IL.IC2_Scrap.get(1, ST.make(Blocks.red_mushroom, 1, W))), FL.array(FL.Purple_Drink.make(1000), FL.Oil_Fish.make(1000)), FL.Sludge.make(1000), IL.Food_Chum.get(8));
			}
			if (IL.TE_Slag_Rich.exists()) {
			RM.Mixer.addRecipeX(T, 16,  256, ST.array(aEvent.mStack, ST.make(Items.fermented_spider_eye, 1, W), OM.dust(MT.MeatRotten), OM.dust(MT.Bone), IL.TE_Slag_Rich         .get(1), IL.IC2_Scrap.get(1, ST.make(Blocks.red_mushroom, 1, W))), FL.array(FL.Purple_Drink.make(1000), FL.Oil_Fish.make(1000)), FL.Sludge.make(1000), IL.Food_Chum.get(8));
			RM.Mixer.addRecipeX(T, 16,  256, ST.array(aEvent.mStack, ST.make(Items.fermented_spider_eye, 1, W), OM.dust(MT.FishRotten), OM.dust(MT.Bone), IL.TE_Slag_Rich         .get(1), IL.IC2_Scrap.get(1, ST.make(Blocks.red_mushroom, 1, W))), FL.array(FL.Purple_Drink.make(1000), FL.Oil_Fish.make(1000)), FL.Sludge.make(1000), IL.Food_Chum.get(8));
			}
		}});
		addListener("listAllfishraw", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OD.listAllmeatsubstitute.is_(aEvent.mStack)) return;
			OreDictItemData tData = OM.anydata_(aEvent.mStack);
			if (OM.materialcontained(tData, MT.Tofu, MT.SoylentGreen)) return;
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Fish", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_FISH);
			if (ST.container(aEvent.mStack, T) != null || ST.meta_(aEvent.mStack) == W) return;
			long tFishOilAmount = U;
			OreDictMaterialStack tByProduct = null;
			if (tData == null) tByProduct = OM.stack(MT.FishRaw, U); else for (OreDictMaterialStack tMat : tData.getAllMaterialStacks()) {
				if (tMat.mMaterial == MT.FishOil) tFishOilAmount = tMat.mAmount; else
				if (tMat.mMaterial != MT.Bone || tByProduct == null) tByProduct = tMat.copy(tMat.mMaterial == MT.FishRaw ? tMat.mAmount / 2 : tMat.mAmount);
			}
			RM.Squeezer .addRecipe1(T, 16,   32, aEvent.mStack, NF, MT.FishOil.liquid(tFishOilAmount  , F), OM.dust(tByProduct));
			RM.Juicer   .addRecipe1(T, 16,   32, aEvent.mStack, NF, MT.FishOil.liquid(tFishOilAmount/2, F), OM.dust(tByProduct));
		}});
		addListener("listAllfishcooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Fish", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_FISH);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,12);
		}});
		addListener("listAllribraw", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Ribs", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,24);
		}});
		addListener("listAllribcooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Ribs", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,24);
			RM.Bath     .addRecipe1(T,  0,   16, aEvent.mStack, FL.Sauce_BBQ.make(250), NF, IL.Food_Rib_BBQ.get(1));
		}});
		addListener("listAllbeefraw", "listAllbeefcooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Beef", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,16);
		}});
		addListener("listAllchickenraw", "listAllchickencooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Chicken", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,12);
		}});
		addListener("listAllmuttonraw", "listAllmuttoncooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Mutton", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,16);
		}});
		addListener("listAllporkraw", "listAllporkcooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Pork", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,16);
		}});
		addListener("listAllrabbitraw", "listAllrabbitcooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Rabbit", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,12);
		}});
		addListener("listAllturkeyraw", "listAllturkeycooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Turkey", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,20);
		}});
		addListener("listAllcrabyraw", "listAllcrabcooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Crab", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,12);
		}});
		addListener("listAllratraw", "listAllratcooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Rat", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,12);
		}});
		addListener("listAllturtleraw", "listAllturtlecooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Turtle", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,16);
		}});
		addListener("listAllostrichraw", "listAllostrichcooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Ostrich", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,16);
		}});
		addListener("listAllvenisonraw", "listAllvenisoncooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Venison", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,16);
		}});
		addListener("listAlltitanraw", "listAlltitancooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Titan Meat", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 8, 0, 0, 0,16);
		}});
		addListener("listAllhamraw", "listAllhamcooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Ham", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,24);
		}});
		addListener("listAllhorseraw", "listAllhorsecooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Horse", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,12);
		}});
		addListener("listAlldograw", "listAlldogcooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Dog", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0,12);
		}});
		addListener("foodBaconraw", "foodBaconcooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Bacon", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 0, 6);
		}});
		addListener("listAllhydraraw", "listAllhydracooked", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!OD.listAllmeatsubstitute.is_(aEvent.mStack))
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Hydra", ST.rotten(aEvent.mStack)?IL.CANS_ROTTEN:IL.CANS_MEAT);
			if (!(aEvent.mStack.getItem() instanceof MultiItemRandom)) FoodsGT.put(aEvent.mStack, 0, 0,20, 0,40);
		}});
		addListener("listAllmushroom", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (!ST.equal(aEvent.mStack, Blocks.brown_mushroom))
			RM.Mixer        .addRecipe2(T, 16,   16, aEvent.mStack, ST.make(Blocks.brown_mushroom, 1, W), NF, FL.Soup_Mushroom.make(1000), ZL_IS);
		}});
		addListener("listAllpropolis", OD.itemResin, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			for (OreDictMaterial tMat : ANY.Ash.mToThis) {
			RM.Mixer        .addRecipeX(T, 16,   16, ST.array(ingot.mat(MT.Peat, 2), OM.dust(tMat, U*2), aEvent.mStack), ingotDouble.mat(MT.PeatBituminous, 1));
			RM.Mixer        .addRecipeX(T, 16,   16, ST.array(dust .mat(MT.Peat, 1), OM.dust(tMat, U  ), aEvent.mStack), dust       .mat(MT.PeatBituminous, 1));
			}
		}});
		addListener(OD.itemTar, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer        .addRecipe2(T, 16,   16, ingot.mat(MT.Peat, 1), aEvent.mStack, ingotDouble.mat(MT.PeatBituminous, 1));
			RM.Mixer        .addRecipe2(T, 16,   16, dust .mat(MT.Peat, 1), aEvent.mStack, dust       .mat(MT.PeatBituminous, 2));
		}});
		
		
		addListener("dropHoney", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (FL.getFluid(aEvent.mStack, T) != null || OM.is_("bucketHoney", aEvent.mStack) || OM.is_("bottleHoney", aEvent.mStack)) return;
			RM.Squeezer     .addRecipe1(T, 16,   16,   500, aEvent.mStack, NF, FL.Honey.make(100), IL.FR_Propolis.get(1));
			RM.Juicer       .addRecipe1(T, 16,   16,   500, aEvent.mStack, NF, FL.Honey.make(100), IL.FR_Propolis.get(1));
		}});
		addListener("dropHoneydew", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Squeezer     .addRecipe1(T, 16,   16, aEvent.mStack, NF, FL.Honeydew.make(100), ZL_IS);
			RM.Juicer       .addRecipe1(T, 16,   16, aEvent.mStack, NF, FL.Honeydew.make(100), ZL_IS);
		}});
		addListener("dropRoyalJelly", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer        .addRecipe1(T, 16,   16, aEvent.mStack, FL.Honeydew.make(200), FL.Ambrosia.make(400), ZL_IS);
			RM.Squeezer     .addRecipe1(T, 16,   16, aEvent.mStack, NF, FL.RoyalJelly.make(100), ZL_IS);
			RM.Juicer       .addRecipe1(T, 16,   16, aEvent.mStack, NF, FL.RoyalJelly.make(100), ZL_IS);
		}});
		}};
		
		RM.Mixer        .addRecipe0(T, 16,   16, FL.array(FL.RoyalJelly.make(100), FL.Honeydew.make(200)), FL.array(FL.Ambrosia.make(400)), ZL_IS);
		
		//----------------------------------------------------------------------------
		
		RM.Distillery   .addRecipe1(T        , 16,  16, ST.tag(0), FL.RoyalJelly  .make(  10), FL.DistW      .make( 1), OM.dust(MT.Sugar));
		RM.Distillery   .addRecipe1(T        , 16,  16, ST.tag(0), FL.Honey       .make( 100), FL.DistW      .make(10), OM.dust(MT.Sugar, U2));
		RM.Distillery   .addRecipe1(T        , 16,  16, ST.tag(0), FL.HoneyGrC    .make( 100), FL.DistW      .make(10), OM.dust(MT.Sugar, U2));
		RM.Distillery   .addRecipe1(T        , 16,  16, ST.tag(0), FL.HoneyBoP    .make( 100), FL.DistW      .make(10), OM.dust(MT.Sugar, U2));
		RM.Distillery   .addRecipe1(T        , 16,  16, ST.tag(0), FL.Honeydew    .make( 100), FL.DistW      .make(10), OM.dust(MT.Sugar));
		RM.Distillery   .addRecipe1(T        , 16,  80, ST.tag(0), FL.Juice_Reed  .make( 100), FL.DistW      .make(50), OM.dust(MT.Sugar));
		RM.Distillery   .addRecipe1(T        , 16,  80, ST.tag(0), FL.Juice_Beet  .make( 100), FL.DistW      .make(50), OM.dust(MT.Sugar));
		RM.Distillery   .addRecipe1(T        , 16,  80, ST.tag(0), FL.Juice_Cactus.make( 100), FL.DistW      .make(50), ZL_IS);
		RM.Distillery   .addRecipe1(T        , 16,  80, ST.tag(0), FL.Sap_Maple   .make( 125), FL.Syrup_Maple.make(50), FL.DistW.make(50));
		RM.Distillery   .addRecipe1(T        , 16,  16, ST.tag(0), FL.Juice_Lemon .make(   4), FL.Alcopops   .make( 2), ZL_IS);
		RM.Distillery   .addRecipe1(T        , 16,  16, ST.tag(0), FL.Juice_Lime  .make(   4), FL.Alcopops   .make( 2), ZL_IS);
		RM.Distillery   .addRecipe1(T        , 16,  16, ST.tag(0), FL.Juice_Potato.make(   4), FL.Vodka      .make( 2), ZL_IS);
		RM.Distillery   .addRecipe1(T        , 16,  16, ST.tag(0), FL.Rum_White   .make(   2), FL.Rum_Dark   .make( 1), ZL_IS);
		RM.Distillery   .addRecipe1(T        , 16,  16, ST.tag(0), FL.ShortMead   .make(   2), FL.Mead       .make( 1), ZL_IS);
		RM.Distillery   .addRecipe1(F        , 16,  16, ST.tag(0), FL.Vodka       .make(   2), FL.Vodka      .make( 1), ZL_IS);
		RM.Distillery   .addRecipe1(F        , 16,  16, ST.tag(0), FL.Rum_Dark    .make(   2), FL.Rum_Dark   .make( 1), ZL_IS);
		RM.Distillery   .addRecipe1(T,T,F,F,F, 16,  80, ST.tag(0), FL.Sap         .make( 125), FL.DistW      .make(50), OM.dust(MT.Sugar));
		RM.Distillery   .addRecipe1(T        , 16, 128, OM.dust(MT.Chili)    , FL.Sauce_Hot    .make(750), FL.Sauce_Diabolo .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T        , 64, 128, OM.dust(MT.Chili)    , FL.Sauce_Diabolo.make(750), FL.Sauce_Diablo  .make(750), ZL_IS);
		RM.Distillery   .addRecipe1(T        , 64, 128, OM.dust(MT.Gunpowder), FL.Sauce_Diablo .make(250), FL.Sauce_Snitches.make(250), ZL_IS);
		RM.Distillery   .addRecipe1(T        , 64, 128, OM.dust(MT.Gunpowder), FL.Lemonade     .make(250), FL.Grenade_Juice .make(250), ZL_IS);
		
		for (FluidStack tWater : FL.array(FL.Water.make(1000), FL.SpDew.make(1000), FL.DistW.make(1000))) {
		RM.Distillery   .addRecipe1(T, 16,   48, OM.dust(MT.Coffee)    , FL.mul(tWater, 3, 4, T), FL.make("potion.darkcoffee", 750), ZL_IS);
		RM.Distillery   .addRecipe1(T, 16,   16, OM.dust(MT.Coffee, U3), FL.mul(tWater, 1, 4, T), FL.make("potion.darkcoffee", 250), ZL_IS);
		
		for (OreDictMaterial tMat : ANY.Flour.mToThis)
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(tMat           ), FL.mul(tWater, 1, 4, T), NF, IL.Food_Dough.get(2));
		
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.Milk        ), FL.mul(tWater, 1, 2, T), FL.Milk.make(1000), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.Honey       ), FL.mul(tWater, 1, 2, T), FL.Honey.make(1000), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.Honeydew    ), FL.mul(tWater, 1, 2, T), FL.Honeydew.make(1000), ZL_IS);
		
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.Tea     , U9), FL.mul(tWater,   750, 1000, T), FL.Tea.make( 750), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,   36, OM.dust(MT.Tea     , U4), FL.mul(tWater,  1687, 1000, T), FL.Tea.make(1687), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,  144, OM.dust(MT.Tea         ), FL.mul(tWater,  6750, 1000, T), FL.Tea.make(6750), ZL_IS);
		
		RM.Injector     .addRecipe1(T, 16,   16, OM.dust(MT.Mg          ), tWater, FL.Mineralwater.make(1000), ZL_IS);
		RM.Injector     .addRecipe1(T, 16,   16, OM.dust(MT.Ca          ), tWater, FL.Mineralwater.make(1000), ZL_IS);
		
		RM.Injector     .addRecipe0(T, 16,   16, FL.array(FL.mul(tWater, 1, 4, T), MT.CO2.gas(U4, T)), FL.Soda.make(250), ZL_IS);
		}
		RM.Injector     .addRecipe0(T, 16,   16, FL.array(FL.Mineralwater.make(250), MT.CO2.gas(U4, T)), FL.Mineralsoda.make(250), ZL_IS);
		RM.Injector     .addRecipe0(T, 16,   16, FL.array(FL.Slime_Green .make(250), MT.CO2.gas(U4, T)), FL.BAWLS.make(250), ZL_IS);
		
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Milk.make (                                    50), FL.Milk_Spoiled                          .make(50), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.MilkGrC.make (                                 50), FL.Milk_Spoiled                          .make(50), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Honeydew.make (                                50), FL.ShortMead                             .make(50), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Pear                               .make(50), FL.Cider_Pear                            .make(25, FL.Cider_Apple), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Peach                              .make(50), FL.Cider_Peach                           .make(25, FL.Cider_Apple), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Ananas                             .make(50), FL.Cider_Ananas                          .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Apple                              .make(50), FL.Cider_Apple                           .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_AppleGrC                           .make(50), FL.Cider_Apple                           .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Cider_Apple                              .make(50), FL.Vinegar_Apple                         .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.make_("potion.goldenapplejuice"       , 50), FL.make("potion.goldencider"          , 25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.make_("potion.idunsapplejuice"        , 50), FL.make("potion.notchesbrew"          , 25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Mash_Rice                                .make(50), FL.Sake                                  .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Mash_Wheat                               .make(50), FL.Whiskey_Scotch                        .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Whiskey_Scotch                           .make(50), FL.Whiskey_GlenMcKenner                  .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Mash_WheatHops                           .make(50), FL.Beer                                  .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Mash_Hops                                .make(50), FL.Beer_Dark                             .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Beer_Dark                                .make(50), FL.Beer_Dragonblood                      .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Reed                               .make(50), FL.Rum_White                             .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Rum_White                                .make(50), FL.Vinegar_Cane                          .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Rum_Dark                                 .make(50), FL.Vinegar_Cane                          .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Sake                                     .make(50), FL.Vinegar_Rice                          .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Wine_Grape_Red                           .make(50), FL.Vinegar_Grape                         .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Wine_Grape_White                         .make(50), FL.Vinegar_Grape                         .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Wine_Grape_Green                         .make(50), FL.Vinegar_Grape                         .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.Wine_Grape_Purple                        .make(50), FL.Vinegar_Grape                         .make(10), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Grape_Red                          .make(50), FL.Wine_Grape_Red                        .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Grape_White                        .make(50), FL.Wine_Grape_White                      .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Grape_Green                        .make(50), FL.Wine_Grape_Green                      .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Grape_Purple                       .make(50), FL.Wine_Grape_Purple                     .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Apricot                            .make(50), FL.Wine_Apricot                          .make(25, FL.Wine_Grape_Green), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Banana                             .make(50), FL.Wine_Banana                           .make(25, FL.Wine_Grape_Green), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Carrot                             .make(50), FL.Wine_Carrot                           .make(25, FL.Wine_Grape_Green), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Cherry                             .make(50), FL.Wine_Cherry                           .make(25, FL.Wine_Grape_Green), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Lemon                              .make(50), FL.Wine_Lemon                            .make(25), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Lime                               .make(50), FL.Wine_Citrus                           .make(25, FL.Wine_Lemon), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Orange                             .make(50), FL.Wine_Citrus                           .make(25, FL.Wine_Lemon), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Kiwi                               .make(50), FL.Wine_Citrus                           .make(25, FL.Wine_Lemon), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Cranberry                          .make(50), FL.Wine_Cranberry                        .make(25, FL.Wine_Grape_Green), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Elderberry                         .make(50), FL.Wine_Elderberry                       .make(25, FL.Wine_Grape_Green), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Plum                               .make(50), FL.Wine_Plum                             .make(25, FL.Wine_Grape_Green), ZL_IS);
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.Juice_Tomato                             .make(50), FL.Wine_Tomato                           .make(25, FL.Wine_Grape_Green), ZL_IS);
		if (FL.Wine_Fortified.exists()) for (String tWine : FluidsGT.WINE) if (FL.exists(tWine) && !FL.Wine_Fortified.is(tWine))
		RM.Fermenter    .addRecipe1(T, 16,  128, ST.tag(0), FL.make(tWine                            , 50), FL.Wine_Fortified                        .make(10), ZL_IS);
		if (FL.Wine_Fruit.exists()) for (String tJuice : FluidsGT.FRUIT_JUICE) if (FL.exists(tJuice))
		RM.Fermenter    .addRecipe1(T, 16,   64, ST.tag(0), FL.make(tJuice                           , 50), FL.Wine_Fruit                            .make(25), ZL_IS);
		
		CR.remove(ST.make(Items.water_bucket, 1, 0));
		CR.remove(ST.make(Items.milk_bucket, 1, 0));
		
		if (FL.Sap.exists())
		RM.Drying           .addRecipe0(T, 16,  200, FL.Sap.make(250), FL.DistW.make(100), OM.dust(MT.Sugar));
		RM.Drying           .addRecipe0(T, 16,  200, FL.Sap_Maple.make(250), FL.DistW.make(100), OM.dust(MT.Sugar));
		RM.Drying           .addRecipe0(T, 16,  100, FL.Juice_Reed.make(200), FL.DistW.make(50), OM.dust(MT.Sugar));
		RM.Drying           .addRecipe0(T, 16,  100, FL.Juice_Cactus.make(200), FL.DistW.make(50), ZL_IS);
		
		RM.Centrifuge       .addRecipe0(T, 16,   16, FL.Milk   .make(50), FL.Cream.make(50), ZL_IS);
		RM.Centrifuge       .addRecipe0(T, 16,   16, FL.MilkGrC.make(50), FL.Cream.make(50), ZL_IS);
		RM.Centrifuge       .addRecipe0(T, 16,   16, FL.MilkSoy.make(50), FL.Cream.make(50), ZL_IS);
		RM.Centrifuge       .addRecipe0(T, 16,   16, FL.Juice_Coconut.make(50), FL.Cream_Coconut.make(50), ZL_IS);
		RM.Centrifuge       .addRecipe0(T, 16,   64, FL.Cream.make(250), NF, IL.Food_Butter.get(1));
		
		RM.Mixer            .addRecipe1(T, 16,   16, OP.stick.mat(MT.WoodTreated, 0), FL.MilkSoy.make(50), FL.Cream.make(50), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   16, OP.stick.mat(MT.WoodTreated, 0), FL.Milk.make(50), FL.Cream.make(50), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   16, OP.stick.mat(MT.WoodTreated, 0), FL.MilkGrC.make(50), FL.Cream.make(50), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   16, OP.stick.mat(MT.WoodTreated, 0), FL.Juice_Coconut.make(50), FL.Cream_Coconut.make(50), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   64, OP.stick.mat(MT.WoodTreated, 0), FL.Cream.make(250), NF, IL.Food_Butter.get(1));
		
		ItemStack
		tOutput = OreDictManager.INSTANCE.getFirstOre("dropHoney", 1); if (ST.valid(tOutput)) {
		RM.Coagulator       .addRecipe0(T,  0,   16, FL.Honey   .make(200), NF, tOutput);
		RM.Coagulator       .addRecipe0(T,  0,   16, FL.HoneyGrC.make(200), NF, tOutput);
		RM.Coagulator       .addRecipe0(T,  0,   16, FL.HoneyBoP.make(200), NF, tOutput);
		}
		tOutput = OreDictManager.INSTANCE.getFirstOre("dropHoneydew", 1); if (ST.valid(tOutput))
		RM.Coagulator       .addRecipe0(T,  0,   16, FL.Honeydew.make(200), NF, tOutput);
		tOutput = IL.FR_Royal_Jelly.get(1, IL.HaC_Royal_Jelly.get(1)); if (ST.valid(tOutput))
		RM.Coagulator       .addRecipe0(T,  0,   16, FL.RoyalJelly.make(100), NF, tOutput);
		
		if (FL.Slime_Pink.exists()) {tOutput = OreDictManager.INSTANCE.getFirstOre("slimeballPink", 1);
		RM.Coagulator       .addRecipe0(T,  0,  256, FL.Slime_Pink .make(250), NF, ST.valid(tOutput) ? tOutput : OP.nugget.mat(MT.MeatRaw, 1));}
		RM.Coagulator       .addRecipe0(T,  0,  256, FL.Slime_Green.make(250), NF, ST.make(Items.slime_ball, 1, 0));
		
		RM.Coagulator       .addRecipe0(T,  0, 1024, FL.Milk   .make(1000), NF, IL.Food_Cheese.get(1));
		RM.Coagulator       .addRecipe0(T,  0, 1024, FL.MilkGrC.make(1000), NF, IL.Food_Cheese.get(1));
		RM.Coagulator       .addRecipe0(T,  0, 1024, FL.MilkSoy.make(250), NF, OM.dust(MT.Tofu));
		
		
		RM.Smelter          .addRecipe0(T, 16,    1, FL.Oil_Sunflower.make(1), FL.Oil_Frying.make(1), ZL_IS);
		RM.Smelter          .addRecipe0(T, 16,    1, FL.Oil_Olive    .make(1), FL.Oil_Frying.make(1), ZL_IS);
		RM.Smelter          .addRecipe0(T, 16,    1, FL.Oil_Nut      .make(1), FL.Oil_Frying.make(1), ZL_IS);
		RM.Smelter          .addRecipe0(T, 16,    1, FL.Oil_Hemp     .make(1), FL.Oil_Frying.make(1), ZL_IS);
		RM.Smelter          .addRecipe0(T, 16,    1, FL.Oil_Lin      .make(1), FL.Oil_Frying.make(1), ZL_IS);
		RM.Smelter          .addRecipe0(T, 16,    1, FL.Oil_Seed     .make(1), FL.Oil_Frying.make(1), ZL_IS);
		RM.Smelter          .addRecipe0(T, 16,    1, FL.Oil_Fish     .make(1), FL.Oil_Frying.make(1), ZL_IS);
		RM.Smelter          .addRecipe0(T, 16,    1, FL.Ice          .make(1), FL.Water     .make(1), ZL_IS);
		
		for (OreDictMaterial tMat : ANY.FlourGrains.mToThis) {
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(tMat), OM.dust(MT.MeatRaw), OM.ingot(MT.MeatRaw));
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(tMat), OM.dust(MT.FishRaw), OM.ingot(MT.FishRaw));
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(tMat), OM.dust(MT.MeatRotten), OM.ingot(MT.MeatRotten));
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(tMat), OM.dust(MT.FishRotten), OM.ingot(MT.FishRotten));
		}
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(MT.OatAbyssal), FL.Juice_Hellderberry.make(100), NF, IL.Food_Dough_Abyssal.get(1));
		
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Mash_Wheat.make(50), FL.Mash_Hops.make(50)), FL.Mash_WheatHops.make(100), ZL_IS);
		
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(MT.Sugar               ), OM.dust(MT.Cocoa    ), OM.dust(MT.Chocolate,  U*2));
		RM.Mixer            .addRecipe2(T, 16,   16, gemChipped.mat(MT.Sugar    ,  4), OM.dust(MT.Cocoa    ), OM.dust(MT.Chocolate,  U*2));
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(MT.Sugar           , U4), OM.dust(MT.Cocoa, U4), OM.dust(MT.Chocolate,   U2));
		RM.Mixer            .addRecipe2(T, 16,   16, gemChipped.mat(MT.Sugar    ,  1), OM.dust(MT.Cocoa, U4), OM.dust(MT.Chocolate,   U2));
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(MT.Sugar           , U9), OM.dust(MT.Cocoa, U9), OM.dust(MT.Chocolate, 2*U9));
		
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Lemonade.make(125), FL.Vodka.make(125)), FL.Leninade.make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Juice_Ananas.make(150), FL.Cream_Coconut.make(50), FL.Rum_White.make(50)), FL.Pina_Colada.make(250), ZL_IS);
		
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.make("potion.darkcoffee", 125), FL.Milk   .make(125)), FL.make("potion.coffee", 250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.make("potion.darkcoffee", 125), FL.MilkGrC.make(125)), FL.make("potion.coffee", 250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.make("potion.darkcoffee", 125), FL.MilkSoy.make(125)), FL.make("potion.coffee", 250), ZL_IS);
		
		for (ItemStack tStack : ST.array(dust.mat(MT.NaCl, 1), dustSmall.mat(MT.NaCl, 4), dustTiny.mat(MT.NaCl,  9))) {
		RM.Mixer            .addRecipe2(T, 16,   16, tStack, IL.Food_Butter.get(1), NF, NF, IL.Food_Butter_Salted.get(1));
		}
		for (ItemStack tStack : ST.array(dust.mat(MT.Milk, 1), dustSmall.mat(MT.Milk, 4), dustTiny.mat(MT.Milk,  9))) {
		RM.Mixer            .addRecipe1(T, 16,   48, tStack, FL.make("potion.darkcoffee"         , 750), FL.make("potion.coffee"              , 750), ZL_IS);
		}
		for (ItemStack tStack : ST.array(dust.mat(MT.Peanut, 1), dustSmall.mat(MT.Peanut, 4), dustTiny.mat(MT.Peanut,  9))) {
		RM.Mixer            .addRecipe1(T, 16,   64, tStack, FL.Cream                        .make(250), FL.Nutbutter_Peanut              .make(250), ZL_IS);
		}
		for (ItemStack tStack : ST.array(dust.mat(MT.Hazelnut, 1), dustSmall.mat(MT.Hazelnut, 4), dustTiny.mat(MT.Hazelnut,  9))) {
		RM.Mixer            .addRecipe1(T, 16,   64, tStack, FL.Cream_Chocolate              .make(250), FL.Cream_Nutella                 .make(250), ZL_IS);
		}
		for (ItemStack tStack : ST.array(dust.mat(MT.Chocolate, 1), dustSmall.mat(MT.Chocolate, 4), dustTiny.mat(MT.Chocolate,  9))) {
		RM.Mixer            .addRecipe1(T, 16,   48, tStack, FL.Milk                         .make(750), FL.make("potion.darkchocolatemilk"   , 750), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   48, tStack, FL.MilkGrC                      .make(750), FL.make("potion.darkchocolatemilk"   , 750), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   48, tStack, FL.MilkSoy                      .make(750), FL.make("potion.darkchocolatemilk"   , 750), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   64, tStack, FL.Cream                        .make(250), FL.Cream_Chocolate               .make(250), ZL_IS);
		}
		for (ItemStack tStack : ST.array(gemChipped.mat(MT.Sugar, 4), dust.mat(MT.Sugar, 1), dustSmall.mat(MT.Sugar, 4), dustTiny.mat(MT.Sugar, 9))) {
		RM.Mixer            .addRecipe1(T, 16,   48, tStack, FL.make("potion.coffee"             , 750), FL.make("potion.cafeaulait"          , 750), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   48, tStack, FL.make("potion.cafeaulait"         , 750), FL.make("potion.laitaucafe"          , 750), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   48, tStack, FL.make("potion.darkcoffee"         , 750), FL.make("potion.darkcafeaulait"      , 750), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   48, tStack, FL.make("potion.darkchocolatemilk"  , 750), FL.make("chocolatemilk"              , 750), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   48, tStack, FL.Juice_Lemon                  .make(750), FL.Lemonade                      .make(750), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   48, tStack, FL.Juice_Tomato                 .make(750), FL.Ketchup                       .make(750), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   48, tStack, FL.Tea                          .make(750), FL.Tea_Sweet                     .make(750), ZL_IS);
		}
		for (ItemStack tStack : ST.array(dust.mat(MT.Chili, 1), dustSmall.mat(MT.Chili, 4), dustTiny.mat(MT.Chili,  9))) {
		RM.Mixer            .addRecipe2(T, 16,   16, tStack, IL.Food_PotatoChips.get(1), IL.Food_ChiliChips.get(1));
		RM.Mixer            .addRecipe1(T, 16,   48, tStack, FL.Sauce_Chili                  .make(750), FL.make("potion.hotsauce"            , 750), ZL_IS);
		}
		for (String tCookingOil : FluidsGT.COOKING_OIL) if (FL.exists(tCookingOil)) for (String tVinegar : FluidsGT.VINEGAR) if (FL.exists(tVinegar)) {
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(MT.NaCl), FL.array(FL.make(tCookingOil, 100), FL.make(tVinegar, 100)), FL.Dressing.make(250), ZL_IS);
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.NaCl), OM.dust(MT.PepperBlack), FL.array(FL.Ketchup.make(250), FL.make(tVinegar, 125)), FL.Sauce_BBQ.make(500), ZL_IS);
		}
		
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Juice_Plum        .make(125), FL.Juice_Apple.make(125)), FL.Med_Laxative.make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Juice_Grape_White .make(125), FL.Juice_Apple.make(125)), FL.Med_Laxative.make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Juice_Grape_Green .make(125), FL.Juice_Apple.make(125)), FL.Med_Laxative.make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Juice_Grape_Red   .make(125), FL.Juice_Apple.make(125)), FL.Med_Laxative.make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Juice_Grape_Purple.make(125), FL.Juice_Apple.make(125)), FL.Med_Laxative.make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Juice_Grape_White .make(125), FL.Juice_Plum .make(125)), FL.Med_Laxative.make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Juice_Grape_Green .make(125), FL.Juice_Plum .make(125)), FL.Med_Laxative.make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Juice_Grape_Red   .make(125), FL.Juice_Plum .make(125)), FL.Med_Laxative.make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Juice_Grape_Purple.make(125), FL.Juice_Plum .make(125)), FL.Med_Laxative.make(250), ZL_IS);
		
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Sap_Rainbow       .make(125), FL.Ambrosia   .make(125)), FL.Med_Heal    .make(250), ZL_IS);
		
		
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice             .make(125)), FL.Smoothie_Fruit       .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Ananas      .make(125)), FL.Smoothie_Ananas      .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Apple       .make(125)), FL.Smoothie_Apple       .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_AppleGrC    .make(125)), FL.Smoothie_Apple       .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Apricot     .make(125)), FL.Smoothie_Apricot     .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Banana      .make(125)), FL.Smoothie_Banana      .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Blackberry  .make(125)), FL.Smoothie_Blackberry  .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Blueberry   .make(125)), FL.Smoothie_Blueberry   .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Cherry      .make(125)), FL.Smoothie_Cherry      .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Cranberry   .make(125)), FL.Smoothie_Cranberry   .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Currant     .make(125)), FL.Smoothie_Currant     .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Elderberry  .make(125)), FL.Smoothie_Elderberry  .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Gooseberry  .make(125)), FL.Smoothie_Gooseberry  .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Grape_Red   .make(125)), FL.Smoothie_Grape_Red   .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Grape_White .make(125)), FL.Smoothie_Grape_White .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Grape_Green .make(125)), FL.Smoothie_Grape_Green .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Grape_Purple.make(125)), FL.Smoothie_Grape_Purple.make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Grapefruit  .make(125)), FL.Smoothie_Grapefruit  .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Kiwi        .make(125)), FL.Smoothie_Kiwi        .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Lemon       .make(125)), FL.Smoothie_Lemon       .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Lime        .make(125)), FL.Smoothie_Lime        .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Melon       .make(125)), FL.Smoothie_Melon       .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Orange      .make(125)), FL.Smoothie_Orange      .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Persimmon   .make(125)), FL.Smoothie_Persimmon   .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Peach       .make(125)), FL.Smoothie_Peach       .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Pear        .make(125)), FL.Smoothie_Pear        .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Plum        .make(125)), FL.Smoothie_Plum        .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Raspberry   .make(125)), FL.Smoothie_Raspberry   .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Strawberry  .make(125)), FL.Smoothie_Strawberry  .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Mango       .make(125)), FL.Smoothie_Mango       .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Starfruit   .make(125)), FL.Smoothie_Starfruit   .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Fig         .make(125)), FL.Smoothie_Fig         .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Pomegranate .make(125)), FL.Smoothie_Pomegranate .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Papaya      .make(125)), FL.Smoothie_Papaya      .make(250), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.Juice_Coconut     .make(125)), FL.Smoothie_Coconut     .make(250), ZL_IS);
		for (String tJuice : FluidsGT.FRUIT_JUICE) if (FL.exists(tJuice)) {
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Ice.make(125), FL.make(tJuice           , 125)), FL.Smoothie_Fruit       .make(250), ZL_IS);
		RM.CryoMixer        .addRecipe0(T, 16,   16, FL.array(FL.make(tJuice, 125), FL.Tea_Sweet.make(125)), FL.Tea_Ice.make(250), ZL_IS);
		}
	}
}
