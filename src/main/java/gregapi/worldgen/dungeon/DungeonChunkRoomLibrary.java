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
		int tWoodType = aData.mRandom.nextInt(6);
		
		String[] tLoots = new String[] {ChestGenHooks.STRONGHOLD_LIBRARY, ChestGenHooks.STRONGHOLD_CORRIDOR, ChestGenHooks.STRONGHOLD_CROSSING, ChestGenHooks.PYRAMID_DESERT_CHEST, ChestGenHooks.PYRAMID_JUNGLE_CHEST, ChestGenHooks.VILLAGE_BLACKSMITH, ChestGenHooks.MINESHAFT_CORRIDOR, ChestGenHooks.DUNGEON_CHEST, ChestGenHooks.BONUS_CHEST};
		
		for (int tX = 2; tX <= 13; tX++) for (int tZ = 2; tZ <= 13; tZ++) {
			aData.set(tX, 1, tZ, Blocks.carpet, aData.mColorInversed, 2);
		}
		
		for (int tX = 1; tX <= 14; tX++) for (int tZ = 1; tZ <= 14; tZ++) {
			if (tX == 1 || tX == 14 || tZ == 1 || tZ == 14) {
				aData.set(tX, 6, tZ, Blocks.planks, tWoodType, 2);
				aData.set(tX, 5, tZ, Blocks.wooden_slab, tWoodType+8, 2);
			} else if ((tX == 3 || tX == 6 || tX == 9 || tX == 12) && (tZ == 3 || tZ == 6 || tZ == 9 || tZ == 12)) {
				aData.smooth(tX, 7, tZ);
				aData.lamp(tX, 6, tZ, +1);
			} else {
				aData.set(tX, 6, tZ, Blocks.wooden_slab, tWoodType+8, 2);
			}
		}
		
		aData.set( 2, 3,  4, Blocks.wooden_slab, tWoodType+8, 2);
		aData.set( 2, 3,  3, Blocks.wooden_slab, tWoodType+8, 2);
		aData.set( 2, 3,  2, Blocks.planks, tWoodType, 2);
		aData.set( 3, 3,  2, Blocks.wooden_slab, tWoodType+8, 2);
		aData.set( 4, 3,  2, Blocks.wooden_slab, tWoodType+8, 2);
		
		aData.set(13, 3,  4, Blocks.wooden_slab, tWoodType+8, 2);
		aData.set(13, 3,  3, Blocks.wooden_slab, tWoodType+8, 2);
		aData.set(13, 3,  2, Blocks.planks, tWoodType, 2);
		aData.set(12, 3,  2, Blocks.wooden_slab, tWoodType+8, 2);
		aData.set(11, 3,  2, Blocks.wooden_slab, tWoodType+8, 2);
		
		aData.set( 2, 3, 11, Blocks.wooden_slab, tWoodType+8, 2);
		aData.set( 2, 3, 12, Blocks.wooden_slab, tWoodType+8, 2);
		aData.set( 2, 3, 13, Blocks.planks, tWoodType, 2);
		aData.set( 3, 3, 13, Blocks.wooden_slab, tWoodType+8, 2);
		aData.set( 4, 3, 13, Blocks.wooden_slab, tWoodType+8, 2);
		
		aData.set(13, 3, 11, Blocks.wooden_slab, tWoodType+8, 2);
		aData.set(13, 3, 12, Blocks.wooden_slab, tWoodType+8, 2);
		aData.set(13, 3, 13, Blocks.planks, tWoodType, 2);
		aData.set(12, 3, 13, Blocks.wooden_slab, tWoodType+8, 2);
		aData.set(11, 3, 13, Blocks.wooden_slab, tWoodType+8, 2);
		
		for (int tY = 1; tY <= 5; tY++) {
			aData.set( 1, tY,  1, Blocks.planks, tWoodType, 2);
			aData.set(14, tY,  1, Blocks.planks, tWoodType, 2);
			aData.set( 1, tY, 14, Blocks.planks, tWoodType, 2);
			aData.set(14, tY, 14, Blocks.planks, tWoodType, 2);
			
			if (tY == 3) {
				aData.set( 2, tY,  1, Blocks.planks, tWoodType, 2);
				aData.set( 3, tY,  1, Blocks.planks, tWoodType, 2);
				aData.set( 4, tY,  1, Blocks.planks, tWoodType, 2);
				aData.set(11, tY,  1, Blocks.planks, tWoodType, 2);
				aData.set(12, tY,  1, Blocks.planks, tWoodType, 2);
				aData.set(13, tY,  1, Blocks.planks, tWoodType, 2);
				
				aData.set(14, tY,  2, Blocks.planks, tWoodType, 2);
				aData.set(14, tY,  3, Blocks.planks, tWoodType, 2);
				aData.set(14, tY,  4, Blocks.planks, tWoodType, 2);
				aData.set(14, tY, 11, Blocks.planks, tWoodType, 2);
				aData.set(14, tY, 12, Blocks.planks, tWoodType, 2);
				aData.set(14, tY, 13, Blocks.planks, tWoodType, 2);
				
				aData.set( 2, tY, 14, Blocks.planks, tWoodType, 2);
				aData.set( 3, tY, 14, Blocks.planks, tWoodType, 2);
				aData.set( 4, tY, 14, Blocks.planks, tWoodType, 2);
				aData.set(11, tY, 14, Blocks.planks, tWoodType, 2);
				aData.set(12, tY, 14, Blocks.planks, tWoodType, 2);
				aData.set(13, tY, 14, Blocks.planks, tWoodType, 2);
				
				aData.set( 1, tY,  2, Blocks.planks, tWoodType, 2);
				aData.set( 1, tY,  3, Blocks.planks, tWoodType, 2);
				aData.set( 1, tY,  4, Blocks.planks, tWoodType, 2);
				aData.set( 1, tY, 11, Blocks.planks, tWoodType, 2);
				aData.set( 1, tY, 12, Blocks.planks, tWoodType, 2);
				aData.set( 1, tY, 13, Blocks.planks, tWoodType, 2);
			} else {
				int tIndex = aData.mRandom.nextInt(24), tKeyIndex = aData.mRandom.nextInt(aData.mGeneratedKeys.length * 2);
				NBTTagList tList = null;
				if (tKeyIndex < aData.mGeneratedKeys.length) {
					aData.mGeneratedKeys[tKeyIndex] = T;
					tList = new NBTTagList();
					tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[tKeyIndex]), "s", (short)aData.mRandom.nextInt(14)));
				}
				
				aData.set( 2, tY,  1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set( 3, tY,  1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set( 4, tY,  1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set(11, tY,  1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set(12, tY,  1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set(13, tY,  1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				
				aData.set(14, tY,  2, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set(14, tY,  3, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set(14, tY,  4, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set(14, tY, 11, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set(14, tY, 12, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set(14, tY, 13, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				
				aData.set( 2, tY, 14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set( 3, tY, 14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set( 4, tY, 14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set(11, tY, 14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set(12, tY, 14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set(13, tY, 14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				
				aData.set( 1, tY,  2, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set( 1, tY,  3, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set( 1, tY,  4, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set( 1, tY, 11, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set( 1, tY, 12, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.set( 1, tY, 13, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
			}
		}
		
		boolean tDidntGenerateSpecial = T, tDidntGenerateZPM = T;
		switch (aData.mRandom.nextInt(3)) {
		case 0:
			if (!MD.TC.mLoaded) break;
			tDidntGenerateSpecial = F;
			Block tThaumcraftTable = ST.block(MD.TC, "blockTable"), tThaumcraftCandle = ST.block(MD.TC, "blockCandle");
			if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
				aData.set   (14, 1,  5, tThaumcraftTable, 1, 2);
				aData.set   (14, 1,  6, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
				aData.set   (14, 1,  7, tThaumcraftTable, 1, 2);
				aData.set   (14, 1,  8, tThaumcraftTable, 1, 2);
				aData.set   (14, 1,  9, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
				aData.set   (14, 1, 10, tThaumcraftTable, 1, 2);
				
				if (aData.mRandom.nextInt(4)==0) aData.coins (14, 2,  5);
				aData.set   (14, 2,  6, tThaumcraftCandle, aData.mColorInversed, 2);
				aData.set   (14, 2,  7+aData.mRandom.nextInt(2), SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				aData.set   (14, 2,  9, tThaumcraftCandle, aData.mColorInversed, 2);
				if (aData.mRandom.nextInt(4)==0) aData.coins (14, 2, 10);
			}
			if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
				aData.set   ( 1, 1,  5, tThaumcraftTable, 1, 2);
				aData.set   ( 1, 1,  6, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
				aData.set   ( 1, 1,  7, tThaumcraftTable, 1, 2);
				aData.set   ( 1, 1,  8, tThaumcraftTable, 1, 2);
				aData.set   ( 1, 1,  9, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
				aData.set   ( 1, 1, 10, tThaumcraftTable, 1, 2);
				
				if (aData.mRandom.nextInt(4)==0) aData.coins ( 1, 2,  5);
				aData.set   ( 1, 2,  6, tThaumcraftCandle, aData.mColorInversed, 2);
				aData.set   ( 1, 2,  7+aData.mRandom.nextInt(2), SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				aData.set   ( 1, 2,  9, tThaumcraftCandle, aData.mColorInversed, 2);
				if (aData.mRandom.nextInt(4)==0) aData.coins ( 1, 2, 10);
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
				aData.set   ( 5, 1, 14, tThaumcraftTable, 0, 2);
				aData.set   ( 6, 1, 14, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
				aData.set   ( 7, 1, 14, tThaumcraftTable, 0, 2);
				aData.set   ( 8, 1, 14, tThaumcraftTable, 0, 2);
				aData.set   ( 9, 1, 14, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
				aData.set   (10, 1, 14, tThaumcraftTable, 0, 2);
				
				if (aData.mRandom.nextInt(4)==0) aData.coins ( 5, 2, 14);
				aData.set   ( 6, 2, 14, tThaumcraftCandle, aData.mColorInversed, 2);
				aData.set   ( 7+aData.mRandom.nextInt(2), 2, 14, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				aData.set   ( 9, 2, 14, tThaumcraftCandle, aData.mColorInversed, 2);
				if (aData.mRandom.nextInt(4)==0) aData.coins (10, 2, 14);
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
				aData.set   ( 5, 1,  1, tThaumcraftTable, 0, 2);
				aData.set   ( 6, 1,  1, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
				aData.set   ( 7, 1,  1, tThaumcraftTable, 0, 2);
				aData.set   ( 8, 1,  1, tThaumcraftTable, 0, 2);
				aData.set   ( 9, 1,  1, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
				aData.set   (10, 1,  1, tThaumcraftTable, 0, 2);
				
				if (aData.mRandom.nextInt(4)==0) aData.coins ( 5, 2,  1);
				aData.set   ( 6, 2,  1, tThaumcraftCandle, aData.mColorInversed, 2);
				aData.set   ( 7+aData.mRandom.nextInt(2), 2,  1, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				aData.set   ( 9, 2,  1, tThaumcraftCandle, aData.mColorInversed, 2);
				if (aData.mRandom.nextInt(4)==0) aData.coins (10, 2,  1);
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
					aData.set   (14, 1,  5, tInkMixer        , 0, 2, 1);
					aData.set   (14, 1,  6, SIDE_UNKNOWN, (short)32705, new FluidTankGT(FL.Myst_Ink.make(1000+1000*aData.mRandom.nextInt(8))).writeToNBT(UT.NBT.make(), NBT_TANK+".out.0"), T, T);
					aData.set   (14, 1,  7, tWritingTable    , 0, 2);
					aData.set   (14, 1,  8, tWritingTable    , 8, 2);
					aData.set   (14, 1,  9, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
					aData.set   (14, 1, 10, tBookBinder  , 0, 2, 1);
					
					aData.set   (14, 2,  9, tLectern     , 0, 2, 1);
				} else {
					aData.set   (14, 1,  5, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
					aData.set   (14, 1,  6, tStand           , 0, 2, 2);
					aData.set   (14, 1,  7, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
					aData.set   (14, 1,  8, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
					aData.set   (14, 1,  9, tStand           , 0, 2, 2);
					aData.set   (14, 1, 10, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
					
					aData.set   (14, 2,  5, tLectern     , 0, 2, 1);
					aData.set   (14, 2,  7, tLectern     , 0, 2, 1);
					aData.set   (14, 2,  8, tLectern     , 0, 2, 1);
					aData.set   (14, 2, 10, tLectern     , 0, 2, 1);
				}
			}
			if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
				if (temp) {
					temp = F;
					aData.set   ( 1, 1,  5, tBookBinder      , 0, 2, 3);
					aData.set   ( 1, 1,  6, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
					aData.set   ( 1, 1,  7, tWritingTable    ,10, 2);
					aData.set   ( 1, 1,  8, tWritingTable    , 2, 2);
					aData.set   ( 1, 1,  9, SIDE_UNKNOWN, (short)32705, UT.NBT.make(), T, T);
					aData.set   ( 1, 1, 10, tInkMixer        , 0, 2, 3);
					
					aData.set   ( 1, 2,  6, tLectern     , 0, 2, 3);
				} else {
					aData.set   ( 1, 1,  5, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
					aData.set   ( 1, 1,  6, tStand           , 0, 2, 6);
					aData.set   ( 1, 1,  7, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
					aData.set   ( 1, 1,  8, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
					aData.set   ( 1, 1,  9, tStand           , 0, 2, 6);
					aData.set   ( 1, 1, 10, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
					
					aData.set   ( 1, 2,  5, tLectern     , 0, 2, 3);
					aData.set   ( 1, 2,  7, tLectern     , 0, 2, 3);
					aData.set   ( 1, 2,  8, tLectern     , 0, 2, 3);
					aData.set   ( 1, 2, 10, tLectern     , 0, 2, 3);
				}
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
				if (temp) {
					temp = F;
					aData.set   ( 5, 1, 14, tBookBinder  , 0, 2, 2);
					aData.set   ( 6, 1, 14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
					aData.set   ( 7, 1, 14, tWritingTable    , 9, 2);
					aData.set   ( 8, 1, 14, tWritingTable    , 1, 2);
					aData.set   ( 9, 1, 14, SIDE_UNKNOWN, (short)32705, UT.NBT.make(), T, T);
					aData.set   (10, 1, 14, tInkMixer        , 0, 2, 2);
					
					aData.set   ( 6, 2, 14, tLectern     , 0, 2, 2);
				} else {
					aData.set   ( 5, 1, 14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
					aData.set   ( 6, 1, 14, tStand           , 0, 2, 4);
					aData.set   ( 7, 1, 14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
					aData.set   ( 8, 1, 14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
					aData.set   ( 9, 1, 14, tStand           , 0, 2, 4);
					aData.set   (10, 1, 14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
					
					aData.set   ( 5, 2, 14, tLectern     , 0, 2, 2);
					aData.set   ( 7, 2, 14, tLectern     , 0, 2, 2);
					aData.set   ( 8, 2, 14, tLectern     , 0, 2, 2);
					aData.set   (10, 2, 14, tLectern     , 0, 2, 2);
				}
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
				if (temp) {
					temp = F;
					aData.set   ( 5, 1,  1, tInkMixer        , 0, 2, 0);
					aData.set   ( 6, 1,  1, SIDE_UNKNOWN, (short)32705, UT.NBT.make(), T, T);
					aData.set   ( 7, 1,  1, tWritingTable    , 3, 2);
					aData.set   ( 8, 1,  1, tWritingTable    ,11, 2);
					aData.set   ( 9, 1,  1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
					aData.set   (10, 1,  1, tBookBinder  , 0, 2, 0);
					
					aData.set   ( 9, 2,  1, tLectern     , 0, 2, 0);
				} else {
					aData.set   ( 5, 1,  1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
					aData.set   ( 6, 1,  1, tStand           , 0, 2, 0);
					aData.set   ( 7, 1,  1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
					aData.set   ( 8, 1,  1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
					aData.set   ( 9, 1,  1, tStand           , 0, 2, 0);
					aData.set   (10, 1,  1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
					
					aData.set   ( 5, 2,  1, tLectern     , 0, 2, 0);
					aData.set   ( 7, 2,  1, tLectern     , 0, 2, 0);
					aData.set   ( 8, 2,  1, tLectern     , 0, 2, 0);
					aData.set   (10, 2,  1, tLectern     , 0, 2, 0);
				}
			}
			break;
		case 2:
			break;
		}
		
		if (tDidntGenerateSpecial) {
			if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
				aData.set   (14, 1,  5, Blocks.wooden_slab, tWoodType+8, 2);
				aData.set   (14, 1,  6, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
				aData.set   (14, 1,  7, Blocks.wooden_slab, tWoodType+8, 2);
				aData.set   (14, 1,  8, Blocks.wooden_slab, tWoodType+8, 2);
				aData.set   (14, 1,  9, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
				aData.set   (14, 1, 10, Blocks.wooden_slab, tWoodType+8, 2);
				
				if (aData.mRandom.nextInt(4)==0) aData.coins (14, 2,  5);
				if (tDidntGenerateZPM && aData.mRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.set   (14, 2,  6, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aData.mRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else aData.pot                 (14, 2,  6);
				aData.set   (14, 2,  7+aData.mRandom.nextInt(2), SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				if (tDidntGenerateZPM && aData.mRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.set   (14, 2,  9, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aData.mRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else aData.pot                 (14, 2,  9);
				if (aData.mRandom.nextInt(4)==0) aData.coins (14, 2, 10);
			}
			if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
				aData.set   ( 1, 1,  5, Blocks.wooden_slab, tWoodType+8, 2);
				aData.set   ( 1, 1,  6, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
				aData.set   ( 1, 1,  7, Blocks.wooden_slab, tWoodType+8, 2);
				aData.set   ( 1, 1,  8, Blocks.wooden_slab, tWoodType+8, 2);
				aData.set   ( 1, 1,  9, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
				aData.set   ( 1, 1, 10, Blocks.wooden_slab, tWoodType+8, 2);
				
				if (aData.mRandom.nextInt(4)==0) aData.coins ( 1, 2,  5);
				if (tDidntGenerateZPM && aData.mRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.set   ( 1, 2,  6, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aData.mRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else aData.pot                 ( 1, 2,  6);
				aData.set   ( 1, 2,  7+aData.mRandom.nextInt(2), SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				if (tDidntGenerateZPM && aData.mRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.set   ( 1, 2,  9, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aData.mRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else aData.pot                 ( 1, 2,  9);
				if (aData.mRandom.nextInt(4)==0) aData.coins ( 1, 2, 10);
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
				aData.set   ( 5, 1, 14, Blocks.wooden_slab, tWoodType+8, 2);
				aData.set   ( 6, 1, 14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
				aData.set   ( 7, 1, 14, Blocks.wooden_slab, tWoodType+8, 2);
				aData.set   ( 8, 1, 14, Blocks.wooden_slab, tWoodType+8, 2);
				aData.set   ( 9, 1, 14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
				aData.set   (10, 1, 14, Blocks.wooden_slab, tWoodType+8, 2);
				
				if (aData.mRandom.nextInt(4)==0) aData.coins ( 5, 2, 14);
				if (tDidntGenerateZPM && aData.mRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.set   ( 6, 2, 14, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aData.mRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else aData.pot                 ( 6, 2, 14);
				aData.set   ( 7+aData.mRandom.nextInt(2), 2, 14, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				if (tDidntGenerateZPM && aData.mRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.set   ( 9, 2, 14, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aData.mRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else aData.pot                 ( 9, 2, 14);
				if (aData.mRandom.nextInt(4)==0) aData.coins (10, 2, 14);
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
				aData.set   ( 5, 1,  1, Blocks.wooden_slab, tWoodType+8, 2);
				aData.set   ( 6, 1,  1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
				aData.set   ( 7, 1,  1, Blocks.wooden_slab, tWoodType+8, 2);
				aData.set   ( 8, 1,  1, Blocks.wooden_slab, tWoodType+8, 2);
				aData.set   ( 9, 1,  1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
				aData.set   (10, 1,  1, Blocks.wooden_slab, tWoodType+8, 2);
				
				if (aData.mRandom.nextInt(4)==0) aData.coins ( 5, 2,  1);
				if (tDidntGenerateZPM && aData.mRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.set   ( 6, 2,  1, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aData.mRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else aData.pot                 ( 6, 2,  1);
				aData.set   ( 7+aData.mRandom.nextInt(2), 2,  1, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				if (tDidntGenerateZPM && aData.mRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.set   ( 9, 2,  1, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aData.mRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else aData.pot                 ( 9, 2,  1);
				if (aData.mRandom.nextInt(4)==0) aData.coins (10, 2,  1);
			}
		}
		
		switch(aData.mRandom.nextInt(4)) {
		case 0:
			aData.set ( 3, 1,  3, Blocks.enchanting_table, 0, 2);
			aData.set ( 3, 1, 12, Blocks.crafting_table, 0, 2);
			aData.set (12, 1,  3, Blocks.jukebox, 0, 2);
			aData.set (12, 1, 12, Blocks.ender_chest, 0, 2);
			aData.set   ( 3, 2, 12, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			break;
		case 1:
			aData.set ( 3, 1,  3, Blocks.ender_chest, 0, 2);
			aData.set ( 3, 1, 12, Blocks.enchanting_table, 0, 2);
			aData.set (12, 1,  3, Blocks.crafting_table, 0, 2);
			aData.set (12, 1, 12, Blocks.jukebox, 0, 2);
			aData.set   (12, 2,  3, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			break;
		case 2:
			aData.set ( 3, 1,  3, Blocks.jukebox, 0, 2);
			aData.set ( 3, 1, 12, Blocks.ender_chest, 0, 2);
			aData.set (12, 1,  3, Blocks.enchanting_table, 0, 2);
			aData.set (12, 1, 12, Blocks.crafting_table, 0, 2);
			aData.set   (12, 2, 12, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			break;
		case 3:
			aData.set ( 3, 1,  3, Blocks.crafting_table, 0, 2);
			aData.set ( 3, 1, 12, Blocks.jukebox, 0, 2);
			aData.set (12, 1,  3, Blocks.ender_chest, 0, 2);
			aData.set (12, 1, 12, Blocks.enchanting_table, 0, 2);
			aData.set   ( 3, 2,  3, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			break;
		}
		return T;
	}
}
