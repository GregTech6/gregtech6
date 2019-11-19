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

import java.util.Set;

import cpw.mods.fml.common.Loader;

/**
 * @author Gregorius Techneticies
 */
public final class ModData implements ICondition<ITagDataContainer<?>> {
	public static final Set<ModData> MODS = new HashSetNoNulls<>();
	
	public boolean mLoaded;
	
	public final String mID, mName;
	
	public ModData(String aID, String aName) {
		mID = aID;
		mName = aName;
		mLoaded = Loader.isModLoaded(mID);
		MODS.add(this);
	}
	
	public ModData setLoaded(boolean aLoaded) {
		mLoaded = aLoaded;
		return this;
	}
	
	@Override
	public String toString() {
		return mID;
	}
	
	@Override
	public boolean isTrue(ITagDataContainer<?> aObject) {
		return mLoaded;
	}
	
	@Override
	public boolean equals(Object aObject) {
		return aObject instanceof ModData && ((ModData)aObject).mID.equals(mID);
	}
	
	@Override
	public int hashCode() {
		return mID.hashCode();
	}
}
