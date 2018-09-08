package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Behavior_DataStorage extends AbstractBehaviorDefault {
	public static final Behavior_DataStorage INSTANCE = new Behavior_DataStorage();
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		if (aStack != null && aStack.hasTagCompound()) {
			NBTTagCompound tUSB = aStack.getTagCompound().getCompoundTag(NBT_USB_DATA);
			if (tUSB != null) {
				UT.NBT.getDataToolTip(tUSB, aList, T);
				aList.add(LH.Chat.DGRAY + "Data: USB " + aStack.getTagCompound().getByte(NBT_USB_TIER) + ".0");
			} else {
				aList.add(LH.Chat.CYAN + "This Stick is Empty");
			}
		}
		return aList;
	}
}