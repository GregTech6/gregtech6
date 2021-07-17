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

import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.fluid.FluidTankGT;
import net.minecraft.block.Block;
import net.minecraftforge.common.ChestGenHooks;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomLibraryMystcraft extends DungeonChunkRoomLibrary {
	@Override
	public boolean generate(DungeonData aData) {
		if (!MD.MYST.mLoaded || !aData.mTags.contains(WorldgenDungeonGT.TAG_LIBRARY_MYST) || !super.generate(aData)) return F;
		aData.mTags.add(WorldgenDungeonGT.TAG_LIBRARY_MYST);
		
		Block tWritingTable = IL.Myst_Desk_Block.block(), tBookBinder = IL.Myst_Book_Binder.block(), tInkMixer = IL.Myst_Ink_Mixer.block(), tLectern = IL.Myst_Lectern.block(), tStand = IL.Myst_Bookstand.block();
		boolean temp = T;
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
			if (temp) {
				temp = F;
				aData.set  (14, 1,  5, tInkMixer    , 0, 2, 1);
				aData.set  (14, 1,  6, 32705, FluidTankGT.writeToNBT(NBT_TANK+".out.0", FL.InkMyst.make(1000+1000*aData.next(8))));
				aData.set  (14, 1,  7, tWritingTable, 0);
				aData.set  (14, 1,  8, tWritingTable, 8);
				aData.shelf(14, 1,  9,  7000, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  (14, 1, 10, tBookBinder  , 0, 2, 1);
				
				aData.set  (14, 2,  9, tLectern     , 0, 2, 1);
			} else {
				aData.shelf(14, 1,  5,  7000, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  (14, 1,  6, tStand       , 0, 2, 2);
				aData.shelf(14, 1,  7,  7000, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.shelf(14, 1,  8,  7000, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  (14, 1,  9, tStand       , 0, 2, 2);
				aData.shelf(14, 1, 10,  7000, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				
				aData.set  (14, 2,  5, tLectern     , 0, 2, 1);
				aData.set  (14, 2,  7, tLectern     , 0, 2, 1);
				aData.set  (14, 2,  8, tLectern     , 0, 2, 1);
				aData.set  (14, 2, 10, tLectern     , 0, 2, 1);
			}
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
			if (temp) {
				temp = F;
				aData.set  ( 1, 1,  5, tBookBinder  , 0, 2, 3);
				aData.shelf( 1, 1,  6,  7000, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 1, 1,  7, tWritingTable,10);
				aData.set  ( 1, 1,  8, tWritingTable, 2);
				aData.set  ( 1, 1,  9, 32705, FluidTankGT.writeToNBT(NBT_TANK+".out.0", FL.InkMyst.make(1000+1000*aData.next(8))));
				aData.set  ( 1, 1, 10, tInkMixer    , 0, 2, 3);
				
				aData.set  ( 1, 2,  6, tLectern     , 0, 2, 3);
			} else {
				aData.shelf( 1, 1,  5,  7000, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 1, 1,  6, tStand       , 0, 2, 6);
				aData.shelf( 1, 1,  7,  7000, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.shelf( 1, 1,  8,  7000, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 1, 1,  9, tStand       , 0, 2, 6);
				aData.shelf( 1, 1, 10,  7000, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				
				aData.set  ( 1, 2,  5, tLectern     , 0, 2, 3);
				aData.set  ( 1, 2,  7, tLectern     , 0, 2, 3);
				aData.set  ( 1, 2,  8, tLectern     , 0, 2, 3);
				aData.set  ( 1, 2, 10, tLectern     , 0, 2, 3);
			}
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
			if (temp) {
				temp = F;
				aData.set  ( 5, 1, 14, tBookBinder  , 0, 2, 2);
				aData.shelf( 6, 1, 14,  7000, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 7, 1, 14, tWritingTable, 9);
				aData.set  ( 8, 1, 14, tWritingTable, 1);
				aData.set  ( 9, 1, 14, 32705, FluidTankGT.writeToNBT(NBT_TANK+".out.0", FL.InkMyst.make(1000+1000*aData.next(8))));
				aData.set  (10, 1, 14, tInkMixer    , 0, 2, 2);
				
				aData.set  ( 6, 2, 14, tLectern     , 0, 2, 2);
			} else {
				aData.shelf( 5, 1, 14,  7000, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 6, 1, 14, tStand       , 0, 2, 4);
				aData.shelf( 7, 1, 14,  7000, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.shelf( 8, 1, 14,  7000, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 9, 1, 14, tStand       , 0, 2, 4);
				aData.shelf(10, 1, 14,  7000, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				
				aData.set  ( 5, 2, 14, tLectern     , 0, 2, 2);
				aData.set  ( 7, 2, 14, tLectern     , 0, 2, 2);
				aData.set  ( 8, 2, 14, tLectern     , 0, 2, 2);
				aData.set  (10, 2, 14, tLectern     , 0, 2, 2);
			}
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
			if (temp) {
				temp = F;
				aData.set  ( 5, 1,  1, tInkMixer    , 0, 2, 0);
				aData.set  ( 6, 1,  1, 32705, FluidTankGT.writeToNBT(NBT_TANK+".out.0", FL.InkMyst.make(1000+1000*aData.next(8))));
				aData.set  ( 7, 1,  1, tWritingTable, 3);
				aData.set  ( 8, 1,  1, tWritingTable,11);
				aData.shelf( 9, 1,  1,  7000, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  (10, 1,  1, tBookBinder  , 0, 2, 0);
				
				aData.set  ( 9, 2,  1, tLectern     , 0, 2, 0);
			} else {
				aData.shelf( 5, 1,  1,  7000, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 6, 1,  1, tStand       , 0, 2, 0);
				aData.shelf( 7, 1,  1,  7000, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.shelf( 8, 1,  1,  7000, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 9, 1,  1, tStand       , 0, 2, 0);
				aData.shelf(10, 1,  1,  7000, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				
				aData.set  ( 5, 2,  1, tLectern     , 0, 2, 0);
				aData.set  ( 7, 2,  1, tLectern     , 0, 2, 0);
				aData.set  ( 8, 2,  1, tLectern     , 0, 2, 0);
				aData.set  (10, 2,  1, tLectern     , 0, 2, 0);
			}
		}
		return T;
	}
}
