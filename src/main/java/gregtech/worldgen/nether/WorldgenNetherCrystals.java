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

package gregtech.worldgen.nether;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.data.CS.BlocksGT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import gregtech.worldgen.NoiseGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenNetherCrystals extends WorldgenObject {
	@SafeVarargs
	public WorldgenNetherCrystals(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (aRandom.nextBoolean() || aBiomeNames.contains("Crystalline Crag")) return F;
		int aX = aMinX+aRandom.nextInt(16), aY = WD.waterLevel(aWorld), aZ = aMinZ+aRandom.nextInt(16), aMeta = new NoiseGenerator(aWorld).get(aX/2, 360, aZ/2, BlocksGT.CrystalOres.maxMeta());
		
		while (WD.air(aWorld, aX, ++aY, aZ) && aY < aWorld.getHeight());
		Block tBlock = WD.block(aWorld, aX, aY, aZ);
		if (tBlock == Blocks.nether_brick || tBlock.getMaterial() != Material.rock) return F;
		if (--aY -10 < WD.waterLevel(aWorld)) return F;
		
		aWorld.setBlock(aX, aY, aZ, BlocksGT.CrystalOres, aMeta, 2);
		for (int i = 0; i < 1500; ++i) {
			int tX = aX+aRandom.nextInt(8)-aRandom.nextInt(8), tY = aY-aRandom.nextInt(12), tZ = aZ+aRandom.nextInt(8)-aRandom.nextInt(8);
			if (WD.air(aWorld, tX, tY, tZ)) {
				int tCount = 0;
				for (int tSide : ALL_SIDES_VALID) {
					Block block = aWorld.getBlock(tX+OFFX[tSide], tY+OFFY[tSide], tZ+OFFZ[tSide]);
					if (block == BlocksGT.CrystalOres) tCount++;
				}
				if (tCount == 1) aWorld.setBlock(tX, tY, tZ, BlocksGT.CrystalOres, aMeta, 2);
			}
		}
		return T;
	}
}
