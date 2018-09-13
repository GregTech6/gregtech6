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
public class WorldgenStructureExteriorCorridor extends WorldgenStructure {
	public static boolean generate(World aWorld, Random aRandom, int aChunkX, int aChunkZ, StructureData aData) {
		if ((aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0 || aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) && (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0 || aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0)) {
			WorldgenStructureExteriorPillar.generate(aWorld, aRandom, aChunkX, aChunkZ, aData);
		}
		
		if (aData.mConnectionCount == 4) {
			for (int tX =  2; tX <= 13; tX++) for (int tZ =  2; tZ <= 13; tZ++) for (int tY = 0; tY <= 6; tY++) {
				if (tX == 2 || tX == 13 || tZ == 2 || tZ == 13 || tY == 0 || tY == 6) {
					if ((tX == 4 || tX == 7 || tX == 8 || tX == 11) && (tZ == 4 || tZ == 7 || tZ == 8 || tZ == 11)) {
						if (tY == 0) {
							setChiseledStone(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
						} else if (tY == 6) {
							setLampBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom, +1);
						} else {
							setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
						}
					} else {
						if (tY == 0) {
							setStoneTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
						} else if (tY == 6) {
							setSmallTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
						} else {
							setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
						}
					}
				} else {
					setAirBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				}
			}
			
			for (int tX = 13; tX <= 15; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					setStoneTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else if (tY == 4) {
					setSmallTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else if (tZ == 5 || tZ == 10) {
					setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else {
					setAirBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				}
			}
			for (int tX =  0; tX <=  2; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					setStoneTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else if (tY == 4) {
					setSmallTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else if (tZ == 5 || tZ == 10) {
					setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else {
					setAirBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				}
			}
			for (int tX =  5; tX <= 10; tX++) for (int tZ = 13; tZ <= 15; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					setStoneTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else if (tY == 4) {
					setSmallTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else if (tX == 5 || tX == 10) {
					setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else {
					setAirBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				}
			}
			for (int tX =  5; tX <= 10; tX++) for (int tZ =  0; tZ <=  2; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					setStoneTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else if (tY == 4) {
					setSmallTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else if (tX == 5 || tX == 10) {
					setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else {
					setAirBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				}
			}
			return T;
		}
		
		for (int tX =  5; tX <= 10; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
			if (tY == 0) {
				setStoneTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
			} else if (tX == 5 || tX == 10 || tZ == 5 || tZ == 10) {
				setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
			} else {
				if (tY == 4) {
					if (WD.liquid(aWorld, aChunkX+tX, aData.mOffsetY+tY+1, aChunkZ+tZ) || aWorld.canBlockSeeTheSky(aChunkX+tX, aData.mOffsetY+tY+1, aChunkZ+tZ)) {
						setGlowGlass(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
					} else {
						setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
					}
				} else {
					setAirBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				}
			}
		}
		
		if (WD.liquid(aWorld, aChunkX+ 7, aData.mOffsetY+4, aChunkZ+ 7) || WD.liquid(aWorld, aChunkX+ 7, aData.mOffsetY+4, aChunkZ+ 8)
		 || WD.liquid(aWorld, aChunkX+ 8, aData.mOffsetY+4, aChunkZ+ 7) || WD.liquid(aWorld, aChunkX+ 8, aData.mOffsetY+4, aChunkZ+ 8)
		 || aWorld.canBlockSeeTheSky(aChunkX+ 7, aData.mOffsetY+4, aChunkZ+ 7) || aWorld.canBlockSeeTheSky(aChunkX+ 7, aData.mOffsetY+4, aChunkZ+ 8)
		 || aWorld.canBlockSeeTheSky(aChunkX+ 8, aData.mOffsetY+4, aChunkZ+ 7) || aWorld.canBlockSeeTheSky(aChunkX+ 8, aData.mOffsetY+4, aChunkZ+ 8)) {
			setGlowGlass        (aWorld, aChunkX+ 7, aData.mOffsetY+4, aChunkZ+ 7, aData, aRandom);
			setGlowGlass        (aWorld, aChunkX+ 7, aData.mOffsetY+4, aChunkZ+ 8, aData, aRandom);
			setGlowGlass        (aWorld, aChunkX+ 8, aData.mOffsetY+4, aChunkZ+ 7, aData, aRandom);
			setGlowGlass        (aWorld, aChunkX+ 8, aData.mOffsetY+4, aChunkZ+ 8, aData, aRandom);
		} else {
			setRedstoneBrick    (aWorld, aChunkX+ 7, aData.mOffsetY+4, aChunkZ+ 7, aData, aRandom);
			setLampBlock        (aWorld, aChunkX+ 7, aData.mOffsetY+4, aChunkZ+ 8, aData, aRandom, 0);
			setLampBlock        (aWorld, aChunkX+ 8, aData.mOffsetY+4, aChunkZ+ 7, aData, aRandom, 0);
			setRedstoneBrick    (aWorld, aChunkX+ 8, aData.mOffsetY+4, aChunkZ+ 8, aData, aRandom);
		}
		
		FluidStack[] tDrinks = new FluidStack[] {NF, NF, NF, NF, NF, FL.Purple_Drink.make(250), FL.Purple_Drink.make(250), FL.Purple_Drink.make(250), FL.Vodka.make(250), FL.Mead.make(250), FL.Whiskey_GlenMcKenner.make(250), FL.Wine_Grape_Purple.make(250)};
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] != 0) {
			for (int tX = 10; tX <= 15; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					setStoneTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else if (tZ == 5 || tZ == 10) {
					setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else {
					if (tY == 4) {
						if (WD.liquid(aWorld, aChunkX+tX, aData.mOffsetY+tY+1, aChunkZ+tZ) || aWorld.canBlockSeeTheSky(aChunkX+tX, aData.mOffsetY+tY+1, aChunkZ+tZ)) {
							setGlowGlass(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
						} else {
							setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
						}
					} else {
						setAirBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
					}
				}
			}
			setRedstoneBrick    (aWorld, aChunkX+13, aData.mOffsetY+4, aChunkZ+ 6, aData, aRandom);
			setLampBlock        (aWorld, aChunkX+13, aData.mOffsetY+4, aChunkZ+ 7, aData, aRandom, 0);
			setLampBlock        (aWorld, aChunkX+13, aData.mOffsetY+4, aChunkZ+ 8, aData, aRandom, 0);
			setRedstoneBrick    (aWorld, aChunkX+13, aData.mOffsetY+4, aChunkZ+ 9, aData, aRandom);
		} else if (aData.mConnectionCount == 3 && aRandom.nextInt(2) == 0) {
			for (int tY =  0; tY <= 4; tY++) for (int tZ =  5; tZ <= 10; tZ++) setSmoothBlock(aWorld, aChunkX+11, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
			for (int tY =  1; tY <= 3; tY++) for (int tZ =  6; tZ <=  9; tZ++) setAirBlock(aWorld, aChunkX+10, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
			
			setSmoothBlock                      (aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 6, aData, aRandom);
			setBlock                            (aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 7, Blocks.crafting_table, 0, 2);
			aData.mRegistry.mBlock.placeBlock   (aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 8, SIDE_UNKNOWN, (short)((aRandom.nextInt(2)==0?508:8)+aRandom.nextInt(3)), UT.NBT.make(null, "gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_X_NEG), T, T);
			setSmoothBlock                      (aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 9, aData, aRandom);
			
			setCoins                            (aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
			aData.mRegistry.mBlock.placeBlock   (aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+ 7, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			
			setCoins                            (aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] != 0) {
			for (int tX =  0; tX <=  5; tX++) for (int tZ =  5; tZ <= 10; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					setStoneTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else if (tZ == 5 || tZ == 10) {
					setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else {
					if (tY == 4) {
						if (WD.liquid(aWorld, aChunkX+tX, aData.mOffsetY+tY+1, aChunkZ+tZ) || aWorld.canBlockSeeTheSky(aChunkX+tX, aData.mOffsetY+tY+1, aChunkZ+tZ)) {
							setGlowGlass(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
						} else {
							setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
						}
					} else {
						setAirBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
					}
				}
			}
			setRedstoneBrick    (aWorld, aChunkX+ 2, aData.mOffsetY+4, aChunkZ+ 6, aData, aRandom);
			setLampBlock        (aWorld, aChunkX+ 2, aData.mOffsetY+4, aChunkZ+ 7, aData, aRandom, 0);
			setLampBlock        (aWorld, aChunkX+ 2, aData.mOffsetY+4, aChunkZ+ 8, aData, aRandom, 0);
			setRedstoneBrick    (aWorld, aChunkX+ 2, aData.mOffsetY+4, aChunkZ+ 9, aData, aRandom);
		} else if (aData.mConnectionCount == 3 && aRandom.nextInt(2) == 0) {
			for (int tY =  0; tY <= 4; tY++) for (int tZ =  5; tZ <= 10; tZ++) setSmoothBlock(aWorld, aChunkX+4, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
			for (int tY =  1; tY <= 3; tY++) for (int tZ =  6; tZ <=  9; tZ++) setAirBlock(aWorld, aChunkX+5, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
			
			setSmoothBlock                      (aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 6, aData, aRandom);
			aData.mRegistry.mBlock.placeBlock   (aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 7, SIDE_UNKNOWN, (short)((aRandom.nextInt(2)==0?508:8)+aRandom.nextInt(3)), UT.NBT.make(null, "gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_X_POS), T, T);
			setBlock                            (aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 8, Blocks.crafting_table, 0, 2);
			setSmoothBlock                      (aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 9, aData, aRandom);
			
			setCoins                            (aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
			
			aData.mRegistry.mBlock.placeBlock   (aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 8, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			setCoins                            (aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] != 0) {
			for (int tX =  5; tX <= 10; tX++) for (int tZ = 10; tZ <= 15; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					setStoneTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else if (tX == 5 || tX == 10) {
					setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else {
					if (tY == 4) {
						if (WD.liquid(aWorld, aChunkX+tX, aData.mOffsetY+tY+1, aChunkZ+tZ) || aWorld.canBlockSeeTheSky(aChunkX+tX, aData.mOffsetY+tY+1, aChunkZ+tZ)) {
							setGlowGlass(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
						} else {
							setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
						}
					} else {
						setAirBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
					}
				}
			}
			setRedstoneBrick    (aWorld, aChunkX+ 6, aData.mOffsetY+4, aChunkZ+13, aData, aRandom);
			setLampBlock        (aWorld, aChunkX+ 7, aData.mOffsetY+4, aChunkZ+13, aData, aRandom, 0);
			setLampBlock        (aWorld, aChunkX+ 8, aData.mOffsetY+4, aChunkZ+13, aData, aRandom, 0);
			setRedstoneBrick    (aWorld, aChunkX+ 9, aData.mOffsetY+4, aChunkZ+13, aData, aRandom);
		} else if (aData.mConnectionCount == 3 && aRandom.nextInt(2) == 0) {
			for (int tY =  0; tY <= 4; tY++) for (int tX =  5; tX <= 10; tX++) setSmoothBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+11, aData, aRandom);
			for (int tY =  1; tY <= 3; tY++) for (int tX =  6; tX <=  9; tX++) setAirBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+10, aData, aRandom);
			
			setSmoothBlock                      (aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+10, aData, aRandom);
			setBlock                            (aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+10, Blocks.crafting_table, 0, 2);
			aData.mRegistry.mBlock.placeBlock   (aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+10, SIDE_UNKNOWN, (short)((aRandom.nextInt(2)==0?508:8)+aRandom.nextInt(3)), UT.NBT.make(null, "gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_Z_NEG), T, T);
			setSmoothBlock                      (aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+10, aData, aRandom);
			
			setCoins                            (aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+10, aData, aRandom);
			aData.mRegistry.mBlock.placeBlock   (aWorld, aChunkX+ 7, aData.mOffsetY+2, aChunkZ+10, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			
			setCoins                            (aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+10, aData, aRandom);
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] != 0) {
			for (int tX =  5; tX <= 10; tX++) for (int tZ =  0; tZ <=  5; tZ++) for (int tY = 0; tY <= 4; tY++) {
				if (tY == 0) {
					setStoneTiles(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else if (tX == 5 || tX == 10) {
					setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
				} else {
					if (tY == 4) {
						if (WD.liquid(aWorld, aChunkX+tX, aData.mOffsetY+tY+1, aChunkZ+tZ) || aWorld.canBlockSeeTheSky(aChunkX+tX, aData.mOffsetY+tY+1, aChunkZ+tZ)) {
							setGlowGlass(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
						} else {
							setRandomBricks(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
						}
					} else {
						setAirBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+tZ, aData, aRandom);
					}
				}
			}
			setRedstoneBrick    (aWorld, aChunkX+ 6, aData.mOffsetY+4, aChunkZ+ 2, aData, aRandom);
			setLampBlock        (aWorld, aChunkX+ 7, aData.mOffsetY+4, aChunkZ+ 2, aData, aRandom, 0);
			setLampBlock        (aWorld, aChunkX+ 8, aData.mOffsetY+4, aChunkZ+ 2, aData, aRandom, 0);
			setRedstoneBrick    (aWorld, aChunkX+ 9, aData.mOffsetY+4, aChunkZ+ 2, aData, aRandom);
		} else if (aData.mConnectionCount == 3 && aRandom.nextInt(2) == 0) {
			for (int tY =  0; tY <= 4; tY++) for (int tX =  5; tX <= 10; tX++) setSmoothBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+4, aData, aRandom);
			for (int tY =  1; tY <= 3; tY++) for (int tX =  6; tX <=  9; tX++) setAirBlock(aWorld, aChunkX+tX, aData.mOffsetY+tY, aChunkZ+5, aData, aRandom);
			
			setSmoothBlock                      (aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 5, aData, aRandom);
			aData.mRegistry.mBlock.placeBlock   (aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 5, SIDE_UNKNOWN, (short)((aRandom.nextInt(2)==0?508:8)+aRandom.nextInt(3)), UT.NBT.make(null, "gt.dungeonloot", ChestGenHooks.STRONGHOLD_CORRIDOR, NBT_FACING, SIDE_Z_POS), T, T);
			setBlock                            (aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 5, Blocks.crafting_table, 0, 2);
			setSmoothBlock                      (aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 5, aData, aRandom);
			
			setCoins                            (aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+ 5, aData, aRandom);
			
			aData.mRegistry.mBlock.placeBlock   (aWorld, aChunkX+ 8, aData.mOffsetY+2, aChunkZ+ 5, SIDE_UNKNOWN, (short)32739, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			setCoins                            (aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+ 5, aData, aRandom);
		}
		return T;
	}
}
