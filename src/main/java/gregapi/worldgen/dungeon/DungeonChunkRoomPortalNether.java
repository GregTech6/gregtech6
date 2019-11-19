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
public class DungeonChunkRoomPortalNether extends DungeonChunkRoomPortal {
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mTags.contains(WorldgenDungeonGT.TAG_PORTAL_NETHER) || !super.generate(aData)) return F;
		aData.mTags.add(WorldgenDungeonGT.TAG_PORTAL_NETHER);
		
		NBTTagList tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Hunting_Blaze")), "s", (short)22));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Tool_MatchBox_Full.get(1)), "s", (short)31));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Blocks.netherrack, 4, 0)), "s", (short)12));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Items.ghast_tear, 4, 0)), "s", (short)39));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Blocks.glowstone, 4, 0)), "s", (short)14));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Items.blaze_rod, 4, 0)), "s", (short)41));
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			aData.set( 1, 2,  5, SIDE_UNKNOWN, (short)502, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tInventory), T, T);
			
			for (int i = 1; i < 15; i++) {
				aData.set( i, 1,  1, Blocks.soul_sand, 0, 2);
				aData.set( i, 2,  1, Blocks.nether_wart, aData.mRandom.nextInt(4), 2);
				aData.set( i, 1, 14, Blocks.soul_sand, 0, 2);
				aData.set( i, 2, 14, Blocks.nether_wart, aData.mRandom.nextInt(4), 2);
				aData.bricks( i, 1,  2, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
				aData.bricks( i, 1, 13, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
			}
			
			aData.set( 2, 1,  6, Blocks.obsidian, 0, 2);
			aData.set( 2, 1,  7, Blocks.obsidian, 0, 2);
			aData.set( 2, 1,  8, Blocks.obsidian, 0, 2);
			aData.set( 2, 1,  9, Blocks.obsidian, 0, 2);
			aData.set( 2, 2,  6, Blocks.obsidian, 0, 2);
			aData.set( 2, 2,  9, Blocks.obsidian, 0, 2);
			aData.set( 2, 3,  6, Blocks.obsidian, 0, 2);
			aData.set( 2, 3,  9, Blocks.obsidian, 0, 2);
			aData.set( 2, 4,  6, Blocks.obsidian, 0, 2);
			aData.set( 2, 4,  9, Blocks.obsidian, 0, 2);
			aData.set( 2, 5,  6, Blocks.obsidian, 0, 2);
			aData.set( 2, 5,  7, Blocks.obsidian, 0, 2);
			aData.set( 2, 5,  8, Blocks.obsidian, 0, 2);
			aData.set( 2, 5,  9, Blocks.obsidian, 0, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			aData.set(14, 2, 10, SIDE_UNKNOWN, (short)502, UT.NBT.make(NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tInventory), T, T);
			
			for (int i = 1; i < 15; i++) {
				aData.set( i, 1,  1, Blocks.soul_sand, 0, 2);
				aData.set( i, 2,  1, Blocks.nether_wart, aData.mRandom.nextInt(4), 2);
				aData.set( i, 1, 14, Blocks.soul_sand, 0, 2);
				aData.set( i, 2, 14, Blocks.nether_wart, aData.mRandom.nextInt(4), 2);
				aData.bricks( i, 1,  2, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
				aData.bricks( i, 1, 13, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
			}
			
			aData.set(13, 1,  6, Blocks.obsidian, 0, 2);
			aData.set(13, 1,  7, Blocks.obsidian, 0, 2);
			aData.set(13, 1,  8, Blocks.obsidian, 0, 2);
			aData.set(13, 1,  9, Blocks.obsidian, 0, 2);
			aData.set(13, 2,  6, Blocks.obsidian, 0, 2);
			aData.set(13, 2,  9, Blocks.obsidian, 0, 2);
			aData.set(13, 3,  6, Blocks.obsidian, 0, 2);
			aData.set(13, 3,  9, Blocks.obsidian, 0, 2);
			aData.set(13, 4,  6, Blocks.obsidian, 0, 2);
			aData.set(13, 4,  9, Blocks.obsidian, 0, 2);
			aData.set(13, 5,  6, Blocks.obsidian, 0, 2);
			aData.set(13, 5,  7, Blocks.obsidian, 0, 2);
			aData.set(13, 5,  8, Blocks.obsidian, 0, 2);
			aData.set(13, 5,  9, Blocks.obsidian, 0, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			aData.set( 5, 2,  1, SIDE_UNKNOWN, (short)502, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory), T, T);
			
			for (int i = 1; i < 15; i++) {
				aData.set( 1, 1,  i, Blocks.soul_sand, 0, 2);
				aData.set( 1, 2,  i, Blocks.nether_wart, aData.mRandom.nextInt(4), 2);
				aData.set(14, 1,  i, Blocks.soul_sand, 0, 2);
				aData.set(14, 2,  i, Blocks.nether_wart, aData.mRandom.nextInt(4), 2);
				aData.bricks( 2, 1,  i, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
				aData.bricks(13, 1,  i, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
			}
			
			aData.set( 6, 1,  2, Blocks.obsidian, 0, 2);
			aData.set( 7, 1,  2, Blocks.obsidian, 0, 2);
			aData.set( 8, 1,  2, Blocks.obsidian, 0, 2);
			aData.set( 9, 1,  2, Blocks.obsidian, 0, 2);
			aData.set( 6, 2,  2, Blocks.obsidian, 0, 2);
			aData.set( 9, 2,  2, Blocks.obsidian, 0, 2);
			aData.set( 6, 3,  2, Blocks.obsidian, 0, 2);
			aData.set( 9, 3,  2, Blocks.obsidian, 0, 2);
			aData.set( 6, 4,  2, Blocks.obsidian, 0, 2);
			aData.set( 9, 4,  2, Blocks.obsidian, 0, 2);
			aData.set( 6, 5,  2, Blocks.obsidian, 0, 2);
			aData.set( 7, 5,  2, Blocks.obsidian, 0, 2);
			aData.set( 8, 5,  2, Blocks.obsidian, 0, 2);
			aData.set( 9, 5,  2, Blocks.obsidian, 0, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			aData.set(10, 2, 14, SIDE_UNKNOWN, (short)502, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tInventory), T, T);
			
			for (int i = 1; i < 15; i++) {
				aData.set( 1, 1,  i, Blocks.soul_sand, 0, 2);
				aData.set( 1, 2,  i, Blocks.nether_wart, aData.mRandom.nextInt(4), 2);
				aData.set(14, 1,  i, Blocks.soul_sand, 0, 2);
				aData.set(14, 2,  i, Blocks.nether_wart, aData.mRandom.nextInt(4), 2);
				aData.bricks( 2, 1,  i, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
				aData.bricks(13, 1,  i, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
			}
			
			aData.set( 6, 1, 13, Blocks.obsidian, 0, 2);
			aData.set( 7, 1, 13, Blocks.obsidian, 0, 2);
			aData.set( 8, 1, 13, Blocks.obsidian, 0, 2);
			aData.set( 9, 1, 13, Blocks.obsidian, 0, 2);
			aData.set( 6, 2, 13, Blocks.obsidian, 0, 2);
			aData.set( 9, 2, 13, Blocks.obsidian, 0, 2);
			aData.set( 6, 3, 13, Blocks.obsidian, 0, 2);
			aData.set( 9, 3, 13, Blocks.obsidian, 0, 2);
			aData.set( 6, 4, 13, Blocks.obsidian, 0, 2);
			aData.set( 9, 4, 13, Blocks.obsidian, 0, 2);
			aData.set( 6, 5, 13, Blocks.obsidian, 0, 2);
			aData.set( 7, 5, 13, Blocks.obsidian, 0, 2);
			aData.set( 8, 5, 13, Blocks.obsidian, 0, 2);
			aData.set( 9, 5, 13, Blocks.obsidian, 0, 2);
		}
		return T;
	}
}
