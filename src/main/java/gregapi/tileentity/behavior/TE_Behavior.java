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

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TE_Behavior {
	public final TileEntity mTileEntity;
	
	public TE_Behavior(TileEntity aTileEntity, NBTTagCompound aNBT) {
		mTileEntity = aTileEntity;
		if (aNBT != null) load(aNBT);
	}
	
	public void load(NBTTagCompound aNBT) {
		//
	}
	
	public void save(NBTTagCompound aNBT) {
		//
	}
}
