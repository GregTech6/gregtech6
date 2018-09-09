package gregapi.gui;

import static gregapi.data.CS.*;

import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackSet;
import gregapi.tileentity.ITileEntityInventoryGUI;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class Slot_Whitelist extends Slot_Normal {
	private ItemStackSet<ItemStackContainer> mWhiteList = new ItemStackSet();
	
	public Slot_Whitelist(ITileEntityInventoryGUI aInventory, int aIndex, int aX, int aY, ItemStack... aValidStacks) {
		super(aInventory, aIndex, aX, aY);
		for (ItemStack aStack : aValidStacks) mWhiteList.add(aStack);
	}
	
	@Override
	public boolean isItemValid(ItemStack aStack) {
		return super.isItemValid(aStack) && mWhiteList.contains(aStack, T);
	}
}