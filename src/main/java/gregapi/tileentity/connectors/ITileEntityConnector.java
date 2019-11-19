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

package gregapi.tileentity.connectors;

import java.util.Collection;

import gregapi.code.TagData;
import gregapi.tileentity.ITileEntity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityConnector extends ITileEntity {
	/**
	 * @return if aSide is connected.
	 */
	public boolean connected(byte aSide);
	
	/**
	 * Connects the Connector with whatever is adjacent to it on that Side.
	 * @param aNotify used to notify the thing on the other Side about the new Connection Status of this Connector
	 * @return true if it is connected to aSide now.
	 */
	public boolean connect(byte aSide, boolean aNotify);
	
	/**
	 * Disconnects the Connector from whatever is adjacent to it on that Side.
	 * @param aNotify used to notify the thing on the other Side about the new Connection Status of this Connector
	 * @return true if it is no longer connected to aSide now.
	 */
	public boolean disconnect(byte aSide, boolean aNotify);
	
	/**
	 * @return a Collection containing all Connector Types this one can connect to.
	 */
	public Collection<TagData> getConnectorTypes(byte aSide);
}
