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

package gregtech.items.tools.machine;

import static gregapi.data.CS.*;

import gregapi.data.CS.SFX;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.behaviors.Behavior_TripwireCutting;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GT_Tool_WireCutter extends ToolStats {
	@Override
	public int getToolDamagePerBlockBreak() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 400;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 200;
	}
	
	@Override
	public int getBaseQuality() {
		return 0;
	}
	
	@Override
	public float getBaseDamage() {
		return 1.25F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 1.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 1.0F;
	}
	
	@Override public boolean canCollect()                                                   {return T;}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		if (!ST.isGT(aBlock)) {
			// Just a simple way to detect various Wires from Mods I don't know of.
			String tClass = UT.Reflection.getLowercaseClass(aBlock);
			if (tClass.contains("cable") || tClass.contains("wire")) return T;
		}
		String tTool = aBlock.getHarvestTool(aMetaData);
		return tTool != null && tTool.equalsIgnoreCase(TOOL_cutter);
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.WIRE_CUTTER : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_cutter, SFX.MISSING, 100, !canBlock(), T));
		aItem.addItemBehavior(aID, new Behavior_TripwireCutting(100));
	}
	
	@Override
	public String getDeathMessage() {
		return "[KILLER] has cut the Cable for the Life Support Machine of [VICTIM]";
	}
}
