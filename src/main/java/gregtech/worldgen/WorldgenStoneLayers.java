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

import gregapi.block.metatype.BlockStones;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregapi.worldgen.StoneLayer;
import gregapi.worldgen.StoneLayerOres;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStoneLayers extends WorldgenObject {
	@SafeVarargs
	public WorldgenStoneLayers(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		GENERATE_STONE = mEnabled;
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (GENERATE_BIOMES && aDimType == DIM_OVERWORLD && aMinX >= -96 && aMinX <= 80 && aMinZ >= -96 && aMinZ <= 80) return F;
		
		final boolean tSlime = (aChunk.getRandomWithSeed(987234911L).nextInt(10) == 0);
		final NoiseGenerator tNoise = new NoiseGenerator(aWorld);
		final ExtendedBlockStorage[] aStorages = aChunk.getBlockStorageArray();
		final int tListSize = StoneLayer.LAYERS.size(), tMaxHeight = aChunk.getTopFilledSegment()+15;
		final StoneLayer[] tScan = new StoneLayer[9];
		
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
		
		for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
			final int tX = aMinX+i, tZ = aMinZ+j;
			final BiomeGenBase aBiome = aBiomes[i][j];
			
			if (StoneLayer.DEEPSLATE != null) {
				// The first Layers are Deepslate if possible.
				tScan[0] = StoneLayer.DEEPSLATE;
				tScan[1] = StoneLayer.DEEPSLATE;
				tScan[2] = StoneLayer.DEEPSLATE;
				tScan[3] = StoneLayer.DEEPSLATE;
				tScan[4] = StoneLayer.DEEPSLATE;
				tScan[5] = StoneLayer.DEEPSLATE;
				tScan[6] = StoneLayer.DEEPSLATE;
				tScan[7] = StoneLayer.DEEPSLATE;
				tScan[8] = (tSlime ? StoneLayer.DEEPSLATE : StoneLayer.LAYERS.get(tNoise.get(tX,  6, tZ, tListSize)));
			} else {
				tScan[0] = StoneLayer.LAYERS.get(tNoise.get(tX, -2, tZ, tListSize));
				tScan[1] = StoneLayer.LAYERS.get(tNoise.get(tX, -1, tZ, tListSize));
				tScan[2] = StoneLayer.LAYERS.get(tNoise.get(tX,  0, tZ, tListSize));
				tScan[3] = StoneLayer.LAYERS.get(tNoise.get(tX,  1, tZ, tListSize));
				tScan[4] = StoneLayer.LAYERS.get(tNoise.get(tX,  2, tZ, tListSize));
				tScan[5] = StoneLayer.LAYERS.get(tNoise.get(tX,  3, tZ, tListSize));
				tScan[6] = StoneLayer.LAYERS.get(tNoise.get(tX,  4, tZ, tListSize));
				tScan[7] = StoneLayer.LAYERS.get(tNoise.get(tX,  5, tZ, tListSize));
				tScan[8] = StoneLayer.LAYERS.get(tNoise.get(tX,  6, tZ, tListSize));
			}
			
			boolean tCanPlaceRocks = F;
			OreDictMaterial tLastRock = MT.Stone, tLastOre = null;
			
			for (int tY = 1; tY < tMaxHeight; tY++) {
				final ExtendedBlockStorage aStorage = aStorages[tY >> 4];
				final Block aBlock = (aStorage == null ? NB : aStorage.getBlockByExtId(i, tY & 15, j));
				assert aStorage != null;
				// Just mark as Opaque Ground.
				if (aBlock == Blocks.bedrock) {
					tCanPlaceRocks = T;
				// Place Rock if on Opaque Surface.
				} else if (aBlock == NB) {
					if (tCanPlaceRocks && aRandom.nextInt(128) == 0) tRegistry.mBlock.placeBlock(aWorld, tX, tY, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, OP.rockGt.mat(aRandom.nextBoolean()&&tLastOre!=null?tLastOre:tLastRock, 1)), F, T);
					tLastOre = null;
					tCanPlaceRocks = F;
				// Stone and Ore Generation in vanilla Stone.
				} else if (aBlock == Blocks.stone || (aBlock == Blocks.monster_egg && aStorage.getExtBlockMetadata(i, tY & 15, j) == 0)) {
					tCanPlaceRocks = T;
					boolean temp = T;
					if (tScan[5] == tScan[1]) {
						for (StoneLayerOres tOres : tScan[3].mOres) if (tOres.mMaterial.mID > 0 && tOres.check(tScan[3], aWorld, tX, tY, tZ, aBiome, aRandom) && (tScan[6] == tScan[0] ? tOres.normal(tScan[3], aWorld, tX, tY, tZ, aBiome) : tOres.small(tScan[3], aWorld, tX, tY, tZ, aBiome))) {
							tLastOre = tOres.mMaterial;
							temp = F;
							break;
						}
					} else {
						for (StoneLayerOres tOres : StoneLayer.get(tScan[5], tScan[1])) if (tOres.mMaterial.mID > 0 && tOres.check(tScan[3], aWorld, tX, tY, tZ, aBiome, aRandom) && tOres.set(tScan[3], aWorld, tX, tY, tZ, aBiome, aRandom)) {
							tLastOre = tOres.mMaterial;
							temp = F;
							break;
						}
					}
					if (temp && tScan[4] != tScan[2] && tScan[3].mOreSmall != null && !StoneLayer.RANDOM_SMALL_GEM_ORES.isEmpty() && aRandom.nextInt(100) == 0) {
						if (tScan[3].mOreSmall.placeBlock(aWorld, tX, tY, tZ, SIDE_UNKNOWN, UT.Code.select(MT.Emerald, StoneLayer.RANDOM_SMALL_GEM_ORES).mID, null, F, T)) {
							temp = F;
						}
					}
					if (temp) {
						tLastRock = tScan[3].mMaterial;
						if (aBlock != tScan[3].mStone) {
							aStorage.func_150818_a(i, tY & 15, j, tScan[3].mStone);
							aStorage.setExtBlockMetadata(i, tY & 15, j, tScan[3].mMetaStone);
						}
					}
				// Cobblestone Generation.
				} else if (aBlock == Blocks.cobblestone) {
					tCanPlaceRocks = T;
					if (tScan[3].mCobble != null) {
						tLastRock = tScan[3].mMaterial;
						if (aBlock != tScan[3].mCobble) {
							aStorage.func_150818_a(i, tY & 15, j, tScan[3].mCobble);
							aStorage.setExtBlockMetadata(i, tY & 15, j, tScan[3].mMetaCobble);
						}
					}
				// Mossy Cobblestone Generation.
				} else if (aBlock == Blocks.mossy_cobblestone) {
					tCanPlaceRocks = T;
					if (tScan[3].mMossy != null) {
						tLastRock = tScan[3].mMaterial;
						if (aBlock != tScan[3].mMossy) {
							aStorage.func_150818_a(i, tY & 15, j, tScan[3].mMossy);
							aStorage.setExtBlockMetadata(i, tY & 15, j, tScan[3].mMetaMossy);
						}
					}
				// Stone and Ore Generation in replaceable Blocks.
				} else if (StoneLayer.REPLACEABLE_BLOCKS.contains(aBlock)) {
					tCanPlaceRocks = T;
					boolean temp = T;
					if (tScan[5] == tScan[1]) {
						for (StoneLayerOres tOres : tScan[3].mOres) if (tOres.mMaterial.mID > 0 && tOres.check(tScan[3], aWorld, tX, tY, tZ, aBiome, aRandom) && (tScan[6] == tScan[0] ? tOres.normal(tScan[3], aWorld, tX, tY, tZ, aBiome) : tOres.small(tScan[3], aWorld, tX, tY, tZ, aBiome))) {
							tLastOre = tOres.mMaterial;
							temp = F;
							break;
						}
					} else {
						for (StoneLayerOres tOres : StoneLayer.get(tScan[5], tScan[1])) if (tOres.mMaterial.mID > 0 && tOres.check(tScan[3], aWorld, tX, tY, tZ, aBiome, aRandom) && tOres.set(tScan[3], aWorld, tX, tY, tZ, aBiome, aRandom)) {
							tLastOre = tOres.mMaterial;
							temp = F;
							break;
						}
					}
					if (temp && tScan[4] != tScan[2] && tScan[3].mOreSmall != null && !StoneLayer.RANDOM_SMALL_GEM_ORES.isEmpty() && aRandom.nextInt(100) == 0) {
						if (tScan[3].mOreSmall.placeBlock(aWorld, tX, tY, tZ, SIDE_UNKNOWN, UT.Code.select(MT.Emerald, StoneLayer.RANDOM_SMALL_GEM_ORES).mID, null, F, T)) {
							temp = F;
						}
					}
					if (temp) {
						tLastRock = tScan[3].mMaterial;
						if (aBlock != tScan[3].mStone) {
							aStorage.func_150818_a(i, tY & 15, j, tScan[3].mStone);
							aStorage.setExtBlockMetadata(i, tY & 15, j, tScan[3].mMetaStone);
						}
					}
				// Check for the GT6 Stone being natural. Unlikely case due to GT6 Stone being the thing that is supposed to generate this very moment and not before.
				} else if (aBlock instanceof BlockStones) {
					tCanPlaceRocks = (aStorage.getExtBlockMetadata(i, tY & 15, j) < 3);
				// Place Rock if on Opaque Surface.
				} else if (WD.easyRep(aWorld, tX, tY, tZ, aBlock)) {
					if (tCanPlaceRocks && !aBlock.getMaterial().isLiquid() && aRandom.nextInt(128) == 0) tRegistry.mBlock.placeBlock(aWorld, tX, tY, tZ, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, OP.rockGt.mat(aRandom.nextBoolean()&&tLastOre!=null?tLastOre:tLastRock, 1)), F, T);
					tLastOre = null;
					tCanPlaceRocks = F;
				// Just check if the last Block was Opaque and of the right kind of Material.
				} else {
					if (aBlock.isOpaqueCube()) {
						tCanPlaceRocks = (aBlock.getMaterial() == Material.clay || aBlock.getMaterial() == Material.sand || aBlock.getMaterial() == Material.grass || aBlock.getMaterial() == Material.ground);
					} else {
						tLastOre = null;
						tCanPlaceRocks = F;
					}
				}
				
				
				
				// And scan for next Block on the Stone Layer Type.
				for (int t = 1; t < tScan.length; t++) tScan[t-1] = tScan[t];
				tScan[tScan.length-1] = StoneLayer.LAYERS.get(tNoise.get(tX, tY-3+tScan.length, tZ, tListSize));
			}
		}
		return T;
	}
	
	@Override public boolean enabled(World aWorld, int aDimType) {return GENERATE_STONE;}
}
