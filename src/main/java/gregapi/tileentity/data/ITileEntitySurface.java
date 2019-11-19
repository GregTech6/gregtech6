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
 * 
 * Implemented by the MultiTileEntity Chest example. If this is not implemented, I might go for the regular Collision Box, but that is much laggier.
 */
public interface ITileEntitySurface extends ITileEntityUnloadable {
	/** The actual Surface Size of this Block. This is also used Serverside! */
	public float getSurfaceSize(byte aSide);
	/** How "Fat" a visual Pipe can be at max when connecting. 1.0F for Full Blocks, 0.5F for 1/4th of a Block (so with half height and half width). This is also used Serverside! */
	public float getSurfaceSizeAttachable(byte aSide);
	/** How deep a Pipe has to go into the Block in order to visually connect to a Surface on that Side. 0.0F for Full Blocks, 0.5F for going to the middle of the Block, 1.0F for going through the whole Block (even though the last should not be the case most of the time). This is also used Serverside! */
	public float getSurfaceDistance(byte aSide);
	/** If this Side is Solid, like a facing of a full Block. It CAN still be transparent like a Block of Glass. This is also used Serverside! */
	public boolean isSurfaceSolid(byte aSide);
	/** If this Side is Opaque, like a facing of a full and non-transparent Block. This is also used Serverside! */
	public boolean isSurfaceOpaque(byte aSide);
}
