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

import gregapi.block.tree.BlockBaseLeaves;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.tools.ToolStats;
import gregapi.old.Textures;
import gregapi.render.IIconContainer;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

import static gregapi.data.CS.*;

public class GT_Tool_BranchCutter extends ToolStats {
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
		return 100;
	}
	
	@Override
	public int getToolDamagePerEntityAttack() {
		return 100;
	}
	
	@Override
	public float getBaseDamage() {
		return 2.5F;
	}
	
	@Override
	public float getSpeedMultiplier() {
		return 0.25F;
	}
	
	@Override
	public float getMaxDurabilityMultiplier() {
		return 0.25F;
	}
	
	@Override public boolean canCollect()                                                   {return T;}
	@Override public boolean isGrafter()                                                    {return T;}
	
	@Override
	public int convertBlockDrops(List<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, long aAvailableDurability, int aX, int aY, int aZ, byte aMetaData, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		if (aBlock.getMaterial() == Material.leaves) aEvent.dropChance = Math.min(1.0F, Math.max(aEvent.dropChance, (UT.Code.bind4(aStack.getItem().getHarvestLevel(aStack, ""))+1) * 0.2F));
		if (aBlock == Blocks.leaves) {
			aDrops.clear();
			if ((aMetaData & 3) == 0 && RNGSUS.nextInt(9) <= aFortune * 2) aDrops.add(IL.Food_Apple_Red.get(1)); else aDrops.add(ST.make(Blocks.sapling, 1, aMetaData & 3));
		} else if (aBlock == Blocks.leaves2) {
			aDrops.clear();
			aDrops.add(ST.make(Blocks.sapling, 1, (aMetaData & 3) + 4));
		} else if (aBlock == Blocks.vine) {
			aDrops.clear();
			aDrops.add(ST.make(Blocks.vine, 1, 0));
		} else if (aBlock instanceof BlockBaseLeaves) {
			aDrops.clear();
			aDrops.add(ST.make(aBlock.getItemDropped(aMetaData, RNGSUS, aFortune), 1, aBlock.damageDropped(aMetaData)));
		} else if (IL.IC2_Leaves_Rubber.equal(aBlock)) {
			aDrops.clear();
			aDrops.add(IL.IC2_Sapling_Rubber.get(1));
		} else if (IL.AETHER_Skyroot_Leaves_Gold.equal(aBlock)) {
			aDrops.clear();
			aDrops.add(IL.AETHER_Skyroot_Sapling_Gold.get(1));
		} else if (IL.AETHER_Skyroot_Leaves_Green.equal(aBlock)) {
			aDrops.clear();
			aDrops.add(IL.AETHER_Skyroot_Sapling_Green.get(1));
		} else if (IL.AETHER_Skyroot_Leaves_Blue.equal(aBlock)) {
			aDrops.clear();
			aDrops.add(IL.AETHER_Skyroot_Sapling_Blue.get(1));
		} else if (IL.AETHER_Skyroot_Leaves_Dark.equal(aBlock)) {
			aDrops.clear();
			aDrops.add(IL.AETHER_Skyroot_Sapling_Dark.get(1));
		} else if (IL.AETHER_Skyroot_Leaves_Purple.equal(aBlock)) {
			aDrops.clear();
			aDrops.add(IL.AETHER_Skyroot_Sapling_Purple.get(1));
		} else if (IL.AETHER_Skyroot_Leaves_Apple.equal(aBlock)) {
			if (RNGSUS.nextInt(9) <= aFortune * 2) aDrops.add(IL.AETHER_Apple.get(1)); else aDrops.add(IL.AETHER_Skyroot_Sapling_Purple.get(1));
		}
		return 0;
	}
	
	@Override
	public boolean isMinableBlock(Block aBlock, byte aMetaData) {
		return "grafter".equalsIgnoreCase(aBlock.getHarvestTool(aMetaData)) || aBlock == Blocks.vine || aBlock.getMaterial() == Material.leaves || IL.TF_Mazehedge.equal(aBlock);
	}
	
	@Override
	public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? Textures.ItemIcons.GRAFTER : Textures.ItemIcons.VOID;
	}
	
	@Override
	public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
		return aIsToolHead ? MultiItemTool.getPrimaryMaterial(aStack, MT.Steel).mRGBaSolid : UNCOLOURED;
	}
	
	@Override
	public String getDeathMessage() {
		return "[VICTIM] has been trimmed by [KILLER]";
	}
}
