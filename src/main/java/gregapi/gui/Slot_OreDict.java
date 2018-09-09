package gregapi.gui;

import gregapi.tileentity.ITileEntityInventoryGUI;
import gregapi.util.OM;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class Slot_OreDict extends Slot_Normal {
	private final String mWhiteList;
	
	public Slot_OreDict(ITileEntityInventoryGUI aInventory, int aIndex, int aX, int aY, String aValidOreDict) {
		super(aInventory, aIndex, aX, aY);
		mWhiteList = aValidOreDict;
	}
	
	@Override
	public boolean isItemValid(ItemStack aStack) {
		return super.isItemValid(aStack) && OM.is(mWhiteList, aStack);
	}
}