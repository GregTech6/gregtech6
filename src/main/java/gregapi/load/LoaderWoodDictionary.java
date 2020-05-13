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

package gregapi.load;

import static gregapi.data.CS.*;

import gregapi.block.metatype.BlockMetaType;
import gregapi.code.ItemStackContainer;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.PlankData;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.wooddict.BeamEntry;
import gregapi.wooddict.LeafEntry;
import gregapi.wooddict.PlankEntry;
import gregapi.wooddict.SaplingEntry;
import gregapi.wooddict.WoodDictionary;
import gregapi.wooddict.WoodEntry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class LoaderWoodDictionary implements Runnable {
	@Override
	public void run() {
		OUT.println("GT_API_POST: Initialize Wood Dictionary.");
		
		// Initializing the List of Decorative Planks
		PlankData.PLANKS[ 43] = ST.make(MD.TCFM, "TaintPlank", 1, 0);
		Block tBlock = ST.block(MD.MoCr, "MoCWoodPlank");
		PlankData.PLANKS[ 44] = ST.make(tBlock, 1, 0);
		PlankData.PLANKS[ 45] = ST.make(tBlock, 1, 1);
		PlankData.PLANKS[ 46] = ST.make(tBlock, 1, 2);
		PlankData.PLANKS[ 96] = ST.make(MD.RC, "cube", 1, 8);
		PlankData.PLANKS[104] = ST.make(MD.BTL, "rubberTreePlanks", 1, 0);
		PlankData.PLANKS[106] = IL.BTL_Portal_Bark.get(1);
		PlankData.PLANKS[107] = IL.BTL_Weedwood_Bark.get(1);
		PlankData.PLANKS[108] = IL.BTL_Weedwood_RottenBark.get(1);
		PlankData.PLANKS[120] = ST.make(MD.ERE, "petrifiedWoodPlanks", 1, 0);
		PlankData.PLANKS[121] = ST.make(MD.ERE, "planks_scorched", 1, 0);
		PlankData.PLANKS[122] = ST.make(MD.ERE, "planks_varnished", 1, 0);
		PlankData.PLANKS[125] = IL.MaCu_Polished_Planks.get(1);
		// No Gaps in this List, so 235 is next!
		
		
		// Vanilla Trees
		OreDictionary.registerOre(OD.plankWood.toString(), ST.make(Blocks.planks, 1, 0));
		OreDictionary.registerOre(OD.plankWood.toString(), ST.make(Blocks.planks, 1, 1));
		OreDictionary.registerOre(OD.plankWood.toString(), ST.make(Blocks.planks, 1, 2));
		OreDictionary.registerOre(OD.plankWood.toString(), ST.make(Blocks.planks, 1, 3));
		OreDictionary.registerOre(OD.plankWood.toString(), ST.make(Blocks.planks, 1, 4));
		OreDictionary.registerOre(OD.plankWood.toString(), ST.make(Blocks.planks, 1, 5));
		new SaplingEntry(ST.make(Blocks.sapling, 1, 0), new WoodEntry(ST.make(Blocks.log    , 1, 0), new BeamEntry(ST.make(BlocksGT.Beam1, 1, 0), new PlankEntry(ST.make(Blocks.planks, 1, 0), ST.make(Blocks.wooden_slab, 1, 0), ST.make(Blocks.oak_stairs     , 1, 0),  0))), ST.make(Blocks.leaves   , 1, 0));
		new SaplingEntry(ST.make(Blocks.sapling, 1, 1), new WoodEntry(ST.make(Blocks.log    , 1, 1), new BeamEntry(ST.make(BlocksGT.Beam1, 1, 1), new PlankEntry(ST.make(Blocks.planks, 1, 1), ST.make(Blocks.wooden_slab, 1, 1), ST.make(Blocks.spruce_stairs  , 1, 0),  1))), ST.make(Blocks.leaves   , 1, 1));
		new SaplingEntry(ST.make(Blocks.sapling, 1, 2), new WoodEntry(ST.make(Blocks.log    , 1, 2), new BeamEntry(ST.make(BlocksGT.Beam1, 1, 2), new PlankEntry(ST.make(Blocks.planks, 1, 2), ST.make(Blocks.wooden_slab, 1, 2), ST.make(Blocks.birch_stairs   , 1, 0),  2))), ST.make(Blocks.leaves   , 1, 2));
		new SaplingEntry(ST.make(Blocks.sapling, 1, 3), new WoodEntry(ST.make(Blocks.log    , 1, 3), new BeamEntry(ST.make(BlocksGT.Beam1, 1, 3), new PlankEntry(ST.make(Blocks.planks, 1, 3), ST.make(Blocks.wooden_slab, 1, 3), ST.make(Blocks.jungle_stairs  , 1, 0),  3))), ST.make(Blocks.leaves   , 1, 3));
		new SaplingEntry(ST.make(Blocks.sapling, 1, 4), new WoodEntry(ST.make(Blocks.log2   , 1, 0), new BeamEntry(ST.make(BlocksGT.Beam2, 1, 0), new PlankEntry(ST.make(Blocks.planks, 1, 4), ST.make(Blocks.wooden_slab, 1, 4), ST.make(Blocks.acacia_stairs  , 1, 0),  4))), ST.make(Blocks.leaves2  , 1, 0));
		new SaplingEntry(ST.make(Blocks.sapling, 1, 5), new WoodEntry(ST.make(Blocks.log2   , 1, 1), new BeamEntry(ST.make(BlocksGT.Beam2, 1, 1), new PlankEntry(ST.make(Blocks.planks, 1, 5), ST.make(Blocks.wooden_slab, 1, 5), ST.make(Blocks.dark_oak_stairs, 1, 0),  5))), ST.make(Blocks.leaves2  , 1, 1));
		new BeamEntry(ST.make(BlocksGT.Beam1FireProof, 1, 0), WoodDictionary.PLANKS.get(Blocks.planks, 0));
		new BeamEntry(ST.make(BlocksGT.Beam1FireProof, 1, 1), WoodDictionary.PLANKS.get(Blocks.planks, 1));
		new BeamEntry(ST.make(BlocksGT.Beam1FireProof, 1, 2), WoodDictionary.PLANKS.get(Blocks.planks, 2));
		new BeamEntry(ST.make(BlocksGT.Beam1FireProof, 1, 3), WoodDictionary.PLANKS.get(Blocks.planks, 3));
		new BeamEntry(ST.make(BlocksGT.Beam2FireProof, 1, 0), WoodDictionary.PLANKS.get(Blocks.planks, 4));
		new BeamEntry(ST.make(BlocksGT.Beam2FireProof, 1, 1), WoodDictionary.PLANKS.get(Blocks.planks, 5));
		
		// GregTech Trees
		if (MD.GT.mLoaded) {
			WoodDictionary.DEFAULT_BEAM = new BeamEntry(IL.Beam.get(1), WoodDictionary.DEFAULT_PLANK = new PlankEntry(IL.Plank.get(1, ST.make(Blocks.planks, 1, 0)), IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), 55));
			new BeamEntry(ST.make(BlocksGT.Beam2FireProof, 1, 3), new PlankEntry(ST.make(BlocksGT.PlanksFireProof, 1, 9), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof).mSlabs[0], 1, 9)));
			
			new WoodEntry(ST.make(BlocksGT.LogA         , 1, 0), new BeamEntry(ST.make(BlocksGT.BeamA           , 1, 0), new PlankEntry(ST.make(BlocksGT.Planks         , 1, 0), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1, 0),  6), 1, 300), 1, 350);
			new WoodEntry(ST.make(BlocksGT.LogAFireProof, 1, 0), new BeamEntry(ST.make(BlocksGT.BeamAFireProof  , 1, 0), new PlankEntry(ST.make(BlocksGT.PlanksFireProof, 1, 0), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1, 0),  0), 1, 300), 1, 350);
			new WoodEntry(ST.make(BlocksGT.LogA         , 1, 1), new BeamEntry(ST.make(BlocksGT.BeamA           , 1, 1), new PlankEntry(ST.make(BlocksGT.Planks         , 1, 1), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1, 1),  7), 1, 350), 1, 400);
			new WoodEntry(ST.make(BlocksGT.LogAFireProof, 1, 1), new BeamEntry(ST.make(BlocksGT.BeamAFireProof  , 1, 1), new PlankEntry(ST.make(BlocksGT.PlanksFireProof, 1, 1), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1, 1),  0), 1, 350), 1, 400);
			new WoodEntry(ST.make(BlocksGT.LogA         , 1, 2), new BeamEntry(ST.make(BlocksGT.BeamA           , 1, 2), new PlankEntry(ST.make(BlocksGT.Planks         , 1, 2), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1, 2), 37), 2, 400), 2, 500);
			new WoodEntry(ST.make(BlocksGT.LogAFireProof, 1, 2), new BeamEntry(ST.make(BlocksGT.BeamAFireProof  , 1, 2), new PlankEntry(ST.make(BlocksGT.PlanksFireProof, 1, 2), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1, 2),  0), 2, 400), 2, 500);
			new WoodEntry(ST.make(BlocksGT.LogA         , 1, 3), new BeamEntry(ST.make(BlocksGT.BeamA           , 1, 3), new PlankEntry(ST.make(BlocksGT.Planks         , 1, 3), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1, 3), 38)));
			new WoodEntry(ST.make(BlocksGT.LogAFireProof, 1, 3), new BeamEntry(ST.make(BlocksGT.BeamAFireProof  , 1, 3), new PlankEntry(ST.make(BlocksGT.PlanksFireProof, 1, 3), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1, 3),  0)));
			new SaplingEntry(ST.make(BlocksGT.Sapling, 1, 0), WoodDictionary.WOODS.get(BlocksGT.LogA, 0), ST.make(BlocksGT.Leaves, 1, 0));
			new SaplingEntry(ST.make(BlocksGT.Sapling, 1, 1), WoodDictionary.WOODS.get(BlocksGT.LogA, 1), ST.make(BlocksGT.Leaves, 1, 1));
			new SaplingEntry(ST.make(BlocksGT.Sapling, 1, 2), WoodDictionary.WOODS.get(BlocksGT.LogA, 2), ST.make(BlocksGT.Leaves, 1, 2));
			new SaplingEntry(ST.make(BlocksGT.Sapling, 1, 3), WoodDictionary.WOODS.get(BlocksGT.LogA, 3), ST.make(BlocksGT.Leaves, 1, 3));
			
			
			new WoodEntry(ST.make(BlocksGT.LogB         , 1, 0), new BeamEntry(ST.make(BlocksGT.BeamB           , 1, 0), new PlankEntry(ST.make(BlocksGT.Planks         , 1, 4), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1, 4), 39)));
			new WoodEntry(ST.make(BlocksGT.LogBFireProof, 1, 0), new BeamEntry(ST.make(BlocksGT.BeamBFireProof  , 1, 0), new PlankEntry(ST.make(BlocksGT.PlanksFireProof, 1, 4), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1, 4),  0)));
			new WoodEntry(ST.make(BlocksGT.LogB         , 1, 1), new BeamEntry(ST.make(BlocksGT.BeamB           , 1, 1), new PlankEntry(ST.make(BlocksGT.Planks         , 1, 5), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1, 5), 97)), ST.make(MD.HaC, "cinnamonItem", 1, 0, IL.Food_Cinnamon), MT.Cinnamon);
			new WoodEntry(ST.make(BlocksGT.LogBFireProof, 1, 1), new BeamEntry(ST.make(BlocksGT.BeamBFireProof  , 1, 1), new PlankEntry(ST.make(BlocksGT.PlanksFireProof, 1, 5), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1, 5),  0)), ST.make(MD.HaC, "cinnamonItem", 1, 0, IL.Food_Cinnamon), MT.Cinnamon);
			new WoodEntry(ST.make(BlocksGT.LogB         , 1, 2), new BeamEntry(ST.make(BlocksGT.BeamB           , 1, 2), new PlankEntry(ST.make(BlocksGT.Planks         , 1, 6), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1, 6), 98)));
			new WoodEntry(ST.make(BlocksGT.LogBFireProof, 1, 2), new BeamEntry(ST.make(BlocksGT.BeamBFireProof  , 1, 2), new PlankEntry(ST.make(BlocksGT.PlanksFireProof, 1, 6), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1, 6),  0)));
			new WoodEntry(ST.make(BlocksGT.LogB         , 1, 3), new BeamEntry(ST.make(BlocksGT.BeamB           , 1, 3), new PlankEntry(ST.make(BlocksGT.Planks         , 1, 7), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1, 7), 99), 1, 400), 1, 500);
			new WoodEntry(ST.make(BlocksGT.LogBFireProof, 1, 3), new BeamEntry(ST.make(BlocksGT.BeamBFireProof  , 1, 3), new PlankEntry(ST.make(BlocksGT.PlanksFireProof, 1, 7), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1, 7),  0), 1, 400), 1, 500);
			new SaplingEntry(ST.make(BlocksGT.Sapling, 1, 4), WoodDictionary.WOODS.get(BlocksGT.LogB, 0), ST.make(BlocksGT.Leaves, 1, 4));
			new SaplingEntry(ST.make(BlocksGT.Sapling, 1, 5), WoodDictionary.WOODS.get(BlocksGT.LogB, 1), ST.make(BlocksGT.Leaves, 1, 5));
			new SaplingEntry(ST.make(BlocksGT.Sapling, 1, 6), WoodDictionary.WOODS.get(BlocksGT.LogB, 2), ST.make(BlocksGT.Leaves, 1, 6));
			new SaplingEntry(ST.make(BlocksGT.Sapling, 1, 7), WoodDictionary.WOODS.get(BlocksGT.LogB, 3), ST.make(BlocksGT.Leaves, 1, 7));
			
			
			new PlankEntry(ST.make(BlocksGT.Planks          , 1, 8), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1, 8), MT.Wood         , 54, null);
			new PlankEntry(ST.make(BlocksGT.PlanksFireProof , 1, 8), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1, 8), MT.Wood         ,  0, null);
			new PlankEntry(ST.make(BlocksGT.Planks          , 1,10), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1,10), MT.WoodSealed   , 62, OP.stick.mat(MT.Wood, 1));
			new PlankEntry(ST.make(BlocksGT.PlanksFireProof , 1,10), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1,10), MT.WoodSealed   ,  0, OP.stick.mat(MT.Wood, 1));
			new PlankEntry(ST.make(BlocksGT.Planks          , 1,11), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1,11), MT.Wood         , 63);
			new PlankEntry(ST.make(BlocksGT.PlanksFireProof , 1,11), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1,11), MT.Wood         ,  0);
			
			
			new WoodEntry(ST.make(BlocksGT.Log1         , 1, 0), null, new PlankEntry(ST.make(BlocksGT.Planks           , 1,12), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1,12), 100), 1, 50, 1, 2, 3, IL.Bark_Dry.get(1)                    , MT.Wood, MT.Bark  , null, 0, 0);
			new WoodEntry(ST.make(BlocksGT.Log1FireProof, 1, 0), null, new PlankEntry(ST.make(BlocksGT.PlanksFireProof  , 1,12), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1,12),   0), 1, 50, 1, 2, 3, IL.Bark_Dry.get(1)                    , MT.Wood, MT.Bark  , null, 0, 0);
			new WoodEntry(ST.make(BlocksGT.Log1         , 1, 1), null, new PlankEntry(ST.make(BlocksGT.Planks           , 1,13), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1,13), 101), 1, 50, 1, 2, 3, IL.FR_Mulch.get(1, OM.dust(MT.Wood))  , MT.Wood, MT.Wood  , null, 0, 0);
			new WoodEntry(ST.make(BlocksGT.Log1FireProof, 1, 1), null, new PlankEntry(ST.make(BlocksGT.PlanksFireProof  , 1,13), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1,13),   0), 1, 50, 1, 2, 3, IL.FR_Mulch.get(1, OM.dust(MT.Wood))  , MT.Wood, MT.Wood  , null, 0, 0);
			new WoodEntry(ST.make(BlocksGT.Log1         , 1, 2), null, new PlankEntry(ST.make(BlocksGT.Planks           , 1,14), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1,14), 102), 1, 50, 1, 2, 3, IL.FR_Mulch.get(1, OM.dust(MT.Wood))  , MT.Wood, MT.Wood  , null, 0, 0);
			new WoodEntry(ST.make(BlocksGT.Log1FireProof, 1, 2), null, new PlankEntry(ST.make(BlocksGT.PlanksFireProof  , 1,14), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1,14),   0), 1, 50, 1, 2, 3, IL.FR_Mulch.get(1, OM.dust(MT.Wood))  , MT.Wood, MT.Wood  , null, 0, 0);
			new WoodEntry(ST.make(BlocksGT.Log1         , 1, 3), null, new PlankEntry(ST.make(BlocksGT.Planks           , 1,15), ST.make(((BlockMetaType)BlocksGT.Planks            ).mSlabs[0], 1,15), 103), 1, 50, 1, 2, 3, OP.dust.mat(MT.Ice, 1)                , MT.Wood, MT.Ice   , null, 0, 0);
			new WoodEntry(ST.make(BlocksGT.Log1FireProof, 1, 3), null, new PlankEntry(ST.make(BlocksGT.PlanksFireProof  , 1,15), ST.make(((BlockMetaType)BlocksGT.PlanksFireProof   ).mSlabs[0], 1,15),   0), 1, 50, 1, 2, 3, OP.dust.mat(MT.Ice, 1)                , MT.Wood, MT.Ice   , null, 0, 0);
			
			// Rubber Tree Beams
			BeamEntry tRubberBeam = new BeamEntry(ST.make(BlocksGT.Beam2, 1, 2), WoodDictionary.PLANKS.get(Blocks.planks, 3), 1, 300, 2, 4, 5, MT.WoodRubber, OP.stickLong.mat(MT.Wood, 1), 1, 2);
			new BeamEntry(ST.make(BlocksGT.Beam2FireProof, 1, 2), WoodDictionary.PLANKS.get(Blocks.planks, 3), 1, 300, 2, 4, 5);
			
			// IC2 Rubber Trees
			if (MD.IC2.mLoaded) {
				new SaplingEntry(IL.IC2_Sapling_Rubber.wild(1), new WoodEntry(IL.IC2_Log_Rubber.wild(1), tRubberBeam, 1, 350, 1, 3, 4, IL.IC2_Resin.get(1, IL.Resin.get(1)), MT.WoodRubber, MT.Bark, OP.stickLong.mat(MT.Wood, 1), 1, 2), IL.IC2_Leaves_Rubber.wild(1));
			}
			// MFR Rubber Trees
			if (MD.MFR.mLoaded) {
				new SaplingEntry(IL.MFR_Sapling_Rubber.wild(1), new WoodEntry(IL.MFR_Log_Rubber.wild(1), tRubberBeam, 1, 350, 1, 3, 4, IL.IC2_Resin.get(1, IL.Resin.get(1)), MT.WoodRubber, MT.Bark, OP.stickLong.mat(MT.Wood, 1), 1, 2), IL.MFR_Leaves_Rubber.wild(1));
			}
			// Atum Palm Trees
			if (MD.ATUM.mLoaded) {
				new SaplingEntry(ST.make(MD.ATUM, "tile.palmSapling", 1, W), new WoodEntry(ST.make(MD.ATUM, "tile.palmLog", 1, W), WoodDictionary.BEAMS.get(BlocksGT.BeamB, 2), new PlankEntry(ST.make(MD.ATUM, "tile.palmPlanks", 1, W), 123)), ST.make(MD.ATUM, "tile.palmLeaves", 1, W));
			}
			// Fossils and Archeology
			if (MD.Fossil.mLoaded) {
				new SaplingEntry(ST.make(MD.Fossil, "palaeorapheSapling", 1, W), new WoodEntry(ST.make(MD.Fossil, "palaeorapheLog", 1, W), WoodDictionary.BEAMS.get(BlocksGT.BeamB, 2), new PlankEntry(ST.make(MD.Fossil, "palaeoraphePlanks", 1, W), ST.make(MD.Fossil, "palaeorapheSlab", 1, 0), ST.make(MD.Fossil, "palaeorapheStairs", 1, 0), 177), 2, 500), ST.make(MD.Fossil, "palaeorapheLeaves", 1, W));
			}
		} else {
			WoodDictionary.DEFAULT_PLANK = WoodDictionary.PLANKS.get(Blocks.planks, 0);
			
			// IC2 Rubber Trees
			if (MD.IC2.mLoaded) {
				new SaplingEntry(IL.IC2_Sapling_Rubber.wild(1), new WoodEntry(IL.IC2_Log_Rubber.wild(1), 1, 350, 1, 3, 4, IL.IC2_Resin.get(1, IL.Resin.get(1)), MT.WoodRubber, MT.Bark, OP.stickLong.mat(MT.Wood, 1), 1, 2), IL.IC2_Leaves_Rubber.wild(1));
			}
			// MFR Rubber Trees
			if (MD.MFR.mLoaded) {
				new SaplingEntry(IL.MFR_Sapling_Rubber.wild(1), new WoodEntry(IL.MFR_Log_Rubber.wild(1), 1, 350, 1, 3, 4, IL.IC2_Resin.get(1, IL.Resin.get(1)), MT.WoodRubber, MT.Bark, OP.stickLong.mat(MT.Wood, 1), 1, 2), IL.MFR_Leaves_Rubber.wild(1));
			}
			// Atum Palm Trees
			if (MD.ATUM.mLoaded) {
				new SaplingEntry(ST.make(MD.ATUM, "tile.palmSapling", 1, W), new WoodEntry(ST.make(MD.ATUM, "tile.palmLog", 1, W), new PlankEntry(ST.make(MD.ATUM, "tile.palmPlanks", 1, W), 123)), ST.make(MD.ATUM, "tile.palmLeaves", 1, W));
			}
			// Fossils and Archeology
			if (MD.Fossil.mLoaded) {
				new SaplingEntry(ST.make(MD.Fossil, "palaeorapheSapling", 1, W), new WoodEntry(ST.make(MD.Fossil, "palaeorapheLog", 1, W), new PlankEntry(ST.make(MD.Fossil, "palaeoraphePlanks", 1, W), ST.make(MD.Fossil, "palaeorapheSlab", 1, 0), ST.make(MD.Fossil, "palaeorapheStairs", 1, 0), 177), 2, 500), ST.make(MD.Fossil, "palaeorapheLeaves", 1, W));
			}
		}
		// BambooModSakuraTrees
		if (MD.Bamboo.mLoaded) {
			new SaplingEntry(ST.make(MD.Bamboo, "sakuraSapling", 1, W), new WoodEntry(ST.make(MD.Bamboo, "sakuraLog", 1, W), new PlankEntry(ST.make(MD.Bamboo, "twoDirDeco", 1, 2), ST.make(MD.Bamboo, "halfTwoDirDeco", 1, 2), 161), 1, 350), ST.make(MD.Bamboo, "sakuraLeaves", 1, W));
		}
		// Thaumcraft Trees
		if (MD.TC.mLoaded) {
			new SaplingEntry(ST.make(MD.TC, "blockCustomPlant", 1, 0), new WoodEntry(IL.TC_Greatwood_Log .get(1), new BeamEntry(ST.make(BlocksGT.Beam3, 1, 0), new PlankEntry(IL.TC_Greatwood_Planks .get(1), ST.make(MD.TC, "blockCosmeticSlabWood", 1, 0), ST.make(MD.TC, "blockStairsGreatwood" , 1, 0), MT.Greatwood , 46), 2,  450), 2,  500), ST.make(MD.TC, "blockMagicalLeaves", 1, 0));
			new SaplingEntry(ST.make(MD.TC, "blockCustomPlant", 1, 1), new WoodEntry(IL.TC_Silverwood_Log.get(1), new BeamEntry(ST.make(BlocksGT.Beam3, 1, 1), new PlankEntry(IL.TC_Silverwood_Planks.get(1), ST.make(MD.TC, "blockCosmeticSlabWood", 1, 1), ST.make(MD.TC, "blockStairsSilverwood", 1, 0), MT.Silverwood, 47), 2, 1800), 2, 2000), ST.make(MD.TC, "blockMagicalLeaves", 1, 1));
			CR.shaped(IL.TC_Greatwood_Planks .get(1), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.TC, "blockCosmeticSlabWood", 1, 0));
			CR.shaped(IL.TC_Silverwood_Planks.get(1), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.TC, "blockCosmeticSlabWood", 1, 1));
		}
		// Twilight Forest Trees
		if (MD.TF.mLoaded) {
			new SaplingEntry(ST.make(MD.TF, "tile.TFSapling", 1, 0), new WoodEntry(IL.TF_Log_Oak                   .get(1), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 0)), ST.make(MD.TF, "tile.TFLeaves", 1, 0));
			new SaplingEntry(ST.make(MD.TF, "tile.TFSapling", 1, 1), new WoodEntry(IL.TF_Log_Canopy                .get(1), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 1)), ST.make(MD.TF, "tile.TFLeaves", 1, 1));
			new SaplingEntry(ST.make(MD.TF, "tile.TFSapling", 1, 2), new WoodEntry(IL.TF_Log_Mangrove              .get(1), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 2)), ST.make(MD.TF, "tile.TFLeaves", 1, 2));
			new SaplingEntry(ST.make(MD.TF, "tile.TFSapling", 1, 3), new WoodEntry(IL.TF_Log_Darkwood              .get(1), new BeamEntry(ST.make(BlocksGT.Beam3, 1, 3), new PlankEntry(ST.make(MD.TF, "tile.TFTowerStone", 1, 0), ST.make(Blocks.wooden_slab, 1, 5), ST.make(Blocks.dark_oak_stairs, 1, 0), 68))), ST.make(MD.TF, "tile.DarkLeaves", 1, 0));
			new SaplingEntry(ST.make(MD.TF, "tile.TFSapling", 1, 4), WoodDictionary.WOODS.get(new ItemStackContainer(IL.TF_Log_Oak.get(1))), WoodDictionary.LEAVES.get(MD.TF, "tile.TFLeaves", 0));
			new SaplingEntry(ST.make(MD.TF, "tile.TFSapling", 1, 5), new WoodEntry(IL.TF_Log_Time                  .get(1), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 1)), ST.make(MD.TF, "tile.TFMagicLeaves", 1, 0));
			new SaplingEntry(ST.make(MD.TF, "tile.TFSapling", 1, 6), new WoodEntry(IL.TF_Log_Trans                 .get(1), WoodDictionary.BEAMS.get(BlocksGT.Beam2, 0)), ST.make(MD.TF, "tile.TFMagicLeaves", 1, 1));
			new SaplingEntry(ST.make(MD.TF, "tile.TFSapling", 1, 7), new WoodEntry(IL.TF_Log_Mine                  .get(1), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 2)), ST.make(MD.TF, "tile.TFMagicLeaves", 1, 2));
			new SaplingEntry(ST.make(MD.TF, "tile.TFSapling", 1, 8), new WoodEntry(IL.TF_Log_Sorting               .get(1), WoodDictionary.BEAMS.get(BlocksGT.Beam2, 1)), ST.make(MD.TF, "tile.TFMagicLeaves", 1, 3));
			new SaplingEntry(ST.make(MD.TF, "tile.TFSapling", 1, 9), WoodDictionary.WOODS.get(Blocks.log, 0), ST.make(MD.TF, "tile.TFLeaves", 1, 3));
			new PlankEntry(ST.make(MD.TF, "tile.TFTowerStone", 1, 1), ST.make(Blocks.wooden_slab, 1, 5), ST.make(Blocks.dark_oak_stairs, 1, 0), 69);
			new PlankEntry(ST.make(MD.TF, "tile.TFTowerStone", 1, 2), ST.make(Blocks.wooden_slab, 1, 5), ST.make(Blocks.dark_oak_stairs, 1, 0), 70);
			new PlankEntry(ST.make(MD.TF, "tile.TFTowerStone", 1, 3), ST.make(Blocks.wooden_slab, 1, 5), ST.make(Blocks.dark_oak_stairs, 1, 0), 71);
		}
		// Betweenlands Trees
		if (MD.BTL.mLoaded) {
			new SaplingEntry(IL.BTL_Weedwood_Sapling.wild(1), new WoodEntry(IL.BTL_Weedwood_Log.wild(1), new BeamEntry(IL.BTL_Weedwood_Beam.wild(1), new PlankEntry(IL.BTL_Weedwood_Planks.get(1), ST.make(MD.BTL, "Weedwood Planks Slab", 1, 0), ST.make(MD.BTL, "weedwoodPlankStairs", 1, 0), MT.Weedwood, 105), 2, 400), 2, 500, IL.BTL_Bark.get(1), MT.Bark), IL.BTL_Weedwood_Leaves.get(1));
			CR.shaped(IL.BTL_Weedwood_Planks.get(1), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BTL, "Weedwood Planks Slab", 1, 0));
		}
		// Aether Trees
		if (MD.AETHER.mLoaded) {
			BeamEntry tSkyrootBeam = new BeamEntry(ST.make(BlocksGT.Beam3, 1, 2), new PlankEntry(IL.AETHER_Skyroot_Planks.get(1), ST.make(MD.AETHER, "tile.skyrootSingleSlab", 1, 0), ST.make(MD.AETHER, "skyrootStairs", 1, 0), MT.Skyroot, 124), 1, 200);
			CR.shaped(IL.AETHER_Skyroot_Planks.get(1), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.AETHER, "tile.skyrootSingleSlab", 1, 0));
			
			new WoodEntry(IL.AETHER_Skyroot_Log_Small.wild(1), tSkyrootBeam, 1, 200);
			
			WoodEntry tSkyrootWood = new WoodEntry(IL.AETHER_Skyroot_Log.wild(1), tSkyrootBeam, 1, 300);
			new SaplingEntry(IL.AETHER_Skyroot_Sapling_Blue     .wild(1), tSkyrootWood, IL.AETHER_Skyroot_Leaves_Blue   .wild(1));
			new SaplingEntry(IL.AETHER_Skyroot_Sapling_Dark     .wild(1), tSkyrootWood, IL.AETHER_Skyroot_Leaves_Dark   .wild(1));
			new SaplingEntry(IL.AETHER_Skyroot_Sapling_Green    .wild(1), tSkyrootWood, IL.AETHER_Skyroot_Leaves_Green  .wild(1));
			new SaplingEntry(IL.AETHER_Skyroot_Sapling_Gold     .wild(1), tSkyrootWood, IL.AETHER_Skyroot_Leaves_Gold   .wild(1));
			new SaplingEntry(IL.AETHER_Skyroot_Sapling_Purple   .wild(1), tSkyrootWood, IL.AETHER_Skyroot_Leaves_Purple .wild(1));
		}
		// Botania Planks
		if (MD.BOTA.mLoaded) {
			new PlankEntry(ST.make(MD.BOTA, "shimmerwoodPlanks", 1, 0), ST.make(MD.BOTA, "shimmerwoodPlanks0Slab", 1, 0), ST.make(MD.BOTA, "shimmerwoodPlanks0Stairs", 1, 0), MT.Shimmerwood, 64);
			for (int i = 1; i < 6; i++)
			new PlankEntry(ST.make(MD.BOTA, "livingwood", 1, i), ST.make(MD.BOTA, "livingwood1Slab", 1, 0), ST.make(MD.BOTA, "livingwood1Stairs", 1, 0), MT.Livingwood, 48+i);
			new PlankEntry(ST.make(MD.BOTA, "livingwood", 1, 0), ST.make(MD.BOTA, "livingwood0Slab", 1, 0), ST.make(MD.BOTA, "livingwood0Stairs", 1, 0), MT.Livingwood, 48);
			for (int i = 1; i < 6; i++)
			new PlankEntry(ST.make(MD.BOTA, "dreamwood", 1, i), ST.make(MD.BOTA, "dreamwood1Slab", 1, 0), ST.make(MD.BOTA, "dreamwood1Stairs", 1, 0), MT.Dreamwood, 56+i);
			new PlankEntry(ST.make(MD.BOTA, "dreamwood", 1, 0), ST.make(MD.BOTA, "dreamwood0Slab", 1, 0), ST.make(MD.BOTA, "dreamwood0Stairs", 1, 0), MT.Dreamwood, 56);
			
			CR.shaped(ST.make(MD.BOTA, "shimmerwoodPlanks"  , 1, 0), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BOTA, "shimmerwoodPlanks0Slab", 1, 0));
			CR.shaped(ST.make(MD.BOTA, "livingwood"         , 1, 0), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BOTA, "livingwood0Slab", 1, 0));
			CR.shaped(ST.make(MD.BOTA, "livingwood"         , 1, 1), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BOTA, "livingwood1Slab", 1, 0));
			CR.shaped(ST.make(MD.BOTA, "dreamwood"          , 1, 0), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BOTA, "dreamwood0Slab", 1, 0));
			CR.shaped(ST.make(MD.BOTA, "dreamwood"          , 1, 1), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BOTA, "dreamwood1Slab", 1, 0));
		}
		// Alfheim Dreamwood Trees
		if (MD.ALF.mLoaded) {
			new SaplingEntry(IL.ALF_DreamSapling.wild(1), new WoodEntry(IL.ALF_DreamWood.wild(1), WoodDictionary.PLANKS.get(ST.block(MD.BOTA, "dreamwood"), 1), 2, 500), IL.ALF_DreamLeaves.wild(1));
		}
		// Witchery Trees
		if (MD.WTCH.mLoaded) {
			Block tPlank = ST.block(MD.WTCH, "witchwood"), tSlab = ST.block(MD.WTCH, "witchwoodslab");
			
			new SaplingEntry(ST.make(MD.WTCH, "witchsapling", 1, 0), new WoodEntry(ST.make(MD.WTCH, "witchlog", 1, 0), new PlankEntry(ST.make(tPlank, 1, 0), ST.make(tSlab, 1, 0), ST.make(MD.WTCH, "stairswoodrowan"   , 1, W), 65)), ST.make(MD.WTCH, "witchleaves", 1, 0));
			new SaplingEntry(ST.make(MD.WTCH, "witchsapling", 1, 1), new WoodEntry(ST.make(MD.WTCH, "witchlog", 1, 1), new PlankEntry(ST.make(tPlank, 1, 1), ST.make(tSlab, 1, 1), ST.make(MD.WTCH, "stairswoodalder"   , 1, W), 66)), ST.make(MD.WTCH, "witchleaves", 1, 1));
			new SaplingEntry(ST.make(MD.WTCH, "witchsapling", 1, 2), new WoodEntry(ST.make(MD.WTCH, "witchlog", 1, 2), new PlankEntry(ST.make(tPlank, 1, 2), ST.make(tSlab, 1, 2), ST.make(MD.WTCH, "stairswoodhawthorn", 1, W), 67)), ST.make(MD.WTCH, "witchleaves", 1, 2));
			
			for (int i = 0; i < 3; i++) CR.shaped(ST.make(tPlank, 1, i), CR.DEF_NCC, "S", "S", 'S', ST.make(tSlab, 1, i));
		}
		// Abyssalcraft Trees
		if (MD.ABYSSAL.mLoaded) {
			new SaplingEntry(ST.make(MD.ABYSSAL, "dltsapling", 1, 0), new WoodEntry(ST.make(MD.ABYSSAL, "dltlog", 1, 0), new PlankEntry(ST.make(MD.ABYSSAL, "dltplank", 1, 0), ST.make(MD.ABYSSAL, "dltslab1", 1, 0), ST.make(MD.ABYSSAL, "dltstairs", 1, 0), 185)), ST.make(MD.ABYSSAL, "dltleaves", 1, 0));
			new SaplingEntry(ST.make(MD.ABYSSAL, "dreadsapling", 1, 0), new WoodEntry(ST.make(MD.ABYSSAL, "dreadlog", 1, 0), new PlankEntry(ST.make(MD.ABYSSAL, "dreadplanks", 1, 0), 186)), ST.make(MD.ABYSSAL, "dreadleaves", 1, 0));
			
			CR.shaped(ST.make(MD.ABYSSAL, "dltplank", 1, 0), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.ABYSSAL, "dltslab1", 1, 0));
		}
		// Extra Biomes Trees
		if (MD.EBXL.mLoaded) {
			Block tPlank = ST.block(MD.EBXL, "planks"), tSlab = ST.block(MD.EBXL, "woodslab");
			new PlankEntry(ST.make(tPlank, 1, 0), ST.make(tSlab                 , 1, 0), ST.make(MD.EBXL, "stairs.redwood"              , 1, 0), 87);
			new PlankEntry(ST.make(tPlank, 1, 1), ST.make(tSlab                 , 1, 1), ST.make(MD.EBXL, "stairs.fir"                  , 1, 0), 88);
			new PlankEntry(ST.make(tPlank, 1, 2), ST.make(tSlab                 , 1, 2), ST.make(MD.EBXL, "stairs.acacia"               , 1, 0), 89);
			new PlankEntry(ST.make(tPlank, 1, 3), ST.make(tSlab                 , 1, 3), ST.make(MD.EBXL, "stairs.cypress"              , 1, 0), 90);
			new PlankEntry(ST.make(tPlank, 1, 4), ST.make(tSlab                 , 1, 4), ST.make(MD.EBXL, "stairs.japanesemaple"        , 1, 0), 91);
			new PlankEntry(ST.make(tPlank, 1, 5), ST.make(tSlab                 , 1, 5), ST.make(MD.EBXL, "stairs.rainboweucalyptus"    , 1, 0), 92);
			new PlankEntry(ST.make(tPlank, 1, 6), ST.make(tSlab                 , 1, 6), ST.make(MD.EBXL, "stairs.autumn"               , 1, 0), 93);
			new PlankEntry(ST.make(tPlank, 1, 7), ST.make(tSlab                 , 1, 7), ST.make(MD.EBXL, "stairs.baldcypress"          , 1, 0), 94);
			new PlankEntry(ST.make(tPlank, 1, 8), ST.make(MD.EBXL, "woodslab2"  , 1, 0), ST.make(MD.EBXL, "stairs.sakurablossom"        , 1, 0), 95);
			
			for (int i = 0; i < 8; i++)
			CR.shaped(ST.make(tPlank, 1, i), CR.DEF_NCC, "S", "S", 'S', ST.make(tSlab, 1, i));
			CR.shaped(ST.make(tPlank, 1, 8), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EBXL, "woodslab2", 1, 0));
			
			new SaplingEntry(ST.make(MD.EBXL, "saplings_2", 1, 4), new WoodEntry(ST.make(MD.EBXL, "mini_log_1", 1, W), null, WoodDictionary.PLANKS.get(tPlank, 8), 1, 100, 1, 2, 3, OP.dustSmall.mat(MT.Bark, 1), MT.Wood, MT.Bark, OP.stickLong.mat(MT.Wood, 1), 1, 2), ST.make(MD.EBXL, "leaves_3", 1, 0));
			
			new WoodEntry(ST.make(MD.EBXL, "cornerlog_oak"                  , 1, W), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 0));
			new WoodEntry(ST.make(MD.EBXL, "cornerlog_redwood"              , 1, W), WoodDictionary.PLANKS.get(tPlank, 0));
			new WoodEntry(ST.make(MD.EBXL, "log2"                           , 1, 3), WoodDictionary.PLANKS.get(tPlank, 0));
			new WoodEntry(ST.make(MD.EBXL, "cornerlog_fir"                  , 1, W), WoodDictionary.PLANKS.get(tPlank, 1));
			new WoodEntry(ST.make(MD.EBXL, "log1"                           , 1, 0), WoodDictionary.PLANKS.get(tPlank, 1));
			new WoodEntry(ST.make(MD.EBXL, "log1"                           , 1, 1), WoodDictionary.PLANKS.get(tPlank, 2));
			new WoodEntry(ST.make(MD.EBXL, "log2"                           , 1, 2), WoodDictionary.PLANKS.get(tPlank, 3));
			new WoodEntry(ST.make(MD.EBXL, "log1"                           , 1, 3), WoodDictionary.PLANKS.get(tPlank, 4));
			new WoodEntry(ST.make(MD.EBXL, "log2"                           , 1, 0), WoodDictionary.PLANKS.get(tPlank, 5));
			new WoodEntry(ST.make(MD.EBXL, "cornerlog_rainboweucalyptus"    , 1, W), WoodDictionary.PLANKS.get(tPlank, 5));
			new WoodEntry(ST.make(MD.EBXL, "log_elbow_rainbow_eucalyptus"   , 1, W), WoodDictionary.PLANKS.get(tPlank, 5));
			new WoodEntry(ST.make(MD.EBXL, "log2"                           , 1, 1), WoodDictionary.PLANKS.get(tPlank, 6));
			new WoodEntry(ST.make(MD.EBXL, "log1"                           , 1, 2), WoodDictionary.PLANKS.get(tPlank, 7));
			new WoodEntry(ST.make(MD.EBXL, "cornerlog_baldcypress"          , 1, W), WoodDictionary.PLANKS.get(tPlank, 7));
			new WoodEntry(ST.make(MD.EBXL, "log_elbow_baldcypress"          , 1, W), WoodDictionary.PLANKS.get(tPlank, 7));
			
			new SaplingEntry(ST.make(MD.EBXL, "saplings_1", 1, 0), WoodDictionary.WOODS.get(MD.EBXL, "log2", 1), ST.make(MD.EBXL, "leaves_1", 1, 0));
			new SaplingEntry(ST.make(MD.EBXL, "saplings_1", 1, 1), WoodDictionary.WOODS.get(MD.EBXL, "log2", 1), ST.make(MD.EBXL, "leaves_1", 1, 1));
			new SaplingEntry(ST.make(MD.EBXL, "saplings_1", 1, 2), WoodDictionary.WOODS.get(MD.EBXL, "log2", 1), ST.make(MD.EBXL, "leaves_1", 1, 2));
			new SaplingEntry(ST.make(MD.EBXL, "saplings_1", 1, 3), WoodDictionary.WOODS.get(MD.EBXL, "log2", 1), ST.make(MD.EBXL, "leaves_1", 1, 3));
			new SaplingEntry(ST.make(MD.EBXL, "saplings_1", 1, 4), WoodDictionary.WOODS.get(MD.EBXL, "log1", 0), ST.make(MD.EBXL, "leaves_4", 1, 0));
			new SaplingEntry(ST.make(MD.EBXL, "saplings_1", 1, 5), WoodDictionary.WOODS.get(MD.EBXL, "log2", 3), ST.make(MD.EBXL, "leaves_4", 1, 1));
			new SaplingEntry(ST.make(MD.EBXL, "saplings_1", 1, 6), WoodDictionary.WOODS.get(MD.EBXL, "log1", 1), ST.make(MD.EBXL, "leaves_4", 1, 2));
			new SaplingEntry(ST.make(MD.EBXL, "saplings_1", 1, 7), WoodDictionary.WOODS.get(MD.EBXL, "log1", 2), ST.make(MD.EBXL, "leaves_4", 1, 3));
			new SaplingEntry(ST.make(MD.EBXL, "saplings_2", 1, 0), WoodDictionary.WOODS.get(MD.EBXL, "log2", 2), ST.make(MD.EBXL, "leaves_2", 1, 0));
			new SaplingEntry(ST.make(MD.EBXL, "saplings_2", 1, 1), WoodDictionary.WOODS.get(MD.EBXL, "log1", 3), ST.make(MD.EBXL, "leaves_2", 1, 1));
			new SaplingEntry(ST.make(MD.EBXL, "saplings_2", 1, 2), WoodDictionary.WOODS.get(MD.EBXL, "log1", 3), ST.make(MD.EBXL, "leaves_2", 1, 2));
			new SaplingEntry(ST.make(MD.EBXL, "saplings_2", 1, 3), WoodDictionary.WOODS.get(MD.EBXL, "log2", 0), ST.make(MD.EBXL, "leaves_2", 1, 3));
		}
		// Biomes o' Plenty Trees
		if (MD.BoP.mLoaded) {
			Block tPlank = ST.block(MD.BoP, "planks");
			new SaplingEntry(ST.make(MD.BoP, "colorizedSaplings", 1, 0), new WoodEntry(ST.make(MD.BoP, "logs1", 1, 0), new PlankEntry(ST.make(tPlank, 1, 0), ST.make(MD.BoP, "woodenSingleSlab1"            , 1, 0) , ST.make(MD.BoP, "sacredoakStairs"   , 1, W), 72), 1, 250), ST.make(MD.BoP, "colorizedLeaves1", 1, 0));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1,10), new WoodEntry(ST.make(MD.BoP, "logs1", 1, 1), new PlankEntry(ST.make(tPlank, 1, 1), ST.make(MD.BoP, "woodenSingleSlab1"            , 1, 1) , ST.make(MD.BoP, "cherryStairs"      , 1, W), 73), 1, 250), ST.make(MD.BoP, "leaves3", 1, 1));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1, 4), new WoodEntry(ST.make(MD.BoP, "logs1", 1, 2), new PlankEntry(ST.make(tPlank, 1, 2), ST.make(MD.BoP, "woodenSingleSlab1"            , 1, 2) , ST.make(MD.BoP, "darkStairs"        , 1, W), 74), 1, 250), ST.make(MD.BoP, "leaves1", 1, 3));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1, 6), new WoodEntry(ST.make(MD.BoP, "logs1", 1, 3), new PlankEntry(ST.make(tPlank, 1, 3), ST.make(MD.BoP, "woodenSingleSlab1"            , 1, 3) , ST.make(MD.BoP, "firStairs"         , 1, W), 75), 1, 250), ST.make(MD.BoP, "leaves2", 1, 1));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1, 7), new WoodEntry(ST.make(MD.BoP, "logs2", 1, 0), new PlankEntry(ST.make(tPlank, 1, 4), ST.make(MD.BoP, "woodenSingleSlab1"            , 1, 4) , ST.make(MD.BoP, "etherealStairs"    , 1, W), 76), 1, 250), ST.make(MD.BoP, "leaves2", 1, 2));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1, 3), new WoodEntry(ST.make(MD.BoP, "logs2", 1, 1), new PlankEntry(ST.make(tPlank, 1, 5), ST.make(MD.BoP, "woodenSingleSlab1"            , 1, 5) , ST.make(MD.BoP, "magicStairs"       , 1, W), 77), 1, 250), ST.make(MD.BoP, "leaves1", 1, 2));
			new SaplingEntry(ST.make(MD.BoP, "colorizedSaplings", 1, 1), new WoodEntry(ST.make(MD.BoP, "logs2", 1, 2), new PlankEntry(ST.make(tPlank, 1, 6), ST.make(MD.BoP, "woodenSingleSlab1"            , 1, 6) , ST.make(MD.BoP, "mangroveStairs"    , 1, W), 78), 1, 250), ST.make(MD.BoP, "colorizedLeaves1", 1, 1));
			new SaplingEntry(ST.make(MD.BoP, "colorizedSaplings", 1, 2), new WoodEntry(ST.make(MD.BoP, "logs2", 1, 3), new PlankEntry(ST.make(tPlank, 1, 7), ST.make(MD.BoP, "woodenSingleSlab1"            , 1, 7) , ST.make(MD.BoP, "palmStairs"        , 1, W), 79), 1, 250), ST.make(MD.BoP, "colorizedLeaves1", 1, 2));
			new SaplingEntry(ST.make(MD.BoP, "colorizedSaplings", 1, 3), new WoodEntry(ST.make(MD.BoP, "logs3", 1, 0), new PlankEntry(ST.make(tPlank, 1, 8), ST.make(MD.BoP, "woodenSingleSlab2"            , 1, 0) , ST.make(MD.BoP, "redwoodStairs"     , 1, W), 80), 1, 250), ST.make(MD.BoP, "colorizedLeaves1", 1, 3));
			new SaplingEntry(ST.make(MD.BoP, "colorizedSaplings", 1, 4), new WoodEntry(ST.make(MD.BoP, "logs3", 1, 1), new PlankEntry(ST.make(tPlank, 1, 9), ST.make(MD.BoP, "woodenSingleSlab2"            , 1, 1) , ST.make(MD.BoP, "willowStairs"      , 1, W), 81), 2, 500), ST.make(MD.BoP, "colorizedLeaves2", 1, 0));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1, 5), new WoodEntry(ST.make(MD.BoP, "logs3", 1, 2), WoodDictionary.PLANKS.get(BlocksGT.Planks, 12), 1, 50, 1, 2, 3, IL.Bark_Dry.get(1), MT.Wood, MT.Bark, null, 0, 0), ST.make(MD.BoP, "leaves2", 1, 0));
			new SaplingEntry(ST.make(MD.BoP, "colorizedSaplings", 1, 5), new WoodEntry(ST.make(MD.BoP, "logs4", 1, 0), new PlankEntry(ST.make(tPlank, 1,11), ST.make(MD.BoP, "woodenSingleSlab2"            , 1, 2) , ST.make(MD.BoP, "pineStairs"        , 1, W), 83), 1, 250), ST.make(MD.BoP, "colorizedLeaves2", 1, 1));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1,13), new WoodEntry(ST.make(MD.BoP, "logs4", 1, 1), new PlankEntry(ST.make(tPlank, 1,12), ST.make(MD.BoP, "woodenSingleSlab2"            , 1, 3) , ST.make(MD.BoP, "hellBarkStairs"    , 1, W), 84), 1, 250), ST.make(MD.BoP, "leaves4", 1, 0));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1,14), new WoodEntry(ST.make(MD.BoP, "logs4", 1, 2), new PlankEntry(ST.make(tPlank, 1,13), ST.make(MD.BoP, "woodenSingleSlab2"            , 1, 4) , ST.make(MD.BoP, "jacarandaStairs"   , 1, W), 85), 1, 250), ST.make(MD.BoP, "leaves4", 1, 1));
			new SaplingEntry(ST.make(MD.BoP, "colorizedSaplings", 1, 6), new WoodEntry(ST.make(MD.BoP, "logs4", 1, 3), new PlankEntry(ST.make(tPlank, 1,14), IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), ST.make(MD.BoP, "mahoganyStairs"    , 1, W), 86), 1, 250), ST.make(MD.BoP, "colorizedLeaves2", 1, 2));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1,12), WoodDictionary.WOODS.get(MD.BoP, "logs1", 1), ST.make(MD.BoP, "leaves3", 1, 3));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1, 1), WoodDictionary.WOODS.get(Blocks.log , 2), ST.make(MD.BoP, "leaves1", 1, 0));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1, 8), WoodDictionary.WOODS.get(Blocks.log2, 1), ST.make(MD.BoP, "leaves2", 1, 3));
			new SaplingEntry(ST.make(MD.BoP, "colorizedSaplings", 1, 7), WoodDictionary.WOODS.get(Blocks.log , 0), ST.make(MD.BoP, "colorizedLeaves2", 1, 3));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1, 0), WoodDictionary.WOODS.get(Blocks.log , 0), ST.make(MD.BoP, "appleLeaves", 1, W));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1,15), WoodDictionary.WOODS.get(Blocks.log , 0), ST.make(MD.BoP, "persimmonLeaves", 1, W));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1,11), WoodDictionary.WOODS.get(Blocks.log , 0), ST.make(MD.BoP, "leaves3", 1, 2));
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1, 9), WoodDictionary.WOODS.get(Blocks.log , 0), ST.make(MD.BoP, "leaves3", 1, 0));
			// This one is Bamboo, so no actual Logs.
			new SaplingEntry(ST.make(MD.BoP, "saplings"         , 1, 2), null, ST.make(MD.BoP, "leaves1", 1, 1));
			
			new PlankEntry(ST.make(tPlank, 1,10), NI, NI, MT.Bamboo, 82, NI, 0, 0, 0);
			
			CR.shaped(ST.make(tPlank, 1, 0), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BoP, "woodenSingleSlab1", 1, 0));
			CR.shaped(ST.make(tPlank, 1, 1), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BoP, "woodenSingleSlab1", 1, 1));
			CR.shaped(ST.make(tPlank, 1, 2), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BoP, "woodenSingleSlab1", 1, 2));
			CR.shaped(ST.make(tPlank, 1, 3), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BoP, "woodenSingleSlab1", 1, 3));
			CR.shaped(ST.make(tPlank, 1, 4), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BoP, "woodenSingleSlab1", 1, 4));
			CR.shaped(ST.make(tPlank, 1, 5), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BoP, "woodenSingleSlab1", 1, 5));
			CR.shaped(ST.make(tPlank, 1, 6), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BoP, "woodenSingleSlab1", 1, 6));
			CR.shaped(ST.make(tPlank, 1, 7), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BoP, "woodenSingleSlab1", 1, 7));
			CR.shaped(ST.make(tPlank, 1, 8), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BoP, "woodenSingleSlab2", 1, 0));
			CR.shaped(ST.make(tPlank, 1, 9), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BoP, "woodenSingleSlab2", 1, 1));
			CR.shaped(ST.make(tPlank, 1,11), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BoP, "woodenSingleSlab2", 1, 2));
			CR.shaped(ST.make(tPlank, 1,12), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BoP, "woodenSingleSlab2", 1, 3));
			CR.shaped(ST.make(tPlank, 1,13), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.BoP, "woodenSingleSlab2", 1, 4));
		}
		// Enhanced Biomes Trees
		if (MD.EB.mLoaded) {
			Block tPlank = ST.block(MD.EB, "enhancedbiomes.tile.planksEB");
			
			new SaplingEntry(ST.make(MD.EB, "enhancedbiomes.tile.saplingEB", 1, 0), new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logOakEB"   , 1, 0), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 0), new PlankEntry(ST.make(tPlank, 1, 0), ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 0), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB0" , 1, W), 162)), ST.make(MD.EB, "enhancedbiomes.tile.leavesOakEB"   , 1, 0));
			new SaplingEntry(ST.make(MD.EB, "enhancedbiomes.tile.saplingEB", 1, 1), new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logOakEB"   , 1, 1), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 0), new PlankEntry(ST.make(tPlank, 1, 1), ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 1), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB1" , 1, W), 163)), ST.make(MD.EB, "enhancedbiomes.tile.leavesOakEB"   , 1, 1));
			new SaplingEntry(ST.make(MD.EB, "enhancedbiomes.tile.saplingEB", 1, 2), new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logOakEB"   , 1, 2), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 0), new PlankEntry(ST.make(tPlank, 1, 2), ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 2), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB2" , 1, W), 164)), ST.make(MD.EB, "enhancedbiomes.tile.leavesOakEB"   , 1, 2));
			new SaplingEntry(ST.make(MD.EB, "enhancedbiomes.tile.saplingEB", 1, 3), new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logOakEB"   , 1, 3), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 2), new PlankEntry(ST.make(tPlank, 1, 3), ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 3), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB3" , 1, W), 165)), ST.make(MD.EB, "enhancedbiomes.tile.leavesOakEB"   , 1, 3));
			new SaplingEntry(ST.make(MD.EB, "enhancedbiomes.tile.saplingEB", 1, 4), new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logSpruceEB", 1, 0), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 1), new PlankEntry(ST.make(tPlank, 1, 4), ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 4), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB4" , 1, W), 166)), ST.make(MD.EB, "enhancedbiomes.tile.leavesSpruceEB", 1, 0));
			new SaplingEntry(ST.make(MD.EB, "enhancedbiomes.tile.saplingEB", 1, 5), new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logSpruceEB", 1, 1), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 1), new PlankEntry(ST.make(tPlank, 1, 5), ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 5), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB5" , 1, W), 167)), ST.make(MD.EB, "enhancedbiomes.tile.leavesSpruceEB", 1, 1));
			new SaplingEntry(ST.make(MD.EB, "enhancedbiomes.tile.saplingEB", 1, 6), new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logSpruceEB", 1, 2), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 1), new PlankEntry(ST.make(tPlank, 1, 6), ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 6), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB6" , 1, W), 168)), ST.make(MD.EB, "enhancedbiomes.tile.leavesSpruceEB", 1, 2));
			new SaplingEntry(ST.make(MD.EB, "enhancedbiomes.tile.saplingEB", 1, 7), new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logSpruceEB", 1, 3), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 2), new PlankEntry(ST.make(tPlank, 1, 7), ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 7), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB7" , 1, W), 169)), ST.make(MD.EB, "enhancedbiomes.tile.leavesSpruceEB", 1, 3));
			new SaplingEntry(ST.make(MD.EB, "enhancedbiomes.tile.saplingEB", 1, 8), new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logBirchEB" , 1, 0), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 2), new PlankEntry(ST.make(tPlank, 1, 8), ST.make(MD.EB, "enhancedbiomes.tile.slab2EB", 1, 0), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB8" , 1, W), 170)), ST.make(MD.EB, "enhancedbiomes.tile.leavesBirchEB" , 1, 0));
			new SaplingEntry(ST.make(MD.EB, "enhancedbiomes.tile.saplingEB", 1, 9), new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logBirchEB" , 1, 1), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 2), new PlankEntry(ST.make(tPlank, 1, 9), ST.make(MD.EB, "enhancedbiomes.tile.slab2EB", 1, 1), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB9" , 1, W), 171)), ST.make(MD.EB, "enhancedbiomes.tile.leavesBirchEB" , 1, 1));
			new SaplingEntry(ST.make(MD.EB, "enhancedbiomes.tile.saplingEB", 1,10), new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logBirchEB" , 1, 2), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 2), new PlankEntry(ST.make(tPlank, 1,10), ST.make(MD.EB, "enhancedbiomes.tile.slab2EB", 1, 2), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB10", 1, W), 172)), ST.make(MD.EB, "enhancedbiomes.tile.leavesBirchEB" , 1, 2));
			new SaplingEntry(ST.make(MD.EB, "enhancedbiomes.tile.saplingEB", 1,12), new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logJungleEB", 1, 0), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 3), new PlankEntry(ST.make(tPlank, 1,12), ST.make(MD.EB, "enhancedbiomes.tile.slab2EB", 1, 4), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB12", 1, W), 173)), ST.make(MD.EB, "enhancedbiomes.tile.leavesJungleEB", 1, 0));
			new SaplingEntry(ST.make(MD.EB, "enhancedbiomes.tile.saplingEB", 1,14), new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logJungleEB", 1, 2), WoodDictionary.BEAMS.get(BlocksGT.Beam2, 0), new PlankEntry(ST.make(tPlank, 1,14), ST.make(MD.EB, "enhancedbiomes.tile.slab2EB", 1, 6), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB14", 1, W), 175)), ST.make(MD.EB, "enhancedbiomes.tile.leavesJungleEB", 1, 2));
			
			new WoodEntry(ST.make(MD.EB, "enhancedbiomes.tile.logJungleEB", 1, 1), new PlankEntry(ST.make(tPlank, 1,13), ST.make(MD.EB, "enhancedbiomes.tile.slab2EB", 1, 5), ST.make(MD.EB, "enhancedbiomes.tile.stairsWoodEB13", 1, W), 174), 1, 50, 1, 2, 3, IL.Bark_Dry.get(1), MT.Wood, MT.Bark, null, 0, 0);
			
			CR.shaped(ST.make(tPlank, 1, 0), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 0));
			CR.shaped(ST.make(tPlank, 1, 1), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 1));
			CR.shaped(ST.make(tPlank, 1, 2), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 2));
			CR.shaped(ST.make(tPlank, 1, 3), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 3));
			CR.shaped(ST.make(tPlank, 1, 4), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 4));
			CR.shaped(ST.make(tPlank, 1, 5), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 5));
			CR.shaped(ST.make(tPlank, 1, 6), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 6));
			CR.shaped(ST.make(tPlank, 1, 7), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab1EB", 1, 7));
			CR.shaped(ST.make(tPlank, 1, 8), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab2EB", 1, 0));
			CR.shaped(ST.make(tPlank, 1, 9), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab2EB", 1, 1));
			CR.shaped(ST.make(tPlank, 1,10), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab2EB", 1, 2));
			CR.shaped(ST.make(tPlank, 1,12), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab2EB", 1, 4));
			CR.shaped(ST.make(tPlank, 1,13), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab2EB", 1, 5));
			CR.shaped(ST.make(tPlank, 1,14), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.EB, "enhancedbiomes.tile.slab2EB", 1, 6));
		}
		// Highlands Trees
		if (MD.HiL.mLoaded) {
			Block tPlank = ST.block(MD.HiL, "hl_woodPlanks"), tSlab = ST.block(MD.HiL, "hl_woodSlab");
			
			new PlankEntry(ST.make(tPlank, 1, 0), ST.make(tSlab, 1, 0), ST.make(MD.HiL, "tile.hl_woodStairs0", 1, W), 178);
			new PlankEntry(ST.make(tPlank, 1, 1), ST.make(tSlab, 1, 1), ST.make(MD.HiL, "tile.hl_woodStairs1", 1, W), 179);
			new PlankEntry(ST.make(tPlank, 1, 2), ST.make(tSlab, 1, 2), ST.make(MD.HiL, "tile.hl_woodStairs2", 1, W), 180);
			new PlankEntry(ST.make(tPlank, 1, 3), ST.make(tSlab, 1, 3), ST.make(MD.HiL, "tile.hl_woodStairs3", 1, W), 181);
			
			new SaplingEntry(ST.make(MD.HiL, "Fir Sapling"                 , 1, W), new WoodEntry(ST.make(MD.HiL, "tile.hl_firWood"          , 1, W), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 1)                                      ), ST.make(MD.HiL, "tile.hl_firLeaves", 1, W));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_acaciaSapling"       , 1, W), new WoodEntry(ST.make(MD.HiL, "tile.hl_acaciaWood"       , 1, W), WoodDictionary.BEAMS.get(BlocksGT.Beam2, 0), WoodDictionary.PLANKS.get(tPlank, 0)), ST.make(MD.HiL, "tile.hl_acaciaLeaves", 1, W));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_ashSapling"          , 1, W), new WoodEntry(ST.make(MD.HiL, "tile.hl_ashWood"          , 1, W), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 0)                                      ), ST.make(MD.HiL, "tile.hl_ashLeaves", 1, W));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_canopySapling"       , 1, W), new WoodEntry(ST.make(MD.HiL, "tile.hl_canopyWood"       , 1, W), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 2)                                      ), ST.make(MD.HiL, "tile.hl_canopyLeaves", 1, W));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_japaneseMapleSapling", 1, W), new WoodEntry(ST.make(MD.HiL, "tile.hl_japaneseMapleWood", 1, W), WoodDictionary.BEAMS.get(BlocksGT.BeamA, 1), WoodDictionary.PLANKS.get(tPlank, 3)), ST.make(MD.HiL, "tile.hl_japaneseMapleLeaves", 1, W));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_mangroveSapling"     , 1, W), new WoodEntry(ST.make(MD.HiL, "tile.hl_mangroveWood"     , 1, W)                                             , WoodDictionary.PLANKS.get(tPlank, 3)), ST.make(MD.HiL, "tile.hl_mangroveLeaves", 1, W));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_palmSapling"         , 1, W), new WoodEntry(ST.make(MD.HiL, "tile.hl_palmWood"         , 1, W), WoodDictionary.BEAMS.get(BlocksGT.BeamB, 2)                                      ), ST.make(MD.HiL, "tile.hl_palmLeaves", 1, W));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_poplarSapling"       , 1, W), new WoodEntry(ST.make(MD.HiL, "tile.hl_poplarWood"       , 1, W)                                             , WoodDictionary.PLANKS.get(tPlank, 1)), ST.make(MD.HiL, "tile.hl_poplarLeaves", 1, W));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_redwoodSapling"      , 1, W), new WoodEntry(ST.make(MD.HiL, "tile.hl_redwoodWood"      , 1, W)                                             , WoodDictionary.PLANKS.get(tPlank, 2)), ST.make(MD.HiL, "tile.hl_redwoodLeaves", 1, W));
			
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_ironwoodSapling"     , 1, W), new WoodEntry(ST.make(MD.HiL, "tile.hl_ironwoodWood"     , 1, W), WoodDictionary.PLANKS.get(tPlank, 3), 1, 300, OP.dustSmall.mat(MT.LiveRoot, 1), MT.LiveRoot), ST.make(MD.HiL, "tile.hl_ironwoodLeaves", 1, W));
			
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_autumnOrangeSapling" , 1, W), WoodDictionary.WOODS.get(Blocks.log, 0), ST.make(MD.HiL, "tile.hl_autumnOrangeLeaves", 1, W));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_autumnYellowSapling" , 1, W), WoodDictionary.WOODS.get(Blocks.log, 0), ST.make(MD.HiL, "tile.hl_autumnYellowLeaves", 1, W));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_deadSapling"         , 1, W), WoodDictionary.WOODS.get(Blocks.log, 0), WoodDictionary.LEAVES.get(Blocks.leaves, 0));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_decBushSapling"      , 1, W), WoodDictionary.WOODS.get(Blocks.log, 0), WoodDictionary.LEAVES.get(Blocks.leaves, 0));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_greatOakSapling"     , 1, W), WoodDictionary.WOODS.get(Blocks.log, 0), WoodDictionary.LEAVES.get(Blocks.leaves, 0));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_evgBushSapling"      , 1, W), WoodDictionary.WOODS.get(Blocks.log, 1), WoodDictionary.LEAVES.get(Blocks.leaves, 1));
			new SaplingEntry(ST.make(MD.HiL, "tile.hl_beechSapling"        , 1, W), WoodDictionary.WOODS.get(Blocks.log, 2), WoodDictionary.LEAVES.get(Blocks.leaves, 2));
			
			CR.shaped(ST.make(tPlank, 1, 0), CR.DEF_NCC, "S", "S", 'S', ST.make(tSlab, 1, 0));
			CR.shaped(ST.make(tPlank, 1, 1), CR.DEF_NCC, "S", "S", 'S', ST.make(tSlab, 1, 1));
			CR.shaped(ST.make(tPlank, 1, 2), CR.DEF_NCC, "S", "S", 'S', ST.make(tSlab, 1, 2));
			CR.shaped(ST.make(tPlank, 1, 3), CR.DEF_NCC, "S", "S", 'S', ST.make(tSlab, 1, 3));
		}
		
		// Tropicraft Trees
		if (MD.TROPIC.mLoaded) {
			new SaplingEntry(IL.TROPIC_Sapling_Palm      .get(1), new WoodEntry(IL.TROPIC_Log_Palm    .get(1), WoodDictionary.BEAMS.get(BlocksGT.BeamB, 2), new PlankEntry(ST.make(MD.TROPIC, "tile.plank", 1, 0), ST.make(MD.TROPIC, "tile.singleSlabs", 1, 3), ST.make(MD.TROPIC, "tile.palmStairs"    , 1, 0), 176)), IL.TROPIC_Leaves_Palm    .get(1));
			new SaplingEntry(IL.TROPIC_Sapling_Mahogany  .get(1), new WoodEntry(IL.TROPIC_Log_Mahogany.get(1), WoodDictionary.BEAMS.get(BlocksGT.Beam1, 3), new PlankEntry(ST.make(MD.TROPIC, "tile.plank", 1, 1), ST.make(MD.TROPIC, "tile.singleSlabs", 1, 3), ST.make(MD.TROPIC, "tile.mahoganyStairs", 1, 0)     )), IL.TROPIC_Leaves_Mahogany.get(1));
			new SaplingEntry(IL.TROPIC_Sapling_Grapefruit.get(1), WoodDictionary.WOODS.get(Blocks.log, 0), IL.TROPIC_Leaves_Grapefruit.get(1));
			new SaplingEntry(IL.TROPIC_Sapling_Lemon     .get(1), WoodDictionary.WOODS.get(Blocks.log, 0), IL.TROPIC_Leaves_Lemon     .get(1));
			new SaplingEntry(IL.TROPIC_Sapling_Lime      .get(1), WoodDictionary.WOODS.get(Blocks.log, 0), IL.TROPIC_Leaves_Lime      .get(1));
			new SaplingEntry(IL.TROPIC_Sapling_Orange    .get(1), WoodDictionary.WOODS.get(Blocks.log, 0), IL.TROPIC_Leaves_Orange    .get(1));
			
			CR.shaped(ST.make(MD.TROPIC, "tile.plank", 1, 0), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.TROPIC, "tile.singleSlabs", 1, 3));
		}
		
		
		// Candycraft Trees
		if (MD.CANDY.mLoaded) {
			new SaplingEntry(IL.CANDY_Sapling_Chocolate.get(1), new WoodEntry(IL.CANDY_Log      .get(1), new PlankEntry(IL.CANDY_Plank      .get(1), ST.make(MD.CANDY, "CandyHalfSlab" , 1, W), ST.make(MD.CANDY, "X1"  , 1, W), MT.Marshmallow, 182), 1, 0, OP.dust.mat(MT.Marshmallow, 1), MT.Marshmallow), IL.CANDY_Leaves_Chocolate.get(1));
			new SaplingEntry(IL.CANDY_Sapling_Caramel  .get(1), new WoodEntry(IL.CANDY_Log_Dark .get(1), new PlankEntry(IL.CANDY_Plank_Dark .get(1), ST.make(MD.CANDY, "CandyHalfSlab2", 1, W), ST.make(MD.CANDY, "XX1" , 1, W), MT.Marshmallow, 183), 1, 0, OP.dust.mat(MT.Marshmallow, 1), MT.Marshmallow), IL.CANDY_Leaves_Caramel  .get(1));
			new SaplingEntry(IL.CANDY_Sapling_White    .get(1), new WoodEntry(IL.CANDY_Log_Light.get(1), new PlankEntry(IL.CANDY_Plank_Light.get(1), ST.make(MD.CANDY, "CandyHalfSlab3", 1, W), ST.make(MD.CANDY, "XXX1", 1, W), MT.Marshmallow, 184), 1, 0, OP.dust.mat(MT.Marshmallow, 1), MT.Marshmallow), IL.CANDY_Leaves_White    .get(1));
			new SaplingEntry(IL.CANDY_Sapling_Cherry   .get(1), WoodDictionary.WOODS.get(new ItemStackContainer(IL.CANDY_Log.get(1))), IL.CANDY_Leaves_Cherry   .get(1));
			
			CR.shaped(IL.CANDY_Plank      .get(1), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.CANDY, "CandyHalfSlab" , 1, W));
			CR.shaped(IL.CANDY_Plank_Dark .get(1), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.CANDY, "CandyHalfSlab2", 1, W));
			CR.shaped(IL.CANDY_Plank_Light.get(1), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.CANDY, "CandyHalfSlab3", 1, W));
		}
		
		
		// Forestry Trees
		if (MD.FR.mLoaded) {
			Block tPlank = ST.block(MD.FR, "planks"), tLog = ST.block(MD.FR, "logs"), tSlab = ST.block(MD.FR, "slabs"), tStair = ST.block(MD.FR, "stairs");
			for (int i = 0; i < 29; i++) {
			new WoodEntry(ST.make(tLog, 1, i), new PlankEntry(ST.make(tPlank, 1, i), ST.make(tSlab, 1, i), ST.make(tStair, 1, i), 8+i), i==12?2:1, i==12?500:250);
			CR.shaped(ST.make(tPlank, 1, i), CR.DEF_NCC, "S", "S", 'S', ST.make(tSlab, 1, i));
			}
			tPlank = ST.block(MD.FR, "planksFireproof"); tLog = ST.block(MD.FR, "logsFireproof"); tSlab = ST.block(MD.FR, "slabsFireproof"); tStair = ST.block(MD.FR, "stairsFireproof");
			for (int i = 0; i < 29; i++) {
			new WoodEntry(ST.make(tLog, 1, i), new PlankEntry(ST.make(tPlank, 1, i), ST.make(tSlab, 1, i), ST.make(tStair, 1, i)     ), i==12?2:1, i==12?500:250);
			CR.shaped(ST.make(tPlank, 1, i), CR.DEF_NCC, "S", "S", 'S', ST.make(tSlab, 1, i));
			}
		}
		// Extra Trees
		if (MD.BINNIE_TREE.mLoaded) {
			Block tPlank = ST.block(MD.BINNIE_TREE, "planks"), tLog = ST.block(MD.BINNIE_TREE, "log"), tSlab = ST.block(MD.BINNIE_TREE, "slab"), tStair = ST.block(MD.BINNIE_TREE, "stairs");
			for (int i = 0; i < 35; i++) {
			new PlankEntry(ST.make(tPlank, 1, i), ST.make(tSlab, 1, i), ST.make(tStair, 1, i), 126+i);
			CR.shaped(ST.make(tPlank, 1, i), CR.DEF_NCC, "S", "S", 'S', ST.make(tSlab, 1, i));
			}
			
			new WoodEntry(ST.make(tLog, 1,  0), WoodDictionary.PLANKS.get(tPlank, 14));
			new WoodEntry(ST.make(tLog, 1,  1), WoodDictionary.PLANKS.get(tPlank,  4));
			new WoodEntry(ST.make(tLog, 1,  2), WoodDictionary.PLANKS.get(tPlank, 10));
			new WoodEntry(ST.make(tLog, 1,  3), WoodDictionary.PLANKS.get(ST.block(MD.FR, "planks"), 15));
			new WoodEntry(ST.make(tLog, 1,  4), WoodDictionary.PLANKS.get(tPlank, 12));
			new WoodEntry(ST.make(tLog, 1,  5), WoodDictionary.PLANKS.get(tPlank, 18));
			new WoodEntry(ST.make(tLog, 1,  6), WoodDictionary.PLANKS.get(tPlank,  2));
			new WoodEntry(ST.make(tLog, 1,  7), WoodDictionary.PLANKS.get(tPlank, 28));
			new WoodEntry(ST.make(tLog, 1,  8), WoodDictionary.PLANKS.get(tPlank,  6));
			new WoodEntry(ST.make(tLog, 1,  9), WoodDictionary.PLANKS.get(tPlank,  5));
			new WoodEntry(ST.make(tLog, 1, 10), WoodDictionary.PLANKS.get(tPlank, 17));
			new WoodEntry(ST.make(tLog, 1, 11));
			new WoodEntry(ST.make(tLog, 1, 12), WoodDictionary.PLANKS.get(tPlank, 15));
			new WoodEntry(ST.make(tLog, 1, 13), WoodDictionary.PLANKS.get(tPlank,  3));
			new WoodEntry(ST.make(tLog, 1, 14), WoodDictionary.PLANKS.get(tPlank,  0));
			new WoodEntry(ST.make(tLog, 1, 15), WoodDictionary.PLANKS.get(tPlank,  7));
			new WoodEntry(ST.make(tLog, 1, 16), WoodDictionary.PLANKS.get(tPlank, 11));
			new WoodEntry(ST.make(tLog, 1, 17), WoodDictionary.PLANKS.get(tPlank, 13));
			new WoodEntry(ST.make(tLog, 1, 18), WoodDictionary.PLANKS.get(tPlank, 19));
			new WoodEntry(ST.make(tLog, 1, 19), WoodDictionary.PLANKS.get(tPlank, 29));
			new WoodEntry(ST.make(tLog, 1, 20), WoodDictionary.PLANKS.get(tPlank,  8));
			new WoodEntry(ST.make(tLog, 1, 21), WoodDictionary.PLANKS.get(tPlank,  1));
			new WoodEntry(ST.make(tLog, 1, 22), WoodDictionary.PLANKS.get(tPlank, 30));
			new WoodEntry(ST.make(tLog, 1, 23), WoodDictionary.PLANKS.get(tPlank, 31));
			new WoodEntry(ST.make(tLog, 1, 24), WoodDictionary.PLANKS.get(tPlank, 25));
			new WoodEntry(ST.make(tLog, 1, 25), WoodDictionary.PLANKS.get(tPlank, 16));
			new WoodEntry(ST.make(tLog, 1, 26), WoodDictionary.PLANKS.get(tPlank, 20));
			new WoodEntry(ST.make(tLog, 1, 27), WoodDictionary.PLANKS.get(tPlank, 22));
			new WoodEntry(ST.make(tLog, 1, 28), WoodDictionary.PLANKS.get(tPlank, 23));
			new WoodEntry(ST.make(tLog, 1, 29), WoodDictionary.PLANKS.get(tPlank, 32));
			new WoodEntry(ST.make(tLog, 1, 30), WoodDictionary.PLANKS.get(tPlank, 27));
			new WoodEntry(ST.make(tLog, 1, 31), WoodDictionary.PLANKS.get(tPlank, 24));
			new WoodEntry(ST.make(tLog, 1, 32), WoodDictionary.PLANKS.get(tPlank, 33));
			new WoodEntry(ST.make(tLog, 1, 33), WoodDictionary.PLANKS.get(tPlank, 26));
			new WoodEntry(ST.make(tLog, 1, 34), WoodDictionary.PLANKS.get(tPlank, 26));
			new WoodEntry(ST.make(tLog, 1, 35), WoodDictionary.PLANKS.get(tPlank,  9));
			new WoodEntry(ST.make(tLog, 1, 36), WoodDictionary.PLANKS.get(tPlank, 21));
			new WoodEntry(ST.make(tLog, 1, 37), WoodDictionary.PLANKS.get(tPlank, 26));
			new WoodEntry(ST.make(tLog, 1, 38), WoodDictionary.BEAMS.get(BlocksGT.BeamB, 1), ST.make(MD.HaC, "cinnamonItem", 1, 0, IL.Food_Cinnamon), MT.Cinnamon);
			new WoodEntry(ST.make(tLog, 1, 39), WoodDictionary.PLANKS.get(tPlank, 34));
		}
		// Rockhounding Woods
		if (MD.RH.mLoaded) {
			Block tPlankA = ST.block(MD.RH, "globbypotato_rockhounding_fossilWoodPlanks"), tPlankB = ST.block(MD.RH, "globbypotato_rockhounding_mobPlanks");
			Block tSlabA  = ST.block(MD.RH, "globbypotato_rockhounding_fossilSlabs"     ), tSlabB  = ST.block(MD.RH, "globbypotato_rockhounding_mobSlabs");
			Item  tStickA = ST.item (MD.RH, "globbypotato_rockhounding_fossilSticks"    ), tStickB = ST.item (MD.RH, "globbypotato_rockhounding_mobSticks");
			
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_bogLogs"   , 1, 0), new PlankEntry(ST.make(tPlankA, 1, 0), ST.make(tSlabA, 1, 0), ST.make(MD.RH, "globbypotato_rockhounding_bogOakStairs"           , 1, 0), MT.Wood, 187, ST.make(tStickA, 1, 0)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_bogLogs"   , 1, 1), new PlankEntry(ST.make(tPlankA, 1, 1), ST.make(tSlabA, 1, 1), ST.make(MD.RH, "globbypotato_rockhounding_bogSpruceStairs"        , 1, 0), MT.Wood, 188, ST.make(tStickA, 1, 1)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_bogLogs"   , 1, 2), new PlankEntry(ST.make(tPlankA, 1, 2), ST.make(tSlabA, 1, 2), ST.make(MD.RH, "globbypotato_rockhounding_bogBirchStairs"         , 1, 0), MT.Wood, 189, ST.make(tStickA, 1, 2)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_bogLogs"   , 1, 3), new PlankEntry(ST.make(tPlankA, 1, 3), ST.make(tSlabA, 1, 3), ST.make(MD.RH, "globbypotato_rockhounding_bogPetrifiedStairs"     , 1, 0), MT.Wood, 190, ST.make(tStickA, 1, 3)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_fossilLogs", 1, 0), new PlankEntry(ST.make(tPlankA, 1, 4), ST.make(tSlabA, 1, 4), ST.make(MD.RH, "globbypotato_rockhounding_fossilPalmStairs"       , 1, 0), MT.Wood, 191, ST.make(tStickA, 1, 4)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_fossilLogs", 1, 1), new PlankEntry(ST.make(tPlankA, 1, 5), ST.make(tSlabA, 1, 5), ST.make(MD.RH, "globbypotato_rockhounding_fossilOpalizedStairs"   , 1, 0), MT.Wood, 192, ST.make(tStickA, 1, 5)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_fossilLogs", 1, 2), new PlankEntry(ST.make(tPlankA, 1, 6), ST.make(tSlabA, 1, 6), ST.make(MD.RH, "globbypotato_rockhounding_fossilRainbowStairs"    , 1, 0), MT.Wood, 193, ST.make(tStickA, 1, 6)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_fossilLogs", 1, 3), new PlankEntry(ST.make(tPlankA, 1, 7), ST.make(tSlabA, 1, 7), ST.make(MD.RH, "globbypotato_rockhounding_fossilAraucariaStairs"  , 1, 0), MT.Wood, 194, ST.make(tStickA, 1, 7)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_swampLogs" , 1, 0), new PlankEntry(ST.make(tPlankA, 1, 8), ST.make(tSlabA, 1, 8), ST.make(MD.RH, "globbypotato_rockhounding_swampKauriStairs"       , 1, 0), MT.Wood, 195, ST.make(tStickA, 1, 8)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_swampLogs" , 1, 1), new PlankEntry(ST.make(tPlankA, 1, 9), ST.make(tSlabA, 1, 9), ST.make(MD.RH, "globbypotato_rockhounding_swampPipeStairs"        , 1, 0), MT.Wood, 196, ST.make(tStickA, 1, 9)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_swampLogs" , 1, 2), new PlankEntry(ST.make(tPlankA, 1,10), ST.make(tSlabA, 1,10), ST.make(MD.RH, "globbypotato_rockhounding_swampMophaneStairs"     , 1, 0), MT.Wood, 197, ST.make(tStickA, 1,10)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_swampLogs" , 1, 3), new PlankEntry(ST.make(tPlankA, 1,11), ST.make(tSlabA, 1,11), ST.make(MD.RH, "globbypotato_rockhounding_swampDriftwoodStairs"   , 1, 0), MT.Wood, 198, ST.make(tStickA, 1,11)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_coldLogs"  , 1, 0), new PlankEntry(ST.make(tPlankA, 1,12), ST.make(tSlabA, 1,12), ST.make(MD.RH, "globbypotato_rockhounding_coldAzuriteStairs"      , 1, 0), MT.Wood, 199, ST.make(tStickA, 1,12)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_coldLogs"  , 1, 1), new PlankEntry(ST.make(tPlankA, 1,13), ST.make(tSlabA, 1,13), ST.make(MD.RH, "globbypotato_rockhounding_coldBeechStairs"        , 1, 0), MT.Wood, 200, ST.make(tStickA, 1,13)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_coldLogs"  , 1, 2), new PlankEntry(ST.make(tPlankA, 1,14), ST.make(tSlabA, 1,14), ST.make(MD.RH, "globbypotato_rockhounding_coldTeredoStairs"       , 1, 0), MT.Wood, 201, ST.make(tStickA, 1,14)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_coldLogs"  , 1, 3), new PlankEntry(ST.make(tPlankA, 1,15), ST.make(tSlabA, 1,15), ST.make(MD.RH, "globbypotato_rockhounding_coldRedwoodStairs"      , 1, 0), MT.Wood, 202, ST.make(tStickA, 1,15)));
			
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogs"   , 1, 0), new PlankEntry(ST.make(tPlankB, 1, 0), ST.make(tSlabB, 1, 0), ST.make(MD.RH, "globbypotato_rockhounding_zombieMobStairs"        , 1, 0), MT.Wood, 203, ST.make(tStickB, 1, 0)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogs"   , 1, 1), new PlankEntry(ST.make(tPlankB, 1, 1), ST.make(tSlabB, 1, 1), ST.make(MD.RH, "globbypotato_rockhounding_creeperMobStairs"       , 1, 0), MT.Wood, 204, ST.make(tStickB, 1, 1)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogs"   , 1, 2), new PlankEntry(ST.make(tPlankB, 1, 2), ST.make(tSlabB, 1, 2), ST.make(MD.RH, "globbypotato_rockhounding_skeletonMobStairs"      , 1, 0), MT.Wood, 205, ST.make(tStickB, 1, 2)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogs"   , 1, 3), new PlankEntry(ST.make(tPlankB, 1, 3), ST.make(tSlabB, 1, 3), ST.make(MD.RH, "globbypotato_rockhounding_endermanMobStairs"      , 1, 0), MT.Wood, 206, ST.make(tStickB, 1, 3)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogsB"  , 1, 0), new PlankEntry(ST.make(tPlankB, 1, 4), ST.make(tSlabB, 1, 4), ST.make(MD.RH, "globbypotato_rockhounding_witchMobStairs"         , 1, 0), MT.Wood, 207, ST.make(tStickB, 1, 4)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogsB"  , 1, 1), new PlankEntry(ST.make(tPlankB, 1, 5), ST.make(tSlabB, 1, 5), ST.make(MD.RH, "globbypotato_rockhounding_slimeMobStairs"         , 1, 0), MT.Wood, 208, ST.make(tStickB, 1, 5)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogsB"  , 1, 2), new PlankEntry(ST.make(tPlankB, 1, 6), ST.make(tSlabB, 1, 6), ST.make(MD.RH, "globbypotato_rockhounding_pigzombieMobStairs"     , 1, 0), MT.Wood, 209, ST.make(tStickB, 1, 6)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogsB"  , 1, 3), new PlankEntry(ST.make(tPlankB, 1, 7), ST.make(tSlabB, 1, 7), ST.make(MD.RH, "globbypotato_rockhounding_witherMobStairs"        , 1, 0), MT.Wood, 210, ST.make(tStickB, 1, 7)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogsC"  , 1, 0), new PlankEntry(ST.make(tPlankB, 1, 8), ST.make(tSlabB, 1, 8), ST.make(MD.RH, "globbypotato_rockhounding_spiderMobStairs"        , 1, 0), MT.Wood, 211, ST.make(tStickB, 1, 8)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogsC"  , 1, 1), new PlankEntry(ST.make(tPlankB, 1, 9), ST.make(tSlabB, 1, 9), ST.make(MD.RH, "globbypotato_rockhounding_ghastMobStairs"         , 1, 0), MT.Wood, 212, ST.make(tStickB, 1, 9)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogsC"  , 1, 2), new PlankEntry(ST.make(tPlankB, 1,10), ST.make(tSlabB, 1,10), ST.make(MD.RH, "globbypotato_rockhounding_blazeMobStairs"         , 1, 0), MT.Wood, 213, ST.make(tStickB, 1,10)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogsC"  , 1, 3), new PlankEntry(ST.make(tPlankB, 1,11), ST.make(tSlabB, 1,11), ST.make(MD.RH, "globbypotato_rockhounding_magmaMobStairs"         , 1, 0), MT.Wood, 214, ST.make(tStickB, 1,11)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogsD"  , 1, 0), new PlankEntry(ST.make(tPlankB, 1,12), ST.make(tSlabB, 1,12), ST.make(MD.RH, "globbypotato_rockhounding_witchBMobStairs"        , 1, 0), MT.Wood, 215, ST.make(tStickB, 1,12)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogsD"  , 1, 1), new PlankEntry(ST.make(tPlankB, 1,13), ST.make(tSlabB, 1,13), ST.make(MD.RH, "globbypotato_rockhounding_spiderBMobStairs"       , 1, 0), MT.Wood, 216, ST.make(tStickB, 1,13)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogsD"  , 1, 2), new PlankEntry(ST.make(tPlankB, 1,14), ST.make(tSlabB, 1,14), ST.make(MD.RH, "globbypotato_rockhounding_zombieBMobStairs"       , 1, 0), MT.Wood, 217, ST.make(tStickB, 1,14)));
			new WoodEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLogsD"  , 1, 3), new PlankEntry(ST.make(tPlankB, 1,15), ST.make(tSlabB, 1,15), ST.make(MD.RH, "globbypotato_rockhounding_endermanBMobStairs"     , 1, 0), MT.Wood, 218, ST.make(tStickB, 1,15)));
			
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_bogOakStairs"           , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_bogSpruceStairs"        , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_bogBirchStairs"         , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_bogPetrifiedStairs"     , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_fossilPalmStairs"       , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_fossilOpalizedStairs"   , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_fossilRainbowStairs"    , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_fossilAraucariaStairs"  , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_swampKauriStairs"       , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_swampPipeStairs"        , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_swampMophaneStairs"     , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_swampDriftwoodStairs"   , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_coldAzuriteStairs"      , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_coldBeechStairs"        , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_coldTeredoStairs"       , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_coldRedwoodStairs"      , 1, 0), OD.stairWood);
			
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_zombieMobStairs"        , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_creeperMobStairs"       , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_skeletonMobStairs"      , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_endermanMobStairs"      , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_witchMobStairs"         , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_slimeMobStairs"         , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_pigzombieMobStairs"     , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_witherMobStairs"        , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_spiderMobStairs"        , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_ghastMobStairs"         , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_blazeMobStairs"         , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_magmaMobStairs"         , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_witchBMobStairs"        , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_spiderBMobStairs"       , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_zombieBMobStairs"       , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_endermanBMobStairs"     , 1, 0), OD.stairWood);
			
			OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_coldLogs"               , 1, W), OD.logWood);
			
			for (int i = 0; i < 4; i++) {
				new LeafEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLeaves" , 1, i));
				new LeafEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLeavesB", 1, i));
				new LeafEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLeavesC", 1, i));
				new LeafEntry(ST.make(MD.RH, "globbypotato_rockhounding_mobLeavesD", 1, i));
				OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_mobLeaves" , 1, i), OP.treeLeaves);
				OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_mobLeavesB", 1, i), OP.treeLeaves);
				OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_mobLeavesC", 1, i), OP.treeLeaves);
				OM.reg(ST.make(MD.RH, "globbypotato_rockhounding_mobLeavesD", 1, i), OP.treeLeaves);
			}
			for (int i = 0; i < 16; i++) {
				CR.shaped(ST.make(tPlankA, 1, i), CR.DEF_NCC, "S", "S", 'S', ST.make(tSlabA, 1, i));
				CR.shaped(ST.make(tPlankB, 1, i), CR.DEF_NCC, "S", "S", 'S', ST.make(tSlabB, 1, i));
				OM.reg(ST.make(tSlabA, 1, i), OD.slabWood);
				OM.reg(ST.make(tSlabB, 1, i), OD.slabWood);
			}
		}
		// Plant Mega Pack Bamboo
		if (MD.PMP.mLoaded) {
			final String[] tNames = {"bambooAsper", "bambooFargesiaRobusta", "bambooGiantTimber", "bambooGolden", "bambooMoso", "bambooShortTassled", "bambooTimorBlack", "bambooTropicalBlue", "bambooWetForest"};
			for (int i = 0; i < 9; i++) {
				new PlankEntry(ST.make(MD.PMP, tNames[i]+"Block", 1, 0), ST.make(MD.PMP, tNames[i]+"Slab", 1, 0), ST.make(MD.PMP, tNames[i]+"Stairs", 1, 0), MT.Bamboo, 224+i, ST.make(MD.PMP, tNames[i]+"Pole", 1, 0), 4, 4, 4);
				CR.shaped(ST.make(MD.PMP, tNames[i]+"Block", 1, 0), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.PMP, tNames[i]+"Slab", 1, 0));
				OM.reg(ST.make(MD.PMP, tNames[i]+"Slab", 1, 0), OD.slabWood);
			}
		}
		// MineFantasy II Trees
		if (MD.MF2.mLoaded) {
			new PlankEntry(ST.make(MD.MF2, "refined_planks", 1, 0), 219);
			new PlankEntry(ST.make(MD.MF2, "nailed_planks" , 1, 0), 220);
			new SaplingEntry(ST.make(MD.MF2, "yew_sapling"     , 1, 0), new WoodEntry(ST.make(MD.MF2, "yew_log"     , 1, 0), new PlankEntry(ST.make(MD.MF2, "yew_planks"     , 1, 0), IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), ST.make(MD.MF2, "yew_stair"     , 1, 0), 221)), ST.make(MD.MF2, "yew_leaves"      , 1, 0));
			new SaplingEntry(ST.make(MD.MF2, "ironbark_sapling", 1, 0), new WoodEntry(ST.make(MD.MF2, "ironbark_log", 1, 0), new PlankEntry(ST.make(MD.MF2, "ironbark_planks", 1, 0), IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), ST.make(MD.MF2, "ironbark_stair", 1, 0), 222)), ST.make(MD.MF2, "ironbark_leaves" , 1, 0));
			new SaplingEntry(ST.make(MD.MF2, "ebony_sapling"   , 1, 0), new WoodEntry(ST.make(MD.MF2, "ebony_log"   , 1, 0), new PlankEntry(ST.make(MD.MF2, "ebony_planks"   , 1, 0), IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), ST.make(MD.MF2, "ebony_stair"   , 1, 0), 223)), ST.make(MD.MF2, "ebony_leaves"    , 1, 0));
			
			OM.reg(ST.make(MD.MF2, "refined_planks"  , 1, 0), OD.plankWood);
			OM.reg(ST.make(MD.MF2, "nailed_planks"   , 1, 0), OD.plankWood);
			OM.reg(ST.make(MD.MF2, "yew_planks"      , 1, 0), OD.plankWood);
			OM.reg(ST.make(MD.MF2, "ironbark_planks" , 1, 0), OD.plankWood);
			OM.reg(ST.make(MD.MF2, "ebony_planks"    , 1, 0), OD.plankWood);
			OM.reg(ST.make(MD.MF2, "yew_stair"       , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.MF2, "ironbark_stair"  , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.MF2, "ebony_stair"     , 1, 0), OD.stairWood);
			OM.reg(ST.make(MD.MF2, "yew_leaves"      , 1, 0), OP.treeLeaves);
			OM.reg(ST.make(MD.MF2, "ironbark_leaves" , 1, 0), OP.treeLeaves);
			OM.reg(ST.make(MD.MF2, "ebony_leaves"    , 1, 0), OP.treeLeaves);
			OM.reg(ST.make(MD.MF2, "yew_sapling"     , 1, 0), OP.treeSapling);
			OM.reg(ST.make(MD.MF2, "ironbark_sapling", 1, 0), OP.treeSapling);
			OM.reg(ST.make(MD.MF2, "ebony_sapling"   , 1, 0), OP.treeSapling);
			OM.reg(ST.make(MD.MF2, "yew_log"         , 1, 0), OD.logWood);
			OM.reg(ST.make(MD.MF2, "ironbark_log"    , 1, 0), OD.logWood);
			OM.reg(ST.make(MD.MF2, "ebony_log"       , 1, 0), OD.logWood);
		}
		// Erebus Trees
		if (MD.ERE.mLoaded) {
			Block tPlank = ST.block(MD.ERE, "planks");
			new PlankEntry(ST.make(tPlank, 1, 0), ST.make(MD.ERE, "slabPlanksBaobab"    , 1, 0), ST.make(MD.ERE, "plankStairBaobab"    , 1, 0), 109);
			new PlankEntry(ST.make(tPlank, 1, 1), ST.make(MD.ERE, "slabPlanksEucalyptus", 1, 0), ST.make(MD.ERE, "plankStairEucalyptus", 1, 0), 110);
			new PlankEntry(ST.make(tPlank, 1, 2), ST.make(MD.ERE, "slabPlanksMahogany"  , 1, 0), ST.make(MD.ERE, "plankStairMahogany"  , 1, 0), 111);
			new PlankEntry(ST.make(tPlank, 1, 3), ST.make(MD.ERE, "slabPlanksMossbark"  , 1, 0), ST.make(MD.ERE, "plankStairMossbark"  , 1, 0), 112);
			new PlankEntry(ST.make(tPlank, 1, 4), ST.make(MD.ERE, "slabPlanksAsper"     , 1, 0), ST.make(MD.ERE, "plankStairAsper"     , 1, 0), 113);
			new PlankEntry(ST.make(tPlank, 1, 5), ST.make(MD.ERE, "slabPlanksCypress"   , 1, 0), ST.make(MD.ERE, "plankStairCypress"   , 1, 0), 114);
			new PlankEntry(ST.make(tPlank, 1, 6), ST.make(MD.ERE, "slabPlanksSap"       , 1, 0), ST.make(MD.ERE, "plankStairSap"       , 1, 0), 115);
			new PlankEntry(ST.make(tPlank, 1, 7), ST.make(MD.ERE, "slabPlanksWhite"     , 1, 0), ST.make(MD.ERE, "plankStairWhite"     , 1, 0), 116);
			new PlankEntry(ST.make(tPlank, 1, 8), ST.make(MD.ERE, "slabPlanksBamboo"    , 1, 0), ST.make(MD.ERE, "plankStairBamboo"    , 1, 0), MT.Bamboo, 117);
			new PlankEntry(ST.make(tPlank, 1, 9), ST.make(MD.ERE, "slabPlanksRotten"    , 1, 0), ST.make(MD.ERE, "plankStairRotten"    , 1, 0), 118);
			new PlankEntry(ST.make(tPlank, 1,10), ST.make(MD.ERE, "slabPlanksMarshwood" , 1, 0), ST.make(MD.ERE, "plankStairMarshwood" , 1, 0), 119);
			
			CR.shaped(ST.make(tPlank, 1, 0), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.ERE, "slabPlanksBaobab"    , 1, 0));
			CR.shaped(ST.make(tPlank, 1, 1), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.ERE, "slabPlanksEucalyptus", 1, 0));
			CR.shaped(ST.make(tPlank, 1, 2), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.ERE, "slabPlanksMahogany"  , 1, 0));
			CR.shaped(ST.make(tPlank, 1, 3), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.ERE, "slabPlanksMossbark"  , 1, 0));
			CR.shaped(ST.make(tPlank, 1, 4), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.ERE, "slabPlanksAsper"     , 1, 0));
			CR.shaped(ST.make(tPlank, 1, 5), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.ERE, "slabPlanksCypress"   , 1, 0));
			CR.shaped(ST.make(tPlank, 1, 6), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.ERE, "slabPlanksSap"       , 1, 0));
			CR.shaped(ST.make(tPlank, 1, 7), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.ERE, "slabPlanksWhite"     , 1, 0));
			CR.shaped(ST.make(tPlank, 1, 8), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.ERE, "slabPlanksBamboo"    , 1, 0));
			CR.shaped(ST.make(tPlank, 1, 9), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.ERE, "slabPlanksRotten"    , 1, 0));
			CR.shaped(ST.make(tPlank, 1,10), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.ERE, "slabPlanksMarshwood" , 1, 0));
			
			new WoodEntry(ST.make(MD.ERE, "logBaobab"    , 1, W), WoodDictionary.PLANKS.get(tPlank, 0));
			new WoodEntry(ST.make(MD.ERE, "logEucalyptus", 1, W), WoodDictionary.PLANKS.get(tPlank, 1));
			new WoodEntry(ST.make(MD.ERE, "logMahogany"  , 1, W), WoodDictionary.PLANKS.get(tPlank, 2));
			new WoodEntry(ST.make(MD.ERE, "logMossbark"  , 1, W), WoodDictionary.PLANKS.get(tPlank, 3));
			new WoodEntry(ST.make(MD.ERE, "logAsper"     , 1, W), WoodDictionary.PLANKS.get(tPlank, 4));
			new WoodEntry(ST.make(MD.ERE, "logCypress"   , 1, W), WoodDictionary.PLANKS.get(tPlank, 5));
			new WoodEntry(ST.make(MD.ERE, "logSap"       , 1, W), WoodDictionary.PLANKS.get(tPlank, 6));
			new WoodEntry(ST.make(MD.ERE, "saplessLog"   , 1, W), WoodDictionary.PLANKS.get(tPlank, 6));
			new WoodEntry(ST.make(MD.ERE, "logRotten"    , 1, W), WoodDictionary.PLANKS.get(tPlank, 9));
			new WoodEntry(ST.make(MD.ERE, "logMarshwood" , 1, W), WoodDictionary.PLANKS.get(tPlank,10));
			
			new SaplingEntry(ST.make(MD.ERE, "saplingBaobab"    , 1, W), WoodDictionary.WOODS.get(MD.ERE, "logBaobab"    , W), ST.make(MD.ERE, "leavesBaobab"    , 1, W));
			new SaplingEntry(ST.make(MD.ERE, "saplingEucalyptus", 1, W), WoodDictionary.WOODS.get(MD.ERE, "logEucalyptus", W), ST.make(MD.ERE, "leavesEucalyptus", 1, W));
			new SaplingEntry(ST.make(MD.ERE, "saplingMahogany"  , 1, W), WoodDictionary.WOODS.get(MD.ERE, "logMahogany"  , W), ST.make(MD.ERE, "leavesMahogany"  , 1, W));
			new SaplingEntry(ST.make(MD.ERE, "saplingMossbark"  , 1, W), WoodDictionary.WOODS.get(MD.ERE, "logMossbark"  , W), ST.make(MD.ERE, "leavesMossbark"  , 1, W));
			new SaplingEntry(ST.make(MD.ERE, "saplingAsper"     , 1, W), WoodDictionary.WOODS.get(MD.ERE, "logAsper"     , W), ST.make(MD.ERE, "leavesAsper"     , 1, W));
			new SaplingEntry(ST.make(MD.ERE, "saplingCypress"   , 1, W), WoodDictionary.WOODS.get(MD.ERE, "logCypress"   , W), ST.make(MD.ERE, "leavesCypress"   , 1, W));
			new SaplingEntry(ST.make(MD.ERE, "saplingSap"       , 1, W), WoodDictionary.WOODS.get(MD.ERE, "logSap"       , W), ST.make(MD.ERE, "leavesSap"       , 1, W));
			new SaplingEntry(ST.make(MD.ERE, "saplingMarshwood" , 1, W), WoodDictionary.WOODS.get(MD.ERE, "logMarshwood" , W), ST.make(MD.ERE, "leavesMarshwood" , 1, W));
		}
		// Netherite Plus Planks
		if (MD.NePl.mLoaded) {
			OreDictionary.registerOre(OD.plankWood.toString(), ST.make(MD.NePl, "CrimsonPlanks", 1, 0));
			OreDictionary.registerOre(OD.plankWood.toString(), ST.make(MD.NePl, "WarpedPlanks" , 1, 0));
			
			new PlankEntry(ST.make(MD.NePl, "CrimsonPlanks", 1, W), 233);
			new PlankEntry(ST.make(MD.NePl, "WarpedPlanks" , 1, W), 234);
		}
		// Chisel Planks
		if (MD.CHSL.mLoaded) {
			for (int i = 1; i < 16; i++) {
			OreDictionary.registerOre(OD.plankWood.toString(), ST.make(MD.CHSL, "oak_planks"        , 1, i));
			OreDictionary.registerOre(OD.plankWood.toString(), ST.make(MD.CHSL, "spruce_planks"     , 1, i));
			OreDictionary.registerOre(OD.plankWood.toString(), ST.make(MD.CHSL, "birch_planks"      , 1, i));
			OreDictionary.registerOre(OD.plankWood.toString(), ST.make(MD.CHSL, "jungle_planks"     , 1, i));
			OreDictionary.registerOre(OD.plankWood.toString(), ST.make(MD.CHSL, "acacia_planks"     , 1, i));
			OreDictionary.registerOre(OD.plankWood.toString(), ST.make(MD.CHSL, "dark_oak_planks"   , 1, i));
			}
			
			new PlankEntry(ST.make(MD.CHSL, "oak_planks"        , 1, W), ST.make(Blocks.wooden_slab, 1, 0), ST.make(Blocks.oak_stairs       , 1, 0));
			new PlankEntry(ST.make(MD.CHSL, "spruce_planks"     , 1, W), ST.make(Blocks.wooden_slab, 1, 1), ST.make(Blocks.spruce_stairs    , 1, 0));
			new PlankEntry(ST.make(MD.CHSL, "birch_planks"      , 1, W), ST.make(Blocks.wooden_slab, 1, 2), ST.make(Blocks.birch_stairs     , 1, 0));
			new PlankEntry(ST.make(MD.CHSL, "jungle_planks"     , 1, W), ST.make(Blocks.wooden_slab, 1, 3), ST.make(Blocks.jungle_stairs    , 1, 0));
			new PlankEntry(ST.make(MD.CHSL, "acacia_planks"     , 1, W), ST.make(Blocks.wooden_slab, 1, 4), ST.make(Blocks.acacia_stairs    , 1, 0));
			new PlankEntry(ST.make(MD.CHSL, "dark_oak_planks"   , 1, W), ST.make(Blocks.wooden_slab, 1, 5), ST.make(Blocks.dark_oak_stairs  , 1, 0));
		}
		// Mariculture Polished Logs and Planks
		if (MD.MaCu.mLoaded) {
			new WoodEntry(IL.MaCu_Polished_Logs.get(1), 1, 300, 0, 0, 0, NI, MT.WoodPolished, MT.Bark, OP.stickLong.mat(MT.Wood, 1));
			new PlankEntry(IL.MaCu_Polished_Planks.get(1), IL.Plank_Slab.get(1, ST.make(Blocks.wooden_slab, 1, 0)), MT.WoodPolished, 125, OP.stick.mat(MT.Wood, 1));
		}
		// Immersive Engineering Treated Wood
		if (MD.IE.mLoaded) {
			new PlankEntry(ST.make(MD.IE, "treatedWood", 1, 0), ST.make(MD.IE, "woodenDecoration", 1, 2), ST.make(MD.IE, "woodenStairs" , 1, 0), MT.WoodSealed, 40, OP.stick.mat(MT.Wood, 1));
			new PlankEntry(ST.make(MD.IE, "treatedWood", 1, 1), ST.make(MD.IE, "woodenDecoration", 1, 2), ST.make(MD.IE, "woodenStairs1", 1, 0), MT.WoodSealed, 41, OP.stick.mat(MT.Wood, 1));
			new PlankEntry(ST.make(MD.IE, "treatedWood", 1, 2), ST.make(MD.IE, "woodenDecoration", 1, 2), ST.make(MD.IE, "woodenStairs2", 1, 0), MT.WoodSealed, 42, OP.stick.mat(MT.Wood, 1));
			CR.shaped(ST.make(MD.IE, "treatedWood", 1, 0), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.IE, "woodenDecoration", 1, 2));
		}
	}
}
