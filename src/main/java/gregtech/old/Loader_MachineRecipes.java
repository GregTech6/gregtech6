/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregtech.old;

import static gregapi.data.CS.*;

import gregapi.config.ConfigCategories;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.old.IGT_RecipeAdder;
import gregapi.old.IGT_RecipeAdder.NonWorkingRecipeAdder;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;

@SuppressWarnings("deprecation")
public class Loader_MachineRecipes implements Runnable {
	@Override
	public void run() {
        OUT.println("GT_Mod: Adding non-OreDict Machine Recipes.");
        IGT_RecipeAdder RA = new NonWorkingRecipeAdder();
        
//      RA.addPrinterRecipe(OM.get(OP.plateDouble, MT.Paper, 1), UT.Fluids.make("squidink", (int)L / 4), NI, IL.Paper_Punch_Card_Empty.get(1), 100, 2);
//      RA.addPrinterRecipe(IL.Paper_Punch_Card_Empty.get(1), UT.Fluids.make("squidink", (int)L / 4), IL.Tool_DataStick.getWithName(0, "With Punch Card Data"), IL.Paper_Punch_Card_Encoded.get(1), 100, 2);
        
//      RA.addMixerRecipe(OM.get(OP.dust, MT.Clay, 1), OM.get(OP.dust, MT.Stone, 3), NI, NI, MT.Water.getFluid(500), MT.Concrete.getMolten(L * 4), NI, 20, 16);
        
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1,  8)								, MT.Water.getFluid(250)												, ST.make(GregTech_API.sBlockConcretes, 1, 0)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1,  9)								, MT.Water.getFluid(250)												, ST.make(GregTech_API.sBlockConcretes, 1, 1)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1, 10)								, MT.Water.getFluid(250)												, ST.make(GregTech_API.sBlockConcretes, 1, 2)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1, 11)								, MT.Water.getFluid(250)												, ST.make(GregTech_API.sBlockConcretes, 1, 3)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1, 12)								, MT.Water.getFluid(250)												, ST.make(GregTech_API.sBlockConcretes, 1, 4)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1, 13)								, MT.Water.getFluid(250)												, ST.make(GregTech_API.sBlockConcretes, 1, 5)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1, 14)								, MT.Water.getFluid(250)												, ST.make(GregTech_API.sBlockConcretes, 1, 6)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1, 15)								, MT.Water.getFluid(250)												, ST.make(GregTech_API.sBlockConcretes, 1, 7)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1,  8)								, FL.DistW.make(250)										, ST.make(GregTech_API.sBlockConcretes, 1, 0)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1,  9)								, FL.DistW.make(250)										, ST.make(GregTech_API.sBlockConcretes, 1, 1)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1, 10)								, FL.DistW.make(250)										, ST.make(GregTech_API.sBlockConcretes, 1, 2)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1, 11)								, FL.DistW.make(250)										, ST.make(GregTech_API.sBlockConcretes, 1, 3)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1, 12)								, FL.DistW.make(250)										, ST.make(GregTech_API.sBlockConcretes, 1, 4)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1, 13)								, FL.DistW.make(250)										, ST.make(GregTech_API.sBlockConcretes, 1, 5)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1, 14)								, FL.DistW.make(250)										, ST.make(GregTech_API.sBlockConcretes, 1, 6)						, NI, NI, null, 200, 4);
//  	RA.addChemicalBathRecipe(ST.make(GregTech_API.sBlockConcretes, 1, 15)								, FL.DistW.make(250)										, ST.make(GregTech_API.sBlockConcretes, 1, 7)						, NI, NI, null, 200, 4);
    	
    	
//      RA.addFluidExtractionRecipe	(ST.make(Items.coal, 1, 1)		, OM.get(OP.dust, MT.Ash, 1)	, MT.Creosote.getFluid(100)	,  1000,  128,   4);
//  	RA.addFluidExtractionRecipe	(OM.get(OP.dust, MT.Wood, 1)			, IL.IC2_Plantball.get(1)		, MT.Creosote.getFluid(5)	,   100,   16,   4);
//  	RA.addFluidExtractionRecipe	(OM.get(OP.dust, MT.HydratedCoal, 1)	, OM.get(OP.dust, MT.Coal, 1)	, MT.Water.getFluid(100)	, 10000,   32,   4);
//  	RA.addFluidExtractionRecipe	(OM.get(OP.gem, MT.Mercury, 1)			, NI							, MT.Mercury.getFluid(1000)	, 10000,  128,   4);
//  	RA.addFluidExtractionRecipe	(OM.get(OP.dust, MT.Monazite, 1)		, NI							, MT.Helium.getFluid(200)	, 10000,   64,  64);
    	
//		RA.addFormingPressRecipe(ST.make(Blocks.glass, 1, W)													, IL.Shape_Mold_Arrow.get(0)											, IL.Arrow_Head_Glass_Emtpy	.get(1)								,  64,   4);
		
