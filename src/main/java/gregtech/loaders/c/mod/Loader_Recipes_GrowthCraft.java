package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.config.ConfigCategories;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Items;
import net.minecraftforge.fluids.FluidStack;

public class Loader_Recipes_GrowthCraft implements Runnable {
	@Override
	public void run() {if (MD.GrC.mLoaded) {OUT.println("GT_Mod: Doing GrowthCraft Recipes.");
		CR.remout(IL.GrC_Honey_Jar.get(1));
		CR.remout(IL.GrC_Honey_Bucket.get(1));
		CR.remout(IL.GrC_Honey_Bottle.get(1));
		CR.remout(IL.GrC_Milk_Bucket.get(1));
		CR.remout(IL.GrC_Milk_Bottle.get(1));
		CR.remout(IL.GrC_Applecore.get(1));
		
		RM.rem_smelting(ST.make(MD.GrC, "grccore.BottleFluidSaltWater", 1, 0));
		RM.rem_smelting(ST.make(MD.GrC, "grccore.BucketFluidSaltWater", 1, 0));
		
		CR.shaped(IL.GrC_Ice_Cream				.get(1), CR.DEF_REM_NAC_NCC, "I", "B", 'B', ST.make(Items.bowl, 1, W), 'I', IL.Food_Ice_Cream);
		CR.shaped(IL.GrC_Ice_Cream_Chocolate	.get(1), CR.DEF_REM_NAC_NCC, "I", "B", 'B', ST.make(Items.bowl, 1, W), 'I', IL.Food_Ice_Cream_Chocolate);
		CR.shaped(IL.GrC_Ice_Cream_Grape		.get(1), CR.DEF_REM_NAC_NCC, "I", "B", 'B', ST.make(Items.bowl, 1, W), 'I', IL.Food_Ice_Cream_Grape);
		CR.shaped(IL.GrC_Ice_Cream_Apple		.get(1), CR.DEF_REM_NAC_NCC, "I", "B", 'B', ST.make(Items.bowl, 1, W), 'I', IL.Food_Ice_Cream_Apple);
		CR.shaped(IL.GrC_Ice_Cream_Honey		.get(1), CR.DEF_REM_NAC_NCC, "I", "B", 'B', ST.make(Items.bowl, 1, W), 'I', IL.Food_Ice_Cream_Honey);
		CR.shaped(IL.GrC_Ice_Cream_Melon		.get(1), CR.DEF_REM_NAC_NCC, "I", "B", 'B', ST.make(Items.bowl, 1, W), 'I', IL.Food_Ice_Cream_Melon);
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "wood2charcoalsmelting", T)) RM.rem_smelting(IL.GrC_Bamboo.get(1));
		
		for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Red		]) RM.Bath.addRecipe1(T, 0, 16, OP.ingot.mat(MT.WaxBee, 1), tDye, NF, ST.make(MD.GrC_Bees, "grcbees.BeesWax", 1, 1));
		for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Black	]) RM.Bath.addRecipe1(T, 0, 16, OP.ingot.mat(MT.WaxBee, 1), tDye, NF, ST.make(MD.GrC_Bees, "grcbees.BeesWax", 1, 2));
	}}
}