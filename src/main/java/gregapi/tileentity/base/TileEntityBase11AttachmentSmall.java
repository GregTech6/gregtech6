/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregapi.tileentity.base;

import gregapi.block.multitileentity.IMultiTileEntity;

import static gregapi.data.CS.T;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase11AttachmentSmall extends TileEntityBase10Attachment implements IMultiTileEntity.IMTE_IgnorePlayerCollisionWhenPlacing {
	@Override public boolean ignorePlayerCollisionWhenPlacing() {return T;}
}
