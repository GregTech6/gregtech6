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

import gregapi.block.metatype.BlockMetaType;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.FluidsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
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

public class Loader_Recipes_Other implements Runnable {
	@Override public void run() {
		dust.addListener(new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {if (aEvent.mMaterial.mReRegistrations.contains(ANY.Wax) && !ST.isGT_(aEvent.mStack)) CR.remove(aEvent.mStack);}});
		
		for (OreDictMaterial tWax : ANY.Wax.mToThis) {
		RM.Compressor.addRecipe1(T, 16, 16, OM.dust(tWax, U ), plate.mat(tWax, 1));
		RM.Compressor.addRecipe1(T, 16, 16, OM.dust(tWax, U4), foil .mat(tWax, 1));
		RM.ic2_compressor(OM.dust(tWax, U ), plate.mat(tWax, 1));
		RM.ic2_compressor(OM.dust(tWax, U4), foil .mat(tWax, 1));
		}
		
		RM.Mixer.addRecipe2(T, 16, 64, dust     .mat(MT.Cu   , 3), dust     .mat(MT.Sn, 1), IL.Compound_Bronze       .get(36));
		RM.Mixer.addRecipe2(T, 16, 64, dust     .mat(MT.Cu   , 3), dust     .mat(MT.Zn, 1), IL.Compound_Brass        .get(36));
		RM.Mixer.addRecipe2(T, 16, 80, dust     .mat(MT.Brass, 4), dust     .mat(MT.Bi, 1), IL.Compound_BismuthBronze.get(45));
		RM.Mixer.addRecipe2(T, 16, 80, IL.Compound_Brass .get(36), dust     .mat(MT.Bi, 1), IL.Compound_BismuthBronze.get(45));
		RM.Mixer.addRecipe2(T, 16, 16, dustSmall.mat(MT.Cu   , 3), dustSmall.mat(MT.Sn, 1), IL.Compound_Bronze       .get( 9));
		RM.Mixer.addRecipe2(T, 16, 16, dustSmall.mat(MT.Cu   , 3), dustSmall.mat(MT.Zn, 1), IL.Compound_Brass        .get( 9));
		RM.Mixer.addRecipe2(T, 16, 80, dustSmall.mat(MT.Brass,16), dustSmall.mat(MT.Bi, 4), IL.Compound_BismuthBronze.get(45));
		RM.Mixer.addRecipe2(T, 16, 80, IL.Compound_Brass .get(36), dustSmall.mat(MT.Bi, 4), IL.Compound_BismuthBronze.get(45));
		for (OreDictPrefix tPrefixA : new OreDictPrefix[] {dustTiny, nugget, scrapGt}) {
		RM.Mixer.addRecipe2(T, 16, 64, tPrefixA .mat(MT.Cu   ,27), dust     .mat(MT.Sn, 1), IL.Compound_Bronze       .get(36));
		RM.Mixer.addRecipe2(T, 16, 64, tPrefixA .mat(MT.Cu   ,27), dust     .mat(MT.Zn, 1), IL.Compound_Brass        .get(36));
		RM.Mixer.addRecipe2(T, 16, 80, tPrefixA .mat(MT.Brass,36), dust     .mat(MT.Bi, 1), IL.Compound_BismuthBronze.get(45));
		RM.Mixer.addRecipe2(T, 16, 64, tPrefixA .mat(MT.Cu   ,27), dustSmall.mat(MT.Sn, 4), IL.Compound_Bronze       .get(36));
		RM.Mixer.addRecipe2(T, 16, 64, tPrefixA .mat(MT.Cu   ,27), dustSmall.mat(MT.Zn, 4), IL.Compound_Brass        .get(36));
		RM.Mixer.addRecipe2(T, 16, 80, tPrefixA .mat(MT.Brass,36), dustSmall.mat(MT.Bi, 4), IL.Compound_BismuthBronze.get(45));
		RM.Mixer.addRecipe2(T, 16, 22, dust     .mat(MT.Cu   , 1), tPrefixA .mat(MT.Sn, 3), IL.Compound_Bronze       .get(12));
		RM.Mixer.addRecipe2(T, 16, 22, dust     .mat(MT.Cu   , 1), tPrefixA .mat(MT.Zn, 3), IL.Compound_Brass        .get(12));
		RM.Mixer.addRecipe2(T, 16, 80, dust     .mat(MT.Brass, 4), tPrefixA .mat(MT.Bi, 9), IL.Compound_BismuthBronze.get(45));
		RM.Mixer.addRecipe2(T, 16, 22, dustSmall.mat(MT.Cu   , 4), tPrefixA .mat(MT.Sn, 3), IL.Compound_Bronze       .get(12));
		RM.Mixer.addRecipe2(T, 16, 22, dustSmall.mat(MT.Cu   , 4), tPrefixA .mat(MT.Zn, 3), IL.Compound_Brass        .get(12));
		RM.Mixer.addRecipe2(T, 16, 80, dustSmall.mat(MT.Brass,16), tPrefixA .mat(MT.Bi, 9), IL.Compound_BismuthBronze.get(45));
		RM.Mixer.addRecipe2(T, 16, 10, IL.Compound_Brass .get( 4), tPrefixA .mat(MT.Bi, 1), IL.Compound_BismuthBronze.get( 5));
		for (OreDictPrefix tPrefixB : new OreDictPrefix[] {dustTiny, nugget, scrapGt}) {
		RM.Mixer.addRecipe2(T, 16,  8, tPrefixA .mat(MT.Cu   , 3), tPrefixB .mat(MT.Sn, 1), IL.Compound_Bronze       .get( 4));
		RM.Mixer.addRecipe2(T, 16,  8, tPrefixA .mat(MT.Cu   , 3), tPrefixB .mat(MT.Zn, 1), IL.Compound_Brass        .get( 4));
		RM.Mixer.addRecipe2(T, 16, 10, tPrefixA .mat(MT.Brass, 4), tPrefixB .mat(MT.Bi, 1), IL.Compound_BismuthBronze.get( 5));
		}
		}
		
		RM.Mixer.addRecipe2(T, 16, 64, dust     .mat(MT.Cu   , 1), dust     .mat(MT.Redstone, 1), dust     .mat(MT.Mingrade, 2));
		RM.Mixer.addRecipe2(T, 16, 16, dustSmall.mat(MT.Cu   , 1), dustSmall.mat(MT.Redstone, 1), dustSmall.mat(MT.Mingrade, 2));
		RM.Mixer.addRecipe2(T, 16,  8, dustTiny .mat(MT.Cu   , 1), dustTiny .mat(MT.Redstone, 1), dustTiny .mat(MT.Mingrade, 2));
		
		for (OreDictMaterial tSapphire : ANY.Sapphire.mToThis) {
		RM.Mixer.addRecipe2(T, 16,  144, OM.dust(tSapphire, 4*U ), OM.dust(MT.Redstone, 5*U ), OM.dust(MT.EnergiumRed , 9*U));
		RM.Mixer.addRecipe2(T, 16,  144, OM.dust(tSapphire, 4*U9), OM.dust(MT.Redstone, 5*U9), OM.dust(MT.EnergiumRed ,   U));
		RM.Mixer.addRecipe2(T, 16,  144, OM.dust(tSapphire, 4*U ), OM.dust(MT.Nikolite, 5*U ), OM.dust(MT.EnergiumCyan, 9*U));
		RM.Mixer.addRecipe2(T, 16,  144, OM.dust(tSapphire, 4*U9), OM.dust(MT.Nikolite, 5*U9), OM.dust(MT.EnergiumCyan,   U));
		}
		
		
		for (OreDictMaterial tMat : ANY.Diamond.mToThis) {
		RM.Mixer   .addRecipe2(T, 16, 256, OP.dust.mat(MT.Obsidian, 1), OP.dust.mat(tMat, 1), OP.dust.mat(MT.RefinedObsidian, 1));
		RM.Injector.addRecipe2(T, 16, 256, OP.dust.mat(MT.Obsidian, 1), OP.dust.mat(tMat, 1), MT.Ge.liquid(U, T), NF, OP.ingot.mat(MT.RefinedObsidian, 1));
		}
		for (OreDictMaterial tMat : ANY.Glowstone.mToThis)
		RM.Injector.addRecipe1(T, 16, 256, OP.dust.mat(tMat              , 1), MT.Ge.liquid(U, T), NF, OP.ingot.mat(MT.RefinedGlowstone, 1));
		RM.Injector.addRecipe1(T, 16, 256, OP.dust.mat(MT.RefinedObsidian, 1), MT.Ge.liquid(U, T), NF, OP.ingot.mat(MT.RefinedObsidian, 1));
		
