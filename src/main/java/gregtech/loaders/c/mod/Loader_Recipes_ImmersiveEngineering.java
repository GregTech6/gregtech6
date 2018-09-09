package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictListenerEvent_Names;
import gregapi.oredict.OreDictListenerEvent_TwoNames;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Loader_Recipes_ImmersiveEngineering implements Runnable {
	@Override
	public void run() {if (MD.IE.mLoaded) {OUT.println("GT_Mod: Doing Immersive Engineering Recipes.");
		RM.sawing(16, 96, F, 100, ST.make(MD.IE, "woodenDevice", 1, 6), IL.Plank.get(6), OP.dustSmall.mat(MT.Wood, 2));
		
		RM.generify(ST.make(BlocksGT.Planks, 1, 10), ST.make(MD.IE, "treatedWood", 1, 0));
		RM.generify(ST.make(BlocksGT.PlanksFireProof, 1, 10), ST.make(MD.IE, "treatedWood", 1, 0));
		RM.generify(ST.make(MD.IE, "treatedWood", 1, 0), ST.make(BlocksGT.Planks, 1, 10));
		CR.shapeless(ST.make(BlocksGT.Planks, 1, 10), CR.DEF, new Object[] {ST.make(MD.IE, "treatedWood", 1, 2)});
		CR.shapeless(ST.make(MD.IE, "treatedWood", 1, 0), CR.DEF, new Object[] {ST.make(BlocksGT.Planks, 1, 10)});
		CR.shapeless(ST.make(MD.IE, "treatedWood", 1, 0), CR.DEF, new Object[] {ST.make(BlocksGT.PlanksFireProof, 1, 10)});
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener(new OreDictListenerEvent_TwoNames("cropHemp", OP.stick.dat(ANY.Wood)) {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
			RM.Loom.addRecipe2(T, 16, 64, ST.amount(8, aStack1), aStack2, ST.make(MD.IE, "material", 1, 4));
		}});
		}};
		
		if (IL.IE_Hammer.exists()) {
			ArrayListNoNulls tRecipesToRemove = new ArrayListNoNulls();
			for (Object tRecipe : CraftingManager.getInstance().getRecipeList()) {
				if (tRecipe instanceof ShapelessOreRecipe) {
					for (Object tInput : ((ShapelessOreRecipe)tRecipe).getInput()) {
						if (IL.IE_Hammer.equal(tInput, F, T)) {
							tRecipesToRemove.add(tRecipe);
							break;
						}
					}
				}
			}
			CraftingManager.getInstance().getRecipeList().removeAll(tRecipesToRemove);
		}
	}}
}