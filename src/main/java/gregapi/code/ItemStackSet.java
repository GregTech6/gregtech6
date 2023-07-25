/**
 * Copyright (c) 2023 GregTech-6 Team
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

import gregapi.GT_API;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;

import static gregapi.data.CS.W;

/**
 * @author Gregorius Techneticies
 */
public class ItemStackSet<E extends ItemStackContainer> extends AbstractSet<ItemStackContainer> {
	private final ItemStackMap<ItemStackContainer, Object> mMap;
	private static final Object OBJECT = new Object();
	
	public ItemStackSet() {
		mMap = new ItemStackMap<>();
		GT_API.STACKMAPS.add(mMap);
	}
	public ItemStackSet(ItemStack... aStacks) {
		mMap = new ItemStackMap<>();
		for (ItemStack aStack : aStacks) add(aStack);
		GT_API.STACKMAPS.add(mMap);
	}
	@Override
	@SuppressWarnings("unchecked")
	public Iterator<ItemStackContainer> iterator() {
		return (Iterator<ItemStackContainer>)mMap.keySet().iterator();
	}
	@Override
	public int size() {
		return mMap.size();
	}
	@Override
	public boolean isEmpty() {
		return mMap.isEmpty();
	}
	@Override
	public boolean contains(Object aObject) {
		return mMap.containsKey(aObject);
	}
	public boolean contains(ItemStack aStack, boolean aWildCard) {
		return mMap.containsKey(aStack, aWildCard);
	}
	public boolean contains(ItemStackContainer aStack, boolean aWildCard) {
		return mMap.containsKey(aStack, aWildCard);
	}
	public boolean add(ModData aMod, String aName, long aMeta) {
		return add(ST.make(aMod, aName, 1, aMeta));
	}
	public boolean add(ItemStack aStack) {
		return ST.valid(aStack) && mMap.put(aStack, OBJECT) == null;
	}
	public boolean add(Block aBlock, long aMeta) {
		return aBlock != null && mMap.put(aBlock, aMeta, OBJECT) == null;
	}
	public boolean add(Block aBlock) {
		return aBlock != null && mMap.put(aBlock, W, OBJECT) == null;
	}
	public boolean add(Item aItem, long aMeta) {
		return aItem != null && mMap.put(aItem, aMeta, OBJECT) == null;
	}
	public boolean add(Item aItem) {
		return aItem != null && mMap.put(aItem, W, OBJECT) == null;
	}
	@Override
	public boolean add(ItemStackContainer aStack) {
		return aStack != null && mMap.put(aStack, OBJECT) == null;
	}
	@Override
	public boolean addAll(Collection<? extends ItemStackContainer> c) {
		return c != null && super.addAll(c);
	}
	@Override
	public boolean remove(Object aStack) {
		return mMap.remove(aStack) == OBJECT;
	}
	@Override
	public boolean equals(Object aObject) {
		return this == aObject;
	}
	@Override
	public void clear() {
		mMap.clear();
	}
}
