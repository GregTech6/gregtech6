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

import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import gregtech.blocks.fluids.BlockRiver;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenRiver extends WorldgenObject {
	public int mHeight = WD.waterLevel();
	
	@SafeVarargs
	public WorldgenRiver(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mHeight = getConfigFile().get(mCategory, "Height", mHeight);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		boolean temp = T;
		for (String tName : aBiomeNames) if (BIOMES_RIVER.contains(tName) && !BIOMES_OCEAN.contains(tName)) {temp = F; break;}
		if (temp) return F;
		int tHeight = WD.waterLevel(aWorld, mHeight);
		final ExtendedBlockStorage[] tStorages = aChunk.getBlockStorageArray();
		for (int tX = 0; tX < 16; tX++) for (int tZ = 0; tZ < 16; tZ++) {
			boolean tPlacedNone = T;
			for (int tY = tHeight; tY > 0; tY--) {
				final ExtendedBlockStorage tStorage = tStorages[tY >> 4];
				if (tStorage == null) continue;
				final Block tBlock = tStorage.getBlockByExtId(tX, tY & 15, tZ);
				if (tBlock.isOpaqueCube()) break;
				if (tBlock != Blocks.water && tBlock != Blocks.flowing_water) continue;
				
				if (tPlacedNone) {
					tPlacedNone = F;
					
					BlockRiver.PLACEMENT_ALLOWED = T;

					if (!aWorld.setBlock(aMinX+tX, tY, aMinZ+tZ, BlocksGT.River, 0, 0) && aWorld.getBlock(aMinX+tX, tY, aMinZ+tZ) != BlocksGT.River) {
						System.out.println("Failed to generate Water at: "+aMinX+tX+"/"+tY+"/"+aMinZ+tZ+"/"+aWorld.getBlock(aMinX+tX, tY, aMinZ+tZ));
						aWorld.setBlock(aMinX+tX, tY, aMinZ+tZ, Blocks.water, 0, 0);
						aChunk.lastSaveTime = Long.MAX_VALUE;
						return F;
					}
					BlockRiver.PLACEMENT_ALLOWED = F;
				} else {
					tStorage.func_150818_a(tX, tY & 15, tZ, BlocksGT.River);
				}
				temp = T;
			}
		}
		return temp;
	}
}
