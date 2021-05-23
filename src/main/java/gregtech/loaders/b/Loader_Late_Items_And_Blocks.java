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

package gregtech.loaders.b;

import static gregapi.data.CS.*;

import gregapi.block.behaviors.Drops;
import gregapi.block.behaviors.Drops_SmallOre;
import gregapi.block.prefixblock.PrefixBlock;
import gregapi.block.prefixblock.PrefixBlock_;
import gregapi.code.ItemStackContainer;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.GarbageGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.BlockTextureCopied;
import gregapi.util.ST;
import gregtech.loaders.a.Loader_Ores;
import net.minecraft.block.Block;

public class Loader_Late_Items_And_Blocks implements Runnable {
	@Override
	public void run() {
		// Atum violates the "Blocks have to be created in preInit" Rule...
		if (MD.ATUM.mLoaded) {
			OUT.println("GT_Mod: Register Late Bullshit for Atum.");
			Block tAtumStone = ST.block(MD.ATUM, "tile.stone"), tAtumCobble = ST.block(MD.ATUM, "tile.cobble"), tAtumSand = ST.block(MD.ATUM, "tile.sand");
			if (tAtumCobble != NB && tAtumStone != NB) {
				BlocksGT.oreAtumLimestone       = new PrefixBlock_(MD.GT, "gt.meta.ore.normal.atum"         , OP.oreLimestone   , null, null, null                                   , BlockTextureCopied.get(tAtumStone , 6, 0) , tAtumStone.getMaterial()  , tAtumStone.stepSound  , TOOL_pickaxe  , 2.0F, 2.0F,   0,   0, 999, 0, 0, 0, 1, 1, 1, F,F,F,F,T,T,F,F,T,T,T,T,T,F, OreDictMaterial.MATERIAL_ARRAY);
				BlocksGT.oreBrokenAtumLimestone = new PrefixBlock_(MD.GT, "gt.meta.ore.broken.atum"         , OP.oreLimestone   , null, null, null                                   , BlockTextureCopied.get(tAtumCobble, 6, 0) , tAtumCobble.getMaterial() , tAtumCobble.stepSound , TOOL_pickaxe  , 1.0F, 1.0F,  -1,   0, 999, 0, 0, 0, 1, 1, 1, T,F,F,F,T,T,F,F,T,T,T,T,T,F, OreDictMaterial.MATERIAL_ARRAY);
				BlocksGT.oreSmallAtumLimestone  = new PrefixBlock_(MD.GT, "gt.meta.ore.small.atum"          , OP.oreSmall       , null, null, new Drops_SmallOre(MT.STONES.Limestone), BlockTextureCopied.get(tAtumStone , 6, 0) , tAtumStone.getMaterial()  , tAtumStone.stepSound  , TOOL_pickaxe  , 2.0F, 2.0F,  -1,   0, 999, 0, 0, 0, 1, 1, 1, F,F,F,F,T,T,F,F,T,T,T,T,T,F, OreDictMaterial.MATERIAL_ARRAY);
				((PrefixBlock)BlocksGT.oreAtumLimestone).mDrops = new Drops(BlocksGT.oreBrokenAtumLimestone, BlocksGT.oreAtumLimestone, OP.oreRaw.mRegisteredPrefixItems.get(0));
				for (byte i = 0; i < 16; i++) {
					BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(tAtumStone, 1, i), BlocksGT.oreBrokenAtumLimestone);
					BlocksGT.stoneToNormalOres.put(new ItemStackContainer(tAtumStone, 1, i), BlocksGT.oreAtumLimestone);
					BlocksGT.stoneToSmallOres .put(new ItemStackContainer(tAtumStone, 1, i), BlocksGT.oreSmallAtumLimestone);
				}
				for (int i = 0; i < 10; i++) {
					ItemsGT.ILLEGAL_DROPS.add((Block)BlocksGT.oreBrokenAtumLimestone, i);
					ItemsGT.ILLEGAL_DROPS.add((Block)BlocksGT.oreAtumLimestone, i);
					ItemsGT.ILLEGAL_DROPS.add((Block)BlocksGT.oreSmallAtumLimestone, i);
					GarbageGT.BLACKLIST.add((Block)BlocksGT.oreBrokenAtumLimestone, i);
					GarbageGT.BLACKLIST.add((Block)BlocksGT.oreAtumLimestone, i);
					GarbageGT.BLACKLIST.add((Block)BlocksGT.oreSmallAtumLimestone, i);
				}
			}
			if (tAtumSand != NB) {
				BlocksGT.oreAtumSand            = new PrefixBlock_(MD.GT, "gt.meta.ore.normal.sand.atum"    , OP.oreStrangesand , null, null, null                                   , BlockTextureCopied.get(tAtumSand  , 6, 0) , tAtumSand.getMaterial()   , tAtumSand.stepSound   , TOOL_shovel   , 2.0F, 2.0F,   0,   0, 999, 0, 0, 0, 1, 1, 1, F,F,F,F,T,T,F,F,T,T,T,T,T,F, OreDictMaterial.MATERIAL_ARRAY);
				BlocksGT.oreSmallAtumSand       = new PrefixBlock_(MD.GT, "gt.meta.ore.small.sand.atum"     , OP.oreSmall       , null, null, new Drops_SmallOre(MT.STONES.Limestone), BlockTextureCopied.get(tAtumSand  , 6, 0) , tAtumSand.getMaterial()   , tAtumSand.stepSound   , TOOL_shovel   , 2.0F, 2.0F,  -1,   0, 999, 0, 0, 0, 1, 1, 1, F,F,F,F,T,T,F,F,T,T,T,T,T,F, OreDictMaterial.MATERIAL_ARRAY);
				for (byte i = 0; i < 16; i++) {
					BlocksGT.stoneToNormalOres.put(new ItemStackContainer(tAtumSand, 1, i), BlocksGT.oreAtumSand);
					BlocksGT.stoneToBrokenOres.put(new ItemStackContainer(tAtumSand, 1, i), BlocksGT.oreAtumSand);
					BlocksGT.stoneToSmallOres .put(new ItemStackContainer(tAtumSand, 1, i), BlocksGT.oreSmallAtumSand);
				}
				for (int i = 0; i < 10; i++) {
					ItemsGT.ILLEGAL_DROPS.add((Block)BlocksGT.oreAtumSand, i);
					ItemsGT.ILLEGAL_DROPS.add((Block)BlocksGT.oreSmallAtumSand, i);
					GarbageGT.BLACKLIST.add((Block)BlocksGT.oreAtumSand, i);
					GarbageGT.BLACKLIST.add((Block)BlocksGT.oreSmallAtumSand, i);
				}
			}
		}
		
		Loader_Ores.rockset(MD.PR_EXPLORATION, "projectred.exploration.stone", 3, 3, "projectred.exploration.stone", 2, "pr.basalt", OP.oreBasalt, MT.STONES.Basalt);
		Loader_Ores.rockset(MD.PR_EXPLORATION, "projectred.exploration.stone"                                      , 0, "pr.marble", OP.oreMarble, MT.STONES.Marble);
		
		Loader_Ores.rockset(MD.BP, "basalt", 0, 0, "basalt_cobble", 0, "bp.basalt", OP.oreBasalt, MT.STONES.Basalt);
		Loader_Ores.rockset(MD.BP, "marble"                       , 0, "bp.marble", OP.oreMarble, MT.STONES.Marble);
	}
}
