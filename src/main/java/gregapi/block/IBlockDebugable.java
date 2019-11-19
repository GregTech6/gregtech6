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

package gregapi.block;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;

/**
 * @author Gregorius Techneticies
 */
public interface IBlockDebugable {
	/**
	 * Returns a Debug Message, for a generic DebugItem
	 * Blocks have to implement this interface NOT TileEntities!
	 * @param aPlayer the Player, who rightclicked with his Debug Item
	 * @param aX Block-Coordinate
	 * @param aY Block-Coordinate
	 * @param aZ Block-Coordinate
	 * @param aScanLevel the Log Level of the Debug Item.
	 * 0 = Obvious
	 * 1 = Visible for the regular Scanner
	 * 2 = Only visible to more advanced Scanners
	 * 3 = Debug ONLY
	 * @return a String-Array containing the DebugInfo, every Index is a separate line (0 = first Line)
	 */
	public ArrayList<String> getDebugInfo(EntityPlayer aPlayer, int aX, int aY, int aZ, int aScanLevel);
}
