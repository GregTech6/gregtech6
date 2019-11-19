/**
 * Copyright (c) 2019 Gregorius Techneticies
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
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenSticks extends WorldgenObject {
	@SafeVarargs
	public WorldgenSticks(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return F;
		int tCount = (WD.dimBTL(aWorld) || WD.dimAETHER(aWorld) ? 2 : 0);
		for (String tName : aBiomeNames) {
			if (tCount < 3) if (BIOMES_WOODS.contains(tName) || BIOMES_SWAMP.contains(tName)) {tCount = 3; continue;}
			if (tCount < 2) if (BIOMES_RIVER.contains(tName) || BIOMES_PLAINS.contains(tName) || BIOMES_SAVANNA.contains(tName) || "Wasteland Forest".equals(tName)) {tCount = 2; continue;}
			if (tCount < 1) if (BIOMES_TAIGA.contains(tName) || BIOMES_MESA.contains(tName) || (aRandom.nextBoolean() && BIOMES_WASTELANDS.contains(tName))) {tCount = 1; continue;}
		}
		if (tCount <= 0) return F;
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
		if (tRegistry == null) return F;
		for (int i = 0, j = tCount+aRandom.nextInt(Math.max(2, tCount)); i < j; i++) {
			int tX = aMinX + aRandom.nextInt(16), tZ = aMinZ + aRandom.nextInt(16);
			for (int tY = aWorld.provider.hasNoSky ? 80 : aWorld.getHeight()-50; tY > 0; tY--) {
				Block tContact = aChunk.getBlock(tX&15, tY, tZ&15);
				if (tContact.getMaterial().isLiquid()) break;
				if (tContact == NB || tContact.isAir(aWorld, tX, tY, tZ)) continue;
				if (tContact.getMaterial() != Material.grass && tContact.getMaterial() != Material.ground) continue;
				if (WD.easyRep(aWorld, tX, tY+1, tZ)) tRegistry.mBlock.placeBlock(aWorld, tX, tY+1, tZ, SIDE_UNKNOWN, (short)32756, null, F, T);
				break;
			}
		}
		return T;
	}
}