//		for (OreDictMaterial tMat : MT.VALUES) if (tMat.mStandardMoltenFluid != null && tMat.contains(SubTag.SOLDERING_MATERIAL)) {
//			int tMultiplier = tMat.contains(SubTag.SOLDERING_MATERIAL_BAD) ? 4 : tMat.contains(SubTag.SOLDERING_MATERIAL_GOOD) ? 1 : 2;
//			for (ItemStack tPlate : new ItemStack[] {OM.get(OP.plate, MT.Fe, 1), OM.get(OP.plate, MT.WroughtIron, 1), OM.get(OP.plate, MT.Al, 1)}) {
//				RA.addAssemblerRecipe(ST.make(Blocks.lever							, 1, W)	, tPlate, tMat.getMolten((L * tMultiplier) / 2), IL.Cover_Controller.get(1)			, 800, 16);
//				RA.addAssemblerRecipe(ST.make(Blocks.redstone_torch					, 1, W)	, tPlate, tMat.getMolten((L * tMultiplier) / 2), IL.Cover_ActivityDetector.get(1)		, 800, 16);
//				RA.addAssemblerRecipe(ST.make(Blocks.heavy_weighted_pressure_plate	, 1, W)	, tPlate, tMat.getMolten((L * tMultiplier) / 2), IL.Cover_FluidDetector.get(1)		, 800, 16);
//				RA.addAssemblerRecipe(ST.make(Blocks.light_weighted_pressure_plate	, 1, W)	, tPlate, tMat.getMolten((L * tMultiplier) / 2), IL.Cover_ItemDetector.get(1)			, 800, 16);
//		 		RA.addAssemblerRecipe(ST.makeIC2("ecMeter"					, 1)	, tPlate, tMat.getMolten((L * tMultiplier) / 2), IL.Cover_EnergyDetector.get(1)		, 800, 16);
//			}
//		}
		
//		RA.addAssemblerRecipe(ST.make(Blocks.redstone_torch, 2, W)											, OM.get(OP.dust, MT.Redstone, 1)		, MT.Concrete.getMolten(L)		, ST.make(Items.repeater, 1, 0)											, 800,    1);
//		RA.addAssemblerRecipe(ST.make(Items.leather, 1, W)													, ST.make(Items.lead, 1, W)										, MT.Glue.getFluid( 50)			, ST.make(Items.name_tag, 1, 0)											, 100,    8);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.Paper, 8)						, ST.make(Items.compass, 1, W)									, NF									, ST.make(Items.map, 1, 0)												, 100,    8);
//		RA.addAssemblerRecipe(OM.get(OP.dust, MT.Tantalum, 1)						, OM.get(OP.plate, MT.Manganese, 1)	, MT.Plastic.getMolten(L)		, IL.Battery_RE_ULV_Tantalum.get(1)										, 100,    4);
		RA.addAssemblerRecipe(ST.make(MD.TF, "item.charmOfLife1", 4, 0)						, ST.tag(4)						, NF									, ST.make(MD.TF, "item.charmOfLife2"			,  1,    0)	, 100,    8);
		RA.addAssemblerRecipe(ST.make(MD.TF, "item.charmOfKeeping1", 4, 0)						, ST.tag(4)						, NF									, ST.make(MD.TF, "item.charmOfKeeping2"		,  1,    0)	, 100,    8);
		RA.addAssemblerRecipe(ST.make(MD.TF, "item.charmOfKeeping2", 4, 0)						, ST.tag(4)						, NF									, ST.make(MD.TF, "item.charmOfKeeping3"		,  1,    0)	, 100,    8);
		RA.addAssemblerRecipe(ST.make(MD.TF, "item.charmOfLife2", 1, 0)						, ST.tag(1)						, NF									, ST.make(MD.TF, "item.charmOfLife1"			,  4,    0)	, 100,    8);
		RA.addAssemblerRecipe(ST.make(MD.TF, "item.charmOfKeeping2", 1, 0)						, ST.tag(1)						, NF									, ST.make(MD.TF, "item.charmOfKeeping1"		,  4,    0)	, 100,    8);
		RA.addAssemblerRecipe(ST.make(MD.TF, "item.charmOfKeeping3", 1, 0)						, ST.tag(1)						, NF									, ST.make(MD.TF, "item.charmOfKeeping2"		,  4,    0)	, 100,    8);
//    	RA.addAssemblerRecipe(IL.FR_Wax.get(6)																, ST.make(Items.string, 1, W)										, MT.Water.getFluid( 600)		, ST.make(MD.FR, "candle"						, 24,    0)	,  64,    8);
//    	RA.addAssemblerRecipe(IL.FR_Wax.get(2)																, IL.FR_Silk.get(1)												, MT.Water.getFluid( 200)		, ST.make(MD.FR, "candle"						,  8,    0)	,  16,    8);
//    	RA.addAssemblerRecipe(IL.FR_Silk.get(9)																, IL.Circuit_Integrated.getWithDamage(0, 9)						, MT.Water.getFluid( 500)		, ST.make(MD.FR, "craftingMaterial"			,  1,    3)	,  64,    8);
    	RA.addAssemblerRecipe(ST.make(MD.FR, "propolis", 5, 2)									, ST.tag(5)						, NF									, ST.make(MD.FR, "craftingMaterial"			,  1,    1)	,  16,    8);
//    	RA.addAssemblerRecipe(ST.make(MD.FR, "sturdyMachine", 1, 0)							, OM.get(OP.gem, MT.Diamond, 4)		, MT.Water.getFluid(5000)		, IL.FR_Casing_Hardened.get(1)											,  64,   32);
    	RA.addAssemblerRecipe(OM.get(OP.ingot, MT.Bronze, 8)						, ST.tag(8)						, NF									, IL.FR_Casing_Sturdy.get(1)												,  32,   16);
