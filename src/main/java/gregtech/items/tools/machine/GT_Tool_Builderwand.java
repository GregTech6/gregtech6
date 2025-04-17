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

package gregtech.items.tools.machine;

import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.render.IIconContainer;
import gregtech.items.behaviors.Behavior_Builderwand;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import static gregapi.data.CS.*;

public class GT_Tool_Builderwand extends ToolStats {
	@Override public int getToolDamagePerBlockBreak()                                       {return 1;}
	@Override public int getToolDamagePerDropConversion()                                   {return 100;}
	@Override public int getToolDamagePerContainerCraft()                                   {return 100;}
	@Override public int getToolDamagePerEntityAttack()                                     {return 100;}
	@Override public float getBaseDamage()                                                  {return 1.0F;}
	@Override public float getMaxDurabilityMultiplier()                                     {return 0.1F;}
	@Override public String getMiningSound()                                                {return SFX.MC_XP;}
	@Override public String getCraftingSound()                                              {return SFX.MC_XP;}
	@Override public String getEntityHitSound()                                             {return SFX.MC_XP;}
	@Override public boolean canCollect()                                                   {return T;}
	@Override public boolean isMiningTool()                                                 {return F;}
	@Override public boolean isMinableBlock(Block aBlock, byte aMetaData)                   {return IL.TC_Block_Air.equal(aBlock);}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Heliodor).mTextureSetsItems.get(OP.toolHeadBuilderwand.mIconIndexItem) : MultiItemTool.getSecondaryMaterial(aStack, MT.WOODS.Scorched).mTextureSetsItems.get(OP.stick.mIconIndexItem);
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Heliodor).mRGBaSolid : MultiItemTool.getSecondaryMaterial(aStack, MT.WOODS.Scorched).mRGBaSolid;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Builderwand());
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_builderwand, SFX.MC_XP, 100, !canBlock(), SFX.RANDOM_PITCH));
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] has been poofed out of existence by [KILLER]";
	}
	
	@Override
	public void afterBreaking(ItemStack aStack, EntityPlayer aPlayer) {
		// No bad Effects for breaking this.
	}
}
