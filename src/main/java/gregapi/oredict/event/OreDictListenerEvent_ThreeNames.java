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

package gregapi.oredict.event;

import static gregapi.data.CS.*;

import gregapi.oredict.OreDictManager;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 * 
 * Used to process Non-Material based OreDict Stuff faster, like a String switch/case kind of a deal.
 */
public abstract class OreDictListenerEvent_ThreeNames implements IOreDictListenerEvent {
	public final String mName1, mName2, mName3;
	
	public OreDictListenerEvent_ThreeNames(String aName1, String aName2, String aName3) {
		mName1 = aName1;
		mName2 = aName2;
		mName3 = aName3;
	}
	
	public abstract void onOreRegistration(ItemStack aStack1, ItemStack aStack2, ItemStack aStack3);
	
	@Override
	public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		if (mName1.equals(aEvent.mOreDictName)) {
			for (ItemStack tStack2 : OreDictManager.getOres(mName2, F)) {
				for (ItemStack tStack3 : OreDictManager.getOres(mName3, F)) {
					onOreRegistration(aEvent.mStack, tStack2, tStack3);
				}
			}
		} else if (mName2.equals(aEvent.mOreDictName)) {
			for (ItemStack tStack1 : OreDictManager.getOres(mName1, F)) {
				for (ItemStack tStack3 : OreDictManager.getOres(mName3, F)) {
					onOreRegistration(tStack1, aEvent.mStack, tStack3);
				}
			}
		} else {
			for (ItemStack tStack1 : OreDictManager.getOres(mName1, F)) {
				for (ItemStack tStack2 : OreDictManager.getOres(mName2, F)) {
					onOreRegistration(tStack1, tStack2, aEvent.mStack);
				}
			}
		}
	}
}
