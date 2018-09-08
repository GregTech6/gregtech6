package gregapi.item.multiitem.behaviors;

import static gregapi.data.CS.*;

import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Switch_Metadata extends AbstractBehaviorDefault {
	public final int mSwitchIndex;
	
	public Behavior_Switch_Metadata(int aSwitchIndex) {
		mSwitchIndex = aSwitchIndex;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
    	if (aStack != null && (aPlayer == null || aPlayer.isSneaking()) && !aWorld.isRemote) {
    		ST.update_(ST.meta(aStack, mSwitchIndex), aWorld, aX, aY, aZ);
    		return T;
    	}
		return F;
	}
}