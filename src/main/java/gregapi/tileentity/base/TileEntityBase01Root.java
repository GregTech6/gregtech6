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

package gregapi.tileentity.base;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import appeng.api.movable.IMovableTile;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetLightValue;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_IsProvidingStrongPower;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.TagData;
import gregapi.data.CS.ModIDs;
import gregapi.data.CS.SFX;
import gregapi.data.FL;
import gregapi.data.TD;
import gregapi.gui.ContainerCommon;
import gregapi.gui.Slot_Base;
import gregapi.network.packets.PacketBlockError;
import gregapi.network.packets.PacketBlockEvent;
import gregapi.random.ExplosionGT;
import gregapi.render.IRenderedBlockObject;
import gregapi.render.IRenderedBlockObject.ErrorRenderer;
import gregapi.render.RenderHelper;
import gregapi.tileentity.ITileEntity;
import gregapi.tileentity.ITileEntityGUI;
import gregapi.tileentity.data.ITileEntitySurface;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.delegate.ITileEntityCanDelegate;
import gregapi.tileentity.delegate.ITileEntityDelegating;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 * 
 * The Functions all TileEntities should have.
 */
@Optional.InterfaceList(value = {
  @Optional.Interface(iface = "appeng.api.movable.IMovableTile", modid = ModIDs.AE)
})
public abstract class TileEntityBase01Root extends TileEntity implements ITileEntity, ITileEntityGUI, IMovableTile {
	/** If this TileEntity checks for the Chunk to be loaded before returning World based values. If this is set to T, this TileEntity will not cause worfin' Chunks, uhh I mean orphan Chunks. */
	public boolean mIgnoreUnloadedChunks = T;
	
	/** This Variable checks if this TileEntity is dead, because Minecraft is too stupid to have proper TileEntity unloading. */
	public boolean mIsDead = F;
	
	/** This Variable checks if this TileEntity should refresh when the Block is being set. That way you can turn this check off any time you need it. */
	public boolean mShouldRefresh = T;
	
	/** This Variable is for a buffered Block Update. */
	public boolean mDoesBlockUpdate = F;
	
	/** This Variable is for the IC2 E-net. */
	public boolean mIsAddedToEnet = F, mDoEnetCheck = T;
	
	/** This Variable is for forcing the Selection Box to be full. */
	public boolean FORCE_FULL_SELECTION_BOXES = F;
	
	/** If this TileEntity is ticking at all */
	public final boolean mIsTicking;
	
	private final ChunkCoordinates mReturnedCoordinates = new ChunkCoordinates();
	
	public TileEntityBase01Root(boolean aIsTicking) {
		mIsTicking = aIsTicking;
	}
	
	@Override
	public void onTileEntityPlaced() {
		//
	}
	
	@Override
	public void onAdjacentBlockChange(int aTileX, int aTileY, int aTileZ) {
		//
	}
	
