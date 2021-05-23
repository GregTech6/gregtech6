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

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.IL;
import gregapi.util.ST;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import gregtech.blocks.fluids.BlockRiver;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenTrees;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenCenterBiomes extends WorldgenObject {
	public int mHeight = 66;
	
	@SafeVarargs
	public WorldgenCenterBiomes(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aLists);
		mHeight = ConfigsGT.WORLDGEN.get(mCategory, "Height", WD.waterLevel()+4);
		GENERATE_BIOMES = mEnabled;
	}
	
	@Override
	public boolean enabled(World aWorld, int aDimType) {
		return GENERATE_BIOMES && aWorld.provider.dimensionId == DIM_OVERWORLD;
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (aMinX >= -96 && aMinX <= 80 && aMinZ >= -96 && aMinZ <= 80) {
			if (GENERATE_STREETS && aMinX >= -32 && aMinX <= 16 && aMinZ >= -32 && aMinZ <= 16) {
				Arrays.fill(aChunk.getBiomeArray(), (byte)BiomeGenBase.river.biomeID);
				return T;
			}
			if (GENERATE_NEXUS && aMinX == 16 && aMinZ == -48) {
				Arrays.fill(aChunk.getBiomeArray(), (byte)BiomeGenBase.plains.biomeID);
				return T;
			}
			if (GENERATE_TESTING && (aMinX == 32 || aMinX == 48) && (aMinZ == -32 || aMinZ == -48)) {
				Arrays.fill(aChunk.getBiomeArray(), (byte)BiomeGenBase.plains.biomeID);
				return T;
			}
			if (aMinX == -16 || aMinX == 0 || aMinZ == -16 || aMinZ == 0) {
				Arrays.fill(aChunk.getBiomeArray(), (byte)BiomeGenBase.river.biomeID);
				BlockRiver.PLACEMENT_ALLOWED = T;
				for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
					for (int k = -3; k < 64; k++) WD.set(aChunk, i, mHeight+k, j, NB, 0);
					WD.set(aChunk, i, mHeight-4, j, BlocksGT.River, 0);
					WD.set(aChunk, i, mHeight-5, j, BlocksGT.River, 0);
					WD.set(aChunk, i, mHeight-6, j, BlocksGT.River, 0);
					WD.set(aChunk, i, mHeight-7, j, BlocksGT.Sands, 0);
					WD.set(aChunk, i, mHeight-8, j, Blocks.gravel, 1);
					WD.set(aChunk, i, mHeight-9, j, Blocks.clay, 0);
					WD.set(aChunk, i, mHeight-10, j, Blocks.clay, 0);
					for (int k = 1; k < mHeight-10; k++) WD.set(aChunk, i, k, j, Blocks.stone, 1);
				}
				BlockRiver.PLACEMENT_ALLOWED = F;
				aWorld.setSpawnLocation(0, mHeight+5, 0);
				return T;
			}
			if (aMinX < 0) {
				if (aMinZ < 0) {
					if ((aMinX == -80 || aMinX == -64) && (aMinZ == -80 || aMinZ == -64)) {
						Arrays.fill(aChunk.getBiomeArray(), (byte)BiomeGenBase.icePlains.biomeID);
						for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
							for (int k = 1; k < 64; k++) WD.set(aChunk, i, mHeight+k, j, NB, 0);
							WD.set(aChunk, i, mHeight  , j, Blocks.ice, 0);
							WD.set(aChunk, i, mHeight-1, j, Blocks.packed_ice, 0);
							WD.set(aChunk, i, mHeight-2, j, Blocks.packed_ice, 0);
							WD.set(aChunk, i, mHeight-3, j, Blocks.packed_ice, 0);
							WD.set(aChunk, i, mHeight-4, j, Blocks.packed_ice, 0);
							WD.set(aChunk, i, mHeight-5, j, Blocks.packed_ice, 0);
							for (int k = 1; k < mHeight-5; k++) WD.set(aChunk, i, k, j, k < 32 ? BlocksGT.SchistGreen : BlocksGT.SchistBlue, aRandom.nextBoolean()?2:0);
						}
					} else {
						Arrays.fill(aChunk.getBiomeArray(), (byte)BiomeGenBase.coldTaiga.biomeID);
						for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
							for (int k = 2; k < 64; k++) WD.set(aChunk, i, mHeight+k, j, NB, 0);
							WD.set(aChunk, i, mHeight+1, j, Blocks.snow_layer, aRandom.nextInt(2));
							WD.set(aChunk, i, mHeight  , j, Blocks.dirt, 2);
							WD.set(aChunk, i, mHeight-1, j, Blocks.dirt, 2);
							WD.set(aChunk, i, mHeight-2, j, Blocks.dirt, 2);
							WD.set(aChunk, i, mHeight-3, j, Blocks.dirt, 2);
							WD.set(aChunk, i, mHeight-4, j, Blocks.dirt, 2);
							WD.set(aChunk, i, mHeight-5, j, Blocks.dirt, 2);
							for (int k = 1; k < mHeight-5; k++) WD.set(aChunk, i, k, j, Blocks.mossy_cobblestone, 0);
						}
						
						WD.set(aChunk,  4, mHeight+1,  4, NB, aRandom.nextInt(2));
						WD.set(aChunk, 12, mHeight+1,  4, NB, aRandom.nextInt(2));
						WD.set(aChunk,  4, mHeight+1, 12, NB, aRandom.nextInt(2));
						WD.set(aChunk, 12, mHeight+1, 12, NB, aRandom.nextInt(2));
						
						new WorldGenTrees(F, 4+aRandom.nextInt(3), 1, 1, F).generate(aWorld, aRandom, aMinX+ 4, mHeight+1, aMinZ+ 4);
						new WorldGenTrees(F, 4+aRandom.nextInt(3), 1, 1, F).generate(aWorld, aRandom, aMinX+12, mHeight+1, aMinZ+ 4);
						new WorldGenTrees(F, 4+aRandom.nextInt(3), 1, 1, F).generate(aWorld, aRandom, aMinX+ 4, mHeight+1, aMinZ+12);
						new WorldGenTrees(F, 4+aRandom.nextInt(3), 1, 1, F).generate(aWorld, aRandom, aMinX+12, mHeight+1, aMinZ+12);
					}
				} else {
					if ((aMinX == -80 || aMinX == -64) && (aMinZ == 48 || aMinZ == 64)) {
						Arrays.fill(aChunk.getBiomeArray(), (byte)BiomeGenBase.forest.biomeID);
						for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
							for (int k = 1; k < 64; k++) WD.set(aChunk, i, mHeight+k, j, NB, 0);
							WD.set(aChunk, i, mHeight  , j, Blocks.grass, 0);
							WD.set(aChunk, i, mHeight-1, j, Blocks.dirt, 0);
							WD.set(aChunk, i, mHeight-2, j, Blocks.dirt, 0);
							WD.set(aChunk, i, mHeight-3, j, Blocks.dirt, 0);
							WD.set(aChunk, i, mHeight-4, j, Blocks.dirt, 0);
							WD.set(aChunk, i, mHeight-5, j, Blocks.dirt, 0);
							for (int k = 1; k < mHeight-5; k++) WD.set(aChunk, i, k, j, k < 32 ? BlocksGT.Kimberlite : BlocksGT.Quartzite, aRandom.nextBoolean()?2:0);
						}
						WD.set(aChunk,  6, mHeight+1,  6, Blocks.pumpkin, 0);
						WD.set(aChunk, 10, mHeight+1,  6, Blocks.pumpkin, 0);
						WD.set(aChunk,  6, mHeight+1, 10, Blocks.pumpkin, 0);
						WD.set(aChunk, 10, mHeight+1, 10, Blocks.pumpkin, 0);
						
						new WorldGenTrees(F, 4+aRandom.nextInt(3), 0, 0, F).generate(aWorld, aRandom, aMinX+ 4, mHeight+1, aMinZ+ 4);
						new WorldGenTrees(F, 4+aRandom.nextInt(3), 2, 2, F).generate(aWorld, aRandom, aMinX+12, mHeight+1, aMinZ+ 4);
						new WorldGenTrees(F, 4+aRandom.nextInt(3), 2, 2, F).generate(aWorld, aRandom, aMinX+ 4, mHeight+1, aMinZ+12);
						new WorldGenTrees(F, 4+aRandom.nextInt(3), 0, 0, F).generate(aWorld, aRandom, aMinX+12, mHeight+1, aMinZ+12);
					} else {
						MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
						Arrays.fill(aChunk.getBiomeArray(), (byte)BiomeGenBase.plains.biomeID);
						for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
							for (int k = 1; k < 64; k++) WD.set(aChunk, i, mHeight+k, j, NB, 0);
							WD.set(aChunk, i, mHeight  , j, Blocks.grass, 0);
							WD.set(aChunk, i, mHeight-1, j, Blocks.dirt, 0);
							WD.set(aChunk, i, mHeight-2, j, BlocksGT.Diggables, 1);
							WD.set(aChunk, i, mHeight-3, j, BlocksGT.Diggables, 3);
							WD.set(aChunk, i, mHeight-4, j, BlocksGT.Diggables, 4);
							WD.set(aChunk, i, mHeight-5, j, BlocksGT.Diggables, 5);
							WD.set(aChunk, i, mHeight-6, j, BlocksGT.Diggables, 6);
							for (int k = 1; k < mHeight-6; k++) WD.set(aChunk, i, k, j, k < 32 ? BlocksGT.Limestone : BlocksGT.Marble, aRandom.nextBoolean()?2:0);
							switch(aRandom.nextInt(60)) {
							case  0: case  1: case  2: if (tRegistry != null) tRegistry.mBlock.placeBlock(aWorld, aMinX+i, mHeight+1, aMinZ+j, SIDE_UNKNOWN, (short)32757, ST.save(NBT_VALUE, ST.make(Items.flint, 1, 0)), F, T); break;
							case  3: case  4: case  5: if (tRegistry != null) tRegistry.mBlock.placeBlock(aWorld, aMinX+i, mHeight+1, aMinZ+j, SIDE_UNKNOWN, (short)32757, null, F, T); break;
							case  6: case  7: case  8: if (tRegistry != null) tRegistry.mBlock.placeBlock(aWorld, aMinX+i, mHeight+1, aMinZ+j, SIDE_UNKNOWN, (short)32756, null, F, T); break;
							case  9: case 10: case 11: case 12: case 13: case 14: WD.set(aChunk, i, mHeight+1, j, Blocks.tallgrass, 1); break;
							case 15: WD.set(aChunk, i, mHeight+1, j, Blocks.yellow_flower, 0); break;
							case 16: WD.set(aChunk, i, mHeight+1, j, Blocks.red_flower, 0); break;
							case 17: WD.set(aChunk, i, mHeight+1, j, Blocks.red_flower, 1); break;
							case 18: WD.set(aChunk, i, mHeight+1, j, Blocks.red_flower, 2); break;
							case 19: WD.set(aChunk, i, mHeight+1, j, Blocks.red_flower, 3); break;
							case 20: WD.set(aChunk, i, mHeight+1, j, Blocks.red_flower, 4); break;
							case 21: WD.set(aChunk, i, mHeight+1, j, Blocks.red_flower, 5); break;
							case 22: WD.set(aChunk, i, mHeight+1, j, Blocks.red_flower, 6); break;
							case 23: WD.set(aChunk, i, mHeight+1, j, Blocks.red_flower, 7); break;
							case 24: WD.set(aChunk, i, mHeight+1, j, Blocks.red_flower, 8); break;
							}
						}
					}
				}
			} else {
				if (aMinZ < 0) {
					if ((aMinX == 48 || aMinX == 64) && (aMinZ == -80 || aMinZ == -64)) {
						Arrays.fill(aChunk.getBiomeArray(), (byte)BiomeGenBase.mesa.biomeID);
						for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
							for (int k = 1; k < 64; k++) WD.set(aChunk, i, mHeight+k, j, NB, 0);
							WD.set(aChunk, i, mHeight  , j, Blocks.sand, 1);
							WD.set(aChunk, i, mHeight-1, j, Blocks.sand, 1);
							WD.set(aChunk, i, mHeight-2, j, Blocks.sand, 1);
							WD.set(aChunk, i, mHeight-3, j, Blocks.sand, 1);
							WD.set(aChunk, i, mHeight-4, j, Blocks.sand, 1);
							WD.set(aChunk, i, mHeight-5, j, Blocks.sand, 1);
							for (int k = 1; k < mHeight-5; k++) WD.set(aChunk, i, k, j, Blocks.hardened_clay, 0);
						}
						for (int i = 1; i <= 3; i++) {
							WD.set(aChunk,  4, mHeight+i,  4, Blocks.cactus, 0);
							WD.set(aChunk, 12, mHeight+i,  4, Blocks.cactus, 0);
							WD.set(aChunk,  4, mHeight+i, 12, Blocks.cactus, 0);
							WD.set(aChunk, 12, mHeight+i, 12, Blocks.cactus, 0);
						}
					} else {
						Arrays.fill(aChunk.getBiomeArray(), (byte)BiomeGenBase.desert.biomeID);
						for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
							for (int k = 1; k < 64; k++) WD.set(aChunk, i, mHeight+k, j, NB, 0);
							WD.set(aChunk, i, mHeight  , j, Blocks.sand, 0);
							WD.set(aChunk, i, mHeight-1, j, Blocks.sand, 0);
							WD.set(aChunk, i, mHeight-2, j, Blocks.sand, 0);
							WD.set(aChunk, i, mHeight-3, j, Blocks.sand, 0);
							WD.set(aChunk, i, mHeight-4, j, Blocks.sand, 0);
							WD.set(aChunk, i, mHeight-5, j, Blocks.sand, 0);
							for (int k = 1; k < mHeight-5; k++) WD.set(aChunk, i, k, j, Blocks.sandstone, 0);
						}
					}
				} else {
					if ((aMinX == 48 || aMinX == 64) && (aMinZ == 48 || aMinZ == 64)) {
						Arrays.fill(aChunk.getBiomeArray(), (byte)BiomeGenBase.swampland.biomeID);
						for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
							for (int k = 1; k < 64; k++) WD.set(aChunk, i, mHeight+k, j, NB, 0);
							WD.set(aChunk, i, mHeight  , j, Blocks.water, 0);
							WD.set(aChunk, i, mHeight-1, j, BlocksGT.Diggables, 0);
							WD.set(aChunk, i, mHeight-2, j, BlocksGT.Diggables, 0);
							WD.set(aChunk, i, mHeight-3, j, BlocksGT.Diggables, 2);
							WD.set(aChunk, i, mHeight-4, j, BlocksGT.Diggables, 2);
							WD.set(aChunk, i, mHeight-5, j, BlocksGT.Diggables, 2);
							for (int k = 1; k < mHeight-5; k++) WD.set(aChunk, i, k, j, k < 32 ? BlocksGT.GraniteRed : BlocksGT.GraniteBlack, aRandom.nextBoolean()?2:0);
							if (aRandom.nextInt(8) == 0) WD.set(aChunk, i, mHeight+1, j, BlocksGT.Glowtus, aRandom.nextInt(16));
						}
						WD.set(aChunk,  4, mHeight+1,  4, Blocks.waterlily, 0);
						WD.set(aChunk, 12, mHeight+1,  4, Blocks.waterlily, 0);
						WD.set(aChunk,  4, mHeight+1, 12, Blocks.waterlily, 0);
						WD.set(aChunk, 12, mHeight+1, 12, Blocks.waterlily, 0);
					} else {
						Arrays.fill(aChunk.getBiomeArray(), (byte)BiomeGenBase.jungle.biomeID);
						if (IL.EtFu_Dirt.exists()) {
							Block tBlock = IL.EtFu_Dirt.block();
							for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
								for (int k = 1; k < 64; k++) WD.set(aChunk, i, mHeight+k, j, NB, 0);
								WD.set(aChunk, i, mHeight  , j, Blocks.grass, 0);
								WD.set(aChunk, i, mHeight-1, j, tBlock, 0);
								WD.set(aChunk, i, mHeight-2, j, tBlock, 0);
								WD.set(aChunk, i, mHeight-3, j, tBlock, 0);
								WD.set(aChunk, i, mHeight-4, j, tBlock, 0);
								WD.set(aChunk, i, mHeight-5, j, tBlock, 0);
								for (int k = 1; k < mHeight-5; k++) WD.set(aChunk, i, k, j, k < 32 ? BlocksGT.Komatiite : BlocksGT.Basalt, aRandom.nextBoolean()?2:0);
							}
						} else {
							for (int i = 0; i < 16; i++) for (int j = 0; j < 16; j++) {
								for (int k = 1; k < 64; k++) WD.set(aChunk, i, mHeight+k, j, NB, 0);
								WD.set(aChunk, i, mHeight  , j, Blocks.grass, 0);
								WD.set(aChunk, i, mHeight-1, j, Blocks.dirt, 1);
								WD.set(aChunk, i, mHeight-2, j, Blocks.dirt, 1);
								WD.set(aChunk, i, mHeight-3, j, Blocks.dirt, 1);
								WD.set(aChunk, i, mHeight-4, j, Blocks.dirt, 1);
								WD.set(aChunk, i, mHeight-5, j, Blocks.dirt, 1);
								for (int k = 1; k < mHeight-5; k++) WD.set(aChunk, i, k, j, k < 32 ? BlocksGT.Komatiite : BlocksGT.Basalt, aRandom.nextBoolean()?2:0);
							}
						}
						WD.set(aChunk, 6+aRandom.nextInt(4), mHeight+1, 6+aRandom.nextInt(4), Blocks.melon_block, 0);
						
						new WorldGenTrees(F, 9+aRandom.nextInt(3), 3, 3, T).generate(aWorld, aRandom, aMinX+ 4, mHeight+1, aMinZ+ 4);
						new WorldGenTrees(F, 9+aRandom.nextInt(3), 3, 3, T).generate(aWorld, aRandom, aMinX+12, mHeight+1, aMinZ+ 4);
						new WorldGenTrees(F, 9+aRandom.nextInt(3), 3, 3, T).generate(aWorld, aRandom, aMinX+ 4, mHeight+1, aMinZ+12);
						new WorldGenTrees(F, 9+aRandom.nextInt(3), 3, 3, T).generate(aWorld, aRandom, aMinX+12, mHeight+1, aMinZ+12);
					}
				}
			}
			return T;
		}
		return F;
	}
}
