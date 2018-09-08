package gregapi.tileentity.behavior;

import gregapi.code.TagData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TE_Behavior_Energy extends TE_Behavior {
	public final TagData mType;
	
	public TE_Behavior_Energy(TileEntity aTileEntity, NBTTagCompound aNBT, TagData aEnergyType) {
		super(aTileEntity, aNBT);
		mType = aEnergyType;
	}
}