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

package gregtech.items.tools.electric;

import gregapi.block.MaterialAdventure;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Place_Sapling;
import gregapi.item.multiitem.behaviors.Behavior_Place_Workbench;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregapi.util.UT;
import gregtech.items.tools.early.GT_Tool_Axe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.event.world.BlockEvent;

import java.util.ArrayList;
import java.util.List;

import static gregapi.data.CS.*;

public class GT_Tool_Chainsaw_LV extends GT_Tool_Axe {
	@Override public int getBaseQuality                () {return 1;}
	@Override public int getToolDamagePerContainerCraft() {return 200;}
	@Override public int getToolDamagePerEntityAttack  () {return super.getToolDamagePerEntityAttack() * 4;}
	@Override public float getSpeedMultiplier          () {return 2.0F;}
	@Override public String getCraftingSound           () {return SFX.IC_CHAINSAW_01;}
	@Override public String getEntityHitSound          () {return SFX.IC_CHAINSAW_02;}
	@Override public String getMiningSound             () {return SFX.IC_CHAINSAW_01;}
	
	@Override
	public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity) {
		if (aEntity instanceof EntityCreeper) return aOriginalHurtResistance / 3;
		return aOriginalHurtResistance;
	}
	
	@Override
	public DamageSource getDamageSource(EntityLivingBase aPlayer, Entity aEntity) {
		if (MD.IC2.mLoaded && aPlayer instanceof EntityPlayer && aEntity instanceof EntityCreeper) try {
		UT.Inventories.unlockAchievement(((EntityPlayer)aPlayer), AchievementList.acquireIron);
		ic2.core.IC2.achievements.issueAchievement((EntityPlayer)aPlayer, "buildCable");
		ic2.core.IC2.achievements.issueAchievement((EntityPlayer)aPlayer, "buildGenerator");
		ic2.core.IC2.achievements.issueAchievement((EntityPlayer)aPlayer, "buildBatBox");
		ic2.core.IC2.achievements.issueAchievement((EntityPlayer)aPlayer, "buildChainsaw");
		ic2.core.IC2.achievements.issueAchievement((EntityPlayer)aPlayer, "killCreeperChainsaw");
		} catch(Throwable e) {e.printStackTrace(ERR);}
		return super.getDamageSource(aPlayer, aEntity);
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		return (tTool != null && (tTool.equalsIgnoreCase(TOOL_axe) || tTool.equalsIgnoreCase(TOOL_saw))) || aBlock.getMaterial() == Material.wood || aBlock.getMaterial() == MaterialAdventure.WOOD || aBlock.getMaterial() == Material.cactus || aBlock.getMaterial() == Material.leaves || aBlock.getMaterial() == Material.vine || aBlock.getMaterial() == Material.plants || aBlock.getMaterial() == Material.gourd || aBlock.getMaterial() == Material.ice || aBlock.getMaterial() == Material.packedIce || aBlock.getMaterial() == Material.coral;
	}
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		if (aBlock.getMaterial() == Material.leaves && aBlock instanceof IShearable) {
			aPlayer.worldObj.setBlock(aX, aY, aZ, aBlock, aMetaData, 0);
			if (((IShearable)aBlock).isShearable(aStack, aPlayer.worldObj, aX, aY, aZ)) {
				ArrayList<ItemStack> tDrops = ((IShearable)aBlock).onSheared(aStack, aPlayer.worldObj, aX, aY, aZ, aFortune);
				aDrops.clear();
				aDrops.addAll(tDrops);
				aEvent.dropChance = 1.0F;
			}
			aPlayer.worldObj.setBlock(aX, aY, aZ, NB, 0, 0);
			return 0;
		}
		if ((aBlock.getMaterial() == Material.ice || aBlock.getMaterial() == Material.packedIce) && aDrops.isEmpty()) {
			aDrops.add(ST.make(aBlock, 1, aMetaData));
			aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
			aEvent.dropChance = 1.0F;
			return 0;
		}
		return super.convertBlockDrops(aDrops, aStack, aPlayer, aBlock, aAvailableDurability, aX, aY, aZ, aMetaData, aFortune, aSilkTouch, aEvent);
	}
	
	@Override
	public float getMiningSpeed(Block aBlock, byte aMetaData, float aDefault, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
		return aBlock.getMaterial() == Material.leaves || aBlock.getMaterial() == Material.vine || aBlock.getMaterial() == Material.plants || aBlock.getMaterial() == Material.gourd ? aDefault : super.getMiningSpeed(aBlock, aMetaData, aDefault, aPlayer, aWorld, aX, aY, aZ);
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.POWER_UNIT_LV : ST.meta(aStack) % 2 != 0 ? Textures.ItemIcons.VOID : MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadChainsaw.mIconIndexItem);
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getSecondaryMaterial(aStack, MT.StainlessSteel).mRGBaSolid : MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid;
	}
	
	@Override
	public void onToolCrafted(ItemStack aStack, EntityPlayer aPlayer) {
		super.onToolCrafted(aStack, aPlayer);
		if (MD.IC2.mLoaded) try {
		aPlayer.triggerAchievement(AchievementList.buildPickaxe);
		aPlayer.triggerAchievement(AchievementList.buildFurnace);
		aPlayer.triggerAchievement(AchievementList.acquireIron);
		ic2.core.IC2.achievements.issueAchievement(aPlayer, "buildCable");
		ic2.core.IC2.achievements.issueAchievement(aPlayer, "buildGenerator");
		ic2.core.IC2.achievements.issueAchievement(aPlayer, "buildBatBox");
		ic2.core.IC2.achievements.issueAchievement(aPlayer, "buildChainsaw");
		} catch(Throwable e) {e.printStackTrace(ERR);}
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_saw, SFX.MC_DIG_WOOD, getToolDamagePerContainerCraft(), F, SFX.RANDOM_PITCH));
		aItem.addItemBehavior(aID, Behavior_Place_Sapling.INSTANCE);
		aItem.addItemBehavior(aID, Behavior_Place_Workbench.INSTANCE);
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] was massacred by [KILLER]";
	}
}
