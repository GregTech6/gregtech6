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
import gregapi.util.UT;
import net.minecraft.init.Blocks;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomFarmMobs extends DungeonChunkRoomEmpty {
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mTags.contains(WorldgenDungeonGT.TAG_FARM_MOBS)) return F;
		aData.mTags.add(WorldgenDungeonGT.TAG_FARM_MOBS);
		
		super.generate(aData);
		
		// Roof
		for (int tX = 0; tX < 16; tX++) for (int tZ = 0; tZ < 16; tZ++) aData.tiles(tX, 42, tZ);
		
		// Outer Walls
		for (int tY = 9; tY < 42; tY++) {
			for (int tX = 0; tX < 16; tX++) for (int tZ = 0; tZ < 16; tZ++) {
				if (tX == 0 || tX == 15 || tZ == 0 || tZ == 15) {
					if (tY % 4 == 0) {
						aData.colored(tX, tY, tZ);
					} else {
						aData.smooth(tX, tY, tZ);
					}
				} else {
					aData.air(tX, tY, tZ);
				}
			}
		}
		
		// Hole Punch!
		aData.air( 7,  7,  7); aData.air( 7,  7,  8); aData.air( 8,  7,  7); aData.air( 8,  7,  8);
		aData.air( 7,  8,  7); aData.air( 7,  8,  8); aData.air( 8,  8,  7); aData.air( 8,  8,  8);
		
		// Golden Spikes! (Steel wont work for Skeletons!)
		aData.set( 7,  4,  7, BlocksGT.Spikes_Fancy, 0);
		aData.set( 7,  4,  8, BlocksGT.Spikes_Fancy, 0);
		aData.set( 8,  4,  7, BlocksGT.Spikes_Fancy, 0);
		aData.set( 8,  4,  8, BlocksGT.Spikes_Fancy, 0);
		
		// Golden Hoppers! (Because why not match the Spikes?)
		aData.set( 7,  3,  7, SIDE_UNKNOWN,  8002, UT.NBT.make(NBT_FACING, SIDE_Z_POS), T, T);
		aData.set( 7,  3,  8, SIDE_UNKNOWN,  8002, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
		aData.set( 8,  3,  7, SIDE_UNKNOWN,  8002, UT.NBT.make(NBT_FACING, SIDE_Z_NEG), T, T);
		aData.set( 8,  3,  8, SIDE_UNKNOWN,  8002, UT.NBT.make(NBT_FACING, SIDE_X_NEG), T, T);
		
		// Steel Bars to prevent Mob spillage
		for (int tY = 4; tY < 7; tY++) {
			aData.set( 6, tY,  7, BlocksGT.Bars_Steel, 8);
			aData.set( 6, tY,  8, BlocksGT.Bars_Steel, 8);
			aData.set( 9, tY,  7, BlocksGT.Bars_Steel, 4);
			aData.set( 9, tY,  8, BlocksGT.Bars_Steel, 4);
			aData.set( 7, tY,  6, BlocksGT.Bars_Steel, 2);
			aData.set( 8, tY,  6, BlocksGT.Bars_Steel, 2);
			aData.set( 7, tY,  9, BlocksGT.Bars_Steel, 1);
			aData.set( 8, tY,  9, BlocksGT.Bars_Steel, 1);
		}
		
		// Water Placement
		aData.smooth( 1,  9,  1); aData.smooth( 2,  9,  1); aData.smooth( 3,  9,  1); aData.smooth( 4,  9,  1);
		aData.smooth( 1,  9,  2); aData.smooth( 2,  9,  2); aData.smooth( 3,  9,  2);
		aData.smooth( 1,  9,  3); aData.smooth( 2,  9,  3);
		aData.smooth( 1,  9,  4);
		aData.smooth(14,  9,  1); aData.smooth(13,  9,  1); aData.smooth(12,  9,  1); aData.smooth(11,  9,  1);
		aData.smooth(14,  9,  2); aData.smooth(13,  9,  2); aData.smooth(12,  9,  2);
		aData.smooth(14,  9,  3); aData.smooth(13,  9,  3);
		aData.smooth(14,  9,  4);
		aData.smooth( 1,  9, 14); aData.smooth( 2,  9, 14); aData.smooth( 3,  9, 14); aData.smooth( 4,  9, 14);
		aData.smooth( 1,  9, 13); aData.smooth( 2,  9, 13); aData.smooth( 3,  9, 13);
		aData.smooth( 1,  9, 12); aData.smooth( 2,  9, 12);
		aData.smooth( 1,  9, 11);
		aData.smooth(14,  9, 14); aData.smooth(13,  9, 14); aData.smooth(12,  9, 14); aData.smooth(11,  9, 14);
		aData.smooth(14,  9, 13); aData.smooth(13,  9, 13); aData.smooth(12,  9, 13);
		aData.smooth(14,  9, 12); aData.smooth(13,  9, 12);
		aData.smooth(14,  9, 11);
		aData.set( 1, 10,  1, Blocks.flowing_water, 0, 3);
		aData.set( 1, 10, 14, Blocks.flowing_water, 0, 3);
		aData.set(14, 10,  1, Blocks.flowing_water, 0, 3);
		aData.set(14, 10, 14, Blocks.flowing_water, 0, 3);
		
		// Platforms for Mobs to spawn on.
		final int[] tPlatforms = { 3, 4, 5, 6, 9,10,11,12};
		for (int tY = 12; tY < 42; tY++) if (tY % 3 == 0) {
			for (int i : tPlatforms) for (int j : tPlatforms) aData.cobbles( i, tY,  j);
			for (int i : tPlatforms) {
				aData.set( i, tY,  2, Blocks.trapdoor, 12); aData.set( i, tY,  8, Blocks.trapdoor, 12);
				aData.set( i, tY, 13, Blocks.trapdoor, 13); aData.set( i, tY,  7, Blocks.trapdoor, 13);
				aData.set( 2, tY,  i, Blocks.trapdoor, 14); aData.set( 8, tY,  i, Blocks.trapdoor, 14);
				aData.set(13, tY,  i, Blocks.trapdoor, 15); aData.set( 7, tY,  i, Blocks.trapdoor, 15);
			}
		}
		
		return T;
	}
}
