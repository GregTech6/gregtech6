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
import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Compat_Recipes_Forestry extends CompatMods {
	public Compat_Recipes_Forestry(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Forestry Recipes.");
		CR.remove(OM.ingot(MT.Sn), OM.ingot(MT.Cu), NI, OM.ingot(MT.Cu), OM.ingot(MT.Cu));
		CR.delate(MD.FR, "honeyedSlice", "letters");
		RM.generify(IL. FR_Royal_Jelly.get(1), IL.HaC_Royal_Jelly.get(1));
		RM.generify(IL.HaC_Royal_Jelly.get(1), IL. FR_Royal_Jelly.get(1));
		
		OM.data(CR.get(null, OP.ingot.mat(MT.Sn, 1), null, OP.ingot.mat(MT.Sn, 1), null, OP.ingot.mat(MT.Sn, 1), null, null, null), new OreDictItemData(MT.Sn, U * 3));
		
		long tBits = DEF | DEL_OTHER_SHAPED_RECIPES | DEL_OTHER_NATIVE_RECIPES | ONLY_IF_HAS_OTHER_RECIPES;
		CR.shaped(ST.make(MD.FR, "gearTin"   , 1, 0), tBits, " X ", "XGX", " X ", 'X', OP.ingot.dat(MT.Sn), 'G', OP.gear.dat(ANY.Stone));
		CR.shaped(ST.make(MD.FR, "gearCopper", 1, 0), tBits, " X ", "XGX", " X ", 'X', OP.ingot.dat(ANY.Cu), 'G', OP.gear.dat(ANY.Stone));
		CR.shaped(ST.make(MD.FR, "gearBronze", 1, 0), tBits, " X ", "XGX", " X ", 'X', OP.ingot.dat(MT.Bronze), 'G', OP.gear.dat(ANY.Stone));
		CR.shapeless(ST.make(MD.FR, "gearTin"   , 1, 0), new Object[] {OP.gearGt.dat(MT.Sn)});
		CR.shapeless(ST.make(MD.FR, "gearCopper", 1, 0), new Object[] {OP.gearGt.dat(ANY.Cu)});
		CR.shapeless(ST.make(MD.FR, "gearBronze", 1, 0), new Object[] {OP.gearGt.dat(MT.Bronze)});
		
		
		CR.shapeless(IL.FR_Mulch.get(1), CR.DEF_NCC, new Object[] {OD.itemPlantRemains, OD.itemPlantRemains, OD.itemPlantRemains, OD.itemPlantRemains});
		CR.shapeless(IL.FR_Mulch.get(1), CR.DEF_NCC, new Object[] {OD.itemGrassMoldy, OD.itemGrassMoldy});
		CR.shapeless(IL.FR_Mulch.get(1), CR.DEF_NCC, new Object[] {OD.itemGrassRotten});
		CR.shapeless(ST.make(MD.FR, "honeyedSlice"  , 1, 0), CR.DEF_NCC, new Object[] {IL.Food_Bread_Sliced, OP.bottle.dat(MT.Honey)});
		CR.shaped(ST.make(MD.FR, "letters"          , 1, 0), CR.DEF_NCC, "G", "P", 'P', OD.paperEmpty, 'G', OD.itemGlue);
		CR.shaped(ST.make(MD.FR, "letters"          , 1, 0), CR.DEF_NCC, "G", "P", 'P', OD.paperEmpty, 'G', "listAllpropolis");
		
		RM.Printer.addRecipe2(F, 16,128, ST.make(Items.paper, 1, W), ST.tag(0), FL.Glue.make(200), NF, ST.make(MD.FR, "letters", 1, 0));
		
		if (CR.has(ST.make(MD.FR, "stamps", 1, 0))) {
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OM.dust(MT.Apatite                    ,U9), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 0));
		RM.Printer.addRecipe2(F, 16,128, OP.plateTiny.mat(MT.Paper, 9)  , OM.dust(MT.Apatite                    , U), FL.Glue.make(225), NF, ST.make(MD.FR, "stamps", 9, 0));
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(MT.Zn                , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 0));
		}
		if (CR.has(ST.make(MD.FR, "stamps", 1, 1))) {
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(MT.Pb                , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 1));
		for (OreDictMaterial tMat : ANY.Cu.mToThis)
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(tMat                 , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 1));
		}
		if (CR.has(ST.make(MD.FR, "stamps", 1, 2))) {
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(MT.Sn                , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 2));
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(MT.Bi                , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 2));
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(MT.Bronze            , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 2));
		}
		if (CR.has(ST.make(MD.FR, "stamps", 1, 3))) {
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(MT.Au                , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 3));
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(MT.Ag                , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 3));
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(MT.Electrum          , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 3));
		}
		if (CR.has(ST.make(MD.FR, "stamps", 1, 4))) {
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(MT.Pt                , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 4));
		for (OreDictMaterial tMat : ANY.W.mToThis)
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(tMat                 , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 4));
		for (OreDictMaterial tMat : ANY.Diamond.mToThis)
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateGemTiny.mat(tMat              , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 4));
		}
		if (CR.has(ST.make(MD.FR, "stamps", 1, 5))) {
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(MT.Os                , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 5));
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(MT.Ir                , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 5));
		for (OreDictMaterial tMat : ANY.Emerald.mToThis)
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateGemTiny.mat(tMat              , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 5));
		}
		if (CR.has(ST.make(MD.FR, "stamps", 1, 6))) {
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(MT.Nq                , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 6));
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateTiny.mat(MT.Ke                , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 6));
		RM.Printer.addRecipe2(F, 16, 16, OP.plateTiny.mat(MT.Paper, 1)  , OP.plateGemTiny.mat(MT.NetherStar     , 1), FL.Glue.make( 25), NF, ST.make(MD.FR, "stamps", 1, 6));
		}
		
		
		
		
		for (int i = 0; i < 29; i++) {
		ItemStack
		  tLogA     = ST.make(MD.FR, "logs"     , 1, i), tLogB      = ST.make(MD.FR, "logsFireproof"    , 1, i)
		, tPlankA   = ST.make(MD.FR, "planks"   , 1, i), tPlankB    = ST.make(MD.FR, "planksFireproof"  , 1, i)
		, tStairA   = ST.make(MD.FR, "stairs"   , 1, i), tStairB    = ST.make(MD.FR, "stairsFireproof"  , 1, i)
		, tSlabA    = ST.make(MD.FR, "slabs"    , 1, i), tSlabB     = ST.make(MD.FR, "slabsFireproof"   , 1, i)
		, tFenceA   = ST.make(MD.FR, "fences"   , 1, i), tFenceB    = ST.make(MD.FR, "fencesFireproof"  , 1, i);
		
		RM.Laminator    .addRecipe2(T, 16,  192, OP.plate.mat(MT.WaxRefractory, 6), tLogA, tLogB);
		RM.Laminator    .addRecipe2(T, 16,   32, OP.plate.mat(MT.WaxRefractory, 1), tPlankA, tPlankB);
		RM.Laminator    .addRecipe2(T, 16,   32, OP.plate.mat(MT.WaxRefractory, 1), ST.amount(2, tSlabA), ST.amount(2, tSlabB));
		RM.Laminator    .addRecipe2(T, 16,   96, OP.plate.mat(MT.WaxRefractory, 3), ST.amount(2, tStairA), ST.amount(2, tStairB));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.plate.mat(MT.WaxRefractory, 2), tFenceA, tFenceB);
		
		RM.Laminator    .addRecipe2(T, 16,  192, OP.foil.mat(MT.WaxRefractory, 24), tLogA, tLogB);
		RM.Laminator    .addRecipe2(T, 16,   32, OP.foil.mat(MT.WaxRefractory,  4), tPlankA, tPlankB);
		RM.Laminator    .addRecipe2(T, 16,   16, OP.foil.mat(MT.WaxRefractory,  2), tSlabA, tSlabB);
		RM.Laminator    .addRecipe2(T, 16,   48, OP.foil.mat(MT.WaxRefractory,  6), tStairA, tStairB);
		RM.Laminator    .addRecipe2(T, 16,   64, OP.foil.mat(MT.WaxRefractory,  8), tFenceA, tFenceB);
		}
		
		RM.Squeezer     .addRecipe1(T, 16,   64, ST.make(MD.FR, "logs"          , 1, 22), NF, FL.make("maplesap", 25), OM.dust(MT.WOODS.Maple));
		RM.Squeezer     .addRecipe1(T, 16,   64, ST.make(MD.FR, "logsFireproof" , 1, 22), NF, FL.make("maplesap", 25), OM.dust(MT.WOODS.Maple));
		
		RM.Press        .addRecipe2(T, 16,   64, OP.plate.mat(MT.Sn                             , 1), OM.dust(MT.Redstone, U*6), IL.FR_Chipset_Tin      .get(1));
		RM.Press        .addRecipe2(T, 16,   64, OP.plate.mat(MT.Bronze                         , 3), OM.dust(MT.Redstone, U*6), IL.FR_Chipset_Bronze   .get(1));
		for (OreDictMaterial tMat : ANY.Iron.mToThis)
		RM.Press        .addRecipe2(T, 16,   64, (tMat==MT.Enori?OP.plateGem:OP.plate).mat(tMat , 3), OM.dust(MT.Redstone, U*6), IL.FR_Chipset_Iron     .get(1));
		RM.Press        .addRecipe2(T, 16,   64, OP.plate.mat(MT.Au                             , 3), OM.dust(MT.Redstone, U*6), IL.FR_Chipset_Gold     .get(1));
		
		RM.Laminator    .addRecipe2(T, 16,  128, OP.plateGem.mat(MT.Glass, 1)       , IL.Electrode_FR_Copper    .get(8), IL.FR_ElectronTube_Copper.get(8));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.casingSmall.mat(MT.Glass, 1)    , IL.Electrode_FR_Copper    .get(4), IL.FR_ElectronTube_Copper.get(4));
		RM.Laminator    .addRecipe2(T, 16,   48, ST.make(Blocks.glass_pane, 1, W)   , IL.Electrode_FR_Copper    .get(1), IL.FR_ElectronTube_Copper.get(1));
		RM.Laminator    .addRecipe2(T, 16,  128, OP.plateGem.mat(MT.Glass, 1)       , IL.Electrode_FR_Tin       .get(8), IL.FR_ElectronTube_Tin.get(8));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.casingSmall.mat(MT.Glass, 1)    , IL.Electrode_FR_Tin       .get(4), IL.FR_ElectronTube_Tin.get(4));
		RM.Laminator    .addRecipe2(T, 16,   48, ST.make(Blocks.glass_pane, 1, W)   , IL.Electrode_FR_Tin       .get(1), IL.FR_ElectronTube_Tin.get(1));
		RM.Laminator    .addRecipe2(T, 16,  128, OP.plateGem.mat(MT.Glass, 1)       , IL.Electrode_FR_Bronze    .get(8), IL.FR_ElectronTube_Bronze.get(8));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.casingSmall.mat(MT.Glass, 1)    , IL.Electrode_FR_Bronze    .get(4), IL.FR_ElectronTube_Bronze.get(4));
		RM.Laminator    .addRecipe2(T, 16,   48, ST.make(Blocks.glass_pane, 1, W)   , IL.Electrode_FR_Bronze    .get(1), IL.FR_ElectronTube_Bronze.get(1));
		RM.Laminator    .addRecipe2(T, 16,  128, OP.plateGem.mat(MT.Glass, 1)       , IL.Electrode_FR_Iron      .get(8), IL.FR_ElectronTube_Iron.get(8));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.casingSmall.mat(MT.Glass, 1)    , IL.Electrode_FR_Iron      .get(4), IL.FR_ElectronTube_Iron.get(4));
		RM.Laminator    .addRecipe2(T, 16,   48, ST.make(Blocks.glass_pane, 1, W)   , IL.Electrode_FR_Iron      .get(1), IL.FR_ElectronTube_Iron.get(1));
		RM.Laminator    .addRecipe2(T, 16,  128, OP.plateGem.mat(MT.Glass, 1)       , IL.Electrode_FR_Gold      .get(8), IL.FR_ElectronTube_Gold.get(8));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.casingSmall.mat(MT.Glass, 1)    , IL.Electrode_FR_Gold      .get(4), IL.FR_ElectronTube_Gold.get(4));
		RM.Laminator    .addRecipe2(T, 16,   48, ST.make(Blocks.glass_pane, 1, W)   , IL.Electrode_FR_Gold      .get(1), IL.FR_ElectronTube_Gold.get(1));
		RM.Laminator    .addRecipe2(T, 16,  128, OP.plateGem.mat(MT.Glass, 1)       , IL.Electrode_FR_Diamond   .get(8), IL.FR_ElectronTube_Diamond.get(8));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.casingSmall.mat(MT.Glass, 1)    , IL.Electrode_FR_Diamond   .get(4), IL.FR_ElectronTube_Diamond.get(4));
		RM.Laminator    .addRecipe2(T, 16,   48, ST.make(Blocks.glass_pane, 1, W)   , IL.Electrode_FR_Diamond   .get(1), IL.FR_ElectronTube_Diamond.get(1));
		RM.Laminator    .addRecipe2(T, 16,  128, OP.plateGem.mat(MT.Glass, 1)       , IL.Electrode_FR_Obsidian  .get(8), IL.FR_ElectronTube_Obsidian.get(8));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.casingSmall.mat(MT.Glass, 1)    , IL.Electrode_FR_Obsidian  .get(4), IL.FR_ElectronTube_Obsidian.get(4));
		RM.Laminator    .addRecipe2(T, 16,   48, ST.make(Blocks.glass_pane, 1, W)   , IL.Electrode_FR_Obsidian  .get(1), IL.FR_ElectronTube_Obsidian.get(1));
		RM.Laminator    .addRecipe2(T, 16,  128, OP.plateGem.mat(MT.Glass, 1)       , IL.Electrode_FR_Blaze     .get(8), IL.FR_ElectronTube_Blaze.get(8));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.casingSmall.mat(MT.Glass, 1)    , IL.Electrode_FR_Blaze     .get(4), IL.FR_ElectronTube_Blaze.get(4));
		RM.Laminator    .addRecipe2(T, 16,   48, ST.make(Blocks.glass_pane, 1, W)   , IL.Electrode_FR_Blaze     .get(1), IL.FR_ElectronTube_Blaze.get(1));
		RM.Laminator    .addRecipe2(T, 16,  128, OP.plateGem.mat(MT.Glass, 1)       , IL.Electrode_FR_Rubber    .get(8), IL.FR_ElectronTube_Rubber.get(8));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.casingSmall.mat(MT.Glass, 1)    , IL.Electrode_FR_Rubber    .get(4), IL.FR_ElectronTube_Rubber.get(4));
		RM.Laminator    .addRecipe2(T, 16,   48, ST.make(Blocks.glass_pane, 1, W)   , IL.Electrode_FR_Rubber    .get(1), IL.FR_ElectronTube_Rubber.get(1));
		RM.Laminator    .addRecipe2(T, 16,  128, OP.plateGem.mat(MT.Glass, 1)       , IL.Electrode_FR_Emerald   .get(8), IL.FR_ElectronTube_Emerald.get(8));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.casingSmall.mat(MT.Glass, 1)    , IL.Electrode_FR_Emerald   .get(4), IL.FR_ElectronTube_Emerald.get(4));
		RM.Laminator    .addRecipe2(T, 16,   48, ST.make(Blocks.glass_pane, 1, W)   , IL.Electrode_FR_Emerald   .get(1), IL.FR_ElectronTube_Emerald.get(1));
		RM.Laminator    .addRecipe2(T, 16,  128, OP.plateGem.mat(MT.Glass, 1)       , IL.Electrode_FR_Apatite   .get(8), IL.FR_ElectronTube_Apatite.get(8));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.casingSmall.mat(MT.Glass, 1)    , IL.Electrode_FR_Apatite   .get(4), IL.FR_ElectronTube_Apatite.get(4));
		RM.Laminator    .addRecipe2(T, 16,   48, ST.make(Blocks.glass_pane, 1, W)   , IL.Electrode_FR_Apatite   .get(1), IL.FR_ElectronTube_Apatite.get(1));
		RM.Laminator    .addRecipe2(T, 16,  128, OP.plateGem.mat(MT.Glass, 1)       , IL.Electrode_FR_Lapis     .get(8), IL.FR_ElectronTube_Lapis.get(8));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.casingSmall.mat(MT.Glass, 1)    , IL.Electrode_FR_Lapis     .get(4), IL.FR_ElectronTube_Lapis.get(4));
		RM.Laminator    .addRecipe2(T, 16,   48, ST.make(Blocks.glass_pane, 1, W)   , IL.Electrode_FR_Lapis     .get(1), IL.FR_ElectronTube_Lapis.get(1));
		RM.Laminator    .addRecipe2(T, 16,  128, OP.plateGem.mat(MT.Glass, 1)       , IL.Electrode_FR_Ender     .get(8), IL.FR_ElectronTube_Ender.get(8));
		RM.Laminator    .addRecipe2(T, 16,   64, OP.casingSmall.mat(MT.Glass, 1)    , IL.Electrode_FR_Ender     .get(4), IL.FR_ElectronTube_Ender.get(4));
		RM.Laminator    .addRecipe2(T, 16,   48, ST.make(Blocks.glass_pane, 1, W)   , IL.Electrode_FR_Ender     .get(1), IL.FR_ElectronTube_Ender.get(1));
		
		ItemStack tEmptyCrate = ST.make(MD.FR, "crate", 1);
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedTin"             , 1), OP.ingot.mat(MT.Sn, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedCopper"          , 1), OP.ingot.mat(MT.Cu, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedSilver"          , 1), OP.ingot.mat(MT.Ag, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedBrass"           , 1), OP.ingot.mat(MT.Bronze, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedBronze"          , 1), OP.ingot.mat(MT.Bronze, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedPeat"            , 1), OP.ingot.mat(MT.Peat, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedRubber"          , 1), OP.ingot.mat(MT.Rubber, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedPhosphor"        , 1), OP.dust.mat(MT.PhosphorusBlue, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedAsh"             , 1), OP.dust.mat(MT.Ash, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedBeeswax"         , 1), OP.dust.mat(MT.WaxBee, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedRefractoryWax"   , 1), OP.dust.mat(MT.WaxRefractory, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedRedstone"        , 1), OP.dust.mat(MT.Redstone, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedGlowstone"       , 1), OP.dust.mat(MT.Glowstone, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedApatite"         , 1), OP.gem.mat(MT.Apatite, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedLapis"           , 1), OP.gem.mat(MT.Lapis, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedCoal"            , 1), OP.gem.mat(MT.Coal, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedCharcoal"        , 1), OP.gem.mat(MT.Charcoal, 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedCarrots"         , 1), IL.Food_Carrot.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedPotatoes"        , 1), IL.Food_Potato.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedNetherwart"      , 1), ST.make(Items.nether_wart, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedCookies"         , 1), ST.make(Items.cookie, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedWheat"           , 1), IL.Crop_Wheat.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedSeeds"           , 1), ST.make(Items.wheat_seeds, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedReeds"           , 1), ST.make(Items.reeds, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedApples"          , 1), IL.Food_Apple_Red.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedClay"            , 1), ST.make(Items.clay_ball, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedCrystallinePollen", 1), IL.FR_Pollen_Cluster_Crystalline.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedPollen"          , 1), IL.FR_Pollen_Cluster.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedPropolis"        , 1), IL.FR_Propolis.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedRoyalJelly"      , 1), IL.FR_Royal_Jelly.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedHoneydew"        , 1), ST.make(MD.FR, "honeydew", 9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedSaplings"        , 1), ST.make(Blocks.sapling, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedSpruceSapling"   , 1), ST.make(Blocks.sapling, 9, 1));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedBirchSapling"    , 1), ST.make(Blocks.sapling, 9, 2));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedJungleSapling"   , 1), ST.make(Blocks.sapling, 9, 3));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedAcaciaSapling"   , 1), ST.make(Blocks.sapling, 9, 4));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedDarkOakSapling"  , 1), ST.make(Blocks.sapling, 9, 5));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedCacti"           , 1), ST.make(Blocks.cactus, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedScrap"           , 1), IL.IC2_Scrap.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedResin"           , 1), IL.IC2_Resin.get(9, IL.Resin.get(9)));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedFertilizer"      , 1), IL.FR_Fertilizer.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedMulch"           , 1), IL.FR_Mulch.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedHoneycombs"      , 1), IL.FR_Comb_Honey.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedStringyCombs"    , 1), IL.FR_Comb_Stringy.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedSimmeringCombs"  , 1), IL.FR_Comb_Simmering.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedCocoaComb"       , 1), IL.FR_Comb_Cocoa.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedPowderyCombs"    , 1), IL.FR_Comb_Powdery.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedMossyCombs"      , 1), IL.FR_Comb_Mossy.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedMellowCombs"     , 1), IL.FR_Comb_Mellow.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedWheatenCombs"    , 1), IL.FR_Comb_Wheaten.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedFrozenCombs"     , 1), IL.FR_Comb_Frozen.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedDrippingCombs"   , 1), IL.FR_Comb_Dripping.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedSilkyCombs"      , 1), IL.FR_Comb_Silky.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedParchedCombs"    , 1), IL.FR_Comb_Parched.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedMysteriousCombs" , 1), IL.FR_Comb_Mysterious.get(9));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedDirt"            , 1), ST.make(Blocks.dirt, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedPodzol"          , 1), ST.make(Blocks.dirt, 9, 2));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedMycelium"        , 1), ST.make(Blocks.mycelium, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedStone"           , 1), ST.make(Blocks.stone, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedCobblestone"     , 1), ST.make(Blocks.cobblestone, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedGravel"          , 1), ST.make(Blocks.gravel, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedSoulsand"        , 1), ST.make(Blocks.soul_sand, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedNetherrack"      , 1), ST.make(Blocks.netherrack, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedNetherbrick"     , 1), ST.make(Blocks.nether_brick, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedObsidian"        , 1), ST.make(Blocks.obsidian, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedSand"            , 1), ST.make(Blocks.sand, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedRedSand"         , 1), ST.make(Blocks.sand, 9, 1));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedSandstone"       , 1), ST.make(Blocks.sandstone, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedBrick"           , 1), ST.make(Blocks.brick_block, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedWood"            , 1), ST.make(Blocks.log, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedSpruceWood"      , 1), ST.make(Blocks.log, 9, 1));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedBirchWood"       , 1), ST.make(Blocks.log, 9, 2));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedJungleWood"      , 1), ST.make(Blocks.log, 9, 3));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedAcaciaWood"      , 1), ST.make(Blocks.log2, 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedDarkOakWood"     , 1), ST.make(Blocks.log2, 9, 1));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedHumus"           , 1), ST.make(MD.FR, "soil", 9, 0));
		RM.boxunbox(tEmptyCrate, ST.make(MD.FR, "cratedBogearth"        , 1), ST.make(MD.FR, "soil", 9, 1));
		
		RM.Juicer       .addRecipe1(T, 16,   64                                     , IL.FR_Ice_Shard           .get(1), NF, FL.Ice.make(1000), ZL_IS);
		
		RM.Squeezer     .addRecipe1(T, 16,   64                                     , IL.FR_Ice_Shard           .get(1), NF, FL.Ice.make(1000), ZL_IS);
		RM.Squeezer     .addRecipe1(T, 16,   64                                     , OP.dust.mat(MT.PhosphorusBlue, 1), NF, FL.Lava.make(1000), ZL_IS);
		
		RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Propolis            .get(1), ZL_FS, FL.array(FL.Latex.make(L  ), FL.Glue.make( 250)), ZL_IS);
		RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Propolis_Sticky     .get(1), ZL_FS, FL.array(FL.Latex.make(L/4), FL.Glue.make(1000)), ZL_IS);
		RM.Centrifuge   .addRecipe1(T, 16,   64,   800                              , IL.FR_Propolis_Pulsating  .get(1), ZL_FS, FL.array(FL.Latex.make(L/4), FL.Glue.make( 125)), OM.dust(MT.EnderPearl, U72));
		RM.Centrifuge   .addRecipe1(T, 16,   64,  6000                              , IL.FR_Propolis_Silky      .get(1), ZL_FS, FL.array(FL.Latex.make(L/4), FL.Glue.make( 125)), IL.FR_Silk.get(1));
		
