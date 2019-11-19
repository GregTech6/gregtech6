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

package gregapi.compat.industrialcraft;

import static gregapi.data.CS.*;

import gregapi.compat.CompatBase;
import gregapi.data.MD;
import gregapi.util.ST;
import ic2.api.info.IC2Classic;
import ic2.api.item.IWrenchHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CompatIC2C extends CompatBase implements IWrenchHandler {
	public CompatIC2C() {
		// IC2C Wrench Handler Registration due to it automatically overriding my Wrenches otherwise.
		if (MD.IC2C.mLoaded) IC2Classic.registerWrenchHandler(this);
	}
	
	@Override public boolean supportsItem(ItemStack aWrench) {return ST.valid(aWrench) && ST.isGT_(aWrench);}
	@Override public boolean canWrench(ItemStack aWrench, int aX, int aY, int aZ, EntityPlayer aPlayer) {return F;}
	@Override public void useWrench(ItemStack aWrench, int aX, int aY, int aZ, EntityPlayer aPlayer) {/**/}
}
