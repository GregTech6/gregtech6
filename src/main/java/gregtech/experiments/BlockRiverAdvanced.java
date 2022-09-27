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

package gregtech.experiments;

import gregapi.data.FL;
import gregapi.util.WD;
import gregtech.blocks.fluids.BlockWaterlike;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.Random;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class BlockRiverAdvanced extends BlockWaterlike {
	public BlockRiverAdvanced(String aName, Fluid aFluid) {
		super(aName, aFluid, F, F);
		tickRate = 1;
	}
	
	@Override
	public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
		aWorld.scheduleBlockUpdate(aX, aY, aZ, this, tickRate);
	}
	
	@Override
	public void updateTick(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		// Scan surroundings.
		Block[] aBlocks  = new Block[7];
		byte [] aMetas   = new byte [7];
		byte    aMeta    = WD.meta(aWorld, aX, aY, aZ, T), aSource = SIDE_UNKNOWN, aFlow = META_TO_SIDE_1[aMeta];
		
		// Gather necessary Data.
		for (byte tSide : ALL_SIDES_VALID) {
			aMetas [tSide] = WD.meta (aWorld, aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide], T);
			aBlocks[tSide] = WD.block(aWorld, aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide], T);
			// Check if this River Block has a Source.
			if (aBlocks[tSide] == this && aMetas[tSide]-1 == OPOS[tSide]) aSource = tSide;
			if (aBlocks[tSide] == Blocks.bedrock) aSource = tSide; // TODO: Remove this if this River Block ever gets used!
		}
		// Make sure whatever this flows into is actually a River Block.
		if (aBlocks[aFlow] != this) aFlow = SIDE_INVALID;
		
		
		// If it touches any of these, it has reached its goal and will stop.
		for (byte tSide : ALL_SIDES_VALID) if (aBlocks[tSide] == BlocksGT.Ocean || aBlocks[tSide] == BlocksGT.River || aBlocks[tSide] == BlocksGT.Swamp) {
			if (SIDES_INVALID[aSource]) WD.set(aWorld, aX, aY, aZ, NB, 0, 3);
			return;
		}
		
		// No Source for this River Block, so remove it.
		if (SIDES_INVALID[aSource]) {WD.set(aWorld, aX, aY, aZ, NB, 0, 3); return;}
		
		// Stop once you reach the bottom of the Map.
		if (aY <= 0) return;
		
		// We are going down? Carve out Dirt, Gravel, Sand and the likes.
		if (SIDES_BOTTOM[aFlow] && SIDES_HORIZONTAL[aSource]) {
			Block tBlock = WD.block(aWorld, aX+OFFX[aSource], aY-1, aZ+OFFZ[aSource]);
			if (tBlock == Blocks.dirt || tBlock == Blocks.grass || tBlock == Blocks.mycelium || tBlock == Blocks.sand || tBlock == Blocks.gravel || tBlock == Blocks.snow) {
				WD.set(aWorld, aX              , aY  , aZ              , NB  , 0, 3, T);
				if (aBlocks[aSource] == this)
				WD.set(aWorld, aX+OFFX[aSource], aY  , aZ+OFFZ[aSource], this, 1+aFlow        , 3, T);
				WD.set(aWorld, aX+OFFX[aSource], aY-1, aZ+OFFZ[aSource], this, 1+OPOS[aSource], 3, T);
				return;
			}
		}
		
		// Gravity goes down, usually
		if (aBlocks[SIDE_DOWN] != this && goThisWay(aWorld, aX, aY, aZ, SIDE_DOWN)) return;
		
		// Only if the Flow is horizontal in this Area.
		if (SIDES_HORIZONTAL[aSource] && SIDES_HORIZONTAL[aFlow] && aBlocks[aSource] == this) {
		//  // Get rid of all U-Turns. // This one does not work properly.
		//  if (aSource == aMetas[aFlow]-1) {
		//      WD.set(aWorld, aX              , aY, aZ              , NB  , 0, 3, T);
		//      WD.set(aWorld, aX+OFFX[aFlow  ], aY, aZ+OFFZ[aFlow  ], NB  , 0, 3, T);
		//      WD.set(aWorld, aX+OFFX[aSource], aY, aZ+OFFZ[aSource], this, aMeta, 3, T);
		//      return;
		//  }
			// Try forming a diagonal River to speed up the process.
			if (!ALONG_AXIS[aFlow][aSource] && aMetas[aFlow] == aMeta && goThisWay(aWorld, aX+OFFX[aSource], aY, aZ+OFFZ[aSource], aFlow)) {
				WD.set(aWorld, aX, aY, aZ, NB, 0, 3, T);
				WD.set(aWorld, aX+OFFX[aSource]+OFFX[aFlow], aY, aZ+OFFZ[aSource]+OFFZ[aFlow], this, aMetas[aSource], 3, T);
				return;
			}
		}
		
		// Well this is already flowing somewhere, so nothing to change.
		if (SIDES_VALID[aFlow]) return;
		
		// Try flowing into a direction that is most likely to lead downwards in the short term.
		for (byte tSide : ALL_SIDES_HORIZONTAL_ORDER[aY % ALL_SIDES_HORIZONTAL_ORDER.length]) if (aBlocks[tSide] != this && canDisplace(aWorld, aX+OFFX[tSide], aY-1, aZ+OFFZ[tSide]) && goThisWay(aWorld, aX, aY, aZ, tSide)) return;
		
		// Try flowing into a direction that is likely to lead downwards.
		for (byte tSide : ALL_SIDES_HORIZONTAL_ORDER[aY % ALL_SIDES_HORIZONTAL_ORDER.length]) if (aBlocks[tSide] != this && canDisplace(aWorld, aX+OFFX[tSide]*2, aY-1, aZ+OFFZ[tSide]*2) && goThisWay(aWorld, aX, aY, aZ, tSide)) return;
		
		// Try flowing the same direction as surrounding River Blocks
		for (byte tSide : ALL_SIDES_HORIZONTAL) if (aBlocks[tSide] == this && aMetas[tSide] != 0 && aMetas[tSide] <= 6) {
			byte tDir = (byte)(aMetas[tSide]-1);
			if (aBlocks[tDir] != this && goThisWay(aWorld, aX, aY, aZ, tDir)) return;
		}
		
		// select random direction
		for (byte tSide : ALL_SIDES_HORIZONTAL_ORDER[aY % ALL_SIDES_HORIZONTAL_ORDER.length]) if (aBlocks[tSide] != this && goThisWay(aWorld, aX, aY, aZ, tSide)) return;
		
		// Wait we can't go ANYWHERE??? Guess we are not a River anymore then!
		WD.set(aWorld, aX, aY, aZ, Blocks.water, 0, 3, T);
	}
	
	public boolean goThisWay(World aWorld, int aX, int aY, int aZ, byte aSide) {
		return displaceIfPossible(aWorld, aX+OFFX[aSide], aY+OFFY[aSide], aZ+OFFZ[aSide]) && WD.set(aWorld, aX+OFFX[aSide], aY+OFFY[aSide], aZ+OFFZ[aSide], this, 0, 3, T) && WD.set(aWorld, aX, aY, aZ, this, aSide+1, 3, T);
	}
	
	public boolean canDisplace(IBlockAccess aWorld, int aX, int aY, int aZ) {
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == this) return F;
		if (WD.water(aBlock)) return aWorld.getBlockMetadata(aX, aY, aZ) > 0;
		if (aBlock.isAir(aWorld, aX, aY, aZ)) return T;
		if (displacements.containsKey(aBlock)) return displacements.get(aBlock);
		Material aMaterial = aBlock.getMaterial();
		if (aMaterial.blocksMovement() || aMaterial.isLiquid() || aMaterial == Material.portal) return F;
		return T;
	}
	
	public boolean displaceIfPossible(World aWorld, int aX, int aY, int aZ) {
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == this) return F;
		if (WD.water(aBlock)) return aWorld.getBlockMetadata(aX, aY, aZ) > 0;
		if (aBlock.isAir(aWorld, aX, aY, aZ)) return T;
		if (displacements.containsKey(aBlock)) {
			if (displacements.get(aBlock)) {
				aBlock.dropBlockAsItem(aWorld, aX, aY, aZ, aWorld.getBlockMetadata(aX, aY, aZ), 0);
				return T;
			}
			return F;
		}
		Material aMaterial = aBlock.getMaterial();
		if (aMaterial.blocksMovement() || aMaterial.isLiquid() || aMaterial == Material.portal) return F;
		aBlock.dropBlockAsItem(aWorld, aX, aY, aZ, aWorld.getBlockMetadata(aX, aY, aZ), 0);
		return T;
	}
	
	@Override
	public int getQuantaValue(IBlockAccess aWorld, int aX, int aY, int aZ) {
		return quantaPerBlock;
	}
	
	@Override
	public FluidStack drain(World aWorld, int aX, int aY, int aZ, boolean aDoDrain) {
		return FL.Water.make(1000);
	}
}
