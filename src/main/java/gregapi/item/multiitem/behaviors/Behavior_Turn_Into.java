package gregapi.item.multiitem.behaviors;

import static gregapi.data.CS.*;

import gregapi.code.IItemContainer;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidContainerItem;

public class Behavior_Turn_Into extends AbstractBehaviorDefault {
	public final IItemContainer mTurnInto;
	
	public Behavior_Turn_Into(IItemContainer aTurnInto) {
		mTurnInto = aTurnInto;
	}
	
	@Override
	public boolean isItemStackUsable(MultiItem aItem, ItemStack aStack) {
		if (mTurnInto == null || !mTurnInto.exists() || (aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getFluid(aStack) != null)) return T;
		ST.set(aStack, mTurnInto.get(1), F, F);
		return T;
	}
}