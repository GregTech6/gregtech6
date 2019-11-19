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

package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import gregapi.data.IL;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Behavior_Place_Dynamite extends AbstractBehaviorDefault {
	public static final Behavior_Place_Dynamite INSTANCE = new Behavior_Place_Dynamite();
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || aPlayer == null || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		if (WD.ore_stone(aWorld.getBlock(aX, aY, aZ), (short)aWorld.getBlockMetadata(aX, aY, aZ))) for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
			int tIndex = aPlayer.inventory.mainInventory.length-i-1;
			ItemStack tStack = aPlayer.inventory.mainInventory[tIndex];
			if (IL.Dynamite.equal(tStack, F, T) || IL.Dynamite_Strong.equal(tStack, F, T)) {
				NBTTagCompound tOldTag = tStack.getTagCompound();
				if (tStack.hasTagCompound()) {
					tStack.setTagCompound((NBTTagCompound)tStack.getTagCompound().copy());
				} else {
					tStack.setTagCompound(UT.NBT.make());
				}
				tStack.getTagCompound().setBoolean(NBT_MODE, T);
				int tOldSize = tStack.stackSize;
				if (tStack.tryPlaceItemIntoWorld(aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ)) {
					if (UT.Entities.hasInfiniteItems(aPlayer)) {
						tStack.stackSize = tOldSize;
					} else {
						((MultiItemTool)aItem).doDamage(aStack, 100, aPlayer);
						ST.use(aPlayer, tIndex, tStack, 0);
					}
					tStack.setTagCompound(tOldTag);
					return T;
				}
				tStack.setTagCompound(tOldTag);
			}
		}
		return F;
	}
}
