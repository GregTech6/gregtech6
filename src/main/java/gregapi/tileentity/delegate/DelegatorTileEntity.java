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

package gregapi.tileentity.delegate;

import static gregapi.data.CS.*;

import gregapi.random.WorldAndCoords;
import gregapi.tileentity.ITileEntityUnloadable;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Gregorius Techneticies
 */
public final class DelegatorTileEntity<T> extends WorldAndCoords {
	/** the TileEntity. This should be an instance of TileEntity. */
	public final T mTileEntity;
	/** the Side of the Delegate responsible for handling. So a TE-Tesseract alike can go a curve. */
	public final byte mSideOfTileEntity;
	
	public DelegatorTileEntity(DelegatorTileEntity<T> aDelegator) {
		super(aDelegator.mWorld, aDelegator.mX, aDelegator.mY, aDelegator.mZ);
		mTileEntity = aDelegator.mTileEntity;
		mSideOfTileEntity = aDelegator.mSideOfTileEntity;
	}
	
	public DelegatorTileEntity(T aTileEntity, byte aSideOfTileEntity) {
		super((TileEntity)aTileEntity);
		mTileEntity = aTileEntity;
		mSideOfTileEntity = aSideOfTileEntity;
	}
	
	public DelegatorTileEntity(T aTileEntity, DelegatorTileEntity<?> aDelegator) {
		super(aDelegator.mWorld, aDelegator.mX, aDelegator.mY, aDelegator.mZ);
		mTileEntity = aTileEntity;
		mSideOfTileEntity = aDelegator.mSideOfTileEntity;
	}
	
	public DelegatorTileEntity(T aTileEntity, World aWorld, int aX, int aY, int aZ, byte aSideOfTileEntity) {
		super(aWorld, aX, aY, aZ);
		mTileEntity = aTileEntity;
		mSideOfTileEntity = aSideOfTileEntity;
	}
	
	public DelegatorTileEntity(T aTileEntity, World aWorld, ChunkCoordinates aCoords, byte aSideOfTileEntity) {
		super(aWorld, aCoords.posX, aCoords.posY, aCoords.posZ);
		mTileEntity = aTileEntity;
		mSideOfTileEntity = aSideOfTileEntity;
	}
	
	public AxisAlignedBB box(double aMinX, double aMinY, double aMinZ, double aMaxX, double aMaxY, double aMaxZ) {return AxisAlignedBB.getBoundingBox(mX+aMinX, mY+aMinY, mZ+aMinZ, mX+aMaxX, mY+aMaxY, mZ+aMaxZ);}
	public AxisAlignedBB box(double[] aBox) {return AxisAlignedBB.getBoundingBox(mX+aBox[0], mY+aBox[1], mZ+aBox[2], mX+aBox[3], mY+aBox[4], mZ+aBox[5]);}
	public AxisAlignedBB box(float[] aBox) {return AxisAlignedBB.getBoundingBox(mX+aBox[0], mY+aBox[1], mZ+aBox[2], mX+aBox[3], mY+aBox[4], mZ+aBox[5]);}
	public AxisAlignedBB box() {return AxisAlignedBB.getBoundingBox(mX, mY, mZ, mX+1, mY+1, mZ+1);}
	
	public ForgeDirection getForgeSideOfTileEntity() {return FORGE_DIR[mSideOfTileEntity];}
	public Block getBlock() {return mWorld.getBlock(mX, mY, mZ);}
	public byte getMetaData() {return UT.Code.bind4(mWorld.getBlockMetadata(mX, mY, mZ));}
	public boolean setBlock(Block aBlock) {return mWorld.setBlock(mX, mY, mZ, aBlock, 0, 3);}
	public boolean setBlock(Block aBlock, int aMetaData) {return mWorld.setBlock(mX, mY, mZ, aBlock, UT.Code.bind4(aMetaData), 3);}
	public boolean setBlock(Block aBlock, int aMetaData, int aFlags) {return mWorld.setBlock(mX, mY, mZ, aBlock, UT.Code.bind4(aMetaData), aFlags);}
	public boolean setMetaData(int aMetaData) {return mWorld.setBlockMetadataWithNotify(mX, mY, mZ, UT.Code.bind4(aMetaData), 3);}
	public boolean setMetaData(int aMetaData, int aFlags) {return mWorld.setBlockMetadataWithNotify(mX, mY, mZ, UT.Code.bind4(aMetaData), aFlags);}
	
	public boolean hasCollisionBox() {return mWorld != null && WD.hasCollide(mWorld, mX, mY, mZ);}
	
	public boolean equalCoords(DelegatorTileEntity<?> aOther) {return aOther.mX == mX && aOther.mY == mY && aOther.mZ == mZ;}
	public boolean equalSideAndCoords(DelegatorTileEntity<?> aOther) {return aOther.mSideOfTileEntity == mSideOfTileEntity && equalCoords(aOther);}
	public boolean equalSideWorldAndCoords(DelegatorTileEntity<?> aOther) {return aOther.mWorld == mWorld && equalSideAndCoords(aOther);}
	public boolean equalSideTileEntityAndCoords(DelegatorTileEntity<?> aOther) {return aOther.mTileEntity == mTileEntity && equalSideAndCoords(aOther);}
	
	public boolean exists() {return mTileEntity instanceof ITileEntityUnloadable ? !((ITileEntityUnloadable)mTileEntity).isDead() : mTileEntity != null && !((TileEntity)mTileEntity).isInvalid() && mWorld != null && mWorld.blockExists(mX, mY, mZ);}
	
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
	@Override public BiomeGenBase getBiome      (int aX, int aZ) {return mWorld==null?null:mWorld.getBiomeGenForCoords(aX, aZ);}
	@Override public TileEntity getTileEntity   (ChunkCoordinates aCoords) {return mWorld==null?null:mWorld.getTileEntity(aCoords.posX, aCoords.posY, aCoords.posZ);}
	@Override public Block getBlock             (ChunkCoordinates aCoords) {return mWorld==null?NB:mWorld.getBlock(aCoords.posX, aCoords.posY, aCoords.posZ);}
	@Override public byte getMetaData           (ChunkCoordinates aCoords) {return mWorld==null?0:UT.Code.bind4(mWorld.getBlockMetadata(aCoords.posX, aCoords.posY, aCoords.posZ));}
	@Override public byte getLightLevel         (ChunkCoordinates aCoords) {return mWorld==null?0:UT.Code.bind4((long)mWorld.getLightBrightness(aCoords.posX, aCoords.posY, aCoords.posZ)*15);}
	@Override public boolean getOpacity         (ChunkCoordinates aCoords) {return mWorld!=null&&mWorld.getBlock(aCoords.posX, aCoords.posY, aCoords.posZ).isOpaqueCube();}
	@Override public boolean getSky             (ChunkCoordinates aCoords) {return mWorld==null||mWorld.canBlockSeeTheSky(aCoords.posX, aCoords.posY, aCoords.posZ);}
	@Override public boolean getRain            (ChunkCoordinates aCoords) {return mWorld==null||mWorld.getPrecipitationHeight(aCoords.posX, aCoords.posZ) <= aCoords.posY;}
	@Override public boolean getAir             (ChunkCoordinates aCoords) {return mWorld==null||mWorld.getBlock(aCoords.posX, aCoords.posY, aCoords.posZ).isAir(mWorld, aCoords.posX, aCoords.posY, aCoords.posZ);}
	@Override public BiomeGenBase getBiome      (ChunkCoordinates aCoords) {return mWorld==null?null:mWorld.getBiomeGenForCoords(aCoords.posX, aCoords.posZ);}
}
