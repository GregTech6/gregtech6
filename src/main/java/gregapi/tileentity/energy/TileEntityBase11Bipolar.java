package gregapi.tileentity.energy;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.data.TD;
import gregapi.tileentity.machines.ITileEntityAdjacentOnOff;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;

public abstract class TileEntityBase11Bipolar extends TileEntityBase10EnergyConverter implements ITileEntityAdjacentOnOff {
	@Override
	public void addToolTipsEfficiency(List aList, ItemStack aStack, boolean aF3_H) {
		if (TD.Energy.ALL_EU.contains(mConverter.mEnergyIN.mType)) {
			if (TD.Energy.ALL_EU.contains(mConverter.mEnergyOUT.mType)) {
				aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, mConverter.mEnergyIN.mRec, mConverter.mEnergyOUT.mRec*2, F)));
			} else {
				if (mConverter.mEnergyOUT.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, mConverter.mEnergyIN.mRec*4, mConverter.mEnergyOUT.mRec*2, F)));
			}
		} else {
			if (TD.Energy.ALL_EU.contains(mConverter.mEnergyOUT.mType) && mConverter.mEnergyIN.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, mConverter.mEnergyIN.mRec, mConverter.mEnergyOUT.mRec*8, F)));
		}
	}
	
    @Override
    public void doConversion(long aTimer) {
		mActivity.mActive = mConverter.doBipolar(aTimer, this, mFacing, OPPOSITES[mFacing]);
		if (mConverter.mOverloaded) {
			overload(mStorage.mEnergy, mConverter.mEnergyOUT.mType);
			mConverter.mOverloaded = F;
			mStorage.mEnergy = 0;
		}
    }
    
	@Override public boolean isInput (byte aSide) {return !ALONG_AXIS[aSide][mFacing];}
	@Override public boolean isOutput(byte aSide) {return  ALONG_AXIS[aSide][mFacing];}
	@Override public String getLocalisedInputSide () {return LH.get(LH.FACE_ANYBUT_FRONT_BACK);}
	@Override public String getLocalisedOutputSide() {return LH.get(LH.FACE_FRONT_BACK);}
}