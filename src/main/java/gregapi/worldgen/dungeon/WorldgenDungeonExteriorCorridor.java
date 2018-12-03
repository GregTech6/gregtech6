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

import gregapi.data.FL;
import gregapi.fluid.FluidTankGT;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenDungeonExteriorCorridor {
	public static boolean generate(World aWorld, Random aRandom, int aDatamX, int aDatamZ, DungeonChunkData aData) {
		if ((aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0 || aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) && (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0 || aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0)) {
			WorldgenStructureExteriorPillar.generate(aWorld, aRandom, aData.mX, aData.mZ, aData);
		}
		
		if (aData.mConnectionCount == 4) {
			for (int tX =  2; tX <= 13; tX++) for (int tZ =  2; tZ <= 13; tZ++) for (int tY = 0; tY <= 6; tY++) {
				if (tX == 2 || tX == 13 || tZ == 2 || tZ == 13 || tY == 0 || tY == 6) {
					if ((tX == 4 || tX == 7 || tX == 8 || tX == 11) && (tZ == 4 || tZ == 7 || tZ == 8 || tZ == 11)) {
						if (tY == 0) {
							aData.setChiseledStone(tX, tY, tZ);
						} else if (tY == 6) {
							aData.setLampBlock(tX, tY, tZ, +1);
						} else {
							aData.setRandomBricks(tX, tY, tZ);
						}
					} else {
						if (tY == 0) {
							aData.setStoneTiles(tX, tY, tZ);
						} else if (tY == 6) {
							aData.setSmallTiles(tX, tY, tZ);
						} else {
							aData.setRandomBricks(tX, tY, tZ);
						}
					}
				} else {
					aData.setAirBlock(tX, tY, tZ);
				}
			}
			
			for (int tX = 13; tX <= 15; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.setStoneTiles(tX, tY, tZ);
				} else if (tY == 4) {
					aData.setSmallTiles(tX, tY, tZ);
				} else if (tZ == 5 || tZ == 10) {
					aData.setRandomBricks(tX, tY, tZ);
				} else {
					aData.setAirBlock(tX, tY, tZ);
				}
			}
			for (int tX =  0; tX <=  2; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.setStoneTiles(tX, tY, tZ);
				} else if (tY == 4) {
					aData.setSmallTiles(tX, tY, tZ);
				} else if (tZ == 5 || tZ == 10) {
					aData.setRandomBricks(tX, tY, tZ);
				} else {
					aData.setAirBlock(tX, tY, tZ);
				}
			}
			for (int tX =  5; tX <= 10; tX++) for (int tZ = 13; tZ <= 15; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.setStoneTiles(tX, tY, tZ);
				} else if (tY == 4) {
					aData.setSmallTiles(tX, tY, tZ);
				} else if (tX == 5 || tX == 10) {
					aData.setRandomBricks(tX, tY, tZ);
				} else {
					aData.setAirBlock(tX, tY, tZ);
				}
			}
			for (int tX =  5; tX <= 10; tX++) for (int tZ =  0; tZ <=  2; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.setStoneTiles(tX, tY, tZ);
				} else if (tY == 4) {
					aData.setSmallTiles(tX, tY, tZ);
				} else if (tX == 5 || tX == 10) {
					aData.setRandomBricks(tX, tY, tZ);
				} else {
					aData.setAirBlock(tX, tY, tZ);
				}
			}
			return T;
		}
		
		for (int tX =  5; tX <= 10; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
			if (tY == 0) {
				aData.setStoneTiles(tX, tY, tZ);
			} else if (tX == 5 || tX == 10 || tZ == 5 || tZ == 10) {
				aData.setRandomBricks(tX, tY, tZ);
			} else {
				if (tY == 4) {
					if (WD.liquid(aWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
						aData.setGlowGlass(tX, tY, tZ);
					} else {
						aData.setRandomBricks(tX, tY, tZ);
					}
				} else {
					aData.setAirBlock(tX, tY, tZ);
				}
			}
		}
		
		if (WD.liquid(aWorld, aData.mX+ 7, aData.mY+4, aData.mZ+ 7) || WD.liquid(aWorld, aData.mX+ 7, aData.mY+4, aData.mZ+ 8)
		 || WD.liquid(aWorld, aData.mX+ 8, aData.mY+4, aData.mZ+ 7) || WD.liquid(aWorld, aData.mX+ 8, aData.mY+4, aData.mZ+ 8)
		 || aWorld.canBlockSeeTheSky(aData.mX+ 7, aData.mY+4, aData.mZ+ 7) || aWorld.canBlockSeeTheSky(aData.mX+ 7, aData.mY+4, aData.mZ+ 8)
		 || aWorld.canBlockSeeTheSky(aData.mX+ 8, aData.mY+4, aData.mZ+ 7) || aWorld.canBlockSeeTheSky(aData.mX+ 8, aData.mY+4, aData.mZ+ 8)) {
			aData.setGlowGlass        (7, 4, 7);
			aData.setGlowGlass        (7, 4, 8);
			aData.setGlowGlass        (8, 4, 7);
			aData.setGlowGlass        (8, 4, 8);
		} else {
			aData.setRedstoneBrick    (7, 4, 7);
			aData.setLampBlock        (7, 4, 8, 0);
			aData.setLampBlock        (8, 4, 7, 0);
			aData.setRedstoneBrick    (8, 4, 8);
		}
		
		FluidStack[] tDrinks = new FluidStack[] {NF, NF, NF, NF, NF, FL.Purple_Drink.make(250), FL.Purple_Drink.make(250), FL.Purple_Drink.make(250), FL.Vodka.make(250), FL.Mead.make(250), FL.Whiskey_GlenMcKenner.make(250), FL.Wine_Grape_Purple.make(250)};
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			for (int tX = 10; tX <= 15; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.setStoneTiles(tX, tY, tZ);
				} else if (tZ == 5 || tZ == 10) {
					aData.setRandomBricks(tX, tY, tZ);
				} else {
					if (tY == 4) {
						if (WD.liquid(aWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
							aData.setGlowGlass(tX, tY, tZ);
						} else {
							aData.setRandomBricks(tX, tY, tZ);
						}
					} else {
						aData.setAirBlock(tX, tY, tZ);
					}
				}
			}
			aData.setRedstoneBrick    (13, 4, 6);
			aData.setLampBlock        (13, 4, 7, 0);
			aData.setLampBlock        (13, 4, 8, 0);
			aData.setRedstoneBrick    (13, 4, 9);
		} else if (aData.mConnectionCount == 3 && aRandom.nextInt(2) == 0) {
			for (int tY =  0; tY <= 4; tY++) for (int tZ =  5; tZ <= 10; tZ++) aData.setSmoothBlock(11, tY, tZ);
			for (int tY =  1; tY <= 3; tY++) for (int tZ =  6; tZ <=  9; tZ++) aData.setAirBlock   (10, tY, tZ);
			
			aData.setSmoothBlock                      (10, 1, 6);
			aData.setBlock                            (10, 1, 7, Blocks.crafting_table, 0, 2);
			aData.setMTE                              (10, 1, 8, SIDE_UNKNOWN, (short)((aRandom.nextInt(2)==0?508:8)+aRandom.nextInt(3)), UT.NBT.make(null, "gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_X_NEG), T, T);
			aData.setSmoothBlock                      (10, 1, 9);
			
			aData.setCoins                            (10, 2, 6);
			aData.setMTE                              (10, 2, 7, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			
			aData.setCoins                            (10, 2, 9);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			for (int tX =  0; tX <=  5; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.setStoneTiles(tX, tY, tZ);
				} else if (tZ == 5 || tZ == 10) {
					aData.setRandomBricks(tX, tY, tZ);
				} else {
					if (tY == 4) {
						if (WD.liquid(aWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
							aData.setGlowGlass(tX, tY, tZ);
						} else {
							aData.setRandomBricks(tX, tY, tZ);
						}
					} else {
						aData.setAirBlock(tX, tY, tZ);
					}
				}
			}
			aData.setRedstoneBrick    (2, 4, 6);
			aData.setLampBlock        (2, 4, 7, 0);
			aData.setLampBlock        (2, 4, 8, 0);
			aData.setRedstoneBrick    (2, 4, 9);
		} else if (aData.mConnectionCount == 3 && aRandom.nextInt(2) == 0) {
			for (int tY =  0; tY <= 4; tY++) for (int tZ =  5; tZ <= 10; tZ++) aData.setSmoothBlock(4, tY, tZ);
			for (int tY =  1; tY <= 3; tY++) for (int tZ =  6; tZ <=  9; tZ++) aData.setAirBlock   (5, tY, tZ);
			
			aData.setSmoothBlock                      (5, 1, 6);
			aData.setMTE                              (5, 1, 7, SIDE_UNKNOWN, (short)((aRandom.nextInt(2)==0?508:8)+aRandom.nextInt(3)), UT.NBT.make(null, "gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_X_POS), T, T);
			aData.setBlock                            (5, 1, 8, Blocks.crafting_table, 0, 2);
			aData.setSmoothBlock                      (5, 1, 9);
			
			aData.setCoins                            (5, 2, 6);
			
			aData.setMTE                              (5, 2, 8, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			aData.setCoins                            (5, 2, 9);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			for (int tX =  5; tX <= 10; tX++) for (int tZ = 10; tZ <= 15; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.setStoneTiles(tX, tY, tZ);
				} else if (tX == 5 || tX == 10) {
					aData.setRandomBricks(tX, tY, tZ);
				} else {
					if (tY == 4) {
						if (WD.liquid(aWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
							aData.setGlowGlass(tX, tY, tZ);
						} else {
							aData.setRandomBricks(tX, tY, tZ);
						}
					} else {
						aData.setAirBlock(tX, tY, tZ);
					}
				}
			}
			aData.setRedstoneBrick    (6, 4, 13);
			aData.setLampBlock        (7, 4, 13, 0);
			aData.setLampBlock        (8, 4, 13, 0);
			aData.setRedstoneBrick    (9, 4, 13);
		} else if (aData.mConnectionCount == 3 && aRandom.nextInt(2) == 0) {
			for (int tY =  0; tY <= 4; tY++) for (int tX =  5; tX <= 10; tX++) aData.setSmoothBlock(tX, tY, 11);
			for (int tY =  1; tY <= 3; tY++) for (int tX =  6; tX <=  9; tX++) aData.setAirBlock   (tX, tY, 10);
			
			aData.setSmoothBlock                      (6, 1, 10);
			aData.setBlock                            (7, 1, 10, Blocks.crafting_table, 0, 2);
			aData.setMTE                              (8, 1, 10, SIDE_UNKNOWN, (short)((aRandom.nextInt(2)==0?508:8)+aRandom.nextInt(3)), UT.NBT.make(null, "gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_Z_NEG), T, T);
			aData.setSmoothBlock                      (9, 1, 10);
			
			aData.setCoins                            (6, 2, 10);
			aData.setMTE                              (7, 2, 10, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			
			aData.setCoins                            (9, 2, 10);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			for (int tX =  5; tX <= 10; tX++) for (int tZ =  0; tZ <=  5; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.setStoneTiles(tX, tY, tZ);
				} else if (tX == 5 || tX == 10) {
					aData.setRandomBricks(tX, tY, tZ);
				} else {
					if (tY == 4) {
						if (WD.liquid(aWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
							aData.setGlowGlass(tX, tY, tZ);
						} else {
							aData.setRandomBricks(tX, tY, tZ);
						}
					} else {
						aData.setAirBlock(tX, tY, tZ);
					}
				}
			}
			aData.setRedstoneBrick    (6, 4, 2);
			aData.setLampBlock        (7, 4, 2, 0);
			aData.setLampBlock        (8, 4, 2, 0);
			aData.setRedstoneBrick    (9, 4, 2);
		} else if (aData.mConnectionCount == 3 && aRandom.nextInt(2) == 0) {
			for (int tY =  0; tY <= 4; tY++) for (int tX =  5; tX <= 10; tX++) aData.setSmoothBlock(tX, tY, 4);
			for (int tY =  1; tY <= 3; tY++) for (int tX =  6; tX <=  9; tX++) aData.setAirBlock   (tX, tY, 5);
			
			aData.setSmoothBlock                      (6, 1, 5);
			aData.setMTE                              (7, 1, 5, SIDE_UNKNOWN, (short)((aRandom.nextInt(2)==0?508:8)+aRandom.nextInt(3)), UT.NBT.make(null, "gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_Z_POS), T, T);
			aData.setBlock                            (8, 1, 5, Blocks.crafting_table, 0, 2);
			aData.setSmoothBlock                      (9, 1, 5);
			
			aData.setCoins                            (6, 2, 5);
			
			aData.setMTE                              (8, 2, 5, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			aData.setCoins                            (9, 2, 5);
		}
		return T;
	}
}
