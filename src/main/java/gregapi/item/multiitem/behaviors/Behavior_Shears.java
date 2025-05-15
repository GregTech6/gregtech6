/**
 * Copyright (c) 2025 GregTech-6 Team
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

import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IShearable;

import java.util.List;

import static gregapi.data.CS.*;

public class Behavior_Shears extends AbstractBehaviorDefault {
	private final int mCosts;
	
	public Behavior_Shears(int aCosts) {
		mCosts = aCosts;
	}
	
	@Override
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof IShearable) {
			if (aPlayer.worldObj.isRemote) return T;
			if (((IShearable)aEntity).isShearable(aStack, aPlayer.worldObj, (int)aEntity.posX, (int)aEntity.posY, (int)aEntity.posZ) && ((MultiItemTool)aItem).doDamage(aStack, mCosts, aPlayer, F)) {
				int tFortune = UT.NBT.getEnchantmentLevelLootingFortune(aStack);
				String tClass = UT.Reflection.getLowercaseClass(aEntity);
				boolean tDropIncrease = ((tFortune > 0) && ("EntitySheep".equalsIgnoreCase(tClass) || "EntityTFBighorn".equalsIgnoreCase(tClass) || "EntityTaintSheep".equalsIgnoreCase(tClass) || "EntitySheepuff".equalsIgnoreCase(tClass)));
				for (ItemStack tStack : ((IShearable)aEntity).onSheared(aStack, aPlayer.worldObj, (int)aEntity.posX, (int)aEntity.posY, (int)aEntity.posZ, tFortune)) {
					if (tDropIncrease && ST.block(tStack) == Blocks.wool) {
						tStack.stackSize += RNGSUS.nextInt(1+tFortune);
						if (tStack.stackSize > 64) tStack.stackSize = 64;
					}
					ST.give(aPlayer, tStack, F);
				}
				return T;
			}
		}
		return F;
	}
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		return onLeftClickEntity(aItem, aStack, aPlayer, aEntity);
	}
	
	static {
		LH.add("gt.behaviour.shears", "Shaves Sheep, Mooshrooms and alike");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.shears"));
		return aList;
	}
}
