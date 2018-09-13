/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregtech;

import static gregapi.data.CS.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import appeng.api.AEApi;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import gregapi.api.Abstract_Mod;
import gregapi.api.Abstract_Proxy;
import gregapi.block.prefixblock.PrefixBlockItem;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.IItemContainer;
import gregapi.code.ItemStackContainer;
import gregapi.code.TagData;
import gregapi.data.ANY;
import gregapi.data.CS.DirectoriesGT;
import gregapi.data.CS.FluidsGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.CS.ModIDs;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.item.multiitem.behaviors.Behavior_Turn_Into;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.prefixitem.PrefixItem;
import gregapi.network.NetworkHandler;
import gregapi.oredict.IOreDictConfigurationComponent;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.recipes.Recipe;
import gregapi.recipes.maps.RecipeMapReplicator;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregtech.blocks.fluids.BlockOcean;
import gregtech.entities.projectiles.EntityArrow_Material;
import gregtech.entities.projectiles.EntityArrow_Potion;
import gregtech.items.tools.early.GT_Tool_Scoop;
import gregtech.loaders.a.*;
import gregtech.loaders.b.Loader_Books;
import gregtech.loaders.b.Loader_Fuels;
import gregtech.loaders.b.Loader_ItemIterator;
import gregtech.loaders.b.Loader_Late_Items_And_Blocks;
import gregtech.loaders.b.Loader_MultiTileEntities;
import gregtech.loaders.b.Loader_OreProcessing;
import gregtech.loaders.b.Loader_Worldgen;
import gregtech.loaders.c.*;
import gregtech.loaders.c.mod.*;
import ic2.core.Ic2Items;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author Gregorius Techneticies
 */
@Mod(modid=ModIDs.GT, name="GregTech", version="GT6-MC1710", dependencies="required-after:"+ModIDs.GAPI_POST)
public class GT_Mod extends Abstract_Mod {
	@SidedProxy(modId = ModIDs.GT, clientSide = "gregtech.GT_Client", serverSide = "gregtech.GT_Server")
	public static GT_Proxy gregtechproxy;
	public static int MIN_IC2 = 827, MAX_IC2 = Integer.MAX_VALUE;
	public static String MAJOR_VERSION = "609";
	
	public GT_Mod() {
		GT = this;
		NW_GT = new NetworkHandler(MD.GT.mID, "GREG");
	}
	
