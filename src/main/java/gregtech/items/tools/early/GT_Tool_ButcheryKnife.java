/**
 * Copyright (c) 2022 GregTech-6 Team
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

import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

import static gregapi.data.CS.*;

public class GT_Tool_ButcheryKnife extends ToolStats {
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
		return 400;
	}
	
	@Override
	public float getBaseDamage() {
		return 1.0F;
	}
	
	@Override
	public int getHurtResistanceTime(int aOriginalHurtResistance, Entity aEntity) {
		return aOriginalHurtResistance * 2;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 0.1F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 1.0F;
	}
	
	@Override
	public boolean isWeapon() {
		return T;
	}
	
	@Override
	public boolean isMiningTool() {
		return F;
	}
	
	@Override
	public Enchantment[] getEnchantments(ItemStack aStack, OreDictMaterial aMaterial) {
		return LOOTING_ENCHANTMENT;
	}
	
	@Override
	public int[] getEnchantmentLevels(ItemStack aStack, OreDictMaterial aMaterial) {
		return new int[] {aMaterial.mToolQuality / 2 + 1};
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? Textures.ItemIcons.BUTCHERYKNIFE : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return !aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	public String getDeathMessage() {
		return "[KILLER] butchered [VICTIM]!";
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		return false;
	}
}
