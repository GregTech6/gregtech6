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

package gregapi.compat.thaumcraft;

import static gregapi.data.CS.*;

import gregapi.util.CR;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import thaumcraft.common.container.ContainerArcaneWorkbench;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.tiles.TileArcaneWorkbench;

@Deprecated
/** Was a bad Idea that does not work well. It works but the GUI loses persistence and Item dupes may randomly happen. */
public class ContainerArcaneWorkbenchFixed extends ContainerArcaneWorkbench {
	public TileArcaneWorkbench mTileEntity;
	public InventoryPlayer mInventoryPlayer;
	
	public ContainerArcaneWorkbenchFixed(ContainerArcaneWorkbench aOriginal) {
		super((InventoryPlayer)UT.Reflection.getFieldContent(aOriginal, "ip"), (TileArcaneWorkbench)UT.Reflection.getFieldContent(aOriginal, "tileEntity"));
		mInventoryPlayer = (InventoryPlayer)UT.Reflection.getFieldContent(aOriginal, "ip");
		mTileEntity  = (TileArcaneWorkbench)UT.Reflection.getFieldContent(aOriginal, "tileEntity");
		onCraftMatrixChanged(mTileEntity);
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory aInventory) {
		if (mInventoryPlayer == null || mTileEntity == null) return;
		mTileEntity.setInventorySlotContentsSoftly(9, CR.getany(mTileEntity.getWorldObj(), mTileEntity.stackList));
		Item tWand = ST.item(mTileEntity.getStackInSlot(10));
		if (tWand instanceof ItemWandCasting && ((ItemWandCasting)tWand).consumeAllVisCrafting(mTileEntity.getStackInSlot(10), mInventoryPlayer.player, ThaumcraftCraftingManager.findMatchingArcaneRecipeAspects(mTileEntity, mInventoryPlayer.player), F)) {
			mTileEntity.setInventorySlotContentsSoftly(9, ThaumcraftCraftingManager.findMatchingArcaneRecipe(mTileEntity, mInventoryPlayer.player));
		}
	}
}
