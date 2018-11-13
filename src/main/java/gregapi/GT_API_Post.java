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

package gregapi;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import gregapi.api.Abstract_Mod;
import gregapi.api.Abstract_Proxy;
import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ModIDs;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.load.LoaderBookList;
import gregapi.load.LoaderItemData;
import gregapi.load.LoaderItemList;
import gregapi.load.LoaderUnificationTargets;
import gregapi.load.LoaderWoodDictionary;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;

/**
 * @author Gregorius Techneticies
 * 
 * This loads after the compatible Mods. The regular API loads before all compatible Mods.
 */
@Mod(modid=ModIDs.GAPI_POST, name="Greg-API-Post", version="GT6-MC1710", dependencies="required-after:"+ModIDs.GAPI+"; after:"+ModIDs.MD8+"; after:"+ModIDs.IC2+"; after:"+ModIDs.IC2C+"; after:"+ModIDs.NC+"; after:"+ModIDs.IHL+"; after:"+ModIDs.FMB+"; after:"+ModIDs.BAUBLES+"; after:"+ModIDs.GaSu+"; after:"+ModIDs.GaNe+"; after:"+ModIDs.GaEn+"; after:"+ModIDs.WdSt+"; after:"+ModIDs.CrGu+"; after:"+ModIDs.COFH_API+"; after:"+ModIDs.COFH_API_ENERGY+"; after:"+ModIDs.CC+"; after:"+ModIDs.OC+"; after:"+ModIDs.DE+"; after:"+ModIDs.FR+"; after:"+ModIDs.FRMB+"; after:"+ModIDs.BINNIE+"; after:"+ModIDs.BINNIE_BEE+"; after:"+ModIDs.BINNIE_TREE+"; after:"+ModIDs.BINNIE_GENETICS+"; after:"+ModIDs.BINNIE_BOTANY+"; after:"+ModIDs.IE+"; after:"+ModIDs.UB+"; after:"+ModIDs.COG+"; after:"+ModIDs.PFAA+"; after:"+ModIDs.ARS+"; after:"+ModIDs.TC+"; after:"+ModIDs.TCFM+"; after:"+ModIDs.BOTA+"; after:"+ModIDs.WTCH+"; after:"+ModIDs.HOWL+"; after:"+ModIDs.MoCr+"; after:"+ModIDs.GoG+"; after:"+ModIDs.LycM+"; after:"+ModIDs.LycM_Arctic+"; after:"+ModIDs.LycM_Demon+"; after:"+ModIDs.LycM_Desert+"; after:"+ModIDs.LycM_Forest+"; after:"+ModIDs.LycM_Fresh+"; after:"+ModIDs.LycM_Inferno+"; after:"+ModIDs.LycM_Jungle+"; after:"+ModIDs.LycM_Mountain+"; after:"+ModIDs.LycM_Plains+"; after:"+ModIDs.LycM_Salt+"; after:"+ModIDs.LycM_Shadow+"; after:"+ModIDs.LycM_Swamp+"; after:"+ModIDs.RC+"; after:"+ModIDs.BP+"; after:"+ModIDs.PR+"; after:"+ModIDs.PR_EXPANSION+"; after:"+ModIDs.PR_INTEGRATION+"; after:"+ModIDs.PR_TRANSMISSION+"; after:"+ModIDs.PR_TRANSPORT+"; after:"+ModIDs.PE+"; after:"+ModIDs.AE+"; after:"+ModIDs.MO+"; after:"+ModIDs.TE_FOUNDATION+"; after:"+ModIDs.TE_DYNAMICS+"; after:"+ModIDs.TE+"; after:"+ModIDs.ZTONES+"; after:"+ModIDs.CHSL+"; after:"+ModIDs.EtFu+"; after:"+ModIDs.BB+"; after:"+ModIDs.DYNAMIC_TREES+"; after:"+ModIDs.BbLC+"; after:"+ModIDs.CARP+"; after:"+ModIDs.BETTER_RECORDS+"; after:"+ModIDs.TF+"; after:"+ModIDs.ERE+"; after:"+ModIDs.MFR+"; after:"+ModIDs.PnC+"; after:"+ModIDs.ExU+"; after:"+ModIDs.ExS+"; after:"+ModIDs.EIO+"; after:"+ModIDs.RT+"; after:"+ModIDs.AA+"; after:"+ModIDs.HaC+"; after:"+ModIDs.CookBook+"; after:"+ModIDs.APC+"; after:"+ModIDs.ENVM+"; after:"+ModIDs.MaCr+"; after:"+ModIDs.BC_TRANSPORT+"; after:"+ModIDs.BC_SILICON+"; after:"+ModIDs.BC_FACTORY+"; after:"+ModIDs.BC_ENERGY+"; after:"+ModIDs.BC_ROBOTICS+"; after:"+ModIDs.BC+"; after:"+ModIDs.BC_BUILDERS+"; after:"+ModIDs.MgC+"; after:"+ModIDs.BR+"; after:"+ModIDs.HBM+"; after:"+ModIDs.DRGN+"; after:"+ModIDs.ElC+"; after:"+ModIDs.CrC+"; after:"+ModIDs.ReC+"; after:"+ModIDs.RoC+"; after:"+ModIDs.Mek+"; after:"+ModIDs.Mek_Tools+"; after:"+ModIDs.Mek_Generators+"; after:"+ModIDs.GC+"; after:"+ModIDs.GC_PLANETS+"; after:"+ModIDs.GC_GALAXYSPACE+"; after:"+ModIDs.VULPES+"; after:"+ModIDs.GC_ADV_ROCKETRY+"; after:"+ModIDs.BTL+"; after:"+ModIDs.AETHER+"; after:"+ModIDs.ATUM+"; after:"+ModIDs.EB+"; after:"+ModIDs.EBXL+"; after:"+ModIDs.BoP+"; after:"+ModIDs.HiL+"; after:"+ModIDs.ATG+"; after:"+ModIDs.RTG+"; after:"+ModIDs.RWG+"; after:"+ModIDs.MYST+"; after:"+ModIDs.WARPBOOK+"; after:"+ModIDs.LOSTBOOKS+"; after:"+ModIDs.EUREKA+"; after:"+ModIDs.ENCHIRIDION+"; after:"+ModIDs.ENCHIRIDION2+"; after:"+ModIDs.SmAc+"; after:"+ModIDs.HQM+"; after:"+ModIDs.JABBA+"; after:"+ModIDs.MaCu+"; after:"+ModIDs.PdC+"; after:"+ModIDs.Bamboo+"; after:"+ModIDs.GrC+"; after:"+ModIDs.GrC_Apples+"; after:"+ModIDs.GrC_Bamboo+"; after:"+ModIDs.GrC_Bees+"; after:"+ModIDs.GrC_Cellar+"; after:"+ModIDs.GrC_Fish+"; after:"+ModIDs.GrC_Grapes+"; after:"+ModIDs.GrC_Hops+"; after:"+ModIDs.GrC_Milk+"; after:"+ModIDs.GrC_Rice+"; after:"+ModIDs.BWM+"; after:"+ModIDs.OMT+"; after:"+ModIDs.TG+"; after:"+ModIDs.FM+"; after:"+ModIDs.FZ+"; after:"+ModIDs.MNTL+"; after:"+ModIDs.OB+"; after:"+ModIDs.TiC+"; after:"+ModIDs.WR_CBE_C+"; after:"+ModIDs.WR_CBE_A+"; after:"+ModIDs.WR_CBE_L+"; after:"+ModIDs.VOLTZ+"; after:"+ModIDs.MFFS+"; after:"+ModIDs.ICBM+"; after:"+ModIDs.ATSCI+"; after:inventorytweaks; after:ironbackpacks; after:journeymap; after:LogisticsPipes; after:LunatriusCore; after:NEIAddons; after:NEIAddons|Developer; after:NEIAddons|AppEng; after:NEIAddons|Botany; after:NEIAddons|Forestry; after:NEIAddons|CraftingTables; after:NEIAddons|ExNihilo; after:neiintegration; after:openglasses; after:simplyjetpacks; after:Stackie; after:StevesCarts; after:TiCTooltips; after:worldedit; after:McMultipart")
public class GT_API_Post extends Abstract_Mod {
	public GT_API_Post() {GAPI_POST = this;}
	
