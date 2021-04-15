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

package gregapi.util;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.IItemContainer;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackSet;
import gregapi.code.ModData;
import gregapi.data.CS;
import gregapi.data.CS.OreDictToolNames;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.recipes.AdvancedCraftingShaped;
import gregapi.recipes.AdvancedCraftingShapeless;
import gregapi.recipes.AdvancedCraftingTool;
import gregapi.recipes.ICraftingRecipeGT;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * @author Gregorius Techneticies
 */
public class CR {
	public static final HashSetNoNulls<String>
	CLASSES_NATIVE = new HashSetNoNulls<>(F
	, ShapedRecipes.class.getName()
	, ShapedOreRecipe.class.getName()
	, AdvancedCraftingShaped.class.getName()
	, ShapelessRecipes.class.getName()
	, ShapelessOreRecipe.class.getName()
	, AdvancedCraftingShapeless.class.getName()
	, "ic2.core.AdvRecipe"
	, "ic2.core.AdvShapelessRecipe"
	, "appeng.recipes.game.ShapedRecipe"
	, "appeng.recipes.game.ShapelessRecipe"
	, "forestry.core.utils.ShapedRecipeCustom"
	, "mekanism.common.recipe.ShapedMekanismRecipe"
	, "mekanism.common.recipe.ShapelessMekanismRecipe"
	),
	CLASSES_SPECIAL = new HashSetNoNulls<>(F
	, AdvancedCraftingTool.class.getName()
	, net.minecraft.item.crafting.RecipeFireworks.class.getName()
	, net.minecraft.item.crafting.RecipesArmorDyes.class.getName()
	, net.minecraft.item.crafting.RecipeBookCloning.class.getName()
	, net.minecraft.item.crafting.RecipesMapCloning.class.getName()
	, net.minecraft.item.crafting.RecipesMapExtending.class.getName()
	, "jds.bibliocraft.BiblioSpecialRecipes"
	, "dan200.qcraft.shared.EntangledQBlockRecipe"
	, "dan200.qcraft.shared.EntangledQuantumComputerRecipe"
	, "dan200.qcraft.shared.QBlockRecipe"
	, "appeng.recipes.game.FacadeRecipe"
	, "appeng.recipes.game.DisassembleRecipe"
	, "mods.railcraft.common.carts.LocomotivePaintingRecipe"
	, "mods.railcraft.common.util.crafting.RotorRepairRecipe"
	, "mods.railcraft.common.util.crafting.RoutingTableCopyRecipe"
	, "mods.railcraft.common.util.crafting.RoutingTicketCopyRecipe"
	, "mods.railcraft.common.util.crafting.TankCartFilterRecipe"
	, "mods.railcraft.common.emblems.LocomotiveEmblemRecipe"
	, "mods.railcraft.common.emblems.EmblemPostColorRecipe"
	, "mods.railcraft.common.emblems.EmblemPostEmblemRecipe"
	, "mods.immibis.redlogic.interaction.RecipeDyeLumarButton"
	, "thaumcraft.common.items.armor.RecipesRobeArmorDyes"
	, "thaumcraft.common.items.armor.RecipesVoidRobeArmorDyes"
	, "thaumcraft.common.lib.crafting.ShapelessNBTOreRecipe"
	, "twilightforest.item.TFMapCloningRecipe"
	, "forestry.lepidopterology.MatingRecipe"
	, "micdoodle8.mods.galacticraft.planets.asteroids.recipe.CanisterRecipes"
	, "shedar.mods.ic2.nuclearcontrol.StorageArrayRecipe"
	);
	
	private static final ArrayListNoNulls<List<ItemStack>> OREDICT_DYE_LISTS = new ArrayListNoNulls<>();
	static {for (String tDye : DYE_OREDICTS) OREDICT_DYE_LISTS.add(OreDictionary.getOres(tDye));}
	
	public static final ItemStackSet<ItemStackContainer> RECIPES_TO_DELATE = new ItemStackSet<>();
	
	public static boolean BUFFERING = T;
	public static final List<IRecipe> BUFFER = new ArrayListNoNulls<>(1000);
	
	public static void stopBuffering() {
		BUFFERING = F;
		List<IRecipe> tList = list();
		for (int i = 0; i < tList.size(); i++) {
			IRecipe tRecipe = tList.get(i);
			if (tRecipe != null) {
				if (tRecipe instanceof ICraftingRecipeGT && !((ICraftingRecipeGT)tRecipe).isRemovableByGT()) continue;
				if (CLASSES_SPECIAL.contains(tRecipe.getClass().getName())) continue;
				if (RECIPES_TO_DELATE.contains(tRecipe.getRecipeOutput(), T)) tList.set(i, null);
			}
		}
		tList.removeAll(Arrays.asList((IRecipe)null));
		// This setting is not meant to speed up load times, its meant to just make Crafting Recipes less visible in NEI
		if (!DISABLE_GT6_CRAFTING_RECIPES) for (IRecipe tRecipe : BUFFER) GameRegistry.addRecipe(tRecipe);
		BUFFER.clear();
	}
	
