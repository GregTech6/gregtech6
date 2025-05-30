/**
 * Copyright (c) 2025 GregTech-6 Team
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

import gregapi.util.UT;
import net.minecraft.init.Blocks;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkDoorPiston implements IDungeonChunk {
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			aData.smooth(15, 3,  6);
			aData.smooth(15, 3,  9);
			
			for (int tX = 11; tX <= 14; tX++) {
				aData.smooth(tX, 0,  6);
				aData.smooth(tX, 0,  7);
				aData.smooth(tX, 0,  8);
				aData.smooth(tX, 0,  9);
			}
			for (int tY = 1; tY <= 6; tY++) {
				aData.smooth(14, tY,  4);
				aData.smooth(13, tY,  4);
				aData.smooth(12, tY,  4);
				aData.smooth(11, tY,  5);
				if (tY >= 3) {
				aData.smooth(11, tY,  6);
				if (tY >= 4) {
				aData.smooth(11, tY,  7);
				aData.smooth(11, tY,  8);
				}
				aData.smooth(11, tY,  9);
				}
				aData.smooth(11, tY, 10);
				aData.smooth(12, tY, 11);
				aData.smooth(13, tY, 11);
				aData.smooth(14, tY, 11);
			}
			for (int tY = 1; tY <= 2; tY++) {
				aData.smooth(14, tY,  5);
				aData.smooth(14, tY,  6);
				aData.smooth(14, tY,  9);
				aData.smooth(14, tY, 10);
				aData.smooth(12, tY,  5);
				aData.smooth(12, tY,  6);
				aData.smooth(12, tY,  9);
				aData.smooth(12, tY, 10);
			}
			aData.smooth (12, 3,  7);
			aData.smooth (12, 3,  8);
			aData.smooth (14, 3,  7);
			aData.smooth (14, 3,  8);
			aData.smooth (13, 4,  7);
			aData.smooth (13, 4,  8);
			aData.smooth (13, 3,  5);
			aData.smooth (13, 3,  6);
			aData.smooth (13, 3,  7);
			aData.smooth (13, 3,  8);
			aData.smooth (13, 3,  9);
			aData.smooth (13, 3, 10);

			aData.lamp   (15, 4,  7, +1);
			aData.lamp   (15, 4,  8, +1);
			aData.colored(13, 1,  7);
			aData.colored(13, 1,  8);
			aData.colored(13, 2,  7);
			aData.colored(13, 2,  8);
			
			aData.set    (11, 2,  9, SIDE_UNKNOWN, 32111, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T));
			aData.set    (15, 2,  6, SIDE_UNKNOWN, 32111, UT.NBT.make(NBT_FACING, SIDE_X_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T));
			aData.set    (13, 1,  5, Blocks.sticky_piston, 3, 2);
			aData.set    (13, 1, 10, Blocks.sticky_piston, 2, 2);
			aData.set    (13, 2,  5, Blocks.sticky_piston, 3, 2);
			aData.set    (13, 2, 10, Blocks.sticky_piston, 2, 2);
			aData.set    (12, 3,  6, Blocks.redstone_wire, 0, 2);
			aData.set    (12, 3,  9, Blocks.redstone_wire, 0, 2);
			aData.set    (14, 3,  6, Blocks.redstone_wire, 0, 2);
			aData.set    (14, 3,  9, Blocks.redstone_wire, 0, 2);
			aData.set    (12, 4,  7, Blocks.redstone_wire, 0, 2);
			aData.set    (12, 4,  8, Blocks.redstone_wire, 0, 2);
			aData.set    (13, 4,  5, Blocks.redstone_wire, 0, 2);
			aData.set    (13, 4, 10, Blocks.redstone_wire, 0, 2);
			aData.set    (14, 4,  7, Blocks.redstone_wire, 0, 2);
			aData.set    (14, 4,  8, Blocks.redstone_wire, 0, 2);
			aData.set    (13, 5,  7, Blocks.redstone_wire, 0, 2);
			aData.set    (13, 5,  8, Blocks.redstone_wire, 0, 2);
			aData.set    (13, 4,  6, Blocks.redstone_torch, 4, 3);
			aData.set    (13, 4,  9, Blocks.redstone_torch, 3, 3);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			aData.smooth( 0, 3,  6);
			aData.smooth( 0, 3,  9);
			for (int tX =  1; tX <=  4; tX++) {
				aData.smooth(tX, 0,  6);
				aData.smooth(tX, 0,  7);
				aData.smooth(tX, 0,  8);
				aData.smooth(tX, 0,  9);
			}
			for (int tY = 1; tY <= 6; tY++) {
				aData.smooth( 1, tY,  4);
				aData.smooth( 2, tY,  4);
				aData.smooth( 3, tY,  4);
				aData.smooth( 4, tY,  5);
				if (tY >= 3) {
				aData.smooth( 4, tY,  6);
				if (tY >= 4) {
				aData.smooth( 4, tY,  7);
				aData.smooth( 4, tY,  8);
				}
				aData.smooth( 4, tY,  9);
				}
				aData.smooth( 4, tY, 10);
				aData.smooth( 1, tY, 11);
				aData.smooth( 2, tY, 11);
				aData.smooth( 3, tY, 11);
			}
			for (int tY = 1; tY <= 2; tY++) {
				aData.smooth( 1, tY,  5);
				aData.smooth( 1, tY,  6);
				aData.smooth( 1, tY,  9);
				aData.smooth( 1, tY, 10);
				aData.smooth( 3, tY,  5);
				aData.smooth( 3, tY,  6);
				aData.smooth( 3, tY,  9);
				aData.smooth( 3, tY, 10);
			}
			aData.smooth ( 3, 3,  7);
			aData.smooth ( 3, 3,  8);
			aData.smooth ( 1, 3,  7);
			aData.smooth ( 1, 3,  8);
			aData.smooth ( 2, 4,  7);
			aData.smooth ( 2, 4,  8);
			aData.smooth ( 2, 3,  5);
			aData.smooth ( 2, 3,  6);
			aData.smooth ( 2, 3,  7);
			aData.smooth ( 2, 3,  8);
			aData.smooth ( 2, 3,  9);
			aData.smooth ( 2, 3, 10);

			aData.lamp   ( 0, 4,  7, +1);
			aData.lamp   ( 0, 4,  8, +1);
			aData.colored( 2, 1,  7);
			aData.colored( 2, 1,  8);
			aData.colored( 2, 2,  7);
			aData.colored( 2, 2,  8);
			
			aData.set    ( 0, 2,  9, SIDE_UNKNOWN, 32111, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T));
			aData.set    ( 4, 2,  6, SIDE_UNKNOWN, 32111, UT.NBT.make(NBT_FACING, SIDE_X_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T));
			aData.set    ( 2, 1,  5, Blocks.sticky_piston, 3, 2);
			aData.set    ( 2, 1, 10, Blocks.sticky_piston, 2, 2);
			aData.set    ( 2, 2,  5, Blocks.sticky_piston, 3, 2);
			aData.set    ( 2, 2, 10, Blocks.sticky_piston, 2, 2);
			aData.set    ( 3, 3,  6, Blocks.redstone_wire, 0, 2);
			aData.set    ( 3, 3,  9, Blocks.redstone_wire, 0, 2);
			aData.set    ( 1, 3,  6, Blocks.redstone_wire, 0, 2);
			aData.set    ( 1, 3,  9, Blocks.redstone_wire, 0, 2);
			aData.set    ( 3, 4,  7, Blocks.redstone_wire, 0, 2);
			aData.set    ( 3, 4,  8, Blocks.redstone_wire, 0, 2);
			aData.set    ( 2, 4,  5, Blocks.redstone_wire, 0, 2);
			aData.set    ( 2, 4, 10, Blocks.redstone_wire, 0, 2);
			aData.set    ( 1, 4,  7, Blocks.redstone_wire, 0, 2);
			aData.set    ( 1, 4,  8, Blocks.redstone_wire, 0, 2);
			aData.set    ( 2, 5,  7, Blocks.redstone_wire, 0, 2);
			aData.set    ( 2, 5,  8, Blocks.redstone_wire, 0, 2);
			aData.set    ( 2, 4,  6, Blocks.redstone_torch, 4, 3);
			aData.set    ( 2, 4,  9, Blocks.redstone_torch, 3, 3);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			aData.smooth( 6, 3, 15);
			aData.smooth( 9, 3, 15);
			for (int tZ = 11; tZ <= 14; tZ++) {
				aData.smooth( 6, 0, tZ);
				aData.smooth( 7, 0, tZ);
				aData.smooth( 8, 0, tZ);
				aData.smooth( 9, 0, tZ);
			}
			for (int tY = 1; tY <= 6; tY++) {
				aData.smooth( 4, tY, 14);
				aData.smooth( 4, tY, 13);
				aData.smooth( 4, tY, 12);
				aData.smooth( 5, tY, 11);
				if (tY >= 3) {
				aData.smooth( 6, tY, 11);
				if (tY >= 4) {
				aData.smooth( 7, tY, 11);
				aData.smooth( 8, tY, 11);
				}
				aData.smooth( 9, tY, 11);
				}
				aData.smooth(10, tY, 11);
				aData.smooth(11, tY, 12);
				aData.smooth(11, tY, 13);
				aData.smooth(11, tY, 14);
			}
			for (int tY = 1; tY <= 2; tY++) {
				aData.smooth( 5, tY, 14);
				aData.smooth( 6, tY, 14);
				aData.smooth( 9, tY, 14);
				aData.smooth(10, tY, 14);
				aData.smooth( 5, tY, 12);
				aData.smooth( 6, tY, 12);
				aData.smooth( 9, tY, 12);
				aData.smooth(10, tY, 12);
			}
			aData.smooth ( 7, 3, 12);
			aData.smooth ( 8, 3, 12);
			aData.smooth ( 7, 3, 14);
			aData.smooth ( 8, 3, 14);
			aData.smooth ( 7, 4, 13);
			aData.smooth ( 8, 4, 13);
			aData.smooth ( 5, 3, 13);
			aData.smooth ( 6, 3, 13);
			aData.smooth ( 7, 3, 13);
			aData.smooth ( 8, 3, 13);
			aData.smooth ( 9, 3, 13);
			aData.smooth (10, 3, 13);
			
			aData.lamp   ( 7, 4, 15, +1);
			aData.lamp   ( 8, 4, 15, +1);
			aData.colored( 7, 1, 13);
			aData.colored( 8, 1, 13);
			aData.colored( 7, 2, 13);
			aData.colored( 8, 2, 13);
			
			aData.set    ( 6, 2, 11, SIDE_UNKNOWN, 32111, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T));
			aData.set    ( 9, 2, 15, SIDE_UNKNOWN, 32111, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T));
			aData.set    ( 5, 1, 13, Blocks.sticky_piston, 5, 2);
			aData.set    (10, 1, 13, Blocks.sticky_piston, 4, 2);
			aData.set    ( 5, 2, 13, Blocks.sticky_piston, 5, 2);
			aData.set    (10, 2, 13, Blocks.sticky_piston, 4, 2);
			aData.set    ( 6, 3, 12, Blocks.redstone_wire, 0, 2);
			aData.set    ( 9, 3, 12, Blocks.redstone_wire, 0, 2);
			aData.set    ( 6, 3, 14, Blocks.redstone_wire, 0, 2);
			aData.set    ( 9, 3, 14, Blocks.redstone_wire, 0, 2);
			aData.set    ( 7, 4, 12, Blocks.redstone_wire, 0, 2);
			aData.set    ( 8, 4, 12, Blocks.redstone_wire, 0, 2);
			aData.set    ( 5, 4, 13, Blocks.redstone_wire, 0, 2);
			aData.set    (10, 4, 13, Blocks.redstone_wire, 0, 2);
			aData.set    ( 7, 4, 14, Blocks.redstone_wire, 0, 2);
			aData.set    ( 8, 4, 14, Blocks.redstone_wire, 0, 2);
			aData.set    ( 7, 5, 13, Blocks.redstone_wire, 0, 2);
			aData.set    ( 8, 5, 13, Blocks.redstone_wire, 0, 2);
			aData.set    ( 6, 4, 13, Blocks.redstone_torch, 2, 3);
			aData.set    ( 9, 4, 13, Blocks.redstone_torch, 1, 3);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			aData.smooth( 6, 3,  0);
			aData.smooth( 9, 3,  0);
			for (int tZ =  1; tZ <=  4; tZ++) {
				aData.smooth( 6, 0, tZ);
				aData.smooth( 7, 0, tZ);
				aData.smooth( 8, 0, tZ);
				aData.smooth( 9, 0, tZ);
			}
			for (int tY = 1; tY <= 6; tY++) {
				aData.smooth( 4, tY,  1);
				aData.smooth( 4, tY,  2);
				aData.smooth( 4, tY,  3);
				aData.smooth( 5, tY,  4);
				if (tY >= 3) {
				aData.smooth( 6, tY,  4);
				if (tY >= 4) {
				aData.smooth( 7, tY,  4);
				aData.smooth( 8, tY,  4);
				}
				aData.smooth( 9, tY,  4);
				}
				aData.smooth(10, tY,  4);
				aData.smooth(11, tY,  1);
				aData.smooth(11, tY,  2);
				aData.smooth(11, tY,  3);
			}
			for (int tY = 1; tY <= 2; tY++) {
				aData.smooth( 5, tY,  1);
				aData.smooth( 6, tY,  1);
				aData.smooth( 9, tY,  1);
				aData.smooth(10, tY,  1);
				aData.smooth( 5, tY,  3);
				aData.smooth( 6, tY,  3);
				aData.smooth( 9, tY,  3);
				aData.smooth(10, tY,  3);
			}
			aData.smooth ( 7, 3,  3);
			aData.smooth ( 8, 3,  3);
			aData.smooth ( 7, 3,  1);
			aData.smooth ( 8, 3,  1);
			aData.smooth ( 7, 4,  2);
			aData.smooth ( 8, 4,  2);
			aData.smooth ( 5, 3,  2);
			aData.smooth ( 6, 3,  2);
			aData.smooth ( 7, 3,  2);
			aData.smooth ( 8, 3,  2);
			aData.smooth ( 9, 3,  2);
			aData.smooth (10, 3,  2);
			
			aData.lamp   ( 7, 4,  0, +1);
			aData.lamp   ( 8, 4,  0, +1);
			aData.colored( 7, 1,  2);
			aData.colored( 8, 1,  2);
			aData.colored( 7, 2,  2);
			aData.colored( 8, 2,  2);
			
			aData.set    ( 6, 2,  0, SIDE_UNKNOWN, 32111, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T));
			aData.set    ( 9, 2,  4, SIDE_UNKNOWN, 32111, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T));
			aData.set    ( 5, 1,  2, Blocks.sticky_piston, 5, 2);
			aData.set    (10, 1,  2, Blocks.sticky_piston, 4, 2);
			aData.set    ( 5, 2,  2, Blocks.sticky_piston, 5, 2);
			aData.set    (10, 2,  2, Blocks.sticky_piston, 4, 2);
			aData.set    ( 6, 3,  3, Blocks.redstone_wire, 0, 2);
			aData.set    ( 9, 3,  3, Blocks.redstone_wire, 0, 2);
			aData.set    ( 6, 3,  1, Blocks.redstone_wire, 0, 2);
			aData.set    ( 9, 3,  1, Blocks.redstone_wire, 0, 2);
			aData.set    ( 7, 4,  3, Blocks.redstone_wire, 0, 2);
			aData.set    ( 8, 4,  3, Blocks.redstone_wire, 0, 2);
			aData.set    ( 5, 4,  2, Blocks.redstone_wire, 0, 2);
			aData.set    (10, 4,  2, Blocks.redstone_wire, 0, 2);
			aData.set    ( 7, 4,  1, Blocks.redstone_wire, 0, 2);
			aData.set    ( 8, 4,  1, Blocks.redstone_wire, 0, 2);
			aData.set    ( 7, 5,  2, Blocks.redstone_wire, 0, 2);
			aData.set    ( 8, 5,  2, Blocks.redstone_wire, 0, 2);
			aData.set    ( 6, 4,  2, Blocks.redstone_torch, 2, 3);
			aData.set    ( 9, 4,  2, Blocks.redstone_torch, 1, 3);
		}
		return T;
	}
}
