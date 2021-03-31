/**
 * Copyright (c) 2020 GregTech-6 Team
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

package gregtech.asm.transformers.minecraft;

import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.ArrayList;
import java.util.Random;

/* This is a separate file so it class loads *while* minecraft loads,
   if we accessed world in the main transformer then we can miss out
   on the transformations.  Not an issue when accessing MC classes
   while transforming other mods though.
 */
public class Replacements {
	public static void BlockIce_harvestBlock(BlockIce self, World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_)
	{
		p_149636_2_.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(self)], 1);
		p_149636_2_.addExhaustion(0.025F);

		if (self.canSilkHarvest(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_) && EnchantmentHelper.getSilkTouchModifier(p_149636_2_))
		{
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			ItemStack itemstack = (ItemStack)UT.Reflection.callPrivateMethod(Block.class, "createStackedBlock", p_149636_6_);
			//ItemStack itemstack = self.createStackedBlock(p_149636_6_);

			if (itemstack != null) items.add(itemstack);

			ForgeEventFactory.fireBlockHarvesting(items, p_149636_1_, self, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_, 0, 1.0f, true, p_149636_2_);
			for (ItemStack is : items) {
				UT.Reflection.callPrivateMethod(Block.class, "dropBlocksAsItem", p_149636_1_, p_149636_3_, p_149636_4_, p_149636_5_, is);
				//self.dropBlockAsItem(p_149636_1_, p_149636_3_, p_149636_4_, p_149636_5_, is);
			}
		}
		else
		{
			if (p_149636_1_.provider.isHellWorld)
			{
				p_149636_1_.setBlockToAir(p_149636_3_, p_149636_4_, p_149636_5_);
				return;
			}

			Material material = p_149636_1_.getBlock(p_149636_3_, p_149636_4_ - 1, p_149636_5_).getMaterial();
			if (material.blocksMovement() || material.isLiquid())
			{
				p_149636_1_.setBlock(p_149636_3_, p_149636_4_, p_149636_5_, Blocks.flowing_water);
			}

			int i1 = EnchantmentHelper.getFortuneModifier(p_149636_2_);
			@SuppressWarnings("unchecked")
			ThreadLocal<EntityPlayer> harvesters = (ThreadLocal<EntityPlayer>)UT.Reflection.getFieldContent(Block.class, "harvesters");
			harvesters.set(p_149636_2_);
			self.dropBlockAsItem(p_149636_1_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_, i1);
			harvesters.set(null);
		}
	}

	public static void BlockStaticLiquid_updateTick(BlockStaticLiquid self, World world, int x, int y, int z, Random rand) {
		if (self.getMaterial() == Material.lava)
		{
			int l = rand.nextInt(3);
			int i1;

			for (i1 = 0; i1 < l; ++i1)
			{
				x += rand.nextInt(3) - 1;
				++y;
				z += rand.nextInt(3) - 1;
				Block block = world.getBlock(x, y, z);

				if (block.getMaterial() == Material.air)
				{
					if (
						BlockStaticLiquid_isFlammable(world, x - 1, y, z, ForgeDirection.EAST) ||
						BlockStaticLiquid_isFlammable(world, x + 1, y, z, ForgeDirection.WEST) ||
						BlockStaticLiquid_isFlammable(world, x, y, z - 1, ForgeDirection.SOUTH) ||
						BlockStaticLiquid_isFlammable(world, x, y, z + 1, ForgeDirection.NORTH) ||
						BlockStaticLiquid_isFlammable(world, x, y - 1, z, ForgeDirection.UP) ||
						BlockStaticLiquid_isFlammable(world, x, y + 1, z, ForgeDirection.DOWN))
					{
						world.setBlock(x, y, z, Blocks.fire);
						return;
					}
				}
				else if (block.getMaterial().blocksMovement())
				{
					return;
				}
			}

			if (l == 0)
			{
				i1 = x;
				int k1 = z;

				for (int j1 = 0; j1 < 3; ++j1)
				{
					x = i1 + rand.nextInt(3) - 1;
					z = k1 + rand.nextInt(3) - 1;

					if (world.isAirBlock(x, y + 1, z) && BlockStaticLiquid_isFlammable(world, x, y, z, ForgeDirection.UP))
					{
						world.setBlock(x, y + 1, z, Blocks.fire);
					}
				}
			}
		}
	}

	public static boolean BlockStaticLiquid_isFlammable(World world, int x, int y, int z, ForgeDirection dir) {
		return world.getBlock(x, y, z).isFlammable(world, x, y, z, dir);
	}

	public static boolean BlockStaticLiquid_isFlammable(World world, int x, int y, int z) {
		return world.getBlock(x, y, z).isFlammable(world, x, y, z, ForgeDirection.UNKNOWN);
	}
}
