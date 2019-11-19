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
	
	@SafeVarargs
	public ArrayListNoNulls(boolean aDummyParameter, E... aArray) {
		super(Arrays.asList(aArray));
		for (int i = 0; i < size(); i++) if (get(i) == null) remove(i--);
	}
	
	public ArrayListNoNulls(Collection<? extends E> aList) {
		super(aList);
		for (int i = 0; i < size(); i++) if (get(i) == null) remove(i--);
	}
	
	public E get(Random aRandom) {
		return isEmpty() ? null : get(aRandom.nextInt(size()));
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
