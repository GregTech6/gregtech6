/**
 * Copyright (c) 2021 GregTech-6 Team
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

package gregapi.tileentity.energy;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.tileentity.behavior.TE_Behavior_Energy_Stats;
import gregapi.tileentity.machines.ITileEntityAdjacentOnOff;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class TileEntityBase11Twotypes extends TileEntityBase10EnergyConverter implements ITileEntityAdjacentOnOff {
	public TE_Behavior_Energy_Stats mEnergyOUT2 = null;
	
	@Override
	public void readEnergyBehavior(NBTTagCompound aNBT) {
		super.readEnergyBehavior(aNBT);
		mEnergyOUT2 = new TE_Behavior_Energy_Stats(this, aNBT, aNBT.hasKey(NBT_ENERGY_EMITTED_2) ? TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED_2)) : TD.Energy.QU, mStorage, aNBT.getLong(NBT_OUTPUT) / 2, aNBT.getLong(NBT_OUTPUT), aNBT.getLong(NBT_OUTPUT) * 2);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(Chat.BLINKING_GRAY + LH.get(LH.TOOLTIP_REMINDER_EXTENDERS));
	}
	
	@Override
	public void addToolTipsEnergy(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTipsEnergy(aList, aStack, aF3_H);
		mEnergyOUT2.addToolTips(aList, aStack, aF3_H, getLocalisedOutputSide2(), T);
	}
	
	@Override
	public void addToolTipsEfficiency(List<String> aList, ItemStack aStack, boolean aF3_H) {
		LH.addToolTipsEfficiency(aList, aStack, aF3_H, mConverter.mEnergyIN, mConverter.mEnergyOUT, mEnergyOUT2, mConverter.mMultiplier);
	}
	
	@Override
	public void doConversion(long aTimer) {
		mActivity.mActive = mConverter.doTwinType(aTimer, this, mFacing, OPOS[mFacing], mMode, mEnergyOUT2);
		if (mConverter.mOverloaded) {
			overload(mStorage.mEnergy, mConverter.mEnergyOUT.mType);
			mConverter.mOverloaded = F;
			mStorage.mEnergy = 0;
		}
	}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting ? mConverter.mEnergyOUT.isType(aEnergyType) || mEnergyOUT2.isType(aEnergyType) : mConverter.mEnergyIN.isType(aEnergyType);}
	@Override public boolean isEnergyEmittingTo             (TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, T) && (SIDES_INVALID[aSide] || (mConverter.mEnergyOUT.isType(aEnergyType) ? isOutput(aSide) : isOutput2(aSide)));}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return new ArrayListNoNulls<>(F, mConverter.mEnergyIN.mType, mConverter.mEnergyOUT.mType, mEnergyOUT2.mType);}
	
	@Override public boolean isInput  (byte aSide) {return !ALONG_AXIS[aSide][mFacing];}
	@Override public boolean isOutput (byte aSide) {return aSide == mFacing;}
			  public boolean isOutput2(byte aSide) {return aSide == OPOS[mFacing];}
	@Override public String getLocalisedInputSide  () {return LH.get(LH.FACE_ANYBUT_FRONT_BACK);}
	@Override public String getLocalisedOutputSide () {return LH.get(LH.FACE_FRONT);}
			  public String getLocalisedOutputSide2() {return LH.get(LH.FACE_BACK);}
}
