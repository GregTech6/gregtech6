package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import java.util.List;

import forestry.api.lepidopterology.EnumFlutterType;
import forestry.api.lepidopterology.IButterfly;
import forestry.api.lepidopterology.IButterflyRoot;
import forestry.api.lepidopterology.IEntityButterfly;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Behavior_Scoop extends AbstractBehaviorDefault {
	private final int mCosts;
	
	public Behavior_Scoop(int aCosts) {
		mCosts = aCosts;
	}
	
	@Override
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof IEntityButterfly) {
			if (aPlayer.worldObj.isRemote) return T;
			if (UT.Entities.hasInfiniteItems(aPlayer) || ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer)) {
				Object tButterfly = ((IEntityButterfly)aEntity).getButterfly(), tRoot = ((IButterfly)tButterfly).getGenome().getPrimary().getRoot();
				((IButterflyRoot)tRoot).getBreedingTracker(aEntity.worldObj, aPlayer.getGameProfile()).registerCatch(((IButterfly)tButterfly));
				UT.Inventories.addStackToPlayerInventoryOrDrop(aPlayer, ((IButterflyRoot)tRoot).getMemberStack(((IButterfly)tButterfly).copy(), EnumFlutterType.BUTTERFLY.ordinal()), F);
				aEntity.setDead();
			}
			return T;
		}
		return F;
	}
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		return onLeftClickEntity(aItem, aStack, aPlayer, aEntity);
	}
	
	static {
		LH.add("gt.behaviour.scoop", "Catches Butterflies");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.scoop"));
		return aList;
	}
}