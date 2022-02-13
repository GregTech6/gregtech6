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

package gregtech.items.tools.crafting;

import static gregapi.data.CS.*;

import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GT_Tool_RollingPin extends ToolStats {
	@Override
	public int getToolDamagePerBlockBreak() {
		return 50;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 50;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 50;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 200;
	}
	
	@Override
	public float getBaseDamage() {
		return 2.0F;
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		return F;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		//
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.ROLLING_PIN : Textures.ItemIcons.VOID;
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got flattened by [KILLER]";
	}
}
