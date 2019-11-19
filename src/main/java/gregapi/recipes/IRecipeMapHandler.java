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

import static gregapi.data.CS.*;

import gregapi.oredict.OreDictItemData;
import gregapi.recipes.Recipe.RecipeMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

/**
 * @author Gregorius Techneticies
 */
public interface IRecipeMapHandler {
	public boolean addRecipesUsing(RecipeMap aMap, ItemStack aStack, OreDictItemData aData);
	public boolean addRecipesUsing(RecipeMap aMap, Fluid aFluid);
	
	public boolean addRecipesProducing(RecipeMap aMap, ItemStack aStack, OreDictItemData aData);
	public boolean addRecipesProducing(RecipeMap aMap, Fluid aFluid);
	
	public boolean containsInput(RecipeMap aMap, ItemStack aStack, OreDictItemData aData);
	public boolean containsInput(RecipeMap aMap, Fluid aFluid);
	
	public boolean addAllRecipes(RecipeMap aMap);
	public boolean isDone();
	
	public boolean onAddedToMap(RecipeMap aMap);
	
	public static abstract class RecipeMapHandler implements IRecipeMapHandler {
		public RecipeMapHandler() {/**/}
		
		@Override public boolean addRecipesUsing(RecipeMap aMap, ItemStack aStack, OreDictItemData aData) {return F;}
		@Override public boolean addRecipesProducing(RecipeMap aMap, ItemStack aStack, OreDictItemData aData) {return F;}
		@Override public boolean containsInput(RecipeMap aMap, ItemStack aStack, OreDictItemData aData) {return !isDone() && addRecipesUsing(aMap, aStack, aData);}
		@Override public boolean addRecipesUsing(RecipeMap aMap, Fluid aFluid) {return F;}
		@Override public boolean addRecipesProducing(RecipeMap aMap, Fluid aFluid) {return F;}
		@Override public boolean containsInput(RecipeMap aMap, Fluid aFluid) {return !isDone() && addRecipesUsing(aMap, aFluid);}
		@Override public boolean addAllRecipes(RecipeMap aMap) {return F;}
		@Override public boolean onAddedToMap(RecipeMap aMap) {return T;}
		@Override public boolean isDone() {return F;}
	}
}
