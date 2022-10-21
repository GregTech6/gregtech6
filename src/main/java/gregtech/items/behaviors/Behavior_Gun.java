/**
 * Copyright (c) 2022 GregTech-6 Team
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

import gregapi.block.misc.BlockBaseBars;
import gregapi.block.misc.BlockBaseSpike;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import gregtech.tileentity.misc.MultiTileEntityGregOLantern;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;

import static gregapi.data.CS.*;

public class Behavior_Gun extends AbstractBehaviorDefault {
	public static Behavior_Gun BULLETS_SMALL  = new Behavior_Gun(TD.Projectiles.BULLET_SMALL);
	public static Behavior_Gun BULLETS_MEDIUM = new Behavior_Gun(TD.Projectiles.BULLET_MEDIUM);
	public static Behavior_Gun BULLETS_LARGE  = new Behavior_Gun(TD.Projectiles.BULLET_LARGE);
	
	public final TagData mBulletType;
	
	public Behavior_Gun(TagData aBulletType) {
		mBulletType = aBulletType;
	}
	
	public MovingObjectPosition trace(World aWorld, Vec3 aPosA, Vec3 aPosB, boolean aHitThings) {
		if (Double.isNaN(aPosA.xCoord) || Double.isNaN(aPosA.yCoord) || Double.isNaN(aPosA.zCoord) || Double.isNaN(aPosB.xCoord) || Double.isNaN(aPosB.yCoord) || Double.isNaN(aPosB.zCoord)) return null;
		int tCount = 1000, aAX = UT.Code.roundDown(aPosA.xCoord), aAY = UT.Code.roundDown(aPosA.yCoord), aAZ = UT.Code.roundDown(aPosA.zCoord), aBX = UT.Code.roundDown(aPosB.xCoord), aBY = UT.Code.roundDown(aPosB.yCoord), aBZ = UT.Code.roundDown(aPosB.zCoord);
		byte tSide = SIDE_UNKNOWN;
		
		while (tCount-- >= 0) {
			Block aBlock = WD.block(aWorld, aAX, aAY, aAZ);
			byte  aMeta  = WD.meta (aWorld, aAX, aAY, aAZ);
			
			
			// TRIPWIRE!
			
			if (aBlock.getMaterial() == Material.water || aBlock.getMaterial() == Material.lava) {
				return new MovingObjectPosition(aAX, aAY, aAZ, tSide, aPosA, T);
			} else if (aBlock.getMaterial() == Material.glass || aBlock == Blocks.redstone_lamp || aBlock == Blocks.lit_redstone_lamp) {
				if (aHitThings) {
					OreDictItemData tData = OM.anydata(ST.make(aBlock, 1, aMeta));
					for (OreDictMaterialStack tMaterial : tData.getAllMaterialStacks()) {
						long tAmount = tMaterial.mAmount / OP.scrapGt.mAmount;
						while (tAmount-->0) ST.drop(aWorld, aAX+0.2+RNGSUS.nextFloat()*0.6, aAY+0.1+RNGSUS.nextFloat()*0.5, aAZ+0.2+RNGSUS.nextFloat()*0.6, OP.scrapGt.mat(tMaterial.mMaterial, 1));
					}
				}
			} else if (aBlock == Blocks.fence || aBlock == Blocks.fence_gate || aBlock == Blocks.web || aBlock == Blocks.mob_spawner || aBlock instanceof BlockPane || aBlock instanceof BlockRail || aBlock instanceof BlockTorch || aBlock instanceof BlockBaseBars || aBlock instanceof BlockBaseSpike || aBlock.getMaterial() == Material.cactus || aBlock.getMaterial() == Material.fire || aBlock.getMaterial() == Material.air || aBlock.getMaterial() == Material.carpet || aBlock.getMaterial() == Material.cloth || aBlock.getMaterial() == Material.leaves || aBlock.getMaterial() == Material.plants || aBlock.getMaterial() == Material.vine) {
				// Just ignore or assume the Player shot through them.
			} else if (aBlock.canCollideCheck(aMeta, F)) {
				MovingObjectPosition tPos = aBlock.collisionRayTrace(aWorld, aAX, aAY, aAZ, aPosA, aPosB);
				if (tPos != null) return tPos;
			}
			
			if (aAX == aBX && aAY == aBY && aAZ == aBZ) return null;
			if (Double.isNaN(aPosA.xCoord) || Double.isNaN(aPosA.yCoord) || Double.isNaN(aPosA.zCoord)) return null;
			
			tSide = SIDE_UNKNOWN;
			
			double tAX = aAX, tAY = aAY, tAZ = aAZ, tBX = aAX, tBY = aAY, tBZ = aAZ;
			double tDiffX = aPosB.xCoord - aPosA.xCoord, tDiffY = aPosB.yCoord - aPosA.yCoord, tDiffZ = aPosB.zCoord - aPosA.zCoord;
			
			if (aAX != aBX) {if (aBX > aAX) tAX++; tBX = (tAX - aPosA.xCoord) / tDiffX;}
			if (aAY != aBY) {if (aBY > aAY) tAY++; tBY = (tAY - aPosA.yCoord) / tDiffY;}
			if (aAZ != aBZ) {if (aBZ > aAZ) tAZ++; tBZ = (tAZ - aPosA.zCoord) / tDiffZ;}
			
			if (tBX < tBY && tBX < tBZ) {
				if (aBX > aAX) tSide = SIDE_X_NEG; else tSide = SIDE_X_POS;
				aPosA.xCoord  = tAX;
				aPosA.yCoord += tDiffY * tBX;
				aPosA.zCoord += tDiffZ * tBX;
			} else if (tBY < tBZ) {
				if (aBY > aAY) tSide = SIDE_Y_NEG; else tSide = SIDE_Y_POS;
				aPosA.xCoord += tDiffX * tBY;
				aPosA.yCoord  = tAY;
				aPosA.zCoord += tDiffZ * tBY;
			} else {
				if (aBZ > aAZ) tSide = SIDE_Z_NEG; else tSide = SIDE_Z_POS;
				aPosA.xCoord += tDiffX * tBZ;
				aPosA.yCoord += tDiffY * tBZ;
				aPosA.zCoord  = tAZ;
			}
			
			aAX = UT.Code.roundDown(aPosA.xCoord); if (tSide == SIDE_X_POS) aAX--;
			aAY = UT.Code.roundDown(aPosA.yCoord); if (tSide == SIDE_Y_POS) aAY--;
			aAZ = UT.Code.roundDown(aPosA.zCoord); if (tSide == SIDE_Z_POS) aAZ--;
		}
		return null;
	}
	
	public boolean shoot(ItemStack aGun, ItemStack aBullet, EntityPlayer aPlayer) {
		ST.update(aGun, aPlayer);
		ST.update(aBullet, aPlayer);
		
		Vec3 tAim = aPlayer.getLookVec(), tPos = Vec3.createVectorHelper(aPlayer.posX, aPlayer.posY + aPlayer.getEyeHeight(), aPlayer.posZ);
		tAim = tPos.addVector(tAim.xCoord * 100, tAim.yCoord * 100, tAim.zCoord * 100);
		
		List<ChunkCoordinates> aCoords = WD.ray(T, T, 200, tPos.xCoord, tPos.yCoord, tPos.zCoord, tAim.xCoord, tAim.yCoord, tAim.zCoord);
		ChunkCoordinates oCoord = null, aCoord = oCoord = aCoords.get(0);
		Block oBlock = NB, aBlock = oBlock = WD.block(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ);
		byte  oMeta  =  0, aMeta  = oMeta  = WD.meta (aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ);
		int tFireAspect = EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, aGun) + EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, aBullet);
		long tPower = 10000;
		boolean tWater = WD.liquid(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ);
		
		for (int i = 1, ii = aCoords.size(); i < ii; i++) {
			if (tPower--<=0) {
				// TODO Maybe drop the Round as an Item at ***oCoord***.
				if (tFireAspect > 2) WD.burn(aPlayer.worldObj, oCoord, T, T);
				break;
			}
			
			oCoord = aCoord;
			oBlock = aBlock;
			oMeta  = aMeta;
			
			aCoord = aCoords.get(i);
			aBlock = WD.block(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ);
			aMeta  = WD.meta (aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ);
			
			if (WD.liquid(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ)) {
				if (!tWater) {
					tWater = T;
					// TODO Bullet Splash Sound.
					
					// if high velocity break entirely, otherwise half the remaining power.
					if (tPower > 10000) tPower = 0; else tPower /= 2;
				}
				continue;
			}
			
			tWater = F;
			
			if (aBlock instanceof BlockPumpkin || WD.te(aPlayer.worldObj, aCoord, T) instanceof MultiTileEntityGregOLantern) {
				tPower-=3000;
				if (RNGSUS.nextInt(3) == 0) {
					ST.drop(aPlayer.worldObj, aCoord.posX+0.2+RNGSUS.nextFloat()*0.6, aCoord.posY+0.1+RNGSUS.nextFloat()*0.5, aCoord.posZ+0.2+RNGSUS.nextFloat()*0.6, ST.make(Blocks.pumpkin, 1, 0));
				} else {
					ST.drop(aPlayer.worldObj, aCoord.posX+0.2+RNGSUS.nextFloat()*0.6, aCoord.posY+0.1+RNGSUS.nextFloat()*0.5, aCoord.posZ+0.2+RNGSUS.nextFloat()*0.6, ST.make(Items.pumpkin_seeds, 1+RNGSUS.nextInt(3), 0));
				}
				// TODO Pumpkin splatter Sound.
				WD.set(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ, NB, 0, 3);
				if (tFireAspect > 1) WD.fire(aPlayer.worldObj, aCoord, F);
				continue;
			}
			if (aBlock == Blocks.melon_block) {
				tPower-=3000;
				ST.drop(aPlayer.worldObj, aCoord.posX+0.2+RNGSUS.nextFloat()*0.6, aCoord.posY+0.1+RNGSUS.nextFloat()*0.5, aCoord.posZ+0.2+RNGSUS.nextFloat()*0.6, ST.make(Items.melon      , 1+RNGSUS.nextInt(6), 0));
				ST.drop(aPlayer.worldObj, aCoord.posX+0.2+RNGSUS.nextFloat()*0.6, aCoord.posY+0.1+RNGSUS.nextFloat()*0.5, aCoord.posZ+0.2+RNGSUS.nextFloat()*0.6, ST.make(Items.melon_seeds, 1+RNGSUS.nextInt(3), 0));
				// TODO Melon splatter Sound.
				WD.set(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ, NB, 0, 3);
				if (tFireAspect > 1) WD.fire(aPlayer.worldObj, aCoord, F);
				continue;
			}
			if (aBlock.getMaterial() == Material.glass || aBlock == Blocks.redstone_lamp || aBlock == Blocks.lit_redstone_lamp) {
				tPower-=1000;
				
				OreDictItemData tData = OM.anydata(ST.make(aBlock, 1, aMeta));
				for (OreDictMaterialStack tMaterial : tData.getAllMaterialStacks()) {
					long tAmount = tMaterial.mAmount / OP.scrapGt.mAmount;
					while (tAmount-->0) {
						ST.drop(aPlayer.worldObj, aCoord.posX+0.2+RNGSUS.nextFloat()*0.6, aCoord.posY+0.1+RNGSUS.nextFloat()*0.5, aCoord.posZ+0.2+RNGSUS.nextFloat()*0.6, OP.scrapGt.mat(tMaterial.mMaterial, 1));
					}
				}
				
				// TODO Glass Shatter Sound.
				WD.set(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ, NB, 0, 3);
				continue;
			}
			
			if (aBlock == Blocks.fence || aBlock == Blocks.fence_gate || aBlock == Blocks.web || aBlock == Blocks.mob_spawner || aBlock instanceof BlockPane || aBlock instanceof BlockRail || aBlock instanceof BlockTorch || aBlock instanceof BlockBaseBars || aBlock instanceof BlockBaseSpike || aBlock.getMaterial() == Material.cactus || aBlock.getMaterial() == Material.fire || aBlock.getMaterial() == Material.air || aBlock.getMaterial() == Material.carpet || aBlock.getMaterial() == Material.cloth || aBlock.getMaterial() == Material.leaves || aBlock.getMaterial() == Material.plants || aBlock.getMaterial() == Material.vine) {
				// Just ignore or assume the Player shot through them.
				continue;
			}
			
			if (aBlock.canCollideCheck(aMeta, F) || aBlock.canCollideCheck(aMeta, T) || WD.opq(aPlayer.worldObj, aCoord.posX, aCoord.posY, aCoord.posZ, T, F)) {
				tPower = 0;
				// TODO Hit Wall Sound.
				continue;
			}
			
			// TODO Scan for Entities in this Block, yes this might be slightly laggy, but it's necessary to be done here.
		}
		return T;
	}
	
	public boolean hit(ItemStack aGun, ItemStack aBullet, EntityLivingBase aTarget) {
		
		
		return T;
	}
	
	@Override
	public ItemStack onItemRightClick(MultiItem aItem, ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		if (aPlayer instanceof EntityPlayerMP) {
			if (aPlayer.isSneaking()) {
				// TODO: Open GUI for reloading Gun
			} else {
				// TODO: Select Bullet!
				shoot(aStack, OP.bulletGtSmall.mat(MT.Ag, 1), aPlayer);
				UT.Sounds.send(SFX.MC_FIREWORK_BLAST_FAR, 128, 1.0F, aPlayer);
			}
		}
		return aStack;
	}
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aPlayer instanceof EntityPlayerMP) {
			if (aPlayer.isSneaking()) {
				// TODO: Open GUI for reloading Gun
			} else {
				// TODO: Just hit the Entity directly for +2 Hearts Bonus Damage. And Remember the TFC Damage Multiplier!
				UT.Sounds.send(SFX.MC_FIREWORK_BLAST_FAR, 128, 1.0F, aPlayer);
			}
			return T;
		}
		return T;
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get(LH.WEAPON_SNEAK_RIGHTCLICK_TO_RELOAD));
		return aList;
	}
}
