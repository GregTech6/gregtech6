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

import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityFoamable extends ITileEntityUnloadable {
	/** @return if it got applied successfully. */
	public boolean applyFoam(byte aSide, Entity aPlayer, short[] aCFoamRGB, byte aVanillaColor, boolean aOwnedFoam);
	
	/** @return if it got dried successfully. */
	public boolean dryFoam(byte aSide, Entity aPlayer);
	
	/** @return if it got removed successfully. */
	public boolean removeFoam(byte aSide, Entity aPlayer);
	
	/** @return if it is foamed. */
	public boolean hasFoam(byte aSide);
	
	/** @return if it is dried. */
	public boolean driedFoam(byte aSide);
	
	/** @return if it is owned. */
	public boolean ownedFoam(byte aSide);
}
