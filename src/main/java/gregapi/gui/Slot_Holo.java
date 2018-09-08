package gregapi.gui;

import static gregapi.data.CS.*;

import gregapi.tileentity.ITileEntityInventoryGUI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class Slot_Holo extends Slot_Normal {
	public boolean mCanInsertItem, mCanStackItem;
	public int mMaxStacksize = 127;
	
	public Slot_Holo(ITileEntityInventoryGUI aInventory, int aIndex, int aX, int aY, boolean aCanInsertItem, boolean aCanStackItem, int aMaxStacksize) {
		super(aInventory, aIndex, aX, aY);
		mCanInsertItem = aCanInsertItem;
		mCanStackItem = aCanStackItem;
		mMaxStacksize = aMaxStacksize;
	}
	
	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		return mCanInsertItem;
	}
	
	@Override
	public int getSlotStackLimit() {
		return mMaxStacksize;
	}
	
	@Override
	public boolean getHasStack() {
		return F;
	}
	
	@Override
	public ItemStack decrStackSize(int par1) {
		if (!mCanStackItem) return null;
		return super.decrStackSize(par1);
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
		return F;
	}
}
