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

import gregapi.data.FL;
import gregapi.fluid.FluidTankGT;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkCorridor extends DungeonChunkPillar {
	@Override
	public boolean generate(DungeonData aData) {
		if ((aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0 || aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) && (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0 || aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0)) {
			try {super.generate(aData);} catch(Throwable e) {e.printStackTrace(ERR);} // The Pillar is not important enough to fail the entire Corridor.
		}
		
		if (aData.mConnectionCount == 4) {
			for (int tX =  2; tX <= 13; tX++) for (int tZ =  2; tZ <= 13; tZ++) for (int tY = 0; tY <= 6; tY++) {
				if (tX == 2 || tX == 13 || tZ == 2 || tZ == 13 || tY == 0 || tY == 6) {
					if ((tX == 4 || tX == 7 || tX == 8 || tX == 11) && (tZ == 4 || tZ == 7 || tZ == 8 || tZ == 11)) {
						if (tY == 0) {
							aData.chiseled(tX, tY, tZ);
						} else if (tY == 6) {
							aData.lamp(tX, tY, tZ, +1);
						} else {
							aData.bricks(tX, tY, tZ);
						}
					} else {
						if (tY == 0) {
							aData.tiles(tX, tY, tZ);
						} else if (tY == 6) {
							aData.smalltiles(tX, tY, tZ);
						} else {
							aData.bricks(tX, tY, tZ);
						}
					}
				} else {
					aData.air(tX, tY, tZ);
				}
			}

			for (int tX = 13; tX <= 15; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.tiles(tX, tY, tZ);
				} else if (tY == 4) {
					aData.smalltiles(tX, tY, tZ);
				} else if (tZ == 5 || tZ == 10) {
					aData.bricks(tX, tY, tZ);
				} else {
					aData.air(tX, tY, tZ);
				}
			}
			for (int tX =  0; tX <=  2; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.tiles(tX, tY, tZ);
				} else if (tY == 4) {
					aData.smalltiles(tX, tY, tZ);
				} else if (tZ == 5 || tZ == 10) {
					aData.bricks(tX, tY, tZ);
				} else {
					aData.air(tX, tY, tZ);
				}
			}
			for (int tX =  5; tX <= 10; tX++) for (int tZ = 13; tZ <= 15; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.tiles(tX, tY, tZ);
				} else if (tY == 4) {
					aData.smalltiles(tX, tY, tZ);
				} else if (tX == 5 || tX == 10) {
					aData.bricks(tX, tY, tZ);
				} else {
					aData.air(tX, tY, tZ);
				}
			}
			for (int tX =  5; tX <= 10; tX++) for (int tZ =  0; tZ <=  2; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.tiles(tX, tY, tZ);
				} else if (tY == 4) {
					aData.smalltiles(tX, tY, tZ);
				} else if (tX == 5 || tX == 10) {
					aData.bricks(tX, tY, tZ);
				} else {
					aData.air(tX, tY, tZ);
				}
			}
			return T;
		}

		for (int tX =  5; tX <= 10; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
			if (tY == 0) {
				aData.tiles(tX, tY, tZ);
			} else if (tX == 5 || tX == 10 || tZ == 5 || tZ == 10) {
				aData.bricks(tX, tY, tZ);
			} else {
				if (tY == 4) {
					if (WD.liquid(aData.mWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aData.mWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
						aData.glassglow(tX, tY, tZ);
					} else {
						aData.bricks(tX, tY, tZ);
					}
				} else {
					aData.air(tX, tY, tZ);
				}
			}
		}

		if (WD.liquid(aData.mWorld, aData.mX+ 7, aData.mY+4, aData.mZ+ 7) || WD.liquid(aData.mWorld, aData.mX+ 7, aData.mY+4, aData.mZ+ 8)
		 || WD.liquid(aData.mWorld, aData.mX+ 8, aData.mY+4, aData.mZ+ 7) || WD.liquid(aData.mWorld, aData.mX+ 8, aData.mY+4, aData.mZ+ 8)
		 || aData.mWorld.canBlockSeeTheSky(aData.mX+ 7, aData.mY+4, aData.mZ+ 7) || aData.mWorld.canBlockSeeTheSky(aData.mX+ 7, aData.mY+4, aData.mZ+ 8)
		 || aData.mWorld.canBlockSeeTheSky(aData.mX+ 8, aData.mY+4, aData.mZ+ 7) || aData.mWorld.canBlockSeeTheSky(aData.mX+ 8, aData.mY+4, aData.mZ+ 8)) {
			aData.glassglow(7, 4, 7);
			aData.glassglow(7, 4, 8);
			aData.glassglow(8, 4, 7);
			aData.glassglow(8, 4, 8);
		} else {
			aData.redstoned(7, 4, 7);
			aData.lamp     (7, 4, 8, 0);
			aData.lamp     (8, 4, 7, 0);
			aData.redstoned(8, 4, 8);
		}

		FluidStack[] tDrinks = FL.array(NF, NF, NF, NF, NF, FL.Purple_Drink.make(250), FL.Purple_Drink.make(250), FL.Purple_Drink.make(250), FL.Vodka.make(250), FL.Mead.make(250), FL.Whiskey_GlenMcKenner.make(250), FL.Wine_Grape_Purple.make(250));

		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			for (int tX = 10; tX <= 15; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.tiles(tX, tY, tZ);
				} else if (tZ == 5 || tZ == 10) {
					aData.bricks(tX, tY, tZ);
				} else {
					if (tY == 4) {
						if (WD.liquid(aData.mWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aData.mWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
							aData.glassglow(tX, tY, tZ);
						} else {
							aData.bricks(tX, tY, tZ);
						}
					} else {
						aData.air(tX, tY, tZ);
					}
				}
			}
			aData.redstoned(13, 4, 6);
			aData.lamp     (13, 4, 7, 0);
			aData.lamp     (13, 4, 8, 0);
			aData.redstoned(13, 4, 9);
		} else if (aData.mConnectionCount == 3 && aData.next1in2()) {
			for (int tY =  0; tY <= 4; tY++) for (int tZ =  5; tZ <= 10; tZ++) aData.smooth(11, tY, tZ);
			for (int tY =  1; tY <= 3; tY++) for (int tZ =  6; tZ <=  9; tZ++) aData.air   (10, tY, tZ);

			aData.smooth   (10, 1, 6);
			aData.set      (10, 1, 7, Blocks.crafting_table, 0, 2);
			aData.set      (10, 1, 8, SIDE_UNKNOWN, (short)((aData.next1in2()?508:8)+aData.next(3)), UT.NBT.make("gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_X_NEG), T, T);
			aData.smooth   (10, 1, 9);

			aData.coins    (10, 2, 6);
			aData.set      (10, 2, 7, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);

			aData.coins    (10, 2, 9);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			for (int tX =  0; tX <=  5; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.tiles(tX, tY, tZ);
				} else if (tZ == 5 || tZ == 10) {
					aData.bricks(tX, tY, tZ);
				} else {
					if (tY == 4) {
						if (WD.liquid(aData.mWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aData.mWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
							aData.glassglow(tX, tY, tZ);
						} else {
							aData.bricks(tX, tY, tZ);
						}
					} else {
						aData.air(tX, tY, tZ);
					}
				}
			}
			aData.redstoned(2, 4, 6);
			aData.lamp     (2, 4, 7, 0);
			aData.lamp     (2, 4, 8, 0);
			aData.redstoned(2, 4, 9);
		} else if (aData.mConnectionCount == 3 && aData.next1in2()) {
			for (int tY =  0; tY <= 4; tY++) for (int tZ =  5; tZ <= 10; tZ++) aData.smooth(4, tY, tZ);
			for (int tY =  1; tY <= 3; tY++) for (int tZ =  6; tZ <=  9; tZ++) aData.air   (5, tY, tZ);

			aData.smooth   (5, 1, 6);
			aData.set      (5, 1, 7, SIDE_UNKNOWN, (short)((aData.next1in2()?508:8)+aData.next(3)), UT.NBT.make("gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_X_POS), T, T);
			aData.set      (5, 1, 8, Blocks.crafting_table, 0, 2);
			aData.smooth   (5, 1, 9);

			aData.coins    (5, 2, 6);

			aData.set      (5, 2, 8, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			aData.coins    (5, 2, 9);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			for (int tX =  5; tX <= 10; tX++) for (int tZ = 10; tZ <= 15; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.tiles(tX, tY, tZ);
				} else if (tX == 5 || tX == 10) {
					aData.bricks(tX, tY, tZ);
				} else {
					if (tY == 4) {
						if (WD.liquid(aData.mWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aData.mWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
							aData.glassglow(tX, tY, tZ);
						} else {
							aData.bricks(tX, tY, tZ);
						}
					} else {
						aData.air(tX, tY, tZ);
					}
				}
			}
			aData.redstoned(6, 4, 13);
			aData.lamp     (7, 4, 13, 0);
			aData.lamp     (8, 4, 13, 0);
			aData.redstoned(9, 4, 13);
		} else if (aData.mConnectionCount == 3 && aData.next1in2()) {
			for (int tY =  0; tY <= 4; tY++) for (int tX =  5; tX <= 10; tX++) aData.smooth(tX, tY, 11);
			for (int tY =  1; tY <= 3; tY++) for (int tX =  6; tX <=  9; tX++) aData.air   (tX, tY, 10);

			aData.smooth   (6, 1, 10);
			aData.set      (7, 1, 10, Blocks.crafting_table, 0, 2);
			aData.set      (8, 1, 10, SIDE_UNKNOWN, (short)((aData.next1in2()?508:8)+aData.next(3)), UT.NBT.make("gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_Z_NEG), T, T);
			aData.smooth   (9, 1, 10);

			aData.coins    (6, 2, 10);
			aData.set      (7, 2, 10, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);

			aData.coins    (9, 2, 10);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			for (int tX =  5; tX <= 10; tX++) for (int tZ =  0; tZ <=  5; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					aData.tiles(tX, tY, tZ);
				} else if (tX == 5 || tX == 10) {
					aData.bricks(tX, tY, tZ);
				} else {
					if (tY == 4) {
						if (WD.liquid(aData.mWorld, aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ) || aData.mWorld.canBlockSeeTheSky(aData.mX+tX, aData.mY+tY+1, aData.mZ+tZ)) {
							aData.glassglow(tX, tY, tZ);
						} else {
							aData.bricks(tX, tY, tZ);
						}
					} else {
						aData.air(tX, tY, tZ);
					}
				}
			}
			aData.redstoned(6, 4, 2);
			aData.lamp     (7, 4, 2, 0);
			aData.lamp     (8, 4, 2, 0);
			aData.redstoned(9, 4, 2);
		} else if (aData.mConnectionCount == 3 && aData.next1in2()) {
			for (int tY =  0; tY <= 4; tY++) for (int tX =  5; tX <= 10; tX++) aData.smooth(tX, tY, 4);
			for (int tY =  1; tY <= 3; tY++) for (int tX =  6; tX <=  9; tX++) aData.air   (tX, tY, 5);

			aData.smooth   (6, 1, 5);
			aData.set      (7, 1, 5, SIDE_UNKNOWN, (short)((aData.next1in2()?508:8)+aData.next(3)), UT.NBT.make("gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_Z_POS), T, T);
			aData.set      (8, 1, 5, Blocks.crafting_table, 0, 2);
			aData.smooth   (9, 1, 5);

			aData.coins    (6, 2, 5);

			aData.set      (8, 2, 5, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			aData.coins    (9, 2, 5);
		}
		return T;
	}
}
