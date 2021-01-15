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

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class Compat_Recipes_Binnie extends CompatMods {
	public Compat_Recipes_Binnie(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {
		if (MD.BINNIE_TREE.mLoaded) {
			OUT.println("GT_Mod: Doing Binnie Extra Trees Recipes.");
			
			new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
			addListener("dustSmallAnyWax", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
				RM.Mixer    .addRecipe1(T, 16,   16, aEvent.mStack, FL.make("turpentine", 10), NF, ST.make(MD.BINNIE_TREE, "misc", 1, 4));
			}});
			addListener("dustAnyWax", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
				RM.Mixer    .addRecipe1(T, 16,   64, aEvent.mStack, FL.make("turpentine", 40), NF, ST.make(MD.BINNIE_TREE, "misc", 4, 4));
			}});
			}};
		}
		if (MD.BINNIE_GENETICS.mLoaded) {
			OUT.println("GT_Mod: Doing Binnie Genetics Recipes.");
			
			CR.delate(MD.BINNIE_GENETICS, "misc", 4);
			RM.Mixer        .addRecipe2(T, 16,   32, OM.dust(MT.Bone), OM.dust(MT.Sugar), ST.make(MD.BINNIE_GENETICS, "misc", 2, 4));
		}
		if (MD.BINNIE_BOTANY.mLoaded) {
			OUT.println("GT_Mod: Doing Binnie Botany Recipes.");
			
			CR.delate(MD.BINNIE_BOTANY, "misc", 6);
			
			new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
			addListener("itemClay", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
				RM.Mixer.addRecipe2(T, 16,   96, ST.amount(4, aEvent.mStack), ST.make(Blocks.gravel, 1, W)  , ST.make(MD.BINNIE_BOTANY, "misc", 6, 6));
				RM.Mixer.addRecipe2(T, 16,   96, ST.amount(4, aEvent.mStack), OM.dust(MT.Gravel)            , ST.make(MD.BINNIE_BOTANY, "misc", 6, 6));
			}});
			addListener("blockClay", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
				RM.Mixer.addRecipe2(T, 16,   96, aEvent.mStack              , ST.make(Blocks.gravel, 1, W)  , ST.make(MD.BINNIE_BOTANY, "misc", 6, 6));
				RM.Mixer.addRecipe2(T, 16,   96, aEvent.mStack              , OM.dust(MT.Gravel)            , ST.make(MD.BINNIE_BOTANY, "misc", 6, 6));
			}});
			}};
			
			CR.delate(MD.BINNIE_BOTANY, "misc", 7);
			RM.Mixer        .addRecipe2(T, 16,   64, ST.make(Items.wheat_seeds, 3, W), ST.make(Items.spider_eye, 1, W), ST.make(MD.BINNIE_BOTANY, "misc", 4, 7));
		}
		if (MD.BINNIE_BEE.mLoaded) {
			OUT.println("GT_Mod: Doing Binnie Extra Bees Recipes.");
			
			CR.shaped(ST.make(MD.BINNIE_BEE, "hiveFrame.cocoa", 1, 0), new Object[] {" C ", "CFC", " C ", 'C', OP.dust.dat(MT.Cocoa), 'F', ST.make(MD.FR, "frameImpregnated", 1, 0)});
			
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Barren     .get(1), NF, FL.Honey.make(50), OM.dust(MT.WaxBee));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Old        .get(1), NF, FL.Honeydew.make(90), OM.dust(MT.WaxParaffin));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  8000}                  , IL.BINNIE_Comb_Rotten     .get(1), NF, FL.Honey.make(20), OM.dust(MT.WaxBee, U4), ST.make(Items.rotten_flesh, 1, 0));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  8000}                  , IL.BINNIE_Comb_Bone       .get(1), NF, FL.Honey.make(20), OM.dust(MT.WaxBee, U4), IL.Dye_Bonemeal.get(1));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  6000}                  , IL.BINNIE_Comb_Oil        .get(1), NF, FL.Honey.make(50), OM.dust(MT.WaxParaffin), IL.BINNIE_Propolis_Oil.get(1));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  6000}                  , IL.BINNIE_Comb_Petroleum  .get(1), NF, FL.Honey.make(50), OM.dust(MT.WaxParaffin), IL.BINNIE_Propolis_Petroleum.get(1));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Damp       .get(1), NF, FL.Water.make(500), IL.BINNIE_Propolis_Water.get(1));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Milk       .get(1), NF, FL.Milk.make(400), OM.dust(MT.WaxBee));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Fruit      .get(1), NF, FL.Juice.make(400), OM.dust(MT.WaxBee));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Seed       .get(1), NF, FL.make("seedoil", 400), OM.dust(MT.WaxBee));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Alcohol    .get(1), NF, FL.make("short.mead", 400), OM.dust(MT.WaxBee));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Coffee     .get(1), NF, FL.make("potion.coffee", 500), OM.dust(MT.WaxBee), OM.dust(MT.Coffee, 3*U4));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Mint       .get(1), NF, FL.Honey.make(80), OM.dust(MT.WaxBee), OM.dust(MT.Mint, 3*U4));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Citrus     .get(1), ZL_FS, FL.array(FL.Juice_Orange.make(100), FL.Juice_Persimmon.make(100), FL.Juice_Lemon.make(100), FL.Juice_Lime.make(100), FL.Juice_Kiwi.make(100)), OM.dust(MT.WaxBee));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Venom      .get(1), NF, FL.make("poison", 200), OM.dust(MT.WaxBee, 8*U9));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Glacial    .get(1), NF, FL.make("liquidnitrogen", 200), OM.dust(MT.WaxBee));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 9000,  9000}                  , IL.BINNIE_Comb_Fungal     .get(1), NF, FL.make("mushroomsoup", 1000), ST.make(Blocks.red_mushroom_block, 1, 0), ST.make(Blocks.brown_mushroom_block, 1, 0));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Peat       .get(1), NF, FL.Honey.make(50), IL.BINNIE_Propolis_Peat.get(1));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Mulch      .get(1), NF, FL.Honey.make(50), OM.dust(MT.WaxBee), IL.FR_Mulch.get(1));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Decomposed .get(1), NF, FL.Honey.make(50), OM.dust(MT.WaxBee), IL.FR_Compost.get(1));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Dusty      .get(1), NF, FL.Honey.make(20), OM.dust(MT.WaxBee, U2), OM.dust(MT.Wood));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Pulp       .get(1), NF, FL.Honey.make(20), OM.dust(MT.WaxBee, U4), OM.dust(MT.Wood, U*2));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Rock       .get(1), NF, FL.Honey.make(30), OM.dust(MT.WaxBee, U2), OM.dust(MT.Stone));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7000}                  , IL.BINNIE_Comb_Tar        .get(1), NF, FL.Honey.make(50), IL.BINNIE_Propolis_Creosote.get(1));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Amber      .get(1), OM.dust(MT.WaxBee), IL.FR_Propolis.get(2));
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Latex      .get(1), NF, FL.Latex.make(L), OM.dust(MT.WaxBee));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Shadow     .get(1), NF, FL.Honey.make(50), OM.dust(MT.Obsidian, U*4));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Energetic  .get(1), NF, FL.Honey.make(50), OM.dust(MT.Redstone, U*2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500,  7500}                  , IL.BINNIE_Comb_Glowing    .get(1), NF, FL.Honey.make(30), OM.dust(MT.Glowstone, U), OM.dust(MT.Gloomstone, U));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 2500,  2500,  2500}           , IL.BINNIE_Comb_Static     .get(1), NF, FL.Honey.make(50), OM.dust(MT.Nikolite, U*4), OM.dust(MT.Teslatite, U*4), OM.dust(MT.Electrotine, U*4));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 4000,  3000,  3000}           , IL.BINNIE_Comb_Certus     .get(1), NF, FL.Honey.make(20), OP.gem.mat(MT.CertusQuartz, 1), OP.gem.mat(MT.NetherQuartz, 1), OP.gem.mat(MT.MilkyQuartz, 1));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Lapis      .get(1), NF, FL.Honey.make(20), OP.gem.mat(MT.Lapis, 9));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Sodalite   .get(1), NF, FL.Honey.make(20), OP.gem.mat(MT.Sodalite, 9));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 1250,1250,1250,1250,1250,1250}, IL.BINNIE_Comb_Emerald    .get(1), NF, FL.Honey.make(20), OP.gemChipped.mat(MT.Maxixe, 1), OP.gemChipped.mat(MT.Aquamarine, 1), OP.gemChipped.mat(MT.Morganite, 1), OP.gemChipped.mat(MT.Heliodor, 1), OP.gemChipped.mat(MT.Goshenite, 1), OP.gemChipped.mat(MT.Bixbite, 1));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Ruby       .get(1), NF, FL.Honey.make(20), OP.gemChipped.mat(MT.Ruby, 1));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 1500, 1500, 1500, 1500, 1500} , IL.BINNIE_Comb_Sapphire   .get(1), NF, FL.Honey.make(20), OP.gemChipped.mat(MT.BlueSapphire, 1), OP.gemChipped.mat(MT.GreenSapphire, 1), OP.gemChipped.mat(MT.YellowSapphire, 1), OP.gemChipped.mat(MT.OrangeSapphire, 1), OP.gemChipped.mat(MT.PurpleSapphire, 1));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Olivine    .get(1), NF, FL.Honey.make(20), OP.gemChipped.mat(MT.Olivine, 1));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Diamond    .get(1), NF, FL.Honey.make(20), OP.gemChipped.mat(MT.Diamond, 1));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64                                             , IL.BINNIE_Comb_Mucous     .get(1), ZL_FS, FL.Honey.make(20), FL.Slime_Green.make(200), FL.Slime_Pink.make(50));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Blaze      .get(1), NF, FL.Honey.make(20), OM.dust(MT.Blaze, U9*2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Shimmering .get(1), NF, FL.Honey.make(20), OM.dust(MT.EnderPearl, U2));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 2500,2500,2500,2500,2500,2500}, IL.BINNIE_Comb_Clay       .get(1), NF, FL.Honey.make(20), ST.make(Items.clay_ball, 1, 0), IL.Clay_Ball_Brown.get(1), IL.Clay_Ball_Red.get(1), IL.Clay_Ball_Yellow.get(1), IL.Clay_Ball_Blue.get(1), IL.Clay_Ball_White.get(1));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Fossil     .get(1), NF, FL.Honey.make(20), OM.dust(MT.Coal, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Brimstone  .get(1), NF, MT.H2SO4.liquid(U10,F), OM.dust(MT.S, U*2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500,  7500}                  , IL.BINNIE_Comb_Unstable   .get(1), NF, MT.HNO3 .liquid(U10,F), OM.dust(MT.KNO3), OM.dust(MT.NaNO3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Pyrite     .get(1), NF, FL.Honey.make(20), OM.dust(MT.Pyrite, U*2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Bauxite    .get(1), NF, FL.Honey.make(20), OM.dust(MT.OREMATS.Bauxite, U*2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Cinnabar   .get(1), NF, FL.Honey.make(20), OM.dust(MT.OREMATS.Cinnabar, U*2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Sphalerite .get(1), NF, FL.Honey.make(20), OM.dust(MT.OREMATS.Sphalerite, U*2));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Iron       .get(1), NF, FL.Honey.make(20), OM.dust(MT.Fe, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Gold       .get(1), NF, FL.Honey.make(20), OM.dust(MT.Au, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Copper     .get(1), NF, FL.Honey.make(20), OM.dust(MT.Cu, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Tin        .get(1), NF, FL.Honey.make(20), OM.dust(MT.Sn, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Silver     .get(1), NF, FL.Honey.make(20), OM.dust(MT.Ag, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Bronze     .get(1), NF, FL.Honey.make(20), OM.dust(MT.Bronze, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Lead       .get(1), NF, FL.Honey.make(20), OM.dust(MT.Pb, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Brass      .get(1), NF, FL.Honey.make(20), OM.dust(MT.Brass, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Electrum   .get(1), NF, FL.Honey.make(20), OM.dust(MT.Electrum, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Zinc       .get(1), NF, FL.Honey.make(20), OM.dust(MT.Zn, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Titanium   .get(1), NF, FL.Honey.make(20), OM.dust(MT.TiO2, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Tungsten   .get(1), NF, FL.Honey.make(20), OM.dust(MT.W, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Steel      .get(1), NF, FL.Honey.make(20), OM.dust(MT.Steel, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Iridium    .get(1), NF, FL.Honey.make(20), OM.dust(MT.Ir, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Platinum   .get(1), NF, FL.Honey.make(20), OM.dust(MT.Pt, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Nickel     .get(1), NF, FL.Honey.make(20), OM.dust(MT.Ni, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 7500}                         , IL.BINNIE_Comb_Invar      .get(1), NF, FL.Honey.make(20), OM.dust(MT.Invar, U2));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 8000,  8000}                  , IL.BINNIE_Comb_Uranium    .get(1), NF, FL.Honey.make(20), OM.dust(MT.U_238, 3*U2), OM.dust(MT.U_235, U2));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 3000}                         , IL.BINNIE_Comb_Yellorium  .get(1), NF, FL.Honey.make(20), OM.dust(MT.Yellorium, U4));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 3000}                         , IL.BINNIE_Comb_Cyanite    .get(1), NF, FL.Honey.make(20), OM.dust(MT.Cyanite, U4));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 3000}                         , IL.BINNIE_Comb_Blutonium  .get(1), NF, FL.Honey.make(20), OM.dust(MT.Blutonium, U4));
			
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Red        .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), IL.BINNIE_Dye_Red.get(3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Yellow     .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), IL.BINNIE_Dye_Yellow.get(3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Blue       .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), IL.BINNIE_Dye_Blue.get(3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Green      .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), IL.BINNIE_Dye_Green.get(3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Black      .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), IL.BINNIE_Dye_Black.get(3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_White      .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), IL.BINNIE_Dye_White.get(3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Brown      .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), IL.BINNIE_Dye_Brown.get(3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Orange     .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), OM.dust(MT.Orange, U*3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Cyan       .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), OM.dust(MT.Cyan, U*3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Purple     .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), OM.dust(MT.Purple, U*3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Gray       .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), OM.dust(MT.Gray, U*3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_LightBlue  .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), OM.dust(MT.LightBlue, U*3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Pink       .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), OM.dust(MT.Pink, U*3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Lime       .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), OM.dust(MT.Lime, U*3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_Magenta    .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), OM.dust(MT.Magenta, U*3));
			RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  7500}                  , IL.BINNIE_Comb_LightGray  .get(1), NF, FL.Honey.make(90), OM.dust(MT.WaxBee), OM.dust(MT.LightGray, U*3));
		}
	}
}
