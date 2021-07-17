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

package gregapi.worldgen.dungeon;

import static gregapi.data.CS.*;

import gregapi.data.CS.BlocksGT;
import gregapi.data.MD;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ChestGenHooks;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomFarmFish extends DungeonChunkRoomEmpty {
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mTags.contains(WorldgenDungeonGT.TAG_FARM_FISH) || !super.generate(aData)) return F;
		aData.mTags.add(WorldgenDungeonGT.TAG_FARM_FISH);
		
		for (int tX = 3; tX <= 12; tX++) for (int tZ = 3; tZ <= 12; tZ++) {
			if ((tX == 3 || tX == 12 || tZ == 3 || tZ == 12)) {
				aData.colored(tX,  0, tZ);
				aData.colored(tX, -1, tZ);
				aData.bricks (tX, -2, tZ);
			} else {
				aData.bricks (tX, -3, tZ);
				aData.colored(tX, -2, tZ);
				aData.set    (tX,  0, tZ, Blocks.water, 0, 2);
				aData.set    (tX, -1, tZ, Blocks.water, 0, 2);
				if (aData.next1in2()) aData.set(tX, 1, tZ, BlocksGT.Glowtus, aData.nextMetaA(), 0);
			}
		}
		
		Block tFishTrap = ST.block(MD.HaC, "fishtrap", null);
		if (tFishTrap != null) {
			aData.air( 6,  1,  6);
			aData.air( 6,  1,  9);
			aData.air( 9,  1,  6);
			aData.air( 9,  1,  9);
			
			aData.set( 6,  0,  6, tFishTrap);
			aData.set( 6,  0,  9, tFishTrap);
			aData.set( 9,  0,  6, tFishTrap);
			aData.set( 9,  0,  9, tFishTrap);
			
			TileEntity
			tTileEntity = WD.te(aData.mWorld, aData.mX+6, aData.mY, aData.mZ+6, T); if (tTileEntity instanceof IInventory) ((IInventory)tTileEntity).setInventorySlotContents(18, ST.make(MD.HaC, "fishtrapbaitItem", aData.nextStack()));
			tTileEntity = WD.te(aData.mWorld, aData.mX+6, aData.mY, aData.mZ+9, T); if (tTileEntity instanceof IInventory) ((IInventory)tTileEntity).setInventorySlotContents(18, ST.make(MD.HaC, "fishtrapbaitItem", aData.nextStack()));
			tTileEntity = WD.te(aData.mWorld, aData.mX+9, aData.mY, aData.mZ+6, T); if (tTileEntity instanceof IInventory) ((IInventory)tTileEntity).setInventorySlotContents(18, ST.make(MD.HaC, "fishtrapbaitItem", aData.nextStack()));
			tTileEntity = WD.te(aData.mWorld, aData.mX+9, aData.mY, aData.mZ+9, T); if (tTileEntity instanceof IInventory) ((IInventory)tTileEntity).setInventorySlotContents(18, ST.make(MD.HaC, "fishtrapbaitItem", aData.nextStack()));
		}
		
		if (aData.next1in2()) aData.set( 1, 1,  1, SIDE_UNKNOWN, 508+aData.next(3), UT.NBT.make("gt.dungeonloot", ChestGenHooks.BONUS_CHEST, NBT_FACING, SIDE_Z_POS), T, T);
		if (aData.next1in2()) aData.set(14, 1,  1, SIDE_UNKNOWN, 508+aData.next(3), UT.NBT.make("gt.dungeonloot", ChestGenHooks.BONUS_CHEST, NBT_FACING, SIDE_X_NEG), T, T);
		if (aData.next1in2()) aData.set( 1, 1, 14, SIDE_UNKNOWN, 508+aData.next(3), UT.NBT.make("gt.dungeonloot", ChestGenHooks.BONUS_CHEST, NBT_FACING, SIDE_X_POS), T, T);
		if (aData.next1in2()) aData.set(14, 1, 14, SIDE_UNKNOWN, 508+aData.next(3), UT.NBT.make("gt.dungeonloot", ChestGenHooks.BONUS_CHEST, NBT_FACING, SIDE_Z_NEG), T, T);
		return T;
	}
}
