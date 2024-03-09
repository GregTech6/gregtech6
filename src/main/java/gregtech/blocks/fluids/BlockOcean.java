/**
 * Copyright (c) 2024 GregTech-6 Team
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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.code.ArrayListNoNulls;
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
public class BlockOcean extends BlockWaterlike {
	public static boolean PLACEMENT_ALLOWED = F, FLOWS_OUT = T, SPREAD_TO_AIR = T, UPDATE_TICK = T;
	
	public BlockOcean(String aName, Fluid aFluid) {
		super(aName, aFluid, FLOWS_OUT, T);
		tickRate = 20;
	}
	
	@Override
	public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
		if (PLACEMENT_ALLOWED) {
			if (UPDATE_TICK) aWorld.scheduleBlockUpdate(aX, aY, aZ, this, 10+RNGSUS.nextInt(90));
		} else {
			aWorld.setBlock(aX, aY, aZ, NB, 0, 2);
		}
	}
	
	@Override
	public void onNeighborBlockChange(World aWorld, int aX, int aY, int aZ, Block aBlock) {
		if (aBlock == Blocks.dirt && aWorld.getBlock(aX, aY-1, aZ) == Blocks.grass) aWorld.setBlock(aX, aY-1, aZ, Blocks.dirt, 1, 2);
		super.onNeighborBlockChange(aWorld, aX, aY, aZ, aBlock);
	}
	
	@Override
	public void updateTick(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		PLACEMENT_ALLOWED = UPDATE_TICK = T;
		
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
		
		boolean tHasNoOceanAround = T, tHasOceanBiome = BIOMES_OCEAN_BEACH.contains(tBiome.biomeName);
		byte tOceanCounter = 0;
		ArrayListNoNulls<ChunkCoordinates> tList = new ArrayListNoNulls<>();
		for (byte tSide : ALL_SIDES_HORIZONTAL) {
			tBlock = WD.block(aWorld, aX, aY, aZ, tSide);
			if (tBlock == this) {
				tHasNoOceanAround = F;
				if (tHasOceanBiome || WD.meta(aWorld, aX, aY, aZ, tSide) == 0) tOceanCounter++;
			} else if (tBlock == BlocksGT.River) {
				tList.add(new ChunkCoordinates(aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide]));
				tOceanCounter++;
			} else if (WD.water(tBlock)) {
				tList.add(new ChunkCoordinates(aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide]));
				if (tHasOceanBiome || WD.meta(aWorld, aX, aY, aZ, tSide) == 0) tOceanCounter++;
			} else if (tHasOceanBiome && tBlock instanceof BlockWaterlike) {
				tList.add(new ChunkCoordinates(aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide]));
				tOceanCounter++;
			}
		}
		
		tBlock = WD.block(aWorld, aX, aY-1, aZ);
		if (tBlock == this) {
			tHasNoOceanAround = F;
			if (WD.meta(aWorld, aX, aY-1, aZ) == 0) tOceanCounter++;
		} else if (WD.anywater(tBlock)) {
			tHasNoOceanAround = F;
			if (aWorld.setBlock(aX, aY-1, aZ, this, 0, WATER_UPDATE_FLAGS)) tOceanCounter++;
		}
		
		if (tHasNoOceanAround && WD.block(aWorld, aX, aY+1, aZ) != this) {
			aWorld.setBlock(aX, aY, aZ, NB, 0, WATER_UPDATE_FLAGS);
			PLACEMENT_ALLOWED = F;
			return;
		}
		
		if (WD.meta(aWorld, aX, aY, aZ) != 0) {
			if (tOceanCounter >= 2 || (SPREAD_TO_AIR && tHasOceanBiome) || (aWorld.getBlock(aX, aY+1, aZ) == this && WD.meta(aWorld, aX, aY+1, aZ) == 0)) {
				aWorld.setBlock(aX, aY, aZ, this, 0, WATER_UPDATE_FLAGS);
			}
		}
		
		if (BIOMES_RIVER_LAKE.contains(tBiome.biomeName)) {
			tOceanCounter = 0;
			for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) if (i != 0 && j != 0) {
				if (aWorld.getBlock(aX+i, aY, aZ+j) == this && WD.meta(aWorld, aX+i, aY, aZ+j) == 0) {
					tOceanCounter++;
				}
			}
			if (tOceanCounter < 3) {
				updateFlow(aWorld, aX, aY, aZ, aRandom);
				PLACEMENT_ALLOWED = F;
				return;
			}
		}
		
		for (ChunkCoordinates tCoords : tList) {
			if (aWorld.setBlock(tCoords.posX, tCoords.posY, tCoords.posZ, this, 0, WATER_UPDATE_FLAGS)) for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) {
				if (aWorld.blockExists(tCoords.posX+i, tCoords.posY, tCoords.posZ+j)) {
					tBlock = aWorld.getBlock(tCoords.posX+i, tCoords.posY, tCoords.posZ+j);
					if (tBlock == this) aWorld.scheduleBlockUpdate(tCoords.posX+i, tCoords.posY, tCoords.posZ+j, this, tickRate);
				}
			}
		}
		
		updateFlow(aWorld, aX, aY, aZ, aRandom);
		PLACEMENT_ALLOWED = F;
		return;
	}
	
	@Override
	public int getLightOpacity(IBlockAccess aWorld, int aX, int aY, int aZ) {
		// TODO FIX THIS SHIT
		return aWorld.getBlockMetadata(aX, aY, aZ) == 0 && WD.air(aWorld.getBlock(aX, aY+1, aZ)) && WD.air(aWorld.getBlock(aX, aY+2, aZ)) && aWorld.getBlock(aX, aY-1, aZ).getLightOpacity(aWorld, aX, aY-1, aZ) < LIGHT_OPACITY_MAX ? 16 : LIGHT_OPACITY_NONE;
	}
	
	@Override public IIcon getIcon(int aSide, int aMeta) {return Blocks.water.getIcon(aSide, aMeta);}
	@Override @SideOnly(Side.CLIENT) public int getRenderColor(int aMeta) {return 0x00c0c0c0;}
	@Override @SideOnly(Side.CLIENT) public int colorMultiplier(IBlockAccess aWorld, int aX, int aY, int aZ) {return 0x00c0c0c0;}
}
