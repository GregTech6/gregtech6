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

package gregapi.tileentity.machines;

import static gregapi.data.CS.*;

import gregapi.recipes.Recipe;
import gregapi.tileentity.data.ITileEntityProgress;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityProcessor extends ITileEntityProgress {
	public ProcessorData getProcessingData(ItemStack[] aInputs, FluidStack[] aFluids);
	
	public static abstract class ProcessorData {
		public ItemStack [] getItemInputs  () {return ZL_IS;}
		public FluidStack[] getFluidInputs () {return ZL_FS;}
		public ItemStack [] getItemOutputs () {return ZL_IS;}
		public FluidStack[] getFluidOutputs() {return ZL_FS;}
		public byte getValidItemInputSide  (ItemStack  aStack) {return SIDE_ANY;}
		public byte getValidFluidInputSide (FluidStack aFluid) {return SIDE_ANY;}
		public byte getValidItemOutputSide (ItemStack  aStack) {return SIDE_ANY;}
		public byte getValidFluidOutputSide(FluidStack aFluid) {return SIDE_ANY;}
	}
	
	public static class ProcessorDataRecipe {
		public final byte mItemInputSide, mFluidInputSide, mItemOutputSide, mFluidOutputSide;
		public final Recipe mRecipe;
		
		public ProcessorDataRecipe(Recipe aRecipe, byte aItemInputSide, byte aFluidInputSide, byte aItemOutputSide, byte aFluidOutputSide) {
			mItemInputSide = aItemInputSide;
			mFluidInputSide = aFluidInputSide;
			mItemOutputSide = aItemOutputSide;
			mFluidOutputSide = aFluidOutputSide;
			mRecipe = aRecipe;
		}
		
		public ItemStack [] getItemInputs  () {return mRecipe.mInputs;}
		public FluidStack[] getFluidInputs () {return mRecipe.mFluidInputs;}
		public ItemStack [] getItemOutputs () {return mRecipe.mOutputs;}
		public FluidStack[] getFluidOutputs() {return mRecipe.mFluidOutputs;}
		public byte getValidItemInputSide  (ItemStack  aStack) {return mItemInputSide;}
		public byte getValidFluidInputSide (FluidStack aFluid) {return mFluidInputSide;}
		public byte getValidItemOutputSide (ItemStack  aStack) {return mItemInputSide;}
		public byte getValidFluidOutputSide(FluidStack aFluid) {return mFluidInputSide;}
	}
	
	public static class ProcessorDataDefault {
		public final byte mItemInputSide, mFluidInputSide, mItemOutputSide, mFluidOutputSide;
		public final ItemStack[] mItemInputs, mItemOutputs;
		public final FluidStack[] mFluidInputs, mFluidOutputs;
		
		public ProcessorDataDefault(ItemStack[] aItemInputs, FluidStack[] aFluidInputs, ItemStack[] aItemOutputs, FluidStack[] aFluidOutputs, byte aItemInputSide, byte aFluidInputSide, byte aItemOutputSide, byte aFluidOutputSide) {
			mItemInputSide = aItemInputSide;
			mFluidInputSide = aFluidInputSide;
			mItemOutputSide = aItemOutputSide;
			mFluidOutputSide = aFluidOutputSide;
			mItemInputs = aItemInputs;
			mFluidInputs = aFluidInputs;
			mItemOutputs = aItemOutputs;
			mFluidOutputs = aFluidOutputs;
		}
		
		public ItemStack [] getItemInputs  () {return mItemInputs;}
		public FluidStack[] getFluidInputs () {return mFluidInputs;}
		public ItemStack [] getItemOutputs () {return mItemOutputs;}
		public FluidStack[] getFluidOutputs() {return mFluidOutputs;}
		public byte getValidItemInputSide  (ItemStack  aStack) {return mItemInputSide;}
		public byte getValidFluidInputSide (FluidStack aFluid) {return mFluidInputSide;}
		public byte getValidItemOutputSide (ItemStack  aStack) {return mItemOutputSide;}
		public byte getValidFluidOutputSide(FluidStack aFluid) {return mFluidOutputSide;}
	}
}
