/**
 * Copyright (c) 2020 GregTech-6 Team
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

package gregapi.data;

import static gregapi.data.CS.*;

import cpw.mods.fml.common.event.FMLInterModComms;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.recipes.maps.RecipeMapFuel;
import gregapi.recipes.maps.RecipeMapFurnaceFuel;
import gregapi.util.UT;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 * 
 * Class containing Fuel Maps, for Recipe Maps go into gregapi.data.RM
 */
public class FM {
	public static final RecipeMap
	  Furnace       = new RecipeMapFurnaceFuel  (null, "mc.recipe.furnacefuel"      , "Furnace Fuels"      , "smelting"    , 0, 1, RES_PATH_GUI+"machines/E_Furnace"   ,/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, "", 1, "", T, F, F, F, F, F, F)
	
	, FluidBed      = new RecipeMapFuel         (null, "gt.recipe.fuels.fluidbed"   , "Fluidized Bed Fuels", null          , 0, 1, RES_PATH_GUI+"machines/Default"     ,/*IN-OUT-MIN-ITEM=*/ 1, 2, 1,/*IN-OUT-MIN-FLUID=*/ 1, 2, 1,/*MIN*/ 2,/*AMP=*/ 1, "", 1, "", T, T, T, F, T, F, F)
	, Burn          = new RecipeMapFuel         (null, "gt.recipe.fuels.burn"       , "Burnable Fuels"     , null          , 0, 1, RES_PATH_GUI+"machines/Default"     ,/*IN-OUT-MIN-ITEM=*/ 1, 2, 0,/*IN-OUT-MIN-FLUID=*/ 1, 2, 0,/*MIN*/ 1,/*AMP=*/ 1, "", 1, "", T, T, T, F, T, F, F)
	, Gas           = new RecipeMapFuel         (null, "gt.recipe.fuels.gas"        , "Gas Fuels"          , null          , 0, 1, RES_PATH_GUI+"machines/Default"     ,/*IN-OUT-MIN-ITEM=*/ 1, 2, 0,/*IN-OUT-MIN-FLUID=*/ 1, 2, 0,/*MIN*/ 1,/*AMP=*/ 1, "", 1, "", T, T, T, F, T, F, F)
	, Hot           = new RecipeMapFuel         (null, "gt.recipe.fuels.hot"        , "Hot Fuels"          , null          , 0, 1, RES_PATH_GUI+"machines/Default"     ,/*IN-OUT-MIN-ITEM=*/ 1, 2, 0,/*IN-OUT-MIN-FLUID=*/ 1, 2, 0,/*MIN*/ 1,/*AMP=*/ 1, "", 1, "", T, T, T, F, T, F, F)
	, Plasma        = new RecipeMapFuel         (null, "gt.recipe.fuels.plasma"     , "Plasma Fuels"       , null          , 0, 1, RES_PATH_GUI+"machines/Default"     ,/*IN-OUT-MIN-ITEM=*/ 1, 2, 0,/*IN-OUT-MIN-FLUID=*/ 1, 2, 0,/*MIN*/ 1,/*AMP=*/ 1, "", 1, "", T, T, T, F, T, F, F)
	, Engine        = new RecipeMapFuel         (null, "gt.recipe.fuels.engine"     , "Engine Fuels"       , null          , 0, 1, RES_PATH_GUI+"machines/Default"     ,/*IN-OUT-MIN-ITEM=*/ 1, 2, 0,/*IN-OUT-MIN-FLUID=*/ 1, 2, 0,/*MIN*/ 1,/*AMP=*/ 1, "", 1, "", T, T, T, F, T, F, F)
	, Turbine       = new RecipeMapFuel         (null, "gt.recipe.fuels.turbine"    , "Turbine Fuels"      , null          , 0, 1, RES_PATH_GUI+"machines/Default"     ,/*IN-OUT-MIN-ITEM=*/ 1, 2, 0,/*IN-OUT-MIN-FLUID=*/ 1, 2, 0,/*MIN*/ 1,/*AMP=*/ 1, "", 1, "", T, T, T, F, T, F, F)
	, Magic         = new RecipeMapFuel         (null, "gt.recipe.fuels.magic"      , "Magic Fuels"        , null          , 0, 1, RES_PATH_GUI+"machines/Default"     ,/*IN-OUT-MIN-ITEM=*/ 1, 2, 0,/*IN-OUT-MIN-FLUID=*/ 1, 2, 0,/*MIN*/ 1,/*AMP=*/ 1, "", 1, "", T, T, T, F, T, F, F)
	;
	
	public static void te_fuel_magmatic(String fluidName, int energy) {
		NBTTagCompound toSend = UT.NBT.make();
		toSend.setString("fluidName", fluidName);
		toSend.setInteger("energy", energy);
		FMLInterModComms.sendMessage("ThermalExpansion", "MagmaticFuel", toSend);
	}
	public static void te_fuel_compression(String fluidName, int energy) {
		NBTTagCompound toSend = UT.NBT.make();
		toSend.setString("fluidName", fluidName);
		toSend.setInteger("energy", energy);
		FMLInterModComms.sendMessage("ThermalExpansion", "CompressionFuel", toSend);
	}
	public static void te_coolant(String fluidName, int energy) {
		NBTTagCompound toSend = UT.NBT.make();
		toSend.setString("fluidName", fluidName);
		toSend.setInteger("energy", energy);
		FMLInterModComms.sendMessage("ThermalExpansion", "Coolant", toSend);
	}
}
