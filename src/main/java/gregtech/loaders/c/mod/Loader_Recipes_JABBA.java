package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.util.ST;

public class Loader_Recipes_JABBA implements Runnable {
	@Override
	public void run() {if (MD.JABBA.mLoaded) {OUT.println("GT_Mod: Doing JABBA Recipes.");
		RM.sawing(16, 800, F, 5000, ST.make(MD.JABBA, "barrel", 1, W), IL.Plank.get(50), OP.dustSmall.mat(MT.Wood, 30));
	}}
}