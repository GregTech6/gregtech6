/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregtech.tileentity.multiblocks;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.FM;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.fluid.FluidTankGT;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityLargeTurbineGas extends MultiTileEntityLargeTurbine {
	public FluidTankGT mInputTank = new FluidTankGT(Integer.MAX_VALUE), mTanksOutput[] = new FluidTankGT[] {new FluidTankGT(Integer.MAX_VALUE), new FluidTankGT(Integer.MAX_VALUE), new FluidTankGT(Integer.MAX_VALUE)};
	public FluidTankGT[] mTanks = new FluidTankGT[] {mInputTank, mTanksOutput[0], mTanksOutput[1], mTanksOutput[2]};
	public RecipeMap mRecipes = FM.Gas;
	public Recipe mLastRecipe = null;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_FUELMAP)) mRecipes = RecipeMap.RECIPE_MAPS.get(aNBT.getString(NBT_RECIPEMAP));
		for (int i = 0; i < mTanksOutput.length; i++)
		mTanksOutput[i].readFromNBT(aNBT, NBT_TANK+"."+i).setCapacity((int)UT.Code.bind_(1, Integer.MAX_VALUE, mEnergyIN.mMax*4));;
		mInputTank.readFromNBT(aNBT, NBT_TANK).setCapacity((int)UT.Code.bind_(1, Integer.MAX_VALUE, mEnergyIN.mMax*4));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		for (int i = 0; i < mTanksOutput.length; i++)
		mTanksOutput[i].writeToNBT(aNBT, NBT_TANK+"."+i);
		mInputTank.writeToNBT(aNBT, NBT_TANK);
	}
	
	static {
		LH.add("gt.tooltip.multiblock.gasturbine.1", "3x3x4 of the Block you crafted this one with");
		LH.add("gt.tooltip.multiblock.gasturbine.2", "Main centered on the 3x3 facing outwards");
		LH.add("gt.tooltip.multiblock.gasturbine.3", "Input only possible at Main Block");
		LH.add("gt.tooltip.multiblock.gasturbine.4", "Exhaust Gas has to be removed!");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.gasturbine.1"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.gasturbine.2"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.gasturbine.3"));
		aList.add(Chat.ORANGE   + LH.get("gt.tooltip.multiblock.gasturbine.4"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void addToolTipsEnergy(List<String> aList, ItemStack aStack, boolean aF3_H) {
		mEnergyOUT.addToolTips(aList, aStack, aF3_H, null, T);
	}
	
	@Override
	public void doConversion(long aTimer) {
		mStopped = F; // TODO
		DEB.println("======");
		DEB.println("TEST A: " + (mStorage.mEnergy < mConverter.mEnergyIN.mMax) + ", " + !mInputTank.isEmpty() + ", " + !(mTanksOutput[0].isHalf() || mTanksOutput[1].isHalf() || mTanksOutput[2].isHalf()));
		if (mStorage.mEnergy < mConverter.mEnergyIN.mMax && !mInputTank.isEmpty() && !(mTanksOutput[0].isHalf() || mTanksOutput[1].isHalf() || mTanksOutput[2].isHalf())) {
			DEB.println("TEST B");
			Recipe tRecipe = mRecipes.findRecipe(this, mLastRecipe, F, mEnergyIN.mMax, NI, mInputTank.AS_ARRAY, ZL_IS);
			if (tRecipe != null) {
				DEB.println("TEST C: " + tRecipe.mEUt + " HU/t, " + tRecipe.mDuration + " ticks");
				mLastRecipe = tRecipe;
				if (tRecipe.mEUt < 0 && tRecipe.mDuration > 0) {
					int tParallel = tRecipe.isRecipeInputEqual(UT.Code.bindInt(UT.Code.divup(mEnergyIN.mMax - mStorage.mEnergy, -tRecipe.mEUt * tRecipe.mDuration)), mInputTank.AS_ARRAY, ZL_IS);
					DEB.println("TEST D: " + tParallel);
					if (tParallel > 0) {
						DEB.println("TEST E: " + (tParallel * tRecipe.mEUt * tRecipe.mDuration));
						mStorage.mEnergy -= tParallel * tRecipe.mEUt * tRecipe.mDuration;
						for (int i = 0; i < tRecipe.mFluidOutputs.length && i < mTanksOutput.length; i++) {
							DEB.println("TEST F:" + i);
							if (!mTanksOutput[i].fillAll(tRecipe.mFluidOutputs[i], tParallel)) {
								DEB.println("TEST G");
								mStorage.mEnergy = 0;
								mStopped = T;
							}
						}
					}
				}
			}
		}
		DEB.println("======");
		mStopped = F; // TODO
		super.doConversion(aTimer);
	}
	
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return /* TODO !mStopped && */mRecipes.containsInput(aFluidToFill, this, NI) ? mInputTank : null;}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTanks;}
	
	@Override
	protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {
		if (aFluidToDrain == null) {
			for (int i = 0; i < mTanksOutput.length; i++) if (!mTanksOutput[i].isEmpty()) return mTanksOutput[i];
		} else {
			for (int i = 0; i < mTanksOutput.length; i++) if (UT.Fluids.equal(aFluidToDrain, mTanksOutput[i].getFluid(), F)) return mTanksOutput[i];
		}
		return null;
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.turbine.gas";}
}
