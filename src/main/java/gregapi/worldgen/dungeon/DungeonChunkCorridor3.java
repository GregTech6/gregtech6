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

import gregapi.data.FL;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.ChestGenHooks;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkCorridor3 extends DungeonChunkCorridor {
	@Override
	public boolean generate(DungeonData aData) {
		super.generate(aData);
		
		FL[] tDrinks = new FL[] {null, null, null, null, null, FL.Purple_Drink, FL.Purple_Drink, FL.Purple_Drink, FL.Vodka, FL.Mead, FL.Whiskey_GlenMcKenner, FL.Wine_Grape_Purple};
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
			switch(aData.next(4)) {
			case 0:
				for (int tY =  0; tY <= 4; tY++) for (int tZ =  5; tZ <= 10; tZ++) aData.smooth (11, tY, tZ);
				for (int tY =  1; tY <= 3; tY++) for (int tZ =  6; tZ <=  9; tZ++) aData.air    (10, tY, tZ);
				
				aData.smooth   (10, 1, 6);
				aData.set      (10, 1, 7, Blocks.crafting_table, 0, 2);
				aData.set      (10, 1, 8, SIDE_UNKNOWN, (short)((aData.next1in2()?508:8)+aData.next(3)), UT.NBT.make("gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_X_NEG), T, T);
				aData.smooth   (10, 1, 9);
				
				aData.coins    (10, 2, 6);
				aData.cup      (10, 2, 7, UT.Code.select(null, tDrinks));
				
				aData.coins    (10, 2, 9);
				return T;
			// Breakable Wall at the unused end.
			case 1:
				for (int tY =  1; tY <= 3; tY++) for (int tZ =  6; tZ <=  9; tZ++) aData.cobbles(10, tY, tZ);
				// TODO More than this, lol.
				return T;
			}
			// Default Corridor Crossing.
			return T;
		}
		
		
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
			switch(aData.next(4)) {
			case 0:
				for (int tY =  0; tY <= 4; tY++) for (int tZ =  5; tZ <= 10; tZ++) aData.smooth (4, tY, tZ);
				for (int tY =  1; tY <= 3; tY++) for (int tZ =  6; tZ <=  9; tZ++) aData.air    (5, tY, tZ);
				
				aData.smooth   (5, 1, 6);
				aData.set      (5, 1, 7, SIDE_UNKNOWN, (short)((aData.next1in2()?508:8)+aData.next(3)), UT.NBT.make("gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_X_POS), T, T);
				aData.set      (5, 1, 8, Blocks.crafting_table, 0, 2);
				aData.smooth   (5, 1, 9);
				
				aData.coins    (5, 2, 6);
				
				aData.cup      (5, 2, 8, UT.Code.select(null, tDrinks));
				aData.coins    (5, 2, 9);
				return T;
			// Breakable Wall at the unused end.
			case 1:
				for (int tY =  1; tY <= 3; tY++) for (int tZ =  6; tZ <=  9; tZ++) aData.cobbles(5, tY, tZ);
				// TODO More than this, lol.
				return T;
			}
			// Default Corridor Crossing.
			return T;
		}
		
		
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
			switch(aData.next(4)) {
			case 0:
				for (int tY =  0; tY <= 4; tY++) for (int tX =  5; tX <= 10; tX++) aData.smooth (tX, tY, 11);
				for (int tY =  1; tY <= 3; tY++) for (int tX =  6; tX <=  9; tX++) aData.air    (tX, tY, 10);
				
				aData.smooth   (6, 1, 10);
				aData.set      (7, 1, 10, Blocks.crafting_table, 0, 2);
				aData.set      (8, 1, 10, SIDE_UNKNOWN, (short)((aData.next1in2()?508:8)+aData.next(3)), UT.NBT.make("gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_Z_NEG), T, T);
				aData.smooth   (9, 1, 10);
				
				aData.coins    (6, 2, 10);
				aData.cup      (7, 2, 10, UT.Code.select(null, tDrinks));
				
				aData.coins    (9, 2, 10);
				return T;
			// Breakable Wall at the unused end.
			case 1:
				for (int tY =  1; tY <= 3; tY++) for (int tX =  6; tX <=  9; tX++) aData.cobbles(tX, tY, 10);
				// TODO More than this, lol.
				return T;
			}
			// Default Corridor Crossing.
			return T;
		}
		
		
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
			switch(aData.next(4)) {
			case 0:
				for (int tY =  0; tY <= 4; tY++) for (int tX =  5; tX <= 10; tX++) aData.smooth (tX, tY, 4);
				for (int tY =  1; tY <= 3; tY++) for (int tX =  6; tX <=  9; tX++) aData.air    (tX, tY, 5);
				
				aData.smooth   (6, 1, 5);
				aData.set      (7, 1, 5, SIDE_UNKNOWN, (short)((aData.next1in2()?508:8)+aData.next(3)), UT.NBT.make("gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_Z_POS), T, T);
				aData.set      (8, 1, 5, Blocks.crafting_table, 0, 2);
				aData.smooth   (9, 1, 5);
				
				aData.coins    (6, 2, 5);
				
				aData.cup      (8, 2, 5, UT.Code.select(null, tDrinks));
				aData.coins    (9, 2, 5);
				return T;
			// Breakable Wall at the unused end.
			case 1:
				for (int tY =  1; tY <= 3; tY++) for (int tX =  6; tX <=  9; tX++) aData.cobbles(tX, tY, 5);
				// TODO More than this, lol.
				return T;
			}
			// Default Corridor Crossing.
			return T;
		}
		// Default Corridor Crossing.
		return T;
	}
}
