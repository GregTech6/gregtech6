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

import gregapi.data.OP;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Place_Sapling extends AbstractBehaviorDefault {
	public static final Behavior_Place_Sapling INSTANCE = new Behavior_Place_Sapling();
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || aPlayer == null || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack) || SIDES_BOTTOM_HORIZONTAL[aSide]) return F;
		
		// Scan Inventory for suitable Saplings.
		for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
			int tIndex = aPlayer.inventory.mainInventory.length-i-1;
			ItemStack tStack = aPlayer.inventory.mainInventory[tIndex];
			// Any OreDict Saplings.
			if (!OP.treeSapling.contains(tStack)) continue;
			
			int tOldSize = tStack.stackSize;
			if (tStack.tryPlaceItemIntoWorld(aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ)) {
				if (UT.Entities.hasInfiniteItems(aPlayer)) {
					tStack.stackSize = tOldSize;
				} else {
					ST.use(aPlayer, tIndex, tStack, 0);
				}
				return T;
			}
			return F;
		}
		return F;
	}
}
