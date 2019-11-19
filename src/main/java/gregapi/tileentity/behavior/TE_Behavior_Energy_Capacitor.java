/**
 * Copyright (c) 2019 Gregorius Techneticies
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

import static gregapi.data.CS.*;

import gregapi.util.UT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TE_Behavior_Energy_Capacitor extends TE_Behavior {
	public long mEnergy = 0, mCapacity = 0;
	
	public TE_Behavior_Energy_Capacitor(TileEntity aTileEntity, NBTTagCompound aNBT, long aCapacity) {
		super(aTileEntity, aNBT);
		mCapacity = aCapacity;
	}
	
	@Override
	public void load(NBTTagCompound aNBT) {
		mEnergy = aNBT.getLong(NBT_ENERGY);
	}
	
	@Override
	public void save(NBTTagCompound aNBT) {
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
	}
}
