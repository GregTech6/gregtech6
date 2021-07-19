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

import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.FluidsGT;
import gregapi.data.CS.OreDictToolNames;
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
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 * 
 * Here is basically everything that I want to change to some better location later.
 */
public class Loader_Recipes_Temporary implements Runnable {
	@Override public void run() {
		ItemStack tStack = null;
		
		// TODO: Graphite Electrodes are made from petroleum coke after it is mixed with coal tar pitch. They are then extruded and shaped, baked to carbonize the binder (pitch) and finally graphitized by heating it to temperatures approaching 3273K.
		RM.Extruder.addRecipe2(T, 512, 512, OP.dust.mat(MT.Graphite, 1), IL.Shape_Extruder_Rod.get(0), OP.stick.mat(MT.Graphite, 1));
		
		
		// TODO: I will keep this antiquated shit for now.
		OM.data(CR.get(tStack = OP.ingot.mat(MT.Bronze, 1), tStack, tStack, tStack, null, tStack, tStack, tStack, tStack), new OreDictItemData(MT.Bronze, 8*U));
		OM.data(CR.get(tStack = OP.plate.mat(MT.Bronze, 1), tStack, tStack, tStack, null, tStack, tStack, tStack, tStack), new OreDictItemData(MT.Bronze, 8*U));
		
		
		// TODO: Better Coolant Item than Lapis.
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lapis   , 1*U), FL.DistW.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lapis   , 2*U), FL.SpDew.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lapis   , 2*U), FL.Water.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lazurite, 1*U), FL.DistW.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lazurite, 2*U), FL.SpDew.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lazurite, 2*U), FL.Water.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Sodalite, 1*U), FL.DistW.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Sodalite, 2*U), FL.SpDew.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Sodalite, 2*U), FL.Water.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		

		if (MD.MET.mLoaded) {
			CR.delate(MD.MET, "alloyer");
			CR.delate(OP.dust.mat(MT.Au, 1));
		}
		
		
		if (MD.BbLC.mLoaded) {
			CR.delate(MD.BbLC, "item.BiblioClipboard");
			CR.shaped(ST.make(MD.BbLC, "item.BiblioClipboard", 1, 0), DEF_REV_NCC, "I F", "PPP", " W ", 'F', OD.craftingFeather, 'W', OD.pressurePlateWood, 'P', OD.paperEmpty, 'I', DYE_OREDICTS[DYE_INDEX_Black]);
		}
		
		
		if (MD.BTRS.mLoaded) {
			CR.delate(IL.BTRS_Backpack.wild(1));
			CR.shaped(IL.BTRS_Backpack .get(1), DEF_REV_NCC, "LqL", "SCS", "LPL", 'L', OD.craftingLeather, 'S', OD.itemString, 'P', OP.plate.dat(MT.Au), 'C', OD.craftingChest);
			
			RM.packunpack(ST.make(Items.flint, 9, 0), ST.make(MD.BTRS, "flintBlock", 1, 0));
		}
		
		
		if (MD.HEX.mLoaded) {
			RM.sawing(32, 576, F, 192, ST.make(MD.HEX, "blockHexoriumMonolithRed"           , 1, W), OP.plateGem.mat(MT.HexoriumRed  , 16));
			RM.sawing(32, 576, F, 192, ST.make(MD.HEX, "blockHexoriumMonolithGreen"         , 1, W), OP.plateGem.mat(MT.HexoriumGreen, 16));
			RM.sawing(32, 576, F, 192, ST.make(MD.HEX, "blockHexoriumMonolithBlue"          , 1, W), OP.plateGem.mat(MT.HexoriumBlue , 16));
			RM.sawing(32, 432, F, 144, ST.make(MD.HEX, "blockHexoriumMonolithBlack"         , 1, W), OP.plateGem.mat(MT.HexoriumBlack, 12));
			RM.sawing(32, 432, F, 144, ST.make(MD.HEX, "blockHexoriumMonolithWhite"         , 1, W), OP.plateGem.mat(MT.HexoriumWhite, 12));
			RM.sawing(32, 432, F, 144, ST.make(MD.HEX, "blockHexoriumNetherMonolithRed"     , 1, W), OP.plateGem.mat(MT.HexoriumRed  , 12));
			RM.sawing(32, 432, F, 144, ST.make(MD.HEX, "blockHexoriumNetherMonolithGreen"   , 1, W), OP.plateGem.mat(MT.HexoriumGreen, 12));
			RM.sawing(32, 432, F, 144, ST.make(MD.HEX, "blockHexoriumNetherMonolithBlue"    , 1, W), OP.plateGem.mat(MT.HexoriumBlue , 12));
			RM.sawing(32, 576, F, 192, ST.make(MD.HEX, "blockHexoriumNetherMonolithBlack"   , 1, W), OP.plateGem.mat(MT.HexoriumBlack, 16));
			RM.sawing(32, 576, F, 192, ST.make(MD.HEX, "blockHexoriumNetherMonolithWhite"   , 1, W), OP.plateGem.mat(MT.HexoriumWhite, 16));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumMonolithRed"  , 1, W), OP.plateGem.mat(MT.HexoriumRed  ,  8));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumMonolithGreen", 1, W), OP.plateGem.mat(MT.HexoriumGreen,  8));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumMonolithBlue" , 1, W), OP.plateGem.mat(MT.HexoriumBlue ,  8));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumMonolithBlack", 1, W), OP.plateGem.mat(MT.HexoriumBlack,  8));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumMonolithWhite", 1, W), OP.plateGem.mat(MT.HexoriumWhite,  8));
			
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumRed"      , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumRed"      , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumGreen"    , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumGreen"    , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumBlue"     , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumBlue"     , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumBlack"    , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumBlack"    , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumWhite"    , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumWhite"    , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumDarkGray" , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumDarkGray" , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumGray"     , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumGray"     , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumLightGray", 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumLightGray", 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumOrange"   , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumOrange"   , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumYellow"   , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumYellow"   , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumLime"     , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumLime"     , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumTurquoise", 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumTurquoise", 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumCyan"     , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumCyan"     , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumSkyBlue"  , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumSkyBlue"  , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumPurple"   , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumPurple"   , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumMagenta"  , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumMagenta"  , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumPink"     , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumPink"     , 8, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumRainbow"  , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumRainbow"  , 8, 0));
			
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumRed"      , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumRed"      , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumGreen"    , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumGreen"    , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumBlue"     , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumBlue"     , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumBlack"    , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumBlack"    , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumWhite"    , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumWhite"    , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumDarkGray" , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumDarkGray" , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumGray"     , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumGray"     , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumLightGray", 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumLightGray", 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumOrange"   , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumOrange"   , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumYellow"   , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumYellow"   , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumLime"     , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumLime"     , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumTurquoise", 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumTurquoise", 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumCyan"     , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumCyan"     , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumSkyBlue"  , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumSkyBlue"  , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumPurple"   , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumPurple"   , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumMagenta"  , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumMagenta"  , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumPink"     , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumPink"     , 1, 0));
			RM.compact(ST.make(MD.HEX, "blockMiniEnergizedHexoriumRainbow"  , 8, W), ST.make(MD.HEX, "blockEnergizedHexoriumRainbow"  , 1, 0));
			
			
			
			new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
			addListener(DYE_OREDICTS_MIXABLE[DYE_INDEX_Black], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
				if (ST.container(aEvent.mStack, T) == null)
				RM.Mixer.addRecipe2(T, 16, 16, aEvent.mStack, OP.dust.mat(MT.HexoriumBlack, 1), ST.make(MD.HEX, "itemHexoriumDye", 16, 0));
			}});
			addListener(DYE_OREDICTS_MIXABLE[DYE_INDEX_White], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
				if (ST.container(aEvent.mStack, T) == null)
				RM.Mixer.addRecipe2(T, 16, 16, aEvent.mStack, OP.dust.mat(MT.HexoriumWhite, 1), ST.make(MD.HEX, "itemHexoriumDyeWhite", 16, 0));
			}});
			addListener(OP.plateGem.dat(MT.HexoriumBlack), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
				RM.add_smelting(aEvent.mStack, ST.make(MD.HEX, "itemBlackHexoriumWafer", 1, 0), F, F, F);
			}});
			addListener(OP.plateGem.dat(MT.HexoriumWhite), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
				RM.add_smelting(aEvent.mStack, ST.make(MD.HEX, "itemWhiteHexoriumWafer", 1, 0), F, F, F);
			}});
			}};
		}
		
		
		if (MD.HEE.mLoaded) {
			RM.biomass(ST.make(MD.HEE, "crossed_decoration", 8, W));
			RM.biomass(ST.make(MD.HEE, "death_flower"      , 8, W));
			
			RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.HEE, "death_flower", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta], ST.make(Items.dye, 2, DYE_INDEX_Magenta));
			RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.HEE, "death_flower", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta], ST.make(Items.dye, 2, DYE_INDEX_Magenta));
			RM.ic2_extractor(ST.make(MD.HEE, "death_flower", 1, 0), ST.make(Items.dye, 3, DYE_INDEX_Magenta));
			
			RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.HEE, "death_flower", 1,15), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Gray], ST.make(Items.dye, 2, DYE_INDEX_Gray));
			RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.HEE, "death_flower", 1,15), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Gray], ST.make(Items.dye, 2, DYE_INDEX_Gray));
			RM.ic2_extractor(ST.make(MD.HEE, "death_flower", 1,15), ST.make(Items.dye, 3, DYE_INDEX_Gray));
			
			RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.HEE, "crossed_decoration", 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange], ST.make(Items.dye, 2, DYE_INDEX_Orange));
			RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.HEE, "crossed_decoration", 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange], ST.make(Items.dye, 2, DYE_INDEX_Orange));
			RM.ic2_extractor(ST.make(MD.HEE, "crossed_decoration", 1, 6), ST.make(Items.dye, 3, DYE_INDEX_Orange));
			
			RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.HEE, "crossed_decoration", 1,13), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 2, DYE_INDEX_Purple));
			RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.HEE, "crossed_decoration", 1,13), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 2, DYE_INDEX_Purple));
			RM.ic2_extractor(ST.make(MD.HEE, "crossed_decoration", 1,13), ST.make(Items.dye, 3, DYE_INDEX_Purple));
		}
		
		
		if (MD.MO.mLoaded) {
			RM.LaserEngraver.addRecipe2(T, 16, 1000, ST.make(MD.MO, "isolinear_circuit", 1, 0), OP.plate.mat(MT.Au, 1), ST.make(MD.MO, "isolinear_circuit", 1, 1));
			for (OreDictMaterial tMat : ANY.Diamond.mToThis)
			RM.LaserEngraver.addRecipe2(T, 32, 1000, ST.make(MD.MO, "isolinear_circuit", 1, 1), OP.plateGem.mat(tMat, 1), ST.make(MD.MO, "isolinear_circuit", 1, 2));
			for (OreDictMaterial tMat : ANY.Emerald.mToThis)
			RM.LaserEngraver.addRecipe2(T, 64, 1000, ST.make(MD.MO, "isolinear_circuit", 1, 2), OP.plateGem.mat(tMat, 1), ST.make(MD.MO, "isolinear_circuit", 1, 3));
		}
		
		
		if (MD.HBM.mLoaded) {
			CR.delate(MD.HBM, "item.apple_lead", "item.apple_schrabidium", "item.apple_euphemium");
			
			CR.shapeless(IL.HBM_Mercury_Drop.get(8), CR.DEF_NCC, new Object[] {IL.Bottle_Mercury});
			RM.generify(IL.HBM_Mercury_Bottle.get(1), IL.Bottle_Mercury.get(1));
			RM.generify(IL.Bottle_Mercury.get(1), IL.HBM_Mercury_Bottle.get(1));
			
			
			RM.Bath.addRecipe1(T, 0, 128, ST.make(MD.HBM, "item.apple_lead", 1, 0), MT.Pb.liquid(64*U9, T), NF, ST.make(MD.HBM, "item.apple_lead", 1, 1));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(MD.HBM, "item.apple_lead", 1, 1), MT.Pb.liquid(64*U , T), NF, ST.make(MD.HBM, "item.apple_lead", 1, 2));
			
			RM.Bath.addRecipe1(T, 0, 128, ST.make(MD.HBM, "item.apple_schrabidium", 1, 0), MT.UNUSED.Schrabidium.liquid(64*U9, T), NF, ST.make(MD.HBM, "item.apple_schrabidium", 1, 1));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(MD.HBM, "item.apple_schrabidium", 1, 1), MT.UNUSED.Schrabidium.liquid(64*U , T), NF, ST.make(MD.HBM, "item.apple_schrabidium", 1, 2));
			
			new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
			addListener("cropApple", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
				if (OM.is("cropAppleWhite", aEvent.mStack) || OM.is("cropCrabapple", aEvent.mStack)) return;
				RM.Bath.addRecipe1(T, 0, 128, aEvent.mStack, MT.Pb                .liquid(8*U9, T), NF, ST.make(MD.HBM, "item.apple_lead"       , 1, 0));
				RM.Bath.addRecipe1(T, 0, 128, aEvent.mStack, MT.UNUSED.Schrabidium.liquid(8*U9, T), NF, ST.make(MD.HBM, "item.apple_schrabidium", 1, 0));
				RM.Bath.addRecipe1(T, 0, 128, aEvent.mStack, MT.UNUSED.Euphemium  .liquid(8*U9, T), NF, ST.make(MD.HBM, "item.apple_euphemium"  , 1, 0));
			}});
			}};
		}
		
		
		if (MD.NeLi.mLoaded) {
			CR.delate(MD.NeLi, "Chain", "ChainEfrine", "ChainGold", "ChainPowered", "ChainPoweredEfrine", "ChainPoweredGold", "EmptyLantern", "EmptyLanternEfrine", "EmptyLanternGold", "Lantern", "LanternEfrine", "LanternGold", "GlowstoneLantern", "GlowstoneLanternEfrine", "GlowstoneLanternGold", "FoxfireLantern", "FoxfireLanternEfrine", "FoxfireLanternGold", "SoulLantern", "SoulLanternEfrine", "SoulLanternGold", "RedstoneLantern", "RedstoneLanternEfrine", "RedstoneLanternGold", "RedstoneLanternOn", "RedstoneLanternEfrineOn", "RedstoneLanternGoldOn");
			
			CR.remove(IL.NeLi_Reed.get(1));
			CR.remove(IL.NeLi_Reed.get(1), IL.NeLi_Reed.get(1), IL.NeLi_Reed.get(1));
			CR.shaped(ST.make(Items.paper, 1, 0), DEF, "XXX", 'X', IL.NeLi_Reed);
			
			// HAS TO BE BEFORE REGULAR TORCHES!!!
			CR.shapeless(IL.NeLi_Bonetorch_Soul  .get(4), DEF_NCC, new Object[] {OP.dust.dat(MT.SoulSand) , IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Soul  .get(4), DEF_NCC, new Object[] {OD.flowerWither          , IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Soul  .get(3), DEF_NCC, new Object[] {OP.dust.dat(MT.SoulSand) , IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Soul  .get(3), DEF_NCC, new Object[] {OD.flowerWither          , IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Soul  .get(2), DEF_NCC, new Object[] {OP.dust.dat(MT.SoulSand) , IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Soul  .get(2), DEF_NCC, new Object[] {OD.flowerWither          , IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Soul  .get(1), DEF_NCC, new Object[] {OP.dust.dat(MT.SoulSand) , IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Soul  .get(1), DEF_NCC, new Object[] {OD.flowerWither          , IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Fox   .get(4), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Powder   , IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Fox   .get(4), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Lily     , IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Fox   .get(3), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Powder   , IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Fox   .get(3), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Lily     , IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Fox   .get(2), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Powder   , IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Fox   .get(2), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Lily     , IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Fox   .get(1), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Powder   , IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Fox   .get(1), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Lily     , IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Shadow.get(4), DEF_NCC, new Object[] {IL.NeLi_Blackstone_Crying, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Shadow.get(3), DEF_NCC, new Object[] {IL.NeLi_Blackstone_Crying, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Shadow.get(2), DEF_NCC, new Object[] {IL.NeLi_Blackstone_Crying, IL.NeLi_Bonetorch, IL.NeLi_Bonetorch});
			CR.shapeless(IL.NeLi_Bonetorch_Shadow.get(1), DEF_NCC, new Object[] {IL.NeLi_Blackstone_Crying, IL.NeLi_Bonetorch});
			
			// HAS TO BE BEFORE SPECIAL TORCHES!!!
			CR.shapeless(IL.NeLi_Torch_Soul      .get(4), DEF_NCC, new Object[] {OP.dust.dat(MT.SoulSand) , OD.blockTorch, OD.blockTorch, OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Soul      .get(4), DEF_NCC, new Object[] {OD.flowerWither          , OD.blockTorch, OD.blockTorch, OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Soul      .get(3), DEF_NCC, new Object[] {OP.dust.dat(MT.SoulSand) , OD.blockTorch, OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Soul      .get(3), DEF_NCC, new Object[] {OD.flowerWither          , OD.blockTorch, OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Soul      .get(2), DEF_NCC, new Object[] {OP.dust.dat(MT.SoulSand) , OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Soul      .get(2), DEF_NCC, new Object[] {OD.flowerWither          , OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Soul      .get(1), DEF_NCC, new Object[] {OP.dust.dat(MT.SoulSand) , OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Soul      .get(1), DEF_NCC, new Object[] {OD.flowerWither          , OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Fox       .get(4), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Powder   , OD.blockTorch, OD.blockTorch, OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Fox       .get(4), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Lily     , OD.blockTorch, OD.blockTorch, OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Fox       .get(3), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Powder   , OD.blockTorch, OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Fox       .get(3), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Lily     , OD.blockTorch, OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Fox       .get(2), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Powder   , OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Fox       .get(2), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Lily     , OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Fox       .get(1), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Powder   , OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Fox       .get(1), DEF_NCC, new Object[] {IL.NeLi_Foxfire_Lily     , OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Shadow    .get(4), DEF_NCC, new Object[] {IL.NeLi_Blackstone_Crying, OD.blockTorch, OD.blockTorch, OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Shadow    .get(3), DEF_NCC, new Object[] {IL.NeLi_Blackstone_Crying, OD.blockTorch, OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Shadow    .get(2), DEF_NCC, new Object[] {IL.NeLi_Blackstone_Crying, OD.blockTorch, OD.blockTorch});
			CR.shapeless(IL.NeLi_Torch_Shadow    .get(1), DEF_NCC, new Object[] {IL.NeLi_Blackstone_Crying, OD.blockTorch});
			
			CR.shaped(ST.make(MD.NeLi, "EmptyLantern"          , 1, 0), DEF_REV_NCC, "PTP", "P P", "PPP", 'P', OP.plateTiny.dat(ANY.Iron ), 'T', OP.screw.dat(ANY.Iron ));
			CR.shaped(ST.make(MD.NeLi, "EmptyLanternGold"      , 1, 0), DEF_REV_NCC, "PTP", "P P", "PPP", 'P', OP.plateTiny.dat(MT.Au    ), 'T', OP.screw.dat(MT.Au    ));
			CR.shaped(ST.make(MD.NeLi, "EmptyLanternEfrine"    , 1, 0), DEF_REV_NCC, "PTP", "P P", "PPP", 'P', OP.plateTiny.dat(MT.Efrine), 'T', OP.screw.dat(MT.Efrine));
			CR.shaped(ST.make(MD.NeLi, "Lantern"               , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(ANY.Iron ), 'T', OP.screw.dat(ANY.Iron ), 'X', OD.blockTorch);
			CR.shaped(ST.make(MD.NeLi, "LanternGold"           , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(MT.Au    ), 'T', OP.screw.dat(MT.Au    ), 'X', OD.blockTorch);
			CR.shaped(ST.make(MD.NeLi, "LanternEfrine"         , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(MT.Efrine), 'T', OP.screw.dat(MT.Efrine), 'X', OD.blockTorch);
			CR.shaped(ST.make(MD.NeLi, "GlowstoneLantern"      , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(ANY.Iron ), 'T', OP.screw.dat(ANY.Iron ), 'X', OD.glowstone);
			CR.shaped(ST.make(MD.NeLi, "GlowstoneLanternGold"  , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(MT.Au    ), 'T', OP.screw.dat(MT.Au    ), 'X', OD.glowstone);
			CR.shaped(ST.make(MD.NeLi, "GlowstoneLanternEfrine", 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(MT.Efrine), 'T', OP.screw.dat(MT.Efrine), 'X', OD.glowstone);
			CR.shaped(ST.make(MD.NeLi, "FoxfireLantern"        , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(ANY.Iron ), 'T', OP.screw.dat(ANY.Iron ), 'X', OD.blockFoxfireTorch);
			CR.shaped(ST.make(MD.NeLi, "FoxfireLanternGold"    , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(MT.Au    ), 'T', OP.screw.dat(MT.Au    ), 'X', OD.blockFoxfireTorch);
			CR.shaped(ST.make(MD.NeLi, "FoxfireLanternEfrine"  , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(MT.Efrine), 'T', OP.screw.dat(MT.Efrine), 'X', OD.blockFoxfireTorch);
			CR.shaped(ST.make(MD.NeLi, "SoulLantern"           , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(ANY.Iron ), 'T', OP.screw.dat(ANY.Iron ), 'X', OD.blockSoulTorch);
			CR.shaped(ST.make(MD.NeLi, "SoulLanternGold"       , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(MT.Au    ), 'T', OP.screw.dat(MT.Au    ), 'X', OD.blockSoulTorch);
			CR.shaped(ST.make(MD.NeLi, "SoulLanternEfrine"     , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(MT.Efrine), 'T', OP.screw.dat(MT.Efrine), 'X', OD.blockSoulTorch);
			CR.shaped(ST.make(MD.NeLi, "ShadowLantern"         , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(ANY.Iron ), 'T', OP.screw.dat(ANY.Iron ), 'X', OD.blockShadowTorch);
			CR.shaped(ST.make(MD.NeLi, "ShadowLanternGold"     , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(MT.Au    ), 'T', OP.screw.dat(MT.Au    ), 'X', OD.blockShadowTorch);
			CR.shaped(ST.make(MD.NeLi, "ShadowLanternEfrine"   , 1, 0), DEF_REV_NCC, "PTP", "PXP", "PPP", 'P', OP.plateTiny.dat(MT.Efrine), 'T', OP.screw.dat(MT.Efrine), 'X', OD.blockShadowTorch);
			CR.shaped(ST.make(MD.NeLi, "RedstoneLantern"       , 1, 0), DEF_REV_NCC, "R", "L", 'L', ST.make(MD.NeLi, "GlowstoneLantern"      , 1, 0), 'R', OD.itemRedstone);
			CR.shaped(ST.make(MD.NeLi, "RedstoneLanternGold"   , 1, 0), DEF_REV_NCC, "R", "L", 'L', ST.make(MD.NeLi, "GlowstoneLanternGold"  , 1, 0), 'R', OD.itemRedstone);
			CR.shaped(ST.make(MD.NeLi, "RedstoneLanternEfrine" , 1, 0), DEF_REV_NCC, "R", "L", 'L', ST.make(MD.NeLi, "GlowstoneLanternEfrine", 1, 0), 'R', OD.itemRedstone);
			
			CR.shapeless(ST.make(MD.NeLi, "Lantern"               , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLantern"      , 1, 0), OD.blockTorch});
			CR.shapeless(ST.make(MD.NeLi, "LanternGold"           , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLanternGold"  , 1, 0), OD.blockTorch});
			CR.shapeless(ST.make(MD.NeLi, "LanternEfrine"         , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLanternEfrine", 1, 0), OD.blockTorch});
			CR.shapeless(ST.make(MD.NeLi, "GlowstoneLantern"      , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLantern"      , 1, 0), OD.glowstone});
			CR.shapeless(ST.make(MD.NeLi, "GlowstoneLanternGold"  , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLanternGold"  , 1, 0), OD.glowstone});
			CR.shapeless(ST.make(MD.NeLi, "GlowstoneLanternEfrine", 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLanternEfrine", 1, 0), OD.glowstone});
			CR.shapeless(ST.make(MD.NeLi, "FoxfireLantern"        , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLantern"      , 1, 0), OD.blockFoxfireTorch});
			CR.shapeless(ST.make(MD.NeLi, "FoxfireLanternGold"    , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLanternGold"  , 1, 0), OD.blockFoxfireTorch});
			CR.shapeless(ST.make(MD.NeLi, "FoxfireLanternEfrine"  , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLanternEfrine", 1, 0), OD.blockFoxfireTorch});
			CR.shapeless(ST.make(MD.NeLi, "SoulLantern"           , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLantern"      , 1, 0), OD.blockSoulTorch});
			CR.shapeless(ST.make(MD.NeLi, "SoulLanternGold"       , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLanternGold"  , 1, 0), OD.blockSoulTorch});
			CR.shapeless(ST.make(MD.NeLi, "SoulLanternEfrine"     , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLanternEfrine", 1, 0), OD.blockSoulTorch});
			CR.shapeless(ST.make(MD.NeLi, "ShadowLantern"         , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLantern"      , 1, 0), OD.blockShadowTorch});
			CR.shapeless(ST.make(MD.NeLi, "ShadowLanternGold"     , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLanternGold"  , 1, 0), OD.blockShadowTorch});
			CR.shapeless(ST.make(MD.NeLi, "ShadowLanternEfrine"   , 1, 0), DEF_NCC, new Object[] {ST.make(MD.NeLi, "EmptyLanternEfrine", 1, 0), OD.blockShadowTorch});
			
			CR.shaped(ST.make(MD.NeLi, "RespawnAnchor"         , 1, 0), DEF_REM_REV, "OOO", "GGG", "OOO", 'O', OD.cryingObsidian, 'G', OD.glowstone);
			
			CR.shaped(IL.NeLi_Lamp_White           .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteWhite  ), 'G', OD.glowstone);
			CR.shaped(IL.NeLi_Lamp_Blue            .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteBlue   ), 'G', OD.glowstone);
			CR.shaped(IL.NeLi_Lamp_Green           .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteGreen  ), 'G', OD.glowstone);
			CR.shaped(IL.NeLi_Lamp_Yellow          .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteYellow ), 'G', OD.glowstone);
			CR.shaped(IL.NeLi_Lamp_Magenta         .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteMagenta), 'G', OD.glowstone);
			CR.shaped(IL.NeLi_Redstone_Lamp_White  .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteWhite  ), 'G', Blocks.redstone_lamp);
			CR.shaped(IL.NeLi_Redstone_Lamp_Blue   .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteBlue   ), 'G', Blocks.redstone_lamp);
			CR.shaped(IL.NeLi_Redstone_Lamp_Green  .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteGreen  ), 'G', Blocks.redstone_lamp);
			CR.shaped(IL.NeLi_Redstone_Lamp_Yellow .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteYellow ), 'G', Blocks.redstone_lamp);
			CR.shaped(IL.NeLi_Redstone_Lamp_Magenta.get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteMagenta), 'G', Blocks.redstone_lamp);
			CR.shaped(IL.NeLi_Redstone_Lamp_White  .get(1), DEF_NCC, " R ", "RGR", " R ", 'R', OD.itemRedstone, 'G', IL.NeLi_Lamp_White  );
			CR.shaped(IL.NeLi_Redstone_Lamp_Blue   .get(1), DEF_NCC, " R ", "RGR", " R ", 'R', OD.itemRedstone, 'G', IL.NeLi_Lamp_Blue   );
			CR.shaped(IL.NeLi_Redstone_Lamp_Green  .get(1), DEF_NCC, " R ", "RGR", " R ", 'R', OD.itemRedstone, 'G', IL.NeLi_Lamp_Green  );
			CR.shaped(IL.NeLi_Redstone_Lamp_Yellow .get(1), DEF_NCC, " R ", "RGR", " R ", 'R', OD.itemRedstone, 'G', IL.NeLi_Lamp_Yellow );
			CR.shaped(IL.NeLi_Redstone_Lamp_Magenta.get(1), DEF_NCC, " R ", "RGR", " R ", 'R', OD.itemRedstone, 'G', IL.NeLi_Lamp_Magenta);
			CR.shaped(IL.NeLi_Glass_White          .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteWhite  ), 'G', OD.blockGlassColorless);
			CR.shaped(IL.NeLi_Glass_Blue           .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteBlue   ), 'G', OD.blockGlassColorless);
			CR.shaped(IL.NeLi_Glass_Green          .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteGreen  ), 'G', OD.blockGlassColorless);
			CR.shaped(IL.NeLi_Glass_Yellow         .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteYellow ), 'G', OD.blockGlassColorless);
			CR.shaped(IL.NeLi_Glass_Magenta        .get(1), DEF_NCC, " F ", "FGF", " F ", 'F', OP.gem.dat(MT.FluoriteMagenta), 'G', OD.blockGlassColorless);
			
			for (FluidStack tWater : FL.array(FL.Water.make(125), FL.SpDew.make(125), FL.DistW.make(100)))
			RM.Bath    .addRecipe1(T,  0, 16           , IL.NeLi_Reed.get(1), tWater, NF, ST.make(Items.paper, 1, 0));
			RM.Loom    .addRecipe2(T, 16, 16, ST.tag(0), IL.NeLi_Reed.get(1), ST.make(Items.paper, 1, 0));
			RM.Squeezer.addRecipe1(T, 16, 16,      4000, IL.NeLi_Reed.get(1), NF, FL.Juice_Reed.make(10), IL.Remains_Plant.get(1));
			RM.Juicer  .addRecipe1(T, 16, 16,      5000, IL.NeLi_Reed.get(1), NF, FL.Juice_Reed.make( 5), IL.Remains_Plant.get(1));
			RM.Shredder.addRecipe1(T, 16, 16           , IL.NeLi_Reed.get(1), IL.Remains_Plant.get(1));
			RM.pulverizing(IL.NeLi_Reed.get(1), IL.Remains_Plant.get(1), T);
			
			RM.Mixer.addRecipe2(T, 16, 16, IL.NeLi_Foxfire_Lily.get(1), OP.dustTiny .mat(MT.Blaze, 1), IL.NeLi_Foxfire_Powder.get(1));
			RM.Mixer.addRecipe2(T, 16,144, IL.NeLi_Foxfire_Lily.get(9), OP.dust     .mat(MT.Blaze, 1), IL.NeLi_Foxfire_Powder.get(9));
			
			RM.Distillery.addRecipe1(T, 16, 32, IL.NeLi_Wart_Crimson  .get(1), FL.DistW         .make(500), FL.Potion_Awkward         .make(500), ZL_IS);
			RM.Distillery.addRecipe1(T, 16, 32, IL.NeLi_Wart_Warped   .get(1), FL.DistW         .make(500), FL.Potion_Thick           .make(500), ZL_IS);
			RM.Distillery.addRecipe1(T, 16, 32, IL.NeLi_Wart_Warped   .get(1), FL.Potion_Awkward.make(500), FL.Potion_NightVision_1   .make(500), ZL_IS);
			RM.Distillery.addRecipe1(T, 16, 32, IL.NeLi_Wart_Soggy    .get(1), FL.DistW         .make(500), FL.Potion_Mundane         .make(500), ZL_IS);
			RM.Distillery.addRecipe1(T, 16, 32, IL.NeLi_Wart_Soggy    .get(1), FL.Potion_Awkward.make(500), FL.Potion_FireResistance_1.make(500), ZL_IS);
			RM.Distillery.addRecipe1(T, 16, 48, IL.NeLi_Wither_Rose   .get(1), FL.Potion_Thick  .make(750), FL.Potion_Harm_2          .make(750), ZL_IS);
			RM.Distillery.addRecipe1(T, 16, 48, IL.NeLi_Bone_Rose     .get(1), FL.Potion_Mundane.make(750), FL.Potion_Jump_1          .make(750), ZL_IS);
			RM.Distillery.addRecipe1(T, 16, 48, IL.NeLi_Soul_Rose     .get(1), FL.Potion_Awkward.make(250), FL.Potion_Invisibility_1  .make(250), ZL_IS);
			RM.Distillery.addRecipe1(T, 16, 48, IL.NeLi_Foxfire_Powder.get(1), FL.Potion_Mundane.make(750), FL.Potion_Strength_2      .make(750), ZL_IS);
			
			RM.Squeezer.addRecipe1(T, 16, 16, IL.NeLi_Foxfire_Lily.get(1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
			RM.Juicer  .addRecipe1(T, 16, 16, IL.NeLi_Foxfire_Lily.get(1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
			RM.ic2_extractor(IL.NeLi_Foxfire_Lily.get(1), ST.make(Items.dye, 2, DYE_INDEX_Purple));
			
			RM.Squeezer.addRecipe1(T, 16, 16, IL.NeLi_Wither_Rose.get(1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Black], ST.make(MD.NeLi, "dye", 1, 0));
			RM.Juicer  .addRecipe1(T, 16, 16, IL.NeLi_Wither_Rose.get(1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Black], ST.make(MD.NeLi, "dye", 1, 0));
			RM.ic2_extractor(IL.NeLi_Wither_Rose.get(1), ST.make(MD.NeLi, "dye", 2, 0));
			
			RM.Squeezer.addRecipe1(T, 16, 16, IL.NeLi_Gloom_Hibiscus.get(1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], ST.make(MD.NeLi, "dye", 1, 1));
			RM.Juicer  .addRecipe1(T, 16, 16, IL.NeLi_Gloom_Hibiscus.get(1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], ST.make(MD.NeLi, "dye", 1, 1));
			RM.ic2_extractor(IL.NeLi_Gloom_Hibiscus.get(1), ST.make(MD.NeLi, "dye", 2, 1));
			
			RM.Squeezer.addRecipe1(T, 16, 16, IL.NeLi_Bone_Rose.get(1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], ST.make(MD.NeLi, "dye", 1, 2));
			RM.Juicer  .addRecipe1(T, 16, 16, IL.NeLi_Bone_Rose.get(1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], ST.make(MD.NeLi, "dye", 1, 2));
			RM.ic2_extractor(IL.NeLi_Bone_Rose.get(1), ST.make(MD.NeLi, "dye", 2, 2));
			
			RM.Squeezer.addRecipe1(T, 16, 16, IL.NeLi_Soul_Rose.get(1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], ST.make(Items.dye, 1, DYE_INDEX_LightBlue));
			RM.Juicer  .addRecipe1(T, 16, 16, IL.NeLi_Soul_Rose.get(1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], ST.make(Items.dye, 1, DYE_INDEX_LightBlue));
			RM.ic2_extractor(IL.NeLi_Soul_Rose.get(1), ST.make(Items.dye, 2, DYE_INDEX_LightBlue));
			
			RM.glowstone(IL.NeLi_Gloomstone.get(1), MT.Gloomstone);
			
			RM.compactsmash(OP.gem.mat(MT.FluoriteWhite  , 1), 4, IL.NeLi_Crystal_White  .get(1));
			RM.compactsmash(OP.gem.mat(MT.FluoriteBlue   , 1), 4, IL.NeLi_Crystal_Blue   .get(1));
			RM.compactsmash(OP.gem.mat(MT.FluoriteGreen  , 1), 4, IL.NeLi_Crystal_Green  .get(1));
			RM.compactsmash(OP.gem.mat(MT.FluoriteYellow , 1), 4, IL.NeLi_Crystal_Yellow .get(1));
			RM.compactsmash(OP.gem.mat(MT.FluoriteMagenta, 1), 4, IL.NeLi_Crystal_Magenta.get(1));
			
			RM.sawing(16, 64, F, 25, IL.NeLi_Crystal_White  .get(1), OP.plateGem.mat(MT.FluoriteWhite  , 4));
			RM.sawing(16, 64, F, 25, IL.NeLi_Crystal_Blue   .get(1), OP.plateGem.mat(MT.FluoriteBlue   , 4));
			RM.sawing(16, 64, F, 25, IL.NeLi_Crystal_Green  .get(1), OP.plateGem.mat(MT.FluoriteGreen  , 4));
			RM.sawing(16, 64, F, 25, IL.NeLi_Crystal_Yellow .get(1), OP.plateGem.mat(MT.FluoriteYellow , 4));
			RM.sawing(16, 64, F, 25, IL.NeLi_Crystal_Magenta.get(1), OP.plateGem.mat(MT.FluoriteMagenta, 4));
			
			RM.lathing(16, 64, IL.NeLi_Crystal_White  .get(1), OP.stickLong.mat(MT.FluoriteWhite  , 2), OM.dust(MT.FluoriteWhite  , 2*U));
			RM.lathing(16, 64, IL.NeLi_Crystal_Blue   .get(1), OP.stickLong.mat(MT.FluoriteBlue   , 2), OM.dust(MT.FluoriteBlue   , 2*U));
			RM.lathing(16, 64, IL.NeLi_Crystal_Green  .get(1), OP.stickLong.mat(MT.FluoriteGreen  , 2), OM.dust(MT.FluoriteGreen  , 2*U));
			RM.lathing(16, 64, IL.NeLi_Crystal_Yellow .get(1), OP.stickLong.mat(MT.FluoriteYellow , 2), OM.dust(MT.FluoriteYellow , 2*U));
			RM.lathing(16, 64, IL.NeLi_Crystal_Magenta.get(1), OP.stickLong.mat(MT.FluoriteMagenta, 2), OM.dust(MT.FluoriteMagenta, 2*U));
			
			RM.biomass(ST.make(MD.NeLi, "Fungus"           , 8, W));
			RM.biomass(ST.make(MD.NeLi, "Wartblock"        , 1, W));
			RM.biomass(ST.make(MD.NeLi, "Roots"            , 8, W));
			RM.biomass(ST.make(MD.NeLi, "NetherGrass"      , 8, W));
			RM.biomass(ST.make(MD.NeLi, "NetherFlowerShrub", 8, W));
			RM.biomass(ST.make(MD.NeLi, "DoublePlants"     , 8, W));
			RM.biomass(ST.make(MD.NeLi, "GroundCover"      , 8, W));
			RM.biomass(ST.make(MD.NeLi, "PotPlants"        , 8, W));
			RM.biomass(ST.make(MD.NeLi, "Plants"           , 8, W));
			RM.biomass(ST.make(MD.NeLi, "Plants2"          , 8, W));
			RM.biomass(ST.make(MD.NeLi, "TwistingVines"    , 8, W));
			RM.biomass(ST.make(MD.NeLi, "WeepingVines"     , 8, W));
			RM.biomass(ST.make(MD.NeLi, "Sprouts"          , 8, W));
			RM.biomass(ST.make(MD.NeLi, "RoastedWart"      , 8, W));
			RM.biomass(IL.NeLi_Wart_Crimson               .wild(8));
			RM.biomass(IL.NeLi_Reed                       .wild(8));
			RM.biomass(IL.NeLi_Wither_Rose                .wild(8));
			RM.biomass(IL.NeLi_Foxfire_Lily               .wild(8));
			
			if (!IL.EtFu_Wart_Block_Nether.exists())
			RM.compact(ST.make(Items.nether_wart, 1, 0), 9, IL.NeLi_Wart_Block_Nether .get(1));
			RM.compact(IL.NeLi_Wart_Crimson     .get(1), 9, IL.NeLi_Wart_Block_Crimson.get(1));
			RM.compact(IL.NeLi_Wart_Warped      .get(1), 9, IL.NeLi_Wart_Block_Warped .get(1));
			RM.compact(IL.NeLi_Wart_Soggy       .get(1), 9, IL.NeLi_Wart_Block_Soggy  .get(1));
			
			RM.box(ST.make(Items.bowl, 1, W), IL.NeLi_Bowl_CrimsonStew  .get(1), ST.make(MD.NeLi, "Fungus", 2, 0));
			RM.box(ST.make(Items.bowl, 1, W), IL.NeLi_Bowl_WarpedStew   .get(1), ST.make(MD.NeLi, "Fungus", 2, 1));
			RM.box(ST.make(Items.bowl, 1, W), IL.NeLi_Bowl_FoxfireStew  .get(1), ST.make(MD.NeLi, "Fungus", 2, 2));
			RM.box(ST.make(Items.bowl, 1, W), IL.NeLi_Bowl_DevilishMaize.get(1), ST.make(MD.NeLi, "DevilishMaizeSeeds", 2, 0));
			
			RM.add_smelting(ST.make(Items.nether_wart, 1, 0), ST.make(MD.NeLi, "RoastedWart", 1, 0), 0.05F, F, T, F);
			
			RM.smash(IL.NeLi_Quartz_Bricks                    .get(1), OP.gem.mat(MT.NetherQuartz, 4));
			RM.smash(IL.NeLi_Quartz_Smooth                    .get(1), OP.gem.mat(MT.NetherQuartz, 4));
			RM.smash(IL.NeLi_Quartz_Chiseled_Pillar           .get(1), OP.gem.mat(MT.NetherQuartz, 4));
			RM.smash(IL.NeLi_Void_Block                       .get(1), OP.gem.mat(MT.VoidQuartz, 4));
			RM.smash(IL.NeLi_Void_Bricks                      .get(1), OP.gem.mat(MT.VoidQuartz, 4));
			RM.smash(IL.NeLi_Void_Smooth                      .get(1), OP.gem.mat(MT.VoidQuartz, 4));
			RM.smash(IL.NeLi_Void_Chiseled                    .get(1), OP.gem.mat(MT.VoidQuartz, 4));
			RM.smash(IL.NeLi_Void_Pillar                      .get(1), OP.gem.mat(MT.VoidQuartz, 4));
			RM.smash(IL.NeLi_Void_Chiseled_Pillar             .get(1), OP.gem.mat(MT.VoidQuartz, 4));
			RM.smash(ST.make(MD.NeLi, "QuartzWall"            , 1, 0), OP.gem.mat(MT.NetherQuartz, 4));
			RM.smash(ST.make(MD.NeLi, "QuartzWall"            , 1, 1), OP.gem.mat(MT.NetherQuartz, 4));
			RM.smash(ST.make(MD.NeLi, "QuartzWall"            , 1, 2), OP.gem.mat(MT.NetherQuartz, 4));
			RM.smash(ST.make(MD.NeLi, "QuartzWall"            , 1, 3), OP.gem.mat(MT.VoidQuartz, 4));
			RM.smash(ST.make(MD.NeLi, "QuartzWall"            , 1, 4), OP.gem.mat(MT.VoidQuartz, 4));
			RM.smash(ST.make(MD.NeLi, "QuartzWall"            , 1, 5), OP.gem.mat(MT.VoidQuartz, 4));
			RM.smash(ST.make(MD.NeLi, "QuartzSingleSlab"      , 1, 0), OP.gem.mat(MT.NetherQuartz, 2));
			RM.smash(ST.make(MD.NeLi, "QuartzSingleSlab"      , 1, 1), OP.gem.mat(MT.NetherQuartz, 2));
			RM.smash(ST.make(MD.NeLi, "QuartzSingleSlab"      , 1, 2), OP.gem.mat(MT.VoidQuartz, 2));
			RM.smash(ST.make(MD.NeLi, "QuartzSingleSlab"      , 1, 3), OP.gem.mat(MT.VoidQuartz, 2));
			RM.smash(ST.make(MD.NeLi, "QuartzSingleSlab"      , 1, 4), OP.gem.mat(MT.VoidQuartz, 2));
			RM.smash(ST.make(MD.NeLi, "QuartzDoubleSlab"      , 1, 0), OP.gem.mat(MT.NetherQuartz, 4));
			RM.smash(ST.make(MD.NeLi, "QuartzDoubleSlab"      , 1, 1), OP.gem.mat(MT.NetherQuartz, 4));
			RM.smash(ST.make(MD.NeLi, "QuartzDoubleSlab"      , 1, 2), OP.gem.mat(MT.VoidQuartz, 4));
			RM.smash(ST.make(MD.NeLi, "QuartzDoubleSlab"      , 1, 3), OP.gem.mat(MT.VoidQuartz, 4));
			RM.smash(ST.make(MD.NeLi, "QuartzDoubleSlab"      , 1, 4), OP.gem.mat(MT.VoidQuartz, 4));
			RM.smash(ST.make(MD.NeLi, "QuartzSmoothStairs"    , 1, W), OP.gem.mat(MT.NetherQuartz, 6));
			RM.smash(ST.make(MD.NeLi, "QuartzBrickStairs"     , 1, W), OP.gem.mat(MT.NetherQuartz, 6));
			RM.smash(ST.make(MD.NeLi, "QuartzVoidSmoothStairs", 1, W), OP.gem.mat(MT.VoidQuartz, 6));
			RM.smash(ST.make(MD.NeLi, "QuartzVoidBrickStairs" , 1, W), OP.gem.mat(MT.VoidQuartz, 6));
			RM.smash(ST.make(MD.NeLi, "QuartzVoidStairs"      , 1, W), OP.gem.mat(MT.VoidQuartz, 6));
			
			RM.Boxinator.addRecipe2(T, 16, 16, ST.make(Items.netherbrick, 2, 0), ST.make(Items.nether_wart, 2, 0), ST.make(MD.NeLi, "Netherbricks", 1, 2));
			RM.Boxinator.addRecipe2(T, 16, 16, ST.make(Items.netherbrick, 2, 0), IL.NeLi_Wart_Crimson     .get(2), ST.make(MD.NeLi, "Netherbricks", 1, 2));
			RM.Boxinator.addRecipe2(T, 16, 16, ST.make(Items.netherbrick, 2, 0), IL.NeLi_Wart_Warped      .get(2), ST.make(MD.NeLi, "Netherbricks", 1, 5));
			RM.Boxinator.addRecipe2(T, 16, 16, ST.make(Items.netherbrick, 2, 0), ST.make(MD.NeLi, "Roots" , 2, 1), ST.make(MD.NeLi, "Netherbricks", 1, 5));
			RM.Boxinator.addRecipe2(T, 16, 16, ST.make(Items.netherbrick, 2, 0), IL.NeLi_Wart_Soggy       .get(2), ST.make(MD.NeLi, "Netherbricks", 1, 8));
			RM.Boxinator.addRecipe2(T, 16, 16, ST.make(Items.netherbrick, 2, 0), ST.make(MD.NeLi, "Plants", 2, 1), ST.make(MD.NeLi, "Netherbricks", 1, 8));
			
			RM.pack(rockGt.mat(MT.STONES.Basalt    , 4), IL.NeLi_Basalt.get(1));
			RM.pack(rockGt.mat(MT.STONES.Blackstone, 4), IL.NeLi_Blackstone.get(1));
			CR.shaped(IL.NeLi_Basalt    .get(1), DEF, "XX", "XX", 'X', rockGt.dat(MT.STONES.Basalt));
			CR.shaped(IL.NeLi_Blackstone.get(1), DEF, "XX", "XX", 'X', rockGt.dat(MT.STONES.Blackstone));
		}
		
		
		if (MD.NePl.mLoaded) {
			CR.delate(MD.NePl, "SoulTorch", "NetheriteIngot", "ItemNetheriteSword", "NetheritePickaxe", "ItemNetheriteShovel", "ItemNetheriteAxe", "ItemNetheriteHoe", "NetheriteHelm", "NetheriteChest", "NetheriteLegg", "NetheriteBoots");
			
			if (ST.invalid(ST.make(MD.EtFu, "netherite_sword", 1, 0))) {
				RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_sword     , 1, 0), MT.Netherite.liquid(2*U4, T), NF, ST.make(MD.NePl, "ItemNetheriteSword" , 1, 0));
				RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_pickaxe   , 1, 0), MT.Netherite.liquid(3*U4, T), NF, ST.make(MD.NePl, "NetheritePickaxe"   , 1, 0));
				RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_shovel    , 1, 0), MT.Netherite.liquid(1*U4, T), NF, ST.make(MD.NePl, "ItemNetheriteShovel", 1, 0));
				RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_axe       , 1, 0), MT.Netherite.liquid(3*U4, T), NF, ST.make(MD.NePl, "ItemNetheriteAxe"   , 1, 0));
				RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_hoe       , 1, 0), MT.Netherite.liquid(2*U4, T), NF, ST.make(MD.NePl, "ItemNetheriteHoe"   , 1, 0));
				RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_helmet    , 1, 0), MT.Netherite.liquid(5*U4, T), NF, ST.make(MD.NePl, "NetheriteHelm"      , 1, 0));
				RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_chestplate, 1, 0), MT.Netherite.liquid(8*U4, T), NF, ST.make(MD.NePl, "NetheriteChest"     , 1, 0));
				RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_leggings  , 1, 0), MT.Netherite.liquid(7*U4, T), NF, ST.make(MD.NePl, "NetheriteLegg"      , 1, 0));
				RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_boots     , 1, 0), MT.Netherite.liquid(4*U4, T), NF, ST.make(MD.NePl, "NetheriteBoots"     , 1, 0));
			}
			
			RM.biomass(ST.make(MD.NePl, "WarpedFungus" , 8, W));
			RM.biomass(ST.make(MD.NePl, "CrimsonFungus", 8, W));
			RM.biomass(ST.make(MD.NePl, "CrimsonRoots" , 8, W));
			RM.biomass(ST.make(MD.NePl, "WarpedRoots"  , 8, W));
			RM.biomass(ST.make(MD.NePl, "TwistingVines", 8, W));
			
			RM.smash(IL.NePl_Quartz_Bricks.get(1), OP.gem.mat(MT.NetherQuartz, 4), 4);
			
			if (MD.NeLi.mLoaded) {
				BlocksGT.blockToDrop.put(IL.NePl_Blackstone         , IL.NeLi_Blackstone         .get(1));
				BlocksGT.blockToDrop.put(IL.NePl_Blackstone_Polished, IL.NeLi_Blackstone_Polished.get(1));
				BlocksGT.blockToDrop.put(IL.NePl_Blackstone_Chiseled, IL.NeLi_Blackstone_Chiseled.get(1));
				BlocksGT.blockToDrop.put(IL.NePl_Blackstone_Bricks  , IL.NeLi_Blackstone_Bricks  .get(1));
				BlocksGT.blockToDrop.put(IL.NePl_Blackstone_Cracked , IL.NeLi_Blackstone_Cracked .get(1));
				BlocksGT.blockToDrop.put(IL.NePl_Basalt             , IL.NeLi_Basalt             .get(1));
				BlocksGT.blockToDrop.put(IL.NePl_Basalt_Polished    , IL.NeLi_Basalt_Polished    .get(1));
				BlocksGT.blockToDrop.put(IL.NePl_Ancient_Debris     , IL.Ancient_Debris          .get(1));
				
				BlocksGT.blockToSilk.put(IL.NePl_Blackstone         , IL.NeLi_Blackstone         .get(1));
				BlocksGT.blockToSilk.put(IL.NePl_Blackstone_Polished, IL.NeLi_Blackstone_Polished.get(1));
				BlocksGT.blockToSilk.put(IL.NePl_Blackstone_Chiseled, IL.NeLi_Blackstone_Chiseled.get(1));
				BlocksGT.blockToSilk.put(IL.NePl_Blackstone_Bricks  , IL.NeLi_Blackstone_Bricks  .get(1));
				BlocksGT.blockToSilk.put(IL.NePl_Blackstone_Cracked , IL.NeLi_Blackstone_Cracked .get(1));
				BlocksGT.blockToSilk.put(IL.NePl_Basalt             , IL.NeLi_Basalt             .get(1));
				BlocksGT.blockToSilk.put(IL.NePl_Basalt_Polished    , IL.NeLi_Basalt_Polished    .get(1));
				BlocksGT.blockToSilk.put(IL.NePl_Ancient_Debris     , IL.Ancient_Debris          .get(1));
			} else {
				CR.shapeless(IL.NePl_Torch.get(4), DEF_NCC, new Object[] {OP.dust.dat(MT.SoulSand), OD.blockTorch, OD.blockTorch, OD.blockTorch, OD.blockTorch});
				CR.shapeless(IL.NePl_Torch.get(4), DEF_NCC, new Object[] {OD.flowerWither         , OD.blockTorch, OD.blockTorch, OD.blockTorch, OD.blockTorch});
				CR.shapeless(IL.NePl_Torch.get(3), DEF_NCC, new Object[] {OP.dust.dat(MT.SoulSand), OD.blockTorch, OD.blockTorch, OD.blockTorch});
				CR.shapeless(IL.NePl_Torch.get(3), DEF_NCC, new Object[] {OD.flowerWither         , OD.blockTorch, OD.blockTorch, OD.blockTorch});
				CR.shapeless(IL.NePl_Torch.get(2), DEF_NCC, new Object[] {OP.dust.dat(MT.SoulSand), OD.blockTorch, OD.blockTorch});
				CR.shapeless(IL.NePl_Torch.get(2), DEF_NCC, new Object[] {OD.flowerWither         , OD.blockTorch, OD.blockTorch});
				CR.shapeless(IL.NePl_Torch.get(1), DEF_NCC, new Object[] {OP.dust.dat(MT.SoulSand), OD.blockTorch});
				CR.shapeless(IL.NePl_Torch.get(1), DEF_NCC, new Object[] {OD.flowerWither         , OD.blockTorch});
				
				RM.Boxinator.addRecipe2(T, 16, 16, ST.make(Items.netherbrick, 2, 0), ST.make(Items.nether_wart, 2, 0), ST.make(MD.NePl, "RedBricks", 1, 0));
				
				RM.pack(rockGt.mat(MT.STONES.Basalt    , 4), IL.NePl_Basalt.get(1));
				RM.pack(rockGt.mat(MT.STONES.Blackstone, 4), IL.NePl_Blackstone.get(1));
				CR.shaped(IL.NePl_Basalt    .get(1), DEF, "XX", "XX", 'X', rockGt.dat(MT.STONES.Basalt));
				CR.shaped(IL.NePl_Blackstone.get(1), DEF, "XX", "XX", 'X', rockGt.dat(MT.STONES.Blackstone));
			}
		}
		
		
		if (MD.PFAA.mLoaded) {
			RM.generify(ST.make(MD.PFAA, "earthyClump", 1, 45), ST.make(Items.clay_ball, 1, 0));
			RM.generify(ST.make(MD.PFAA, "earthyClump", 1, 47), ST.make(Items.clay_ball, 1, 0));
			RM.generify(ST.make(MD.PFAA, "earthyClump", 1, 48), ST.make(Items.clay_ball, 1, 0));
			RM.generify(ST.make(MD.PFAA, "earthyClump", 1, 49), ST.make(Items.clay_ball, 1, 0));
			RM.add_smelting(ST.make(MD.PFAA, "earthyClump", 1, 45), ST.make(Items.brick, 1, 0), F, F, T);
			RM.add_smelting(ST.make(MD.PFAA, "earthyClump", 1, 47), ST.make(Items.brick, 1, 0), F, F, T);
			RM.add_smelting(ST.make(MD.PFAA, "earthyClump", 1, 48), ST.make(Items.brick, 1, 0), F, F, T);
			RM.add_smelting(ST.make(MD.PFAA, "earthyClump", 1, 49), ST.make(Items.brick, 1, 0), F, F, T);
			CR.shaped    (OP.plate.mat(MT.ClayBrown         , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', ST.make(MD.PFAA, "earthyClump", 4, 45));
			CR.shaped    (OP.plate.mat(MT.Bentonite         , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', ST.make(MD.PFAA, "earthyClump", 4, 47));
			CR.shaped    (OP.plate.mat(MT.Palygorskite      , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', ST.make(MD.PFAA, "earthyClump", 4, 48));
			CR.shaped    (OP.plate.mat(MT.Kaolinite         , 1), CR.DEF_NCC, "R", "C", 'R', OreDictToolNames.rollingpin, 'C', ST.make(MD.PFAA, "earthyClump", 4, 49));
			CR.shapeless (ST.make(MD.PFAA, "earthyClump", 4, 45), CR.DEF_NCC, new Object[] {ST.make(MD.PFAA, "weakClay"   , 1,  0)});
			CR.shapeless (ST.make(MD.PFAA, "earthyClump", 4, 47), CR.DEF_NCC, new Object[] {ST.make(MD.PFAA, "weakOreClay", 1,  1)});
			CR.shapeless (ST.make(MD.PFAA, "earthyClump", 4, 48), CR.DEF_NCC, new Object[] {ST.make(MD.PFAA, "weakOreClay", 1,  2)});
			CR.shapeless (ST.make(MD.PFAA, "earthyClump", 4, 49), CR.DEF_NCC, new Object[] {ST.make(MD.PFAA, "weakOreClay", 1,  3)});
			RM.packunpack(ST.make(MD.PFAA, "earthyClump", 4, 45), ST.make(MD.PFAA, "weakClay"   , 1,  0));
			RM.packunpack(ST.make(MD.PFAA, "earthyClump", 4, 47), ST.make(MD.PFAA, "weakOreClay", 1,  1));
			RM.packunpack(ST.make(MD.PFAA, "earthyClump", 4, 48), ST.make(MD.PFAA, "weakOreClay", 1,  2));
			RM.packunpack(ST.make(MD.PFAA, "earthyClump", 4, 49), ST.make(MD.PFAA, "weakOreClay", 1,  3));
			RM.RollingMill.addRecipe1(T, 16, 32, ST.make(MD.PFAA, "earthyClump", 1, 45), OP.plate.mat(MT.ClayBrown   , 1));
			RM.RollingMill.addRecipe1(T, 16, 32, ST.make(MD.PFAA, "earthyClump", 1, 47), OP.plate.mat(MT.Bentonite   , 1));
			RM.RollingMill.addRecipe1(T, 16, 32, ST.make(MD.PFAA, "earthyClump", 1, 48), OP.plate.mat(MT.Palygorskite, 1));
			RM.RollingMill.addRecipe1(T, 16, 32, ST.make(MD.PFAA, "earthyClump", 1, 49), OP.plate.mat(MT.Kaolinite   , 1));
			RM.Compressor .addRecipe1(T, 16, 32, ST.make(MD.PFAA, "earthyClump", 4, 45), ST.make(MD.PFAA, "weakClay"   , 1,  0));
			RM.Compressor .addRecipe1(T, 16, 32, ST.make(MD.PFAA, "earthyClump", 4, 47), ST.make(MD.PFAA, "weakOreClay", 1,  1));
			RM.Compressor .addRecipe1(T, 16, 32, ST.make(MD.PFAA, "earthyClump", 4, 48), ST.make(MD.PFAA, "weakOreClay", 1,  2));
			RM.Compressor .addRecipe1(T, 16, 32, ST.make(MD.PFAA, "earthyClump", 4, 49), ST.make(MD.PFAA, "weakOreClay", 1,  3));
			RM.Mortar     .addRecipe1(T, 16, 16, ST.make(MD.PFAA, "earthyClump", 1, 45), OM.dust(MT.ClayBrown));
			RM.Mortar     .addRecipe1(T, 16, 16, ST.make(MD.PFAA, "earthyClump", 1, 47), OM.dust(MT.Bentonite));
			RM.Mortar     .addRecipe1(T, 16, 16, ST.make(MD.PFAA, "earthyClump", 1, 48), OM.dust(MT.Palygorskite));
			RM.Mortar     .addRecipe1(T, 16, 16, ST.make(MD.PFAA, "earthyClump", 1, 49), OM.dust(MT.Kaolinite));
			RM.Mortar     .addRecipe1(T, 16, 64, ST.make(MD.PFAA, "weakClay"   , 1,  0), OM.dust(MT.ClayBrown, U*4));
			RM.Mortar     .addRecipe1(T, 16, 64, ST.make(MD.PFAA, "weakOreClay", 1,  1), OM.dust(MT.Bentonite, U*4));
			RM.Mortar     .addRecipe1(T, 16, 64, ST.make(MD.PFAA, "weakOreClay", 1,  2), OM.dust(MT.Palygorskite, U*4));
			RM.Mortar     .addRecipe1(T, 16, 64, ST.make(MD.PFAA, "weakOreClay", 1,  3), OM.dust(MT.Kaolinite, U*4));
			
			
			
			
			RM.Sifting          .addRecipe1(T, 16, 128, new long[] {10000, 10, 40, 150, 200, 400, 500} , IL.PFAA_Sands.getWithMeta(1, 1), crushedCentrifuged.mat(MT.OREMATS.Cassiterite, 2), gemExquisite.mat(MT.Zircon, 2), gemFlawless.mat(MT.Zircon, 2), gem.mat(MT.Zircon, 2), gemFlawed.mat(MT.Zircon, 2), gemChipped.mat(MT.Zircon, 2), dust.mat(MT.Zircon, 2));
			RM.Sifting          .addRecipe1(T, 16, 128, new long[] {10000, 10, 40, 150, 200, 400, 500} , IL.PFAA_Sands.getWithMeta(1, 5), dust.mat(MT.VolcanicAsh, 1), gemExquisite.mat(MT.Olivine, 1), gemFlawless.mat(MT.Olivine, 1), gem.mat(MT.Olivine, 1), gemFlawed.mat(MT.Olivine, 1), gemChipped.mat(MT.Olivine, 1), dust.mat(MT.Olivine, 1));
			RM.Sifting          .addRecipe1(T, 16, 128, new long[] {3000, 3000, 3000, 3000, 3000, 3000}, IL.PFAA_Sands.getWithMeta(1, 2), crushedPurifiedTiny.mat(MT.Almandine, 8), crushedPurifiedTiny.mat(MT.Andradite, 8), crushedPurifiedTiny.mat(MT.Grossular, 8), crushedPurifiedTiny.mat(MT.Pyrope, 8), crushedPurifiedTiny.mat(MT.Spessartine, 8), crushedPurifiedTiny.mat(MT.Uvarovite, 8));
			RM.Sifting          .addRecipe1(T, 16, 128, new long[] {8000, 8000, 8000, 3000, 3000, 3000}, IL.PFAA_Sands.getWithMeta(1, 4), dust.mat(MT.MilkyQuartz, 1), dust.mat(MT.CertusQuartz, 1), dust.mat(MT.BlackQuartz, 1), gem.mat(MT.MilkyQuartz, 1), gem.mat(MT.CertusQuartz, 1), gem.mat(MT.BlackQuartz, 1));
			RM.Sifting          .addRecipe1(T, 16, 128                                                 , IL.PFAA_Sands.getWithMeta(1, 6), crushedPurified.mat(MT.OREMATS.Glauconite, 2));
		}
		
		
		if (MD.RH.mLoaded) {
			RM.Sifting          .addRecipe1(T, 16, 200, new long[] {5000, 5000}, IL.RH_Sand_Olivine.get(1), OP.gem.mat(MT.Olivine, 1), OP.dust.mat(MT.Olivine, 1));
			RM.Sifting          .addRecipe1(T, 16, 200, new long[] {9000, 1000}, IL.RH_Sand_Gypsum .get(1), OP.dust.mat(MT.Gypsum, 1), OP.dust.mat(MT.S, 1));
			
			RM.Sifting          .addRecipe1(T, 16, 200, new long[] {9900, 500, 500}     , IL.RH_Sand_Magnetite.get(1), dust.mat(MT.OREMATS.GraniticMineralSand, 1), rockGt.mat(MT.STONES.GraniteBlack, 1), nugget.mat(MT.Au, 1));
			RM.MagneticSeparator.addRecipe1(T, 16, 144, new long[] {9900, 500, 500, 500}, IL.RH_Sand_Magnetite.get(1), dust.mat(MT.OREMATS.GraniticMineralSand, 1), rockGt.mat(MT.STONES.GraniteBlack, 1), nugget.mat(MT.Au, 1), dustTiny.mat(MT.Au, 2));
			RM.Centrifuge       .addRecipe1(T, 16, 256, new long[] {9000, 1000}         , IL.RH_Sand_Magnetite.get(1), dust.mat(MT.OREMATS.GraniticMineralSand, 1), dust.mat(MT.V2O5, 1));
			
			if (MD.TROPIC.mLoaded) {
				RM.Sifting      .addRecipe1(T, 16, 200, new long[] {9900, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 200, 100, 50}, IL.RH_Sand_Coral.get(1), IL.TROPIC_Sand_Pure.get(1), ST.make(MD.TROPIC, "shell", 1, 0), ST.make(MD.TROPIC, "shell", 1, 1), ST.make(MD.TROPIC, "shell", 1, 2), ST.make(MD.TROPIC, "shell", 1, 3), ST.make(MD.TROPIC, "shell", 1, 4), ST.make(MD.TROPIC, "shell", 1, 5), ST.make(MD.TROPIC, "pearl", 1, 0), ST.make(MD.TROPIC, "pearl", 1, 1), OP.gem.mat(MT.Azurite, 1), OP.gem.mat(MT.Eudialyte, 1), OP.gem.mat(MT.Zr, 1));
			} else {
				RM.Sifting      .addRecipe1(T, 16, 200, new long[] {9900, 200, 100, 50}, IL.RH_Sand_Coral.get(1), ST.make(Blocks.sand, 1, 0), OP.gem.mat(MT.Azurite, 1), OP.gem.mat(MT.Eudialyte, 1), OP.gem.mat(MT.Zr, 1));
			}
		}
		
		
		// Some of these aren't Temporary, but I like having all Generifier Recipes for Fluids in on place.
		RM.generify   (FL.make("molten.meteoriciron"   , 1), FL.make("molten.iron", 1));
		RM.generify   (FL.make("molten.wroughtiron"    , 1), FL.make("molten.iron", 1));
		RM.generify   (FL.make("molten.osmiumelemental", 1), FL.make("molten.osmium", 1));
		RM.generify   (FL.make("deuterium"             , 5), FL.make("rc deuterium", 1));
		RM.generify   (FL.make("tritium"               , 5), FL.make("rc tritium", 1));
		RM.generify   (FL.make("carbondioxide"         , 5), FL.make("rc co2", 1));
		RM.generify   (FL.make("chlorine"              , 5), FL.make("rc chlorine", 1));
		RM.generify   (FL.make("heavywater"            , 1), FL.Heavy_Reiker.make(1));
		RM.genericycle(FL.XP                      .make( 3), FL.Mob.make(10));
		RM.genericycle(FL.Redstone_TE             .make(25), FL.Redstone.make(36));
		RM.genericycle(FL.Lubricant               .make( 1), FL.LubRoCant.make(1));
		RM.generify   (FL.Oil_Canola              .make( 2), FL.lube(1));
		RM.generify   (FL.make("ethanol"               , 1), FL.BioEthanol.make(1));
		RM.genericycle(FL.BioEthanol              .make( 1), FL.Reikanol.make(1));
		RM.genericycle(FL.Oxygen                  .make( 1), FL.Reikygen.make(1));
		RM.genericycle(FL.Liquid_Oxygen           .make( 1), FL.Liquid_Reikygen.make(1));
		RM.genericycle(FL.make("molten.latex"          , 1), FL.Latex.make(1));
		RM.generify   (FL.Slime_Pink              .make( 1), FL.Slime_Green.make(1));
		RM.generify   (FL.RoyalJelly              .make( 1), FL.Honey.make(10));
		RM.genericycle(FL.Honey                   .make( 1), FL.HoneyGrC.make(1), FL.HoneyBoP.make(1));
		RM.genericycle(FL.Milk                    .make( 1), FL.MilkGrC.make(1));
		RM.genericycle(FL.make("for.honeydew"          , 1), FL.Honeydew.make(1));
		RM.genericycle(FL.Resin_Spruce            .make( 1), FL.Resin.make(1));
		RM.genericycle(FL.make("sulfuricacid"          , 1), FL.make("acid", 1));
		RM.genericycle(FL.Oil_Plant               .make( 2), FL.Oil_Seed.make(1));
		RM.genericycle(FL.Biomass                 .make( 1), FL.BiomassIC2.make(1));
		RM.genericycle(FL.Methane                 .make( 1), FL.make("ic2biogas", 4));
		RM.generify   (FL.make("gas_natural_gas"       , 1), FL.Methane.make(1));
		RM.generify   (FL.make("naturalgas"            , 1), FL.Methane.make(1));
		RM.generify   (FL.make("gas.natural"           , 1), FL.Methane.make(1));
		RM.generify   (FL.Liquid_Methane          .make( 1), FL.Methane.make(643));
		RM.genericycle(FL.make("kerosine"              , 1), FL.make("kerosene", 1));
		RM.genericycle(FL.make("petrol"                , 1), FL.make("gasoline", 1));
		RM.genericycle(FL.make("fuel"                  , 1), FL.make("fueloil", 1));
		RM.generify   (FL.Steam_IC2_Superheated   .make( 1), FL.Steam.make(3));
		RM.generify   (FL.Steam_IC2               .make( 1), FL.Steam.make(1));
		RM.generify   (FL.DistW                   .make( 1), FL.Water.make(1));
		RM.generify   (FL.SpDew                   .make( 1), FL.Water.make(1));
		RM.generify   (FL.Oil_Lin                 .make( 1), FL.Oil_Seed.make(1));
		RM.generify   (FL.Oil_Hemp                .make( 1), FL.Oil_Seed.make(1));
		RM.generify   (FL.Oil_Olive               .make( 1), FL.Oil_Seed.make(1));
		RM.generify   (FL.Oil_Sunflower           .make( 1), FL.Oil_Seed.make(1));
		RM.generify   (FL.Oil_Nut                 .make( 1), FL.Oil_Seed.make(1));
		
		for (String tFluid : FluidsGT.JUICE) if (FL.exists(tFluid)) RM.generify(FL.make(tFluid, 1), FL.Juice.make(1));
	}
}