	@Override public String getModID() {return MD.GAPI_POST.mID;}
	@Override public String getModName() {return MD.GAPI_POST.mName;}
	@Override public String getModNameForLog() {return "GT_API_POST";}
	@Override public Abstract_Proxy getProxy() {return null;}
	
	@Mod.EventHandler public final void onPreLoad           (FMLPreInitializationEvent  aEvent) {onModPreInit(aEvent);}
	@Mod.EventHandler public final void onLoad              (FMLInitializationEvent     aEvent) {onModInit(aEvent);}
	@Mod.EventHandler public final void onPostLoad          (FMLPostInitializationEvent aEvent) {onModPostInit(aEvent);}
	@Mod.EventHandler public final void onServerStarting    (FMLServerStartingEvent     aEvent) {onModServerStarting(aEvent);}
	@Mod.EventHandler public final void onServerStarted     (FMLServerStartedEvent      aEvent) {onModServerStarted(aEvent);}
	@Mod.EventHandler public final void onServerStopping    (FMLServerStoppingEvent     aEvent) {onModServerStopping(aEvent);}
	@Mod.EventHandler public final void onServerStopped     (FMLServerStoppedEvent      aEvent) {onModServerStopped(aEvent);}
	
	@Override
	public void onModPreInit2(FMLPreInitializationEvent aEvent) {
		try {
			OUT.println(getModNameForLog() + ": Sorting Greg-API-Post to the end of the Mod List for further processing.");
			LoadController tLoadController = ((LoadController)UT.Reflection.getFieldContent(Loader.instance(), "modController", T, T));
			List<ModContainer> tModList = tLoadController.getActiveModList(), tNewModsList = new ArrayList<>(tModList.size());
			ModContainer tGregTech = null;
			for (short i = 0; i < tModList.size(); i++) {
				ModContainer tMod = tModList.get(i);
				if (tMod.getModId().equalsIgnoreCase(MD.GAPI_POST.mID)) tGregTech = tMod; else tNewModsList.add(tMod);
			}
			if (tGregTech != null) tNewModsList.add(tGregTech);
			UT.Reflection.getField(tLoadController, "activeModList", T, T).set(tLoadController, tNewModsList);
		} catch(Throwable e) {
			if (D1) e.printStackTrace(ERR);
		}
		
		// Fixing Items of certain Mods.
		for (Item tItem : new Item[] {
		  ST.item(MD.GrC_Grapes, "grc.grapes")
		, ST.item(MD.FR, "letters")
		, ST.item(MD.FZ, "acid")
		}) if (tItem != null) tItem.setMaxDamage(0).setHasSubtypes(T);
		
		new LoaderItemList().run();
		new LoaderItemData().run();
		new LoaderUnificationTargets().run();
		
		if (MD.MET.mLoaded) {
			MT.OREMATS.Bauxite      .addOreByProducts(MT.Alduorite      );
			MT.OREMATS.Chalcopyrite .addOreByProducts(MT.Infuscolium    );
			MT.OREMATS.Scheelite    .addOreByProducts(MT.Rubracium      );
			MT.OREMATS.Pentlandite  .addOreByProducts(MT.Meutoite       );
			MT.OREMATS.Magnesite    .addOreByProducts(MT.Lemurite       );
			MT.OREMATS.Stibnite     .addOreByProducts(MT.Ceruclase      );
			MT.TiO2                 .addOreByProducts(MT.Atlarus        );
			MT.OREMATS.Garnierite   .addOreByProducts(MT.Oureclase      );
			MT.OREMATS.Cooperite    .addOreByProducts(MT.Kalendrite     );
			MT.OREMATS.Ilmenite     .addOreByProducts(MT.Orichalcum     );
			MT.OREMATS.Sphalerite   .addOreByProducts(MT.Carmot         );
			MT.OREMATS.Cinnabar     .addOreByProducts(MT.Sanguinite     );
			MT.OREMATS.Malachite    .addOreByProducts(MT.Vyroxeres      );
			MT.MnO2                 .addOreByProducts(MT.Eximite        );
			MT.OREMATS.Cobaltite    .addOreByProducts(MT.Prometheum     );
			MT.OREMATS.Cassiterite  .addOreByProducts(MT.Ignatius       );
			MT.OREMATS.Wolframite   .addOreByProducts(MT.Vulcanite      );
			MT.Fe2O3                .addOreByProducts(MT.DeepIron       );
			MT.OREMATS.Magnetite    .addOreByProducts(MT.ShadowIron     );
		}
		if (MD.Mek.mLoaded) {
			MT.OREMATS.Galena.addOreByProducts(MT.FakeOsmium);
			MT.OREMATS.Magnetite.addOreByProducts(MT.FakeOsmium);
			MT.OREMATS.Ferrovanadium.addOreByProducts(MT.FakeOsmium);
			MT.OREMATS.BasalticMineralSand.addOreByProducts(MT.FakeOsmium);
			MT.OREMATS.GraniticMineralSand.addOreByProducts(MT.FakeOsmium);
		}
		if (MD.TiC.mLoaded) {
			MT.Co.addOreByProducts(MT.Ardite);
		}
		if (MD.BR.mLoaded) {
			MT.Th.addOreByProducts(MT.Cyanite);
			MT.Monazite.addOreByProducts(MT.Cyanite);
			MT.Forcicium.addOreByProducts(MT.Cyanite);
			MT.Forcillium.addOreByProducts(MT.Cyanite);
			MT.OREMATS.Pitchblende.addOreByProducts(MT.Yellorium);
			MT.OREMATS.Uraninite.addOreByProducts(MT.Yellorium);
			MT.U_238.addOreByProducts(MT.Yellorium);
			MT.Pu.addOreByProducts(MT.Blutonium);
			MT.Am.addOreByProducts(MT.Blutonium);
		}
		if (MD.AE.mLoaded) {
			OP.gem  .disableItemGeneration(MT.CertusQuartz, MT.Fluix);
			OP.dust .disableItemGeneration(MT.CertusQuartz, MT.Fluix);
		}
		if (MD.AA.mLoaded) {
			MT.OREMATS.Barite.addOreByProducts(MT.BlackQuartz);
		}
		
	}
	
