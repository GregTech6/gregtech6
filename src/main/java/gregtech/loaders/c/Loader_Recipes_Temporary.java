package gregtech.loaders.c;

import static gregapi.data.CS.*;

import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;

/**
 * @author Gregorius Techneticies
 * 
 * Here is basically everything that I want to change to something better later.
 */
public class Loader_Recipes_Temporary implements Runnable {
	@Override public void run() {OUT.println("GT_Mod: Doing Temporary Recipes for GregTech things that are not quite fleshed out yet.");
		// TODO: Graphite Electrodes are made from petroleum coke after it is mixed with coal tar pitch. They are then extruded and shaped, baked to carbonize the binder (pitch) and finally graphitized by heating it to temperatures approaching 3273K.
		RM.Extruder.addRecipe2(T, 512, 512, OP.dust.mat(MT.Graphite, 1), IL.Shape_Extruder_Rod.get(0), OP.stick.mat(MT.Graphite, 1));
		
		
		
	}
}