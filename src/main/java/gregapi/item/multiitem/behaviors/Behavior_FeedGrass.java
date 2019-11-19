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
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Behavior_FeedGrass extends AbstractBehaviorDefault {
	public static final Behavior_FeedGrass INSTANCE = new Behavior_FeedGrass();
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof EntityCow || aEntity instanceof EntitySheep) {
			if (((EntityAnimal)aEntity).getGrowingAge() == 0 && !((EntityAnimal)aEntity).isInLove()) {
				UT.Entities.consumeCurrentItem(aPlayer);
				((EntityAnimal)aEntity).func_146082_f(aPlayer);
				return T;
			}
		}
		if (aEntity instanceof EntityHorse) {
			boolean tConsume = F;
			if (((EntityHorse)aEntity).getHealth() < ((EntityHorse)aEntity).getMaxHealth()) {
				((EntityHorse)aEntity).heal(1);
				tConsume = T;
			}
			if (!((EntityHorse)aEntity).isAdultHorse()) {
				((EntityHorse)aEntity).addGrowth(30);
				tConsume = T;
			}
			if (tConsume || !((EntityHorse)aEntity).isTame()) {
				tConsume = T;
				((EntityHorse)aEntity).increaseTemper(2);
			}
			if (tConsume) {
				((EntityHorse)aEntity).worldObj.playSoundAtEntity(aEntity, "eating", 1.0F, 1.0F + RNGSUS.nextFloat() - RNGSUS.nextFloat() * 0.2F);
				UT.Entities.consumeCurrentItem(aPlayer);
			}
			return tConsume;
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.feed.grass", "Is usable as Cow, Sheep and Horse Food");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.feed.grass"));
		return aList;
	}
}
