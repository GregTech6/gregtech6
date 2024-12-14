package gregapi;

import cpw.mods.fml.common.event.FMLInterModComms;
import gregapi.data.MD;
import gregapi.recipes.Recipe;
import gregapi.tileentity.tools.MultiTileEntityAdvancedCraftingTable;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;


public class NEI_GT_IMCSender implements Runnable{
    @Override
    public void run() {
        for (Recipe.RecipeMap tMap : Recipe.RecipeMap.RECIPE_MAP_LIST) if (tMap.mNEIAllowed) {
            String stackName = ST.regMeta(tMap.mRecipeMachineList.isEmpty() ? ST.make(Blocks.lit_furnace, 1, 0) : tMap.mRecipeMachineList.get(0));
            sendHandler(tMap.mNameNEI,stackName);

            for (ItemStack aStack : tMap.mRecipeMachineList) if (ST.valid(aStack)) {
                sendCatalyst(tMap.mNameNEI,ST.regMeta(aStack));
            }
        };
    }
    private static void sendHandler(String aName, String aBlock) {
        NBTTagCompound aNBT = new NBTTagCompound();
        aNBT.setString("handler", aName);
        aNBT.setString("modName", MD.GT.mName);
        aNBT.setString("modId", MD.GT.mID);
        aNBT.setBoolean("modRequired", true);
        aNBT.setString("itemName", aBlock);
        aNBT.setInteger("handlerHeight", 135);
        aNBT.setInteger("handlerWidth", 166);
        aNBT.setInteger("maxRecipesPerPage", 1);
        aNBT.setInteger("yShift", 0);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerHandlerInfo", aNBT);
    }

    private static void sendCatalyst(String aName, String aStack) {
        NBTTagCompound aNBT = new NBTTagCompound();
        aNBT.setString("handlerID", aName);
        aNBT.setString("itemName", aStack);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerCatalystInfo", aNBT);
    }
}
