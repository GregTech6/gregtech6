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

package gregtech.items.behaviors;

import static gregapi.data.CS.*;

import gregapi.code.TagData;
import gregapi.data.TD;
import gregapi.item.IItemProjectile.EntityProjectile;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.UT;
import gregapi.util.UT.Enchantments;
import gregtech.entities.projectiles.EntityArrow_Material;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class Behavior_Arrow extends AbstractBehaviorDefault {
	public static Behavior_Arrow DEFAULT_WOODEN  = new Behavior_Arrow(EntityArrow_Material.class, 1.00F, 6.0F);
	public static Behavior_Arrow DEFAULT_PLASTIC = new Behavior_Arrow(EntityArrow_Material.class, 1.50F, 6.0F);
	
	private final int mLevel;
	private final Enchantment mEnchantment;
	private final float mSpeedMultiplier, mPrecision;
	private final Class<? extends EntityArrow_Material> mArrow;
	
	public Behavior_Arrow(Class<? extends EntityArrow_Material> aArrow, float aSpeed, float aPrecision) {
		this(aArrow, aSpeed, aPrecision, null, 0);
	}
	
	public Behavior_Arrow(Class<? extends EntityArrow_Material> aArrow, float aSpeed, float aPrecision, Enchantment aEnchantment, int aLevel) {
		mArrow = aArrow;
		mSpeedMultiplier = aSpeed;
		mPrecision = aPrecision;
		mEnchantment = aEnchantment;
		mLevel = aLevel;
	}
	
	@Override
	public boolean onLeftClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aEntity instanceof EntityLivingBase) {
			Enchantments.applyBullshitA((EntityLivingBase)aEntity, aPlayer, aStack);
			Enchantments.applyBullshitB(aPlayer, aEntity, aStack);
			if (!UT.Entities.hasInfiniteItems(aPlayer)) aStack.stackSize--;
			if (aStack.stackSize <= 0) aPlayer.destroyCurrentEquippedItem();
			return F;
		}
		return F;
	}
	
	@Override
	public boolean isItemStackUsable(MultiItem aItem, ItemStack aStack) {
		if (mEnchantment != null && mLevel > 0) {
			NBTTagCompound tNBT = UT.NBT.getNBT(aStack);
			if (!tNBT.getBoolean("gt.u")) {
				tNBT.setBoolean("gt.u", T);
				UT.NBT.set(aStack, tNBT);
				UT.NBT.addEnchantment(aStack, mEnchantment, mLevel);
			}
		}
		return T;
	}
	
	@Override
	public boolean canDispense(MultiItem aItem, IBlockSource aSource, ItemStack aStack) {
		return T;
	}
	
	@Override
	public ItemStack onDispense(MultiItem aItem, IBlockSource aSource, ItemStack aStack) {
		World aWorld = aSource.getWorld();
		IPosition tPosition = BlockDispenser.func_149939_a(aSource);
		EnumFacing tFacing = BlockDispenser.func_149937_b(aSource.getBlockMetadata());
		EntityProjectile tEntityArrow = getProjectile(aItem, TD.Projectiles.ARROW, aStack, aWorld, tPosition.getX(), tPosition.getY(), tPosition.getZ());
		if (tEntityArrow != null) {
			tEntityArrow.setThrowableHeading(tFacing.getFrontOffsetX(), (tFacing.getFrontOffsetY() + 0.1F), tFacing.getFrontOffsetZ(), mSpeedMultiplier * 1.10F, mPrecision);
			tEntityArrow.setProjectileStack(aStack);
			tEntityArrow.canBePickedUp = 1;
			aWorld.spawnEntityInWorld(tEntityArrow);
			if (aStack.stackSize < 100) aStack.stackSize--;
			return aStack;
		}
		return super.onDispense(aItem, aSource, aStack);
	}
	
	@Override
	public boolean hasProjectile(MultiItem aItem, TagData aProjectileType, ItemStack aStack) {
		return aProjectileType == TD.Projectiles.ARROW;
	}
	
	@Override
	public EntityProjectile getProjectile(MultiItem aItem, TagData aProjectileType, ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
		if (!hasProjectile(aItem, aProjectileType, aStack)) return null;
		EntityArrow_Material rArrow = (EntityArrow_Material)UT.Reflection.callConstructor(mArrow.getName(), -1, null, T, aWorld, aX, aY, aZ);
		rArrow.setProjectileStack(aStack);
		return rArrow;
	}
	
	@Override
	public EntityProjectile getProjectile(MultiItem aItem, TagData aProjectileType, ItemStack aStack, World aWorld, EntityLivingBase aEntity, float aSpeed) {
		if (!hasProjectile(aItem, aProjectileType, aStack)) return null;
		EntityArrow_Material rArrow = (EntityArrow_Material)UT.Reflection.callConstructor(mArrow.getName(), -1, null, T, aWorld, aEntity, mSpeedMultiplier * aSpeed);
		rArrow.setProjectileStack(aStack);
		return rArrow;
	}
}