	public static final long NONE = 0;
	/** Mirrors the Recipe */
	public static final long MIR = B[0];
	/** Buffers the Recipe for later addition. This makes things more efficient. */
	public static final long BUF = B[1];
	/** Reverses the Output of the Recipe for smelting and pulverising. */
	public static final long REV = B[5];
	/** This is a special Tag I used for crafting Coins up and down. */
	public static final long KEEPNBT = B[2];
	/** Makes the Recipe Reverse Craftable in the Disassembler. */
	public static final long DISMANTLE = B[3];
	/** Prevents the Recipe from accidentally getting removed by my own Handlers. */
	public static final long NO_REM = B[4];
	/** Prevents the Recipe from being AutoCrafted by GT. */
	public static final long NO_AUTO = B[14];
	/** Disables the check for colliding Recipes. */
	public static final long NO_COLLISION_CHECK = B[10];
	/** Removes all Recipes with the same Output Item regardless of NBT, unless another Recipe Deletion Bit is added too. */
	public static final long DEL_OTHER_RECIPES = B[6];
	/** Removes all Recipes with the same Output Item limited to the same NBT. */
	public static final long DEL_OTHER_RECIPES_IF_SAME_NBT = B[7];
	/** Removes all Recipes with the same Output Item limited to Shaped Recipes. */
	public static final long DEL_OTHER_SHAPED_RECIPES = B[8];
	/** Removes all Recipes with the same Output Item limited to native Recipe Handlers. */
	public static final long DEL_OTHER_NATIVE_RECIPES = B[9];
	/** Removes only if no OreDict Dye List is used. */
	public static final long DEL_IF_NO_DYES = B[13];
	/** Only adds the Recipe if there is another Recipe having that Output */
	public static final long ONLY_IF_HAS_OTHER_RECIPES = B[11];
	/** Only adds the Recipe if it has an Output */
	public static final long ONLY_IF_HAS_RESULT = B[12];
	
	public static final long DEF = BUF|NO_REM;
	public static final long DEF_MIR = DEF|MIR;
	public static final long DEF_REV = DEF|REV;
	public static final long DEF_REV_MIR = DEF_REV|MIR;
	public static final long DEF_NCC = DEF|NO_COLLISION_CHECK;
	public static final long DEF_NCC_MIR = DEF_NCC|MIR;
	public static final long DEF_REV_NCC = DEF_REV|NO_COLLISION_CHECK;
	public static final long DEF_REV_NCC_MIR = DEF_REV_NCC|MIR;
	public static final long DEF_NAC = DEF|NO_AUTO;
	public static final long DEF_NAC_MIR = DEF_NAC|MIR;
	public static final long DEF_NAC_NCC = DEF_NCC|NO_AUTO;
	public static final long DEF_NAC_NCC_MIR = DEF_NAC_NCC|MIR;
	public static final long DEF_NAC_REV = DEF_REV|NO_AUTO;
	public static final long DEF_NAC_REV_MIR = DEF_NAC_REV|MIR;
	public static final long DEF_NAC_REV_NCC = DEF_REV_NCC|NO_AUTO;
	public static final long DEF_NAC_REV_NCC_MIR = DEF_NAC_REV_NCC|MIR;
	public static final long DEF_REM = DEF|DEL_OTHER_RECIPES;
	public static final long DEF_REM_REV = DEF_REM|REV;
	public static final long DEF_REM_NCC = DEF_REM|NO_COLLISION_CHECK;
	public static final long DEF_REM_REV_NCC = DEF_REM_REV|NO_COLLISION_CHECK;
	public static final long DEF_REM_REV_NCC_MIR = DEF_REM_REV_NCC|MIR;
	public static final long DEF_REM_NAC = DEF_REM|NO_AUTO;
	public static final long DEF_REM_NAC_NCC = DEF_REM_NCC|NO_AUTO;
	public static final long DEF_REM_NAC_REV = DEF_REM_REV|NO_AUTO;
	public static final long DEF_REM_NAC_REV_MIR = DEF_REM_NAC_REV|MIR;
	public static final long DEF_REM_NAC_REV_NCC = DEF_REM_REV_NCC|NO_AUTO;
	public static final long DEF_REM_NAC_REV_NCC_MIR = DEF_REM_NAC_REV_NCC|MIR;
	
	/**
	 * Regular Crafting Recipes. Deletes conflicting Recipes too.
	 * You can insert instances of IItemContainer into the Recipe Input Array directly without having to call "get(1)" on them.
	 * Enums are automatically getting their "name()"-Method called in order to deliver an OreDict String.
	 * Lowercase Letters are reserved for Tools. They are as follows:
	 * 
	 *   'a' = OreDictToolNames.axe
	 * , 'b' = OreDictToolNames.blade
	 * , 'c' = OreDictToolNames.crowbar
	 * , 'd' = OreDictToolNames.screwdriver
	 * , 'e' = OreDictToolNames.drill
	 * , 'f' = OreDictToolNames.file
	 * , 'h' = OreDictToolNames.hammer
	 * , 'i' = OreDictToolNames.solderingiron
	 * , 'j' = OreDictToolNames.solderingmetal
	 * , 'k' = OreDictToolNames.knife
	 * , 'l' = OreDictToolNames.magnifyingglass
	 * , 'n' = OreDictToolNames.monkeywrench
	 * , 'o' = OreDictToolNames.bendingcylindersmall
	 * , 'p' = OreDictToolNames.drawplate
	 * , 'q' = OreDictToolNames.scissors
	 * , 'r' = OreDictToolNames.softhammer
	 * , 's' = OreDictToolNames.saw
	 * , 'v' = OreDictToolNames.sawaxe
	 * , 'w' = OreDictToolNames.wrench
	 * , 'x' = OreDictToolNames.wirecutter
	 * , 'y' = OreDictToolNames.chisel
	 * , 'z' = OreDictToolNames.bendingcylinder
	 */
	public static boolean shaped(ItemStack aResult, Enchantment[] aEnchantmentsAdded, int[] aEnchantmentLevelsAdded, Object[] aRecipe) {return shaped(aResult, aEnchantmentsAdded, aEnchantmentLevelsAdded, F, T, F, F, F, F, F, F, F, F, F, F, T, F, F, aRecipe);}
	
