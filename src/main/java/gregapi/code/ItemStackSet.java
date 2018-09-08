package gregapi.code;

import static gregapi.data.CS.*;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import gregapi.GT_API;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class ItemStackSet<E extends ItemStackContainer> extends AbstractSet<E> {
	private transient HashMap<ItemStackContainer, Object> map;
    private static final Object OBJECT = new Object();
    
    public ItemStackSet() {
        map = new HashMap();
        GT_API.STACKMAPS.add(map);
    }
    
    public ItemStackSet(Collection<? extends E> c) {
        map = new HashMap(Math.max((int) (c.size()/.75f) + 1, 16));
        addAll(c);
        GT_API.STACKMAPS.add(map);
    }
    
    public ItemStackSet(int initialCapacity, float loadFactor) {
        map = new HashMap(initialCapacity, loadFactor);
        GT_API.STACKMAPS.add(map);
    }
    
    public ItemStackSet(int initialCapacity) {
        map = new HashMap(initialCapacity);
        GT_API.STACKMAPS.add(map);
    }
    
    @Override
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
    
    public boolean add(ItemStack aStack) {
        return ST.valid(aStack) && map.put(new ItemStackContainer(aStack), OBJECT) == null;
    }
    
    @Override
	public boolean add(E aStack) {
        return aStack != null && map.put(aStack, OBJECT) == null;
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