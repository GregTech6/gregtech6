package gregapi.tileentity.connectors;

import static gregapi.data.CS.*;

import gregapi.code.HashSetNoNulls;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityRedstoneWire extends ITileEntityConnector {
	public static final long MAX_RANGE = Integer.MAX_VALUE;
	
	public boolean canEmitRedstoneToWire(byte aSide, int aRedstoneID);
	public boolean canAcceptRedstoneFromWire(byte aSide, int aRedstoneID);
	
	public boolean updateRedstone(int aRedstoneID);
	public long getRedstoneMinusLoss(byte aSide, int aRedstoneID);
	public long getRedstoneValue(byte aSide, int aRedstoneID);
	public long getRedstoneLoss(int aRedstoneID);
	
	public static class Util {
		public static void doRedstoneUpdate(ITileEntityRedstoneWire aTileEntity, int aRedstoneID) {
			HashSetNoNulls<ITileEntityRedstoneWire> tSetUpdating = new HashSetNoNulls(F, aTileEntity), tSetNext = new HashSetNoNulls();
			while (!tSetUpdating.isEmpty()) {
				for (ITileEntityRedstoneWire tTileEntity : tSetUpdating) for (byte tSide : ALL_SIDES_VALID) if (tTileEntity.canEmitRedstoneToWire(tSide, aRedstoneID)) {
					DelegatorTileEntity<TileEntity> tDelegator = tTileEntity.getAdjacentTileEntity(tSide);
					if (tDelegator.mTileEntity instanceof ITileEntityRedstoneWire && ((ITileEntityRedstoneWire)tDelegator.mTileEntity).canAcceptRedstoneFromWire(tDelegator.mSideOfTileEntity, aRedstoneID) && ((ITileEntityRedstoneWire)tDelegator.mTileEntity).updateRedstone(aRedstoneID)) {
						tSetNext.add((ITileEntityRedstoneWire)tDelegator.mTileEntity);
					}
				}
				tSetUpdating.clear();
				tSetUpdating.addAll(tSetNext);
				tSetNext.clear();
			}
		}
	}
}