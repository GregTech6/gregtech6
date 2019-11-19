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

package gregapi.compat;

import static gregapi.data.CS.*;

import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;

public abstract class CompatMods extends CompatBase {
	public final ModData mMod;
	
	public CompatMods(ModData aMod) {
		this(aMod, GAPI_POST);
	}
	public CompatMods(ModData aMod, Abstract_Mod aGTMod) {
		mMod = aMod;
		if (mMod.mLoaded) aGTMod.mCompatClasses.add(this);
	}
	@Override
	public String toString() {
		return mMod.mName;
	}
}
