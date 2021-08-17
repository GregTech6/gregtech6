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

package gregtech.worldgen.center;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.block.metatype.BlockStones;
import gregapi.data.CS.BlocksGT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenNexus extends WorldgenObject {
	public int mHeight = 66;
	
	@SafeVarargs
	public WorldgenNexus(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mHeight = getConfigFile().get(mCategory, "Height", WD.waterLevel()+4);
		GENERATE_NEXUS = mEnabled;
	}
	
	@Override
	public boolean enabled(World aWorld, int aDimType) {
		return GENERATE_NEXUS && aWorld.provider.dimensionId == DIM_OVERWORLD;
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (aMinX != 16 || aMinZ != -48) return F;
		final boolean[] fWindow = {F,T,T,T,T,F,T,T,T,T,F,T,T,T,T,F};
		if (GENERATE_STREETS) {
			for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
				for (int k = 2; k < 64; k++) WD.set(aChunk, i, mHeight+k, j, NB, 0);
				for (int k = 1; k < mHeight; k++) WD.set(aChunk, i, k, j, BlocksGT.Concrete, DYE_INDEX_Gray);
				WD.set(aChunk, i, mHeight+ 1, j, BlocksGT.CFoam, DYE_INDEX_Gray);
				if (i == 0 || i == 15 || j == 0 || j == 15) {
				WD.set(aChunk, i, mHeight+ 0, j, BlocksGT.Concrete, DYE_INDEX_Gray);
				WD.set(aChunk, i, mHeight+ 2, j, BlocksGT.Concrete, DYE_INDEX_LightGray);
				WD.set(aChunk, i, mHeight+ 3, j, BlocksGT.Concrete, DYE_INDEX_LightGray);
				WD.set(aChunk, i, mHeight+ 4, j, BlocksGT.Concrete, DYE_INDEX_LightGray);
				WD.set(aChunk, i, mHeight+ 5, j, BlocksGT.Concrete, DYE_INDEX_LightGray);
				WD.set(aChunk, i, mHeight+ 6, j, BlocksGT.Concrete, DYE_INDEX_LightGray);
				WD.set(aChunk, i, mHeight+ 7, j, BlocksGT.CFoam, DYE_INDEX_Gray);
				WD.set(aChunk, i, mHeight+ 8, j, BlocksGT.Concrete, DYE_INDEX_LightGray);
				if (fWindow[i] || fWindow[j]) {
				WD.set(aChunk, i, mHeight+ 9, j, BlocksGT.Glass, DYE_INDEX_LightBlue);
				WD.set(aChunk, i, mHeight+10, j, BlocksGT.Glass, DYE_INDEX_LightBlue);
				WD.set(aChunk, i, mHeight+11, j, BlocksGT.Glass, DYE_INDEX_LightBlue);
				} else {
				WD.set(aChunk, i, mHeight+ 9, j, BlocksGT.Concrete, DYE_INDEX_LightGray);
				WD.set(aChunk, i, mHeight+10, j, BlocksGT.Concrete, DYE_INDEX_LightGray);
				WD.set(aChunk, i, mHeight+11, j, BlocksGT.Concrete, DYE_INDEX_LightGray);
				}
				WD.set(aChunk, i, mHeight+12, j, BlocksGT.Concrete, DYE_INDEX_LightGray);
				} else {
				WD.set(aChunk, i, mHeight+ 0, j, Blocks.obsidian, 0);
				}
				WD.set(aChunk, i, mHeight+13, j, BlocksGT.CFoam, DYE_INDEX_Gray);
			}
		} else {
			int tRockType = RNGSUS.nextInt(BlocksGT.stones.length);
			
			for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
				for (int k = 2; k < 64; k++) WD.set(aChunk, i, mHeight+k, j, NB, 0);
				for (int k = 1; k < mHeight; k++) WD.set(aChunk, i, k, j, BlocksGT.stones[tRockType], 0);
				WD.set(aChunk, i, mHeight+ 1, j, BlocksGT.stones[tRockType], BlockStones.TILES);
				if (i == 0 || i == 15 || j == 0 || j == 15) {
				WD.set(aChunk, i, mHeight+ 0, j, BlocksGT.stones[tRockType], 3+RNGSUS.nextInt(3));
				WD.set(aChunk, i, mHeight+ 2, j, BlocksGT.stones[tRockType], 3+RNGSUS.nextInt(3));
				WD.set(aChunk, i, mHeight+ 3, j, BlocksGT.stones[tRockType], 3+RNGSUS.nextInt(3));
				WD.set(aChunk, i, mHeight+ 4, j, BlocksGT.stones[tRockType], 3+RNGSUS.nextInt(3));
				WD.set(aChunk, i, mHeight+ 5, j, BlocksGT.stones[tRockType], 3+RNGSUS.nextInt(3));
				WD.set(aChunk, i, mHeight+ 6, j, BlocksGT.stones[tRockType], 3+RNGSUS.nextInt(3));
				WD.set(aChunk, i, mHeight+ 7, j, BlocksGT.stones[tRockType], BlockStones.CHISL);
				WD.set(aChunk, i, mHeight+ 8, j, BlocksGT.stones[tRockType], 3+RNGSUS.nextInt(3));
				if (fWindow[i] || fWindow[j]) {
				WD.set(aChunk, i, mHeight+ 9, j, Blocks.glass_pane, 0);
				WD.set(aChunk, i, mHeight+10, j, Blocks.glass_pane, 0);
				WD.set(aChunk, i, mHeight+11, j, Blocks.glass_pane, 0);
				} else {
				WD.set(aChunk, i, mHeight+ 9, j, BlocksGT.stones[tRockType], 3+RNGSUS.nextInt(3));
				WD.set(aChunk, i, mHeight+10, j, BlocksGT.stones[tRockType], 3+RNGSUS.nextInt(3));
				WD.set(aChunk, i, mHeight+11, j, BlocksGT.stones[tRockType], 3+RNGSUS.nextInt(3));
				}
				WD.set(aChunk, i, mHeight+12, j, BlocksGT.stones[tRockType], 3+RNGSUS.nextInt(3));
				} else {
				WD.set(aChunk, i, mHeight+ 0, j, Blocks.obsidian, 0);
				}
				WD.set(aChunk, i, mHeight+13, j, BlocksGT.stones[tRockType], BlockStones.STILE);
			}
		}
		
