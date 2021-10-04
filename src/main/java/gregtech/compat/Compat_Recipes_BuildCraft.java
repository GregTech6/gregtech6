/**
 * Copyright (c) 2021 GregTech-6 Team
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

package gregtech.compat;

import static gregapi.data.CS.*;
import static gregapi.util.CR.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.config.ConfigCategories;
import gregapi.data.ANY;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Compat_Recipes_BuildCraft extends CompatMods {
	public Compat_Recipes_BuildCraft(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing BC Recipes.");
		long tBits = DEF | DEL_OTHER_SHAPED_RECIPES | DEL_OTHER_NATIVE_RECIPES | ONLY_IF_HAS_OTHER_RECIPES;
		
		CR.shaped(ST.make(MD.BC, "woodenGearItem" , 1, 0), tBits, " X ", "X X", " X ", 'X', OP.stick.dat(ANY.Wood));
		CR.shaped(ST.make(MD.BC, "stoneGearItem"  , 1, 0), tBits, " X ", "XGX", " X ", 'X', OP.stone, 'G', OP.gear.dat(ANY.Wood));
		CR.shaped(ST.make(MD.BC, "stoneGearItem"  , 1, 0), tBits, " X ", "XGX", " X ", 'X', OP.cobblestone, 'G', OP.gear.dat(ANY.Wood));
		CR.shaped(ST.make(MD.BC, "ironGearItem"   , 1, 0), tBits, " X ", "XGX", " X ", 'X', OP.ingot.dat(ANY.Fe), 'G', OP.gear.dat(ANY.Stone));
		CR.shaped(ST.make(MD.BC, "goldGearItem"   , 1, 0), tBits, " X ", "XGX", " X ", 'X', OP.ingot.dat(MT.Au), 'G', OP.gear.dat(ANY.Fe));
		CR.shaped(ST.make(MD.BC, "diamondGearItem", 1, 0), tBits, " X ", "XGX", " X ", 'X', OP.gem.dat(ANY.Diamond), 'G', OP.gear.dat(MT.Au));
		CR.shapeless(ST.make(MD.BC, "woodenGearItem" , 1, 0), new Object[] {OP.gearGt.dat(ANY.Wood)});
		CR.shapeless(ST.make(MD.BC, "stoneGearItem"  , 1, 0), new Object[] {OP.gearGt.dat(ANY.Stone)});
		CR.shapeless(ST.make(MD.BC, "ironGearItem"   , 1, 0), new Object[] {OP.gearGt.dat(ANY.Fe)});
		CR.shapeless(ST.make(MD.BC, "goldGearItem"   , 1, 0), new Object[] {OP.gearGt.dat(MT.Au)});
		CR.shapeless(ST.make(MD.BC, "diamondGearItem", 1, 0), new Object[] {OP.gearGt.dat(ANY.Diamond)});
		
		if (MD.BC_SILICON.mLoaded) {
			for (OreDictMaterial tMat : ANY.Iron.mToThis)
			RM.Press            .addRecipe2(T, 64,  256, (tMat==MT.Enori?OP.plateGem:OP.plate).mat(tMat, 1) , ST.make(MD.BC_SILICON, "redstoneChipset", 1, 0), ST.make(MD.BC_SILICON, "redstoneChipset", 1, 1));
			RM.Press            .addRecipe2(T, 64,  512, OP.plate.mat(MT.Au, 1)                             , ST.make(MD.BC_SILICON, "redstoneChipset", 1, 0), ST.make(MD.BC_SILICON, "redstoneChipset", 1, 2));
			for (OreDictMaterial tMat : ANY.Diamond.mToThis)
			RM.Press            .addRecipe2(T,128,  512, OP.plateGem.mat(tMat, 1)                           , ST.make(MD.BC_SILICON, "redstoneChipset", 1, 0), ST.make(MD.BC_SILICON, "redstoneChipset", 1, 3));
			RM.Press            .addRecipe2(T, 64,  512, OP.plateGem.mat(MT.EnderPearl, 1)                  , ST.make(MD.BC_SILICON, "redstoneChipset", 1, 0), ST.make(MD.BC_SILICON, "redstoneChipset", 2, 4));
			for (OreDictMaterial tMat : ANY.SiO2.mToThis) {ItemStack tQuartzPlate = OP.plateGem.mat(tMat, 1); if (ST.valid(tQuartzPlate))
			RM.Press            .addRecipe2(T, 64,  768, tQuartzPlate                                       , ST.make(MD.BC_SILICON, "redstoneChipset", 1, 0), ST.make(MD.BC_SILICON, "redstoneChipset", 1, 5));}
			RM.Press            .addRecipe2(T, 64,  768, ST.make(Items.comparator, 1, W)                    , ST.make(MD.BC_SILICON, "redstoneChipset", 1, 0), ST.make(MD.BC_SILICON, "redstoneChipset", 1, 6));
			for (OreDictMaterial tMat : ANY.Emerald.mToThis)
			RM.Press            .addRecipe2(T, 64,  512, OP.plateGem.mat(tMat, 1)                           , ST.make(MD.BC_SILICON, "redstoneChipset", 1, 0), ST.make(MD.BC_SILICON, "redstoneChipset", 1, 7));
			
			RM.Press            .addRecipe2(T, 16,   64, IL.Circuit_Plate_Signalum.get(1), ST.make(MD.BC_SILICON, "redstoneChipset", 4, 0), IL.Circuit_Board_BC_Redstone.get(1));
			RM.Press            .addRecipe2(T, 16,   64, IL.Circuit_Plate_Signalum.get(1), ST.make(MD.BC_SILICON, "redstoneChipset", 4, 1), IL.Circuit_Board_BC_Iron.get(1));
			RM.Press            .addRecipe2(T, 16,   64, IL.Circuit_Plate_Signalum.get(1), ST.make(MD.BC_SILICON, "redstoneChipset", 4, 2), IL.Circuit_Board_BC_Gold.get(1));
			RM.Press            .addRecipe2(T, 16,   64, IL.Circuit_Plate_Signalum.get(1), ST.make(MD.BC_SILICON, "redstoneChipset", 4, 3), IL.Circuit_Board_BC_Diamond.get(1));
			RM.Press            .addRecipe2(T, 16,   64, IL.Circuit_Plate_Signalum.get(1), ST.make(MD.BC_SILICON, "redstoneChipset", 8, 4), IL.Circuit_Board_BC_Ender.get(1));
			RM.Press            .addRecipe2(T, 16,   64, IL.Circuit_Plate_Signalum.get(1), ST.make(MD.BC_SILICON, "redstoneChipset", 4, 5), IL.Circuit_Board_BC_Quartz.get(1));
			RM.Press            .addRecipe2(T, 16,   64, IL.Circuit_Plate_Signalum.get(1), ST.make(MD.BC_SILICON, "redstoneChipset", 4, 6), IL.Circuit_Board_BC_Comparator.get(1));
			RM.Press            .addRecipe2(T, 16,   64, IL.Circuit_Plate_Signalum.get(1), ST.make(MD.BC_SILICON, "redstoneChipset", 4, 7), IL.Circuit_Board_BC_Emerald.get(1));
		}
		
		if (MD.BC_TRANSPORT.mLoaded) {
			if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "buildcraft-void-pipe-items", T)) {
				ItemsGT.RECIPE_REMOVED_USE_TRASH_BIN_INSTEAD.add(ST.make(MD.BC_TRANSPORT, "item.buildcraftPipe.pipeitemsvoid", 1, 0));
				CR.delate(MD.BC_TRANSPORT, "item.buildcraftPipe.pipeitemsvoid", 0);
			}
			if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "buildcraft-void-pipe-fluids", T)) {
				ItemsGT.RECIPE_REMOVED_USE_TRASH_BIN_INSTEAD.add(ST.make(MD.BC_TRANSPORT, "item.buildcraftPipe.pipefluidsvoid", 1, 0));
				CR.delate(MD.BC_TRANSPORT, "item.buildcraftPipe.pipefluidsvoid", 0);
			}
			
			CR.shaped(ST.make(MD.BC_TRANSPORT, "gateCopier", 1, 0), tBits, "PGP", "PPP", " P ", 'G', MD.BC_SILICON.mLoaded ? ST.make(MD.BC_SILICON, "redstoneChipset", 1, W) : OD.itemRedstone, 'P', OP.plate.dat(ANY.Iron));
			
			CR.delate(MD.BC_TRANSPORT, "pipeWaterproof");
			for (FluidStack tWater : FL.waters(50))
			RM.Distillery   .addRecipe1(T, 16,   16, IL.Dye_Cactus.get(1), tWater, NF, ST.make(MD.BC_TRANSPORT, "pipeWaterproof", 1, 0));
			RM.ic2_extractor(IL.Dye_Cactus.get(1), ST.make(MD.BC_TRANSPORT, "pipeWaterproof", 1, 0));
			
			for (OreDictMaterial tMat : ANY.Wax.mToThis) {
			for (FluidStack tWater : FL.waters(50))
			RM.Distillery   .addRecipe1(T, 16,   16, OM.dust(tMat), tWater, NF, ST.make(MD.BC_TRANSPORT, "pipeWaterproof", 1, 0));
			RM.ic2_extractor(OM.dust(tMat), ST.make(MD.BC_TRANSPORT, "pipeWaterproof", 1, 0));
			}
			
			RM.sawing(16, 16, F, 10, ST.make(MD.BC_TRANSPORT, "item.buildcraftPipe.pipestructurecobblestone", 1, W), ST.make(MD.BC_TRANSPORT, "pipePlug", 4, 0));
			
			for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Red     ]) RM.Bath.addRecipe1(T, 0, 16, OP.wireFine.mat(MT.RedAlloy, 1), FL.mul(tDye, 1, 8, T), NF, ST.make(MD.BC_TRANSPORT, "pipeWire", 1, 0));
			for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Blue    ]) RM.Bath.addRecipe1(T, 0, 16, OP.wireFine.mat(MT.RedAlloy, 1), FL.mul(tDye, 1, 8, T), NF, ST.make(MD.BC_TRANSPORT, "pipeWire", 1, 1));
			for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Green   ]) RM.Bath.addRecipe1(T, 0, 16, OP.wireFine.mat(MT.RedAlloy, 1), FL.mul(tDye, 1, 8, T), NF, ST.make(MD.BC_TRANSPORT, "pipeWire", 1, 2));
			for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Yellow  ]) RM.Bath.addRecipe1(T, 0, 16, OP.wireFine.mat(MT.RedAlloy, 1), FL.mul(tDye, 1, 8, T), NF, ST.make(MD.BC_TRANSPORT, "pipeWire", 1, 3));
			
			RM.Assembler        .addRecipe2(T, 16,   16, ST.make(Blocks.gravel      , 1, W), ST.make(MD.BC_TRANSPORT, "item.buildcraftPipe.pipeitemscobblestone", 1, 0), ST.make(MD.BC_TRANSPORT, "item.buildcraftPipe.pipestructurecobblestone", 1, 0));
			RM.Assembler        .addRecipe2(T, 16,   16, ST.make(Blocks.cobblestone , 2, W), ST.make(Blocks.glass, 1, W), ST.make(MD.BC_TRANSPORT, "item.buildcraftPipe.pipeitemscobblestone", 1, 0));
			
			for (int i = 0; i < 16; i++) {
			RM.Assembler        .addRecipe2(T, 16,   16, ST.make(Blocks.gravel      , 1, W), ST.make(MD.BC_TRANSPORT, "item.buildcraftPipe.pipeitemscobblestone", 1, i+1), ST.make(MD.BC_TRANSPORT, "item.buildcraftPipe.pipestructurecobblestone", 1, i+1));
			RM.Assembler        .addRecipe2(T, 16,   16, ST.make(Blocks.cobblestone , 2, W), ST.make(Blocks.stained_glass, 1, i), ST.make(MD.BC_TRANSPORT, "item.buildcraftPipe.pipeitemscobblestone", 1, i+1));
			}
		}
	}
}
