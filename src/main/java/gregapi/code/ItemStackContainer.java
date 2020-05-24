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

package gregapi.code;

import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class ItemStackContainer {
	public final Item mItem;
	public final Block mBlock;
	public final byte mStackSize;
	public final short mMetaData;
	
	public ItemStackContainer(Item aItem, long aStackSize, long aMetaData) {
		mItem = aItem;
		mBlock = ST.block(mItem);
		mStackSize = (byte)aStackSize;
		mMetaData = (short)aMetaData;
	}
	public ItemStackContainer(Block aBlock, long aStackSize, long aMetaData) {
		mBlock = aBlock;
		mItem = ST.item(mBlock);
		mStackSize = (byte)aStackSize;
		mMetaData = (short)aMetaData;
	}
	public ItemStackContainer(long aID, long aStackSize, long aMetaData) {
		mItem = ST.item(aID);
		mBlock = ST.block(mItem);
		mStackSize = (byte)aStackSize;
		mMetaData = (short)aMetaData;
	}
	public ItemStackContainer(ItemStack aStack) {this(ST.item(aStack), ST.size(aStack), ST.meta(aStack));}
	public ItemStackContainer(ItemStack aStack, long aMetaData) {this(ST.item(aStack), ST.size(aStack), aMetaData);}
	public ItemStackContainer(ItemStack aStack, long aStackSize, long aMetaData) {this(ST.item(aStack), aStackSize, aMetaData);}
	public ItemStackContainer(int aHashCode) {this(ST.toItem(aHashCode), 0, ST.toMeta(aHashCode));}
	
	public ItemStack toStack() {return mItem == null ? null : ST.make(mItem, 1, mMetaData);}
	public boolean isStackEqual(ItemStack aStack) {return ST.equal(aStack, mItem, mMetaData);}
	public boolean isStackEqual(ItemStackContainer aStack) {return aStack.mItem == mItem && ST.equal(aStack.mMetaData, mMetaData);}
	
	@Override public boolean equals(Object aStack) {return aStack == this || (aStack instanceof ItemStackContainer && ((ItemStackContainer)aStack).mItem == mItem && ((ItemStackContainer)aStack).mMetaData == mMetaData);}
	@Override public int hashCode() {return ST.toInt(mItem, mMetaData);}
}
