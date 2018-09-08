package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.MD;
import gregapi.util.CR;

public class Loader_Recipes_RandomThings implements Runnable {
	@Override
	public void run() {
		if (MD.RT.mLoaded) {OUT.println("GT_Mod: Doing RandomThings Recipes.");
			
			CR.remout(MD.RT, "ingredient", 4);
		}
	}
}