/**
 * Copyright (c) 2021 GregTech-6 Team
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

package gregapi.code;

import static gregapi.data.CS.*;

import java.util.HashMap;

import gregapi.GT_API;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class ItemStackMap<K extends ItemStackContainer, V> extends HashMap<ItemStackContainer, V> {
	private static final long serialVersionUID = 1L;
	
	public ItemStackMap() {
		super();
		GT_API.STACKMAPS.add(this);
	}
	
	@Override
	public boolean equals(Object o) {
		return this == o;
	}
	
	public boolean containsKey(long aID, long aMeta, boolean aWildcard) {
		return containsKey(new ItemStackContainer(aID, 1, aMeta)) || (aWildcard && aMeta != W && containsKey(new ItemStackContainer(aID, 1, W)));
	}
	public boolean containsKey(Item aItem, long aMeta, boolean aWildcard) {
		return containsKey(new ItemStackContainer(aItem, 1, aMeta)) || (aWildcard && aMeta != W && containsKey(new ItemStackContainer(aItem, 1, W)));
	}
	public boolean containsKey(Block aBlock, long aMeta, boolean aWildcard) {
		return containsKey(new ItemStackContainer(aBlock, 1, aMeta)) || (aWildcard && aMeta != W && containsKey(new ItemStackContainer(aBlock, 1, W)));
	}
	public boolean containsKey(ItemStack aStack, boolean aWildcard) {
		return containsKey(new ItemStackContainer(aStack)) || (aWildcard && containsKey(new ItemStackContainer(aStack, W)));
	}
	
	public V get(IItemContainer aStack) {
		return get(new ItemStackContainer(aStack.get(1)));
	}
	public V get(ItemStack aStack) {
		return get(new ItemStackContainer(aStack));
	}
	public V get(ItemStack aStack, long aMeta) {
		return get(new ItemStackContainer(aStack, aMeta));
	}
	public V get(long aID, long aMeta) {
		return get(new ItemStackContainer(aID, 1, aMeta));
	}
	public V get(Item aItem, long aMeta) {
		return get(new ItemStackContainer(aItem, 1, aMeta));
	}
	public V get(Block aBlock, long aMeta) {
		return get(new ItemStackContainer(aBlock, 1, aMeta));
	}
	public V get(ModData aMod, String aName, long aMeta) {
		return aMod.mLoaded ? get(new ItemStackContainer(ST.make(aMod, aName, 1, aMeta))) : null;
	}
	
	public V put(IItemContainer aKey, V aValue) {
		return put(aKey.get(1), aValue);
	}
	public V put(ItemStack aKey, V aValue) {
		return put(new ItemStackContainer(aKey), aValue);
	}
	public V put(long aID, long aMeta, V aValue) {
		return put(new ItemStackContainer(aID, 1, aMeta), aValue);
	}
	public V put(Item aItem, long aMeta, V aValue) {
		return put(new ItemStackContainer(aItem, 1, aMeta), aValue);
	}
	public V put(Block aBlock, long aMeta, V aValue) {
		return put(new ItemStackContainer(aBlock, 1, aMeta), aValue);
	}
	public V put(ModData aMod, String aName, long aMeta, V aValue) {
		return put(new ItemStackContainer(ST.make(aMod, aName, 1, aMeta)), aValue);
	}
}
