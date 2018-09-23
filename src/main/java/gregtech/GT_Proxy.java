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

package gregtech;

import static gregapi.data.CS.*;

import java.net.URL;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import gregapi.GT_API;
import gregapi.api.Abstract_Mod;
import gregapi.api.Abstract_Proxy;
import gregapi.block.IBlockToolable;
import gregapi.block.metatype.BlockStones;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.config.ConfigCategories;
import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.CS.SFX;
import gregapi.data.CS.ToolsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.CR;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregtech.blocks.fluids.BlockWaterlike;
import gregtech.entities.Override_Drops;
import gregtech.entities.projectiles.EntityArrow_Material;
import gregtech.tileentity.misc.MultiTileEntityCertificate;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fluids.Fluid;

public abstract class GT_Proxy extends Abstract_Proxy {
	public final HashSetNoNulls<String> mSupporterListSilver = new HashSetNoNulls<>();
	public final HashSetNoNulls<String> mSupporterListGold = new HashSetNoNulls<>();
	
	public String mMessage = "";
	
	public boolean mDisableVanillaOres = T, mDisableIC2Ores = T, mIncreaseDungeonLoot = T, mNerfedVanillaTools = T, mVersionOutdated = F;
	public int mSkeletonsShootGTArrows = 16, mFlintChance = 30;
	
