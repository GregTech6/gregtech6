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
		
		OreDictMaterial[] tGeneratedMaterials = {MT.Cu, MT.Cu, MT.Sn, MT.Bronze, MT.Fe, MT.Fe, MT.Fe, MT.Steel, MT.Steel, MT.StainlessSteel, MT.StainlessSteel, MT.DamascusSteel};
		IPrefixBlock[] tGeneratedCrates = {BlocksGT.crateGtDust, BlocksGT.crateGtPlate, BlocksGT.crateGtIngot, BlocksGT.crateGtIngot, BlocksGT.crateGtDust, BlocksGT.crateGtPlate, BlocksGT.crateGtIngot, BlocksGT.crateGtIngot, BlocksGT.crateGt64Dust, BlocksGT.crateGt64Plate, BlocksGT.crateGt64Ingot, BlocksGT.crateGt64Ingot};
		FluidStack[][] tFluids = {
		  {FL.Oil_Creosote.make(16000), FL.Oil_Seed.make(16000), FL.lube(16000), FL.Glue.make(16000), FL.Latex.make(16000), FL.Holywater.make(16000), FL.Purple_Drink.make(16000)}
		, {FL.Oil_Creosote.make(32000), FL.Oil_Seed.make(32000), FL.lube(32000), FL.Glue.make(32000), FL.Latex.make(32000), FL.Holywater.make(32000), FL.Purple_Drink.make(32000)}
		, {FL.Oil_Normal.make(64000), FL.Oil_Normal.make(64000), FL.Oil_Soulsand.make(64000), FL.Oil_Light.make(64000), FL.Oil_Medium.make(64000), FL.Oil_Heavy.make(64000), FL.Oil_ExtraHeavy.make(64000)}
		};
		short[] tIDs = new short[] {32714, 32734, 32716};
		
		if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
			if (aData.mRandom.nextBoolean()) {
				int tType = aData.mRandom.nextInt(tIDs.length);
				for (int i = 0; i < 2; i++) for (int j = 0; j < 3; j++) {
					if (aData.mRandom.nextBoolean()) for (int k = 0; k < 4; k++) {
						aData.set(12+i, 1+k, 7+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.mRandom.nextBoolean()) break;
					} else if (aData.mRandom.nextBoolean()) {
						if (aData.mRandom.nextBoolean()) {
							aData.set(12+i, 1, 7+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
						} else {
							aData.set(12+i, 1, 7+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow, NBT_PAINTED, T), NBT_TANK), T, T);
						}
					}
				}
			}
		}
		if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
			if (aData.mRandom.nextBoolean()) {
				int tType = aData.mRandom.nextInt(tIDs.length);
				for (int i = 0; i < 2; i++) for (int j = 0; j < 3; j++) {
					if (aData.mRandom.nextBoolean()) for (int k = 0; k < 4; k++) {
						aData.set(1+i, 1+k, 7+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.mRandom.nextBoolean()) break;
					} else if (aData.mRandom.nextBoolean()) {
						if (aData.mRandom.nextBoolean()) {
							aData.set(1+i, 1, 7+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
						} else {
							aData.set(1+i, 1, 7+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow, NBT_PAINTED, T), NBT_TANK), T, T);
						}
					}
				}
			}
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
			if (aData.mRandom.nextBoolean()) {
				int tType = aData.mRandom.nextInt(tIDs.length);
				for (int i = 0; i < 3; i++) for (int j = 0; j < 2; j++) {
					if (aData.mRandom.nextBoolean()) for (int k = 0; k < 4; k++) {
						aData.set(7+i, 1+k, 12+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.mRandom.nextBoolean()) break;
					} else if (aData.mRandom.nextBoolean()) {
						if (aData.mRandom.nextBoolean()) {
							aData.set(7+i, 1, 12+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
						} else {
							aData.set(7+i, 1, 12+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow, NBT_PAINTED, T), NBT_TANK), T, T);
						}
					}
				}
			}
		}
		if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
			if (aData.mRandom.nextBoolean()) {
				int tType = aData.mRandom.nextInt(tIDs.length);
				for (int i = 0; i < 3; i++) for (int j = 0; j < 2; j++) {
					if (aData.mRandom.nextBoolean()) for (int k = 0; k < 4; k++) {
						aData.set(7+i, 1+k, 1+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.mRandom.nextBoolean()) break;
					} else if (aData.mRandom.nextBoolean()) {
						if (aData.mRandom.nextBoolean()) {
							aData.set(7+i, 1, 1+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
						} else {
							aData.set(7+i, 1, 1+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow, NBT_PAINTED, T), NBT_TANK), T, T);
						}
					}
				}
			}
		}
		
		if (aData.mRandom.nextBoolean()) {
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 2,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 2,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 2,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 2,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  3, 2,  3, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 2,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 2,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  3, 2,  3, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 3,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 3,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 3,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 3,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  3, 3,  3, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 3,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 3,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  3, 3,  3, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 4,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 4,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 4,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 4,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
		} else if (aData.mRandom.nextBoolean()) {
			if (aData.mRandom.nextBoolean()) aData.set( 1, 1,  1, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 2, 1,  2, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 1, 1,  1, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 2, 1,  2, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 3, 1,  3, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 1, 1,  1, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 2, 1,  2, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 3, 1,  3, tHexoriumColor, 1, tHexoriumRandom, 1);
		}
		if (aData.mRandom.nextBoolean()) {
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 12, 2,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 12, 2,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 2,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 2,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 2,  3, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 2,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 2,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 2,  3, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 12, 3,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 12, 3,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 3,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 3,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 3,  3, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 3,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 3,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 3,  3, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 4,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 4,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 4,  1, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 4,  2, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
		} else if (aData.mRandom.nextBoolean()) {
			if (aData.mRandom.nextBoolean()) aData.set(12, 1,  1, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(12, 1,  2, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(13, 1,  1, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(13, 1,  2, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(13, 1,  3, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(14, 1,  1, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(14, 1,  2, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(14, 1,  3, tHexoriumColor, 1, tHexoriumRandom, 1);
		}
		if (aData.mRandom.nextBoolean()) {
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 2, 12, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 2, 12, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 2, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 2, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  3, 2, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 2, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 2, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  3, 2, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 3, 12, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 3, 12, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 3, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 3, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  3, 3, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 3, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 3, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  3, 3, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 4, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 4, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  1, 4, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)],  2, 4, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
		} else if (aData.mRandom.nextBoolean()) {
			if (aData.mRandom.nextBoolean()) aData.set( 1, 1, 12, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 2, 1, 12, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 1, 1, 13, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 2, 1, 13, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 3, 1, 13, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 1, 1, 14, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 2, 1, 14, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set( 3, 1, 14, tHexoriumColor, 1, tHexoriumRandom, 1);
		}
		if (aData.mRandom.nextBoolean()) {
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 12, 2, 12, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 12, 2, 12, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 2, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 2, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 2, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 2, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 2, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 2, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 12, 3, 12, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 12, 3, 12, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 3, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 3, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 3, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 3, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 3, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 3, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 4, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 13, 4, 13, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 4, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
			if (aData.mRandom.nextBoolean()) aData.set(tGeneratedCrates[aData.mRandom.nextInt(tGeneratedCrates.length)], 14, 4, 14, tGeneratedMaterials[aData.mRandom.nextInt(tGeneratedMaterials.length)].mID);
		} else if (aData.mRandom.nextBoolean()) {
			if (aData.mRandom.nextBoolean()) aData.set(12, 1, 12, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(12, 1, 12, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(13, 1, 13, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(13, 1, 13, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(13, 1, 13, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(14, 1, 14, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(14, 1, 14, tHexoriumColor, 1, tHexoriumRandom, 1);
			if (aData.mRandom.nextBoolean()) aData.set(14, 1, 14, tHexoriumColor, 1, tHexoriumRandom, 1);
		}
		return T;
	}
}
