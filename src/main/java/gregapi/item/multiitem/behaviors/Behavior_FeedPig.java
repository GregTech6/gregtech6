package gregapi.item.multiitem.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Behavior_FeedPig extends AbstractBehaviorDefault {
	public static final Behavior_FeedPig INSTANCE = new Behavior_FeedPig();
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof EntityPig) {
			if (((EntityAnimal)aEntity).getGrowingAge() == 0 && !((EntityAnimal)aEntity).isInLove()) {
				UT.Entities.consumeCurrentItem(aPlayer);
				((EntityAnimal)aEntity).func_146082_f(aPlayer);
				return T;
			}
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.feed.pig", "Is usable as Pig Food");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.feed.pig"));
		return aList;
	}
}