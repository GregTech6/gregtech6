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

import gregapi.block.IPrefixBlock;
import gregapi.data.CS.BlocksGT;
import gregapi.data.FL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.fluid.FluidTankGT;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomStorage extends DungeonChunkRoomVault {
	public static OreDictMaterial[]
	  sHexoriums = {MT.HexoriumRed, MT.HexoriumGreen, MT.HexoriumBlue, MT.HexoriumWhite, MT.HexoriumBlack}
	, sInfused   = {MT.InfusedFire, MT.InfusedAir, MT.InfusedWater, MT.InfusedEarth, MT.InfusedEntropy, MT.InfusedOrder}
	, sWoods     = {MT.WOODS.Oak, MT.WOODS.Birch, MT.WOODS.Spruce, MT.WOODS.Jungle, MT.WOODS.Acacia, MT.WOODS.DarkOak, MT.WOODS.Compressed, MT.WoodRubber, MT.WOODS.Maple, MT.WOODS.Willow, MT.WOODS.BlueMahoe, MT.WOODS.Hazel, MT.WOODS.Cinnamon, MT.WOODS.Coconut, MT.WOODS.Rainbowood}
	, sMetals    = {MT.Cu, MT.Cu, MT.Sn, MT.Bronze, MT.Fe, MT.Fe, MT.Fe, MT.Steel, MT.Steel, MT.StainlessSteel, MT.StainlessSteel, MT.DamascusSteel}
	;
	
	@Override
	public boolean generate(DungeonData aData) {
		if (!super.generate(aData)) return F;
		
		Block
		tInfusedCrystal = ST.block(MD.TC , "blockCrystal"),
		tHexoriumColor  = ST.block(MD.HEX, UT.Code.select(aData.mColor, "blockEnergizedHexoriumMonolithRainbow", HEXORIUM_MONOLITHS)),
		tHexoriumRandom = ST.block(MD.HEX, UT.Code.select(              "blockEnergizedHexoriumMonolithRainbow", HEXORIUM_MONOLITHS));
		
		IPrefixBlock[] tMetalCrates = {BlocksGT.crateGtDust, BlocksGT.crateGtDust, BlocksGT.crateGtIngot, BlocksGT.crateGtIngot, BlocksGT.crateGtIngot, BlocksGT.crateGtIngot, BlocksGT.crateGtPlate, BlocksGT.crateGtPlate, BlocksGT.crateGt64Dust, BlocksGT.crateGt64Plate, BlocksGT.crateGt64Ingot, BlocksGT.crateGt64Ingot};
		FluidStack[][] tFluids = {
		  {FL.Oil_Creosote.make(16000), FL.Oil_Seed.make(16000), FL.lube(16000), FL.Glue.make(16000), FL.Latex.make(16000), FL.Holywater.make(16000), FL.Purple_Drink.make(16000)}
		, {FL.Oil_Creosote.make(32000), FL.Oil_Seed.make(32000), FL.lube(32000), FL.Glue.make(32000), FL.Latex.make(32000), FL.Holywater.make(32000), FL.Purple_Drink.make(32000)}
		, {FL.Oil_Normal.make(64000), FL.Oil_Normal.make(64000), FL.Oil_Soulsand.make(64000), FL.Oil_Light.make(64000), FL.Oil_Medium.make(64000), FL.Oil_Heavy.make(64000), FL.Oil_ExtraHeavy.make(64000)}
		};
		short[] tIDs = new short[] {32714, 32734, 32716};
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
			if (aData.next1in2()) {
				int tType = aData.next(tIDs.length);
				for (int i = 0; i < 3; i++) for (int j = 0; j < 4; j++) {
					if (aData.next1in2()) for (int k = 0; k < 4; k++) {
						aData.set(12+i, 1+k, 6+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.next1in2()) break;
					} else if (aData.next1in2()) {
						if (aData.next1in2()) {
							aData.set(12+i, 1, 6+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
						} else {
							aData.set(12+i, 1, 6+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow, NBT_PAINTED, T), NBT_TANK), T, T);
						}
					}
				}
			}
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
			if (aData.next1in2()) {
				int tType = aData.next(tIDs.length);
				for (int i = 0; i < 3; i++) for (int j = 0; j < 4; j++) {
					if (aData.next1in2()) for (int k = 0; k < 4; k++) {
						aData.set(1+i, 1+k, 6+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.next1in2()) break;
					} else if (aData.next1in2()) {
						if (aData.next1in2()) {
							aData.set(1+i, 1, 6+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
						} else {
							aData.set(1+i, 1, 6+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow, NBT_PAINTED, T), NBT_TANK), T, T);
						}
					}
				}
			}
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
			if (aData.next1in2()) {
				int tType = aData.next(tIDs.length);
				for (int i = 0; i < 4; i++) for (int j = 0; j < 3; j++) {
					if (aData.next1in2()) for (int k = 0; k < 4; k++) {
						aData.set(7+i, 1+k, 12+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.next1in2()) break;
					} else if (aData.next1in2()) {
						if (aData.next1in2()) {
							aData.set(6+i, 1, 12+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
						} else {
							aData.set(6+i, 1, 12+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow, NBT_PAINTED, T), NBT_TANK), T, T);
						}
					}
				}
			}
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
			if (aData.next1in2()) {
				int tType = aData.next(tIDs.length);
				for (int i = 0; i < 4; i++) for (int j = 0; j < 3; j++) {
					if (aData.next1in2()) for (int k = 0; k < 4; k++) {
						aData.set(7+i, 1+k, 1+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.next1in2()) break;
					} else if (aData.next1in2()) {
						if (aData.next1in2()) {
							aData.set(6+i, 1, 1+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
						} else {
							aData.set(6+i, 1, 1+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow, NBT_PAINTED, T), NBT_TANK), T, T);
						}
					}
				}
			}
		}
		
		
		switch(aData.next(5)) {
		case  0:
			for (int i = 1; i <= 3; i++) for (int j = 1; j <= 3; j++) {
				if (aData.next1in2()) aData.set(tMetalCrates, i, 2, j, sMetals);
				if (aData.next1in2()) aData.set(tMetalCrates, i, 3, j, sMetals);
				if (aData.next1in2()) aData.set(tMetalCrates, i, 4, j, sMetals);
			}
			break;
		case  1:
			for (int i = 1; i <= 3; i++) for (int j = 1; j <= 3; j++) {
				if (aData.next1in2()) aData.set(BlocksGT.crateGt64Plate, i, 2, j, sWoods);
				if (aData.next1in2()) aData.set(BlocksGT.crateGt64Plate, i, 3, j, sWoods);
				if (aData.next1in2()) aData.set(BlocksGT.crateGt64Plate, i, 4, j, sWoods);
			}
			break;
		case  2:
			if (MD.HEX.mLoaded) for (int i = 1; i <= 3; i++) for (int j = 1; j <= 3; j++) {
				if (aData.next1in2()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 1, j, sHexoriums);
				if (aData.next1in2()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 2, j, sHexoriums);
				if (aData.next1in2()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 3, j, sHexoriums);
						if (aData.next1in2()) aData.set(i, 4, j, tHexoriumColor, 1, tHexoriumRandom, 1);
				} else  if (aData.next1in2()) aData.set(i, 3, j, tHexoriumColor, 1, tHexoriumRandom, 1);
				} else  if (aData.next1in2()) aData.set(i, 2, j, tHexoriumColor, 1, tHexoriumRandom, 1);
				} else  if (aData.next1in2()) aData.set(i, 1, j, tHexoriumColor, 1, tHexoriumRandom, 1);
			}
			break;
		case  3:
			if (MD.TC.mLoaded) for (int i = 1; i <= 3; i++) for (int j = 1; j <= 3; j++) {
				if (aData.next1in2()) {aData.set(aData.next1in5() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 1, j, sInfused);
				if (aData.next1in2()) {aData.set(aData.next1in5() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 2, j, sInfused);
				if (aData.next1in2()) {aData.set(aData.next1in5() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 3, j, sInfused);
						if (aData.next1in2()) aData.set(i, 4, j, tInfusedCrystal, aData.next(7));
				} else  if (aData.next1in2()) aData.set(i, 3, j, tInfusedCrystal, aData.next(7));
				} else  if (aData.next1in2()) aData.set(i, 2, j, tInfusedCrystal, aData.next(7));
				} else  if (aData.next1in2()) aData.set(i, 1, j, tInfusedCrystal, aData.next(7));
			}
			break;
		default:
			// Nothing
			break;
		}
		
		switch(aData.next(5)) {
		case  0:
			for (int i = 12; i <= 14; i++) for (int j = 1; j <= 3; j++) {
				if (aData.next1in2()) aData.set(tMetalCrates, i, 2, j, sMetals);
				if (aData.next1in2()) aData.set(tMetalCrates, i, 3, j, sMetals);
				if (aData.next1in2()) aData.set(tMetalCrates, i, 4, j, sMetals);
			}
			break;
		case  1:
			for (int i = 12; i <= 14; i++) for (int j = 1; j <= 3; j++) {
				if (aData.next1in2()) aData.set(BlocksGT.crateGt64Plate, i, 2, j, sWoods);
				if (aData.next1in2()) aData.set(BlocksGT.crateGt64Plate, i, 3, j, sWoods);
				if (aData.next1in2()) aData.set(BlocksGT.crateGt64Plate, i, 4, j, sWoods);
			}
			break;
		case  2:
			if (MD.HEX.mLoaded) for (int i = 12; i <= 14; i++) for (int j = 1; j <= 3; j++) {
				if (aData.next1in2()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 1, j, sHexoriums);
				if (aData.next1in2()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 2, j, sHexoriums);
				if (aData.next1in2()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 3, j, sHexoriums);
						if (aData.next1in2()) aData.set(i, 4, j, tHexoriumColor, 1, tHexoriumRandom, 1);
				} else  if (aData.next1in2()) aData.set(i, 3, j, tHexoriumColor, 1, tHexoriumRandom, 1);
				} else  if (aData.next1in2()) aData.set(i, 2, j, tHexoriumColor, 1, tHexoriumRandom, 1);
				} else  if (aData.next1in2()) aData.set(i, 1, j, tHexoriumColor, 1, tHexoriumRandom, 1);
			}
			break;
		case  3:
			if (MD.TC.mLoaded) for (int i = 12; i <= 14; i++) for (int j = 1; j <= 3; j++) {
				if (aData.next1in2()) {aData.set(aData.next1in5() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 1, j, sInfused);
				if (aData.next1in2()) {aData.set(aData.next1in5() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 2, j, sInfused);
				if (aData.next1in2()) {aData.set(aData.next1in5() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 3, j, sInfused);
						if (aData.next1in2()) aData.set(i, 4, j, tInfusedCrystal, aData.next(7));
				} else  if (aData.next1in2()) aData.set(i, 3, j, tInfusedCrystal, aData.next(7));
				} else  if (aData.next1in2()) aData.set(i, 2, j, tInfusedCrystal, aData.next(7));
				} else  if (aData.next1in2()) aData.set(i, 1, j, tInfusedCrystal, aData.next(7));
			}
			break;
		default:
			// Nothing
			break;
		}
		
		switch(aData.next(5)) {
		case  0:
			for (int i = 1; i <= 3; i++) for (int j = 12; j <= 14; j++) {
				if (aData.next1in2()) aData.set(tMetalCrates, i, 2, j, sMetals);
				if (aData.next1in2()) aData.set(tMetalCrates, i, 3, j, sMetals);
				if (aData.next1in2()) aData.set(tMetalCrates, i, 4, j, sMetals);
			}
			break;
		case  1:
			for (int i = 1; i <= 3; i++) for (int j = 12; j <= 14; j++) {
				if (aData.next1in2()) aData.set(BlocksGT.crateGt64Plate, i, 2, j, sWoods);
				if (aData.next1in2()) aData.set(BlocksGT.crateGt64Plate, i, 3, j, sWoods);
				if (aData.next1in2()) aData.set(BlocksGT.crateGt64Plate, i, 4, j, sWoods);
			}
			break;
		case  2:
			if (MD.HEX.mLoaded) for (int i = 1; i <= 3; i++) for (int j = 12; j <= 14; j++) {
				if (aData.next1in2()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 1, j, sHexoriums);
				if (aData.next1in2()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 2, j, sHexoriums);
				if (aData.next1in2()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 3, j, sHexoriums);
						if (aData.next1in2()) aData.set(i, 4, j, tHexoriumColor, 1, tHexoriumRandom, 1);
				} else  if (aData.next1in2()) aData.set(i, 3, j, tHexoriumColor, 1, tHexoriumRandom, 1);
				} else  if (aData.next1in2()) aData.set(i, 2, j, tHexoriumColor, 1, tHexoriumRandom, 1);
				} else  if (aData.next1in2()) aData.set(i, 1, j, tHexoriumColor, 1, tHexoriumRandom, 1);
			}
			break;
		case  3:
			if (MD.TC.mLoaded) for (int i = 1; i <= 3; i++) for (int j = 12; j <= 14; j++) {
				if (aData.next1in2()) {aData.set(aData.next1in5() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 1, j, sInfused);
				if (aData.next1in2()) {aData.set(aData.next1in5() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 2, j, sInfused);
				if (aData.next1in2()) {aData.set(aData.next1in5() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 3, j, sInfused);
						if (aData.next1in2()) aData.set(i, 4, j, tInfusedCrystal, aData.next(7));
				} else  if (aData.next1in2()) aData.set(i, 3, j, tInfusedCrystal, aData.next(7));
				} else  if (aData.next1in2()) aData.set(i, 2, j, tInfusedCrystal, aData.next(7));
				} else  if (aData.next1in2()) aData.set(i, 1, j, tInfusedCrystal, aData.next(7));
			}
			break;
		default:
			// Nothing
			break;
		}
		
		
		
		switch(aData.next(5)) {
		case  0:
			for (int i = 12; i <= 14; i++) for (int j = 12; j <= 14; j++) {
				if (aData.next1in2()) aData.set(tMetalCrates, i, 2, j, sMetals);
				if (aData.next1in2()) aData.set(tMetalCrates, i, 3, j, sMetals);
				if (aData.next1in2()) aData.set(tMetalCrates, i, 4, j, sMetals);
			}
			break;
		case  1:
			for (int i = 12; i <= 14; i++) for (int j = 12; j <= 14; j++) {
				if (aData.next1in2()) aData.set(BlocksGT.crateGt64Plate, i, 2, j, sWoods);
				if (aData.next1in2()) aData.set(BlocksGT.crateGt64Plate, i, 3, j, sWoods);
				if (aData.next1in2()) aData.set(BlocksGT.crateGt64Plate, i, 4, j, sWoods);
			}
			break;
		case  2:
			if (MD.HEX.mLoaded) for (int i = 12; i <= 14; i++) for (int j = 12; j <= 14; j++) {
				if (aData.next1in2()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 1, j, sHexoriums);
				if (aData.next1in2()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 2, j, sHexoriums);
				if (aData.next1in2()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 3, j, sHexoriums);
						if (aData.next1in2()) aData.set(i, 4, j, tHexoriumColor, 1, tHexoriumRandom, 1);
				} else  if (aData.next1in2()) aData.set(i, 3, j, tHexoriumColor, 1, tHexoriumRandom, 1);
				} else  if (aData.next1in2()) aData.set(i, 2, j, tHexoriumColor, 1, tHexoriumRandom, 1);
				} else  if (aData.next1in2()) aData.set(i, 1, j, tHexoriumColor, 1, tHexoriumRandom, 1);
			}
			break;
		case  3:
			if (MD.TC.mLoaded) for (int i = 12; i <= 14; i++) for (int j = 12; j <= 14; j++) {
				if (aData.next1in2()) {aData.set(aData.next1in5() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 1, j, sInfused);
				if (aData.next1in2()) {aData.set(aData.next1in5() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 2, j, sInfused);
				if (aData.next1in2()) {aData.set(aData.next1in5() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 3, j, sInfused);
						if (aData.next1in2()) aData.set(i, 4, j, tInfusedCrystal, aData.next(7));
				} else  if (aData.next1in2()) aData.set(i, 3, j, tInfusedCrystal, aData.next(7));
				} else  if (aData.next1in2()) aData.set(i, 2, j, tInfusedCrystal, aData.next(7));
				} else  if (aData.next1in2()) aData.set(i, 1, j, tInfusedCrystal, aData.next(7));
			}
			break;
		default:
			// Nothing
			break;
		}
		return T;
	}
}
