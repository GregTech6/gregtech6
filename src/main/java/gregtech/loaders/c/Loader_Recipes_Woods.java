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

package gregtech.loaders.c;

import static gregapi.data.CS.*;
import static gregapi.data.OP.*;
import static gregapi.util.CR.*;

import java.util.Arrays;

import gregapi.block.metatype.BlockMetaType;
import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.FluidsGT;
import gregapi.data.CS.ToolsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.oredict.OreDictManager;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.wooddict.BeamEntry;
import gregapi.wooddict.PlankEntry;
import gregapi.wooddict.WoodDictionary;
import gregapi.wooddict.WoodEntry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Loader_Recipes_Woods implements Runnable {
	@Override public void run() {
		// Fire proofing Recipes
		for (int i = 0; i <  4; i++) {
		RM.Laminator    .addRecipe2(T, 16,  192, plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.Log1 , 1, i), ST.make(BlocksGT.Log1FireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.LogA , 1, i), ST.make(BlocksGT.LogAFireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.LogB , 1, i), ST.make(BlocksGT.LogBFireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.LogC , 1, i), ST.make(BlocksGT.LogCFireProof , 1, i));
	//  RM.Laminator    .addRecipe2(T, 16,  192, plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.LogD , 1, i), ST.make(BlocksGT.LogDFireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.Beam1, 1, i), ST.make(BlocksGT.Beam1FireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.Beam2, 1, i), ST.make(BlocksGT.Beam2FireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.Beam3, 1, i), ST.make(BlocksGT.Beam3FireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.BeamA, 1, i), ST.make(BlocksGT.BeamAFireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.BeamB, 1, i), ST.make(BlocksGT.BeamBFireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.BeamC, 1, i), ST.make(BlocksGT.BeamCFireProof, 1, i));
	//  RM.Laminator    .addRecipe2(T, 16,  192, plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.BeamD, 1, i), ST.make(BlocksGT.BeamDFireProof, 1, i));
		
		RM.Laminator    .addRecipe2(T, 16,  192, foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.Log1 , 1, i), ST.make(BlocksGT.Log1FireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.LogA , 1, i), ST.make(BlocksGT.LogAFireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.LogB , 1, i), ST.make(BlocksGT.LogBFireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.LogC , 1, i), ST.make(BlocksGT.LogCFireProof , 1, i));
	//  RM.Laminator    .addRecipe2(T, 16,  192, foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.LogD , 1, i), ST.make(BlocksGT.LogDFireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.Beam1, 1, i), ST.make(BlocksGT.Beam1FireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.Beam2, 1, i), ST.make(BlocksGT.Beam2FireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.Beam3, 1, i), ST.make(BlocksGT.Beam3FireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.BeamA, 1, i), ST.make(BlocksGT.BeamAFireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.BeamB, 1, i), ST.make(BlocksGT.BeamBFireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.BeamC, 1, i), ST.make(BlocksGT.BeamCFireProof, 1, i));
	//  RM.Laminator    .addRecipe2(T, 16,  192, foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.BeamD, 1, i), ST.make(BlocksGT.BeamDFireProof, 1, i));
		
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.Log1 , 1, i), FL.Potion_FireResistance_1L.make(100), NF, ST.make(BlocksGT.Log1FireProof , 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.LogA , 1, i), FL.Potion_FireResistance_1L.make(100), NF, ST.make(BlocksGT.LogAFireProof , 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.LogB , 1, i), FL.Potion_FireResistance_1L.make(100), NF, ST.make(BlocksGT.LogBFireProof , 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.Beam1, 1, i), FL.Potion_FireResistance_1L.make(100), NF, ST.make(BlocksGT.Beam1FireProof, 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.Beam2, 1, i), FL.Potion_FireResistance_1L.make(100), NF, ST.make(BlocksGT.Beam2FireProof, 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.Beam3, 1, i), FL.Potion_FireResistance_1L.make(100), NF, ST.make(BlocksGT.Beam3FireProof, 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.BeamA, 1, i), FL.Potion_FireResistance_1L.make(100), NF, ST.make(BlocksGT.BeamAFireProof, 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.BeamB, 1, i), FL.Potion_FireResistance_1L.make(100), NF, ST.make(BlocksGT.BeamBFireProof, 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.BeamC, 1, i), FL.Potion_FireResistance_1L.make(100), NF, ST.make(BlocksGT.BeamCFireProof, 1, i));
	//  RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.BeamD, 1, i), FL.Potion_FireResistance_1L.make(100), NF, ST.make(BlocksGT.BeamDFireProof, 1, i));
		}
		for (int i = 0; i < 16; i++) {
		RM.Laminator    .addRecipe2(T, 16,   32, plate.mat(MT.WaxRefractory,  1), ST.make(BlocksGT.Planks                            , 1, i), ST.make(BlocksGT.PlanksFireProof                            , 1, i));
		RM.Laminator    .addRecipe2(T, 16,   32, plate.mat(MT.WaxRefractory,  1), ST.make(((BlockMetaType)BlocksGT.Planks ).mSlabs[0], 2, i), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof ).mSlabs[0], 2, i));
		RM.Laminator    .addRecipe2(T, 16,   32, plate.mat(MT.WaxRefractory,  1), ST.make(BlocksGT.Planks2                           , 1, i), ST.make(BlocksGT.Planks2FireProof                           , 1, i));
		RM.Laminator    .addRecipe2(T, 16,   32, plate.mat(MT.WaxRefractory,  1), ST.make(((BlockMetaType)BlocksGT.Planks2).mSlabs[0], 2, i), ST.make(((BlockMetaType)BlocksGT.Planks2FireProof).mSlabs[0], 2, i));
		
		RM.Laminator    .addRecipe2(T, 16,   32, foil .mat(MT.WaxRefractory,  4), ST.make(BlocksGT.Planks                            , 1, i), ST.make(BlocksGT.PlanksFireProof                            , 1, i));
		RM.Laminator    .addRecipe2(T, 16,   16, foil .mat(MT.WaxRefractory,  2), ST.make(((BlockMetaType)BlocksGT.Planks ).mSlabs[0], 1, i), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof ).mSlabs[0], 1, i));
		RM.Laminator    .addRecipe2(T, 16,   32, foil .mat(MT.WaxRefractory,  4), ST.make(BlocksGT.Planks2                           , 1, i), ST.make(BlocksGT.Planks2FireProof                           , 1, i));
		RM.Laminator    .addRecipe2(T, 16,   16, foil .mat(MT.WaxRefractory,  2), ST.make(((BlockMetaType)BlocksGT.Planks2).mSlabs[0], 1, i), ST.make(((BlockMetaType)BlocksGT.Planks2FireProof).mSlabs[0], 1, i));
		
		RM.Bath         .addRecipe1(T,  0,   32, ST.make(BlocksGT.Planks                            , 1, i), FL.Potion_FireResistance_1L.make(20), NF, ST.make(BlocksGT.PlanksFireProof                            , 1, i));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(((BlockMetaType)BlocksGT.Planks ).mSlabs[0], 1, i), FL.Potion_FireResistance_1L.make(10), NF, ST.make(((BlockMetaType)BlocksGT.PlanksFireProof ).mSlabs[0], 1, i));
		RM.Bath         .addRecipe1(T,  0,   32, ST.make(BlocksGT.Planks2                           , 1, i), FL.Potion_FireResistance_1L.make(20), NF, ST.make(BlocksGT.Planks2FireProof                           , 1, i));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(((BlockMetaType)BlocksGT.Planks2).mSlabs[0], 1, i), FL.Potion_FireResistance_1L.make(10), NF, ST.make(((BlockMetaType)BlocksGT.Planks2FireProof).mSlabs[0], 1, i));
		}
		if (MD.FR.mLoaded) {
		Block tPlank1 = ST.block(MD.FR, "planks"), tLog1 = ST.block(MD.FR, "logs"), tSlab1 = ST.block(MD.FR, "slabs"), tStair1 = ST.block(MD.FR, "stairs"), tPlank2 = ST.block(MD.FR, "planksFireproof"), tLog2 = ST.block(MD.FR, "logsFireproof"), tSlab2 = ST.block(MD.FR, "slabsFireproof"), tStair2 = ST.block(MD.FR, "stairsFireproof");
		for (int i = 0; i < 29; i++) {
		RM.Laminator    .addRecipe2(T, 16,  192, plate.mat(MT.WaxRefractory,  6), ST.make(tLog1  , 1, i), ST.make(tLog2  , 1, i));
		RM.Laminator    .addRecipe2(T, 16,   32, plate.mat(MT.WaxRefractory,  1), ST.make(tPlank1, 1, i), ST.make(tPlank2, 1, i));
		RM.Laminator    .addRecipe2(T, 16,   32, plate.mat(MT.WaxRefractory,  1), ST.make(tStair1, 1, i), ST.make(tStair2, 1, i));
		RM.Laminator    .addRecipe2(T, 16,   32, plate.mat(MT.WaxRefractory,  1), ST.make(tSlab1 , 2, i), ST.make(tSlab2 , 2, i));
		
		RM.Laminator    .addRecipe2(T, 16,  192, foil .mat(MT.WaxRefractory, 24), ST.make(tLog1  , 1, i), ST.make(tLog2  , 1, i));
		RM.Laminator    .addRecipe2(T, 16,   32, foil .mat(MT.WaxRefractory,  4), ST.make(tPlank1, 1, i), ST.make(tPlank2, 1, i));
		RM.Laminator    .addRecipe2(T, 16,   24, foil .mat(MT.WaxRefractory,  3), ST.make(tStair1, 1, i), ST.make(tStair2, 1, i));
		RM.Laminator    .addRecipe2(T, 16,   16, foil .mat(MT.WaxRefractory,  2), ST.make(tSlab1 , 1, i), ST.make(tSlab2 , 1, i));
		
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(tLog1  , 1, i), FL.Potion_FireResistance_1L.make(100), NF, ST.make(tLog2  , 1, i));
		RM.Bath         .addRecipe1(T,  0,   32, ST.make(tPlank1, 1, i), FL.Potion_FireResistance_1L.make( 20), NF, ST.make(tPlank2, 1, i));
		RM.Bath         .addRecipe1(T,  0,   24, ST.make(tStair1, 1, i), FL.Potion_FireResistance_1L.make( 15), NF, ST.make(tStair2, 1, i));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(tSlab1 , 1, i), FL.Potion_FireResistance_1L.make( 10), NF, ST.make(tSlab2 , 1, i));
		}
		}
		
		// Other Recipes
		RM.Squeezer     .addRecipe1(T, 16,   64, ST.make(BlocksGT.Leaves_AB  , 1, 0), NF, FL.Latex.make(L/72), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, ST.make(BlocksGT.Leaves_AB  , 1, 8), NF, FL.Latex.make(L/72), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, ST.make(BlocksGT.Saplings_AB, 1, 0), NF, FL.Latex.make(L/ 4), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, ST.make(BlocksGT.Saplings_AB, 1, 8), NF, FL.Latex.make(L/ 4), NI);
		RM.Squeezer     .addRecipe1(T, 16,   16, OM.dust(MT.WoodRubber         ), NF, FL.Latex       .make(L/9)        , OM.dust(MT.Wood));
		RM.Squeezer     .addRecipe1(T, 16,   16, OM.dust(MT.WOODS.Spruce       ), NF, FL.Resin_Spruce.make(5, FL.Resin), OM.dust(MT.Wood));
		RM.Squeezer     .addRecipe1(T, 16,   16, OM.dust(MT.WOODS.BlueSpruce   ), NF, FL.Resin_Spruce.make(5, FL.Resin), OM.dust(MT.Wood));
		RM.Squeezer     .addRecipe1(T, 16,   16, OM.dust(MT.WOODS.Maple        ), NF, FL.Sap_Maple   .make(5)          , OM.dust(MT.Wood));
		RM.Squeezer     .addRecipe1(T, 16,   16, OM.dust(MT.WOODS.Rainbowood   ), NF, FL.Sap_Rainbow .make(5)          , OM.dust(MT.Wood));
		
		CR.shaped(ST.make(Items.bowl, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES, "k", "X", 'X', OD.plankWood);
		
		CR.shaped(IL.Stick.get(1), CR.DEF_NCC, "  ", " S", 'S', stick.dat(ANY.WoodDefault));
		
		// Generic Wooden Stuff made mostly from Buttons.
		CR.shaped(gearGt             .mat(MT.Wood, 1), CR.DEF_NCC, "BPB", "PsP", "BPB", 'P', OD.plankAnyWood, 'B', OD.buttonWood);
		CR.shaped(gearGtSmall        .mat(MT.Wood, 1), CR.DEF_NCC,  "P ",  " s"       , 'P', OD.buttonWood);
		CR.shaped(casingSmall        .mat(MT.Wood, 2), CR.DEF_NCC,  " P",  "s "       , 'P', OD.buttonWood);
		CR.shaped(plateTiny          .mat(MT.Wood, 9), CR.DEF_NCC,  "s ",  " P"       , 'P', OD.buttonWood);
		CR.shaped(ring               .mat(MT.Wood, 4), CR.DEF_NCC,  "P ",  " k"       , 'P', OD.buttonWood);
		CR.shaped(round              .mat(MT.Wood, 9), CR.DEF_NCC,  "P ",  "fk"       , 'P', OD.buttonWood);
		CR.shaped(toolHeadHammer     .mat(MT.Wood, 1), CR.DEF_NCC, "PP ", "PPg", "PPv", 'P', OD.buttonWood);
		CR.shaped(toolHeadRawArrow   .mat(MT.Wood, 4), CR.DEF_NCC,        "  P", "r v", 'P', OD.buttonWood);
		CR.shaped(toolHeadRawSword   .mat(MT.Wood, 1), CR.DEF_NCC,        " P ", "rPv", 'P', OD.buttonWood);
		CR.shaped(toolHeadRawPickaxe .mat(MT.Wood, 1), CR.DEF_NCC,        "PPP", "rgv", 'P', OD.buttonWood);
		CR.shaped(toolHeadRawShovel  .mat(MT.Wood, 1), CR.DEF_NCC,               "rPv", 'P', OD.buttonWood);
		CR.shaped(toolHeadRawSpade   .mat(MT.Wood, 1), CR.DEF_NCC,        " P ", "r v", 'P', OD.buttonWood);
		CR.shaped(toolHeadRawAxe     .mat(MT.Wood, 1), CR.DEF_NCC,        " PP", "rPv", 'P', OD.buttonWood);
		CR.shaped(toolHeadRawHoe     .mat(MT.Wood, 1), CR.DEF_NCC,        " PP", "r v", 'P', OD.buttonWood);
		CR.shaped(toolHeadRawSense   .mat(MT.Wood, 1), CR.DEF_NCC, "PPP", "   ", "r v", 'P', OD.buttonWood);
		CR.shaped(toolHeadRawPlow    .mat(MT.Wood, 1), CR.DEF_NCC, "PPP", "PPP", "r v", 'P', OD.buttonWood);
		
		// Railcraft related Recipes
		if (IL.RC_Tie_Wood.exists()) {
			if (IL.IE_Treated_Slab.exists())
			RM.Bath.addRecipe1(T, 0, 16, IL.IE_Treated_Slab    .get(1), FL.Oil_Creosote.make(250), NF, IL.RC_Tie_Wood.get(1));
			RM.Bath.addRecipe1(T, 0, 16, IL.Treated_Planks_Slab.get(1), FL.Oil_Creosote.make(250), NF, IL.RC_Tie_Wood.get(1));
		}
		if (IL.RC_Creosote_Wood.exists()) {
			CR.shapeless(IL.Plank.get(NERFED_WOOD?4:5), CR.DEF_NCC, new Object[] {IL.RC_Creosote_Wood});
		}
		
		// Log related Recipes
		for (WoodEntry aEntry : WoodDictionary.LIST_WOODS) {
			if (aEntry.mBeamEntry != null)
			RM.debarking(                aEntry.mLog, ST.validMeta(1, aEntry.mBeamEntry.mBeam), aEntry.mBark);
			RM.pulverizing(              aEntry.mLog, OM.dust(aEntry.mMaterialWood.mTargetPulver, aEntry.mPlankCountBuzz, 1), aEntry.mBark, 50, F);
			RM.sawing(16, 128, F, 4,     aEntry.mLog, ST.validMeta(aEntry.mPlankCountBuzz, aEntry.mPlankEntry.mPlank), aEntry.mBark);
			RM.lathing(16, 80,           aEntry.mLog, ST.validMeta(aEntry.mStickCountLathe, aEntry.mStick), OM.dust(aEntry.mMaterialWood));
			if (IL.RC_Creosote_Wood.exists())
			RM.Bath.addRecipe1(T, 0, 16, aEntry.mLog, FL.Oil_Creosote.make(1000), NF, IL.RC_Creosote_Wood.get(1));
			
			CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CLUB, aEntry.mMaterialWood, MT.Wood), CR.DEF_MIR, " L", "S ", 'L', aEntry.mLog, 'S', OD.stickAnyWood);
			
			if (aEntry.mCreosoteAmount > 0 || aEntry.mCharcoalCount > 0) {
				ItemStack[] tOutputs = new ItemStack[aEntry.mCharcoalCount];
				if (tOutputs.length > 0) Arrays.fill(tOutputs, gem.mat(MT.Charcoal, 1));
				RM.CokeOven.addRecipe1(T, 0, 3600, aEntry.mLog, NF, aEntry.mCreosoteAmount <= 0 ? NF : FL.Oil_Creosote.make(aEntry.mCreosoteAmount), tOutputs);
			}
			
			CR.remove(ST.validMeta(1, aEntry.mLog));
			CR.shaped   (ST.validMeta(NERFED_WOOD?aEntry.mStickCountSaw :aEntry.mStickCountLathe, aEntry.mStick            ), CR.DEF_NCC, "sLf", 'L', aEntry.mLog);
			CR.shaped   (ST.validMeta(NERFED_WOOD?aEntry.mPlankCountSaw :aEntry.mPlankCountBuzz , aEntry.mPlankEntry.mPlank), CR.DEF_NCC, "s", "L", 'L', aEntry.mLog);
			CR.shapeless(ST.validMeta(NERFED_WOOD?aEntry.mPlankCountHand:aEntry.mPlankCountSaw  , aEntry.mPlankEntry.mPlank), CR.DEF_NCC, new Object[] {aEntry.mLog});
		}
		
		// Beam related Recipes
		for (BeamEntry aEntry : WoodDictionary.LIST_BEAMS) {
			RM.generify(                 aEntry.mBeam, IL.Beam.get(1));
			RM.pulverizing(              aEntry.mBeam, OM.dust(aEntry.mMaterialBeam.mTargetPulver, aEntry.mPlankCountBuzz, 1));
			RM.sawing(16, 128, F, 4,     aEntry.mBeam, ST.validMeta(aEntry.mPlankCountBuzz, aEntry.mPlankEntry.mPlank), OM.dust(aEntry.mMaterialBeam));
			RM.lathing(16, 80,           aEntry.mBeam, ST.validMeta(aEntry.mStickCountLathe, aEntry.mStick), OM.dust(aEntry.mMaterialBeam));
			
			CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CLUB, aEntry.mMaterialBeam, MT.Wood), CR.DEF_MIR, " B", "S ", 'B', aEntry.mBeam, 'S', OD.stickAnyWood);
			
			if (aEntry.mCreosoteAmount > 0 || aEntry.mCharcoalCount > 0) {
				ItemStack[] tOutputs = new ItemStack[aEntry.mCharcoalCount];
				if (tOutputs.length > 0) Arrays.fill(tOutputs, gem.mat(MT.Charcoal, 1));
				RM.CokeOven.addRecipe1(T, 0, 3600, aEntry.mBeam, NF, aEntry.mCreosoteAmount <= 0 ? NF : FL.Oil_Creosote.make(aEntry.mCreosoteAmount), tOutputs);
			}
			
			CR.shaped   (ST.validMeta(NERFED_WOOD?aEntry.mStickCountSaw :aEntry.mStickCountLathe, aEntry.mStick            ), CR.DEF_NCC, "sBf", 'B', aEntry.mBeam);
			CR.shaped   (ST.validMeta(NERFED_WOOD?aEntry.mPlankCountSaw :aEntry.mPlankCountBuzz , aEntry.mPlankEntry.mPlank), CR.DEF_NCC, "s", "B", 'B', aEntry.mBeam);
			CR.shapeless(ST.validMeta(NERFED_WOOD?aEntry.mPlankCountHand:aEntry.mPlankCountSaw  , aEntry.mPlankEntry.mPlank), CR.DEF_NCC, new Object[] {aEntry.mBeam});
		}
		
		// Plank related Recipes
		for (PlankEntry aEntry : WoodDictionary.LIST_PLANKS) {
			ItemStack aPlank = ST.validMeta_(1, aEntry.mPlank);
			RM.generify(aEntry.mPlank, IL.Plank.get(1));
			RM.pulverizing(aEntry.mPlank, OM.dust(aEntry.mMaterialPlank.mTargetPulver, 1, 1));
			
			if (aEntry.mMaterialPlank != MT.PetrifiedWood)
			RM.Injector.addRecipe1(T, 16, 128, ST.amount(9, aEntry.mPlank), FL.Mineralwater.make(1000), NF, OP.rockGt.mat(MT.PetrifiedWood, 4));
			
			CR.shaped(gearGt             .mat(aEntry.mMaterialPlank, 1), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC, "BPB", "PsP", "BPB", 'P', aEntry.mPlank, 'B', bolt.dat(aEntry.mMaterialPlank));
			CR.shaped(gearGtSmall        .mat(aEntry.mMaterialPlank, 1), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC,  "P ",  " s"       , 'P', aEntry.mPlank);
			CR.shaped(casingSmall        .mat(aEntry.mMaterialPlank, 2), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC,  " P",  "s "       , 'P', aEntry.mPlank);
			CR.shaped(plateTiny          .mat(aEntry.mMaterialPlank, 9), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC,  "s ",  " P"       , 'P', aEntry.mPlank);
			CR.shaped(ring               .mat(aEntry.mMaterialPlank, 4), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC,  "P ",  " k"       , 'P', aEntry.mPlank);
			CR.shaped(round              .mat(aEntry.mMaterialPlank, 9), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC,  "P ",  "fk"       , 'P', aEntry.mPlank);
			CR.shaped(toolHeadBuilderwand.mat(aEntry.mMaterialPlank, 1), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC,  "ks",  "fP"       , 'P', aEntry.mPlank);
			CR.shaped(toolHeadHammer     .mat(aEntry.mMaterialPlank, 1), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC, "PP ", "PPg", "PPv", 'P', aEntry.mPlank);
			if (!aEntry.mMaterialPlank.contains(TD.Compounds.COATED)) {
			CR.shaped(toolHeadRawArrow   .mat(aEntry.mMaterialPlank, 4), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC,        "  P", "r v", 'P', aEntry.mPlank);
			CR.shaped(toolHeadRawSword   .mat(aEntry.mMaterialPlank, 1), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC,        " P ", "rPv", 'P', aEntry.mPlank);
			CR.shaped(toolHeadRawPickaxe .mat(aEntry.mMaterialPlank, 1), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC,        "PPP", "rgv", 'P', aEntry.mPlank);
			CR.shaped(toolHeadRawShovel  .mat(aEntry.mMaterialPlank, 1), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC,               "rPv", 'P', aEntry.mPlank);
			CR.shaped(toolHeadRawSpade   .mat(aEntry.mMaterialPlank, 1), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC,        " P ", "r v", 'P', aEntry.mPlank);
			CR.shaped(toolHeadRawAxe     .mat(aEntry.mMaterialPlank, 1), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC,        " PP", "rPv", 'P', aEntry.mPlank);
			CR.shaped(toolHeadRawHoe     .mat(aEntry.mMaterialPlank, 1), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC,        " PP", "r v", 'P', aEntry.mPlank);
			CR.shaped(toolHeadRawSense   .mat(aEntry.mMaterialPlank, 1), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC, "PPP", "   ", "r v", 'P', aEntry.mPlank);
			CR.shaped(toolHeadRawPlow    .mat(aEntry.mMaterialPlank, 1), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC, "PPP", "PPP", "r v", 'P', aEntry.mPlank);
			}
			
			CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.ROLLING_PIN, aEntry.mMaterialPlank, MT.Wood), CR.DEF_MIR, "  S", " P ", "S f", 'P', aPlank, 'S', OD.stickAnyWood);
			CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.ROLLING_PIN, aEntry.mMaterialPlank, MT.Wood), CR.DEF_MIR, "  S", " P ", "S k", 'P', aPlank, 'S', OD.stickAnyWood);
			
			if (ST.valid(aEntry.mStick)) {
				RM.lathing(16, 16, aEntry.mPlank, ST.validMeta_(aEntry.mStickCountLathe, aEntry.mStick));
				CR.remove(aPlank, NI, NI, aPlank);
				CR.shaped(ST.validMeta_((NERFED_WOOD?aEntry.mStickCountHand:aEntry.mStickCountSaw)*2, aEntry.mStick), CR.DEF_NCC, "P", "P", 'P', aEntry.mPlank);
				CR.shaped(ST.validMeta_( NERFED_WOOD?aEntry.mStickCountSaw :aEntry.mStickCountLathe , aEntry.mStick), CR.DEF_NCC, "s", "P", 'P', aEntry.mPlank);
			}
			if (!IL.Crate.equal(aEntry.mPlank, F, T) && !IL.Crate_Fireproof.equal(aEntry.mPlank, F, T)) {
				RM.unbox   (IL.Crate          .get(1), crateGtPlate  .mat(aEntry.mMaterialPlank, 1), ST.amount(16, aEntry.mPlank));
				RM.boxunbox(IL.Crate          .get(1), crateGt64Plate.mat(aEntry.mMaterialPlank, 1), ST.amount(64, aEntry.mPlank));
				RM.box     (IL.Crate_Fireproof.get(1), crateGt64Plate.mat(aEntry.mMaterialPlank, 1), ST.amount(64, aEntry.mPlank));
				
				if (aEntry.mMaterialPlank == MT.Wood || aEntry.mMaterialPlank == ANY.Wood) {
					RM.pack      (aEntry.mPlank, 9, blockPlate.mat(aEntry.mMaterialPlank, 1));
					RM.unpack    (blockPlate.mat(aEntry.mMaterialPlank, 1), IL.Plank.get(9));
				} else {
					RM.packunpack(aEntry.mPlank, 9, blockPlate.mat(aEntry.mMaterialPlank, 1));
				}
			}
			
			for (String tFluidName : FluidsGT.LUBRICANT) if (FL.exists(tFluidName)) {
				FluidStack tFluid = FL.make(tFluidName, 1);
				RM.CNC.addRecipe2(T, 16, 64, ST.amount(4, aEntry.mPlank), ST.tag(0), tFluid, NF, gearGt.mat(aEntry.mMaterialPlank, 1));
			}
			
			if (ST.valid(aEntry.mStair)) {
				CR.shaped(ST.validMeta_(4, aEntry.mStair), CR.DEF_NCC_MIR, "vP", "PP", 'P', aEntry.mPlank);
			}
			
			if (ST.valid(aEntry.mSlab)) {
				CR.shaped(ST.validMeta_(2, aEntry.mSlab), CR.DEF_NCC_MIR, "vP", 'P', aEntry.mPlank);
				RM.sawing(16, 72, F, 3, aEntry.mPlank, ST.validMeta_(2, aEntry.mSlab));
			}
		}
		
		// Stair related Recipes
		for (PlankEntry aEntry : WoodDictionary.LIST_STAIRS) {
			RM.generify(aEntry.mStair, IL.Plank_Stairs.get(1));
			RM.pulverizing(aEntry.mStair, OM.dust(aEntry.mMaterialPlank.mTargetPulver, 3, 4));
			
			ItemStack aPlank = ST.validMeta_(1, aEntry.mPlank);
			CR.remove(NI, NI, aPlank, NI, aPlank, aPlank, aPlank, aPlank, aPlank);
			CR.remove(aPlank, NI, NI, aPlank, aPlank, NI, aPlank, aPlank, aPlank);
			
			if (aEntry.mMaterialPlank != MT.PetrifiedWood)
			RM.Injector.addRecipe1(T, 16, 96, ST.amount(9, aEntry.mStair), FL.Mineralwater.make(750), NF, OP.rockGt.mat(MT.PetrifiedWood, 3));
			
			if (ST.valid(aEntry.mSlab)) {
				CR.shaped(ST.validMeta_(1, aEntry.mSlab ), CR.DEF_NCC_MIR, "vP", 'P', aEntry.mStair);
				RM.sawing(16, 72, F, 3, aEntry.mStair, ST.validMeta_(1, aEntry.mSlab), dustSmall.mat(aEntry.mMaterialPlank, 1));
			}
		}
		
		// Slab related Recipes
		for (PlankEntry aEntry : WoodDictionary.LIST_SLABS) {
			RM.generify(aEntry.mSlab, IL.Plank_Slab.get(1));
			RM.pulverizing(aEntry.mSlab, OM.dust(aEntry.mMaterialPlank.mTargetPulver, 1, 2));
			
			ItemStack aPlank = ST.validMeta_(1, aEntry.mPlank);
			CR.remove(aPlank, aPlank, aPlank);
			
			if (aEntry.mMaterialPlank != MT.PetrifiedWood)
			RM.Injector.addRecipe1(T, 16, 64, ST.amount(9, aEntry.mSlab), FL.Mineralwater.make(500), NF, OP.rockGt.mat(MT.PetrifiedWood, 2));
			
			if (ST.valid(aEntry.mStair)) {
				CR.shaped(ST.validMeta_(2, aEntry.mStair), CR.DEF_NCC_MIR, "vP", "PP", 'P', aEntry.mSlab);
			}
		}
		
		// Making sure that all normal Wood Crates/Blocks have at least some Output.
		for (ItemStack tCrate : OreDictManager.getOres(crateGtPlate  .dat(ANY.WoodUntreated), F)) RM.unbox(IL.Crate.get(1), tCrate, IL.Plank.get(16));
		for (ItemStack tCrate : OreDictManager.getOres(crateGt64Plate.dat(ANY.WoodUntreated), F)) RM.unbox(IL.Crate.get(1), tCrate, IL.Plank.get(64));
		for (ItemStack tBlock : OreDictManager.getOres(blockPlate    .dat(ANY.WoodUntreated), F)) RM.unpack(tBlock, IL.Plank.get(9));
	}
}