	@Override
	public void onModInit2(FMLInitializationEvent aEvent) {
		new LoaderWoodDictionary().run();
		
		// Atum violates the "Items have to be created in preInit" Rule...
		if (MD.ATUM.mLoaded) {
			IL.ATUM_Scarab      .set(ST.make(MD.ATUM, "item.scarab" , 1, 0), new OreDictItemData(MT.Au, 4*U, MT.Diamond, U));
			IL.ATUM_Limestone   .set(ST.make(MD.ATUM, "tile.stone"  , 1, 0), OP.stone.dat(MT.Limestone));
			IL.ATUM_Limecobble  .set(ST.make(MD.ATUM, "tile.cobble" , 1, 0), OP.stone.dat(MT.Limestone));
			
			OM.reg("cropDate"               , ST.make(MD.ATUM, "item.date", 1, 0));
			OM.reg("cropFlax"               , ST.make(MD.ATUM, "item.flax", 1, 0));
			OM.reg("seedFlax"               , ST.make(MD.ATUM, "item.flaxSeeds", 1, 0));
			OM.reg("itemPelt"               , ST.make(MD.ATUM, "item.wolfPelt", 1, 0));
			OM.reg(OP.dust, MT.Ectoplasm    , ST.make(MD.ATUM, "item.ectoplasm", 1, 0));
			OreDictManager.INSTANCE.setItemData_(ST.make(MD.ATUM, "tile.ironOre"        , 1, 0), OP.oreLimestone.dat(MT.Fe          ));
			OreDictManager.INSTANCE.setItemData_(ST.make(MD.ATUM, "tile.goldOre"        , 1, 0), OP.oreLimestone.dat(MT.Au          ));
			OreDictManager.INSTANCE.setItemData_(ST.make(MD.ATUM, "tile.redstoneOre"    , 1, 0), OP.oreLimestone.dat(MT.Redstone    ));
			OreDictManager.INSTANCE.setItemData_(ST.make(MD.ATUM, "tile.lapisOre"       , 1, 0), OP.oreLimestone.dat(MT.Lapis       ));
			OreDictManager.INSTANCE.setItemData_(ST.make(MD.ATUM, "tile.coalOre"        , 1, 0), OP.oreLimestone.dat(MT.Coal        ));
			OreDictManager.INSTANCE.setItemData_(ST.make(MD.ATUM, "tile.diamondOre"     , 1, 0), OP.oreLimestone.dat(MT.Diamond     ));
			
			OM.data(MD.ATUM, "item.stoneChunk", 1, 0, MT.Limestone, U);
			OM.data(MD.ATUM, "tile.sand", 1, W, MT.Sand, U);
			OM.data(MD.ATUM, "tile.framedGlass", 1, W, MT.Glass, U);
			OM.data(MD.ATUM, "tile.crystalGlass", 1, W, MT.Glass, U);
			OM.data(MD.ATUM, "tile.framedStainedGlass", 1, W, MT.Glass, U);
			OM.data(MD.ATUM, "tile.crystalStainedGlass", 1, W, MT.Glass, U);
			OM.data(MD.ATUM, "tile.thinFramedGlass", 1, W, MT.Glass, 3*U8);
			OM.data(MD.ATUM, "tile.thinCrystalGlass", 1, W, MT.Glass, 3*U8);
			OM.data(MD.ATUM, "tile.thinFramedStainedGlass", 1, W, MT.Glass, 3*U8);
			OM.data(MD.ATUM, "tile.thinCrystalStainedGlass", 1, W, MT.Glass, 3*U8);
			
			OM.data(MD.ATUM, "item.loot", 1,  2, MT.Ag  , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,  3, MT.Ag  , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,  4, MT.Au  , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,  5, MT.Au  , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,  6, MT.Au  , 2*U, MT.BlueSapphire  , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,  7, MT.Au  , 2*U, MT.BlueSapphire  , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,  8, MT.Au  , 2*U, MT.Ruby          , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,  9, MT.Au  , 2*U, MT.Ruby          , 2*U);
			OM.data(MD.ATUM, "item.loot", 1, 10, MT.Au  , 2*U, MT.Emerald       , 2*U);
			OM.data(MD.ATUM, "item.loot", 1, 11, MT.Au  , 2*U, MT.Emerald       , 2*U);
			OM.data(MD.ATUM, "item.loot", 1, 12, MT.Au  , 2*U, MT.Diamond       , 2*U);
			OM.data(MD.ATUM, "item.loot", 1, 13, MT.Au  , 2*U, MT.Diamond       , 2*U);
			OM.data(MD.ATUM, "item.loot", 1, 34, MT.Ag  ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 35, MT.Ag  ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 36, MT.Au  ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 37, MT.Au  ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 38, MT.Au  ,   U, MT.BlueSapphire  ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 39, MT.Au  ,   U, MT.BlueSapphire  ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 40, MT.Au  ,   U, MT.Ruby          ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 41, MT.Au  ,   U, MT.Ruby          ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 42, MT.Au  ,   U, MT.Emerald       ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 43, MT.Au  ,   U, MT.Emerald       ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 44, MT.Au  ,   U, MT.Diamond       ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 45, MT.Au  ,   U, MT.Diamond       ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 66, MT.Ag  ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 67, MT.Ag  ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 68, MT.Au  ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 69, MT.Au  ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 70, MT.Au  ,   U, MT.BlueSapphire  ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 71, MT.Au  ,   U, MT.BlueSapphire  ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 72, MT.Au  ,   U, MT.Ruby          ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 73, MT.Au  ,   U, MT.Ruby          ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 74, MT.Au  ,   U, MT.Emerald       ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 75, MT.Au  ,   U, MT.Emerald       ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 76, MT.Au  ,   U, MT.Diamond       ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 77, MT.Au  ,   U, MT.Diamond       ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 98, MT.Ag  ,   U);
			OM.data(MD.ATUM, "item.loot", 1, 99, MT.Ag  ,   U);
			OM.data(MD.ATUM, "item.loot", 1,100, MT.Au  ,   U);
			OM.data(MD.ATUM, "item.loot", 1,101, MT.Au  ,   U);
			OM.data(MD.ATUM, "item.loot", 1,102, MT.Au  ,   U, MT.BlueSapphire  ,   U);
			OM.data(MD.ATUM, "item.loot", 1,103, MT.Au  ,   U, MT.BlueSapphire  ,   U);
			OM.data(MD.ATUM, "item.loot", 1,104, MT.Au  ,   U, MT.Ruby          ,   U);
			OM.data(MD.ATUM, "item.loot", 1,105, MT.Au  ,   U, MT.Ruby          ,   U);
			OM.data(MD.ATUM, "item.loot", 1,106, MT.Au  ,   U, MT.Emerald       ,   U);
			OM.data(MD.ATUM, "item.loot", 1,107, MT.Au  ,   U, MT.Emerald       ,   U);
			OM.data(MD.ATUM, "item.loot", 1,108, MT.Au  ,   U, MT.Diamond       ,   U);
			OM.data(MD.ATUM, "item.loot", 1,109, MT.Au  ,   U, MT.Diamond       ,   U);
			OM.data(MD.ATUM, "item.loot", 1,130, MT.Ag  , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,131, MT.Ag  , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,132, MT.Au  , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,133, MT.Au  , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,134, MT.Au  , 2*U, MT.BlueSapphire  , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,135, MT.Au  , 2*U, MT.BlueSapphire  , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,136, MT.Au  , 2*U, MT.Ruby          , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,137, MT.Au  , 2*U, MT.Ruby          , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,138, MT.Au  , 2*U, MT.Emerald       , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,139, MT.Au  , 2*U, MT.Emerald       , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,140, MT.Au  , 2*U, MT.Diamond       , 2*U);
			OM.data(MD.ATUM, "item.loot", 1,141, MT.Au  , 2*U, MT.Diamond       , 2*U);
		}
		
		// And Project Red violates that Rule aswell...
		if (MD.PR.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.ingot  , MT.RedAlloy                   , ST.make(MD.PR, "projectred.core.part", 1, 10));
			OreDictManager.INSTANCE.setTarget(OP.ingot  , MT.ElectrotineAlloy           , ST.make(MD.PR, "projectred.core.part", 1, 55));
			OreDictManager.INSTANCE.setTarget(OP.dust   , MT.Electrotine                , ST.make(MD.PR, "projectred.core.part", 1, 56));
		}
		
