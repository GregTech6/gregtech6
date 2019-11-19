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

package gregapi.tileentity.delegate;

import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityDelegating extends ITileEntityCanDelegate {
	/** Gets the TileEntity which is responsible for handling this Side. DO NOT RETURN NULL! Return a DelegatorTileEntity Object without TileEntity, or a DelegatorTileEntity with the 'this' Object in order to not delegate. */
	public DelegatorTileEntity<TileEntity> getDelegateTileEntity(byte aSide);
}
