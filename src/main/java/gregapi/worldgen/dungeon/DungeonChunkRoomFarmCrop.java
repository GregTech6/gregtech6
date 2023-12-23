/**
 * Copyright (c) 2023 GregTech-6 Team
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

import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.*;
import gregapi.data.MD;
import gregapi.util.ST;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomFarmCrop extends DungeonChunkRoomEmpty {
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mTags.contains(WorldgenDungeonGT.TAG_FARM_CROP) || !super.generate(aData)) return F;
		aData.mTags.add(WorldgenDungeonGT.TAG_FARM_CROP);
		
		for (int tCoord = 1; tCoord <= 14; tCoord++) if (tCoord <= 4 || tCoord >= 11) {
			aData.smooth(tCoord,  1,      5, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
			aData.smooth(tCoord,  1,     10, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
			aData.smooth(     5,  1, tCoord, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
			aData.smooth(    10,  1, tCoord, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
			
			aData.smooth(tCoord,  5,      5, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
			aData.smooth(tCoord,  5,     10, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
			aData.smooth(     5,  5, tCoord, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
			aData.smooth(    10,  5, tCoord, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
			
			aData.smooth(tCoord,  6,      5, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
			aData.smooth(tCoord,  6,     10, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
			aData.smooth(     5,  6, tCoord, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
			aData.smooth(    10,  6, tCoord, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
		}
		
		// All these Crops grow in 8 MetaData Stages. The "null" Parameter in the Mod Blocks is REQUIRED!
		ArrayListNoNulls<Block> tCrops = new ArrayListNoNulls<>(F
		, Blocks.carrots
		, Blocks.potatoes
		, Blocks.wheat
		, BlocksGT.EtFu_Beetroot_Crop
		, ST.block(MD.HaC, "pamartichokeCrop", null)
		, ST.block(MD.HaC, "pamasparagusCrop", null)
		, ST.block(MD.HaC, "pambambooshootCrop", null)
		, ST.block(MD.HaC, "pambarleyCrop", null)
		, ST.block(MD.HaC, "pambeanCrop", null)
		, ST.block(MD.HaC, "pambeetCrop", null)
		, ST.block(MD.HaC, "pambellpepperCrop", null)
		, ST.block(MD.HaC, "pamblackberryCrop", null)
		, ST.block(MD.HaC, "pamblueberryCrop", null)
		, ST.block(MD.HaC, "pambroccoliCrop", null)
		, ST.block(MD.HaC, "pambrusselsproutCrop", null)
		, ST.block(MD.HaC, "pamcabbageCrop", null)
//      , ST.block(MD.HaC, "pamcactusfruitCrop", null) // Does not grow on Farmland, needs Block of Sand!
		, ST.block(MD.HaC, "pamcandleberryCrop", null)
		, ST.block(MD.HaC, "pamcantaloupeCrop", null)
		, ST.block(MD.HaC, "pamcauliflowerCrop", null)
		, ST.block(MD.HaC, "pamceleryCrop", null)
		, ST.block(MD.HaC, "pamchilipepperCrop", null)
		, ST.block(MD.HaC, "pamcoffeebeanCrop", null)
		, ST.block(MD.HaC, "pamcornCrop", null)
		, ST.block(MD.HaC, "pamcottonCrop", null)
//      , ST.block(MD.HaC, "pamcranberryCrop", null) // Does not grow on Farmland, needs Block of Water!
		, ST.block(MD.HaC, "pamcucumberCrop", null)
		, ST.block(MD.HaC, "pamcurryleafCrop", null)
		, ST.block(MD.HaC, "pameggplantCrop", null)
		, ST.block(MD.HaC, "pamgarlicCrop", null)
		, ST.block(MD.HaC, "pamgingerCrop", null)
		, ST.block(MD.HaC, "pamgrapeCrop", null)
		, ST.block(MD.HaC, "pamkiwiCrop", null)
		, ST.block(MD.HaC, "pamleekCrop", null)
		, ST.block(MD.HaC, "pamlettuceCrop", null)
		, ST.block(MD.HaC, "pammustardseedsCrop", null)
		, ST.block(MD.HaC, "pamoatsCrop", null)
		, ST.block(MD.HaC, "pamokraCrop", null)
		, ST.block(MD.HaC, "pamonionCrop", null)
		, ST.block(MD.HaC, "pamparsnipCrop", null)
		, ST.block(MD.HaC, "pampeanutCrop", null)
		, ST.block(MD.HaC, "pampeasCrop", null)
		, ST.block(MD.HaC, "pampineappleCrop", null)
		, ST.block(MD.HaC, "pamradishCrop", null)
		, ST.block(MD.HaC, "pamraspberryCrop", null)
		, ST.block(MD.HaC, "pamrhubarbCrop", null)
//      , ST.block(MD.HaC, "pamriceCrop", null) // Does not grow on Farmland, needs Block of Water!
		, ST.block(MD.HaC, "pamrutabagaCrop", null)
		, ST.block(MD.HaC, "pamryeCrop", null)
		, ST.block(MD.HaC, "pamscallionCrop", null)
//      , ST.block(MD.HaC, "pamseaweedCrop", null) // Does not grow on Farmland, needs Block of Water!
		, ST.block(MD.HaC, "pamsesameseedsCrop", null)
		, ST.block(MD.HaC, "pamsoybeanCrop", null)
		, ST.block(MD.HaC, "pamspiceleafCrop", null)
		, ST.block(MD.HaC, "pamspinachCrop", null)
		, ST.block(MD.HaC, "pamstrawberryCrop", null)
		, ST.block(MD.HaC, "pamsweetpotatoCrop", null)
		, ST.block(MD.HaC, "pamtealeafCrop", null)
		, ST.block(MD.HaC, "pamtomatoCrop", null)
		, ST.block(MD.HaC, "pamturnipCrop", null)
//      , ST.block(MD.HaC, "pamwaterchestnutCrop", null) // Does not grow on Farmland, needs Block of Water!
		, ST.block(MD.HaC, "pamwhitemushroomCrop", null)
		, ST.block(MD.HaC, "pamwintersquashCrop", null)
		, ST.block(MD.HaC, "pamzucchiniCrop", null)
		);
		
		for (int tX = 1; tX <= 14; tX++) for (int tZ = 1; tZ <= 14; tZ++) if ((tX <= 4 || tX >= 11) && (tZ <= 4 || tZ >= 11)) {
			aData.lamp(tX, 5, tZ, +1);
			
			if (tX >= 4 && tX <= 11 && tZ >= 4 && tZ <= 11) {
				aData.set(tX, 1, tZ, Blocks.water, 0, 2);
				aData.set(tX, 2, tZ, BlocksGT.Glowtus, aData.nextMetaA(), 2);
			} else {
				aData.set(tX, 1, tZ, Blocks.farmland, 15, 2);
				if (tX >= 8 && tZ >= 8) {
					if (WD.even(tX, 2, tZ)) {
						aData.set(tX, 2, tZ, Blocks.melon_stem, aData.next(8), Blocks.pumpkin_stem, aData.next(8), 2);
					} else {
						aData.set(tX, 2, tZ, Blocks.melon_block, 0, Blocks.pumpkin, 0, 2);
					}
				} else {
					aData.set(tX, 2, tZ, tCrops.get(aData.next(tCrops.size())), aData.next(8), 2);
				}
			}
		}
		
		aData.set( 5,  1,  5, 32065); aData.set( 5,  2,  5, Blocks.reeds ); aData.set( 5,  3,  5, Blocks.reeds ); aData.set( 5,  4,  5, Blocks.reeds );
		aData.set( 5,  1, 10, 32065); aData.set( 5,  2, 10, Blocks.cactus); aData.set( 5,  3, 10, Blocks.cactus); aData.set( 5,  4, 10, Blocks.cactus);
		aData.set(10,  1,  5, 32065); aData.set(10,  2,  5, Blocks.cactus); aData.set(10,  3,  5, Blocks.cactus); aData.set(10,  4,  5, Blocks.cactus);
		aData.set(10,  1, 10, 32065); aData.set(10,  2, 10, Blocks.reeds ); aData.set(10,  3, 10, Blocks.reeds ); aData.set(10,  4, 10, Blocks.reeds );
		
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
			aData.smooth(14,  3,  5, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
			aData.set   (14,  3,  6, Blocks.log, 11); aData.set(13,  3,  6, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_X_NEG]+aData.next(3)*4);
			aData.set   (14,  3,  7, Blocks.log, 11); aData.set(13,  3,  7, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_X_NEG]+aData.next(3)*4);
			aData.set   (14,  3,  8, Blocks.log, 11); aData.set(13,  3,  8, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_X_NEG]+aData.next(3)*4);
			aData.set   (14,  3,  9, Blocks.log, 11); aData.set(13,  3,  9, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_X_NEG]+aData.next(3)*4);
			aData.smooth(14,  3, 10, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
			
			aData.set   (14,  1,  6, 32065); aData.set(14,  2,  6, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			aData.set   (14,  1,  7, 32065); aData.set(14,  2,  7, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			aData.set   (14,  1,  8, 32065); aData.set(14,  2,  8, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			aData.set   (14,  1,  9, 32065); aData.set(14,  2,  9, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			
			aData.set   (14,  4,  6, 32065); aData.flower(14,  5,  6);
			aData.set   (14,  4,  7, 32065); aData.set   (14,  5,  7, Blocks.double_plant, 1); aData.set(14,  6,  7, Blocks.double_plant, 9);
			aData.set   (14,  4,  8, 32065); aData.flower(14,  5,  8);
			aData.set   (14,  4,  9, 32065); aData.flower(14,  5,  9);
		}
		
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
			aData.smooth( 1,  3,  5, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
			aData.set   ( 1,  3,  6, Blocks.log, 11); aData.set( 2,  3,  6, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_X_POS]+aData.next(3)*4);
			aData.set   ( 1,  3,  7, Blocks.log, 11); aData.set( 2,  3,  7, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_X_POS]+aData.next(3)*4);
			aData.set   ( 1,  3,  8, Blocks.log, 11); aData.set( 2,  3,  8, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_X_POS]+aData.next(3)*4);
			aData.set   ( 1,  3,  9, Blocks.log, 11); aData.set( 2,  3,  9, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_X_POS]+aData.next(3)*4);
			aData.smooth( 1,  3, 10, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
			
			aData.set   ( 1,  1,  6, 32065); aData.set( 1,  2,  6, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			aData.set   ( 1,  1,  7, 32065); aData.set( 1,  2,  7, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			aData.set   ( 1,  1,  8, 32065); aData.set( 1,  2,  8, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			aData.set   ( 1,  1,  9, 32065); aData.set( 1,  2,  9, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			
			aData.set   ( 1,  4,  6, 32065); aData.flower( 1,  5,  6);
			aData.set   ( 1,  4,  7, 32065); aData.flower( 1,  5,  7);
			aData.set   ( 1,  4,  8, 32065); aData.set   ( 1,  5,  8, Blocks.double_plant, 0); aData.set( 1,  6,  8, Blocks.double_plant, 9);
			aData.set   ( 1,  4,  9, 32065); aData.flower( 1,  5,  9);
		}
		
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
			aData.smooth( 5,  3, 14, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
			aData.set   ( 6,  3, 14, Blocks.log,  7); aData.set( 6,  3, 13, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_Z_NEG]+aData.next(3)*4);
			aData.set   ( 7,  3, 14, Blocks.log,  7); aData.set( 7,  3, 13, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_Z_NEG]+aData.next(3)*4);
			aData.set   ( 8,  3, 14, Blocks.log,  7); aData.set( 8,  3, 13, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_Z_NEG]+aData.next(3)*4);
			aData.set   ( 9,  3, 14, Blocks.log,  7); aData.set( 9,  3, 13, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_Z_NEG]+aData.next(3)*4);
			aData.smooth(10,  3, 14, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
			
			aData.set   ( 6,  1, 14, 32065); aData.set( 6,  2, 14, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			aData.set   ( 7,  1, 14, 32065); aData.set( 7,  2, 14, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			aData.set   ( 8,  1, 14, 32065); aData.set( 8,  2, 14, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			aData.set   ( 9,  1, 14, 32065); aData.set( 9,  2, 14, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			
			aData.set   ( 6,  4, 14, 32065); aData.flower( 6,  5, 14);
			aData.set   ( 7,  4, 14, 32065); aData.set   ( 7,  5, 14, Blocks.double_plant, 4); aData.set( 7,  6, 14, Blocks.double_plant, 9);
			aData.set   ( 8,  4, 14, 32065); aData.flower( 8,  5, 14);
			aData.set   ( 9,  4, 14, 32065); aData.flower( 9,  5, 14);
		}
		
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
			aData.smooth( 5,  3,  1, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
			aData.set   ( 6,  3,  1, Blocks.log,  7); aData.set( 6,  3,  2, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_Z_POS]+aData.next(3)*4);
			aData.set   ( 7,  3,  1, Blocks.log,  7); aData.set( 7,  3,  2, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_Z_POS]+aData.next(3)*4);
			aData.set   ( 8,  3,  1, Blocks.log,  7); aData.set( 8,  3,  2, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_Z_POS]+aData.next(3)*4);
			aData.set   ( 9,  3,  1, Blocks.log,  7); aData.set( 9,  3,  2, Blocks.cocoa, COMPASS_FROM_SIDE[SIDE_Z_POS]+aData.next(3)*4);
			aData.smooth(10,  3,  1, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
			
			aData.set   ( 6,  1,  1, 32065); aData.set( 6,  2,  1, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			aData.set   ( 7,  1,  1, 32065); aData.set( 7,  2,  1, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			aData.set   ( 8,  1,  1, 32065); aData.set( 8,  2,  1, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			aData.set   ( 9,  1,  1, 32065); aData.set( 9,  2,  1, BlocksGT.Saplings_AB, aData.next(BlocksGT.Saplings_AB.maxMeta()), BlocksGT.Saplings_CD, aData.next(BlocksGT.Saplings_CD.maxMeta()), Blocks.sapling, aData.next(6));
			
			aData.set   ( 6,  4,  1, 32065); aData.flower( 6,  5,  1);
			aData.set   ( 7,  4,  1, 32065); aData.flower( 7,  5,  1);
			aData.set   ( 8,  4,  1, 32065); aData.set   ( 8,  5,  1, Blocks.double_plant, 5); aData.set( 8,  6,  1, Blocks.double_plant, 9);
			aData.set   ( 9,  4,  1, 32065); aData.flower( 9,  5,  1);
		}
		
		return T;
	}
}
