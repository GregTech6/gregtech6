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

package gregtech.old;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Item_Casings1 extends GT_Item_Casings_Abstract {
	public GT_Item_Casings1(Block par1) {
		super(par1);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack aStack, EntityPlayer aPlayer, @SuppressWarnings("rawtypes") List aList, boolean aF3_H) {
		super.addInformation(aStack, aPlayer, aList, aF3_H);
		switch(getDamage(aStack)) {
		case 12: aList.add(mCoil01Tooltip); break;
		case 13: aList.add(mCoil02Tooltip); break;
		case 14: aList.add(mCoil03Tooltip); break;
		}
	}
}
