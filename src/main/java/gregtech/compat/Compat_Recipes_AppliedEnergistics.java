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

package gregtech.compat;

import static gregapi.data.CS.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.ANY;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;

public class Compat_Recipes_AppliedEnergistics extends CompatMods {
	public Compat_Recipes_AppliedEnergistics(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing AE Recipes.");
		CR.delate(MD.AE, "item.ToolCertusQuartzWrench", "item.ToolNetherQuartzWrench");
		
		RM.ae_grinder(5, ST.make(MD.AE, "item.ItemMultiMaterial", 1, 10), OP.dustSmall.mat(MT.CertusQuartz, 2));
		RM.ae_grinder(5, ST.make(MD.AE, "item.ItemMultiMaterial", 1, 11), OP.dustSmall.mat(MT.NetherQuartz, 2));
		RM.ae_grinder(5, ST.make(MD.AE, "item.ItemMultiMaterial", 1, 12), OP.dustSmall.mat(MT.Fluix, 2));
		
		RM.add_smelting(ST.make(MD.AE, "item.ItemMultiMaterial", 1, 45), ST.make(MD.AE, "tile.BlockSkyStone", 1, 0));
		
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Olivine), OM.dust(MT.RareEarth), OM.dust(MT.Basalt), OM.dust(MT.Obsidian)), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 45));
		
		RM.Shredder     .addRecipe1(T, 64,   16, ST.make(MD.AE, "tile.BlockSkyStone", 1, W), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 45));
		RM.Shredder     .addRecipe1(T, 64,  128, ST.make(MD.AE, "tile.BlockSkyChest", 1, W), ST.make(MD.AE, "item.ItemMultiMaterial", 8, 45));
		
		RM.Press        .addRecipe2(T, 16,   64, ST.make(MD.AE, "item.ItemMultiMaterial", 0, 13), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 10)   , ST.make(MD.AE, "item.ItemMultiMaterial", 1, 16));
		RM.Press        .addRecipe2(T, 16,   64, ST.make(MD.AE, "item.ItemMultiMaterial", 0, 13), OP.plateGem   .mat(MT.CertusQuartz    , 1)        , ST.make(MD.AE, "item.ItemMultiMaterial", 1, 16));
		for (OreDictMaterial tMat : ANY.Diamond.mToThis)
		RM.Press        .addRecipe2(T, 16,   64, ST.make(MD.AE, "item.ItemMultiMaterial", 0, 14), OP.plateGem   .mat(tMat               , 1)        , ST.make(MD.AE, "item.ItemMultiMaterial", 1, 17));
		RM.Press        .addRecipe2(T, 16,   64, ST.make(MD.AE, "item.ItemMultiMaterial", 0, 15), OP.plate      .mat(MT.Au              , 1)        , ST.make(MD.AE, "item.ItemMultiMaterial", 1, 18));
		RM.Press        .addRecipe2(T, 16,   64, ST.make(MD.AE, "item.ItemMultiMaterial", 0, 19), OP.plate      .mat(MT.Si              , 1)        , ST.make(MD.AE, "item.ItemMultiMaterial", 1, 20));
		RM.Press        .addRecipe2(T, 16,   64, ST.make(MD.AE, "item.ItemMultiMaterial", 0, 19), OP.plateGem   .mat(MT.Si              , 1)        , ST.make(MD.AE, "item.ItemMultiMaterial", 1, 20));
		
		for (OreDictMaterial tMat : ANY.Iron.mToThis) if (tMat != MT.Enori) {
		RM.Press        .addRecipe2(T, 16,   64, ST.make(MD.AE, "item.ItemMultiMaterial", 0, 13), OP.blockSolid.mat(tMat, 1), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 13));
		RM.Press        .addRecipe2(T, 16,   64, ST.make(MD.AE, "item.ItemMultiMaterial", 0, 14), OP.blockSolid.mat(tMat, 1), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 14));
		RM.Press        .addRecipe2(T, 16,   64, ST.make(MD.AE, "item.ItemMultiMaterial", 0, 15), OP.blockSolid.mat(tMat, 1), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 15));
		RM.Press        .addRecipe2(T, 16,   64, ST.make(MD.AE, "item.ItemMultiMaterial", 0, 19), OP.blockSolid.mat(tMat, 1), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 19));
		}
		
		RM.Press        .addRecipeX(T, 16,   64, ST.array(ST.make(MD.AE, "item.ItemMultiMaterial", 1, 16), OM.dust(MT.Redstone), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 20)), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 23));
		RM.Press        .addRecipeX(T, 16,   64, ST.array(ST.make(MD.AE, "item.ItemMultiMaterial", 1, 17), OM.dust(MT.Redstone), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 20)), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 24));
		RM.Press        .addRecipeX(T, 16,   64, ST.array(ST.make(MD.AE, "item.ItemMultiMaterial", 1, 18), OM.dust(MT.Redstone), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 20)), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 22));
		
		RM.Autoclave    .addRecipe2(T,  0, 1500, ST.make(MD.AE, "item.ItemCrystalSeed", 1,    0), ST.tag(2), FL.Steam.make(48000), FL.DistW.make(225), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 10));
		RM.Autoclave    .addRecipe2(T,  0, 1500, ST.make(MD.AE, "item.ItemCrystalSeed", 1,  600), ST.tag(2), FL.Steam.make(48000), FL.DistW.make(225), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 11));
		RM.Autoclave    .addRecipe2(T,  0, 1500, ST.make(MD.AE, "item.ItemCrystalSeed", 1, 1200), ST.tag(2), FL.Steam.make(48000), FL.DistW.make(225), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 12));
		
		RM.Compressor   .addRecipe1(T, 16,   16, OP.gem.mat(MT.CertusQuartz                 , 4), ST.make(MD.AE, "tile.BlockQuartz", 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, ST.make(MD.AE, "item.ItemMultiMaterial", 8, 10), ST.make(MD.AE, "tile.BlockQuartz", 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, OP.gem.mat(MT.Fluix                        , 4), ST.make(MD.AE, "tile.BlockFluix", 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, ST.make(MD.AE, "item.ItemMultiMaterial", 8, 12), ST.make(MD.AE, "tile.BlockFluix", 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, ST.make(MD.AE, "item.ItemMultiMaterial", 8, 11), ST.make(Blocks.quartz_block, 1, 0));
		
		RM.Centrifuge   .addRecipe1(T, 64,   16, new long[] {2000, 2000, 2000, 2000, 2000, 2000}, ST.make(MD.AE, "item.ItemMultiMaterial", 1, 45), OP.dustSmall.mat(MT.OREMATS.BasalticMineralSand, 1), OP.dustSmall.mat(MT.Olivine, 1), OP.dustSmall.mat(MT.Obsidian, 1), OP.dustSmall.mat(MT.Basalt, 1), OP.dustSmall.mat(MT.Flint, 1), OP.dustSmall.mat(MT.RareEarth, 1));
		
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.CertusQuartz), ST.make(Blocks.sand, 1, W), ST.make(MD.AE, "item.ItemCrystalSeed", 2,    0));
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.NetherQuartz), ST.make(Blocks.sand, 1, W), ST.make(MD.AE, "item.ItemCrystalSeed", 2,  600));
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.Fluix       ), ST.make(Blocks.sand, 1, W), ST.make(MD.AE, "item.ItemCrystalSeed", 2, 1200));
		if (IL.AETHER_Sand.exists()) {
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.CertusQuartz), IL.AETHER_Sand     .get(1), ST.make(MD.AE, "item.ItemCrystalSeed", 2,    0));
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.NetherQuartz), IL.AETHER_Sand     .get(1), ST.make(MD.AE, "item.ItemCrystalSeed", 2,  600));
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.Fluix       ), IL.AETHER_Sand     .get(1), ST.make(MD.AE, "item.ItemCrystalSeed", 2, 1200));
		}
		
		RM.smash(ST.make(MD.AE, "tile.BlockQuartz"              , 1, W), OP.gem.mat(MT.CertusQuartz, 4));
		RM.smash(ST.make(MD.AE, "tile.BlockQuartzPillar"        , 1, W), OP.gem.mat(MT.CertusQuartz, 4));
		RM.smash(ST.make(MD.AE, "tile.BlockQuartzChiseled"      , 1, W), OP.gem.mat(MT.CertusQuartz, 4));
		RM.smash(ST.make(MD.AE, "tile.BlockFluix"               , 1, W), OP.gem.mat(MT.Fluix, 4));
		RM.smash(ST.make(MD.AE, "tile.QuartzStairBlock"         , 1, W), OP.gem.mat(MT.CertusQuartz, 6));
		RM.smash(ST.make(MD.AE, "tile.QuartzPillarStairBlock"   , 1, W), OP.gem.mat(MT.CertusQuartz, 6));
		RM.smash(ST.make(MD.AE, "tile.ChiseledQuartzStairBlock" , 1, W), OP.gem.mat(MT.CertusQuartz, 6));
		RM.smash(ST.make(MD.AE, "tile.FluixStairBlock"          , 1, W), OP.gem.mat(MT.Fluix, 6));
		RM.smash(ST.make(MD.AE, "tile.QuartzSlabBlock"          , 1, W), OP.gem.mat(MT.CertusQuartz, 2));
		RM.smash(ST.make(MD.AE, "tile.QuartzPillarSlabBlock"    , 1, W), OP.gem.mat(MT.CertusQuartz, 2));
		RM.smash(ST.make(MD.AE, "tile.ChiseledQuartzSlabBlock"  , 1, W), OP.gem.mat(MT.CertusQuartz, 2));
		RM.smash(ST.make(MD.AE, "tile.FluixSlabBlock"           , 1, W), OP.gem.mat(MT.Fluix, 2));
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener("gemCertusQuartz", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(10, aEvent.mStack, OP.dust.mat(MT.CertusQuartz, 1));
		}});
		addListener("gemNetherQuartz", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(10, aEvent.mStack, OP.dust.mat(MT.NetherQuartz, 1));
		}});
		addListener("gemVoidQuartz", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(10, aEvent.mStack, OP.dust.mat(MT.VoidQuartz, 1));
		}});
		addListener("gemMilkyQuartz", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(10, aEvent.mStack, OP.dust.mat(MT.MilkyQuartz, 1));
		}});
		addListener("gemFluix", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(10, aEvent.mStack, OP.dust.mat(MT.Fluix, 1));
		}});
		addListener("gemCoal", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder( 5, aEvent.mStack, OP.dust.mat(MT.Coal, 1));
		}});
		addListener("gemCharcoal", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder( 5, aEvent.mStack, OP.dust.mat(MT.Charcoal, 1));
		}});
		addListener("gemSulfur", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder( 5, aEvent.mStack, OP.dust.mat(MT.S, 1));
		}});
		addListener("gemSaltpeter", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder( 5, aEvent.mStack, OP.dust.mat(MT.KNO3, 1));
		}});
		addListener("ingotCopper", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(10, aEvent.mStack, OP.dust.mat(MT.Cu, 1));
		}});
		addListener("ingotTin", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(10, aEvent.mStack, OP.dust.mat(MT.Sn, 1));
		}});
		addListener("ingotLead", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(10, aEvent.mStack, OP.dust.mat(MT.Pb, 1));
		}});
		addListener("ingotBismuth", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(10, aEvent.mStack, OP.dust.mat(MT.Bi, 1));
		}});
		addListener("ingotZinc", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(10, aEvent.mStack, OP.dust.mat(MT.Zn, 1));
		}});
		addListener("ingotBrass", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(10, aEvent.mStack, OP.dust.mat(MT.Brass, 1));
		}});
		addListener("ingotBronze", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(20, aEvent.mStack, OP.dust.mat(MT.Bronze, 1));
		}});
		addListener("ingotGold", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(20, aEvent.mStack, OP.dust.mat(MT.Au, 1));
		}});
		addListener("ingotPlatinum", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(20, aEvent.mStack, OP.dust.mat(MT.Pt, 1));
		}});
		addListener("ingotNickel", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(20, aEvent.mStack, OP.dust.mat(MT.Ni, 1));
		}});
		addListener("ingotIron", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.ae_grinder(20, aEvent.mStack, OP.dust.mat(MT.Fe, 1));
		}});
		addListener(DYE_OREDICTS_LENS[DYE_INDEX_White], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			for (OreDictMaterial tMat : ANY.Fe.mToThis) if (tMat != MT.Enori)
			RM.LaserEngraver.addRecipe2(T,512,512, OP.blockSolid.mat(tMat, 1), ST.amount(0, aEvent.mStack), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 13));
		}});
		addListener(DYE_OREDICTS_LENS[DYE_INDEX_Cyan], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			for (OreDictMaterial tMat : ANY.Fe.mToThis) if (tMat != MT.Enori)
			RM.LaserEngraver.addRecipe2(T,512,512, OP.blockSolid.mat(tMat, 1), ST.amount(0, aEvent.mStack), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 14));
		}});
		addListener(DYE_OREDICTS_LENS[DYE_INDEX_Yellow], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			for (OreDictMaterial tMat : ANY.Fe.mToThis) if (tMat != MT.Enori)
			RM.LaserEngraver.addRecipe2(T,512,512, OP.blockSolid.mat(tMat, 1), ST.amount(0, aEvent.mStack), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 15));
		}});
		addListener(DYE_OREDICTS_LENS[DYE_INDEX_Purple], new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			for (OreDictMaterial tMat : ANY.Fe.mToThis) if (tMat != MT.Enori)
			RM.LaserEngraver.addRecipe2(T,512,512, OP.blockSolid.mat(tMat, 1), ST.amount(0, aEvent.mStack), ST.make(MD.AE, "item.ItemMultiMaterial", 1, 19));
		}});
		}};
	}
}
