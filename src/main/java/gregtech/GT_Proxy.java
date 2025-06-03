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

package gregtech;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import gregapi.GT_API;
import gregapi.api.Abstract_Mod;
import gregapi.api.Abstract_Proxy;
import gregapi.block.IBlockToolable;
import gregapi.block.metatype.BlockStones;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.data.*;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregtech.blocks.fluids.BlockWaterlike;
import gregtech.entities.Override_Drops;
import gregtech.entities.ai.EntityAIBetterAttackOnCollide;
import gregtech.entities.projectiles.EntityArrow_Material;
import gregtech.tileentity.misc.MultiTileEntityCertificate;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.MinecraftForge;
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
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;
import net.minecraftforge.fluids.Fluid;

import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static gregapi.data.CS.*;

public abstract class GT_Proxy extends Abstract_Proxy {
	public final HashSetNoNulls<String> mSupporterListSilver = new HashSetNoNulls<>();
	public final HashSetNoNulls<String> mSupporterListGold = new HashSetNoNulls<>();
	
	public String mMessage = "";
	
	public boolean mDisableVanillaOres = T, mDisableVanillaLakes = T, mVersionOutdated = F;
	public int mSkeletonsShootGTArrows = 16, mFlintChance = 30;
	
	public GT_Proxy() {
		MinecraftForge.EVENT_BUS         .register(this);
		MinecraftForge.ORE_GEN_BUS       .register(this);
		MinecraftForge.TERRAIN_GEN_BUS   .register(this);
		FMLCommonHandler.instance().bus().register(this);
	}
	
	@Override
	public void onProxyBeforePreInit(Abstract_Mod aMod, FMLPreInitializationEvent aEvent) {
		super.onProxyBeforePreInit(aMod, aEvent);
		
		// Because of the whole ban wave Mojang did with their new Microsoft Bullshit Auth System, I am not going to
		// ever add more people to these Lists anymore. So I decided to no longer check those Text Files on my Server.
		// Of-course the Server will still contain said Text Files, I just stop downloading them.
		
		try {
			Scanner tScanner = new Scanner(getClass().getResourceAsStream("/supporterlist.txt"));
			while (tScanner.hasNextLine()) mSupporterListSilver.add(tScanner.nextLine().toLowerCase());
			tScanner.close();
		} catch(Throwable e) {e.printStackTrace(ERR);}
		try {
			Scanner tScanner = new Scanner(getClass().getResourceAsStream("/supporterlistgold.txt"));
			while (tScanner.hasNextLine()) mSupporterListGold.add(tScanner.nextLine().toLowerCase());
			tScanner.close();
		} catch(Throwable e) {e.printStackTrace(ERR);}
		
		// Just making sure there is no overlaps.
		mSupporterListSilver.removeAll(mSupporterListGold);
	}
	
