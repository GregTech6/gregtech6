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

package gregapi.random;

import static gregapi.data.CS.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import gregapi.data.CS.SFX;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/**
 * @author Gregorius Techneticies
 */
public class ExplosionGT extends Explosion {
	public static ExplosionGT explode(World aWorld, Entity aEntity, double aX, double aY, double aZ, float aPower, boolean aFlaming, boolean aSmoking) {
		ExplosionGT tExplosion = new ExplosionGT(aWorld, aEntity, aX, aY, aZ, aPower);
		tExplosion.isFlaming = aFlaming;
		tExplosion.isSmoking = aSmoking;
		if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(aWorld, tExplosion)) return tExplosion;
		tExplosion.doExplosionA();
		if (aWorld instanceof WorldServer) {
			tExplosion.doExplosionB(F);
			if (!aSmoking) tExplosion.affectedBlockPositions.clear();
			@SuppressWarnings("rawtypes")
			Iterator tIterator = aWorld.playerEntities.iterator();
			while (tIterator.hasNext()) {
				EntityPlayer tPlayer = (EntityPlayer)tIterator.next();
				if (tPlayer.getDistanceSq(aX, aY, aZ) < 4096) {
					((EntityPlayerMP)tPlayer).playerNetServerHandler.sendPacket(new S27PacketExplosion(aX, aY, aZ, aPower, tExplosion.affectedBlockPositions, (Vec3)tExplosion.func_77277_b().get(tPlayer)));
				}
			}
		} else {
			tExplosion.doExplosionB(T);
		}
		return tExplosion;
	}
	
	public ExplosionGT(World aWorld, Entity aEntity, double aX, double aY, double aZ, float aPower) {
		super(aWorld, aEntity, aX, aY, aZ, aPower);
		mWorld = aWorld;
	}
	
	private World mWorld;
	@SuppressWarnings("rawtypes")
	private Map field_77288_k = new HashMap<>();
	
	@Override
	@SuppressWarnings("unchecked")
	public void doExplosionA() {
		float tSize = explosionSize;
		HashSet<ChunkPosition> tPositions = new HashSet<>();
		for (int i = 0; i < 16; ++i) for (int j = 0; j < 16; ++j) for (int k = 0; k < 16; ++k) {
			if (i == 0 || i == 15 || j == 0 || j == 15 || k == 0 || k == 15) {
				double tIncX = i / 7.5F - 1, tIncY = j / 7.5F - 1, tIncZ = k / 7.5F - 1;
				double tDist = Math.sqrt(tIncX * tIncX + tIncY * tIncY + tIncZ * tIncZ);
				tIncX /= tDist; tIncY /= tDist; tIncZ /= tDist;
				float tPow = tSize * (0.7F + mWorld.rand.nextFloat() * 0.6F);
				double tX = explosionX, tY = explosionY, tZ = explosionZ;
				for (float tMul = 0.3F; tPow > 0; tPow -= tMul * 0.75F) {
					int tFloorX = UT.Code.roundDown(tX), tFloorY = UT.Code.roundDown(tY), tFloorZ = UT.Code.roundDown(tZ);
					Block tBlock = mWorld.getBlock(tFloorX, tFloorY, tFloorZ);
					if (tBlock.getMaterial() != Material.air) {
						float f3 = exploder != null ? exploder.func_145772_a(this, mWorld, tFloorX, tFloorY, tFloorZ, tBlock) : tBlock.getExplosionResistance(exploder, mWorld, tFloorX, tFloorY, tFloorZ, explosionX, explosionY, explosionZ);
						tPow -= (f3 + 0.3F) * tMul;
					}
					if (tPow > 0 && (exploder == null || exploder.func_145774_a(this, mWorld, tFloorX, tFloorY, tFloorZ, tBlock, tPow))) {
						tPositions.add(new ChunkPosition(tFloorX, tFloorY, tFloorZ));
					}
					tX += tIncX * tMul; tY += tIncY * tMul; tZ += tIncZ * tMul;
				}
			}
		}
		affectedBlockPositions.addAll(tPositions);
		tSize *= 2;
		@SuppressWarnings("rawtypes")
		List tEntities = mWorld.getEntitiesWithinAABBExcludingEntity(exploder, AxisAlignedBB.getBoundingBox(UT.Code.roundDown(explosionX - tSize - 1), UT.Code.roundDown(explosionY - tSize - 1), UT.Code.roundDown(explosionZ - tSize - 1), UT.Code.roundDown(explosionX + tSize + 1), UT.Code.roundDown(explosionY + tSize + 1), UT.Code.roundDown(explosionZ + tSize + 1)));
		net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(mWorld, this, tEntities, tSize);
		Vec3 tVec3 = Vec3.createVectorHelper(explosionX, explosionY, explosionZ);
		for (int i1 = 0; i1 < tEntities.size(); ++i1) {
			Entity tEntity = (Entity)tEntities.get(i1);
			double tEntityDist = tEntity.getDistance(explosionX, explosionY, explosionZ) / tSize;
			if (tEntityDist <= 1 && !(tEntity instanceof EntityWither || tEntity instanceof EntityDragon || tEntity instanceof EntityDragonPart || tEntity.getClass().getName().toLowerCase().contains("boss"))) {
				double tKnockX = tEntity.posX - explosionX, tKnockY = tEntity.posY + tEntity.getEyeHeight() - explosionY, tKnockZ = tEntity.posZ - explosionZ;
				double tDist = MathHelper.sqrt_double(tKnockX * tKnockX + tKnockY * tKnockY + tKnockZ * tKnockZ);
				if (tDist > 0) {
					tKnockX /= tDist;
					tKnockY /= tDist;
					tKnockZ /= tDist;
					double tKnockback = (1 - tEntityDist) * mWorld.getBlockDensity(tVec3, tEntity.boundingBox);
					tEntity.attackEntityFrom(DamageSource.setExplosionSource(this), ((int)((tKnockback * tKnockback + tKnockback) * 4 * tSize + 1)) * TFC_DAMAGE_MULTIPLIER);
					double tBlastProtection = EnchantmentProtection.func_92092_a(tEntity, tKnockback);
					tEntity.motionX += tKnockX * tBlastProtection;
					tEntity.motionY += tKnockY * tBlastProtection;
					tEntity.motionZ += tKnockZ * tBlastProtection;
					
					if (tEntity instanceof EntityPlayer) field_77288_k.put(tEntity, Vec3.createVectorHelper(tKnockX * tKnockback, tKnockY * tKnockback, tKnockZ * tKnockback));
				}
			}
		}
	}
	
	@Override
	public void doExplosionB(boolean aEffects) {
		mWorld.playSoundEffect(explosionX, explosionY, explosionZ, SFX.MC_EXPLODE, 4, (1 + (mWorld.rand.nextFloat() - mWorld.rand.nextFloat()) * 0.2F) * 0.7F);
		mWorld.spawnParticle(explosionSize >= 2 && isSmoking ? "hugeexplosion" : "largeexplode", explosionX, explosionY, explosionZ, 1, 0, 0);
		if (isSmoking) {
			@SuppressWarnings("rawtypes")
			Iterator tIterator = affectedBlockPositions.iterator();
			while (tIterator.hasNext()) {
				final ChunkPosition tPos = (ChunkPosition)tIterator.next();
				final Block tBlock = mWorld.getBlock(tPos.chunkPosX, tPos.chunkPosY, tPos.chunkPosZ);
				if (aEffects) {
					double d0 = (tPos.chunkPosX + mWorld.rand.nextFloat());
					double d1 = (tPos.chunkPosY + mWorld.rand.nextFloat());
					double d2 = (tPos.chunkPosZ + mWorld.rand.nextFloat());
					double d3 = d0 - explosionX;
					double d4 = d1 - explosionY;
					double d5 = d2 - explosionZ;
					double d6 = MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
					d3 /= d6;
					d4 /= d6;
					d5 /= d6;
					double d7 = 0.5D / (d6 / explosionSize + 0.1D);
					d7 *= (mWorld.rand.nextFloat() * mWorld.rand.nextFloat() + 0.3F);
					d3 *= d7;
					d4 *= d7;
					d5 *= d7;
					mWorld.spawnParticle("explode", (d0 + explosionX) / 2, (d1 + explosionY) / 2, (d2 + explosionZ) / 2, d3, d4, d5);
					mWorld.spawnParticle("smoke", d0, d1, d2, d3, d4, d5);
				}
				if (tBlock.getMaterial() != Material.air) {
					if (tBlock.canDropFromExplosion(this)) tBlock.dropBlockAsItemWithChance(mWorld, tPos.chunkPosX, tPos.chunkPosY, tPos.chunkPosZ, mWorld.getBlockMetadata(tPos.chunkPosX, tPos.chunkPosY, tPos.chunkPosZ), 1 / explosionSize, 0);
					tBlock.onBlockExploded(mWorld, tPos.chunkPosX, tPos.chunkPosY, tPos.chunkPosZ, this);
				}
			}
		}
		if (isFlaming) {
			@SuppressWarnings("rawtypes")
			Iterator tIterator = affectedBlockPositions.iterator();
			while (tIterator.hasNext()) {
				final ChunkPosition tPos = (ChunkPosition)tIterator.next();
				final Block tBlock = mWorld.getBlock(tPos.chunkPosX, tPos.chunkPosY, tPos.chunkPosZ), tAbove = mWorld.getBlock(tPos.chunkPosX, tPos.chunkPosY - 1, tPos.chunkPosZ);
				if (tBlock.getMaterial() == Material.air && tAbove.func_149730_j() && RNGSUS.nextInt(3) == 0) {
					mWorld.setBlock(tPos.chunkPosX, tPos.chunkPosY, tPos.chunkPosZ, Blocks.fire);
				}
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override public Map func_77277_b() {return field_77288_k;}
	@Override public EntityLivingBase getExplosivePlacedBy() {return exploder == null ? null : (exploder instanceof EntityTNTPrimed ? ((EntityTNTPrimed)exploder).getTntPlacedBy() : (exploder instanceof EntityLivingBase ? (EntityLivingBase)exploder : null));}
}
