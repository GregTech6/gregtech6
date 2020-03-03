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

package gregtech.loaders.c;

import static gregapi.data.CS.*;

import java.util.Arrays;

import gregapi.block.metatype.BlockMetaType;
import gregapi.data.CS.BlocksGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.wooddict.BeamEntry;
import gregapi.wooddict.PlankEntry;
import gregapi.wooddict.WoodDictionary;
import gregapi.wooddict.WoodEntry;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class Loader_Recipes_Woods implements Runnable {
	@Override public void run() {
		for (int i = 0; i <  4; i++) {
		RM.Laminator    .addRecipe2(T, 16,  192, OP.plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.Log1 , 1, i), ST.make(BlocksGT.Log1FireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.LogA , 1, i), ST.make(BlocksGT.LogAFireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.LogB , 1, i), ST.make(BlocksGT.LogBFireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.Beam1, 1, i), ST.make(BlocksGT.Beam1FireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.Beam2, 1, i), ST.make(BlocksGT.Beam2FireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.Beam3, 1, i), ST.make(BlocksGT.Beam3FireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.BeamA, 1, i), ST.make(BlocksGT.BeamAFireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.plate.mat(MT.WaxRefractory, 6), ST.make(BlocksGT.BeamB, 1, i), ST.make(BlocksGT.BeamBFireProof, 1, i));
		
		RM.Laminator    .addRecipe2(T, 16,  192, OP.foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.Log1 , 1, i), ST.make(BlocksGT.Log1FireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.LogA , 1, i), ST.make(BlocksGT.LogAFireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.LogB , 1, i), ST.make(BlocksGT.LogBFireProof , 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.Beam1, 1, i), ST.make(BlocksGT.Beam1FireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.Beam2, 1, i), ST.make(BlocksGT.Beam2FireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.Beam3, 1, i), ST.make(BlocksGT.Beam3FireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.BeamA, 1, i), ST.make(BlocksGT.BeamAFireProof, 1, i));
		RM.Laminator    .addRecipe2(T, 16,  192, OP.foil.mat(MT.WaxRefractory, 24), ST.make(BlocksGT.BeamB, 1, i), ST.make(BlocksGT.BeamBFireProof, 1, i));
		
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.Log1 , 1, i), FL.Potion_FireResistance_1L.make(300), NF, ST.make(BlocksGT.Log1FireProof , 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.LogA , 1, i), FL.Potion_FireResistance_1L.make(300), NF, ST.make(BlocksGT.LogAFireProof , 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.LogB , 1, i), FL.Potion_FireResistance_1L.make(300), NF, ST.make(BlocksGT.LogBFireProof , 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.Beam1, 1, i), FL.Potion_FireResistance_1L.make(300), NF, ST.make(BlocksGT.Beam1FireProof, 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.Beam2, 1, i), FL.Potion_FireResistance_1L.make(300), NF, ST.make(BlocksGT.Beam2FireProof, 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.Beam3, 1, i), FL.Potion_FireResistance_1L.make(300), NF, ST.make(BlocksGT.Beam3FireProof, 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.BeamA, 1, i), FL.Potion_FireResistance_1L.make(300), NF, ST.make(BlocksGT.BeamAFireProof, 1, i));
		RM.Bath         .addRecipe1(T,  0,  192, ST.make(BlocksGT.BeamB, 1, i), FL.Potion_FireResistance_1L.make(300), NF, ST.make(BlocksGT.BeamBFireProof, 1, i));
		}
		for (int i = 0; i < 16; i++) {
		RM.Laminator    .addRecipe2(T, 16,   32, OP.plate.mat(MT.WaxRefractory, 1), ST.make(BlocksGT.Planks                             , 1, i), ST.make(BlocksGT.PlanksFireProof                           , 1, i));
		RM.Laminator    .addRecipe2(T, 16,   32, OP.plate.mat(MT.WaxRefractory, 1), ST.make(((BlockMetaType)BlocksGT.Planks).mSlabs[0]  , 2, i), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof).mSlabs[0], 2, i));
		
		RM.Laminator    .addRecipe2(T, 16,   32, OP.foil.mat(MT.WaxRefractory,  4), ST.make(BlocksGT.Planks                             , 1, i), ST.make(BlocksGT.PlanksFireProof                           , 1, i));
		RM.Laminator    .addRecipe2(T, 16,   16, OP.foil.mat(MT.WaxRefractory,  2), ST.make(((BlockMetaType)BlocksGT.Planks).mSlabs[0]  , 1, i), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof).mSlabs[0], 1, i));
		
		RM.Bath         .addRecipe1(T,  0,   32, ST.make(BlocksGT.Planks                            , 1, i), FL.Potion_FireResistance_1L.make(50), NF, ST.make(BlocksGT.PlanksFireProof                             , 1, i));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(((BlockMetaType)BlocksGT.Planks).mSlabs[0] , 1, i), FL.Potion_FireResistance_1L.make(25), NF, ST.make(((BlockMetaType)BlocksGT.PlanksFireProof).mSlabs[0]  , 1, i));
		}
		
		RM.Squeezer     .addRecipe1(T, 16,   64, ST.make(BlocksGT.Leaves, 1, 0), NF, FL.Latex.make(L/72), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, ST.make(BlocksGT.Leaves, 1, 8), NF, FL.Latex.make(L/72), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, ST.make(BlocksGT.Sapling, 1, 0), NF, FL.Latex.make(L/4), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, ST.make(BlocksGT.Sapling, 1, 8), NF, FL.Latex.make(L/4), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, OM.dust(MT.WoodRubber), NF, FL.Latex.make(L/9), OM.dust(MT.Wood));
		RM.Squeezer     .addRecipe1(T, 16,   64, ST.make(Blocks.log, 1, 1), NF, FL.Resin_Spruce.make(50, FL.Resin), OM.dust(MT.Wood));
		RM.Squeezer     .addRecipe1(T, 16,   64, ST.make(BlocksGT.LogA, 1, 1), NF, FL.Sap_Maple.make(50), OM.dust(MT.Wood));
		RM.Squeezer     .addRecipe1(T, 16,   64, ST.make(BlocksGT.LogB, 1, 3), NF, FL.Sap_Rainbow.make(50), OM.dust(MT.Wood));
		
		
		if (IL.RC_Tie_Wood.exists()) {
			if (IL.IE_Treated_Slab.exists())
			RM.Bath.addRecipe1(T, 0, 16, IL.IE_Treated_Slab    .get(1), FL.Oil_Creosote.make(250), NF, IL.RC_Tie_Wood.get(1));
			RM.Bath.addRecipe1(T, 0, 16, IL.Treated_Planks_Slab.get(1), FL.Oil_Creosote.make(250), NF, IL.RC_Tie_Wood.get(1));
		}
		if (IL.RC_Creosote_Wood.exists()) {
			CR.shaped   (IL.Plank.get(NERFED_WOOD?4:5), CR.DEF_NAC_NCC, "s", "L", 'L', IL.RC_Creosote_Wood);
			CR.shapeless(IL.Plank.get(NERFED_WOOD?2:4), CR.DEF_NAC_NCC, new Object[] {IL.RC_Creosote_Wood});
		}
		
		
		for (WoodEntry aEntry : WoodDictionary.WOODS.values()) {
			if (aEntry.mBeamEntry != null)
			RM.debarking(                      aEntry.mLog, ST.validMeta(1, aEntry.mBeamEntry.mBeam), aEntry.mBark);
			RM.pulverizing(                    aEntry.mLog, OP.dust.mat(aEntry.mMaterialWood, aEntry.mPlankCountBuzz), aEntry.mBark, 50, F);
			RM.sawing(16, 128, F, 4,           aEntry.mLog, ST.validMeta(aEntry.mPlankCountBuzz, aEntry.mPlankEntry.mPlank), aEntry.mBark);
			RM.lathing(16, 80,                 aEntry.mLog, ST.validMeta(aEntry.mStickCountLathe, aEntry.mStick), OM.dust(aEntry.mMaterialWood));
			if (IL.RC_Creosote_Wood.exists())
			RM.Bath.addRecipe1(T, 0, 16,       aEntry.mLog, FL.Oil_Creosote.make(1000), NF, IL.RC_Creosote_Wood.get(1));
			
			if (aEntry.mCreosoteAmount > 0 || aEntry.mCharcoalCount > 0) {
				ItemStack[] tOutputs = new ItemStack[aEntry.mCharcoalCount];
				if (tOutputs.length > 0) Arrays.fill(tOutputs, OP.gem.mat(MT.Charcoal, 1));
				RM.CokeOven.addRecipe1(T, 0, 3600, aEntry.mLog, NF, aEntry.mCreosoteAmount <= 0 ? NF : FL.Oil_Creosote.make(aEntry.mCreosoteAmount), tOutputs);
			}
			
			CR.remove(ST.validMeta(1, aEntry.mLog));
			CR.shaped   (ST.validMeta(NERFED_WOOD?aEntry.mStickCountSaw :aEntry.mStickCountLathe, aEntry.mStick            ), CR.DEF_NAC_NCC, "sLf", 'L', aEntry.mLog);
			CR.shaped   (ST.validMeta(NERFED_WOOD?aEntry.mPlankCountSaw :aEntry.mPlankCountBuzz , aEntry.mPlankEntry.mPlank), CR.DEF_NAC_NCC, "s", "L", 'L', aEntry.mLog);
			CR.shapeless(ST.validMeta(NERFED_WOOD?aEntry.mPlankCountHand:aEntry.mPlankCountSaw  , aEntry.mPlankEntry.mPlank), CR.DEF_NAC_NCC, new Object[] {aEntry.mLog});
		}
		
		
		for (BeamEntry aEntry : WoodDictionary.BEAMS.values()) {
			RM.generify(                       aEntry.mBeam, IL.Beam.get(1));
			RM.pulverizing(                    aEntry.mBeam, OP.dust.mat(aEntry.mMaterialBeam, aEntry.mPlankCountBuzz));
			RM.sawing(16, 128, F, 4,           aEntry.mBeam, ST.validMeta(aEntry.mPlankCountBuzz, aEntry.mPlankEntry.mPlank), OM.dust(aEntry.mMaterialBeam));
			RM.lathing(16, 80,                 aEntry.mBeam, ST.validMeta(aEntry.mStickCountLathe, aEntry.mStick), OM.dust(aEntry.mMaterialBeam));
			
			if (aEntry.mCreosoteAmount > 0 || aEntry.mCharcoalCount > 0) {
				ItemStack[] tOutputs = new ItemStack[aEntry.mCharcoalCount];
				if (tOutputs.length > 0) Arrays.fill(tOutputs, OP.gem.mat(MT.Charcoal, 1));
				RM.CokeOven.addRecipe1(T, 0, 3600, aEntry.mBeam, NF, aEntry.mCreosoteAmount <= 0 ? NF : FL.Oil_Creosote.make(aEntry.mCreosoteAmount), tOutputs);
			}
			
			CR.shaped   (ST.validMeta(NERFED_WOOD?aEntry.mStickCountSaw :aEntry.mStickCountLathe, aEntry.mStick            ), CR.DEF_NAC_NCC, "sBf", 'B', aEntry.mBeam);
			CR.shaped   (ST.validMeta(NERFED_WOOD?aEntry.mPlankCountSaw :aEntry.mPlankCountBuzz , aEntry.mPlankEntry.mPlank), CR.DEF_NAC_NCC, "s", "B", 'B', aEntry.mBeam);
			CR.shapeless(ST.validMeta(NERFED_WOOD?aEntry.mPlankCountHand:aEntry.mPlankCountSaw  , aEntry.mPlankEntry.mPlank), CR.DEF_NAC_NCC, new Object[] {aEntry.mBeam});
		}
		
		
		for (PlankEntry aEntry : WoodDictionary.PLANKS.values()) {
			ItemStack aPlank = ST.validMeta_(1, aEntry.mPlank);
			RM.generify(aEntry.mPlank, IL.Plank.get(1));
			RM.pulverizing(aEntry.mPlank, OP.dust.mat(aEntry.mMaterialPlank, 1));
			if (ST.valid(aEntry.mStick)) {
				RM.lathing(16, 16, aEntry.mPlank, ST.validMeta_(aEntry.mStickCountLathe, aEntry.mStick));
				CR.remove(aPlank, NI, NI, aPlank);
				CR.shaped(ST.validMeta_((NERFED_WOOD?aEntry.mStickCountHand:aEntry.mStickCountSaw)*2, aEntry.mStick), CR.DEF_NAC_NCC, "P", "P", 'P', aEntry.mPlank);
				CR.shaped(ST.validMeta_( NERFED_WOOD?aEntry.mStickCountSaw :aEntry.mStickCountLathe , aEntry.mStick), CR.DEF_NAC_NCC, "s", "P", 'P', aEntry.mPlank);
			}
			if (!IL.Crate.equal(aEntry.mPlank, F, T) && !IL.Crate_Fireproof.equal(aEntry.mPlank, F, T)) {
			RM.Boxinator.addRecipe2(T, 16, 16, ST.amount(16, aEntry.mPlank), IL.Crate.get(1), OP.crateGtPlate.mat(aEntry.mMaterialPlank, 1));
			RM.Boxinator.addRecipe2(T, 16, 16, ST.amount(16, aEntry.mPlank), IL.Crate_Fireproof.get(1), OP.crateGtPlate.mat(aEntry.mMaterialPlank, 1));
			}
			RM.Boxinator.addRecipe2(T, 16, 16, ST.amount( 9, aEntry.mPlank), ST.tag(9), OP.blockPlate.mat(aEntry.mMaterialPlank, 1));
//          RM.CNC.addRecipe2(T, 16, 64, ST.amount(4, aEntry.mPlank), NI, OP.gearGt.mat(aEntry.mMaterialPlank, 1));
			
			if (ST.valid(aEntry.mStair)) {
				CR.shaped(ST.validMeta_(4, aEntry.mStair), CR.DEF_NCC_MIR, "vP", "PP", 'P', aEntry.mPlank);
			}
			
			if (ST.valid(aEntry.mSlab)) {
				CR.shaped(ST.validMeta_(2, aEntry.mSlab), CR.DEF_NAC_NCC_MIR, "vP", 'P', aEntry.mPlank);
				RM.sawing(16, 72, F, 3, aEntry.mPlank, ST.validMeta_(2, aEntry.mSlab));
			}
		}
		
		
		for (PlankEntry aEntry : WoodDictionary.STAIRS.values()) {
			RM.generify(aEntry.mStair, IL.Plank_Stairs.get(1));
			RM.pulverizing(aEntry.mStair, OP.dustSmall.mat(aEntry.mMaterialPlank, 3));
			ItemStack aPlank = ST.validMeta_(1, aEntry.mPlank);
			CR.remove(NI, NI, aPlank, NI, aPlank, aPlank, aPlank, aPlank, aPlank);
			CR.remove(aPlank, NI, NI, aPlank, aPlank, NI, aPlank, aPlank, aPlank);
			
			if (ST.valid(aEntry.mSlab)) {
				CR.shaped(ST.validMeta_(1, aEntry.mSlab ), CR.DEF_NCC_MIR, "vP", 'P', aEntry.mStair);
				RM.sawing(16, 72, F, 3, aEntry.mStair, ST.validMeta_(1, aEntry.mSlab), OP.dustSmall.mat(aEntry.mMaterialPlank, 1));
			}
		}
		
		
		for (PlankEntry aEntry : WoodDictionary.SLABS.values()) {
			RM.generify(aEntry.mSlab, IL.Plank_Slab.get(1));
			RM.pulverizing(aEntry.mSlab, OP.dustSmall.mat(aEntry.mMaterialPlank, 2));
			ItemStack aPlank = ST.validMeta_(1, aEntry.mPlank);
			CR.remove(aPlank, aPlank, aPlank);
			
			if (ST.valid(aEntry.mStair)) {
				CR.shaped(ST.validMeta_(2, aEntry.mStair), CR.DEF_NCC_MIR, "vP", "PP", 'P', aEntry.mSlab);
			}
		}
	}
}
