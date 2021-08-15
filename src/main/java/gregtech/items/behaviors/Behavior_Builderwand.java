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

package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import gregapi.block.metatype.BlockMetaType;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Builderwand extends AbstractBehaviorDefault {
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || aPlayer == null || !(aItem instanceof MultiItemTool) || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack) || WD.te(aWorld, aX, aY, aZ, T) != null) return F;
		
		Block aBlock = WD.block(aWorld, aX, aY, aZ, T);
		byte aMeta = WD.meta(aWorld, aX, aY, aZ, T);
		
		if (ST.invalid(aBlock)) return F;
		
		int tDist = (MultiItemTool.getPrimaryMaterial(aStack).mToolQuality+1);
		boolean rReturn = F;
		
		for (int tX = (SIDES_AXIS_X[aSide]?0:-tDist); tX <= (SIDES_AXIS_X[aSide]?0:tDist); tX++)
		for (int tY = (SIDES_AXIS_Y[aSide]?0:-tDist); tY <= (SIDES_AXIS_Y[aSide]?0:tDist); tY++)
		for (int tZ = (SIDES_AXIS_Z[aSide]?0:-tDist); tZ <= (SIDES_AXIS_Z[aSide]?0:tDist); tZ++)
		if (aBlock == WD.block(aWorld, aX+tX, aY+tY, aZ+tZ, T) && aMeta == WD.meta(aWorld, aX+tX, aY+tY, aZ+tZ, T)) {
			// Doublechecking Wand Permissions at that location.
			if (!aPlayer.canPlayerEdit(aX+tX            , aY+tY            , aZ+tZ            , aSide, aStack)) continue;
			if (!aPlayer.canPlayerEdit(aX+tX+OFFX[aSide], aY+tY+OFFY[aSide], aZ+tZ+OFFZ[aSide], aSide, aStack)) continue;
			// Scan Inventory for equal Blocks.
			for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
				ItemStack tStack = aPlayer.inventory.mainInventory[aPlayer.inventory.mainInventory.length-i-1];
				if (ST.invalid(tStack)) continue;
				Block tBlock = ST.block(tStack);
				if (ST.invalid(tBlock)) {
					if (tStack.getItem() instanceof ItemBlock) {
						tBlock = ((ItemBlock)tStack.getItem()).field_150939_a;
						if (ST.invalid(tBlock)) continue;
					} else continue;
				}
				
				if (aBlock == tBlock) {
					if (aMeta != ST.meta(tStack)) continue;
				} else if (aBlock instanceof BlockMetaType && tBlock instanceof BlockMetaType) {
					// This makes sure that GT Slabs can be placed with this Wand.
					if (aMeta != ST.meta(tStack)) continue;
					if (((BlockMetaType)aBlock).mBlock   != ((BlockMetaType)tBlock).mBlock  ) continue;
					if (((BlockMetaType)aBlock).mIsWall  != ((BlockMetaType)tBlock).mIsWall ) continue;
					if (((BlockMetaType)aBlock).mIsSlab  != ((BlockMetaType)tBlock).mIsSlab ) continue;
					if (((BlockMetaType)aBlock).mIsStair != ((BlockMetaType)tBlock).mIsStair) continue;
				} else continue;
				// Doublechecking Block Permissions at that location.
				if (!aPlayer.canPlayerEdit(aX+tX            , aY+tY            , aZ+tZ            , aSide, tStack)) continue;
				if (!aPlayer.canPlayerEdit(aX+tX+OFFX[aSide], aY+tY+OFFY[aSide], aZ+tZ+OFFZ[aSide], aSide, tStack)) continue;
				
				int tOldSize = tStack.stackSize;
				if (tStack.tryPlaceItemIntoWorld(aPlayer, aWorld, aX+tX, aY+tY, aZ+tZ, aSide, aHitX, aHitY, aHitZ)) {
					if (UT.Entities.hasInfiniteItems(aPlayer)) {
						tStack.stackSize = tOldSize;
					} else {
						ST.use(aPlayer, T, tStack, 0);
						((MultiItemTool)aItem).doDamage(aStack, 1, aPlayer, T);
					}
					rReturn = T;
				}
				break;
			}
		}
		return rReturn;
	}
}
