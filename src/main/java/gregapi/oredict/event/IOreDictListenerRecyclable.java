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

import gregapi.oredict.OreDictItemData;
import gregapi.util.OM;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public interface IOreDictListenerRecyclable {
	public void onRecycleableRegistration(OreDictRecyclingContainer aEvent);
	
	public static class OreDictRecyclingContainer {
		public final OreDictItemData mItemData;
		public final ItemStack mStack;
		
		public OreDictRecyclingContainer(ItemStack aStack, OreDictItemData aItemData) {
			mItemData = aItemData;
			mStack = aStack.copy();
		}
		
		public OreDictRecyclingContainer(OreDictRecyclingContainer aItemData) {
			mItemData = OM.data_(aItemData.mStack);
			mStack = aItemData.mStack.copy();
		}
	}
}
