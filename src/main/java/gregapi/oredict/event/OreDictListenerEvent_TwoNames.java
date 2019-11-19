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
public abstract class OreDictListenerEvent_TwoNames implements IOreDictListenerEvent {
	public final String mName1, mName2;
	
	public OreDictListenerEvent_TwoNames(Object aName1, Object aName2) {
		mName1 = aName1.toString();
		mName2 = aName2.toString();
	}
	
	public abstract void onOreRegistration(ItemStack aStack1, ItemStack aStack2);
	
	@Override
	public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		if (mName1.equals(aEvent.mOreDictName)) {
			for (ItemStack tStack : OreDictManager.getOres(mName2, F)) {
				onOreRegistration(aEvent.mStack, tStack);
			}
		} else {
			for (ItemStack tStack : OreDictManager.getOres(mName1, F)) {
				onOreRegistration(tStack, aEvent.mStack);
			}
		}
	}
}
