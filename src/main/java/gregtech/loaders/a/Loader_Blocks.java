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

package gregtech.loaders.a;

import static gregapi.data.CS.*;

import gregapi.block.fluid.BlockBaseFluid;
import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TC;
import gregapi.old.Textures;
import gregapi.util.CR;
import gregapi.util.ST;
import gregapi.util.UT;
import gregtech.blocks.*;
import gregtech.blocks.fluids.BlockOcean;
import gregtech.blocks.fluids.BlockSwamp;
import gregtech.blocks.plants.BlockFlowersA;
import gregtech.blocks.plants.BlockFlowersB;
import gregtech.blocks.plants.BlockGlowtus;
import net.minecraft.block.Block;

public class Loader_Blocks implements Runnable {
	@Override
	public void run() {
		OUT.println("GT_Mod: Register Blocks.");
		
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.CFoam                               = new BlockCFoam                ("gt.block.cfoam"));
		BlocksGT.CFoamFresh                                                     = new BlockCFoamFresh           ("gt.block.cfoam.fresh");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.CFoam, 1, W), F, TC.stack(TC.TERRA, 1), TC.stack(TC.TUTAMEN, 1));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.CFoamFresh, 1, W), F, TC.stack(TC.TERRA, 1), TC.stack(TC.TUTAMEN, 1));
		
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Asphalt                             = new BlockAsphalt              ("gt.block.asphalt"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Asphalt, 1, W), F, TC.stack(TC.TERRA, 1), TC.stack(TC.ITER, 1));
		ItemsGT.addNEIRedirects(BlocksGT.Asphalt);
		
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Concrete                            = new BlockConcrete             ("gt.block.concrete"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Concrete, 1, W), F, TC.stack(TC.TERRA, 1), TC.stack(TC.FABRICO, 1));
		ItemsGT.addNEIRedirects(BlocksGT.Concrete);
		
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.ConcreteReinforced                  = new BlockConcreteReinforced   ("gt.block.concrete.reinforced"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.ConcreteReinforced, 1, W), F, TC.stack(TC.TERRA, 1), TC.stack(TC.FABRICO, 1), TC.stack(TC.TUTAMEN, 1));
		ItemsGT.addNEIRedirects(BlocksGT.ConcreteReinforced);
		for (byte i = 0; i < 16; i++) CR.shaped(ST.make(BlocksGT.ConcreteReinforced, 1, i), CR.DEF_MIR, "Se", "X ", 'X', ST.make(BlocksGT.Concrete, 1, i), 'S', OP.stick.dat(ANY.Iron));
		
		BlocksGT.Glass                                                          = new BlockGlassClear           ("gt.block.glass");
		BlocksGT.GlowGlass                                                      = new BlockGlassGlow            ("gt.block.glass.glow");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Glass, 1, W), F, TC.stack(TC.VITREUS, 2));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.GlowGlass, 1, W), F, TC.stack(TC.VITREUS, 2), TC.stack(TC.LUX, 2));
		
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Diggables                           = new BlockDiggable             ("gt.block.diggable"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Diggables, 1, W), F, TC.stack(TC.TERRA, 2));
		
		BlocksGT.Paths                                                          = new BlockPath                 ("gt.block.paths");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Paths, 1, W), F, TC.stack(TC.TERRA, 2));
		
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.RockOres                            = new BlockRockOres             ("gt.block.rockores"));
		
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.Sands                               = new BlockSands                ("gt.block.sands"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Sands, 1, W), F, TC.stack(TC.TERRA, 1));
		
		BlocksGT.Spikes_Sharp                                                   = new BlockSpikeSharp           ("gt.block.spikes.sharp");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Spikes_Sharp, 1, W), F, TC.stack(TC.METALLUM, 4), TC.stack(TC.TELUM, 4));
		
		BlocksGT.Spikes_Super                                                   = new BlockSpikeSuper           ("gt.block.spikes.super");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Spikes_Super, 1, W), F, TC.stack(TC.METALLUM, 4), TC.stack(TC.TELUM, 8));
		
		BlocksGT.Spikes_Metal                                                   = new BlockSpikeMetal           ("gt.block.spikes.metal");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Spikes_Metal, 1, W), F, TC.stack(TC.VENENUM, 4), TC.stack(TC.TELUM, 4));
		
		BlocksGT.Spikes_Fancy                                                   = new BlockSpikeFancy           ("gt.block.spikes.fancy");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Spikes_Fancy, 1, W), F, TC.stack(TC.LUCRUM, 4), TC.stack(TC.TELUM, 4));
		
		BlocksGT.FlowersA                                                       = new BlockFlowersA             ("gt.block.flower.a");
		BlocksGT.FlowersB                                                       = new BlockFlowersB             ("gt.block.flower.b");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make((Block)BlocksGT.FlowersA, 1, W), F, TC.stack(TC.HERBA, 2));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make((Block)BlocksGT.FlowersB, 1, W), F, TC.stack(TC.HERBA, 2));
		
		BlocksGT.Glowtus                                                        = new BlockGlowtus              ("gt.block.lilypad.glowtus");
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Glowtus, 1, W), F, TC.stack(TC.HERBA, 2), TC.stack(TC.LUX, 2));
		
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.BalesGrass                          = new BlockBaleGrass            ("gt.block.bale.grass"));
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.BalesCrop                           = new BlockBaleCrop             ("gt.block.bale.crop"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.BalesGrass, 1, W), F, TC.stack(TC.MESSIS, 4));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.BalesCrop, 1, W), F, TC.stack(TC.MESSIS, 4));
		
		BlocksGT.Ocean                                                          = new BlockOcean                ("gt.block.ocean", UT.Fluids.fluid("seawater"));
		BlocksGT.Swamp                                                          = new BlockSwamp                ("gt.block.swamp", UT.Fluids.fluid("waterdirty"));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Ocean         , 1, W), F, TC.stack(TC.AQUA, 3), TC.stack(TC.TEMPESTAS, 1));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.Swamp         , 1, W), F, TC.stack(TC.AQUA, 3), TC.stack(TC.VENENUM, 1));
		
		BlocksGT.OilExtraHeavy                                                  = new BlockBaseFluid            ("gt.block.fluid.oil.extraheavy"    , UT.Fluids.fluid("liquid_extra_heavy_oil"  ), 1000);
		BlocksGT.OilHeavy                                                       = new BlockBaseFluid            ("gt.block.fluid.oil.heavy"         , UT.Fluids.fluid("liquid_heavy_oil"        ), 1000);
		BlocksGT.OilMedium                                                      = new BlockBaseFluid            ("gt.block.fluid.oil.medium"        , UT.Fluids.fluid("liquid_medium_oil"       ), 1000);
		BlocksGT.OilLight                                                       = new BlockBaseFluid            ("gt.block.fluid.oil.light"         , UT.Fluids.fluid("liquid_light_oil"        ), 1000);
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.OilExtraHeavy , 1, W), F, TC.stack(TC.AQUA, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.POTENTIA, 3));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.OilHeavy      , 1, W), F, TC.stack(TC.AQUA, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.POTENTIA, 2));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.OilMedium     , 1, W), F, TC.stack(TC.AQUA, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.POTENTIA, 1));
		if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(ST.make(BlocksGT.OilLight      , 1, W), F, TC.stack(TC.AQUA, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.LUX, 1));
		
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.LongDistWire01                      = new BlockLongDistWire         ("gt.block.longdistwire.01", Textures.BlockIcons.LONG_DIST_WIRES_01, new byte[] {4, 4, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8}));
		
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 0), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Sn));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 1), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Pb));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 2), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(ANY.Cu));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 3), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Ag));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 4), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Au));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 5), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Electrum));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 6), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.BlueAlloy));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 7), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.ElectrotineAlloy));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 8), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(ANY.Steel));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1, 9), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Al));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1,10), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(ANY.W));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1,11), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.TungstenSteel));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1,12), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Os));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1,13), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Pt));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1,14), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Nq));
		CR.shaped(ST.make(BlocksGT.LongDistWire01, 1,15), CR.DEF_REV_NCC, "RSR", "PWP", "RSR", 'R', OP.plate.dat(MT.Rubber), 'P', OP.plateCurved.dat(ANY.Cu), 'S', OP.plateCurved.dat(MT.Al), 'W', OP.wireGt16.dat(MT.Graphene));
		
		VISUALLY_OPAQUE_BLOCKS.add(BlocksGT.LongDistPipe01                      = new BlockLongDistPipe         ("gt.block.longdistpipe.01", Textures.BlockIcons.LONG_DIST_PIPES_01, new long[] {-1, MT.StainlessSteel.mMeltingPoint, MT.W.mMeltingPoint, MT.Ad.mMeltingPoint, MT.Draconium.mMeltingPoint, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
		
		CR.shaped(ST.make(BlocksGT.LongDistPipe01, 1, 0), CR.DEF_REV_NCC, "SPS", "PwP", "SPS", 'P', OP.pipeMedium.dat(MT.Electrum       ), 'S', OP.plate.dat(MT.Plastic));
		CR.shaped(ST.make(BlocksGT.LongDistPipe01, 1, 1), CR.DEF_REV_NCC, "SPS", "PwP", "SPS", 'P', OP.pipeMedium.dat(MT.StainlessSteel ), 'S', OP.plate.dat(MT.Plastic));
		CR.shaped(ST.make(BlocksGT.LongDistPipe01, 1, 2), CR.DEF_REV_NCC, "SPS", "PwP", "SPS", 'P', OP.pipeMedium.dat(ANY.W             ), 'S', OP.plate.dat(MT.Plastic));
		CR.shaped(ST.make(BlocksGT.LongDistPipe01, 1, 3), CR.DEF_REV_NCC, "SPS", "PwP", "SPS", 'P', OP.pipeMedium.dat(MT.Ad             ), 'S', OP.plate.dat(MT.Plastic));
		CR.shaped(ST.make(BlocksGT.LongDistPipe01, 1, 4), CR.DEF_REV_NCC, "SPS", "PwP", "SPS", 'P', OP.pipeMedium.dat(MT.Draconium      ), 'S', OP.plate.dat(MT.Plastic));
		
		/*
		OM.reg(OP.blockGlass    .toString()                     , ST.make(BlocksGT.Glass, 1, W));
		for (byte i = 0; i < 16; i++) {
		OM.reg(OP.stainedGlass  .toString()+DYE_OREDICTS_POST[i], ST.make(BlocksGT.Glass, 1, i));
		OM.reg(OP.blockGlass    .toString()+DYE_OREDICTS_POST[i], ST.make(BlocksGT.Glass, 1, i));
		}*/
	}
}
