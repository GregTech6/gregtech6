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

import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.fluid.FluidTankGT;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ChestGenHooks;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomLibrary extends DungeonChunkRoomEmpty {
	@Override
	public boolean generate(DungeonData aData) {
		super.generate(aData);
		int tPlank = aData.next(6), tSlab = tPlank + 8, tShelf = 7000 + tPlank;
		
		String[] tLoots = new String[] {ChestGenHooks.STRONGHOLD_LIBRARY, ChestGenHooks.STRONGHOLD_CORRIDOR, ChestGenHooks.STRONGHOLD_CROSSING, ChestGenHooks.PYRAMID_DESERT_CHEST, ChestGenHooks.PYRAMID_JUNGLE_CHEST, ChestGenHooks.VILLAGE_BLACKSMITH, ChestGenHooks.MINESHAFT_CORRIDOR, ChestGenHooks.DUNGEON_CHEST, ChestGenHooks.BONUS_CHEST};
		
		for (int tX = 2; tX <= 13; tX++) for (int tZ = 2; tZ <= 13; tZ++) {
			aData.set(tX, 1, tZ, Blocks.carpet, aData.mColorInversed);
		}
		
		for (int tX = 1; tX <= 14; tX++) for (int tZ = 1; tZ <= 14; tZ++) {
			if (tX == 1 || tX == 14 || tZ == 1 || tZ == 14) {
				aData.set(tX, 6, tZ, Blocks.planks, tPlank);
				aData.set(tX, 5, tZ, Blocks.wooden_slab, tSlab);
			} else if ((tX == 3 || tX == 6 || tX == 9 || tX == 12) && (tZ == 3 || tZ == 6 || tZ == 9 || tZ == 12)) {
				aData.smooth(tX, 7, tZ);
				aData.lamp(tX, 6, tZ, +1);
			} else {
				aData.set(tX, 6, tZ, Blocks.wooden_slab, tSlab);
			}
		}
		
		aData.set( 2, 3,  4, Blocks.wooden_slab, tSlab);
		aData.set( 2, 3,  3, Blocks.wooden_slab, tSlab);
		aData.set( 2, 3,  2, Blocks.planks, tPlank);
		aData.set( 3, 3,  2, Blocks.wooden_slab, tSlab);
		aData.set( 4, 3,  2, Blocks.wooden_slab, tSlab);
		
		aData.set(13, 3,  4, Blocks.wooden_slab, tSlab);
		aData.set(13, 3,  3, Blocks.wooden_slab, tSlab);
		aData.set(13, 3,  2, Blocks.planks, tPlank);
		aData.set(12, 3,  2, Blocks.wooden_slab, tSlab);
		aData.set(11, 3,  2, Blocks.wooden_slab, tSlab);
		
		aData.set( 2, 3, 11, Blocks.wooden_slab, tSlab);
		aData.set( 2, 3, 12, Blocks.wooden_slab, tSlab);
		aData.set( 2, 3, 13, Blocks.planks, tPlank);
		aData.set( 3, 3, 13, Blocks.wooden_slab, tSlab);
		aData.set( 4, 3, 13, Blocks.wooden_slab, tSlab);
		
		aData.set(13, 3, 11, Blocks.wooden_slab, tSlab);
		aData.set(13, 3, 12, Blocks.wooden_slab, tSlab);
		aData.set(13, 3, 13, Blocks.planks, tPlank);
		aData.set(12, 3, 13, Blocks.wooden_slab, tSlab);
		aData.set(11, 3, 13, Blocks.wooden_slab, tSlab);
		
		for (int tY = 1; tY <= 5; tY++) {
			aData.set( 1, tY,  1, Blocks.planks, tPlank);
			aData.set(14, tY,  1, Blocks.planks, tPlank);
			aData.set( 1, tY, 14, Blocks.planks, tPlank);
			aData.set(14, tY, 14, Blocks.planks, tPlank);
			
			if (tY == 3) {
				aData.set( 2, tY,  1, Blocks.planks, tPlank);
				aData.set( 3, tY,  1, Blocks.planks, tPlank);
				aData.set( 4, tY,  1, Blocks.planks, tPlank);
				aData.set(11, tY,  1, Blocks.planks, tPlank);
				aData.set(12, tY,  1, Blocks.planks, tPlank);
				aData.set(13, tY,  1, Blocks.planks, tPlank);
				
				aData.set(14, tY,  2, Blocks.planks, tPlank);
				aData.set(14, tY,  3, Blocks.planks, tPlank);
				aData.set(14, tY,  4, Blocks.planks, tPlank);
				aData.set(14, tY, 11, Blocks.planks, tPlank);
				aData.set(14, tY, 12, Blocks.planks, tPlank);
				aData.set(14, tY, 13, Blocks.planks, tPlank);
				
				aData.set( 2, tY, 14, Blocks.planks, tPlank);
				aData.set( 3, tY, 14, Blocks.planks, tPlank);
				aData.set( 4, tY, 14, Blocks.planks, tPlank);
				aData.set(11, tY, 14, Blocks.planks, tPlank);
				aData.set(12, tY, 14, Blocks.planks, tPlank);
				aData.set(13, tY, 14, Blocks.planks, tPlank);
				
				aData.set( 1, tY,  2, Blocks.planks, tPlank);
				aData.set( 1, tY,  3, Blocks.planks, tPlank);
				aData.set( 1, tY,  4, Blocks.planks, tPlank);
				aData.set( 1, tY, 11, Blocks.planks, tPlank);
				aData.set( 1, tY, 12, Blocks.planks, tPlank);
				aData.set( 1, tY, 13, Blocks.planks, tPlank);
			} else {
				int tIndex = aData.next(24), tKeyIndex = aData.next(aData.mGeneratedKeys.length * 2);
				NBTTagList tList = null;
				if (tKeyIndex < aData.mGeneratedKeys.length) {
					aData.mGeneratedKeys[tKeyIndex] = T;
					tList = new NBTTagList();
					tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[tKeyIndex]), "s", (short)aData.next(14)));
				}
				
				aData.shelf( 2, tY,  1, tShelf, SIDE_Z_POS, tLoots, tIndex--==0?tList:null);
				aData.shelf( 3, tY,  1, tShelf, SIDE_Z_POS, tLoots, tIndex--==0?tList:null);
				aData.shelf( 4, tY,  1, tShelf, SIDE_Z_POS, tLoots, tIndex--==0?tList:null);
				aData.shelf(11, tY,  1, tShelf, SIDE_Z_POS, tLoots, tIndex--==0?tList:null);
				aData.shelf(12, tY,  1, tShelf, SIDE_Z_POS, tLoots, tIndex--==0?tList:null);
				aData.shelf(13, tY,  1, tShelf, SIDE_Z_POS, tLoots, tIndex--==0?tList:null);
				
				aData.shelf(14, tY,  2, tShelf, SIDE_X_NEG, tLoots, tIndex--==0?tList:null);
				aData.shelf(14, tY,  3, tShelf, SIDE_X_NEG, tLoots, tIndex--==0?tList:null);
				aData.shelf(14, tY,  4, tShelf, SIDE_X_NEG, tLoots, tIndex--==0?tList:null);
				aData.shelf(14, tY, 11, tShelf, SIDE_X_NEG, tLoots, tIndex--==0?tList:null);
				aData.shelf(14, tY, 12, tShelf, SIDE_X_NEG, tLoots, tIndex--==0?tList:null);
				aData.shelf(14, tY, 13, tShelf, SIDE_X_NEG, tLoots, tIndex--==0?tList:null);
				
				aData.shelf( 2, tY, 14, tShelf, SIDE_Z_NEG, tLoots, tIndex--==0?tList:null);
				aData.shelf( 3, tY, 14, tShelf, SIDE_Z_NEG, tLoots, tIndex--==0?tList:null);
				aData.shelf( 4, tY, 14, tShelf, SIDE_Z_NEG, tLoots, tIndex--==0?tList:null);
				aData.shelf(11, tY, 14, tShelf, SIDE_Z_NEG, tLoots, tIndex--==0?tList:null);
				aData.shelf(12, tY, 14, tShelf, SIDE_Z_NEG, tLoots, tIndex--==0?tList:null);
				aData.shelf(13, tY, 14, tShelf, SIDE_Z_NEG, tLoots, tIndex--==0?tList:null);
				
				aData.shelf( 1, tY,  2, tShelf, SIDE_X_POS, tLoots, tIndex--==0?tList:null);
				aData.shelf( 1, tY,  3, tShelf, SIDE_X_POS, tLoots, tIndex--==0?tList:null);
				aData.shelf( 1, tY,  4, tShelf, SIDE_X_POS, tLoots, tIndex--==0?tList:null);
				aData.shelf( 1, tY, 11, tShelf, SIDE_X_POS, tLoots, tIndex--==0?tList:null);
				aData.shelf( 1, tY, 12, tShelf, SIDE_X_POS, tLoots, tIndex--==0?tList:null);
				aData.shelf( 1, tY, 13, tShelf, SIDE_X_POS, tLoots, tIndex--==0?tList:null);
			}
		}
		
		Block
		tHexoriumColor  = ST.block(MD.HEX, UT.Code.select(aData.mColor, "blockEnergizedHexoriumMonolithRainbow", HEXORIUM_MONOLITHS)),
		tHexoriumRandom = ST.block(MD.HEX, UT.Code.select(              "blockEnergizedHexoriumMonolithRainbow", HEXORIUM_MONOLITHS));
		
		boolean tDidntGenerateSpecial = T, tDidntGenerateZPM = T;
		switch (aData.next(3)) {
		case 0:
			if (!MD.TC.mLoaded) break;
			tDidntGenerateSpecial = F;
			Block tThaumcraftTable = ST.block(MD.TC, "blockTable"), tThaumcraftCandle = ST.block(MD.TC, "blockCandle");
			if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
				aData.set  (14, 1,  5, tThaumcraftTable, 1);
				aData.shelf(14, 1,  6, 7046, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  (14, 1,  7, tThaumcraftTable, 1);
				aData.set  (14, 1,  8, tThaumcraftTable, 1);
				aData.shelf(14, 1,  9, 7046, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  (14, 1, 10, tThaumcraftTable, 1);
				
				if (aData.next1in4())   aData.coins(14, 2,  5);
										aData.set  (14, 2,  6, tThaumcraftCandle, aData.mColorInversed, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, 9);
										aData.cup  (14, 2,  7+aData.next(2), FL.Potion_NightVision_1L);
										aData.set  (14, 2,  9, tThaumcraftCandle, aData.mColorInversed, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, 9);
				if (aData.next1in4())   aData.coins(14, 2, 10);
			}
			if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
				aData.set  ( 1, 1,  5, tThaumcraftTable, 1);
				aData.shelf( 1, 1,  6, 7046, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 1, 1,  7, tThaumcraftTable, 1);
				aData.set  ( 1, 1,  8, tThaumcraftTable, 1);
				aData.shelf( 1, 1,  9, 7046, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 1, 1, 10, tThaumcraftTable, 1);
				
				if (aData.next1in4())   aData.coins( 1, 2,  5);
										aData.set  ( 1, 2,  6, tThaumcraftCandle, aData.mColorInversed, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, 9);
										aData.cup  ( 1, 2,  7+aData.next(2), FL.Potion_NightVision_1L);
										aData.set  ( 1, 2,  9, tThaumcraftCandle, aData.mColorInversed, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, 9);
				if (aData.next1in4())   aData.coins( 1, 2, 10);
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
				aData.set  ( 5, 1, 14, tThaumcraftTable);
				aData.shelf( 6, 1, 14, 7046, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 7, 1, 14, tThaumcraftTable);
				aData.set  ( 8, 1, 14, tThaumcraftTable);
				aData.shelf( 9, 1, 14, 7046, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  (10, 1, 14, tThaumcraftTable);
				
				if (aData.next1in4())   aData.coins( 5, 2, 14);
										aData.set  ( 6, 2, 14, tThaumcraftCandle, aData.mColorInversed, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, 9);
										aData.cup  ( 7+aData.next(2), 2, 14, FL.Potion_NightVision_1L);
										aData.set  ( 9, 2, 14, tThaumcraftCandle, aData.mColorInversed, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, 9);
				if (aData.next1in4())   aData.coins(10, 2, 14);
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
				aData.set  ( 5, 1,  1, tThaumcraftTable);
				aData.shelf( 6, 1,  1, 7046, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 7, 1,  1, tThaumcraftTable);
				aData.set  ( 8, 1,  1, tThaumcraftTable);
				aData.shelf( 9, 1,  1, 7046, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  (10, 1,  1, tThaumcraftTable);
				
				if (aData.next1in4())   aData.coins( 5, 2,  1);
										aData.set  ( 6, 2,  1, tThaumcraftCandle, aData.mColorInversed, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, 9);
										aData.cup  ( 7+aData.next(2), 2,  1, FL.Potion_NightVision_1L);
										aData.set  ( 9, 2,  1, tThaumcraftCandle, aData.mColorInversed, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, 9);
				if (aData.next1in4())   aData.coins(10, 2,  1);
			}
			break;
		case 1:
			if (!MD.MYST.mLoaded) break;
			tDidntGenerateSpecial = F;
			Block tWritingTable = IL.Myst_Desk_Block.block(), tBookBinder = IL.Myst_Book_Binder.block(), tInkMixer = IL.Myst_Ink_Mixer.block(), tLectern = IL.Myst_Lectern.block(), tStand = IL.Myst_Bookstand.block();
			boolean temp = T;
			if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
				if (temp) {
					temp = F;
					aData.set  (14, 1,  5, tInkMixer    , 0, 2, 1);
					aData.set  (14, 1,  6, 32705, FluidTankGT.writeToNBT(NBT_TANK+".out.0", FL.InkMyst.make(1000+1000*aData.next(8))));
					aData.set  (14, 1,  7, tWritingTable, 0);
					aData.set  (14, 1,  8, tWritingTable, 8);
					aData.shelf(14, 1,  9, tShelf, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.set  (14, 1, 10, tBookBinder  , 0, 2, 1);
					
					aData.set  (14, 2,  9, tLectern     , 0, 2, 1);
				} else {
					aData.shelf(14, 1,  5, tShelf, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.set  (14, 1,  6, tStand       , 0, 2, 2);
					aData.shelf(14, 1,  7, tShelf, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.shelf(14, 1,  8, tShelf, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.set  (14, 1,  9, tStand       , 0, 2, 2);
					aData.shelf(14, 1, 10, tShelf, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
					
					aData.set  (14, 2,  5, tLectern     , 0, 2, 1);
					aData.set  (14, 2,  7, tLectern     , 0, 2, 1);
					aData.set  (14, 2,  8, tLectern     , 0, 2, 1);
					aData.set  (14, 2, 10, tLectern     , 0, 2, 1);
				}
			}
			if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
				if (temp) {
					temp = F;
					aData.set  ( 1, 1,  5, tBookBinder  , 0, 2, 3);
					aData.shelf( 1, 1,  6, tShelf, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.set  ( 1, 1,  7, tWritingTable,10);
					aData.set  ( 1, 1,  8, tWritingTable, 2);
					aData.set  ( 1, 1,  9, 32705, FluidTankGT.writeToNBT(NBT_TANK+".out.0", FL.InkMyst.make(1000+1000*aData.next(8))));
					aData.set  ( 1, 1, 10, tInkMixer    , 0, 2, 3);
					
					aData.set  ( 1, 2,  6, tLectern     , 0, 2, 3);
				} else {
					aData.shelf( 1, 1,  5, tShelf, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.set  ( 1, 1,  6, tStand       , 0, 2, 6);
					aData.shelf( 1, 1,  7, tShelf, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.shelf( 1, 1,  8, tShelf, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.set  ( 1, 1,  9, tStand       , 0, 2, 6);
					aData.shelf( 1, 1, 10, tShelf, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
					
					aData.set  ( 1, 2,  5, tLectern     , 0, 2, 3);
					aData.set  ( 1, 2,  7, tLectern     , 0, 2, 3);
					aData.set  ( 1, 2,  8, tLectern     , 0, 2, 3);
					aData.set  ( 1, 2, 10, tLectern     , 0, 2, 3);
				}
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
				if (temp) {
					temp = F;
					aData.set  ( 5, 1, 14, tBookBinder  , 0, 2, 2);
					aData.shelf( 6, 1, 14, tShelf, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.set  ( 7, 1, 14, tWritingTable, 9);
					aData.set  ( 8, 1, 14, tWritingTable, 1);
					aData.set  ( 9, 1, 14, 32705, FluidTankGT.writeToNBT(NBT_TANK+".out.0", FL.InkMyst.make(1000+1000*aData.next(8))));
					aData.set  (10, 1, 14, tInkMixer    , 0, 2, 2);
					
					aData.set  ( 6, 2, 14, tLectern     , 0, 2, 2);
				} else {
					aData.shelf( 5, 1, 14, tShelf, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.set  ( 6, 1, 14, tStand       , 0, 2, 4);
					aData.shelf( 7, 1, 14, tShelf, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.shelf( 8, 1, 14, tShelf, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.set  ( 9, 1, 14, tStand       , 0, 2, 4);
					aData.shelf(10, 1, 14, tShelf, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
					
					aData.set  ( 5, 2, 14, tLectern     , 0, 2, 2);
					aData.set  ( 7, 2, 14, tLectern     , 0, 2, 2);
					aData.set  ( 8, 2, 14, tLectern     , 0, 2, 2);
					aData.set  (10, 2, 14, tLectern     , 0, 2, 2);
				}
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
				if (temp) {
					temp = F;
					aData.set  ( 5, 1,  1, tInkMixer    , 0, 2, 0);
					aData.set  ( 6, 1,  1, 32705, FluidTankGT.writeToNBT(NBT_TANK+".out.0", FL.InkMyst.make(1000+1000*aData.next(8))));
					aData.set  ( 7, 1,  1, tWritingTable, 3);
					aData.set  ( 8, 1,  1, tWritingTable,11);
					aData.shelf( 9, 1,  1, tShelf, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.set  (10, 1,  1, tBookBinder  , 0, 2, 0);
					
					aData.set  ( 9, 2,  1, tLectern     , 0, 2, 0);
				} else {
					aData.shelf( 5, 1,  1, tShelf, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.set  ( 6, 1,  1, tStand       , 0, 2, 0);
					aData.shelf( 7, 1,  1, tShelf, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.shelf( 8, 1,  1, tShelf, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
					aData.set  ( 9, 1,  1, tStand       , 0, 2, 0);
					aData.shelf(10, 1,  1, tShelf, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
					
					aData.set  ( 5, 2,  1, tLectern     , 0, 2, 0);
					aData.set  ( 7, 2,  1, tLectern     , 0, 2, 0);
					aData.set  ( 8, 2,  1, tLectern     , 0, 2, 0);
					aData.set  (10, 2,  1, tLectern     , 0, 2, 0);
				}
			}
			break;
		case 2:
			break;
		}
		
		if (tDidntGenerateSpecial) {
			if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
				aData.set  (14, 1,  5, Blocks.wooden_slab, tSlab);
				aData.shelf(14, 1,  6, tShelf, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  (14, 1,  7, Blocks.wooden_slab, tSlab);
				aData.set  (14, 1,  8, Blocks.wooden_slab, tSlab);
				aData.shelf(14, 1,  9, tShelf, SIDE_X_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  (14, 1, 10, Blocks.wooden_slab, tSlab);
				
				if (aData.next1in4()) aData.coins(14, 2,  5);
				if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm(14, 2,  6)) tDidntGenerateZPM = F; else aData.pot(14, 2,  6, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
				aData.cup(14, 2,  7+aData.next(2), FL.Potion_NightVision_1L);
				if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm(14, 2,  9)) tDidntGenerateZPM = F; else aData.pot(14, 2,  9, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
				if (aData.next1in4()) aData.coins(14, 2, 10);
			}
			if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
				aData.set  ( 1, 1,  5, Blocks.wooden_slab, tSlab);
				aData.shelf( 1, 1,  6, tShelf, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 1, 1,  7, Blocks.wooden_slab, tSlab);
				aData.set  ( 1, 1,  8, Blocks.wooden_slab, tSlab);
				aData.shelf( 1, 1,  9, tShelf, SIDE_X_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 1, 1, 10, Blocks.wooden_slab, tSlab);
				
				if (aData.next1in4()) aData.coins( 1, 2,  5);
				if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm( 1, 2,  6)) tDidntGenerateZPM = F; else aData.pot( 1, 2,  6, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
				aData.cup( 1, 2,  7+aData.next(2), FL.Potion_NightVision_1L);
				if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm( 1, 2,  9)) tDidntGenerateZPM = F; else aData.pot( 1, 2,  9, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
				if (aData.next1in4()) aData.coins( 1, 2, 10);
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
				aData.set  ( 5, 1, 14, Blocks.wooden_slab, tSlab);
				aData.shelf( 6, 1, 14, tShelf, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 7, 1, 14, Blocks.wooden_slab, tSlab);
				aData.set  ( 8, 1, 14, Blocks.wooden_slab, tSlab);
				aData.shelf( 9, 1, 14, tShelf, SIDE_Z_NEG, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  (10, 1, 14, Blocks.wooden_slab, tSlab);
				
				if (aData.next1in4()) aData.coins( 5, 2, 14);
				if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm( 6, 2, 14)) tDidntGenerateZPM = F; else aData.pot( 6, 2, 14, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
				aData.cup( 7+aData.next(2), 2, 14, FL.Potion_NightVision_1L);
				if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm( 9, 2, 14)) tDidntGenerateZPM = F; else aData.pot( 9, 2, 14, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
				if (aData.next1in4()) aData.coins(10, 2, 14);
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
				aData.set  ( 5, 1,  1, Blocks.wooden_slab, tSlab);
				aData.shelf( 6, 1,  1, tShelf, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  ( 7, 1,  1, Blocks.wooden_slab, tSlab);
				aData.set  ( 8, 1,  1, Blocks.wooden_slab, tSlab);
				aData.shelf( 9, 1,  1, tShelf, SIDE_Z_POS, ChestGenHooks.STRONGHOLD_LIBRARY);
				aData.set  (10, 1,  1, Blocks.wooden_slab, tSlab);
				
				if (aData.next1in4()) aData.coins( 5, 2,  1);
				if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm( 6, 2,  1)) tDidntGenerateZPM = F; else aData.pot( 6, 2,  1, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
				aData.cup( 7+aData.next(2), 2,  1, FL.Potion_NightVision_1L);
				if (tDidntGenerateZPM && aData.next(16)==0 && aData.zpm( 9, 2,  1)) tDidntGenerateZPM = F; else aData.pot( 9, 2,  1, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
				if (aData.next1in4()) aData.coins(10, 2,  1);
			}
		}
		
		switch(aData.next(4)) {
		case 0:
			aData.set( 3, 1,  3, Blocks.enchanting_table);
			aData.set( 3, 1, 12, Blocks.crafting_table);
			aData.set(12, 1,  3, Blocks.jukebox);
			aData.set(12, 1, 12, Blocks.ender_chest);
			aData.cup( 3, 2, 12, FL.Potion_NightVision_1L, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
			break;
		case 1:
			aData.set( 3, 1,  3, Blocks.ender_chest);
			aData.set( 3, 1, 12, Blocks.enchanting_table);
			aData.set(12, 1,  3, Blocks.crafting_table);
			aData.set(12, 1, 12, Blocks.jukebox);
			aData.cup(12, 2,  3, FL.Potion_NightVision_1L, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
			break;
		case 2:
			aData.set( 3, 1,  3, Blocks.jukebox);
			aData.set( 3, 1, 12, Blocks.ender_chest);
			aData.set(12, 1,  3, Blocks.enchanting_table);
			aData.set(12, 1, 12, Blocks.crafting_table);
			aData.cup(12, 2, 12, FL.Potion_NightVision_1L, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
			break;
		case 3:
			aData.set( 3, 1,  3, Blocks.crafting_table);
			aData.set( 3, 1, 12, Blocks.jukebox);
			aData.set(12, 1,  3, Blocks.ender_chest);
			aData.set(12, 1, 12, Blocks.enchanting_table);
			aData.cup( 3, 2,  3, FL.Potion_NightVision_1L, aData.next1in2() ? tHexoriumColor : tHexoriumRandom, aData.next1in2() ? 1 : 9);
			break;
		}
		return T;
	}
}
