/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregtech.worldgen.structure;

import static gregapi.data.CS.*;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStructureInteriorPortalEnd extends WorldgenStructure {
	public static boolean generate(World aWorld, Random aRandom, int aChunkX, int aChunkZ, StructureData aData) {
		for (int tX = 5; tX <= 10; tX++) for (int tZ = 5; tZ <= 10; tZ++) {
			setBlock(aWorld, aChunkX+tX, aData.mOffsetY, aChunkZ+tZ, Blocks.end_stone, 0, 2);
			if ((tX == 5 || tX == 10) && (tZ == 5 || tZ == 10)) {
				setBlock(aWorld, aChunkX+tX, aData.mOffsetY+1, aChunkZ+tZ, Blocks.end_stone, 0, 2);
				setBlock(aWorld, aChunkX+tX, aData.mOffsetY+2, aChunkZ+tZ, Blocks.glowstone, 0, 3);
			}
		}
		setBlock(aWorld, aChunkX+ 7, aData.mOffsetY  , aChunkZ+ 6, Blocks.end_portal_frame, 4, 2);
		setBlock(aWorld, aChunkX+ 8, aData.mOffsetY  , aChunkZ+ 6, Blocks.end_portal_frame, 4, 2);
		setBlock(aWorld, aChunkX+ 9, aData.mOffsetY  , aChunkZ+ 7, Blocks.end_portal_frame, 5, 2);
		setBlock(aWorld, aChunkX+ 9, aData.mOffsetY  , aChunkZ+ 8, Blocks.end_portal_frame, 5, 2);
		setBlock(aWorld, aChunkX+ 7, aData.mOffsetY  , aChunkZ+ 9, Blocks.end_portal_frame, 6, 2);
		setBlock(aWorld, aChunkX+ 8, aData.mOffsetY  , aChunkZ+ 9, Blocks.end_portal_frame, 6, 2);
		setBlock(aWorld, aChunkX+ 6, aData.mOffsetY  , aChunkZ+ 7, Blocks.end_portal_frame, 7, 2);
		setBlock(aWorld, aChunkX+ 6, aData.mOffsetY  , aChunkZ+ 8, Blocks.end_portal_frame, 7, 2);
		setBlock(aWorld, aChunkX+ 7, aData.mOffsetY  , aChunkZ+ 7, Blocks.end_portal, 0, 2);
		setBlock(aWorld, aChunkX+ 7, aData.mOffsetY  , aChunkZ+ 8, Blocks.end_portal, 0, 2);
		setBlock(aWorld, aChunkX+ 8, aData.mOffsetY  , aChunkZ+ 7, Blocks.end_portal, 0, 2);
		setBlock(aWorld, aChunkX+ 8, aData.mOffsetY  , aChunkZ+ 8, Blocks.end_portal, 0, 2);
		setBlock(aWorld, aChunkX+ 7, aData.mOffsetY-1, aChunkZ+ 7, Blocks.end_stone, 0, 2);
		setBlock(aWorld, aChunkX+ 7, aData.mOffsetY-1, aChunkZ+ 8, Blocks.end_stone, 0, 2);
		setBlock(aWorld, aChunkX+ 8, aData.mOffsetY-1, aChunkZ+ 7, Blocks.end_stone, 0, 2);
		setBlock(aWorld, aChunkX+ 8, aData.mOffsetY-1, aChunkZ+ 8, Blocks.end_stone, 0, 2);
		return T;
	}
}
