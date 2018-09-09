package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.util.CR;
import gregapi.util.ST;


public class Loader_Recipes_BetterRecords implements Runnable {
	@Override
	public void run() {if (MD.BETTER_RECORDS.mLoaded) {OUT.println("GT_Mod: Doing Better Records Recipes.");
		CR.shapeless(ST.make(MD.BETTER_RECORDS, "urlrecord", 1, 0), CR.DEF_NCC, new Object[] {OD.record, OP.gem.dat(MT.EnderEye)});
	}}
}