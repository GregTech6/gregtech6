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
import gregapi.util.WD;
import net.minecraft.init.Blocks;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomFarm extends DungeonChunkRoomEmpty {
	@Override
	public boolean generate(DungeonData aData) {
		super.generate(aData);
		
		for (int tCoord = 1; tCoord <= 14; tCoord++) if (tCoord <= 4 || tCoord >= 11) {
			aData.bricks(tCoord,  1,  5, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
			aData.bricks(tCoord,  1, 10, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
			aData.bricks( 5,  1, tCoord, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
			aData.bricks(10,  1, tCoord, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
			
			aData.bricks(tCoord,  5,  5, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
			aData.bricks(tCoord,  5, 10, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
			aData.bricks( 5,  5, tCoord, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
			aData.bricks(10,  5, tCoord, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
			
			aData.bricks(tCoord,  6,  5, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
			aData.bricks(tCoord,  6, 10, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
			aData.bricks( 5,  6, tCoord, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
			aData.bricks(10,  6, tCoord, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
		}
		
		for (int tX = 1; tX <= 14; tX++) for (int tZ = 1; tZ <= 14; tZ++) if ((tX <= 4 || tX >= 11) && (tZ <= 4 || tZ >= 11)) {
			aData.lamp(tX, 5, tZ, +1);
			
			if (tX >= 4 && tX <= 11 && tZ >= 4 && tZ <= 11) {
				aData.set(tX, 1, tZ, Blocks.water, 0, 2);
				aData.set(tX, 2, tZ, BlocksGT.Glowtus, aData.nextMetaA(), 2);
			} else {
				aData.set(tX, 1, tZ, Blocks.farmland, 15, 2);
				if (tX >= 8) {
					if (tZ >= 8) {
						if (WD.even(tX, 2, tZ)) {
							aData.set(tX, 2, tZ, aData.next1in2() ? Blocks.melon_stem : Blocks.pumpkin_stem, aData.next(8), 2);
						} else {
							aData.set(tX, 2, tZ, aData.next1in2() ? Blocks.melon_block : Blocks.pumpkin, aData.next(8), 2);
						}
					} else {
						aData.set(tX, 2, tZ, aData.next1in2() ? Blocks.carrots : Blocks.potatoes, aData.next(8), 2);
					}
				} else {
					if (tZ >= 8) {
						aData.set(tX, 2, tZ, Blocks.wheat, aData.next(8), 2);
					} else {
						aData.set(tX, 2, tZ, Blocks.wheat, aData.next(8), 2);
					}
				}
			}
		}
		
		aData.set( 5,  1,  5, 32065); aData.set( 5,  2,  5, Blocks.reeds); aData.set( 5,  3,  5, Blocks.reeds); aData.set( 5,  4,  5, Blocks.reeds);
		aData.set( 5,  1, 10, 32065); aData.set( 5,  2, 10, Blocks.reeds); aData.set( 5,  3, 10, Blocks.reeds); aData.set( 5,  4, 10, Blocks.reeds);
		aData.set(10,  1,  5, 32065); aData.set(10,  2,  5, Blocks.reeds); aData.set(10,  3,  5, Blocks.reeds); aData.set(10,  4,  5, Blocks.reeds);
		aData.set(10,  1, 10, 32065); aData.set(10,  2, 10, Blocks.reeds); aData.set(10,  3, 10, Blocks.reeds); aData.set(10,  4, 10, Blocks.reeds);
		
		return T;
	}
}
