package gregtech.loaders.b;

import static gregapi.data.CS.*;

import gregapi.data.CS.FluidsGT;
import gregapi.data.FL;
import gregapi.data.FM;
import gregapi.util.UT;


public class Loader_Fuels implements Runnable {
	@Override
	public void run() {
		OUT.println("GT_Mod: Initializing various Fuels.");
		
		FM.Burn			.addRecipe0(T, - 64,  1, UT.Fluids.make("liquid_extra_heavy_oil", 1)		, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, - 48,  1, UT.Fluids.make("liquid_heavy_oil", 1)				, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, - 32,  1, UT.Fluids.make("liquid_medium_oil", 1)				, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, - 24,  1, UT.Fluids.make("liquid_light_oil", 1)				, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, - 32,  1, UT.Fluids.make("oil", 1)							, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, - 16,  1, UT.Fluids.make("creosote", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, - 16,  1, UT.Fluids.make("biomass", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, - 16,  1, UT.Fluids.make("ic2biomass", 1)					, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, -  4,  1, UT.Fluids.make("glue", 1)							, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, -  4,  1, FL.Oil_Fish.make(1)								, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, -  4,  1, FL.Oil_Sunflower.make(1)							, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, -  4,  1, FL.Oil_Olive.make(1)								, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, -  4,  1, FL.Oil_Nut.make(1)									, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, -  2,  1, FL.Oil_Hemp.make(1)								, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, -  2,  1, FL.Oil_Lin.make(1)									, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Burn			.addRecipe0(T, -  2,  1, FL.Oil_Seed.make(1)								, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		if (FL.Oil_Canola.exists())
		FM.Burn			.addRecipe0(T, -  2,  1, FL.Oil_Canola.make(1)								, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		if (FL.Oil_Plant.exists())
		FM.Burn			.addRecipe0(T, -  1,  1, FL.Oil_Plant.make(10)								, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		if (UT.Fluids.exists("heavyoil"))
		FM.Burn			.addRecipe0(T, - 48,  1, UT.Fluids.make("heavyoil", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		if (UT.Fluids.exists("hotcrude"))
		FM.Burn			.addRecipe0(T, - 32,  1, UT.Fluids.make("hotcrude", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		if (UT.Fluids.exists("lightoil"))
		FM.Burn			.addRecipe0(T, - 24,  1, UT.Fluids.make("lightoil", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		
		if (UT.Fluids.exists("kerosene")) {
		FM.Burn			.addRecipe0(T, - 64,  5, UT.Fluids.make("kerosene", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 64,  7, UT.Fluids.make("kerosene", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		}
		FM.Burn			.addRecipe0(T, - 64,  5, UT.Fluids.make("kerosine", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 64,  7, UT.Fluids.make("kerosine", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		
		if (UT.Fluids.exists("gasoline")) {
		FM.Burn			.addRecipe0(T, - 64,  5, UT.Fluids.make("gasoline", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 64,  7, UT.Fluids.make("gasoline", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		}
		FM.Burn			.addRecipe0(T, - 64,  5, UT.Fluids.make("petrol", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 64,  7, UT.Fluids.make("petrol", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		
		FM.Burn			.addRecipe0(T, - 64,  5, UT.Fluids.make("diesel", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 64,  7, UT.Fluids.make("diesel", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		
		FM.Burn			.addRecipe0(T, - 64,  6, UT.Fluids.make("fuel", 1)							, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 64,  8, UT.Fluids.make("fuel", 1)							, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		
		FM.Burn			.addRecipe0(T, - 64,  9, UT.Fluids.make("nitrofuel", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 64, 12, UT.Fluids.make("nitrofuel", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		
		if (UT.Fluids.exists("lpg")) {
		FM.Burn			.addRecipe0(T, - 64,  8, UT.Fluids.make("lpg", 1)							, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 64, 10, UT.Fluids.make("lpg", 1)							, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		}
		for (String tAlcohol : FluidsGT.RUM) if (UT.Fluids.exists(tAlcohol)) {
		FM.Burn			.addRecipe0(T, - 16,  6, UT.Fluids.make(tAlcohol, 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 16,  9, UT.Fluids.make(tAlcohol, 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		}
		for (String tAlcohol : FluidsGT.WHISKEY) if (UT.Fluids.exists(tAlcohol)) {
		FM.Burn			.addRecipe0(T, - 16,  6, UT.Fluids.make(tAlcohol, 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 16,  9, UT.Fluids.make(tAlcohol, 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		}
		for (String tAlcohol : FluidsGT.VINEGAR) if (UT.Fluids.exists(tAlcohol)) {
		FM.Burn			.addRecipe0(T, - 16,  6, UT.Fluids.make(tAlcohol, 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 16,  9, UT.Fluids.make(tAlcohol, 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		}
		for (String tAlcohol : new String[] {"vodka", "binnie.vodka"}) if (UT.Fluids.exists(tAlcohol)) {
		FM.Burn			.addRecipe0(T, - 16,  6, UT.Fluids.make(tAlcohol, 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 16,  9, UT.Fluids.make(tAlcohol, 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		}
		for (String tAlcohol : new String[] {"potion.dragonblood", "potion.goldencider", "potion.notchesbrew"}) if (UT.Fluids.exists(tAlcohol)) {
		FM.Burn			.addRecipe0(T, - 16, 12, UT.Fluids.make(tAlcohol, 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 16, 18, UT.Fluids.make(tAlcohol, 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		}
		
		FM.Burn			.addRecipe0(T, - 16,  9, UT.Fluids.make("bioethanol", 1)					, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 16, 12, UT.Fluids.make("bioethanol", 1)					, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		
		if (UT.Fluids.exists("biodiesel")) {
		FM.Burn			.addRecipe0(T, - 16,  9, UT.Fluids.make("biodiesel", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 16, 12, UT.Fluids.make("biodiesel", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		}
		if (UT.Fluids.exists("biofuel")) {
		FM.Burn			.addRecipe0(T, - 64,  9, UT.Fluids.make("biofuel", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 64, 12, UT.Fluids.make("biofuel", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		}
		if (UT.Fluids.exists("hootch")) {
		FM.Burn			.addRecipe0(T, - 16,  5, UT.Fluids.make("hootch", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 16,  6, UT.Fluids.make("hootch", 1)						, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		}
		if (UT.Fluids.exists("fire_water")) {
		FM.Burn			.addRecipe0(T, - 32,  9, UT.Fluids.make("fire_water", 1)					, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 32, 10, UT.Fluids.make("fire_water", 1)					, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		}
		if (UT.Fluids.exists("rocket_fuel")) {
		FM.Burn			.addRecipe0(T, - 64,  4, UT.Fluids.make("rocket_fuel", 1)					, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		FM.Engine		.addRecipe0(T, - 64,  5, UT.Fluids.make("rocket_fuel", 1)					, UT.Fluids.make("carbondioxide", 1), ZL_IS);
		}
		
		
		
		FM.Burn			.addRecipe0(T, - 16,  1, UT.Fluids.make("hydrogen", 2)						, FL.DistW.make(3), ZL_IS);
		FM.Gas			.addRecipe0(T, - 16,  2, UT.Fluids.make("hydrogen", 2)						, FL.DistW.make(3), ZL_IS);
		
		FM.Burn			.addRecipe0(T, - 16, 24, UT.Fluids.make("methane", 5)						, FL.DistW.make(6), UT.Fluids.make("carbondioxide", 3));
		FM.Burn			.addRecipe0(T, - 16, 24, UT.Fluids.make("gas_natural_gas", 5)				, FL.DistW.make(6), UT.Fluids.make("carbondioxide", 3));
		FM.Gas			.addRecipe0(T, - 16, 30, UT.Fluids.make("methane", 5)						, FL.DistW.make(6), UT.Fluids.make("carbondioxide", 3));
		FM.Gas			.addRecipe0(T, - 16, 30, UT.Fluids.make("gas_natural_gas", 5)				, FL.DistW.make(6), UT.Fluids.make("carbondioxide", 3));
		if (UT.Fluids.exists("naturalgas")) {
		FM.Burn			.addRecipe0(T, - 16, 24, UT.Fluids.make("naturalgas", 5)					, FL.DistW.make(6), UT.Fluids.make("carbondioxide", 3));
		FM.Gas			.addRecipe0(T, - 16, 30, UT.Fluids.make("naturalgas", 5)					, FL.DistW.make(6), UT.Fluids.make("carbondioxide", 3));
		}
		if (UT.Fluids.exists("ic2biogas")) {
		FM.Burn			.addRecipe0(T, - 16, 24, UT.Fluids.make("ic2biogas", 5)						, FL.DistW.make(6), UT.Fluids.make("carbondioxide", 3));
		FM.Gas			.addRecipe0(T, - 16, 30, UT.Fluids.make("ic2biogas", 5)						, FL.DistW.make(6), UT.Fluids.make("carbondioxide", 3));
		}
		
		FM.Burn			.addRecipe0(T, - 64, 42, UT.Fluids.make("butane", 7)						, FL.DistW.make(7), UT.Fluids.make("carbondioxide", 6));
		FM.Gas			.addRecipe0(T, - 64, 56, UT.Fluids.make("butane", 7)						, FL.DistW.make(7), UT.Fluids.make("carbondioxide", 6));
		
		FM.Burn			.addRecipe0(T, - 64, 30, UT.Fluids.make("propane", 5)						, FL.DistW.make(5), UT.Fluids.make("carbondioxide", 4));
		FM.Gas			.addRecipe0(T, - 64, 40, UT.Fluids.make("propane", 5)						, FL.DistW.make(5), UT.Fluids.make("carbondioxide", 4));
		
		FM.Hot			.addRecipe0(T, - 16, EU_PER_LAVA/16, FL.Lava.make(1)						, FL.Lava_Pahoehoe.make(1), ZL_IS);
//		FM.Hot			.addRecipe0(T, - 16,  X, UT.Fluids.make("ic2hotwater", 1)					, FL.DistW.make(1), ZL_ITEMSTACK);
//		FM.Hot			.addRecipe0(T, - 16,  X, UT.Fluids.make("ic2hotcoolant", 1)					, FL.Coolant_IC2.make(1), ZL_ITEMSTACK);
		
		
		
		FM.Turbine		.addRecipe0(T, - 16, 10/STEAM_PER_EU, UT.Fluids.make("steam", 160)			, FL.DistW.make(1), ZL_IS);
//		FM.Turbine		.addRecipe0(T, - 16,  X, UT.Fluids.make("ic2steam", 100)					, UT.Fluids.make("ic2hotwater", 1), ZL_ITEMSTACK);
//		FM.Turbine		.addRecipe0(T, -128,  X, UT.Fluids.make("ic2superheatedsteam", 1)			, UT.Fluids.make("ic2steam", 1), ZL_ITEMSTACK);
		
		
		
		FM.Magic		.addRecipe0(T, - 16,  2, UT.Fluids.make("mercury", 1));
		
		
//      new Recipe(new ItemStack(Items.lava_bucket), new ItemStack(Blocks.obsidian), OM.get(OP.ingot, MT.Copper, 1), OM.get(OP.ingot, MT.Tin, 1), OM.get(OP.ingot, MT.Electrum, 1), 30, 2);
		
//      RecipeMap.sSmallNaquadahReactorFuels.addRecipe(T, new ItemStack[] {OM.get(OP.bolt , MT.Nq_528, 1)}, new ItemStack[] {OM.get(OP.bolt , MT.Nq		, 1)}, null, null, null, null, 0, 0,  25000);
//      RecipeMap.sLargeNaquadahReactorFuels.addRecipe(T, new ItemStack[] {OM.get(OP.ingot, MT.Nq_528, 1)}, new ItemStack[] {OM.get(OP.ingot, MT.Nq		, 1)}, null, null, null, null, 0, 0, 200000);
//      RecipeMap.sFluidNaquadahReactorFuels.addRecipe(T, new ItemStack[] {OM.get(OP.cell , MT.Nq_528, 1)}, new ItemStack[] {OM.get(OP.cell , MT.Empty  , 1)}, null, null, null, null, 0, 0, 200000);
		
//      RA.addFuel(GT_ModHandler.getModItem(ModIDs.TC, "ItemResource", 1, 4)				, null,      4, 5);
//		RA.addFuel(new ItemStack(Items.experience_bottle, 1)								, null,     10, 5);
//      RA.addFuel(new ItemStack(Items.ghast_tear, 1)										, null,    500, 5);
//      RA.addFuel(new ItemStack(Blocks.beacon, 1)											, null, MT.NetherStar.mFuelPower * 2, MT.NetherStar.mFuelType);
	}
}