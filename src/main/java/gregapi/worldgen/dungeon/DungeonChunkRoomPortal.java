/**
 * Copyright (c) 2019 Gregorius Techneticies
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
public class DungeonChunkRoomPortal extends DungeonChunkRoomVault {
	@Override
	public boolean generate(DungeonData aData) {
		if (!super.generate(aData)) return F;
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			for (int i = 0; i < 6; i++) for (int j = 0; j < 6; j++) if (i == 0 || j == 0 || i == 5 || j == 5) {
				if ((i == 0 || i == 5) && (j == 0 || j == 5)) {
					aData.lamp( 1+i, 1,  5+j, -1);
				} else {
					aData.colored( 1+i, 1,  5+j);
				}
			} else {
				aData.bricks( 1+i, 1,  5+j);
			}
			
			aData.bricks( 1, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 2, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 3, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 5, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 6, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 7, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 7, 1,  5, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 7, 1,  6, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 7, 1,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 7, 1,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 7, 1,  9, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 7, 1, 10, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 7, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 6, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 5, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 3, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 2, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 1, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			for (int i = 0; i < 6; i++) for (int j = 0; j < 6; j++) if (i == 0 || j == 0 || i == 5 || j == 5) {
				if ((i == 0 || i == 5) && (j == 0 || j == 5)) {
					aData.lamp( 9+i, 1,  5+j, -1);
				} else {
					aData.colored( 9+i, 1,  5+j);
				}
			} else {
				aData.bricks( 9+i, 1,  5+j);
			}
			
			aData.bricks(14, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(13, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(12, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(10, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 9, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 8, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 8, 1,  5, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 8, 1,  6, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 8, 1,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 8, 1,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 8, 1,  9, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 8, 1, 10, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 8, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 9, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(10, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(12, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(13, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(14, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			for (int i = 0; i < 6; i++) for (int j = 0; j < 6; j++) if (i == 0 || j == 0 || i == 5 || j == 5) {
				if ((i == 0 || i == 5) && (j == 0 || j == 5)) {
					aData.lamp( 5+i, 1,  1+j, -1);
				} else {
					aData.colored( 5+i, 1,  1+j);
				}
			} else {
				aData.bricks( 5+i, 1,  1+j);
			}
			
			aData.bricks( 4, 1,  1, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1,  2, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1,  3, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1,  5, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1,  6, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 5, 1,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 6, 1,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 7, 1,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 8, 1,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 9, 1,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(10, 1,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1,  7, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1,  6, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1,  5, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1,  4, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1,  3, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1,  2, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1,  1, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			for (int i = 0; i < 6; i++) for (int j = 0; j < 6; j++) if (i == 0 || j == 0 || i == 5 || j == 5) {
				if ((i == 0 || i == 5) && (j == 0 || j == 5)) {
					aData.lamp( 5+i, 1,  9+j, -1);
				} else {
					aData.colored( 5+i, 1,  9+j);
				}
			} else {
				aData.bricks( 5+i, 1,  9+j);
			}
			
			aData.bricks( 4, 1, 14, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1, 13, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1, 12, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1, 10, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1,  9, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 4, 1,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 5, 1,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 6, 1,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 7, 1,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 8, 1,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks( 9, 1,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(10, 1,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1,  8, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1,  9, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1, 10, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1, 11, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1, 12, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1, 13, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
			aData.bricks(11, 1, 14, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0]);
		}
		return T;
	}
}
