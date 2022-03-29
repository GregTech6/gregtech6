/**
 * Copyright (c) 2022 GregTech-6 Team
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

package gregtech.blocks.fluids;

import gregapi.block.IBlockExtendedMetaData;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.IL;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class BlockSwamp extends BlockWaterlike {
	public static boolean PLACEMENT_ALLOWED = F, FLOWS_OUT = T;
	
	public BlockSwamp(String aName, Fluid aFluid) {
		super(aName, aFluid, FLOWS_OUT);
		tickRate = 10;
	}
	
	@Override
	public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
		if (PLACEMENT_ALLOWED) {
			aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 10+RNGSUS.nextInt(90));
		} else {
			aWorld.setBlockToAir(aX, aY, aZ);
		}
	}
	
	@Override
	public void updateTick(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		PLACEMENT_ALLOWED = T;
		
		if (aWorld.doChunksNearChunkExist(aX, aY, aZ, 33)) {
			aWorld.func_147451_t(aX, aY, aZ);
			WD.update(aWorld, aX, aY, aZ);
			if (aY > 0) {
				if (aWorld.getBlock(aX, aY-1, aZ) == this) {
					aWorld.scheduleBlockUpdate(aX, aY-1, aZ, this, tickRate);
				} else {
					aWorld.func_147451_t(aX, aY-1, aZ);
					WD.update(aWorld, aX, aY-1, aZ);
				}
			}
		} else {
			aWorld.scheduleBlockUpdate(aX, aY, aZ, this, Math.max(600, tickRate));
			PLACEMENT_ALLOWED = F;
			return;
		}
		
		if (aY <= 0) {
			updateFlow(aWorld, aX, aY, aZ, aRandom);
			PLACEMENT_ALLOWED = F;
			return;
		}
		
		Block tBlock;
		
		BiomeGenBase tBiome = aWorld.getBiomeGenForCoords(aX, aZ);
		
		boolean tDirt = F;
		
		byte tSwampCounter = 0;
		ArrayListNoNulls<ChunkCoordinates> tList = new ArrayListNoNulls<>();
		for (byte tSide : ALL_SIDES_BUT_TOP) {
			tBlock = WD.block(aWorld, aX, aY, aZ, tSide);
			if (tBlock != NB) {
				byte tMeta = WD.meta(aWorld, aX, aY, aZ, tSide);
				if (tBlock == this) {
					if (tMeta == 0) tSwampCounter++;
				} else if (tBlock instanceof BlockWaterlike) {
					if (tMeta == 0 || tBlock instanceof BlockOcean) tSwampCounter++;
				} else if (tBlock == Blocks.water || tBlock == Blocks.flowing_water) {
					tList.add(new ChunkCoordinates(aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide]));
					if (tMeta == 0) tSwampCounter++;
				} else if (tBlock == Blocks.sand || tBlock == Blocks.dirt || tBlock == Blocks.grass || tBlock == Blocks.mycelium || tBlock == BlocksGT.Grass || tBlock == BlocksGT.Diggables || tBlock == BlocksGT.Sands || tBlock == BlocksGT.oreSand || tBlock == BlocksGT.oreRedSand || tBlock == BlocksGT.oreMud || tBlock == BlocksGT.oreSmallSand || tBlock == BlocksGT.oreSmallRedSand || tBlock == BlocksGT.oreSmallMud || IL.EtFu_Dirt.equal(tBlock)) {
					tDirt = T;
				}
			}
		}
		
		if (tDirt) for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
			tBlock = aWorld.getBlock(aX+i, aY+j, aZ+k);
			if (tBlock == Blocks.sand || tBlock == Blocks.dirt || tBlock == Blocks.grass || tBlock == Blocks.mycelium || IL.EtFu_Dirt.equal(tBlock)) {aWorld.setBlock(aX+i, aY+j, aZ+k, BlocksGT.Diggables, 0, 2); continue;}
			if (tBlock == BlocksGT.oreSand || tBlock == BlocksGT.oreRedSand) {BlocksGT.oreMud.placeBlock(aWorld, aX+i, aY+j, aZ+k, SIDE_UNKNOWN, ((IBlockExtendedMetaData)tBlock).getExtendedMetaData(aWorld, aX+i, aY+j, aZ+k), null, T, T); continue;}
			if (tBlock == BlocksGT.oreSmallSand || tBlock == BlocksGT.oreSmallRedSand) {BlocksGT.oreSmallMud.placeBlock(aWorld, aX+i, aY+j, aZ+k, SIDE_UNKNOWN, ((IBlockExtendedMetaData)tBlock).getExtendedMetaData(aWorld, aX+i, aY+j, aZ+k), null, T, T); continue;}
		}
		
		if (aWorld.getBlockMetadata(aX, aY, aZ) == 0) {
			if (tSwampCounter <= 0 && !(aWorld.getBlock(aX, aY+1, aZ) instanceof BlockSwamp)) {
				aWorld.setBlockToAir(aX, aY, aZ);
				PLACEMENT_ALLOWED = F;
				return;
			}
		} else {
			if (tSwampCounter >= 2) {
				aWorld.setBlock(aX, aY, aZ, this, 0, WATER_UPDATE_FLAGS);
			}
		}
		
		if (BIOMES_INFINITE_WATER.contains(tBiome.biomeName)) {
			tSwampCounter = 0;
			for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) if (i != 0 && j != 0) {
				if (aWorld.getBlock(aX+i, aY, aZ+j) instanceof BlockSwamp && aWorld.getBlockMetadata(aX+i, aY, aZ+j) == 0) {
					tSwampCounter++;
				}
			}
			if (tSwampCounter < 3) {
				updateFlow(aWorld, aX, aY, aZ, aRandom);
				PLACEMENT_ALLOWED = F;
				return;
			}
		}
		
		for (ChunkCoordinates tCoords : tList) {
			if (aWorld.setBlock(tCoords.posX, tCoords.posY, tCoords.posZ, this, 0, WATER_UPDATE_FLAGS)) for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) {
				if (aWorld.blockExists(tCoords.posX+i, tCoords.posY, tCoords.posZ+j)) {
					tBlock = aWorld.getBlock(tCoords.posX+i, tCoords.posY, tCoords.posZ+j);
					if (tBlock instanceof BlockSwamp) aWorld.scheduleBlockUpdate(tCoords.posX+i, tCoords.posY, tCoords.posZ+j, this, tickRate);
				}
			}
		}
		
		updateFlow(aWorld, aX, aY, aZ, aRandom);
		PLACEMENT_ALLOWED = F;
		return;
	}
	
	@Override public int getLightOpacity(IBlockAccess aWorld, int aX, int aY, int aZ) {if (aWorld.getBlock(aX, aY+1, aZ) != this || aWorld.getBlockMetadata(aX, aY, aZ) > 0) return LIGHT_OPACITY_WATER; return LIGHT_OPACITY_MAX;}
	@Override public IIcon getIcon(int aSide, int aMeta) {return Blocks.water.getIcon(aSide, aMeta);}
	@Override public int getRenderColor(int aMeta) {return 0x0000ff00;}
	
	@Override
	public int colorMultiplier(IBlockAccess aWorld, int aX, int aY, int aZ) {
		if (aWorld.getBlock(aX, aY+1, aZ) ==this) return 0x0000ff00;
		if (water(aWorld.getBlock(aX+1, aY, aZ))) return 0x0060ff60;
		if (water(aWorld.getBlock(aX-1, aY, aZ))) return 0x0060ff60;
		if (water(aWorld.getBlock(aX, aY, aZ+1))) return 0x0060ff60;
		if (water(aWorld.getBlock(aX, aY, aZ-1))) return 0x0060ff60;
		return 0x0000ff00;
	}
	
	public static boolean water(Block aBlock) {return aBlock == Blocks.water || aBlock == Blocks.flowing_water || aBlock == BlocksGT.Ocean || aBlock == BlocksGT.River;}
}
