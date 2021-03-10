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

package gregtech;

import static gregapi.data.CS.*;

import java.net.URL;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import cpw.mods.fml.common.FMLCommonHandler;
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
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.config.ConfigCategories;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.CS.SFX;
import gregapi.data.CS.ToolsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregtech.blocks.fluids.BlockWaterlike;
import gregtech.entities.Override_Drops;
import gregtech.entities.projectiles.EntityArrow_Material;
import gregtech.tileentity.misc.MultiTileEntityCertificate;
import joptsimple.internal.Strings;
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
import net.minecraftforge.common.MinecraftForge;
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
	
	public String mMessage = Strings.EMPTY;
	
	public boolean mDisableVanillaOres = T, mVersionOutdated = F;
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
		new Thread(new Runnable() {@Override public void run() {
		
		List<String>
		tTextFile = downloadTextFile("updates.gregtech.mechaenetia.com/com/gregoriust/gregtech/supporterlist.txt", T);
		if (tTextFile != null && tTextFile.size() > 3) {
			mSupporterListSilver.addAll(tTextFile);
		} else try {
			Scanner tScanner = new Scanner(getClass().getResourceAsStream("/supporterlist.txt"));
			while (tScanner.hasNextLine()) mSupporterListSilver.add(tScanner.nextLine().toLowerCase());
			tScanner.close();
			OUT.println("GT_DL_Thread: Failed downloading Silver Supporter List, using interal List!");
		} catch(Throwable e) {e.printStackTrace(ERR);}
		
		tTextFile = downloadTextFile("updates.gregtech.mechaenetia.com/com/gregoriust/gregtech/supporterlistgold.txt", T);
		if (tTextFile != null && tTextFile.size() > 3) {
			mSupporterListGold.addAll(tTextFile);
		} else try {
			Scanner tScanner = new Scanner(getClass().getResourceAsStream("/supporterlistgold.txt"));
			while (tScanner.hasNextLine()) mSupporterListGold.add(tScanner.nextLine().toLowerCase());
			tScanner.close();
			OUT.println("GT_DL_Thread: Failed downloading Gold Supporter List, using interal List!");
		} catch(Throwable e) {e.printStackTrace(ERR);}
		
		mSupporterListSilver.removeAll(mSupporterListGold);
		
		if (CODE_CLIENT) {
			tTextFile = downloadTextFile("updates.gregtech.mechaenetia.com/com/gregoriust/gregtech/message.txt", F);
			if (tTextFile != null) {
				for (String tLine : tTextFile) mMessage += tLine + " ";
				if (mMessage.length() <= 5) mMessage = Strings.EMPTY;
			}
			
			if (ConfigsGT.CLIENT.get(ConfigCategories.news, "version_checker", T)) try {
				String tVersion = javax.xml.xpath.XPathFactory.newInstance().newXPath().compile("metadata/versioning/release/text()").evaluate(javax.xml.parsers.DocumentBuilderFactory.newInstance().newDocumentBuilder().parse((new URL("https://updates.gregtech.mechaenetia.com/com/gregoriust/gregtech/gregtech_1.7.10/maven-metadata.xml")).openConnection().getInputStream()), javax.xml.xpath.XPathConstants.STRING).toString().substring(0, 7);
				// Check if the first 4 Characters of the Version Number are the same, quick and dirty check that doesn't require Number parsing.
				// And just ignore the first Versions of each Major Release, since that one is usually the buggiest or a quickfix.
				mVersionOutdated = !tVersion.startsWith("6") && !tVersion.endsWith("00") && !tVersion.endsWith("01") && !BuildInfo.version.startsWith(tVersion.substring(0, 4));
				
				OUT.println("GT_DL_Thread: Current Version = '" + BuildInfo.version.substring(0, 7) + "'; Recent Version = '" + tVersion + "'; Majorly Outdated = " + (mVersionOutdated?"Yes":"No"));
			} catch(Throwable e) {
				OUT.println("GT_DL_Thread: Failed Downloading Version Number of the latest Major Version!");
				e.printStackTrace(ERR);
			}
		}
		
		}}).start();
	}
	
	protected List<String> downloadTextFile(String aURL, boolean aLowercase) {
		List<String> rList = new ArrayListNoNulls<>();
		try {
			Scanner tScanner = new Scanner(new URL(aURL.startsWith("http")?aURL:"https://"+aURL).openStream());
			while (tScanner.hasNextLine()) rList.add(aLowercase ? tScanner.nextLine().toLowerCase() : tScanner.nextLine());
			tScanner.close();
			for (String tLine : rList) if (tLine.contains("a href")) {
				OUT.println("GT_DL_Thread: Your Internet Connection has Issues, you should probably go check that your ISP or Network don't do stupid Stuff.");
				return new ArrayListNoNulls<>();
			}
			return rList;
		} catch(Throwable f) {
			OUT.println("GT_DL_Thread: Failed to Connect.");
		}
		return null;
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
		if (mDisableVanillaOres && !WD.dimTF(aEvent.world) && PREVENTED_ORES.contains(aEvent.type)) aEvent.setResult(Result.DENY);
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
								UT.Inventories.addStackToPlayerInventoryOrDrop(aEvent.entityPlayer, ST.make(Items.potionitem, 1, 0), F);
							}
						}
						if (!WD.infiniteWater(aEvent.world, tTarget.blockX, tTarget.blockY, tTarget.blockZ)) aEvent.world.setBlockToAir(tTarget.blockX, tTarget.blockY, tTarget.blockZ);
						if (aEvent.entityPlayer.openContainer != null) aEvent.entityPlayer.openContainer.detectAndSendChanges();
						return;
					}
					if (tBlock == BlocksGT.River || WD.waterstream(tBlock)) {
						ItemStack tStack = FL.fill(FL.Water.make(Integer.MAX_VALUE), aStack, F, T, F, T);
						if (tStack == null) return;
						ST.use(aEvent.entityPlayer, aStack);
						UT.Inventories.addStackToPlayerInventoryOrDrop(aEvent.entityPlayer, tStack, F);
						return;
					}
					if (tBlock == BlocksGT.Ocean) {
						ItemStack tStack = FL.fill(FL.Ocean.make(Integer.MAX_VALUE), aStack, F, T, F, T);
						if (tStack == null) return;
						ST.use(aEvent.entityPlayer, aStack);
						UT.Inventories.addStackToPlayerInventoryOrDrop(aEvent.entityPlayer, tStack, F);
						return;
					}
					if (tBlock == BlocksGT.Swamp) {
						ItemStack tStack = FL.fill(FL.Dirty_Water.make(Integer.MAX_VALUE), aStack, F, T, F, T);
						if (tStack == null) return;
						ST.use(aEvent.entityPlayer, aStack);
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
						if (aStack.getItemDamage() >= aStack.getMaxDamage()) ST.use(aEvent.entityPlayer, aStack);
						return;
					}
					List<String> tChatReturn = new ArrayListNoNulls<>();
					long tDamage = IBlockToolable.Util.onToolClick(TOOL_igniter, aStack.getItemDamage()*10000, 1, aEvent.entityPlayer, tChatReturn, aEvent.entityPlayer.inventory, aEvent.entityPlayer.isSneaking(), aStack, aEvent.world, (byte)aEvent.face, aEvent.x, aEvent.y, aEvent.z, 0.5F, 0.5F, 0.5F);
					UT.Entities.sendchat(aEvent.entityPlayer, tChatReturn, F);
					if (tDamage > 0) {
						aEvent.setCanceled(T);
						UT.Sounds.send(aEvent.world, SFX.MC_IGNITE, 1.0F, 1.0F, aEvent.x, aEvent.y, aEvent.z);
						if (!UT.Entities.hasInfiniteItems(aEvent.entityPlayer)) {
							aStack.damageItem(UT.Code.bindInt(UT.Code.units(tDamage, 10000, 1, T)), aEvent.entityPlayer);
							if (aStack.getItemDamage() >= aStack.getMaxDamage()) ST.use(aEvent.entityPlayer, aStack);
						}
						return;
					}
				} else if (aStack.getItem() == Items.stick || IL.Stick.equal(aStack) || OM.is("stickAnyNormalWood", aStack)) {
					if (!aEvent.world.isRemote && aEvent.entityPlayer.isSneaking() && MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32073).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
						ST.use(aEvent.entityPlayer, aStack);
						aEvent.setCanceled(T);
					}
				} else if (aStack.getItem() == Items.flint) {
					if (!aEvent.world.isRemote && aEvent.entityPlayer.isSneaking() && MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32074, ST.save(NBT_VALUE, ST.amount(1, aStack))).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
						ST.use(aEvent.entityPlayer, aStack);
						aEvent.setCanceled(T);
					}
				} else {
					if (!aEvent.world.isRemote && aEvent.entityPlayer.isSneaking() && ST.block(aStack) == NB) {
						OreDictItemData tData = OM.anyassociation_(aStack);
						if (tData != null) {
							if (tData.mPrefix == OP.rockGt) {
								if (MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32074, ST.save(NBT_VALUE, ST.amount(1, aStack))).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
									ST.use(aEvent.entityPlayer, aStack);
									aEvent.setCanceled(T);
								}
							}
							if (tData.mPrefix == OP.ingot) if (!MD.BOTA.mLoaded || tData.mMaterial.mMaterial.mOriginalMod != MD.BOTA || Blocks.beacon != aEvent.world.getBlock(aEvent.x, aEvent.y, aEvent.z)) {
								if (MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32084, ST.save(NBT_VALUE, ST.copy(aStack))).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
									ST.use(aEvent.entityPlayer, aStack, aStack.stackSize);
									aEvent.setCanceled(T);
								}
							}
							if (tData.mPrefix == OP.plate) {
								if (MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32085, ST.save(NBT_VALUE, ST.copy(aStack))).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
									ST.use(aEvent.entityPlayer, aStack, aStack.stackSize);
									aEvent.setCanceled(T);
								}
							}
							if (tData.mPrefix == OP.plateGem) {
								if (MultiTileEntityRegistry.getRegistry("gt.multitileentity").getItem(32086, ST.save(NBT_VALUE, ST.copy(aStack))).tryPlaceItemIntoWorld(aEvent.entityPlayer, aEvent.world, aEvent.x, aEvent.y, aEvent.z, (byte)aEvent.face, 0.5F, 0.5F, 0.5F)) {
									ST.use(aEvent.entityPlayer, aStack, aStack.stackSize);
									aEvent.setCanceled(T);
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
						UT.Sounds.send(aEvent.world, SFX.MC_IGNITE, 1.0F, 1.0F, aEvent.x, aEvent.y, aEvent.z);
						aEvent.setCanceled(T);
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
					OreDictMaterial tMaterial = MT.Craponite; // Just default to Anti-Bear989Sr Arrows
					switch(RNGSUS.nextInt(10)) {
					case 0: tMaterial = MT.Steel; break; // Sharpness 2
					case 1: tMaterial = MT.AnnealedCopper; break; // Dissolving 5
					case 2: tMaterial = MT.AstralSilver; break; // Disjunction 5 and Werebane 5
					case 3: tMaterial = MT.BismuthBronze; break; // Bane of Arthropods 4
					case 4: tMaterial = MT.Pt; break; // Smite 5
					case 5: tMaterial = MT.Netherite; break; // Fire Aspect 3
					case 6: tMaterial = MT.Thaumium; break; // Fortune/Looting 2
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
			if (ST.equal(((EntityPlayer)aEvent.entity).getCurrentEquippedItem(), ToolsGT.sMetaTool, ToolsGT.SCISSORS) || ST.equal(((EntityPlayer)aEvent.entity).getCurrentEquippedItem(), ToolsGT.sMetaTool, ToolsGT.POCKET_SCISSORS)) aEvent.distance *= 2;
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
