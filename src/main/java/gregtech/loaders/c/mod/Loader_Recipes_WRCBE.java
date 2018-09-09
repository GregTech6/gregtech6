package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.ANY;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class Loader_Recipes_WRCBE implements Runnable {
	@Override
	public void run() {if (MD.WR_CBE_C.mLoaded) {OUT.println("GT_Mod: Doing WR-CBE Recipes.");
		CR.remove(ST.make(Blocks.obsidian, 1, 0), NI, NI, ST.make(Blocks.obsidian, 1, 0));
		CR.remout(MD.WR_CBE_C, "retherPearl");
		
		for (OreDictMaterial tGlowstone : ANY.Glowstone.mToThis) {
			RM.Mixer.addRecipeX(T, 16, 16, new ItemStack[] {OP.gem.mat(MT.EnderPearl, 1), OP.dust     .mat(MT.Redstone,  4), OP.dust     .mat(tGlowstone,  4)}, ST.make(MD.WR_CBE_C, "retherPearl", 1, 0));
			RM.Mixer.addRecipeX(T, 16, 16, new ItemStack[] {OP.gem.mat(MT.EnderPearl, 1), OP.dustSmall.mat(MT.Redstone, 16), OP.dustSmall.mat(tGlowstone, 16)}, ST.make(MD.WR_CBE_C, "retherPearl", 1, 0));
			RM.Mixer.addRecipeX(T, 16, 16, new ItemStack[] {OP.gem.mat(MT.EnderPearl, 1), OP.dustTiny .mat(MT.Redstone, 36), OP.dustTiny .mat(tGlowstone, 36)}, ST.make(MD.WR_CBE_C, "retherPearl", 1, 0));
		}
	}}
}