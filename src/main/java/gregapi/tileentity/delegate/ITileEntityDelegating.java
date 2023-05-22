/**
 * Copyright (c) 2023 GregTech-6 Team
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

import gregapi.data.LH;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityDelegating extends ITileEntityCanDelegate {
	public static final byte EXTENDER_INV = 1, EXTENDER_TANK = 2, EXTENDER_REDSTONE = 4, EXTENDER_OTHER = 8, EXTENDER_CONTROL = 16, EXTENDER_ALL = 31;
	
	public static final String
	TOOLTIP_EXTENDER_EXCLUSIVE = LH.add("gt.tileentity.extender.tooltip.exclusive", "Cannot be attached to other Extenders!"),
	TOOLTIP_EXTENDER_INVENTORY = LH.add("gt.tileentity.extender.tooltip.inv"      , "Relays Inventories"                    ),
	TOOLTIP_EXTENDER_TANK      = LH.add("gt.tileentity.extender.tooltip.tank"     , "Relays Tanks"                          ),
	TOOLTIP_EXTENDER_REDSTONE  = LH.add("gt.tileentity.extender.tooltip.redstone" , "Relays Redstone"                       ),
	TOOLTIP_EXTENDER_OTHER     = LH.add("gt.tileentity.extender.tooltip.other"    , "Relays Misc Functions"                 ),
	TOOLTIP_EXTENDER_CONTROL   = LH.add("gt.tileentity.extender.tooltip.control"  , "Relays Control Functions"              ),
	TOOLTIP_EXTENDER_ALL       = LH.add("gt.tileentity.extender.tooltip.all"      , "Relays everything"                     );
	
	/** Gets the TileEntity which is responsible for handling this Side. DO NOT RETURN NULL! Return a DelegatorTileEntity Object without TileEntity, or a DelegatorTileEntity with the 'this' Object in order to not delegate. */
	public DelegatorTileEntity<TileEntity> getDelegateTileEntity(byte aSide);
}
