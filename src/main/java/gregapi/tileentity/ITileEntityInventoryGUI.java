package gregapi.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityInventoryGUI extends ITileEntityUnloadable {
	/**
	 * Returns the number of slots in the inventory.
	 */
	int getSizeInventoryGUI();
	
	/**
	 * Returns the stack in slot i
	 */
	ItemStack getStackInSlotGUI(int aSlot);
	
	/**
	 * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
	 * new stack.
	 */
	ItemStack decrStackSizeGUI(int aSlot, int aDecrement);
	
	/**
	 * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
	 * like when you close a workbench GUI.
	 */
	ItemStack getStackInSlotOnClosingGUI(int aSlot);
	
	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 */
	void setInventorySlotContentsGUI(int aSlot, ItemStack aStack);
	
	/**
	 * Returns the name of the inventory
	 */
	String getInventoryNameGUI();
	
	/**
	 * Returns if the inventory is named
	 */
	boolean hasCustomInventoryNameGUI();
	
	/**
	 * Returns the maximum stack size for a inventory slot.
	 */
	int getInventoryStackLimitGUI(int aSlot);
	
	/**
	 * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
	 * hasn't changed and skip it.
	 */
	void markDirtyGUI();
	
	/**
	 * Do not make give this method the name canInteractWith because it clashes with Container
	 */
	boolean isUseableByPlayerGUI(EntityPlayer aPlayer);
	
	void openInventoryGUI();
	
	void closeInventoryGUI();
	
	/**
	 * Returns true if GUI is allowed to insert the given stack (ignoring stack size) into the given slot.
	 */
	boolean isItemValidForSlotGUI(int aSlot, ItemStack aStack);
	
	/**
	 * Returns true if you can take an Item out of this Slot.
	 */
	boolean canTakeOutOfSlotGUI(int aSlot);
}