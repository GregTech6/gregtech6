package gregtech.tileentity.panels;

import static gregapi.data.CS.*;

import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntityPanelColored extends MultiTileEntityPanel {
	public short mColor = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mColor = aNBT.getByte(NBT_COLOR);
	}
}