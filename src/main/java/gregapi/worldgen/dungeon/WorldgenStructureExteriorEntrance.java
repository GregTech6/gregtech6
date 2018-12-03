/**
 * Copyright (c) 2018 Gregorius Techneticies
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
public class WorldgenStructureExteriorEntrance extends WorldgenDungeonGT {
	public static boolean generate(DungeonChunkData aData) {
		WorldgenStructureExteriorPillar.generate(aData.mWorld, aData.mRandom, aData.mX, aData.mZ, aData);
		
		for (int tX = 0; tX < 16; tX++) for (int tZ = 0; tZ < 16; tZ++) for (int tY = 0; tY <= 7; tY++) {
			if (tX == 0 || tX == 15 || tZ == 0 || tZ == 15 || tY == 0 || tY == 7) {
				if ((tX == 2 || tX == 6 || tX == 9 || tX == 13) && (tZ == 2 || tZ == 6 || tZ == 9 || tZ == 13)) {
					if (tY == 0) {
						aData.setChiseledStone(tX, tY, tZ);
					} else if (tY == 7) {
						if (!((tX == 6 || tX == 9) && (tZ == 6 || tZ == 9))) {
							aData.setLampBlock(tX, tY, tZ, +1);
						} else {
							aData.setRandomBricks(tX, tY, tZ);
							aData.setRandomBricks(tX, tY+1, tZ);
						}
					} else {
						aData.setRandomBricks(tX, tY, tZ);
					}
				} else {
					if (tY == 0) {
						aData.setStoneTiles(tX, tY, tZ);
					} else if (tY == 7) {
						aData.setSmallTiles(tX, tY, tZ);
					} else {
						aData.setRandomBricks(tX, tY, tZ);
					}
				}
			} else {
				aData.setAirBlock(tX, tY, tZ);
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
				if ((tY - aData.mY) % 4 == 0) {
					aData.setColored(tX, tY, tZ);
				} else {
					aData.setSmoothBlock(tX, tY, tZ);
				}
			} else if (tX == 3 || tZ == 3 || tX == 12 || tZ == 12) {
				if (tY == tHeight-1) {
					aData.setStoneTiles(tX, tY, tZ);
				} else {
					aData.setRandomBricks(tX, tY, tZ);
				}
			} else {
				aData.setAirBlock(tX, tY, tZ);
			}
		}
		
		for (int tY = 1; tY <= 6; tY++) for (int tX = 6; tX <= 9; tX++) for (int tZ = 6; tZ <= 9; tZ++) {
			if (tX == 6 || tZ == 6 || tX == 9 || tZ == 9) {
				if ((tY - aData.mY) % 4 == 0) {
					aData.setColored(tX, tY, tZ);
				} else {
					aData.setSmoothBlock(tX, tY, tZ);
				}
			}
		}
		
		aData.setAirBlock(7, 1, 6);
		aData.setAirBlock(7, 1, 9);
		aData.setAirBlock(7, 2, 6);
		aData.setAirBlock(7, 2, 9);
		aData.setAirBlock(8, 1, 6);
		aData.setAirBlock(8, 1, 9);
		aData.setAirBlock(8, 2, 6);
		aData.setAirBlock(8, 2, 9);
		
		int tOffsetY = -5;
		
		while (tOffsetY + 10 < tHeight) {
			tOffsetY += 5;
			
			aData.setSmoothBlock(10, tOffsetY+ 1,  6, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock(11, tOffsetY+ 1,  6, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock(10, tOffsetY+ 1,  7, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock(11, tOffsetY+ 1,  7, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock(10, tOffsetY+ 2,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock(11, tOffsetY+ 2,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock(10, tOffsetY+ 2,  9, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock(11, tOffsetY+ 2,  9, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			
			aData.setSmoothBlock(10, tOffsetY+ 3, 10, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock(11, tOffsetY+ 3, 10, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock(10, tOffsetY+ 3, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock(11, tOffsetY+ 3, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			
			aData.setSmoothBlock( 9, tOffsetY+ 3, 10, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 9, tOffsetY+ 3, 11, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 8, tOffsetY+ 4, 10, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock( 8, tOffsetY+ 4, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock( 7, tOffsetY+ 4, 10, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 7, tOffsetY+ 4, 11, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 6, tOffsetY+ 5, 10, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock( 6, tOffsetY+ 5, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			
			aData.setSmoothBlock( 5, tOffsetY+ 5, 10, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 5, tOffsetY+ 5, 11, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 4, tOffsetY+ 5, 10, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 4, tOffsetY+ 5, 11, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			
			
			
			aData.setSmoothBlock( 4, tOffsetY+ 1,  9, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock( 5, tOffsetY+ 1,  9, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock( 4, tOffsetY+ 1,  8, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 5, tOffsetY+ 1,  8, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 4, tOffsetY+ 2,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock( 5, tOffsetY+ 2,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock( 4, tOffsetY+ 2,  6, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 5, tOffsetY+ 2,  6, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			
			aData.setSmoothBlock( 4, tOffsetY+ 3,  5, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock( 5, tOffsetY+ 3,  5, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock( 4, tOffsetY+ 3,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock( 5, tOffsetY+ 3,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			
			aData.setSmoothBlock( 6, tOffsetY+ 3,  4, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 6, tOffsetY+ 3,  5, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 7, tOffsetY+ 4,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock( 7, tOffsetY+ 4,  5, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock( 8, tOffsetY+ 4,  4, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 8, tOffsetY+ 4,  5, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock( 9, tOffsetY+ 5,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.setSmoothBlock( 9, tOffsetY+ 5,  5, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			
			aData.setSmoothBlock(10, tOffsetY+ 5,  4, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock(10, tOffsetY+ 5,  5, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock(11, tOffsetY+ 5,  4, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
			aData.setSmoothBlock(11, tOffsetY+ 5,  5, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		}
		
		aData.setStoneTiles( 4, tOffsetY+ 5,  9, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles( 5, tOffsetY+ 5,  9, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles( 4, tOffsetY+ 5,  8, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles( 5, tOffsetY+ 5,  8, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles( 4, tOffsetY+ 5,  7, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles( 5, tOffsetY+ 5,  7, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles( 4, tOffsetY+ 5,  6, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles( 5, tOffsetY+ 5,  6, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		
		aData.setStoneTiles(10, tOffsetY+ 5,  9, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles(11, tOffsetY+ 5,  9, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles(10, tOffsetY+ 5,  8, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles(11, tOffsetY+ 5,  8, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles(10, tOffsetY+ 5,  7, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles(11, tOffsetY+ 5,  7, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles(10, tOffsetY+ 5,  6, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		aData.setStoneTiles(11, tOffsetY+ 5,  6, aData.mPrimary.mSlabs[1], aData.mSecondary.mSlabs[1]);
		
		for (int tY = Math.max(8, tHeight-2); tY <= tHeight+2; tY++) for (int tX = 2; tX <= 13; tX++) for (int tZ = 2; tZ <= 13; tZ++) {
			if (tX == 2 || tZ == 2 || tX == 13 || tZ == 13) {
				if (tY == tHeight+1) {
					aData.setColored(tX, tY, tZ);
				} else {
					aData.setRandomBricks(tX, tY, tZ);
				}
			} else {
				if (tY >= tHeight) aData.setAirBlock(tX, tY, tZ);
			}
		}
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			aData.setColored      (15, 0,  5);
			aData.setColored      (15, 0,  6);
			aData.setColored      (15, 0,  7);
			aData.setColored      (15, 0,  8);
			aData.setColored      (15, 0,  9);
			aData.setColored      (15, 0, 10);
			aData.setColored      (15, 1,  5);
			aData.setAirBlock     (15, 1,  6);
			aData.setAirBlock     (15, 1,  7);
			aData.setAirBlock     (15, 1,  8);
			aData.setAirBlock     (15, 1,  9);
			aData.setColored      (15, 1, 10);
			aData.setColored      (15, 2,  5);
			aData.setAirBlock     (15, 2,  6);
			aData.setAirBlock     (15, 2,  7);
			aData.setAirBlock     (15, 2,  8);
			aData.setAirBlock     (15, 2,  9);
			aData.setColored      (15, 2, 10);
			aData.setColored      (15, 3,  5);
			aData.setAirBlock     (15, 3,  6);
			aData.setAirBlock     (15, 3,  7);
			aData.setAirBlock     (15, 3,  8);
			aData.setAirBlock     (15, 3,  9);
			aData.setColored      (15, 3, 10);
			aData.setColored      (15, 4,  5);
			aData.setColored      (15, 4,  6);
			aData.setColored      (15, 4,  7);
			aData.setColored      (15, 4,  8);
			aData.setColored      (15, 4,  9);
			aData.setColored      (15, 4, 10);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			aData.setColored      ( 0, 0,  5);
			aData.setColored      ( 0, 0,  6);
			aData.setColored      ( 0, 0,  7);
			aData.setColored      ( 0, 0,  8);
			aData.setColored      ( 0, 0,  9);
			aData.setColored      ( 0, 0, 10);
			aData.setColored      ( 0, 1,  5);
			aData.setAirBlock     ( 0, 1,  6);
			aData.setAirBlock     ( 0, 1,  7);
			aData.setAirBlock     ( 0, 1,  8);
			aData.setAirBlock     ( 0, 1,  9);
			aData.setColored      ( 0, 1, 10);
			aData.setColored      ( 0, 2,  5);
			aData.setAirBlock     ( 0, 2,  6);
			aData.setAirBlock     ( 0, 2,  7);
			aData.setAirBlock     ( 0, 2,  8);
			aData.setAirBlock     ( 0, 2,  9);
			aData.setColored      ( 0, 2, 10);
			aData.setColored      ( 0, 3,  5);
			aData.setAirBlock     ( 0, 3,  6);
			aData.setAirBlock     ( 0, 3,  7);
			aData.setAirBlock     ( 0, 3,  8);
			aData.setAirBlock     ( 0, 3,  9);
			aData.setColored      ( 0, 3, 10);
			aData.setColored      ( 0, 4,  5);
			aData.setColored      ( 0, 4,  6);
			aData.setColored      ( 0, 4,  7);
			aData.setColored      ( 0, 4,  8);
			aData.setColored      ( 0, 4,  9);
			aData.setColored      ( 0, 4, 10);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			aData.setColored      ( 5, 0, 15);
			aData.setColored      ( 6, 0, 15);
			aData.setColored      ( 7, 0, 15);
			aData.setColored      ( 8, 0, 15);
			aData.setColored      ( 9, 0, 15);
			aData.setColored      (10, 0, 15);
			aData.setColored      ( 5, 1, 15);
			aData.setAirBlock     ( 6, 1, 15);
			aData.setAirBlock     ( 7, 1, 15);
			aData.setAirBlock     ( 8, 1, 15);
			aData.setAirBlock     ( 9, 1, 15);
			aData.setColored      (10, 1, 15);
			aData.setColored      ( 5, 2, 15);
			aData.setAirBlock     ( 6, 2, 15);
			aData.setAirBlock     ( 7, 2, 15);
			aData.setAirBlock     ( 8, 2, 15);
			aData.setAirBlock     ( 9, 2, 15);
			aData.setColored      (10, 2, 15);
			aData.setColored      ( 5, 3, 15);
			aData.setAirBlock     ( 6, 3, 15);
			aData.setAirBlock     ( 7, 3, 15);
			aData.setAirBlock     ( 8, 3, 15);
			aData.setAirBlock     ( 9, 3, 15);
			aData.setColored      (10, 3, 15);
			aData.setColored      ( 5, 4, 15);
			aData.setColored      ( 6, 4, 15);
			aData.setColored      ( 7, 4, 15);
			aData.setColored      ( 8, 4, 15);
			aData.setColored      ( 9, 4, 15);
			aData.setColored      (10, 4, 15);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			aData.setColored      ( 5, 0,  0);
			aData.setColored      ( 6, 0,  0);
			aData.setColored      ( 7, 0,  0);
			aData.setColored      ( 8, 0,  0);
			aData.setColored      ( 9, 0,  0);
			aData.setColored      (10, 0,  0);
			aData.setColored      ( 5, 1,  0);
			aData.setAirBlock     ( 6, 1,  0);
			aData.setAirBlock     ( 7, 1,  0);
			aData.setAirBlock     ( 8, 1,  0);
			aData.setAirBlock     ( 9, 1,  0);
			aData.setColored      (10, 1,  0);
			aData.setColored      ( 5, 2,  0);
			aData.setAirBlock     ( 6, 2,  0);
			aData.setAirBlock     ( 7, 2,  0);
			aData.setAirBlock     ( 8, 2,  0);
			aData.setAirBlock     ( 9, 2,  0);
			aData.setColored      (10, 2,  0);
			aData.setColored      ( 5, 3,  0);
			aData.setAirBlock     ( 6, 3,  0);
			aData.setAirBlock     ( 7, 3,  0);
			aData.setAirBlock     ( 8, 3,  0);
			aData.setAirBlock     ( 9, 3,  0);
			aData.setColored      (10, 3,  0);
			aData.setColored      ( 5, 4,  0);
			aData.setColored      ( 6, 4,  0);
			aData.setColored      ( 7, 4,  0);
			aData.setColored      ( 8, 4,  0);
			aData.setColored      ( 9, 4,  0);
			aData.setColored      (10, 4,  0);
		}
		
		return T;
	}
}
