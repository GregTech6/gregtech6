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

package gregapi;

import codechicken.nei.recipe.GuiUsageRecipe;
import cpw.mods.fml.common.Loader;
import gregapi.data.MD;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.tileentity.tools.MultiTileEntityAdvancedCraftingTable.MultiTileEntityGUIClientAdvancedCraftingTable;

import java.util.Iterator;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class NEI_GT_API_Config implements codechicken.nei.api.IConfigureNEI, Runnable {
	@Override
	public void loadConfig() {
		NEI = T;
		NEI_NH = Loader.instance().getIndexedModList().get("NotEnoughItems").getVersion().contains("GTNH");
		if (GAPI_POST.mFinishedPostInit) run(); else GAPI_POST.mAfterPostInit.add(this);
	}
	
	@Override
	public void run() {
		NEI = T;
		NEI_NH = Loader.instance().getIndexedModList().get("NotEnoughItems").getVersion().contains("GTNH");
		
		// Dont mess with NEI-NH, only mess with Vanilla NEI.
		if (!NEI_NH) {
			// Remove Usage Handlers that have been replaced by other Handlers or Tooltips.
			for (Iterator tIterator : new Iterator[] {/*GuiCraftingRecipe.craftinghandlers.iterator(), */GuiUsageRecipe.usagehandlers.iterator()}) {
				while (tIterator.hasNext()) {
					Object tObject = tIterator.next();
					if (tObject == null
					|| tObject.getClass().getName().contains("codechicken.nei.recipe.FuelRecipeHandler") // This one is just annoying clutter and badly designed too.
					|| tObject.getClass().getName().contains("codechicken.nei.recipe.FurnaceRecipeHandler") // There is a more detailed GT6 Handler for this.
					) tIterator.remove();// else DEB.println(tObject.getClass().getName());
				}
			}
		}
		
		for (RecipeMap tMap : RecipeMap.RECIPE_MAP_LIST) if (tMap.mNEIAllowed) new NEI_RecipeMap(tMap).init();
		
		if (CODE_CLIENT) {
			codechicken.nei.api.API.registerGuiOverlay(MultiTileEntityGUIClientAdvancedCraftingTable.class, "crafting", 55, 22);
			codechicken.nei.api.API.registerGuiOverlayHandler(MultiTileEntityGUIClientAdvancedCraftingTable.class, new codechicken.nei.recipe.DefaultOverlayHandler(55, 22), "crafting");
		}
	}
	
	@Override public String getName() {return MD.GAPI.mName + " NEI Plugin";}
	@Override public String getVersion() {return "6.16.02";}
}
