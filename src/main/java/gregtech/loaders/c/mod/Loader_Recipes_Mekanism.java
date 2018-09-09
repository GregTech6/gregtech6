package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.MD;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;

public class Loader_Recipes_Mekanism implements Runnable {
	@Override
	public void run() {
		if (MD.Mek.mLoaded) {
			OUT.println("GT_Mod: Doing Mekanism Recipes.");
			ItemStack x = ST.make(MD.Mek, "Salt", 1, 0);
			CR.remove(x, x, NI, x, x);
		}
	}
}