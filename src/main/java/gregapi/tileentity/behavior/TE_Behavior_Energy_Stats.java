package gregapi.tileentity.behavior;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TE_Behavior_Energy_Stats extends TE_Behavior_Energy {
	public long mRec, mMin, mMax;
	public boolean mOverloaded = F;
	public TE_Behavior_Energy_Capacitor mStorage;
	
	public TE_Behavior_Energy_Stats(TileEntity aTileEntity, NBTTagCompound aNBT, TagData aEnergyType, TE_Behavior_Energy_Capacitor aStorage, long aSizeMin, long aSizeRec, long aSizeMax) {
		super(aTileEntity, aNBT, aEnergyType);
		mStorage = aStorage; mMin = Math.abs(aSizeMin); mRec = Math.abs(aSizeRec); mMax = Math.abs(aSizeMax);
	}
	
	public boolean isType(TagData aEnergyType) {return mType == aEnergyType;}
	public long sizeMin(TagData aEnergyType) {return mType != aEnergyType ? 0 : mMin;}
	public long sizeRec(TagData aEnergyType) {return mType != aEnergyType ? 0 : mRec;}
	public long sizeMax(TagData aEnergyType) {return mType != aEnergyType ? 0 : mMax;}
	public Collection<TagData> getTypes() {return mType.AS_LIST;}
	
	public void addToolTips(List aList, ItemStack aStack, boolean aF3_H, String aSides, boolean aEmitting) {
		aList.add((aEmitting ? (Chat.RED + LH.get(LH.ENERGY_OUTPUT)) : (Chat.GREEN + LH.get(LH.ENERGY_INPUT))) + ": " + Chat.WHITE + mRec + " " + mType.getChatFormat() + mType.getLocalisedNameShort() + Chat.WHITE + "/t ("+mMin+" to "+mMax+(UT.Code.stringInvalid(aSides)?"":", "+aSides)+")");
		aList.add(aEmitting ? LH.getToolTipRedstoneFluxEmit(mType) : LH.getToolTipRedstoneFluxAccept(mType));
	}
	
	public long doInject(long aSize, long aAmount, boolean aDoInject) {
		aSize = Math.abs(aSize);
		if (aSize > mMax) {
			if (aDoInject) mOverloaded = T;
			return aAmount;
		}
		if (mStorage == null || mStorage.mEnergy >= mStorage.mCapacity) return 0;
		long tInput = Math.min(mStorage.mCapacity - mStorage.mEnergy, aSize * aAmount), tConsumed = Math.min(aAmount, (tInput/aSize) + (tInput%aSize!=0?1:0));
		if (aDoInject) mStorage.mEnergy += tConsumed * aSize;
		return tConsumed;
	}
}