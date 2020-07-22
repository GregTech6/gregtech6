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

package gregapi.util;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import gregapi.GT_API;
import gregapi.block.BlockBase;
import gregapi.block.IBlockDebugable;
import gregapi.block.IBlockExtendedMetaData;
import gregapi.block.IBlockPlacable;
import gregapi.block.IBlockTileEntity;
import gregapi.block.metatype.BlockMetaType;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.code.TagData;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.SFX;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.TD;
import gregapi.event.BlockScanningEvent;
import gregapi.oredict.OreDictMaterial;
import gregapi.random.IHasWorldAndCoords;
import gregapi.tileentity.ITileEntity;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.data.ITileEntityGibbl;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.tileentity.data.ITileEntityTemperature;
import gregapi.tileentity.data.ITileEntityWeight;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.delegate.ITileEntityDelegating;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntityRunningPassively;
import gregapi.tileentity.machines.ITileEntityRunningPossible;
import gregapi.tileentity.machines.ITileEntityRunningSuccessfully;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.util.UT.Code;
import gregtech.blocks.fluids.BlockWaterlike;
import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidHandler;
import thaumcraft.api.nodes.INode;
import twilightforest.TwilightForestMod;

/**
 * @author Gregorius Techneticies
 */
public class WD {
	public static ItemStack suck(IHasWorldAndCoords aCoordinates) {return suck(aCoordinates.getWorld(), aCoordinates.getX(), aCoordinates.getY(), aCoordinates.getZ());}
	public static ItemStack suck(World aWorld, double aX, double aY, double aZ) {return suck(aWorld, aX, aY, aZ, 1, 1, 1);}
	@SuppressWarnings("unchecked")
	public static ItemStack suck(World aWorld, double aX, double aY, double aZ, double aL, double aH, double aW) {
		for (EntityItem tItem : (ArrayList<EntityItem>)aWorld.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+aL, aY+aH, aZ+aW))) {
			if (!tItem.isDead) {
				aWorld.removeEntity(tItem);
				ItemStack rStack = tItem.getEntityItem();
				tItem.setEntityItemStack(ST.amount(0, rStack));
				tItem.setDead();
				return rStack;
			}
		}
		return null;
	}
	
	public static boolean obstructed(World aWorld, int aX, int aY, int aZ, byte aSide) {
		aX += OFFSETS_X[aSide]; aY += OFFSETS_Y[aSide]; aZ += OFFSETS_Z[aSide];
		TileEntity tTileEntity = te(aWorld, aX, aY, aZ, T);
		if (tTileEntity != null) {
			if (tTileEntity instanceof ITileEntityQuickObstructionCheck) return F;
			if (MD.TC.mLoaded && tTileEntity instanceof INode) return F;
		}
		Block tBlock = aWorld.getBlock(aX, aY, aZ);
		if (tBlock instanceof BlockTrapDoor || tBlock instanceof BlockDoor) return F;
		AxisAlignedBB tBoundingBox = tBlock.getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ);
		if (tBoundingBox == null) return F;
		switch(aSide) {
		case 0: return tBoundingBox.maxY-aY > PX_N[4] && tBoundingBox.maxX-aX > PX_P[2] && tBoundingBox.minX-aX < PX_N[2] && tBoundingBox.maxZ-aZ > PX_P[2] && tBoundingBox.minZ-aZ < PX_N[2];
		case 1: return tBoundingBox.minY-aY < PX_P[4] && tBoundingBox.maxX-aX > PX_P[2] && tBoundingBox.minX-aX < PX_N[2] && tBoundingBox.maxZ-aZ > PX_P[2] && tBoundingBox.minZ-aZ < PX_N[2];
		case 2: return tBoundingBox.maxZ-aZ > PX_N[4] && tBoundingBox.maxX-aX > PX_P[2] && tBoundingBox.minX-aX < PX_N[2] && tBoundingBox.maxY-aY > PX_P[2] && tBoundingBox.minY-aY < PX_N[2];
		case 3: return tBoundingBox.minZ-aZ < PX_P[4] && tBoundingBox.maxX-aX > PX_P[2] && tBoundingBox.minX-aX < PX_N[2] && tBoundingBox.maxY-aY > PX_P[2] && tBoundingBox.minY-aY < PX_N[2];
		case 4: return tBoundingBox.maxX-aX > PX_N[4] && tBoundingBox.maxZ-aZ > PX_P[2] && tBoundingBox.minZ-aZ < PX_N[2] && tBoundingBox.maxY-aY > PX_P[2] && tBoundingBox.minY-aY < PX_N[2];
		case 5: return tBoundingBox.minX-aX < PX_P[4] && tBoundingBox.maxZ-aZ > PX_P[2] && tBoundingBox.minZ-aZ < PX_N[2] && tBoundingBox.maxY-aY > PX_P[2] && tBoundingBox.minY-aY < PX_N[2];
		}
		return F;
	}
	
	public static MovingObjectPosition getMOP(World aWorld, EntityPlayer aPlayer, boolean aFlag) {
		Vec3 vec3 = Vec3.createVectorHelper(
		  aPlayer.prevPosX + (aPlayer.posX - aPlayer.prevPosX)
		, aPlayer.prevPosY + (aPlayer.posY - aPlayer.prevPosY) + (aWorld.isRemote ? aPlayer.getEyeHeight() - aPlayer.getDefaultEyeHeight() : aPlayer.getEyeHeight()) // isRemote check to revert changes to ray trace position due to adding the eye height clientside and player yOffset differences
		, aPlayer.prevPosZ + (aPlayer.posZ - aPlayer.prevPosZ)
		);
		float  tPitch = aPlayer.prevRotationPitch + (aPlayer.rotationPitch - aPlayer.prevRotationPitch);
		float  tYaw   = aPlayer.prevRotationYaw   + (aPlayer.rotationYaw   - aPlayer.prevRotationYaw  );
		float  tZ     =  MathHelper.cos(-tYaw   * 0.017453292F - (float)Math.PI);
		float  tX     =  MathHelper.sin(-tYaw   * 0.017453292F - (float)Math.PI);
		float  tW     = -MathHelper.cos(-tPitch * 0.017453292F);
		float  tY     =  MathHelper.sin(-tPitch * 0.017453292F);
		double tReach = (aPlayer instanceof EntityPlayerMP ? ((EntityPlayerMP)aPlayer).theItemInWorldManager.getBlockReachDistance() : 5);
		return aWorld.func_147447_a(vec3, vec3.addVector(tX * tW * tReach, tY * tReach, tZ * tW * tReach), aFlag, !aFlag, F);
	}
	
	public static boolean dimPlanet(World aWorld) {return aWorld != null && dimPlanet(aWorld.provider);}
	public static boolean dimPlanet(WorldProvider aProvider) {return !(Math.abs(aProvider.dimensionId) <= 1 || dimMYST(aProvider) || dimTF(aProvider) || dimERE(aProvider) || dimBTL(aProvider) || dimENVM(aProvider) || dimDD(aProvider) || dimLM(aProvider) || dimAETHER(aProvider) || dimALF(aProvider) || dimTROPIC(aProvider) || dimCANDY(aProvider));}
	
	public static boolean dimMYST(World aWorld) {return aWorld != null && dimMYST(aWorld.provider);}
	public static boolean dimMYST(WorldProvider aProvider) {return MD.MYST.mLoaded && aProvider.getClass().getName().toLowerCase().contains("com.xcompwiz.mystcraft");}
	
	public static boolean dimCANDY(World aWorld) {return aWorld != null && dimCANDY(aWorld.provider);}
	public static boolean dimCANDY(WorldProvider aProvider) {return MD.CANDY.mLoaded && "WorldProviderCandy".equalsIgnoreCase(UT.Reflection.getLowercaseClass(aProvider));}
	
	public static boolean dimTROPIC(World aWorld) {return aWorld != null && dimTROPIC(aWorld.provider);}
	public static boolean dimTROPIC(WorldProvider aProvider) {return MD.TROPIC.mLoaded && "WorldProviderTropicraft".equalsIgnoreCase(UT.Reflection.getLowercaseClass(aProvider));}
	
	public static boolean dimATUM(World aWorld) {return aWorld != null && dimATUM(aWorld.provider);}
	public static boolean dimATUM(WorldProvider aProvider) {return MD.ATUM.mLoaded && "AtumWorldProvider".equalsIgnoreCase(UT.Reflection.getLowercaseClass(aProvider));}
	
	public static boolean dimTF(World aWorld) {return aWorld != null && dimTF(aWorld.provider);}
	public static boolean dimTF(WorldProvider aProvider) {return MD.TF.mLoaded && aProvider.dimensionId == TwilightForestMod.dimensionID;}
	
	public static boolean dimBTL(World aWorld) {return aWorld != null && dimBTL(aWorld.provider);}
	public static boolean dimBTL(WorldProvider aProvider) {return MD.BTL.mLoaded && "WorldProviderBetweenlands".equalsIgnoreCase(UT.Reflection.getLowercaseClass(aProvider));}
	
	public static boolean dimERE(World aWorld) {return aWorld != null && dimERE(aWorld.provider);}
	public static boolean dimERE(WorldProvider aProvider) {return MD.ERE.mLoaded && "WorldProviderErebus".equalsIgnoreCase(UT.Reflection.getLowercaseClass(aProvider));}
	
	public static boolean dimALF(World aWorld) {return aWorld != null && dimALF(aWorld.provider);}
	public static boolean dimALF(WorldProvider aProvider) {return MD.ALF.mLoaded && "WorldProviderAlfheim".equalsIgnoreCase(UT.Reflection.getLowercaseClass(aProvider));}
	
	public static boolean dimDD(World aWorld) {return aWorld != null && dimDD(aWorld.provider);}
	public static boolean dimDD(WorldProvider aProvider) {return (MD.ExU.mLoaded || MD.ExS.mLoaded) && "WorldProviderUnderdark".equalsIgnoreCase(UT.Reflection.getLowercaseClass(aProvider));}
	
	public static boolean dimLM(World aWorld) {return aWorld != null && dimLM(aWorld.provider);}
	public static boolean dimLM(WorldProvider aProvider) {return (MD.ExU.mLoaded || MD.ExS.mLoaded) && "WorldProviderEndOfTime".equalsIgnoreCase(UT.Reflection.getLowercaseClass(aProvider));}
	
	public static boolean dimENVM(World aWorld) {return aWorld != null && dimENVM(aWorld.provider);}
	public static boolean dimENVM(WorldProvider aProvider) {return MD.ENVM.mLoaded && "WorldProviderCaves".equalsIgnoreCase(UT.Reflection.getLowercaseClass(aProvider));}
	
	public static boolean dimAETHER(World aWorld) {return aWorld != null && dimAETHER(aWorld.provider);}
	public static boolean dimAETHER(WorldProvider aProvider) {return MD.AETHER.mLoaded && "WorldProviderAether".equalsIgnoreCase(UT.Reflection.getLowercaseClass(aProvider));}
	
	public static boolean move(Entity aEntity, int aDimension, double aX, double aY, double aZ) {
		WorldServer tTargetWorld = DimensionManager.getWorld(aDimension), tOriginalWorld = DimensionManager.getWorld(aEntity.worldObj.provider.dimensionId);
		if (tTargetWorld != null && tOriginalWorld != null && tTargetWorld != tOriginalWorld) {
			if (aEntity.ridingEntity != null) aEntity.mountEntity(null);
			if (aEntity.riddenByEntity != null) aEntity.riddenByEntity.mountEntity(null);
			
			if (aEntity instanceof EntityPlayerMP) {
				EntityPlayerMP aPlayer = (EntityPlayerMP)aEntity;
				aPlayer.dimension = aDimension;
				aPlayer.playerNetServerHandler.sendPacket(new S07PacketRespawn(aPlayer.dimension, aPlayer.worldObj.difficultySetting, aPlayer.worldObj.getWorldInfo().getTerrainType(), aPlayer.theItemInWorldManager.getGameType()));
				tOriginalWorld.removePlayerEntityDangerously(aPlayer);
				aPlayer.isDead = F;
				aPlayer.setWorld(tTargetWorld);
				MinecraftServer.getServer().getConfigurationManager().func_72375_a(aPlayer, tOriginalWorld);
				aPlayer.playerNetServerHandler.setPlayerLocation(aX+0.5, aY+0.5, aZ+0.5, aPlayer.rotationYaw, aPlayer.rotationPitch);
				aPlayer.theItemInWorldManager.setWorld(tTargetWorld);
				MinecraftServer.getServer().getConfigurationManager().updateTimeAndWeatherForPlayer(aPlayer, tTargetWorld);
				MinecraftServer.getServer().getConfigurationManager().syncPlayerInventory(aPlayer);
				@SuppressWarnings("rawtypes")
				Iterator tIterator = aPlayer.getActivePotionEffects().iterator();
				while (tIterator.hasNext()) {
					PotionEffect potioneffect = (PotionEffect)tIterator.next();
					aPlayer.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(aPlayer.getEntityId(), potioneffect));
				}
				aPlayer.playerNetServerHandler.setPlayerLocation(aX+0.5, aY+0.5, aZ+0.5, aPlayer.rotationYaw, aPlayer.rotationPitch);
				FMLCommonHandler.instance().firePlayerChangedDimensionEvent(aPlayer, tOriginalWorld.provider.dimensionId, aDimension);
			} else {
				aEntity.setPosition(aX+0.5, aY+0.5, aZ+0.5);
				aEntity.worldObj.removeEntity(aEntity);
				aEntity.dimension = aDimension;
				aEntity.isDead = F;
				Entity tNewEntity = EntityList.createEntityByName(EntityList.getEntityString(aEntity), tTargetWorld);
				if (tNewEntity != null) {
					tNewEntity.copyDataFrom(aEntity, T);
					aEntity.setDead();
					tNewEntity.isDead = F;
					boolean temp = tNewEntity.forceSpawn;
					tNewEntity.forceSpawn = T;
					tTargetWorld.spawnEntityInWorld(tNewEntity);
					tNewEntity.forceSpawn = temp;
					tNewEntity.isDead = F;
					aEntity = tNewEntity;
				}
			}
			
			if (aEntity instanceof EntityLivingBase) {
				((EntityLivingBase)aEntity).setPositionAndUpdate(aX, aY, aZ);
			} else {
				aEntity.setPosition(aX, aY, aZ);
			}
			
			tOriginalWorld.resetUpdateEntityTick();
			tTargetWorld.resetUpdateEntityTick();
			return T;
		}
		return F;
	}
	
	/** to get a TileEntity properly, according to my additional Interfaces. Normally you should set aLoadUnloadedChunks to false, unless you have already checked these Coordinates, or you want to load Chunks */
	public static DelegatorTileEntity<TileEntity> te(World aWorld, ChunkCoordinates aCoords, byte aSide, boolean aLoadUnloadedChunks) {
		TileEntity aTileEntity = te(aWorld, aCoords, aLoadUnloadedChunks);
		return aTileEntity instanceof ITileEntityDelegating ? ((ITileEntityDelegating)aTileEntity).getDelegateTileEntity(aSide) : new DelegatorTileEntity<>(aTileEntity, aWorld, aCoords, aSide);
	}
	
	/** to get a TileEntity properly, according to my additional Interfaces. Normally you should set aLoadUnloadedChunks to false, unless you have already checked these Coordinates, or you want to load Chunks */
	public static DelegatorTileEntity<TileEntity> te(World aWorld, int aX, int aY, int aZ, byte aSide, boolean aLoadUnloadedChunks) {
		TileEntity aTileEntity = te(aWorld, aX, aY, aZ, aLoadUnloadedChunks);
		return aTileEntity instanceof ITileEntityDelegating ? ((ITileEntityDelegating)aTileEntity).getDelegateTileEntity(aSide) : new DelegatorTileEntity<>(aTileEntity, aWorld, aX, aY, aZ, aSide);
	}
	
	/** to get a TileEntity properly, according to my additional Interfaces. Normally you should set aLoadUnloadedChunks to false, unless you have already checked these Coordinates, or you want to load Chunks */
	public static TileEntity te(World aWorld, ChunkCoordinates aCoords, boolean aLoadUnloadedChunks) {
		if (aLoadUnloadedChunks || aWorld.blockExists(aCoords.posX, aCoords.posY, aCoords.posZ)) {
			TileEntity rTileEntity = aWorld.getTileEntity(aCoords.posX, aCoords.posY, aCoords.posZ);
			if (rTileEntity != null) return rTileEntity;
			rTileEntity = LAST_BROKEN_TILEENTITY.get();
			if (rTileEntity != null && rTileEntity.xCoord == aCoords.posX && rTileEntity.yCoord == aCoords.posY && rTileEntity.zCoord == aCoords.posZ) return rTileEntity;
			Block tBlock = aWorld.getBlock(aCoords.posX, aCoords.posY, aCoords.posZ);
			return tBlock instanceof IBlockTileEntity ? ((IBlockTileEntity)tBlock).getTileEntity(aWorld, aCoords.posX, aCoords.posY, aCoords.posZ) : null;
		}
		return null;
	}
	
	/** to get a TileEntity properly, according to my additional Interfaces. Normally you should set aLoadUnloadedChunks to false, unless you have already checked these Coordinates, or you want to load Chunks */
	public static TileEntity te(World aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks) {
		if (aLoadUnloadedChunks || aWorld.blockExists(aX, aY, aZ)) {
			TileEntity rTileEntity = aWorld.getTileEntity(aX, aY, aZ);
			if (rTileEntity != null) return rTileEntity;
			rTileEntity = LAST_BROKEN_TILEENTITY.get();
			if (rTileEntity != null && rTileEntity.xCoord == aX && rTileEntity.yCoord == aY && rTileEntity.zCoord == aZ) return rTileEntity;
			Block tBlock = aWorld.getBlock(aX, aY, aZ);
			return tBlock instanceof IBlockTileEntity ? ((IBlockTileEntity)tBlock).getTileEntity(aWorld, aX, aY, aZ) : null;
		}
		return null;
	}
	
	/** Sets the TileEntity at the passed position, with the option of turning adjacent TileEntity updates off. */
	public static TileEntity te(World aWorld, int aX, int aY, int aZ, TileEntity aTileEntity, boolean aCauseTileEntityUpdates) {
		if (aCauseTileEntityUpdates) aWorld.setTileEntity(aX, aY, aZ, aTileEntity); else {
			Chunk tChunk = aWorld.getChunkFromChunkCoords(aX >> 4, aZ >> 4);
			if (tChunk != null) {
				aWorld.addTileEntity(aTileEntity);
				tChunk.func_150812_a(aX & 15, aY, aZ & 15, aTileEntity);
				tChunk.setChunkModified();
			}
		}
		return aTileEntity;
	}
	
	public static boolean oxygen(World aWorld, int aX, int aY, int aZ) {
		return  !MD.GC.mLoaded || !(aWorld.provider instanceof IGalacticraftWorldProvider) || OxygenUtil.checkTorchHasOxygen(aWorld, NB, aX, aY, aZ);
	}
	public static boolean collectable_air(World aWorld, int aX, int aY, int aZ) {
		return (!MD.GC.mLoaded || !(aWorld.provider instanceof IGalacticraftWorldProvider)) && !hasCollide(aWorld, aX, aY, aZ);
	}
	
	/** @return the regular Environment Temperature of the World at this Location according to my calculations. In Kelvin, ofcourse. */
	public static long envTemp(World aWorld, int aX, int aY, int aZ) {
		return envTemp(aWorld.getBiomeGenForCoords(aX, aZ), aX, aY, aZ);
	}
	/** @return the regular Environment Temperature of the World at this Location according to my calculations. In Kelvin, ofcourse. */
	public static long envTemp(BiomeGenBase aBiome, int aX, int aY, int aZ) {
		return Math.max(1, aBiome == null ? DEF_ENV_TEMP : (long)(C - 3 + aBiome.getFloatTemperature(aX, aY, aZ) * 20));
	}
	/** @return the regular Environment Temperature of the World at this Location according to my calculations. In Kelvin, ofcourse. */
	public static long envTemp(BiomeGenBase aBiome) {
		return Math.max(1, aBiome == null ? DEF_ENV_TEMP : (long)(C - 3 + aBiome.temperature * 20));
	}
	
	/** @return the Height of the Water Level that should probably be in this World. */
	public static int waterLevel(World aWorld) {
		return waterLevel(aWorld.provider, 62);
	}
	/** @return the Height of the Water Level that should probably be in this World. */
	public static int waterLevel(WorldProvider aProvider) {
		return waterLevel(aProvider, 62);
	}
	/** @return the Height of the Water Level that should probably be in this World. */
	public static int waterLevel(World aWorld, int aDefaultOverworld) {
		return waterLevel(aWorld.provider, aDefaultOverworld);
	}
	/** @return the Height of the Water Level that should probably be in this World. */
	public static int waterLevel(WorldProvider aProvider, int aDefaultOverworld) {
		return aProvider.dimensionId == DIM_OVERWORLD ? waterLevel(aDefaultOverworld) : dimTF(aProvider) ? 31 : 62;
	}
	/** @return the Height of the Water Level that should probably be in this World. */
	public static int waterLevel(int aDefaultOverworld) {
		return MD.TFC.mLoaded || MD.TFCP.mLoaded? 143 : aDefaultOverworld;
	}
	/** @return the Height of the Water Level that should probably be in this World. */
	public static int waterLevel() {
		return waterLevel(62);
	}
	
	/** @return the regular Temperature of the World at this Location according to Gregs calculations. In Kelvin, ofcourse. */
	public static long temperature(World aWorld, int aX, int aY, int aZ) {
		long rTemperature = envTemp(aWorld, aX, aY, aZ);
		if (burning(aWorld, aX, aY, aZ)) rTemperature = Math.max(rTemperature, C + 200);
		for (ChunkCoordinates tCoords : new ChunkCoordinates[] {new ChunkCoordinates(aX, aY, aZ), new ChunkCoordinates(aX+1, aY, aZ), new ChunkCoordinates(aX-1, aY, aZ), new ChunkCoordinates(aX, aY+1, aZ), new ChunkCoordinates(aX, aY-1, aZ), new ChunkCoordinates(aX, aY, aZ+1), new ChunkCoordinates(aX, aY, aZ-1)}) {
			Block tBlock = block(aWorld, tCoords.posX, tCoords.posY, tCoords.posZ, F);
			if (tBlock == Blocks.lava || tBlock == Blocks.flowing_lava) rTemperature = Math.max(rTemperature, C + 500);
			else if (tBlock instanceof BlockFire) rTemperature = Math.max(rTemperature, C + 200);
		}
		return rTemperature;
	}
	
	public static ItemStack stack(World aWorld, int aX, int aY, int aZ) {
		Block tBlock = aWorld.getBlock(aX, aY, aZ);
		return ST.make(tBlock, 1, tBlock instanceof IBlockExtendedMetaData ? ((IBlockExtendedMetaData)tBlock).getExtendedMetaData(aWorld, aX, aY, aZ) : aWorld.getBlockMetadata(aX, aY, aZ));
	}
	
	public static void update(IBlockAccess aWorld, int aX, int aY, int aZ) {
		((World)aWorld).markBlockForUpdate(aX, aY, aZ);
		if (CLIENT_BLOCKUPDATE_SOUNDS && CODE_CLIENT && CLIENT_TIME > 100) {
			EntityPlayer tPlayer = GT_API.api_proxy.getThePlayer();
			if (tPlayer != null && Math.abs(tPlayer.posX - aX) < 16 && Math.abs(tPlayer.posY - aY) < 16 && Math.abs(tPlayer.posZ - aZ) < 16) {
				UT.Sounds.play(SFX.MC_FIREWORK_LAUNCH, 1, 1.0F, 1.0F, aX, aY, aZ);
			}
		}
	}
	
	public static Block block(World aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks) {return aLoadUnloadedChunks || aWorld.blockExists(aX, aY, aZ) ? aWorld.getBlock(aX, aY, aZ) : NB;}
	public static Block block(World aWorld, int aX, int aY, int aZ) {return aWorld.getBlock(aX, aY, aZ);}
	public static Block block(World aWorld, int aX, int aY, int aZ, byte aSide, boolean aLoadUnloadedChunks) {return block(aWorld, aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide], aLoadUnloadedChunks);}
	public static Block block(World aWorld, int aX, int aY, int aZ, byte aSide) {return block(aWorld, aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide]);}
	public static byte  meta (World aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks) {return aLoadUnloadedChunks || aWorld.blockExists(aX, aY, aZ) ? UT.Code.bind4(aWorld.getBlockMetadata(aX, aY, aZ)) : 0;}
	public static byte  meta (World aWorld, int aX, int aY, int aZ) {return UT.Code.bind4(aWorld.getBlockMetadata(aX, aY, aZ));}
	public static byte  meta (World aWorld, int aX, int aY, int aZ, byte aSide, boolean aLoadUnloadedChunks) {return meta(aWorld, aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide], aLoadUnloadedChunks);}
	public static byte  meta (World aWorld, int aX, int aY, int aZ, byte aSide) {return meta(aWorld, aX+OFFSETS_X[aSide], aY+OFFSETS_Y[aSide], aZ+OFFSETS_Z[aSide]);}
	
	public static boolean set(World aWorld, int aX, int aY, int aZ, Block aBlock, long aMeta, long aFlags) {
		return set(aWorld, aX, aY, aZ, aBlock, Code.bind4(aMeta), (byte)aFlags, aBlock.isOpaqueCube());
	}
	
	public static boolean set(World aWorld, int aX, int aY, int aZ, Block aBlock, long aMeta, long aFlags, boolean aRemoveGrassBelow) {
		if (aRemoveGrassBelow) {
			Block tBlock = aWorld.getBlock(aX, aY-1, aZ);
			if (tBlock == Blocks.grass || tBlock == Blocks.mycelium) aWorld.setBlock(aX, aY-1, aZ, Blocks.dirt, 0, (byte)aFlags);
		}
		return aWorld.setBlock(aX, aY, aZ, aBlock, Code.bind4(aMeta), (byte)aFlags);
	}
	
	public static boolean set(Chunk aChunk, int aX, int aY, int aZ, Block aBlock, long aMeta) {
		return aChunk.func_150807_a(aX, aY, aZ, aBlock, Code.bind4(aMeta));
	}
	public static boolean set(Chunk aChunk, int aX, int aY, int aZ, Block aBlock, long aMeta, boolean aRemoveGrassBelow) {
		if (aRemoveGrassBelow) {
			Block tBlock = aChunk.getBlock(aX, aY-1, aZ);
			if (tBlock == Blocks.grass || tBlock == Blocks.mycelium) aChunk.func_150807_a(aX, aY-1, aZ, Blocks.dirt, 0);
		}
		return aChunk.func_150807_a(aX, aY, aZ, aBlock, Code.bind4(aMeta));
	}
	
	public static boolean sign(World aWorld, int aX, int aY, int aZ, byte aSide, long aFlags, String aLine1, String aLine2, String aLine3, String aLine4) {
		aWorld.setBlock(aX, aY, aZ, Blocks.wall_sign, aSide, (byte)aFlags);
		TileEntity tSign = te(aWorld, aX, aY, aZ, T);
		if (!(tSign instanceof TileEntitySign)) return F;
		((TileEntitySign)tSign).signText[0] = aLine1;
		((TileEntitySign)tSign).signText[1] = aLine2;
		((TileEntitySign)tSign).signText[2] = aLine3;
		((TileEntitySign)tSign).signText[3] = aLine4;
		return T;
	}
	
	public static Random random(World aWorld, long aChunkX, long aChunkZ) {return random(aChunkX, aChunkZ, aWorld.getSeed());}
	public static Random random(long aSeed, long aChunkX, long aChunkZ) {
		Random rRandom = new Random(aSeed);
		for (int i = 0; i < 50; i++) rRandom.nextInt(0x00ffffff);
		rRandom = new Random(aSeed ^ ((rRandom.nextLong() >> 2 + 1L) * aChunkX + (rRandom.nextLong() >> 2 + 1L) * aChunkZ));
		for (int i = 0; i < 50; i++) rRandom.nextInt(0x00ffffff);
		return rRandom;
	}
	
	public static int random(World aWorld, int aX, int aY, int aZ, int aBound) {return random(aWorld.getSeed(), aX, aY, aZ, aBound);}
	public static int random(long aSeed, int aX, int aY, int aZ, int aBound) {
		Random rRandom = new Random(aSeed);
		for (int i = 0; i < 10; i++) rRandom.nextInt(0x00ffffff);
		rRandom = new Random(aSeed ^ ((rRandom.nextLong() >> 2 + 1L) * aX + (rRandom.nextLong() >> 2 + 1L) * aZ));
		for (int i = 0; i < 10; i++) rRandom.nextInt(0x00ffffff);
		return rRandom.nextInt(aBound);
	}
	
	public static boolean border(int aFromX, int aFromZ, int aToX, int aToZ) {return aFromX >> 4 != aToX >> 4 || aFromZ >> 4 != aToZ >> 4;}
	
	public static boolean even(TileEntity aTileEntity) {return even(aTileEntity.xCoord, aTileEntity.yCoord, aTileEntity.zCoord);}
	public static boolean even(ChunkCoordinates aCoords) {return even(aCoords.posX, aCoords.posY, aCoords.posZ);}
	public static boolean even(int... aCoords) {int i = 0; for (int tCoord : aCoords) if (tCoord % 2 == 0) i++; return i % 2 == 0;}
	
	public static boolean setIfDiff(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMetaData, int aFlags) {return (aWorld.getBlock(aX, aY, aZ) != aBlock || aWorld.getBlockMetadata(aX, aY, aZ) != aMetaData) && aWorld.setBlock(aX, aY, aZ, aBlock, aMetaData, aFlags);}
	
	public static boolean set(World aWorld, int aX, int aY, int aZ, ItemStack aStack) {
		Block tBlock = ST.block(aStack);
		if (tBlock == NB) return F;
		if (tBlock instanceof IBlockPlacable) return ((IBlockPlacable)tBlock).placeBlock(aWorld, aX, aY, aZ, (byte)6, ST.meta_(aStack), aStack.getTagCompound(), T, F);
		if (ST.meta_(aStack) < 16) return aWorld.setBlock(aX, aY, aZ, tBlock, ST.meta_(aStack), 3);
		return F;
	}
	
	public static boolean leafdecay(World aWorld, int aX, int aY, int aZ, Block aBlock) {
		if (aBlock == null || (!(aBlock instanceof BlockBase) && aBlock.canSustainLeaves(aWorld, aX, aY, aZ))) {
			for (int i = -7; i <= 7; ++i) for (int j = -7; j <= 7; ++j) for (int k = -7; k <= 7; ++k) {
				Block tBlock = aWorld.getBlock(aX+i, aY+j, aZ+k);
				if (!(tBlock instanceof BlockBase) && tBlock.isLeaves(aWorld, aX+i, aY+j, aZ+k)) {
					aWorld.scheduleBlockUpdate(aX+i, aY+j, aZ+k, tBlock, 1+RNGSUS.nextInt(100));
				}
			}
			return T;
		}
		return F;
	}
	
	public static boolean liquid(World aWorld, int aX, int aY, int aZ) {return liquid(aWorld.getBlock(aX, aY, aZ));}
	public static boolean liquid(Block aBlock) {return aBlock instanceof BlockLiquid || aBlock instanceof IFluidBlock;}
	
	public static boolean stone(Block aBlock, short aMetaData) {
		if (aBlock == Blocks.obsidian) return T;
		ItemStackContainer tStack = new ItemStackContainer(aBlock, 1, aMetaData);
		return BlocksGT.stoneToNormalOres.containsKey(tStack) || BlocksGT.stoneToBrokenOres.containsKey(tStack) || BlocksGT.stoneToSmallOres.containsKey(tStack);
	}
	
	public static boolean floor(World aWorld, int aX, int aY, int aZ) {return floor(aWorld, aX, aY, aZ, aWorld.getBlock(aX, aY, aZ));}
	public static boolean floor(World aWorld, int aX, int aY, int aZ, Block aBlock) {return aBlock.isSideSolid(aWorld, aX, aY, aZ, FORGE_DIR[SIDE_UP]) || floor(aBlock);}
	public static boolean floor(Block aBlock) {return aBlock.isOpaqueCube() || aBlock instanceof BlockSlab || aBlock instanceof BlockStairs || aBlock instanceof BlockMetaType;}
	
	@SuppressWarnings("unlikely-arg-type")
	public static boolean ore(Block aBlock, short aMetaData) {return (aBlock instanceof IBlockPlacable && (BlocksGT.stoneToBrokenOres.containsValue(aBlock) || BlocksGT.stoneToNormalOres.containsValue(aBlock) || BlocksGT.stoneToSmallOres.containsValue(aBlock)) || OM.prefixcontains(ST.make(aBlock, 1, aMetaData), TD.Prefix.ORE));}
	public static boolean ore_stone(Block aBlock, short aMetaData) {return ore(aBlock, aMetaData) || stone(aBlock, aMetaData);}
	
	public static boolean visOcc(World aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks, boolean aDefault) {return visOpq(aWorld, aX+1, aY, aZ, aLoadUnloadedChunks || !border(aX, aZ, aX+1, aZ), aDefault) && visOpq(aWorld, aX-1, aY, aZ, aLoadUnloadedChunks || !border(aX, aZ, aX-1, aZ), aDefault) && visOpq(aWorld, aX, aY+1, aZ, T, aDefault) && visOpq(aWorld, aX, aY-1, aZ, T, aDefault) && visOpq(aWorld, aX, aY, aZ+1, aLoadUnloadedChunks || !border(aX, aZ, aX, aZ+1), aDefault) && visOpq(aWorld, aX, aY, aZ-1, aLoadUnloadedChunks || !border(aX, aZ, aX, aZ-1), aDefault);}
	public static boolean visOpq(World aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks, boolean aDefault) {return aLoadUnloadedChunks || aWorld.blockExists(aX, aY, aZ) ? visOpq(aWorld.getBlock(aX, aY, aZ)) : aDefault;}
	public static boolean visOpq(Block aBlock) {return aBlock.isOpaqueCube() || VISUALLY_OPAQUE_BLOCKS.contains(aBlock);}
	
	public static boolean occ(World aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks, boolean aDefault) {return opq(aWorld, aX+1, aY, aZ, aLoadUnloadedChunks || !border(aX, aZ, aX+1, aZ), aDefault) && opq(aWorld, aX-1, aY, aZ, aLoadUnloadedChunks || !border(aX, aZ, aX-1, aZ), aDefault) && opq(aWorld, aX, aY+1, aZ, T, aDefault) && opq(aWorld, aX, aY-1, aZ, T, aDefault) && opq(aWorld, aX, aY, aZ+1, aLoadUnloadedChunks || !border(aX, aZ, aX, aZ+1), aDefault) && opq(aWorld, aX, aY, aZ-1, aLoadUnloadedChunks || !border(aX, aZ, aX, aZ-1), aDefault);}
	public static boolean opq(World aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks, boolean aDefault) {return aLoadUnloadedChunks || aWorld.blockExists(aX, aY, aZ) ? opq(aWorld.getBlock(aX, aY, aZ)) : aDefault;}
	public static boolean opq(Block aBlock) {return aBlock.isOpaqueCube() && !(aBlock instanceof BlockLeaves);}
	
	public static boolean air(World aWorld, int aX, int aY, int aZ) {return air(aWorld, aX, aY, aZ, aWorld.getBlock(aX, aY, aZ));}
	public static boolean air(World aWorld, int aX, int aY, int aZ, Block aBlock) {return aBlock == NB || (aBlock.isAir(aWorld, aX, aY, aZ) && !(MD.TC.mLoaded && !aBlock.isOpaqueCube() && te(aWorld, aX, aY, aZ, T) instanceof INode));}
	public static boolean air(Block aBlock) {return aBlock == NB;}
	
	public static boolean lava(IBlockAccess aWorld, int aX, int aY, int aZ) {return lava(aWorld, aX, aY, aZ, aWorld.getBlock(aX, aY, aZ));}
	public static boolean lava(IBlockAccess aWorld, int aX, int aY, int aZ, Block aBlock) {return aBlock == Blocks.lava || aBlock == Blocks.flowing_lava;}
	public static boolean lava(Block aBlock) {return aBlock == Blocks.lava || aBlock == Blocks.flowing_lava;}
	
	public static boolean water(IBlockAccess aWorld, int aX, int aY, int aZ) {return water(aWorld, aX, aY, aZ, aWorld.getBlock(aX, aY, aZ));}
	public static boolean water(IBlockAccess aWorld, int aX, int aY, int aZ, Block aBlock) {return aBlock == Blocks.water || aBlock == Blocks.flowing_water;}
	public static boolean water(Block aBlock) {return aBlock == Blocks.water || aBlock == Blocks.flowing_water;}
	
	public static boolean waterstream(Block aBlock) {return MD.Streams.mLoaded && UT.Code.stringValidate(ST.regName(aBlock)).startsWith("streams:river/tile.water");}
	
	public static boolean anywater(IBlockAccess aWorld, int aX, int aY, int aZ) {return anywater(aWorld, aX, aY, aZ, aWorld.getBlock(aX, aY, aZ));}
	public static boolean anywater(IBlockAccess aWorld, int aX, int aY, int aZ, Block aBlock) {return aBlock instanceof BlockWaterlike || water(aWorld, aX, aY, aZ, aBlock) || waterstream(aBlock);}
	public static boolean anywater(Block aBlock) {return aBlock instanceof BlockWaterlike || water(aBlock) || waterstream(aBlock);}
	
	public static boolean bedrock(World aWorld, int aX, int aY, int aZ) {return bedrock(aWorld, aX, aY, aZ, aWorld.getBlock(aX, aY, aZ));}
	public static boolean bedrock(World aWorld, int aX, int aY, int aZ, Block aBlock) {return aBlock == Blocks.bedrock || (IL.BTL_Bedrock.exists() && IL.BTL_Bedrock.equal(aBlock));}
	public static boolean bedrock(Block aBlock) {return aBlock == Blocks.bedrock || (IL.BTL_Bedrock.exists() && IL.BTL_Bedrock.equal(aBlock));}
	
	public static boolean grass(World aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks) {return grass(block(aWorld, aX, aY, aZ, aLoadUnloadedChunks), meta(aWorld, aX, aY, aZ, aLoadUnloadedChunks));}
	public static boolean grass(World aWorld, int aX, int aY, int aZ) {return grass(block(aWorld, aX, aY, aZ), meta(aWorld, aX, aY, aZ));}
	public static boolean grass(World aWorld, int aX, int aY, int aZ, Block aBlock, long aMeta) {return grass(aBlock, aMeta);}
	public static boolean grass(Block aBlock, long aMeta) {
		if (aBlock == Blocks.tallgrass) return T;
		if (aBlock == Blocks.double_plant)  return aMeta ==  2 || aMeta ==  3;
		if (IL.TF_Tall_Grass.equal(aBlock)) return aMeta ==  8 || aMeta == 10;
		return IL.AETHER_Tall_Grass.equal(aBlock);
	}
	
	public static boolean easyRep(World aWorld, int aX, int aY, int aZ) {return easyRep(aWorld, aX, aY, aZ, aWorld.getBlock(aX, aY, aZ));}
	public static boolean easyRep(World aWorld, int aX, int aY, int aZ, Block aBlock) {return air(aWorld, aX, aY, aZ, aBlock) || aBlock instanceof BlockBush || aBlock.isLeaves(aWorld, aX, aY, aZ) || aBlock.canBeReplacedByLeaves(aWorld, aX, aY, aZ);}
	
	public static boolean infiniteWater(World aWorld, int aX, int aY, int aZ) {return (MD.TF.mLoaded && aWorld.provider.dimensionId == TwilightForestMod.dimensionID ? UT.Code.inside(21, 31, aY) : UT.Code.inside(52, 62, aY)) && BIOMES_RIVER_LAKE.contains(aWorld.getBiomeGenForCoords(aX, aZ).biomeName);}
	public static boolean infiniteWater(World aWorld, int aX, int aY, int aZ, Block aBlock) {return waterstream(aBlock) || ((aBlock == Blocks.water || aBlock == Blocks.flowing_water) && (MD.TF.mLoaded && aWorld.provider.dimensionId == TwilightForestMod.dimensionID ? UT.Code.inside(21, 31, aY) : UT.Code.inside(52, 62, aY)) && BIOMES_RIVER_LAKE.contains(aWorld.getBiomeGenForCoords(aX, aZ).biomeName));}
	
	public static boolean hasCollide(World aWorld, int aX, int aY, int aZ) {return hasCollide(aWorld, aX, aY, aZ, aWorld.getBlock(aX, aY, aZ));}
	public static boolean hasCollide(World aWorld, int aX, int aY, int aZ, Block aBlock) {return aBlock.isOpaqueCube() || aBlock.getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ) != null;}
	
	public static boolean hasCollide(World aWorld, ChunkCoordinates aCoords) {return hasCollide(aWorld, aCoords, aWorld.getBlock(aCoords.posX, aCoords.posY, aCoords.posZ));}
	public static boolean hasCollide(World aWorld, ChunkCoordinates aCoords, Block aBlock) {return aBlock.isOpaqueCube() || aBlock.getCollisionBoundingBoxFromPool(aWorld, aCoords.posX, aCoords.posY, aCoords.posZ) != null;}
	
	public static boolean burning(World aWorld, int aX, int aY, int aZ) {return block(aWorld, aX, aY, aZ, F) instanceof BlockFire || block(aWorld, aX+1, aY, aZ, F) instanceof BlockFire || block(aWorld, aX-1, aY, aZ, F) instanceof BlockFire || block(aWorld, aX, aY+1, aZ, F) instanceof BlockFire || block(aWorld, aX, aY-1, aZ, F) instanceof BlockFire || block(aWorld, aX, aY, aZ+1, F) instanceof BlockFire || block(aWorld, aX, aY, aZ-1, F) instanceof BlockFire;}
	
	public static void burn(World aWorld, ChunkCoordinates aCoords, boolean aReplaceCenter, boolean aCheckFlammability) {for (byte tSide : aReplaceCenter?ALL_SIDES:ALL_SIDES_VALID) fire(aWorld, aCoords.posX+OFFSETS_X[tSide], aCoords.posY+OFFSETS_Y[tSide], aCoords.posZ+OFFSETS_Z[tSide], aCheckFlammability);}
	public static void burn(World aWorld, int aX, int aY, int aZ  , boolean aReplaceCenter, boolean aCheckFlammability) {for (byte tSide : aReplaceCenter?ALL_SIDES:ALL_SIDES_VALID) fire(aWorld, aX+OFFSETS_X[tSide], aY+OFFSETS_Y[tSide], aZ+OFFSETS_Z[tSide], aCheckFlammability);}
	
	public static boolean fire(World aWorld, ChunkCoordinates aCoords, boolean aCheckFlammability) {return fire(aWorld, aCoords.posX, aCoords.posY, aCoords.posZ, aCheckFlammability);}
	public static boolean fire(World aWorld, int aX, int aY, int aZ, boolean aCheckFlammability) {
		Block tBlock = aWorld.getBlock(aX, aY, aZ);
		if (tBlock.getMaterial() == Material.lava || tBlock.getMaterial() == Material.fire) return F;
		if (tBlock.getMaterial() == Material.carpet || tBlock.getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ) == null) {
			if (MD.TC.mLoaded && te(aWorld, aX, aY, aZ, T) instanceof INode) return F;
			if (tBlock.getFlammability(aWorld, aX, aY, aZ, FORGE_DIR[SIDE_ANY]) > 0) return aWorld.setBlock(aX, aY, aZ, Blocks.fire, 0, 3);
			if (aCheckFlammability) {
				for (byte tSide : ALL_SIDES_VALID) if (block(aWorld, aX, aY, aZ, tSide).getFlammability(aWorld, aX+OFFSETS_X[tSide], aY+OFFSETS_Y[tSide], aZ+OFFSETS_Z[tSide], FORGE_DIR_OPPOSITES[tSide]) > 0) return aWorld.setBlock(aX, aY, aZ, Blocks.fire);
			} else {
				return aWorld.setBlock(aX, aY, aZ, Blocks.fire, 0, 3);
			}
		}
		return F;
	}
	
	public static boolean oreGenReplaceable(World aWorld, int aX, int aY, int aZ, boolean aAllowAir) {
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == NB) return aAllowAir;
		byte aMetaData = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
		if (BlocksGT.sDontGenerateOresIn.contains(new ItemStackContainer(aBlock, 1, aMetaData))) return F;
		if (BlocksGT.stoneToNormalOres.containsKey(new ItemStackContainer(aBlock, 1, aMetaData))) return T;
		if (Blocks.stone        != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.stone        )) return T;
		if (Blocks.gravel       != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.gravel       )) return T;
		if (Blocks.sand         != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.sand         )) return T;
		if (Blocks.netherrack   != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.netherrack   )) return T;
		if (Blocks.end_stone    != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.end_stone    )) return T;
		return F;
	}
	
	public static boolean setOre(World aWorld, int aX, int aY, int aZ, OreDictMaterial aMaterial) {
		return aMaterial != null && setOre(aWorld, aX, aY, aZ, aMaterial.mID);
	}
	
	public static boolean setOre(World aWorld, int aX, int aY, int aZ, short aID) {
		if (aID <= 0 && aID == W) return F;
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == NB) return F;
		byte aMetaData = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
		if (BlocksGT.sDontGenerateOresIn.contains(new ItemStackContainer(aBlock, 1, aMetaData))) return F;
		IBlockPlacable tBlock = BlocksGT.stoneToNormalOres.get(new ItemStackContainer(aBlock, 1, aMetaData));
		if (tBlock == null) {
		if (Blocks.stone        != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.stone        )) tBlock = BlocksGT.ore; else
		if (Blocks.gravel       != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.gravel       )) tBlock = BlocksGT.oreGravel; else
		if (Blocks.sand         != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.sand         )) tBlock = BlocksGT.oreSand; else
		if (Blocks.netherrack   != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.netherrack   )) tBlock = BlocksGT.oreNetherrack; else
		if (Blocks.end_stone    != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.end_stone    )) tBlock = BlocksGT.oreEndstone;
		}
		return tBlock != null && tBlock.placeBlock(aWorld, aX, aY, aZ, (byte)6, aID, null, F, T);
	}
	
	public static boolean setSmallOre(World aWorld, int aX, int aY, int aZ, OreDictMaterial aMaterial) {
		return aMaterial != null && setSmallOre(aWorld, aX, aY, aZ, aMaterial.mID);
	}
	
	public static boolean setSmallOre(World aWorld, int aX, int aY, int aZ, short aID) {
		if (aID <= 0 && aID == W) return F;
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == NB) return F;
		byte aMetaData = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
		if (BlocksGT.sDontGenerateOresIn.contains(new ItemStackContainer(aBlock, 1, aMetaData))) return F;
		IBlockPlacable tBlock = BlocksGT.stoneToSmallOres.get(new ItemStackContainer(aBlock, 1, aMetaData));
		if (tBlock == null) {
		if (Blocks.stone        != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.stone        )) tBlock = BlocksGT.oreSmall; else
		if (Blocks.gravel       != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.gravel       )) tBlock = BlocksGT.oreSmallGravel; else
		if (Blocks.sand         != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.sand         )) tBlock = BlocksGT.oreSmallSand; else
		if (Blocks.netherrack   != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.netherrack   )) tBlock = BlocksGT.oreSmallNetherrack; else
		if (Blocks.end_stone    != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.end_stone    )) tBlock = BlocksGT.oreSmallEndstone;
		}
		return tBlock != null && tBlock.placeBlock(aWorld, aX, aY, aZ, (byte)6, aID, null, F, T);
	}
	
	/** Removes Bedrock from that Position and replaces it with regular Stone of the region. */
	public static boolean removeBedrock(World aWorld, int aX, int aY, int aZ) {
		Block tBlock = aWorld.getBlock(aX, aY, aZ), tStone = (aWorld.provider.dimensionId == DIM_NETHER ? Blocks.netherrack : Blocks.stone);
		
		if (tBlock == NB || bedrock(tBlock)) {
			for (int i = 1; i < 7; i++) for (byte tSide : ALL_SIDES_BUT_BOTTOM) {
				tBlock = aWorld.getBlock(aX+OFFSETS_X[tSide]*i, aY+OFFSETS_Y[tSide]*i, aZ+OFFSETS_Z[tSide]*i);
				if (tBlock != NB && tBlock != tStone && !bedrock(tBlock)) {
					int tMetaData = aWorld.getBlockMetadata(aX+OFFSETS_X[tSide]*i, aY+OFFSETS_Y[tSide]*i, aZ+OFFSETS_Z[tSide]*i);
					if (BlocksGT.stoneToNormalOres.containsKey(new ItemStackContainer(tBlock, 1, tMetaData))) {
						return aWorld.setBlock(aX, aY, aZ, tBlock, tMetaData, 0);
					}
				}
			}
			return aWorld.setBlock(aX, aY, aZ, tStone, 0, 0);
		}
		return F;
	}
	
	public static long scan(ArrayList<String> aList, EntityPlayer aPlayer, World aWorld, int aScanLevel, int aX, int aY, int aZ, byte aSide, float aClickX, float aClickY, float aClickZ) {
		if (aList == null) return 0;
		
		ArrayList<String> rList = new ArrayListNoNulls<>();
		long rEUAmount = 0;
		
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
		TileEntity aTileEntity = te(aWorld, aX, aY, aZ, T);
		
		rList.add("--- X: " + aX + " Y: " + aY + " Z: " + aZ + " ---");
		try {
			rList.add("Name: " + (aTileEntity instanceof IInventory && Code.stringValid(((IInventory)aTileEntity).getInventoryName()) ? ((IInventory)aTileEntity).getInventoryName() : aBlock.getUnlocalizedName()) + "  MetaData: " + aMeta);
			if (aScanLevel >= 10) {
				rList.add("Block Class: " + aBlock.getClass());
				if (aTileEntity != null) rList.add("TileEntity Class: " + aTileEntity.getClass());
			}
			float tResistance = aBlock.getExplosionResistance(aPlayer, aWorld, aX, aY, aZ, aPlayer.posX, aPlayer.posY, aPlayer.posZ);
			rList.add("Hardness: " + aBlock.getBlockHardness(aWorld, aX, aY, aZ) + "  Blast Resistance: " + tResistance + (tResistance < 16 ? " (TNT Blastable)" : " (TNT Proof)"));
			int tHarvestLevel = aBlock.getHarvestLevel(aMeta);
			String tHarvestTool = aBlock.getHarvestTool(aMeta);
			rList.add(tHarvestLevel == 0 && aBlock.getMaterial().isAdventureModeExempt() ? "Hand-Harvestable, but " + (Code.stringValid(tHarvestTool)?Code.capitalise(tHarvestTool):"None") + " is faster" : "Tool to Harvest: " + (Code.stringValid(tHarvestTool)?Code.capitalise(tHarvestTool):"None") + " (" + tHarvestLevel + ")");
			if (aBlock.isBeaconBase(aWorld, aX, aY, aZ, aX, aY+1, aZ)) rList.add("Is usable for Beacon Pyramids");
			if (MD.GC.mLoaded && aBlock instanceof IPartialSealableBlock) rList.add(((IPartialSealableBlock)aBlock).isSealed(aWorld, aX, aY, aZ, FORGE_DIR[aSide ^ 1]) ? "Is Sealable on this Side" : "Is not Sealable on this Side");
		} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
		if (aTileEntity != null) {
			try {if (aTileEntity instanceof ITileEntityWeight && ((ITileEntityWeight)aTileEntity).getWeightValue(aSide) > 0) {
				rEUAmount+=V[3];
				rList.add("Weight: " + ((ITileEntityWeight)aTileEntity).getWeightValue(aSide) + " kg");
			}} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
			try {if (aTileEntity instanceof ITileEntityTemperature && ((ITileEntityTemperature)aTileEntity).getTemperatureMax(aSide) > 0) {
				rEUAmount+=V[3];
				rList.add("Temperature: " + ((ITileEntityTemperature)aTileEntity).getTemperatureValue(aSide) + " / " + ((ITileEntityTemperature)aTileEntity).getTemperatureMax(aSide) + " K");
			}} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
			try {if (aTileEntity instanceof ITileEntityGibbl && ((ITileEntityGibbl)aTileEntity).getGibblMax(aSide) > 0) {
				rEUAmount+=V[3];
				rList.add("Pressure: " + ((ITileEntityGibbl)aTileEntity).getGibblValue(aSide) + " / " + ((ITileEntityGibbl)aTileEntity).getGibblMax(aSide) + " Gibbl");
			}} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
			try {if (aTileEntity instanceof ITileEntityProgress && ((ITileEntityProgress)aTileEntity).getProgressMax(aSide) > 0) {
				rEUAmount+=V[3];
				rList.add("Progress: " + ((ITileEntityProgress)aTileEntity).getProgressValue(aSide) + " / " + ((ITileEntityProgress)aTileEntity).getProgressMax(aSide));
			}} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
			
			
			String rState = "";
			try {if (aTileEntity instanceof ITileEntitySwitchableOnOff) {
				if (Code.stringValid(rState)) rState += " --- ";
				rEUAmount+=V[3];
				rState += ("State: " + (((ITileEntitySwitchableOnOff)aTileEntity).getStateOnOff()?"ON":"OFF"));
			}} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
			try {if (aTileEntity instanceof ITileEntitySwitchableMode) {
				if (Code.stringValid(rState)) rState += " --- ";
				rEUAmount+=V[3];
				rState += ("Mode: " + (((ITileEntitySwitchableMode)aTileEntity).getStateMode()));
			}} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
			try {if (aTileEntity instanceof ITileEntityRunningSuccessfully) {
				if (Code.stringValid(rState)) rState += " --- ";
				rEUAmount+=V[3];
				rState += ("Running: " + (((ITileEntityRunningSuccessfully)aTileEntity).getStateRunningSuccessfully()?"Successfully":((ITileEntityRunningSuccessfully)aTileEntity).getStateRunningActively()?"Actively":((ITileEntityRunningSuccessfully)aTileEntity).getStateRunningPassively()?"Passively":((ITileEntityRunningSuccessfully)aTileEntity).getStateRunningPossible()?"Possible":"Not Possible"));
			} else if (aTileEntity instanceof ITileEntityRunningActively) {
				if (Code.stringValid(rState)) rState += " --- ";
				rEUAmount+=V[3];
				rState += ("Running: " + (((ITileEntityRunningActively)aTileEntity).getStateRunningActively()?"Actively":((ITileEntityRunningActively)aTileEntity).getStateRunningPassively()?"Passively":((ITileEntityRunningActively)aTileEntity).getStateRunningPossible()?"Possible":"Not Possible"));
			} else if (aTileEntity instanceof ITileEntityRunningPassively) {
				if (Code.stringValid(rState)) rState += " --- ";
				rEUAmount+=V[3];
				rState += ("Running: " + (((ITileEntityRunningPassively)aTileEntity).getStateRunningPassively()?"Passively":((ITileEntityRunningPassively)aTileEntity).getStateRunningPossible()?"Possible":"Not Possible"));
			} else if (aTileEntity instanceof ITileEntityRunningPossible) {
				if (Code.stringValid(rState)) rState += " --- ";
				rEUAmount+=V[3];
				rState += ("Running: " + (((ITileEntityRunningPossible)aTileEntity).getStateRunningPossible()?"Possible":"Not Possible"));
			}} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
			if (Code.stringValid(rState)) rList.add(rState);
			
			
			try {if (aTileEntity instanceof ITileEntityEnergy) {
				rEUAmount+=V[3];
				for (TagData tEnergyType : ((ITileEntityEnergy)aTileEntity).getEnergyTypes(aSide)) {
					rList.add("Input: " + ((ITileEntityEnergy)aTileEntity).getEnergySizeInputMin(tEnergyType, aSide) + " to " + ((ITileEntityEnergy)aTileEntity).getEnergySizeInputMax(tEnergyType, aSide) + tEnergyType.getLocalisedNameShort());
					rList.add("Output: " + ((ITileEntityEnergy)aTileEntity).getEnergySizeOutputMin(tEnergyType, aSide) + " to " + ((ITileEntityEnergy)aTileEntity).getEnergySizeOutputMax(tEnergyType, aSide) + tEnergyType.getLocalisedNameShort());
				}
			}} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
			try {if (aTileEntity instanceof ITileEntityEnergyDataCapacitor) {
				rEUAmount+=V[3];
				for (TagData tEnergyType : ((ITileEntityEnergyDataCapacitor)aTileEntity).getEnergyCapacitorTypes(aSide)) {
					rList.add("Stored: " + ((ITileEntityEnergyDataCapacitor)aTileEntity).getEnergyStored(tEnergyType, aSide) + " of " + ((ITileEntityEnergyDataCapacitor)aTileEntity).getEnergyCapacity(tEnergyType, aSide) + tEnergyType.getLocalisedNameShort());
				}
			}} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
			
			
			try {if (aTileEntity instanceof IFluidHandler) {
				rEUAmount+=V[3];
				FluidTankInfo[] tTanks = ((IFluidHandler)aTileEntity).getTankInfo(FORGE_DIR[aSide]);
				if (tTanks != null) for (byte i = 0; i < tTanks.length; i++) {
					rList.add("Tank " + i + ": " + (tTanks[i].fluid==null?0:tTanks[i].fluid.amount) + " / " + tTanks[i].capacity + " " + FL.name(tTanks[i].fluid, T));
				}
			}} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
			
			if (!(aTileEntity instanceof ITileEntity)) {
				try {if (aTileEntity instanceof ic2.api.reactor.IReactorChamber) {
					rEUAmount+=V[4];
					aTileEntity = (TileEntity)(((ic2.api.reactor.IReactorChamber)aTileEntity).getReactor());
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
				try {if (aTileEntity instanceof ic2.api.reactor.IReactor) {
					rEUAmount+=V[4];
					rList.add( "Heat: " + ((ic2.api.reactor.IReactor)aTileEntity).getHeat() + "/" + ((ic2.api.reactor.IReactor)aTileEntity).getMaxHeat()
							+ "  HEM: " + ((ic2.api.reactor.IReactor)aTileEntity).getHeatEffectModifier() + "  Base IC2-EU Output: " + ((ic2.api.reactor.IReactor)aTileEntity).getReactorEUEnergyOutput());
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
				try {if (aTileEntity instanceof ic2.api.tile.IWrenchable) {
					rEUAmount+=V[3];
					rList.add("Facing: " + ((ic2.api.tile.IWrenchable)aTileEntity).getFacing() + " / IC2 Wrench Drop Chance: " + (((ic2.api.tile.IWrenchable)aTileEntity).wrenchCanRemove(aPlayer)?(((ic2.api.tile.IWrenchable)aTileEntity).getWrenchDropRate()*100):0) + "%");
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
				try {if (aTileEntity instanceof ic2.api.energy.tile.IEnergySink) {
					rEUAmount+=V[3];
					rList.add("Demanded Energy: " + ((ic2.api.energy.tile.IEnergySink)aTileEntity).getDemandedEnergy() + " IC2-EU");
					rList.add("Max Safe Input: " + V[((ic2.api.energy.tile.IEnergySink)aTileEntity).getSinkTier()] + " IC2-EU/t");
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
				try {if (aTileEntity instanceof ic2.api.energy.tile.IEnergySource) {
					rEUAmount+=V[3];
					rList.add("Max Energy Output: " + V[((ic2.api.energy.tile.IEnergySource)aTileEntity).getSourceTier()] + " IC2-EU/t");
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
				try {if (aTileEntity instanceof ic2.api.energy.tile.IEnergyConductor) {
					rEUAmount+=V[3];
					rList.add("Conduction Loss: " + ((ic2.api.energy.tile.IEnergyConductor)aTileEntity).getConductionLoss() + " IC2-EU/m");
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
				try {if (aTileEntity instanceof ic2.api.tile.IEnergyStorage) {
					rEUAmount+=V[3];
					rList.add("Contained Energy: " + ((ic2.api.tile.IEnergyStorage)aTileEntity).getStored() + " of " + ((ic2.api.tile.IEnergyStorage)aTileEntity).getCapacity() + " IC2-EU");
					rList.add(((ic2.api.tile.IEnergyStorage)aTileEntity).isTeleporterCompatible(FORGE_DIR[aSide])?"Teleporter Compatible":"Not Teleporter Compatible");
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
			}
		}
		try {if (aBlock instanceof IBlockDebugable) {
			rEUAmount+=V[3];
			ArrayList<String> temp = ((IBlockDebugable)aBlock).getDebugInfo(aPlayer, aX, aY, aZ, aScanLevel);
			if (temp != null) rList.addAll(temp);
		}} catch(Throwable e) {if (D1) e.printStackTrace(ERR);}
		
		BlockScanningEvent tEvent = new BlockScanningEvent(aWorld, aPlayer, aX, aY, aZ, aSide, aScanLevel, aBlock, aTileEntity, rList, aClickX, aClickY, aClickZ);
		tEvent.mEUCost = rEUAmount;
		MinecraftForge.EVENT_BUS.post(tEvent);
		if (!tEvent.isCanceled()) aList.addAll(rList);
		return tEvent.mEUCost;
	}
}
