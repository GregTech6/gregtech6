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

package gregapi.random;

import static gregapi.data.CS.*;

import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.delegate.ITileEntityCanDelegate;
import gregapi.tileentity.delegate.ITileEntityDelegating;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 * 
 * Contains simple Utility Functions based on the In-World-Coordinates
 */
public class WorldAndCoords implements IHasWorldAndCoords, Comparable<WorldAndCoords> {
	public final int mX, mY, mZ;
	public final World mWorld;
	
	public WorldAndCoords(World aWorld, int aX, int aY, int aZ) {mWorld = aWorld; mX = aX; mY = aY; mZ = aZ;}
	public WorldAndCoords(World aWorld, ChunkCoordinates aCoords) {mWorld = aWorld; mX = aCoords.posX; mY = aCoords.posY; mZ = aCoords.posZ;}
	public WorldAndCoords(TileEntity aTileEntity) {mWorld = aTileEntity.getWorldObj(); mX = aTileEntity.xCoord; mY = aTileEntity.yCoord; mZ = aTileEntity.zCoord;}
	
	@Override public World getWorld() {return mWorld;}
	@Override public int getX() {return mX;}
	@Override public int getY() {return mY;}
	@Override public int getZ() {return mZ;}
	@Override public int getOffsetX (byte aSide) {return mX + OFFX[aSide];}
	@Override public int getOffsetY (byte aSide) {return mY + OFFY[aSide];}
	@Override public int getOffsetZ (byte aSide) {return mZ + OFFZ[aSide];}
	@Override public int getOffsetX (byte aSide, int aMultiplier) {return mX + OFFX[aSide] * aMultiplier;}
	@Override public int getOffsetY (byte aSide, int aMultiplier) {return mY + OFFY[aSide] * aMultiplier;}
	@Override public int getOffsetZ (byte aSide, int aMultiplier) {return mZ + OFFZ[aSide] * aMultiplier;}
	@Override public int getOffsetXN(byte aSide) {return mX - OFFX[aSide];}
	@Override public int getOffsetYN(byte aSide) {return mY - OFFY[aSide];}
	@Override public int getOffsetZN(byte aSide) {return mZ - OFFZ[aSide];}
	@Override public int getOffsetXN(byte aSide, int aMultiplier) {return mX - OFFX[aSide] * aMultiplier;}
	@Override public int getOffsetYN(byte aSide, int aMultiplier) {return mY - OFFY[aSide] * aMultiplier;}
	@Override public int getOffsetZN(byte aSide, int aMultiplier) {return mZ - OFFZ[aSide] * aMultiplier;}
	@Override public ChunkCoordinates getCoords() {return new ChunkCoordinates(mX, mY, mZ);}
	@Override public ChunkCoordinates getOffset (byte aSide, int aMultiplier) {return new ChunkCoordinates(getOffsetX (aSide, aMultiplier), getOffsetY (aSide, aMultiplier), getOffsetZ (aSide, aMultiplier));}
	@Override public ChunkCoordinates getOffsetN(byte aSide, int aMultiplier) {return new ChunkCoordinates(getOffsetXN(aSide, aMultiplier), getOffsetYN(aSide, aMultiplier), getOffsetZN(aSide, aMultiplier));}
	@Override public boolean isServerSide() {return mWorld == null ? cpw.mods.fml.common.FMLCommonHandler.instance().getEffectiveSide().isServer() : !mWorld.isRemote;}
	@Override public boolean isClientSide() {return mWorld == null ? cpw.mods.fml.common.FMLCommonHandler.instance().getEffectiveSide().isClient() :  mWorld.isRemote;}
	@Override public int rng(int aRange) {return RNGSUS.nextInt(aRange);}
	@Override public int getRandomNumber(int aRange) {return RNGSUS.nextInt(aRange);}
	@Override public TileEntity getTileEntity   (int aX, int aY, int aZ) {return mWorld==null?null:mWorld.getTileEntity(aX, aY, aZ);}
	@Override public Block getBlock             (int aX, int aY, int aZ) {return mWorld==null?NB:mWorld.getBlock(aX, aY, aZ);}
	@Override public byte getMetaData           (int aX, int aY, int aZ) {return mWorld==null?0:UT.Code.bind4(mWorld.getBlockMetadata(aX, aY, aZ));}
	@Override public byte getLightLevel         (int aX, int aY, int aZ) {return mWorld==null?0:UT.Code.bind4((long)mWorld.getLightBrightness(aX, aY, aZ)*15);}
	@Override public boolean getOpacity         (int aX, int aY, int aZ) {return mWorld!=null&&mWorld.getBlock(aX, aY, aZ).isOpaqueCube();}
	@Override public boolean getSky             (int aX, int aY, int aZ) {return mWorld==null||mWorld.canBlockSeeTheSky(aX, aY, aZ);}
	@Override public boolean getRain            (int aX, int aY, int aZ) {return mWorld==null||mWorld.getPrecipitationHeight(aX, aZ) <= aY;}
	@Override public boolean getAir             (int aX, int aY, int aZ) {return mWorld==null||mWorld.getBlock(aX, aY, aZ).isAir(mWorld, aX, aY, aZ);}
	@Override public BiomeGenBase getBiome() {return getBiome(mX, mZ);}
	@Override public BiomeGenBase getBiome      (int aX, int aZ) {return mWorld==null?null:mWorld.getBiomeGenForCoords(aX, aZ);}
	@Override public BiomeGenBase getBiome      (ChunkCoordinates aCoords) {return mWorld==null?null:mWorld.getBiomeGenForCoords(aCoords.posX, aCoords.posZ);}
	@Override public TileEntity getTileEntity   (ChunkCoordinates aCoords) {return mWorld==null?null:mWorld.getTileEntity(aCoords.posX, aCoords.posY, aCoords.posZ);}
	@Override public Block getBlock             (ChunkCoordinates aCoords) {return mWorld==null?NB:mWorld.getBlock(aCoords.posX, aCoords.posY, aCoords.posZ);}
	@Override public byte getMetaData           (ChunkCoordinates aCoords) {return mWorld==null?0:UT.Code.bind4(mWorld.getBlockMetadata(aCoords.posX, aCoords.posY, aCoords.posZ));}
	@Override public byte getLightLevel         (ChunkCoordinates aCoords) {return mWorld==null?0:UT.Code.bind4((long)mWorld.getLightBrightness(aCoords.posX, aCoords.posY, aCoords.posZ)*15);}
	@Override public boolean getOpacity         (ChunkCoordinates aCoords) {return mWorld!=null&&mWorld.getBlock(aCoords.posX, aCoords.posY, aCoords.posZ).isOpaqueCube();}
	@Override public boolean getSky             (ChunkCoordinates aCoords) {return mWorld==null||mWorld.canBlockSeeTheSky(aCoords.posX, aCoords.posY, aCoords.posZ);}
	@Override public boolean getRain            (ChunkCoordinates aCoords) {return mWorld==null||mWorld.getPrecipitationHeight(aCoords.posX, aCoords.posZ) <= aCoords.posY;}
	@Override public boolean getAir             (ChunkCoordinates aCoords) {return mWorld==null||mWorld.getBlock(aCoords.posX, aCoords.posY, aCoords.posZ).isAir(mWorld, aCoords.posX, aCoords.posY, aCoords.posZ);}
	@Override public Block getBlockOffset(int aX, int aY, int aZ) {return getBlock(mX+aX, mY+aY, mZ+aZ);}
	@Override public Block getBlockAtSide(byte aSide) {return getBlockAtSideAndDistance(aSide, 1);}
	@Override public Block getBlockAtSideAndDistance(byte aSide, int aDistance) {return getBlock(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public byte getMetaDataOffset(int aX, int aY, int aZ) {return getMetaData(mX+aX, mY+aY, mZ+aZ);}
	@Override public byte getMetaDataAtSide(byte aSide) {return getMetaDataAtSideAndDistance(aSide, 1);}
	@Override public byte getMetaDataAtSideAndDistance(byte aSide, int aDistance) {return getMetaData(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public byte getLightLevelOffset(int aX, int aY, int aZ) {return getLightLevel(mX+aX, mY+aY, mZ+aZ);}
	@Override public byte getLightLevelAtSide(byte aSide) {return getLightLevelAtSideAndDistance(aSide, 1);}
	@Override public byte getLightLevelAtSideAndDistance(byte aSide, int aDistance) {return getLightLevel(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public boolean getOpacityOffset(int aX, int aY, int aZ) {return getOpacity(mX+aX, mY+aY, mZ+aZ);}
	@Override public boolean getOpacityAtSide(byte aSide) {return getOpacityAtSideAndDistance(aSide, 1);}
	@Override public boolean getOpacityAtSideAndDistance(byte aSide, int aDistance) {return getOpacity(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public boolean getRainOffset(int aX, int aY, int aZ) {return getRain(mX+aX, mY+aY, mZ+aZ);}
	@Override public boolean getRainAtSide(byte aSide) {return getRainAtSideAndDistance(aSide, 1);}
	@Override public boolean getRainAtSideAndDistance(byte aSide, int aDistance) {return getRain(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public boolean getSkyOffset(int aX, int aY, int aZ) {return getSky(mX+aX, mY+aY, mZ+aZ);}
	@Override public boolean getSkyAtSide(byte aSide) {return getSkyAtSideAndDistance(aSide, 1);}
	@Override public boolean getSkyAtSideAndDistance(byte aSide, int aDistance) {return getSky(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public boolean getAirOffset(int aX, int aY, int aZ) {return getAir(mX+aX, mY+aY, mZ+aZ);}
	@Override public boolean getAirAtSide(byte aSide) {return getAirAtSideAndDistance(aSide, 1);}
	@Override public boolean getAirAtSideAndDistance(byte aSide, int aDistance) {return getAir(getOffsetX(aSide, aDistance), getOffsetY(aSide, aDistance), getOffsetZ(aSide, aDistance));}
	@Override public TileEntity getTileEntityOffset(int aX, int aY, int aZ) {return getTileEntity(mX+aX, mY+aY, mZ+aZ);}
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
		if (tTileEntity == null) return new DelegatorTileEntity<>(null, mWorld, getOffsetX(aSide), getOffsetY(aSide), getOffsetZ(aSide), OPOS[aSide]);
		if (aNotConnectToDelegators && tTileEntity instanceof ITileEntityCanDelegate && ((ITileEntityCanDelegate)tTileEntity).isExtender(aSide)) return new DelegatorTileEntity<>(null, mWorld, getOffsetX(aSide), getOffsetY(aSide), getOffsetZ(aSide), OPOS[aSide]);
		if (aAllowDelegates && tTileEntity instanceof ITileEntityDelegating) return ((ITileEntityDelegating)tTileEntity).getDelegateTileEntity(OPOS[aSide]);
		return new DelegatorTileEntity<>(tTileEntity, tTileEntity.getWorldObj(), tTileEntity.xCoord, tTileEntity.yCoord, tTileEntity.zCoord, OPOS[aSide]);
	}
	
	@Override
	public boolean hasRedstoneIncoming() {
		for (byte tSide : ALL_SIDES_VALID) if (getRedstoneIncoming(tSide) > 0) return T;
		return F;
	}
	
	@Override
	public byte getRedstoneIncoming(byte aSide) {
		if (SIDES_INVALID[aSide]) {
			byte rRedstone = 0;
			for (byte tSide : ALL_SIDES_VALID) {
				rRedstone = (byte)Math.max(rRedstone, mWorld.getIndirectPowerLevelTo(getOffsetX(tSide), getOffsetY(tSide), getOffsetZ(tSide), tSide));
				if (rRedstone >= 15) return 15;
			}
			return rRedstone;
		}
		return UT.Code.bind4(mWorld.getIndirectPowerLevelTo(getOffsetX(aSide), getOffsetY(aSide), getOffsetZ(aSide), aSide));
	}
	
	@Override
	public byte getComparatorIncoming(byte aSide) {
		Block tBlock = getBlockAtSide(aSide);
		return tBlock.hasComparatorInputOverride()?UT.Code.bind4(tBlock.getComparatorInputOverride(mWorld, getOffsetX(aSide), getOffsetY(aSide), getOffsetZ(aSide), OPOS[aSide])):getRedstoneIncoming(aSide);
	}
	
	@Override public boolean equals(Object aObject) {return aObject instanceof WorldAndCoords && ((WorldAndCoords)aObject).mWorld == mWorld && ((WorldAndCoords)aObject).mX == mX && ((WorldAndCoords)aObject).mY == mY && ((WorldAndCoords)aObject).mZ == mZ;}
	@Override public int hashCode() {return mX + mZ << 8 + mY << 16;}
	@Override public int compareTo(WorldAndCoords aObject) {return mY == aObject.mY ? mZ == aObject.mZ ? mX - aObject.mX : mZ - aObject.mZ : mY - aObject.mY;}
	@Override public String toString() {return "Pos{x=" + mX + ", y=" + mY + ", z=" + mZ + '}';}
}
