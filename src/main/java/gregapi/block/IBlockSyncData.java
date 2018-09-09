package gregapi.block;

import gregapi.network.INetworkHandler;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 */
public interface IBlockSyncData {
	/** If you have something that causes a Crash here, the Connection gets terminated. */
	public void receiveDataByte				(IBlockAccess aWorld, int aX, int aY, int aZ, byte   aData, INetworkHandler aNetworkHandler);
	/** If you have something that causes a Crash here, the Connection gets terminated. */
	public void receiveDataShort			(IBlockAccess aWorld, int aX, int aY, int aZ, short  aData, INetworkHandler aNetworkHandler);
	/** If you have something that causes a Crash here, the Connection gets terminated. */
	public void receiveDataInteger			(IBlockAccess aWorld, int aX, int aY, int aZ, int    aData, INetworkHandler aNetworkHandler);
	/** If you have something that causes a Crash here, the Connection gets terminated. */
	public void receiveDataLong				(IBlockAccess aWorld, int aX, int aY, int aZ, long   aData, INetworkHandler aNetworkHandler);
	/** If you have something that causes a Crash here, the Connection gets terminated. */
	public void receiveDataByteArray		(IBlockAccess aWorld, int aX, int aY, int aZ, byte[] aData, INetworkHandler aNetworkHandler);
	/** If you have something that causes a Crash here, the Connection gets terminated. */
	public void receiveDataName				(IBlockAccess aWorld, int aX, int aY, int aZ, String aData, INetworkHandler aNetworkHandler);
	
	public static interface IBlockSyncDataAndIDs extends IBlockSyncData {
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveData				(IBlockAccess aWorld, int aX, int aY, int aZ              , INetworkHandler aNetworkHandler, short aID1, short aID2);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataByte			(IBlockAccess aWorld, int aX, int aY, int aZ, byte   aData, INetworkHandler aNetworkHandler, short aID1, short aID2);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataShort		(IBlockAccess aWorld, int aX, int aY, int aZ, short  aData, INetworkHandler aNetworkHandler, short aID1, short aID2);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataInteger		(IBlockAccess aWorld, int aX, int aY, int aZ, int    aData, INetworkHandler aNetworkHandler, short aID1, short aID2);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataLong			(IBlockAccess aWorld, int aX, int aY, int aZ, long   aData, INetworkHandler aNetworkHandler, short aID1, short aID2);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataByteArray	(IBlockAccess aWorld, int aX, int aY, int aZ, byte[] aData, INetworkHandler aNetworkHandler, short aID1, short aID2);
	}
	
	public static interface IBlockSyncDataAndCoversAndIDs extends IBlockSyncDataAndIDs {
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveData				(IBlockAccess aWorld, int aX, int aY, int aZ              , INetworkHandler aNetworkHandler, short aID1, short aID2, short[] aCoverIDs, short[] aCoverMetas, short[] aCoverVisuals);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataByte			(IBlockAccess aWorld, int aX, int aY, int aZ, byte   aData, INetworkHandler aNetworkHandler, short aID1, short aID2, short[] aCoverIDs, short[] aCoverMetas, short[] aCoverVisuals);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataShort		(IBlockAccess aWorld, int aX, int aY, int aZ, short  aData, INetworkHandler aNetworkHandler, short aID1, short aID2, short[] aCoverIDs, short[] aCoverMetas, short[] aCoverVisuals);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataInteger		(IBlockAccess aWorld, int aX, int aY, int aZ, int    aData, INetworkHandler aNetworkHandler, short aID1, short aID2, short[] aCoverIDs, short[] aCoverMetas, short[] aCoverVisuals);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataLong			(IBlockAccess aWorld, int aX, int aY, int aZ, long   aData, INetworkHandler aNetworkHandler, short aID1, short aID2, short[] aCoverIDs, short[] aCoverMetas, short[] aCoverVisuals);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataByteArray	(IBlockAccess aWorld, int aX, int aY, int aZ, byte[] aData, INetworkHandler aNetworkHandler, short aID1, short aID2, short[] aCoverIDs, short[] aCoverMetas, short[] aCoverVisuals);
		
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveData				(IBlockAccess aWorld, int aX, int aY, int aZ              , INetworkHandler aNetworkHandler, short[] aCoverVisuals, boolean[] aVisualsToSync);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataByte			(IBlockAccess aWorld, int aX, int aY, int aZ, byte   aData, INetworkHandler aNetworkHandler, short[] aCoverVisuals, boolean[] aVisualsToSync);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataShort		(IBlockAccess aWorld, int aX, int aY, int aZ, short  aData, INetworkHandler aNetworkHandler, short[] aCoverVisuals, boolean[] aVisualsToSync);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataInteger		(IBlockAccess aWorld, int aX, int aY, int aZ, int    aData, INetworkHandler aNetworkHandler, short[] aCoverVisuals, boolean[] aVisualsToSync);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataLong			(IBlockAccess aWorld, int aX, int aY, int aZ, long   aData, INetworkHandler aNetworkHandler, short[] aCoverVisuals, boolean[] aVisualsToSync);
		/** If you have something that causes a Crash here, the Connection gets terminated. */
		public void receiveDataByteArray	(IBlockAccess aWorld, int aX, int aY, int aZ, byte[] aData, INetworkHandler aNetworkHandler, short[] aCoverVisuals, boolean[] aVisualsToSync);
	}
}