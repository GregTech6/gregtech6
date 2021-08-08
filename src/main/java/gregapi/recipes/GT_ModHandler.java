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

import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

@Deprecated
public class GT_ModHandler {
	// Those few are still used by IHL so I cant delete this Class...
	@Deprecated public static boolean addCraftingRecipe(ItemStack aResult, Object[] aRecipe) {return CR.shaped(aResult, CR.DEF, aRecipe);}
	@Deprecated public static boolean addCraftingRecipe(ItemStack aResult, long aBitMask, Object[] aRecipe) {return CR.shaped(aResult, aBitMask, aRecipe);}
	@Deprecated public static boolean addShapelessCraftingRecipe(ItemStack aResult, Object[] aRecipe) {return CR.shapeless(aResult, CR.DEF, aRecipe);}
	@Deprecated public static boolean addShapelessCraftingRecipe(ItemStack aResult, long aBitMask, Object[] aRecipe) {return CR.shapeless(aResult, aBitMask, aRecipe);}
	@Deprecated public static boolean addSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {return RM.add_smelting(aInput, aOutput, 0, F, F, F);}
	@Deprecated public static class RecipeBits {public static long MIRRORED = B[0], BUFFERED = B[1], KEEPNBT = B[2], DISMANTLEABLE = B[3], NOT_REMOVABLE = B[4], REVERSIBLE = B[5], DELETE_ALL_OTHER_RECIPES = B[6], DELETE_ALL_OTHER_RECIPES_IF_SAME_NBT = B[7], DELETE_ALL_OTHER_SHAPED_RECIPES = B[8], DELETE_ALL_OTHER_NATIVE_RECIPES = B[9], DO_NOT_CHECK_FOR_COLLISIONS = B[10], ONLY_ADD_IF_THERE_IS_ANOTHER_RECIPE_FOR_IT = B[11], ONLY_ADD_IF_RESULT_IS_NOT_NULL = B[12], DELETE_ONLY_IF_NO_DYE_IS_INVOLVED = B[13], NOT_AUTOCRAFTABLE = B[14], DEFAULT = BUFFERED|NOT_REMOVABLE, DEFAULT_REV = DEFAULT|REVERSIBLE, DEFAULT_NCC = DEFAULT|DO_NOT_CHECK_FOR_COLLISIONS, DEFAULT_REV_NCC = DEFAULT_REV|DO_NOT_CHECK_FOR_COLLISIONS, DEFAULT_NAC = DEFAULT|NOT_AUTOCRAFTABLE, DEFAULT_NAC_NCC = DEFAULT_NCC|NOT_AUTOCRAFTABLE, DEFAULT_NAC_REV = DEFAULT_REV|NOT_AUTOCRAFTABLE, DEFAULT_NAC_REV_NCC = DEFAULT_REV_NCC|NOT_AUTOCRAFTABLE;}
	
