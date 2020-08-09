/**
 * Copyright (c) 2020 GregTech-6 Team
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

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import gregapi.worldgen.WorldgenOnSurface;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenSticks extends WorldgenOnSurface {
	@SafeVarargs
	public WorldgenSticks(String aName, boolean aDefault, int aAmount, int aProbability, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aAmount, aProbability, aLists);
	}
	
	@Override
	public int canGenerate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return 0;
		if (WD.dimBTL(aWorld) || WD.dimAETHER(aWorld)) return mAmount * 2;
		int tCount = 0;
		for (String tName : aBiomeNames) {
			if (BIOMES_WOODS.contains(tName) || BIOMES_SWAMP.contains(tName)) {return mAmount * 3;}
			if (tCount < 2) if (BIOMES_RIVER.contains(tName) || BIOMES_PLAINS.contains(tName) || BIOMES_SAVANNA.contains(tName) || "Wasteland Forest".equalsIgnoreCase(tName)) {tCount = 2; continue;}
			if (tCount < 1) if (BIOMES_TAIGA.contains(tName) || BIOMES_MESA.contains(tName) || BIOMES_WASTELANDS.contains(tName)) {tCount = 1; continue;}
		}
		return mAmount * tCount;
	}
	
	@Override
	public boolean tryPlaceStuff(World aWorld, int aX, int aY, int aZ, Random aRandom, Block aContact) {
		if (aContact.getMaterial() != Material.grass && aContact.getMaterial() != Material.ground) return F;
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
		if (tRegistry == null) return F;
		if (WD.easyRep(aWorld, aX, aY+1, aZ)) return tRegistry.mBlock.placeBlock(aWorld, aX, aY+1, aZ, SIDE_UNKNOWN, (short)32756, null, F, T);
		return F;
	}
}
