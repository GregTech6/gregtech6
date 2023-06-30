/**
 * Copyright (c) 2023 GregTech-6 Team
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
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.*;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;

import static gregapi.data.CS.*;
import static gregapi.util.CR.*;

public class Compat_Recipes_Betweenlands extends CompatMods {
	public Compat_Recipes_Betweenlands(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Betweenlands Recipes.");
		CR.remove(IL.BTL_Weedwood_Bark.get(1));
		if (COMPAT_IC2 != null) COMPAT_IC2.addToExplosionWhitelist(IL.BTL_Bedrock.block());
		
		// Weedwood Bowl
		CR.shaped(ST.make(MD.BTL, "unknownGeneric"  , 1,25), DEF     | DEL_OTHER_SHAPED_RECIPES,  "k" ,  "X" , 'X', OD.plankWeedwood);
		// Mud Bricks
		RM.add_smelting(IL.BTL_Mud.get(1), IL.BTL_Mud_Brick.get(1), T, F, T);
		CR.shapeless(IL.Mud_Ball.get(4), CR.DEF_NCC, new Object[] {IL.BTL_Mud});
		CR.shaped(IL.BTL_Mud_Bricks                 .get(1), DEF     | DEL_OTHER_SHAPED_RECIPES, "BB" , "BB" , 'B', IL.BTL_Mud_Brick);
		CR.shaped(IL.BTL_Mud_Bricks                 .get(3), DEF     | DEL_OTHER_SHAPED_RECIPES, "BB" , "BB" , 'B', ST.make(MD.BTL, "mudBrickStairs", 1, 0));
		CR.shaped(IL.BTL_Mud_Bricks                 .get(1), DEF     | DEL_OTHER_SHAPED_RECIPES,  "B" ,  "B" , 'B', ST.make(MD.BTL, "Mud Brick Slab", 1, 0));
		CR.shaped(ST.make(MD.BTL, "mudBrickStairs"  , 2, 0), DEF_MIR | DEL_OTHER_SHAPED_RECIPES, " B" , "BB" , 'B', ST.make(MD.BTL, "Mud Brick Slab", 1, 0));
		CR.shaped(ST.make(MD.BTL, "mudBrickStairs"  , 1, 0), DEF_MIR | DEL_OTHER_SHAPED_RECIPES, " B" , "BB" , 'B', OD.itemMudBrick);
		CR.shaped(ST.make(MD.BTL, "mudBrickStairs"  , 4, 0), DEF_MIR | DEL_OTHER_SHAPED_RECIPES, " B" , "BB" , 'B', OD.blockMudBricks);
		CR.shaped(ST.make(MD.BTL, "Mud Brick Slab"  , 1, 0), DEF     | DEL_OTHER_SHAPED_RECIPES,        "BB" , 'B', OD.itemMudBrick);
		CR.shaped(ST.make(MD.BTL, "Mud Brick Slab"  , 4, 0), DEF     | DEL_OTHER_SHAPED_RECIPES,        "BB" , 'B', OD.blockMudBricks);
		CR.shaped(ST.make(MD.BTL, "mudBrickWall"    , 1, 0), DEF     | DEL_OTHER_SHAPED_RECIPES, " B ", "BBB", 'B', OD.itemMudBrick);
		CR.shaped(ST.make(MD.BTL, "mudBrickWall"    , 4, 0), DEF     | DEL_OTHER_SHAPED_RECIPES, " B ", "BBB", 'B', OD.blockMudBricks);
		CR.shaped(ST.make(MD.BTL, "mudFlowerPotItem", 1, 0), DEF     | DEL_OTHER_SHAPED_RECIPES, "B B", " B ", 'B', OD.itemMudBrick);
		CR.shaped(ST.make(MD.BTL, "mudFlowerPotItem", 4, 0), DEF     | DEL_OTHER_SHAPED_RECIPES, "B B", " B ", 'B', OD.blockMudBricks);
		// Thatch Blocks
		CR.shaped(ST.make(MD.BTL, "thatch"          , 4, 0), DEF     | DEL_OTHER_SHAPED_RECIPES, "BB" , "BB" , 'B', ST.make(MD.BTL, "unknownGeneric", 1, 9));
		CR.shaped(ST.make(MD.BTL, "thatchSlope"     , 4, 0), DEF_MIR | DEL_OTHER_SHAPED_RECIPES, " B" , "BB" , 'B', ST.make(MD.BTL, "unknownGeneric", 1, 9));
		CR.shaped(ST.make(MD.BTL, "Thatch Slab"     , 4, 0), DEF     | DEL_OTHER_SHAPED_RECIPES,        "BB" , 'B', ST.make(MD.BTL, "unknownGeneric", 1, 9));
		CR.shaped(ST.make(MD.BTL, "thatchSlope"     , 4, 0), DEF_MIR | DEL_OTHER_SHAPED_RECIPES, " B" , "BB" , 'B', ST.make(MD.BTL, "thatch"        , 1, 0));
		CR.shaped(ST.make(MD.BTL, "Thatch Slab"     , 4, 0), DEF     | DEL_OTHER_SHAPED_RECIPES,        "BB" , 'B', ST.make(MD.BTL, "thatch"        , 1, 0));
		CR.shapeless(ST.make(MD.BTL, "unknownGeneric", 1, 9), CR.DEF_NCC, new Object[] {ST.make(MD.BTL, "thatch"     , 1, 0)});
		CR.shapeless(ST.make(MD.BTL, "thatch"        , 1, 0), CR.DEF_NCC, new Object[] {ST.make(MD.BTL, "Thatch Slab", 1, 0), ST.make(MD.BTL, "Thatch Slab", 1, 0)});
		CR.shapeless(ST.make(MD.BTL, "thatch"        , 3, 0), CR.DEF_NCC, new Object[] {ST.make(MD.BTL, "thatchSlope", 1, 0), ST.make(MD.BTL, "thatchSlope", 1, 0), ST.make(MD.BTL, "thatchSlope", 1, 0), ST.make(MD.BTL, "thatchSlope", 1, 0)});
		CR.shapeless(ST.make(MD.BTL, "Thatch Slab"   , 3, 0), CR.DEF_NCC, new Object[] {ST.make(MD.BTL, "thatchSlope", 1, 0), ST.make(MD.BTL, "thatchSlope", 1, 0)});
		// Peat from Peat Blocks
		CR.shapeless(OP.ingot.mat(MT.Peat, 4), CR.DEF_NCC, new Object[] {IL.BTL_Peat});
		
		
		RM.compactsmash (IL.BTL_Mud_Brick .get(4), 4, IL.BTL_Mud_Bricks.get(1));
		RM.compactsmash (IL.BTL_Coral_Mire.get(1), 1, ST.make(MD.BTL, "mireCoralBlock", 1, 0));
		RM.compactsmash (IL.BTL_Coral_Deep.get(1), 1, ST.make(MD.BTL, "deepWaterCoralBlock", 1, 0));
		RM.compactunpack(IL.BTL_Compost   .get(9), 9, ST.make(MD.BTL, "blockOfCompost", 1, 0));
		RM.compact      (IL.FR_Compost    .get(9), 9, ST.make(MD.BTL, "blockOfCompost", 1, 0));
		
		RM.pressurewash(ST.make(MD.BTL, "bloodyTempleBrick", 1, 0), ST.make(MD.BTL, "templeBrick", 1, 0));
		
		RM.moss(ST.make(MD.BTL, "genericStone"               , 1, 1), ST.make(MD.BTL, "genericStone"                    , 1, 3));
		RM.moss(ST.make(MD.BTL, "genericStone"               , 1, 3), ST.make(MD.BTL, "genericStone"                    , 1, 2));
		RM.moss(ST.make(MD.BTL, "templeBrick"                , 1, 0), ST.make(MD.BTL, "mossyTempleBrick"                , 1, 0));
		RM.moss(ST.make(MD.BTL, "limestoneBricks"            , 1, 0), ST.make(MD.BTL, "mossyLimestoneBricks"            , 1, 0));
		RM.moss(ST.make(MD.BTL, "Smooth Betweenstone Slab"   , 1, 0), ST.make(MD.BTL, "Mossy Smooth Betweenstone Slab"  , 1, 0));
		RM.moss(ST.make(MD.BTL, "betweenstoneBrickStairs"    , 1, 0), ST.make(MD.BTL, "betweenstoneBrickStairsMossy"    , 1, 0));
		RM.moss(ST.make(MD.BTL, "betweenstoneBrickWall"      , 1, 0), ST.make(MD.BTL, "betweenstoneBrickWallMossy"      , 1, 0));
		RM.moss(ST.make(MD.BTL, "betweenstoneTilesCollapsing", 1, 0), ST.make(MD.BTL, "betweenstoneTilesMossyCollapsing", 1, 0));
		RM.moss(ST.make(MD.BTL, "smoothBetweenstoneStairs"   , 1, 0), ST.make(MD.BTL, "betweenstoneSmoothStairsMossy"   , 1, 0));
		RM.moss(ST.make(MD.BTL, "Betweenstone Brick Slab"    , 1, 0), ST.make(MD.BTL, "Mossy Betweenstone Brick Slab"   , 1, 0));
		RM.moss(ST.make(MD.BTL, "smoothBetweenstoneWall"     , 1, 0), ST.make(MD.BTL, "smoothBetweenstoneWallMossy"     , 1, 0));
		RM.moss(ST.make(MD.BTL, "smoothBetweenstone"         , 1, 0), ST.make(MD.BTL, "betweenstoneSmoothMossy"         , 1, 0));
		RM.moss(ST.make(MD.BTL, "betweenstoneTiles"          , 1, 0), ST.make(MD.BTL, "betweenstoneTilesMossy"          , 1, 0));
		RM.moss(ST.make(MD.BTL, "betweenstoneBricks"         , 1, 0), ST.make(MD.BTL, "betweenstoneBricksMossy"         , 1, 0));
		
		RM.stonetypes(MT.STONES.Limestone, F, OP.rockGt.mat(MT.STONES.Limestone, 4), OP.blockDust.mat(MT.STONES.Limestone, 1)
		, RM.stoneshapes(MT.STONES.Limestone, F, ST.make(MD.BTL, "limestone"                         , 1, 0), ST.make(MD.BTL, "limestoneStairs"                   , 1, 0), ST.make(MD.BTL, "Limestone Slab"                    , 1, 0), ST.make(MD.BTL, "limestoneWall"                     , 1, 0), ST.make(MD.BTL, "limestonePillar"                   , 1, 0))
		, NI
		, RM.stoneshapes(MT.STONES.Limestone, F, ST.make(MD.BTL, "limestoneBricks"                   , 1, 0), ST.make(MD.BTL, "limestoneBrickStairs"              , 1, 0), ST.make(MD.BTL, "Limestone Brick Slab"              , 1, 0), ST.make(MD.BTL, "limestoneBrickWall"                , 1, 0), NI)
		, ST.make(MD.BTL, "crackedLimestoneBricks"            , 1, 0)
		, ST.make(MD.BTL, "chiseledLimestone"                 , 1, 0)
		, ST.make(MD.BTL, "polishedLimestone"                 , 1, 0)
		, ST.make(MD.BTL, "limestoneTiles"                    , 1, 0)
		, NI
		);
		
		
		RM.stoneshapes(MT.STONES.Betweenstone, F, ST.make(MD.BTL, "betweenstoneBricksMossy"           , 1, 0), ST.make(MD.BTL, "betweenstoneBrickStairsMossy"      , 1, 0), ST.make(MD.BTL, "Mossy Betweenstone Brick Slab"     , 1, 0), ST.make(MD.BTL, "betweenstoneBrickWallMossy"        , 1, 0), NI);
		RM.stoneshapes(MT.STONES.Betweenstone, F, ST.make(MD.BTL, "betweenstoneSmoothMossy"           , 1, 0), ST.make(MD.BTL, "betweenstoneSmoothStairsMossy"     , 1, 0), ST.make(MD.BTL, "Mossy Smooth Betweenstone Slab"    , 1, 0), ST.make(MD.BTL, "smoothBetweenstoneWallMossy"       , 1, 0), NI);
		RM.stonetypes(MT.STONES.Betweenstone, T, OP.rockGt.mat(MT.STONES.Betweenstone, 4), OP.blockDust.mat(MT.STONES.Betweenstone, 1)
		, RM.stoneshapes(MT.STONES.Betweenstone, F, ST.make(MD.BTL, "smoothBetweenstone"                , 1, 0), ST.make(MD.BTL, "smoothBetweenstoneStairs"          , 1, 0), ST.make(MD.BTL, "Smooth Betweenstone Slab"          , 1, 0), ST.make(MD.BTL, "smoothBetweenstoneWall"            , 1, 0), ST.make(MD.BTL, "betweenstonePillar"                , 1, 0))
		, ST.make(MD.BTL, "betweenstone"                      , 1, 0)
		, RM.stoneshapes(MT.STONES.Betweenstone, F, ST.make(MD.BTL, "betweenstoneBricks"                , 1, 0), ST.make(MD.BTL, "betweenstoneBrickStairs"           , 1, 0), ST.make(MD.BTL, "Betweenstone Brick Slab"           , 1, 0), ST.make(MD.BTL, "betweenstoneBrickWall"             , 1, 0), NI)
		, RM.stoneshapes(MT.STONES.Betweenstone, F, ST.make(MD.BTL, "betweenstoneBricksCracked"         , 1, 0), ST.make(MD.BTL, "betweenstoneBrickStairsCracked"    , 1, 0), ST.make(MD.BTL, "Cracked Betweenstone Brick Slab"   , 1, 0), ST.make(MD.BTL, "betweenstoneBrickWallCracked"      , 1, 0), NI)
		, ST.make(MD.BTL, "chiseledBetweenstone"              , 1, 0)
		, NI
		, ST.make(MD.BTL, "betweenstoneTiles"                 , 1, 0)
		, NI
		);
		
		
		RM.stonetypes(MT.STONES.Pitstone, T, OP.rockGt.mat(MT.STONES.Pitstone, 4), OP.blockDust.mat(MT.STONES.Pitstone, 1)
		, ST.make(MD.BTL, "pitstone"                          , 1, 0)
		, RM.stoneshapes(MT.STONES.Pitstone, T, ST.make(MD.BTL, "pitstoneBricks"                    , 1, 0), ST.make(MD.BTL, "pitstoneBrickStairs"               , 1, 0), ST.make(MD.BTL, "Pitstone Brick Slab"               , 1, 0), ST.make(MD.BTL, "pitstoneBrickWall"                 , 1, 0), NI)
		, ST.make(MD.BTL, "pitstoneTiles"                     , 1, 0)
		, NI
		, ST.make(MD.BTL, "chiseledPitstone"                  , 1, 0)
		, RM.stoneshapes(MT.STONES.Pitstone, F, ST.make(MD.BTL, "smoothPitstone"                    , 1, 0), ST.make(MD.BTL, "smoothPitstoneStairs"              , 1, 0), ST.make(MD.BTL, "Smooth Pitstone Slab"              , 1, 0), ST.make(MD.BTL, "smoothPitstoneWall"                , 1, 0), ST.make(MD.BTL, "pitstonePillar"                    , 1, 0))
		, NI
		, NI
		);
		
		
		RM.stonetypes(MT.STONES.Cragrock, T, OP.rockGt.mat(MT.STONES.Cragrock, 4), OP.blockDust.mat(MT.STONES.Cragrock, 1)
		, RM.stoneshapes(MT.STONES.Cragrock, F, ST.make(MD.BTL, "smoothCragrock"                    , 1, 0), ST.make(MD.BTL, "smoothCragrockStairs"              , 1, 0), ST.make(MD.BTL, "Smooth Cragrock Slab"              , 1, 0), ST.make(MD.BTL, "smoothCragrockWall"                , 1, 0), ST.make(MD.BTL, "cragrockPillar"                    , 1, 0))
		, ST.make(MD.BTL, "genericStone"                      , 1, 1)
		, RM.stoneshapes(MT.STONES.Cragrock, F, ST.make(MD.BTL, "cragrockBrick"                     , 1, 0), ST.make(MD.BTL, "cragrockBrickStairs"               , 1, 0), ST.make(MD.BTL, "Cragrock Brick Slab"               , 1, 0), ST.make(MD.BTL, "cragrockWall"                      , 1, 0), NI)
		, NI
		, ST.make(MD.BTL, "carvedCrag"                        , 1, 0)
		, NI
		, ST.make(MD.BTL, "cragTiles"                         , 1, 0)
		, NI
		);
		
		
		RM.stonetypes(MT.STONES.Templerock, T, OP.rockGt.mat(MT.STONES.Templerock, 4), OP.blockDust.mat(MT.STONES.Templerock, 1)
		, NI
		, NI
		, RM.stoneshapes(MT.STONES.Templerock, F, ST.make(MD.BTL, "templeBrick"                       , 1, 0), ST.make(MD.BTL, "templeBrickStairs"                 , 1, 0), ST.make(MD.BTL, "Temple Brick Slab"                 , 1, 0), ST.make(MD.BTL, "templeBrickWall"                   , 1, 0), ST.make(MD.BTL, "templePillar"                      , 1, 0))
		, ST.make(MD.BTL, "crackedTempleBrick"                , 1, 0)
		, ST.make(MD.BTL, "carvedTempleBrick"                 , 1, 0)
		, ST.make(MD.BTL, "smoothTempleBrick"                 , 1, 0)
		, NI
		, NI
		);
		
		
		//ST.make(MD.BTL, "betweenstoneTilesCracked"          , 1, 0);// Cracked Betweenstone Tiles
		//ST.make(MD.BTL, "betweenstoneTilesFortress"         , 1, 0);// Glowing Betweenstone Tile
		//ST.make(MD.BTL, "betweenstoneBricksMirage"          , 1, 0);// Betweenstone Bricks Mirage
		//ST.make(MD.BTL, "polishedLimestoneCollapsing"       , 1, 0);// Weak Polished Limestone
		//ST.make(MD.BTL, "glowingSmoothCragrock"             , 1, 0);// Glowing Smooth Cragrock
		
		
		RM.Drying.addRecipe1(T, 16, 128, ST.make(MD.BTL, "unknownGeneric", 1, 8), NF, FL.DistW.make(50), ST.make(MD.BTL, "unknownGeneric", 1, 9));
		
		RM.sawing(16,  16, F, 100, ST.make(MD.BTL, "walkway"                        , 1, W), IL.BTL_Weedwood_Planks.get(1), OM.dust(MT.Weedwood, U3));
		RM.sawing(16,  16, F, 100, ST.make(MD.BTL, "weedwoodPlanksButton"           , 1, W), IL.BTL_Weedwood_Planks.get(1));
		RM.sawing(16,  32, F, 100, ST.make(MD.BTL, "weedwoodPlanksPressurePlate"    , 1, W), IL.BTL_Weedwood_Planks.get(2));
		RM.sawing(16,  32, F, 100, ST.make(MD.BTL, "weedwoodSign"                   , 1, W), IL.BTL_Weedwood_Planks.get(2), OM.dust(MT.Weedwood, OP.stick.mAmount / 3));
		RM.sawing(16,  32, F, 100, ST.make(MD.BTL, "weedwoodPlanksFenceGate"        , 1, W), IL.BTL_Weedwood_Planks.get(2), OM.dust(MT.Weedwood, OP.stick.mAmount * 4));
		RM.sawing(16,  48, F, 100, ST.make(MD.BTL, "mossBedItem"                    , 1, W), IL.BTL_Weedwood_Planks.get(3));
		RM.sawing(16,  48, F, 100, ST.make(MD.BTL, "weedwoodTrapDoor"               , 1, W), IL.BTL_Weedwood_Planks.get(3));
		RM.sawing(16,  64, F, 100, ST.make(MD.BTL, "weedwoodCraftingTable"          , 1, W), IL.BTL_Weedwood_Planks.get(4));
		RM.sawing(16,  80, F, 100, ST.make(MD.BTL, "weedwoodRowboat"                , 1, W), IL.BTL_Weedwood_Planks.get(5));
		RM.sawing(16,  96, F, 100, ST.make(MD.BTL, "door_weedwood"                  , 1, W), IL.BTL_Weedwood_Planks.get(6));
		RM.sawing(16, 128, F, 100, ST.make(MD.BTL, "weedwoodChest"                  , 1, W), IL.BTL_Weedwood_Planks.get(8));
		RM.sawing(16, 128, F, 100, ST.make(MD.BTL, "weedwoodJukebox"                , 1, W), IL.BTL_Weedwood_Planks.get(8), OP.gem.mat(MT.Valonite, 1));
		
		CR.shapeless(IL.BTL_Weedwood_Planks.get(1), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "walkway"                    )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(1), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodPlanksButton"       )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(2), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodPlanksPressurePlate")});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(2), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodSign"               )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(2), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodPlanksFenceGate"    )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(3), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "mossBedItem"                )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(3), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodTrapDoor"           )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(4), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodCraftingTable"      )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(5), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodRowboat"            )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(6), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "door_weedwood"              )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(8), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodChest"              )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(8), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodJukebox"            )});
		
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1, 0), ST.make(MD.BTL, "groundStuff", 1, 0));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,13), ST.make(MD.BTL, "groundStuff", 1, 1));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,28), ST.make(MD.BTL, "groundStuff", 1, 2));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,24), ST.make(MD.BTL, "groundStuff", 1, 3));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1, 2), ST.make(MD.BTL, "groundStuff", 1, 4));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,11), ST.make(MD.BTL, "groundStuff", 1, 5));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,18), ST.make(MD.BTL, "groundStuff", 1, 6));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,23), ST.make(MD.BTL, "groundStuff", 1, 7));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,26), ST.make(MD.BTL, "groundStuff", 1, 8));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,19), ST.make(MD.BTL, "groundStuff", 1, 9));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,31), ST.make(MD.BTL, "groundStuff", 1,10));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1, 4), ST.make(MD.BTL, "groundStuff", 1,11));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,15), ST.make(MD.BTL, "groundStuff", 1,12));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1, 3), ST.make(MD.BTL, "groundStuff", 1,13));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1, 8), ST.make(MD.BTL, "groundStuff", 1,14));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1, 9), ST.make(MD.BTL, "groundStuff", 1,15));
		RM.mortarize(ST.make(MD.BTL, "unknownGeneric"         , 1, 9), ST.make(MD.BTL, "groundStuff", 1,17));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1, 1), ST.make(MD.BTL, "groundStuff", 1,18));
		RM.mortarize(ST.make(MD.BTL, "unknownGeneric"         , 1,24), ST.make(MD.BTL, "groundStuff", 1,19));
		RM.mortarize(ST.make(MD.BTL, "blackHatMushroomItem"   , 1, 0), ST.make(MD.BTL, "groundStuff", 1,20));
		RM.mortarize(ST.make(MD.BTL, "unknownGeneric"         , 1, 3), ST.make(MD.BTL, "groundStuff", 1,21));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1, 7), ST.make(MD.BTL, "groundStuff", 1,22));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,10), ST.make(MD.BTL, "groundStuff", 1,23));
		RM.mortarize(ST.make(MD.BTL, "bulbCappedMushroomItem" , 1, 0), ST.make(MD.BTL, "groundStuff", 1,24));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,12), ST.make(MD.BTL, "groundStuff", 1,25));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,14), ST.make(MD.BTL, "groundStuff", 1,26));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,29), ST.make(MD.BTL, "groundStuff", 1,27));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1, 6), ST.make(MD.BTL, "groundStuff", 1,29));
		RM.mortarize(ST.make(MD.BTL, "flatheadMushroomItem"   , 1, 0), ST.make(MD.BTL, "groundStuff", 1,30));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,16), ST.make(MD.BTL, "groundStuff", 1,31));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,32), ST.make(MD.BTL, "groundStuff", 1,33));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,17), ST.make(MD.BTL, "groundStuff", 1,34));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,20), ST.make(MD.BTL, "groundStuff", 1,35));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1, 5), ST.make(MD.BTL, "groundStuff", 1,36));
		RM.mortarize(ST.make(MD.BTL, "unknownGeneric"         , 1, 4), ST.make(MD.BTL, "groundStuff", 1,37));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,30), ST.make(MD.BTL, "groundStuff", 1,38));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,21), ST.make(MD.BTL, "groundStuff", 1,39));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,22), ST.make(MD.BTL, "groundStuff", 1,40));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,25), ST.make(MD.BTL, "groundStuff", 1,41));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,27), ST.make(MD.BTL, "groundStuff", 1,42));
		RM.mortarize(ST.make(MD.BTL, "unknownGeneric"         , 1,31), ST.make(MD.BTL, "groundStuff", 1,43));
		RM.mortarize(ST.make(MD.BTL, "unknownGeneric"         , 1,11), ST.make(MD.BTL, "groundStuff", 1,44));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,33), ST.make(MD.BTL, "groundStuff", 1,46));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,34), ST.make(MD.BTL, "groundStuff", 1,47));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,35), ST.make(MD.BTL, "groundStuff", 1,48));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,36), ST.make(MD.BTL, "groundStuff", 1,49));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,37), ST.make(MD.BTL, "groundStuff", 1,50));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,38), ST.make(MD.BTL, "groundStuff", 1,51));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,39), ST.make(MD.BTL, "groundStuff", 1,52));
		RM.mortarize(ST.make(MD.BTL, "plantDrop"              , 1,40), ST.make(MD.BTL, "groundStuff", 1,53));
		
		RM.Mortar   .addRecipe1(T, 16, 16, 6000, IL.BTL_Portal_Bark.get(1), IL.BTL_Bark.get(9));
		RM.Mortar   .addRecipe1(T, 16, 16, 5000, IL.BTL_Weedwood_Bark.get(1), IL.BTL_Bark.get(9));
		RM.Mortar   .addRecipe1(T, 16, 16, 1000, IL.BTL_Weedwood_RottenBark.get(1), IL.FR_Mulch.exists()?IL.FR_Mulch.get(9):OP.dust.mat(MT.Weedwood, 9));
		
		CR.shaped(OP.stick.mat(MT.Weedwood, 2), DEF, "s", "X", 'X', ST.make(MD.BTL, "deadWeedwoodBush", 1, 0));
		CR.shaped(OP.stick.mat(MT.Weedwood, 2), DEF, "k", "X", 'X', ST.make(MD.BTL, "deadWeedwoodBush", 1, 0));
		CR.shaped(OP.stick.mat(MT.Weedwood, 2), DEF, "s", "X", 'X', ST.make(MD.BTL, "weedwoodBush", 1, 0));
		CR.shaped(OP.stick.mat(MT.Weedwood, 2), DEF, "k", "X", 'X', ST.make(MD.BTL, "weedwoodBush", 1, 0));
	}
}
