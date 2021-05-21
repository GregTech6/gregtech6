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

import java.util.List;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.ANY;
import gregapi.data.CS.FluidsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Compat_Recipes_GalactiCraft extends CompatMods {
	public Compat_Recipes_GalactiCraft(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {
		if (MD.GC.mLoaded) {
			OUT.println("GT_Mod: Doing Galacticraft Recipes.");
			for (OreDictMaterial tMat : ANY.Steel.mToThis) {
			ItemStack
			tStack = OP.plate.mat(tMat, 1);
			if (ST.valid(tStack)) RM.Press.addRecipeX(T, 16, 256, ST.array(tStack, OP.plate     .mat(MT.Al, 1), OP.plate     .mat(MT.Bronze, 1)), ST.make(MD.GC, "item.heavyPlating", 1, 0));
			tStack = OP.compressed.mat(tMat, 1);
			if (ST.valid(tStack)) RM.Press.addRecipeX(T, 16, 256, ST.array(tStack, OP.compressed.mat(MT.Al, 1), OP.compressed.mat(MT.Bronze, 1)), ST.make(MD.GC, "item.heavyPlating", 1, 0));
			}
			
			CR.delate(MD.GC, "tile.landingPad");
			
			CR.shaped(ST.make(MD.GC, "tile.landingPad", 9, 0), CR.DEF_REV_NCC, "PPP", "DDD", 'P', OP.plate.dat(ANY.Fe   ), 'D', OP.plateDense.dat(ANY.Fe   ));
			CR.shaped(ST.make(MD.GC, "tile.landingPad", 9, 1), CR.DEF_REV_NCC, "PPP", "DDD", 'P', OP.plate.dat(ANY.Steel), 'D', OP.plateDense.dat(ANY.Steel));
			
			List<ItemStack>
			  tListA = OreDictionary.getOres("gt:complateCopper"      ), tListA1 = OreDictionary.getOres("compressedCopper"      )
			, tListB = OreDictionary.getOres("gt:complateTin"         ), tListB1 = OreDictionary.getOres("compressedTin"         )
			, tListC = OreDictionary.getOres("gt:complateAluminium"   ), tListC1 = OreDictionary.getOres("compressedAluminum"    ), tListC2 = OreDictionary.getOres("compressedAluminium")
			, tListD = OreDictionary.getOres("gt:complateSteel"       ), tListD1 = OreDictionary.getOres("compressedSteel"       )
			, tListE = OreDictionary.getOres("gt:complateBronze"      ), tListE1 = OreDictionary.getOres("compressedBronze"      )
			, tListF = OreDictionary.getOres("gt:complateIron"        ), tListF1 = OreDictionary.getOres("compressedIron"        )
			, tListG = OreDictionary.getOres("gt:complateMeteoricIron"), tListG1 = OreDictionary.getOres("compressedMeteoricIron")
			, tListH = OreDictionary.getOres("gt:complateDesh"        ), tListH1 = OreDictionary.getOres("compressedDesh"        ), tListH2 = OreDictionary.getOres("stickDesh"), tListH3 = OreDictionary.getOres("ingotDesh")
			, tListI = OreDictionary.getOres("gt:complateTitanium"    ), tListI1 = OreDictionary.getOres("compressedTitanium"    )
			;
			
			Item
			  tItemA = ST.item(MD.GC, "item.basicItem")
			, tItemG = ST.item(MD.GC, "item.meteoricIronIngot")
			, tItemH = ST.item(MD.GC_PLANETS, "item.null")
			, tItemI = ST.item(MD.GC_PLANETS, "item.itemBasicAsteroids");
			
			for (IRecipe tRecipe : CR.list()) if (tRecipe.getClass() == ShapedOreRecipe.class) {
				ItemStack tOutput = tRecipe.getRecipeOutput();
				if (ST.valid(tOutput)) {
					Object[] tInputs = ((ShapedOreRecipe)tRecipe).getInput();
					for (int i = 0; i < tInputs.length; i++) if (tInputs[i] != null) {
						Object tInput = tInputs[i];
						if (tInput instanceof List) {
							if (tListA1 == tInput) tInputs[i] = tListA; else
							if (tListB1 == tInput) tInputs[i] = tListB; else
							if (tListC1 == tInput) tInputs[i] = tListC; else
							if (tListC2 == tInput) tInputs[i] = tListC; else
							if (tListD1 == tInput) tInputs[i] = tListD; else
							if (tListE1 == tInput) tInputs[i] = tListE; else
							if (tListF1 == tInput) tInputs[i] = tListF; else
							if (tListG1 == tInput) tInputs[i] = tListG; else
							if (tListH1 == tInput) tInputs[i] = tListH; else
							if (tListI1 == tInput) tInputs[i] = tListI;
						} else if (tInput instanceof ItemStack && ST.valid((ItemStack)tInput)) {
							Item tItem = ST.item((ItemStack)tInput);
							if (tItem == tItemA) {switch(ST.meta_((ItemStack)tInput)) {
							case  6: tInputs[i] = tListA; break;
							case  7: tInputs[i] = tListB; break;
							case  8: tInputs[i] = tListC; break;
							case  9: tInputs[i] = tListD; break;
							case 10: tInputs[i] = tListE; break;
							case 11: tInputs[i] = tListF; break;
							}}else if (tItem == tItemG) {if (ST.meta_((ItemStack)tInput) == 1) tInputs[i] = tListG;
							} else if (tItem == tItemH) {switch(ST.meta_((ItemStack)tInput)) {
							case  1: tInputs[i] = tListH2; break;
							case  2: tInputs[i] = tListH3; break;
							case  5: tInputs[i] = tListH ; break;
							}}else if (tItem == tItemI) {if (ST.meta_((ItemStack)tInput) == 6) tInputs[i] = tListI;
							}
						}
					}
				}
			}
			
			for (String tFluid : FluidsGT.LIQUID_OXYGEN) if (FL.exists(tFluid)) {
			if (IL.GC_OxyTank_7.exists())
			RM.Canner.addFakeRecipe(F, ST.array(IL.GC_OxyTank_7  .wild(1)), ST.array(IL.GC_OxyTank_7  .get(1)), null, null, FL.array(FL.make(tFluid, UT.Code.units(4500, 2700, 250, T))), ZL_FS, 64, 16, 0);
			RM.Canner.addFakeRecipe(F, ST.array(IL.GC_OxyTank_1  .wild(1)), ST.array(IL.GC_OxyTank_1  .get(1)), null, null, FL.array(FL.make(tFluid, UT.Code.units( 900, 2700, 250, T))), ZL_FS, 64, 16, 0);
			RM.Canner.addFakeRecipe(F, ST.array(IL.GC_OxyTank_2  .wild(1)), ST.array(IL.GC_OxyTank_2  .get(1)), null, null, FL.array(FL.make(tFluid, UT.Code.units(1800, 2700, 250, T))), ZL_FS, 64, 16, 0);
			RM.Canner.addFakeRecipe(F, ST.array(IL.GC_OxyTank_3  .wild(1)), ST.array(IL.GC_OxyTank_3  .get(1)), null, null, FL.array(FL.make(tFluid, UT.Code.units(2700, 2700, 250, T))), ZL_FS, 64, 16, 0);    
			}
		}
		if (MD.GC_PLANETS.mLoaded) {
			OUT.println("GT_Mod: Doing Galacticraft Planets Recipes.");
			CR.delate(MD.GC_PLANETS, "item.carbonFragments");
			
			RM.smash(ST.make(MD.GC_PLANETS, "tile.denseIce", 1, W), OM.dust(MT.Ice, 4*U));
			RM.Squeezer     .addRecipe1(T,  16,  256, ST.make(MD.GC_PLANETS, "tile.denseIce", 1, W), NF, FL.Ice.make(4000), NI);
			RM.Juicer       .addRecipe1(T,  16,  256, ST.make(MD.GC_PLANETS, "tile.denseIce", 1, W), NF, FL.Ice.make(4000), NI);
			RM.Compressor   .addRecipe1(T, 256,   32, ST.make(Blocks.packed_ice, 2, W), ST.make(MD.GC_PLANETS, "tile.denseIce", 1, 0));
			
			RM.Press.addRecipe2(T, 16, 512, ST.make(MD.GC, "item.heavyPlating", 1, 0), OP.compressed.mat(MT.MeteoricIron    , 1), ST.make(MD.GC_PLANETS, "item.null", 1, 3));
			RM.Press.addRecipe2(T, 16, 512, ST.make(MD.GC, "item.heavyPlating", 1, 0), OP.plate     .mat(MT.MeteoricIron    , 1), ST.make(MD.GC_PLANETS, "item.null", 1, 3));
			RM.Press.addRecipe2(T, 16, 512, ST.make(MD.GC, "item.heavyPlating", 1, 0), OP.plate     .mat(MT.MeteoricSteel   , 1), ST.make(MD.GC_PLANETS, "item.null", 1, 3));
			RM.Press.addRecipe2(T, 16, 512, ST.make(MD.GC, "item.heavyPlating", 1, 0), OP.plate     .mat(MT.Meteorite       , 1), ST.make(MD.GC_PLANETS, "item.null", 1, 3));
			RM.Press.addRecipe2(T, 16, 512, ST.make(MD.GC_PLANETS, "item.null", 1, 3), OP.plate     .mat(MT.Desh            , 1), ST.make(MD.GC_PLANETS, "item.itemBasicAsteroids", 1, 0));
			RM.Press.addRecipe2(T, 16, 512, ST.make(MD.GC_PLANETS, "item.null", 1, 3), OP.compressed.mat(MT.Desh            , 1), ST.make(MD.GC_PLANETS, "item.itemBasicAsteroids", 1, 0));
		}
		if (MD.VULPES.mLoaded) {
			OUT.println("GT_Mod: Doing LibVulpes Recipes.");
			CR.shaped(ST.make(MD.VULPES, "libVulpescoil0", 1, 2), CR.DEF_NAC_REV_NCC | CR.DEL_OTHER_SHAPED_RECIPES, "XXX", "X X", "XXX", 'X', OP.wireGt02.dat(MT.Au));
			CR.shaped(ST.make(MD.VULPES, "libVulpescoil0", 1, 4), CR.DEF_NAC_REV_NCC | CR.DEL_OTHER_SHAPED_RECIPES, "XXX", "X X", "XXX", 'X', OP.wireGt02.dat(ANY.Cu));
			CR.shaped(ST.make(MD.VULPES, "libVulpescoil0", 1, 7), CR.DEF_NAC_REV_NCC | CR.DEL_OTHER_SHAPED_RECIPES, "XXX", "X X", "XXX", 'X', OP.wireGt02.dat(MT.Ti));
			CR.shaped(ST.make(MD.VULPES, "libVulpescoil0", 1, 9), CR.DEF_NAC_REV_NCC | CR.DEL_OTHER_SHAPED_RECIPES, "XXX", "X X", "XXX", 'X', OP.wireGt02.dat(MT.Al));
			CR.shaped(ST.make(MD.VULPES, "libVulpescoil0", 1,10), CR.DEF_NAC_REV_NCC | CR.DEL_OTHER_SHAPED_RECIPES, "XXX", "X X", "XXX", 'X', OP.wireGt02.dat(MT.Ir));
			CR.shaped(ST.make(MD.VULPES, "libVulpescoil0", 1, 7), CR.DEF_NAC_REV_NCC, "XXX", "X X", "XXX", 'X', OP.stickLong.dat(MT.Ti));
			CR.shaped(ST.make(MD.VULPES, "libVulpescoil0", 1,10), CR.DEF_NAC_REV_NCC, "XXX", "X X", "XXX", 'X', OP.stickLong.dat(MT.Ir));
		}
		if (MD.GC_ADV_ROCKETRY.mLoaded) {
			OUT.println("GT_Mod: Doing Advanced Rocketry Recipes.");
			RM.sawing(16, 16, F, 50, OP.plateGemTiny.mat(MT.Si, 2), ST.make(MD.GC_ADV_ROCKETRY, "wafer", 1, 0));
			RM.sawing(16, 16, F, 50, ST.make(MD.GC_ADV_ROCKETRY, "circuitplate", 1, 0), ST.make(MD.GC_ADV_ROCKETRY, "circuitIC", 4, 0));
			RM.sawing(16, 16, F, 50, ST.make(MD.GC_ADV_ROCKETRY, "circuitplate", 1, 1), ST.make(MD.GC_ADV_ROCKETRY, "circuitIC", 4, 2));
			RM.RollBender.addRecipe1(T, 16, 128, OP.sheetGt.mat(MT.Fe   , 2), ST.make(MD.GC_ADV_ROCKETRY, "pressureTank", 1, 0));
			RM.RollBender.addRecipe1(T, 16, 256, OP.sheetGt.mat(MT.Steel, 2), ST.make(MD.GC_ADV_ROCKETRY, "pressureTank", 1, 1));
			RM.RollBender.addRecipe1(T, 16, 512, OP.sheetGt.mat(MT.Al   , 2), ST.make(MD.GC_ADV_ROCKETRY, "pressureTank", 1, 2));
			RM.RollBender.addRecipe1(T, 64, 512, OP.sheetGt.mat(MT.Ti   , 2), ST.make(MD.GC_ADV_ROCKETRY, "pressureTank", 1, 3));
		}
		if (MD.GC_GALAXYSPACE.mLoaded) {
			OUT.println("GT_Mod: Doing Galaxy Space Recipes.");
			if (ST.valid(ST.make(MD.GC_GALAXYSPACE, "dungeonglowstone", 1, 0))) {
				RM.rem_smelting(ST.make(MD.GC_GALAXYSPACE, "item.BasicItems", 1,  8));
				RM.rem_smelting(ST.make(MD.GC_GALAXYSPACE, "item.BasicItems", 1, 16));
				
				RM.Compressor.addRecipe1(T, 16, 64, OP.plateGem.mat(MT.Coal, 9), ST.make(MD.GC_GALAXYSPACE, "item.CompressedPlates", 1, 3));
				
				RM.glowstone(ST.make(MD.GC_GALAXYSPACE, "dungeonglowstone"  , 1, 0), MT.GlowstoneCeres    );
				
				RM.generify(IL.Pill_Iodine.get(1), ST.make(MD.GC_GALAXYSPACE, "item.BasicItems", 1, 11));
				CR.delate(MD.GC_GALAXYSPACE, "item.BasicItems", 11);
				CR.shapeless(ST.make(MD.GC_GALAXYSPACE, "item.BasicItems", 1, 11), new Object[] {IL.Pill_Iodine});
			}
			if (ST.valid(ST.make(MD.GC_GALAXYSPACE, "ceresglowstone", 1, 0))) {
				RM.glowstone(ST.make(MD.GC_GALAXYSPACE, "ceresglowstone"    , 1, 0), MT.GlowstoneCeres    );
				RM.glowstone(ST.make(MD.GC_GALAXYSPACE, "ioglowstone"       , 1, 0), MT.GlowstoneIo       );
				RM.glowstone(ST.make(MD.GC_GALAXYSPACE, "enceladusglowstone", 1, 0), MT.GlowstoneEnceladus);
				RM.glowstone(ST.make(MD.GC_GALAXYSPACE, "proteusglowstone"  , 1, 0), MT.GlowstoneProteus  );
				RM.glowstone(ST.make(MD.GC_GALAXYSPACE, "plutoglowstone"    , 1, 0), MT.GlowstonePluto    );
			}
			
			CR.delate(MD.GC_GALAXYSPACE, "futureglasses", W);
			for (byte i = 0; i < 16; i++){
				CR.shaped(ST.make(MD.GC_GALAXYSPACE, "futureglasses", 1, i), CR.DEF_NAC_NCC, "GGG", "GDG", "GGG", 'G', ST.make(MD.GC_GALAXYSPACE, "futureglass", 1, W), 'D', DYE_OREDICTS[i]);
				for (FluidStack tDye : DYE_FLUIDS[i])
				RM.Bath.addRecipe1(T, 0, 16, ST.make(MD.GC_GALAXYSPACE, "futureglass", 1, W), FL.mul(tDye, 1, 16, T), NF, ST.make(MD.GC_GALAXYSPACE, "futureglasses", 1, i));
			}
			
			for (String tFluid : FluidsGT.LIQUID_OXYGEN) if (FL.exists(tFluid)) {
			RM.Canner.addFakeRecipe(F, ST.array(IL.GC_OxyTank_4  .wild(1)), ST.array(IL.GC_OxyTank_4  .get(1)), null, null, FL.array(FL.make(tFluid, UT.Code.units(3500, 2700, 250, T))), ZL_FS, 64, 16, 0);
			RM.Canner.addFakeRecipe(F, ST.array(IL.GC_OxyTank_5  .wild(1)), ST.array(IL.GC_OxyTank_5  .get(1)), null, null, FL.array(FL.make(tFluid, UT.Code.units(4000, 2700, 250, T))), ZL_FS, 64, 16, 0);
			RM.Canner.addFakeRecipe(F, ST.array(IL.GC_OxyTank_6  .wild(1)), ST.array(IL.GC_OxyTank_6  .get(1)), null, null, FL.array(FL.make(tFluid, UT.Code.units(4500, 2700, 250, T))), ZL_FS, 64, 16, 0);
			RM.Canner.addFakeRecipe(F, ST.array(IL.GC_OxyTank_Env.wild(1)), ST.array(IL.GC_OxyTank_Env.get(1)), null, null, FL.array(FL.make(tFluid, UT.Code.units( 500, 2700, 250, T))), ZL_FS, 64, 16, 0);
			}
		}
	}
}
