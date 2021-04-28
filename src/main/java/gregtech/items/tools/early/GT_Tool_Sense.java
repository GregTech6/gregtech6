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

package gregtech.items.tools.early;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

public class GT_Tool_Sense extends ToolStats {
	private ThreadLocal<Object> sIsHarvestingRightNow = new ThreadLocal<>();
	
	@Override
	public float getBaseDamage() {
		return 3.00F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 4.0F;
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		String tTool = aBlock.getHarvestTool(aMetaData);
		return (tTool != null && (tTool.equalsIgnoreCase(TOOL_sense) || tTool.equalsIgnoreCase(TOOL_scythe))) || aBlock.getMaterial() == Material.plants || aBlock.getMaterial() == Material.leaves || aBlock.getMaterial() == Material.vine || IL.TF_Mazehedge.equal(aBlock) || IL.NeLi_Wart_Block_Crimson.equal(aBlock);
	}
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		int rConversions = 0;
		if (sIsHarvestingRightNow.get() == null && aPlayer instanceof EntityPlayerMP) {
			sIsHarvestingRightNow.set(this);
			for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) for (int k = -1; k < 2; k++) if (i != 0 || j != 0 || k != 0) if (aStack.getItem().getDigSpeed(aStack, aPlayer.worldObj.getBlock(aX+i, aY+j, aZ+k), aPlayer.worldObj.getBlockMetadata(aX+i, aY+j, aZ+k)) > 0) if (((EntityPlayerMP)aPlayer).theItemInWorldManager.tryHarvestBlock(aX+i, aY+j, aZ+k)) rConversions++;
			sIsHarvestingRightNow.set(null);
		}
		if (!harvestGrass(aDrops, aStack, aPlayer, aBlock, aAvailableDurability, aX, aY, aZ, aMetaData, aFortune, aSilkTouch, aEvent)) {
			harvestStick(aDrops, aStack, aPlayer, aBlock, aAvailableDurability, aX, aY, aZ, aMetaData, aFortune, aSilkTouch, aEvent);
		}
		return rConversions;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mTextureSetsItems.get(OP.toolHeadSense.mIconIndexItem) : MultiItemTool.getSecondaryMaterial(aStack, MT.WOODS.Spruce).mTextureSetsItems.get(OP.stick.mIconIndexItem);
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : MultiItemTool.getSecondaryMaterial(aStack, MT.WOODS.Spruce).mRGBaSolid;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_sense, 100, !canBlock()));
	}
	
	@Override
	public String getDeathMessage() {
		return "[KILLER] has taken the Soul of [VICTIM]";
	}
}
