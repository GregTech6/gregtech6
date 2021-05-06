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

package gregapi;

import static gregapi.data.CS.*;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLModIdMappingEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import enviromine.EnviroPotion;
import gregapi.api.Abstract_Mod;
import gregapi.api.Abstract_Proxy;
import gregapi.block.ToolCompat;
import gregapi.block.prefixblock.PrefixBlockFallingEntity;
import gregapi.block.prefixblock.PrefixBlockTileEntity;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.compat.ICompat;
import gregapi.compat.buildcraft.ICompatBC;
import gregapi.compat.computercraft.ICompatCC;
import gregapi.compat.forestry.ICompatFR;
import gregapi.compat.galacticraft.ICompatGC;
import gregapi.compat.industrialcraft.ICompatIC2;
import gregapi.compat.industrialcraft.ICompatIC2EUItem;
import gregapi.compat.thaumcraft.ICompatTC;
import gregapi.config.Config;
import gregapi.config.ConfigCategories;
import gregapi.cover.CoverRegistry;
import gregapi.cover.ICover;
import gregapi.cover.covers.CoverRedstoneRepeater;
import gregapi.cover.covers.CoverRedstoneTorch;
import gregapi.data.BI;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.CS.DirectoriesGT;
import gregapi.data.CS.IconsGT;
import gregapi.data.CS.ModIDs;
import gregapi.data.CS.PotionsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.dummies.DummyWorld;
import gregapi.enchants.Enchantment_EnderDamage;
import gregapi.enchants.Enchantment_Radioactivity;
import gregapi.enchants.Enchantment_SlimeDamage;
import gregapi.enchants.Enchantment_WerewolfDamage;
import gregapi.item.ItemEmptySlot;
import gregapi.item.ItemFluidDisplay;
import gregapi.item.ItemIntegratedCircuit;
import gregapi.lang.LanguageHandler;
import gregapi.load.LoaderOreDictReRegistrations;
import gregapi.log.LogBuffer;
import gregapi.log.LoggerPlayerActivity;
import gregapi.network.NetworkHandler;
import gregapi.network.packets.PacketBlockError;
import gregapi.network.packets.PacketBlockEvent;
import gregapi.network.packets.PacketConfig;
import gregapi.network.packets.PacketDeathPoint;
import gregapi.network.packets.PacketItemStackChat;
import gregapi.network.packets.PacketPrefix;
import gregapi.network.packets.PacketSound;
import gregapi.network.packets.covers.PacketSyncDataByteAndIDsAndCovers;
import gregapi.network.packets.covers.PacketSyncDataByteArrayAndIDsAndCovers;
import gregapi.network.packets.covers.PacketSyncDataIDsAndCovers;
import gregapi.network.packets.covers.PacketSyncDataIntegerAndIDsAndCovers;
import gregapi.network.packets.covers.PacketSyncDataLongAndIDsAndCovers;
import gregapi.network.packets.covers.PacketSyncDataShortAndIDsAndCovers;
import gregapi.network.packets.covervisuals.PacketSyncDataByteAndCoverVisuals;
import gregapi.network.packets.covervisuals.PacketSyncDataByteArrayAndCoverVisuals;
import gregapi.network.packets.covervisuals.PacketSyncDataCoverVisuals;
import gregapi.network.packets.covervisuals.PacketSyncDataIntegerAndCoverVisuals;
import gregapi.network.packets.covervisuals.PacketSyncDataLongAndCoverVisuals;
import gregapi.network.packets.covervisuals.PacketSyncDataShortAndCoverVisuals;
import gregapi.network.packets.data.PacketSyncDataByte;
import gregapi.network.packets.data.PacketSyncDataByteArray;
import gregapi.network.packets.data.PacketSyncDataInteger;
import gregapi.network.packets.data.PacketSyncDataLong;
import gregapi.network.packets.data.PacketSyncDataName;
import gregapi.network.packets.data.PacketSyncDataShort;
import gregapi.network.packets.ids.PacketSyncDataByteAndIDs;
import gregapi.network.packets.ids.PacketSyncDataByteArrayAndIDs;
import gregapi.network.packets.ids.PacketSyncDataIDs;
import gregapi.network.packets.ids.PacketSyncDataIntegerAndIDs;
import gregapi.network.packets.ids.PacketSyncDataLongAndIDs;
import gregapi.network.packets.ids.PacketSyncDataShortAndIDs;
import gregapi.old.Textures;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.recipes.AdvancedCrafting1ToY;
import gregapi.recipes.AdvancedCraftingShaped;
import gregapi.recipes.AdvancedCraftingShapeless;
import gregapi.recipes.AdvancedCraftingTool;
import gregapi.recipes.AdvancedCraftingXToY;
import gregapi.render.IRenderedBlockObject.ErrorRenderer;
import gregapi.render.ITexture;
import gregapi.render.TextureSet;
import gregapi.tileentity.energy.EnergyCompat;
import gregapi.util.CR;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.worldgen.GT6WorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.RecipeSorter;
import thaumcraft.api.ThaumcraftApi;

/**
 * @author Gregorius Techneticies
 * 
 * This loads before compatible Mods, except Micdoodlecore. GT_API_Post loads after all compatible Mods.
 */
@Mod(modid=ModIDs.GAPI, name="Greg-API", version="GT6-MC1710", dependencies="required-before:"+ModIDs.GAPI_POST+"; after:"+ModIDs.MD8+"; before:"+ModIDs.IC2+"; before:"+ModIDs.IC2C+"; before:"+ModIDs.NC+"; before:"+ModIDs.IHL+"; before:"+ModIDs.FUNK+"; before:"+ModIDs.BAUBLES+"; before:"+ModIDs.HEE+"; before:"+ModIDs.GaSu+"; before:"+ModIDs.GaNe+"; before:"+ModIDs.GaEn+"; before:"+ModIDs.WdSt+"; before:"+ModIDs.CrGu+"; before:"+ModIDs.COFH_API+"; before:"+ModIDs.COFH_API_ENERGY+"; before:"+ModIDs.COFH_CORE+"; before:"+ModIDs.CC+"; before:"+ModIDs.OC+"; before:"+ModIDs.HEX+"; before:"+ModIDs.DE+"; before:"+ModIDs.AV+"; before:"+ModIDs.FR+"; before:"+ModIDs.FRMB+"; before:"+ModIDs.BINNIE+"; before:"+ModIDs.BINNIE_BEE+"; before:"+ModIDs.BINNIE_TREE+"; before:"+ModIDs.BINNIE_GENETICS+"; before:"+ModIDs.BINNIE_BOTANY+"; before:"+ModIDs.IE+"; before:"+ModIDs.UB+"; before:"+ModIDs.COG+"; before:"+ModIDs.PFAA+"; before:"+ModIDs.MIN+"; before:"+ModIDs.RH+"; before:"+ModIDs.CANDY+"; before:"+ModIDs.ABYSSAL+"; before:"+ModIDs.SOULFOREST+"; before:"+ModIDs.ARS+"; before:"+ModIDs.TC+"; before:"+ModIDs.TCFM+"; before:"+ModIDs.BOTA+"; before:"+ModIDs.ALF+"; before:"+ModIDs.WTCH+"; before:"+ModIDs.HOWL+"; before:"+ModIDs.MoCr+"; before:"+ModIDs.GoG+"; before:"+ModIDs.LycM+"; before:"+ModIDs.LycM_Arctic+"; before:"+ModIDs.LycM_Demon+"; before:"+ModIDs.LycM_Desert+"; before:"+ModIDs.LycM_Forest+"; before:"+ModIDs.LycM_Fresh+"; before:"+ModIDs.LycM_Inferno+"; before:"+ModIDs.LycM_Jungle+"; before:"+ModIDs.LycM_Mountain+"; before:"+ModIDs.LycM_Plains+"; before:"+ModIDs.LycM_Salt+"; before:"+ModIDs.LycM_Shadow+"; before:"+ModIDs.LycM_Swamp+"; before:"+ModIDs.RC+"; before:"+ModIDs.BP+"; before:"+ModIDs.PR+"; before:"+ModIDs.PR_EXPANSION+"; before:"+ModIDs.PR_INTEGRATION+"; before:"+ModIDs.PR_TRANSMISSION+"; before:"+ModIDs.PR_TRANSPORT+"; before:"+ModIDs.PR_EXPLORATION+"; before:"+ModIDs.PR_COMPATIBILITY+"; before:"+ModIDs.PR_FABRICATION+"; before:"+ModIDs.PR_ILLUMINATION+"; before:"+ModIDs.PE+"; before:"+ModIDs.AE+"; before:"+ModIDs.MO+"; before:"+ModIDs.TE_FOUNDATION+"; before:"+ModIDs.TE_DYNAMICS+"; before:"+ModIDs.TE+"; before:"+ModIDs.ZTONES+"; before:"+ModIDs.CHSL+"; before:"+ModIDs.NePl+"; before:"+ModIDs.NeLi+"; before:"+ModIDs.EtFu+"; before:"+ModIDs.BB+"; before:"+ModIDs.DYNAMIC_TREES+"; before:"+ModIDs.BbLC+"; before:"+ModIDs.CARP+"; before:"+ModIDs.BETTER_RECORDS+"; before:"+ModIDs.TF+"; before:"+ModIDs.ERE+"; before:"+ModIDs.MFR+"; before:"+ModIDs.PnC+"; before:"+ModIDs.ExU+"; before:"+ModIDs.ExS+"; before:"+ModIDs.EIO+"; before:"+ModIDs.RT+"; before:"+ModIDs.AA+"; before:"+ModIDs.TreeCap+"; before:"+ModIDs.HaC+"; before:"+ModIDs.CookBook+"; before:"+ModIDs.APC+"; before:"+ModIDs.ENVM+"; before:"+ModIDs.MaCr+"; before:"+ModIDs.BC_TRANSPORT+"; before:"+ModIDs.BC_SILICON+"; before:"+ModIDs.BC_FACTORY+"; before:"+ModIDs.BC_ENERGY+"; before:"+ModIDs.BC_ROBOTICS+"; before:"+ModIDs.BC+"; before:"+ModIDs.BC_BUILDERS+"; before:"+ModIDs.MgC+"; before:"+ModIDs.BR+"; before:"+ModIDs.HBM+"; before:"+ModIDs.ELN+"; before:"+ModIDs.DRGN+"; before:"+ModIDs.ElC+"; before:"+ModIDs.CrC+"; before:"+ModIDs.ReC+"; before:"+ModIDs.RoC+"; before:"+ModIDs.Mek+"; before:"+ModIDs.Mek_Tools+"; before:"+ModIDs.Mek_Generators+"; before:"+ModIDs.GC+"; before:"+ModIDs.GC_PLANETS+"; before:"+ModIDs.GC_GALAXYSPACE+"; before:"+ModIDs.VULPES+"; before:"+ModIDs.GC_ADV_ROCKETRY+"; before:"+ModIDs.BTL+"; before:"+ModIDs.AETHER+"; before:"+ModIDs.TROPIC+"; before:"+ModIDs.ATUM+"; before:"+ModIDs.EB+"; before:"+ModIDs.EBXL+"; before:"+ModIDs.BoP+"; before:"+ModIDs.HiL+"; before:"+ModIDs.ATG+"; before:"+ModIDs.RTG+"; before:"+ModIDs.RWG+"; before:"+ModIDs.CW2+"; before:"+ModIDs.A97_MINING+"; before:"+ModIDs.MYST+"; before:"+ModIDs.WARPBOOK+"; before:"+ModIDs.LOSTBOOKS+"; before:"+ModIDs.LOOTBAGS+"; before:"+ModIDs.EUREKA+"; before:"+ModIDs.ENCHIRIDION+"; before:"+ModIDs.ENCHIRIDION2+"; before:"+ModIDs.SmAc+"; before:"+ModIDs.HQM+"; before:"+ModIDs.SD+"; before:"+ModIDs.BTRS+"; before:"+ModIDs.JABBA+"; before:"+ModIDs.MaCu+"; before:"+ModIDs.PdC+"; before:"+ModIDs.Bamboo+"; before:"+ModIDs.PMP+"; before:"+ModIDs.Fossil+"; before:"+ModIDs.GrC+"; before:"+ModIDs.GrC_Apples+"; before:"+ModIDs.GrC_Bamboo+"; before:"+ModIDs.GrC_Bees+"; before:"+ModIDs.GrC_Cellar+"; before:"+ModIDs.GrC_Fish+"; before:"+ModIDs.GrC_Grapes+"; before:"+ModIDs.GrC_Hops+"; before:"+ModIDs.GrC_Milk+"; before:"+ModIDs.GrC_Rice+"; before:"+ModIDs.BG2+"; before:"+ModIDs.BWM+"; before:"+ModIDs.OMT+"; before:"+ModIDs.TG+"; before:"+ModIDs.FM+"; before:"+ModIDs.FZ+"; before:"+ModIDs.MNTL+"; before:"+ModIDs.OB+"; before:"+ModIDs.PA+"; before:"+ModIDs.TiC+"; before:"+ModIDs.MF2+"; before:"+ModIDs.WR_CBE_C+"; before:"+ModIDs.WR_CBE_A+"; before:"+ModIDs.WR_CBE_L+"; before:"+ModIDs.VOLTZ+"; before:"+ModIDs.MFFS+"; before:"+ModIDs.ICBM+"; before:"+ModIDs.ATSCI+"; before:inventorytweaks; before:ironbackpacks; before:journeymap; before:LogisticsPipes; before:LunatriusCore; before:NEIAddons; before:NEIAddons|Developer; before:NEIAddons|AppEng; before:NEIAddons|Botany; before:NEIAddons|Forestry; before:NEIAddons|CraftingTables; before:NEIAddons|ExNihilo; before:neiintegration; before:openglasses; before:simplyjetpacks; before:Stackie; before:StevesCarts; before:TiCTooltips; before:worldedit; before:McMultipart; before:OpenPeripheralCore; before:OpenPeripheralIntegration; before:OpenPeripheral; ")
public class GT_API extends Abstract_Mod {
	@SidedProxy(modId = ModIDs.GAPI, clientSide = "gregapi.GT_API_Proxy_Client", serverSide = "gregapi.GT_API_Proxy_Server")
	public static GT_API_Proxy api_proxy;
	
