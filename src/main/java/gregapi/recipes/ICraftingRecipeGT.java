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