	public GT_Proxy() {
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.ORE_GEN_BUS.register(this);
		MinecraftForge.TERRAIN_GEN_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@Override
	public void onProxyBeforePreInit(Abstract_Mod aMod, FMLPreInitializationEvent aEvent) {
		super.onProxyBeforePreInit(aMod, aEvent);
		new Thread(new Runnable() {@Override public void run() {
//      String[]
//      tSupporterListGoldDefault = new String[] {"Bear989Sr", "ElectroBot", "Ilirith", "Ngar", "Vash505", "stephen_2015", "Totilus", "mrgreenacid", "Asturrial", "DarkYuan", "tyra_oa", "seregheru", "Sovereignty89", "TheWorstPHO", "e99999", "PrivateDijon", "djflippy", "TOFUFreak", "SweetyLizard", "repo_alt", "ihategravel22", "WindowsBunny", "abestone2", "crepes_r_us", "adamcirillo", "Buuz135", "123mcprorot123", "Schlaibi", "MarconosII", "k0jul", "Trilexcom", "laurynasl", "Axlegear", "mtimmerije", "FPSaddiction", "Yabdat", "Goshen", "InsaneyHaney", "KrotanHill", "buizerd007", "Lehran", "GrandKaiser", "kei_kouma", "Mehrin", "leagris", "BloodyAsp", "kehaan", "Mine_Sasha", "DarthUmbris"},
//      tSupporterListSilverDefault = new String[] {"Bear989jr", "ultrasn0wz", "NanoHeart_", "Briareos1981", "XxinsanityxX", "Bladezz88", "Spungebubb", "estebes", "cmclouser", "ArchonCerulean", "Ray_CZ", "stepgoku", "phone1246", "msmilkshake", "Xyic0re", "FenixElite", "Nohicom", "pitchcherry", "MatthieuLeDieu", "Nicholas_Manuel", "Stijn_A", "negersvend", "jorstar", "Ralacroix", "ManuCortex", "Raganork", "TexanMD", "Morehatz", "MiniKatalyst", "Thanatos_00", "Goshen_Ithilien", "TheSkera", "LuxusDarkangel", "Ashleee", "Pit_of_Darkness", "DoughnutDev", "GeekTechMedia", "Heph", "Mileaos2", "CodingWithClass", "UltraPeeks", "boredi", "Lushiita", "Moothox", "fry_lad", "cdaser", "renadi", "hanakocz", "GeoStyx", "Beardedflea", "MysteryDump", "Flaver4", "x_Fame", "Azuxul", "manf", "Bimgo", "leagris", "IAmMinecrafter02", "Cerous", "Devilin_Pixy", "Bkarlsson87", "BadAlchemy", "CaballoCraft", "melanclock", "Resursator", "demanzke", "AndrewAmmerlaan", "Deathlycraft", "Jirajha", "Axlegear", "kei_kouma", "Dracion", "dungi", "Dorfschwein", "Zero Tw0", "mattiagraz85", "sebastiank30", "Plem", "invultri", "grillo126", "malcanteth", "Malevolence_", "Nicholas_Manuel", "Sirbab", "kehaan", "bpgames123", "semig0d", "9000bowser", "Sovereignty89", "Kris1432", "xander_cage_", "XanderT", "samuraijp", "bsaa", "SpwnX", "tworf", "Kadah", "kanni", "Stute", "Hegik", "Onlyme", "t3hero", "Hotchi", "jagoly", "Nullav"/*, "nallar"*/, "BH5432", "Sibmer", "inceee", "foxxx0", "Hartok", "TMSama", "Shlnen", "Carsso", "zessirb", "meep310", "Seldron", "yttr1um", "hohounk", "freebug", "Sylphio", "jmarler", "Saberawr", "r00teniy", "Neonbeta", "yinscape", "voooon24", "Quintine", "peach774", "lepthymo", "bildeman", "Kremnari", "Aerosalo", "OndraSter", "oscares91", "crdl_pls", "Daxx367x2", "EGERTRONx", "aka13_404", "Abouttabs", "Johnstaal", "djshiny99", "megatronp", "DZCreeper", "Kane_Hart", "Truculent", "vidplace7", "simon6689", "MomoNasty", "UnknownXLV", "goreacraft", "Fluttermine", "Daddy_Cecil", "MrMaleficus", "TigersFangs", "cublikefoot", "chainman564", "NikitaBuker", "Misha999777", "25FiveDetail", "AntiCivilBoy", "michaelbrady"/*, "xXxIceFirexXx"*/, "Asutoro", "Speedynutty68", "GarretSidzaka", "HallowCharm977", "mastermind1919", "The_Hypersonic", "diamondguy2798", "zF4ll3nPr3d4t0r", "CrafterOfMines57", "XxELIT3xSNIP3RxX", "SuterusuKusanagi", "xavier0014", "Ultimaheart4", "Ultimabunny4", "adamros", "alexbegt"};
		
		if (ConfigsGT.CLIENT.get(ConfigCategories.news, "version_checker", T)) {
			try {
				OUT.println("GT_DL_Thread: Downloading Version Number of the latest Major Version.");
				Scanner tScanner = new Scanner(new URL("http://gregtech.overminddl1.com/com/gregoriust/gregtech/gregtech_1.7.10/version.txt").openStream());
				if (tScanner.hasNextLine()) {
					String tVersion = tScanner.nextLine();
					while (tScanner.hasNextLine()) tVersion += tScanner.nextLine();
					mVersionOutdated = !tVersion.contains(GT_Mod.MAJOR_VERSION);
					OUT.println("GT_DL_Thread: Current Version = '" + GT_Mod.MAJOR_VERSION + "'; Recent Version = '" + tVersion + "'; Outdated = " + (mVersionOutdated?"Yes":"No"));
				}
				tScanner.close();
			} catch(Throwable e) {
				OUT.println("GT_DL_Thread: Failed Version Number of the latest Major Version!");
			}
		}
		
		OUT.println("GT_DL_Thread: Downloading Silver Supporter List.");
		if (downloadSupporterListSilverFromMain()) {
			OUT.println("GT_DL_Thread: Success downloading Silver Supporter List!");
		} else {
			OUT.println("GT_DL_Thread: Failed downloading Silver Supporter List, using interal List!");
			try {
				Scanner tScanner = new Scanner(getClass().getResourceAsStream("/supporterlist.txt"));
				while (tScanner.hasNextLine()) mSupporterListSilver.add(tScanner.nextLine().toLowerCase());
				tScanner.close();
			} catch(Throwable e) {e.printStackTrace(DEB);}
		}
		
		OUT.println("GT_DL_Thread: Downloading Gold Supporter List.");
		if (downloadSupporterListGoldFromMain()) {
			OUT.println("GT_DL_Thread: Success downloading Gold Supporter List!");
		} else {
			OUT.println("GT_DL_Thread: Failed downloading Gold Supporter List, using interal List!");
			try {
				Scanner tScanner = new Scanner(getClass().getResourceAsStream("/supporterlistgold.txt"));
				while (tScanner.hasNextLine()) mSupporterListGold.add(tScanner.nextLine().toLowerCase());
				tScanner.close();
			} catch(Throwable e) {e.printStackTrace(DEB);}
		}
		
		try {
			OUT.println("GT_DL_Thread: Downloading News.");
			Scanner tScanner = new Scanner(new URL("http://gregtech.overminddl1.com/com/gregoriust/gregtech/message.txt").openStream());
			while (tScanner.hasNextLine()) mMessage += tScanner.nextLine() + " ";
			tScanner.close();
		} catch(Throwable e) {
			OUT.println("GT_DL_Thread: Failed downloading News!");
		}
		
		mSupporterListSilver.removeAll(mSupporterListGold);
		}}).start();
	}
	
	@Override
	public void onProxyBeforePostInit(Abstract_Mod aMod, FMLPostInitializationEvent aEvent) {
		if (mIncreaseDungeonLoot) {
			OUT.println("GT_Mod: Increasing general amount of Loot in Dungeon Chests and alike");
			ChestGenHooks tChest;
			tChest = ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST                ); tChest.setMax(tChest.getMax()+ 8); tChest.setMin(tChest.getMin()+ 4);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST              ); tChest.setMax(tChest.getMax()+12); tChest.setMin(tChest.getMin()+ 6);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST       ); tChest.setMax(tChest.getMax()+ 8); tChest.setMin(tChest.getMin()+ 4);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST       ); tChest.setMax(tChest.getMax()+16); tChest.setMin(tChest.getMin()+ 8);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER   ); tChest.setMax(tChest.getMax()+ 2); tChest.setMin(tChest.getMin()+ 1);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR         ); tChest.setMax(tChest.getMax()+ 4); tChest.setMin(tChest.getMin()+ 2);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH         ); tChest.setMax(tChest.getMax()+12); tChest.setMin(tChest.getMin()+ 6);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING        ); tChest.setMax(tChest.getMax()+ 8); tChest.setMin(tChest.getMin()+ 4);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR        ); tChest.setMax(tChest.getMax()+ 6); tChest.setMin(tChest.getMin()+ 3);
			tChest = ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY         ); tChest.setMax(tChest.getMax()+16); tChest.setMin(tChest.getMin()+ 8);
		}
		if (mNerfedVanillaTools) {
			OUT.println("GT_Mod: Nerfing Vanilla Tool Durability");
			Items.wooden_sword.setMaxDamage(4);
			Items.wooden_pickaxe.setMaxDamage(4);
			Items.wooden_shovel.setMaxDamage(4);
			Items.wooden_axe.setMaxDamage(4);
			Items.wooden_hoe.setMaxDamage(4);
			
			Items.stone_sword.setMaxDamage(16);
			Items.stone_pickaxe.setMaxDamage(16);
			Items.stone_shovel.setMaxDamage(16);
			Items.stone_axe.setMaxDamage(16);
			Items.stone_hoe.setMaxDamage(16);
			
			Items.iron_sword.setMaxDamage(80);
			Items.iron_pickaxe.setMaxDamage(80);
			Items.iron_shovel.setMaxDamage(80);
			Items.iron_axe.setMaxDamage(80);
			Items.iron_hoe.setMaxDamage(80);
			
			Items.golden_sword.setMaxDamage(8);
			Items.golden_pickaxe.setMaxDamage(8);
			Items.golden_shovel.setMaxDamage(8);
			Items.golden_axe.setMaxDamage(8);
			Items.golden_hoe.setMaxDamage(8);
			
			Items.diamond_sword.setMaxDamage(240);
			Items.diamond_pickaxe.setMaxDamage(240);
			Items.diamond_shovel.setMaxDamage(240);
			Items.diamond_axe.setMaxDamage(240);
			Items.diamond_hoe.setMaxDamage(240);
		}
		
		OUT.println("GT_Mod: Adding Tool Usage Crafting Recipes for OreDict Items.");
		for (OreDictMaterial aMaterial : OreDictMaterial.MATERIAL_MAP.values()) if (!aMaterial.contains(TD.Properties.INVALID_MATERIAL) && aMaterial.mTargetRegistration == aMaterial) {
			long
			tBits = CR.ONLY_IF_HAS_RESULT | CR.DEF_NCC;
			CR.shaped(OP.toolHeadWrench     .mat(aMaterial                          ,  1), tBits, "hXW", "XRX", "WXd", 'X', OP.plate    .dat(aMaterial), 'S', OP.plate.dat(ANY.Steel), 'R', OP.ring.dat(ANY.Steel), 'W', OP.screw.dat(ANY.Steel));
			CR.shaped(OP.toolHeadWrench     .mat(aMaterial                          ,  1), tBits, "hXW", "XRX", "WXd", 'X', OP.plateGem .dat(aMaterial), 'S', OP.plate.dat(ANY.Steel), 'R', OP.ring.dat(ANY.Steel), 'W', OP.screw.dat(ANY.Steel));
			CR.shaped(OP.toolHeadChainsaw   .mat(aMaterial                          ,  1), tBits, "SRS", "XhX", "SRS", 'X', OP.plate    .dat(aMaterial), 'S', OP.plate.dat(ANY.Steel), 'R', OP.ring.dat(ANY.Steel));
			CR.shaped(OP.toolHeadChainsaw   .mat(aMaterial                          ,  1), tBits, "SRS", "XhX", "SRS", 'X', OP.plateGem .dat(aMaterial), 'S', OP.plate.dat(ANY.Steel), 'R', OP.ring.dat(ANY.Steel));
			CR.shaped(OP.toolHeadDrill      .mat(aMaterial                          ,  1), tBits, "XSX", "XSX", "ShS", 'X', OP.plate    .dat(aMaterial), 'S', OP.plate.dat(ANY.Steel));
			CR.shaped(OP.toolHeadDrill      .mat(aMaterial                          ,  1), tBits, "XSX", "XSX", "ShS", 'X', OP.plateGem .dat(aMaterial), 'S', OP.plate.dat(ANY.Steel));
			
			tBits = CR.ONLY_IF_HAS_RESULT | CR.DEF_NAC_NCC;
			if (!aMaterial.contains(TD.Compounds.COATED)) {
			CR.shaped(OP.gearGtSmall        .mat(aMaterial                          ,  1), tBits, "P ", aMaterial.contains(TD.Properties.WOOD)?" s":aMaterial.contains(TD.Properties.STONE)?" f":" h", 'P', OP.plate.dat(aMaterial));
			}
		}
	}
	
	
	@SubscribeEvent
	public void onClientConnectedToServerEvent(ClientConnectedToServerEvent aEvent) {
		//
	}
	
	@SubscribeEvent
	public void onEndermanTeleportEvent(EnderTeleportEvent aEvent) {
		if (aEvent.entityLiving instanceof EntityEnderman && aEvent.entityLiving.getActivePotionEffect(Potion.weakness) != null) aEvent.setCanceled(T);
	}
	
	private static final EnumSet<EventType> PREVENTED_ORES = EnumSet.of(EventType.COAL, EventType.IRON, EventType.GOLD, EventType.DIAMOND, EventType.REDSTONE, EventType.LAPIS, EventType.QUARTZ);
	
	@SubscribeEvent
	public void onOreGenEvent(GenerateMinable aEvent) {
		if (mDisableVanillaOres && aEvent.generator instanceof WorldGenMinable && PREVENTED_ORES.contains(aEvent.type)) aEvent.setResult(Result.DENY);
	}
	@SubscribeEvent
	public void onTerrainGenEvent(DecorateBiomeEvent.Decorate aEvent) {
		if (aEvent.world.provider.dimensionId == 0) {
			if (MD.RTG.mLoaded) {
				String tClassName = UT.Reflection.getLowercaseClass(aEvent.world.provider.terrainType);
				if ("WorldProviderSurfaceRTG".equalsIgnoreCase(tClassName) || "WorldTypeRTG".equalsIgnoreCase(tClassName)) return;
			}
			if (GENERATE_STREETS && (UT.Code.inside(-48, 47, aEvent.chunkX) || UT.Code.inside(-48, 47, aEvent.chunkZ))) aEvent.setResult(Result.DENY);
			if (GENERATE_BIOMES  && (UT.Code.inside(-96, 95, aEvent.chunkX) && UT.Code.inside(-96, 95, aEvent.chunkZ))) aEvent.setResult(Result.DENY);
		}
	}
	@SubscribeEvent
	public void onTerrainGenEvent(PopulateChunkEvent.Populate aEvent) {
		if (aEvent.world.provider.dimensionId == 0) {
			if (MD.RTG.mLoaded) {
				String tClassName = UT.Reflection.getLowercaseClass(aEvent.world.provider.terrainType);
				if ("WorldProviderSurfaceRTG".equalsIgnoreCase(tClassName) || "WorldTypeRTG".equalsIgnoreCase(tClassName)) return;
			}
			if (GENERATE_STREETS && (UT.Code.inside(-48, 47, aEvent.chunkX) || UT.Code.inside(-48, 47, aEvent.chunkZ))) aEvent.setResult(Result.DENY);
			if (GENERATE_BIOMES  && (UT.Code.inside(-96, 95, aEvent.chunkX) && UT.Code.inside(-96, 95, aEvent.chunkZ))) aEvent.setResult(Result.DENY);
		}
	}
	@SubscribeEvent
	public void onGetVillageBlockIDEvent(BiomeEvent.GetVillageBlockID aEvent) {
		if (aEvent.original == Blocks.cobblestone) {
			aEvent.replacement = aEvent.biome == null ? BlocksGT.Andesite : BlocksGT.stones[(aEvent.biome.biomeID+6) % BlocksGT.stones.length];
			aEvent.setResult(Result.DENY);
		}
	}
	@SubscribeEvent
	public void onGetVillageBlockMetaEvent(BiomeEvent.GetVillageBlockMeta aEvent) {
		if (aEvent.original == Blocks.cobblestone || aEvent.original instanceof BlockStones) {
			aEvent.replacement = BlockStones.SBRIK;
			aEvent.setResult(Result.DENY);
		}
		if (aEvent.original == Blocks.sandstone) {
			aEvent.replacement = 2; // That's smooth Sandstone.
			aEvent.setResult(Result.DENY);
		}
	}
	
	private static final HashSetNoNulls<String> CHECKED_PLAYERS = new HashSetNoNulls<>();
	
	@SubscribeEvent
	public void onPlayerInteraction(PlayerInteractEvent aEvent) {
		if (aEvent.entityPlayer == null || aEvent.entityPlayer.worldObj == null || aEvent.action == null || aEvent.world.provider == null) return;
		String aName = aEvent.entityPlayer.getCommandSenderName(), aNameLowercase = aName.toLowerCase();
		if (!aEvent.world.isRemote && CHECKED_PLAYERS.add(aName)) {
			if (mSupporterListSilver.contains(aEvent.entityPlayer.getUniqueID().toString()) || mSupporterListGold.contains(aEvent.entityPlayer.getUniqueID().toString()) || mSupporterListSilver.contains(aNameLowercase) || mSupporterListGold.contains(aNameLowercase)) {
				if (!MultiTileEntityCertificate.ALREADY_RECEIVED.contains(aNameLowercase)) {
					if (UT.Inventories.addStackToPlayerInventoryOrDrop(aEvent.entityPlayer, MultiTileEntityCertificate.getCertificate(1, aName), F)) {
						MultiTileEntityCertificate.ALREADY_RECEIVED.add(aNameLowercase);
						UT.Entities.sendchat(aEvent.entityPlayer, CHAT_GREG + " Thank you, " + aName + ", for Supporting GregTech! Here, have a Certificate. ;)");
					}
				}
			}
		}
		
		ItemStack aStack = aEvent.entityPlayer.getCurrentEquippedItem();
		if (aStack != null && aStack.stackSize > 0) {
			if (aEvent.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
				if (aStack.getItem() == Items.glass_bottle) {
					aEvent.setCanceled(T);
					if (aEvent.world.isRemote) {
						GT_API.api_proxy.sendUseItemPacket(aEvent.entityPlayer, aEvent.world, aStack);
						return;
					}
					
					MovingObjectPosition tTarget = WD.getMOP(aEvent.world, aEvent.entityPlayer, T);
					if (tTarget == null || tTarget.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK || !aEvent.world.canMineBlock(aEvent.entityPlayer, tTarget.blockX, tTarget.blockY, tTarget.blockZ) || !aEvent.entityPlayer.canPlayerEdit(tTarget.blockX, tTarget.blockY, tTarget.blockZ, tTarget.sideHit, aStack)) return;
					Block tBlock = aEvent.world.getBlock(tTarget.blockX, tTarget.blockY, tTarget.blockZ);
					
					if (tBlock == Blocks.water || tBlock == Blocks.flowing_water) {
						if (aEvent.world.getBlockMetadata(tTarget.blockX, tTarget.blockY, tTarget.blockZ) != 0) return;
						for (int i = 0; i < 3 && aStack.stackSize > 0; i++) {
							if (aStack.stackSize == 1) {
								aEvent.entityPlayer.inventory.mainInventory[aEvent.entityPlayer.inventory.currentItem] = ST.make(Items.potionitem, 1, 0);
							} else {
								aStack.stackSize--;
								UT.Inventories.addStackToPlayerInventoryOrDrop(aEvent.entityPlayer, ST.make(Items.potionitem, 1, 0), F);
							}
						}
						if (!WD.infiniteWater(aEvent.world, tTarget.blockX, tTarget.blockY, tTarget.blockZ)) aEvent.world.setBlockToAir(tTarget.blockX, tTarget.blockY, tTarget.blockZ);
						if (aStack.stackSize <= 0) ForgeEventFactory.onPlayerDestroyItem(aEvent.entityPlayer, aStack);
						if (aEvent.entityPlayer.openContainer != null) aEvent.entityPlayer.openContainer.detectAndSendChanges();
						return;
					}
					if (tBlock == BlocksGT.Ocean) {
						ItemStack tStack = UT.Fluids.fillFluidContainer(FL.Ocean.make(Integer.MAX_VALUE), aStack, F, T, F, T);
						if (tStack == null) return;
						if (--aStack.stackSize <= 0) ForgeEventFactory.onPlayerDestroyItem(aEvent.entityPlayer, aStack);
						UT.Inventories.addStackToPlayerInventoryOrDrop(aEvent.entityPlayer, tStack, F);
						return;
					}
					if (tBlock == BlocksGT.Swamp) {
						ItemStack tStack = UT.Fluids.fillFluidContainer(FL.Dirty_Water.make(Integer.MAX_VALUE), aStack, F, T, F, T);
						if (tStack == null) return;
						if (--aStack.stackSize <= 0) ForgeEventFactory.onPlayerDestroyItem(aEvent.entityPlayer, aStack);
						UT.Inventories.addStackToPlayerInventoryOrDrop(aEvent.entityPlayer, tStack, F);
						return;
					}
					return;
				}
				if (aStack.getItem() == Items.bucket) {
					MovingObjectPosition tTarget = WD.getMOP(aEvent.world, aEvent.entityPlayer, T);
					if (tTarget != null && tTarget.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && aEvent.world.getBlock(tTarget.blockX, tTarget.blockY, tTarget.blockZ) instanceof BlockWaterlike) aEvent.setCanceled(T);
					return;
				}
			}
			if (aEvent.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
				if (IL.ERE_Spray_Repellant.equal(aStack, T, T)) {
					if (!aEvent.world.isRemote && aStack.getItem().onItemUse(aStack, aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, aEvent.face, 0.5F, 0.5F, 0.5F)) {
						aEvent.setCanceled(T);
						UT.Inventories.addStackToPlayerInventoryOrDrop(aEvent.entityPlayer, IL.Spray_Empty.get(1), aEvent.world, aEvent.x, aEvent.y, aEvent.z);
						return;
					}
				} else if (aStack.getItem() == Items.flint_and_steel) {
					if (!aEvent.world.isRemote && !UT.Entities.hasInfiniteItems(aEvent.entityPlayer) && RNGSUS.nextInt(100) >= mFlintChance) {
						aEvent.setCanceled(T);
						aStack.damageItem(1, aEvent.entityPlayer);
						if (aStack.getItemDamage() >= aStack.getMaxDamage()) aStack.stackSize--;
						if (aStack.stackSize <= 0) ForgeEventFactory.onPlayerDestroyItem(aEvent.entityPlayer, aStack);
						return;
					}
					List<String> tChatReturn = new ArrayListNoNulls<>();
					long tDamage = IBlockToolable.Util.onToolClick(TOOL_igniter, aStack.getItemDamage()*10000, 1, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.world, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
					UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
					if (tDamage > 0) {
						aEvent.setCanceled(T);
						aStack.damageItem(UT.Code.bindInt(UT.Code.units(tDamage, 10000, 1, T)), aEvent.entityPlayer);
						UT.Sounds.send(aEvent.world, SFX.MC_IGNITE, 1.0F, 1.0F, aEvent.x, aEvent.y, aEvent.z);
						if (aStack.getItemDamage() >= aStack.getMaxDamage()) aStack.stackSize--;
						if (aStack.stackSize <= 0) ForgeEventFactory.onPlayerDestroyItem(aEvent.entityPlayer, aStack);
						return;
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onEntitySpawningEvent(EntityJoinWorldEvent aEvent) {
		if (aEvent.entity != null && !aEvent.entity.worldObj.isRemote) {
			if (mSkeletonsShootGTArrows > 0 && aEvent.entity.getClass() == EntityArrow.class && RNGSUS.nextInt(mSkeletonsShootGTArrows) == 0) {
				if (((EntityArrow)aEvent.entity).shootingEntity instanceof EntitySkeleton) {
					aEvent.entity.worldObj.spawnEntityInWorld(new EntityArrow_Material((EntityArrow)aEvent.entity, new ArrayListNoNulls<>(OP.arrowGtWood.mRegisteredItems).get(RNGSUS).toStack()));
					aEvent.entity.setDead();
				}
			}
		}
	}

// Not gonna do that one due to exploitiness.
//  @SubscribeEvent
//  public void onItemExpireEvent(ItemExpireEvent aEvent) {
//      ItemStack aStack = aEvent.entityItem.getEntityItem();
//      if (aStack != null) {
//          if (aStack.stackSize <= 0) {aEvent.entityItem.setDead(); return;}
//          
//          if (!aEvent.entityItem.worldObj.isRemote && aEvent.entityItem.onGround) {
//              if (aStack.getItem() == Items.stick) {
//                  MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
//                  for (byte tSide : ALL_SIDES_MIDDLE_DOWN) {
//                      if (aEvent.entityItem.worldObj.canPlaceEntityOnSide(tRegistry.mBlock, MathHelper.floor_double(aEvent.entityItem.posX)+OFFSETS_X[tSide], MathHelper.floor_double(aEvent.entityItem.posY)+OFFSETS_Y[tSide], MathHelper.floor_double(aEvent.entityItem.posZ)+OFFSETS_Z[tSide], F, SIDE_TOP, aEvent.entityItem, aStack)) {
//                          if (tRegistry.mBlock.placeBlock(aEvent.entityItem.worldObj, MathHelper.floor_double(aEvent.entityItem.posX)+OFFSETS_X[tSide], MathHelper.floor_double(aEvent.entityItem.posY)+OFFSETS_Y[tSide], MathHelper.floor_double(aEvent.entityItem.posZ)+OFFSETS_Z[tSide], SIDE_UNKNOWN, (short)32756, null, T, F)) {
//                              aStack.stackSize--;
//                              if (aStack.stackSize <= 0) {aEvent.entityItem.setDead(); return;}
//                          }
//                      }
//                  }
//                  aEvent.entityItem.setEntityItemStack(aStack);
//                  aEvent.extraLife = 200;
//                  aEvent.setCanceled(T);
//                  return;
//              }
//              OreDictItemData tData = OM.data(aStack);
//              if (tData != null && tData.mPrefix == OP.rockGt) {
//                  MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
//                  for (byte tSide : ALL_SIDES_MIDDLE_DOWN) {
//                      if (aEvent.entityItem.worldObj.canPlaceEntityOnSide(tRegistry.mBlock, MathHelper.floor_double(aEvent.entityItem.posX)+OFFSETS_X[tSide], MathHelper.floor_double(aEvent.entityItem.posY)+OFFSETS_Y[tSide], MathHelper.floor_double(aEvent.entityItem.posZ)+OFFSETS_Z[tSide], F, SIDE_TOP, aEvent.entityItem, aStack)) {
//                          if (tRegistry.mBlock.placeBlock(aEvent.entityItem.worldObj, MathHelper.floor_double(aEvent.entityItem.posX)+OFFSETS_X[tSide], MathHelper.floor_double(aEvent.entityItem.posY)+OFFSETS_Y[tSide], MathHelper.floor_double(aEvent.entityItem.posZ)+OFFSETS_Z[tSide], SIDE_UNKNOWN, (short)32757, ST.save(null, NBT_VALUE, aStack), T, F)) {
//                              aStack.stackSize = 0;
//                              aEvent.entityItem.setDead();
//                              return;
//                          }
//                      }
//                  }
//                  aEvent.entityItem.setEntityItemStack(aStack);
//                  aEvent.extraLife = 200;
//                  aEvent.setCanceled(T);
//                  return;
//              }
//          }
//      }
//  }
	
	@SubscribeEvent
	public void onEntityLivingDropsEventEvent(LivingDropsEvent aEvent) {
		if (aEvent.entity.worldObj.isRemote || aEvent.entity instanceof EntityPlayer || aEvent.entityLiving == null) return;
		Override_Drops.handleDrops(aEvent.entityLiving, UT.Reflection.getLowercaseClass(aEvent.entityLiving), aEvent.drops, aEvent.lootingLevel, aEvent.entityLiving.isBurning(), aEvent.recentlyHit);
	}
	
	@SubscribeEvent
	public void onEntityLivingFallEvent(LivingFallEvent aEvent) {
		if (!aEvent.entity.worldObj.isRemote && aEvent.entity instanceof EntityPlayer) {
			if (ST.equal(((EntityPlayer)aEvent.entity).getCurrentEquippedItem(), ST.make(ToolsGT.sMetaTool, 1, ToolsGT.SCISSORS), T) || ST.equal(((EntityPlayer)aEvent.entity).getCurrentEquippedItem(), ST.make(ToolsGT.sMetaTool, 1, ToolsGT.POCKET_SCISSORS), T)) aEvent.distance *= 2;
		}
	}
	
	public ArrayListNoNulls<EntityOcelot> mOcelots = new ArrayListNoNulls<>();
	
	@SubscribeEvent
	public void onEntityConstructingEvent(EntityConstructing aEvent) {
		if (Abstract_Mod.sFinalized < Abstract_Mod.sModCountUsingGTAPI) return;
		if (aEvent.entity instanceof EntityOcelot) mOcelots.add(((EntityOcelot)aEvent.entity));
	}
	
	@SubscribeEvent
	public void onServerTickEvent(ServerTickEvent aEvent) {
		if (aEvent.side.isServer() && aEvent.phase == Phase.START && SERVER_TIME > 20) {
			try {
				Iterator<EntityOcelot> tIterator = mOcelots.iterator();
				while (tIterator.hasNext()) {
					EntityOcelot tOcelot = tIterator.next();
					if (tOcelot != null && tOcelot.tasks != null) tOcelot.tasks.addTask(3, new EntityAITempt(tOcelot, 0.6D, ItemsGT.CANS, T));
					tIterator.remove();
				}
				mOcelots.clear();
			} catch(Throwable e) {
				e.printStackTrace(ERR);
			}
		}
	}
	
	@SafeVarargs public final Fluid addAutogeneratedLiquid(OreDictMaterial aMaterial, Set<String>... aFluidList) {return UT.Fluids.createLiquid(aMaterial, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedLiquid(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return UT.Fluids.createPlasma(aMaterial, aTexture, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedGas(OreDictMaterial aMaterial, Set<String>... aFluidList) {return UT.Fluids.createGas(aMaterial, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedGas(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return UT.Fluids.createGas(aMaterial, aTexture, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedMolten(OreDictMaterial aMaterial, Set<String>... aFluidList) {return UT.Fluids.createMolten(aMaterial, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedMolten(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return UT.Fluids.createMolten(aMaterial, aTexture, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedVapor(OreDictMaterial aMaterial, Set<String>... aFluidList) {return UT.Fluids.createVapour(aMaterial, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedVaporized(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return UT.Fluids.createVapour(aMaterial, aTexture, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedPlasma(OreDictMaterial aMaterial, Set<String>... aFluidList) {return UT.Fluids.createPlasma(aMaterial, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedPlasma(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return UT.Fluids.createPlasma(aMaterial, aTexture, aFluidList);}
	@SafeVarargs public final Fluid addFluid(String aName, String aLocalized, OreDictMaterial aMaterial, int aState, long aAmountPerUnit, long aTemperatureK, Set<String>... aFluidList) {return UT.Fluids.create(aName, aLocalized, aMaterial, aState, aAmountPerUnit, aTemperatureK, aFluidList);}    
	@SafeVarargs public final Fluid addFluid(String aName, String aLocalized, OreDictMaterial aMaterial, int aState, long aAmountPerUnit, long aTemperatureK, ItemStack aFullContainer, ItemStack aEmptyContainer, int aFluidAmount, Set<String>... aFluidList) {return UT.Fluids.create(aName, aLocalized, aMaterial, aState, aAmountPerUnit, aTemperatureK, aFullContainer, aEmptyContainer, aFluidAmount, aFluidList);}
	@SafeVarargs public final Fluid addFluid(String aName, IIconContainer aTexture, String aLocalized, OreDictMaterial aMaterial, short[] aRGBa, int aState, long aAmountPerUnit, long aTemperatureK, ItemStack aFullContainer, ItemStack aEmptyContainer, int aFluidAmount, Set<String>... aFluidList) {return UT.Fluids.create(aName, aTexture, aLocalized, aMaterial, aRGBa, aState, aAmountPerUnit, aTemperatureK, aFullContainer, aEmptyContainer, aFluidAmount, aFluidList);}
	
	public boolean downloadSupporterListSilverFromMain() {
		try {
			Scanner tScanner = new Scanner(new URL("http://gregtech.overminddl1.com/com/gregoriust/gregtech/supporterlist.txt").openStream());
			while (tScanner.hasNextLine()) mSupporterListSilver.add(tScanner.nextLine().toLowerCase());
			tScanner.close();
			return mSupporterListSilver.size() > 3;
		} catch(Throwable e) {e.printStackTrace(DEB);}
		return F;
	}
	
	public boolean downloadSupporterListGoldFromMain() {
		try {
			Scanner tScanner = new Scanner(new URL("http://gregtech.overminddl1.com/com/gregoriust/gregtech/supporterlistgold.txt").openStream());
			while (tScanner.hasNextLine()) mSupporterListGold.add(tScanner.nextLine().toLowerCase());
			tScanner.close();
			return mSupporterListGold.size() > 3;
		} catch(Throwable e) {e.printStackTrace(DEB);}
		return F;
	}
}
