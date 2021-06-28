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

package gregapi.block.tree;

import static gregapi.data.CS.*;

import gregapi.block.BlockBaseMeta;
import gregapi.data.MD;
import gregapi.render.IIconContainer;
import mods.railcraft.common.carts.EntityTunnelBore;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public abstract class BlockBaseTree extends BlockBaseMeta {
	public BlockBaseTree(Class<? extends ItemBlock> aItemClass, String aNameInternal, Material aMaterial, SoundType aSoundType, long aMaxMeta, IIconContainer[] aIcons) {
		super(aItemClass, aNameInternal, aMaterial, aSoundType, aMaxMeta, aIcons);
		if (MD.RC.mLoaded) try {EntityTunnelBore.addMineableBlock(this);} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	public abstract int getLeavesRangeSide(byte aMeta);
	public abstract int getLeavesRangeYPos(byte aMeta);
	public abstract int getLeavesRangeYNeg(byte aMeta);
	
	@Override
	public void breakBlock(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMeta) {
		int tRangeSide = getLeavesRangeSide((byte)aMeta)+1, tRangeYNeg = getLeavesRangeYNeg((byte)aMeta)+1, tRangeYPos = getLeavesRangeYPos((byte)aMeta)+1;
		if (!aWorld.isRemote && aWorld.checkChunksExist(aX - tRangeSide, aY - tRangeYNeg, aZ - tRangeSide, aX + tRangeSide, aY + tRangeYPos, aZ + tRangeSide)) {
			tRangeSide--; tRangeYNeg--; tRangeYPos--;
			for (int i = -tRangeSide; i <= tRangeSide; ++i) for (int j = -tRangeYNeg; j <= tRangeYPos; ++j) for (int k = -tRangeSide; k <= tRangeSide; ++k) {
				Block tBlock = aWorld.getBlock(aX + i, aY + j, aZ + k);
				if (tBlock.isLeaves(aWorld, aX + i, aY + j, aZ + k)) tBlock.beginLeavesDecay(aWorld, aX + i, aY + j, aZ + k);
			}
		}
	}
}