	@Override
	public void onModPreInit2(FMLPreInitializationEvent aEvent) {
		try {
			OUT.println(getModNameForLog() + ": Sorting GregTech to the end of the Mod List for further processing.");
			LoadController tLoadController = ((LoadController)UT.Reflection.getFieldContent(Loader.instance(), "modController", T, T));
			List<ModContainer> tModList = tLoadController.getActiveModList(), tNewModsList = new ArrayList<>(tModList.size());
			ModContainer tGregTech = null;
			for (short i = 0; i < tModList.size(); i++) {
				ModContainer tMod = tModList.get(i);
				if (tMod.getModId().equalsIgnoreCase(MD.GT.mID)) tGregTech = tMod; else tNewModsList.add(tMod);
			}
			if (tGregTech != null) tNewModsList.add(tGregTech);
			UT.Reflection.getField(tLoadController, "activeModList", T, T).set(tLoadController, tNewModsList);
		} catch(Throwable e) {
			if (D1) e.printStackTrace(ERR);
		}
		
		File tFile = new File(DirectoriesGT.CONFIG_GT, "GregTech.cfg");
		if (!tFile.exists()) tFile = new File(DirectoriesGT.CONFIG_GT, "gregtech.cfg");
		Configuration tMainConfig = new Configuration(tFile);
		
		OUT.println(getModNameForLog() + ": Setting Configs");
		
		gregtechproxy.mSkeletonsShootGTArrows           = tMainConfig.get("general", "SkeletonsShootGTArrows"       , 16).getInt(16);
		gregtechproxy.mFlintChance                      = tMainConfig.get("general", "FlintAndSteelChance"          , 30).getInt(30);
		gregtechproxy.mDisableVanillaOres               = tMainConfig.get("general", "DisableVanillaOres"           , T).getBoolean(T);
		gregtechproxy.mDisableIC2Ores                   = tMainConfig.get("general", "DisableIC2Ores"               , T).getBoolean(T);
		gregtechproxy.mIncreaseDungeonLoot              = tMainConfig.get("general", "IncreaseDungeonLoot"          , T).getBoolean(T);
		gregtechproxy.mNerfedVanillaTools               = tMainConfig.get("general", "SmallerVanillaToolDurability" , T).getBoolean(T);
		
		BlockOcean.SPREAD_TO_AIR                        = tMainConfig.get("general", "OceanBlocksSpreadToAir"       , T).getBoolean(T);
		
		OUT.println(getModNameForLog() + ": Saving Main Config");
		tMainConfig.save();
		
		if (COMPAT_IC2 != null && !MD.IC2C.mLoaded) {
			OUT.println(getModNameForLog() + ": Removing all original Scrapbox Drops.");
			try {
				UT.Reflection.getField("ic2.core.item.ItemScrapbox$Drop", "topChance", T, T).set(null, 0);
				((List<?>)UT.Reflection.getFieldContent(UT.Reflection.getFieldContent("ic2.api.recipe.Recipes", "scrapboxDrops", T, T), "drops", T, T)).clear();
			} catch(Throwable e) {
				if (D1) e.printStackTrace(ERR);
			}
			
			OUT.println(getModNameForLog() + ": Adding Scrap with a Weight of 200.0F to the Scrapbox Drops.");
			COMPAT_IC2.scrapbox(200.0F, IL.IC2_Scrap.get(1));
		}
		
		EntityRegistry.registerModEntity(EntityArrow_Material.class , "GT_Entity_Arrow"         , 1, GT, 160, 1, T);
		EntityRegistry.registerModEntity(EntityArrow_Potion.class   , "GT_Entity_Arrow_Potion"  , 2, GT, 160, 1, T);
		
		for (OreDictMaterial tWood : ANY.Wood.mToThis) OP.plate.disableItemGeneration(tWood);
		OP.ingot        .disableItemGeneration(MT.Butter, MT.ButterSalted, MT.Chocolate, MT.Cheese, MT.MeatRaw, MT.MeatCooked, MT.FishRaw, MT.FishCooked, MT.Tofu, MT.SoylentGreen);
		OP.gemChipped   .disableItemGeneration(MT.EnergiumRed, MT.EnergiumCyan);
		OP.gemFlawed    .disableItemGeneration(MT.EnergiumRed, MT.EnergiumCyan);
		OP.gem          .disableItemGeneration(MT.EnergiumRed, MT.EnergiumCyan);
		OP.gemFlawless  .disableItemGeneration(MT.EnergiumRed, MT.EnergiumCyan);
		OP.gemExquisite .disableItemGeneration(MT.EnergiumRed, MT.EnergiumCyan);
		OP.gemLegendary .disableItemGeneration(MT.EnergiumRed, MT.EnergiumCyan);
		
		
		OUT.println("GT_Mod: Register Other Stuff.");
		
		RM.pulverizing(ST.make(Blocks.cobblestone   , 1, W), ST.make(Blocks.sand, 1, 0), null, 0, F);
		RM.pulverizing(ST.make(Blocks.stone         , 1, W), ST.make(Blocks.cobblestone, 1, 0), null, 0, F);
		RM.pulverizing(ST.make(Blocks.gravel        , 1, W), ST.make(Items.flint, 1, 0), OP.dustSmall.mat(MT.Flint, 1), 10, F);
		RM.pulverizing(ST.make(Blocks.furnace       , 1, W), ST.make(Blocks.sand, 6, 0), null, 0, F);
		RM.pulverizing(ST.make(Blocks.lit_furnace   , 1, W), ST.make(Blocks.sand, 6, 0), null, 0, F);
		RM.pulverizing(ST.make(Items.bone           , 1, W), IL.Dye_Bonemeal.get(2), IL.Dye_Bonemeal.get(1), 50, T);
		RM.pulverizing(ST.make(Items.blaze_rod      , 1, W), ST.make(Items.blaze_powder, 3, 0), ST.make(Items.blaze_powder, 1, 0), 50, T);
		RM.pulverizing(ST.make(Blocks.pumpkin       , 1, W), ST.make(Items.pumpkin_seeds, 4, 0), null, 0, F);
		RM.pulverizing(ST.make(Items.melon          , 1, W), ST.make(Items.melon_seeds, 1, 0), null, 0, F);
		RM.pulverizing(ST.make(Blocks.wool          , 1, W), ST.make(Items.string, 2, 0), ST.make(Items.string, 1, 0), 50, F);
		
		new Loader_Fluids().run();
		new Loader_Tools().run();
		new Loader_Items().run();
		new Loader_PrefixBlocks().run();
		new Loader_Rocks().run();
		new Loader_Blocks().run();
		new Loader_Woods().run();
		new Loader_Rails().run();
		new Loader_Ores().run();
		new Loader_Others().run();
		
//      new Loader_CircuitBehaviors().run();
//      new Loader_CoverBehaviors().run();
//      new Loader_Sonictron().run();
	}
	
	@Override
	public void onModInit2(FMLInitializationEvent aEvent) {
		for (FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) if (tData.filledContainer.getItem() == Items.potionitem && ST.meta(tData.filledContainer) == 0) {tData.fluid.amount = 0; break;}
		OP.chemtube.mContainerItem = OP.chemtube.mat(MT.Empty, 1);
		
		new Loader_Late_Items_And_Blocks().run();
		
		if (MD.IC2C.mLoaded) for (int i = 0; i <= 6; i++) FMLInterModComms.sendMessage(MD.IC2C.mID, "generatorDrop", ST.save(UT.NBT.makeInt("Key", i), "Value", IL.IC2_Machine.get(1)));
		
		new Loader_MultiTileEntities().run();
		new Loader_Books().run();
		new Loader_OreProcessing().run();
		new Loader_Worldgen().run();
		new Loader_ItemIterator().run();
	}
	
