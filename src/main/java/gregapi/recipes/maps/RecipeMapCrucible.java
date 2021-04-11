/**
 * Copyright (c) 2021 GregTech-6 Team
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

package gregapi.recipes.maps;

import static gregapi.data.CS.*;
import static gregapi.data.TD.Processing.*;

import java.util.Collection;
import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.recipes.Recipe;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapCrucible extends RecipeMapSpecialSingleInput {
	public RecipeMapCrucible(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower, boolean aUseBucketSizeIn, boolean aUseBucketSizeOut) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, aUseBucketSizeIn, aUseBucketSizeOut);
	}
	
	@Override
	public List<Recipe> getNEIRecipes(ItemStack... aOutputs) {
		List<Recipe> rList = super.getNEIRecipes(aOutputs);
		for (ItemStack aOutput : aOutputs) {
			OreDictItemData aData = OM.anydata(aOutput);
			if (aData == null || !aData.hasValidPrefixMaterialData() || !aData.mPrefix.contains(TD.Prefix.INGOT_BASED)) continue;
			for (OreDictMaterial tMat : aData.mMaterial.mMaterial.mTargetedSmelting) if (tMat.mTargetSmelting.mMaterial == aData.mMaterial.mMaterial) {
				if (tMat != aData.mMaterial.mMaterial) {
				rList.add(getRecipeFor(OP.ingot             .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.blockIngot        .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.gem               .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.blockGem          .mat(tMat, 1)));
				}
				rList.add(getRecipeFor(OP.dust              .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.blockDust         .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.crushed           .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.crushedPurified   .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.crushedCentrifuged.mat(tMat, 1)));
				rList.add(getRecipeFor(OP.chunk             .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.rubble            .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.pebbles           .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.cluster           .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.cleanGravel       .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.dirtyGravel       .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.crystalline       .mat(tMat, 1)));
				rList.add(getRecipeFor(OP.reduced           .mat(tMat, 1)));
			}
		}
		return rList;
	}
	
	@Override
	protected Recipe getRecipeFor(ItemStack aInput) {
		OreDictItemData aData = OM.anydata(aInput);
		if (aData == null) return null;
		
		List<OreDictMaterialStack> tList = new ArrayListNoNulls<>();
		for (OreDictMaterialStack tMaterial : aData.getAllMaterialStacks()) if (tMaterial.mMaterial.mTargetSmelting.mAmount > 0 && tMaterial.mMaterial.contains(MELTING)) OM.stack(UT.Code.units(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetSmelting.mAmount, F), tMaterial.mMaterial.mTargetSmelting.mMaterial).addToList(tList);
		if (tList.isEmpty()) return null;
		
		ArrayListNoNulls<ItemStack> tIngots = new ArrayListNoNulls<>();
		for (OreDictMaterialStack tMaterial : tList) tIngots.add(OM.ingotOrDust(tMaterial.mMaterial, tMaterial.mAmount));
		
		if (tIngots.isEmpty()) return null;
		return RM.CrucibleSmelting.addFakeRecipe(F, ST.array(ST.amount(1, aInput)), tIngots.toArray(ZL_IS), null, null, null, null, 0, 0, aData.mMaterial.mMaterial.mMeltingPoint);
	}
}
