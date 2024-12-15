package gregapi;

import cpw.mods.fml.common.event.FMLInterModComms;
import gregapi.data.MD;
import gregapi.recipes.Recipe;
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

            //sortMachines(tMap.mRecipeMachineList);
            for (ItemStack aStack : tMap.mRecipeMachineList) if (ST.valid(aStack)) {
                sendCatalyst(tMap.mNameNEI,ST.regMeta(aStack),0); //getSortedPriority(aStack));
            }
        };
        //mCatalystPriority = null;
    }
    //Seems unnecessary...
    //protected @Nullable HashMap<String, Integer> mCatalystPriority = new HashMap<>();
    //
    //public void sortMachines(List<ItemStack> stackList){
    //    mCatalystPriority = new HashMap<>();
    //    HashMap<String, Double> priorityTmp = new HashMap<>();
    //    for (ItemStack stack : stackList) {
    //        if(!(stack.hasTagCompound())) {
    //            mCatalystPriority.put(ST.regMeta(stack), 0);
    //            continue;
    //        }
    //        NBTTagCompound tag = stack.getTagCompound();
    //        if(tag.hasKey(NBT_INPUT_MAX))priorityTmp.put(ST.regMeta(stack), (double) tag.getLong(NBT_INPUT_MAX));
    //        else if(tag.hasKey(NBT_INPUT))priorityTmp.put(ST.regMeta(stack), (double) tag.getLong(NBT_INPUT));
    //        else if(tag.hasKey(NBT_OUTPUT_MAX))priorityTmp.put(ST.regMeta(stack), (double) tag.getLong(NBT_OUTPUT_MAX));
    //        else if(tag.hasKey(NBT_OUTPUT))priorityTmp.put(ST.regMeta(stack), (double) tag.getLong(NBT_OUTPUT));
    //        else if(tag.hasKey(NBT_MATERIAL))priorityTmp.put(ST.regMeta(stack),OreDictMaterial.get(tag.getString(NBT_MATERIAL)).mMeltingPoint * 1.25);
    //        else priorityTmp.put(ST.regMeta(stack),0.0);
    //    }
    //    mCatalystPriority = sortMap(priorityTmp);
    //}
    //public static HashMap<String, Integer> sortMap(HashMap<String, Double> originalMap) {
    //    //sort them based on the double values
    //    List<Map.Entry<String, Double>> entryList = new ArrayList<>(originalMap.entrySet());
    //    entryList.sort(Map.Entry.comparingByValue());
    //
    //    // Step 2: Create a new HashMap to store the sorted indices
    //    HashMap<String, Integer> sortedIndices = new HashMap<>();
    //
    //    // Step 3: Assign indices based on the sorted order
    //    int index = 0;
    //    for (Map.Entry<String, Double> entry : entryList) {
    //        sortedIndices.put(entry.getKey(), index++);
    //    }
    //
    //    return sortedIndices;
    //}
    //public int getSortedPriority(ItemStack stack){
    //    if(mCatalystPriority==null)return 0;
    //    Integer i = mCatalystPriority.get(ST.regMeta(stack));
    //    if(i == null)return 0;
    //    return i;
    //}

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

    private static void sendCatalyst(String aName, String aStack, int aPriority) {
        NBTTagCompound aNBT = new NBTTagCompound();
        aNBT.setString("handlerID", aName);
        aNBT.setString("itemName", aStack);
        aNBT.setInteger("priority", aPriority);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerCatalystInfo", aNBT);
    }
}
