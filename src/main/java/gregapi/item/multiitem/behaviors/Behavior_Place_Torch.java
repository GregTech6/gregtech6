package gregapi.item.multiitem.behaviors;

import static gregapi.data.CS.*;

import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class Behavior_Place_Torch extends AbstractBehaviorDefault {
	public static final Behavior_Place_Torch INSTANCE = new Behavior_Place_Torch();
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || aPlayer == null || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
			int tIndex = aPlayer.inventory.mainInventory.length-i-1;
			ItemStack tStack = aPlayer.inventory.mainInventory[tIndex];
			if (ST.valid(tStack) && ST.torch(tStack)) {
				int tOldSize = tStack.stackSize;
				if (WD.grass(aWorld, aX, aY, aZ)) {aSide = SIDE_TOP; aWorld.setBlockToAir(aX, aY--, aZ);}
				if (tStack.tryPlaceItemIntoWorld(aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ)) {
					if (UT.Entities.hasInfiniteItems(aPlayer)) {
						tStack.stackSize = tOldSize;
					} else {
						if (tStack.stackSize <= 0) {
							ForgeEventFactory.onPlayerDestroyItem(aPlayer, tStack);
							aPlayer.inventory.mainInventory[tIndex] = null;
						}
						if (aPlayer.openContainer != null) aPlayer.openContainer.detectAndSendChanges();
					}
			    	return T;
				}
		    	return F;
			}
		}
    	return F;
	}
}