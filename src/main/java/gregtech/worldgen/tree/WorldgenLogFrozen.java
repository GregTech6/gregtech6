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

package gregtech.worldgen.tree;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.data.CS.BlocksGT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenLogFrozen extends WorldgenObject {
	@SafeVarargs
	public WorldgenLogFrozen(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (aRandom.nextInt(8) != 0 || checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return F;
		boolean temp = T;
		for (String tName : aBiomeNames) if (BIOMES_FROZEN.contains(tName)) {temp = F; break;}
		if (temp) return F;
		int tX = aMinX + aRandom.nextInt(16), tZ = aMinZ + aRandom.nextInt(16);
		for (int tY = aWorld.provider.hasNoSky ? 80 : aWorld.getHeight()-50; tY > 0; tY--) {
			Block tContact = aChunk.getBlock(tX&15, tY, tZ&15);
			if (tContact == NB || tContact.isAir(aWorld, tX, tY, tZ)) {temp = T; continue;}
			if (!BlocksGT.plantableGreens.contains(tContact) && tContact != Blocks.snow) {temp = F; continue;}
			if (!temp && aChunk.getBlock(tX&15, tY+1, tZ&15) != Blocks.snow_layer) return F;
			switch(aRandom.nextInt(3)) {
			case 0:
				if (aRandom.nextBoolean())  WD.set(aWorld, tX  , tY-1, tZ  , BlocksGT.Log1, PILLARS_Y[3], 2);
											WD.set(aWorld, tX  , tY  , tZ  , BlocksGT.Log1, PILLARS_Y[3], 2);
											WD.set(aWorld, tX  , tY+1, tZ  , BlocksGT.Log1, PILLARS_Y[3], 2);
											WD.set(aWorld, tX  , tY+2, tZ  , BlocksGT.Log1, PILLARS_Y[3], 2);
				if (aRandom.nextBoolean())  WD.set(aWorld, tX  , tY+3, tZ  , BlocksGT.Log1, PILLARS_Y[3], 2);
				return T;
			case 1:
				if (aRandom.nextBoolean())  WD.set(aWorld, tX-2, tY+1, tZ  , BlocksGT.Log1, PILLARS_X[3], 2);
											WD.set(aWorld, tX-1, tY+1, tZ  , BlocksGT.Log1, PILLARS_X[3], 2);
											WD.set(aWorld, tX  , tY+1, tZ  , BlocksGT.Log1, PILLARS_X[3], 2);
											WD.set(aWorld, tX+1, tY+1, tZ  , BlocksGT.Log1, PILLARS_X[3], 2);
				if (aRandom.nextBoolean())  WD.set(aWorld, tX+2, tY+1, tZ  , BlocksGT.Log1, PILLARS_X[3], 2);
				return T;
			case 2:
				if (aRandom.nextBoolean())  WD.set(aWorld, tX  , tY+1, tZ-2, BlocksGT.Log1, PILLARS_Z[3], 2);
											WD.set(aWorld, tX  , tY+1, tZ-1, BlocksGT.Log1, PILLARS_Z[3], 2);
											WD.set(aWorld, tX  , tY+1, tZ  , BlocksGT.Log1, PILLARS_Z[3], 2);
											WD.set(aWorld, tX  , tY+1, tZ+1, BlocksGT.Log1, PILLARS_Z[3], 2);
				if (aRandom.nextBoolean())  WD.set(aWorld, tX  , tY+1, tZ+2, BlocksGT.Log1, PILLARS_Z[3], 2);
				return T;
			}
		}
		return T;
	}
}
