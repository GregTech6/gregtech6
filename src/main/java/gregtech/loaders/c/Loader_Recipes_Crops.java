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
import gregapi.data.CS.Sandwiches;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.oredict.event.OreDictListenerEvent_TwoNames;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Loader_Recipes_Crops implements Runnable {
	@Override public void run() {
		for (OreDictMaterial tMat : ANY.FlourGrains.mToThis) {
			RM.biomass(OP.dust      .mat(tMat, 9));
			RM.biomass(OP.blockDust .mat(tMat, 1));
		}
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		
		addListener(new OreDictListenerEvent_TwoNames("flowerCerublossom", "flowerDesertNova") {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
			RM.Mixer    .addRecipeX(T, 16, 16, ST.array(aStack1, aStack2, OM.dust(MT.ArcaneAsh), OM.dust(MT.Vinteum)), OM.dust(MT.VinteumPurified));
		}});
		addListener("flowerCerublossom", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Squeezer .addRecipe1(T, 16, 16, 2000, aEvent.mStack, NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], 2), IL.Remains_Plant.get(1));
			RM.Juicer   .addRecipe1(T, 16, 16, 3000, aEvent.mStack, NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], 1), IL.Remains_Plant.get(1));
			RM.ic2_extractor(aEvent.mStack, dust.mat(MT.Blue, 3));
		}});
		addListener("flowerDesertNova", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Squeezer .addRecipe1(T, 16, 16, 7000, aEvent.mStack, NF, FL.Juice_Cactus.make(100), IL.Dye_Cactus.get(2));
			RM.Juicer   .addRecipe1(T, 16, 16, 9000, aEvent.mStack, NF, FL.Juice_Cactus.make( 75), IL.Dye_Cactus.get(2));
			RM.ic2_extractor(aEvent.mStack, IL.Dye_Cactus.get(3));
		}});
		addListener("flowerCinderpearl", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Squeezer .addRecipe1(T, 16, 16, aEvent.mStack, ST.make(Items.blaze_powder, 1, 0));
			RM.Juicer   .addRecipe1(T, 16, 16, aEvent.mStack, ST.make(Items.blaze_powder, 1, 0));
			RM.Shredder .addRecipe1(T, 16, 16, aEvent.mStack, ST.make(Items.blaze_powder, 1, 0));
			RM.Mortar   .addRecipe1(T, 16, 16, aEvent.mStack, ST.make(Items.blaze_powder, 1, 0));
			RM.ic2_extractor(aEvent.mStack, ST.make(Items.blaze_powder, 1, 0));
		}});
		addListener("flowerShimmerleaf", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Squeezer .addRecipe1(T, 16, 16, 2000, aEvent.mStack, NF, MT.Hg.liquid(U, F), IL.Remains_Plant.get(1));
			RM.Juicer   .addRecipe1(T, 16, 16, 3000, aEvent.mStack, NF, MT.Hg.liquid(U, F), IL.Remains_Plant.get(1));
		}});
		
		
		addListener(OD.bamboo, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.biomass(ST.amount(4, aEvent.mStack));
			RM.CokeOven.addRecipe1(F, 0, 1800, ST.amount(1, aEvent.mStack), NF, FL.Oil_Creosote.make(50), IL.GrC_Bamboo_Charcoal.get(1, OP.stick.mat(MT.Charcoal, 1)));
		}});
		
		
		addListener(OD.baleGrassRotten, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.biomass(ST.amount(1, aEvent.mStack), 16);
			RM.unpack(aEvent.mStack, IL.Grass_Rotten.get(9));
		}});
		addListener(OD.baleGrassMoldy, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.biomass(ST.amount(1, aEvent.mStack), 16);
			RM.unpack(aEvent.mStack, IL.Grass_Moldy.get(9));
		}});
		addListener(OD.baleGrassDry, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.biomass(ST.amount(1, aEvent.mStack));
			RM.unpack(aEvent.mStack, IL.Grass_Dry.get(9));
		}});
		addListener(OD.baleGrass, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Drying.addRecipe1(T, 16, 360, aEvent.mStack, NF, FL.DistW.make(180), IL.Bale_Dry.get(1));
			RM.biomass(ST.amount(1, aEvent.mStack));
			RM.unpack(aEvent.mStack, IL.Grass.get(9));
		}});
		addListener(OD.itemGrassRotten, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.biomass(ST.amount(9, aEvent.mStack), 16);
			RM.compact(aEvent.mStack, 9, IL.Bale_Rotten.get(1));
		}});
		addListener(OD.itemGrassMoldy, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.biomass(ST.amount(9, aEvent.mStack), 16);
			RM.compact(aEvent.mStack, 9, IL.Bale_Moldy.get(1));
		}});
		addListener(OD.itemGrassDry, "cropStraw", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.biomass(ST.amount(9, aEvent.mStack));
			RM.compact(aEvent.mStack, 9, IL.Bale_Dry.get(1));
		}});
		addListener(OD.itemGrass, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Drying.addRecipe1(T, 16, 40, aEvent.mStack, NF, FL.DistW.make(20), IL.Grass_Dry.get(1));
			RM.biomass(ST.amount(9, aEvent.mStack));
			RM.compact(aEvent.mStack, 9, IL.Bale.get(1));
		}});
		addListener(OD.itemPlantRemains, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.biomass(ST.amount(8, aEvent.mStack));
			RM.generify(aEvent.mStack, IL.Remains_Plant.get(1));
		}});
		
		
		
		addListener("cropRice", "cropWildRice", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			RM.Drying       .addRecipe1(T, 16, 40, aEvent.mStack, NF, FL.DistW.make(20), IL.Grass_Dry.get(1));
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.Water.make(250), FL.Mash_Rice.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.SpDew.make(250), FL.Mash_Rice.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.DistW.make(250), FL.Mash_Rice.make(250), ZL_IS);
			RM.Shredder     .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Rice), IL.Grass.get(1));
			RM.Mortar       .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Rice, U2), IL.Grass.get(1));
			RM.ae_grinder(4, aEvent.mStack, OM.dust(MT.Rice), IL.Grass.get(1), 0.8F);
			RM.biomass(ST.amount(9, aEvent.mStack));
			RM.compact(aEvent.mStack, 9, IL.Bale_Rice.get(1));
		}});
		addListener("baleRice", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Shredder.addRecipe1(T, 16, 144, aEvent.mStack, OM.dust(MT.Rice, U*9), IL.Grass.get(9));
			RM.Drying.addRecipe1(T, 16, 360, aEvent.mStack, NF, FL.DistW.make(180), IL.Bale_Dry.get(1));
			RM.biomass(ST.amount(1, aEvent.mStack));
			RM.unpack(aEvent.mStack, IL.Crop_Rice.get(9));
		}});
		
		addListener("cropOats", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			RM.Drying       .addRecipe1(T, 16, 40, aEvent.mStack, NF, FL.DistW.make(20), IL.Grass_Dry.get(1));
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.Water.make(250), FL.Mash_Grain.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.SpDew.make(250), FL.Mash_Grain.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.DistW.make(250), FL.Mash_Grain.make(250), ZL_IS);
			RM.Shredder     .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Oat), IL.Grass.get(1));
			RM.Mortar       .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Oat, U2), IL.Grass.get(1));
			RM.ae_grinder(4, aEvent.mStack, OM.dust(MT.Oat), IL.Grass.get(1), 0.8F);
			RM.biomass(ST.amount(9, aEvent.mStack));
			RM.compact(aEvent.mStack, 9, IL.Bale_Oats.get(1));
		}});
		addListener("baleOats", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Shredder.addRecipe1(T, 16, 144, aEvent.mStack, OM.dust(MT.Oat, U*9), IL.Grass.get(9));
			RM.Drying.addRecipe1(T, 16, 360, aEvent.mStack, NF, FL.DistW.make(180), IL.Bale_Dry.get(1));
			RM.biomass(ST.amount(1, aEvent.mStack));
			RM.unpack(aEvent.mStack, IL.Crop_Oats.get(9));
		}});
		
		addListener("cropAbyssalOats", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			// Those things grow in the Nether, that's why dry.
			RM.Drying       .addRecipe1(T, 16, 10, aEvent.mStack, NF, FL.DistW.make(5), IL.Grass_Dry.get(1));
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.Water.make(250), FL.Mash_Grain.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.SpDew.make(250), FL.Mash_Grain.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.DistW.make(250), FL.Mash_Grain.make(250), ZL_IS);
			RM.Shredder     .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.OatAbyssal), IL.Grass_Dry.get(1));
			RM.Mortar       .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.OatAbyssal, U2), IL.Grass_Dry.get(1));
			RM.ae_grinder(4, aEvent.mStack, OM.dust(MT.OatAbyssal), IL.Grass_Dry.get(1), 0.8F);
			RM.biomass(ST.amount(9, aEvent.mStack));
			RM.compact(aEvent.mStack, 9, IL.Bale_AbyssalOats.exists() ? IL.Bale_AbyssalOats.get(1) : IL.Bale_Dry.get(1));
		}});
		addListener("baleAbyssalOats", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			// Those things grow in the Nether, that's why dry.
			RM.Shredder.addRecipe1(T, 16, 144, aEvent.mStack, OM.dust(MT.OatAbyssal, U*9), IL.Grass_Dry.get(9));
			RM.Drying.addRecipe1(T, 16, 90, aEvent.mStack, NF, FL.DistW.make(45), IL.Bale_Dry.get(1));
			RM.biomass(ST.amount(1, aEvent.mStack));
			RM.unpack(aEvent.mStack, IL.Crop_AbyssalOats.exists() ? IL.Crop_AbyssalOats.get(9) : IL.Grass_Dry.get(9));
		}});
		
		addListener("cropBarley", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			RM.Drying       .addRecipe1(T, 16, 40, aEvent.mStack, NF, FL.DistW.make(20), IL.Grass_Dry.get(1));
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.Water.make(250), FL.Mash_Grain.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.SpDew.make(250), FL.Mash_Grain.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.DistW.make(250), FL.Mash_Grain.make(250), ZL_IS);
			RM.Shredder     .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Barley), IL.Grass.get(1));
			RM.Mortar       .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Barley, U2), IL.Grass.get(1));
			RM.ae_grinder(4, aEvent.mStack, OM.dust(MT.Barley), IL.Grass.get(1), 0.8F);
			RM.biomass(ST.amount(9, aEvent.mStack));
			RM.compact(aEvent.mStack, 9, IL.Bale_Barley.get(1));
		}});
		addListener("baleBarley", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Shredder.addRecipe1(T, 16, 144, aEvent.mStack, OM.dust(MT.Barley, U*9), IL.Grass.get(9));
			RM.Drying.addRecipe1(T, 16, 360, aEvent.mStack, NF, FL.DistW.make(180), IL.Bale_Dry.get(1));
			RM.biomass(ST.amount(1, aEvent.mStack));
			RM.unpack(aEvent.mStack, IL.Crop_Barley.get(9));
		}});
		
		addListener("cropRye", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			RM.Drying       .addRecipe1(T, 16, 40, aEvent.mStack, NF, FL.DistW.make(20), IL.Grass_Dry.get(1));
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.Water.make(250), FL.Mash_Rye.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.SpDew.make(250), FL.Mash_Rye.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.DistW.make(250), FL.Mash_Rye.make(250), ZL_IS);
			RM.Shredder     .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Rye), IL.Grass.get(1));
			RM.Mortar       .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Rye, U2), IL.Grass.get(1));
			RM.ae_grinder(4, aEvent.mStack, OM.dust(MT.Rye), IL.Grass.get(1), 0.8F);
			RM.biomass(ST.amount(9, aEvent.mStack));
			RM.compact(aEvent.mStack, 9, IL.Bale_Rye.get(1));
		}});
		addListener("baleRye", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Shredder.addRecipe1(T, 16, 144, aEvent.mStack, OM.dust(MT.Rye, U*9), IL.Grass.get(9));
			RM.Drying.addRecipe1(T, 16, 360, aEvent.mStack, NF, FL.DistW.make(180), IL.Bale_Dry.get(1));
			RM.biomass(ST.amount(1, aEvent.mStack));
			RM.unpack(aEvent.mStack, IL.Crop_Rye.get(9));
		}});
		
		addListener("cropWheat", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			RM.Drying       .addRecipe1(T, 16, 40, aEvent.mStack, NF, FL.DistW.make(20), IL.Grass_Dry.get(1));
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.Water.make(250), FL.Mash_Wheat.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.SpDew.make(250), FL.Mash_Wheat.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.DistW.make(250), FL.Mash_Wheat.make(250), ZL_IS);
			RM.Shredder     .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Wheat), IL.Grass.get(1));
			RM.Mortar       .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Wheat, U2), IL.Grass.get(1));
			RM.ae_grinder(4, aEvent.mStack, OM.dust(MT.Wheat), IL.Grass.get(1), 0.8F);
			RM.biomass(ST.amount(9, aEvent.mStack));
			RM.compact(aEvent.mStack, 9, IL.Bale_Wheat.get(1));
		}});
		addListener("baleWheat", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Shredder.addRecipe1(T, 16, 144, aEvent.mStack, OM.dust(MT.Wheat, U*9), IL.Grass.get(9));
			RM.Drying.addRecipe1(T, 16, 360, aEvent.mStack, NF, FL.DistW.make(180), IL.Bale_Dry.get(1));
			RM.biomass(ST.amount(1, aEvent.mStack));
			RM.unpack(aEvent.mStack, IL.Crop_Wheat.get(9));
		}});
		
		
		
		addListener(new String[] {"seedSoybean", "seedCoffee", "seedCrop", "seedThistle", "seedFlower", "seedWitherShrub", "seedHellderberry", "seedGhost", "seedQuartzBerry", "seedHellBush"}, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			RM.Squeezer .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Oil_Seed.make(20), ZL_IS);
			RM.Juicer   .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Oil_Seed.make(20), ZL_IS);
		}});
		addListener(new String[] {"seedMelon", "seedStrawberry", "seedTurnip", "seedBeet", "seedCorn", "seedsAcresia", "seedMisc"}, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			RM.Squeezer .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Oil_Seed.make(30), ZL_IS);
			RM.Juicer   .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Oil_Seed.make(30), ZL_IS);
		}});
		addListener(new String[] {"seedRice", "seedApple", "seedGrape", "seedCabbage", "seedPurplePear"}, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			RM.Squeezer .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Oil_Seed.make(40), ZL_IS);
			RM.Juicer   .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Oil_Seed.make(40), ZL_IS);
		}});
		addListener(new String[] {"seedWheat", "seedBarley", "seedOats", "seedAbyssalOats", "seedRye", "seedCamellia"}, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			RM.Squeezer .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Oil_Seed.make(50), ZL_IS);
			RM.Juicer   .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Oil_Seed.make(50), ZL_IS);
		}});
		addListener(new String[] {"seedPumpkin", "seedDarkFruit", "seedAspectrus"}, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			RM.Squeezer .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Oil_Seed.make(60), ZL_IS);
			RM.Juicer   .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Oil_Seed.make(60), ZL_IS);
		}});
		addListener("seedCotton", "seedHemp", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			RM.Squeezer .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.lube(5), ZL_IS);
			RM.Juicer   .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.lube(5), ZL_IS);
		}});
		addListener("seedFlax", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			RM.Squeezer .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.lube(10), ZL_IS);
			RM.Juicer   .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.lube(10), ZL_IS);
		}});
		addListener("seedCanola", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			if (FL.Oil_Canola.exists()) {
			RM.Squeezer     .addRecipe1(T, 16, 16, 3000, aEvent.mStack, NF, FL.Oil_Canola.make(14), IL.Remains_Plant.get(1));
			RM.Juicer       .addRecipe1(T, 16, 16, 4000, aEvent.mStack, NF, FL.Oil_Canola.make(12), IL.Remains_Plant.get(1));
			} else {
			RM.Squeezer     .addRecipe1(T, 16, 16, 3000, aEvent.mStack, NF, FL.lube(7), IL.Remains_Plant.get(1));
			RM.Juicer       .addRecipe1(T, 16, 16, 4000, aEvent.mStack, NF, FL.lube(6), IL.Remains_Plant.get(1));
			}
		}});
		addListener("seedSunflower", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OP.dust.contains(aEvent.mStack)) return;
			RM.Squeezer .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Oil_Sunflower.make(100), ZL_IS);
			RM.Juicer   .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Oil_Sunflower.make( 75), ZL_IS);
		}});
		
		
		
		addListener("cropHemp", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Loom         .addRecipe2(T, 16, 16, ST.tag(10), ST.amount(4, aEvent.mStack), IL.Rope.get(1));
			RM.Loom         .addRecipe2(T, 16, 16, ST.tag( 0), ST.amount(9, aEvent.mStack), ST.make(Blocks.wool, 1, 0));
			RM.Loom         .addRecipe2(T, 16, 16, ST.tag( 1), ST.amount(3, aEvent.mStack), ST.make(Items.string, 1, 0));
			RM.crop(aEvent.mStack, FL.Oil_Hemp, 100, IL.Remains_Plant.get(1), 3000, null, null, 0, 0, 0, 0, 0);
		}});
		addListener("cropFlax", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Loom         .addRecipe2(T, 16, 16, ST.tag( 0), ST.amount(4, aEvent.mStack), ST.make(Blocks.wool, 1, 0));
			RM.Loom         .addRecipe2(T, 16, 16, ST.tag( 1), ST.amount(2, aEvent.mStack), ST.make(Items.string, 1, 0));
			RM.Squeezer     .addRecipe1(T, 16, 16, 3000, aEvent.mStack, NF, FL.lube(30), IL.Remains_Plant.get(1));
			RM.Juicer       .addRecipe1(T, 16, 16, 4000, aEvent.mStack, NF, FL.lube(20), IL.Remains_Plant.get(1));
		}});
		addListener("cropCanola", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (FL.Oil_Canola.exists()) {
			RM.Squeezer     .addRecipe1(T, 16, 16, 3000, aEvent.mStack, NF, FL.Oil_Canola.make(140), IL.Remains_Plant.get(1));
			RM.Juicer       .addRecipe1(T, 16, 16, 4000, aEvent.mStack, NF, FL.Oil_Canola.make(120), IL.Remains_Plant.get(1));
			} else {
			RM.Squeezer     .addRecipe1(T, 16, 16, 3000, aEvent.mStack, NF, FL.lube(70), IL.Remains_Plant.get(1));
			RM.Juicer       .addRecipe1(T, 16, 16, 4000, aEvent.mStack, NF, FL.lube(60), IL.Remains_Plant.get(1));
			}
		}});
		addListener("cropIvy", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Squeezer     .addRecipe1(T, 16, 16, 7000, aEvent.mStack, NF, FL.Potion_Poison_1.make(500), IL.Remains_Plant.get(1));
			RM.Juicer       .addRecipe1(T, 16, 16, 8000, aEvent.mStack, NF, FL.Potion_Poison_1.make(250), IL.Remains_Plant.get(1));
			RM.Distillery   .addRecipe1(T, 16, 48, aEvent.mStack, FL.Potion_Awkward.make(750), FL.Potion_Poison_1.make(750), ZL_IS);
		}});
		addListener("cropCandle", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Squeezer     .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Juice.make(25), OP.dustSmall.mat(MT.WaxPlant, 1));
			RM.Juicer       .addRecipe1(T, 16, 16, aEvent.mStack, NF, FL.Juice.make(20), OP.dustSmall.mat(MT.WaxPlant, 1));
			RM.ae_grinder(4, aEvent.mStack, OP.dustSmall.mat(MT.WaxPlant, 2), OP.dustSmall.mat(MT.WaxPlant, 1), 0.5F, OP.dustSmall.mat(MT.WaxPlant, 1), 0.5F);
		}});
		addListener("cropCandleberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop(aEvent.mStack, FL.Juice, 25, OP.dustSmall.mat(MT.WaxPlant, 4), 3000, "Canned Candleberries", IL.CANS_FRUIT, 0, 0, 0, 4, 0);
		}});
		
		
		addListener("cropOlive", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Oil_Olive, 100, 8000, "Canned Olives", 0, 0, 8, 4, 0);
		}});
		addListener("cropSunflower", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop(aEvent.mStack, FL.Oil_Sunflower, 100, IL.Remains_Plant.get(1), 8000, null, null, 0, 0, 8, 2, 0);
		}});
		
		
		addListener("cropStarAnise", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop(aEvent.mStack, FL.Oil_Seed, 100, IL.Remains_Plant.get(1), 7000, null, null, 0, 0, 0, 8, 0);
		}});
		
		
		addListener("cropCocoa", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop(aEvent.mStack, FL.Oil_Seed, 100, OP.dustSmall.mat(MT.Cocoa, 4), 8000, null, null, 0, 0, 4, 4, 0);
			RM.ae_grinder(4, aEvent.mStack, OP.dustSmall.mat(MT.Cocoa, 2), OP.dustSmall.mat(MT.Cocoa, 1), 0.5F, OP.dustSmall.mat(MT.Cocoa, 1), 0.5F);
		}});
		addListener("cropCoffee", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop(aEvent.mStack, FL.Oil_Seed, 100, OP.dustSmall.mat(MT.Coffee, 4), 8000, null, null, 0, 4, 4, 4, 0);
			RM.ae_grinder(4, aEvent.mStack, OP.dustSmall.mat(MT.Coffee, 2), OP.dustSmall.mat(MT.Coffee, 1), 0.5F, OP.dustSmall.mat(MT.Coffee, 1), 0.5F);
		}});
		addListener("cropTea", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop(aEvent.mStack, null, 0, OP.dustTiny.mat(MT.Tea, 2), 9000, null, null, 0, 4, 0, 0, 0);
			RM.ae_grinder(4, aEvent.mStack, OP.dustTiny.mat(MT.Tea, 1), OP.dustTiny.mat(MT.Tea, 1), 0.25F, OP.dustTiny.mat(MT.Tea, 1), 0.25F);
		}});
		addListener("cropHops", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer.addRecipe1(T, 16, 16, aEvent.mStack, FL.Water.make(250), FL.Mash_Hops.make(250), ZL_IS);
			RM.Mixer.addRecipe1(T, 16, 16, aEvent.mStack, FL.SpDew.make(250), FL.Mash_Hops.make(250), ZL_IS);
			RM.Mixer.addRecipe1(T, 16, 16, aEvent.mStack, FL.DistW.make(250), FL.Mash_Hops.make(250), ZL_IS);
		}});
		addListener("cropTobacco", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			ItemStack tStack = OreDictManager.INSTANCE.getFirstOre("leafTobaccoDried", 1); if (ST.valid(tStack))
			RM.Drying.addRecipe1(T, 16, 50, aEvent.mStack, NF, FL.DistW.make(25), tStack);
		}});
		addListener("cropCoca", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			ItemStack tStack = OreDictManager.INSTANCE.getFirstOre("leafCocaDried", 1); if (ST.valid(tStack))
			RM.Drying.addRecipe1(T, 16, 50, aEvent.mStack, NF, FL.DistW.make(25), tStack);
		}});
		addListener("budCannabis", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			ItemStack tStack = OreDictManager.INSTANCE.getFirstOre("budCannabisDried", 1); if (ST.valid(tStack))
			RM.Drying.addRecipe1(T, 16, 50, aEvent.mStack, NF, FL.DistW.make(25), tStack);
		}});
		addListener("leafCannabis", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			ItemStack tStack = OreDictManager.INSTANCE.getFirstOre("leafCannabisDried", 1); if (ST.valid(tStack))
			RM.Drying.addRecipe1(T, 16, 50, aEvent.mStack, NF, FL.DistW.make(25), tStack);
		}});
		
		
		addListener("cropPeanut", "cropPeanuts", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 100, OP.dustSmall.mat(MT.Peanut, 4), 8000, "Canned Peanuts");
			RM.ae_grinder(4, aEvent.mStack, OP.dustSmall.mat(MT.Peanut, 2), OP.dustSmall.mat(MT.Peanut, 1), 0.5F, OP.dustSmall.mat(MT.Peanut, 1), 0.5F);
		}});
		addListener("cropHazelnut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 100, OP.dustSmall.mat(MT.Hazelnut, 4), 8000, "Canned Hazelnuts");
			RM.ae_grinder(4, aEvent.mStack, OP.dustSmall.mat(MT.Hazelnut, 2), OP.dustSmall.mat(MT.Hazelnut, 1), 0.5F, OP.dustSmall.mat(MT.Hazelnut, 1), 0.5F);
		}});
		addListener("cropPistachio", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 100, OP.dustSmall.mat(MT.Pistachio, 4), 8000, "Canned Pistachios");
			RM.ae_grinder(4, aEvent.mStack, OP.dustSmall.mat(MT.Pistachio, 2), OP.dustSmall.mat(MT.Pistachio, 1), 0.5F, OP.dustSmall.mat(MT.Pistachio, 1), 0.5F);
		}});
		addListener("cropNutmeg", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 100, OP.dustSmall.mat(MT.Nutmeg, 4), 8000, "Canned Nutmegs");
			RM.ae_grinder(4, aEvent.mStack, OP.dustSmall.mat(MT.Nutmeg, 2), OP.dustSmall.mat(MT.Nutmeg, 1), 0.5F, OP.dustSmall.mat(MT.Nutmeg, 1), 0.5F);
		}});
		addListener("cropAlmond", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 100, OP.dustSmall.mat(MT.Almond, 4), 8000, "Canned Almonds");
			RM.ae_grinder(4, aEvent.mStack, OP.dustSmall.mat(MT.Almond, 2), OP.dustSmall.mat(MT.Almond, 1), 0.5F, OP.dustSmall.mat(MT.Almond, 1), 0.5F);
		}});
		addListener("cropCandlenut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 100, OP.dustSmall.mat(MT.WaxPlant, 4), 8000, "Canned Candlenuts");
			RM.ae_grinder(4, aEvent.mStack, OP.dustSmall.mat(MT.WaxPlant, 2), OP.dustSmall.mat(MT.WaxPlant, 1), 0.5F, OP.dustSmall.mat(MT.WaxPlant, 1), 0.5F);
		}});
		addListener("cropPecan", "cropPecannut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 100, 8000, "Canned Pecannuts");
		}});
		addListener("cropBeechnut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 100, 8000, "Canned Beechnuts");
		}});
		addListener("cropBrazilNut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 100, 8000, "Canned Brazilnuts");
		}});
		addListener("cropGingkoNut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 100, 8000, "Canned Gingkonuts");
		}});
		addListener("cropCashew", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 100, 8000, "Canned Cashews");
		}});
		addListener("cropAcorn", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 100, 6000, "Canned Acorns");
		}});
		addListener("cropButternut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 125, 6000, "Canned Butternuts");
		}});
		addListener("cropWalnut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OM.is("cropButternut", aEvent.mStack)) return;
			RM.crop_nut(aEvent.mStack, 125, 6000, "Canned Walnuts");
			RM.boxunbox(ST.make(MD.FR, "crate", 1), ST.make(MD.FR, "cratedWalnut", 1), ST.amount(9, aEvent.mStack));
		}});
		addListener("cropChestnut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_nut(aEvent.mStack, 150, 4000, "Canned Chestnuts");
			RM.boxunbox(ST.make(MD.FR, "crate", 1), ST.make(MD.FR, "cratedChestnut", 1), ST.amount(9, aEvent.mStack));
		}});
		
		
		addListener("cropCorn", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Corn", IL.CANS_VEGGIE);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.Water.make(250), FL.Mash_Corn.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.SpDew.make(250), FL.Mash_Corn.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.DistW.make(250), FL.Mash_Corn.make(250), ZL_IS);
			RM.Shredder     .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Corn));
			RM.Mortar       .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Corn, U2));
			RM.ae_grinder(4, aEvent.mStack, OM.dust(MT.Corn));
			if (!ST.isGT(aEvent.mStack)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 4, 0);
		}});
		addListener("cropDevilishMaize", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.food_can(aEvent.mStack, Math.max(1, ST.food(aEvent.mStack)), "Canned Devilish Maize", IL.CANS_VEGGIE);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.Water.make(250), FL.Mash_Corn.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.SpDew.make(250), FL.Mash_Corn.make(250), ZL_IS);
			RM.Mixer        .addRecipe1(T, 16, 16, aEvent.mStack, FL.DistW.make(250), FL.Mash_Corn.make(250), ZL_IS);
			RM.Shredder     .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Corn));
			RM.Mortar       .addRecipe1(T, 16, 16, aEvent.mStack, OM.dust(MT.Corn, U2));
			RM.ae_grinder(4, aEvent.mStack, OM.dust(MT.Corn));
			if (!ST.isGT(aEvent.mStack)) FoodsGT.put(aEvent.mStack, 0, 0, 0, 4, 0);
		}});
		
		
		addListener("cropCrabapple", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Apple, 100, 7000, "Canned Crabapples", 0, 0, 0, 8, 0);
		}});
		addListener("cropAppleWhite", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Apple, 150, 9000, "Canned White Apples", 0, 0, 0,12, 0);
		}});
		addListener("cropAppleGreen", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Apple, 150, 8000, "Canned Green Apples", 0, 0, 0, 4, 0);
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Quarters_Hollow.get(0), IL.Food_Apple_Green_Sliced.get(4));
		}});
		addListener("cropAppleYellow", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Apple, 125, 8000, "Canned Yellow Apples", 0, 0, 0, 8, 0);
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Quarters_Hollow.get(0), IL.Food_Apple_Yellow_Sliced.get(4));
		}});
		addListener("cropAppleDarkRed", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Apple, 125, 9000, "Canned Dark Red Apples", 0, 0, 0,12, 0);
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Quarters_Hollow.get(0), IL.Food_Apple_DarkRed_Sliced.get(4));
		}});
		addListener("cropAppleRed", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Apple, 100, 7000, "Canned Red Apples", 0, 0, 0, 8, 0);
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Quarters_Hollow.get(0), IL.Food_Apple_Red_Sliced.get(4));
		}});
		addListener("cropApple", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OM.is("cropAppleWhite", aEvent.mStack) || OM.is("cropCrabapple", aEvent.mStack)) return;
			RM.Bath.addRecipe1(T, 0, 128, aEvent.mStack, MT.Au.liquid(U*8, T), NF, ST.make(Items.golden_apple, 1, 0));
			if (OM.is("cropAppleDarkRed", aEvent.mStack) || OM.is("cropAppleRed", aEvent.mStack) || OM.is("cropAppleYellow", aEvent.mStack) || OM.is("cropAppleGreen", aEvent.mStack)) return;
			RM.crop_fruit(aEvent.mStack, FL.Juice_Apple, 100, 7000, "Canned Apples", 0, 0, 0, 8, 0);
		}});
		
		
		addListener("cropMelon", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Melon, 250, 6000, "Canned Melons", 0, 0, 0, 8, 0);
		}});
		
		
		addListener("cropCoconut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop(aEvent.mStack, FL.Juice_Coconut, 200, IL.Remains_Nut.get(1), 7000, "Canned Coconuts", IL.CANS_FRUIT, 0, 0, 0, 8, 0);
		}});
		
		
		addListener("cropBerry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 100, 4000, "Canned Berries", 0, 0, 0, 8, 0);
		}});
		addListener("cropRowanberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 150, 3000, "Canned Rowan Berries", 0, 0, 0, 8, 0);
		}});
		addListener("cropSwampberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 150, 3000, "Canned Swampberries", 0, 0, 0, 8, 0);
		}});
		addListener("cropHeartberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 100, 5000, "Canned Heartberries", 0, 0, 0, 4, 0);
		}});
		addListener("cropJuniper", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 100, 5000, "Canned Junipers", 0, 0, 0, 8, 0);
		}});
		addListener("cropBlackthorn", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 100, 5000, "Canned Blackthorn", 0, 0, 0, 8, 0);
		}});
		addListener("cropBlackberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Blackberry, 100, 4000, "Canned Blackberries", 0, 0, 0, 8, 0);
		}});
		addListener("cropBlueberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Blueberry, 100, 4000, "Canned Blueberries", 0, 0, 0, 8, 0);
		}});
		addListener("cropRaspberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Raspberry, 100, 4000, "Canned Raspberries", 0, 0, 0, 8, 0);
		}});
		addListener("cropCranberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Cranberry, 100, 4000, "Canned Cranberries", 0, 0, 0, 8, 0);
		}});
		addListener("cropGooseberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Gooseberry, 100, 5000, "Canned Gooseberries", 0, 0, 0, 8, 0);
		}});
		addListener("cropStrawberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Strawberry, 100, 4000, "Canned Strawberries", 0, 0, 0, 8, 0);
		}});
		addListener("fruitsAcresia", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 100, 4000, "Canned Acresias", 0, 0, 0, 8, 0);
		}});
		addListener("cropWyndberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 100, 4000, "Canned Wyndberries", 0, 0, 0, 8, 0);
		}});
		addListener("cropElderberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Elderberry, 100, 4000, "Canned Elderberries", 0, 0, 0, 8, 0);
		}});
		addListener("cropHellderberry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Hellderberry, 100, 4000, "Canned Hellderberries", 0, 0, 0, 8, 0);
		}});
		addListener("cropCurrantsRed", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Currant, 100, 4000, "Canned Red Currants", 0, 0, 0, 8, 0);
		}});
		addListener("cropCurrantsWhite", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Currant, 100, 4000, "Canned White Currants", 0, 0, 0, 8, 0);
		}});
		addListener("cropCurrantsBlack", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Currant, 100, 4000, "Canned Black Currants", 0, 0, 0, 6, 0);
		}});
		addListener("cropCurrants", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OM.is("cropCurrantsRed", aEvent.mStack) || OM.is("cropCurrantsWhite", aEvent.mStack) || OM.is("cropCurrantsBlack", aEvent.mStack)) return;
			RM.crop_fruit(aEvent.mStack, FL.Juice_Currant, 100, 4000, "Canned Currants", 0, 0, 0, 8, 0);
		}});
		
		
		addListener("cropBlackCherry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Cherry, 100, 5000, "Canned Black Cherries", 0, 0, 0,12, 0);
		}});
		addListener("cropSourCherry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Cherry, 100, 5000, "Canned Sour Cherries", 0, 0, 0,12, 0);
		}});
		addListener("cropWildCherry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Cherry, 100, 5000, "Canned Wild Cherries", 0, 0, 0,12, 0);
		}});
		addListener("cropCherry", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OM.is("cropBlackCherry", aEvent.mStack) || OM.is("cropSourCherry", aEvent.mStack) || OM.is("cropWildCherry", aEvent.mStack)) return;
			RM.crop_fruit(aEvent.mStack, FL.Juice_Cherry, 100, 5000, "Canned Cherries", 0, 0, 0,12, 0);
			RM.boxunbox(ST.make(MD.FR, "crate", 1), ST.make(MD.FR, "cratedCherry", 1), ST.amount(9, aEvent.mStack));
		}});
		
		
		addListener("cropCactusfruit", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_CactusFruit, 100, 7000, "Canned Cactusfruits", 0, 0, 8, 8, 0);
		}});
		addListener("cropPricklyPair", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_CactusFruit, 100, 7000, "Canned Prickly Pairs", 0, 0, 8, 8, 0);
		}});
		
		
		addListener("cropDragonfruit", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 100, 8000, "Canned Dragonfruits", 0, 0, 8, 8, 0);
		}});
		
		
		addListener("cropDarkFruit", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 100, 6000, "Canned Dark Fruits", 0, 0, 0, 8, 0);
		}});
		
		
		addListener("cropKiwi", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Kiwi, 125, 8000, "Canned Kiwis", 0, 0, 0, 4, 0);
		}});
		
		
		addListener("cropKeyLime", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Lime, 125, 8000, "Canned Key Limes", 0, 0, 0, 4, 0);
		}});
		addListener("cropFingerLime", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Lime, 125, 8000, "Canned Finger Limes", 0, 0, 0, 4, 0);
		}});
		addListener("cropLime", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OM.is("cropFingerLime", aEvent.mStack) || OM.is("cropKeyLime", aEvent.mStack)) return;
			RM.crop_fruit(aEvent.mStack, FL.Juice_Lime, 125, 8000, "Canned Limes", 0, 0, 0, 4, 0);
		}});
		
		
		addListener("cropBuddhaHand", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Lemon, 125, 7000, "Canned Buddha Hands", 0, 0, 0, 4, 0);
		}});
		addListener("cropCitron", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OM.is("cropBuddhaHand", aEvent.mStack)) return;
			RM.crop_fruit(aEvent.mStack, FL.Juice_Lemon, 125, 7000, "Canned Citrons", 0, 0, 0, 4, 0);
		}});
		addListener("cropLemon", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OM.is("cropCitron", aEvent.mStack) || OM.is("cropBuddhaHand", aEvent.mStack)) return;
			RM.crop_fruit(aEvent.mStack, FL.Juice_Lemon, 125, 7000, "Canned Lemons", 0, 0, 0, 4, 0);
			RM.boxunbox(ST.make(MD.FR, "crate", 1), ST.make(MD.FR, "cratedLemon", 1), ST.amount(9, aEvent.mStack));
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Flat.get(0), IL.Food_Lemon_Sliced.get(4));
		}});
		
		
		addListener("cropPersimmon", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Persimmon, 125, 8000, "Canned Persimmons", 0, 0, 0, 4, 0);
		}});
		addListener("cropManderin", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Orange, 150, 8000, "Canned Manderines", 0, 0, 0, 4, 0);
		}});
		addListener("cropTangerine", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Orange, 150, 8000, "Canned Tangerines", 0, 0, 0, 4, 0);
		}});
		addListener("cropSatsuma", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Orange, 125, 8000, "Canned Satsumas", 0, 0, 0, 4, 0);
		}});
		addListener("cropOsangeOrange", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Orange, 125, 8000, "Canned Osange Oranges", 0, 0, 0, 4, 0);
		}});
		addListener("cropOrange", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OM.is("cropManderin", aEvent.mStack) || OM.is("cropPersimmon", aEvent.mStack) || OM.is("cropSatsuma", aEvent.mStack) || OM.is("cropTangerine", aEvent.mStack)) return;
			RM.crop_fruit(aEvent.mStack, FL.Juice_Orange, 125, 8000, "Canned Oranges", 0, 0, 0, 4, 0);
		}});
		
		
		addListener("cropPomelo", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 100, 8000, "Canned Pomelos", 0, 0, 0, 4, 0);
		}});
		
		
		addListener("cropPear", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Pear, 125, 7000, "Canned Pears", 0, 0, 0, 8, 0);
		}});
		addListener("cropSandPear", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Pear, 125, 7000, "Canned Sand Pears", 0, 0, 0, 8, 0);
		}});
		
		
		addListener("cropNectarine", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Peach, 125, 7000, "Canned Nectarines", 0, 0, 0,12, 0);
		}});
		addListener("cropPeach", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OM.is("cropNectarine", aEvent.mStack)) return;
			RM.crop_fruit(aEvent.mStack, FL.Juice_Peach, 150, 7000, "Canned Peaches", 0, 0, 0,12, 0);
		}});
		
		
		addListener("cropCherryPlum", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Plum, 150, 6000, "Canned Cherry Plums", 0, 0, 4, 8, 0);
		}});
		addListener("cropPlum", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OM.is("cropCherryPlum", aEvent.mStack)) return;
			RM.crop_fruit(aEvent.mStack, FL.Juice_Plum, 150, 6000, "Canned Plums", 0, 0, 4, 8, 0);
			RM.boxunbox(ST.make(MD.FR, "crate", 1), ST.make(MD.FR, "cratedPlum", 1), ST.amount(9, aEvent.mStack));
		}});
		
		
		addListener("cropMango", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Mango, 150, 7000, "Canned Mangos", 0, 0, 0, 8, 0);
		}});
		
		
		addListener("cropKumquat", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 100, 7000, "Canned Kumquats", 0, 0, 0, 8, 0);
		}});
		
		
		addListener("cropAvocado", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 100, 7000, "Canned Avocados", 0, 0, 0, 8, 0);
		}});
		
		
		addListener("cropFig", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Fig, 100, 9000, "Canned Figs", 0, 0, 0, 8, 0);
		}});
		
		
		addListener("cropPomegranate", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Pomegranate, 100, 9000, "Canned Pomegranates", 0, 0, 0, 8, 0);
			RM.Drying.addRecipe1(T, 16, 100, aEvent.mStack, NF, FL.DistW.make(50), IL.Food_Pomeraisins.get(1));
			RM.add_smelting(aEvent.mStack, IL.Food_Pomeraisins.get(1), F, T, F);
		}});
		
		
		addListener("cropStarfruit", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Starfruit, 100, 7000, "Canned Starfruits", 0, 0, 0, 8, 0);
		}});
		
		
		addListener("cropApricot", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Apricot, 150, 7000, "Canned Apricots", 0, 0, 0,12, 0);
		}});
		
		
		addListener("cropPapaya", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Papaya, 400, 7000, "Canned Papayas", 0, 0, 0, 8, 0);
			RM.boxunbox(ST.make(MD.FR, "crate", 1), ST.make(MD.FR, "cratedPapaya", 1), ST.amount(9, aEvent.mStack));
		}});
		
		
		addListener("cropGrapefruit", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Grapefruit, 400, 7000, "Canned Grapefruits", 0, 0, 0, 8, 0);
		}});
		
		
		if (MD.HaC.mLoaded) RM.Drying.addRecipe1(T, 16, 100, ST.make(MD.HaC, "grapeItem", 1), NF, FL.DistW.make(50), ST.make(MD.HaC, "raisinsItem", 1));
		
		addListener("cropGrapeRed", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Grape_Red, 125, 8000, "Canned Grapes", 0, 0, 0,12, 0);
			RM.Drying.addRecipe1(T, 16, 100, aEvent.mStack, NF, FL.DistW.make(50), IL.Food_Raisins_Red.get(1));
			if (!OM.is("foodRaisins", RM.get_smelting(aEvent.mStack, F, NI))) RM.add_smelting(aEvent.mStack, IL.Food_Raisins_Red.get(1), F, T, F);
		}});
		addListener("cropGrapePurple", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Grape_Purple, 125, 8000, "Canned Grapes", 0, 0, 0,12, 0);
			RM.Drying.addRecipe1(T, 16, 100, aEvent.mStack, NF, FL.DistW.make(50), IL.Food_Raisins_Purple.get(1));
			if (!OM.is("foodRaisins", RM.get_smelting(aEvent.mStack, F, NI))) RM.add_smelting(aEvent.mStack, IL.Food_Raisins_Purple.get(1), F, T, F);
		}});
		addListener("cropGrapeWhite", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Grape_White, 125, 8000, "Canned Grapes", 0, 0, 0,12, 0);
			RM.Drying.addRecipe1(T, 16, 100, aEvent.mStack, NF, FL.DistW.make(50), IL.Food_Raisins_White.get(1));
			if (!OM.is("foodRaisins", RM.get_smelting(aEvent.mStack, F, NI))) RM.add_smelting(aEvent.mStack, IL.Food_Raisins_White.get(1), F, T, F);
		}});
		addListener("cropGrapeGreen", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Grape_Green, 125, 8000, "Canned Grapes", 0, 0, 0,12, 0);
			RM.Drying.addRecipe1(T, 16, 100, aEvent.mStack, NF, FL.DistW.make(50), IL.Food_Raisins_Green.get(1));
			if (!OM.is("foodRaisins", RM.get_smelting(aEvent.mStack, F, NI))) RM.add_smelting(aEvent.mStack, IL.Food_Raisins_Green.get(1), F, T, F);
		}});
		addListener("cropGrape", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OM.is("cropGrapeWhite", aEvent.mStack) || OM.is("cropGrapeGreen", aEvent.mStack) || OM.is("cropGrapeRed", aEvent.mStack) || OM.is("cropGrapePurple", aEvent.mStack)) return;
			RM.crop_fruit(aEvent.mStack, FL.Juice_Grape_Green, 125, 8000, "Canned Grapes", 0, 0, 0,12, 0);
			if (!OM.is("foodRaisins", RM.get_smelting(aEvent.mStack, F, NI))) RM.add_smelting(aEvent.mStack, IL.Food_Raisins_Green.get(1), F, T, F);
		}});
		
		
		addListener("cropDate", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Date, 125, 7000, "Canned Dates", 0, 0, 0,12, 0);
			RM.boxunbox(ST.make(MD.FR, "crate", 1), ST.make(MD.FR, "cratedDates", 1), ST.amount(9, aEvent.mStack));
		}});
		
		
		addListener("cropBanana", "cropRedBanana", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Banana, 100, 8000, "Canned Bananas", 0, 0, 0, 8, 0);
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Flat.get(0), IL.Food_Banana_Sliced.get(4));
		}});
		addListener("cropPlantain", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Banana, 100, 8000, "Canned Plantains", 0, 0, 0, 8, 0);
		}});
		
		
		addListener("cropAnanas", "cropPineapple", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice_Ananas, 200, 9000, "Canned Ananas", 0, 0, 0, 8, 0);
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Flat.get(0), IL.Food_Ananas_Sliced.get(4));
		}});
		
		
		addListener("cropBeet", "cropBeetroot", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_veggie(aEvent.mStack, FL.Juice_Beet, 200, 7000, "Canned Beets", 0, 0, 0, 8, 0);
		}});
		addListener("cropCarrot", "cropWildcarrots", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_veggie(aEvent.mStack, FL.Juice_Carrot, 100, 7000, "Canned Carrots", 0, 0, 0, 8, 0);
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Flat.get(0), IL.Food_Carrot_Sliced.get(4));
			RM.Boxinator.addRecipe2(T, 16, 16, aEvent.mStack, IL.Pill_Empty.get(1), IL.Pill_Red.get(1));
		}});
		addListener("cropPumpkin", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_veggie(aEvent.mStack, FL.Juice_Pumpkin, 1000, 9000, "Canned Pumpkin", 0, 0, 0,12, 0);
		}});
		
		
		addListener("cropPotato", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop(aEvent.mStack, FL.Juice_Potato, 100, OP.dustSmall.mat(MT.Potato, 4), 8000, "Canned Potatoes", IL.CANS_VEGGIE, 0, 0, 0, 4, 0);
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Flat.get(0), IL.Food_PotatoChips_Raw.get(1));
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Grid.get(0), IL.Food_Fries_Raw.get(1));
			for (OreDictMaterial tMat : ANY.Wood.mToThis) {ItemStack tStick = OP.stick.mat(tMat, 1); if (ST.valid(tStick)) {
			RM.Boxinator.addRecipe2(T, 16, 16, aEvent.mStack, OP.stick.mat(tMat, 1), IL.Food_Potato_On_Stick.get(1));
			}}
		}});
		
		
		addListener("cropTomato", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_veggie(aEvent.mStack, FL.Juice_Tomato, 100, 5000, "Canned Tomatoes", 0, 0, 0, 4, 0);
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Flat.get(0), IL.Food_Tomato_Sliced.get(4));
			if (!IL.Food_MTomato.equal(aEvent.mStack))
			RM.Bath.addRecipe1(T, 0, 16, aEvent.mStack, FL.Sap_Rainbow.make(100), NF, IL.Food_MTomato.get(1));
		}});
		
		
		addListener("cropCucumber", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_veggie(aEvent.mStack, FL.Juice_Cucumber, 150, 5000, "Canned Cucumbers", 0, 0, 0, 4, 0);
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Flat.get(0), IL.Food_Cucumber_Sliced.get(4));
			for (String tFluid : FluidsGT.VINEGAR) if (FL.exists(tFluid))
			RM.Bath.addRecipe1(T, 0, 16, aEvent.mStack, FL.make(tFluid, 50), NF, IL.Food_Pickle.get(1));
		}});
		addListener("cropPickle", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_veggie(aEvent.mStack, FL.Juice_Cucumber, 150, 5000, "Canned Pickles", 4, 0, 0, 4, 0);
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Flat.get(0), IL.Food_Pickle_Sliced.get(4));
		}});
		
		
		addListener("cropOnion", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_veggie(aEvent.mStack, FL.Juice_Onion, 100, 7000, "Canned Onions", 0, 0, 0, 4, 0);
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Flat.get(0), IL.Food_Onion_Sliced.get(4));
		}});
		
		
		addListener("cropSoybean", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_veggie(aEvent.mStack, FL.MilkSoy, 100, 5000, "Canned Soybeans", 0, 0, 0, 4, 0);
		}});
		
		
		addListener("cropChilipepper", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop(aEvent.mStack, FL.Sauce_Chili, 100, OP.dustSmall.mat(MT.Chili, 4), 8000, "Canned Chilipeppers", IL.CANS_VEGGIE, 0, 0, 4, 4, 0);
			if (!ST.isGT(aEvent.mStack) && ST.food(aEvent.mStack) > 0 && !Sandwiches.INGREDIENTS.containsKey(aEvent.mStack, F)) Sandwiches.INGREDIENTS.put(aEvent.mStack, (byte)20);
		}});
		addListener("cropCinnamon", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop(aEvent.mStack, null, 0, OP.dustSmall.mat(MT.Cinnamon, 4), 9000, null, null, 0, 0, 0, 4, 0);
		}});
		addListener("cropCurryleaf", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop(aEvent.mStack, null, 0, OP.dustSmall.mat(MT.Curry, 4), 9000, null, null, 0, 0, 4, 0, 0);
		}});
		addListener("cropPeppercorn", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop(aEvent.mStack, null, 0, OP.dustSmall.mat(MT.PepperBlack, 4), 9000, null, null, 0, 0, 4, 0, 0);
		}});
		addListener("cropClove", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Oil_Seed, 150, 7000, "Canned Cloves", 0, 0, 0, 8, 0);
		}});
		addListener("cropAllspice", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_fruit(aEvent.mStack, FL.Juice, 100, 7000, "Canned Allspice", 0, 0, 0, 8, 0);
		}});
		addListener("cropSpiceleaf", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Boxinator.addRecipe2(T, 16, 16, aEvent.mStack, IL.Pill_Empty.get(1), IL.Pill_Blue.get(1));
		}});
		addListener("cropTruffle", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.crop_veggie(aEvent.mStack, FL.Oil_Seed, 100, 5000, "Canned Truffles", 0, 0, 0, 4, 0);
		}});
		
		
		}};
		
		
		crop.addListener(new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			OreDictItemData tData = OM.data(aEvent.mStack);
			if (tData == null || !tData.hasValidPrefixMaterialData()) {
			RM.Shredder     .addRecipe1(T, 16, 16, 9000, ST.amount(1, aEvent.mStack), IL.Remains_Plant.get(1));
			RM.Mortar       .addRecipe1(T, 16, 16, 4500, aEvent.mStack, IL.Remains_Plant.get(1));
			}
		}});
		flower.addListener(new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			OreDictItemData tData = OM.data(aEvent.mStack);
			if (tData == null || !tData.hasValidPrefixMaterialData()) {
			RM.Shredder     .addRecipe1(T, 16, 16, 4000, ST.amount(1, aEvent.mStack), IL.Remains_Plant.get(1));
			RM.Mortar       .addRecipe1(T, 16, 16, 2000, aEvent.mStack, IL.Remains_Plant.get(1));
			}
		}});
		treeLeaves.addListener(new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Shredder     .addRecipe1(T, 16, 16, 6000, ST.amount(1, aEvent.mStack), IL.Remains_Plant.get(1));
			RM.Mortar       .addRecipe1(T, 16, 16, 3000, aEvent.mStack, IL.Remains_Plant.get(1));
		}});
		treeSapling.addListener(new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Shredder     .addRecipe1(T, 16, 16, 6000, ST.amount(1, aEvent.mStack), IL.Remains_Plant.get(2));
			RM.Mortar       .addRecipe1(T, 16, 16, 3000, aEvent.mStack, IL.Remains_Plant.get(2));
		}});
	}
}