//    	RA.addAssemblerRecipe(OM.get(OP.stick, MT.Wood, 1)						, ST.make(Blocks.wool, 1, W)										, MT.Creosote.getFluid(1000)		, ST.make(Blocks.torch										,  6,    0)	, 400,    1);
    	RA.addAssemblerRecipe(ST.make(MD.FR, "craftingMaterial", 5, 1)							, ST.tag(5)						, NF									, OM.get(OP.gem, MT.EnderPearl	,  1      )	,  64,    8);
//    	RA.addAssemblerRecipe(ST.make(Blocks.piston, 1, W)													, IL.Circuit_Integrated.getWithDamage(0, 1)						, MT.Glue.getFluid(100)			, ST.make(Blocks.sticky_piston								,  1,    0)	, 100,    4);
//    	RA.addAssemblerRecipe(OM.get(OP.plate, MT.Rubber, 3)						, ST.makeIC2("carbonMesh", 3)								, MT.Glue.getFluid(300)			, IL.Duct_Tape.get(1)														, 100,   64);
//    	RA.addAssemblerRecipe(OM.get(OP.plate, MT.Paper, 3)						, ST.make(Items.leather, 1, W)									, MT.Glue.getFluid( 20)			, ST.make(Items.book, 1, 0)												,  32,    8);
//    	RA.addAssemblerRecipe(IL.Paper_Printed_Pages.get(1)													, ST.make(Items.leather, 1, W)									, MT.Glue.getFluid( 20)			, ST.make(Items.written_book, 1, 0)										,  32,    8);
//    	RA.addAssemblerRecipe(IL.Food_Baked_Cake.get(1)														, ST.make(Items.egg, 1, 0)										, MT.Milk.getFluid(3000)			, ST.make(Items.cake, 1, 0)												, 100,    8);
    	RA.addAssemblerRecipe(IL.Food_Bun_Sliced.get(2)														, ST.tag(2)						, NF									, IL.Food_Buns_Sliced.get(1)												, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Bread_Sliced.get(2)													, ST.tag(2)						, NF									, IL.Food_Breads_Sliced.get(1)											, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Baguette_Sliced.get(2)												, ST.tag(2)						, NF									, IL.Food_Baguettes_Sliced.get(1)											, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Buns_Sliced.get(1)													, ST.tag(1)						, NF									, IL.Food_Bun_Sliced.get(2)												, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Breads_Sliced.get(1)													, ST.tag(1)						, NF									, IL.Food_Bread_Sliced.get(2)												, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Baguettes_Sliced.get(1)												, ST.tag(1)						, NF									, IL.Food_Baguette_Sliced.get(2)											, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Bun_Sliced.get(2)														, OM.get(OP.ingot, MT.MeatCooked, 1)	, NF									, IL.Food_Burger_Meat.get(1)												, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Buns_Sliced.get(1)													, OM.get(OP.ingot, MT.MeatCooked, 1)	, NF									, IL.Food_Burger_Meat.get(1)												, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Bun_Sliced.get(2)														, OM.get(OP.ingot, MT.Tofu, 1)	, NF									, IL.Food_Burger_Tofu.get(1)												, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Buns_Sliced.get(1)													, OM.get(OP.ingot, MT.Tofu, 1)	, NF									, IL.Food_Burger_Tofu.get(1)												, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Bun_Sliced.get(2)														, OM.get(OP.ingot, MT.SoylentGreen, 1)	, NF									, IL.Food_Burger_Soylent.get(1)												, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Buns_Sliced.get(1)													, OM.get(OP.ingot, MT.SoylentGreen, 1)	, NF									, IL.Food_Burger_Soylent.get(1)												, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Bun_Sliced.get(2)														, IL.Food_Chum.get(1)												, NF									, IL.Food_Burger_Chum.get(1)												, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Buns_Sliced.get(1)													, IL.Food_Chum.get(1)												, NF									, IL.Food_Burger_Chum.get(1)												, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Bun_Sliced.get(2)														, IL.Food_Cheese_Sliced.get(3)									, NF									, IL.Food_Burger_Cheese.get(1)											, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Buns_Sliced.get(1)													, IL.Food_Cheese_Sliced.get(3)									, NF									, IL.Food_Burger_Cheese.get(1)											, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Dough_Flat.get(1)														, OM.get(OP.dust, MT.MeatCooked, 1)	, NF									, IL.Food_Pizza_Meat_Raw.get(1)											, 100,    4);
    	RA.addAssemblerRecipe(IL.Food_Dough_Flat.get(1)														, IL.Food_Cheese_Sliced.get(3)									, NF									, IL.Food_Pizza_Cheese_Raw.get(1)											, 100,    4);
    	
        
    	RA.addBrewingRecipe(OM.get(OP.dust, MT.Talc, 1)			, FluidRegistry.getFluid("oil")				, FluidRegistry.getFluid("lubricant"						), F);
    	RA.addBrewingRecipe(OM.get(OP.dust, MT.Soapstone, 1)	, FluidRegistry.getFluid("oil")				, FluidRegistry.getFluid("lubricant"						), F);
    	RA.addBrewingRecipe(OM.get(OP.dust, MT.Redstone, 1)		, FluidRegistry.getFluid("oil")				, FluidRegistry.getFluid("lubricant"						), F);
    	RA.addBrewingRecipe(OM.get(OP.dust, MT.Talc, 1)			, FluidRegistry.getFluid("creosote")		, FluidRegistry.getFluid("lubricant"						), F);
    	RA.addBrewingRecipe(OM.get(OP.dust, MT.Soapstone, 1)	, FluidRegistry.getFluid("creosote")		, FluidRegistry.getFluid("lubricant"						), F);
    	RA.addBrewingRecipe(OM.get(OP.dust, MT.Redstone, 1)		, FluidRegistry.getFluid("creosote")		, FluidRegistry.getFluid("lubricant"						), F);
    	RA.addBrewingRecipe(OM.get(OP.dust, MT.Talc, 1)			, FluidRegistry.getFluid("seedoil")			, FluidRegistry.getFluid("lubricant"						), F);
    	RA.addBrewingRecipe(OM.get(OP.dust, MT.Soapstone, 1)	, FluidRegistry.getFluid("seedoil")			, FluidRegistry.getFluid("lubricant"						), F);
    	RA.addBrewingRecipe(OM.get(OP.dust, MT.Redstone, 1)		, FluidRegistry.getFluid("seedoil")			, FluidRegistry.getFluid("lubricant"						), F);
		
        
        RM.pulverizing(ST.make(Items.flint, 1, W), OM.get(OP.dustTiny, MT.Flint, 4), OM.get(OP.dustTiny, MT.Flint, 1), 40, T);
