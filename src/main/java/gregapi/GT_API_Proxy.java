/**
 * Copyright (c) 2022 GregTech-6 Team
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

import cofh.lib.util.ComparableItem;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import ganymedes01.etfuturum.entities.EntityHusk;
import ganymedes01.etfuturum.entities.EntityStray;
import ganymedes01.etfuturum.entities.EntityZombieVillager;
import ganymedes01.etfuturum.recipes.BlastFurnaceRecipes;
import ganymedes01.etfuturum.recipes.SmokerRecipes;
import gregapi.api.Abstract_Mod;
import gregapi.api.Abstract_Proxy;
import gregapi.block.*;
import gregapi.block.metatype.BlockBasePlanks;
import gregapi.block.misc.BlockBaseBale;
import gregapi.block.multitileentity.MultiTileEntityItemInternal;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.block.prefixblock.PrefixBlockTileEntity;
import gregapi.block.tree.BlockBaseBeam;
import gregapi.block.tree.BlockBaseLog;
import gregapi.block.tree.BlockBaseSapling;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.data.*;
import gregapi.enchants.Enchantment_WerewolfDamage;
import gregapi.item.IItemNoGTOverride;
import gregapi.item.IItemProjectile;
import gregapi.item.IItemProjectile.EntityProjectile;
import gregapi.item.IItemRottable.RottingUtil;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.multiitem.tools.IToolStats;
import gregapi.network.packets.PacketConfig;
import gregapi.network.packets.PacketDeathPoint;
import gregapi.network.packets.PacketPrefix;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.OreDictPrefix;
import gregapi.oredict.listeners.IOreDictListenerItem;
import gregapi.player.EntityFoodTracker;
import gregapi.random.IHasWorldAndCoords;
import gregapi.tileentity.*;
import gregapi.tileentity.inventories.ITileEntityBookShelf;
import gregapi.util.*;
import gregapi.worldgen.GT6WorldGenerator;
import gregtech.items.behaviors.Behavior_Gun;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.BlockJukebox.TileEntityJukebox;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import thaumcraft.common.entities.monster.EntityBrainyZombie;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class GT_API_Proxy extends Abstract_Proxy implements IGuiHandler, IFuelHandler, IWorldGenerator {
	public GT_API_Proxy() {
		GameRegistry.registerFuelHandler(this);
		GameRegistry.registerWorldGenerator(this, Integer.MAX_VALUE);
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
	}
	
	public int addArmor(String aPrefix) {
		return 0;
	}
	
	public EntityPlayer getThePlayer() {
		return null;
	}
	
	public boolean sendUseItemPacket(EntityPlayer aPlayer, World aWorld, ItemStack aStack) {
		return F;
	}
	
	@Override
	public Object getServerGuiElement(int aGUIID, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
		TileEntity tTileEntity = WD.te(aWorld, aX, aY, aZ, T);
		return tTileEntity instanceof ITileEntityGUI ? ((ITileEntityGUI)tTileEntity).getGUIServer(aGUIID, aPlayer) : null;
	}
	
	@Override
	public Object getClientGuiElement(int aGUIID, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
		TileEntity tTileEntity = WD.te(aWorld, aX, aY, aZ, T);
		return tTileEntity instanceof ITileEntityGUI ? ((ITileEntityGUI)tTileEntity).getGUIClient(aGUIID, aPlayer) : null;
	}
	
	private File mSaveLocation = null;
	
	/**
	 * Check if the Save Location passed in matches the current Save Location.
	 */
	public boolean checkSaveLocation(File aSaveLocation, boolean aSaveTheWorldIfNull) {
		boolean tSave = F, tLoad = F;
		if (aSaveLocation == null) {
			tSave = (aSaveTheWorldIfNull && mSaveLocation != null);
		} else if (mSaveLocation == null) {
			tLoad = T;
		} else {
			tSave = tLoad = !mSaveLocation.equals(aSaveLocation);
		}
		
		if (tSave) {
			OUT.println("Saving  World! " + mSaveLocation);
			new File(mSaveLocation, "gregtech").mkdirs();
			GarbageGT.onServerSave(mSaveLocation);
			MultiTileEntityRegistry.onServerSave(mSaveLocation);
		}
		if (tLoad) {
			OUT.println("Loading World! " + aSaveLocation);
			mSaveLocation = aSaveLocation;
			new File(mSaveLocation, "gregtech").mkdirs();
			GarbageGT.onServerLoad(mSaveLocation);
			MultiTileEntityRegistry.onServerLoad(mSaveLocation);
		}
		return tSave || tLoad;
	}
	
	@Override
	public void onProxyBeforeServerStarted(Abstract_Mod aMod, FMLServerStartedEvent aEvent) {
		SERVER_TIME = 0;
		MultiTileEntityRegistry.onServerStart();
	}
	
	@Override
	public void onProxyAfterServerStopping(Abstract_Mod aMod, FMLServerStoppingEvent aEvent) {
		checkSaveLocation(null, T);
		MultiTileEntityRegistry.onServerStop();
	}
	
	@SubscribeEvent public void onWorldSave  (WorldEvent.Save   aEvent) {checkSaveLocation(aEvent.world.getSaveHandler().getWorldDirectory(), F);}
	@SubscribeEvent public void onWorldLoad  (WorldEvent.Load   aEvent) {checkSaveLocation(aEvent.world.getSaveHandler().getWorldDirectory(), F);}
	@SubscribeEvent public void onWorldUnload(WorldEvent.Unload aEvent) {checkSaveLocation(aEvent.world.getSaveHandler().getWorldDirectory(), F);}
	
	public  static final List<ITileEntityServerTickPre  > SERVER_TICK_PRE                = new ArrayListNoNulls<>(), SERVER_TICK_PR2  = new ArrayListNoNulls<>();
	public  static final List<ITileEntityServerTickPost > SERVER_TICK_POST               = new ArrayListNoNulls<>(), SERVER_TICK_PO2T = new ArrayListNoNulls<>();
	public  static       List<IHasWorldAndCoords>         DELAYED_BLOCK_UPDATES          = new ArrayListNoNulls<>();
	private static       List<IHasWorldAndCoords>         DELAYED_BLOCK_UPDATES_2        = new ArrayListNoNulls<>();
	public  static       List<ITileEntityScheduledUpdate> SCHEDULED_TILEENTITY_UPDATES   = new ArrayListNoNulls<>();
	private static       List<ITileEntityScheduledUpdate> SCHEDULED_TILEENTITY_UPDATES_2 = new ArrayListNoNulls<>();
	
	@SubscribeEvent
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void onServerTick(ServerTickEvent aEvent) {
		if (aEvent.side.isServer()) {
			// Try acquiring the Lock within 10 Milliseconds. Otherwise fuck anyone who locks it up for too long, or any other faulty reason MC doesn't work.
			try {TICK_LOCK.tryLock(10, TimeUnit.MILLISECONDS);} catch (Throwable e) {e.printStackTrace(ERR);} finally {if (TICK_LOCK.isHeldByCurrentThread()) TICK_LOCK.unlock();}
			
			// Making sure it is being free'd up in order to prevent exploits or Garbage Collection mishaps.
			LAST_BROKEN_TILEENTITY.set(null);
			
			if (aEvent.phase == Phase.START) {
				SYNC_SECOND = (SERVER_TIME % 20 == 0);
				
				if (SERVER_TIME++ == 0) {
					// Unification Stuff
					HashSetNoNulls<ItemStack> tStacks = new HashSetNoNulls<>(10000);
					
					if (MD.IC2.mLoaded) try {
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.cannerBottle        .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.centrifuge          .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.compressor          .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.extractor           .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.macerator           .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.metalformerCutting  .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.metalformerExtruding.getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.metalformerRolling  .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.matterAmplifier     .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.oreWashing          .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					} catch(Throwable e) {e.printStackTrace(ERR);}
					
					if (MD.RC.mLoaded) {
					try {for (Object  tRecipe : mods.railcraft.api.crafting.RailcraftCraftingManager.blastFurnace  .getRecipes   ()) tStacks.add((ItemStack)UT.Reflection.getFieldContent(tRecipe, "output"));} catch(Throwable e) {e.printStackTrace(ERR);}
					try {for (Object  tRecipe : mods.railcraft.api.crafting.RailcraftCraftingManager.cokeOven      .getRecipes   ()) tStacks.add((ItemStack)UT.Reflection.getFieldContent(tRecipe, "output"));} catch(Throwable e) {e.printStackTrace(ERR);}
					try {for (Object  tRecipe : mods.railcraft.api.crafting.RailcraftCraftingManager.rockCrusher   .getRecipes   ()) for (Map.Entry<ItemStack, Float> tEntry : (List<Map.Entry<ItemStack, Float>>)UT.Reflection.getFieldContent(tRecipe, "outputs")) tStacks.add(tEntry.getKey());} catch(Throwable e) {e.printStackTrace(ERR);}
					try {for (IRecipe tRecipe : mods.railcraft.api.crafting.RailcraftCraftingManager.rollingMachine.getRecipeList()) if (tRecipe != null) tStacks.add(tRecipe.getRecipeOutput());} catch(Throwable e) {e.printStackTrace(ERR);}
					}
					
					if (MD.TE.mLoaded && ALWAYS_FALSE) {
						List<Map> tMaps = new ArrayListNoNulls<>();
						List<Set> tSets = new ArrayListNoNulls<>();
						
						for (String tClassName : new String[] {"cofh.thermalexpansion.util.crafting.InsolatorManager", "cofh.thermalexpansion.util.crafting.ChargerManager", "cofh.thermalexpansion.util.crafting.ExtruderManager", "cofh.thermalexpansion.util.crafting.PrecipitatorManager", "cofh.thermalexpansion.util.crafting.TransposerManager", "cofh.thermalexpansion.util.crafting.CrucibleManager", "cofh.thermalexpansion.util.crafting.SmelterManager", "cofh.thermalexpansion.util.crafting.SawmillManager", "cofh.thermalexpansion.util.crafting.PulverizerManager", "cofh.thermalexpansion.util.crafting.FurnaceManager"}) {try {
							Class tClass = Class.forName(tClassName);
							Object
							tObject = UT.Reflection.getFieldContent(tClass, "recipeMap", T, F);
							if (tObject instanceof Map) tMaps.add((Map)tObject);
							tObject = UT.Reflection.getFieldContent(tClass, "recipeMapFill", T, F);
							if (tObject instanceof Map) tMaps.add((Map)tObject);
							tObject = UT.Reflection.getFieldContent(tClass, "recipeMapExtraction", T, F);
							if (tObject instanceof Map) tMaps.add((Map)tObject);
							tObject = UT.Reflection.getFieldContent(tClass, "validationSet", T, F);
							if (tObject instanceof Set) tSets.add((Set)tObject);
							tObject = UT.Reflection.getFieldContent(tClass, "lockSet", T, F);
							if (tObject instanceof Set) tSets.add((Set)tObject);
						} catch(Throwable e) {e.printStackTrace(ERR);}}
						
						for (Map tMap : tMaps) {
							try {for (Object tCompStack : tMap.keySet()) if (tCompStack instanceof ComparableItem) {
								ItemStack tStack = OM.get(ST.make(((ComparableItem)tCompStack).item, 1, ((ComparableItem)tCompStack).metadata));
								if (ST.valid(tStack)) {
									((ComparableItem)tCompStack).item     = ST.item_(tStack);
									((ComparableItem)tCompStack).metadata = ST.meta_(tStack);
								}
							}} catch(Throwable e) {e.printStackTrace(ERR);}
							UT.Code.reMap(tMap);
						}
						
						for (Set tSet : tSets) {
							try {for (Object tCompStack : tSet) if (tCompStack instanceof ComparableItem) {
								ItemStack tStack = OM.get(ST.make(((ComparableItem)tCompStack).item, 1, ((ComparableItem)tCompStack).metadata));
								if (ST.valid(tStack)) {
									((ComparableItem)tCompStack).item     = ST.item_(tStack);
									((ComparableItem)tCompStack).metadata = ST.meta_(tStack);
								}
							}} catch(Throwable e) {e.printStackTrace(ERR);}
							UT.Code.reMap(tSet);
						}
					}
					
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST           ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST             ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH      ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING     ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY      ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR     ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST    ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST    ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR      ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					
					if (MD.IE.mLoaded) try {
						for (WeightedRandomChestContent tContent : ((ChestGenHooks)UT.Reflection.getFieldContent("blusunrize.immersiveengineering.common.world.VillageEngineersHouse", "crateContents")).getItems(RNGSUS)) {
							if (OM.is("ingotAluminium", tContent.theItemId)) {
								ST.set(tContent.theItemId, OP.ingot.mat(MT.Constantan, 1));
							} else {
								tStacks.add(tContent.theItemId);
							}
						}
					} catch(Throwable e) {
						e.printStackTrace(ERR);
					}
					
					for (Object tStack : FurnaceRecipes.smelting().getSmeltingList().values()) tStacks.add((ItemStack)tStack);
					
					if (MD.EtFu.mLoaded) try {
						for (Object tStack : SmokerRecipes      .smelting().getSmeltingList().values()) tStacks.add((ItemStack)tStack);
						for (Object tStack : BlastFurnaceRecipes.smelting().getSmeltingList().values()) tStacks.add((ItemStack)tStack);
					} catch(Throwable e) {e.printStackTrace(ERR);}
					
					for (IRecipe tRecipe : CR.list()) if (tRecipe != null) tStacks.add(tRecipe.getRecipeOutput());
					
					for (ItemStack tOutput : tStacks) {
						if (OreDictManager.INSTANCE.isOreDictItem(tOutput)) {
							ERR.println("GT-ERR-01: @ " + tOutput.getUnlocalizedName() + "   " + tOutput.getDisplayName());
							FMLLog.severe("GT-ERR-01: @ " + tOutput.getUnlocalizedName() + "   " + tOutput.getDisplayName());
							if (CS.CODE_CLIENT) {
								FMLLog.severe("A Recipe used an OreDict Item as Output directly, without copying it before!!! This is a typical CallByReference/CallByValue Error");
								FMLLog.severe("Said Item will be renamed to make the invalid Recipe visible, so that you can report it properly.");
								FMLLog.severe("Please check all Recipes outputting this Item, and report the Recipes to their Owner.");
								FMLLog.severe("The Owner of the ==>RECIPE<==, NOT the Owner of the Item, which has been mentioned above!!!");
								FMLLog.severe("And ONLY Recipes which are ==>OUTPUTTING<== the Item, sorry but I don't want failed Bug Reports.");
								FMLLog.severe("GregTech just reports this Error to you, so you can report it to the Mod causing the Problem.");
								FMLLog.severe("Even though I make that Bug visible, I can not and will not fix that for you, that's for the causing Mod to fix.");
								FMLLog.severe("And speaking of failed Reports:");
								FMLLog.severe("Both IC2 and GregTech CANNOT be the CAUSE of this Problem, so don't report it to either of them.");
								FMLLog.severe("I REPEAT, BOTH, IC2 and GregTech CANNOT be the source of THIS BUG. NO MATTER WHAT.");
								FMLLog.severe("Asking in the IC2 Forums, which Mod is causing that, won't help anyone, since it is not possible to determine, which Mod it is.");
								FMLLog.severe("If it would be possible, then I would have had added the Mod which is causing it to the Message already. But it is not possible.");
								FMLLog.severe("Sorry, but this Error is serious enough to justify this Wall-O-Text and the partially allcapsed Language.");
								FMLLog.severe("Also it is a Ban Reason on the IC2-Forums to seriously post this Text. We all know about its existence.");
								
								tOutput.setStackDisplayName("ERROR!");
								UT.NBT.setBoolean(UT.NBT.getNBT(tOutput), "gt.err.oredict.output", T);
							}
						} else {
							OM.set(tOutput);
						}
					}
					
					// Cleaning up Recipes with Empty OreDict Lists, since they are never craftable.
					List<IRecipe> tList = CR.list();
					for (int i = 0; i < tList.size(); i++) {
						Object tRecipe = tList.get(i);
						if (tRecipe instanceof ShapedOreRecipe) {
							Object[] tInput = ((ShapedOreRecipe)tRecipe).getInput();
							for (int j = 0; j < tInput.length; j++) {
								if (tInput[j] instanceof List && ((List<?>)tInput[j]).isEmpty()) {
//                                  DEB.println("Removed Recipe for " + ((ShapedOreRecipe)tRecipe).getRecipeOutput().getDisplayName() + " because Ingredient Nr. " + j + " is missing");
									tList.remove(i--);
									break;
								}
							}
						} else if (tRecipe instanceof ShapelessOreRecipe) {
							ArrayList<Object> tInput = ((ShapelessOreRecipe)tRecipe).getInput();
							for (int j = 0; j < tInput.size(); j++) {
								if (tInput.get(j) instanceof List && ((List<?>)tInput.get(j)).isEmpty()) {
//                                  DEB.println("Removed Recipe for " + ((ShapelessOreRecipe)tRecipe).getRecipeOutput().getDisplayName() + " because Ingredient Nr. " + j + " is missing");
									tList.remove(i--);
									break;
								}
							}
						}
					}
					
					OreDictManager.INSTANCE.fixStacksizes();
				}
				
				for (int i = 0; i < SERVER_TICK_PRE.size(); i++) {
					ITileEntityServerTickPre tTileEntity = SERVER_TICK_PRE.get(i);
					if (tTileEntity.isDead()) {
						SERVER_TICK_PRE.remove(i--);
						tTileEntity.onUnregisterPre();
					} else {
						try {
							tTileEntity.onServerTickPre(T);
						} catch(Throwable e) {
							SERVER_TICK_PRE.remove(i--);
							tTileEntity.setError("Server Tick Pre 1 - " + e);
							e.printStackTrace(ERR);
						}
					}
				}
				for (int i = 0; i < SERVER_TICK_PR2.size(); i++) {
					ITileEntityServerTickPre tTileEntity = SERVER_TICK_PR2.get(i);
					if (tTileEntity.isDead()) {
						SERVER_TICK_PR2.remove(i--);
						tTileEntity.onUnregisterPre();
					} else {
						try {
							tTileEntity.onServerTickPre(F);
						} catch(Throwable e) {
							SERVER_TICK_PR2.remove(i--);
							tTileEntity.setError("Server Tick Pre 2 - " + e);
							e.printStackTrace(ERR);
						}
					}
				}
				
				DELAYED_BLOCK_UPDATES_2.clear();
				List tList = DELAYED_BLOCK_UPDATES_2;
				DELAYED_BLOCK_UPDATES_2 = DELAYED_BLOCK_UPDATES;
				DELAYED_BLOCK_UPDATES = tList;
				for (IHasWorldAndCoords tTileEntity : DELAYED_BLOCK_UPDATES_2) {
					try {
						tTileEntity.getWorld().notifyBlocksOfNeighborChange(tTileEntity.getX(), tTileEntity.getY(), tTileEntity.getZ(), tTileEntity.getBlock(tTileEntity.getCoords()));
					} catch(Throwable e) {
						if (tTileEntity instanceof ITileEntityErrorable) ((ITileEntityErrorable)tTileEntity).setError("Delayed Block Update - " + e);
						e.printStackTrace(ERR);
					}
				}
				
				if (SERVER_TIME > 10) {
					for (ITileEntityScheduledUpdate tTileEntity : SCHEDULED_TILEENTITY_UPDATES_2) if (!tTileEntity.isDead()) {
						try {
							tTileEntity.onScheduledUpdate();
						} catch(Throwable e) {
							if (tTileEntity instanceof ITileEntityErrorable) ((ITileEntityErrorable)tTileEntity).setError("Scheduled TileEntity Update - " + e);
							e.printStackTrace(ERR);
						}
					}
					SCHEDULED_TILEENTITY_UPDATES_2.clear();
					tList = SCHEDULED_TILEENTITY_UPDATES_2;
					SCHEDULED_TILEENTITY_UPDATES_2 = SCHEDULED_TILEENTITY_UPDATES;
					SCHEDULED_TILEENTITY_UPDATES = tList;
					
					while (!mNewPlayers.isEmpty()) {
						EntityPlayerMP tPlayer = mNewPlayers.remove(0);
						NW_API.sendToPlayer(new PacketConfig(), tPlayer);
						for (OreDictPrefix tPrefix : OreDictPrefix.VALUES) if (!tPrefix.contains(TD.Prefix.PREFIX_UNUSED)) NW_API.sendToPlayer(new PacketPrefix(tPrefix), tPlayer);
					}
				}
			}
			
			if (aEvent.phase == Phase.END) {
				for (int i = 0; i < SERVER_TICK_POST.size(); i++) {
					ITileEntityServerTickPost tTileEntity = SERVER_TICK_POST.get(i);
					if (tTileEntity.isDead()) {
						SERVER_TICK_POST.remove(i--);
						tTileEntity.onUnregisterPost();
					} else {
						try {
							tTileEntity.onServerTickPost(T);
						} catch(Throwable e) {
							SERVER_TICK_POST.remove(i--);
							tTileEntity.setError("Server Tick Post 1 - " + e);
							e.printStackTrace(ERR);
						}
					}
				}
				for (int i = 0; i < SERVER_TICK_PO2T.size(); i++) {
					ITileEntityServerTickPost tTileEntity = SERVER_TICK_PO2T.get(i);
					if (tTileEntity.isDead()) {
						SERVER_TICK_PO2T.remove(i--);
						tTileEntity.onUnregisterPost();
					} else {
						try {
							tTileEntity.onServerTickPost(F);
						} catch(Throwable e) {
							SERVER_TICK_PO2T.remove(i--);
							tTileEntity.setError("Server Tick Post 2 - " + e);
							e.printStackTrace(ERR);
						}
					}
				}
				EntityFoodTracker.tick();
				if (TICK_LOCK.isHeldByCurrentThread()) TICK_LOCK.unlock();
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent aEvent) {
		int
		tX = UT.Code.roundDown(aEvent.entityLiving.posX),
		tY = UT.Code.roundDown(aEvent.entityLiving.posY + aEvent.entityLiving.getEyeHeight()),
		tZ = UT.Code.roundDown(aEvent.entityLiving.posZ);
		
		Block tBlock = aEvent.entityLiving.worldObj.getBlock(tX, tY, tZ);
		if (tBlock instanceof IBlockOnHeadInside) ((IBlockOnHeadInside)tBlock).onHeadInside(aEvent.entityLiving, aEvent.entityLiving.worldObj, tX, tY, tZ);
		
		if (aEvent.entityLiving.onGround) {
			tY = UT.Code.roundDown(aEvent.entityLiving.boundingBox.minY-0.001F);
			tBlock = aEvent.entityLiving.worldObj.getBlock(tX, tY, tZ);
			if (IL.EtFu_Path.equal(tBlock) && BlocksGT.Paths != null && aEvent.entityLiving.worldObj.setBlock(tX, tY, tZ, BlocksGT.Paths, 0, 2)) tBlock = BlocksGT.Paths;
			if (tBlock instanceof IBlockOnWalkOver) ((IBlockOnWalkOver)tBlock).onWalkOver(aEvent.entityLiving, aEvent.entityLiving.worldObj, tX, tY, tZ);
		}
	}
	
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent aEvent) {
		if (aEvent.side.isServer() && aEvent.phase == Phase.END) {
			ArrayListNoNulls<EntityXPOrb> tOrbs = (XP_ORB_COMBINING && SERVER_TIME % 40 == 31 ? new ArrayListNoNulls<EntityXPOrb>(128) : null);
			
			for (int i = 0; i < aEvent.world.loadedEntityList.size(); i++) {
				Entity aEntity = (Entity)aEvent.world.loadedEntityList.get(i);
				if (aEntity == null || aEntity.isDead) continue;
				if (aEntity instanceof EntityXPOrb) {
					if (tOrbs != null) tOrbs.add((EntityXPOrb)aEntity);
				} else if (aEntity instanceof EntityItem) {
					ItemStack aStack = ((EntityItem)aEntity).getEntityItem();
					if (ST.valid(aStack)) {
						ItemStack rStack = ST.copy(aStack);
						boolean tBreak = F, tFireProof = F;
						
						// TODO make a case for Armor too whenever I decide to even add Armor.
						if (rStack.getItem() instanceof MultiItemTool) {
							if (MultiItemTool.getPrimaryMaterial  (aStack).contains(TD.Properties.UNBURNABLE)) tFireProof = T;
							if (MultiItemTool.getSecondaryMaterial(aStack).contains(TD.Properties.UNBURNABLE)) tFireProof = T;
						}
						
						OreDictItemData aData = OM.anydata_(rStack);
						if (aData != null) {
							if (aData.hasValidPrefixData()) for (IOreDictListenerItem tListener : aData.mPrefix.mListenersItem) {
								rStack = tListener.onTickWorld(aData.mPrefix, aData.mMaterial.mMaterial, rStack, (EntityItem)aEntity);
								if (!ST.equal(rStack, aStack) || rStack.stackSize != aStack.stackSize) {tBreak = T; break;}
							}
							if (!tBreak && aData.hasValidMaterialData()) for (OreDictMaterialStack tMaterial : aData.getAllMaterialStacks()) {
								if (tBreak) break;
								if (tMaterial.mMaterial.contains(TD.Properties.UNBURNABLE)) tFireProof = T;
								for (IOreDictListenerItem tListener : tMaterial.mMaterial.mListenersItem) {
									rStack = tListener.onTickWorld(aData.mPrefix, tMaterial.mMaterial, rStack, (EntityItem)aEntity);
									if (!ST.equal(rStack, aStack) || rStack.stackSize != aStack.stackSize) {tBreak = T; break;}
								}
							}
						}
						
						if (rStack == null || rStack.stackSize <= 0) {
							((EntityItem)aEntity).setEntityItemStack(NI);
							((EntityItem)aEntity).setDead();
						} else if (!ST.equal(rStack, aStack) || rStack.stackSize != aStack.stackSize) {
							((EntityItem)aEntity).setEntityItemStack(rStack);
							((EntityItem)aEntity).delayBeforeCanPickup = 40;
						}
						
						if (!aEntity.isDead && aEntity.isBurning() && (tBreak || (tFireProof && !MD.MC.owns(rStack)))) {
							UT.Reflection.setField(EntityItem.class, aEntity, "health", 250, F);
							UT.Reflection.setField(EntityItem.class, aEntity, "field_70291_e", 250, F);
							aEntity.extinguish();
						}
					}
				} else if (aEntity instanceof EntityLivingBase) {
					if (ENTITY_CRAMMING > 0 && SERVER_TIME % 50 == 0 && !(aEntity instanceof EntityPlayer) && ((EntityLivingBase)aEntity).canBePushed() && ((EntityLivingBase)aEntity).getHealth() > 0) {
						List<?> tList = aEntity.worldObj.getEntitiesWithinAABBExcludingEntity(aEntity, aEntity.boundingBox.expand(0.2, 0.0, 0.2));
						Class<? extends Entity> tClass = aEntity.getClass();
						int aEntityCount = 1;
						if (tList != null) for (int j = 0; j < tList.size(); j++) if (tList.get(j) != null && tList.get(j).getClass() == tClass) aEntityCount++;
						if (aEntityCount > ENTITY_CRAMMING) aEntity.attackEntityFrom(DamageSource.inWall, (aEntityCount - ENTITY_CRAMMING) * TFC_DAMAGE_MULTIPLIER);
					}
				}
			}
			
			if (tOrbs != null && tOrbs.size() > 32) for (EntityXPOrb aOrb : tOrbs) {
				if (aOrb.xpValue >= Short.MAX_VALUE) continue;
				if (aOrb.xpValue <= 0) {aOrb.xpValue = 0; aOrb.setDead(); continue;}
				for (EntityXPOrb tOrb : tOrbs) if (aOrb != tOrb && !tOrb.isDead && tOrb.xpValue > 0 && tOrb.xpValue < Short.MAX_VALUE && aOrb.getDistanceSqToEntity(tOrb) <= 3) {
					aOrb.xpOrbAge = Math.min(aOrb.xpOrbAge, tOrb.xpOrbAge);
					if (aOrb.xpValue +  tOrb.xpValue > Short.MAX_VALUE) {
						tOrb.xpValue -= (Short.MAX_VALUE - aOrb.xpValue);
						aOrb.xpValue  =  Short.MAX_VALUE;
						break;
					}
					aOrb.xpValue += tOrb.xpValue;
					tOrb.xpValue  = 0;
					tOrb.setDead();
					break;
				}
			}
			
			if (SERVER_TIME % 20 == 1) {
				checkSaveLocation(aEvent.world.getSaveHandler().getWorldDirectory(), T);
				
				for (int i = 0; i < aEvent.world.loadedTileEntityList.size(); i++) {
					TileEntity aTileEntity = (TileEntity)aEvent.world.loadedTileEntityList.get(i);
					if (aTileEntity instanceof ITileEntityNeedsSaving) WD.mark(aTileEntity);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerItemPickupEvent(cpw.mods.fml.common.gameevent.PlayerEvent.ItemPickupEvent aEvent) {
		UT.Inventories.checkAchievements(aEvent.player, aEvent.pickedUp.getEntityItem());
	}
	
	private int BEAR_INVENTORY_COOL_DOWN = 5;
	
	@SubscribeEvent
	public void onPlayerTickEvent(PlayerTickEvent aEvent) {
		if (!aEvent.player.isDead && aEvent.phase == Phase.END) {
			for (Object tPotion : aEvent.player.getActivePotionEffects()) {
				if (tPotion instanceof PotionEffect && ((PotionEffect)tPotion).getDuration() <= 0) {
					aEvent.player.removePotionEffect(((PotionEffect)tPotion).getPotionID());
					break;
				}
			}
			if (aEvent.side.isServer()) {
				if (SURVIVAL_INTO_ADVENTURE_MODE && aEvent.player.ticksExisted%200==0 && aEvent.player.capabilities.allowEdit && !UT.Entities.isCreative(aEvent.player)) {
					aEvent.player.setGameType(WorldSettings.GameType.ADVENTURE);
					aEvent.player.capabilities.allowEdit = F;
					if (ADVENTURE_MODE_KIT) {
						if (MD.GT.mLoaded) {
							UT.Entities.sendchat(aEvent.player, CHAT_GREG + "Thank you for choosing the GregTech-6 Adventure Mode Starter Kit.");
							ST.drop(aEvent.player, IL.Bottle_Purple_Drink.get(6));
							ST.drop(aEvent.player, IL.Grass_Dry.get(8));
							ST.drop(aEvent.player, IL.Stick.get(16));
							ST.drop(aEvent.player, Items.flint, 12, 0);
							ST.drop(aEvent.player, Blocks.dirt, 16, 0);
							ST.drop(aEvent.player, Blocks.sapling, 4, 0);
							switch (RNGSUS.nextInt(4)) {
							case 0: ST.drop(aEvent.player, IL.Food_Large_Sandwich_Veggie.get(1)); break;
							case 1: ST.drop(aEvent.player, IL.Food_Large_Sandwich_Cheese.get(1)); break;
							case 2: ST.drop(aEvent.player, IL.Food_Large_Sandwich_Steak .get(1)); break;
							case 3: ST.drop(aEvent.player, IL.Food_Large_Sandwich_Bacon .get(1)); break;
							}
						} else {
							UT.Entities.sendchat(aEvent.player, CHAT_GREG + "It's dangerous to go alone! Take this.");
							ST.drop(aEvent.player, Items.stone_axe, 1, 0);
						}
					}
				}
				final boolean tHungerEffect = (HUNGER_BY_INVENTORY_WEIGHT && aEvent.player.ticksExisted % 2400 == 1200), tBetweenlands = WD.dimBTL(aEvent.player.worldObj.provider);//, tCrazyJ1984 = "CrazyJ1984".equalsIgnoreCase(aEvent.player.getCommandSenderName());
				if (aEvent.player.ticksExisted % 120 == 0) {
					ItemStack tStack;
					int tCount = 64, tEmptySlots = 36, tCraponite = 0;
					for (int i = 0; i < 36; i++) {
						if (ST.valid(tStack = aEvent.player.inventory.getStackInSlot(i))) {
							tEmptySlots--;
							if (tBetweenlands) {
								if (tStack.getItem() == Items.potionitem) {
									ST.set(tStack, IL.BTL_Tainted_Potion.get(1), F, F);
								} else if (tStack.getItem() instanceof IFluidContainerItem) {
									FluidStack tFluid = ((IFluidContainerItem)tStack.getItem()).getFluid(tStack);
									if (tFluid != null && !FL.Potion_Tainted.is(tFluid) && FluidsGT.POTION.contains(tFluid.getFluid().getName())) {
										((IFluidContainerItem)tStack.getItem()).drain(tStack, Integer.MAX_VALUE, T);
										((IFluidContainerItem)tStack.getItem()).fill(tStack, FL.Potion_Tainted.make(tFluid.amount), T);
									}
								}
								ItemStack tRotten = RottingUtil.rotting(tStack, aEvent.player.worldObj, UT.Code.roundDown(aEvent.player.posX), UT.Code.roundDown(aEvent.player.posY), UT.Code.roundDown(aEvent.player.posZ));
								if (ST.invalid(tRotten)) {tStack.stackSize = 0; aEvent.player.inventory.setInventorySlotContents(i, null); continue;}
								if (tStack != tRotten) ST.set(tStack, tRotten);
							}
							OreDictItemData tData = OM.anydata_(tStack);
							if (!UT.Entities.isInvincible(aEvent.player)) {
								UT.Entities.applyRadioactivity(aEvent.player, UT.Entities.getRadioactivityLevel(tStack), tStack.stackSize);
								float tHeat = UT.Entities.getHeatDamageFromItem(tStack);
								if (tHeat != 0.0F) if (tHeat > 0) UT.Entities.applyHeatDamage(aEvent.player, tHeat); else UT.Entities.applyFrostDamage(aEvent.player, -tHeat);
							}
							if (tData != null && tData.hasValidMaterialData()) {
								if ((tData.mMaterial.mMaterial == MT.Bedrockium || tData.mMaterial.mMaterial == MT.Neutronium) && (tData.hasValidPrefixData() || tData.mByProducts.length <= 0)) {
									PotionEffect tEffect = null;
									UT.Entities.applyPotion(aEvent.player, Potion.moveSlowdown.id, Math.max(140, ((tEffect = aEvent.player.getActivePotionEffect(Potion.moveSlowdown))==null?0:tEffect.getDuration())), 3, F);
								}
								if (tData.mMaterial.mMaterial == MT.Craponite) {
									tCraponite++;
								}
								if (tData.mMaterial.mMaterial == MT.Firestone && tData.hasValidPrefixData() && !MD.RC.owns(tStack)) for (int j = (int)UT.Code.divup(tData.mMaterial.mAmount, U) * tStack.stackSize; j > 0; j--) {
									WD.fire(aEvent.player.worldObj, UT.Code.roundDown(aEvent.player.posX)-5+RNGSUS.nextInt(11), UT.Code.roundDown(aEvent.player.posY)-5+RNGSUS.nextInt(11), UT.Code.roundDown(aEvent.player.posZ)-5+RNGSUS.nextInt(11), RNGSUS.nextInt(8) != 0);
								}
							}
							if (tHungerEffect) tCount+=(tStack.stackSize * 64) / Math.max(1, tStack.getMaxStackSize());
							if (INVENTORY_UNIFICATION) OM.set_(tStack);
							ST.update(tStack, aEvent.player);
							if (tStack.hasTagCompound() && tStack.getTagCompound().hasNoTags()) tStack.setTagCompound(null);
						}
					}
					
					// This Code is to tell Bear and all the people around him that he should clean up his always cluttered Inventory.
					if ("Bear989Sr".equalsIgnoreCase(aEvent.player.getCommandSenderName())) {
						if (tCraponite > 0) {
							// Crazy started to give Bear her Craponite Arrows, lets not let him have those.
							UT.Entities.applyPotion(aEvent.player, Potion.poison, 1200, tCraponite, T);
						}
						if (--BEAR_INVENTORY_COOL_DOWN < 0 && tEmptySlots < 4) {
							BEAR_INVENTORY_COOL_DOWN = 100;
							UT.Sounds.send(SFX.MC_HMM, aEvent.player);
							for (int i = 0; i < aEvent.player.worldObj.playerEntities.size(); i++) {
								EntityPlayer tPlayer = (EntityPlayer)aEvent.player.worldObj.playerEntities.get(i);
								if (tPlayer == null) continue;
								if ("Bear989Sr".equalsIgnoreCase(tPlayer.getCommandSenderName())) {
									if (tPlayer.posY < 30) {
										UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "Stop making Holes in the Ground, Bear!"));
									} else {
										// Bear does not like being called these names, so lets annoy him. XD
										switch(tEmptySlots) {
										case 0: UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "Alright Buttercup, your Inventory is full, time to go home.")); break;
										case 1: UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "Your Inventory is starting to get full, Buttercup")); break;
										case 2: UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "Your Inventory is starting to get full, Bean989Sr")); break;
										case 3: UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "Your Inventory is starting to get full, Mr. Bear")); break;
										}
									}
								} else if ("Bear989jr".equalsIgnoreCase(tPlayer.getCommandSenderName())) {
									UT.Inventories.addStackToPlayerInventoryOrDrop(tPlayer, UT.NBT.addEnchantment(ST.make(Items.cookie, 1, 0, "Jr. Cookie"), Enchantment_WerewolfDamage.INSTANCE, 1), F);
									UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "Have a Jr. Cookie. Please tell Fatass to clean his Inventory, or smack him with it."));
								} else if ("CrazyJ1984".equalsIgnoreCase(tPlayer.getCommandSenderName())) {
									ItemStack tArrow = ST.update(OP.arrowGtWood.mat(MT.Craponite, 1), aEvent.player);
									if (ST.valid(tArrow)) {
										UT.Inventories.addStackToPlayerInventoryOrDrop(tPlayer, tArrow, F);
										UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "I'm not trying to tell you what to do, but please don't hurt Bear with this."));
									} else {
										UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "I'm not trying to tell you what to do, but please don't hurt Bear."));
									}
								} else if ("GregoriusT".equalsIgnoreCase(tPlayer.getCommandSenderName())) {
									UT.Inventories.addStackToPlayerInventoryOrDrop(tPlayer, ST.update(OP.arrowGtPlastic.mat(MT.Tc, 1), aEvent.player), F);
									UT.Entities.chat(tPlayer, new ChatComponentText(LH.Chat.BOLD + "You have received an Arrow"));
								} else if ("Ilirith".equalsIgnoreCase(tPlayer.getCommandSenderName())) {
									UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "Could you tell Bear989Sr very gently, that his Inventory is a fucking mess again?"));
								} else if ("Shadowkn1ght18".equalsIgnoreCase(tPlayer.getCommandSenderName())) {
									UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "Here is your special Message to make you tell Bear989Sr to clean his Inventory."));
								} else if ("e99999".equalsIgnoreCase(tPlayer.getCommandSenderName())) {
									UT.Entities.chat(tPlayer, new ChatComponentText(LH.Chat.DGRAY + "You get the sneaking suspicion that Bears Inventory may or may not be full right now."));
								} else {
									UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "There is this fella called Bear-Nine-Eight-Nine, needing be reminded of his Inventory being a major Pine."));
								}
							}
						}
					}
					
					for (int i = 0; i < 4; i++) if (ST.valid(tStack = aEvent.player.inventory.armorInventory[i])) {
						// The Better Storage Backpack would dupe Items when destroyed while worn, so this will prevent that.
						// A Backpack already is hindrance enough if you want full Armor, so Durability should not matter here anyways.
						// I also like this Backpack implementation, so I cant just leave the dupe exploit easy to pull off.
						if (MD.BTRS.mLoaded && (IL.BTRS_Backpack.equal(tStack, T, T) || IL.BTRS_Thaumpack.equal(tStack, T, T) || IL.BTRS_Enderpack.equal(tStack, T, T))) {
							ST.meta(tStack, 0);
						}
						
						if (!UT.Entities.isInvincible(aEvent.player)) {
							UT.Entities.applyRadioactivity(aEvent.player, UT.Entities.getRadioactivityLevel(tStack), tStack.stackSize);
							float tHeat = UT.Entities.getHeatDamageFromItem(tStack);
							if (tHeat != 0.0F) if (tHeat > 0) UT.Entities.applyHeatDamage(aEvent.player, tHeat); else UT.Entities.applyFrostDamage(aEvent.player, -tHeat);
						}
						if (tHungerEffect) tCount+=256;
					}
					if (tHungerEffect) aEvent.player.addExhaustion(Math.max(1.0F, tCount/666F));
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onChunkWatchEvent(ChunkWatchEvent.Watch aEvent) {
		Chunk tChunk = aEvent.player.worldObj.getChunkFromChunkCoords(aEvent.chunk.chunkXPos, aEvent.chunk.chunkZPos);
		if (tChunk != null && tChunk.isTerrainPopulated) {
			byte tIterations = 8;
			HashSetNoNulls<Object> tSet = new HashSetNoNulls<>();
			while (tIterations-->0) try {
				for (Object tTileEntity : tChunk.chunkTileEntityMap.values()) if (tTileEntity instanceof ITileEntitySynchronising) if (tSet.add(tTileEntity)) ((ITileEntitySynchronising)tTileEntity).sendUpdateToPlayer(aEvent.player);
				tIterations = 0;
			} catch(ConcurrentModificationException e) {
				if (tIterations <= 0) ERR.println("Failed to Iterate 8 times. Giving up on sending Data to Client!");
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerDestroyItem(PlayerDestroyItemEvent aEvent) {
		// Uhh, why is this null? Must be a Bug somewhere else.
		if (aEvent.original == null) return;
		// Only for real Players!
		if (!UT.Entities.isPlayer(aEvent.entityPlayer)) return;
		// No Creative Mode Refill!
		if (UT.Entities.hasInfiniteItems(aEvent.entityPlayer)) return;
		// Tool Break Fatique.
		if (TOOL_BREAK_FATIQUE) {
			if (ST.item_(aEvent.original) instanceof MultiItemTool) {
				IToolStats tStats = ((MultiItemTool)ST.item_(aEvent.original)).getToolStats(aEvent.original);
				if (tStats != null) tStats.afterBreaking(aEvent.original, aEvent.entityPlayer);
			} else
			if (ST.item_(aEvent.original) instanceof ItemSword || ST.item_(aEvent.original) instanceof ItemTool) {
				// If you work so hard that your Tool breaks, you should probably take a break yourself. :P
				UT.Entities.applyPotion(aEvent.entityPlayer, Potion.weakness   , 300, 2, F);
				UT.Entities.applyPotion(aEvent.entityPlayer, Potion.digSlowdown, 300, 2, F);
			}
		}
		// 
		ItemStack[] tInv = aEvent.entityPlayer.inventory.mainInventory;
		// Only work on Vanilla-Sized Player Inventories!
		if (tInv.length != 36) return;
		// 
		int tSlot = aEvent.entityPlayer.inventory.currentItem;
		// There cant be any Inventory Row above this one.
		if (tSlot >= 27) return;
		// Refill, but only if the Slot in the Hotbar is Empty.
		if (tInv[tSlot] != null && tInv[tSlot].stackSize > 0) return;
		// Do not refill Foods!
		if (ST.food(aEvent.original) > 0) return;
		// Do not refill Edibles!
		if (aEvent.original.getItemUseAction() == EnumAction.eat) return;
		// Do not refill Drinkables!
		if (aEvent.original.getItemUseAction() == EnumAction.drink) return;
		// Move into First Row.
		if (tSlot < 9) {
			if (ST.equal(aEvent.original, tInv[tSlot+27], T)) {
			if (ST.equal(aEvent.original, tInv[tSlot+18], T)) {
			if (ST.equal(aEvent.original, tInv[tSlot+ 9], T)) {
			tInv[tSlot] = tInv[tSlot+ 9]; tInv[tSlot+ 9] = null; ST.update(aEvent.entityPlayer); return;}
			tInv[tSlot] = tInv[tSlot+18]; tInv[tSlot+18] = null; ST.update(aEvent.entityPlayer); return;}
			tInv[tSlot] = tInv[tSlot+27]; tInv[tSlot+27] = null; ST.update(aEvent.entityPlayer); return;}
			return;
		}
		// Move into Second Row. Usually only with the Double Hotbars Mod.
		if (tSlot < 18) {
			if (ST.equal(aEvent.original, tInv[tSlot+18], T)) {
			if (ST.equal(aEvent.original, tInv[tSlot+ 9], T)) {
			tInv[tSlot] = tInv[tSlot+ 9]; tInv[tSlot+ 9] = null; ST.update(aEvent.entityPlayer); return;}
			tInv[tSlot] = tInv[tSlot+18]; tInv[tSlot+18] = null; ST.update(aEvent.entityPlayer); return;}
			return;
		}
		// Move into Third Row. Unsure if a Triple Hotbar Mod exists, but if it does, well then it is supported.
		if (ST.equal(aEvent.original, tInv[tSlot+ 9], T)) {
		tInv[tSlot] = tInv[tSlot+ 9]; tInv[tSlot+ 9] = null; ST.update(aEvent.entityPlayer); return;}
		return;
	}
	
	@SubscribeEvent
	public void onItemUseFinish(PlayerUseItemEvent.Finish aEvent) {
		int[] tStats = FoodsGT.get(aEvent.item);
		if (tStats != null) {
			EntityFoodTracker tTracker = EntityFoodTracker.get(aEvent.entityPlayer);
			if (tTracker != null) {
				if (tStats.length > 0 && tStats[0] != 0) tTracker.changeAlcohol    (tStats[0]);
				if (tStats.length > 1 && tStats[1] != 0) tTracker.changeCaffeine   (tStats[1]);
				if (tStats.length > 2 && tStats[2] != 0) tTracker.changeDehydration(tStats[2]);
				if (tStats.length > 3 && tStats[3] != 0) tTracker.changeSugar      (tStats[3]);
				if (tStats.length > 4 && tStats[4] != 0) tTracker.changeFat        (tStats[4]);
				if (tStats.length > 5 && tStats[5] != 0) tTracker.changeRadiation  (tStats[5]);
			}
		}
		
		NBTTagCompound tNBT = aEvent.item.getTagCompound();
		if (tNBT != null && tNBT.hasKey(NBT_EFFECTS)) {
			tNBT = tNBT.getCompoundTag(NBT_EFFECTS);
			if (RNGSUS.nextInt(100) < tNBT.getInteger("chance")) UT.Entities.applyPotion(aEvent.entityPlayer, tNBT.getInteger("id"), tNBT.getInteger("time"), tNBT.getInteger("lvl"), F);
		}
		
		if (aEvent.item.getItem() == Items.apple) {
			if (IL.GrC_Applecore.exists()) {
				if (aEvent.result == null) aEvent.result = IL.GrC_Applecore.get(1); else UT.Inventories.addStackToPlayerInventoryOrDrop(aEvent.entityPlayer, IL.GrC_Applecore.get(1), F);
			} else if (IL.Food_Apple_Red_Core.exists()) {
				if (aEvent.result == null) aEvent.result = IL.Food_Apple_Red_Core.get(1); else UT.Inventories.addStackToPlayerInventoryOrDrop(aEvent.entityPlayer, IL.Food_Apple_Red_Core.get(1), F);
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerInteraction(PlayerInteractEvent aEvent) {
		if (aEvent.entityPlayer == null || aEvent.entityPlayer.worldObj == null || aEvent.action == null || aEvent.world.provider == null) return;
		
		PLAYER_LAST_CLICKED.put(aEvent.entityPlayer, new ChunkCoordinates(aEvent.x, aEvent.y, aEvent.z));
		
		ItemStack aStack = aEvent.entityPlayer.inventory.getCurrentItem();
		Block aBlock = WD.block(aEvent.world, aEvent.x, aEvent.y, aEvent.z);
		TileEntity aTileEntity = aEvent.world.getTileEntity(aEvent.x, aEvent.y, aEvent.z);
		
		if (aEvent.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
			// Fixing a Vanilla Dupe Bug with stacked Music Discs and the Jukebox.
			if (aTileEntity instanceof TileEntityJukebox) {
				ItemStack tStack = ((TileEntityJukebox)aTileEntity).func_145856_a();
				if (tStack != null) tStack.stackSize = 1;
				return;
			}
			// You can easily recycle most things in GT6 anyways, so this should not be needed.
			if (IL.TF_Uncrafting.equal(aBlock)) {
				UT.Entities.chat(aEvent.entityPlayer, CHAT_GREG + "No cheating! ;)");
				aEvent.setCanceled(T);
				return;
			}
			// Just rightclick the Trophy to get the Achievement/Progress.
			if (IL.TF_Trophy.equal(aBlock)) {
				UT.Inventories.checkAchievements(aEvent.entityPlayer, ST.make(aBlock.getItemDropped(0, RNGSUS, 0), 1, aBlock.getDamageValue(aEvent.world, aEvent.x, aEvent.y, aEvent.z)));
				return;
			}
			// Some Clientside Only Stuff.
			if (aEvent.entityPlayer.worldObj.isRemote && !aEvent.entityPlayer.isSneaking()) {
				if (aTileEntity instanceof PrefixBlockTileEntity) {
					// Show uses for Bedrock Ore when clicking it.
					if (aBlock == BlocksGT.oreBedrock || aBlock == BlocksGT.oreSmallBedrock) {
						RM.BedrockOreList.openNEI();
					//  RM.BedrockOreList.guiUsesNEI(ST.make((Block)BlocksGT.oreBedrock, 1, ((PrefixBlockTileEntity)aTileEntity).mMetaData));
					}
				}
			}
			if (ST.valid(aStack)) {
				// Preventing a Railcraft Crash with Fluid Container Items.
				if (aStack.getItem() instanceof IFluidContainerItem && !aEvent.entityPlayer.isSneaking() && aTileEntity != null && aTileEntity.getClass().getName().startsWith("mods.railcraft.common")) {
					aEvent.setCanceled(T);
					return;
				}
				/* I think this was for fixing some Adventure Mode related thing. Probably placing Scaffolds with Leftclick was broken, but I ended up fixing it another way.
				if (MD.IC2.mLoaded && SIDES_HORIZONTAL[aEvent.face] && !aEvent.entityPlayer.isSneaking() && !aEvent.entityPlayer.capabilities.allowEdit && !aEvent.world.canPlaceEntityOnSide(aBlock, aEvent.x+OFFSETS_X[aEvent.face], aEvent.y, aEvent.z+OFFSETS_Z[aEvent.face], F, aEvent.face, aEvent.entityPlayer, aStack)) {
					if (IL.IC2_Scaffold.equal(aBlock) && IL.IC2_Scaffold.equal(aStack, F, T)) {
						aBlock.onBlockClicked(aEvent.world, aEvent.x, aEvent.y, aEvent.z, aEvent.entityPlayer);
						aEvent.entityPlayer.swingItem();
						aEvent.setCanceled(T);
						return;
					}
					if (IL.IC2_Scaffold_Iron.equal(aBlock) && IL.IC2_Scaffold_Iron.equal(aStack, F, T)) {
						aBlock.onBlockClicked(aEvent.world, aEvent.x, aEvent.y, aEvent.z, aEvent.entityPlayer);
						aEvent.entityPlayer.swingItem();
						aEvent.setCanceled(T);
						return;
					}
				}*/
				if (!(aStack.getItem() instanceof IItemNoGTOverride)) {
					// Dollies won't work on GT6 TileEntities, so to prevent a Crash and deleted Resources, I just disable the interaction.
					if (IL.JABBA_Dolly.equal(aStack, T, T) || IL.JABBA_Dolly_Diamond.equal(aStack, T, T)) {
						if (aTileEntity instanceof ITileEntitySpecificPlacementBehavior) {
							UT.Entities.chat(aEvent.entityPlayer, CHAT_GREG + "The Dolly Code is sadly not smart enough to move this TileEntity.", CHAT_GREG + "It would crash if it actually did, so be glad I prevented your mistake.", CHAT_GREG + "Would be great if it did work though...");
							aEvent.setCanceled(T);
						}
						return;
					}
					// Instant breaking for those Wrenches.
					if (IL.BC_Wrench.equal(aStack, T, T) || IL.FR_Wrench.equal(aStack, T, T) || IL.SC2_Wrench.equal(aStack, T, T) || IL.AE_Wrench_Certus.equal(aStack, T, T) || IL.AE_Wrench_Quartz.equal(aStack, T, T) || IL.TE_Wrench.equal(aStack, T, T) || IL.TE_Wrench_Battle.equal(aStack, T, T)) {
						List<String> tChatReturn = new ArrayListNoNulls<>();
						long tDamage = IBlockToolable.Util.onToolClickWithoutCompat(TOOL_wrench, Long.MAX_VALUE, 3, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.entityPlayer.worldObj, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
						UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
						if (tDamage > 0) {
							ST.use(aEvent.entityPlayer, aStack);
							UT.Sounds.send(aEvent.world, SFX.MC_BREAK, 1.0F, 1.0F, aEvent.x, aEvent.y, aEvent.z);
							aEvent.setCanceled(T);
						}
						return;
					}
					// Instant breaking for those Soft Hammers.
					if (IL.MFR_Hammer.equal(aStack, T, T)) {
						List<String> tChatReturn = new ArrayListNoNulls<>();
						long tDamage = IBlockToolable.Util.onToolClickWithoutCompat(TOOL_softhammer, Long.MAX_VALUE, 3, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.entityPlayer.worldObj, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
						UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
						if (tDamage > 0) {
							ST.use(aEvent.entityPlayer, aStack);
							UT.Sounds.send(aEvent.world, SFX.MC_BREAK, 1.0F, 1.0F, aEvent.x, aEvent.y, aEvent.z);
							aEvent.setCanceled(T);
						}
						return;
					}
					// Instant breaking for those Hard Hammers.
					if (IL.IE_Hammer.equal(aStack, F, T) || IL.A97_Hammer.equal(aStack, T, T) || IL.SC2_Hammer.equal(aStack, T, T) || IL.SC2_Hammer_Gilded.equal(aStack, T, T)) {
						List<String> tChatReturn = new ArrayListNoNulls<>();
						long tDamage = IBlockToolable.Util.onToolClickWithoutCompat(TOOL_hammer, Long.MAX_VALUE, 3, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.entityPlayer.worldObj, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
						UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
						if (tDamage > 0) {
							ST.use(aEvent.entityPlayer, aStack);
							UT.Sounds.send(aEvent.world, SFX.MC_BREAK, 1.0F, 1.0F, aEvent.x, aEvent.y, aEvent.z);
							aEvent.setCanceled(T);
						}
						return;
					}
					// Make Railcrafts Crowbars work on GT6 Stuff.
					if (IL.RC_Crowbar_Iron.equal(aStack, T, T) || IL.RC_Crowbar_Steel.equal(aStack, T, T) || IL.RC_Crowbar_Thaumium.equal(aStack, T, T) || IL.RC_Crowbar_Voidmetal.equal(aStack, T, T)) {
						List<String> tChatReturn = new ArrayListNoNulls<>();
						long tDamage = IBlockToolable.Util.onToolClickWithoutCompat(TOOL_crowbar, Long.MAX_VALUE, 2, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.entityPlayer.worldObj, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
						UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
						if (tDamage > 0) {
							aStack.damageItem((int)UT.Code.units(tDamage, 10000, 1, T), aEvent.entityPlayer);
							if (aStack.getItemDamage() >= aStack.getMaxDamage()) ST.use(aEvent.entityPlayer, aStack);
							aEvent.setCanceled(T);
						}
						return;
					}
					// Make Forestry Scoops work on GT6 Stuff.
					if (IL.FR_Scoop.equal(aStack, T, T)) {
						List<String> tChatReturn = new ArrayListNoNulls<>();
						long tDamage = IBlockToolable.Util.onToolClickWithoutCompat(TOOL_scoop, Long.MAX_VALUE, 0, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.world, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
						UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
						if (tDamage > 0) {
							aStack.damageItem((int)UT.Code.units(tDamage, 10000, 1, T), aEvent.entityPlayer);
							if (aStack.getItemDamage() >= aStack.getMaxDamage()) ST.use(aEvent.entityPlayer, aStack);
							aEvent.setCanceled(T);
						}
						return;
					}
					// Make Railcrafts Firestone work as Flint and Steel on TNT and GT6 Machines
					if (IL.RC_Firestone_Refined.equal(aStack, T, T) || IL.RC_Firestone_Cracked.equal(aStack, T, T)) {
						List<String> tChatReturn = new ArrayListNoNulls<>();
						long tDamage = IBlockToolable.Util.onToolClickWithoutCompat(TOOL_igniter, Long.MAX_VALUE, Long.MAX_VALUE, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.world, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
						UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
						if (tDamage > 0) {
							aStack.damageItem((int)UT.Code.units(tDamage, 10000, 1, T), aEvent.entityPlayer);
							if (aStack.getItemDamage() >= aStack.getMaxDamage()) ST.use(aEvent.entityPlayer, aStack);
							UT.Sounds.send(aEvent.world, SFX.MC_IGNITE, 1.0F, 1.0F, aEvent.x, aEvent.y, aEvent.z);
							aEvent.setCanceled(T);
						}
						return;
					}
					// Make Twilight Forests Lamp of Cinders work as infinite Flint and Steel on TNT and GT6 Machines. Should be a good reward for getting to it.
					if (IL.TF_Lamp_of_Cinders.equal(aStack, T, T)) {
						List<String> tChatReturn = new ArrayListNoNulls<>();
						long tDamage = IBlockToolable.Util.onToolClick(TOOL_igniter, Long.MAX_VALUE, Long.MAX_VALUE, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.world, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
						UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
						if (tDamage > 0) aEvent.setCanceled(T);
						return;
					}
					if (IL.TF_Transformation_Powder.equal(aStack, T, T)) {
						// Make Twilight Forests Transformation Powder work on Mob Spawners
						if (aTileEntity instanceof TileEntityMobSpawner) {
							if (aEvent.world.isRemote) return;
							MobSpawnerBaseLogic tSpawner = ((TileEntityMobSpawner)aTileEntity).func_145881_a();
							String tResult = TRANSFORMATION_POWDER_SPAWNER_MAP.get(tSpawner.getEntityNameToSpawn());
							if (UT.Code.stringValid(tResult)) {
								if (ST.use(aEvent.entityPlayer, aStack, 16)) {
									tSpawner.setEntityName(tResult);
									// I hope this works sync the new Mob Data over.
									aEvent.world.markBlockForUpdate(aEvent.x, aEvent.y, aEvent.z);
								} else {
									UT.Entities.sendchat(aEvent.entityPlayer, "You need 16 Bags of Transformation Powder to convert this!");
								}
							} else {
								UT.Entities.sendchat(aEvent.entityPlayer, "This Spawner does not have a Counterpart to convert into!");
							}
							aEvent.setCanceled(T);
							return;
						}
					}
				}
			}
		}
		
		if (aEvent.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK || aEvent.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
			if (ST.valid(aStack)) {
				// Make sure that shelvable Items don't do a Rightclick Action instead of being shelved.
				if (aTileEntity instanceof ITileEntityBookShelf && ((ITileEntityBookShelf)aTileEntity).isShelfFace((byte)aEvent.face)) {
					aEvent.useBlock = Result.ALLOW;
					if (BooksGT.BOOK_REGISTER.containsKey(aStack, T)) aEvent.useItem = Result.DENY;
					return;
				}
				// Reload Guns with the potential Ammo in this Slot if applicable. Ugly Code, I know.
				if (!aEvent.entityPlayer.worldObj.isRemote) {
					for (int i = 0; i < aEvent.entityPlayer.inventory.mainInventory.length; i++) {
						ItemStack tStack = aEvent.entityPlayer.inventory.mainInventory[i];
						if (ST.item(tStack) instanceof MultiItem) {
							List<IBehavior<MultiItem>> tList = ((MultiItem) ST.item_(tStack)).mItemBehaviors.get(ST.meta_(tStack));
							if (tList != null) for (IBehavior<MultiItem> tBehavior : tList) {
								if (tBehavior instanceof Behavior_Gun) {
									if (((Behavior_Gun) tBehavior).reloadGun(tStack, aEvent.entityPlayer, T)) {
										aEvent.setCanceled(T);
										return;
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onUseHoeEvent(net.minecraftforge.event.entity.player.UseHoeEvent aEvent) {
		if (aEvent.world.getBlock(aEvent.x, aEvent.y, aEvent.z) == Blocks.dirt && aEvent.world.getBlockMetadata(aEvent.x, aEvent.y, aEvent.z) != 0) aEvent.setCanceled(T);
	}
	
	@SubscribeEvent
	@SuppressWarnings("unlikely-arg-type")
	public void onBlockBreakSpeedEvent(PlayerEvent.BreakSpeed aEvent) {
		if (aEvent.newSpeed > 0) {
			if (aEvent.entityPlayer != null) {
				ItemStack aStack = aEvent.entityPlayer.getCurrentEquippedItem();
				if (aStack != null && aStack.getItem() instanceof MultiItemTool) {
					// Aether does something stupid so Reflection it is
					UT.Reflection.setField(aEvent, "originalSpeed", aEvent.newSpeed = ((MultiItemTool)aStack.getItem()).onBlockBreakSpeedEvent(aEvent.newSpeed, aStack, aEvent.entityPlayer, aEvent.block, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.metadata, aEvent));
				}
			}
			
			ItemStackContainer tBlock = new ItemStackContainer(aEvent.block, 1, aEvent.metadata);
			
			if (OM.prefixcontains(ST.make(aEvent.block, 1, aEvent.metadata), TD.Prefix.ORE)) {
				// Aether does something stupid so Reflection it is
				UT.Reflection.setField(aEvent, "originalSpeed", aEvent.newSpeed /= HARDNESS_MULTIPLIER_ORES);
				return;
			}
			if (BlocksGT.stoneToBrokenOres.containsKey(tBlock) || BlocksGT.stoneToNormalOres.containsKey(tBlock) || BlocksGT.stoneToSmallOres.containsKey(tBlock)) {
				if (aEvent.block.getMaterial() == Material.sand || aEvent.block.getMaterial() == Material.clay || aEvent.block.getMaterial() == Material.grass || aEvent.block.getMaterial() == Material.ground) {
					// Aether does something stupid so Reflection it is
					UT.Reflection.setField(aEvent, "originalSpeed", aEvent.newSpeed /= HARDNESS_MULTIPLIER_SAND);
					return;
				}
				// Aether does something stupid so Reflection it is
				UT.Reflection.setField(aEvent, "originalSpeed", aEvent.newSpeed /= HARDNESS_MULTIPLIER_ROCK);
				return;
			}
			if (aEvent.block instanceof IBlockPlacable) {
				if (BlocksGT.stoneToBrokenOres.containsValue(aEvent.block) || BlocksGT.stoneToNormalOres.containsValue(aEvent.block) || BlocksGT.stoneToSmallOres.containsValue(aEvent.block)) {
					// Aether does something stupid so Reflection it is
					UT.Reflection.setField(aEvent, "originalSpeed", aEvent.newSpeed /= HARDNESS_MULTIPLIER_ORES);
					return;
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onBlockBreakingEvent(BlockEvent.BreakEvent aEvent) {
		if (aEvent.block instanceof IPrefixBlock && EnchantmentHelper.getSilkTouchModifier(aEvent.getPlayer())) aEvent.setExpToDrop(0);
	}
	
	@SubscribeEvent
	public void onBlockHarvestingEvent(BlockEvent.HarvestDropsEvent aEvent) {
		Iterator<ItemStack> aDrops = aEvent.drops.iterator();
		Block aBlock = (aEvent.block == Blocks.lit_redstone_ore ? Blocks.redstone_ore : aEvent.block);
		while (aDrops.hasNext()) {
			ItemStack aDrop = aDrops.next();
			if (ST.invalid(aDrop) || ItemsGT.ILLEGAL_DROPS.contains(aDrop, T)) {aDrops.remove(); continue;}
			if (ST.item_(aDrop) == Items.gold_nugget) ST.meta_(aDrop, 0);
			if (FORCE_GRAVEL_NO_FLINT && aBlock == Blocks.gravel && ST.item_(aDrop) == Items.flint) ST.set(aDrop, ST.make(Blocks.gravel, 1, 0), T, F);
		}
		
		if (aBlock == Blocks.dirt && aEvent.blockMetadata == 1) for (int i = 0, j = aEvent.drops.size(); i < j; i++) if (ST.block(aEvent.drops.get(0)) == Blocks.dirt) {
			aEvent.drops.set(i, ST.make(Blocks.dirt, aEvent.drops.get(i).stackSize, 1));
		}
		
		if (aEvent.harvester != null) {
			if (FAST_LEAF_DECAY) WD.leafdecay(aEvent.world, aEvent.x, aEvent.y, aEvent.z, aBlock, F, F);
			ItemStack aTool = aEvent.harvester.getCurrentEquippedItem();
			if (aTool != null) {
				boolean
				tFireAspect = (EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, aTool) >= 3),
				tCanCollect = (ST.item_(aTool) instanceof MultiItemTool && ((MultiItemTool)ST.item_(aTool)).canCollectDropsDirectly(aTool, aBlock, (byte)aEvent.blockMetadata));
				
				if (ST.item_(aTool) instanceof MultiItemTool) {
					((MultiItemTool)ST.item_(aTool)).onHarvestBlockEvent(aEvent.drops, aTool, aEvent.harvester, aBlock, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.blockMetadata, aEvent.fortuneLevel, aEvent.isSilkTouching, aEvent);
				}
				
				for (ItemStack tDrop : aEvent.drops) {
					OM.set(tDrop);
					
					ItemStack
					tTarget = (aEvent.isSilkTouching?BlocksGT.blockToSilk:BlocksGT.blockToDrop).get(tDrop);
					if (ST.invalid(tTarget)) continue;
					OM.set(ST.set(tDrop, tTarget, F, F));
					
					if (!tFireAspect) continue;
					
					tTarget = RM.get_smelting(tDrop, F, null);
					if (ST.invalid(tTarget)) continue;
					tDrop.stackSize *= tTarget.stackSize;
					OM.set(ST.set(tDrop, tTarget, F, T));
					
					tTarget = (aEvent.isSilkTouching?BlocksGT.blockToSilk:BlocksGT.blockToDrop).get(tDrop);
					if (ST.invalid(tTarget)) continue;
					OM.set(ST.set(tDrop, tTarget, F, F));
				}
				
				if (tCanCollect && !aEvent.drops.isEmpty()) {
					boolean aCollectSound = T;
					aDrops = aEvent.drops.iterator();
					while (aDrops.hasNext()) {
						ItemStack aDrop = ST.update(aDrops.next(), aEvent.world, aEvent.x, aEvent.y, aEvent.z);
						
						EntityItem tEntity = ST.entity(aEvent.harvester, aDrop);
						EntityItemPickupEvent tEvent = new EntityItemPickupEvent(aEvent.harvester, tEntity);
						ST.set(aDrop, tEvent.item.getEntityItem(), T, T);
						if (MinecraftForge.EVENT_BUS.post(tEvent)) continue;
						
						if (tEvent.getResult() == Result.ALLOW || aDrop.stackSize <= 0 || UT.Inventories.addStackToPlayerInventory(aEvent.harvester, aDrop)) {
							aDrops.remove();
							if (aCollectSound) {
								UT.Sounds.send(SFX.MC_COLLECT, 0.2F, ((RNGSUS.nextFloat()-RNGSUS.nextFloat())*0.7F+1.0F)*2.0F, aEvent.harvester);
								aCollectSound = F;
							}
						}
					}
				}
			}
			UT.Inventories.removeNullStacksFromInventory(aEvent.harvester.inventory);
		}
	}
	
	public static List<EntityPlayerMP> mNewPlayers = new ArrayListNoNulls<>();
	
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent aEvent) {
		if (aEvent.entityLiving instanceof EntityPlayerMP) NW_API.sendToPlayer(new PacketDeathPoint(UT.Code.roundDown(aEvent.entityLiving.posX), UT.Code.roundDown(aEvent.entityLiving.posY), UT.Code.roundDown(aEvent.entityLiving.posZ)), (EntityPlayerMP)aEvent.entityLiving);
	}
	
	@SubscribeEvent
	public void onLoginEvent(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent aEvent) {
		if (DISABLE_ALL_IC2_COMPRESSOR_RECIPES) ic2.api.recipe.Recipes.compressor.getRecipes().clear();
		if (DISABLE_ALL_IC2_EXTRACTOR_RECIPES ) ic2.api.recipe.Recipes.extractor .getRecipes().clear();
		if (DISABLE_ALL_IC2_MACERATOR_RECIPES ) ic2.api.recipe.Recipes.macerator .getRecipes().clear();
		if (DISABLE_ALL_IC2_OREWASHER_RECIPES ) ic2.api.recipe.Recipes.oreWashing.getRecipes().clear();
		if (DISABLE_ALL_IC2_CENTRIFUGE_RECIPES) ic2.api.recipe.Recipes.centrifuge.getRecipes().clear();
		
		if (aEvent.player.worldObj.isRemote) return;
		if (aEvent.player instanceof EntityPlayerMP) mNewPlayers.add((EntityPlayerMP)aEvent.player);
	}
	
	@Override
	public void generate(Random aRandom, int aChunkX, int aChunkZ, World aWorld, IChunkProvider aChunkGenerator, IChunkProvider aChunkProvider) {
		GT6WorldGenerator.generate(aWorld, aChunkX << 4, aChunkZ << 4, F);
	}
	/*
	@SubscribeEvent
	public void populate(PopulateChunkEvent.Post aEvent) {
		WorldGeneratorGT6.generate(aEvent.world, aEvent.chunkX << 4, aEvent.chunkZ << 4, F);
	}
	*/
	
	@SubscribeEvent
	public void onItemExpireEvent(ItemExpireEvent aEvent) {
		if (aEvent.entity.worldObj.isRemote) return;
		ItemStack aStack = aEvent.entityItem.getEntityItem();
		int aX = UT.Code.roundDown(aEvent.entity.posX), aY = UT.Code.roundDown(aEvent.entity.posY), aZ = UT.Code.roundDown(aEvent.entity.posZ);
		if (ST.valid(aStack)) {
			if (ST.item_(aStack) instanceof MultiTileEntityItemInternal) {
				long tExtraLife = ((MultiTileEntityItemInternal)ST.item_(aStack)).onDespawn(aEvent.entityItem, aStack);
				if (aStack.stackSize <= 0) {
					aEvent.extraLife = 0;
					aEvent.entityItem.setDead();
					aEvent.setCanceled(T);
					return;
				}
				aEvent.entityItem.setEntityItemStack(aStack);
				if (tExtraLife > 0) {
					aEvent.extraLife = UT.Code.bindInt(aEvent.extraLife + tExtraLife);
					aEvent.setCanceled(T);
					return;
				}
			}
			MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
			if (tRegistry != null) {
				OreDictItemData tData = OM.anydata(aStack);
				if (tData != null) {
					if (tData.mPrefix == OP.rockGt || tData.mPrefix == OP.oreRaw) for (byte[] tOff : CUBE_3) if (WD.irrelevant(aEvent.entity.worldObj, aX+tOff[0], aY+tOff[1], aZ+tOff[2]) && tRegistry.mBlock.placeBlock(aEvent.entity.worldObj, aX+tOff[0], aY+tOff[1], aZ+tOff[2], SIDE_TOP, (short)32074, ST.save(NBT_VALUE, aStack), T, F)) {aStack.stackSize = 0; aEvent.extraLife = 0; aEvent.entityItem.setDead(); aEvent.setCanceled(T); return;}
					if (tData.mPrefix == OP.ingot                               ) for (byte[] tOff : CUBE_3) if (WD.irrelevant(aEvent.entity.worldObj, aX+tOff[0], aY+tOff[1], aZ+tOff[2]) && tRegistry.mBlock.placeBlock(aEvent.entity.worldObj, aX+tOff[0], aY+tOff[1], aZ+tOff[2], SIDE_TOP, (short)32084, ST.save(NBT_VALUE, aStack), T, F)) {aStack.stackSize = 0; aEvent.extraLife = 0; aEvent.entityItem.setDead(); aEvent.setCanceled(T); return;}
					if (tData.mPrefix == OP.plate                               ) for (byte[] tOff : CUBE_3) if (WD.irrelevant(aEvent.entity.worldObj, aX+tOff[0], aY+tOff[1], aZ+tOff[2]) && tRegistry.mBlock.placeBlock(aEvent.entity.worldObj, aX+tOff[0], aY+tOff[1], aZ+tOff[2], SIDE_TOP, (short)32085, ST.save(NBT_VALUE, aStack), T, F)) {aStack.stackSize = 0; aEvent.extraLife = 0; aEvent.entityItem.setDead(); aEvent.setCanceled(T); return;}
					if (tData.mPrefix == OP.plateGem                            ) for (byte[] tOff : CUBE_3) if (WD.irrelevant(aEvent.entity.worldObj, aX+tOff[0], aY+tOff[1], aZ+tOff[2]) && tRegistry.mBlock.placeBlock(aEvent.entity.worldObj, aX+tOff[0], aY+tOff[1], aZ+tOff[2], SIDE_TOP, (short)32086, ST.save(NBT_VALUE, aStack), T, F)) {aStack.stackSize = 0; aEvent.extraLife = 0; aEvent.entityItem.setDead(); aEvent.setCanceled(T); return;}
					if (tData.mPrefix == OP.scrapGt                             ) for (byte[] tOff : CUBE_3) if (WD.irrelevant(aEvent.entity.worldObj, aX+tOff[0], aY+tOff[1], aZ+tOff[2]) && tRegistry.mBlock.placeBlock(aEvent.entity.worldObj, aX+tOff[0], aY+tOff[1], aZ+tOff[2], SIDE_TOP, (short)32103, ST.save(NBT_VALUE, aStack), T, F)) {aStack.stackSize = 0; aEvent.extraLife = 0; aEvent.entityItem.setDead(); aEvent.setCanceled(T); return;}
				}
			}
			GarbageGT.trash(aStack);
			aStack.stackSize = 0;
			aEvent.extraLife = 0;
			aEvent.entityItem.setEntityItemStack(aStack);
			aEvent.entityItem.setDead();
			aEvent.setCanceled(T);
			return;
		}
	}
	
	@SubscribeEvent
	public void onCheckSpawnEvent(LivingSpawnEvent.CheckSpawn aEvent) {
		if (aEvent.getResult() == Result.DENY) return;
		Class<? extends EntityLivingBase> aMobClass = aEvent.entityLiving.getClass();
		World aWorld = aEvent.world;
		int aX = UT.Code.roundDown(aEvent.x), aY = (int)UT.Code.bind(0, aWorld.getHeight(), UT.Code.roundDown(aEvent.y)), aZ = UT.Code.roundDown(aEvent.z);
		
		if (SPAWN_NO_BATS && aMobClass == EntityBat.class && aWorld.getBlock(aX, aY-2, aZ) != Blocks.stone && aWorld.getBlock(aX, aY+2, aZ) != Blocks.stone) {aEvent.setResult(Result.DENY); return;}
		
		if (!WD.dimOverworldLike(aWorld)) return;
		if (SPAWN_HOSTILES_ONLY_IN_DARKNESS) try {
			Chunk tChunk = aWorld.getChunkFromBlockCoords(aX, aZ);
			if (tChunk != null && tChunk.getBlockStorageArray() != null && tChunk.getBlockStorageArray()[aY >> 4] != null && tChunk.getBlockStorageArray()[aY >> 4].getExtBlocklightValue(aX & 15, aY & 15, aZ & 15) > 0) {
				// Vanilla Mobs only, just in case.
				if (aMobClass == EntityCreeper.class || aMobClass == EntityEnderman.class || aMobClass == EntitySkeleton.class || aMobClass == EntityZombie.class || aMobClass == EntitySpider.class || aMobClass == EntityWitch.class || aMobClass == EntityBat.class) {aEvent.setResult(Result.DENY); return;}
				// Well, that Zombie is kindof like Vanilla, so it counts.
				if (MD.TC.mLoaded) if (aEvent.entityLiving instanceof EntityBrainyZombie) {aEvent.setResult(Result.DENY); return;}
				// TODO Add Drowned and other Et Futurum Requiem Mobs once they are released.
				if (MD.EtFu.mLoaded) if (aEvent.entityLiving instanceof EntityZombieVillager || aEvent.entityLiving instanceof EntityStray || aEvent.entityLiving instanceof EntityHusk) {aEvent.setResult(Result.DENY); return;}
			}
		} catch(Throwable e) {e.printStackTrace(ERR);}
		
		if (aWorld.provider.dimensionId != 0 || aY + 16 < WD.waterLevel(aWorld)) return;
		if (GENERATE_BIOMES) {
			if (UT.Code.inside(-96,  95, aX) && UT.Code.inside(-96,  95, aZ)) {aEvent.setResult(Result.DENY); return;}
		} else if (GENERATE_NEXUS) {
			if (UT.Code.inside(  0,  48, aX) && UT.Code.inside(-64, -16, aZ)) {aEvent.setResult(Result.DENY); return;}
		}
		if (GENERATE_STREETS && (UT.Code.inside(-48, 48, aX) || UT.Code.inside(-48, 48, aZ))) {aEvent.setResult(Result.DENY); return;}
		if (SPAWN_ZONE_MOB_PROTECTION && UT.Code.inside(-144, 144, aX-aWorld.getWorldInfo().getSpawnX()) && UT.Code.inside(-144, 144, aZ-aWorld.getWorldInfo().getSpawnZ()) && WD.opq(aWorld, aX, 0, aZ, F, F)) {aEvent.setResult(Result.DENY); return;}
	}
	
	@SubscribeEvent
	public void onEntitySpawningEvent(EntityJoinWorldEvent aEvent) {
		if (aEvent.entity instanceof EntityItem && !aEvent.entity.worldObj.isRemote) {
			ItemStack aStack = ST.update(OM.get(((EntityItem)aEvent.entity).getEntityItem()), aEvent.entity);
			if (ST.valid(aStack) && aStack.stackSize > 0) {
				if (ST.meta_(aStack) == W || ST.item_(aStack) == Items.gold_nugget) ST.meta(aStack, 0);
				if (((EntityItem)aEvent.entity).lifespan > 1200) {
					if (ST.item_(aStack) == Items.egg || ST.item_(aStack) == Items.feather || ST.item_(aStack) == Items.apple) {
						((EntityItem)aEvent.entity).lifespan = 1200;
					} else {
						if (((EntityItem)aEvent.entity).lifespan == 6000) {
							((EntityItem)aEvent.entity).lifespan = ITEM_DESPAWN_TIME;
						}
					}
				}
				// Result was valid so set the ItemStack.
				((EntityItem)aEvent.entity).setEntityItemStack(aStack);
			} else {
				// Result was invalid therefore kill the Stack.
				aEvent.entity.setDead();
				return;
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityConstructingEvent(EntityConstructing aEvent) {
		if (Abstract_Mod.sFinalized >= Abstract_Mod.sModCountUsingGTAPI && aEvent.entity instanceof EntityPlayer) EntityFoodTracker.add((EntityPlayer)aEvent.entity);
	}
	
	@SubscribeEvent
	public void onArrowNockEvent(ArrowNockEvent aEvent) {
		if (!aEvent.isCanceled() && ST.valid(aEvent.result) && UT.Inventories.getProjectile(TD.Projectiles.ARROW, aEvent.entityPlayer.inventory) != null) {
			aEvent.entityPlayer.setItemInUse(aEvent.result, aEvent.result.getItem().getMaxItemUseDuration(aEvent.result));
			aEvent.setCanceled(T);
		}
	}
	
	@SubscribeEvent
	public void onArrowLooseEvent(ArrowLooseEvent aEvent) {
		ItemStack aArrow = UT.Inventories.getProjectile(TD.Projectiles.ARROW, aEvent.entityPlayer.inventory);
		if (!aEvent.isCanceled() && ST.valid(aEvent.bow) && aArrow != null && aEvent.bow.getItem() instanceof ItemBow) {
			float tSpeed = aEvent.charge / 20.0F;
			tSpeed = (tSpeed * tSpeed + tSpeed * 2.0F) / 3.0F;
			
			if (tSpeed < 0.1) return;
			if (tSpeed > 1.0) tSpeed = 1.0F;
			
			EntityProjectile tArrowEntity = ((IItemProjectile)aArrow.getItem()).getProjectile(TD.Projectiles.ARROW, aArrow, aEvent.entityPlayer.worldObj, aEvent.entityPlayer, tSpeed * 2.0F);
			
			if (tSpeed >= 1.0F) tArrowEntity.setIsCritical(T);
			
			int
			tLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, aEvent.bow);
			if (tLevel > 0) tArrowEntity.setDamage(tArrowEntity.getDamage() + tLevel * 0.5D + 0.5D);
			tLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, aEvent.bow);
			if (tLevel > 0) tArrowEntity.setKnockbackStrength(tLevel);
			tLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, aEvent.bow);
			if (tLevel > 0) tArrowEntity.setFire(tLevel * 100);
			
			aEvent.bow.damageItem(1, aEvent.entityPlayer);
			aEvent.bow.getItem();
			aEvent.entityPlayer.worldObj.playSoundAtEntity(aEvent.entityPlayer, "random.bow", 1.0F, 1.0F / (RNGSUS.nextFloat() * 0.4F + 1.2F) + tSpeed * 0.5F);
			
			tArrowEntity.canBePickedUp = 1;
			
			if (!UT.Entities.hasInfiniteItems(aEvent.entityPlayer)) aArrow.stackSize--;
			if (aArrow.stackSize == 0) UT.Inventories.removeNullStacksFromInventory(aEvent.entityPlayer.inventory);
			
			if (!aEvent.entityPlayer.worldObj.isRemote) aEvent.entityPlayer.worldObj.spawnEntityInWorld(tArrowEntity);
			
			aEvent.setCanceled(T);
		}
	}
	
	@Override
	public int getBurnTime(ItemStack aFuel) {
		if (ST.invalid(aFuel) || FL.getFluid(aFuel, T) != null) return 0;
		Block aBlock = ST.block(aFuel);
		if (aBlock instanceof BlockRailBase                                  ) return 0; // Needed so Railcrafts Tunnel Bore works properly and doesn't try to burn its Rails while laying them.
		if (aBlock instanceof BlockHugeMushroom                              ) return (3 * TICKS_PER_SMELT) / 2;
		if (aBlock == BlocksGT.BalesGrass                                    ) return (9 * TICKS_PER_SMELT) / ((ST.meta_(aFuel) & 3) == 1 ? 2 : 4);
		if (aBlock instanceof BlockBaseBale                                  ) return (9 * TICKS_PER_SMELT) / 4;
		if (aBlock instanceof BlockBasePlanks                                ) return (3 * TICKS_PER_SMELT) / 2;
		if (aBlock instanceof BlockBaseSapling                               ) return      TICKS_PER_SMELT  / 2;
		if (aBlock instanceof BlockBaseBeam || aBlock instanceof BlockBaseLog) return  6 * TICKS_PER_SMELT     ;
		long rFuelValue = UT.NBT.getNBT(aFuel).getLong(NBT_FUEL_VALUE);
		if (aFuel.getItem() instanceof MultiItemRandom) {
			Short tFuelValue = ((MultiItemRandom)aFuel.getItem()).mBurnValues.get(ST.meta_(aFuel));
			if (tFuelValue != null) rFuelValue = Math.max(rFuelValue, tFuelValue);
		} else {
			if (OD.plankAnyWood.is_(aFuel)) return 3 * TICKS_PER_SMELT / 2;
			if (OD.logWood     .is_(aFuel)) return 6 * TICKS_PER_SMELT    ;
			if (OD.itemResin   .is_(aFuel)) return     TICKS_PER_SMELT / 2;
		}
		
		OreDictItemData tData = OM.anydata_(aFuel);
		if (tData != null) {
			long tBurnTime = 0;
			if (tData.mPrefix == OP.oreRaw) {
				tBurnTime = tData.mMaterial.mMaterial.mFurnaceBurnTime;
			} else if (tData.mPrefix == OP.blockRaw) {
				tBurnTime = tData.mMaterial.mMaterial.mFurnaceBurnTime * 10;
			} else if (tData.mPrefix == null || tData.mPrefix.contains(TD.Prefix.BURNABLE)) {
				for (OreDictMaterialStack tMaterial : tData.getAllMaterialStacks()) tBurnTime += UT.Code.units(tMaterial.mMaterial.mFurnaceBurnTime, U, tMaterial.mAmount, F);
				if (tData.mPrefix == OP.stick          && ANY.Wood.mToThis.contains(tData.mMaterial.mMaterial)) return (int)UT.Code.bind(0, 32000, Math.max( TICKS_PER_SMELT      /2, tBurnTime));
				if (tData.mPrefix == OP.stickLong      && ANY.Wood.mToThis.contains(tData.mMaterial.mMaterial)) return (int)UT.Code.bind(0, 32000, Math.max( TICKS_PER_SMELT        , tBurnTime));
				if (tData.mPrefix == OP.blockPlate     && ANY.Wood.mToThis.contains(tData.mMaterial.mMaterial)) return (int)UT.Code.bind(0, 32000, Math.max((TICKS_PER_SMELT* 27L)/2, tBurnTime));
				if (tData.mPrefix == OP.crateGtPlate   && ANY.Wood.mToThis.contains(tData.mMaterial.mMaterial)) return (int)UT.Code.bind(0, 32000, Math.max((TICKS_PER_SMELT* 51L)/2, tBurnTime));
				if (tData.mPrefix == OP.crateGt64Plate && ANY.Wood.mToThis.contains(tData.mMaterial.mMaterial)) return (int)UT.Code.bind(0, 32000, Math.max((TICKS_PER_SMELT*195L)/2, tBurnTime));
			}
			rFuelValue = Math.max(rFuelValue, tBurnTime);
		}
		// return at most 160 Smelts, without any fraction smelts.
		return (int)UT.Code.bind(0, 32000, rFuelValue);
	}
}