//      RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Comb_Honey          .get(1), NF, FL.Honey   .make( 90), OM.dust(MT.WaxBee));
		RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Comb_Cocoa          .get(1)                           , OM.dust(MT.WaxBee), OM.dust(MT.Cocoa, U2));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7000}          , IL.FR_Comb_Simmering      .get(1)                           , OM.dust(MT.WaxRefractory), OP.dust.mat(MT.PhosphorusBlue, 2));
		RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Comb_Stringy        .get(1), NF, FL.Honey   .make( 40), IL.FR_Propolis.get(1));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000, 10000,  2000}   , IL.FR_Comb_Frozen         .get(1), NF, FL.Honey   .make( 70), OM.dust(MT.WaxBee, 3*U4), OM.dust(MT.Snow, U9), IL.FR_Pollen_Cluster_Crystalline.get(1));
		RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Comb_Dripping       .get(1), NF, FL.Honeydew.make(100), IL.FR_Propolis_Sticky.get(1));
		RM.Centrifuge   .addRecipe1(T, 16,   64,  8000                              , IL.FR_Comb_Silky          .get(1), NF, FL.Honey   .make(100), IL.FR_Propolis_Silky.get(1));
		RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Comb_Parched        .get(1), NF, FL.Honey   .make( 60), OM.dust(MT.WaxBee, U2), ST.make(Blocks.sand, 1, 0));
		RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Comb_Mysterious     .get(1), NF, FL.Honey   .make( 40), OM.dust(MT.WaxMagic, U9), IL.FR_Propolis_Pulsating.get(1));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,     9}          , IL.FR_Comb_Irradiated     .get(1), NF, FL.Honey   .make( 60), OM.dust(MT.WaxBee, U2), OM.dust(MT.Ir, U9));
		RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Comb_Powdery        .get(1), NF, FL.Honey   .make( 20), OM.dust(MT.WaxBee, U4), OM.dust(MT.Gunpowder, U));
		RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Comb_Reddened       .get(1), NF, FL.Honey   .make( 60), OM.dust(MT.WaxAmnesic, 2*U3));
		RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Comb_Darkened       .get(1), NF, FL.Honey   .make( 60), OM.dust(MT.WaxAmnesic, U3));
		RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Comb_Omega          .get(1), NF, FL.Honey   .make( 60), OM.dust(MT.WaxAmnesic));
		RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Comb_Wheaten        .get(1), NF, FL.Honey   .make( 20), OM.dust(MT.WaxBee, U4), OM.dust(MT.Wheat, U));
		RM.Centrifuge   .addRecipe1(T, 16,   64                                     , IL.FR_Comb_Mossy          .get(1), NF, FL.Honey   .make( 60), OM.dust(MT.WaxBee, U2), ST.make(Blocks.vine, 2, 0));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000, 10000,  6000}   , IL.FR_Comb_Mellow         .get(1), NF, FL.Honeydew.make(100), OM.dust(MT.WaxBee, U4), OM.dust(MT.NetherQuartz, U3));
		
		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(0), IL.FR_Propolis_Pulsating.get(5) , IL.FR_Pulsating_Mesh.get(1));
		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(0), IL.FR_Pulsating_Mesh.get(5)     , OP.gem.mat(MT.EnderPearl, 1));
		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(0), IL.FR_Silk.get(9)               , IL.FR_Silk_Woven.get(1));
		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(1), IL.FR_Silk.get(3)               , ST.make(Items.string, 1, 0));
		
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(4), IL.FR_Silk_Woven.get(5)         , ST.make(MD.FR, "apiaristHelmet", 1, 0));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(5), IL.FR_Silk_Woven.get(8)         , ST.make(MD.FR, "apiaristChest", 1, 0));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(6), IL.FR_Silk_Woven.get(7)         , ST.make(MD.FR, "apiaristLegs", 1, 0));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(7), IL.FR_Silk_Woven.get(4)         , ST.make(MD.FR, "apiaristBoots", 1, 0));
		
		RM.Loom         .addRecipe2(T, 16,   16, IL.FR_Stick.get(8)                         , ST.make((Item)OP.plantGtFiber.mRegisteredPrefixItems.get(0), 1, W), ST.make(MD.FR, "frameImpregnated", 1, 0));
		RM.Loom         .addRecipe2(T, 16,   16, IL.FR_Stick.get(8)                         , ST.make(Items.string, 1, W)                                       , ST.make(MD.FR, "frameImpregnated", 1, 0));
		
		RM.Canner       .addRecipe2(T, 16,   16, OM.dust(MT.I), IL.FR_TinCapsule.get(1), IL.FR_Iodine_Capsule.get(1));
		RM.Canner       .addRecipe1(T, 16,   16, IL.FR_Iodine_Capsule.get(1), IL.FR_TinCapsule.get(1), OM.dust(MT.I));
		
		RM.Canner       .addRecipe2(T, 16,   16, OM.dust(MT.AgI), IL.FR_TinCapsule.get(1), IL.FR_Dissipation_Capsule.get(1));
		RM.Canner       .addRecipe1(T, 16,   16, IL.FR_Dissipation_Capsule.get(1), IL.FR_TinCapsule.get(1), OM.dust(MT.AgI));
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener("dropHoney", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		if (FL.getFluid(aEvent.mStack, T) != null || OM.is_("bucketHoney", aEvent.mStack) || OM.is_("bottleHoney", aEvent.mStack)) return;
		RM.Canner       .addRecipe2(T, 16,   16, ST.amount(4, aEvent.mStack), IL.FR_WaxCapsule.get(1), IL.FR_HoneyPot.get(1));
		}});
		addListener("listAllwheats", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		RM.Mixer        .addRecipe2(T, 16,   16, ST.amount(4, aEvent.mStack), ST.make(Blocks.dirt, 1, W), IL.FR_Compost.get(4));
		}});
		addListener(OD.logWood, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		RM.Bath         .addRecipe1(T,  0,  128, aEvent.mStack, FL.Oil_Seed         .make(100), NF, IL.FR_Stick.get(1));
		RM.Bath         .addRecipe1(T,  0,  128, aEvent.mStack, FL.Oil_Lin          .make(100), NF, IL.FR_Stick.get(1));
		RM.Bath         .addRecipe1(T,  0,  128, aEvent.mStack, FL.Oil_Hemp         .make(100), NF, IL.FR_Stick.get(1));
		RM.Bath         .addRecipe1(T,  0,  128, aEvent.mStack, FL.Oil_Nut          .make(100), NF, IL.FR_Stick.get(1));
		RM.Bath         .addRecipe1(T,  0,  128, aEvent.mStack, FL.Oil_Olive        .make(100), NF, IL.FR_Stick.get(1));
		RM.Bath         .addRecipe1(T,  0,  128, aEvent.mStack, FL.Oil_Sunflower    .make(100), NF, IL.FR_Stick.get(1));
		
		RM.Assembler    .addRecipe2(T, 16,  64, ST.amount(8, aEvent.mStack), ST.tag(8), MT.SeedOil.liquid(U4, T), NF, IL.FR_Casing_Impregnated.get(1));
		}});
		addListener(OD.itemPlantRemains, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		RM.generify(ST.amount(4, aEvent.mStack), IL.FR_Mulch.get(1));
		}});
		addListener(OD.itemGrassMoldy, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		RM.generify(ST.amount(2, aEvent.mStack), IL.FR_Mulch.get(1));
		}});
		addListener(OD.itemGrassRotten, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		RM.generify(ST.amount(1, aEvent.mStack), IL.FR_Mulch.get(1));
		}});
		addListener("baleGrassMoldy", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		RM.generify(ST.amount(1, aEvent.mStack), IL.FR_Mulch.get(4));
		}});
		addListener("baleGrassRotten", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		RM.generify(ST.amount(1, aEvent.mStack), IL.FR_Mulch.get(9));
		}});
		addListener(OP.stick.dat(ANY.WoodNormal), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		RM.Loom         .addRecipe2(T, 16,   16, ST.amount(8, aEvent.mStack), ST.make((Item)OP.plantGtFiber.mRegisteredPrefixItems.get(0), 1, W), ST.make(MD.FR, "frameUntreated", 1, 0));
		RM.Loom         .addRecipe2(T, 16,   16, ST.amount(8, aEvent.mStack), ST.make(Items.string, 1, W)                                       , ST.make(MD.FR, "frameUntreated", 1, 0));
		}});
		}};
		
		RM.Bath         .addRecipe1(T,  0,  128, ST.make(Items.string, 1, W), MT.Wax            .liquid(U*6, T), NF, IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4));
		RM.Bath         .addRecipe1(T,  0,  128, ST.make(Items.string, 1, W), MT.WaxBee         .liquid(U*6, T), NF, IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4));
		RM.Bath         .addRecipe1(T,  0,  128, ST.make(Items.string, 1, W), MT.WaxPlant       .liquid(U*6, T), NF, IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4));
		RM.Bath         .addRecipe1(T,  0,  128, ST.make(Items.string, 1, W), MT.WaxParaffin    .liquid(U*6, T), NF, IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4), IL.FR_Candle.get(4));
		RM.Bath         .addRecipe1(T,  0,  128, IL.FR_Silk.get(1), MT.Wax          .liquid(U*2, T), NF, IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1));
		RM.Bath         .addRecipe1(T,  0,  128, IL.FR_Silk.get(1), MT.WaxBee       .liquid(U*2, T), NF, IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1));
		RM.Bath         .addRecipe1(T,  0,  128, IL.FR_Silk.get(1), MT.WaxPlant     .liquid(U*2, T), NF, IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1));
		RM.Bath         .addRecipe1(T,  0,  128, IL.FR_Silk.get(1), MT.WaxParaffin  .liquid(U*2, T), NF, IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1), IL.FR_Candle.get(1));
		
		for (FluidStack tWater : FL.waters(1000,  800))
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.Bark, U*4)              , tWater, NF, IL.FR_Mulch.get(1));
		for (FluidStack tWater : FL.waters( 250,  200))
		RM.Mixer        .addRecipe1(T, 16,   16, IL.FR_Propolis.get(1)              , tWater, FL.Glue.make( 250), ZL_IS);
		for (FluidStack tWater : FL.waters(1000,  800))
		RM.Mixer        .addRecipe1(T, 16,   16, IL.FR_Propolis_Sticky.get(1)       , tWater, FL.Glue.make(1000), ZL_IS);
		
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.Ash, 4*U)               , ST.make(Blocks.dirt, 1, W), IL.FR_Compost.get(1));
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.DarkAsh, 3*U)           , ST.make(Blocks.dirt, 1, W), IL.FR_Compost.get(1));
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.VolcanicAsh, 2*U)       , ST.make(Blocks.dirt, 1, W), IL.FR_Compost.get(1));
		
		RM.Mixer        .addRecipe1(T, 16,   64, ST.make(Items.melon, 1, W), FL.array(FL.Honey   .make(600), FL.Honeydew.make(200), FL.Potion_Awkward.make(750)), FL.array(FL.Potion_Heal_1.make(750)), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,   64, ST.make(Items.melon, 1, W), FL.array(FL.Honey   .make(600), FL.Honeydew.make(200), FL.Potion_Thick  .make(750)), FL.array(FL.Potion_Heal_2.make(750)), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,   64, ST.make(Items.melon, 1, W), FL.array(FL.HoneyGrC.make(600), FL.Honeydew.make(200), FL.Potion_Awkward.make(750)), FL.array(FL.Potion_Heal_1.make(750)), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,   64, ST.make(Items.melon, 1, W), FL.array(FL.HoneyGrC.make(600), FL.Honeydew.make(200), FL.Potion_Thick  .make(750)), FL.array(FL.Potion_Heal_2.make(750)), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,   64, ST.make(Items.melon, 1, W), FL.array(FL.HoneyBoP.make(600), FL.Honeydew.make(200), FL.Potion_Awkward.make(750)), FL.array(FL.Potion_Heal_1.make(750)), ZL_IS);
		RM.Mixer        .addRecipe1(T, 16,   64, ST.make(Items.melon, 1, W), FL.array(FL.HoneyBoP.make(600), FL.Honeydew.make(200), FL.Potion_Thick  .make(750)), FL.array(FL.Potion_Heal_2.make(750)), ZL_IS);
		
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Pollen_Cluster.get(3)        , IL.FR_Propolis            .get(6), ZL_FS, FL.Slime_Green.make(250), ZL_IS);
		
		RM.Mixer        .addRecipe2(T, 16,   16, OP.gem.mat(MT.Apatite, 1), ST.make(Blocks.sand, 2, W), IL.FR_Fertilizer.get(8));
		RM.Mixer        .addRecipe2(T, 16,   16, OP.gem.mat(MT.Apatite, 1), OM.dust(MT.Ash, U*8), IL.FR_Fertilizer.get(16));
		RM.Mixer        .addRecipe2(T, 16,   16, OP.gem.mat(MT.Apatite, 1), OM.dust(MT.DarkAsh, U*4), IL.FR_Fertilizer.get(16));
		RM.Mixer        .addRecipe2(T, 16,   16, OP.gem.mat(MT.Apatite, 1), OM.dust(MT.VolcanicAsh, U*2), IL.FR_Fertilizer.get(16));
		
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.NaNO3), OM.dust(MT.SoylentGreen  ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.KNO3 ), OM.dust(MT.SoylentGreen  ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Niter), OM.dust(MT.SoylentGreen  ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.NaNO3), OM.dust(MT.MeatRaw       ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.KNO3 ), OM.dust(MT.MeatRaw       ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Niter), OM.dust(MT.MeatRaw       ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.NaNO3), OM.dust(MT.FishRaw       ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.KNO3 ), OM.dust(MT.FishRaw       ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Niter), OM.dust(MT.FishRaw       ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.NaNO3), OM.dust(MT.MeatCooked    ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.KNO3 ), OM.dust(MT.MeatCooked    ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Niter), OM.dust(MT.MeatCooked    ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.NaNO3), OM.dust(MT.FishCooked    ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.KNO3 ), OM.dust(MT.FishCooked    ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Niter), OM.dust(MT.FishCooked    ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.NaNO3), OM.dust(MT.MeatRotten    ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.KNO3 ), OM.dust(MT.MeatRotten    ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Niter), OM.dust(MT.MeatRotten    ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.NaNO3), OM.dust(MT.FishRotten    ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.KNO3 ), OM.dust(MT.FishRotten    ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Niter), OM.dust(MT.FishRotten    ), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.NaNO3), ST.make(Blocks.dirt, 2, W), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.KNO3 ), ST.make(Blocks.dirt, 2, W), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Niter), ST.make(Blocks.dirt, 2, W), ST.make(Blocks.sand, 2, W)), IL.FR_Fertilizer.get(2));
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.Apatite), ST.make(Blocks.sand, 2, W), IL.FR_Fertilizer.get(8));
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.Apatite), OM.dust(MT.Ash, U*8), IL.FR_Fertilizer.get(16));
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.Apatite), OM.dust(MT.DarkAsh, U*4), IL.FR_Fertilizer.get(16));
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.Apatite), OM.dust(MT.VolcanicAsh, U*2), IL.FR_Fertilizer.get(16));
		
		RM.Mixer        .addRecipeX(T, 16,   64, ST.array(ST.make(Blocks.sand, 4, W), ST.make(Blocks.dirt, 4, W), IL.FR_Mulch.get(1)), ST.make(MD.FR, "soil", 8, 1));
		
		for (FL tFluid : new FL[] {FL.Water, FL.DistW, FL.SpDew, FL.Ocean, FL.OceanGrC, FL.Tropics_Water}) if (tFluid.exists()) {
		if (IL.IC2_Fertilizer.exists())
		RM.Mixer        .addRecipe2(T, 16,   64, IL.IC2_Fertilizer.get(1)   , ST.make(Blocks.dirt, 8, W), tFluid.make(1000), NF, ST.make(MD.FR, "soil", 8, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Fertilizer.get(1)    , ST.make(Blocks.dirt, 8, W), tFluid.make(1000), NF, ST.make(MD.FR, "soil", 8, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Compost.get(1)       , ST.make(Blocks.dirt, 8, W), tFluid.make(1000), NF, ST.make(MD.FR, "soil", 8, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Mulch.get(1)         , ST.make(Blocks.dirt, 8, W), tFluid.make(1000), NF, ST.make(MD.FR, "soil", 9, 0));
		RM.Mixer        .addRecipe2(T, 16,   16, ST.make(Blocks.sand, 1, W) , ST.make(Blocks.dirt, 1, W), tFluid.make( 250), NF, ST.make(MD.FR, "soil", 2, 1));
		}
		if (IL.IC2_Fertilizer.exists())
		RM.Mixer        .addRecipe2(T, 16,   64, IL.IC2_Fertilizer.get(1)   , ST.make(Blocks.dirt, 8, W), FL.Dirty_Water.make(2000), NF, ST.make(MD.FR, "soil", 9, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Fertilizer.get(1)    , ST.make(Blocks.dirt, 8, W), FL.Dirty_Water.make(2000), NF, ST.make(MD.FR, "soil", 9, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Compost.get(1)       , ST.make(Blocks.dirt, 8, W), FL.Dirty_Water.make(2000), NF, ST.make(MD.FR, "soil", 9, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Mulch.get(1)         , ST.make(Blocks.dirt, 8, W), FL.Dirty_Water.make(2000), NF, ST.make(MD.FR, "soil",10, 0));
		RM.Mixer        .addRecipe2(T, 16,   16, ST.make(Blocks.sand, 4, W) , ST.make(Blocks.dirt, 4, W), FL.Dirty_Water.make(2000), NF, ST.make(MD.FR, "soil", 9, 1));
		
		if (IL.IC2_Fertilizer.exists()) {
		RM.Mixer        .addRecipe2(T, 16,   64, IL.IC2_Fertilizer.get(1)   , ST.make(BlocksGT.Diggables, 8, 0) , ST.make(MD.FR, "soil", 8, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.IC2_Fertilizer.get(1)   , IL.Mud_Ball.get(36)               , ST.make(MD.FR, "soil", 8, 0));
		if (IL.BoP_Mud_Ball.exists())
		RM.Mixer        .addRecipe2(T, 16,   64, IL.IC2_Fertilizer.get(1)   , IL.BoP_Mud_Ball.get(36)           , ST.make(MD.FR, "soil", 8, 0));
		if (IL.BoP_Mud.exists())
		RM.Mixer        .addRecipe2(T, 16,   64, IL.IC2_Fertilizer.get(1)   , IL.BoP_Mud.get(8)                 , ST.make(MD.FR, "soil", 8, 0));
		}
		if (IL.BoP_Mud_Ball.exists()) {
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Fertilizer.get(1)    , IL.BoP_Mud_Ball.get(36)           , ST.make(MD.FR, "soil", 8, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Compost.get(1)       , IL.BoP_Mud_Ball.get(36)           , ST.make(MD.FR, "soil", 8, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Mulch.get(1)         , IL.BoP_Mud_Ball.get(36)           , ST.make(MD.FR, "soil", 9, 0));
		RM.Mixer        .addRecipe2(T, 16,   16, ST.make(Blocks.sand, 1, W) , IL.BoP_Mud_Ball.get( 4)           , ST.make(MD.FR, "soil", 2, 1));
		}
		if (IL.BoP_Mud.exists()) {
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Fertilizer.get(1)    , IL.BoP_Mud.get(8)                 , ST.make(MD.FR, "soil", 8, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Compost.get(1)       , IL.BoP_Mud.get(8)                 , ST.make(MD.FR, "soil", 8, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Mulch.get(1)         , IL.BoP_Mud.get(8)                 , ST.make(MD.FR, "soil", 9, 0));
		RM.Mixer        .addRecipe2(T, 16,   16, ST.make(Blocks.sand, 1, W) , IL.BoP_Mud.get(1)                 , ST.make(MD.FR, "soil", 2, 1));
		}
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Fertilizer.get(1)    , ST.make(BlocksGT.Diggables, 8, 0) , ST.make(MD.FR, "soil", 8, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Fertilizer.get(1)    , IL.Mud_Ball.get(36)               , ST.make(MD.FR, "soil", 8, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Compost.get(1)       , ST.make(BlocksGT.Diggables, 8, 0) , ST.make(MD.FR, "soil", 8, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Compost.get(1)       , IL.Mud_Ball.get(36)               , ST.make(MD.FR, "soil", 8, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Mulch.get(1)         , ST.make(BlocksGT.Diggables, 8, 0) , ST.make(MD.FR, "soil", 9, 0));
		RM.Mixer        .addRecipe2(T, 16,   64, IL.FR_Mulch.get(1)         , IL.Mud_Ball.get(36)               , ST.make(MD.FR, "soil", 9, 0));
		RM.Mixer        .addRecipe2(T, 16,   16, ST.make(Blocks.sand, 1, W) , ST.make(BlocksGT.Diggables, 1, 0) , ST.make(MD.FR, "soil", 2, 1));
		RM.Mixer        .addRecipe2(T, 16,   16, ST.make(Blocks.sand, 1, W) , IL.Mud_Ball.get( 4)               , ST.make(MD.FR, "soil", 2, 1));
	}
}
