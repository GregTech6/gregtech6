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

import java.util.Collection;
import java.util.List;
import java.util.Random;

import gregapi.worldgen.WorldgenFluid;
import gregapi.worldgen.WorldgenObject;
import gregtech.tileentity.misc.MultiTileEntityFluidSpring;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenFluidSpring extends WorldgenFluid {
	public final FluidStack mSpringFluid;
	public final int mSpringChance;
	
	@SafeVarargs
	public WorldgenFluidSpring(String aName, boolean aDefault, Block aBlock, int aBlockMeta, int aAmount, int aSize, int aProbability, int aMinY, int aMaxY, Collection<String> aBiomeList, boolean aAllowToGenerateinVoid, FluidStack aSpringFluid, int aSpringChance, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aBlock, aBlockMeta, aAmount, aSize, aProbability, aMinY, aMaxY, aBiomeList, aAllowToGenerateinVoid, aLists);
		mSpringFluid = aSpringFluid;
		mSpringChance = aSpringChance;
	}
	
	@Override
	public boolean doBedrockStuff(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		if (aY == 0 && mSpringFluid != null && aRandom.nextInt(mSpringChance) == 0 && MultiTileEntityFluidSpring.setBlock(aWorld, aX, aY, aZ, mSpringFluid) && aWorld.setBlock(aX, aY+1, aZ, mBlock, mBlockMeta, 0)) {
			return T;
		}
		return F;
	}
}
