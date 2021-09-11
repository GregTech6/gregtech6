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

package gregapi.recipes;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.code.ICondition;
import gregapi.data.MT;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * @author Gregorius Techneticies
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class AdvancedCraftingXToY implements ICraftingRecipeGT {
	public final ICondition mCondition;
	public final OreDictPrefix mInput, mOutput;
	public final boolean mAutoCraftable;
	public final int mInputCount, mOutputCount;
	
	public AdvancedCraftingXToY(OreDictPrefix aInput, int aInputCount, OreDictPrefix aOutput, int aOutputCount, boolean aAutoCraftable) {this(aInput, aInputCount, aOutput, aOutputCount, aAutoCraftable, MT.NULL.NOT);}
	public AdvancedCraftingXToY(OreDictPrefix aInput, int aInputCount, OreDictPrefix aOutput, int aOutputCount, boolean aAutoCraftable, ICondition aCondition) {
		mAutoCraftable = aAutoCraftable;
		mCondition = aCondition;
		mInput = aInput;
		mInputCount = aInputCount;
		mOutput = aOutput;
		mOutputCount = aOutputCount;
		mInput.mShapelessManagers.add(this);
		
		List<IRecipe> tRecipeList = CR.list();
		try {for (int i = 0; i < tRecipeList.size(); i++) {
			IRecipe tRecipe = tRecipeList.get(i);
			if (tRecipe == null) {tRecipeList.remove(i--); continue;}
			int tCount = 0;
			OreDictMaterial tMaterial = null;
			
			if (tRecipeList.get(i) instanceof ICraftingRecipeGT) {
				// NOTHING
			} else if (tRecipe instanceof ShapedOreRecipe) {
				Object[] tInputs = ((ShapedOreRecipe)tRecipe).getInput();
				
				if (tInputs != null && tInputs.length >= mInputCount && (mInputCount == 9 || (mInputCount == 4 && tInputs.length == 9 && tInputs[2] == null && tInputs[5] == null && tInputs[6] == null && tInputs[7] == null && tInputs[8] == null))) for (Object tObject : tInputs) if (tObject != null) {
					if (++tCount > mInputCount) {
						tCount = 0;
						break;
					}
					if (tObject instanceof ItemStack) {
						OreDictItemData tData = OM.data((ItemStack)tObject);
						if (tData == null || tData.mPrefix != mInput || tData.mMaterial == null || !mCondition.isTrue(tData.mMaterial.mMaterial)) {
							tCount = 0;
							break;
						} else if (tCount == 1) {
							tMaterial = tData.mMaterial.mMaterial;
						} else if (tMaterial != tData.mMaterial.mMaterial) {
							tCount = 0;
							break;
						}
					} else if (tObject instanceof List) {
						if (((List)tObject).isEmpty()) {
							tCount = 0;
							break;
						}
						if (((List)tObject).get(0) instanceof ItemStack) {
							OreDictItemData tData = OM.data((ItemStack)((List)tObject).get(0));
							if (tData == null || tData.mPrefix != mInput || tData.mMaterial == null || !mCondition.isTrue(tData.mMaterial.mMaterial)) {
								tCount = 0;
								break;
							} else if (tCount == 1) {
								tMaterial = tData.mMaterial.mMaterial;
							} else if (tMaterial != tData.mMaterial.mMaterial) {
								tCount = 0;
								break;
							}
						}
					} else {
						tCount = 0;
						break;
					}
				}
			} else if (tRecipe instanceof ShapelessOreRecipe) {
				List tInputs = ((ShapelessOreRecipe)tRecipe).getInput();
				
				if (tInputs != null && tInputs.size() == mInputCount) for (Object tObject : tInputs) if (tObject != null) {
					tCount++;
					if (tObject instanceof ItemStack) {
						OreDictItemData tData = OM.data((ItemStack)tObject);
						if (tData == null || tData.mPrefix != mInput || tData.mMaterial == null || !mCondition.isTrue(tData.mMaterial.mMaterial)) {
							tCount = 0;
							break;
						} else if (tCount == 1) {
							tMaterial = tData.mMaterial.mMaterial;
						} else if (tMaterial != tData.mMaterial.mMaterial) {
							tCount = 0;
							break;
						}
					} else if (tObject instanceof List) {
						if (((List)tObject).isEmpty()) {
							tCount = 0;
							break;
						}
						if (((List)tObject).get(0) instanceof ItemStack) {
							OreDictItemData tData = OM.data((ItemStack)((List)tObject).get(0));
							if (tData == null || tData.mPrefix != mInput || tData.mMaterial == null || !mCondition.isTrue(tData.mMaterial.mMaterial)) {
								tCount = 0;
								break;
							} else if (tCount == 1) {
								tMaterial = tData.mMaterial.mMaterial;
							} else if (tMaterial != tData.mMaterial.mMaterial) {
								tCount = 0;
								break;
							}
						}
					} else {
						tCount = 0;
						break;
					}
				}
			} else if (tRecipe instanceof ShapedRecipes) {
				ItemStack[] tInputs = ((ShapedRecipes)tRecipe).recipeItems;
				
				if (tInputs != null && tInputs.length >= mInputCount && (mInputCount == 9 || (mInputCount == 4 && tInputs.length == 9 && tInputs[2] == null && tInputs[5] == null && tInputs[6] == null && tInputs[7] == null && tInputs[8] == null))) for (ItemStack tObject : tInputs) if (tObject != null) {
					if (++tCount > mInputCount) {
						tCount = 0;
						break;
					}
					OreDictItemData tData = OM.data(tObject);
					if (tData == null || tData.mPrefix != mInput || tData.mMaterial == null || !mCondition.isTrue(tData.mMaterial.mMaterial)) {
						tCount = 0;
						break;
					} else if (tCount == 1) {
						tMaterial = tData.mMaterial.mMaterial;
					} else if (tMaterial != tData.mMaterial.mMaterial) {
						tCount = 0;
						break;
					}
				}
			} else if (tRecipe instanceof ShapelessRecipes) {
				List tInputs = ((ShapelessRecipes)tRecipe).recipeItems;
				
				if (tInputs != null && tInputs.size() == mInputCount) for (Object tObject : tInputs) if (tObject != null) {
					tCount++;
					if (tObject instanceof ItemStack) {
						OreDictItemData tData = OM.data((ItemStack)tObject);
						if (tData == null || tData.mPrefix != mInput || tData.mMaterial == null || !mCondition.isTrue(tData.mMaterial.mMaterial)) {
							tCount = 0;
							break;
						} else if (tCount == 1) {
							tMaterial = tData.mMaterial.mMaterial;
						} else if (tMaterial != tData.mMaterial.mMaterial) {
							tCount = 0;
							break;
						}
					} else {
						tCount = 0;
						break;
					}
				}
			}
			
			if (tCount == mInputCount) {
				OreDictItemData tData = OM.data(tRecipe.getRecipeOutput());
				if (tData != null && tData.mPrefix == mOutput && tData.mMaterial.mMaterial == tMaterial) {
					tRecipeList.remove(i--);
				}
			}
			
		}} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	@Override
	public boolean matches(InventoryCrafting aGrid, World aWorld) {
		ItemStack tStack = null;
		OreDictMaterial rMaterial = null;
		
		int tInventorySize = aGrid.getSizeInventory(), tCounter = 0;
		if (tInventorySize < mInputCount) return F;
		for (int i = 0; i < tInventorySize; i++) {
			tStack = aGrid.getStackInSlot(i);
			if (ST.valid(tStack)) {
				OreDictItemData tData = OM.anydata_(tStack);
				if (tData == null || tData.mPrefix != mInput) return F;
				if (rMaterial == null) rMaterial = tData.mMaterial.mMaterial; else if (rMaterial != tData.mMaterial.mMaterial) return F;
				tCounter++;
				continue;
			}
			
			if (i-tCounter+mInputCount >= tInventorySize) return F;
		}
		return rMaterial != null && tCounter == mInputCount && hasOutputFor(rMaterial);
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting aGrid) {
		for (int i = 0, j = aGrid.getSizeInventory(); i < j; i++) {
			OreDictItemData tData = OM.anydata(aGrid.getStackInSlot(i));
			if (tData == null || tData.mMaterial == null || !mCondition.isTrue(tData.mMaterial.mMaterial)) continue;
			return mOutput.mat(tData.mMaterial.mMaterial, mOutputCount);
		}
		return ERROR_OUTPUT.copy();
	}
	
	public boolean hasOutputFor(OreDictMaterial aMaterial) {
		return ST.valid(mOutput.mat(aMaterial, mOutputCount)) && mCondition.isTrue(aMaterial);
	}
	
	@Override public boolean isRemovableByGT() {return F;}
	@Override public boolean isAutocraftableByGT() {return mAutoCraftable;}
	@Override public int getRecipeSize() {return mInputCount;}
	@Override public ItemStack getRecipeOutput() {return ERROR_OUTPUT.copy();}
}