		// Okay I should not have wondered about Blue Power doing the same garbage considering Project Red...
		if (MD.BP.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.ingot  , MT.RedAlloy                   , ST.make(MD.BP, "red_alloy_ingot", 1, 0));
			OreDictManager.INSTANCE.setTarget(OP.ingot  , MT.BlueAlloy                  , ST.make(MD.BP, "blue_alloy_ingot", 1, 0));
			OreDictManager.INSTANCE.setTarget(OP.ingot  , MT.PurpleAlloy                , ST.make(MD.BP, "purple_alloy_ingot", 1, 0));
			OreDictManager.INSTANCE.setTarget(OP.dust   , MT.Teslatite                  , ST.make(MD.BP, "teslatite_dust", 1, 0));
			OreDictManager.INSTANCE.setTarget(OP.dust   , MT.UNUSED.InfusedTeslatite    , ST.make(MD.BP, "infused_teslatite_dust", 1, 0));
			OM.reg("seedFlax"                                                           , ST.make(MD.BP, "flax_seeds", 1, 0));
			OM.reg(DYE_OREDICTS_MIXABLE[DYE_INDEX_Purple]                               , ST.make(MD.BP, "indigo_dye", 1, 0));
			OM.reg("cropIndigo"                                                         , ST.make(MD.BP, "indigo_flower", 1, 0));
			OM.reg(OP.dustTiny.dat(MT.Zn)                                               , ST.make(MD.BP, "zinc_tiny_dust", 1, 0));
			OM.reg(OP.crushed.dat(MT.Zn)                                                , ST.make(MD.BP, "zinc_ore_crushed", 1, 0));
			OM.reg(OP.crushedPurified.dat(MT.Zn)                                        , ST.make(MD.BP, "zinc_ore_purified", 1, 0));
			OM.reg(OP.boule.dat(MT.Si)                                                  , ST.make(MD.BP, "silicon_boule", 1, 0));
		}
		
		// Yay for Chickenbones doing it wrong, I guess...
		if (MD.WR_CBE_C.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.stick  , MT.Obsidian                   , ST.make(MD.WR_CBE_C, "obsidianStick", 1, 0));
		}
		
		// Oh look, Matter Overdrive does this shit too...
		if (MD.MO.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.gem    , MT.Dilithium                  , ST.make(MD.MO, "dilithium_crystal", 1, 0));
			OreDictManager.INSTANCE.setTarget(OP.dust   , MT.TritaniumAlloy             , ST.make(MD.MO, "tritanium_dust", 1, 0));
			OreDictManager.INSTANCE.setTarget(OP.nugget , MT.TritaniumAlloy             , ST.make(MD.MO, "tritanium_nugget", 1, 0));
			OreDictManager.INSTANCE.setTarget(OP.ingot  , MT.TritaniumAlloy             , ST.make(MD.MO, "tritanium_ingot", 1, 0));
			OreDictManager.INSTANCE.setTarget(OP.plate  , MT.TritaniumAlloy             , ST.make(MD.MO, "tritanium_plate", 1, 0));
		}
		
		// ThermalExpansion gets on this ShitList too I guess...
		if (MD.TE.mLoaded) {
			OM.data(MD.TE, "Tank"   , 1,   1, ANY.Cu        ,  U * 1, MT.Glass  ,  U * 4);
			OM.data(MD.TE, "Tank"   , 1,   2, MT.Invar      ,  U * 4, ANY.Cu    ,  U * 1, MT.Glass  ,  U * 4);
			OM.data(MD.TE, "Tank"   , 1,   3, MT.Invar      ,  U * 4, ANY.Cu    ,  U * 1, MT.Glass  ,  U * 4);
			OM.data(MD.TE, "Tank"   , 1,   4, MT.Enderium   ,  U * 4, MT.Invar  ,  U * 4, ANY.Cu    ,  U * 1, MT.Glass  ,  U * 4);
		}
		
		// Wow, Ars Magica too is on this List, at least for its Blocks...
		IL.ARS_Cerublossom.set(ST.make(MD.ARS, "blueOrchid", 1, 0), null, "flowerCerublossom");
		IL.ARS_DesertNova .set(ST.make(MD.ARS, "desertNova", 1, 0), null, "flowerDesertNova");
		
		// Cooking for Blockheads is here too!...
		if (MD.CookBook.mLoaded) {
			OM.data(MD.CookBook, "recipebook", 1, W, MT.Paper, U*3);
		}
		
		// Grimoire of Gaia... though I did not expect them to have done a good job with that...
		if (MD.GoG.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.chunkGt, MT.Fe, ST.make(MD.GoG, "item.GrimoireOfGaia.Shard", 1, 0));
			OreDictManager.INSTANCE.setTarget(OP.chunkGt, MT.Au, ST.make(MD.GoG, "item.GrimoireOfGaia.Shard", 1, 1));
			OM.data(MD.GoG, "item.GrimoireOfGaia.Shard"     , 1, 2, MT.Diamond      ,  U4);
			OM.data(MD.GoG, "item.GrimoireOfGaia.Shard"     , 1, 3, MT.Emerald      ,  U4);
			OM.data(MD.GoG, "item.GrimoireOfGaia.Shard"     , 1, 4, MT.NetherStar   ,  U4);
			OM.data(MD.GoG, "item.GrimoireOfGaia.Shard"     , 1, 5, MT.EnderPearl   ,  U4);
			OM.data(MD.GoG, "item.GrimoireOfGaia.Shard"     , 1, 6, MT.Blaze        ,  U8);
			OM.data(MD.GoG, "item.GrimoireOfGaia.Fragment"  , 1, 0, MT.Emerald      ,  U8);
		}
		
		// Seems like it isn't "better" in all aspects.
		if (MD.BETTER_RECORDS.mLoaded) {
			OM.reg(OD.record, ST.make(MD.BETTER_RECORDS, "urlrecord", 1, 0));
			OM.reg(OD.record, ST.make(MD.BETTER_RECORDS, "urlmultirecord", 1, 0));
		}
	}
	
	@Override
	public void onModPostInit2(FMLPostInitializationEvent aEvent) {
		OUT.println(getModNameForLog() + ": Checking IC2 Recipe Lists.");
		if (DISABLE_ALL_IC2_COMPRESSOR_RECIPES  ) ic2.api.recipe.Recipes.compressor.getRecipes().clear();
		if (DISABLE_ALL_IC2_EXTRACTOR_RECIPES   ) ic2.api.recipe.Recipes.extractor .getRecipes().clear();
		if (DISABLE_ALL_IC2_MACERATOR_RECIPES   ) ic2.api.recipe.Recipes.macerator .getRecipes().clear();
		if (DISABLE_ALL_IC2_OREWASHER_RECIPES   ) ic2.api.recipe.Recipes.oreWashing.getRecipes().clear();
		if (DISABLE_ALL_IC2_CENTRIFUGE_RECIPES  ) ic2.api.recipe.Recipes.centrifuge.getRecipes().clear();
		
		OUT.println(getModNameForLog() + ": Registering things that are considered Flowers by Bumblebees.");
		if (MD.BoP.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.BoP, "flowers"));
			BlocksGT.FLOWERS.add(ST.block(MD.BoP, "flowers2"));
		}
		if (MD.EBXL.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.EBXL, "flower1"));
			BlocksGT.FLOWERS.add(ST.block(MD.EBXL, "flower2"));
			BlocksGT.FLOWERS.add(ST.block(MD.EBXL, "flower3"));
		}
		if (MD.TCFM.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.TCFM, "InkFlower"));
			BlocksGT.FLOWERS.add(ST.block(MD.TCFM, "UmbralBush"));
		}
		if (MD.BP.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.BP, "indigo_flower"));
		}
		if (MD.ARS.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.ARS, "blueOrchid"));
		}
		
		OUT.println(getModNameForLog() + ": Registering other Mods Enchantments for Materials");
		for (Enchantment tEnchant : Enchantment.enchantmentsList) if (tEnchant != null) {
			if ("enchantment.Magnetization".equalsIgnoreCase(tEnchant.getName())) {
				for (OreDictMaterial tMaterial : MT.ALL_MATERIALS_REGISTERED_HERE) {
					if (tMaterial == MT.NeodymiumMagnetic) {
						tMaterial.addEnchantmentForTools(tEnchant, 3).addEnchantmentForArmors(tEnchant, 3);
					} else if (tMaterial == MT.MeteoricSteel || tMaterial == MT.Meteorite) {
						tMaterial.addEnchantmentForTools(tEnchant, 2).addEnchantmentForArmors(tEnchant, 2);
					} else if (tMaterial.contains(TD.Properties.MAGNETIC_ACTIVE)) {
						tMaterial.addEnchantmentForTools(tEnchant, 1).addEnchantmentForArmors(tEnchant, 1);
					}
				}
			}
			if ("enchantment.Cold Touch".equalsIgnoreCase(tEnchant.getName())) {
				MT.Ice                  .addEnchantmentForTools(tEnchant, 1);
				MT.Snow                 .addEnchantmentForTools(tEnchant, 1);
				MT.FrozenIron           .addEnchantmentForTools(tEnchant, 2);
				MT.Blizz                .addEnchantmentForTools(tEnchant, 3);
				MT.Frezarite            .addEnchantmentForTools(tEnchant, 4);
				MT.InfusedWater         .addEnchantmentForTools(tEnchant, 4);
				MT.Cryotheum            .addEnchantmentForTools(tEnchant, 5);
			}
			if ("enchantment.frost_walker".equalsIgnoreCase(tEnchant.getName())) {
				MT.Ice                  .addEnchantmentForArmors(tEnchant, 1);
				MT.Snow                 .addEnchantmentForArmors(tEnchant, 1);
				MT.FrozenIron           .addEnchantmentForArmors(tEnchant, 1);
				MT.Blizz                .addEnchantmentForArmors(tEnchant, 1);
				MT.Frezarite            .addEnchantmentForArmors(tEnchant, 1);
				MT.InfusedWater         .addEnchantmentForArmors(tEnchant, 1);
				MT.Cryotheum            .addEnchantmentForArmors(tEnchant, 1);
			}
			if ("enchantment.railcraft.crowbar.implosion".equalsIgnoreCase(tEnchant.getName())) {
				for (OreDictMaterial tMat : ANY.Emerald.mToThis) tMat.addEnchantmentForTools(tEnchant, 5);
				for (OreDictMaterial tMat : ANY.Sapphire.mToThis) tMat.addEnchantmentForTools(tEnchant, 3);
				MT.Spinel               .addEnchantmentForTools(tEnchant, 3);
				MT.BalasRuby            .addEnchantmentForTools(tEnchant, 3);
				MT.Almandine            .addEnchantmentForTools(tEnchant, 2);
				MT.Grossular            .addEnchantmentForTools(tEnchant, 2);
				MT.Pyrope               .addEnchantmentForTools(tEnchant, 2);
				MT.Spessartine          .addEnchantmentForTools(tEnchant, 2);
				MT.Andradite            .addEnchantmentForTools(tEnchant, 2);
				MT.Uvarovite            .addEnchantmentForTools(tEnchant, 2);
				MT.Topaz                .addEnchantmentForTools(tEnchant, 5);
				MT.BlueTopaz            .addEnchantmentForTools(tEnchant, 5);
				MT.Tanzanite            .addEnchantmentForTools(tEnchant, 4);
				MT.Alexandrite          .addEnchantmentForTools(tEnchant, 5);
				MT.Opal                 .addEnchantmentForTools(tEnchant, 4);
				MT.Jasper               .addEnchantmentForTools(tEnchant, 2);
				MT.Olivine              .addEnchantmentForTools(tEnchant, 2);
				MT.Amethyst             .addEnchantmentForTools(tEnchant, 3);
				MT.Dioptase             .addEnchantmentForTools(tEnchant, 3);
				MT.Jade                 .addEnchantmentForTools(tEnchant, 7);
				MT.Craponite            .addEnchantmentForTools(tEnchant, 1);
				MT.EnderAmethyst        .addEnchantmentForTools(tEnchant, 5);
			}
		}
		
		OUT.println(getModNameForLog() + ": Doing Books for Shelves.");
		new LoaderBookList().run();
		
		OUT.println(getModNameForLog() + ": Fixing Chemical Formula Tooltips of certain Elements");
		/** Diatomic Elements get a Subscript 2 appended to their ToolTip after PostInit. That way the ToolTip Calculation works properly until PostInit happens. */
		for (OreDictMaterial tMaterial : OreDictMaterial.MATERIAL_MAP.values()) if (tMaterial.contains(TD.Atomic.DIATOMIC_NONMETAL)) tMaterial.mTooltipChemical += "\u2082";
		
		MT.P.mTooltipChemical += "\u2084";
		MT.S.mTooltipChemical += "\u2088";
		MT.Se.mTooltipChemical += "\u2088";
	}
	
	@Override
	public void onModServerStarting2(FMLServerStartingEvent aEvent) {
		if (DISABLE_ALL_IC2_COMPRESSOR_RECIPES  ) ic2.api.recipe.Recipes.compressor.getRecipes().clear();
		if (DISABLE_ALL_IC2_EXTRACTOR_RECIPES   ) ic2.api.recipe.Recipes.extractor .getRecipes().clear();
		if (DISABLE_ALL_IC2_MACERATOR_RECIPES   ) ic2.api.recipe.Recipes.macerator .getRecipes().clear();
		if (DISABLE_ALL_IC2_OREWASHER_RECIPES   ) ic2.api.recipe.Recipes.oreWashing.getRecipes().clear();
		if (DISABLE_ALL_IC2_CENTRIFUGE_RECIPES  ) ic2.api.recipe.Recipes.centrifuge.getRecipes().clear();
	}
	
	@Override public void onModServerStarted2(FMLServerStartedEvent aEvent) {/**/}
	@Override public void onModServerStopping2(FMLServerStoppingEvent aEvent) {/**/}
	@Override public void onModServerStopped2(FMLServerStoppedEvent aEvent) {/**/}
}
