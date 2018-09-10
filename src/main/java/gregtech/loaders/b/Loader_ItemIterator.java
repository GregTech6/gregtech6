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

package gregtech.loaders.b;

import static gregapi.data.CS.*;

import java.util.Iterator;

import gregapi.config.ConfigCategories;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.CS.ToolsGT;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.item.ItemBase;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class Loader_ItemIterator implements Runnable {
	@Override
	public void run() {
		ItemStack tStack, tStack2;
		
		OUT.println("GT_Mod: Scanning for certain kinds of compatible Machineblocks.");
		if (null != (tStack = CR.get(tStack2 = OP.ingot.mat(MT.Bronze, 1), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2))) {
			OM.data(tStack, new OreDictItemData(OM.stack(MT.Bronze, (8*U)/tStack.stackSize)));
		}
		if (null != (tStack = CR.get(tStack2 = OP.plate.mat(MT.Bronze, 1), tStack2, tStack2, tStack2, null, tStack2, tStack2, tStack2, tStack2))) {
			OM.data(tStack, new OreDictItemData(OM.stack(MT.Bronze, (8*U)/tStack.stackSize)));
		}
		
		OUT.println("GT_Mod: Adding Food Recipes to the Automatic Canning Machine. (also during the following Item Iteration)");
		
		if (IL.IC2_Food_Can_Empty.exists() && IL.IC2_Food_Can_Filled.exists()) {
		RM.Canner.addRecipe2(T, 16, 64, ST.make(Items.rotten_flesh, 1, W), IL.IC2_Food_Can_Empty.get(4), IL.IC2_Food_Can_Spoiled.get(4, IL.IC2_Food_Can_Filled.get(4)));
		RM.Canner.addRecipe2(T, 16, 32, ST.make(Items.spider_eye, 1, W), IL.IC2_Food_Can_Empty.get(2), IL.IC2_Food_Can_Poisonous.get(2, IL.IC2_Food_Can_Filled.get(2)));
		RM.Canner.addRecipe2(T, 16, 32, IL.Food_Potato_Poisonous.get(1), IL.IC2_Food_Can_Empty.get(2), IL.IC2_Food_Can_Poisonous.get(2, IL.IC2_Food_Can_Filled.get(2)));
		RM.Canner.addRecipe2(T, 16, 96, ST.make(Items.cake, 1, W), IL.IC2_Food_Can_Empty.get(12), IL.IC2_Food_Can_Filled.get(12));
		RM.Canner.addRecipe2(T, 16, 48, ST.make(Items.mushroom_stew, 1, W), IL.IC2_Food_Can_Empty.get(6), IL.IC2_Food_Can_Filled.get(6), ST.make(Items.bowl, 1, 0));
		}
		
		OUT.println("GT_Mod: Scanning ItemList.");
		
		Iterator tIterator = Item.itemRegistry.iterator();
		
		Object tObject;
		String tName;
		
		while (tIterator.hasNext()) if ((tObject = tIterator.next()) != null && tObject instanceof Item && !(tObject instanceof ItemBase)) {
			Item tItem = (Item)tObject;
			if ((tName = tItem.getUnlocalizedName()) != null) {
				try {
					if (tItem instanceof mods.railcraft.api.core.items.IToolCrowbar) {
						if (!tItem.isDamageable() && !(COMPAT_EU_ITEM != null && COMPAT_EU_ITEM.is(ST.make(tItem, 1, 0)))) {
							if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "infiniteDurabilityRCCrowbars", F)) {
								if (CR.remout(ST.make(tItem, 1, W))) OUT.println("GT_Mod: Removed infinite RC Crowbar: " + tName);
							}
						} else {
							if (ToolsGT.add(TOOL_crowbar, ST.make(tItem, 1, W))) OUT.println("GT_Mod: Registered valid RC Crowbar: " + tName);
						}
					}
				} catch (Throwable e) {/*Do nothing*/}
				try {
					if (tItem instanceof buildcraft.api.tools.IToolWrench) {
						if (!tItem.isDamageable() && !(COMPAT_EU_ITEM != null && COMPAT_EU_ITEM.is(ST.make(tItem, 1, 0)))) {
							if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "infiniteDurabilityBCWrenches", F)) {
								if (CR.remout(ST.make(tItem, 1, W))) OUT.println("GT_Mod: Removed infinite BC Wrench: " + tName);
							}
						} else {
							if (ToolsGT.add(TOOL_wrench, ST.make(tItem, 1, W))) OUT.println("GT_Mod: Registered valid BC Wrench: " + tName);
						}
					}
				} catch (Throwable e) {/*Do nothing*/}
				
				if (tItem instanceof ItemFood && IL.IC2_Food_Can_Empty.exists() && IL.IC2_Food_Can_Filled.exists() && tItem != IL.IC2_Food_Can_Filled.item() && tItem != IL.IC2_Food_Can_Spoiled.item()) {
					int tFoodValue = ((ItemFood)tItem).func_150905_g(ST.make(tItem, 1, 0));
					if (tFoodValue > 0) {
						RM.Canner.addRecipe2(T, 16, 16*tFoodValue, ST.make(tItem, 1, W), IL.IC2_Food_Can_Empty.get(tFoodValue), IL.IC2_Food_Can_Filled.get(tFoodValue), ST.container(ST.make(tItem, 1, 0), true));
					}
				}
				
				if (tName.equals("item.ItemSensorLocationCard") || tName.equals("item.ItemEnergySensorLocationCard") || tName.equals("item.ItemEnergyArrayLocationCard") || tName.equals("item.ItemTextCard")) {
					RM.Assembler.addRecipe1(T, 16, 400, ST.make(tItem, 1, W), IL.Circuit_Basic.get(2));
				}
				if (tName.equals("item.ItemTimeCard")) {
					RM.Assembler.addRecipe1(T, 16, 200, ST.make(tItem, 1, W), IL.Circuit_Basic.get(1));
				}
				if (tName.equals("tile.ArsMagica:ore_vinteum")) {
					OreDictManager.INSTANCE.setTarget_(OP.oreVanillastone, MT.Vinteum, ST.make(tItem, 1, 0));
				}
				if (tName.equals("item.ccprintout")) {
					OM.reg_("paperWritten", ST.make(tItem, 1, 0));
					OM.reg_("paperWritten", ST.make(tItem, 1, 1));
					OM.reg_("bookWritten", ST.make(tItem, 1, 2));
				}
			}
		}
	}
}
