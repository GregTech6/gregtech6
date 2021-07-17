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

import gregapi.block.metatype.BlockMetaType;
import gregapi.data.CS.BlocksGT;
import gregapi.data.FL;
import gregapi.data.MD;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraftforge.common.ChestGenHooks;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomLibraryNormal extends DungeonChunkRoomLibrary {
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mTags.contains(WorldgenDungeonGT.TAG_LIBRARY_NORMAL) || !super.generate(aData)) return F;
		aData.mTags.add(WorldgenDungeonGT.TAG_LIBRARY_NORMAL);
		
		Block
		tHexoriumColor  = ST.block(MD.HEX, UT.Code.select(aData.mColor, "blockEnergizedHexoriumMonolithRainbow", HEXORIUM_MONOLITHS)),
		tHexoriumRandom = ST.block(MD.HEX, UT.Code.select(              "blockEnergizedHexoriumMonolithRainbow", HEXORIUM_MONOLITHS));
		
		boolean tDidntGenerateZPM = T;
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
			aData.set  (14, 1,  5, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			aData.shelf(14, 1,  6,  7839, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
			aData.set  (14, 1,  7, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			aData.set  (14, 1,  8, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			aData.shelf(14, 1,  9,  7839, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
			aData.set  (14, 1, 10, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			
			if (aData.next1in4()) aData.coins(14, 2,  5);
			if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm(14, 2,  6)) tDidntGenerateZPM = F; else aData.pot(14, 2,  6, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
			aData.cup(14, 2,  7+aData.next(2), FL.Potion_NightVision_1L);
			if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm(14, 2,  9)) tDidntGenerateZPM = F; else aData.pot(14, 2,  9, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
			if (aData.next1in4()) aData.coins(14, 2, 10);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
			aData.set  ( 1, 1,  5, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			aData.shelf( 1, 1,  6,  7839, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
			aData.set  ( 1, 1,  7, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			aData.set  ( 1, 1,  8, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			aData.shelf( 1, 1,  9,  7839, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
			aData.set  ( 1, 1, 10, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			
			if (aData.next1in4()) aData.coins( 1, 2,  5);
			if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm( 1, 2,  6)) tDidntGenerateZPM = F; else aData.pot( 1, 2,  6, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
			aData.cup( 1, 2,  7+aData.next(2), FL.Potion_NightVision_1L);
			if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm( 1, 2,  9)) tDidntGenerateZPM = F; else aData.pot( 1, 2,  9, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
			if (aData.next1in4()) aData.coins( 1, 2, 10);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
			aData.set  ( 5, 1, 14, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			aData.shelf( 6, 1, 14,  7839, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
			aData.set  ( 7, 1, 14, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			aData.set  ( 8, 1, 14, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			aData.shelf( 9, 1, 14,  7839, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
			aData.set  (10, 1, 14, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			
			if (aData.next1in4()) aData.coins( 5, 2, 14);
			if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm( 6, 2, 14)) tDidntGenerateZPM = F; else aData.pot( 6, 2, 14, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
			aData.cup( 7+aData.next(2), 2, 14, FL.Potion_NightVision_1L);
			if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm( 9, 2, 14)) tDidntGenerateZPM = F; else aData.pot( 9, 2, 14, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
			if (aData.next1in4()) aData.coins(10, 2, 14);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
			aData.set  ( 5, 1,  1, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			aData.shelf( 6, 1,  1,  7839, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
			aData.set  ( 7, 1,  1, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			aData.set  ( 8, 1,  1, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			aData.shelf( 9, 1,  1,  7839, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
			aData.set  (10, 1,  1, ((BlockMetaType)BlocksGT.Planks2).mSlabs[1], 0);
			
			if (aData.next1in4()) aData.coins( 5, 2,  1);
			if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm( 6, 2,  1)) tDidntGenerateZPM = F; else aData.pot( 6, 2,  1, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
			aData.cup( 7+aData.next(2), 2,  1, FL.Potion_NightVision_1L);
			if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm( 9, 2,  1)) tDidntGenerateZPM = F; else aData.pot( 9, 2,  1, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
			if (aData.next1in4()) aData.coins(10, 2,  1);
		}
		return T;
	}
}
