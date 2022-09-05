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
		byte aDir = WD.meta(aWorld, aX, aY, aZ);
		
		Block[] aBlocks = new Block[6]; for (byte tSide : ALL_SIDES_VALID) aBlocks[tSide] = WD.block(aWorld, aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide], T);;
		if (aDir >= aBlocks.length) aDir = SIDE_DOWN;
		
		if (aBlocks[aDir] == BlocksGT.Ocean || aBlocks[aDir] == BlocksGT.River || aBlocks[aDir] == BlocksGT.Swamp) return;
		
		if (aBlocks[aDir] != this && displaceIfPossible(aWorld, aX+OFFX[aDir], aY+OFFY[aDir], aZ+OFFZ[aDir])) {
			WD.set(aWorld, aX+OFFX[aDir], aY+OFFY[aDir], aZ+OFFZ[aDir], this, aDir, 3, T);
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
