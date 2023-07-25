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

import java.util.HashMap;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class ItemStackMap<K extends ItemStackContainer, V> extends HashMap<ItemStackContainer, V> {
	private static final long serialVersionUID = 1L;
	
	private boolean mHasWildcards = F;
	
	public ItemStackMap() {
		super();
		GT_API.STACKMAPS.add(this);
	}
	@Override
	public boolean equals(Object o) {
		return this == o;
	}
	public boolean containsKey(long aID, long aMeta, boolean aWildcard) {
		if (mHasWildcards) {
			if (aWildcard) {
				if (containsKey(new ItemStackContainer(aID, 1, W))) return T;
				if (aMeta == W) return F;
			}
		} else {
			if (aMeta == W) return F;
		}
		return containsKey(new ItemStackContainer(aID, 1, aMeta));
	}
	public boolean containsKey(Item aItem, long aMeta, boolean aWildcard) {
		if (mHasWildcards) {
			if (aWildcard) {
				if (containsKey(new ItemStackContainer(aItem, 1, W))) return T;
				if (aMeta == W) return F;
			}
		} else {
			if (aMeta == W) return F;
		}
		return containsKey(new ItemStackContainer(aItem, 1, aMeta));
	}
	public boolean containsKey(Block aBlock, long aMeta, boolean aWildcard) {
		if (mHasWildcards) {
			if (aWildcard) {
				if (containsKey(new ItemStackContainer(aBlock, 1, W))) return T;
				if (aMeta == W) return F;
			}
		} else {
			if (aMeta == W) return F;
		}
		return containsKey(new ItemStackContainer(aBlock, 1, aMeta));
	}
	public boolean containsKey(ItemStack aStack, boolean aWildcard) {
		if (mHasWildcards) {
			if (aWildcard) {
				if (containsKey(new ItemStackContainer(aStack, W))) return T;
				if (ST.meta(aStack) == W) return F;
			}
		} else {
			if (ST.meta(aStack) == W) return F;
		}
		return containsKey(new ItemStackContainer(aStack));
	}
	public boolean containsKey(ItemStackContainer aStack, boolean aWildcard) {
		if (mHasWildcards) {
			if (aWildcard) {
				if (aStack.mMetaData == W) return containsKey(aStack);
				if (containsKey(new ItemStackContainer(aStack.mItem, 1, W))) return T;
			}
		} else {
			if (aStack.mMetaData == W) return F;
		}
		return containsKey(aStack);
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
	
	@Override
	public V put(ItemStackContainer aKey, V aValue) {
		if (aKey.mMetaData == W) mHasWildcards = T;
		return super.put(aKey, aValue);
	}
	public V put(IItemContainer aKey, V aValue) {
		ItemStackContainer tKey = new ItemStackContainer(aKey.get(1));
		if (tKey.mMetaData == W) mHasWildcards = T;
		return put(tKey, aValue);
	}
	public V put(ItemStack aKey, V aValue) {
		ItemStackContainer tKey = new ItemStackContainer(aKey);
		if (tKey.mMetaData == W) mHasWildcards = T;
		return put(tKey, aValue);
	}
	public V put(long aID, long aMeta, V aValue) {
		if (aMeta == W) mHasWildcards = T;
		return put(new ItemStackContainer(aID, 1, aMeta), aValue);
	}
	public V put(Item aItem, long aMeta, V aValue) {
		if (aMeta == W) mHasWildcards = T;
		return put(new ItemStackContainer(aItem, 1, aMeta), aValue);
	}
	public V put(Block aBlock, long aMeta, V aValue) {
		if (aMeta == W) mHasWildcards = T;
		return put(new ItemStackContainer(aBlock, 1, aMeta), aValue);
	}
	public V put(ModData aMod, String aName, long aMeta, V aValue) {
		if (aMeta == W) mHasWildcards = T;
		return put(new ItemStackContainer(ST.make(aMod, aName, 1, aMeta)), aValue);
	}
}
