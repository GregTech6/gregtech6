/**
 * Copyright (c) 2020 GregTech-6 Team
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

import gregapi.item.IItemGT;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Switch_Metadata extends AbstractBehaviorDefault {
	public final int mSwitchIndex;
	public final boolean mCheckTarget;
	
	public Behavior_Switch_Metadata(int aSwitchIndex) {
		this(aSwitchIndex, F);
	}
	public Behavior_Switch_Metadata(int aSwitchIndex, boolean aCheckTarget) {
		mSwitchIndex = aSwitchIndex;
		mCheckTarget = aCheckTarget;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aStack != null && (aPlayer == null || aPlayer.isSneaking()) && !aWorld.isRemote) {
			if (mCheckTarget) {
				Block aBlock = WD.block(aWorld, aX, aY, aZ, T);
				if (aBlock instanceof IItemGT) {
					ST.update_(ST.meta_(aStack, mSwitchIndex), aWorld, aX, aY, aZ);
					return T;
				}
				if (WD.te(aWorld, aX, aY, aZ, T) == null) {
					ST.update_(ST.meta_(aStack, mSwitchIndex), aWorld, aX, aY, aZ);
					return T;
				}
				return F;
			}
			ST.update_(ST.meta_(aStack, mSwitchIndex), aWorld, aX, aY, aZ);
			return T;
		}
		return F;
	}
}
