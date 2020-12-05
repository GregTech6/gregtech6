/**
 * Copyright (c) 2020 GregTech-6 Team
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

import java.util.Map;

import gregapi.data.FL;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.util.UT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class FluidTankGT implements IFluidTank {
	public final FluidTankGT[] AS_ARRAY = new FluidTankGT[] {this};
	
	private FluidStack mFluid;
	private long mCapacity = 0, mAmount = 0;
	private boolean mPreventDraining = F, mVoidExcess = F, mChangedFluids = F;
	/** HashMap of adjustable Tank Sizes based on Fluids if needed. */
	private Map<String, Long> mAdjustableCapacity = null;
	private long mAdjustableMultiplier = 1;
	
	public FluidTankGT(FluidStack aFluid) {mFluid = aFluid; if (aFluid != null) {mCapacity = aFluid.amount; mAmount = aFluid.amount;}}
	public FluidTankGT(FluidStack aFluid, long aCapacity) {mFluid = aFluid; mCapacity = aCapacity; mAmount = (aFluid == null ? 0 : aFluid.amount);}
	public FluidTankGT(FluidStack aFluid, long aAmount, long aCapacity) {mFluid = aFluid; mCapacity = aCapacity; mAmount = (aFluid == null ? 0 : aAmount);}
	public FluidTankGT(long aCapacity) {mCapacity = aCapacity;}
	public FluidTankGT(Fluid aFluid, long aAmount) {this(FL.make(aFluid, aAmount)); mAmount = aAmount;}
	public FluidTankGT(Fluid aFluid, long aAmount, long aCapacity) {this(FL.make(aFluid, aAmount), aCapacity); mAmount = aAmount;}
	public FluidTankGT(NBTTagCompound aNBT, long aCapacity) {mCapacity = aCapacity; if (aNBT != null && !aNBT.hasNoTags()) {mFluid = FL.load_(aNBT); mAmount = (isEmpty() ? 0 : aNBT.hasKey("LAmount") ? aNBT.getLong("LAmount") : mFluid.amount);}}
	public FluidTankGT(NBTTagCompound aNBT, String aKey, long aCapacity) {this(aNBT.hasKey(aKey) ? aNBT.getCompoundTag(aKey) : null, aCapacity);}
	
	public FluidTankGT readFromNBT(NBTTagCompound aNBT, String aKey) {
		if (aNBT.hasKey(aKey)) {
			aNBT = aNBT.getCompoundTag(aKey);
			if (aNBT != null && !aNBT.hasNoTags()) {
				mFluid = FL.load_(aNBT);
				mAmount = (isEmpty() ? 0 : aNBT.hasKey("LAmount") ? aNBT.getLong("LAmount") : mFluid.amount);
			}
		}
		return this;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound aNBT, String aKey) {
		if (mFluid != null && (mPreventDraining || mAmount > 0)) {
			NBTTagCompound tNBT = UT.NBT.make();
			mFluid.amount = UT.Code.bindInt(mAmount);
			aNBT.setTag(aKey, mFluid.writeToNBT(tNBT));
			if (mAmount > Integer.MAX_VALUE) tNBT.setLong("LAmount", mAmount);
		} else {
			aNBT.removeTag(aKey);
		}
		return aNBT;
	}
	
	public NBTTagCompound writeToNBT(String aKey) {
		NBTTagCompound aNBT = UT.NBT.make();
		if (mFluid != null && (mPreventDraining || mAmount > 0)) {
			NBTTagCompound tNBT = UT.NBT.make();
			mFluid.amount = UT.Code.bindInt(mAmount);
			aNBT.setTag(aKey, mFluid.writeToNBT(tNBT));
			if (mAmount > Integer.MAX_VALUE) tNBT.setLong("LAmount", mAmount);
		} else {
			aNBT.removeTag(aKey);
		}
		return aNBT;
	}
	
	public static NBTTagCompound writeToNBT(String aKey, FluidStack aFluid) {
		NBTTagCompound rNBT = UT.NBT.make();
		if (aFluid != null && aFluid.amount > 0) {
			rNBT.setTag(aKey, aFluid.writeToNBT(UT.NBT.make()));
		}
		return rNBT;
	}
	
	public static NBTTagCompound writeToNBT(NBTTagCompound aNBT, String aKey, FluidStack aFluid) {
		if (aFluid != null && aFluid.amount > 0) {
			aNBT.setTag(aKey, aFluid.writeToNBT(UT.NBT.make()));
		} else {
			aNBT.removeTag(aKey);
		}
		return aNBT;
	}
	
	public FluidStack drain(int aDrained) {return drain(aDrained, T);}
	@Override
	public FluidStack drain(int aDrained, boolean aDoDrain) {
		if (isEmpty() || aDrained <= 0) return null;
		if (mAmount < aDrained) aDrained = (int)mAmount;
		FluidStack rFluid = new FluidStack(mFluid, aDrained);
		if (aDoDrain) {
			mAmount -= aDrained;
			if (mAmount <= 0) {
				if (!mPreventDraining) {
					mFluid = null;
					mChangedFluids = T;
				}
				mAmount = 0;
			}
		}
		return rFluid;
	}
	
	public boolean drainAll(long aDrained) {
		if (isEmpty() || mAmount < aDrained) return F;
		mAmount -= aDrained;
		if (mAmount <= 0) {
			if (!mPreventDraining) {
				mFluid = null;
				mChangedFluids = T;
			}
			mAmount = 0;
		}
		return T;
	}
	
	public long remove(long aDrained) {
		if (isEmpty() || mAmount <= 0 || aDrained <= 0) return 0;
		if (mAmount < aDrained) aDrained = mAmount;
		mAmount -= aDrained;
		if (mAmount <= 0) {
			if (!mPreventDraining) {
				mFluid = null;
				mChangedFluids = T;
			}
			mAmount = 0;
		}
		return aDrained;
	}
	
	public long add(long aFilled) {
		if (isEmpty() || aFilled <= 0) return 0;
		long tCapacity = capacity();
		if (mAmount + aFilled > tCapacity) {
			if (!mVoidExcess) aFilled = tCapacity - mAmount;
			mAmount = tCapacity;
			return aFilled;
		}
		mAmount += aFilled;
		return aFilled;
	}
	
	public long add(long aFilled, FluidStack aFluid) {
		if (aFluid == null || aFilled <= 0) return 0;
		if (isEmpty()) {
			mFluid = aFluid.copy();
			mChangedFluids = T;
			mAmount = Math.min(capacity(aFluid), aFilled);
			return mVoidExcess ? aFilled : mAmount;
		}
		return contains(aFluid) ? add(aFilled) : 0;
	}
	
	public int fill(FluidStack aFluid) {return fill(aFluid, T);}
	@Override
	public int fill(FluidStack aFluid, boolean aDoFill) {
		if (aFluid == null) return 0;
		if (aDoFill) {
			if (isEmpty()) {
				mFluid = aFluid.copy();
				mChangedFluids = T;
				mAmount = Math.min(capacity(aFluid), aFluid.amount);
				return mVoidExcess ? aFluid.amount : (int)mAmount;
			}
			if (!contains(aFluid)) return 0;
			long tCapacity = capacity(aFluid), tFilled = tCapacity - mAmount;
			if (aFluid.amount < tFilled) {
				mAmount += aFluid.amount;
				tFilled = aFluid.amount;
			} else mAmount = tCapacity;
			return mVoidExcess ? aFluid.amount : (int)tFilled;
		}
		return UT.Code.bindInt(isEmpty() ? mVoidExcess ? aFluid.amount : Math.min(capacity(aFluid), aFluid.amount) : contains(aFluid) ? mVoidExcess ? aFluid.amount : Math.min(capacity(aFluid) - mAmount, aFluid.amount) : 0);
	}
	
	public boolean canFillAll(FluidStack aFluid) {return aFluid == null || aFluid.amount <= 0 || (isEmpty() ? mVoidExcess || aFluid.amount <= capacity(aFluid) : contains(aFluid) && (mVoidExcess || mAmount + aFluid.amount <= capacity(aFluid)));}
	public boolean canFillAll(long aAmount) {return aAmount <= 0 || mVoidExcess || mAmount + aAmount <= capacity();}
	
	public boolean fillAll(FluidStack aFluid) {
		if (aFluid == null || aFluid.amount <= 0) return T;
		if (isEmpty()) {
			long tCapacity = capacity(aFluid);
			if (aFluid.amount <= tCapacity || mVoidExcess) {
				mFluid = aFluid.copy();
				mChangedFluids = T;
				mAmount = aFluid.amount;
				if (mAmount > tCapacity) mAmount = tCapacity;
				return T;
			}
			return F;
		}
		if (contains(aFluid)) {
			if (mAmount + aFluid.amount <= capacity()) {
				mAmount += aFluid.amount;
				return T;
			}
			if (mVoidExcess) {
				mAmount = capacity();
				return T;
			}
		}
		return F;
	}
	
	public boolean fillAll(FluidStack aFluid, long aMultiplier) {
		if (aMultiplier <= 0) return T;
		if (aMultiplier == 1) return fillAll(aFluid);
		if (aFluid == null || aFluid.amount <= 0) return T;
		if (isEmpty()) {
			long tCapacity = capacity(aFluid);
			if (aFluid.amount * aMultiplier <= tCapacity || mVoidExcess) {
				mFluid = aFluid.copy();
				mChangedFluids = T;
				mAmount = aFluid.amount * aMultiplier;
				if (mAmount > tCapacity) mAmount = tCapacity;
				return T;
			}
			return F;
		}
		if (contains(aFluid)) {
			if (mAmount + aFluid.amount * aMultiplier <= capacity()) {
				mAmount += aFluid.amount * aMultiplier;
				return T;
			}
			if (mVoidExcess) {
				mAmount = capacity();
				return T;
			}
		}
		return F;
	}
	
	/** Resets the Tank Contents entirely */
	public FluidTankGT setEmpty() {mFluid = null; mChangedFluids = T; mAmount = 0; return this;}
	/** Sets Fluid Content, taking Amount from the Fluid Parameter  */
	public FluidTankGT setFluid(FluidStack aFluid) {mFluid = aFluid; mChangedFluids = T; mAmount = (aFluid == null ? 0 : aFluid.amount); return this;}
	/** Sets Fluid Content and Amount */
	public FluidTankGT setFluid(FluidStack aFluid, long aAmount) {mFluid = aFluid; mChangedFluids = T; mAmount = (aFluid == null ? 0 : aAmount); return this;}
	/** Sets the Capacity, and yes it accepts 63 Bit Numbers */
	public FluidTankGT setCapacity(long aCapacity) {if (aCapacity >= 0) mCapacity = aCapacity; return this;}
	/** Always keeps at least 0 Liters of Fluid instead of setting it to null */
	public FluidTankGT setPreventDraining() {return setPreventDraining(T);}
	/** Always keeps at least 0 Liters of Fluid instead of setting it to null */
	public FluidTankGT setPreventDraining(boolean aPrevent) {mPreventDraining = aPrevent; return this;}
	/** Voids any Overlow */
	public FluidTankGT setVoidExcess() {return setVoidExcess(T);}
	/** Voids any Overlow */
	public FluidTankGT setVoidExcess(boolean aVoidExcess) {mVoidExcess = aVoidExcess; return this;}
	/** Sets Tank capacity Map, should it be needed. */
	public FluidTankGT setCapacity(RecipeMap aMap, long aCapacityMultiplier) {mAdjustableCapacity = aMap.mMinInputTankSizes; mAdjustableMultiplier = aCapacityMultiplier; return this;}
	/** Sets Tank capacity Map, should it be needed. */
	public FluidTankGT setCapacity(Map<String, Long> aMap, long aCapacityMultiplier) {mAdjustableCapacity = aMap; mAdjustableMultiplier = aCapacityMultiplier; return this;}
	
	public boolean isEmpty() {return mFluid == null;}
	public boolean isFull () {return mFluid != null && mAmount     >= capacity();}
	public boolean isHalf () {return mFluid != null && mAmount * 2 >= capacity();}
	
	public boolean contains(Fluid aFluid) {return mFluid != null && mFluid.getFluid() == aFluid;}
	public boolean contains(FluidStack aFluid) {return FL.equal(mFluid, aFluid);}
	
	public boolean has(long aAmount) {return mAmount >= aAmount;}
	public boolean has() {return mAmount > 0;}
	
	public boolean check() {if (mChangedFluids) {mChangedFluids = F; return T;} return F;}
	public boolean update() {return mChangedFluids = T;}
	public boolean changed() {return mChangedFluids;}
	
	public long amount() {return isEmpty() ? 0 : mAmount;}
	public long amount(long aMax) {return isEmpty() || aMax <= 0 ? 0 : mAmount < aMax ? mAmount : aMax;}
	
	public long capacity (                 ) {return mAdjustableCapacity == null ? mCapacity : capacity_(mFluid);}
	public long capacity (FluidStack aFluid) {return mAdjustableCapacity == null ? mCapacity : capacity_(aFluid);}
	public long capacity (Fluid      aFluid) {return mAdjustableCapacity == null ? mCapacity : capacity_(aFluid);}
	public long capacity (String     aFluid) {return mAdjustableCapacity == null ? mCapacity : capacity_(aFluid);}
	public long capacity_(FluidStack aFluid) {return aFluid == null ? mCapacity : capacity_(aFluid.getFluid());}
	public long capacity_(Fluid      aFluid) {return aFluid == null ? mCapacity : capacity_(aFluid.getName());}
	public long capacity_(String     aFluid) {
		if (aFluid == null) return mCapacity;
		Long tSize = mAdjustableCapacity.get(aFluid);
		return tSize == null ? Math.max(mAmount, mCapacity) : Math.max(tSize * mAdjustableMultiplier, Math.max(mAmount, mCapacity));
	}
	
	public String name() {return mFluid == null ? null : mFluid.getFluid().getName();}
	public String name(boolean aLocalised) {return FL.name(mFluid, aLocalised);}
	
	public String content() {return content("Empty");}
	public String content(String aEmptyMessage) {return  mFluid == null ? aEmptyMessage : amount() + " L of " + name(T) + " (" + (FL.gas(mFluid) ? "Gaseous" : "Liquid") + ")";}
	public String contentcap() {return mFluid == null ? "Capacity: " + mCapacity + " L" : amount() + " L of " + name(T) + " (" + (FL.gas(mFluid) ? "Gaseous" : "Liquid") + "); Max: "+capacity()+" L)";}
	
	public Fluid fluid() {return mFluid == null ? null : mFluid.getFluid();}
	
	public FluidStack make(int aAmount) {return FL.make(fluid(), aAmount);}
	
	public FluidStack get() {return mFluid;}
	public FluidStack get(long aMax) {return isEmpty() || aMax <= 0 ? null : new FluidStack(mFluid, UT.Code.bindInt(mAmount < aMax ? mAmount : aMax));}
	
	@Override public FluidStack getFluid() {if (mFluid != null) mFluid.amount = UT.Code.bindInt(mAmount); return mFluid;}
	@Override public int getFluidAmount() {return UT.Code.bindInt(mAmount);}
	@Override public int getCapacity() {return UT.Code.bindInt(capacity());}
	@Override public FluidTankInfo getInfo() {return new FluidTankInfo(isEmpty() ? null : mFluid.copy(), UT.Code.bindInt(capacity()));}
}
