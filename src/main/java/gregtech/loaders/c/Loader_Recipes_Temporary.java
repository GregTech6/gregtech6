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

package gregtech.loaders.c;

import static gregapi.data.CS.*;

import gregapi.data.CS.FluidsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.OM;

/**
 * @author Gregorius Techneticies
 * 
 * Here is basically everything that I want to change to something better later.
 */
public class Loader_Recipes_Temporary implements Runnable {
	@Override public void run() {
		// TODO: Graphite Electrodes are made from petroleum coke after it is mixed with coal tar pitch. They are then extruded and shaped, baked to carbonize the binder (pitch) and finally graphitized by heating it to temperatures approaching 3273K.
		RM.Extruder.addRecipe2(T, 512, 512, OP.dust.mat(MT.Graphite, 1), IL.Shape_Extruder_Rod.get(0), OP.stick.mat(MT.Graphite, 1));
		// TODO: Better Coolant Item than Lapis.
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lapis   , 1*U), FL.DistW.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lapis   , 2*U), FL.Water.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lazurite, 1*U), FL.DistW.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lazurite, 2*U), FL.Water.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Sodalite, 1*U), FL.DistW.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Sodalite, 2*U), FL.Water.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		// TODO: Just no Ender IO Compat Handler and for this small thing I wont make a new Class.
		CR.delate(MD.EIO, "itemYetaWrench");
		
		
		
		
		
		// Some of these aren't Temporary, but I like having all Generifier Recipes for Fluids in on place.
		RM.generify(FL.make("molten.meteoriciron"        , 1), FL.make("molten.iron", 1));
		RM.generify(FL.make("molten.wroughtiron"         , 1), FL.make("molten.iron", 1));
		RM.generify(FL.make("molten.osmiumelemental"     , 1), FL.make("molten.osmium", 1));
		RM.generify(FL.Redstone_TE                   .make(25),FL.Redstone.make(36));
		RM.generify(FL.Redstone                      .make(36),FL.Redstone_TE.make(25));
		RM.generify(FL.Lubricant                     .make(1), FL.LubRoCant.make(1));
		RM.generify(FL.LubRoCant                     .make(1), FL.Lubricant.make(1));
		RM.generify(FL.Oil_Canola                    .make(2), FL.lube(1));
		RM.generify(FL.make("molten.latex"               , 1), FL.Latex.make(1));
		RM.generify(FL.Latex                         .make(1), FL.make("molten.latex", 1));
		RM.generify(FL.Slime_Pink                    .make(1), FL.Slime_Green.make(1));
		RM.generify(FL.RoyalJelly                    .make(1), FL.Honey.make(10));
		RM.generify(FL.Honey                         .make(1), FL.HoneyGrC.make(1));
		RM.generify(FL.HoneyGrC                      .make(1), FL.HoneyBoP.make(1));
		RM.generify(FL.HoneyBoP                      .make(1), FL.Honey.make(1));
		RM.generify(FL.Milk                          .make(1), FL.MilkGrC.make(1));
		RM.generify(FL.MilkGrC                       .make(1), FL.Milk.make(1));
		RM.generify(FL.make("for.honeydew"               , 1), FL.Honeydew.make(1));
		RM.generify(FL.make("spruceresin"                , 1), FL.make("resin", 1));
		RM.generify(FL.make("resin"                      , 1), FL.make("spruceresin", 1));
		RM.generify(FL.make("sulfuricacid"               , 1), FL.make("acid", 1));
		RM.generify(FL.make("acid"                       , 1), FL.make("sulfuricacid", 1));
		RM.generify(FL.Oil_Plant                     .make(2), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Seed                      .make(1), FL.Oil_Plant.make(2));
		RM.generify(FL.make("biomass"                    , 1), FL.make("ic2biomass", 1));
		RM.generify(FL.make("ic2biomass"                 , 1), FL.make("biomass", 1));
		RM.generify(FL.Methane                       .make(1), FL.make("ic2biogas", 4));
		RM.generify(FL.make("ic2biogas"                  , 4), FL.Methane.make(1));
		RM.generify(FL.make("gas_natural_gas"            , 1), FL.Methane.make(1));
		RM.generify(FL.make("naturalgas"                 , 1), FL.Methane.make(1));
		RM.generify(FL.make("gas.natural"                , 1), FL.Methane.make(1));
		RM.generify(FL.Liquid_Methane                .make(1), FL.Methane.make(643));
		RM.generify(FL.make("kerosine"                   , 1), FL.make("kerosene", 1));
		RM.generify(FL.make("kerosene"                   , 1), FL.make("kerosine", 1));
		RM.generify(FL.make("petrol"                     , 1), FL.make("gasoline", 1));
		RM.generify(FL.make("gasoline"                   , 1), FL.make("petrol", 1));
		RM.generify(FL.make("fuel"                       , 1), FL.make("fueloil", 1));
		RM.generify(FL.make("fueloil"                    , 1), FL.make("fuel", 1));
		RM.generify(FL.Steam_IC2_Superheated         .make(1), FL.Steam.make(3));
		RM.generify(FL.Steam_IC2                     .make(1), FL.Steam.make(1));
		RM.generify(FL.DistW                         .make(1), FL.Water.make(1));
		RM.generify(FL.Oil_Lin                       .make(1), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Hemp                      .make(1), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Olive                     .make(1), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Sunflower                 .make(1), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Nut                       .make(1), FL.Oil_Seed.make(1));
		
		for (String tFluid : FluidsGT.JUICE) if (FL.exists(tFluid)) RM.generify(FL.make(tFluid, 1), FL.Juice.make(1));
	}
}
