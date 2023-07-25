/**
 * Copyright (c) 2023 GregTech-6 Team
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

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.*;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Items;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;

import static gregapi.data.CS.*;
import static gregapi.data.OP.rockGt;

public class Compat_Recipes_Erebus extends CompatMods {
	public Compat_Recipes_Erebus(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Erebus Recipes.");
		RM.Mortar       .addRecipe1(T, 16, 16       , ST.make(MD.ERE, "fireBloom"           , 1, W), OM.dust(MT.Blaze, U72));
		RM.Mortar       .addRecipe1(T, 16, 16       , ST.make(MD.ERE, "materials"           , 1,56), OM.dust(MT.Jade, U9));
		RM.Mortar       .addRecipe1(T, 16, 16       , ST.make(MD.ERE, "materials"           , 1, 0), IL.Dye_Bonemeal.get(1));
		RM.Mortar       .addRecipe1(T, 16, 16       , ST.make(MD.ERE, "materials"           , 1, 2), IL.Dye_Bonemeal.get(1));
		RM.Mortar       .addRecipe1(T, 16,128       , ST.make(MD.ERE, "materials"           , 1,16), IL.Dye_Bonemeal.get(8));
		
		RM.Crusher      .addRecipe1(T, 16, 64       , ST.make(MD.ERE, "oreEncrustedDiamond" , 1, 0), ST.make(MD.ERE, "encrustedDiamond", 2*MT.Diamond.mOreMultiplier*MT.Diamond.mOreProcessingMultiplier, 0), OM.dust(MT.VolcanicAsh));
		RM.Hammer       .addRecipe1(T, 16, 16,  7500, ST.make(MD.ERE, "oreEncrustedDiamond" , 1, 0), ST.make(MD.ERE, "encrustedDiamond", 2*MT.Diamond.mOreMultiplier*MT.Diamond.mOreProcessingMultiplier, 0));
		
		RM.Crusher      .addRecipe1(T, 16, 64       , ST.make(MD.ERE, "volcanicRock"        , 1, 0), OM.dust(MT.VolcanicAsh));
		RM.Hammer       .addRecipe1(T, 16, 16       , ST.make(MD.ERE, "volcanicRock"        , 1, 0), OM.dust(MT.VolcanicAsh));
		RM.Shredder     .addRecipe1(T, 16, 96       , ST.make(MD.ERE, "volcanicRock"        , 1, 0), OM.dust(MT.VolcanicAsh));
		
		RM.Sharpening   .addRecipe1(T, 16, 16       , ST.make(MD.ERE, "encrustedDiamond"    , 1, W), ST.make(Items.diamond, 1, 0), OM.dust(MT.VolcanicAsh, U2));
		
		RM.pack(rockGt.mat(MT.STONES.Gneiss    , 4), ST.make(MD.ERE, "gneiss", 1, 0));
		
		RM.pack(ST.make(Items.string, 9, 0), ST.make(MD.ERE, "blockSilk", 1, 0));
		RM.unpack(ST.make(MD.ERE, "blockSilk", 1, 0), ST.make(Items.string, 9, 0));
		
		CR.shaped(ST.make(MD.ERE, "gneiss"      , 1, 0), CR.DEF, "XX", "XX", 'X', rockGt.dat(MT.STONES.Gneiss));
		
		// Mud Bricks
		CR.shapeless(IL.Mud_Ball.get(4), CR.DEF_NCC, new Object[] {IL.ERE_Mud});
		RM.compactsmash(IL.ERE_Mud_Brick.get(4), 4, IL.ERE_Mud_Bricks.get(1));
		RM.add_smelting(IL.ERE_Mud.get(1), IL.ERE_Mud_Brick.get(1), T, F, T);
		CR.delate(MD.ERE, "mirbrick", "petrifiedWoodPlanks");
		CR.shaped(IL.ERE_Mud_Bricks.get(1), CR.DEF | CR.DEL_OTHER_SHAPED_RECIPES, "BB" , "BB" , 'B', IL.ERE_Mud_Brick);
		CR.shaped(ST.make(MD.ERE, "mirbrick", 1, 0), CR.DEF_MIR, "XY", "YX", 'X', OD.itemMudBrick  , 'Y', OD.itemClay);
		CR.shaped(ST.make(MD.ERE, "mirbrick", 4, 0), CR.DEF_MIR, "XY", "YX", 'X', OD.blockMudBricks, 'Y', OD.blockClay);
		
		
		RM.pressurewash(ST.make(MD.ERE, "umberstone"        , 1, 3), ST.make(MD.ERE, "umberstone"        , 1, 1), ST.make(Items.string, 4, 0));
		RM.pressurewash(ST.make(MD.ERE, "umberPaver"        , 1, 2), ST.make(MD.ERE, "umberPaver"        , 1, 0), ST.make(Items.string, 4, 0));
		RM.pressurewash(ST.make(MD.ERE, "wallErebus"        , 1, 3), ST.make(MD.ERE, "wallErebus"        , 1, 1), ST.make(Items.string, 4, 0));
		RM.pressurewash(ST.make(MD.ERE, "wallErebus"        , 1, 7), ST.make(MD.ERE, "wallErebus"        , 1, 5), ST.make(Items.string, 4, 0));
		RM.pressurewash(ST.make(MD.ERE, "umbercobbleStairs3", 1, 0), ST.make(MD.ERE, "umbercobbleStairs1", 1, 0), ST.make(Items.string, 3, 0));
		RM.pressurewash(ST.make(MD.ERE, "slab-umberstone3"  , 1, 0), ST.make(MD.ERE, "slab-umberstone1"  , 1, 0), ST.make(Items.string, 2, 0));
		RM.moss(ST.make(MD.ERE, "umberstone"        , 1, 1), ST.make(MD.ERE, "umberstone"        , 1, 2));
		RM.moss(ST.make(MD.ERE, "umberPaver"        , 1, 0), ST.make(MD.ERE, "umberPaver"        , 1, 1));
		RM.moss(ST.make(MD.ERE, "wallErebus"        , 1, 1), ST.make(MD.ERE, "wallErebus"        , 1, 2));
		RM.moss(ST.make(MD.ERE, "wallErebus"        , 1, 5), ST.make(MD.ERE, "wallErebus"        , 1, 6));
		RM.moss(ST.make(MD.ERE, "umbercobbleStairs1", 1, 0), ST.make(MD.ERE, "umbercobbleStairs2", 1, 0));
		RM.moss(ST.make(MD.ERE, "slab-umberstone1"  , 1, 0), ST.make(MD.ERE, "slab-umberstone2"  , 1, 0));
		
		RM.stoneshapes(MT.STONES.Umber, F, ST.make(MD.ERE, "umberstone", 1, 2), ST.make(MD.ERE, "umbercobbleStairs2", 1, 0), ST.make(MD.ERE, "slab-umberstone2"   , 1, 0), ST.make(MD.ERE, "wallErebus"         , 1, 2), NI);
		RM.stoneshapes(MT.STONES.Umber, F, ST.make(MD.ERE, "umberstone", 1, 3), ST.make(MD.ERE, "umbercobbleStairs3", 1, 0), ST.make(MD.ERE, "slab-umberstone3"   , 1, 0), ST.make(MD.ERE, "wallErebus"         , 1, 3), NI);
		RM.stoneshapes(MT.STONES.Umber, F, ST.make(MD.ERE, "umberPaver", 1, 1), NI                                         , ST.make(MD.ERE, "slab-umberPaver1"   , 1, 0), ST.make(MD.ERE, "wallErebus"         , 1, 6), NI);
		RM.stoneshapes(MT.STONES.Umber, F, ST.make(MD.ERE, "umberPaver", 1, 2), NI                                         , NI                                          , ST.make(MD.ERE, "wallErebus"         , 1, 7), NI);
		RM.stonetypes(MT.STONES.Umber, T, OP.rockGt.mat(MT.STONES.Umber, 4), OP.blockDust.mat(MT.STONES.Umber, 1)
		, RM.stoneshapes(MT.STONES.Umber, F, ST.make(MD.ERE, "umberstone", 1, 0), ST.make(MD.ERE, "umbercobbleStairs0", 1, 0), ST.make(MD.ERE, "slab-umberstone0"   , 1, 0), ST.make(MD.ERE, "wallErebus"         , 1, 0), NI)
		, RM.stoneshapes(MT.STONES.Umber, T, ST.make(MD.ERE, "umberstone", 1, 1), ST.make(MD.ERE, "umbercobbleStairs1", 1, 0), ST.make(MD.ERE, "slab-umberstone1"   , 1, 0), ST.make(MD.ERE, "wallErebus"         , 1, 1), NI)
		, RM.stoneshapes(MT.STONES.Umber, F, ST.make(MD.ERE, "umberstone", 1, 4), ST.make(MD.ERE, "umbercobbleStairs4", 1, 0), ST.make(MD.ERE, "slab-umberstone4"   , 1, 0), ST.make(MD.ERE, "wallErebus"         , 1, 4), NI)
		, RM.stoneshapes(MT.STONES.Umber, F, ST.make(MD.ERE, "umberPaver", 1, 0), NI                                         , ST.make(MD.ERE, "slab-umberPaver0"   , 1, 0), ST.make(MD.ERE, "wallErebus"         , 1, 5), NI)
		, NI
		, RM.stoneshapes(MT.STONES.Umber, F, ST.make(MD.ERE, "umberstone", 1, 5), ST.make(MD.ERE, "umbercobbleStairs5", 1, 0), NI                                          , NI                                          , ST.make(MD.ERE, "umberstonePillar"   , 1, 0))
		, RM.stoneshapes(MT.STONES.Umber, F, ST.make(MD.ERE, "umberstone", 1, 6), ST.make(MD.ERE, "umbercobbleStairs6", 1, 0), NI                                          , NI                                          , NI)
		, NI
		);
		
		
		RM.stonetypes(MT.STONES.Gneiss, F, OP.rockGt.mat(MT.STONES.Gneiss, 4), OP.blockDust.mat(MT.STONES.Gneiss, 1)
		, RM.stoneshapes(MT.STONES.Gneiss, F, ST.make(MD.ERE, "gneiss", 1, 0), ST.make(MD.ERE, "gneissStairs0", 1, 0), NI, NI, NI)
		, NI
		, RM.stoneshapes(MT.STONES.Gneiss, F, ST.make(MD.ERE, "gneiss", 1, 3), ST.make(MD.ERE, "gneissStairs3", 1, 0), NI, NI, NI)
		, RM.stoneshapes(MT.STONES.Gneiss, F, ST.make(MD.ERE, "gneiss", 1, 2), ST.make(MD.ERE, "gneissStairs2", 1, 0), NI, NI, NI)
		, RM.stoneshapes(MT.STONES.Gneiss, F, ST.make(MD.ERE, "gneiss", 1, 1), ST.make(MD.ERE, "gneissStairs1", 1, 0), NI, NI, NI)
		, RM.stoneshapes(MT.STONES.Gneiss, F, ST.make(MD.ERE, "gneiss", 1, 4), ST.make(MD.ERE, "gneissStairs4", 1, 0), NI, NI, NI)
		, RM.stoneshapes(MT.STONES.Gneiss, F, ST.make(MD.ERE, "gneiss", 1, 5), ST.make(MD.ERE, "gneissStairs5", 1, 0), NI, NI, NI)
		, NI
		);
		
		
		RM.Canner       .addRecipe2(T, 16,144,  ST.make(MD.ERE, "materials"     , 1,29), IL.Spray_Empty.get(9), IL.ERE_Spray_Repellant.get(9));
		
		for (FluidStack tFluid : FL.waters(250))
		RM.Mixer        .addRecipe1(T, 16, 16, IL.ERE_Herbicide.get(1), tFluid, FL.Potion_Poison_2.make(250), ZL_IS);
		
		RM.Squeezer     .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "weepingBlue"   , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], ST.make(MD.ERE, "materials", 2,26));
		RM.Juicer       .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "weepingBlue"   , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], ST.make(MD.ERE, "materials", 2,26));
		RM.ic2_extractor(                       ST.make(MD.ERE, "weepingBlue"   , 1, 0), ST.make(MD.ERE, "materials", 2,26));
		
		RM.Squeezer     .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "waterFlower"   , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.Juicer       .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "waterFlower"   , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.ic2_extractor(                       ST.make(MD.ERE, "waterFlower"   , 1, 0), ST.make(Items.dye, 3, DYE_INDEX_Pink));
		
		RM.Squeezer     .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "pricklyPair"   , 1, W), NF, FL.Juice_Cactus.make(100), IL.Dye_Cactus.get(2));
		RM.Juicer       .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "pricklyPair"   , 1, W), NF, FL.Juice_Cactus.make(100), IL.Dye_Cactus.get(2));
		RM.ic2_extractor(                       ST.make(MD.ERE, "pricklyPair"   , 1, W), IL.Dye_Cactus.get(2));
		
		RM.Squeezer     .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "materials"     , 1,22), NF, FL.Potion_Poison_2.make(750), ZL_IS);
		RM.Juicer       .addRecipe1(T, 16, 16,  ST.make(MD.ERE, "materials"     , 1,22), NF, FL.Potion_Poison_2.make(750), ZL_IS);
		
		FL.set(new FluidContainerData(FL.Honey     .make(1000), ST.make(MD.ERE, "bucketHoney"      , 1, 0), ST.make(Items.bucket       , 1, 0), F), F, T);
		FL.set(new FluidContainerData(FL.HoneyBoP  .make(1000), ST.make(MD.ERE, "bucketHoney"      , 1, 0), ST.make(Items.bucket       , 1, 0), F), F, F);
		FL.set(new FluidContainerData(FL.HoneyGrC  .make(1000), ST.make(MD.ERE, "bucketHoney"      , 1, 0), ST.make(Items.bucket       , 1, 0), F), F, F);
		FL.set(new FluidContainerData(FL.Honey     .make(1000), ST.make(MD.ERE, "bambucketHoney"   , 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, T);
		FL.set(new FluidContainerData(FL.HoneyBoP  .make(1000), ST.make(MD.ERE, "bambucketHoney"   , 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, F);
		FL.set(new FluidContainerData(FL.HoneyGrC  .make(1000), ST.make(MD.ERE, "bambucketHoney"   , 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, F);
		FL.set(new FluidContainerData(FL.Milk      .make(1000), ST.make(MD.ERE, "bambucketMilk"    , 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, T);
		FL.set(new FluidContainerData(FL.MilkGrC   .make(1000), ST.make(MD.ERE, "bambucketMilk"    , 1, 0), ST.make(MD.ERE, "bambucket", 1, 0), F), T, F);
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener("stoneGneiss", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Crusher  .addRecipe1(T, 16, 64       , aEvent.mStack, OP.rockGt.mat(MT.STONES.Gneiss, 4));
			RM.Hammer   .addRecipe1(T, 16, 64,  7000, aEvent.mStack, OP.rockGt.mat(MT.STONES.Gneiss, 4));
		}});
		}};
	}
}
