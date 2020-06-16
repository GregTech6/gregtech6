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
		
		Block tHexorium = ST.block(MD.HEX, "blockEnergizedHexoriumMonolithRainbow");
		
		for (int tX = 5; tX <= 10; tX++) for (int tZ = 5; tZ <= 10; tZ++) {
			aData.set(tX, 0, tZ, Blocks.end_stone, 0);
			if ((tX == 5 || tX == 10) && (tZ == 5 || tZ == 10)) {
				aData.set(tX, 1, tZ, Blocks.end_stone, 0);
				if (tHexorium != NB && tHexorium != null) {
					aData.set(tX, 2, tZ, tHexorium, 9, 3);
				} else {
					aData.set(tX, 2, tZ, Blocks.glowstone, 0, 3);
				}
			}
		}
		aData.set( 7,  0,  6, Blocks.end_portal_frame, 4);
		aData.set( 8,  0,  6, Blocks.end_portal_frame, 4);
		aData.set( 9,  0,  7, Blocks.end_portal_frame, 5);
		aData.set( 9,  0,  8, Blocks.end_portal_frame, 5);
		aData.set( 7,  0,  9, Blocks.end_portal_frame, 6);
		aData.set( 8,  0,  9, Blocks.end_portal_frame, 6);
		aData.set( 6,  0,  7, Blocks.end_portal_frame, 7);
		aData.set( 6,  0,  8, Blocks.end_portal_frame, 7);
		aData.set( 7,  0,  7, Blocks.end_portal, 0);
		aData.set( 7,  0,  8, Blocks.end_portal, 0);
		aData.set( 8,  0,  7, Blocks.end_portal, 0);
		aData.set( 8,  0,  8, Blocks.end_portal, 0);
		aData.set( 7, -1,  7, Blocks.end_stone, 0);
		aData.set( 7, -1,  8, Blocks.end_stone, 0);
		aData.set( 8, -1,  7, Blocks.end_stone, 0);
		aData.set( 8, -1,  8, Blocks.end_stone, 0);
		return T;
	}
}
