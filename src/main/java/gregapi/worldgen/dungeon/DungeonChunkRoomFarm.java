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

package gregapi.worldgen.dungeon;

import static gregapi.data.CS.*;

import gregapi.data.CS.BlocksGT;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.ChestGenHooks;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomFarm extends DungeonChunkRoomEmpty {
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mConnectionCount != 2) return F;
		super.generate(aData);
		
		
		// TODO: WIP
		
		
		for (int tX = 3; tX <= 12; tX++) for (int tZ = 3; tZ <= 12; tZ++) {
			if ((tX == 3 || tX == 12 || tZ == 3 || tZ == 12)) {
				aData.colored(tX,  0, tZ);
				aData.colored(tX, -1, tZ);
				aData.bricks (tX, -2, tZ);
			} else {
				aData.bricks (tX, -3, tZ);
				aData.colored(tX, -2, tZ);
				aData.set(tX,  0, tZ, Blocks.water, 0, 2);
				aData.set(tX, -1, tZ, Blocks.water, 0, 2);
				if (aData.next1in2()) aData.set(tX, 1, tZ, BlocksGT.Glowtus, aData.next(16), 0);
			}
		}
		
		short tChestType = (short)((aData.next1in2()?508:8)+aData.next(3));
		
		if (aData.next1in2()) aData.set( 1, 1,  1, SIDE_UNKNOWN, tChestType, UT.NBT.make("gt.dungeonloot", ChestGenHooks.BONUS_CHEST, NBT_FACING, SIDE_Z_POS), T, T);
		if (aData.next1in2()) aData.set(14, 1,  1, SIDE_UNKNOWN, tChestType, UT.NBT.make("gt.dungeonloot", ChestGenHooks.BONUS_CHEST, NBT_FACING, SIDE_X_NEG), T, T);
		if (aData.next1in2()) aData.set( 1, 1, 14, SIDE_UNKNOWN, tChestType, UT.NBT.make("gt.dungeonloot", ChestGenHooks.BONUS_CHEST, NBT_FACING, SIDE_X_POS), T, T);
		if (aData.next1in2()) aData.set(14, 1, 14, SIDE_UNKNOWN, tChestType, UT.NBT.make("gt.dungeonloot", ChestGenHooks.BONUS_CHEST, NBT_FACING, SIDE_Z_NEG), T, T);
		return T;
	}
}
