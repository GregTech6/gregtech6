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

package gregapi.tileentity.connectors;

import static gregapi.data.CS.*;

import gregapi.code.HashSetNoNulls;
import gregapi.data.TD;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.EnergyCompat;
import gregapi.tileentity.energy.ITileEntityEnergy;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 * 
 * Experimental thingy, likely unfinished.
 */
public class MultiTileEntityWireBundledElectric extends MultiTileEntityWireElectric {
	@Override
	@SuppressWarnings("deprecation")
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		
		if (aIsServerSide) {
			mWattageLast = mTransferredWattage;
			mTransferredWattage = 0;
			mTransferredAmperes = 0;
			if (EnergyCompat.IC_ENERGY) for (byte tSide : ALL_SIDES_VALID) if (canAcceptEnergyFrom(tSide)) {
				DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
				if (!(tDelegator.mTileEntity instanceof gregapi.tileentity.ITileEntityEnergy)) {
					TileEntity tEmitter = tDelegator.mTileEntity instanceof IEnergyTile || EnergyNet.instance == null ? tDelegator.mTileEntity : EnergyNet.instance.getTileEntity(tDelegator.mWorld, tDelegator.mX, tDelegator.mY, tDelegator.mZ);
					if (tEmitter instanceof IEnergySource && ((IEnergySource)tEmitter).emitsEnergyTo(this, tDelegator.getForgeSideOfTileEntity())) {
						long tEU = (long)((IEnergySource)tEmitter).getOfferedEnergy();
						if (transferElectricity(tSide, tEU, 1, -1, new HashSetNoNulls<TileEntity>(F, this)) > 0) ((IEnergySource)tEmitter).drawEnergy(tEU);
					}
				}
			}
		}
	}
	
	@Override
	public long transferElectricity(byte aSide, long aVoltage, long aAmperage, long aChannel, HashSetNoNulls<TileEntity> aAlreadyPassed) {
		long rUsedAmperes = 0;
		
		if (mTimer < 1) return rUsedAmperes;
		
		if (Math.abs(aVoltage) > mLoss) {
			if (aVoltage > 0) aVoltage -= mLoss; else aVoltage += mLoss;
			for (byte tSide : ALL_SIDES_VALID_BUT[aSide]) if (canEmitEnergyTo(tSide)) {
				if (aAmperage <= rUsedAmperes) break;
				DelegatorTileEntity<TileEntity> tDelegator = getAdjacentTileEntity(tSide);
				if (aAlreadyPassed.add(tDelegator.mTileEntity)) {
					if (tDelegator.mTileEntity instanceof MultiTileEntityWireElectric) {
						if (((MultiTileEntityWireElectric)tDelegator.mTileEntity).isEnergyAcceptingFrom(TD.Energy.EU, tDelegator.mSideOfTileEntity, F)) {
							rUsedAmperes += ((MultiTileEntityWireElectric)tDelegator.mTileEntity).transferElectricity(tDelegator.mSideOfTileEntity, aVoltage, aAmperage-rUsedAmperes, aChannel, aAlreadyPassed);
						}
					} else {
						rUsedAmperes += ITileEntityEnergy.Util.insertEnergyInto(TD.Energy.EU, aVoltage, aAmperage-rUsedAmperes, this, tDelegator);
					}
				}
			}
		} else {
			aVoltage = 0;
		}
		return addToEnergyTransferred(aVoltage, rUsedAmperes)?rUsedAmperes:aAmperage;
	}
	
	@Override
	public boolean addToEnergyTransferred(long aVoltage, long aAmperage) {
		mTransferredAmperes += aAmperage;
		mTransferredWattage += Math.abs(aVoltage * aAmperage);
		if (mTimer > 1 && (Math.abs(aVoltage) > mVoltage || mTransferredAmperes > mAmperage)) {
			setToFire();
			return F;
		}
		return T;
	}
	
	@Override public String getTileEntityName               () {return "gt.multitileentity.connector.wire.bundled.electric";}
}
