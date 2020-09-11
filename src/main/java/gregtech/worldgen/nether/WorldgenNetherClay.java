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

package gregtech.worldgen.nether;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.data.CS.BlocksGT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import gregtech.worldgen.WorldgenPit;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenNetherClay extends WorldgenObject {
	@SafeVarargs
	public WorldgenNetherClay(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (aRandom.nextInt(64) > 0 || checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return F;
		
		int tX = aMinX - 16, tZ = aMinZ -16, tUpperBound = WD.waterLevel(aWorld)+3, tLowerBound = WD.waterLevel(aWorld)+2;
		for (int i = 0; i < 48; i++) for (int j = 0; j < 48; j++) if (WorldgenPit.SHAPE[i][j]) {
			for (int tY = tUpperBound; tY >= tLowerBound; tY--) {
				if (aWorld.getBlock(tX+i, tY, tZ+j) == Blocks.netherrack) aWorld.setBlock(tX+i, tY, tZ+j, BlocksGT.Diggables, 3, 2);
			}
		}
		return T;
	}
}
