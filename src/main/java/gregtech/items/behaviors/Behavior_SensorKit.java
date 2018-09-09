package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Behavior_SensorKit extends AbstractBehaviorDefault {
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aPlayer instanceof EntityPlayerMP) {
			TileEntity tTileEntity = WD.te(aWorld, aX, aY, aZ, T);
			if (tTileEntity instanceof IInventory && !((IInventory)tTileEntity).isUseableByPlayer(aPlayer)) return F;/*
			if (tTileEntity instanceof IGregTechDeviceInformation && ((IGregTechDeviceInformation)tTileEntity).isGivingInformation()) {
				UT.Stacks.set(aStack, IL.NC_SensorCard.get(aStack.stackSize));
				NBTTagCompound tNBT = aStack.getTagCompound();
				if (tNBT == null) tNBT = new NBTTagCompound();
				tNBT.setInteger("x", aX);
				tNBT.setInteger("y", aY);
				tNBT.setInteger("z", aZ);
				aStack.setTagCompound(tNBT);
			}*/
			return T;
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.sensorkit.tooltip", "Used to display Information using the Mod Nuclear Control");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.sensorkit.tooltip"));
		return aList;
	}
}