	@SubscribeEvent
	public void onClientConnectedToServerEvent(ClientConnectedToServerEvent aEvent) {
		//
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onEndermanTeleportEvent(EnderTeleportEvent aEvent) {
		if (aEvent.entityLiving instanceof EntityEnderman && aEvent.entityLiving.getActivePotionEffect(Potion.weakness) != null) aEvent.setCanceled(T);
	}
	
	private static final EnumSet<EventType> PREVENTED_ORES = EnumSet.of(EventType.COAL, EventType.IRON, EventType.GOLD, EventType.DIAMOND, EventType.REDSTONE, EventType.LAPIS, EventType.QUARTZ);
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onOreGenEvent(GenerateMinable aEvent) {
		if (mDisableVanillaOres && !WD.dimTF(aEvent.world) && PREVENTED_ORES.contains(aEvent.type)) aEvent.setResult(Result.DENY);
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onTerrainGenEvent(DecorateBiomeEvent.Decorate aEvent) {
		if (aEvent.world.provider.dimensionId == 0) {
			if (MD.RTG.mLoaded) {
				String tClassName = UT.Reflection.getLowercaseClass(aEvent.world.provider.terrainType);
				if ("WorldProviderSurfaceRTG".equalsIgnoreCase(tClassName) || "WorldTypeRTG".equalsIgnoreCase(tClassName)) return;
			}
			if (GENERATE_STREETS && (UT.Code.inside(-48, 47, aEvent.chunkX) || UT.Code.inside(-48, 47, aEvent.chunkZ))) {aEvent.setResult(Result.DENY); return;}
			if (GENERATE_BIOMES  && (UT.Code.inside(-96, 95, aEvent.chunkX) && UT.Code.inside(-96, 95, aEvent.chunkZ))) {aEvent.setResult(Result.DENY); return;}
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onTerrainGenEvent(PopulateChunkEvent.Populate aEvent) {
		if (aEvent.world.provider.dimensionId == 0) {
			if (mDisableVanillaLakes && (aEvent.type == Populate.EventType.LAKE || aEvent.type == Populate.EventType.LAVA)) {aEvent.setResult(Result.DENY); return;}
			if (MD.RTG.mLoaded) {
				String tClassName = UT.Reflection.getLowercaseClass(aEvent.world.provider.terrainType);
				if ("WorldProviderSurfaceRTG".equalsIgnoreCase(tClassName) || "WorldTypeRTG".equalsIgnoreCase(tClassName)) return;
			}
			if (GENERATE_STREETS && (UT.Code.inside(-48, 47, aEvent.chunkX) || UT.Code.inside(-48, 47, aEvent.chunkZ))) {aEvent.setResult(Result.DENY); return;}
			if (GENERATE_BIOMES  && (UT.Code.inside(-96, 95, aEvent.chunkX) && UT.Code.inside(-96, 95, aEvent.chunkZ))) {aEvent.setResult(Result.DENY); return;}
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onGetVillageBlockIDEvent(BiomeEvent.GetVillageBlockID aEvent) {
		if (aEvent.original == Blocks.cobblestone) {
			aEvent.replacement = (aEvent.biome == null ? BlocksGT.Andesite : BlocksGT.stones[(aEvent.biome.biomeID+6) % BlocksGT.stones.length]);
			aEvent.setResult(Result.DENY);
		}
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
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
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onPlayerInteraction(PlayerInteractEvent aEvent) {
		if (aEvent.entityPlayer == null || aEvent.entityPlayer.worldObj == null || aEvent.action == null || aEvent.world.provider == null) return;
		String aName = aEvent.entityPlayer.getCommandSenderName(), aNameLowercase = aName.toLowerCase();
		if (!aEvent.world.isRemote && CHECKED_PLAYERS.add(aName)) {
			if (mSupporterListSilver.contains(aEvent.entityPlayer.getUniqueID().toString()) || mSupporterListGold.contains(aEvent.entityPlayer.getUniqueID().toString()) || mSupporterListSilver.contains(aNameLowercase) || mSupporterListGold.contains(aNameLowercase)) {
				if (!MultiTileEntityCertificate.ALREADY_RECEIVED.contains(aNameLowercase)) {
					if (ST.give(aEvent.entityPlayer, MultiTileEntityCertificate.getCertificate(1, aName), F)) {
						MultiTileEntityCertificate.ALREADY_RECEIVED.add(aNameLowercase);
						UT.Entities.sendchat(aEvent.entityPlayer, CHAT_GREG + "Thank you, " + aName + ", for Supporting GregTech! Here, have a Certificate. ;)");
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
								ST.use(aEvent.entityPlayer, aStack);
								ST.give(aEvent.entityPlayer, ST.make(Items.potionitem, 1, 0), F);
							}
						}
						if (!WD.infiniteWater(aEvent.world, tTarget.blockX, tTarget.blockY, tTarget.blockZ)) aEvent.world.setBlockToAir(tTarget.blockX, tTarget.blockY, tTarget.blockZ);
						ST.update(aEvent.entityPlayer);
						return;
					}
					if (tBlock == BlocksGT.River || WD.waterstream(tBlock)) {
						ItemStack tStack = FL.Water.fill(aStack);
						if (tStack == null) return;
						ST.use(aEvent.entityPlayer, aStack);
						ST.give(aEvent.entityPlayer, tStack, F);
						return;
					}
					if (tBlock == BlocksGT.Ocean) {
						ItemStack tStack = FL.Ocean.fill(aStack);
						if (tStack == null) return;
						ST.use(aEvent.entityPlayer, aStack);
						ST.give(aEvent.entityPlayer, tStack, F);
						return;
					}
					if (tBlock == BlocksGT.Swamp) {
						ItemStack tStack = FL.Dirty_Water.fill(aStack);
						if (tStack == null) return;
						ST.use(aEvent.entityPlayer, aStack);
						ST.give(aEvent.entityPlayer, tStack, F);
						return;
					}
					return;
				}
				if (aStack.getItem() == Items.bucket) {
					MovingObjectPosition tTarget = WD.getMOP(aEvent.world, aEvent.entityPlayer, T);
					if (tTarget != null && tTarget.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
						Block tBlock = aEvent.world.getBlock(tTarget.blockX, tTarget.blockY, tTarget.blockZ);
						if (tBlock instanceof BlockWaterlike && tBlock != BlocksGT.River) aEvent.setCanceled(T);
					}
					return;
				}
			}
			if (aEvent.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
				if (IL.ERE_Spray_Repellant.equal(aStack, T, T)) {
					if (!aEvent.world.isRemote && aStack.getItem().onItemUse(aStack, aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, aEvent.face, 0.5F, 0.5F, 0.5F)) {
						aEvent.setCanceled(T);
						ST.give(aEvent.entityPlayer, IL.Spray_Empty.get(1), aEvent.world, aEvent.x, aEvent.y, aEvent.z);
						return;
					}
				} else if (aStack.getItem() == Items.flint_and_steel) {
					if (!aEvent.world.isRemote && !UT.Entities.hasInfiniteItems(aEvent.entityPlayer) && RNGSUS.nextInt(100) >= mFlintChance) {
						aEvent.setCanceled(T);
						aStack.damageItem(1, aEvent.entityPlayer);
						if (aStack.getItemDamage() >= aStack.getMaxDamage()) ST.use(aEvent.entityPlayer, aStack);
						return;
					}
					List<String> tChatReturn = new ArrayListNoNulls<>();
					long tDamage = IBlockToolable.Util.onToolClick(TOOL_igniter, aStack.getItemDamage()*10000, 1, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.world, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
					UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
					if (tDamage > 0) {
						aEvent.setCanceled(T);
						UT.Sounds.send(SFX.MC_IGNITE, aEvent.world, aEvent.x, aEvent.y, aEvent.z);
						if (!UT.Entities.hasInfiniteItems(aEvent.entityPlayer)) {
							aStack.damageItem(UT.Code.bindInt(UT.Code.units(tDamage, 10000, 1, T)), aEvent.entityPlayer);
							if (aStack.getItemDamage() >= aStack.getMaxDamage()) ST.use(aEvent.entityPlayer, aStack);
						}
						return;
					}
				} else if (IL.Food_Toast_Sliced.equal(aStack, F, T) || IL.Food_Toasted_Sliced.equal(aStack, F, T)) {
					int tUsed = Math.min(16, aStack.stackSize);
					if (!aEvent.world.isRemote && aEvent.entityPlayer.isSneaking() && MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32105, ST.save("sandwich.0", ST.amount(tUsed, aStack))).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
						ST.use(aEvent.entityPlayer, aStack, tUsed); aEvent.setCanceled(T);
					}
				} else if (aStack.getItem() == Items.stick || IL.Stick.equal(aStack) || OM.is("stickAnyNormalWood", aStack)) {
					if (!aEvent.world.isRemote && aEvent.entityPlayer.isSneaking() && MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32073).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
						ST.use(aEvent.entityPlayer, aStack); aEvent.setCanceled(T);
					}
				} else if (aStack.getItem() == Items.flint) {
					if (!aEvent.world.isRemote && aEvent.entityPlayer.isSneaking() && MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32074, ST.save(NBT_VALUE, ST.amount(1, aStack))).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
						ST.use(aEvent.entityPlayer, aStack); aEvent.setCanceled(T);
					}
				} else {
					if (!aEvent.world.isRemote && aEvent.entityPlayer.isSneaking() && ST.block(aStack) == NB) {
						OreDictItemData tData = OM.anyassociation_(aStack);
						if (tData != null) {
							if (tData.mPrefix == OP.rockGt || tData.mPrefix == OP.oreRaw) {
								if (MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32074, ST.save(NBT_VALUE, ST.amount(1, aStack))).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
									ST.use(aEvent.entityPlayer, aStack); aEvent.setCanceled(T);
								}
							}
							if (tData.mPrefix == OP.ingot) if (!MD.BOTA.mLoaded || tData.mMaterial.mMaterial.mOriginalMod != MD.BOTA || Blocks.beacon != aEvent.world.getBlock(aEvent.x, aEvent.y, aEvent.z)) {
								if (MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32084, ST.save(NBT_VALUE, aStack)).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
									ST.use(aEvent.entityPlayer, aStack, aStack.stackSize); aEvent.setCanceled(T);
								}
							}
							if (tData.mPrefix == OP.plate) {
								if (MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32085, ST.save(NBT_VALUE, aStack)).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
									ST.use(aEvent.entityPlayer, aStack, aStack.stackSize); aEvent.setCanceled(T);
								}
							}
							if (tData.mPrefix == OP.plateGem) {
								if (MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32086, ST.save(NBT_VALUE, aStack)).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
									ST.use(aEvent.entityPlayer, aStack, aStack.stackSize); aEvent.setCanceled(T);
								}
							}
							if (tData.mPrefix == OP.scrapGt) {
								if (MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32103, ST.save(NBT_VALUE, aStack)).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
									ST.use(aEvent.entityPlayer, aStack, aStack.stackSize); aEvent.setCanceled(T);
								}
							}
						}
					}
				}
			}
		} else {
			if (aEvent.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
				if (aEvent.entityPlayer.isBurning()) {
					List<String> tChatReturn = new ArrayListNoNulls<>();
					long tDamage = IBlockToolable.Util.onToolClick(TOOL_igniter, Long.MAX_VALUE, 1, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), NI, aEvent.world, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
					UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
					if (tDamage > 0) {
						UT.Sounds.send(SFX.MC_IGNITE, aEvent.world, aEvent.x, aEvent.y, aEvent.z);
						aEvent.setCanceled(T);
					}
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onEntitySpawningEvent(EntityJoinWorldEvent aEvent) {
		if (aEvent.entity == null) return;
		
		if (aEvent.entity instanceof EntityLiving) {
			// AI Tasks for Entities
			EntityAITasks tTasks = ((EntityLiving)aEvent.entity).tasks;
			if (tTasks != null) {
				if (aEvent.entity instanceof EntityVillager) {
					tTasks.addTask(3, new EntityAITempt((EntityCreature)aEvent.entity, 0.6D, Items.emerald, F));
				}
				if (aEvent.entity instanceof EntityOcelot) {
					if (ItemsGT.CANS != null) tTasks.addTask(3, new EntityAITempt((EntityCreature)aEvent.entity, 0.6D, ItemsGT.CANS, T));
				}
				if (aEvent.entity instanceof EntityZombie) {
					for (int i = 0; i < tTasks.taskEntries.size(); i++) {
						EntityAITasks.EntityAITaskEntry tEntry = (EntityAITasks.EntityAITaskEntry)tTasks.taskEntries.get(i);
						if (tEntry.action.getClass() == EntityAIAttackOnCollide.class) {
							tEntry.action = new EntityAIBetterAttackOnCollide((EntityAIAttackOnCollide)tEntry.action);
						}
					}
				}
			}
			
			// Check if this Entity was already spawned, and not just unloaded and reloaded.
			if (!aEvent.entity.worldObj.isRemote && !aEvent.entity.getEntityData().hasKey("gt.spawned")) {
				if (aEvent.entity instanceof EntityZombie && !((EntityZombie)aEvent.entity).isChild() && ST.invalid(((EntityZombie)aEvent.entity).getEquipmentInSlot(0))) {
					if (ZOMBIES_HOLD_TNT && RNGSUS.nextInt(250) == 0) {
						((EntityZombie)aEvent.entity).setCurrentItemOrArmor(0, ST.make(Blocks.tnt, 1+RNGSUS.nextInt(2), 0));
					} else if (ZOMBIES_HOLD_PICKAXES && RNGSUS.nextInt(100) == 0) {
						((EntityZombie)aEvent.entity).setCurrentItemOrArmor(0, ST.make(Items.iron_pickaxe, 1, Items.iron_pickaxe.getMaxDamage() < 5 ? 0 : 1+RNGSUS.nextInt(Items.iron_pickaxe.getMaxDamage()-2)));
					}
				}
				// Mark Entity as has been spawned
				aEvent.entity.getEntityData().setBoolean("gt.spawned", T);
			}
			return;
		}
		
		if (aEvent.entity.worldObj.isRemote) return;
		
		if (mSkeletonsShootGTArrows > 0 && aEvent.entity.getClass() == EntityArrow.class && RNGSUS.nextInt(mSkeletonsShootGTArrows) == 0) {
			if (((EntityArrow)aEvent.entity).shootingEntity instanceof EntitySkeleton) {
				OreDictMaterial tMaterial = MT.Craponite; // Just default to Anti-Bear989Sr Arrows
				switch(RNGSUS.nextInt(10)) {
				case 0: tMaterial = MT.Steel; break; // Sharpness 2
				case 1: tMaterial = MT.AnnealedCopper; break; // Dissolving 5
				case 2: tMaterial = MT.AstralSilver; break; // Disjunction 5 and Werebane 5
				case 3: tMaterial = MT.BismuthBronze; break; // Bane of Arthropods 4
				case 4: tMaterial = MT.Pt; break; // Smite 5
				case 5: tMaterial = MT.Netherite; break; // Fire Aspect 3
				case 6: tMaterial = MT.Efrine; break; // Fortune/Looting 2
				case 7: tMaterial = MT.Rubber; break; // Knockback 2
				case 8: tMaterial = MT.DamascusSteel; break; // Sharpness 5
				case 9: tMaterial = MT.Craponite; break; // Werebane 10
				}
				ItemStack tArrow = OP.arrowGtWood.mat(tMaterial, 1);
				if (ST.valid(tArrow)) {
					aEvent.entity.worldObj.spawnEntityInWorld(new EntityArrow_Material((EntityArrow)aEvent.entity, tArrow));
					aEvent.entity.setDead();
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onEntityLivingDropsEventEvent(LivingDropsEvent aEvent) {
		if (aEvent.entity.worldObj.isRemote || aEvent.entityLiving == null) return;
		Override_Drops.handleDrops(aEvent.entityLiving, UT.Reflection.getLowercaseClass(aEvent.entityLiving), aEvent.drops, aEvent.source, aEvent.lootingLevel, aEvent.entityLiving.isBurning(), aEvent.recentlyHit);
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onEntityLivingFallEvent(LivingFallEvent aEvent) {
		if (!aEvent.entity.worldObj.isRemote && aEvent.entity instanceof EntityPlayer) {
			if (ST.equal(((EntityPlayer)aEvent.entity).getCurrentEquippedItem(), ToolsGT.sMetaTool, ToolsGT.SCISSORS) || ST.equal(((EntityPlayer)aEvent.entity).getCurrentEquippedItem(), ToolsGT.sMetaTool, ToolsGT.POCKET_SCISSORS)) aEvent.distance *= 2;
		}
	}
	
	@SafeVarargs public final Fluid addAutogeneratedLiquid(OreDictMaterial aMaterial, Set<String>... aFluidList) {return FL.createLiquid(aMaterial, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedLiquid(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return FL.createPlasma(aMaterial, aTexture, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedGas(OreDictMaterial aMaterial, Set<String>... aFluidList) {return FL.createGas(aMaterial, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedGas(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return FL.createGas(aMaterial, aTexture, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedMolten(OreDictMaterial aMaterial, Set<String>... aFluidList) {return FL.createMolten(aMaterial, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedMolten(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return FL.createMolten(aMaterial, aTexture, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedVapor(OreDictMaterial aMaterial, Set<String>... aFluidList) {return FL.createVapour(aMaterial, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedVaporized(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return FL.createVapour(aMaterial, aTexture, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedPlasma(OreDictMaterial aMaterial, Set<String>... aFluidList) {return FL.createPlasma(aMaterial, aFluidList);}
	@SafeVarargs public final Fluid addAutogeneratedPlasma(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return FL.createPlasma(aMaterial, aTexture, aFluidList);}
	@SafeVarargs public final Fluid addFluid(String aName, String aLocalized, OreDictMaterial aMaterial, int aState, long aAmountPerUnit, long aTemperatureK, Set<String>... aFluidList) {return FL.create(aName, aLocalized, aMaterial, aState, aAmountPerUnit, aTemperatureK, aFluidList);}    
	@SafeVarargs public final Fluid addFluid(String aName, String aLocalized, OreDictMaterial aMaterial, int aState, long aAmountPerUnit, long aTemperatureK, ItemStack aFullContainer, ItemStack aEmptyContainer, int aFluidAmount, Set<String>... aFluidList) {return FL.create(aName, aLocalized, aMaterial, aState, aAmountPerUnit, aTemperatureK, aFullContainer, aEmptyContainer, aFluidAmount, aFluidList);}
	@SafeVarargs public final Fluid addFluid(String aName, IIconContainer aTexture, String aLocalized, OreDictMaterial aMaterial, short[] aRGBa, int aState, long aAmountPerUnit, long aTemperatureK, ItemStack aFullContainer, ItemStack aEmptyContainer, int aFluidAmount, Set<String>... aFluidList) {return FL.create(aName, aTexture, aLocalized, aMaterial, aRGBa, aState, aAmountPerUnit, aTemperatureK, aFullContainer, aEmptyContainer, aFluidAmount, aFluidList);}
}
