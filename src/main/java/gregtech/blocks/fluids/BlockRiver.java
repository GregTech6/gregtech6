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

package gregtech.blocks.fluids;

import static gregapi.data.CS.*;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.data.FL;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class BlockRiver extends BlockWaterlike {
	public static boolean PLACEMENT_ALLOWED = F;
	
	public BlockRiver(String aName, Fluid aFluid) {
		super(aName, aFluid);
		tickRate = 20;
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
	public void onNeighborBlockChange(World aWorld, int aX, int aY, int aZ, Block aBlock) {
		if (aBlock == Blocks.dirt && aWorld.getBlock(aX, aY-1, aZ) == Blocks.grass) aWorld.setBlock(aX, aY-1, aZ, Blocks.dirt, 1, 2);
		super.onNeighborBlockChange(aWorld, aX, aY, aZ, aBlock);
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
		if (WD.meta(aWorld, aX, aY, aZ) != 0) {
			byte tRiverCounter = 0;
			for (byte tSide : ALL_SIDES_HORIZONTAL) if (WD.block(aWorld, aX, aY, aZ, tSide) == this && WD.meta(aWorld, aX, aY, aZ, tSide) == 0) tRiverCounter++;
			if (tRiverCounter >= 2) aWorld.setBlock(aX, aY, aZ, this, 0, 2);
		}
		updateFlow(aWorld, aX, aY, aZ, aRandom);
		PLACEMENT_ALLOWED = F;
		return;
	}
	
	@Override
	public FluidStack drain(World aWorld, int aX, int aY, int aZ, boolean aDoDrain) {
		if (!isSourceBlock(aWorld, aX, aY, aZ)) return null;
		if (aDoDrain) aWorld.setBlockToAir(aX, aY, aZ);
		return FL.Water.make(1000);
	}
	
	@Override public int getLightOpacity(IBlockAccess aWorld, int aX, int aY, int aZ) {return LIGHT_OPACITY_WATER;}
	@Override public IIcon getIcon(int aSide, int aMeta) {return Blocks.water.getIcon(aSide, aMeta);}
	@Override @SideOnly(Side.CLIENT) public int getRenderColor(int aMeta) {return 0x00ffffff;}
	@Override @SideOnly(Side.CLIENT) public int colorMultiplier(IBlockAccess aWorld, int aX, int aY, int aZ) {return 0x00ffffff;}
}
