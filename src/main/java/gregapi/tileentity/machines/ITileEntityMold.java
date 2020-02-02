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

package gregapi.tileentity.machines;

import gregapi.oredict.OreDictMaterialStack;
import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityMold extends ITileEntityUnloadable {
	public boolean  isMoldInputSide(byte aSide);
	/** The Maximum Temperature this Mold can accept without melting itself */
	public long     getMoldMaxTemperature();
	/** The Amount of Material required to fill the Mold completely */
	public long     getMoldRequiredMaterialUnits();
	/** The first Parameter can also have a Material Amount higher or lower than the actually required Amount, usually only in case it is done manually. It returns the amount of Material subtracted from the passed MaterialStack */
	public long     fillMold(OreDictMaterialStack aMaterial, long aTemperature, byte aSide);
}
