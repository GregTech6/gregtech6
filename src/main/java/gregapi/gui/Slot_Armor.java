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
