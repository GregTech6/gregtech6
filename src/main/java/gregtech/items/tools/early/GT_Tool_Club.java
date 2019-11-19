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

package gregtech.items.tools.early;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.CS.SFX;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.world.BlockEvent;

public class GT_Tool_Club extends GT_Tool_HardHammer {
	@Override public int getToolDamagePerBlockBreak()                                       {return  50;}
	@Override public int getToolDamagePerDropConversion()                                   {return 100;}
	@Override public int getToolDamagePerContainerCraft()                                   {return 800;}
	@Override public int getToolDamagePerEntityAttack()                                     {return  50;}
	@Override public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity) {return (aOriginalHurtResistance * 3) / 2;}
	@Override public float getSpeedMultiplier()                                             {return 0.5F;}
	@Override public String getCraftingSound()                                              {return SFX.MC_DIG_ROCK;}
	@Override public String getBreakingSound()                                              {return SFX.MC_BREAK;}
	@Override public boolean canBlock()                                                     {return T;}
	@Override public boolean isWeapon()                                                     {return T;}
	@Override public boolean isMiningTool()                                                 {return F;}
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		if (aBlock == Blocks.stone || aBlock == Blocks.cobblestone || aBlock == Blocks.mossy_cobblestone || aBlock == Blocks.stonebrick || aBlock == Blocks.stone_brick_stairs || aBlock == Blocks.cobblestone_wall || aBlock == Blocks.stone_button || aBlock == Blocks.stone_pressure_plate) {
			aDrops.clear();
			aDrops.add(OP.rockGt.mat(MT.Stone, 1+RNGSUS.nextInt(4)));
			return 0;
		}
		if (aBlock == Blocks.netherrack) {
			aDrops.clear();
			aDrops.add(OP.rockGt.mat(MT.Netherrack, 1+RNGSUS.nextInt(4)));
			return 0;
		}
		if (aBlock == Blocks.nether_brick || aBlock == Blocks.nether_brick_stairs || aBlock == Blocks.nether_brick_fence) {
			aDrops.clear();
			aDrops.add(OP.rockGt.mat(MT.NetherBrick, 1+RNGSUS.nextInt(4)));
			return 0;
		}
		if (aBlock == Blocks.end_stone) {
			aDrops.clear();
			aDrops.add(OP.rockGt.mat(MT.Endstone, 1+RNGSUS.nextInt(4)));
			return 0;
		}
		return super.convertBlockDrops(aDrops, aStack, aPlayer, aBlock, aAvailableDurability, aX, aY, aZ, aMetaData, aFortune, aSilkTouch, aEvent);
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? Textures.ItemIcons.CLUB : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	public void onToolCrafted(ItemStack aStack, EntityPlayer aPlayer) {
		super.onToolCrafted(aStack, aPlayer);
		aPlayer.triggerAchievement(AchievementList.buildSword);
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		// No Behaviors
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got welcomed into the club by [KILLER]";
	}
	
	@Override
	public IChatComponent getDeathMessage(EntityLivingBase aPlayer, EntityLivingBase aEntity, String aNamePlayer, String aNameEntity) {
		return aNamePlayer.equalsIgnoreCase("Bear989Sr") ? new ChatComponentText(EnumChatFormatting.RED+aNameEntity+EnumChatFormatting.WHITE+" got clubbed by a Bear!") : super.getDeathMessage(aPlayer, aEntity, aNamePlayer, aNameEntity);
	}
}
