package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Blocks;

public class Loader_Recipes_Chisel implements Runnable {
	@Override
	public void run() {if (MD.CHSL.mLoaded) {OUT.println("GT_Mod: Doing Chisel Mod Recipes.");
		RM.rem_smelting(ST.make(Blocks.gravel, 1, 0));
		
		CR.remout(IL.CHSL_Granite.get(1));
		CR.remout(IL.CHSL_Diorite.get(1));
		CR.remout(IL.CHSL_Andesite.get(1));
		CR.remout(IL.CHSL_Granite_Smooth.get(1));
		CR.remout(IL.CHSL_Diorite_Smooth.get(1));
		CR.remout(IL.CHSL_Andesite_Smooth.get(1));
	}}
}