//      RM.pulverizing(ST.make(Items.item_frame, 1, W), ST.make(Items.leather, 1), OM.dust(MT.Wood, OP.stick.mMaterialAmount * 4), 95, F);
//      RM.pulverizing(ST.make(Items.bow, 1, 0), ST.make(Items.string, 3), OM.dust(MT.Wood, OP.stick.mMaterialAmount * 3), 95, F);
        
        RA.addBoxingRecipe(IL.Food_Fries.get(1), OM.get(OP.plateDouble, MT.Paper, 1), IL.Food_Fries_Packaged.get(1), 64, 16);
        RA.addBoxingRecipe(IL.Food_PotatoChips.get(1), OM.get(OP.foil, MT.Al, 1), IL.Food_PotatoChips_Packaged.get(1), 64, 16);
        RA.addBoxingRecipe(IL.Food_ChiliChips.get(1), OM.get(OP.foil, MT.Al, 1), IL.Food_ChiliChips_Packaged.get(1), 64, 16);
		
//		RA.addCannerRecipe(GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Nitrogen, 16), ItemList.Spray_Empty.get(1), ItemList.Spray_Ice.get(1), ItemList.Cell_Empty.get(16), 1600, 2);
		
//		RA.addFusionReactorRecipe(OM.get(OP.cell, MT.D	, 1), OM.get(OP.cell, MT.T		, 1), OM.get(OP.cellPlasma, MT.He, 1), 128, -4096, 40000000);
//    	RA.addFusionReactorRecipe(OM.get(OP.cell, MT.D	, 1), OM.get(OP.cell, MT.He_3	, 1), OM.get(OP.cellPlasma, MT.He, 1), 128, -2048, 60000000);
//    	RA.addFusionReactorRecipe(OM.get(OP.cell, MT.Li	, 1), OM.get(OP.cell, MT.W		, 1), OM.get(OP.dust, MT.Ir, 1), 512, -32768, 150000000);
//		RA.addFusionReactorRecipe(OM.get(OP.cell, MT.Be	, 1), OM.get(OP.cell, MT.W		, 1), OM.get(OP.dust, MT.Pt, 1), 512, -32768, 100000000);
		
		RA.addImplosionRecipe(IL.IC2_Compressed_Coal_Chunk.get(1), 8, IL.IC2_Industrial_Diamond.get(1), OM.get(OP.dustTiny, MT.DarkAsh, 4));
        
//		RA.addDistillationRecipe(OM.get(OP.cell, MT.Oil		, 16), 32, OM.get(OP.cell, MT.Fuel, 16), OM.get(OP.cell, MT.SulfuricAcid, 16), OM.get(OP.cell, MT.Glyceryl, 1), OM.get(OP.cell, MT.Methane, 15), 4000, 64);
//      RA.addDistillationRecipe(OM.get(OP.cell, MT.Biomass	,  3), 0, OM.get(OP.cell, MT.Ethanol, 1), OM.get(OP.cell, MT.Water, 1), IL.Cell_Empty.get(1), IL.IC2_Fertilizer.get(1), 500, 64);
        
//      RA.addChemicalRecipe(OM.get(OP.dust, MT.NetherQuartz	, 3), OM.get(OP.dust, MT.Na		, 1), MT.Water.getFluid(1000)		, NF											, OM.get(OP.gem, MT.NetherQuartz	, 3)	,  500);
//      RA.addChemicalRecipe(OM.get(OP.dust, MT.CertusQuartz	, 3), OM.get(OP.dust, MT.Na		, 1), MT.Water.getFluid(1000)		, NF											, OM.get(OP.gem, MT.CertusQuartz	, 3)	,  500);
//      RA.addChemicalRecipe(OM.get(OP.dust, MT.Quartzite		, 3), OM.get(OP.dust, MT.Na		, 1), MT.Water.getFluid(1000)		, NF											, OM.get(OP.gem, MT.Quartzite		, 3)	,  500);
//      RA.addChemicalRecipe(OM.get(OP.dust, MT.NetherQuartz	, 3), OM.get(OP.dust, MT.Na		, 1), FL.DistW.make(1000)	, NF											, OM.get(OP.gem, MT.NetherQuartz	, 3)	,  500);
//      RA.addChemicalRecipe(OM.get(OP.dust, MT.CertusQuartz	, 3), OM.get(OP.dust, MT.Na		, 1), FL.DistW.make(1000)	, NF											, OM.get(OP.gem, MT.CertusQuartz	, 3)	,  500);
//      RA.addChemicalRecipe(OM.get(OP.dust, MT.Quartzite		, 3), OM.get(OP.dust, MT.Na		, 1), FL.DistW.make(1000)	, NF											, OM.get(OP.gem, MT.Quartzite		, 3)	,  500);
        
