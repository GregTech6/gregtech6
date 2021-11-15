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

package gregtech.entities.projectiles;

import static gregapi.data.CS.*;

import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityArrow_Potion extends EntityArrow_Material {
	
	public EntityArrow_Potion(World aWorld) {
		super(aWorld);
	}
	
	public EntityArrow_Potion(World aWorld, double aX, double aY, double aZ) {
		super(aWorld, aX, aY, aZ);
	}
	
	public EntityArrow_Potion(World aWorld, EntityLivingBase aEntity, float aSpeed) {
		super(aWorld, aEntity, aSpeed);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound aNBT) {
		super.writeEntityToNBT(aNBT);
		aNBT.setIntArray("mPotions", mPotions);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound aNBT) {
		super.readEntityFromNBT(aNBT);
		setPotions(aNBT.getIntArray("mPotions"));
	}
	
	@Override
	public boolean breaksOnImpact() {
		return T;
	}
	
	/**
	 * @param aPotionEffects An Array of Potion Effects with %4==0 Elements as follows
	 * ID of a Potion Effect. 0 for none
	 * Duration of the Potion in Ticks
	 * Level of the Effect. [0, 1, 2] are for [I, II, III]
	 * The likelihood that this Potion Effect takes place upon being eaten [1 - 100]
	 */
	public void setPotions(int... aPotions) {
		if (aPotions != null) mPotions = aPotions;
	}
	
	public int[] getPotions() {
		return mPotions;
	}
	
	private int[] mPotions = new int[0];
	
	@Override
	public int[] onHitEntity(Entity aHitEntity, Entity aShootingEntity, ItemStack aArrow, int aRegularDamage, int aMagicDamage, int aKnockback, int aFireDamage, int aHitTimer) {
		if (aHitEntity instanceof EntityLivingBase) for (int i = 3; i < mPotions.length; i+=4) {
			if (RNGSUS.nextInt(100) < mPotions[i]) {
				UT.Entities.applyPotion(aHitEntity, mPotions[i-3], mPotions[i-2], mPotions[i-1], F);
			}
		}
		return super.onHitEntity(aHitEntity, aShootingEntity, aArrow, 1, aMagicDamage, aKnockback, aFireDamage, aHitTimer);
	}
}
