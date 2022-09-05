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
		tickRate = 20;
	}
	
	@Override
	public void onBlockAdded(World aWorld, int aX, int aY, int aZ) {
		aWorld.scheduleBlockUpdate(aX, aY, aZ, this, tickRate);
	}
	
	@Override
	public void updateTick(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		// Scan surroundings.
		Block[] aBlocks  = new Block[6];
		byte [] aMetas   = new byte [6];
		boolean aInvalid = T;
		for (byte tSide : ALL_SIDES_VALID) {
			aMetas [tSide] = WD.meta (aWorld, aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide], T);
			aBlocks[tSide] = WD.block(aWorld, aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide], T);
			// Check if this River Block has a Source.
			if (aBlocks[tSide] == this && aMetas[tSide]-1 == OPOS[tSide]) aInvalid = F;
			if (aBlocks[tSide] == Blocks.bedrock) aInvalid = F; // TODO: Remove this if this River Block ever gets used!
		};
		
		// Well this is already flowing somewhere, so nothing to change.
		if (aBlocks[WD.meta(aWorld, aX, aY, aZ, T) % aBlocks.length] == this) {
			if (aInvalid) WD.set(aWorld, aX, aY, aZ, NB, 0, 3);
			return;
		}
		
		// If it touches any of these, it has reached its goal and will stop.
		for (byte tSide : ALL_SIDES_VALID) if (aBlocks[tSide] == BlocksGT.Ocean || aBlocks[tSide] == BlocksGT.River || aBlocks[tSide] == BlocksGT.Swamp) {
			if (aInvalid) WD.set(aWorld, aX, aY, aZ, NB, 0, 3);
			return;
		}
		
		// No Source for this River Block, so remove it.
		if (aInvalid) WD.set(aWorld, aX, aY, aZ, NB, 0, 3);
		
		// gravity goes down, usually
		byte tDir = SIDE_DOWN;
		if (aBlocks[tDir] != this && displaceIfPossible(aWorld, aX+OFFX[tDir], aY+OFFY[tDir], aZ+OFFZ[tDir])) {
			WD.set(aWorld, aX+OFFX[tDir], aY+OFFY[tDir], aZ+OFFZ[tDir], this,      0, 3, T);
			WD.set(aWorld, aX           , aY           , aZ           , this, tDir+1, 3, T);
			return;
		}
		
		// try flowing straight
		for (byte tSide : ALL_SIDES_HORIZONTAL) if (aBlocks[tSide] == this) if (aMetas[tSide]-1 == OPOS[tSide]) {
			tDir = (byte)(aMetas[tSide]-1);
			if (aBlocks[tDir] != this && displaceIfPossible(aWorld, aX+OFFX[tDir], aY+OFFY[tDir], aZ+OFFZ[tDir])) {
				WD.set(aWorld, aX+OFFX[tDir], aY+OFFY[tDir], aZ+OFFZ[tDir], this,      0, 3, T);
				WD.set(aWorld, aX           , aY           , aZ           , this, tDir+1, 3, T);
				return;
			}
			break;
		}
		
		// select random direction
		for (byte tSide : ALL_SIDES_HORIZONTAL_ORDER[RNGSUS.nextInt(ALL_SIDES_HORIZONTAL_ORDER.length)]) if (tDir != tSide) {
			if (aBlocks[tSide] != this && displaceIfPossible(aWorld, aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide])) {
				WD.set(aWorld, aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide], this,       0, 3, T);
				WD.set(aWorld, aX            , aY            , aZ            , this, tSide+1, 3, T);
				return;
			}
		}
		
		// Well, then go upwards instead!
		tDir = SIDE_UP;
		if (aBlocks[tDir] != this && displaceIfPossible(aWorld, aX+OFFX[tDir], aY+OFFY[tDir], aZ+OFFZ[tDir])) {
			WD.set(aWorld, aX+OFFX[tDir], aY+OFFY[tDir], aZ+OFFZ[tDir], this,      0, 3, T);
			WD.set(aWorld, aX           , aY           , aZ           , this, tDir+1, 3, T);
			return;
		}
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
