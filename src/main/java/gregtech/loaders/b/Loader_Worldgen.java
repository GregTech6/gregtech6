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

package gregtech.loaders.b;

import gregapi.block.BlockBase;
import gregapi.block.metatype.BlockStones;
import gregapi.config.ConfigCategories;
import gregapi.data.*;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.worldgen.*;
import gregapi.worldgen.dungeon.WorldgenDungeonGT;
import gregtech.worldgen.*;
import gregtech.worldgen.aether.WorldgenAetherRocks;
import gregtech.worldgen.alfheim.WorldgenAlfheimRocks;
import gregtech.worldgen.center.*;
import gregtech.worldgen.erebus.WorldgenErebusRocks;
import gregtech.worldgen.mars.WorldgenMarsRocks;
import gregtech.worldgen.moon.WorldgenMoonRocks;
import gregtech.worldgen.nether.WorldgenNetherClay;
import gregtech.worldgen.nether.WorldgenNetherCrystals;
import gregtech.worldgen.nether.WorldgenNetherQuartz;
import gregtech.worldgen.nether.WorldgenRacks;
import gregtech.worldgen.overworld.WorldgenColtan;
import gregtech.worldgen.planets.WorldgenPlanetRocks;
import gregtech.worldgen.tree.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class Loader_Worldgen implements Runnable {
	@Override
	public void run() {
		boolean
		tInfiniteOil = ConfigsGT.WORLDGEN.get(ConfigCategories.general, "GenerateInfiniteOilSources", T),
		tInfiniteGas = ConfigsGT.WORLDGEN.get(ConfigCategories.general, "GenerateInfiniteGasSources", T);
		
		new WorldgenStoneLayers("stonelayers", T, GEN_GT, GEN_ENVM_GT, GEN_A97_GT, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld_GT); // MUST BE FIRST
		
		StoneLayer.DEEPSLATE = new StoneLayer(BlocksGT.Slate, MT.STONES.Deepslate, IL.EtFu_Deepslate.block(), 0, IL.EtFu_Deepslate_Cobble.block(), 0
		, new StoneLayerOres(MT.Emerald                                 , U64,  0, 32, ST.block(MD.EtFu, "deepslate_emerald_ore"), BIOMES_MOUNTAINS)
		, new StoneLayerOres(MT.Diamond                                 , U64,  0, 12, ST.block(MD.EtFu, "deepslate_diamond_ore"), BIOMES_JUNGLE, BIOMES_VOLCANIC)
		, new StoneLayerOres(MT.Lapis                                   , U12, 16, 24, ST.block(MD.EtFu, "deepslate_lapis_ore"), BIOMES_FROZEN, BIOMES_TAIGA)
		, new StoneLayerOres(MT.Redstone                                , U16,  0, 20, ST.block(MD.EtFu, "deepslate_redstone_ore"))
		, new StoneLayerOres(MT.Au                                      , U32,  0, 16, ST.block(MD.EtFu, "deepslate_gold_ore"), BIOMES_MESA)
		, new StoneLayerOres(MT.Cu                                      , U16,  0, 32, ST.block(MD.EtFu, "deepslate_copper_ore"), BIOMES_DESERT, BIOMES_SAVANNA)
		, new StoneLayerOres(MT.Fe                                      , U16,  0, 32, ST.block(MD.EtFu, "deepslate_iron_ore"), BIOMES_SWAMP, BIOMES_WOODS)
		, new StoneLayerOres(MT.Coal                                    , U16,  0, 32, ST.block(MD.EtFu, "deepslate_coal_ore"), BIOMES_PLAINS, BIOMES_SHROOM)
		,!MT.Nikolite.mHidden ? new StoneLayerOres(MT.Nikolite          , U32,  0, 20) : null
		, MD.HEX     .mLoaded ? new StoneLayerOres(MT.HexoriumBlack     , U32,  0, 16) : null
		, MD.HEX     .mLoaded ? new StoneLayerOres(MT.HexoriumWhite     , U32,  0, 16) : null
		, MD.MET     .mLoaded ? new StoneLayerOres(MT.DeepIron          , U16,  0, 16) : null
		);
		
		
		// Vanilla Stone
		
		
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.Stone, ST.block(MD.MIN, "conglomerate")));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.Stone, ST.block(MD.MIN, "pegmatite")));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.Stone, ST.block(MD.PFAA, "weakStone"), 0, ST.block(MD.PFAA, "weakRubble"), 0));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.Stone, ST.block(MD.PFAA, "weakStone"), 1, ST.block(MD.PFAA, "weakRubble"), 1));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.Stone, ST.block(MD.PFAA, "weakStone"), 2, ST.block(MD.PFAA, "weakRubble"), 2));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.Stone, ST.block(MD.PFAA, "weakStone"), 3, ST.block(MD.PFAA, "weakRubble"), 3));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.Stone, ST.block(MD.PFAA, "mediumStone"), 2, ST.block(MD.PFAA, "mediumCobble"), 2));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.Stone, ST.block(MD.PFAA, "mediumStone"), 4, ST.block(MD.PFAA, "mediumCobble"), 4));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.Stone, ST.block(MD.PFAA, "strongStone"), 6, ST.block(MD.PFAA, "strongCobble"), 6));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.Stone, ST.block(MD.PFAA, "veryStrongStone"), 2, ST.block(MD.PFAA, "veryStrongCobble"), 2));
		
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Chert, ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"),10, ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB"),10));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Chert, ST.block(MD.UB, "sedimentaryStone"), 7+8));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Chert, ST.block(MD.MIN, "chert")));
		
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Pumice, ST.block(MD.MIN, "pumice")));
		
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Eclogite, ST.block(MD.UB, "metamorphicStone"), 1+8, ST.block(MD.UB, "metamorphicCobblestone"), 1));
		
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Greywacke, ST.block(MD.UB, "sedimentaryStone"), 6+8));
		
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Migmatite, ST.block(MD.UB, "metamorphicStone"), 7+8, ST.block(MD.UB, "metamorphicCobblestone"), 7));
		
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.Stone, ST.block(MD.PFAA, "veryStrongStone"), 3, ST.block(MD.PFAA, "veryStrongCobble"), 3
		, new StoneLayerOres(MT.Olivine                 , U64, 32, 64)
		));
		
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Dacite, ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"), 12, ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB"), 12));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Dacite, ST.block(MD.UB, "igneousStone"), 7+8, ST.block(MD.UB, "igneousCobblestone"), 7
		,!MT.Nikolite.mHidden ? new StoneLayerOres(MT.Nikolite          , U32,  0, 20) : null
		));
		StoneLayer.LAYERS.add(new StoneLayer(null
		, MD.ARS     .mLoaded ? new StoneLayerOres(MT.Vinteum           , U64, 16, 40) : null
		, MD.TC      .mLoaded ? new StoneLayerOres(MT.InfusedOrder      , U64, 24, 40, ST.block(MD.TC, "blockCustomOre"), 5, BIOMES_MAGICAL_GOOD) : null
		, MD.TC      .mLoaded ? new StoneLayerOres(MT.InfusedWater      , U64, 48, 64, ST.block(MD.TC, "blockCustomOre"), 3, BIOMES_MAGICAL_GOOD) : null
		, MD.TC      .mLoaded ? new StoneLayerOres(MT.InfusedEarth      , U64,  0, 16, ST.block(MD.TC, "blockCustomOre"), 4, BIOMES_MAGICAL_GOOD) : null
		, MD.TC      .mLoaded ? new StoneLayerOres(MT.OREMATS.Cinnabar  , U32, 16, 40, ST.block(MD.TC, "blockCustomOre"), 0, BIOMES_MAGICAL_GOOD) : null
		));
		StoneLayer.LAYERS.add(new StoneLayer(null
		, MD.HBM     .mLoaded ? new StoneLayerOres(MT.OREMATS.Uraninite , U16,  0, 48, ST.block(MD.HBM, "tile.ore_uranium")) : null
		, MD.TC      .mLoaded ? new StoneLayerOres(MT.InfusedEntropy    , U64, 24, 40, ST.block(MD.TC, "blockCustomOre"), 6, BIOMES_MAGICAL_GOOD) : null
		, MD.TC      .mLoaded ? new StoneLayerOres(MT.InfusedAir        , U64, 48, 64, ST.block(MD.TC, "blockCustomOre"), 1, BIOMES_MAGICAL_GOOD) : null
		, MD.TC      .mLoaded ? new StoneLayerOres(MT.InfusedFire       , U64,  0, 16, ST.block(MD.TC, "blockCustomOre"), 2, BIOMES_MAGICAL_GOOD) : null
		, MD.TC      .mLoaded ? new StoneLayerOres(MT.Amber             , U32, 16, 40, ST.block(MD.TC, "blockCustomOre"), 7, BIOMES_MAGICAL_GOOD) : null
		));
		StoneLayer.LAYERS.add(new StoneLayer(null
		, new StoneLayerOres(MT.Emerald                 , U64, 16, 60, Blocks.emerald_ore, BIOMES_MOUNTAINS)
		, new StoneLayerOres(MT.Diamond                 , U64,  8, 24, Blocks.diamond_ore)
		, new StoneLayerOres(MT.Lapis                   , U24, 16, 48, Blocks.lapis_ore)
		, new StoneLayerOres(MT.Redstone                , U8 ,  8, 24, Blocks.redstone_ore)
		, new StoneLayerOres(MT.Au                      , U32,  8, 32, Blocks.gold_ore)
		, new StoneLayerOres(MT.Au                      , U32, 33, 64, Blocks.gold_ore, BIOMES_MESA)
		, new StoneLayerOres(MT.Cu                      , U16, 20, 50, ST.block(MD.EtFu, "copper_ore"), BIOMES_MESA, BIOMES_DESERT, BIOMES_SAVANNA)
		, new StoneLayerOres(MT.Fe                      , U8 , 40, 80, Blocks.iron_ore)
		, new StoneLayerOres(MT.Coal                    , U8 , 60,100, Blocks.coal_ore)
		, new StoneLayerOres(MT.Stone                   , U64,  0,255, Blocks.monster_egg)
		));
		StoneLayer.LAYERS.add(new StoneLayer(null
		, new StoneLayerOres(MT.Fe2O3                   , U6 , 30, 70)
		, new StoneLayerOres(MT.Jasper                  , U64, 32, 64, BIOMES_MOUNTAINS)
		, new StoneLayerOres(MT.JasperBlue              , U64, 32, 64, BIOMES_FROZEN)
		, new StoneLayerOres(MT.JasperGreen             , U64, 32, 64, BIOMES_DESERT)
		, new StoneLayerOres(MT.JasperYellow            , U64, 32, 64, BIOMES_SAVANNA)
		, new StoneLayerOres(MT.JasperRainforest        , U64, 32, 64, BIOMES_JUNGLE)
		, new StoneLayerOres(MT.JasperOcean             , U64, 32, 64, BIOMES_OCEAN_BEACH)
		, new StoneLayerOres(MT.TigerEyeYellow          , U64, 32, 64, BIOMES_TAIGA)
		, new StoneLayerOres(MT.TigerEyeGreen           , U64, 32, 64, BIOMES_SWAMP)
		, new StoneLayerOres(MT.TigerEyeRed             , U64, 32, 64, BIOMES_PLAINS)
		, new StoneLayerOres(MT.TigerEyeBlue            , U64, 32, 64, BIOMES_RIVER_LAKE)
		, new StoneLayerOres(MT.TigerEyeBlack           , U64, 32, 64, BIOMES_MESA)
		, new StoneLayerOres(MT.TigerIron               , U64, 32, 64, BIOMES_SHROOM)
		));
		StoneLayer.LAYERS.add(new StoneLayer(null
		, MD.HEX     .mLoaded ? new StoneLayerOres(MT.HexoriumRed       , U64,  0, 64, ST.block(MD.HEX, "blockHexoriumOreRed"  )) : null
		, MD.HEX     .mLoaded ? new StoneLayerOres(MT.HexoriumGreen     , U64,  0, 64, ST.block(MD.HEX, "blockHexoriumOreGreen")) : null
		, MD.HEX     .mLoaded ? new StoneLayerOres(MT.HexoriumBlue      , U64,  0, 64, ST.block(MD.HEX, "blockHexoriumOreBlue" )) : null
		, MD.HEX     .mLoaded ? new StoneLayerOres(MT.HexoriumBlack     , U64,  0, 20, ST.block(MD.HEX, "blockHexoriumOreBlack")) : null
		, MD.HEX     .mLoaded ? new StoneLayerOres(MT.HexoriumWhite     , U64,  0, 20, ST.block(MD.HEX, "blockHexoriumOreWhite")) : null
		));
		
		
		// Basaltic Stuff
		
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Komatiite, MT.STONES.Gabbro, ST.block(MD.UB, "igneousStone"), 4+8, ST.block(MD.UB, "igneousCobblestone"), 4));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Komatiite, MT.STONES.Gabbro, ST.block(MD.PFAA, "veryStrongStone"), 1, ST.block(MD.PFAA, "veryStrongCobble"), 1));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Komatiite, MT.STONES.Gabbro, ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"),11, ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB"),11));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Komatiite, MT.STONES.Komatiite, ST.block(MD.UB, "igneousStone"), 6+8, ST.block(MD.UB, "igneousCobblestone"), 6));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Komatiite
		, new StoneLayerOres(MT.MgCO3                   , U16, 20, 50)
		, new StoneLayerOres(MT.OREMATS.Cinnabar        , U12,  0, 32)
		, new StoneLayerOres(MT.Redstone                , U8 ,  0, 30)
		, new StoneLayerOres(MT.Pyrite                  , U12,  0, 30)
		));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Kimberlite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Kimberlite
		, new StoneLayerOres(MT.Diamond                 , U48,  0, 12)
		, new StoneLayerOres(MT.Spinel                  , U48, 24, 48, BIOMES_MOUNTAINS)
		, new StoneLayerOres(MT.BalasRuby               , U48, 24, 48, BIOMES_JUNGLE)
		));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt, MT.STONES.Basalt, ST.block(MD.GaSu, "basalt")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt, MT.STONES.Basalt, ST.block(MD.PR_EXPLORATION, "projectred.exploration.stone"), 3, ST.block(MD.PR_EXPLORATION, "projectred.exploration.stone"), 2));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt, MT.STONES.Basalt, ST.block(MD.BP, "basalt")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt, MT.STONES.Basalt, ST.block(MD.PFAA, "strongStone"), 1, ST.block(MD.PFAA, "strongCobble"), 1));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt, MT.STONES.Basalt, ST.block(MD.MIN, "basalt")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt, MT.STONES.Basalt, ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"), 0, ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB"), 0));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt, MT.STONES.Basalt, ST.block(MD.HBM, "tile.basalt_smooth"), 0, ST.block(MD.HBM, "tile.basalt"), 0
		, new StoneLayerOres(MT.Asbestos                , U16, 16, 48, ST.block(MD.HBM, "tile.basalt_asbestos"))
		, new StoneLayerOres(MT.FluoriteWhite           , U16,  0, 48, ST.block(MD.HBM, "tile.basalt_fluorite"))
		, new StoneLayerOres(MT.S                       , U8 ,  0, 32, ST.block(MD.HBM, "tile.basalt_sulfur"))
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt, MT.STONES.Basalt, ST.block(MD.UB, "igneousStone"), 5+8, ST.block(MD.UB, "igneousCobblestone"), 5
		, new StoneLayerOres(MT.OREMATS.Bastnasite      , U24, 24, 32)
		, new StoneLayerOres(MT.Monazite                , U32, 24, 32)
		, new StoneLayerOres(MT.MnO2                    , U8 , 16, 48)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Basalt
		, new StoneLayerOres(MT.Olivine                 , U32,  0, 32)
		, new StoneLayerOres(MT.Uvarovite               , U32,  8, 40)
		, new StoneLayerOres(MT.Grossular               , U32, 16, 48)
		, new StoneLayerOres(MT.OREMATS.Chromite        , U8 , 32, 64)
		));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite, MT.STONES.Rhyolite, ST.block(MD.MIN, "rhyolite")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite, MT.STONES.Rhyolite, ST.block(MD.UB, "igneousStone"), 2+8, ST.block(MD.UB, "igneousCobblestone"), 2));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite, MT.STONES.Rhyolite, ST.block(MD.PFAA, "strongStone"), 7, ST.block(MD.PFAA, "strongCobble"), 7));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite, MT.STONES.Rhyolite, ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"), 5, ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB"), 5));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite, MT.STONES.Andesite, ST.block(MD.MIN, "andesite")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite, MT.STONES.Andesite, ST.block(MD.PFAA, "strongStone"), 0, ST.block(MD.PFAA, "strongCobble"), 0));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite, MT.STONES.Andesite, ST.block(MD.UB, "igneousStone"), 3+8, ST.block(MD.UB, "igneousCobblestone"), 3
		, new StoneLayerOres(MT.OREMATS.Bromargyrite    , U8 ,  0, 32)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Andesite
		, new StoneLayerOres(MT.Au                      , U12,  0, 32)
		));
		
		
		// Chalky Stuff
		
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble, MT.Talc, ST.block(MD.UB, "metamorphicStone"), 6+8, ST.block(MD.UB, "metamorphicCobblestone"), 6));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble, MT.Chalk, ST.block(MD.UB, "sedimentaryStone"), 1+8));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble, MT.Chalk, ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"), 6, ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB"), 6));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble, MT.STONES.Marble, ST.block(MD.CHSL, "marble")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble, MT.STONES.Marble, ST.block(MD.RC, "cube"), 7));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble, MT.STONES.Marble, ST.block(MD.PR_EXPLORATION, "projectred.exploration.stone")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble, MT.STONES.Marble, ST.block(MD.BP, "marble")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble, MT.STONES.Marble, ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"), 7, ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB"), 7));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble, MT.STONES.Marble, ST.block(MD.PFAA, "strongStone"), 5, ST.block(MD.PFAA, "strongCobble"), 5));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble, MT.STONES.Marble, ST.block(MD.UB, "metamorphicStone"), 2+8, ST.block(MD.UB, "metamorphicCobblestone"), 2
		, new StoneLayerOres(MT.Dioptase                , U64, 24, 48, BIOMES_MOUNTAINS)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Marble
		, new StoneLayerOres(MT.OREMATS.Cassiterite     , U16, 40, 80)
		, new StoneLayerOres(MT.OREMATS.Stannite        , U16, 38, 82)
		, new StoneLayerOres(MT.OREMATS.Kesterite       , U16, 38, 82)
		, new StoneLayerOres(MT.OREMATS.Sphalerite      , U8 , 10, 30)
		, new StoneLayerOres(MT.OREMATS.Chalcopyrite    , U8 ,  0, 20)
		, new StoneLayerOres(MT.Pyrite                  , U12,  0, 30)
		));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone, MT.Dolomite, ST.block(MD.MIN, "dolomite")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone, MT.Dolomite, ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"), 8, ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB"), 8));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone, MT.Dolomite, ST.block(MD.UB, "sedimentaryStone"), 5+8
		, new StoneLayerOres(MT.Bone                    , U8 , 30, 60)
		));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone, MT.STONES.Limestone, ST.block(MD.PFAA, "mediumStone"), 0, ST.block(MD.PFAA, "mediumCobble"), 0));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone, MT.STONES.Limestone, ST.block(MD.MIN, "limestone")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone, MT.STONES.Limestone, ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"), 3, ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB"), 3));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone, MT.STONES.Limestone, ST.block(MD.BoP, "rocks")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone, MT.STONES.Limestone, ST.block(MD.CHSL, "limestone")
		, new StoneLayerOres(MT.NaCl                    , U8 , 40, 80, ST.block(MD.CHSL, "limestone") == NB ? NB : ST.block(MD.HaC, "salt"))
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone, MT.STONES.Limestone, ST.block(MD.UB, "sedimentaryStone"), 0+8
		, new StoneLayerOres(MT.OREMATS.Stibnite        , U24, 10, 30)
		, new StoneLayerOres(MT.OREMATS.Galena          , U8 , 30,120)
		, new StoneLayerOres(MT.Pb                      , U16, 50, 70)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone
		, new StoneLayerOres(MT.Pyrite                  , U16,  0, 30)
		, new StoneLayerOres(MT.OREMATS.Arsenopyrite    , U16,  0, 20)
		, new StoneLayerOres(MT.OREMATS.Galena          , U8 ,  5, 25)
		, new StoneLayerOres(MT.OREMATS.Galena          , U8 , 80,120)
		, new StoneLayerOres(MT.OREMATS.Wulfenite       , U32, 30, 45)
		, new StoneLayerOres(MT.OREMATS.Powellite       , U32, 35, 50)
		, new StoneLayerOres(MT.OREMATS.Molybdenite     ,U128, 30, 50)
		, new StoneLayerOres(MT.OREMATS.Tetrahedrite    , U8 , 40, 80)
		, new StoneLayerOres(MT.Cu                      , U16, 40, 80)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Limestone
		, new StoneLayerOres(MT.OREMATS.Scheelite       , U64,  0, 16)
		, new StoneLayerOres(MT.OREMATS.Wolframite      , U64,  0, 16)
		, new StoneLayerOres(MT.OREMATS.Ferberite       , U64,  0, 16)
		, new StoneLayerOres(MT.OREMATS.Huebnerite      , U64,  0, 16)
		, new StoneLayerOres(MT.OREMATS.Tungstate       , U64,  0, 16)
		, new StoneLayerOres(MT.OREMATS.YellowLimonite  , U8 , 16, 48)
		, new StoneLayerOres(MT.OREMATS.BrownLimonite   , U8 , 32, 64)
		, new StoneLayerOres(MT.OREMATS.Malachite       , U12, 16, 64)
		, new StoneLayerOres(MT.Azurite                 , U24, 16, 64)
		));
		
		
		// Granites
		
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.GraniteBlack, MT.STONES.GraniteBlack, ST.block(MD.PFAA, "strongStone"), 3, ST.block(MD.PFAA, "strongCobble"), 3));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.GraniteBlack, MT.STONES.GraniteBlack, ST.block(MD.UB, "igneousStone"), 1+8, ST.block(MD.UB, "igneousCobblestone"), 1));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.GraniteBlack
		, new StoneLayerOres(MT.OREMATS.Cooperite       , U32,  0, 16)
		, new StoneLayerOres(MT.OREMATS.Sperrylite      , U32,  0, 16)
		, new StoneLayerOres(MT.Ir                      , U64,  0,  8)
		, new StoneLayerOres(MT.Emerald                 , U64, 24, 48, BIOMES_MOUNTAINS)
		, new StoneLayerOres(MT.Aquamarine              , U64,  8, 32, BIOMES_OCEAN_BEACH)
		, new StoneLayerOres(MT.Morganite               , U64, 24, 48, BIOMES_JUNGLE)
		, new StoneLayerOres(MT.Heliodor                , U64, 24, 48, BIOMES_DESERT)
		, new StoneLayerOres(MT.Goshenite               , U64, 24, 48, BIOMES_FROZEN)
		, new StoneLayerOres(MT.Bixbite                 , U64, 24, 48, BIOMES_SAVANNA)
		, new StoneLayerOres(MT.Maxixe                  , U64, 24, 48, BIOMES_TAIGA)
		));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.GraniteRed, MT.STONES.GraniteRed, ST.block(MD.UB, "igneousStone"), 0+8, ST.block(MD.UB, "igneousCobblestone"), 0));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.GraniteRed, MT.STONES.GraniteRed, ST.block(MD.MIN, "granite")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.GraniteRed
		, new StoneLayerOres(MT.OREMATS.Pitchblende     , U32,  0, 18)
		, new StoneLayerOres(MT.OREMATS.Uraninite       , U32,  0, 16)
		, MD.HBM.mLoaded ? null : new StoneLayerOres(MT.OREMATS.Tantalite       , U64, 30, 40)
		, MD.HBM.mLoaded ? null : new StoneLayerOres(MT.OREMATS.Columbite       , U64, 30, 40)
		, MD.HBM.mLoaded ? null : new StoneLayerOres(MT.OREMATS.Coltan          , U16, 20, 50)
		));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Granite, MT.S, ST.block(MD.MIN, "sulfur_ore")).setNoDeep());
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Granite, MT.KNO3, ST.block(MD.MIN, "nitrate_ore")).setNoDeep());
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Granite, MT.Lignite, ST.block(MD.UB, "sedimentaryStone"), 4+8).setNoDeep());
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Granite
		, new StoneLayerOres(MT.BlueTopaz               , U64,  8, 32, BIOMES_OCEAN_BEACH)
		, new StoneLayerOres(MT.Topaz                   , U64, 24, 48, BIOMES_FROZEN)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Granite
		, new StoneLayerOres(MT.Apatite                 , U8 , 32, 64)
		, new StoneLayerOres(MT.PO4                     , U24, 36, 60)
		, new StoneLayerOres(MT.Phosphorite             , U24, 40, 56)
		, new StoneLayerOres(MT.PhosphorusRed           , U24, 44, 52)
		));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Diorite, MT.STONES.Diorite, ST.block(MD.PFAA, "veryStrongStone"), 0, ST.block(MD.PFAA, "veryStrongCobble"), 0));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Diorite, MT.STONES.Diorite, ST.block(MD.MIN, "diorite")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Diorite));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Diorite
		, new StoneLayerOres(MT.Sapphire                , U64, 24, 48, BIOMES_FROZEN)
		, new StoneLayerOres(MT.GreenSapphire           , U64, 24, 48, BIOMES_JUNGLE)
		, new StoneLayerOres(MT.YellowSapphire          , U64, 24, 48, BIOMES_MOUNTAINS)
		, new StoneLayerOres(MT.OrangeSapphire          , U64, 24, 48, BIOMES_SAVANNA)
		, new StoneLayerOres(MT.BlueSapphire            , U64,  8, 32, BIOMES_OCEAN_BEACH)
		, new StoneLayerOres(MT.PurpleSapphire          , U64, 24, 48, BIOMES_TAIGA)
		, new StoneLayerOres(MT.Ruby                    , U64, 24, 48, BIOMES_DESERT)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Diorite
		, new StoneLayerOres(MT.OREMATS.Garnierite      , U8 , 16, 48)
		, new StoneLayerOres(MT.OREMATS.Pentlandite     , U8 , 24, 56)
		, new StoneLayerOres(MT.OREMATS.Cobaltite       , U8 , 32, 64)
		, new StoneLayerOres(MT.Craponite               , U64, 24, 48, BIOMES_JUNGLE)
		, new StoneLayerOres(MT.Amethyst                , U64, 24, 48, BIOMES_TAIGA)
		, new StoneLayerOres(MT.Alexandrite             , U64, 24, 48, BIOMES_JUNGLE)
		));
		
		
		// Schists
		
		
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Gneiss, ST.block(MD.MIN, "gneiss")));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Gneiss, ST.block(MD.PFAA, "strongStone"), 2, ST.block(MD.PFAA, "strongCobble"), 2));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Gneiss, ST.block(MD.UB, "metamorphicStone"), 0+8, ST.block(MD.UB, "metamorphicCobblestone"), 0));
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Gneiss, ST.block(MD.HBM, "tile.stone_gneiss")
		, new StoneLayerOres(MT.Graphite                , U8 ,  0, 64)
		, new StoneLayerOres(MT.Asbestos                , U8 ,  0, 48, ST.block(MD.HBM, "tile.ore_gneiss_asbestos"))
		, MD.HBM     .mLoaded ? new StoneLayerOres(MT.CH4               , U6 ,  0, 48, ST.block(MD.HBM, "tile.ore_gneiss_gas"    )) : null
		, MD.HBM     .mLoaded ? new StoneLayerOres(MT.OREMATS.Uraninite , U16,  0, 48, ST.block(MD.HBM, "tile.ore_gneiss_uranium")) : null
		));
		
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Pinkschist, ST.block(MD.PFAA, "mediumStone"), 1, ST.block(MD.PFAA, "mediumCobble"), 1));
		
		StoneLayer.LAYERS.add(new StoneLayer(null, MT.STONES.Grayschist, ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"), 9, ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB"), 9));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.SchistGreen, MT.STONES.Greenschist, ST.block(MD.UB, "metamorphicStone"), 5+8, ST.block(MD.UB, "metamorphicCobblestone"), 5));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.SchistGreen, MT.STONES.Greenschist, ST.block(MD.PFAA, "strongStone"), 4, ST.block(MD.PFAA, "strongCobble"), 4));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.SchistGreen
		, new StoneLayerOres(MT.Andradite               , U32,  8, 40)
		, new StoneLayerOres(MT.Almandine               , U32, 16, 48)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.SchistBlue, MT.STONES.Blueschist, ST.block(MD.UB, "metamorphicStone"), 4+8, ST.block(MD.UB, "metamorphicCobblestone"), 4));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.SchistBlue, MT.STONES.Blueschist, ST.block(MD.MIN, "schist")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.SchistBlue
		, new StoneLayerOres(MT.Spessartine             , U32,  8, 40)
		, new StoneLayerOres(MT.Pyrope                  , U32, 16, 48)
		));
		
		
		// Other Stuff
		
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Shale, MT.STONES.Shale, ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"), 1, ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB"), 1));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Shale, MT.STONES.Shale, ST.block(MD.PFAA, "weakStone"), 4, ST.block(MD.PFAA, "weakRubble"), 4));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Shale, MT.STONES.Shale, ST.block(MD.BoP, "rocks"), 4));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Shale, MT.STONES.Shale, ST.block(MD.MIN, "shale")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Shale, MT.STONES.Shale, ST.block(MD.UB, "sedimentaryStone"), 2+8));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Shale));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Slate, MT.STONES.Slate, ST.block(MD.MIN, "slate")));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Slate, MT.STONES.Slate, ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"), 4, ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB"), 4));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Slate, MT.STONES.Slate, ST.block(MD.PFAA, "mediumStone"), 3, ST.block(MD.PFAA, "mediumCobble"), 3));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Slate, MT.STONES.Slate, ST.block(MD.SC2, "BlockSlate"), 0, ST.block(MD.SC2, "BlockSlate"), 3));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Slate, MT.STONES.Slate, ST.block(MD.SC2, "BlockSlate"), 1, ST.block(MD.SC2, "BlockSlate"), 4));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Slate, MT.STONES.Slate, ST.block(MD.SC2, "BlockSlate"), 2, ST.block(MD.SC2, "BlockSlate"), 5));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Slate, MT.STONES.Slate, ST.block(MD.SC2, "BlockLightSlate"), 0, ST.block(MD.SC2, "BlockLightSlate"), 3));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Slate, MT.STONES.Slate, ST.block(MD.SC2, "BlockLightSlate"), 1, ST.block(MD.SC2, "BlockLightSlate"), 4));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Slate, MT.STONES.Slate, ST.block(MD.SC2, "BlockLightSlate"), 2, ST.block(MD.SC2, "BlockLightSlate"), 5));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Slate));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Quartzite, MT.RedSand, ST.block(MD.PFAA, "strongStone"), 9, ST.block(MD.PFAA, "strongCobble"), 9
		, new StoneLayerOres(MT.Niter                   , U6 , 40, 70, BIOMES_DESERT)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Quartzite, MT.Sand, ST.block(MD.PFAA, "strongStone"), 8, ST.block(MD.PFAA, "strongCobble"), 8
		, new StoneLayerOres(MT.Niter                   , U6 , 40, 70, BIOMES_DESERT)
		));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Quartzite, MT.Sand, ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"), 2, ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB"), 2
		, new StoneLayerOres(MT.Niter                   , U6 , 40, 70, BIOMES_DESERT)
		));
		
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Quartzite, MT.STONES.Siltstone, ST.block(MD.BoP, "rocks"), 2));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Quartzite, MT.STONES.Siltstone, ST.block(MD.UB, "sedimentaryStone"), 3+8));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Quartzite, MT.STONES.Quartzite, ST.block(MD.PFAA, "veryStrongStone"), 4, ST.block(MD.PFAA, "veryStrongCobble"), 4));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Quartzite, MT.STONES.Quartzite, ST.block(MD.UB, "metamorphicStone"), 3+8, ST.block(MD.UB, "metamorphicCobblestone"), 3));
		StoneLayer.LAYERS.add(new StoneLayer(BlocksGT.Quartzite
		, new StoneLayerOres(MT.CertusQuartz            , U16, 16, 48)
		, new StoneLayerOres(MT.OREMATS.Barite          , U32,  0, 32)
		));
		
		
		// --------------------------------
		
		
		StoneLayer.bothsides(MT.Coal, MT.Stone
		, new StoneLayerOres(MT.Amber                   , U4 , 30, 70, BIOMES_OCEAN_BEACH)
		, new StoneLayerOres(MT.Amber                   , U8 , 30, 70, BIOMES_RIVER_LAKE)
		, new StoneLayerOres(MT.AmberDominican          , U8 , 30, 70, BIOMES_SHROOM)
		);
		StoneLayer.bothsides(MT.Lignite, MT.Stone
		, new StoneLayerOres(MT.Amber                   , U4 , 30, 70, BIOMES_OCEAN_BEACH)
		, new StoneLayerOres(MT.Amber                   , U8 , 30, 70, BIOMES_RIVER_LAKE)
		, new StoneLayerOres(MT.AmberDominican          , U8 , 30, 70, BIOMES_SHROOM)
		);
		StoneLayer.bothsides(MT.Oilshale, MT.Stone
		, new StoneLayerOres(MT.Amber                   , U4 , 30, 70, BIOMES_OCEAN_BEACH)
		, new StoneLayerOres(MT.Amber                   , U8 , 30, 70, BIOMES_RIVER_LAKE)
		, new StoneLayerOres(MT.AmberDominican          , U8 , 30, 70, BIOMES_SHROOM)
		);
		StoneLayer.bothsides(MT.STONES.Komatiite, MT.STONES.Basalt
		, new StoneLayerOres(MT.OREMATS.Perlite         , U4 ,  0, 16)
		);
		StoneLayer.bothsides(MT.STONES.Gabbro, MT.STONES.Basalt
		, new StoneLayerOres(MT.OREMATS.Perlite         , U4 ,  0, 16)
		);
		StoneLayer.bothsides(MT.STONES.Limestone, MT.STONES.Basalt
		, new StoneLayerOres(MT.OREMATS.Ilmenite        , U8 ,  0, 32)
		, new StoneLayerOres(MT.TiO2                    , U12,  0, 32)
		);
		StoneLayer.topbottom(MT.Dolomite, MT.STONES.Diorite
		, new StoneLayerOres(MT.Opal                    , U64, 48, 64)
		, new StoneLayerOres(MT.OREMATS.Diatomite       , U16, 16, 64)
		);
		StoneLayer.bothsides(MT.Dolomite, MT.KCl
		, new StoneLayerOres(MT.KIO3                    , U12, 32, 64)
		);
		StoneLayer.bothsides(MT.Dolomite, MT.STONES.Quartzite
		, new StoneLayerOres(MT.OREMATS.Kyanite         , U16, 32, 72)
		, new StoneLayerOres(MT.OREMATS.Lepidolite      , U32, 16, 48)
		, new StoneLayerOres(MT.OREMATS.Spodumene       , U32, 32, 64)
		, MD.HBM.mLoaded ? null : new StoneLayerOres(MT.OREMATS.Tantalite       ,U128, 16, 48)
		, MD.HBM.mLoaded ? null : new StoneLayerOres(MT.OREMATS.Columbite       ,U128, 16, 48)
		, MD.HBM.mLoaded ? null : new StoneLayerOres(MT.OREMATS.Coltan          , U32,  8, 56)
		);
		StoneLayer.bothsides(MT.Talc, MT.NaCl
		, new StoneLayerOres(MT.OREMATS.Borax           , U8 , 16, 48)
		);
		StoneLayer.bothsides(MT.Chalk, MT.STONES.Quartzite
		, new StoneLayerOres(MT.Asbestos                , U4 ,  0, 48)
		, new StoneLayerOres(MT.Talc                    , U4 ,  0, 80)
		, new StoneLayerOres(MT.OREMATS.Glauconite      , U4 , 32, 80)
		);
		StoneLayer.bothsides(MT.STONES.Rhyolite, MT.STONES.Quartzite
		, new StoneLayerOres(MT.OREMATS.Alunite         , U4 , 32, 80)
		);
		StoneLayer.bothsides(MT.STONES.Gneiss, MT.Gypsum
		, new StoneLayerOres(MT.OREMATS.Mirabilite      , U8 , 16, 64)
		, new StoneLayerOres(MT.OREMATS.Trona           , U8 , 16, 64)
		);
		StoneLayer.bothsides(MT.STONES.Gneiss, MT.Gypsum
		, new StoneLayerOres(MT.OREMATS.Mirabilite      , U8 , 16, 64)
		, new StoneLayerOres(MT.OREMATS.Trona           , U8 , 16, 64)
		);
		StoneLayer.bothsides(MT.STONES.Granite, MT.NaCl
		, new StoneLayerOres(MT.OREMATS.Zeolite         , U8 , 16, 48)
		);
		StoneLayer.bothsides(MT.STONES.Granite, MT.KCl
		, new StoneLayerOres(MT.OREMATS.Pollucite       , U8 , 16, 48)
		);
		StoneLayer.bothsides(MT.STONES.GraniteRed, MT.STONES.Gneiss
		, new StoneLayerOres(MT.OREMATS.Vermiculite     , U8 , 48, 80)
		, new StoneLayerOres(MT.Asbestos                , U64, 16, 48)
		);
		StoneLayer.bothsides(MT.STONES.GraniteBlack, MT.STONES.Gneiss
		, new StoneLayerOres(MT.OREMATS.Mica            , U8 , 16, 48)
		, new StoneLayerOres(MT.Biotite                 , U16, 16, 48)
		);
		StoneLayer.bothsides(MT.STONES.GraniteBlack, MT.STONES.Marble
		, new StoneLayerOres(MT.Lapis                   , U8 ,  0, 48)
		, new StoneLayerOres(MT.Sodalite                , U16,  0, 48)
		, new StoneLayerOres(MT.Lazurite                , U16,  0, 48)
		, new StoneLayerOres(MT.Pyrite                  , U16,  0, 48)
		);
		StoneLayer.topbottom(MT.STONES.GraniteBlack, MT.STONES.Basalt
		, new StoneLayerOres(MT.Diamond                 , U64,  0, 32)
		, new StoneLayerOres(MT.DiamondPink             , U32,  0, 32, BIOMES_JUNGLE)
		, new StoneLayerOres(MT.Graphite                , U8 ,  0, 32)
		);
		StoneLayer.bothsides(MT.STONES.GraniteBlack, MT.STONES.Granite
		, new StoneLayerOres(MT.Zircon                  , U24,  0, 32)
		);
		StoneLayer.bothsides(MT.STONES.GraniteBlack, MT.STONES.GraniteRed
		, new StoneLayerOres(MT.Zircon                  , U24,  0, 32)
		);
		
		
		
		
		new WorldgenOcean           ("ocean.seawater"          , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA); // IT IS IMPORTANT THAT OCEAN COMES BEFORE RIVER AND SWAMP!!!
		new WorldgenRiver           ("river.riverwater"        , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT); // IT IS IMPORTANT THAT RIVER COMES AFTER OCEAN AND BEFORE SWAMP!!!
		new WorldgenSwamp           ("swamp.dirtywater"        , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TWILIGHT, GEN_EREBUS); // IT IS IMPORTANT THAT SWAMP COMES AFTER RIVER AND OCEAN!!!
		
		new WorldgenDeepOcean       ("ocean.prismacorals"      , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC);
		new WorldgenBlackSand       ("river.magnetite"         , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC, GEN_TWILIGHT, GEN_TROPICS);
		new WorldgenTurf            ("swamp.turf"              , T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC, GEN_TWILIGHT);
		
		int tChance = 320;
		if (MD.PFAA.mLoaded) {
		tChance = 512;
		new WorldgenPit             ("pit.clay.pfaa.lateric"   , T, ST.block(MD.PFAA, "weakClay"   ), 0, 1, tChance, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);
		new WorldgenPit             ("pit.clay.pfaa.bentonite" , T, ST.block(MD.PFAA, "weakOreClay"), 1, 1, tChance, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);
		new WorldgenPit             ("pit.clay.pfaa.fullers"   , T, ST.block(MD.PFAA, "weakOreClay"), 2, 1, tChance, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);
		new WorldgenPit             ("pit.clay.pfaa.kaolinite" , T, ST.block(MD.PFAA, "weakOreClay"), 3, 1, tChance, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);
		}
		new WorldgenPit             ("pit.clay.vanilla"        , T, Blocks.clay                     , 0, 1, tChance, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);
		new WorldgenPit             ("pit.clay.brown"          , T, BlocksGT.Diggables              , 1, 1, tChance, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);
		new WorldgenPit             ("pit.clay.red"            , F, BlocksGT.Diggables              , 3, 1, tChance, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM); // Disabled by default because it's supposed to be only in the Nether
		new WorldgenPit             ("pit.clay.yellow"         , T, BlocksGT.Diggables              , 4, 1, tChance, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);
		new WorldgenPit             ("pit.clay.blue"           , T, BlocksGT.Diggables              , 5, 1, tChance, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);
		new WorldgenPit             ("pit.clay.white"          , T, BlocksGT.Diggables              , 6, 1, tChance, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);
		
		new WorldgenNetherClay      ("nether.clay.red"         , T, GEN_NETHER);
		new WorldgenNetherQuartz    ("nether.netherquartz"     , T, GEN_NETHER);
		new WorldgenNetherCrystals  ("nether.nethercrystals"   , T, GEN_NETHER);
		
		new WorldgenLogDry          ("log.dry"                 , T, 1, 8, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_EREBUS, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);
		new WorldgenLogRotten       ("log.rotten"              , T, 1, 3, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_EREBUS, GEN_TWILIGHT, GEN_TROPICS);
		new WorldgenLogMossy        ("log.mossy"               , T, 1, 8, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_EREBUS, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);
		new WorldgenLogFrozen       ("log.frozen"              , T, 1, 8, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_EREBUS, GEN_TWILIGHT);
		
		new WorldgenTreeRubber      ("tree.rubber"             , T, 1, 5, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC, GEN_TWILIGHT);
		new WorldgenTreeMaple       ("tree.maple"              , T, 1, 5, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC, GEN_EREBUS, GEN_TWILIGHT);
		new WorldgenTreeWillow      ("tree.willow"             , T, 1, 4, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC, GEN_EREBUS, GEN_TWILIGHT);
		new WorldgenTreeBlueMahoe   ("tree.bluemahoe"          , T, 1, 3, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC, GEN_EREBUS, GEN_TROPICS);
		new WorldgenTreeHazel       ("tree.hazel"              , T, 1,32, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC, GEN_EREBUS, GEN_TWILIGHT, GEN_ALFHEIM);
		new WorldgenTreeCinnamon    ("tree.cinnamon"           , T, 1, 3, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC, GEN_EREBUS, GEN_TROPICS);
		new WorldgenTreeCoconut     ("tree.coconut"            , T, 1, 1, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC, GEN_ATUM, GEN_TROPICS);
		new WorldgenTreeRainbowood  ("tree.rainbowood"         , T, 1, 4, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC, GEN_EREBUS, GEN_TWILIGHT, GEN_ALFHEIM, GEN_AETHER);
		new WorldgenTreeBlueSpruce  ("tree.bluespruce"         , T, 1,32, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC, GEN_TWILIGHT);
		
		new WorldgenRocks           (   "overworld.rocks"      , T, 2, 3, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC);
		new WorldgenRacks           (      "nether.rocks"      , T      , GEN_NETHER);
		new WorldgenErebusRocks     (      "erebus.rocks"      , T      , GEN_EREBUS);
		new WorldgenRocks           (    "twilight.rocks"      , T, 4, 3, GEN_TWILIGHT);
		new WorldgenRocks           (     "tropics.rocks"      , T, 3, 3, GEN_TROPICS);
		new WorldgenAlfheimRocks    (     "alfheim.rocks"      , T, 3, 3, GEN_ALFHEIM);
		new WorldgenAetherRocks     (      "aether.rocks"      , T, 3, 3, GEN_AETHER);
		new WorldgenRocks           (        "atum.rocks"      , T, 3, 3, GEN_ATUM);
		new WorldgenMoonRocks       (        "moon.rocks"      , T      , GEN_MOON);
		new WorldgenMarsRocks       (        "mars.rocks"      , T      , GEN_MARS);
		new WorldgenPlanetRocks     (      "planet.rocks"      , T      , GEN_PLANETS);
		
		new WorldgenSticks          ("sticks"                  , T, 2, 2, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TWILIGHT, GEN_TROPICS, GEN_EREBUS, GEN_ALFHEIM, GEN_BETWEENLANDS, GEN_AETHER, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT);
		
		new WorldgenGlowtus         ("plant.glowtus"           , T,16, 2, GEN_OVERWORLD, GEN_GT, GEN_PFAA                     , GEN_TFC, GEN_EREBUS, GEN_TWILIGHT, GEN_TROPICS);
		new WorldgenBushes          ("plant.bush"              , T, 1, 4, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_EREBUS, GEN_TWILIGHT, GEN_TROPICS, GEN_ALFHEIM);
		
		new WorldgenHives           (   "overworld.bumblehives", T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT);
		new WorldgenHives           (      "nether.bumblehives", T, GEN_NETHER);
		new WorldgenHives           (         "end.bumblehives", T, GEN_END);
		new WorldgenHives           (      "erebus.bumblehives", T, GEN_EREBUS);
		new WorldgenHives           (    "twilight.bumblehives", T, GEN_TWILIGHT);
		new WorldgenHives           (     "tropics.bumblehives", T, GEN_TROPICS);
		new WorldgenHives           (     "alfheim.bumblehives", T, GEN_ALFHEIM);
		new WorldgenHives           ("betweenlands.bumblehives", T, GEN_BETWEENLANDS);
		new WorldgenHives           (      "aether.bumblehives", T, GEN_AETHER);
		new WorldgenHives           (        "atum.bumblehives", T, GEN_ATUM);
		
		new WorldgenCenterBiomes    ("center.biomes"           , F, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC);
		new WorldgenStreets         ("center.streets"          , F, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC);
		new WorldgenNexus           ("center.nexus"            , F, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC);
		new WorldgenBeacon          ("center.beacon"           , F, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC);
		new WorldgenTesting         ("center.testing"          , F, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC);
		
		new WorldgenDungeonGT("overworld.structure.dungeon.large", T, 100, 3, 7, 20, 20, 6, T, F, F, T, T, T, T, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC);
		
		for (BlockBase tStone : BlocksGT.stones) if (tStone != BlocksGT.PrismarineDark && tStone != BlocksGT.PrismarineLight) {
		new WorldgenStone("overworld.stone."+ ((BlockStones)tStone).mMaterial.mNameInternal.toLowerCase(), F, tStone,  0,  1, 200, 100, 0, 120, null, F, GEN_OVERWORLD, GEN_PFAA, GEN_A97, GEN_TFC, GEN_ENVM, GEN_CW2_AquaCavern, GEN_CW2_Caveland, GEN_CW2_Cavenia, GEN_CW2_Cavern, GEN_CW2_Caveworld);
		new WorldgenStone("nether.stone."   + ((BlockStones)tStone).mMaterial.mNameInternal.toLowerCase(), F, tStone,  0,  1, 200, 200, 0, 120, null, F, GEN_NETHER);
		new WorldgenStone("twilight.stone." + ((BlockStones)tStone).mMaterial.mNameInternal.toLowerCase(), T, tStone,  0,  1, 100, 200, 0,  40, null, F, GEN_TWILIGHT);
		new WorldgenStone("erebus.stone."   + ((BlockStones)tStone).mMaterial.mNameInternal.toLowerCase(), T, tStone,  0,  1, 200, 200, 0, 120, null, F, GEN_EREBUS);
		new WorldgenStone("atum.stone."     + ((BlockStones)tStone).mMaterial.mNameInternal.toLowerCase(), T, tStone,  0,  1, 200, 100, 0, 120, null, F, GEN_ATUM);
		new WorldgenStone("tropics.stone."  + ((BlockStones)tStone).mMaterial.mNameInternal.toLowerCase(), T, tStone,  0,  1, 200, 100, 0, 120, null, F, GEN_TROPICS);
		}
		
		Block
		tBlock = BlocksGT.RockOres;
		if (tBlock != null && tBlock != NB) {
		new WorldgenOresVanilla("twilight.ore.anthracite"       , T, tBlock,  0,  1, 50, 100, 16, 32, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.lignite"          , T, tBlock,  1,  1, 50, 100, 16, 32, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.salt"             , T, tBlock,  2,  1, 50, 100, 16, 32, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.rocksalt"         , T, tBlock,  3,  1, 50, 100, 16, 32, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.bauxite"          , T, tBlock,  4,  1, 50, 100, 16, 32, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.oilshale"         , T, tBlock,  5,  1, 50, 100, 16, 32, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.gypsum"           , T, tBlock,  6,  1, 50, 100, 16, 32, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.milkyquartz"      , T, tBlock,  7,  1, 50, 100, 16, 32, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		}
		tBlock = BlocksGT.VanillaOresA;
		if (tBlock != null && tBlock != NB) {
		new WorldgenOresVanilla("twilight.ore.sulfur"           , T, tBlock,  0,  1, 16,   1,  0,  8, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.apatite"          , T, tBlock,  1,  1, 16,   2, 24, 32, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.ruby"             , T, tBlock,  2,  1, 12,   1, 40, 52, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.amber"            , T, tBlock,  3,  1, 12,   1, 40, 52, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.amethyst"         , T, tBlock,  4,  1, 12,   1, 40, 52, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.galena"           , T, tBlock,  5,  1, 24,   4,  8, 32, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.tetrahedrite"     , T, tBlock,  6,  1, 24,   4,  8, 32, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.cassiterite"      , T, tBlock,  7,  1, 24,   4,  8, 32, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.cooperite"        , T, tBlock,  8,  1,  6,   1, 40, 52, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.pentlandite"      , T, tBlock,  9,  1, 16,   4,  8, 24, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.scheelite"        , T, tBlock, 10,  1, 12,   4,  8, 24, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.rutile"           , T, tBlock, 11,  1,  6,   1,  8, 24, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.bastnasite"       , T, tBlock, 12,  1, 16,   1, 40, 52, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.graphite"         , T, tBlock, 13,  1,  6,   2,  0,  8, Blocks.stone          , 0, null               , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.pitchblende"      , T, tBlock, 14,  1, 16,   1,  8, 16, Blocks.stone          , 0, BIOMES_SWAMP       , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.borax"            , T, tBlock, 15,  2,  6,   1,  0, 16, Blocks.stone          , 0, BIOMES_LAKE        , F, GEN_TWILIGHT);
		}
		tBlock = ST.block(MD.TC, "blockCustomOre", null);
		if (tBlock != null && tBlock != NB) {
		new WorldgenOresVanilla("twilight.ore.tc_cinnabar"      , T, tBlock,  0,  1, 12,   1,  8, 16, Blocks.stone          , 0, BIOMES_VOLCANIC    , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.tc_air"           , T, tBlock,  1,  2, 16,   1,  8, 32, Blocks.stone          , 0, BIOMES_MAGICAL     , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.tc_fire"          , T, tBlock,  2,  2, 16,   1,  8, 32, Blocks.stone          , 0, BIOMES_MAGICAL     , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.tc_water"         , T, tBlock,  3,  2, 16,   1,  8, 32, Blocks.stone          , 0, BIOMES_MAGICAL     , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.tc_earth"         , T, tBlock,  4,  2, 16,   1,  8, 32, Blocks.stone          , 0, BIOMES_MAGICAL     , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.tc_order"         , T, tBlock,  5,  2, 16,   1,  8, 32, Blocks.stone          , 0, BIOMES_MAGICAL     , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.tc_entropy"       , T, tBlock,  6,  2, 16,   1,  8, 32, Blocks.stone          , 0, BIOMES_MAGICAL     , F, GEN_TWILIGHT);
		}
		tBlock = ST.block(MD.ARS, "vinteumOre", null);
		if (tBlock != null && tBlock != NB) {
		new WorldgenOresVanilla("twilight.ore.ars_vinteum"      , T, tBlock,  0,  2, 16,   1,  8, 32, Blocks.stone          , 0, BIOMES_MAGICAL     , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.ars_chimerite"    , T, tBlock,  1,  2, 16,   1,  8, 32, Blocks.stone          , 0, BIOMES_MAGICAL     , F, GEN_TWILIGHT);
		new WorldgenOresVanilla("twilight.ore.ars_bluetopaz"    , T, tBlock,  2,  1,  8,   1,  8, 16, Blocks.stone          , 0, BIOMES_DARK_FOREST , F, GEN_TWILIGHT);
		}
		tBlock = (IL.Ancient_Debris.exists() ? IL.Ancient_Debris.block() : null);
		if (tBlock != null && tBlock != NB) {
		new WorldgenOresVanilla("twilight.ore.netherite"        , T, tBlock,  0,  8,  8,   1,  8, 80, IL.TF_Deadrock.block(), 2, BIOMES_MOUNTAINS   , F, GEN_TWILIGHT);
		}
		tBlock = ST.block(MD.TC, "blockCustomPlant", null);
		if (tBlock != null && tBlock != NB) {
		new WorldgenFlowers    ("twilight.plant.tc_greatwood"   , T, tBlock,  0,  1,  8, BIOMES_MAGICAL_GOOD, GEN_TWILIGHT);
		new WorldgenFlowers    ("twilight.plant.tc_silverwood"  , T, tBlock,  1,  1, 64, BIOMES_MAGICAL_GOOD, GEN_TWILIGHT);
		new WorldgenFlowers    ("twilight.plant.tc_shimmerleaf" , T, tBlock,  2,  2,  4, BIOMES_MAGICAL_GOOD, GEN_TWILIGHT);
		new WorldgenFlowers    ("twilight.plant.tc_cinderpearl" , T, tBlock,  3,  4,  4, BIOMES_SAVANNA     , GEN_TWILIGHT);
		new WorldgenFlowers    ("twilight.plant.tc_vishroom"    , T, tBlock,  5,  1,  8, BIOMES_SHROOM      , GEN_TWILIGHT);
		}
		
		
		// Bedrock Ores.
		new WorldgenOresBedrock("ore.bedrock.diamond"      , T, T, 128000, MT.Diamond             , BlocksGT.FlowersB, 6, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.tungstate"    , T, T,  96000, MT.OREMATS.Tungstate   , BlocksGT.FlowersB, 7, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.ferberite"    , T, T,  96000, MT.OREMATS.Ferberite   , BlocksGT.FlowersB, 7, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.wolframite"   , T, T,  96000, MT.OREMATS.Wolframite  , BlocksGT.FlowersB, 7, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.stolzite"     , T, T,  96000, MT.OREMATS.Stolzite    , BlocksGT.FlowersB, 7, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.scheelite"    , T, T,  96000, MT.OREMATS.Scheelite   , BlocksGT.FlowersB, 7, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.huebnerite"   , T, T,  96000, MT.OREMATS.Huebnerite  , BlocksGT.FlowersB, 7, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.russelite"    , T, T,  96000, MT.OREMATS.Russellite  , BlocksGT.FlowersB, 7, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.pinalite"     , T, T,  96000, MT.OREMATS.Pinalite    , BlocksGT.FlowersB, 7, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.uraninite"    , T, T,  60000, MT.OREMATS.Uraninite   , BlocksGT.FlowersA, 5, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.pitchblende"  , T, T,  60000, MT.OREMATS.Pitchblende , BlocksGT.FlowersB, 5, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.gold.a"       , T, T,  32000, MT.Au                  , BlocksGT.FlowersA, 0, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.gold.b"       , T, T,  32000, MT.Au                  , BlocksGT.FlowersB, 2, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.cooperite"    , T, T,  16000, MT.OREMATS.Cooperite   , BlocksGT.FlowersA, 6, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.copper"       , T, T,  16000, MT.Cu                  , BlocksGT.FlowersB, 3, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.monazite"     , T, T,  16000, MT.Monazite            , BlocksGT.FlowersA, 7, GEN_FLOOR); // TODO Rare Earth Flower
		new WorldgenOresBedrock("ore.bedrock.powellite"    , T, T,  14000, MT.OREMATS.Powellite   , BlocksGT.FlowersA, 7, GEN_FLOOR); // Molybdenum Flower? I think I should just keep this one as Orechid.
		new WorldgenOresBedrock("ore.bedrock.bastnasite"   , T, T,   8000, MT.OREMATS.Bastnasite  , BlocksGT.FlowersA, 7, GEN_FLOOR); // TODO Rare Earth Flower
		new WorldgenOresBedrock("ore.bedrock.stibnite"     , T, T,   8000, MT.OREMATS.Arsenopyrite, BlocksGT.FlowersB, 0, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.redstone"     , T, T,   7000, MT.Redstone            , BlocksGT.FlowersB, 4, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.vanadium"     , T, T,   6000, MT.V2O5                , BlocksGT.FlowersA, 7, GEN_FLOOR); // TODO Vanadium Flower
		new WorldgenOresBedrock("ore.bedrock.galena"       , T, T,   6000, MT.OREMATS.Galena      , BlocksGT.FlowersA, 1, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.coal"         , T, T,   5000, MT.Coal                , BlocksGT.FlowersA, 7, GEN_FLOOR); // TODO Coal/Carbon Flower
		new WorldgenOresBedrock("ore.bedrock.graphite"     , T, T,   5000, MT.Graphite            , BlocksGT.FlowersA, 7, GEN_FLOOR); // TODO Coal/Carbon Flower
		new WorldgenOresBedrock("ore.bedrock.stibnite"     , T, T,   4000, MT.OREMATS.Stibnite    , BlocksGT.FlowersB, 1, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.hematite"     , T, T,   4000, MT.Fe2O3               , BlocksGT.FlowersA, 7, GEN_FLOOR); // TODO Iron Flower
		new WorldgenOresBedrock("ore.bedrock.sphalerite"   , T, T,   3000, MT.OREMATS.Sphalerite  , BlocksGT.FlowersA, 3, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.smithsonite"  , T, T,   3000, MT.OREMATS.Smithsonite , BlocksGT.FlowersA, 3, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.pentlandite"  , T, T,   3000, MT.OREMATS.Pentlandite , BlocksGT.FlowersA, 4, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.saltpeter"    , T, T,   3000, MT.Niter               , BlocksGT.FlowersA, 7, GEN_FLOOR); // TODO Niter Flower
		new WorldgenOresBedrock("ore.bedrock.bauxite"      , T, T,   2000, MT.OREMATS.Bauxite     , BlocksGT.FlowersA, 7, GEN_FLOOR); // TODO Aluminium Flower
		new WorldgenOresBedrock("ore.bedrock.cassiterite"  , T, T,   2000, MT.OREMATS.Cassiterite , BlocksGT.FlowersA, 7, GEN_FLOOR); // TODO Tin Flower
		new WorldgenOresBedrock("ore.bedrock.chalcopyrite" , T, T,   2000, MT.OREMATS.Chalcopyrite, BlocksGT.FlowersA, 2, GEN_FLOOR);
		new WorldgenOresBedrock("ore.bedrock.voidquartz"   , T, T,   4000, MT.VoidQuartz                                , GEN_NETHER);
		new WorldgenOresBedrock("ore.bedrock.glowstone"    , T, T,   4000, MT.Glowstone                                 , GEN_NETHER);
		new WorldgenOresBedrock("ore.bedrock.gloomstone"   , T, T,   4000, MT.Gloomstone                                , GEN_NETHER);
		new WorldgenOresBedrock("ore.bedrock.efrine"       , T, T,   2000, MT.Efrine                                    , GEN_NETHER);
		new WorldgenOresBedrock("ore.bedrock.netherquartz" , T, T,   2000, MT.NetherQuartz                              , GEN_NETHER);
		new WorldgenOresBedrock("ore.bedrock.firestone"    , T, T,   8000, MT.Firestone                                 , GEN_NETHER);
		new WorldgenOresBedrock("ore.bedrock.ancientdebris", T, T,   4000, MT.AncientDebris                             , GEN_NETHER, GEN_MARS);
		new WorldgenOresBedrock("ore.bedrock.naquadah"     , T, T,  10000, MT.Nq                                        , GEN_MARS);
		new WorldgenOresBedrock("ore.bedrock.desh"         , T, T,   2000, MT.Desh                                      , GEN_MARS);
		new WorldgenOresBedrock("ore.bedrock.dolamide"     , T, T,   5000, MT.Dolamide                                  , GEN_MARS, GEN_PLANETS);
		new WorldgenOresBedrock("ore.bedrock.adamantine"   , T, T,  10000, MT.Adamantine                                , GEN_MARS, GEN_EREBUS, GEN_BETWEENLANDS);
		new WorldgenOresBedrock("ore.bedrock.octine"       , T, T,   5000, MT.Octine                                    , GEN_BETWEENLANDS);
		new WorldgenOresBedrock("ore.bedrock.syrmorite"    , T, T,   2000, MT.Syrmorite                                 , GEN_BETWEENLANDS);
		
		new WorldgenOresBedrock("ore.bedrock.hexorium"     , MD.HEX.mLoaded, T, 9000, ANY.Hexorium, BlocksGT.FlowersA, 8, GEN_FLOOR);
		
		for (int i = 0, j = ConfigsGT.WORLDGEN.get(ConfigCategories.general, "AmountOfCustomBedrockOreSlots", 0); i < j; i++) {
		new WorldgenOresBedrock("ore.bedrock.custom" + (i<10?"0":"") + i, F, T, 100000, MT.NULL, BlocksGT.FlowersA, 7, GEN_FLOOR);
		}
		
		// Special Bedrock Ore Generator just for HBMs Coltan.
		new WorldgenColtan ("ore.special.coltan"           , T,  20,  40,  32, 480, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_TFC);
		
		// Has to be after Bedrock Ores, because it would reduce Ore chances too much otherwise.
		new WorldgenFluidSpring(   "overworld.fluid.oil.extraheavy", T, BlocksGT.OilExtraHeavy  , 15, 400, tInfiniteOil ? FL.Oil_ExtraHeavy  .make( 6000) : NF, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC);
		new WorldgenFluidSpring(   "overworld.fluid.oil.heavy"     , T, BlocksGT.OilHeavy       , 15, 400, tInfiniteOil ? FL.Oil_Heavy       .make( 6000) : NF, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC);
		new WorldgenFluidSpring(   "overworld.fluid.oil.medium"    , T, BlocksGT.OilMedium      , 15, 400, tInfiniteOil ? FL.Oil_Medium      .make( 6000) : NF, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC);
		new WorldgenFluidSpring(   "overworld.fluid.oil.light"     , T, BlocksGT.OilLight       , 15, 400, tInfiniteOil ? FL.Oil_Light       .make( 6000) : NF, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC);
		new WorldgenFluidSpring(   "overworld.fluid.gas.natural"   , T, BlocksGT.GasNatural     , 15, 200, tInfiniteGas ? FL.Gas_Natural     .make( 3000) : NF, GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_TFC);
		new WorldgenFluidSpring(   "overworld.fluid.water"         , T, BlocksGT.WaterGeothermal, 15, 100,                FL.Water_Geothermal.make(  500)     , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_TFC);
		new WorldgenFluidSpring(   "overworld.fluid.lava"          , T, Blocks.lava             ,  0, 200,                FL.Lava            .make( 1000)     , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM);
		new WorldgenFluidSpring(        "atum.fluid.oil.extraheavy", T, BlocksGT.OilExtraHeavy  , 15, 200, tInfiniteOil ? FL.Oil_ExtraHeavy  .make( 2000) : NF, GEN_ATUM);
		new WorldgenFluidSpring(        "atum.fluid.oil.heavy"     , T, BlocksGT.OilHeavy       , 15, 200, tInfiniteOil ? FL.Oil_Heavy       .make( 2000) : NF, GEN_ATUM);
		new WorldgenFluidSpring(        "atum.fluid.oil.medium"    , T, BlocksGT.OilMedium      , 15, 200, tInfiniteOil ? FL.Oil_Medium      .make( 2000) : NF, GEN_ATUM);
		new WorldgenFluidSpring(        "atum.fluid.oil.light"     , T, BlocksGT.OilLight       , 15, 200, tInfiniteOil ? FL.Oil_Light       .make( 2000) : NF, GEN_ATUM);
		new WorldgenFluidSpring(      "erebus.fluid.gas.natural"   , T, BlocksGT.GasNatural     , 15, 200, tInfiniteGas ? FL.Gas_Natural     .make( 1000) : NF, GEN_EREBUS);
		new WorldgenFluidSpring("betweenlands.fluid.gas.natural"   , T, BlocksGT.GasNatural     , 15, 200, tInfiniteGas ? FL.Gas_Natural     .make( 1000) : NF, GEN_BETWEENLANDS);
		new WorldgenFluidSpring(    "twilight.fluid.gas.natural"   , T, BlocksGT.GasNatural     , 15, 200, tInfiniteGas ? FL.Gas_Natural     .make( 1000) : NF, GEN_TWILIGHT);
		new WorldgenFluidSpring(    "twilight.fluid.water"         , T, BlocksGT.WaterGeothermal, 15, 100,                FL.Water_Geothermal.make(  250)     , GEN_TWILIGHT);
		new WorldgenFluidSpring(      "nether.fluid.lava"          , T, Blocks.lava             ,  0, 100,                FL.Lava            .make(  500)     , GEN_NETHER);
		
		// Small Ores.
		new WorldgenOresSmall("ore.small.copper"           , T,  60, 120,  16, MT.Cu                  , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER            , GEN_END, GEN_MARS, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.chalcopyrite"     , T,  60, 120,  16, MT.OREMATS.Chalcopyrite, GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM                                     , GEN_END, GEN_MARS);
		new WorldgenOresSmall("ore.small.malachite"        , T,  40,  70,   8, MT.OREMATS.Malachite   , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM                                     , GEN_END, GEN_MARS);
		new WorldgenOresSmall("ore.small.tin"              , T,  60, 120,  16, MT.Sn                  , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER            , GEN_END, GEN_MARS, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.cassiterite"      , T,  60, 120,  16, MT.OREMATS.Cassiterite , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_NETHER, GEN_END, GEN_MARS);
		new WorldgenOresSmall("ore.small.zinc"             , T,  40,  70,   4, MT.Zn                  , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER            , GEN_END, GEN_MARS, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.sphalerite"       , T,  30,  60,  12, MT.OREMATS.Sphalerite  , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM                                     , GEN_END, GEN_MARS);
		new WorldgenOresSmall("ore.small.smithsonite"      , T,  30,  60,   2, MT.OREMATS.Smithsonite , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER            , GEN_END, GEN_MARS);
		new WorldgenOresSmall("ore.small.stibnite"         , T,  20,  40,   2, MT.OREMATS.Stibnite    , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM                                     , GEN_END, GEN_MARS);
		new WorldgenOresSmall("ore.small.bismuth"          , T,  80, 120,   8, MT.Bi                  , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_NETHER         , GEN_MARS, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.lead"             , T,  40,  80,  16, MT.Pb                  , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER            , GEN_END, GEN_MARS, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.galena"           , T,  40,  80,  16, MT.OREMATS.Galena      , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM                                     , GEN_END, GEN_MARS);
		new WorldgenOresSmall("ore.small.silver"           , T,  20,  40,   4, MT.Ag                  , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER            , GEN_END, GEN_MARS, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.gold"             , T,  20,  40,   4, MT.Au                  , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_NETHER, GEN_END, GEN_MARS, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.pyrite"           , T,  20,  40,   4, MT.Pyrite              , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM                                     , GEN_END, GEN_MARS, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.hematite"         , T,  40,  80,  24, MT.Fe2O3               , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER            , GEN_END, GEN_MARS);
		new WorldgenOresSmall("ore.small.pyrolusite"       , T,  20,  40,   4, MT.MnO2                , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER            , GEN_END, GEN_MARS               , GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.garnierite"       , T,  20,  40,   4, MT.OREMATS.Garnierite  , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER            , GEN_END, GEN_MARS);
		new WorldgenOresSmall("ore.small.pentlandite"      , T,  20,  40,   4, MT.OREMATS.Pentlandite , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM                                     , GEN_END, GEN_MARS);
		new WorldgenOresSmall("ore.small.scheelite"        , T,   5,  50,   1, MT.OREMATS.Scheelite   , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_NETHER, GEN_END, GEN_MARS, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.salt"             , T,  40,  80,   6, MT.NaCl                , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_NETHER, GEN_END, GEN_MARS);
		new WorldgenOresSmall("ore.small.rocksalt"         , T,  40,  80,   6, MT.KCl                 , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_NETHER, GEN_END, GEN_MARS);
		new WorldgenOresSmall("ore.small.borax"            , T,  10,  40,   4, MT.OREMATS.Borax       , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_NETHER, GEN_END, GEN_MARS);
		new WorldgenOresSmall("ore.small.asbestos"         , T,  20,  40,   8, MT.Asbestos            , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM                         , GEN_NETHER);
		new WorldgenOresSmall("ore.small.diamond"          , T,   5,  10,   2, MT.Diamond             , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM             , GEN_AETHER, GEN_NETHER         , GEN_MARS, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.amber"            , T,   5,  70,   1, MT.Amber               , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM             , GEN_AETHER);
		new WorldgenOresSmall("ore.small.craponite"        , T,   5, 250,   2, MT.Craponite           , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_NETHER, GEN_END, GEN_MARS, GEN_ASTEROIDS, GEN_MOON, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.redstone"         , T,   5,  20,  16, MT.Redstone            , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_NETHER         , GEN_MARS                         , GEN_PLANETS);
		new WorldgenOresSmall("ore.small.lapis"            , T,  20,  40,   8, MT.Lapis               , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER);
		new WorldgenOresSmall("ore.small.eudialyte"        , T,  20,  40,   4, MT.Eudialyte           , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT);
		new WorldgenOresSmall("ore.small.azurite"          , T,  20,  40,   4, MT.Azurite             , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT);
		new WorldgenOresSmall("ore.small.coal"             , T,  60, 100,  24, MT.Coal                , GEN_OVERWORLD, GEN_GT          , GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.graphite"         , T,   5,  10,   2, MT.Graphite            , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM, GEN_AETHER, GEN_NETHER         , GEN_MARS, GEN_ASTEROIDS          , GEN_PLANETS);
		new WorldgenOresSmall("ore.small.pollucite"        , T,   1, 250,   1, MT.OREMATS.Pollucite   , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM            , GEN_NETHER);
		new WorldgenOresSmall("ore.small.zeolite"          , T,   1, 250,   1, MT.OREMATS.Zeolite     , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_ALFHEIM            , GEN_NETHER);
		new WorldgenOresSmall("ore.small.coltan"           , T,   1, 250,   4, MT.OREMATS.Coltan      , GEN_END, GEN_ASTEROIDS, GEN_ALFHEIM, GEN_AETHER, GEN_NETHER, GEN_EREBUS, GEN_BETWEENLANDS);
		new WorldgenOresSmall("ore.small.platinum"         , T,  20,  40,   6, MT.Pt                  , GEN_END, GEN_ASTEROIDS, GEN_ALFHEIM, GEN_AETHER);
		new WorldgenOresSmall("ore.small.iridium"          , T,  20,  40,   6, MT.Ir                  , GEN_END, GEN_ASTEROIDS, GEN_ALFHEIM, GEN_AETHER);
		new WorldgenOresSmall("ore.small.sperrylite"       , T,  20,  40,   4, MT.OREMATS.Sperrylite  , GEN_END, GEN_ASTEROIDS, GEN_ALFHEIM, GEN_AETHER);
		new WorldgenOresSmall("ore.small.cooperite"        , T,  20,  40,   4, MT.OREMATS.Cooperite   , GEN_END, GEN_ASTEROIDS);
		new WorldgenOresSmall("ore.small.naquadah"         , T,  10,  80,   6, MT.Nq                  , GEN_END, GEN_ASTEROIDS, GEN_MARS, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.trinium"          , T,  10,  80,  12, MT.Ke                  , GEN_END, GEN_ASTEROIDS, GEN_MARS, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.dolamide"         , T,   5, 250,   8, MT.Dolamide                     , GEN_ASTEROIDS, GEN_MARS, GEN_PLANETS);
		new WorldgenOresSmall("ore.small.endium"           , T,  10,  80,  32, MT.Endium              , GEN_END);
		new WorldgenOresSmall("ore.small.sugilite"         , T,  10,  80,  16, MT.Sugilite            , GEN_END);
		new WorldgenOresSmall("ore.small.ambrosium"        , T,  30, 120,  64, MT.Ambrosium           , GEN_AETHER);
		new WorldgenOresSmall("ore.small.zanite"           , T,  30, 120,  16, MT.Zanite              , GEN_AETHER);
		new WorldgenOresSmall("ore.small.sulfur"           , T,   5,  15,   8, MT.S                   , GEN_OVERWORLD, GEN_GT, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_MARS);
		new WorldgenOresSmall("ore.small.niter"            , T,  10, 120,  32, MT.Niter               , GEN_NETHER);
		new WorldgenOresSmall("ore.small.efrine"           , T,  90, 120,   8, MT.Efrine              , GEN_NETHER);
		new WorldgenOresSmall("ore.small.cinnabar"         , T,   5, 250,  16, MT.OREMATS.Cinnabar    , GEN_NETHER);
		new WorldgenOresSmall("ore.small.ancientdebris"    , !IL.Ancient_Debris.exists(), 5, 90, 16, MT.AncientDebris, GEN_NETHER, GEN_MARS);
		
		new WorldgenOresSmall("ore.small.blackquartz"      , MD.AA      .mLoaded,  20,  40,   1, MT.BlackQuartz                , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.certus"           , MD.AE      .mLoaded,  20,  40,   1, MT.CertusQuartz               , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_ASTEROIDS, GEN_MOON);
		new WorldgenOresSmall("ore.small.vinteum"          , MD.ARS     .mLoaded,  30,  60,   8, MT.Vinteum                    , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM);
		new WorldgenOresSmall("ore.small.chimerite"        , MD.ARS     .mLoaded,  10,  40,   8, MT.Chimerite                  , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM);
		new WorldgenOresSmall("ore.small.hexorium_red"     , MD.HEX     .mLoaded,   0,  60,  30, MT.HexoriumRed                , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.hexorium_green"   , MD.HEX     .mLoaded,   0,  60,  30, MT.HexoriumGreen              , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.hexorium_blue"    , MD.HEX     .mLoaded,   0,  60,  30, MT.HexoriumBlue               , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.hexorium_black"   , MD.HEX     .mLoaded,   0,  20,   6, MT.HexoriumBlack              , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.hexorium_white"   , MD.HEX     .mLoaded,   0,  20,   6, MT.HexoriumWhite              , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.perdito"          , MD.TC      .mLoaded,  10,  60,   8, MT.InfusedEntropy             , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.ignis"            , MD.TC      .mLoaded,  10,  60,   8, MT.InfusedFire                , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.aer"              , MD.TC      .mLoaded,  10,  60,   8, MT.InfusedAir                 , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.aqua"             , MD.TC      .mLoaded,  10,  60,   8, MT.InfusedWater               , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.terra"            , MD.TC      .mLoaded,  10,  60,   8, MT.InfusedEarth               , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.ordo"             , MD.TC      .mLoaded,  10,  60,   8, MT.InfusedOrder               , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		new WorldgenOresSmall("ore.small.bischofite"       , MD.IHL     .mLoaded,  40,  80,   1, MT.OREMATS.Bischofite         , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.datolite"         , MD.IHL     .mLoaded,  40,  80,   1, MT.Datolite                   , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.mica"             , MD.IHL     .mLoaded,  40,  80,   1, MT.OREMATS.Mica               , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.gypsum"           , MD.IHL     .mLoaded,  40,  80,   1, MT.Gypsum                     , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.potassiumfeldspar", MD.IHL     .mLoaded,  40,  80,   1, MT.PotassiumFeldspar          , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.trona"            , MD.IHL     .mLoaded,  40,  80,   1, MT.OREMATS.Trona              , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM);
		new WorldgenOresSmall("ore.small.nikolite"         ,!MT.Nikolite.mHidden,  10,  40,   4, MT.Nikolite                   , GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_A97, GEN_A97_GT, GEN_ENVM, GEN_ENVM_GT, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_AETHER, GEN_MARS, GEN_PLANETS, GEN_ALFHEIM, GEN_NETHER, GEN_END);
		
		for (OreDictMaterial tGem : OreDictMaterial.MATERIAL_ARRAY) if (tGem != null && tGem.contains(TD.Properties.RANDOM_SMALL_GEM_ORE)) {
		new WorldgenOresSmall("ore.small."+tGem.mNameInternal.toLowerCase(), T, 5, 250, 1, tGem, GEN_GEMS);
		if (ConfigsGT.WORLDGEN.get("ore.random_small_gem_ores", tGem.mNameInternal, T)) StoneLayer.RANDOM_SMALL_GEM_ORES.add(tGem);
		}
		
		for (int i = 0, j = ConfigsGT.WORLDGEN.get(ConfigCategories.general, "AmountOfCustomSmallOreSlots", 0); i < j; i++) {
		new WorldgenOresSmall("ore.small.custom"+(i<10?"0":"") + i, F, 0, 0, 0, MT.NULL, GEN_ALL);
		}
		
		new WorldgenOresLarge("ore.large.lignite"     , T, T, 50, 130, 160, 8, 32, MT.Lignite                      , MT.Lignite                     , MT.Lignite                     , MT.Coal               , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM);
		new WorldgenOresLarge("ore.large.coal"        , T, T, 50,  80,  80, 6, 32, MT.Coal                         , MT.Coal                        , MT.Coal                        , MT.Lignite            , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM);
		new WorldgenOresLarge("ore.large.apatite"     , T, T, 40,  60,  60, 3, 16, MT.Apatite                      , MT.Apatite                     , MT.PhosphorusBlue              , MT.PO4                , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS);
		new WorldgenOresLarge("ore.large.lapis"       , T, T, 20,  50,  40, 5, 16, MT.Lazurite                     , MT.Sodalite                    , MT.Lapis                       , MT.Azurite            , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS);
		new WorldgenOresLarge("ore.large.bauxite"     , T, T, 50,  90,  80, 4, 24, MT.OREMATS.Bauxite              , MT.OREMATS.Bauxite             , MT.OREMATS.Bauxite             , MT.OREMATS.Ilmenite   , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS);
		new WorldgenOresLarge("ore.large.iodinesalt"  , T, T, 50,  60,  30, 3, 24, MT.KIO3                         , MT.NaCl                        , MT.OREMATS.Borax               , MT.OREMATS.Zeolite    , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.rocksalt"    , T, T, 50,  60,  30, 3, 24, MT.KCl                          , MT.OREMATS.Coltan              , MT.OREMATS.Lepidolite          , MT.OREMATS.Spodumene  , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.asbestos"    , T, T, 10,  40,  30, 3, 16, MT.OREMATS.Chromite             , MT.Talc                        , MT.Gypsum                      , MT.Asbestos           , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.sapphire"    , T, T, 10,  40,  30, 3, 16, MT.BlueSapphire                 , MT.OrangeSapphire              , MT.YellowSapphire              , MT.Ruby               , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.sapphire2"   , T, T, 10,  40,  30, 3, 16, MT.GreenSapphire                , MT.Ruby                        , MT.BlueSapphire                , MT.PurpleSapphire     , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.garnet"      , T, T, 10,  40,  60, 3, 16, MT.Almandine                    , MT.Pyrope                      , MT.Andradite                   , MT.Uvarovite          , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.pitchblende" , T, T, 10,  40,  40, 3, 16, MT.OREMATS.Pitchblende          , MT.OREMATS.Pitchblende         , MT.OREMATS.Uraninite           , MT.OREMATS.Uraninite  , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS);
		new WorldgenOresLarge("ore.large.monazite"    , T, T, 20,  40,  30, 3, 16, MT.OREMATS.Bastnasite           , MT.OREMATS.Bastnasite          , MT.Monazite                    , MT.Nd                 , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS);
		new WorldgenOresLarge("ore.large.diamond"     , T, T,  5,  20,  40, 2, 16, MT.Graphite                     , MT.Graphite                    , MT.Diamond                     , MT.Graphite           , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS);
		new WorldgenOresLarge("ore.large.galena"      , T, T, 30,  60,  40, 5, 16, MT.OREMATS.Galena               , MT.OREMATS.Galena              , MT.Ag                          , MT.Pb                 , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS);
		new WorldgenOresLarge("ore.large.quartz"      , T, T, 40,  80,  60, 3, 16, MT.MilkyQuartz                  , MT.OREMATS.Barite              , MT.CertusQuartz                , MT.CertusQuartz       , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS, ORE_MOON, ORE_ASTEROIDS);
		new WorldgenOresLarge("ore.large.olivine"     , T, T, 10,  40,  60, 3, 16, MT.OREMATS.Kyanite              , MT.MgCO3                       , MT.Olivine                     , MT.OREMATS.Glauconite , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS, ORE_MOON);
		new WorldgenOresLarge("ore.large.gold"        , T, T, 20,  30,   5, 3, 16, MT.Pyrite                       , MT.OREMATS.Chalcopyrite        , MT.OREMATS.Arsenopyrite        , MT.Au                 , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS, ORE_ASTEROIDS);
		new WorldgenOresLarge("ore.large.platinum"    , T, T, 40,  50,   5, 3, 16, MT.OREMATS.Cooperite            , MT.Pd                          , MT.OREMATS.Sperrylite          , MT.Ir                 , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS, ORE_END, ORE_ASTEROIDS);
		new WorldgenOresLarge("ore.large.molybdenum"  , T, T, 20,  50,   5, 3, 16, MT.OREMATS.Wulfenite            , MT.OREMATS.Molybdenite         , MT.Mo                          , MT.OREMATS.Powellite  , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS, ORE_END);
		new WorldgenOresLarge("ore.large.cassiterite" , T, T, 40,  90, 170, 5, 24, MT.OREMATS.Stannite             , MT.OREMATS.Kesterite           , MT.OREMATS.Huebnerite          , MT.OREMATS.Cassiterite, ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS, ORE_END);
		new WorldgenOresLarge("ore.large.tungstate"   , T, T, 20,  50,  10, 3, 16, MT.OREMATS.Scheelite            , MT.OREMATS.Russellite          , MT.OREMATS.Tungstate           , MT.OREMATS.Pinalite   , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_ASTEROIDS);
		new WorldgenOresLarge("ore.large.manganese"   , T, T, 20,  30,  20, 3, 16, MT.Grossular                    , MT.Spessartine                 , MT.MnO2                        , MT.OREMATS.Coltan     , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.beryllium"   , T, T,  5,  30,  15, 3, 16, MT.Aquamarine                   , MT.Maxixe                      , MT.Emerald                     , MT.Th                 , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.beryllium2"  , T, T,  5,  30,  15, 3, 16, MT.Bixbite                      , MT.Goshenite                   , MT.Heliodor                    , MT.Morganite          , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.titanium"    , T, T, 10,  40,  40, 3, 16, MT.TiO2                         , MT.TiO2                        , MT.Zircon                      , MT.OREMATS.Ilmenite   , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.nickel"      , T, T, 10,  40,  40, 3, 16, MT.OREMATS.Garnierite           , MT.Ni                          , MT.OREMATS.Cobaltite           , MT.OREMATS.Pentlandite, ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.redstone"    , T, T, 10,  40,  60, 3, 24, MT.Redstone                     , MT.Redstone                    , MT.Ruby                        , MT.OREMATS.Cinnabar   , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS);
		new WorldgenOresLarge("ore.large.tetrahedrite", T, T, 70, 120, 150, 4, 24, MT.OREMATS.Tetrahedrite         , MT.OREMATS.Tetrahedrite        , MT.Cu                          , MT.OREMATS.Stibnite   , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS, ORE_PLANETS);
		new WorldgenOresLarge("ore.large.iron"        , T, T, 10,  40, 120, 4, 24, MT.OREMATS.BrownLimonite        , MT.OREMATS.YellowLimonite      , MT.Fe2O3                       , MT.OREMATS.Malachite  , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.copper"      , T, T, 10,  30,  80, 4, 24, MT.OREMATS.Chalcopyrite         , MT.Fe2O3                       , MT.Pyrite                      , MT.Cu                 , ORE_OVERWORLD, ORE_A97, ORE_ENVM, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_EREBUS, ORE_ATUM, ORE_BETWEENLANDS, ORE_MARS);
		new WorldgenOresLarge("ore.large.adamantium"  , T, T, 10, 120,   5, 2, 16, MT.OREMATS.BrownLimonite        , MT.OREMATS.YellowLimonite      , MT.Fe2O3                       , MT.Adamantine         , ORE_MARS);
		new WorldgenOresLarge("ore.large.naquadah"    , T, T, 10,  60,  10, 4, 32, MT.Nq                           , MT.Nq                          , MT.Nq                          , MT.Nq_528             , ORE_MARS, ORE_PLANETS, ORE_ASTEROIDS, ORE_END);
		new WorldgenOresLarge("ore.large.trinium"     , T, T, 10,  90, 100, 1, 12, MT.Ke                           , MT.Ke                          , MT.Ke                          , MT.Ke                 , ORE_MARS, ORE_PLANETS, ORE_ASTEROIDS, ORE_END);
		new WorldgenOresLarge("ore.large.dolamide"    , T, T,  5,  60,  40, 3, 16, MT.OREMATS.DuraniumHexaiodide   , MT.OREMATS.DuraniumHexafluoride, MT.OREMATS.DuraniumHexachloride, MT.Dolamide           , ORE_MARS, ORE_PLANETS, ORE_ASTEROIDS);
		new WorldgenOresLarge("ore.large.moonmars"    , T, T, 10,  90, 240, 1,  8, MT.MgCO3                        , MT.MnO2                        , MT.Al2O3                       , MT.TiO2               , ORE_MARS, ORE_PLANETS, ORE_MOON);
		new WorldgenOresLarge("ore.large.cheese"      , T, T, 10,  90, 100, 3, 16, MT.Cheese                       , MT.Cheese                      , MT.Cheese                      , MT.Se                 , ORE_MOON);
		new WorldgenOresLarge("ore.large.desh"        , T, T, 10,  90, 100, 3, 16, MT.OREMATS.TritaniumHexafluoride, MT.OREMATS.DuraniumHexaastatide, MT.OREMATS.DuraniumHexabromide , MT.Desh               , ORE_MARS);
		new WorldgenOresLarge("ore.large.syrmorite"   , T, T, 30,  45, 160, 2, 32, MT.Syrmorite                    , MT.Syrmorite                   , MT.Syrmorite                   , MT.Syrmorite          , ORE_BETWEENLANDS);
		new WorldgenOresLarge("ore.large.octine"      , T, T, 10,  25,  40, 1, 32, MT.Octine                       , MT.Octine                      , MT.Octine                      , MT.Octine             , ORE_BETWEENLANDS);
		
		for (int i = 0, j = ConfigsGT.WORLDGEN_GT5.get(ConfigCategories.general, "AmountOfCustomLargeVeinSlots", 0); i < j; i++) {
		new WorldgenOresLarge("ore.large.custom"+(i<10?"0":"") + i, F, T, 0, 0, 0, 0, 0, MT.NULL, MT.NULL, MT.NULL, MT.NULL, ORE_ALL);
		}
	}
}
