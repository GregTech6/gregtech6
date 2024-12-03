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

import gregapi.data.FL;
import gregapi.tileentity.IRateLimitedTank;
import gregapi.util.UT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public class FluidTankGTRateLimitedPowerConducting extends FluidTankGTRateLimited {
	public FluidTankGTRateLimitedPowerConducting(long aCapacity) {
		super(aCapacity);
	}

	@Override
	public long getLastInputRate() {
		return FL.powerconducting(getFluid())? mLastInputRate : Long.MAX_VALUE;
	}
	@Override
	public void setLastInputRate(long lastInputRate) {
		if(FL.powerconducting(getFluid()))mLastInputRate = lastInputRate;
	}
}
