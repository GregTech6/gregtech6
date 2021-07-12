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
import gregapi.data.MT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenDeepOcean extends WorldgenObject {
	@SafeVarargs
	public WorldgenDeepOcean(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (!aBiomeNames.contains(BiomeGenBase.deepOcean.biomeName)) return F;
		int i = 3 + aRandom.nextInt(9), j = 30 + aRandom.nextInt(9), k = 3 + aRandom.nextInt(9), m = 0, n = 0;
		if (WD.anywater(aChunk.getBlock(i, j, k))) {
			switch (new NoiseGenerator(aWorld).get(aMinX+8, 32, aMinZ+8, 16)) {
			default:
				// Keep Deep Ocean Normal.
				return F;
			case  8: case  9: case 10: case 11:
				// Corals maybe?
				return F;
			case 12: case 13:
				// Add Dark Prismarine Pylons.
				for (int l =  8; l < 11; l++) {
					WD.set(aChunk, i  , j+l, k  , BlocksGT.PrismarineDark, 0);
					WD.set(aChunk, i  , j-l, k  , BlocksGT.PrismarineDark, 0);
					if (aRandom.nextInt(8) == 0) BlocksGT.ores_normal[14].placeBlock(aWorld, aMinX+i+m, j+l, aMinZ+k+n, SIDE_UNKNOWN, MT.OREMATS.Garnierite.mID, null, F, T);
				}
				for (int l =  5; l <  8; l++) for (m = -1; m <= 1; m++) for (n = -1; n <= 1; n++) {
					WD.set(aChunk, i+m, j+l, k+n, BlocksGT.PrismarineDark, 0);
					WD.set(aChunk, i+m, j-l, k+n, BlocksGT.PrismarineDark, 0);
					if (aRandom.nextInt(8) == 0) BlocksGT.ores_normal[14].placeBlock(aWorld, aMinX+i+m, j+l, aMinZ+k+n, SIDE_UNKNOWN, MT.OREMATS.Garnierite.mID, null, F, T);
				}
				for (int l =  2; l <  5; l++) for (m = -2; m <= 2; m++) for (n = -2; n <= 2; n++) {
					WD.set(aChunk, i+m, j+l, k+n, BlocksGT.PrismarineDark, 0);
					WD.set(aChunk, i+m, j-l, k+n, BlocksGT.PrismarineDark, 0);
					if (aRandom.nextInt(8) == 0) BlocksGT.ores_normal[14].placeBlock(aWorld, aMinX+i+m, j+l, aMinZ+k+n, SIDE_UNKNOWN, MT.OREMATS.Garnierite.mID, null, F, T);
				}
				for (int l =  0; l <  2; l++) for (m = -3; m <= 3; m++) for (n = -3; n <= 3; n++) {
					WD.set(aChunk, i+m, j+l, k+n, BlocksGT.PrismarineDark, 0);
					WD.set(aChunk, i+m, j-l, k+n, BlocksGT.PrismarineDark, 0);
					if (aRandom.nextInt(8) == 0) BlocksGT.ores_normal[14].placeBlock(aWorld, aMinX+i+m, j+l, aMinZ+k+n, SIDE_UNKNOWN, MT.OREMATS.Garnierite.mID, null, F, T);
				}
				return T;
			case 14: case 15:
				// Add Light Prismarine Pylons.
				for (int l =  8; l < 11; l++) {
					WD.set(aChunk, i  , j+l, k  , BlocksGT.PrismarineLight, 0);
					WD.set(aChunk, i  , j-l, k  , BlocksGT.PrismarineLight, 0);
					if (aRandom.nextInt(8) == 0) BlocksGT.ores_normal[13].placeBlock(aWorld, aMinX+i+m, j+l, aMinZ+k+n, SIDE_UNKNOWN, MT.MnO2.mID, null, F, T);
				}
				for (int l =  5; l <  8; l++) for (m = -1; m <= 1; m++) for (n = -1; n <= 1; n++) {
					WD.set(aChunk, i+m, j+l, k+n, BlocksGT.PrismarineLight, 0);
					WD.set(aChunk, i+m, j-l, k+n, BlocksGT.PrismarineLight, 0);
					if (aRandom.nextInt(8) == 0) BlocksGT.ores_normal[13].placeBlock(aWorld, aMinX+i+m, j+l, aMinZ+k+n, SIDE_UNKNOWN, MT.MnO2.mID, null, F, T);
				}
				for (int l =  2; l <  5; l++) for (m = -2; m <= 2; m++) for (n = -2; n <= 2; n++) {
					WD.set(aChunk, i+m, j+l, k+n, BlocksGT.PrismarineLight, 0);
					WD.set(aChunk, i+m, j-l, k+n, BlocksGT.PrismarineLight, 0);
					if (aRandom.nextInt(8) == 0) BlocksGT.ores_normal[13].placeBlock(aWorld, aMinX+i+m, j+l, aMinZ+k+n, SIDE_UNKNOWN, MT.MnO2.mID, null, F, T);
				}
				for (int l =  0; l <  2; l++) for (m = -3; m <= 3; m++) for (n = -3; n <= 3; n++) {
					WD.set(aChunk, i+m, j+l, k+n, BlocksGT.PrismarineLight, 0);
					WD.set(aChunk, i+m, j-l, k+n, BlocksGT.PrismarineLight, 0);
					if (aRandom.nextInt(8) == 0) BlocksGT.ores_normal[13].placeBlock(aWorld, aMinX+i+m, j+l, aMinZ+k+n, SIDE_UNKNOWN, MT.MnO2.mID, null, F, T);
				}
				return T;
			}
		}
		return F;
	}
}
