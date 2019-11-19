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

package gregapi.recipes;

import gregapi.util.ST;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

/**
 * @author Gregorius Techneticies
 */
public interface ICraftingRecipeGT extends IRecipe {
	/** Used for Recipes as an Error Indicator. */
	public static final ItemStack ERROR_OUTPUT = ST.make(Items.stick, 0, 0, "Error: Please Report used Ingredients to GregTech!");
	/** this is basically just needed so I don't accidentally remove my own Recipes. */
	public boolean isRemovableByGT();
	/** return false to make GT Autocrafting Tables not produce this Recipe. */
	public boolean isAutocraftableByGT();
}
