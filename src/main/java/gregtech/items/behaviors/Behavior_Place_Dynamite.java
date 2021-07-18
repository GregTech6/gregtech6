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

import java.util.List;

import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Behavior_Place_Dynamite extends AbstractBehaviorDefault {
	public static final Behavior_Place_Dynamite INSTANCE = new Behavior_Place_Dynamite();
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || aPlayer == null || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		if (WD.ore_stone(aWorld.getBlock(aX, aY, aZ), (short)aWorld.getBlockMetadata(aX, aY, aZ))) for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
			ItemStack tStack = aPlayer.inventory.mainInventory[aPlayer.inventory.mainInventory.length-i-1];
			if (IL.Boomstick.equal(tStack, F, T) || IL.Dynamite.equal(tStack, F, T) || IL.Dynamite_Strong.equal(tStack, F, T)) {
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
						ST.use(aPlayer, T, tStack, 0);
					}
					tStack.setTagCompound(tOldTag);
					// Add Dynamite Coords to Remote Activator if in Hotbar.
					for (int j = 0; j < InventoryPlayer.getHotbarSize(); j++) if (IL.Tool_Remote_Activator.equal(aPlayer.inventory.mainInventory[j], F, T)) {
						if (Behavior_Remote.addCoords(aPlayer.inventory.mainInventory[j], aPlayer, aWorld, aX+OFFX[aSide], aY+OFFY[aSide], aZ+OFFZ[aSide])) {
							break;
						}
					}
					return T;
				}
				tStack.setTagCompound(tOldTag);
			}
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.placedynamite", "Places Dynamite and links it to Remote Activators in Hotbar");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.placedynamite"));
		return aList;
	}
}
