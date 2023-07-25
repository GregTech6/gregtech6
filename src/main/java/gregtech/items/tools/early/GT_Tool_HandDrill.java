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

package gregtech.items.tools.early;

import gregapi.data.CS.*;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import static gregapi.data.CS.*;

public class GT_Tool_HandDrill extends ToolStats {
	@Override
	public float getBaseDamage() {
		return 0.5F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 0.5F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 0.25F;
	}
	
	@Override
	public String getCraftingSound() {
		return SFX.GT_SCREWDRIVER;
	}
	
	@Override public boolean canCollect()                                                   {return T;}
	@Override public boolean isMiningTool()                                                 {return F;}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		return TOOL_drill.equalsIgnoreCase(aBlock.getHarvestTool(aMetaData));
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.HAND_DRILL : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_drill, SFX.GT_SCREWDRIVER, 100, !canBlock(), T));
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] has been tortured by [KILLER]";
	}
}