//      WD.set(aChunk, 5, mHeight+1,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
//      WD.set(aChunk, 6, mHeight+1,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
//      WD.set(aChunk, 7, mHeight+1,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
//      WD.set(aChunk, 8, mHeight+1,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
//      WD.set(aChunk, 9, mHeight+1,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
//      WD.set(aChunk,10, mHeight+1,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 5, mHeight+2,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 6, mHeight+2,15, NB, 0);
		WD.set(aChunk, 7, mHeight+2,15, NB, 0);
		WD.set(aChunk, 8, mHeight+2,15, NB, 0);
		WD.set(aChunk, 9, mHeight+2,15, NB, 0);
		WD.set(aChunk,10, mHeight+2,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 5, mHeight+3,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 6, mHeight+3,15, NB, 0);
		WD.set(aChunk, 7, mHeight+3,15, NB, 0);
		WD.set(aChunk, 8, mHeight+3,15, NB, 0);
		WD.set(aChunk, 9, mHeight+3,15, NB, 0);
		WD.set(aChunk,10, mHeight+3,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 5, mHeight+4,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 6, mHeight+4,15, NB, 0);
		WD.set(aChunk, 7, mHeight+4,15, NB, 0);
		WD.set(aChunk, 8, mHeight+4,15, NB, 0);
		WD.set(aChunk, 9, mHeight+4,15, NB, 0);
		WD.set(aChunk,10, mHeight+4,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 5, mHeight+5,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 6, mHeight+5,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 7, mHeight+5,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 8, mHeight+5,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 9, mHeight+5,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk,10, mHeight+5,15, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		
//      WD.set(aChunk, 6, mHeight+1,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
//      WD.set(aChunk, 7, mHeight+1,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
//      WD.set(aChunk, 8, mHeight+1,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
//      WD.set(aChunk, 9, mHeight+1,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 6, mHeight+2,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 9, mHeight+2,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 6, mHeight+3,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 9, mHeight+3,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 6, mHeight+4,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 7, mHeight+4,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 8, mHeight+4,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		WD.set(aChunk, 9, mHeight+4,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Gray);
		
		WD.set(aChunk, 6, mHeight+1, 1, Blocks.obsidian, 0);
		WD.set(aChunk, 7, mHeight+1, 1, Blocks.obsidian, 0);
		WD.set(aChunk, 8, mHeight+1, 1, Blocks.obsidian, 0);
		WD.set(aChunk, 9, mHeight+1, 1, Blocks.obsidian, 0);
		WD.set(aChunk, 6, mHeight+2, 1, Blocks.obsidian, 0);
		WD.set(aChunk, 9, mHeight+2, 1, Blocks.obsidian, 0);
		WD.set(aChunk, 6, mHeight+3, 1, Blocks.obsidian, 0);
		WD.set(aChunk, 9, mHeight+3, 1, Blocks.obsidian, 0);
		WD.set(aChunk, 6, mHeight+4, 1, Blocks.obsidian, 0);
		WD.set(aChunk, 9, mHeight+4, 1, Blocks.obsidian, 0);
		WD.set(aChunk, 6, mHeight+5, 1, Blocks.glowstone, 0);
		WD.set(aChunk, 7, mHeight+5, 1, Blocks.obsidian, 0);
		WD.set(aChunk, 8, mHeight+5, 1, Blocks.obsidian, 0);
		WD.set(aChunk, 9, mHeight+5, 1, Blocks.glowstone, 0);
		
		// -----
		
		WD.set(aChunk, 1, mHeight+1, 1, Blocks.glowstone, 0);
		WD.set(aChunk, 1, mHeight+1, 2, Blocks.end_portal_frame, 3);
		WD.set(aChunk, 1, mHeight+1, 3, Blocks.end_portal_frame, 3);
		WD.set(aChunk, 1, mHeight+1, 4, Blocks.end_portal_frame, 3);
		WD.set(aChunk, 1, mHeight+1, 5, Blocks.glowstone, 0);
		WD.set(aChunk, 2, mHeight+1, 1, Blocks.end_portal_frame, 0);
		WD.set(aChunk, 2, mHeight+1, 2, NB, 0);
		WD.set(aChunk, 2, mHeight+1, 3, NB, 0);
		WD.set(aChunk, 2, mHeight+1, 4, NB, 0);
		WD.set(aChunk, 2, mHeight+1, 5, Blocks.end_portal_frame, 2);
		WD.set(aChunk, 3, mHeight+1, 1, Blocks.end_portal_frame, 0);
		WD.set(aChunk, 3, mHeight+1, 2, NB, 0);
		WD.set(aChunk, 3, mHeight+1, 3, NB, 0);
		WD.set(aChunk, 3, mHeight+1, 4, NB, 0);
		WD.set(aChunk, 3, mHeight+1, 5, Blocks.end_portal_frame, 2);
		WD.set(aChunk, 4, mHeight+1, 1, Blocks.end_portal_frame, 0);
		WD.set(aChunk, 4, mHeight+1, 2, NB, 0);
		WD.set(aChunk, 4, mHeight+1, 3, NB, 0);
		WD.set(aChunk, 4, mHeight+1, 4, NB, 0);
		WD.set(aChunk, 4, mHeight+1, 5, Blocks.end_portal_frame, 2);
		WD.set(aChunk, 5, mHeight+1, 1, Blocks.glowstone, 0);
		WD.set(aChunk, 5, mHeight+1, 2, Blocks.end_portal_frame, 1);
		WD.set(aChunk, 5, mHeight+1, 3, Blocks.end_portal_frame, 1);
		WD.set(aChunk, 5, mHeight+1, 4, Blocks.end_portal_frame, 1);
		WD.set(aChunk, 5, mHeight+1, 5, Blocks.glowstone, 0);
		
		// -----
		
		WD.set(aChunk,10, mHeight+1, 1, Blocks.glowstone, 0);
		WD.set(aChunk,10, mHeight+1, 2, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,10, mHeight+1, 3, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,10, mHeight+1, 4, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,10, mHeight+1, 5, Blocks.glowstone, 0);
		WD.set(aChunk,11, mHeight+1, 1, Blocks.grass, 0);
		WD.set(aChunk,11, mHeight+1, 2, Blocks.grass, 0);
		WD.set(aChunk,11, mHeight+1, 3, Blocks.grass, 0);
		WD.set(aChunk,11, mHeight+1, 4, Blocks.grass, 0);
		WD.set(aChunk,11, mHeight+1, 5, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,12, mHeight+1, 1, Blocks.grass, 0);
		WD.set(aChunk,12, mHeight+1, 2, Blocks.water, 0);
		WD.set(aChunk,12, mHeight+1, 3, Blocks.water, 0);
		WD.set(aChunk,12, mHeight+1, 4, Blocks.grass, 0);
		WD.set(aChunk,12, mHeight+1, 5, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,13, mHeight+1, 1, Blocks.grass, 0);
		WD.set(aChunk,13, mHeight+1, 2, Blocks.water, 0);
		WD.set(aChunk,13, mHeight+1, 3, Blocks.water, 0);
		WD.set(aChunk,13, mHeight+1, 4, Blocks.grass, 0);
		WD.set(aChunk,13, mHeight+1, 5, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,14, mHeight+1, 1, Blocks.grass, 0);
		WD.set(aChunk,14, mHeight+1, 2, Blocks.grass, 0);
		WD.set(aChunk,14, mHeight+1, 3, Blocks.grass, 0);
		WD.set(aChunk,14, mHeight+1, 4, Blocks.grass, 0);
		WD.set(aChunk,14, mHeight+1, 5, Blocks.glowstone, 0);
		WD.set(aChunk,11, mHeight+2, 1, Blocks.yellow_flower, 0);
		WD.set(aChunk,11, mHeight+2, 2, Blocks.yellow_flower, 0);
		WD.set(aChunk,11, mHeight+2, 3, Blocks.yellow_flower, 0);
		WD.set(aChunk,11, mHeight+2, 4, Blocks.yellow_flower, 0);
		WD.set(aChunk,12, mHeight+2, 1, Blocks.yellow_flower, 0);
		WD.set(aChunk,12, mHeight+2, 4, Blocks.yellow_flower, 0);
		WD.set(aChunk,13, mHeight+2, 1, Blocks.yellow_flower, 0);
		WD.set(aChunk,13, mHeight+2, 4, Blocks.yellow_flower, 0);
		WD.set(aChunk,14, mHeight+2, 1, Blocks.yellow_flower, 0);
		WD.set(aChunk,14, mHeight+2, 2, Blocks.yellow_flower, 0);
		WD.set(aChunk,14, mHeight+2, 3, Blocks.yellow_flower, 0);
		WD.set(aChunk,14, mHeight+2, 4, Blocks.yellow_flower, 0);
		
		// -----
		
		WD.set(aChunk, 1, mHeight+0,10, Blocks.sandstone, 2);
		WD.set(aChunk, 1, mHeight+0,11, Blocks.sandstone, 2);
		WD.set(aChunk, 1, mHeight+0,12, Blocks.sandstone, 2);
		WD.set(aChunk, 1, mHeight+0,13, Blocks.sandstone, 2);
		WD.set(aChunk, 1, mHeight+0,14, Blocks.sandstone, 2);
		WD.set(aChunk, 2, mHeight+0,10, Blocks.sandstone, 2);
		WD.set(aChunk, 2, mHeight+0,11, Blocks.sandstone, 2);
		WD.set(aChunk, 2, mHeight+0,12, Blocks.sandstone, 2);
		WD.set(aChunk, 2, mHeight+0,13, Blocks.sandstone, 2);
		WD.set(aChunk, 2, mHeight+0,14, Blocks.sandstone, 2);
		WD.set(aChunk, 3, mHeight+0,10, Blocks.sandstone, 2);
		WD.set(aChunk, 3, mHeight+0,11, Blocks.sandstone, 2);
		WD.set(aChunk, 3, mHeight+0,12, Blocks.sandstone, 2);
		WD.set(aChunk, 3, mHeight+0,13, Blocks.sandstone, 2);
		WD.set(aChunk, 3, mHeight+0,14, Blocks.sandstone, 2);
		WD.set(aChunk, 4, mHeight+0,10, Blocks.sandstone, 2);
		WD.set(aChunk, 4, mHeight+0,11, Blocks.sandstone, 2);
		WD.set(aChunk, 4, mHeight+0,12, Blocks.sandstone, 2);
		WD.set(aChunk, 4, mHeight+0,13, Blocks.sandstone, 2);
		WD.set(aChunk, 4, mHeight+0,14, Blocks.sandstone, 2);
		WD.set(aChunk, 5, mHeight+0,10, Blocks.sandstone, 2);
		WD.set(aChunk, 5, mHeight+0,11, Blocks.sandstone, 2);
		WD.set(aChunk, 5, mHeight+0,12, Blocks.sandstone, 2);
		WD.set(aChunk, 5, mHeight+0,13, Blocks.sandstone, 2);
		WD.set(aChunk, 5, mHeight+0,14, Blocks.sandstone, 2);
		
		WD.set(aChunk, 1, mHeight+1,10, Blocks.sandstone, 2);
		WD.set(aChunk, 1, mHeight+1,11, Blocks.sandstone, 2);
		WD.set(aChunk, 1, mHeight+1,12, Blocks.sandstone, 2);
		WD.set(aChunk, 1, mHeight+1,13, Blocks.sandstone, 2);
		WD.set(aChunk, 1, mHeight+1,14, Blocks.sandstone, 2);
		WD.set(aChunk, 2, mHeight+1,10, Blocks.sandstone, 2);
		WD.set(aChunk, 2, mHeight+1,11, NB, 0);
		WD.set(aChunk, 2, mHeight+1,12, NB, 0);
		WD.set(aChunk, 2, mHeight+1,13, NB, 0);
		WD.set(aChunk, 2, mHeight+1,14, Blocks.sandstone, 2);
		WD.set(aChunk, 3, mHeight+1,10, Blocks.sandstone, 2);
		WD.set(aChunk, 3, mHeight+1,11, NB, 0);
		WD.set(aChunk, 3, mHeight+1,12, NB, 0);
		WD.set(aChunk, 3, mHeight+1,13, NB, 0);
		WD.set(aChunk, 3, mHeight+1,14, Blocks.sandstone, 2);
		WD.set(aChunk, 4, mHeight+1,10, Blocks.sandstone, 2);
		WD.set(aChunk, 4, mHeight+1,11, NB, 0);
		WD.set(aChunk, 4, mHeight+1,12, NB, 0);
		WD.set(aChunk, 4, mHeight+1,13, NB, 0);
		WD.set(aChunk, 4, mHeight+1,14, Blocks.sandstone, 2);
		WD.set(aChunk, 5, mHeight+1,10, Blocks.sandstone, 2);
		WD.set(aChunk, 5, mHeight+1,11, Blocks.sandstone, 2);
		WD.set(aChunk, 5, mHeight+1,12, Blocks.sandstone, 2);
		WD.set(aChunk, 5, mHeight+1,13, Blocks.sandstone, 2);
		WD.set(aChunk, 5, mHeight+1,14, Blocks.sandstone, 2);
		
		WD.set(aChunk, 1, mHeight+2,10, Blocks.sandstone, 2);
		WD.set(aChunk, 1, mHeight+2,14, Blocks.sandstone, 2);
		WD.set(aChunk, 5, mHeight+2,10, Blocks.sandstone, 2);
		WD.set(aChunk, 5, mHeight+2,14, Blocks.sandstone, 2);
		
		WD.set(aChunk, 1, mHeight+3,10, Blocks.sandstone, 1);
		WD.set(aChunk, 1, mHeight+3,14, Blocks.sandstone, 1);
		WD.set(aChunk, 5, mHeight+3,10, Blocks.sandstone, 1);
		WD.set(aChunk, 5, mHeight+3,14, Blocks.sandstone, 1);
		
		WD.set(aChunk, 1, mHeight+4,10, Blocks.glowstone, 0);
		WD.set(aChunk, 1, mHeight+4,14, Blocks.glowstone, 0);
		WD.set(aChunk, 5, mHeight+4,10, Blocks.glowstone, 0);
		WD.set(aChunk, 5, mHeight+4,14, Blocks.glowstone, 0);
		
		// -----
		
		WD.set(aChunk,10, mHeight+1,10, Blocks.glowstone, 0);
		WD.set(aChunk,10, mHeight+1,11, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,10, mHeight+1,12, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,10, mHeight+1,13, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,10, mHeight+1,14, Blocks.glowstone, 0);
		WD.set(aChunk,11, mHeight+1,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,12, mHeight+1,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,13, mHeight+1,14, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,14, mHeight+1,14, Blocks.glowstone, 0);
		WD.set(aChunk,14, mHeight+1,13, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,14, mHeight+1,12, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,14, mHeight+1,11, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,14, mHeight+1,10, Blocks.glowstone, 0);
		WD.set(aChunk,13, mHeight+1,10, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,12, mHeight+1,10, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		WD.set(aChunk,11, mHeight+1,10, GENERATE_STREETS?BlocksGT.CFoam:Blocks.stained_hardened_clay, DYE_INDEX_Black);
		
		aWorld.setSpawnLocation(0, mHeight+5, 0);
		return T;
	}
}
