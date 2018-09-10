/**
 * Copyright (c) 2018 Gregorius Techneticies
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

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.event.FMLInterModComms;
import gregapi.config.ConfigCategories;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class GT_ModHandler {
	/**
	 * Adds a Recipe to the Sawmills of GregTech and ThermalCraft
	 */
	public static boolean addSawmillRecipe(ItemStack aInput, ItemStack aOutput1, ItemStack aOutput2) {
		if (ST.invalid(aInput) || ST.invalid(aOutput1)) return F;
		aOutput1 = OM.get_(aOutput1);
		aOutput2 = OM.get(aOutput2);
		if (!ConfigsGT.RECIPES.get(ConfigCategories.Machines.sawmill, aInput, T)) return F;
	    try {
	    	ThermalExpansion.addSawmillRecipe(1600, aInput, aOutput1, aOutput2, 100);
		} catch(Throwable e) {/*Do nothing*/}
		return T;
	}
	
	/**
	 * Induction Smelter Recipes and Alloy Smelter Recipes
	 */
	public static boolean addAlloySmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, int aDuration, int aEUt, boolean aAllowSecondaryInputEmpty) {
		if (aInput1 == null || (aInput2 == null && !aAllowSecondaryInputEmpty) || ST.invalid(aOutput1)) return F;
		aOutput1 = OM.get_(aOutput1);
		boolean temp = F;
		if (addInductionSmelterRecipe(aInput1, aInput2, aOutput1, null, aDuration * aEUt * 2, 0)) temp = T;
		return temp;
	}
	
	/**
	 * Induction Smelter Recipes for TE
	 */
	public static boolean addInductionSmelterRecipe(ItemStack aInput1, ItemStack aInput2, ItemStack aOutput1, ItemStack aOutput2, int aEnergy, int aChance) {
		if (ST.invalid(aInput1 == null) || ST.invalid(aOutput1)) return F;
		aOutput1 = OM.get_(aOutput1);
		aOutput2 = OM.get(aOutput2);
		if (ST.container(aInput1, F) != null || !ConfigsGT.RECIPES.get(ConfigCategories.Machines.inductionsmelter, aInput2==null?aInput1:aOutput1, T)) return F;
	    try {
	    	ThermalExpansion.addSmelterRecipe(aEnergy*10, ST.copy(aInput1), aInput2==null?ST.make(Blocks.sand, 1, 0):aInput2, aOutput1, aOutput2, aChance);
		} catch(Throwable e) {/*Do nothing*/}
		return T;
	}
	
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
	
	/**
	 * Copy of the original Helper Class of Thermal Expansion, just to make sure it works even when other Mods include TE-APIs
	 */
	public static class ThermalExpansion {
		public static void addFurnaceRecipe(int energy, ItemStack input, ItemStack output) {
		    NBTTagCompound toSend = UT.NBT.make();
		    toSend.setInteger("energy", energy);
		    toSend.setTag("input", UT.NBT.make());
		    toSend.setTag("output", UT.NBT.make());
		    input.writeToNBT(toSend.getCompoundTag("input"));
		    output.writeToNBT(toSend.getCompoundTag("output"));
		    FMLInterModComms.sendMessage("ThermalExpansion", "FurnaceRecipe", toSend);
	    }
		
	    public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput) {
	        addPulverizerRecipe(energy, input, primaryOutput, null, 0);
	    }
	    
	    public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput) {
	    	addPulverizerRecipe(energy, input, primaryOutput, secondaryOutput, 100);
	    }
	    
	    public static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {
	    	if (input == null || primaryOutput == null) return;
	    	NBTTagCompound toSend = UT.NBT.make();
	        toSend.setInteger("energy", energy);
	        toSend.setTag("input", UT.NBT.make());
	        toSend.setTag("primaryOutput", UT.NBT.make());
	        toSend.setTag("secondaryOutput", UT.NBT.make());
	        input.writeToNBT(toSend.getCompoundTag("input"));
	        primaryOutput.writeToNBT(toSend.getCompoundTag("primaryOutput"));
	        if (secondaryOutput != null) secondaryOutput.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
	        toSend.setInteger("secondaryChance", secondaryChance);
	        FMLInterModComms.sendMessage("ThermalExpansion", "PulverizerRecipe", toSend);
	    }
	    
	    public static void addSawmillRecipe(int energy, ItemStack input, ItemStack primaryOutput) {
	        addSawmillRecipe(energy, input, primaryOutput, null, 0);
	    }
	    
	    public static void addSawmillRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput) {
	        addSawmillRecipe(energy, input, primaryOutput, secondaryOutput, 100);
	    }
	    
	    public static void addSawmillRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {
	    	if (input == null || primaryOutput == null) return;
	    	NBTTagCompound toSend = UT.NBT.make();
	        toSend.setInteger("energy", energy);
	        toSend.setTag("input", UT.NBT.make());
	        toSend.setTag("primaryOutput", UT.NBT.make());
	        toSend.setTag("secondaryOutput", UT.NBT.make());
	        input.writeToNBT(toSend.getCompoundTag("input"));
	        primaryOutput.writeToNBT(toSend.getCompoundTag("primaryOutput"));
	        if (secondaryOutput != null) secondaryOutput.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
	        toSend.setInteger("secondaryChance", secondaryChance);
	        FMLInterModComms.sendMessage("ThermalExpansion", "SawmillRecipe", toSend);
	    }
	    
	    public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput) {
	        addSmelterRecipe(energy, primaryInput, secondaryInput, primaryOutput, null, 0);
	    }
	    
	    public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput) {
	        addSmelterRecipe(energy, primaryInput, secondaryInput, primaryOutput, secondaryOutput, 100);
	    }
	    
	    public static void addSmelterRecipe(int energy, ItemStack primaryInput, ItemStack secondaryInput, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance) {
	    	if (primaryInput == null || secondaryInput == null || primaryOutput == null) return;
	    	NBTTagCompound toSend = UT.NBT.make();
	        toSend.setInteger("energy", energy);
	        toSend.setTag("primaryInput", UT.NBT.make());
	        toSend.setTag("secondaryInput", UT.NBT.make());
	        toSend.setTag("primaryOutput", UT.NBT.make());
	        toSend.setTag("secondaryOutput", UT.NBT.make());
	        primaryInput.writeToNBT(toSend.getCompoundTag("primaryInput"));
	        secondaryInput.writeToNBT(toSend.getCompoundTag("secondaryInput"));
	        primaryOutput.writeToNBT(toSend.getCompoundTag("primaryOutput"));
	        if (secondaryOutput != null) secondaryOutput.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
	        toSend.setInteger("secondaryChance", secondaryChance);
	        FMLInterModComms.sendMessage("ThermalExpansion", "SmelterRecipe", toSend);
	    }
	    
	    public static void addSmelterBlastOre(OreDictMaterial aMaterial) {
	        NBTTagCompound toSend = UT.NBT.make();
	        toSend.setString("oreType", aMaterial.toString());
	        FMLInterModComms.sendMessage("ThermalExpansion", "SmelterBlastOreType", toSend);
	    }
	    
	    public static void addCrucibleRecipe(int energy, ItemStack input, FluidStack output) {
	    	if (input == null || output == null) return;
	    	NBTTagCompound toSend = UT.NBT.make();
	        toSend.setInteger("energy", energy);
	        toSend.setTag("input", UT.NBT.make());
	        toSend.setTag("output", UT.NBT.make());
	        input.writeToNBT(toSend.getCompoundTag("input"));
	        output.writeToNBT(toSend.getCompoundTag("output"));
	        FMLInterModComms.sendMessage("ThermalExpansion", "CrucibleRecipe", toSend);
	    }
	    
	    public static void addTransposerFill(int energy, ItemStack input, ItemStack output, FluidStack fluid, boolean reversible) {
	    	if (input == null || output == null || fluid == null) return;
	    	NBTTagCompound toSend = UT.NBT.make();
	        toSend.setInteger("energy", energy);
	        toSend.setTag("input", UT.NBT.make());
	        toSend.setTag("output", UT.NBT.make());
	        toSend.setTag("fluid", UT.NBT.make());
	        input.writeToNBT(toSend.getCompoundTag("input"));
	        output.writeToNBT(toSend.getCompoundTag("output"));
	        UT.NBT.setBoolean(toSend, "reversible", reversible);
	        fluid.writeToNBT(toSend.getCompoundTag("fluid"));
	        FMLInterModComms.sendMessage("ThermalExpansion", "TransposerFillRecipe", toSend);
	    }
	    
	    public static void addTransposerExtract(int energy, ItemStack input, ItemStack output, FluidStack fluid, int chance, boolean reversible) {
	    	if (input == null || output == null || fluid == null) return;
	    	NBTTagCompound toSend = UT.NBT.make();
	        toSend.setInteger("energy", energy);
	        toSend.setTag("input", UT.NBT.make());
	        toSend.setTag("output", UT.NBT.make());
	        toSend.setTag("fluid", UT.NBT.make());
	        input.writeToNBT(toSend.getCompoundTag("input"));
	        output.writeToNBT(toSend.getCompoundTag("output"));
	        UT.NBT.setBoolean(toSend, "reversible", reversible);
	        toSend.setInteger("chance", chance);
	        fluid.writeToNBT(toSend.getCompoundTag("fluid"));
	        FMLInterModComms.sendMessage("ThermalExpansion", "TransposerExtractRecipe", toSend);
	    }
	    
	    public static void addMagmaticFuel(String fluidName, int energy) {
	        NBTTagCompound toSend = UT.NBT.make();
	        toSend.setString("fluidName", fluidName);
	        toSend.setInteger("energy", energy);
	        FMLInterModComms.sendMessage("ThermalExpansion", "MagmaticFuel", toSend);
	    }
	    
	    public static void addCompressionFuel(String fluidName, int energy) {
	        NBTTagCompound toSend = UT.NBT.make();
	        toSend.setString("fluidName", fluidName);
	        toSend.setInteger("energy", energy);
	        FMLInterModComms.sendMessage("ThermalExpansion", "CompressionFuel", toSend);
	    }
	    
	    public static void addCoolant(String fluidName, int energy) {
	        NBTTagCompound toSend = UT.NBT.make();
	        toSend.setString("fluidName", fluidName);
	        toSend.setInteger("energy", energy);
	        FMLInterModComms.sendMessage("ThermalExpansion", "Coolant", toSend);
	    }
	}
	
	@Deprecated public static class RecipeBits {public static long MIRRORED = B[0], BUFFERED = B[1], KEEPNBT = B[2], DISMANTLEABLE = B[3], NOT_REMOVABLE = B[4], REVERSIBLE = B[5], DELETE_ALL_OTHER_RECIPES = B[6], DELETE_ALL_OTHER_RECIPES_IF_SAME_NBT = B[7], DELETE_ALL_OTHER_SHAPED_RECIPES = B[8], DELETE_ALL_OTHER_NATIVE_RECIPES = B[9], DO_NOT_CHECK_FOR_COLLISIONS = B[10], ONLY_ADD_IF_THERE_IS_ANOTHER_RECIPE_FOR_IT = B[11], ONLY_ADD_IF_RESULT_IS_NOT_NULL = B[12], DELETE_ONLY_IF_NO_DYE_IS_INVOLVED = B[13], NOT_AUTOCRAFTABLE = B[14], DEFAULT = BUFFERED|NOT_REMOVABLE, DEFAULT_REV = DEFAULT|REVERSIBLE, DEFAULT_NCC = DEFAULT|DO_NOT_CHECK_FOR_COLLISIONS, DEFAULT_REV_NCC = DEFAULT_REV|DO_NOT_CHECK_FOR_COLLISIONS, DEFAULT_NAC = DEFAULT|NOT_AUTOCRAFTABLE, DEFAULT_NAC_NCC = DEFAULT_NCC|NOT_AUTOCRAFTABLE, DEFAULT_NAC_REV = DEFAULT_REV|NOT_AUTOCRAFTABLE, DEFAULT_NAC_REV_NCC = DEFAULT_REV_NCC|NOT_AUTOCRAFTABLE;}
	@Deprecated public static boolean addCraftingRecipe(ItemStack aResult, Enchantment[] aEnchantmentsAdded, int[] aEnchantmentLevelsAdded, Object[] aRecipe) {return CR.shaped(aResult, aEnchantmentsAdded, aEnchantmentLevelsAdded, aRecipe);}
	@Deprecated public static boolean addCraftingRecipe(ItemStack aResult, Object[] aRecipe) {return CR.shaped(aResult, CR.DEF, aRecipe);}
	@Deprecated public static boolean addCraftingRecipe(ItemStack aResult, long aBitMask, Object[] aRecipe) {return CR.shaped(aResult, aBitMask, aRecipe);}
	@Deprecated public static boolean addShapelessEnchantingRecipe(ItemStack aResult, Enchantment[] aEnchantmentsAdded, int[] aEnchantmentLevelsAdded, Object[] aRecipe) {return CR.shapeless(aResult, aEnchantmentsAdded, aEnchantmentLevelsAdded, aRecipe);}
	@Deprecated public static boolean addShapelessCraftingRecipe(ItemStack aResult, Object[] aRecipe) {return CR.shapeless(aResult, CR.DEF, aRecipe);}
	@Deprecated public static boolean addShapelessCraftingRecipe(ItemStack aResult, long aBitMask, Object[] aRecipe) {return CR.shapeless(aResult, aBitMask, aRecipe);}
	@Deprecated public static ItemStack removeRecipe(ItemStack... aRecipe) {return CR.remove(aRecipe);}
	@Deprecated public static boolean removeRecipeByOutput(ItemStack aOutput) {return CR.remout(aOutput, T, F, F, F);}
	@Deprecated public static boolean removeRecipeByOutput(ItemStack aOutput, boolean aIgnoreNBT, boolean aNotRemoveShapelessRecipes, boolean aOnlyRemoveNativeHandlers, boolean aDontRemoveDyeingRecipes) {return CR.remout(aOutput, aIgnoreNBT, aNotRemoveShapelessRecipes, aOnlyRemoveNativeHandlers, aDontRemoveDyeingRecipes);}
    @Deprecated public static ItemStack getAllRecipeOutput(World aWorld, ItemStack... aRecipe) {return CR.getany(aWorld, aRecipe);}
    @Deprecated public static ItemStack getRecipeOutput(ItemStack... aRecipe) {return CR.get(aRecipe);}
    @Deprecated public static ItemStack getRecipeOutput(boolean aUncopiedStack, ItemStack... aRecipe) {return CR.get(aUncopiedStack, aRecipe);}
    @Deprecated public static ArrayList<ItemStack> getRecipeOutputs(ItemStack... aRecipe) {return CR.outputs(aRecipe);}
    @Deprecated public static ArrayList<ItemStack> getRecipeOutputs(List<IRecipe> aList, boolean aDeleteFromList, ItemStack... aRecipe) {return CR.outputs(aList, aDeleteFromList, aRecipe);}
	@Deprecated public static boolean addSmeltingRecipe(ItemStack aInput, ItemStack aOutput) {return RM.add_smelting(aInput, aOutput, 0);}
}
