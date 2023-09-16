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

package gregtech.entities.projectiles;

import com.mojang.authlib.GameProfile;
import gregapi.item.IItemProjectile.EntityProjectile;
import gregapi.oredict.OreDictItemData;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.UT.Enchantments;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import java.util.List;
import java.util.UUID;

import static gregapi.data.CS.*;

public class EntityArrow_Material extends EntityProjectile {
	private int mHitBlockX = -1;
	private int mHitBlockY = -1;
	private int mHitBlockZ = -1;
	private Block mHitBlock = NB;
	private int mHitBlockMeta = 0;
	private boolean inGround = F;
	private int mTicksAlive = 0;
	private int ticksInAir = 0;
	private int mKnockback = 0;
	
	private ItemStack mArrow = null;
	
	public EntityArrow_Material(World aWorld) {
		super(aWorld);
	}
	
	public EntityArrow_Material(World aWorld, double aX, double aY, double aZ) {
		super(aWorld, aX, aY, aZ);
	}
	
	public EntityArrow_Material(World aWorld, EntityLivingBase aEntity, float aSpeed) {
		super(aWorld, aEntity, aSpeed);
	}
	
	public EntityArrow_Material(EntityArrow aArrow, ItemStack aStack) {
		super(aArrow.worldObj);
		shootingEntity = aArrow.shootingEntity;
		NBTTagCompound tNBT = UT.NBT.make();
		aArrow.writeToNBT(tNBT);
		readFromNBT(tNBT);
		setProjectileStack(aStack);
	}
	
