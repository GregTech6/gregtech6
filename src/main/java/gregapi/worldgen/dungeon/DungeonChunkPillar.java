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

import gregapi.data.CS.BlocksGT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;

/**
 * @author Gregorius Techneticies
 * 
 * This is the Pillar Implementation
 * It is a good Idea to just extend this Class and do a super Call in order to have a Pillar generated before placing your Exterior Design.
 */
public class DungeonChunkPillar implements IDungeonChunk {
	@Override
	public boolean generate(DungeonData aData) {
		boolean temp = T;
		for (int tX = 6; tX <= 9 && temp; tX++) for (int tZ = 6; tZ <= 9 && temp; tZ++) if (WD.opq(aData.mWorld, aData.mX+tX, aData.mY-1, aData.mZ+tZ, T, F)) temp = F;
		
		if (temp) for (int tX =  5; tX <= 10; tX++) for (int tZ =  5; tZ <= 10; tZ++) {
			aData.smooth(tX, -1, tZ);
			aData.bricks(tX, -2, tZ);
		}
		
		for (int tY = -3; aData.mY+tY >= 2 && temp; tY--) {
			temp = F;
			for (int tX = 6; tX <= 9 && !temp; tX++) for (int tZ = 6; tZ <= 9 && !temp; tZ++) {
				Block tBlock = aData.mWorld.getBlock(aData.mX+tX, aData.mY+tY, aData.mZ+tZ);
				if (tBlock instanceof BlockFalling || !tBlock.isOpaqueCube() || tBlock == BlocksGT.Sands || tBlock == BlocksGT.Diggables) temp = T;
			}
			if (temp) {
				for (int tX =  6; tX <=  9; tX++) for (int tZ =  6; tZ <=  9; tZ++) {
					aData.bricks(tX, tY  , tZ);
				}
			} else {
				for (int tX =  5; tX <= 10; tX++) for (int tZ =  5; tZ <= 10; tZ++) {
					aData.smooth(tX, tY+1, tZ);
					aData.bricks(tX, tY  , tZ);
					aData.bricks(tX, tY-1, tZ);
					if (tY > 2 || aData.mWorld.getBlock(aData.mX+tX, 0, aData.mZ+tZ).getBlockHardness(aData.mWorld, aData.mX+tX, 0, aData.mZ+tZ) >= 0)
					aData.smooth(tX, tY-2, tZ);
				}
			}
		}
		
		return T;
	}
}
