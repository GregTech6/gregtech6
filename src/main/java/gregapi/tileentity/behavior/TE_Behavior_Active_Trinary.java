package gregapi.tileentity.behavior;

import static gregapi.data.CS.*;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TE_Behavior_Active_Trinary extends TE_Behavior {
	public long mData = 0;
	public byte mState = 0;
	public boolean mActive = F;
	
	public TE_Behavior_Active_Trinary(TileEntity aTileEntity, NBTTagCompound aNBT) {
		super(aTileEntity, aNBT);
	}
	
	@Override
	public void load(NBTTagCompound aNBT) {
		if (aNBT.hasKey(NBT_ACTIVE)) mActive = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_ACTIVE_DATA)) {mData = aNBT.getLong(NBT_ACTIVE_DATA);}
	}
	
	@Override
	public void save(NBTTagCompound aNBT) {
		if (aNBT.hasKey(NBT_ACTIVE)) mActive = aNBT.getBoolean(NBT_ACTIVE);
		if (aNBT.hasKey(NBT_ACTIVE_DATA)) {mData = aNBT.getLong(NBT_ACTIVE_DATA);}
	}
	
	public boolean check(boolean aStopped) {
		mData <<= 1;
		if (mActive) mData |= 1;
		byte tActiveState = mState;
		if (mData == 0 || aStopped) mState = 0; else if (mData == ~0L) mState = 1; else mState = 2;
		return tActiveState != mState;
	}
}