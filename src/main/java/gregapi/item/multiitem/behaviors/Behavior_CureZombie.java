/**
 * Copyright (c) 2023 GregTech-6 Team
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
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.List;

import static gregapi.data.CS.*;

public class Behavior_CureZombie extends AbstractBehaviorDefault {
	public final int mAverageCureTime;
	public final boolean mNeedsWeakness;
	
	public Behavior_CureZombie(int aAverageCureTime, boolean aNeedsWeakness) {
		mAverageCureTime = aAverageCureTime;
		mNeedsWeakness = aNeedsWeakness;
	}
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof EntityZombie && ((EntityZombie)aEntity).isVillager()) {
			if (!mNeedsWeakness || ((EntityZombie)aEntity).isPotionActive(Potion.weakness)) {
				UT.Entities.consumeCurrentItem(aPlayer);
				if (!(aEntity).worldObj.isRemote) {
					int tCureTime = RNGSUS.nextInt(mAverageCureTime * 2) + 500;
					NBTTagCompound tNBT = UT.NBT.make();
					aEntity.writeToNBT(tNBT);
					tNBT.setInteger("ConversionTime", tCureTime);
					aEntity.readFromNBT(tNBT);
					aEntity.getDataWatcher().updateObject(14, Byte.valueOf((byte)1));
					((EntityZombie)aEntity).removePotionEffect(Potion.weakness.id);
					((EntityZombie)aEntity).addPotionEffect(new PotionEffect(Potion.damageBoost.id, tCureTime, Math.min(((EntityZombie)aEntity).worldObj.difficultySetting.getDifficultyId() - 1, 0)));
					aEntity.worldObj.setEntityState(aEntity, (byte)16);
				}
				return T;
			}
		}
		return F;
	}
	
	static {
		LH.add("gt.behaviour.cure.zombie.strong", "Can be used to cure strong Zombie Villagers");
		LH.add("gt.behaviour.cure.zombie.weak", "Can be used to cure weakened Zombie Villagers");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get(mNeedsWeakness ? "gt.behaviour.cure.zombie.weak" : "gt.behaviour.cure.zombie.strong"));
		return aList;
	}
}
