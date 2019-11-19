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

package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Behavior_DataStorage16 extends AbstractBehaviorDefault {
	public static final Behavior_DataStorage16 INSTANCE = new Behavior_DataStorage16();
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		if (aStack != null) {
			if (aStack.hasTagCompound() && aStack.getTagCompound().hasKey(NBT_USB_DRIVE)) {
				NBTTagCompound tDrive = aStack.getTagCompound().getCompoundTag(NBT_USB_DRIVE);
				if (tDrive.hasNoTags()) {
					aList.add(LH.Chat.CYAN + "Uncleanly Formatted");
				} else {
					for (byte i = 0; i < 16; i++) {
						NBTTagCompound tUSB = tDrive.getCompoundTag(NBT_USB_DATA+i);
						if (tUSB == null || tUSB.hasNoTags()) {
							aList.add(LH.Chat.DGRAY + "Data Slot "+i+" is Empty");
						} else {
							UT.NBT.getDataToolTip(tUSB, aList, F);
						}
					}
				}
			} else {
				aList.add(LH.Chat.CYAN + "Perfectly Formatted");
			}
		}
		return aList;
	}
}
