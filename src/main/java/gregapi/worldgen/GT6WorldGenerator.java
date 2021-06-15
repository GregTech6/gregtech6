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

package gregapi.worldgen;

import static gregapi.data.CS.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.BiomeNameSet;
import gregapi.util.WD;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class GT6WorldGenerator {
	public static class WorldGenContainer implements Runnable {
		public final int mMinX, mMinZ, mMaxX, mMaxZ, mDimType;
		public final World mWorld;
		public final Random mRandom;
		public final List<WorldgenObject> mGenNormal;
		public final List<WorldgenObject> mGenLargeOres;
		
		public WorldGenContainer(List<WorldgenObject> aGenNormal, List<WorldgenObject> aGenLargeOres, int aDimType, World aWorld, int aX, int aZ) {
			mMinX = aX; mMinZ = aZ; mMaxX = aX + 15; mMaxZ = aZ + 15;
			mDimType = aDimType;
			mWorld = aWorld;
			mGenNormal = aGenNormal;
			mGenLargeOres = aGenLargeOres;
			mRandom = WD.random(aWorld, aX, aZ);
		}
		
		@Override @SuppressWarnings("unchecked")
		public void run() {
			if (!mGenNormal.isEmpty()) {
				Chunk tChunk = mWorld.getChunkFromBlockCoords(mMinX+7, mMinZ+7);
				if (tChunk == null) return;
				BiomeGenBase[][] tBiomes = new BiomeGenBase[16][16];
				BiomeNameSet tBiomeNames = new BiomeNameSet();
				for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
					tBiomes[i][j] = tChunk.getBiomeGenForWorldCoords(i, j, mWorld.provider.worldChunkMgr);
					if (tBiomes[i][j] == null) {
						tBiomes[i][j] = (mDimType == DIM_NETHER ? BiomeGenBase.hell : mDimType == DIM_END ? BiomeGenBase.sky : BiomeGenBase.plains);
					} else {
						tBiomeNames.add(tBiomes[i][j].biomeName);
					}
				}
				
				// Yes, it has to be looped twice in a row, this cannot be optimized into one Loop!
				for (WorldgenObject tWorldGen : mGenNormal) tWorldGen.reset(mWorld, tChunk, mDimType, mMinX, mMinZ, mMaxX, mMaxZ, mRandom, tBiomes, tBiomeNames);
				for (WorldgenObject tWorldGen : mGenNormal) if (tWorldGen.enabled(mWorld, mDimType)) {
					try {
						tWorldGen.generate(mWorld, tChunk, mDimType, mMinX, mMinZ, mMaxX, mMaxZ, mRandom, tBiomes, tBiomeNames);
					} catch (Throwable e) {
						e.printStackTrace(ERR);
					}
					if (tChunk.lastSaveTime == Long.MAX_VALUE) {
						tChunk.hasEntities = tChunk.isModified = F;
						throw new RuntimeException("A corrupted Chunk was found while generating at (X: " + mMinX + " Z: " + mMinZ + "), please try loading the World again to see if this specific Chunk consistently corrupts, and >>>ONLY<<< if it does so, please report this to Greg.");
					}
				}
				
				if (mGenLargeOres != null && !mGenLargeOres.isEmpty()) {
					int tMaxWeight = 0;
					List<WorldgenOresLarge> tList = new ArrayListNoNulls<>();
					
					for (WorldgenObject tWorldGen : mGenLargeOres) if (tWorldGen.enabled(mWorld, mDimType)) {tMaxWeight += ((WorldgenOresLarge)tWorldGen).mWeight; tList.add((WorldgenOresLarge)tWorldGen);}
					
					if (tMaxWeight > 0 && !tList.isEmpty()) for (int tX=-32; tX<=32; tX+=16) for (int tZ=-32; tZ<=32; tZ+=16) {
						int tChunkX = mMinX+tX, tChunkZ = mMinZ+tZ;
						if (((tChunkX >> 4)+402653184) % 3 == 1 && ((tChunkZ >> 4)+402653184) % 3 == 1) {
							Random aRandom = WD.random(mWorld, tChunkX, tChunkZ);
							int tRandomWeight = aRandom.nextInt(tMaxWeight);
							for (WorldgenOresLarge tWorldGen : tList) {
								tRandomWeight -= tWorldGen.mWeight;
								if (tRandomWeight <= 0) {try {tWorldGen.generate(mWorld, tChunk, mMinX, mMinZ, mMaxX, mMaxZ, tChunkX, tChunkZ, mRandom);} catch (Throwable e) {e.printStackTrace(ERR);} break;}
							}
						}
					}
				}
				
				// Kill off every single Item Entity that may have dropped during Worldgen.
				for (EntityItem tEntity : (List<EntityItem>)mWorld.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(mMinX-32, 0, mMinZ-32, mMinX+48, 256, mMinZ+48))) tEntity.setDead();
				// Prevent Snow Layers from killing Treestumps. I really hope this works...
				Arrays.fill(tChunk.precipitationHeightMap, -999);
				// Chunk got modified, duh.
				tChunk.isModified = T;
			}
		}
	}
	
	private static final List<Runnable> LIST = new ArrayListNoNulls<>();
	private static boolean LOCK = F;
	public static boolean PFAA = F, TFC = F;
	
	public static void generate(World aWorld, int aX, int aZ, boolean aGalactiCraft) {
		switch(aWorld.provider.dimensionId) {
		case -2147483648  : return;
		case DIM_OVERWORLD: generate(new WorldGenContainer(TFC ? GEN_TFC : PFAA ? GEN_PFAA : GENERATE_STONE ? GEN_GT : GEN_OVERWORLD, TFC ? ORE_TFC : PFAA ? ORE_PFAA : GENERATE_STONE ? null : ORE_OVERWORLD, DIM_OVERWORLD, aWorld, aX, aZ)); return;
		case DIM_NETHER   : generate(new WorldGenContainer(GEN_NETHER, ORE_NETHER, DIM_NETHER, aWorld, aX, aZ)); return;
		case DIM_END      : generate(new WorldGenContainer(GEN_END   , ORE_END   , DIM_END   , aWorld, aX, aZ)); return;
		}
		
		if (WD.dimENVM         (aWorld)) {generate(new WorldGenContainer(GENERATE_STONE ? GEN_ENVM_GT : GEN_ENVM, GENERATE_STONE ? null : ORE_ENVM, DIM_ENVM, aWorld, aX, aZ)); return;}
		if (WD.dimA97          (aWorld)) {generate(new WorldGenContainer(GENERATE_STONE ? GEN_A97_GT  : GEN_A97 , GENERATE_STONE ? null : ORE_A97 , DIM_A97 , aWorld, aX, aZ)); return;}
		if (WD.dimCW2AquaCavern(aWorld)) {generate(new WorldGenContainer(GENERATE_STONE ? GEN_CW2_AquaCavern_GT : GEN_CW2_AquaCavern, GENERATE_STONE ? null : ORE_CW2_AquaCavern, DIM_CW2_AquaCavern, aWorld, aX, aZ)); return;}
		if (WD.dimCW2Caveland  (aWorld)) {generate(new WorldGenContainer(GENERATE_STONE ? GEN_CW2_Caveland_GT   : GEN_CW2_Caveland  , GENERATE_STONE ? null : ORE_CW2_Caveland  , DIM_CW2_Caveland  , aWorld, aX, aZ)); return;}
		if (WD.dimCW2Cavenia   (aWorld)) {generate(new WorldGenContainer(GENERATE_STONE ? GEN_CW2_Cavenia_GT    : GEN_CW2_Cavenia   , GENERATE_STONE ? null : ORE_CW2_Cavenia   , DIM_CW2_Cavenia   , aWorld, aX, aZ)); return;}
		if (WD.dimCW2Cavern    (aWorld)) {generate(new WorldGenContainer(GENERATE_STONE ? GEN_CW2_Cavern_GT     : GEN_CW2_Cavern    , GENERATE_STONE ? null : ORE_CW2_Cavern    , DIM_CW2_Cavern    , aWorld, aX, aZ)); return;}
		if (WD.dimCW2Caveworld (aWorld)) {generate(new WorldGenContainer(GENERATE_STONE ? GEN_CW2_Caveworld_GT  : GEN_CW2_Caveworld , GENERATE_STONE ? null : ORE_CW2_Caveworld , DIM_CW2_Caveworld , aWorld, aX, aZ)); return;}
		
		if (WD.dimMYST         (aWorld)) {generate(new WorldGenContainer(TFC ? GEN_TFC : PFAA ? GEN_PFAA : GENERATE_STONE ? GEN_GT : GEN_OVERWORLD, TFC ? ORE_TFC : PFAA ? ORE_PFAA : GENERATE_STONE ? null : ORE_OVERWORLD, DIM_OVERWORLD, aWorld, aX, aZ)); return;}
		if (WD.dimWTCH         (aWorld)) {generate(new WorldGenContainer(TFC ? GEN_TFC : PFAA ? GEN_PFAA : GENERATE_STONE ? GEN_GT : GEN_OVERWORLD, TFC ? ORE_TFC : PFAA ? ORE_PFAA : GENERATE_STONE ? null : ORE_OVERWORLD, DIM_OVERWORLD, aWorld, aX, aZ)); return;}
		
		if (WD.dimTF           (aWorld)) {generate(new WorldGenContainer(GEN_TWILIGHT    , ORE_TWILIGHT    , DIM_TWILIGHT    , aWorld, aX, aZ)); return;}
		if (WD.dimAETHER       (aWorld)) {generate(new WorldGenContainer(GEN_AETHER      , ORE_AETHER      , DIM_AETHER      , aWorld, aX, aZ)); return;}
		if (WD.dimERE          (aWorld)) {generate(new WorldGenContainer(GEN_EREBUS      , ORE_EREBUS      , DIM_EREBUS      , aWorld, aX, aZ)); return;}
		if (WD.dimBTL          (aWorld)) {generate(new WorldGenContainer(GEN_BETWEENLANDS, ORE_BETWEENLANDS, DIM_BETWEENLANDS, aWorld, aX, aZ)); return;}
		if (WD.dimATUM         (aWorld)) {generate(new WorldGenContainer(GEN_ATUM        , ORE_ATUM        , DIM_ATUM        , aWorld, aX, aZ)); return;}
		if (WD.dimALF          (aWorld)) {generate(new WorldGenContainer(GEN_ALFHEIM     , ORE_ALFHEIM     , DIM_ALFHEIM     , aWorld, aX, aZ)); return;}
		if (WD.dimDD           (aWorld)) {generate(new WorldGenContainer(GEN_DEEPDARK    , ORE_DEEPDARK    , DIM_DEEPDARK    , aWorld, aX, aZ)); return;}
		if (WD.dimTROPIC       (aWorld)) {generate(new WorldGenContainer(GEN_TROPICS     , ORE_TROPICS     , DIM_TROPICS     , aWorld, aX, aZ)); return;}
		if (WD.dimCANDY        (aWorld)) {generate(new WorldGenContainer(GEN_CANDY       , ORE_CANDY       , DIM_CANDY       , aWorld, aX, aZ)); return;}
		
		
		BiomeGenBase aBiome = aWorld.getBiomeGenForCoords(aX+7, aZ+7);
		if (aBiome == null || BIOMES_VOID.contains(aBiome.biomeName)) return;
		
		if (BIOMES_EREBUS.contains(aBiome.biomeName)) {
			generate(new WorldGenContainer(GEN_EREBUS, ORE_EREBUS, DIM_EREBUS, aWorld, aX, aZ));
			return;
		}
		if (BIOMES_MOON.contains(aBiome.biomeName)) {
			generate(new WorldGenContainer(GEN_MOON, ORE_MOON, DIM_MOON, aWorld, aX, aZ));
			return;
		}
		if (BIOMES_MARS.contains(aBiome.biomeName)) {
			generate(new WorldGenContainer(GEN_MARS, ORE_MARS, DIM_MARS, aWorld, aX, aZ));
			return;
		}
		if (BIOMES_ASTEROIDS.contains(aBiome.biomeName)) {
			generate(new WorldGenContainer(GEN_ASTEROIDS, ORE_ASTEROIDS, DIM_ASTEROIDS, aWorld, aX, aZ));
			return;
		}
		if (aGalactiCraft || BIOMES_SPACE.contains(aBiome.biomeName)) {
			generate(new WorldGenContainer(GEN_PLANETS, ORE_PLANETS, DIM_PLANETS, aWorld, aX, aZ));
			return;
		}
	}
	
	public static void generate(WorldGenContainer aWorldgen) {
		LIST.add(aWorldgen);
		if (!LOCK) {
			LOCK = T;
			while (!LIST.isEmpty()) LIST.remove(LIST.size()-1).run();
			LOCK = F;
		}
	}
}
