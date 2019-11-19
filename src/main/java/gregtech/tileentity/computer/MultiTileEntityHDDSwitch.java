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

package gregtech.tileentity.computer;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.gui.ContainerClientDefault;
import gregapi.gui.ContainerCommonDefault;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.computer.TileEntityBase08DataSwitch;
import gregapi.util.OM;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityHDDSwitch extends TileEntityBase08DataSwitch {
	static {
		LH.add("gt.multitileentity.hdd.switch.tooltip", "Switches between the 16 Data Slots using Selector Covers");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get("gt.multitileentity.hdd.switch.tooltip"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public NBTTagCompound getUSBData(byte aSide, int aUSBTier) {
		ItemStack tDrive = slot(0);
		if (OM.is(OD_USB_DRIVES[aUSBTier], tDrive) && tDrive.hasTagCompound()) {
			NBTTagCompound tDriveData = tDrive.getTagCompound().getCompoundTag(NBT_USB_DRIVE);
			if (tDriveData.getByte(NBT_USB_TIER+mMode) <= aUSBTier) return tDriveData.hasKey(NBT_USB_DATA+mMode) ? tDriveData.getCompoundTag(NBT_USB_DATA+mMode) : null;
		}
		return null;
	}
	
	@Override
	public boolean setUSBData(byte aSide, int aUSBTier, NBTTagCompound aData) {
		ItemStack tDrive = slot(0);
		if (OM.is(OD_USB_DRIVES[aUSBTier], tDrive)) {
			if (!tDrive.hasTagCompound()) tDrive.setTagCompound(UT.NBT.make());
			NBTTagCompound tDriveData = tDrive.getTagCompound().getCompoundTag(NBT_USB_DRIVE);
			if (aData == null || aData.hasNoTags()) {
				tDriveData.removeTag(NBT_USB_DATA+mMode);
				tDriveData.removeTag(NBT_USB_TIER+mMode);
			} else {
				tDriveData.setTag(NBT_USB_DATA+mMode, aData);
				tDriveData.setByte(NBT_USB_TIER+mMode, (byte)aUSBTier);
			}
			if (tDriveData.hasNoTags()) {
				tDrive.getTagCompound().removeTag(NBT_USB_DRIVE);
			} else {
				tDrive.getTagCompound().setTag(NBT_USB_DRIVE, tDriveData);
			}
			if (tDrive.getTagCompound().hasNoTags()) tDrive.setTagCompound(null);
			return T;
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[(int)UT.Code.bind_(0, 2, aSide)], mRGBa), BlockTextureDefault.get(sOverlays[(int)UT.Code.bind_(0, 2, aSide)])) : null;
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/hdd/switch/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/hdd/switch/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/hdd/switch/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/hdd/switch/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/hdd/switch/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/hdd/switch/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.hdd.switch";}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[1];}
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return OM.is(OD_USB_DRIVES[0], aStack);}
	
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new ContainerClientDefault(aPlayer.inventory, this, aGUIID, RES_PATH_GUI + "machines/HDDSwitch.png");}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return new ContainerCommonDefault(aPlayer.inventory, this, aGUIID);}
}
