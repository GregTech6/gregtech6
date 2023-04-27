/**
 * Copyright (c) 2023 GregTech-6 Team
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

package gregapi.tileentity.behavior;

import gregapi.data.TD;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.util.UT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import static gregapi.data.CS.*;

public class TE_Behavior_Energy_Converter extends TE_Behavior {
	public TE_Behavior_Energy_Stats mEnergyIN, mEnergyOUT;
	public TE_Behavior_Energy_Capacitor mStorage;
	public boolean mWasteEnergy = F, mLimitConsumption = F, mOverloaded = F, mEmitsEnergy = F, mCanEmitEnergy = F, mSizeIrrelevant = F, mFast = F;
	public long mMultiplier = 1;
	public byte mFactor = 1;
	
	public TE_Behavior_Energy_Converter(TileEntity aTileEntity, NBTTagCompound aNBT, TE_Behavior_Energy_Capacitor aStorage, TE_Behavior_Energy_Stats aEnergyIN, TE_Behavior_Energy_Stats aEnergyOUT, long aMultiplier, boolean aWasteEnergy, boolean aNegativeOutput, boolean aLimitConsumption) {
		super(aTileEntity, aNBT);
		mStorage = aStorage;
		mEnergyIN = aEnergyIN;
		mEnergyOUT = aEnergyOUT;
		mMultiplier = aMultiplier;
		mWasteEnergy = aWasteEnergy;
		mSizeIrrelevant = TD.Energy.ALL_SIZE_IRRELEVANT.contains(mEnergyOUT.mType);
		mLimitConsumption = aLimitConsumption || TD.Energy.ALL_COMSUMPTION_LIMITED.contains(mEnergyIN.mType);
		if (aNegativeOutput) mFactor = -1;
	}
	
	@Override
	public void load(NBTTagCompound aNBT) {
		mEmitsEnergy = aNBT.getBoolean(NBT_ACTIVE_ENERGY);
		mCanEmitEnergy = aNBT.getBoolean(NBT_CAN_ENERGY);
	}
	
	@Override
	public void save(NBTTagCompound aNBT) {
		UT.NBT.setBoolean(aNBT, NBT_ACTIVE_ENERGY, mEmitsEnergy);
		UT.NBT.setBoolean(aNBT, NBT_CAN_ENERGY, mCanEmitEnergy);
	}
	
	public boolean doConversion(long aTimer, TileEntity aEmitter, byte aSide, byte aMode, boolean aNegative) {
		long tOutput = UT.Code.units(mStorage.mEnergy, mEnergyIN.mRec, mEnergyOUT.mRec, F);
		if (aMode > 0) tOutput = Math.min(tOutput, UT.Code.units(mEnergyOUT.mMax, 16, 16-aMode, F));
		mCanEmitEnergy = (tOutput >= mEnergyOUT.mMin);
		mFast = (tOutput > mEnergyOUT.mRec);
		mEmitsEnergy = F;
		if (mCanEmitEnergy) {
			if (tOutput > mEnergyOUT.mMax) {
				if (mLimitConsumption) {
					tOutput = mEnergyOUT.mMax;
				} else {
					if (aTimer > 2) return mOverloaded = T;
					DEB.println("Machine overloaded on Chunkload with: " + mStorage.mEnergy + " " + mEnergyIN.mType.getLocalisedNameLong());
					mStorage.mEnergy = 0;
					return T;
				}
			}
			if (mSizeIrrelevant) {
				long tEmittedPackets = (SIDES_VALID[aSide] ? ITileEntityEnergy.Util.emitEnergyToSide(mEnergyOUT.mType, aSide, aNegative ? -1 : 1,                     tOutput*mMultiplier, aEmitter) : ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyOUT.mType, aNegative ? -1 : 1,                     tOutput*mMultiplier, (ITileEntityEnergy)aEmitter));
				if (tEmittedPackets > 0) {
					if (!mWasteEnergy) mStorage.mEnergy -= UT.Code.units(tEmittedPackets, mEnergyOUT.mRec*mMultiplier, mEnergyIN.mRec, T);
					mEmitsEnergy = T;
				}
			} else {
				long tEmittedPackets = (SIDES_VALID[aSide] ? ITileEntityEnergy.Util.emitEnergyToSide(mEnergyOUT.mType, aSide, aNegative ? -tOutput*mFactor : tOutput*mFactor, mMultiplier, aEmitter) : ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyOUT.mType, aNegative ? -tOutput*mFactor : tOutput*mFactor, mMultiplier, (ITileEntityEnergy)aEmitter));
				if (tEmittedPackets > 0) {
					if (!mWasteEnergy) mStorage.mEnergy -= UT.Code.units(tEmittedPackets * tOutput, mEnergyOUT.mRec*mMultiplier, mEnergyIN.mRec, T);
					mEmitsEnergy = T;
				}
			}
		}
		if (mWasteEnergy) mStorage.mEnergy = Math.max(0, mStorage.mEnergy-UT.Code.units(mEnergyIN.mMax, 16, 16-aMode, T));
		return mCanEmitEnergy;
	}
	
	public boolean doBipolar(long aTimer, TileEntity aEmitter, byte aSidePos, byte aSideNeg, byte aMode) {
		long tOutput = UT.Code.units(mStorage.mEnergy, mEnergyIN.mRec, mEnergyOUT.mRec, F);
		if (aMode > 0) tOutput = Math.min(tOutput, UT.Code.units(mEnergyOUT.mMax, 16, 16-aMode, F));
		mCanEmitEnergy = (tOutput >= mEnergyOUT.mMin);
		mFast = (tOutput > mEnergyOUT.mRec);
		mEmitsEnergy = F;
		if (mCanEmitEnergy) {
			if (tOutput > mEnergyOUT.mMax) {
				if (mLimitConsumption) {
					tOutput = mEnergyOUT.mMax;
				} else {
					if (aTimer > 2) return mOverloaded = T;
					DEB.println("Machine overloaded on Chunkload with: " + mStorage.mEnergy + " " + mEnergyIN.mType.getLocalisedNameLong());
					mStorage.mEnergy = 0;
					return T;
				}
			}
			if (mSizeIrrelevant) {
				long
				tEmittedPackets = ITileEntityEnergy.Util.emitEnergyToSide(mEnergyOUT.mType, aSidePos, 1, tOutput*mMultiplier, aEmitter);
				tEmittedPackets+= ITileEntityEnergy.Util.emitEnergyToSide(mEnergyOUT.mType, aSideNeg, 1,-tOutput*mMultiplier, aEmitter);
				if (tEmittedPackets > 0) {
					if (!mWasteEnergy) mStorage.mEnergy -= UT.Code.units(tEmittedPackets, mEnergyOUT.mRec*mMultiplier, mEnergyIN.mRec, T);
					mEmitsEnergy = T;
				}
			} else {
				long
				tEmittedPackets = ITileEntityEnergy.Util.emitEnergyToSide(mEnergyOUT.mType, aSidePos, tOutput, mMultiplier, aEmitter);
				tEmittedPackets+= ITileEntityEnergy.Util.emitEnergyToSide(mEnergyOUT.mType, aSideNeg,-tOutput, mMultiplier, aEmitter);
				if (tEmittedPackets > 0) {
					if (!mWasteEnergy) mStorage.mEnergy -= UT.Code.units(tEmittedPackets * tOutput, mEnergyOUT.mRec*mMultiplier, mEnergyIN.mRec, T);
					mEmitsEnergy = T;
				}
			}
		}
		if (mWasteEnergy) mStorage.mEnergy = Math.max(0, mStorage.mEnergy-UT.Code.units(mEnergyIN.mMax, 16, 16-aMode, T));
		return mCanEmitEnergy;
	}
	
	public boolean doTwinType(long aTimer, TileEntity aEmitter, byte aSide1, byte aSide2, byte aMode, TE_Behavior_Energy_Stats aEnergyOUT) {
		long tOutput = UT.Code.units(mStorage.mEnergy, mEnergyIN.mRec, mEnergyOUT.mRec, F);
		if (aMode > 0) tOutput = Math.min(tOutput, UT.Code.units(mEnergyOUT.mMax, 16, 16-aMode, F));
		mCanEmitEnergy = (tOutput >= mEnergyOUT.mMin);
		mFast = (tOutput > mEnergyOUT.mRec);
		mEmitsEnergy = F;
		if (mCanEmitEnergy) {
			if (tOutput > mEnergyOUT.mMax) {
				if (mLimitConsumption) {
					tOutput = mEnergyOUT.mMax;
				} else {
					if (aTimer > 2) return mOverloaded = T;
					DEB.println("Machine overloaded on Chunkload with: " + mStorage.mEnergy + " " + mEnergyIN.mType.getLocalisedNameLong());
					mStorage.mEnergy = 0;
					return T;
				}
			}
			if (mSizeIrrelevant) {
				long tEmittedPackets = ITileEntityEnergy.Util.emitEnergyToSide(mEnergyOUT.mType, aSide1, 1, tOutput*mMultiplier, aEmitter);
				if (!mWasteEnergy) mStorage.mEnergy -= UT.Code.units(tEmittedPackets, mEnergyOUT.mRec*mMultiplier, mEnergyIN.mRec, T);
				mEmitsEnergy = (tEmittedPackets > 0);
				
				if (TD.Energy.ALL_SIZE_IRRELEVANT.contains(aEnergyOUT.mType)) {
					tEmittedPackets = ITileEntityEnergy.Util.emitEnergyToSide(aEnergyOUT.mType, aSide2, 1, tOutput*mMultiplier, aEmitter);
					if (!mWasteEnergy) mStorage.mEnergy -= UT.Code.units(tEmittedPackets, aEnergyOUT.mRec*mMultiplier, mEnergyIN.mRec, T);
					mEmitsEnergy = (tEmittedPackets > 0 || mEmitsEnergy);
				} else {
					tEmittedPackets = ITileEntityEnergy.Util.emitEnergyToSide(aEnergyOUT.mType, aSide2, tOutput*mFactor, mMultiplier, aEmitter);
					if (!mWasteEnergy) mStorage.mEnergy -= UT.Code.units(tEmittedPackets * tOutput, aEnergyOUT.mRec*mMultiplier, mEnergyIN.mRec, T);
					mEmitsEnergy = (tEmittedPackets > 0 || mEmitsEnergy);
				}
			} else {
				long tEmittedPackets = ITileEntityEnergy.Util.emitEnergyToSide(mEnergyOUT.mType, aSide1, tOutput*mFactor, mMultiplier, aEmitter);
				if (!mWasteEnergy) mStorage.mEnergy -= UT.Code.units(tEmittedPackets * tOutput, mEnergyOUT.mRec*mMultiplier, mEnergyIN.mRec, T);
				mEmitsEnergy = (tEmittedPackets > 0);
				
				if (TD.Energy.ALL_SIZE_IRRELEVANT.contains(aEnergyOUT.mType)) {
					tEmittedPackets = ITileEntityEnergy.Util.emitEnergyToSide(aEnergyOUT.mType, aSide2, 1, tOutput*mMultiplier, aEmitter);
					if (!mWasteEnergy) mStorage.mEnergy -= UT.Code.units(tEmittedPackets, aEnergyOUT.mRec*mMultiplier, mEnergyIN.mRec, T);
					mEmitsEnergy = (tEmittedPackets > 0 || mEmitsEnergy);
				} else {
					tEmittedPackets = ITileEntityEnergy.Util.emitEnergyToSide(aEnergyOUT.mType, aSide2, tOutput*mFactor, mMultiplier, aEmitter);
					if (!mWasteEnergy) mStorage.mEnergy -= UT.Code.units(tEmittedPackets * tOutput, aEnergyOUT.mRec*mMultiplier, mEnergyIN.mRec, T);
					mEmitsEnergy = (tEmittedPackets > 0 || mEmitsEnergy);
				}
			}
		}
		if (mWasteEnergy) mStorage.mEnergy = Math.max(0, mStorage.mEnergy-UT.Code.units(mEnergyIN.mMax, 16, 16-aMode, T));
		return mCanEmitEnergy;
	}
}
