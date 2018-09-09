package gregapi.tileentity.tank;

import static gregapi.data.CS.*;

import gregapi.network.INetworkHandler;
import gregapi.network.IPacket;
import gregapi.util.UT;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase09FluidContainerSync extends TileEntityBase08FluidContainer {
	protected short oDisplay = -1;
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return super.onTickCheck(aTimer) || (short)UT.Fluids.id_(mTank) != oDisplay;
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oDisplay = (short)UT.Fluids.id_(mTank);
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		short tDisplay = (short)UT.Fluids.id_(mTank);
		if (aSendAll) return getClientDataPacketByteArray(aSendAll, UT.Code.toByteS(tDisplay, 0), UT.Code.toByteS(tDisplay, 1), (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa));
		return getClientDataPacketByteArray(aSendAll, UT.Code.toByteS(tDisplay, 0), UT.Code.toByteS(tDisplay, 1));
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		if (aData.length > 1) mTank.setFluid(UT.Fluids.make(UT.Code.combine(aData[0], aData[1]), mTank.getCapacity()));
		if (aData.length > 4) mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[2]), UT.Code.unsignB(aData[3]), UT.Code.unsignB(aData[4])});
		return T;
	}
}