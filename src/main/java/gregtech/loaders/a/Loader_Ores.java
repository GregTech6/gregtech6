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

package gregtech.loaders.a;

import static gregapi.data.CS.*;

import gregapi.block.behaviors.Drops;
import gregapi.block.behaviors.Drops_None;
import gregapi.block.behaviors.Drops_SmallOre;
import gregapi.block.prefixblock.PrefixBlock;
import gregapi.block.prefixblock.PrefixBlock_;
import gregapi.code.ItemStackContainer;
import gregapi.code.ModData;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.GarbageGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.render.BlockTextureCopied;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class Loader_Ores implements Runnable {
	@Override
	public void run() {
		BlocksGT.oreBedrock                 = new PrefixBlock_(MD.GT, "gt.meta.ore.normal.bedrock"          , OP.oreBedrock             , null, null, new Drops_None()          , BlockTextureCopied.get(Blocks.bedrock         , 0), Material.rock, Block.soundTypePiston  , TOOL_pickaxe,-1,3600000F,9999,9999,9999,0,0,0,1,1,1,F,F,T,T,T,T,F,F,F,F,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreSmallBedrock            = new PrefixBlock_(MD.GT, "gt.meta.ore.small.bedrock"           , OP.oreSmall               , null, null, new Drops_None()          , BlockTextureCopied.get(Blocks.bedrock         , 0), Material.rock, Block.soundTypePiston  , TOOL_pickaxe,-1,3600000F,9999,9999,9999,0,0,0,1,1,1,F,F,T,T,T,T,F,F,F,F,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		
		ItemsGT.DEBUG_ITEMS.add((Block)BlocksGT.oreBedrock);
		ItemsGT.DEBUG_ITEMS.add((Block)BlocksGT.oreSmallBedrock);
		
		ItemsGT.ILLEGAL_DROPS.add((Block)BlocksGT.oreBedrock);
		ItemsGT.ILLEGAL_DROPS.add((Block)BlocksGT.oreSmallBedrock);
		
		GarbageGT.BLACKLIST.add((Block)BlocksGT.oreBedrock);
		GarbageGT.BLACKLIST.add((Block)BlocksGT.oreSmallBedrock);
		
		BlocksGT.oreBroken                  = new PrefixBlock_(MD.GT, "gt.meta.ore.broken.default"          , OP.oreVanillastone        , null                                  , BlockTextureCopied.get(Blocks.cobblestone     , 0), Material.rock, Block.soundTypeStone   , TOOL_pickaxe  , 0.50F, 0.50F,  -1,   0, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreBrokenSandstone         = new PrefixBlock_(MD.GT, "gt.meta.ore.broken.sandstone"        , OP.oreSandstone           , null                                  , BlockTextureCopied.get(Blocks.sandstone       , 0), Material.rock, Block.soundTypeStone   , TOOL_pickaxe  , 0.30F, 0.40F,  -1,   0, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreBrokenNetherrack        = new PrefixBlock_(MD.GT, "gt.meta.ore.broken.netherrack"       , OP.oreNetherrack          , null                                  , BlockTextureCopied.get(Blocks.netherrack      , 0), Material.rock, Block.soundTypeStone   , TOOL_pickaxe  , 0.20F, 0.20F,  -1,   0, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreBrokenEndstone          = new PrefixBlock_(MD.GT, "gt.meta.ore.broken.endstone"         , OP.oreEndstone            , null                                  , BlockTextureCopied.get(Blocks.end_stone       , 0), Material.rock, Block.soundTypeStone   , TOOL_pickaxe  , 0.50F, 1.00F,  -1,   0, T,T, OreDictMaterial.MATERIAL_ARRAY);
		
		BlocksGT.ore                        = new PrefixBlock_(MD.GT, "gt.meta.ore.normal.default"          , OP.oreVanillastone        , null                                  , BlockTextureCopied.get(Blocks.stone           , 0), Material.rock, Block.soundTypeStone   , TOOL_pickaxe  , 1.00F, 1.00F,   0,   0, F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreSandstone               = new PrefixBlock_(MD.GT, "gt.meta.ore.normal.sandstone"        , OP.oreSandstone           , null                                  , BlockTextureCopied.get(Blocks.sandstone       , 0), Material.rock, Block.soundTypeStone   , TOOL_pickaxe  , 0.60F, 0.80F,   0,   0, F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreNetherrack              = new PrefixBlock_(MD.GT, "gt.meta.ore.normal.netherrack"       , OP.oreNetherrack          , null                                  , BlockTextureCopied.get(Blocks.netherrack      , 0), Material.rock, Block.soundTypeStone   , TOOL_pickaxe  , 0.50F, 0.50F,   0,   0, F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreEndstone                = new PrefixBlock_(MD.GT, "gt.meta.ore.normal.endstone"         , OP.oreEndstone            , null                                  , BlockTextureCopied.get(Blocks.end_stone       , 0), Material.rock, Block.soundTypeStone   , TOOL_pickaxe  , 1.00F, 2.00F,   0,   0, F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreGravel                  = new PrefixBlock_(MD.GT, "gt.meta.ore.normal.gravel"           , OP.oreGravel              , null                                  , BlockTextureCopied.get(Blocks.gravel          , 0), Material.sand, Block.soundTypeGravel  , TOOL_shovel   , 0.60F, 0.80F,   0,   0, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreSand                    = new PrefixBlock_(MD.GT, "gt.meta.ore.normal.sand"             , OP.oreSand                , null                                  , BlockTextureCopied.get(Blocks.sand            , 0), Material.sand, Block.soundTypeSand    , TOOL_shovel   , 0.40F, 0.60F,   0,   0, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreRedSand                 = new PrefixBlock_(MD.GT, "gt.meta.ore.normal.redsand"          , OP.oreRedSand             , null                                  , BlockTextureCopied.get(Blocks.sand            , 1), Material.sand, Block.soundTypeSand    , TOOL_shovel   , 0.40F, 0.60F,   0,   0, T,F, OreDictMaterial.MATERIAL_ARRAY);
		
		BlocksGT.oreSmall                   = new PrefixBlock_(MD.GT, "gt.meta.ore.small.default"           , OP.oreSmall               , new Drops_SmallOre(MT.Stone)          , BlockTextureCopied.get(Blocks.stone           , 0), Material.rock, Block.soundTypeStone   , TOOL_pickaxe  , 1.00F, 1.00F,  -1,   0, F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreSmallSandstone          = new PrefixBlock_(MD.GT, "gt.meta.ore.small.sandstone"         , OP.oreSmall               , new Drops_SmallOre(MT.Sand)           , BlockTextureCopied.get(Blocks.sandstone       , 0), Material.rock, Block.soundTypeStone   , TOOL_pickaxe  , 0.60F, 0.80F,  -1,   0, F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreSmallNetherrack         = new PrefixBlock_(MD.GT, "gt.meta.ore.small.netherrack"        , OP.oreSmall               , new Drops_SmallOre(MT.Netherrack)     , BlockTextureCopied.get(Blocks.netherrack      , 0), Material.rock, Block.soundTypeStone   , TOOL_pickaxe  , 0.50F, 0.50F,  -1,   0, F,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreSmallEndstone           = new PrefixBlock_(MD.GT, "gt.meta.ore.small.endstone"          , OP.oreSmall               , new Drops_SmallOre(MT.Endstone)       , BlockTextureCopied.get(Blocks.end_stone       , 0), Material.rock, Block.soundTypeStone   , TOOL_pickaxe  , 1.00F, 2.00F,  -1,   0, F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreSmallGravel             = new PrefixBlock_(MD.GT, "gt.meta.ore.small.gravel"            , OP.oreSmall               , new Drops_SmallOre(MT.Stone)          , BlockTextureCopied.get(Blocks.gravel          , 0), Material.sand, Block.soundTypeGravel  , TOOL_shovel   , 0.60F, 0.80F,  -1,   0, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreSmallSand               = new PrefixBlock_(MD.GT, "gt.meta.ore.small.sand"              , OP.oreSmall               , new Drops_SmallOre(MT.Sand)           , BlockTextureCopied.get(Blocks.sand            , 0), Material.sand, Block.soundTypeSand    , TOOL_shovel   , 0.40F, 0.60F,  -1,   0, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreSmallRedSand            = new PrefixBlock_(MD.GT, "gt.meta.ore.small.redsand"           , OP.oreSmall               , new Drops_SmallOre(MT.RedSand)        , BlockTextureCopied.get(Blocks.sand            , 1), Material.sand, Block.soundTypeSand    , TOOL_shovel   , 0.40F, 0.60F,  -1,   0, T,F, OreDictMaterial.MATERIAL_ARRAY);
		
		((PrefixBlock)BlocksGT.ore          ).mDrops = new Drops(BlocksGT.oreBroken          , BlocksGT.ore          , OP.oreRaw.mRegisteredPrefixItems.get(0), 0, 1);
		((PrefixBlock)BlocksGT.oreSandstone ).mDrops = new Drops(BlocksGT.oreBrokenSandstone , BlocksGT.oreSandstone , OP.oreRaw.mRegisteredPrefixItems.get(0), 0, 1);
		((PrefixBlock)BlocksGT.oreNetherrack).mDrops = new Drops(BlocksGT.oreBrokenNetherrack, BlocksGT.oreNetherrack, OP.oreRaw.mRegisteredPrefixItems.get(0), 0, 1);
		((PrefixBlock)BlocksGT.oreEndstone  ).mDrops = new Drops(BlocksGT.oreBrokenEndstone  , BlocksGT.oreEndstone  , OP.oreRaw.mRegisteredPrefixItems.get(0), 2, 3);
		((PrefixBlock)BlocksGT.oreGravel    ).mDrops = new Drops(BlocksGT.oreGravel          , BlocksGT.oreGravel    , OP.oreRaw.mRegisteredPrefixItems.get(0), 0, 1);
		((PrefixBlock)BlocksGT.oreSand      ).mDrops = new Drops(BlocksGT.oreSand            , BlocksGT.oreSand      , OP.oreRaw.mRegisteredPrefixItems.get(0), 0, 1);
		((PrefixBlock)BlocksGT.oreRedSand   ).mDrops = new Drops(BlocksGT.oreRedSand         , BlocksGT.oreRedSand   , OP.oreRaw.mRegisteredPrefixItems.get(0), 0, 1);
		
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(Blocks.stone     , 1, 0), BlocksGT.ore                );
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(Blocks.netherrack, 1, 0), BlocksGT.oreNetherrack      );
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(Blocks.end_stone , 1, 0), BlocksGT.oreEndstone        );
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(Blocks.gravel    , 1, 0), BlocksGT.oreGravel          );
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(Blocks.sandstone , 1, 0), BlocksGT.oreSandstone       );
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(Blocks.sand      , 1, 0), BlocksGT.oreSand            );
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(Blocks.sand      , 1, 1), BlocksGT.oreRedSand         );
		
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(Blocks.stone     , 1, 0), BlocksGT.oreBroken          );
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(Blocks.netherrack, 1, 0), BlocksGT.oreBrokenNetherrack);
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(Blocks.end_stone , 1, 0), BlocksGT.oreBrokenEndstone  );
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(Blocks.gravel    , 1, 0), BlocksGT.oreGravel          );
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(Blocks.sandstone , 1, 0), BlocksGT.oreBrokenSandstone );
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(Blocks.sand      , 1, 0), BlocksGT.oreSand            );
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(Blocks.sand      , 1, 1), BlocksGT.oreRedSand         );
		
		BlocksGT.stoneToSmallOres.put(new ItemStackContainer(Blocks.stone      , 1, 0), BlocksGT.oreSmall           );
		BlocksGT.stoneToSmallOres.put(new ItemStackContainer(Blocks.gravel     , 1, 0), BlocksGT.oreSmallGravel     );
		BlocksGT.stoneToSmallOres.put(new ItemStackContainer(Blocks.netherrack , 1, 0), BlocksGT.oreSmallNetherrack );
		BlocksGT.stoneToSmallOres.put(new ItemStackContainer(Blocks.end_stone  , 1, 0), BlocksGT.oreSmallEndstone   );
		BlocksGT.stoneToSmallOres.put(new ItemStackContainer(Blocks.sandstone  , 1, 0), BlocksGT.oreSmallSandstone  );
		BlocksGT.stoneToSmallOres.put(new ItemStackContainer(Blocks.sand       , 1, 0), BlocksGT.oreSmallSand       );
		BlocksGT.stoneToSmallOres.put(new ItemStackContainer(Blocks.sand       , 1, 1), BlocksGT.oreSmallRedSand    );
		
		BlocksGT.stoneOverridable.add(BlocksGT.ore                ); BlocksGT.drillableDynamite.add(BlocksGT.ore                );
		BlocksGT.stoneOverridable.add(BlocksGT.oreNetherrack      ); BlocksGT.drillableDynamite.add(BlocksGT.oreNetherrack      );
		BlocksGT.stoneOverridable.add(BlocksGT.oreEndstone        ); BlocksGT.drillableDynamite.add(BlocksGT.oreEndstone        );
		BlocksGT.stoneOverridable.add(BlocksGT.oreBroken          ); BlocksGT.drillableDynamite.add(BlocksGT.oreBroken          );
		BlocksGT.stoneOverridable.add(BlocksGT.oreBrokenNetherrack); BlocksGT.drillableDynamite.add(BlocksGT.oreBrokenNetherrack);
		BlocksGT.stoneOverridable.add(BlocksGT.oreBrokenEndstone  ); BlocksGT.drillableDynamite.add(BlocksGT.oreBrokenEndstone  );
		BlocksGT.stoneOverridable.add(BlocksGT.oreSmall           ); BlocksGT.drillableDynamite.add(BlocksGT.oreSmall           );
		BlocksGT.stoneOverridable.add(BlocksGT.oreSmallGravel     ); BlocksGT.drillableDynamite.add(BlocksGT.oreSmallGravel     );
		BlocksGT.stoneOverridable.add(BlocksGT.oreSmallNetherrack ); BlocksGT.drillableDynamite.add(BlocksGT.oreSmallNetherrack );
		
		//====================================================================================================//
		
		BlocksGT.oreMud                     = new PrefixBlock_(MD.GT, "gt.meta.ore.normal.mud"              , OP.oreMud                 , null                                  , BlockTextureCopied.get(BlocksGT.Diggables     , 0), Material.ground,Block.soundTypeGravel , TOOL_shovel   , 0.30F, 0.50F,   0,   0, T,F, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.oreSmallMud                = new PrefixBlock_(MD.GT, "gt.meta.ore.small.mud"               , OP.oreSmall               , new Drops_SmallOre(null)              , BlockTextureCopied.get(BlocksGT.Diggables     , 0), Material.ground,Block.soundTypeGravel , TOOL_shovel   , 0.30F, 0.50F,  -1,   0, T,F, OreDictMaterial.MATERIAL_ARRAY);
		
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(BlocksGT.Diggables, 1, 0), BlocksGT.oreMud     );
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(BlocksGT.Diggables, 1, 0), BlocksGT.oreMud     );
		BlocksGT.stoneToSmallOres .put(new ItemStackContainer(BlocksGT.Diggables, 1, 0), BlocksGT.oreSmallMud);
		
		((PrefixBlock)BlocksGT.oreMud).mDrops = new Drops(BlocksGT.oreMud, BlocksGT.oreMud, OP.oreRaw.mRegisteredPrefixItems.get(0), 0, 1);
		
		//====================================================================================================//
		
		for (Object tBlock : new Object[] {BlocksGT.ore, BlocksGT.oreSandstone, BlocksGT.oreNetherrack, BlocksGT.oreEndstone, BlocksGT.oreGravel, BlocksGT.oreMud, BlocksGT.oreSand, BlocksGT.oreRedSand, BlocksGT.oreBroken, BlocksGT.oreBrokenSandstone, BlocksGT.oreBrokenNetherrack, BlocksGT.oreBrokenEndstone, BlocksGT.oreSmall, BlocksGT.oreSmallSandstone, BlocksGT.oreSmallNetherrack, BlocksGT.oreSmallEndstone, BlocksGT.oreSmallGravel, BlocksGT.oreSmallMud, BlocksGT.oreSmallSand, BlocksGT.oreSmallRedSand}) {
			for (int i = 0; i < 10; i++) {
				GarbageGT.BLACKLIST.add((Block)tBlock, i);
				ItemsGT.ILLEGAL_DROPS.add((Block)tBlock, i);
			}
		}
		
		//====================================================================================================//
		
		if (MD.UB.mLoaded) {
		RM.generify(ST.make(MD.UB, "igneousStone"                     , 1, W), ST.make(Blocks.stone, 1, 0));
		RM.generify(ST.make(MD.UB, "metamorphicStone"                 , 1, W), ST.make(Blocks.stone, 1, 0));
		RM.generify(ST.make(MD.UB, "sedimentaryStone"                 , 1, W), ST.make(Blocks.stone, 1, 0));
		RM.generify(ST.make(MD.UB, "igneousCobblestone"               , 1, W), ST.make(Blocks.cobblestone, 1, 0));
		RM.generify(ST.make(MD.UB, "metamorphicCobblestone"           , 1, W), ST.make(Blocks.cobblestone, 1, 0));
		}
		if (MD.EB.mLoaded) {
		RM.generify(ST.make(MD.EB, "enhancedbiomes.tile.stoneEB"      , 1, W), ST.make(Blocks.stone, 1, 0));
		RM.generify(ST.make(MD.EB, "enhancedbiomes.tile.stoneCobbleEB", 1, W), ST.make(Blocks.cobblestone, 1, 0));
		}
		
		OreDictPrefix[] tPrefix;
		OreDictMaterial[] tDrops;
		
		tPrefix = new OreDictPrefix[] {OP.oreBasalt, OP.oreShale, OP.oreSandstone, OP.oreLimestone, OP.oreSlate, OP.oreAndesite, OP.oreMarble, OP.oreMarble, OP.oreLimestone, OP.oreGrayschist, OP.oreVanillastone, OP.oreBasalt, OP.oreVanillastone};
		tDrops  = new OreDictMaterial[] {MT.STONES.Basalt, MT.STONES.Shale, MT.Sand, MT.STONES.Limestone, MT.STONES.Slate, MT.STONES.Rhyolite, MT.Chalk, MT.STONES.Marble, MT.Dolomite, MT.STONES.Grayschist, MT.STONES.Chert, MT.STONES.Gabbro, MT.STONES.Dacite};
		for (int i = 0; i < 13; i++) rockset(MD.EB, "enhancedbiomes.tile.stoneEB", i, i, "enhancedbiomes.tile.stoneCobbleEB", i, "eb.stone."+i, tPrefix[i], tDrops[i]);
		
		
		
		tPrefix = new OreDictPrefix[] {OP.oreVanillagranite, OP.oreBlackgranite, OP.oreAndesite, OP.oreAndesite, OP.oreBasalt, OP.oreBasalt, OP.oreKomatiite, OP.oreVanillastone};
		tDrops  = new OreDictMaterial[] {MT.STONES.Granite, MT.STONES.GraniteBlack, MT.STONES.Rhyolite, MT.STONES.Andesite, MT.STONES.Gabbro, MT.STONES.Basalt, MT.STONES.Komatiite, MT.STONES.Dacite};
		for (int i = 0; i < 8; i++) rockset(MD.UB, "igneousStone", i, i+8, "igneousCobblestone", i, "ub.igneous."+i, tPrefix[i], tDrops[i]);
		
		tPrefix = new OreDictPrefix[] {OP.oreGneiss, OP.oreVanillastone, OP.oreMarble, OP.oreQuartzite, OP.oreBlueschist, OP.oreGreenschist, OP.oreVanillastone, OP.oreVanillastone};
		tDrops  = new OreDictMaterial[] {MT.STONES.Gneiss, MT.STONES.Eclogite, MT.STONES.Marble, MT.STONES.Quartzite, MT.STONES.Blueschist, MT.STONES.Greenschist, MT.Talc, MT.STONES.Migmatite};
		for (int i = 0; i < 8; i++) rockset(MD.UB, "metamorphicStone", i, i+8, "metamorphicCobblestone", i, "ub.metamorphic."+i, tPrefix[i], tDrops[i]);
		
		tPrefix = new OreDictPrefix[] {OP.oreLimestone, OP.oreMarble, OP.oreShale, OP.oreSiltstone, OP.oreVanillastone, OP.oreLimestone, OP.oreVanillastone, OP.oreVanillastone};
		tDrops  = new OreDictMaterial[] {MT.STONES.Limestone, MT.Chalk, MT.STONES.Shale, MT.STONES.Siltstone, MT.Lignite, MT.Dolomite, MT.STONES.Greywacke, MT.STONES.Chert};
		for (int i = 0; i < 8; i++) rockset(MD.UB, "sedimentaryStone", i, i+8, "sedimentaryStone", i, "ub.sedimentary."+i, tPrefix[i], tDrops[i]);
		
		if (MD.PFAA.mLoaded) {
		RM.generify(ST.make(MD.PFAA, "weakStone"       , 1, W), ST.make(Blocks.stone, 1, 0));
		RM.generify(ST.make(MD.PFAA, "mediumStone"     , 1, W), ST.make(Blocks.stone, 1, 0));
		RM.generify(ST.make(MD.PFAA, "strongStone"     , 1, W), ST.make(Blocks.stone, 1, 0));
		RM.generify(ST.make(MD.PFAA, "veryStrongStone" , 1, W), ST.make(Blocks.stone, 1, 0));
		RM.generify(ST.make(MD.PFAA, "weakRubble"      , 1, W), ST.make(Blocks.gravel, 1, 0));
		RM.generify(ST.make(MD.PFAA, "mediumCobble"    , 1, W), ST.make(Blocks.cobblestone, 1, 0));
		RM.generify(ST.make(MD.PFAA, "strongCobble"    , 1, W), ST.make(Blocks.cobblestone, 1, 0));
		RM.generify(ST.make(MD.PFAA, "veryStrongCobble", 1, W), ST.make(Blocks.cobblestone, 1, 0));
		}
		
		tPrefix = new OreDictPrefix[] {OP.oreVanillastone, OP.oreVanillastone, OP.oreVanillastone, OP.oreVanillastone, OP.oreShale};
		tDrops  = new OreDictMaterial[] {MT.Stone, MT.Stone, MT.Stone, MT.Stone, MT.STONES.Shale};
		for (int i = 0; i < 5; i++) rockset(MD.PFAA, "weakStone", i, i, "weakRubble", i, "pfaa.weak."+i, tPrefix[i], tDrops[i], 0.5F, 0.5F, 0, F, F, T);
		
		tPrefix = new OreDictPrefix[] {OP.oreLimestone, OP.orePinkschist, OP.oreVanillastone, OP.oreVanillastone, OP.oreVanillastone};
		tDrops  = new OreDictMaterial[] {MT.STONES.Limestone, MT.STONES.Pinkschist, MT.Stone, MT.STONES.Slate, MT.Stone};
		for (int i = 0; i < 5; i++) rockset(MD.PFAA, "mediumStone", i, i, "mediumCobble", i, "pfaa.medium."+i, tPrefix[i], tDrops[i], 1.0F, 1.0F, 0, F, F, T);
		
		tPrefix = new OreDictPrefix[] {OP.oreAndesite, OP.oreBasalt, OP.oreGneiss, OP.oreBlackgranite, OP.oreGreenschist, OP.oreMarble, OP.oreVanillastone, OP.oreAndesite, OP.oreSandstone, OP.oreSandstone};
		tDrops  = new OreDictMaterial[] {MT.STONES.Andesite, MT.STONES.Basalt, MT.STONES.Gneiss, MT.STONES.GraniteBlack, MT.STONES.Greenschist, MT.STONES.Marble, MT.Stone, MT.STONES.Rhyolite, MT.Sand, MT.RedSand};
		for (int i = 0; i <10; i++) rockset(MD.PFAA, "strongStone", i, i, "strongCobble", i, "pfaa.strong."+i, tPrefix[i], tDrops[i], 1.5F, 1.5F, 1, F, F, T);
		
		tPrefix = new OreDictPrefix[] {OP.oreDiorite, OP.oreBasalt, OP.oreVanillastone, OP.oreVanillastone, OP.oreQuartzite};
		tDrops  = new OreDictMaterial[] {MT.STONES.Diorite, MT.STONES.Gabbro, MT.Stone, MT.Stone, MT.STONES.Quartzite};
		for (int i = 0; i < 5; i++) rockset(MD.PFAA, "veryStrongStone", i, i, "veryStrongCobble", i, "pfaa.verystrong."+i, tPrefix[i], tDrops[i], 2.0F, 2.0F, 2, F, F, T);
		
		
		rockset(MD.CHSL, "granite"                                , 0, "chisel.granite"       , OP.oreVanillagranite      , MT.STONES.Granite);
		rockset(MD.CHSL, "diorite"                                , 0, "chisel.diorite"       , OP.oreDiorite             , MT.STONES.Diorite);
		rockset(MD.CHSL, "andesite"                               , 0, "chisel.andesite"      , OP.oreAndesite            , MT.STONES.Andesite);
		
		rockset(MD.CHSL, "marble"                                 , 0, "chisel.marble"        , OP.oreMarble              , MT.STONES.Marble);
		rockset(MD.CHSL, "limestone"                              , 0, "chisel.limestone"     , OP.oreLimestone           , MT.STONES.Limestone);
		
		rockset(MD.EtFu, "stone"                                  , 1, "etfu.granite"         , OP.oreVanillagranite      , MT.STONES.Granite);
		rockset(MD.EtFu, "stone"                                  , 3, "etfu.diorite"         , OP.oreDiorite             , MT.STONES.Diorite);
		rockset(MD.EtFu, "stone"                                  , 5, "etfu.andesite"        , OP.oreAndesite            , MT.STONES.Andesite);
		rockset(MD.EtFu, "deepslate", 0, 0, "cobbled_deepslate"   , 0, "etfu.deepslate"       , OP.oreDeepslate           , MT.STONES.Deepslate);
		
		rockset(MD.BoP, "rocks"                                   , 0, "bop.limestone"        , OP.oreLimestone           , MT.STONES.Limestone);
		rockset(MD.BoP, "rocks"                                   , 2, "bop.siltstone"        , OP.oreSiltstone           , MT.STONES.Siltstone);
		rockset(MD.BoP, "rocks"                                   , 4, "bop.shale"            , OP.oreShale               , MT.STONES.Shale);
		
		rockset(MD.GaSu, "basalt"                                 , 0, "gasu.basalt"          , OP.oreBasalt              , MT.STONES.Basalt);
		
		rockset(MD.HBM, "tile.stone_gneiss"                       , 0, "hbm.gneiss"           , OP.oreGneiss              , MT.STONES.Gneiss);
		rockset(MD.HBM, "tile.basalt_smooth"                      , 0, "hbm.basalt"           , OP.oreBasalt              , MT.STONES.Basalt);
		
		rockset(MD.RC, "cube"                                     , 6, "rc.abyssal"           , OP.oreBasalt              , MT.STONES.Basalt);
		rockset(MD.RC, "cube"                                     , 7, "rc.quarried"          , OP.oreMarble              , MT.STONES.Marble);
		
		rockset(MD.SC2, "BlockSlate"     , 0, 0, "BlockSlate"     , 3, "sc2.blueslate"        , OP.oreSlate               , MT.STONES.Slate);
		rockset(MD.SC2, "BlockSlate"     , 1, 1, "BlockSlate"     , 4, "sc2.redslate"         , OP.oreSlate               , MT.STONES.Slate);
		rockset(MD.SC2, "BlockSlate"     , 2, 2, "BlockSlate"     , 5, "sc2.blackslate"       , OP.oreSlate               , MT.STONES.Slate);
		rockset(MD.SC2, "BlockLightSlate", 0, 0, "BlockLightSlate", 3, "sc2.lightblueslate"   , OP.oreSlate               , MT.STONES.Slate);
		rockset(MD.SC2, "BlockLightSlate", 1, 1, "BlockLightSlate", 4, "sc2.grayslate"        , OP.oreSlate               , MT.STONES.Slate);
		rockset(MD.SC2, "BlockLightSlate", 2, 2, "BlockLightSlate", 5, "sc2.lightredslate"    , OP.oreSlate               , MT.STONES.Slate);
		
		rockset(MD.MIN, "basalt"                                  , 0, "min.basalt"           , OP.oreBasalt              , MT.STONES.Basalt);
		rockset(MD.MIN, "granite"                                 , 0, "min.granite"          , OP.oreVanillagranite      , MT.STONES.Granite);
		rockset(MD.MIN, "andesite"                                , 0, "min.andesite"         , OP.oreAndesite            , MT.STONES.Andesite);
		rockset(MD.MIN, "diorite"                                 , 0, "min.diorite"          , OP.oreDiorite             , MT.STONES.Diorite);
		rockset(MD.MIN, "limestone"                               , 0, "min.limestone"        , OP.oreLimestone           , MT.STONES.Limestone);
		rockset(MD.MIN, "shale"                                   , 0, "min.shale"            , OP.oreShale               , MT.STONES.Shale);
		rockset(MD.MIN, "slate"                                   , 0, "min.slate"            , OP.oreSlate               , MT.STONES.Slate);
		rockset(MD.MIN, "schist"                                  , 0, "min.schist"           , OP.oreBlueschist          , MT.STONES.Blueschist);
		rockset(MD.MIN, "gneiss"                                  , 0, "min.gneiss"           , OP.oreVanillastone        , MT.STONES.Gneiss);
		rockset(MD.MIN, "dolomite"                                , 0, "min.dolomite"         , OP.oreLimestone           , MT.Dolomite);
		rockset(MD.MIN, "rhyolite"                                , 0, "min.rhyolite"         , OP.oreAndesite            , MT.STONES.Rhyolite);
		rockset(MD.MIN, "pumice"                                  , 0, "min.pumice"           , OP.oreVanillastone        , MT.STONES.Pumice);
		rockset(MD.MIN, "conglomerate"                            , 0, "min.conglomerate"     , OP.oreVanillastone        , MT.Stone);
		rockset(MD.MIN, "pegmatite"                               , 0, "min.pegmatite"        , OP.oreVanillastone        , MT.Stone);
		rockset(MD.MIN, "chert"                                   , 0, "min.chert"            , OP.oreVanillastone        , MT.STONES.Chert);
		
		if (MD.NePl.mLoaded) {
		rockset(MD.NePl, "Blackstone"                             , 0, "nepl.blackstone"      , OP.oreBlackstone          , MT.STONES.Blackstone);
		rockset(MD.NePl, "Basalt"                                 , 0, "nepl.basalt"          , OP.oreBasalt              , MT.STONES.Basalt);
		}
		if (MD.NeLi.mLoaded) {
		rockset(MD.NeLi, "Blackstone"                             , 0, "neli.blackstone"      , OP.oreBlackstone          , MT.STONES.Blackstone);
		rockset(MD.NeLi, "Basalt"                                 , 0, "neli.basalt"          , OP.oreBasalt              , MT.STONES.Basalt);
		}
		if (MD.BOTA.mLoaded) {
		rockset(MD.BOTA, ST.block(MD.BOTA, "livingrock"), 0, 0, ST.block(MD.ALF, "LivingCobble"), 0, "botania.livingrock", OP.oreLivingrock, MT.STONES.Livingrock, 1.0F, 1.0F, 0, F, F, T);
		}
		if (MD.TF.mLoaded) {
		rockset(MD.TF, "tile.TFDeadrock"                          , 2, "twilight.deadrock"    , OP.oreDeadrock            , MT.STONES.Deadrock);
		}
		if (MD.AETHER.mLoaded) {
		rockset(MD.AETHER, "holystone", 1, 0, "holystone"         , 1, "aether.holystone"     , OP.oreHolystone           , MT.STONES.Holystone);
		}
		if (MD.ERE.mLoaded) {
		rockset(MD.ERE, "umberstone", 0, 0, "umberstone"          , 1, "erebus.umberstone"    , OP.oreUmberstone          , MT.STONES.Umber);
		rockset(MD.ERE, "umberGravel"                             , 0, "erebus.umbergravel"   , OP.oreUmberstone          , MT.STONES.Umber, 1, 1, 0, T, F, F);
		}
		if (MD.BTL.mLoaded) {
		rockset(MD.BTL, "betweenstone"                            , 0, "btl.betweenstone"     , OP.oreBetweenstone        , MT.STONES.Betweenstone);
		rockset(MD.BTL, "pitstone"                                , 0, "btl.pitstone"         , OP.orePitstone            , MT.STONES.Pitstone);
		}
		if (MD.GC.mLoaded) {
		rockset(MD.GC, "tile.moonBlock"                           , 3, "gc.moon.dirt"         , OP.oreMoon                , MT.STONES.MoonRock);
		rockset(MD.GC, "tile.moonBlock"                           , 4, "gc.moon.rock"         , OP.oreMoon                , MT.STONES.MoonRock);
		}
		if (MD.GC_PLANETS.mLoaded) {
		rockset(MD.GC_PLANETS, "tile.mars", 9, 9, "tile.mars"     , 4, "gc.mars.rock"         , OP.oreMars                , MT.STONES.MarsRock);
		rockset(MD.GC_PLANETS, "tile.mars"                        , 6, "gc.mars.dirt"         , OP.oreMars                , MT.STONES.MarsRock);
		
		rockset(MD.GC_PLANETS, "tile.asteroidsBlock"              , 0, "gc.asteroids.dark"    , OP.oreSpace               , MT.STONES.SpaceRock);
		rockset(MD.GC_PLANETS, "tile.asteroidsBlock"              , 1, "gc.asteroids.gray"    , OP.oreSpace               , MT.STONES.SpaceRock);
		rockset(MD.GC_PLANETS, "tile.asteroidsBlock"              , 2, "gc.asteroids.light"   , OP.oreSpace               , MT.STONES.SpaceRock);
		}
		
		/* Doesn't work so I won't waste Block IDs until I get it to work.
		if (MD.GC_GALAXYSPACE.mLoaded) {
		rockset(MD.GC_GALAXYSPACE, "mercuryblocks"      , 2, "gs.mercury.rock"      , OP.oreSpace               , MT.SpaceRock);
		rockset(MD.GC_GALAXYSPACE, "ceresblocks"        , 1, "gs.ceres.rock"        , OP.oreSpace               , MT.SpaceRock);
		rockset(MD.GC_GALAXYSPACE, "deimosblocks"       , 1, "gs.deimos.rock"       , OP.oreSpace               , MT.SpaceRock);
		rockset(MD.GC_GALAXYSPACE, "ioblocks"           , 2, "gs.io.rock"           , OP.oreSpace               , MT.SpaceRock);
		rockset(MD.GC_GALAXYSPACE, "oberonblocks"       , 2, "gs.oberon.rock"       , OP.oreSpace               , MT.SpaceRock);
		rockset(MD.GC_GALAXYSPACE, "proteusblocks"      , 2, "gs.proteus.rock"      , OP.oreSpace               , MT.SpaceRock);
		rockset(MD.GC_GALAXYSPACE, "tritonblocks"       , 2, "gs.triton.rock"       , OP.oreSpace               , MT.SpaceRock);
		rockset(MD.GC_GALAXYSPACE, "mirandablocks"      , 2, "gs.miranda.rock"      , OP.oreSpace               , MT.SpaceRock);
		rockset(MD.GC_GALAXYSPACE, "venusblocks"        , 1, "gs.venus.rock"        , OP.oreSpace               , MT.SpaceRock);
		rockset(MD.GC_GALAXYSPACE, "phobosblocks"       , 2, "gs.phobos.rock"       , OP.oreSpace               , MT.SpaceRock);
		rockset(MD.GC_GALAXYSPACE, "ganymedeblocks"     , 1, "gs.ganymede.rock"     , OP.oreSpace               , MT.SpaceRock);
		rockset(MD.GC_GALAXYSPACE, "barnardaEsubgrunt"  , 0, "gs.barnarda.e.rock"   , OP.oreSpace               , MT.SpaceRock);
		}*/
		
		if (MD.CHSL.mLoaded) {
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(IL.CHSL_Granite .get(1)), BlocksGT.ores_normal[5]);
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(IL.CHSL_Granite .get(1)), BlocksGT.ores_broken[5]);
		BlocksGT.stoneToSmallOres .put(new ItemStackContainer(IL.CHSL_Granite .get(1)), BlocksGT.ores_small [5]);
		
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(IL.CHSL_Diorite .get(1)), BlocksGT.ores_normal[6]);
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(IL.CHSL_Diorite .get(1)), BlocksGT.ores_broken[6]);
		BlocksGT.stoneToSmallOres .put(new ItemStackContainer(IL.CHSL_Diorite .get(1)), BlocksGT.ores_small [6]);
		
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(IL.CHSL_Andesite.get(1)), BlocksGT.ores_normal[7]);
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(IL.CHSL_Andesite.get(1)), BlocksGT.ores_broken[7]);
		BlocksGT.stoneToSmallOres .put(new ItemStackContainer(IL.CHSL_Andesite.get(1)), BlocksGT.ores_small [7]);
		}
		if (MD.EtFu.mLoaded) {
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(IL.EtFu_Granite .get(1)), BlocksGT.ores_normal[5]);
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(IL.EtFu_Granite .get(1)), BlocksGT.ores_broken[5]);
		BlocksGT.stoneToSmallOres .put(new ItemStackContainer(IL.EtFu_Granite .get(1)), BlocksGT.ores_small [5]);
		
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(IL.EtFu_Diorite .get(1)), BlocksGT.ores_normal[6]);
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(IL.EtFu_Diorite .get(1)), BlocksGT.ores_broken[6]);
		BlocksGT.stoneToSmallOres .put(new ItemStackContainer(IL.EtFu_Diorite .get(1)), BlocksGT.ores_small [6]);
		
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(IL.EtFu_Andesite.get(1)), BlocksGT.ores_normal[7]);
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(IL.EtFu_Andesite.get(1)), BlocksGT.ores_broken[7]);
		BlocksGT.stoneToSmallOres .put(new ItemStackContainer(IL.EtFu_Andesite.get(1)), BlocksGT.ores_small [7]);
		}
		if (MD.GaSu.mLoaded) {
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(IL.GaSu_Granite .get(1)), BlocksGT.ores_normal[5]);
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(IL.GaSu_Granite .get(1)), BlocksGT.ores_broken[5]);
		BlocksGT.stoneToSmallOres .put(new ItemStackContainer(IL.GaSu_Granite .get(1)), BlocksGT.ores_small [5]);
		
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(IL.GaSu_Diorite .get(1)), BlocksGT.ores_normal[6]);
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(IL.GaSu_Diorite .get(1)), BlocksGT.ores_broken[6]);
		BlocksGT.stoneToSmallOres .put(new ItemStackContainer(IL.GaSu_Diorite .get(1)), BlocksGT.ores_small [6]);
		
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(IL.GaSu_Andesite.get(1)), BlocksGT.ores_normal[7]);
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(IL.GaSu_Andesite.get(1)), BlocksGT.ores_broken[7]);
		BlocksGT.stoneToSmallOres .put(new ItemStackContainer(IL.GaSu_Andesite.get(1)), BlocksGT.ores_small [7]);
		}
		if (MD.BOTA.mLoaded) {
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(IL.BOTA_Granite .get(1)), BlocksGT.ores_normal[5]);
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(IL.BOTA_Granite .get(1)), BlocksGT.ores_broken[5]);
		BlocksGT.stoneToSmallOres .put(new ItemStackContainer(IL.BOTA_Granite .get(1)), BlocksGT.ores_small [5]);
		
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(IL.BOTA_Diorite .get(1)), BlocksGT.ores_normal[6]);
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(IL.BOTA_Diorite .get(1)), BlocksGT.ores_broken[6]);
		BlocksGT.stoneToSmallOres .put(new ItemStackContainer(IL.BOTA_Diorite .get(1)), BlocksGT.ores_small [6]);
		
		BlocksGT.stoneToNormalOres.put(new ItemStackContainer(IL.BOTA_Andesite.get(1)), BlocksGT.ores_normal[7]);
		BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(IL.BOTA_Andesite.get(1)), BlocksGT.ores_broken[7]);
		BlocksGT.stoneToSmallOres .put(new ItemStackContainer(IL.BOTA_Andesite.get(1)), BlocksGT.ores_small [7]);
		}
	}
	
	public static boolean rockset(ModData aMod, String aRock, int aMeta, String aName, OreDictPrefix aPrefix, OreDictMaterial aDrops) {
		return rockset(aMod, aRock, aMeta, aName, aPrefix, aDrops, 1, 1, 0, F, F, T);
	}
	
	public static boolean rockset(ModData aMod, String aRock, int aMeta, String aName, OreDictPrefix aPrefix, OreDictMaterial aDrops, float aBaseHardness, float aBaseResistance, int aHarvestLevelMinimum, boolean aGravity, boolean aEnderDragonProof, boolean aStoneOverrideable) {
		return rockset(aMod, ST.block(aMod, aRock), aMeta, aName, aPrefix, aDrops, aBaseHardness, aBaseResistance, aHarvestLevelMinimum, aGravity, aEnderDragonProof, aStoneOverrideable);
	}
	
	public static boolean rockset(ModData aMod, Block aRock, int aMeta, String aName, OreDictPrefix aPrefix, OreDictMaterial aDrops, float aBaseHardness, float aBaseResistance, int aHarvestLevelMinimum, boolean aGravity, boolean aEnderDragonProof, boolean aStoneOverrideable) {
		return rockset(aMod, aRock, aMeta, aMeta, aRock, aMeta, aName, aPrefix, aDrops, aBaseHardness, aBaseResistance, aHarvestLevelMinimum, aGravity, aEnderDragonProof, aStoneOverrideable);
	}
	
	public static boolean rockset(ModData aMod, String aRock, int aMetaA, int aMetaB, String aCobble, int aMeta, String aName, OreDictPrefix aPrefix, OreDictMaterial aDrops) {
		return rockset(aMod, aRock, aMetaA, aMetaB, aCobble, aMeta, aName, aPrefix, aDrops, 1.0F, 1.0F, 0, F, F, T);
	}
	
	public static boolean rockset(ModData aMod, String aRock, int aMetaA, int aMetaB, String aCobble, int aMeta, String aName, OreDictPrefix aPrefix, OreDictMaterial aDrops, float aBaseHardness, float aBaseResistance, int aHarvestLevelMinimum, boolean aGravity, boolean aEnderDragonProof, boolean aStoneOverrideable) {
		return rockset(aMod, ST.block(aMod, aRock), aMetaA, aMetaB, ST.block(aMod, aCobble), aMeta, aName, aPrefix, aDrops, aBaseHardness, aBaseResistance, aHarvestLevelMinimum, aGravity, aEnderDragonProof, aStoneOverrideable);
	}
	
	public static boolean rockset(ModData aMod, Block aRock, int aMetaA, int aMetaB, Block aCobble, int aMeta, String aName, OreDictPrefix aPrefix, OreDictMaterial aDrops, float aBaseHardness, float aBaseResistance, int aHarvestLevelMinimum, boolean aGravity, boolean aEnderDragonProof, boolean aStoneOverrideable) {
		boolean tHidden = F;
		
		if (aMod.mLoaded) {
			if (aRock == NB || aRock == null) {
				if (D1) throw new IllegalArgumentException("The Stone of the Mod '" + aMod.mID + "' with the Category '" + aName + "' is missing despite the Mod being loaded. Maybe an old/wrong Version of the Mod is used?");
				aPrefix = OP.oreVanillastone;
				aRock = Blocks.stone;
				aCobble = Blocks.cobblestone;
				aMeta = aMetaA = aMetaB = aHarvestLevelMinimum = 0;
				aBaseHardness = aBaseResistance = 1;
				aGravity = aEnderDragonProof = F;
				tHidden = T;
			} else {
				if (aCobble == NB || aCobble == null) {aCobble = aRock; aMeta = aMetaA;}
			}
		} else {
			aPrefix = OP.oreVanillastone;
			aRock = Blocks.stone;
			aCobble = Blocks.cobblestone;
			aMeta = aMetaA = aMetaB = aHarvestLevelMinimum = 0;
			aBaseHardness = aBaseResistance = 1;
			aGravity = aEnderDragonProof = F;
			tHidden = T;
		}
		
		PrefixBlock
		tOre1 = new PrefixBlock_(MD.GT, "gt.meta.ore.normal."+aName, aPrefix    , aMod.mLoaded ? null                       : ((PrefixBlock)BlocksGT.ore      ).mDrops, BlockTextureCopied.get(aRock  , aMetaA==W?0:aMetaA), aRock  .getMaterial(), aRock  .stepSound, TOOL_pickaxe, aBaseHardness  , aBaseResistance  ,  0, aHarvestLevelMinimum, aGravity, aEnderDragonProof, OreDictMaterial.MATERIAL_ARRAY).setHidden(tHidden),
		tOre2 = new PrefixBlock_(MD.GT, "gt.meta.ore.broken."+aName, aPrefix    , aMod.mLoaded ? null                       : ((PrefixBlock)BlocksGT.oreBroken).mDrops, BlockTextureCopied.get(aCobble, aMeta ==W?0:aMeta ), aCobble.getMaterial(), aCobble.stepSound, TOOL_pickaxe, aBaseHardness/2, aBaseResistance/2, -1, aHarvestLevelMinimum, T       , aEnderDragonProof, OreDictMaterial.MATERIAL_ARRAY).setHidden(tHidden),
		tOre3 = new PrefixBlock_(MD.GT, "gt.meta.ore.small." +aName, OP.oreSmall, aMod.mLoaded ? new Drops_SmallOre(aDrops) : ((PrefixBlock)BlocksGT.oreSmall ).mDrops, BlockTextureCopied.get(aRock  , aMetaA==W?0:aMetaA), aRock  .getMaterial(), aRock  .stepSound, TOOL_pickaxe, aBaseHardness  , aBaseResistance  , -1, aHarvestLevelMinimum, aGravity, aEnderDragonProof, OreDictMaterial.MATERIAL_ARRAY).setHidden(tHidden);
		
		if (aMod.mLoaded) {
			if (aStoneOverrideable) {
				BlocksGT.stoneOverridable.add(tOre1);
				BlocksGT.stoneOverridable.add(tOre2);
				BlocksGT.stoneOverridable.add(tOre3);
				BlocksGT.drillableDynamite.add(tOre1);
				BlocksGT.drillableDynamite.add(tOre2);
				BlocksGT.drillableDynamite.add(tOre3);
			}
			if (aRock != Blocks.stone) if (aMetaA != W) {
				BlocksGT.stoneToNormalOres.put(new ItemStackContainer(aRock, 1, aMetaA), tOre1);
				BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(aRock, 1, aMetaA), tOre2);
				BlocksGT.stoneToSmallOres .put(new ItemStackContainer(aRock, 1, aMetaA), tOre3);
				if (aMetaA != aMetaB) {
				BlocksGT.stoneToNormalOres.put(new ItemStackContainer(aRock, 1, aMetaB), tOre1);
				BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(aRock, 1, aMetaB), tOre2);
				BlocksGT.stoneToSmallOres .put(new ItemStackContainer(aRock, 1, aMetaB), tOre3);
				}
			} else for (int i = 0; i < 16; i++) {
				BlocksGT.stoneToNormalOres.put(new ItemStackContainer(aRock, 1, i), tOre1);
				BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(aRock, 1, i), tOre2);
				BlocksGT.stoneToSmallOres .put(new ItemStackContainer(aRock, 1, i), tOre3);
			}
			tOre1.mDrops = new Drops(tOre2, tOre1, OP.oreRaw.mRegisteredPrefixItems.get(0), 0, 1);
		} else {
			tOre1.mRegisterToOreDict = tOre2.mRegisterToOreDict = tOre3.mRegisterToOreDict = F;
		}
		
		for (int i = 0; i < 10; i++) {
			GarbageGT.BLACKLIST.add(tOre1, i);
			GarbageGT.BLACKLIST.add(tOre2, i);
			GarbageGT.BLACKLIST.add(tOre3, i);
			ItemsGT.ILLEGAL_DROPS.add(tOre1, i);
			ItemsGT.ILLEGAL_DROPS.add(tOre2, i);
			ItemsGT.ILLEGAL_DROPS.add(tOre3, i);
		}
		return T;
	}
	
	public static PrefixBlock normal(Block aRock, int aMeta, String aName, OreDictPrefix aPrefix, Material aVanillaMaterial, SoundType aSoundType, String aTool, float aBaseHardness, float aBaseResistance, int aHarvestLevelMinimum, boolean aGravity, boolean aEnderDragonProof) {
		if (aRock == NB || aRock == null) return null;
		PrefixBlock rOre = new PrefixBlock_(MD.GT, "gt.meta.ore.normal."+aName, aPrefix, null, BlockTextureCopied.get(aRock, aMeta), aVanillaMaterial, aSoundType, aTool, aBaseHardness, aBaseResistance, 0, aHarvestLevelMinimum, aGravity, aEnderDragonProof, OreDictMaterial.MATERIAL_ARRAY);
		if (aRock != Blocks.stone) if (aMeta != W) BlocksGT.stoneToNormalOres.put(new ItemStackContainer(aRock, 1, aMeta), rOre); else for (int i = 0; i < 16; i++) BlocksGT.stoneToNormalOres.put(new ItemStackContainer(aRock, 1, i), rOre);
		return rOre;
	}
	
	public static PrefixBlock broken(Block aRock, int aMeta, String aName, OreDictPrefix aPrefix, Material aVanillaMaterial, SoundType aSoundType, String aTool, float aBaseHardness, float aBaseResistance, int aHarvestLevelMinimum, boolean aEnderDragonProof) {
		if (aRock == NB || aRock == null) return null;
		PrefixBlock rOre = new PrefixBlock_(MD.GT, "gt.meta.ore.broken."+aName, aPrefix, null, BlockTextureCopied.get(aRock, aMeta), aVanillaMaterial, aSoundType, aTool, aBaseHardness, aBaseResistance, -1, aHarvestLevelMinimum, T, aEnderDragonProof, OreDictMaterial.MATERIAL_ARRAY);
		if (aRock != Blocks.stone) if (aMeta != W) BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(aRock, 1, aMeta), rOre); else for (int i = 0; i < 16; i++) BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(aRock, 1, i), rOre);
		return rOre;
	}
	
	public static PrefixBlock small(Block aRock, int aMeta, String aName, OreDictMaterial aDrops, Material aVanillaMaterial, SoundType aSoundType, String aTool, float aBaseHardness, float aBaseResistance, int aHarvestLevelMinimum, boolean aGravity, boolean aEnderDragonProof) {
		if (aRock == NB || aRock == null) return null;
		PrefixBlock rOre = new PrefixBlock_(MD.GT, "gt.meta.ore.small."+aName, OP.oreSmall, new Drops_SmallOre(aDrops), BlockTextureCopied.get(aRock, aMeta), aVanillaMaterial, aSoundType, aTool, aBaseHardness, aBaseResistance, -1, aHarvestLevelMinimum, aGravity, aEnderDragonProof, OreDictMaterial.MATERIAL_ARRAY);
		if (aRock != Blocks.stone) if (aMeta != W) BlocksGT.stoneToSmallOres.put(new ItemStackContainer(aRock, 1, aMeta), rOre); else for (int i = 0; i < 16; i++) BlocksGT.stoneToSmallOres.put(new ItemStackContainer(aRock, 1, i), rOre);
		return rOre;
	}
}
