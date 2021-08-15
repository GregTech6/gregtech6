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

package gregapi.block.prefixblock;

import static gregapi.data.CS.*;

import gregapi.block.IBlockPlacable;
import gregapi.code.ArrayListNoNulls;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class PrefixBlockFallingEntity extends EntityFallingBlock {
	protected IBlockPlacable mBlock;
	protected ItemStack mStack;
	
	public PrefixBlockFallingEntity(World aWorld) {
		super(aWorld);
	}
	
	public PrefixBlockFallingEntity(World aWorld, double aX, double aY, double aZ, IBlockPlacable aBlock, ItemStack aStack) {
		super(aWorld, aX, aY, aZ, (Block)aBlock, 0);
		mBlock = aBlock;
		mStack = aStack;
		field_145810_d = aStack.getTagCompound();
	}
	
	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		++field_145812_b;
		motionY -= 0.03999999910593033D;
		moveEntity(motionX, motionY, motionZ);
		motionX *= 0.9800000190734863D;
		motionY *= 0.9800000190734863D;
		motionZ *= 0.9800000190734863D;
		if (!worldObj.isRemote) {
			int aX = UT.Code.roundDown(posX);
			int aY = UT.Code.roundDown(posY);
			int aZ = UT.Code.roundDown(posZ);
			if (field_145812_b == 1) {
				if (worldObj.getBlock(aX, aY, aZ) != super.func_145805_f()) {
					setDead();
					return;
				}
				worldObj.setBlockToAir(aX, aY, aZ);
			}
			if (onGround) {
				motionX *= 0.699999988079071D;
				motionZ *= 0.699999988079071D;
				motionY *= -0.5D;
				if (worldObj.getBlock(aX, aY, aZ) != Blocks.piston_extension) {
					setDead();
					if (!worldObj.canPlaceEntityOnSide(super.func_145805_f(), aX, aY, aZ, T, 1, null, mStack) || BlockFalling.func_149831_e(worldObj, aX, aY - 1, aZ) || !mBlock.placeBlock(worldObj, aX, aY, aZ, (byte)1, ST.meta_(mStack), mStack.getTagCompound(), T, T)) {
						if (field_145813_c) if (mBlock instanceof PrefixBlock) {for (ItemStack tStack : ((PrefixBlock)mBlock).mDrops.getDrops((PrefixBlock)mBlock, worldObj, aX, aY, aZ, ST.meta_(mStack), null, 0, F)) entityDropItem(tStack, 0.0F);} else entityDropItem(mStack, 0.0F);
					}
				}
			} else if (field_145812_b > 100 && !worldObj.isRemote && (aY < 1 || aY > 256) || field_145812_b > 600) {
				if (field_145813_c) if (mBlock instanceof PrefixBlock) {for (ItemStack tStack : ((PrefixBlock)mBlock).mDrops.getDrops((PrefixBlock)mBlock, worldObj, aX, aY, aZ, ST.meta_(mStack), null, 0, F)) entityDropItem(tStack, 0.0F);} else entityDropItem(mStack, 0.0F);
				setDead();
			}
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected void fall(float p_70069_1_) {
		int i = MathHelper.ceiling_float_int(p_70069_1_ - 1.0F);
		if (i > 0) for (Entity tEntity : new ArrayListNoNulls<Entity>(worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox))) {
			if (tEntity instanceof EntityLivingBase) tEntity.attackEntityFrom(DamageSource.fallingBlock, TFC_DAMAGE_MULTIPLIER * Math.min(MathHelper.floor_float((float)i * 2), 40));
		}
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound aNBT) {
		super.writeEntityToNBT(aNBT);
		aNBT.setShort("MetaData", ST.meta_(mStack));
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound aNBT) {
		super.readEntityFromNBT(aNBT);
		mBlock = (IBlockPlacable)super.func_145805_f();
		mStack = ST.make(super.func_145805_f(), 1, aNBT.getShort("MetaData"));
		mStack.setTagCompound(field_145810_d);
	}
	
	@Override
	public Block func_145805_f() {
		return Blocks.gravel;
	}
}
