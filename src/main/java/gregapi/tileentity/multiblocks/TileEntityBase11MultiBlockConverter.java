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

package gregapi.tileentity.multiblocks;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.TD;
import gregapi.tileentity.behavior.TE_Behavior_Active_Trinary;
import gregapi.tileentity.behavior.TE_Behavior_Energy_Capacitor;
import gregapi.tileentity.behavior.TE_Behavior_Energy_Converter;
import gregapi.tileentity.behavior.TE_Behavior_Energy_Stats;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.Collection;
import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 * 
 * Some Defaults for MultiBlock Machines.
 */
public abstract class TileEntityBase11MultiBlockConverter extends TileEntityBase10MultiBlockBase implements ITileEntityRunningActively, ITileEntityEnergy, IMultiBlockEnergy {
	protected boolean mStopped = F;
	protected byte mExplosionPrevention = 0;
	
	public TE_Behavior_Energy_Stats mEnergyIN = null, mEnergyOUT = null;
	public TE_Behavior_Energy_Capacitor mStorage = null;
	public TE_Behavior_Energy_Converter mConverter = null;
	public TE_Behavior_Active_Trinary mActivity = null;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_STOPPED)) mStopped = aNBT.getBoolean(NBT_STOPPED);
		mActivity = new TE_Behavior_Active_Trinary(this, aNBT);
		readEnergyBehavior(aNBT);
		readEnergyConverter(aNBT);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mStopped);
		mActivity.save(aNBT);
		writeEnergyBehavior(aNBT);
	}
	
	public void readEnergyBehavior(NBTTagCompound aNBT) {
		long tInput = aNBT.getLong(NBT_INPUT), tOutput = aNBT.getLong(NBT_OUTPUT);
		mStorage    = new TE_Behavior_Energy_Capacitor  (this, aNBT, tInput * 2);
		mEnergyIN   = new TE_Behavior_Energy_Stats      (this, aNBT, aNBT.hasKey(NBT_ENERGY_ACCEPTED) ? TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED)) : TD.Energy.QU   , mStorage, tInput <= 16 ? 1 : tInput / 2, tInput, tInput * 2);
		mEnergyOUT  = new TE_Behavior_Energy_Stats      (this, aNBT, aNBT.hasKey(NBT_ENERGY_EMITTED ) ? TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED )) : mEnergyIN.mType, mStorage, tOutput / 2, tOutput, tOutput * 2);
	}
	
	public void readEnergyConverter(NBTTagCompound aNBT) {
		mConverter  = new TE_Behavior_Energy_Converter  (this, aNBT, mStorage, mEnergyIN, mEnergyOUT, aNBT.hasKey(NBT_MULTIPLIER) ? aNBT.getLong(NBT_MULTIPLIER) : 1, aNBT.getBoolean(NBT_WASTE_ENERGY), F, aNBT.getBoolean(NBT_LIMIT_CONSUMPTION));
	}
	
	public void writeEnergyBehavior(NBTTagCompound aNBT) {
		mStorage.save(aNBT);
		mConverter.save(aNBT);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		addToolTipsEnergy(aList, aStack, aF3_H);
		addToolTipsEfficiency(aList, aStack, aF3_H);
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	public void addToolTipsEnergy(List<String> aList, ItemStack aStack, boolean aF3_H) {
		mEnergyIN .addToolTips(aList, aStack, aF3_H, null, F);
		mEnergyOUT.addToolTips(aList, aStack, aF3_H, null, T);
	}
	
	public void addToolTipsEfficiency(List<String> aList, ItemStack aStack, boolean aF3_H) {
		LH.addToolTipsEfficiency(aList, aStack, aF3_H, mConverter);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && checkStructure(F)) {
			doConversion(aTimer);
			if (mTimer % 600 == 5 && !mActivity.mActive && mExplosionPrevention > 0) mExplosionPrevention--;
		}
	}
	
	@Override public boolean onTickCheck(long aTimer) {return mActivity.check(mStopped) || super.onTickCheck(aTimer);}
	@Override public void setVisualData(byte aData) {mActivity.mState = aData;}
	@Override public byte getVisualData() {return mActivity.mState;}
	
	public abstract TileEntity getEmittingTileEntity();
	public abstract byte getEmittingSide();
	
	public void doConversion(long aTimer) {
		mActivity.mActive = mConverter.doConversion(aTimer, getEmittingTileEntity(), getEmittingSide(), (byte)0, F);
		if (mConverter.mOverloaded) {
			overload(mStorage.mEnergy, mEnergyOUT.mType);
			mConverter.mOverloaded = F;
			mStorage.mEnergy = 0;
		}
	}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		long tConsumed = mEnergyIN.doInject(aSize, aAmount, aDoInject);
		if (mEnergyIN.mOverloaded) {
			overload(aSize, aEnergyType);
			mEnergyIN.mOverloaded = F;
		}
		return tConsumed;
	}
	
	public void overload(long aSize, TagData aEnergyType) {
		if (mExplosionPrevention < 100) {
			if (mTimer < 100) DEB.println("Machine overloaded on startup with: " + aSize + " " + aEnergyType.getLocalisedNameLong());
			mExplosionPrevention++;
			mStorage.mEnergy = 0;
		} else {
			overcharge(aSize, aEnergyType);
		}
	}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting ? aEnergyType == mEnergyOUT.mType : aEnergyType == mEnergyIN.mType;}
	@Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || (!mStopped && (mConverter.mWasteEnergy || (mConverter.mEmitsEnergy == mConverter.mCanEmitEnergy)))) && (SIDES_INVALID[aSide] || isInput (aSide)) && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	@Override public boolean isEnergyEmittingTo             (TagData aEnergyType, byte aSide, boolean aTheoretical) {return                                                                                                                         (SIDES_INVALID[aSide] || isOutput(aSide)) && super.isEnergyEmittingTo   (aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeOutputMin            (TagData aEnergyType, byte aSide) {return mEnergyOUT.sizeMin(aEnergyType);}
	@Override public long getEnergySizeOutputRecommended    (TagData aEnergyType, byte aSide) {return mEnergyOUT.sizeRec(aEnergyType);}
	@Override public long getEnergySizeOutputMax            (TagData aEnergyType, byte aSide) {return mEnergyOUT.sizeMax(aEnergyType);}
	@Override public long getEnergySizeInputMin             (TagData aEnergyType, byte aSide) {return mEnergyIN .sizeMin(aEnergyType);}
	@Override public long getEnergySizeInputRecommended     (TagData aEnergyType, byte aSide) {return mEnergyIN .sizeRec(aEnergyType);}
	@Override public long getEnergySizeInputMax             (TagData aEnergyType, byte aSide) {return mEnergyIN .sizeMax(aEnergyType);}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return new ArrayListNoNulls<>(F, mEnergyIN.mType, mEnergyOUT.mType);}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
	@Override public boolean getStateRunningPossible() {return T;}
	@Override public boolean getStateRunningPassively() {return mActivity.mActive;}
	@Override public boolean getStateRunningActively() {return mConverter.mEmitsEnergy;}
	public boolean setStateOnOff(boolean aOnOff) {mStopped = !aOnOff; return !mStopped;}
	public boolean getStateOnOff() {return !mStopped;}
	
	public boolean isInput (byte aSide) {return aSide == mFacing;}
	public boolean isOutput(byte aSide) {return aSide != mFacing;}
}
