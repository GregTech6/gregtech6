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

package gregtech.worldgen.structure;

import static gregapi.data.CS.*;

import java.util.Random;

import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStructureInteriorPortal extends WorldgenStructure {
	public static boolean generate(World aWorld, Random aRandom, int aChunkX, int aChunkZ, StructureData aData) {
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			for (int i = 0; i < 6; i++) for (int j = 0; j < 6; j++) if (i == 0 || j == 0 || i == 5 || j == 5) {
				if ((i == 0 || i == 5) && (j == 0 || j == 5)) {
					setLampBlock(aWorld, aChunkX+ 1+i, aData.mOffsetY+1, aChunkZ+ 5+j, aData, aRandom, -1);
				} else {
					setColored(aWorld, aChunkX+ 1+i, aData.mOffsetY+1, aChunkZ+ 5+j, aData, aRandom);
				}
			} else {
				setRandomBricks(aWorld, aChunkX+ 1+i, aData.mOffsetY+1, aChunkZ+ 5+j, aData, aRandom);
			}
			
			setRandomBricks(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 5, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 6, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 9, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+10, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 2, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			for (int i = 0; i < 6; i++) for (int j = 0; j < 6; j++) if (i == 0 || j == 0 || i == 5 || j == 5) {
				if ((i == 0 || i == 5) && (j == 0 || j == 5)) {
					setLampBlock(aWorld, aChunkX+ 9+i, aData.mOffsetY+1, aChunkZ+ 5+j, aData, aRandom, -1);
				} else {
					setColored(aWorld, aChunkX+ 9+i, aData.mOffsetY+1, aChunkZ+ 5+j, aData, aRandom);
				}
			} else {
				setRandomBricks(aWorld, aChunkX+ 9+i, aData.mOffsetY+1, aChunkZ+ 5+j, aData, aRandom);
			}
			
			setRandomBricks(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 5, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 6, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 9, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+10, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+13, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			for (int i = 0; i < 6; i++) for (int j = 0; j < 6; j++) if (i == 0 || j == 0 || i == 5 || j == 5) {
				if ((i == 0 || i == 5) && (j == 0 || j == 5)) {
					setLampBlock(aWorld, aChunkX+ 5+i, aData.mOffsetY+1, aChunkZ+ 1+j, aData, aRandom, -1);
				} else {
					setColored(aWorld, aChunkX+ 5+i, aData.mOffsetY+1, aChunkZ+ 1+j, aData, aRandom);
				}
			} else {
				setRandomBricks(aWorld, aChunkX+ 5+i, aData.mOffsetY+1, aChunkZ+ 1+j, aData, aRandom);
			}
			
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 1, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 2, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 3, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 5, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 6, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 7, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 6, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 5, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 4, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 3, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 2, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 1, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			for (int i = 0; i < 6; i++) for (int j = 0; j < 6; j++) if (i == 0 || j == 0 || i == 5 || j == 5) {
				if ((i == 0 || i == 5) && (j == 0 || j == 5)) {
					setLampBlock(aWorld, aChunkX+ 5+i, aData.mOffsetY+1, aChunkZ+ 9+j, aData, aRandom, -1);
				} else {
					setColored(aWorld, aChunkX+ 5+i, aData.mOffsetY+1, aChunkZ+ 9+j, aData, aRandom);
				}
			} else {
				setRandomBricks(aWorld, aChunkX+ 5+i, aData.mOffsetY+1, aChunkZ+ 9+j, aData, aRandom);
			}
			
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+14, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+13, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+12, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+10, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 9, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 4, aData.mOffsetY+1, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 8, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+ 9, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+10, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+11, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+12, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+13, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
			setRandomBricks(aWorld, aChunkX+11, aData.mOffsetY+1, aChunkZ+14, aData, aData.mPrimary.mSlabs[0], aData.mSecondary.mSlabs[0], aRandom);
		}
		return T;
	}
}
