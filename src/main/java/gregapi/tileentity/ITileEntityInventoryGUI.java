/**
 * Copyright (c) 2020 GregTech-6 Team
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

package gregapi.tileentity;

import gregapi.gui.Slot_Base;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityInventoryGUI extends ITileEntityUnloadable {
	/**
	 * @return the number of slots in the inventory.
	 */
	public int getSizeInventoryGUI();
	
	/**
	 * @return the stack in slot i
	 */
	public ItemStack getStackInSlotGUI(int aSlot);
	
	/**
	 * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
	 * new stack.
	 */
	public ItemStack decrStackSizeGUI(int aSlot, int aDecrement);
	
	/**
	 * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
	 * like when you close a workbench GUI.
	 */
	public ItemStack getStackInSlotOnClosingGUI(int aSlot);
	
	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 */
	public void setInventorySlotContentsGUI(int aSlot, ItemStack aStack);
	
	/**
	 * Returns the name of the inventory
	 */
	public String getInventoryNameGUI();
	
	/**
	 * Returns if the inventory is named
	 */
	public boolean hasCustomInventoryNameGUI();
	
	/**
	 * Returns the maximum stack size for a inventory slot.
	 */
	public int getInventoryStackLimitGUI(int aSlot);
	
	/**
	 * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
	 * hasn't changed and skip it.
	 */
	public void markDirtyGUI();
	
	/**
	 * @return true to intercept the regular Click Behaviour.
	 */
	public boolean interceptClick(int aGUIID, Slot_Base aSlot, int aSlotIndex, int aInvSlot, EntityPlayer aPlayer, boolean aShiftclick, boolean aRightclick, int aMouse, int aShift);
	
	/**
	 * only called when interceptClick returns true
	 */
	public ItemStack slotClick(int aGUIID, Slot_Base aSlot, int aSlotIndex, int aInvSlot, EntityPlayer aPlayer, boolean aShiftclick, boolean aRightclick, int aMouse, int aShift);
	
	/**
	 * Do not make give this method the name canInteractWith because it clashes with Container
	 */
	public boolean isUseableByPlayerGUI(EntityPlayer aPlayer);
	
	public void openInventoryGUI();
	
	public void closeInventoryGUI();
	
	/**
	 * @return true if GUI is allowed to insert the given stack (ignoring stack size) into the given slot.
	 */
	public boolean isItemValidForSlotGUI(int aSlot, ItemStack aStack);
	
	/**
	 * @return true if you can take an Item out of this Slot.
	 */
	public boolean canTakeOutOfSlotGUI(int aSlot);
	
	/**
	 * @return true if all Slots are compromised and need resync.
	 */
	public boolean needsToSyncEverything();
}
