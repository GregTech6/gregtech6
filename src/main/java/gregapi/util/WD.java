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

package gregapi.util;

import cpw.mods.fml.common.FMLCommonHandler;
import gregapi.GT_API;
import gregapi.block.IBlockDebugable;
import gregapi.block.IBlockExtendedMetaData;
import gregapi.block.IBlockPlacable;
import gregapi.block.IBlockTileEntity;
import gregapi.block.metatype.BlockMetaType;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.code.TagData;
import gregapi.data.*;
import gregapi.event.BlockScanningEvent;
import gregapi.item.IItemGT;
import gregapi.oredict.OreDictMaterial;
import gregapi.random.IHasWorldAndCoords;
import gregapi.tileentity.ITileEntity;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.ITileEntityUnloadable;
import gregapi.tileentity.data.ITileEntityGibbl;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.tileentity.data.ITileEntityTemperature;
import gregapi.tileentity.data.ITileEntityWeight;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.delegate.ITileEntityDelegating;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import gregapi.tileentity.machines.*;
import gregapi.util.UT.Code;
import gregtech.blocks.fluids.BlockWaterlike;
import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import net.minecraft.block.*;
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
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.*;
import thaumcraft.api.nodes.INode;
import twilightforest.TwilightForestMod;

import java.util.*;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class WD {
	public static ItemStack suck(IHasWorldAndCoords aCoordinates) {return suck(aCoordinates.getWorld(), aCoordinates.getX(), aCoordinates.getY(), aCoordinates.getZ());}
	public static ItemStack suck(World aWorld, double aX, double aY, double aZ) {return suck(aWorld, aX, aY, aZ, 1, 1, 1);}
	@SuppressWarnings("unchecked")
	public static ItemStack suck(World aWorld, double aX, double aY, double aZ, double aL, double aH, double aW) {
		for (EntityItem tItem : (Iterable<EntityItem>)aWorld.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+aL, aY+aH, aZ+aW))) {
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
	public static List<ItemStack> suckAll(IHasWorldAndCoords aCoordinates) {return suckAll(aCoordinates.getWorld(), aCoordinates.getX(), aCoordinates.getY(), aCoordinates.getZ());}
	public static List<ItemStack> suckAll(World aWorld, double aX, double aY, double aZ) {return suckAll(aWorld, aX, aY, aZ, 1, 1, 1);}
	@SuppressWarnings("unchecked")
	public static List<ItemStack> suckAll(World aWorld, double aX, double aY, double aZ, double aL, double aH, double aW) {
		List<EntityItem> tList = aWorld.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(aX, aY, aZ, aX+aL, aY+aH, aZ+aW));
		if (tList.isEmpty()) return Collections.emptyList();
		List<ItemStack> rOutput = ST.arraylist();
		for (EntityItem tItem : tList) {
			if (!tItem.isDead) {
				aWorld.removeEntity(tItem);
				ItemStack rStack = tItem.getEntityItem();
				tItem.setEntityItemStack(ST.amount(0, rStack));
				tItem.setDead();
				rOutput.add(rStack);
			}
		}
		return rOutput;
	}
	
	public static boolean obstructed(World aWorld, int aX, int aY, int aZ, byte aSide) {
		if (!OBSTRUCTION_CHECKS) return F;
		aX += OFFX[aSide]; aY += OFFY[aSide]; aZ += OFFZ[aSide];
		TileEntity tTileEntity = te(aWorld, aX, aY, aZ, T);
		if (tTileEntity != null) {
			if (tTileEntity instanceof ITileEntityQuickObstructionCheck) return ((ITileEntityQuickObstructionCheck)tTileEntity).isObstructingBlockAt(OPOS[aSide]);
			if (MD.TC.mLoaded && tTileEntity instanceof INode) return F;
		}
		Block tBlock = aWorld.getBlock(aX, aY, aZ);
		if (tBlock instanceof BlockTrapDoor || tBlock instanceof BlockDoor || tBlock instanceof BlockLadder) return F;
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
	
	public static boolean dimOverworldLike(World aWorld) {return aWorld != null && dimOverworldLike(aWorld.provider);}
	public static boolean dimOverworldLike(WorldProvider aProvider) {return aProvider.dimensionId == 0 || dimOverworldLike(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimOverworldLike(WorldProvider aProvider, String aProviderClassName) {return aProvider.dimensionId == 0 || dimENVM(aProvider, aProviderClassName) || dimA97(aProvider, aProviderClassName) || dimWTCH(aProvider, aProviderClassName) || dimMYST(aProvider, aProviderClassName) || dimCW2(aProvider, aProviderClassName);}
	
	public static boolean dimPlanet(World aWorld) {return aWorld != null && dimPlanet(aWorld.provider);}
	public static boolean dimPlanet(WorldProvider aProvider) {return Math.abs(aProvider.dimensionId) > 1 && dimPlanet(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimPlanet(WorldProvider aProvider, String aProviderClassName) {return !(Math.abs(aProvider.dimensionId) <= 1 || dimMYST(aProvider, aProviderClassName) || dimATUM(aProvider, aProviderClassName) || dimWTCH(aProvider, aProviderClassName) || dimA97(aProvider, aProviderClassName) || dimCW2(aProvider, aProviderClassName) || dimTF(aProvider, aProviderClassName) || dimERE(aProvider, aProviderClassName) || dimBTL(aProvider, aProviderClassName) || dimENVM(aProvider, aProviderClassName) || dimDD(aProvider, aProviderClassName) || dimLM(aProvider, aProviderClassName) || dimAETHER(aProvider, aProviderClassName) || dimALF(aProvider, aProviderClassName) || dimTROPIC(aProvider, aProviderClassName) || dimCANDY(aProvider, aProviderClassName));}
	
	public static boolean dimMYST(World aWorld) {return aWorld != null && dimMYST(aWorld.provider);}
	public static boolean dimMYST(WorldProvider aProvider) {return MD.MYST.mLoaded && aProvider.getClass().getName().toLowerCase().contains("com.xcompwiz.mystcraft");}
	public static boolean dimMYST(WorldProvider aProvider, String aProviderClassName) {return MD.MYST.mLoaded && aProvider.getClass().getName().toLowerCase().contains("com.xcompwiz.mystcraft");}
	
	public static boolean dimCANDY(World aWorld) {return aWorld != null && dimCANDY(aWorld.provider);}
	public static boolean dimCANDY(WorldProvider aProvider) {return MD.CANDY.mLoaded && dimCANDY(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimCANDY(WorldProvider aProvider, String aProviderClassName) {return MD.CANDY.mLoaded && "WorldProviderCandy".equalsIgnoreCase(UT.Reflection.getLowercaseClass(aProvider));}
	
	public static boolean dimTROPIC(World aWorld) {return aWorld != null && dimTROPIC(aWorld.provider);}
	public static boolean dimTROPIC(WorldProvider aProvider) {return MD.TROPIC.mLoaded && dimTROPIC(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimTROPIC(WorldProvider aProvider, String aProviderClassName) {return MD.TROPIC.mLoaded && "WorldProviderTropicraft".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimATUM(World aWorld) {return aWorld != null && dimATUM(aWorld.provider);}
	public static boolean dimATUM(WorldProvider aProvider) {return MD.ATUM.mLoaded && dimATUM(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimATUM(WorldProvider aProvider, String aProviderClassName) {return MD.ATUM.mLoaded && "AtumWorldProvider".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimTF(World aWorld) {return aWorld != null && dimTF(aWorld.provider);}
	public static boolean dimTF(WorldProvider aProvider) {return MD.TF.mLoaded && aProvider.dimensionId == TwilightForestMod.dimensionID;}
	public static boolean dimTF(WorldProvider aProvider, String aProviderClassName) {return MD.TF.mLoaded && aProvider.dimensionId == TwilightForestMod.dimensionID;}
	
	public static boolean dimBTL(World aWorld) {return aWorld != null && dimBTL(aWorld.provider);}
	public static boolean dimBTL(WorldProvider aProvider) {return MD.BTL.mLoaded && dimBTL(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimBTL(WorldProvider aProvider, String aProviderClassName) {return MD.BTL.mLoaded && "WorldProviderBetweenlands".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimERE(World aWorld) {return aWorld != null && dimERE(aWorld.provider);}
	public static boolean dimERE(WorldProvider aProvider) {return MD.ERE.mLoaded && dimERE(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimERE(WorldProvider aProvider, String aProviderClassName) {return MD.ERE.mLoaded && "WorldProviderErebus".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimALF(World aWorld) {return aWorld != null && dimALF(aWorld.provider);}
	public static boolean dimALF(WorldProvider aProvider) {return MD.ALF.mLoaded && dimALF(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimALF(WorldProvider aProvider, String aProviderClassName) {return MD.ALF.mLoaded && "WorldProviderAlfheim".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimDD(World aWorld) {return aWorld != null && dimDD(aWorld.provider);}
	public static boolean dimDD(WorldProvider aProvider) {return (MD.ExU.mLoaded || MD.ExS.mLoaded) && dimDD(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimDD(WorldProvider aProvider, String aProviderClassName) {return (MD.ExU.mLoaded || MD.ExS.mLoaded) && "WorldProviderUnderdark".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimLM(World aWorld) {return aWorld != null && dimLM(aWorld.provider);}
	public static boolean dimLM(WorldProvider aProvider) {return (MD.ExU.mLoaded || MD.ExS.mLoaded) && dimLM(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimLM(WorldProvider aProvider, String aProviderClassName) {return (MD.ExU.mLoaded || MD.ExS.mLoaded) && "WorldProviderEndOfTime".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimENVM(World aWorld) {return aWorld != null && dimENVM(aWorld.provider);}
	public static boolean dimENVM(WorldProvider aProvider) {return MD.ENVM.mLoaded && dimENVM(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimENVM(WorldProvider aProvider, String aProviderClassName) {return MD.ENVM.mLoaded && "WorldProviderCaves".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimA97(World aWorld) {return aWorld != null && dimA97(aWorld.provider);}
	public static boolean dimA97(WorldProvider aProvider) {return MD.A97_MINING.mLoaded && dimA97(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimA97(WorldProvider aProvider, String aProviderClassName) {return MD.A97_MINING.mLoaded && "WorldProviderMiner".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimCW2(World aWorld) {return aWorld != null && dimCW2(aWorld.provider);}
	public static boolean dimCW2(WorldProvider aProvider) {return MD.CW2.mLoaded && dimCW2(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimCW2(WorldProvider aProvider, String aProviderClassName) {return dimCW2AquaCavern(aProvider, aProviderClassName) || dimCW2Caveland(aProvider, aProviderClassName) || dimCW2Cavenia(aProvider, aProviderClassName) || dimCW2Cavern(aProvider, aProviderClassName) || dimCW2Caveworld(aProvider, aProviderClassName);}
	
	public static boolean dimCW2AquaCavern(World aWorld) {return aWorld != null && dimCW2AquaCavern(aWorld.provider);}
	public static boolean dimCW2AquaCavern(WorldProvider aProvider) {return MD.CW2.mLoaded && dimCW2AquaCavern(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimCW2AquaCavern(WorldProvider aProvider, String aProviderClassName) {return MD.CW2.mLoaded && "WorldProviderAquaCavern".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimCW2Caveland(World aWorld) {return aWorld != null && dimCW2Caveland(aWorld.provider);}
	public static boolean dimCW2Caveland(WorldProvider aProvider) {return MD.CW2.mLoaded && dimCW2Caveland(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimCW2Caveland(WorldProvider aProvider, String aProviderClassName) {return MD.CW2.mLoaded && "WorldProviderCaveland".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimCW2Cavenia(World aWorld) {return aWorld != null && dimCW2Cavenia(aWorld.provider);}
	public static boolean dimCW2Cavenia(WorldProvider aProvider) {return MD.CW2.mLoaded && dimCW2Cavenia(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimCW2Cavenia(WorldProvider aProvider, String aProviderClassName) {return MD.CW2.mLoaded && "WorldProviderCavenia".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimCW2Cavern(World aWorld) {return aWorld != null && dimCW2Cavern(aWorld.provider);}
	public static boolean dimCW2Cavern(WorldProvider aProvider) {return MD.CW2.mLoaded && dimCW2Cavern(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimCW2Cavern(WorldProvider aProvider, String aProviderClassName) {return MD.CW2.mLoaded && "WorldProviderCavern".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimCW2Caveworld(World aWorld) {return aWorld != null && dimCW2Caveworld(aWorld.provider);}
	public static boolean dimCW2Caveworld(WorldProvider aProvider) {return MD.CW2.mLoaded && dimCW2Caveworld(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimCW2Caveworld(WorldProvider aProvider, String aProviderClassName) {return MD.CW2.mLoaded && "WorldProviderCaveworld".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimWTCH(World aWorld) {return aWorld != null && dimWTCH(aWorld.provider);}
	public static boolean dimWTCH(WorldProvider aProvider) {return MD.WTCH.mLoaded && dimWTCH(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimWTCH(WorldProvider aProvider, String aProviderClassName) {return MD.WTCH.mLoaded && "WorldProviderDreamWorld".equalsIgnoreCase(aProviderClassName);}
	
	public static boolean dimAETHER(World aWorld) {return aWorld != null && dimAETHER(aWorld.provider);}
	public static boolean dimAETHER(WorldProvider aProvider) {return (MD.AETHER.mLoaded || MD.AETHEL.mLoaded) && dimAETHER(aProvider, UT.Reflection.getLowercaseClass(aProvider));}
	public static boolean dimAETHER(WorldProvider aProvider, String aProviderClassName) {return MD.AETHEL.mLoaded ? "AetherWorldProvider".equalsIgnoreCase(aProviderClassName) : MD.AETHER.mLoaded && "WorldProviderAether".equalsIgnoreCase(aProviderClassName);}
	
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
	
	
	/** Marks a Chunk dirty so it is saved */
	public static boolean mark(World aWorld, int aX, int aZ) {
		if (aWorld == null || aWorld.isRemote) return F;
		Chunk aChunk = aWorld.getChunkFromBlockCoords(aX, aZ);
		if (aChunk == null) {
			aWorld.getBlockMetadata(aX, 0, aZ);
			aChunk = aWorld.getChunkFromBlockCoords(aX, aZ);
			if (aChunk == null) {
				ERR.println("Some important Chunk does not exist for some reason at Coordinates X: " + aX + " and Z: " + aZ);
				return F;
			}
		}
		aChunk.setChunkModified();
		return T;
	}
	/** Marks a Chunk dirty so it is saved */
	public static boolean mark(Object aTileEntity) {
		return aTileEntity instanceof TileEntity && mark(((TileEntity)aTileEntity).getWorldObj(), ((TileEntity)aTileEntity).xCoord, ((TileEntity)aTileEntity).zCoord);
	}
	
	
	/** to get a TileEntity properly, according to my additional Interfaces. Normally you should set aLoadUnloadedChunks to false, unless you have already checked these Coordinates, or you want to load Chunks */
	public static DelegatorTileEntity<TileEntity> te(World aWorld, ChunkCoordinates aCoords, byte aSide, boolean aLoadUnloadedChunks) {
		return te(aWorld, aCoords.posX, aCoords.posY, aCoords.posZ, aSide, aLoadUnloadedChunks);
	}
	/** to get a TileEntity properly, according to my additional Interfaces. Normally you should set aLoadUnloadedChunks to false, unless you have already checked these Coordinates, or you want to load Chunks */
	public static DelegatorTileEntity<TileEntity> te(World aWorld, int aX, int aY, int aZ, byte aSide, boolean aLoadUnloadedChunks) {
		TileEntity aTileEntity = te(aWorld, aX, aY, aZ, aLoadUnloadedChunks);
		return aTileEntity instanceof ITileEntityDelegating ? ((ITileEntityDelegating)aTileEntity).getDelegateTileEntity(aSide) : new DelegatorTileEntity<>(aTileEntity, aWorld, aX, aY, aZ, aSide);
	}
	/** to get a TileEntity properly, according to my additional Interfaces. Normally you should set aLoadUnloadedChunks to false, unless you have already checked these Coordinates, or you want to load Chunks */
	public static TileEntity te(World aWorld, ChunkCoordinates aCoords, boolean aLoadUnloadedChunks) {
		return te(aWorld, aCoords.posX, aCoords.posY, aCoords.posZ, aLoadUnloadedChunks);
	}
	/** to get a TileEntity properly, according to my additional Interfaces. Normally you should set aLoadUnloadedChunks to false, unless you have already checked these Coordinates, or you want to load Chunks */
	public static TileEntity te(World aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks) {
		if (aLoadUnloadedChunks || aWorld.blockExists(aX, aY, aZ)) {
			TileEntity rTileEntity = aWorld.getTileEntity(aX, aY, aZ);
			if (rTileEntity instanceof ITileEntityUnloadable && ((ITileEntityUnloadable)rTileEntity).isDead()) return null;
			if (rTileEntity != null) return rTileEntity;
			rTileEntity = LAST_BROKEN_TILEENTITY.get();
			if (rTileEntity != null && rTileEntity.xCoord == aX && rTileEntity.yCoord == aY && rTileEntity.zCoord == aZ) return rTileEntity;
			Block tBlock = aWorld.getBlock(aX, aY, aZ);
			return tBlock instanceof IBlockTileEntity ? ((IBlockTileEntity)tBlock).getTileEntity(aWorld, aX, aY, aZ) : null;
		}
		return null;
	}
	
	public static byte WARN_ABOUT_TILEENTITY_NEGATIVE_Y_COORD = 0;
	
	public static TileEntity invalidateTileEntityWithNegativeYCoord(int aX, int aY, int aZ, TileEntity aTileEntity) {
		if (WARN_ABOUT_TILEENTITY_NEGATIVE_Y_COORD == 0) UT.Entities.chat(null, "Please provide the gregtech.log File to Greg, there was a weird Error");
		if (WARN_ABOUT_TILEENTITY_NEGATIVE_Y_COORD < 10) {
			ERR.println("===============================");
			ERR.println("X:" + aX);
			ERR.println("Y:" + aY);
			ERR.println("Z:" + aZ);
			ERR.println("Class:" + aTileEntity.getClass());
			new Throwable().printStackTrace(ERR);
			ERR.println("===============================");
		}
		if (WARN_ABOUT_TILEENTITY_NEGATIVE_Y_COORD == 9) UT.Entities.chat(null, "Please provide the gregtech.log File to Greg, there was a LOT of weird Errors");
		if (WARN_ABOUT_TILEENTITY_NEGATIVE_Y_COORD < 99) WARN_ABOUT_TILEENTITY_NEGATIVE_Y_COORD++;
		aTileEntity.invalidate();
		aTileEntity.yCoord = 0;
		return aTileEntity;
	}
	
	/** Sets the TileEntity at the passed position, with the option of turning adjacent TileEntity updates off. */
	public static TileEntity te(World aWorld, int aX, int aY, int aZ, TileEntity aTileEntity, boolean aCauseTileEntityUpdates) {
		if (aY < 0) return invalidateTileEntityWithNegativeYCoord(aX, aY, aZ, aTileEntity);
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
		return (!MD.GC.mLoaded || !(aWorld.provider instanceof IGalacticraftWorldProvider)) && !hasCollide(aWorld, aX, aY, aZ) && !liquid(aWorld, aX, aY, aZ);
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
		return aProvider.dimensionId == DIM_OVERWORLD ? waterLevel(aDefaultOverworld) : aProvider.hasNoSky || dimTF(aProvider) ? 31 : 62;
	}
	/** @return the Height of the Water Level that should probably be in the Overworld. */
	public static int waterLevel(int aDefaultOverworld) {
		return MD.TFC.mLoaded || MD.TFCP.mLoaded? 143 : aDefaultOverworld;
	}
	/** @return the Height of the Water Level that should probably be in the Overworld. */
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
	
	public static Block block(IBlockAccess aWorld, int aX, int aY, int aZ) {return aWorld.getBlock(aX, aY, aZ);}
	public static Block block(World        aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks) {return aLoadUnloadedChunks || aWorld.blockExists(aX, aY, aZ) ? aWorld.getBlock(aX, aY, aZ) : NB;}
	public static Block block(World        aWorld, int aX, int aY, int aZ, byte aSide, boolean aLoadUnloadedChunks) {return block(aWorld, aX+OFFX[aSide], aY+OFFY[aSide], aZ+OFFZ[aSide], aLoadUnloadedChunks);}
	public static Block block(World        aWorld, int aX, int aY, int aZ, byte aSide) {return block(aWorld, aX+OFFX[aSide], aY+OFFY[aSide], aZ+OFFZ[aSide]);}
	public static byte  meta (IBlockAccess aWorld, int aX, int aY, int aZ) {return UT.Code.bind4(aWorld.getBlockMetadata(aX, aY, aZ));}
	public static byte  meta (World        aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks) {return aLoadUnloadedChunks || aWorld.blockExists(aX, aY, aZ) ? UT.Code.bind4(aWorld.getBlockMetadata(aX, aY, aZ)) : 0;}
	public static byte  meta (World        aWorld, int aX, int aY, int aZ, byte aSide, boolean aLoadUnloadedChunks) {return meta(aWorld, aX+OFFX[aSide], aY+OFFY[aSide], aZ+OFFZ[aSide], aLoadUnloadedChunks);}
	public static byte  meta (World        aWorld, int aX, int aY, int aZ, byte aSide) {return meta(aWorld, aX+OFFX[aSide], aY+OFFY[aSide], aZ+OFFZ[aSide]);}
	public static byte  meta (long aBitAnd, IBlockAccess aWorld, int aX, int aY, int aZ) {return UT.Code.bind4(aWorld.getBlockMetadata(aX, aY, aZ) & aBitAnd);}
	public static byte  meta (long aBitAnd, World        aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks) {return aLoadUnloadedChunks || aWorld.blockExists(aX, aY, aZ) ? UT.Code.bind4(aWorld.getBlockMetadata(aX, aY, aZ) & aBitAnd) : 0;}
	public static byte  meta (long aBitAnd, World        aWorld, int aX, int aY, int aZ, byte aSide, boolean aLoadUnloadedChunks) {return meta(aBitAnd, aWorld, aX+OFFX[aSide], aY+OFFY[aSide], aZ+OFFZ[aSide], aLoadUnloadedChunks);}
	public static byte  meta (long aBitAnd, World        aWorld, int aX, int aY, int aZ, byte aSide) {return meta(aBitAnd, aWorld, aX+OFFX[aSide], aY+OFFY[aSide], aZ+OFFZ[aSide]);}
	
	public static boolean set(World aWorld, int aX, int aY, int aZ, Block aBlock, long aMeta, long aFlags) {
		return set(aWorld, aX, aY, aZ, aBlock, aMeta, aFlags, aBlock.isOpaqueCube());
	}
	
	public static boolean set(World aWorld, int aX, int aY, int aZ, Block aBlock, long aMeta, long aFlags, boolean aRemoveGrassBelow) {
		if (aRemoveGrassBelow) {
			Block tBlock = aWorld.getBlock(aX, aY-1, aZ);
			if (tBlock == Blocks.grass || tBlock == Blocks.mycelium) aWorld.setBlock(aX, aY-1, aZ, Blocks.dirt, 0, (byte)aFlags);
		}
		return aWorld.setBlock(aX, aY, aZ, aBlock, aBlock==NB?0:Code.bind4(aMeta), (byte)aFlags);
	}
	
	public static boolean set(Chunk aChunk, int aX, int aY, int aZ, Block aBlock, long aMeta) {
		return aChunk.func_150807_a(aX, aY, aZ, aBlock, aBlock==NB?0:Code.bind4(aMeta));
	}
	public static boolean set(Chunk aChunk, int aX, int aY, int aZ, Block aBlock, long aMeta, boolean aRemoveGrassBelow) {
		if (aRemoveGrassBelow) {
			Block tBlock = aChunk.getBlock(aX, aY-1, aZ);
			if (tBlock == Blocks.grass || tBlock == Blocks.mycelium) aChunk.func_150807_a(aX, aY-1, aZ, Blocks.dirt, 0);
		}
		return aChunk.func_150807_a(aX, aY, aZ, aBlock, aBlock==NB?0:Code.bind4(aMeta));
	}
	
	public static boolean replace(World aWorld, int aX, int aY, int aZ, Block aReplaceBlock, long aReplaceMeta, Block aTargetBlock, long aTargetMeta) {
		if (aTargetBlock == null || aReplaceBlock == null) return F;
		if (aReplaceBlock != block(aWorld, aX, aY, aZ)) return F;
		if (aReplaceMeta != W && aReplaceMeta != meta(aWorld, aX, aY, aZ)) return F;
		return aWorld.setBlock(aX, aY, aZ, aTargetBlock, Code.bind4(aTargetMeta), 2);
	}
	public static boolean replace(World aWorld, ChunkCoordinates aCoords, Block aReplaceBlock, long aReplaceMeta, Block aTargetBlock, long aTargetMeta) {
		return replace(aWorld, aCoords.posX, aCoords.posY, aCoords.posZ, aReplaceBlock, aReplaceMeta, aTargetBlock, aTargetMeta);
	}
	public static boolean replaceAll(World aWorld, int aX, int aY, int aZ, Block aReplaceBlock, long aReplaceMeta, Block aTargetBlock, long aTargetMeta) {
		return replaceAll(aWorld, new ChunkCoordinates(aX, aY, aZ), aReplaceBlock, aReplaceMeta, aTargetBlock, aTargetMeta);
	}
	public static boolean replaceAll(World aWorld, ChunkCoordinates aCoords, Block aReplaceBlock, long aReplaceMeta, Block aTargetBlock, long aTargetMeta) {
		if (!replace(aWorld, aCoords, aReplaceBlock, aReplaceMeta, aTargetBlock, aTargetMeta)) return F;
		HashSetNoNulls<ChunkCoordinates> tSwap,
		tDone  = new HashSetNoNulls<>(F, aCoords),
		tCheck = new HashSetNoNulls<>(F, aCoords),
		tNext  = new HashSetNoNulls<>();
		
		while (!tCheck.isEmpty() && tDone.size() < 32768) {
			tNext.clear();
			for (ChunkCoordinates tChecking : tCheck) {
				if (Math.abs(tChecking.posX - aCoords.posX) < 128 && Math.abs(tChecking.posZ - aCoords.posZ) < 128) for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
					ChunkCoordinates tCoords = new ChunkCoordinates(tChecking.posX+i, tChecking.posY+j, tChecking.posZ+k);
					if (tDone.add(tCoords) && replace(aWorld, tCoords, aReplaceBlock, aReplaceMeta, aTargetBlock, aTargetMeta)) tNext.add(tCoords);
				}
			}
			tSwap = tNext; tNext = tCheck; tCheck = tSwap;
		}
		return T;
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
	
	public static Random random(World aWorld, long aChunkX, long aChunkZ) {return random(aChunkX >> 4, aChunkZ >> 4, aWorld.getSeed() ^ aWorld.provider.dimensionId);}
	public static Random random(long aSeed, long aChunkX, long aChunkZ) {
		// Seed is XOR-ed with the Dimension ID to prevent multiple Dimensions from being identical in Ore Generation.
		// Yes that actually happened with Aromas Mining World, and resulted in a prospecting exploit.
		Random rRandom = new Random(aSeed);
		// Javas Random sucks so bad, the first few results are to be discarded
		for (int i = 0; i < 50; i++) rRandom.nextInt(0x00ffffff);
		// And then I use the first Result as a Seed for a second Random because it is THAT bad!
		rRandom = new Random(aSeed ^ ((rRandom.nextLong() >> 2 + 1L) * aChunkX + (rRandom.nextLong() >> 2 + 1L) * aChunkZ));
		// Javas Random still sucks badly, discarding some results again.
		for (int i = 0; i < 50; i++) rRandom.nextInt(0x00ffffff);
		// There we have it, a somewhat working Random function that is actually random
		// and does not cause my Code to generate almost perfect Diagonal Lines of Ores.
		return rRandom;
	}
	
	public static int random(World aWorld, int aX, int aY, int aZ, int aBound) {return random(aWorld.getSeed() ^ aWorld.provider.dimensionId, aX, aY, aZ, aBound);}
	public static int random(long aSeed, int aX, int aY, int aZ, int aBound) {
		Random rRandom = new Random(aSeed ^ aY);
		for (int i = 0; i < 10; i++) rRandom.nextInt(0x00ffffff);
		rRandom = new Random(aSeed ^ ((rRandom.nextLong() >> 2 + 1L) * aX + (rRandom.nextLong() >> 2 + 1L) * aZ));
		for (int i = 0; i < 10; i++) rRandom.nextInt(0x00ffffff);
		return rRandom.nextInt(aBound);
	}
	
	public static Random random(TileEntity aTileEntity) {return new Random(aTileEntity.xCoord ^ aTileEntity.yCoord ^ aTileEntity.zCoord);}
	public static int random(TileEntity aTileEntity, int aBound) {return random(aTileEntity).nextInt(aBound);}
	public static boolean random(TileEntity aTileEntity, int aBound, long aTime) {return random(aTileEntity, aBound) == aTime % aBound;}
	
	public static boolean border(int aFromX, int aFromZ, int aToX, int aToZ) {return aFromX >> 4 != aToX >> 4 || aFromZ >> 4 != aToZ >> 4;}
	
	public static boolean even(TileEntity aTileEntity) {return even(aTileEntity.xCoord, aTileEntity.yCoord, aTileEntity.zCoord);}
	public static boolean even(ChunkCoordinates aCoords) {return even(aCoords.posX, aCoords.posY, aCoords.posZ);}
	public static boolean even(int... aCoords) {int i = 0; for (int tCoord : aCoords) if (tCoord % 2 == 0) i++; return i % 2 == 0;}
	
	public static int evenness(TileEntity aTileEntity) {return evenness(aTileEntity.xCoord, aTileEntity.yCoord, aTileEntity.zCoord);}
	public static int evenness(ChunkCoordinates aCoords) {return evenness(aCoords.posX, aCoords.posY, aCoords.posZ);}
	public static int evenness(int... aCoords) {int i = 0; for (int tCoord : aCoords) {i <<= 1; if (tCoord % 2 != 0) i++;} return i;}
	
	public static boolean setIfDiff(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMeta, int aFlags) {return (aWorld.getBlock(aX, aY, aZ) != aBlock || aWorld.getBlockMetadata(aX, aY, aZ) != aMeta) && aWorld.setBlock(aX, aY, aZ, aBlock, aMeta, aFlags);}
	
	public static boolean set(World aWorld, int aX, int aY, int aZ, ItemStack aStack) {
		Block tBlock = ST.block(aStack);
		if (tBlock == NB) return F;
		if (tBlock instanceof IBlockPlacable) return ((IBlockPlacable)tBlock).placeBlock(aWorld, aX, aY, aZ, (byte)6, ST.meta_(aStack), aStack.getTagCompound(), T, F);
		if (ST.meta_(aStack) < 16) return aWorld.setBlock(aX, aY, aZ, tBlock, ST.meta_(aStack), 3);
		return F;
	}
	
	public static boolean leafdecay(World aWorld, int aX, int aY, int aZ, Block aBlock) {return leafdecay(aWorld, aX, aY, aZ, aBlock, F, F);}
	public static boolean leafdecay(World aWorld, int aX, int aY, int aZ, Block aBlock, boolean aOnlyTopArea) {return leafdecay(aWorld, aX, aY, aZ, aBlock, aOnlyTopArea, F);}
	public static boolean leafdecay(World aWorld, int aX, int aY, int aZ, Block aBlock, boolean aOnlyTopArea, boolean aTreeCapitator) {
		if (aBlock == null || aBlock.canSustainLeaves(aWorld, aX, aY, aZ)) {
			for (int j = (aOnlyTopArea ? 0 : -7); j <= 7; ++j) for (int i = -7; i <= 7; ++i) for (int k = -7; k <= 7; ++k) {
				Block tBlock = aWorld.getBlock(aX+i, aY+j, aZ+k);
				if (tBlock != NB) {
					if (tBlock == Blocks.brown_mushroom_block || tBlock == Blocks.red_mushroom_block) {
						if (aTreeCapitator && Math.abs(i) <= 4 && Math.abs(k) <= 4 && j <= 0 && j >= -2) aWorld.func_147480_a(aX+i, aY+j, aZ+k, T);
					} else if (IL.NeLi_Wart_Block_Crimson.equal(tBlock) || IL.NeLi_ShroomLight.equal(tBlock)) {
						if (aTreeCapitator && Math.abs(i) <= 4 && Math.abs(k) <= 4) aWorld.func_147480_a(aX+i, aY+j, aZ+k, T);
					} else {
						if (tBlock.isLeaves(aWorld, aX+i, aY+j, aZ+k)) aWorld.scheduleBlockUpdate(aX+i, aY+j, aZ+k, tBlock, 1+RNGSUS.nextInt(100));
					}
				}
			}
			return T;
		}
		return F;
	}
	
	public static boolean liquid(World aWorld, int aX, int aY, int aZ) {return liquid(aWorld.getBlock(aX, aY, aZ));}
	public static boolean liquid(Block aBlock) {return aBlock instanceof BlockLiquid || aBlock instanceof IFluidBlock;}
	
	public static boolean liquid_classic(World aWorld, int aX, int aY, int aZ) {return liquid_classic(aWorld.getBlock(aX, aY, aZ));}
	public static boolean liquid_classic(Block aBlock) {return aBlock instanceof BlockLiquid || aBlock instanceof BlockFluidClassic;}
	
	public static boolean liquid_finite(World aWorld, int aX, int aY, int aZ) {return liquid_finite(aWorld.getBlock(aX, aY, aZ));}
	public static boolean liquid_finite(Block aBlock) {return aBlock instanceof BlockFluidFinite;}
	
	public static boolean liquid_borken(World aWorld, int aX, int aY, int aZ) {return liquid_borken(aWorld.getBlock(aX, aY, aZ));}
	public static boolean liquid_borken(Block aBlock) {return !(aBlock instanceof IItemGT) && liquid_classic(aBlock);}
	
	public static boolean stone(Block aBlock, short aMeta) {
		if (aBlock == NB) return F;
		if (aBlock == Blocks.obsidian) return T;
		ItemStackContainer tStack = new ItemStackContainer(aBlock, 1, aMeta);
		return BlocksGT.stoneToNormalOres.containsKey(tStack) || BlocksGT.stoneToBrokenOres.containsKey(tStack) || BlocksGT.stoneToSmallOres.containsKey(tStack);
	}
	
	public static boolean floor(World aWorld, int aX, int aY, int aZ) {return floor(aWorld, aX, aY, aZ, aWorld.getBlock(aX, aY, aZ));}
	public static boolean floor(World aWorld, int aX, int aY, int aZ, Block aBlock) {return aBlock.isSideSolid(aWorld, aX, aY, aZ, FORGE_DIR[SIDE_UP]) || floor(aBlock);}
	public static boolean floor(Block aBlock) {return aBlock.isOpaqueCube() || aBlock instanceof BlockSlab || aBlock instanceof BlockStairs || aBlock instanceof BlockMetaType;}
	
	@SuppressWarnings("unlikely-arg-type")
	public static boolean ore(Block aBlock, short aMeta) {return (aBlock instanceof IBlockPlacable && (BlocksGT.stoneToBrokenOres.containsValue(aBlock) || BlocksGT.stoneToNormalOres.containsValue(aBlock) || BlocksGT.stoneToSmallOres.containsValue(aBlock)) || OM.prefixcontains(ST.make(aBlock, 1, aMeta), TD.Prefix.ORE));}
	public static boolean ore_stone(Block aBlock, short aMeta) {return ore(aBlock, aMeta) || stone(aBlock, aMeta);}
	
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
	public static boolean bedrock(World aWorld, int aX, int aY, int aZ, Block aBlock) {return bedrock(aBlock);}
	public static boolean bedrock(Block aBlock) {return aBlock == Blocks.bedrock || IL.BTL_Bedrock.equal(aBlock);}
	
	public static boolean grass(World aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks) {return grass(block(aWorld, aX, aY, aZ, aLoadUnloadedChunks), meta(aWorld, aX, aY, aZ, aLoadUnloadedChunks));}
	public static boolean grass(World aWorld, int aX, int aY, int aZ) {return grass(block(aWorld, aX, aY, aZ), meta(aWorld, aX, aY, aZ));}
	public static boolean grass(World aWorld, int aX, int aY, int aZ, Block aBlock, long aMeta) {return grass(aBlock, aMeta);}
	public static boolean grass(Block aBlock, long aMeta) {
		if (aBlock == Blocks.tallgrass) return T;
		if (aBlock == Blocks.double_plant)  return aMeta ==  2 || aMeta ==  3;
		if (IL.TF_Tall_Grass.equal(aBlock)) return aMeta ==  8 || aMeta == 10;
		return IL.AETHER_Tall_Grass.equal(aBlock);
	}
	
	public static boolean irrelevant(World aWorld, int aX, int aY, int aZ) {return irrelevant(aWorld, aX, aY, aZ, aWorld.getBlock(aX, aY, aZ));}
	public static boolean irrelevant(World aWorld, int aX, int aY, int aZ, Block aBlock) {return air(aWorld, aX, aY, aZ, aBlock) || aBlock == Blocks.vine || aBlock == Blocks.snow_layer || aBlock == Blocks.fire || grass(aWorld, aX, aY, aZ) || anywater(aBlock);}
	
	public static boolean easyRep(World aWorld, int aX, int aY, int aZ) {return easyRep(aWorld, aX, aY, aZ, aWorld.getBlock(aX, aY, aZ));}
	public static boolean easyRep(World aWorld, int aX, int aY, int aZ, Block aBlock) {return air(aWorld, aX, aY, aZ, aBlock) || aBlock instanceof BlockBush || aBlock instanceof BlockSnow || aBlock instanceof BlockFire || aBlock.isLeaves(aWorld, aX, aY, aZ) || aBlock.canBeReplacedByLeaves(aWorld, aX, aY, aZ);}
	
	public static boolean infiniteWater(World aWorld, int aX, int aY, int aZ              ) {int tLevel = waterLevel(aWorld); return                                                                                       UT.Code.inside(tLevel-15, tLevel, aY) && BIOMES_RIVER_LAKE.contains(aWorld.getBiomeGenForCoords(aX, aZ).biomeName);}
	public static boolean infiniteWater(World aWorld, int aX, int aY, int aZ, Block aBlock) {int tLevel = waterLevel(aWorld); return waterstream(aBlock) || ((aBlock == Blocks.water || aBlock == Blocks.flowing_water) && UT.Code.inside(tLevel-15, tLevel, aY) && BIOMES_RIVER_LAKE.contains(aWorld.getBiomeGenForCoords(aX, aZ).biomeName));}
	
	public static boolean hasCollide(World aWorld, int aX, int aY, int aZ) {return hasCollide(aWorld, aX, aY, aZ, aWorld.getBlock(aX, aY, aZ));}
	public static boolean hasCollide(World aWorld, int aX, int aY, int aZ, Block aBlock) {return aBlock.isOpaqueCube() || aBlock.getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ) != null;}
	
	public static boolean hasCollide(World aWorld, ChunkCoordinates aCoords) {return hasCollide(aWorld, aCoords, aWorld.getBlock(aCoords.posX, aCoords.posY, aCoords.posZ));}
	public static boolean hasCollide(World aWorld, ChunkCoordinates aCoords, Block aBlock) {return aBlock.isOpaqueCube() || aBlock.getCollisionBoundingBoxFromPool(aWorld, aCoords.posX, aCoords.posY, aCoords.posZ) != null;}
	
	public static boolean flaming(World aWorld, int aX, int aY, int aZ) {return block(aWorld, aX, aY, aZ, F) instanceof BlockFire;}
	public static boolean burning(World aWorld, int aX, int aY, int aZ) {return flaming(aWorld, aX, aY, aZ) || flaming(aWorld, aX+1, aY, aZ) || flaming(aWorld, aX-1, aY, aZ) || flaming(aWorld, aX, aY+1, aZ) || flaming(aWorld, aX, aY-1, aZ) || flaming(aWorld, aX, aY, aZ+1) || flaming(aWorld, aX, aY, aZ-1);}
	
	public static void burn(World aWorld, ChunkCoordinates aCoords, boolean aReplaceCenter, boolean aCheckFlammability) {for (byte tSide : aReplaceCenter?ALL_SIDES_MIDDLE_UP:ALL_SIDES_VALID) fire(aWorld, aCoords.posX+OFFX[tSide], aCoords.posY+OFFY[tSide], aCoords.posZ+OFFZ[tSide], aCheckFlammability);}
	public static void burn(World aWorld, int aX, int aY, int aZ  , boolean aReplaceCenter, boolean aCheckFlammability) {for (byte tSide : aReplaceCenter?ALL_SIDES_MIDDLE_UP:ALL_SIDES_VALID) fire(aWorld, aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide], aCheckFlammability);}
	
	public static boolean fire(World aWorld, ChunkCoordinates aCoords, boolean aCheckFlammability) {return fire(aWorld, aCoords.posX, aCoords.posY, aCoords.posZ, aCheckFlammability);}
	public static boolean fire(World aWorld, int aX, int aY, int aZ, boolean aCheckFlammability) {
		Block tBlock = aWorld.getBlock(aX, aY, aZ);
		if (tBlock.getMaterial() == Material.lava || tBlock.getMaterial() == Material.fire) return F;
		if (tBlock.getMaterial() == Material.carpet || tBlock.getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ) == null) {
			if (MD.TC.mLoaded && te(aWorld, aX, aY, aZ, T) instanceof INode) return F;
			if (tBlock.getFlammability(aWorld, aX, aY, aZ, FORGE_DIR[SIDE_ANY]) > 0) return aWorld.setBlock(aX, aY, aZ, Blocks.fire, 0, 3);
			if (tBlock instanceof IItemGT) return F;
			if (aCheckFlammability) {
				for (byte tSide : ALL_SIDES_VALID) {
					Block tAdjacent = block(aWorld, aX, aY, aZ, tSide);
					if (tAdjacent == Blocks.chest || tAdjacent == Blocks.trapped_chest) return aWorld.setBlock(aX, aY, aZ, Blocks.fire);
					if (tAdjacent.getFlammability(aWorld, aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide], FORGE_DIR_OPPOSITES[tSide]) > 0) return aWorld.setBlock(aX, aY, aZ, Blocks.fire);
				}
			} else {
				return aWorld.setBlock(aX, aY, aZ, Blocks.fire, 0, 3);
			}
		}
		return F;
	}
	
	public static boolean oreGenReplaceable(World aWorld, int aX, int aY, int aZ, boolean aAllowAir) {
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == NB) return aAllowAir;
		byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
		if (BlocksGT.sDontGenerateOresIn.contains(new ItemStackContainer(aBlock, 1, aMeta))) return F;
		if (BlocksGT.stoneToNormalOres.containsKey(new ItemStackContainer(aBlock, 1, aMeta))) return T;
		if (Blocks.stone      != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.stone     )) return T;
		if (Blocks.gravel     != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.gravel    )) return T;
		if (Blocks.sand       != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.sand      )) return T;
		if (Blocks.netherrack != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.netherrack)) return T;
		if (Blocks.end_stone  != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.end_stone )) return T;
		return F;
	}
	
	public static boolean setOre(World aWorld, int aX, int aY, int aZ, OreDictMaterial aMaterial) {
		return aMaterial != null && setOre(aWorld, aX, aY, aZ, aMaterial.mID);
	}
	
	public static boolean setOre(World aWorld, int aX, int aY, int aZ, short aID) {
		if (aID <= 0 && aID == W) return F;
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == NB) return F;
		byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
		if (BlocksGT.sDontGenerateOresIn.contains(new ItemStackContainer(aBlock, 1, aMeta))) return F;
		IBlockPlacable tBlock = BlocksGT.stoneToNormalOres.get(new ItemStackContainer(aBlock, 1, aMeta));
		if (tBlock == null) {
		if (Blocks.stone      != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.stone     )) tBlock = BlocksGT.ore; else
		if (Blocks.gravel     != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.gravel    )) tBlock = BlocksGT.oreGravel; else
		if (Blocks.sand       != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.sand      )) tBlock = BlocksGT.oreSand; else
		if (Blocks.netherrack != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.netherrack)) tBlock = BlocksGT.oreNetherrack; else
		if (Blocks.end_stone  != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.end_stone )) tBlock = BlocksGT.oreEndstone;
		}
		return tBlock != null && tBlock.placeBlock(aWorld, aX, aY, aZ, (byte)6, aID, null, F, T);
	}
	
	public static boolean setSmallOre(World aWorld, int aX, int aY, int aZ, OreDictMaterial aMaterial) {
		return aMaterial != null && setSmallOre(aWorld, aX, aY, aZ, aMaterial.mID);
	}
	
	public static boolean setSmallOre(World aWorld, int aX, int aY, int aZ, short aID) {
		if (aID <= 0 && aID == W) return F;
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == NB || WD.bedrock(aBlock)) return F;
		byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
		if (BlocksGT.sDontGenerateOresIn.contains(new ItemStackContainer(aBlock, 1, aMeta))) return F;
		IBlockPlacable tBlock = BlocksGT.stoneToSmallOres.get(new ItemStackContainer(aBlock, 1, aMeta));
		if (tBlock == null) {
		if (Blocks.stone      != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.stone     )) tBlock = BlocksGT.oreSmall; else
		if (Blocks.gravel     != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.gravel    )) tBlock = BlocksGT.oreSmallGravel; else
		if (Blocks.sand       != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.sand      )) tBlock = BlocksGT.oreSmallSand; else
		if (Blocks.netherrack != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.netherrack)) tBlock = BlocksGT.oreSmallNetherrack; else
		if (Blocks.end_stone  != aBlock && aBlock.isReplaceableOreGen(aWorld, aX, aY, aZ, Blocks.end_stone )) tBlock = BlocksGT.oreSmallEndstone;
		}
		return tBlock != null && tBlock.placeBlock(aWorld, aX, aY, aZ, (byte)6, aID, null, F, T);
	}
	
	/** Removes Bedrock from that Position and replaces it with regular Stone of the region. */
	public static boolean removeBedrock(World aWorld, int aX, int aY, int aZ) {
		Block tBlock = aWorld.getBlock(aX, aY, aZ), tStone = (aWorld.provider.dimensionId == DIM_NETHER ? Blocks.netherrack : Blocks.stone);
		
		if (tBlock == NB || bedrock(tBlock)) {
			for (byte tSide : ALL_SIDES_BUT_BOTTOM) for (int i = 1; i < 7; i++) {
				tBlock = aWorld.getBlock(aX+OFFX[tSide]*i, aY+OFFY[tSide]*i, aZ+OFFZ[tSide]*i);
				if (tBlock != NB && tBlock != tStone && !bedrock(tBlock)) {
					int tMetaData = aWorld.getBlockMetadata(aX+OFFX[tSide]*i, aY+OFFY[tSide]*i, aZ+OFFZ[tSide]*i);
					if (BlocksGT.stoneToNormalOres.containsKey(new ItemStackContainer(tBlock, 1, tMetaData))) {
						return aWorld.setBlock(aX, aY, aZ, tBlock, tMetaData, 0);
					}
				}
			}
			return aWorld.setBlock(aX, aY, aZ, tStone, 0, 0);
		}
		return F;
	}
	
	public static List<ChunkCoordinates> line(final Vec3 aStart, final Vec3 aEnd) {
		List<ChunkCoordinates> rList = new ArrayListNoNulls<>();
		if (Double.isNaN(aStart.xCoord) || Double.isNaN(aStart.yCoord) || Double.isNaN(aStart.zCoord) || Double.isNaN(aEnd.xCoord) || Double.isNaN(aEnd.yCoord) || Double.isNaN(aEnd.zCoord)) return rList;
		Vec3 tPoint = Vec3.createVectorHelper(aStart.xCoord, aStart.yCoord, aStart.zCoord);
		
		int sx = UT.Code.roundDown(tPoint.xCoord);
		int sy = UT.Code.roundDown(tPoint.yCoord);
		int sz = UT.Code.roundDown(tPoint.zCoord);
		int ex = UT.Code.roundDown(aEnd.xCoord);
		int ey = UT.Code.roundDown(aEnd.yCoord);
		int ez = UT.Code.roundDown(aEnd.zCoord);
		
		rList.add(new ChunkCoordinates(sx, sy, sz));
		
		int maxAttempts = 2000; // Just to prevent accidental infinite loops
		
		while (maxAttempts-- >= 0) {
			if (Double.isNaN(tPoint.xCoord) || Double.isNaN(tPoint.yCoord) || Double.isNaN(tPoint.zCoord)) return rList;
			if (sx == ex && sy == ey && sz == ez) return rList;
			
			boolean performx = true;
			boolean performy = true;
			boolean performz = true;
			
			double nx = 999.0D;
			double ny = 999.0D;
			double nz = 999.0D;
			
			double ndx = 999.0D;
			double ndy = 999.0D;
			double ndz = 999.0D;
			
			double distx = aEnd.xCoord - tPoint.xCoord;
			double disty = aEnd.yCoord - tPoint.yCoord;
			double distz = aEnd.zCoord - tPoint.zCoord;
			
			if (ex > sx) {
				nx = (double) sx + 1.0D;
			} else if (ex < sx) {
				nx = (double) sx + 0.0D;
			} else {
				performx = false;
			}
			
			if (ey > sy) {
				ny = (double) sy + 1.0D;
			} else if (ey < sy) {
				ny = (double) sy + 0.0D;
			} else {
				performy = false;
			}
			
			if (ez > sz) {
				nz = (double) sz + 1.0D;
			} else if (ez < sz) {
				nz = (double) sz + 0.0D;
			} else {
				performz = false;
			}
			
			if (performx) {
				ndx = (nx - tPoint.xCoord) / distx;
			}
			
			if (performy) {
				ndy = (ny - tPoint.yCoord) / disty;
			}
			
			if (performz) {
				ndz = (nz - tPoint.zCoord) / distz;
			}
			
			byte whereTo;
			
			if (ndx < ndy && ndx < ndz) {
				if (ex > sx) whereTo = 4;
				else whereTo = 5;
				
				tPoint.xCoord = nx;
				tPoint.yCoord += disty * ndx;
				tPoint.zCoord += distz * ndx;
			} else if (ndy < ndz) {
				if (ey > sy) whereTo = 0;
				else whereTo = 1;
				
				tPoint.xCoord += distx * ndy;
				tPoint.yCoord = ny;
				tPoint.zCoord += distz * ndy;
			} else {
				if (ez > sz) whereTo = 2;
				else whereTo = 3;
				
				tPoint.xCoord += distx * ndz;
				tPoint.yCoord += disty * ndz;
				tPoint.zCoord = nz;
			}
			
			sx = UT.Code.roundDown(tPoint.xCoord);
			sy = UT.Code.roundDown(tPoint.yCoord);
			sz = UT.Code.roundDown(tPoint.zCoord);
			
			if (whereTo == 5) --sx;
			if (whereTo == 1) --sy;
			if (whereTo == 3) --sz;
			
			rList.add(new ChunkCoordinates(sx, sy, sz));
		}
		return rList;
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
			rList.add("Registry: " + ST.regName(aBlock));
			if (aScanLevel >= 10) {
				rList.add("Block Class: " + aBlock.getClass());
				if (aTileEntity != null) rList.add("TileEntity Class: " + aTileEntity.getClass());
			}
			float tResistance = aBlock.getExplosionResistance(aPlayer, aWorld, aX, aY, aZ, aPlayer.posX, aPlayer.posY, aPlayer.posZ);
			rList.add("Hardness: " + aBlock.getBlockHardness(aWorld, aX, aY, aZ) + " - " + LH.getToolTipBlastResistance(aBlock, tResistance));
			int tHarvestLevel = aBlock.getHarvestLevel(aMeta);
			String tHarvestTool = aBlock.getHarvestTool(aMeta);
			rList.add(tHarvestLevel == 0 && aBlock.getMaterial().isAdventureModeExempt() ? "Hand-Harvestable, but " + (Code.stringValid(tHarvestTool)?Code.capitalise(tHarvestTool):"None") + " is faster" : "Tool to Harvest: " + (Code.stringValid(tHarvestTool)?Code.capitalise(tHarvestTool):"None") + " (" + tHarvestLevel + ")");
			if (aBlock.isBeaconBase(aWorld, aX, aY, aZ, aX, aY+1, aZ)) rList.add("Is usable for Beacon Pyramids");
			if (MD.GC.mLoaded && aBlock instanceof IPartialSealableBlock) rList.add(((IPartialSealableBlock)aBlock).isSealed(aWorld, aX, aY, aZ, FORGE_DIR[aSide ^ 1]) ? "Is Sealable on this Side" : "Is not Sealable on this Side");
		} catch(Throwable e) {e.printStackTrace(ERR);}
		if (aTileEntity != null) {
			try {if (aTileEntity instanceof ITileEntityWeight && ((ITileEntityWeight)aTileEntity).getWeightValue(aSide) > 0) {
				rEUAmount+=V[3];
				rList.add("Weight: " + ((ITileEntityWeight)aTileEntity).getWeightValue(aSide) + " kg");
			}} catch(Throwable e) {e.printStackTrace(ERR);}
			try {if (aTileEntity instanceof ITileEntityTemperature && ((ITileEntityTemperature)aTileEntity).getTemperatureMax(aSide) > 0) {
				rEUAmount+=V[3];
				rList.add("Temperature: " + ((ITileEntityTemperature)aTileEntity).getTemperatureValue(aSide) + " / " + ((ITileEntityTemperature)aTileEntity).getTemperatureMax(aSide) + " K");
			}} catch(Throwable e) {e.printStackTrace(ERR);}
			try {if (aTileEntity instanceof ITileEntityGibbl && ((ITileEntityGibbl)aTileEntity).getGibblMax(aSide) > 0) {
				rEUAmount+=V[3];
				rList.add("Pressure: " + ((ITileEntityGibbl)aTileEntity).getGibblValue(aSide) + " / " + ((ITileEntityGibbl)aTileEntity).getGibblMax(aSide) + " Gibbl");
			}} catch(Throwable e) {e.printStackTrace(ERR);}
			try {if (aTileEntity instanceof ITileEntityProgress && ((ITileEntityProgress)aTileEntity).getProgressMax(aSide) > 0) {
				rEUAmount+=V[3];
				rList.add("Progress: " + ((ITileEntityProgress)aTileEntity).getProgressValue(aSide) + " / " + ((ITileEntityProgress)aTileEntity).getProgressMax(aSide));
			}} catch(Throwable e) {e.printStackTrace(ERR);}
			
			
			String rState = "";
			try {if (aTileEntity instanceof ITileEntitySwitchableOnOff) {
				if (Code.stringValid(rState)) rState += " --- ";
				rEUAmount+=V[3];
				rState += ("State: " + (((ITileEntitySwitchableOnOff)aTileEntity).getStateOnOff()?"ON":"OFF"));
			}} catch(Throwable e) {e.printStackTrace(ERR);}
			try {if (aTileEntity instanceof ITileEntitySwitchableMode) {
				if (Code.stringValid(rState)) rState += " --- ";
				rEUAmount+=V[3];
				rState += ("Mode: " + (((ITileEntitySwitchableMode)aTileEntity).getStateMode()));
			}} catch(Throwable e) {e.printStackTrace(ERR);}
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
			}} catch(Throwable e) {e.printStackTrace(ERR);}
			if (Code.stringValid(rState)) rList.add(rState);
			
			
			try {if (aTileEntity instanceof ITileEntityEnergy) {
				rEUAmount+=V[3];
				for (TagData tEnergyType : ((ITileEntityEnergy)aTileEntity).getEnergyTypes(aSide)) {
					rList.add("Input: " + ((ITileEntityEnergy)aTileEntity).getEnergySizeInputMin(tEnergyType, aSide) + " to " + ((ITileEntityEnergy)aTileEntity).getEnergySizeInputMax(tEnergyType, aSide) + tEnergyType.getLocalisedNameShort());
					rList.add("Output: " + ((ITileEntityEnergy)aTileEntity).getEnergySizeOutputMin(tEnergyType, aSide) + " to " + ((ITileEntityEnergy)aTileEntity).getEnergySizeOutputMax(tEnergyType, aSide) + tEnergyType.getLocalisedNameShort());
				}
			}} catch(Throwable e) {e.printStackTrace(ERR);}
			try {if (aTileEntity instanceof ITileEntityEnergyDataCapacitor) {
				rEUAmount+=V[3];
				for (TagData tEnergyType : ((ITileEntityEnergyDataCapacitor)aTileEntity).getEnergyCapacitorTypes(aSide)) {
					rList.add("Stored: " + ((ITileEntityEnergyDataCapacitor)aTileEntity).getEnergyStored(tEnergyType, aSide) + " of " + ((ITileEntityEnergyDataCapacitor)aTileEntity).getEnergyCapacity(tEnergyType, aSide) + tEnergyType.getLocalisedNameShort());
				}
			}} catch(Throwable e) {e.printStackTrace(ERR);}
			
			
			try {if (aTileEntity instanceof IFluidHandler) {
				rEUAmount+=V[3];
				FluidTankInfo[] tTanks = ((IFluidHandler)aTileEntity).getTankInfo(FORGE_DIR[aSide]);
				if (tTanks != null) for (byte i = 0; i < tTanks.length; i++) {
					rList.add("Tank " + i + ": " + (tTanks[i].fluid==null?0:tTanks[i].fluid.amount) + " / " + tTanks[i].capacity + " " + FL.name(tTanks[i].fluid, T));
				}
			}} catch(Throwable e) {e.printStackTrace(ERR);}
			
			if (!(aTileEntity instanceof ITileEntity)) {
				try {if (aTileEntity instanceof ic2.api.reactor.IReactorChamber) {
					rEUAmount+=V[4];
					aTileEntity = (TileEntity)(((ic2.api.reactor.IReactorChamber)aTileEntity).getReactor());
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {e.printStackTrace(ERR);}
				try {if (aTileEntity instanceof ic2.api.reactor.IReactor) {
					rEUAmount+=V[4];
					rList.add( "Heat: " + ((ic2.api.reactor.IReactor)aTileEntity).getHeat() + "/" + ((ic2.api.reactor.IReactor)aTileEntity).getMaxHeat()
							+ "  HEM: " + ((ic2.api.reactor.IReactor)aTileEntity).getHeatEffectModifier() + "  Base IC2-EU Output: " + ((ic2.api.reactor.IReactor)aTileEntity).getReactorEUEnergyOutput());
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {e.printStackTrace(ERR);}
				try {if (aTileEntity instanceof ic2.api.tile.IWrenchable) {
					rEUAmount+=V[3];
					rList.add("Facing: " + ((ic2.api.tile.IWrenchable)aTileEntity).getFacing() + " / IC2 Wrench Drop Chance: " + (((ic2.api.tile.IWrenchable)aTileEntity).wrenchCanRemove(aPlayer)?(((ic2.api.tile.IWrenchable)aTileEntity).getWrenchDropRate()*100):0) + "%");
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {e.printStackTrace(ERR);}
				try {if (aTileEntity instanceof ic2.api.energy.tile.IEnergySink) {
					rEUAmount+=V[3];
					rList.add("Demanded Energy: " + ((ic2.api.energy.tile.IEnergySink)aTileEntity).getDemandedEnergy() + " IC2-EU");
					rList.add("Max Safe Input: " + V[((ic2.api.energy.tile.IEnergySink)aTileEntity).getSinkTier()] + " IC2-EU/t");
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {e.printStackTrace(ERR);}
				try {if (aTileEntity instanceof ic2.api.energy.tile.IEnergySource) {
					rEUAmount+=V[3];
					rList.add("Max Energy Output: " + V[((ic2.api.energy.tile.IEnergySource)aTileEntity).getSourceTier()] + " IC2-EU/t");
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {e.printStackTrace(ERR);}
				try {if (aTileEntity instanceof ic2.api.energy.tile.IEnergyConductor) {
					rEUAmount+=V[3];
					rList.add("Conduction Loss: " + ((ic2.api.energy.tile.IEnergyConductor)aTileEntity).getConductionLoss() + " IC2-EU/m");
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {e.printStackTrace(ERR);}
				try {if (aTileEntity instanceof ic2.api.tile.IEnergyStorage) {
					rEUAmount+=V[3];
					rList.add("Contained Energy: " + ((ic2.api.tile.IEnergyStorage)aTileEntity).getStored() + " of " + ((ic2.api.tile.IEnergyStorage)aTileEntity).getCapacity() + " IC2-EU");
					rList.add(((ic2.api.tile.IEnergyStorage)aTileEntity).isTeleporterCompatible(FORGE_DIR[aSide])?"Teleporter Compatible":"Not Teleporter Compatible");
				}} catch(NoClassDefFoundError e) {/* ignore */} catch(Throwable e) {e.printStackTrace(ERR);}
			}
		}
		try {if (aBlock instanceof IBlockDebugable) {
			rEUAmount+=V[3];
			ArrayList<String> temp = ((IBlockDebugable)aBlock).getDebugInfo(aPlayer, aX, aY, aZ, aScanLevel);
			if (temp != null) rList.addAll(temp);
		}} catch(Throwable e) {e.printStackTrace(ERR);}
		
		BlockScanningEvent tEvent = new BlockScanningEvent(aWorld, aPlayer, aX, aY, aZ, aSide, aScanLevel, aBlock, aTileEntity, rList, aClickX, aClickY, aClickZ);
		tEvent.mEUCost = rEUAmount;
		MinecraftForge.EVENT_BUS.post(tEvent);
		if (!tEvent.isCanceled()) aList.addAll(rList);
		return tEvent.mEUCost;
	}
}
