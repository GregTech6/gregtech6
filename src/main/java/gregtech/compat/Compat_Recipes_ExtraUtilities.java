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

package gregtech.compat;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.config.ConfigCategories;
import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Blocks;

import static gregapi.data.CS.*;

public class Compat_Recipes_ExtraUtilities extends CompatMods {
	public Compat_Recipes_ExtraUtilities(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Extra Utilities Recipes.");
		ItemsGT.VOIDING_ITEMS.add(MD.ExU, "trashcan", 0);
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "extra-utilities-trash-can-items", T)) {
			ItemsGT.RECIPE_REMOVED_USE_TRASH_BIN_INSTEAD.add(MD.ExU, "trashcan", 0);
			CR.delate(MD.ExU, "trashcan", 0);
		}
		ItemsGT.VOIDING_ITEMS.add(MD.ExU, "trashcan", 1);
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "extra-utilities-trash-can-fluids", T)) {
			ItemsGT.RECIPE_REMOVED_USE_TRASH_BIN_INSTEAD.add(MD.ExU, "trashcan", 1);
			CR.delate(MD.ExU, "trashcan", 1);
		}
		ItemsGT.VOIDING_ITEMS.add(MD.ExU, "trashcan", 2);
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "extra-utilities-trash-can-energy", F)) {
			ItemsGT.RECIPE_REMOVED_USE_TRASH_BIN_INSTEAD.add(MD.ExU, "trashcan", 2);
			CR.delate(MD.ExU, "trashcan", 2);
		}
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener("cobblestone"               , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1, 0));}});
		addListener("compressedCobblestone1x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1, 1));}});
		addListener("compressedCobblestone2x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1, 2));}});
		addListener("compressedCobblestone3x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1, 3));}});
		addListener("compressedCobblestone4x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1, 4));}});
		addListener("compressedCobblestone5x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1, 5));}});
		addListener("compressedCobblestone6x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1, 6));}});
		addListener("compressedCobblestone7x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1, 7));}});
		addListener("dirt"                      , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1, 8));}});
		addListener("compressedDirt1x"          , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1, 9));}});
		addListener("compressedDirt2x"          , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1,10));}});
		addListener("compressedDirt3x"          , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1,11));}});
		addListener("gravel"                    , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1,12));}});
		addListener("compressedGravel1x"        , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1,13));}});
		addListener("sand"                      , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1,14));}});
		addListener("compressedSand1x"          , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Press.addRecipe2(T, 16, 64, ST.amount(9, aEvent.mStack), ST.tag(9), ST.make(MD.ExU, "cobblestone_compressed", 1,15));}});
		
		addListener("compressedCobblestone1x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(Blocks.cobblestone, 9, 0));}});
		addListener("compressedCobblestone2x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(MD.ExU, "cobblestone_compressed", 9, 0));}});
		addListener("compressedCobblestone3x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(MD.ExU, "cobblestone_compressed", 9, 1));}});
		addListener("compressedCobblestone4x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(MD.ExU, "cobblestone_compressed", 9, 2));}});
		addListener("compressedCobblestone5x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(MD.ExU, "cobblestone_compressed", 9, 3));}});
		addListener("compressedCobblestone6x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(MD.ExU, "cobblestone_compressed", 9, 4));}});
		addListener("compressedCobblestone7x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(MD.ExU, "cobblestone_compressed", 9, 5));}});
		addListener("compressedCobblestone8x"   , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(MD.ExU, "cobblestone_compressed", 9, 6));}});
		addListener("compressedDirt1x"          , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(Blocks.dirt, 9, 0));}});
		addListener("compressedDirt2x"          , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(MD.ExU, "cobblestone_compressed", 9, 8));}});
		addListener("compressedDirt3x"          , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(MD.ExU, "cobblestone_compressed", 9, 9));}});
		addListener("compressedDirt4x"          , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(MD.ExU, "cobblestone_compressed", 9,10));}});
		addListener("compressedGravel1x"        , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(Blocks.gravel, 9, 0));}});
		addListener("compressedGravel2x"        , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(MD.ExU, "cobblestone_compressed", 9,12));}});
		addListener("compressedSand1x"          , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(Blocks.sand, 9, 0));}});
		addListener("compressedSand2x"          , new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.Crusher.addRecipe1(T, 16, 64, ST.amount(1, aEvent.mStack), ST.make(MD.ExU, "cobblestone_compressed", 9,14));}});
		}};
	}
}