//		RA.addChemicalRecipe(OM.get(OP.dust, MT.Sn			, 1), OM.get(OP.dust, MT.Saltpeter	, 1), MT.Glass.getMolten(L * 6)		, NF											, ST.make(MD.RC, "tile.railcraft.glass", 6)			,   50);
		
    	RA.addVacuumFreezerRecipe(ST.mkic("reactorCoolantSimple", 1, W), ST.mkic("reactorCoolantSimple", 1), 100);
        RA.addVacuumFreezerRecipe(ST.mkic("reactorCoolantTriple", 1, W), ST.mkic("reactorCoolantTriple", 1), 300);
        RA.addVacuumFreezerRecipe(ST.mkic("reactorCoolantSix"	, 1, W), ST.mkic("reactorCoolantSix"	, 1), 600);
//      RA.addVacuumFreezerRecipe(OM.get(OP.cell, MT.Water, 1), OM.get(OP.cell, MT.Ice, 1), 50);
        
    	if (!ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "torchesFromCoal", F))
		RA.addAssemblerRecipe(OM.get(OP.stick, MT.Wood			, 1), ST.make(Items.coal, 1, W), ST.make(Blocks.torch, 4, 0), 400, 1);
    	RA.addAssemblerRecipe(OM.get(OP.plate, MT.Au			, 2), ST.tag(2), ST.make(Blocks.light_weighted_pressure_plate, 1, 0), 200, 4);
    	RA.addAssemblerRecipe(OM.get(OP.plate, MT.Fe			, 2), ST.tag(2), ST.make(Blocks.heavy_weighted_pressure_plate, 1, 0), 200, 4);
    	RA.addAssemblerRecipe(OM.get(OP.plate, MT.Fe			, 6), ST.tag(6), ST.make(Items.iron_door, 1, 0), 600, 4);
    	RA.addAssemblerRecipe(OM.get(OP.plate, MT.Fe			, 7), ST.tag(7), ST.make(Items.cauldron, 1, 0), 700, 4);
    	RA.addAssemblerRecipe(OM.get(OP.stick, MT.Fe			, 1), ST.tag(1), ST.mkic("ironFence", 1), 100, 4);
    	RA.addAssemblerRecipe(OM.get(OP.stick, MT.Fe			, 3), ST.tag(3), ST.make(Blocks.iron_bars, 4, 0), 300, 4);
    	RA.addAssemblerRecipe(OM.get(OP.plate, MT.WroughtIron	, 2), ST.tag(2), ST.make(Blocks.heavy_weighted_pressure_plate, 1, 0), 200, 4);
    	RA.addAssemblerRecipe(OM.get(OP.plate, MT.WroughtIron	, 6), ST.tag(6), ST.make(Items.iron_door, 1, 0), 600, 4);
    	RA.addAssemblerRecipe(OM.get(OP.plate, MT.WroughtIron	, 7), ST.tag(7), ST.make(Items.cauldron, 1, 0), 700, 4);
    	RA.addAssemblerRecipe(OM.get(OP.stick, MT.WroughtIron	, 1), ST.tag(1), ST.mkic("ironFence", 1), 100, 4);
    	RA.addAssemblerRecipe(OM.get(OP.stick, MT.WroughtIron	, 3), ST.tag(3), ST.make(Blocks.iron_bars, 4, 0), 300, 4);
    	RA.addAssemblerRecipe(OM.get(OP.stick, MT.Wood			, 3), ST.tag(3), ST.make(Blocks.fence, 1, 0), 300, 4);
    	RA.addAssemblerRecipe(OM.get(OP.stick, MT.Wood			, 2), OM.get(OP.ring, MT.Fe, 2), ST.make(Blocks.tripwire_hook, 1, 0), 400, 4);
    	RA.addAssemblerRecipe(OM.get(OP.stick, MT.Wood			, 2), OM.get(OP.ring, MT.WroughtIron, 2), ST.make(Blocks.tripwire_hook, 1, 0), 400, 4);
    	RA.addAssemblerRecipe(OM.get(OP.stick, MT.Wood			, 3), ST.make(Items.string, 3, W), ST.make(Items.bow, 1, 0), 400, 4);
    	RA.addAssemblerRecipe(OM.get(OP.plate, MT.Fe			, 3), OM.get(OP.minecartWheels, MT.Fe			, 2), ST.make(Items.minecart, 1, 0), 500, 2);
    	RA.addAssemblerRecipe(OM.get(OP.plate, MT.WroughtIron	, 3), OM.get(OP.minecartWheels, MT.WroughtIron	, 2), ST.make(Items.minecart, 1, 0), 400, 2);
    	RA.addAssemblerRecipe(OM.get(OP.plate, MT.Steel			, 3), OM.get(OP.minecartWheels, MT.Steel		, 2), ST.make(Items.minecart, 1, 0), 300, 2);
    	RA.addAssemblerRecipe(ST.make(Items.minecart, 1, 0), ST.make(Blocks.hopper			, 1, W), ST.make(Items.hopper_minecart	, 1, 0), 400, 4);
    	RA.addAssemblerRecipe(ST.make(Items.minecart, 1, 0), ST.make(Blocks.tnt			, 1, W), ST.make(Items.tnt_minecart		, 1, 0), 400, 4);
    	RA.addAssemblerRecipe(ST.make(Items.minecart, 1, 0), ST.make(Blocks.chest			, 1, W), ST.make(Items.chest_minecart		, 1, 0), 400, 4);
    	RA.addAssemblerRecipe(ST.make(Items.minecart, 1, 0), ST.make(Blocks.trapped_chest	, 1, W), ST.make(Items.chest_minecart		, 1, 0), 400, 4);
    	RA.addAssemblerRecipe(ST.make(Items.minecart, 1, 0), ST.make(Blocks.furnace		, 1, W), ST.make(Items.furnace_minecart	, 1, 0), 400, 4);
    	RA.addAssemblerRecipe(ST.make(Blocks.tripwire_hook, 1, 0), ST.make(Blocks.chest, 1, W), ST.make(Blocks.trapped_chest, 1, 0), 200, 4);
		RA.addAssemblerRecipe(ST.make(Blocks.stone				, 1, 0), ST.tag(4), ST.make(Blocks.stonebrick	, 1, 0),  50, 4);
		RA.addAssemblerRecipe(ST.make(Blocks.sandstone			, 1, 0), ST.tag(1), ST.make(Blocks.sandstone	, 1, 2),  50, 4);
		RA.addAssemblerRecipe(ST.make(Blocks.sandstone			, 1, 1), ST.tag(1), ST.make(Blocks.sandstone	, 1, 0),  50, 4);
		RA.addAssemblerRecipe(ST.make(Blocks.sandstone			, 1, 2), ST.tag(1), ST.make(Blocks.sandstone	, 1, 0),  50, 4);
