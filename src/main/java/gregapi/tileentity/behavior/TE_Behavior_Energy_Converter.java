package gregapi.tileentity.behavior;

import static gregapi.data.CS.*;

import gregapi.data.TD;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.util.UT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TE_Behavior_Energy_Converter extends TE_Behavior {
	public TE_Behavior_Energy_Stats mEnergyIN, mEnergyOUT;
	public TE_Behavior_Energy_Capacitor mStorage;
	public boolean mWasteEnergy = F, mLimitConsumption = F, mOverloaded = F, mEmitsEnergy = F, mCanEmitEnergy = F, mSizeIrrelevant = F, mFast = F;
	public long mMultiplier = 1;
	public byte mFactor = 1;
	
	public TE_Behavior_Energy_Converter(TileEntity aTileEntity, NBTTagCompound aNBT, TE_Behavior_Energy_Capacitor aStorage, TE_Behavior_Energy_Stats aEnergyIN, TE_Behavior_Energy_Stats aEnergyOUT, long aMultiplier, boolean aWasteEnergy, boolean aNegativeOutput) {
		super(aTileEntity, aNBT);
		mStorage = aStorage;
		mEnergyIN = aEnergyIN;
		mEnergyOUT = aEnergyOUT;
		mMultiplier = aMultiplier;
		mWasteEnergy = aWasteEnergy;
		mSizeIrrelevant = TD.Energy.ALL_SIZE_IRRELEVANT.contains(mEnergyOUT.mType);
		mLimitConsumption = TD.Energy.ALL_COMSUMPTION_LIMITED.contains(mEnergyIN.mType);
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
	
	public boolean doConversion(long aTimer, TileEntity aEmitter, byte aSide) {
		long tOutput = UT.Code.units(mStorage.mEnergy, mEnergyIN.mRec, mEnergyOUT.mRec, F);
		mFast = (tOutput > mEnergyOUT.mRec);
		mCanEmitEnergy = (tOutput >= mEnergyOUT.mMin);
		mEmitsEnergy = F;
		if (mCanEmitEnergy) {
			if (tOutput > mEnergyOUT.mMax && mLimitConsumption) tOutput = mEnergyOUT.mMax; else if (tOutput > mEnergyOUT.mMax) {
				if (aTimer > 2) return mOverloaded = T;
				DEB.println("Machine overcharged on startup with: " + mStorage.mEnergy + " " + mEnergyOUT.mType.getLocalisedNameLong());
				mStorage.mEnergy = 0;
				return T;
			}
			if (mSizeIrrelevant) {
				long tEmittedPackets = (SIDES_VALID[aSide] ? ITileEntityEnergy.Util.emitEnergyToSide(mEnergyOUT.mType, aSide, 1, tOutput*mMultiplier, aEmitter) : ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyOUT.mType, 1, tOutput*mMultiplier, (ITileEntityEnergy)aEmitter));
				if (tEmittedPackets > 0) {
					if (!mWasteEnergy) mStorage.mEnergy -= UT.Code.units(tEmittedPackets, mEnergyOUT.mRec*mMultiplier, mEnergyIN.mRec, T);
					mEmitsEnergy = T;
				}
			} else {
				long tEmittedPackets = (SIDES_VALID[aSide] ? ITileEntityEnergy.Util.emitEnergyToSide(mEnergyOUT.mType, aSide, tOutput*mFactor, mMultiplier, aEmitter) : ITileEntityEnergy.Util.emitEnergyToNetwork(mEnergyOUT.mType, tOutput*mFactor, mMultiplier, (ITileEntityEnergy)aEmitter));
				if (tEmittedPackets > 0) {
					if (!mWasteEnergy) mStorage.mEnergy -= UT.Code.units(tEmittedPackets * tOutput, mEnergyOUT.mRec*mMultiplier, mEnergyIN.mRec, T);
					mEmitsEnergy = T;
				}
			}
		}
		if (mWasteEnergy) mStorage.mEnergy = Math.max(0, mStorage.mEnergy-mEnergyIN.mMax);
		return mCanEmitEnergy;
	}
	
	public boolean doBipolar(long aTimer, TileEntity aEmitter, byte aSidePos, byte aSideNeg) {
		long tOutput = UT.Code.units(mStorage.mEnergy, mEnergyIN.mRec, mEnergyOUT.mRec, F);
		mFast = (tOutput > mEnergyOUT.mRec);
		mCanEmitEnergy = (tOutput >= mEnergyOUT.mMin);
		mEmitsEnergy = F;
		if (mCanEmitEnergy) {
			if (tOutput > mEnergyOUT.mMax && mLimitConsumption) tOutput = mEnergyOUT.mMax; else if (tOutput > mEnergyOUT.mMax) {
				if (aTimer > 2) return mOverloaded = T;
				DEB.println("Machine overcharged on startup with: " + mStorage.mEnergy + " " + mEnergyOUT.mType.getLocalisedNameLong());
				mStorage.mEnergy = 0;
				return T;
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
		if (mWasteEnergy) mStorage.mEnergy = Math.max(0, mStorage.mEnergy-mEnergyIN.mMax);
		return mCanEmitEnergy;
	}
	
	public boolean doTwinType(long aTimer, TileEntity aEmitter, byte aSide1, byte aSide2, TE_Behavior_Energy_Stats aEnergyOUT) {
		long tOutput = UT.Code.units(mStorage.mEnergy, mEnergyIN.mRec, mEnergyOUT.mRec, F);
		mFast = (tOutput > mEnergyOUT.mRec);
		mCanEmitEnergy = (tOutput >= mEnergyOUT.mMin);
		mEmitsEnergy = F;
		if (mCanEmitEnergy) {
			if (tOutput > mEnergyOUT.mMax && mLimitConsumption) tOutput = mEnergyOUT.mMax; else if (tOutput > mEnergyOUT.mMax) {
				if (aTimer > 2) return mOverloaded = T;
				DEB.println("Machine overcharged on startup with: " + mStorage.mEnergy + " " + mEnergyOUT.mType.getLocalisedNameLong());
				mStorage.mEnergy = 0;
				return T;
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
		if (mWasteEnergy) mStorage.mEnergy = Math.max(0, mStorage.mEnergy-mEnergyIN.mMax);
		return mCanEmitEnergy;
	}
}