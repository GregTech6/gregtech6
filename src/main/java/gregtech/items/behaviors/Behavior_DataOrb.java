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

import java.util.List;

import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Behavior_DataOrb extends AbstractBehaviorDefault {
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		if (!getDataTitle(aStack).equals("")) {
			aList.add(getDataTitle(aStack));
			aList.add(getDataName(aStack));
		}
		return aList;
	}
	
	public static void copyInventory(ItemStack aInventory[], ItemStack aNewContent[], int aIndexlength) {
		for (int i = 0; i < aIndexlength; i++) {
			if (aNewContent[i] == null)
				aInventory[i] = null;
			else
				aInventory[i] = ST.copy(aNewContent[i]);
		}
	}
	
	public static String getDataName(ItemStack aStack) {
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) return "";
		return tNBT.getString("mDataName");
	}
	
	public static String getDataTitle(ItemStack aStack) {
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) return "";
		return tNBT.getString("mDataTitle");
	}
	
	public static NBTTagCompound setDataName(ItemStack aStack, String aDataName) {
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) tNBT = UT.NBT.make();
		tNBT.setString("mDataName", aDataName);
		UT.NBT.set(aStack, tNBT);
		return tNBT;
	}
	
	public static NBTTagCompound setDataTitle(ItemStack aStack, String aDataTitle) {
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) tNBT = UT.NBT.make();
		tNBT.setString("mDataTitle", aDataTitle);
		UT.NBT.set(aStack, tNBT);
		return tNBT;
	}
	
	public static ItemStack[] getNBTInventory(ItemStack aStack) {
		ItemStack[] tInventory = new ItemStack[256];
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) return tInventory;
		
		NBTTagList tNBT_ItemList = tNBT.getTagList("Inventory", 10);
		for (int i = 0; i < tNBT_ItemList.tagCount(); i++) {
			NBTTagCompound tag = tNBT_ItemList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < tInventory.length) {
				tInventory[slot] = ST.load(tag);
			}
		}
		return tInventory;
	}
	
	public static NBTTagCompound setNBTInventory(ItemStack aStack, ItemStack[] aInventory) {
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT == null) tNBT = UT.NBT.make();
		
		NBTTagList tNBT_ItemList = new NBTTagList();
		for (int i = 0; i < aInventory.length; i++) {
			ItemStack stack = aInventory[i];
			if (stack != null) {
				NBTTagCompound tag = UT.NBT.make();
				tag.setByte("Slot", (byte) i);
				tNBT_ItemList.appendTag(ST.save(stack));
			}
		}
		tNBT.setTag("Inventory", tNBT_ItemList);
		UT.NBT.set(aStack, tNBT);
		return tNBT;
	}
}
