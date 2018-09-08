package gregapi.recipes;

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
}