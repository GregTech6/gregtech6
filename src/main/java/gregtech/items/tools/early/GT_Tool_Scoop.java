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
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

import static gregapi.data.CS.*;

public class GT_Tool_Scoop extends ToolStats {
	public static Material sBeeHiveMaterial;
	
	@Override
	public int getToolDamagePerBlockBreak() {
		return 200;
	}
	
	@Override
	public int getToolDamagePerDropConversion() {
		return 100;
	}
	
	@Override
	public int getToolDamagePerContainerCraft() {
		return 100;
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
		return 1.0F;
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
		return TOOL_scoop.equalsIgnoreCase(aBlock.getHarvestTool(aMetaData)) || aBlock.getMaterial() == sBeeHiveMaterial;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.SCOOP : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_scoop, SFX.MC_DIG_CLOTH, getToolDamagePerContainerCraft(), !canBlock(), T));
		try {
			Object tObject = UT.Reflection.callConstructor("gregtech.items.behaviors.Behavior_Scoop", 0, null, F, 200);
			if (tObject instanceof IBehavior<?>) aItem.addItemBehavior(aID, (IBehavior<MultiItem>)tObject);
		} catch (Throwable e) {
			if (MD.FR.mLoaded) e.printStackTrace(ERR);
		}
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] got scooped up by [KILLER]";
	}
}
