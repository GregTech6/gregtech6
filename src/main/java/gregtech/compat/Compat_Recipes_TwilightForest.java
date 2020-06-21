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

package gregtech.compat;

import static gregapi.data.CS.*;
import static gregapi.util.CR.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.ANY;
import gregapi.data.CS.ItemsGT;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class Compat_Recipes_TwilightForest extends CompatMods {
	public Compat_Recipes_TwilightForest(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing TF Recipes.");
		CR.delate(MD.TF, "tile.TFUncraftingTable");
		CR.delate(IL.TF_Carminite.get(1));
		
		CR.shaped(ST.make(Items.stick, 2, 0), DEF, "s", "X", 'X', IL.TF_Dry_Bush);
		CR.shaped(ST.make(Items.stick, 2, 0), DEF, "k", "X", 'X', IL.TF_Dry_Bush);
		
		CR.shaped(ST.make(Blocks.torch, 6, 0), DEF_NAC, "X", "S", 'X', IL.TF_Torchberries, 'S', OP.stick.dat(ANY.Wood));
		
		RM.pack(ST.make(MD.TF, "item.charmOfKeeping1"   ,  4, W), ST.make(MD.TF, "item.charmOfKeeping2" , 1, 0));
		RM.pack(ST.make(MD.TF, "item.charmOfKeeping1"   , 16, W), ST.make(MD.TF, "item.charmOfKeeping3" , 1, 0));
		RM.pack(ST.make(MD.TF, "item.charmOfKeeping2"   ,  4, W), ST.make(MD.TF, "item.charmOfKeeping3" , 1, 0));
		RM.pack(ST.make(MD.TF, "item.charmOfLife1"      ,  4, W), ST.make(MD.TF, "item.charmOfLife2"    , 1, 0));
		
		RM.unpack(ST.make(MD.TF, "item.charmOfKeeping2" , 1, W), ST.make(MD.TF, "item.charmOfKeeping1"  , 4, 0));
		RM.unpack(ST.make(MD.TF, "item.charmOfKeeping3" , 1, W), ST.make(MD.TF, "item.charmOfKeeping2"  , 4, 0));
		RM.unpack(ST.make(MD.TF, "item.charmOfLife2"    , 1, W), ST.make(MD.TF, "item.charmOfLife1"     , 4, 0));
		
		ItemsGT.addNEIRedirects(ST.make(MD.TF, "item.charmOfKeeping1", 1, W), ST.make(MD.TF, "item.charmOfKeeping2", 1, W), ST.make(MD.TF, "item.charmOfKeeping3", 1, W));
		ItemsGT.addNEIRedirects(ST.make(MD.TF, "item.charmOfLife1", 1, W), ST.make(MD.TF, "item.charmOfLife2", 1, W));
		
		RM.sawing(16, 16, F,  2, ST.make(MD.TF, "tile.TFRoots", 1, 0), ST.make(Items.stick, 6, 0));
		RM.sawing(16, 16, F,  6, ST.make(MD.TF, "tile.TFRoots", 1, 1), IL.TF_LiveRoot.get(2), ST.make(Items.stick, 2, 0));
		
		CR.remove(ST.make(MD.TF, "tile.GiantLog"        , 1, 0));
		CR.remove(ST.make(MD.TF, "tile.GiantCobble"     , 1, 0));
		CR.remove(ST.make(MD.TF, "tile.GiantObsidian"   , 1, 0));
		CR.remove(ST.make(MD.TF, "tile.GiantLeaves"     , 1, 0));
		
		RM.sawing(64, 64, F, 50, ST.make(MD.TF, "tile.GiantLog"         , 1, 0), ST.make(Blocks.log, 16, 0));
		RM.sawing(64,256, F, 50, ST.make(MD.TF, "tile.GiantCobble"      , 1, 0), ST.make(Blocks.cobblestone, 64, 0));
		RM.sawing(64,512, F, 50, ST.make(MD.TF, "tile.GiantObsidian"    , 1, 0), ST.make(Blocks.obsidian, 64, 0));
		RM.sawing(64, 16, F, 50, ST.make(MD.TF, "tile.GiantLeaves"      , 1, 0), ST.make(Blocks.leaves, 64, 0));
		
		RM.Mixer.addRecipeX(T, 16, 64, ST.array(OM.dust(MT.Redstone, 4*U), IL.TF_BorerEssence.get(4), ST.make(Items.ghast_tear, 1, W)), IL.TF_Carminite.get(1));
		
		RM.Loom.addRecipe2(T, 16,  128, ST.tag(4), ST.make(MD.TF, "item.arcticFur", 5), ST.make(MD.TF, "item.arcticHelm", 1));
		RM.Loom.addRecipe2(T, 16,  128, ST.tag(5), ST.make(MD.TF, "item.arcticFur", 8), ST.make(MD.TF, "item.arcticPlate", 1));
		RM.Loom.addRecipe2(T, 16,  128, ST.tag(6), ST.make(MD.TF, "item.arcticFur", 7), ST.make(MD.TF, "item.arcticLegs", 1));
		RM.Loom.addRecipe2(T, 16,  128, ST.tag(7), ST.make(MD.TF, "item.arcticFur", 4), ST.make(MD.TF, "item.arcticBoots", 1));
		
//      Doesnt work right, stuff will miss the enchants.
//      RM.Bath.addRecipe1(T, 0, 5*144, ST.make(Items.iron_helmet       , 1, 0), FL.FieryBlood.make(5*L), NF, ST.make(MD.TF, "item.fieryHelm", 1));
//      RM.Bath.addRecipe1(T, 0, 5*144, ST.make(Items.iron_helmet       , 1, 0), FL.FieryTears.make(5*L), NF, ST.make(MD.TF, "item.fieryHelm", 1));
//      RM.Bath.addRecipe1(T, 0, 8*144, ST.make(Items.iron_chestplate   , 1, 0), FL.FieryBlood.make(8*L), NF, ST.make(MD.TF, "item.fieryPlate", 1));
//      RM.Bath.addRecipe1(T, 0, 8*144, ST.make(Items.iron_chestplate   , 1, 0), FL.FieryTears.make(8*L), NF, ST.make(MD.TF, "item.fieryPlate", 1));
//      RM.Bath.addRecipe1(T, 0, 7*144, ST.make(Items.iron_leggings     , 1, 0), FL.FieryBlood.make(7*L), NF, ST.make(MD.TF, "item.fieryLegs", 1));
//      RM.Bath.addRecipe1(T, 0, 7*144, ST.make(Items.iron_leggings     , 1, 0), FL.FieryTears.make(7*L), NF, ST.make(MD.TF, "item.fieryLegs", 1));
//      RM.Bath.addRecipe1(T, 0, 4*144, ST.make(Items.iron_boots        , 1, 0), FL.FieryBlood.make(4*L), NF, ST.make(MD.TF, "item.fieryBoots", 1));
//      RM.Bath.addRecipe1(T, 0, 4*144, ST.make(Items.iron_boots        , 1, 0), FL.FieryTears.make(4*L), NF, ST.make(MD.TF, "item.fieryBoots", 1));
//      RM.Bath.addRecipe1(T, 0, 2*144, ST.make(Items.iron_sword        , 1, 0), FL.FieryBlood.make(2*L), NF, ST.make(MD.TF, "item.fierySword", 1));
//      RM.Bath.addRecipe1(T, 0, 2*144, ST.make(Items.iron_sword        , 1, 0), FL.FieryTears.make(2*L), NF, ST.make(MD.TF, "item.fierySword", 1));
//      RM.Bath.addRecipe1(T, 0, 3*144, ST.make(Items.iron_pickaxe      , 1, 0), FL.FieryBlood.make(3*L), NF, ST.make(MD.TF, "item.fieryPick", 1));
//      RM.Bath.addRecipe1(T, 0, 3*144, ST.make(Items.iron_pickaxe      , 1, 0), FL.FieryTears.make(3*L), NF, ST.make(MD.TF, "item.fieryPick", 1));
	}
}
