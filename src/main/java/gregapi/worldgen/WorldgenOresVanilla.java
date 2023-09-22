/**
 * Copyright (c) 2023 GregTech-6 Team
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

import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import static gregapi.data.CS.NB;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenOresVanilla extends WorldgenBlob {
	public final Block mReplaceBlock;
	public final byte mReplaceMeta;
	
	@SafeVarargs
	public WorldgenOresVanilla(String aName, boolean aDefault, Block aBlock, int aBlockMeta, int aAmount, int aSize, int aProbability, int aMinY, int aMaxY, Block aReplaceBlock, int aReplaceMeta, Collection<String> aBiomeList, boolean aAllowToGenerateinVoid, List<WorldgenObject>... aLists) {
		super(aName, aDefault, aBlock, aBlockMeta, aAmount, aSize, aProbability, aMinY, aMaxY, aBiomeList, aAllowToGenerateinVoid, aLists);
		mReplaceBlock = aReplaceBlock;
		mReplaceMeta  = UT.Code.bind4(aReplaceMeta);
	}
	
	@Override
	public boolean tryPlaceStuff(World aWorld, int aX, int aY, int aZ, Random aRandom) {
		Block tTargetedBlock = aWorld.getBlock(aX, aY, aZ);
		if (tTargetedBlock == NB || tTargetedBlock.isAir(aWorld, aX, aY, aZ)) return mAllowToGenerateinVoid && aWorld.setBlock(aX, aY, aZ, mBlock, mBlockMeta, 0);
		return (mReplaceBlock == null ? !ST.isGT(tTargetedBlock) && tTargetedBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.stone) : tTargetedBlock == mReplaceBlock && WD.meta(aWorld, aX, aY, aZ) == mReplaceMeta) && aWorld.setBlock(aX, aY, aZ, mBlock, mBlockMeta, 0);
	}
}
