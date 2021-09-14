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

package gregtech.loaders.b;

import static gregapi.data.CS.*;

import gregapi.data.CS.FluidsGT;
import gregapi.data.FL;
import gregapi.data.FM;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.OM;
import gregapi.util.UT;


public class Loader_Fuels implements Runnable {
	@Override
	public void run() {
		for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Charcoal, MT.Coal, MT.CoalCoke, MT.Lignite, MT.LigniteCoke, MT.Anthracite, MT.Prismane, MT.Lonsdaleite, MT.PetCoke, MT.Peat, MT.PeatBituminous}) {
		FM.FluidBed     .addRecipe1(T, -1, (tMat.mFurnaceBurnTime * 3 * EU_PER_FURNACE_TICK) *  9, OP.blockDust.mat(tMat, 1), FL.Calcite.make(648), NF, OM.dust(tMat.mTargetBurning.mMaterial, UT.Code.units(tMat.mTargetBurning.mAmount, U*2, U*9, F)));
		FM.FluidBed     .addRecipe1(T, -1, (tMat.mFurnaceBurnTime * 3 * EU_PER_FURNACE_TICK)     , OP.dust     .mat(tMat, 1), FL.Calcite.make( 72), NF, OM.dust(tMat.mTargetBurning.mMaterial, UT.Code.units(tMat.mTargetBurning.mAmount, U*2, U  , F)));
		FM.FluidBed     .addRecipe1(T, -1, (tMat.mFurnaceBurnTime * 3 * EU_PER_FURNACE_TICK) /  4, OP.dustSmall.mat(tMat, 1), FL.Calcite.make( 18), NF, OM.dust(tMat.mTargetBurning.mMaterial, UT.Code.units(tMat.mTargetBurning.mAmount, U*2, U4 , F)));
		FM.FluidBed     .addRecipe1(T, -1, (tMat.mFurnaceBurnTime * 3 * EU_PER_FURNACE_TICK) /  9, OP.dustTiny .mat(tMat, 1), FL.Calcite.make(  8), NF, OM.dust(tMat.mTargetBurning.mMaterial, UT.Code.units(tMat.mTargetBurning.mAmount, U*2, U9 , F)));
		FM.FluidBed     .addRecipe1(T, -1, (tMat.mFurnaceBurnTime * 3 * EU_PER_FURNACE_TICK) / 72, OP.dustDiv72.mat(tMat, 1), FL.Calcite.make(  1), NF, OM.dust(tMat.mTargetBurning.mMaterial, UT.Code.units(tMat.mTargetBurning.mAmount, U*2, U72, F)));
		}
		
		FM.Burn         .addRecipe0(T, - 16, 48, FL.Oil_ExtraHeavy.make(1)                   , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 16, 36, FL.Oil_Heavy.make(1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 16, 24, FL.Oil_Medium.make(1)                       , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 16, 18, FL.Oil_Light.make(1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 16, 24, FL.Oil_Normal.make(1)                       , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 16, 18, FL.Oil_Soulsand.make(1)                     , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 16,  2, FL.Oil_Creosote.make(1)                     , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 16,  2, FL.Biomass.make(1)                          , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, - 16,  2, FL.BiomassIC2.make(1)                       , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  8,  1, FL.Oil_Whale.make(1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  4,  1, FL.Oil_Fish.make(1)                         , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  4,  1, FL.Oil_Sunflower.make(1)                    , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  4,  1, FL.Oil_Olive.make(1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  4,  1, FL.Oil_Nut.make(1)                          , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  2,  1, FL.Oil_Hemp.make(1)                         , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  2,  1, FL.Oil_Lin.make(1)                          , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Burn         .addRecipe0(T, -  2,  1, FL.Oil_Seed.make(1)                         , FL.CarbonDioxide.make(1), ZL_IS);
		if (FL.Oil_Canola.exists())
		FM.Burn         .addRecipe0(T, -  2,  1, FL.Oil_Canola.make(1)                       , FL.CarbonDioxide.make(1), ZL_IS);
		if (FL.Oil_Plant.exists())
		FM.Burn         .addRecipe0(T, -  1,  1, FL.Oil_Plant.make(1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		if (FL.Oil_Heavy2.exists())
		FM.Burn         .addRecipe0(T, - 16, 36, FL.Oil_Heavy2.make(1)                       , FL.CarbonDioxide.make(1), ZL_IS);
		if (FL.Oil_HotCrude.exists())
		FM.Burn         .addRecipe0(T, - 16, 24, FL.Oil_HotCrude.make(1)                     , FL.CarbonDioxide.make(1), ZL_IS);
		if (FL.Oil_Light2.exists())
		FM.Burn         .addRecipe0(T, - 16, 18, FL.Oil_Light2.make(1)                       , FL.CarbonDioxide.make(1), ZL_IS);
		
		FM.Burn         .addRecipe0(T, -  4,  1, FL.Glue.make(1)                             , FL.CarbonDioxide.make(1), ZL_IS);
		
		if (FL.JetFuel.exists()) {
		FM.Burn         .addRecipe0(T, -128,  9, FL.JetFuel.make(1)                          , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, -128, 12, FL.JetFuel.make(1)                          , FL.CarbonDioxide.make(1), ZL_IS);
		}
		if (FL.exists("kerosene")) {
		FM.Burn         .addRecipe0(T, - 64,  5, FL.make("kerosene", 1)                      , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  7, FL.make("kerosene", 1)                      , FL.CarbonDioxide.make(1), ZL_IS);
		}
		FM.Burn         .addRecipe0(T, - 64,  5, FL.make("kerosine", 1)                      , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  7, FL.make("kerosine", 1)                      , FL.CarbonDioxide.make(1), ZL_IS);
		
		if (FL.exists("gasoline")) {
		FM.Burn         .addRecipe0(T, - 64,  5, FL.make("gasoline", 1)                      , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  7, FL.make("gasoline", 1)                      , FL.CarbonDioxide.make(1), ZL_IS);
		}
		FM.Burn         .addRecipe0(T, - 64,  5, FL.make("petrol", 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  7, FL.make("petrol", 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		
		FM.Burn         .addRecipe0(T, - 64,  5, FL.make("diesel", 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  7, FL.make("diesel", 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		
		FM.Burn         .addRecipe0(T, - 64,  6, FL.Fuel.make(1)                             , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  8, FL.Fuel.make(1)                             , FL.CarbonDioxide.make(1), ZL_IS);
		
		FM.Burn         .addRecipe0(T, - 64,  9, FL.make("nitrofuel", 1)                     , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64, 12, FL.make("nitrofuel", 1)                     , FL.CarbonDioxide.make(1), ZL_IS);
		
		for (String tAlcohol : FluidsGT.RUM) if (FL.exists(tAlcohol)) {
		FM.Burn         .addRecipe0(T, - 16,  6, FL.make(tAlcohol, 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16,  9, FL.make(tAlcohol, 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		}
		for (String tAlcohol : FluidsGT.WHISKEY) if (FL.exists(tAlcohol)) {
		FM.Burn         .addRecipe0(T, - 16,  6, FL.make(tAlcohol, 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16,  9, FL.make(tAlcohol, 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		}
		for (String tAlcohol : FluidsGT.VINEGAR) if (FL.exists(tAlcohol)) {
		FM.Burn         .addRecipe0(T, - 16,  6, FL.make(tAlcohol, 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16,  9, FL.make(tAlcohol, 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		}
		for (String tAlcohol : new String[] {"vodka", "binnie.vodka"}) if (FL.exists(tAlcohol)) {
		FM.Burn         .addRecipe0(T, - 16,  6, FL.make(tAlcohol, 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16,  9, FL.make(tAlcohol, 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		}
		for (String tAlcohol : new String[] {"potion.dragonblood", "potion.goldencider", "potion.notchesbrew"}) if (FL.exists(tAlcohol)) {
		FM.Burn         .addRecipe0(T, - 16, 12, FL.make(tAlcohol, 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16, 18, FL.make(tAlcohol, 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		}
		
		FM.Burn         .addRecipe0(T, - 16,  9, FL.BioEthanol.make(1)                       , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16, 12, FL.BioEthanol.make(1)                       , FL.CarbonDioxide.make(1), ZL_IS);
		
		
		FM.Burn         .addRecipe0(T, - 16,  9, FL.make("ethanol", 1)                       , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16, 12, FL.make("ethanol", 1)                       , FL.CarbonDioxide.make(1), ZL_IS);
		
		if (FL.Reikanol.exists()) {
		FM.Burn         .addRecipe0(T, - 16,  9, FL.Reikanol.make(1)                         , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16, 12, FL.Reikanol.make(1)                         , FL.CarbonDioxide.make(1), ZL_IS);
		}
		if (FL.BioDiesel.exists()) {
		FM.Burn         .addRecipe0(T, - 16,  9, FL.BioDiesel.make(1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16, 12, FL.BioDiesel.make(1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		}
		if (FL.BioFuel.exists()) {
		FM.Burn         .addRecipe0(T, - 64,  9, FL.BioFuel.make(1)                          , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64, 12, FL.BioFuel.make(1)                          , FL.CarbonDioxide.make(1), ZL_IS);
		}
		if (FL.exists("hootch")) {
		FM.Burn         .addRecipe0(T, - 16,  5, FL.make("hootch", 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 16,  6, FL.make("hootch", 1)                        , FL.CarbonDioxide.make(1), ZL_IS);
		}
		if (FL.exists("fire_water")) {
		FM.Burn         .addRecipe0(T, - 32,  9, FL.make("fire_water", 1)                    , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 32, 10, FL.make("fire_water", 1)                    , FL.CarbonDioxide.make(1), ZL_IS);
		}
		if (FL.exists("rocket_fuel")) {
		FM.Burn         .addRecipe0(T, - 64,  4, FL.make("rocket_fuel", 1)                   , FL.CarbonDioxide.make(1), ZL_IS);
		FM.Engine       .addRecipe0(T, - 64,  5, FL.make("rocket_fuel", 1)                   , FL.CarbonDioxide.make(1), ZL_IS);
		}
		
		
		
		FM.Burn         .addRecipe0(T, - 16,  1, FL.make("hydrogen", 2)                      , FL.Water.make(3), ZL_IS);
		FM.Gas          .addRecipe0(T, - 16,  2, FL.make("hydrogen", 2)                      , FL.Water.make(3), ZL_IS);
		
		FM.Burn         .addRecipe0(T, - 64, 24, FL.make("methane", 5)                       , FL.Water.make(6), FL.CarbonDioxide.make(3));
		FM.Burn         .addRecipe0(T, - 64, 24, FL.make("gas_natural_gas", 5)               , FL.Water.make(6), FL.CarbonDioxide.make(3));
		FM.Gas          .addRecipe0(T, - 64, 30, FL.make("methane", 5)                       , FL.Water.make(6), FL.CarbonDioxide.make(3));
		FM.Gas          .addRecipe0(T, - 64, 30, FL.make("gas_natural_gas", 5)               , FL.Water.make(6), FL.CarbonDioxide.make(3));
		if (FL.exists("naturalgas")) {
		FM.Burn         .addRecipe0(T, - 64, 24, FL.make("naturalgas", 5)                    , FL.Water.make(6), FL.CarbonDioxide.make(3));
		FM.Gas          .addRecipe0(T, - 64, 30, FL.make("naturalgas", 5)                    , FL.Water.make(6), FL.CarbonDioxide.make(3));
		}
		if (FL.exists("gas.natural")) {
		FM.Burn         .addRecipe0(T, - 64, 24, FL.make("gas.natural", 5)                   , FL.Water.make(6), FL.CarbonDioxide.make(3));
		FM.Gas          .addRecipe0(T, - 64, 30, FL.make("gas.natural", 5)                   , FL.Water.make(6), FL.CarbonDioxide.make(3));
		}
		if (FL.exists("ic2biogas")) {
		FM.Burn         .addRecipe0(T, - 64, 24, FL.make("ic2biogas", 20)                    , FL.Water.make(6), FL.CarbonDioxide.make(3));
		FM.Gas          .addRecipe0(T, - 64, 30, FL.make("ic2biogas", 20)                    , FL.Water.make(6), FL.CarbonDioxide.make(3));
		}
		if (FL.LPG.exists()) {
		FM.Burn         .addRecipe0(T, - 64, 42, FL.LPG.make(7)                              , FL.Water.make(7), FL.CarbonDioxide.make(6));
		}
		
		FM.Burn         .addRecipe0(T, - 64, 42, FL.make("butane", 7)                        , FL.Water.make(7), FL.CarbonDioxide.make(6));
		FM.Gas          .addRecipe0(T, - 64, 56, FL.make("butane", 7)                        , FL.Water.make(7), FL.CarbonDioxide.make(6));
		
		FM.Burn         .addRecipe0(T, - 64, 30, FL.make("propane", 5)                       , FL.Water.make(5), FL.CarbonDioxide.make(4));
		FM.Gas          .addRecipe0(T, - 64, 40, FL.make("propane", 5)                       , FL.Water.make(5), FL.CarbonDioxide.make(4));
		
		FM.Burn         .addRecipe0(T, - 64,  4, FL.make("ethylene", 1)                      , FL.Water.make(1), FL.CarbonDioxide.make(1));
		FM.Gas          .addRecipe0(T, - 64,  5, FL.make("ethylene", 1)                      , FL.Water.make(1), FL.CarbonDioxide.make(1));
		
		FM.Burn         .addRecipe0(T, - 64,  3, FL.make("propylene", 1)                     , FL.Water.make(1), FL.CarbonDioxide.make(1));
		FM.Gas          .addRecipe0(T, - 64,  4, FL.make("propylene", 1)                     , FL.Water.make(1), FL.CarbonDioxide.make(1));
		
		FM.Hot          .addRecipe0(T, - 16,  6, FL.Blaze.make(1)                            , NF, ZL_IS);
		FM.Hot          .addRecipe0(T, - 16, EU_PER_LAVA/16, FL.Lava.make(1)                 , FL.Lava_Pahoehoe.make(1), ZL_IS);
		if (FL.Water_Hot.exists())
		FM.Hot          .addRecipe0(T, -  2,  1, FL.Water_Hot.make(1)                        , FL.Water.make(1), ZL_IS);
		if (FL.Water_Boiling.exists())
		FM.Hot          .addRecipe0(T, -  2,  1, FL.Water_Boiling.make(1)                    , FL.Water.make(1), ZL_IS);
		
		FM.Hot          .addRecipe0(T, -  1, EU_PER_COOLANT         , FL.Coolant_IC2_Hot.make(1)     , FL.Coolant_IC2.make(1)       , ZL_IS);
		FM.Hot          .addRecipe0(T, -  1, EU_PER_SODIUM          , FL.Hot_Molten_Sodium.make(1)   , FL.amount(MT.Na.mLiquid, 1)  , ZL_IS);
		FM.Hot          .addRecipe0(T, -  1, EU_PER_TIN             , FL.Hot_Molten_Tin.make(1)      , FL.amount(MT.Sn.mLiquid, 1)  , ZL_IS);
		FM.Hot          .addRecipe0(T, -  1, EU_PER_HEAVY_WATER     , FL.Hot_Heavy_Water.make(1)     , FL.amount(MT.D2O.mLiquid, 1) , ZL_IS);
		FM.Hot          .addRecipe0(T, -  1, EU_PER_SEMI_HEAVY_WATER, FL.Hot_Semi_Heavy_Water.make(1), FL.amount(MT.HDO.mLiquid, 1) , ZL_IS);
		FM.Hot          .addRecipe0(T, -  1, EU_PER_TRITIATED_WATER , FL.Hot_Tritiated_Water.make(1) , FL.amount(MT.T2O.mLiquid, 1) , ZL_IS);
		FM.Hot          .addRecipe0(T, -  1, EU_PER_CO2             , FL.Hot_Carbon_Dioxide.make(1)  , FL.amount(MT.CO2.mGas, 1)    , ZL_IS);
		FM.Hot          .addRecipe0(T, -  1, EU_PER_HELIUM          , FL.Hot_Helium.make(1)          , FL.amount(MT.He.mGas, 1)     , ZL_IS);
		FM.Hot          .addRecipe0(T, -  1, EU_PER_LICL            , FL.Hot_Molten_LiCl.make(1)     , FL.amount(MT.LiCl.mLiquid, 1), ZL_IS);
		
		FM.Turbine      .addRecipe0(T, - 16,  5, FL.Steam.make(160)                          , FL.DistW.make(1), ZL_IS);
//      FM.Turbine      .addRecipe0(T, - 10,  5, FL.Steam_IC2.make(100)                      , FL.DistW.make(1), ZL_IS);
//      FM.Turbine      .addRecipe0(T, - 30,  5, FL.Steam_IC2_Superheated.make(100)          , FL.DistW.make(1), ZL_IS);
		
		
		
//      FM.Magic        .addRecipe0(T, - 64, 64, FL.make("mercury", 1));
		
		
//      new Recipe(new ItemStack(Items.lava_bucket), new ItemStack(Blocks.obsidian), OM.get(OP.ingot, MT.Copper, 1), OM.get(OP.ingot, MT.Tin, 1), OM.get(OP.ingot, MT.Electrum, 1), 30, 2);
		
//      RecipeMap.sSmallNaquadahReactorFuels.addRecipe(T, ST.array(OM.get(OP.bolt , MT.Nq_528, 1)}, ST.array(OM.get(OP.bolt , MT.Nq     , 1)}, null, null, null, null, 0, 0,  25000);
//      RecipeMap.sLargeNaquadahReactorFuels.addRecipe(T, ST.array(OM.get(OP.ingot, MT.Nq_528, 1)}, ST.array(OM.get(OP.ingot, MT.Nq     , 1)}, null, null, null, null, 0, 0, 200000);
//      RecipeMap.sFluidNaquadahReactorFuels.addRecipe(T, ST.array(OM.get(OP.cell , MT.Nq_528, 1)}, ST.array(OM.get(OP.cell , MT.Empty  , 1)}, null, null, null, null, 0, 0, 200000);
		
//      RA.addFuel(GT_ModHandler.getModItem(ModIDs.TC, "ItemResource", 1, 4)                , null,      4, 5);
//      RA.addFuel(new ItemStack(Items.experience_bottle, 1)                                , null,     10, 5);
//      RA.addFuel(new ItemStack(Items.ghast_tear, 1)                                       , null,    500, 5);
//      RA.addFuel(new ItemStack(Blocks.beacon, 1)                                          , null, MT.NetherStar.mFuelPower * 2, MT.NetherStar.mFuelType);
	}
}
