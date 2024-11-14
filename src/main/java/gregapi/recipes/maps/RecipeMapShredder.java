/**
 * Copyright (c) 2024 GregTech-6 Team
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

import gregapi.code.ArrayListNoNulls;
import gregapi.data.FL;
import gregapi.data.TD;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.recipes.Recipe;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;

import java.util.Collection;
import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapShredder extends RecipeMapSpecialSingleInput {
	public RecipeMapShredder(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower, boolean aUseBucketSizeIn, boolean aUseBucketSizeOut) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, aUseBucketSizeIn, aUseBucketSizeOut);
	}
	
	@Override
	public Recipe getRecipeFor(ItemStack aInput) {
		OreDictItemData aData = OM.anydata(aInput);
		if (aData == null || (aData.mPrefix != null && (!aData.mPrefix.contains(TD.Prefix.RECYCLABLE) || aData.mPrefix.containsAny(TD.Prefix.DUST_BASED, TD.Prefix.ORE, TD.Prefix.ORE_PROCESSING_BASED, TD.Prefix.IS_CONTAINER))) || (aData.mMaterial != null && aData.mMaterial.mMaterial.contains(TD.Atomic.ANTIMATTER)) || FL.getFluid(aInput, T) != null) return null;
		List<OreDictMaterialStack> tList = new ArrayListNoNulls<>();
		for (OreDictMaterialStack tMaterial : aData.getAllMaterialStacks()) if (tMaterial.mMaterial.mTargetPulver.mAmount > 0) OM.stack(UT.Code.units(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetPulver.mAmount, F), tMaterial.mMaterial.mTargetPulver.mMaterial).addToList(tList);
		if (tList.isEmpty()) return null;
		ItemStack[] tOutputs = new ItemStack[Math.min(tList.size(), mOutputItemsCount)];
		int i = 0, tDuration = 0;
		for (OreDictMaterialStack tMaterial : tList) {
			tDuration += UT.Code.units(tMaterial.mAmount, U, (tMaterial.mMaterial.containsAny(TD.Properties.QUARTZ)?64:tMaterial.mMaterial.containsAny(TD.Properties.BRITTLE, TD.Properties.STRETCHY, TD.Properties.BOUNCY, TD.Properties.STONE, TD.Properties.WOOD, TD.Properties.FOOD, TD.Properties.MEAT, TD.Properties.ROTTEN)?2:256)*Math.max(1, tMaterial.mMaterial.mToolQuality+1), T);
			if (i < tOutputs.length) {
				ItemStack tStack = OM.dust(tMaterial);
				if (tStack != null) tOutputs[i++] = tStack;
			}
		}
		if (!UT.Code.exists(0, tOutputs)) return null;
		return new Recipe(F, F, F, ST.array(ST.amount(1, aInput)), tOutputs, null, null, ZL_FS, ZL_FS, Math.max(1, tDuration), 16, 0);
	}
}
