package gregapi.tileentity.computer;

import gregapi.tileentity.ITileEntityUnloadable;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityUSBPort extends ITileEntityUnloadable {
	/**
	 * @return the Data Tag that is contained inside the "gt.usb.data" Tag of an USB Sticks NBTTagCompound. So "tItemStackUSB.getTagCompound().getCompoundTag(NBT_USB_DATA)".
	 */
	public NBTTagCompound getUSBData(byte aSide, int aUSBTier);
	
	/**
	 * sets the Data Tag that is contained inside the "gt.usb.data" Tag of an USB Sticks NBTTagCompound.
	 * @return true if successful.
	 */
	public boolean setUSBData(byte aSide, int aUSBTier, NBTTagCompound aData);
}