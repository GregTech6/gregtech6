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

import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.BlocksGT;
import gregapi.data.MD;
import gregapi.util.ST;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomFarm extends DungeonChunkRoomEmpty {
	@Override
	public boolean generate(DungeonData aData) {
		super.generate(aData);
		
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
		, ST.block(MD.EtFu, "beetroots", null)
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
//      , ST.block(MD.HaC, "pamcactusfruitCrop", null) // Does not grow on Farmland
		, ST.block(MD.HaC, "pamcandleberryCrop", null)
		, ST.block(MD.HaC, "pamcantaloupeCrop", null)
		, ST.block(MD.HaC, "pamcauliflowerCrop", null)
		, ST.block(MD.HaC, "pamceleryCrop", null)
		, ST.block(MD.HaC, "pamchilipepperCrop", null)
		, ST.block(MD.HaC, "pamcoffeebeanCrop", null)
		, ST.block(MD.HaC, "pamcornCrop", null)
		, ST.block(MD.HaC, "pamcottonCrop", null)
		, ST.block(MD.HaC, "pamcranberryCrop", null)
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
//      , ST.block(MD.HaC, "pamriceCrop", null) // Does not grow on Farmland
		, ST.block(MD.HaC, "pamrutabagaCrop", null)
		, ST.block(MD.HaC, "pamryeCrop", null)
		, ST.block(MD.HaC, "pamscallionCrop", null)
//      , ST.block(MD.HaC, "pamseaweedCrop", null) // Does not grow on Farmland
		, ST.block(MD.HaC, "pamsesameseedsCrop", null)
		, ST.block(MD.HaC, "pamsoybeanCrop", null)
		, ST.block(MD.HaC, "pamspiceleafCrop", null)
		, ST.block(MD.HaC, "pamspinachCrop", null)
		, ST.block(MD.HaC, "pamstrawberryCrop", null)
		, ST.block(MD.HaC, "pamsweetpotatoCrop", null)
		, ST.block(MD.HaC, "pamtealeafCrop", null)
		, ST.block(MD.HaC, "pamtomatoCrop", null)
		, ST.block(MD.HaC, "pamturnipCrop", null)
		, ST.block(MD.HaC, "pamwaterchestnutCrop", null)
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
		
		// TODO: Put Jungle Logs with Cocoa Beans, and Shelves with Pots of Saplings and Tall Flowers on the Side Walls.
		
		return T;
	}
}
