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