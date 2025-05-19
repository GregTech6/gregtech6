/**
 * Copyright (c) 2025 GregTech-6 Team
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

import appeng.api.AEApi;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.*;
import gregapi.api.Abstract_Mod;
import gregapi.api.Abstract_Proxy;
import gregapi.data.*;
import gregapi.load.*;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.render.IconContainerCopied;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.wooddict.*;
import gregapi.worldgen.StoneLayer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import twilightforest.TFTreasure;
import twilightforest.TFTreasureTable;

import java.util.ArrayList;
import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 *
 * This loads after compatible Mods. The regular API loads before all compatible Mods.
 */
@Mod(modid=ModIDs.GAPI_POST, name="Greg-API-Post", version="GT6-MC1710", dependencies="required-after:"+ModIDs.GAPI+"; after:"+ModIDs.MD8+"; after:"+ModIDs.IC2+"; after:"+ModIDs.IC2C+"; after:"+ModIDs.NC+"; after:"+ModIDs.IHL+"; after:"+ModIDs.FUNK+"; after:"+ModIDs.BAUBLES+"; after:"+ModIDs.HEE+"; after:"+ModIDs.GaSu+"; after:"+ModIDs.GaNe+"; after:"+ModIDs.GaEn+"; after:"+ModIDs.WdSt+"; after:"+ModIDs.CrGu+"; after:"+ModIDs.COFH_API+"; after:"+ModIDs.COFH_API_ENERGY+"; after:"+ModIDs.COFH_CORE+"; after:"+ModIDs.CC+"; after:"+ModIDs.OC+"; after:"+ModIDs.HEX+"; after:"+ModIDs.DE+"; after:"+ModIDs.AV+"; after:"+ModIDs.FR+"; after:"+ModIDs.FRMB+"; after:"+ModIDs.BINNIE+"; after:"+ModIDs.BINNIE_BEE+"; after:"+ModIDs.BINNIE_TREE+"; after:"+ModIDs.BINNIE_GENETICS+"; after:"+ModIDs.BINNIE_BOTANY+"; after:"+ModIDs.IE+"; after:"+ModIDs.UB+"; after:"+ModIDs.COG+"; after:"+ModIDs.PFAA+"; after:"+ModIDs.MIN+"; after:"+ModIDs.RH+"; after:"+ModIDs.CANDY+"; after:"+ModIDs.ABYSSAL+"; after:"+ModIDs.SOULFOREST+"; after:"+ModIDs.ARS+"; after:"+ModIDs.TC+"; after:"+ModIDs.TCTE+"; after:"+ModIDs.TCFM+"; after:"+ModIDs.BOTA+"; after:"+ModIDs.ALF+"; after:"+ModIDs.WTCH+"; after:"+ModIDs.HOWL+"; after:"+ModIDs.MoCr+"; after:"+ModIDs.WiMo+"; after:"+ModIDs.Birb+"; after:"+ModIDs.ChocoCraft+"; after:"+ModIDs.GoG+"; after:"+ModIDs.DRPG+"; after:"+ModIDs.LycM+"; after:"+ModIDs.LycM_Arctic+"; after:"+ModIDs.LycM_Demon+"; after:"+ModIDs.LycM_Desert+"; after:"+ModIDs.LycM_Forest+"; after:"+ModIDs.LycM_Fresh+"; after:"+ModIDs.LycM_Inferno+"; after:"+ModIDs.LycM_Jungle+"; after:"+ModIDs.LycM_Mountain+"; after:"+ModIDs.LycM_Plains+"; after:"+ModIDs.LycM_Salt+"; after:"+ModIDs.LycM_Shadow+"; after:"+ModIDs.LycM_Swamp+"; after:"+ModIDs.RC+"; after:"+ModIDs.BP+"; after:"+ModIDs.PR+"; after:"+ModIDs.PR_EXPANSION+"; after:"+ModIDs.PR_INTEGRATION+"; after:"+ModIDs.PR_TRANSMISSION+"; after:"+ModIDs.PR_TRANSPORT+"; after:"+ModIDs.PR_EXPLORATION+"; after:"+ModIDs.PR_COMPATIBILITY+"; after:"+ModIDs.PR_FABRICATION+"; after:"+ModIDs.PR_ILLUMINATION+"; after:"+ModIDs.PE+"; after:"+ModIDs.AE+"; after:"+ModIDs.MO+"; after:"+ModIDs.TE_FOUNDATION+"; after:"+ModIDs.TE_DYNAMICS+"; after:"+ModIDs.TE_DRILLS+"; after:"+ModIDs.TE+"; after:"+ModIDs.ZTONES+"; after:"+ModIDs.CHSL+"; after:"+ModIDs.NePl+"; after:"+ModIDs.NeLi+"; after:"+ModIDs.EnLi+"; after:"+ModIDs.EtFu+"; after:"+ModIDs.BB+"; after:"+ModIDs.DYNAMIC_TREES+"; after:"+ModIDs.BbLC+"; after:"+ModIDs.CARP+"; after:"+ModIDs.BETTER_RECORDS+"; after:"+ModIDs.TF+"; after:"+ModIDs.ERE+"; after:"+ModIDs.MFR+"; after:"+ModIDs.FSP+"; after:"+ModIDs.SC2+"; after:"+ModIDs.PnC+"; after:"+ModIDs.ExU+"; after:"+ModIDs.ExS+"; after:"+ModIDs.EIO+"; after:"+ModIDs.RT+"; after:"+ModIDs.AA+"; after:"+ModIDs.TreeCap+"; after:"+ModIDs.HaC+"; after:"+ModIDs.CookBook+"; after:"+ModIDs.APC+"; after:"+ModIDs.ENVM+"; after:"+ModIDs.MaCr+"; after:"+ModIDs.BC_TRANSPORT+"; after:"+ModIDs.BC_SILICON+"; after:"+ModIDs.BC_FACTORY+"; after:"+ModIDs.BC_ENERGY+"; after:"+ModIDs.BC_ROBOTICS+"; after:"+ModIDs.BC+"; after:"+ModIDs.BC_BUILDERS+"; after:"+ModIDs.MgC+"; after:"+ModIDs.BR+"; after:"+ModIDs.HBM+"; after:"+ModIDs.ELN+"; after:"+ModIDs.DRGN+"; after:"+ModIDs.ElC+"; after:"+ModIDs.CrC+"; after:"+ModIDs.ReC+"; after:"+ModIDs.RoC+"; after:"+ModIDs.Mek+"; after:"+ModIDs.Mek_Tools+"; after:"+ModIDs.Mek_Generators+"; after:"+ModIDs.GC+"; after:"+ModIDs.GC_PLANETS+"; after:"+ModIDs.GC_GALAXYSPACE+"; after:"+ModIDs.VULPES+"; after:"+ModIDs.GC_ADV_ROCKETRY+"; after:"+ModIDs.GC_EXTRAPLANETS+"; after:"+ModIDs.BTL+"; after:"+ModIDs.AETHER+"; after:"+ModIDs.AETHEL+"; after:"+ModIDs.TROPIC+"; after:"+ModIDs.ATUM+"; after:"+ModIDs.EB+"; after:"+ModIDs.EBXL+"; after:"+ModIDs.BoP+"; after:"+ModIDs.HiL+"; after:"+ModIDs.ATG+"; after:"+ModIDs.RTG+"; after:"+ModIDs.RWG+"; after:"+ModIDs.CW2+"; after:"+ModIDs.A97+"; after:"+ModIDs.A97_MINING+"; after:"+ModIDs.MYST+"; after:"+ModIDs.WARPBOOK+"; after:"+ModIDs.LOSTBOOKS+"; after:"+ModIDs.LOOTBAGS+"; after:"+ModIDs.EUREKA+"; after:"+ModIDs.ENCHIRIDION+"; after:"+ModIDs.ENCHIRIDION2+"; after:"+ModIDs.SmAc+"; after:"+ModIDs.HQM+"; after:"+ModIDs.SD+"; after:"+ModIDs.BTRS+"; after:"+ModIDs.JABBA+"; after:"+ModIDs.MaCu+"; after:"+ModIDs.PdC+"; after:"+ModIDs.Bamboo+"; after:"+ModIDs.PMP+"; after:"+ModIDs.Fossil+"; after:"+ModIDs.GrC+"; after:"+ModIDs.GrC_Apples+"; after:"+ModIDs.GrC_Bamboo+"; after:"+ModIDs.GrC_Bees+"; after:"+ModIDs.GrC_Cellar+"; after:"+ModIDs.GrC_Fish+"; after:"+ModIDs.GrC_Grapes+"; after:"+ModIDs.GrC_Hops+"; after:"+ModIDs.GrC_Milk+"; after:"+ModIDs.GrC_Rice+"; after:"+ModIDs.BG2+"; after:"+ModIDs.BWM+"; after:"+ModIDs.OMT+"; after:"+ModIDs.TG+"; after:"+ModIDs.FM+"; after:"+ModIDs.FZ+"; after:"+ModIDs.MNTL+"; after:"+ModIDs.OB+"; after:"+ModIDs.PA+"; after:"+ModIDs.TiC+"; after:"+ModIDs.MF2+"; after:"+ModIDs.TRANSLOCATOR+"; after:"+ModIDs.WR_CBE_C+"; after:"+ModIDs.WR_CBE_A+"; after:"+ModIDs.WR_CBE_L+"; after:"+ModIDs.VOLTZ+"; after:"+ModIDs.MFFS+"; after:"+ModIDs.ICBM+"; after:"+ModIDs.ATSCI+"; after:inventorytweaks; after:ironbackpacks; after:journeymap; after:LogisticsPipes; after:LunatriusCore; after:NEIAddons; after:NEIAddons|Developer; after:NEIAddons|AppEng; after:NEIAddons|Botany; after:NEIAddons|Forestry; after:NEIAddons|CraftingTables; after:NEIAddons|ExNihilo; after:neiintegration; after:openglasses; after:simplyjetpacks; after:Stackie; after:StevesCarts; after:TiCTooltips; after:worldedit; after:McMultipart")
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
			LoadController tLoadController = ((LoadController)UT.Reflection.getFieldContent(Loader.instance(), "modController", T, T));
			List<ModContainer> tModList = tLoadController.getActiveModList(), tNewModsList = new ArrayList<>(tModList.size());
			ModContainer tGregTech = null;
			for (short i = 0; i < tModList.size(); i++) {
				ModContainer tMod = tModList.get(i);
				if (tMod.getModId().equalsIgnoreCase(MD.GAPI_POST.mID)) tGregTech = tMod; else tNewModsList.add(tMod);
			}
			if (tGregTech != null) tNewModsList.add(tGregTech);
			UT.Reflection.setFieldContent(tLoadController, "activeModList", tNewModsList);
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
		
		// Fixing Items of certain Mods.
		for (Item tItem : new Item[] {
		  ST.item(MD.GrC_Grapes, "grc.grapes")
		, ST.item(MD.FR, "letters")
		, ST.item(MD.FZ, "acid")
		}) if (tItem != null) tItem.setMaxDamage(0).setHasSubtypes(T);
		
		OM.blacklist(ST.make(MD.GrC_Bees, "grcbees.BeesWax", 1, 1));
		OM.blacklist(ST.make(MD.GrC_Bees, "grcbees.BeesWax", 1, 2));
		OM.blacklist(ST.make(MD.TC, "ItemResource", 1, 6));
		OM.blacklist(ST.make(MD.TC, "ItemResource", 1,18));
		OM.blacklist(ST.make(MD.FZ, "diamond_shard", 1, W));
		OM.blacklist(ST.make(MD.ReC, "reactorcraft_item_fluorite", 1, W));
		OM.blacklist(ST.make(MD.ERE, "encrustedDiamond", 1, W));
		OM.blacklist(ST.make(MD.BTL, "groundStuff", 1, 16));
		OM.blacklist(ST.make(MD.BoP, "gems", 1, 0));
		OM.blacklist(ST.make(MD.BoP, "gems", 1, 1));
		OM.blacklist(ST.make(MD.BoP, "gems", 1, 2));
		OM.blacklist(ST.make(MD.BoP, "gems", 1, 3));
		OM.blacklist(ST.make(MD.BoP, "gems", 1, 4));
		OM.blacklist(ST.make(MD.BoP, "gems", 1, 5));
		OM.blacklist(ST.make(MD.BoP, "gems", 1, 6));
		OM.blacklist(ST.make(MD.BoP, "gems", 1, 7));
		
		new LoaderItemList().run();
		new LoaderItemData().run();
		new LoaderUnificationTargets().run();
		
		if (MD.MET.mLoaded) {
			MT.OREMATS.Bauxite            .addOreByProducts(MT.Alduorite      );
			MT.OREMATS.Chalcopyrite       .addOreByProducts(MT.Infuscolium    );
			MT.OREMATS.Scheelite          .addOreByProducts(MT.Rubracium      );
			MT.OREMATS.Pentlandite        .addOreByProducts(MT.Meutoite       );
			MT.MgCO3                      .addOreByProducts(MT.Lemurite       );
			MT.Ardite                     .addOreByProducts(MT.Aredrite       );
			MT.Co                         .addOreByProducts(MT.Aredrite       );
			MT.OREMATS.Cobaltite          .addOreByProducts(MT.Aredrite       );
			MT.OREMATS.Stibnite           .addOreByProducts(MT.Ceruclase      );
			MT.OREMATS.Garnierite         .addOreByProducts(MT.Oureclase      );
			MT.OREMATS.Cooperite          .addOreByProducts(MT.Kalendrite     );
			MT.OREMATS.Ilmenite           .addOreByProducts(MT.Orichalcum     );
			MT.OREMATS.Sphalerite         .addOreByProducts(MT.Carmot         );
			MT.OREMATS.Cinnabar           .addOreByProducts(MT.Sanguinite     );
			MT.OREMATS.Malachite          .addOreByProducts(MT.Vyroxeres      );
			MT.MnO2                       .addOreByProducts(MT.Eximite        );
			MT.OREMATS.Cobaltite          .addOreByProducts(MT.Prometheum     );
			MT.OREMATS.Cassiterite        .addOreByProducts(MT.Ignatius       );
			MT.OREMATS.Wolframite         .addOreByProducts(MT.Vulcanite      );
			MT.Ge                         .addOreByProducts(MT.DeepIron       );
			MT.OREMATS.BrownLimonite      .addOreByProducts(MT.DeepIron       );
			MT.OREMATS.YellowLimonite     .addOreByProducts(MT.DeepIron       );
			MT.Fe2O3                      .addOreByProducts(MT.DeepIron       );
			MT.AncientDebris              .addOreByProducts(MT.ShadowIron     );
			MT.Netherite                  .addOreByProducts(MT.ShadowIron     );
			MT.Efrine                     .addOreByProducts(MT.ShadowIron     );
		}
		if (MD.TiC.mLoaded) {
			MT.Pb.addOreByProducts(MT.Ardite);
			MT.Sb.addOreByProducts(MT.Ardite);
			MT.OREMATS.Galena.addOreByProducts(MT.Ardite); // I found Ardaite on Wikipedia, which is a Galena Type of Material.
			MT.OREMATS.Stibnite.addOreByProducts(MT.Ardite);
			MT.Aredrite.addOreByProducts(MT.Ardite);
		}
		if (MD.RP.mLoaded || MD.PR.mLoaded || MD.BP.mLoaded || !MT.Nikolite.mHidden) {
			MT.Azurite.addOreByProducts(MT.Nikolite);
			MT.Monazite.addOreByProducts(MT.Nikolite);
			MT.OREMATS.Bastnasite.addOreByProducts(MT.Nikolite);
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
		if (MD.AA.mLoaded) {
			MT.OREMATS.Barite.addOreByProducts(MT.BlackQuartz);
			MT.MilkyQuartz.addOreByProducts(MT.BlackQuartz);
		}
		if (MD.AE.mLoaded) {
			// Because Applied Energistics would not create its own Items if I also added them, which leads to it getting Issues with its own shit.
			OP.gem .disableItemGeneration(MT.CertusQuartz, MT.Fluix);
			OP.dust.disableItemGeneration(MT.CertusQuartz, MT.Fluix);
		}
	}
	
	@Override
	public void onModInit2(FMLInitializationEvent aEvent) {
		new LoaderWoodDictionary().run();
		
		// Atum violates the "Items have to be created in preInit" Rule...
		if (MD.ATUM.mLoaded) {
			IL.ATUM_Scarab      .set(ST.make(MD.ATUM, "item.scarab" , 1, 0), new OreDictItemData(MT.Au, 4*U, MT.Diamond, U));
			IL.ATUM_Limestone   .set(ST.make(MD.ATUM, "tile.stone"  , 1, 0), OP.stone.dat(MT.STONES.Limestone));
			IL.ATUM_Limecobble  .set(ST.make(MD.ATUM, "tile.cobble" , 1, 0), OP.stone.dat(MT.STONES.Limestone));
			
			OM.reg("cropDate"               , ST.make(MD.ATUM, "item.date", 1, 0));
			OM.reg("cropFlax"               , ST.make(MD.ATUM, "item.flax", 1, 0));
			OM.reg("seedFlax"               , ST.make(MD.ATUM, "item.flaxSeeds", 1, 0));
			OM.reg("itemPelt"               , ST.make(MD.ATUM, "item.wolfPelt", 1, 0));
			OM.reg(OP.dust, MT.Ectoplasm    , ST.make(MD.ATUM, "item.ectoplasm", 1, 0));
			
			OreDictManager.INSTANCE.setTarget(OP.oreLimestone, MT.Fe2O3   , MD.ATUM, "tile.ironOre"    , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreLimestone, MT.Au      , MD.ATUM, "tile.goldOre"    , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreLimestone, MT.Redstone, MD.ATUM, "tile.redstoneOre", 0);
			OreDictManager.INSTANCE.setTarget(OP.oreLimestone, MT.Lapis   , MD.ATUM, "tile.lapisOre"   , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreLimestone, MT.Coal    , MD.ATUM, "tile.coalOre"    , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreLimestone, MT.Diamond , MD.ATUM, "tile.diamondOre" , 0);
			
			OM.data(MD.ATUM, "item.stoneChunk", 1, 0, MT.STONES.Limestone, U);
			OM.data(MD.ATUM, "tile.sand", 1, W, MT.Sand, U*9);
			OM.data(MD.ATUM, "tile.framedGlass", 1, W, MT.Glass, U*9);
			OM.data(MD.ATUM, "tile.crystalGlass", 1, W, MT.Glass, U*9);
			OM.data(MD.ATUM, "tile.framedStainedGlass", 1, W, MT.Glass, U*9);
			OM.data(MD.ATUM, "tile.crystalStainedGlass", 1, W, MT.Glass, U*9);
			OM.data(MD.ATUM, "tile.thinFramedGlass", 1, W, MT.Glass, U);
			OM.data(MD.ATUM, "tile.thinCrystalGlass", 1, W, MT.Glass, U);
			OM.data(MD.ATUM, "tile.thinFramedStainedGlass", 1, W, MT.Glass, U);
			OM.data(MD.ATUM, "tile.thinCrystalStainedGlass", 1, W, MT.Glass, U);
			
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
		
		// Okay I should not have wondered about Blue Power doing the same garbage considering Project Red...
		if (MD.BP.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.ingot    , MT.RedAlloy                 , MD.BP, "red_alloy_ingot", 0);
			OreDictManager.INSTANCE.setTarget(OP.ingot    , MT.BlueAlloy                , MD.BP, "blue_alloy_ingot", 0);
			OreDictManager.INSTANCE.setTarget(OP.ingot    , MT.PurpleAlloy              , MD.BP, "purple_alloy_ingot", 0);
			OreDictManager.INSTANCE.setTarget(OP.dust     , MT.Nikolite                 , MD.BP, "teslatite_dust", 0);
			OreDictManager.INSTANCE.setTarget(OP.blockDust, MT.Nikolite                 , MD.BP, "teslatite_block", 0);
			OreDictManager.INSTANCE.setTarget(OP.dust     , MT.UNUSED.Teslatite         , MD.BP, "infused_teslatite_dust", 0);
			OreDictManager.INSTANCE.setTarget(OP.plate    , MT.Stone                    , MD.BP, "stone_tile", 0);
			
			OM.reg(OD.craftingWorkBench                  , ST.make(MD.BP, "project_table", 1, 0));
			OM.reg(OD.craftingWorkBench                  , ST.make(MD.BP, "auto_project_table", 1, 0));
			OM.reg("seedFlax"                            , ST.make(MD.BP, "flax_seeds", 1, 0));
			OM.reg(DYE_OREDICTS_MIXABLE[DYE_INDEX_Purple], ST.make(MD.BP, "indigo_dye", 1, 0));
			OM.reg("cropIndigo"                          , ST.make(MD.BP, "indigo_flower", 1, 0));
			OM.reg(OP.dustTiny.dat(MT.Zn)                , ST.make(MD.BP, "zinc_tiny_dust", 1, 0));
			OM.reg(OP.crushed.dat(MT.Zn)                 , ST.make(MD.BP, "zinc_ore_crushed", 1, 0));
			OM.reg(OP.crushedPurified.dat(MT.Zn)         , ST.make(MD.BP, "zinc_ore_purified", 1, 0));
			OM.reg(OP.boule.dat(MT.Si)                   , ST.make(MD.BP, "silicon_boule", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Basalt)        , ST.make(MD.BP, "basalt", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Basalt)        , ST.make(MD.BP, "basalt_cobble", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Basalt)        , ST.make(MD.BP, "basalt_brick", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Basalt)        , ST.make(MD.BP, "fancy_basalt", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Basalt)        , ST.make(MD.BP, "basalt_brick_small", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Basalt)        , ST.make(MD.BP, "basaltbrick_cracked", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Basalt)        , ST.make(MD.BP, "basalt_paver", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Basalt)        , ST.make(MD.BP, "basalt_tile", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Marble)        , ST.make(MD.BP, "marble", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Marble)        , ST.make(MD.BP, "marble_brick", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Marble)        , ST.make(MD.BP, "fancy_marble", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Marble)        , ST.make(MD.BP, "marble_brick_small", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Marble)        , ST.make(MD.BP, "marble_paver", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Marble)        , ST.make(MD.BP, "marble_tile", 1, 0));
			
			OM.data(MD.BP, "zincplate", 1, 0, ANY.Fe,   U2, MT.Zn,   U4);
			OM.data(MD.BP, "paint_can", 1, W, ANY.Fe, 7*U2, MT.Zn, 7*U4);
			OM.data(MD.BP, "lumar", 1, W, ANY.Glowstone, U2, MT.Redstone, U2);
			OM.data(MD.BP, "tiles", 1, 0, MT.STONES.Marble, 2*U, MT.STONES.Basalt, 2*U);
			
			ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.BP, "tiles", 1, 0));
			ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.BP, "sapphire_glass", 1, 0));
			ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.BP, "reinforced_sapphire_glass", 1, 0));
			
			StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.BP, "ruby_ore"      , null));
			StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.BP, "sapphire_ore"  , null));
			StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.BP, "amethyst_ore"  , null));
			StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.BP, "silver_ore"    , null));
			StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.BP, "teslatite_ore" , null));
			StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.BP, "tungsten_ore"  , null));
			StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.BP, "zinc_ore"      , null));
			StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.BP, "copper_ore"    , null));
			
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Ruby             , MD.BP, "ruby_ore"     , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.BlueSapphire     , MD.BP, "sapphire_ore" , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Amethyst         , MD.BP, "amethyst_ore" , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Ag               , MD.BP, "silver_ore"   , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Nikolite         , MD.BP, "teslatite_ore", 0);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.OREMATS.Tungstate, MD.BP, "tungsten_ore" , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Zn               , MD.BP, "zinc_ore"     , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Cu               , MD.BP, "copper_ore"   , 0);
		}
		
		// And Project Red violates that Rule aswell...
		if (MD.PR.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.ingot  , MT.RedAlloy                   , MD.PR, "projectred.core.part", 10);
			OreDictManager.INSTANCE.setTarget(OP.ingot  , MT.ElectrotineAlloy           , MD.PR, "projectred.core.part", 55);
			OreDictManager.INSTANCE.setTarget(OP.dust   , MT.Nikolite                   , MD.PR, "projectred.core.part", 56);
			
			OM.reg(OP.stone.dat(MT.STONES.Marble), ST.make(MD.PR_EXPLORATION, "projectred.exploration.stone", 1, 0));
			OM.reg(OP.stone.dat(MT.STONES.Marble), ST.make(MD.PR_EXPLORATION, "projectred.exploration.stone", 1, 1));
			OM.reg(OP.stone.dat(MT.STONES.Basalt), ST.make(MD.PR_EXPLORATION, "projectred.exploration.stone", 1, 2));
			OM.reg(OP.stone.dat(MT.STONES.Basalt), ST.make(MD.PR_EXPLORATION, "projectred.exploration.stone", 1, 3));
			OM.reg(OP.stone.dat(MT.STONES.Basalt), ST.make(MD.PR_EXPLORATION, "projectred.exploration.stone", 1, 4));
			for (int i = 0; i < 16; i++) {
			OM.reg(DYE_OREDICTS_MIXABLE[i], ST.make(MD.PR_EXPLORATION, "projectred.exploration.lilyseed", 1, 15-i));
			OM.data(MD.PR, "projectred.core.part", 1, i+19, ANY.Glowstone, 2*U);
			}
			OM.data(MD.PR, "projectred.core.part", 1, 16, ANY.Cu, U);
			OM.data(MD.PR, "projectred.core.part", 1, 17, ANY.Fe, U);
			OM.data(MD.PR, "projectred.core.part", 1, 18, MT .Au, U);
			
			StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.PR_EXPLORATION, "projectred.exploration.ore", null));
			
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Ruby        , MD.PR_EXPLORATION, "projectred.exploration.ore", 0);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.BlueSapphire, MD.PR_EXPLORATION, "projectred.exploration.ore", 1);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Peridot     , MD.PR_EXPLORATION, "projectred.exploration.ore", 2);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Cu          , MD.PR_EXPLORATION, "projectred.exploration.ore", 3);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Sn          , MD.PR_EXPLORATION, "projectred.exploration.ore", 4);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Ag          , MD.PR_EXPLORATION, "projectred.exploration.ore", 5);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Nikolite    , MD.PR_EXPLORATION, "projectred.exploration.ore", 6);
		}
		
		// Yay for ChickenBones ALWAYS doing it wrong...
		if (MD.WR_CBE_C.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.stick, MT.Obsidian, MD.WR_CBE_C, "obsidianStick", 0);
		}
		if (MD.FMB.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.stick, MT.Stone, MD.FMB, "stoneRod", 0);
		}
		if (MD.TRANSLOCATOR.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.nugget, MT.Diamond, MD.TRANSLOCATOR, "diamondNugget", 0);
		}
		
		// Oh look, Matter Overdrive does this shit too...
		if (MD.MO.mLoaded) {
			StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MO, "dilithium_ore", null));
			StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MO, "tritanium_ore", null));
			
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Dolamide                , MD.MO, "dilithium_ore"    , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.OREMATS.TritaniumDioxide, MD.MO, "tritanium_ore"    , 0);
			OreDictManager.INSTANCE.setTarget(OP.gem            , MT.Dilithium               , MD.MO, "dilithium_crystal", 0);
			OreDictManager.INSTANCE.setTarget(OP.dust           , MT.TritaniumAlloy          , MD.MO, "tritanium_dust"   , 0);
			OreDictManager.INSTANCE.setTarget(OP.nugget         , MT.TritaniumAlloy          , MD.MO, "tritanium_nugget" , 0);
			OreDictManager.INSTANCE.setTarget(OP.ingot          , MT.TritaniumAlloy          , MD.MO, "tritanium_ingot"  , 0);
			OreDictManager.INSTANCE.setTarget(OP.plate          , MT.TritaniumAlloy          , MD.MO, "tritanium_plate"  , 0);
		}
		
		// ThermalExpansion gets on this ShitList too I guess...
		if (MD.TE.mLoaded) {
			ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TE, "Glass", 1, W));
			
			IL.TE_Rockwool   .set(ST.make(MD.TE, "Rockwool", 1, 0));
			IL.TE_ObsidiGlass.set(ST.make(MD.TE, "Glass"   , 1, 0));
			IL.TE_LumiumGlass.set(ST.make(MD.TE, "Glass"   , 1, 1));
			
			OM.data(MD.TE, "Tank", 1, 1, ANY.Cu     ,  U * 1, MT.Glass,  U * 4);
			OM.data(MD.TE, "Tank", 1, 2, MT.Invar   ,  U * 4, ANY.Cu  ,  U * 1, MT.Glass,  U * 4);
			OM.data(MD.TE, "Tank", 1, 3, MT.Invar   ,  U * 4, ANY.Cu  ,  U * 1, MT.Glass,  U * 4);
			OM.data(MD.TE, "Tank", 1, 4, MT.Enderium,  U * 4, MT.Invar,  U * 4, ANY.Cu  ,  U * 1, MT.Glass,  U * 4);
		}
		
		// Wow, Ars Magica too is on this List, at least for its Blocks...
		if (MD.ARS.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Vinteum          , MD.ARS, "vinteumOre", 0);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Chimerite        , MD.ARS, "vinteumOre", 1);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.BlueTopaz        , MD.ARS, "vinteumOre", 2);
			OreDictManager.INSTANCE.setTarget(OP.ore            , MT.Sunstone         , MD.ARS, "vinteumOre", 3);
			OreDictManager.INSTANCE.setTarget(OP.oreMoon        , MT.Moonstone        , MD.ARS, "vinteumOre", 4);
			OreDictManager.INSTANCE.setTarget(OP.blockGem       , MT.Moonstone        , MD.ARS, "vinteumOre", 5);
			OreDictManager.INSTANCE.setTarget(OP.blockDust      , MT.Vinteum          , MD.ARS, "vinteumOre", 6);
			OreDictManager.INSTANCE.setTarget(OP.blockGem       , MT.BlueTopaz        , MD.ARS, "vinteumOre", 7);
			OreDictManager.INSTANCE.setTarget(OP.blockGem       , MT.Sunstone         , MD.ARS, "vinteumOre", 8);
			OreDictManager.INSTANCE.setTarget(OP.blockGem       , MT.Chimerite        , MD.ARS, "vinteumOre", 9);
			OreDictManager.INSTANCE.setTarget(OP.dust           , MT.Vinteum          , MD.ARS, "itemOre", 0);
			OreDictManager.INSTANCE.setTarget(OP.dust           , MT.ArcaneCompound   , MD.ARS, "itemOre", 1);
			OreDictManager.INSTANCE.setTarget(OP.dust           , MT.ArcaneAsh        , MD.ARS, "itemOre", 2);
			OreDictManager.INSTANCE.setTarget(OP.dust           , MT.VinteumPurified  , MD.ARS, "itemOre", 3);
			OreDictManager.INSTANCE.setTarget(OP.gem            , MT.Chimerite        , MD.ARS, "itemOre", 4);
			OreDictManager.INSTANCE.setTarget(OP.gem            , MT.BlueTopaz        , MD.ARS, "itemOre", 5);
			OreDictManager.INSTANCE.setTarget(OP.gem            , MT.Sunstone         , MD.ARS, "itemOre", 6);
			OreDictManager.INSTANCE.setTarget(OP.gem            , MT.Moonstone        , MD.ARS, "itemOre", 7);
			
			IL.ARS_Cerublossom.set(ST.make(MD.ARS, "blueOrchid", 1, 0), null, "flowerCerublossom");
			IL.ARS_DesertNova .set(ST.make(MD.ARS, "desertNova", 1, 0), null, "flowerDesertNova");
			
			new SaplingEntry(ST.make(MD.ARS, "saplingWitchwood", 1, 0), new WoodEntry(ST.make(MD.ARS, "WitchwoodLog", 1, 0), new PlankEntry(ST.make(MD.ARS, "planksWitchwood", 1, 0), ST.make(MD.ARS, "witchwoodSingleSlab", 1, 0), ST.make(MD.ARS, "stairsWitchwood", 1, 0), MT.WOODS.Witchwood, 260), 1, 500), ST.make(MD.ARS, "WitchwoodLeaves", 1, 0));
			CR.shaped(ST.make(MD.ARS, "planksWitchwood", 1, 0), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.ARS, "witchwoodSingleSlab", 1, 0));
		}
		
		// Cooking for Blockheads is here too!...
		if (MD.CookBook.mLoaded) {
			OM.data(MD.CookBook, "recipebook", 1, W, MT.Paper, U*3);
		}
		
		// Grimoire of Gaia... though I did not expect them to have done a good job with that...
		if (MD.GoG.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.chunkGt, MT.Fe, MD.GoG, "item.GrimoireOfGaia.Shard", 0);
			OreDictManager.INSTANCE.setTarget(OP.chunkGt, MT.Au, MD.GoG, "item.GrimoireOfGaia.Shard", 1);
			OM.data(MD.GoG, "item.GrimoireOfGaia.Shard"   , 1, 2, MT.Diamond   , U4);
			OM.data(MD.GoG, "item.GrimoireOfGaia.Shard"   , 1, 3, MT.Emerald   , U4);
			OM.data(MD.GoG, "item.GrimoireOfGaia.Shard"   , 1, 4, MT.NetherStar, U4);
			OM.data(MD.GoG, "item.GrimoireOfGaia.Shard"   , 1, 5, MT.EnderPearl, U4);
			OM.data(MD.GoG, "item.GrimoireOfGaia.Shard"   , 1, 6, MT.Blaze     , U8);
			OM.data(MD.GoG, "item.GrimoireOfGaia.Fragment", 1, 0, MT.Emerald   , U8);
		}
		
		// Seems like it isn't "better" in all aspects.
		if (MD.BETTER_RECORDS.mLoaded) {
			OM.reg(OD.record, ST.make(MD.BETTER_RECORDS, "urlrecord", 1, 0));
			OM.reg(OD.record, ST.make(MD.BETTER_RECORDS, "urlmultirecord", 1, 0));
		}
		
		// Aether Legacy is fucking this up as well, and the best part is, it once WAS working fine, but they fucking decided to break it!
		if (MD.AETHEL.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.oreHolystone   , MT.Zanite                 , MD.AETHEL, "zanite_ore"     , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreHolystone   , MT.Ambrosium              , MD.AETHEL, "ambrosium_ore"  , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreHolystone   , MT.Gravitite              , MD.AETHEL, "gravitite_ore"  , 0);
			OreDictManager.INSTANCE.setTarget(OP.gem            , MT.Zanite                 , MD.AETHEL, "zanite_gemstone", 0);
			OreDictManager.INSTANCE.setTarget(OP.gem            , MT.Ambrosium              , MD.AETHEL, "ambrosium_shard", 0);
			OreDictManager.INSTANCE.setTarget(OP.gem            , MT.AmberGolden            , MD.AETHEL, "golden_amber"   , 0);
			OreDictManager.INSTANCE.setTarget(OP.blockGem       , MT.Zanite                 , MD.AETHEL, "zanite_block"   , 0);
			OreDictManager.INSTANCE.setTarget(OP.stick          , MT.Skyroot                , MD.AETHEL, "skyroot_stick"  , 0);
			OreDictManager.INSTANCE.setTarget(OP.ring           , MT.Fe                     , MD.AETHEL, "iron_ring"      , 0);
			OreDictManager.INSTANCE.setTarget(OP.ring           , MT.Au                     , MD.AETHEL, "golden_ring"    , 0);
			OreDictManager.INSTANCE.setTarget(OP.ring           , MT.Zanite                 , MD.AETHEL, "zanite_ring"    , 0);
			// Just to force the unification Target to be this Ring instead.
			OreDictManager.INSTANCE.setTarget(OP.ring           , MT.Au                     , MD.TC, "ItemBaubleBlanks"  ,  1);
			
			OM.reg("cropBerry"                          , ST.make(MD.AETHEL, "enchanted_blueberry", 1, 0));
			OM.reg("cropBlueberry"                      , ST.make(MD.AETHEL, "blue_berry", 1, 0));
			OM.reg(OD.itemEgg                           , ST.make(MD.AETHEL, "moa_egg", 1, W));
			OM.reg(OD.itemFeather                       , ST.make(MD.AETHEL, "golden_feather", 1, W));
			OM.reg(OD.slimeball                         , ST.make(MD.AETHEL, "swet_ball", 1, 0));
			OM.reg(OD.slimeballSwet                     , ST.make(MD.AETHEL, "swet_ball", 1, 0));
			
			OM.reg(OP.stone, MT.STONES.Holystone        , ST.make(MD.AETHEL, "holystone"      , 1, 0));
			OM.reg(OP.stone, MT.STONES.Holystone        , ST.make(MD.AETHEL, "holystone"      , 1, 1));
			OM.reg(OP.stone, MT.STONES.Holystone        , ST.make(MD.AETHEL, "holystone"      , 1, 2));
			OM.reg(OP.stone, MT.STONES.Holystone        , ST.make(MD.AETHEL, "holystone"      , 1, 3));
			OM.reg(OP.stone, MT.STONES.Holystone        , ST.make(MD.AETHEL, "holystone_brick", 1, 0));
			OM.reg(OP.stone, MT.STONES.Holystone        , ST.make(MD.AETHEL, "mossy_holystone", 1, 0));
			
			BlocksGT.drillableDynamite.add(ST.block(MD.AETHEL, "aether_dirt"           , null));
			BlocksGT.drillableDynamite.add(ST.block(MD.AETHEL, "aether_grass"          , null));
			BlocksGT.drillableDynamite.add(ST.block(MD.AETHEL, "enchanted_aether_grass", null));
			BlocksGT.harvestableSpade.add(ST.block(MD.AETHEL, "aether_dirt"            , null));
			BlocksGT.harvestableSpade.add(ST.block(MD.AETHEL, "aether_grass"           , null));
			BlocksGT.harvestableSpade.add(ST.block(MD.AETHEL, "enchanted_aether_grass" , null));
			BlocksGT.plantableGreens.add(ST.block(MD.AETHEL, "aether_grass"            , null));
			BlocksGT.plantableGreens.add(ST.block(MD.AETHEL, "aether_dirt"             , null));
			BlocksGT.plantableGreens.add(ST.block(MD.AETHEL, "enchanted_aether_grass"  , null));
			BlocksGT.plantableGrass.add(ST.block(MD.AETHEL, "aether_grass"             , null));
			BlocksGT.plantableGrass.add(ST.block(MD.AETHEL, "enchanted_aether_grass"   , null));
			BlocksGT.plantableTrees.add(ST.block(MD.AETHEL, "aether_grass"             , null));
			BlocksGT.plantableTrees.add(ST.block(MD.AETHEL, "enchanted_aether_grass"   , null));
			BlocksGT.breakableGlass.add(ST.block(MD.AETHEL, "quicksoil_glass"          , null));
			
			OM.data(MD.AETHEL, "skyroot_fence"                  , 1,   W, MT.Skyroot            ,  U * 1);
			OM.data(MD.AETHEL, "skyroot_fence_gate"             , 1,   W, MT.Skyroot            ,  U * 4);
			OM.data(MD.AETHEL, "skyroot_bed_item"               , 1,   W, MT.Skyroot            ,  U * 3);
			OM.data(MD.AETHEL, "skyroot_bookshelf"              , 1,   W, MT.Skyroot            ,  U * 6, MT.Paper, U * 9);
			OM.dat2(MD.AETHEL, "skyroot_sword"                  , 1     , MT.Skyroot            ,  OP.toolHeadSword  .mAmount + U2);
			OM.dat2(MD.AETHEL, "skyroot_pickaxe"                , 1     , MT.Skyroot            ,  OP.toolHeadPickaxe.mAmount + U );
			OM.dat2(MD.AETHEL, "skyroot_shovel"                 , 1     , MT.Skyroot            ,  OP.toolHeadShovel .mAmount + U );
			OM.dat2(MD.AETHEL, "skyroot_axe"                    , 1     , MT.Skyroot            ,  OP.toolHeadAxe    .mAmount + U );
			OM.dat2(MD.AETHEL, "holystone_sword"                , 1     , MT.STONES.Holystone   ,  OP.toolHeadSword  .mAmount, MT.Skyroot, U2);
			OM.dat2(MD.AETHEL, "holystone_pickaxe"              , 1     , MT.STONES.Holystone   ,  OP.toolHeadPickaxe.mAmount, MT.Skyroot, U );
			OM.dat2(MD.AETHEL, "holystone_shovel"               , 1     , MT.STONES.Holystone   ,  OP.toolHeadShovel .mAmount, MT.Skyroot, U );
			OM.dat2(MD.AETHEL, "holystone_axe"                  , 1     , MT.STONES.Holystone   ,  OP.toolHeadAxe    .mAmount, MT.Skyroot, U );
			OM.dat2(MD.AETHEL, "zanite_sword"                   , 1     , MT.Zanite             ,  OP.toolHeadSword  .mAmount, MT.Skyroot, U2);
			OM.dat2(MD.AETHEL, "zanite_pickaxe"                 , 1     , MT.Zanite             ,  OP.toolHeadPickaxe.mAmount, MT.Skyroot, U );
			OM.dat2(MD.AETHEL, "zanite_shovel"                  , 1     , MT.Zanite             ,  OP.toolHeadShovel .mAmount, MT.Skyroot, U );
			OM.dat2(MD.AETHEL, "zanite_axe"                     , 1     , MT.Zanite             ,  OP.toolHeadAxe    .mAmount, MT.Skyroot, U );
			OM.dat2(MD.AETHEL, "zanite_helmet"                  , 1     , MT.Zanite             ,  U * 5);
			OM.dat2(MD.AETHEL, "zanite_chestplate"              , 1     , MT.Zanite             ,  U * 8);
			OM.dat2(MD.AETHEL, "zanite_leggings"                , 1     , MT.Zanite             ,  U * 7);
			OM.dat2(MD.AETHEL, "zanite_boots"                   , 1     , MT.Zanite             ,  U * 4);
			
			IL.AETHER_Skyroot_Planks                .set(ST.make(MD.AETHEL, "skyroot_planks"                    , 1, 0), new OreDictItemData(MT.Skyroot, U), OD.plankSkyroot);
			IL.AETHER_Skyroot_Log                   .set(ST.make(MD.AETHEL, "skyroot_log"                       , 1, 0), new OreDictItemData(MT.Skyroot, U*8, MT.Bark, U));
			IL.AETHER_Skyroot_Log_Gold              .set(ST.make(MD.AETHEL, "golden_oak_log"                    , 1, 2), new OreDictItemData(MT.Skyroot, U*8, MT.AmberGolden, U)); OM.reg(IL.AETHER_Skyroot_Log.wild(1), OD.logWood);
			IL.AETHER_Bucket_Empty                  .set(ST.make(MD.AETHEL, "skyroot_bucket"                    , 1, 0), new OreDictItemData(MT.Skyroot, U*3));
			IL.AETHER_Bucket_Water                  .set(ST.make(MD.AETHEL, "skyroot_bucket"                    , 1, 1), new OreDictItemData(MT.Skyroot, U*3), OD.container1000water);
			IL.AETHER_Bucket_Poison                 .set(ST.make(MD.AETHEL, "skyroot_bucket"                    , 1, 2), new OreDictItemData(MT.Skyroot, U*3), OD.container1000poison);
			IL.AETHER_Bucket_Remedy                 .set(ST.make(MD.AETHEL, "skyroot_bucket"                    , 1, 3), new OreDictItemData(MT.Skyroot, U*3));
			IL.AETHER_Bucket_Milk                   .set(ST.make(MD.AETHEL, "skyroot_bucket"                    , 1, 4), new OreDictItemData(MT.Skyroot, U*3), OD.container1000milk);
			IL.AETHER_Torch_Ambrosium               .set(ST.make(MD.AETHEL, "ambrosium_torch"                   , 1, 0), new OreDictItemData(MT.Ambrosium, U8, MT.Skyroot, U16), OD.blockTorch);
			IL.AETHER_Apple                         .set(ST.make(MD.AETHEL, "white_apple"                       , 1, 0), null, "cropAppleWhite");
			IL.AETHER_Sand                          .set(ST.make(MD.AETHEL, "quicksoil"                         , 1, 0), new OreDictItemData(MT.Sand, U*9), OD.sand);
			IL.AETHER_Glass                         .set(ST.make(MD.AETHEL, "quicksoil_glass"                   , 1, 0), null, OD.blockGlassColorless);
			IL.AETHER_Flower_Purple                 .set(ST.make(MD.AETHEL, "purple_flower"                     , 1, 0), null, OD.flower);
			IL.AETHER_Flower_White                  .set(ST.make(MD.AETHEL, "white_flower"                      , 1, 0), null, OD.flower);
			IL.AETHER_Dirt                          .set(ST.make(MD.AETHEL, "aether_dirt"                       , 1, 0));
			IL.AETHER_Grass                         .set(ST.make(MD.AETHEL, "aether_grass"                      , 1, 0));
			IL.AETHER_Grass_Enchanted               .set(ST.make(MD.AETHEL, "enchanted_aether_grass"            , 1, 0));
			IL.AETHER_Skyroot_Sapling_Gold          .set(ST.make(MD.AETHEL, "golden_oak_sapling"                , 1, 0), null, OP.treeSapling);
			IL.AETHER_Skyroot_Sapling_Green         .set(ST.make(MD.AETHEL, "skyroot_sapling"                   , 1, 0), null, OP.treeSapling);
			IL.AETHER_Skyroot_Leaves_Gold           .set(ST.make(MD.AETHEL, "golden_oak_leaves"                 , 1, 0), null, OP.treeLeaves);
			IL.AETHER_Skyroot_Leaves_Green          .set(ST.make(MD.AETHEL, "skyroot_leaves"                    , 1, 0), null, OP.treeLeaves);
			IL.AETHER_Skyroot_Leaves_Blue           .set(ST.make(MD.AETHER, "holiday_leaves"                    , 1, 1), null, OP.treeLeaves);
			IL.AETHER_Skyroot_Leaves_Dark           .set(ST.make(MD.AETHER, "decorated_holiday_leaves"          , 1, 1), null, OP.treeLeaves);
			IL.AETHER_Skyroot_Leaves_Purple         .set(ST.make(MD.AETHEL, "crystal_leaves"                    , 1, 0), null, OP.treeLeaves);
			IL.AETHER_Skyroot_Leaves_Apple          .set(ST.make(MD.AETHEL, "crystal_fruit_leaves"              , 1, 0), null, OP.treeLeaves);
			
			if (IL.AETHER_Dirt.exists()) Textures.BlockIcons.DIRTS[1] = new IconContainerCopied(IL.AETHER_Dirt.block(), 0, SIDE_BOTTOM);
			
			OM.reg(OD.record, ST.make(MD.AETHEL, "ascending_dawn" , 1, 0));
			OM.reg(OD.record, ST.make(MD.AETHEL, "welcoming_skies", 1, 0));
			OM.reg(OD.record, ST.make(MD.AETHEL, "aether_tune"    , 1, 0));
			OM.reg(OD.record, ST.make(MD.AETHEL, "legacy"         , 1, 0));
			
			BeamEntry tSkyrootBeam = new BeamEntry(ST.make(BlocksGT.Beam3, 1, 2), new PlankEntry(IL.AETHER_Skyroot_Planks.get(1), ST.make(MD.AETHEL, "skyroot_slab", 1, 0), ST.make(MD.AETHEL, "skyroot_stairs", 1, 0), MT.Skyroot, 124), 1, 200);
			new BeamEntry(ST.make(BlocksGT.Beam3FireProof, 1, 2), WoodDictionary.PLANKS.get(IL.AETHER_Skyroot_Planks));
			CR.shaped(IL.AETHER_Skyroot_Planks.get(1), CR.DEF_NCC, "S", "S", 'S', ST.make(MD.AETHEL, "skyroot_slab", 1, 0));
			
			//new SaplingEntry(IL.AETHER_Skyroot_Sapling_Blue     .wild(1), tSkyrootWood, IL.AETHER_Skyroot_Leaves_Blue   .wild(1));
			//new SaplingEntry(IL.AETHER_Skyroot_Sapling_Dark     .wild(1), tSkyrootWood, IL.AETHER_Skyroot_Leaves_Dark   .wild(1));
			new SaplingEntry(IL.AETHER_Skyroot_Sapling_Green    .wild(1), new WoodEntry(IL.AETHER_Skyroot_Log     .wild(1), tSkyrootBeam, 1, 300), IL.AETHER_Skyroot_Leaves_Green  .wild(1));
			new SaplingEntry(IL.AETHER_Skyroot_Sapling_Gold     .wild(1), new WoodEntry(IL.AETHER_Skyroot_Log_Gold.wild(1), tSkyrootBeam, 2, 500, OP.gem.mat(MT.AmberGolden, 1), MT.AmberGolden), IL.AETHER_Skyroot_Leaves_Gold   .wild(1));
			//new SaplingEntry(IL.AETHER_Skyroot_Sapling_Purple   .wild(1), tSkyrootWood, IL.AETHER_Skyroot_Leaves_Purple .wild(1));
		}
		
		// Needs to be done here for all Aether Mods, because I don't want duplicate Code.
		FL.reg(FL.Water          .make(1000), IL.AETHER_Bucket_Water .get(1), IL.AETHER_Bucket_Empty.get(1));
		FL.reg(FL.Milk           .make(1000), IL.AETHER_Bucket_Milk  .get(1), IL.AETHER_Bucket_Empty.get(1));
		FL.reg(FL.Potion_Poison_2.make(1000), IL.AETHER_Bucket_Poison.get(1), IL.AETHER_Bucket_Empty.get(1));
		FL.reg(FL.Poison         .make(1000), IL.AETHER_Bucket_Poison.get(1), IL.AETHER_Bucket_Empty.get(1));
		
		// Those "On-Demand" Materials of VoltzEngine are registered late...
		if (MD.VOLTZ.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Sn               , MD.VOLTZ, "veStoneOre", 0);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Cu               , MD.VOLTZ, "veStoneOre", 1);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Ag               , MD.VOLTZ, "veStoneOre", 2);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Pb               , MD.VOLTZ, "veStoneOre", 3);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Zn               , MD.VOLTZ, "veStoneOre", 4);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Ni               , MD.VOLTZ, "veStoneOre", 5);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.OREMATS.Bauxite  , MD.VOLTZ, "veStoneOre", 6);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.MgCO3            , MD.VOLTZ, "veStoneOre", 7);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.OREMATS.Uraninite, MD.VOLTZ, "veStoneOre", 8);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Pt               , MD.VOLTZ, "veStoneOre", 9);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Amazonite        , MD.VOLTZ, "veGemOre"  , 0);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.SmokeyQuartz     , MD.VOLTZ, "veGemOre"  , 1);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.OnyxBlack        , MD.VOLTZ, "veGemOre"  , 2);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.OnyxRed          , MD.VOLTZ, "veGemOre"  , 3);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Uvarovite        , MD.VOLTZ, "veGemOre"  , 4);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Grossular        , MD.VOLTZ, "veGemOre"  , 5);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Almandine        , MD.VOLTZ, "veGemOre"  , 6);
			OreDictManager.INSTANCE.setTarget(OP.oreVanillastone, MT.Andradite        , MD.VOLTZ, "veGemOre"  , 7);
			
			OM.data(MD.VOLTZ, "veSheetMetal", 1,   0, ANY.Steel ,  U);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,   1, ANY.Steel ,  U2);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,   2, ANY.Steel ,  U4);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,   3, ANY.Steel ,  U8);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,   4, ANY.Steel ,  U3);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,   5, ANY.Steel ,  U4);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,   6, ANY.Steel ,  U4);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,   7, ANY.Steel ,  U);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,   8, ANY.Steel ,  U);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,   9, ANY.Steel ,  U);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,  10, ANY.Steel ,  U);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,  11, ANY.Steel ,  U);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,  12, ANY.Steel ,  17*U16);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,  13, ANY.Steel ,  33*U16);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,  14, ANY.Steel ,  67*U16);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,  15, ANY.Steel ,  U16);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,  16, ANY.Steel ,  U8);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,  17, ANY.Steel ,  U4);
			OM.data(MD.VOLTZ, "veSheetMetal", 1,  18, ANY.Steel ,  3*U2);
			
			OM.data(MD.VOLTZ, "veGear"      , 1,   1, ANY.Cu    ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,   2, MT.Sn     ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,   3, MT.Bronze ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,   4, ANY.Fe    ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,   5, ANY.Steel ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,   6, MT.Ag     ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,   7, MT.Au     ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,   8, MT.Pb     ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,   9, MT.Zn     ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,  10, MT.Ni     ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,  11, MT.Al     ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,  12, MT.Mg     ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,  14, MT.Brass  ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,  15, ANY.Stone ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,  16, ANY.Wood  ,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,  17, MT.Diamond,  U);
			OM.data(MD.VOLTZ, "veGear"      , 1,  18, MT.Pt     ,  U);
			
			OM.data(MD.VOLTZ, "veRod"       , 1,   1, ANY.Cu    ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,   2, MT.Sn     ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,   3, MT.Bronze ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,   4, ANY.Fe    ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,   5, ANY.Steel ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,   6, MT.Ag     ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,   7, MT.Au     ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,   8, MT.Pb     ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,   9, MT.Zn     ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,  10, MT.Ni     ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,  11, MT.Al     ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,  12, MT.Mg     ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,  14, MT.Brass  ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,  15, ANY.Stone ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,  16, ANY.Wood  ,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,  17, MT.Diamond,  U2);
			OM.data(MD.VOLTZ, "veRod"       , 1,  18, MT.Pt     ,  U2);
			
			OM.data(MD.VOLTZ, "veWire"      , 1,   1, ANY.Cu    ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,   2, MT.Sn     ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,   3, MT.Bronze ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,   4, ANY.Fe    ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,   5, ANY.Steel ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,   6, MT.Ag     ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,   7, MT.Au     ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,   8, MT.Pb     ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,   9, MT.Zn     ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,  10, MT.Ni     ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,  11, MT.Al     ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,  12, MT.Mg     ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,  14, MT.Brass  ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,  15, ANY.Stone ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,  16, ANY.Wood  ,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,  17, MT.Diamond,  U9);
			OM.data(MD.VOLTZ, "veWire"      , 1,  18, MT.Pt     ,  U9);
		}
		
		// Bugfix just in case that one broken Version of Et Futurum Requiem is used.
		if (MD.EtFu.mLoaded) {
			OreDictManager.INSTANCE.setTarget(OP.ingot, MT.Cu, MD.EtFu, "copper_ingot", 0);
		}
	}
	
	@Override
	public void onModPostInit2(FMLPostInitializationEvent aEvent) {
		if (DISABLE_ALL_IC2_COMPRESSOR_RECIPES  ) ic2.api.recipe.Recipes.compressor.getRecipes().clear();
		if (DISABLE_ALL_IC2_EXTRACTOR_RECIPES   ) ic2.api.recipe.Recipes.extractor .getRecipes().clear();
		if (DISABLE_ALL_IC2_MACERATOR_RECIPES   ) ic2.api.recipe.Recipes.macerator .getRecipes().clear();
		if (DISABLE_ALL_IC2_OREWASHER_RECIPES   ) ic2.api.recipe.Recipes.oreWashing.getRecipes().clear();
		if (DISABLE_ALL_IC2_CENTRIFUGE_RECIPES  ) ic2.api.recipe.Recipes.centrifuge.getRecipes().clear();
		
		// Clearing the AE Grindstone Recipe List.
		if (MD.AE.mLoaded) AEApi.instance().registries().grinder().getRecipes().clear();
		
		// Well Netherite Plus is very special with its Compat Items... This is WAY too late in the loading Cycle! (I am aware that the Ancient Dust got removed in later Versions)
		if (MD.NePl.mLoaded) {
			IL.GC_OxyTank_7.set(ST.make(MD.NePl, "item.oxygenTankNetheriteFull", 1, 0));
			OreDictManager.registerOreSafe(OP.dust      .dat(MT.AncientDebris), ST.make(MD.NePl, "AncientDust"        , 1, 0));
			OreDictManager.registerOreSafe(OP.compressed.dat(MT.Netherite    ), ST.make(MD.NePl, "CompressedNetherite", 1, 0));
			OreDictManager.INSTANCE.setTarget(OP.dust      , MT.AncientDebris , ST.make(MD.NePl, "AncientDust"        , 1, 0), T, T, T);
			OreDictManager.INSTANCE.setTarget(OP.compressed, MT.Netherite     , ST.make(MD.NePl, "CompressedNetherite", 1, 0), T, T, T);
		}
		
		if (MD.EtFu.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.EtFu, "rose"              ));
			BlocksGT.FLOWERS.add(ST.block(MD.EtFu, "cornflower"        ));
			BlocksGT.FLOWERS.add(ST.block(MD.EtFu, "lily_of_the_valley"));
		}
		if (MD.BoP.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.BoP , "flowers"           ));
			BlocksGT.FLOWERS.add(ST.block(MD.BoP , "flowers2"          ));
		}
		if (MD.EBXL.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.EBXL, "flower1"           ));
			BlocksGT.FLOWERS.add(ST.block(MD.EBXL, "flower2"           ));
			BlocksGT.FLOWERS.add(ST.block(MD.EBXL, "flower3"           ));
		}
		if (MD.TCFM.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.TCFM, "InkFlower"         ));
			BlocksGT.FLOWERS.add(ST.block(MD.TCFM, "UmbralBush"        ));
		}
		if (MD.BP.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.BP  , "indigo_flower"     ));
		}
		if (MD.ARS.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.ARS , "blueOrchid"        ));
		}
		if (MD.AETHER.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.AETHER , "purpleFlower"   ));
			BlocksGT.FLOWERS.add(ST.block(MD.AETHER , "whiteRose"      ));
		}
		if (MD.AETHEL.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.AETHEL , "purple_flower"  ));
			BlocksGT.FLOWERS.add(ST.block(MD.AETHEL , "white_flower"   ));
		}
		if (MD.BOTA.mLoaded) {
			BlocksGT.FLOWERS.add(ST.block(MD.BOTA, "flower"            ));
			BlocksGT.FLOWERS.add(ST.block(MD.BOTA, "shinyFlower"       ));
		}
		
		for (Enchantment tEnchant : Enchantment.enchantmentsList) if (tEnchant != null) {
			if ("enchantment.Magnetization".equalsIgnoreCase(tEnchant.getName())) {
				for (OreDictMaterial tMaterial : MT.ALL_MATERIALS_REGISTERED_HERE) {
					if (tMaterial == MT.NeodymiumMagnetic) {
						tMaterial.addEnchantmentForTools(tEnchant, 3).addEnchantmentForWeapons(tEnchant, 3).addEnchantmentForArmors(tEnchant, 3);
					} else if (tMaterial == MT.SteelMagnetic || tMaterial == MT.MeteoricSteel || tMaterial == MT.MeteoricBlackSteel || tMaterial == MT.MeteoricBlueSteel || tMaterial == MT.MeteoricRedSteel || tMaterial == MT.MeteoflameSteel || tMaterial == MT.MeteoflameBlackSteel || tMaterial == MT.MeteoflameBlueSteel || tMaterial == MT.MeteoflameRedSteel || tMaterial == MT.Meteorite) {
						tMaterial.addEnchantmentForTools(tEnchant, 2).addEnchantmentForWeapons(tEnchant, 2).addEnchantmentForArmors(tEnchant, 2);
					} else if (tMaterial.containsAny(TD.Properties.MAGNETIC_ACTIVE, TD.Properties.AUTO_COLLECTING)) {
						tMaterial.addEnchantmentForTools(tEnchant, 1).addEnchantmentForWeapons(tEnchant, 1).addEnchantmentForArmors(tEnchant, 1);
					}
				}
			}
			if ("enchantment.swift_sneak".equalsIgnoreCase(tEnchant.getName())) {
				if (MD.TF.mLoaded) {
					((TFTreasureTable)UT.Reflection.getFieldContent(TFTreasure.tower_library  , "ultrarare")).addEnchantedBook(tEnchant, 2);
					((TFTreasureTable)UT.Reflection.getFieldContent(TFTreasure.labyrinth_vault, "rare"     )).addEnchantedBook(tEnchant, 2);
					((TFTreasureTable)UT.Reflection.getFieldContent(TFTreasure.stronghold_room, "rare"     )).addEnchantedBook(tEnchant, 1);
					((TFTreasureTable)UT.Reflection.getFieldContent(TFTreasure.darktower_cache, "rare"     )).addEnchantedBook(tEnchant, 1);
					((TFTreasureTable)UT.Reflection.getFieldContent(TFTreasure.aurora_cache   , "rare"     )).addEnchantedBook(tEnchant, 1);
					((TFTreasureTable)UT.Reflection.getFieldContent(TFTreasure.aurora_room    , "uncommon" )).addEnchantedBook(tEnchant, 1);
				}
			}
			if ("enchantment.mending".equalsIgnoreCase(tEnchant.getName())) {
				if (MD.TF.mLoaded) {
					((TFTreasureTable)UT.Reflection.getFieldContent(TFTreasure.tower_library  , "ultrarare")).addEnchantedBook(tEnchant, 1);
					((TFTreasureTable)UT.Reflection.getFieldContent(TFTreasure.labyrinth_vault, "rare"     )).addEnchantedBook(tEnchant, 1);
					((TFTreasureTable)UT.Reflection.getFieldContent(TFTreasure.darktower_key  , "rare"     )).addEnchantedBook(tEnchant, 1);
					((TFTreasureTable)UT.Reflection.getFieldContent(TFTreasure.aurora_room    , "rare"     )).addEnchantedBook(tEnchant, 1);
					((TFTreasureTable)UT.Reflection.getFieldContent(TFTreasure.troll_vault    , "uncommon" )).addEnchantedBook(tEnchant, 1);
				}
			}
			if ("enchantment.Cold Touch".equalsIgnoreCase(tEnchant.getName())) {
				MT.Ice                  .addEnchantmentForDamage(tEnchant, 1);
				MT.Snow                 .addEnchantmentForDamage(tEnchant, 1);
				MT.FrozenIron           .addEnchantmentForDamage(tEnchant, 2);
				MT.Blizz                .addEnchantmentForDamage(tEnchant, 3);
				MT.Frezarite            .addEnchantmentForDamage(tEnchant, 4);
				MT.InfusedWater         .addEnchantmentForDamage(tEnchant, 4);
				MT.Cryotheum            .addEnchantmentForDamage(tEnchant, 5);
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
				for (OreDictMaterial tMat : ANY.Emerald   .mToThis) tMat.addEnchantmentForWeapons(tEnchant, 5).addEnchantmentForAmmo(tEnchant, 7);
				for (OreDictMaterial tMat : ANY.Sapphire  .mToThis) tMat.addEnchantmentForWeapons(tEnchant, 3).addEnchantmentForAmmo(tEnchant, 5);
				for (OreDictMaterial tMat : ANY.Garnet    .mToThis) tMat.addEnchantmentForWeapons(tEnchant, 2).addEnchantmentForAmmo(tEnchant, 4);
				for (OreDictMaterial tMat : ANY.Jasper    .mToThis) tMat.addEnchantmentForWeapons(tEnchant, 2).addEnchantmentForAmmo(tEnchant, 4);
				for (OreDictMaterial tMat : ANY.TigerEye  .mToThis) tMat.addEnchantmentForWeapons(tEnchant, 4).addEnchantmentForAmmo(tEnchant, 6);
				for (OreDictMaterial tMat : ANY.Aventurine.mToThis) tMat.addEnchantmentForWeapons(tEnchant, 3).addEnchantmentForAmmo(tEnchant, 5);
				for (OreDictMaterial tMat : ANY.Phosphorus.mToThis) tMat.addEnchantmentForWeapons(tEnchant, 5).addEnchantmentForAmmo(tEnchant, 7);
				MT.PO4                  .addEnchantmentForWeapons(tEnchant, 5).addEnchantmentForAmmo(tEnchant, 7);
				MT.Phosphorite          .addEnchantmentForWeapons(tEnchant, 5).addEnchantmentForAmmo(tEnchant, 7);
				MT.Spinel               .addEnchantmentForWeapons(tEnchant, 3).addEnchantmentForAmmo(tEnchant, 5);
				MT.BalasRuby            .addEnchantmentForWeapons(tEnchant, 3).addEnchantmentForAmmo(tEnchant, 5);
				MT.OnyxBlack            .addEnchantmentForWeapons(tEnchant, 3).addEnchantmentForAmmo(tEnchant, 5);
				MT.OnyxRed              .addEnchantmentForWeapons(tEnchant, 3).addEnchantmentForAmmo(tEnchant, 5);
				MT.Topaz                .addEnchantmentForWeapons(tEnchant, 5).addEnchantmentForAmmo(tEnchant, 7);
				MT.BlueTopaz            .addEnchantmentForWeapons(tEnchant, 5).addEnchantmentForAmmo(tEnchant, 7);
				MT.Zanite               .addEnchantmentForWeapons(tEnchant, 4).addEnchantmentForAmmo(tEnchant, 6);
				MT.Tanzanite            .addEnchantmentForWeapons(tEnchant, 4).addEnchantmentForAmmo(tEnchant, 6);
				MT.Amazonite            .addEnchantmentForWeapons(tEnchant, 5).addEnchantmentForAmmo(tEnchant, 7);
				MT.Alexandrite          .addEnchantmentForWeapons(tEnchant, 5).addEnchantmentForAmmo(tEnchant, 7);
				MT.Opal                 .addEnchantmentForWeapons(tEnchant, 4).addEnchantmentForAmmo(tEnchant, 6);
				MT.Peridot              .addEnchantmentForWeapons(tEnchant, 2).addEnchantmentForAmmo(tEnchant, 4);
				MT.Dioptase             .addEnchantmentForWeapons(tEnchant, 3).addEnchantmentForAmmo(tEnchant, 5);
				MT.Jade                 .addEnchantmentForWeapons(tEnchant, 3).addEnchantmentForAmmo(tEnchant, 5);
				MT.Craponite            .addEnchantmentForWeapons(tEnchant, 1).addEnchantmentForAmmo(tEnchant, 3);
				MT.Amethyst             .addEnchantmentForWeapons(tEnchant, 3).addEnchantmentForAmmo(tEnchant, 5);
				MT.EnderAmethyst        .addEnchantmentForWeapons(tEnchant, 5).addEnchantmentForAmmo(tEnchant, 7);
			}
		}
		
		new LoaderBookList().run();
		
		for (OreDictMaterial tMaterial : OreDictMaterial.MATERIAL_MAP.values()) {
			// Diatomic Elements get a Subscript 2 appended to their ToolTip after PostInit. That way the ToolTip Calculation works properly until PostInit happens.
			if (tMaterial.contains(TD.Atomic.DIATOMIC_NONMETAL)) tMaterial.mTooltipChemical += NUM_SUB[2];
			// Simple automatically added Alloying Recipes.
			if (tMaterial.contains(TD.Processing.CRUCIBLE_ALLOY)) if (tMaterial.mComponents != null) tMaterial.addAlloyingRecipe(tMaterial.mComponents); else ERR.println("ERROR: Alloying Recipe for " + tMaterial.mNameLocal + " cannot be added due to lack of Component Information");
		}
		
		// Polyatomic Elements get a Subscript 4 or 8 appended to their ToolTip after PostInit. That way the ToolTip Calculation works properly until PostInit happens.
		MT.P .mTooltipChemical += NUM_SUB[4];
		MT.S .mTooltipChemical += NUM_SUB[8];
		MT.Se.mTooltipChemical += NUM_SUB[8];
		
		// Adding all the things that belong to the "ore" Prefix to its registered List.
		for (OreDictPrefix tPrefix : OreDictPrefix.VALUES) if (tPrefix.containsAny(TD.Prefix.STANDARD_ORE, TD.Prefix.DUST_ORE, TD.Prefix.DENSE_ORE)) OP.ore.mRegisteredItems.addAll(tPrefix.mRegisteredItems);
		
		// And now to stop CoFH-Core from crashing and lagging the Game in Singleplayer with the FMLProxyPacket race condition Bug.
		// I hate having to do this, but there is no other proper way to actually fix this Issue...
		// It would be so much easier to just say to not use this Mod, but ofcourse a lot of Stuff depends on it "existing" for no good reasons.
		// This Bug has caused many people way too much wasted time to just leave it alone. So Baseball Bat instead of Surgical Precision it is!
		
		// Replaced by ASM Stuff.
		//if (CODE_CLIENT && MD.COFH_CORE.mLoaded && ConfigsGT.CLIENT.get(ConfigCategories.general, "BrutallyFixCoFHCoreInSinglePlayer", T)) try {
		//  MinecraftForge.EVENT_BUS.unregister(cofh.CoFHCore.instance);
		//  FMLCommonHandler.instance().bus().unregister(cofh.core.util.FMLEventHandler.instance);
		//} catch (Throwable e) {
		//  e.printStackTrace(ERR);
		//}
	}
	
	@Override
	public void onModServerStarting2(FMLServerStartingEvent aEvent) {
		if (DISABLE_ALL_IC2_COMPRESSOR_RECIPES) ic2.api.recipe.Recipes.compressor.getRecipes().clear();
		if (DISABLE_ALL_IC2_EXTRACTOR_RECIPES ) ic2.api.recipe.Recipes.extractor .getRecipes().clear();
		if (DISABLE_ALL_IC2_MACERATOR_RECIPES ) ic2.api.recipe.Recipes.macerator .getRecipes().clear();
		if (DISABLE_ALL_IC2_OREWASHER_RECIPES ) ic2.api.recipe.Recipes.oreWashing.getRecipes().clear();
		if (DISABLE_ALL_IC2_CENTRIFUGE_RECIPES) ic2.api.recipe.Recipes.centrifuge.getRecipes().clear();
	}

	@Override public void onModServerStarted2(FMLServerStartedEvent aEvent) {/**/}
	@Override public void onModServerStopping2(FMLServerStoppingEvent aEvent) {/**/}
	@Override public void onModServerStopped2(FMLServerStoppedEvent aEvent) {/**/}
}
