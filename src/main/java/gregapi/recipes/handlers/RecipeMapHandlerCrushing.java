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

package gregapi.recipes.handlers;

import static gregapi.data.CS.*;
import static gregapi.data.OP.*;

import gregapi.block.IPrefixBlock;
import gregapi.data.CS.BlocksGT;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.recipes.IRecipeMapHandler.RecipeMapHandler;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapHandlerCrushing extends RecipeMapHandler {
	public RecipeMapHandlerCrushing() {/**/}
	
	@Override
	public boolean addRecipesUsing(RecipeMap aMap, boolean aNEI, ItemStack aInput, OreDictItemData aData) {
		if (aData == null || !aData.hasValidPrefixMaterialData() || aData.mPrefix == oreBedrock || !aData.mPrefix.contains(TD.Prefix.ORE) || aData.mPrefix.containsAny(TD.Prefix.DUST_ORE, TD.Prefix.IS_CONTAINER) || aData.mMaterial.mMaterial.contains(TD.Atomic.ANTIMATTER) || IL.PFAA_Sands.equal(aInput, T, T)) return F;
		OreDictMaterial aCrushedMat = aData.mMaterial.mMaterial.mTargetCrushing.mMaterial;
		long aCrushedAmount = aData.mMaterial.mMaterial.mTargetCrushing.mAmount, aMultiplier = aData.mMaterial.mMaterial.mOreProcessingMultiplier;
		
		if (aData.mPrefix == oreNetherrack || aData.mPrefix == oreNether || aData.mPrefix == oreBasalt || aData.mPrefix == oreKomatiite || aData.mPrefix == oreDeepslate) {
			if (aData.mMaterial.mMaterial == MT.HexoriumBlack || aData.mMaterial.mMaterial == MT.HexoriumWhite) {
				aMultiplier *= (aData.mMaterial.mMaterial.mOreMultiplier + 1);
			} else if (aData.mMaterial.mMaterial == MT.HexoriumRed || aData.mMaterial.mMaterial == MT.HexoriumGreen || aData.mMaterial.mMaterial == MT.HexoriumBlue) {
				aMultiplier *= (aData.mMaterial.mMaterial.mOreMultiplier - 1);
			} else {
				aMultiplier *=  aData.mMaterial.mMaterial.mOreMultiplier;
			}
		} else if (aData.mPrefix == blockRaw) {
				aMultiplier *=  aData.mMaterial.mMaterial.mOreMultiplier * 2; // Multiply by 2, but fill out 4.5 times more Slots
		} else {
			    aMultiplier *=  aData.mMaterial.mMaterial.mOreMultiplier;
		}
		if (aData.mPrefix == orePoor) {
			ItemStack tOutput = OP.crushedTiny          .mat(aCrushedMat, UT.Code.bindStack(UT.Code.units(aCrushedAmount, U, 3 * aMultiplier, F)));
			if (tOutput == null) tOutput = OP.dustTiny  .mat(aCrushedMat, UT.Code.bindStack(UT.Code.units(aCrushedAmount, U, 3 * aMultiplier, F)));
			return ST.valid(tOutput) && null != aMap.addRecipe(new Recipe(F, F, T, ST.array(ST.amount(1, aInput)), ST.array(tOutput), null, null, ZL_FS, ZL_FS, Math.max(1, 16*tOutput.stackSize*Math.max(1, aData.mMaterial.mMaterial.mToolQuality+1)), 16, 0));
		}
		if (aData.mPrefix == oreSmall || aData.mPrefix == oreRich || aData.mPrefix == oreNormal) {
			// TODO
			return F;
		}
		ItemStack[] tOutputs = new ItemStack[aMap.mOutputItemsCount];
								 tOutputs[0] = OP.crushed.mat(aCrushedMat, UT.Code.bindStack(UT.Code.units(aCrushedAmount, U, aMultiplier, F)));
		if (tOutputs[0] == null) tOutputs[0] = OP.dust   .mat(aCrushedMat, UT.Code.bindStack(UT.Code.units(aCrushedAmount, U, aMultiplier, F)));
		if (tOutputs[0] == null) tOutputs[0] = OP.gem    .mat(aCrushedMat, UT.Code.bindStack(UT.Code.units(aCrushedAmount, U, aMultiplier, F)));
		if (tOutputs[0] == null) return F;
		long[] tChances = new long[tOutputs.length];
		int i = 1, tDuration = 128*tOutputs[0].stackSize*Math.max(1, aData.mMaterial.mMaterial.mToolQuality+1);
		tChances[i  ] = 10000;
		tOutputs[i++] = tOutputs[0];
		if (aData.mPrefix == blockRaw) {
			tChances[i  ] = 10000;
			tOutputs[i++] = tOutputs[0];
			tChances[i  ] = 10000;
			tOutputs[i++] = tOutputs[0];
			tChances[i  ] = 10000;
			tOutputs[i++] = tOutputs[0];
			tChances[i  ] = 10000;
			tOutputs[i++] = tOutputs[0];
			tChances[i  ] = 10000;
			tOutputs[i++] = tOutputs[0];
			tChances[i  ] = 10000;
			tOutputs[i++] = tOutputs[0];
			tChances[i  ] = 10000;
			tOutputs[i++] = tOutputs[0];
			tDuration *= 9;
			tDuration /= 2;
		}
		if (aData.mPrefix.contains(TD.Prefix.DENSE_ORE)) {
			tChances[i  ] = 10000;
			tOutputs[i++] = tOutputs[0];
			tChances[i  ] = 10000;
			tOutputs[i++] = tOutputs[0];
			tDuration *= 2;
		}
		if (aData.mMaterial.mMaterial.contains(TD.Processing.PULVERIZING_CINNABAR)) {
			tChances[i  ] += 2500;
		}
		if (aData.mMaterial.mMaterial.mByProducts.contains(MT.Hg)) {
			tChances[i  ] += 1000;
		}
		if (aData.mMaterial.mMaterial.mByProducts.contains(MT.OREMATS.Cinnabar)) {
			tChances[i  ] +=  500;
		}
		if (aData.mMaterial.mMaterial.mByProducts.contains(MT.Redstone)) {
			tChances[i  ] +=  500;
		}
		if (tChances[i  ] > 0) {
			tOutputs[i++] = OP.gem.mat(MT.OREMATS.Cinnabar, aData.mPrefix == blockRaw ? 9 : aData.mPrefix.contains(TD.Prefix.DENSE_ORE) ? 2 : 1);
		}
		
		for (OreDictMaterialStack tMaterial : aData.mPrefix.mByProducts) {
			tDuration += UT.Code.units(tMaterial.mAmount, U, 64*Math.max(1, tMaterial.mMaterial.mToolQuality+1), T);
			if (i < tOutputs.length) {
				ItemStack tStack = OM.dust(tMaterial.mMaterial.mTargetCrushing.mMaterial, UT.Code.units(tMaterial.mAmount, U, tMaterial.mMaterial.mTargetCrushing.mAmount, F));
				if (tStack != null) {
					tChances[i  ] = 10000;
					tOutputs[i++] = tStack;
				}
			}
		}
		return null != aMap.addRecipe(new Recipe(F, F, T, ST.array(ST.amount(1, aInput)), tOutputs, NI, tChances, ZL_FS, ZL_FS, Math.max(1, tDuration), 16, 0));
	}
	
	@Override
	public boolean containsInput(RecipeMap aMap, ItemStack aStack, OreDictItemData aData) {
		// For some Reason it requires this to let Items in, even if it should actually be able to find the Recipe.
		return (aData != null && ST.block(aStack) instanceof IPrefixBlock && aData.mPrefix.containsAny(TD.Prefix.STANDARD_ORE, TD.Prefix.DENSE_ORE)) || super.containsInput(aMap, aStack, aData);
	}
	
	@Override
	public boolean addRecipesProducing(RecipeMap aMap, boolean aNEI, ItemStack aStack, OreDictItemData aData) {
		if (aData != null && aData.hasValidPrefixMaterialData() && (aData.mPrefix == OP.crushed || aData.mPrefix == OP.dust || aData.mPrefix == OP.gem)) {
			boolean temp = F;
			for (OreDictMaterial tMaterial : aData.mMaterial.mMaterial.mTargetedCrushing) if (tMaterial.mID > 0 && tMaterial.mTargetCrushing.mMaterial == aData.mMaterial.mMaterial && OP.oreRaw.isGeneratingItem(aData.mMaterial.mMaterial)) {
				if (BlocksGT.ore       != null) if (addRecipesUsing(aMap, aNEI, ST.make((Block)BlocksGT.ore      , 1, tMaterial.mID), OP.oreVanillastone.dat(tMaterial))) temp = T;
				if (BlocksGT.oreBroken != null) if (addRecipesUsing(aMap, aNEI, ST.make((Block)BlocksGT.oreBroken, 1, tMaterial.mID), OP.oreVanillastone.dat(tMaterial))) temp = T;
				if (addRecipesUsing(aMap, aNEI, OP.oreRaw  .mat(tMaterial, 1), OP.oreRaw  .dat(tMaterial))) temp = T;
				if (addRecipesUsing(aMap, aNEI, OP.blockRaw.mat(tMaterial, 1), OP.blockRaw.dat(tMaterial))) temp = T;
			}
			return temp;
		}
		return F;
	}
}
