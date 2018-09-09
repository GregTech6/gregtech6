package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.util.ST;
import gregapi.util.UT;

public class Loader_Recipes_ForbiddenMagic implements Runnable {
	@Override
	public void run() {if (MD.TCFM.mLoaded) {OUT.println("GT_Mod: Doing TC Forbidden Magic Recipes.");
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.TCFM, "InkFlower"	, 1, W), NF, UT.Fluids.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Black], 2), ST.make(MD.TCFM, "FMResource", 1, 1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.TCFM, "UmbralBush"	, 1, W), NF, UT.Fluids.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Black], 4), ST.make(MD.TCFM, "FMResource", 2, 1));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.TCFM, "InkFlower"	, 1, W), NF, UT.Fluids.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Black], 1), ST.make(MD.TCFM, "FMResource", 1, 1));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.TCFM, "UmbralBush"	, 1, W), NF, UT.Fluids.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Black], 2), ST.make(MD.TCFM, "FMResource", 2, 1));
		RM.ic2_extractor(ST.make(MD.TCFM, "InkFlower"	, 1, W), ST.make(MD.TCFM, "FMResource", 3, 1));
		RM.ic2_extractor(ST.make(MD.TCFM, "UmbralBush"	, 1, W), ST.make(MD.TCFM, "FMResource", 6, 1));
	}}
}