	public static final Collection<Map<ItemStackContainer, ?>> STACKMAPS = new ArrayListNoNulls<>();
	
	/** Used to register Icons. It is not necessary to make those into Lists */
	public static Set<Runnable> sBlockIconload = new HashSetNoNulls<>(), sItemIconload = new HashSetNoNulls<>();
	/** The Icon Registers from Blocks and Items. They will get set right before the corresponding Icon Load Phase as executed in the Runnable List above. */
	@SideOnly(Side.CLIENT)
	public static IIconRegister sBlockIcons, sItemIcons;
	
	private LoggerPlayerActivity mPlayerLogger;
	
	@SuppressWarnings("unchecked")
	public GT_API() {
		GAPI = this;
		
		if (!MD.ENCHIRIDION.mLoaded) MD.MaCu.mLoaded = F;
		
		// A bunch of Code that is there to statically initialize the Database in the right order and without crashes.
		MT.init();
		BI.BAROMETER.toString();
		OP.ore.toString();
		
		Textures.BlockIcons.VOID.toString();
		Textures.ItemIcons .VOID.toString();
		ErrorRenderer.INSTANCE.toString();
		// Guess what, I once got a random Crash from that one not being classloaded...
		UT.Entities.class.toString();
		
		try {
			DW = new DummyWorld();
		} catch(Throwable e) {
			ERR.println("======================================================================================================");
			ERR.println("WARNING, DUMMY WORLD COULD NOT BE CREATED, SOME RECIPE RELATED THINGS MAY NOT FUNCTION PROPERLY NOW!!!");
			ERR.println("======================================================================================================");
			e.printStackTrace(ERR);
			ERR.println("======================================================================================================");
		}
		
		IconsGT.INDEX_BLOCK_GAS       = TextureSet.addToAll(MD.GT.mID, F, "gas");
		IconsGT.INDEX_BLOCK_PLASMA    = TextureSet.addToAll(MD.GT.mID, F, "plasma");
		IconsGT.INDEX_BLOCK_MOLTEN    = TextureSet.addToAll(MD.GT.mID, F, "molten");
		IconsGT.INDEX_BLOCK_PIPE_SIDE = TextureSet.addToAll(MD.GT.mID, F, "pipeSide");
		
		OP.ore              .addTextureSet(MD.GT, F);
		OP.oreGravel        .addTextureSet(MD.GT, F);
		OP.oreDense         .addTextureSet(MD.GT, F);
		OP.oreBedrock       .addTextureSet(MD.GT, F);
		
		OP.pipeTiny         .addTextureSet(MD.GT, F);
		OP.pipeSmall        .addTextureSet(MD.GT, F);
		OP.pipeMedium       .addTextureSet(MD.GT, F);
		OP.pipeLarge        .addTextureSet(MD.GT, F);
		OP.pipeHuge         .addTextureSet(MD.GT, F);
		OP.pipeQuadruple    .addTextureSet(MD.GT, F);
		OP.pipeNonuple      .addTextureSet(MD.GT, F);
		
		OP.wire             .addTextureSet(MD.GT, F);
		OP.foil             .addTextureSet(MD.GT, F);
		
		// It is VERY important that those are registered first. Otherwise GregTech would output its own Storage Blocks.
		OreDictManager.INSTANCE.setTarget_(OP.blockSolid, MT.Obsidian, ST.make(Blocks.obsidian      , 1, 0), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.blockIngot, MT.Fe      , ST.make(Blocks.iron_block    , 1, 0), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.blockIngot, MT.Au      , ST.make(Blocks.gold_block    , 1, 0), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.blockGem  , MT.Diamond , ST.make(Blocks.diamond_block , 1, 0), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.blockGem  , MT.Emerald , ST.make(Blocks.emerald_block , 1, 0), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.blockGem  , MT.Lapis   , ST.make(Blocks.lapis_block   , 1, 0), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.blockGem  , MT.Coal    , ST.make(Blocks.coal_block    , 1, 0), T, F, T);
		OreDictManager.INSTANCE.setTarget_(OP.blockDust , MT.Redstone, ST.make(Blocks.redstone_block, 1, 0), T, F, T);
		
		// Fixing missing Container Items.
		Items.mushroom_stew.setContainerItem(Items.bowl);
		Items.potionitem.setContainerItem(Items.glass_bottle);
		Items.experience_bottle.setContainerItem(Items.glass_bottle);
		
		// Fixing Max Stacksizes that don't make sense.
		Items.cake.setMaxStackSize(64);
		Items.wooden_door.setMaxStackSize(8);
		Items.iron_door.setMaxStackSize(8);
		
		// Fixing some Adventure Mode things.
		Blocks.bed.setHarvestLevel("axe", 0);
		Blocks.sponge.setHarvestLevel("axe", 0);
		Blocks.hay_block.setHarvestLevel("axe", 0);
		Blocks.tnt.setHarvestLevel("pickaxe", 0);
		Blocks.monster_egg.setHarvestLevel("pickaxe", 0);
		Blocks.obsidian.setHarvestLevel("pickaxe", 3);
		
		try {
			// The Access Transformer should make this work
			Material.tnt.setAdventureModeExempt();
		} catch(Throwable e) {
			UT.Reflection.callMethod(Material.tnt, new String[] {"func_85158_p", "setAdventureModeExempt"}, T, F, F);
			e.printStackTrace(ERR);
		}
		
		Set<Block> tSet = (Set<Block>)UT.Reflection.getFieldContent(ItemAxe.class, "field_150917_c", T, T);
		tSet.add(Blocks.bed);
		tSet.add(Blocks.hay_block);
		tSet.add(Blocks.sponge);
		
		tSet = (Set<Block>)UT.Reflection.getFieldContent(ItemPickaxe.class, "field_150915_c", T, T);
		tSet.add(Blocks.monster_egg);
		tSet.add(Blocks.tnt);
	}
	
	@Mod.EventHandler
	public void onPreLoad(FMLPreInitializationEvent aEvent) {
		DirectoriesGT.CONFIG = aEvent.getModConfigurationDirectory();
		
		DirectoriesGT.CONFIG_GT = new File(DirectoriesGT.CONFIG, "GregTech");
		if (!DirectoriesGT.CONFIG_GT.exists()) DirectoriesGT.CONFIG_GT = new File(DirectoriesGT.CONFIG, "gregtech");
		
		DirectoriesGT.CONFIG_RECIPES = new File(DirectoriesGT.CONFIG, "Recipes");
		if (!DirectoriesGT.CONFIG_RECIPES.exists()) DirectoriesGT.CONFIG_RECIPES = new File(DirectoriesGT.CONFIG, "recipes");
		
		DirectoriesGT.MINECRAFT = DirectoriesGT.CONFIG.getParentFile();
		
		DirectoriesGT.LOGS = new File(DirectoriesGT.MINECRAFT, "logs");
		
		onModPreInit(aEvent);
	}
	
	@Mod.EventHandler
	public void onLoad(FMLInitializationEvent aEvent) {
		for (OreDictMaterial tMaterial : OreDictMaterial.MATERIAL_ARRAY) if (tMaterial != null && !tMaterial.contains(TD.Properties.INVALID_MATERIAL)) {
			tMaterial.mOreProcessingMultiplier = UT.Code.bindStack(ConfigsGT.OREPROCESSING.get(ConfigCategories.Materials.oreprocessingoutputmultiplier, tMaterial.mNameInternal, 1));
			tMaterial.mOreMultiplier = (byte)ConfigsGT.MATERIAL.get(tMaterial.mNameInternal, "MultiplierOre", tMaterial.mOreMultiplier);
			tMaterial.mToolQuality = (byte)ConfigsGT.MATERIAL.get(tMaterial.mNameInternal, "ToolQuality", tMaterial.mToolQuality);
			if (tMaterial.mToolTypes > 0) {
				tMaterial.mToolSpeed = (float)ConfigsGT.MATERIAL.get(tMaterial.mNameInternal, "ToolSpeed", tMaterial.mToolSpeed);
				tMaterial.mToolDurability = ConfigsGT.MATERIAL.get(tMaterial.mNameInternal, "ToolDurability", tMaterial.mToolDurability);
				tMaterial.mHandleMaterial = OreDictMaterial.get(ConfigsGT.MATERIAL.get(tMaterial.mNameInternal, "ToolHandle", tMaterial.mHandleMaterial.mNameInternal));
			}
		}
		onModInit(aEvent);
	}
	
//  @SubscribeEvent
//  @Mod.EventHandler
//  public void loadComplete(FMLLoadCompleteEvent aEvent) {
//      Why the fuck doesn't this work!?! Who can actually receive this Event? Both annotations won't work...
//  }
	
