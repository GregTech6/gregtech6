/**
 * Copyright (c) 2021 GregTech-6 Team
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

import net.minecraft.entity.player.EntityPlayer;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityKeyInteractable extends ITileEntityUnloadable {
	/** @return if it got opened successfully. aKeys is the List of IDs the Key has (if you have a key bundle of some sort, for example) */
	public boolean useKey(EntityPlayer aPlayer, byte aSide, float hitX, float hitY, float hitZ, long... aKeys);
	/** @return if you can clone the ID of this Lock. */
	public boolean canCloneKey(EntityPlayer aPlayer, byte aSide, float hitX, float hitY, float hitZ);
	/** @return the ID of the Key belonging to this Lock. 0 means there is no ID. */
	public long getKeyID();
}
