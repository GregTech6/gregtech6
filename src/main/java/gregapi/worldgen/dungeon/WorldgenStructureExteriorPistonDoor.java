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

package gregapi.worldgen.dungeon;

import static gregapi.data.CS.*;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStructureExteriorPistonDoor extends WorldgenDungeonGT {
	public static boolean generate(World aWorld, Random aRandom, int aChunkX, int aChunkZ, DungeonChunkData aData) {
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			setSmoothBlock(aWorld, aChunkX+15, aData.mY+3, aChunkZ+ 6, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+15, aData.mY+3, aChunkZ+ 9, aData, aRandom);
			
			for (int tX = 11; tX <= 14; tX++) {
				setSmoothBlock(aWorld, aChunkX+tX, aData.mY+0, aChunkZ+ 6, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+tX, aData.mY+0, aChunkZ+ 7, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+tX, aData.mY+0, aChunkZ+ 8, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+tX, aData.mY+0, aChunkZ+ 9, aData, aRandom);
			}
			for (int tY = 1; tY <= 6; tY++) {
				setSmoothBlock(aWorld, aChunkX+14, aData.mY+tY, aChunkZ+ 4, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+13, aData.mY+tY, aChunkZ+ 4, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+12, aData.mY+tY, aChunkZ+ 4, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+11, aData.mY+tY, aChunkZ+ 5, aData, aRandom);
				if (tY >= 3) {
				setSmoothBlock(aWorld, aChunkX+11, aData.mY+tY, aChunkZ+ 6, aData, aRandom);
				if (tY >= 4) {
				setSmoothBlock(aWorld, aChunkX+11, aData.mY+tY, aChunkZ+ 7, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+11, aData.mY+tY, aChunkZ+ 8, aData, aRandom);
				}
				setSmoothBlock(aWorld, aChunkX+11, aData.mY+tY, aChunkZ+ 9, aData, aRandom);
				}
				setSmoothBlock(aWorld, aChunkX+11, aData.mY+tY, aChunkZ+10, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+12, aData.mY+tY, aChunkZ+11, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+13, aData.mY+tY, aChunkZ+11, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+14, aData.mY+tY, aChunkZ+11, aData, aRandom);
			}
			for (int tY = 1; tY <= 2; tY++) {
				setSmoothBlock(aWorld, aChunkX+14, aData.mY+tY, aChunkZ+ 5, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+14, aData.mY+tY, aChunkZ+ 6, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+14, aData.mY+tY, aChunkZ+ 9, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+14, aData.mY+tY, aChunkZ+10, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+12, aData.mY+tY, aChunkZ+ 5, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+12, aData.mY+tY, aChunkZ+ 6, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+12, aData.mY+tY, aChunkZ+ 9, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+12, aData.mY+tY, aChunkZ+10, aData, aRandom);
			}
			setSmoothBlock(aWorld, aChunkX+12, aData.mY+3, aChunkZ+ 7, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+12, aData.mY+3, aChunkZ+ 8, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+14, aData.mY+3, aChunkZ+ 7, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+14, aData.mY+3, aChunkZ+ 8, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+13, aData.mY+4, aChunkZ+ 7, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+13, aData.mY+4, aChunkZ+ 8, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+13, aData.mY+3, aChunkZ+ 5, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+13, aData.mY+3, aChunkZ+ 6, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+13, aData.mY+3, aChunkZ+ 7, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+13, aData.mY+3, aChunkZ+ 8, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+13, aData.mY+3, aChunkZ+ 9, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+13, aData.mY+3, aChunkZ+10, aData, aRandom);

			setLampBlock(aWorld, aChunkX+15, aData.mY+4, aChunkZ+ 7, aData, aRandom, +1);
			setLampBlock(aWorld, aChunkX+15, aData.mY+4, aChunkZ+ 8, aData, aRandom, +1);
			
			setBlock(aWorld, aChunkX+12, aData.mY+2, aChunkZ+ 7, Blocks.stone_button, 3, 2);
			setBlock(aWorld, aChunkX+12, aData.mY+2, aChunkZ+ 8, Blocks.stone_button, 4, 2);
			setBlock(aWorld, aChunkX+14, aData.mY+2, aChunkZ+ 7, Blocks.stone_button, 3, 2);
			setBlock(aWorld, aChunkX+14, aData.mY+2, aChunkZ+ 8, Blocks.stone_button, 4, 2);
			setBlock(aWorld, aChunkX+13, aData.mY+1, aChunkZ+ 5, Blocks.sticky_piston, 3, 2);
			setColored(aWorld, aChunkX+13, aData.mY+1, aChunkZ+ 7, aData, aRandom);
			setColored(aWorld, aChunkX+13, aData.mY+1, aChunkZ+ 8, aData, aRandom);
			setBlock(aWorld, aChunkX+13, aData.mY+1, aChunkZ+10, Blocks.sticky_piston, 2, 2);
			setBlock(aWorld, aChunkX+13, aData.mY+2, aChunkZ+ 5, Blocks.sticky_piston, 3, 2);
			setColored(aWorld, aChunkX+13, aData.mY+2, aChunkZ+ 7, aData, aRandom);
			setColored(aWorld, aChunkX+13, aData.mY+2, aChunkZ+ 8, aData, aRandom);
			setBlock(aWorld, aChunkX+13, aData.mY+2, aChunkZ+10, Blocks.sticky_piston, 2, 2);
			setBlock(aWorld, aChunkX+12, aData.mY+3, aChunkZ+ 6, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+12, aData.mY+3, aChunkZ+ 9, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+14, aData.mY+3, aChunkZ+ 6, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+14, aData.mY+3, aChunkZ+ 9, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+12, aData.mY+4, aChunkZ+ 7, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+12, aData.mY+4, aChunkZ+ 8, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mY+4, aChunkZ+ 5, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mY+4, aChunkZ+ 6, Blocks.redstone_torch, 4, 3);
			setBlock(aWorld, aChunkX+13, aData.mY+4, aChunkZ+ 9, Blocks.redstone_torch, 3, 3);
			setBlock(aWorld, aChunkX+13, aData.mY+4, aChunkZ+10, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+14, aData.mY+4, aChunkZ+ 7, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+14, aData.mY+4, aChunkZ+ 8, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mY+5, aChunkZ+ 7, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+13, aData.mY+5, aChunkZ+ 8, Blocks.redstone_wire, 0, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			setSmoothBlock(aWorld, aChunkX+ 0, aData.mY+3, aChunkZ+ 6, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 0, aData.mY+3, aChunkZ+ 9, aData, aRandom);
			for (int tX =  1; tX <=  4; tX++) {
				setSmoothBlock(aWorld, aChunkX+tX, aData.mY+0, aChunkZ+ 6, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+tX, aData.mY+0, aChunkZ+ 7, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+tX, aData.mY+0, aChunkZ+ 8, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+tX, aData.mY+0, aChunkZ+ 9, aData, aRandom);
			}
			for (int tY = 1; tY <= 6; tY++) {
				setSmoothBlock(aWorld, aChunkX+ 1, aData.mY+tY, aChunkZ+ 4, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 2, aData.mY+tY, aChunkZ+ 4, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 3, aData.mY+tY, aChunkZ+ 4, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 4, aData.mY+tY, aChunkZ+ 5, aData, aRandom);
				if (tY >= 3) {
				setSmoothBlock(aWorld, aChunkX+ 4, aData.mY+tY, aChunkZ+ 6, aData, aRandom);
				if (tY >= 4) {
				setSmoothBlock(aWorld, aChunkX+ 4, aData.mY+tY, aChunkZ+ 7, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 4, aData.mY+tY, aChunkZ+ 8, aData, aRandom);
				}
				setSmoothBlock(aWorld, aChunkX+ 4, aData.mY+tY, aChunkZ+ 9, aData, aRandom);
				}
				setSmoothBlock(aWorld, aChunkX+ 4, aData.mY+tY, aChunkZ+10, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 1, aData.mY+tY, aChunkZ+11, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 2, aData.mY+tY, aChunkZ+11, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 3, aData.mY+tY, aChunkZ+11, aData, aRandom);
			}
			for (int tY = 1; tY <= 2; tY++) {
				setSmoothBlock(aWorld, aChunkX+ 1, aData.mY+tY, aChunkZ+ 5, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 1, aData.mY+tY, aChunkZ+ 6, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 1, aData.mY+tY, aChunkZ+ 9, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 1, aData.mY+tY, aChunkZ+10, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 3, aData.mY+tY, aChunkZ+ 5, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 3, aData.mY+tY, aChunkZ+ 6, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 3, aData.mY+tY, aChunkZ+ 9, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 3, aData.mY+tY, aChunkZ+10, aData, aRandom);
			}
			setSmoothBlock(aWorld, aChunkX+ 3, aData.mY+3, aChunkZ+ 7, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 3, aData.mY+3, aChunkZ+ 8, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 1, aData.mY+3, aChunkZ+ 7, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 1, aData.mY+3, aChunkZ+ 8, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 2, aData.mY+4, aChunkZ+ 7, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 2, aData.mY+4, aChunkZ+ 8, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 2, aData.mY+3, aChunkZ+ 5, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 2, aData.mY+3, aChunkZ+ 6, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 2, aData.mY+3, aChunkZ+ 7, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 2, aData.mY+3, aChunkZ+ 8, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 2, aData.mY+3, aChunkZ+ 9, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 2, aData.mY+3, aChunkZ+10, aData, aRandom);

			setLampBlock(aWorld, aChunkX+ 0, aData.mY+4, aChunkZ+ 7, aData, aRandom, +1);
			setLampBlock(aWorld, aChunkX+ 0, aData.mY+4, aChunkZ+ 8, aData, aRandom, +1);
			
			setBlock(aWorld, aChunkX+ 1, aData.mY+2, aChunkZ+ 7, Blocks.stone_button, 3, 2);
			setBlock(aWorld, aChunkX+ 1, aData.mY+2, aChunkZ+ 8, Blocks.stone_button, 4, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mY+2, aChunkZ+ 7, Blocks.stone_button, 3, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mY+2, aChunkZ+ 8, Blocks.stone_button, 4, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mY+1, aChunkZ+ 5, Blocks.sticky_piston, 3, 2);
			setColored(aWorld, aChunkX+ 2, aData.mY+1, aChunkZ+ 7, aData, aRandom);
			setColored(aWorld, aChunkX+ 2, aData.mY+1, aChunkZ+ 8, aData, aRandom);
			setBlock(aWorld, aChunkX+ 2, aData.mY+1, aChunkZ+10, Blocks.sticky_piston, 2, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mY+2, aChunkZ+ 5, Blocks.sticky_piston, 3, 2);
			setColored(aWorld, aChunkX+ 2, aData.mY+2, aChunkZ+ 7, aData, aRandom);
			setColored(aWorld, aChunkX+ 2, aData.mY+2, aChunkZ+ 8, aData, aRandom);
			setBlock(aWorld, aChunkX+ 2, aData.mY+2, aChunkZ+10, Blocks.sticky_piston, 2, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mY+3, aChunkZ+ 6, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mY+3, aChunkZ+ 9, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 1, aData.mY+3, aChunkZ+ 6, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 1, aData.mY+3, aChunkZ+ 9, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mY+4, aChunkZ+ 7, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 3, aData.mY+4, aChunkZ+ 8, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mY+4, aChunkZ+ 5, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mY+4, aChunkZ+ 6, Blocks.redstone_torch, 4, 3);
			setBlock(aWorld, aChunkX+ 2, aData.mY+4, aChunkZ+ 9, Blocks.redstone_torch, 3, 3);
			setBlock(aWorld, aChunkX+ 2, aData.mY+4, aChunkZ+10, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 1, aData.mY+4, aChunkZ+ 7, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 1, aData.mY+4, aChunkZ+ 8, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mY+5, aChunkZ+ 7, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 2, aData.mY+5, aChunkZ+ 8, Blocks.redstone_wire, 0, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			setSmoothBlock(aWorld, aChunkX+ 6, aData.mY+3, aChunkZ+15, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 9, aData.mY+3, aChunkZ+15, aData, aRandom);
			for (int tZ = 11; tZ <= 14; tZ++) {
				setSmoothBlock(aWorld, aChunkX+ 6, aData.mY+0, aChunkZ+tZ, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 7, aData.mY+0, aChunkZ+tZ, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 8, aData.mY+0, aChunkZ+tZ, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 9, aData.mY+0, aChunkZ+tZ, aData, aRandom);
			}
			for (int tY = 1; tY <= 6; tY++) {
				setSmoothBlock(aWorld, aChunkX+ 4, aData.mY+tY, aChunkZ+14, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 4, aData.mY+tY, aChunkZ+13, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 4, aData.mY+tY, aChunkZ+12, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 5, aData.mY+tY, aChunkZ+11, aData, aRandom);
				if (tY >= 3) {                                  
				setSmoothBlock(aWorld, aChunkX+ 6, aData.mY+tY, aChunkZ+11, aData, aRandom);
				if (tY >= 4) {                                  
				setSmoothBlock(aWorld, aChunkX+ 7, aData.mY+tY, aChunkZ+11, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 8, aData.mY+tY, aChunkZ+11, aData, aRandom);
				}                                                
				setSmoothBlock(aWorld, aChunkX+ 9, aData.mY+tY, aChunkZ+11, aData, aRandom);
				}                                                
				setSmoothBlock(aWorld, aChunkX+10, aData.mY+tY, aChunkZ+11, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+11, aData.mY+tY, aChunkZ+12, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+11, aData.mY+tY, aChunkZ+13, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+11, aData.mY+tY, aChunkZ+14, aData, aRandom);
			}
			for (int tY = 1; tY <= 2; tY++) {
				setSmoothBlock(aWorld, aChunkX+ 5, aData.mY+tY, aChunkZ+14, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 6, aData.mY+tY, aChunkZ+14, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 9, aData.mY+tY, aChunkZ+14, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+10, aData.mY+tY, aChunkZ+14, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 5, aData.mY+tY, aChunkZ+12, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 6, aData.mY+tY, aChunkZ+12, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 9, aData.mY+tY, aChunkZ+12, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+10, aData.mY+tY, aChunkZ+12, aData, aRandom);
			}
			setSmoothBlock(aWorld, aChunkX+ 7, aData.mY+3, aChunkZ+12, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 8, aData.mY+3, aChunkZ+12, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 7, aData.mY+3, aChunkZ+14, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 8, aData.mY+3, aChunkZ+14, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 7, aData.mY+4, aChunkZ+13, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 8, aData.mY+4, aChunkZ+13, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 5, aData.mY+3, aChunkZ+13, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 6, aData.mY+3, aChunkZ+13, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 7, aData.mY+3, aChunkZ+13, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 8, aData.mY+3, aChunkZ+13, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 9, aData.mY+3, aChunkZ+13, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+10, aData.mY+3, aChunkZ+13, aData, aRandom);
			
			setLampBlock(aWorld, aChunkX+ 7, aData.mY+4, aChunkZ+15, aData, aRandom, +1);
			setLampBlock(aWorld, aChunkX+ 8, aData.mY+4, aChunkZ+15, aData, aRandom, +1);
			
			setBlock(aWorld, aChunkX+ 7, aData.mY+2, aChunkZ+12, Blocks.stone_button, 1, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mY+2, aChunkZ+12, Blocks.stone_button, 2, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mY+2, aChunkZ+14, Blocks.stone_button, 1, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mY+2, aChunkZ+14, Blocks.stone_button, 2, 2);
			setBlock(aWorld, aChunkX+ 5, aData.mY+1, aChunkZ+13, Blocks.sticky_piston, 5, 2);
			setColored(aWorld, aChunkX+ 7, aData.mY+1, aChunkZ+13, aData, aRandom);
			setColored(aWorld, aChunkX+ 8, aData.mY+1, aChunkZ+13, aData, aRandom);
			setBlock(aWorld, aChunkX+10, aData.mY+1, aChunkZ+13, Blocks.sticky_piston, 4, 2);
			setBlock(aWorld, aChunkX+ 5, aData.mY+2, aChunkZ+13, Blocks.sticky_piston, 5, 2);
			setColored(aWorld, aChunkX+ 7, aData.mY+2, aChunkZ+13, aData, aRandom);
			setColored(aWorld, aChunkX+ 8, aData.mY+2, aChunkZ+13, aData, aRandom);
			setBlock(aWorld, aChunkX+10, aData.mY+2, aChunkZ+13, Blocks.sticky_piston, 4, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mY+3, aChunkZ+12, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mY+3, aChunkZ+12, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mY+3, aChunkZ+14, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mY+3, aChunkZ+14, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mY+4, aChunkZ+12, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mY+4, aChunkZ+12, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 5, aData.mY+4, aChunkZ+13, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mY+4, aChunkZ+13, Blocks.redstone_torch, 2, 3);
			setBlock(aWorld, aChunkX+ 9, aData.mY+4, aChunkZ+13, Blocks.redstone_torch, 1, 3);
			setBlock(aWorld, aChunkX+10, aData.mY+4, aChunkZ+13, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mY+4, aChunkZ+14, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mY+4, aChunkZ+14, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mY+5, aChunkZ+13, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mY+5, aChunkZ+13, Blocks.redstone_wire, 0, 2);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			setSmoothBlock(aWorld, aChunkX+ 6, aData.mY+3, aChunkZ+ 0, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 9, aData.mY+3, aChunkZ+ 0, aData, aRandom);
			for (int tZ =  1; tZ <=  4; tZ++) {
				setSmoothBlock(aWorld, aChunkX+ 6, aData.mY+0, aChunkZ+tZ, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 7, aData.mY+0, aChunkZ+tZ, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 8, aData.mY+0, aChunkZ+tZ, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 9, aData.mY+0, aChunkZ+tZ, aData, aRandom);
			}
			for (int tY = 1; tY <= 6; tY++) {
				setSmoothBlock(aWorld, aChunkX+ 4, aData.mY+tY, aChunkZ+ 1, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 4, aData.mY+tY, aChunkZ+ 2, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 4, aData.mY+tY, aChunkZ+ 3, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 5, aData.mY+tY, aChunkZ+ 4, aData, aRandom);
				if (tY >= 3) {                                  
				setSmoothBlock(aWorld, aChunkX+ 6, aData.mY+tY, aChunkZ+ 4, aData, aRandom);
				if (tY >= 4) {                                  
				setSmoothBlock(aWorld, aChunkX+ 7, aData.mY+tY, aChunkZ+ 4, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 8, aData.mY+tY, aChunkZ+ 4, aData, aRandom);
				}                                                
				setSmoothBlock(aWorld, aChunkX+ 9, aData.mY+tY, aChunkZ+ 4, aData, aRandom);
				}                                                
				setSmoothBlock(aWorld, aChunkX+10, aData.mY+tY, aChunkZ+ 4, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+11, aData.mY+tY, aChunkZ+ 1, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+11, aData.mY+tY, aChunkZ+ 2, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+11, aData.mY+tY, aChunkZ+ 3, aData, aRandom);
			}
			for (int tY = 1; tY <= 2; tY++) {
				setSmoothBlock(aWorld, aChunkX+ 5, aData.mY+tY, aChunkZ+ 1, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 6, aData.mY+tY, aChunkZ+ 1, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 9, aData.mY+tY, aChunkZ+ 1, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+10, aData.mY+tY, aChunkZ+ 1, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 5, aData.mY+tY, aChunkZ+ 3, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 6, aData.mY+tY, aChunkZ+ 3, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+ 9, aData.mY+tY, aChunkZ+ 3, aData, aRandom);
				setSmoothBlock(aWorld, aChunkX+10, aData.mY+tY, aChunkZ+ 3, aData, aRandom);
			}
			setSmoothBlock(aWorld, aChunkX+ 7, aData.mY+3, aChunkZ+ 3, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 8, aData.mY+3, aChunkZ+ 3, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 7, aData.mY+3, aChunkZ+ 1, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 8, aData.mY+3, aChunkZ+ 1, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 7, aData.mY+4, aChunkZ+ 2, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 8, aData.mY+4, aChunkZ+ 2, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 5, aData.mY+3, aChunkZ+ 2, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 6, aData.mY+3, aChunkZ+ 2, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 7, aData.mY+3, aChunkZ+ 2, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 8, aData.mY+3, aChunkZ+ 2, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+ 9, aData.mY+3, aChunkZ+ 2, aData, aRandom);
			setSmoothBlock(aWorld, aChunkX+10, aData.mY+3, aChunkZ+ 2, aData, aRandom);
			
			setLampBlock(aWorld, aChunkX+ 7, aData.mY+4, aChunkZ+ 0, aData, aRandom, +1);
			setLampBlock(aWorld, aChunkX+ 8, aData.mY+4, aChunkZ+ 0, aData, aRandom, +1);
			
			setBlock(aWorld, aChunkX+ 7, aData.mY+2, aChunkZ+ 1, Blocks.stone_button, 1, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mY+2, aChunkZ+ 1, Blocks.stone_button, 2, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mY+2, aChunkZ+ 3, Blocks.stone_button, 1, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mY+2, aChunkZ+ 3, Blocks.stone_button, 2, 2);
			setBlock(aWorld, aChunkX+ 5, aData.mY+1, aChunkZ+ 2, Blocks.sticky_piston, 5, 2);
			setColored(aWorld, aChunkX+ 7, aData.mY+1, aChunkZ+ 2, aData, aRandom);
			setColored(aWorld, aChunkX+ 8, aData.mY+1, aChunkZ+ 2, aData, aRandom);
			setBlock(aWorld, aChunkX+10, aData.mY+1, aChunkZ+ 2, Blocks.sticky_piston, 4, 2);
			setBlock(aWorld, aChunkX+ 5, aData.mY+2, aChunkZ+ 2, Blocks.sticky_piston, 5, 2);
			setColored(aWorld, aChunkX+ 7, aData.mY+2, aChunkZ+ 2, aData, aRandom);
			setColored(aWorld, aChunkX+ 8, aData.mY+2, aChunkZ+ 2, aData, aRandom);
			setBlock(aWorld, aChunkX+10, aData.mY+2, aChunkZ+ 2, Blocks.sticky_piston, 4, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mY+3, aChunkZ+ 3, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mY+3, aChunkZ+ 3, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mY+3, aChunkZ+ 1, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 9, aData.mY+3, aChunkZ+ 1, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mY+4, aChunkZ+ 3, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mY+4, aChunkZ+ 3, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 5, aData.mY+4, aChunkZ+ 2, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 6, aData.mY+4, aChunkZ+ 2, Blocks.redstone_torch, 2, 3);
			setBlock(aWorld, aChunkX+ 9, aData.mY+4, aChunkZ+ 2, Blocks.redstone_torch, 1, 3);
			setBlock(aWorld, aChunkX+10, aData.mY+4, aChunkZ+ 2, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mY+4, aChunkZ+ 1, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mY+4, aChunkZ+ 1, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 7, aData.mY+5, aChunkZ+ 2, Blocks.redstone_wire, 0, 2);
			setBlock(aWorld, aChunkX+ 8, aData.mY+5, aChunkZ+ 2, Blocks.redstone_wire, 0, 2);
		}
		return T;
	}
}