		RM.Compressor.addRecipe1(T, 16, 256, OP.dust.mat(MT.RefinedObsidian, 1), OP.plate.mat(MT.RefinedObsidian, 1));
		RM.ic2_compressor(OP.dust.mat(MT.RefinedObsidian, 1), OP.plate.mat(MT.RefinedObsidian, 1));
		
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		
		for (byte i = 0; i < 16; i++) {final byte aIndex = i;
		addListener(DYE_OREDICTS_MIXABLE[aIndex], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (ST.container(aEvent.mStack, T) == null) {
				for (FluidStack tWater : FL.waters(L))
				RM.Mixer.addRecipe1(T, 16, 16, aEvent.mStack, tWater, FL.mul(DYE_FLUIDS_WATER[aIndex], 3, 2, F), ZL_IS);
				
				ItemStack tStack = dust.mat(MT.DATA.Dye_Materials[aIndex], 1);
				if (!ST.equal(tStack, aEvent.mStack, T)) RM.generify(aEvent.mStack, tStack);
			}
		}});
		}
		
		addListener(DYE_OREDICTS_LENS[DYE_INDEX_Green], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			for (OreDictMaterial tMat : ANY.Diamond.mToThis)
			RM.LaserEngraver    .addRecipe2(T,256,   64, plateGem.mat(tMat   , 1), ST.amount(0, aEvent.mStack), IL.Circuit_Crystal_Diamond.get(1));
			for (OreDictMaterial tMat : ANY.Emerald.mToThis)
			RM.LaserEngraver    .addRecipe2(T,256,   64, plateGem.mat(tMat   , 1), ST.amount(0, aEvent.mStack), IL.Circuit_Crystal_Emerald.get(1));
			for (OreDictMaterial tMat : ANY.Sapphire.mToThis) if (tMat != MT.Ruby)
			RM.LaserEngraver    .addRecipe2(T,256,   64, plateGem.mat(tMat   , 1), ST.amount(0, aEvent.mStack), IL.Circuit_Crystal_Sapphire.get(1));
			RM.LaserEngraver    .addRecipe2(T,256,   64, plateGem.mat(MT.Ruby, 1), ST.amount(0, aEvent.mStack), IL.Circuit_Crystal_Ruby.get(1));
		}});
		addListener(DYE_OREDICTS_LENS[DYE_INDEX_Red], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			for (OreDictMaterial tMat : ANY.Cu.mToThis)
			RM.LaserEngraver    .addRecipe2(T, 16,   64, foil.mat(tMat                  , 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Copper.get(1));
			RM.LaserEngraver    .addRecipe2(T, 16,   64, foil.mat(MT.Au                 , 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Gold.get(1));
			RM.LaserEngraver    .addRecipe2(T, 16,   64, foil.mat(MT.Pt                 , 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Platinum.get(1));
			RM.LaserEngraver    .addRecipe2(T, 16,   64, foil.mat(MT.Thaumium           , 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Magic.get(1));
			RM.LaserEngraver    .addRecipe2(T, 16,   64, foil.mat(MT.Manasteel          , 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Magic.get(1));
			RM.LaserEngraver    .addRecipe2(T, 16,   64, foil.mat(MT.Mithril            , 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Magic.get(1));
			RM.LaserEngraver    .addRecipe2(T, 16,   64, foil.mat(MT.Enderium           , 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Enderium.get(1));
			RM.LaserEngraver    .addRecipe2(T, 16,   64, foil.mat(MT.Signalum           , 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Signalum.get(1));
		}});
		
		
		addListener(dust.dat(MT.HydratedCoal), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.dust(MT.Coal), F, F, F);}});
		addListener(rockGt.dat(MT.Netherrack), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, rockGt.mat(MT.NetherBrick, 1), F, F, T);}});
		
		
		addListener(new Object[] {OD.itemRubber, "crushedDraconium", "gemHexorium", "ingotIron", "oreCoal", "oreIron", "oreHematite", "oreMagnetite", "oreBogIron", "oreBrownLimonite", "oreYellowLimonite", "oreSiderite", "oreTaconite", "oreGold", "oreRedstone", "oreLapis", "oreDiamond", "oreEmerald", "oreBauxite", "oreAluminium", "oreRutile", "oreTitanium", "oreTungsten", "oreSulfur", "oreSaltpeter", "oreNiter", "oreNitre"}, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.rem_smelting(aEvent.mStack);
		}});
		addListener(OD.itemResin, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.biomass(ST.amount(6, aEvent.mStack), 32);
			RM.rem_smelting(aEvent.mStack);
			RM.ic2_extractor(aEvent.mStack, OM.ingot(MT.Rubber, 7*U9));
			RM.Juicer           .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Latex.make(L/2), NI);
			RM.Squeezer         .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Latex.make(L  ), NI);
			RM.Centrifuge       .addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Latex.make(L  ), FL.Glue.make(250));
			for (FluidStack tWater : FL.waters(250))
			RM.Mixer            .addRecipe1(T, 16,   16, aEvent.mStack, tWater, FL.Glue.make(250), ZL_IS);
			RM.Laminator        .addRecipe2(T, 16,   16, aEvent.mStack, ST.make(Blocks.piston, 1, W), ST.make(Blocks.sticky_piston, 1, 0));
		}});
		addListener(OD.itemClay, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(aEvent.mStack, OM.dust(MT.Redstone), OM.dust(MT.Basalz, U9), OM.dust(MT.Obsidian)), OM.dust(MT.Petrotheum, 2*U));
		}});
		addListener(OD.blockClay, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer            .addRecipeX(T, 16,   64, ST.array(aEvent.mStack, OM.dust(MT.Redstone,U*4), OM.dust(MT.Basalz, U9*4), OM.dust(MT.Obsidian, U*4)), OM.dust(MT.Petrotheum, 8*U));
		}});
		addListener(OD.itemGrassDry, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Loom             .addRecipe2(T, 16,   16, ST.tag(10), ST.amount(7, aEvent.mStack), IL.Rope_Grass.get(1));
		}});
		addListener(dustTiny.dat(ANY.Wood), dustTiny.dat(MT.Bark), dustTiny.dat(MT.Paper), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer            .addRecipe1(T, 16,   32,              aEvent.mStack , MT.Glyceryl.fluid(U9,T), NF, OM.dust(MT.Dynamite,2*U9));
			for (FluidStack tWater : FL.waters(125, 100))
			RM.Bath             .addRecipe1(T,  0,   16, ST.amount(9, aEvent.mStack), tWater, NF, ST.make(Items.paper, 1, 0));
		}});
		addListener(dustSmall.dat(ANY.Wood), dustSmall.dat(MT.Bark), dustSmall.dat(MT.Paper), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer            .addRecipe1(T, 16,   32,              aEvent.mStack , MT.Glyceryl.fluid(U4,T), NF, OM.dust(MT.Dynamite,  U2));
			for (FluidStack tWater : FL.waters(125, 100))
			RM.Bath             .addRecipe1(T,  0,   16, ST.amount(4, aEvent.mStack), tWater, NF, ST.make(Items.paper, 1, 0));
		}});
		addListener(dust.dat(ANY.Wood), dust.dat(MT.Bark), dust.dat(MT.Paper), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer            .addRecipe1(T, 16,   32,              aEvent.mStack , MT.Glyceryl.fluid(U ,T), NF, OM.dust(MT.Dynamite,2*U ));
			for (FluidStack tWater : FL.waters(125, 100))
			RM.Bath             .addRecipe1(T,  0,   16,              aEvent.mStack , tWater, NF, ST.make(Items.paper, 1, 0));
		}});
		addListener(stick.dat(ANY.WoodNormal), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Loom         .addRecipe2(T, 16,   16, ST.make(Blocks.wool  , 1, W), ST.amount(8, aEvent.mStack), ST.make(Items.painting, 1, 0));
			RM.Loom         .addRecipe2(T, 16,   16, ST.make(Items.leather, 1, W), ST.amount(8, aEvent.mStack), ST.make(Items.item_frame, 1, 0));
		}});
		}};
		
		
		//----------------------------------------------------------------------------
		
		if (FL.Turpentine.exists()) {
			if (FL.Resin       .exists()) RM.Distillery.addRecipe1(T, 16, 16, ST.tag(0), FL.Resin       .make(10), FL.Turpentine.make(6), FL.DistW.make(3));
			if (FL.Resin_Spruce.exists()) RM.Distillery.addRecipe1(T, 16, 16, ST.tag(0), FL.Resin_Spruce.make(10), FL.Turpentine.make(6), FL.DistW.make(3));
		}
		
		for (FluidStack tWater : FL.waters(1000)) {
		RM.Bath         .addRecipe1(T,  0,   16, OM.dust(MT.Coal), FL.mul(tWater, 1, 8, T), NF, OM.dust(MT.HydratedCoal));
		
		RM.CryoMixer    .addRecipeX(T, 16,   32, ST.array(OM.dust(MT.Redstone    ), OM.dust(MT.Blizz, U9  ), OM.dust(MT.NaNO3     )), FL.mul(tWater, 1, 4, T), NF, OM.dust(MT.Cryotheum, 2*U));
		RM.CryoMixer    .addRecipeX(T, 16,  128, ST.array(OM.dust(MT.Redstone,U*4), OM.dust(MT.Blizz, U9*4), OM.dust(MT.NaNO3, U*4)),        tWater          , NF, OM.dust(MT.Cryotheum, 8*U));
		RM.CryoMixer    .addRecipeX(T, 16,   32, ST.array(OM.dust(MT.Redstone    ), OM.dust(MT.Blizz, U9  ), OM.dust(MT.KNO3      )), FL.mul(tWater, 1, 4, T), NF, OM.dust(MT.Cryotheum, 2*U));
		RM.CryoMixer    .addRecipeX(T, 16,  128, ST.array(OM.dust(MT.Redstone,U*4), OM.dust(MT.Blizz, U9*4), OM.dust(MT.KNO3 , U*4)),        tWater          , NF, OM.dust(MT.Cryotheum, 8*U));
		RM.CryoMixer    .addRecipeX(T, 16,   32, ST.array(OM.dust(MT.Redstone    ), OM.dust(MT.Blizz, U9  ), OM.dust(MT.Niter     )), FL.mul(tWater, 1, 4, T), NF, OM.dust(MT.Cryotheum, 2*U));
		RM.CryoMixer    .addRecipeX(T, 16,  128, ST.array(OM.dust(MT.Redstone,U*4), OM.dust(MT.Blizz, U9*4), OM.dust(MT.Niter, U*4)),        tWater          , NF, OM.dust(MT.Cryotheum, 8*U));
		
		RM.Mixer        .addRecipeX(T, 16,   64, ST.array(gem .mat(MT.ChargedCertusQuartz, 1), gem .mat(MT.VoidQuartz  , 1)                          ), FL.mul(tWater, 1, 2, T), NF, gem .mat(MT.Fluix, 2));
		RM.Mixer        .addRecipeX(T, 16,   64, ST.array(dust.mat(MT.ChargedCertusQuartz, 1), dust.mat(MT.VoidQuartz  , 1)                          ), FL.mul(tWater, 1, 2, T), NF, dust.mat(MT.Fluix, 2));
		RM.Mixer        .addRecipeX(T, 16,   64, ST.array(gem .mat(MT.ChargedCertusQuartz, 1), gem .mat(MT.NetherQuartz, 1), dust.mat(MT.Redstone, 1)), FL.mul(tWater, 1, 2, T), NF, gem .mat(MT.Fluix, 2));
		RM.Mixer        .addRecipeX(T, 16,   64, ST.array(dust.mat(MT.ChargedCertusQuartz, 1), dust.mat(MT.NetherQuartz, 1), dust.mat(MT.Redstone, 1)), FL.mul(tWater, 1, 2, T), NF, dust.mat(MT.Fluix, 2));
		RM.Mixer        .addRecipeX(T, 16,   64, ST.array(gem .mat(MT.ChargedCertusQuartz, 1), gem .mat(MT.MilkyQuartz , 1), dust.mat(MT.Redstone, 1)), FL.mul(tWater, 1, 2, T), NF, gem .mat(MT.Fluix, 2));
		RM.Mixer        .addRecipeX(T, 16,   64, ST.array(dust.mat(MT.ChargedCertusQuartz, 1), dust.mat(MT.MilkyQuartz , 1), dust.mat(MT.Redstone, 1)), FL.mul(tWater, 1, 2, T), NF, dust.mat(MT.Fluix, 2));
		
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.ConstructionFoam), FL.mul(tWater, 1,10, T), FL.CFoam.make(100), ZL_IS);
		
		for (OreDictMaterial tClay : ANY.Clay.mToThis) for (OreDictMaterial tSand : ANY.SiO2.mToThis) {
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Stone, MT.Concrete, MT.Talc, MT.STONES.Rhyolite, MT.STONES.Gneiss, MT.STONES.Shale, MT.Oilshale, MT.Dolomite, MT.STONES.Chert, MT.Asbestos}) {
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U* 6), OM.dust(tSand, U * 2), OM.dust(tClay, U4)), FL.mul(tWater, 1), FL.CFoam.make(1000), ZL_IS);
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U*24), OM.dust(tSand, U * 8), OM.dust(tClay, U )), FL.mul(tWater, 4), FL.CFoam.make(4000), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Diorite, MT.STONES.Marble, MT.Chalk, MT.CaCO3, MT.Endstone, MT.STONES.Livingrock, MT.STONES.Holystone}) {
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U* 6), OM.dust(tSand, U * 2), OM.dust(tClay, U4)), FL.mul(tWater, 1), FL.mul(DYED_C_FOAMS[DYE_INDEX_White], 10), ZL_IS);
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U*24), OM.dust(tSand, U * 8), OM.dust(tClay, U )), FL.mul(tWater, 4), FL.mul(DYED_C_FOAMS[DYE_INDEX_White], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Basalt, MT.STONES.Gabbro, MT.STONES.GraniteBlack, MT.STONES.Deepslate}) {
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U* 6), OM.dust(tSand, U * 2), OM.dust(tClay, U4)), FL.mul(tWater, 1), FL.mul(DYED_C_FOAMS[DYE_INDEX_Black], 10), ZL_IS);
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U*24), OM.dust(tSand, U * 8), OM.dust(tClay, U )), FL.mul(tWater, 4), FL.mul(DYED_C_FOAMS[DYE_INDEX_Black], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Migmatite, MT.STONES.Eclogite, MT.STONES.SpaceRock, MT.STONES.Slate}) {
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U* 6), OM.dust(tSand, U * 2), OM.dust(tClay, U4)), FL.mul(tWater, 1), FL.mul(DYED_C_FOAMS[DYE_INDEX_Gray], 10), ZL_IS);
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U*24), OM.dust(tSand, U * 8), OM.dust(tClay, U )), FL.mul(tWater, 4), FL.mul(DYED_C_FOAMS[DYE_INDEX_Gray], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Andesite, MT.STONES.Dacite, MT.STONES.Deadrock, MT.STONES.Greywacke, MT.STONES.MoonRock, MT.STONES.MoonTurf}) {
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U* 6), OM.dust(tSand, U * 2), OM.dust(tClay, U4)), FL.mul(tWater, 1), FL.mul(DYED_C_FOAMS[DYE_INDEX_LightGray], 10), ZL_IS);
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U*24), OM.dust(tSand, U * 8), OM.dust(tClay, U )), FL.mul(tWater, 4), FL.mul(DYED_C_FOAMS[DYE_INDEX_LightGray], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Blueschist}) {
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U* 6), OM.dust(tSand, U * 2), OM.dust(tClay, U4)), FL.mul(tWater, 1), FL.mul(DYED_C_FOAMS[DYE_INDEX_LightBlue], 10), ZL_IS);
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U*24), OM.dust(tSand, U * 8), OM.dust(tClay, U )), FL.mul(tWater, 4), FL.mul(DYED_C_FOAMS[DYE_INDEX_LightBlue], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Greenschist, MT.STONES.Betweenstone, MT.PrismarineLight}) {
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U* 6), OM.dust(tSand, U * 2), OM.dust(tClay, U4)), FL.mul(tWater, 1), FL.mul(DYED_C_FOAMS[DYE_INDEX_Lime], 10), ZL_IS);
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U*24), OM.dust(tSand, U * 8), OM.dust(tClay, U )), FL.mul(tWater, 4), FL.mul(DYED_C_FOAMS[DYE_INDEX_Lime], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Pitstone, MT.PrismarineDark}) {
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U* 6), OM.dust(tSand, U * 2), OM.dust(tClay, U4)), FL.mul(tWater, 1), FL.mul(DYED_C_FOAMS[DYE_INDEX_Green], 10), ZL_IS);
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U*24), OM.dust(tSand, U * 8), OM.dust(tClay, U )), FL.mul(tWater, 4), FL.mul(DYED_C_FOAMS[DYE_INDEX_Green], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Redrock, MT.STONES.MarsRock, MT.STONES.MarsSand, MT.Netherrack, MT.STONES.GraniteRed, MT.STONES.Granite}) {
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U* 6), OM.dust(tSand, U * 2), OM.dust(tClay, U4)), FL.mul(tWater, 1), FL.mul(DYED_C_FOAMS[DYE_INDEX_Red], 10), ZL_IS);
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U*24), OM.dust(tSand, U * 8), OM.dust(tClay, U )), FL.mul(tWater, 4), FL.mul(DYED_C_FOAMS[DYE_INDEX_Red], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Komatiite}) {
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U* 6), OM.dust(tSand, U * 2), OM.dust(tClay, U4)), FL.mul(tWater, 1), FL.mul(DYED_C_FOAMS[DYE_INDEX_Yellow], 10), ZL_IS);
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U*24), OM.dust(tSand, U * 8), OM.dust(tClay, U )), FL.mul(tWater, 4), FL.mul(DYED_C_FOAMS[DYE_INDEX_Yellow], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Limestone}) {
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U* 6), OM.dust(tSand, U * 2), OM.dust(tClay, U4)), FL.mul(tWater, 1), FL.mul(DYED_C_FOAMS[DYE_INDEX_Orange], 10), ZL_IS);
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U*24), OM.dust(tSand, U * 8), OM.dust(tClay, U )), FL.mul(tWater, 4), FL.mul(DYED_C_FOAMS[DYE_INDEX_Orange], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Quartzite, MT.STONES.Siltstone}) {
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U* 6), OM.dust(tSand, U * 2), OM.dust(tClay, U4)), FL.mul(tWater, 1), FL.mul(DYED_C_FOAMS[DYE_INDEX_Pink], 10), ZL_IS);
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U*24), OM.dust(tSand, U * 8), OM.dust(tClay, U )), FL.mul(tWater, 4), FL.mul(DYED_C_FOAMS[DYE_INDEX_Pink], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Umber, MT.STONES.Kimberlite}) {
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U* 6), OM.dust(tSand, U * 2), OM.dust(tClay, U4)), FL.mul(tWater, 1), FL.mul(DYED_C_FOAMS[DYE_INDEX_Brown], 10), ZL_IS);
		RM.Mixer.addRecipeX(T,F,F,F,T, 16,  256, ST.array(OM.dust(tRock, U*24), OM.dust(tSand, U * 8), OM.dust(tClay, U )), FL.mul(tWater, 4), FL.mul(DYED_C_FOAMS[DYE_INDEX_Brown], 40), ZL_IS);
		}
		}
		
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(MT.Indigo, U9), FL.mul(tWater,   L  , 1000, T), FL.make("indigo",   L  ), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   36, OM.dust(MT.Indigo, U4), FL.mul(tWater, 9*L/4, 1000, T), FL.make("indigo", 9*L/4), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,  144, OM.dust(MT.Indigo    ), FL.mul(tWater, 9*L  , 1000, T), FL.make("indigo", 9*L  ), ZL_IS);
		
		// Concrete
		RM.Mixer            .addRecipe1(T, 16,  144, blockDust.mat(MT.Concrete, 1), FL.mul(tWater, 9, 2, T), FL.Concrete.make(9*L), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   16, dust     .mat(MT.Concrete, 1), FL.mul(tWater, 1, 2, T), FL.Concrete.make(  L), ZL_IS);
		}
		for (OreDictMaterial tRock : ANY.Stone.mToThis) for (OreDictMaterial tCalcite : ANY.Calcite.mToThis) if (tRock != MT.Concrete && !tRock.mReRegistrations.contains(ANY.Calcite)) for (OreDictMaterial tAsh : ANY.Ash.mToThis) {
		RM.Mixer            .addRecipeX(T, 16, 1296, ST.array(blockDust.mat(tRock, 9), blockDust.mat(tCalcite, 1), blockDust.mat(tAsh, 1)), blockDust.mat(MT.Concrete,11));
		RM.Mixer            .addRecipeX(T, 16, 1296, ST.array(blockDust.mat(tRock, 9), blockDust.mat(tCalcite, 1), dust     .mat(tAsh, 9)), blockDust.mat(MT.Concrete,11));
		RM.Mixer            .addRecipeX(T, 16, 1296, ST.array(blockDust.mat(tRock, 9), dust     .mat(tCalcite, 9), blockDust.mat(tAsh, 1)), blockDust.mat(MT.Concrete,11));
		RM.Mixer            .addRecipeX(T, 16,  144, ST.array(blockDust.mat(tRock, 1), dust     .mat(tCalcite, 1), dust     .mat(tAsh, 1)), dust     .mat(MT.Concrete,11));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(dust     .mat(tRock, 9), dust     .mat(tCalcite, 1), dust     .mat(tAsh, 1)), dust     .mat(MT.Concrete,11));
		}
		for (OreDictMaterial tIron : ANY.Iron.mToThis)
		RM.Drying           .addRecipe1(T, 16,   16, stick.mat(tIron, 1) , FL.Concrete.make(L), FL.DistW.make(8), ST.make(BlocksGT.ConcreteReinforced, 1, DYE_INDEX_LightGray));
		RM.Drying           .addRecipe1(T, 16,   16, ST.tag(0)           , FL.Concrete.make(L), FL.DistW.make(8), ST.make(BlocksGT.Concrete          , 1, DYE_INDEX_LightGray));
		
		// Asphalt
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Stone, MT.Concrete, MT.Talc, MT.STONES.Rhyolite, MT.STONES.Gneiss, MT.STONES.Shale, MT.Oilshale, MT.Dolomite, MT.STONES.Chert})
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Gray));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Diorite, MT.STONES.Marble, MT.Chalk, MT.CaCO3, MT.Endstone, MT.STONES.Livingrock, MT.STONES.Holystone})
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_White));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Basalt, MT.STONES.Gabbro, MT.STONES.GraniteBlack, MT.STONES.Deepslate})
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Black));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Migmatite, MT.STONES.Eclogite, MT.STONES.SpaceRock, MT.STONES.Slate})
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Gray));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Andesite, MT.STONES.Dacite, MT.STONES.Deadrock, MT.STONES.Greywacke, MT.STONES.MoonRock, MT.STONES.MoonTurf})
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_LightGray));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Blueschist})
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_LightBlue));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Greenschist, MT.STONES.Betweenstone, MT.PrismarineLight})
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Lime));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Pitstone, MT.PrismarineDark})
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Lime));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Redrock, MT.STONES.MarsRock, MT.STONES.MarsSand, MT.Netherrack, MT.STONES.GraniteRed, MT.STONES.Granite})
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Red));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Komatiite})
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Yellow));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Limestone})
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Orange));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Quartzite, MT.STONES.Siltstone})
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Pink));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.STONES.Umber, MT.STONES.Kimberlite})
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Brown));
		
		ItemStack x, y;
		CR.remove(x = dust.mat(MT.S, 1), x, x, x, ST.make(Items.coal, 1, 0), x, x, x, x);
		CR.remove(x, x, x, x, ST.make(Items.coal, 1, 1), x, x, x, x);
		CR.remove(y = dust.mat(MT.KNO3, 1), y, x, dust.mat(MT.Charcoal, 1));
		CR.remove(y, x, y, y, ST.make(Items.coal, 1, 1), y, y, y, y);
		CR.remove(y, y, y, y, ST.make(Items.coal, 1, 1), y, y, y, y);
		CR.remove(y, y, x, dust.mat(MT.Coal, 1));
		CR.remove(ST.make(Items.quartz, 1, 0), ST.make(Items.coal, 1, 0));
		CR.remove(ST.make(Items.quartz, 1, 0), ST.make(Items.coal, 1, 1));
		CR.remove(NI, x = ST.make(Items.coal, 1, 0), NI, x, y = ST.make(Items.iron_ingot, 1, 0), x, NI, x, NI);
		CR.remove(NI, NI, NI, NI, y, NI, NI, y, NI);
		CR.remove(NI, NI, NI, NI, y = ingot.mat(MT.Al, 1), NI, NI, y, NI);
		CR.remove(NI, NI, NI, NI, y = ingot.mat(MT.Steel, 1), NI, NI, y, NI);
		CR.remove(x, x, x, x, ingot.mat(MT.W, 1), x, x, x, x);
		CR.remove(x = dust.mat(MT.Cu, 1), x, x, y = dust.mat(MT.Sn, 1));
		CR.remove(x, y, x, y, x, y, x, y, x);
		CR.remove(x, x, x, dust.mat(MT.Zn, 1));
		CR.remove(x, y = dust.mat(MT.Ni, 1));
		CR.remove(x = dust.mat(MT.Fe, 1), x, y);
		CR.remove(dust.mat(MT.Cu         , 1), dust.mat(MT.Sn        , 1));
		CR.remove(dust.mat(MT.Cu         , 1), dust.mat(MT.Zn        , 1));
		CR.remove(dust.mat(MT.Cu         , 1), dust.mat(MT.Redstone  , 1));
		CR.remove(dust.mat(MT.Fe         , 1), dust.mat(MT.Mn        , 1));
		CR.remove(dust.mat(MT.Fe         , 1), dust.mat(MT.Bronze    , 1));
		CR.remove(dust.mat(MT.Au         , 1), dust.mat(MT.Bronze    , 1));
		CR.remove(dust.mat(MT.Au         , 1), dust.mat(MT.Ag        , 1));
		CR.remove(dust.mat(MT.Au         , 1), dust.mat(MT.Fe        , 1));
		CR.remove(dust.mat(MT.Infuscolium, 1), dust.mat(MT.DeepIron  , 1));
		CR.remove(dust.mat(MT.ShadowIron , 1), dust.mat(MT.Lemurite  , 1));
		CR.remove(dust.mat(MT.Alduorite  , 1), dust.mat(MT.Ceruclase , 1));
		CR.remove(dust.mat(MT.Mithril    , 1), dust.mat(MT.Rubracium , 1));
		CR.remove(dust.mat(MT.Pt         , 1), dust.mat(MT.Orichalcum, 1));
		CR.remove(dust.mat(MT.Eximite    , 1), dust.mat(MT.Meutoite  , 1));
		CR.remove(dust.mat(MT.Adamantine , 1), dust.mat(MT.Atl       , 1));
		CR.remove(dust.mat(MT.Prometheum , 1), dust.mat(MT.Kalendrite, 1));
		CR.remove(x = dust.mat(MT.Clay, 1), x, dust.mat(MT.PotassiumFeldspar, 1), dust.mat(MT.SiO2, 1));
		CR.remove(dust.mat(MT.Redstone, 1), dust.mat(MT.Nikolite, 1));
		
		//----------------------------------------------------------------------------
		
		CR.shaped(stick         .mat(MT.PetrifiedWood   ,  9), CR.DEF, "X" , "X" , 'X', rockGt.dat(MT.PetrifiedWood));
		CR.shaped(plate         .mat(MT.PetrifiedWood   ,  9), CR.DEF, "XX", "XX", 'X', rockGt.dat(MT.PetrifiedWood));
		CR.shaped(ST.make(Blocks.stone_slab          , 9,  2), CR.DEF, "XX"      , 'X', rockGt.dat(MT.PetrifiedWood));
		CR.shaped(plateTiny     .mat(MT.Paper           ,  9), CR.DEF, "b ", " X", 'X', plate.dat(MT.Paper));
		CR.shaped(plateTiny     .mat(MT.Paper           ,  9), CR.DEF, "q ", " X", 'X', plate.dat(MT.Paper));
		for (OreDictMaterial tWax : ANY.Wax.mToThis) {
		CR.shaped(ring          .mat(tWax               ,  1), CR.DEF, "k", "X", 'X', plate.dat(tWax));
		CR.shaped(casingSmall   .mat(tWax               ,  1), CR.DEF, "X", "k", 'X', plate.dat(tWax));
		}
		CR.shaped(ring          .mat(MT.Rubber          ,  1), CR.DEF, "k", "X", 'X', plate.dat(MT.Rubber));
		CR.shaped(casingSmall   .mat(MT.Rubber          ,  1), CR.DEF, "X", "k", 'X', plate.dat(MT.Rubber));
		CR.shaped(gearGt        .mat(MT.Stone           ,  1), CR.DEF, "SPS", "PfP", "SPS", 'P', stoneSmooth, 'S', OD.buttonStone);
		CR.shaped(gearGt        .mat(MT.Stone           ,  1), CR.DEF, "SPS", "PfP", "SPS", 'P', stoneSmooth, 'S', rockGt.dat(MT.Stone));
		CR.shaped(gearGt        .mat(MT.Stone           ,  1), CR.DEF, "SPS", "PfP", "SPS", 'P', stoneSmooth, 'S', stick.dat(MT.Stone));
		CR.shaped(gearGtSmall   .mat(MT.Stone           ,  1), CR.DEF, "P ", " f", 'P', stoneSmooth);
		
		for (OreDictMaterial tMat : OreDictMaterial.MATERIAL_MAP.values()) if (!tMat.containsAny(TD.Properties.INVALID_MATERIAL, TD.Compounds.COATED, TD.Properties.WOOD) && tMat.mTargetRegistration == tMat)
		CR.shaped(gearGtSmall.mat(tMat, 1), CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC, "P ", tMat.contains(TD.Properties.STONE)?" f":" h", 'P', plate.dat(tMat));
		
		
		RM.Slicer           .addRecipe2(T, 16,   16, ST.make(Items.paper, 1, W), IL.Shape_Slicer_Grid.get(0), plateTiny.mat(MT.Paper, 9));
		
		
		if (FL.Mana_TE.exists()) {
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Blocks.dirt                , 1, 1), FL.Mana_TE.make(1), NF, ST.make(Blocks.dirt, 1, 2));
		if (IL.EtFu_Dirt.exists())
		RM.Bath             .addRecipe1(T,  0,   16, IL.EtFu_Dirt                       .get(1), FL.Mana_TE.make(1), NF, ST.make(Blocks.dirt, 1, 2));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Blocks.dirt                , 1, 0), FL.Mana_TE.make(1), NF, ST.make(Blocks.grass, 1, 0));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Blocks.dirt                , 1, 2), FL.Mana_TE.make(1), NF, ST.make(Blocks.mycelium, 1, 0));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Blocks.glass               , 1, W), FL.Mana_TE.make(1), NF, ST.make(Blocks.sand, 1, 0));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Blocks.stained_glass       , 1, W), FL.Mana_TE.make(1), NF, ST.make(Blocks.sand, 1, 0));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Blocks.cobblestone         , 1, W), FL.Mana_TE.make(1), NF, ST.make(Blocks.mossy_cobblestone, 1, 0));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Blocks.stonebrick          , 1, 0), FL.Mana_TE.make(1), NF, ST.make(Blocks.stonebrick, 1, 1));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Blocks.stone               , 1, 0), FL.Mana_TE.make(1), NF, ST.make(Blocks.stonebrick, 1, 3));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Blocks.lapis_ore           , 1, W), FL.Mana_TE.make(5), NF, ST.make(Blocks.lapis_block, 1, 0));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Blocks.redstone_ore        , 1, W), FL.Mana_TE.make(5), NF, ST.make(Blocks.redstone_block, 1, 0));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(Blocks.lit_redstone_ore    , 1, W), FL.Mana_TE.make(5), NF, ST.make(Blocks.redstone_block, 1, 0));
		}
		
		RM.Bath             .addRecipe2(T,  0,   16, ST.make(Items.paper, 2, W), ST.tag(2), FL.Glue.make( 125), NF, plateDouble.mat(MT.Paper, 1));
		RM.Bath             .addRecipe2(T,  0,   32, ST.make(Items.paper, 3, W), ST.tag(3), FL.Glue.make( 250), NF, plateTriple.mat(MT.Paper, 1));
		RM.Bath             .addRecipe2(T,  0,   48, ST.make(Items.paper, 4, W), ST.tag(4), FL.Glue.make( 375), NF, plateQuadruple.mat(MT.Paper, 1));
		RM.Bath             .addRecipe2(T,  0,   64, ST.make(Items.paper, 5, W), ST.tag(5), FL.Glue.make( 500), NF, plateQuintuple.mat(MT.Paper, 1));
