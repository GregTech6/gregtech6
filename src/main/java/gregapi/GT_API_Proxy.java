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
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import gregapi.api.Abstract_Mod;
import gregapi.api.Abstract_Proxy;
import gregapi.block.IBlockOnHeadInside;
import gregapi.block.IBlockOnWalkOver;
import gregapi.block.IBlockPlacable;
import gregapi.block.IBlockToolable;
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
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.BooksGT;
import gregapi.data.CS.FluidsGT;
import gregapi.data.CS.FoodsGT;
import gregapi.data.CS.GarbageGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.CS.PotionsGT;
import gregapi.data.CS.SFX;
import gregapi.data.CS.ToolsGT;
import gregapi.enchants.Enchantment_WerewolfDamage;
import gregapi.item.IItemNoGTOverride;
import gregapi.item.IItemProjectile;
import gregapi.item.IItemProjectile.EntityProjectile;
import gregapi.item.IItemRottable.RottingUtil;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.item.multiitem.MultiItemTool;
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
import gregapi.tileentity.ITileEntityErrorable;
import gregapi.tileentity.ITileEntityGUI;
import gregapi.tileentity.ITileEntityNeedsSaving;
import gregapi.tileentity.ITileEntityScheduledUpdate;
import gregapi.tileentity.ITileEntityServerTickPost;
import gregapi.tileentity.ITileEntityServerTickPre;
import gregapi.tileentity.ITileEntitySpecificPlacementBehavior;
import gregapi.tileentity.ITileEntitySynchronising;
import gregapi.tileentity.inventories.ITileEntityBookShelf;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregapi.worldgen.GT6WorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox.TileEntityJukebox;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
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
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

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
	
	public File mSaveLocation = null;
	
	@Override
	public void onProxyBeforeServerStarted(Abstract_Mod aMod, FMLServerStartedEvent aEvent) {
		SERVER_TIME = 0;
		
		if (mSaveLocation == null) {
			OUT.println("WARNING: World Specific Save Files could not be loaded!");
		} else {
			new File(mSaveLocation, "gregtech").mkdirs();
			
			GarbageGT.onServerLoad(mSaveLocation);
			MultiTileEntityRegistry.onServerLoad(mSaveLocation);
			OUT.println("GT_Server: Loaded Special Save Files");
		}
	}
	
	@Override
	public void onProxyAfterServerStopping(Abstract_Mod aMod, FMLServerStoppingEvent aEvent) {
		if (mSaveLocation == null) {
			OUT.println("WARNING: World Specific Save Files could not be saved!");
		} else {
			new File(mSaveLocation, "gregtech").mkdirs();
			
			GarbageGT.onServerSave(mSaveLocation);
			MultiTileEntityRegistry.onServerSave(mSaveLocation);
			OUT.println("GT_Server: Saved Special Save Files");
		}
		
		mSaveLocation = null;
	}
	
	@SubscribeEvent public void onWorldSave  (WorldEvent.Save   aEvent) {if (mSaveLocation == null) mSaveLocation = aEvent.world.getSaveHandler().getWorldDirectory();}
	@SubscribeEvent public void onWorldLoad  (WorldEvent.Load   aEvent) {if (mSaveLocation == null) mSaveLocation = aEvent.world.getSaveHandler().getWorldDirectory();}
	@SubscribeEvent public void onWorldUnload(WorldEvent.Unload aEvent) {if (mSaveLocation == null) mSaveLocation = aEvent.world.getSaveHandler().getWorldDirectory();}
	
	public  static final List<ITileEntityServerTickPre  > SERVER_TICK_PRE                = new ArrayListNoNulls<>(), SERVER_TICK_PR2  = new ArrayListNoNulls<>();
	public  static final List<ITileEntityServerTickPost > SERVER_TICK_POST               = new ArrayListNoNulls<>(), SERVER_TICK_PO2T = new ArrayListNoNulls<>();
	public  static       List<IHasWorldAndCoords>         DELAYED_BLOCK_UPDATES          = new ArrayListNoNulls<>();
	private static       List<IHasWorldAndCoords>         DELAYED_BLOCK_UPDATES_2        = new ArrayListNoNulls<>();
	public  static       List<ITileEntityScheduledUpdate> SCHEDULED_TILEENTITY_UPDATES   = new ArrayListNoNulls<>();
	private static       List<ITileEntityScheduledUpdate> SCHEDULED_TILEENTITY_UPDATES_2 = new ArrayListNoNulls<>();
	
	@SubscribeEvent
	@SuppressWarnings("unchecked")
	public void onServerTick(ServerTickEvent aEvent) {
		if (aEvent.side.isServer()) {
			TICK_LOCK.lock();
			
			// Making sure it is being free'd up in order to prevent exploits or Garbage Collection mishaps.
			LAST_BROKEN_TILEENTITY.set(null);
			
			if (aEvent.phase == Phase.START) {
				if (SERVER_TIME++ == 0) {
					HashSetNoNulls<ItemStack> tStacks = new HashSetNoNulls<>(10000);
					
					if (MD.IC2.mLoaded) try {
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.cannerBottle              .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.centrifuge                .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.compressor                .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.extractor                 .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.macerator                 .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.metalformerCutting        .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.metalformerExtruding      .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.metalformerRolling        .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.matterAmplifier           .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					for (ic2.api.recipe.RecipeOutput tRecipe : ic2.api.recipe.Recipes.oreWashing                .getRecipes().values()) for (ItemStack tStack : tRecipe.items) tStacks.add(tStack);
					} catch(Throwable e) {e.printStackTrace(ERR);}
					
					if (MD.RC.mLoaded) {
					try {for (Object  tRecipe : mods.railcraft.api.crafting.RailcraftCraftingManager.blastFurnace  .getRecipes   ()) tStacks.add((ItemStack)UT.Reflection.getFieldContent(tRecipe, "output"));} catch(Throwable e) {e.printStackTrace(ERR);}
					try {for (Object  tRecipe : mods.railcraft.api.crafting.RailcraftCraftingManager.cokeOven      .getRecipes   ()) tStacks.add((ItemStack)UT.Reflection.getFieldContent(tRecipe, "output"));} catch(Throwable e) {e.printStackTrace(ERR);}
					try {for (Object  tRecipe : mods.railcraft.api.crafting.RailcraftCraftingManager.rockCrusher   .getRecipes   ()) for (Map.Entry<ItemStack, Float> tEntry : (List<Map.Entry<ItemStack, Float>>)UT.Reflection.getFieldContent(tRecipe, "outputs")) tStacks.add(tEntry.getKey());} catch(Throwable e) {e.printStackTrace(ERR);}
					try {for (IRecipe tRecipe : mods.railcraft.api.crafting.RailcraftCraftingManager.rollingMachine.getRecipeList()) if (tRecipe != null) tStacks.add(tRecipe.getRecipeOutput());} catch(Throwable e) {e.printStackTrace(ERR);}
					}
					
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST            ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.BONUS_CHEST              ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH       ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CROSSING      ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY       ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR      ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_DISPENSER ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST     ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST     ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					for (WeightedRandomChestContent tContent : ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR       ).getItems(RNGSUS)) tStacks.add(tContent.theItemId);
					
					for (Object tStack : FurnaceRecipes.smelting().getSmeltingList().values()) tStacks.add((ItemStack)tStack);
					
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
					
					OUT.println("GT_API: Cleaning up all OreDict Crafting Recipes, which have an empty List in them, since they are never meeting any Condition.");
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
				@SuppressWarnings("rawtypes")
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
				TICK_LOCK.unlock();
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
						
						// TODO make a case for Armor too whenever I decide to ever add Armor.
						if (rStack.getItem() instanceof MultiItemTool) {
							if (MultiItemTool.getPrimaryMaterial  (aStack).contains(TD.Properties.UNBURNABLE)) tFireProof = T;
							if (MultiItemTool.getSecondaryMaterial(aStack).contains(TD.Properties.UNBURNABLE)) tFireProof = T;
						}
						OreDictItemData aData = OM.anydata_(rStack);
						if (aData != null) {
							if (aData.mPrefix != null) for (IOreDictListenerItem tListener : aData.mPrefix.mListenersItem) {
								rStack = tListener.onTickWorld(aData.mPrefix, aData.mMaterial.mMaterial, rStack, (EntityItem)aEntity);
								if (!ST.equal(rStack, aStack) || rStack.stackSize != aStack.stackSize) {
									((EntityItem)aEntity).delayBeforeCanPickup = 40;
									tBreak = T;
									break;
								}
							}
							if (!tBreak && aData.mMaterial != null) for (OreDictMaterialStack tMaterial : aData.getAllMaterialStacks()) {
								if (tMaterial.mMaterial.contains(TD.Properties.UNBURNABLE)) tFireProof = T;
								if (tBreak) break;
								for (IOreDictListenerItem tListener : tMaterial.mMaterial.mListenersItem) {
									rStack = tListener.onTickWorld(aData.mPrefix, tMaterial.mMaterial, rStack, (EntityItem)aEntity);
									if (!ST.equal(rStack, aStack) || rStack.stackSize != aStack.stackSize) {
										((EntityItem)aEntity).delayBeforeCanPickup = 40;
										tBreak = T;
										break;
									}
								}
							}
							if (rStack == null || rStack.stackSize <= 0) {
								((EntityItem)aEntity).setEntityItemStack(NI);
								((EntityItem)aEntity).setDead();
							} else {
								((EntityItem)aEntity).setEntityItemStack(rStack);
							}
						}
						
						if (!aEntity.isDead && tFireProof && aEntity.isBurning()) {
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
						if (aEntityCount > ENTITY_CRAMMING) aEntity.attackEntityFrom(DamageSource.inWall, aEntityCount - ENTITY_CRAMMING);
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
				mSaveLocation = aEvent.world.getSaveHandler().getWorldDirectory();
				
				for (int i = 0; i < aEvent.world.loadedTileEntityList.size(); i++) {
					TileEntity aTileEntity = (TileEntity)aEvent.world.loadedTileEntityList.get(i);
					if (aTileEntity instanceof ITileEntityNeedsSaving) aTileEntity.getWorldObj().getChunkFromBlockCoords(aTileEntity.xCoord, aTileEntity.zCoord).setChunkModified();
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
				final boolean tHungerEffect = (HUNGER_BY_INVENTORY_WEIGHT && aEvent.player.ticksExisted % 2400 == 1200), tBetweenlands = WD.dimBTL(aEvent.player.worldObj.provider), tCrazyJ1984 = "CrazyJ1984".equalsIgnoreCase(aEvent.player.getCommandSenderName());
				if (aEvent.player.ticksExisted % 120 == 0) {
					ItemStack tStack;
					int tCount = 64, tEmptySlots = 36;
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
								if ((tData.mMaterial.mMaterial == MT.Bedrockium || tData.mMaterial.mMaterial == MT.Neutronium) && (tData.mPrefix != null || tData.mByProducts.length <= 0)) {
									PotionEffect tEffect = null;
									aEvent.player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, Math.max(140, ((tEffect = aEvent.player.getActivePotionEffect(Potion.moveSlowdown))==null?0:tEffect.getDuration())), 3));
								}
								if (tCrazyJ1984 && !tStack.hasTagCompound() && tData.hasValidPrefixData() && tData.mPrefix.mNameInternal.startsWith("gem")) {
									if (tData.mMaterial.mMaterial == MT.Diamond  ) ST.name_(tStack, tData.mPrefix.mMaterialPre + MT.Craponite.mNameLocal + tData.mPrefix.mMaterialPost);
									if (tData.mMaterial.mMaterial == MT.Craponite) ST.name_(tStack, tData.mPrefix.mMaterialPre + MT.Diamond  .mNameLocal + tData.mPrefix.mMaterialPost);
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
										switch(tEmptySlots) {
										case 0: UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "You still have a lot of empty Slots left... In your 2x2 Crafting Grid.")); break;
										case 1: UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "There is like a Gazillion Slots left in your Inventory... If you use that one Slot for a Backpack.")); break;
										case 2: UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "You shouldn't clean up your Inventory... If you want it to be full soon.")); break;
										case 3: UT.Entities.chat(tPlayer, new ChatComponentText(CHAT_GREG + "Your Inventory is not going to get full... If you stop collecting Items.")); break;
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
					
					for (int i = 0; i < 4; i++) if ((tStack = aEvent.player.inventory.armorInventory[i]) != null) {
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
		if (!UT.Entities.isPlayer(aEvent.entityPlayer) || aEvent.original == null || UT.Entities.hasInfiniteItems(aEvent.entityPlayer)) return;
		ItemStack[] tInv = aEvent.entityPlayer.inventory.mainInventory;
		if (tInv.length != 36) return;
		int tSlot = aEvent.entityPlayer.inventory.currentItem;
		ItemStack tCompare = ST.amount(1, aEvent.original);
		if (tCompare == null || OP.scrapGt.contains(tCompare)) tCompare = ST.make(ToolsGT.sMetaTool, 1, W);
		
		if (OP.scrapGt.contains(tInv[tSlot])) {
			ItemStack tScrap = ST.copy(tInv[tSlot]);
			tInv[tSlot] = ST.make(Blocks.cobblestone, 0, 0);
			UT.Inventories.addStackToPlayerInventoryOrDrop(aEvent.entityPlayer, tScrap, F);
			tInv[tSlot] = null;
		}
		
		if (ST.food(tCompare) <= 0 && aEvent.original.getItemUseAction() != EnumAction.eat && aEvent.original.getItemUseAction() != EnumAction.drink && (tInv[tSlot] == null || tInv[tSlot].stackSize == 0)) {
			if (tSlot < 9) {
				if (ST.equal(tCompare, tInv[tSlot+27], T)) {
				if (ST.equal(tCompare, tInv[tSlot+18], T)) {
				if (ST.equal(tCompare, tInv[tSlot+ 9], T)) {
				tInv[tSlot] = tInv[tSlot+ 9]; tInv[tSlot+ 9] = null; if (aEvent.entityPlayer.openContainer != null) aEvent.entityPlayer.openContainer.detectAndSendChanges(); return;}
				tInv[tSlot] = tInv[tSlot+18]; tInv[tSlot+18] = null; if (aEvent.entityPlayer.openContainer != null) aEvent.entityPlayer.openContainer.detectAndSendChanges(); return;}
				tInv[tSlot] = tInv[tSlot+27]; tInv[tSlot+27] = null; if (aEvent.entityPlayer.openContainer != null) aEvent.entityPlayer.openContainer.detectAndSendChanges(); return;}
				return;
			}
			if (tSlot < 18) {
				if (ST.equal(tCompare, tInv[tSlot+18], T)) {
				if (ST.equal(tCompare, tInv[tSlot+ 9], T)) {
				tInv[tSlot] = tInv[tSlot+ 9]; tInv[tSlot+ 9] = null; if (aEvent.entityPlayer.openContainer != null) aEvent.entityPlayer.openContainer.detectAndSendChanges(); return;}
				tInv[tSlot] = tInv[tSlot+18]; tInv[tSlot+18] = null; if (aEvent.entityPlayer.openContainer != null) aEvent.entityPlayer.openContainer.detectAndSendChanges(); return;}
				return;
			}
			if (tSlot < 27) {
				if (ST.equal(tCompare, tInv[tSlot+ 9], T)) {
				tInv[tSlot] = tInv[tSlot+ 9]; tInv[tSlot+ 9] = null; if (aEvent.entityPlayer.openContainer != null) aEvent.entityPlayer.openContainer.detectAndSendChanges(); return;}
				return;
			}
		}
	}
	
	@SubscribeEvent
	public void onItemUseFinish(PlayerUseItemEvent.Finish aEvent) {
		int[] tStats = FoodsGT.get(aEvent.item);
		if (tStats != null) {
			EntityFoodTracker tTracker = EntityFoodTracker.get(aEvent.entityPlayer);
			if (tTracker != null) {
				if (tStats.length > 0 && tStats[0] != 0) tTracker.changeAlcohol(tStats[0]);
				if (tStats.length > 1 && tStats[1] != 0) tTracker.changeCaffeine(tStats[1]);
				if (tStats.length > 2 && tStats[2] != 0) tTracker.changeDehydration(tStats[2]);
				if (tStats.length > 3 && tStats[3] != 0) tTracker.changeSugar(tStats[3]);
				if (tStats.length > 4 && tStats[4] != 0) tTracker.changeFat(tStats[4]);
			}
		}
		
		NBTTagCompound tNBT = aEvent.item.getTagCompound();
		if (tNBT != null && tNBT.hasKey(NBT_EFFECTS)) {
			tNBT = tNBT.getCompoundTag(NBT_EFFECTS);
			int tID = tNBT.getInteger("id"), tTime = tNBT.getInteger("time"), tLevel = tNBT.getInteger("lvl"), tChance = tNBT.getInteger("chance");
			if (tID < -1) switch(tID) {
			case -2: tID = PotionsGT.ID_RADIATION; break;
			case -3: tID = PotionsGT.ID_HYPOTHERMIA; break;
			case -4: tID = PotionsGT.ID_HEATSTROKE; break;
			case -5: tID = PotionsGT.ID_FROSTBITE; break;
			case -6: tID = PotionsGT.ID_DEHYDRATION; break;
			case -7: tID = PotionsGT.ID_INSANITY; break;
			}
			if (tID >= 0 && RNGSUS.nextInt(100) < tChance) {
				if (tLevel >= 0) {
					aEvent.entityPlayer.addPotionEffect(new PotionEffect(tID, tTime, tLevel, F));
				} else {
					aEvent.entityPlayer.removePotionEffect(tID);
				}
			}
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
							UT.Entities.chat(aEvent.entityPlayer, CHAT_GREG + "The Code of this Dolly is not smart enough to move this TileEntity.", CHAT_GREG + "It would crash if it actually did, so be glad I prevented that mistake.");
							aEvent.setCanceled(T);
						}
						return;
					}
					// Instant breaking for those Wrenches.
					if (IL.BC_Wrench.equal(aStack, T, T) || IL.FR_Wrench.equal(aStack, T, T) || IL.AE_Wrench_Certus.equal(aStack, T, T) || IL.AE_Wrench_Quartz.equal(aStack, T, T) || IL.TE_Wrench.equal(aStack, T, T) || IL.TE_Wrench_Battle.equal(aStack, T, T)) {
						List<String> tChatReturn = new ArrayListNoNulls<>();
						long tDamage = IBlockToolable.Util.onToolClick(TOOL_wrench, Long.MAX_VALUE, 3, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.entityPlayer.worldObj, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
						UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
						if (tDamage > 0) {
							ST.use(aEvent.entityPlayer, aStack);
							UT.Sounds.send(SFX.MC_BREAK, aEvent.entityPlayer);
							aEvent.setCanceled(T);
						}
						return;
					}
					// Instant breaking for those Hammers.
					if (IL.IE_Hammer.equal(aStack, T, T)) {
						List<String> tChatReturn = new ArrayListNoNulls<>();
						long tDamage = IBlockToolable.Util.onToolClick(TOOL_hammer, Long.MAX_VALUE, 3, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.entityPlayer.worldObj, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
						UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
						if (tDamage > 0) {
							ST.use(aEvent.entityPlayer, aStack);
							UT.Sounds.send(SFX.MC_BREAK, aEvent.entityPlayer);
							aEvent.setCanceled(T);
						}
						return;
					}
					// Make Railcrafts Crowbars work on GT6 Stuff.
					if (IL.RC_Crowbar_Iron.equal(aStack, T, T) || IL.RC_Crowbar_Steel.equal(aStack, T, T) || IL.RC_Crowbar_Thaumium.equal(aStack, T, T) || IL.RC_Crowbar_Voidmetal.equal(aStack, T, T)) {
						List<String> tChatReturn = new ArrayListNoNulls<>();
						long tDamage = IBlockToolable.Util.onToolClick(TOOL_crowbar, Long.MAX_VALUE, 2, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.entityPlayer.worldObj, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
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
						long tDamage = IBlockToolable.Util.onToolClick(TOOL_scoop, Long.MAX_VALUE, 0, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.world, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
						UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
						if (tDamage > 0) {
							aStack.damageItem((int)UT.Code.units(tDamage, 10000, 1, T), aEvent.entityPlayer);
							if (aStack.getItemDamage() >= aStack.getMaxDamage()) ST.use(aEvent.entityPlayer, aStack);
							aEvent.setCanceled(T);
						}
						return;
					}
					// Make Twilight Forests Lamp of Cinders work as infinite Flint and Steel on TNT and GT6 Machines
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
		
		// Make sure that shelvable Items don't do a Rightclick Action instead of being shelved.
		if (aEvent.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK || aEvent.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
			if (ST.valid(aStack)) {
				if (aTileEntity instanceof ITileEntityBookShelf && ((ITileEntityBookShelf)aTileEntity).isShelfFace((byte)aEvent.face)) {
					aEvent.useBlock = Result.ALLOW;
					if (BooksGT.BOOK_REGISTER.containsKey(aStack, T)) aEvent.useItem = Result.DENY;
					return;
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
	public void onBlockHarvestingEvent(BlockEvent.HarvestDropsEvent aEvent) {
		Iterator<ItemStack> aDrops = aEvent.drops.iterator();
		while (aDrops.hasNext()) {
			ItemStack tDrop = aDrops.next();
			if (ST.invalid(tDrop) || ItemsGT.ILLEGAL_DROPS.contains(tDrop, T)) {aDrops.remove(); continue;}
			if (ST.item_(tDrop) == Items.gold_nugget) ST.meta_(tDrop, 0);
		}
		
		if (aEvent.block == Blocks.dirt && aEvent.blockMetadata == 1) for (int i = 0, j = aEvent.drops.size(); i < j; i++) if (ST.block(aEvent.drops.get(0)) == Blocks.dirt) {
			aEvent.drops.set(i, ST.make(Blocks.dirt, aEvent.drops.get(i).stackSize, 1));
		}
		
		if (aEvent.harvester != null) {
			if (FAST_LEAF_DECAY) WD.leafdecay(aEvent.world, aEvent.x, aEvent.y, aEvent.z, aEvent.block, F);
			ItemStack aStack = aEvent.harvester.getCurrentEquippedItem();
			if (aStack != null) {
				boolean tFireAspect = (EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, aStack) >= 3), tCanCollect = (aStack.getItem() instanceof MultiItemTool && ((MultiItemTool)aStack.getItem()).canCollectDropsDirectly(aStack, aEvent.block, (byte)aEvent.blockMetadata));
				if (aStack.getItem() instanceof MultiItemTool) {
					((MultiItemTool)aStack.getItem()).onHarvestBlockEvent(aEvent.drops, aStack, aEvent.harvester, aEvent.block, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.blockMetadata, aEvent.fortuneLevel, aEvent.isSilkTouching, aEvent);
				}
				
				if (tFireAspect) {
				//  if (aEvent.world.isRemote) for (int i = 0; i < 4; i++) {
				//      double tX = RNGSUS.nextGaussian()/50, tY = RNGSUS.nextGaussian()/50, tZ = RNGSUS.nextGaussian()/50;
				//      aEvent.world.spawnParticle("flame", aEvent.x+0.5+tX*20, aEvent.y+0.5+tY*20, aEvent.z+0.5+tZ*20,-tX,-tY,-tZ);
				//  }
					for (ItemStack tDrop : aEvent.drops) {
						ItemStack tSmeltingOutput = RM.get_smelting(tDrop, F, null);
						if (tSmeltingOutput != null) {
							tDrop.stackSize *= tSmeltingOutput.stackSize;
							ST.set(tDrop, tSmeltingOutput, F, T);
						}
					}
				}
				
				for (ItemStack tDrop : aEvent.drops) {
					OM.set(tDrop);
					// TODO Hashmap (one for Silk Touching and another for normal Harvesting)
					if (MD.GT.mLoaded) {
					if (IL.CHSL_Granite            .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Granite , 1, aEvent.isSilkTouching ? 0 : 1), F, F); else
					if (IL.EtFu_Granite            .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Granite , 1, aEvent.isSilkTouching ? 0 : 1), F, F); else
					if (IL.GaSu_Granite            .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Granite , 1, aEvent.isSilkTouching ? 0 : 1), F, F); else
					if (IL.BOTA_Granite            .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Granite , 1, aEvent.isSilkTouching ? 0 : 1), F, F); else
					if (IL.CHSL_Granite_Smooth     .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Granite , 1, 7), F, F); else
					if (IL.EtFu_Granite_Smooth     .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Granite , 1, 7), F, F); else
					if (IL.GaSu_Granite_Smooth     .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Granite , 1, 7), F, F); else
					if (IL.BOTA_Granite_Smooth     .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Granite , 1, 7), F, F); else
					if (IL.BOTA_Granite_Bricks     .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Granite , 1, 3), F, F); else
					if (IL.BOTA_Granite_Chiseled   .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Granite , 1, 6), F, F); else
					if (IL.CHSL_Diorite            .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Diorite , 1, aEvent.isSilkTouching ? 0 : 1), F, F); else
					if (IL.EtFu_Diorite            .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Diorite , 1, aEvent.isSilkTouching ? 0 : 1), F, F); else
					if (IL.GaSu_Diorite            .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Diorite , 1, aEvent.isSilkTouching ? 0 : 1), F, F); else
					if (IL.BOTA_Diorite            .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Diorite , 1, aEvent.isSilkTouching ? 0 : 1), F, F); else
					if (IL.CHSL_Diorite_Smooth     .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Diorite , 1, 7), F, F); else
					if (IL.EtFu_Diorite_Smooth     .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Diorite , 1, 7), F, F); else
					if (IL.GaSu_Diorite_Smooth     .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Diorite , 1, 7), F, F); else
					if (IL.BOTA_Diorite_Smooth     .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Diorite , 1, 7), F, F); else
					if (IL.BOTA_Diorite_Bricks     .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Diorite , 1, 3), F, F); else
					if (IL.BOTA_Diorite_Chiseled   .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Diorite , 1, 6), F, F); else
					if (IL.CHSL_Andesite           .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Andesite, 1, aEvent.isSilkTouching ? 0 : 1), F, F); else
					if (IL.EtFu_Andesite           .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Andesite, 1, aEvent.isSilkTouching ? 0 : 1), F, F); else
					if (IL.GaSu_Andesite           .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Andesite, 1, aEvent.isSilkTouching ? 0 : 1), F, F); else
					if (IL.BOTA_Andesite           .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Andesite, 1, aEvent.isSilkTouching ? 0 : 1), F, F); else
					if (IL.CHSL_Andesite_Smooth    .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Andesite, 1, 7), F, F); else
					if (IL.EtFu_Andesite_Smooth    .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Andesite, 1, 7), F, F); else
					if (IL.GaSu_Andesite_Smooth    .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Andesite, 1, 7), F, F); else
					if (IL.BOTA_Andesite_Smooth    .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Andesite, 1, 7), F, F); else
					if (IL.BOTA_Andesite_Bricks    .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Andesite, 1, 3), F, F); else
					if (IL.BOTA_Andesite_Chiseled  .equal(tDrop, F, T)) ST.set(tDrop, ST.make(BlocksGT.Andesite, 1, 6), F, F);
					}
					if (MD.NePl.mLoaded && MD.NeLi.mLoaded) {
					if (IL.NePl_Blackstone         .equal(tDrop, F, T)) ST.set(tDrop, IL.NeLi_Blackstone         .get(1), F, F); else
					if (IL.NePl_Blackstone_Polished.equal(tDrop, F, T)) ST.set(tDrop, IL.NeLi_Blackstone_Polished.get(1), F, F); else
					if (IL.NePl_Blackstone_Chiseled.equal(tDrop, F, T)) ST.set(tDrop, IL.NeLi_Blackstone_Chiseled.get(1), F, F); else
					if (IL.NePl_Blackstone_Bricks  .equal(tDrop, F, T)) ST.set(tDrop, IL.NeLi_Blackstone_Bricks  .get(1), F, F); else
					if (IL.NePl_Blackstone_Cracked .equal(tDrop, F, T)) ST.set(tDrop, IL.NeLi_Blackstone_Cracked .get(1), F, F); else
					if (IL.NePl_Basalt             .equal(tDrop, F, T)) ST.set(tDrop, IL.NeLi_Basalt             .get(1), F, F); else
					if (IL.NePl_Basalt_Polished    .equal(tDrop, F, T)) ST.set(tDrop, IL.NeLi_Basalt_Polished    .get(1), F, F);
					}
				}
				
				if (tCanCollect && !aEvent.drops.isEmpty()) {
					boolean aCollectSound = T;
					aDrops = aEvent.drops.iterator();
					while (aDrops.hasNext()) if (UT.Inventories.addStackToPlayerInventory(aEvent.harvester, ST.update(aDrops.next(), aEvent.world, aEvent.x, aEvent.y, aEvent.z))) {
						aDrops.remove();
						if (aCollectSound) {
							UT.Sounds.send(SFX.MC_COLLECT, 0.2F, ((RNGSUS.nextFloat()-RNGSUS.nextFloat())*0.7F+1.0F)*2.0F, aEvent.harvester);
							aCollectSound = F;
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
		if (aEvent.entityItem.worldObj.isRemote) return;
		ItemStack aStack = aEvent.entityItem.getEntityItem();
		if (ST.valid(aStack)) {
			if (aStack.getItem() instanceof MultiTileEntityItemInternal) {
				long tExtraLife = ((MultiTileEntityItemInternal)aStack.getItem()).onDespawn(aEvent.entityItem, aStack);
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
		if (aEvent.getResult() == Result.DENY || aEvent.world.provider.dimensionId != 0 || aEvent.y + 16 < WD.waterLevel(aEvent.world)) return;
		if (GENERATE_BIOMES) {
			if (UT.Code.inside(-96,  95, (int)aEvent.x) && UT.Code.inside(-96,  95, (int)aEvent.z)) {aEvent.setResult(Result.DENY); return;}
		} else if (GENERATE_NEXUS) {
			if (UT.Code.inside(  0,  48, (int)aEvent.x) && UT.Code.inside(-64, -16, (int)aEvent.z)) {aEvent.setResult(Result.DENY); return;}
		}
		if (GENERATE_STREETS && (UT.Code.inside(-48, 48, (int)aEvent.x) || UT.Code.inside(-48, 48, (int)aEvent.z))) {aEvent.setResult(Result.DENY); return;}
		if (SPAWN_ZONE_MOB_PROTECTION && UT.Code.inside(-144, 144, ((int)aEvent.x)-aEvent.world.getWorldInfo().getSpawnX()) && UT.Code.inside(-144, 144, ((int)aEvent.z)-aEvent.world.getWorldInfo().getSpawnZ()) && WD.opq(aEvent.world, (int)aEvent.x, 0, (int)aEvent.z, F, F)) {aEvent.setResult(Result.DENY); return;}
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
		if (aBlock instanceof BlockRailBase) return 0;
		if (aBlock == Blocks.red_mushroom_block || aBlock == Blocks.brown_mushroom_block) return (3 * TICKS_PER_SMELT) / 2;
		if (aBlock == BlocksGT.BalesGrass) return (9 * TICKS_PER_SMELT) / ((ST.meta_(aFuel) & 3) == 1 ? 2 : 4);
		if (aBlock instanceof BlockBaseBale) return (9 * TICKS_PER_SMELT) / 4;
		if (aBlock instanceof BlockBasePlanks) return (3 * TICKS_PER_SMELT) / 2;
		if (aBlock instanceof BlockBaseSapling) return TICKS_PER_SMELT / 2;
		if (aBlock instanceof BlockBaseBeam || aBlock instanceof BlockBaseLog) return TICKS_PER_SMELT * 6;
		long rFuelValue = 0;
		if (aFuel.getItem() instanceof MultiItemRandom) {
			Short tFuelValue = ((MultiItemRandom)aFuel.getItem()).mBurnValues.get(ST.meta_(aFuel));
			if (tFuelValue != null) rFuelValue = Math.max(rFuelValue, tFuelValue);
		} else {
			if (OD.logWood.is_(aFuel)) return TICKS_PER_SMELT * 6;
			if (IL.IC2_Resin.equal(aFuel, F, T)) return TICKS_PER_SMELT / 2;
		}
		NBTTagCompound tNBT = aFuel.getTagCompound();
		if (tNBT != null) {
			rFuelValue = Math.max(rFuelValue, tNBT.getLong(NBT_FUEL_VALUE));
		}
		OreDictItemData tData = OM.anydata_(aFuel);
		if (tData != null && (tData.mPrefix == null || tData.mPrefix.contains(TD.Prefix.BURNABLE))) {
			long tBurnTime = 0;
			for (OreDictMaterialStack tMaterial : tData.getAllMaterialStacks()) tBurnTime += UT.Code.units(tMaterial.mMaterial.mFurnaceBurnTime, U, tMaterial.mAmount, F);
			if (tData.mPrefix == OP.stick          && ANY.Wood.mToThis.contains(tData.mMaterial.mMaterial)) return UT.Code.bind15(Math.max( TICKS_PER_SMELT     /2, tBurnTime));
			if (tData.mPrefix == OP.stickLong      && ANY.Wood.mToThis.contains(tData.mMaterial.mMaterial)) return UT.Code.bind15(Math.max( TICKS_PER_SMELT       , tBurnTime));
			if (tData.mPrefix == OP.blockPlate     && ANY.Wood.mToThis.contains(tData.mMaterial.mMaterial)) return UT.Code.bind15(Math.max((TICKS_PER_SMELT* 27)/2, tBurnTime));
			if (tData.mPrefix == OP.crateGtPlate   && ANY.Wood.mToThis.contains(tData.mMaterial.mMaterial)) return UT.Code.bind15(Math.max((TICKS_PER_SMELT* 51)/2, tBurnTime));
			if (tData.mPrefix == OP.crateGt64Plate && ANY.Wood.mToThis.contains(tData.mMaterial.mMaterial)) return UT.Code.bind15(Math.max((TICKS_PER_SMELT*195)/2, tBurnTime));
			rFuelValue = Math.max(rFuelValue, tBurnTime);
		}
		return UT.Code.bind15(rFuelValue);
	}
}
