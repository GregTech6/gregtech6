/**
 * Copyright (c) 2022 GregTech-6 Team
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

import gregapi.data.MD;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.tileentity.tools.MultiTileEntityAdvancedCraftingTable.MultiTileEntityGUIClientAdvancedCraftingTable;
import gregtech.BuildInfo;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class NEI_GT_API_Config implements codechicken.nei.api.IConfigureNEI {
	public static boolean ADDED = T;
	
	@Override
	public void loadConfig() {
		ADDED = F;
		
		for (RecipeMap tMap : RecipeMap.RECIPE_MAP_LIST) if (tMap.mNEIAllowed) new NEI_RecipeMap(tMap);
		
		if (CODE_CLIENT) {
			codechicken.nei.api.API.registerGuiOverlay(MultiTileEntityGUIClientAdvancedCraftingTable.class, "crafting", 55, 22);
			codechicken.nei.api.API.registerGuiOverlayHandler(MultiTileEntityGUIClientAdvancedCraftingTable.class, new codechicken.nei.recipe.DefaultOverlayHandler(55, 22), "crafting");
		}
		
		NEI = T;
		
		ADDED = T;
	}
	
	@Override
	public String getName() {
		return MD.GAPI.mName + " NEI Plugin";
	}
	
	@Override
	public String getVersion() {
		return BuildInfo.version;
	}
}
