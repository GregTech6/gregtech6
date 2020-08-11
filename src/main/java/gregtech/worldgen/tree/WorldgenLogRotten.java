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

package gregtech.worldgen.tree;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.data.CS.BlocksGT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import gregapi.worldgen.WorldgenOnSurface;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenLogRotten extends WorldgenOnSurface {
	@SafeVarargs
	public WorldgenLogRotten(String aName, boolean aDefault, int aAmount, int aProbability, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aAmount, aProbability, aLists);
	}
	
	@Override
	public int canGenerate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return 0;
		for (String tName : aBiomeNames) if (BIOMES_SWAMP.contains(tName) || BIOMES_JUNGLE.contains(tName)) return mAmount;
		return 0;
	}
	
	@Override
	public boolean tryPlaceStuff(World aWorld, int aX, int aY, int aZ, Random aRandom, Block aContact) {
		if (WD.anywater(aWorld, aX, aY, aZ, aContact)) aY--; else {
			if (!BlocksGT.plantableGreens.contains(aContact)) return F;
			if (!WD.air(aWorld, aX, aY+1, aZ)) return F;
		}
		switch(aRandom.nextInt(3)) {
		case 0:
			int tY = WD.waterLevel(aWorld);
			if (aRandom.nextBoolean())              WD.set(aWorld, aX  , aY-1, aZ  , BlocksGT.Log1, PILLARS_Y[1], 2);
													WD.set(aWorld, aX  , aY  , aZ  , BlocksGT.Log1, PILLARS_Y[1], 2);
													WD.set(aWorld, aX  , aY+1, aZ  , BlocksGT.Log1, PILLARS_Y[1], 2);
													WD.set(aWorld, aX  , aY+2, aZ  , BlocksGT.Log1, PILLARS_Y[1], 2);
			if (aY < tY   || aRandom.nextBoolean()) WD.set(aWorld, aX  , aY+3, aZ  , BlocksGT.Log1, PILLARS_Y[1], 2);
			if (aY < tY-1)                          WD.set(aWorld, aX  , aY+4, aZ  , BlocksGT.Log1, PILLARS_Y[1], 2);
			if (aY < tY-2 && aRandom.nextBoolean()) WD.set(aWorld, aX  , aY+5, aZ  , BlocksGT.Log1, PILLARS_Y[1], 2);
			return T;
		case 1:
			if (aRandom.nextBoolean())              WD.set(aWorld, aX-2, aY+1, aZ  , BlocksGT.Log1, PILLARS_X[1], 2);
													WD.set(aWorld, aX-1, aY+1, aZ  , BlocksGT.Log1, PILLARS_X[1], 2);
													WD.set(aWorld, aX  , aY+1, aZ  , BlocksGT.Log1, PILLARS_X[1], 2);
													WD.set(aWorld, aX+1, aY+1, aZ  , BlocksGT.Log1, PILLARS_X[1], 2);
			if (aRandom.nextBoolean())              WD.set(aWorld, aX+2, aY+1, aZ  , BlocksGT.Log1, PILLARS_X[1], 2);
			return T;
		case 2:
			if (aRandom.nextBoolean())              WD.set(aWorld, aX  , aY+1, aZ-2, BlocksGT.Log1, PILLARS_Z[1], 2);
													WD.set(aWorld, aX  , aY+1, aZ-1, BlocksGT.Log1, PILLARS_Z[1], 2);
													WD.set(aWorld, aX  , aY+1, aZ  , BlocksGT.Log1, PILLARS_Z[1], 2);
													WD.set(aWorld, aX  , aY+1, aZ+1, BlocksGT.Log1, PILLARS_Z[1], 2);
			if (aRandom.nextBoolean())              WD.set(aWorld, aX  , aY+1, aZ+2, BlocksGT.Log1, PILLARS_Z[1], 2);
			return T;
		}
		return F;
	}
}
