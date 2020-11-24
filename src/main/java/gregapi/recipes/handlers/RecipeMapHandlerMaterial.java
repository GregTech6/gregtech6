/**
 * Copyright (c) 2020 GregTech-6 Team
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

package gregapi.recipes.handlers;

import static gregapi.data.CS.*;

import gregapi.code.ICondition;
import gregapi.data.FL;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.recipes.IRecipeMapHandler.RecipeMapHandler;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
@SuppressWarnings("rawtypes")
public class RecipeMapHandlerMaterial extends RecipeMapHandler {
	private ICondition mCondition;
	private final OreDictMaterial mInputMaterial, mOutputMaterial;
	private final ItemStack mAdditionalInput;
	private final FluidStack mFluidInputPerUnit, mFluidOutputPerUnit;
	private final long mEUt, mDuration;
	private final boolean mAllowToGenerateAllRecipesAtOnce;
	
	private boolean mAlreadyAddedAllRecipes = F;
	
	public RecipeMapHandlerMaterial(OreDictMaterial aInputMaterial, FluidStack aFluidInputPerUnit, long aEUt, long aDuration, FluidStack aFluidOutputPerUnit, OreDictMaterial aOutputMaterial, ItemStack aAdditionalInput, boolean aAllowToGenerateAllRecipesAtOnce, ICondition aCondition) {
		mAllowToGenerateAllRecipesAtOnce = aAllowToGenerateAllRecipesAtOnce;
		mFluidInputPerUnit  = aFluidInputPerUnit;
		mFluidOutputPerUnit = aFluidOutputPerUnit;
		mCondition          = (aCondition == null ? ICondition.TRUE : aCondition);
		mInputMaterial      = aInputMaterial;
		mOutputMaterial     = aOutputMaterial;
		mAdditionalInput    = aAdditionalInput;
		mDuration           = aDuration;
		mEUt                = aEUt;
	}
	
	@Override
	public boolean addRecipesUsing(RecipeMap aMap, boolean aNEI, ItemStack aStack, OreDictItemData aData) {
		if (isDone()) return F;
		if (ST.equal(aStack, mAdditionalInput)) return aNEI && mAllowToGenerateAllRecipesAtOnce && addAllRecipesInternal(aMap);
		return aData != null && aData.hasValidPrefixMaterialData() && aData.mMaterial.mMaterial == mInputMaterial && addRecipeForPrefix(aMap, aData.mPrefix);
	}
	
	@Override
	public boolean addRecipesProducing(RecipeMap aMap, boolean aNEI, ItemStack aStack, OreDictItemData aData) {
		if (isDone()) return F;
		return aData != null && aData.hasValidPrefixMaterialData() && aData.mMaterial.mMaterial == mOutputMaterial && addRecipeForPrefix(aMap, aData.mPrefix);
	}
	
	@Override
	public boolean addRecipesUsing(RecipeMap aMap, boolean aNEI, Fluid aFluid) {
		if (isDone()) return F;
		return aNEI && mAllowToGenerateAllRecipesAtOnce && mFluidInputPerUnit != null && mFluidInputPerUnit.getFluid() == aFluid && addAllRecipesInternal(aMap);
	}
	
	@Override
	public boolean addRecipesProducing(RecipeMap aMap, boolean aNEI, Fluid aFluid) {
		if (isDone()) return F;
		return aNEI && mAllowToGenerateAllRecipesAtOnce && mFluidOutputPerUnit != null && mFluidOutputPerUnit.getFluid() == aFluid && addAllRecipesInternal(aMap);
	}
	
	@Override
	public boolean containsInput(RecipeMap aMap, Fluid aFluid) {
		return mFluidInputPerUnit != null && mFluidInputPerUnit.getFluid() == aFluid;
	}
	
	@Override
	public boolean addAllRecipes(RecipeMap aMap) {
		return mAllowToGenerateAllRecipesAtOnce && addAllRecipesInternal(aMap);
	}
	
	public boolean addAllRecipesInternal(RecipeMap aMap) {
		if (isDone()) return F;
		aMap.mConfigFile.mSaveOnEdit = F;
		for (OreDictPrefix tPrefix : OreDictPrefix.VALUES) addRecipeForPrefix(aMap, tPrefix);
		mAlreadyAddedAllRecipes = T;
		aMap.mConfigFile.mSaveOnEdit = T;
		aMap.mConfigFile.mConfig.save();
		return T;
	}
	
	@Override
	public boolean isDone() {
		return mAlreadyAddedAllRecipes;
	}
	
	@Override
	public boolean onAddedToMap(RecipeMap aMap) {
		if (mFluidInputPerUnit != null) {
			aMap.mMaxFluidInputSize = Math.max(mFluidInputPerUnit.amount * 16, aMap.mMaxFluidInputSize);
			Long tSize = aMap.mMinInputTankSizes.get(mFluidInputPerUnit.getFluid().getName());
			if (tSize == null || tSize < mFluidInputPerUnit.amount) aMap.mMinInputTankSizes.put(mFluidInputPerUnit.getFluid().getName(), (long)mFluidInputPerUnit.amount);
		}
		if (mFluidOutputPerUnit != null) {
			aMap.mMaxFluidOutputSize = Math.max(mFluidOutputPerUnit.amount * 16, aMap.mMaxFluidOutputSize);
		}
		return T;
	}
	
	@SuppressWarnings("unchecked")
	public boolean addRecipeForPrefix(RecipeMap aMap, OreDictPrefix aPrefix) {
		if (!mCondition.isTrue(aPrefix) || aPrefix.mAmount <= 0) return F;
		
		ItemStack[] tInputs = new ItemStack[mAdditionalInput==null?1:2];
		if (mAdditionalInput != null) tInputs[tInputs.length-1] = mAdditionalInput;
		tInputs[0] = aPrefix.mat(mInputMaterial, 1);
		for (ItemStack tInput : tInputs) if (ST.invalid(tInput)) return F;
		
		ItemStack tOutput = aPrefix.mat(mOutputMaterial, 1);
		if (ST.invalid(tOutput)) return F;
		
		return aMap.addRecipeX(F,T,F,F,T, mEUt, Math.max(1, getCosts(aPrefix)), tInputs, FL.mul(mFluidInputPerUnit, aPrefix.mAmount, U, T), FL.mul(mFluidOutputPerUnit, aPrefix.mAmount, U, F), tOutput) != null;
	}
	
	public long getCosts(OreDictPrefix aPrefix) {
		return UT.Code.units(aPrefix.mAmount, U, mDuration, T);
	}
}