	@Override public String getModID() {return MD.GAPI.mID;}
	@Override public String getModName() {return MD.GAPI.mName;}
	@Override public String getModNameForLog() {return "GT_API";}
	@Override public Abstract_Proxy getProxy() {return api_proxy;}
	
	@Mod.EventHandler public void onPostLoad        (FMLPostInitializationEvent aEvent) {onModPostInit(aEvent);}
	@Mod.EventHandler public void onServerStarting  (FMLServerStartingEvent     aEvent) {onModServerStarting(aEvent);}
	@Mod.EventHandler public void onServerStarted   (FMLServerStartedEvent      aEvent) {onModServerStarted(aEvent);}
	@Mod.EventHandler public void onServerStopping  (FMLServerStoppingEvent     aEvent) {onModServerStopping(aEvent);}
	@Mod.EventHandler public void onServerStopped   (FMLServerStoppedEvent      aEvent) {onModServerStopped(aEvent);}
	
	@Override
	@SuppressWarnings({ "resource", "deprecation" })
	public void onModPreInit2(FMLPreInitializationEvent aEvent) {
		File
		tFile = new File(DirectoriesGT.CONFIG_GT, "IDs.cfg");
		if (!tFile.exists()) tFile = new File(DirectoriesGT.CONFIG_GT, "ids.cfg");
		Config.sConfigFileIDs = new Configuration(tFile); Config.sConfigFileIDs.save();
		
		ConfigsGT.GREGTECH      = new Config("GregTech.cfg").setUseDefaultInNames(F);
		ConfigsGT.RECIPES       = new Config("Recipes.cfg");
		ConfigsGT.WORLDGEN      = new Config("WorldGenerationNew.cfg");
		ConfigsGT.MATERIAL      = new Config("Materials.cfg");
		ConfigsGT.OREPROCESSING = new Config("OreProcessing.cfg");
		// Deprecated Config Files.
		ConfigsGT.OVERPOWERED = ConfigsGT.MACHINES = ConfigsGT.SPECIAL = ConfigsGT.GREGTECH;
		
		
		tFile = new File(DirectoriesGT.CONFIG_GT, "Stacksizes.cfg");
		if (!tFile.exists()) tFile = new File(DirectoriesGT.CONFIG_GT, "stacksizes.cfg");
		Configuration tStackConfig = new Configuration(tFile);
		
		tFile = new File(DirectoriesGT.LOGS, "gregtech.log");
		if (!tFile.exists()) try {tFile.createNewFile();} catch(Throwable e) {/**/}
		
		List<String>
		tList = ((LogBuffer)OUT).mBufferedLog;
		try {
			OUT = new PrintStream(tFile);
		} catch (Throwable e) {
			OUT = System.out;
		}
		
		for (String tString : tList) OUT.println(tString);
		
		if (ConfigsGT.GREGTECH.get("general", "LoggingErrors", T)) {
			tList = ((LogBuffer)ERR).mBufferedLog;
			ERR = OUT;
			for (String tString : tList) ERR.println(tString);
		} else {
			OUT.println("**********************************************************************");
			OUT.println("* WARNING: ERROR LOGGING HAS BEEN DISABLED FOR THIS LOG FILE         *");
			OUT.println("**********************************************************************");
		}
		
		tFile = new File(DirectoriesGT.CONFIG_GT, "materiallist.log");
		if (!tFile.exists()) {try {tFile.createNewFile();} catch (Throwable e) {/**/}}
		try {
			MAT_LOG = new PrintStream(tFile);
			MAT_LOG.println("**********************************************************************");
			MAT_LOG.println("* This is the complete List of usable GregTech Materials             *");
			MAT_LOG.println("**********************************************************************");
		} catch (Throwable e) {/**/}
		
		tFile = new File(DirectoriesGT.LOGS, "oredict.log");
		if (!tFile.exists()) {try {tFile.createNewFile();} catch (Throwable e) {/**/}}
		try {
			tList = ((LogBuffer)ORD).mBufferedLog;
			ORD = new PrintStream(tFile);
			ORD.println("**********************************************************************");
			ORD.println("* This is the complete Log of the GregTech OreDictionary Handler     *");
			ORD.println("**********************************************************************");
			for (String tString : tList) ORD.println(tString);
		} catch (Throwable e) {/**/}
		
		if (ConfigsGT.GREGTECH.get("general", "LoggingPlayerActivity", !CODE_CLIENT)) {
			tFile = new File(DirectoriesGT.LOGS, "playeractivity_"+(System.currentTimeMillis()/60000)+".log");
			if (!tFile.exists()) {try {tFile.createNewFile();} catch (Throwable e) {/**/}}
			try {mPlayerLogger = new LoggerPlayerActivity(new PrintStream(tFile));} catch (Throwable e) {/**/}
		}
		
		ConfigsGT.CLIENT = new Config(DirectoriesGT.MINECRAFT, "GregTech.cfg");
		
		D1 = ConfigsGT.CLIENT.get("debug" , "logs"   , F);
		D2 = ConfigsGT.CLIENT.get("debug" , "oredict", F);
		D3 = ConfigsGT.CLIENT.get("debug" , "misc"   , F);
		CLIENT_BLOCKUPDATE_SOUNDS = ConfigsGT.CLIENT.get("debug" , "block_update_sounds", F);
		if ( ConfigsGT.CLIENT.get("debug" , "april"  , F)) APRIL_FOOLS = T;
		
		if (APRIL_FOOLS) {
			MT.W.setLocal("Wolframium");
			MT.V.setLocal("Vandalium");
			MT.B.setLocal("Boring");
			MT.S.setLocal("Sulphur");
			MT.K.setLocal("Kalium");
			MT.Na.setLocal("Natrium");
			MT.Ar.setLocal("Aragon");
			MT.Al.setLocal("Aluminum");
			MT.Ni.setLocal("Ferrous Metal");
			MT.Pt.setLocal("Shiny Metal");
			MT.Mithril.setLocal("Mana Infused Metal");
			MT.Hg.setLocal("Quicksilver");
			MT.Mo.setLocal("Molly-B");
			MT.Sb.setLocal("Anti-Money");
			MT.Tc.setLocal("Gregorium");
			MT.Si.setLocal("Silicone");
			MT.Cr.setLocal("Chrome");
			MT.Cu.setLocal("Cooper");
			MT.AnnealedCopper.setLocal("Anilled Cooper");
			MT.Mg.setLocal("Manganesium");
			MT.Mn.setLocal("Animenese");
			MT.As.setLocal("Arse Nick");
			MT.Br.setLocal("Bro, that's mine");
			MT.Kr.setLocal("Kryptonite");
			MT.Bi.setLocal("Biffmiff");
			MT.Sg.setLocal("Resistance is Futile");
			MT.Zr.setLocal("Diamond");
			MT.Au.setLocal("Pyrite");
			MT.Pyrite.setLocal("Gold");
			MT.Fe.setLocal("Irun");
			MT.IronWood.setLocal("Irunwood");
			MT.ShadowIron.setLocal("Shade Irun");
			MT.DarkIron.setLocal("Dank Irun");
			MT.MeteoricIron.setLocal("Metaur Irun");
			MT.GildedIron.setLocal("Guild Irun");
			MT.WroughtIron.setLocal("Wrecked Irun");
			MT.Steel.setLocal("Style");
			MT.RedSteel.setLocal("Rad Style");
			MT.BlueSteel.setLocal("Blu Style");
			MT.BlackSteel.setLocal("Afroamerican Style");
			MT.DamascusSteel.setLocal("Dank Style");
			MT.VanadiumSteel.setLocal("Vandalium Style");
			MT.TungstenSteel.setLocal("Wolf Style");
			MT.MeteoricSteel.setLocal("Metaur Style");
			MT.ShadowSteel.setLocal("Shade Style");
			MT.Steeleaf.setLocal("Style Leave");
			MT.Knightmetal.setLocal("Night Metal");
			MT.FierySteel.setLocal("Fury Style");
			MT.Thaumium.setLocal("Thaumanominum");
			MT.DarkThaumium.setLocal("Dank Thaumanominum");
			MT.Coal.setLocal("Cool");
			MT.Charcoal.setLocal("Charred Cole");
			MT.Lapis.setLocal("Le Piss");
			MT.Redstone.setLocal("Blingstone");
			MT.Glowstone.setLocal("Klostein");
			MT.Emerald.setLocal("Chaos Emerald");
			MT.Craponite.setLocal("Pink Diamond");
			MT.Diamond.setLocal("Sapphire");
			MT.DiamondPink.setLocal("Craponite");
			MT.Bedrock.setLocal("Sofarock");
			MT.Plastic.setLocal("LEGO");
			MT.Asbestos.setLocal("Bestos");
			MT.AncientDebris.setLocal("Cinnabun");
			MT.Cinnamon.setLocal("Ancient Debris");
			MT.WOODS.Cinnamon.setLocal("Ancient Debris");
			MT.Rb.setLocal("Ruby");
			MT.Ruby.setLocal("Red Sapphire");
			MT.KNO3.setLocal("Niter");
			MT.NaNO3.setLocal("Nitre");
			MT.Glyceryl.setLocal("Nitro");
			MT.Gunpowder.setLocal("Boompowder");
			MT.Lubricant.setLocal("Lube");
			MT.H2SO4.setLocal("Sulphuric Acid");
			MT.H2S2O7.setLocal("Disulphuric Acid");
			MT.Greenschist.setLocal("Green Shit");
			MT.Blueschist.setLocal("Blue Shit");
			MT.Nikolite.setLocal("Bluestone");
			MT.Teslatite.setLocal("Bluestone");
			MT.Electrotine.setLocal("Bluestone");
			MT.PigIron.setLocal("Ferrobacon");
			MT.TinAlloy.setLocal("Tin*");
			MT.Bronze.setLocal("Tinkerers Alloy");
			MT.BlackBronze.setLocal("Afroamerican Tinkerers Alloy");
			MT.Constantan.setLocal("Cupronickel");
			MT.FakeOsmium.setLocal("Platinum");
			MT.NetherQuartz.setLocal("Weather Quartz");
			MT.MilkyQuartz.setLocal("Milk Quartz");
			MT.CertusQuartz.setLocal("Citrus Quartz");
			MT.ChargedCertusQuartz.setLocal("Charged Citrus Quartz");
			MT.UUMatter.setLocal("UwU-Matter");
			MT.UUAmplifier.setLocal("UwU-Amplifier");
			MT.OREMATS.Galena.setLocal("Silverlead");
			MT.OREMATS.Huebnerite.setLocal("Boobnerite");
			MT.OREMATS.Bromargyrite.setLocal("Bromagnerite");
			MT.OREMATS.Chalcopyrite.setLocal("Chackapackerite");
			
			for (OreDictMaterial tMaterial : OreDictMaterial.MATERIAL_MAP.values()) if (tMaterial.mNameLocal.toLowerCase().contains("wood")) tMaterial.setLocal(tMaterial.mNameLocal + " >:] nice");
		}
		
		if (D1) {
			tList = ((LogBuffer)DEB).mBufferedLog;
			DEB = OUT;
			for (String tString : tList) DEB.println(tString);
		}
		
		for (OreDictPrefix tPrefix : OreDictPrefix.VALUES) if (!tPrefix.contains(TD.Prefix.PREFIX_UNUSED)) {
			tPrefix.setConfigStacksize(tStackConfig.get("stacksizes", tPrefix.mNameInternal+"_"+tPrefix.mDefaultStackSize, tPrefix.mDefaultStackSize).getInt());
		}
		tStackConfig.save();
		
		SURVIVAL_INTO_ADVENTURE_MODE            = ConfigsGT.GREGTECH.get("general", "forceAdventureMode"               , F);
		ADVENTURE_MODE_KIT                      = ConfigsGT.GREGTECH.get("general", "AdventureModeStartingKit"         , !MD.GT.mLoaded);
		HUNGER_BY_INVENTORY_WEIGHT              = ConfigsGT.GREGTECH.get("general", "AFK_Hunger"                       ,  MD.GT.mLoaded);
		INVENTORY_UNIFICATION                   = ConfigsGT.GREGTECH.get("general", "InventoryUnification"             , T);
		XP_ORB_COMBINING                        = ConfigsGT.GREGTECH.get("general", "XP_Orb_Combining"                 , T);
		CONFIG_HARDNESS_MULTIPLIER_SAND         = ConfigsGT.GREGTECH.get("general", "HardnessMultiplier_Sand"          , 1);
		CONFIG_HARDNESS_MULTIPLIER_ROCK         = ConfigsGT.GREGTECH.get("general", "HardnessMultiplier_Rock"          , 1);
		CONFIG_HARDNESS_MULTIPLIER_ORES         = ConfigsGT.GREGTECH.get("general", "HardnessMultiplier_Ores"          , 1);
		ITEM_DESPAWN_TIME                       = ConfigsGT.GREGTECH.get("general", "ItemDespawnTime"                  ,6000);
		TREE_GROWTH_TIME                        = ConfigsGT.GREGTECH.get("general", "Tree_Growth_Time"                 , 1);
		ENTITY_CRAMMING                         = ConfigsGT.GREGTECH.get("general", "MaxEqualEntitiesAtOneSpot"        , 3);
		DRINKS_ALWAYS_DRINKABLE                 = ConfigsGT.GREGTECH.get("general", "drinks_always_drinkable"          , F);
		EMIT_EU_AS_RF                           = ConfigsGT.GREGTECH.get("general", "Emit_EU_as_RF_from_Blocks"        , F);
		NERFED_WOOD                             = ConfigsGT.GREGTECH.get("general", "WoodNeedsSawForCrafting"          , T);
		FORCE_GRAVEL_NO_FLINT                   = ConfigsGT.GREGTECH.get("general", "GravelWontDropFlint"              , F);
		FAST_LEAF_DECAY                         = ConfigsGT.GREGTECH.get("general", "FastLeafDecay"                    , T);
		CONSTANT_ENERGY                         = ConfigsGT.GREGTECH.get("general", "UninterruptedEnergyRequirement"   , T);
		FOOD_OVERDOSE_DEATH                     = ConfigsGT.GREGTECH.get("general", "DeathByOverdosingCertainFoods"    , T);
		NUTRITION_SYSTEM                        = ConfigsGT.GREGTECH.get("general", "NutritionSystem"                  , T);
		OBSTRUCTION_CHECKS                      = ConfigsGT.GREGTECH.get("general", "ObstructionChecks"                , T);
		OWNERSHIP_RESET                         = ConfigsGT.GREGTECH.get("general", "ResetPlayerOwnershipOfGT6Blocks"  , F);
		SPAWN_ZONE_MOB_PROTECTION               = ConfigsGT.GREGTECH.get("general", "PreventMobSpawnsCloseToSpawn"     , T);
		DISABLE_GT6_CRAFTING_RECIPES            = ConfigsGT.GREGTECH.get("general", "DisableGT6CraftingRecipesDEBUG"   , F);
		TOOL_SOUNDS                             = ConfigsGT.GREGTECH.get("general", "sound_tools"                      , T);
		UT.Sounds.MULTITHREADED                 = ConfigsGT.GREGTECH.get("general", "sound_multi_threading"            , F);
		
		ENABLE_ADDING_IC2_MACERATOR_RECIPES     = ConfigsGT.GREGTECH.get("ic2", "EnableAddingMaceratorRecipes"         , T);
		ENABLE_ADDING_IC2_EXTRACTOR_RECIPES     = ConfigsGT.GREGTECH.get("ic2", "EnableAddingExtractorRecipes"         , T);
		ENABLE_ADDING_IC2_COMPRESSOR_RECIPES    = ConfigsGT.GREGTECH.get("ic2", "EnableAddingCompressorRecipes"        , T);
		ENABLE_ADDING_IC2_OREWASHER_RECIPES     = ConfigsGT.GREGTECH.get("ic2", "EnableAddingOreWasherRecipes"         , T);
		ENABLE_ADDING_IC2_CENTRIFUGE_RECIPES    = ConfigsGT.GREGTECH.get("ic2", "EnableAddingThermalCentrifugeRecipes" , T);
		
		if (MD.IC2C.mLoaded) {
		DISABLE_ALL_IC2_MACERATOR_RECIPES       = F;
		ENABLE_ADDING_IC2_MACERATOR_RECIPES     = T;
		DISABLE_ALL_IC2_EXTRACTOR_RECIPES       = F;
		ENABLE_ADDING_IC2_EXTRACTOR_RECIPES     = T;
		DISABLE_ALL_IC2_COMPRESSOR_RECIPES      = F;
		ENABLE_ADDING_IC2_COMPRESSOR_RECIPES    = T;
		DISABLE_ALL_IC2_OREWASHER_RECIPES       = F;
		ENABLE_ADDING_IC2_OREWASHER_RECIPES     = F;
		DISABLE_ALL_IC2_CENTRIFUGE_RECIPES      = F;
		ENABLE_ADDING_IC2_CENTRIFUGE_RECIPES    = F;
		} else if (MD.IC2.mLoaded) {
		DISABLE_ALL_IC2_MACERATOR_RECIPES       = ConfigsGT.GREGTECH.get("ic2", "DisableAllMaceratorRecipes"           , F);
		if (DISABLE_ALL_IC2_MACERATOR_RECIPES) ENABLE_ADDING_IC2_MACERATOR_RECIPES = F;
		DISABLE_ALL_IC2_EXTRACTOR_RECIPES       = ConfigsGT.GREGTECH.get("ic2", "DisableAllExtractorRecipes"           , F);
		if (DISABLE_ALL_IC2_EXTRACTOR_RECIPES) ENABLE_ADDING_IC2_EXTRACTOR_RECIPES = F;
		DISABLE_ALL_IC2_COMPRESSOR_RECIPES      = ConfigsGT.GREGTECH.get("ic2", "DisableAllCompressorRecipes"          , F);
		if (DISABLE_ALL_IC2_COMPRESSOR_RECIPES) ENABLE_ADDING_IC2_COMPRESSOR_RECIPES = F;
		DISABLE_ALL_IC2_OREWASHER_RECIPES       = ConfigsGT.GREGTECH.get("ic2", "DisableAllOreWasherRecipes"           , F);
		if (DISABLE_ALL_IC2_OREWASHER_RECIPES) ENABLE_ADDING_IC2_OREWASHER_RECIPES = F;
		DISABLE_ALL_IC2_CENTRIFUGE_RECIPES      = ConfigsGT.GREGTECH.get("ic2", "DisableAllThermalCentrifugeRecipes"   , F);
		if (DISABLE_ALL_IC2_CENTRIFUGE_RECIPES) ENABLE_ADDING_IC2_CENTRIFUGE_RECIPES = F;
		} else {
		DISABLE_ALL_IC2_MACERATOR_RECIPES       = F;
		ENABLE_ADDING_IC2_MACERATOR_RECIPES     = F;
		DISABLE_ALL_IC2_EXTRACTOR_RECIPES       = F;
		ENABLE_ADDING_IC2_EXTRACTOR_RECIPES     = F;
		DISABLE_ALL_IC2_COMPRESSOR_RECIPES      = F;
		ENABLE_ADDING_IC2_COMPRESSOR_RECIPES    = F;
		DISABLE_ALL_IC2_OREWASHER_RECIPES       = F;
		ENABLE_ADDING_IC2_OREWASHER_RECIPES     = F;
		DISABLE_ALL_IC2_CENTRIFUGE_RECIPES      = F;
		ENABLE_ADDING_IC2_CENTRIFUGE_RECIPES    = F;
		}
		
		if (ConfigsGT.GREGTECH.get("general", "disable_STDOUT"             , F)) System.out.close();
		if (ConfigsGT.GREGTECH.get("general", "disable_STDERR"             , F)) System.err.close();
		if (ConfigsGT.GREGTECH.get("general", "hardermobspawners"          , T)) Blocks.mob_spawner.setHardness(500.0F);
		if (ConfigsGT.GREGTECH.get("general", "blastresistantmobspawners"  , T)) Blocks.mob_spawner.setResistance(6000000.0F);
		
		FIRE_EXPLOSIONS                     = ConfigsGT.GREGTECH.get("machines", "explode_by_fire"    , T);
		RAIN_EXPLOSIONS                     = ConfigsGT.GREGTECH.get("machines", "explode_by_rain"    , T);
		WATER_EXPLOSIONS                    = ConfigsGT.GREGTECH.get("machines", "explode_by_water"   , T);
		THUNDER_EXPLOSIONS                  = ConfigsGT.GREGTECH.get("machines", "explode_by_thunder" , T);
		OVERCHARGE_EXPLOSIONS               = ConfigsGT.GREGTECH.get("machines", "explode_by_overload", F);
		FIRE_BREAKING                       = ConfigsGT.GREGTECH.get("machines", "break_by_fire"      , T);
		RAIN_BREAKING                       = ConfigsGT.GREGTECH.get("machines", "break_by_rain"      , T);
		WATER_BREAKING                      = ConfigsGT.GREGTECH.get("machines", "break_by_water"     , T);
		THUNDER_BREAKING                    = ConfigsGT.GREGTECH.get("machines", "break_by_thunder"   , T);
		OVERCHARGE_BREAKING                 = ConfigsGT.GREGTECH.get("machines", "break_by_overload"  , F);
		
		if (FIRE_EXPLOSIONS      ) FIRE_BREAKING       = T;
		if (RAIN_EXPLOSIONS      ) RAIN_BREAKING       = T;
		if (WATER_EXPLOSIONS     ) WATER_BREAKING      = T;
		if (THUNDER_EXPLOSIONS   ) THUNDER_BREAKING    = T;
		if (OVERCHARGE_EXPLOSIONS) OVERCHARGE_BREAKING = T;
		
		if (CONFIG_HARDNESS_MULTIPLIER_SAND <= 0.0) CONFIG_HARDNESS_MULTIPLIER_SAND = 1.0;
		if (CONFIG_HARDNESS_MULTIPLIER_ROCK <= 0.0) CONFIG_HARDNESS_MULTIPLIER_ROCK = 1.0;
		if (CONFIG_HARDNESS_MULTIPLIER_ORES <= 0.0) CONFIG_HARDNESS_MULTIPLIER_ORES = 1.0;
		
		HARDNESS_MULTIPLIER_SAND = CONFIG_HARDNESS_MULTIPLIER_SAND;
		HARDNESS_MULTIPLIER_ROCK = CONFIG_HARDNESS_MULTIPLIER_ROCK;
		HARDNESS_MULTIPLIER_ORES = CONFIG_HARDNESS_MULTIPLIER_ORES;
		
		if (ConfigsGT.GREGTECH.get("compat", "IC2Classic"          , T)) ICompat.COMPAT_CLASSES.add(                   (ICompat          )UT.Reflection.callConstructor("gregapi.compat.industrialcraft.CompatIC2C"      , 0, null, D2));
		if (ConfigsGT.GREGTECH.get("compat", "IC2EnergyItems"      , T)) ICompat.COMPAT_CLASSES.add(COMPAT_EU_ITEM   = (ICompatIC2EUItem )UT.Reflection.callConstructor("gregapi.compat.industrialcraft.CompatIC2EUItem" , 0, null, D2));
		if (ConfigsGT.GREGTECH.get("compat", "IndustrialCraft2"    , T)) ICompat.COMPAT_CLASSES.add(COMPAT_IC2       = (ICompatIC2       )UT.Reflection.callConstructor("gregapi.compat.industrialcraft.CompatIC2"       , 0, null, D2));
		if (ConfigsGT.GREGTECH.get("compat", "ThaumCraft"          , T)) ICompat.COMPAT_CLASSES.add(COMPAT_TC        = (ICompatTC        )UT.Reflection.callConstructor("gregapi.compat.thaumcraft.CompatTC"             , 0, null, D2));
		if (ConfigsGT.GREGTECH.get("compat", "BuildCraft"          , T)) ICompat.COMPAT_CLASSES.add(COMPAT_BC        = (ICompatBC        )UT.Reflection.callConstructor("gregapi.compat.buildcraft.CompatBC"             , 0, null, D2));
		if (ConfigsGT.GREGTECH.get("compat", "ComputerCraft"       , T)) ICompat.COMPAT_CLASSES.add(COMPAT_CC        = (ICompatCC        )UT.Reflection.callConstructor("gregapi.compat.computercraft.CompatCC"          , 0, null, D2));
		if (ConfigsGT.GREGTECH.get("compat", "Forestry"            , T)) ICompat.COMPAT_CLASSES.add(COMPAT_FR        = (ICompatFR        )UT.Reflection.callConstructor("gregapi.compat.forestry.CompatFR"               , 0, null, D2));
		if (ConfigsGT.GREGTECH.get("compat", "GalactiCraft"        , T)) ICompat.COMPAT_CLASSES.add(COMPAT_GC        = (ICompatGC        )UT.Reflection.callConstructor("gregapi.compat.galacticraft.CompatGC"           , 0, null, D2));
		
		if (MD.TC.mLoaded) try {ThaumcraftApi.objectTags.isEmpty();} catch(NoSuchFieldError e) {throw new RuntimeException("Please uninstall ThaumicFixer, GregTech-6 itself by now fixes the Thaumometer Lag Issue in a far better and less 'Thaumcraft-Addons breaking' way than Thaumic Fixer.");}
		
		SHOW_HIDDEN_ITEMS                   = ConfigsGT.CLIENT.get(ConfigCategories.visibility, "HiddenGTItems"           , F);
		SHOW_HIDDEN_MATERIALS               = ConfigsGT.CLIENT.get(ConfigCategories.visibility, "HiddenGTMaterials"       , F);
		SHOW_HIDDEN_PREFIXES                = ConfigsGT.CLIENT.get(ConfigCategories.visibility, "HiddenGTPrefixes"        , F);
		SHOW_MICROBLOCKS                    = ConfigsGT.CLIENT.get(ConfigCategories.visibility, "MicroBlocks"             , F);
		SHOW_ORE_BLOCK_PREFIXES             = ConfigsGT.CLIENT.get(ConfigCategories.visibility, "OreBlocks"               , F);
		SHOW_INTERNAL_NAMES                 = ConfigsGT.CLIENT.get(ConfigCategories.visibility, "InternalNames"           , F);
		SHOW_CHEM_FORMULAS                  = ConfigsGT.CLIENT.get(ConfigCategories.visibility, "ChemTooltips"            , T);
		
		ITexture.Util.GT_ALPHA_BLENDING     = ConfigsGT.CLIENT.get(ConfigCategories.general, "useGTAlphaBlending"      , ITexture.Util.GT_ALPHA_BLENDING);
		ITexture.Util.MC_ALPHA_BLENDING     = ConfigsGT.CLIENT.get(ConfigCategories.general, "useMCAlphaBlending"      , ITexture.Util.MC_ALPHA_BLENDING);
		
		GT6WorldGenerator.PFAA = (ConfigsGT.WORLDGEN.get(ConfigCategories.general, "AutoDetectPFAA", T) && MD.PFAA.mLoaded && MD.COG.mLoaded);
		GT6WorldGenerator.TFC  = (ConfigsGT.WORLDGEN.get(ConfigCategories.general, "AutoDetectTFC" , T) && (MD.TFC.mLoaded || MD.TFCP.mLoaded));
		
		// Register Crafting Recipe Classes.
		RecipeSorter.register("gregtech:shaped"   , AdvancedCraftingShaped.class   , RecipeSorter.Category.SHAPED   , "after:minecraft:shaped before:minecraft:shapeless");
		RecipeSorter.register("gregtech:shapeless", AdvancedCraftingShapeless.class, RecipeSorter.Category.SHAPELESS, "after:gregtech:shaped after:minecraft:shapeless");
		RecipeSorter.register("gregtech:1ToY"     , AdvancedCrafting1ToY.class     , RecipeSorter.Category.SHAPELESS, "after:gregtech:shaped after:gregtech:shapeless");
		RecipeSorter.register("gregtech:XToY"     , AdvancedCraftingXToY.class     , RecipeSorter.Category.SHAPELESS, "after:gregtech:shaped after:gregtech:1ToY");
		RecipeSorter.register("gregtech:tool"     , AdvancedCraftingTool.class     , RecipeSorter.Category.SHAPELESS, "after:gregtech:shaped after:gregtech:XToY");
		
		// A Default Packet Handler for some of the already existing Code. Yes, all those Packets are generalised special cases in order to save on Bandwidth.
		// [        +127] = PacketConfig
		// [        +126] = PacketPrefix
		// [        +125] = PacketItemStackChat
		// [+112 to +119] = PacketBlockEvent
		// [+104 to +111] = PacketBlockError
		// [+ 72 to + 79] = PacketDeathPoint
		// [-120 to + 71] = PacketSyncData
		// [-128 to -121] = PacketSound
		NW_API = new NetworkHandler(MD.GAPI.mID, "GAPI", new PacketConfig(), new PacketPrefix(), new PacketItemStackChat()
		, new PacketBlockEvent                          ( 0), new PacketBlockEvent                          ( 1), new PacketBlockEvent                          ( 2), new PacketBlockEvent                          ( 3), new PacketBlockEvent                          ( 4), new PacketBlockEvent                          ( 5), new PacketBlockEvent                          ( 6), new PacketBlockEvent                          ( 7)
		, new PacketBlockError                          ( 0), new PacketBlockError                          ( 1), new PacketBlockError                          ( 2), new PacketBlockError                          ( 3), new PacketBlockError                          ( 4), new PacketBlockError                          ( 5), new PacketBlockError                          ( 6), new PacketBlockError                          ( 7)
		, new PacketDeathPoint                          ( 0), new PacketDeathPoint                          ( 1), new PacketDeathPoint                          ( 2), new PacketDeathPoint                          ( 3), new PacketDeathPoint                          ( 4), new PacketDeathPoint                          ( 5), new PacketDeathPoint                          ( 6), new PacketDeathPoint                          ( 7)
		, new PacketSound                               ( 0), new PacketSound                               ( 1), new PacketSound                               ( 2), new PacketSound                               ( 3), new PacketSound                               ( 4), new PacketSound                               ( 5), new PacketSound                               ( 6), new PacketSound                               ( 7)
		, new PacketSyncDataName                        ( 0), new PacketSyncDataName                        ( 1), new PacketSyncDataName                        ( 2), new PacketSyncDataName                        ( 3), new PacketSyncDataName                        ( 4), new PacketSyncDataName                        ( 5), new PacketSyncDataName                        ( 6), new PacketSyncDataName                        ( 7)
		, new PacketSyncDataByte                        ( 0), new PacketSyncDataByte                        ( 1), new PacketSyncDataByte                        ( 2), new PacketSyncDataByte                        ( 3), new PacketSyncDataByte                        ( 4), new PacketSyncDataByte                        ( 5), new PacketSyncDataByte                        ( 6), new PacketSyncDataByte                        ( 7)
		, new PacketSyncDataShort                       ( 0), new PacketSyncDataShort                       ( 1), new PacketSyncDataShort                       ( 2), new PacketSyncDataShort                       ( 3), new PacketSyncDataShort                       ( 4), new PacketSyncDataShort                       ( 5), new PacketSyncDataShort                       ( 6), new PacketSyncDataShort                       ( 7)
		, new PacketSyncDataInteger                     ( 0), new PacketSyncDataInteger                     ( 1), new PacketSyncDataInteger                     ( 2), new PacketSyncDataInteger                     ( 3), new PacketSyncDataInteger                     ( 4), new PacketSyncDataInteger                     ( 5), new PacketSyncDataInteger                     ( 6), new PacketSyncDataInteger                     ( 7)
		, new PacketSyncDataLong                        ( 0), new PacketSyncDataLong                        ( 1), new PacketSyncDataLong                        ( 2), new PacketSyncDataLong                        ( 3), new PacketSyncDataLong                        ( 4), new PacketSyncDataLong                        ( 5), new PacketSyncDataLong                        ( 6), new PacketSyncDataLong                        ( 7)
		, new PacketSyncDataByteArray                   ( 0), new PacketSyncDataByteArray                   ( 1), new PacketSyncDataByteArray                   ( 2), new PacketSyncDataByteArray                   ( 3), new PacketSyncDataByteArray                   ( 4), new PacketSyncDataByteArray                   ( 5), new PacketSyncDataByteArray                   ( 6), new PacketSyncDataByteArray                   ( 7)
		, new PacketSyncDataIDs                         ( 0), new PacketSyncDataIDs                         ( 1), new PacketSyncDataIDs                         ( 2), new PacketSyncDataIDs                         ( 3), new PacketSyncDataIDs                         ( 4), new PacketSyncDataIDs                         ( 5), new PacketSyncDataIDs                         ( 6), new PacketSyncDataIDs                         ( 7)
		, new PacketSyncDataByteAndIDs                  ( 0), new PacketSyncDataByteAndIDs                  ( 1), new PacketSyncDataByteAndIDs                  ( 2), new PacketSyncDataByteAndIDs                  ( 3), new PacketSyncDataByteAndIDs                  ( 4), new PacketSyncDataByteAndIDs                  ( 5), new PacketSyncDataByteAndIDs                  ( 6), new PacketSyncDataByteAndIDs                  ( 7)
		, new PacketSyncDataShortAndIDs                 ( 0), new PacketSyncDataShortAndIDs                 ( 1), new PacketSyncDataShortAndIDs                 ( 2), new PacketSyncDataShortAndIDs                 ( 3), new PacketSyncDataShortAndIDs                 ( 4), new PacketSyncDataShortAndIDs                 ( 5), new PacketSyncDataShortAndIDs                 ( 6), new PacketSyncDataShortAndIDs                 ( 7)
		, new PacketSyncDataIntegerAndIDs               ( 0), new PacketSyncDataIntegerAndIDs               ( 1), new PacketSyncDataIntegerAndIDs               ( 2), new PacketSyncDataIntegerAndIDs               ( 3), new PacketSyncDataIntegerAndIDs               ( 4), new PacketSyncDataIntegerAndIDs               ( 5), new PacketSyncDataIntegerAndIDs               ( 6), new PacketSyncDataIntegerAndIDs               ( 7)
		, new PacketSyncDataLongAndIDs                  ( 0), new PacketSyncDataLongAndIDs                  ( 1), new PacketSyncDataLongAndIDs                  ( 2), new PacketSyncDataLongAndIDs                  ( 3), new PacketSyncDataLongAndIDs                  ( 4), new PacketSyncDataLongAndIDs                  ( 5), new PacketSyncDataLongAndIDs                  ( 6), new PacketSyncDataLongAndIDs                  ( 7)
		, new PacketSyncDataByteArrayAndIDs             ( 0), new PacketSyncDataByteArrayAndIDs             ( 1), new PacketSyncDataByteArrayAndIDs             ( 2), new PacketSyncDataByteArrayAndIDs             ( 3), new PacketSyncDataByteArrayAndIDs             ( 4), new PacketSyncDataByteArrayAndIDs             ( 5), new PacketSyncDataByteArrayAndIDs             ( 6), new PacketSyncDataByteArrayAndIDs             ( 7)
		, new PacketSyncDataIDsAndCovers                ( 0), new PacketSyncDataIDsAndCovers                ( 1), new PacketSyncDataIDsAndCovers                ( 2), new PacketSyncDataIDsAndCovers                ( 3), new PacketSyncDataIDsAndCovers                ( 4), new PacketSyncDataIDsAndCovers                ( 5), new PacketSyncDataIDsAndCovers                ( 6), new PacketSyncDataIDsAndCovers                ( 7)
		, new PacketSyncDataByteAndIDsAndCovers         ( 0), new PacketSyncDataByteAndIDsAndCovers         ( 1), new PacketSyncDataByteAndIDsAndCovers         ( 2), new PacketSyncDataByteAndIDsAndCovers         ( 3), new PacketSyncDataByteAndIDsAndCovers         ( 4), new PacketSyncDataByteAndIDsAndCovers         ( 5), new PacketSyncDataByteAndIDsAndCovers         ( 6), new PacketSyncDataByteAndIDsAndCovers         ( 7)
		, new PacketSyncDataShortAndIDsAndCovers        ( 0), new PacketSyncDataShortAndIDsAndCovers        ( 1), new PacketSyncDataShortAndIDsAndCovers        ( 2), new PacketSyncDataShortAndIDsAndCovers        ( 3), new PacketSyncDataShortAndIDsAndCovers        ( 4), new PacketSyncDataShortAndIDsAndCovers        ( 5), new PacketSyncDataShortAndIDsAndCovers        ( 6), new PacketSyncDataShortAndIDsAndCovers        ( 7)
		, new PacketSyncDataIntegerAndIDsAndCovers      ( 0), new PacketSyncDataIntegerAndIDsAndCovers      ( 1), new PacketSyncDataIntegerAndIDsAndCovers      ( 2), new PacketSyncDataIntegerAndIDsAndCovers      ( 3), new PacketSyncDataIntegerAndIDsAndCovers      ( 4), new PacketSyncDataIntegerAndIDsAndCovers      ( 5), new PacketSyncDataIntegerAndIDsAndCovers      ( 6), new PacketSyncDataIntegerAndIDsAndCovers      ( 7)
		, new PacketSyncDataLongAndIDsAndCovers         ( 0), new PacketSyncDataLongAndIDsAndCovers         ( 1), new PacketSyncDataLongAndIDsAndCovers         ( 2), new PacketSyncDataLongAndIDsAndCovers         ( 3), new PacketSyncDataLongAndIDsAndCovers         ( 4), new PacketSyncDataLongAndIDsAndCovers         ( 5), new PacketSyncDataLongAndIDsAndCovers         ( 6), new PacketSyncDataLongAndIDsAndCovers         ( 7)
		, new PacketSyncDataByteArrayAndIDsAndCovers    ( 0), new PacketSyncDataByteArrayAndIDsAndCovers    ( 1), new PacketSyncDataByteArrayAndIDsAndCovers    ( 2), new PacketSyncDataByteArrayAndIDsAndCovers    ( 3), new PacketSyncDataByteArrayAndIDsAndCovers    ( 4), new PacketSyncDataByteArrayAndIDsAndCovers    ( 5), new PacketSyncDataByteArrayAndIDsAndCovers    ( 6), new PacketSyncDataByteArrayAndIDsAndCovers    ( 7)
		, new PacketSyncDataCoverVisuals                ( 0), new PacketSyncDataCoverVisuals                ( 1), new PacketSyncDataCoverVisuals                ( 2), new PacketSyncDataCoverVisuals                ( 3), new PacketSyncDataCoverVisuals                ( 4), new PacketSyncDataCoverVisuals                ( 5), new PacketSyncDataCoverVisuals                ( 6), new PacketSyncDataCoverVisuals                ( 7)
		, new PacketSyncDataByteAndCoverVisuals         ( 0), new PacketSyncDataByteAndCoverVisuals         ( 1), new PacketSyncDataByteAndCoverVisuals         ( 2), new PacketSyncDataByteAndCoverVisuals         ( 3), new PacketSyncDataByteAndCoverVisuals         ( 4), new PacketSyncDataByteAndCoverVisuals         ( 5), new PacketSyncDataByteAndCoverVisuals         ( 6), new PacketSyncDataByteAndCoverVisuals         ( 7)
		, new PacketSyncDataShortAndCoverVisuals        ( 0), new PacketSyncDataShortAndCoverVisuals        ( 1), new PacketSyncDataShortAndCoverVisuals        ( 2), new PacketSyncDataShortAndCoverVisuals        ( 3), new PacketSyncDataShortAndCoverVisuals        ( 4), new PacketSyncDataShortAndCoverVisuals        ( 5), new PacketSyncDataShortAndCoverVisuals        ( 6), new PacketSyncDataShortAndCoverVisuals        ( 7)
		, new PacketSyncDataIntegerAndCoverVisuals      ( 0), new PacketSyncDataIntegerAndCoverVisuals      ( 1), new PacketSyncDataIntegerAndCoverVisuals      ( 2), new PacketSyncDataIntegerAndCoverVisuals      ( 3), new PacketSyncDataIntegerAndCoverVisuals      ( 4), new PacketSyncDataIntegerAndCoverVisuals      ( 5), new PacketSyncDataIntegerAndCoverVisuals      ( 6), new PacketSyncDataIntegerAndCoverVisuals      ( 7)
		, new PacketSyncDataLongAndCoverVisuals         ( 0), new PacketSyncDataLongAndCoverVisuals         ( 1), new PacketSyncDataLongAndCoverVisuals         ( 2), new PacketSyncDataLongAndCoverVisuals         ( 3), new PacketSyncDataLongAndCoverVisuals         ( 4), new PacketSyncDataLongAndCoverVisuals         ( 5), new PacketSyncDataLongAndCoverVisuals         ( 6), new PacketSyncDataLongAndCoverVisuals         ( 7)
		, new PacketSyncDataByteArrayAndCoverVisuals    ( 0), new PacketSyncDataByteArrayAndCoverVisuals    ( 1), new PacketSyncDataByteArrayAndCoverVisuals    ( 2), new PacketSyncDataByteArrayAndCoverVisuals    ( 3), new PacketSyncDataByteArrayAndCoverVisuals    ( 4), new PacketSyncDataByteArrayAndCoverVisuals    ( 5), new PacketSyncDataByteArrayAndCoverVisuals    ( 6), new PacketSyncDataByteArrayAndCoverVisuals    ( 7)
		);
		NW_AP2 = new NetworkHandler(MD.GAPI.mID, "GAP2"
		, new PacketSyncDataByte                        ( 0), new PacketSyncDataByte                        ( 1), new PacketSyncDataByte                        ( 2), new PacketSyncDataByte                        ( 3), new PacketSyncDataByte                        ( 4), new PacketSyncDataByte                        ( 5), new PacketSyncDataByte                        ( 6), new PacketSyncDataByte                        ( 7)
		, new PacketSyncDataShort                       ( 0), new PacketSyncDataShort                       ( 1), new PacketSyncDataShort                       ( 2), new PacketSyncDataShort                       ( 3), new PacketSyncDataShort                       ( 4), new PacketSyncDataShort                       ( 5), new PacketSyncDataShort                       ( 6), new PacketSyncDataShort                       ( 7)
		, new PacketSyncDataInteger                     ( 0), new PacketSyncDataInteger                     ( 1), new PacketSyncDataInteger                     ( 2), new PacketSyncDataInteger                     ( 3), new PacketSyncDataInteger                     ( 4), new PacketSyncDataInteger                     ( 5), new PacketSyncDataInteger                     ( 6), new PacketSyncDataInteger                     ( 7)
		, new PacketSyncDataLong                        ( 0), new PacketSyncDataLong                        ( 1), new PacketSyncDataLong                        ( 2), new PacketSyncDataLong                        ( 3), new PacketSyncDataLong                        ( 4), new PacketSyncDataLong                        ( 5), new PacketSyncDataLong                        ( 6), new PacketSyncDataLong                        ( 7)
		, new PacketSyncDataByteArray                   ( 0), new PacketSyncDataByteArray                   ( 1), new PacketSyncDataByteArray                   ( 2), new PacketSyncDataByteArray                   ( 3), new PacketSyncDataByteArray                   ( 4), new PacketSyncDataByteArray                   ( 5), new PacketSyncDataByteArray                   ( 6), new PacketSyncDataByteArray                   ( 7)
		, new PacketSyncDataIDs                         ( 0), new PacketSyncDataIDs                         ( 1), new PacketSyncDataIDs                         ( 2), new PacketSyncDataIDs                         ( 3), new PacketSyncDataIDs                         ( 4), new PacketSyncDataIDs                         ( 5), new PacketSyncDataIDs                         ( 6), new PacketSyncDataIDs                         ( 7)
		, new PacketSyncDataByteAndIDs                  ( 0), new PacketSyncDataByteAndIDs                  ( 1), new PacketSyncDataByteAndIDs                  ( 2), new PacketSyncDataByteAndIDs                  ( 3), new PacketSyncDataByteAndIDs                  ( 4), new PacketSyncDataByteAndIDs                  ( 5), new PacketSyncDataByteAndIDs                  ( 6), new PacketSyncDataByteAndIDs                  ( 7)
		, new PacketSyncDataShortAndIDs                 ( 0), new PacketSyncDataShortAndIDs                 ( 1), new PacketSyncDataShortAndIDs                 ( 2), new PacketSyncDataShortAndIDs                 ( 3), new PacketSyncDataShortAndIDs                 ( 4), new PacketSyncDataShortAndIDs                 ( 5), new PacketSyncDataShortAndIDs                 ( 6), new PacketSyncDataShortAndIDs                 ( 7)
		, new PacketSyncDataIntegerAndIDs               ( 0), new PacketSyncDataIntegerAndIDs               ( 1), new PacketSyncDataIntegerAndIDs               ( 2), new PacketSyncDataIntegerAndIDs               ( 3), new PacketSyncDataIntegerAndIDs               ( 4), new PacketSyncDataIntegerAndIDs               ( 5), new PacketSyncDataIntegerAndIDs               ( 6), new PacketSyncDataIntegerAndIDs               ( 7)
		, new PacketSyncDataLongAndIDs                  ( 0), new PacketSyncDataLongAndIDs                  ( 1), new PacketSyncDataLongAndIDs                  ( 2), new PacketSyncDataLongAndIDs                  ( 3), new PacketSyncDataLongAndIDs                  ( 4), new PacketSyncDataLongAndIDs                  ( 5), new PacketSyncDataLongAndIDs                  ( 6), new PacketSyncDataLongAndIDs                  ( 7)
		, new PacketSyncDataByteArrayAndIDs             ( 0), new PacketSyncDataByteArrayAndIDs             ( 1), new PacketSyncDataByteArrayAndIDs             ( 2), new PacketSyncDataByteArrayAndIDs             ( 3), new PacketSyncDataByteArrayAndIDs             ( 4), new PacketSyncDataByteArrayAndIDs             ( 5), new PacketSyncDataByteArrayAndIDs             ( 6), new PacketSyncDataByteArrayAndIDs             ( 7)
		, new PacketSyncDataIDsAndCovers                ( 0), new PacketSyncDataIDsAndCovers                ( 1), new PacketSyncDataIDsAndCovers                ( 2), new PacketSyncDataIDsAndCovers                ( 3), new PacketSyncDataIDsAndCovers                ( 4), new PacketSyncDataIDsAndCovers                ( 5), new PacketSyncDataIDsAndCovers                ( 6), new PacketSyncDataIDsAndCovers                ( 7)
		, new PacketSyncDataByteAndIDsAndCovers         ( 0), new PacketSyncDataByteAndIDsAndCovers         ( 1), new PacketSyncDataByteAndIDsAndCovers         ( 2), new PacketSyncDataByteAndIDsAndCovers         ( 3), new PacketSyncDataByteAndIDsAndCovers         ( 4), new PacketSyncDataByteAndIDsAndCovers         ( 5), new PacketSyncDataByteAndIDsAndCovers         ( 6), new PacketSyncDataByteAndIDsAndCovers         ( 7)
		, new PacketSyncDataShortAndIDsAndCovers        ( 0), new PacketSyncDataShortAndIDsAndCovers        ( 1), new PacketSyncDataShortAndIDsAndCovers        ( 2), new PacketSyncDataShortAndIDsAndCovers        ( 3), new PacketSyncDataShortAndIDsAndCovers        ( 4), new PacketSyncDataShortAndIDsAndCovers        ( 5), new PacketSyncDataShortAndIDsAndCovers        ( 6), new PacketSyncDataShortAndIDsAndCovers        ( 7)
		, new PacketSyncDataIntegerAndIDsAndCovers      ( 0), new PacketSyncDataIntegerAndIDsAndCovers      ( 1), new PacketSyncDataIntegerAndIDsAndCovers      ( 2), new PacketSyncDataIntegerAndIDsAndCovers      ( 3), new PacketSyncDataIntegerAndIDsAndCovers      ( 4), new PacketSyncDataIntegerAndIDsAndCovers      ( 5), new PacketSyncDataIntegerAndIDsAndCovers      ( 6), new PacketSyncDataIntegerAndIDsAndCovers      ( 7)
		, new PacketSyncDataLongAndIDsAndCovers         ( 0), new PacketSyncDataLongAndIDsAndCovers         ( 1), new PacketSyncDataLongAndIDsAndCovers         ( 2), new PacketSyncDataLongAndIDsAndCovers         ( 3), new PacketSyncDataLongAndIDsAndCovers         ( 4), new PacketSyncDataLongAndIDsAndCovers         ( 5), new PacketSyncDataLongAndIDsAndCovers         ( 6), new PacketSyncDataLongAndIDsAndCovers         ( 7)
		, new PacketSyncDataByteArrayAndIDsAndCovers    ( 0), new PacketSyncDataByteArrayAndIDsAndCovers    ( 1), new PacketSyncDataByteArrayAndIDsAndCovers    ( 2), new PacketSyncDataByteArrayAndIDsAndCovers    ( 3), new PacketSyncDataByteArrayAndIDsAndCovers    ( 4), new PacketSyncDataByteArrayAndIDsAndCovers    ( 5), new PacketSyncDataByteArrayAndIDsAndCovers    ( 6), new PacketSyncDataByteArrayAndIDsAndCovers    ( 7)
		, new PacketSyncDataCoverVisuals                ( 0), new PacketSyncDataCoverVisuals                ( 1), new PacketSyncDataCoverVisuals                ( 2), new PacketSyncDataCoverVisuals                ( 3), new PacketSyncDataCoverVisuals                ( 4), new PacketSyncDataCoverVisuals                ( 5), new PacketSyncDataCoverVisuals                ( 6), new PacketSyncDataCoverVisuals                ( 7)
		, new PacketSyncDataByteAndCoverVisuals         ( 0), new PacketSyncDataByteAndCoverVisuals         ( 1), new PacketSyncDataByteAndCoverVisuals         ( 2), new PacketSyncDataByteAndCoverVisuals         ( 3), new PacketSyncDataByteAndCoverVisuals         ( 4), new PacketSyncDataByteAndCoverVisuals         ( 5), new PacketSyncDataByteAndCoverVisuals         ( 6), new PacketSyncDataByteAndCoverVisuals         ( 7)
		, new PacketSyncDataShortAndCoverVisuals        ( 0), new PacketSyncDataShortAndCoverVisuals        ( 1), new PacketSyncDataShortAndCoverVisuals        ( 2), new PacketSyncDataShortAndCoverVisuals        ( 3), new PacketSyncDataShortAndCoverVisuals        ( 4), new PacketSyncDataShortAndCoverVisuals        ( 5), new PacketSyncDataShortAndCoverVisuals        ( 6), new PacketSyncDataShortAndCoverVisuals        ( 7)
		, new PacketSyncDataIntegerAndCoverVisuals      ( 0), new PacketSyncDataIntegerAndCoverVisuals      ( 1), new PacketSyncDataIntegerAndCoverVisuals      ( 2), new PacketSyncDataIntegerAndCoverVisuals      ( 3), new PacketSyncDataIntegerAndCoverVisuals      ( 4), new PacketSyncDataIntegerAndCoverVisuals      ( 5), new PacketSyncDataIntegerAndCoverVisuals      ( 6), new PacketSyncDataIntegerAndCoverVisuals      ( 7)
		, new PacketSyncDataLongAndCoverVisuals         ( 0), new PacketSyncDataLongAndCoverVisuals         ( 1), new PacketSyncDataLongAndCoverVisuals         ( 2), new PacketSyncDataLongAndCoverVisuals         ( 3), new PacketSyncDataLongAndCoverVisuals         ( 4), new PacketSyncDataLongAndCoverVisuals         ( 5), new PacketSyncDataLongAndCoverVisuals         ( 6), new PacketSyncDataLongAndCoverVisuals         ( 7)
		, new PacketSyncDataByteArrayAndCoverVisuals    ( 0), new PacketSyncDataByteArrayAndCoverVisuals    ( 1), new PacketSyncDataByteArrayAndCoverVisuals    ( 2), new PacketSyncDataByteArrayAndCoverVisuals    ( 3), new PacketSyncDataByteArrayAndCoverVisuals    ( 4), new PacketSyncDataByteArrayAndCoverVisuals    ( 5), new PacketSyncDataByteArrayAndCoverVisuals    ( 6), new PacketSyncDataByteArrayAndCoverVisuals    ( 7)
		);
		// Registering the TileEntity used for Meta-Generated Blocks to store the 32000 variations.
		GameRegistry.registerTileEntity(PrefixBlockTileEntity.class, "gt.MetaBlockTileEntity");
		// Creating and loading the Lang File.
		if (CODE_CLIENT) {
			tFile = new File(DirectoriesGT.MINECRAFT, "GregTech.lang");
			if (!tFile.exists()) tFile = new File(DirectoriesGT.MINECRAFT, "gregtech.lang");
			LanguageHandler.sLangFile = new Configuration(tFile);
		} else {
			sBlockIconload.clear();
			sBlockIconload = null;
			sItemIconload.clear();
			sItemIconload = null;
		}
		// Creating and loading the Unification Config.
		OreDictManager.INSTANCE.mUnificationConfig = new Config("Unification.cfg");
		// Initialising the Re-Registrations.
		new LoaderOreDictReRegistrations().run();
		// Register the Falling MetaBlock Entity.
		EntityRegistry.registerModEntity(PrefixBlockFallingEntity.class, "gt.MetaBlockFallingEntity", 0, this, 160, 1, T);
		// Initialise Enchantments.
		new Enchantment_WerewolfDamage();
		new Enchantment_EnderDamage();
		new Enchantment_Radioactivity();
		new Enchantment_SlimeDamage();
		// Initialises the Fluid Display Item.
		IL.Display_Fluid.set(new ItemFluidDisplay());
		// Initialises the Integrated Circuit Item.
		IL.Circuit_Selector.set(new ItemIntegratedCircuit());
		// Initialises the Empty Slot Marker Item.
		IL.Empty_Slot.set(new ItemEmptySlot());
		// Register the GUI Handler.
		NetworkRegistry.INSTANCE.registerGuiHandler(this, api_proxy);
		// Fixing vanilla Oak Plank Slab Recipe.
		CR.remove(ST.make(Blocks.planks, 1, 0), ST.make(Blocks.planks, 1, 1), ST.make(Blocks.planks, 1, 2));
		CR.shaped(ST.make(Blocks.wooden_slab, 6, 0), CR.NONE, "WWW", 'W', ST.make(Blocks.planks, 1, 0));
		// Preventing a Water Dupe by registering this Recipe early so it won't be overridden
		RM.Canner.addRecipe1(T, 16, 16, ST.make(Items.glass_bottle, 1, 0), FL.Water.make(250), NF, ST.make(Items.potionitem, 1, 0));
		RM.Canner.addRecipe1(T, 16, 16, ST.make(Items.potionitem, 1, 0), ST.make(Items.glass_bottle, 1, 0));
		
		try {
			OUT.println(getModNameForLog() + ": Sorting Greg-API to the start of the Mod List for further processing.");
			LoadController tLoadController = ((LoadController)UT.Reflection.getFieldContent(Loader.instance(), "modController", T, T));
			List<ModContainer> tModList = tLoadController.getActiveModList(), tNewModsList = new ArrayList<>(tModList.size());
			ModContainer tGregTech = null;
			for (short i = 0; i < tModList.size(); i++) {
				ModContainer tMod = tModList.get(i);
				if (tMod.getModId().equalsIgnoreCase(MD.GAPI.mID)) tGregTech = tMod; else tNewModsList.add(tMod);
			}
			if (tGregTech != null) tNewModsList.add(0, tGregTech);
			UT.Reflection.getField(tLoadController, "activeModList", T, T).set(tLoadController, tNewModsList);
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
		
		for (ICompat tCompat : ICompat.COMPAT_CLASSES) try {tCompat.onPreLoad(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	@Override
	public void onModInit2(FMLInitializationEvent aEvent) {
// TODO team.chisel.carving.Carving.chisel.getGroup("cobblestone").setOreName(null);
		
		OUT.println(getModNameForLog() + ": If the Loading Bar somehow Freezes at this Point, then you definetly ran out of Memory or permgenspace, look at the other Logs to confirm it.");
		OreDictManager.INSTANCE.enableRegistrations();
		
		for (ICompat tCompat : ICompat.COMPAT_CLASSES) try {tCompat.onLoad(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	@Override
	public void onModPostInit2(FMLPostInitializationEvent aEvent) {
		if (MD.IC2.mLoaded) {
			PotionsGT.ID_RADIATION = ic2.api.info.Info.POTION_RADIATION.id;
		}
		if (MD.ENVM.mLoaded) {
			PotionsGT.ID_DEHYDRATION = EnviroPotion.dehydration.id;
			PotionsGT.ID_FROSTBITE = EnviroPotion.frostbite.id;
			PotionsGT.ID_HEATSTROKE = EnviroPotion.heatstroke.id;
			PotionsGT.ID_HYPOTHERMIA = EnviroPotion.hypothermia.id;
			PotionsGT.ID_INSANITY = EnviroPotion.insanity.id;
		}
		
		EnergyCompat.checkAvailabilities();
		ToolCompat.checkAvailabilities();
		ST.checkAvailabilities();
		
		OUT.println(getModNameForLog() + ": If the Loading Bar somehow Freezes at this Point, then you definetly ran out of Memory or permgenspace, look at the other Logs to confirm it.");
		OreDictManager.INSTANCE.onPostLoad();
		
		ICover tCover = new CoverRedstoneTorch();
		CoverRegistry.put(ST.make(Blocks.redstone_torch, 1, 0), tCover);
		CoverRegistry.put(ST.make(Blocks.unlit_redstone_torch, 1, 0), tCover);
		CoverRegistry.put(ST.make(Items.repeater, 1, 0), new CoverRedstoneRepeater());
		
		OreDictPrefix.applyAllStackSizes();
		
//      Doesn't fucking work, the Chisel API is pure garbage...
//      if (MD.CHSL.mLoaded) {
//          if (MD.EtFu.mLoaded) {
//              FMLInterModComms.sendRuntimeMessage(GAPI, "ChiselAPI|Carving", "variation:add", "granite|" +MD.EtFu.mID+":stone|1");
//              FMLInterModComms.sendRuntimeMessage(GAPI, "ChiselAPI|Carving", "variation:add", "diorite|" +MD.EtFu.mID+":stone|3");
//              FMLInterModComms.sendRuntimeMessage(GAPI, "ChiselAPI|Carving", "variation:add", "andesite|"+MD.EtFu.mID+":stone|5");
//          }
//          if (MD.BOTA.mLoaded) {
//              FMLInterModComms.sendRuntimeMessage(GAPI, "ChiselAPI|Carving", "variation:add", "granite|" +MD.BOTA.mID+":stone|3");
//              FMLInterModComms.sendRuntimeMessage(GAPI, "ChiselAPI|Carving", "variation:add", "diorite|" +MD.BOTA.mID+":stone|2");
//              FMLInterModComms.sendRuntimeMessage(GAPI, "ChiselAPI|Carving", "variation:add", "andesite|"+MD.BOTA.mID+":stone|0");
//          }
//          if (MD.GT.mLoaded) {
//              FMLInterModComms.sendRuntimeMessage(GAPI, "ChiselAPI|Carving", "variation:add", "granite|" +MD.GT.mID+":gt.stone.granite|0");
//              FMLInterModComms.sendRuntimeMessage(GAPI, "ChiselAPI|Carving", "variation:add", "diorite|" +MD.GT.mID+":gt.stone.diorite|0");
//              FMLInterModComms.sendRuntimeMessage(GAPI, "ChiselAPI|Carving", "variation:add", "andesite|"+MD.GT.mID+":gt.stone.andesite|0");
//          }
//      }
		
		// Saving the Lang File.
		LanguageHandler.save();
		
		if (mPlayerLogger != null) new Thread(mPlayerLogger).start();
		
		for (ICompat tCompat : ICompat.COMPAT_CLASSES) try {tCompat.onPostLoad(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
		
		for (OreDictMaterial tMaterial : OreDictMaterial.MATERIAL_ARRAY) if (tMaterial != null && !tMaterial.contains(TD.Properties.INVALID_MATERIAL)) {
			if (tMaterial.mID < 10000) MAT_LOG.print(" ");
			if (tMaterial.mID <  1000) MAT_LOG.print(" ");
			if (tMaterial.mID <   100) MAT_LOG.print(" ");
			if (tMaterial.mID <    10) MAT_LOG.print(" ");
			MAT_LOG.print(tMaterial.mID);
			MAT_LOG.print(": ");
			MAT_LOG.print(tMaterial.mNameInternal);
			MAT_LOG.println();
		}
	}
	
	@Override
	public void onModServerStarting2(FMLServerStartingEvent aEvent) {
		for (ICompat tCompat : ICompat.COMPAT_CLASSES) try {tCompat.onServerStarting(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	@Override
	public void onModServerStarted2(FMLServerStartedEvent aEvent) {
		for (Map<ItemStackContainer, ?> tMap : STACKMAPS) UT.Code.reMap(tMap);
		for (ICompat tCompat : ICompat.COMPAT_CLASSES) try {tCompat.onServerStarted(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	@Override
	public void onModServerStopping2(FMLServerStoppingEvent aEvent) {
		for (ICompat tCompat : ICompat.COMPAT_CLASSES) try {tCompat.onServerStopping(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	@Override
	public void onModServerStopped2(FMLServerStoppedEvent aEvent) {
		for (ICompat tCompat : ICompat.COMPAT_CLASSES) try {tCompat.onServerStopped(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	@Mod.EventHandler
	public void onIDChangingEvent(FMLModIdMappingEvent aEvent) {
		// Fixing missing Blocks caused by DragonAPI. The Issue is more complicated but it should fix some part of it.
		if (Block.blockRegistry.getObjectById( 26) == null) Block.blockRegistry.addObject( 26, "bed", Blocks.bed);
		if (Block.blockRegistry.getObjectById( 34) == null) Block.blockRegistry.addObject( 34, "piston_head", Blocks.piston_head);
		if (Block.blockRegistry.getObjectById( 55) == null) Block.blockRegistry.addObject( 55, "redstone_wire", Blocks.redstone_wire);
		if (Block.blockRegistry.getObjectById( 59) == null) Block.blockRegistry.addObject( 59, "wheat", Blocks.wheat);
		if (Block.blockRegistry.getObjectById( 63) == null) Block.blockRegistry.addObject( 63, "standing_sign", Blocks.standing_sign);
		if (Block.blockRegistry.getObjectById( 64) == null) Block.blockRegistry.addObject( 64, "wooden_door", Blocks.wooden_door);
		if (Block.blockRegistry.getObjectById( 68) == null) Block.blockRegistry.addObject( 68, "wall_sign", Blocks.wall_sign);
		if (Block.blockRegistry.getObjectById( 71) == null) Block.blockRegistry.addObject( 71, "iron_door", Blocks.iron_door);
		if (Block.blockRegistry.getObjectById( 74) == null) Block.blockRegistry.addObject( 74, "lit_redstone_ore", Blocks.lit_redstone_ore);
		if (Block.blockRegistry.getObjectById( 75) == null) Block.blockRegistry.addObject( 75, "unlit_redstone_torch", Blocks.unlit_redstone_torch);
		if (Block.blockRegistry.getObjectById( 83) == null) Block.blockRegistry.addObject( 83, "reeds", Blocks.reeds);
		if (Block.blockRegistry.getObjectById( 92) == null) Block.blockRegistry.addObject( 92, "cake", Blocks.cake);
		if (Block.blockRegistry.getObjectById( 93) == null) Block.blockRegistry.addObject( 93, "unpowered_repeater", Blocks.unpowered_repeater);
		if (Block.blockRegistry.getObjectById( 94) == null) Block.blockRegistry.addObject( 94, "powered_repeater", Blocks.powered_repeater);
		if (Block.blockRegistry.getObjectById(104) == null) Block.blockRegistry.addObject(104, "pumpkin_stem", Blocks.pumpkin_stem);
		if (Block.blockRegistry.getObjectById(105) == null) Block.blockRegistry.addObject(105, "melon_stem", Blocks.melon_stem);
		if (Block.blockRegistry.getObjectById(115) == null) Block.blockRegistry.addObject(115, "nether_wart", Blocks.nether_wart);
		if (Block.blockRegistry.getObjectById(117) == null) Block.blockRegistry.addObject(117, "brewing_stand", Blocks.brewing_stand);
		if (Block.blockRegistry.getObjectById(118) == null) Block.blockRegistry.addObject(118, "cauldron", Blocks.cauldron);
		if (Block.blockRegistry.getObjectById(124) == null) Block.blockRegistry.addObject(124, "lit_redstone_lamp", Blocks.lit_redstone_lamp);
		if (Block.blockRegistry.getObjectById(132) == null) Block.blockRegistry.addObject(132, "tripwire", Blocks.tripwire);
		if (Block.blockRegistry.getObjectById(140) == null) Block.blockRegistry.addObject(140, "flower_pot", Blocks.flower_pot);
		if (Block.blockRegistry.getObjectById(144) == null) Block.blockRegistry.addObject(144, "skull", Blocks.skull);
		if (Block.blockRegistry.getObjectById(149) == null) Block.blockRegistry.addObject(149, "unpowered_comparator", Blocks.unpowered_comparator);
		if (Block.blockRegistry.getObjectById(150) == null) Block.blockRegistry.addObject(150, "powered_comparator", Blocks.powered_comparator);
		
		OUT.println(getModNameForLog() + ": Remapping ItemStackMaps due to ID Map change. Those damn Items should have a consistent Hashcode, but noooo, ofcourse they break Basic Code Conventions! Thanks Forge and Mojang!");
		
		for (Map<ItemStackContainer, ?> tMap : STACKMAPS) UT.Code.reMap(tMap);
		for (ICompat tCompat : ICompat.COMPAT_CLASSES) try {tCompat.onIDChanging(aEvent);} catch(Throwable e) {e.printStackTrace(ERR);}
	}
}
