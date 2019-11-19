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

import java.util.List;

import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Behavior_FeedCat extends AbstractBehaviorDefault {
	public static final Behavior_FeedCat INSTANCE = new Behavior_FeedCat();
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof EntityOcelot) {
			for (Object tTask : ((EntityOcelot)aEntity).tasks.taskEntries) if (((EntityAITaskEntry)tTask).action instanceof EntityAITempt && ((EntityAITempt)((EntityAITaskEntry)tTask).action).isRunning()) {
				if (aPlayer.getDistanceSqToEntity(aEntity) < 9.0D) {
					UT.Entities.consumeCurrentItem(aPlayer);
					if (!aPlayer.worldObj.isRemote) {
						if (RNGSUS.nextInt(3) == 0) {
							((EntityOcelot)aEntity).setTamed(T);
							((EntityOcelot)aEntity).setTameSkin(1 + RNGSUS.nextInt(3));
							((EntityOcelot)aEntity).func_152115_b(aPlayer.getUniqueID().toString());
							for (int i = 0; i < 7; ++i) aEntity.worldObj.spawnParticle("heart", aEntity.posX + (RNGSUS.nextFloat() * aEntity.width * 2.0F) - aEntity.width, aEntity.posY + 0.5D + (RNGSUS.nextFloat() * aEntity.height), aEntity.posZ + (RNGSUS.nextFloat() * aEntity.width * 2.0F) - aEntity.width, RNGSUS.nextGaussian() * 0.02D, RNGSUS.nextGaussian() * 0.02D, RNGSUS.nextGaussian() * 0.02D);
							((EntityOcelot)aEntity).worldObj.setEntityState(aEntity, (byte)7);
						} else {
							for (int i = 0; i < 7; ++i) aEntity.worldObj.spawnParticle("smoke", aEntity.posX + (RNGSUS.nextFloat() * aEntity.width * 2.0F) - aEntity.width, aEntity.posY + 0.5D + (RNGSUS.nextFloat() * aEntity.height), aEntity.posZ + (RNGSUS.nextFloat() * aEntity.width * 2.0F) - aEntity.width, RNGSUS.nextGaussian() * 0.02D, RNGSUS.nextGaussian() * 0.02D, RNGSUS.nextGaussian() * 0.02D);
							((EntityOcelot)aEntity).worldObj.setEntityState(aEntity, (byte)6);
						}
					}
				}
				return T;
			}
			if (((EntityOcelot)aEntity).isTamed() && ((EntityOcelot)aEntity).getGrowingAge() == 0 && !((EntityOcelot)aEntity).isInLove()) {
				UT.Entities.consumeCurrentItem(aPlayer);
				((EntityOcelot)aEntity).func_146082_f(aPlayer);
				return T;
			}
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.feed.cat", "Is usable as Cat Food");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.feed.cat"));
		return aList;
	}
}
