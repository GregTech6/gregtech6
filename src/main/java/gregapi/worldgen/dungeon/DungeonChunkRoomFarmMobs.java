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

import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomFarmMobs extends DungeonChunkRoomEmpty {
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mTags.contains(WorldgenDungeonGT.TAG_FARM_MOBS) || !super.generate(aData)) return F;
		aData.mTags.add(WorldgenDungeonGT.TAG_FARM_MOBS);
		
		// Check if we have space for a large Setup or not.
		boolean tXNZN = 
		(aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ-1] == 0 || aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ-1] == -128) &&
		(aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ  ] == 0 || aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ  ] == -128) &&
		(aData.mRoomLayout[aData.mRoomX  ][aData.mRoomZ-1] == 0 || aData.mRoomLayout[aData.mRoomX  ][aData.mRoomZ-1] == -128);
		boolean tXPZN = 
		(aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ-1] == 0 || aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ-1] == -128) &&
		(aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ  ] == 0 || aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ  ] == -128) &&
		(aData.mRoomLayout[aData.mRoomX  ][aData.mRoomZ-1] == 0 || aData.mRoomLayout[aData.mRoomX  ][aData.mRoomZ-1] == -128);
		boolean tXNZP = 
		(aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ+1] == 0 || aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ+1] == -128) &&
		(aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ  ] == 0 || aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ  ] == -128) &&
		(aData.mRoomLayout[aData.mRoomX  ][aData.mRoomZ+1] == 0 || aData.mRoomLayout[aData.mRoomX  ][aData.mRoomZ+1] == -128);
		boolean tXPZP = 
		(aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ+1] == 0 || aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ+1] == -128) &&
		(aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ  ] == 0 || aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ  ] == -128) &&
		(aData.mRoomLayout[aData.mRoomX  ][aData.mRoomZ+1] == 0 || aData.mRoomLayout[aData.mRoomX  ][aData.mRoomZ+1] == -128);
		
		// Make a solid Pillar, the rest will be carved away when needed.
		for (int tY =  1; tY <=  6; tY++) for (int tX =  6; tX <=  9; tX++) for (int tZ =  6; tZ <=  9; tZ++) aData.bricks(tX, tY, tZ);
		for (int tY =  7; tY <=  8; tY++) for (int tX =  5; tX <= 10; tX++) for (int tZ =  5; tZ <= 10; tZ++) aData.smalltiles(tX, tY, tZ);
		
		// Platforms for Mobs to spawn on.
		makePlatForms(aData, 0, 0);
		if (tXNZN || tXNZP) makePlatForms(aData,-16,  0);
		if (tXPZN || tXPZP) makePlatForms(aData,+16,  0);
		if (tXNZN || tXPZN) makePlatForms(aData,  0,-16);
		if (tXNZP || tXPZP) makePlatForms(aData,  0,+16);
		if (tXNZN) makePlatForms(aData,-16,-16);
		if (tXPZN) makePlatForms(aData,+16,-16);
		if (tXNZP) makePlatForms(aData,-16,+16);
		if (tXPZP) makePlatForms(aData,+16,+16);
		
		// Lamps need to be replaced.
		aData.lamp( 3, 6,  3, +1);
		aData.lamp( 3, 6,  6, +1);
		aData.lamp( 3, 6,  9, +1);
		aData.lamp( 3, 6, 12, +1);
		aData.lamp( 6, 6,  3, +1);
		aData.lamp( 9, 6,  3, +1);
		aData.lamp( 6, 6, 12, +1);
		aData.lamp( 9, 6, 12, +1);
		aData.lamp(12, 6,  3, +1);
		aData.lamp(12, 6,  6, +1);
		aData.lamp(12, 6,  9, +1);
		aData.lamp(12, 6, 12, +1);
		
		// Item Pipes and Mass Storages.
		aData.set     ( 8,  6,  8, SIDE_DOWN   , 25377, UT.NBT.make(NBT_CONNECTION    ,  3, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  5,  8, SIDE_DOWN   , 25377, UT.NBT.make(NBT_CONNECTION    ,  3, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  4,  8, SIDE_DOWN   , 25377, UT.NBT.make(NBT_CONNECTION    ,  3, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.chiseled( 6,  3,  6);
		aData.set     ( 6,  3,  7, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_X_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.glass_bottle  , 1+aData.next(8), 0))), T, T);
		aData.set     ( 6,  3,  8, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_X_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.slime_ball    , 1+aData.next(8), 0))), T, T);
		aData.chiseled( 6,  3,  9);
		aData.set     ( 7,  3,  6, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.string        , 1+aData.next(8), 0))), T, T);
		aData.set     ( 7,  3,  7, SIDE_UNKNOWN, 25377, UT.NBT.make(NBT_CONNECTION    , 60, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 7,  3,  8, SIDE_UNKNOWN, 25377, UT.NBT.make(NBT_CONNECTION    , 60, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 7,  3,  9, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.redstone      , 1+aData.next(8), 0))), T, T);
		aData.set     ( 8,  3,  6, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.spider_eye    , 1+aData.next(8), 0))), T, T);
		aData.set     ( 8,  3,  7, SIDE_UNKNOWN, 25377, UT.NBT.make(NBT_CONNECTION    , 60, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  3,  8, SIDE_UNKNOWN, 25377, UT.NBT.make(NBT_CONNECTION    , 63, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  3,  9, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.glowstone_dust, 1+aData.next(8), 0))), T, T);
		aData.chiseled( 9,  3,  6);
		aData.set     ( 9,  3,  7, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.bone          , 1+aData.next(8), 0))), T, T);
		aData.set     ( 9,  3,  8, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.stick         , 1+aData.next(8), 0))), T, T);
		aData.chiseled( 9,  3,  9);
		
		aData.chiseled( 6,  2,  6);
		aData.smooth  ( 6,  2,  7); // Nothing can be placed here without messing up the Restrictor Pipe!
		aData.set     ( 6,  2,  8, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_X_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.feather       , 1+aData.next(8), 0))), T, T);
		aData.chiseled( 6,  2,  9);
		aData.set     ( 7,  2,  6, SIDE_UNKNOWN,  4009, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 7,  2,  7, SIDE_UNKNOWN, 25377, UT.NBT.make(NBT_CONNECTION    , 60, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 7,  2,  8, SIDE_UNKNOWN, 25377, UT.NBT.make(NBT_CONNECTION    , 60, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 7,  2,  9, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.gunpowder     , 1+aData.next(8), 0))), T, T);
		aData.set     ( 8,  2,  6, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.rotten_flesh  , 1+aData.next(8), 0))), T, T);
		aData.set     ( 8,  2,  7, SIDE_UNKNOWN, 25377, UT.NBT.make(NBT_CONNECTION    , 60, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  2,  8, SIDE_UNKNOWN, 25377, UT.NBT.make(NBT_CONNECTION    , 62, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  2,  9, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.sugar         , 1+aData.next(8), 0))), T, T);
		aData.chiseled( 9,  2,  6);
		aData.set     ( 9,  2,  7, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.arrow         , 1+aData.next(8), 0))), T, T);
		aData.set     ( 9,  2,  8, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(OP.arrowGtWood.mat(MT.Empty , 1+aData.next(8)   ))), T, T);
		aData.chiseled( 9,  2,  9);
		
		return T;
	}
	
	public static boolean makePlatForms(DungeonData aData, int aX, int aZ) {
		for (int eX = aX+15, tX = aX; tX <= eX; tX++)
		for (int eZ = aZ+15, tZ = aZ; tZ <= eZ; tZ++) {
			// Roof, two Blocks thick due to Sunlight glitches.
			aData.tiles     (tX, 43, tZ);
			aData.smalltiles(tX, 42, tZ);
			// Floor for the Water to flow on without dripping beneath.
			aData.smalltiles(tX,  8, tZ);
			aData.tiles     (tX,  7, tZ);
			aData.tiles     (tX,  6, tZ);
			// Outer Walls filled with Air.
			for (int tY = 9; tY < 42; tY++) {
				if (tX == aX || tX == eX || tZ == aZ || tZ == eZ) {
					aData.bricks(tX, tY, tZ);
				} else {
					aData.air(tX, tY, tZ);
				}
			}
		}
		
		// Platforms for Mobs to spawn on.
		final int[] tPlatforms = { 3, 4, 5, 6, 9,10,11,12};
		for (int tY = 12; tY < 42; tY++) if (tY % 3 == 0) {
			for (int i : tPlatforms) for (int j : tPlatforms) aData.cobbles(aX+ i, tY, aZ+ j);
			for (int i : tPlatforms) {
				aData.set(aX+ i, tY, aZ+ 2, Blocks.trapdoor, 12); aData.set(aX+ i, tY, aZ+ 8, Blocks.trapdoor, 12);
				aData.set(aX+ i, tY, aZ+13, Blocks.trapdoor, 13); aData.set(aX+ i, tY, aZ+ 7, Blocks.trapdoor, 13);
				aData.set(aX+ 2, tY, aZ+ i, Blocks.trapdoor, 14); aData.set(aX+ 8, tY, aZ+ i, Blocks.trapdoor, 14);
				aData.set(aX+13, tY, aZ+ i, Blocks.trapdoor, 15); aData.set(aX+ 7, tY, aZ+ i, Blocks.trapdoor, 15);
			}
		}
		
		// Blue or Red Steel Omni-Spikes, so all possible Mobs are taken care of!
		int tSpike = (aData.next1in2() ? 6 : 14);
		aData.set     (aX+ 7,  9, aZ+ 7, BlocksGT.Spikes_Steel, tSpike);
		aData.set     (aX+ 7,  9, aZ+ 8, BlocksGT.Spikes_Steel, tSpike);
		aData.set     (aX+ 8,  9, aZ+ 7, BlocksGT.Spikes_Steel, tSpike);
		aData.set     (aX+ 8,  9, aZ+ 8, BlocksGT.Spikes_Steel, tSpike);
		
		// Steel Hoppers!
		aData.set     (aX+ 7,  8, aZ+ 7, SIDE_UNKNOWN,  8010, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     (aX+ 7,  8, aZ+ 8, SIDE_UNKNOWN,  8010, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     (aX+ 8,  8, aZ+ 7, SIDE_UNKNOWN,  8010, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     (aX+ 8,  8, aZ+ 8, SIDE_UNKNOWN,  8010, UT.NBT.make(NBT_FACING, SIDE_Y_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		
		// Item Pipes.
		aData.chiseled(aX+ 7,  7, aZ+ 7);
		aData.chiseled(aX+ 7,  7, aZ+ 8);
		aData.chiseled(aX+ 8,  7, aZ+ 7);
		aData.set     (aX+ 8,  7, aZ+ 8, SIDE_Y_NEG, 25377, UT.NBT.make(NBT_CONNECTION,              63, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		if (aX > 0) for (int tX = aX- 7, eX = aX+ 7; tX <= eX; tX++)
		aData.set     (tX   ,  7, aZ+ 8, SIDE_X_NEG, 25377, UT.NBT.make(NBT_CONNECTION, SBIT_W | SBIT_E, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		if (aX < 0) for (int tX = aX+ 9, eX = aX+23; tX <= eX; tX++)
		aData.set     (tX   ,  7, aZ+ 8, SIDE_X_POS, 25377, UT.NBT.make(NBT_CONNECTION, SBIT_W | SBIT_E, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		if (aZ > 0) for (int tZ = aZ- 7, eZ = aZ+ 7; tZ <= eZ; tZ++)
		aData.set     (aX+ 8,  7, tZ   , SIDE_Z_NEG, 25377, UT.NBT.make(NBT_CONNECTION, SBIT_N | SBIT_S, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		if (aZ < 0) for (int tZ = aZ+ 9, eZ = aZ+23; tZ <= eZ; tZ++)
		aData.set     (aX+ 8,  7, tZ   , SIDE_Z_POS, 25377, UT.NBT.make(NBT_CONNECTION, SBIT_N | SBIT_S, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		
		// Water Placement in center Area.
		aData.smooth(aX+ 1,  9, aZ+ 1); aData.smooth(aX+ 2,  9, aZ+ 1); aData.smooth(aX+ 3,  9, aZ+ 1); aData.smooth(aX+ 4,  9, aZ+ 1);
		aData.smooth(aX+ 1,  9, aZ+ 2); aData.smooth(aX+ 2,  9, aZ+ 2); aData.smooth(aX+ 3,  9, aZ+ 2);
		aData.smooth(aX+ 1,  9, aZ+ 3); aData.smooth(aX+ 2,  9, aZ+ 3);
		aData.smooth(aX+ 1,  9, aZ+ 4);
		aData.smooth(aX+14,  9, aZ+ 1); aData.smooth(aX+13,  9, aZ+ 1); aData.smooth(aX+12,  9, aZ+ 1); aData.smooth(aX+11,  9, aZ+ 1);
		aData.smooth(aX+14,  9, aZ+ 2); aData.smooth(aX+13,  9, aZ+ 2); aData.smooth(aX+12,  9, aZ+ 2);
		aData.smooth(aX+14,  9, aZ+ 3); aData.smooth(aX+13,  9, aZ+ 3);
		aData.smooth(aX+14,  9, aZ+ 4);
		aData.smooth(aX+ 1,  9, aZ+14); aData.smooth(aX+ 2,  9, aZ+14); aData.smooth(aX+ 3,  9, aZ+14); aData.smooth(aX+ 4,  9, aZ+14);
		aData.smooth(aX+ 1,  9, aZ+13); aData.smooth(aX+ 2,  9, aZ+13); aData.smooth(aX+ 3,  9, aZ+13);
		aData.smooth(aX+ 1,  9, aZ+12); aData.smooth(aX+ 2,  9, aZ+12);
		aData.smooth(aX+ 1,  9, aZ+11);
		aData.smooth(aX+14,  9, aZ+14); aData.smooth(aX+13,  9, aZ+14); aData.smooth(aX+12,  9, aZ+14); aData.smooth(aX+11,  9, aZ+14);
		aData.smooth(aX+14,  9, aZ+13); aData.smooth(aX+13,  9, aZ+13); aData.smooth(aX+12,  9, aZ+13);
		aData.smooth(aX+14,  9, aZ+12); aData.smooth(aX+13,  9, aZ+12);
		aData.smooth(aX+14,  9, aZ+11);
		aData.set(aX+ 1, 10, aZ+ 1, Blocks.flowing_water, 0, 3);
		aData.set(aX+ 1, 10, aZ+14, Blocks.flowing_water, 0, 3);
		aData.set(aX+14, 10, aZ+ 1, Blocks.flowing_water, 0, 3);
		aData.set(aX+14, 10, aZ+14, Blocks.flowing_water, 0, 3);
		
		return T;
	}
}
