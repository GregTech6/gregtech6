/**
 * Copyright (c) 2023 GregTech-6 Team
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

import gregapi.config.ConfigCategories;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregapi.data.CS.*;

@SuppressWarnings("deprecation")
public class Loader_MachineRecipes implements Runnable {
	@Override
	public void run() {
		gregapi.old.IGT_RecipeAdder RA = gregapi.old.IGT_RecipeAdder.NON_WORKING;
		
//      RA.addPrinterRecipe(OM.get(OP.plateDouble, MT.Paper, 1), UT.Fluids.make("squidink", (int)L / 4), NI, IL.Paper_Punch_Card_Empty.get(1), 100, 2);
//      RA.addPrinterRecipe(IL.Paper_Punch_Card_Empty.get(1), UT.Fluids.make("squidink", (int)L / 4), IL.Tool_DataStick.getWithName(0, "With Punch Card Data"), IL.Paper_Punch_Card_Encoded.get(1), 100, 2);
		
//      RA.addFormingPressRecipe(ST.make(Blocks.glass, 1, W), IL.Shape_Mold_Arrow.get(0), IL.Arrow_Head_Glass_Emtpy.get(1), 64, 4);
		
//      for (OreDictMaterial tMat : MT.VALUES) if (tMat.mStandardMoltenFluid != null && tMat.contains(SubTag.SOLDERING_MATERIAL)) {
//          int tMultiplier = tMat.contains(SubTag.SOLDERING_MATERIAL_BAD) ? 4 : tMat.contains(SubTag.SOLDERING_MATERIAL_GOOD) ? 1 : 2;
//          for (ItemStack tPlate : ST.array(OM.get(OP.plate, MT.Fe, 1), OM.get(OP.plate, MT.WroughtIron, 1), OM.get(OP.plate, MT.Al, 1)}) {
//              RA.addAssemblerRecipe(ST.make(Blocks.lever                        , 1, W), tPlate, tMat.getMolten((L * tMultiplier) / 2), IL.Cover_Controller.get(1)      , 800, 16);
//              RA.addAssemblerRecipe(ST.make(Blocks.redstone_torch               , 1, W), tPlate, tMat.getMolten((L * tMultiplier) / 2), IL.Cover_ActivityDetector.get(1), 800, 16);
//              RA.addAssemblerRecipe(ST.make(Blocks.heavy_weighted_pressure_plate, 1, W), tPlate, tMat.getMolten((L * tMultiplier) / 2), IL.Cover_FluidDetector.get(1)   , 800, 16);
//              RA.addAssemblerRecipe(ST.make(Blocks.light_weighted_pressure_plate, 1, W), tPlate, tMat.getMolten((L * tMultiplier) / 2), IL.Cover_ItemDetector.get(1)    , 800, 16);
//              RA.addAssemblerRecipe(ST.makeIC2("ecMeter"                        , 1   ), tPlate, tMat.getMolten((L * tMultiplier) / 2), IL.Cover_EnergyDetector.get(1)  , 800, 16);
//          }
//      }
		
//      RA.addAssemblerRecipe(ST.make(Blocks.redstone_torch, 2, W) , OM.get(OP.dust, MT.Redstone, 1)     , MT.Concrete.getMolten(L)  , ST.make(Items.repeater, 1, 0)    , 800,  1);
//      RA.addAssemblerRecipe(ST.make(Items.leather, 1, W)         , ST.make(Items.lead, 1, W)           , MT.Glue.getFluid( 50)     , ST.make(Items.name_tag, 1, 0)    , 100,  8);
//      RA.addAssemblerRecipe(OM.get(OP.plate, MT.Paper, 8)        , ST.make(Items.compass, 1, W)        , NF                        , ST.make(Items.map, 1, 0)         , 100,  8);
//      RA.addAssemblerRecipe(ST.make(MD.FR, "sturdyMachine", 1, 0), OM.get(OP.gem, MT.Diamond, 4)       , MT.Water.getFluid(5000)   , IL.FR_Casing_Hardened.get(1)     ,  64, 32);
//      RA.addAssemblerRecipe(OM.get(OP.ingot, MT.Bronze, 8)       , ST.tag(8)                           , NF                        , IL.FR_Casing_Sturdy.get(1)       ,  32, 16);
//      RA.addAssemblerRecipe(OM.get(OP.stick, MT.Wood, 1)         , ST.make(Blocks.wool, 1, W)          , MT.Creosote.getFluid(1000), ST.make(Blocks.torch, 6, 0)      , 400,  1);
//      RA.addAssemblerRecipe(OM.get(OP.plate, MT.Rubber, 3)       , ST.makeIC2("carbonMesh", 3)         , MT.Glue.getFluid(300)     , IL.Duct_Tape.get(1)              , 100, 64);
//      RA.addAssemblerRecipe(OM.get(OP.plate, MT.Paper, 3)        , ST.make(Items.leather, 1, W)        , MT.Glue.getFluid( 20)     , ST.make(Items.book, 1, 0)        ,  32,  8);
//      RA.addAssemblerRecipe(IL.Paper_Printed_Pages.get(1)        , ST.make(Items.leather, 1, W)        , MT.Glue.getFluid( 20)     , ST.make(Items.written_book, 1, 0),  32,  8);
//      RA.addAssemblerRecipe(IL.Food_Baked_Cake.get(1)            , ST.make(Items.egg, 1, 0)            , MT.Milk.getFluid(3000)    , ST.make(Items.cake, 1, 0)        , 100,  8);
		RA.addAssemblerRecipe(IL.Food_Bun_Sliced.get(2)            , OM.get(OP.ingot, MT.MeatCooked, 1)  , NF                        , IL.Food_Burger_Meat.get(1)       , 100,  4);
		RA.addAssemblerRecipe(IL.Food_Buns_Sliced.get(1)           , OM.get(OP.ingot, MT.MeatCooked, 1)  , NF                        , IL.Food_Burger_Meat.get(1)       , 100,  4);
		RA.addAssemblerRecipe(IL.Food_Bun_Sliced.get(2)            , OM.get(OP.ingot, MT.Tofu, 1)        , NF                        , IL.Food_Burger_Tofu.get(1)       , 100,  4);
		RA.addAssemblerRecipe(IL.Food_Buns_Sliced.get(1)           , OM.get(OP.ingot, MT.Tofu, 1)        , NF                        , IL.Food_Burger_Tofu.get(1)       , 100,  4);
		RA.addAssemblerRecipe(IL.Food_Bun_Sliced.get(2)            , OM.get(OP.ingot, MT.SoylentGreen, 1), NF                        , IL.Food_Burger_Soylent.get(1)    , 100,  4);
		RA.addAssemblerRecipe(IL.Food_Buns_Sliced.get(1)           , OM.get(OP.ingot, MT.SoylentGreen, 1), NF                        , IL.Food_Burger_Soylent.get(1)    , 100,  4);
		RA.addAssemblerRecipe(IL.Food_Bun_Sliced.get(2)            , IL.Food_Chum.get(1)                 , NF                        , IL.Food_Burger_Chum.get(1)       , 100,  4);
		RA.addAssemblerRecipe(IL.Food_Buns_Sliced.get(1)           , IL.Food_Chum.get(1)                 , NF                        , IL.Food_Burger_Chum.get(1)       , 100,  4);
		RA.addAssemblerRecipe(IL.Food_Bun_Sliced.get(2)            , IL.Food_Cheese_Sliced.get(3)        , NF                        , IL.Food_Burger_Cheese.get(1)     , 100,  4);
		RA.addAssemblerRecipe(IL.Food_Buns_Sliced.get(1)           , IL.Food_Cheese_Sliced.get(3)        , NF                        , IL.Food_Burger_Cheese.get(1)     , 100,  4);
		RA.addAssemblerRecipe(IL.Food_Dough_Flat.get(1)            , OM.get(OP.dust, MT.MeatCooked, 1)   , NF                        , IL.Food_Pizza_Meat_Raw.get(1)    , 100,  4);
		RA.addAssemblerRecipe(IL.Food_Dough_Flat.get(1)            , IL.Food_Cheese_Sliced.get(3)        , NF                        , IL.Food_Pizza_Cheese_Raw.get(1)  , 100,  4);
		
		
//      RM.pulverizing(ST.make(Items.item_frame, 1, W), ST.make(Items.leather, 1), OM.dust(MT.Wood, OP.stick.mMaterialAmount * 4), 95, F);
//      RM.pulverizing(ST.make(Items.bow, 1, 0), ST.make(Items.string, 3), OM.dust(MT.Wood, OP.stick.mMaterialAmount * 3), 95, F);
		
//      RA.addCannerRecipe(OM.get(OP.cell, Materials.Nitrogen, 16), ItemList.Spray_Empty.get(1), ItemList.Spray_Ice.get(1), ItemList.Cell_Empty.get(16), 1600, 2);
		
//      RA.addChemicalRecipe(OM.get(OP.dust, MT.Sn, 1), OM.get(OP.dust, MT.Saltpeter, 1), MT.Glass.getMolten(L * 6), NF, ST.make(MD.RC, "tile.railcraft.glass", 6),   50);
		
		RA.addVacuumFreezerRecipe(ST.mkic("reactorCoolantSimple", 1, W), ST.mkic("reactorCoolantSimple", 1), 100);
		RA.addVacuumFreezerRecipe(ST.mkic("reactorCoolantTriple", 1, W), ST.mkic("reactorCoolantTriple", 1), 300);
		RA.addVacuumFreezerRecipe(ST.mkic("reactorCoolantSix"   , 1, W), ST.mkic("reactorCoolantSix"   , 1), 600);
		
		if (!ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "torchesFromCoal", F))
		RA.addAssemblerRecipe(IL.Stick.get(1), ST.make(Items.coal, 1, W), IL.Torch.get(4), 400, 1);
		RA.addAssemblerRecipe(IL.Stick.get(3), ST.tag(3), ST.make(Blocks.fence, 1, 0), 300, 4);
		RA.addAssemblerRecipe(IL.Stick.get(2), OM.get(OP.ring, MT.Fe, 2), ST.make(Blocks.tripwire_hook, 1, 0), 400, 4);
		RA.addAssemblerRecipe(IL.Stick.get(2), OM.get(OP.ring, MT.WroughtIron, 2), ST.make(Blocks.tripwire_hook, 1, 0), 400, 4);
		RA.addAssemblerRecipe(IL.Stick.get(3), ST.make(Items.string, 3, W), ST.make(Items.bow, 1, 0), 400, 4);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.Au         , 2), ST.tag(2), ST.make(Blocks.light_weighted_pressure_plate, 1, 0), 200, 4);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.Fe         , 2), ST.tag(2), ST.make(Blocks.heavy_weighted_pressure_plate, 1, 0), 200, 4);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.Fe         , 6), ST.tag(6), ST.make(Items.iron_door, 1, 0), 600, 4);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.Fe         , 7), ST.tag(7), ST.make(Items.cauldron, 1, 0), 700, 4);
		RA.addAssemblerRecipe(OM.get(OP.stick, MT.Fe         , 1), ST.tag(1), ST.mkic("ironFence", 1), 100, 4);
		RA.addAssemblerRecipe(OM.get(OP.stick, MT.Fe         , 3), ST.tag(3), ST.make(Blocks.iron_bars, 4, 0), 300, 4);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.WroughtIron, 2), ST.tag(2), ST.make(Blocks.heavy_weighted_pressure_plate, 1, 0), 200, 4);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.WroughtIron, 6), ST.tag(6), ST.make(Items.iron_door, 1, 0), 600, 4);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.WroughtIron, 7), ST.tag(7), ST.make(Items.cauldron, 1, 0), 700, 4);
		RA.addAssemblerRecipe(OM.get(OP.stick, MT.WroughtIron, 1), ST.tag(1), ST.mkic("ironFence", 1), 100, 4);
		RA.addAssemblerRecipe(OM.get(OP.stick, MT.WroughtIron, 3), ST.tag(3), ST.make(Blocks.iron_bars, 4, 0), 300, 4);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.Fe         , 3), OM.get(OP.minecartWheels, MT.Fe         , 2), ST.make(Items.minecart, 1, 0), 500, 2);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.WroughtIron, 3), OM.get(OP.minecartWheels, MT.WroughtIron, 2), ST.make(Items.minecart, 1, 0), 400, 2);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.Steel      , 3), OM.get(OP.minecartWheels, MT.Steel      , 2), ST.make(Items.minecart, 1, 0), 300, 2);
		RA.addAssemblerRecipe(ST.make(Blocks.tripwire_hook, 1, 0), ST.make(Blocks.chest, 1, W), ST.make(Blocks.trapped_chest, 1, 0), 200, 4);
		RA.addAssemblerRecipe(ST.make(Blocks.stone    , 1, 0), ST.tag(4), ST.make(Blocks.stonebrick, 1, 0),  50, 4);
		RA.addAssemblerRecipe(ST.make(Blocks.sandstone, 1, 0), ST.tag(1), ST.make(Blocks.sandstone , 1, 2),  50, 4);
		RA.addAssemblerRecipe(ST.make(Blocks.sandstone, 1, 1), ST.tag(1), ST.make(Blocks.sandstone , 1, 0),  50, 4);
		RA.addAssemblerRecipe(ST.make(Blocks.sandstone, 1, 2), ST.tag(1), ST.make(Blocks.sandstone , 1, 0),  50, 4);
		RA.addAssemblerRecipe(ST.mkic("batPack", 1, W), ST.tag(1), IL.IC2_ReBattery.get(6), 800, 4);
		RA.addAssemblerRecipe(ST.make(Blocks.stone_slab, 3, 0), IL.RC_Rebar.get(1), IL.RC_Tie_Stone.get(1), 128, 8);
		RA.addAssemblerRecipe(ST.make(Blocks.stone_slab, 3, 7), IL.RC_Rebar.get(1), IL.RC_Tie_Stone.get(1), 128, 8);
		RA.addAssemblerRecipe(OM.get(OP.wireGt01, MT.Cu            , 9), OM.get(OP.plate, MT.Pb, 2), NF, IL.RC_ShuntingWire.get(4), 1600, 4);
		RA.addAssemblerRecipe(OM.get(OP.wireGt01, MT.AnnealedCopper, 9), OM.get(OP.plate, MT.Pb, 2), NF, IL.RC_ShuntingWire.get(4), 1600, 4);
		
		for (ItemStack tRail : ST.array(IL.RC_Rail_Standard.get(6), IL.RC_Rail_Adv.get(6), IL.RC_Rail_Reinforced.get(6), IL.RC_Rail_Electric.get(6), IL.RC_Rail_HS.get(6), IL.RC_Rail_Wooden.get(6))) {
			for (ItemStack tBed : ST.array(IL.RC_Bed_Wood.get(1), IL.RC_Bed_Stone.get(1))) {
				RA.addAssemblerRecipe(tBed, tRail, CR.get(tRail, NI, tRail, tRail, tBed, tRail, tRail, NI, tRail), 400, 4);
//              RA.addAssemblerRecipe(tBed, tRail, MT.Redstone.getMolten(L  ), CR.getOutput(ST.array(tRail, NI, tRail, tRail, tBed, tRail, tRail, OM.get(OP.dust, MT.Redstone, 1), tRail}), 400, 4);
//              RA.addAssemblerRecipe(tBed, tRail, MT.Redstone.getMolten(L*2), CR.getOutput(ST.array(tRail, OM.get(OP.dust, MT.Redstone, 1), tRail, tRail, tBed, tRail, tRail, OM.get(OP.dust, MT.Redstone, 1), tRail}), 400, 4);
			}
		}
		
		RA.addAssemblerRecipe(ST.mkic("carbonFiber", 2), ST.tag(2), ST.mkic("carbonMesh", 1), 800, 2);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.Al, 4), ST.mkic("generator", 1), ST.mkic("waterMill", 2), 6400, 8);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.Fe, 5), ST.make(Blocks.chest, 1, W), ST.make(Blocks.hopper, 1, 0), 800, 2);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.Fe, 5), ST.make(Blocks.trapped_chest, 1, W), ST.make(Blocks.hopper, 1, 0), 800, 2);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.WroughtIron, 5), ST.make(Blocks.chest, 1, W), ST.make(Blocks.hopper, 1, 0), 800, 2);
		RA.addAssemblerRecipe(OM.get(OP.plate, MT.WroughtIron, 5), ST.make(Blocks.trapped_chest, 1, W), ST.make(Blocks.hopper, 1, 0), 800, 2);
		RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 1), IL.Stick.get(1), ST.make(Blocks.redstone_torch, 1, 0), 400, 1);
		RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 1), OM.get(OP.plate, MT.Fe, 4), ST.make(Items.compass, 1, 0), 400, 4);
		RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 1), OM.get(OP.plate, MT.WroughtIron, 4), ST.make(Items.compass, 1, 0), 400, 4);
		RA.addAssemblerRecipe(OM.get(OP.dust, MT.Redstone, 1), OM.get(OP.plate, MT.Au, 4), ST.make(Items.clock, 1, 0), 400, 4);
	}
}