	/**
	 * Regular Crafting Recipes. Deletes conflicting Recipes too.
	 * You can insert instances of IItemContainer into the Recipe Input Array directly without having to call "get(1)" on them.
	 * Enums are automatically getting their "name()"-Method called in order to deliver an OreDict String.
	 * Lowercase Letters are reserved for Tools. They are as follows:
	 * 
	 *   'a' = OreDictToolNames.axe
	 * , 'b' = OreDictToolNames.blade
	 * , 'c' = OreDictToolNames.crowbar
	 * , 'd' = OreDictToolNames.screwdriver
	 * , 'e' = OreDictToolNames.drill
	 * , 'f' = OreDictToolNames.file
	 * , 'h' = OreDictToolNames.hammer
	 * , 'i' = OreDictToolNames.solderingiron
	 * , 'j' = OreDictToolNames.solderingmetal
	 * , 'k' = OreDictToolNames.knife
	 * , 'l' = OreDictToolNames.magnifyingglass
	 * , 'n' = OreDictToolNames.monkeywrench
	 * , 'o' = OreDictToolNames.bendingcylindersmall
	 * , 'p' = OreDictToolNames.drawplate
	 * , 'q' = OreDictToolNames.scissors
	 * , 'r' = OreDictToolNames.softhammer
	 * , 's' = OreDictToolNames.saw
	 * , 'v' = OreDictToolNames.sawaxe
	 * , 'w' = OreDictToolNames.wrench
	 * , 'x' = OreDictToolNames.wirecutter
	 * , 'y' = OreDictToolNames.chisel
	 * , 'z' = OreDictToolNames.bendingcylinder
	 */
	public static boolean shaped(ItemStack aResult, Object[] aRecipe) {return shaped(aResult, DEF, aRecipe);}
	
