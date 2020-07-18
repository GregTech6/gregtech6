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

import gregapi.util.WD;
import net.minecraft.block.Block;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkEntrance extends DungeonChunkPillar {
	@Override
	public boolean generate(DungeonData aData) {
		try {super.generate(aData);} catch(Throwable e) {e.printStackTrace(ERR);} // The Pillar is not important enough to fail the entire Entrance.
		
		for (int tX = 0; tX < 16; tX++) for (int tZ = 0; tZ < 16; tZ++) for (int tY = 0; tY <= 7; tY++) {
			if (tX == 0 || tX == 15 || tZ == 0 || tZ == 15 || tY == 0 || tY == 7) {
				if ((tX == 2 || tX == 6 || tX == 9 || tX == 13) && (tZ == 2 || tZ == 6 || tZ == 9 || tZ == 13)) {
					if (tY == 0) {
						aData.chiseled(tX, tY, tZ);
					} else if (tY == 7) {
						if (!((tX == 6 || tX == 9) && (tZ == 6 || tZ == 9))) {
							aData.lamp(tX, tY, tZ, +1);
						} else {
							aData.bricks(tX, tY, tZ);
							aData.bricks(tX, tY+1, tZ);
						}
					} else {
						aData.bricks(tX, tY, tZ);
					}
				} else {
					if (tY == 0) {
						aData.tiles(tX, tY, tZ);
					} else if (tY == 7) {
						aData.smalltiles(tX, tY, tZ);
					} else {
						aData.bricks(tX, tY, tZ);
					}
				}
			} else {
				aData.air(tX, tY, tZ);
			}
		}
		
		int tHeight = 10+aData.mY;
		for (int eY = aData.mWorld.getHeight()-32, tAirAmount = 0; tHeight < eY && tAirAmount < 100; tHeight++) {
			tAirAmount = 0;
			for (int tX = 3+aData.mX, eX = tX + 10; tX < eX; tX++) for (int tZ = 3+aData.mZ, eZ = tZ + 10; tZ < eZ; tZ++) {
				Block tBlock = WD.block(aData.mWorld, tX, tHeight, tZ);
				if (WD.easyRep(aData.mWorld, tX, tHeight, tZ, tBlock) || tBlock.isWood(aData.mWorld, tX, tHeight, tZ)) tAirAmount++;
			}
		}
		tHeight -= aData.mY;
		
		if ((tHeight - 1) % 5 != 0) tHeight += 5 - ((tHeight - 1) % 5);
		
		for (int tY = 7; tY <= tHeight; tY++) for (int tX = 3; tX <= 12; tX++) for (int tZ = 3; tZ <= 12; tZ++) {
			if (tX >= 6 && tX <= 9 && tZ >= 6 && tZ <= 9 && (tX == 6 || tZ == 6 || tX == 9 || tZ == 9)) {
				if (tY % 4 == 0) {
					aData.colored(tX, tY, tZ);
				} else {
					aData.smooth(tX, tY, tZ);
				}
			} else if (tX == 3 || tZ == 3 || tX == 12 || tZ == 12) {
				if (tY == tHeight-1) {
					aData.tiles(tX, tY, tZ);
				} else {
					aData.bricks(tX, tY, tZ);
				}
			} else {
				aData.air(tX, tY, tZ);
			}
		}
		
		for (int tY = 1; tY <= 6; tY++) for (int tX = 6; tX <= 9; tX++) for (int tZ = 6; tZ <= 9; tZ++) {
			if (tX == 6 || tZ == 6 || tX == 9 || tZ == 9) {
				if (tY % 4 == 0) {
					aData.colored(tX, tY, tZ);
				} else {
					aData.smooth(tX, tY, tZ);
				}
			}
		}
		
		aData.air(7, 1, 6);
		aData.air(7, 1, 9);
		aData.air(7, 2, 6);
		aData.air(7, 2, 9);
		aData.air(8, 1, 6);
		aData.air(8, 1, 9);
		aData.air(8, 2, 6);
		aData.air(8, 2, 9);
		
		int tOffsetY = -5;
		
		while (tOffsetY + 10 < tHeight) {
			tOffsetY += 5;
			
			aData.smooth(10, tOffsetY+ 1,  6, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth(11, tOffsetY+ 1,  6, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth(10, tOffsetY+ 1,  7, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth(11, tOffsetY+ 1,  7, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth(10, tOffsetY+ 2,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth(11, tOffsetY+ 2,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth(10, tOffsetY+ 2,  9, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth(11, tOffsetY+ 2,  9, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			
			aData.smooth(10, tOffsetY+ 3, 10, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth(11, tOffsetY+ 3, 10, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth(10, tOffsetY+ 3, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth(11, tOffsetY+ 3, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			
			aData.smooth( 9, tOffsetY+ 3, 10, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 9, tOffsetY+ 3, 11, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 8, tOffsetY+ 4, 10, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth( 8, tOffsetY+ 4, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth( 7, tOffsetY+ 4, 10, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 7, tOffsetY+ 4, 11, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 6, tOffsetY+ 5, 10, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth( 6, tOffsetY+ 5, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			
			aData.smooth( 5, tOffsetY+ 5, 10, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 5, tOffsetY+ 5, 11, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 4, tOffsetY+ 5, 10, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 4, tOffsetY+ 5, 11, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			
			
			
			aData.smooth( 4, tOffsetY+ 1,  9, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth( 5, tOffsetY+ 1,  9, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth( 4, tOffsetY+ 1,  8, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 5, tOffsetY+ 1,  8, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 4, tOffsetY+ 2,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth( 5, tOffsetY+ 2,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth( 4, tOffsetY+ 2,  6, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 5, tOffsetY+ 2,  6, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			
			aData.smooth( 4, tOffsetY+ 3,  5, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth( 5, tOffsetY+ 3,  5, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth( 4, tOffsetY+ 3,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth( 5, tOffsetY+ 3,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			
			aData.smooth( 6, tOffsetY+ 3,  4, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 6, tOffsetY+ 3,  5, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 7, tOffsetY+ 4,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth( 7, tOffsetY+ 4,  5, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth( 8, tOffsetY+ 4,  4, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 8, tOffsetY+ 4,  5, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth( 9, tOffsetY+ 5,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.smooth( 9, tOffsetY+ 5,  5, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			
			aData.smooth(10, tOffsetY+ 5,  4, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth(10, tOffsetY+ 5,  5, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth(11, tOffsetY+ 5,  4, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.smooth(11, tOffsetY+ 5,  5, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		}
		
		aData.tiles( 4, tOffsetY+ 5,  9, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles( 5, tOffsetY+ 5,  9, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles( 4, tOffsetY+ 5,  8, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles( 5, tOffsetY+ 5,  8, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles( 4, tOffsetY+ 5,  7, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles( 5, tOffsetY+ 5,  7, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles( 4, tOffsetY+ 5,  6, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles( 5, tOffsetY+ 5,  6, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		
		aData.tiles(10, tOffsetY+ 5,  9, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles(11, tOffsetY+ 5,  9, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles(10, tOffsetY+ 5,  8, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles(11, tOffsetY+ 5,  8, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles(10, tOffsetY+ 5,  7, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles(11, tOffsetY+ 5,  7, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles(10, tOffsetY+ 5,  6, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.tiles(11, tOffsetY+ 5,  6, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		
		for (int tY = Math.max(8, tHeight-2); tY <= tHeight+2; tY++) for (int tX = 2; tX <= 13; tX++) for (int tZ = 2; tZ <= 13; tZ++) {
			if (tX == 2 || tZ == 2 || tX == 13 || tZ == 13) {
				if (tY == tHeight+1) {
					aData.colored(tX, tY, tZ);
				} else {
					aData.bricks(tX, tY, tZ);
				}
			} else {
				if (tY >= tHeight) aData.air(tX, tY, tZ);
			}
		}
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			aData.colored (15, 0,  5);
			aData.colored (15, 0,  6);
			aData.colored (15, 0,  7);
			aData.colored (15, 0,  8);
			aData.colored (15, 0,  9);
			aData.colored (15, 0, 10);
			aData.colored (15, 1,  5);
			aData.air     (15, 1,  6);
			aData.air     (15, 1,  7);
			aData.air     (15, 1,  8);
			aData.air     (15, 1,  9);
			aData.colored (15, 1, 10);
			aData.colored (15, 2,  5);
			aData.air     (15, 2,  6);
			aData.air     (15, 2,  7);
			aData.air     (15, 2,  8);
			aData.air     (15, 2,  9);
			aData.colored (15, 2, 10);
			aData.colored (15, 3,  5);
			aData.air     (15, 3,  6);
			aData.air     (15, 3,  7);
			aData.air     (15, 3,  8);
			aData.air     (15, 3,  9);
			aData.colored (15, 3, 10);
			aData.colored (15, 4,  5);
			aData.colored (15, 4,  6);
			aData.colored (15, 4,  7);
			aData.colored (15, 4,  8);
			aData.colored (15, 4,  9);
			aData.colored (15, 4, 10);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			aData.colored ( 0, 0,  5);
			aData.colored ( 0, 0,  6);
			aData.colored ( 0, 0,  7);
			aData.colored ( 0, 0,  8);
			aData.colored ( 0, 0,  9);
			aData.colored ( 0, 0, 10);
			aData.colored ( 0, 1,  5);
			aData.air     ( 0, 1,  6);
			aData.air     ( 0, 1,  7);
			aData.air     ( 0, 1,  8);
			aData.air     ( 0, 1,  9);
			aData.colored ( 0, 1, 10);
			aData.colored ( 0, 2,  5);
			aData.air     ( 0, 2,  6);
			aData.air     ( 0, 2,  7);
			aData.air     ( 0, 2,  8);
			aData.air     ( 0, 2,  9);
			aData.colored ( 0, 2, 10);
			aData.colored ( 0, 3,  5);
			aData.air     ( 0, 3,  6);
			aData.air     ( 0, 3,  7);
			aData.air     ( 0, 3,  8);
			aData.air     ( 0, 3,  9);
			aData.colored ( 0, 3, 10);
			aData.colored ( 0, 4,  5);
			aData.colored ( 0, 4,  6);
			aData.colored ( 0, 4,  7);
			aData.colored ( 0, 4,  8);
			aData.colored ( 0, 4,  9);
			aData.colored ( 0, 4, 10);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			aData.colored ( 5, 0, 15);
			aData.colored ( 6, 0, 15);
			aData.colored ( 7, 0, 15);
			aData.colored ( 8, 0, 15);
			aData.colored ( 9, 0, 15);
			aData.colored (10, 0, 15);
			aData.colored ( 5, 1, 15);
			aData.air     ( 6, 1, 15);
			aData.air     ( 7, 1, 15);
			aData.air     ( 8, 1, 15);
			aData.air     ( 9, 1, 15);
			aData.colored (10, 1, 15);
			aData.colored ( 5, 2, 15);
			aData.air     ( 6, 2, 15);
			aData.air     ( 7, 2, 15);
			aData.air     ( 8, 2, 15);
			aData.air     ( 9, 2, 15);
			aData.colored (10, 2, 15);
			aData.colored ( 5, 3, 15);
			aData.air     ( 6, 3, 15);
			aData.air     ( 7, 3, 15);
			aData.air     ( 8, 3, 15);
			aData.air     ( 9, 3, 15);
			aData.colored (10, 3, 15);
			aData.colored ( 5, 4, 15);
			aData.colored ( 6, 4, 15);
			aData.colored ( 7, 4, 15);
			aData.colored ( 8, 4, 15);
			aData.colored ( 9, 4, 15);
			aData.colored (10, 4, 15);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			aData.colored ( 5, 0,  0);
			aData.colored ( 6, 0,  0);
			aData.colored ( 7, 0,  0);
			aData.colored ( 8, 0,  0);
			aData.colored ( 9, 0,  0);
			aData.colored (10, 0,  0);
			aData.colored ( 5, 1,  0);
			aData.air     ( 6, 1,  0);
			aData.air     ( 7, 1,  0);
			aData.air     ( 8, 1,  0);
			aData.air     ( 9, 1,  0);
			aData.colored (10, 1,  0);
			aData.colored ( 5, 2,  0);
			aData.air     ( 6, 2,  0);
			aData.air     ( 7, 2,  0);
			aData.air     ( 8, 2,  0);
			aData.air     ( 9, 2,  0);
			aData.colored (10, 2,  0);
			aData.colored ( 5, 3,  0);
			aData.air     ( 6, 3,  0);
			aData.air     ( 7, 3,  0);
			aData.air     ( 8, 3,  0);
			aData.air     ( 9, 3,  0);
			aData.colored (10, 3,  0);
			aData.colored ( 5, 4,  0);
			aData.colored ( 6, 4,  0);
			aData.colored ( 7, 4,  0);
			aData.colored ( 8, 4,  0);
			aData.colored ( 9, 4,  0);
			aData.colored (10, 4,  0);
		}
		
		return T;
	}
}
