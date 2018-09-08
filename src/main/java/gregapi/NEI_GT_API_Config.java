package gregapi;

import static gregapi.data.CS.*;

import gregapi.data.MD;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.tileentity.tools.MultiTileEntityAdvancedCraftingTable.MultiTileEntityGUIClientAdvancedCraftingTable;

/**
 * @author Gregorius Techneticies
 */
public class NEI_GT_API_Config implements codechicken.nei.api.IConfigureNEI {
	public static boolean sIsAdded = T;
	
	@Override
	public void loadConfig() {
        sIsAdded = F;
        
        for (RecipeMap tMap : RecipeMap.RECIPE_MAPS.values()) if (tMap.mNEIAllowed) new NEI_RecipeMap(tMap);
        
        if (CODE_CLIENT) {
        	codechicken.nei.api.API.registerGuiOverlay(MultiTileEntityGUIClientAdvancedCraftingTable.class, "crafting", 55, 22);
        	codechicken.nei.api.API.registerGuiOverlayHandler(MultiTileEntityGUIClientAdvancedCraftingTable.class, new codechicken.nei.recipe.DefaultOverlayHandler(55, 22), "crafting");
        }
    	
        NEI = T;
        
        sIsAdded = T;
	}
	
	@Override
	public String getName() {
		return MD.GAPI.mName + " NEI Plugin";
	}
	
	/** This is just the last time I was looking at this Version Number :P */
	@Override
	public String getVersion() {
		return "6.07.13";
	}
}