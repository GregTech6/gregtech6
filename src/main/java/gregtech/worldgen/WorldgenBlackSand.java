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

package gregtech.worldgen;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.data.CS.BlocksGT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenBlackSand extends WorldgenObject {
	@SafeVarargs
	public WorldgenBlackSand(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (aRandom.nextInt(32) > 0 || checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return F;
		for (String tName : aBiomeNames) if (BIOMES_OCEAN_BEACH.contains(tName) || BIOMES_SWAMP.contains(tName)) return F;
		boolean temp = T;
		for (String tName : aBiomeNames) if (BIOMES_RIVER.contains(tName)) {temp = F; break;}
		if (temp) return F;
		
		int tX = aMinX-16, tZ = aMinZ-16, tUpperBound = WD.waterLevel(aWorld)+1, tLowerBound = WD.waterLevel(aWorld)-12, aMeta = aRandom.nextInt(3);
		for (int i = 0; i < 48; i++) for (int j = 0; j < 48; j++) if (WorldgenPit.SHAPE[i][j]) {
			Block tBlock = NB, tLastBlock = WD.block(aWorld, tX+i, tUpperBound+1, tZ+j);
			for (int tY = tUpperBound, tGenerated = 0; tY >= tLowerBound && tGenerated < 2; tY--, tLastBlock = tBlock) {
				tBlock = WD.block(aWorld, tX+i, tY, tZ+j);
				byte tMeta = WD.meta(aWorld, tX+i, tY, tZ+j);
				if (tBlock == BlocksGT.Sands && tMeta == aMeta) {tGenerated++; continue;}
				if (!tBlock.isOpaqueCube()) {if (tGenerated > 0) break; continue;}
				if ((tBlock == Blocks.dirt && tMeta < 2) || tBlock == Blocks.gravel || tBlock == Blocks.sand || tBlock == Blocks.clay || tBlock == BlocksGT.oreSmallGravel || tBlock == BlocksGT.oreGravel || tBlock == BlocksGT.oreSmallSand || tBlock == BlocksGT.oreSand || tBlock == BlocksGT.oreSmallRedSand || tBlock == BlocksGT.oreRedSand) {
					// Don't take away the Dirt Block below Trees, Bushes and other Plants.
					if (tGenerated <= 0 && (tLastBlock.getMaterial() == Material.wood || tLastBlock.getMaterial() == Material.gourd)) continue;
				} else {
					if (tGenerated > 0) {
						if (tBlock.getMaterial() != Material.rock) break;
					} else {
						continue;
					}
				}
				aWorld.setBlock(tX+i, tY, tZ+j, BlocksGT.Sands, aMeta, 3);
				tGenerated++;
			}
		}
		return temp;
	}
}
