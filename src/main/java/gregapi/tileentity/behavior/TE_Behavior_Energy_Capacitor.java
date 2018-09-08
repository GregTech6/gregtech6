package gregapi.tileentity.behavior;

import static gregapi.data.CS.*;

import gregapi.util.UT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TE_Behavior_Energy_Capacitor extends TE_Behavior {
	public long mEnergy = 0, mCapacity = 0;
	
	public TE_Behavior_Energy_Capacitor(TileEntity aTileEntity, NBTTagCompound aNBT, long aCapacity) {
		super(aTileEntity, aNBT);
		mCapacity = aCapacity;
	}
	
	@Override
	public void load(NBTTagCompound aNBT) {
		mEnergy = aNBT.getLong(NBT_ENERGY);
	}
	
	@Override
	public void save(NBTTagCompound aNBT) {
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
	}
}