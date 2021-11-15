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

package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import gregapi.code.TagData;
import gregapi.item.IItemProjectile.EntityProjectile;
import gregapi.item.multiitem.MultiItem;
import gregapi.util.UT;
import gregtech.entities.projectiles.EntityArrow_Potion;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Arrow_Potion extends Behavior_Arrow {
	private final int[] mPotions;
	
	public Behavior_Arrow_Potion(float aSpeed, float aPrecision, int... aPotions) {
		super(EntityArrow_Potion.class, aSpeed, aPrecision);
		mPotions = aPotions;
	}
	
	public Behavior_Arrow_Potion(float aSpeed, float aPrecision, Enchantment aEnchantment, int aLevel, int... aPotions) {
		super(EntityArrow_Potion.class, aSpeed, aPrecision, aEnchantment, aLevel);
		mPotions = aPotions;
	}
	
	@Override
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof EntityLivingBase) for (int i = 3; i < mPotions.length; i+=4) if (RNGSUS.nextInt(100) < mPotions[i]) UT.Entities.applyPotion(aEntity, mPotions[i-3], mPotions[i-2], mPotions[i-1], F);
		return super.onLeftClickEntity(aItem, aStack, aPlayer, aEntity);
	}
	
	@Override
	public EntityProjectile getProjectile(MultiItem aItem, TagData aProjectileType, ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
		if (!hasProjectile(aItem, aProjectileType, aStack)) return null;
		EntityArrow_Potion rArrow = new EntityArrow_Potion(aWorld, aX, aY, aZ);
		rArrow.setProjectileStack(aStack);
		rArrow.setPotions(mPotions);
		return rArrow;
	}
	
	@Override
	public EntityProjectile getProjectile(MultiItem aItem, TagData aProjectileType, ItemStack aStack, World aWorld, EntityLivingBase aEntity, float aSpeed) {
		if (!hasProjectile(aItem, aProjectileType, aStack)) return null;
		EntityArrow_Potion rArrow = new EntityArrow_Potion(aWorld, aEntity, aSpeed);
		rArrow.setProjectileStack(aStack);
		rArrow.setPotions(mPotions);
		return rArrow;
	}
}
