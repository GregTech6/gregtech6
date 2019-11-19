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

package gregapi.tileentity.tank;

import static gregapi.data.CS.*;

import gregapi.data.FL;
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
		return super.onTickCheck(aTimer) || FL.id_(mTank) != oDisplay;
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oDisplay = FL.id_(mTank);
	}
	
	@Override
	public IPacket getClientDataPacket(boolean aSendAll) {
		short tDisplay = FL.id_(mTank);
		if (aSendAll) return getClientDataPacketByteArray(aSendAll, UT.Code.toByteS(tDisplay, 0), UT.Code.toByteS(tDisplay, 1), (byte)UT.Code.getR(mRGBa), (byte)UT.Code.getG(mRGBa), (byte)UT.Code.getB(mRGBa));
		return getClientDataPacketByteArray(aSendAll, UT.Code.toByteS(tDisplay, 0), UT.Code.toByteS(tDisplay, 1));
	}
	
	@Override
	public boolean receiveDataByteArray(byte[] aData, INetworkHandler aNetworkHandler) {
		if (aData.length > 1) mTank.setFluid(FL.make(UT.Code.combine(aData[0], aData[1]), mTank.getCapacity()));
		if (aData.length > 4) mRGBa = UT.Code.getRGBInt(new short[] {UT.Code.unsignB(aData[2]), UT.Code.unsignB(aData[3]), UT.Code.unsignB(aData[4])});
		return T;
	}
}
