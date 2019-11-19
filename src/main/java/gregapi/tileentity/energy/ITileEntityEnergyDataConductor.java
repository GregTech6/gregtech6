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

import gregapi.code.TagData;
import gregapi.oredict.OreDictMaterial;
import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 * 
 * Informative Class.
 * 
 * Interface for things like Data Panels and Stuff.
 * Not all Data might be reliable. This is just for Information sake.
 */
public interface ITileEntityEnergyDataConductor extends ITileEntityUnloadable {
	/**
	 * @return if this is a Conductor for this Type of Energy.
	 */
	public boolean isEnergyConducting(TagData aEnergyType);
	
	/**
	 * @return the maximum Packet Size of the Conductor.
	 */
	public long getEnergyMaxSize(TagData aEnergyType);
	
	/**
	 * @return the maximum Packet Amount of the Conductor.
	 */
	public long getEnergyMaxPackets(TagData aEnergyType);
	
	/**
	 * @return the Loss of the Conductor, per Meter.
	 */
	public long getEnergyLossPerMeter(TagData aEnergyType);
	
	/**
	 * @return the Material the Conductor consists of. (may return Materials._NULL)
	 */
	public OreDictMaterial getEnergyConductorMaterial();
	
	/**
	 * @return the Material the Conductor Insulation/Hull consists of. (may return Materials._NULL)
	 */
	public OreDictMaterial getEnergyConductorInsulation();
}
