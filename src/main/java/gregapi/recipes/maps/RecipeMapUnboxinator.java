/**
 * Copyright (c) 2025 GregTech-6 Team
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

import gregapi.data.IL;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.Behavior_Drop_Loot;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.FluidStack;

import java.util.Collection;
import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapUnboxinator extends RecipeMap {
	public RecipeMapUnboxinator(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower, boolean aUseBucketSizeIn, boolean aUseBucketSizeOut) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, F, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, aUseBucketSizeIn, aUseBucketSizeOut);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		if (aInputs == null || aInputs.length <= 0 || aInputs[0] == null) return super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		if (IL.Crate_Loot.equal(aInputs[0], F, T)) {
			// Due to the randomness it is not good if there are Items in the Output Slot, because those Items could manipulate the outcome.
			return new Recipe(F, F, F, ST.array(IL.Crate_Loot.get(1)), ST.array(ST.generateOneVanillaLoot(), IL.Crate.get(1)), null, null, null, null, 16, 16, 0).setNeedEmptyOut();
		}
		// For any GT6 Grabbag Items, so I don't have to add to this over and over. Still gotta manually add Fake Recipes for the Item Filter though.
		if (ST.item(aInputs[0]) instanceof MultiItem) {
			List<IBehavior<MultiItem>> tList = ((MultiItem)ST.item(aInputs[0])).mItemBehaviors.get(ST.meta(aInputs[0]));
			if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) if (tBehavior instanceof Behavior_Drop_Loot) {
				ItemStack[] tOutputs = ST.array(((Behavior_Drop_Loot)tBehavior).mLoots.length);
				for (int i = 0; i < tOutputs.length; i++) tOutputs[i] = ChestGenHooks.getOneItem(((Behavior_Drop_Loot)tBehavior).mLoots[i], RNGSUS);
				// If somehow no Loot, return the whole stack of Lootbags, don't waste any time or items.
				if (!ST.hasValid(tOutputs)) return new Recipe(F, F, F, aInputs, aInputs, null, null, null, null, 1, 1, 0);
				// Due to the randomness it is not good if there are Items in the Output Slot, because those Items could manipulate the outcome.
				return new Recipe(F, F, F, ST.array(ST.amount(1, aInputs[0])), tOutputs, null, null, null, null, 16, 16, 0).setNeedEmptyOut();
			}
		}
		if (COMPAT_IC2 != null && IL.IC2_Scrapbox.equal(aInputs[0], F, T)) {
			ItemStack tOutput = COMPAT_IC2.scrapbox(aInputs[0]);
			// If API Failure, return the whole stack of Scrapboxes, don't waste any time or items.
			if (tOutput == null) return new Recipe(F, F, F, aInputs, aInputs, null, null, null, null, 1, 1, 0);
			// Due to the randomness it is not good if there are Items in the Output Slot, because those Items could manipulate the outcome.
			return new Recipe(F, F, F, ST.array(IL.IC2_Scrapbox.get(1)), ST.array(tOutput), null, null, null, null, 16, 16, 0).setNeedEmptyOut();
		}
		if (COMPAT_TC != null && IL.TC_Loot_Common.equal(aInputs[0], T, T)) {
			ItemStack[] tOutputs = COMPAT_TC.lootbag(ST.meta(aInputs[0]));
			// If Compat Failure, return the whole stack of Lootbags, don't waste any time or items.
			if (!ST.hasValid(tOutputs)) return new Recipe(F, F, F, aInputs, aInputs, null, null, null, null, 1, 1, 0);
			// Due to the randomness it is not good if there are Items in the Output Slot, because those Items could manipulate the outcome.
			return new Recipe(F, F, F, ST.array(ST.amount(1, aInputs[0])), tOutputs, null, null, null, null, 16, 16, 0).setNeedEmptyOut();
		}
		if (IL.LOOTBAGS_Bag_0.equal(aInputs[0], T, T)) {
			ItemStack tBag = ST.amount(1, aInputs[0]);
			UT.Reflection.callPrivateMethod(tBag.getItem().getClass(), "generateInventory", tBag);
			// Due to the randomness it is not good if there are Items in the Output Slot, because those Items could manipulate the outcome.
			return new Recipe(F, F, F, ST.array(ST.amount(1, aInputs[0])), tBag.hasTagCompound() ? (ItemStack[])UT.Reflection.callPublicMethod(tBag.getItem().getClass(), "getInventory", tBag) : ZL_IS, null, null, null, null, 16, 16, 0).setNeedEmptyOut();
		}
		return super.findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
	}
}
