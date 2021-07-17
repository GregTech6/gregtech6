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

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkCorridor4 extends DungeonChunkPillar {
	@Override
	public boolean generate(DungeonData aData) {
		if ((aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0 || aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) && (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0 || aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0)) {
			try {super.generate(aData);} catch(Throwable e) {e.printStackTrace(ERR);} // The Pillar is not important enough to fail the entire Corridor.
		}
		
		for (int tX =  2; tX <= 13; tX++) for (int tZ =  2; tZ <= 13; tZ++) for (int tY = 0; tY <= 6; tY++) {
			if (tX == 2 || tX == 13 || tZ == 2 || tZ == 13 || tY == 0 || tY == 6) {
				if ((tX == 4 || tX == 7 || tX == 8 || tX == 11) && (tZ == 4 || tZ == 7 || tZ == 8 || tZ == 11)) {
					if (tY == 0) {
						aData.chiseled(tX, tY, tZ);
					} else if (tY == 6) {
						aData.lamp(tX, tY, tZ, +1);
					} else {
						aData.bricks(tX, tY, tZ);
					}
				} else {
					if (tY == 0) {
						aData.tiles(tX, tY, tZ);
					} else if (tY == 6) {
						aData.smalltiles(tX, tY, tZ);
					} else {
						aData.bricks(tX, tY, tZ);
					}
				}
			} else {
				aData.air(tX, tY, tZ);
			}
		}
		
		for (int tX = 13; tX <= 15; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
			if (tY == 0) {
				aData.tiles(tX, tY, tZ);
			} else if (tY == 4) {
				aData.smalltiles(tX, tY, tZ);
			} else if (tZ == 5 || tZ == 10) {
				aData.bricks(tX, tY, tZ);
			} else {
				aData.air(tX, tY, tZ);
			}
		}
		for (int tX =  0; tX <=  2; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
			if (tY == 0) {
				aData.tiles(tX, tY, tZ);
			} else if (tY == 4) {
				aData.smalltiles(tX, tY, tZ);
			} else if (tZ == 5 || tZ == 10) {
				aData.bricks(tX, tY, tZ);
			} else {
				aData.air(tX, tY, tZ);
			}
		}
		for (int tX =  5; tX <= 10; tX++) for (int tZ = 13; tZ <= 15; tZ++) for (int tY = 0; tY <= 4; tY++) {
			if (tY == 0) {
				aData.tiles(tX, tY, tZ);
			} else if (tY == 4) {
				aData.smalltiles(tX, tY, tZ);
			} else if (tX == 5 || tX == 10) {
				aData.bricks(tX, tY, tZ);
			} else {
				aData.air(tX, tY, tZ);
			}
		}
		for (int tX =  5; tX <= 10; tX++) for (int tZ =  0; tZ <=  2; tZ++) for (int tY = 0; tY <= 4; tY++) {
			if (tY == 0) {
				aData.tiles(tX, tY, tZ);
			} else if (tY == 4) {
				aData.smalltiles(tX, tY, tZ);
			} else if (tX == 5 || tX == 10) {
				aData.bricks(tX, tY, tZ);
			} else {
				aData.air(tX, tY, tZ);
			}
		}
		return T;
	}
}
