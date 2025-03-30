/**
 * Copyright (c) 2024 GregTech-6 Team
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

import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.util.ST;
import gregapi.util.UT;
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
			TFTreasure.labyrinth_room    = TwilightTreasureReplacer.create(TFTreasure.labyrinth_room   ,  5, "labyrinth_room"   , ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,     8);
			TFTreasure.labyrinth_deadend = TwilightTreasureReplacer.create(TFTreasure.labyrinth_deadend,  6, "labyrinth_deadend", ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   508);
			TFTreasure.labyrinth_vault   = TwilightTreasureReplacer.create(TFTreasure.labyrinth_vault  , 10, "labyrinth_vault"  , ChestGenHooks.VILLAGE_BLACKSMITH      ,    16);
			TFTreasure.tower_room        = TwilightTreasureReplacer.create(TFTreasure.tower_room       ,  7, "tower_room"       , ChestGenHooks.PYRAMID_DESERT_CHEST    ,     2);
			TFTreasure.tower_library     = TwilightTreasureReplacer.create(TFTreasure.tower_library    ,  8, "tower_library"    , ChestGenHooks.STRONGHOLD_LIBRARY      ,   502);
			TFTreasure.basement          = TwilightTreasureReplacer.create(TFTreasure.basement         ,  9, "basement"         , ChestGenHooks.STRONGHOLD_CROSSING     ,     9);
			TFTreasure.darktower_cache   = TwilightTreasureReplacer.create(TFTreasure.darktower_cache  , 11, "darktower_cache"  , ChestGenHooks.STRONGHOLD_CORRIDOR     ,   525);
			TFTreasure.darktower_key     = TwilightTreasureReplacer.create(TFTreasure.darktower_key    , 12, "darktower_key"    , ChestGenHooks.DUNGEON_CHEST           ,    25);
			TFTreasure.darktower_boss    = TwilightTreasureReplacer.create(TFTreasure.darktower_boss   , 13, "darktower_boss"   , ChestGenHooks.PYRAMID_JUNGLE_DISPENSER,    59);
			TFTreasure.tree_cache        = TwilightTreasureReplacer.create(TFTreasure.tree_cache       , 14, "tree_cache"       , ChestGenHooks.BONUS_CHEST             ,   508);
			TFTreasure.stronghold_cache  = TwilightTreasureReplacer.create(TFTreasure.stronghold_cache , 15, "stronghold_cache" , ChestGenHooks.STRONGHOLD_CORRIDOR     ,   510);
			TFTreasure.stronghold_room   = TwilightTreasureReplacer.create(TFTreasure.stronghold_room  , 16, "stronghold_room"  , ChestGenHooks.DUNGEON_CHEST           ,    10);
			TFTreasure.stronghold_boss   = TwilightTreasureReplacer.create(TFTreasure.stronghold_boss  , 17, "stronghold_boss"  , ChestGenHooks.VILLAGE_BLACKSMITH      ,   559);
			TFTreasure.aurora_cache      = TwilightTreasureReplacer.create(TFTreasure.aurora_cache     , 18, "aurora_cache"     , ChestGenHooks.DUNGEON_CHEST           ,    46);
			TFTreasure.aurora_room       = TwilightTreasureReplacer.create(TFTreasure.aurora_room      , 19, "aurora_room"      , ChestGenHooks.DUNGEON_CHEST           ,    13);
		////TFTreasure.aurora_boss       = TwilightTreasureReplacer.create(TFTreasure.aurora_boss      , 20, "aurora_boss"      , ChestGenHooks.DUNGEON_CHEST           , 32745);// This one is actually empty and unused.
			TFTreasure.troll_garden      = TwilightTreasureReplacer.create(TFTreasure.troll_garden     , 21, "troll_garden"     , ChestGenHooks.DUNGEON_CHEST           ,   524);
			TFTreasure.troll_vault       = TwilightTreasureReplacer.create(TFTreasure.troll_vault      , 22, "troll_vault"      , ChestGenHooks.VILLAGE_BLACKSMITH      ,    24);
		}
		
		addLoot(ChestGenHooks.BONUS_CHEST             ,   2, 8,16, IL.Bottle_Purple_Drink.get(1));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   5, 8,16, IL.Bottle_Glue.get(1));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   2, 8,16, IL.Bottle_Lubricant.get(1));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   2, 1, 1, ST.book("Manual_Elements"));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   5, 1, 1, ST.book("Manual_Alloys"));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   5, 1, 1, ST.book("Manual_Smeltery"));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   5, 1, 1, ST.book("Manual_Random"));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   5, 1, 1, ST.book("Manual_Tools"));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   1, 1, 1, ST.book("Manual_Hunting_Creeper"));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   1, 1, 1, ST.book("Manual_Hunting_Skeleton"));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   1, 1, 1, ST.book("Manual_Hunting_Zombie"));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   1, 1, 1, ST.book("Manual_Hunting_Spider"));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   5, 1, 1, MT.Fe.mDictionaryBook);
		addLoot(ChestGenHooks.BONUS_CHEST             ,   5, 1, 1, MT.WroughtIron.mDictionaryBook);
		addLoot(ChestGenHooks.BONUS_CHEST             ,   5, 1, 1, MT.Steel.mDictionaryBook);
		addLoot(ChestGenHooks.BONUS_CHEST             ,   5, 1, 1, MT.Cu.mDictionaryBook);
		addLoot(ChestGenHooks.BONUS_CHEST             ,   5, 1, 1, MT.Sn.mDictionaryBook);
		addLoot(ChestGenHooks.BONUS_CHEST             ,   5, 1, 1, MT.Bronze.mDictionaryBook);
		addLoot(ChestGenHooks.BONUS_CHEST             ,   1, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 0));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   1, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 1));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   1, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 2));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   1, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 3));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   1, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 4));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   1, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 5));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   1, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 6));
		addLoot(ChestGenHooks.BONUS_CHEST             ,   1, 2, 8, ST.make(BlocksGT.Saplings_CD, 1, 0));
		
//      addLoot(ChestGenHooks.DUNGEON_CHEST           ,  20, 1, 1, ItemList.Spray_Ice.get(1));
//      addLoot(ChestGenHooks.DUNGEON_CHEST           ,  20, 1, 1, ItemList.Spray_Pepper.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  20, 4, 8, IL.Bottle_Holy_Water.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  60, 8,16, IL.Bottle_Purple_Drink.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  10, 8,16, IL.Bottle_Glue.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  20, 8,16, IL.Bottle_Lubricant.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   6, 2, 6, OP.dust.mat(MT.OREMATS.Zeolite, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  24, 1, 6, OP.ingot.mat(MT.Ag, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   6, 1, 6, OP.ingot.mat(MT.Pb, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 1, 6, OP.ingot.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 1, 6, OP.ingot.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 1, 6, OP.ingot.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   2, 1, 6, OP.ingot.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  24, 1, 6, OP.plate.mat(MT.Ag, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   6, 1, 6, OP.plate.mat(MT.Pb, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 1, 6, OP.plate.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 1, 6, OP.plate.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 1, 6, OP.plate.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   2, 1, 6, OP.plate.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  24, 2,12, OP.stick.mat(MT.Ag, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   6, 2,12, OP.stick.mat(MT.Pb, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 2,12, OP.stick.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 2,12, OP.stick.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 2,12, OP.stick.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   2, 2,12, OP.stick.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  24, 4,24, OP.toolHeadArrow.mat(MT.Ag, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   6, 4,24, OP.toolHeadArrow.mat(MT.Pb, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 4,24, OP.toolHeadArrow.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 4,24, OP.toolHeadArrow.mat(MT.Bronze, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  12, 4,24, OP.toolHeadArrow.mat(MT.Brass, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   2, 4,24, OP.toolHeadArrow.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  16, 1, 6, OP.gemFlawless.mat(MT.Amethyst, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  16, 1, 6, OP.gemFlawless.mat(MT.Diamond, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  16, 1, 6, OP.gemFlawless.mat(MT.Ruby, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   8, 1, 6, OP.gemFlawless.mat(MT.BlueSapphire, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   8, 1, 6, OP.gemFlawless.mat(MT.GreenSapphire, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   8, 1, 6, OP.gemFlawless.mat(MT.PurpleSapphire, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   8, 1, 6, OP.gemFlawless.mat(MT.YellowSapphire, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   8, 1, 6, OP.gemFlawless.mat(MT.OrangeSapphire, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 6, OP.gemFlawless.mat(MT.Emerald, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 6, OP.gemFlawless.mat(MT.Aquamarine, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 6, OP.gemFlawless.mat(MT.Morganite, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 6, OP.gemFlawless.mat(MT.Heliodor, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 6, OP.gemFlawless.mat(MT.Goshenite, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 6, OP.gemFlawless.mat(MT.Bixbite, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 6, OP.gemFlawless.mat(MT.Maxixe, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  40, 1, 6, OP.ingot.mat(MT.Nd, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  40, 1, 3, OP.ingot.mat(MT.Cr, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  40, 1, 3, OP.ingot.mat(MT.Mn, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   1, 1, 1, ST.book("Manual_Hunting_Creeper"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   1, 1, 1, ST.book("Manual_Hunting_Skeleton"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   1, 1, 1, ST.book("Manual_Hunting_Zombie"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   1, 1, 1, ST.book("Manual_Hunting_Spider"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   1, 1, 1, ST.book("Manual_Hunting_End"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   1, 1, 1, ST.book("Manual_Hunting_Blaze"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   1, 1, 1, ST.book("Manual_Hunting_Witch"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 1, ST.book("Manual_Elements"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 1, ST.book("Manual_Alloys"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 1, ST.book("Manual_Smeltery"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 1, ST.book("Manual_Extenders"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 1, ST.book("Manual_Printer"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 1, ST.book("Manual_Steam"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 1, ST.book("Manual_Random"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 1, ST.book("Manual_Tools"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 1, ST.book("Manual_Enchantments"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   1, 1, 1, ST.book("Manual_Reactors"));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  20, 1, 4, IL.Tool_MatchBox_Full.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 1, IL.Tool_Lighter_Invar_Full.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   1, 1, 1, IL.Tool_Lighter_Platinum_Full.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   2, 1, 4, IL.Pill_Cure_All.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   5, 1, 1, IL.Porcelain_Cup.get(1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  40,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Cu));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  20,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Ag));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  10,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Au));
		for (int i = 0; i < 16; i++)
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   1,16,64, ST.make(BlocksGT.Glowtus, 1, i));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  10, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 0));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  10, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 1));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  10, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 2));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  10, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 3));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  10, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 4));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  10, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 5));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  10, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 6));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,   1, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 7));
		addLoot(ChestGenHooks.DUNGEON_CHEST           ,  10, 2, 8, ST.make(BlocksGT.Saplings_CD, 1, 0));
		addLoot(ChestGenHooks.DUNGEON_CHEST           , 100, 1, 2, ST.make(ItemsGT.BOOKS, 1, 32002));
		
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   2, 4, 8, IL.Bottle_Holy_Water.get(1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   4,16,64, OP.rockGt.mat(MT.STONES.SkyStone, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   3, 4,16, OP.ingot.mat(MT.Ag, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   1, 2, 8, OP.ingot.mat(MT.Pt, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   3, 4,16, OP.plate.mat(MT.Ag, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   1, 2, 8, OP.plate.mat(MT.Pt, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   3,16,64, OP.toolHeadArrow.mat(MT.Ag, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   1, 8,32, OP.toolHeadArrow.mat(MT.Pt, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   1, 4,16, OP.toolHeadArrow.mat(MT.Nq, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   2, 2, 8, OP.gemFlawless.mat(MT.Diamond, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   2, 2, 8, OP.gemFlawless.mat(MT.Ruby, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   1, 2, 8, OP.gemFlawless.mat(MT.BlueSapphire, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   1, 2, 8, OP.gemFlawless.mat(MT.GreenSapphire, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   1, 2, 8, OP.gemFlawless.mat(MT.PurpleSapphire, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   1, 2, 8, OP.gemFlawless.mat(MT.YellowSapphire, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   1, 2, 8, OP.gemFlawless.mat(MT.OrangeSapphire, 1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   3, 1, 1, ST.book("Manual_Random"));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   6, 1, 1, ST.book("Manual_Elements"));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   1, 1, 1, IL.Tool_Lighter_Platinum_Full.get(1));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,  12,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Au));
		addLoot(ChestGenHooks.PYRAMID_DESERT_CHEST    ,   1,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Nq));
		
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   4,16,64, OP.rockGt.mat(MT.STONES.SkyStone, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   3, 4,16, OP.ingot.mat(MT.ArsenicCopper, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   3, 4,16, OP.plate.mat(MT.ArsenicCopper, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   3,16,64, OP.toolHeadArrow.mat(MT.ArsenicCopper, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   1, 4,16, OP.toolHeadArrow.mat(MT.Ke, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   2, 2, 8, OP.gemFlawless.mat(MT.Amethyst, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   2, 2, 8, OP.gemFlawless.mat(MT.Diamond, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   1, 2, 8, OP.gemFlawless.mat(MT.DiamondPink, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   2, 2, 8, OP.gemFlawless.mat(MT.Ruby, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   1, 2, 8, OP.gemFlawless.mat(MT.BlueSapphire, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   1, 2, 8, OP.gemFlawless.mat(MT.GreenSapphire, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   1, 2, 8, OP.gemFlawless.mat(MT.PurpleSapphire, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   1, 2, 8, OP.gemFlawless.mat(MT.YellowSapphire, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   1, 2, 8, OP.gemFlawless.mat(MT.OrangeSapphire, 1));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,  12,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Au));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   1,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Ke));
		for (int i = 0; i < 16; i++)
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   1,32,64, ST.make(BlocksGT.Glowtus, 1, i));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   6, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 3));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   6, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 5));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ,   1, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 7));
		
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER,  30, 2, 8, ST.make(Items.fire_charge,  1, 0));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER,  20, 8,16, ST.update(OP.arrowGtWood.mat(MT.DamascusSteel, 1)));
		addLoot(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER,   1, 8,16, ST.update(OP.arrowGtWood.mat(MT.Ke, 1)));
		
//      addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,  10, 1, 1, ItemList.Spray_Ice.get(1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 8,16, IL.Bottle_Glue.get(1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 8,16, IL.Bottle_Lubricant.get(1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 2, IL.Pill_Cure_All.get(1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4,18,64, OP.nugget.mat(MT.Cu, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4,18,64, OP.nugget.mat(MT.Sn, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4,18,64, OP.nugget.mat(MT.Zn, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4, 9,36, OP.nugget.mat(MT.Pb, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 9,36, OP.nugget.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 9,36, OP.nugget.mat(MT.Ag, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4, 8,32, OP.chunkGt.mat(MT.Cu, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4, 8,32, OP.chunkGt.mat(MT.Sn, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4, 8,32, OP.chunkGt.mat(MT.Zn, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4, 4,16, OP.chunkGt.mat(MT.Pb, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 4,16, OP.chunkGt.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 4,16, OP.chunkGt.mat(MT.Ag, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4, 4,16, OP.ingot.mat(MT.Cu, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4, 4,16, OP.ingot.mat(MT.Sn, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4, 4,16, OP.ingot.mat(MT.Zn, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4, 1, 4, OP.ingot.mat(MT.Pb, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 1, 4, OP.ingot.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.ingot.mat(MT.Ag, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4,16,64, ST.make(Blocks.coal_ore    , 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   4,16,64, ST.make(Blocks.iron_ore    , 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 8,32, ST.make(Blocks.gold_ore    , 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 8,32, ST.make(Blocks.lapis_ore   , 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 8,32, ST.make(Blocks.redstone_ore, 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 4,16, ST.make(Blocks.diamond_ore , 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 4,16, ST.make(Blocks.emerald_ore , 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 2, 6, OP.dust.mat(MT.OREMATS.Zeolite, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 1, 4, OP.gemFlawless.mat(MT.Amethyst, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 1, 4, OP.gemFlawless.mat(MT.Diamond, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   2, 1, 4, OP.gemFlawless.mat(MT.Ruby, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.gemFlawless.mat(MT.BlueSapphire, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.gemFlawless.mat(MT.GreenSapphire, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.gemFlawless.mat(MT.PurpleSapphire, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.gemFlawless.mat(MT.YellowSapphire, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.gemFlawless.mat(MT.OrangeSapphire, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.gemFlawless.mat(MT.Emerald, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.gemFlawless.mat(MT.Aquamarine, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.gemFlawless.mat(MT.Morganite, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.gemFlawless.mat(MT.Heliodor, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.gemFlawless.mat(MT.Goshenite, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.gemFlawless.mat(MT.Bixbite, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.gemFlawless.mat(MT.Maxixe, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   5, 1, 4, OP.toolHeadShovel.mat(MT.ArsenicBronze, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   3, 1, 4, OP.toolHeadShovel.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.toolHeadShovel.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   5, 1, 4, OP.toolHeadPickaxe.mat(MT.ArsenicBronze, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   3, 1, 4, OP.toolHeadRawPickaxe.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   1, 1, 4, OP.toolHeadPickaxe.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,  20, 1, 1, IL.Tool_MatchBox_Full.get(1));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,  12,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Cu));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   6, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 0));
		addLoot(ChestGenHooks.MINESHAFT_CORRIDOR      ,   6, 1, 2, ST.make(ItemsGT.BOOKS, 1, 32002));
		
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   4, 8,16, IL.Bottle_Glue.get(1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   4, 8,16, IL.Bottle_Lubricant.get(1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   1, 1, 4, IL.Pill_Cure_All.get(1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   1, 1, 1, OP.chemtube.mat(MT.Mcg, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   6, 1, 4, OP.ingot.mat(MT.Cr, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   6, 2, 8, OP.ingot.mat(MT.Nd, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   3, 2, 8, OP.ingot.mat(MT.Mn, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   3, 2, 8, OP.plate.mat(MT.Mn, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   3, 8, 8, OP.toolHeadArrow.mat(MT.Mn, 1));
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
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   6, 1, 1, ST.book("Manual_Alloys"));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   6, 1, 1, ST.book("Manual_Tools"));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   3, 1, 1, ST.book("Manual_Random"));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   6, 1, 1, ST.book("Manual_Smeltery"));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,  10, 1, 1, IL.Tool_MatchBox_Full.get(1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   3, 1, 1, IL.Tool_Lighter_Invar_Full.get(1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,  12,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Cu));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   6, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 0));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,   6, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 1));
		addLoot(ChestGenHooks.VILLAGE_BLACKSMITH      ,  20, 1, 2, ST.make(ItemsGT.BOOKS, 1, 32002));
		
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Hunting_Creeper"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Hunting_Skeleton"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Hunting_Zombie"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Hunting_Spider"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Hunting_End"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Hunting_Blaze"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Hunting_Witch"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Elements"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Alloys"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Tools"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Enchantments"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Smeltery"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Extenders"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Printer"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Steam"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Random"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   3, 1, 1, ST.book("Manual_Reactors"));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,  20, 1,10, ST.make(ItemsGT.BOOKS, 1, 32002));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 9, IL.Paper_Magic_Research_0.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 8, IL.Paper_Magic_Research_1.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 7, IL.Paper_Magic_Research_2.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 6, IL.Paper_Magic_Research_3.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 5, IL.Paper_Magic_Research_4.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 4, IL.Paper_Magic_Research_5.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 3, IL.Paper_Magic_Research_6.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 2, IL.Paper_Magic_Research_7.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_LIBRARY      ,   1, 1, 1, IL.Paper_Magic_Research_8.get(1));
		
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   6, 4, 8, IL.Bottle_Holy_Water.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   4, 8,16, IL.Bottle_Glue.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   4, 8,16, IL.Bottle_Lubricant.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,  10, 1, 4, IL.Pill_Cure_All.get(1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,  10, 1, 2, OP.chemtube.mat(MT.Mcg, 1));
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
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   6, 4, 8, OP.crateGtDust.mat(MT.Nd, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   6, 2, 4, OP.crateGtDust.mat(MT.Cr, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   6, 2, 4, OP.crateGtDust.mat(MT.Mn, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,  12,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Ag));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   6, 2, 8, ST.make(BlocksGT.Saplings_AB, 1, 2));
		addLoot(ChestGenHooks.STRONGHOLD_CROSSING     ,   2, 1, 4, ST.make(ItemsGT.BOOKS, 1, 32002));
		
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,  12, 1, 4, OP.toolHeadSword.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,   6, 1, 4, OP.toolHeadSword.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,  12, 1, 4, OP.toolHeadAxeDouble.mat(MT.Steel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,   6, 1, 4, OP.toolHeadAxeDouble.mat(MT.DamascusSteel, 1));
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,   6,16,48, ST.update(OP.arrowGtWood.mat(MT.DamascusSteel, 1)));
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,   6, 8,24, ST.update(OP.arrowGtWood.mat(MT.SterlingSilver, 1)));
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,  12,16,64, MultiTileEntityCoin.COIN_MAP.get(MT.Ag));
		addLoot(ChestGenHooks.STRONGHOLD_CORRIDOR     ,   3, 1, 1, ST.book("Manual_Random"));
	}
	
	public static boolean addLoot(String aType, WeightedRandomChestContent aLoot) {
		if (ST.invalid(aLoot.theItemId) || UT.Code.stringInvalid(aType)) {
			ERR.println("Failed to add Loot: " + aLoot.theItemId + " to " + aType);
			return F;
		}
		ChestGenHooks.addItem(aType, aLoot);
		return T;
	}
	
	public static boolean addLoot(String aType, int aChance, int aMin, int aMax, ItemStack aLoot) {
		if (ST.invalid(aLoot) || aMin <= 0 || aMax <= 0 || UT.Code.stringInvalid(aType)) {
			ERR.println("Failed to add Loot: " + aLoot + " to " + aType);
			return F;
		}
		if (ConfigsGT.WORLDGEN.get("loot." + aType, aLoot, T)) {
			ChestGenHooks.addItem(aType, new WeightedRandomChestContent(ST.copy(aLoot), Math.min(aMin, aLoot.getMaxStackSize()), Math.min(aMax, aLoot.getMaxStackSize()), aChance));
		}
		return T;
	}
}
