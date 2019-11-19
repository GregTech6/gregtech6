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

package gregapi.tileentity;

import net.minecraft.inventory.IInventory;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityAdjacentInventoryUpdatable extends ITileEntityUnloadable {
	/**
	 * Gets called by some GT things like Pipes to notify about changes inside the Inventory of the caller.
	 * 
	 * This is only for important Inventory Updates, like when a Pipe has more free space than before.
	 */
	public void adjacentInventoryUpdated(byte aSide, IInventory aTileEntity);
}
