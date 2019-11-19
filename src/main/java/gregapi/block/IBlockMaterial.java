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

import gregapi.oredict.OreDictMaterialStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 * 
 * This Interface is used for determining the Chemical Compound at a certain Side of this Block for reacting with it.
 */
public interface IBlockMaterial {
	/** Gets the Material on this Side of the Block, including its amount in Material Units. */
	public OreDictMaterialStack getMaterialAtSide(IBlockAccess aWorld, int aX, int aY, int aZ, byte aSide);
	/** Removes the specified Material from that Side. return true if it was successful. */
	public boolean removeMaterialFromSide(World aWorld, int aX, int aY, int aZ, byte aSide, OreDictMaterialStack aMaterial);
}
