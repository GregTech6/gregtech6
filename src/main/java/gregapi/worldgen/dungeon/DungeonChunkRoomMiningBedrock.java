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
import gregapi.data.MT;
import gregapi.data.MT.OREMATS;
import gregapi.util.UT;
import gregapi.worldgen.WorldgenOresBedrock;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomMiningBedrock extends DungeonChunkRoomEmpty {
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mTags.contains(WorldgenDungeonGT.TAG_MINING_BEDROCK) || !super.generate(aData)) return F;
		aData.mTags.add(WorldgenDungeonGT.TAG_MINING_BEDROCK);
		if (!WorldgenOresBedrock.generateVein(UT.Code.select(MT.Redstone, MT.Redstone, MT.S, MT.Fe2O3, MT.MnO2, MT.Apatite, OREMATS.Molybdenite, MT.OREMATS.Bauxite, MT.OREMATS.Sphalerite, MT.OREMATS.Tetrahedrite, MT.OREMATS.Cassiterite, MT.OREMATS.Garnierite, MT.OREMATS.Galena), aData.mWorld, aData.mWorld.provider.dimensionId, aData.mX, aData.mZ, aData.mRandom)) return F;
		
		for (int tY = 6-aData.mY; tY < 0; tY++) {
			for (int tX =  2; tX <= 13; tX++) for (int tZ =  2; tZ <= 13; tZ++) {
				if (tX ==  2) {
					if (tZ ==  8 && aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
						aData.set   ( 2, tY, tZ,  8410, UT.NBT.make(NBT_FACING, SIDE_X_POS));
						aData.bricks( 1, tY, tZ);
					} else {
						aData.bricks( 2, tY, tZ);
					}
				} else if (tX == 13) {
					if (tZ ==  7 && aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
						aData.set   (13, tY, tZ,  8410, UT.NBT.make(NBT_FACING, SIDE_X_NEG));
						aData.bricks(14, tY, tZ);
					} else {
						aData.bricks(13, tY, tZ);
					}
				} else if (tZ ==  2) {
					if (tX ==  7 && aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
						aData.set   (tX, tY,  2,  8410, UT.NBT.make(NBT_FACING, SIDE_Z_POS));
						aData.bricks(tX, tY,  1);
					} else {
						aData.bricks(tX, tY,  2);
					}
				} else if (tZ == 13) {
					if (tX ==  8 && aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
						aData.set   (tX, tY, 13,  8410, UT.NBT.make(NBT_FACING, SIDE_Z_NEG));
						aData.bricks(tX, tY, 14);
					} else {
						aData.bricks(tX, tY, 13);
					}
				} else {
					aData.air   (tX, tY, tZ);
				}
			}
		}
		
		for (int tX =  2; tX <= 13; tX++) for (int tZ =  2; tZ <= 13; tZ++) {
			if (tX ==  2) {
				if (tZ != 2 && tZ != 13) {
				aData.set   (tX,  1, tZ, BlocksGT.Bars_Steel, 8);
				aData.set   (tX,  0, tZ,  8410, UT.NBT.make(NBT_FACING, SIDE_X_POS));
				} else {
				aData.lamp(tX,  0, tZ, -1);
				}
			} else if (tX == 13) {
				if (tZ != 2 && tZ != 13) {
				aData.set   (tX,  1, tZ, BlocksGT.Bars_Steel, 4);
				aData.set   (tX,  0, tZ,  8410, UT.NBT.make(NBT_FACING, SIDE_X_NEG));
				} else {
				aData.lamp(tX,  0, tZ, -1);
				}
			} else if (tZ ==  2) {
				aData.set   (tX,  1, tZ, BlocksGT.Bars_Steel, 2);
				aData.set   (tX,  0, tZ,  8410, UT.NBT.make(NBT_FACING, SIDE_Z_POS));
			} else if (tZ == 13) {
				aData.set   (tX,  1, tZ, BlocksGT.Bars_Steel, 1);
				aData.set   (tX,  0, tZ,  8410, UT.NBT.make(NBT_FACING, SIDE_Z_NEG));
			} else {
				aData.air   (tX,  0, tZ);
			}
		}
		
		return T;
	}
}
