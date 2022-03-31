/**
 * Copyright (c) 2022 GregTech-6 Team
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

package gregapi.tileentity.computer;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import gregapi.util.OM;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase08DataSwitch extends TileEntityBase07Paintable implements IMTE_AddToolTips, ITileEntityUSBPort, ITileEntitySwitchableMode {
	public byte mMode = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_MODE)) mMode = aNBT.getByte(NBT_MODE);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		if (mMode != 0) aNBT.setByte(NBT_MODE, mMode);
	}
	
	static {
		LH.add("gt.multitileentity.data.switch.tooltip.1", "Use adjacent to Machines that require USB Sticks to select Recipes");
		LH.add("gt.multitileentity.data.switch.tooltip.2", "Add USB Cable to Slot where USB Stick in the Recipe would go");
		LH.add("gt.multitileentity.data.switch.tooltip.3", "Clicking with USB Stick will write its Data to the selected Slot.");
		LH.add("gt.multitileentity.data.switch.tooltip.4", "Not usable with Machines that consume USB Sticks, such as Scanners!");
		LH.add("gt.multitileentity.data.switch.tooltip.5", "Requires a Selector Cover to work, otherwise it always selects Slot 0!");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get("gt.multitileentity.data.switch.tooltip.1"));
		aList.add(Chat.CYAN + LH.get("gt.multitileentity.data.switch.tooltip.2"));
		aList.add(Chat.CYAN + LH.get("gt.multitileentity.data.switch.tooltip.3"));
		aList.add(Chat.ORANGE + LH.get("gt.multitileentity.data.switch.tooltip.4"));
		aList.add(Chat.ORANGE + LH.get("gt.multitileentity.data.switch.tooltip.5"));
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && isUseableByPlayerGUI(aPlayer)) {
			ItemStack aHeldItem = aPlayer.inventory.getCurrentItem();
			if (OM.is(OD_USB_STICKS[0], aHeldItem)) {
				if (aHeldItem.hasTagCompound() && aHeldItem.getTagCompound().hasKey(NBT_USB_TIER)) {
					setUSBData(aSide, aHeldItem.getTagCompound().getByte(NBT_USB_TIER), aHeldItem.getTagCompound().getCompoundTag(NBT_USB_DATA));
					playClick();
				} else {
					setUSBData(aSide, 0, null);
					playClick();
				}
			} else {
				openGUI(aPlayer);
			}
		}
		return T;
	}
	
	@Override public int getInventoryStackLimit() {return 1;}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public byte getStateMode() {return mMode;}
	
	@Override
	public byte setStateMode(byte aMode) {
		if (mMode != aMode) {
			mMode = aMode;
			for (byte tSide : ALL_SIDES_VALID) {
				DelegatorTileEntity<IInventory> tDelegator = getAdjacentInventory(tSide);
				if (tDelegator.mTileEntity != null) for (int i = 0, j = tDelegator.mTileEntity.getSizeInventory(); i < j; i++) {
					ItemStack tUSB = tDelegator.mTileEntity.getStackInSlot(i);
					if (OM.is(OD_USB_CABLES[0], tUSB) && (!tUSB.hasTagCompound() || !tUSB.getTagCompound().hasKey(NBT_USB_DIRECTION) || SIDES_EQUAL[tUSB.getTagCompound().getByte(NBT_USB_DIRECTION)][tDelegator.mSideOfTileEntity])) {
						tDelegator.mTileEntity.markDirty();
						break;
					}
				}
			}
		}
		return mMode;
	}
}
