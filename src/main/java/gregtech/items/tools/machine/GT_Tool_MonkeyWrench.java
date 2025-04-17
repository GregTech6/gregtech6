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

import gregapi.data.CS.SFX;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import static gregapi.data.CS.T;
import static gregapi.data.CS.TOOL_monkeywrench;

public class GT_Tool_MonkeyWrench extends GT_Tool_Wrench {
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		if (super.isMinableBlock(aBlock, aMetaData)) return T;
		return TOOL_monkeywrench.equalsIgnoreCase(aBlock.getHarvestTool(aMetaData));
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.MONKEYWRENCH : Textures.ItemIcons.VOID;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_monkeywrench, SFX.GT_WRENCH, 100, !canBlock(), SFX.RANDOM_PITCH));
	}
	
	@Override
	public String getDeathMessage() {
		return "[KILLER] threw a Monkey Wrench into the Plans of [VICTIM]";
	}
}
