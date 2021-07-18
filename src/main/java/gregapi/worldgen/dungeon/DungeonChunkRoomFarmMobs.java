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
import gregapi.data.OP;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomFarmMobs extends DungeonChunkRoomEmpty {
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mTags.contains(WorldgenDungeonGT.TAG_FARM_MOBS) || !super.generate(aData)) return F;
		aData.mTags.add(WorldgenDungeonGT.TAG_FARM_MOBS);
		
		// Roof, two Blocks thick due to Sunlight glitches.
		for (int tX = 0; tX < 16; tX++) for (int tZ = 0; tZ < 16; tZ++) {
			aData.tiles     (tX, 43, tZ);
			aData.smalltiles(tX, 42, tZ);
		}
		
		// Outer Walls.
		for (int tY = 9; tY < 42; tY++) {
			for (int tX = 0; tX < 16; tX++) for (int tZ = 0; tZ < 16; tZ++) {
				if (tX == 0 || tX == 15 || tZ == 0 || tZ == 15) {
					aData.bricks(tX, tY, tZ);
				} else {
					aData.air(tX, tY, tZ);
				}
			}
		}
		
		// Make a solid Pillar, the rest will be carved away when needed.
		for (int tY =  1; tY <=  6; tY++) for (int tX =  6; tX <=  9; tX++) for (int tZ =  6; tZ <=  9; tZ++) aData.bricks(tX, tY, tZ);
		for (int tY =  7; tY <=  8; tY++) for (int tX =  5; tX <= 10; tX++) for (int tZ =  5; tZ <= 10; tZ++) aData.smalltiles(tX, tY, tZ);
		
		// Golden Omni-Spikes! (Steel wont work on Skeletons!)
		aData.set     ( 7,  9,  7, BlocksGT.Spikes_Fancy, 6);
		aData.set     ( 7,  9,  8, BlocksGT.Spikes_Fancy, 6);
		aData.set     ( 8,  9,  7, BlocksGT.Spikes_Fancy, 6);
		aData.set     ( 8,  9,  8, BlocksGT.Spikes_Fancy, 6);
		
		// Steel Hoppers!
		aData.set     ( 7,  8,  7, SIDE_UNKNOWN,  8010, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 7,  8,  8, SIDE_UNKNOWN,  8010, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  8,  7, SIDE_UNKNOWN,  8010, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  8,  8, SIDE_UNKNOWN,  8010, UT.NBT.make(NBT_FACING, SIDE_Y_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		
		// Item Pipes and Mass Storages.
		aData.chiseled( 7,  7,  7);
		aData.chiseled( 7,  7,  8);
		aData.chiseled( 8,  7,  7);
		aData.set     ( 8,  7,  8, SIDE_UNKNOWN, 25002, UT.NBT.make(NBT_CONNECTION    ,  3, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  6,  8, SIDE_UNKNOWN, 25002, UT.NBT.make(NBT_CONNECTION    ,  3, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  5,  8, SIDE_UNKNOWN, 25002, UT.NBT.make(NBT_CONNECTION    ,  3, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  4,  8, SIDE_UNKNOWN, 25002, UT.NBT.make(NBT_CONNECTION    ,  3, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		
		aData.chiseled( 6,  3,  6);
		aData.set     ( 6,  3,  7, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_X_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.glass_bottle  , 1+aData.next(8), 0))), T, T);
		aData.set     ( 6,  3,  8, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_X_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.leather       , 1+aData.next(8), 0))), T, T);
		aData.chiseled( 6,  3,  9);
		aData.set     ( 7,  3,  6, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.string        , 1+aData.next(8), 0))), T, T);
		aData.set     ( 7,  3,  7, SIDE_UNKNOWN, 25002, UT.NBT.make(NBT_CONNECTION    , 60, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 7,  3,  8, SIDE_UNKNOWN, 25002, UT.NBT.make(NBT_CONNECTION    , 60, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 7,  3,  9, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.redstone      , 1+aData.next(8), 0))), T, T);
		aData.set     ( 8,  3,  6, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.spider_eye    , 1+aData.next(8), 0))), T, T);
		aData.set     ( 8,  3,  7, SIDE_UNKNOWN, 25002, UT.NBT.make(NBT_CONNECTION    , 60, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  3,  8, SIDE_UNKNOWN, 25002, UT.NBT.make(NBT_CONNECTION    , 63, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
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
		aData.set     ( 7,  2,  7, SIDE_UNKNOWN, 25005, UT.NBT.make(NBT_CONNECTION    , 60, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 7,  2,  8, SIDE_UNKNOWN, 25002, UT.NBT.make(NBT_CONNECTION    , 60, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 7,  2,  9, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.gunpowder     , 1+aData.next(8), 0))), T, T);
		aData.set     ( 8,  2,  6, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.rotten_flesh  , 1+aData.next(8), 0))), T, T);
		aData.set     ( 8,  2,  7, SIDE_UNKNOWN, 25002, UT.NBT.make(NBT_CONNECTION    , 60, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  2,  8, SIDE_UNKNOWN, 25002, UT.NBT.make(NBT_CONNECTION    , 62, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		aData.set     ( 8,  2,  9, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.sugar         , 1+aData.next(8), 0))), T, T);
		aData.chiseled( 9,  2,  6);
		aData.set     ( 9,  2,  7, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(ST.make(Items.arrow         , 1+aData.next(8), 0))), T, T);
		aData.set     ( 9,  2,  8, SIDE_UNKNOWN,  6009, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_INV_LIST, UT.NBT.makeInv(OP.arrowGtWood.mat(MT.Empty , 1+aData.next(8)   ))), T, T);
		aData.chiseled( 9,  2,  9);
		
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