//		RA.addAssemblerRecipe(ST.make(GregTech_API.sBlockMachines, 1, 6), ItemList.Circuit_Data.get(4), ItemList.Tool_Sonictron.get(1), 6400, 8);
		RA.addAssemblerRecipe(ST.mkic("waterMill", 2), ST.tag(2), ST.mkic("generator", 1), 6400, 8);
		RA.addAssemblerRecipe(ST.mkic("batPack", 1, W), ST.tag(1), IL.IC2_ReBattery.get(6), 800, 4);
		RA.addAssemblerRecipe(ST.make(Blocks.stone_slab, 3, 0), IL.RC_Rebar.get(1), IL.RC_Tie_Stone.get(1), 128, 8);
		RA.addAssemblerRecipe(ST.make(Blocks.stone_slab, 3, 7), IL.RC_Rebar.get(1), IL.RC_Tie_Stone.get(1), 128, 8);
		RA.addAssemblerRecipe(OM.get(OP.wireGt01, MT.Cu, 9), OM.get(OP.plate, MT.Pb, 2), NF, IL.RC_ShuntingWire.get(4), 1600, 4);
		RA.addAssemblerRecipe(OM.get(OP.wireGt01, MT.AnnealedCopper, 9), OM.get(OP.plate, MT.Pb, 2), NF, IL.RC_ShuntingWire.get(4), 1600, 4);
//		RA.addAssemblerRecipe(OM.get(OP.ingot, MT.Steel, 3), OM.get(OP.plate, MT.Au, 3), MT.Blaze.getMolten(L * 3), IL.RC_Rail_HS.get(8), 400, 4);
//		RA.addAssemblerRecipe(IL.RC_Rail_Standard.get(3), OM.get(OP.plate, MT.Au, 3), MT.Redstone.getMolten(L * 3), IL.RC_Rail_Adv.get(8), 400, 4);
		RA.addAssemblerRecipe(IL.RC_Rail_Standard.get(1), OM.get(OP.wireGt01, MT.Cu, 1), IL.RC_Rail_Electric.get(1), 50, 4);
		RA.addAssemblerRecipe(IL.RC_Rail_Standard.get(1), OM.get(OP.wireGt01, MT.AnnealedCopper, 1), IL.RC_Rail_Electric.get(1), 50, 4);
		RA.addAssemblerRecipe(IL.RC_Tie_Wood.get(6), OM.get(OP.plate, MT.Fe, 1), IL.RC_Rail_Wooden.get(6), 400, 4);
		RA.addAssemblerRecipe(IL.RC_Tie_Wood.get(6), OM.get(OP.plate, MT.WroughtIron, 1), IL.RC_Rail_Wooden.get(6), 400, 4);
		RA.addAssemblerRecipe(IL.RC_Tie_Wood.get(4), ST.tag(4), IL.RC_Bed_Wood.get(1), 200, 4);
		RA.addAssemblerRecipe(IL.RC_Tie_Stone.get(4), ST.tag(4), IL.RC_Bed_Stone.get(1), 200, 4);
		
		for (ItemStack tRail : new ItemStack[] {IL.RC_Rail_Standard.get(6), IL.RC_Rail_Adv.get(6), IL.RC_Rail_Reinforced.get(6), IL.RC_Rail_Electric.get(6), IL.RC_Rail_HS.get(6), IL.RC_Rail_Wooden.get(6)}) {
			for (ItemStack tBed : new ItemStack[] {IL.RC_Bed_Wood.get(1), IL.RC_Bed_Stone.get(1)}) {
				RA.addAssemblerRecipe(tBed, tRail, CR.get(tRail, NI, tRail, tRail, tBed, tRail, tRail, NI, tRail), 400, 4);
//				RA.addAssemblerRecipe(tBed, tRail, MT.Redstone.getMolten(L  ), CR.getOutput(new ItemStack[] {tRail, NI, tRail, tRail, tBed, tRail, tRail, OM.get(OP.dust, MT.Redstone, 1), tRail}), 400, 4);
//		    	RA.addAssemblerRecipe(tBed, tRail, MT.Redstone.getMolten(L*2), CR.getOutput(new ItemStack[] {tRail, OM.get(OP.dust, MT.Redstone, 1), tRail, tRail, tBed, tRail, tRail, OM.get(OP.dust, MT.Redstone, 1), tRail}), 400, 4);
			}
		}
		
    	RA.addAssemblerRecipe(ST.mkic("carbonFiber", 2), ST.tag(2), ST.mkic("carbonMesh", 1), 800, 2);
