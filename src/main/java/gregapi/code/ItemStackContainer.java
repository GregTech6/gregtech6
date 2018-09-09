package gregapi.code;

import static gregapi.data.CS.*;

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
		mBlock = Block.getBlockFromItem(mItem);
		mStackSize = (byte)aStackSize;
		mMetaData = (short)aMetaData;
	}
	
	public ItemStackContainer(Block aBlock, long aStackSize, long aMetaData) {
		mBlock = aBlock;
		mItem = Item.getItemFromBlock(mBlock);
		mStackSize = (byte)aStackSize;
		mMetaData = (short)aMetaData;
	}
	
	public ItemStackContainer(long aID, long aStackSize, long aMetaData) {
		mItem = Item.getItemById((int)aID);
		mBlock = Block.getBlockFromItem(mItem);
		mStackSize = (byte)aStackSize;
		mMetaData = (short)aMetaData;
	}
	
	public ItemStackContainer(ItemStack aStack) {
		this(aStack==null?null:aStack.getItem(), aStack==null?0:aStack.stackSize, aStack==null?0:ST.meta(aStack));
	}
	
	public ItemStackContainer(ItemStack aStack, long aMetaData) {
		this(aStack==null?null:aStack.getItem(), aStack==null?0:aStack.stackSize, aStack==null?0:aMetaData);
	}
	
	public ItemStackContainer(int aHashCode) {
		this(ST.toStack(aHashCode));
	}
	
	public ItemStack toStack() {
		if (mItem == null) return NI;
		return ST.make(mItem, 1, mMetaData);
	}
	
	public boolean isStackEqual(ItemStack aStack) {
		return ST.equal(toStack(), aStack);
	}
	
	public boolean isStackEqual(ItemStackContainer aStack) {
		return ST.equal(toStack(), aStack.toStack());
	}
	
	@Override
	public boolean equals(Object aStack) {
		if (aStack == this) return T;
		if (aStack instanceof ItemStackContainer) return ((ItemStackContainer)aStack).mItem == mItem && ((ItemStackContainer)aStack).mMetaData == mMetaData;
		return F;
	}
	
	@Override
	public int hashCode() {
		return ST.toInt(toStack());
	}
}