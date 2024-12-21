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

package gregapi.fluid;

import gregapi.tileentity.IRateLimitedTank;
import gregapi.util.UT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public class FluidTankGTRateLimited extends FluidTankGT implements IRateLimitedTank {
	protected long mLastInputRate = Long.MAX_VALUE;
	public FluidTankGTRateLimited(){
		super();
	}
	public FluidTankGTRateLimited(long aCapacity){
		super(aCapacity);
	}
	public FluidTankGT readFromNBT(NBTTagCompound aNBT, String aKey) {
		super.readFromNBT(aNBT, aKey);
		mLastInputRate = aNBT.getLong("lastInputRate");
		return this;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound aNBT, String aKey) {
		super.writeToNBT(aNBT, aKey);
		UT.NBT.setNumber(aNBT,"lastInputRate", mLastInputRate);
		return aNBT;
	}

	public NBTTagCompound writeToNBT(String aKey) {
		NBTTagCompound aNBT = super.writeToNBT(aKey);
		UT.NBT.setNumber(aNBT,"lastInputRate", mLastInputRate);
		return aNBT;
	}

	@Override
	public void setLastInputRate(long lastInputRate) {
		mLastInputRate = lastInputRate;
	}

	public long setLastInputRate2(long lastInputRate) {
		setLastInputRate(lastInputRate);
		return lastInputRate;
	}
	public long setLastInputRate2(FluidStack fluidStack) {
		if (fluidStack == null) return 0;
		setLastInputRate(fluidStack.amount);
		return mLastInputRate;
	}
	@Override
	public long getLastInputRate() {
		return mLastInputRate;
	}

	@Override
	public FluidStack drain(int aDrained) {
		return super.drain((int)Math.min(aDrained , getLastInputRate()));
	}

	@Override
	public FluidStack drain(int aDrained, boolean aDoDrain) {
		return super.drain((int)Math.min(aDrained , getLastInputRate()), aDoDrain);
	}

	@Override
	public boolean drainAll(long aDrained) {
		return super.drainAll(Math.min(aDrained, getLastInputRate()));
	}

	@Override
	public int fill(FluidStack aFluid) {
		setLastInputRate2(aFluid);
		return super.fill(aFluid);
	}

	@Override
	public int fill(FluidStack aFluid, boolean aDoFill) {
		setLastInputRate2(aFluid);
		return super.fill(aFluid, aDoFill);
	}

	@Override
	public long add(long aFilled) {
		setLastInputRate2(aFilled);
		return super.add(aFilled);
	}

	@Override
	public long add(long aFilled, FluidStack aFluid) {
		setLastInputRate2(aFilled);
		return super.add(aFilled, aFluid);
	}

	@Override
	public long remove(long aDrained) {
		return super.remove(Math.min(aDrained, getLastInputRate()));
	}


	@Override
	public FluidTankGT setEmpty() {
		mLastInputRate=0;
		return super.setEmpty();
	}
}
