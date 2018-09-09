package gregapi.recipes.maps;

import static gregapi.data.CS.*;

import java.util.Collection;

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
public class RecipeMapFormingPress extends RecipeMap {
	public RecipeMapFormingPress(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (aInputs == null || aInputs.length < 2 || aInputs[0] == null || aInputs[1] == null || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		if (rRecipe == null) {
			if (IL.Shape_Mold_Name.equal(aInputs[0], F, T)) {
				ItemStack tOutput = ST.amount(1, aInputs[1]);
				tOutput.setStackDisplayName(aInputs[0].getDisplayName());
				return new Recipe(F, F, F, new ItemStack[] {IL.Shape_Mold_Name.get(0), ST.amount(1, aInputs[1])}, new ItemStack[] {tOutput}, null, null, null, null, 128, 8, 0);
			}
			if (IL.Shape_Mold_Name.equal(aInputs[1], F, T)) {
				ItemStack tOutput = ST.amount(1, aInputs[0]);
				tOutput.setStackDisplayName(aInputs[1].getDisplayName());
				return new Recipe(F, F, F, new ItemStack[] {IL.Shape_Mold_Name.get(0), ST.amount(1, aInputs[0])}, new ItemStack[] {tOutput}, null, null, null, null, 128, 8, 0);
			}
			return null;
		}
		for (ItemStack aMold : aInputs) {
			if (IL.Shape_Mold_Credit.equal(aMold, F, T)) {
				NBTTagCompound tNBT = aMold.getTagCompound();
				if (tNBT == null) tNBT = UT.NBT.make();
				if (!tNBT.hasKey("credit_security_id")) UT.NBT.setNumber(tNBT, "credit_security_id", System.nanoTime());
				UT.NBT.set(aMold, tNBT);
				
				rRecipe = rRecipe.copy();
				rRecipe.mCanBeBuffered = F;
				UT.NBT.set(rRecipe.mOutputs[0], tNBT);
				return rRecipe;
			}
		}
		return rRecipe;
	}
}