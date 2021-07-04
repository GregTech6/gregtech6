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

package gregapi.tileentity.base;

import static gregapi.data.CS.*;

import gregapi.tileentity.ITileEntityNeedsSaving;
import gregapi.tileentity.ITileEntityUnloadable;
import gregapi.util.WD;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;

/**
 * @author Gregorius Techneticies
 * 
 * Improved TileEntity with less Lag in regards of getting adjacent TileEntities, a Timer and a Coordinate Check in case it gets moved by Frames or something.
 */
public abstract class TileEntityBase02AdjacentTEBuffer extends TileEntityBase01Root implements ITileEntityNeedsSaving {
	public TileEntityBase02AdjacentTEBuffer() {
		super(T);
	}
	
	/** Timer Value */
	protected long mTimer = 0;
	
	/** old Coordinates during the previous Tick */
	protected int oX = 0, oY = 0, oZ = 0;
	
	/**
	 * Buffers adjacent TileEntities for faster access
	 * "this" means that there is no TileEntity, while "null" means that it doesn't know if there is even a TileEntity and still needs to check that if needed.
	 */
	private final TileEntity[] mBufferedTileEntities = new TileEntity[6];
	
	private void clearNullMarkersFromTileEntityBuffer() {
		for (int i = 0; i < mBufferedTileEntities.length; i++) if (mBufferedTileEntities[i] == this) mBufferedTileEntities[i] = null;
	}
	
	private void clearEverythingFromTileEntityBuffer() {
		for (int i = 0; i < mBufferedTileEntities.length; i++) mBufferedTileEntities[i] = null;
	}
	
	/**
	 * YOU MUST HAVE THIS INSIDE YOUR BLOCK CODE!!!
	 * 
	 *  public void onNeighborChange(IBlockAccess aWorld, int aX, int aY, int aZ, int aTileX, int aTileY, int aTileZ) {
	 *      TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
	 *      if (tTileEntity instanceof ITileEntity) ((ITileEntity)tTileEntity).onAdjacentBlockChange(aTileX, aTileY, aTileZ);
	 *  }
	 *  
	 *  public void onNeighborBlockChange(World aWorld, int aX, int aY, int aZ, Block aBlock) {
	 *      TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
	 *      if (tTileEntity instanceof ITileEntity) ((ITileEntity)tTileEntity).onAdjacentBlockChange(aX, aY, aZ);
	 *  }
	 */
	@Override
	public final void onAdjacentBlockChange(int aTileX, int aTileY, int aTileZ) {
		super.onAdjacentBlockChange(aTileX, aTileY, aTileZ);
		clearNullMarkersFromTileEntityBuffer();
		onAdjacentBlockChange2(aTileX, aTileY, aTileZ);
	}
	
	public void onAdjacentBlockChange2(int aTileX, int aTileY, int aTileZ) {
		//
	}
	
	/**
	 * Highly optimised in order to return adjacent TileEntities much faster.
	 */
	@Override
	public TileEntity getTileEntityAtSideAndDistance(byte aSide, int aDistance) {
		if (worldObj == null) return null;
		if (aDistance != 1) return super.getTileEntityAtSideAndDistance(aSide, aDistance);
		if (SIDES_INVALID[aSide] || mBufferedTileEntities[aSide] == this) return null;
		ChunkCoordinates tCoords = getOffset(aSide, 1);
		boolean tChunksCrossed = crossedChunkBorder(tCoords);
		if (tChunksCrossed && (!(mBufferedTileEntities[aSide] instanceof ITileEntityUnloadable) || ((ITileEntityUnloadable)mBufferedTileEntities[aSide]).isDead())) {
			mBufferedTileEntities[aSide] = null;
			if (mIgnoreUnloadedChunks && !worldObj.blockExists(tCoords.posX, tCoords.posY, tCoords.posZ)) return null;
		}
		if (mBufferedTileEntities[aSide] == null) {
			TileEntity tTileEntity = WD.te(worldObj, tCoords.posX, tCoords.posY, tCoords.posZ, T);
			if (tTileEntity == null) {mBufferedTileEntities[aSide] = this; return null;}
			if (tChunksCrossed) WD.mark(tTileEntity);
			if (tTileEntity.xCoord == tCoords.posX && tTileEntity.yCoord == tCoords.posY && tTileEntity.zCoord == tCoords.posZ) return mBufferedTileEntities[aSide] = tTileEntity;
			mBufferedTileEntities[aSide] = null;
			return tTileEntity;
		}
		if (mBufferedTileEntities[aSide].xCoord != tCoords.posX || mBufferedTileEntities[aSide].yCoord != tCoords.posY || mBufferedTileEntities[aSide].zCoord != tCoords.posZ) {
			mBufferedTileEntities[aSide] = null;
			return getTileEntityAtSideAndDistance(aSide, aDistance);
		}
		if (mBufferedTileEntities[aSide] instanceof ITileEntityUnloadable) {
			if (((ITileEntityUnloadable)mBufferedTileEntities[aSide]).isDead()) {
				mBufferedTileEntities[aSide] = null;
				return getTileEntityAtSideAndDistance(aSide, aDistance);
			}
		} else if (mBufferedTileEntities[aSide].isInvalid()) {
			mBufferedTileEntities[aSide] = null;
			return getTileEntityAtSideAndDistance(aSide, aDistance);
		}
		if (tChunksCrossed) WD.mark(mBufferedTileEntities[aSide]);
		return mBufferedTileEntities[aSide];
	}
	
	@Override
	public void validate() {
		clearNullMarkersFromTileEntityBuffer();
		super.validate();
	}
	
	@Override
	public void invalidate() {
		clearNullMarkersFromTileEntityBuffer();
		super.invalidate();
	}
	
	@Override
	public void onChunkUnload() {
		clearNullMarkersFromTileEntityBuffer();
		super.onChunkUnload();
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (!isDead()) {
			mTimer++;
			
			if (isServerSide()) {
				if (mTimer == 1) {
					oX = xCoord; oY = yCoord; oZ = zCoord; mDoesBlockUpdate = T;
					clearEverythingFromTileEntityBuffer();
				}
				if (oX != xCoord || oY != yCoord || oZ != zCoord) {
					clearEverythingFromTileEntityBuffer();
					onCoordinateChange(worldObj, oX, oY, oZ);
					oX = xCoord; oY = yCoord; oZ = zCoord;
				}
			}
		}
	}
	
	@Override
	public long getTimer() {
		return mTimer;
	}
	
	@Override
	public void doneMoving() {
		clearEverythingFromTileEntityBuffer();
		onCoordinateChange(worldObj, oX, oY, oZ);
		oX = xCoord; oY = yCoord; oZ = zCoord;
	}
}
