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
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

public class GT_Tool_Plow extends ToolStats {
	private ThreadLocal<Object> sIsHarvestingRightNow = new ThreadLocal<>();
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 10;
	}
	
	@Override
	public float getNormalDamageAgainstEntity(float aOriginalDamage, Entity aEntity, ItemStack aStack, EntityPlayer aPlayer) {
		return aEntity instanceof EntitySnowman ? aOriginalDamage*4 : aOriginalDamage;
	}
	
	@Override
	public float getBaseDamage() {
		return 1.00F;
	}
	
	@Override public boolean canCollect()                                                   {return T;}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		return (tTool != null && tTool.equalsIgnoreCase(TOOL_plow)) || aBlock.getMaterial() == Material.snow || aBlock.getMaterial() == Material.craftedSnow;
	}
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		int rConversions = 0;
		if (sIsHarvestingRightNow.get() == null && aPlayer instanceof EntityPlayerMP) {
			sIsHarvestingRightNow.set(this);
			for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) for (int k = -1; k < 2; k++) if (i != 0 || j != 0 || k != 0) if (aStack.getItem().getDigSpeed(aStack, aPlayer.worldObj.getBlock(aX+i, aY+j, aZ+k), aPlayer.worldObj.getBlockMetadata(aX+i, aY+j, aZ+k)) > 0) if (((EntityPlayerMP)aPlayer).theItemInWorldManager.tryHarvestBlock(aX+i, aY+j, aZ+k)) rConversions++;
			sIsHarvestingRightNow.set(null);
		}
		return rConversions;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadPlow.mIconIndexItem) : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mTextureSetsItems.get(OP.stick.mIconIndexItem);
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : MultiItemTool.getSecondaryMaterial(aStack, MT.Wood).mRGBaSolid;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_shovel, SFX.MC_DIG_GRAVEL, 10, F));
	}
	
	@Override
	public String getDeathMessage() {
		return "[KILLER] plew through the yard of [VICTIM]";
	}
}
