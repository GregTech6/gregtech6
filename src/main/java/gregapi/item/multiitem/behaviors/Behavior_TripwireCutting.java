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

package gregapi.item.multiitem.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_TripwireCutting extends AbstractBehaviorDefault {
	private final int mCosts;
	
	public Behavior_TripwireCutting(int aCosts) {
		mCosts = aCosts;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aPlayer.worldObj.isRemote) return F;
		if (aWorld.getBlock(aX, aY, aZ) == Blocks.tripwire) {
			if (((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, T)) {
				int aMeta = aWorld.getBlockMetadata(aX, aY, aZ) | 8;
				aWorld.setBlockMetadataWithNotify(aX, aY, aZ, aMeta, 4);
				if (Blocks.tripwire.removedByPlayer(aWorld, aPlayer, aX, aY, aZ, T)) {
					Blocks.tripwire.onBlockDestroyedByPlayer(aWorld, aX, aY, aZ, aMeta);
					Blocks.tripwire.harvestBlock(aWorld, aPlayer, aX, aY, aZ, aMeta);
					UT.Sounds.send(aWorld, SFX.MC_SHEARS, 1.0F, 1.0F, aX, aY, aZ);
				}
			}
			return T;
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.tripwirecutting", "Can cut Tripwires by Rightclick");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.tripwirecutting"));
		return aList;
	}
}
