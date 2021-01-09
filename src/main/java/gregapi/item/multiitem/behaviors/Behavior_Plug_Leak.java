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

package gregapi.item.multiitem.behaviors;

import static gregapi.data.CS.*;

import gregapi.block.IPrefixBlock;
import gregapi.data.TD;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.behaviors.IBehavior.AbstractBehaviorDefault;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Behavior_Plug_Leak extends AbstractBehaviorDefault {
	public static final Behavior_Plug_Leak INSTANCE = new Behavior_Plug_Leak();
	
	@Override
	public boolean onItemUse(MultiItem aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aWorld.isRemote || aPlayer == null || !aPlayer.canPlayerEdit(aX, aY, aZ, aSide, aStack)) return F;
		for (byte tSide : ALL_SIDES) {
			// Only place right next to Liquids or inside of Liquids.
			if (!WD.liquid(WD.block(aWorld, aX+OFFSETS_X[aSide]+OFFSETS_X[tSide], aY+OFFSETS_Y[aSide]+OFFSETS_Y[tSide], aZ+OFFSETS_Z[aSide]+OFFSETS_Z[tSide]))) continue;
			// Scan Inventory for suitable Items.
			for (int i = 0; i < aPlayer.inventory.mainInventory.length; i++) {
				int tIndex = aPlayer.inventory.mainInventory.length-i-1;
				ItemStack tStack = aPlayer.inventory.mainInventory[tIndex];
				if (ST.invalid(tStack)) continue;
				Block tBlock = ST.block(tStack);
				// The Block has to be Opaque to ensure the Leak is plugged.
				if (tBlock == NB || !tBlock.isOpaqueCube()) continue;
				// Don't use any PrefixBlocks, TileEntities or Silverfish Blocks.
				if (tBlock instanceof IPrefixBlock || tBlock instanceof ITileEntityProvider || tBlock instanceof BlockSilverfish) continue;
				// Only use Blocks that are typically mined.
				if (tBlock.getMaterial() != Material.rock && tBlock.getMaterial() != Material.ground && tBlock.getMaterial() != Material.sand && tBlock.getMaterial() != Material.clay) continue;
				// Don't use frikkin Ore Blocks or Storage Blocks for this!
				if (OM.prefixcontainsany(OM.anydata(tStack), TD.Prefix.ORE, TD.Prefix.STORAGE_BASED)) continue;
				
				int tOldSize = tStack.stackSize;
				if (tStack.tryPlaceItemIntoWorld(aPlayer, aWorld, aX, aY, aZ, aSide, aHitX, aHitY, aHitZ)) {
					if (UT.Entities.hasInfiniteItems(aPlayer)) {
						tStack.stackSize = tOldSize;
					} else {
						ST.use(aPlayer, tIndex, tStack, 0);
					}
					return T;
				}
				return F;
			}
			return F;
		}
		return F;
	}
}
