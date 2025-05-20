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

package gregtech.loaders.c;

import gregapi.data.*;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.wooddict.SaplingEntry;
import gregapi.wooddict.WoodDictionary;
import gregtech.tileentity.placeables.MultiTileEntityCoin;
import gregtech.worldgen.ChestGenHooksChestReplacer;
import gregtech.worldgen.TwilightTreasureReplacer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import twilightforest.TFTreasure;

import static gregapi.data.CS.*;
import static gregapi.data.TD.Properties.RANDOM_SMALL_GEM_ORE;


public class Loader_Loot implements Runnable {
	@Override
	public void run() {
		new ChestGenHooksChestReplacer(ChestGenHooks.DUNGEON_CHEST       , 32745);
		new ChestGenHooksChestReplacer(ChestGenHooks.MINESHAFT_CORRIDOR  ,   500);
		new ChestGenHooksChestReplacer(ChestGenHooks.STRONGHOLD_LIBRARY  ,   508);
		new ChestGenHooksChestReplacer(ChestGenHooks.STRONGHOLD_CROSSING ,   509);
		new ChestGenHooksChestReplacer(ChestGenHooks.STRONGHOLD_CORRIDOR ,   510);
		new ChestGenHooksChestReplacer(ChestGenHooks.PYRAMID_DESERT_CHEST,     2);
		new ChestGenHooksChestReplacer(ChestGenHooks.PYRAMID_JUNGLE_CHEST,   502);
		if (!MD.VN4.mLoaded)
		new ChestGenHooksChestReplacer(ChestGenHooks.VILLAGE_BLACKSMITH  ,    24);
		
		if (MD.TF.mLoaded) {
			TFTreasure.hill1             = TwilightTreasureReplacer.create(TFTreasure.hill1            ,  1, "hill1"            , ChestGenHooks.MINESHAFT_CORRIDOR      ,   509);
			TFTreasure.hill2             = TwilightTreasureReplacer.create(TFTreasure.hill2            ,  2, "hill2"            , ChestGenHooks.MINESHAFT_CORRIDOR      ,   558);
			TFTreasure.hill3             = TwilightTreasureReplacer.create(TFTreasure.hill3            ,  3, "hill3"            , ChestGenHooks.MINESHAFT_CORRIDOR      ,    58);
			TFTreasure.hedgemaze         = TwilightTreasureReplacer.create(TFTreasure.hedgemaze        ,  4, "hedgemaze"        , ChestGenHooks.BONUS_CHEST             ,   516);
			TFTreasure.tree_cache        = TwilightTreasureReplacer.create(TFTreasure.tree_cache       , 14, "tree_cache"       , ChestGenHooks.BONUS_CHEST             ,   508);
			TFTreasure.basement          = TwilightTreasureReplacer.create(TFTreasure.basement         ,  9, "basement"         , ChestGenHooks.STRONGHOLD_CROSSING     ,     9);
			TFTreasure.labyrinth_room    = TwilightTreasureReplacer.create(TFTreasure.labyrinth_room   ,  5, "labyrinth_room"   , ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,     8);
			TFTreasure.labyrinth_deadend = TwilightTreasureReplacer.create(TFTreasure.labyrinth_deadend,  6, "labyrinth_deadend", ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   508);
			TFTreasure.labyrinth_vault   = TwilightTreasureReplacer.create(TFTreasure.labyrinth_vault  , 10, "labyrinth_vault"  , ChestGenHooks.VILLAGE_BLACKSMITH      ,    16);
			TFTreasure.tower_room        = TwilightTreasureReplacer.create(TFTreasure.tower_room       ,  7, "tower_room"       , ChestGenHooks.PYRAMID_DESERT_CHEST    ,     2);
			TFTreasure.tower_library     = TwilightTreasureReplacer.create(TFTreasure.tower_library    ,  8, "tower_library"    , ChestGenHooks.STRONGHOLD_LIBRARY      ,   502);
			TFTreasure.darktower_cache   = TwilightTreasureReplacer.create(TFTreasure.darktower_cache  , 11, "darktower_cache"  , ChestGenHooks.STRONGHOLD_CORRIDOR     ,   525);
			TFTreasure.darktower_key     = TwilightTreasureReplacer.create(TFTreasure.darktower_key    , 12, "darktower_key"    , ChestGenHooks.DUNGEON_CHEST           ,    25);
			TFTreasure.darktower_boss    = TwilightTreasureReplacer.create(TFTreasure.darktower_boss   , 13, "darktower_boss"   , ChestGenHooks.PYRAMID_JUNGLE_DISPENSER,    59);
			TFTreasure.stronghold_cache  = TwilightTreasureReplacer.create(TFTreasure.stronghold_cache , 15, "stronghold_cache" , ChestGenHooks.STRONGHOLD_CORRIDOR     ,   510);
			TFTreasure.stronghold_room   = TwilightTreasureReplacer.create(TFTreasure.stronghold_room  , 16, "stronghold_room"  , ChestGenHooks.DUNGEON_CHEST           ,    10);
			TFTreasure.stronghold_boss   = TwilightTreasureReplacer.create(TFTreasure.stronghold_boss  , 17, "stronghold_boss"  , ChestGenHooks.VILLAGE_BLACKSMITH      ,   559);
			TFTreasure.aurora_cache      = TwilightTreasureReplacer.create(TFTreasure.aurora_cache     , 18, "aurora_cache"     , ChestGenHooks.DUNGEON_CHEST           ,    46);
			TFTreasure.aurora_room       = TwilightTreasureReplacer.create(TFTreasure.aurora_room      , 19, "aurora_room"      , ChestGenHooks.DUNGEON_CHEST           ,    13);
		////TFTreasure.aurora_boss       = TwilightTreasureReplacer.create(TFTreasure.aurora_boss      , 20, "aurora_boss"      , ChestGenHooks.DUNGEON_CHEST           , 32745);// This one is actually empty and unused.
			TFTreasure.troll_garden      = TwilightTreasureReplacer.create(TFTreasure.troll_garden     , 21, "troll_garden"     , ChestGenHooks.DUNGEON_CHEST           ,   524);
			TFTreasure.troll_vault       = TwilightTreasureReplacer.create(TFTreasure.troll_vault      , 22, "troll_vault"      , ChestGenHooks.VILLAGE_BLACKSMITH      ,    24);
		}
		
		
		ST.LOOT_TABLES.add("gt.flawless");
		ChestGenHooks.getInfo("gt.flawless").setMin( 8);
		ChestGenHooks.getInfo("gt.flawless").setMax(24);
		addLoot("gt.flawless",2160, 1, 1, OP.gemFlawless.mat(MT.Diamond       , 1));
		addLoot("gt.flawless", 144, 1, 1, OP.gemFlawless.mat(MT.DiamondPink   , 1));
		addLoot("gt.flawless",1152, 1, 1, OP.gemFlawless.mat(MT.Emerald       , 1));
		addLoot("gt.flawless", 432, 1, 1, OP.gemFlawless.mat(MT.Aquamarine    , 1));
		addLoot("gt.flawless", 144, 1, 1, OP.gemFlawless.mat(MT.Morganite     , 1));
		addLoot("gt.flawless", 144, 1, 1, OP.gemFlawless.mat(MT.Heliodor      , 1));
		addLoot("gt.flawless", 144, 1, 1, OP.gemFlawless.mat(MT.Goshenite     , 1));
		addLoot("gt.flawless", 144, 1, 1, OP.gemFlawless.mat(MT.Bixbite       , 1));
		addLoot("gt.flawless", 144, 1, 1, OP.gemFlawless.mat(MT.Maxixe        , 1));
		addLoot("gt.flawless", 720, 1, 1, OP.gemFlawless.mat(MT.Ruby          , 1));
		addLoot("gt.flawless", 576, 1, 1, OP.gemFlawless.mat(MT.BlueSapphire  , 1));
		addLoot("gt.flawless", 576, 1, 1, OP.gemFlawless.mat(MT.GreenSapphire , 1));
		addLoot("gt.flawless", 144, 1, 1, OP.gemFlawless.mat(MT.PurpleSapphire, 1));
		addLoot("gt.flawless", 144, 1, 1, OP.gemFlawless.mat(MT.YellowSapphire, 1));
		addLoot("gt.flawless", 144, 1, 1, OP.gemFlawless.mat(MT.OrangeSapphire, 1));
		addLoot("gt.flawless", 144, 1, 1, OP.gemFlawless.mat(MT.Craponite     , 1));
		addLoot("gt.flawless", 576, 1, 1, OP.gemFlawless.mat(MT.Amethyst      , 1));
		addLoot("gt.flawless", 576, 1, 1, OP.gemFlawless.mat(MT.Amber         , 1));
		addLoot("gt.flawless", 576, 1, 1, OP.gemFlawless.mat(MT.Jade          , 1));
		addLoot("gt.flawless", 432, 1, 1, OP.gemFlawless.mat(MT.Redstone      , 1));
		
		
		ST.LOOT_TABLES.add("gt.gems");
		ChestGenHooks.getInfo("gt.gems").setMin( 8);
		ChestGenHooks.getInfo("gt.gems").setMax(24);
		addLoot("gt.gems",9216, 1, 4, OP.gem       .mat(MT.Emerald    , 1));
		addLoot("gt.gems",2160, 1, 4, OP.gem       .mat(MT.Diamond    , 1));
		addLoot("gt.gems",2160, 2, 8, OP.gemFlawed .mat(MT.Diamond    , 1));
		addLoot("gt.gems",2160, 4,16, OP.gemChipped.mat(MT.Diamond    , 1));
		addLoot("gt.gems", 144, 1, 4, OP.gem       .mat(MT.DiamondPink, 1));
		addLoot("gt.gems", 144, 2, 8, OP.gemFlawed .mat(MT.DiamondPink, 1));
		addLoot("gt.gems", 144, 4,16, OP.gemChipped.mat(MT.DiamondPink, 1));
		addLoot("gt.gems", 144, 1, 4, OP.gem       .mat(MT.Craponite  , 1));
		addLoot("gt.gems", 144, 2, 8, OP.gemFlawed .mat(MT.Craponite  , 1));
		addLoot("gt.gems", 144, 4,16, OP.gemChipped.mat(MT.Craponite  , 1));
		addLoot("gt.gems", 144, 1, 4, OP.gem       .mat(MT.Amber      , 1));
		addLoot("gt.gems", 144, 2, 8, OP.gemFlawed .mat(MT.Amber      , 1));
		addLoot("gt.gems", 144, 4,16, OP.gemChipped.mat(MT.Amber      , 1));
		for (OreDictMaterial tMaterial : OreDictMaterial.MATERIAL_ARRAY) if (tMaterial != null && tMaterial.contains(RANDOM_SMALL_GEM_ORE)) {
		addLoot("gt.gems", 144, 1, 4, OP.gem       .mat(tMaterial, 1), T);
		addLoot("gt.gems", 144, 2, 8, OP.gemFlawed .mat(tMaterial, 1), F);
		addLoot("gt.gems", 144, 4,16, OP.gemChipped.mat(tMaterial, 1), F);
		}
		
		
		ST.LOOT_TABLES.add("gt.misc");
		ChestGenHooks.getInfo("gt.misc").setMin( 8);
		ChestGenHooks.getInfo("gt.misc").setMax(24);
		addLoot("gt.misc", 144, 1, 4, ST.make(Items.name_tag      , 1, 0));
		addLoot("gt.misc", 144, 2, 8, ST.make(Items.leather       , 1, 0));
		addLoot("gt.misc", 144, 2, 8, ST.make(Items.flint         , 1, 0));
		addLoot("gt.misc",  13, 1, 1, ST.make(Items.record_13     , 1, 0));
		addLoot("gt.misc",  13, 1, 1, ST.make(Items.record_cat    , 1, 0));
		addLoot("gt.misc",  13, 1, 1, ST.make(Items.record_blocks , 1, 0));
		addLoot("gt.misc",  13, 1, 1, ST.make(Items.record_chirp  , 1, 0));
		addLoot("gt.misc",  13, 1, 1, ST.make(Items.record_far    , 1, 0));
		addLoot("gt.misc",  13, 1, 1, ST.make(Items.record_mall   , 1, 0));
		addLoot("gt.misc",  13, 1, 1, ST.make(Items.record_mellohi, 1, 0));
		addLoot("gt.misc",  13, 1, 1, ST.make(Items.record_stal   , 1, 0));
		addLoot("gt.misc",  13, 1, 1, ST.make(Items.record_strad  , 1, 0));
		addLoot("gt.misc",  13, 1, 1, ST.make(Items.record_ward   , 1, 0));
		addLoot("gt.misc",  13, 1, 1, ST.make(Items.record_11     , 1, 0));
		addLoot("gt.misc",  13, 1, 1, ST.make(Items.record_wait   , 1, 0));
		addLoot("gt.misc", 144, 3,12, OP.billet    .mat(MT.Nd             , 1));
		addLoot("gt.misc", 144, 3,12, OP.billet    .mat(MT.Cr             , 1));
		addLoot("gt.misc", 144, 3,12, OP.billet    .mat(MT.Mn             , 1));
		addLoot("gt.misc", 144, 3,12, OP.billet    .mat(MT.Ni             , 1));
		addLoot("gt.misc", 144, 3,12, OP.billet    .mat(MT.Sb             , 1));
		addLoot("gt.misc", 144, 3,12, OP.billet    .mat(MT.Sn             , 1));
		addLoot("gt.misc", 144, 3,12, OP.billet    .mat(MT.Zn             , 1));
		addLoot("gt.misc", 144, 3,12, OP.billet    .mat(MT.Cu             , 1));
		addLoot("gt.misc", 144, 3,12, OP.billet    .mat(MT.Ag             , 1));
		addLoot("gt.misc", 144, 3,12, OP.billet    .mat(MT.Au             , 1));
		addLoot("gt.misc", 144, 3,12, OP.billet    .mat(MT.Pt             , 1));
		addLoot("gt.misc", 144, 3,12, OP.billet    .mat(MT.Pb             , 1));
		addLoot("gt.misc", 144, 3,12, OP.dust      .mat(MT.Redstone       , 1));
		addLoot("gt.misc", 144, 3,12, OP.rockGt    .mat(MT.STONES.SkyStone, 1));
		addLoot("gt.misc", 144, 1, 2, OP.dust      .mat(MT.OREMATS.Zeolite, 1));
		addLoot("gt.misc",  72, 1, 4, OP.rockGt    .mat(MT.MeteoricIron   , 1));
		addLoot("gt.misc",  72, 1, 1, OP.oreRaw    .mat(MT.MeteoricIron   , 1));
		addLoot("gt.misc",  36, 2, 8, OP.stick     .mat(MT.Blizz          , 1));
		addLoot("gt.misc",  36, 2, 8, OP.stick     .mat(MT.Blitz          , 1));
		addLoot("gt.misc",  36, 2, 8, OP.stick     .mat(MT.Basalz         , 1));
		addLoot("gt.misc",  36, 2, 8, OP.stick     .mat(MT.Breeze         , 1));
		addLoot("gt.misc",  48, 2, 8, IL.Food_Can_Undefined_6.get(1));
		addLoot("gt.misc",  48, 2, 8, IL.Food_Can_Bread_6.get(1));
		addLoot("gt.misc",  48, 2, 8, IL.Food_Can_Chum_4.get(1));
		addLoot("gt.misc", 144, 3,12, IL.Dynamite.get(1));
		addLoot("gt.misc", 144, 1, 4, IL.Tool_MatchBox_Full.get(1));
		addLoot("gt.misc",  33, 1, 1, IL.Tool_Lighter_Invar_Full.get(1));
		addLoot("gt.misc",   3, 1, 1, IL.Tool_Lighter_Platinum_Full.get(1));
		addLoot("gt.misc",  36, 1, 1, IL.Porcelain_Cup.get(1));
		addLoot("gt.misc",  36, 2, 4, IL.Pill_Cure_All.get(1));
		addLoot("gt.misc",  36, 1, 2, OP.chemtube.mat(MT.Mcg, 1));
		
		
		ST.LOOT_TABLES.add("gt.saplings");
		ChestGenHooks.getInfo("gt.saplings").setMin( 8);
		ChestGenHooks.getInfo("gt.saplings").setMax(24);
		// Well, sometimes the Tree does not survive. XD
		addLoot("gt.saplings", 288, 1, 4, ST.make(Blocks.deadbush, 1, 0));
		addLoot("gt.saplings", 288, 1, 4, ST.make(Blocks.tallgrass, 1, 0));
		// Yes I count those as Saplings too, they are kinda Tree alike. Better than putting these in the Seed Bags.
		addLoot("gt.saplings", 144, 1, 1, ST.make(Blocks.double_plant, 1, 0));
		addLoot("gt.saplings", 144, 1, 1, ST.make(Blocks.double_plant, 1, 1));
		addLoot("gt.saplings", 144, 1, 1, ST.make(Blocks.double_plant, 1, 2));
		addLoot("gt.saplings", 144, 1, 1, ST.make(Blocks.double_plant, 1, 3));
		addLoot("gt.saplings", 144, 1, 1, ST.make(Blocks.double_plant, 1, 4));
		addLoot("gt.saplings", 144, 1, 1, ST.make(Blocks.double_plant, 1, 5));
		addLoot("gt.saplings", 144, 1, 1, ST.make(Blocks.brown_mushroom, 1, 0));
		addLoot("gt.saplings", 144, 1, 1, ST.make(Blocks.red_mushroom, 1, 0));
		addLoot("gt.saplings", 144, 1, 1, ST.make(Blocks.cactus, 1, 0));
		addLoot("gt.saplings", 144, 1, 1, ST.make(Items.reeds, 1, 0));
		// Iterate the Entire List of Saplings and pick the ones from Mods that are Overworld focussed.
		for (SaplingEntry tEntry : WoodDictionary.LIST_SAPLINGS) {
		if (MD.MC  .owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		if (MD.EtFu.owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		if (MD.GT  .owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		if (MD.IC2 .owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		if (MD.IC2C.owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		if (MD.MFR .owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		if (MD.TC  .owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		if (MD.WTCH.owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		if (MD.EBXL.owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		if (MD.EB  .owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		if (MD.BoP .owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		if (MD.HiL .owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		if (MD.HaC .owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		if (MD.MF2 .owns(tEntry.mSapling)) {if (addLoot("gt.saplings", 144, tEntry.mCount, tEntry.mCount, ST.validMeta(tEntry.mSapling))) tEntry.mBaggedSaplingLoot = T; continue;}
		}
		
		
		ST.LOOT_TABLES.add("gt.seeds");
		ChestGenHooks.getInfo("gt.seeds").setMin( 8);
		ChestGenHooks.getInfo("gt.seeds").setMax(24);
		// These don't have Seeds, but what can I do when they are kinda needed in this List...
		addLoot("gt.seeds", 144, 4,12, ST.make(Items.potato, 1, 0));
		addLoot("gt.seeds", 144, 4,12, ST.make(Items.carrot, 1, 0));
		addLoot("gt.seeds", 144, 4,12, IL.Dye_Cocoa.get(1));
		for (int i = 0; i < 16; i++)
		addLoot("gt.seeds",   9, 8,24, ST.make(BlocksGT.Glowtus, 1, i));
		addLoot("gt.seeds", 144, 8,24, ST.make(Blocks.waterlily, 1, 0));
		// Actual Seeds.
		addLoot("gt.seeds", 144,16,48, ST.make(Items.wheat_seeds, 1, 0));
		addLoot("gt.seeds", 144,16,48, ST.make(Items.pumpkin_seeds, 1, 0));
		addLoot("gt.seeds", 144,16,48, ST.make(Items.melon_seeds, 1, 0));
		addLoot("gt.seeds", 144,16,48, IL.EtFu_Beet_Seeds.get(1), F);
		addLoot("gt.seeds", 144,16,48, IL.GaSu_Beet_Seeds.get(1), F);
		addLoot("gt.seeds", 144,16,48, IL.BoP_Turnip_Seeds.get(1), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.GaSu, "camelliaSeeds"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.GaNe, "witherShrubSeeds"       , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.GaNe, "ghostSeeds"             , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.GaNe, "quarzBerrySeeds"        , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.GaNe, "hellBushSeeds"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.ChocoCraft, "Loverly_Gysahl"   , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.ChocoCraft, "Red_Gysahl"       , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.ChocoCraft, "Golden_Gysahl"    , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.RoC, "rotarycraft_item_canola" , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.GrC_Milk, "grcmilk.SeedThistle", 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.SC2, "ItemTeaSeed"             , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.ERE, "cabbageSeeds"            , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.Bamboo, "seedrice"             , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.EBXL, "extrabiomes.seed"       , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.WTCH, "seedsmandrake"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.WTCH, "seedswormwood"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.WTCH, "seedssnowbell"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.WTCH, "seedsartichoke"         , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.WTCH, "seedswolfsbane"         , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.WTCH, "seedsbelladonna"        , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.NeLi, "GhastlyGourdSeeds"      , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.NeLi, "AbyssalOatSeeds"        , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.NeLi, "DevilishMaizeSeeds"     , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.NeLi, "HellderBerrySeeds"      , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedBeet"                , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedBellPepperOrange"    , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedBellPepperRed"       , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedBellPepperYellow"    , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedBroccoli"            , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedCassava"             , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedCelery"              , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedCorn"                , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedCucumber"            , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedEggplant"            , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedGreenBean"           , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedLeek"                , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedLettuce"             , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedOnion"               , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedSorrel"              , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedSpinach"             , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "seedTomato"              , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.PMP, "foodQuinoaSeeds"         , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "artichokeseedItem"       , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "asparagusseedItem"       , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "bambooshootseedItem"     , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "barleyseedItem"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "beanseedItem"            , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "beetseedItem"            , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "bellpepperseedItem"      , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "blackberryseedItem"      , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "blueberryseedItem"       , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "broccoliseedItem"        , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "brusselsproutseedItem"   , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "cabbageseedItem"         , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "cactusfruitseedItem"     , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "candleberryseedItem"     , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "cantaloupeseedItem"      , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "cauliflowerseedItem"     , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "celeryseedItem"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "chilipepperseedItem"     , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "coffeeseedItem"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "cornseedItem"            , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "cottonseedItem"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "cranberryseedItem"       , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "cucumberseedItem"        , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "curryleafseedItem"       , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "eggplantseedItem"        , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "garlicseedItem"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "gingerseedItem"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "grapeseedItem"           , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "kiwiseedItem"            , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "leekseedItem"            , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "lettuceseedItem"         , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "mustardseedItem"         , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "oatsseedItem"            , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "okraseedItem"            , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "onionseedItem"           , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "parsnipseedItem"         , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "peanutseedItem"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "peasseedItem"            , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "pineappleseedItem"       , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "radishseedItem"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "raspberryseedItem"       , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "rhubarbseedItem"         , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "riceseedItem"            , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "rutabagaseedItem"        , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "ryeseedItem"             , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "scallionseedItem"        , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "seaweedseedItem"         , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "sesameseedsseedItem"     , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "soybeanseedItem"         , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "spiceleafseedItem"       , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "spinachseedItem"         , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "strawberryseedItem"      , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "sweetpotatoseedItem"     , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "teaseedItem"             , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "tomatoseedItem"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "turnipseedItem"          , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "waterchestnutseedItem"   , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "whitemushroomseedItem"   , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "wintersquashseedItem"    , 1, 0), F);
		addLoot("gt.seeds", 144,16,48, ST.make(MD.HaC, "zucchiniseedItem"        , 1, 0), F);
		
		
		ST.LOOT_TABLES.add("gt.books");
		ChestGenHooks.getInfo("gt.books").setMin( 8);
		ChestGenHooks.getInfo("gt.books").setMax(24);
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Hunting_Creeper"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Hunting_Skeleton"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Hunting_Zombie"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Hunting_Spider"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Hunting_End"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Hunting_Blaze"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Hunting_Witch"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Elements"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Alloys"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Smeltery"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Extenders"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Printer"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Steam"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Random"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Tools"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Enchantments"));
		addLoot("gt.books", 144, 1, 1, ST.book("Manual_Reactors"));
		
		
		ST.LOOT_TABLES.add("gt.matdicts");
		ChestGenHooks.getInfo("gt.matdicts").setMin( 8);
		ChestGenHooks.getInfo("gt.matdicts").setMax(24);
		// Dedicated Loot Table for ALL Material Dictionaries, so I can get rid of special case code in my Books.
		for (OreDictMaterial tMaterial : OreDictMaterial.MATERIAL_ARRAY) if (tMaterial != null && tMaterial.mDictionaryBook != null)
		addLoot("gt.matdicts", 144, 1, 1, tMaterial.mDictionaryBook);
		
		
		ST.LOOT_TABLES.add("gt.bottles");
		ChestGenHooks.getInfo("gt.bottles").setMin( 8);
		ChestGenHooks.getInfo("gt.bottles").setMax(24);
		addLoot("gt.bottles", 288, 1, 1, ST.make(Items.experience_bottle, 1, 0));
		addLoot("gt.bottles", 144, 1, 1, IL.Bottle_Empty                .get(1));
		addLoot("gt.bottles", 144, 1, 1, IL.Bottle_Milk                 .get(1));
		addLoot("gt.bottles", 144, 1, 1, IL.Bottle_Milk_Spoiled         .get(1));
		addLoot("gt.bottles", 144, 1, 1, IL.Bottle_Milk_Soy             .get(1));
		addLoot("gt.bottles", 144, 1, 1, IL.EtFu_Bottle_Honey           .get(1, IL.Bottle_Honey.get(1)));
		addLoot("gt.bottles", 144, 1, 1, FL.Blood     .exists() ? IL.Bottle_Blood     .get(1) : ST.make(Items.experience_bottle, 1, 0));
		addLoot("gt.bottles",  72, 1, 1, IL.Bottle_Slime_Green          .get(1));
		addLoot("gt.bottles",  36, 1, 1, FL.Slime_Pink.exists() ? IL.Bottle_Slime_Pink.get(1) : IL.Bottle_Slime_Green.get(1));
		addLoot("gt.bottles",  36, 1, 1, FL.Slime_Blue.exists() ? IL.Bottle_Slime_Blue.get(1) : IL.Bottle_Slime_Green.get(1));
		addLoot("gt.bottles", 144, 1, 1, IL.Bottle_Tar                  .get(1));
		addLoot("gt.bottles", 144, 1, 1, IL.Bottle_Glue                 .get(1));
		addLoot("gt.bottles", 144, 1, 1, IL.Bottle_Lubricant            .get(1));
		addLoot("gt.bottles", 144, 1, 1, IL.HBM_Bottle_Mercury          .get(1, IL.Bottle_Mercury.get(1)));
		addLoot("gt.bottles", 144, 1, 1, IL.Bottle_Holy_Water           .get(1));
		addLoot("gt.bottles", 144, 1, 1, IL.Bottle_Ink                  .get(1));
		addLoot("gt.bottles", 144, 1, 1, IL.Bottle_Indigo               .get(1));
		addLoot("gt.bottles", 144, 1, 1, IL.Bottle_Beer                 .get(1));
		addLoot("gt.bottles", 144, 1, 1, IL.Bottle_Purple_Drink         .get(1));
		addLoot("gt.bottles", 144, 1, 1, FL.Sap_Maple              .fill(IL.Bottle_Empty.get(1)));
		// Rare Stuff
		addLoot("gt.bottles",  36, 1, 1, FL.Sap_Rainbow            .fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles",  36, 1, 1, FL.Med_Heal               .fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles",  36, 1, 1, FL.Med_Laxative           .fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles",  36, 1, 1, FL.Grenade_Juice          .fill(IL.Bottle_Empty.get(1)));
		// Basic Potions
		addLoot("gt.bottles", 144, 1, 1, FL.Potion_Speed_1         .fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles", 144, 1, 1, FL.Potion_Slowness_1S     .fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles", 144, 1, 1, FL.Potion_Strength_1      .fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles", 144, 1, 1, FL.Potion_Weakness_1S     .fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles", 144, 1, 1, FL.Potion_Regen_1         .fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles", 144, 1, 1, FL.Potion_Heal_1S         .fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles", 144, 1, 1, FL.Potion_Poison_1S       .fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles", 144, 1, 1, FL.Potion_Harm_1S         .fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles", 144, 1, 1, FL.Potion_FireResistance_1.fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles", 144, 1, 1, FL.Potion_NightVision_1   .fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles", 144, 1, 1, FL.Potion_WaterBreathing_1.fill(IL.Bottle_Empty.get(1)));
		addLoot("gt.bottles", 144, 1, 1, FL.Potion_Invisibility_1  .fill(IL.Bottle_Empty.get(1)));
		
		
		addLoot(ChestGenHooks.BONUS_CHEST             ,  10, 8,16, IL.Bottle_Purple_Drink.get(1));
		addLoot(ChestGenHooks.BONUS_CHEST             ,  10, 8,16, IL.Bottle_Slime_Green.get(1));
		addLoot(ChestGenHooks.BONUS_CHEST             ,  10, 8,16, IL.Bottle_Lubricant.get(1));
		addLoot(ChestGenHooks.BONUS_CHEST             ,  10, 8,16, IL.Book_Loot_Guide.get(1));
		addLoot(ChestGenHooks.BONUS_CHEST             ,  10, 8,16, IL.Bag_Loot_Sapling.get(1));
		
//      addLoot(ChestGenHooks.DUNGEON_CHEST           ,  20, 1, 1, ItemList.Spray_Ice.get(1));
//      addLoot(ChestGenHooks.DUNGEON_CHEST           ,  20, 1, 1, ItemList.Spray_Pepper.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 1, 6, OP.ingot.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 1, 6, OP.ingot.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 1, 6, OP.ingot.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   2, 1, 6, OP.ingot.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 1, 6, OP.plate.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 1, 6, OP.plate.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 1, 6, OP.plate.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   2, 1, 6, OP.plate.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 2,12, OP.stick.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 2,12, OP.stick.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 2,12, OP.stick.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   2, 2,12, OP.stick.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 4,24, OP.toolHeadArrow.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 4,24, OP.toolHeadArrow.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 4,24, OP.toolHeadArrow.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   2, 4,24, OP.toolHeadArrow.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  40,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Cu));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  20,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Ag));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  10,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Au));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  20, 1, 4, IL.Bag_Loot_Sapling.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  30, 1, 4, IL.Bag_Loot_Seeds.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           , 100, 1, 4, IL.Bag_Loot_Gems.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  20, 2, 4, IL.Bag_Loot_Misc.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  80, 2, 8, IL.Bottle_Loot.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  50, 2, 8, IL.Book_Loot_Guide.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           , 100, 1, 2, IL.Book_Loot_MatDict.get(1));
		
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   2, 4, 8, IL.Bottle_Holy_Water.get(1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   1, 4,16, OP.toolHeadArrow.mat(MT.Nq, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,  12,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Au));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   1,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Nq));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   5, 4, 8, IL.Bag_Loot_Gems.get(1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   5, 1, 2, IL.Bag_Loot_Misc.get(1));
		
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   3, 4,16, OP.ingot.mat(MT.ArsenicCopper, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   3, 4,16, OP.plate.mat(MT.ArsenicCopper, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   3,16,64, OP.toolHeadArrow.mat(MT.ArsenicCopper, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   1, 4,16, OP.toolHeadArrow.mat(MT.Ke, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,  12,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Au));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   1,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Ke));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,  10, 2, 8, IL.Bag_Loot_Sapling.get(1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,  15, 2, 8, IL.Bag_Loot_Seeds.get(1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   5, 4, 8, IL.Bag_Loot_Gems.get(1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   5, 2, 4, IL.Bag_Loot_Misc.get(1));
		
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER,  30, 2, 8, ST.make(Items.fire_charge,  1, 0));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER,  20, 8,16, ST.update(OP.arrowGtWood.mat(MT.DamascusSteel, 1)));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER,   1, 8,16, ST.update(OP.arrowGtWood.mat(MT.Ke, 1)));
		
//      addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,  10, 1, 1, ItemList.Spray_Ice.get(1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 8,16, IL.Bottle_Slime_Green.get(1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 8,16, IL.Bottle_Lubricant.get(1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4,16,64, ST.make(Blocks.coal_ore    , 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4,16,64, ST.make(Blocks.iron_ore    , 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 8,32, ST.make(Blocks.gold_ore    , 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 8,32, ST.make(Blocks.lapis_ore   , 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 8,32, ST.make(Blocks.redstone_ore, 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 4,16, ST.make(Blocks.diamond_ore , 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 4,16, ST.make(Blocks.emerald_ore , 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   5, 1, 4, OP.toolHeadShovel.mat(MT.ArsenicBronze, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   3, 1, 4, OP.toolHeadShovel.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.toolHeadShovel.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   5, 1, 4, OP.toolHeadPickaxe.mat(MT.ArsenicBronze, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   3, 1, 4, OP.toolHeadRawPickaxe.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.toolHeadPickaxe.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,  20, 1, 1, IL.Tool_MatchBox_Full.get(1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,  12,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Cu));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,  10, 3, 9, IL.Bag_Loot_Gems.get(1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,  10, 1, 2, IL.Bag_Loot_Misc.get(1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   6, 1, 2, IL.Book_Loot_MatDict.get(1));
		
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   4, 8,16, IL.Bottle_Slime_Green.get(1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   4, 8,16, IL.Bottle_Lubricant.get(1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2, 4,12, OP.ingot.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2, 4,12, OP.plate.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2, 8,24, OP.stick.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2, 4,12, OP.gearGtSmall.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2,16,48, OP.toolHeadArrow.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2, 4,12, OP.ingot.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2, 4,12, OP.plate.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2, 8,24, OP.stick.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2, 4,12, OP.gearGtSmall.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2,16,48, OP.toolHeadArrow.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2, 4,12, OP.ingot.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2, 4,12, OP.plate.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2, 8,24, OP.stick.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2, 4,12, OP.gearGtSmall.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   2,16,48, OP.toolHeadArrow.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   1, 4,12, OP.ingot.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,  12,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Cu));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,  10, 2, 8, IL.Bag_Loot_Sapling.get(1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,  15, 2, 8, IL.Bag_Loot_Seeds.get(1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,  10, 2, 8, IL.Bag_Loot_Gems.get(1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,  10, 2, 8, IL.Bag_Loot_Misc.get(1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,  40, 4, 8, IL.Book_Loot_Guide.get(1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,  20, 1, 2, IL.Book_Loot_MatDict.get(1));
		
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 9, IL.Paper_Magic_Research_0.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 8, IL.Paper_Magic_Research_1.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 7, IL.Paper_Magic_Research_2.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 6, IL.Paper_Magic_Research_3.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 5, IL.Paper_Magic_Research_4.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 4, IL.Paper_Magic_Research_5.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 3, IL.Paper_Magic_Research_6.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 2, IL.Paper_Magic_Research_7.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 1, IL.Paper_Magic_Research_8.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,  40, 4, 8, IL.Book_Loot_Guide.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,  20, 4,16, IL.Book_Loot_MatDict.get(1));
		
//      addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,  20, 1, 1, ItemList.Spray_Pepper.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   2, 4, 8, OP.crateGtDust.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   4, 8,16, OP.crateGtDust.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   4, 8,16, OP.crateGtDust.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   4, 4, 8, OP.crateGtDust.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   2, 4, 8, OP.crateGtIngot.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   4, 8,16, OP.crateGtIngot.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   4, 8,16, OP.crateGtIngot.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   4, 4, 8, OP.crateGtIngot.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   2, 4, 8, OP.crateGtPlate.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   4, 8,16, OP.crateGtPlate.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   4, 8,16, OP.crateGtPlate.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   4, 4, 8, OP.crateGtPlate.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,  12,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Ag));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   5, 2, 8, IL.Bag_Loot_Sapling.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,  10, 2, 8, IL.Bag_Loot_Seeds.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,  10, 2, 8, IL.Bag_Loot_Misc.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,  30, 2, 8, IL.Bottle_Loot.get(1));
		
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,  12, 1, 4, OP.toolHeadSword.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,   6, 1, 4, OP.toolHeadSword.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,  12, 1, 4, OP.toolHeadAxeDouble.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,   6, 1, 4, OP.toolHeadAxeDouble.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,   6,16,48, ST.update(OP.arrowGtWood.mat(MT.DamascusSteel, 1)));
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,   6, 8,24, ST.update(OP.arrowGtWood.mat(MT.SterlingSilver, 1)));
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,  12,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Ag));
	}
	
	public static boolean addLoot(String aType, WeightedRandomChestContent aLoot) {
		if (ST.invalid(aLoot.theItemId) || UT.Code.stringInvalid(aType)) {
			ERR.println("Failed to add Loot: " + aLoot.theItemId + " to " + aType);
			return F;
		}
		ChestGenHooks.addItem(aType, aLoot);
		return T;
	}
	
	public static boolean addLoot(String aType, int aChance, int aMin, int aMax, ItemStack aLoot) {return addLoot(aType, aChance, aMin, aMax, aLoot, T);}
	public static boolean addLoot(String aType, int aChance, int aMin, int aMax, ItemStack aLoot, boolean aLogMissing) {
		if (ST.invalid(aLoot) || aMin <= 0 || aMax <= 0 || UT.Code.stringInvalid(aType)) {
			if (aLogMissing) ERR.println("Failed to add Loot: " + aLoot + " to " + aType);
			return F;
		}
		if (ConfigsGT.WORLDGEN.get("loot." + aType, aLoot, T)) {
			ChestGenHooks.addItem(aType, new WeightedRandomChestContent(ST.copy(aLoot), Math.min(aMin, aLoot.getMaxStackSize()), Math.min(aMax, aLoot.getMaxStackSize()), aChance));
		}
		return T;
	}
}
