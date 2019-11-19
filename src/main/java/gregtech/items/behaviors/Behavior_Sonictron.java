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

import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class Behavior_Sonictron extends AbstractBehaviorDefault {
	public static final IBehavior<MultiItem> INSTANCE = new Behavior_Sonictron();
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		/*if (!aWorld.isRemote && aWorld.getBlock(aX, aY, aZ) == GregTech_API.sBlockMachines && aWorld.getBlockMetadata(aX, aY, aZ) == 6) {
			
			GT_TileEntity_Sonictron tSonictron = (GT_TileEntity_Sonictron)aWorld.getTileEntity(aX, aY, aZ);
			if (tSonictron != null) {
				ItemStack[] tInventory = getNBTInventory(aStack);
				if (aPlayer.isSneaking()) {
					copyInventory(tSonictron.mInventory, tInventory, 64);
				} else {
					copyInventory(tInventory, tSonictron.mInventory, 64);
				}
				setNBTInventory(aStack, tInventory);
				tSonictron.sendClientData = true;
				return true;
			}
			
		}*/
		setCurrentIndex(aStack, -1);
		return false;
	}
	
	@Override
	public ItemStack onItemRightClick(MultiItem aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		setCurrentIndex(aStack, 0);
		return aStack;
	}
	
	@Override
	public void onUpdate(MultiItem aItem, ItemStack aStack, World aWorld, Entity aPlayer, int aTimer, boolean aIsInHand) {
		int tTickTimer      = getTickTimer(aStack),
			tCurrentIndex   = getCurrentIndex(aStack);
			
		if (tTickTimer++%2==0&&tCurrentIndex>-1) {
			//ItemStack[] tInventory = getNBTInventory(aStack);
			//GT.doSonictronSound(tInventory[tCurrentIndex], aPlayer.worldObj, aPlayer.posX, aPlayer.posY, aPlayer.posZ);
			if (++tCurrentIndex>63) tCurrentIndex=-1;
		}
		
		setTickTimer(aStack, tTickTimer);
		setCurrentIndex(aStack, tCurrentIndex);
	}
	
	public static int getCurrentIndex(ItemStack aStack) {
		NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
		if (tNBTTagCompound == null) tNBTTagCompound = UT.NBT.make();
		return tNBTTagCompound.getInteger("mCurrentIndex");
	}

	public static int getTickTimer(ItemStack aStack) {
		NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
		if (tNBTTagCompound == null) tNBTTagCompound = UT.NBT.make();
		return tNBTTagCompound.getInteger("mTickTimer");
	}

	public static NBTTagCompound setCurrentIndex(ItemStack aStack, int aIndex) {
		NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
		if (tNBTTagCompound == null) tNBTTagCompound = UT.NBT.make();
		tNBTTagCompound.setInteger("mCurrentIndex", aIndex);
		return tNBTTagCompound;
	}

	public static NBTTagCompound setTickTimer(ItemStack aStack, int aTime) {
		NBTTagCompound tNBTTagCompound = aStack.getTagCompound();
		if (tNBTTagCompound == null) tNBTTagCompound = UT.NBT.make();
		tNBTTagCompound.setInteger("mTickTimer", aTime);
		return tNBTTagCompound;
	}
	
	public static ItemStack[] getNBTInventory(ItemStack aStack) {
		ItemStack[] tInventory = new ItemStack[64];
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
	
	public static void copyInventory(ItemStack aInventory[], ItemStack aNewContent[], int aIndexlength) {
		for (int i = 0; i < aIndexlength; i++) {
			if (aNewContent[i] == null)
				aInventory[i] = null;
			else
				aInventory[i] = ST.copy(aNewContent[i]);
		}
	}
}
