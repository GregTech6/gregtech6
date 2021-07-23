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

package gregapi.player;

import static gregapi.data.CS.*;

import gregapi.code.ArrayListNoNulls;
import gregapi.damage.DamageSources;
import gregapi.data.CS.PotionsGT;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * @author Gregorius Techneticies
 */
public class EntityFoodTracker implements IExtendedEntityProperties {
	public static ArrayListNoNulls<EntityFoodTracker> TICK_LIST = new ArrayListNoNulls<>();
	
	public byte mAlcohol = 0, mCaffeine = 0, mDehydration = 0, mSugar = 0, mFat = 0;
	public final EntityLivingBase mEntity;
	
	public EntityFoodTracker(EntityLivingBase aEntity) {
		mEntity = aEntity;
	}
	
	@Override
	public void saveNBTData(NBTTagCompound aNBT) {
		NBTTagCompound tNBT = UT.NBT.make();
		if (mAlcohol        != 0) tNBT.setByte("a", mAlcohol);
		if (mCaffeine       != 0) tNBT.setByte("c", mCaffeine);
		if (mSugar          != 0) tNBT.setByte("s", mSugar);
		if (mDehydration    != 0) tNBT.setByte("d", mDehydration);
		if (mFat            != 0) tNBT.setByte("f", mFat);
		aNBT.setTag("gt.properties.food", tNBT.hasNoTags() ? null : tNBT);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound aNBT) {
		if (!aNBT.hasKey("gt.properties.food")) return;
		NBTTagCompound tNBT = aNBT.getCompoundTag("gt.properties.food");
		mAlcohol        = tNBT.getByte("a");
		mCaffeine       = tNBT.getByte("c");
		mDehydration    = tNBT.getByte("d");
		mSugar          = tNBT.getByte("s");
		mFat            = tNBT.getByte("f");
	}
	
	@Override
	public void init(Entity aEntity, World aWorld) {
		TICK_LIST.add(this);
	}
	
	public void changeAlcohol(long aAmount) {
		mAlcohol = UT.Code.bind7(mAlcohol + aAmount);
	}
	
	public void changeCaffeine(long aAmount) {
		mCaffeine = UT.Code.bind7(mCaffeine + aAmount);
	}
	
	public void changeDehydration(long aAmount) {
		mDehydration = UT.Code.bind7(mDehydration + aAmount);
	}
	
	public void changeSugar(long aAmount) {
		mSugar = UT.Code.bind7(mSugar + aAmount);
	}
	
	public void changeFat(long aAmount) {
		mFat = UT.Code.bind7(mFat + aAmount);
	}
	
