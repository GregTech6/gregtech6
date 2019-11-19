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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Behavior_FeedChocolate extends AbstractBehaviorDefault {
	public static final Behavior_FeedChocolate INSTANCE = new Behavior_FeedChocolate();
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof EntityTameable && ((EntityTameable)aEntity).isTamed()) {
			((EntityLivingBase)aEntity).addPotionEffect(new PotionEffect(Potion.poison.id, 120, 0));
			UT.Entities.consumeCurrentItem(aPlayer);
			return T;
		}
		if (aEntity instanceof EntityHorse) {
			((EntityLivingBase)aEntity).addPotionEffect(new PotionEffect(Potion.poison.id, 120, 0));
			((EntityHorse)aEntity).worldObj.playSoundAtEntity(aEntity, "eating", 1.0F, 1.0F + RNGSUS.nextFloat() - RNGSUS.nextFloat() * 0.2F);
			UT.Entities.consumeCurrentItem(aPlayer);
			return T;
		}
		if (aEntity instanceof EntityAnimal) {
			((EntityLivingBase)aEntity).addPotionEffect(new PotionEffect(Potion.poison.id, 120, 0));
			UT.Entities.consumeCurrentItem(aPlayer);
			return T;
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.feed.chocolate", "Do not feed this to Pets!");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.feed.chocolate"));
		return aList;
	}
}
