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

package gregtech;

import static gregapi.data.CS.*;

import java.util.ArrayList;
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
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.block.prefixblock.PrefixBlockItem;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.IItemContainer;
import gregapi.code.ItemStackContainer;
import gregapi.code.TagData;
import gregapi.compat.CompatMods;
import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.CS.FluidsGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.CS.ModIDs;
import gregapi.data.CS.ToolsGT;
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
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.configurations.IOreDictConfigurationComponent;
import gregapi.recipes.Recipe;
import gregapi.recipes.maps.RecipeMapReplicator;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregtech.blocks.fluids.BlockOcean;
import gregtech.compat.*;
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
import ic2.core.Ic2Items;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author Gregorius Techneticies
 */
@Mod(modid=ModIDs.GT, name="GregTech", version="GT6-MC1710", dependencies="required-after:"+ModIDs.GAPI_POST)
public class GT6_Main extends Abstract_Mod {
	@SidedProxy(modId = ModIDs.GT, clientSide = "gregtech.GT_Client", serverSide = "gregtech.GT_Server")
	public static GT_Proxy gt_proxy;
	
	public GT6_Main() {
		GT = this;
		NW_GT = new NetworkHandler(MD.GT.mID, "GREG");
	}
	
	@Override
	public void onModPreInit2(FMLPreInitializationEvent aEvent) {
		try {
			LoadController tLoadController = ((LoadController)UT.Reflection.getFieldContent(Loader.instance(), "modController", T, T));
			List<ModContainer> tModList = tLoadController.getActiveModList(), tNewModsList = new ArrayList<>(tModList.size());
			ModContainer tGregTech = null;
			for (short i = 0; i < tModList.size(); i++) {
				ModContainer tMod = tModList.get(i);
				if (tMod.getModId().equalsIgnoreCase(MD.GT.mID)) tGregTech = tMod; else tNewModsList.add(tMod);
			}
			if (tGregTech != null) tNewModsList.add(tGregTech);
			UT.Reflection.setFieldContent(tLoadController, "activeModList", tNewModsList);
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
		
		gt_proxy.mSkeletonsShootGTArrows = ConfigsGT.GREGTECH.get("general", "SkeletonsShootGTArrows", 16);
		gt_proxy.mFlintChance            = (int)UT.Code.bind(1, 100, ConfigsGT.GREGTECH.get("general", "FlintAndSteelChance", 30));
		gt_proxy.mDisableVanillaOres     = ConfigsGT.GREGTECH.get("general", "DisableVanillaOres"    , T);
		mDisableIC2Ores                  = ConfigsGT.GREGTECH.get("general", "DisableIC2Ores"        , T);
		BlockOcean.SPREAD_TO_AIR         = ConfigsGT.GREGTECH.get("general", "OceanBlocksSpreadToAir", T);
		
		if (ConfigsGT.GREGTECH.get("general", "IncreaseDungeonLoot", T)) {
			ChestGenHooks tChest;
			tChest = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST             ); tChest.setMax(tChest.getMax()+ 8); tChest.setMin(tChest.getMin()+ 4);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST           ); tChest.setMax(tChest.getMax()+12); tChest.setMin(tChest.getMin()+ 6);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST    ); tChest.setMax(tChest.getMax()+ 8); tChest.setMin(tChest.getMin()+ 4);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ); tChest.setMax(tChest.getMax()+16); tChest.setMin(tChest.getMin()+ 8);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER); tChest.setMax(tChest.getMax()+ 2); tChest.setMin(tChest.getMin()+ 1);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR      ); tChest.setMax(tChest.getMax()+ 4); tChest.setMin(tChest.getMin()+ 2);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH      ); tChest.setMax(tChest.getMax()+12); tChest.setMin(tChest.getMin()+ 6);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING     ); tChest.setMax(tChest.getMax()+ 8); tChest.setMin(tChest.getMin()+ 4);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR     ); tChest.setMax(tChest.getMax()+ 6); tChest.setMin(tChest.getMin()+ 3);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY      ); tChest.setMax(tChest.getMax()+16); tChest.setMin(tChest.getMin()+ 8);
		}
		if (ConfigsGT.GREGTECH.get("general", "SmallerVanillaToolDurability", T)) {
			Items.wooden_sword   .setMaxDamage(  8);
			Items.wooden_pickaxe .setMaxDamage(  8);
			Items.wooden_shovel  .setMaxDamage(  8);
			Items.wooden_axe     .setMaxDamage(  8);
			Items.wooden_hoe     .setMaxDamage(  8);
			Items.stone_sword    .setMaxDamage( 16);
			Items.stone_pickaxe  .setMaxDamage( 16);
			Items.stone_shovel   .setMaxDamage( 16);
			Items.stone_axe      .setMaxDamage( 16);
			Items.stone_hoe      .setMaxDamage( 16);
			Items.golden_sword   .setMaxDamage( 32);
			Items.golden_pickaxe .setMaxDamage( 32);
			Items.golden_shovel  .setMaxDamage( 32);
			Items.golden_axe     .setMaxDamage( 32);
			Items.golden_hoe     .setMaxDamage( 32);
			Items.iron_sword     .setMaxDamage(128);
			Items.iron_pickaxe   .setMaxDamage(128);
			Items.iron_shovel    .setMaxDamage(128);
			Items.iron_axe       .setMaxDamage(128);
			Items.iron_hoe       .setMaxDamage(128);
			Items.diamond_sword  .setMaxDamage(512);
			Items.diamond_pickaxe.setMaxDamage(512);
			Items.diamond_shovel .setMaxDamage(512);
			Items.diamond_axe    .setMaxDamage(512);
			Items.diamond_hoe    .setMaxDamage(512);
		}
		
		if (COMPAT_IC2 != null && !MD.IC2C.mLoaded) {
			OUT.println(getModNameForLog() + ": Removing all original Scrapbox Drops.");
			try {
				UT.Reflection.getField("ic2.core.item.ItemScrapbox$Drop", "topChance", T, T).set(null, 0);
				((List<?>)UT.Reflection.getFieldContent(UT.Reflection.getFieldContent("ic2.api.recipe.Recipes", "scrapboxDrops", T, T), "drops", T, T)).clear();
			} catch(Throwable e) {
				e.printStackTrace(ERR);
			}

			OUT.println(getModNameForLog() + ": Adding Scrap with a Weight of 200.0F to the Scrapbox Drops.");
			COMPAT_IC2.scrapbox(200.0F, IL.IC2_Scrap.get(1));
		}

		EntityRegistry.registerModEntity(EntityArrow_Material.class, "GT_Entity_Arrow"       , 1, GT, 160, 1, T);
		EntityRegistry.registerModEntity(EntityArrow_Potion.class  , "GT_Entity_Arrow_Potion", 2, GT, 160, 1, T);

		for (OreDictMaterial tWood : ANY.Wood.mToThis) OP.plate.disableItemGeneration(tWood);
		OP.ingot       .disableItemGeneration(MT.Butter, MT.ButterSalted, MT.Chocolate, MT.Cheese, MT.MeatRaw, MT.MeatCooked, MT.FishRaw, MT.FishCooked, MT.Tofu, MT.SoylentGreen);
		OP.gemChipped  .disableItemGeneration(MT.EnergiumRed, MT.EnergiumCyan);
		OP.gemFlawed   .disableItemGeneration(MT.EnergiumRed, MT.EnergiumCyan);
		OP.gem         .disableItemGeneration(MT.EnergiumRed, MT.EnergiumCyan);
		OP.gemFlawless .disableItemGeneration(MT.EnergiumRed, MT.EnergiumCyan);
		OP.gemExquisite.disableItemGeneration(MT.EnergiumRed, MT.EnergiumCyan);
		OP.gemLegendary.disableItemGeneration(MT.EnergiumRed, MT.EnergiumCyan);


		RM.pulverizing(ST.make(Blocks.cobblestone, 1, W), ST.make(Blocks.sand, 1, 0), null, 0, F);
		RM.pulverizing(ST.make(Blocks.stone      , 1, W), ST.make(Blocks.cobblestone, 1, 0), null, 0, F);
		RM.pulverizing(ST.make(Blocks.gravel     , 1, W), ST.make(Items.flint, 1, 0), OP.dustSmall.mat(MT.Flint, 1), 10, F);
		RM.pulverizing(ST.make(Blocks.furnace    , 1, W), ST.make(Blocks.sand, 6, 0), null, 0, F);
		RM.pulverizing(ST.make(Blocks.lit_furnace, 1, W), ST.make(Blocks.sand, 6, 0), null, 0, F);
		RM.pulverizing(ST.make(Items.bone        , 1, W), IL.Dye_Bonemeal.get(2), IL.Dye_Bonemeal.get(1), 50, T);
		RM.pulverizing(ST.make(Items.blaze_rod   , 1, W), ST.make(Items.blaze_powder, 3, 0), ST.make(Items.blaze_powder, 1, 0), 50, T);
		RM.pulverizing(ST.make(Blocks.pumpkin    , 1, W), ST.make(Items.pumpkin_seeds, 4, 0), null, 0, F);
		RM.pulverizing(ST.make(Items.melon       , 1, W), ST.make(Items.melon_seeds, 1, 0), null, 0, F);
		RM.pulverizing(ST.make(Blocks.wool       , 1, W), ST.make(Items.string, 2, 0), ST.make(Items.string, 1, 0), 50, F);
		
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
		
		new CompatMods(MD.MC, this) {@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {
			// Clearing the AE Grindstone Recipe List, so we don't need to worry about pre-existing Recipes.
			if (MD.AE.mLoaded) AEApi.instance().registries().grinder().getRecipes().clear();
			// We ain't got Water in that Water Bottle. That would be an infinite Water Exploit.
			for (FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) if (tData.filledContainer.getItem() == Items.potionitem && ST.meta_(tData.filledContainer) == 0) {tData.fluid.amount = 0; break;}
			
			ArrayListNoNulls<Runnable> tList = new ArrayListNoNulls<>(F,
				new Loader_BlockResistance(),
				new Loader_Fuels(),
				new Loader_Loot(),
				
				new Loader_Recipes_Furnace(), // has to be before everything else!
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
				
				new Loader_Recipes_Extruder()
			);
			
			for (Runnable tRunnable : tList) try {tRunnable.run();} catch(Throwable e) {e.printStackTrace(ERR);}
		}};
		
		new Compat_Recipes_Ganys                (MD.GAPI          , this);
		new Compat_Recipes_Chisel               (MD.CHSL          , this);
		new Compat_Recipes_FunkyLocomotion      (MD.FUNK          , this);
		new Compat_Recipes_BetterBeginnings     (MD.BB            , this);
		new Compat_Recipes_IndustrialCraft      (MD.IC2           , this);
		new Compat_Recipes_IndustrialCraft_Scrap(MD.IC2           , this);
		new Compat_Recipes_BuildCraft           (MD.BC            , this);
		new Compat_Recipes_Railcraft            (MD.RC            , this); // has to be before MFR!
		new Compat_Recipes_ThermalExpansion     (MD.TE_FOUNDATION , this);
		new Compat_Recipes_Forestry             (MD.FR            , this);
		new Compat_Recipes_MagicBees            (MD.FRMB          , this);
		new Compat_Recipes_Binnie               (MD.BINNIE        , this);
		new Compat_Recipes_BetterRecords        (MD.BETTER_RECORDS, this);
		new Compat_Recipes_BalkonsWeaponMod     (MD.BWM           , this);
		new Compat_Recipes_OpenModularTurrets   (MD.OMT           , this);
		new Compat_Recipes_TechGuns             (MD.TG            , this);
		new Compat_Recipes_Atum                 (MD.ATUM          , this);
		new Compat_Recipes_Tropicraft           (MD.TROPIC        , this);
		new Compat_Recipes_CandyCraft           (MD.CANDY         , this);
		new Compat_Recipes_JABBA                (MD.JABBA         , this);
		new Compat_Recipes_Factorization        (MD.FZ            , this);
		new Compat_Recipes_MineFactoryReloaded  (MD.MFR           , this); // Has to be after RC!
		new Compat_Recipes_AppliedEnergistics   (MD.AE            , this);
		new Compat_Recipes_Bluepower            (MD.BP            , this);
		new Compat_Recipes_ProjectRed           (MD.PR            , this);
		new Compat_Recipes_ProjectE             (MD.PE            , this);
		new Compat_Recipes_OpenComputers        (MD.OC            , this);
		new Compat_Recipes_GrowthCraft          (MD.GrC           , this);
		new Compat_Recipes_HarvestCraft         (MD.HaC           , this);
		new Compat_Recipes_MoCreatures          (MD.MoCr          , this);
		new Compat_Recipes_Lycanites            (MD.LycM          , this);
		new Compat_Recipes_Erebus               (MD.ERE           , this);
		new Compat_Recipes_Betweenlands         (MD.BTL           , this);
		new Compat_Recipes_TwilightForest       (MD.TF            , this);
		new Compat_Recipes_Enviromine           (MD.ENVM          , this);
		new Compat_Recipes_ExtraBiomesXL        (MD.EBXL          , this);
		new Compat_Recipes_BiomesOPlenty        (MD.BoP           , this);
		new Compat_Recipes_Highlands            (MD.HiL           , this);
		new Compat_Recipes_Mariculture          (MD.MaCu          , this);
		new Compat_Recipes_ImmersiveEngineering (MD.IE            , this);
		new Compat_Recipes_Reika                (MD.DRGN          , this);
		new Compat_Recipes_Voltz                (MD.VOLTZ         , this);
		new Compat_Recipes_Mekanism             (MD.Mek           , this);
		new Compat_Recipes_GalactiCraft         (MD.GC            , this);
		new Compat_Recipes_Mystcraft            (MD.MYST          , this);
		new Compat_Recipes_Witchery             (MD.WTCH          , this);
		new Compat_Recipes_Thaumcraft           (MD.TC            , this);
		new Compat_Recipes_ForbiddenMagic       (MD.TCFM          , this);
		new Compat_Recipes_ArsMagica            (MD.ARS           , this);
		new Compat_Recipes_Botania              (MD.BOTA          , this);
		new Compat_Recipes_Aether               (MD.AETHER        , this);
		new Compat_Recipes_RandomThings         (MD.RT            , this);
		new Compat_Recipes_ActuallyAdditions    (MD.AA            , this);
		new Compat_Recipes_ExtraUtilities       (MD.ExU           , this);
		new Compat_Recipes_WRCBE                (MD.WR_CBE_C      , this);
		
		new CompatMods(MD.GT, this) {@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {
			ArrayListNoNulls<Runnable> tList = new ArrayListNoNulls<>(F,
				new Loader_Recipes_Replace(),
				new Loader_Recipes_Copy(),
				new Loader_Recipes_Decomp(),
				new Loader_Recipes_Handlers()
			);
			for (Runnable tRunnable : tList) try {tRunnable.run();} catch(Throwable e) {e.printStackTrace(ERR);}
		}};
	}
	
	@Override
	public void onModInit2(FMLInitializationEvent aEvent) {
		for (FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) if (tData.filledContainer.getItem() == Items.potionitem && ST.meta_(tData.filledContainer) == 0) {tData.fluid.amount = 0; break;}
		
		new Loader_Late_Items_And_Blocks().run();
		
		if (MD.IC2C.mLoaded) for (int i = 0; i <= 6; i++) FMLInterModComms.sendMessage(MD.IC2C.mID, "generatorDrop", ST.save(UT.NBT.makeInt("Key", i), "Value", IL.IC2_Machine.get(1)));
		
		ArrayListNoNulls<Runnable> tList = new ArrayListNoNulls<>(F,
			new Loader_MultiTileEntities(),
			new Loader_Books(),
			new Loader_OreProcessing(),
			new Loader_Worldgen(),
			new Loader_ItemIterator()
		);
		for (Runnable tRunnable : tList) try {tRunnable.run();} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	@Override
	public void onModPostInit2(FMLPostInitializationEvent aEvent) {
		ItemStack tLignite = ST.make(MD.UB, "ligniteCoal", 1, 0);
		if (ST.valid(tLignite)) CR.remove(tLignite, tLignite, tLignite, tLignite, tLignite, tLignite, tLignite, tLignite, tLignite);
		
		Block tBlock = ST.block(MD.FR, "beehives", NB);
		if (tBlock != NB) {tBlock.setHarvestLevel("scoop", 0); GT_Tool_Scoop.sBeeHiveMaterial = tBlock.getMaterial();}
		
//      if (IL.FR_Butterfly     .get(1) != null)    RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, ST.array(IL.FR_Butterfly     .getWildcard(1)}                                , ST.array(IL.FR_Butterfly      .getWithName(1, "Scanned Butterfly"     )}, null                                                    , FL.array(MT.Honey.liquid(U/20, T)}, null, 500, 2, 0);
//      if (IL.FR_Larvae        .get(1) != null)    RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, ST.array(IL.FR_Larvae        .getWildcard(1)}                                , ST.array(IL.FR_Larvae         .getWithName(1, "Scanned Larvae"        )}, null                                                    , FL.array(MT.Honey.liquid(U/20, T)}, null, 500, 2, 0);
//      if (IL.FR_Serum         .get(1) != null)    RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, ST.array(IL.FR_Serum         .getWildcard(1)}                                , ST.array(IL.FR_Serum          .getWithName(1, "Scanned Serum"         )}, null                                                    , FL.array(MT.Honey.liquid(U/20, T)}, null, 500, 2, 0);
//      if (IL.FR_Caterpillar   .get(1) != null)    RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, ST.array(IL.FR_Caterpillar   .getWildcard(1)}                                , ST.array(IL.FR_Caterpillar    .getWithName(1, "Scanned Caterpillar"   )}, null                                                    , FL.array(MT.Honey.liquid(U/20, T)}, null, 500, 2, 0);
//      if (IL.FR_PollenFertile .get(1) != null)    RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, ST.array(IL.FR_PollenFertile .getWildcard(1)}                                , ST.array(IL.FR_PollenFertile  .getWithName(1, "Scanned Pollen"        )}, null                                                    , FL.array(MT.Honey.liquid(U/20, T)}, null, 500, 2, 0);
//                                                  RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, ST.array(IL.Tool_DataOrb     .getWithName(1, "Orb to overwrite")}            , ST.array(IL.Tool_DataOrb      .getWithName(1, "Copy of the Orb"       )}, IL.Tool_DataOrb.getWithName(0, "Orb to copy")           , null, null, 512, 32, 0);
//                                                  RecipeMap.sScannerFakeRecipes.addFakeRecipe(F, ST.array(IL.Tool_DataStick   .getWithName(1, "Stick to overwrite")}          , ST.array(IL.Tool_DataStick    .getWithName(1, "Copy of the Stick"     )}, IL.Tool_DataStick.getWithName(0, "Stick to copy")       , null, null, 128, 32, 0);
		
		for (IItemContainer tBee : new IItemContainer[] {IL.FR_Bee_Drone, IL.FR_Bee_Princess, IL.FR_Bee_Queen}) if (tBee.exists()) {
		for (String tFluid : FluidsGT.HONEY) if (FL.exists(tFluid))
		RM.Bumblelyzer.addFakeRecipe(F, ST.array(tBee.wild(1)), ST.array(tBee.getWithName(1, "Scanned Bee")), null, null, FL.array(FL.make(tFluid, 50)) , null, 64, 16, 0);
		RM.Bumblelyzer.addFakeRecipe(F, ST.array(tBee.wild(1)), ST.array(tBee.getWithName(1, "Scanned Bee")), null, null, FL.array(FL.Honeydew.make(50)), null, 64, 16, 0);
		}
		for (IItemContainer tPlant : new IItemContainer[] {IL.FR_Tree_Sapling, IL.IC2_Crop_Seeds}) if (tPlant.exists()) {
		RM.Plantalyzer.addFakeRecipe(F, ST.array(tPlant.wild(1)), ST.array(tPlant.getWithName(1, "Scanned Plant")), null, null, null, null, 64, 16, 0);
		}
		
		
		for (ItemStack tStack : OreDictManager.getOres("bookWritten", F))
		RM.ScannerVisuals.addFakeRecipe(F, ST.array(tStack, IL.USB_Stick_1.get(1))                                              , ST.array(IL.USB_Stick_1.getWithName(1, "Containing scanned Book"                  ), tStack), null, null, ZL_FS, ZL_FS, 512, 16, 0);
		RM.ScannerVisuals.addFakeRecipe(F, ST.array(IL.Paper_Printed_Pages.get(1), IL.USB_Stick_1.get(1))                       , ST.array(IL.USB_Stick_1.getWithName(1, "Containing scanned Book"                  ), IL.Paper_Printed_Pages.get(1)), null, null, ZL_FS, ZL_FS, 512, 16, 0);
		RM.ScannerVisuals.addFakeRecipe(F, ST.array(IL.Paper_Printed_Pages_Many.get(1), IL.USB_Stick_1.get(1))                  , ST.array(IL.USB_Stick_1.getWithName(1, "Containing large scanned Book"            ), IL.Paper_Printed_Pages_Many.get(1)), null, null, ZL_FS, ZL_FS, 512, 16, 0);
		for (ItemStack tStack : OreDictManager.getOres("gt:canvas", F))
		RM.ScannerVisuals.addFakeRecipe(F, ST.array(tStack, IL.USB_Stick_1.get(1))                                              , ST.array(IL.USB_Stick_1.getWithName(1, "Containing scanned Block"                 ), tStack), null, null, ZL_FS, ZL_FS, 64, 16, 0);
		RM.ScannerVisuals.addFakeRecipe(F, ST.array(ST.make(Blocks.crafting_table, 1, 0, "ANY BLOCK"), IL.USB_Stick_1.get(1))   , ST.array(IL.USB_Stick_1.getWithName(1, "Containing scanned Block"                 ), ST.make(Blocks.crafting_table, 1, 0, "ANY BLOCK")), null, null, ZL_FS, ZL_FS, 512, 16, 0);
		RM.ScannerVisuals.addFakeRecipe(F, ST.array(ST.make(Items.filled_map, 1, W), IL.USB_Stick_1.get(1))                     , ST.array(IL.USB_Stick_1.getWithName(1, "Containing scanned Map"                   ), ST.make(Items.filled_map, 1, W)), null, null, ZL_FS, ZL_FS, 64, 16, 0);
		RM.ScannerVisuals.addFakeRecipe(F, ST.array(IL.Paper_Blueprint_Used.get(1), IL.USB_Stick_1.get(1))                      , ST.array(IL.USB_Stick_1.getWithName(1, "Containing scanned Blueprint"             ), IL.Paper_Blueprint_Used.get(1)), null, null, ZL_FS, ZL_FS, 64, 16, 0);
		if (IL.GC_Schematic_1.exists())
		RM.ScannerVisuals.addFakeRecipe(F, ST.array(IL.GC_Schematic_1.wild(1), IL.USB_Stick_1.get(1))                           , ST.array(IL.USB_Stick_1.getWithName(1, "Containing scanned Schematics"            ), IL.GC_Schematic_1.wild(1)), null, null, ZL_FS, ZL_FS, 1024, 16, 0);
		if (IL.GC_Schematic_2.exists())
		RM.ScannerVisuals.addFakeRecipe(F, ST.array(IL.GC_Schematic_2.wild(1), IL.USB_Stick_1.get(1))                           , ST.array(IL.USB_Stick_1.getWithName(1, "Containing scanned Schematics"            ), IL.GC_Schematic_2.wild(1)), null, null, ZL_FS, ZL_FS, 1024, 16, 0);
		if (IL.GC_Schematic_3.exists())
		RM.ScannerVisuals.addFakeRecipe(F, ST.array(IL.GC_Schematic_3.wild(1), IL.USB_Stick_1.get(1))                           , ST.array(IL.USB_Stick_1.getWithName(1, "Containing scanned Schematics"            ), IL.GC_Schematic_3.wild(1)), null, null, ZL_FS, ZL_FS, 1024, 16, 0);
		if (IL.IE_Blueprint_Projectiles_Common.exists())
		RM.ScannerVisuals.addFakeRecipe(F, ST.array(IL.IE_Blueprint_Projectiles_Common.wild(1), IL.USB_Stick_1.get(1))          , ST.array(IL.USB_Stick_1.getWithName(1, "Containing scanned Engineer's Blueprint"  ), IL.IE_Blueprint_Projectiles_Common.wild(1)), null, null, ZL_FS, ZL_FS, 1024, 16, 0);
		
		RM.Printer.addRecipe1(T, 16, 256, ST.make(Items.book, 1, W), DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], NF, ST.book("Manual_Printer", ST.make(Items.written_book, 1, 0)));
		
		for (ItemStack tStack : OreDictManager.getOres("gt:canvas", F))
		RM.Printer.addFakeRecipe(F, ST.array(tStack                             , IL.USB_Stick_1.getWithName(0, "Containing scanned Block"               )), ST.array(tStack                                    ), null, null, FL.array(FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Yellow], 1, 9, T), FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Magenta], 1, 9, T), FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Cyan], 1, 9, T), FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, 9, T)), ZL_FS,   64, 16, 0);
