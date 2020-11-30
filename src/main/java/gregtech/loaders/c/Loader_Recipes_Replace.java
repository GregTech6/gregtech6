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

package gregtech.loaders.c;

import static gregapi.data.CS.*;

import java.util.List;
import java.util.Set;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackSet;
import gregapi.config.ConfigCategories;
import gregapi.data.ANY;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.CS.ToolsGT;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.recipes.ICraftingRecipeGT;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Loader_Recipes_Replace implements Runnable {
	public static final Set<String> sNonReplaceableNames = new HashSetNoNulls<>(F, "ic2.itemwoodrotor", "ic2.itemironrotor", "ic2.itemsteelrotor", "ic2.itemcarbonrotor", "ic2.itemwcarbonrotor", "ic2.itemSteamTurbine", "ic2.itemLathingTool", "item.ItemToolThermometer");
	public static final ItemStackSet<ItemStackContainer> NON_REPLACEABLE = new ItemStackSet<>();
	
	@SuppressWarnings("rawtypes")
	@Override public void run() {OUT.println("GT_Mod: Replacing Vanilla alike Recipes.");
		NON_REPLACEABLE.add(ST.make(ToolsGT.sMetaTool                           , 1, W));
		NON_REPLACEABLE.add(ST.make(Items.bow                                   , 1, W));
		NON_REPLACEABLE.add(ST.make(Items.fishing_rod                           , 1, W));
		NON_REPLACEABLE.add(IL.IC2_ForgeHammer.getWithMeta(                       1, W));
		NON_REPLACEABLE.add(IL.IC2_WireCutter.getWithMeta(                        1, W));
		NON_REPLACEABLE.add(ST.mkic("painter"                                   , 1, W));
		NON_REPLACEABLE.add(ST.mkic("blackPainter"                              , 1, W));
		NON_REPLACEABLE.add(ST.mkic("redPainter"                                , 1, W));
		NON_REPLACEABLE.add(ST.mkic("greenPainter"                              , 1, W));
		NON_REPLACEABLE.add(ST.mkic("brownPainter"                              , 1, W));
		NON_REPLACEABLE.add(ST.mkic("bluePainter"                               , 1, W));
		NON_REPLACEABLE.add(ST.mkic("purplePainter"                             , 1, W));
		NON_REPLACEABLE.add(ST.mkic("cyanPainter"                               , 1, W));
		NON_REPLACEABLE.add(ST.mkic("lightGreyPainter"                          , 1, W));
		NON_REPLACEABLE.add(ST.mkic("darkGreyPainter"                           , 1, W));
		NON_REPLACEABLE.add(ST.mkic("pinkPainter"                               , 1, W));
		NON_REPLACEABLE.add(ST.mkic("limePainter"                               , 1, W));
		NON_REPLACEABLE.add(ST.mkic("yellowPainter"                             , 1, W));
		NON_REPLACEABLE.add(ST.mkic("cloudPainter"                              , 1, W));
		NON_REPLACEABLE.add(ST.mkic("magentaPainter"                            , 1, W));
		NON_REPLACEABLE.add(ST.mkic("orangePainter"                             , 1, W));
		NON_REPLACEABLE.add(ST.mkic("whitePainter"                              , 1, W));
		NON_REPLACEABLE.add(ST.mkic("cfPack"                                    , 1, W));
		NON_REPLACEABLE.add(ST.mkic("jetpack"                                   , 1, W));
		NON_REPLACEABLE.add(ST.mkic("treetap"                                   , 1, W));
		NON_REPLACEABLE.add(ST.mkic("weedEx"                                    , 1, W));
		NON_REPLACEABLE.add(ST.mkic("staticBoots"                               , 1, W));
		NON_REPLACEABLE.add(ST.mkic("compositeArmor"                            , 1, W));
		NON_REPLACEABLE.add(ST.mkic("hazmatHelmet"                              , 1, W));
		NON_REPLACEABLE.add(ST.mkic("hazmatChestplate"                          , 1, W));
		NON_REPLACEABLE.add(ST.mkic("hazmatLeggings"                            , 1, W));
		NON_REPLACEABLE.add(ST.mkic("hazmatBoots"                               , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.RC, "part.turbine.disk"                  , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.RC, "part.turbine.blade"                 , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.RC, "part.turbine.rotor"                 , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.RC, "borehead.diamond"                   , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.RC, "borehead.steel"                     , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.RC, "borehead.iron"                      , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.TF, "item.plateNaga"                     , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.TF, "item.legsNaga"                      , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.TF, "item.arcticHelm"                    , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.TF, "item.arcticPlate"                   , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.TF, "item.arcticLegs"                    , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.TF, "item.arcticBoots"                   , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.TF, "item.yetiHelm"                      , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.TF, "item.yetiPlate"                     , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.TF, "item.yetiLegs"                      , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.TF, "item.yetiBoots"                     , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.AE, "item.ToolCertusQuartzCuttingKnife"  , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.AE, "item.ToolNetherQuartzCuttingKnife"  , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.FR, "apiaristHelmet"                     , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.FR, "apiaristChest"                      , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.FR, "apiaristLegs"                       , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.FR, "apiaristBoots"                      , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.FR, "frameUntreated"                     , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.FR, "frameImpregnated"                   , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.FR, "frameProven"                        , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.FR, "waxCast"                            , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.GC, "item.sensorGlasses"                 , 1, W));
		NON_REPLACEABLE.add(ST.make(MD.NC, "ItemToolThermometer"                , 1, W));
		
		
		List<ItemStack> tStickList = OreDictionary.getOres(OD.stickWood.toString());
		HashSetNoNulls<Object> tAlreadyScannedItems = new HashSetNoNulls<>();
		ArrayListNoNulls<RecipeReplacement> tList = new ArrayListNoNulls<>();
		List<IRecipe> tRecipeList = CR.list();
		boolean tUseProgressBar = UT.LoadingBar.start("Looking up Recipes", tRecipeList.size());
		for (int l = 0; l < tRecipeList.size(); l++) {
			IRecipe tRecipe = tRecipeList.get(l);
			if (tUseProgressBar) UT.LoadingBar.step("");
			ItemStack aOutput = tRecipe.getRecipeOutput();
			if (ST.invalid(aOutput)) continue;
			if (aOutput.stackSize != 1) continue;
			if (aOutput.getMaxDamage() <= 0) continue;
			if (aOutput.getMaxStackSize() != 1) continue;
			if (tRecipe instanceof ShapelessRecipes) continue;
			if (tRecipe instanceof ShapelessOreRecipe) continue;
			if (tRecipe instanceof ICraftingRecipeGT) continue;
			if (ST.block(aOutput) != NB) continue;
			if (COMPAT_IC2 != null && COMPAT_IC2.isReactorItem(aOutput)) continue;
			if (COMPAT_EU_ITEM != null && COMPAT_EU_ITEM.is(aOutput)) continue;
			if (NON_REPLACEABLE.contains(aOutput, T)) continue;
			if (sNonReplaceableNames.contains(aOutput.getUnlocalizedName())) continue;
			
			Object[] tRecipeInputs = null;
			
			if (tRecipe instanceof ShapedOreRecipe) {
				tRecipeInputs = ((ShapedOreRecipe)tRecipe).getInput();
			} else if (tRecipe instanceof ShapedRecipes) {
				tRecipeInputs = ((ShapedRecipes)tRecipe).recipeItems;
			} else if (MD.IC2.mLoaded && tRecipe instanceof ic2.core.AdvRecipe) {
				tRecipeInputs = ((ic2.core.AdvRecipe)tRecipe).input;
			}
			
			if (tRecipeInputs == null || tRecipeInputs.length <= 0) continue;
			
			OreDictPrefix tPrefix = null;
			OreDictMaterial tMat = null, tRod = null;
			
			boolean temp = T;
			tAlreadyScannedItems.clear();
			for (int i = 0; i < tRecipeInputs.length; i++) {
				Object tObject = tRecipeInputs[i];
				if (!tAlreadyScannedItems.add(tObject)) continue;
				OreDictItemData tData = null;
				if (tObject instanceof ItemStack) {
					if (IL.Stick.equal(tObject)) {tRod = ANY.Wood; continue;}
					tData = OM.anyassociation((ItemStack)tObject);
				} else if (MD.IC2.mLoaded && tObject instanceof ic2.api.recipe.RecipeInputItemStack) {
					if (IL.Stick.equal(((ic2.api.recipe.RecipeInputItemStack)tObject).input)) {tRod = ANY.Wood; continue;}
					tData = OM.anyassociation(((ic2.api.recipe.RecipeInputItemStack)tObject).input);
				} else if (MD.IC2.mLoaded && tObject instanceof ic2.api.recipe.RecipeInputOreDict) {
					if (OD.stickWood   .toString().equals(((ic2.api.recipe.RecipeInputOreDict)tObject).input)) {tRod = ANY.Wood; continue;}
					if (OD.stickAnyWood.toString().equals(((ic2.api.recipe.RecipeInputOreDict)tObject).input)) {tRod = ANY.Wood; continue;}
					tData = OM.data(((ic2.api.recipe.RecipeInputOreDict)tObject).input);
				} else if (tObject instanceof List) {
					if (tStickList == tObject) {tRod = ANY.Wood; continue;}
					switch(((List)tObject).size()) {
					case  0:
						temp = F;
						break;
					case  1:
						if (((List)tObject).get(0) instanceof ItemStack) {
							tData = OM.anyassociation((ItemStack)(((List)tObject).get(0)));
						} else {
							temp = F;
						}
						break;
					default:
						for (Object tContent : ((List)tObject)) if (tContent instanceof ItemStack) {
							if (tData == null) {
								tData = OM.anyassociation((ItemStack)tContent);
								if (tData == null || !tData.hasValidPrefixMaterialData()) {temp = F; break;}
							} else {
								OreDictItemData tAssociation = OM.anyassociation((ItemStack)tContent);
								if (tAssociation == null || tAssociation.mPrefix != tData.mPrefix || tAssociation.mMaterial.mMaterial != tData.mMaterial.mMaterial) {temp = F; break;}
							}
						} else {
							temp = F; break;
						}
						break;
					}
					if (!temp) break;
				} else {
					temp = F; break;
				}
				
				if (tData == null) {temp = F; break;}
				if (tData.mPrefix == OP.stick) {
					if (tRod != null && tRod != tData.mMaterial.mMaterial) {temp = F; break;}
					tRod = tData.mMaterial.mMaterial;
				} else {
					if (tMat != null && tMat != tData.mMaterial.mMaterial) {temp = F; break;}
					if (tData.mPrefix != OP.ingot && tData.mPrefix != OP.gem) {temp = F; break;}
					tMat = tData.mMaterial.mMaterial;
					tPrefix = tData.mPrefix;
				}
			}
			if (temp) tList.add(new RecipeReplacement(tRecipe, tPrefix, tMat, tRod));
		}
		
		if (tUseProgressBar) UT.LoadingBar.finish();
		
		tUseProgressBar = UT.LoadingBar.start("Replacing Recipes", tList.size());
		
		for (RecipeReplacement aRecipe : tList) {
			if (tUseProgressBar) UT.LoadingBar.step(aRecipe.mMat);
			if (aRecipe.mPrefix == null || aRecipe.mMat == null) continue;
			ItemStack tMat = aRecipe.mPrefix.mat(aRecipe.mMat, 1);
			if (tMat == null) continue;
			INGT.func_150996_a(tMat.getItem());
			INGT.stackSize = 1;
			ST.meta_(INGT, ST.meta_(tMat));
			RecipeReplacer[] tReplacer = sRecipesMat;
			if (aRecipe.mRod != null) {
				ItemStack tRod = (aRecipe.mRod == ANY.Wood ? IL.Stick.get(1) : OP.stick.mat(aRecipe.mRod, 1));
				if (tRod == null) continue;
				STCK.func_150996_a(tRod.getItem());
				STCK.stackSize = 1;
				ST.meta_(STCK, ST.meta_(tRod));
				tReplacer = sRecipesRod;
			}
			
			for (RecipeReplacer aReplacer : tReplacer) {
				ItemStack tCrafted = getRecipeOutput(aRecipe.mRecipe, aReplacer.mRecipe);
				if (tCrafted == null) continue;
				int tMatCount = 0, tRodCount = 0;
				for (ItemStack tStack : aReplacer.mRecipe) if (tStack == INGT) tMatCount++; else if (tStack == STCK) tRodCount++;
				OM.dat2(tCrafted, aRecipe.mMat, aRecipe.mPrefix.mAmount * tMatCount, OM.stack(aRecipe.mRod, OP.stick.mAmount * tRodCount));
				if (aRecipe.mPrefix != OP.ingot) continue;
				ItemStack tPlate = OP.plate.mat(aRecipe.mMat, 1);
				if (tPlate == null || aReplacer.mShape == null || aReplacer.mShape.length <= 0) continue;
				if (!ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, aRecipe.mMat+"."+aReplacer.mName, T)) continue;
				if (!tRecipeList.remove(aRecipe.mRecipe)) continue;
				
				switch (aReplacer.mShape.length) {
				case  1: CR.shaped(tCrafted, CR.DEF, new Object[] {aReplacer.mShape[0]                                          , PLT.charAt(0), OP.plate.dat(aRecipe.mMat), CRV.charAt(0), OP.plateCurved.dat(aRecipe.mMat), ROD.charAt(0), OP.stick.dat(aRecipe.mRod == null ? aRecipe.mMat : aRecipe.mRod), NGT.charAt(0), OP.ingot.dat(aRecipe.mMat)}); break;
				case  2: CR.shaped(tCrafted, CR.DEF, new Object[] {aReplacer.mShape[0], aReplacer.mShape[1]                     , PLT.charAt(0), OP.plate.dat(aRecipe.mMat), CRV.charAt(0), OP.plateCurved.dat(aRecipe.mMat), ROD.charAt(0), OP.stick.dat(aRecipe.mRod == null ? aRecipe.mMat : aRecipe.mRod), NGT.charAt(0), OP.ingot.dat(aRecipe.mMat)}); break;
				default: CR.shaped(tCrafted, CR.DEF, new Object[] {aReplacer.mShape[0], aReplacer.mShape[1], aReplacer.mShape[2], PLT.charAt(0), OP.plate.dat(aRecipe.mMat), CRV.charAt(0), OP.plateCurved.dat(aRecipe.mMat), ROD.charAt(0), OP.stick.dat(aRecipe.mRod == null ? aRecipe.mMat : aRecipe.mRod), NGT.charAt(0), OP.ingot.dat(aRecipe.mMat)}); break;
				}
			}
		}
		
		if (tUseProgressBar) UT.LoadingBar.finish();
	}
	
	public static ItemStack getRecipeOutput(IRecipe aRecipe, ItemStack... aStacks) {
		if (aRecipe == null || aStacks == null) return null;
		for (byte i = 0; i < aStacks.length; i++) if (aStacks[i] != null) {
			InventoryCrafting aCrafting = new InventoryCrafting(new Container() {@Override public boolean canInteractWith(EntityPlayer aPlayer) {return F;}}, 3, 3);
			for (int j = 0; j < 9 && j < aStacks.length; j++) aCrafting.setInventorySlotContents(j, aStacks[j]);
			if (!aRecipe.matches(aCrafting, DW)) return null;
			ItemStack rOutput = aRecipe.getCraftingResult(aCrafting);
			if (rOutput == null || rOutput.stackSize <= 0) return null;
			return rOutput;
		}
		return null;
	}
	
	public static class RecipeReplacement {
		public final IRecipe mRecipe;
		public final OreDictPrefix mPrefix;
		public OreDictMaterial mMat, mRod;
		public RecipeReplacement(IRecipe aRecipe, OreDictPrefix aPrefix, OreDictMaterial aMat, OreDictMaterial aRod) {mRecipe = aRecipe; mPrefix = aPrefix; mMat = aMat; mRod = aRod;}
	}
	
	public static class RecipeReplacer {
		public final ItemStack[] mRecipe;
		public final String mName;
		public final String[] mShape;
		
		public RecipeReplacer(ItemStack[] aRecipe, String aName, String... aShape) {mRecipe = aRecipe; mName = aName; mShape = aShape;}
		public RecipeReplacer(ItemStack[] aRecipe) {mRecipe = aRecipe; mName = "null"; mShape = ZL_STRING;}
	}
	
	private static final ItemStack INGT = ST.make(Blocks.dirt, 1, 0), STCK = ST.make(Blocks.dirt, 1, 0);
	private static final String HAM = "h", FIL = "f", NGT = "I", PLT = "P", CRV = "C", ROD = "R", ___ = " ";
	
	public static final RecipeReplacer[] sRecipesMat = {
		  new RecipeReplacer(ST.array
				(INGT, null, INGT
				,INGT, INGT, INGT
				,null, INGT, null))
		, new RecipeReplacer(ST.array
				(INGT, null, INGT
				,INGT, null, INGT
				,INGT, INGT, INGT))
		, new RecipeReplacer(ST.array
				(null, INGT, null
				,INGT, INGT, INGT
				,INGT, null, INGT))
		, new RecipeReplacer(ST.array
				(INGT, INGT, INGT
				,INGT, null, INGT
				,null, null, null)
				, "Helmet"
				, PLT+PLT+PLT
				, CRV+HAM+CRV)
		, new RecipeReplacer(ST.array
				(INGT, null, INGT
				,INGT, INGT, INGT
				,INGT, INGT, INGT)
				, "ChestPlate"
				, PLT+HAM+PLT
				, CRV+PLT+CRV
				, CRV+PLT+CRV)
		, new RecipeReplacer(ST.array
				(INGT, INGT, INGT
				,INGT, null, INGT
				,INGT, null, INGT)
				, "Pants"
				, PLT+CRV+PLT
				, CRV+HAM+CRV
				, CRV+___+CRV)
		, new RecipeReplacer(ST.array
				(null, null, null
				,INGT, null, INGT
				,INGT, null, INGT)
				, "Boots"
				, PLT+___+PLT
				, CRV+HAM+CRV)
		, new RecipeReplacer(ST.array
				(INGT, null, null
				,null, INGT, null
				,null, null, null)
				, "Shears"
				, HAM+PLT
				, PLT+FIL)
		, new RecipeReplacer(ST.array
				(null, INGT, null
				,INGT, null, null
				,null, null, null)
				, "Shears"
				, HAM+PLT
				, PLT+FIL)
	};
	
	public static final RecipeReplacer[] sRecipesRod = {
		  new RecipeReplacer(ST.array
				(null, INGT, null
				,null, INGT, null
				,null, STCK, null)
				, "Sword"
				, ___+PLT+___
				, FIL+PLT+HAM
				, ___+ROD+___)
		, new RecipeReplacer(ST.array
				(INGT, null, null
				,INGT, null, null
				,STCK, null, null)
				, "Sword"
				, ___+PLT+___
				, FIL+PLT+HAM
				, ___+ROD+___)
		, new RecipeReplacer(ST.array
				(INGT, INGT, INGT
				,null, STCK, null
				,null, STCK, null)
				, "Pickaxe"
				, PLT+NGT+NGT
				, FIL+ROD+HAM
				, ___+ROD+___)
		, new RecipeReplacer(ST.array
				(null, INGT, null
				,null, STCK, null
				,null, STCK, null)
				, "Shovel"
				, FIL+PLT+HAM
				, ___+ROD+___
				, ___+ROD+___)
		, new RecipeReplacer(ST.array
				(INGT, INGT, null
				,INGT, STCK, null
				,null, STCK, null)
				, "Axe"
				, PLT+NGT+HAM
				, PLT+ROD+___
				, FIL+ROD+___)
		, new RecipeReplacer(ST.array
				(null, INGT, INGT
				,null, STCK, INGT
				,null, STCK, null)
				, "Axe"
				, PLT+NGT+HAM
				, PLT+ROD+___
				, FIL+ROD+___)
		, new RecipeReplacer(ST.array
				(INGT, INGT, null
				,null, STCK, null
				,null, STCK, null)
				, "Hoe"
				, PLT+NGT+HAM
				, FIL+ROD+___
				, ___+ROD+___)
		, new RecipeReplacer(ST.array
				(INGT, INGT, null
				,STCK, null, null
				,STCK, null, null)
				, "Hoe"
				, PLT+NGT+HAM
				, FIL+ROD+___
				, ___+ROD+___)
		, new RecipeReplacer(ST.array
				(null, INGT, INGT
				,null, STCK, null
				,null, STCK, null)
				, "Hoe"
				, PLT+NGT+HAM
				, FIL+ROD+___
				, ___+ROD+___)
		, new RecipeReplacer(ST.array
				(INGT, INGT, null
				,INGT, null, null
				,INGT, INGT, STCK)
				, "Sickle"
				, PLT+PLT+___
				, PLT+FIL+HAM
				, PLT+PLT+ROD)
		, new RecipeReplacer(ST.array
				(null, INGT, INGT
				,null, null, INGT
				,STCK, INGT, INGT)
				, "Sickle"
				, PLT+PLT+___
				, PLT+FIL+HAM
				, PLT+PLT+ROD)
		, new RecipeReplacer(ST.array
				(null, INGT, null
				,INGT, null, null
				,null, INGT, STCK)
				, "Sickle"
				, ___+PLT+___
				, PLT+FIL+___
				, HAM+PLT+ROD)
		, new RecipeReplacer(ST.array
				(null, INGT, null
				,null, null, INGT
				,STCK, INGT, null)
				, "Sickle"
				, ___+PLT+___
				, PLT+FIL+___
				, HAM+PLT+ROD)
		, new RecipeReplacer(ST.array
				(null, INGT, null
				,INGT, null, INGT
				,null, null, STCK)
				, "Sickle"
				, ___+PLT+___
				, PLT+FIL+___
				, HAM+PLT+ROD)
		, new RecipeReplacer(ST.array
				(null, INGT, null
				,INGT, null, INGT
				,STCK, null, null)
				, "Sickle"
				, ___+PLT+___
				, PLT+FIL+___
				, HAM+PLT+ROD)
		, new RecipeReplacer(ST.array
				(null, STCK, null
				,null, INGT, null
				,null, INGT, null)
				, "Sword"
				, ___+ROD+___
				, FIL+PLT+HAM
				, ___+PLT+___)
		, new RecipeReplacer(ST.array
				(null, STCK, null
				,null, STCK, null
				,INGT, INGT, INGT)
				, "Pickaxe"
				, ___+ROD+___
				, FIL+ROD+HAM
				, PLT+NGT+NGT)
		, new RecipeReplacer(ST.array
				(null, STCK, null
				,null, STCK, null
				,null, INGT, null)
				, "Shovel"
				, ___+ROD+___
				, ___+ROD+___
				, FIL+PLT+HAM)
		, new RecipeReplacer(ST.array
				(null, STCK, null
				,INGT, STCK, null
				,INGT, INGT, null)
				, "Axe"
				, FIL+ROD+___
				, PLT+ROD+___
				, PLT+NGT+HAM)
		, new RecipeReplacer(ST.array
				(null, STCK, null
				,null, STCK, INGT
				,null, INGT, INGT)
				, "Axe"
				, FIL+ROD+___
				, PLT+ROD+___
				, PLT+NGT+HAM)
		, new RecipeReplacer(ST.array
				(null, STCK, null
				,null, STCK, null
				,INGT, INGT, null)
				, "Hoe"
				, ___+ROD+___
				, FIL+ROD+___
				, PLT+NGT+HAM)
		, new RecipeReplacer(ST.array
				(null, STCK, null
				,null, STCK, null
				,null, INGT, INGT)
				, "Hoe"
				, ___+ROD+___
				, FIL+ROD+___
				, PLT+NGT+HAM)
		, new RecipeReplacer(ST.array
				(null, INGT, INGT
				,null, STCK, INGT
				,STCK, null, null)
				, "Halberd"
				, PLT+PLT+HAM
				, PLT+ROD+___
				, FIL+___+ROD)
		, new RecipeReplacer(ST.array
				(INGT, INGT, null
				,INGT, STCK, null
				,null, null, STCK)
				, "Halberd"
				, PLT+PLT+HAM
				, PLT+ROD+___
				, FIL+___+ROD)
		, new RecipeReplacer(ST.array
				(null, null, INGT
				,null, STCK, null
				,STCK, null, null)
				, "Spear"
				, PLT+HAM+___
				, FIL+ROD+___
				, ___+___+ROD)
		, new RecipeReplacer(ST.array
				(INGT, null, null
				,null, STCK, null
				,null, null, STCK)
				, "Spear"
				, PLT+HAM+___
				, FIL+ROD+___
				, ___+___+ROD)
		, new RecipeReplacer(ST.array
				(null, null, INGT
				,null, INGT, null
				,STCK, null, null)
				, "LongSword"
				, PLT+HAM+___
				, FIL+PLT+___
				, ___+___+ROD)
		, new RecipeReplacer(ST.array
				(INGT, null, null
				,null, INGT, null
				,null, null, STCK)
				, "LongSword"
				, PLT+HAM+___
				, FIL+PLT+___
				, ___+___+ROD)
		, new RecipeReplacer(ST.array
				(null, null, INGT
				,null, STCK, null
				,null, null, null)
				, "Knife"
				, HAM+PLT
				, ROD+FIL)
		, new RecipeReplacer(ST.array
				(INGT, STCK, null
				,null, null, null
				,null, null, null)
				, "Knife"
				, FIL+HAM
				, PLT+ROD)
		, new RecipeReplacer(ST.array
				(STCK, INGT, null
				,null, null, null
				,null, null, null)
				, "Knife"
				, FIL+HAM
				, PLT+ROD)
		, new RecipeReplacer(ST.array
				(INGT, null, null
				,STCK, null, null
				,null, null, null)
				, "Knife"
				, PLT+FIL
				, ROD+HAM)
		, new RecipeReplacer(ST.array
				(STCK, null, null
				,INGT, null, null
				,null, null, null)
				, "Knife"
				, PLT+FIL
				, ROD+HAM)
		, new RecipeReplacer(ST.array
				(INGT, INGT, INGT
				,INGT, INGT, INGT
				,null, STCK, null))
		, new RecipeReplacer(ST.array
				(INGT, INGT, null
				,INGT, INGT, STCK
				,INGT, INGT, null))
		, new RecipeReplacer(ST.array
				(null, INGT, INGT
				,STCK, INGT, INGT
				,null, INGT, INGT))
		, new RecipeReplacer(ST.array
				(null, STCK, null
				,INGT, INGT, INGT
				,INGT, INGT, INGT))
		, new RecipeReplacer(ST.array
				(INGT, INGT, INGT
				,INGT, STCK, INGT
				,INGT, STCK, INGT)
				, "Paxel"
				, PLT+NGT+PLT
				, NGT+ROD+NGT
				, PLT+ROD+PLT)
		, new RecipeReplacer(ST.array
				(INGT, INGT, INGT
				,INGT, STCK, INGT
				,null, STCK, null)
				, "WarAxe"
				, PLT+PLT+PLT
				, PLT+ROD+PLT
				, FIL+ROD+HAM)
		, new RecipeReplacer(ST.array
				(INGT, null, INGT
				,INGT, STCK, INGT
				,null, STCK, null)
				, "WarAxe"
				, PLT+___+PLT
				, PLT+ROD+PLT
				, FIL+ROD+HAM)
		, new RecipeReplacer(ST.array
				(INGT, INGT, null
				,INGT, STCK, STCK
				,INGT, INGT, null))
		, new RecipeReplacer(ST.array
				(null, INGT, INGT
				,STCK, STCK, INGT
				,null, INGT, INGT)
				, null)
		, new RecipeReplacer(ST.array
				(null, STCK, null
				,INGT, STCK, INGT
				,INGT, INGT, INGT))
		, new RecipeReplacer(ST.array
				(INGT, STCK, INGT
				,INGT, STCK, INGT
				,null, STCK, null)
				, "WarHammer"
				, PLT+ROD+PLT
				, PLT+ROD+PLT
				, FIL+ROD+HAM)
		, new RecipeReplacer(ST.array
				(INGT, INGT, null
				,STCK, STCK, STCK
				,INGT, INGT, null))
		, new RecipeReplacer(ST.array
				(null, INGT, INGT
				,STCK, STCK, STCK
				,null, INGT, INGT))
		, new RecipeReplacer(ST.array
				(null, STCK, null
				,INGT, STCK, INGT
				,INGT, STCK, INGT))
		, new RecipeReplacer(ST.array
				(INGT, INGT, null
				,STCK, null, INGT
				,STCK, null, null)
				, "Scythe"
				, NGT+PLT+HAM
				, ROD+FIL+PLT
				, ROD+___+___)
		, new RecipeReplacer(ST.array
				(null, INGT, INGT
				,INGT, null, STCK
				,null, null, STCK)
				, "Scythe"
				, HAM+PLT+NGT
				, PLT+FIL+ROD
				, ___+___+ROD)
		, new RecipeReplacer(ST.array
				(INGT, INGT, STCK
				,null, STCK, null
				,STCK, null, null)
				, "Scythe"
				, PLT+PLT+ROD
				, FIL+ROD+HAM
				, ROD+___+___)
	};
}
