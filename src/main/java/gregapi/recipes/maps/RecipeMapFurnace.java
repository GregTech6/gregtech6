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

import gregapi.block.metatype.BlockStones;
import gregapi.data.*;
import gregapi.oredict.OreDictItemData;
import gregapi.random.IHasWorldAndCoords;
import gregapi.recipes.Recipe;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidStack;

import java.util.Collection;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class RecipeMapFurnace extends RecipeMapNonGTRecipes {
	public RecipeMapFurnace(Collection<Recipe> aRecipeList, String aUnlocalizedName, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower, boolean aUseBucketSizeIn, boolean aUseBucketSizeOut) {
		super(aRecipeList, aUnlocalizedName, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, aUseBucketSizeIn, aUseBucketSizeOut);
	}
	
	@Override
	public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
		if (aInputs == null || aInputs.length <= 0 || aInputs[0] == null) return null;
		if (aRecipe != null && aRecipe.isRecipeInputEqual(F, T, aFluids, aInputs)) return aRecipe;
		ItemStack tOutput = RM.get_smelting(aInputs[0]);
		if (tOutput == null) return null;
		if (FL.XP.exists()) {
			OreDictItemData tData = OM.anydata_(aInputs[0]);
			// Don't allow any Dusts to give XP
			if (!OM.prefixcontainsany(tData, TD.Prefix.DUST_BASED, TD.Prefix.INGOT_BASED, TD.Prefix.GEM_BASED)) {
				FluidStack tFluid = null;
				if (tOutput.getItem() == Items.brick || tOutput.getItem() == Items.netherbrick || tOutput.getItem() == Items.clay_ball || tOutput.getItem() == Items.dye) {
					// Bricks and Dyes are 0.05 XP
					tFluid = FL.XP.make(tOutput.stackSize);
				} else if (tOutput.getItem() == Items.coal) {
					// Coal/Charcoal is giving 0.10 XP
					tFluid = FL.XP.make(tOutput.stackSize * 2);
				} else if (IL.EtFu_Chorus_Popped.equal(tOutput)) {
					// Chorus Fruit is 0.20 XP
					tFluid = FL.XP.make(tOutput.stackSize * 4);
				} else if (IL.ERE_Pot_Cooked.equal(tOutput)) {
					// The Titan Stew a whole Orb of XP
					tFluid = FL.XP.make(tOutput.stackSize * 20);
				} else if (OD.blockGlass.is(tOutput) || OD.paneGlass.is(tOutput)) {
					// Glass is 0.05 XP, yes I know it can be made from Stone, but this is enough effort to warrant at least some XP.
					tFluid = FL.XP.make(tOutput.stackSize);
				} else {
					Block tBlock = ST.block(tOutput);
					if (tBlock == Blocks.cobblestone || tBlock == Blocks.stone || tBlock == Blocks.stonebrick || tBlock instanceof BlockStones) {
						// Stone should not give XP, especially not because of the Cobble Generator Upgrades.
						// GT6 Stone is also not allowed due to easily recycleable Recipes.
					} else if (tBlock == Blocks.hardened_clay || tBlock == Blocks.stained_hardened_clay) {
						// Hardened Clay is 0.10 XP
						tFluid = FL.XP.make(tOutput.stackSize * 2);
					} else if (tBlock == Blocks.brick_block || tBlock == Blocks.nether_brick) {
						// Brick Blocks are 0.15 XP, yes only three instead of four Bricks worth of XP
						tFluid = FL.XP.make(tOutput.stackSize * 3);
					} else if (ST.food(tOutput) > 0) {
						// Food always gives 0.05 XP, not more, not less.
						tFluid = FL.XP.make(tOutput.stackSize);
					} else {
						// Now for OreDictItemData of the Input Item
						if (tData != null && tData.validData()) {
							if (tData.mPrefix.containsAny(TD.Prefix.ORE, TD.Prefix.ORE_PROCESSING_BASED)) {
								tData = OM.anydata_(tOutput);
								if (tData != null && tData.validData()) {
									// Give XP based on Tool Quality of the Output.
									long tXP = tOutput.stackSize * (3+tData.mMaterial.mMaterial.mToolQuality);
									// Valuable Tag is for Gold and certain Gems and happens to double the XP you can get from this.
									if (tData.mMaterial.mMaterial.contains(TD.Properties.VALUABLE)) tXP *= 2;
									// Magical Tag also doubles the XP you can get from this.
									if (tData.mMaterial.mMaterial.contains(TD.Properties.MAGICAL )) tXP *= 2;
									if (tData.mPrefix.mAmount > 0) {
										// Give at least 0.05 XP for this, or more if the Recipes is valuable enough.
										tFluid = FL.XP.make(UT.Code.divup(tData.mPrefix.mAmount * tXP, U));
									} else {
										// This is probably an Ore Block since those have a Value of -1, or something else that doesn't have a Unit Amount.
										tFluid = FL.XP.make(tXP);
									}
								} else {
									// I don't know what this is, guess I will default to 5.
									tFluid = FL.XP.make(tOutput.stackSize * 5);
								}
							} else {
								// No XP from this case! This is likely either a Recycling Recipe or a Dust to Ingot Recipe!
							}
						} else {
							// Those RotaryCraft Extracts are all about Ore Processing.
							if (MD.RoC.owns(aInputs[0], "extracts")) {
								tData = OM.anydata_(tOutput);
								if (tData != null && tData.validData()) {
									// Give XP based on Tool Quality of the Output.
									long tXP = tOutput.stackSize * (3+tData.mMaterial.mMaterial.mToolQuality);
									// Valuable Tag is for Gold and certain Gems and happens to double the XP you can get from this.
									if (tData.mMaterial.mMaterial.contains(TD.Properties.VALUABLE)) tXP *= 2;
									// Magical Tag also doubles the XP you can get from this.
									if (tData.mMaterial.mMaterial.contains(TD.Properties.MAGICAL )) tXP *= 2;
									if (tData.mPrefix.mAmount > 0) {
										// Give at least 0.05 XP for this, or more if the Recipes is valuable enough.
										tFluid = FL.XP.make(UT.Code.divup(tData.mPrefix.mAmount * tXP, U));
									} else {
										// This is probably something that doesn't have a Unit Amount.
										tFluid = FL.XP.make(tXP);
									}
								} else {
									// I don't know what this is, guess I will default to 5.
									tFluid = FL.XP.make(tOutput.stackSize * 5);
								}
							} else {
								// Guess we need to default to the normal Furnace way of determining XP
								tFluid = FL.XP.make(UT.Code.bind(1, tOutput.stackSize * 20, UT.Code.roundUp(tOutput.stackSize * 20 * FurnaceRecipes.smelting().func_151398_b(tOutput))));
							}
						}
					}
				}
				// return the Fluid Variant of the Recipe
				if (tFluid != null && tFluid.amount > 0) {
					return new Recipe(F, F, T, ST.array(ST.amount(1, aInputs[0])), ST.array(tOutput), null, null, ZL_FS, new FluidStack[] {tFluid}, 16, 16, 0);
				}
			}
		}
		// return the Normal Recipe
		return new Recipe(F, F, T, ST.array(ST.amount(1, aInputs[0])), ST.array(tOutput), null, null, ZL_FS, ZL_FS, 16, 16, 0);
	}
	
	@Override public boolean containsInput(ItemStack aStack, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {return ST.valid(RM.get_smelting(aStack));}
}