//      RM.Bath             .addRecipe2(T,  0,  128, ST.make(Items.paper, 9, W), ST.tag(9), FL.Glue.make(1000), NF, plateDense.mat(MT.Paper, 1));
		
		
		
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
		
		for (byte i = 0; i < 16; i++) for (FluidStack tDye : DYE_FLUIDS[i]) {
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(BlocksGT.CFoam                                             , 1, W), FL.mul(tDye, 1, 24, T), NF, ST.make(BlocksGT.CFoam                                          , 1, i));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(BlocksGT.Asphalt                                           , 1, W), FL.mul(tDye, 1, 24, T), NF, ST.make(BlocksGT.Asphalt                                        , 1, i));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(BlocksGT.Concrete                                          , 1, W), FL.mul(tDye, 1, 24, T), NF, ST.make(BlocksGT.Concrete                                       , 1, i));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(BlocksGT.ConcreteReinforced                                , 1, W), FL.mul(tDye, 1, 24, T), NF, ST.make(BlocksGT.ConcreteReinforced                             , 1, i));
		
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(((BlockMetaType)BlocksGT.CFoam                 ).mSlabs[0] , 1, W), FL.mul(tDye, 1, 48, T), NF, ST.make(((BlockMetaType)BlocksGT.CFoam              ).mSlabs[0] , 1, i));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(((BlockMetaType)BlocksGT.Asphalt               ).mSlabs[0] , 1, W), FL.mul(tDye, 1, 48, T), NF, ST.make(((BlockMetaType)BlocksGT.Asphalt            ).mSlabs[0] , 1, i));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(((BlockMetaType)BlocksGT.Concrete              ).mSlabs[0] , 1, W), FL.mul(tDye, 1, 48, T), NF, ST.make(((BlockMetaType)BlocksGT.Concrete           ).mSlabs[0] , 1, i));
		RM.Bath             .addRecipe1(T,  0,   16, ST.make(((BlockMetaType)BlocksGT.ConcreteReinforced    ).mSlabs[0] , 1, W), FL.mul(tDye, 1, 48, T), NF, ST.make(((BlockMetaType)BlocksGT.ConcreteReinforced ).mSlabs[0] , 1, i));
		
		if (tRegistry != null) for (byte j = 0; j < 16; j++) {
		RM.Bath             .addRecipe1(F,  0,   16, tRegistry.getItem(32452+j), FL.mul(tDye, 1,144, T), NF, tRegistry.getItem(32452+i));
		RM.Bath             .addRecipe1(F,  0,   16, tRegistry.getItem(32468+j), FL.mul(tDye, 1,144, T), NF, tRegistry.getItem(32468+i));
		RM.Bath             .addRecipe1(F,  0,   16, tRegistry.getItem(32484+j), FL.mul(tDye, 1,144, T), NF, tRegistry.getItem(32484+i));
		}
		}
		
		for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Green    ]) RM.Bath.addRecipe1(T, 0, 16, ST.make(Blocks.grass, 1, W), FL.mul(tDye, 1, 24, T), NF, ST.make(BlocksGT.Grass, 1, 0));
		for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Lime     ]) RM.Bath.addRecipe1(T, 0, 16, ST.make(Blocks.grass, 1, W), FL.mul(tDye, 1, 24, T), NF, ST.make(BlocksGT.Grass, 1, 1));
		for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Black    ]) RM.Bath.addRecipe1(T, 0, 16, ST.make(Blocks.grass, 1, W), FL.mul(tDye, 1, 24, T), NF, ST.make(BlocksGT.Grass, 1, 2));
		for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_LightGray]) RM.Bath.addRecipe1(T, 0, 16, ST.make(Blocks.grass, 1, W), FL.mul(tDye, 1, 24, T), NF, ST.make(BlocksGT.Grass, 1, 3));
		
		
		for (byte i = 0; i < 16; i++) {
		for (FluidStack tDye : DYE_FLUIDS[i]) if (tDye.getFluid() != DYE_FLUIDS_CHEMICAL[i].getFluid()) {
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.mul(tDye, 3, 2, T), MT.SunflowerOil    .liquid(U50, T)), FL.mul(DYE_FLUIDS_CHEMICAL[i], 2), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.mul(tDye, 3, 2, T), MT.OliveOil        .liquid(U50, T)), FL.mul(DYE_FLUIDS_CHEMICAL[i], 2), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.mul(tDye, 3, 2, T), MT.NutOil          .liquid(U50, T)), FL.mul(DYE_FLUIDS_CHEMICAL[i], 2), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.mul(tDye, 3, 2, T), MT.SeedOil         .liquid(U50, T)), FL.mul(DYE_FLUIDS_CHEMICAL[i], 2), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.mul(tDye, 3, 2, T), MT.LinOil          .liquid(U50, T)), FL.mul(DYE_FLUIDS_CHEMICAL[i], 2), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.mul(tDye, 3, 2, T), MT.HempOil         .liquid(U50, T)), FL.mul(DYE_FLUIDS_CHEMICAL[i], 2), ZL_IS);
		}
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.mul(DYE_FLUIDS_CHEMICAL[i], 1, 9, T), FL.CFoam.make(100)), DYED_C_FOAMS[i], ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(MT.Pd, U4), DYED_C_FOAMS[i], DYED_C_FOAMS_OWNED[i], ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   64, OM.dust(MT.Pd), FL.mul(DYED_C_FOAMS[i], 4), FL.mul(DYED_C_FOAMS_OWNED[i], 4), ZL_IS);
		// TODO: Foamer
		for (String tFluid : FluidsGT.AIR) if (FL.exists(tFluid))
		RM.Injector         .addRecipe0(T, 16,   16, FL.array(DYED_C_FOAMS[i], FL.make(tFluid, 1000)), ZL_FS, ST.make(BlocksGT.CFoamFresh, 1, i));
		RM.Drying           .addRecipe1(T, 16,   16, ST.make(BlocksGT.CFoamFresh, 1, i), ST.make(BlocksGT.CFoam, 1, i));
		}
		
		for (OreDictMaterial tClay : ANY.Clay.mToThis) {
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Redstone), OM.dust(MT.Basalz, U9), OM.dust(tClay), OM.dust(MT.Obsidian)), OM.dust(MT.Petrotheum, 2*U));
		}
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer            .addRecipeX(T, 16,   64, ST.array(IL.Clay_Ball_White.get(2), OM.dust(tMat    ), OM.dust(MT.PotassiumFeldspar    )), OM.dust(MT.Porcelain, U*4));
		RM.Mixer            .addRecipeX(T, 16,   64, ST.array(OM.dust(MT.Kaolinite,U*2), OM.dust(tMat    ), OM.dust(MT.PotassiumFeldspar    )), OM.dust(MT.Porcelain, U*4));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Kaolinite, U2), OM.dust(tMat, U4), OM.dust(MT.PotassiumFeldspar, U4)), OM.dust(MT.Porcelain     ));
		}
		for (OreDictMaterial tGlowstone : ANY.Glowstone.mToThis) {
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Stone        ), OM.dust(MT.Netherrack    ), OM.dust(MT.Redstone    ), OM.dust(tGlowstone   )), OM.dust(MT.ArcaneCompound, U2));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Stone    , U4), OM.dust(MT.Netherrack, U4), OM.dust(MT.Redstone, U4), OM.dust(tGlowstone,U4)), OM.dust(MT.ArcaneCompound, U8));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Stone    , U9), OM.dust(MT.Netherrack, U9), OM.dust(MT.Redstone, U9), OM.dust(tGlowstone,U9)), OM.dust(MT.ArcaneCompound, U18));
		}
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Ag           ), OM.dust(MT.I    )), OM.dust(MT.AgI, 2*U  ));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Ag       , U4), OM.dust(MT.I, U4)), OM.dust(MT.AgI, 2* U4));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Ag       , U9), OM.dust(MT.I, U9)), OM.dust(MT.AgI, 2* U9));
		for (OreDictMaterial tMat : ANY.Quartz.mToThis) {ItemStack tDust = dust.mat(tMat, 1), tGem = gem.mat(tMat, 1);
		if (ST.valid(tDust)) {
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(MT.C           ,U*2), tDust, OM.dust(MT.BlackQuartz));
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(MT.Coal            ), tDust, OM.dust(MT.BlackQuartz));
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(MT.Charcoal        ), tDust, OM.dust(MT.BlackQuartz));
		}
		if (ST.valid(tGem)) {
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(MT.C           ,U*2), tGem, OM.dust(MT.BlackQuartz));
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(MT.Coal            ), tGem, OM.dust(MT.BlackQuartz));
		RM.Mixer            .addRecipe2(T, 16,   16, OM.dust(MT.Charcoal        ), tGem, OM.dust(MT.BlackQuartz));
		}}
		for (OreDictMaterial tNiter : new OreDictMaterial[] {MT.NaNO3, MT.KNO3, MT.Niter}) {
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.C       ,2*U ), OM.dust(tNiter     ), OM.dust(MT.Blaze,   U9)), OM.dust(MT.Gunpowder, 4*U ));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Charcoal,2*U ), OM.dust(tNiter     ), OM.dust(MT.Blaze,   U9)), OM.dust(MT.Gunpowder, 4*U ));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Coal         ), OM.dust(tNiter     ), OM.dust(MT.Blaze,   U9)), OM.dust(MT.Gunpowder, 4*U ));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.CoalCoke     ), OM.dust(tNiter     ), OM.dust(MT.Blaze,   U9)), OM.dust(MT.Gunpowder, 4*U ));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.C       ,2*U ), OM.dust(tNiter     ), OM.dust(MT.S          )), OM.dust(MT.Gunpowder, 4*U ));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Charcoal,2*U ), OM.dust(tNiter     ), OM.dust(MT.S          )), OM.dust(MT.Gunpowder, 4*U ));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Coal         ), OM.dust(tNiter     ), OM.dust(MT.S          )), OM.dust(MT.Gunpowder, 4*U ));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.CoalCoke     ), OM.dust(tNiter     ), OM.dust(MT.S          )), OM.dust(MT.Gunpowder, 4*U ));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.C       ,2*U4), OM.dust(tNiter,  U4), OM.dust(MT.S    ,   U4)), OM.dust(MT.Gunpowder, 4*U4));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Charcoal,2*U4), OM.dust(tNiter,  U4), OM.dust(MT.S    ,   U4)), OM.dust(MT.Gunpowder, 4*U4));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Coal    ,  U4), OM.dust(tNiter,  U4), OM.dust(MT.S    ,   U4)), OM.dust(MT.Gunpowder, 4*U4));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.CoalCoke,  U4), OM.dust(tNiter,  U4), OM.dust(MT.S    ,   U4)), OM.dust(MT.Gunpowder, 4*U4));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.C       ,2*U9), OM.dust(tNiter,  U9), OM.dust(MT.S    ,   U9)), OM.dust(MT.Gunpowder, 4*U9));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Charcoal,2*U9), OM.dust(tNiter,  U9), OM.dust(MT.S    ,   U9)), OM.dust(MT.Gunpowder, 4*U9));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Coal    ,  U9), OM.dust(tNiter,  U9), OM.dust(MT.S    ,   U9)), OM.dust(MT.Gunpowder, 4*U9));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.CoalCoke,  U9), OM.dust(tNiter,  U9), OM.dust(MT.S    ,   U9)), OM.dust(MT.Gunpowder, 4*U9));
		for (OreDictMaterial tMat : ANY.SiO2.mToThis)
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Redstone     ), OM.dust(tNiter     ), OM.dust(MT.Blitz,   U9), OM.dust(tMat                )), OM.dust(MT.Aerotheum, 2*U));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Redstone     ), OM.dust(tNiter     ), OM.dust(MT.Blizz,   U9), ST.make(Items.snowball, 1, W)), OM.dust(MT.Cryotheum, 2*U));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Redstone     ), OM.dust(tNiter     ), OM.dust(MT.Blizz,   U9), OM.dust(MT.Snow       ,   U4)), OM.dust(MT.Cryotheum, 2*U));
		RM.Mixer            .addRecipeX(T, 16,   64, ST.array(OM.dust(MT.Redstone,4*U ), OM.dust(tNiter,4*U ), OM.dust(MT.Blizz, 4*U9), ST.make(Blocks.snow   , 1, W)), OM.dust(MT.Cryotheum, 8*U));
		RM.Mixer            .addRecipeX(T, 16,   64, ST.array(OM.dust(MT.Redstone,4*U ), OM.dust(tNiter,4*U ), OM.dust(MT.Blizz, 4*U9), OM.dust(MT.Snow             )), OM.dust(MT.Cryotheum, 8*U));
		}
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Redstone), OM.dust(MT.Coal), OM.dust(MT.S)), FL.Blaze.make(L), NF, OM.dust(MT.Pyrotheum, 2*U));
		RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Redstone), OM.dust(MT.Blaze, U9), OM.dust(MT.Coal), OM.dust(MT.S)), OM.dust(MT.Pyrotheum, 2*U));
		if (FL.Pyrotheum.exists() && FL.Cryotheum.exists() && FL.Petrotheum.exists() && FL.Aerotheum.exists() && FL.Mana_TE.exists())
		RM.Mixer            .addRecipe0(T, 16,   16, FL.array(FL.Pyrotheum.make(1), FL.Cryotheum.make(1), FL.Petrotheum.make(1), FL.Aerotheum.make(1)), FL.array(FL.Mana_TE.make(4)), ZL_IS);
		
		RM.Mixer            .addRecipeX(T, 16,   32, ST.array(OM.dust(MT.Fe), OM.dust(MT.LiveRoot), OM.dust(MT.Au,  U9)), OM.dust(MT.IronWood, U*2));
		RM.Mixer            .addRecipe2(T, 16,   32, OM.dust(MT.GildedIron ), OM.dust(MT.LiveRoot), OM.dust(MT.IronWood, U*2));
		
		// Dyes
		for (FluidStack[] tDyes : new FluidStack[][] {DYE_FLUIDS_WATER, DYE_FLUIDS_FLOWER, DYE_FLUIDS_CHEMICAL}) {
		RM.Mixer            .addRecipe0(T, 16, 64, FL.array(tDyes[DYE_INDEX_Red    ], tDyes[DYE_INDEX_Blue     ]), FL.mul(tDyes[DYE_INDEX_Purple    ], 2), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16, 64, FL.array(tDyes[DYE_INDEX_Green  ], tDyes[DYE_INDEX_Blue     ]), FL.mul(tDyes[DYE_INDEX_Cyan      ], 2), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16, 64, FL.array(tDyes[DYE_INDEX_Red    ], tDyes[DYE_INDEX_White    ]), FL.mul(tDyes[DYE_INDEX_Pink      ], 2), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16, 64, FL.array(tDyes[DYE_INDEX_Green  ], tDyes[DYE_INDEX_White    ]), FL.mul(tDyes[DYE_INDEX_Lime      ], 2), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16, 64, FL.array(tDyes[DYE_INDEX_Blue   ], tDyes[DYE_INDEX_White    ]), FL.mul(tDyes[DYE_INDEX_LightBlue ], 2), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16, 64, FL.array(tDyes[DYE_INDEX_Purple ], tDyes[DYE_INDEX_Pink     ]), FL.mul(tDyes[DYE_INDEX_Magenta   ], 2), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16, 64, FL.array(tDyes[DYE_INDEX_Red    ], tDyes[DYE_INDEX_Yellow   ]), FL.mul(tDyes[DYE_INDEX_Orange    ], 2), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16, 64, FL.array(tDyes[DYE_INDEX_Black  ], tDyes[DYE_INDEX_White    ]), FL.mul(tDyes[DYE_INDEX_Gray      ], 2), ZL_IS);
		RM.Mixer            .addRecipe0(T, 16, 64, FL.array(tDyes[DYE_INDEX_Gray   ], tDyes[DYE_INDEX_White    ]), FL.mul(tDyes[DYE_INDEX_LightGray ], 2), ZL_IS);
		}
		
		// Glass
		for (int i = 0; i < 16; i++) {
		RM.Mixer            .addRecipe1(T, 16, 16, OM.dust(MT.Na2SO4     ), FL.array(MT.Glass.liquid(U*9*4, T), FL.mul(DYE_FLUIDS_CHEMICAL[i], 1,  1, T)), ZL_FS, ST.make(BlocksGT.Glass, 4, i));
		RM.Mixer            .addRecipe1(T, 16, 16, OM.dust(MT.Na2SO4,  U4), FL.array(MT.Glass.liquid(U*9  , T), FL.mul(DYE_FLUIDS_CHEMICAL[i], 1,  4, T)), ZL_FS, ST.make(BlocksGT.Glass, 1, i));
		RM.Mixer            .addRecipe1(T, 16, 16, OM.dust(MT.K2SO4      ), FL.array(MT.Glass.liquid(U*9*4, T), FL.mul(DYE_FLUIDS_CHEMICAL[i], 1,  1, T)), ZL_FS, ST.make(BlocksGT.Glass, 4, i));
		RM.Mixer            .addRecipe1(T, 16, 16, OM.dust(MT.K2SO4 ,  U4), FL.array(MT.Glass.liquid(U*9  , T), FL.mul(DYE_FLUIDS_CHEMICAL[i], 1,  4, T)), ZL_FS, ST.make(BlocksGT.Glass, 1, i));
		
		for (OreDictMaterial tMat : ANY.Glowstone.mToThis) {
		RM.Injector         .addRecipe2(T, 16, 32, dust     .mat(tMat, 1), ST.make(BlocksGT.Glass, 1, i), ST.make(BlocksGT.GlowGlass, 1, i));
		RM.Injector         .addRecipe2(T, 16, 32, dustSmall.mat(tMat, 4), ST.make(BlocksGT.Glass, 1, i), ST.make(BlocksGT.GlowGlass, 1, i));
		RM.Injector         .addRecipe2(T, 16, 32, dust     .mat(tMat, 1), ST.make(((BlockMetaType)BlocksGT.Glass).mSlabs[0], 2, i), ST.make(((BlockMetaType)BlocksGT.GlowGlass).mSlabs[0], 2, i));
		RM.Injector         .addRecipe2(T, 16, 16, dustSmall.mat(tMat, 2), ST.make(((BlockMetaType)BlocksGT.Glass).mSlabs[0], 1, i), ST.make(((BlockMetaType)BlocksGT.GlowGlass).mSlabs[0], 1, i));
		}
		}
		
		// Lubricant
		for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Talc, MT.Redstone}) {
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Frying    .make(1000), FL.lube(1000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Sunflower .make(1000), FL.lube(1000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Olive     .make(1000), FL.lube(1000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Nut       .make(1000), FL.lube(1000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Lin       .make(1000), FL.lube(1000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Hemp      .make(1000), FL.lube(1000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Fish      .make(1000), FL.lube(1000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Whale     .make(1000), FL.lube(1000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Seed      .make(1000), FL.lube(1000), ZL_IS); if (FL.Oil_Plant.exists())
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Plant     .make(1000), FL.lube(1000), ZL_IS); if (FL.Oil_Canola.exists())
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Canola    .make(1000), FL.lube(2000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Creosote  .make(1000), FL.lube(2000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Soulsand  .make(1000), FL.lube(3000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Light     .make(1000), FL.lube(3000), ZL_IS); if (FL.Oil_Light2.exists())
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Light2    .make(1000), FL.lube(3000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Normal    .make(1000), FL.lube(4000), ZL_IS); if (FL.Oil_HotCrude.exists())
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_HotCrude  .make(1000), FL.lube(4000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Medium    .make(1000), FL.lube(4000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Heavy     .make(1000), FL.lube(5000), ZL_IS); if (FL.Oil_Heavy2.exists())
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_Heavy2    .make(1000), FL.lube(5000), ZL_IS);
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tMat), FL.Oil_ExtraHeavy.make(1000), FL.lube(6000), ZL_IS);
		}
		
		// Other
		RM.Lightning    .addRecipe2(T, 16, 2048, ST.tag(1), gem.mat(MT.CertusQuartz, 1), gem.mat(MT.ChargedCertusQuartz, 1));
		
		
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Press        .addRecipeX(T, 16,   16, ST.array(dust     .mat(tMat, 2), dust     .mat(MT.Gunpowder, 2), ST.make(Items.string                                    , 1, W)), IL.Boomstick      .get(1));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(dust     .mat(tMat, 2), dust     .mat(MT.Gunpowder, 2), ST.make((Item)plantGtFiber.mRegisteredPrefixItems.get(0), 1, W)), IL.Boomstick      .get(1));
		RM.Press        .addRecipeX(T, 16,  144, ST.array(blockDust.mat(tMat, 2), blockDust.mat(MT.Gunpowder, 2), ST.make(Items.string                                    , 9, W)), IL.Boomstick      .get(9));
		RM.Press        .addRecipeX(T, 16,  144, ST.array(blockDust.mat(tMat, 2), blockDust.mat(MT.Gunpowder, 2), ST.make((Item)plantGtFiber.mRegisteredPrefixItems.get(0), 9, W)), IL.Boomstick      .get(9));
		}
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag             (1), dust     .mat(MT.Dynamite , 1), ST.make(Items.string                                    , 1, W)), IL.Dynamite       .get(1));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag             (1), dust     .mat(MT.Dynamite , 1), ST.make((Item)plantGtFiber.mRegisteredPrefixItems.get(0), 1, W)), IL.Dynamite       .get(1));
		RM.Press        .addRecipeX(T, 16,  144, ST.array(ST.tag             (1), blockDust.mat(MT.Dynamite , 1), ST.make(Items.string                                    , 9, W)), IL.Dynamite       .get(9));
		RM.Press        .addRecipeX(T, 16,  144, ST.array(ST.tag             (1), blockDust.mat(MT.Dynamite , 1), ST.make((Item)plantGtFiber.mRegisteredPrefixItems.get(0), 9, W)), IL.Dynamite       .get(9));
		
		RM.Press        .addRecipeX(T, 16,   64, ST.array(ST.tag             (2), dust     .mat(MT.Dynamite , 2), ST.make(Items.string                                    , 1, W)), IL.Dynamite_Strong.get(1));
		RM.Press        .addRecipeX(T, 16,   64, ST.array(ST.tag             (2), dust     .mat(MT.Dynamite , 2), ST.make((Item)plantGtFiber.mRegisteredPrefixItems.get(0), 1, W)), IL.Dynamite_Strong.get(1));
		RM.Press        .addRecipeX(T, 16,  576, ST.array(ST.tag             (2), blockDust.mat(MT.Dynamite , 2), ST.make(Items.string                                    , 9, W)), IL.Dynamite_Strong.get(9));
		RM.Press        .addRecipeX(T, 16,  576, ST.array(ST.tag             (2), blockDust.mat(MT.Dynamite , 2), ST.make((Item)plantGtFiber.mRegisteredPrefixItems.get(0), 9, W)), IL.Dynamite_Strong.get(9));
		
		
		RM.Press        .addRecipeX(T, 16,   16, ST.array(IL.Shape_Press_Bullet_Casing_Small .get(0), dustDiv72.mat(MT.Gunpowder, 8), plateTiny.mat(MT.Brass, 1)), bulletGtSmall .mat(MT.Empty, 1));
		RM.Press        .addRecipeX(T, 16,   32, ST.array(IL.Shape_Press_Bullet_Casing_Medium.get(0), dustDiv72.mat(MT.Gunpowder,16), plateTiny.mat(MT.Brass, 2)), bulletGtMedium.mat(MT.Empty, 1));
		RM.Press        .addRecipeX(T, 16,   64, ST.array(IL.Shape_Press_Bullet_Casing_Large .get(0), dustDiv72.mat(MT.Gunpowder,24), plateTiny.mat(MT.Brass, 3)), bulletGtLarge .mat(MT.Empty, 1));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(IL.Shape_Press_Bullet_Casing_Small .get(0), dustTiny .mat(MT.Gunpowder, 1), plateTiny.mat(MT.Brass, 1)), bulletGtSmall .mat(MT.Empty, 1));
		RM.Press        .addRecipeX(T, 16,   32, ST.array(IL.Shape_Press_Bullet_Casing_Medium.get(0), dustTiny .mat(MT.Gunpowder, 2), plateTiny.mat(MT.Brass, 2)), bulletGtMedium.mat(MT.Empty, 1));
		RM.Press        .addRecipeX(T, 16,   64, ST.array(IL.Shape_Press_Bullet_Casing_Large .get(0), dustTiny .mat(MT.Gunpowder, 3), plateTiny.mat(MT.Brass, 3)), bulletGtLarge .mat(MT.Empty, 1));
		RM.Press        .addRecipeX(T, 16,  144, ST.array(IL.Shape_Press_Bullet_Casing_Small .get(0), dustSmall.mat(MT.Gunpowder, 4), plateTiny.mat(MT.Brass, 9)), bulletGtSmall .mat(MT.Empty, 9));
		RM.Press        .addRecipeX(T, 16,  288, ST.array(IL.Shape_Press_Bullet_Casing_Medium.get(0), dustSmall.mat(MT.Gunpowder, 8), plateTiny.mat(MT.Brass,18)), bulletGtMedium.mat(MT.Empty, 9));
		RM.Press        .addRecipeX(T, 16,  192, ST.array(IL.Shape_Press_Bullet_Casing_Large .get(0), dustSmall.mat(MT.Gunpowder, 4), plateTiny.mat(MT.Brass, 9)), bulletGtLarge .mat(MT.Empty, 3));
		RM.Press        .addRecipeX(T, 16,  144, ST.array(IL.Shape_Press_Bullet_Casing_Small .get(0), dust     .mat(MT.Gunpowder, 1), plateTiny.mat(MT.Brass, 9)), bulletGtSmall .mat(MT.Empty, 9));
		RM.Press        .addRecipeX(T, 16,  288, ST.array(IL.Shape_Press_Bullet_Casing_Medium.get(0), dust     .mat(MT.Gunpowder, 2), plateTiny.mat(MT.Brass,18)), bulletGtMedium.mat(MT.Empty, 9));
		RM.Press        .addRecipeX(T, 16,  192, ST.array(IL.Shape_Press_Bullet_Casing_Large .get(0), dust     .mat(MT.Gunpowder, 1), plateTiny.mat(MT.Brass, 9)), bulletGtLarge .mat(MT.Empty, 3));
		
		
		for (byte i = 0; i < 16; i++) {
		RM.Drying       .addRecipe0(T, 16,   40, FL.mul(DYE_FLUIDS_WATER [i], 1, 6, T), FL.DistW.make(20), dustTiny.mat(MT.DATA.Dye_Materials[i], 1));
		RM.Drying       .addRecipe0(T, 16,   20, FL.mul(DYE_FLUIDS_FLOWER[i], 1, 6, T), FL.DistW.make(10), dustTiny.mat(MT.DATA.Dye_Materials[i], 1));
		}
		
		RM.Centrifuge   .addRecipe0(T, 64,   16, MT.FishOil .liquid( U8, T), MT.Hg.liquid(1, T), ZL_IS);
		RM.Centrifuge   .addRecipe0(T, 64,   16, MT.WhaleOil.liquid( U8, T), MT.Hg.liquid(1, T), ZL_IS);
		
		RM.Coagulator   .addRecipe0(T,  0,  256, FL.Latex.make(L/9), NF, nugget.mat(MT.Rubber, 1));
		
		for (FluidStack tFluid : FL.array(MT.He.gas(U, T), MT.Ne.gas(U, T), MT.Ar.gas(U, T), MT.Kr.gas(U, T), MT.Xe.gas(U, T), MT.Rn.gas(U, T))) if (tFluid != null) {
			RM.CrystallisationCrucible.addRecipe1(T, 16,  72000, OM.dust(MT.Si             ,  U9), FL.array(       tFluid    , MT.Si            .liquid(35*U9, T)), NF, bouleGt.mat(MT.Si              , 1));
			RM.CrystallisationCrucible.addRecipe1(T, 16,  72000, OM.dust(MT.Ge             ,  U9), FL.array(       tFluid    , MT.Ge            .liquid(35*U9, T)), NF, bouleGt.mat(MT.Ge              , 1));
			RM.CrystallisationCrucible.addRecipe1(T, 16,  72000, OM.dust(MT.RedstoneAlloy  ,  U9), FL.array(       tFluid    , MT.RedstoneAlloy .liquid(35*U9, T)), NF, bouleGt.mat(MT.RedstoneAlloy   , 1));
			RM.CrystallisationCrucible.addRecipe1(T, 16,  72000, OM.dust(MT.NikolineAlloy  ,  U9), FL.array(       tFluid    , MT.NikolineAlloy .liquid(35*U9, T)), NF, bouleGt.mat(MT.NikolineAlloy   , 1));
			RM.CrystallisationCrucible.addRecipe1(T, 16, 648000, OM.dust(MT.Si                  ), FL.array(FL.mul(tFluid, 9), MT.Si            .liquid(35*U , T)), NF, bouleGt.mat(MT.Si              , 9));
			RM.CrystallisationCrucible.addRecipe1(T, 16, 648000, OM.dust(MT.Ge                  ), FL.array(FL.mul(tFluid, 9), MT.Ge            .liquid(35*U , T)), NF, bouleGt.mat(MT.Ge              , 9));
			RM.CrystallisationCrucible.addRecipe1(T, 16, 648000, OM.dust(MT.RedstoneAlloy       ), FL.array(FL.mul(tFluid, 9), MT.RedstoneAlloy .liquid(35*U , T)), NF, bouleGt.mat(MT.RedstoneAlloy   , 9));
			RM.CrystallisationCrucible.addRecipe1(T, 16, 648000, OM.dust(MT.NikolineAlloy       ), FL.array(FL.mul(tFluid, 9), MT.NikolineAlloy .liquid(35*U , T)), NF, bouleGt.mat(MT.NikolineAlloy   , 9));
			
			RM.CrystallisationCrucible.addRecipe1(T,256,  18000, OM.dust(MT.Al2O3          ,  U9), FL.array(       tFluid    , MT.Al2O3         .liquid(29*U9, T)), NF, bouleGt.mat(MT.Sapphire        , 1));
			RM.CrystallisationCrucible.addRecipe1(T,256, 156000, OM.dust(MT.Al2O3               ), FL.array(FL.mul(tFluid, 9), MT.Al2O3         .liquid(29*U , T)), NF, bouleGt.mat(MT.Sapphire        , 9));
			
			RM.CrystallisationCrucible.addRecipe1(T,256,  18000, OM.dust(MT.Mg             ,2*U3), FL.array(       tFluid    , MT.Al2O3         .liquid(10*U3, T)), NF, bouleGt.mat(MT.GreenSapphire   , 1));
			RM.CrystallisationCrucible.addRecipe1(T,256,  18000, OM.dust(MT.Ti             ,2*U3), FL.array(       tFluid    , MT.Al2O3         .liquid(10*U3, T)), NF, bouleGt.mat(MT.YellowSapphire  , 1));
			RM.CrystallisationCrucible.addRecipe1(T,256,  18000, OM.dust(MT.Cu             ,2*U3), FL.array(       tFluid    , MT.Al2O3         .liquid(10*U3, T)), NF, bouleGt.mat(MT.OrangeSapphire  , 1));
			RM.CrystallisationCrucible.addRecipe1(T,256,  18000, OM.dust(MT.Fe             ,2*U3), FL.array(       tFluid    , MT.Al2O3         .liquid(10*U3, T)), NF, bouleGt.mat(MT.BlueSapphire    , 1));
			RM.CrystallisationCrucible.addRecipe1(T,256,  18000, OM.dust(MT.V              ,2*U3), FL.array(       tFluid    , MT.Al2O3         .liquid(10*U3, T)), NF, bouleGt.mat(MT.PurpleSapphire  , 1));
			RM.CrystallisationCrucible.addRecipe1(T,256,  18000, OM.dust(MT.Cr             ,2*U3), FL.array(       tFluid    , MT.Al2O3         .liquid(10*U3, T)), NF, bouleGt.mat(MT.Ruby            , 1));
			RM.CrystallisationCrucible.addRecipe1(T,256,  52000, OM.dust(MT.Mg             ,2*U ), FL.array(FL.mul(tFluid, 3), MT.Al2O3         .liquid(10*U , T)), NF, bouleGt.mat(MT.GreenSapphire   , 3));
			RM.CrystallisationCrucible.addRecipe1(T,256,  52000, OM.dust(MT.Ti             ,2*U ), FL.array(FL.mul(tFluid, 3), MT.Al2O3         .liquid(10*U , T)), NF, bouleGt.mat(MT.YellowSapphire  , 3));
			RM.CrystallisationCrucible.addRecipe1(T,256,  52000, OM.dust(MT.Cu             ,2*U ), FL.array(FL.mul(tFluid, 3), MT.Al2O3         .liquid(10*U , T)), NF, bouleGt.mat(MT.OrangeSapphire  , 3));
			RM.CrystallisationCrucible.addRecipe1(T,256,  52000, OM.dust(MT.Fe             ,2*U ), FL.array(FL.mul(tFluid, 3), MT.Al2O3         .liquid(10*U , T)), NF, bouleGt.mat(MT.BlueSapphire    , 3));
			RM.CrystallisationCrucible.addRecipe1(T,256,  52000, OM.dust(MT.V              ,2*U ), FL.array(FL.mul(tFluid, 3), MT.Al2O3         .liquid(10*U , T)), NF, bouleGt.mat(MT.PurpleSapphire  , 3));
			RM.CrystallisationCrucible.addRecipe1(T,256,  52000, OM.dust(MT.Cr             ,2*U ), FL.array(FL.mul(tFluid, 3), MT.Al2O3         .liquid(10*U , T)), NF, bouleGt.mat(MT.Ruby            , 3));
		}
		
		for (ItemStack tTNT : ST.array(ST.make(Blocks.tnt, 8, W), IL.IC2_ITNT.get(4), IL.Boomstick.get(12), IL.Dynamite.get(2), IL.Dynamite_Strong.get(1))) if (ST.valid(tTNT)) {
			for (OreDictMaterial tMat : ANY.Diamond.mToThis) {OreDictMaterial tOutput = MT.DiamondIndustrial;
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(0)), plateGem    .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(1)), gem         .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 2), ST.mul(4, tTNT), ST.tag(2)), gemFlawless .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 4), ST.mul(8, tTNT), ST.tag(3)), gemExquisite.mat(tOutput, 1));
			}
			for (OreDictMaterial tMat : ANY.Sapphire.mToThis) {OreDictMaterial tOutput = MT.Sapphire;
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(0)), plateGem    .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(1)), gem         .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 2), ST.mul(4, tTNT), ST.tag(2)), gemFlawless .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 4), ST.mul(8, tTNT), ST.tag(3)), gemExquisite.mat(tOutput, 1));
			}
			for (OreDictMaterial tMat : ANY.Emerald.mToThis) {OreDictMaterial tOutput = MT.Emerald;
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(0)), plateGem    .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(1)), gem         .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 2), ST.mul(4, tTNT), ST.tag(2)), gemFlawless .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 4), ST.mul(8, tTNT), ST.tag(3)), gemExquisite.mat(tOutput, 1));
			}
			for (OreDictMaterial tMat : ANY.Amethyst.mToThis) {OreDictMaterial tOutput = tMat;
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(0)), plateGem    .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(1)), gem         .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 2), ST.mul(4, tTNT), ST.tag(2)), gemFlawless .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 4), ST.mul(8, tTNT), ST.tag(3)), gemExquisite.mat(tOutput, 1));
			}
			for (OreDictMaterial tMat : ANY.Garnet.mToThis) {OreDictMaterial tOutput = tMat;
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(0)), plateGem    .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(1)), gem         .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 2), ST.mul(4, tTNT), ST.tag(2)), gemFlawless .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 4), ST.mul(8, tTNT), ST.tag(3)), gemExquisite.mat(tOutput, 1));
			}
			for (OreDictMaterial tMat : ANY.Jasper.mToThis) {OreDictMaterial tOutput = tMat;
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(0)), plateGem    .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(1)), gem         .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 2), ST.mul(4, tTNT), ST.tag(2)), gemFlawless .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 4), ST.mul(8, tTNT), ST.tag(3)), gemExquisite.mat(tOutput, 1));
			}
			for (OreDictMaterial tMat : ANY.TigerEye.mToThis) {OreDictMaterial tOutput = tMat;
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(0)), plateGem    .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(1)), gem         .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 2), ST.mul(4, tTNT), ST.tag(2)), gemFlawless .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 4), ST.mul(8, tTNT), ST.tag(3)), gemExquisite.mat(tOutput, 1));
			}
			for (OreDictMaterial tMat : ANY.Aventurine.mToThis) {OreDictMaterial tOutput = tMat;
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(0)), plateGem    .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(1)), gem         .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 2), ST.mul(4, tTNT), ST.tag(2)), gemFlawless .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 4), ST.mul(8, tTNT), ST.tag(3)), gemExquisite.mat(tOutput, 1));
			}
			for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Spinel, MT.BalasRuby, MT.Topaz, MT.BlueTopaz, MT.Tanzanite, MT.Amazonite, MT.Alexandrite, MT.Opal, MT.OnyxRed, MT.OnyxBlack, MT.Olivine, MT.Dioptase, MT.Craponite}) {OreDictMaterial tOutput = tMat;
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(0)), plateGem    .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 1),           tTNT , ST.tag(1)), gem         .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 2), ST.mul(4, tTNT), ST.tag(2)), gemFlawless .mat(tOutput, 1));
				RM.ImplosionCompressor.addRecipeX(T,  0,  256, ST.array(dust.mat(tMat, 4), ST.mul(8, tTNT), ST.tag(3)), gemExquisite.mat(tOutput, 1));
			}
		}
		
		for (int i = 0; i < 16; i++)
		RM.Loom    .addRecipe2(T, 16,   16, ST.tag(10), plantGtFiber.mat(MT.DATA.Dye_Materials[15-i], 4), IL.Rope.get(1));
		RM.Loom    .addRecipe2(T, 16,   16, ST.tag(10), plantGtFiber.mat(MT.Cu, 4), IL.Rope.get(1));
		RM.Loom    .addRecipe2(T, 16,   16, ST.tag(10), ST.make(Items.string, 4, W), IL.Rope_Silk.get(1));
		RM.Loom    .addRecipe2(T, 16,   16, ST.tag(10), wireFine.mat(MT.Plastic, 4), IL.Rope_Plastic.get(1));
		
		RM.CokeOven.addRecipe1(T,  0,  3600, gem                   .mat(MT.Coal    , 1), NF, MT.Creosote.liquid(   U2, F), gem       .mat(MT.CoalCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, nugget                .mat(MT.Coal    , 9), NF, MT.Creosote.liquid(   U2, F), ingot     .mat(MT.CoalCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, chunkGt               .mat(MT.Coal    , 4), NF, MT.Creosote.liquid(   U2, F), ingot     .mat(MT.CoalCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  7200, billet                .mat(MT.Coal    , 3), NF, MT.Creosote.liquid(   U , F), ingot     .mat(MT.CoalCoke, 2));
		RM.CokeOven.addRecipe1(T,  0,  3600, ingot                 .mat(MT.Coal    , 1), NF, MT.Creosote.liquid(   U2, F), ingot     .mat(MT.CoalCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  7200, oreRaw                .mat(MT.Coal    , 1), NF, MT.Creosote.liquid(   U , F), ingot     .mat(MT.CoalCoke, 2));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushed               .mat(MT.Coal    , 1), NF, MT.Creosote.liquid(   U2, F), ingot     .mat(MT.CoalCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedTiny           .mat(MT.Coal    , 9), NF, MT.Creosote.liquid(   U2, F), ingot     .mat(MT.CoalCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedPurified       .mat(MT.Coal    , 1), NF, MT.Creosote.liquid(   U2, F), chunkGt   .mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedPurifiedTiny   .mat(MT.Coal    , 9), NF, MT.Creosote.liquid(   U2, F), chunkGt   .mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedCentrifuged    .mat(MT.Coal    , 1), NF, MT.Creosote.liquid(   U2, F), chunkGt   .mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedCentrifugedTiny.mat(MT.Coal    , 9), NF, MT.Creosote.liquid(   U2, F), chunkGt   .mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1), chunkGt.mat(MT.CoalCoke, 1));
		RM.CokeOven.addRecipe1(T,  0, 32400, blockRaw              .mat(MT.Coal    , 1), NF, MT.Creosote.liquid( 9*U , F), blockIngot.mat(MT.CoalCoke, 2));
		RM.CokeOven.addRecipe1(T,  0, 32400, blockIngot            .mat(MT.Coal    , 1), NF, MT.Creosote.liquid( 9*U2, F), blockIngot.mat(MT.CoalCoke, 1));
		RM.CokeOven.addRecipe1(T,  0, 32400, blockGem              .mat(MT.Coal    , 1), NF, MT.Creosote.liquid( 9*U2, F), blockGem  .mat(MT.CoalCoke, 1));
		
		RM.CokeOven.addRecipe1(T,  0,  3600, gem                   .mat(MT.Lignite , 1), NF, MT.Creosote.liquid( 3*U4, F), gem       .mat(MT.LigniteCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, nugget                .mat(MT.Lignite , 9), NF, MT.Creosote.liquid( 3*U4, F), ingot     .mat(MT.LigniteCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, chunkGt               .mat(MT.Lignite , 4), NF, MT.Creosote.liquid( 3*U4, F), ingot     .mat(MT.LigniteCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  7200, billet                .mat(MT.Lignite , 3), NF, MT.Creosote.liquid( 3*U2, F), ingot     .mat(MT.LigniteCoke, 2));
		RM.CokeOven.addRecipe1(T,  0,  3600, ingot                 .mat(MT.Lignite , 1), NF, MT.Creosote.liquid( 3*U4, F), ingot     .mat(MT.LigniteCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  7200, oreRaw                .mat(MT.Lignite , 1), NF, MT.Creosote.liquid( 3*U2, F), ingot     .mat(MT.LigniteCoke, 2));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushed               .mat(MT.Lignite , 1), NF, MT.Creosote.liquid( 3*U4, F), ingot     .mat(MT.LigniteCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedTiny           .mat(MT.Lignite , 9), NF, MT.Creosote.liquid( 3*U4, F), ingot     .mat(MT.LigniteCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedPurified       .mat(MT.Lignite , 1), NF, MT.Creosote.liquid( 3*U4, F), chunkGt   .mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedPurifiedTiny   .mat(MT.Lignite , 9), NF, MT.Creosote.liquid( 3*U4, F), chunkGt   .mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedCentrifuged    .mat(MT.Lignite , 1), NF, MT.Creosote.liquid( 3*U4, F), chunkGt   .mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedCentrifugedTiny.mat(MT.Lignite , 9), NF, MT.Creosote.liquid( 3*U4, F), chunkGt   .mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1), chunkGt.mat(MT.LigniteCoke, 1));
		RM.CokeOven.addRecipe1(T,  0, 32400, blockRaw              .mat(MT.Lignite , 1), NF, MT.Creosote.liquid(27*U2, F), blockIngot.mat(MT.LigniteCoke, 2));
		RM.CokeOven.addRecipe1(T,  0, 32400, blockIngot            .mat(MT.Lignite , 1), NF, MT.Creosote.liquid(27*U4, F), blockIngot.mat(MT.LigniteCoke, 1));
		RM.CokeOven.addRecipe1(T,  0, 32400, blockGem              .mat(MT.Lignite , 1), NF, MT.Creosote.liquid(27*U4, F), blockGem  .mat(MT.LigniteCoke, 1));
		
		RM.CokeOven.addRecipe1(T,  0,  3600, dust                  .mat(MT.Oilshale, 1), NF, MT.Oil     .liquid(  U40, F), dustTiny  .mat(MT.Asphalt, 1));
		RM.CokeOven.addRecipe1(T,  0,  7200, oreRaw                .mat(MT.Oilshale, 1), NF, MT.Oil     .liquid(  U20, F), dustTiny  .mat(MT.Asphalt, 2));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushed               .mat(MT.Oilshale, 1), NF, MT.Oil     .liquid(  U40, F), dustTiny  .mat(MT.Asphalt, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedTiny           .mat(MT.Oilshale, 9), NF, MT.Oil     .liquid(  U40, F), dustTiny  .mat(MT.Asphalt, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedPurified       .mat(MT.Oilshale, 1), NF, MT.Oil     .liquid(  U40, F), dustTiny  .mat(MT.Asphalt, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedPurifiedTiny   .mat(MT.Oilshale, 9), NF, MT.Oil     .liquid(  U40, F), dustTiny  .mat(MT.Asphalt, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedCentrifuged    .mat(MT.Oilshale, 1), NF, MT.Oil     .liquid(  U40, F), dustTiny  .mat(MT.Asphalt, 1));
		RM.CokeOven.addRecipe1(T,  0,  3600, crushedCentrifugedTiny.mat(MT.Oilshale, 9), NF, MT.Oil     .liquid(  U40, F), dustTiny  .mat(MT.Asphalt, 1));
		RM.CokeOven.addRecipe1(T,  0, 32400, blockDust             .mat(MT.Oilshale, 1), NF, MT.Oil     .liquid(9*U40, F), dust      .mat(MT.Asphalt, 1));
		
		
		RM.Nanofab.addRecipe2(T, 16,  64, ST.tag( 0), dustDiv72.mat(MT.C,18), foil.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 0), dustTiny .mat(MT.C, 9), foil.mat(MT.Graphene, 4));
		RM.Nanofab.addRecipe2(T, 16,  64, ST.tag( 0), dustSmall.mat(MT.C, 1), foil.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 0), dust     .mat(MT.C, 1), foil.mat(MT.Graphene, 4));
		
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 1), dustTiny .mat(MT.C, 9), plate.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 1), dustSmall.mat(MT.C, 4), plate.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 1), dust     .mat(MT.C, 1), plate.mat(MT.Graphene, 1));
		
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 2), dustTiny .mat(MT.C, 9), plateCurved.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 2), dustSmall.mat(MT.C, 4), plateCurved.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 2), dust     .mat(MT.C, 1), plateCurved.mat(MT.Graphene, 1));
		
		RM.Nanofab.addRecipe2(T, 16, 128, ST.tag( 3), dustDiv72.mat(MT.C,36), casingSmall.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 3), dustTiny .mat(MT.C, 9), casingSmall.mat(MT.Graphene, 2));
		RM.Nanofab.addRecipe2(T, 16, 128, ST.tag( 3), dustSmall.mat(MT.C, 2), casingSmall.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 3), dust     .mat(MT.C, 1), casingSmall.mat(MT.Graphene, 2));
		
		RM.Nanofab.addRecipe2(T, 16,  32, ST.tag( 4), dustDiv72.mat(MT.C, 9), wireFine.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 4), dustTiny .mat(MT.C, 9), wireFine.mat(MT.Graphene, 8));
		RM.Nanofab.addRecipe2(T, 16,  64, ST.tag( 4), dustSmall.mat(MT.C, 1), wireFine.mat(MT.Graphene, 2));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 4), dust     .mat(MT.C, 1), wireFine.mat(MT.Graphene, 8));
		
		RM.Nanofab.addRecipe2(T, 16, 128, ST.tag( 5), dustDiv72.mat(MT.C,36), wireGt01.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 5), dustTiny .mat(MT.C, 9), wireGt01.mat(MT.Graphene, 2));
		RM.Nanofab.addRecipe2(T, 16, 128, ST.tag( 5), dustSmall.mat(MT.C, 2), wireGt01.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 5), dust     .mat(MT.C, 1), wireGt01.mat(MT.Graphene, 2));
		
		RM.Nanofab.addRecipe2(T, 16,  32, ST.tag( 6), dustDiv72.mat(MT.C, 9), bolt.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 6), dustTiny .mat(MT.C, 9), bolt.mat(MT.Graphene, 8));
		RM.Nanofab.addRecipe2(T, 16,  64, ST.tag( 6), dustSmall.mat(MT.C, 1), bolt.mat(MT.Graphene, 2));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 6), dust     .mat(MT.C, 1), bolt.mat(MT.Graphene, 8));
		
		RM.Nanofab.addRecipe2(T, 16, 128, ST.tag( 7), dustDiv72.mat(MT.C,36), stick.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 7), dustTiny .mat(MT.C, 9), stick.mat(MT.Graphene, 2));
		RM.Nanofab.addRecipe2(T, 16, 128, ST.tag( 7), dustSmall.mat(MT.C, 2), stick.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 7), dust     .mat(MT.C, 1), stick.mat(MT.Graphene, 2));
		
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 8), dustTiny .mat(MT.C, 9), stickLong.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 8), dustSmall.mat(MT.C, 4), stickLong.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 8), dust     .mat(MT.C, 1), stickLong.mat(MT.Graphene, 1));
		
		RM.Nanofab.addRecipe2(T, 16,  64, ST.tag( 9), dustDiv72.mat(MT.C,18), ring.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 9), dustTiny .mat(MT.C, 9), ring.mat(MT.Graphene, 4));
		RM.Nanofab.addRecipe2(T, 16,  64, ST.tag( 9), dustSmall.mat(MT.C, 1), ring.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag( 9), dust     .mat(MT.C, 1), ring.mat(MT.Graphene, 4));
		
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag(10), dustTiny .mat(MT.C, 9), gearGtSmall.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag(10), dustSmall.mat(MT.C, 4), gearGtSmall.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag(10), dust     .mat(MT.C, 1), gearGtSmall.mat(MT.Graphene, 1));
		
		RM.Nanofab.addRecipe2(T, 16,1024, ST.tag(11), dustTiny .mat(MT.C,36), gearGt.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16,1024, ST.tag(11), dustSmall.mat(MT.C,16), gearGt.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16,1024, ST.tag(11), dust     .mat(MT.C, 4), gearGt.mat(MT.Graphene, 1));
		
		RM.Nanofab.addRecipe2(T, 16,1088, ST.tag(12), dustSmall.mat(MT.C,17), rotor.mat(MT.Graphene, 1));
		RM.Nanofab.addRecipe2(T, 16,4352, ST.tag(12), dust     .mat(MT.C,17), rotor.mat(MT.Graphene, 4));
		
		RM.Nanofab.addRecipe2(T, 16, 128, ST.tag(13), dustDiv72.mat(MT.C,36), pipeTiny.mat(MT.C, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag(13), dustTiny .mat(MT.C, 9), pipeTiny.mat(MT.C, 2));
		RM.Nanofab.addRecipe2(T, 16, 128, ST.tag(13), dustSmall.mat(MT.C, 2), pipeTiny.mat(MT.C, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag(13), dust     .mat(MT.C, 1), pipeTiny.mat(MT.C, 2));
		
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag(14), dustTiny .mat(MT.C, 9), pipeSmall.mat(MT.C, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag(14), dustSmall.mat(MT.C, 4), pipeSmall.mat(MT.C, 1));
		RM.Nanofab.addRecipe2(T, 16, 256, ST.tag(14), dust     .mat(MT.C, 1), pipeSmall.mat(MT.C, 1));
		
		RM.Nanofab.addRecipe2(T, 16, 768, ST.tag(15), dustTiny .mat(MT.C,27), pipeMedium.mat(MT.C, 1));
		RM.Nanofab.addRecipe2(T, 16, 768, ST.tag(15), dustSmall.mat(MT.C,12), pipeMedium.mat(MT.C, 1));
		RM.Nanofab.addRecipe2(T, 16, 768, ST.tag(15), dust     .mat(MT.C, 3), pipeMedium.mat(MT.C, 1));
		
		RM.Nanofab.addRecipe2(T, 16,1536, ST.tag(16), dustTiny .mat(MT.C,54), pipeLarge.mat(MT.C, 1));
		RM.Nanofab.addRecipe2(T, 16,1536, ST.tag(16), dustSmall.mat(MT.C,24), pipeLarge.mat(MT.C, 1));
		RM.Nanofab.addRecipe2(T, 16,1536, ST.tag(16), dust     .mat(MT.C, 6), pipeLarge.mat(MT.C, 1));
		
		RM.Nanofab.addRecipe2(T, 16,3072, ST.tag(17), dustSmall.mat(MT.C,48), pipeHuge.mat(MT.C, 1));
		RM.Nanofab.addRecipe2(T, 16,3072, ST.tag(17), dust     .mat(MT.C,12), pipeHuge.mat(MT.C, 1));
		
		
		if (FL.Ender_TE.exists()) {
		RM.Massfab   .addRecipe2(T,  16, 144    , ST.tag(1), dustDiv72.mat(MT.AncientDebris, 1), NF, FL.Ender_TE.make(250    ), ZL_IS);
		RM.Massfab   .addRecipe2(T,  16, 144*  8, ST.tag(1), dustTiny .mat(MT.AncientDebris, 1), NF, FL.Ender_TE.make(250*  8), ZL_IS);
		RM.Massfab   .addRecipe2(T,  16, 144* 18, ST.tag(1), dustSmall.mat(MT.AncientDebris, 1), NF, FL.Ender_TE.make(250* 18), ZL_IS);
		RM.Massfab   .addRecipe2(T,  16, 144* 72, ST.tag(1), dust     .mat(MT.AncientDebris, 1), NF, FL.Ender_TE.make(250* 72), ZL_IS);
		RM.Massfab   .addRecipe2(T,  16, 144* 72, ST.tag(1), ingot    .mat(MT.AncientDebris, 1), NF, FL.Ender_TE.make(250* 72), ZL_IS);
		RM.Massfab   .addRecipe2(T,  16, 144*648, ST.tag(1), blockDust.mat(MT.AncientDebris, 1), NF, FL.Ender_TE.make(250*648), ZL_IS);
		RM.Replicator.addRecipe1(T,  16, 144    , ST.tag(0), FL.Ender_TE.make(250), NF, gem.mat(MT.EnderPearl, 1));
		RM.Lightning .addRecipe1(T, 512, 512, blockSolid.mat(MT.Netherite, 1), FL.array(FL.Ender_TE.make(1000), FL.Oil_Soulsand.make(5000)), ZL_FS, gem.mat(MT.NetherStar, 1));
		}
		RM.Massfab   .addRecipe2(T,  16, 144    , ST.tag(0), dustDiv72.mat(MT.AncientDebris, 1), NF, FL.Ender.make(L    ), ZL_IS);
		RM.Massfab   .addRecipe2(T,  16, 144*  8, ST.tag(0), dustTiny .mat(MT.AncientDebris, 1), NF, FL.Ender.make(L*  8), ZL_IS);
		RM.Massfab   .addRecipe2(T,  16, 144* 18, ST.tag(0), dustSmall.mat(MT.AncientDebris, 1), NF, FL.Ender.make(L* 18), ZL_IS);
		RM.Massfab   .addRecipe2(T,  16, 144* 72, ST.tag(0), dust     .mat(MT.AncientDebris, 1), NF, FL.Ender.make(L* 72), ZL_IS);
		RM.Massfab   .addRecipe2(T,  16, 144* 72, ST.tag(0), ingot    .mat(MT.AncientDebris, 1), NF, FL.Ender.make(L* 72), ZL_IS);
		RM.Massfab   .addRecipe2(T,  16, 144*648, ST.tag(0), blockDust.mat(MT.AncientDebris, 1), NF, FL.Ender.make(L*648), ZL_IS);
		RM.Replicator.addRecipe1(T,  16, 144    , ST.tag(0), FL.Ender.make(L), NF, gem.mat(MT.EnderPearl, 1));
		RM.Lightning .addRecipe1(T, 512, 512, blockSolid.mat(MT.Netherite, 1), FL.array(FL.Ender.make(L*4), FL.Oil_Soulsand.make(5000)), ZL_FS, gem.mat(MT.NetherStar, 1));
		
		
		
		
		RM.Fusion.addRecipe1(F, -8192,  730, ST.tag(1), FL.array(MT.D     .gas   (U*2, T)                        ), FL.array(MT.He_3  .gas   (  U2, F), MT.T     .gas   (  U2, F)                                                      ), ZL_IS             ).setSpecialNumber(  730L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192, 1130, ST.tag(1), FL.array(MT.T     .gas   (U*2, T)                        ), FL.array(MT.He    .gas   (  U , F)                                                                                 ), ZL_IS             ).setSpecialNumber( 1130L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192, 1290, ST.tag(1), FL.array(MT.He_3  .gas   (U*2, T)                        ), FL.array(MT.He    .gas   (  U , F)                                                                                 ), ZL_IS             ).setSpecialNumber( 1290L*8192L*16L);
		RM.Fusion.addRecipe1(F,     0, 1890, ST.tag(1), FL.array(MT.He    .gas   (U*2, T)                        ), FL.array(MT.Be_8  .liquid(  U , F)                                                                                 ), ZL_IS             ).setSpecialNumber( 1890L*8192L*16L);
		RM.Fusion.addRecipe1(F,     0, 3214, ST.tag(1), FL.array(MT.Be_8  .liquid(U*2, T)                        ), FL.array(MT.O     .gas   (  U , F)                                                                                 ), ZL_IS             ).setSpecialNumber( 3214L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192,  546, ST.tag(2), FL.array(MT.H     .gas   (U  , T), MT.B_11.liquid(U  , T)), FL.array(MT.He    .gas   (3*U , F)                                                                                 ), ZL_IS             ).setSpecialNumber( 8469L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192,  315, ST.tag(2), FL.array(MT.H     .gas   (U  , T), MT.C   .liquid(U  , T)), FL.array(MT.C_13  .liquid(  U , F)                                                                                 ), ZL_IS             ).setSpecialNumber(  315L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192,  754, ST.tag(2), FL.array(MT.H     .gas   (U  , T), MT.C_13.liquid(U  , T)), FL.array(MT.N     .gas   (  U , F)                                                                                 ), ZL_IS             ).setSpecialNumber(  754L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192, 1404, ST.tag(2), FL.array(MT.H     .gas   (U*2, T), MT.N   .gas   (U  , T)), FL.array(MT.He    .gas   (  U2, F), MT.C     .liquid(  U2, F), MT.O     .gas   (  U2, F)                           ), ZL_IS             ).setSpecialNumber( 1404L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192,  455, ST.tag(2), FL.array(MT.H     .gas   (U*2, T), MT.O   .gas   (U  , T)), FL.array(MT.He    .gas   (  U2, F), MT.F     .gas   (  U2, F), MT.N     .gas   (  U2, F)                           ), ZL_IS             ).setSpecialNumber(  455L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192, 1760, ST.tag(2), FL.array(MT.D     .gas   (U  , T), MT.T   .gas   (U  , T)), FL.array(MT.He    .gas   (  U , F)                                                                                 ), ZL_IS             ).setSpecialNumber( 1760L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192, 1830, ST.tag(2), FL.array(MT.D     .gas   (U  , T), MT.He_3.gas   (U  , T)), FL.array(MT.He    .gas   (  U , F)                                                                                 ), ZL_IS             ).setSpecialNumber( 1830L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192, 2640, ST.tag(2), FL.array(MT.T     .gas   (U  , T), MT.He_3.gas   (U  , T)), FL.array(MT.He    .gas   (3*U4, F), MT.D     .gas   (  U4, F)                                                      ), ZL_IS             ).setSpecialNumber( 2640L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192, 3336, ST.tag(2), FL.array(MT.D     .gas   (U  , T), MT.Li_6.liquid(U  , T)), FL.array(MT.He    .gas   (3*U8, F), MT.He_3  .gas   (  U8, F), MT.Li    .liquid(  U8, F), MT.Be_7  .liquid(  U8, F)), ZL_IS             ).setSpecialNumber( 3336L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192, 1690, ST.tag(2), FL.array(MT.He_3  .gas   (U  , T), MT.Li_6.liquid(U  , T)), FL.array(MT.He    .gas   (2*U , F)                                                                                 ), ZL_IS             ).setSpecialNumber( 1690L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192,  736, ST.tag(2), FL.array(MT.He    .gas   (U  , T), MT.Be_8.liquid(U  , T)), FL.array(MT.C     .liquid(  U , F)                                                                                 ), ZL_IS             ).setSpecialNumber(  736L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192,  716, ST.tag(2), FL.array(MT.He    .gas   (U  , T), MT.C   .liquid(U  , T)), FL.array(MT.O     .gas   (  U , F)                                                                                 ), ZL_IS             ).setSpecialNumber(  716L*8192L*16L);
		RM.Fusion.addRecipe1(F, -8192, 1956, ST.tag(2), FL.array(MT.Ad    .liquid(U  , T), MT.Be_7.liquid(U  , T)), FL.array(MT.W     .liquid(  U , F), MT.He    .gas   (16*U, F), MT.He_3  .gas   (24*U, F), MT.T     .gas   (24*U, F)), dust.mat(MT.Vb, 1)).setSpecialNumber(94956L*8192L*16L);
		
		
		for (OreDictMaterial tMaterial : OreDictMaterial.MATERIAL_ARRAY) if (tMaterial != null && tMaterial.mNeutrons+tMaterial.mProtons > 0 && tMaterial.contains(TD.Atomic.ELEMENT) && !tMaterial.contains(TD.Atomic.ANTIMATTER)) {
			ItemStack
			tInput = dust.mat(tMaterial, 1);          if (tInput != null) RM.Massfab.addRecipe1(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*131072  , tInput, NF, tMaterial.mProtons<1?NF:FL.MatterCharged.make(tMaterial.mProtons  ), tMaterial.mNeutrons<1?NF:FL.MatterNeutral.make(tMaterial.mNeutrons  ));
			tInput = ingot.mat(tMaterial, 1);         if (tInput != null) RM.Massfab.addRecipe1(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*131072  , tInput, NF, tMaterial.mProtons<1?NF:FL.MatterCharged.make(tMaterial.mProtons  ), tMaterial.mNeutrons<1?NF:FL.MatterNeutral.make(tMaterial.mNeutrons  ));
			tInput = plate.mat(tMaterial, 1);         if (tInput != null) RM.Massfab.addRecipe1(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*131072  , tInput, NF, tMaterial.mProtons<1?NF:FL.MatterCharged.make(tMaterial.mProtons  ), tMaterial.mNeutrons<1?NF:FL.MatterNeutral.make(tMaterial.mNeutrons  ));
			tInput = plateGem.mat(tMaterial, 1);      if (tInput != null) RM.Massfab.addRecipe1(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*131072  , tInput, NF, tMaterial.mProtons<1?NF:FL.MatterCharged.make(tMaterial.mProtons  ), tMaterial.mNeutrons<1?NF:FL.MatterNeutral.make(tMaterial.mNeutrons  ));
			tInput = gem.mat(tMaterial, 1);           if (tInput != null) RM.Massfab.addRecipe1(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*131072  , tInput, NF, tMaterial.mProtons<1?NF:FL.MatterCharged.make(tMaterial.mProtons  ), tMaterial.mNeutrons<1?NF:FL.MatterNeutral.make(tMaterial.mNeutrons  ));
			
			tInput = blockDust.mat(tMaterial, 1);     if (tInput != null) RM.Massfab.addRecipe1(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*131072*9, tInput, NF, tMaterial.mProtons<1?NF:FL.MatterCharged.make(tMaterial.mProtons*9), tMaterial.mNeutrons<1?NF:FL.MatterNeutral.make(tMaterial.mNeutrons*9));
			tInput = blockIngot.mat(tMaterial, 1);    if (tInput != null) RM.Massfab.addRecipe1(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*131072*9, tInput, NF, tMaterial.mProtons<1?NF:FL.MatterCharged.make(tMaterial.mProtons*9), tMaterial.mNeutrons<1?NF:FL.MatterNeutral.make(tMaterial.mNeutrons*9));
			tInput = blockPlate.mat(tMaterial, 1);    if (tInput != null) RM.Massfab.addRecipe1(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*131072*9, tInput, NF, tMaterial.mProtons<1?NF:FL.MatterCharged.make(tMaterial.mProtons*9), tMaterial.mNeutrons<1?NF:FL.MatterNeutral.make(tMaterial.mNeutrons*9));
			tInput = blockPlateGem.mat(tMaterial, 1); if (tInput != null) RM.Massfab.addRecipe1(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*131072*9, tInput, NF, tMaterial.mProtons<1?NF:FL.MatterCharged.make(tMaterial.mProtons*9), tMaterial.mNeutrons<1?NF:FL.MatterNeutral.make(tMaterial.mNeutrons*9));
			tInput = blockGem.mat(tMaterial, 1);      if (tInput != null) RM.Massfab.addRecipe1(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*131072*9, tInput, NF, tMaterial.mProtons<1?NF:FL.MatterCharged.make(tMaterial.mProtons*9), tMaterial.mNeutrons<1?NF:FL.MatterNeutral.make(tMaterial.mNeutrons*9));
			
			FluidStack
			tFluid = tMaterial.liquid(U, T); if (!FL.Error.is(tFluid)) RM.Massfab.addRecipe0(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*131072  , tFluid,     tMaterial.mProtons<1?NF:FL.MatterCharged.make(tMaterial.mProtons  ), tMaterial.mNeutrons<1?NF:FL.MatterNeutral.make(tMaterial.mNeutrons  ));
			tFluid = tMaterial.gas(U, T);    if (!FL.Error.is(tFluid)) RM.Massfab.addRecipe0(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*131072  , tFluid,     tMaterial.mProtons<1?NF:FL.MatterCharged.make(tMaterial.mProtons  ), tMaterial.mNeutrons<1?NF:FL.MatterNeutral.make(tMaterial.mNeutrons  ));
			tFluid = tMaterial.plasma(U, T); if (!FL.Error.is(tFluid)) RM.Massfab.addRecipe0(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*131072  , tFluid,     tMaterial.mProtons<1?NF:FL.MatterCharged.make(tMaterial.mProtons  ), tMaterial.mNeutrons<1?NF:FL.MatterNeutral.make(tMaterial.mNeutrons  ));
		}
	}
}
