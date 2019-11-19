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

package gregapi.tileentity.multiblocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public interface IMultiBlockInventory extends ITileEntityMultiBlockController {
	public int[] getAccessibleSlotsFromSide     (MultiTileEntityMultiBlockPart aPart, byte aSide);
	public boolean canInsertItem                (MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack, byte aSide);
	public boolean canExtractItem               (MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack, byte aSide);
	public int getSizeInventory                 (MultiTileEntityMultiBlockPart aPart);
	public ItemStack getStackInSlot             (MultiTileEntityMultiBlockPart aPart, int aSlot);
	public ItemStack decrStackSize              (MultiTileEntityMultiBlockPart aPart, int aSlot, int aDecrement);
	public ItemStack getStackInSlotOnClosing    (MultiTileEntityMultiBlockPart aPart, int aSlot);
	public void setInventorySlotContents        (MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack);
	public String getInventoryName              (MultiTileEntityMultiBlockPart aPart);
	public boolean hasCustomInventoryName       (MultiTileEntityMultiBlockPart aPart);
	public int getInventoryStackLimit           (MultiTileEntityMultiBlockPart aPart);
	public void markDirty                       (MultiTileEntityMultiBlockPart aPart);
	public boolean isUseableByPlayer            (MultiTileEntityMultiBlockPart aPart, EntityPlayer aPlayer);
	public void openInventory                   (MultiTileEntityMultiBlockPart aPart);
	public void closeInventory                  (MultiTileEntityMultiBlockPart aPart);
	public boolean isItemValidForSlot           (MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack);
}
