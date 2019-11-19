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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagList;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomPortalTwilight extends DungeonChunkRoomPortal {
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mTags.contains(WorldgenDungeonGT.TAG_PORTAL_TWILIGHT) || !super.generate(aData)) return F;
		aData.mTags.add(WorldgenDungeonGT.TAG_PORTAL_TWILIGHT);
		
		NBTTagList tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Portal_TF")), "s", (short)22));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Items.diamond, 1, 0)), "s", (short)31));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.TF_LiveRoot.get(4)), "s", (short)12));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.TF_LiveRoot.get(4)), "s", (short)39));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.TF_LiveRoot.get(4)), "s", (short)14));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.TF_LiveRoot.get(4)), "s", (short)41));
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			aData.set( 1, 2,  5, SIDE_UNKNOWN, (short)502, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tInventory), T, T);
			
			aData.set( 2, 1,  6, Blocks.grass, 0, 2);
			aData.set( 2, 1,  7, Blocks.grass, 0, 2);
			aData.set( 2, 1,  8, Blocks.grass, 0, 2);
			aData.set( 2, 1,  9, Blocks.grass, 0, 2);
			aData.set( 3, 1,  6, Blocks.grass, 0, 2);
			aData.set( 3, 1,  7, Blocks.water, 0, 2);
			aData.set( 3, 1,  8, Blocks.water, 0, 2);
			aData.set( 3, 1,  9, Blocks.grass, 0, 2);
			aData.set( 4, 1,  6, Blocks.grass, 0, 2);
			aData.set( 4, 1,  7, Blocks.water, 0, 2);
			aData.set( 4, 1,  8, Blocks.water, 0, 2);
			aData.set( 4, 1,  9, Blocks.grass, 0, 2);
			aData.set( 5, 1,  6, Blocks.grass, 0, 2);
			aData.set( 5, 1,  7, Blocks.grass, 0, 2);
			aData.set( 5, 1,  8, Blocks.grass, 0, 2);
			aData.set( 5, 1,  9, Blocks.grass, 0, 2);
			
			aData.flower( 2, 2,  6);
			aData.flower( 2, 2,  7);
			aData.flower( 2, 2,  8);
			aData.flower( 2, 2,  9);
			aData.flower( 3, 2,  6);
			aData.flower( 3, 2,  9);
			aData.flower( 4, 2,  6);
			aData.flower( 4, 2,  9);
			aData.flower( 5, 2,  6);
			aData.flower( 5, 2,  7);
			aData.flower( 5, 2,  8);
			aData.flower( 5, 2,  9);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			aData.set(14, 2, 10, SIDE_UNKNOWN, (short)502, UT.NBT.make(NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tInventory), T, T);
			
			aData.set(10, 1,  6, Blocks.grass, 0, 2);
			aData.set(10, 1,  7, Blocks.grass, 0, 2);
			aData.set(10, 1,  8, Blocks.grass, 0, 2);
			aData.set(10, 1,  9, Blocks.grass, 0, 2);
			aData.set(11, 1,  6, Blocks.grass, 0, 2);
			aData.set(11, 1,  7, Blocks.water, 0, 2);
			aData.set(11, 1,  8, Blocks.water, 0, 2);
			aData.set(11, 1,  9, Blocks.grass, 0, 2);
			aData.set(12, 1,  6, Blocks.grass, 0, 2);
			aData.set(12, 1,  7, Blocks.water, 0, 2);
			aData.set(12, 1,  8, Blocks.water, 0, 2);
			aData.set(12, 1,  9, Blocks.grass, 0, 2);
			aData.set(13, 1,  6, Blocks.grass, 0, 2);
			aData.set(13, 1,  7, Blocks.grass, 0, 2);
			aData.set(13, 1,  8, Blocks.grass, 0, 2);
			aData.set(13, 1,  9, Blocks.grass, 0, 2);
			
			aData.flower(10, 2,  6);
			aData.flower(10, 2,  7);
			aData.flower(10, 2,  8);
			aData.flower(10, 2,  9);
			aData.flower(11, 2,  6);
			aData.flower(11, 2,  9);
			aData.flower(12, 2,  6);
			aData.flower(12, 2,  9);
			aData.flower(13, 2,  6);
			aData.flower(13, 2,  7);
			aData.flower(13, 2,  8);
			aData.flower(13, 2,  9);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			aData.set( 5, 2,  1, SIDE_UNKNOWN, (short)502, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory), T, T);
			
			aData.set( 6, 1,  2, Blocks.grass, 0, 2);
			aData.set( 7, 1,  2, Blocks.grass, 0, 2);
			aData.set( 8, 1,  2, Blocks.grass, 0, 2);
			aData.set( 9, 1,  2, Blocks.grass, 0, 2);
			aData.set( 6, 1,  3, Blocks.grass, 0, 2);
			aData.set( 7, 1,  3, Blocks.water, 0, 2);
			aData.set( 8, 1,  3, Blocks.water, 0, 2);
			aData.set( 9, 1,  3, Blocks.grass, 0, 2);
			aData.set( 6, 1,  4, Blocks.grass, 0, 2);
			aData.set( 7, 1,  4, Blocks.water, 0, 2);
			aData.set( 8, 1,  4, Blocks.water, 0, 2);
			aData.set( 9, 1,  4, Blocks.grass, 0, 2);
			aData.set( 6, 1,  5, Blocks.grass, 0, 2);
			aData.set( 7, 1,  5, Blocks.grass, 0, 2);
			aData.set( 8, 1,  5, Blocks.grass, 0, 2);
			aData.set( 9, 1,  5, Blocks.grass, 0, 2);
			
			aData.flower( 6, 2,  2);
			aData.flower( 7, 2,  2);
			aData.flower( 8, 2,  2);
			aData.flower( 9, 2,  2);
			aData.flower( 6, 2,  3);
			aData.flower( 9, 2,  3);
			aData.flower( 6, 2,  4);
			aData.flower( 9, 2,  4);
			aData.flower( 6, 2,  5);
			aData.flower( 7, 2,  5);
			aData.flower( 8, 2,  5);
			aData.flower( 9, 2,  5);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			aData.set(10, 2, 14, SIDE_UNKNOWN, (short)502, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tInventory), T, T);
			
			aData.set( 6, 1, 10, Blocks.grass, 0, 2);
			aData.set( 7, 1, 10, Blocks.grass, 0, 2);
			aData.set( 8, 1, 10, Blocks.grass, 0, 2);
			aData.set( 9, 1, 10, Blocks.grass, 0, 2);
			aData.set( 6, 1, 11, Blocks.grass, 0, 2);
			aData.set( 7, 1, 11, Blocks.water, 0, 2);
			aData.set( 8, 1, 11, Blocks.water, 0, 2);
			aData.set( 9, 1, 11, Blocks.grass, 0, 2);
			aData.set( 6, 1, 12, Blocks.grass, 0, 2);
			aData.set( 7, 1, 12, Blocks.water, 0, 2);
			aData.set( 8, 1, 12, Blocks.water, 0, 2);
			aData.set( 9, 1, 12, Blocks.grass, 0, 2);
			aData.set( 6, 1, 13, Blocks.grass, 0, 2);
			aData.set( 7, 1, 13, Blocks.grass, 0, 2);
			aData.set( 8, 1, 13, Blocks.grass, 0, 2);
			aData.set( 9, 1, 13, Blocks.grass, 0, 2);
			
			aData.flower( 6, 2, 10);
			aData.flower( 7, 2, 10);
			aData.flower( 8, 2, 10);
			aData.flower( 9, 2, 10);
			aData.flower( 6, 2, 11);
			aData.flower( 9, 2, 11);
			aData.flower( 6, 2, 12);
			aData.flower( 9, 2, 12);
			aData.flower( 6, 2, 13);
			aData.flower( 7, 2, 13);
			aData.flower( 8, 2, 13);
			aData.flower( 9, 2, 13);
		}
		return T;
	}
}
