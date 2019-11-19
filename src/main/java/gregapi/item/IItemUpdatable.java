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

package gregapi.item;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public interface IItemUpdatable {
	/** Updates the Data of the ItemStack. Not called every tick but instead called whenever something important happens to the Stack. */
	public void updateItemStack(ItemStack aStack);
	/** Updates the Data of the ItemStack. Not called every tick but instead called whenever something important happens to the Stack. */
	public void updateItemStack(ItemStack aStack, World aWorld, int aX, int aY, int aZ);
}
