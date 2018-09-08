package gregapi.item.multiitem.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Behavior_FeedDog extends AbstractBehaviorDefault {
	public static final Behavior_FeedDog INSTANCE = new Behavior_FeedDog();
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof EntityWolf) {
			if (((EntityWolf)aEntity).isTamed()) {
				if (((EntityWolf)aEntity).getDataWatcher().getWatchableObjectFloat(18) < 20.0F) {
					UT.Entities.consumeCurrentItem(aPlayer);
					((EntityWolf)aEntity).heal(ST.food(aStack));
					return T;
				}
				
				if (((EntityWolf)aEntity).getGrowingAge() == 0 && !((EntityWolf)aEntity).isInLove()) {
					UT.Entities.consumeCurrentItem(aPlayer);
					((EntityWolf)aEntity).func_146082_f(aPlayer);
					return T;
				}
			}
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.feed.dog", "Is usable as Dog Food");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.feed.dog"));
		return aList;
	}
}