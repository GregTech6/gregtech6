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
public class MultiTileEntityUSBSwitch extends TileEntityBase08DataSwitch {
	static {
		LH.add("gt.multitileentity.usb.switch.tooltip", "Switches between 16 different USB Sticks using Selector Covers");
	}
	
	@Override
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN + LH.get("gt.multitileentity.usb.switch.tooltip"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public NBTTagCompound getUSBData(byte aSide, int aUSBTier) {
		ItemStack tUSB = slot(mMode);
		if (OM.is(OD_USB_STICKS[aUSBTier], tUSB) && tUSB.hasTagCompound() && tUSB.getTagCompound().getByte(NBT_USB_TIER) <= aUSBTier) {
			return tUSB.getTagCompound().getCompoundTag(NBT_USB_DATA);
		}
		return null;
	}
	
	@Override
	public boolean setUSBData(byte aSide, int aUSBTier, NBTTagCompound aData) {
		ItemStack tUSB = slot(mMode);
		if (OM.is(OD_USB_STICKS[aUSBTier], tUSB)) {
			if (!tUSB.hasTagCompound()) tUSB.setTagCompound(UT.NBT.make());
			if (aData == null || aData.hasNoTags()) {
				tUSB.getTagCompound().removeTag(NBT_USB_DATA);
				tUSB.getTagCompound().removeTag(NBT_USB_TIER);
			} else {
				tUSB.getTagCompound().setTag(NBT_USB_DATA, aData);
				tUSB.getTagCompound().setByte(NBT_USB_TIER, (byte)aUSBTier);
			}
			if (tUSB.getTagCompound().hasNoTags()) tUSB.setTagCompound(null);
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
		new Textures.BlockIcons.CustomIcon("machines/usb/switch/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/usb/switch/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/usb/switch/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/usb/switch/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/usb/switch/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/usb/switch/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.usb.hub";}
	
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[16];}
	@Override public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {return OM.is(OD_USB_STICKS[0], aStack);}
	
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new ContainerClientDefault(aPlayer.inventory, this, RES_PATH_GUI + "machines/USBSwitch.png");}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return new ContainerCommonDefault(aPlayer.inventory, this);}
}