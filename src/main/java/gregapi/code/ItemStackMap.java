package gregapi.code;

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
	
	public V get(long aID, long aMetaData) {
		return get(new ItemStackContainer(aID, 1, aMetaData));
	}
	
	public V get(Item aItem, long aMetaData) {
		return get(new ItemStackContainer(aItem, 1, aMetaData));
	}
	
	public V get(Block aBlock, long aMetaData) {
		return get(new ItemStackContainer(aBlock, 1, aMetaData));
	}
	
	public V get(ModData aMod, String aName, long aMetaData) {
		return aMod.mLoaded ? get(new ItemStackContainer(ST.make(aMod, aName, 1, aMetaData))) : null;
	}
	
	public V put(ItemStack aKey, V aValue) {
		return put(new ItemStackContainer(aKey), aValue);
	}
}