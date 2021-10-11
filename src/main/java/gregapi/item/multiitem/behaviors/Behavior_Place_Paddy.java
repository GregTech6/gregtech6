/**
 * Copyright (c) 2021 GregTech-6 Team
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

import java.util.List;

import gregapi.data.CS.SFX;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Place_Paddy extends AbstractBehaviorDefault {
	private final int mCosts;
	
	public Behavior_Place_Paddy(int aCosts) {
		mCosts = aCosts;
	}
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || !IL.GrC_Paddy.exists() || aPlayer == null || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		if (aWorld.getBlock(aX, aY, aZ) == Blocks.farmland && ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
			UT.Sounds.send(aWorld, SFX.MC_DIG_GRAVEL, 1.0F, 1.25F, aX, aY, aZ);
			aWorld.setBlock(aX, aY, aZ, IL.GrC_Paddy.block(), aWorld.getBlockMetadata(aX, aY, aZ), 3);
			return T;
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.paddy", "Creates Paddies on Farmland on Rightclick");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		if (IL.GrC_Paddy.exists()) aList.add(LH.get("gt.behaviour.paddy"));
		return aList;
	}
}