	@Override
	public void onModPostInit2(FMLPostInitializationEvent aEvent) {
		// Clearing the AE Grindstone Recipe List.
		if (MD.AE.mLoaded) AEApi.instance().registries().grinder().getRecipes().clear();
		
		for (FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) if (tData.filledContainer.getItem() == Items.potionitem && ST.meta(tData.filledContainer) == 0) {tData.fluid.amount = 0; break;}
		
		new Loader_BlockResistance().run();
		new Loader_Fuels().run();
		new Loader_Crops().run();
		new Loader_Loot().run();
		
		// Doing all the Recipe Additions.
		
		ArrayListNoNulls<Thread> tThreadList = new ArrayListNoNulls<>(F
		, new Thread(new Loader_Recipes_Extruder())
		);
		
		for (Thread tThread : tThreadList) tThread.start();
		
		ArrayListNoNulls<Runnable> tList = new ArrayListNoNulls<>(F,
			new Loader_Recipes_Woods(), // has to be before Vanilla!
			new Loader_Recipes_Vanilla(), // has to be after Woods!
			new Loader_Recipes_Temporary(),
			new Loader_Recipes_Chem(),
			new Loader_Recipes_Crops(),
			new Loader_Recipes_Potions(),
			new Loader_Recipes_Food(),
			new Loader_Recipes_Ores(),
			new Loader_Recipes_Alloys(),
			new Loader_Recipes_Other(),
			
			new Loader_Recipes_Chisel(),
			new Loader_Recipes_Ganys(),
			new Loader_Recipes_IndustrialCraft(),
			new Loader_Recipes_IndustrialCraft_Scrap(),
			new Loader_Recipes_BuildCraft(),
			new Loader_Recipes_Railcraft(), // has to be before MFR!
			new Loader_Recipes_ThermalExpansion(),
			new Loader_Recipes_Forestry(),
			new Loader_Recipes_MagicBees(),
			new Loader_Recipes_Binnie(),
			new Loader_Recipes_BetterRecords(),
			new Loader_Recipes_BalkonsWeaponMod(),
			new Loader_Recipes_OpenModularTurrets(),
			new Loader_Recipes_TechGuns(),
			new Loader_Recipes_Atum(),
			new Loader_Recipes_JABBA(),
			new Loader_Recipes_Factorization(),
			new Loader_Recipes_MineFactoryReloaded(), // Has to be after RC!
			new Loader_Recipes_AppliedEnergistics(),
			new Loader_Recipes_Bluepower(),
			new Loader_Recipes_ProjectRed(),
			new Loader_Recipes_ProjectE(),
			new Loader_Recipes_GrowthCraft(),
			new Loader_Recipes_HarvestCraft(),
			new Loader_Recipes_MoCreatures(),
			new Loader_Recipes_Lycanite(),
			new Loader_Recipes_Erebus(),
			new Loader_Recipes_Betweenlands(),
			new Loader_Recipes_TwilightForest(),
			new Loader_Recipes_Enviromine(),
			new Loader_Recipes_ExtraBiomesXL(),
			new Loader_Recipes_BiomesOPlenty(),
			new Loader_Recipes_Highlands(),
			new Loader_Recipes_Mariculture(),
			new Loader_Recipes_ImmersiveEngineering(),
			new Loader_Recipes_Reika(),
			new Loader_Recipes_Mekanism(),
			new Loader_Recipes_GalactiCraft(),
			new Loader_Recipes_Mystcraft(),
			new Loader_Recipes_Thaumcraft(),
			new Loader_Recipes_ForbiddenMagic(),
			new Loader_Recipes_ArsMagica(),
			new Loader_Recipes_Botania(),
			new Loader_Recipes_Aether(),
			new Loader_Recipes_RandomThings(),
			new Loader_Recipes_ActuallyAdditions(),
			new Loader_Recipes_ExtraUtilities(),
			new Loader_Recipes_WRCBE(),
			
			new Loader_Recipes_Replace(),
			new Loader_Recipes_Copy(),
			new Loader_Recipes_Decomp(),
			new Loader_Recipes_Handlers()
		);
		
		for (Runnable tRunnable : tList) try {
			tRunnable.run();
		} catch(Throwable e) {e.printStackTrace(ERR);}
		
		ItemStack tLignite = ST.make(MD.UB, "ligniteCoal", 1, 0);
		if (ST.valid(tLignite)) CR.remove(tLignite, tLignite, tLignite, tLignite, tLignite, tLignite, tLignite, tLignite, tLignite);
		
		for (Thread tThread : tThreadList) try {tThread.join();} catch (InterruptedException e) {e.printStackTrace(ERR);}
		
		Block tBlock = ST.block(MD.FR, "beehives", NB);
		if (tBlock != NB) {tBlock.setHarvestLevel("scoop", 0); GT_Tool_Scoop.sBeeHiveMaterial = tBlock.getMaterial();}
		
		OUT.println(getModNameForLog() + ": Adding Fake Recipes for NEI");
//      if (IL.FR_Tree_Sapling  .get(1) != null)    RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, new ItemStack[] {IL.FR_Tree_Sapling  .getWildcard(1)}                                , new ItemStack[] {IL.FR_Tree_Sapling   .getWithName(1, "Scanned Sapling"       )}, null                                                    , new FluidStack[] {MT.Honey.liquid(U/20, T)}, null, 500, 2, 0);
//      if (IL.FR_Butterfly     .get(1) != null)    RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, new ItemStack[] {IL.FR_Butterfly     .getWildcard(1)}                                , new ItemStack[] {IL.FR_Butterfly      .getWithName(1, "Scanned Butterfly"     )}, null                                                    , new FluidStack[] {MT.Honey.liquid(U/20, T)}, null, 500, 2, 0);
//      if (IL.FR_Larvae        .get(1) != null)    RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, new ItemStack[] {IL.FR_Larvae        .getWildcard(1)}                                , new ItemStack[] {IL.FR_Larvae         .getWithName(1, "Scanned Larvae"        )}, null                                                    , new FluidStack[] {MT.Honey.liquid(U/20, T)}, null, 500, 2, 0);
//      if (IL.FR_Serum         .get(1) != null)    RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, new ItemStack[] {IL.FR_Serum         .getWildcard(1)}                                , new ItemStack[] {IL.FR_Serum          .getWithName(1, "Scanned Serum"         )}, null                                                    , new FluidStack[] {MT.Honey.liquid(U/20, T)}, null, 500, 2, 0);
//      if (IL.FR_Caterpillar   .get(1) != null)    RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, new ItemStack[] {IL.FR_Caterpillar   .getWildcard(1)}                                , new ItemStack[] {IL.FR_Caterpillar    .getWithName(1, "Scanned Caterpillar"   )}, null                                                    , new FluidStack[] {MT.Honey.liquid(U/20, T)}, null, 500, 2, 0);
//      if (IL.FR_PollenFertile .get(1) != null)    RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, new ItemStack[] {IL.FR_PollenFertile .getWildcard(1)}                                , new ItemStack[] {IL.FR_PollenFertile  .getWithName(1, "Scanned Pollen"        )}, null                                                    , new FluidStack[] {MT.Honey.liquid(U/20, T)}, null, 500, 2, 0);
//      if (IL.IC2_Crop_Seeds   .get(1) != null)    RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, new ItemStack[] {IL.IC2_Crop_Seeds   .getWildcard(1)}                                , new ItemStack[] {IL.IC2_Crop_Seeds    .getWithName(1, "Scanned Seeds"         )}, null                                                    , null, null, 160,  8, 0);
//                                                  RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, new ItemStack[] {ST.make(Items.written_book, 1, W)}                          , new ItemStack[] {IL.Tool_DataStick    .getWithName(1, "Scanned Book Data"     )}, IL.Tool_DataStick.getWithName(1, "Stick to save it to") , null, null, 128, 32, 0);
//                                                  RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, new ItemStack[] {ST.make(Items.filled_map, 1, W)}                                , new ItemStack[] {IL.Tool_DataStick    .getWithName(1, "Scanned Map Data"      )}, IL.Tool_DataStick.getWithName(1, "Stick to save it to") , null, null, 128, 32, 0);
//                                                  RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, new ItemStack[] {IL.Tool_DataOrb     .getWithName(1, "Orb to overwrite")}            , new ItemStack[] {IL.Tool_DataOrb      .getWithName(1, "Copy of the Orb"       )}, IL.Tool_DataOrb.getWithName(0, "Orb to copy")           , null, null, 512, 32, 0);
//                                                  RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, new ItemStack[] {IL.Tool_DataStick   .getWithName(1, "Stick to overwrite")}          , new ItemStack[] {IL.Tool_DataStick    .getWithName(1, "Copy of the Stick"     )}, IL.Tool_DataStick.getWithName(0, "Stick to copy")       , null, null, 128, 32, 0);
		
		for (IItemContainer tBee : new IItemContainer[] {IL.FR_Bee_Drone, IL.FR_Bee_Princess, IL.FR_Bee_Queen}) if (tBee.exists()) {
		for (String tFluid : FluidsGT.HONEY) if (UT.Fluids.exists(tFluid))
		RM.Bumblelyzer.addFakeRecipe(F, new ItemStack[] {tBee.wild(1)}, new ItemStack[] {tBee.getWithName(1, "Scanned Bee")}, null, null, new FluidStack[] {UT.Fluids.make(tFluid, 50)} , null, 64, 16, 0);
		RM.Bumblelyzer.addFakeRecipe(F, new ItemStack[] {tBee.wild(1)}, new ItemStack[] {tBee.getWithName(1, "Scanned Bee")}, null, null, new FluidStack[] {FL.Honeydew.make(50)}       , null, 64, 16, 0);
		}
		for (IItemContainer tPlant : new IItemContainer[] {IL.FR_Tree_Sapling, IL.IC2_Crop_Seeds}) if (tPlant.exists()) {
		RM.Plantalyzer.addFakeRecipe(F, new ItemStack[] {tPlant.wild(1)}, new ItemStack[] {tPlant.getWithName(1, "Scanned Plant")}, null, null, null, null, 64, 16, 0);
		}
		
		for (ItemStack tStack : OreDictManager.getOres("bookWritten", F))
		RM.ScannerVisuals.addFakeRecipe(F, new ItemStack[] {tStack, IL.USB_Stick_1.get(1)}                                              , new ItemStack[] {IL.USB_Stick_1.getWithName(1, "Containing scanned Book"                  ), tStack}, null, null, ZL_FS, ZL_FS, 512, 16, 0);
		RM.ScannerVisuals.addFakeRecipe(F, new ItemStack[] {IL.Paper_Printed_Pages.get(1), IL.USB_Stick_1.get(1)}                       , new ItemStack[] {IL.USB_Stick_1.getWithName(1, "Containing scanned Book"                  ), IL.Paper_Printed_Pages.get(1)}, null, null, ZL_FS, ZL_FS, 512, 16, 0);
		RM.ScannerVisuals.addFakeRecipe(F, new ItemStack[] {IL.Paper_Printed_Pages_Many.get(1), IL.USB_Stick_1.get(1)}                  , new ItemStack[] {IL.USB_Stick_1.getWithName(1, "Containing large scanned Book"            ), IL.Paper_Printed_Pages_Many.get(1)}, null, null, ZL_FS, ZL_FS, 512, 16, 0);
		for (ItemStack tStack : OreDictManager.getOres("gt:canvas", F))
		RM.ScannerVisuals.addFakeRecipe(F, new ItemStack[] {tStack, IL.USB_Stick_1.get(1)}                                              , new ItemStack[] {IL.USB_Stick_1.getWithName(1, "Containing scanned Block"                 ), tStack}, null, null, ZL_FS, ZL_FS, 64, 16, 0);
		RM.ScannerVisuals.addFakeRecipe(F, new ItemStack[] {ST.make(Blocks.crafting_table, 1, 0, "ANY BLOCK"), IL.USB_Stick_1.get(1)}   , new ItemStack[] {IL.USB_Stick_1.getWithName(1, "Containing scanned Block"                 ), ST.make(Blocks.crafting_table, 1, 0, "ANY BLOCK")}, null, null, ZL_FS, ZL_FS, 512, 16, 0);
		RM.ScannerVisuals.addFakeRecipe(F, new ItemStack[] {ST.make(Items.filled_map, 1, W), IL.USB_Stick_1.get(1)}                     , new ItemStack[] {IL.USB_Stick_1.getWithName(1, "Containing scanned Map"                   ), ST.make(Items.filled_map, 1, W)}, null, null, ZL_FS, ZL_FS, 64, 16, 0);
		RM.ScannerVisuals.addFakeRecipe(F, new ItemStack[] {IL.Paper_Blueprint_Used.get(1), IL.USB_Stick_1.get(1)}                      , new ItemStack[] {IL.USB_Stick_1.getWithName(1, "Containing scanned Blueprint"             ), IL.Paper_Blueprint_Used.get(1)}, null, null, ZL_FS, ZL_FS, 64, 16, 0);
		if (IL.GC_Schematic_1.exists())
		RM.ScannerVisuals.addFakeRecipe(F, new ItemStack[] {IL.GC_Schematic_1.wild(1), IL.USB_Stick_1.get(1)}                           , new ItemStack[] {IL.USB_Stick_1.getWithName(1, "Containing scanned Schematics"            ), IL.GC_Schematic_1.wild(1)}, null, null, ZL_FS, ZL_FS, 1024, 16, 0);
		if (IL.GC_Schematic_2.exists())
		RM.ScannerVisuals.addFakeRecipe(F, new ItemStack[] {IL.GC_Schematic_2.wild(1), IL.USB_Stick_1.get(1)}                           , new ItemStack[] {IL.USB_Stick_1.getWithName(1, "Containing scanned Schematics"            ), IL.GC_Schematic_2.wild(1)}, null, null, ZL_FS, ZL_FS, 1024, 16, 0);
		if (IL.GC_Schematic_3.exists())
		RM.ScannerVisuals.addFakeRecipe(F, new ItemStack[] {IL.GC_Schematic_3.wild(1), IL.USB_Stick_1.get(1)}                           , new ItemStack[] {IL.USB_Stick_1.getWithName(1, "Containing scanned Schematics"            ), IL.GC_Schematic_3.wild(1)}, null, null, ZL_FS, ZL_FS, 1024, 16, 0);
		if (IL.IE_Blueprint_Projectiles_Common.exists())
		RM.ScannerVisuals.addFakeRecipe(F, new ItemStack[] {IL.IE_Blueprint_Projectiles_Common.wild(1), IL.USB_Stick_1.get(1)}          , new ItemStack[] {IL.USB_Stick_1.getWithName(1, "Containing scanned Engineer's Blueprint"  ), IL.IE_Blueprint_Projectiles_Common.wild(1)}, null, null, ZL_FS, ZL_FS, 1024, 16, 0);
		
		RM.Printer.addRecipe1(T, 16, 256, ST.make(Items.book, 1, W), DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], NF, ST.book("Manual_Printer", ST.make(Items.written_book, 1, 0)));
		
