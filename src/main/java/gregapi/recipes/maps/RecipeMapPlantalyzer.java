package gregapi.recipes.maps;

import static gregapi.data.CS.*;

import java.util.Collection;

import forestry.api.genetics.AlleleManager;
import forestry.api.genetics.IIndividual;
import gregapi.data.IL;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapPlantalyzer extends RecipeMap {
	public RecipeMapPlantalyzer(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (aInputs == null || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		if (rRecipe == null) {
			for (ItemStack aInput : aInputs) if (ST.valid(aInput)) {
				if (IL.IC2_Crop_Seeds.equal(aInput, T, T)) {
					ItemStack rOutput = ST.copy(aInput);
					NBTTagCompound tNBT = UT.NBT.getOrCreate(rOutput);
					if (tNBT.getByte("scan") >= 4) return new Recipe(F, F, F, new ItemStack[] {aInput}, new ItemStack[] {aInput}, null, null, null, null, 1, 16, 0);
					tNBT.setByte("scan", (byte)4);
					return new Recipe(F, F, F, new ItemStack[] {aInput}, new ItemStack[] {rOutput}, null, null, null, null, 64, 16, 0);
				}
				if (IL.FR_Tree_Sapling.equal(aInput, T, T)) try {
					Object tIndividual = AlleleManager.alleleRegistry.getIndividual(aInput);
					if (tIndividual == null || !((IIndividual)tIndividual).analyze()) return new Recipe(F, F, F, new ItemStack[] {aInput}, new ItemStack[] {aInput}, null, null, null, null, 1, 16, 0);
					ItemStack rOutput = ST.copy(aInput);
					((IIndividual)tIndividual).writeToNBT(UT.NBT.getOrCreate(rOutput));
					return new Recipe(F, F, F, new ItemStack[] {aInput}, new ItemStack[] {rOutput}, null, null, null, null, 64, 16, 0);
				} catch(Throwable e) {e.printStackTrace(ERR);}
			}
		}
		return rRecipe;
	}
}