	@Override public void markDirty() {/* Oh no, I won't let this do anything anymore! It's only useful for Comparators and that didn't work properly anyways! */}
	@Override public World getWorld() {return worldObj;}
	@Override public int getX() {return xCoord;}
	@Override public int getY() {return yCoord;}
	@Override public int getZ() {return zCoord;}
	@Override public int getOffsetX (byte aSide) {return xCoord + OFFSETS_X[aSide];}
	@Override public int getOffsetY (byte aSide) {return yCoord + OFFSETS_Y[aSide];}
	@Override public int getOffsetZ (byte aSide) {return zCoord + OFFSETS_Z[aSide];}
	@Override public int getOffsetX (byte aSide, int aMultiplier) {return xCoord + OFFSETS_X[aSide] * aMultiplier;}
	@Override public int getOffsetY (byte aSide, int aMultiplier) {return yCoord + OFFSETS_Y[aSide] * aMultiplier;}
	@Override public int getOffsetZ (byte aSide, int aMultiplier) {return zCoord + OFFSETS_Z[aSide] * aMultiplier;}
	@Override public int getOffsetXN(byte aSide) {return xCoord - OFFSETS_X[aSide];}
	@Override public int getOffsetYN(byte aSide) {return yCoord - OFFSETS_Y[aSide];}
	@Override public int getOffsetZN(byte aSide) {return zCoord - OFFSETS_Z[aSide];}
	@Override public int getOffsetXN(byte aSide, int aMultiplier) {return xCoord - OFFSETS_X[aSide] * aMultiplier;}
	@Override public int getOffsetYN(byte aSide, int aMultiplier) {return yCoord - OFFSETS_Y[aSide] * aMultiplier;}
	@Override public int getOffsetZN(byte aSide, int aMultiplier) {return zCoord - OFFSETS_Z[aSide] * aMultiplier;}
	@Override public ChunkCoordinates getCoords() {mReturnedCoordinates.posX = xCoord; mReturnedCoordinates.posY = yCoord; mReturnedCoordinates.posZ = zCoord; return mReturnedCoordinates;}
	@Override public ChunkCoordinates getOffset (byte aSide, int aMultiplier) {return new ChunkCoordinates(getOffsetX (aSide, aMultiplier), getOffsetY (aSide, aMultiplier), getOffsetZ (aSide, aMultiplier));}
	@Override public ChunkCoordinates getOffsetN(byte aSide, int aMultiplier) {return new ChunkCoordinates(getOffsetXN(aSide, aMultiplier), getOffsetYN(aSide, aMultiplier), getOffsetZN(aSide, aMultiplier));}
	@Override public boolean isServerSide() {return worldObj == null ? cpw.mods.fml.common.FMLCommonHandler.instance().getEffectiveSide().isServer() : !worldObj.isRemote;}
	@Override public boolean isClientSide() {return worldObj == null ? cpw.mods.fml.common.FMLCommonHandler.instance().getEffectiveSide().isClient() :  worldObj.isRemote;}
	@Override public boolean openGUI(EntityPlayer aPlayer) {return openGUI(aPlayer, 0);}
	@Override public boolean openGUI(EntityPlayer aPlayer, int aID) {if (aPlayer == null) return F; aPlayer.openGui(GAPI, aID, worldObj, xCoord, yCoord, zCoord); return T;}
	@Override public int getRandomNumber(int aRange) {return RNGSUS.nextInt(aRange);}
	@Override public int rng(int aRange) {return RNGSUS.nextInt(aRange);}
	public boolean rng() {return RNGSUS.nextBoolean();}
	@Override public BiomeGenBase getBiome(ChunkCoordinates aCoords) {return worldObj==null?BiomeGenBase.plains:worldObj.getBiomeGenForCoords(aCoords.posX, aCoords.posZ);}
	@Override public BiomeGenBase getBiome(int aX, int aZ) {return worldObj==null?BiomeGenBase.plains:worldObj.getBiomeGenForCoords(aX, aZ);}
	@Override public BiomeGenBase getBiome() {return getBiome(xCoord, zCoord);}
	@Override public Block getBlockOffset(int aX, int aY, int aZ) {return getBlock(xCoord+aX, yCoord+aY, zCoord+aZ);}
	@Override public Block getBlockAtSide(byte aSide) {return getBlockAtSideAndDistance(aSide, 1);}
	@Override public Block getBlockAtSideAndDistance(byte aSide, int aDistance) {return getBlock(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public byte getMetaDataOffset(int aX, int aY, int aZ) {return getMetaData(xCoord+aX, yCoord+aY, zCoord+aZ);}
	@Override public byte getMetaDataAtSide(byte aSide) {return getMetaDataAtSideAndDistance(aSide, 1);}
	@Override public byte getMetaDataAtSideAndDistance(byte aSide, int aDistance) {return getMetaData(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public byte getLightLevelOffset(int aX, int aY, int aZ) {return getLightLevel(xCoord+aX, yCoord+aY, zCoord+aZ);}
	@Override public byte getLightLevelAtSide(byte aSide) {return getLightLevelAtSideAndDistance(aSide, 1);}
	@Override public byte getLightLevelAtSideAndDistance(byte aSide, int aDistance) {return getLightLevel(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public boolean getOpacityOffset(int aX, int aY, int aZ) {return getOpacity(xCoord+aX, yCoord+aY, zCoord+aZ);}
	@Override public boolean getOpacityAtSide(byte aSide) {return getOpacityAtSideAndDistance(aSide, 1);}
	@Override public boolean getOpacityAtSideAndDistance(byte aSide, int aDistance) {return getOpacity(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public boolean getRainOffset(int aX, int aY, int aZ) {return getRain(xCoord+aX, yCoord+aY, zCoord+aZ);}
	@Override public boolean getRainAtSide(byte aSide) {return getRainAtSideAndDistance(aSide, 1);}
	@Override public boolean getRainAtSideAndDistance(byte aSide, int aDistance) {return getRain(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public boolean getSkyOffset(int aX, int aY, int aZ) {return getSky(xCoord+aX, yCoord+aY, zCoord+aZ);}
	@Override public boolean getSkyAtSide(byte aSide) {return getSkyAtSideAndDistance(aSide, 1);}
	@Override public boolean getSkyAtSideAndDistance(byte aSide, int aDistance) {return getSky(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public boolean getAirOffset(int aX, int aY, int aZ) {return getAir(xCoord+aX, yCoord+aY, zCoord+aZ);}
	@Override public boolean getAirAtSide(byte aSide) {return getAirAtSideAndDistance(aSide, 1);}
	@Override public boolean getAirAtSideAndDistance(byte aSide, int aDistance) {return getAir(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public TileEntity getTileEntityOffset(int aX, int aY, int aZ) {return getTileEntity(xCoord+aX, yCoord+aY, zCoord+aZ);}
	@Override public TileEntity getTileEntityAtSideAndDistance(byte aSide, int aDistance) {return getTileEntity(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public DelegatorTileEntity<TileEntity         > getAdjacentTileEntity     (byte aSide) {return getAdjacentTileEntity(aSide, T, F);}
	@Override public DelegatorTileEntity<IInventory         > getAdjacentInventory      (byte aSide) {return getAdjacentInventory(aSide, T, F);}
	@Override public DelegatorTileEntity<ISidedInventory    > getAdjacentSidedInventory (byte aSide) {return getAdjacentSidedInventory(aSide, T, F);}
	@Override public DelegatorTileEntity<IFluidHandler      > getAdjacentTank           (byte aSide) {return getAdjacentTank(aSide, T, F);}
	@Override public DelegatorTileEntity<IInventory         > getAdjacentInventory      (byte aSide, boolean aAllowDelegates, boolean aNotConnectToDelegators) {DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(aSide, aAllowDelegates, aNotConnectToDelegators); return new DelegatorTileEntity<>(tDelegator.mTileEntity instanceof IInventory      ?(IInventory        )tDelegator.mTileEntity:null, tDelegator);}
	@Override public DelegatorTileEntity<ISidedInventory    > getAdjacentSidedInventory (byte aSide, boolean aAllowDelegates, boolean aNotConnectToDelegators) {DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(aSide, aAllowDelegates, aNotConnectToDelegators); return new DelegatorTileEntity<>(tDelegator.mTileEntity instanceof ISidedInventory ?(ISidedInventory   )tDelegator.mTileEntity:null, tDelegator);}
	@Override public DelegatorTileEntity<IFluidHandler      > getAdjacentTank           (byte aSide, boolean aAllowDelegates, boolean aNotConnectToDelegators) {DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(aSide, aAllowDelegates, aNotConnectToDelegators); return new DelegatorTileEntity<>(tDelegator.mTileEntity instanceof IFluidHandler   ?(IFluidHandler     )tDelegator.mTileEntity:null, tDelegator);}
	
	@Override
	public DelegatorTileEntity<TileEntity> getAdjacentTileEntity(byte aSide, boolean aAllowDelegates, boolean aNotConnectToDelegators) {
		TileEntity tTileEntity = getTileEntityAtSideAndDistance(aSide, 1);
		if (tTileEntity == null) return new DelegatorTileEntity<>(null, worldObj, getOffsetX(aSide), getOffsetY(aSide), getOffsetZ(aSide), OPPOSITES[aSide]);
		if (aNotConnectToDelegators && tTileEntity instanceof ITileEntityCanDelegate && ((ITileEntityCanDelegate)tTileEntity).isExtender(aSide)) return new DelegatorTileEntity<>(null, worldObj, getOffsetX(aSide), getOffsetY(aSide), getOffsetZ(aSide), OPPOSITES[aSide]);
		if (aAllowDelegates && tTileEntity instanceof ITileEntityDelegating) return ((ITileEntityDelegating)tTileEntity).getDelegateTileEntity(OPPOSITES[aSide]);
		return new DelegatorTileEntity<>(tTileEntity, tTileEntity.getWorldObj(), tTileEntity.xCoord, tTileEntity.yCoord, tTileEntity.zCoord, OPPOSITES[aSide]);
	}
	
	public List<DelegatorTileEntity<TileEntity>> allAdjacentTileEntities(boolean aAllowDelegates, boolean aNotConnectToDelegators) {
		ArrayListNoNulls<DelegatorTileEntity<TileEntity>> rDelegates = new ArrayListNoNulls<>();
		for (byte tSide : ALL_SIDES_VALID) {
			DelegatorTileEntity<TileEntity> tDelegate = getAdjacentTileEntity(tSide, aAllowDelegates, aNotConnectToDelegators);
			if (tDelegate.mTileEntity != null) rDelegates.add(tDelegate);
		}
		return rDelegates;
	}
	
	@Override
	public Block getBlock(int aX, int aY, int aZ) {
		if (worldObj == null) return NB;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !worldObj.blockExists(aX, aY, aZ)) return NB;
		return worldObj.getBlock(aX, aY, aZ);
	}
	
	@Override
	public byte getMetaData(int aX, int aY, int aZ) {
		if (worldObj == null) return 0;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !worldObj.blockExists(aX, aY, aZ)) return 0;
		return UT.Code.bind4(worldObj.getBlockMetadata(aX, aY, aZ));
	}
	
	@Override
	public byte getLightLevel(int aX, int aY, int aZ) {
		if (worldObj == null) return 14;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !worldObj.blockExists(aX, aY, aZ)) return 0;
		return UT.Code.bind4((long)worldObj.getLightBrightness(aX, aY, aZ)*15);
	}
	
	@Override
	public boolean getSky(int aX, int aY, int aZ) {
		if (worldObj == null) return T;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !worldObj.blockExists(aX, aY, aZ)) return T;
		return worldObj.canBlockSeeTheSky(aX, aY, aZ);
	}
	
	@Override
	public boolean getRain(int aX, int aY, int aZ) {
		if (worldObj == null) return T;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !worldObj.blockExists(aX, aY, aZ)) return T;
		return worldObj.getPrecipitationHeight(aX, aZ) <= aY;
	}
	
	@Override
	public boolean getOpacity(int aX, int aY, int aZ) {
		if (worldObj == null) return F;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !worldObj.blockExists(aX, aY, aZ)) return F;
		return worldObj.getBlock(aX, aY, aZ).isOpaqueCube();
	}
	
	@Override
	public boolean getAir(int aX, int aY, int aZ) {
		if (worldObj == null) return T;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !worldObj.blockExists(aX, aY, aZ)) return T;
		return worldObj.getBlock(aX, aY, aZ).isAir(worldObj, aX, aY, aZ);
	}
	
	@Override
	public TileEntity getTileEntity(int aX, int aY, int aZ) {
		if (worldObj == null) return null;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aX, aZ) && !worldObj.blockExists(aX, aY, aZ)) return null;
		return WD.te(worldObj, aX, aY, aZ, T);
	}
	
	@Override
	public Block getBlock(ChunkCoordinates aCoords) {
		if (worldObj == null) return NB;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aCoords) && !worldObj.blockExists(aCoords.posX, aCoords.posY, aCoords.posZ)) return NB;
		return worldObj.getBlock(aCoords.posX, aCoords.posY, aCoords.posZ);
	}
	
	@Override
	public byte getMetaData(ChunkCoordinates aCoords) {
		if (worldObj == null) return 0;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aCoords) && !worldObj.blockExists(aCoords.posX, aCoords.posY, aCoords.posZ)) return 0;
		return (byte)worldObj.getBlockMetadata(aCoords.posX, aCoords.posY, aCoords.posZ);
	}
	
	@Override
	public byte getLightLevel(ChunkCoordinates aCoords) {
		if (worldObj == null) return 14;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aCoords) && !worldObj.blockExists(aCoords.posX, aCoords.posY, aCoords.posZ)) return 0;
		return UT.Code.bind4((long)worldObj.getLightBrightness(aCoords.posX, aCoords.posY, aCoords.posZ)*15);
	}
	
	@Override
	public boolean getSky(ChunkCoordinates aCoords) {
		if (worldObj == null) return T;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aCoords) && !worldObj.blockExists(aCoords.posX, aCoords.posY, aCoords.posZ)) return T;
		return worldObj.canBlockSeeTheSky(aCoords.posX, aCoords.posY, aCoords.posZ);
	}
	
	@Override
	public boolean getRain(ChunkCoordinates aCoords) {
		if (worldObj == null) return T;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aCoords) && !worldObj.blockExists(aCoords.posX, aCoords.posY, aCoords.posZ)) return T;
		return worldObj.getPrecipitationHeight(aCoords.posX, aCoords.posZ) <= aCoords.posY;
	}
	
	@Override
	public boolean getOpacity(ChunkCoordinates aCoords) {
		if (worldObj == null) return F;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aCoords) && !worldObj.blockExists(aCoords.posX, aCoords.posY, aCoords.posZ)) return F;
		return worldObj.getBlock(aCoords.posX, aCoords.posY, aCoords.posZ).isOpaqueCube();
	}
	
	@Override
	public boolean getAir(ChunkCoordinates aCoords) {
		if (worldObj == null) return T;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aCoords) && !worldObj.blockExists(aCoords.posX, aCoords.posY, aCoords.posZ)) return T;
		return worldObj.getBlock(aCoords.posX, aCoords.posY, aCoords.posZ).isAir(worldObj, aCoords.posX, aCoords.posY, aCoords.posZ);
	}
	
	@Override
	public TileEntity getTileEntity(ChunkCoordinates aCoords) {
		if (worldObj == null) return null;
		if (mIgnoreUnloadedChunks && crossedChunkBorder(aCoords) && !worldObj.blockExists(aCoords.posX, aCoords.posY, aCoords.posZ)) return null;
		return WD.te(worldObj, aCoords, T);
	}
	
	public DelegatorTileEntity<TileEntity> delegator(byte aSide) {
		return new DelegatorTileEntity<TileEntity>(this, aSide);
	}
	
	@Override
	public void sendBlockEvent(byte aID, byte aValue) {
		NW_API.sendToAllPlayersInRange(new PacketBlockEvent(getCoords(), aID, aValue), worldObj, getCoords());
	}
	
	@Override
	public boolean isDead() {
		return mIsDead;
	}
	
	@Override
	public void invalidate() {
		super.invalidate();
		setDead();
	}
	
	@Override
	public void validate() {
		super.validate();
		setAlive();
	}
	
	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
		setDead();
	}
	
	@Optional.Method(modid = ModIDs.IC2)
	private void loadIntoEnet() {
		if (this instanceof IEnergyTile && (isEnergyType(TD.Energy.EU, SIDE_ANY, T) || isEnergyType(TD.Energy.EU, SIDE_ANY, F))) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent((IEnergyTile)this));
			mIsAddedToEnet = T;
		} else {
			mDoEnetCheck = F;
		}
	}
	
	@Optional.Method(modid = ModIDs.IC2)
	private void unloadFromEnet() {
		MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent((IEnergyTile)this));
		mIsAddedToEnet = F;
	}
	
	protected void doEnetUpdate() {
		if (isServerSide() && mIsAddedToEnet && mDoEnetCheck) try {unloadFromEnet(); loadIntoEnet();} catch(Throwable e) {mDoEnetCheck = F;}
	}
	
	protected void setDead() {
		if (!mIsDead) {
			mIsDead = T;
			if (isServerSide() && mIsAddedToEnet) try {unloadFromEnet();} catch(Throwable e) {mDoEnetCheck = F;}
		}
	}
	
	protected void setAlive() {
		mIsDead = F;
	}
	
	@Override
	public void updateEntity() {
		// Well, if the TileEntity gets ticked, it is alive.
		if (isDead()) setAlive();
		
		if (isServerSide() && !mIsAddedToEnet && mDoEnetCheck) try {loadIntoEnet();} catch(Throwable e) {mDoEnetCheck = F;}
		
		if (mExplosionStrength > 0) {
			setToAir();
			if (mExplosionStrength < 1) {
				UT.Sounds.send(worldObj, SFX.MC_EXPLODE, 1, 1, getCoords());
			} else {
				ExplosionGT.explode(worldObj, null, xCoord, yCoord, zCoord, mExplosionStrength, F, T);
			}
			setDead();
			return;
		}
		
		if (mDoesBlockUpdate) doBlockUpdate();
	}
	
	@Override
	public long getTimer() {
		return 0;
	}
	
	@Override
	public boolean canUpdate() {
		return mIsTicking && mShouldRefresh;
	}
	
	@Override
	public boolean shouldRefresh(Block aOldBlock, Block aNewBlock, int aOldMeta, int aNewMeta, World aWorld, int aX, int aY, int aZ) {
		return mShouldRefresh || aOldBlock != aNewBlock;
	}
	
	/** Simple Function to prevent Block Updates from happening multiple times within the same Tick. */
	public final void causeBlockUpdate() {
		if (mIsTicking) mDoesBlockUpdate = T; else doBlockUpdate();
	}
	
	public void doBlockUpdate() {
		Block tBlock = getBlock(getCoords());
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, tBlock);
		if (this instanceof IMTE_IsProvidingStrongPower) for (byte tSide : ALL_SIDES_VALID) {
			if (getBlockAtSide(tSide).isNormalCube(worldObj, xCoord+OFFSETS_X[tSide], yCoord+OFFSETS_Y[tSide], zCoord+OFFSETS_Z[tSide])) {
				worldObj.notifyBlocksOfNeighborChange(xCoord+OFFSETS_X[tSide], yCoord+OFFSETS_Y[tSide], zCoord+OFFSETS_Z[tSide], tBlock, OPPOSITES[tSide]);
			}
		}
		mDoesBlockUpdate = F;
	}
	
	public final boolean crossedChunkBorder(int aX, int aZ) {
		return aX >> 4 != xCoord >> 4 || aZ >> 4 != zCoord >> 4;
	}
	
	public final boolean crossedChunkBorder(ChunkCoordinates aCoords) {
		return aCoords.posX >> 4 != xCoord >> 4 || aCoords.posZ >> 4 != zCoord >> 4;
	}
	
	public float mExplosionStrength = 0;
	
	public void explode() {
		// Seems to be a reasonable Default Explosion. This Function can of course be overridden.
		explode(4);
	}
	
	public void explode(double aStrength) {
		mExplosionStrength = (float)Math.max(aStrength, mExplosionStrength);
		if (!mIsTicking) {
			setToAir();
			if (mExplosionStrength < 1) {
				UT.Sounds.send(worldObj, SFX.MC_EXPLODE, 1, 1, getCoords());
			} else {
				ExplosionGT.explode(worldObj, null, xCoord, yCoord, zCoord, mExplosionStrength, F, T);
			}
		}
	}
	
	public void overcharge(long aVoltage, TagData aEnergyType) {
		// Only explode if allowed
		if (OVERCHARGE_EXPLOSIONS) {
			if (TD.Energy.ALL_EXPLODING.contains(aEnergyType)) {
				explode(UT.Code.tierMax(aVoltage));
			} else {
				explode(0.1);
			}
		} else if (OVERCHARGE_BREAKING) {
			explode(0.1);
		}
		// Yes, I will annoy people with that a lot, even when they disable Explosions.
		UT.Sounds.send(worldObj, TD.Energy.ALL_ELECTRIC.contains(aEnergyType)?SFX.IC_MACHINE_OVERLOAD:TD.Energy.ALL_KINETIC.contains(aEnergyType)?SFX.IC_MACHINE_INTERRUPT:SFX.MC_EXPLODE, 1, 1, getCoords());
		// The Noise should make the position obvious.
		DEB.println("Machine overcharged with: " + aVoltage + " " + aEnergyType.getLocalisedNameLong());
	}
	
	public float getExplosionResistance(Entity aExploder, double aExplosionX, double aExplosionY, double aExplosionZ) {return mExplosionStrength > 0 ? 0 : getExplosionResistance2(aExploder, aExplosionX, aExplosionY, aExplosionZ);}
	public float getExplosionResistance() {return mExplosionStrength > 0 ? 0 : getExplosionResistance2();}
	
	public float getExplosionResistance2(Entity aExploder, double aExplosionX, double aExplosionY, double aExplosionZ) {return getExplosionResistance2();}
	public float getExplosionResistance2() {return 0;}
	
	@SideOnly(Side.CLIENT)
	@Override public Object getGUIClient(int aGUIID, EntityPlayer aPlayer) {return null;}
	@Override public Object getGUIServer(int aGUIID, EntityPlayer aPlayer) {return null;}
	
	public boolean interceptClick(int aGUIID, Slot_Base aSlot, int aSlotIndex, int aInvSlot, EntityPlayer aPlayer, boolean aShiftclick, boolean aRightclick, int aMouse, int aShift) {return F;}
	public ItemStack slotClick(int aGUIID, Slot_Base aSlot, int aSlotIndex, int aInvSlot, EntityPlayer aPlayer, boolean aShiftclick, boolean aRightclick, int aMouse, int aShift) {return null;}
	public void killGUIs() {for (Object tPlayer : worldObj.playerEntities) if (tPlayer instanceof EntityPlayer && ((EntityPlayer)tPlayer).openContainer instanceof ContainerCommon && ((ContainerCommon)((EntityPlayer)tPlayer).openContainer).mTileEntity == this) ((EntityPlayer)tPlayer).closeScreen();}
	public void rebootGUIs(int aGUIID) {for (Object tPlayer : worldObj.playerEntities) if (tPlayer instanceof EntityPlayer && ((EntityPlayer)tPlayer).openContainer instanceof ContainerCommon && ((ContainerCommon)((EntityPlayer)tPlayer).openContainer).mTileEntity == this) {((EntityPlayer)tPlayer).closeScreen(); openGUI((EntityPlayer)tPlayer, aGUIID);}}
	public long getOpenGUIs() {long rGUIs = 0; for (Object tPlayer : worldObj.playerEntities) if (tPlayer instanceof EntityPlayer && ((EntityPlayer)tPlayer).openContainer instanceof ContainerCommon && ((ContainerCommon)((EntityPlayer)tPlayer).openContainer).mTileEntity == this) rGUIs++; return rGUIs;}
	
	public boolean shouldSideBeRendered(byte aSide) {
		TileEntity tTileEntity = getTileEntityAtSideAndDistance(aSide, 1);
		return tTileEntity instanceof ITileEntitySurface ? !((ITileEntitySurface)tTileEntity).isSurfaceOpaque(OPPOSITES[aSide]) : !WD.visOpq(worldObj, getOffsetX(aSide), getOffsetY(aSide), getOffsetZ(aSide), SIDES_VERTICAL[aSide] || WD.border(xCoord, zCoord, getOffsetX(aSide), getOffsetZ(aSide)), F);
	}
	
	@SideOnly(Side.CLIENT) public boolean renderItem(Block aBlock, RenderBlocks aRenderer) {return F;}
	@SideOnly(Side.CLIENT) public boolean renderBlock(Block aBlock, RenderBlocks aRenderer, IBlockAccess aWorld, int aX, int aY, int aZ) {return F;}
	@SideOnly(Side.CLIENT) public boolean usesRenderPass(int aRenderPass, boolean[] aShouldSideBeRendered) {return T;}
	@SideOnly(Side.CLIENT) public boolean renderFullBlockSide(Block aBlock, RenderBlocks aRenderer, byte aSide) {return shouldSideBeRendered(aSide);}
	@SideOnly(Side.CLIENT) public final IRenderedBlockObject passRenderingToObject(ItemStack aStack) {return ERROR_MESSAGE == null ? passRenderingToObject2(aStack) : ErrorRenderer.INSTANCE;}
	@SideOnly(Side.CLIENT) public final IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ) {return ERROR_MESSAGE == null ? passRenderingToObject2(aWorld, aX, aY, aZ) : ErrorRenderer.INSTANCE;}
	@SideOnly(Side.CLIENT) public IRenderedBlockObject passRenderingToObject2(ItemStack aStack) {return (IRenderedBlockObject)this;}
	@SideOnly(Side.CLIENT) public IRenderedBlockObject passRenderingToObject2(IBlockAccess aWorld, int aX, int aY, int aZ) {return (IRenderedBlockObject)this;}
	
	protected void updateInventory() {/**/}
	
	public void playClick() {UT.Sounds.send(worldObj, SFX.MC_CLICK, 1, 1, getCoords());}
	public void playCollect() {UT.Sounds.send(worldObj, SFX.MC_COLLECT, 0.2F, ((RNGSUS.nextFloat() - RNGSUS.nextFloat()) * 0.7F + 1) * 2, getCoords());}
	
	public void updateLightValue() {
		if (this instanceof IMTE_GetLightValue) {
			worldObj.setLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord, ((IMTE_GetLightValue)this).getLightValue());
			for (byte tSide : ALL_SIDES_MIDDLE) worldObj.updateLightByType(EnumSkyBlock.Block, xCoord+OFFSETS_X[tSide], yCoord+OFFSETS_Y[tSide], zCoord+OFFSETS_Z[tSide]);
		}
	}
	
	@Override
	public boolean hasRedstoneIncoming() {
		for (byte tSide : ALL_SIDES_VALID) if (getRedstoneIncoming(tSide) > 0) return T;
		return F;
	}
	
	@Override
	public byte getRedstoneIncoming(byte aSide) {
		if (worldObj == null) return 0;
		if (SIDES_INVALID[aSide]) {
			byte rRedstone = 0;
			for (byte tSide : ALL_SIDES_VALID) {
				rRedstone = (byte)Math.max(rRedstone, worldObj.getIndirectPowerLevelTo(getOffsetX(tSide), getOffsetY(tSide), getOffsetZ(tSide), tSide));
				if (rRedstone >= 15) return 15;
			}
			return rRedstone;
		}
		return UT.Code.bind4(worldObj.getIndirectPowerLevelTo(getOffsetX(aSide), getOffsetY(aSide), getOffsetZ(aSide), aSide));
	}
	
	@Override
	public byte getComparatorIncoming(byte aSide) {
		if (worldObj == null) return 0;
		Block tBlock = getBlockAtSide(aSide);
		return tBlock.hasComparatorInputOverride()?UT.Code.bind4(tBlock.getComparatorInputOverride(worldObj, getOffsetX(aSide), getOffsetY(aSide), getOffsetZ(aSide), OPPOSITES[aSide])):getRedstoneIncoming(aSide);
	}
	
	// A Default implementation of the Fluid Tank behaviour, so that every TileEntity can use this to simplify its Code.
	
	protected IFluidTank getFluidTankFillable(byte aSide, FluidStack aFluidToFill) {return null;}
	protected IFluidTank getFluidTankDrainable(byte aSide, FluidStack aFluidToDrain) {return null;}
	protected IFluidTank[] getFluidTanks(byte aSide) {return ZL_FT;}
	
	public int fill(ForgeDirection aDirection, FluidStack aFluid, boolean aDoFill) {
		if (aFluid == null || aFluid.amount <= 0) return 0;
		IFluidTank tTank = getFluidTankFillable(UT.Code.side(aDirection), aFluid);
		if (tTank == null) return 0;
		int rFilledAmount = tTank.fill(aFluid, aDoFill);
		if (rFilledAmount > 0 && aDoFill) updateInventory();
		return rFilledAmount;
	}
	
	public FluidStack drain(ForgeDirection aDirection, FluidStack aFluid, boolean aDoDrain) {
		if (aFluid == null || aFluid.amount <= 0) return null;
		IFluidTank tTank = getFluidTankDrainable(UT.Code.side(aDirection), aFluid);
		if (tTank == null || tTank.getFluid() == null || tTank.getFluidAmount() == 0 || !tTank.getFluid().isFluidEqual(aFluid)) return null;
		FluidStack rDrained = tTank.drain(aFluid.amount, aDoDrain);
		if (rDrained != null && aDoDrain) updateInventory();
		return rDrained;
	}
	
	public FluidStack drain(ForgeDirection aDirection, int aAmountToDrain, boolean aDoDrain) {
		if (aAmountToDrain <= 0) return null;
		IFluidTank tTank = getFluidTankDrainable(UT.Code.side(aDirection), null);
		if (tTank == null || tTank.getFluid() == null || tTank.getFluidAmount() == 0) return null;
		FluidStack rDrained = tTank.drain(aAmountToDrain, aDoDrain);
		if (rDrained != null && aDoDrain) updateInventory();
		return rDrained;
	}
	
	public boolean canFill(ForgeDirection aDirection, Fluid aFluid) {
		if (aFluid == null) return F;
		IFluidTank tTank = getFluidTankFillable(UT.Code.side(aDirection), FL.make(aFluid, 0));
		return tTank != null && (tTank.getFluid() == null || tTank.getFluid().getFluid() == aFluid);
	}
	
	public boolean canDrain(ForgeDirection aDirection, Fluid aFluid) {
		if (aFluid == null) return F;
		IFluidTank tTank = getFluidTankDrainable(UT.Code.side(aDirection), FL.make(aFluid, 0));
		return tTank != null && (tTank.getFluid() != null && tTank.getFluid().getFluid() == aFluid);
	}
	
	public FluidTankInfo[] getTankInfo(ForgeDirection aDirection) {
		IFluidTank[] tTanks = getFluidTanks(UT.Code.side(aDirection));
		if (tTanks == null || tTanks.length <= 0) return ZL_FLUIDTANKINFO;
		FluidTankInfo[] rInfo = new FluidTankInfo[tTanks.length];
		for (int i = 0; i < tTanks.length; i++) rInfo[i] = new FluidTankInfo(tTanks[i]);
		return rInfo;
	}
	
	// A Default implementation of the MultiBlock related Fluid Tank behaviour.
	
	protected IFluidTank getFluidTankFillable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToFill) {return getFluidTankFillable(aSide, aFluidToFill);}
	protected IFluidTank getFluidTankDrainable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToDrain) {return getFluidTankDrainable(aSide, aFluidToDrain);}
	protected IFluidTank[] getFluidTanks(MultiTileEntityMultiBlockPart aPart, byte aSide) {return getFluidTanks(aSide);}
	
	public int fill(MultiTileEntityMultiBlockPart aPart, byte aDirection, FluidStack aFluid, boolean aDoFill) {
		if (aFluid == null || aFluid.amount <= 0) return 0;
		IFluidTank tTank = getFluidTankFillable(aPart, UT.Code.side(aDirection), aFluid);
		if (tTank == null) return 0;
		int rFilledAmount = tTank.fill(aFluid, aDoFill);
		if (rFilledAmount > 0 && aDoFill) updateInventory();
		return rFilledAmount;
	}
	
	public FluidStack drain(MultiTileEntityMultiBlockPart aPart, byte aDirection, FluidStack aFluid, boolean aDoDrain) {
		if (aFluid == null || aFluid.amount <= 0) return null;
		IFluidTank tTank = getFluidTankDrainable(aPart, UT.Code.side(aDirection), aFluid);
		if (tTank == null || tTank.getFluid() == null || tTank.getFluidAmount() == 0 || !tTank.getFluid().isFluidEqual(aFluid)) return null;
		FluidStack rDrained = tTank.drain(aFluid.amount, aDoDrain);
		if (rDrained != null && aDoDrain) updateInventory();
		return rDrained;
	}
	
	public FluidStack drain(MultiTileEntityMultiBlockPart aPart, byte aDirection, int aAmountToDrain, boolean aDoDrain) {
		if (aAmountToDrain <= 0) return null;
		IFluidTank tTank = getFluidTankDrainable(aPart, UT.Code.side(aDirection), null);
		if (tTank == null || tTank.getFluid() == null || tTank.getFluidAmount() == 0) return null;
		FluidStack rDrained = tTank.drain(aAmountToDrain, aDoDrain);
		if (rDrained != null && aDoDrain) updateInventory();
		return rDrained;
	}
	
	public boolean canFill(MultiTileEntityMultiBlockPart aPart, byte aDirection, Fluid aFluid) {
		if (aFluid == null) return F;
		IFluidTank tTank = getFluidTankFillable(aPart, UT.Code.side(aDirection), FL.make(aFluid, 0));
		return tTank != null && (tTank.getFluid() == null || tTank.getFluid().getFluid() == aFluid);
	}
	
	public boolean canDrain(MultiTileEntityMultiBlockPart aPart, byte aDirection, Fluid aFluid) {
		if (aFluid == null) return F;
		IFluidTank tTank = getFluidTankDrainable(aPart, UT.Code.side(aDirection), FL.make(aFluid, 0));
		return tTank != null && (tTank.getFluid() != null && tTank.getFluid().getFluid() == aFluid);
	}
	
	public FluidTankInfo[] getTankInfo(MultiTileEntityMultiBlockPart aPart, byte aDirection) {
		IFluidTank[] tTanks = getFluidTanks(aPart, UT.Code.side(aDirection));
		if (tTanks == null || tTanks.length <= 0) return ZL_FLUIDTANKINFO;
		FluidTankInfo[] rInfo = new FluidTankInfo[tTanks.length];
		for (int i = 0; i < tTanks.length; i++) rInfo[i] = new FluidTankInfo(tTanks[i]);
		return rInfo;
	}
	
	// A Default implementation of the Energy behaviour.
	
	public long doInject (TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject ) {return 0;}
	public long doExtract(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoExtract) {return 0;}
	public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return F;}
	public boolean isEnergyCapacitorType(TagData aEnergyType, byte aSide) {return F;}
	public long getEnergyStored(TagData aEnergyType, byte aSide) {return 0;}
	public long getEnergyCapacity(TagData aEnergyType, byte aSide) {return 0;}
	public Collection<TagData> getEnergyTypes(byte aSide) {return Collections.emptyList();}
	public Collection<TagData> getEnergyCapacitorTypes(byte aSide) {return Collections.emptyList();}
	
	public boolean isEnergyEmittingTo   (TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, T) && getSurfaceSizeAttachable(aSide) > 0;}
	public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, F) && getSurfaceSizeAttachable(aSide) > 0;}
	public synchronized long doEnergyExtraction(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoExtract) {return aSize != 0 && isEnergyEmittingTo   (aEnergyType, aSide, F) ? TD.Energy.ALL_SIZE_IRRELEVANT.contains(aEnergyType) || Math.abs(aSize) >= getEnergySizeOutputMin(aEnergyType, aSide) ? doExtract(aEnergyType, aSide, aSize, aAmount, aDoExtract) :       0 : 0;}
	public synchronized long doEnergyInjection (TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject ) {return aSize != 0 && isEnergyAcceptingFrom(aEnergyType, aSide, F) ? TD.Energy.ALL_SIZE_IRRELEVANT.contains(aEnergyType) || Math.abs(aSize) >= getEnergySizeInputMin (aEnergyType, aSide) ? doInject (aEnergyType, aSide, aSize, aAmount, aDoInject ) : aAmount : 0;}
	public long getEnergyOffered(TagData aEnergyType, byte aSide, long aSize) {return 0;}
	public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return 0;}
	public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return getEnergySizeOutputRecommended(aEnergyType, aSide) / 2;}
	public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return getEnergySizeOutputRecommended(aEnergyType, aSide) * 2;}
	public long getEnergyDemanded(TagData aEnergyType, byte aSide, long aSize) {return 0;}
	public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return 0;}
	public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return getEnergySizeInputRecommended(aEnergyType, aSide) / 2;}
	public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return getEnergySizeInputRecommended(aEnergyType, aSide) * 2;}
	
	// A Default implementation of the MultiBlock Energy behaviour.
	
	public boolean isEnergyType                         (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, boolean aEmitting) {return isEnergyType(aEnergyType, aSide, aEmitting);}
	public boolean isEnergyCapacitorType                (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide) {return isEnergyCapacitorType(aEnergyType, aSide);}
	public long getEnergyStored                         (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide) {return getEnergyStored(aEnergyType, aSide);}
	public long getEnergyCapacity                       (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide) {return getEnergyCapacity(aEnergyType, aSide);}
	public Collection<TagData> getEnergyTypes           (MultiTileEntityMultiBlockPart aPart, byte aSide) {return getEnergyTypes(aSide);}
	public Collection<TagData> getEnergyCapacitorTypes  (MultiTileEntityMultiBlockPart aPart, byte aSide) {return getEnergyCapacitorTypes(aSide);}
	
	public boolean isEnergyAcceptingFrom                (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	public boolean isEnergyEmittingTo                   (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyEmittingTo   (aEnergyType, aSide, aTheoretical);}
	public long doEnergyInjection                       (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject ) {return doEnergyInjection (aEnergyType, aSide, aSize, aAmount, aDoInject );}
	public long doEnergyExtraction                      (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoExtract) {return doEnergyExtraction(aEnergyType, aSide, aSize, aAmount, aDoExtract);}
	public long getEnergyOffered                        (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, long aSize) {return getEnergyOffered(aEnergyType, aSide, aSize);}
	public long getEnergySizeOutputRecommended          (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide) {return getEnergySizeOutputRecommended(aEnergyType, aSide);}
	public long getEnergySizeOutputMin                  (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide) {return getEnergySizeOutputMin(aEnergyType, aSide);}
	public long getEnergySizeOutputMax                  (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide) {return getEnergySizeOutputMax(aEnergyType, aSide);}
	public long getEnergyDemanded                       (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide, long aSize) {return getEnergyDemanded(aEnergyType, aSide, aSize);}
	public long getEnergySizeInputRecommended           (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide) {return getEnergySizeInputRecommended(aEnergyType, aSide);}
	public long getEnergySizeInputMin                   (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide) {return getEnergySizeInputMin(aEnergyType, aSide);}
	public long getEnergySizeInputMax                   (MultiTileEntityMultiBlockPart aPart, TagData aEnergyType, byte aSide) {return getEnergySizeInputMax(aEnergyType, aSide);}
	
	// A Default implementation for RF Stuff, so I don't have to implement those Interfaces manually every time I make an RF Emitter or Acceptor.
	
	public boolean canConnectEnergy(ForgeDirection aDirection) {for (TagData tTag : TD.Energy.ALL_RF) if (isEnergyEmittingTo(tTag, UT.Code.side(aDirection), T)) return T; return isEnergyAcceptingFrom(TD.Energy.RF, UT.Code.side(aDirection), T);}
	public int receiveEnergy(ForgeDirection aDirection, int aSize, boolean aSimulate) {return (int)doEnergyInjection (TD.Energy.RF, UT.Code.side(aDirection), aSize, 1, !aSimulate)*aSize;}
	public int extractEnergy(ForgeDirection aDirection, int aSize, boolean aSimulate) {return (int)doEnergyExtraction(TD.Energy.RF, UT.Code.side(aDirection), aSize, 1, !aSimulate)*aSize;}
	public int getEnergyStored   (ForgeDirection aDirection) {return UT.Code.bindInt(getEnergyCapacity(TD.Energy.RF, UT.Code.side(aDirection)));}
	public int getMaxEnergyStored(ForgeDirection aDirection) {return UT.Code.bindInt(getEnergyCapacity(TD.Energy.RF, UT.Code.side(aDirection)));}
	
	// A Default implementation for EU Stuff, so I don't have to implement those Interfaces manually every time I make an EU Emitter or Acceptor.
	
	public int getSinkTier() {return UT.Code.tierMax(getEnergySizeInputRecommended(TD.Energy.EU, SIDE_ANY));}
	public int getSourceTier() {return UT.Code.tierMax(getEnergySizeOutputRecommended(TD.Energy.EU, SIDE_ANY));}
	public boolean acceptsEnergyFrom(TileEntity aEmitter, ForgeDirection aDirection) {return !(aEmitter instanceof ITileEntityEnergy) && isEnergyAcceptingFrom(TD.Energy.EU, UT.Code.side(aDirection), T);}
	public boolean emitsEnergyTo(TileEntity aReceiver, ForgeDirection aDirection) {return !(aReceiver instanceof ITileEntityEnergy) && isEnergyEmittingTo(TD.Energy.EU, UT.Code.side(aDirection), T);}
	public double getDemandedEnergy() {long tSize = getEnergySizeInputMax(TD.Energy.EU, SIDE_ANY); return tSize * doEnergyInjection(TD.Energy.EU, SIDE_ANY, tSize, 256, F);}
	
	public double injectEnergy(ForgeDirection aDirection, double aAmount, double aSize) {
		aSize = Math.min(aSize, aAmount);
		return aAmount - (aSize <= 0 ? aAmount * doEnergyInjection(TD.Energy.EU, UT.Code.side(aDirection), (long)aAmount, 1, T) : aSize * doEnergyInjection(TD.Energy.EU, UT.Code.side(aDirection), (long)aSize, (long)(aAmount / aSize), T));
	}
	
	/**
	 * Structural checks that make sure that nothing is wrong with the environment around the Block. Should maybe only be checked every 600 ticks and only while the Machine is active.
	 * @return false if something went wrong.
	 */
	public boolean doDefaultStructuralChecks() {
		for (TagData tEnergyType : getEnergyTypes(SIDE_ANY)) {
			if (TD.Energy.ALL_WEAK_TO_FIRE.contains(tEnergyType)) for (byte tSide : ALL_SIDES_VALID) {
				if (!isFireProof(tSide) && getBlockAtSide(tSide) instanceof BlockFire && rng(10) == 0) {
					if (FIRE_EXPLOSIONS) explode(TD.Energy.ALL_EXPLODING.contains(tEnergyType) ? 4.0 : 0.1); else if (FIRE_BREAKING) explode(0.1);
					UT.Sounds.send(worldObj, TD.Energy.ALL_ELECTRIC.contains(tEnergyType)?SFX.IC_MACHINE_OVERLOAD:TD.Energy.ALL_KINETIC.contains(tEnergyType)?SFX.IC_MACHINE_INTERRUPT:SFX.MC_EXPLODE, 1, 1, getCoords());
					DEB.println("Machine came into contact with Fire - Energy Type: " + tEnergyType.getLocalisedNameLong());
					return F;
				}
			}
			if (TD.Energy.ALL_WEAK_TO_WATER.contains(tEnergyType)) for (byte tSide : ALL_SIDES_BUT_BOTTOM) {
				if (!isWaterProof(tSide) && WD.liquid(getBlockAtSide(tSide))) {
					if (WATER_EXPLOSIONS) explode(TD.Energy.ALL_EXPLODING.contains(tEnergyType) ? 4.0 : 0.1); else if (WATER_BREAKING) explode(0.1);
					UT.Sounds.send(worldObj, TD.Energy.ALL_ELECTRIC.contains(tEnergyType)?SFX.IC_MACHINE_OVERLOAD:TD.Energy.ALL_KINETIC.contains(tEnergyType)?SFX.IC_MACHINE_INTERRUPT:SFX.MC_EXPLODE, 1, 1, getCoords());
					DEB.println("Machine came into contact with Water - Energy Type: " + tEnergyType.getLocalisedNameLong());
					return F;
				}
				if (!isRainProof(tSide) && worldObj.isRaining() && getBiome().rainfall > 0 && rng(100) == 0 && getRainAtSide(tSide)) {
					if (RAIN_EXPLOSIONS) explode(TD.Energy.ALL_EXPLODING.contains(tEnergyType) ? 4.0 : 0.1); else if (RAIN_BREAKING) explode(0.1);
					UT.Sounds.send(worldObj, TD.Energy.ALL_ELECTRIC.contains(tEnergyType)?SFX.IC_MACHINE_OVERLOAD:TD.Energy.ALL_KINETIC.contains(tEnergyType)?SFX.IC_MACHINE_INTERRUPT:SFX.MC_EXPLODE, 1, 1, getCoords());
					DEB.println("Machine came into contact with Rain - Energy Type: " + tEnergyType.getLocalisedNameLong());
					return F;
				}
			}
			if (TD.Energy.ALL_WEAK_TO_THUNDER.contains(tEnergyType)) for (byte tSide : ALL_SIDES_BUT_BOTTOM) {
				if (!isThunderProof(tSide) && worldObj.isThundering() && rng(1000) == 0 && getRainAtSide(tSide)) {
					if (THUNDER_EXPLOSIONS) explode(TD.Energy.ALL_EXPLODING.contains(tEnergyType) ? 4.0 : 0.1); else if (THUNDER_BREAKING) explode(0.1);
					UT.Sounds.send(worldObj, TD.Energy.ALL_ELECTRIC.contains(tEnergyType)?SFX.IC_MACHINE_OVERLOAD:TD.Energy.ALL_KINETIC.contains(tEnergyType)?SFX.IC_MACHINE_INTERRUPT:SFX.MC_EXPLODE, 1, 1, getCoords());
					DEB.println("Machine came into contact with Thunder - Energy Type: " + tEnergyType.getLocalisedNameLong());
					return F;
				}
			}
		}
		return T;
	}
	
	public boolean isFireProof              (byte aSide) {return F;}
	public boolean isRainProof              (byte aSide) {return F;}
	public boolean isWaterProof             (byte aSide) {return F;}
	public boolean isThunderProof           (byte aSide) {return F;}
	
	// A Default implementation of the Surface behavior.
	
	public float getSurfaceSize             (byte aSide) {return 1;}
	public float getSurfaceSizeAttachable   (byte aSide) {return getSurfaceSize(aSide);}
	public float getSurfaceDistance         (byte aSide) {return 0;}
	public boolean isSurfaceSolid           (byte aSide) {return isSurfaceOpaque(aSide);}
	public boolean isSurfaceOpaque          (byte aSide) {return T;}
	public boolean isSealable               (byte aSide) {return F;}
	public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box();}
	public AxisAlignedBB getSelectedBoundingBoxFromPool () {if (FORCE_FULL_SELECTION_BOXES) return box(); return box(shrunkBox());}
	public void setBlockBoundsBasedOnState(Block aBlock) {if (FORCE_FULL_SELECTION_BOXES) box(aBlock); else box(aBlock, shrunkBox());}
	public boolean ignorePlayerCollisionWhenPlacing(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {return ignorePlayerCollisionWhenPlacing();}
	public boolean ignorePlayerCollisionWhenPlacing() {return F;}
	
	/** Old Coordinate containing Variant of onCoordinateChange, use only if you really need the Coordinates, as there is also a No-Parameter variant in use for some TileEntity Types! */
	public void onCoordinateChange(World aWorld, int aOldX, int aOldY, int aOldZ) {onCoordinateChange();}
	public void onCoordinateChange() {/**/}
	
	// AE Stuff
	
	@Override public boolean prepareToMove() {return T;}
	@Override public void doneMoving() {onCoordinateChange();}
	
	// Fire Stuff
	
	public int getFireSpreadSpeed(byte aSide, boolean aDefault) {return aDefault ? 150 : 0;}
	public int getFlammability   (byte aSide, boolean aDefault) {return aDefault ? 150 : 0;}
	public void setOnFire() {WD.burn(worldObj, getCoords(), F, F);}
	public boolean setToFire() {return worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.fire, 0, 3);}
	public boolean setToAir () {return worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air , 0, 3);}
	
	// Inventory Stuff
	
	public ItemStack slot(int aIndex, ItemStack aStack) {return NI;}
	public ItemStack slot(int aIndex) {return NI;}
	public ItemStack slotTake(int aIndex) {return NI;}
	public boolean slotNull(int aIndex) {if (slotHas(aIndex) && slot(aIndex).stackSize < 0) return slotKill(aIndex); return F;}
	public boolean slotKill(int aIndex) {slot(aIndex, NI); return T;}
	public boolean slotHas(int aIndex) {return F;}
	public boolean invempty() {return T;}
	public int invsize() {return 0;}
	public NBTTagCompound slotNBT(int aIndex) {return null;}
	
	// Connectable Inventories
	
	public int[] getAccessibleSlotsOfConnectedInventory() {return UT.Code.getAscendingArray(invsize());}
	
	public int addStackToConnectedInventory(byte aSide, ItemStack aStack, boolean aOnlyAddIfItAlreadyHasItemsOfThatTypeOrIsDedicated) {
		if (ST.invalid(aStack)) return 0;
		int rCount = 0, aCount = aStack.stackSize;
		for (int tSlot : getAccessibleSlotsOfConnectedInventory()) if (ST.equal(slot(tSlot), aStack)) {
			aOnlyAddIfItAlreadyHasItemsOfThatTypeOrIsDedicated = F;
			int tChange = Math.min(aCount, slot(tSlot).getMaxStackSize() - slot(tSlot).stackSize);
			slot(tSlot).stackSize += tChange;
			rCount += tChange;
			aCount -= tChange;
			if (aCount <= 0) {
				updateInventory();
				return rCount;
			}
		}
		if (!aOnlyAddIfItAlreadyHasItemsOfThatTypeOrIsDedicated) for (int tSlot : getAccessibleSlotsOfConnectedInventory()) if (!slotHas(tSlot)) {
			slot(tSlot, ST.amount(aCount, aStack));
			rCount += aCount;
			aCount = 0;
			updateInventory();
			return rCount;
		}
		if (rCount > 0) updateInventory();
		return rCount;
	}
	
	public int removeStackFromConnectedInventory(byte aSide, ItemStack aStack, boolean aOnlyRemoveIfItCanRemoveAllAtOnce) {
		if (ST.invalid(aStack)) return 0;
		int rCount = 0;
		if (!aOnlyRemoveIfItCanRemoveAllAtOnce || getAmountOfItemsInConnectedInventory(aSide, aStack, aStack.stackSize) >= aStack.stackSize) {
			for (int tSlot : getAccessibleSlotsOfConnectedInventory()) if (ST.equal(slot(tSlot), aStack)) {
				int tChange = Math.min(aStack.stackSize - rCount, slot(tSlot).stackSize);
				slot(tSlot).stackSize -= tChange;
				if (slot(tSlot).stackSize <= 0) slotKill(tSlot);
				rCount += tChange;
				if (rCount >= aStack.stackSize) {
					updateInventory();
					return rCount;
				}
			}
		}
		if (rCount > 0) updateInventory();
		return rCount;
	}
	
	public long getAmountOfItemsInConnectedInventory(byte aSide, ItemStack aStack, long aStopCountingAtThisNumber) {
		if (ST.invalid(aStack)) return 0;
		long rCount = 0;
		for (int tSlot : getAccessibleSlotsOfConnectedInventory()) if (ST.equal(slot(tSlot), aStack) && (rCount += slot(tSlot).stackSize) >= aStopCountingAtThisNumber) break;
		return rCount;
	}
	
	// Quick Fix for newly added Functionality
	
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {return 0;}
	public int capnozzleFill(byte aSide, FluidStack aFluid, boolean aDoFill) {return funnelFill(aSide, aFluid, aDoFill);}
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {return null;}
	public FluidStack nozzleDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {return tapDrain(aSide, aMaxDrain, aDoDrain);}   
	
	// Useful to check if a Player or any other Entity is actually allowed to access or interact with this Block.
	
	@Override
	public boolean allowInteraction(Entity aEntity) {return T;}
	public boolean allowRightclick(Entity aEntity) {return allowInteraction(aEntity);}
	public float getPlayerRelativeBlockHardness(EntityPlayer aPlayer, float aOriginal) {return allowInteraction(aPlayer) ? Math.max(aOriginal, 0.0001F) : 0;}
	
	// Regarding Multiblocks. If true it will always send a machineblock update whenever something relevant changes, such as facing or connectivity.
	
	public boolean hasMultiBlockMachineRelevantData() {return F;}
	
	// Makes a Bounding Box without having to constantly specify the Offset Coordinates.
	
	public AxisAlignedBB box(double aMinX, double aMinY, double aMinZ, double aMaxX, double aMaxY, double aMaxZ) {return AxisAlignedBB.getBoundingBox(xCoord+aMinX, yCoord+aMinY, zCoord+aMinZ, xCoord+aMaxX, yCoord+aMaxY, zCoord+aMaxZ);}
	public AxisAlignedBB box(double[] aBox) {return AxisAlignedBB.getBoundingBox(xCoord+aBox[0], yCoord+aBox[1], zCoord+aBox[2], xCoord+aBox[3], yCoord+aBox[4], zCoord+aBox[5]);}
	public AxisAlignedBB box(float[] aBox) {return AxisAlignedBB.getBoundingBox(xCoord+aBox[0], yCoord+aBox[1], zCoord+aBox[2], xCoord+aBox[3], yCoord+aBox[4], zCoord+aBox[5]);}
	public AxisAlignedBB box() {return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1, yCoord+1, zCoord+1);}
	
	public boolean box(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, double aMinX, double aMinY, double aMinZ, double aMaxX, double aMaxY, double aMaxZ) {
		AxisAlignedBB tBox = box(aMinX, aMinY, aMinZ, aMaxX, aMaxY, aMaxZ);
		return tBox.intersectsWith(aAABB) && aList.add(tBox);
	}
	public boolean box(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, double[] aBox) {
		AxisAlignedBB tBox = box(aBox[0], aBox[1], aBox[2], aBox[3], aBox[4], aBox[5]);
		return tBox.intersectsWith(aAABB) && aList.add(tBox);
	}
	public boolean box(AxisAlignedBB aAABB, List<AxisAlignedBB> aList, float[] aBox) {
		AxisAlignedBB tBox = box(aBox[0], aBox[1], aBox[2], aBox[3], aBox[4], aBox[5]);
		return tBox.intersectsWith(aAABB) && aList.add(tBox);
	}
	public boolean box(AxisAlignedBB aAABB, List<AxisAlignedBB> aList) {
		AxisAlignedBB tBox = box(PX_BOX);
		return tBox.intersectsWith(aAABB) && aList.add(tBox);
	}
	public boolean box(AxisAlignedBB aBox, AxisAlignedBB aAABB, List<AxisAlignedBB> aList) {
		return aBox != null && aBox.intersectsWith(aAABB) && aList.add(aBox);
	}
	
	public boolean box(Block aBlock) {aBlock.setBlockBounds(0,0,0,1,1,1); return T;}
	public boolean box(Block aBlock, double[] aBox) {aBlock.setBlockBounds((float)aBox[0], (float)aBox[1], (float)aBox[2], (float)aBox[3], (float)aBox[4], (float)aBox[5]); return T;}
	public boolean box(Block aBlock, float[] aBox) {aBlock.setBlockBounds(aBox[0], aBox[1], aBox[2], aBox[3], aBox[4], aBox[5]); return T;}
	public boolean box(Block aBlock, double aMinX, double aMinY, double aMinZ, double aMaxX, double aMaxY, double aMaxZ) {aBlock.setBlockBounds((float)aMinX, (float)aMinY, (float)aMinZ, (float)aMaxX, (float)aMaxY, (float)aMaxZ); return T;}
	
	public float[] shrunkBox() {return PX_BOX;}
	
	// Default Overlay Code
	
	public boolean isUsingWrenchingOverlay(ItemStack aStack, byte aSide) {
		return F;
	}
	
	public boolean isConnectedWrenchingOverlay(ItemStack aStack, byte aSide) {
		return F;
	}
	
	public boolean onDrawBlockHighlight2(DrawBlockHighlightEvent aEvent) {return F;}
	
	public final boolean onDrawBlockHighlight(DrawBlockHighlightEvent aEvent) {
		FORCE_FULL_SELECTION_BOXES = F;
		if (!SIDES_VALID[aEvent.target.sideHit] || onDrawBlockHighlight2(aEvent)) return T;
		if (ST.valid(aEvent.currentItem) && isUsingWrenchingOverlay(aEvent.currentItem, (byte)aEvent.target.sideHit)) {
			FORCE_FULL_SELECTION_BOXES = T;
			byte tConnections = 0; for (byte i = 0; i < 6; i++) if (isConnectedWrenchingOverlay(aEvent.currentItem, i)) tConnections |= (1 << i);
			RenderHelper.drawWrenchOverlay(aEvent.player, aEvent.target.blockX, aEvent.target.blockY, aEvent.target.blockZ, tConnections, (byte)aEvent.target.sideHit, aEvent.partialTicks);
			return T;
		}
		return T;
	}
	
	// Error things
	
	public String ERROR_MESSAGE = null;
	@Override public void setError(String aError) {ERROR_MESSAGE = aError; if (isServerSide()) NW_API.sendToAllPlayersInRange(new PacketBlockError(getCoords(), ERROR_MESSAGE), worldObj, getCoords());}
}