	public static void tick() {
		if (SERVER_TIME % 50 == 0) for (int i = 0; i < TICK_LIST.size(); i++) {
			EntityFoodTracker tTracker = TICK_LIST.get(i);
			if (tTracker.mEntity.isDead) {TICK_LIST.remove(i--); continue;}
			
			if (tTracker.mAlcohol >= 100) {
				if (FOOD_OVERDOSE_DEATH || tTracker.mEntity.getHealth() >= 2)
				tTracker.mEntity.attackEntityFrom(DamageSources.getAlcoholDamage(), FOOD_OVERDOSE_DEATH?2:1);
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.confusion.id, 1200, 2));
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 300, 3));
			} else if (tTracker.mAlcohol >= 75) {
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.confusion.id, 1200, 1));
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 300, 2));
			} else if (tTracker.mAlcohol >= 50) {
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.confusion.id, 1200, 0));
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 300, 1));
			} else if (tTracker.mAlcohol >= 25) {
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 300, 0));
			}
			
			if (tTracker.mCaffeine >= 100) {
				if (FOOD_OVERDOSE_DEATH || tTracker.mEntity.getHealth() >= 2)
				tTracker.mEntity.attackEntityFrom(DamageSources.getCaffeineDamage(), FOOD_OVERDOSE_DEATH?2:1);
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.weakness.id, 1200, 2));
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 300, 3));
			} else if (tTracker.mCaffeine >= 75) {
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.weakness.id, 1200, 1));
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 300, 2));
			} else if (tTracker.mCaffeine >= 50) {
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.weakness.id, 1200, 0));
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 300, 1));
			} else if (tTracker.mCaffeine >= 25) {
				tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 300, 0));
			}
			
			if (NUTRITION_SYSTEM) {
				if (tTracker.mFat >= 100) {
					if (FOOD_OVERDOSE_DEATH || tTracker.mEntity.getHealth() >= 2)
					tTracker.mEntity.attackEntityFrom(DamageSources.getFatDamage(), FOOD_OVERDOSE_DEATH?2:1);
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1200, 2));
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.resistance.id, 300, 3));
				} else if (tTracker.mFat >= 75) {
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1200, 1));
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.resistance.id, 300, 2));
				} else if (tTracker.mFat >= 50) {
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1200, 0));
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.resistance.id, 300, 1));
				} else if (tTracker.mFat >= 25) {
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.resistance.id, 300, 0));
				}
				
				if (tTracker.mSugar >= 100) {
					if (FOOD_OVERDOSE_DEATH || tTracker.mEntity.getHealth() >= 2)
					tTracker.mEntity.attackEntityFrom(DamageSources.getSugarDamage(), FOOD_OVERDOSE_DEATH?2:1);
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 1200, 2));
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 300, 3));
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.jump.id, 300, 3));
				} else if (tTracker.mSugar >= 75) {
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 1200, 1));
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 300, 2));
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.jump.id, 300, 2));
				} else if (tTracker.mSugar >= 50) {
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 1200, 0));
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 300, 1));
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.jump.id, 300, 1));
				} else if (tTracker.mSugar >= 25) {
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 300, 0));
					tTracker.mEntity.addPotionEffect(new PotionEffect(Potion.jump.id, 300, 0));
				}
				
				if (tTracker.mDehydration >= 100) {
					if (FOOD_OVERDOSE_DEATH || tTracker.mEntity.getHealth() >= 2)
					tTracker.mEntity.attackEntityFrom(DamageSources.getDehydrationDamage(), FOOD_OVERDOSE_DEATH?2:1);
					tTracker.mEntity.addPotionEffect(new PotionEffect(PotionsGT.ID_DEHYDRATION >= 0 ? PotionsGT.ID_DEHYDRATION : Potion.hunger.id, 1200, 3));
				} else if (tTracker.mDehydration >= 75) {
					tTracker.mEntity.addPotionEffect(new PotionEffect(PotionsGT.ID_DEHYDRATION >= 0 ? PotionsGT.ID_DEHYDRATION : Potion.hunger.id, 1200, 2));
				} else if (tTracker.mDehydration >= 50) {
					tTracker.mEntity.addPotionEffect(new PotionEffect(PotionsGT.ID_DEHYDRATION >= 0 ? PotionsGT.ID_DEHYDRATION : Potion.hunger.id, 1200, 1));
				} else if (tTracker.mDehydration >= 25) {
					tTracker.mEntity.addPotionEffect(new PotionEffect(PotionsGT.ID_DEHYDRATION >= 0 ? PotionsGT.ID_DEHYDRATION : Potion.hunger.id, 1200, 0));
				}
			}
			
			if (tTracker.mAlcohol       > 0) tTracker.mAlcohol--;
			if (tTracker.mCaffeine      > 0) tTracker.mCaffeine--;
			if (tTracker.mDehydration   > 0) tTracker.mDehydration--;
			if (tTracker.mSugar         > 0) tTracker.mSugar--;
			if (tTracker.mFat           > 0) tTracker.mFat--;
		}
	}
	
	public static void add(EntityLivingBase aEntity) {
		if (aEntity == null || aEntity.worldObj.isRemote) return;
		aEntity.registerExtendedProperties("gt.properties.food", new EntityFoodTracker(aEntity));
	}
	
	public static EntityFoodTracker get(Entity aEntity) {
		if (aEntity == null || aEntity.worldObj.isRemote) return null;
		Object rTracker = aEntity.getExtendedProperties("gt.properties.food");
		return rTracker instanceof EntityFoodTracker ? (EntityFoodTracker)rTracker : null;
	}
}
