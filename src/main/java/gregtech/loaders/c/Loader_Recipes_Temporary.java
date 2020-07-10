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
import static gregapi.data.OP.*;
import static gregapi.util.CR.*;

import gregapi.data.ANY;
import gregapi.data.CS.FluidsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

/**
 * @author Gregorius Techneticies
 * 
 * Here is basically everything that I want to change to something better later.
 */
public class Loader_Recipes_Temporary implements Runnable {
	@Override public void run() {
		// TODO: Graphite Electrodes are made from petroleum coke after it is mixed with coal tar pitch. They are then extruded and shaped, baked to carbonize the binder (pitch) and finally graphitized by heating it to temperatures approaching 3273K.
		RM.Extruder.addRecipe2(T, 512, 512, OP.dust.mat(MT.Graphite, 1), IL.Shape_Extruder_Rod.get(0), OP.stick.mat(MT.Graphite, 1));
		
		
		// TODO: Better Coolant Item than Lapis.
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lapis   , 1*U), FL.DistW.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lapis   , 2*U), FL.Water.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lazurite, 1*U), FL.DistW.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Lazurite, 2*U), FL.Water.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Sodalite, 1*U), FL.DistW.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		RM.Injector.addRecipe1(T, 16, 16, OM.dust(MT.Sodalite, 2*U), FL.Water.make(1000), FL.Coolant_IC2.make(1000), ZL_IS);
		
		RM.Injector.addRecipe1(T, 64, 32, OM.dust(MT.Th, 1*U), FL.amount(MT.LiCl.mLiquid, 10000), FL.Thorium_Salt.make(10000), ZL_IS);
		
		
		// TODO: Just no Ender IO Compat Handler and for this small thing I wont make a new Class.
		CR.delate(MD.EIO, "itemYetaWrench");
		
		
		
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
			
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumRed"      , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumRed"      , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumGreen"    , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumGreen"    , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumBlue"     , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumBlue"     , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumBlack"    , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumBlack"    , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumWhite"    , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumWhite"    , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumDarkGray" , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumDarkGray" , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumGray"     , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumGray"     , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumLightGray", 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumLightGray", 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumOrange"   , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumOrange"   , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumYellow"   , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumYellow"   , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumLime"     , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumLime"     , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumTurquoise", 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumTurquoise", 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumCyan"     , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumCyan"     , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumSkyBlue"  , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumSkyBlue"  , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumPurple"   , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumPurple"   , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumMagenta"  , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumMagenta"  , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumPink"     , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumPink"     , 1, 0));
			RM.sawing(32, 288, F,  96, ST.make(MD.HEX, "blockEnergizedHexoriumRainbow"  , 1, W), ST.make(MD.HEX, "blockMiniEnergizedHexoriumRainbow"  , 1, 0));
			
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
				RM.add_smelting(aEvent.mStack, ST.make(MD.HEX, "itemBlackHexoriumWafer", 1, 0));
			}});
			addListener(OP.plateGem.dat(MT.HexoriumWhite), new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
				RM.add_smelting(aEvent.mStack, ST.make(MD.HEX, "itemWhiteHexoriumWafer", 1, 0));
			}});
			}};
		}
		
		
		if (MD.HBM.mLoaded) {
			CR.delate(MD.HBM, "item.apple_lead", "item.apple_schrabidium", "item.apple_euphemium");
			
			CR.shapeless(IL.HBM_Mercury_Drop.get(8), CR.DEF_NAC_NCC, new Object[] {IL.Bottle_Mercury});
			RM.generify(IL.HBM_Mercury_Bottle.get(1), IL.Bottle_Mercury.get(1));
			RM.generify(IL.Bottle_Mercury.get(1), IL.HBM_Mercury_Bottle.get(1));
			
			
			RM.Bath.addRecipe1(T, 0, 128, ST.make(MD.HBM, "item.apple_lead", 1, 0), MT.Pb.liquid(64*U9, T), NF, ST.make(MD.HBM, "item.apple_lead", 1, 1));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(MD.HBM, "item.apple_lead", 1, 1), MT.Pb.liquid(64*U , T), NF, ST.make(MD.HBM, "item.apple_lead", 1, 2));
			
			RM.Bath.addRecipe1(T, 0, 128, ST.make(MD.HBM, "item.apple_schrabidium", 1, 0), MT.UNUSED.Schrabidium.liquid(64*U9, T), NF, ST.make(MD.HBM, "item.apple_schrabidium", 1, 1));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(MD.HBM, "item.apple_schrabidium", 1, 1), MT.UNUSED.Schrabidium.liquid(64*U , T), NF, ST.make(MD.HBM, "item.apple_schrabidium", 1, 2));
			
			new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
			addListener("cropApple", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
				if (OM.is("cropAppleWhite", aEvent.mStack) || OM.is("cropCrabapple", aEvent.mStack)) return;
				RM.Bath.addRecipe1(T, 0, 128, aEvent.mStack, MT.Pb.liquid(8*U9, T), NF, ST.make(MD.HBM, "item.apple_lead", 1, 0));
				RM.Bath.addRecipe1(T, 0, 128, aEvent.mStack, MT.UNUSED.Schrabidium.liquid(8*U9, T), NF, ST.make(MD.HBM, "item.apple_schrabidium", 1, 0));
				RM.Bath.addRecipe1(T, 0, 128, aEvent.mStack, MT.UNUSED.Euphemium.liquid(8*U9, T), NF, ST.make(MD.HBM, "item.apple_euphemium", 1, 0));
			}});
			}};
		}
		
		
		if (MD.NePl.mLoaded) {
			CR.delate(MD.NePl, "SoulTorch", "NetheriteIngot", "ItemNetheriteSword", "NetheritePickaxe", "ItemNetheriteShovel", "ItemNetheriteAxe", "ItemNetheriteHoe", "NetheriteHelm", "NetheriteChest", "NetheriteLegg", "NetheriteBoots");
			
			CR.shaped(IL.NePl_Torch.get(4), DEF_NAC, "X", "S", 'X', OD.soulsand, 'S', OP.stick.dat(ANY.Wood));
			
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_sword     , 1, 0), MT.Netherite.liquid(2*U4, T), NF, ST.make(MD.NePl, "ItemNetheriteSword" , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_pickaxe   , 1, 0), MT.Netherite.liquid(3*U4, T), NF, ST.make(MD.NePl, "NetheritePickaxe"   , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_shovel    , 1, 0), MT.Netherite.liquid(1*U4, T), NF, ST.make(MD.NePl, "ItemNetheriteShovel", 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_axe       , 1, 0), MT.Netherite.liquid(3*U4, T), NF, ST.make(MD.NePl, "ItemNetheriteAxe"   , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_hoe       , 1, 0), MT.Netherite.liquid(2*U4, T), NF, ST.make(MD.NePl, "ItemNetheriteHoe"   , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_helmet    , 1, 0), MT.Netherite.liquid(5*U4, T), NF, ST.make(MD.NePl, "NetheriteHelm"      , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_chestplate, 1, 0), MT.Netherite.liquid(8*U4, T), NF, ST.make(MD.NePl, "NetheriteChest"     , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_leggings  , 1, 0), MT.Netherite.liquid(7*U4, T), NF, ST.make(MD.NePl, "NetheriteLegg"      , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, ST.make(Items.diamond_boots     , 1, 0), MT.Netherite.liquid(4*U4, T), NF, ST.make(MD.NePl, "NetheriteBoots"     , 1, 0));
			
			RM.biomass(ST.make(MD.NePl, "WarpedFungus" , 8, 0));
			RM.biomass(ST.make(MD.NePl, "CrimsonFungus", 8, 0));
			RM.biomass(ST.make(MD.NePl, "CrimsonRoots" , 8, 0));
			RM.biomass(ST.make(MD.NePl, "WarpedRoots"  , 8, 0));
			RM.biomass(ST.make(MD.NePl, "TwistingVines", 8, 0));
			
			RM.Boxinator.addRecipe2(T, 16, 16, ST.make(Items.netherbrick, 2, 0), ST.make(Items.nether_wart, 2, 0), ST.make(MD.NePl, "RedBricks", 1, 0));
		}
		
		
		if (MD.RH.mLoaded) {
			RM.Sifting          .addRecipe1(T, 16, 200, new long[] {5000, 5000}, IL.RH_Sand_Olivine.get(1), OP.gem.mat(MT.Olivine, 1), OP.dust.mat(MT.Olivine, 1));
			RM.Sifting          .addRecipe1(T, 16, 200, new long[] {9000, 1000}, IL.RH_Sand_Gypsum .get(1), OP.dust.mat(MT.OREMATS.Gypsum, 1), OP.dust.mat(MT.S, 1));
			
			RM.Sifting          .addRecipe1(T, 16, 200, new long[] {9900, 500, 500}     , IL.RH_Sand_Magnetite.get(1), dust.mat(MT.OREMATS.Magnetite, 1), rockGt.mat(MT.Basalt, 1), nugget.mat(MT.Au, 1));
			RM.MagneticSeparator.addRecipe1(T, 16, 144, new long[] {9900, 500, 500, 500}, IL.RH_Sand_Magnetite.get(1), dust.mat(MT.OREMATS.Magnetite, 1), rockGt.mat(MT.Basalt, 1), nugget.mat(MT.Au, 1), dustTiny.mat(MT.Au, 2));
			RM.Centrifuge       .addRecipe1(T, 16, 256, new long[] {9000, 1000}         , IL.RH_Sand_Magnetite.get(1), dust.mat(MT.OREMATS.Magnetite, 1), dust.mat(MT.V2O5, 1));
			
			if (MD.TROPIC.mLoaded) {
				RM.Sifting      .addRecipe1(T, 16, 200, new long[] {9900, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 200, 100, 50}, IL.RH_Sand_Coral.get(1), IL.TROPIC_Sand_Pure.get(1), ST.make(MD.TROPIC, "shell", 1, 0), ST.make(MD.TROPIC, "shell", 1, 1), ST.make(MD.TROPIC, "shell", 1, 2), ST.make(MD.TROPIC, "shell", 1, 3), ST.make(MD.TROPIC, "shell", 1, 4), ST.make(MD.TROPIC, "shell", 1, 5), ST.make(MD.TROPIC, "pearl", 1, 0), ST.make(MD.TROPIC, "pearl", 1, 1), OP.gem.mat(MT.Azurite, 1), OP.gem.mat(MT.Eudialyte, 1), OP.gem.mat(MT.Zr, 1));
			} else {
				RM.Sifting      .addRecipe1(T, 16, 200, new long[] {9900, 200, 100, 50}, IL.RH_Sand_Coral.get(1), ST.make(Blocks.sand, 1, 0), OP.gem.mat(MT.Azurite, 1), OP.gem.mat(MT.Eudialyte, 1), OP.gem.mat(MT.Zr, 1));
			}
		}
		
		
		// Some of these aren't Temporary, but I like having all Generifier Recipes for Fluids in on place.
		RM.generify(FL.make("molten.meteoriciron"         , 1), FL.make("molten.iron", 1));
		RM.generify(FL.make("molten.wroughtiron"          , 1), FL.make("molten.iron", 1));
		RM.generify(FL.make("molten.osmiumelemental"      , 1), FL.make("molten.osmium", 1));
		RM.generify(FL.make("deuterium"                   , 5), FL.make("rc deuterium", 1));
		RM.generify(FL.make("tritium"                     , 5), FL.make("rc tritium", 1));
		RM.generify(FL.make("carbondioxide"               , 5), FL.make("rc co2", 1));
		RM.generify(FL.make("chlorine"                    , 5), FL.make("rc chlorine", 1));
		RM.generify(FL.make("heavywater"                  , 1), FL.Heavy_Reiker.make(1));
		RM.generify(FL.Redstone_TE                   .make(25), FL.Redstone.make(36));
		RM.generify(FL.Redstone                      .make(36), FL.Redstone_TE.make(25));
		RM.generify(FL.Lubricant                     .make( 1), FL.LubRoCant.make(1));
		RM.generify(FL.LubRoCant                     .make( 1), FL.Lubricant.make(1));
		RM.generify(FL.make("ethanol"                     , 1), FL.Reikanol.make(1));
		RM.generify(FL.BioEthanol                    .make( 1), FL.Reikanol.make(1));
		RM.generify(FL.Reikanol                      .make( 1), FL.BioEthanol.make(1));
		RM.generify(FL.Oxygen                        .make( 1), FL.Reikygen.make(1));
		RM.generify(FL.Reikygen                      .make( 1), FL.Oxygen.make(1));
		RM.generify(FL.Liquid_Oxygen                 .make( 1), FL.Liquid_Reikygen.make(1));
		RM.generify(FL.Liquid_Reikygen               .make( 1), FL.Liquid_Oxygen.make(1));
		RM.generify(FL.Oil_Canola                    .make( 2), FL.lube(1));
		RM.generify(FL.make("molten.latex"                , 1), FL.Latex.make(1));
		RM.generify(FL.Latex                         .make( 1), FL.make("molten.latex", 1));
		RM.generify(FL.Slime_Pink                    .make( 1), FL.Slime_Green.make(1));
		RM.generify(FL.RoyalJelly                    .make( 1), FL.Honey.make(10));
		RM.generify(FL.Honey                         .make( 1), FL.HoneyGrC.make(1));
		RM.generify(FL.HoneyGrC                      .make( 1), FL.HoneyBoP.make(1));
		RM.generify(FL.HoneyBoP                      .make( 1), FL.Honey.make(1));
		RM.generify(FL.Milk                          .make( 1), FL.MilkGrC.make(1));
		RM.generify(FL.MilkGrC                       .make( 1), FL.Milk.make(1));
		RM.generify(FL.make("for.honeydew"                , 1), FL.Honeydew.make(1));
		RM.generify(FL.make("spruceresin"                 , 1), FL.make("resin", 1));
		RM.generify(FL.make("resin"                       , 1), FL.make("spruceresin", 1));
		RM.generify(FL.make("sulfuricacid"                , 1), FL.make("acid", 1));
		RM.generify(FL.make("acid"                        , 1), FL.make("sulfuricacid", 1));
		RM.generify(FL.Oil_Plant                     .make( 2), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Seed                      .make( 1), FL.Oil_Plant.make(2));
		RM.generify(FL.make("biomass"                     , 1), FL.make("ic2biomass", 1));
		RM.generify(FL.make("ic2biomass"                  , 1), FL.make("biomass", 1));
		RM.generify(FL.Methane                       .make( 1), FL.make("ic2biogas", 4));
		RM.generify(FL.make("ic2biogas"                   , 4), FL.Methane.make(1));
		RM.generify(FL.make("gas_natural_gas"             , 1), FL.Methane.make(1));
		RM.generify(FL.make("naturalgas"                  , 1), FL.Methane.make(1));
		RM.generify(FL.make("gas.natural"                 , 1), FL.Methane.make(1));
		RM.generify(FL.Liquid_Methane                .make( 1), FL.Methane.make(643));
		RM.generify(FL.make("kerosine"                    , 1), FL.make("kerosene", 1));
		RM.generify(FL.make("kerosene"                    , 1), FL.make("kerosine", 1));
		RM.generify(FL.make("petrol"                      , 1), FL.make("gasoline", 1));
		RM.generify(FL.make("gasoline"                    , 1), FL.make("petrol", 1));
		RM.generify(FL.make("fuel"                        , 1), FL.make("fueloil", 1));
		RM.generify(FL.make("fueloil"                     , 1), FL.make("fuel", 1));
		RM.generify(FL.Steam_IC2_Superheated         .make( 1), FL.Steam.make(3));
		RM.generify(FL.Steam_IC2                     .make( 1), FL.Steam.make(1));
		RM.generify(FL.DistW                         .make( 1), FL.Water.make(1));
		RM.generify(FL.Oil_Lin                       .make( 1), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Hemp                      .make( 1), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Olive                     .make( 1), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Sunflower                 .make( 1), FL.Oil_Seed.make(1));
		RM.generify(FL.Oil_Nut                       .make( 1), FL.Oil_Seed.make(1));
		
		for (String tFluid : FluidsGT.JUICE) if (FL.exists(tFluid)) RM.generify(FL.make(tFluid, 1), FL.Juice.make(1));
	}
}
