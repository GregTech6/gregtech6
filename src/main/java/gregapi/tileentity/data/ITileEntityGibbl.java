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

package gregapi.tileentity.data;

import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityGibbl extends ITileEntityUnloadable {
	/** The Pressure this Object has right now. Measured in Milligibbl. 1 Gibbl is 1m³ per 1m³. A full BuildCraft Tank would have a Pressure of 16 Gibbl. */
	public long getGibblValue(byte aSide);
	/** The Pressure this Object can have at most before breaking. Measured in Milligibbl. 1 Gibbl is 1m³ per 1m³. A full BuildCraft Tank would have a Pressure of 16 Gibbl. */
	public long getGibblMax(byte aSide);
}
