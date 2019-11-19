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

package gregapi.oredict.listeners;

import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public interface IOreDictListenerItem {
	public ItemStack onTickWorld        (OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, EntityItem aItem);
	public ItemStack onClickRight       (OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, EntityPlayer aPlayer);
	public void onTickPlayer            (OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, EntityPlayer aPlayer, int aIndex);
	public void onTickInventory         (OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, IInventory aInventory, int aIndex);
	
	/** Returns null if it doesn't provide a ToolTip for this Behaviour. */
	public String getListenerToolTip    (OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack);
	
	/** Contains a Default implementation in case only one of the Events is actually needed. Extend this Class rather than the Interface, for more API-Security. */
	public abstract class OreDictListenerItem implements IOreDictListenerItem {
		@Override
		public ItemStack onTickWorld(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, EntityItem aItem) {
			return aStack;
		}
		
		@Override
		public ItemStack onClickRight(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, EntityPlayer aPlayer) {
			return aStack;
		}
		
		@Override
		public void onTickPlayer(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, EntityPlayer aPlayer, int aIndex) {
			//
		}
		
		@Override
		public void onTickInventory(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack, IInventory aInventory, int aIndex) {
			//
		}
		
		@Override
		public String getListenerToolTip(OreDictPrefix aPrefix, OreDictMaterial aMaterial, ItemStack aStack) {
			return null;
		}
	}
}
