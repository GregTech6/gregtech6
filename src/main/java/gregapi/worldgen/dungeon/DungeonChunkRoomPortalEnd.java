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

import gregapi.data.MD;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomPortalEnd extends DungeonChunkRoomVault {
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mTags.contains(WorldgenDungeonGT.TAG_PORTAL_END) || !super.generate(aData)) return F;
		aData.mTags.add(WorldgenDungeonGT.TAG_PORTAL_END);
		
		for (int tX = 1; tX < 15; tX++) for (int tZ = 1; tZ < 15; tZ++) {
			if ((tX == 3 || tX == 6 || tX == 9 || tX == 12) && (tZ == 3 || tZ == 6 || tZ == 9 || tZ == 12)) {
				aData.set(tX, 0, tZ, Blocks.glowstone, 0);
			} else {
				aData.set(tX, 0, tZ, Blocks.end_stone, 0);
			}
		}
		
		Block tPurpurBlock = ST.block(MD.EtFu, "purpur_block"), tPurpurPillar = ST.block(MD.EtFu, "purpur_pillar");
		
		if (ST.valid(tPurpurBlock) && ST.valid(tPurpurPillar)) {
			for (int tX = 1; tX < 15; tX++) for (int tZ = 1; tZ < 15; tZ++) {
				if ((tX == 1 || tX == 2 || tX == 13 || tX == 14) && (tZ == 1 || tZ == 2 || tZ == 13 || tZ == 14)) {
					aData.set  (tX, 0, tZ, tPurpurPillar, 0);
					aData.set  (tX, 1, tZ, tPurpurPillar, 0);
					aData.set  (tX, 2, tZ, tPurpurPillar, 0);
					aData.set  (tX, 3, tZ, tPurpurPillar, 0);
					aData.set  (tX, 4, tZ, tPurpurPillar, 0);
					aData.set  (tX, 5, tZ, tPurpurPillar, 0);
					aData.set  (tX, 6, tZ, tPurpurPillar, 0);
					aData.set  (tX, 7, tZ, tPurpurPillar, 0);
				} else if ((tX == 3 || tX == 6 || tX == 9 || tX == 12) && (tZ == 3 || tZ == 6 || tZ == 9 || tZ == 12)) {
					aData.set  (tX, 0, tZ, tPurpurPillar, 0);
					aData.set  (tX, 7, tZ, Blocks.glowstone);
					aData.tiles(tX, 8, tZ);
				} else {
					aData.set  (tX, 0, tZ, tPurpurBlock, 0);
					aData.set  (tX, 7, tZ, tPurpurBlock, 0);
					aData.tiles(tX, 8, tZ);
				}
			}
		}
		
		Block tHexorium = ST.block(MD.HEX, "blockEnergizedHexoriumMonolithRainbow");
		
		for (int tX = 5; tX <= 10; tX++) for (int tZ = 5; tZ <= 10; tZ++) {
			if ((tX == 5 || tX == 10) && (tZ == 5 || tZ == 10)) {
				aData.obsidian(tX,  0, tZ, T);
				aData.obsidian(tX,  1, tZ, T);
				if (ST.valid(tHexorium)) {
					aData.set(tX, 2, tZ, tHexorium, 9, 3);
				} else {
					aData.set(tX, 2, tZ, Blocks.glowstone, 0, 3);
				}
			}
		}
		aData.set     ( 7,  0,  6, Blocks.end_portal_frame, 4);
		aData.set     ( 8,  0,  6, Blocks.end_portal_frame, 4);
		aData.set     ( 9,  0,  7, Blocks.end_portal_frame, 5);
		aData.set     ( 9,  0,  8, Blocks.end_portal_frame, 5);
		aData.set     ( 7,  0,  9, Blocks.end_portal_frame, 6);
		aData.set     ( 8,  0,  9, Blocks.end_portal_frame, 6);
		aData.set     ( 6,  0,  7, Blocks.end_portal_frame, 7);
		aData.set     ( 6,  0,  8, Blocks.end_portal_frame, 7);
		aData.set     ( 7,  0,  7, Blocks.end_portal, 0);
		aData.set     ( 7,  0,  8, Blocks.end_portal, 0);
		aData.set     ( 8,  0,  7, Blocks.end_portal, 0);
		aData.set     ( 8,  0,  8, Blocks.end_portal, 0);
		aData.obsidian( 7, -1,  7, T);
		aData.obsidian( 7, -1,  8, T);
		aData.obsidian( 8, -1,  7, T);
		aData.obsidian( 8, -1,  8, T);
		return T;
	}
}
