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
import gregapi.data.CS.OreDictToolNames;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;

public class Compat_Recipes_Betweenlands extends CompatMods {
	public Compat_Recipes_Betweenlands(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Betweenlands Recipes.");
		CR.remove(IL.BTL_Weedwood_Bark.get(1));
		// Weedwood Bowl
		CR.shaped(ST.make(MD.BTL, "unknownGeneric", 1, 25), DEF | DEL_OTHER_SHAPED_RECIPES, "k", "X", 'X', OD.plankWeedwood);
		
		if (COMPAT_IC2 != null) COMPAT_IC2.addToExplosionWhitelist(IL.BTL_Bedrock.block());
		
		RM.sawing(16,  16, F, 100, ST.make(MD.BTL, "walkway"                        , 1, W), IL.BTL_Weedwood_Planks.get(1), OM.dust(MT.Weedwood, U3));
		RM.sawing(16,  16, F, 100, ST.make(MD.BTL, "weedwoodPlanksButton"           , 1, W), IL.BTL_Weedwood_Planks.get(1));
		RM.sawing(16,  32, F, 100, ST.make(MD.BTL, "weedwoodPlanksPressurePlate"    , 1, W), IL.BTL_Weedwood_Planks.get(2));
		RM.sawing(16,  32, F, 100, ST.make(MD.BTL, "weedwoodSign"                   , 1, W), IL.BTL_Weedwood_Planks.get(2), OM.dust(MT.Weedwood, OP.stick.mAmount / 3));
		RM.sawing(16,  32, F, 100, ST.make(MD.BTL, "weedwoodPlanksFenceGate"        , 1, W), IL.BTL_Weedwood_Planks.get(2), OM.dust(MT.Weedwood, OP.stick.mAmount * 4));
		RM.sawing(16,  48, F, 100, ST.make(MD.BTL, "mossBedItem"                    , 1, W), IL.BTL_Weedwood_Planks.get(3));
		RM.sawing(16,  48, F, 100, ST.make(MD.BTL, "weedwoodTrapDoor"               , 1, W), IL.BTL_Weedwood_Planks.get(3));
		RM.sawing(16,  64, F, 100, ST.make(MD.BTL, "weedwoodCraftingTable"          , 1, W), IL.BTL_Weedwood_Planks.get(4));
		RM.sawing(16,  80, F, 100, ST.make(MD.BTL, "weedwoodRowboat"                , 1, W), IL.BTL_Weedwood_Planks.get(5));
		RM.sawing(16,  96, F, 100, ST.make(MD.BTL, "door_weedwood"                  , 1, W), IL.BTL_Weedwood_Planks.get(6));
		RM.sawing(16, 128, F, 100, ST.make(MD.BTL, "weedwoodChest"                  , 1, W), IL.BTL_Weedwood_Planks.get(8));
		RM.sawing(16, 128, F, 100, ST.make(MD.BTL, "weedwoodJukebox"                , 1, W), IL.BTL_Weedwood_Planks.get(8), OP.gem.mat(MT.Valonite, 1));
		
		CR.shapeless(IL.BTL_Weedwood_Planks.get(1), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "walkway"                    )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(1), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodPlanksButton"       )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(2), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodPlanksPressurePlate")});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(2), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodSign"               )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(2), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodPlanksFenceGate"    )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(3), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "mossBedItem"                )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(3), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodTrapDoor"           )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(4), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodCraftingTable"      )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(5), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodRowboat"            )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(6), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "door_weedwood"              )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(8), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodChest"              )});
		CR.shapeless(IL.BTL_Weedwood_Planks.get(8), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.BTL, "weedwoodJukebox"            )});
		
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1, 0), ST.make(MD.BTL, "groundStuff", 1, 0));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,13), ST.make(MD.BTL, "groundStuff", 1, 1));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,28), ST.make(MD.BTL, "groundStuff", 1, 2));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,24), ST.make(MD.BTL, "groundStuff", 1, 3));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1, 2), ST.make(MD.BTL, "groundStuff", 1, 4));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,11), ST.make(MD.BTL, "groundStuff", 1, 5));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,18), ST.make(MD.BTL, "groundStuff", 1, 6));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,23), ST.make(MD.BTL, "groundStuff", 1, 7));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,26), ST.make(MD.BTL, "groundStuff", 1, 8));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,19), ST.make(MD.BTL, "groundStuff", 1, 9));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,31), ST.make(MD.BTL, "groundStuff", 1,10));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1, 4), ST.make(MD.BTL, "groundStuff", 1,11));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,15), ST.make(MD.BTL, "groundStuff", 1,12));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1, 3), ST.make(MD.BTL, "groundStuff", 1,13));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1, 8), ST.make(MD.BTL, "groundStuff", 1,14));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1, 9), ST.make(MD.BTL, "groundStuff", 1,15));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "unknownGeneric"         , 1, 9), ST.make(MD.BTL, "groundStuff", 1,17));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1, 1), ST.make(MD.BTL, "groundStuff", 1,18));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "unknownGeneric"         , 1,24), ST.make(MD.BTL, "groundStuff", 1,19));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "blackHatMushroomItem"   , 1, 0), ST.make(MD.BTL, "groundStuff", 1,20));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "unknownGeneric"         , 1, 3), ST.make(MD.BTL, "groundStuff", 1,21));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1, 7), ST.make(MD.BTL, "groundStuff", 1,22));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,10), ST.make(MD.BTL, "groundStuff", 1,23));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "bulbCappedMushroomItem" , 1, 0), ST.make(MD.BTL, "groundStuff", 1,24));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,12), ST.make(MD.BTL, "groundStuff", 1,25));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,14), ST.make(MD.BTL, "groundStuff", 1,26));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,29), ST.make(MD.BTL, "groundStuff", 1,27));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1, 6), ST.make(MD.BTL, "groundStuff", 1,29));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "flatheadMushroomItem"   , 1, 0), ST.make(MD.BTL, "groundStuff", 1,30));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,16), ST.make(MD.BTL, "groundStuff", 1,31));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,32), ST.make(MD.BTL, "groundStuff", 1,33));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,17), ST.make(MD.BTL, "groundStuff", 1,34));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,20), ST.make(MD.BTL, "groundStuff", 1,35));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1, 5), ST.make(MD.BTL, "groundStuff", 1,36));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "unknownGeneric"         , 1, 4), ST.make(MD.BTL, "groundStuff", 1,37));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,30), ST.make(MD.BTL, "groundStuff", 1,38));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,21), ST.make(MD.BTL, "groundStuff", 1,39));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,22), ST.make(MD.BTL, "groundStuff", 1,40));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,25), ST.make(MD.BTL, "groundStuff", 1,41));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,27), ST.make(MD.BTL, "groundStuff", 1,42));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "unknownGeneric"         , 1,31), ST.make(MD.BTL, "groundStuff", 1,43));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "unknownGeneric"         , 1,11), ST.make(MD.BTL, "groundStuff", 1,44));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,33), ST.make(MD.BTL, "groundStuff", 1,46));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,34), ST.make(MD.BTL, "groundStuff", 1,47));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,35), ST.make(MD.BTL, "groundStuff", 1,48));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,36), ST.make(MD.BTL, "groundStuff", 1,49));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,37), ST.make(MD.BTL, "groundStuff", 1,50));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,38), ST.make(MD.BTL, "groundStuff", 1,51));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,39), ST.make(MD.BTL, "groundStuff", 1,52));
		RM.Mortar   .addRecipe1(T, 16, 16, ST.make(MD.BTL, "plantDrop"              , 1,40), ST.make(MD.BTL, "groundStuff", 1,53));
		
		RM.Mortar   .addRecipe1(T, 16, 16, 6000, IL.BTL_Portal_Bark.get(1), IL.BTL_Bark.get(9));
		RM.Mortar   .addRecipe1(T, 16, 16, 5000, IL.BTL_Weedwood_Bark.get(1), IL.BTL_Bark.get(9));
		RM.Mortar   .addRecipe1(T, 16, 16, 1000, IL.BTL_Weedwood_RottenBark.get(1), IL.FR_Mulch.exists()?IL.FR_Mulch.get(9):OP.dust.mat(MT.Weedwood, 9));
		
		CR.shaped(OP.stick.mat(MT.Weedwood, 2), DEF, "s", "X", 'X', ST.make(MD.BTL, "deadWeedwoodBush", 1, 0));
		CR.shaped(OP.stick.mat(MT.Weedwood, 2), DEF, "k", "X", 'X', ST.make(MD.BTL, "deadWeedwoodBush", 1, 0));
		CR.shaped(OP.stick.mat(MT.Weedwood, 2), DEF, "s", "X", 'X', ST.make(MD.BTL, "weedwoodBush", 1, 0));
		CR.shaped(OP.stick.mat(MT.Weedwood, 2), DEF, "k", "X", 'X', ST.make(MD.BTL, "weedwoodBush", 1, 0));
	}
}
