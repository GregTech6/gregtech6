package gregapi.gui;

import gregapi.tileentity.ITileEntityInventoryGUI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class Slot_Armor extends Slot_Normal {
	final int mArmorType;
	final EntityPlayer mPlayer;
	
	public Slot_Armor(ITileEntityInventoryGUI aInventory, int aIndex, int aX, int aY, int aArmor, EntityPlayer aPlayer) {
		super(aInventory, aIndex, aX, aY);
		mArmorType = aArmor;
		mPlayer = aPlayer;
	}
	
	@Override
	public int getSlotStackLimit() {
		return 1;
	}
	
	@Override
	public boolean isItemValid(ItemStack aStack) {
		return aStack != null && aStack.getItem() != null && aStack.getItem().isValidArmor(aStack, mArmorType, mPlayer);
	}
}
