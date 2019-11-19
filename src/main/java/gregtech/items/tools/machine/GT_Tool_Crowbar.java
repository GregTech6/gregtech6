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

package gregtech.items.tools.machine;

import static gregapi.data.CS.*;

import gregapi.data.CS.SFX;
import gregapi.data.CS.ToolsGT;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.tools.IToolStats;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GT_Tool_Crowbar extends ToolStats {
	@Override
	public int getToolDamagePerBlockBreak() {
		return 50;
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
		return 2.0F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 1.0F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 1.0F;
	}
	
	@Override
	public String getCraftingSound() {
		return SFX.MC_BREAK;
	}
	
	@Override
	public String getEntityHitSound() {
		return SFX.MC_BREAK;
	}
	
	@Override
	public String getMiningSound() {
		return SFX.MC_BREAK;
	}
	
	@Override public boolean canCollect()                                                   {return T;}
	@Override public boolean canBlock()                                                     {return T;}
	@Override public boolean isCrowbar()                                                    {return T;}
	@Override public boolean isWeapon()                                                     {return T;}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		if (aBlock instanceof BlockRailBase || aBlock.getMaterial() == Material.circuits || IL.TG_Ore_Cluster_1.equal(aBlock) || IL.TG_Ore_Cluster_2.equal(aBlock)) return T;
		String tTool = aBlock.getHarvestTool(aMetaData);
		if (UT.Code.stringValid(tTool)) return TOOL_crowbar.equalsIgnoreCase(tTool);
		for (IToolStats tStat : ToolsGT.sMetaTool.mToolStats.values()) if (!(tStat instanceof GT_Tool_Crowbar) && tStat.isMinableBlock(aBlock, aMetaData)) return F;
		return T;
	}
	
	@Override
	public float getMiningSpeed(Block aBlock, byte aMetaData, float aDefault, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ) {
		return IL.TG_Ore_Cluster_1.equal(aBlock) || IL.TG_Ore_Cluster_2.equal(aBlock) ? Float.MAX_VALUE : super.getMiningSpeed(aBlock, aMetaData, aDefault, aPlayer, aWorld, aX, aY, aZ);
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.CROWBAR : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_crowbar, SFX.MC_BREAK, 100, !canBlock()));
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] lost Half a Life to [KILLER]";
	}
}
