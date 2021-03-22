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
	, sDusts     = {MT.NaCl, MT.NaCl, MT.KCl, MT.KCl, MT.Gunpowder, MT.Gunpowder, MT.Gunpowder, MT.Bone, MT.Bone, MT.Asphalt, MT.Asphalt, MT.Clay, MT.ClayBrown, MT.ClayRed, MT.Bentonite, MT.Palygorskite, MT.Kaolinite, MT.Porcelain, MT.RareEarth, MT.Sugar, MT.Cocoa, MT.Coffee, MT.Vanilla, MT.PepperBlack, MT.Curry, MT.Wheat, MT.Barley, MT.Rye, MT.Rice, MT.Oat, MT.OatAbyssal, MT.Corn, MT.Potato, MT.Blaze, MT.Blizz, MT.Blitz, MT.Basalz}
	, sWoods     = {MT.WOODS.Oak, MT.WOODS.Birch, MT.WOODS.Spruce, MT.WOODS.Jungle, MT.WOODS.Acacia, MT.WOODS.DarkOak, MT.WOODS.Crimson, MT.WOODS.Warped, MT.WOODS.Compressed, MT.WoodRubber, MT.WOODS.Maple, MT.WOODS.Willow, MT.WOODS.BlueMahoe, MT.WOODS.Hazel, MT.WOODS.Cinnamon, MT.WOODS.Coconut, MT.WOODS.Rainbowood, MT.Livingwood, MT.Greatwood, MT.Silverwood, MT.WoodTreated, MT.Weedwood, MT.Skyroot}
	, sGems      = {MT.EnderPearl, MT.EnderPearl, MT.EnderEye, MT.Diamond, MT.DiamondPink, MT.Emerald, MT.Aquamarine, MT.Ruby, MT.GreenSapphire, MT.BlueSapphire, MT.Amethyst, MT.Craponite, MT.Amber, MT.WaxBee, MT.WaxRefractory, MT.VoidQuartz, MT.NetherQuartz, MT.NetherQuartz, MT.MilkyQuartz, MT.MilkyQuartz, MT.CertusQuartz, MT.ChargedCertusQuartz, MT.Lapis, MT.Lapis, MT.Lapis, MT.Redstone, MT.Redstone, MT.Redstone, MT.Glowstone, MT.Glowstone, MT.Gloomstone, MT.Apatite, MT.Apatite, MT.Apatite, MT.Coal, MT.Coal, MT.Coal, MT.Coal, MT.Coal, MT.Charcoal, MT.Charcoal, MT.Charcoal, MT.Charcoal, MT.Charcoal, MT.Lignite, MT.Lignite, MT.Lignite, MT.Lignite, MT.Lignite}
	, sMetals    = {MT.Cu, MT.Cu, MT.Sn, MT.Bronze, MT.Fe, MT.Fe, MT.Fe, MT.Steel, MT.Steel, MT.StainlessSteel, MT.StainlessSteel, MT.DamascusSteel}
	;
	
	@Override
	public boolean generate(DungeonData aData) {
		if (!super.generate(aData)) return F;
		
		Block
		tInfusedCrystal = ST.block(MD.TC , "blockCrystal"),
		tHexoriumColor  = ST.block(MD.HEX, UT.Code.select(aData.mColor, "blockEnergizedHexoriumMonolithRainbow", HEXORIUM_MONOLITHS)),
		tHexoriumRandom = ST.block(MD.HEX, UT.Code.select(              "blockEnergizedHexoriumMonolithRainbow", HEXORIUM_MONOLITHS));
		
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
						if (aData.next2in3()) break;
					} else if (aData.next1in2()) {
						switch(aData.next(3)) {
						case 0: aData.set(12+i, 1, 6+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red      , NBT_PAINTED, T), NBT_TANK), T, T); break;
						case 1: aData.set(12+i, 1, 6+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Oxygen .make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_LightBlue, NBT_PAINTED, T), NBT_TANK), T, T); break;
						case 2: aData.set(12+i, 1, 6+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium .make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow   , NBT_PAINTED, T), NBT_TANK), T, T); break;
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
						if (aData.next2in3()) break;
					} else if (aData.next1in2()) {
						switch(aData.next(3)) {
						case 0: aData.set(1+i, 1, 6+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red      , NBT_PAINTED, T), NBT_TANK), T, T); break;
						case 1: aData.set(1+i, 1, 6+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Oxygen .make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_LightBlue, NBT_PAINTED, T), NBT_TANK), T, T); break;
						case 2: aData.set(1+i, 1, 6+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium .make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow   , NBT_PAINTED, T), NBT_TANK), T, T); break;
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
						aData.set(6+i, 1+k, 12+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.next2in3()) break;
					} else if (aData.next1in2()) {
						switch(aData.next(3)) {
						case 0: aData.set(6+i, 1, 12+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red      , NBT_PAINTED, T), NBT_TANK), T, T); break;
						case 1: aData.set(6+i, 1, 12+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Oxygen .make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_LightBlue, NBT_PAINTED, T), NBT_TANK), T, T); break;
						case 2: aData.set(6+i, 1, 12+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium .make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow   , NBT_PAINTED, T), NBT_TANK), T, T); break;
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
						aData.set(6+i, 1+k, 1+j, SIDE_UNKNOWN, tIDs[tType], new FluidTankGT(UT.Code.select(NF, tFluids[tType])).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
						if (aData.next2in3()) break;
					} else if (aData.next1in2()) {
						switch(aData.next(3)) {
						case 0: aData.set(6+i, 1, 1+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red      , NBT_PAINTED, T), NBT_TANK), T, T); break;
						case 1: aData.set(6+i, 1, 1+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Oxygen .make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_LightBlue, NBT_PAINTED, T), NBT_TANK), T, T); break;
						case 2: aData.set(6+i, 1, 1+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium .make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow   , NBT_PAINTED, T), NBT_TANK), T, T); break;
						}
					}
				}
			}
		}
		
		
		IPrefixBlock[] tMetalCrates = {BlocksGT.crateGtDust, BlocksGT.crateGtDust, BlocksGT.crateGtIngot, BlocksGT.crateGtIngot, BlocksGT.crateGtIngot, BlocksGT.crateGtIngot, BlocksGT.crateGtPlate, BlocksGT.crateGtPlate, BlocksGT.crateGt64Dust, BlocksGT.crateGt64Plate, BlocksGT.crateGt64Ingot, BlocksGT.crateGt64Ingot};
		int[] tStart = {1, 12}, tEnd = {3, 14};
		for (int a = 0; a < 2; a++) for (int b = 0; b < 2; b++) {
			switch(aData.next(6 + (MD.HEX.mLoaded ? 1 : 0) + (MD.TC.mLoaded ? 1 : 0))) {
			case  0:
				for (int i = tStart[a]; i <= tEnd[a]; i++) for (int j = tStart[b]; j <= tEnd[b]; j++) {
					if (aData.next3in4()) {aData.set(tMetalCrates, i, 1, j, sMetals);
					if (aData.next2in3()) {aData.set(tMetalCrates, i, 2, j, sMetals);
					if (aData.next1in2()) {aData.set(tMetalCrates, i, 3, j, sMetals);
							if (aData.next1in3()) aData.ingots_or_plates(i, 4, j, 0, sMetals);
					} else  if (aData.next1in3()) aData.ingots_or_plates(i, 3, j, 0, sMetals);
					} else  if (aData.next1in3()) aData.ingots_or_plates(i, 2, j, 0, sMetals);
					} else  if (aData.next1in3()) aData.ingots_or_plates(i, 1, j, 0, sMetals);
				}
				break;
			case  1:
				for (int i = tStart[a]; i <= tEnd[a]; i++) for (int j = tStart[b]; j <= tEnd[b]; j++) {
					if (aData.next3in4()) {aData.set(aData.next1in2() ? BlocksGT.crateGt64Dust : BlocksGT.crateGtDust, i, 1, j, sDusts);
					if (aData.next2in3()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Dust : BlocksGT.crateGtDust, i, 2, j, sDusts);
					if (aData.next1in2()) {aData.set(aData.next1in4() ? BlocksGT.crateGt64Dust : BlocksGT.crateGtDust, i, 3, j, sDusts);
					}}}
				}
				break;
			case  2:
				for (int i = tStart[a]; i <= tEnd[a]; i++) for (int j = tStart[b]; j <= tEnd[b]; j++) {
					if (aData.next3in4()) {aData.set(aData.next1in4() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 1, j, sGems);
					if (aData.next2in3()) {aData.set(aData.next1in6() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 2, j, sGems);
					if (aData.next1in2()) {aData.set(aData.next1in8() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 3, j, sGems);
					}}}
				}
				break;
			case  3:
				for (int i = tStart[a]; i <= tEnd[a]; i++) for (int j = tStart[b]; j <= tEnd[b]; j++) {
					if (aData.next3in4()) {aData.set(BlocksGT.crateGt64Plate, i, 1, j, sWoods);
					if (aData.next2in3()) {aData.set(BlocksGT.crateGt64Plate, i, 2, j, sWoods);
					if (aData.next1in2()) {aData.set(BlocksGT.crateGt64Plate, i, 3, j, sWoods);
					}}}
				}
				break;
			case  4:
				if (MD.HEX.mLoaded) for (int i = tStart[a]; i <= tEnd[a]; i++) for (int j = tStart[b]; j <= tEnd[b]; j++) {
					if (aData.next1in2()) {aData.set(aData.next1in2() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 1, j, sHexoriums);
					if (aData.next1in2()) {aData.set(aData.next1in3() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 2, j, sHexoriums);
					if (aData.next1in2()) {aData.set(aData.next1in4() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 3, j, sHexoriums);
							if (aData.next1in2()) aData.set(i, 4, j, tHexoriumColor, 1, tHexoriumRandom, 1);
					} else  if (aData.next1in2()) aData.set(i, 3, j, tHexoriumColor, 1, tHexoriumRandom, 1);
					} else  if (aData.next1in2()) aData.set(i, 2, j, tHexoriumColor, 1, tHexoriumRandom, 1);
					} else  if (aData.next1in2()) aData.set(i, 1, j, tHexoriumColor, 1, tHexoriumRandom, 1);
				}
				break;
			case  5:
				if (MD.TC.mLoaded) for (int i = tStart[a]; i <= tEnd[a]; i++) for (int j = tStart[b]; j <= tEnd[b]; j++) {
					if (aData.next1in2()) {aData.set(aData.next1in4() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 1, j, sInfused);
					if (aData.next1in2()) {aData.set(aData.next1in6() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 2, j, sInfused);
					if (aData.next1in2()) {aData.set(aData.next1in8() ? BlocksGT.crateGt64Gem : BlocksGT.crateGtGem, i, 3, j, sInfused);
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
		}
		
		return T;
	}
}
