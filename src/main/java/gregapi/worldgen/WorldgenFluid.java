/**
 * Copyright (c) 2019 Gregorius Techneticies
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

package gregapi.worldgen;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenFluid extends WorldgenBlob {
	@SafeVarargs
	public WorldgenFluid(String aName, boolean aDefault, Block aBlock, int aBlockMeta, int aAmount, int aSize, int aProbability, int aMinY, int aMaxY, Collection<String> aBiomeList, boolean aAllowToGenerateinVoid, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aBlock, aBlockMeta, aAmount, aSize, aProbability, aMinY, aMaxY, aBiomeList, aAllowToGenerateinVoid, aLists);
	}
	
	@Override
	public boolean tryPlaceStuff(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		Block tTargetedBlock = aWorld.getBlock(aX, aY, aZ);
		if (tTargetedBlock == mBlock && aWorld.getBlockMetadata(aX, aY, aZ) == mBlockMeta) {
			return T;
		}
		if (WD.bedrock(aWorld, aX, aY, aZ, tTargetedBlock)) {
			return aY >= 1 && aY <= 4 ? aWorld.setBlock(aX, aY, aZ, mBlock, mBlockMeta, 0) : doBedrockStuff(aWorld, aX, aY, aZ, aRandom);
		}
		if (tTargetedBlock == NB || tTargetedBlock.isAir(aWorld, aX, aY, aZ)) {
			return mAllowToGenerateinVoid && aWorld.setBlock(aX, aY, aZ, mBlock, mBlockMeta, 0);
		}
		if (tTargetedBlock == Blocks.dirt || tTargetedBlock == Blocks.soul_sand || WD.ore_stone(tTargetedBlock, (byte)aWorld.getBlockMetadata(aX, aY, aZ))) {
			return aWorld.setBlock(aX, aY, aZ, mBlock, mBlockMeta, 0);
		}
		return F;
	}
	
	public boolean doBedrockStuff(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		return F;
	}
}
