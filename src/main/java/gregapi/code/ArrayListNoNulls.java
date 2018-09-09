package gregapi.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

/**
 * @author Gregorius Techneticies
 * 
 * An ArrayList which does not permit null Values.
 */
public class ArrayListNoNulls<E> extends ArrayList<E> {
	private static final long serialVersionUID = 1L;

	public ArrayListNoNulls() {
		super(1);
	}
	
	public ArrayListNoNulls(int aCapacity) {
		super(aCapacity);
	}
	
	public ArrayListNoNulls(boolean aDummyParameter, E... aArray) {
		super(Arrays.asList(aArray));
		for (int i = 0; i < size(); i++) if (get(i) == null) remove(i--);
	}
	
    public ArrayListNoNulls(Collection<? extends E> aList) {
    	super(aList);
    	for (int i = 0; i < size(); i++) if (get(i) == null) remove(i--);
    }
    
    public E get(Random aRandom) {
    	return get(aRandom.nextInt(size()));
    }
    
    @Override
	public E set(int aIndex, E aElement) {
    	if (aElement != null) return super.set(aIndex, aElement);
        return null;
    }
    
    @Override
	public boolean add(E aElement) {
    	if (aElement != null) return super.add(aElement);
        return false;
    }
    
    @Override
	public void add(int aIndex, E aElement) {
    	if (aElement != null) super.add(aIndex, aElement);
    }
    
    @Override
	public boolean addAll(Collection<? extends E> aList) {
        boolean rReturn = super.addAll(aList);
		for (int i = 0; i < size(); i++) if (get(i) == null) remove(i--);
		return rReturn;
    }
    
    @Override
	public boolean addAll(int aIndex, Collection<? extends E> aList) {
    	boolean rReturn = super.addAll(aIndex, aList);
		for (int i = 0; i < size(); i++) if (get(i) == null) remove(i--);
		return rReturn;
    }
}