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

package gregapi.tileentity.energy;

import java.util.Collection;

import gregapi.code.TagData;
import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 * 
 * Informative Class.
 * 
 * Interface for things like Data Panels and Stuff.
 * Not all Data might be reliable. This is just for Information sake.
 */
public interface ITileEntityEnergyDataCapacitor extends ITileEntityUnloadable {
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it.
	 * 
	 * Why is there a Side Parameter you ask? I don't know, maybe one Energy Storage for every Side (insert aSide=6 if you don't want to watch for Sides).
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @return the amount of stored Energy of this kind in this Block. The returned amount is NOT "Math.max"-ed by getEnergyCapacity, so in case you want to display it, you should do that outside of this Block.
	 */
	public long getEnergyStored(TagData aEnergyType, byte aSide);
	
	/**
	 * This Method may be called by a different Thread. Take that into account when implementing it.
	 * 
	 * Why is there a Side Parameter you ask? I don't know, maybe one Energy Storage for every Side (insert aSide=6 if you don't want to watch for Sides).
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @return the Capacity for this kind of Energy, by this Block.
	 */
	public long getEnergyCapacity(TagData aEnergyType, byte aSide);
	
	/**
	 * You do not have to check for this Function, this is only for things like Energy Network optimisation and similar.
	 * 
	 * @param aEnergyType The Type of Energy
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (don't do Side checks for this Side)
	 * @return if this TileEntity has anything to do with this Type of Energy, depending on insert or extract request. The returning Value must be constant for this TileEntity.
	 */
	public boolean isEnergyCapacitorType(TagData aEnergyType, byte aSide);
	
	/**
	 * Gets all the Types of Energy, which are relevant to this Capacitor.
	 * 
	 * @param aSide 0 - 5 = Vanilla Directions of the Implementors Block. 6 = No specific Side (should return all related Types of Energy)
	 * @return any Type of Energy that is related to this TileEntity. This is especially useful for Data Displays and Redstone Conditions, where people can select the Energy Type via GUI or something.
	 */
	public Collection<TagData> getEnergyCapacitorTypes(byte aSide);
}
