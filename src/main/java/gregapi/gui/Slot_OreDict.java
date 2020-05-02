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

import gregapi.tileentity.ITileEntityInventoryGUI;
import gregapi.util.OM;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class Slot_OreDict extends Slot_Base {
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