	/**
	 * Regular Crafting Recipes. Deletes conflicting Recipes too.
	 * You can insert instances of IItemContainer into the Recipe Input Array directly without having to call "get(1)" on them.
	 * Enums are automatically getting their "name()"-Method called in order to deliver an OreDict String.
	 * Lowercase Letters are reserved for Tools. They are as follows:
	 * 
	 *   'a' = OreDictToolNames.axe
	 * , 'b' = OreDictToolNames.blade
	 * , 'c' = OreDictToolNames.crowbar
	 * , 'd' = OreDictToolNames.screwdriver
	 * , 'e' = OreDictToolNames.drill
	 * , 'f' = OreDictToolNames.file
	 * , 'h' = OreDictToolNames.hammer
	 * , 'i' = OreDictToolNames.solderingiron
	 * , 'j' = OreDictToolNames.solderingmetal
	 * , 'k' = OreDictToolNames.knife
	 * , 'l' = OreDictToolNames.magnifyingglass
	 * , 'n' = OreDictToolNames.monkeywrench
	 * , 'o' = OreDictToolNames.bendingcylindersmall
	 * , 'p' = OreDictToolNames.drawplate
	 * , 'q' = OreDictToolNames.scissors
	 * , 'r' = OreDictToolNames.softhammer
	 * , 's' = OreDictToolNames.saw
	 * , 'v' = OreDictToolNames.sawaxe
	 * , 'w' = OreDictToolNames.wrench
	 * , 'x' = OreDictToolNames.wirecutter
	 * , 'y' = OreDictToolNames.chisel
	 * , 'z' = OreDictToolNames.bendingcylinder
	 */
	public static boolean shaped(ItemStack aResult, long aBitMask, Object[] aRecipe) {return shaped(aResult, new Enchantment[0], new int[0], (aBitMask & MIR) != 0, (aBitMask & BUF) != 0, (aBitMask & KEEPNBT) != 0, (aBitMask & DISMANTLE) != 0, (aBitMask & NO_REM) == 0, (aBitMask & REV) != 0, (aBitMask & DEL_OTHER_RECIPES) != 0, (aBitMask & DEL_OTHER_RECIPES_IF_SAME_NBT) != 0, (aBitMask & DEL_OTHER_SHAPED_RECIPES) != 0, (aBitMask & DEL_OTHER_NATIVE_RECIPES) != 0, (aBitMask & NO_COLLISION_CHECK) == 0, (aBitMask & ONLY_IF_HAS_OTHER_RECIPES) != 0, (aBitMask & ONLY_IF_HAS_RESULT) != 0, (aBitMask & DEL_IF_NO_DYES) != 0, (aBitMask & NO_AUTO) != 0, aRecipe);}
	
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, String aRecipeC, char aC1, Object aO1, char aC2, Object aO2, char aC3, Object aO3, char aC4, Object aO4, char aC5, Object aO5, char aC6, Object aO6, char aC7, Object aO7, char aC8, Object aO8, char aC9, Object aO9) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aRecipeC, aC1, aO1, aC2, aO2, aC3, aO3, aC4, aO4, aC5, aO5, aC6, aO6, aC7, aO7, aC8, aO8, aC9, aO9});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, String aRecipeC, char aC1, Object aO1, char aC2, Object aO2, char aC3, Object aO3, char aC4, Object aO4, char aC5, Object aO5, char aC6, Object aO6, char aC7, Object aO7, char aC8, Object aO8) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aRecipeC, aC1, aO1, aC2, aO2, aC3, aO3, aC4, aO4, aC5, aO5, aC6, aO6, aC7, aO7, aC8, aO8});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, String aRecipeC, char aC1, Object aO1, char aC2, Object aO2, char aC3, Object aO3, char aC4, Object aO4, char aC5, Object aO5, char aC6, Object aO6, char aC7, Object aO7) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aRecipeC, aC1, aO1, aC2, aO2, aC3, aO3, aC4, aO4, aC5, aO5, aC6, aO6, aC7, aO7});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, String aRecipeC, char aC1, Object aO1, char aC2, Object aO2, char aC3, Object aO3, char aC4, Object aO4, char aC5, Object aO5, char aC6, Object aO6) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aRecipeC, aC1, aO1, aC2, aO2, aC3, aO3, aC4, aO4, aC5, aO5, aC6, aO6});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, String aRecipeC, char aC1, Object aO1, char aC2, Object aO2, char aC3, Object aO3, char aC4, Object aO4, char aC5, Object aO5) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aRecipeC, aC1, aO1, aC2, aO2, aC3, aO3, aC4, aO4, aC5, aO5});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, String aRecipeC, char aC1, Object aO1, char aC2, Object aO2, char aC3, Object aO3, char aC4, Object aO4) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aRecipeC, aC1, aO1, aC2, aO2, aC3, aO3, aC4, aO4});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, String aRecipeC, char aC1, Object aO1, char aC2, Object aO2, char aC3, Object aO3) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aRecipeC, aC1, aO1, aC2, aO2, aC3, aO3});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, String aRecipeC, char aC1, Object aO1, char aC2, Object aO2) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aRecipeC, aC1, aO1, aC2, aO2});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, String aRecipeC, char aC1, Object aO1) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aRecipeC, aC1, aO1});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, char aC1, Object aO1, char aC2, Object aO2, char aC3, Object aO3, char aC4, Object aO4, char aC5, Object aO5, char aC6, Object aO6) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aC1, aO1, aC2, aO2, aC3, aO3, aC4, aO4, aC5, aO5, aC6, aO6});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, char aC1, Object aO1, char aC2, Object aO2, char aC3, Object aO3, char aC4, Object aO4, char aC5, Object aO5) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aC1, aO1, aC2, aO2, aC3, aO3, aC4, aO4, aC5, aO5});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, char aC1, Object aO1, char aC2, Object aO2, char aC3, Object aO3, char aC4, Object aO4) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aC1, aO1, aC2, aO2, aC3, aO3, aC4, aO4});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, char aC1, Object aO1, char aC2, Object aO2, char aC3, Object aO3) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aC1, aO1, aC2, aO2, aC3, aO3});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, char aC1, Object aO1, char aC2, Object aO2) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aC1, aO1, aC2, aO2});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, String aRecipeB, char aC1, Object aO1) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aRecipeB, aC1, aO1});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, char aC1, Object aO1, char aC2, Object aO2, char aC3, Object aO3) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aC1, aO1, aC2, aO2, aC3, aO3});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, char aC1, Object aO1, char aC2, Object aO2) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aC1, aO1, aC2, aO2});}
	public static boolean shaped(ItemStack aResult, long aBitMask, String aRecipeA, char aC1, Object aO1) {return shaped(aResult, aBitMask, new Object[] {aRecipeA, aC1, aO1});}
	
	private static boolean shaped(ItemStack aResult, Enchantment[] aEnchantmentsAdded, int[] aEnchantmentLevelsAdded, boolean aMirrored, boolean aBuffered, boolean aKeepNBT, boolean aDismantleable, boolean aRemovable, boolean aReversible, boolean aRemoveAllOthersWithSameOutput, boolean aRemoveAllOthersWithSameOutputIfTheyHaveSameNBT, boolean aRemoveAllOtherShapedsWithSameOutput, boolean aRemoveAllOtherNativeRecipes, boolean aCheckForCollisions, boolean aOnlyAddIfThereIsAnyRecipeOutputtingThis, boolean aOnlyAddIfResultIsNotNull, boolean aDeleteOnlyIfNoDyeInvolved, boolean aNotAutoCraftable, Object[] aRecipe) {
		if (aOnlyAddIfResultIsNotNull && ST.invalid(aResult)) return F;
		if (aRecipe == null || aRecipe.length <= 0) return F;
		
		boolean tThereWasARecipe = F;
		
		aResult = ST.validMeta(OM.get(aResult));
		for (byte i = 0; i < aRecipe.length; i++) {
			if (aRecipe[i] instanceof IItemContainer)
				aRecipe[i] = ((IItemContainer)aRecipe[i]).get(1);
			else if (aRecipe[i] instanceof Enum)
				aRecipe[i] = ((Enum<?>)aRecipe[i]).name();
			else if (aRecipe[i] instanceof Item)
				aRecipe[i] = ST.make((Item)aRecipe[i], 1, W);
			else if (aRecipe[i] instanceof Block)
				aRecipe[i] = ST.make((Block)aRecipe[i], 1, W);
			else if (!(aRecipe[i] == null || aRecipe[i] instanceof ItemStack || aRecipe[i] instanceof OreDictItemData || aRecipe[i] instanceof String || aRecipe[i] instanceof Character))
				aRecipe[i] = aRecipe[i].toString();
		}
		
		try {
			String shape = "";
			int idx = 0;
			if (aRecipe[idx] instanceof Boolean) throw new IllegalArgumentException();
			
			ArrayList<Object> tRecipeList = new ArrayList<>(Arrays.asList(aRecipe));
			
			while (aRecipe[idx] instanceof String) {
				String s = (String)aRecipe[idx++];
				shape += s;
				while (s.length() < 3) s+=" ";
				if (s.length() > 3) throw new IllegalArgumentException();
				
				for (char c : s.toCharArray()) {
					switch(c) {
					case 'a': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.axe                      ); break;
					case 'b': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.blade                    ); break;
					case 'c': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.crowbar                  ); break;
					case 'd': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.screwdriver              ); break;
					case 'e': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.drill                    ); break;
					case 'f': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.file                     ); break;
					case 'h': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.hammer                   ); break;
					case 'i': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.solderingiron            ); break;
					case 'j': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.solderingmetal           ); break;
					case 'k': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.knife                    ); break;
					case 'l': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.magnifyingglass          ); break;
					case 'n': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.monkeywrench             ); break;
					case 'o': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.bendingcylindersmall     ); break;
					case 'p': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.drawplate                ); break;
					case 'q': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.scissors                 ); break;
					case 'r': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.softhammer               ); break;
					case 's': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.saw                      ); break;
					case 'v': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.sawaxe                   ); break;
					case 'w': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.wrench                   ); break;
					case 'x': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.wirecutter               ); break;
					case 'y': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.chisel                   ); break;
					case 'z': tRecipeList.add(c); tRecipeList.add(OreDictToolNames.bendingcylinder          ); break;
					}
				}
			}
			
			aRecipe = tRecipeList.toArray();
			
			HashMap<Character, ItemStack> tItemStackMap = new HashMap<>();
			HashMap<Character, OreDictItemData> tItemDataMap = new HashMap<>();
			tItemStackMap.put(' ', null);
			
			boolean tRemoveRecipe = T;
			
			for (; idx < aRecipe.length; idx += 2) {
				if (aRecipe[idx] == null || aRecipe[idx + 1] == null) {
					ERR.println("WARNING: Missing Item for shaped Recipe: " + (aResult==null?"null":aResult.getDisplayName()));
					for (Object tContent : aRecipe) ERR.println(tContent);
					return F;
				}
				
				Character tChar = (Character)aRecipe[idx];
				Object tInput = aRecipe[idx + 1];
				
				if (tInput instanceof ItemStack) {
					tItemStackMap.put(tChar, ST.copy_((ItemStack)tInput));
					tItemDataMap .put(tChar, OM.data_((ItemStack)tInput));
				} else {
					String tInputString = tInput.toString();
					if (tInput instanceof OreDictItemData) {
						tItemDataMap.put(tChar, (OreDictItemData)tInput);
						if (UT.Code.stringInvalid(tInputString)) {
							throw new IllegalArgumentException();
						}
					} else if (UT.Code.stringValid(tInputString)) {
						tItemDataMap.put(tChar, OreDictManager.INSTANCE.getAutomaticItemData(tInputString));
					} else {
						throw new IllegalArgumentException();
					}
					ItemStack tStack = OreDictManager.INSTANCE.getFirstOre(tInputString, 1);
					if (tStack == null) tRemoveRecipe = F; else tItemStackMap.put(tChar, tStack);
					aRecipe[idx + 1] = tInputString;
				}
			}
			
			if (aReversible && aResult != null) {
				OreDictItemData[] tData = new OreDictItemData[9];
				int x = -1;
				for (char chr : shape.toCharArray()) tData[++x] = tItemDataMap.get(chr);
				if (UT.Code.containsSomething(tData)) OM.data(aResult, new OreDictItemData(tData));
			}
			
			if (aCheckForCollisions && tRemoveRecipe) {
				ItemStack[] tRecipe = new ItemStack[9];
				int x = -1;
				for (char chr : shape.toCharArray()) {
					tRecipe[++x] = tItemStackMap.get(chr);
					if (tRecipe[x] != null && ST.meta_(tRecipe[x]) == W) ST.meta_(tRecipe[x], 0);
				}
				tThereWasARecipe = remove(tRecipe) != null || tThereWasARecipe;
			}
		} catch(Throwable e) {e.printStackTrace(ERR);}
		
		if (aResult == null || aResult.stackSize <= 0) return F;
		
		if (aRemoveAllOthersWithSameOutput || aRemoveAllOthersWithSameOutputIfTheyHaveSameNBT || aRemoveAllOtherShapedsWithSameOutput || aRemoveAllOtherNativeRecipes)
			tThereWasARecipe = remout(aResult, !aRemoveAllOthersWithSameOutputIfTheyHaveSameNBT, aRemoveAllOtherShapedsWithSameOutput, aRemoveAllOtherNativeRecipes, aDeleteOnlyIfNoDyeInvolved) || tThereWasARecipe;
		
		if (aOnlyAddIfThereIsAnyRecipeOutputtingThis && !tThereWasARecipe) {
			List<IRecipe> tList = list();
			for (int i = 0; i < tList.size(); i++) {
				IRecipe tRecipe = tList.get(i);
				if (CLASSES_SPECIAL.contains(tRecipe.getClass().getName())) continue;
				if (ST.equal(OM.get(tRecipe.getRecipeOutput()), aResult, T)) {
					tThereWasARecipe = T;
					break;
				}
			}
		}
		
		aResult = ST.update_(aResult);
		
		if (tThereWasARecipe || !aOnlyAddIfThereIsAnyRecipeOutputtingThis) {
			if (BUFFERING && aBuffered)
				BUFFER.add(new AdvancedCraftingShaped(ST.copy_(aResult), aDismantleable, aRemovable, aKeepNBT, !aNotAutoCraftable, aEnchantmentsAdded, aEnchantmentLevelsAdded, aRecipe).setMirrored(aMirrored));
			else
				GameRegistry.addRecipe(new AdvancedCraftingShaped(ST.copy_(aResult), aDismantleable, aRemovable, aKeepNBT, !aNotAutoCraftable, aEnchantmentsAdded, aEnchantmentLevelsAdded, aRecipe).setMirrored(aMirrored));
		}
		return T;
	}
	
	/** Shapeless Crafting Recipes. Deletes conflicting Recipes too. */
	public static boolean shapeless(ItemStack aResult, Enchantment[] aEnchantmentsAdded, int[] aEnchantmentLevelsAdded, Object[] aRecipe) {return shapeless(aResult, aEnchantmentsAdded, aEnchantmentLevelsAdded, T, F, F, F, F, F, F, F, F, aRecipe);}
	/** Shapeless Crafting Recipes. Deletes conflicting Recipes too. */
	public static boolean shapeless(ItemStack aResult, Object[] aRecipe) {return shapeless(aResult, DEF, aRecipe);}
	/** Shapeless Crafting Recipes. Deletes conflicting Recipes too. */
	public static boolean shapeless(ItemStack aResult, long aBitMask, Object[] aRecipe) {return shapeless(aResult, new Enchantment[0], new int[0], (aBitMask & BUF) != 0, (aBitMask & KEEPNBT) != 0, (aBitMask & DISMANTLE) != 0, (aBitMask & NO_REM) == 0, (aBitMask & NO_AUTO) != 0, (aBitMask & DEL_OTHER_RECIPES) != 0, (aBitMask & DEL_OTHER_RECIPES_IF_SAME_NBT) != 0, (aBitMask & DEL_OTHER_SHAPED_RECIPES) != 0, (aBitMask & DEL_OTHER_NATIVE_RECIPES) != 0, aRecipe);}
	
	private static boolean shapeless(ItemStack aResult, Enchantment[] aEnchantmentsAdded, int[] aEnchantmentLevelsAdded, boolean aBuffered, boolean aKeepNBT, boolean aDismantleable, boolean aRemovable, boolean aNotAutoCraftable, boolean aRemoveAllOthersWithSameOutput, boolean aRemoveAllOthersWithSameOutputIfTheyHaveSameNBT, boolean aRemoveAllOtherShapedsWithSameOutput, boolean aRemoveAllOtherNativeRecipes, Object[] aRecipe) {
		if (aRecipe == null || aRecipe.length <= 0) return F;
		if (aRecipe.length > 9) throw new IllegalArgumentException("Shapeless Recipe has more than 9 Inputs! This would crash NEI!");
		
		aResult = ST.validMeta(OM.get(aResult));
		for (byte i = 0; i < aRecipe.length; i++) {
			if (aRecipe[i] instanceof IItemContainer)
				aRecipe[i] = ((IItemContainer)aRecipe[i]).get(1);
			else if (aRecipe[i] instanceof Enum)
				aRecipe[i] = ((Enum<?>)aRecipe[i]).name();
			else if (aRecipe[i] instanceof Item)
				aRecipe[i] = ST.make((Item)aRecipe[i], 1, W);
			else if (aRecipe[i] instanceof Block)
				aRecipe[i] = ST.make((Block)aRecipe[i], 1, W);
			else if (!(aRecipe[i] == null || aRecipe[i] instanceof ItemStack || aRecipe[i] instanceof String || aRecipe[i] instanceof Character))
				aRecipe[i] = aRecipe[i].toString();
		}
		try {
			ItemStack[] tRecipe = new ItemStack[9];
			int i = 0;
			for (Object tObject : aRecipe) {
				if (tObject == null) {
					ERR.println("WARNING: Missing Item for shapeless Recipe: " + (aResult==null?"null":aResult.getDisplayName()));
					for (Object tContent : aRecipe) ERR.println(tContent);
					return F;
				}
				if (tObject instanceof ItemStack) {
					tRecipe[i] = (ItemStack)tObject;
				} else if (tObject instanceof String) {
					tRecipe[i] = OreDictManager.INSTANCE.getFirstOre(tObject, 1);
					if (tRecipe[i] == null) break;
				} else if (tObject instanceof Boolean) {
					//
				} else {
					throw new IllegalArgumentException();
				}
				i++;
			}
			remove(tRecipe);
		} catch(Throwable e) {e.printStackTrace(ERR);}
		
		if (aResult == null || aResult.stackSize <= 0) return F;
		
		if (aRemoveAllOthersWithSameOutput || aRemoveAllOthersWithSameOutputIfTheyHaveSameNBT || aRemoveAllOtherShapedsWithSameOutput || aRemoveAllOtherNativeRecipes) {
			remout(aResult, !aRemoveAllOthersWithSameOutputIfTheyHaveSameNBT, aRemoveAllOtherShapedsWithSameOutput, aRemoveAllOtherNativeRecipes, T);
		}
		
		ST.update(aResult);
		
		if (BUFFERING && aBuffered)
			BUFFER.add(new AdvancedCraftingShapeless(ST.copy(aResult), aDismantleable, aRemovable, aKeepNBT, !aNotAutoCraftable, aEnchantmentsAdded, aEnchantmentLevelsAdded, aRecipe));
		else
			GameRegistry.addRecipe(new AdvancedCraftingShapeless(ST.copy(aResult), aDismantleable, aRemovable, aKeepNBT, !aNotAutoCraftable, aEnchantmentsAdded, aEnchantmentLevelsAdded, aRecipe));
		return T;
	}
	
	public static IRecipe sLastRecipe = null;
	
	/**
	 * Checks all Crafting Handlers for Recipe Output
	 */
	public static ItemStack getany(World aWorld, ItemStack... aRecipe) {
		if (!ST.hasValid(aRecipe)) return null;
		
		if (aWorld == null) aWorld = CS.DW;
		
		InventoryCrafting aCrafting = crafting(aRecipe);
		
		if (sLastRecipe != null && sLastRecipe.matches(aCrafting, aWorld)) return sLastRecipe.getCraftingResult(aCrafting);
		
		List<IRecipe> tList = list();
		for (int i = 0; i < tList.size(); i++) if (tList.get(i).matches(aCrafting, aWorld)) return (sLastRecipe = tList.get(i)).getCraftingResult(aCrafting);
		
		int tIndex = 0;
		ItemStack tStack1 = null, tStack2 = null;
		for (int i = 0, j = aCrafting.getSizeInventory(); i < j; i++) {
			ItemStack tStack = aCrafting.getStackInSlot(i);
			if (tStack != null) {
				if (tIndex == 0) tStack1 = tStack;
				if (tIndex == 1) tStack2 = tStack;
				tIndex++;
			}
		}
		
		if (tIndex == 2) {
			assert tStack1 != null && tStack2 != null;
			if (tStack1.getItem() == tStack2.getItem() && tStack1.getItem().isRepairable()) {
				int tNewDamage = ST.meta_(tStack1)+ST.meta_(tStack2)+(tStack1.getMaxDamage()/-20)-tStack1.getMaxDamage();
				return ST.make(tStack1.getItem(), 1, tNewDamage<0?0:tNewDamage);
			}
		}
		
		return null;
	}
	
	/** Gives you a copy of the Output from a Crafting Recipe. Used for Recipe Detection. */
	public static ItemStack get(ItemStack... aRecipe) {return get(F, aRecipe);}
	/** Gives you a copy of the Output from a Crafting Recipe. Used for Recipe Detection. */
	public static ItemStack get(boolean aUncopiedStack, ItemStack... aRecipe) {
		if (!ST.hasValid(aRecipe)) return null;
		InventoryCrafting aCrafting = crafting(aRecipe);
		List<IRecipe> tList = list();
		for (int i = 0; i < tList.size(); i++) try {if (tList.get(i).matches(aCrafting, CS.DW)) return aUncopiedStack ? ST.valisize(tList.get(i).getRecipeOutput()) : ST.copy(ST.valisize(tList.get(i).getCraftingResult(aCrafting)));} catch(Throwable e) {e.printStackTrace(ERR);}
		return null;
	}
	
	/** Gives you a list of the Outputs from a Crafting Recipe. If you have multiple Mods, which add Bronze Armor for example */
	public static List<ItemStack> outputs(ItemStack... aRecipe) {return outputs(list(), F, aRecipe);}
	/** Gives you a list of the Outputs from a Crafting Recipe. If you have multiple Mods, which add Bronze Armor for example */
	public static List<ItemStack> outputs(List<IRecipe> aList, boolean aDeleteFromList, ItemStack... aRecipe) {
		if (aList == null || !ST.hasValid(aRecipe)) return Collections.emptyList();
		InventoryCrafting aCrafting = crafting(aRecipe);
		ArrayList<ItemStack> rList = new ArrayListNoNulls<>();
		for (int i = 0; i < aList.size(); i++) try {if (aList.get(i).matches(aCrafting, CS.DW)) rList.add(ST.copy(ST.valisize((aDeleteFromList ? aList.remove(i--) : aList.get(i)).getCraftingResult(aCrafting))));} catch(Throwable e) {e.printStackTrace(ERR);}
		return rList;
	}
	
	public static InventoryCrafting crafting(ItemStack... aRecipe) {
		InventoryCrafting rCrafting = new InventoryCrafting(new Container() {@Override public boolean canInteractWith(EntityPlayer var1) {return F;}}, 3, 3);
		for (int i = 0; i < 9 && i < aRecipe.length; i++) rCrafting.setInventorySlotContents(i, aRecipe[i]);
		return rCrafting;
	}
	
	@SuppressWarnings("unchecked")
	public static List<IRecipe> list() {return CraftingManager.getInstance().getRecipeList();}
	
	/**
	 * Checks if this Item has a Crafting Recipe.
	 * @param aOutput The output of the Recipe.
	 * @return if it has found at least one Recipe.
	 */
	public static boolean has(ItemStack aOutput) {
		if (ST.invalid(aOutput)) return F;
		List<IRecipe> tList = list();
		for (int i = 0; i < tList.size(); i++) if (ST.equal(OM.get(tList.get(i).getRecipeOutput()), aOutput, T)) return T;
		return F;
	}
	
	/**
	 * Removes a Crafting Recipe.
	 * @param aOutput The output of the Recipe.
	 * @return if it has removed at least one Recipe.
	 */
	public static boolean remout(ItemStack aOutput, boolean aIgnoreNBT, boolean aNotRemoveShapelessRecipes, boolean aOnlyRemoveNativeHandlers, boolean aDontRemoveDyeingRecipes) {
		if (ST.invalid(aOutput)) return F;
		boolean rReturn = F;
		List<IRecipe> tList = list();
		aOutput = OM.get_(aOutput);
		for (int i = 0; i < tList.size(); i++) {
			IRecipe tRecipe = tList.get(i);
			if (tRecipe instanceof ICraftingRecipeGT && !((ICraftingRecipeGT)tRecipe).isRemovableByGT()) continue;
			if (aNotRemoveShapelessRecipes && (tRecipe instanceof ShapelessRecipes || tRecipe instanceof ShapelessOreRecipe)) continue;
			if (aOnlyRemoveNativeHandlers) {
				if (!CLASSES_NATIVE.contains(tRecipe.getClass().getName())) continue;
			} else {
				if (CLASSES_SPECIAL.contains(tRecipe.getClass().getName())) continue;
			}
			if (!ST.equal(OM.get(tRecipe.getRecipeOutput()), aOutput, aIgnoreNBT)) continue;
			if (aDontRemoveDyeingRecipes) {
				if (tRecipe instanceof ShapedOreRecipe   ) {boolean temp = F; for (Object tObject : ((ShapedOreRecipe   )tRecipe).getInput()) if (OREDICT_DYE_LISTS.contains(tObject)) {temp = T; break;} if (temp) continue;}
				if (tRecipe instanceof ShapelessOreRecipe) {boolean temp = F; for (Object tObject : ((ShapelessOreRecipe)tRecipe).getInput()) if (OREDICT_DYE_LISTS.contains(tObject)) {temp = T; break;} if (temp) continue;}
			}
			tList.remove(i--);
			rReturn = T;
		}
		return rReturn;
	}
	
	/**
	 * Removes a Crafting Recipe.
	 * @param aOutput The output of the Recipe.
	 * @return if it has removed at least one Recipe.
	 */
	public static boolean remout(ItemStack aOutput) {return remout(aOutput, T, F, F, F);}
	
	/**
	 * Removes a Crafting Recipe.
	 * @param aOutput The output of the Recipe.
	 * @return if it has removed at least one Recipe.
	 */
	public static boolean remout(ModData aMod, String... aNames) {if (aMod.mLoaded) for (String aName : aNames) remout(aMod, aName, W); return aMod.mLoaded;}
	
	/**
	 * Removes a Crafting Recipe.
	 * @param aOutput The output of the Recipe.
	 * @return if it has removed at least one Recipe.
	 */
	public static boolean remout(ModData aMod, String aName, int aMetaData) {return remout(ST.make(aMod, aName, 1, aMetaData));}
	
	/**
	 * Yes "Delate" is a pun on Delete and Late. :P
	 * Removes a Crafting Recipe after Post Init.
	 * @param aOutput The output of the Recipe.
	 */
	public static void delate(ItemStack aOutput) {if (BUFFERING) RECIPES_TO_DELATE.add(aOutput); else remout(aOutput);}
	
	/**
	 * Yes "Delate" is a pun on Delete and Late. :P
	 * Removes a Crafting Recipe after Post Init.
	 * @param The output of the Recipe.
	 */
	public static void delate(ModData aMod, String... aNames) {if (aMod.mLoaded) for (String aName : aNames) delate(aMod, aName, W);}
	
	/**
	 * Yes "Delate" is a pun on Delete and Late. :P
	 * Removes a Crafting Recipe after Post Init.
	 * @param The output of the Recipe.
	 */
	public static void delate(ModData aMod, String aName, int aMetaData, int... aOtherMetaData) {Item aItem = ST.item(aMod, aName); if (aItem == null) return; delate(ST.make(aItem, 1, aMetaData)); for (int tMetaData : aOtherMetaData) delate(ST.make(aItem, 1, tMetaData));}
	
	/**
	 * Removes a Crafting Recipe and gives you the former output of it.
	 * @param aRecipe The content of the Crafting Grid as ItemStackArray with length 9
	 * @return the output of the old Recipe or null if there was nothing.
	 */
	public static ItemStack remove(ItemStack... aRecipe) {
		if (!ST.hasValid(aRecipe)) return null;
		ItemStack rReturn = null, tReturn = null;
		InventoryCrafting aCrafting = crafting(aRecipe);
		List<IRecipe> tList = list();
		for (int i = 0; i < tList.size(); i++) {try {for (; i < tList.size(); i++) {
			if ((!(tList.get(i) instanceof ICraftingRecipeGT) || ((ICraftingRecipeGT)tList.get(i)).isRemovableByGT()) && tList.get(i).matches(aCrafting, CS.DW)) {
				tReturn = tList.get(i).getCraftingResult(aCrafting);
				if (tReturn != null) {rReturn = tReturn; tList.remove(i--);}
			}
		}} catch(Throwable e) {e.printStackTrace(ERR);}}
		return rReturn;
	}
}
