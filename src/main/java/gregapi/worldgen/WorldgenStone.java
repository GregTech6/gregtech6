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

import gregapi.block.IBlockExtendedMetaData;
import gregapi.block.IBlockPlacable;
import gregapi.code.ItemStackContainer;
import gregapi.data.CS.BlocksGT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStone extends WorldgenBlob {
	@SafeVarargs
	public WorldgenStone(String aName, boolean aDefault, Block aBlock, int aBlockMeta, int aAmount, int aSize, int aProbability, int aMinY, int aMaxY, Collection<String> aBiomeList, boolean aAllowToGenerateinVoid, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aBlock, aBlockMeta, aAmount, aSize, aProbability, aMinY, aMaxY, aBiomeList, aAllowToGenerateinVoid, aLists);
	}
	
	@Override
	public boolean tryPlaceStuff(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		Block tTargetedBlock = aWorld.getBlock(aX, aY, aZ);
		if (tTargetedBlock == NB || tTargetedBlock.isAir(aWorld, aX, aY, aZ)) {
			return mAllowToGenerateinVoid && aWorld.setBlock(aX, aY, aZ, mBlock, mBlockMeta, 0);
		}
		if (tTargetedBlock instanceof IBlockExtendedMetaData) {
			return overrideBlock((IBlockExtendedMetaData)tTargetedBlock, aWorld, aX, aY, aZ);
		}
		return (tTargetedBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.stone) || tTargetedBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.end_stone) || tTargetedBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.netherrack)) && aWorld.setBlock(aX, aY, aZ, mBlock, mBlockMeta, 0);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	private boolean overrideBlock(IBlockExtendedMetaData aBlock, World aWorld, int aX, int aY, int aZ) {
		if (!BlocksGT.stoneOverridable.contains(aBlock)) return F;
		short aID = aBlock.getExtendedMetaData(aWorld, aX, aY, aZ);
		IBlockPlacable tBlock = null;
		if (BlocksGT.stoneToNormalOres.values().contains(aBlock)) {
			tBlock = BlocksGT.stoneToNormalOres.get(new ItemStackContainer(mBlock, 1, mBlockMeta));
		} else if (BlocksGT.stoneToSmallOres.values().contains(aBlock)) {
			tBlock = BlocksGT.stoneToSmallOres.get(new ItemStackContainer(mBlock, 1, mBlockMeta));
		} else if (BlocksGT.stoneToBrokenOres.values().contains(aBlock)) {
			tBlock = BlocksGT.stoneToBrokenOres.get(new ItemStackContainer(mBlock, 1, mBlockMeta));
		}
		return tBlock != null && tBlock.placeBlock(aWorld, aX, aY, aZ, SIDE_ANY, aID, null, F, T);
	}
}
