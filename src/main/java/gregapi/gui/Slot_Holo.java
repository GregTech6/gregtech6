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

package gregapi.gui;

import static gregapi.data.CS.*;

import gregapi.tileentity.ITileEntityInventoryGUI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class Slot_Holo extends Slot_Base {
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
