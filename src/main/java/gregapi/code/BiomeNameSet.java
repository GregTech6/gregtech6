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

import static gregapi.data.CS.*;

import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;

import net.minecraft.world.biome.BiomeGenBase;

/**
 * @author Gregorius Techneticies
 */
public class BiomeNameSet extends AbstractSet<String> {
	private transient HashMap<String, Object> map;
	private static final Object OBJECT = new Object();
	
	@SafeVarargs
	public BiomeNameSet(Object... aArray) {
		map = new HashMap<>(Math.max((int)(aArray.length/.75F) + 1, 16));
		for (Object aName : aArray) add(aName instanceof BiomeGenBase ? ((BiomeGenBase)aName).biomeName : aName.toString());
	}
	
	@Override
	public boolean contains(Object aName) {
		if (aName == null) return F;
		String aString = (aName instanceof BiomeGenBase ? ((BiomeGenBase)aName).biomeName : aName.toString());
		if (aString == null || aString.isEmpty()) return F;
		return map.containsKey(aString.toLowerCase());
	}
	
	@Override
	public boolean add(String aName) {
		if (aName == null || aName.isEmpty()) return F;
		aName = aName.toLowerCase();
		if (!aName.endsWith(" m")) add(aName + " m");
		return map.put(aName, OBJECT) == null;
	}
	
	@Override public Iterator<String> iterator() {return map.keySet().iterator();}
	@Override public boolean remove(Object aName) {return map.remove(aName)==OBJECT;}
	@Override public boolean isEmpty() {return map.isEmpty();}
	@Override public int size() {return map.size();}
	@Override public void clear() {map.clear();}
}
