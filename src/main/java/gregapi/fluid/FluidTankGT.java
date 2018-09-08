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
	
	public FluidTankGT(FluidStack aFluid) {mFluid = aFluid; if (aFluid != null) mCapacity = aFluid.amount;}
	public FluidTankGT(FluidStack aFluid, long aCapacity) {mFluid = aFluid; mCapacity = UT.Code.bindInt(aCapacity);}
	public FluidTankGT(long aCapacity) {this(NF, aCapacity);}
	public FluidTankGT(Fluid aFluid, long aAmount) {this(UT.Fluids.make(aFluid, aAmount));}
	public FluidTankGT(Fluid aFluid, long aAmount, long aCapacity) {this(UT.Fluids.make(aFluid, aAmount), aCapacity);}
	public FluidTankGT(NBTTagCompound aNBT, long aCapacity) {this(UT.Fluids.load(aNBT), aCapacity);}
	
	public FluidTankGT readFromNBT(NBTTagCompound aNBT, String aKey) {if (aNBT.hasKey(aKey)) setFluid(UT.Fluids.load(aNBT.getCompoundTag(aKey))); return this;}
	public NBTTagCompound writeToNBT(NBTTagCompound aNBT, String aKey) {if (mFluid != null && (mPreventDraining || mFluid.amount > 0)) aNBT.setTag(aKey, mFluid.writeToNBT(UT.NBT.make())); else aNBT.removeTag(aKey); return aNBT;}
	public NBTTagCompound writeToNBT(String aKey) {NBTTagCompound aNBT = UT.NBT.make(); if (mFluid != null && (mPreventDraining || mFluid.amount > 0)) aNBT.setTag(aKey, mFluid.writeToNBT(UT.NBT.make())); else aNBT.removeTag(aKey); return aNBT;}
	
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
	
	public FluidTankGT setFluid(FluidStack aFluid) {mFluid = aFluid; return this;}
	public FluidTankGT setCapacity(long aCapacity) {mCapacity = UT.Code.bindInt(aCapacity); return this;}
	public FluidTankGT setPreventDraining() {return setPreventDraining(T);}
	public FluidTankGT setPreventDraining(boolean aPrevent) {mPreventDraining = aPrevent; return this;}
	public FluidTankGT setVoidExcess() {return setVoidExcess(T);}
	public FluidTankGT setVoidExcess(boolean aVoidExcess) {mVoidExcess = aVoidExcess; return this;}
	
	public boolean isEmpty() {return mFluid == null;}
	public boolean isFull() {return mFluid != null && mFluid.amount >= mCapacity;}
	
	@Override public FluidStack getFluid() {return mFluid;}
	@Override public int getFluidAmount() {return mFluid == null ? 0 : mFluid.amount;}
	@Override public int getCapacity() {return (int)mCapacity;}
	@Override public FluidTankInfo getInfo() {return new FluidTankInfo(mFluid == null ? null : mFluid.copy(), (int)mCapacity);}
}