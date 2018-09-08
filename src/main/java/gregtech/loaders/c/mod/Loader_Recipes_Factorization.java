package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.util.ST;

public class Loader_Recipes_Factorization implements Runnable {
	@Override
	public void run() {if (MD.FZ.mLoaded) {OUT.println("GT_Mod: Doing Factorization Recipes.");
		RM.Canner.addRecipe1(T, 16, 16, IL.Bottle_Empty.get(1), MT.H2SO4		.fluid(U, T), NF, ST.make(MD.FZ, "acid", 1, 0));
		RM.Canner.addRecipe1(T, 16, 16, IL.Bottle_Empty.get(1), MT.AquaRegia	.fluid(U, T), NF, ST.make(MD.FZ, "acid", 1, 1));
		
		RM.sawing(16, 800, F, 5000, ST.make(MD.FZ, "daybarrel", 1, W), OP.plate.mat(MT.Wood, 42), OP.dustSmall.mat(MT.Wood, 30));
		
		RM.generify(ST.make(MD.FZ, "diamond_shard", 1, W), OP.gemFlawed.mat(MT.Diamond, 1));
	}}
}