//    	RA.addAssemblerRecipe(ST.makeIC2("carbonMesh", 16), ItemList.Circuit_Integrated.getWithDamage(0, 16), ItemList.Component_LavaFilter.get(1), 1600, 8);
    	RA.addAssemblerRecipe(OM.get(OP.plate, MT.Al, 4), ST.mkic("generator", 1), ST.mkic("waterMill", 2), 6400, 8);
//	    RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Al, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1), GregTech_API.getGregTechComponent(86, 1), 800, 16);
   		RA.addAssemblerRecipe(OM.get(OP.plate, MT.Fe, 5), ST.make(Blocks.chest, 1, W), ST.make(Blocks.hopper, 1, 0), 800, 2);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.Fe, 5), ST.make(Blocks.trapped_chest, 1, W), ST.make(Blocks.hopper, 1, 0), 800, 2);
//   	RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1), ST.make(Items.comparator, 1), GregTech_API.getGregTechComponent(30, 1), 800, 16);
//	    RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Iron, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1), GregTech_API.getGregTechComponent(86, 1), 800, 16);
   		RA.addAssemblerRecipe(OM.get(OP.plate, MT.WroughtIron, 5), ST.make(Blocks.chest, 1, W), ST.make(Blocks.hopper, 1, 0), 800, 2);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.WroughtIron, 5), ST.make(Blocks.trapped_chest, 1, W), ST.make(Blocks.hopper, 1, 0), 800, 2);
//   	RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron, 1), ST.make(Items.comparator, 1), GregTech_API.getGregTechComponent(30, 1), 800, 16);
//	    RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.WroughtIron, 1), GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1), GregTech_API.getGregTechComponent(86, 1), 800, 16);
   	   	RA.addAssemblerRecipe(OM.get(OP.plate, MT.Magnalium, 2), ST.mkic("generator", 1), ST.mkic("windMill", 1), 6400, 8);
// 		RA.addAssemblerRecipe(GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Steel, 2), GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Glass, 1), GregTech_API.getGregTechComponent(81, 1), 1600, 32);
   		RA.addAssemblerRecipe(OM.get(OP.gem  , MT.EnderPearl, 1), ST.make(Items.blaze_powder, 1, 0), ST.make(Items.ender_eye, 1, 0),  400, 2);
		RA.addAssemblerRecipe(OM.get(OP.gem  , MT.EnderPearl, 6), ST.make(Items.blaze_rod   , 1, 0), ST.make(Items.ender_eye, 6, 0), 2500, 2);
	    RA.addAssemblerRecipe(OM.get(OP.dust, MT.Flint, 5), ST.make(Blocks.tnt, 3, W), ST.mkic("industrialTnt", 5), 800, 2);
		RA.addAssemblerRecipe(OM.get(OP.dust, MT.Gunpowder, 4), ST.make(Blocks.sand, 4, W), ST.make(Blocks.tnt, 1, 0), 400, 1);
