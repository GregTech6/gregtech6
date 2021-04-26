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

import gregapi.data.IL;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
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
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Blocks.obsidian  , 16, 0)), "s", (short) 4));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Blocks.netherrack, 16, 0)), "s", (short)11));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Blocks.glowstone , 16, 0)), "s", (short)15));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Hunting_Blaze"  )), "s", (short)22));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Items.ghast_tear ,  4, 0)), "s", (short)29));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(Items.blaze_rod  ,  4, 0)), "s", (short)33));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(IL.Tool_MatchBox_Full     .get(1)), "s", (short)40));
		
		for (int tX = 1; tX < 15; tX++) for (int tZ = 1; tZ < 15; tZ++) {
			if ((tX == 3 || tX == 6 || tX == 9 || tX == 12) && (tZ == 3 || tZ == 6 || tZ == 9 || tZ == 12)) {
				aData.set(tX, 0, tZ, Blocks.glowstone, 0);
			} else {
				aData.set(tX, 0, tZ, Blocks.netherrack, 0);
			}
		}
		
		Block tShroomLight = IL.NeLi_ShroomLight.block(), tWartBlock = IL.NeLi_Wart_Block_Crimson.block();
		int tMeta = aData.next(3);
		
		if (ST.valid(tShroomLight) && ST.valid(tWartBlock)) {
			for (int tX = 1; tX < 15; tX++) for (int tZ = 1; tZ < 15; tZ++) {
				if ((tX == 3 || tX == 6 || tX == 9 || tX == 12) && (tZ == 3 || tZ == 6 || tZ == 9 || tZ == 12)) {
					aData.set  (tX, 7, tZ, tShroomLight);
					aData.tiles(tX, 8, tZ);
				} else {
					aData.set  (tX, 7, tZ, tWartBlock, tMeta);
					aData.tiles(tX, 8, tZ);
				}
			}
		}
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			aData.set( 1, 2,  5, SIDE_UNKNOWN, (short)502, UT.NBT.make(NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tInventory), T, T);
			
			for (int i = 1; i < 15; i++) {
				aData.set   ( i, 1,  1, Blocks.soul_sand);
				aData.set   ( i, 2,  1, Blocks.nether_wart, aData.next(4));
				aData.smooth( i, 3,  1, aData.mPrimary.mSlabs[SIDE_Y_POS], aData.mSecondary.mSlabs[SIDE_Y_POS]);
				aData.set   ( i, 1, 14, Blocks.soul_sand);
				aData.set   ( i, 2, 14, Blocks.nether_wart, aData.next(4));
				aData.smooth( i, 3, 14, aData.mPrimary.mSlabs[SIDE_Y_POS], aData.mSecondary.mSlabs[SIDE_Y_POS]);
				aData.smooth( i, 1,  2, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
				aData.smooth( i, 1, 13, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
			}
			
			aData.obsidian( 2, 1,  6, T);
			aData.obsidian( 2, 1,  7, T);
			aData.obsidian( 2, 1,  8, T);
			aData.obsidian( 2, 1,  9, T);
			aData.obsidian( 2, 2,  6, T);
			aData.obsidian( 2, 2,  9, T);
			aData.obsidian( 2, 3,  6, T);
			aData.obsidian( 2, 3,  9, T);
			aData.obsidian( 2, 4,  6, T);
			aData.obsidian( 2, 4,  9, T);
			aData.obsidian( 2, 5,  6, T);
			aData.obsidian( 2, 5,  7, F);
			aData.obsidian( 2, 5,  8, F);
			aData.obsidian( 2, 5,  9, T);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			aData.set(14, 2, 10, SIDE_UNKNOWN, (short)502, UT.NBT.make(NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tInventory), T, T);
			
			for (int i = 1; i < 15; i++) {
				aData.set   ( i, 1,  1, Blocks.soul_sand);
				aData.set   ( i, 2,  1, Blocks.nether_wart, aData.next(4));
				aData.smooth( i, 3,  1, aData.mPrimary.mSlabs[SIDE_Y_POS], aData.mSecondary.mSlabs[SIDE_Y_POS]);
				aData.set   ( i, 1, 14, Blocks.soul_sand);
				aData.set   ( i, 2, 14, Blocks.nether_wart, aData.next(4));
				aData.smooth( i, 3, 14, aData.mPrimary.mSlabs[SIDE_Y_POS], aData.mSecondary.mSlabs[SIDE_Y_POS]);
				aData.smooth( i, 1,  2, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
				aData.smooth( i, 1, 13, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
			}
			
			aData.obsidian(13, 1,  6, T);
			aData.obsidian(13, 1,  7, T);
			aData.obsidian(13, 1,  8, T);
			aData.obsidian(13, 1,  9, T);
			aData.obsidian(13, 2,  6, T);
			aData.obsidian(13, 2,  9, T);
			aData.obsidian(13, 3,  6, T);
			aData.obsidian(13, 3,  9, T);
			aData.obsidian(13, 4,  6, T);
			aData.obsidian(13, 4,  9, T);
			aData.obsidian(13, 5,  6, T);
			aData.obsidian(13, 5,  7, F);
			aData.obsidian(13, 5,  8, F);
			aData.obsidian(13, 5,  9, T);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			aData.set( 5, 2,  1, SIDE_UNKNOWN, (short)502, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory), T, T);
			
			for (int i = 1; i < 15; i++) {
				aData.set   ( 1, 1,  i, Blocks.soul_sand);
				aData.set   ( 1, 2,  i, Blocks.nether_wart, aData.next(4));
				aData.smooth( 1, 3,  i, aData.mPrimary.mSlabs[SIDE_Y_POS], aData.mSecondary.mSlabs[SIDE_Y_POS]);
				aData.set   (14, 1,  i, Blocks.soul_sand);
				aData.set   (14, 2,  i, Blocks.nether_wart, aData.next(4));
				aData.smooth(14, 3,  i, aData.mPrimary.mSlabs[SIDE_Y_POS], aData.mSecondary.mSlabs[SIDE_Y_POS]);
				aData.smooth( 2, 1,  i, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
				aData.smooth(13, 1,  i, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
			}
			
			aData.obsidian( 6, 1,  2, T);
			aData.obsidian( 7, 1,  2, T);
			aData.obsidian( 8, 1,  2, T);
			aData.obsidian( 9, 1,  2, T);
			aData.obsidian( 6, 2,  2, T);
			aData.obsidian( 9, 2,  2, T);
			aData.obsidian( 6, 3,  2, T);
			aData.obsidian( 9, 3,  2, T);
			aData.obsidian( 6, 4,  2, T);
			aData.obsidian( 9, 4,  2, T);
			aData.obsidian( 6, 5,  2, T);
			aData.obsidian( 7, 5,  2, F);
			aData.obsidian( 8, 5,  2, F);
			aData.obsidian( 9, 5,  2, T);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			aData.set(10, 2, 14, SIDE_UNKNOWN, (short)502, UT.NBT.make(NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tInventory), T, T);
			
			for (int i = 1; i < 15; i++) {
				aData.set   ( 1, 1,  i, Blocks.soul_sand);
				aData.set   ( 1, 2,  i, Blocks.nether_wart, aData.next(4));
				aData.smooth( 1, 3,  i, aData.mPrimary.mSlabs[SIDE_Y_POS], aData.mSecondary.mSlabs[SIDE_Y_POS]);
				aData.set   (14, 1,  i, Blocks.soul_sand);
				aData.set   (14, 2,  i, Blocks.nether_wart, aData.next(4));
				aData.smooth(14, 3,  i, aData.mPrimary.mSlabs[SIDE_Y_POS], aData.mSecondary.mSlabs[SIDE_Y_POS]);
				aData.smooth( 2, 1,  i, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
				aData.smooth(13, 1,  i, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
			}
			
			aData.obsidian( 6, 1, 13, T);
			aData.obsidian( 7, 1, 13, T);
			aData.obsidian( 8, 1, 13, T);
			aData.obsidian( 9, 1, 13, T);
			aData.obsidian( 6, 2, 13, T);
			aData.obsidian( 9, 2, 13, T);
			aData.obsidian( 6, 3, 13, T);
			aData.obsidian( 9, 3, 13, T);
			aData.obsidian( 6, 4, 13, T);
			aData.obsidian( 9, 4, 13, T);
			aData.obsidian( 6, 5, 13, T);
			aData.obsidian( 7, 5, 13, F);
			aData.obsidian( 8, 5, 13, F);
			aData.obsidian( 9, 5, 13, T);
		}
		return T;
	}
}
