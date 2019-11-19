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

import java.util.List;

import gregapi.block.misc.BlockBaseBars;
import gregapi.block.misc.BlockBaseSpike;
import gregapi.code.TagData;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
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
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockRail;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

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
		
		//MovingObjectPosition tTargetBlock = trace(aPlayer.worldObj, aPosA, aPosB, F);
		
		
		
		
		
		
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
				// TODO: Just hit the Entity directly for +2 Hearts Bonus Damage.
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
