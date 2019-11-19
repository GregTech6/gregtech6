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

package gregapi.random;

import net.minecraft.util.ChunkCoordinates;

/**
 * @author Gregorius Techneticies
 * 
 * Contains simple Utility Functions based just on the Coordinates of the Implementor.
 */
public interface IHasCoords {
	public int getX();
	public int getY();
	public int getZ();
	public int getOffsetX (byte aSide);
	public int getOffsetY (byte aSide);
	public int getOffsetZ (byte aSide);
	
	public int getOffsetX (byte aSide, int aMultiplier);
	public int getOffsetY (byte aSide, int aMultiplier);
	public int getOffsetZ (byte aSide, int aMultiplier);
	
	public int getOffsetXN(byte aSide);
	public int getOffsetYN(byte aSide);
	public int getOffsetZN(byte aSide);
	
	public int getOffsetXN(byte aSide, int aMultiplier);
	public int getOffsetYN(byte aSide, int aMultiplier);
	public int getOffsetZN(byte aSide, int aMultiplier);
	
	/** Do not change the XYZ of the returned Coordinates Object! */
	public ChunkCoordinates getCoords();
	/** Do not change the XYZ of the returned Coordinates Object! */
	public ChunkCoordinates getOffset (byte aSide, int aMultiplier);
	/** Do not change the XYZ of the returned Coordinates Object! */
	public ChunkCoordinates getOffsetN(byte aSide, int aMultiplier);
}
