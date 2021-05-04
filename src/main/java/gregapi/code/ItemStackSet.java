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

import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import gregapi.GT_API;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class ItemStackSet<E extends ItemStackContainer> extends AbstractSet<E> {
	private transient HashMap<ItemStackContainer, Object> map;
	private static final Object OBJECT = new Object();
	
	public ItemStackSet() {
		map = new HashMap<>();
		GT_API.STACKMAPS.add(map);
	}
	
	public ItemStackSet(ItemStack... aStacks) {
		map = new HashMap<>();
		for (ItemStack aStack : aStacks) add(aStack);
		GT_API.STACKMAPS.add(map);
	}
	
	public ItemStackSet(Collection<? extends E> c) {
		map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
		addAll(c);
		GT_API.STACKMAPS.add(map);
	}
	
	public ItemStackSet(int initialCapacity, float loadFactor) {
		map = new HashMap<>(initialCapacity, loadFactor);
		GT_API.STACKMAPS.add(map);
	}
	
	public ItemStackSet(int initialCapacity) {
		map = new HashMap<>(initialCapacity);
		GT_API.STACKMAPS.add(map);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator() {
		return (Iterator<E>)map.keySet().iterator();
	}
	
	@Override
	public int size() {
		return map.size();
	}
	
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}
	
	@Override
	public boolean contains(Object aObject) {
		return map.containsKey(aObject);
	}
	public boolean contains(ItemStack aObject, boolean aWildCard) {
		return map.containsKey(new ItemStackContainer(aObject)) || (aWildCard && map.containsKey(new ItemStackContainer(aObject         , W)));
	}
	public boolean contains(ItemStackContainer aObject, boolean aWildCard) {
		return map.containsKey(                       aObject ) || (aWildCard && map.containsKey(new ItemStackContainer(aObject.mItem, 1, W)));
	}
	
	public boolean add(ModData aMod, String aName, long aMeta) {
		return add(ST.make(aMod, aName, 1, aMeta));
	}
	public boolean add(ItemStack aStack) {
		return ST.valid(aStack) && map.put(new ItemStackContainer(aStack), OBJECT) == null;
	}
	public boolean add(Block aBlock, long aMeta) {
		return aBlock != null && map.put(new ItemStackContainer(aBlock, 1, aMeta), OBJECT) == null;
	}
	public boolean add(Block aBlock) {
		return aBlock != null && map.put(new ItemStackContainer(aBlock, 1, W), OBJECT) == null;
	}
	public boolean add(Item aItem, long aMeta) {
		return aItem != null && map.put(new ItemStackContainer(aItem, 1, aMeta), OBJECT) == null;
	}
	public boolean add(Item aItem) {
		return aItem != null && map.put(new ItemStackContainer(aItem, 1, W), OBJECT) == null;
	}
	
	@Override
	public boolean add(E aStack) {
		return aStack != null && map.put(aStack, OBJECT) == null;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return c != null && super.addAll(c);
	}
	
	@Override
	public boolean remove(Object aStack) {
		return map.remove(aStack) == OBJECT;
	}
	
	@Override
	public boolean equals(Object aStack) {
		return this == aStack;
	}
	
	@Override
	public void clear() {
		map.clear();
	}
}
