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

import gregapi.block.metatype.BlockMetaType;
import gregapi.code.HashSetNoNulls;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.util.UT;
import gregapi.util.WD;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStreets extends WorldgenObject {
	public int mHeight = 66;
	
	@SafeVarargs
	public WorldgenStreets(String aName, boolean aDefault, List<WorldgenObject>... aLists) {
		super(aName, ConfigsGT.WORLDGEN.get("worldgenerator.streets", "Enabled", aDefault), aLists);
		mHeight = getConfigFile().get(mCategory, "Height", getConfigFile().get("worldgenerator.streets", "Height", WD.waterLevel()+4));
		GENERATE_STREETS = mEnabled;
	}
	
	@Override
	public boolean enabled(World aWorld, int aDimType) {
		return GENERATE_STREETS && aWorld.provider.dimensionId == DIM_OVERWORLD;
	}
	
	@Override
	public boolean generate(World aWorld, Chunk aChunk, int aDimType, int aMinX, int aMinZ, int aMaxX, int aMaxZ, Random aRandom, BiomeGenBase[][] aBiomes, Set<String> aBiomeNames) {
		if (aMinX == -16 || aMinX == 0) {
			if (aMinZ == -16 || aMinZ == 0) {
				for (int i = -32; i < 32; i++) for (int j = -32; j < 32; j++) {
					for (int k = 2; k < 64; k++) aWorld.setBlock(i, mHeight+k, j, NB, 0, 0);
					for (int k = 1; k < mHeight; k++) aWorld.setBlock(i, k, j, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
					
					aWorld.setBlock(i, mHeight-1, j, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
					if (i <= -29 || j <= -29 || i >= 28 || j >= 28) {
						if (UT.Code.inside(-12, 11, i) || UT.Code.inside(-12, 11, j)) {
							aWorld.setBlock(i, mHeight  , j, BlocksGT.Asphalt, i == -31 || j == -31 || i == 30 || j == 30 ? DYE_INDEX_White : DYE_INDEX_Gray, 0);
							aWorld.setBlock(i, mHeight+1, j, NB, 0, 0);
						} else {
							aWorld.setBlock(i, mHeight  , j, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
							if (!GENERATE_BIOMES && (i == -32 || j == -32 || i == 31 || j == 31) && (!GENERATE_NEXUS || i < 0 || i == 31 || j != -32) && (!GENERATE_TESTING || j > 0 || i != 31 || j == -32)) {
								aWorld.setBlock(i, mHeight+1, j, WD.even(i, 1, j)?BlocksGT.CFoam:BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
								aWorld.setBlock(i, mHeight+2, j, WD.even(i, 2, j)?BlocksGT.CFoam:BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
								aWorld.setBlock(i, mHeight+3, j, WD.even(i, 3, j)?BlocksGT.CFoam:BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
								aWorld.setBlock(i, mHeight+4, j, WD.even(i, 4, j)?BlocksGT.CFoam:BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
								aWorld.setBlock(i, mHeight+5, j, WD.even(i, 5, j)?BlocksGT.CFoam:BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
							} else {
								aWorld.setBlock(i, mHeight+1, j, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Y_NEG], DYE_INDEX_LightGray, 0);
							}
						}
					} else {
						if (UT.Code.inside(-12, 11, i) && UT.Code.inside(-12, 11, j)) {
							aWorld.setBlock(i, mHeight  , j, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
							aWorld.setBlock(i, mHeight+1, j, UT.Code.inside(-11, 10, i) && UT.Code.inside(-11, 10, j) ? BlocksGT.Concrete : BlocksGT.CFoam, DYE_INDEX_LightGray, 0);
						} else {
							aWorld.setBlock(i, mHeight  , j, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
							aWorld.setBlock(i, mHeight+1, j, NB, 0, 0);
						}
					}
				}
				
				aWorld.setBlock(-32, mHeight+1, - 1, BlocksGT.Concrete, DYE_INDEX_Blue, 0);
				aWorld.setBlock(-31, mHeight+1, - 1, BlocksGT.Concrete, DYE_INDEX_Blue, 0);
				aWorld.setBlock(-32, mHeight+1,   0, BlocksGT.Concrete, DYE_INDEX_Blue, 0);
				aWorld.setBlock(-31, mHeight+1,   0, BlocksGT.Concrete, DYE_INDEX_Blue, 0);
				aWorld.setBlock(-32, mHeight+1, - 2, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_White, 0);
				aWorld.setBlock(-31, mHeight+1, - 2, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_Red, 0);
				aWorld.setBlock(-30, mHeight+1, - 1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_White, 0);
				aWorld.setBlock(-30, mHeight+1,   0, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_Red, 0);
				aWorld.setBlock(-31, mHeight+1,   1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_White, 0);
				aWorld.setBlock(-32, mHeight+1,   1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_Red, 0);
				
				aWorld.setBlock( 31, mHeight+1, - 1, BlocksGT.Concrete, DYE_INDEX_Red, 0);
				aWorld.setBlock( 30, mHeight+1, - 1, BlocksGT.Concrete, DYE_INDEX_Red, 0);
				aWorld.setBlock( 31, mHeight+1,   0, BlocksGT.Concrete, DYE_INDEX_Red, 0);
				aWorld.setBlock( 30, mHeight+1,   0, BlocksGT.Concrete, DYE_INDEX_Red, 0);
				aWorld.setBlock( 31, mHeight+1, - 2, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_Red, 0);
				aWorld.setBlock( 30, mHeight+1, - 2, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_White, 0);
				aWorld.setBlock( 29, mHeight+1, - 1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_Red, 0);
				aWorld.setBlock( 29, mHeight+1,   0, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_White, 0);
				aWorld.setBlock( 30, mHeight+1,   1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_Red, 0);
				aWorld.setBlock( 31, mHeight+1,   1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_White, 0);
				
				aWorld.setBlock(- 1, mHeight+1, -32, BlocksGT.Concrete, DYE_INDEX_Yellow, 0);
				aWorld.setBlock(- 1, mHeight+1, -31, BlocksGT.Concrete, DYE_INDEX_Yellow, 0);
				aWorld.setBlock(  0, mHeight+1, -32, BlocksGT.Concrete, DYE_INDEX_Yellow, 0);
				aWorld.setBlock(  0, mHeight+1, -31, BlocksGT.Concrete, DYE_INDEX_Yellow, 0);
				aWorld.setBlock(- 2, mHeight+1, -32, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_White, 0);
				aWorld.setBlock(- 2, mHeight+1, -31, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_Red, 0);
				aWorld.setBlock(- 1, mHeight+1, -30, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_White, 0);
				aWorld.setBlock(  0, mHeight+1, -30, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_Red, 0);
				aWorld.setBlock(  1, mHeight+1, -31, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_White, 0);
				aWorld.setBlock(  1, mHeight+1, -32, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_Red, 0);
				
				aWorld.setBlock(- 1, mHeight+1,  31, BlocksGT.Concrete, DYE_INDEX_Green, 0);
				aWorld.setBlock(- 1, mHeight+1,  30, BlocksGT.Concrete, DYE_INDEX_Green, 0);
				aWorld.setBlock(  0, mHeight+1,  31, BlocksGT.Concrete, DYE_INDEX_Green, 0);
				aWorld.setBlock(  0, mHeight+1,  30, BlocksGT.Concrete, DYE_INDEX_Green, 0);
				aWorld.setBlock(- 2, mHeight+1,  31, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_Red, 0);
				aWorld.setBlock(- 2, mHeight+1,  30, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_White, 0);
				aWorld.setBlock(- 1, mHeight+1,  29, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_Red, 0);
				aWorld.setBlock(  0, mHeight+1,  29, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_White, 0);
				aWorld.setBlock(  1, mHeight+1,  30, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_Red, 0);
				aWorld.setBlock(  1, mHeight+1,  31, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_White, 0);
				
				if (GENERATE_BEACON) {
					for (int i = -5; i < 5; i++) for (int j = -5; j < 5; j++) WD.set(aWorld, i, mHeight-3, j, Blocks.iron_block, 0, 0);
					for (int i = -4; i < 4; i++) for (int j = -4; j < 4; j++) WD.set(aWorld, i, mHeight-2, j, Blocks.iron_block, 0, 0);
					for (int i = -3; i < 3; i++) for (int j = -3; j < 3; j++) WD.set(aWorld, i, mHeight-1, j, Blocks.iron_block, 0, 0);
					for (int i = -2; i < 2; i++) for (int j = -2; j < 2; j++) WD.set(aWorld, i, mHeight  , j, Blocks.iron_block, 0, 0);
					
					TileEntity tTileEntity;
					
					WD.set(aWorld, -1, mHeight+1, -1, Blocks.beacon, 0, 3);
					tTileEntity = WD.te(aWorld, -1, mHeight+1, -1, T);
					if (tTileEntity instanceof TileEntityBeacon) {
						NBTTagCompound tNBT = UT.NBT.make();
						tTileEntity.writeToNBT(tNBT);
						tNBT.setInteger("Primary", Potion.moveSpeed.id);
						tNBT.setInteger("Secondary", Potion.moveSpeed.id);
						tNBT.setInteger("Levels", 4);
						tTileEntity.readFromNBT(tNBT);
					}
					
					WD.set(aWorld, -1, mHeight+1, 0, Blocks.beacon, 0, 3);
					tTileEntity = WD.te(aWorld, -1, mHeight+1, 0, T);
					if (tTileEntity instanceof TileEntityBeacon) {
						NBTTagCompound tNBT = UT.NBT.make();
						tTileEntity.writeToNBT(tNBT);
						tNBT.setInteger("Primary", Potion.digSpeed.id);
						tNBT.setInteger("Secondary", Potion.digSpeed.id);
						tNBT.setInteger("Levels", 4);
						tTileEntity.readFromNBT(tNBT);
					}
					
					WD.set(aWorld, 0, mHeight+1, -1, Blocks.beacon, 0, 3);
					tTileEntity = WD.te(aWorld, 0, mHeight+1, -1, T);
					if (tTileEntity instanceof TileEntityBeacon) {
						NBTTagCompound tNBT = UT.NBT.make();
						tTileEntity.writeToNBT(tNBT);
						tNBT.setInteger("Primary", Potion.damageBoost.id);
						tNBT.setInteger("Secondary", Potion.damageBoost.id);
						tNBT.setInteger("Levels", 4);
						tTileEntity.readFromNBT(tNBT);
					}
					
					WD.set(aWorld, 0, mHeight+1, 0, Blocks.beacon, 0, 3);
					tTileEntity = WD.te(aWorld, 0, mHeight+1, 0, T);
					if (tTileEntity instanceof TileEntityBeacon) {
						NBTTagCompound tNBT = UT.NBT.make();
						tTileEntity.writeToNBT(tNBT);
						tNBT.setInteger("Primary", Potion.resistance.id);
						tNBT.setInteger("Secondary", Potion.regeneration.id);
						tNBT.setInteger("Levels", 4);
						tTileEntity.readFromNBT(tNBT);
					}
				}
				
				aWorld.setSpawnLocation(0, mHeight+5, 0);
				return T;
			}
			if (aMinZ < -96 || aMinZ > 80) {
				aWorld.setBlock(aMinX-32, 255, aMinZ-32, NB, 0, 0);
				aWorld.setBlock(aMinX-32, 255, aMinZ+32, NB, 0, 0);
				aWorld.setBlock(aMinX+32, 255, aMinZ-32, NB, 0, 0);
				aWorld.setBlock(aMinX+32, 255, aMinZ+32, NB, 0, 0);
				
				for (int tOpaqueCount = 0, i = -16; i < 16; i++) for (int j = 0; j < 16; j++) {
					Block tBlock = aWorld.getBlock(i, mHeight+9, aMinZ+j);
					if (tBlock.getMaterial().isLiquid() || WD.anywater(tBlock) || (WD.opq(tBlock) && tBlock.getMaterial() != Material.wood && !tBlock.isWood(aWorld, i, mHeight+9, aMinZ+j) && !tBlock.isLeaves(aWorld, i, mHeight+9, aMinZ+j))) {
						if (tOpaqueCount++ > 128) {
							return generateRoadX(aWorld, aMinZ, F, F, T, F, F);
						}
					}
				}
				aBiomeNames = new HashSetNoNulls<>(aBiomeNames);
				for (int i = aMinZ; i <= aMaxZ; i++) for (int j = (aMinZ < 0 ? 0 : -16), k = (aMinZ < 0 ? 16 : 0); j < k; j++) {
					BiomeGenBase tBiome = aWorld.getBiomeGenForCoords(j, i);
					if (tBiome != null) aBiomeNames.add(tBiome.biomeName);
				}
				for (String tName : aBiomeNames) if (BIOMES_INFINITE_WATER.contains(tName)) {
					return generateRoadX(aWorld, aMinZ, F, T, F, T, T);
				}
				return generateRoadX(aWorld, aMinZ, T, T, F, F, T);
			}
			return aMinZ != -32 && aMinZ != 16 && generateRoadX(aWorld, aMinZ, F, F, F, T, !GENERATE_BIOMES);
		}
		if (aMinZ == -16 || aMinZ == 0) {
			if (aMinX < -96 || aMinX > 80) {
				aWorld.setBlock(aMinX-32, 255, aMinZ-32, NB, 0, 0);
				aWorld.setBlock(aMinX-32, 255, aMinZ+32, NB, 0, 0);
				aWorld.setBlock(aMinX+32, 255, aMinZ-32, NB, 0, 0);
				aWorld.setBlock(aMinX+32, 255, aMinZ+32, NB, 0, 0);
				
				for (int tOpaqueCount = 0, i = -16; i < 16; i++) for (int j = 0; j < 16; j++) {
					Block tBlock = aWorld.getBlock(aMinX+j, mHeight+9, i);
					if (tBlock.getMaterial().isLiquid() || WD.anywater(tBlock) || (WD.opq(tBlock) && tBlock.getMaterial() != Material.wood && !tBlock.isWood(aWorld, aMinX+j, mHeight+9, i) && !tBlock.isLeaves(aWorld, aMinX+j, mHeight+9, i))) {
						if (tOpaqueCount++ > 128) {
							return generateRoadZ(aWorld, aMinX, F, F, T, F, F);
						}
					}
				}
				aBiomeNames = new HashSetNoNulls<>(aBiomeNames);
				for (int i = aMinX; i <= aMaxX; i++) for (int j = (aMinZ < 0 ? 0 : -16), k = (aMinZ < 0 ? 16 : 0); j < k; j++) {
					BiomeGenBase tBiome = aWorld.getBiomeGenForCoords(i, j);
					if (tBiome != null) aBiomeNames.add(tBiome.biomeName);
				}
				for (String tName : aBiomeNames) if (BIOMES_INFINITE_WATER.contains(tName)) {
					return generateRoadZ(aWorld, aMinX, F, T, F, T, T);
				}
				return generateRoadZ(aWorld, aMinX, T, T, F, F, T);
			}
			return aMinX != -32 && aMinX != 16 && generateRoadZ(aWorld, aMinX, F, F, F, T, !GENERATE_BIOMES);
		}
		return F;
	}
	
	@SuppressWarnings("unchecked")
	public final boolean generateRoadX(World aWorld, int aMinZ, boolean aLand, boolean aKillSky, boolean aTunnel, boolean aBridge, boolean aSideWalls) {
		for (int i = 0; i < 16; i++) {
			if (aLand) {
				for (int j = mHeight+1; j > 0; j--) if (!WD.opq(aWorld, -13, j, aMinZ+i, T, T)) WD.set(aWorld, -13, j, aMinZ+i, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight+1; j > 0; j--) if (!WD.opq(aWorld,  12, j, aMinZ+i, T, T)) WD.set(aWorld,  12, j, aMinZ+i, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight  ; j > 0; j--) if (!WD.opq(aWorld, -14, j, aMinZ+i, T, T)) WD.set(aWorld, -14, j, aMinZ+i, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight  ; j > 0; j--) if (!WD.opq(aWorld,  13, j, aMinZ+i, T, T)) WD.set(aWorld,  13, j, aMinZ+i, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight-1; j > 0; j--) if (!WD.opq(aWorld, -15, j, aMinZ+i, T, T)) WD.set(aWorld, -15, j, aMinZ+i, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight-1; j > 0; j--) if (!WD.opq(aWorld,  14, j, aMinZ+i, T, T)) WD.set(aWorld,  14, j, aMinZ+i, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight-2; j > 0; j--) if (!WD.opq(aWorld, -16, j, aMinZ+i, T, T)) WD.set(aWorld, -16, j, aMinZ+i, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight-2; j > 0; j--) if (!WD.opq(aWorld,  15, j, aMinZ+i, T, T)) WD.set(aWorld,  15, j, aMinZ+i, Blocks.gravel, 1, 0, T); else break;
			}
			if (aTunnel) {
				for (int j = -12; j < 12; j++)
				aWorld.setBlock(  j, mHeight+7, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_White, 0);
				for (int j = 0; j < 7; j++) {
				aWorld.setBlock(-13, mHeight+j, aMinZ+i, BlocksGT.Concrete, j == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
				aWorld.setBlock( 12, mHeight+j, aMinZ+i, BlocksGT.Concrete, j == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
				}
			}
			if (aBridge) {
				WD.set(aWorld, -13, mHeight  , aMinZ+i, BlocksGT.Concrete, DYE_INDEX_Gray, 0, T);
				WD.set(aWorld,  12, mHeight  , aMinZ+i, BlocksGT.Concrete, DYE_INDEX_Gray, 0, T);
				aWorld.setBlock(-13, mHeight+1, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock( 12, mHeight+1, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
			}
			if (aKillSky) {
				for (int j = -13; j < 13; j++) for (int k = 2; k < 64; k++) aWorld.setBlock(j, mHeight+k, aMinZ+i, NB, 0, 0);
			} else {
				for (int j = -12; j < 12; j++) for (int k = 2; k <  7; k++) aWorld.setBlock(j, mHeight+k, aMinZ+i, NB, 0, 0);
			}
			for (int j = -12; j < 2; j++) {
				aWorld.setBlock(j, mHeight+1, aMinZ+i, NB, 0, 0);
				if (aLand) {
				WD.set(aWorld, j, mHeight-2, aMinZ+i, Blocks.cobblestone, 0, 0, T);
				aWorld.setBlock(j, mHeight-1, aMinZ+i, Blocks.gravel, 1, 0);
				for (int k = mHeight-3; k > 0; k--) if (!WD.opq(aWorld, j, k, aMinZ+i, T, T)) WD.set(aWorld, j, k, aMinZ+i, Blocks.cobblestone, 0, 0, T); else break;
				} else {
				WD.set(aWorld, j, mHeight-1, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_Gray, 0, T);
				}
			}
			for (int j = 1; j < 12; j++) {
				aWorld.setBlock(j, mHeight+1, aMinZ+i, NB, 0, 0);
				if (aLand) {
				WD.set(aWorld, j, mHeight-2, aMinZ+i, Blocks.cobblestone, 0, 0, T);
				aWorld.setBlock(j, mHeight-1, aMinZ+i, Blocks.gravel, 1, 0);
				for (int k = mHeight-3; k > 0; k--) if (!WD.opq(aWorld, j, k, aMinZ+i, T, T)) WD.set(aWorld, j, k, aMinZ+i, Blocks.cobblestone, 0, 0, T); else break;
				} else {
				WD.set(aWorld, j, mHeight-1, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_Gray, 0, T);
				}
			}
			
			aWorld.setBlock(-12, mHeight+1, aMinZ+i, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_LightGray, 0);
			aWorld.setBlock(-12, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(-11, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_White, 0);
			aWorld.setBlock(-10, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(- 9, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(- 8, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(- 7, mHeight  , aMinZ+i, BlocksGT.Asphalt, ((i+2) / 4) % 2 == 0 ? DYE_INDEX_Gray : DYE_INDEX_White, 0);
			aWorld.setBlock(- 6, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(- 5, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(- 4, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(- 3, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_White, 0);
			aWorld.setBlock(- 2, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(- 2, mHeight+1, aMinZ+i, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_LightGray, 0);
			
			if (aLand) {
			WD.set(aWorld, - 1, mHeight-1, aMinZ+i, Blocks.cobblestone, 0, 0, T);
			WD.set(aWorld, - 1, mHeight  , aMinZ+i, Blocks.gravel, 1, 0, F);
			WD.set(aWorld, - 1, mHeight+1, aMinZ+i, Blocks.gravel, 1, 0, F);
			WD.set(aWorld,   0, mHeight-1, aMinZ+i, Blocks.cobblestone, 0, 0, T);
			WD.set(aWorld,   0, mHeight  , aMinZ+i, Blocks.gravel, 1, 0, F);
			WD.set(aWorld,   0, mHeight+1, aMinZ+i, Blocks.gravel, 1, 0, F);
			for (int j = mHeight-2; j > 0; j--) if (!WD.opq(aWorld, - 1, j, aMinZ+i, T, T)) WD.set(aWorld, - 1, j, aMinZ+i, Blocks.cobblestone, 0, 0, T); else break;
			for (int j = mHeight-2; j > 0; j--) if (!WD.opq(aWorld,   0, j, aMinZ+i, T, T)) WD.set(aWorld,   0, j, aMinZ+i, Blocks.cobblestone, 0, 0, T); else break;
			} else {
			WD.set(aWorld, - 1, mHeight-1, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_Gray, 0, T);
			WD.set(aWorld, - 1, mHeight  , aMinZ+i, BlocksGT.Concrete, DYE_INDEX_Gray, 0, F);
			WD.set(aWorld, - 1, mHeight+1, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_Gray, 0, F);
			WD.set(aWorld,   0, mHeight-1, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_Gray, 0, T);
			WD.set(aWorld,   0, mHeight  , aMinZ+i, BlocksGT.Concrete, DYE_INDEX_Gray, 0, F);
			WD.set(aWorld,   0, mHeight+1, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_Gray, 0, F);
			}
			
			aWorld.setBlock(  1, mHeight+1, aMinZ+i, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_LightGray, 0);
			aWorld.setBlock(  1, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(  2, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_White, 0);
			aWorld.setBlock(  3, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(  4, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(  5, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(  6, mHeight  , aMinZ+i, BlocksGT.Asphalt, ((i+2) / 4) % 2 == 0 ? DYE_INDEX_Gray : DYE_INDEX_White, 0);
			aWorld.setBlock(  7, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(  8, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(  9, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock( 10, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_White, 0);
			aWorld.setBlock( 11, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock( 11, mHeight+1, aMinZ+i, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_LightGray, 0);
		}
		
		if (aTunnel) {
			aWorld.setBlock(-13, mHeight+3, aMinZ+ 1, Blocks.glowstone, 0, 0);
			aWorld.setBlock( 12, mHeight+3, aMinZ+ 1, Blocks.glowstone, 0, 0);
			aWorld.setBlock(-13, mHeight+3, aMinZ+ 6, Blocks.glowstone, 0, 0);
			aWorld.setBlock( 12, mHeight+3, aMinZ+ 6, Blocks.glowstone, 0, 0);
			aWorld.setBlock(-13, mHeight+3, aMinZ+ 9, Blocks.glowstone, 0, 0);
			aWorld.setBlock( 12, mHeight+3, aMinZ+ 9, Blocks.glowstone, 0, 0);
			aWorld.setBlock(-13, mHeight+3, aMinZ+14, Blocks.glowstone, 0, 0);
			aWorld.setBlock( 12, mHeight+3, aMinZ+14, Blocks.glowstone, 0, 0);
		}
		if (aSideWalls) {
			for (int i =  0; i <  8; i++) {Block tBlock = WD.block(aWorld,  13, mHeight+4, aMinZ+i, T); if (tBlock.getMaterial().isLiquid() || WD.opq(tBlock)) {for (int j = 0; j <  8; j++) for (int k = 2; k < 6; k++) WD.set(aWorld,  12, mHeight+k, aMinZ+j, WD.even(0, k, j)?BlocksGT.CFoam:BlocksGT.Concrete, DYE_INDEX_LightGray, 0, T); break;}}
			for (int i =  0; i <  8; i++) {Block tBlock = WD.block(aWorld, -14, mHeight+4, aMinZ+i, T); if (tBlock.getMaterial().isLiquid() || WD.opq(tBlock)) {for (int j = 0; j <  8; j++) for (int k = 2; k < 6; k++) WD.set(aWorld, -13, mHeight+k, aMinZ+j, WD.even(1, k, j)?BlocksGT.CFoam:BlocksGT.Concrete, DYE_INDEX_LightGray, 0, T); break;}}
			for (int i =  8; i < 16; i++) {Block tBlock = WD.block(aWorld,  13, mHeight+4, aMinZ+i, T); if (tBlock.getMaterial().isLiquid() || WD.opq(tBlock)) {for (int j = 8; j < 16; j++) for (int k = 2; k < 6; k++) WD.set(aWorld,  12, mHeight+k, aMinZ+j, WD.even(0, k, j)?BlocksGT.CFoam:BlocksGT.Concrete, DYE_INDEX_LightGray, 0, T); break;}}
			for (int i =  8; i < 16; i++) {Block tBlock = WD.block(aWorld, -14, mHeight+4, aMinZ+i, T); if (tBlock.getMaterial().isLiquid() || WD.opq(tBlock)) {for (int j = 8; j < 16; j++) for (int k = 2; k < 6; k++) WD.set(aWorld, -13, mHeight+k, aMinZ+j, WD.even(1, k, j)?BlocksGT.CFoam:BlocksGT.Concrete, DYE_INDEX_LightGray, 0, T); break;}}
		}
		
		if (aMinZ >> 9 != (aMinZ-16) >> 9) {
			for (int i = 5; i < 11; i++) for (int j = 1; j < 6; j++) {
			WD.set(aWorld, -13, mHeight+j, aMinZ+i, BlocksGT.Concrete, i == 5 || i == 10 || j == 1 || j == 5 ? aTunnel ? DYE_INDEX_Black : DYE_INDEX_White : aMinZ < 0 ? DYE_INDEX_Yellow : DYE_INDEX_Green, 0, j == 1);
			WD.set(aWorld,  12, mHeight+j, aMinZ+i, BlocksGT.Concrete, i == 5 || i == 10 || j == 1 || j == 5 ? aTunnel ? DYE_INDEX_Black : DYE_INDEX_White : aMinZ < 0 ? DYE_INDEX_Yellow : DYE_INDEX_Green, 0, j == 1);
			}
			WD.sign(aWorld, -12, mHeight+3, aMinZ+7, SIDE_X_POS, 0, "", "X: -1", "Z: " + ((aMinZ-16) >> 9), "");
			WD.sign(aWorld, -12, mHeight+3, aMinZ+8, SIDE_X_POS, 0, "", "X: -1", "Z: " + ( aMinZ     >> 9), "");
			WD.sign(aWorld,  11, mHeight+3, aMinZ+7, SIDE_X_NEG, 0, "", "X: 0" , "Z: " + ((aMinZ-16) >> 9), "");
			WD.sign(aWorld,  11, mHeight+3, aMinZ+8, SIDE_X_NEG, 0, "", "X: 0" , "Z: " + ( aMinZ     >> 9), "");
			
			aWorld.setBlock(-13, mHeight+1, aMinZ+ 5, Blocks.glowstone, 0, 0);
			aWorld.setBlock(-13, mHeight+1, aMinZ+10, Blocks.glowstone, 0, 0);
			aWorld.setBlock(-13, mHeight+5, aMinZ+ 5, Blocks.glowstone, 0, 0);
			aWorld.setBlock(-13, mHeight+5, aMinZ+10, Blocks.glowstone, 0, 0);
			aWorld.setBlock( 12, mHeight+1, aMinZ+ 5, Blocks.glowstone, 0, 0);
			aWorld.setBlock( 12, mHeight+1, aMinZ+10, Blocks.glowstone, 0, 0);
			aWorld.setBlock( 12, mHeight+5, aMinZ+ 5, Blocks.glowstone, 0, 0);
			aWorld.setBlock( 12, mHeight+5, aMinZ+10, Blocks.glowstone, 0, 0);
		}
		if (aMinZ >> 9 != (aMinZ+16) >> 9) {
			aWorld.setBlock(- 2, mHeight+1, aMinZ+0, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_White, 0);
			aWorld.setBlock(- 2, mHeight+1, aMinZ+1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_Red, 0);
			aWorld.setBlock(- 2, mHeight+1, aMinZ+2, NB, 0, 0);
			aWorld.setBlock(- 1, mHeight+1, aMinZ+2, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_White, 0);
			aWorld.setBlock(  0, mHeight+1, aMinZ+2, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_Red, 0);
			aWorld.setBlock(  1, mHeight+1, aMinZ+2, NB, 0, 0);
			aWorld.setBlock(  1, mHeight+1, aMinZ+1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_White, 0);
			aWorld.setBlock(  1, mHeight+1, aMinZ+0, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_Red, 0);
			
			for (int i = 2; i < 14; i++) {
			aWorld.setBlock(- 1, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(  0, mHeight  , aMinZ+i, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			}
			for (int i = 3; i < 13; i++) {
			aWorld.setBlock(- 2, mHeight+1, aMinZ+i, NB, 0, 0);
			aWorld.setBlock(- 1, mHeight+1, aMinZ+i, NB, 0, 0);
			aWorld.setBlock(  0, mHeight+1, aMinZ+i, NB, 0, 0);
			aWorld.setBlock(  1, mHeight+1, aMinZ+i, NB, 0, 0);
			}
			
			aWorld.setBlock(- 2, mHeight+1, aMinZ+15, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_Red, 0);
			aWorld.setBlock(- 2, mHeight+1, aMinZ+14, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_White, 0);
			aWorld.setBlock(- 2, mHeight+1, aMinZ+13, NB, 0, 0);
			aWorld.setBlock(- 1, mHeight+1, aMinZ+13, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_Red, 0);
			aWorld.setBlock(  0, mHeight+1, aMinZ+13, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_White, 0);
			aWorld.setBlock(  1, mHeight+1, aMinZ+13, NB, 0, 0);
			aWorld.setBlock(  1, mHeight+1, aMinZ+14, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_Red, 0);
			aWorld.setBlock(  1, mHeight+1, aMinZ+15, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_White, 0);
			
			if (aTunnel) {
				for (int i = 0; i < 7; i++) {
					aWorld.setBlock(-1, mHeight+i, aMinZ+ 0, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(-1, mHeight+i, aMinZ+ 1, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock( 0, mHeight+i, aMinZ+ 0, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock( 0, mHeight+i, aMinZ+ 1, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(-1, mHeight+i, aMinZ+14, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(-1, mHeight+i, aMinZ+15, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock( 0, mHeight+i, aMinZ+14, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock( 0, mHeight+i, aMinZ+15, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
				}
			}
		} else {
			if (aTunnel) {
				for (int i = 0; i < 7; i++) {
					aWorld.setBlock(-1, mHeight+i, aMinZ+ 7, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(-1, mHeight+i, aMinZ+ 8, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock( 0, mHeight+i, aMinZ+ 7, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock( 0, mHeight+i, aMinZ+ 8, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
				}
			}
		}
		
		if (aBridge) {
			for (int i = 6; i <= 9; i++) {
				for (int j = -9; j <= -6; j++) {
					aWorld.setBlock(j, mHeight-2, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					aWorld.setBlock(j, mHeight-3, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
				}
				for (int j = 5; j <= 8; j++) {
					aWorld.setBlock(j, mHeight-2, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					aWorld.setBlock(j, mHeight-3, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
				}
			}
			for (int k = mHeight-4; k > 0; k--) if (!(WD.opq(aWorld, -10, k, aMinZ+10, T, T) && WD.opq(aWorld, -5, k, aMinZ+10, T, T) && WD.opq(aWorld, -10, k, aMinZ+5, T, T) && WD.opq(aWorld, -5, k, aMinZ+5, T, T))) {
				aWorld.setBlock( -7, k, aMinZ+7, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock( -8, k, aMinZ+8, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock( -7, k, aMinZ+8, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock( -8, k, aMinZ+7, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
			} else {
				for (int i = 6; i <= 9; i++) for (int j = -9; j <= -6; j++) {
					if (k>-3) aWorld.setBlock(j, k+3, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>-2) aWorld.setBlock(j, k+2, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>-1) aWorld.setBlock(j, k+1, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k> 0) aWorld.setBlock(j, k  , aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>+1) aWorld.setBlock(j, k-1, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>+2) aWorld.setBlock(j, k-2, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>+3) aWorld.setBlock(j, k-3, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
				}
				break;
			}
			for (int k = mHeight-4; k > 0; k--) if (!(WD.opq(aWorld, 9, k, aMinZ+10, T, T) && WD.opq(aWorld, 4, k, aMinZ+10, T, T) && WD.opq(aWorld, 9, k, aMinZ+5, T, T) && WD.opq(aWorld, 4, k, aMinZ+5, T, T))) {
				aWorld.setBlock(  6, k, aMinZ+7, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock(  7, k, aMinZ+8, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock(  6, k, aMinZ+8, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock(  7, k, aMinZ+7, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
			} else {
				for (int i = 6; i <= 9; i++) for (int j = 5; j <= 8; j++) {
					if (k>-3) aWorld.setBlock(j, k+3, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>-2) aWorld.setBlock(j, k+2, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>-1) aWorld.setBlock(j, k+1, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k> 0) aWorld.setBlock(j, k  , aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>+1) aWorld.setBlock(j, k-1, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>+2) aWorld.setBlock(j, k-2, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>+3) aWorld.setBlock(j, k-3, aMinZ+i, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
				}
				break;
			}
		}
		
		// Kill every living thing close by except Players.
		for (EntityLivingBase tEntity : (List<EntityLivingBase>)aWorld.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(-16, mHeight, aMinZ, +16, mHeight+8, aMinZ+16))) if (!(tEntity instanceof EntityPlayer)) tEntity.setDead();
		return T;
	}
	
	@SuppressWarnings("unchecked")
	public final boolean generateRoadZ(World aWorld, int aMinX, boolean aLand, boolean aKillSky, boolean aTunnel, boolean aBridge, boolean aSideWalls) {
		for (int i = 0; i < 16; i++) {
			if (aLand) {
				for (int j = mHeight+1; j > 0; j--) if (!WD.opq(aWorld, aMinX+i, j, -13, T, T)) WD.set(aWorld, aMinX+i, j, -13, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight+1; j > 0; j--) if (!WD.opq(aWorld, aMinX+i, j,  12, T, T)) WD.set(aWorld, aMinX+i, j,  12, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight  ; j > 0; j--) if (!WD.opq(aWorld, aMinX+i, j, -14, T, T)) WD.set(aWorld, aMinX+i, j, -14, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight  ; j > 0; j--) if (!WD.opq(aWorld, aMinX+i, j,  13, T, T)) WD.set(aWorld, aMinX+i, j,  13, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight-1; j > 0; j--) if (!WD.opq(aWorld, aMinX+i, j, -15, T, T)) WD.set(aWorld, aMinX+i, j, -15, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight-1; j > 0; j--) if (!WD.opq(aWorld, aMinX+i, j,  14, T, T)) WD.set(aWorld, aMinX+i, j,  14, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight-2; j > 0; j--) if (!WD.opq(aWorld, aMinX+i, j, -16, T, T)) WD.set(aWorld, aMinX+i, j, -16, Blocks.gravel, 1, 0, T); else break;
				for (int j = mHeight-2; j > 0; j--) if (!WD.opq(aWorld, aMinX+i, j,  15, T, T)) WD.set(aWorld, aMinX+i, j,  15, Blocks.gravel, 1, 0, T); else break;
			}
			if (aTunnel) {
				for (int j = -12; j < 12; j++)
				aWorld.setBlock(aMinX+i, mHeight+7,   j, BlocksGT.Concrete, DYE_INDEX_White, 0);
				for (int j = 0; j < 7; j++) {
				aWorld.setBlock(aMinX+i, mHeight+j, -13, BlocksGT.Concrete, j == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
				aWorld.setBlock(aMinX+i, mHeight+j,  12, BlocksGT.Concrete, j == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
				}
			}
			if (aBridge) {
				WD.set(aWorld, aMinX+i, mHeight  , -13, BlocksGT.Concrete, DYE_INDEX_Gray, 0, T);
				WD.set(aWorld, aMinX+i, mHeight  ,  12, BlocksGT.Concrete, DYE_INDEX_Gray, 0, T);
				aWorld.setBlock(aMinX+i, mHeight+1, -13, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock(aMinX+i, mHeight+1,  12, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
			}
			if (aKillSky) {
				for (int j = -13; j < 13; j++) for (int k = 2; k < 32; k++) aWorld.setBlock(aMinX+i, mHeight+k, j, NB, 0, 0);
			} else {
				for (int j = -12; j < 12; j++) for (int k = 2; k <  7; k++) aWorld.setBlock(aMinX+i, mHeight+k, j, NB, 0, 0);
			}
			for (int j = -12; j < 2; j++) {
				aWorld.setBlock(aMinX+i, mHeight+1, j, NB, 0, 0);
				if (aLand) {
				WD.set(aWorld, aMinX+i, mHeight-2, j, Blocks.cobblestone, 0, 0, T);
				aWorld.setBlock(aMinX+i, mHeight-1, j, Blocks.gravel, 1, 0);
				for (int k = mHeight-3; k > 0; k--) if (!WD.opq(aWorld, aMinX+i, k, j, T, T)) WD.set(aWorld, aMinX+i, k, j, Blocks.cobblestone, 0, 0, T); else break;
				} else {
				WD.set(aWorld, aMinX+i, mHeight-1, j, BlocksGT.Concrete, DYE_INDEX_Gray, 0, T);
				}
			}
			for (int j = 1; j < 12; j++) {
				aWorld.setBlock(aMinX+i, mHeight+1, j, NB, 0, 0);
				if (aLand) {
				WD.set(aWorld, aMinX+i, mHeight-2, j, Blocks.cobblestone, 0, 0, T);
				aWorld.setBlock(aMinX+i, mHeight-1, j, Blocks.gravel, 1, 0);
				for (int k = mHeight-3; k > 0; k--) if (!WD.opq(aWorld, aMinX+i, k, j, T, T)) WD.set(aWorld, aMinX+i, k, j, Blocks.cobblestone, 0, 0, T); else break;
				} else {
				WD.set(aWorld, aMinX+i, mHeight-1, j, BlocksGT.Concrete, DYE_INDEX_Gray, 0, T);
				}
			}
			
			aWorld.setBlock(aMinX+i, mHeight+1, -12, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_LightGray, 0);
			aWorld.setBlock(aMinX+i, mHeight  , -12, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  , -11, BlocksGT.Asphalt, DYE_INDEX_White, 0);
			aWorld.setBlock(aMinX+i, mHeight  , -10, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  , - 9, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  , - 8, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  , - 7, BlocksGT.Asphalt, ((i+2) / 4) % 2 == 0 ? DYE_INDEX_Gray : DYE_INDEX_White, 0);
			aWorld.setBlock(aMinX+i, mHeight  , - 6, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  , - 5, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  , - 4, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  , - 3, BlocksGT.Asphalt, DYE_INDEX_White, 0);
			aWorld.setBlock(aMinX+i, mHeight  , - 2, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight+1, - 2, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_LightGray, 0);
			
			if (aLand) {
			WD.set(aWorld, aMinX+i, mHeight-1, - 1, Blocks.cobblestone, 0, 0, T);
			WD.set(aWorld, aMinX+i, mHeight  , - 1, Blocks.gravel, 1, 0, F);
			WD.set(aWorld, aMinX+i, mHeight+1, - 1, Blocks.gravel, 1, 0, F);
			WD.set(aWorld, aMinX+i, mHeight-1,   0, Blocks.cobblestone, 0, 0, T);
			WD.set(aWorld, aMinX+i, mHeight  ,   0, Blocks.gravel, 1, 0, F);
			WD.set(aWorld, aMinX+i, mHeight+1,   0, Blocks.gravel, 1, 0, F);
			for (int j = mHeight-2; j > 0; j--) if (!WD.opq(aWorld, aMinX+i, j, - 1, T, T)) WD.set(aWorld, aMinX+i, j, - 1, Blocks.cobblestone, 0, 0, T); else break;
			for (int j = mHeight-2; j > 0; j--) if (!WD.opq(aWorld, aMinX+i, j,   0, T, T)) WD.set(aWorld, aMinX+i, j,   0, Blocks.cobblestone, 0, 0, T); else break;
			} else {
			WD.set(aWorld, aMinX+i, mHeight-1, - 1, BlocksGT.Concrete, DYE_INDEX_Gray, 0, T);
			WD.set(aWorld, aMinX+i, mHeight  , - 1, BlocksGT.Concrete, DYE_INDEX_Gray, 0, F);
			WD.set(aWorld, aMinX+i, mHeight+1, - 1, BlocksGT.Concrete, DYE_INDEX_Gray, 0, F);
			WD.set(aWorld, aMinX+i, mHeight-1,   0, BlocksGT.Concrete, DYE_INDEX_Gray, 0, T);
			WD.set(aWorld, aMinX+i, mHeight  ,   0, BlocksGT.Concrete, DYE_INDEX_Gray, 0, F);
			WD.set(aWorld, aMinX+i, mHeight+1,   0, BlocksGT.Concrete, DYE_INDEX_Gray, 0, F);
			}
			
			aWorld.setBlock(aMinX+i, mHeight+1,   1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_LightGray, 0);
			aWorld.setBlock(aMinX+i, mHeight  ,   1, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  ,   2, BlocksGT.Asphalt, DYE_INDEX_White, 0);
			aWorld.setBlock(aMinX+i, mHeight  ,   3, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  ,   4, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  ,   5, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  ,   6, BlocksGT.Asphalt, ((i+2) / 4) % 2 == 0 ? DYE_INDEX_Gray : DYE_INDEX_White, 0);
			aWorld.setBlock(aMinX+i, mHeight  ,   7, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  ,   8, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  ,   9, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  ,  10, BlocksGT.Asphalt, DYE_INDEX_White, 0);
			aWorld.setBlock(aMinX+i, mHeight  ,  11, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight+1,  11, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_LightGray, 0);
		}
		
		if (aTunnel) {
			aWorld.setBlock(aMinX+ 1, mHeight+3, -13, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+ 1, mHeight+3,  12, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+ 6, mHeight+3, -13, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+ 6, mHeight+3,  12, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+ 9, mHeight+3, -13, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+ 9, mHeight+3,  12, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+14, mHeight+3, -13, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+14, mHeight+3,  12, Blocks.glowstone, 0, 0);
		}
		if (aSideWalls) {
			for (int i =  0; i <  8; i++) {Block tBlock = WD.block(aWorld, aMinX+i, mHeight+4,  13, T); if (tBlock.getMaterial().isLiquid() || WD.opq(tBlock)) {for (int j = 0; j <  8; j++) for (int k = 2; k < 6; k++) WD.set(aWorld, aMinX+j, mHeight+k,  12, WD.even(0, k, j)?BlocksGT.CFoam:BlocksGT.Concrete, DYE_INDEX_LightGray, 0, T); break;}}
			for (int i =  0; i <  8; i++) {Block tBlock = WD.block(aWorld, aMinX+i, mHeight+4, -14, T); if (tBlock.getMaterial().isLiquid() || WD.opq(tBlock)) {for (int j = 0; j <  8; j++) for (int k = 2; k < 6; k++) WD.set(aWorld, aMinX+j, mHeight+k, -13, WD.even(1, k, j)?BlocksGT.CFoam:BlocksGT.Concrete, DYE_INDEX_LightGray, 0, T); break;}}
			for (int i =  8; i < 16; i++) {Block tBlock = WD.block(aWorld, aMinX+i, mHeight+4,  13, T); if (tBlock.getMaterial().isLiquid() || WD.opq(tBlock)) {for (int j = 8; j < 16; j++) for (int k = 2; k < 6; k++) WD.set(aWorld, aMinX+j, mHeight+k,  12, WD.even(0, k, j)?BlocksGT.CFoam:BlocksGT.Concrete, DYE_INDEX_LightGray, 0, T); break;}}
			for (int i =  8; i < 16; i++) {Block tBlock = WD.block(aWorld, aMinX+i, mHeight+4, -14, T); if (tBlock.getMaterial().isLiquid() || WD.opq(tBlock)) {for (int j = 8; j < 16; j++) for (int k = 2; k < 6; k++) WD.set(aWorld, aMinX+j, mHeight+k, -13, WD.even(1, k, j)?BlocksGT.CFoam:BlocksGT.Concrete, DYE_INDEX_LightGray, 0, T); break;}}
		}
		
		if (aMinX >> 9 != (aMinX-16) >> 9) {
			for (int i = 5; i < 11; i++) for (int j = 1; j < 6; j++) {
			WD.set(aWorld, aMinX+i, mHeight+j, -13, BlocksGT.Concrete, i == 5 || i == 10 || j == 1 || j == 5 ? aTunnel ? DYE_INDEX_Black : DYE_INDEX_White : aMinX < 0 ? DYE_INDEX_Blue : DYE_INDEX_Red, 0, j == 1);
			WD.set(aWorld, aMinX+i, mHeight+j,  12, BlocksGT.Concrete, i == 5 || i == 10 || j == 1 || j == 5 ? aTunnel ? DYE_INDEX_Black : DYE_INDEX_White : aMinX < 0 ? DYE_INDEX_Blue : DYE_INDEX_Red, 0, j == 1);
			}
			WD.sign(aWorld, aMinX+7, mHeight+3, -12, SIDE_Z_POS, 0, "", "X: " + ((aMinX-16) >> 9), "Z: -1", "");
			WD.sign(aWorld, aMinX+8, mHeight+3, -12, SIDE_Z_POS, 0, "", "X: " + ( aMinX     >> 9), "Z: -1", "");
			WD.sign(aWorld, aMinX+7, mHeight+3,  11, SIDE_Z_NEG, 0, "", "X: " + ((aMinX-16) >> 9), "Z: 0" , "");
			WD.sign(aWorld, aMinX+8, mHeight+3,  11, SIDE_Z_NEG, 0, "", "X: " + ( aMinX     >> 9), "Z: 0" , "");
			
			aWorld.setBlock(aMinX+ 5, mHeight+1, -13, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+10, mHeight+1, -13, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+ 5, mHeight+5, -13, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+10, mHeight+5, -13, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+ 5, mHeight+1,  12, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+10, mHeight+1,  12, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+ 5, mHeight+5,  12, Blocks.glowstone, 0, 0);
			aWorld.setBlock(aMinX+10, mHeight+5,  12, Blocks.glowstone, 0, 0);
		}
		if (aMinX >> 9 != (aMinX+16) >> 9) {
			aWorld.setBlock(aMinX+0, mHeight+1, - 2, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_White, 0);
			aWorld.setBlock(aMinX+1, mHeight+1, - 2, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_Red, 0);
			aWorld.setBlock(aMinX+2, mHeight+1, - 2, NB, 0, 0);
			aWorld.setBlock(aMinX+2, mHeight+1, - 1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_White, 0);
			aWorld.setBlock(aMinX+2, mHeight+1,   0, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_NEG], DYE_INDEX_Red, 0);
			aWorld.setBlock(aMinX+2, mHeight+1,   1, NB, 0, 0);
			aWorld.setBlock(aMinX+1, mHeight+1,   1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_White, 0);
			aWorld.setBlock(aMinX+0, mHeight+1,   1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_Red, 0);
			
			for (int i = 2; i < 14; i++) {
			aWorld.setBlock(aMinX+i, mHeight  , - 1, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			aWorld.setBlock(aMinX+i, mHeight  ,   0, BlocksGT.Asphalt, DYE_INDEX_Gray, 0);
			}
			for (int i = 3; i < 13; i++) {
			aWorld.setBlock(aMinX+i, mHeight+1, - 2, NB, 0, 0);
			aWorld.setBlock(aMinX+i, mHeight+1, - 1, NB, 0, 0);
			aWorld.setBlock(aMinX+i, mHeight+1,   0, NB, 0, 0);
			aWorld.setBlock(aMinX+i, mHeight+1,   1, NB, 0, 0);
			}
			
			aWorld.setBlock(aMinX+15, mHeight+1, - 2, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_Red, 0);
			aWorld.setBlock(aMinX+14, mHeight+1, - 2, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_POS], DYE_INDEX_White, 0);
			aWorld.setBlock(aMinX+13, mHeight+1, - 2, NB, 0, 0);
			aWorld.setBlock(aMinX+13, mHeight+1, - 1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_Red, 0);
			aWorld.setBlock(aMinX+13, mHeight+1,   0, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_X_POS], DYE_INDEX_White, 0);
			aWorld.setBlock(aMinX+13, mHeight+1,   1, NB, 0, 0);
			aWorld.setBlock(aMinX+14, mHeight+1,   1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_Red, 0);
			aWorld.setBlock(aMinX+15, mHeight+1,   1, ((BlockMetaType)BlocksGT.CFoam).mSlabs[SIDE_Z_NEG], DYE_INDEX_White, 0);
			
			if (aTunnel) {
				for (int i = 0; i < 7; i++) {
					aWorld.setBlock(aMinX+ 0, mHeight+i, -1, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(aMinX+ 0, mHeight+i,  0, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(aMinX+ 1, mHeight+i, -1, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(aMinX+ 1, mHeight+i,  0, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(aMinX+14, mHeight+i, -1, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(aMinX+14, mHeight+i,  0, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(aMinX+15, mHeight+i, -1, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(aMinX+15, mHeight+i,  0, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
				}
			}
		} else {
			if (aTunnel) {
				for (int i = 0; i < 7; i++) {
					aWorld.setBlock(aMinX+ 7, mHeight+i, -1, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(aMinX+ 7, mHeight+i,  0, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(aMinX+ 8, mHeight+i, -1, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
					aWorld.setBlock(aMinX+ 8, mHeight+i,  0, BlocksGT.Concrete, i == 3 ? DYE_INDEX_LightGray : DYE_INDEX_White, 0);
				}
			}
		}
		
		if (aBridge) {
			for (int i = 6; i <= 9; i++) {
				for (int j = -9; j <= -6; j++) {
					aWorld.setBlock(aMinX+i, mHeight-2, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					aWorld.setBlock(aMinX+i, mHeight-3, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
				}
				for (int j = 5; j <= 8; j++) {
					aWorld.setBlock(aMinX+i, mHeight-2, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					aWorld.setBlock(aMinX+i, mHeight-3, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
				}
			}
			for (int k = mHeight-4; k > 0; k--) if (!(WD.opq(aWorld, aMinX+10, k, -10, T, T) && WD.opq(aWorld, aMinX+10, k, -5, T, T) && WD.opq(aWorld, aMinX+5, k, -10, T, T) && WD.opq(aWorld, aMinX+5, k, -5, T, T))) {
				aWorld.setBlock(aMinX+7, k,  -7, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock(aMinX+8, k,  -8, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock(aMinX+7, k,  -8, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock(aMinX+8, k,  -7, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
			} else {
				for (int i = 6; i <= 9; i++) for (int j = -9; j <= -6; j++) {
					if (k>-3) aWorld.setBlock(aMinX+i, k+3, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>-2) aWorld.setBlock(aMinX+i, k+2, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>-1) aWorld.setBlock(aMinX+i, k+1, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k> 0) aWorld.setBlock(aMinX+i, k  , j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>+1) aWorld.setBlock(aMinX+i, k-1, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>+2) aWorld.setBlock(aMinX+i, k-2, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>+3) aWorld.setBlock(aMinX+i, k-3, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
				}
				break;
			}
			for (int k = mHeight-4; k > 0; k--) if (!(WD.opq(aWorld, aMinX+10, k, 9, T, T) && WD.opq(aWorld, aMinX+10, k, 4, T, T) && WD.opq(aWorld, aMinX+5, k, 9, T, T) && WD.opq(aWorld, aMinX+5, k, 4, T, T))) {
				aWorld.setBlock(aMinX+7, k,   6, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock(aMinX+8, k,   7, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock(aMinX+7, k,   7, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
				aWorld.setBlock(aMinX+8, k,   6, BlocksGT.Concrete, DYE_INDEX_Gray, 0);
			} else {
				for (int i = 6; i <= 9; i++) for (int j = 5; j <= 8; j++) {
					if (k>-3) aWorld.setBlock(aMinX+i, k+3, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>-2) aWorld.setBlock(aMinX+i, k+2, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>-1) aWorld.setBlock(aMinX+i, k+1, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k> 0) aWorld.setBlock(aMinX+i, k  , j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>+1) aWorld.setBlock(aMinX+i, k-1, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>+2) aWorld.setBlock(aMinX+i, k-2, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
					if (k>+3) aWorld.setBlock(aMinX+i, k-3, j, BlocksGT.Concrete, DYE_INDEX_LightGray, 0);
				}
				break;
			}
		}
		
		// Kill every living thing close by except Players.
		for (EntityLivingBase tEntity : (List<EntityLivingBase>)aWorld.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(aMinX, mHeight, -16, aMinX+16, mHeight+8, +16))) if (!(tEntity instanceof EntityPlayer)) tEntity.setDead();
		return T;
	}
}
