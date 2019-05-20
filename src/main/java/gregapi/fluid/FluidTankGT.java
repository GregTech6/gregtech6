/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregapi.fluid;

import static gregapi.data.CS.*;

import gregapi.util.UT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class FluidTankGT implements IFluidTank {
	private FluidStack mFluid;
	private long mCapacity;
	private boolean mPreventDraining = F, mVoidExcess = F;
	public final FluidTankGT[] AS_ARRAY = new FluidTankGT[] {this};
	
	public FluidTankGT(FluidStack aFluid) {mFluid = aFluid; if (aFluid != null) mCapacity = aFluid.amount;}
	public FluidTankGT(FluidStack aFluid, long aCapacity) {mFluid = aFluid; mCapacity = UT.Code.bindInt(aCapacity);}
	public FluidTankGT(long aCapacity) {this(NF, aCapacity);}
	public FluidTankGT(Fluid aFluid, long aAmount) {this(UT.Fluids.make(aFluid, aAmount));}
	public FluidTankGT(Fluid aFluid, long aAmount, long aCapacity) {this(UT.Fluids.make(aFluid, aAmount), aCapacity);}
	public FluidTankGT(NBTTagCompound aNBT, long aCapacity) {this(UT.Fluids.load(aNBT), aCapacity);}
	
	public FluidTankGT readFromNBT(NBTTagCompound aNBT, String aKey) {if (aNBT.hasKey(aKey)) setFluid(UT.Fluids.load(aNBT.getCompoundTag(aKey))); return this;}
	public NBTTagCompound writeToNBT(NBTTagCompound aNBT, String aKey) {if (mFluid != null && (mPreventDraining || mFluid.amount > 0)) aNBT.setTag(aKey, mFluid.writeToNBT(UT.NBT.make())); else aNBT.removeTag(aKey); return aNBT;}
	public NBTTagCompound writeToNBT(String aKey) {NBTTagCompound aNBT = UT.NBT.make(); if (mFluid != null && (mPreventDraining || mFluid.amount > 0)) aNBT.setTag(aKey, mFluid.writeToNBT(UT.NBT.make())); else aNBT.removeTag(aKey); return aNBT;}
	
	public static NBTTagCompound writeToNBT(String aKey, FluidStack aFluid) {NBTTagCompound rNBT = UT.NBT.make(); if (aFluid != null && aFluid.amount > 0) rNBT.setTag(aKey, aFluid.writeToNBT(UT.NBT.make())); return rNBT;}
	public static NBTTagCompound writeToNBT(NBTTagCompound aNBT, String aKey, FluidStack aFluid) {if (aFluid != null && aFluid.amount > 0) aNBT.setTag(aKey, aFluid.writeToNBT(UT.NBT.make())); else aNBT.removeTag(aKey); return aNBT;}
	
	@Override
	public int fill(FluidStack aFluid, boolean aDoFill) {
		if (aFluid == null) return 0;
		if (aDoFill) {
			if (mFluid == null) {
				mFluid = UT.Fluids.amount(aFluid, Math.min(mCapacity, aFluid.amount));
				return mVoidExcess ? aFluid.amount : mFluid.amount;
			}
			if (!mFluid.isFluidEqual(aFluid)) return 0;
			long tFilled = mCapacity - mFluid.amount;
			if (aFluid.amount < tFilled) {
				mFluid.amount += aFluid.amount;
				tFilled = aFluid.amount;
			} else mFluid.amount = (int)mCapacity;
			return mVoidExcess ? aFluid.amount : (int)tFilled;
		}
		return UT.Code.bindInt(mFluid == null ? mVoidExcess ? aFluid.amount : Math.min(mCapacity, aFluid.amount) : mFluid.isFluidEqual(aFluid) ? mVoidExcess ? aFluid.amount : Math.min(mCapacity - mFluid.amount, aFluid.amount) : 0);
	}
	
	@Override
	public FluidStack drain(int aDrained, boolean aDoDrain) {
		if (mFluid == null || aDrained <= 0) return null;
		if (mFluid.amount < aDrained) aDrained = mFluid.amount;
		FluidStack rFluid = new FluidStack(mFluid, aDrained);
		if (aDoDrain) {
			mFluid.amount -= aDrained;
			if (mFluid.amount <= 0 && !mPreventDraining) mFluid = null;
		}
		return rFluid;
	}
	
	public boolean drainAll(long aDrained) {
		if (mFluid == null || mFluid.amount < aDrained) return F;
		mFluid.amount -= aDrained;
		if (mFluid.amount <= 0 && !mPreventDraining) mFluid = null;
		return T;
	}
	
	public long remove(long aDrained) {
		if (mFluid == null || mFluid.amount <= 0 || aDrained <= 0) return 0;
		if (mFluid.amount < aDrained) aDrained = mFluid.amount;
		mFluid.amount -= aDrained;
		if (mFluid.amount <= 0 && !mPreventDraining) mFluid = null;
		return aDrained;
	}
	
	public long add(long aFilled) {
		if (mFluid == null || aFilled <= 0) return 0;
		if (mFluid.amount + aFilled > mCapacity) {
			if (!mVoidExcess) aFilled = mCapacity - mFluid.amount;
			mFluid.amount = (int)mCapacity;
			return aFilled;
		}
		mFluid.amount += aFilled;
		return aFilled;
	}
	
	public boolean canFillAll(FluidStack aFluid) {return aFluid == null || aFluid.amount <= 0 || (mFluid == null ? mVoidExcess || aFluid.amount <= mCapacity : mFluid.isFluidEqual(aFluid) && (mVoidExcess || mFluid.amount + aFluid.amount <= mCapacity));}
	
	public boolean fillAll(FluidStack aFluid) {
		if (aFluid == null || aFluid.amount <= 0) return T;
		if (mFluid == null) {
			if (aFluid.amount <= mCapacity || mVoidExcess) {
				mFluid = aFluid.copy();
				return T;
			}
			return F;
		}
		if (mFluid.isFluidEqual(aFluid)) {
			if (mFluid.amount + aFluid.amount <= mCapacity) {
				mFluid.amount += aFluid.amount;
				return T;
			}
			if (mVoidExcess) {
				mFluid.amount = (int)mCapacity;
				return T;
			}
		}
		return F;
	}
	
	public boolean fillAll(FluidStack aFluid, int aMultiplier) {
		if (aMultiplier <= 0) return T;
		if (aMultiplier == 1) return fillAll(aFluid);
		if (aFluid == null || aFluid.amount <= 0) return T;
		if (mFluid == null) {
			if (aFluid.amount * aMultiplier <= mCapacity || mVoidExcess) {
				mFluid = aFluid.copy();
				mFluid.amount *= aMultiplier;
				return T;
			}
			return F;
		}
		if (mFluid.isFluidEqual(aFluid)) {
			if (mFluid.amount + aFluid.amount * aMultiplier <= mCapacity) {
				mFluid.amount += aFluid.amount * aMultiplier;
				return T;
			}
			if (mVoidExcess) {
				mFluid.amount = (int)mCapacity;
				return T;
			}
		}
		return F;
	}
	
	public FluidTankGT setEmpty() {mFluid = NF; return this;}
	public FluidTankGT setFluid(FluidStack aFluid) {mFluid = aFluid; return this;}
	public FluidTankGT setCapacity(long aCapacity) {mCapacity = UT.Code.bindInt(aCapacity); return this;}
	public FluidTankGT setPreventDraining() {return setPreventDraining(T);}
	public FluidTankGT setPreventDraining(boolean aPrevent) {mPreventDraining = aPrevent; return this;}
	public FluidTankGT setVoidExcess() {return setVoidExcess(T);}
	public FluidTankGT setVoidExcess(boolean aVoidExcess) {mVoidExcess = aVoidExcess; return this;}
	
	public boolean isEmpty() {return mFluid == null;}
	public boolean isFull() {return mFluid != null && mFluid.amount     >= mCapacity;}
	public boolean isHalf() {return mFluid != null && mFluid.amount * 2 >= mCapacity;}
	
	public boolean contains(Fluid aFluid) {return mFluid.getFluid() == aFluid;}
	public boolean contains(FluidStack aFluid) {return UT.Fluids.equal(mFluid, aFluid);}
	
	public int amount() {return mFluid == null ? 0 : mFluid.amount;}
	public int amount(long aMax) {return mFluid == null || aMax <= 0 ? 0 : mFluid.amount < aMax ? mFluid.amount : (int)aMax;}
	
	public FluidStack get() {return mFluid;}
	public FluidStack get(long aMax) {return mFluid == null || aMax <= 0 ? null : new FluidStack(mFluid, mFluid.amount < aMax ? mFluid.amount : (int)aMax);}
	
	@Override public FluidStack getFluid() {return mFluid;}
	@Override public int getFluidAmount() {return mFluid == null ? 0 : mFluid.amount;}
	@Override public int getCapacity() {return (int)mCapacity;}
	@Override public FluidTankInfo getInfo() {return new FluidTankInfo(mFluid == null ? null : mFluid.copy(), (int)mCapacity);}
}
