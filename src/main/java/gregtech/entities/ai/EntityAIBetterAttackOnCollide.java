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

package gregtech.entities.ai;

import gregapi.data.CS;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

// Most a copy of `EntityAIAttackOnCollide`
public class EntityAIBetterAttackOnCollide extends EntityAIBase {
	World worldObj;
	EntityCreature attacker;
	int attackTick;
	double speedTowardsTarget;
	boolean longMemory;
	PathEntity entityPathEntity;
	Class<?> classTarget;
	private int field_75445_i;
	private double field_151497_i;
	private double field_151495_j;
	private double field_151496_k;
	private int failedPathFindingPenalty;

	public EntityAIBetterAttackOnCollide(EntityAIAttackOnCollide orig) {
		this(orig.attacker, orig.classTarget, orig.speedTowardsTarget, orig.longMemory);
	}

	public EntityAIBetterAttackOnCollide(EntityCreature p_i1635_1_, Class<?> p_i1635_2_, double p_i1635_3_, boolean p_i1635_5_) {
		this(p_i1635_1_, p_i1635_3_, p_i1635_5_);
		this.classTarget = p_i1635_2_;
	}

	public EntityAIBetterAttackOnCollide(EntityCreature p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_) {
		this.attacker = p_i1636_1_;
		this.worldObj = p_i1636_1_.worldObj;
		this.speedTowardsTarget = p_i1636_2_;
		this.longMemory = p_i1636_4_;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
		if (entitylivingbase == null) {
			return false;
		} else if (!entitylivingbase.isEntityAlive()) {
			return false;
		} else if (this.classTarget != null && !this.classTarget.isAssignableFrom(entitylivingbase.getClass())) {
			return false;
		} else if (--this.field_75445_i <= 0) {
			this.entityPathEntity = this.attacker.getNavigator().getPathToEntityLiving(entitylivingbase);
			this.field_75445_i = 4 + this.attacker.getRNG().nextInt(7);
			return this.entityPathEntity != null;
		} else {
			return true;
		}
	}

	@Override
	public boolean continueExecuting() {
		EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
		return entitylivingbase == null ? false : (!entitylivingbase.isEntityAlive() ? false : (!this.longMemory ? !this.attacker.getNavigator().noPath() : this.attacker.isWithinHomeDistance(MathHelper.floor_double(entitylivingbase.posX), MathHelper.floor_double(entitylivingbase.posY), MathHelper.floor_double(entitylivingbase.posZ))));
	}

	@Override
	public void startExecuting() {
		this.attacker.getNavigator().setPath(this.entityPathEntity, this.speedTowardsTarget);
		this.field_75445_i = 0;
	}

	@Override
	public void resetTask() {
		this.attacker.getNavigator().clearPathEntity();
	}

	@Override
	public void updateTask() {
		EntityLivingBase entitylivingbase = this.attacker.getAttackTarget();
		this.attacker.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
		double d0 = this.attacker.getDistanceSq(entitylivingbase.posX, entitylivingbase.boundingBox.minY, entitylivingbase.posZ);
		double d1 = this.attacker.width * 2.0F * this.attacker.width * 2.0F + entitylivingbase.width;
		--this.field_75445_i;
		if ((this.longMemory || this.attacker.getEntitySenses().canSee(entitylivingbase)) && this.field_75445_i <= 0 && (this.field_151497_i == 0.0D && this.field_151495_j == 0.0D && this.field_151496_k == 0.0D || entitylivingbase.getDistanceSq(this.field_151497_i, this.field_151495_j, this.field_151496_k) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F)) {
			this.field_151497_i = entitylivingbase.posX;
			this.field_151495_j = entitylivingbase.boundingBox.minY;
			this.field_151496_k = entitylivingbase.posZ;
			this.field_75445_i = this.failedPathFindingPenalty + 4 + this.attacker.getRNG().nextInt(7);
			if (this.attacker.getNavigator().getPath() != null) {
				PathPoint finalPathPoint = this.attacker.getNavigator().getPath().getFinalPathPoint();
				if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.xCoord, finalPathPoint.yCoord, finalPathPoint.zCoord) < 1.0D) {
					this.failedPathFindingPenalty = 0;
				} else {
					this.failedPathFindingPenalty += 10;
				}
			} else {
				this.failedPathFindingPenalty += 10;
			}

			if (d0 > 1024.0D) {
				this.field_75445_i += 10;
			} else if (d0 > 256.0D) {
				this.field_75445_i += 5;
			}

			if (!this.attacker.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget)) {
				this.field_75445_i += 15;
			}
		}

		this.attackTick = Math.max(this.attackTick - 1, 0);
		if (d0 <= d1 && this.attackTick <= 0) {
			this.attackTick = 5;

			boolean doAttack = true;
			if (this.attacker.getHeldItem() != null) {
				this.attacker.swingItem();
				ItemStack held = this.attacker.getHeldItem();
				if (held.stackSize > 0 && CS.ZOMBIES_IGNITE_HELD_TNT && held.getItem() == Item.getItemFromBlock(Blocks.tnt)) {
					this.attackTick = 20;
					doAttack = false;
					held.stackSize -= 1;
					if (held.stackSize <= 0) this.attacker.setCurrentItemOrArmor(0, null);
					if (!worldObj.isRemote) {
						EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(worldObj, this.attacker.posX, this.attacker.posY, this.attacker.posZ, this.attacker);
						worldObj.spawnEntityInWorld(entitytntprimed);
						worldObj.playSoundAtEntity(entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
					}
				} else if (held.stackSize > 0 && CS.ZOMBIES_DIG_WITH_TOOLS) {
					// TODO: Handle tools to break things
					// 1. figure out what the `held` item can work on.
					// 2. Get nearby block that the tool works on adjust up/down or even if the target is high or low in comparison, skip TE's and such perhaps with CS.ZOMBIES_DIG_TILEENTITIES.
					// 3. Use up some of the `held` tool and break that block then set `attackTick` to something hig based on toughness of that block or so (or add another counter to take 'time' to break something?)
				}
			}

			if (doAttack) this.attacker.attackEntityAsMob(entitylivingbase);
		}
		// TODO?:  this.playSound("creeper.primed", 1.0F, 0.5F);

	}
}
