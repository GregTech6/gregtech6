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

package gregtech.loaders.a;

import static gregapi.data.CS.*;

import gregapi.block.metatype.BlockMetaType;
import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.TC;
import gregapi.oredict.OreDictManager;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregtech.blocks.tree.BlockTreeLeaves;
import gregtech.blocks.tree.BlockTreeLog1;
import gregtech.blocks.tree.BlockTreeLog1FireProof;
import gregtech.blocks.tree.BlockTreeLogA;
import gregtech.blocks.tree.BlockTreeLogAFireProof;
import gregtech.blocks.tree.BlockTreeLogB;
import gregtech.blocks.tree.BlockTreeLogBFireProof;
import gregtech.blocks.tree.BlockTreeSapling;
import gregtech.blocks.wood.*;
import net.minecraft.init.Blocks;

public class Loader_Woods implements Runnable {
	@Override
	public void run() {
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Log1            = new BlockTreeLog1             ("gt.block.log.1"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Log1FireProof   = new BlockTreeLog1FireProof    ("gt.block.log.1.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.LogA            = new BlockTreeLogA             ("gt.block.log.a"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.LogAFireProof   = new BlockTreeLogAFireProof    ("gt.block.log.a.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.LogB            = new BlockTreeLogB             ("gt.block.log.b"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.LogBFireProof   = new BlockTreeLogBFireProof    ("gt.block.log.b.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.BeamA           = new BlockTreeBeamA            ("gt.block.beam.a"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.BeamAFireProof  = new BlockTreeBeamAFireProof   ("gt.block.beam.a.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.BeamB           = new BlockTreeBeamB            ("gt.block.beam.b"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.BeamBFireProof  = new BlockTreeBeamBFireProof   ("gt.block.beam.b.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Beam1           = new BlockTreeBeam1            ("gt.block.beam.1"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Beam1FireProof  = new BlockTreeBeam1FireProof   ("gt.block.beam.1.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Beam2           = new BlockTreeBeam2            ("gt.block.beam.2"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Beam2FireProof  = new BlockTreeBeam2FireProof   ("gt.block.beam.2.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Beam3           = new BlockTreeBeam3            ("gt.block.beam.3"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Beam3FireProof  = new BlockTreeBeam3FireProof   ("gt.block.beam.3.fireproof"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Planks          = new BlockTreePlanks           ("gt.block.planks"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.PlanksFireProof = new BlockTreePlanksFireProof  ("gt.block.planks.fireproof"));
		
		BlocksGT.Sapling    = new BlockTreeSapling  ("gt.block.sapling");
		BlocksGT.Leaves     = new BlockTreeLeaves   ("gt.block.leaves", BlocksGT.Sapling);
		
		IL.Plank_Stairs       .set(ST.make(Blocks.oak_stairs, 1, 0));
		IL.Plank_Slab         .set(ST.make(((BlockMetaType)BlocksGT.Planks).mSlabs[0], 1, 9));
		IL.Plank              .set(ST.make(BlocksGT.Planks, 1,  9));
		IL.Treated_Planks_Slab.set(ST.make(((BlockMetaType)BlocksGT.Planks).mSlabs[0], 1, 10));
		IL.Treated_Planks     .set(ST.make(BlocksGT.Planks, 1, 10));
		IL.Beam               .set(ST.make(BlocksGT.Beam2 , 1,  3));
		IL.Crate              .set(ST.make(BlocksGT.Planks, 1, 11));
		IL.Crate_Fireproof    .set(ST.make(BlocksGT.PlanksFireProof, 1, 11));
		
		CR.shaped(IL.Crate.get(1), CR.DEF_NCC, "Ts", "Pd", 'P', OD.plankAnyWood, 'T', OP.screw.dat(MT.HSLA));
		CR.shaped(IL.Crate.get(1), CR.DEF_NCC, "Ts", "Pd", 'P', OD.plankAnyWood, 'T', OP.screw.dat(ANY.Iron));
		CR.shaped(IL.Crate.get(1), CR.DEF_NCC, "Ts", "Pd", 'P', OD.plankAnyWood, 'T', OP.screw.dat(ANY.WoodPlastic));
		
		OM.reg(OP.plate, MT.WoodTreated, ST.make(BlocksGT.PlanksFireProof, 1, 10));
		OreDictManager.INSTANCE.setTarget(OP.plate, MT.Wood       , IL.Plank.get(1));
		OreDictManager.INSTANCE.setTarget(OP.plate, MT.WoodTreated, ST.make(BlocksGT.Planks, 1, 10));
		
		for (int i = 0; i < 16; i++) {
			if (i != 10) {
				OM.reg(ST.make(BlocksGT.Planks          , 1, i), OD.plankWood);
				OM.reg(ST.make(BlocksGT.PlanksFireProof , 1, i), OD.plankWood);
			}
			for (byte tSide : ALL_SIDES_VALID) {
				OM.reg(ST.make(((BlockMetaType)BlocksGT.Planks          ).mSlabs[tSide], 1, i), OD.slabWood);
				OM.reg(ST.make(((BlockMetaType)BlocksGT.PlanksFireProof ).mSlabs[tSide], 1, i), OD.slabWood);
			}
		}
		
		if (COMPAT_TC != null) {
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Log1             , 1, W), F, TC.stack(TC.ARBOR, 2));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Log1FireProof    , 1, W), F, TC.stack(TC.ARBOR, 2), TC.stack(TC.GELUM, 1));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Beam1            , 1, W), F, TC.stack(TC.ARBOR, 4));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Beam1FireProof   , 1, W), F, TC.stack(TC.ARBOR, 4), TC.stack(TC.GELUM, 1));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Beam2            , 1, W), F, TC.stack(TC.ARBOR, 4));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Beam2FireProof   , 1, W), F, TC.stack(TC.ARBOR, 4), TC.stack(TC.GELUM, 1));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Beam3            , 1, W), F, TC.stack(TC.ARBOR, 4));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Beam3FireProof   , 1, W), F, TC.stack(TC.ARBOR, 4), TC.stack(TC.GELUM, 1));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.BeamA            , 1, W), F, TC.stack(TC.ARBOR, 4));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.BeamAFireProof   , 1, W), F, TC.stack(TC.ARBOR, 4), TC.stack(TC.GELUM, 1));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.BeamB            , 1, W), F, TC.stack(TC.ARBOR, 4));
		COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.BeamBFireProof   , 1, W), F, TC.stack(TC.ARBOR, 4), TC.stack(TC.GELUM, 1));
		}
		
		// Those typically get overridden, but in case of a fuck up it is at least worth some Wood.
		OM.data(ST.make(BlocksGT.Log1           , 1, W), ANY.Wood, U*4);
		OM.data(ST.make(BlocksGT.Log1FireProof  , 1, W), ANY.Wood, U*4);
		OM.data(ST.make(BlocksGT.Beam1          , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.Beam1FireProof , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.Beam2          , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.Beam2FireProof , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.Beam3          , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.Beam3FireProof , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.BeamA          , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.BeamAFireProof , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.BeamB          , 1, W), ANY.Wood, U*8);
		OM.data(ST.make(BlocksGT.BeamBFireProof , 1, W), ANY.Wood, U*8);
	}
}
