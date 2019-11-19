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

package gregapi.old;

import static gregapi.data.CS.*;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

@Deprecated
@SuppressWarnings("deprecation")
public interface IGT_RecipeAdder {
	public boolean addFusionReactorRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, long aFusionDurationInTicks, long aFusionEnergyPerTick, long aEnergyNeededForStartingFusion);
	public boolean addCentrifugeRecipe(ItemStack aInput1, long aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, ItemStack aOutput5, ItemStack aOutput6, long aDuration);
	public boolean addCentrifugeRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, ItemStack aOutput5, ItemStack aOutput6, long[] aChances, long aDuration, long aEUt);
	public boolean addElectrolyzerRecipe(ItemStack aInput1, long aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, ItemStack aOutput5, ItemStack aOutput6, long aDuration, long aEUt);
	public boolean addElectrolyzerRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, ItemStack aOutput5, ItemStack aOutput6, long[] aChances, long aDuration, long aEUt);
	public boolean addChemicalRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput, long aDuration);
	public boolean addChemicalRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput, long aDuration);
	public boolean addBlastRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, long aDuration, long aEUt, long aLevel);
	public boolean addBlastRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput1, ItemStack aOutput2, long aDuration, long aEUt, long aLevel);
	public boolean addCannerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, long aDuration, long aEUt);
	public boolean addAlloySmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, long aDuration, long aEUt);
	public boolean addCNCRecipe(ItemStack aInput1, ItemStack aOutput1, long aDuration, long aEUt);
	public boolean addAssemblerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, long aDuration, long aEUt);
	public boolean addAssemblerRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, ItemStack aOutput1, long aDuration, long aEUt);
	public boolean addForgeHammerRecipe(ItemStack aInput1, ItemStack aOutput1, long aDuration, long aEUt);
	public boolean addWiremillRecipe(ItemStack aInput, ItemStack aOutput, long aDuration, long aEUt);
	public boolean addPolarizerRecipe(ItemStack aInput, ItemStack aOutput, long aDuration, long aEUt);
	public boolean addBenderRecipe(ItemStack aInput, ItemStack aOutput, long aDuration, long aEUt);
	public boolean addExtruderRecipe(ItemStack aInput, ItemStack aShape, ItemStack aOutput, long aDuration, long aEUt);
	public boolean addSlicerRecipe(ItemStack aInput, ItemStack aShape, ItemStack aOutput, long aDuration, long aEUt);
	public boolean addImplosionRecipe(ItemStack aInput1, long aInput2, ItemStack aOutput1, ItemStack aOutput2);
	public boolean addGrinderRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4);
	public boolean addDistillationRecipe(ItemStack aInput1, long aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, long aDuration, long aEUt);
	public boolean addLatheRecipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2, long aDuration, long aEUt);
	public boolean addCutterRecipe(ItemStack aInput, FluidStack aLubricant, ItemStack aOutput1, ItemStack aOutput2, long aDuration, long aEUt);
	public boolean addCutterRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, long aDuration, long aEUt);
	public boolean addBoxingRecipe(ItemStack aContainedItem, ItemStack aEmptyBox, ItemStack aFullBox, long aDuration, long aEUt);
	public boolean addUnboxingRecipe(ItemStack aFullBox, ItemStack aContainedItem, ItemStack aEmptyBox, long aDuration, long aEUt);
	public boolean addVacuumFreezerRecipe(ItemStack aInput1, ItemStack aOutput1, long aDuration);
	public boolean addFuel(ItemStack aInput1, ItemStack aOutput1, long aEU, long aType);
	public boolean addAmplifier(ItemStack aAmplifierItem, long aDuration, long aAmplifierAmountOutputted);
	public boolean addBrewingRecipe(ItemStack aIngredient, Fluid aInput, Fluid aOutput, boolean aHidden);
	public boolean addFermentingRecipe(FluidStack aInput, FluidStack aOutput, long aDuration, boolean aHidden);
	public boolean addFluidHeaterRecipe(ItemStack aCircuit, FluidStack aInput, FluidStack aOutput, long aDuration, long aEUt);
	public boolean addDistilleryRecipe(ItemStack aCircuit, FluidStack aInput, FluidStack aOutput, long aDuration, long aEUt, boolean aHidden);
	public boolean addFluidSolidifierRecipe(ItemStack aMold, FluidStack aInput, ItemStack aOutput, long aDuration, long aEUt);
	public boolean addFluidSmelterRecipe(ItemStack aInput, ItemStack aRemains, FluidStack aOutput, long aChance, long aDuration, long aEUt);
	public boolean addFluidExtractionRecipe(ItemStack aInput, ItemStack aRemains, FluidStack aOutput, long aChance, long aDuration, long aEUt);
	public boolean addFluidCannerRecipe(ItemStack aInput, ItemStack aOutput, FluidStack aFluidInput, FluidStack aFluidOutput);
	public boolean addChemicalBathRecipe(ItemStack aInput, FluidStack aBathingFluid, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, long[] aChances, long aDuration, long aEUt);
	public boolean addElectromagneticSeparatorRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, long[] aChances, long aDuration, long aEUt);
	public boolean addPrinterRecipe(ItemStack aInput, FluidStack aFluid, ItemStack aSpecialSlot, ItemStack aOutput, long aDuration, long aEUt);
	public boolean addAutoclaveRecipe(ItemStack aInput, FluidStack aFluid, ItemStack aOutput, long aChance, long aDuration, long aEUt);
	public boolean addMixerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aInput3, ItemStack aInput4, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput, long aDuration, long aEUt);
	public boolean addLaserEngraverRecipe(ItemStack aItemToEngrave, ItemStack aLens, ItemStack aEngravedItem, long aDuration, long aEUt);
	public boolean addFormingPressRecipe(ItemStack aItemToImprint, ItemStack aForm, ItemStack aImprintedItem, long aDuration, long aEUt);
	public boolean addSifterRecipe(ItemStack aItemToSift, ItemStack[] aSiftedItems, long[] aChances, long aDuration, long aEUt);
	public boolean addArcFurnaceRecipe(ItemStack aInput, ItemStack[] aOutputs, long[] aChances, long aDuration, long aEUt);
	public boolean addPulveriserRecipe(ItemStack aInput, ItemStack[] aOutputs, long[] aChances, long aDuration, long aEUt);
	public boolean addShredderRecipe(ItemStack aInput, ItemStack[] aOutputs, long[] aChances, long aDuration, long aEUt);
	public boolean addCrusherRecipe(ItemStack aInput, ItemStack[] aOutputs, long[] aChances, long aDuration, long aEUt);
	public boolean addSharpeningRecipe(ItemStack aItemToSharpen, ItemStack aSharpenedItem, ItemStack aRemainingDust, long aDustChance, long aDuration, long aEUt);
	
	public static final IGT_RecipeAdder NON_WORKING = new NonWorkingRecipeAdder();
	
	public static class NonWorkingRecipeAdder implements IGT_RecipeAdder {
		@Override public boolean addFusionReactorRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, long aFusionDurationInTicks, long aFusionEnergyPerTick, long aEnergyNeededForStartingFusion) {return F;}
		@Override public boolean addCentrifugeRecipe(ItemStack aInput1, long aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, ItemStack aOutput5, ItemStack aOutput6, long aDuration) {return F;}
		@Override public boolean addCentrifugeRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, ItemStack aOutput5, ItemStack aOutput6, long[] aChances, long aDuration, long aEUt) {return F;}
		@Override public boolean addElectrolyzerRecipe(ItemStack aInput1, long aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, ItemStack aOutput5, ItemStack aOutput6, long aDuration, long aEUt) {return F;}
		@Override public boolean addElectrolyzerRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, ItemStack aOutput5, ItemStack aOutput6, long[] aChances, long aDuration, long aEUt) {return F;}
		@Override public boolean addChemicalRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput, long aDuration) {return F;}
		@Override public boolean addChemicalRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput, long aDuration) {return F;}
		@Override public boolean addBlastRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, long aDuration, long aEUt, long aLevel) {return F;}
		@Override public boolean addBlastRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput1, ItemStack aOutput2, long aDuration, long aEUt, long aLevel) {return F;}
		@Override public boolean addCannerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, long aDuration, long aEUt) {return F;}
		@Override public boolean addAlloySmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, long aDuration, long aEUt) {return F;}
		@Override public boolean addCNCRecipe(ItemStack aInput1, ItemStack aOutput1, long aDuration, long aEUt) {return F;}
		@Override public boolean addAssemblerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, long aDuration, long aEUt) {return F;}
		@Override public boolean addAssemblerRecipe(ItemStack aInput1, ItemStack aInput2, FluidStack aFluidInput, ItemStack aOutput1, long aDuration, long aEUt) {return F;}
		@Override public boolean addForgeHammerRecipe(ItemStack aInput1, ItemStack aOutput1, long aDuration, long aEUt) {return F;}
		@Override public boolean addWiremillRecipe(ItemStack aInput, ItemStack aOutput, long aDuration, long aEUt) {return F;}
		@Override public boolean addPolarizerRecipe(ItemStack aInput, ItemStack aOutput, long aDuration, long aEUt) {return F;}
		@Override public boolean addBenderRecipe(ItemStack aInput, ItemStack aOutput, long aDuration, long aEUt) {return F;}
		@Override public boolean addExtruderRecipe(ItemStack aInput, ItemStack aShape, ItemStack aOutput, long aDuration, long aEUt) {return F;}
		@Override public boolean addSlicerRecipe(ItemStack aInput, ItemStack aShape, ItemStack aOutput, long aDuration, long aEUt) {return F;}
		@Override public boolean addImplosionRecipe(ItemStack aInput1, long aInput2, ItemStack aOutput1, ItemStack aOutput2) {return F;}
		@Override public boolean addGrinderRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4) {return F;}
		@Override public boolean addDistillationRecipe(ItemStack aInput1, long aInput2, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, ItemStack aOutput4, long aDuration, long aEUt) {return F;}
		@Override public boolean addLatheRecipe(ItemStack aInput1, ItemStack aOutput1, ItemStack aOutput2, long aDuration, long aEUt) {return F;}
		@Override public boolean addCutterRecipe(ItemStack aInput, FluidStack aLubricant, ItemStack aOutput1, ItemStack aOutput2, long aDuration, long aEUt) {return F;}
		@Override public boolean addCutterRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, long aDuration, long aEUt) {return F;}
		@Override public boolean addBoxingRecipe(ItemStack aContainedItem, ItemStack aEmptyBox, ItemStack aFullBox, long aDuration, long aEUt) {return F;}
		@Override public boolean addUnboxingRecipe(ItemStack aFullBox, ItemStack aContainedItem, ItemStack aEmptyBox, long aDuration, long aEUt) {return F;}
		@Override public boolean addVacuumFreezerRecipe(ItemStack aInput1, ItemStack aOutput1, long aDuration) {return F;}
		@Override public boolean addFuel(ItemStack aInput1, ItemStack aOutput1, long aEU, long aType) {return F;}
		@Override public boolean addAmplifier(ItemStack aAmplifierItem, long aDuration, long aAmplifierAmountOutputted) {return F;}
		@Override public boolean addBrewingRecipe(ItemStack aIngredient, Fluid aInput, Fluid aOutput, boolean aHidden) {return F;}
		@Override public boolean addFermentingRecipe(FluidStack aInput, FluidStack aOutput, long aDuration, boolean aHidden) {return F;}
		@Override public boolean addFluidHeaterRecipe(ItemStack aCircuit, FluidStack aInput, FluidStack aOutput, long aDuration, long aEUt) {return F;}
		@Override public boolean addDistilleryRecipe(ItemStack aCircuit, FluidStack aInput, FluidStack aOutput, long aDuration, long aEUt, boolean aHidden) {return F;}
		@Override public boolean addFluidSolidifierRecipe(ItemStack aMold, FluidStack aInput, ItemStack aOutput, long aDuration, long aEUt) {return F;}
		@Override public boolean addFluidSmelterRecipe(ItemStack aInput, ItemStack aRemains, FluidStack aOutput, long aChance, long aDuration, long aEUt) {return F;}
		@Override public boolean addFluidExtractionRecipe(ItemStack aInput, ItemStack aRemains, FluidStack aOutput, long aChance, long aDuration, long aEUt) {return F;}
		@Override public boolean addFluidCannerRecipe(ItemStack aInput, ItemStack aOutput, FluidStack aFluidInput, FluidStack aFluidOutput) {return F;}
		@Override public boolean addChemicalBathRecipe(ItemStack aInput, FluidStack aBathingFluid, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, long[] aChances, long aDuration, long aEUt) {return F;}
		@Override public boolean addElectromagneticSeparatorRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2, ItemStack aOutput3, long[] aChances, long aDuration, long aEUt) {return F;}
		@Override public boolean addPrinterRecipe(ItemStack aInput, FluidStack aFluid, ItemStack aSpecialSlot, ItemStack aOutput, long aDuration, long aEUt) {return F;}
		@Override public boolean addAutoclaveRecipe(ItemStack aInput, FluidStack aFluid, ItemStack aOutput, long aChance, long aDuration, long aEUt) {return F;}
		@Override public boolean addMixerRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aInput3, ItemStack aInput4, FluidStack aFluidInput, FluidStack aFluidOutput, ItemStack aOutput, long aDuration, long aEUt) {return F;}
		@Override public boolean addLaserEngraverRecipe(ItemStack aItemToEngrave, ItemStack aLens, ItemStack aEngravedItem, long aDuration, long aEUt) {return F;}
		@Override public boolean addFormingPressRecipe(ItemStack aItemToImprint, ItemStack aForm, ItemStack aImprintedItem, long aDuration, long aEUt) {return F;}
		@Override public boolean addSifterRecipe(ItemStack aItemToSift, ItemStack[] aSiftedItems, long[] aChances, long aDuration, long aEUt) {return F;}
		@Override public boolean addArcFurnaceRecipe(ItemStack aInput, ItemStack[] aOutputs, long[] aChances, long aDuration, long aEUt) {return F;}
		@Override public boolean addPulveriserRecipe(ItemStack aInput, ItemStack[] aOutputs, long[] aChances, long aDuration, long aEUt) {return F;}
		@Override public boolean addShredderRecipe(ItemStack aInput, ItemStack[] aOutputs, long[] aChances, long aDuration, long aEUt) {return F;}
		@Override public boolean addCrusherRecipe(ItemStack aInput, ItemStack[] aOutputs, long[] aChances, long aDuration, long aEUt) {return F;}
		@Override public boolean addSharpeningRecipe(ItemStack aItemToSharpen, ItemStack aSharpenedItem, ItemStack aRemainingDust, long aDustChance, long aDuration, long aEUt) {return F;}
	}
}
