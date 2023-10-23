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

package gregapi.item.prefixitem;

import cpw.mods.fml.common.FMLLog;
import gregapi.code.ModData;
import gregapi.code.ObjectStack;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.item.IItemProjectile;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class PrefixItemProjectile extends PrefixItem implements IItemProjectile {
	public final TagData mProjectileType;
	public final Class<? extends EntityProjectile> mEntityClass;
	public final float mSpeedMultiplier, mPrecision;
	public final boolean mStabbing, mIsBullet;
	
	public PrefixItemProjectile(ModData aMod, String aNameInternal, OreDictPrefix aPrefix, TagData aProjectileType, Class<? extends EntityProjectile> aEntityClass, float aSpeedMultiplier, float aPrecision, boolean aDispensable, boolean aStabbing, boolean aIsBullet, OreDictMaterial... aMaterialList) {
		this(aMod.mID, aMod.mID, aNameInternal, aPrefix, aProjectileType, aEntityClass, aSpeedMultiplier, aPrecision, aDispensable, aStabbing, aIsBullet, aMaterialList);
	}
	
	public PrefixItemProjectile(String aModIDOwner, String aModIDTextures, String aNameInternal, OreDictPrefix aPrefix, TagData aProjectileType, Class<? extends EntityProjectile> aEntityClass, float aSpeedMultiplier, float aPrecision, boolean aDispensable, boolean aStabbing, boolean aIsBullet, OreDictMaterial... aMaterialList) {
		super(aModIDOwner, aModIDTextures, aNameInternal, aPrefix, aMaterialList);
		mProjectileType = aProjectileType;
		mEntityClass = aEntityClass;
		mPrecision = aPrecision;
		mSpeedMultiplier = aSpeedMultiplier;
		mStabbing = aStabbing;
		mIsBullet = aIsBullet;
		if (aDispensable) BlockDispenser.dispenseBehaviorRegistry.putObject(this, new MetaItemDispense());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void addInformation(ItemStack aStack, EntityPlayer aPlayer, List aList, boolean aF3_H) {
		if (mIsBullet) {
			OreDictMaterial tMat = getMaterial(ST.meta(aStack));
			int tDamage = (int)((tMat == null ? 1.0 : tMat.getWeight(getPrefix(ST.meta(aStack)).mAmount) / 50.0) * 2.0F * TFC_DAMAGE_MULTIPLIER)+1;
			aList.add(LH.Chat.WHITE + "Bullet Damage: " + LH.Chat.RED + tDamage/2.0F + (TFC_DAMAGE_MULTIPLIER>1?"":" Hearts"));
		}
		super.addInformation(aStack, aPlayer, aList, aF3_H);
	}
	
	@Override
	public boolean hasProjectile(TagData aProjectileType, ItemStack aStack) {
		return (mProjectileType == aProjectileType || aProjectileType == null) && UT.Code.exists(ST.meta_(aStack), mMaterialList) && mMaterialList[ST.meta_(aStack)] != MT.Empty;
	}
	
	@Override
	public EntityProjectile getProjectile(TagData aProjectileType, ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
		if (!hasProjectile(aProjectileType, aStack)) return null;
		try {
			EntityProjectile tProjectile = mEntityClass.getConstructor(World.class, Double.TYPE, Double.TYPE, Double.TYPE).newInstance(aWorld, aX, aY, aZ);
			tProjectile.setProjectileStack(ST.amount(1, aStack));
			return tProjectile;
		} catch (Throwable e) {FMLLog.severe("Problems with '%s'", mEntityClass.getName()); FMLLog.severe(e.toString());}
		return null;
	}
	
	@Override
	public EntityProjectile getProjectile(TagData aProjectileType, ItemStack aStack, World aWorld, EntityLivingBase aEntity, float aSpeed) {
		if (!hasProjectile(aProjectileType, aStack)) return null;
		try {
			EntityProjectile tProjectile = mEntityClass.getConstructor(World.class, EntityLivingBase.class, Float.TYPE).newInstance(aWorld, aEntity, mSpeedMultiplier * aSpeed);
			tProjectile.setProjectileStack(ST.amount(1, aStack));
			return tProjectile;
		} catch (Throwable e) {FMLLog.severe("Problems with '%s'", mEntityClass.getName()); FMLLog.severe(e.toString());}
		return null;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		super.onLeftClickEntity(aStack, aPlayer, aEntity);
		if (aEntity instanceof EntityLivingBase) {
			if (mStabbing) {
				UT.Enchantments.applyBullshitA((EntityLivingBase)aEntity, aPlayer, aStack);
				UT.Enchantments.applyBullshitB(aPlayer, aEntity, aStack);
			}
			ST.use(aPlayer, aStack);
			return F;
		}
		return F;
	}
	
	public int mLootingMultiplier = 1;
	
	public PrefixItemProjectile setLootingMultiplier(int aLootingMultiplier) {
		mLootingMultiplier = aLootingMultiplier;
		return this;
	}
	
	@Override
	public void updateItemStack(ItemStack aStack) {
		super.updateItemStack(aStack);
		short aMetaData = ST.meta_(aStack);
		if (UT.Code.exists(aMetaData, mMaterialList) && !mMaterialList[aMetaData].mEnchantmentAmmo.isEmpty()) {
			NBTTagCompound tNBT = UT.NBT.getOrCreate(aStack);
			if (!tNBT.getBoolean("gt.u")) {
				tNBT.setBoolean("gt.u", T);
				for (ObjectStack<Enchantment> tEnchantment : mMaterialList[aMetaData].mEnchantmentAmmo) {
					UT.NBT.addEnchantment(aStack, tEnchantment.mObject, tEnchantment.mObject == Enchantment.looting ? tEnchantment.mAmount * mLootingMultiplier : tEnchantment.mAmount);
				}
			}
		}
	}
	
	public ItemStack onDispense(IBlockSource aSource, ItemStack aStack) {
		World aWorld = aSource.getWorld();
		IPosition tPosition = BlockDispenser.func_149939_a(aSource);
		EnumFacing tFacing = BlockDispenser.func_149937_b(aSource.getBlockMetadata());
		EntityProjectile tProjectile = getProjectile(mProjectileType, aStack, aWorld, tPosition.getX(), tPosition.getY(), tPosition.getZ());
		if (tProjectile != null) {
			tProjectile.setThrowableHeading(tFacing.getFrontOffsetX(), (tFacing.getFrontOffsetY() + 0.1F), tFacing.getFrontOffsetZ(), mSpeedMultiplier * 1.10F, mPrecision);
			tProjectile.setProjectileStack(ST.amount(1, aStack));
			tProjectile.canBePickedUp = 1;
			aWorld.spawnEntityInWorld(tProjectile);
			if (aStack.stackSize < 100) aStack.stackSize--;
			return aStack;
		}
		
		// Default Item Dropping.
		EnumFacing enumfacing = BlockDispenser.func_149937_b(aSource.getBlockMetadata());
		IPosition iposition = BlockDispenser.func_149939_a(aSource);
		ItemStack itemstack1 = aStack.splitStack(1);
		BehaviorDefaultDispenseItem.doDispense(aSource.getWorld(), itemstack1, 6, enumfacing, iposition);
		return aStack;
	}
	
	public static class MetaItemDispense extends BehaviorProjectileDispense {
		@Override public ItemStack dispenseStack(IBlockSource aSource, ItemStack aStack) {return ((PrefixItemProjectile)aStack.getItem()).onDispense(aSource, aStack);}
		@Override protected IProjectile getProjectileEntity(World aWorld, IPosition aPosition) {return null;}
	}
}
