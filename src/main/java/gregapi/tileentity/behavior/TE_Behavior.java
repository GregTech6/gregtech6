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