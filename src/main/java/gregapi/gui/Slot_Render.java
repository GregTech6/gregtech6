package gregapi.gui;

import static gregapi.data.CS.*;

import gregapi.tileentity.ITileEntityInventoryGUI;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class Slot_Render extends Slot_Holo {
	public Slot_Render(ITileEntityInventoryGUI aInventory, int aIndex, int aX, int aY) {
		super(aInventory, aIndex, aX, aY, F, F, 0);
	}
	
	/**
	 * NEI has a nice and "useful" Delete-All Function, which would delete the Content of this Slot. This is here to prevent that.
	 */
	@Override
	public void putStack(ItemStack aStack) {
		if (inventory instanceof TileEntity && ((TileEntity)inventory).getWorldObj().isRemote) {
			inventory.setInventorySlotContents(getSlotIndex(), aStack);
		}
		onSlotChanged();
	}
}