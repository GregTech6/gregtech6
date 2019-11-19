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

package gregapi.compat.buildcraft;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.code.TagData;
import gregapi.compat.CompatBase;
import gregapi.data.TD;


public class CompatBC extends CompatBase implements ICompatBC {
	public CompatBC() {
		TriggerBC_Energy_Capacity_Empty.class.getCanonicalName();
		TriggerBC_Energy_Capacity_Partial.class.getCanonicalName();
		TriggerBC_Energy_Capacity_NotFull.class.getCanonicalName();
		TriggerBC_Energy_Capacity_Full.class.getCanonicalName();
	}
	
	@Override
	public void onPostLoad(FMLPostInitializationEvent aEvent) {
		for (TagData tEnergyType : TD.Energy.ALL) {
			new TriggerBC_Energy_Capacity_Empty(tEnergyType);
			new TriggerBC_Energy_Capacity_Partial(tEnergyType);
			new TriggerBC_Energy_Capacity_NotFull(tEnergyType);
			new TriggerBC_Energy_Capacity_Full(tEnergyType);
		}
	}
}
