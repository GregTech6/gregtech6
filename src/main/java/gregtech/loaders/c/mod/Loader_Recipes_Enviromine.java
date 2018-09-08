package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.util.CR;

public class Loader_Recipes_Enviromine implements Runnable {
	@Override
	public void run() {if (MD.ENVM.mLoaded) {OUT.println("GT_Mod: Doing Enviromine Recipes.");
		CR.remout(IL.ENVM_Bottle_Water_Cold.get(1));
		CR.remout(IL.ENVM_Bottle_Water_Dirty.get(1));
		CR.remout(IL.ENVM_Bottle_Water_Salty.get(1));
	}}
}