/**
 * Copyright (c) 2019 Gregorius Techneticies
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

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
