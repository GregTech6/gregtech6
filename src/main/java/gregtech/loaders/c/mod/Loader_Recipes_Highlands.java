package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.RM;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Items;

public class Loader_Recipes_Highlands implements Runnable {
	@Override
	public void run() {if (MD.HiL.mLoaded) {OUT.println("GT_Mod: Doing Highlands Recipes.");
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.HiL, "tile.hl_blueFlower"	, 1, W), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Cyan], ST.make(Items.dye, 1, DYE_INDEX_Cyan));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.HiL, "tile.hl_lavender"	, 1, W), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.HiL, "tile.hl_whiteFlower"	, 1, W), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], OM.dust(MT.White));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.HiL, "tile.hl_cotton"		, 1, W), ST.make(Items.string, 2, 0));
		
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.HiL, "tile.hl_blueFlower"	, 1, W), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Cyan], ST.make(Items.dye, 1, DYE_INDEX_Cyan));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.HiL, "tile.hl_lavender"		, 1, W), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.HiL, "tile.hl_whiteFlower"	, 1, W), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], OM.dust(MT.White));
		RM.Juicer.addRecipe1(T, 16, 16, ST.make(MD.HiL, "tile.hl_cotton"		, 1, W), ST.make(Items.string, 1, 0));
		
		if (ENABLE_ADDING_IC2_EXTRACTOR_RECIPES) {
		RM.ic2_extractor(ST.make(MD.HiL, "tile.hl_blueFlower"	, 1, W), ST.make(Items.dye, 2, DYE_INDEX_Cyan));
		RM.ic2_extractor(ST.make(MD.HiL, "tile.hl_lavender"		, 1, W), ST.make(Items.dye, 2, DYE_INDEX_Purple));
		RM.ic2_extractor(ST.make(MD.HiL, "tile.hl_whiteFlower"	, 1, W), OM.dust(MT.White, U*2));
		RM.ic2_extractor(ST.make(MD.HiL, "tile.hl_cotton"		, 1, W), ST.make(Items.string, 2, 0));
		}
	}}
}