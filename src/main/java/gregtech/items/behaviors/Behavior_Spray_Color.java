/**
 * Copyright (c) 2025 GregTech-6 Team
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

import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static gregapi.data.CS.*;

public class Behavior_Spray_Color extends AbstractBehaviorDefault {
	private final ItemStack mEmpty, mUsed, mFull;
	private final long mUses;
	private final byte mColor;
	
	public Behavior_Spray_Color(ItemStack aEmpty, ItemStack aUsed, ItemStack aFull, long aUses, byte aColor) {
		mEmpty = aEmpty;
		mUsed = aUsed;
		mFull = aFull;
		mUses = aUses * 10;
		mColor = UT.Code.bind4(aColor);
		LH.add("gt.behaviour.paintspray."+mColor+".tooltip", "Can Color things in " + DYE_NAMES[mColor]);
	}
	
	@Override
	public boolean onItemUseFirst(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float hitX, float hitY, float hitZ) {
		if (aWorld.isRemote || aStack.stackSize != 1) return F;
		
		boolean rOutput = F;
		
		if (!aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		
		NBTTagCompound tNBT = UT.NBT.getNBT(aStack);
		long tUses = tNBT.getLong("gt.remaining");
		
		if (ST.equal(aStack, mFull, T)) {
			aStack.func_150996_a(mUsed.getItem());
			ST.meta_(aStack, ST.meta_(mUsed));
			tUses = mUses;
		}
		if (ST.equal(aStack, mUsed, T)) {
			if (colorize(aWorld, aX, aY, aZ, aSide)) {
				UT.Sounds.send(SFX.IC_SPRAY, aWorld, aX, aY, aZ);
				if (!UT.Entities.hasInfiniteItems(aPlayer)) tUses-=10;
				rOutput = T;
			}
		}
		
		UT.NBT.set(aStack, UT.NBT.setPosNum(tNBT, "gt.remaining", tUses));
		
		if (tUses <= 0) {
			if (mEmpty == null) {
				aStack.stackSize--;
			} else {
				aStack.func_150996_a(mEmpty.getItem());
				ST.meta_(aStack, ST.meta_(mEmpty));
			}
		}
		return rOutput;
	}
	
	@Override
	public boolean onRightClickEntity(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		if (aStack.stackSize != 1) return F;
		
		boolean rUsed = F;
		
		if (aEntity instanceof EntitySheep && !((EntitySheep)aEntity).getSheared() ) {
			if (((EntitySheep)aEntity).getFleeceColor() != (~mColor & 15)) {
				((EntitySheep)aEntity).setFleeceColor(~mColor & 15);
				if (aEntity.worldObj.isRemote) return T;
				rUsed = T;
			}
		}
		if (aEntity instanceof EntityWolf && ((EntityWolf)aEntity).isTamed()) {
			if (((EntityWolf)aEntity).getCollarColor() != (~mColor & 15)) {
				((EntityWolf)aEntity).setCollarColor(~mColor & 15);
				if (aEntity.worldObj.isRemote) return T;
				rUsed = T;
			}
		}
		
		if (rUsed) {
			NBTTagCompound tNBT = UT.NBT.getNBT(aStack);
			long tUses = tNBT.getLong("gt.remaining");
			
			if (ST.equal(aStack, mFull, T)) {
				aStack.func_150996_a(mUsed.getItem());
				ST.meta_(aStack, ST.meta_(mUsed));
				tUses = mUses;
			}
			if (ST.equal(aStack, mUsed, T) && !UT.Entities.hasInfiniteItems(aPlayer)) tUses-=50;
			
			UT.NBT.set(aStack, UT.NBT.setPosNum(tNBT, "gt.remaining", tUses));
			
			if (tUses <= 0) {
				if (mEmpty == null) {
					aStack.stackSize--;
				} else {
					aStack.func_150996_a(mEmpty.getItem());
					ST.meta_(aStack, ST.meta_(mEmpty));
				}
			}
			
			return T;
		}
		return F;
	}
	
	private final Collection<Block> mAllowedVanillaBlocks = Arrays.asList(Blocks.grass, Blocks.glass, Blocks.glass_pane, Blocks.stained_glass, Blocks.stained_glass_pane, Blocks.carpet, Blocks.hardened_clay, Blocks.stained_hardened_clay);
	
	private boolean colorize(World aWorld, int aX, int aY, int aZ, byte aSide) {
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock != NB && (mAllowedVanillaBlocks.contains(aBlock) || aBlock instanceof BlockColored || IL.TE_Rockwool.block() == aBlock || aBlock == BlocksGT.Grass)) {
			if (aBlock == Blocks.hardened_clay  ) return aWorld.setBlock(aX, aY, aZ, Blocks.stained_hardened_clay, ~mColor & 15, 3);
			if (aBlock == Blocks.glass_pane     ) return aWorld.setBlock(aX, aY, aZ, Blocks.stained_glass_pane   , ~mColor & 15, 3);
			if (aBlock == Blocks.glass          ) return aWorld.setBlock(aX, aY, aZ, Blocks.stained_glass        , ~mColor & 15, 3);
			
			if (aBlock == Blocks.grass || aBlock == BlocksGT.Grass) {
				switch(mColor) {
				case DYE_INDEX_Green    : return aWorld.setBlock(aX, aY, aZ, BlocksGT.Grass, 0, 3);
				case DYE_INDEX_Lime     : return aWorld.setBlock(aX, aY, aZ, BlocksGT.Grass, 1, 3);
				case DYE_INDEX_Black    : return aWorld.setBlock(aX, aY, aZ, BlocksGT.Grass, 2, 3);
				case DYE_INDEX_LightGray: return aWorld.setBlock(aX, aY, aZ, BlocksGT.Grass, 3, 3);
				case DYE_INDEX_Yellow   : return aWorld.setBlock(aX, aY, aZ, BlocksGT.Grass, 4, 3);
				case DYE_INDEX_Brown    : return aWorld.setBlock(aX, aY, aZ, BlocksGT.Grass, 5, 3);
				default: return F;
				}
			}
			return aWorld.getBlockMetadata(aX, aY, aZ) != (~mColor & 15) && aWorld.setBlockMetadataWithNotify(aX, aY, aZ, ~mColor & 15, 3);
		}
		return aBlock.recolourBlock(aWorld, aX, aY, aZ, FORGE_DIR[aSide], ~mColor & 15);
	}
	
	static {
		LH.add("gt.behaviour.paintspray.uses", "Remaining Uses:");
		LH.add("gt.behaviour.unstackable", "Not usable when stacked!");
	}
	
	@Override
	public List<String> getAdditionalToolTips(MultiItem aItem, List<String> aList, ItemStack aStack) {
		aList.add(LH.get("gt.behaviour.paintspray."+mColor+".tooltip"));
		NBTTagCompound tNBT = aStack.getTagCompound();
		long tRemaining = (ST.equal(aStack, mFull, T)?mUses:tNBT==null?0:tNBT.getLong("gt.remaining"));
		aList.add(LH.get("gt.behaviour.paintspray.uses") + " " + (tRemaining / 10) + "." + (tRemaining % 10));
		aList.add(LH.get("gt.behaviour.unstackable"));
		return aList;
	}
}