	@Override
	public void onUpdate() {
		onEntityUpdate();
		if (mArrow == null && !worldObj.isRemote) {
			setDead();
			return;
		}
		
		Entity tShootingEntity = shootingEntity;
		
		if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F) {
			float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			prevRotationYaw = rotationYaw = (float)(Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
			prevRotationPitch = rotationPitch = (float)(Math.atan2(motionY, f) * 180.0D / Math.PI);
		}
		
		if (mTicksAlive++ == 3000) setDead();
		
		Block tBlock = worldObj.getBlock(mHitBlockX, mHitBlockY, mHitBlockZ);
		
		if (tBlock.getMaterial() != Material.air) {
			tBlock.setBlockBoundsBasedOnState(worldObj, mHitBlockX, mHitBlockY, mHitBlockZ);
			AxisAlignedBB axisalignedbb = tBlock.getCollisionBoundingBoxFromPool(worldObj, mHitBlockX, mHitBlockY, mHitBlockZ);
			if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3.createVectorHelper(posX, posY, posZ))) inGround = T;
		}
		
		if (arrowShake > 0) arrowShake--;
		
		if (inGround) {
			int j = worldObj.getBlockMetadata(mHitBlockX, mHitBlockY, mHitBlockZ);
			if (tBlock != mHitBlock || j != mHitBlockMeta) {
				inGround = F;
				motionX *= (rand.nextFloat() * 0.2F);
				motionY *= (rand.nextFloat() * 0.2F);
				motionZ *= (rand.nextFloat() * 0.2F);
				mTicksAlive = 0;
				ticksInAir = 0;
			}
		} else {
			ticksInAir++;
			Vec3 vec31 = Vec3.createVectorHelper(posX, posY, posZ);
			Vec3 vec3 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
			MovingObjectPosition tVector = worldObj.func_147447_a(vec31, vec3, F, T, F);
			vec31 = Vec3.createVectorHelper(posX, posY, posZ);
			vec3 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
			
			if (tVector != null) vec3 = Vec3.createVectorHelper(tVector.hitVec.xCoord, tVector.hitVec.yCoord, tVector.hitVec.zCoord);
			
			Entity tHitEntity = null;
			@SuppressWarnings("rawtypes")
			List tAllPotentiallyHitEntities = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
			double tSmallestDistance = Double.MAX_VALUE;
			
			for (int i = 0; i < tAllPotentiallyHitEntities.size(); ++i) {
				Entity entity1 = (Entity)tAllPotentiallyHitEntities.get(i);
				
				if (entity1.canBeCollidedWith() && (entity1 != tShootingEntity || ticksInAir >= 5)) {
					AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(0.3, 0.3, 0.3);
					MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec31, vec3);
					
					if (movingobjectposition1 != null) {
						double tDistance = vec31.distanceTo(movingobjectposition1.hitVec);
						
						if (tDistance < tSmallestDistance) {
							tHitEntity = entity1;
							tSmallestDistance = tDistance;
						}
					}
				}
			}
			
			if (tHitEntity != null) tVector = new MovingObjectPosition(tHitEntity);
			
			if (tVector != null && tHitEntity != null && tHitEntity instanceof EntityPlayer) {
				if (((EntityPlayer)tHitEntity).capabilities.disableDamage || (tShootingEntity instanceof EntityPlayer && !((EntityPlayer)tShootingEntity).canAttackPlayer((EntityPlayer)tHitEntity))) tVector = null;
			}
			
			if (tVector != null) {
				if (tHitEntity != null) {
					OreDictItemData tData = OM.anydata(mArrow);
					
					// To make Railcrafts Implosion Enchantment work...
					if (tShootingEntity instanceof EntityPlayer) MinecraftForge.EVENT_BUS.post(new AttackEntityEvent((EntityPlayer)tShootingEntity, tHitEntity));
					
					float
					tMagicDamage = tHitEntity instanceof EntityLivingBase?EnchantmentHelper.func_152377_a(mArrow, ((EntityLivingBase)tHitEntity).getCreatureAttribute()):0,
					tDamage = UT.Code.roundUp(MathHelper.sqrt_double(motionX*motionX + motionY*motionY + motionZ*motionZ) * (getDamage() + Math.max(0, tData != null && tData.validMaterial() ? tData.mMaterial.mMaterial.mToolQuality-1 : 0)));
					
					if (getIsCritical()) tDamage += rand.nextInt((int)(tDamage / 2.0 + 2.0));
					
					int
					tImplosion  = UT.NBT.getEnchantmentLevelImplosion(mArrow),
					tFireDamage = (isBurning()?5:0) + 4 * UT.NBT.getEnchantmentLevel(Enchantment.fireAspect, mArrow),
					tKnockback  = mKnockback + UT.NBT.getEnchantmentLevel(Enchantment.knockback, mArrow),
					tHitTimer   = -1;
					
					// Also work on Ghasts and such. But no double dipping on Anti Creeper Damage!
					if (tImplosion > 0 && UT.Entities.isExplosiveCreature(tHitEntity) && !EntityCreeper.class.isInstance(tHitEntity)) tMagicDamage += 1.5F * tImplosion;
					
					int[] tDamages = onHitEntity(tHitEntity, tShootingEntity==null?this:tShootingEntity, mArrow==null?ST.make(Items.arrow, 1, 0):mArrow, (int)(tDamage*2), (int)(tMagicDamage*2), tKnockback, tFireDamage, tHitTimer);
					
					if (tDamages != null) {
						tDamage      = tDamages[0] / 2.0F;
						tMagicDamage = tDamages[1] / 2.0F;
						tKnockback   = tDamages[2];
						tFireDamage  = tDamages[3];
						tHitTimer    = tDamages[4];
						
						if (tFireDamage > 0 && !(tHitEntity instanceof EntityEnderman)) tHitEntity.setFire(tFireDamage);
						
						if (!(tHitEntity instanceof EntityPlayer) && UT.NBT.getEnchantmentLevel(Enchantment.looting, mArrow) > 0) {
							EntityPlayer tPlayer = null;
							if (worldObj instanceof WorldServer) tPlayer = FakePlayerFactory.get((WorldServer)worldObj, new GameProfile(new UUID(0, 0), tShootingEntity instanceof EntityLivingBase?((EntityLivingBase)tShootingEntity).getCommandSenderName():"Arrow"));
							if (tPlayer != null) {
								tPlayer.inventory.currentItem = 0;
								tPlayer.inventory.setInventorySlotContents(0, getArrowItem());
								// Bypasses Twilight Forest Progression Checks. Yeah this is needed or else any Looting Arrow would do ZERO Damage.
								if (WD.dimTF(worldObj)) tPlayer.capabilities.isCreativeMode = T;
								tShootingEntity = tPlayer;
								tPlayer.setDead();
							}
						}
						
						// To make Looting work at all...
						DamageSource tDamageSource = DamageSource.causeArrowDamage(this, tShootingEntity==null?this:tShootingEntity);
						
						if (tDamage + tMagicDamage > 0 && tHitEntity.attackEntityFrom(tDamageSource, (tDamage + tMagicDamage) * TFC_DAMAGE_MULTIPLIER)) {
							if (tHitEntity instanceof EntityLivingBase) {
								if (tHitTimer >= 0) tHitEntity.hurtResistantTime = tHitTimer;
								
								if (tHitEntity instanceof EntityCreeper && UT.NBT.getEnchantmentLevel(Enchantment.fireAspect, mArrow) > 0 && tImplosion <= 0) ((EntityCreeper)tHitEntity).func_146079_cb();
								
								EntityLivingBase tHitLivingEntity = (EntityLivingBase)tHitEntity;
								
								if (!worldObj.isRemote) tHitLivingEntity.setArrowCountInEntity(tHitLivingEntity.getArrowCountInEntity() + 1);
								
								if (tKnockback > 0) {
									float tKnockbackDivider = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
									if (tKnockbackDivider > 0.0F) tHitLivingEntity.addVelocity(motionX * tKnockback * 0.6000000238418579D / tKnockbackDivider, 0.1D, motionZ * tKnockback * 0.6000000238418579D / tKnockbackDivider);
								}
								
								Enchantments.applyBullshitA(tHitLivingEntity                                                                  , tShootingEntity==null?this:tShootingEntity, mArrow);
								Enchantments.applyBullshitB(tShootingEntity instanceof EntityLivingBase?(EntityLivingBase)tShootingEntity:null, tHitLivingEntity                          , mArrow);
								
								if (tShootingEntity != null && tHitLivingEntity != tShootingEntity && tHitLivingEntity instanceof EntityPlayer && tShootingEntity instanceof EntityPlayerMP) {
									((EntityPlayerMP)tShootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
								}
							}
							
							if (tShootingEntity instanceof EntityPlayer && tMagicDamage > 0.0F) ((EntityPlayer)tShootingEntity).onEnchantmentCritical(tHitEntity);
							
							if (!(tHitEntity instanceof EntityEnderman) || ((EntityEnderman)tHitEntity).getActivePotionEffect(Potion.weakness) != null) {
								if (tFireDamage > 0) tHitEntity.setFire(tFireDamage);
								playSound("random.bowhit", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
								setDead();
							}
						} else {
							motionX *= -0.10000000149011612D;
							motionY *= -0.10000000149011612D;
							motionZ *= -0.10000000149011612D;
							rotationYaw += 180.0F;
							prevRotationYaw += 180.0F;
							ticksInAir = 0;
						}
					}
				} else {
					mHitBlockX = tVector.blockX;
					mHitBlockY = tVector.blockY;
					mHitBlockZ = tVector.blockZ;
					mHitBlock = worldObj.getBlock(mHitBlockX, mHitBlockY, mHitBlockZ);
					mHitBlockMeta = worldObj.getBlockMetadata(mHitBlockX, mHitBlockY, mHitBlockZ);
					motionX = ((float)(tVector.hitVec.xCoord - posX));
					motionY = ((float)(tVector.hitVec.yCoord - posY));
					motionZ = ((float)(tVector.hitVec.zCoord - posZ));
					float f2 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
					posX -= motionX / f2 * 0.05000000074505806D;
					posY -= motionY / f2 * 0.05000000074505806D;
					posZ -= motionZ / f2 * 0.05000000074505806D;
					playSound("random.bowhit", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
					inGround = true;
					arrowShake = 7;
					setIsCritical(false);
					
					if (mHitBlock.getMaterial() != Material.air) mHitBlock.onEntityCollidedWithBlock(worldObj, mHitBlockX, mHitBlockY, mHitBlockZ, this);
					
					if (!worldObj.isRemote && UT.NBT.getEnchantmentLevel(Enchantment.fireAspect, mArrow) > 2) WD.burn(worldObj, mHitBlockX, mHitBlockY, mHitBlockZ, T, F);
					
					if (breaksOnImpact()) setDead();
				}
			}
			
			if (getIsCritical()) for (int i = 0; i < 4; ++i) worldObj.spawnParticle("crit", posX + motionX * i / 4.0D, posY + motionY * i / 4.0D, posZ + motionZ * i / 4.0D, -motionX, -motionY + 0.2D, -motionZ);
			
			posX += motionX; posY += motionY; posZ += motionZ;
			
			rotationYaw = (float)(Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
			
			for (rotationPitch = (float)(Math.atan2(motionY, MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ)) * 180.0D / Math.PI); rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F) {/**/}
			
			while (rotationPitch    - prevRotationPitch >= 180.0F) prevRotationPitch += 360.0F;
			while (rotationYaw      - prevRotationYaw   < -180.0F) prevRotationYaw -= 360.0F;
			while (rotationYaw      - prevRotationYaw   >= 180.0F) prevRotationYaw += 360.0F;
			
			rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
			rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
			float tFrictionMultiplier = 0.99F;
			
			if (isInWater()) {
				for (int l = 0; l < 4; ++l) worldObj.spawnParticle("bubble", posX - motionX * 0.25, posY - motionY * 0.25, posZ - motionZ * 0.25, motionX, motionY, motionZ);
				tFrictionMultiplier = 0.8F;
			}
			
			if (isWet()) extinguish();
			
			motionX *= tFrictionMultiplier;
			motionY *= tFrictionMultiplier;
			motionZ *= tFrictionMultiplier;
			motionY -= 0.05F;
			setPosition(posX, posY, posZ);
			func_145775_I();
		}
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound aNBT) {
		super.writeEntityToNBT(aNBT);
		aNBT.setShort("xTile", (short)mHitBlockX);
		aNBT.setShort("yTile", (short)mHitBlockY);
		aNBT.setShort("zTile", (short)mHitBlockZ);
		aNBT.setShort("life", (short)mTicksAlive);
		aNBT.setByte("inTile", (byte)Block.getIdFromBlock(mHitBlock));
		aNBT.setByte("inData", (byte)mHitBlockMeta);
		aNBT.setByte("shake", (byte)arrowShake);
		aNBT.setByte("inGround", (byte)(inGround ? 1 : 0));
		aNBT.setByte("pickup", (byte)canBePickedUp);
		aNBT.setDouble("damage", getDamage());
		aNBT.setTag("mArrow", ST.save(mArrow));
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound aNBT) {
		super.readEntityFromNBT(aNBT);
		mHitBlockX = aNBT.getShort("xTile");
		mHitBlockY = aNBT.getShort("yTile");
		mHitBlockZ = aNBT.getShort("zTile");
		mTicksAlive = aNBT.getShort("life");
		mHitBlock = Block.getBlockById(aNBT.getByte("inTile") & 255);
		mHitBlockMeta = aNBT.getByte("inData") & 255;
		arrowShake = aNBT.getByte("shake") & 255;
		inGround = aNBT.getByte("inGround") == 1;
		setDamage(aNBT.getDouble("damage"));
		canBePickedUp = aNBT.getByte("pickup");
		mArrow = ST.load(aNBT, "mArrow");
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer aPlayer) {
		if (!worldObj.isRemote && inGround && arrowShake <= 0 && canBePickedUp == 1 && aPlayer.inventory.addItemStackToInventory(getArrowItem())) {
			playSound("random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			aPlayer.onItemPickup(this, 1);
			setDead();
		}
	}
	
	/**
	 * @param aHitEntity the hit Entity
	 * @param aShootingEntity the shooting Entity
	 * @param aArrow the Arrow Item, might be a vanilla Arrow if the Client has not synched the Item.
	 * @param aRegularDamage Damage in Half Hearts
	 * @param aMagicDamage Magic Damage in Half Hearts
	 * @param aKnockback Knockback Level
	 * @param aFireDamage Fire Damage
	 * @return null if this is not damaging the Entity, otherwise see the return value below.
	 */
	public int[] onHitEntity(Entity aHitEntity, Entity aShootingEntity, ItemStack aArrow, int aRegularDamage, int aMagicDamage, int aKnockback, int aFireDamage, int aHitTimer) {
		return new int[] {aRegularDamage, aMagicDamage, aKnockback, aFireDamage, aHitTimer};
	}
	
	@Override
	public void setProjectileStack(ItemStack aStack) {
		mArrow = ST.update(ST.amount(1, aStack), this);
	}
	
	public ItemStack getArrowItem() {
		return ST.copy(mArrow);
	}
	
	public boolean breaksOnImpact() {
		return false;
	}
	
	@Override
	public void setKnockbackStrength(int aKnockback) {
		mKnockback = aKnockback;
	}
}
