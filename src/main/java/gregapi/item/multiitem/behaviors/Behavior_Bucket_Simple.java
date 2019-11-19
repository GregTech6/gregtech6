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

import gregapi.data.CS.BlocksGT;
import gregapi.data.FL;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;

public class Behavior_Bucket_Simple extends AbstractBehaviorDefault {
	public static final IBehavior<MultiItem> INSTANCE = new Behavior_Bucket_Simple(NI);
	
	public ItemStack mDefaultFullBucket;
	
	public Behavior_Bucket_Simple(ItemStack aDefault) {
		mDefaultFullBucket = aDefault;
	}
	
	@Override
	public ItemStack onItemRightClick(MultiItem aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		FluidStack mFluid = FL.getFluid(aStack, T);
		MovingObjectPosition tTarget = WD.getMOP(aWorld, aPlayer, mFluid == null);
		if (tTarget == null || tTarget.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) return aStack;
		int tX = tTarget.blockX, tY = tTarget.blockY, tZ = tTarget.blockZ;
		ItemStack tBucket = ST.make(Items.bucket, 1, 0);
		
		if (mFluid == null) {
			Block tFluidBlock = aWorld.getBlock(tX, tY, tZ);
			if (tFluidBlock == BlocksGT.River) {
				tBucket = FL.fill(FL.Water.make(1000), aStack, F, T, F, T);
				return tBucket == null ? aStack : tBucket;
			}
			if (tFluidBlock == BlocksGT.Ocean) {
				tBucket = FL.fill(FL.Ocean.make(1000), aStack, F, T, F, T);
				return tBucket == null ? aStack : tBucket;
			}
			if (tFluidBlock == BlocksGT.Swamp) {
				tBucket = FL.fill(FL.Dirty_Water.make(1000), aStack, F, T, F, T);
				return tBucket == null ? aStack : tBucket;
			}
			if (tFluidBlock == Blocks.lava || tFluidBlock == Blocks.flowing_lava || tFluidBlock == Blocks.water || tFluidBlock == Blocks.flowing_water) {
				if (aWorld.getBlockMetadata(tX, tY, tZ) == 0) tBucket = tBucket.getItem().onItemRightClick(tBucket, aWorld, aPlayer);
			} else
			if (tFluidBlock instanceof IFluidBlock) {
				FluidStack tFluid = ((IFluidBlock)tFluidBlock).drain(aWorld, tX, tY, tZ, F);
				if (tFluid != null) {
					if (FL.fill(tFluid, aStack, F, T, F, T) != null) tBucket = tBucket.getItem().onItemRightClick(tBucket, aWorld, aPlayer);
					if (FL.milk(tFluid) && tFluid.amount >= 1000) tBucket = ST.make(Items.milk_bucket, 1, 0);
				}
			}
		} else {
			if (ST.valid(mDefaultFullBucket)) {
				tBucket = ST.copy(mDefaultFullBucket);
				tBucket = tBucket.getItem().onItemRightClick(tBucket, aWorld, aPlayer);
			} else {
				if ((tBucket = FL.fill(mFluid, tBucket, F, T, F, T)) == null) return aStack;
				tBucket = tBucket.getItem().onItemRightClick(tBucket, aWorld, aPlayer);
			}
		}
		aPlayer.clearItemInUse();
		return processBucket(tBucket, aStack, mFluid != null);
	}
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (FL.getFluid(aStack, T) == null && (aEntity.getClass() == EntityCow.class || aEntity.getClass() == EntityMooshroom.class) && !((EntityCow)aEntity).isChild()) {
			if (!aPlayer.worldObj.isRemote || UT.Entities.hasInfiniteItems(aPlayer)) ST.set(aStack, FL.fill(FL.Milk.make(Integer.MAX_VALUE), aStack, F, T, T, T));
			return T;
		}
		return F;
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aPlayer.worldObj.isRemote) return F;
		FluidStack mFluid = FL.getFluid(aStack, T);
		if (mFluid == null) return F;
		if (FL.water(mFluid) && mFluid.amount >= 1000) {
			Block aBlock = aWorld.getBlock(aX, aY, aZ);
			if (aBlock instanceof BlockCauldron) {
				if (aWorld.getBlockMetadata(aX, aY, aZ) < 3) {
					((BlockCauldron)aBlock).func_150024_a(aWorld, aX, aY, aZ, 3);
					ST.set(aStack, ST.container(aStack, T));
					return T;
				}
				return F;
			}
		}
		return F;
	}
	
	protected ItemStack processBucket(ItemStack aBucket, ItemStack aStack, boolean aWasFull) {
		if (aBucket == null) return aStack;
		if (aWasFull) {
			if (aBucket.getItem() == Items.bucket) {
				aBucket = ST.container(aStack, F);
				if (aBucket == null) aStack.stackSize = 0; else aStack = aBucket;
				return aStack;
			}
		} else {
			FluidStack tFluid = FL.getFluid(aBucket, T);
			if (tFluid != null) {
				aBucket = FL.fill(tFluid, aStack, F, T, F, T);
				if (aBucket == null) aStack.stackSize = 0; else aStack = aBucket;
				return aStack;
			}
		}
		return aStack;
	}
}