//		RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 4), OM.get(OP.dust, MT.Glowstone				, 4), ST.make(Blocks.redstone_lamp, 1, 0), 400, 1);
//		RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 4), OM.get(OP.dust, MT.GlowstoneCeres		, 4), ST.make(Blocks.redstone_lamp, 1, 0), 400, 1);
//		RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 4), OM.get(OP.dust, MT.GlowstoneIo			, 4), ST.make(Blocks.redstone_lamp, 1, 0), 400, 1);
//		RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 4), OM.get(OP.dust, MT.GlowstoneEnceladus	, 4), ST.make(Blocks.redstone_lamp, 1, 0), 400, 1);
//		RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 4), OM.get(OP.dust, MT.GlowstoneProteus		, 4), ST.make(Blocks.redstone_lamp, 1, 0), 400, 1);
//		RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 4), OM.get(OP.dust, MT.GlowstonePluto		, 4), ST.make(Blocks.redstone_lamp, 1, 0), 400, 1);
		RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 1), OM.get(OP.stick, MT.Wood, 1), ST.make(Blocks.redstone_torch, 1, 0), 400, 1);
    	RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 1), OM.get(OP.plate, MT.Fe, 4), ST.make(Items.compass, 1, 0), 400, 4);
    	RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 1), OM.get(OP.plate, MT.WroughtIron, 4), ST.make(Items.compass, 1, 0), 400, 4);
    	RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 1), OM.get(OP.plate, MT.Au, 4), ST.make(Items.clock, 1, 0), 400, 4);
    	RA.addAssemblerRecipe(OM.get(OP.stick, MT.Wood, 1), OM.get(OP.dust, MT.S, 1), ST.make(Blocks.torch, 2, 0), 400, 1);
    	RA.addAssemblerRecipe(OM.get(OP.stick, MT.Wood, 1), OM.get(OP.dust, MT.Phosphorus, 1), ST.make(Blocks.torch, 6, 0), 400, 1);
    	RA.addAssemblerRecipe(ST.mkic("tinCableItem", 1)		, OM.get(OP.ingot, MT.Rubber, 1), ST.mkic("insulatedTinCableItem", 1), 100, 2);
	    RA.addAssemblerRecipe(ST.mkic("copperCableItem", 1)	, OM.get(OP.ingot, MT.Rubber, 1), ST.mkic("insulatedCopperCableItem", 1), 100, 2);
    	RA.addAssemblerRecipe(ST.mkic("goldCableItem", 1)		, OM.get(OP.ingot, MT.Rubber, 2), ST.mkic("insulatedGoldCableItem", 1), 200, 2);
    	RA.addAssemblerRecipe(ST.mkic("ironCableItem", 1)		, OM.get(OP.ingot, MT.Rubber, 3), ST.mkic("insulatedIronCableItem", 1), 300, 2);
    	
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadSword	, MT.Wood		, 1), OM.get(OP.stick, MT.Wood, 1), ST.make(Items.wooden_sword, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadSword	, MT.Stone		, 1), OM.get(OP.stick, MT.Wood, 1), ST.make(Items.stone_sword, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadSword	, MT.Fe			, 1), OM.get(OP.stick, MT.Wood, 1), ST.make(Items.iron_sword, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadSword	, MT.Au			, 1), OM.get(OP.stick, MT.Wood, 1), ST.make(Items.golden_sword, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadSword	, MT.Diamond	, 1), OM.get(OP.stick, MT.Wood, 1), ST.make(Items.diamond_sword, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadSword	, MT.Bronze		, 1), OM.get(OP.stick, MT.Wood, 1), IL.Tool_Sword_Bronze.getUndamaged(1), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadSword	, MT.Steel		, 1), OM.get(OP.stick, MT.Wood, 1), IL.Tool_Sword_Steel.getUndamaged(1), 100, 16);
    	
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadPickaxe	, MT.Wood		, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.wooden_pickaxe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadPickaxe	, MT.Stone		, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.stone_pickaxe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadPickaxe	, MT.Fe			, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.iron_pickaxe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadPickaxe	, MT.Au			, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.golden_pickaxe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadPickaxe	, MT.Diamond	, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.diamond_pickaxe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadPickaxe	, MT.Bronze		, 1), OM.get(OP.stick, MT.Wood, 2), IL.Tool_Pickaxe_Bronze.getUndamaged(1), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadPickaxe	, MT.Steel		, 1), OM.get(OP.stick, MT.Wood, 2), IL.Tool_Pickaxe_Steel.getUndamaged(1), 100, 16);
    	
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadShovel	, MT.Wood		, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.wooden_shovel, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadShovel	, MT.Stone		, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.stone_shovel, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadShovel	, MT.Fe			, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.iron_shovel, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadShovel	, MT.Au			, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.golden_shovel, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadShovel	, MT.Diamond	, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.diamond_shovel, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadShovel	, MT.Bronze		, 1), OM.get(OP.stick, MT.Wood, 2), IL.Tool_Shovel_Bronze.getUndamaged(1), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadShovel	, MT.Steel		, 1), OM.get(OP.stick, MT.Wood, 2), IL.Tool_Shovel_Steel.getUndamaged(1), 100, 16);
    	
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadAxe		, MT.Wood		, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.wooden_axe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadAxe		, MT.Stone		, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.stone_axe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadAxe		, MT.Fe			, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.iron_axe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadAxe		, MT.Au			, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.golden_axe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadAxe		, MT.Diamond	, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.diamond_axe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadAxe		, MT.Bronze		, 1), OM.get(OP.stick, MT.Wood, 2), IL.Tool_Axe_Bronze.getUndamaged(1), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadAxe		, MT.Steel		, 1), OM.get(OP.stick, MT.Wood, 2), IL.Tool_Axe_Steel.getUndamaged(1), 100, 16);
    	
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadHoe		, MT.Wood		, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.wooden_hoe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadHoe		, MT.Stone		, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.stone_hoe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadHoe		, MT.Fe			, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.iron_hoe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadHoe		, MT.Au			, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.golden_hoe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadHoe		, MT.Diamond	, 1), OM.get(OP.stick, MT.Wood, 2), ST.make(Items.diamond_hoe, 1, 0), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadHoe		, MT.Bronze		, 1), OM.get(OP.stick, MT.Wood, 2), IL.Tool_Hoe_Bronze.getUndamaged(1), 100, 16);
    	RA.addAssemblerRecipe(OM.get(OP.toolHeadHoe		, MT.Steel		, 1), OM.get(OP.stick, MT.Wood, 2), IL.Tool_Hoe_Steel.getUndamaged(1), 100, 16);
	}
}
