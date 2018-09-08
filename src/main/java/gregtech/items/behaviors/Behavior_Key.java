package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.tileentity.ITileEntityKeyInteractable;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Behavior_Key extends AbstractBehaviorDefault {
	public static final IBehavior<MultiItem> INSTANCE = new Behavior_Key();
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aWorld.isRemote) return F;
		
		DelegatorTileEntity<TileEntity> aTileEntity = WD.te(aWorld, aX, aY, aZ, aSide, T);
		if (aTileEntity.mTileEntity instanceof ITileEntityKeyInteractable) {
			NBTTagCompound tNBT = aStack.getTagCompound();
			if (tNBT == null) tNBT = UT.NBT.make();
			if (!tNBT.hasKey(NBT_KEY)) tNBT.setLong(NBT_KEY, System.nanoTime());
			UT.NBT.set(aStack, tNBT);
			return ((ITileEntityKeyInteractable)aTileEntity.mTileEntity).useKey(aPlayer, aSide, hitX, hitY, hitZ, tNBT.getLong(NBT_KEY));
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.key", "Can open certain regular Locks");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.key"));
		NBTTagCompound tNBT = aStack.getTagCompound();
		if (tNBT != null && tNBT.hasKey(NBT_KEY)) aList.add("Key ID: " + tNBT.getLong(NBT_KEY)); else aList.add("*BLANK*");
		return aList;
	}
}