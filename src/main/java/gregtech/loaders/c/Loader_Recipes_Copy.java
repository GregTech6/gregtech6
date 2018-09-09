package gregtech.loaders.c;

import static gregapi.data.CS.*;

import java.util.Map;

import forestry.api.recipes.ICentrifugeRecipe;
import forestry.api.recipes.ISqueezerRecipe;
import forestry.api.recipes.RecipeManagers;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.RM;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;

public class Loader_Recipes_Copy implements Runnable {
	@Override public void run() {OUT.println("GT_Mod: Copying other Mods Recipes and the Fluid Registry over to GT Machines.");
		
		for (Map<String, FluidContainerData> tMap : UT.Fluids.sEmpty2Fluid2Data.values()) for (FluidContainerData tData : tMap.values()) {
			ItemStack tEmpty = (tData.emptyContainer.getItem() == Items.bucket ? ST.container(tData.filledContainer, F) : tData.emptyContainer);
			if (ST.valid(tEmpty)) RM.Canner.addRecipe1(T, 16, Math.max(tData.fluid.amount / 64, 16), tEmpty, tData.fluid, NF, tData.filledContainer);
		}
		for (FluidContainerData tData : UT.Fluids.sFilled2Data.values()) {
			RM.Canner.addRecipe1(T, 16, Math.max(tData.fluid.amount / 64, 16), tData.filledContainer, NF, tData.fluid, ST.container(tData.filledContainer, T));
			if (MD.FR.mLoaded) {
			if (IL.FR_TinCapsule		.equal(tData.emptyContainer)) RM.Squeezer.addRecipe1(T, 16, Math.max(tData.fluid.amount / 64, 16),  500, tData.filledContainer, NF, tData.fluid, OM.ingot(MT.Sn));
			if (IL.FR_WaxCapsule		.equal(tData.emptyContainer)) RM.Squeezer.addRecipe1(T, 16, Math.max(tData.fluid.amount / 64, 16), 1000, tData.filledContainer, NF, tData.fluid, OM.dust(MT.WaxBee));
			if (IL.FR_RefractoryCapsule	.equal(tData.emptyContainer)) RM.Squeezer.addRecipe1(T, 16, Math.max(tData.fluid.amount / 64, 16), 1000, tData.filledContainer, NF, tData.fluid, OM.dust(MT.WaxRefractory));
			}
		}
		
		if (MD.FR.mLoaded) {
			try {
				for (ICentrifugeRecipe tRecipe : RecipeManagers.centrifugeManager.recipes()) {
					Map<ItemStack, Float> tMap = tRecipe.getAllProducts();
					ItemStack[] tOutput = new ItemStack[tMap.size()];
					if (tOutput.length > 0) {
						int i = 0;
						long[] tChances = new long[tOutput.length];
						for (Map.Entry<ItemStack, Float> tEntry : tMap.entrySet()) {
							tOutput[i] = tEntry.getKey();
							tChances[i++] = Math.max(1, (long)(10000*tEntry.getValue()));
						}
						RM.Centrifuge.addRecipe(T, new ItemStack[] {tRecipe.getInput()}, tOutput, NI, tChances, null, null, tRecipe.getProcessingTime(), 16, 0);
					}
				}
			} catch(Throwable e) {
				if (D1) e.printStackTrace(ERR);
			}
			
			try {
				for (ISqueezerRecipe tRecipe : RecipeManagers.squeezerManager.recipes()) {
					ItemStack[] tInput = tRecipe.getResources();
					if (tInput.length == 1 && UT.Fluids.getFluidForFilledItem(tInput[0], T) == null) {
						RM.Squeezer.addRecipe(T, tInput, new ItemStack[] {tRecipe.getRemnants()}, NI, new long[] {Math.max(1, (long)(10000*tRecipe.getRemnantsChance()))}, null, new FluidStack[] {tRecipe.getFluidOutput()}, tRecipe.getProcessingTime(), 16, 0);
					}
				}
			} catch(Throwable e) {
				if (D1) e.printStackTrace(ERR);
			}
		}
	}
}