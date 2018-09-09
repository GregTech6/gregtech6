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
import gregapi.oredict.IOreDictListenerEvent;
import gregapi.oredict.OreDictListenerEvent_Names;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Loader_Recipes_Other implements Runnable {
	@Override public void run() {OUT.println("GT_Mod: Doing GT Recipes for other things.");
		dust.addListener(new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {if (aEvent.mMaterial.mReRegistrations.contains(ANY.Wax)) CR.remove(aEvent.mStack);}});
		
		for (OreDictMaterial tWax : ANY.Wax.mToThis) {
		RM.Compressor.addRecipe1(T, 16, 16, OM.dust(tWax, U ), plate.mat(tWax, 1));
		RM.Compressor.addRecipe1(T, 16, 16, OM.dust(tWax, U4), foil .mat(tWax, 1));
		RM.ic2_compressor(OM.dust(tWax, U ), plate.mat(tWax, 1));
		RM.ic2_compressor(OM.dust(tWax, U4), foil .mat(tWax, 1));
		}
		RM.Mixer.addRecipe2(T, 16, 64, OM.dust(MT.Cu, 3*U ), OM.dust(MT.Sn    ), OM.dust(MT.Bronze, 3*U ));
		RM.Mixer.addRecipe2(T, 16, 64, OM.dust(MT.Cu, 3*U ), OM.dust(MT.Zn    ), OM.dust(MT.Brass , 3*U ));
		RM.Mixer.addRecipe2(T, 16, 64, OM.dust(MT.Cu, 3*U4), OM.dust(MT.Sn, U4), OM.dust(MT.Bronze, 3*U4));
		RM.Mixer.addRecipe2(T, 16, 64, OM.dust(MT.Cu, 3*U4), OM.dust(MT.Zn, U4), OM.dust(MT.Brass , 3*U4));
		RM.Mixer.addRecipe2(T, 16, 64, OM.dust(MT.Cu, 3*U9), OM.dust(MT.Sn, U9), OM.dust(MT.Bronze, 3*U9));
		RM.Mixer.addRecipe2(T, 16, 64, OM.dust(MT.Cu, 3*U9), OM.dust(MT.Zn, U9), OM.dust(MT.Brass , 3*U9));
		
		for (OreDictMaterial tSapphire : ANY.Sapphire.mToThis) {
		RM.Mixer.addRecipe2(T, 16,  144, OM.dust(tSapphire, 4*U ), OM.dust(MT.Redstone		, 5*U ), OM.dust(MT.EnergiumRed, 9*U));
		RM.Mixer.addRecipe2(T, 16,  144, OM.dust(tSapphire, 4*U9), OM.dust(MT.Redstone		, 5*U9), OM.dust(MT.EnergiumRed, U));
		RM.Mixer.addRecipe2(T, 16,  144, OM.dust(tSapphire, 4*U ), OM.dust(MT.Nikolite		, 5*U ), OM.dust(MT.EnergiumCyan, 9*U));
		RM.Mixer.addRecipe2(T, 16,  144, OM.dust(tSapphire, 4*U9), OM.dust(MT.Nikolite		, 5*U9), OM.dust(MT.EnergiumCyan, U));
		RM.Mixer.addRecipe2(T, 16,  144, OM.dust(tSapphire, 4*U ), OM.dust(MT.Teslatite		, 5*U ), OM.dust(MT.EnergiumCyan, 9*U));
		RM.Mixer.addRecipe2(T, 16,  144, OM.dust(tSapphire, 4*U9), OM.dust(MT.Teslatite		, 5*U9), OM.dust(MT.EnergiumCyan, U));
		RM.Mixer.addRecipe2(T, 16,  144, OM.dust(tSapphire, 4*U ), OM.dust(MT.Electrotine	, 5*U ), OM.dust(MT.EnergiumCyan, 9*U));
		RM.Mixer.addRecipe2(T, 16,  144, OM.dust(tSapphire, 4*U9), OM.dust(MT.Electrotine	, 5*U9), OM.dust(MT.EnergiumCyan, U));
		}
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		
		for (byte i = 0; i < 16; i++) {final int aIndex = i;
		addListener(DYE_OREDICTS_MIXABLE[aIndex], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (ST.container(aEvent.mStack, T) == null) {
				RM.Mixer.addRecipe1(T, 16, 16, aEvent.mStack, FL.Water.make(L), UT.Fluids.mul(DYE_FLUIDS_WATER[aIndex], 3, 2, F), ZL_IS);
				RM.Mixer.addRecipe1(T, 16, 16, aEvent.mStack, FL.DistW.make(L), UT.Fluids.mul(DYE_FLUIDS_WATER[aIndex], 3, 2, F), ZL_IS);
				
				ItemStack tStack = dust.mat(MT.DATA.Dye_Materials[aIndex], 1);
				if (!ST.equal(tStack, aEvent.mStack, T)) RM.generify(aEvent.mStack, tStack);
			}
		}});
		}
		
		addListener(DYE_OREDICTS_LENS[DYE_INDEX_Green], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.LaserEngraver	.addRecipe2(T,256,   64, plateGem.mat(MT.Diamond		, 1), ST.amount(0, aEvent.mStack), IL.Circuit_Crystal_Diamond.get(1));
			RM.LaserEngraver	.addRecipe2(T,256,   64, plateGem.mat(MT.Ruby			, 1), ST.amount(0, aEvent.mStack), IL.Circuit_Crystal_Ruby.get(1));
			for (OreDictMaterial tMat : ANY.Emerald.mToThis)
			RM.LaserEngraver	.addRecipe2(T,256,   64, plateGem.mat(tMat				, 1), ST.amount(0, aEvent.mStack), IL.Circuit_Crystal_Emerald.get(1));
			for (OreDictMaterial tMat : ANY.Sapphire.mToThis) if (tMat != MT.Ruby)
			RM.LaserEngraver	.addRecipe2(T,256,   64, plateGem.mat(tMat				, 1), ST.amount(0, aEvent.mStack), IL.Circuit_Crystal_Sapphire.get(1));
		}});
		addListener(DYE_OREDICTS_LENS[DYE_INDEX_Red], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			for (OreDictMaterial tMat : ANY.Cu.mToThis)
			RM.LaserEngraver	.addRecipe2(T, 16,   64, foil.mat(tMat					, 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Copper.get(1));
			RM.LaserEngraver	.addRecipe2(T, 16,   64, foil.mat(MT.Au					, 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Gold.get(1));
			RM.LaserEngraver	.addRecipe2(T, 16,   64, foil.mat(MT.Pt					, 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Platinum.get(1));
			RM.LaserEngraver	.addRecipe2(T, 16,   64, foil.mat(MT.Thaumium			, 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Magic.get(1));
			RM.LaserEngraver	.addRecipe2(T, 16,   64, foil.mat(MT.Manasteel			, 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Magic.get(1));
			RM.LaserEngraver	.addRecipe2(T, 16,   64, foil.mat(MT.Mithril			, 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Magic.get(1));
			RM.LaserEngraver	.addRecipe2(T, 16,   64, foil.mat(MT.Enderium			, 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Enderium.get(1));
			RM.LaserEngraver	.addRecipe2(T, 16,   64, foil.mat(MT.Signalum			, 4), ST.amount(0, aEvent.mStack), IL.Circuit_Wire_Signalum.get(1));
		}});
		
		
		addListener(dust.dat(MT.HydratedCoal)	, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, OM.dust(MT.Coal));}});
		addListener(rockGt.dat(MT.Netherrack)	, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {RM.add_smelting(aEvent.mStack, rockGt.mat(MT.NetherBrick, 1));}});
		
		
		addListener(new Object[] {OD.itemRubber, "ingotIron", "oreCoal", "oreIron", "oreGold", "oreRedstone", "oreLapis", "oreDiamond", "oreEmerald", "oreBauxite", "oreAluminium", "oreTitanium", "oreTungsten"}, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.rem_smelting(aEvent.mStack);
		}});
		addListener(OD.itemResin, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.rem_smelting(aEvent.mStack);
			RM.ic2_extractor(aEvent.mStack, OM.ingot(MT.Rubber, 7*U9));
			RM.Juicer			.addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Latex.make(L/2), NI);
			RM.Squeezer			.addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Latex.make(L  ), NI);
			RM.Centrifuge		.addRecipe1(T, 16,   64, aEvent.mStack, NF, FL.Latex.make(L  ), FL.Glue.make(250));
			RM.Mixer			.addRecipe1(T, 16,   16, aEvent.mStack, FL.Water.make(250), FL.Glue.make(250), ZL_IS);
			RM.Mixer			.addRecipe1(T, 16,   16, aEvent.mStack, FL.DistW.make(200), FL.Glue.make(250), ZL_IS);
			RM.Laminator		.addRecipe2(T, 16,   16, aEvent.mStack, ST.make(Blocks.piston, 1, W), ST.make(Blocks.sticky_piston, 1, 0));
		}});
		addListener(OD.itemClay, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {aEvent.mStack, OM.dust(MT.Redstone), OM.dust(MT.Basalz), OM.dust(MT.Obsidian)}, OM.dust(MT.Petrotheum, 2*U));
		}});
		addListener("blockClay", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer			.addRecipeX(T, 16,   64, new ItemStack[] {aEvent.mStack, OM.dust(MT.Redstone,U*4), OM.dust(MT.Basalz, U*4), OM.dust(MT.Obsidian, U*4)}, OM.dust(MT.Petrotheum, 8*U));
		}});
		addListener(OD.itemGrassDry, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Loom				.addRecipe2(T, 16,   16, ST.tag(10), ST.amount(7, aEvent.mStack), IL.Rope_Grass.get(1));
		}});
		addListener(OP.dustTiny.dat(ANY.Wood), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer			.addRecipe1(T, 16,   32, aEvent.mStack, MT.Glyceryl.fluid( U9, T), NF, OM.dust(MT.Dynamite,2*U9));
		}});
		addListener(OP.dustSmall.dat(ANY.Wood), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer			.addRecipe1(T, 16,   32, aEvent.mStack, MT.Glyceryl.fluid( U4, T), NF, OM.dust(MT.Dynamite,  U2));
		}});
		addListener(OP.dust.dat(ANY.Wood), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Mixer			.addRecipe1(T, 16,   32, aEvent.mStack, MT.Glyceryl.fluid( U , T), NF, OM.dust(MT.Dynamite,2*U ));
			RM.Bath				.addRecipe1(T,  0,   16, aEvent.mStack, FL.Water.make(125), NF, ST.make(Items.paper, 1, 0));
			RM.Bath				.addRecipe1(T,  0,   16, aEvent.mStack, FL.DistW.make(100), NF, ST.make(Items.paper, 1, 0));
		}});
		addListener(OP.stick.dat(ANY.WoodNormal), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Loom			.addRecipe2(T, 16,   16, ST.make(Blocks.wool	, 1, W), ST.amount(8, aEvent.mStack), ST.make(Items.painting, 1, 0));
			RM.Loom			.addRecipe2(T, 16,   16, ST.make(Items.leather	, 1, W), ST.amount(8, aEvent.mStack), ST.make(Items.item_frame, 1, 0));
		}});
		}};
		
		
		//----------------------------------------------------------------------------
		
		
		RM.Distillery	.addRecipe1(T, 16,   24, ST.tag(0)				, FL.Biomass		.make(  40), UT.Fluids.make("bioethanol"	,    12), FL.DistW.make(20));
		RM.Distillery	.addRecipe1(T, 16,   24, ST.tag(0)				, FL.BiomassIC2		.make(  40), UT.Fluids.make("bioethanol"	,    12), FL.DistW.make(20));
		RM.Distillery	.addRecipe1(T, 16,   24, ST.tag(1)				, FL.Biomass		.make(  40), MT.Glycerol			.liquid (U50, F), FL.DistW.make(20));
		RM.Distillery	.addRecipe1(T, 16,   24, ST.tag(1)				, FL.BiomassIC2		.make(  40), MT.Glycerol			.liquid (U50, F), FL.DistW.make(20));
		
		if (FL.Resin		.exists()) RM.Distillery.addRecipe1(T,T,F,F,F, 16, 16, ST.tag(0), FL.Resin			.make(10), UT.Fluids.make("turpentine", 6), FL.DistW.make(3));
		if (FL.Resin_Spruce	.exists()) RM.Distillery.addRecipe1(T,T,F,F,F, 16, 16, ST.tag(0), FL.Resin_Spruce	.make(10), UT.Fluids.make("turpentine", 6), FL.DistW.make(3));
		
		for (FluidStack tWater : new FluidStack[] {FL.Water.make(1000), FL.DistW.make(1000)}) {
		RM.Bath			.addRecipe1(T,  0,   16, OM.dust(MT.Coal)									, UT.Fluids.mul(tWater, 1, 8, T), NF, OM.dust(MT.HydratedCoal));
		
		RM.CryoMixer	.addRecipeX(T, 16,   32, new ItemStack[] {OM.dust(MT.Redstone		), OM.dust(MT.Blizz), OM.dust(MT.NaNO3)}, UT.Fluids.mul(tWater, 1, 4, T), NF, OM.dust(MT.Cryotheum, 2*U));
		RM.CryoMixer	.addRecipeX(T, 16,  128, new ItemStack[] {OM.dust(MT.Redstone	,U*4), OM.dust(MT.Blizz, U*4), OM.dust(MT.NaNO3, U*4)}, tWater, NF, OM.dust(MT.Cryotheum, 8*U));
		RM.CryoMixer	.addRecipeX(T, 16,   32, new ItemStack[] {OM.dust(MT.Redstone		), OM.dust(MT.Blizz), OM.dust(MT.KNO3)}, UT.Fluids.mul(tWater, 1, 4, T), NF, OM.dust(MT.Cryotheum, 2*U));
		RM.CryoMixer	.addRecipeX(T, 16,  128, new ItemStack[] {OM.dust(MT.Redstone	,U*4), OM.dust(MT.Blizz, U*4), OM.dust(MT.KNO3, U*4)}, tWater, NF, OM.dust(MT.Cryotheum, 8*U));
		
		RM.Mixer		.addRecipeX(T, 16,   64, new ItemStack[] {OP.gem .mat(MT.ChargedCertusQuartz, 1), OP.gem .mat(MT.NetherQuartz, 1), OP.dust.mat(MT.Redstone, 1)}, UT.Fluids.mul(tWater, 1, 2, T), NF, OP.gem .mat(MT.Fluix, 2));
		RM.Mixer		.addRecipeX(T, 16,   64, new ItemStack[] {OP.dust.mat(MT.ChargedCertusQuartz, 1), OP.dust.mat(MT.NetherQuartz, 1), OP.dust.mat(MT.Redstone, 1)}, UT.Fluids.mul(tWater, 1, 2, T), NF, OP.dust.mat(MT.Fluix, 2));
		
		RM.Mixer		.addRecipe1(T, 16,   16, OM.dust(MT.ConstructionFoam	)					, UT.Fluids.mul(tWater, 1,10, T), MT.ConstructionFoam.liquid(U, F), ZL_IS);
		
		for (OreDictMaterial tClay : ANY.Clay.mToThis) {
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Stone, MT.Concrete, MT.Gravel, MT.Soapstone, MT.Rhyolite, MT.Gneiss, MT.Shale, MT.Dolomite, MT.Chert, MT.Asbestos}) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), OM.dust(tMat		, U * 2), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), MT.ConstructionFoam.liquid(10*U, F), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), OM.dust(tMat		, U * 8), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), MT.ConstructionFoam.liquid(40*U, F), ZL_IS);
		}
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), ST.make(Blocks.sand	,  2, W), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), MT.ConstructionFoam.liquid(10*U, F), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), ST.make(Blocks.sand	,  8, W), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), MT.ConstructionFoam.liquid(40*U, F), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Diorite, MT.Marble, MT.Chalk, MT.Livingrock, MT.Holystone}) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), OM.dust(tMat		, U * 2), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_White], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), OM.dust(tMat		, U * 8), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_White], 40), ZL_IS);
		}
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), ST.make(Blocks.sand	,  2, W), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_White], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), ST.make(Blocks.sand	,  8, W), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_White], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Basalt, MT.Gabbro}) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), OM.dust(tMat		, U * 2), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Black], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), OM.dust(tMat		, U * 8), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Black], 40), ZL_IS);
		}
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), ST.make(Blocks.sand	,  2, W), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Black], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), ST.make(Blocks.sand	,  8, W), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Black], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Migmatite, MT.Eclogite, MT.SpaceRock}) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), OM.dust(tMat		, U * 2), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Gray], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), OM.dust(tMat		, U * 8), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Gray], 40), ZL_IS);
		}
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), ST.make(Blocks.sand	,  2, W), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Gray], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), ST.make(Blocks.sand	,  8, W), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Gray], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Andesite, MT.Dacite, MT.Greywacke, MT.MoonRock, MT.MoonTurf}) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), OM.dust(tMat		, U * 2), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_LightGray], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), OM.dust(tMat		, U * 8), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_LightGray], 40), ZL_IS);
		}
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), ST.make(Blocks.sand	,  2, W), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_LightGray], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), ST.make(Blocks.sand	,  8, W), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_LightGray], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Blueschist}) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), OM.dust(tMat		, U * 2), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_LightBlue], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), OM.dust(tMat		, U * 8), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_LightBlue], 40), ZL_IS);
		}
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), ST.make(Blocks.sand	,  2, W), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_LightBlue], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), ST.make(Blocks.sand	,  8, W), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_LightBlue], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Greenschist, MT.Betweenstone}) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), OM.dust(tMat		, U * 2), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Lime], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), OM.dust(tMat		, U * 8), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Lime], 40), ZL_IS);
		}
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), ST.make(Blocks.sand	,  2, W), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Lime], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), ST.make(Blocks.sand	,  8, W), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Lime], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Pitstone}) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), OM.dust(tMat		, U * 2), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Green], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), OM.dust(tMat		, U * 8), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Green], 40), ZL_IS);
		}
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), ST.make(Blocks.sand	,  2, W), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Green], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), ST.make(Blocks.sand	,  8, W), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Green], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Redrock, MT.MarsRock, MT.MarsSand}) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), OM.dust(tMat		, U * 2), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Red], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), OM.dust(tMat		, U * 8), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Red], 40), ZL_IS);
		}
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), ST.make(Blocks.sand	,  2, W), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Red], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), ST.make(Blocks.sand	,  8, W), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Red], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Komatiite}) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), OM.dust(tMat		, U * 2), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Yellow], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), OM.dust(tMat		, U * 8), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Yellow], 40), ZL_IS);
		}
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), ST.make(Blocks.sand	,  2, W), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Yellow], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), ST.make(Blocks.sand	,  8, W), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Yellow], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Limestone}) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), OM.dust(tMat		, U * 2), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Orange], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), OM.dust(tMat		, U * 8), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Orange], 40), ZL_IS);
		}
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), ST.make(Blocks.sand	,  2, W), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Orange], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), ST.make(Blocks.sand	,  8, W), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Orange], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Quartzite, MT.Siltstone}) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), OM.dust(tMat		, U * 2), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Pink], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), OM.dust(tMat		, U * 8), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Pink], 40), ZL_IS);
		}
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), ST.make(Blocks.sand	,  2, W), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Pink], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), ST.make(Blocks.sand	,  8, W), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Pink], 40), ZL_IS);
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Umber, MT.Kimberlite}) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), OM.dust(tMat		, U * 2), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Brown], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), OM.dust(tMat		, U * 8), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Brown], 40), ZL_IS);
		}
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U* 6), ST.make(Blocks.sand	,  2, W), OM.dust(tClay, U4)}, UT.Fluids.mul(tWater, 1), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Brown], 10), ZL_IS);
		RM.Mixer.addRecipeX(T, 16,  256, new ItemStack[] {OM.dust(tRock, U*24), ST.make(Blocks.sand	,  8, W), OM.dust(tClay, U )}, UT.Fluids.mul(tWater, 4), UT.Fluids.mul(DYED_C_FOAMS[DYE_INDEX_Brown], 40), ZL_IS);
		}
		}
		
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(MT.Indigo	, U9), UT.Fluids.mul(tWater,   L  , 1000, T), UT.Fluids.make("indigo",   L  ), ZL_IS);
		RM.Mixer			.addRecipe1(T, 16,   36, OM.dust(MT.Indigo	, U4), UT.Fluids.mul(tWater, 9*L/4, 1000, T), UT.Fluids.make("indigo", 9*L/4), ZL_IS);
		RM.Mixer			.addRecipe1(T, 16,  144, OM.dust(MT.Indigo		), UT.Fluids.mul(tWater, 9*L  , 1000, T), UT.Fluids.make("indigo", 9*L  ), ZL_IS);
		
		// Concrete
		RM.Mixer			.addRecipe1(T, 16,  144, OP.blockDust	.mat(MT.Concrete, 1), UT.Fluids.mul(tWater, 9, 2, T), FL.Concrete.make(9*L), ZL_IS);
		RM.Mixer			.addRecipe1(T, 16,   16, OP.dust		.mat(MT.Concrete, 1), UT.Fluids.mul(tWater, 1, 2, T), FL.Concrete.make(  L), ZL_IS);
		}
		for (OreDictMaterial tRock : ANY.Stone.mToThis) for (OreDictMaterial tCalcite : ANY.Calcite.mToThis) if (tRock != MT.Concrete && !tRock.mReRegistrations.contains(ANY.Calcite)) for (OreDictMaterial tAsh : ANY.Ash.mToThis) {
		RM.Mixer			.addRecipeX(T, 16, 1296, new ItemStack[] {OP.blockDust	.mat(tRock, 9), OP.blockDust.mat(tCalcite, 1), OP.blockDust	.mat(tAsh, 1)}, OP.blockDust.mat(MT.Concrete,11));
		RM.Mixer			.addRecipeX(T, 16, 1296, new ItemStack[] {OP.blockDust	.mat(tRock, 9), OP.blockDust.mat(tCalcite, 1), OP.dust		.mat(tAsh, 9)}, OP.blockDust.mat(MT.Concrete,11));
		RM.Mixer			.addRecipeX(T, 16, 1296, new ItemStack[] {OP.blockDust	.mat(tRock, 9), OP.dust		.mat(tCalcite, 9), OP.blockDust	.mat(tAsh, 1)}, OP.blockDust.mat(MT.Concrete,11));
		RM.Mixer			.addRecipeX(T, 16,  144, new ItemStack[] {OP.blockDust	.mat(tRock, 1), OP.dust		.mat(tCalcite, 1), OP.dust		.mat(tAsh, 1)}, OP.dust		.mat(MT.Concrete,11));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OP.dust		.mat(tRock, 9), OP.dust		.mat(tCalcite, 1), OP.dust		.mat(tAsh, 1)}, OP.dust		.mat(MT.Concrete,11));
		}
		for (OreDictMaterial tIron : ANY.Iron.mToThis)
		RM.Drying			.addRecipe1(T, 16,   16, OP.stick.mat(tIron, 1)	, FL.Concrete.make(L), FL.DistW.make(8), ST.make(BlocksGT.ConcreteReinforced	, 1, DYE_INDEX_LightGray));
		RM.Drying			.addRecipe1(T, 16,   16, ST.tag(0)				, FL.Concrete.make(L), FL.DistW.make(8), ST.make(BlocksGT.Concrete				, 1, DYE_INDEX_LightGray));
		
		// Asphalt
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Stone, MT.Concrete, MT.Gravel, MT.Soapstone, MT.Rhyolite, MT.Gneiss, MT.Shale, MT.Dolomite, MT.Chert})
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Gray));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Diorite, MT.Marble, MT.Chalk, MT.Livingrock, MT.Holystone})
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_White));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Basalt, MT.Gabbro})
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Black));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Migmatite, MT.Eclogite, MT.SpaceRock})
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Gray));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Andesite, MT.Dacite, MT.Greywacke, MT.MoonRock, MT.MoonTurf})
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_LightGray));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Blueschist})
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_LightBlue));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Greenschist, MT.Betweenstone})
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Lime));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Pitstone})
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Lime));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Redrock, MT.MarsRock, MT.MarsSand})
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Red));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Komatiite})
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Yellow));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Limestone})
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Orange));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Quartzite, MT.Siltstone})
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Pink));
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Umber, MT.Kimberlite})
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(tRock), MT.Asphalt.liquid(U, T), NF, ST.make(BlocksGT.Asphalt, 1, DYE_INDEX_Brown));
		
		RM.Fermenter		.addRecipe1(T, 16,   24, ST.tag(0), FL.Biomass		.make(40), UT.Fluids.make("methane", 24), ZL_IS);
		RM.Fermenter		.addRecipe1(T, 16,   24, ST.tag(0), FL.BiomassIC2	.make(40), UT.Fluids.make("methane", 24), ZL_IS);
		
		ItemStack x, y;
		CR.remove(x = dust.mat(MT.S, 1), x, x, x, ST.make(Items.coal, 1, 0), x, x, x, x);
		CR.remove(x, x, x, x, ST.make(Items.coal, 1, 1), x, x, x, x);
		CR.remove(y = dust.mat(MT.KNO3, 1), y, x, dust.mat(MT.Charcoal, 1));
		CR.remove(y, y, x, dust.mat(MT.Coal, 1));
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
		CR.remove(dust.mat(MT.Au, 1), dust.mat(MT.Ag, 1));
		CR.remove(x = dust.mat(MT.Clay, 1), x, dust.mat(MT.PotassiumFeldspar, 1), dust.mat(MT.SiO2, 1));
		CR.remove(OP.dust.mat(MT.Redstone, 1), OP.dust.mat(MT.Teslatite, 1));
		
		//----------------------------------------------------------------------------
		
		RM.pack(rockGt.mat(MT.Stone			, 4), ST.make(Blocks.cobblestone, 1, 0));
		RM.pack(rockGt.mat(MT.Netherrack	, 4), ST.make(Blocks.netherrack, 1, 0));
		RM.pack(rockGt.mat(MT.Endstone		, 4), ST.make(Blocks.end_stone, 1, 0));
		
		CR.shaped(ST.make(Blocks.cobblestone			,1,0), CR.DEF_NAC	, "XX", "XX", 'X', rockGt.dat(MT.Stone));
		CR.shaped(ST.make(Blocks.netherrack				,1,0), CR.DEF_NAC	, "XX", "XX", 'X', rockGt.dat(MT.Netherrack));
		CR.shaped(ST.make(Blocks.end_stone				,1,0), CR.DEF_NAC	, "XX", "XX", 'X', rockGt.dat(MT.Endstone));
		
		CR.shaped(stick			.mat(MT.PetrifiedWood	,  1), CR.DEF_NAC	, "X" , "X" , 'X', OP.rockGt.dat(MT.PetrifiedWood));
		CR.shaped(plate			.mat(MT.PetrifiedWood	,  1), CR.DEF_NAC	, "XX", "XX", 'X', OP.rockGt.dat(MT.PetrifiedWood));
		CR.shaped(plateTiny		.mat(MT.Paper			,  9), CR.DEF_NAC	, "b ", " X", 'X', plate.dat(MT.Paper));
		CR.shaped(plateTiny		.mat(MT.Paper			,  9), CR.DEF_NAC	, "q ", " X", 'X', plate.dat(MT.Paper));
		for (OreDictMaterial tWax : ANY.Wax.mToThis) {
		CR.shaped(ring			.mat(tWax				,  1), CR.DEF_NAC	, "k", "X", 'X', plate.dat(tWax));
		CR.shaped(casingSmall	.mat(tWax				,  1), CR.DEF_NAC	, "X", "k", 'X', plate.dat(tWax));
		}
		CR.shaped(ring			.mat(MT.Rubber			,  1), CR.DEF_NAC	, "k", "X", 'X', plate.dat(MT.Rubber));
		CR.shaped(casingSmall	.mat(MT.Rubber			,  1), CR.DEF_NAC	, "X", "k", 'X', plate.dat(MT.Rubber));
		CR.shaped(gearGt		.mat(MT.Wood			,  1), CR.DEF_NAC	, "SPS", "PsP", "SPS", 'P', OD.plankWood, 'S', ST.make(Blocks.wooden_button, 1, W));
		CR.shaped(gearGt		.mat(MT.Stone			,  1), CR.DEF_NAC	, "SPS", "PfP", "SPS", 'P', stoneSmooth, 'S', ST.make(Blocks.stone_button, 1, W));
		CR.shaped(gearGt		.mat(MT.Stone			,  1), CR.DEF_NAC	, "SPS", "PfP", "SPS", 'P', stoneSmooth, 'S', OP.rockGt.dat(MT.Stone));
		CR.shaped(gearGt		.mat(MT.Stone			,  1), CR.DEF_NAC	, "SPS", "PfP", "SPS", 'P', stoneSmooth, 'S', OP.stick.dat(MT.Stone));
		CR.shaped(gearGtSmall	.mat(MT.Stone			,  1), CR.DEF_NAC	, "P ", " f", 'P', stoneSmooth);
		
		RM.Slicer			.addRecipe2(T, 16,   16, ST.make(Items.paper, 1, W), IL.Shape_Slicer_Grid.get(0), plateTiny.mat(MT.Paper, 9));
		
		
		if (FL.Mana_TE.exists()) {
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(Blocks.dirt				, 1, 1), FL.Mana_TE.make(1), NF, ST.make(Blocks.dirt, 1, 2));
		if (IL.EtFu_Dirt.exists())
		RM.Bath				.addRecipe1(T,  0,   16, IL.EtFu_Dirt						.get(1), FL.Mana_TE.make(1), NF, ST.make(Blocks.dirt, 1, 2));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(Blocks.dirt				, 1, 0), FL.Mana_TE.make(1), NF, ST.make(Blocks.grass, 1, 0));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(Blocks.dirt				, 1, 2), FL.Mana_TE.make(1), NF, ST.make(Blocks.mycelium, 1, 0));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(Blocks.glass				, 1, W), FL.Mana_TE.make(1), NF, ST.make(Blocks.sand, 1, 0));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(Blocks.stained_glass		, 1, W), FL.Mana_TE.make(1), NF, ST.make(Blocks.sand, 1, 0));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(Blocks.cobblestone			, 1, W), FL.Mana_TE.make(1), NF, ST.make(Blocks.mossy_cobblestone, 1, 0));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(Blocks.stonebrick			, 1, 0), FL.Mana_TE.make(1), NF, ST.make(Blocks.stonebrick, 1, 1));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(Blocks.stone				, 1, 0), FL.Mana_TE.make(1), NF, ST.make(Blocks.stonebrick, 1, 3));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(Blocks.lapis_ore			, 1, W), FL.Mana_TE.make(5), NF, ST.make(Blocks.lapis_block, 1, 0));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(Blocks.redstone_ore		, 1, W), FL.Mana_TE.make(5), NF, ST.make(Blocks.redstone_block, 1, 0));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(Blocks.lit_redstone_ore	, 1, W), FL.Mana_TE.make(5), NF, ST.make(Blocks.redstone_block, 1, 0));
		}
		
		RM.Bath				.addRecipe2(T,  0,   16, ST.make(Items.paper, 2, W), ST.tag(2), FL.Glue.make( 125), NF, plateDouble.mat(MT.Paper, 1));
		RM.Bath				.addRecipe2(T,  0,   32, ST.make(Items.paper, 3, W), ST.tag(3), FL.Glue.make( 250), NF, plateTriple.mat(MT.Paper, 1));
		RM.Bath				.addRecipe2(T,  0,   48, ST.make(Items.paper, 4, W), ST.tag(4), FL.Glue.make( 375), NF, plateQuadruple.mat(MT.Paper, 1));
		RM.Bath				.addRecipe2(T,  0,   64, ST.make(Items.paper, 5, W), ST.tag(5), FL.Glue.make( 500), NF, plateQuintuple.mat(MT.Paper, 1));
