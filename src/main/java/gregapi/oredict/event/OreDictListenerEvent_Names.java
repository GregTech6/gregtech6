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

package gregapi.oredict.event;

import static gregapi.data.CS.*;

import java.util.HashMap;
import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictPrefix;

/**
 * @author Gregorius Techneticies
 * 
 * Used to process Non-Material based OreDict Stuff faster, like a String switch/case kind of a deal.
 */
public abstract class OreDictListenerEvent_Names implements IOreDictListenerEvent {
	private final HashMap<String, List<IOreDictListenerEvent>> NAME_EVENTS = new HashMap<>();
	
	public OreDictListenerEvent_Names(OreDictPrefix aPrefix) {
		addAllListeners();
		aPrefix.addListener(this);
	}
	
	public OreDictListenerEvent_Names() {
		addAllListeners();
		OreDictManager.INSTANCE.addListener(this);
		for (String tOreDictName : NAME_EVENTS.keySet()) OreDictManager.INSTANCE.addKnownName(tOreDictName);
	}
	
	public abstract void addAllListeners();
	
	public boolean addListener(Object aOreDictName, IOreDictListenerEvent aListener) {
		aOreDictName = aOreDictName.toString();
		List<IOreDictListenerEvent> tList = NAME_EVENTS.get(aOreDictName);
		if (tList == null) NAME_EVENTS.put((String)aOreDictName, tList = new ArrayListNoNulls<>());
		if (!tList.add(aListener)) throw new UnknownError("Unknown Error: " + aOreDictName + "  -  " + aListener.toString());
		return T;
	}
	public boolean addListener(Object aOreDictName1, Object aOreDictName2, IOreDictListenerEvent aListener) {
		return addListener(aOreDictName1, aListener) && addListener(aOreDictName2, aListener);
	}
	public boolean addListener(Object aOreDictName1, Object aOreDictName2, Object aOreDictName3, IOreDictListenerEvent aListener) {
		return addListener(aOreDictName1, aListener) && addListener(aOreDictName2, aListener) && addListener(aOreDictName3, aListener);
	}
	public boolean addListener(Object aOreDictName1, Object aOreDictName2, Object aOreDictName3, Object aOreDictName4, IOreDictListenerEvent aListener) {
		return addListener(aOreDictName1, aListener) && addListener(aOreDictName2, aListener) && addListener(aOreDictName3, aListener) && addListener(aOreDictName4, aListener);
	}
	public boolean addListener(Object[] aOreDictNames, IOreDictListenerEvent aListener) {
		for (Object tOreDictName : aOreDictNames) addListener(tOreDictName, aListener);
		return T;
	}
	public boolean addListener(OreDictListenerEvent_TwoNames aListener) {
		return addListener(aListener.mName1, aListener) && addListener(aListener.mName2, aListener);
	}
	public boolean addListener(OreDictListenerEvent_ThreeNames aListener) {
		return addListener(aListener.mName1, aListener) && addListener(aListener.mName2, aListener) && addListener(aListener.mName3, aListener);
	}
	
	@Override
	public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		List<IOreDictListenerEvent> tList = NAME_EVENTS.get(aEvent.mOreDictName);
		if (tList != null) for (IOreDictListenerEvent tListener : tList) tListener.onOreRegistration(aEvent);
	}
}
