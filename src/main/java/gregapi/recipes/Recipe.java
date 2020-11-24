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

package gregapi.recipes;

import static gregapi.data.CS.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackMap;
import gregapi.config.Config;
import gregapi.data.CS.DirectoriesGT;
import gregapi.data.FL;
import gregapi.data.FM;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.RM;
import gregapi.gui.Slot_Base;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.random.IHasWorldAndCoords;
import gregapi.tileentity.ITileEntityInventoryGUI;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class Recipe {
	public static class RecipeMap implements Runnable {
		/** RecipeMap-HashMap so that Machines can store their corresponding Recipe Lists as String NBT. */
		public static final Map<String, RecipeMap> RECIPE_MAPS = new HashMap<>();
		
		/** List of Recipe Map Handlers. They will dynamically add regular Recipes when needed. */
		public final List<IRecipeMapHandler> mRecipeMapHandlers = new ArrayListNoNulls<>();
		/** List of Machines that can perform/use the Recipes of this Map. */
		public final List<ItemStack> mRecipeMachineList = new ArrayListNoNulls<>();
		/** HashMap of Recipes based on their Items */
		public final ItemStackMap<ItemStackContainer, Collection<Recipe>> mRecipeItemMap = new ItemStackMap<>();
		/** HashMap of Recipes based on their Fluids */
		public final Map<String, Collection<Recipe>> mRecipeFluidMap = new HashMap<>();
		/** HashMap of Minimum Tank Sizes based on the Input Fluids */
		public final Map<String, Long> mMinInputTankSizes = new HashMap<>();
		/** The List of all Recipes */
		public final Collection<Recipe> mRecipeList;
		/** Used to detect if MineTweaker fucked with the Recipe List without also fixing the HashMaps. */
		public int mRecipeListSize = 0;
		/** String used as an unlocalised Name. */
		public final String mNameInternal;
		/** String used as a localised Name in things that shouldn't be localised, like Config File Names. */
		public final String mNameLocal, mNameLocalUnderscored;
		/** String used in NEI for the Recipe Lists. If null it will use the unlocalised Name instead*/
		public final String mNameNEI;
		/** GUI used for NEI Display. Usually the GUI of the Machine itself */
		public final String mGUIPath;
		public final String mNEISpecialValuePre, mNEISpecialValuePost;
		public final byte mProgressBarDirection, mProgressBarAmount;
		public final int mInputItemsCount, mOutputItemsCount, mInputFluidCount, mOutputFluidCount, mMinimalInputItems, mMinimalInputFluids, mMinimalInputs;
		public final long mNEISpecialValueMultiplier, mPower;
		public final boolean mNEIAllowed, mShowVoltageAmperageInNEI, mNeedsOutputs, mCombinePower, mUseBucketSizeIn, mUseBucketSizeOut;
		public boolean mLogErrors = T;
		/** Used to determine Input Tank Size. Contains the size of the largest FluidStack Input, but is almost always at least 1000. */
		public int mMaxFluidInputSize  = 1000;
		/** Used to determine Output Tank Size. Contains the size of the largest FluidStack Output, but is almost always at least 1000. */
		public int mMaxFluidOutputSize = 1000;
		/** The Config File corresponding to this Recipe Handler. Will be initialised by GT_API. */
		public Config mConfigFile = null;
		
		/**
		 * Initialises a new type of Recipe Handler.
		 * @param aRecipeList a List you specify as Recipe List. Usually just an ArrayList with a pre-initialised Size.
		 * @param aNameInternal the unlocalised Name of this Recipe Handler, used mainly for NEI.
		 * @param aNameLocal the displayed Name inside the NEI Recipe GUI and regular ToolTips.
		 * @param aNEIGUIPath the displayed GUI Texture, usually just a Machine GUI. Auto-Attaches ".png" if forgotten.
		 * @param aInputItemsCount the usual amount of Input Slots this Recipe Class has.
		 * @param aOutputItemsCount the usual amount of Output Slots this Recipe Class has.
		 * @param aNEISpecialValuePre the String in front of the Special Value in NEI.
		 * @param aNEISpecialValueMultiplier the Value the Special Value is getting Multiplied with before displaying
		 * @param aNEISpecialValuePost the String after the Special Value. Usually for a Unit or something.
		 * @param aNEIAllowed if NEI is allowed to display this Recipe Handler in general.
		 */
		public RecipeMap(Collection<Recipe> aRecipeList, String aNameInternal, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower, boolean aUseBucketSizeIn, boolean aUseBucketSizeOut) {
			mNEIAllowed = aNEIAllowed;
			mShowVoltageAmperageInNEI = aShowVoltageAmperageInNEI;
			mNeedsOutputs = aNeedsOutputs;
			mCombinePower = aCombinePower;
			mUseBucketSizeIn = aUseBucketSizeIn;
			mUseBucketSizeOut = aUseBucketSizeOut;
			mRecipeList = (aRecipeList == null ? new HashSetNoNulls<Recipe>() : aRecipeList);
			mNameInternal = aNameInternal;
			mNameLocal = aNameLocal;
			// TODO proper String sanitizer
			StringBuilder tBuilder = new StringBuilder(mNameLocal.length());
			for (char tChar : mNameLocal.toCharArray()) {
				if (tChar == '(' || tChar == ')' || tChar == '[' || tChar == ']' || tChar == '{' || tChar == '}' || tChar == '"' || tChar == '\'' || tChar == '<' || tChar == '>' || tChar == '°' || tChar == '~' || tChar == '$' || tChar == '%' || tChar == '#' || tChar == '+' || tChar == '*' || tChar == '§' || tChar == '!' || tChar == '?' || tChar == '.' || tChar == ',' || tChar == ':' || tChar == ';') continue;
				if (tChar == ' ' || tChar == '-' || tChar == '=' || tChar == '&' || tChar == '^' || tChar == '|' || tChar == '/' || tChar == '\\') tBuilder.append('_'); else tBuilder.append(tChar);
			}
			mNameLocalUnderscored = tBuilder.toString();
			mNameNEI = aNameNEI == null ? mNameInternal : aNameNEI;
			mGUIPath = aNEIGUIPath.endsWith(".png")?aNEIGUIPath:aNEIGUIPath + ".png";
			mNEISpecialValuePre = aNEISpecialValuePre;
			mNEISpecialValueMultiplier = aNEISpecialValueMultiplier;
			mNEISpecialValuePost = aNEISpecialValuePost;
			mPower = aPower;
			mMinimalInputItems = (int)aMinimalInputItems;
			mInputItemsCount = (int)Math.max(aInputItemsCount, mMinimalInputItems);
			mOutputItemsCount = (int)aOutputItemsCount;
			mMinimalInputFluids = (int)aMinimalInputFluids;
			mInputFluidCount = (int)Math.max(aInputFluidCount, mMinimalInputFluids);
			mOutputFluidCount = (int)aOutputFluidCount;
			mMinimalInputs = (int)aMinimalInputs;
			mProgressBarDirection = (byte)aProgressBarDirection;
			mProgressBarAmount = (byte)aProgressBarAmount;
			LH.add(mNameInternal, mNameLocal);
			if (RECIPE_MAPS.containsKey(mNameInternal)) throw new IllegalArgumentException("Recipe Map Name already exists: " + mNameInternal);
			RECIPE_MAPS.put(mNameInternal, this);
			if (aConfigAllowed) if (GAPI.mBeforePreInit != null) GAPI.mBeforePreInit.add(this); else run();
		}
		
		public RecipeMap() {
			this(null, "", "", "", 0, 0, "", 0, 0, 0, 0, 0, 0, 0, 0, "", 0, "", F, F, F, F, F, T, T);
			mLogErrors = F;
		}
		
		@Override public String toString() {return mNameInternal;}
		@Override public void run() {mConfigFile = new Config(DirectoriesGT.CONFIG_RECIPES, mNameLocalUnderscored+".cfg");}
		
		@Deprecated public RecipeMap(Collection<Recipe> aRecipeList, String aNameInternal, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs, boolean aCombinePower) {this(aRecipeList, aNameInternal, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, aCombinePower, T, T);}
		@Deprecated public RecipeMap(Collection<Recipe> aRecipeList, String aNameInternal, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aMinimalInputs, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs) {this(aRecipeList, aNameInternal, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, aMinimalInputs, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs, F);}
		@Deprecated public RecipeMap(Collection<Recipe> aRecipeList, String aNameInternal, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed, boolean aNeedsOutputs) {this(aRecipeList, aNameInternal, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, 0, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, aNeedsOutputs);}
		@Deprecated public RecipeMap(Collection<Recipe> aRecipeList, String aNameInternal, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed, boolean aConfigAllowed) {this(aRecipeList, aNameInternal, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, 0, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, aConfigAllowed, T);}
		@Deprecated public RecipeMap(Collection<Recipe> aRecipeList, String aNameInternal, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aInputFluidCount, long aOutputFluidCount, long aMinimalInputFluids, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed) {this(aRecipeList, aNameInternal, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, aInputFluidCount, aOutputFluidCount, aMinimalInputFluids, 0, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, T, T);}
		@Deprecated public RecipeMap(Collection<Recipe> aRecipeList, String aNameInternal, String aNameLocal, String aNameNEI, long aProgressBarDirection, long aProgressBarAmount, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aMinimalInputFluids, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed) {this(aRecipeList, aNameInternal, aNameLocal, aNameNEI, aProgressBarDirection, aProgressBarAmount, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, 0, 0, aMinimalInputFluids, 0, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, T, T);}
		@Deprecated public RecipeMap(Collection<Recipe> aRecipeList, String aNameInternal, String aNameLocal, String aNameNEI, String aNEIGUIPath, long aInputItemsCount, long aOutputItemsCount, long aMinimalInputItems, long aMinimalInputFluids, long aPower, String aNEISpecialValuePre, long aNEISpecialValueMultiplier, String aNEISpecialValuePost, boolean aShowVoltageAmperageInNEI, boolean aNEIAllowed) {this(aRecipeList, aNameInternal, aNameLocal, aNameNEI, 0, 1, aNEIGUIPath, aInputItemsCount, aOutputItemsCount, aMinimalInputItems, 0, 0, aMinimalInputFluids, 0, aPower, aNEISpecialValuePre, aNEISpecialValueMultiplier, aNEISpecialValuePost, aShowVoltageAmperageInNEI, aNEIAllowed, T, T);}
		
		public Recipe addRecipe(boolean aOptimize, ItemStack[] aInputs, ItemStack[] aOutputs, Object aSpecial, long[] aOutputChances, FluidStack[] aFluidInputs, FluidStack[] aFluidOutputs, long aDuration, long aEUt, long aSpecialValue) {
			return addRecipe(new Recipe(aOptimize, T, T, aInputs, aOutputs, aSpecial, aOutputChances, aFluidInputs, aFluidOutputs, aDuration, aEUt, aSpecialValue));
		}
		
		public Recipe addRecipe(Recipe aRecipe) {
			return addRecipe(aRecipe, T, F, F, T);
		}
		
		// The Number next to the addRecipe is for the amount of Item Inputs used. X = Array
		
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration, long   aChance , ItemStack aInput                                                                            , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , ST.array(aOutput), NI, new long[] {aChance}, ZL_FS                , ZL_FS                 , aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration, long   aChance , ItemStack aInput1, ItemStack aInput2                                                        , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), ST.array(aOutput), NI, new long[] {aChance}, ZL_FS                , ZL_FS                 , aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration, long   aChance , ItemStack[] aInputs                                                                         , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , ST.array(aOutput), NI, new long[] {aChance}, ZL_FS                , ZL_FS                 , aDuration, aEUt, 0));}
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration                 , ItemStack aInput                                                                            , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, ZL_LONG             , ZL_FS                , ZL_FS                 , aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration                 , ItemStack aInput1, ItemStack aInput2                                                        , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, ZL_LONG             , ZL_FS                , ZL_FS                 , aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration                 , ItemStack[] aInputs                                                                         , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, ZL_LONG             , ZL_FS                , ZL_FS                 , aDuration, aEUt, 0));}
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration, long[] aChances, ItemStack aInput                                                                            , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, aChances            , ZL_FS                , ZL_FS                 , aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration, long[] aChances, ItemStack aInput1, ItemStack aInput2                                                        , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, aChances            , ZL_FS                , ZL_FS                 , aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration, long[] aChances, ItemStack[] aInputs                                                                         , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, aChances            , ZL_FS                , ZL_FS                 , aDuration, aEUt, 0));}
		public Recipe addRecipe0(boolean aOptimize, long aEUt, long aDuration, long   aChance                                       , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , ST.array(aOutput), NI, new long[] {aChance}, FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration, long   aChance , ItemStack aInput                    , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , ST.array(aOutput), NI, new long[] {aChance}, FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration, long   aChance , ItemStack aInput1, ItemStack aInput2, FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), ST.array(aOutput), NI, new long[] {aChance}, FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration, long   aChance , ItemStack[] aInputs                 , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , ST.array(aOutput), NI, new long[] {aChance}, FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe0(boolean aOptimize, long aEUt, long aDuration                                                       , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , aOutputs         , NI, ZL_LONG             , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration                 , ItemStack aInput                    , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, ZL_LONG             , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration                 , ItemStack aInput1, ItemStack aInput2, FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, ZL_LONG             , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration                 , ItemStack[] aInputs                 , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, ZL_LONG             , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe0(boolean aOptimize, long aEUt, long aDuration, long[] aChances                                      , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , aOutputs         , NI, aChances            , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration, long[] aChances, ItemStack aInput                    , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, aChances            , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration, long[] aChances, ItemStack aInput1, ItemStack aInput2, FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, aChances            , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration, long[] aChances, ItemStack[] aInputs                 , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, aChances            , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe0(boolean aOptimize, long aEUt, long aDuration                                                       , FluidStack   aFluidInput , FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , ZL_IS            , NI, ZL_LONG             , FL.array(aFluidInput), aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration                 , ItemStack aInput                    , FluidStack   aFluidInput , FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , ZL_IS            , NI, ZL_LONG             , FL.array(aFluidInput), aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration                 , ItemStack aInput1, ItemStack aInput2, FluidStack   aFluidInput , FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), ZL_IS            , NI, ZL_LONG             , FL.array(aFluidInput), aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration                 , ItemStack[] aInputs                 , FluidStack   aFluidInput , FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , ZL_IS            , NI, ZL_LONG             , FL.array(aFluidInput), aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe0(boolean aOptimize, long aEUt, long aDuration, long   aChance                                       , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration, long   aChance , ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration, long   aChance , ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration, long   aChance , ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe0(boolean aOptimize, long aEUt, long aDuration                                                       , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , aOutputs         , NI, ZL_LONG             , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration                 , ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, ZL_LONG             , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration                 , ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, ZL_LONG             , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration                 , ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, ZL_LONG             , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe0(boolean aOptimize, long aEUt, long aDuration, long[] aChances                                      , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , aOutputs         , NI, aChances            , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration, long[] aChances, ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, aChances            , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration, long[] aChances, ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, aChances            , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration, long[] aChances, ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, aChances            , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0));}
		public Recipe addRecipe0(boolean aOptimize, long aEUt, long aDuration                                                       , FluidStack[] aFluidInputs, FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , ZL_IS            , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration                 , ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , ZL_IS            , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration                 , ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), ZL_IS            , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration                 , ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , ZL_IS            , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe0(boolean aOptimize, long aEUt, long aDuration, long   aChance                                       , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration, long   aChance , ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration, long   aChance , ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration, long   aChance , ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe0(boolean aOptimize, long aEUt, long aDuration                                                       , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , aOutputs         , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration                 , ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration                 , ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration                 , ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe0(boolean aOptimize, long aEUt, long aDuration, long[] aChances                                      , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , aOutputs         , NI, aChances            , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe1(boolean aOptimize, long aEUt, long aDuration, long[] aChances, ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, aChances            , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipe2(boolean aOptimize, long aEUt, long aDuration, long[] aChances, ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, aChances            , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		public Recipe addRecipeX(boolean aOptimize, long aEUt, long aDuration, long[] aChances, ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, aChances            , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0));}
		
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance , ItemStack aInput                                                                            , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , ST.array(aOutput), NI, new long[] {aChance}, ZL_FS                , ZL_FS                 , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance , ItemStack aInput1, ItemStack aInput2                                                        , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), ST.array(aOutput), NI, new long[] {aChance}, ZL_FS                , ZL_FS                 , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance , ItemStack[] aInputs                                                                         , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , ST.array(aOutput), NI, new long[] {aChance}, ZL_FS                , ZL_FS                 , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack aInput                                                                            , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, ZL_LONG             , ZL_FS                , ZL_FS                 , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack aInput1, ItemStack aInput2                                                        , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, ZL_LONG             , ZL_FS                , ZL_FS                 , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack[] aInputs                                                                         , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, ZL_LONG             , ZL_FS                , ZL_FS                 , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances, ItemStack aInput                                                                            , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, aChances            , ZL_FS                , ZL_FS                 , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances, ItemStack aInput1, ItemStack aInput2                                                        , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, aChances            , ZL_FS                , ZL_FS                 , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances, ItemStack[] aInputs                                                                         , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, aChances            , ZL_FS                , ZL_FS                 , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe0(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance                                       , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , ST.array(aOutput), NI, new long[] {aChance}, FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance , ItemStack aInput                    , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , ST.array(aOutput), NI, new long[] {aChance}, FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance , ItemStack aInput1, ItemStack aInput2, FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), ST.array(aOutput), NI, new long[] {aChance}, FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance , ItemStack[] aInputs                 , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , ST.array(aOutput), NI, new long[] {aChance}, FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe0(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                                                       , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , aOutputs         , NI, ZL_LONG             , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack aInput                    , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, ZL_LONG             , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack aInput1, ItemStack aInput2, FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, ZL_LONG             , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack[] aInputs                 , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, ZL_LONG             , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe0(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances                                      , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , aOutputs         , NI, aChances            , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances, ItemStack aInput                    , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, aChances            , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances, ItemStack aInput1, ItemStack aInput2, FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, aChances            , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances, ItemStack[] aInputs                 , FluidStack   aFluidInput , FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, aChances            , FL.array(aFluidInput), FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe0(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                                                       , FluidStack   aFluidInput , FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , ZL_IS            , NI, ZL_LONG             , FL.array(aFluidInput), aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack aInput                    , FluidStack   aFluidInput , FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , ZL_IS            , NI, ZL_LONG             , FL.array(aFluidInput), aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack aInput1, ItemStack aInput2, FluidStack   aFluidInput , FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), ZL_IS            , NI, ZL_LONG             , FL.array(aFluidInput), aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack[] aInputs                 , FluidStack   aFluidInput , FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , ZL_IS            , NI, ZL_LONG             , FL.array(aFluidInput), aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe0(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance                                       , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance , ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance , ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance , ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe0(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                                                       , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , aOutputs         , NI, ZL_LONG             , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, ZL_LONG             , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, ZL_LONG             , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, ZL_LONG             , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe0(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances                                      , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , aOutputs         , NI, aChances            , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances, ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, aChances            , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances, ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, aChances            , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances, ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack    aFluidOutput , ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, aChances            , aFluidInputs         , FL.array(aFluidOutput), aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe0(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                                                       , FluidStack[] aFluidInputs, FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , ZL_IS            , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , ZL_IS            , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), ZL_IS            , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack... aFluidOutputs                       ) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , ZL_IS            , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe0(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance                                       , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance , ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance , ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long   aChance , ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack    aOutput ) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , ST.array(aOutput), NI, new long[] {aChance}, aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe0(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                                                       , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , aOutputs         , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration                 , ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, ZL_LONG             , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe0(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances                                      , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ZL_IS                     , aOutputs         , NI, aChances            , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe1(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances, ItemStack aInput                    , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput)          , aOutputs         , NI, aChances            , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipe2(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances, ItemStack aInput1, ItemStack aInput2, FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, ST.array(aInput1, aInput2), aOutputs         , NI, aChances            , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		public Recipe addRecipeX(boolean aOptimize, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors, long aEUt, long aDuration, long[] aChances, ItemStack[] aInputs                 , FluidStack[] aFluidInputs, FluidStack[]  aFluidOutputs, ItemStack... aOutputs) {return addRecipe(new Recipe(aOptimize, T, T, aInputs                   , aOutputs         , NI, aChances            , aFluidInputs         , aFluidOutputs         , aDuration, aEUt, 0), aCheckForCollisions, aFakeRecipe, aHidden, aLogErrors);}
		
		public Recipe addRecipe(Recipe aRecipe, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden, boolean aLogErrors) {
			aRecipe.mHidden = aHidden;
			aRecipe.mFakeRecipe = aFakeRecipe;
			if (aCheckForCollisions && findRecipeInternal(null, null, F, F, Long.MAX_VALUE, null, aRecipe.mFluidInputs, aRecipe.mInputs) != null) return null;
			return add(aRecipe, aLogErrors && mLogErrors);
		}
		
		public Recipe addRecipe(Recipe aRecipe, boolean aCheckForCollisions, boolean aFakeRecipe, boolean aHidden) {
			return addRecipe(aRecipe, aCheckForCollisions, aFakeRecipe, aHidden, mLogErrors);
		}
		
		/** Only used for fake Recipe Handlers to show something in NEI, do not use this for adding actual Recipes! findRecipe wont find fake Recipes, containsInput WILL find fake Recipes */
		public Recipe addFakeRecipe(boolean aCheckForCollisions, ItemStack[] aInputs, ItemStack[] aOutputs, Object aSpecial, long[] aOutputChances, FluidStack[] aFluidInputs, FluidStack[] aFluidOutputs, long aDuration, long aEUt, long aSpecialValue) {
			return addFakeRecipe(aCheckForCollisions, new Recipe(F, F, F, aInputs, aOutputs, aSpecial, aOutputChances, aFluidInputs, aFluidOutputs, aDuration, aEUt, aSpecialValue));
		}
		
		/** Only used for fake Recipe Handlers to show something in NEI, do not use this for adding actual Recipes! findRecipe wont find fake Recipes, containsInput WILL find fake Recipes */
		public Recipe addFakeRecipe(boolean aCheckForCollisions, ItemStack[] aInputs, ItemStack[] aOutputs, Object aSpecial, FluidStack[] aFluidInputs, FluidStack[] aFluidOutputs, long aDuration, long aEUt, long aSpecialValue) {
			return addFakeRecipe(aCheckForCollisions, new Recipe(F, F, F, aInputs, aOutputs, aSpecial, null, aFluidInputs, aFluidOutputs, aDuration, aEUt, aSpecialValue));
		}
		
		/** Only used for fake Recipe Handlers to show something in NEI, do not use this for adding actual Recipes! findRecipe wont find fake Recipes, containsInput WILL find fake Recipes */
		public Recipe addFakeRecipe(boolean aCheckForCollisions, Recipe aRecipe) {
			return addRecipe(aRecipe, aCheckForCollisions, T, F, mLogErrors);
		}
		
		public Recipe add(Recipe aRecipe) {
			return add(aRecipe, mLogErrors);
		}
		
		public Recipe add(Recipe aRecipe, boolean aLogErrors) {
			return add(aRecipe, aLogErrors, !aRecipe.mFakeRecipe && aRecipe.mCanBeBuffered);
		}
		
		public synchronized Recipe add(Recipe aRecipe, boolean aLogErrors, boolean aAddConfig) {
			if (!aRecipe.mFakeRecipe) {
				boolean tErrored = F, tFailed = F;
				if (aRecipe.mInputs.length + aRecipe.mFluidInputs.length <= 0) {
					if (aLogErrors) ERR.println("ERROR: Recipe has no Inputs!");
					tFailed = T;
				} else {
					if (mNeedsOutputs && aRecipe.mOutputs.length + aRecipe.mFluidOutputs.length <= 0) {if (aLogErrors) ERR.println("ERROR: Recipe has no Outputs!"                                              ); tFailed = T;}
					if (aRecipe.mInputs         .length < mMinimalInputItems                        ) {if (aLogErrors) ERR.println("ERROR: Recipe has less than the minimal amount of Input ItemStacks!"        ); tFailed = T;}
					if (aRecipe.mFluidInputs    .length < mMinimalInputFluids                       ) {if (aLogErrors) ERR.println("ERROR: Recipe has less than the minimal amount of Input FluidStacks!"       ); tFailed = T;}
					if (aRecipe.mFluidInputs.length + aRecipe.mInputs.length < mMinimalInputs       ) {if (aLogErrors) ERR.println("ERROR: Recipe has less than the minimal amount of general Inputs!"          ); tFailed = T;}
					if (aRecipe.mInputs         .length > mInputItemsCount                          ) {if (aLogErrors) ERR.println("ERROR: Recipe has more than the maximum amount of Input ItemStacks!"        ); tFailed = T;}
					if (aRecipe.mFluidInputs    .length > mInputFluidCount                          ) {if (aLogErrors) ERR.println("ERROR: Recipe has more than the maximum amount of Input FluidStacks!"       ); tFailed = T;}
				}
				if (aRecipe.mOutputs            .length > mOutputItemsCount                         ) {if (aLogErrors) ERR.println("WARNING: Recipe has more than the maximum amount of Output ItemStacks!"     ); tErrored = T;}
				if (aRecipe.mFluidOutputs       .length > mOutputFluidCount                         ) {if (aLogErrors) ERR.println("WARNING: Recipe has more than the maximum amount of Output FluidStacks!"    ); tErrored = T;}
				if (aRecipe.mDuration                                                           == 0) {if (aLogErrors) ERR.println("WARNING: Recipe has no Duration Value!"                                     ); tErrored = T;}
				
				if (tErrored || tFailed) {
					if (aLogErrors) {
						DEB.println("Recipe Map: " + mNameInternal);
						DEB.println("Input Items:  " + ST.names(aRecipe.mInputs));
						DEB.println("Input Fluid:  " + FL.configNames(aRecipe.mFluidInputs));
						DEB.println("Output Items: " + ST.names(aRecipe.mOutputs));
						DEB.println("Output Fluid: " + FL.configNames(aRecipe.mFluidOutputs));
						int i = 0; for (StackTraceElement tElement : new Exception().getStackTrace()) if (!tElement.getClassName().equals(RecipeMap.class.getName())) if (i++<5 && !tElement.getClassName().startsWith("sun")) ERR.println("\tat " + tElement); else break;
					}
					if (tFailed) return null;
				}
			}
			
			for (FluidStack tFluid : aRecipe.mFluidInputs) if (FL.Error.is(tFluid)) {
				if (D1) {
					DEB.println("Compat: The Fluid for a Recipe has not been found! This might just be for a Mod that is not installed!");
					DEB.println("Input Items:  " + ST.names(aRecipe.mInputs));
					DEB.println("Input Fluid:  " + FL.configNames(aRecipe.mFluidInputs));
					DEB.println("Output Items: " + ST.names(aRecipe.mOutputs));
					DEB.println("Output Fluid: " + FL.configNames(aRecipe.mFluidOutputs));
					int i = 0; for (StackTraceElement tElement : new Exception().getStackTrace()) if (!tElement.getClassName().equals(RecipeMap.class.getName())) if (i++<5 && !tElement.getClassName().startsWith("sun")) DEB.println("\tat " + tElement); else break;
				}
				return null;
			}
			
			if (aAddConfig && mConfigFile != null) {
				String tConfigName = "";
				if (aRecipe.mInputs.length > 0) tConfigName += ST.configNames(aRecipe.mInputs);
				if (aRecipe.mFluidInputs.length > 0) tConfigName += FL.configNames(aRecipe.mFluidInputs);
				if (UT.Code.stringValid(tConfigName)) {
					aRecipe.mDuration = mConfigFile.get(mNameLocalUnderscored, tConfigName, aRecipe.mEnabled?aRecipe.mDuration:0);
					aRecipe.mEnabled = (aRecipe.mDuration > 0);
				}
			}
			
			if (!aRecipe.mEnabled || !mRecipeList.add(aRecipe)) return null;
			
			mRecipeListSize++;
			
			for (FluidStack aFluid : aRecipe.mFluidInputs) if (aFluid != null) {
				String aFluidName = aFluid.getFluid().getName();
				mMaxFluidInputSize = Math.max(mMaxFluidInputSize, aFluid.amount);
				Collection<Recipe> tList = mRecipeFluidMap.get(aFluidName);
				if (tList == null) mRecipeFluidMap.put(aFluidName, tList = new HashSet<>(1));
				tList.add(aRecipe);
				Long tSize = mMinInputTankSizes.get(aFluidName);
				if (tSize == null || tSize < aFluid.amount) mMinInputTankSizes.put(aFluidName, (long)aFluid.amount);
			}
			for (FluidStack aFluid : aRecipe.mFluidOutputs) if (aFluid != null) {
				mMaxFluidOutputSize = Math.max(mMaxFluidOutputSize, aFluid.amount);
			}
			return addToItemMap(aRecipe);
		}
		
		public void reInit() {
			mRecipeItemMap.clear();
			for (Recipe tRecipe : mRecipeList) {
				OreDictManager.INSTANCE.setStackArray(T, tRecipe.mInputs);
				OreDictManager.INSTANCE.setStackArray(T, tRecipe.mOutputs);
				addToItemMap(tRecipe);
			}
			mRecipeListSize = mRecipeList.size();
		}
		
		public boolean add(IRecipeMapHandler aRecipeMapHandler) {
			return mRecipeMapHandlers.add(aRecipeMapHandler) && aRecipeMapHandler.onAddedToMap(this);
		}
		
		public Slot_Base getSpecialSlot(ITileEntityInventoryGUI aInventory, int aIndex, int aX, int aY) {
			return null;
		}
		
		/** @return if this Item is a valid Input for any for the Recipes */
		public boolean containsInput(ItemStack aStack, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {
			if (ST.invalid(aStack)) return F;
			if (mRecipeItemMap.containsKey(ST.item_(aStack), ST.meta_(aStack), T)) return T;
			if (mRecipeMapHandlers.isEmpty()) return F;
			OreDictItemData aData = OM.data_(aStack);
			for (IRecipeMapHandler tHandler : mRecipeMapHandlers) if (tHandler.containsInput(this, aStack, aData)) return T;
			return F;
		}
		
		/** @return if this Fluid is a valid Input for any for the Recipes */
		public boolean containsInput(FluidStack aFluid, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {
			return aFluid != null && containsInput(aFluid.getFluid(), aTileEntity, aSpecialSlot);
		}
		
		/** @return if this Fluid is a valid Input for any for the Recipes */
		public boolean containsInput(Fluid aFluid, IHasWorldAndCoords aTileEntity, ItemStack aSpecialSlot) {
			if (aFluid == null) return F;
			if (mRecipeFluidMap.containsKey(aFluid.getName())) return T;
			if (mRecipeMapHandlers.isEmpty()) return F;
			for (IRecipeMapHandler tHandler : mRecipeMapHandlers) if (tHandler.containsInput(this, aFluid)) return T;
			return F;
		}
		
		/** @return the Tank Size that is the Minimum for this Fluid Input.*/
		public long minTankSize(Fluid aFluid) {
			if (aFluid == null) return 1000;
			Object tSize = mMinInputTankSizes.get(aFluid.getName());
			return tSize == null ? 1000 : Math.max(1000, (long)tSize);
		}
		
		private Recipe oRecipe = null;
		
		/**
		 * finds a Recipe matching the Fluid and ItemStack Inputs.
		 * @param aTileEntity an Object representing the current coordinates of the executing Block/Entity/Whatever. This may be null, especially during Startup.
		 * @param aRecipe in case this is != null it will try to use this Recipe first when looking things up.
		 * @param aNotUnificated if this is T the Recipe searcher will unificate the ItemStack Inputs
		 * @param aSize Voltage of the Machine or Long.MAX_VALUE if it has no Voltage
		 * @param aFluids the Fluid Inputs
		 * @param aSpecialSlot the content of the Special Slot, the regular Manager doesn't do anything with this, but some custom ones do.
		 * @param aInputs the Item Inputs
		 * @return the Recipe it has found or null for no matching Recipe
		 */
		public Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
			return findRecipeInternal(aTileEntity, aRecipe, T, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		}
		
		/**
		 * finds a Recipe matching the Fluid and ItemStack Inputs. (Using IFluidTank as parameter and converting it automatically)
		 * @param aTileEntity an Object representing the current coordinates of the executing Block/Entity/Whatever. This may be null, especially during Startup.
		 * @param aRecipe in case this is != null it will try to use this Recipe first when looking things up.
		 * @param aNotUnificated if this is T the Recipe searcher will unificate the ItemStack Inputs
		 * @param aSize Voltage of the Machine or Long.MAX_VALUE if it has no Voltage
		 * @param aTanks the Fluid Inputs
		 * @param aSpecialSlot the content of the Special Slot, the regular Manager doesn't do anything with this, but some custom ones do.
		 * @param aInputs the Item Inputs
		 * @return the Recipe it has found or null for no matching Recipe
		 */
		public final Recipe findRecipe(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, IFluidTank[] aTanks, ItemStack... aInputs) {
			FluidStack[] aFluids = new FluidStack[aTanks.length];
			for (int i = 0; i < aFluids.length; i++) aFluids[i] = aTanks[i].getFluid();
			return findRecipe(aTileEntity, aRecipe, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
		}
		
		public Recipe findRecipeInternal(IHasWorldAndCoords aTileEntity, Recipe aRecipe, boolean aLoop, boolean aNotUnificated, long aSize, ItemStack aSpecialSlot, FluidStack[] aFluids, ItemStack... aInputs) {
			// No Recipes? Well, nothing to be found then.
			if (mRecipeList.isEmpty() && (!aLoop || mRecipeMapHandlers.isEmpty())) return null;
			
			// Some Recipe Classes require a certain amount of Inputs of certain kinds. Like "at least 1 Fluid + 1 Stack" or "at least 2 Stacks" before they start searching for Recipes.
			// This improves Performance massively, especially if people leave things like Circuits, Molds or Shapes in their Machines to select Sub Recipes.
			if (GAPI_POST.mFinishedServerStarted > 0) {
				long tItemAmount = UT.Code.getNonNulls(aInputs);
				if (tItemAmount < mMinimalInputItems) return null;
				long tFluidAmount = UT.Code.getNonNulls(aFluids);
				if (tFluidAmount < mMinimalInputFluids) return null;
				if (tFluidAmount + tItemAmount < mMinimalInputs) return null;
			}
			
			// Unification happens here in case the Input isn't already unificated.
			if (aNotUnificated) aInputs = OreDictManager.INSTANCE.getStackArray(T, (Object[])aInputs);
			
			// Check the Recipe which has been used last time in order to not have to search for it again, if possible.
			if (aRecipe != null) if (!aRecipe.mFakeRecipe && aRecipe.mCanBeBuffered && aRecipe.isRecipeInputEqual(F, T, aFluids, aInputs)) return aRecipe.mEnabled&&UT.Code.abs_greater_equal(aSize*mPower, aRecipe.mEUt)?oRecipe=aRecipe:null;
			if (oRecipe != null) if (!oRecipe.mFakeRecipe && oRecipe.mCanBeBuffered && oRecipe.isRecipeInputEqual(F, T, aFluids, aInputs)) return oRecipe.mEnabled&&UT.Code.abs_greater_equal(aSize*mPower, oRecipe.mEUt)?oRecipe:null;
			
			// Because MineTweaker screws up at this.
			if (aLoop && mRecipeListSize != mRecipeList.size()) {
				ERR.println("RecipeMap for " + mNameLocal + " got changed without re-initializing the HashMaps! This is a Bug of whatever Recipe Tweaker Mod you are using!");
				reInit();
			}
			
			// Now look for the Recipes inside the Item HashMaps, but only when the Recipes usually have Items.
			if (mInputItemsCount > 0) for (ItemStack tStack1 : aInputs) if (tStack1 != null) {
				Collection<Recipe>
				tRecipes = mRecipeItemMap.get(new ItemStackContainer(tStack1));
				if (tRecipes != null) for (Recipe tRecipe : tRecipes) if (!tRecipe.mFakeRecipe && tRecipe.isRecipeInputEqual(F, T, aFluids, aInputs)) return tRecipe.mEnabled&&UT.Code.abs_greater_equal(aSize*mPower, tRecipe.mEUt)?oRecipe=tRecipe:null;
				
				tRecipes = mRecipeItemMap.get(new ItemStackContainer(tStack1, W));
				if (tRecipes != null) for (Recipe tRecipe : tRecipes) if (!tRecipe.mFakeRecipe && tRecipe.isRecipeInputEqual(F, T, aFluids, aInputs)) return tRecipe.mEnabled&&UT.Code.abs_greater_equal(aSize*mPower, tRecipe.mEUt)?oRecipe=tRecipe:null;
				
				ItemStack tStack2 = OreDictManager.INSTANCE.getStack_(F, tStack1);
				
				if (!ST.equal(tStack1, tStack2, T)) {
				tRecipes = mRecipeItemMap.get(new ItemStackContainer(tStack2));
				if (tRecipes != null) for (Recipe tRecipe : tRecipes) if (!tRecipe.mFakeRecipe && tRecipe.isRecipeInputEqual(F, T, aFluids, aInputs)) return tRecipe.mEnabled&&UT.Code.abs_greater_equal(aSize*mPower, tRecipe.mEUt)?oRecipe=tRecipe:null;
				}
				if (tStack1.getItem() != tStack2.getItem()) {
				tRecipes = mRecipeItemMap.get(new ItemStackContainer(tStack2, W));
				if (tRecipes != null) for (Recipe tRecipe : tRecipes) if (!tRecipe.mFakeRecipe && tRecipe.isRecipeInputEqual(F, T, aFluids, aInputs)) return tRecipe.mEnabled&&UT.Code.abs_greater_equal(aSize*mPower, tRecipe.mEUt)?oRecipe=tRecipe:null;
				}
			}
			
			// If the minimal Amount of Items for the Recipe is 0, then it could be a Fluid-Only Recipe, so check that Map too.
			if (mInputFluidCount > 0 && mMinimalInputItems == 0) for (FluidStack aFluid : aFluids) if (aFluid != null) {
				Collection<Recipe>
				tRecipes = mRecipeFluidMap.get(aFluid.getFluid().getName());
				if (tRecipes != null) for (Recipe tRecipe : tRecipes) if (!tRecipe.mFakeRecipe && tRecipe.isRecipeInputEqual(F, T, aFluids, aInputs)) return tRecipe.mEnabled&&UT.Code.abs_greater_equal(aSize*mPower, tRecipe.mEUt)?oRecipe=tRecipe:null;
			}
			
			// Apply Recipe Map Handlers to "discover" new Recipes.
			if (aLoop && !mRecipeMapHandlers.isEmpty()) {
				for (int i = 0; i < mRecipeMapHandlers.size(); i++) {
					IRecipeMapHandler tHandler = mRecipeMapHandlers.get(i);
					if (tHandler.isDone()) mRecipeMapHandlers.remove(tHandler);
				}
				if (!mRecipeMapHandlers.isEmpty()) {
					aLoop = F;
					for (ItemStack tInput : aInputs) if (ST.valid(tInput)) {
						OreDictItemData tData = OM.data_(tInput);
						for (IRecipeMapHandler tHandler : mRecipeMapHandlers) if (tHandler.addRecipesUsing(this, F, tInput, tData)) aLoop = T;
					}
					for (FluidStack tInput : aFluids) if (tInput != null) {
						for (IRecipeMapHandler tHandler : mRecipeMapHandlers) if (tHandler.addRecipesUsing(this, F, tInput.getFluid())) aLoop = T;
					}
					if (aLoop) return findRecipeInternal(aTileEntity, aRecipe, F, aNotUnificated, aSize, aSpecialSlot, aFluids, aInputs);
				}
			}
			
			// And nothing has been found.
			return null;
		}
		
		public List<Recipe> getNEIAllRecipes() {
			long tTimeBefore = System.currentTimeMillis();
			for (int i = 0; i < mRecipeMapHandlers.size(); i++) {
				IRecipeMapHandler tHandler = mRecipeMapHandlers.get(i);
				tHandler.addAllRecipes(this);
				if (tHandler.isDone()) mRecipeMapHandlers.remove(tHandler);
				if (System.currentTimeMillis() - tTimeBefore > 60000) break;
			}
			ArrayListNoNulls<Recipe> rList = new ArrayListNoNulls<>();
			for (Recipe tRecipe : mRecipeList) if (tRecipe.mEnabled && !tRecipe.mHidden) rList.add(tRecipe);
			return rList;
		}
		
		public List<Recipe> getNEIRecipes(ItemStack... aOutputs) {
			for (ItemStack aOutput : aOutputs) if (ST.valid(aOutput)) {
				if (IL.Display_Fluid.equal(aOutput, T, T)) {
					Fluid tFluid = FL.fluid(ST.meta_(aOutput));
					if (tFluid != null) for (IRecipeMapHandler tHandler : mRecipeMapHandlers) tHandler.addRecipesProducing(this, T, tFluid);
				} else {
					OreDictItemData tData = OM.data_(aOutput);
					for (IRecipeMapHandler tHandler : mRecipeMapHandlers) tHandler.addRecipesProducing(this, T, aOutput, tData);
				}
			}
			ArrayListNoNulls<Recipe> rList = new ArrayListNoNulls<>();
			for (Recipe tRecipe : mRecipeList) if (tRecipe.mEnabled && !tRecipe.mHidden) {
				for (ItemStack aOutput : aOutputs) if (aOutput != null) {
					if (IL.Display_Fluid.equal(aOutput, T, T)) {
						for (FluidStack tOutput : tRecipe.mFluidOutputs) {
							if (ST.meta_(aOutput) >= 0 && FL.id(tOutput) == ST.meta_(aOutput)) {
								rList.add(tRecipe);
								break;
							}
						}
					} else {
						for (ItemStack tOutput : tRecipe.mOutputs) if (tOutput != null && tOutput.getItem() == aOutput.getItem()) {
							if (ST.meta_(tOutput) == W || ST.meta_(tOutput) == ST.meta_(aOutput) || tOutput.isItemStackDamageable()) {
								rList.add(tRecipe);
								break;
							}
						}
					}
				}
			}
			return rList;
		}
		
		public List<Recipe> getNEIUsages(ItemStack... aInputs) {
			for (ItemStack aInput : aInputs) if (ST.valid(aInput)) {
				if (IL.Display_Fluid.equal(aInput, T, T)) {
					Fluid tFluid = FL.fluid(ST.meta_(aInput));
					if (tFluid != null) for (IRecipeMapHandler tHandler : mRecipeMapHandlers) tHandler.addRecipesUsing(this, T, tFluid);
				} else {
					OreDictItemData tData = OM.data_(aInput);
					for (IRecipeMapHandler tHandler : mRecipeMapHandlers) tHandler.addRecipesUsing(this, T, aInput, tData);
				}
			}
			ArrayListNoNulls<Recipe> rList = new ArrayListNoNulls<>();
			for (Recipe tRecipe : mRecipeList) if (tRecipe.mEnabled && !tRecipe.mHidden) {
				for (ItemStack aInput : aInputs) if (aInput != null) {
					if (IL.Display_Fluid.equal(aInput, T, T)) {
						for (FluidStack tInput : tRecipe.mFluidInputs) {
							if (ST.meta_(aInput) >= 0 && FL.id(tInput) == ST.meta_(aInput)) {
								rList.add(tRecipe);
								break;
							}
						}
					} else {
						for (ItemStack tInput : tRecipe.mInputs) if (tInput != null && tInput.getItem() == aInput.getItem()) {
							if (ST.meta_(tInput) == W || ST.meta_(tInput) == ST.meta_(aInput) || tInput.isItemStackDamageable()) {
								rList.add(tRecipe);
								break;
							}
						}
					}
				}
			}
			return rList;
		}
		
		public Recipe addToItemMap(Recipe aRecipe) {
			for (ItemStack aStack : aRecipe.mInputs) if (aStack != null) {
				ItemStackContainer tStack = new ItemStackContainer(aStack);
				Collection<Recipe> tList = mRecipeItemMap.get(tStack);
				if (tList == null) mRecipeItemMap.put(tStack, tList = new HashSet<>(1));
				tList.add(aRecipe);
			}
			return aRecipe;
		}
		
		public boolean openNEI   (                  ) {try {codechicken.nei.recipe.GuiCraftingRecipe.openRecipeGui(mNameNEI          ); return T;} catch(Throwable e) {/**/} return F;}
		public boolean guiRecipes(Object... aOutputs) {try {codechicken.nei.recipe.GuiCraftingRecipe.openRecipeGui(mNameNEI, aOutputs); return T;} catch(Throwable e) {/**/} return F;}
		public boolean guiUsesNEI(Object... aInputs ) {try {codechicken.nei.recipe.GuiUsageRecipe   .openRecipeGui(mNameNEI, aInputs ); return T;} catch(Throwable e) {/**/} return F;}
		
		/** Old position for the Recipe Maps, please refer to gregapi.data.RM and gregapi.data.FM in the future. */
		@Deprecated public static RecipeMap sMaceratorRecipes = new RecipeMap(), sFurnaceRecipes = RM.Furnace, sMicrowaveRecipes = RM.Microwave, sFurnaceFuel = FM.Furnace, sByProductList = RM.ByProductList, sCrucibleSmelting = RM.CrucibleSmelting,
		sCrucibleAlloying = RM.CrucibleAlloying, sGenerifierRecipes = RM.Generifier, sSharpeningRecipes = RM.Sharpening, sSifterRecipes = RM.Sifting, sHammerRecipes = RM.Hammer, sChiselRecipes = RM.Chisel, sShredderRecipes = RM.Shredder,
		sCrusherRecipes = RM.Crusher, sLatheRecipes = RM.Lathe, sCutterRecipes = RM.Cutter, sCoagulatorRecipes = RM.Coagulator, sSqueezerRecipes = RM.Squeezer, sJuicerRecipes = RM.Juicer, sMortarRecipes = RM.Mortar, sCompressorRecipes = RM.Compressor,
		sCentrifugeRecipes = RM.Centrifuge, sElectrolyzerRecipes = RM.Electrolyzer, sRollingMillRecipes = RM.RollingMill, sRollBenderRecipes = RM.RollBender, sRollFormerRecipes = RM.RollFormer, sClusterMillRecipes = RM.ClusterMill,
		sWiremillRecipes = RM.Wiremill, sMixerRecipes = RM.Mixer, sCannerRecipes = RM.Canner, sInjectorRecipes = RM.Injector, sRoastingRecipes = RM.Roasting, sDryingRecipes = RM.Drying, sFermenterRecipes = RM.Fermenter, sDistilleryRecipes = RM.Distillery,
		sExtruderRecipes = RM.Extruder, sPolarizerRecipes = RM.Polarizer, sLoomRecipes = RM.Loom, sCookingRecipes = RM.Cooking, sPressRecipes = RM.Press, sBathRecipes = RM.Bath, sSmelterRecipes = RM.Smelter, sLaserEngraverRecipes = RM.LaserEngraver,
		sWelderRecipes = RM.Welder, sCrystallisationCrucibleRecipes = RM.CrystallisationCrucible, sScannerVisualsRecipes = RM.ScannerVisuals, sPrinterRecipes = RM.Printer, sSluiceRecipes = RM.Sluice, sMagneticSeparatorRecipes = RM.MagneticSeparator,
		sAutocrafterRecipes = RM.Autocrafter, sMassfabRecipes = RM.Massfab, sScannerMolecularRecipes = RM.ScannerMolecular, sReplicatorRecipes = RM.Replicator, sSlicerRecipes = RM.Slicer, sCokeOvenRecipes = RM.CokeOven, sDistillationTowerRecipes = RM.DistillationTower,
		sAutoclaveRecipes = RM.Autoclave, sBoxinatorRecipes = RM.Boxinator, sUnboxinatorRecipes = RM.Unboxinator, sFusionRecipes = RM.Fusion, sBlastRecipes = RM.BlastFurnace, sImplosionRecipes = RM.ImplosionCompressor, sVacuumRecipes = RM.VacuumFreezer,
		sAssemblerRecipes = RM.Assembler, sCNCRecipes = RM.CNC, sFuelsBurn = FM.Burn, sFuelsGas = FM.Gas, sFuelsHot = FM.Hot, sFuelsPlasma = FM.Plasma, sFuelsEngine = FM.Engine, sFuelsTurbine = FM.Turbine, sFuelsMagic = FM.Magic;
	}
	
	public static void reInit() {for (RecipeMap tMapEntry : RecipeMap.RECIPE_MAPS.values()) tMapEntry.reInit();}
	
	/** If you want to change the Output, feel free to modify or even replace the whole ItemStack Array, for Inputs, please add a new Recipe, because of the HashMaps. */
	public ItemStack[] mInputs, mOutputs;
	/** If you want to change the Output, feel free to modify or even replace the whole ItemStack Array, for Inputs, please add a new Recipe, because of the HashMaps. */
	public FluidStack[] mFluidInputs, mFluidOutputs;
	/** If you changed the amount of Array-Items inside the Output Array then the length of this Array must be larger or equal to the Output Array. A chance of 10000 equals 100% if the MaxChance Value is default. */
	public long[] mChances, mMaxChances;
	/** An Item that needs to be inside the Special Slot, like for example the Copy Slot inside the Printer. This is only useful for Fake Recipes in NEI, since findRecipe() and containsInput() don't give a shit about this Field. Lists are also possible. */
	public Object mSpecialItems;
	
	public long mDuration, mEUt, mSpecialValue;
	
	/** Use this to just disable a specific Recipe, but the Configuration enables that already for every single Recipe. */
	public boolean mEnabled = T;
	/** If this Recipe is hidden from NEI */
	public boolean mHidden = F;
	/** If this Recipe is Fake and therefore doesn't get found by the findRecipe Function (It is still in the HashMaps, so that containsInput does return T on those fake Inputs) */
	public boolean mFakeRecipe = F;
	/** If this Recipe can be stored inside a Machine in order to make Recipe searching more Efficient by trying the previously used Recipe first. In case you have a Recipe Map overriding things and returning one time use Recipes, you have to set this to F. */
	public boolean mCanBeBuffered = T;
	/** If this Recipe needs the Output Slots to be completely empty. Needed in case you have randomised Outputs */
	public boolean mNeedsEmptyOutput = F;
	
	public int getOutputChance(long aIndex) {if (aIndex < 0 || aIndex >= mChances.length) return getMaxChance(aIndex); return (int)mChances[(int)aIndex];}
	public int getMaxChance(long aIndex) {if (aIndex < 0 || aIndex >= mMaxChances.length) return 10000; return (int)mMaxChances[(int)aIndex];}
	
	public ItemStack getRepresentativeInput(long aIndex) {if (aIndex < 0 || aIndex >= mInputs.length) return null; return ST.copy(mInputs[(int)aIndex]);}
	public ItemStack getOutput(long aIndex) {if (aIndex < 0 || aIndex >= mOutputs.length) return null; return ST.copy(mOutputs[(int)aIndex]);}
	
	public FluidStack getRepresentativeFluidInput(long aIndex) {if (aIndex < 0 || aIndex >= mFluidInputs.length || mFluidInputs[(int)aIndex] == null) return null; return mFluidInputs[(int)aIndex].copy();}
	public FluidStack getFluidOutput(long aIndex) {if (aIndex < 0 || aIndex >= mFluidOutputs.length || mFluidOutputs[(int)aIndex] == null) return null; return mFluidOutputs[(int)aIndex].copy();}
	
	public Recipe copy() {
		return new Recipe(this);
	}
	
	public Recipe setSpecialNumber(long aNumber) {
		mSpecialValue = aNumber;
		return this;
	}
	
	public Recipe setNoBuffering() {
		mCanBeBuffered = F;
		return this;
	}
	
	public Recipe setNeedEmptyOut() {
		mNeedsEmptyOutput = T;
		return this;
	}
	
	public boolean blockINblockOUT() {
		return mInputs.length == 1 && mOutputs.length == 1 && mFluidInputs.length == 0 && mFluidOutputs.length == 0 && ST.block(mInputs[0]) != NB && ST.block(mOutputs[0]) != NB && mInputs[0].stackSize == 1 && mOutputs[0].stackSize == 1;
	}
	
	public FluidStack[] getFluidOutputs() {
		return getFluidOutputs(RNGSUS, 1);
	}
	
	public FluidStack[] getFluidOutputs(Random aRandom) {
		return getFluidOutputs(aRandom, 1);
	}
	
	public FluidStack[] getFluidOutputs(Random aRandom, int aProcessCount) {
		FluidStack[] rArray = new FluidStack[mFluidOutputs.length];
		for (int i = 0; i < rArray.length; i++) rArray[i] = FL.mul(getFluidOutput(i), aProcessCount);
		return rArray;
	}
	
	public ItemStack[] getOutputs() {
		return getOutputs(RNGSUS, 1);
	}
	
	public ItemStack[] getOutputs(Random aRandom) {
		return getOutputs(aRandom, 1);
	}
	
	public ItemStack[] getOutputs(Random aRandom, int aProcessCount) {
		ItemStack[] rArray = new ItemStack[mOutputs.length];
		if (aRandom == null) aRandom = new Random();
		for (int i = 0; i < rArray.length; i++) {
			ItemStack tOutput = getOutput(i);
			if (tOutput != null) {
				int tChance = getOutputChance(i);
				if (tChance > 0) {
					int tMax = getMaxChance(i);
					if (tChance >= tMax) {
						rArray[i] = ST.mul_(aProcessCount, tOutput);
					} else {
						for (int j = 0, k = tOutput.stackSize * aProcessCount; j < k; j++) if (aRandom.nextInt(tMax) < tChance) {
							if (rArray[i] == null) rArray[i] = ST.amount(1, tOutput); else rArray[i].stackSize++;
						}
					}
				} else {
					rArray[i] = tOutput;
				}
			}
		}
		return rArray;
	}
	
	public boolean checkStacksEqual(boolean aDecreaseStacksizeBySuccess, boolean aDontCheckStackSizes, ItemStack... aInputs) {
		boolean[] tChecked = new boolean[aInputs.length];
		for (ItemStack tInput : mInputs) if (ST.valid(tInput)) {
			boolean temp = T;
			for (int i = 0; i < aInputs.length; i++) if (!tChecked[i]) {
				ItemStack aInput = aInputs[i];
				if (ST.valid(aInput)) {
					if ((aDontCheckStackSizes || aInput.stackSize >= tInput.stackSize) && OreDictManager.INSTANCE.equal_(F, aInput, tInput)) {
						if (aDecreaseStacksizeBySuccess) aInput.stackSize -= tInput.stackSize;
						tChecked[i] = T;
						temp = F;
						break;
					}
				} else {
					tChecked[i] = T;
				}
			}
			if (temp) return F;
		}
		return T;
	}
	
	@Deprecated
	public boolean isRecipeInputEqual(boolean aDecreaseStacksizeBySuccess, FluidStack[] aFluidInputs, ItemStack... aInputs) {
		return isRecipeInputEqual(aDecreaseStacksizeBySuccess, F, aFluidInputs, aInputs);
	}
	
	public boolean isRecipeInputEqual(boolean aDecreaseStacksizeBySuccess, boolean aDontCheckStackSizes, FluidStack[] aFluidInputs, ItemStack... aInputs) {
		if (mFluidInputs.length > 0 && (aFluidInputs == null || aFluidInputs.length < 1)) return F;     
		if (mInputs.length > 0 && (aInputs == null || aInputs.length < 1)) return F;
		
		for (FluidStack tFluid : mFluidInputs) if (tFluid != null) {
			boolean temp = T;
			for (FluidStack aFluid : aFluidInputs) if (aFluid != null && aFluid.isFluidEqual(tFluid) && (aDontCheckStackSizes || aFluid.amount >= tFluid.amount)) {temp = F; break;}
			if (temp) return F;
		}
		
		if (!checkStacksEqual(F, aDontCheckStackSizes, aInputs)) return F;
		
		if (aDecreaseStacksizeBySuccess) {
			for (FluidStack tFluid : mFluidInputs) if (tFluid != null) for (FluidStack aFluid : aFluidInputs) if (aFluid != null && aFluid.isFluidEqual(tFluid) && aFluid.amount >= tFluid.amount) {aFluid.amount -= tFluid.amount; break;}
			checkStacksEqual(T, F, aInputs);
		}
		
		return T;
	}
	
	public boolean isRecipeInputEqual(boolean aDecreaseStacksizeBySuccess, boolean aDontCheckStackSizes, IFluidTank[] aFluidInputs, ItemStack... aInputs) {
		if (mFluidInputs.length > 0 && (aFluidInputs == null || aFluidInputs.length < 1)) return F;     
		if (mInputs.length > 0 && (aInputs == null || aInputs.length < 1)) return F;
		
		for (FluidStack tFluid : mFluidInputs) if (tFluid != null) {
			boolean temp = T;
			for (IFluidTank tTank : aFluidInputs) {FluidStack aFluid = tTank.getFluid(); if (aFluid != null && aFluid.isFluidEqual(tFluid) && (aDontCheckStackSizes || aFluid.amount >= tFluid.amount)) {temp = F; break;}}
			if (temp) return F;
		}
		
		if (!checkStacksEqual(F, aDontCheckStackSizes, aInputs)) return F;
		
		if (aDecreaseStacksizeBySuccess) {
			for (FluidStack tFluid : mFluidInputs) if (tFluid != null) for (IFluidTank tTank : aFluidInputs) {FluidStack aFluid = tTank.getFluid(); if (aFluid != null && aFluid.isFluidEqual(tFluid) && aFluid.amount >= tFluid.amount) {tTank.drain(tFluid.amount, T); break;}}
			checkStacksEqual(T, F, aInputs);
		}
		
		return T;
	}
	
	public int isRecipeInputEqual(int aMaxProcessCount, IFluidTank[] aFluidInputs, ItemStack... aInputs) {
		if (aMaxProcessCount <= 0) return 0;
		if (mFluidInputs.length > 0 && (aFluidInputs == null || aFluidInputs.length < 1)) return 0;     
		if (mInputs.length > 0 && (aInputs == null || aInputs.length < 1)) return 0;
		
		int rProcessCount = 0;
		while (rProcessCount < aMaxProcessCount) {
			for (FluidStack tFluid : mFluidInputs) if (tFluid != null) {
				boolean temp = T;
				for (IFluidTank tTank : aFluidInputs) {FluidStack aFluid = tTank.getFluid(); if (aFluid != null && aFluid.isFluidEqual(tFluid) && aFluid.amount >= tFluid.amount) {temp = F; break;}}
				if (temp) return rProcessCount;
			}
			
			if (!checkStacksEqual(F, F, aInputs)) return rProcessCount;
			
			for (FluidStack tFluid : mFluidInputs) if (tFluid != null) for (IFluidTank tTank : aFluidInputs) {FluidStack aFluid = tTank.getFluid(); if (aFluid != null && aFluid.isFluidEqual(tFluid) && (aFluid.amount >= tFluid.amount)) {tTank.drain(tFluid.amount, T); break;}}
			checkStacksEqual(T, F, aInputs);
			
			rProcessCount++;
		}
		return rProcessCount;
	}
	
	private Recipe(Recipe aRecipe) {
		mInputs = ST.copyArray(aRecipe.mInputs);
		mOutputs = ST.copyArray(aRecipe.mOutputs);
		mSpecialItems = aRecipe.mSpecialItems;
		mChances = Arrays.copyOf(aRecipe.mChances, aRecipe.mChances.length);
		mMaxChances = Arrays.copyOf(aRecipe.mMaxChances, aRecipe.mMaxChances.length);
		mFluidInputs = FL.copy(aRecipe.mFluidInputs);
		mFluidOutputs = FL.copy(aRecipe.mFluidOutputs);
		mEUt = aRecipe.mEUt;
		mDuration = aRecipe.mDuration;
		mSpecialValue = aRecipe.mSpecialValue;
		mNeedsEmptyOutput = aRecipe.mNeedsEmptyOutput;
		mCanBeBuffered = aRecipe.mCanBeBuffered;
		mFakeRecipe = aRecipe.mFakeRecipe;
		mEnabled = aRecipe.mEnabled;
		mHidden = aRecipe.mHidden;
	}
	
	public Recipe(boolean aOptimize, boolean aUnificate, ItemStack[] aInputs, ItemStack[] aOutputs, Object aSpecialItems, long[] aChances, FluidStack[] aFluidInputs, FluidStack[] aFluidOutputs, long aDuration, long aEUt, long aSpecialValue) {
		this(aOptimize, aUnificate, T, aInputs, aOutputs, aSpecialItems, aChances, aFluidInputs, aFluidOutputs, aDuration, aEUt, aSpecialValue);
	}
	
	public Recipe(boolean aOptimize, boolean aUnificate, boolean aCanBeBuffered, ItemStack[] aInputs, ItemStack[] aOutputs, Object aSpecialItems, long[] aChances, FluidStack[] aFluidInputs, FluidStack[] aFluidOutputs, long aDuration, long aEUt, long aSpecialValue) {
		mCanBeBuffered = aCanBeBuffered;
		if (aInputs       == null) aInputs       = ZL_IS;
		if (aOutputs      == null) aOutputs      = ZL_IS;
		if (aFluidInputs  == null) aFluidInputs  = ZL_FS;
		if (aFluidOutputs == null) aFluidOutputs = ZL_FS;
		if (aChances      == null) aChances      = aOutputs.length<0?ZL_LONG:new long[aOutputs.length];
		if (aChances.length < aOutputs.length) aChances = Arrays.copyOf(aChances, aOutputs.length);
		aInputs  = UT.Code.getWithoutTrailingNulls(aInputs ).toArray(ZL_IS);
		aOutputs = UT.Code.getWithoutTrailingNulls(aOutputs).toArray(ZL_IS);
		if (aUnificate) {
			OreDictManager.INSTANCE.setStackArray_(T, aInputs );
			OreDictManager.INSTANCE.setStackArray_(T, aOutputs);
		}
		for (int i = 0; i < aFluidOutputs.length; i++) if (aFluidOutputs[i] != NF && (aFluidOutputs[i].amount <= 0 || FL.Error.is(aFluidOutputs[i]))) aFluidOutputs[i] = NF;
		aFluidInputs  = UT.Code.getWithoutNulls(aFluidInputs ).toArray(ZL_FS);
		aFluidOutputs = UT.Code.getWithoutNulls(aFluidOutputs).toArray(ZL_FS);
		
		int l = UT.Code.bindInt(aDuration / 16);
		
		for (int i = 0; i < aChances     .length; i++) if (aChances[i] <=  0) aChances[i] = 10000;
		for (int i = 0; i < aInputs      .length; i++) if (aInputs [i] != null) {aInputs [i] = ST.copy_     (aInputs [i]); if (aInputs [i].stackSize != 0) l = Math.min(aInputs [i].stackSize, l);}
		for (int i = 0; i < aOutputs     .length; i++) if (aOutputs[i] != null) {aOutputs[i] = ST.validMeta_(aOutputs[i]); if (aOutputs[i].stackSize != 0) l = Math.min(aOutputs[i].stackSize, l);}
		for (int i = 0; i < aFluidInputs .length; i++) {aFluidInputs [i] = aFluidInputs [i].copy(); if (aFluidInputs [i].amount != 0) l = Math.min(aFluidInputs [i].amount, l);}
		for (int i = 0; i < aFluidOutputs.length; i++) {aFluidOutputs[i] = aFluidOutputs[i].copy(); if (aFluidOutputs[i].amount != 0) l = Math.min(aFluidOutputs[i].amount, l);}
		
		if (aOptimize) {
			for (int i = 0; i < aInputs.length; i++) if (aInputs[i] != NI && ST.meta_(aInputs[i]) != W) for (int j = 0; j < aOutputs.length; j++) {
				if (aOutputs[j] != null && ST.equal_(aInputs[i], aOutputs[j], F)) {
					if (aInputs[i].stackSize >= aOutputs[j].stackSize) {
						aInputs[i].stackSize -= aOutputs[j].stackSize;
						l = Math.min(aInputs [i].stackSize, l);
						aOutputs[j] = NI;
					} else {
						aOutputs[j].stackSize -= aInputs[i].stackSize;
						l = Math.min(aOutputs[i].stackSize, l);
					}
				}
			}
			
			for (; l > 1; l--) {
				boolean temp = T;
				for (int j = 0; temp && j < aInputs      .length; j++) if (aInputs [j] != null && aInputs [j].stackSize % l != 0) temp = F;
				for (int j = 0; temp && j < aOutputs     .length; j++) if (aOutputs[j] != null && aOutputs[j].stackSize % l != 0) temp = F;
				for (int j = 0; temp && j < aFluidInputs .length; j++) if (aFluidInputs [j].amount % l != 0) temp = F;
				for (int j = 0; temp && j < aFluidOutputs.length; j++) if (aFluidOutputs[j].amount % l != 0) temp = F;
				if (temp) {
					for (int j = 0; j < aInputs      .length; j++) if (aInputs [j] != null) aInputs [j].stackSize /= l;
					for (int j = 0; j < aOutputs     .length; j++) if (aOutputs[j] != null) aOutputs[j].stackSize /= l;
					for (int j = 0; j < aFluidInputs .length; j++) aFluidInputs [j].amount /= l;
					for (int j = 0; j < aFluidOutputs.length; j++) aFluidOutputs[j].amount /= l;
					aDuration /= l;
					break;
				}
			}
		}
		
		for (int i = 0; i < aInputs .length; i++) if (aInputs [i] != NI && aInputs [i].stackSize > 64) aInputs [i].stackSize = 64;
		for (int i = 0; i < aOutputs.length; i++) if (aOutputs[i] != NI && aOutputs[i].stackSize > 64) aOutputs[i].stackSize = 64;
		
		mInputs = aInputs;
		mOutputs = aOutputs;
		mSpecialItems = aSpecialItems;
		mChances = aChances;
		Arrays.fill(mMaxChances = new long[aChances.length], 10000);
		mFluidInputs = aFluidInputs;
		mFluidOutputs = aFluidOutputs;
		mDuration = aDuration;
		mSpecialValue = aSpecialValue;
		mEUt = aEUt;
	}
}
