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

package gregapi.tileentity.connectors;

import static gregapi.data.CS.*;

import java.util.Map;

import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.UT;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityItemPipe extends ITileEntityConnector {
	/**
	 * @return if this Pipe can still be used.
	 */
	public boolean pipeCapacityCheck();
	
	/**
	 * @return if this Pipe can still be used.
	 */
	public boolean incrementTransferCounter(long aIncrement);
	
	/**
	 * Sends an ItemStack from aSender to the adjacent Blocks.
	 * @param aSender the BaseMetaTileEntity sending the Stack.
	 * @return if it was able to send something
	 */
	public boolean sendItemStack(Object aSender);
	
	public boolean canEmitItemsTo(byte aSide, Object aSender);
	
	public boolean canAcceptItemsFrom(byte aSide, Object aSender);
	
	/**
	 * Executes the Sending Code for inserting Stacks into the TileEntities.
	 * @param aSender the BaseMetaTileEntity sending the Stack.
	 * @param aSide the Side of the PIPE facing the TileEntity.
	 * @return if this Side was allowed to Output into the Block.
	 */
	public boolean insertItemStackIntoTileEntity(Object aSender, byte aSide);
	
	/**
	 * Can be used to make flow control Pipes, like Redpowers Restriction Tubes.
	 * Every normal Pipe returns a Value of 32768, so you can easily insert lower Numbers to set Routing priorities.
	 * Negative Numbers to "suck" Items into a certain direction are also possible.
	 */
	public long getStepSize();
	
	/**
	 * Utility for the Item Network
	 */
	public static class Util {
		/**
		 * @return a List of connected Item Pipes
		 */
		public static Map<ITileEntityItemPipe, Long> scanPipes(ITileEntityItemPipe aPipe, Map<ITileEntityItemPipe, Long> aMap, long aStep, boolean aSuckItems, boolean aIgnoreCapacity) {
			aStep += aPipe.getStepSize();
			// TODO Make this iterative instead of recursive.
			if (aIgnoreCapacity || aPipe.pipeCapacityCheck()) if (aMap.get(aPipe) == null || aMap.get(aPipe) > aStep) {
				aMap.put(aPipe, aStep);
				for (byte aSide : ALL_SIDES_VALID) {
					if (aSuckItems) {
						if (aPipe.canAcceptItemsFrom(aSide, null)) {
							DelegatorTileEntity<TileEntity> tDelegator = aPipe.getAdjacentTileEntity(aSide);
							if (tDelegator.mTileEntity instanceof ITileEntityItemPipe && UT.Code.haveOneCommonElement(aPipe.getConnectorTypes(aSide), ((ITileEntityItemPipe)tDelegator.mTileEntity).getConnectorTypes(tDelegator.mSideOfTileEntity)) && ((ITileEntityItemPipe)tDelegator.mTileEntity).canEmitItemsTo(tDelegator.mSideOfTileEntity, null)) {
								scanPipes((ITileEntityItemPipe)tDelegator.mTileEntity, aMap, aStep, aSuckItems, aIgnoreCapacity);
							}
						}
					} else {
						if (aPipe.canEmitItemsTo(aSide, null)) {
							DelegatorTileEntity<TileEntity> tDelegator = aPipe.getAdjacentTileEntity(aSide);
							if (tDelegator.mTileEntity instanceof ITileEntityItemPipe && UT.Code.haveOneCommonElement(aPipe.getConnectorTypes(aSide), ((ITileEntityItemPipe)tDelegator.mTileEntity).getConnectorTypes(tDelegator.mSideOfTileEntity)) && ((ITileEntityItemPipe)tDelegator.mTileEntity).canAcceptItemsFrom(tDelegator.mSideOfTileEntity, null)) {
								scanPipes((ITileEntityItemPipe)tDelegator.mTileEntity, aMap, aStep, aSuckItems, aIgnoreCapacity);
							}
						}
					}
				}
			}
			return aMap;
		}
	}
}