//      RM.Printer.addFakeRecipe(F, ST.array(IL.Paper_Punch_Card_Empty.get(1)   , IL.USB_Stick_1.getWithName(0, "Containing scanned Punchcard"           )), ST.array(IL.Paper_Punch_Card_Encoded.get(1)        ), null, null, FL.array(                                                                                                                                                                       FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, 9, T)), ZL_FS,   32, 16, 0);
		RM.Printer.addFakeRecipe(F, ST.array(IL.Paper_Blueprint_Empty.get(1)    , IL.USB_Stick_1.getWithName(0, "Containing scanned Blueprint"           )), ST.array(IL.Paper_Blueprint_Used.get(1)            ), null, null, FL.array(                                                                                                                                                                       FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_White], 1, 9, T)), ZL_FS,   32, 16, 0);
		RM.Printer.addFakeRecipe(F, ST.array(ST.make(Items.paper, 1, W)         , IL.USB_Stick_1.getWithName(0, "Containing scanned Blueprint"           )), ST.array(IL.Paper_Blueprint_Used.get(1)            ), null, null, FL.array(                                                                                                                                                                       FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Blue ], 1, 1, T)), ZL_FS,  128, 16, 0);
		RM.Printer.addFakeRecipe(F, ST.array(ST.make(Items.paper, 3, W)         , IL.USB_Stick_1.getWithName(0, "Containing scanned Book"                )), ST.array(IL.Paper_Printed_Pages.get(1)             ), null, null, FL.array(                                                                                                                                                                       FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, 2, T)), ZL_FS,  512, 16, 0);
		RM.Printer.addFakeRecipe(F, ST.array(ST.make(Items.paper, 6, W)         , IL.USB_Stick_1.getWithName(0, "Containing large scanned Book"          )), ST.array(IL.Paper_Printed_Pages_Many.get(1)        ), null, null, FL.array(                                                                                                                                                                       FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, 1, T)), ZL_FS, 1024, 16, 0);
		RM.Printer.addFakeRecipe(F, ST.array(ST.make(Items.map, 1, W)           , IL.USB_Stick_1.getWithName(0, "Containing scanned Map"                 )), ST.array(ST.make(Items.filled_map, 1, 0)           ), null, null, FL.array(FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Yellow], 1, 9, T), FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Magenta], 1, 9, T), FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Cyan], 1, 9, T), FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 1, 9, T)), ZL_FS,   64, 16, 0);
		if (IL.GC_Schematic_1.exists())
		RM.Printer.addFakeRecipe(F, ST.array(ST.make(Items.paper, 8, W)         , IL.USB_Stick_1.getWithName(0, "Containing scanned Schematics"          )), ST.array(IL.GC_Schematic_1.wild(1)                 ), null, null, FL.array(                                                                                                                                                                       FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 4, 1, T)), ZL_FS, 2048, 16, 0);
		if (IL.GC_Schematic_2.exists())
		RM.Printer.addFakeRecipe(F, ST.array(ST.make(Items.paper, 8, W)         , IL.USB_Stick_1.getWithName(0, "Containing scanned Schematics"          )), ST.array(IL.GC_Schematic_2.wild(1)                 ), null, null, FL.array(                                                                                                                                                                       FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 4, 1, T)), ZL_FS, 2048, 16, 0);
		if (IL.GC_Schematic_3.exists())
		RM.Printer.addFakeRecipe(F, ST.array(ST.make(Items.paper, 8, W)         , IL.USB_Stick_1.getWithName(0, "Containing scanned Schematics"          )), ST.array(IL.GC_Schematic_3.wild(1)                 ), null, null, FL.array(                                                                                                                                                                       FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black], 4, 1, T)), ZL_FS, 2048, 16, 0);
		if (IL.IE_Blueprint_Projectiles_Common.exists())
		RM.Printer.addFakeRecipe(F, ST.array(ST.make(Items.paper, 3, W)         , IL.USB_Stick_1.getWithName(0, "Containing scanned Engineer's Blueprint")), ST.array(IL.IE_Blueprint_Projectiles_Common.wild(1)), null, null, FL.array(                                                                                                                                                                       FL.mul(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Blue ], 3, 1, T)), ZL_FS, 2048, 16, 0);
		
		if (IL.IE_Treated_Stairs.exists())  RM.Bath.addFakeRecipe(F, ST.array(ST.make(Blocks.oak_stairs , 1, W)), ST.array(IL.IE_Treated_Stairs.get(1                               )), null, null, FL.array(FL.Oil_Creosote.make( 75)), ZL_FS, 102, 0, 0);
											RM.Bath.addFakeRecipe(F, ST.array(ST.make(Blocks.wooden_slab, 1, W)), ST.array(IL.IE_Treated_Slab  .get(1, IL.Treated_Planks_Slab.get(1))), null, null, FL.array(FL.Oil_Creosote.make( 50)), ZL_FS,  72, 0, 0);
											RM.Bath.addFakeRecipe(F, ST.array(IL.Plank_Slab             .get(1)), ST.array(IL.IE_Treated_Slab  .get(1, IL.Treated_Planks_Slab.get(1))), null, null, FL.array(FL.Oil_Creosote.make( 50)), ZL_FS,  72, 0, 0);
		if (IL.ERE_White_Planks.exists())   RM.Bath.addFakeRecipe(F, ST.array(IL.Plank                  .get(1)), ST.array(IL.ERE_White_Planks .get(1                               )), null, null, FL.array(       DYE_FLUIDS_WATER[DYE_INDEX_White]          ), ZL_FS, 144, 0, 0);
		if (IL.ERE_White_Slab.exists())     RM.Bath.addFakeRecipe(F, ST.array(IL.Plank_Slab             .get(1)), ST.array(IL.ERE_White_Slab   .get(1                               )), null, null, FL.array(FL.mul(DYE_FLUIDS_WATER[DYE_INDEX_White], 1, 2, T)), ZL_FS,  72, 0, 0);
		if (IL.ERE_White_Planks.exists())   RM.Bath.addFakeRecipe(F, ST.array(ST.make(Blocks.planks     , 1, W)), ST.array(IL.ERE_White_Planks .get(1                               )), null, null, FL.array(       DYE_FLUIDS_WATER[DYE_INDEX_White]          ), ZL_FS, 144, 0, 0);
		if (IL.ERE_White_Stairs.exists())   RM.Bath.addFakeRecipe(F, ST.array(ST.make(Blocks.oak_stairs , 1, W)), ST.array(IL.ERE_White_Stairs .get(1                               )), null, null, FL.array(FL.mul(DYE_FLUIDS_WATER[DYE_INDEX_White], 3, 4, T)), ZL_FS, 102, 0, 0);
		if (IL.ERE_White_Slab.exists())     RM.Bath.addFakeRecipe(F, ST.array(ST.make(Blocks.wooden_slab, 1, W)), ST.array(IL.ERE_White_Slab   .get(1                               )), null, null, FL.array(FL.mul(DYE_FLUIDS_WATER[DYE_INDEX_White], 1, 2, T)), ZL_FS,  72, 0, 0);
		
		if (IL.LOOTBAGS_Bag_0.exists())     RM.Unboxinator.addFakeRecipe(F, ST.array(IL.LOOTBAGS_Bag_0.get(1)), ST.array(IL.LOOTBAGS_Bag_0.getWithName(1, "Random Drops depending on Config")), null, ZL_LONG, ZL_FS, ZL_FS, 16, 16, 0);
		if (IL.LOOTBAGS_Bag_1.exists())     RM.Unboxinator.addFakeRecipe(F, ST.array(IL.LOOTBAGS_Bag_1.get(1)), ST.array(IL.LOOTBAGS_Bag_1.getWithName(1, "Random Drops depending on Config")), null, ZL_LONG, ZL_FS, ZL_FS, 16, 16, 0);
		if (IL.LOOTBAGS_Bag_2.exists())     RM.Unboxinator.addFakeRecipe(F, ST.array(IL.LOOTBAGS_Bag_2.get(1)), ST.array(IL.LOOTBAGS_Bag_2.getWithName(1, "Random Drops depending on Config")), null, ZL_LONG, ZL_FS, ZL_FS, 16, 16, 0);
		if (IL.LOOTBAGS_Bag_3.exists())     RM.Unboxinator.addFakeRecipe(F, ST.array(IL.LOOTBAGS_Bag_3.get(1)), ST.array(IL.LOOTBAGS_Bag_3.getWithName(1, "Random Drops depending on Config")), null, ZL_LONG, ZL_FS, ZL_FS, 16, 16, 0);
		if (IL.LOOTBAGS_Bag_4.exists())     RM.Unboxinator.addFakeRecipe(F, ST.array(IL.LOOTBAGS_Bag_4.get(1)), ST.array(IL.LOOTBAGS_Bag_4.getWithName(1, "Random Drops depending on Config")), null, ZL_LONG, ZL_FS, ZL_FS, 16, 16, 0);
		
											RM.BedrockOreList.addFakeRecipe(F, ST.array(ST.make(Blocks.bedrock, 1, W)), ST.array(ST.make(Blocks.cobblestone, 1, 0, "Various Cobblestone Types"), OP.dustImpure.mat(MT.Bedrock, 1)), null, new long[] {9990, 10}, FL.array(FL.lube(100)), null, 0, 0, 0);
		if (IL.BTL_Bedrock.exists())        RM.BedrockOreList.addFakeRecipe(F, ST.array(IL.BTL_Bedrock        .get(1)), ST.array(ST.make(Blocks.cobblestone, 1, 0, "Various Cobblestone Types"), OP.dustImpure.mat(MT.Bedrock, 1)), null, new long[] {9990, 10}, FL.array(FL.lube(100)), null, 0, 0, 0);
		
		
		MultiTileEntityRegistry aRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
		
		RM.Other.addFakeRecipe(F, ST.array(
		  IL.Ceramic_Mold.getWithName(1, "Don't forget to shape the Mold to pour it")
		, IL.Ceramic_Crucible.getWithName(1, "Wait until it all turns into Steel")
		, ST.make(aRegistry.getItem(1302), "Point a running Engine into the Crucible to blow Air")
		, ST.make(OP.ingot.mat(MT.Fe, 1), "Throw Iron into Crucible")
		, ST.make(aRegistry.getItem(1199), "Heat up the Crucible using a Burning Box")
		, ST.make(OP.ingot.mat(MT.WroughtIron, 1), "Or throw Wrought Iron into the Crucible, either works")
		), ST.array(OP.dust.mat(MT.Steel, 1), OP.ingot.mat(MT.Steel, 1), OP.plate.mat(MT.Steel, 1), OP.scrapGt.mat(MT.Steel, 1), OP.stick.mat(MT.Steel, 1), OP.gearGt.mat(MT.Steel, 1)), null, ZL_LONG, FL.array(FL.Air.make(1), FL.Air_Nether.make(1), FL.Air_End.make(1)), ZL_FS, 0, 0, 0);
		
		RM.Other.addFakeRecipe(F, ST.array(
		  ST.make(OP.dust.mat(MT.OREMATS.Cinnabar, 2), "Throw two Units of Cinnabar into Crucible")
		, IL.Ceramic_Crucible.getWithName(1, "Wait until it melts into Mercury")
		, IL.Bottle_Empty.getWithName(1, "Rightclick the Crucible with an Empty Bottle")
		, NI
		, ST.make(aRegistry.getItem(1199), "Heat up the Crucible using a Burning Box")
		, NI
		), ST.array(IL.Bottle_Mercury.get(1)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		RM.Other.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 0, "Find a Rubber Tree in a Taiga Biome or similar")
		, ST.make(BlocksGT.Leaves_AB, 1, 0, "Make sure its natural Leaves stay intact!")
		, ST.make(BlocksGT.LogA, 1, 0, "Look for a possible Resin Hole at the Tree")
		, NI
		, NI
		, IL.Bag_Sap_Resin.getWithName(1, "Place Resin Bag at the Hole")
		), ST.array(IL.Resin.get(1), IL.IC2_Resin.get(1)), null, ZL_LONG, ZL_FS, FL.array(FL.Resin_Rubber.make(250)), 0, 0, 0);
		
		RM.Other.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 1, "Find a Maple Tree in a Forest")
		, ST.make(BlocksGT.Leaves_AB, 1, 1, "Make sure its natural Leaves stay intact!")
		, ST.make(BlocksGT.LogA, 1, 1, "Choose one of the Log Segments at the Base of the Tree")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.HAND_DRILL, "Drill only one Hole into the Tree")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.DRILL_LV  , "Drill only one Hole into the Tree")
		, IL.Bag_Sap_Resin.getWithName(1, "Place Sap Bag at the drilled Hole")
		), ZL_IS, null, ZL_LONG, ZL_FS, FL.array(FL.Sap_Maple.make(250)), 0, 0, 0);
		
		RM.Other.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 2, "Find a Willow Tree in the Swamp")
		, ST.make(BlocksGT.Leaves_AB, 1, 2, "Harvest its Leaves for Sticks")
		, ST.make(BlocksGT.LogA, 1, 2, "Use its Logs in a Coke Oven for double the Charcoal")
		, NI
		, NI
		, NI
		), ST.array(OP.stick.mat(MT.WOODS.Willow, 1), OP.gem.mat(MT.Charcoal, 2), OP.ingot.mat(MT.Charcoal, 2)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		RM.Other.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 3, "Find a Blue Mahoe Tree in the Jungle")
		, ST.make(BlocksGT.Leaves_AB, 1, 3, "Harvest its Leaves for Sticks")
		, ST.make(BlocksGT.LogA, 1, 3, "Nothing special about its Logs")
		, NI
		, NI
		, NI
		), ST.array(OP.stick.mat(MT.WOODS.BlueMahoe, 1)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		RM.Other.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 4, "Find a Hazel Tree in the Plains")
		, ST.make(BlocksGT.Leaves_AB, 1, 4, "Harvest its Leaves for Hazelnuts and Sticks")
		, ST.make(BlocksGT.LogB, 1, 0, "Nothing special about its Logs")
		, NI
		, NI
		, NI
		), ST.array(IL.Food_Hazelnut.get(1), OP.stick.mat(MT.WOODS.Hazel, 1)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		RM.Other.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 5, "Find a Cinnamon Tree in the Jungle")
		, ST.make(BlocksGT.Leaves_AB, 1, 5, "Nothing special about its Leaves")
		, ST.make(BlocksGT.LogB, 1, 1, "The Bark does not regrow! Plant a new Tree for more")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.KNIFE, "Remove its edible Bark")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.AXE  , "Remove its edible Bark")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.SAW  , "Remove its edible Bark")
		), ST.array(OM.dust(MT.Cinnamon), IL.Food_Cinnamon.get(1), IL.HaC_Cinnamon.get(1)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		RM.Other.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 6, "Find a Coconut Tree near the Ocean")
		, ST.make(BlocksGT.Leaves_AB, 1, 6, "Harvest its Leaves for Coconuts")
		, ST.make(BlocksGT.LogB, 1, 2, "Nothing special about its Logs")
		, NI
		, NI
		, NI
		), ST.array(IL.Food_Coconut.get(1)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		RM.Other.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 7, "Find a super rare Rainbow Tree")
		, ST.make(BlocksGT.Leaves_AB, 1, 7, "Make sure its natural Leaves stay intact!")
		, ST.make(BlocksGT.LogB, 1, 3, "Choose one of the Log Segments at the Base of the Tree")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.HAND_DRILL, "Drill only one Hole into the Tree")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.DRILL_LV  , "Drill only one Hole into the Tree")
		, IL.Bag_Sap_Resin.getWithName(1, "Place Sap Bag at the drilled Hole")
		), ZL_IS, null, ZL_LONG, ZL_FS, FL.array(FL.Sap_Rainbow.make(250)), 0, 0, 0);
		
		RM.Other.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_CD, 1, 0, "Find a Blue Spruce Tree in the Mountains")
		, ST.make(BlocksGT.Leaves_CD, 1, 0, "Nothing special about its Leaves")
		, ST.make(BlocksGT.LogC, 1, 0, "Nothing special about its Logs")
		, NI
		, NI
		, NI
		), ZL_IS, null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		
		if (CODE_CLIENT) {
			for (OreDictMaterial aMaterial : OreDictMaterial.ALLOYS) {
				for (IOreDictConfigurationComponent tAlloy : aMaterial.mAlloyCreationRecipes) {
					boolean temp = T, tAddSpecial = F;
					ArrayListNoNulls<ItemStack> tDusts = new ArrayListNoNulls<>(), tIngots = new ArrayListNoNulls<>(), tSpecial = new ArrayListNoNulls<>();
					ArrayListNoNulls<Long> tMeltingPoints = new ArrayListNoNulls<>();
					for (OreDictMaterialStack tMaterial : tAlloy.getUndividedComponents()) {
						boolean tAddedSpecial = F;
						if (tMaterial.mMaterial.mHidden) {temp = F; break;}
						if (tMaterial.mMaterial == MT.Air) {
							tDusts .add(FL.Air.display(UT.Code.units(tMaterial.mAmount, U, 1000, T)));
							tIngots.add(FL.Air.display(UT.Code.units(tMaterial.mAmount, U, 1000, T)));
							continue;
						}
						if (tMaterial.mMaterial == MT.OREMATS.Magnetite          ) {tAddedSpecial = tSpecial.add(ST.make(BlocksGT.Sands, UT.Code.divup(tMaterial.mAmount, U), 0));} else
						if (tMaterial.mMaterial == MT.OREMATS.BasalticMineralSand) {tAddedSpecial = tSpecial.add(ST.make(BlocksGT.Sands, UT.Code.divup(tMaterial.mAmount, U), 1));} else
						if (tMaterial.mMaterial == MT.OREMATS.GraniticMineralSand) {tAddedSpecial = tSpecial.add(ST.make(BlocksGT.Sands, UT.Code.divup(tMaterial.mAmount, U), 2));} else
						if (tMaterial.mMaterial == MT.C                          ) {tAddedSpecial = tSpecial.add(OM.dustOrIngot(MT.Coal, tMaterial.mAmount * 2));}
						if (tMaterial.mMaterial == MT.CaCO3                      ) {tAddedSpecial = tSpecial.add(OM.dustOrIngot(MT.STONES.Limestone, tMaterial.mAmount * 2));}
						
						tMeltingPoints.add(tMaterial.mMaterial.mMeltingPoint);
						ItemStack tDust = OM.dustOrIngot(tMaterial.mMaterial, tMaterial.mAmount);
						if (!tDusts.add(tDust)) {temp = F; break;}
						tIngots.add(OM.ingotOrDust(tMaterial.mMaterial, tMaterial.mAmount));
						if (tAddedSpecial) tAddSpecial = T; else tSpecial.add(tDust);
					}
					Collections.sort(tMeltingPoints);
					if (temp) {
						RM.CrucibleAlloying.addFakeRecipe(F, tDusts  .toArray(ZL_IS), ST.array(OM.ingotOrDust(aMaterial, tAlloy.getCommonDivider() * U)), null, null, null, null, 0, 0, tMeltingPoints.size()>1?Math.max(tMeltingPoints.get(tMeltingPoints.size()-2), aMaterial.mMeltingPoint):aMaterial.mMeltingPoint);
						RM.CrucibleAlloying.addFakeRecipe(F, tIngots .toArray(ZL_IS), ST.array(OM.ingotOrDust(aMaterial, tAlloy.getCommonDivider() * U)), null, null, null, null, 0, 0, tMeltingPoints.size()>1?Math.max(tMeltingPoints.get(tMeltingPoints.size()-2), aMaterial.mMeltingPoint):aMaterial.mMeltingPoint);
						if (tAddSpecial)
						RM.CrucibleAlloying.addFakeRecipe(F, tSpecial.toArray(ZL_IS), ST.array(OM.ingotOrDust(aMaterial, tAlloy.getCommonDivider() * U)), null, null, null, null, 0, 0, tMeltingPoints.size()>1?Math.max(tMeltingPoints.get(tMeltingPoints.size()-2), aMaterial.mMeltingPoint):aMaterial.mMeltingPoint);
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
		for (FluidContainerData tData : FluidContainerRegistry.getRegisteredFluidContainerData()) if (tData.filledContainer.getItem() == Items.potionitem && ST.meta_(tData.filledContainer) == 0) {tData.fluid.amount = 0; break;}
		
		
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
				if (ST.valid(tStack) && ST.block(tStack) == NB && !(tStack.getItem() instanceof PrefixItem) && !(tStack.getItem() instanceof PrefixBlockItem)) {
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
		if (MD.IC2.mLoaded && !MD.IC2C.mLoaded) try {if (mDisableIC2Ores) Ic2Items.tinOre = Ic2Items.leadOre = Ic2Items.copperOre = Ic2Items.uraniumOre = null;} catch (Throwable e) {e.printStackTrace(ERR);}
		if (MD.TE.mLoaded) {
			ItemStack tPyrotheum = OP.dust.mat(MT.Pyrotheum, 1);
			for (ItemStackContainer tStack : OP.ore.mRegisteredItems) CR.remove(tStack.toStack(), tPyrotheum);
		}
	}
	
	public boolean mDisableIC2Ores = T;
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
			
			List tList = UT.Code.getWithoutNulls(Item.itemRegistry.getKeys().toArray(ZL_STRING));
			
			Collections.sort(tList);
			for (Object tItemName : tList) ORD.println(tItemName);
			
			ORD.println("*");
			ORD.println("OreDictionary:");
			ORD.println("*"); ORD.println("*"); ORD.println("*");
			
			tList = UT.Code.getWithoutNulls(OreDictionary.getOreNames());
			Collections.sort(tList);
			for (Object tOreName : tList) {
				int tAmount = OreDictionary.getOres(tOreName.toString()).size();
				if (tAmount > 0) ORD.println((tAmount<10?" ":"") + tAmount + "x " + tOreName);
			}
			
			ORD.println("*");
			ORD.println("Materials:");
			ORD.println("*"); ORD.println("*"); ORD.println("*");
			
			for (int i = 0; i < OreDictMaterial.MATERIAL_ARRAY.length; i++) {
				OreDictMaterial tMaterial = OreDictMaterial.MATERIAL_ARRAY[i];
				if (tMaterial == null) {
					if (i >= 8000 && i < 10000) {
						ORD.println(i + ": <RESERVED>");
					}
				} else {
					if (tMaterial.mToolTypes > 0) {
						ORD.println(i + ": " + tMaterial.mNameInternal + "; T:" + tMaterial.mToolTypes + "; Q:" + tMaterial.mToolQuality + "; D:" + tMaterial.mToolDurability + "; S:" + tMaterial.mToolSpeed);
					} else {
						ORD.println(i + ": " + tMaterial.mNameInternal);
					}
				}
			}
			
			ORD.println("*");
			ORD.println("Fluids:");
			ORD.println("*"); ORD.println("*"); ORD.println("*");
			
			tList = UT.Code.getWithoutNulls(FluidRegistry.getRegisteredFluids().keySet().toArray(ZL_STRING));
			Collections.sort(tList);
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
		} catch(Throwable e) {e.printStackTrace(ERR);}
	}

	@Override public void onModServerStarted2(FMLServerStartedEvent aEvent) {/**/}
	@Override public void onModServerStopped2(FMLServerStoppedEvent aEvent) {/**/}

	@Override public String getModID() {return MD.GT.mID;}
	@Override public String getModName() {return MD.GT.mName;}
	@Override public String getModNameForLog() {return "GT_Mod";}
	@Override public Abstract_Proxy getProxy() {return gt_proxy;}

	@Mod.EventHandler public void onPreLoad         (FMLPreInitializationEvent  aEvent) {onModPreInit(aEvent);}
	@Mod.EventHandler public void onLoad            (FMLInitializationEvent     aEvent) {onModInit(aEvent);}
	@Mod.EventHandler public void onPostLoad        (FMLPostInitializationEvent aEvent) {onModPostInit(aEvent);}
	@Mod.EventHandler public void onServerStarting  (FMLServerStartingEvent     aEvent) {onModServerStarting(aEvent);}
	@Mod.EventHandler public void onServerStarted   (FMLServerStartedEvent      aEvent) {onModServerStarted(aEvent);}
	@Mod.EventHandler public void onServerStopping  (FMLServerStoppingEvent     aEvent) {onModServerStopping(aEvent);}
	@Mod.EventHandler public void onServerStopped   (FMLServerStoppedEvent      aEvent) {onModServerStopped(aEvent);}
}