		for (ItemStack tStack : OreDictManager.getOres("gt:canvas", F))
		RM.Printer.addFakeRecipe(F, new ItemStack[] {tStack                             , IL.USB_Stick_1.getWithName(0, "Containing scanned Block"                  )}, new ItemStack[] {tStack                                     }, null, null, new FluidStack[] {UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Yellow], 1, 9, T), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Magenta], 1, 9, T), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Cyan], 1, 9, T), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, 9, T)}, ZL_FS,   64, 16, 0);
		RM.Printer.addFakeRecipe(F, new ItemStack[] {IL.Paper_Punch_Card_Empty.get(1)   , IL.USB_Stick_1.getWithName(0, "Containing scanned Punchcard"              )}, new ItemStack[] {IL.Paper_Punch_Card_Encoded.get(1)         }, null, null, new FluidStack[] {                                                                                                                                                                                            UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, 9, T)}, ZL_FS,   32, 16, 0);
		RM.Printer.addFakeRecipe(F, new ItemStack[] {IL.Paper_Blueprint_Empty.get(1)    , IL.USB_Stick_1.getWithName(0, "Containing scanned Blueprint"              )}, new ItemStack[] {IL.Paper_Blueprint_Used.get(1)             }, null, null, new FluidStack[] {                                                                                                                                                                                            UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_White], 1, 9, T)}, ZL_FS,   32, 16, 0);
		RM.Printer.addFakeRecipe(F, new ItemStack[] {ST.make(Items.paper, 1, W)         , IL.USB_Stick_1.getWithName(0, "Containing scanned Blueprint"              )}, new ItemStack[] {IL.Paper_Blueprint_Used.get(1)             }, null, null, new FluidStack[] {                                                                                                                                                                                            UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Blue ], 1, 1, T)}, ZL_FS,  128, 16, 0);
		RM.Printer.addFakeRecipe(F, new ItemStack[] {ST.make(Items.paper, 3, W)         , IL.USB_Stick_1.getWithName(0, "Containing scanned Book"                   )}, new ItemStack[] {IL.Paper_Printed_Pages.get(1)              }, null, null, new FluidStack[] {                                                                                                                                                                                            UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, 2, T)}, ZL_FS,  512, 16, 0);
		RM.Printer.addFakeRecipe(F, new ItemStack[] {ST.make(Items.paper, 6, W)         , IL.USB_Stick_1.getWithName(0, "Containing large scanned Book"             )}, new ItemStack[] {IL.Paper_Printed_Pages_Many.get(1)         }, null, null, new FluidStack[] {                                                                                                                                                                                            UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, 1, T)}, ZL_FS, 1024, 16, 0);
		RM.Printer.addFakeRecipe(F, new ItemStack[] {ST.make(Items.map, 1, W)           , IL.USB_Stick_1.getWithName(0, "Containing scanned Map"                    )}, new ItemStack[] {ST.make(Items.filled_map, 1, 0)            }, null, null, new FluidStack[] {UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Yellow], 1, 9, T), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Magenta], 1, 9, T), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Cyan], 1, 9, T), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, 9, T)}, ZL_FS,   64, 16, 0);
		if (IL.GC_Schematic_1.exists())
		RM.Printer.addFakeRecipe(F, new ItemStack[] {ST.make(Items.paper, 8, W)         , IL.USB_Stick_1.getWithName(0, "Containing scanned Schematics"             )}, new ItemStack[] {IL.GC_Schematic_1.wild(1)                  }, null, null, new FluidStack[] {                                                                                                                                                                                            UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 4, 1, T)}, ZL_FS, 2048, 16, 0);
		if (IL.GC_Schematic_2.exists())
		RM.Printer.addFakeRecipe(F, new ItemStack[] {ST.make(Items.paper, 8, W)         , IL.USB_Stick_1.getWithName(0, "Containing scanned Schematics"             )}, new ItemStack[] {IL.GC_Schematic_2.wild(1)                  }, null, null, new FluidStack[] {                                                                                                                                                                                            UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 4, 1, T)}, ZL_FS, 2048, 16, 0);
		if (IL.GC_Schematic_3.exists())
		RM.Printer.addFakeRecipe(F, new ItemStack[] {ST.make(Items.paper, 8, W)         , IL.USB_Stick_1.getWithName(0, "Containing scanned Schematics"             )}, new ItemStack[] {IL.GC_Schematic_3.wild(1)                  }, null, null, new FluidStack[] {                                                                                                                                                                                            UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 4, 1, T)}, ZL_FS, 2048, 16, 0);
		if (IL.IE_Blueprint_Projectiles_Common.exists())
		RM.Printer.addFakeRecipe(F, new ItemStack[] {ST.make(Items.paper, 3, W)         , IL.USB_Stick_1.getWithName(0, "Containing scanned Engineer's Blueprint"   )}, new ItemStack[] {IL.IE_Blueprint_Projectiles_Common.wild(1) }, null, null, new FluidStack[] {                                                                                                                                                                                            UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Blue ], 3, 1, T)}, ZL_FS, 2048, 16, 0);
		
		if (CODE_CLIENT) {
			for (OreDictMaterial aMaterial : OreDictMaterial.ALLOYS) {
				for (IOreDictConfigurationComponent tAlloy : aMaterial.mAlloyCreationRecipes) {
					boolean temp = T;
					ArrayListNoNulls<ItemStack> tDusts = new ArrayListNoNulls<>(), tIngots = new ArrayListNoNulls<>();
					ArrayListNoNulls<Long> tMeltingPoints = new ArrayListNoNulls<>();
					for (OreDictMaterialStack tMaterial : tAlloy.getUndividedComponents()) {
						tMeltingPoints.add(tMaterial.mMaterial.mMeltingPoint);
						if (!tDusts.add(OM.dustOrIngot(tMaterial.mMaterial, tMaterial.mAmount))) {temp = F; break;}
						tIngots.add(OM.ingotOrDust(tMaterial.mMaterial, tMaterial.mAmount));
					}
					Collections.sort(tMeltingPoints);
					if (temp) {
						RM.CrucibleAlloying.addFakeRecipe(F, tDusts .toArray(ZL_IS), new ItemStack[] {OM.ingotOrDust(aMaterial, tAlloy.getCommonDivider() * U)}, null, null, null, null, 0, 0, tMeltingPoints.size()>1?Math.max(tMeltingPoints.get(tMeltingPoints.size()-2), aMaterial.mMeltingPoint):aMaterial.mMeltingPoint);
						RM.CrucibleAlloying.addFakeRecipe(F, tIngots.toArray(ZL_IS), new ItemStack[] {OM.ingotOrDust(aMaterial, tAlloy.getCommonDivider() * U)}, null, null, null, null, 0, 0, tMeltingPoints.size()>1?Math.max(tMeltingPoints.get(tMeltingPoints.size()-2), aMaterial.mMeltingPoint):aMaterial.mMeltingPoint);
					}
				}
			}
			for (OreDictMaterial aMaterial : OreDictMaterial.MATERIAL_ARRAY) if (aMaterial != null) {
				Recipe tRecipe = RecipeMapReplicator.getReplicatorRecipe(aMaterial, IL.USB_Stick_3.getWithName(0, "Mat Data: "+aMaterial.getLocal()));
				if (tRecipe != null) RM.Replicator.addFakeRecipe(F, tRecipe);
			}
		}
		
		for (MultiItemRandom tItem : ItemsGT.ALL_MULTI_ITEMS) for (Entry<Short, ArrayList<IBehavior<MultiItem>>> tEntry : tItem.mItemBehaviors.entrySet()) for (IBehavior<MultiItem> tBehavior : tEntry.getValue()) if (tBehavior instanceof Behavior_Turn_Into) if (((Behavior_Turn_Into)tBehavior).mTurnInto.exists()) tItem.mVisibleItems.set(tEntry.getKey(), F);
	}
	
	@Override
	public void onModServerStarting2(FMLServerStartingEvent aEvent) {
		for (FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) if (tData.filledContainer.getItem() == Items.potionitem && ST.meta(tData.filledContainer) == 0) {tData.fluid.amount = 0; break;}
		
		ORD.println("============================");
		ORD.println("Outputting Unknown Materials");
		ORD.println("============================");
		for (String tUnknown : OreDictManager.INSTANCE.getUnknownMaterials()) ORD.println(tUnknown);
		ORD.println("============================");
		
		if (CODE_CLIENT) {try {
		ORD.println("============================");
		ORD.println("Outputting Colors of unknown Materials");
		ORD.println("============================");
		for (OreDictMaterial tUnknown : OreDictMaterial.MATERIAL_MAP.values()) if (tUnknown != null && tUnknown.contains(TD.Properties.UNUSED_MATERIAL) && !tUnknown.contains(TD.Properties.IGNORE_IN_COLOR_LOG)) {
			for (ItemStackContainer aStack : tUnknown.mRegisteredItems) {
				ItemStack tStack = aStack.toStack();
				if (ST.valid(tStack) && ST.block(aStack) == NB && !(tStack.getItem() instanceof PrefixItem) && !(tStack.getItem() instanceof PrefixBlockItem)) {
					short[] tRGB = UT.Code.color(tStack);
					if (tRGB != null && tRGB != UNCOLOURED) ORD.println(tUnknown.mNameInternal + "  -  RGB: " + tRGB[0]+", "+tRGB[1]+", "+tRGB[2] + "  -  " + ST.names(tStack));
				}
			}
		}
		ORD.println("============================");
		} catch(Throwable e) {e.printStackTrace(ERR);}}
		
		ORD.println("================================");
		ORD.println("Outputting Unknown OreDict Names");
		ORD.println("================================");
		for (String tUnknown : OreDictManager.INSTANCE.getUnknownNames()) ORD.println(tUnknown);
		ORD.println("================================");
		
		/*
		try {((CommandHandler)aEvent.getServer().getCommandManager()).registerCommand(new CommandBase() {
			@Override public String getCommandName() {return "xyzd";}
			@Override public String getCommandUsage(ICommandSender aSender) {return E;}
			@Override public int getRequiredPermissionLevel() {return 0;}
			@Override public boolean canCommandSenderUseCommand(ICommandSender aSender) {return T;}
			@Override public void processCommand(ICommandSender aSender, String[] aParameters) {
				if (aParameters.length >= 3) {
					EntityPlayerMP aPlayer = getCommandSenderAsPlayer(aSender);
					if (aPlayer != null && (aPlayer.username.equals("GregoriusT") || aPlayer.username.equals("Player"))) {
						try {
							if (aPlayer.ridingEntity != null) aPlayer.mountEntity(null);
							if (aPlayer.riddenByEntity != null) aPlayer.riddenByEntity.mountEntity(null);
							
							if (aParameters.length >= 4) {
								GT_Utility.moveEntityToDimensionAtCoords(aPlayer, Integer.parseInt(aParameters[3]), Integer.parseInt(aParameters[0])+0.5, Integer.parseInt(aParameters[1])+0.5, Integer.parseInt(aParameters[2])+0.5);
							} else {
								aPlayer.setPositionAndUpdate(Integer.parseInt(aParameters[0]), Integer.parseInt(aParameters[1]), Integer.parseInt(aParameters[2]));
							}
						} catch(Throwable e) {/*Do nothing}
					}
				}
			}
		});} catch(Throwable e) {/*Do nothing}
		*/
		if (MD.IC2.mLoaded && !MD.IC2C.mLoaded) try {
		if (gregtechproxy.mDisableIC2Ores) Ic2Items.tinOre = Ic2Items.leadOre = Ic2Items.copperOre = Ic2Items.uraniumOre = null;
		} catch (Throwable e) {e.printStackTrace(ERR);}
		if (MD.TE.mLoaded) {
			ItemStack tPyrotheum = OP.dust.mat(MT.Pyrotheum, 1);
			for (ItemStackContainer tStack : OP.ore.mRegisteredItems) CR.remove(tStack.toStack(), tPyrotheum);
		}
	}
	
	@Override
	public void onModServerStopping2(FMLServerStoppingEvent aEvent) {
		try {
		if (D1 || ORD != System.out) {
			ORD.println("*");
			ORD.println("TagData:");
			ORD.println("*"); ORD.println("*"); ORD.println("*");
			
			for (TagData tData : TagData.TAGS) ORD.println(tData.mName);
			
			ORD.println("*");
			ORD.println("ItemRegistry:");
			ORD.println("*"); ORD.println("*"); ORD.println("*");
			
			Object[] tList = Item.itemRegistry.getKeys().toArray();
			
			Arrays.sort(tList);
			for (Object tItemName : tList) ORD.println(tItemName);
			
			ORD.println("*");
			ORD.println("OreDictionary:");
			ORD.println("*"); ORD.println("*"); ORD.println("*");
			
			tList = OreDictionary.getOreNames();
			Arrays.sort(tList);
			for (Object tOreName : tList) {
				int tAmount = OreDictionary.getOres(tOreName.toString()).size();
				if (tAmount > 0) ORD.println((tAmount<10?" ":"") + tAmount + "x " + tOreName);
			}
			
			ORD.println("*");
			ORD.println("Materials:");
			ORD.println("*"); ORD.println("*"); ORD.println("*");
			
			for (OreDictMaterial tMaterial : OreDictMaterial.MATERIAL_ARRAY) if (tMaterial != null) {
				if (tMaterial.mToolTypes > 0) {
					ORD.println(tMaterial.mNameInternal + "; T:" + tMaterial.mToolTypes + "; Q:" + tMaterial.mToolQuality + "; D:" + tMaterial.mToolDurability + "; S:" + tMaterial.mToolSpeed);
				} else {
					ORD.println(tMaterial.mNameInternal);
				}
			}
			
			ORD.println("*");
			ORD.println("Fluids:");
			ORD.println("*"); ORD.println("*"); ORD.println("*");
			
			tList = FluidRegistry.getRegisteredFluids().keySet().toArray(ZL_STRING);
			Arrays.sort(tList);
			for (Object tFluidName : tList) ORD.println(tFluidName);
			
			ORD.println("*"); ORD.println("*"); ORD.println("*");
			ORD.println("Biomes:");
			ORD.println("*"); ORD.println("*"); ORD.println("*");
			
			for (int i = 0; i < BiomeGenBase.getBiomeGenArray().length; i++) {
				if (BiomeGenBase.getBiomeGenArray()[i] != null) ORD.println(BiomeGenBase.getBiomeGenArray()[i].biomeID + " = " + BiomeGenBase.getBiomeGenArray()[i].biomeName);
			}
			
			ORD.println("*"); ORD.println("*"); ORD.println("*");
			ORD.println("Enchantments:");
			ORD.println("*"); ORD.println("*"); ORD.println("*");
			
			for (int i = 0; i < Enchantment.enchantmentsList.length; i++) {
				if (Enchantment.enchantmentsList[i] != null) ORD.println(i + " = " + Enchantment.enchantmentsList[i].getName());
			}
			
			ORD.println("*"); ORD.println("*"); ORD.println("*");
			ORD.println("END GregTech-Debug");
			ORD.println("*"); ORD.println("*"); ORD.println("*");
		}
		} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
	}
	
	@Override public void onModServerStarted2(FMLServerStartedEvent aEvent) {/**/}
	@Override public void onModServerStopped2(FMLServerStoppedEvent aEvent) {/**/}
	
	@Override public String getModID() {return MD.GT.mID;}
	@Override public String getModName() {return MD.GT.mName;}
	@Override public String getModNameForLog() {return "GT_Mod";}
	@Override public Abstract_Proxy getProxy() {return gregtechproxy;}
	
	@Mod.EventHandler public void onPreLoad         (FMLPreInitializationEvent  aEvent) {onModPreInit(aEvent);}
	@Mod.EventHandler public void onLoad            (FMLInitializationEvent     aEvent) {onModInit(aEvent);}
	@Mod.EventHandler public void onPostLoad        (FMLPostInitializationEvent aEvent) {onModPostInit(aEvent);}
	@Mod.EventHandler public void onServerStarting  (FMLServerStartingEvent     aEvent) {onModServerStarting(aEvent);}
	@Mod.EventHandler public void onServerStarted   (FMLServerStartedEvent      aEvent) {onModServerStarted(aEvent);}
	@Mod.EventHandler public void onServerStopping  (FMLServerStoppingEvent     aEvent) {onModServerStopping(aEvent);}
	@Mod.EventHandler public void onServerStopped   (FMLServerStoppedEvent      aEvent) {onModServerStopped(aEvent);}
}
