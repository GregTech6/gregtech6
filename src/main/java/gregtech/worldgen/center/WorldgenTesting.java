/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregtech.worldgen.center;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenTesting extends WorldgenObject {
	public int mHeight = 66;
	
	@SafeVarargs
	public WorldgenTesting(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mHeight = ConfigsGT.WORLDGEN.get(mCategory, "Height", mHeight);
		GENERATE_TESTING = mEnabled;
	}
	
	@Override
	public boolean enabled(World aWorld, int aDimType) {
		return GENERATE_TESTING && aWorld.provider.dimensionId == DIM_OVERWORLD;
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if ((aMinX != 32 && aMinX != 48) || (aMinZ != -32 && aMinZ != -48)) return F;
		
		for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
			for (int k = 1; k <= mHeight; k++) WD.set(aChunk, i, k, j, BlocksGT.Concrete, DYE_INDEX_Gray);
			for (int k = mHeight+2; k < 256; k++) WD.set(aChunk, i, k, j, NB, 0);
			
			if ((i == 0 && aMinX == 32) || (i == 15 && aMinX == 48) || (j == 0 && aMinZ == -48) || (j == 15 && aMinZ == -32)) {
			WD.set(aChunk, i, mHeight+ 1, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+ 2, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+ 3, j, BlocksGT.CFoam, DYE_INDEX_Yellow);
			WD.set(aChunk, i, mHeight+ 4, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+ 5, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+ 6, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+ 7, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			WD.set(aChunk, i, mHeight+ 8, j, BlocksGT.CFoam, DYE_INDEX_LightBlue);
			} else if ((i == 3 || i == 6 || i == 9 || i == 12) && (j == 3 || j == 6 || j == 9 || j == 12)) {
			WD.set(aChunk, i, mHeight+ 1, j, BlocksGT.GlowGlass, DYE_INDEX_Yellow);
			WD.set(aChunk, i, mHeight+ 9, j, BlocksGT.GlowGlass, DYE_INDEX_LightBlue);
			} else {
			WD.set(aChunk, i, mHeight+ 1, j, BlocksGT.CFoam, DYE_INDEX_Gray);
			WD.set(aChunk, i, mHeight+ 9, j, BlocksGT.CFoam, DYE_INDEX_LightGray);
			}
		}
		
		aWorld.setSpawnLocation(0, mHeight+5, 0);
		return T;
	}
}
