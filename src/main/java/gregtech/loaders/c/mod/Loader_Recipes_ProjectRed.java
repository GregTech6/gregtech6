package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.ANY;
import gregapi.data.MD;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.util.CR;
import gregapi.util.ST;


public class Loader_Recipes_ProjectRed implements Runnable {
	@Override
	public void run() {
		if (MD.PR.mLoaded) {OUT.println("GT_Mod: Doing PR Recipes.");
			CR.shaped(ST.make(MD.PR, "projectred.core.part", 1, 40), CR.DEF | CR.DEL_OTHER_NATIVE_RECIPES, " D ", "DID", " D ", 'D', OD.itemRedstone, 'I', OP.ingot.dat(ANY.Cu));
			
			
			
		}
	}
}