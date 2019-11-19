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

package gregapi.block;

import gregapi.network.INetworkHandler;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 */
public interface IBlockSyncData {
	/** If you have something that causes a Crash here, the Connection gets terminated. */
	public void receiveDataByte             (IBlockAccess aWorld, int aX, int aY, int aZ, byte   aData, INetworkHandler aNetworkHandler);
	/** If you have something that causes a Crash here, the Connection gets terminated. */
	public void receiveDataShort            (IBlockAccess aWorld, int aX, int aY, int aZ, short  aData, INetworkHandler aNetworkHandler);
	/** If you have something that causes a Crash here, the Connection gets terminated. */
	public void receiveDataInteger          (IBlockAccess aWorld, int aX, int aY, int aZ, int    aData, INetworkHandler aNetworkHandler);
	/** If you have something that causes a Crash here, the Connection gets terminated. */
	public void receiveDataLong             (IBlockAccess aWorld, int aX, int aY, int aZ, long   aData, INetworkHandler aNetworkHandler);
	/** If you have something that causes a Crash here, the Connection gets terminated. */
	public void receiveDataByteArray        (IBlockAccess aWorld, int aX, int aY, int aZ, byte[] aData, INetworkHandler aNetworkHandler);
	/** If you have something that causes a Crash here, the Connection gets terminated. */
	public void receiveDataName             (IBlockAccess aWorld, int aX, int aY, int aZ, String aData, INetworkHandler aNetworkHandler);
	
	public static interface IBlockSyncDataAndIDs extends IBlockSyncData {
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveData             (IBlockAccess aWorld, int aX, int aY, int aZ              , INetworkHandler aNetworkHandler, short aID1, short aID2);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataByte         (IBlockAccess aWorld, int aX, int aY, int aZ, byte   aData, INetworkHandler aNetworkHandler, short aID1, short aID2);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataShort        (IBlockAccess aWorld, int aX, int aY, int aZ, short  aData, INetworkHandler aNetworkHandler, short aID1, short aID2);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataInteger      (IBlockAccess aWorld, int aX, int aY, int aZ, int    aData, INetworkHandler aNetworkHandler, short aID1, short aID2);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataLong         (IBlockAccess aWorld, int aX, int aY, int aZ, long   aData, INetworkHandler aNetworkHandler, short aID1, short aID2);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataByteArray    (IBlockAccess aWorld, int aX, int aY, int aZ, byte[] aData, INetworkHandler aNetworkHandler, short aID1, short aID2);
	}
	
	public static interface IBlockSyncDataAndCoversAndIDs extends IBlockSyncDataAndIDs {
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveData             (IBlockAccess aWorld, int aX, int aY, int aZ              , INetworkHandler aNetworkHandler, short aID1, short aID2, short[] aCoverIDs, short[] aCoverMetas, short[] aCoverVisuals);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataByte         (IBlockAccess aWorld, int aX, int aY, int aZ, byte   aData, INetworkHandler aNetworkHandler, short aID1, short aID2, short[] aCoverIDs, short[] aCoverMetas, short[] aCoverVisuals);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataShort        (IBlockAccess aWorld, int aX, int aY, int aZ, short  aData, INetworkHandler aNetworkHandler, short aID1, short aID2, short[] aCoverIDs, short[] aCoverMetas, short[] aCoverVisuals);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataInteger      (IBlockAccess aWorld, int aX, int aY, int aZ, int    aData, INetworkHandler aNetworkHandler, short aID1, short aID2, short[] aCoverIDs, short[] aCoverMetas, short[] aCoverVisuals);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataLong         (IBlockAccess aWorld, int aX, int aY, int aZ, long   aData, INetworkHandler aNetworkHandler, short aID1, short aID2, short[] aCoverIDs, short[] aCoverMetas, short[] aCoverVisuals);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataByteArray    (IBlockAccess aWorld, int aX, int aY, int aZ, byte[] aData, INetworkHandler aNetworkHandler, short aID1, short aID2, short[] aCoverIDs, short[] aCoverMetas, short[] aCoverVisuals);
		
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveData             (IBlockAccess aWorld, int aX, int aY, int aZ              , INetworkHandler aNetworkHandler, short[] aCoverVisuals, boolean[] aVisualsToSync);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataByte         (IBlockAccess aWorld, int aX, int aY, int aZ, byte   aData, INetworkHandler aNetworkHandler, short[] aCoverVisuals, boolean[] aVisualsToSync);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataShort        (IBlockAccess aWorld, int aX, int aY, int aZ, short  aData, INetworkHandler aNetworkHandler, short[] aCoverVisuals, boolean[] aVisualsToSync);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataInteger      (IBlockAccess aWorld, int aX, int aY, int aZ, int    aData, INetworkHandler aNetworkHandler, short[] aCoverVisuals, boolean[] aVisualsToSync);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataLong         (IBlockAccess aWorld, int aX, int aY, int aZ, long   aData, INetworkHandler aNetworkHandler, short[] aCoverVisuals, boolean[] aVisualsToSync);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataByteArray    (IBlockAccess aWorld, int aX, int aY, int aZ, byte[] aData, INetworkHandler aNetworkHandler, short[] aCoverVisuals, boolean[] aVisualsToSync);
	}
}