	// Presumably nobody uses those anymore, I hope...
	@Deprecated public static int chargeElectricItem(ItemStack aStack, int aCharge, int aTier, boolean aIgnoreLimit, boolean aSimulate) {return COMPAT_EU_ITEM != null && ST.valid(aStack) && COMPAT_EU_ITEM.is(aStack) ? UT.Code.bindInt(COMPAT_EU_ITEM.charge(aStack, aCharge, !aSimulate)) : 0;}
	@Deprecated public static int dischargeElectricItem(ItemStack aStack, int aCharge, int aTier, boolean aIgnoreLimit, boolean aSimulate, boolean aIgnoreDischargability) {return COMPAT_EU_ITEM != null && ST.valid(aStack) && COMPAT_EU_ITEM.is(aStack) ? UT.Code.bindInt(COMPAT_EU_ITEM.decharge(aStack, aCharge, !aSimulate)) : 0;}
	@Deprecated public static boolean canUseElectricItem(ItemStack aStack, int aCharge) {return F;}
	@Deprecated public static boolean useElectricItem(ItemStack aStack, int aCharge, EntityPlayer aPlayer) {return COMPAT_EU_ITEM != null && ST.valid(aStack) && COMPAT_EU_ITEM.is(aStack) && COMPAT_EU_ITEM.decharge(aStack, aCharge, T) > 0;}
	@Deprecated public static boolean isChargerItem(ItemStack aStack) {return COMPAT_EU_ITEM != null && ST.valid(aStack) && COMPAT_EU_ITEM.is(aStack) && COMPAT_EU_ITEM.provider(aStack);}
	@Deprecated public static boolean isElectricItem(ItemStack aStack) {return COMPAT_EU_ITEM != null && ST.valid(aStack) && COMPAT_EU_ITEM.is(aStack);}
	@Deprecated public static boolean isElectricItem(ItemStack aStack, byte aTier) {return COMPAT_EU_ITEM != null && ST.valid(aStack) && COMPAT_EU_ITEM.is(aStack) && COMPAT_EU_ITEM.tier(aStack) == aTier;}
	@Deprecated public static boolean isReactorItem(ItemStack aStack) {return COMPAT_IC2 != null && COMPAT_IC2.isReactorItem(aStack);}
	@Deprecated public static boolean damageOrDechargeItem(ItemStack aStack, int aDamage, int aDecharge, EntityLivingBase aPlayer) {return F;}
	@Deprecated public static boolean useSolderingIron(ItemStack aStack, EntityLivingBase aPlayer) {return F;}
	@Deprecated public static boolean addSawmillRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2) {return F;}
	@Deprecated public static boolean addAlloySmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt, boolean aAllowSecondaryInputEmpty) {return F;}
	@Deprecated public static boolean addInductionSmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aEnergy, int aChance) {return F;}
	
	@Deprecated
	public static class ThermalExpansion {
	  @Deprecated public static void addFurnaceRecipe(int energy, ItemStack input, ItemStack output) {/**/}
	  @Deprecated public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput) {/**/}
	  @Deprecated public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput) {/**/}
	  @Deprecated public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {/**/}
	  @Deprecated public static void addSawmillRecipe(int energy, ItemStack input, ItemStack primaryOutput) {/**/}
	  @Deprecated public static void addSawmillRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput) {/**/}
	  @Deprecated public static void addSawmillRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {/**/}
	  @Deprecated public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput) {/**/}
	  @Deprecated public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput) {/**/}
	  @Deprecated public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {/**/}
	  @Deprecated public static void addSmelterBlastOre(OreDictMaterial aMaterial) {/**/}
	  @Deprecated public static void addCrucibleRecipe(int energy, ItemStack input, FluidStack output) {/**/}
	  @Deprecated public static void addTransposerFill(int energy, ItemStack input, ItemStack output, FluidStack fluid, boolean reversible) {/**/}
	  @Deprecated public static void addTransposerExtract(int energy, ItemStack input, ItemStack output, FluidStack fluid, int chance, boolean reversible) {/**/}
	  @Deprecated public static void addMagmaticFuel(String fluidName, int energy) {/**/}
	  @Deprecated public static void addCompressionFuel(String fluidName, int energy) {/**/}
	  @Deprecated public static void addCoolant(String fluidName, int energy) {/**/}
	}
	
	@Deprecated public static boolean addCraftingRecipe(ItemStack aResult, Enchantment[] aEnchantmentsAdded, int[] aEnchantmentLevelsAdded, Object[] aRecipe) {return CR.shaped(aResult, aEnchantmentsAdded, aEnchantmentLevelsAdded, aRecipe);}
	@Deprecated public static boolean addShapelessEnchantingRecipe(ItemStack aResult, Enchantment[] aEnchantmentsAdded, int[] aEnchantmentLevelsAdded, Object[] aRecipe) {return CR.shapeless(aResult, aEnchantmentsAdded, aEnchantmentLevelsAdded, aRecipe);}
	@Deprecated public static ItemStack removeRecipe(ItemStack... aRecipe) {return CR.remove(aRecipe);}
	@Deprecated public static boolean removeRecipeByOutput(ItemStack aOutput) {return CR.remout(aOutput, T, F, F, F);}
	@Deprecated public static boolean removeRecipeByOutput(ItemStack aOutput, boolean aIgnoreNBT, boolean aNotRemoveShapelessRecipes, boolean aOnlyRemoveNativeHandlers, boolean aDontRemoveDyeingRecipes) {return CR.remout(aOutput, aIgnoreNBT, aNotRemoveShapelessRecipes, aOnlyRemoveNativeHandlers, aDontRemoveDyeingRecipes);}
	@Deprecated public static ItemStack getAllRecipeOutput(World aWorld, ItemStack... aRecipe) {return CR.getany(aWorld, aRecipe);}
	@Deprecated public static ItemStack getRecipeOutput(ItemStack... aRecipe) {return CR.get(aRecipe);}
	@Deprecated public static ItemStack getRecipeOutput(boolean aUncopiedStack, ItemStack... aRecipe) {return CR.get(aUncopiedStack, aRecipe);}
}
