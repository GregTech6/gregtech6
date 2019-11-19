/**
 * Copyright (c) 2019 Gregorius Techneticies
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

package gregapi.tileentity;

import gregapi.random.IHasWorldAndCoords;
import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntity extends IHasWorldAndCoords, ITileEntityUnloadable, ITileEntityErrorable {
	/**
	 * Sends a Block Event to the Client TileEntity, the byte Parameters are only for validation as Minecraft doesn't properly write Packet Data.
	 * 
	 * This should be used to send Block Sound Effects to the Client, as it is much less Network Heavy to have 2 Bytes rather than a String.
	 */
	public void sendBlockEvent(byte aID, byte aValue);
	
	/** @return the amount of Time this TileEntity has been loaded. */
	public long getTimer();
	
	/**
	 * YOU MUST HAVE THIS INSIDE YOUR BLOCK CODE!!!
	 * 
	 * public void onNeighborChange(IBlockAccess aWorld, int aX, int aY, int aZ, int aTileX, int aTileY, int aTileZ) {
	 *     TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
	 *     if (tTileEntity instanceof ITileEntity) ((ITileEntity)tTileEntity).onAdjacentBlockChange(aTileX, aTileY, aTileZ);
	 * }
	 * 
	 * public void onNeighborBlockChange(World aWorld, int aX, int aY, int aZ, Block aBlock) {
	 *     TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
	 *     if (tTileEntity instanceof ITileEntity) ((ITileEntity)tTileEntity).onAdjacentBlockChange(aX, aY, aZ);
	 * }
	 */
	public void onAdjacentBlockChange(int aTileX, int aTileY, int aTileZ);
	
	/** Called after the TileEntity has been placed and set up. */
	public void onTileEntityPlaced();
	
	public boolean allowInteraction(Entity aEntity);
}
