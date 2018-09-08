package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.util.CR;
import net.minecraftforge.fluids.FluidStack;

public class Loader_Recipes_Mystcraft implements Runnable {
	@Override
	public void run() {
		if (MD.MYST.mLoaded) {OUT.println("GT_Mod: Doing Mystcraft Recipes.");
			
			CR.remout(IL.Myst_Ink_Vial.get(1));
			
			for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Black]) {
			RM.Mixer			.addRecipe0(T, 16,   16, new FluidStack[] {tDye, FL.Water.make(125)}, FL.Myst_Ink.make(500), ZL_IS);
			RM.Mixer			.addRecipe0(T, 16,   16, new FluidStack[] {tDye, FL.DistW.make(125)}, FL.Myst_Ink.make(500), ZL_IS);
			}
		}
	}
}