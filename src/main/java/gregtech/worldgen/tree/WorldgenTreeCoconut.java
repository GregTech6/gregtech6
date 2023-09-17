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

package gregtech.worldgen.tree;

import gregapi.block.tree.BlockBaseSapling;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import gregapi.worldgen.WorldgenOnSurface;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenTreeCoconut extends WorldgenOnSurface {
	@SafeVarargs
	public WorldgenTreeCoconut(String aName, boolean aDefault, int aAmount, int aProbability, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aAmount, aProbability, aLists);
	}
	
	@Override
	public int canGenerate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return 0;
		boolean temp = F;
		for (String tName : aBiomeNames) {
			if (BIOMES_COCONUT  .contains(tName)) {temp = T; continue;}
			if (BIOMES_MOUNTAINS.contains(tName)) return 0;// Too Mountainous
			if (BIOMES_FROZEN   .contains(tName)) return 0;// Too Cold
			if (BIOMES_TAIGA    .contains(tName)) return 0;// Too Cold
			if (BIOMES_SWAMP    .contains(tName)) return 0;// Too Shrek
			if (BIOMES_WOODS    .contains(tName)) return 0;// Too Forested
		}
		return temp ? mAmount : 0;
	}
	
	@Override
	public boolean tryPlaceStuff(World aWorld, int aX, int aY, int aZ, Random aRandom, Block aContact) {
		if (!BlocksGT.plantableTrees.contains(aContact)) return F;
		if (!WD.easyRep(aWorld, aX, aY+1, aZ)) return F;
		return ((BlockBaseSapling)BlocksGT.Saplings_AB).grow(aWorld, aX, aY+1, aZ, (byte)6, aRandom);
	}
}