//		RM.Bath				.addRecipe2(T,  0,  128, ST.make(Items.paper, 9, W), ST.tag(9), FL.Glue.make(1000), NF, plateDense.mat(MT.Paper, 1));
		
		
		
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
		
		for (byte i = 0; i < 16; i++) for (FluidStack tDye : DYE_FLUIDS[i]) {
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(BlocksGT.CFoam												, 1, W), UT.Fluids.mul(tDye, 1, 24, T), NF, ST.make(BlocksGT.CFoam											, 1, i));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(BlocksGT.Asphalt											, 1, W), UT.Fluids.mul(tDye, 1, 24, T), NF, ST.make(BlocksGT.Asphalt										, 1, i));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(BlocksGT.Concrete											, 1, W), UT.Fluids.mul(tDye, 1, 24, T), NF, ST.make(BlocksGT.Concrete										, 1, i));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(BlocksGT.ConcreteReinforced								, 1, W), UT.Fluids.mul(tDye, 1, 24, T), NF, ST.make(BlocksGT.ConcreteReinforced								, 1, i));
		
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(((BlockMetaType)BlocksGT.CFoam					).mSlabs[0]	, 1, W), UT.Fluids.mul(tDye, 1, 48, T), NF, ST.make(((BlockMetaType)BlocksGT.CFoam				).mSlabs[0]	, 1, i));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(((BlockMetaType)BlocksGT.Asphalt				).mSlabs[0]	, 1, W), UT.Fluids.mul(tDye, 1, 48, T), NF, ST.make(((BlockMetaType)BlocksGT.Asphalt			).mSlabs[0]	, 1, i));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(((BlockMetaType)BlocksGT.Concrete				).mSlabs[0]	, 1, W), UT.Fluids.mul(tDye, 1, 48, T), NF, ST.make(((BlockMetaType)BlocksGT.Concrete			).mSlabs[0]	, 1, i));
		RM.Bath				.addRecipe1(T,  0,   16, ST.make(((BlockMetaType)BlocksGT.ConcreteReinforced	).mSlabs[0]	, 1, W), UT.Fluids.mul(tDye, 1, 48, T), NF, ST.make(((BlockMetaType)BlocksGT.ConcreteReinforced	).mSlabs[0]	, 1, i));
		
		if (tRegistry != null) for (byte j = 0; j < 16; j++) {
		RM.Bath				.addRecipe1(T,  0,   16, tRegistry.getItem(32452+j), UT.Fluids.mul(tDye, 1,144, T), NF, tRegistry.getItem(32452+i));
		RM.Bath				.addRecipe1(T,  0,   16, tRegistry.getItem(32468+j), UT.Fluids.mul(tDye, 1,144, T), NF, tRegistry.getItem(32468+i));
		RM.Bath				.addRecipe1(T,  0,   16, tRegistry.getItem(32484+j), UT.Fluids.mul(tDye, 1,144, T), NF, tRegistry.getItem(32484+i));
		}
		}
		
		
		
		for (byte i = 0; i < 16; i++) {
		for (FluidStack tDye : DYE_FLUIDS[i]) if (tDye.getFluid() != DYE_FLUIDS_CHEMICAL[i].getFluid()) {
		RM.Mixer			.addRecipe0(T, 16,   16, new FluidStack[] {UT.Fluids.mul(tDye, 3, 2, T), MT.SunflowerOil	.liquid(U50, T)}, UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[i], 2), ZL_IS);
		RM.Mixer			.addRecipe0(T, 16,   16, new FluidStack[] {UT.Fluids.mul(tDye, 3, 2, T), MT.OliveOil		.liquid(U50, T)}, UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[i], 2), ZL_IS);
		RM.Mixer			.addRecipe0(T, 16,   16, new FluidStack[] {UT.Fluids.mul(tDye, 3, 2, T), MT.NutOil			.liquid(U50, T)}, UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[i], 2), ZL_IS);
		RM.Mixer			.addRecipe0(T, 16,   16, new FluidStack[] {UT.Fluids.mul(tDye, 3, 2, T), MT.SeedOil			.liquid(U50, T)}, UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[i], 2), ZL_IS);
		RM.Mixer			.addRecipe0(T, 16,   16, new FluidStack[] {UT.Fluids.mul(tDye, 3, 2, T), MT.LinOil			.liquid(U50, T)}, UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[i], 2), ZL_IS);
		RM.Mixer			.addRecipe0(T, 16,   16, new FluidStack[] {UT.Fluids.mul(tDye, 3, 2, T), MT.HempOil			.liquid(U50, T)}, UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[i], 2), ZL_IS);
		}
		RM.Mixer			.addRecipe0(T, 16,   16, new FluidStack[] {UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[i], 1, 9, T), MT.ConstructionFoam.liquid(U, T)}, DYED_C_FOAMS[i], ZL_IS);
		RM.Mixer			.addRecipe1(T, 16,   16, OM.dust(MT.Pd, U4), DYED_C_FOAMS[i], DYED_C_FOAMS_OWNED[i], ZL_IS);
		RM.Mixer			.addRecipe1(T, 16,   64, OM.dust(MT.Pd), UT.Fluids.mul(DYED_C_FOAMS[i], 4), UT.Fluids.mul(DYED_C_FOAMS_OWNED[i], 4), ZL_IS);
		}
		
		for (OreDictMaterial tClay : ANY.Clay.mToThis) {
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {
		RM.Mixer			.addRecipeX(T, 16,   64, new ItemStack[] {OM.dust(tClay			,U*2), OM.dust(tMat    ), OM.dust(MT.PotassiumFeldspar    )}, OM.dust(MT.Porcelain, U*4));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(tClay			, U2), OM.dust(tMat, U4), OM.dust(MT.PotassiumFeldspar, U4)}, OM.dust(MT.Porcelain     ));
		}
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Redstone		), OM.dust(MT.Basalz), OM.dust(tClay), OM.dust(MT.Obsidian)}, OM.dust(MT.Petrotheum, 2*U));
		}
		for (OreDictMaterial tGlowstone : ANY.Glowstone.mToThis) {
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Stone			), OM.dust(MT.Netherrack	), OM.dust(MT.Redstone		), OM.dust(tGlowstone	)}, OM.dust(MT.ArcaneCompound, U2));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Stone		, U4), OM.dust(MT.Netherrack, U4), OM.dust(MT.Redstone	, U4), OM.dust(tGlowstone,U4)}, OM.dust(MT.ArcaneCompound, U8));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Stone		, U9), OM.dust(MT.Netherrack, U9), OM.dust(MT.Redstone	, U9), OM.dust(tGlowstone,U9)}, OM.dust(MT.ArcaneCompound, U18));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Gravel			), OM.dust(MT.Netherrack	), OM.dust(MT.Redstone		), OM.dust(tGlowstone	)}, OM.dust(MT.ArcaneCompound, U2));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Gravel		, U4), OM.dust(MT.Netherrack, U4), OM.dust(MT.Redstone	, U4), OM.dust(tGlowstone,U4)}, OM.dust(MT.ArcaneCompound, U8));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Gravel		, U9), OM.dust(MT.Netherrack, U9), OM.dust(MT.Redstone	, U9), OM.dust(tGlowstone,U9)}, OM.dust(MT.ArcaneCompound, U18));
		}
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Ag				), OM.dust(MT.I    )}, OM.dust(MT.AgI, 2*U  ));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Ag			, U4), OM.dust(MT.I, U4)}, OM.dust(MT.AgI, 2* U4));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Ag			, U9), OM.dust(MT.I, U9)}, OM.dust(MT.AgI, 2* U9));
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {ItemStack tDust = OP.dust.mat(tMat, 1), tGem = OP.gem.mat(tMat, 1);
		if (ST.valid(tDust)) {
		RM.Mixer			.addRecipe2(T, 16,   16, OM.dust(MT.C			,U*2), tDust, OM.dust(MT.BlackQuartz));
		RM.Mixer			.addRecipe2(T, 16,   16, OM.dust(MT.Coal			), tDust, OM.dust(MT.BlackQuartz));
		RM.Mixer			.addRecipe2(T, 16,   16, OM.dust(MT.Charcoal		), tDust, OM.dust(MT.BlackQuartz));
		}
		if (ST.valid(tGem)) {
		RM.Mixer			.addRecipe2(T, 16,   16, OM.dust(MT.C			,U*2), tGem, OM.dust(MT.BlackQuartz));
		RM.Mixer			.addRecipe2(T, 16,   16, OM.dust(MT.Coal			), tGem, OM.dust(MT.BlackQuartz));
		RM.Mixer			.addRecipe2(T, 16,   16, OM.dust(MT.Charcoal		), tGem, OM.dust(MT.BlackQuartz));
		}}
		
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.C			,U*2), OM.dust(MT.S    ), OM.dust(MT.NaNO3    )}, OM.dust(MT.Gunpowder, 3*U  ));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Coal			), OM.dust(MT.S    ), OM.dust(MT.NaNO3    )}, OM.dust(MT.Gunpowder, 3*U  ));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Charcoal		), OM.dust(MT.S    ), OM.dust(MT.NaNO3    )}, OM.dust(MT.Gunpowder, 2*U  ));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.C		  ,2* U4), OM.dust(MT.S, U4), OM.dust(MT.NaNO3, U4)}, OM.dust(MT.Gunpowder, 3* U4));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Coal		, U4), OM.dust(MT.S, U4), OM.dust(MT.NaNO3, U4)}, OM.dust(MT.Gunpowder, 3* U4));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Charcoal	, U4), OM.dust(MT.S, U4), OM.dust(MT.NaNO3, U4)}, OM.dust(MT.Gunpowder,    U2));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.C		  ,2* U9), OM.dust(MT.S, U9), OM.dust(MT.NaNO3, U9)}, OM.dust(MT.Gunpowder,    U3));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Coal		, U9), OM.dust(MT.S, U9), OM.dust(MT.NaNO3, U9)}, OM.dust(MT.Gunpowder,    U3));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Charcoal	, U9), OM.dust(MT.S, U9), OM.dust(MT.NaNO3, U9)}, OM.dust(MT.Gunpowder, 2* U9));
		for (OreDictMaterial tMat : ANY.SiO2.mToThis)
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Redstone		), OM.dust(MT.Blitz), OM.dust(MT.NaNO3), OM.dust(tMat)}, OM.dust(MT.Aerotheum, 2*U));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Redstone		), OM.dust(MT.Blitz), OM.dust(MT.NaNO3), ST.make(Blocks.sand, 1, W)}, OM.dust(MT.Aerotheum, 2*U));
		
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.C			,U*2), OM.dust(MT.S    ), OM.dust(MT.KNO3     )}, OM.dust(MT.Gunpowder, 3*U  ));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Coal			), OM.dust(MT.S    ), OM.dust(MT.KNO3     )}, OM.dust(MT.Gunpowder, 3*U  ));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Charcoal		), OM.dust(MT.S    ), OM.dust(MT.KNO3     )}, OM.dust(MT.Gunpowder, 2*U  ));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.C		  ,2* U4), OM.dust(MT.S, U4), OM.dust(MT.KNO3 , U4)}, OM.dust(MT.Gunpowder, 3* U4));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Coal		, U4), OM.dust(MT.S, U4), OM.dust(MT.KNO3 , U4)}, OM.dust(MT.Gunpowder, 3* U4));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Charcoal	, U4), OM.dust(MT.S, U4), OM.dust(MT.KNO3 , U4)}, OM.dust(MT.Gunpowder,    U2));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.C		  ,2* U9), OM.dust(MT.S, U9), OM.dust(MT.KNO3 , U9)}, OM.dust(MT.Gunpowder,    U3));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Coal		, U9), OM.dust(MT.S, U9), OM.dust(MT.KNO3 , U9)}, OM.dust(MT.Gunpowder,    U3));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Charcoal	, U9), OM.dust(MT.S, U9), OM.dust(MT.KNO3 , U9)}, OM.dust(MT.Gunpowder, 2* U9));
		for (OreDictMaterial tMat : ANY.SiO2.mToThis)
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Redstone		), OM.dust(MT.Blitz), OM.dust(MT.KNO3 ), OM.dust(tMat)}, OM.dust(MT.Aerotheum, 2*U));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Redstone		), OM.dust(MT.Blitz), OM.dust(MT.KNO3 ), ST.make(Blocks.sand, 1, W)}, OM.dust(MT.Aerotheum, 2*U));
		
		
		
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Redstone		), OM.dust(MT.Blaze), OM.dust(MT.Coal), OM.dust(MT.S)}, OM.dust(MT.Pyrotheum, 2*U));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Redstone		), OM.dust(MT.Blizz), ST.make(Items.snowball, 1, W), OM.dust(MT.NaNO3)}, OM.dust(MT.Cryotheum, 2*U));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Redstone		), OM.dust(MT.Blizz), OM.dust(MT.Snow,  U4), OM.dust(MT.NaNO3)}, OM.dust(MT.Cryotheum, 2*U));
		RM.Mixer			.addRecipeX(T, 16,   64, new ItemStack[] {OM.dust(MT.Redstone	,U*4), OM.dust(MT.Blizz, U*4), ST.make(Blocks.snow, 1, W), OM.dust(MT.NaNO3, U*4)}, OM.dust(MT.Cryotheum, 8*U));
		RM.Mixer			.addRecipeX(T, 16,   64, new ItemStack[] {OM.dust(MT.Redstone	,U*4), OM.dust(MT.Blizz, U*4), OM.dust(MT.Snow), OM.dust(MT.NaNO3, U*4)}, OM.dust(MT.Cryotheum, 8*U));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Redstone		), OM.dust(MT.Blizz), ST.make(Items.snowball, 1, W), OM.dust(MT.KNO3)}, OM.dust(MT.Cryotheum, 2*U));
		RM.Mixer			.addRecipeX(T, 16,   16, new ItemStack[] {OM.dust(MT.Redstone		), OM.dust(MT.Blizz), OM.dust(MT.Snow,  U4), OM.dust(MT.KNO3)}, OM.dust(MT.Cryotheum, 2*U));
		RM.Mixer			.addRecipeX(T, 16,   64, new ItemStack[] {OM.dust(MT.Redstone	,U*4), OM.dust(MT.Blizz, U*4), ST.make(Blocks.snow, 1, W), OM.dust(MT.KNO3, U*4)}, OM.dust(MT.Cryotheum, 8*U));
		RM.Mixer			.addRecipeX(T, 16,   64, new ItemStack[] {OM.dust(MT.Redstone	,U*4), OM.dust(MT.Blizz, U*4), OM.dust(MT.Snow), OM.dust(MT.KNO3, U*4)}, OM.dust(MT.Cryotheum, 8*U));
		RM.Mixer			.addRecipeX(T, 16,   32, new ItemStack[] {OM.dust(MT.Fe				), OM.dust(MT.LiveRoot), OM.dust(MT.Au,  U9)}, OM.dust(MT.IronWood, U*2));
		RM.Mixer			.addRecipe2(T, 16,   32, OM.dust(MT.GildedIron			), OM.dust(MT.LiveRoot  ), OM.dust(MT.IronWood,   U*2));
		RM.Mixer			.addRecipe2(T, 16,   64, OM.dust(MT.Bi					), OM.dust(MT.Brass, 4*U  ), OM.dust(MT.BismuthBronze, 5*U2));
		RM.Mixer			.addRecipe2(T, 16,   32, OM.dust(MT.Bi				, U4), OM.dust(MT.Brass,   U  ), OM.dust(MT.BismuthBronze, 5*U8));
		RM.Mixer			.addRecipe2(T, 16,   16, OM.dust(MT.Bi				, U9), OM.dust(MT.Brass, 4* U9), OM.dust(MT.BismuthBronze, 5*U18));
		
		// Dyes
		for (FluidStack[] tDyes : new FluidStack[][] {DYE_FLUIDS_WATER, DYE_FLUIDS_FLOWER, DYE_FLUIDS_CHEMICAL}) {
		RM.Mixer			.addRecipe0(T, 16, 64, new FluidStack[] {tDyes[DYE_INDEX_Red	], tDyes[DYE_INDEX_Blue		]}, UT.Fluids.mul(tDyes[DYE_INDEX_Purple	], 2), ZL_IS);
		RM.Mixer			.addRecipe0(T, 16, 64, new FluidStack[] {tDyes[DYE_INDEX_Green	], tDyes[DYE_INDEX_Blue		]}, UT.Fluids.mul(tDyes[DYE_INDEX_Cyan		], 2), ZL_IS);
		RM.Mixer			.addRecipe0(T, 16, 64, new FluidStack[] {tDyes[DYE_INDEX_Red	], tDyes[DYE_INDEX_White	]}, UT.Fluids.mul(tDyes[DYE_INDEX_Pink		], 2), ZL_IS);
		RM.Mixer			.addRecipe0(T, 16, 64, new FluidStack[] {tDyes[DYE_INDEX_Green	], tDyes[DYE_INDEX_White	]}, UT.Fluids.mul(tDyes[DYE_INDEX_Lime		], 2), ZL_IS);
		RM.Mixer			.addRecipe0(T, 16, 64, new FluidStack[] {tDyes[DYE_INDEX_Blue	], tDyes[DYE_INDEX_White	]}, UT.Fluids.mul(tDyes[DYE_INDEX_LightBlue	], 2), ZL_IS);
		RM.Mixer			.addRecipe0(T, 16, 64, new FluidStack[] {tDyes[DYE_INDEX_Purple	], tDyes[DYE_INDEX_Pink		]}, UT.Fluids.mul(tDyes[DYE_INDEX_Magenta	], 2), ZL_IS);
		RM.Mixer			.addRecipe0(T, 16, 64, new FluidStack[] {tDyes[DYE_INDEX_Red	], tDyes[DYE_INDEX_Yellow	]}, UT.Fluids.mul(tDyes[DYE_INDEX_Orange	], 2), ZL_IS);
		RM.Mixer			.addRecipe0(T, 16, 64, new FluidStack[] {tDyes[DYE_INDEX_Black	], tDyes[DYE_INDEX_White	]}, UT.Fluids.mul(tDyes[DYE_INDEX_Gray		], 2), ZL_IS);
		RM.Mixer			.addRecipe0(T, 16, 64, new FluidStack[] {tDyes[DYE_INDEX_Gray	], tDyes[DYE_INDEX_White	]}, UT.Fluids.mul(tDyes[DYE_INDEX_LightGray	], 2), ZL_IS);
		}
		
		// Glass
		for (int i = 0; i < 16; i++) {
		RM.Mixer			.addRecipe1(T, 16, 16, OM.dust(MT.Na2SO4     ), new FluidStack[] {UT.Fluids.make("glass",  4000), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[i], 1,  1, T)}, ZL_FS, ST.make(BlocksGT.Glass, 4, i));
		RM.Mixer			.addRecipe1(T, 16, 16, OM.dust(MT.Na2SO4,  U4), new FluidStack[] {UT.Fluids.make("glass",  1000), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[i], 1,  4, T)}, ZL_FS, ST.make(BlocksGT.Glass, 1, i));
		RM.Mixer			.addRecipe1(T, 16, 16, OM.dust(MT.K2SO4      ), new FluidStack[] {UT.Fluids.make("glass",  4000), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[i], 1,  1, T)}, ZL_FS, ST.make(BlocksGT.Glass, 4, i));
		RM.Mixer			.addRecipe1(T, 16, 16, OM.dust(MT.K2SO4 ,  U4), new FluidStack[] {UT.Fluids.make("glass",  1000), UT.Fluids.mul(DYE_FLUIDS_CHEMICAL[i], 1,  4, T)}, ZL_FS, ST.make(BlocksGT.Glass, 1, i));
		
		for (OreDictMaterial tMat : ANY.Glowstone.mToThis) {
		RM.Injector			.addRecipe2(T, 16, 32, OP.dust		.mat(tMat, 1), ST.make(BlocksGT.Glass, 1, i), ST.make(BlocksGT.GlowGlass, 1, i));
		RM.Injector			.addRecipe2(T, 16, 32, OP.dustSmall	.mat(tMat, 4), ST.make(BlocksGT.Glass, 1, i), ST.make(BlocksGT.GlowGlass, 1, i));
		RM.Injector			.addRecipe2(T, 16, 32, OP.dust		.mat(tMat, 1), ST.make(((BlockMetaType)BlocksGT.Glass).mSlabs[0], 2, i), ST.make(((BlockMetaType)BlocksGT.GlowGlass).mSlabs[0], 2, i));
		RM.Injector			.addRecipe2(T, 16, 16, OP.dustSmall	.mat(tMat, 2), ST.make(((BlockMetaType)BlocksGT.Glass).mSlabs[0], 1, i), ST.make(((BlockMetaType)BlocksGT.GlowGlass).mSlabs[0], 1, i));
		}
		}
		
		RM.Lightning	.addRecipe2(T, 16, 2048, ST.tag(0), gem.mat(MT.CertusQuartz, 1), gem.mat(MT.ChargedCertusQuartz, 1));
		
		for (byte i = 0; i < 16; i++) {
		RM.Press		.addRecipeX(T, 16,   16, new ItemStack[] {ST.tag(1), OM.dust(MT.Dynamite, U*1), plantGtFiber.mat(MT.DATA.Dye_Materials[15-i], 1)}, IL.Dynamite.get(1));
		RM.Press		.addRecipeX(T, 16,   64, new ItemStack[] {ST.tag(2), OM.dust(MT.Dynamite, U*2), plantGtFiber.mat(MT.DATA.Dye_Materials[15-i], 1)}, IL.Dynamite_Strong.get(1));
		}
		RM.Press		.addRecipeX(T, 16,   16, new ItemStack[] {ST.tag(1), OM.dust(MT.Dynamite, U*1), plantGtFiber.mat(MT.Cu, 1)}, IL.Dynamite.get(1));
		RM.Press		.addRecipeX(T, 16,   64, new ItemStack[] {ST.tag(2), OM.dust(MT.Dynamite, U*2), plantGtFiber.mat(MT.Cu, 1)}, IL.Dynamite_Strong.get(1));
		RM.Press		.addRecipeX(T, 16,   16, new ItemStack[] {ST.tag(1), OM.dust(MT.Dynamite, U*1), ST.make(Items.string, 1, W)}, IL.Dynamite.get(1));
		RM.Press		.addRecipeX(T, 16,   64, new ItemStack[] {ST.tag(2), OM.dust(MT.Dynamite, U*2), ST.make(Items.string, 1, W)}, IL.Dynamite_Strong.get(1));
		
		
		
		CR.shaped(OP.arrowGtWood	.mat(MT.Empty, 1), CR.DEF_NCC_MIR, " S", "F ", 'S', OP.stick.dat(ANY.Wood), 'F', OD.craftingFeather);
		CR.shaped(OP.arrowGtPlastic	.mat(MT.Empty, 1), CR.DEF_NCC_MIR, " S", "F ", 'S', OP.stick.dat(MT.Plastic), 'F', OD.craftingFeather);
		CR.shaped(OP.arrowGtWood	.mat(MT.Empty, 1), CR.DEF_NCC_MIR, "PS", "sP", 'S', OP.stick.dat(ANY.Wood), 'P', OP.plateTiny.dat(MT.Plastic));
		CR.shaped(OP.arrowGtPlastic	.mat(MT.Empty, 1), CR.DEF_NCC_MIR, "PS", "sP", 'S', OP.stick.dat(MT.Plastic), 'P', OP.plateTiny.dat(MT.Plastic));
		
		
		RM.Press		.addRecipeX(T, 16,   16, new ItemStack[] {IL.Shape_Press_Bullet_Casing_Small	.get(0), OP.dustDiv72	.mat(MT.Gunpowder, 8), OP.plateTiny.mat(MT.Brass, 1)}, OP.bulletGtSmall.mat(MT.Empty, 1));
		RM.Press		.addRecipeX(T, 16,   32, new ItemStack[] {IL.Shape_Press_Bullet_Casing_Medium	.get(0), OP.dustDiv72	.mat(MT.Gunpowder,16), OP.plateTiny.mat(MT.Brass, 2)}, OP.bulletGtMedium.mat(MT.Empty, 1));
		RM.Press		.addRecipeX(T, 16,   64, new ItemStack[] {IL.Shape_Press_Bullet_Casing_Large	.get(0), OP.dustDiv72	.mat(MT.Gunpowder,24), OP.plateTiny.mat(MT.Brass, 3)}, OP.bulletGtLarge.mat(MT.Empty, 1));
		RM.Press		.addRecipeX(T, 16,   16, new ItemStack[] {IL.Shape_Press_Bullet_Casing_Small	.get(0), OP.dustTiny	.mat(MT.Gunpowder, 1), OP.plateTiny.mat(MT.Brass, 1)}, OP.bulletGtSmall.mat(MT.Empty, 1));
		RM.Press		.addRecipeX(T, 16,   32, new ItemStack[] {IL.Shape_Press_Bullet_Casing_Medium	.get(0), OP.dustTiny	.mat(MT.Gunpowder, 2), OP.plateTiny.mat(MT.Brass, 2)}, OP.bulletGtMedium.mat(MT.Empty, 1));
		RM.Press		.addRecipeX(T, 16,   64, new ItemStack[] {IL.Shape_Press_Bullet_Casing_Large	.get(0), OP.dustTiny	.mat(MT.Gunpowder, 3), OP.plateTiny.mat(MT.Brass, 3)}, OP.bulletGtLarge.mat(MT.Empty, 1));
		RM.Press		.addRecipeX(T, 16,  144, new ItemStack[] {IL.Shape_Press_Bullet_Casing_Small	.get(0), OP.dustSmall	.mat(MT.Gunpowder, 4), OP.plateTiny.mat(MT.Brass, 9)}, OP.bulletGtSmall.mat(MT.Empty, 9));
		RM.Press		.addRecipeX(T, 16,  288, new ItemStack[] {IL.Shape_Press_Bullet_Casing_Medium	.get(0), OP.dustSmall	.mat(MT.Gunpowder, 8), OP.plateTiny.mat(MT.Brass,18)}, OP.bulletGtMedium.mat(MT.Empty, 9));
		RM.Press		.addRecipeX(T, 16,  192, new ItemStack[] {IL.Shape_Press_Bullet_Casing_Large	.get(0), OP.dustSmall	.mat(MT.Gunpowder, 4), OP.plateTiny.mat(MT.Brass, 9)}, OP.bulletGtLarge.mat(MT.Empty, 3));
		RM.Press		.addRecipeX(T, 16,  144, new ItemStack[] {IL.Shape_Press_Bullet_Casing_Small	.get(0), OP.dust		.mat(MT.Gunpowder, 1), OP.plateTiny.mat(MT.Brass, 9)}, OP.bulletGtSmall.mat(MT.Empty, 9));
		RM.Press		.addRecipeX(T, 16,  288, new ItemStack[] {IL.Shape_Press_Bullet_Casing_Medium	.get(0), OP.dust		.mat(MT.Gunpowder, 2), OP.plateTiny.mat(MT.Brass,18)}, OP.bulletGtMedium.mat(MT.Empty, 9));
		RM.Press		.addRecipeX(T, 16,  192, new ItemStack[] {IL.Shape_Press_Bullet_Casing_Large	.get(0), OP.dust		.mat(MT.Gunpowder, 1), OP.plateTiny.mat(MT.Brass, 9)}, OP.bulletGtLarge.mat(MT.Empty, 3));
		
		
		
		for (byte i = 0; i < 16; i++) {
		RM.Drying		.addRecipe0(T, 16,   40, UT.Fluids.mul(DYE_FLUIDS_WATER [i], 1, 6, T), FL.DistW.make(20), dustTiny.mat(MT.DATA.Dye_Materials[i], 1));
		RM.Drying		.addRecipe0(T, 16,   20, UT.Fluids.mul(DYE_FLUIDS_FLOWER[i], 1, 6, T), FL.DistW.make(10), dustTiny.mat(MT.DATA.Dye_Materials[i], 1));
		}
		
		RM.Squeezer		.addRecipe1(T, 16,   64, ST.make(BlocksGT.Leaves, 1, 0), NF, FL.Latex.make(L/72), NI);
		RM.Squeezer		.addRecipe1(T, 16,   64, ST.make(BlocksGT.Leaves, 1, 8), NF, FL.Latex.make(L/72), NI);
		RM.Squeezer		.addRecipe1(T, 16,   64, ST.make(BlocksGT.Sapling, 1, 0), NF, FL.Latex.make(L/4), NI);
		RM.Squeezer		.addRecipe1(T, 16,   64, ST.make(BlocksGT.Sapling, 1, 8), NF, FL.Latex.make(L/4), NI);
		RM.Squeezer		.addRecipe1(T, 16,   64, OM.dust(MT.WoodRubber), NF, FL.Latex.make(L/9), OM.dust(MT.Wood));
		RM.Squeezer		.addRecipe1(T, 16,   64, ST.make(Blocks.log, 1, 1), NF, FL.Resin_Spruce.make(50, FL.Resin), OM.dust(MT.Wood));
		RM.Squeezer		.addRecipe1(T, 16,   64, ST.make(BlocksGT.LogA, 1, 1), NF, FL.Sap_Maple.make(50), OM.dust(MT.Wood));
		RM.Squeezer		.addRecipe1(T, 16,   64, ST.make(BlocksGT.LogB, 1, 3), NF, FL.Sap_Rainbow.make(50), OM.dust(MT.Wood));
		
		RM.Centrifuge	.addRecipe0(T, 64,   16, MT.FishOil.liquid( U2, T), MT.Hg.liquid(U144, F), ZL_IS);
		RM.Centrifuge	.addRecipe0(T, 64,   64, FL.Air			.make(200), MT.N.gas(U7, T), MT.O.gas(U20, T), MT.CO2.gas(U100, T), MT.He.gas(U1000, T), MT.Ne.gas(U1000, T), MT.Ar.gas(U1000, T));
		RM.Centrifuge	.addRecipe0(T, 64,   64, FL.Air_Nether	.make(200), MT.N.gas(U7, T), MT.O.gas(U20, T), MT.SO2.gas(U100, T), MT.He.gas(U1000, T), MT.Ne.gas(U1000, T), MT.Ar.gas(U1000, T));
		RM.Centrifuge	.addRecipe0(T, 64,   64, FL.Air_End		.make(200), MT.N.gas(U7, T), MT.O.gas(U20, T), MT.CO2.gas(U100, T), MT.He.gas(U500 , T), MT.Ne.gas(U500 , T), MT.Ar.gas(U500 , T));
		
		for (FluidStack tFluid : new FluidStack[] {MT.He.gas(U, T), MT.Ne.gas(U, T), MT.Ar.gas(U, T), MT.Kr.gas(U, T), MT.Xe.gas(U, T), MT.Rn.gas(U, T)}) if (tFluid != null) {
			RM.CrystallisationCrucible.addRecipe1(T, 16,  72000, OM.dust(MT.Si				, U9), new FluidStack[] {              tFluid    , MT.Si			.liquid(35* U9, T)}, NF, bouleGt.mat(MT.Si				, 1));
			RM.CrystallisationCrucible.addRecipe1(T, 16,  72000, OM.dust(MT.RedstoneAlloy	, U9), new FluidStack[] {              tFluid    , MT.RedstoneAlloy	.liquid(35* U9, T)}, NF, bouleGt.mat(MT.RedstoneAlloy	, 1));
			RM.CrystallisationCrucible.addRecipe1(T, 16,  72000, OM.dust(MT.NikolineAlloy	, U9), new FluidStack[] {              tFluid    , MT.NikolineAlloy	.liquid(35* U9, T)}, NF, bouleGt.mat(MT.NikolineAlloy	, 1));
			RM.CrystallisationCrucible.addRecipe1(T, 16,  72000, OM.dust(MT.TeslatineAlloy	, U9), new FluidStack[] {              tFluid    , MT.TeslatineAlloy.liquid(35* U9, T)}, NF, bouleGt.mat(MT.TeslatineAlloy	, 1));
			RM.CrystallisationCrucible.addRecipe1(T, 16, 648000, OM.dust(MT.Si					), new FluidStack[] {UT.Fluids.mul(tFluid, 9), MT.Si			.liquid(35*U  , T)}, NF, bouleGt.mat(MT.Si				, 9));
			RM.CrystallisationCrucible.addRecipe1(T, 16, 648000, OM.dust(MT.RedstoneAlloy		), new FluidStack[] {UT.Fluids.mul(tFluid, 9), MT.RedstoneAlloy	.liquid(35*U  , T)}, NF, bouleGt.mat(MT.RedstoneAlloy	, 9));
			RM.CrystallisationCrucible.addRecipe1(T, 16, 648000, OM.dust(MT.NikolineAlloy		), new FluidStack[] {UT.Fluids.mul(tFluid, 9), MT.NikolineAlloy	.liquid(35*U  , T)}, NF, bouleGt.mat(MT.NikolineAlloy	, 9));
			RM.CrystallisationCrucible.addRecipe1(T, 16, 648000, OM.dust(MT.TeslatineAlloy		), new FluidStack[] {UT.Fluids.mul(tFluid, 9), MT.TeslatineAlloy.liquid(35*U  , T)}, NF, bouleGt.mat(MT.TeslatineAlloy	, 9));
		}
		
		for (int i = 0; i < 16; i++)
		RM.Loom			.addRecipe2(T, 16,   16, ST.tag(10), OP.plantGtFiber.mat(MT.DATA.Dye_Materials[15-i], 4), IL.Rope.get(1));
		RM.Loom			.addRecipe2(T, 16,   16, ST.tag(10), OP.plantGtFiber.mat(MT.Cu, 4), IL.Rope.get(1));
		RM.Loom			.addRecipe2(T, 16,   16, ST.tag(10), ST.make(Items.string, 4, W), IL.Rope_Silk.get(1));
		
		RM.CokeOven		.addRecipe1(T,  0,  3600, gem						.mat(MT.Coal	, 1), NF, MT.Creosote	.liquid(   U2, F), gem		.mat(MT.CoalCoke, 1));
		RM.CokeOven		.addRecipe1(T,  0,  3600, crushedPurified			.mat(MT.Coal	, 1), NF, MT.Creosote	.liquid(   U2, F), gem		.mat(MT.CoalCoke, 1));
		RM.CokeOven		.addRecipe1(T,  0,  3600, crushedPurifiedTiny		.mat(MT.Coal	, 9), NF, MT.Creosote	.liquid(   U2, F), gem		.mat(MT.CoalCoke, 1));
		RM.CokeOven		.addRecipe1(T,  0,  3600, crushedCentrifuged		.mat(MT.Coal	, 1), NF, MT.Creosote	.liquid(   U2, F), gem		.mat(MT.CoalCoke, 1));
		RM.CokeOven		.addRecipe1(T,  0,  3600, crushedCentrifugedTiny	.mat(MT.Coal	, 9), NF, MT.Creosote	.liquid(   U2, F), gem		.mat(MT.CoalCoke, 1));
		RM.CokeOven		.addRecipe1(T,  0, 32400, blockGem					.mat(MT.Coal	, 1), NF, MT.Creosote	.liquid( 9*U2, F), blockGem	.mat(MT.CoalCoke, 1));
		
		RM.CokeOven		.addRecipe1(T,  0,  3600, gem						.mat(MT.Lignite	, 1), NF, MT.Creosote	.liquid( 3*U4, F), gem		.mat(MT.LigniteCoke, 1));
		RM.CokeOven		.addRecipe1(T,  0,  3600, crushedPurified			.mat(MT.Lignite	, 1), NF, MT.Creosote	.liquid( 3*U4, F), gem		.mat(MT.LigniteCoke, 1));
		RM.CokeOven		.addRecipe1(T,  0,  3600, crushedPurifiedTiny		.mat(MT.Lignite	, 9), NF, MT.Creosote	.liquid( 3*U4, F), gem		.mat(MT.LigniteCoke, 1));
		RM.CokeOven		.addRecipe1(T,  0,  3600, crushedCentrifuged		.mat(MT.Lignite	, 1), NF, MT.Creosote	.liquid( 3*U4, F), gem		.mat(MT.LigniteCoke, 1));
		RM.CokeOven		.addRecipe1(T,  0,  3600, crushedCentrifugedTiny	.mat(MT.Lignite	, 9), NF, MT.Creosote	.liquid( 3*U4, F), gem		.mat(MT.LigniteCoke, 1));
		RM.CokeOven		.addRecipe1(T,  0, 32400, blockGem					.mat(MT.Lignite	, 1), NF, MT.Creosote	.liquid(27*U4, F), blockGem	.mat(MT.LigniteCoke, 1));
		
		RM.CokeOven		.addRecipe1(T,  0,  3600, dust						.mat(MT.Oilshale, 1), NF, MT.Oil		.liquid(  U40, F), dustTiny	.mat(MT.Asphalt, 1));
		RM.CokeOven		.addRecipe1(T,  0, 32400, blockDust					.mat(MT.Oilshale, 1), NF, MT.Oil		.liquid(9*U40, F), dust		.mat(MT.Asphalt, 1));
		
		
		RM.DistillationTower	.addRecipe0(F,  64, 512, new long[] { 9000,  9000,  9000}, new FluidStack[] {FL.Oil_ExtraHeavy	.make(50)}, new FluidStack[] {FL.Oil_Fuel.make(70), UT.Fluids.make("diesel",  45), FL.Kerosine.make(40), UT.Fluids.make("petrol",  35), UT.Fluids.make("propane",  10), UT.Fluids.make("butane",  10), FL.lube(100)}, dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1), dustTiny.mat(MT.Plastic, 1));
		RM.DistillationTower	.addRecipe0(F,  64, 384, new long[] { 7000,  7000,  7000}, new FluidStack[] {FL.Oil_Heavy		.make(50)}, new FluidStack[] {FL.Oil_Fuel.make(60), UT.Fluids.make("diesel",  35), FL.Kerosine.make(35), UT.Fluids.make("petrol",  30), UT.Fluids.make("propane",  15), UT.Fluids.make("butane",  15), FL.lube( 80)}, dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1), dustTiny.mat(MT.Plastic, 1)); if (FL.Oil_Heavy2.exists())
		RM.DistillationTower	.addRecipe0(F,  64, 384, new long[] { 7000,  7000,  7000}, new FluidStack[] {FL.Oil_Heavy2		.make(50)}, new FluidStack[] {FL.Oil_Fuel.make(60), UT.Fluids.make("diesel",  35), FL.Kerosine.make(35), UT.Fluids.make("petrol",  30), UT.Fluids.make("propane",  15), UT.Fluids.make("butane",  15), FL.lube( 80)}, dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1), dustTiny.mat(MT.Plastic, 1));
		RM.DistillationTower	.addRecipe0(F,  64, 256, new long[] { 5000,  5000,  5000}, new FluidStack[] {FL.Oil_Medium		.make(50)}, new FluidStack[] {FL.Oil_Fuel.make(50), UT.Fluids.make("diesel",  25), FL.Kerosine.make(25), UT.Fluids.make("petrol",  25), UT.Fluids.make("propane",  25), UT.Fluids.make("butane",  25), FL.lube( 50)}, dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1), dustTiny.mat(MT.Plastic, 1));
		RM.DistillationTower	.addRecipe0(F,  64, 256, new long[] { 5000,  5000,  5000}, new FluidStack[] {FL.Oil_Normal		.make(50)}, new FluidStack[] {FL.Oil_Fuel.make(50), UT.Fluids.make("diesel",  25), FL.Kerosine.make(25), UT.Fluids.make("petrol",  25), UT.Fluids.make("propane",  25), UT.Fluids.make("butane",  25), FL.lube( 50)}, dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1), dustTiny.mat(MT.Plastic, 1)); if (FL.Oil_HotCrude.exists())
		RM.DistillationTower	.addRecipe0(F,  64, 128, new long[] { 5000,  5000,  5000}, new FluidStack[] {FL.Oil_HotCrude	.make(50)}, new FluidStack[] {FL.Oil_Fuel.make(50), UT.Fluids.make("diesel",  25), FL.Kerosine.make(25), UT.Fluids.make("petrol",  25), UT.Fluids.make("propane",  25), UT.Fluids.make("butane",  25), FL.lube( 50)}, dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1), dustTiny.mat(MT.Plastic, 1));
		RM.DistillationTower	.addRecipe0(F,  64, 128, new long[] { 3000,  3000,  3000}, new FluidStack[] {FL.Oil_Light		.make(50)}, new FluidStack[] {FL.Oil_Fuel.make(25), UT.Fluids.make("diesel",  15), FL.Kerosine.make(15), UT.Fluids.make("petrol",  15), UT.Fluids.make("propane",  50), UT.Fluids.make("butane",  50), FL.lube( 25)}, dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1), dustTiny.mat(MT.Plastic, 1)); if (FL.Oil_Light2.exists())
		RM.DistillationTower	.addRecipe0(F,  64, 128, new long[] { 3000,  3000,  3000}, new FluidStack[] {FL.Oil_Light2		.make(50)}, new FluidStack[] {FL.Oil_Fuel.make(25), UT.Fluids.make("diesel",  15), FL.Kerosine.make(15), UT.Fluids.make("petrol",  15), UT.Fluids.make("propane",  50), UT.Fluids.make("butane",  50), FL.lube( 25)}, dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1), dustTiny.mat(MT.Plastic, 1));
		RM.DistillationTower	.addRecipe0(F,  64, 128, new long[] { 2000,  2000,  2000}, new FluidStack[] {FL.Oil_Soulsand	.make(50)}, new FluidStack[] {FL.Oil_Fuel.make(20), UT.Fluids.make("diesel",  10), FL.Kerosine.make(10), UT.Fluids.make("petrol",  10), UT.Fluids.make("propane",  10), UT.Fluids.make("butane",  10), FL.lube( 80)}, dustTiny.mat(MT.WaxParaffin, 1), dustTiny.mat(MT.Asphalt, 1), dustTiny.mat(MT.Plastic, 1));
		
		RM.Coagulator	.addRecipe0(T,  0,  256, FL.Latex.make(L/9), NF, nugget.mat(MT.Rubber, 1));
		
		for (OreDictMaterial tMaterial : OreDictMaterial.MATERIAL_ARRAY) if (tMaterial != null && tMaterial.mNeutrons+tMaterial.mProtons > 0 && tMaterial.contains(TD.Atomic.ELEMENT) && !tMaterial.contains(TD.Atomic.ANTIMATTER)) {
			ItemStack tInput = OM.dust(tMaterial);		if (tInput != null)			RM.Massfab.addRecipe1(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*65536, tInput, NF, tMaterial.mProtons<1?NF:UT.Fluids.make("chargedmatter", tMaterial.mProtons), tMaterial.mNeutrons<1?NF:UT.Fluids.make("neutralmatter", tMaterial.mNeutrons));
			tInput = OM.ingot(tMaterial);				if (tInput != null)			RM.Massfab.addRecipe1(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*65536, tInput, NF, tMaterial.mProtons<1?NF:UT.Fluids.make("chargedmatter", tMaterial.mProtons), tMaterial.mNeutrons<1?NF:UT.Fluids.make("neutralmatter", tMaterial.mNeutrons));
			FluidStack tFluid = tMaterial.liquid(U, T);	if (!FL.Error.is(tFluid))	RM.Massfab.addRecipe0(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*65536, tFluid,     tMaterial.mProtons<1?NF:UT.Fluids.make("chargedmatter", tMaterial.mProtons), tMaterial.mNeutrons<1?NF:UT.Fluids.make("neutralmatter", tMaterial.mNeutrons));
			tFluid = tMaterial.gas(U, T);				if (!FL.Error.is(tFluid))	RM.Massfab.addRecipe0(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*65536, tFluid,     tMaterial.mProtons<1?NF:UT.Fluids.make("chargedmatter", tMaterial.mProtons), tMaterial.mNeutrons<1?NF:UT.Fluids.make("neutralmatter", tMaterial.mNeutrons));
			tFluid = tMaterial.plasma(U, T);			if (!FL.Error.is(tFluid))	RM.Massfab.addRecipe0(T, 1, (tMaterial.mNeutrons+tMaterial.mProtons)*65536, tFluid,     tMaterial.mProtons<1?NF:UT.Fluids.make("chargedmatter", tMaterial.mProtons), tMaterial.mNeutrons<1?NF:UT.Fluids.make("neutralmatter", tMaterial.mNeutrons));
		}
		
		RM.generify(UT.Fluids.make("molten.meteoriciron"		, 1), UT.Fluids.make("molten.iron", 1));
		RM.generify(UT.Fluids.make("molten.wroughtiron"			, 1), UT.Fluids.make("molten.iron", 1));
		RM.generify(FL.Redstone_TE							.make(25),FL.Redstone.make(36));
		RM.generify(FL.Redstone								.make(36),FL.Redstone_TE.make(25));
		RM.generify(FL.Lubricant							.make(1), FL.LubRoCant.make(1));
		RM.generify(FL.LubRoCant							.make(1), FL.Lubricant.make(1));
		RM.generify(FL.Oil_Canola							.make(2), FL.lube(1));
		RM.generify(UT.Fluids.make("molten.latex"				, 1), FL.Latex.make(1));
		RM.generify(FL.Latex								.make(1), UT.Fluids.make("molten.latex", 1));
		RM.generify(FL.Slime_Pink							.make(1), FL.Slime_Green.make(1));
		RM.generify(FL.Honey								.make(1), FL.HoneyGrC.make(1));
		RM.generify(FL.HoneyGrC								.make(1), FL.HoneyBoP.make(1));
		RM.generify(FL.HoneyBoP								.make(1), FL.Honey.make(1));
		RM.generify(FL.Milk									.make(1), FL.MilkGrC.make(1));
		RM.generify(FL.MilkGrC								.make(1), FL.Milk.make(1));
		RM.generify(UT.Fluids.make("for.honeydew"				, 1), FL.Honeydew.make(1));
		RM.generify(UT.Fluids.make("spruceresin"				, 1), UT.Fluids.make("resin", 1));
		RM.generify(UT.Fluids.make("resin"						, 1), UT.Fluids.make("spruceresin", 1));
		RM.generify(UT.Fluids.make("sulfuricacid"				, 1), UT.Fluids.make("acid", 1));
		RM.generify(UT.Fluids.make("acid"						, 1), UT.Fluids.make("sulfuricacid", 1));
		RM.generify(FL.Oil_Plant							.make(20),FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Seed								.make(1), FL.Oil_Plant.make(20));
		RM.generify(UT.Fluids.make("biomass"					, 1), UT.Fluids.make("ic2biomass", 1));
		RM.generify(UT.Fluids.make("ic2biomass"					, 1), UT.Fluids.make("biomass", 1));
		RM.generify(UT.Fluids.make("methane"					, 1), UT.Fluids.make("ic2biogas", 1));
		RM.generify(UT.Fluids.make("ic2biogas"					, 1), UT.Fluids.make("methane", 1));
		RM.generify(UT.Fluids.make("gas_natural_gas"			, 1), UT.Fluids.make("methane", 1));
		RM.generify(UT.Fluids.make("naturalgas"					, 1), UT.Fluids.make("methane", 1));
		RM.generify(UT.Fluids.make("kerosine"					, 1), UT.Fluids.make("kerosene", 1));
		RM.generify(UT.Fluids.make("kerosene"					, 1), UT.Fluids.make("kerosine", 1));
		RM.generify(UT.Fluids.make("petrol"						, 1), UT.Fluids.make("gasoline", 1));
		RM.generify(UT.Fluids.make("gasoline"					, 1), UT.Fluids.make("petrol", 1));
		RM.generify(UT.Fluids.make("fuel"						, 1), UT.Fluids.make("fueloil", 1));
		RM.generify(UT.Fluids.make("fueloil"					, 1), UT.Fluids.make("fuel", 1));
		RM.generify(FL.DistW								.make(1), FL.Water.make(1));
		RM.generify(FL.Oil_Lin								.make(1), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Hemp								.make(1), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Olive							.make(1), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Sunflower						.make(1), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Nut								.make(1), FL.Oil_Seed.make(1));
		
		for (String tFluid : FluidsGT.JUICE) if (UT.Fluids.exists(tFluid)) RM.generify(UT.Fluids.make(tFluid, 1), FL.Juice.make(1));
	}
}