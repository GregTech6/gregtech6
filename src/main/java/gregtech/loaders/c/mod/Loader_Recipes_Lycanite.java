package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.FL;
import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Items;

public class Loader_Recipes_Lycanite implements Runnable {
	@Override
	public void run() {if (MD.LycM.mLoaded) {OUT.println("GT_Mod: Doing Lycanite Mobs Recipes.");
		CR.remout(MD.LycM_Inferno, "bucketpurelava");
		if (FL.Lava_Pure.exists()) RM.Mixer.addRecipe1(T, 16, 16, ST.make(Items.ghast_tear, 1, W), FL.Lava.make(1000), FL.Lava_Pure.make(1000), ZL_IS);
	}}
}