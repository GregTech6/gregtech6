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

import gregapi.util.WD;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkCorridor extends DungeonChunkPillar {
	@Override
	public boolean generate(DungeonData aData) {
		if ((aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0 || aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) && (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0 || aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0)) {
			try {super.generate(aData);} catch(Throwable e) {e.printStackTrace(ERR);} // The Pillar is not important enough to fail the entire Corridor.
		}
		
		for (int tX =  5; tX <= 10; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
			if (tY == 0) {
				aData.tiles(tX, tY, tZ);
			} else if (tX == 5 || tX == 10 || tZ == 5 || tZ == 10) {
				aData.bricks(tX, tY, tZ);
			} else {
				if (tY == 4) {
					if (WD.liquid(aData.mWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aData.mWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
						aData.glassglow(tX, tY, tZ);
					} else {
						aData.bricks(tX, tY, tZ);
					}
				} else {
					aData.air(tX, tY, tZ);
				}
			}
		}

		if (WD.liquid(aData.mWorld, aData.mX+ 7, aData.mY+4, aData.mZ+ 7) || WD.liquid(aData.mWorld, aData.mX+ 7, aData.mY+4, aData.mZ+ 8)
		 || WD.liquid(aData.mWorld, aData.mX+ 8, aData.mY+4, aData.mZ+ 7) || WD.liquid(aData.mWorld, aData.mX+ 8, aData.mY+4, aData.mZ+ 8)
		 || aData.mWorld.canBlockSeeTheSky(aData.mX+ 7, aData.mY+4, aData.mZ+ 7) || aData.mWorld.canBlockSeeTheSky(aData.mX+ 7, aData.mY+4, aData.mZ+ 8)
		 || aData.mWorld.canBlockSeeTheSky(aData.mX+ 8, aData.mY+4, aData.mZ+ 7) || aData.mWorld.canBlockSeeTheSky(aData.mX+ 8, aData.mY+4, aData.mZ+ 8)) {
			aData.glassglow(7, 4, 7);
			aData.glassglow(7, 4, 8);
			aData.glassglow(8, 4, 7);
			aData.glassglow(8, 4, 8);
		} else {
			aData.redstoned(7, 4, 7);
			aData.lamp     (7, 4, 8, 0);
			aData.lamp     (8, 4, 7, 0);
			aData.redstoned(8, 4, 8);
		}
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			for (int tX = 10; tX <= 15; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.tiles(tX, tY, tZ);
				} else if (tZ == 5 || tZ == 10) {
					aData.bricks(tX, tY, tZ);
				} else {
					if (tY == 4) {
						if (WD.liquid(aData.mWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aData.mWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
							aData.glassglow(tX, tY, tZ);
						} else {
							aData.bricks(tX, tY, tZ);
						}
					} else {
						aData.air(tX, tY, tZ);
					}
				}
			}
			aData.redstoned(13, 4, 6);
			aData.lamp     (13, 4, 7, 0);
			aData.lamp     (13, 4, 8, 0);
			aData.redstoned(13, 4, 9);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			for (int tX =  0; tX <=  5; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.tiles(tX, tY, tZ);
				} else if (tZ == 5 || tZ == 10) {
					aData.bricks(tX, tY, tZ);
				} else {
					if (tY == 4) {
						if (WD.liquid(aData.mWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aData.mWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
							aData.glassglow(tX, tY, tZ);
						} else {
							aData.bricks(tX, tY, tZ);
						}
					} else {
						aData.air(tX, tY, tZ);
					}
				}
			}
			aData.redstoned(2, 4, 6);
			aData.lamp     (2, 4, 7, 0);
			aData.lamp     (2, 4, 8, 0);
			aData.redstoned(2, 4, 9);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			for (int tX =  5; tX <= 10; tX++) for (int tZ = 10; tZ <= 15; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.tiles(tX, tY, tZ);
				} else if (tX == 5 || tX == 10) {
					aData.bricks(tX, tY, tZ);
				} else {
					if (tY == 4) {
						if (WD.liquid(aData.mWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aData.mWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
							aData.glassglow(tX, tY, tZ);
						} else {
							aData.bricks(tX, tY, tZ);
						}
					} else {
						aData.air(tX, tY, tZ);
					}
				}
			}
			aData.redstoned(6, 4, 13);
			aData.lamp     (7, 4, 13, 0);
			aData.lamp     (8, 4, 13, 0);
			aData.redstoned(9, 4, 13);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			for (int tX =  5; tX <= 10; tX++) for (int tZ =  0; tZ <=  5; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.tiles(tX, tY, tZ);
				} else if (tX == 5 || tX == 10) {
					aData.bricks(tX, tY, tZ);
				} else {
					if (tY == 4) {
						if (WD.liquid(aData.mWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aData.mWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
							aData.glassglow(tX, tY, tZ);
						} else {
							aData.bricks(tX, tY, tZ);
						}
					} else {
						aData.air(tX, tY, tZ);
					}
				}
			}
			aData.redstoned(6, 4, 2);
			aData.lamp     (7, 4, 2, 0);
			aData.lamp     (8, 4, 2, 0);
			aData.redstoned(9, 4, 2);
		}
		return T;
	}
}
