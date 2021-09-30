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

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.ANY;
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
import net.minecraft.init.Items;
import net.minecraftforge.fluids.FluidStack;

public class Compat_Recipes_ActuallyAdditions extends CompatMods {
	public Compat_Recipes_ActuallyAdditions(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Actually Adding Recipes.");
		CR.shaped(ST.make(MD.AA, "itemFood", 1,  7), CR.DEF_REM_NCC, " k", "D ", 'D', IL.Food_Dough_Egg_Flat);
		CR.shaped(ST.make(MD.AA, "itemFood", 2, 10), CR.DEF_REM_NCC, " k", "X ", 'X', "foodBread");
		CR.shaped(ST.make(MD.AA, "itemFood", 1,  8), CR.DEF_REM_NCC, "C", "Z", 'Z', IL.Food_CakeBottom, 'C', "foodChocolatecream");
		CR.shaped(ST.make(MD.AA, "itemFood", 1, 12), CR.DEF_REM_NCC, "DDD", 'D', "foodChocolateDough");
		
		CR.shapeless(ST.make(MD.AA, "itemFood", 1,  5), CR.DEF_REM_NCC, new Object[] {IL.Food_Fries});
		CR.shapeless(ST.make(MD.AA, "itemFood", 1, 20), CR.DEF_REM_NCC, new Object[] {IL.Food_Bacon_Cooked});
		CR.shapeless(ST.make(MD.AA, "itemFood", 1, 19), CR.DEF_REM_NCC, new Object[] {ST.make(MD.AA, "itemFood", 1, 10), "foodChocolatecream"});
		CR.shapeless(ST.make(MD.AA, "itemFood", 1,  6), CR.DEF_REM_NCC, new Object[] {Items.bowl                                        , ST.make(MD.AA, "itemFood", 1, 7), ST.make(MD.AA, "itemFood", 1, 7), ST.make(MD.AA, "itemFood", 1, 7), "foodKetchup", OP.dust.dat(MT.MeatCooked)});
		CR.shapeless(ST.make(MD.AA, "itemFood", 1, 14), CR.DEF_REM_NCC, new Object[] {IL.Food_Dough_Flat_Ketchup                        , "listAllfishcooked", "listAllmushroom", IL.Food_Carrot_Sliced, IL.Food_Carrot_Sliced, IL.Food_Carrot_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced});
		CR.shapeless(ST.make(MD.AA, "itemFood", 1, 13), CR.DEF_REM_NCC, new Object[] {IL.Food_Buns_Sliced                               , "listAllbeefcooked", IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(ST.make(MD.AA, "itemFood", 1, 13), CR.DEF_REM_NCC, new Object[] {IL.Food_Bun_Sliced, IL.Food_Bun_Sliced            , "listAllbeefcooked", IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, IL.Food_Onion_Sliced});
		CR.shapeless(ST.make(MD.AA, "itemFood", 1, 11), CR.DEF_REM_NCC, new Object[] {IL.Food_Baguettes_Sliced                          , "listAllfishcooked", IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, OD.paperEmpty, OD.paperEmpty});
		CR.shapeless(ST.make(MD.AA, "itemFood", 1, 11), CR.DEF_REM_NCC, new Object[] {IL.Food_Baguette_Sliced, IL.Food_Baguette_Sliced  , "listAllfishcooked", IL.Food_Cheese_Sliced, IL.Food_Cheese_Sliced, IL.Food_Cucumber_Sliced, IL.Food_Tomato_Sliced, OD.paperEmpty, OD.paperEmpty});
		CR.shapeless(ST.make(MD.AA, "itemMisc", 2,  1), CR.DEF_REM_NCC, new Object[] {OD.itemPlantRemains});
		
		CR.delate(MD.AA, "blockFermentingBarrel", 0);
		CR.delate(MD.AA, "itemFood", 2);
		CR.delate(MD.AA, "itemFood", 9);
		CR.delate(MD.AA, "itemFood",15);
		CR.delate(MD.AA, "itemMisc", 4);
		CR.delate(MD.AA, "itemMisc", 9);
		CR.delate(MD.AA, "itemMisc",12);
		
		RM.Coagulator   .addRecipe0(T,  0,  160, FL.XP .make(160), NF, IL.AA_XP.get(1));
		RM.Coagulator   .addRecipe0(T,  0,  160, FL.Mob.make(528), NF, IL.AA_XP.get(1));
		
		RM.Replicator   .addRecipe2(T, 16,   16, OP.dust        .mat(MT.Redstone, 1), ST.tag(0), OP.gem     .mat(MT.Redstonia   , 1));
		RM.Replicator   .addRecipe2(T, 16,   16, OP.gem         .mat(MT.Lapis   , 1), ST.tag(0), OP.gem     .mat(MT.Palis       , 1));
		RM.Replicator   .addRecipe2(T, 16,   16, OP.gem         .mat(MT.Coal    , 1), ST.tag(0), OP.gem     .mat(MT.VoidCrystal , 1));
		RM.Replicator   .addRecipe2(T, 16,  144, OP.blockDust   .mat(MT.Redstone, 1), ST.tag(0), OP.blockGem.mat(MT.Redstonia   , 1));
		RM.Replicator   .addRecipe2(T, 16,  144, OP.blockGem    .mat(MT.Lapis   , 1), ST.tag(0), OP.blockGem.mat(MT.Palis       , 1));
		RM.Replicator   .addRecipe2(T, 16,  144, OP.blockGem    .mat(MT.Coal    , 1), ST.tag(0), OP.blockGem.mat(MT.VoidCrystal , 1));
		for (OreDictMaterial tMat : ANY.Diamond.mToThis) if (tMat != MT.Diamantine) {
		RM.Replicator   .addRecipe2(T, 16,   16, OP.gem         .mat(tMat       , 1), ST.tag(0), OP.gem     .mat(MT.Diamantine  , 1));
		RM.Replicator   .addRecipe2(T, 16,  144, OP.blockGem    .mat(tMat       , 1), ST.tag(0), OP.blockGem.mat(MT.Diamantine  , 1));
		}
		for (OreDictMaterial tMat : ANY.Emerald.mToThis) if (tMat != MT.Emeradic) {
		RM.Replicator   .addRecipe2(T, 16,   16, OP.gem         .mat(tMat       , 1), ST.tag(0), OP.gem     .mat(MT.Emeradic    , 1));
		RM.Replicator   .addRecipe2(T, 16,  144, OP.blockGem    .mat(tMat       , 1), ST.tag(0), OP.blockGem.mat(MT.Emeradic    , 1));
		}
		for (OreDictMaterial tMat : ANY.Iron.mToThis) if (tMat != MT.Enori) {
		RM.Replicator   .addRecipe2(T, 16,   16, OP.ingot       .mat(tMat       , 1), ST.tag(0), OP.gem     .mat(MT.Enori       , 1));
		RM.Replicator   .addRecipe2(T, 16,  144, OP.blockIngot  .mat(tMat       , 1), ST.tag(0), OP.blockGem.mat(MT.Enori       , 1));
		}
		
		for (FluidStack tWater : FL.waters(250)) {
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.Rice), tWater, NF, IL.AA_Dough_Rice.get(1));
		RM.Mixer        .addRecipe1(T, 16,   16, IL.AA_Dough_Rice.get(1), tWater, NF, ST.make(MD.AA, "itemMisc", 1, 12));
		}
		
		RM.Squeezer     .addRecipe1(T, 16,   16, ST.make(MD.AA, "blockBlackLotus", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Black], IL.AA_Dye_Black.get(2));
		RM.Juicer       .addRecipe1(T, 16,   16, ST.make(MD.AA, "blockBlackLotus", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Black], IL.AA_Dye_Black.get(2));
		RM.ic2_extractor(ST.make(MD.AA, "blockBlackLotus", 1, 0), IL.AA_Dye_Black.get(3));
		
		RM.Compressor   .addRecipe1(T, 16,   16, OP.gem.mat(MT.BlackQuartz, 4), ST.make(MD.AA, "blockMisc", 1, 1));
		
		RM.smash(ST.make(MD.AA, "blockMisc"                   , 1, 0), OP.gem.mat(MT.BlackQuartz, 2));
		RM.smash(ST.make(MD.AA, "blockMisc"                   , 1, 1), OP.gem.mat(MT.BlackQuartz, 4));
		RM.smash(ST.make(MD.AA, "blockMisc"                   , 1, 2), OP.gem.mat(MT.BlackQuartz, 4));
		RM.smash(ST.make(MD.AA, "blockPillarQuartzStair"      , 1, 0), OP.gem.mat(MT.BlackQuartz, 2));
		RM.smash(ST.make(MD.AA, "blockChiseledQuartzStair"    , 1, 0), OP.gem.mat(MT.BlackQuartz, 4));
		RM.smash(ST.make(MD.AA, "blockQuartzStair"            , 1, 0), OP.gem.mat(MT.BlackQuartz, 4));
		RM.smash(ST.make(MD.AA, "blockPillarQuartzWall"       , 1, 0), OP.gem.mat(MT.BlackQuartz, 2));
		RM.smash(ST.make(MD.AA, "blockChiseledQuartzWall"     , 1, 0), OP.gem.mat(MT.BlackQuartz, 4));
		RM.smash(ST.make(MD.AA, "blockQuartzWall"             , 1, 0), OP.gem.mat(MT.BlackQuartz, 4));
		RM.smash(ST.make(MD.AA, "blockPillarQuartzSlab"       , 1, 0), OP.gem.mat(MT.BlackQuartz, 1));
		RM.smash(ST.make(MD.AA, "blockChiseledQuartzSlab"     , 1, 0), OP.gem.mat(MT.BlackQuartz, 2));
		RM.smash(ST.make(MD.AA, "blockQuartzSlab"             , 1, 0), OP.gem.mat(MT.BlackQuartz, 2));
		RM.smash(ST.make(MD.AA, "blockTestifiBucksWhiteWall"  , 1, 0), OP.gem.mat(MT.NetherQuartz, 4));
		RM.smash(ST.make(MD.AA, "blockTestifiBucksWhiteFence" , 1, 0), OP.gem.mat(MT.NetherQuartz, 4));
		RM.smash(ST.make(MD.AA, "blockTestifiBucksWhiteStairs", 1, 0), OP.gem.mat(MT.NetherQuartz, 4));
		RM.smash(ST.make(MD.AA, "blockTestifiBucksWhiteSlab"  , 1, 0), OP.gem.mat(MT.NetherQuartz, 2));
		RM.smash(ST.make(MD.AA, "blockTestifiBucksGreenWall"  , 1, 0), OP.gem.mat(MT.NetherQuartz, 4));
		RM.smash(ST.make(MD.AA, "blockTestifiBucksGreenFence" , 1, 0), OP.gem.mat(MT.NetherQuartz, 4));
		RM.smash(ST.make(MD.AA, "blockTestifiBucksGreenStairs", 1, 0), OP.gem.mat(MT.NetherQuartz, 4));
		RM.smash(ST.make(MD.AA, "blockTestifiBucksGreenSlab"  , 1, 0), OP.gem.mat(MT.NetherQuartz, 2));
	}
}
