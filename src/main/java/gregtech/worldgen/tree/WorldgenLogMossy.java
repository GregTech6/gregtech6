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
import gregapi.data.MD;
import gregapi.util.ST;
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
public class WorldgenLogMossy extends WorldgenObject {
	@SafeVarargs
	public WorldgenLogMossy(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (aRandom.nextInt(8) != 0 || checkForMajorWorldgen(aWorld, aMinX, aMinZ, aMaxX, aMaxZ)) return F;
		boolean temp = T;
		for (String tName : aBiomeNames) if (BIOMES_PLAINS.contains(tName) || BIOMES_WOODS.contains(tName) || BIOMES_SWAMP.contains(tName)) {temp = F; break;}
		if (temp) return F;
		int tX = aMinX + aRandom.nextInt(16), tZ = aMinZ + aRandom.nextInt(16);
		for (int tY = aWorld.provider.hasNoSky ? 80 : aWorld.getHeight()-50; tY > 0; tY--) {
			Block tContact = aChunk.getBlock(tX&15, tY, tZ&15);
			if (tContact == NB || tContact.isAir(aWorld, tX, tY, tZ)) continue;
			if (!BlocksGT.plantableTrees.contains(tContact)) continue;
			if (!aChunk.getBlock(tX&15, tY+1, tZ&15).isAir(aWorld, tX, tY+1, tZ)) return F;
			switch(aRandom.nextInt(3)) {
			case 0:
				if (aRandom.nextBoolean())  WD.set(aWorld, tX  , tY-1, tZ  , BlocksGT.Log1, PILLARS_Y[2], 2);
											WD.set(aWorld, tX  , tY  , tZ  , BlocksGT.Log1, PILLARS_Y[2], 2);
											WD.set(aWorld, tX  , tY+1, tZ  , BlocksGT.Log1, PILLARS_Y[2], 2);
											WD.set(aWorld, tX  , tY+2, tZ  , BlocksGT.Log1, PILLARS_Y[2], 2);
				if (aRandom.nextBoolean())  WD.set(aWorld, tX  , tY+3, tZ  , BlocksGT.Log1, PILLARS_Y[2], 2);
				return T;
			case 1:
				if (aRandom.nextBoolean())  WD.set(aWorld, tX-2, tY+1, tZ  , BlocksGT.Log1, PILLARS_X[2], 2);
											WD.set(aWorld, tX-1, tY+1, tZ  , BlocksGT.Log1, PILLARS_X[2], 2);
											WD.set(aWorld, tX  , tY+1, tZ  , BlocksGT.Log1, PILLARS_X[2], 2);
											WD.set(aWorld, tX+1, tY+1, tZ  , BlocksGT.Log1, PILLARS_X[2], 2);
				if (aRandom.nextBoolean())  WD.set(aWorld, tX+2, tY+1, tZ  , BlocksGT.Log1, PILLARS_X[2], 2);
				
				if (aRandom.nextBoolean())  setMushroom(aWorld, tX-1, tY+2, tZ  , aRandom);
				if (aRandom.nextBoolean())  setMushroom(aWorld, tX  , tY+2, tZ  , aRandom);
				if (aRandom.nextBoolean())  setMushroom(aWorld, tX+1, tY+2, tZ  , aRandom);
				return T;
			case 2:
				if (aRandom.nextBoolean())  WD.set(aWorld, tX  , tY+1, tZ-2, BlocksGT.Log1, PILLARS_Z[2], 2);
											WD.set(aWorld, tX  , tY+1, tZ-1, BlocksGT.Log1, PILLARS_Z[2], 2);
											WD.set(aWorld, tX  , tY+1, tZ  , BlocksGT.Log1, PILLARS_Z[2], 2);
											WD.set(aWorld, tX  , tY+1, tZ+1, BlocksGT.Log1, PILLARS_Z[2], 2);
				if (aRandom.nextBoolean())  WD.set(aWorld, tX  , tY+1, tZ+2, BlocksGT.Log1, PILLARS_Z[2], 2);
				
				if (aRandom.nextBoolean())  setMushroom(aWorld, tX  , tY+2, tZ-1, aRandom);
				if (aRandom.nextBoolean())  setMushroom(aWorld, tX  , tY+2, tZ  , aRandom);
				if (aRandom.nextBoolean())  setMushroom(aWorld, tX  , tY+2, tZ+1, aRandom);
				return T;
			}
		}
		return T;
	}
	
	public boolean setMushroom(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		if (!WD.air(aWorld, aX, aY, aZ)) return F;
		switch(aRandom.nextInt(MD.HaC.mLoaded?3:2)) {
		case 0: return WD.set(aWorld, aX, aY, aZ, Blocks.red_mushroom, 0, 2);
		case 1: return WD.set(aWorld, aX, aY, aZ, Blocks.brown_mushroom, 0, 2);
		case 2: return WD.set(aWorld, aX, aY, aZ, ST.block(MD.HaC, "mushroomgarden"), 0, 2);
		}
		return F;
	}
}
