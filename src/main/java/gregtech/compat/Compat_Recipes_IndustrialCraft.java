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
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.config.ConfigCategories;
import gregapi.data.*;
import gregapi.old.GT_BaseCrop;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import ic2.api.crops.Crops;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.FluidStack;

import static gregapi.data.CS.*;

public class Compat_Recipes_IndustrialCraft extends CompatMods {
	public Compat_Recipes_IndustrialCraft(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@SuppressWarnings("deprecation")
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {if (MD.IC2C.mLoaded) OUT.println("GT_Mod: Doing IC2-Classic Things."); else OUT.println("GT_Mod: Doing IC2-Exp Things.");
		MultiTileEntityRegistry aRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
		
		String tName = "";
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "generator"       ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "TDC", "BMR", "FwW", 'M', IL.IC2_Machine    , 'C', OD_CIRCUITS[1], 'W', OP.cableGt01.dat(ANY.Cu), 'R', "gt:re-battery1", 'T', aRegistry.getItem(1522), 'B', aRegistry.getItem(1204), 'F', aRegistry.getItem(1104), 'D', aRegistry.getItem(10111));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "electrolyzer"    ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "WwW", "W W", "CMC", 'M', IL.IC2_Machine    , 'C', OD_CIRCUITS[2], 'W', OP.wireGt01.dat(MT.Pt));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "electroFurnace"  ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "WWW", "WwW", "CMC", 'M', IL.IC2_Machine    , 'C', OD_CIRCUITS[1], 'W', OP.wireGt01.dat(MT.AnnealedCopper));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "inductionFurnace"), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "WWW", "WwW", "CMC", 'M', IL.IC2_Machine_Adv, 'C', OD_CIRCUITS[3], 'W', OP.wireGt04.dat(MT.AnnealedCopper));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "extractor"       ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, " w ", "PMP", "WCW", 'M', IL.IC2_Machine    , 'C', OD_CIRCUITS[1], 'W', OP.cableGt01.dat(ANY.Cu), 'P', IL.PUMPS[2]);}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "macerator"       ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "DwD", "GMG", "CPC", 'M', IL.IC2_Machine    , 'C', OD_CIRCUITS[2], 'W', OP.cableGt01.dat(ANY.Cu), 'P', IL.MOTORS[2], 'D', OP.gem.dat(ANY.Diamond), 'G', OP.gearGt.dat(ANY.Steel));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "compressor"      ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, " w ", "PMP", "WCW", 'M', IL.IC2_Machine    , 'C', OD_CIRCUITS[1], 'W', OP.cableGt01.dat(ANY.Cu), 'P', IL.PISTONS[2]);}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "recycler"        ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "GwG", "PMP", "WCW", 'M', IL.IC2_Machine    , 'C', OD_CIRCUITS[2], 'W', OP.cableGt01.dat(ANY.Cu), 'P', IL.PISTONS[3], 'G', OP.dust.dat(ANY.Glowstone));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "massFabricator"  ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "CXC", "CMC"       , 'M', IL.IC2_Machine_Adv, 'C', OD_CIRCUITS[6], 'X', aRegistry.getItem(20413));}
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "lvTransformer"   ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "MwM", " X ", "M M", 'M', OD.plankAnyWood   , 'C', OD_CIRCUITS[1], 'X', aRegistry.getItem(10041));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "mvTransformer"   ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, " w ", " X ", " M ", 'M', IL.IC2_Machine    , 'C', OD_CIRCUITS[2], 'X', aRegistry.getItem(10042));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "hvTransformer"   ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, " w ", " X ", " M ", 'M', IL.IC2_Machine_Adv, 'C', OD_CIRCUITS[3], 'X', aRegistry.getItem(10043));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "evTransformer"   ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, " w ", " X ", " M ", 'M', IL.IC2_Machine_Adv, 'C', OD_CIRCUITS[4], 'X', aRegistry.getItem(10044));}
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "batBox"          ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "MBM", "BXB", "MBM", 'M', OD.plankAnyWood   , 'X', aRegistry.getItem(10081), 'B', "gt:re-battery1");}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "mfeUnit"         ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "BCB", "BXB", "BMB", 'M', IL.IC2_Machine    , 'X', aRegistry.getItem(10083), 'B', "gt:re-battery3", 'C', OD_CIRCUITS[3]);}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "mfsUnit"         ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "BCB", "BXB", "BMB", 'M', IL.IC2_Machine_Adv, 'X', aRegistry.getItem(10043), 'B', ST.mkic("mfeUnit", 1), 'C', OD_CIRCUITS[4]);}
		
		CR.remove(ST.make(Items.lava_bucket , 1, 0), IL.Cell_Empty.get(1));
		CR.remove(ST.make(Items.water_bucket, 1, 0), IL.Cell_Empty.get(1));
		
		if (COMPAT_IC2 != null) COMPAT_IC2.addToExplosionWhitelist(Blocks.bedrock);
		
		for (OreDictMaterial tMat : ANY.Iron.mToThis) {
			ItemStack tStack = OP.casingMachine.mat(tMat, 1);
			if (ST.valid(tStack)) RM.generify(tStack, IL.IC2_Machine.get(1));
		}
		
		CR.shapeless(IL.IC2_Machine.get(1), CR.DEF, new Object[] {OP.casingMachine.dat(ANY.Iron)});
		
		CR.shapeless(ST.mkic("barrel", 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, new Object[] {aRegistry.getItem(32714)});
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "ic2_mininglaser", T))
		CR.shaped(ST.mkic("miningLaser", 1), CR.DEF_REV_NCC | CR.DEL_OTHER_SHAPED_RECIPES, "LOE", "PPC", "dTA", 'L', DYE_OREDICTS_LENS[DYE_INDEX_Red], 'O', IL.Comp_Laser_Gas_CO2, 'C', OD_CIRCUITS[3], 'T', OP.screw.dat(MT.Ti), 'P', OP.plate.dat(MT.Ti), 'E', IL.IC2_EnergyCrystal.wild(1), 'A', IL.IC2_Advanced_Alloy);
		CR.shaped(ST.mkic("ironFence", 6), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "XXX", "XXX", " w ", 'X', OP.stick.dat(ANY.Fe));
		
		try {
			UT.removeSimpleIC2MachineRecipe(NI, ic2.api.recipe.Recipes.compressor.getRecipes(), IL.Cell_Air.get(1));
			UT.removeSimpleIC2MachineRecipe(ST.make(Items.snowball   , 1, W), ic2.api.recipe.Recipes.compressor.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(ST.make(Blocks.snow      , 1, W), ic2.api.recipe.Recipes.compressor.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(ST.make(Blocks.ice       , 1, W), ic2.api.recipe.Recipes.compressor.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(ST.make(Blocks.packed_ice, 1, W), ic2.api.recipe.Recipes.compressor.getRecipes(), NI);
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
		
		RM.ic2_compressor(ST.make(Items.snowball, 4, 0), ST.make(Blocks.snow, 1, 0));
		RM.ic2_compressor(ST.make(Blocks.snow, 1, 0), ST.make(Blocks.ice, 1, 0));
		RM.ic2_compressor(ST.make(Blocks.ice, 2, 0), ST.make(Blocks.packed_ice, 1, 0));
		
		//====================================================================================================
		if (MD.IC2C.mLoaded) { // IC2 Classic
		//====================================================================================================
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "windMill"  ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "PCP", "WIW", "PMP", 'M', IL.IC2_Machine, 'C', OD_CIRCUITS[1], 'W', OP.wireGt01.dat(ANY.Cu), 'I', OP.stick.dat(MT.IronMagnetic), 'P', OP.plateCurved.dat(MT.Magnalium));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "waterMill" ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "PCP", "WIW", "PMP", 'M', IL.IC2_Machine, 'C', OD_CIRCUITS[1], 'W', OP.wireGt01.dat(ANY.Cu), 'I', OP.stick.dat(MT.IronMagnetic), 'P', OP.plateCurved.dat(MT.Al));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "solarPanel"), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "wGd", "WSW", "CMC", 'M', IL.IC2_Machine, 'C', OD_CIRCUITS[1], 'W', OP.cableGt01.dat(ANY.Cu), 'S', OP.plateGem.dat(ANY.Si), 'G', OP.plateGem.dat(MT.Glass));}
		
		try {
			UT.removeSimpleIC2MachineRecipe(NI, ic2.api.recipe.Recipes.compressor.getRecipes(), ST.make(MD.IC2, "item.itemPartDCP", 1, 0));
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
		
		RM.ic2_compressor(OP.ingot          .mat(MT.Cu, 9), OP.plateDense.mat(MT.Cu, 1));
		RM.ic2_compressor(OP.plate          .mat(MT.Cu, 9), OP.plateDense.mat(MT.Cu, 1));
		RM.ic2_compressor(OP.plateTriple    .mat(MT.Cu, 3), OP.plateDense.mat(MT.Cu, 1));
		RM.ic2_compressor(OP.blockIngot     .mat(MT.Cu, 1), OP.plateDense.mat(MT.Cu, 1));
		RM.ic2_compressor(OP.blockPlate     .mat(MT.Cu, 1), OP.plateDense.mat(MT.Cu, 1));
		RM.ic2_compressor(OP.blockSolid     .mat(MT.Cu, 1), OP.plateDense.mat(MT.Cu, 1));
		
		RM.ic2_compressor(OP.ingot          .mat(MT.AnnealedCopper, 9), OP.plateDense.mat(MT.Cu, 1));
		RM.ic2_compressor(OP.plate          .mat(MT.AnnealedCopper, 9), OP.plateDense.mat(MT.Cu, 1));
		RM.ic2_compressor(OP.plateTriple    .mat(MT.AnnealedCopper, 3), OP.plateDense.mat(MT.Cu, 1));
		RM.ic2_compressor(OP.blockIngot     .mat(MT.AnnealedCopper, 1), OP.plateDense.mat(MT.Cu, 1));
		RM.ic2_compressor(OP.blockPlate     .mat(MT.AnnealedCopper, 1), OP.plateDense.mat(MT.Cu, 1));
		RM.ic2_compressor(OP.blockSolid     .mat(MT.AnnealedCopper, 1), OP.plateDense.mat(MT.Cu, 1));
		
		RM.compact(ST.make(MD.IC2, "blockMachine2", 3, 2), ST.make(MD.IC2, "blockMachine2", 1, 15));
		
		CR.shaped(IL.IC2_Machine.get(1), CR.DEF, "III", "I I", "III", 'I', OP.ingot.dat(MT.TECH.RefinedIron));
		CR.remove(IL.IC2_Machine.get(1));
		CR.shapeless(OP.ingot.mat(MT.Fe, 8), new Object[] {IL.IC2_Machine});
		
		RM.Massfab      .addRecipe1(T, 1, 32768, IL.IC2_Scrap                        .get(36), ST.make(MD.IC2, "item.itemMatter", 1, 0));
		RM.Massfab      .addRecipe1(T, 1, 32768, IL.IC2_Scrapbox                     .get( 4), ST.make(MD.IC2, "item.itemMatter", 1, 0));
		RM.Massfab      .addRecipe1(T, 1, 32768, ST.make(MD.IC2, "item.itemScrapMetal", 2, 0), ST.make(MD.IC2, "item.itemMatter", 1, 0));
		
		RM.Shredder     .addRecipe1(T, 16,   16, IL.IC2_Plantball.get(1)        , ST.make(Blocks.dirt, 1, 0));
		RM.Shredder     .addRecipe1(T, 16,   16, 300, ST.mkic("weed", 1)        , IL.IC2_Plantball.get(1));
		RM.Shredder     .addRecipe1(T, 16,   16, 600, ST.make(Blocks.vine, 1, W), IL.IC2_Plantball.get(1));
		
		RM.biomass(IL.IC2_Plantball.get(1), 32);
		
		RM.Squeezer     .addRecipe1(T, 16,   16, ST.make(MD.IC2, "item.itemMatter", 1, 0), NF, FL.UUM.make(1), ZL_IS);
		RM.Coagulator   .addRecipe0(T,  0,   16, FL.UUM.make(1), NF, ST.make(MD.IC2, "item.itemMatter", 1, 0));
		
		RM.Generifier   .addRecipe1(T, F, F, F, F, 0, 1, ST.make(MD.IC2, "item.itemMatter", 1, 0), NF, FL.UUM.make(1), ZL_IS);
		RM.Generifier   .addRecipe0(T, F, F, F, F, 0, 1, FL.UUM.make(1), NF, ST.make(MD.IC2, "item.itemMatter", 1, 0));
		
		RM.Mixer        .addRecipeX(T, 16,   48, ST.array(IL.IC2_Scrap.get(2), IL.IC2_Fertilizer.get(1), IL.Dye_Bonemeal.get(1)), FL.Potion_Poison_2.make( 250), NF, ST.make(MD.IC2, "item.itemSpecialFertilizer",  3, 0));
		RM.Mixer        .addRecipeX(T, 16,  192, ST.array(IL.IC2_Scrap.get(8), IL.IC2_Fertilizer.get(4), OM.dust(MT.Ca)        ), FL.Potion_Poison_2.make(1000), NF, ST.make(MD.IC2, "item.itemSpecialFertilizer", 12, 0));
		for (OreDictMaterial tMat : ANY.Clay.mToThis) {
		for (FluidStack tWater : FL.waters(1000))
		RM.Mixer        .addRecipeX(T, 16,   48, ST.array(OM.dust(MT.Coal), OM.dust(tMat), OM.dust(MT.Redstone)), tWater, NF, ST.make(MD.IC2, "constructionFoam",  3, 0));
		}
		
		RM.Compressor   .addRecipe1(T, 16,   32, ST.make(MD.IC2, "item.itemScrapMetal", 8, 0), ST.make(MD.IC2, "item.scrapMetalChunk", 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   32, IL.IC2_Plantball.get(1), ST.make(MD.IC2, "item.itemFuelPlantCmpr", 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.HydratedCoal), ST.make(MD.IC2, "item.itemFuelCoalCmpr", 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, ST.make(MD.IC2, "item.itemOreUran", 1, 0), OM.ingot(MT.U_238));
		RM.Compressor   .addRecipe1(T, 16,   16, ST.make(MD.IC2, "constructionFoam", 1, 0), ST.make(MD.IC2, "item.itemPartPellet", 1, 0));
		
		ItemStack tCoalDust = OM.dust(MT.Coal);
		for (ItemStack tStack : ST.array(
			ST.make(MD.IC2, "item.itemCellUranEmpty", 1, 0),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 100),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 101),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 102),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 103),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 104),
			ST.make(MD.IC2, "item.itemCellUranEnriched", 1, 0),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 200),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 201),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 202),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 203),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 204)
			)) if (ST.valid(tStack)) {
			ItemStack rStack = CR.get(tStack, tCoalDust);
			if (ST.valid(rStack)) RM.Canner.addRecipe2(T, 16, 16, tStack, tCoalDust, rStack);
		}
		
		boolean temp = T;
		
		for (ItemStack tStack : ST.array(
			OM.ingot(MT.U_238),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 0),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 1),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 2),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 3),
			ST.make(MD.IC2, "item.itemEnrichedUranStuff", 1, 4),
			ST.make(MD.IC2, "item.itemFuelPlantCmpr", 1, 0),
			ST.make(MD.IC2, "item.itemFuelCoalCmpr", 1, 0)
			)) if (ST.valid(tStack)) {
			ItemStack rStack = CR.get(tStack, IL.Cell_Empty.get(1));
			if (ST.valid(rStack)) {
				RM.Canner.addRecipe2(T, 16, 16, tStack, IL.Cell_Empty.get(1), rStack);
				if (temp) {
					temp = F;
					RM.Canner.addRecipe2(T, 16, 32, OM.ingot(MT.U_235), IL.Cell_Empty.get(2), ST.mul_(2, rStack));
				}
			}
		}
		
		CR.delate(MD.IC2, "item.itemRawObsidianBlade");
		CR.shaped(ST.make(MD.IC2, "item.itemRawObsidianBlade", 1, 0), CR.DEF_REV, "OOO", "OFO", "OOO", 'O', OP.blockDust.dat(MT.Obsidian), 'F', OD.itemFlint);
		
		//====================================================================================================
		} else { // IC2 Experimental
		//====================================================================================================
		
		for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST           ).getItems(RNGSUS)) if (IL.IC2_Iridium_Shard.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Scrapbox.get(1), F, T); else if (IL.IC2_Iridium_Ore.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Iridium_Shard.get(1), F, T);
		for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST             ).getItems(RNGSUS)) if (IL.IC2_Iridium_Shard.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Scrapbox.get(1), F, T); else if (IL.IC2_Iridium_Ore.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Iridium_Shard.get(1), F, T);
		for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH      ).getItems(RNGSUS)) if (IL.IC2_Iridium_Shard.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Scrapbox.get(1), F, T); else if (IL.IC2_Iridium_Ore.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Iridium_Shard.get(1), F, T);
		for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING     ).getItems(RNGSUS)) if (IL.IC2_Iridium_Shard.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Scrapbox.get(1), F, T); else if (IL.IC2_Iridium_Ore.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Iridium_Shard.get(1), F, T);
		for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY      ).getItems(RNGSUS)) if (IL.IC2_Iridium_Shard.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Scrapbox.get(1), F, T); else if (IL.IC2_Iridium_Ore.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Iridium_Shard.get(1), F, T);
		for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR     ).getItems(RNGSUS)) if (IL.IC2_Iridium_Shard.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Scrapbox.get(1), F, T); else if (IL.IC2_Iridium_Ore.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Iridium_Shard.get(1), F, T);
		for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).getItems(RNGSUS)) if (IL.IC2_Iridium_Shard.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Scrapbox.get(1), F, T); else if (IL.IC2_Iridium_Ore.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Iridium_Shard.get(1), F, T);
		for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ).getItems(RNGSUS)) if (IL.IC2_Iridium_Shard.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Scrapbox.get(1), F, T); else if (IL.IC2_Iridium_Ore.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Iridium_Shard.get(1), F, T);
		for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST    ).getItems(RNGSUS)) if (IL.IC2_Iridium_Shard.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Scrapbox.get(1), F, T); else if (IL.IC2_Iridium_Ore.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Iridium_Shard.get(1), F, T);
		for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR      ).getItems(RNGSUS)) if (IL.IC2_Iridium_Shard.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Scrapbox.get(1), F, T); else if (IL.IC2_Iridium_Ore.equal(tContent.theItemId, F, T)) ST.set(tContent.theItemId, IL.IC2_Iridium_Shard.get(1), F, T);
		
		try {
			UT.removeSimpleIC2MachineRecipe(ST.make(Items.gunpowder, 1, 0), ic2.api.recipe.Recipes.extractor.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(NI, ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), IL.Cell_Empty.get(1));
			UT.removeSimpleIC2MachineRecipe(NI, ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), IL.IC2_Fuel_Rod_Empty.get(1));
			UT.removeSimpleIC2MachineRecipe(NI, ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), IL.IC2_Food_Can_Empty.get(1));
			UT.removeSimpleIC2MachineRecipe(NI, ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), IL.IC2_ShaftIron.get(1));
			UT.removeSimpleIC2MachineRecipe(NI, ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), IL.IC2_ShaftSteel.get(1));
			UT.removeSimpleIC2MachineRecipe(NI, ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), ST.mkic("ironFence", 1));
			UT.removeSimpleIC2MachineRecipe(OP.ingot.mat(MT.W, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.ingot.mat(MT.Au, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.ingot.mat(MT.Fe, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.ingot.mat(MT.Cu, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.ingot.mat(MT.Pb, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.ingot.mat(MT.Sn, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.ingot.mat(MT.Steel, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.ingot.mat(MT.Bronze, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.plate.mat(MT.Au, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.plate.mat(MT.Fe, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.plate.mat(MT.Cu, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.plate.mat(MT.Pb, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.plate.mat(MT.Sn, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.plate.mat(MT.Steel, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(OP.plate.mat(MT.Bronze, 1), ic2.api.recipe.Recipes.metalformerExtruding.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(ST.make(Blocks.wool, 1, W), ic2.api.recipe.Recipes.extractor.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(IL.IC2_Energium_Dust.get(1), ic2.api.recipe.Recipes.compressor.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(IL.IC2_Fuel_Rod_Empty.get(1), ic2.api.recipe.Recipes.macerator.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(ST.mkic("reactorDepletedUraniumSimple", 1), ic2.api.recipe.Recipes.centrifuge.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(ST.mkic("reactorDepletedUraniumDual", 1)  , ic2.api.recipe.Recipes.centrifuge.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(ST.mkic("reactorDepletedUraniumQuad", 1)  , ic2.api.recipe.Recipes.centrifuge.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(ST.mkic("reactorDepletedMOXSimple", 1)    , ic2.api.recipe.Recipes.centrifuge.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(ST.mkic("reactorDepletedMOXDual", 1)      , ic2.api.recipe.Recipes.centrifuge.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(ST.mkic("reactorDepletedMOXQuad", 1)      , ic2.api.recipe.Recipes.centrifuge.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(ST.mkic("RTGPellets", 1)                  , ic2.api.recipe.Recipes.centrifuge.getRecipes(), NI);
			UT.removeSimpleIC2MachineRecipe(NI, ic2.api.recipe.Recipes.centrifuge.getRecipes(), ST.mkic("Uran238", 1));
			UT.removeSimpleIC2MachineRecipe(NI, ic2.api.recipe.Recipes.centrifuge.getRecipes(), ST.mkic("Uran235", 1));
			UT.removeSimpleIC2MachineRecipe(NI, ic2.api.recipe.Recipes.centrifuge.getRecipes(), ST.mkic("smallUran235", 1));
			UT.removeSimpleIC2MachineRecipe(NI, ic2.api.recipe.Recipes.centrifuge.getRecipes(), ST.mkic("smallPlutonium", 1));
			UT.removeSimpleIC2MachineRecipe(NI, ic2.api.recipe.Recipes.centrifuge.getRecipes(), ST.mkic("Plutonium", 1));
			
			Object tCrop;
			UT.Reflection.getField(tCrop = Crops.instance.getCropList()[13], "mDrop").set(tCrop, OP.plantGtBlossom.mat(MT.Fe, 1));
			UT.Reflection.getField(tCrop = Crops.instance.getCropList()[14], "mDrop").set(tCrop, OP.plantGtBlossom.mat(MT.Au, 1));
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
		
		CR.delate(ST.mkic("MOXFuel", 1));
		CR.delate(ST.mkic("UranFuel", 1));
		CR.delate(ST.mkic("RTGPellets", 1));
		
		CR.shaped(IL.IC2_Iridium_Ore.get(1), CR.DEF_NCC, "XXX", "XXX", "XXX", 'X', IL.IC2_Iridium_Shard);
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "windMill"       ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "PwP", "IMI", "PCP", 'M', IL.IC2_Generator  , 'C', OD_CIRCUITS[1], 'P', OP.plateCurved.dat(MT.Magnalium), 'I', OP.plateDouble.dat(ANY.Steel));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "waterMill"      ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "P P", "wMh", "PCP", 'M', IL.IC2_Generator  , 'C', OD_CIRCUITS[1], 'P', OP.plateCurved.dat(MT.Al));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "solarPanel"     ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "CSC", "WMW"       , 'M', IL.IC2_Generator  , 'C', OD_CIRCUITS[2], 'W', OP.cableGt01.dat(ANY.Cu), 'S', OP.plateGem.dat(ANY.Si));}
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "metalformer"    ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "XYZ", "CMC"       , 'M', IL.IC2_Machine , 'C', OD_CIRCUITS[4], 'X', aRegistry.getItem(20122), 'Y', aRegistry.getItem(20201), 'Z', aRegistry.getItem(20152));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "orewashingplant"), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "CXC", "CMC"       , 'M', IL.IC2_Machine , 'C', OD_CIRCUITS[4], 'X', aRegistry.getItem(20292));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "blockcutter"    ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "CXC", "CMC"       , 'M', IL.IC2_Machine , 'C', OD_CIRCUITS[4], 'X', aRegistry.getItem(20062));}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "centrifuge"     ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "WWW", "WXW", "CMC", 'M', IL.IC2_Machine , 'C', OD_CIRCUITS[4], 'X', aRegistry.getItem(20082), 'W', OP.wireGt04.dat(ANY.Cu));}
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.harderrecipes, "ic2_" + (tName = "cesuUnit"       ), T)) {CR.shaped(ST.mkic(tName, 1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "MBM", "BXB", "MBM", 'M', OP.plate.dat(MT.Bronze), 'X', aRegistry.getItem(10082), 'B', "gt:re-battery2");}
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "ic2_" + (tName = "fermenter"    ), T)) CR.delate(ST.mkic(tName, 1));
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "ic2_" + (tName = "blastfurnace" ), T)) CR.delate(ST.mkic(tName, 1));
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "ic2_forgehammer"                 , T)) CR.delate(IL.IC2_ForgeHammer.wild(1));
		
		CR.delate(ST.make(MD.IC2, "blockreactorvessel"      , 1, 0));
		CR.delate(ST.make(MD.IC2, "blockReactorFluidPort"   , 1, 0));
		CR.delate(ST.make(MD.IC2, "blockGenerator"          , 1, 7));
		CR.delate(ST.make(MD.IC2, "blockHeatGenerator"      , 1, 1));
		CR.delate(ST.make(MD.IC2, "blockKineticGenerator"   , 1, 1));
		CR.delate(ST.make(MD.IC2, "blockMachine3"           , 1, 3));
		CR.delate(ST.make(MD.IC2, "itemArmorCFPack"         , 1, 0));
		CR.delate(ST.make(MD.IC2, "itemArmorJetpack"    , 1, 18001));
		CR.delate(ST.make(MD.IC2, "itemFoamSprayer"         , 1, 0));
		
		CR.shaped(ST.make(MD.IC2, "blockreactorvessel"      , 1, 0), CR.DEF_REV_NCC, "PSP", "SPS", "PSP", 'P', OP.plate.dat(MT.Pb), 'S', OP.stone);
		CR.shaped(ST.make(MD.IC2, "blockReactorFluidPort"   , 1, 0), CR.DEF_REV_NCC, "XXX", "XCX", "XXX", 'C', OP.cell.dat(MT.Empty), 'X', ST.make(MD.IC2, "blockreactorvessel", 1, 0));
		CR.shaped(ST.make(MD.IC2, "blockMachine3"           , 1, 3), CR.DEF_REV_NCC, "GGG", "G G", "CXC", 'C', OP.cell.dat(MT.Empty), 'X', IL.IC2_Machine, 'G', OP.plateGem.dat(MT.Glass));
		CR.shaped(ST.make(MD.IC2, "blockGenerator"          , 1, 7), CR.DEF_REV_NCC, "PCP", "CXC", "PCP", 'C', OP.cell.dat(MT.Empty), 'P', OP.casingSmall.dat(ANY.Iron), 'X', ST.make(MD.IC2, "blockGenerator", 1, 1));
		CR.shaped(ST.make(MD.IC2, "blockHeatGenerator"      , 1, 1), CR.DEF_REV_NCC, "PCP", "CXC", "PCP", 'C', OP.cell.dat(MT.Empty), 'P', OP.casingSmall.dat(ANY.Iron), 'X', ST.make(MD.IC2, "itemRecipePart", 1, 5));
		CR.shaped(ST.make(MD.IC2, "blockKineticGenerator"   , 1, 1), CR.DEF_REV_NCC, "PPP", "XSS", "CPP", 'C', OP.cell.dat(MT.Empty), 'P', OP.casingSmall.dat(ANY.Iron), 'X', ST.make(MD.IC2, "itemRecipePart", 1, 6), 'S', IL.IC2_ShaftIron);
		CR.shaped(ST.make(MD.IC2, "itemArmorCFPack"         , 1, 0), CR.DEF_REV_NCC, "PXP", "CPC", "CPC", 'C', OP.cell.dat(MT.Empty), 'P', OP.casingSmall.dat(ANY.Iron), 'X', OD_CIRCUITS[1]);
		CR.shaped(ST.make(MD.IC2, "itemArmorJetpack"    , 1, 18001), CR.DEF_REV_NCC, "PXP", "PCP", "R R", 'C', OP.cell.dat(MT.Empty), 'P', OP.casingSmall.dat(ANY.Iron), 'X', OD_CIRCUITS[1], 'R', OD.itemRedstone);
		CR.shaped(ST.make(MD.IC2, "itemFoamSprayer"         , 1, 0), CR.DEF_REV_NCC, "P  ", " P ", " CP", 'C', OP.cell.dat(MT.Empty), 'P', OP.casingSmall.dat(ANY.Iron));
		
		RM.Massfab      .addRecipe1(T, 1, 32768, IL.IC2_Scrap       .get(36), NF, FL.UUM.make(1), ZL_IS);
		RM.Massfab      .addRecipe1(T, 1, 32768, IL.IC2_Scrapbox    .get( 4), NF, FL.UUM.make(1), ZL_IS);
		
		
		for (OreDictMaterial tMat : ANY.Steel.mToThis)
		RM.RollFormer   .addRecipe1(T,256,   16, OP.blockSolid.mat(tMat, 1)                         , IL.IC2_ShaftSteel.get(1));
		for (OreDictMaterial tMat : ANY.Fe.mToThis) if (tMat != MT.Enori)
		RM.RollFormer   .addRecipe1(T,256,   16, OP.blockSolid.mat(tMat, 1)                         , IL.IC2_ShaftIron.get(1));
		
		for (FluidStack tWater : FL.waters(1000)) {
		RM.Mixer        .addRecipe1(T, 16,   16, ST.mkic("constructionFoamPowder", 1)               , tWater, FL.CFoam.make(1000), ZL_IS);
		}
		
		RM.Compressor   .addRecipe1(T, 16,   16, OM.dust(MT.U_238)                                  , ST.mkic("Uran238", 1));
		RM.Compressor   .addRecipe1(T, 16,   16, OM.dust(MT.U_235)                                  , ST.mkic("Uran235", 1));
		RM.Compressor   .addRecipe1(T, 16,   16, OM.dust(MT.Pu)                                     , ST.mkic("Plutonium", 1));
		RM.Compressor   .addRecipe1(T, 16,   16, OM.dust(MT.U_235, U9)                              , ST.mkic("smallUran235", 1));
		RM.Compressor   .addRecipe1(T, 16,   16, OM.dust(MT.Pu, U9)                                 , ST.mkic("smallPlutonium", 1));
		RM.Compressor   .addRecipe1(T, 16,   16, OM.dust(MT.Ir, U9)                                 , IL.IC2_Iridium_Shard.get(1));
		RM.Compressor   .addRecipe1(T, 16,   16, OM.dust(MT.Ir)                                     , IL.IC2_Iridium_Ore.get(1));
		RM.Compressor   .addRecipe1(T, 16,  256, OP.plateGem.mat(MT.Lapis, 9)                       , OP.plateDense.mat(MT.Lapis, 1));
		RM.Compressor   .addRecipe1(T, 16,  256, OP.plateGem.mat(MT.Lazurite, 9)                    , OP.plateDense.mat(MT.Lazurite, 1));
		RM.Compressor   .addRecipe1(T, 16,  256, OP.plateGem.mat(MT.Sodalite, 9)                    , OP.plateDense.mat(MT.Sodalite, 1));
		RM.Compressor   .addRecipe1(T, 16,  768, OP.plate.mat(MT.Obsidian, 9)                       , OP.plateDense.mat(MT.Obsidian, 1));
		
		RM.Shredder     .addRecipe1(T, 16,   16, IL.IC2_Biochaff.get(1)                             , ST.make(Blocks.dirt, 1, 0));
		RM.Shredder     .addRecipe1(T, 16,   16, IL.IC2_Plantball.get(1)                            , IL.IC2_Biochaff.get(1));
		RM.Shredder     .addRecipe1(T, 16,   16, 300, ST.mkic("weed", 1)                            , IL.IC2_Biochaff.get(1));
		RM.Shredder     .addRecipe1(T, 16,   16, 600, ST.make(Blocks.vine, 1, W)                    , IL.IC2_Biochaff.get(1));
		if (IL.HBM_Biomass.exists())
		RM.Shredder     .addRecipe1(T, 16,   16, IL.HBM_Biomass.get(1)                              , IL.IC2_Biochaff.get(1));
		if (IL.HBM_Biomass_Compressed.exists())
		RM.Shredder     .addRecipe1(T, 16,   16, IL.HBM_Biomass_Compressed.get(1)                   , IL.IC2_Biochaff.get(1));
		
		RM.biomass(IL.IC2_Biochaff.get(1), 32);
		
		RM.generify(IL.IC2_Fuel_Rod_Empty.get(1), IL.Reactor_Rod_Empty.get(1));
		RM.generify(IL.Reactor_Rod_Empty.get(1), IL.IC2_Fuel_Rod_Empty.get(1));
		
		RM.RollBender   .addRecipe1(T, 16,  128, OP.casingSmall.mat(MT.Zr, 2)                       , IL.IC2_Fuel_Rod_Empty.get(1));
		
		RM.Press        .addRecipe2(T, 64,  192, ST.mkic("Uran238",18)                              , ST.mkic("Uran235", 1)         , ST.mkic("UranFuel", 3));
		RM.Press        .addRecipe2(T, 64,   64, ST.mkic("Uran238", 6)                              , ST.mkic("smallUran235", 3)    , ST.mkic("UranFuel", 1));
		RM.Press        .addRecipe2(T, 64,   64, ST.mkic("Uran238", 6)                              , ST.mkic("Plutonium", 3)       , ST.mkic("MOXFuel", 1));
		RM.Press        .addRecipe2(T, 64,  192, OP.ingot.mat(MT.U_238,18)                          , OP.ingot.mat(MT.U_235, 1)     , ST.mkic("UranFuel", 3));
		RM.Press        .addRecipe2(T, 64,   64, OP.ingot.mat(MT.U_238, 6)                          , OP.nugget.mat(MT.U_235, 3)    , ST.mkic("UranFuel", 1));
		RM.Press        .addRecipe2(T, 64,   64, OP.ingot.mat(MT.U_238, 6)                          , OP.ingot.mat(MT.Pu, 3)        , ST.mkic("MOXFuel", 1));
		
		RM.Canner       .addRecipe2(T, 16,   16, ST.mkic("UranFuel", 1)                             , IL.IC2_Fuel_Rod_Empty.get(1)  , ST.mkic("reactorUraniumSimple", 1, 1));
		RM.Canner       .addRecipe2(T, 16,   16, ST.mkic("UranFuel", 1)                             , IL.Reactor_Rod_Empty.get(1)   , ST.mkic("reactorUraniumSimple", 1, 1));
		RM.Canner       .addRecipe2(T, 16,   16, ST.mkic("MOXFuel", 1)                              , IL.IC2_Fuel_Rod_Empty.get(1)  , ST.mkic("reactorMOXSimple", 1, 1));
		RM.Canner       .addRecipe2(T, 16,   16, ST.mkic("MOXFuel", 1)                              , IL.Reactor_Rod_Empty.get(1)   , ST.mkic("reactorMOXSimple", 1, 1));
		
		RM.Centrifuge   .addRecipe1(T, 512, 128, ST.mkic("reactorDepletedUraniumSimple", 1)         , OP.dust.mat(MT.Zr, 1), OP.crushedCentrifugedTiny.mat(MT.Pu,  1), OP.crushedCentrifuged.mat(MT.U_238,  3));
		RM.Centrifuge   .addRecipe1(T, 512, 256, ST.mkic("reactorDepletedUraniumDual", 1)           , OP.dust.mat(MT.Zr, 2), OP.crushedCentrifugedTiny.mat(MT.Pu,  2), OP.crushedCentrifuged.mat(MT.U_238,  6), OP.dust.mat(MT.Fe, 1));
		RM.Centrifuge   .addRecipe1(T, 512, 512, ST.mkic("reactorDepletedUraniumQuad", 1)           , OP.dust.mat(MT.Zr, 4), OP.crushedCentrifugedTiny.mat(MT.Pu,  4), OP.crushedCentrifuged.mat(MT.U_238, 12), OP.dust.mat(MT.Fe, 3), OP.dust.mat(MT.Cu, 2));
		RM.Centrifuge   .addRecipe1(T, 512, 128, ST.mkic("reactorDepletedMOXSimple", 1)             , OP.dust.mat(MT.Zr, 1), OP.crushedCentrifugedTiny.mat(MT.Pu, 21));
		RM.Centrifuge   .addRecipe1(T, 512, 256, ST.mkic("reactorDepletedMOXDual", 1)               , OP.dust.mat(MT.Zr, 2), OP.crushedCentrifugedTiny.mat(MT.Pu, 42), OP.dust.mat(MT.Fe, 1));
		RM.Centrifuge   .addRecipe1(T, 512, 512, ST.mkic("reactorDepletedMOXQuad", 1)               , OP.dust.mat(MT.Zr, 4), OP.crushedCentrifugedTiny.mat(MT.Pu, 64), OP.dust.mat(MT.Fe, 3), OP.dust.mat(MT.Cu, 2), OP.crushedCentrifugedTiny.mat(MT.Pu, 20));
		RM.Centrifuge   .addRecipe1(T, 512, 512, ST.mkic("RTGPellets", 1)                           , OP.dust.mat(MT.Fe,54), OP.dust.mat(MT.Pu, 3));
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener("plateDenseAnyIron", "plateDenseAnyIronSteel", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Press    .addRecipe2(T, 64,  128, ST.amount(6, aEvent.mStack), ST.mkic("smallPlutonium", 27) , ST.mkic("RTGPellets", 1));
			RM.Press    .addRecipe2(T, 64,  128, ST.amount(6, aEvent.mStack), ST.mkic("Plutonium", 3)       , ST.mkic("RTGPellets", 1));
			RM.Press    .addRecipe2(T, 64,  128, ST.amount(6, aEvent.mStack), OP.nugget.mat(MT.Pu, 27)      , ST.mkic("RTGPellets", 1));
			RM.Press    .addRecipe2(T, 64,  128, ST.amount(6, aEvent.mStack), OP.chunkGt.mat(MT.Pu, 12)     , ST.mkic("RTGPellets", 1));
			RM.Press    .addRecipe2(T, 64,  128, ST.amount(6, aEvent.mStack), OP.ingot.mat(MT.Pu, 3)        , ST.mkic("RTGPellets", 1));
			RM.Press    .addRecipe2(T, 64,  128, ST.amount(6, aEvent.mStack), OP.dustTiny.mat(MT.Pu, 27)    , ST.mkic("RTGPellets", 1));
			RM.Press    .addRecipe2(T, 64,  128, ST.amount(6, aEvent.mStack), OP.dustSmall.mat(MT.Pu, 12)   , ST.mkic("RTGPellets", 1));
			RM.Press    .addRecipe2(T, 64,  128, ST.amount(6, aEvent.mStack), OP.dust.mat(MT.Pu, 3)         , ST.mkic("RTGPellets", 1));
		}});
		}};
		
		//====================================================================================================
		} // IC2 in general
		//====================================================================================================
		
		RM.pulverizing(ST.make(Items.clay_ball, 1, W), OM.dust(MT.Clay, U));
		RM.pulverizing(ST.make(Blocks.clay, 1, W), OM.dust(MT.Clay, U*4));
		RM.pulverizing(IL.Clay_Ball_Brown .get(1), OM.dust(MT.ClayBrown, U));
		RM.pulverizing(IL.Clay_Ball_Red   .get(1), OM.dust(MT.ClayRed, U));
		RM.pulverizing(IL.Clay_Ball_Yellow.get(1), OM.dust(MT.Bentonite, U));
		RM.pulverizing(IL.Clay_Ball_Blue  .get(1), OM.dust(MT.Palygorskite, U));
		RM.pulverizing(IL.Clay_Ball_White .get(1), OM.dust(MT.Kaolinite, U));
		RM.pulverizing(ST.make(BlocksGT.Diggables, 1, 1), OM.dust(MT.ClayBrown, U*4));
		RM.pulverizing(ST.make(BlocksGT.Diggables, 1, 3), OM.dust(MT.ClayRed, U*4));
		RM.pulverizing(ST.make(BlocksGT.Diggables, 1, 4), OM.dust(MT.Bentonite, U*4));
		RM.pulverizing(ST.make(BlocksGT.Diggables, 1, 5), OM.dust(MT.Palygorskite, U*4));
		RM.pulverizing(ST.make(BlocksGT.Diggables, 1, 6), OM.dust(MT.Kaolinite, U*4));
		
		if (IL.IC2_Sapling_Rubber.exists())
		RM.ic2_extractor(IL.IC2_Sapling_Rubber.get(1), OM.ingot(MT.Rubber, 2*U9));
		RM.ic2_extractor(IL.Cell_Air.get(1), IL.Cell_Empty.get(1));
		
		CR.shaped(ST.mkic("copperCableItem" , 2), CR.DEF, "xP", 'P', OP.plate.dat(ANY.Cu));
		CR.shaped(ST.mkic("goldCableItem"   , 4), CR.DEF, "xP", 'P', OP.plate.dat(MT.Au));
		CR.shaped(ST.mkic("ironCableItem"   , 3), CR.DEF, "xP", 'P', OP.plate.dat(ANY.Fe));
		CR.shaped(ST.mkic("tinCableItem"    , 3), CR.DEF, "xP", 'P', OP.plate.dat(MT.Sn));
		
		CR.delate(ST.mkic("crop", 1));
		
		CR.shaped(ST.mkic("crop", 1), CR.DEF, "SsS", 'S', OP.stick.dat(MT.WoodTreated));
		CR.shaped(ST.mkic("crop", 1), CR.DEF, "SkS", 'S', OP.stick.dat(MT.WoodTreated));
		CR.shaped(IL.IC2_Scaffold.get(4), CR.DEF, "WWW", " S ", "S S", 'W', OD.plankAnyWood, 'S', OP.stick.dat(ANY.Wood));
		
		CR.remout(ST.mkic("electronicCircuit", 1));
		CR.remout(ST.mkic("advancedCircuit"  , 1));
		RM.generify(IL.Circuit_Basic   .get(1), ST.mkic("electronicCircuit", 1));
		RM.generify(IL.Circuit_Good    .get(1), ST.mkic("electronicCircuit", 1));
		RM.generify(IL.Circuit_Advanced.get(1), ST.mkic("advancedCircuit"  , 1));
		RM.generify(IL.Circuit_Elite   .get(1), ST.mkic("advancedCircuit"  , 1));
		RM.generify(IL.Circuit_Master  .get(1), ST.mkic("advancedCircuit"  , 1));
		RM.generify(IL.Circuit_Ultimate.get(1), ST.mkic("advancedCircuit"  , 1));
		CR.shaped(ST.mkic("electronicCircuit", 1), CR.DEF_NAC_NCC, "  ", " C", 'C', IL.Circuit_Basic   );
		CR.shaped(ST.mkic("electronicCircuit", 1), CR.DEF_NAC_NCC, "  ", " C", 'C', IL.Circuit_Good    );
		CR.shaped(ST.mkic("advancedCircuit"  , 1), CR.DEF_NAC_NCC, "  ", " C", 'C', IL.Circuit_Advanced);
		CR.shaped(ST.mkic("advancedCircuit"  , 1), CR.DEF_NAC_NCC, "  ", " C", 'C', IL.Circuit_Elite   );
		CR.shaped(ST.mkic("advancedCircuit"  , 1), CR.DEF_NAC_NCC, "  ", " C", 'C', IL.Circuit_Master  );
		CR.shaped(ST.mkic("advancedCircuit"  , 1), CR.DEF_NAC_NCC, "  ", " C", 'C', IL.Circuit_Ultimate);
		
		RM.Autoclave        .addRecipe2(T,  0, 3000, OM.dust(MT.EnergiumRed, U*9), ST.tag(9), FL.Steam.make(96000), FL.DistW.make(450), IL.IC2_EnergyCrystal.get(1));
		
		RM.Boxinator        .addRecipe2(T, 16,   16, IL.IC2_Scrap.get(9), ST.tag(9), IL.IC2_Scrapbox.get(1));
		RM.Unboxinator      .addFakeRecipe(F, ST.array(IL.IC2_Scrapbox.get(1)), ST.array(IL.IC2_Scrapbox.getWithName(1, "Random Drops, see Scrapbox Handler")), null, ZL_LONG, ZL_FS, ZL_FS, 16, 16, 0);
		
		for (OreDictMaterial tMat : ANY.Diamond.mToThis)
		RM.Press            .addRecipeX(T, 64,  256, ST.array(IL.IC2_Advanced_Alloy.get(4), OP.plate.mat(MT.Ir, 4), OP.gem.mat(tMat, 1)), IL.IC2_Iridium_Alloy.get(1));
		RM.Press            .addRecipe2(T, 16,   64, IL.IC2_Compressed_Coal_Ball.get(8), OP.dust.mat(MT.Obsidian, 9), IL.IC2_Compressed_Coal_Chunk.get(1));
		
		RM.Compressor       .addRecipe1(T,256,  256, IL.IC2_Carbon_Mesh.get(1)                          , IL.IC2_Carbon_Plate.get(1));
		RM.Compressor       .addRecipe1(T,256,  512, IL.IC2_Compressed_Coal_Chunk.get(1)                , OP.gem.mat(MT.DiamondIndustrial, 1));
		RM.Compressor       .addRecipe1(T, 64,  128, IL.IC2_Coal_Ball.get(1)                            , IL.IC2_Compressed_Coal_Ball.get(1));
		RM.Compressor       .addRecipe1(T, 64,  128, IL.IC2_Mixed_Metal_Ingot.get(1)                    , IL.IC2_Advanced_Alloy.get(1));
		RM.Compressor       .addRecipe1(T, 16,   16, OM.dust(MT.Ir)                                     , ST.mkic("iridiumOre", 1));
		
		RM.RollingMill      .addRecipe1(T, 16,  128, IL.IC2_Mixed_Metal_Ingot.get(1)                    , IL.IC2_Advanced_Alloy.get(1));
		
		RM.RollBender       .addRecipe1(T, 16,  256, OP.plateCurved.mat(MT.Sn, 1)                       , IL.Cell_Empty.get(1));
		RM.RollBender       .addRecipe1(T, 16,   64, OP.casingSmall.mat(MT.Sn, 1)                       , IL.IC2_Food_Can_Empty.get(1));
		
		for (OreDictMaterial tMat : ANY.Cu.mToThis)
		RM.Wiremill         .addRecipe1(T, 16,  128, OP.plate.mat(tMat, 1)                              , ST.mkic("copperCableItem", 3));
		RM.Wiremill         .addRecipe1(T, 16,  128, OP.plate.mat(MT.Sn, 1)                             , ST.mkic("tinCableItem", 4));
		for (OreDictMaterial tMat : ANY.Iron.mToThis)
		RM.Wiremill         .addRecipe1(T, 16,  128, (tMat==MT.Enori?OP.plateGem:OP.plate).mat(tMat, 1) , ST.mkic("ironCableItem", 6));
		RM.Wiremill         .addRecipe1(T, 16,  128, OP.plate.mat(MT.Au, 1)                             , ST.mkic("goldCableItem", 6));
		
		for (OreDictMaterial tMat : ANY.Rubber.mToThis) {
		RM.Laminator.addRecipe2(T, 16,  64, OP.plate.mat(tMat, 1), ST.mkic("tinCableItem"   , 1), ST.mkic("insulatedTinCableItem"   , 1));
		RM.Laminator.addRecipe2(T, 16,  64, OP.plate.mat(tMat, 1), ST.mkic("copperCableItem", 1), ST.mkic("insulatedCopperCableItem", 1));
		RM.Laminator.addRecipe2(T, 16, 128, OP.plate.mat(tMat, 2), ST.mkic("goldCableItem"  , 1), ST.mkic("insulatedGoldCableItem"  , 1));
		RM.Laminator.addRecipe2(T, 16, 192, OP.plate.mat(tMat, 3), ST.mkic("ironCableItem"  , 1), ST.mkic("insulatedIronCableItem"  , 1));
		}
		
		for (FluidStack tCFoam : DYED_C_FOAMS)
		RM.Drying           .addRecipe1(T, 16,   16, IL.IC2_Scaffold_Iron.get(1)                        , tCFoam, NF, IL.IC2_Wall_Reinforced.get(1));
		RM.Drying           .addRecipe1(T, 16,   16, IL.IC2_Scaffold_Iron.get(1)                        , FL.CFoam.make(100), NF, IL.IC2_Wall_Reinforced.get(1));
		
		RM.Bath             .addRecipe1(T,  0,   16, ST.mkic("dynamite", 1)                             , FL.Glue.make(125), NF, ST.mkic("stickyDynamite", 1));
		
		if (IL.ERE_Herbicide.exists())
		RM.Canner           .addRecipe2(T, 16,   16, IL.ERE_Herbicide.get(1)                            , IL.Spray_Empty.get(1)                 , IL.IC2_Spray_WeedEx.get(1));
		if (IL.HBM_Poison_Powder.exists())
		RM.Canner           .addRecipe2(T, 16,   16, IL.HBM_Poison_Powder.get(1)                        , IL.Spray_Empty.get(1)                 , IL.IC2_Spray_WeedEx.get(1));
		RM.Canner           .addRecipe2(T, 16,   16, IL.IC2_Grin_Powder.get(1)                          , IL.Spray_Empty.get(1)                 , IL.IC2_Spray_WeedEx.get(1));
		RM.Canner           .addRecipe1(T, 16,   16, IL.Spray_Empty.get(1)                              , FL.Potion_Poison_2.make(250)      , NF, IL.IC2_Spray_WeedEx.get(1)); if (FL.Poison.exists())
		RM.Canner           .addRecipe1(T, 16,   16, IL.Spray_Empty.get(1)                              , FL.Poison.make(250)               , NF, IL.IC2_Spray_WeedEx.get(1));
		
		RM.Loom             .addRecipe2(T, 64,  128, ST.tag(0)                                          , OP.wireFine.mat(MT.Graphene, 32)      , IL.IC2_Carbon_Fiber.get(1));
		RM.Loom             .addRecipe2(T, 64,  128, ST.tag(0)                                          , IL.IC2_Carbon_Fiber.get(2)            , IL.IC2_Carbon_Mesh.get(1));
		RM.Loom             .addRecipe2(T, 64,  256, ST.tag(1)                                          , OP.wireFine.mat(MT.Graphene, 64)      , IL.IC2_Carbon_Mesh.get(1));
		
		if (IL.IC2_Sapling_Rubber.exists())
		RM.Squeezer         .addRecipe1(T, 16,   64, IL.IC2_Sapling_Rubber.get(1)                       , NF, FL.Latex.make(L/ 4), NI);
		if (IL.IC2_Leaves_Rubber.exists())
		RM.Squeezer         .addRecipe1(T, 16,   64, IL.IC2_Leaves_Rubber.get(1)                        , NF, FL.Latex.make(L/72), NI);
		
		RM.Electrolyzer     .addRecipe2(T, 16, 1000, ST.tag(0), IL.Cell_Water.get(1)                    , ST.mkic("electrolyzedWaterCell", 1));
		
		for (FluidStack tWater : FL.waters(1000))
		RM.Injector         .addRecipe1(T, 16,   16, IL.Cell_Empty.get(1), tWater                       , NF, ST.mkic("hydratingCell", 1));
		
		RM.Mixer            .addRecipe2(T, 16,   32, ST.make(Blocks.tnt, 1, W)                          , OM.dust(MT.Na2SO3, U*1), IL.IC2_ITNT.get(1));
		RM.Mixer            .addRecipe2(T, 16,   32, ST.make(Items.flint, 1, W)                         , OM.dust(MT.Coal, U*8), IL.IC2_Coal_Ball.get(1));
		
		for (FluidStack tWater : FL.waters(250))
		RM.Mixer            .addRecipe1(T, 16,   16, IL.IC2_Grin_Powder.get(1), tWater, FL.Potion_Poison_2.make(250), ZL_IS);
		
		RM.Mixer            .addRecipeX(T, 16,   32, ST.array(ST.tag(2), IL.IC2_Scrap.get(1), OM.dust(MT.SoylentGreen)), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipeX(T, 16,   32, ST.array(ST.tag(2), IL.IC2_Scrap.get(1), OM.dust(MT.MeatRaw     )), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipeX(T, 16,   32, ST.array(ST.tag(2), IL.IC2_Scrap.get(1), OM.dust(MT.FishRaw     )), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipeX(T, 16,   32, ST.array(ST.tag(2), IL.IC2_Scrap.get(1), OM.dust(MT.MeatCooked  )), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipeX(T, 16,   32, ST.array(ST.tag(2), IL.IC2_Scrap.get(1), OM.dust(MT.FishCooked  )), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipeX(T, 16,   32, ST.array(ST.tag(2), IL.IC2_Scrap.get(1), OM.dust(MT.MeatRotten  )), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipeX(T, 16,   32, ST.array(ST.tag(2), IL.IC2_Scrap.get(1), OM.dust(MT.FishRotten  )), IL.IC2_Fertilizer.get(2));
		
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.CaCO3             ), OM.dust(MT.S              ), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.CaCO3             ), OM.dust(MT.Phosphorus     ), IL.IC2_Fertilizer.get(3));
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.CaCO3             ), OM.dust(MT.PhosphorusBlue ), IL.IC2_Fertilizer.get(3));
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.CaCO3             ), OM.dust(MT.PhosphorusRed  ), IL.IC2_Fertilizer.get(3));
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.CaCO3             ), OM.dust(MT.PhosphorusWhite), IL.IC2_Fertilizer.get(3));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.CaCO3             ), OM.dust(MT.PO4            ), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(MT.CaCO3             ), OM.dust(MT.Ash        ,3*U), IL.IC2_Fertilizer.get(1));
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(MT.CaCO3             ), OM.dust(MT.DarkAsh        ), IL.IC2_Fertilizer.get(1));
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(MT.CaCO3             ), OM.dust(MT.VolcanicAsh    ), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.Ca                ), OM.dust(MT.S              ), IL.IC2_Fertilizer.get(3));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.Ca                ), OM.dust(MT.Phosphorus     ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.Ca                ), OM.dust(MT.PhosphorusBlue ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.Ca                ), OM.dust(MT.PhosphorusRed  ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.Ca                ), OM.dust(MT.PhosphorusWhite), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.Ca                ), OM.dust(MT.PO4            ), IL.IC2_Fertilizer.get(3));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.Ca                ), OM.dust(MT.Ash        ,3*U), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.Ca                ), OM.dust(MT.DarkAsh        ), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.Ca                ), OM.dust(MT.VolcanicAsh    ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.Apatite           ), OM.dust(MT.S              ), IL.IC2_Fertilizer.get(3));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.Apatite           ), OM.dust(MT.Phosphorus     ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.Apatite           ), OM.dust(MT.PhosphorusBlue ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.Apatite           ), OM.dust(MT.PhosphorusRed  ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.Apatite           ), OM.dust(MT.PhosphorusWhite), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.Apatite           ), OM.dust(MT.PO4            ), IL.IC2_Fertilizer.get(3));
		if (!MD.FR.mLoaded) {
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.Apatite           ), OM.dust(MT.Ash        ,3*U), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.Apatite           ), OM.dust(MT.DarkAsh        ), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.Apatite           ), OM.dust(MT.VolcanicAsh    ), IL.IC2_Fertilizer.get(4));
		}
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.OREMATS.Glauconite), OM.dust(MT.S              ), IL.IC2_Fertilizer.get(3));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.OREMATS.Glauconite), OM.dust(MT.Phosphorus     ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.OREMATS.Glauconite), OM.dust(MT.PhosphorusBlue ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.OREMATS.Glauconite), OM.dust(MT.PhosphorusRed  ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.OREMATS.Glauconite), OM.dust(MT.PhosphorusWhite), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.OREMATS.Glauconite), OM.dust(MT.PO4            ), IL.IC2_Fertilizer.get(3));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.OREMATS.Glauconite), OM.dust(MT.Ash        ,3*U), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.OREMATS.Glauconite), OM.dust(MT.DarkAsh        ), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.OREMATS.Glauconite), OM.dust(MT.VolcanicAsh    ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.NaHSO4            ), OM.dust(MT.S              ), IL.IC2_Fertilizer.get(3));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.NaHSO4            ), OM.dust(MT.Phosphorus     ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.NaHSO4            ), OM.dust(MT.PhosphorusBlue ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.NaHSO4            ), OM.dust(MT.PhosphorusRed  ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.NaHSO4            ), OM.dust(MT.PhosphorusWhite), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.NaHSO4            ), OM.dust(MT.PO4            ), IL.IC2_Fertilizer.get(3));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.NaHSO4            ), OM.dust(MT.Ash        ,3*U), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.NaHSO4            ), OM.dust(MT.DarkAsh        ), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.NaHSO4            ), OM.dust(MT.VolcanicAsh    ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.KHSO4             ), OM.dust(MT.S              ), IL.IC2_Fertilizer.get(3));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.KHSO4             ), OM.dust(MT.Phosphorus     ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.KHSO4             ), OM.dust(MT.PhosphorusBlue ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.KHSO4             ), OM.dust(MT.PhosphorusRed  ), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   64, OM.dust(MT.KHSO4             ), OM.dust(MT.PhosphorusWhite), IL.IC2_Fertilizer.get(4));
		RM.Mixer            .addRecipe2(T, 16,   48, OM.dust(MT.KHSO4             ), OM.dust(MT.PO4            ), IL.IC2_Fertilizer.get(3));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.KHSO4             ), OM.dust(MT.Ash        ,3*U), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.KHSO4             ), OM.dust(MT.DarkAsh        ), IL.IC2_Fertilizer.get(2));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.KHSO4             ), OM.dust(MT.VolcanicAsh    ), IL.IC2_Fertilizer.get(4));
		
		
		for (OreDictMaterial tMat : ANY.Iron.mToThis) if (tMat != MT.Enori) {
		RM.Welder           .addRecipeX(T, 16,  128, ST.array(OP.ingot.mat(tMat   , 1), OP.ingot.mat(MT.Bronze, 1), OP.ingot.mat(MT.Sn, 1)), IL.IC2_Mixed_Metal_Ingot.get(1));
		RM.Welder           .addRecipeX(T, 16,  256, ST.array(OP.plate.mat(tMat   , 1), OP.plate.mat(MT.Bronze, 1), OP.plate.mat(MT.Sn, 1)), IL.IC2_Mixed_Metal_Ingot.get(1));
		}
		RM.Welder           .addRecipeX(T, 16,  128, ST.array(OP.ingot.mat(MT.HSLA, 1), OP.ingot.mat(MT.Bronze, 1), OP.ingot.mat(MT.Sn, 1)), IL.IC2_Mixed_Metal_Ingot.get(1));
		RM.Welder           .addRecipeX(T, 16,  256, ST.array(OP.plate.mat(MT.HSLA, 1), OP.plate.mat(MT.Bronze, 1), OP.plate.mat(MT.Sn, 1)), IL.IC2_Mixed_Metal_Ingot.get(1));
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener("blockSolidObsidian", "blockDustObsidian", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Press.addRecipe2(T, 16,   64, IL.IC2_Compressed_Coal_Ball.get(8), aEvent.mStack, IL.IC2_Compressed_Coal_Chunk.get(1));
		}});
		}};
		
		
		//                                                                                                                                                                                                                                                                                                                                                 TIER,SIZE,    ,AH,HA,CH,FD,DF,CO,WD
		new GT_BaseCrop("Indigo"                , "Eloraam"                 , OP.plantGtBlossom.mat(MT.Indigo, 1)               , null                                                                                                                                                                  , OP.plantGtBlossom.mat(MT.Indigo, 4)               , 2, 4,     0, 1, 4, 1, 1, 0, 4, 0, new String[] {"Flower"      , "Color", "Ingredient"});
		new GT_BaseCrop("Flax"                  , "Eloraam"                 , ST.make(Items.string, 1, 0)                       , null                                                                                                                                                                  , null                                              , 2, 4,     0, 1, 4, 1, 1, 2, 0, 1, new String[] {"Vine"        , "Silk", "Addictive"});
		new GT_BaseCrop("Oil Berries"           , "Spacetoad"               , OP.plantGtBerry.mat(MT.Oil, 1)                    , null                                                                                                                                                                  , null                                              , 9, 4, 27000, 1, 4, 6, 1, 2, 1,12, new String[] {"Reed"        , "Fire", "Dark", "Rotten", "Coal", "Oil", "Berry"});
		new GT_BaseCrop("Bobsyeruncle Ranks"    , "GenerikB"                , OP.plantGtBerry.mat(MT.Emerald, 1)                , null                                                                                                                                                                  , null                                              ,11, 4, 16500, 1, 4, 4, 0, 8, 2, 9, new String[] {"Vine"        , "Shiny", "Emerald", "Berylium", "Crystal"});
		new GT_BaseCrop("Diareed"               , "Direwolf20"              , OP.dustTiny.mat(MT.Diamond ,1)                    , null                                                                                                                                                                  , null                                              ,12, 4, 36000, 1, 4, 5, 0,10, 2,10, new String[] {"Reed"        , "Fire", "Shiny", "Coal", "Diamond", "Crystal"});
		new GT_BaseCrop("Withereed"             , "CovertJaguar"            , OP.dust.mat(MT.Coal, 1)                           , ST.array(ST.make(Items.coal, 1, 0), ST.make(Items.coal, 1, 0))                                                                                                        , null                                              , 8, 4, 24000, 1, 4, 2, 0, 4, 1, 6, new String[] {"Reed"        , "Fire", "Undead", "Coal", "Rotten", "Wither"});
		new GT_BaseCrop("Blaze Reed"            , "Mr. Brain"               , ST.make(Items.blaze_powder, 1, 0)                 , ST.array(ST.make(Items.blaze_rod, 1, 0))                                                                                                                              , null                                              ,10, 4,  9000, 1, 4, 4, 0, 8, 2,10, new String[] {"Reed"        , "Fire", "Blaze", "Sulfur"});
		new GT_BaseCrop("Eggplant"              , "Link"                    , ST.make(Items.egg, 1, 0)                          , ST.array(ST.make(Items.chicken, 1, 0), ST.make(Items.feather, 1, 0), ST.make(Items.feather, 1, 0), ST.make(Items.feather, 1, 0))                                      , null                                              , 6, 3,   900, 2, 3, 0, 4, 1, 0, 0, new String[] {"Flower"      , "Food", "Chicken", "Egg", "Feather", "Addictive"});
		new GT_BaseCrop("Corium"                , "Gregorius Techneticies"  , ST.make(Items.leather, 1, 0)                      , null                                                                                                                                                                  , null                                              , 6, 4,     0, 1, 4, 0, 2, 3, 1, 0, new String[] {"Vine"        , "Cow", "Silk"});
		new GT_BaseCrop("Corpse Plant"          , "Mr. Kenny"               , ST.make(Items.rotten_flesh, 1, 0)                 , ST.array(IL.Dye_Bonemeal.get(1), IL.Dye_Bonemeal.get(1), ST.make(Items.bone, 1, 0))                                                                                   , null                                              , 5, 4,     0, 1, 4, 0, 2, 1, 0, 3, new String[] {"Vine"        , "Food", "Toxic", "Undead", "Rotten"});
		new GT_BaseCrop("Creeper Weed"          , "General Spaz"            , OP.dust.mat(MT.Gunpowder, 1)                      , null                                                                                                                                                                  , null                                              , 7, 4,     0, 1, 4, 3, 0, 5, 1, 3, new String[] {"Vine"        , "Creeper", "Explosive", "Fire", "Sulfur", "Saltpeter", "Coal"});
		new GT_BaseCrop("Ender Bloom"           , "RichardG"                , OP.dust.mat(MT.EnderPearl, 1)                     , ST.array(ST.make(Items.ender_pearl, 1, 0), ST.make(Items.ender_pearl, 1, 0), ST.make(Items.ender_eye, 1, 0))                                                          , null                                              ,10, 4, 15000, 1, 4, 5, 0, 2, 1, 6, new String[] {"Flower"      , "Shiny", "Ender"});
		new GT_BaseCrop("Meat Rose"             , "VintageBeef"             , ST.make(Items.dye, 1, 9)                          , ST.array(ST.make(Items.beef, 1, 0), ST.make(Items.porkchop, 1, 0), ST.make(Items.chicken, 1, 0), ST.make(Items.fish, 1, 0))                                           , null                                              , 7, 4,  1500, 1, 4, 0, 4, 1, 3, 0, new String[] {"Flower"      , "Food", "Cow", "Fish", "Chicken", "Pig"});
		new GT_BaseCrop("Milkwart"              , "Mr. Brain"               , OP.plantGtWart.mat(MT.Milk, 1)                    , null                                                                                                                                                                  , OP.plantGtWart.mat(MT.Milk, 1)                    , 6, 3,   900, 1, 3, 0, 3, 0, 1, 0, new String[] {"Wart"        , "Food", "Milk", "Cow", "Ingredient"});
		new GT_BaseCrop("Glowshrooms"           , "Speiger"                 , OP.plantGtWart.mat(MT.Glowstone, 1)               , null                                                                                                                                                                  , OP.plantGtWart.mat(MT.Glowstone, 1)               , 6, 3,  2400, 1, 3, 5, 0, 0, 2, 0, new String[] {"Wart"        , "Shiny", "Ingredient"});
		new GT_BaseCrop("Slime Plant"           , "Neowulf"                 , ST.make(Items.slime_ball, 1, 0)                   , null                                                                                                                                                                  , null                                              , 6, 4,     0, 3, 4, 3, 0, 0, 0, 2, new String[] {"Bush"        , "Slime", "Bouncy", "Sticky"});
		new GT_BaseCrop("Spidernip"             , "Ms. Muffet"              , ST.make(Items.string, 1, 0)                       , ST.array(ST.make(Items.spider_eye, 1, 0), ST.make(Blocks.web, 1, 0))                                                                                                  , null                                              , 4, 4,   600, 1, 4, 2, 1, 4, 1, 3, new String[] {"Flower"      , "Toxic", "Silk", "Spider", "Ingredient", "Addictive"});
		new GT_BaseCrop("Tear Stalks"           , "Neowulf"                 , ST.make(Items.ghast_tear, 1, 0)                   , null                                                                                                                                                                  , null                                              , 8, 4,  2400, 1, 4, 1, 2, 0, 0, 0, new String[] {"Reed"        , "Healing", "Nether", "Ingredient", "Ghast"});
		new GT_BaseCrop("Tine"                  , "Gregorius Techneticies"  , OP.plantGtTwig.mat(MT.Sn, 1)                      , null                                                                                                                                                                  , null                                              , 5, 3, 15000, 2, 3, 2, 0, 3, 0, 0, new String[] {"Bush"        , "Shiny", "Metal", "Pine", "Tin"});
		new GT_BaseCrop("Coppon"                , "Mr. Brain"               , OP.plantGtFiber.mat(MT.Cu, 1)                     , null                                                                                                                                                                  , null                                              , 6, 3, 18000, 2, 3, 2, 0, 1, 1, 1, new String[] {"Bush"        , "Shiny", "Metal", "Cotton", "Copper"});
		new GT_BaseCrop("Argentia"              , "Eloraam"                 , OP.plantGtBlossom.mat(MT.Ag, 1)                   , null                                                                                                                                                                  , null                                              , 7, 4, 21000, 3, 4, 2, 0, 1, 0, 0, new String[] {"Reed"        , "Shiny", "Metal", "Silver"});
		new GT_BaseCrop("Plumbilia"             , "KingLemming"             , OP.plantGtBlossom.mat(MT.Pb, 1)                   , null                                                                                                                                                                  , null                                              , 6, 4, 18000, 3, 4, 2, 0, 3, 1, 1, new String[] {"Reed"        , "Heavy", "Metal", "Lead"});
		new GT_BaseCrop("Steeleaf Ranks"        , "Benimatic"               , OP.nugget.mat(MT.Steeleaf, 1)                     , ST.array(OP.ingot.mat(MT.Steeleaf, 1))                                                                                                                                , null                                              ,10, 4, 30000, 1, 4, 3, 0, 7, 2, 8, new String[] {"Vine"        , "Metal", "Iron"});
		new GT_BaseCrop("Liveroots"             , "Benimatic"               , OP.dust.mat(MT.LiveRoot, 1)                       , ST.array(IL.TF_LiveRoot.get(1))                                                                                                                                       , null                                              , 8, 4,     0, 1, 4, 2, 0, 5, 2, 6, new String[] {"Vine"        , "Wood"});
		//                                                                                                                                                                                                                                                                                                                                                 TIER,SIZE,    ,AH,HA,CH,FD,DF,CO,WD
		new GT_BaseCrop("Rye"                   , "Binnie"                  , IL.Crop_Rye.get(1)                                , null                                                                                                                                                                  , IL.Crop_Rye.get(1)                                , 1, 7,     0, 2, 7, 0, 4, 0, 0, 2, new String[] {"Wheat"       , "Food", "Grain"});
		new GT_BaseCrop("Barley"                , "Glitchfiend"             , IL.Crop_Barley.get(1)                             , null                                                                                                                                                                  , IL.Crop_Barley.get(1)                             , 1, 7,     0, 2, 7, 0, 4, 0, 0, 2, new String[] {"Wheat"       , "Food", "Grain"});
		new GT_BaseCrop("Oats"                  , "Pam"                     , IL.Crop_Oats.get(1)                               , null                                                                                                                                                                  , IL.Crop_Oats.get(1)                               , 1, 7,     0, 2, 7, 0, 4, 0, 0, 2, new String[] {"Wheat"       , "Food", "Grain"});
		new GT_BaseCrop("Rice"                  , "Ellpeck"                 , IL.Crop_Rice.get(1)                               , null                                                                                                                                                                  , IL.Crop_Rice.get(1)                               , 1, 7,     0, 2, 7, 0, 4, 0, 0, 2, new String[] {"Wheat"       , "Food", "Grain"});
		//                                                                                                                                                                                                                                                                                                                                                 TIER,SIZE,    ,AH,HA,CH,FD,DF,CO,WD
		new GT_BaseCrop("Tea"                   , "Pam"                     , OP.plantGtBlossom.mat(MT.Tea, 1)                  , null                                                                                                                                                                  , OP.plantGtBlossom.mat(MT.Tea, 4)                  , 2, 4,     0, 1, 4, 2, 4, 2, 0, 1, new String[] {"Leaves"      , "Food", "Addictive"});
		new GT_BaseCrop("Mint"                  , "Gregorius Techneticies"  , OP.plantGtBlossom.mat(MT.Mint, 1)                 , null                                                                                                                                                                  , OP.plantGtBlossom.mat(MT.Mint, 4)                 , 2, 4,     0, 1, 4, 2, 3, 5, 0, 1, new String[] {"Leaves"      , "Food", "Fresh"});
		new GT_BaseCrop("Lemon Plant"           , "Cave Johnson"            , IL.Food_Lemon.get(1)                              , null                                                                                                                                                                  , IL.Food_Lemon.get(4)                              , 3, 4,     0, 3, 4, 3, 4, 3, 1, 2, new String[] {"Bush"        , "Food", "Fruit", "Explosive"});
		new GT_BaseCrop("Green Apple Tree"      , "The Guy"                 , IL.Food_Apple_Green.get(1)                        , null                                                                                                                                                                  , IL.Food_Apple_Green.get(4)                        , 3, 4,     0, 3, 4, 3, 3, 5, 1, 2, new String[] {"Bush"        , "Food", "Fruit", "Apple"});
		new GT_BaseCrop("Yellow Apple Tree"     , "The Guy"                 , IL.Food_Apple_Yellow.get(1)                       , null                                                                                                                                                                  , IL.Food_Apple_Yellow.get(4)                       , 3, 4,     0, 3, 4, 2, 4, 4, 1, 2, new String[] {"Bush"        , "Food", "Fruit", "Apple"});
		new GT_BaseCrop("Red Apple Tree"        , "The Guy"                 , IL.Food_Apple_Red.get(1)                          , null                                                                                                                                                                  , IL.Food_Apple_Red.get(4)                          , 3, 4,     0, 3, 4, 2, 4, 3, 1, 2, new String[] {"Bush"        , "Food", "Fruit", "Apple"});
		new GT_BaseCrop("Dark Red Apple Tree"   , "The Guy"                 , IL.Food_Apple_DarkRed.get(1)                      , null                                                                                                                                                                  , IL.Food_Apple_DarkRed.get(4)                      , 3, 4,     0, 3, 4, 2, 5, 2, 2, 3, new String[] {"Bush"        , "Food", "Fruit", "Apple"});
		new GT_BaseCrop("Chili"                 , "Gregorius Techneticies"  , IL.Food_Chili_Pepper.get(1)                       , null                                                                                                                                                                  , IL.Food_Chili_Pepper.get(4)                       , 3, 4,     0, 3, 4, 4, 4, 6, 2, 1, new String[] {"Vine"        , "Food", "Fruit", "Fire"});
		new GT_BaseCrop("Tomato Plant"          , "Kirby"                   , IL.Food_Tomato.get(1)                             , null                                                                                                                                                                  , IL.Food_Tomato.get(4)                             , 2, 4,     0, 3, 4, 2, 4, 2, 1, 3, new String[] {"Vine"        , "Food", "Fruit", "Vegetable"});
		new GT_BaseCrop("Red Grapes"            , "Pam"                     , IL.Food_Grapes_Red.get(1)                         , null                                                                                                                                                                  , IL.Food_Grapes_Red.get(4)                         , 3, 4,     0, 3, 4, 1, 4, 0, 2, 3, new String[] {"Vine"        , "Food", "Fruit"});
		new GT_BaseCrop("White Grapes"          , "Binnie"                  , IL.Food_Grapes_White.get(1)                       , null                                                                                                                                                                  , IL.Food_Grapes_White.get(4)                       , 3, 4,     0, 3, 4, 1, 4, 0, 0, 3, new String[] {"Vine"        , "Food", "Fruit"});
		new GT_BaseCrop("Green Grapes"          , "Gwafu"                   , IL.Food_Grapes_Green.get(1)                       , null                                                                                                                                                                  , IL.Food_Grapes_Green.get(4)                       , 3, 4,     0, 3, 4, 1, 4, 0, 1, 3, new String[] {"Vine"        , "Food", "Fruit"});
		new GT_BaseCrop("Purple Grapes"         , "Gregorius Techneticies"  , IL.Food_Grapes_Purple.get(1)                      , ST.array(IL.Food_Grapes_Purple.getWithName(1, "Member Berries"))                                                                                                      , IL.Food_Grapes_Purple.get(4)                      , 3, 4,     0, 3, 4, 1, 4, 0, 2, 3, new String[] {"Vine"        , "Food", "Fruit", "Member?"});
		new GT_BaseCrop("Blueberry Bush"        , "Pam"                     , IL.Food_Blueberry.get(1)                          , null                                                                                                                                                                  , IL.Food_Blueberry.get(4)                          , 3, 5,     0, 4, 5, 1, 4, 1, 4, 2, new String[] {"Bush"        , "Food", "Fruit", "Berry", "Color"});
		new GT_BaseCrop("Gooseberry Bush"       , "Pam"                     , IL.Food_Gooseberry.get(1)                         , null                                                                                                                                                                  , IL.Food_Gooseberry.get(4)                         , 3, 5,     0, 4, 5, 1, 4, 1, 1, 2, new String[] {"Bush"        , "Food", "Fruit", "Berry"});
		new GT_BaseCrop("Candleberry Bush"      , "Pam"                     , IL.Food_Candleberry.get(1)                        , null                                                                                                                                                                  , IL.Food_Candleberry.get(4)                        , 3, 5,     0, 4, 5, 3, 3, 1, 0, 2, new String[] {"Bush"        , "Food", "Fruit", "Berry", "Wax"});
		new GT_BaseCrop("Cranberries"           , "Pam"                     , IL.Food_Cranberry.get(1)                          , null                                                                                                                                                                  , IL.Food_Cranberry.get(4)                          , 3, 4,     0, 3, 4, 1, 4, 0, 2, 3, new String[] {"Vine"        , "Food", "Fruit", "Berry"});
		new GT_BaseCrop("Black Currants"        , "Gregorius Techneticies"  , IL.Food_Currants_Black.get(1)                     , null                                                                                                                                                                  , IL.Food_Currants_Black.get(4)                     , 3, 5,     0, 4, 5, 1, 4, 1, 1, 2, new String[] {"Bush"        , "Food", "Fruit", "Berry"});
		new GT_BaseCrop("White Currants"        , "Gregorius Techneticies"  , IL.Food_Currants_White.get(1)                     , null                                                                                                                                                                  , IL.Food_Currants_White.get(4)                     , 3, 5,     0, 4, 5, 1, 4, 1, 0, 2, new String[] {"Bush"        , "Food", "Fruit", "Berry"});
		new GT_BaseCrop("Red Currants"          , "Gregorius Techneticies"  , IL.Food_Currants_Red.get(1)                       , null                                                                                                                                                                  , IL.Food_Currants_Red.get(4)                       , 3, 5,     0, 4, 5, 1, 4, 1, 2, 2, new String[] {"Bush"        , "Food", "Fruit", "Berry"});
		new GT_BaseCrop("Blackberries"          , "Pam"                     , IL.Food_Blackberry.get(1)                         , null                                                                                                                                                                  , IL.Food_Blackberry.get(4)                         , 3, 4,     0, 3, 4, 1, 4, 4, 2, 3, new String[] {"Vine"        , "Food", "Fruit", "Berry"});
		new GT_BaseCrop("Raspberries"           , "Pam"                     , IL.Food_Raspberry.get(1)                          , null                                                                                                                                                                  , IL.Food_Raspberry.get(4)                          , 3, 4,     0, 3, 4, 1, 4, 1, 2, 3, new String[] {"Vine"        , "Food", "Fruit", "Berry"});
		new GT_BaseCrop("Strawberries"          , "Pam"                     , IL.Food_Strawberry.get(1)                         , null                                                                                                                                                                  , IL.Food_Strawberry.get(4)                         , 3, 4,     0, 1, 4, 1, 4, 0, 2, 4, new String[] {"Bush"        , "Food", "Fruit", "Berry"});
		new GT_BaseCrop("Onion"                 , "Onion San"               , IL.Food_Onion.get(1)                              , null                                                                                                                                                                  , IL.Food_Onion.get(4)                              , 2, 4,     0, 1, 4, 3, 3, 3, 0, 1, new String[] {"Vegetable"   , "Food", "Ingredient"});
		new GT_BaseCrop("Cucumber"              , "Pam"                     , IL.Food_Cucumber.get(1)                           , null                                                                                                                                                                  , IL.Food_Cucumber.get(4)                           , 2, 4,     0, 1, 4, 1, 5, 0, 0, 2, new String[] {"Vegetable"   , "Food", "Ingredient"});
		new GT_BaseCrop("Peanuts"               , "Snoopy"                  , IL.Food_Peanut.get(1)                             , null                                                                                                                                                                  , IL.Food_Peanut.get(4)                             , 3, 4,     0, 1, 4, 1, 4, 0, 2, 4, new String[] {"Bush"        , "Food", "Nut"});
		new GT_BaseCrop("Ananas"                , "Spongebob"               , IL.Food_Ananas.get(1)                             , null                                                                                                                                                                  , IL.Food_Ananas.get(4)                             , 4, 3,     0, 1, 3, 3, 3, 5, 1, 1, new String[] {"Bush"        , "Food", "Fruit", "Pine", "Apple"});
		//                                                                                                                                                                                                                                                                                                                                                 TIER,SIZE,    ,AH,HA,CH,FD,DF,CO,WD
		new GT_BaseCrop("Desert Nova"           , "Mithion"                 , IL.ARS_DesertNova .get(1, IL.DesertNova .get(1))  , null                                                                                                                                                                  , IL.ARS_DesertNova.get(4, IL.DesertNova.get(4))    , 6, 4,     0, 1, 4, 5, 1, 7, 4,10, new String[] {"Cactus"      , "Magic", "Fire", "Explosive"});
		new GT_BaseCrop("Cerublossom"           , "Mithion"                 , IL.ARS_Cerublossom.get(1, IL.Cerublossom.get(1))  , null                                                                                                                                                                  , IL.ARS_Cerublossom.get(4, IL.Cerublossom.get(4))  , 6, 4,     0, 1, 4, 1, 1, 2, 4,10, new String[] {"Flower"      , "Magic", "Shiny"});
		new GT_BaseCrop("Shimmerleaf"           , "Azanor"                  , OP.chunkGt.mat(MT.Hg, 1)                          , null                                                                                                                                                                  , IL.TC_Shimmerleaf.get(4)                          ,11, 4,     0, 1, 4, 5, 1, 4, 1, 8, new String[] {"Flower"      , "Magic", "Shiny", "Metal", "Mercury"});
		new GT_BaseCrop("Cinderpearl"           , "Azanor"                  , ST.make(Items.blaze_powder, 1, 0)                 , null                                                                                                                                                                  , IL.TC_Cinderpearl.get(4)                          , 8, 4,     0, 1, 4, 3, 1, 8, 2, 8, new String[] {"Flower"      , "Magic", "Fire", "Blaze", "Sulfur", "Ingredient"});
	}
}
