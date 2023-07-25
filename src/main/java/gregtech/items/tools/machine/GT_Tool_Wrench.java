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

package gregtech.items.tools.machine;

import gregapi.block.misc.BlockBaseBars;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

import static gregapi.data.CS.*;

public class GT_Tool_Wrench extends ToolStats {
	public static final List<String> mEffectiveList = Arrays.asList(
		"EntityTowerGuardian"
	);
	
	@Override
	public float getNormalDamageAgainstEntity(float aOriginalDamage, Entity aEntity, ItemStack aStack, EntityPlayer aPlayer) {
		String tName = aEntity.getClass().getName();
		tName = tName.substring(tName.lastIndexOf(".")+1);
		return aEntity instanceof EntityIronGolem || mEffectiveList.contains(tName) || tName.contains("Golem") ? aOriginalDamage*2 : aOriginalDamage;
	}
	
	@Override public int getToolDamagePerBlockBreak()                                       {return  50;}
	@Override public int getToolDamagePerContainerCraft()                                   {return 800;}
	@Override public int getToolDamagePerEntityAttack()                                     {return 200;}
	@Override public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity) {return aOriginalHurtResistance * 2;}
	@Override public float getBaseDamage()                                                  {return 5.0F;}
	@Override public float getExhaustionPerAttack(Entity aEntity)                           {return 0.6F;}
	@Override public String getCraftingSound()                                              {return SFX.GT_WRENCH;}
	@Override public String getMiningSound()                                                {return SFX.GT_WRENCH;}
	@Override public boolean canCollect()                                                   {return T;}
	@Override public boolean isWrench()                                                     {return T;}
	
	public List<String> mRegistryNamePrefixes = new ArrayListNoNulls<>(F, "BuildCraft|", "Forestry:core", "Forestry:mail", "Forestry:alveary", "Forestry:factory", "Forestry:engine", "Forestry:ffarm", "Railcraft:machine", "progressiveautomation", "MineFactoryReloaded:machine", "MineFactoryReloaded:rednet", "MCFrames:", MD.FUNK.mID, MD.WARPDRIVE.mID);
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		if (aBlock.getMaterial() == Material.piston || aBlock.getMaterial() == Material.redstoneLight || aBlock instanceof BlockBaseBars || aBlock == Blocks.hopper || aBlock == Blocks.dispenser || aBlock == Blocks.dropper) return T;
		if (TOOL_wrench.equalsIgnoreCase(aBlock.getHarvestTool(aMetaData))) return T;
		if (aBlock.getMaterial().isLiquid()) return F;
		String tName = ST.regName(aBlock);
		if (UT.Code.stringInvalid(tName)) return F;
		if (MD.AE.owns_(tName)) return aBlock.getMaterial() == Material.iron || aBlock.getMaterial() == Material.anvil;
		for (String tPrefix : mRegistryNamePrefixes) if (tName.startsWith(tPrefix)) return T;
		return F;
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.WRENCH : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	public void onStatsAddedToTool(MultiItemTool aItem, int aID) {
		aItem.addItemBehavior(aID, new Behavior_Tool(TOOL_wrench, SFX.GT_WRENCH, 100, !canBlock(), T));
	}
	
	@Override
	public String getDeathMessage() {
		return "[KILLER] gave [VICTIM] a whack with the Wrench!";
	}
}
