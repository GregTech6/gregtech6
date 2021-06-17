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

import static gregapi.data.CS.*;

import gregapi.util.ST;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

// Started off as a refactored copy of `EntityAIAttackOnCollide`
public class EntityAIBetterAttackOnCollide extends EntityAIBase {
	public World mWorld;
	public PathEntity mPath;
	public Class<?> mTargetClass;
	public EntityCreature mCreature;
	public int mAttackCoolDown, mPathCoolDown, mFailedPathFindingPenalty;
	public double mX, mY, mZ, mSpeedToTarget;
	public boolean mLastingMemory;
	
	public EntityAIBetterAttackOnCollide(EntityAIAttackOnCollide orig) {
		mTargetClass = orig.classTarget;
		mCreature = orig.attacker;
		mWorld = mCreature.worldObj;
		mSpeedToTarget = orig.speedTowardsTarget;
		mLastingMemory = orig.longMemory;
		setMutexBits(3);
	}
	
	@Override
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = mCreature.getAttackTarget();
		if (entitylivingbase == null) return F;
		if (!entitylivingbase.isEntityAlive()) return F;
		if (mTargetClass != null && !mTargetClass.isAssignableFrom(entitylivingbase.getClass())) return F;
		
		if (--mPathCoolDown <= 0) {
			mPath = mCreature.getNavigator().getPathToEntityLiving(entitylivingbase);
			mPathCoolDown = 4 + mCreature.getRNG().nextInt(7);
			return mPath != null;
		}
		return T;
	}
	
	@Override
	public boolean continueExecuting() {
		EntityLivingBase tTarget = mCreature.getAttackTarget();
		return tTarget != null && tTarget.isEntityAlive() && (!mLastingMemory ? !mCreature.getNavigator().noPath() : mCreature.isWithinHomeDistance(MathHelper.floor_double(tTarget.posX), MathHelper.floor_double(tTarget.posY), MathHelper.floor_double(tTarget.posZ)));
	}
	
	@Override
	public void startExecuting() {
		mCreature.getNavigator().setPath(mPath, mSpeedToTarget);
		mPathCoolDown = 0;
	}
	
	@Override
	public void resetTask() {
		mCreature.getNavigator().clearPathEntity();
	}
	
	@Override
	public void updateTask() {
		EntityLivingBase tTarget = mCreature.getAttackTarget();
		mCreature.getLookHelper().setLookPositionWithEntity(tTarget, 30, 30);
		double tTargetDistance = mCreature.getDistanceSq(tTarget.posX, tTarget.boundingBox.minY, tTarget.posZ);
		double tLookRadius = mCreature.width * mCreature.width * 4 + tTarget.width;
		mPathCoolDown--;
		if ((mLastingMemory || mCreature.getEntitySenses().canSee(tTarget)) && mPathCoolDown <= 0 && ((mX == 0 && mY == 0 && mZ == 0) || tTarget.getDistanceSq(mX, mY, mZ) >= 1 || mCreature.getRNG().nextFloat() < 0.05F)) {
			mX = tTarget.posX; mY = tTarget.boundingBox.minY; mZ = tTarget.posZ;
			
			mPathCoolDown = mFailedPathFindingPenalty + 4 + mCreature.getRNG().nextInt(7);
			if (mCreature.getNavigator().getPath() != null) {
				PathPoint tPathPoint = mCreature.getNavigator().getPath().getFinalPathPoint();
				if (tPathPoint != null && tTarget.getDistanceSq(tPathPoint.xCoord, tPathPoint.yCoord, tPathPoint.zCoord) < 1) {
					mFailedPathFindingPenalty = 0;
				} else {
					mFailedPathFindingPenalty += 10;
				}
			} else {
				mFailedPathFindingPenalty += 10;
			}
			
			if (tTargetDistance > 1024) {
				mPathCoolDown += 10;
			} else if (tTargetDistance > 256) {
				mPathCoolDown += 5;
			}
			
			if (!mCreature.getNavigator().tryMoveToEntityLiving(tTarget, mSpeedToTarget)) {
				mPathCoolDown += 15;
			}
		}
		
		mAttackCoolDown = Math.max(mAttackCoolDown - 1, 0);
		if (tTargetDistance <= tLookRadius && mAttackCoolDown <= 0) {
			mAttackCoolDown = 5;
			
			boolean tAttacking = T;
			ItemStack tHeld = ST.valisize(mCreature.getHeldItem());
			
			if (tHeld != null) {
				mCreature.swingItem();
				if (ZOMBIES_IGNITE_HELD_TNT && ST.equal_(tHeld, Blocks.tnt)) {
					mAttackCoolDown = 20;
					tAttacking = F;
					
					if (--tHeld.stackSize <= 0) mCreature.setCurrentItemOrArmor(0, NI);
					
					if (!mWorld.isRemote) {
						EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(mWorld, mCreature.posX, mCreature.posY, mCreature.posZ, mCreature);
						mWorld.spawnEntityInWorld(entitytntprimed);
						mWorld.playSoundAtEntity(entitytntprimed, "game.tnt.primed", 1, 1);
					}
				} else
				if (ZOMBIES_DIG_WITH_TOOLS) {
					// TODO: Handle tools to break things
					// 1. figure out what the `held` item can work on.
					// 2. Get nearby block that the tool works on adjust up/down or even if the target is high or low in comparison, skip TE's and such perhaps with ZOMBIES_DIG_TILEENTITIES.
					// 3. Use up some of the `held` tool and break that block then set `attackTick` to something hig based on toughness of that block or so (or add another counter to take 'time' to break something?)
				}
			}
			
			if (tAttacking) mCreature.attackEntityAsMob(tTarget);
			
			// TODO: playSound("creeper.primed", 1, 0.5);
		}
	}
}
