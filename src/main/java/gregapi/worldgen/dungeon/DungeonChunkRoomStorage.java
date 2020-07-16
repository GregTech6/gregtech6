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
	@Override
	public boolean generate(DungeonData aData) {
		if (!super.generate(aData)) return F;
		
		Block
		tHexoriumColor  = ST.block(MD.HEX, UT.Code.select(aData.mColor, "blockEnergizedHexoriumMonolithRainbow", HEXORIUM_MONOLITHS)),
		tHexoriumRandom = ST.block(MD.HEX, UT.Code.select(              "blockEnergizedHexoriumMonolithRainbow", HEXORIUM_MONOLITHS));
		
		OreDictMaterial[] tWoods = {MT.WOODS.Oak, MT.WOODS.Birch, MT.WOODS.Spruce, MT.WOODS.Jungle, MT.WOODS.Acacia, MT.WOODS.DarkOak, MT.WOODS.Compressed, MT.WoodRubber, MT.WOODS.Maple, MT.WOODS.Willow, MT.WOODS.BlueMahoe, MT.WOODS.Hazel, MT.WOODS.Cinnamon, MT.WOODS.Coconut, MT.WOODS.Rainbowood};
		OreDictMaterial[] tMetals = {MT.Cu, MT.Cu, MT.Sn, MT.Bronze, MT.Fe, MT.Fe, MT.Fe, MT.Steel, MT.Steel, MT.StainlessSteel, MT.StainlessSteel, MT.DamascusSteel};
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
				for (int i = 0; i < 2; i++) for (int j = 0; j < 3; j++) {
					if (aData.next1in2()) for (int k = 0; k < 4; k++) {
						aData.set(12+i, 1+k, 7+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.next1in2()) break;
					} else if (aData.next1in2()) {
						if (aData.next1in2()) {
							aData.set(12+i, 1, 7+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
						} else {
							aData.set(12+i, 1, 7+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow, NBT_PAINTED, T), NBT_TANK), T, T);
						}
					}
				}
			}
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
			if (aData.next1in2()) {
				int tType = aData.next(tIDs.length);
				for (int i = 0; i < 2; i++) for (int j = 0; j < 3; j++) {
					if (aData.next1in2()) for (int k = 0; k < 4; k++) {
						aData.set(1+i, 1+k, 7+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.next1in2()) break;
					} else if (aData.next1in2()) {
						if (aData.next1in2()) {
							aData.set(1+i, 1, 7+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
						} else {
							aData.set(1+i, 1, 7+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow, NBT_PAINTED, T), NBT_TANK), T, T);
						}
					}
				}
			}
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
			if (aData.next1in2()) {
				int tType = aData.next(tIDs.length);
				for (int i = 0; i < 3; i++) for (int j = 0; j < 2; j++) {
					if (aData.next1in2()) for (int k = 0; k < 4; k++) {
						aData.set(7+i, 1+k, 12+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.next1in2()) break;
					} else if (aData.next1in2()) {
						if (aData.next1in2()) {
							aData.set(7+i, 1, 12+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
						} else {
							aData.set(7+i, 1, 12+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow, NBT_PAINTED, T), NBT_TANK), T, T);
						}
					}
				}
			}
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
			if (aData.next1in2()) {
				int tType = aData.next(tIDs.length);
				for (int i = 0; i < 3; i++) for (int j = 0; j < 2; j++) {
					if (aData.next1in2()) for (int k = 0; k < 4; k++) {
						aData.set(7+i, 1+k, 1+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.next1in2()) break;
					} else if (aData.next1in2()) {
						if (aData.next1in2()) {
							aData.set(7+i, 1, 1+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
						} else {
							aData.set(7+i, 1, 1+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow, NBT_PAINTED, T), NBT_TANK), T, T);
						}
					}
				}
			}
		}
		
		
		switch(aData.next(4)) {
		case  0:
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 2,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 2,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 2,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 2,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  3, 2,  3, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 2,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 2,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  3, 2,  3, tMetals);
			
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 3,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 3,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 3,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 3,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  3, 3,  3, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 3,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 3,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  3, 3,  3, tMetals);
			
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 4,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 4,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 4,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 4,  2, tMetals);
			break;
		case  1:
			if (aData.next1in2()) aData.set( 1, 1,  1, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 2, 1,  2, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 1, 1,  1, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 2, 1,  2, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 3, 1,  3, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 1, 1,  1, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 2, 1,  2, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 3, 1,  3, tHexoriumColor, 1, tHexoriumRandom, 1);
			break;
		case  2:
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 2,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 2,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 2,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 2,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  3, 2,  3, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 2,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 2,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  3, 2,  3, tWoods);
			
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 3,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 3,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 3,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 3,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  3, 3,  3, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 3,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 3,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  3, 3,  3, tWoods);
			
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 4,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 4,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 4,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 4,  2, tWoods);
			break;
		default:
			// Nothing
			break;
		}
		
		
		
		switch(aData.next(4)) {
		case  0:
			if (aData.next1in2()) aData.set(tMetalCrates, 12, 2,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 12, 2,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 2,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 2,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 2,  3, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 2,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 2,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 2,  3, tMetals);
			
			if (aData.next1in2()) aData.set(tMetalCrates, 12, 3,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 12, 3,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 3,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 3,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 3,  3, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 3,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 3,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 3,  3, tMetals);
			
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 4,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 4,  2, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 4,  1, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 4,  2, tMetals);
			break;
		case  1:
			if (aData.next1in2()) aData.set(12, 1,  1, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(12, 1,  2, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(13, 1,  1, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(13, 1,  2, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(13, 1,  3, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(14, 1,  1, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(14, 1,  2, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(14, 1,  3, tHexoriumColor, 1, tHexoriumRandom, 1);
			break;
		case  2:
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 12, 2,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 12, 2,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 2,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 2,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 2,  3, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 2,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 2,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 2,  3, tWoods);
			
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 12, 3,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 12, 3,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 3,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 3,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 3,  3, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 3,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 3,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 3,  3, tWoods);
			
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 4,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 4,  2, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 4,  1, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 4,  2, tWoods);
			break;
		default:
			// Nothing
			break;
		}
		
		
		
		switch(aData.next(4)) {
		case  0:
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 2, 12, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 2, 12, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 2, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 2, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  3, 2, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 2, 14, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 2, 14, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  3, 2, 14, tMetals);
			
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 3, 12, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 3, 12, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 3, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 3, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  3, 3, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 3, 14, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 3, 14, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  3, 3, 14, tMetals);
			
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 4, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 4, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  1, 4, 14, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates,  2, 4, 14, tMetals);
			break;
		case  1:
			if (aData.next1in2()) aData.set( 1, 1, 12, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 2, 1, 12, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 1, 1, 13, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 2, 1, 13, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 3, 1, 13, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 1, 1, 14, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 2, 1, 14, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set( 3, 1, 14, tHexoriumColor, 1, tHexoriumRandom, 1);
			break;
		case  2:
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 2, 12, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 2, 12, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 2, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 2, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  3, 2, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 2, 14, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 2, 14, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  3, 2, 14, tWoods);
			
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 3, 12, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 3, 12, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 3, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 3, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  3, 3, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 3, 14, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 3, 14, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  3, 3, 14, tWoods);
			
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 4, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 4, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  1, 4, 14, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate,  2, 4, 14, tWoods);
			break;
		default:
			// Nothing
			break;
		}
		
		
		
		switch(aData.next(4)) {
		case  0:
			if (aData.next1in2()) aData.set(tMetalCrates, 12, 2, 12, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 12, 2, 12, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 2, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 2, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 2, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 2, 14, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 2, 14, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 2, 14, tMetals);
			
			if (aData.next1in2()) aData.set(tMetalCrates, 12, 3, 12, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 12, 3, 12, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 3, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 3, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 3, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 3, 14, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 3, 14, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 3, 14, tMetals);
			
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 4, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 13, 4, 13, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 4, 14, tMetals);
			if (aData.next1in2()) aData.set(tMetalCrates, 14, 4, 14, tMetals);
			break;
		case  1:
			if (aData.next1in2()) aData.set(12, 1, 12, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(12, 1, 12, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(13, 1, 13, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(13, 1, 13, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(13, 1, 13, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(14, 1, 14, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(14, 1, 14, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.next1in2()) aData.set(14, 1, 14, tHexoriumColor, 1, tHexoriumRandom, 1);
			break;
		case  2:
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 12, 2, 12, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 12, 2, 12, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 2, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 2, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 2, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 2, 14, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 2, 14, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 2, 14, tWoods);
			
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 12, 3, 12, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 12, 3, 12, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 3, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 3, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 3, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 3, 14, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 3, 14, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 3, 14, tWoods);
			
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 4, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 13, 4, 13, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 4, 14, tWoods);
			if (aData.next2in3()) aData.set(BlocksGT.crateGt64Plate, 14, 4, 14, tWoods);
			break;
		default:
			// Nothing
			break;
		}
		return T;
	}
}
