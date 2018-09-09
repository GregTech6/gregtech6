package gregapi.recipes.maps;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public abstract class RecipeMapSpecialSingleInput extends RecipeMap {
	public RecipeMapSpecialSingleInput(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (rRecipe != null || aInputs == null || aInputs.length <= 0 || aInputs[0] == null || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		return getRecipeFor(aInputs[0]);
	}
	
	@Override
	public boolean containsInput(ItemStack aStack, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {
		return super.containsInput(aStack, aTileEntity, aSpecialSlot) || getRecipeFor(aStack) != null;
	}
	
	@Override
	public List<Recipe> getNEIUsages(ItemStack... aInputs) {
		List<Recipe> rList = super.getNEIUsages(aInputs);
		if (rList.isEmpty()) for (ItemStack aInput : aInputs) if (rList.add(getRecipeFor(aInput))) break;
		return rList;
	}
	
	protected abstract Recipe getRecipeFor(ItemStack aInput);
}