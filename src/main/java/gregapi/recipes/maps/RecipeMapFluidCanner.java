package gregapi.recipes.maps;

import static gregapi.data.CS.*;

import java.util.Collection;

import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapFluidCanner extends RecipeMap {
	public RecipeMapFluidCanner(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs);
		mMaxFluidInputSize = 128000;
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		Recipe rRecipe = super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (aInputs == null || aInputs.length <= 0 || rRecipe != null || GAPI_POST.mFinishedServerStarted <= 0) return rRecipe;
		for (ItemStack tInput : aInputs) if (ST.valid(tInput)) {
			FluidStack tFluid = UT.Fluids.getFluidForFilledItem(tInput, T);
			if (tFluid != null) {
				return FL.Error.is(tFluid) ? null : new Recipe(F, F, F, new ItemStack[] {ST.amount(1, tInput)}, new ItemStack[] {ST.container(tInput, T)}, null, null, ZL_FS, new FluidStack[] {tFluid}, Math.max(tFluid.amount / 64, 16), 16, 0);
			} else if (aFluids != null && aFluids.length > 0 && aFluids[0] != null && !FL.Error.is(aFluids[0])) {
				if ((MD.GC.mLoaded || MD.GC_GALAXYSPACE.mLoaded) && FL.Liquid_Oxygen.is(aFluids[0])) {
					if (IL.GC_OxyTank_1.equal(tInput, T, T) || IL.GC_OxyTank_2.equal(tInput, T, T) || IL.GC_OxyTank_3.equal(tInput, T, T) || IL.GC_OxyTank_4.equal(tInput, T, T) || IL.GC_OxyTank_5.equal(tInput, T, T) || IL.GC_OxyTank_6.equal(tInput, T, T) || IL.GC_OxyTank_Env.equal(tInput, T, T)) {
						short tMeta = ST.meta(tInput);
						return new Recipe(F, F, F, new ItemStack[] {ST.amount(1, tInput)}, new ItemStack[] {ST.copyAmountAndMeta(1, 0, tInput)}, null, null, new FluidStack[] {tMeta <= 0 ? NF : FL.Liquid_Oxygen.make(UT.Code.units(tMeta, 2700, 250, T))}, ZL_FS, tMeta <= 0 ? 1 : 64, 16, 0);
					}
				}
				ItemStack tOutput = UT.Fluids.fillFluidContainer(aFluids[0], tInput, F, T, F, F);
				tFluid = UT.Fluids.getFluidForFilledItem(tOutput, T);
				if (tFluid != null) return new Recipe(F, F, F, new ItemStack[] {ST.amount(1, tInput)}, new ItemStack[] {tOutput}, null, null, new FluidStack[] {tFluid}, ZL_FS, Math.max(tFluid.amount / 64, 16), 16, 0);
			}
		}
		return rRecipe;
	}
	
	@Override public boolean containsInput(ItemStack aStack, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return aStack != null && (super.containsInput(aStack, aTileEntity, aSpecialSlot) || (aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) > 0));}
	@Override public boolean containsInput(FluidStack aFluid, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return T;}
	@Override public boolean containsInput(Fluid aFluid, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return T;}
}