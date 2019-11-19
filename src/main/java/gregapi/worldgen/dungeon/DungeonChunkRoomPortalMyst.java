/**
 * Copyright (c) 2019 Gregorius Techneticies
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

import gregapi.data.IL;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagList;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomPortalMyst extends DungeonChunkRoomPortal {
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mTags.contains(WorldgenDungeonGT.TAG_PORTAL_MYST) || !super.generate(aData)) return F;
		aData.mTags.add(WorldgenDungeonGT.TAG_PORTAL_MYST);
		
		NBTTagList tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)12));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)13));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)14));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)21));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)23));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)30));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)32));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)39));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)40));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Myst_Crystal.get(5)), "s", (short)41));
		
		Block tCrystal = IL.Myst_Crystal.block();
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			aData.set( 1, 2,  5, SIDE_UNKNOWN, 502, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tInventory), T, T);
			
			aData.set( 2, 1,  6, tCrystal, 0, 2);
			aData.set( 2, 1,  7, tCrystal, 0, 2);
			aData.set( 2, 1,  8, tCrystal, 0, 2);
			aData.set( 2, 1,  9, tCrystal, 0, 2);
			aData.set( 2, 2,  6, tCrystal, 0, 2);
			aData.set( 2, 2,  9, tCrystal, 0, 2);
			aData.set( 2, 3,  6, tCrystal, 0, 2);
			aData.set( 2, 3,  9, tCrystal, 0, 2);
			aData.set( 2, 4,  6, tCrystal, 0, 2);
			aData.set( 2, 4,  9, tCrystal, 0, 2);
			aData.set( 2, 5,  6, tCrystal, 0, 2);
			aData.set( 2, 5,  7, tCrystal, 0, 2);
			aData.set( 2, 5,  8, tCrystal, 0, 2);
			aData.set( 2, 5,  9, tCrystal, 0, 2);
			
			aData.set( 1, 5,  9, IL.Myst_Receptacle.block(), SIDE_X_NEG, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			aData.set(14, 2, 10, SIDE_UNKNOWN, 502, UT.NBT.make(NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tInventory), T, T);
			
			aData.set(13, 1,  6, tCrystal, 0, 2);
			aData.set(13, 1,  7, tCrystal, 0, 2);
			aData.set(13, 1,  8, tCrystal, 0, 2);
			aData.set(13, 1,  9, tCrystal, 0, 2);
			aData.set(13, 2,  6, tCrystal, 0, 2);
			aData.set(13, 2,  9, tCrystal, 0, 2);
			aData.set(13, 3,  6, tCrystal, 0, 2);
			aData.set(13, 3,  9, tCrystal, 0, 2);
			aData.set(13, 4,  6, tCrystal, 0, 2);
			aData.set(13, 4,  9, tCrystal, 0, 2);
			aData.set(13, 5,  6, tCrystal, 0, 2);
			aData.set(13, 5,  7, tCrystal, 0, 2);
			aData.set(13, 5,  8, tCrystal, 0, 2);
			aData.set(13, 5,  9, tCrystal, 0, 2);
			
			aData.set(14, 5,  6, IL.Myst_Receptacle.block(), SIDE_X_POS, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			aData.set( 5, 2,  1, SIDE_UNKNOWN, 502, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory), T, T);
			
			aData.set( 6, 1,  2, tCrystal, 0, 2);
			aData.set( 7, 1,  2, tCrystal, 0, 2);
			aData.set( 8, 1,  2, tCrystal, 0, 2);
			aData.set( 9, 1,  2, tCrystal, 0, 2);
			aData.set( 6, 2,  2, tCrystal, 0, 2);
			aData.set( 9, 2,  2, tCrystal, 0, 2);
			aData.set( 6, 3,  2, tCrystal, 0, 2);
			aData.set( 9, 3,  2, tCrystal, 0, 2);
			aData.set( 6, 4,  2, tCrystal, 0, 2);
			aData.set( 9, 4,  2, tCrystal, 0, 2);
			aData.set( 6, 5,  2, tCrystal, 0, 2);
			aData.set( 7, 5,  2, tCrystal, 0, 2);
			aData.set( 8, 5,  2, tCrystal, 0, 2);
			aData.set( 9, 5,  2, tCrystal, 0, 2);
			
			aData.set( 9, 5,  1, IL.Myst_Receptacle.block(), SIDE_Z_NEG, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			aData.set(10, 2, 14, SIDE_UNKNOWN, 502, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tInventory), T, T);
			
			aData.set( 6, 1, 13, tCrystal, 0, 2);
			aData.set( 7, 1, 13, tCrystal, 0, 2);
			aData.set( 8, 1, 13, tCrystal, 0, 2);
			aData.set( 9, 1, 13, tCrystal, 0, 2);
			aData.set( 6, 2, 13, tCrystal, 0, 2);
			aData.set( 9, 2, 13, tCrystal, 0, 2);
			aData.set( 6, 3, 13, tCrystal, 0, 2);
			aData.set( 9, 3, 13, tCrystal, 0, 2);
			aData.set( 6, 4, 13, tCrystal, 0, 2);
			aData.set( 9, 4, 13, tCrystal, 0, 2);
			aData.set( 6, 5, 13, tCrystal, 0, 2);
			aData.set( 7, 5, 13, tCrystal, 0, 2);
			aData.set( 8, 5, 13, tCrystal, 0, 2);
			aData.set( 9, 5, 13, tCrystal, 0, 2);
			
			aData.set( 6, 5, 14, IL.Myst_Receptacle.block(), SIDE_Z_POS, 2);
		}
		return T;
	}
}
