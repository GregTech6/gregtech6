/**
 * Copyright (c) 2024 GregTech-6 Team
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

import gregapi.data.FL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ChestGenHooks;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkBarracks extends DungeonChunkRoomEmpty {
	@Override
	public boolean generate(DungeonData aData) {
		if (!super.generate(aData)) return F;
		
		for (int tX = 1; tX <= 14; tX++) for (int tZ = 1; tZ <= 14; tZ++) if ((tX <= 4 || tX >= 11) && (tZ <= 4 || tZ >= 11)) {
			aData.set(tX, 1, tZ, Blocks.carpet, aData.mColorInversed, 2);
		}
		for (int tY = 1; tY <=  6; tY++) {
			for (int tCoord = 1; tCoord <= 14; tCoord++) if (tCoord <= 3 || tCoord >= 12) {
				aData.bricks(tCoord, tY,  5, aData.mPrimary.mSlabs[SIDE_Z_NEG], aData.mSecondary.mSlabs[SIDE_Z_NEG]);
				aData.bricks(tCoord, tY, 10, aData.mPrimary.mSlabs[SIDE_Z_POS], aData.mSecondary.mSlabs[SIDE_Z_POS]);
				aData.bricks( 5, tY, tCoord, aData.mPrimary.mSlabs[SIDE_X_NEG], aData.mSecondary.mSlabs[SIDE_X_NEG]);
				aData.bricks(10, tY, tCoord, aData.mPrimary.mSlabs[SIDE_X_POS], aData.mSecondary.mSlabs[SIDE_X_POS]);
			}
			
			aData.smooth( 4, tY,  5);
			aData.smooth( 5, tY,  4);
			aData.smooth( 5, tY,  5);
			aData.smooth( 4, tY, 10);
			aData.smooth( 5, tY, 10);
			aData.smooth( 5, tY, 11);
			aData.smooth(10, tY,  4);
			aData.smooth(10, tY,  5);
			aData.smooth(11, tY,  5);
			aData.smooth(10, tY, 10);
			aData.smooth(10, tY, 11);
			aData.smooth(11, tY, 10);
		}
		
		aData.set( 3, 1,  5, Blocks.iron_door, 1, 2);
		aData.set( 3, 2,  5, Blocks.iron_door, 8, 2);
		aData.set(12, 1,  5, Blocks.iron_door, 1, 2);
		aData.set(12, 2,  5, Blocks.iron_door, 9, 2);
		aData.set( 3, 1, 10, Blocks.iron_door, 3, 2);
		aData.set( 3, 2, 10, Blocks.iron_door, 9, 2);
		aData.set(12, 1, 10, Blocks.iron_door, 3, 2);
		aData.set(12, 2, 10, Blocks.iron_door, 8, 2);
		aData.set( 4, 2,  6, Blocks.stone_button, 3, 2);
		aData.set(11, 2,  6, Blocks.stone_button, 3, 2);
		aData.set( 4, 2,  9, Blocks.stone_button, 4, 2);
		aData.set(11, 2,  9, Blocks.stone_button, 4, 2);
		aData.set( 3, 1,  4, Blocks.stone_pressure_plate, 0, 2);
		aData.set(12, 1,  4, Blocks.stone_pressure_plate, 0, 2);
		aData.set( 3, 1, 11, Blocks.stone_pressure_plate, 0, 2);
		aData.set(12, 1, 11, Blocks.stone_pressure_plate, 0, 2);
		aData.set( 1, 1,  1, Blocks.bed,10, 2);
		aData.set( 1, 1,  2, Blocks.bed, 2, 2);
		aData.set( 1, 1, 13, Blocks.bed, 0, 2);
		aData.set( 1, 1, 14, Blocks.bed, 8, 2);
		aData.set(14, 1,  1, Blocks.bed,10, 2);
		aData.set(14, 1,  2, Blocks.bed, 2, 2);
		aData.set(14, 1, 13, Blocks.bed, 0, 2);
		aData.set(14, 1, 14, Blocks.bed, 8, 2);
		aData.set( 1, 1,  4, Blocks.crafting_table, 0, 2);
		aData.set( 1, 1, 11, Blocks.crafting_table, 0, 2);
		aData.set(14, 1,  4, Blocks.crafting_table, 0, 2);
		aData.set(14, 1, 11, Blocks.crafting_table, 0, 2);
		
		FL[] tDrinks = new FL[] {null, null, null, null, null, FL.Purple_Drink, FL.Grenade_Juice, FL.Vodka, FL.Leninade, FL.Mead, FL.BAWLS, FL.Whiskey_GlenMcKenner, FL.Wine_Grape_Purple};
		
		Block
		tHexoriumColor  = ST.block(MD.HEX, UT.Code.select(aData.mColor, "blockEnergizedHexoriumMonolithRainbow", HEXORIUM_MONOLITHS)),
		tHexoriumRandom = ST.block(MD.HEX, UT.Code.select(              "blockEnergizedHexoriumMonolithRainbow", HEXORIUM_MONOLITHS));
		
		aData.cup( 1, 2,  4, UT.Code.select(null, tDrinks), aData.next1in2() ? tHexoriumColor : tHexoriumRandom, 9);
		aData.cup( 1, 2, 11, UT.Code.select(null, tDrinks), aData.next1in2() ? tHexoriumColor : tHexoriumRandom, 9);
		aData.cup(14, 2,  4, UT.Code.select(null, tDrinks), aData.next1in2() ? tHexoriumColor : tHexoriumRandom, 9);
		aData.set(14, 2, 11, SIDE_UNKNOWN, 32074, ST.save(NBT_VALUE, OP.rockGt.mat(MT.STONES.SkyStone, 16+aData.next(49))), T, T);// You need 4 Blocks of Sky Stone to start an AE System, so these 16+ Rocks will guarantee that you have enough, even when AE Meteors are disabled, or if AE got added to the Modpack later.
		
		String[] tLoots = new String[] {ChestGenHooks.STRONGHOLD_LIBRARY, ChestGenHooks.STRONGHOLD_CORRIDOR, ChestGenHooks.STRONGHOLD_CROSSING, ChestGenHooks.PYRAMID_DESERT_CHEST, ChestGenHooks.PYRAMID_JUNGLE_CHEST, ChestGenHooks.VILLAGE_BLACKSMITH, ChestGenHooks.MINESHAFT_CORRIDOR, ChestGenHooks.DUNGEON_CHEST, ChestGenHooks.BONUS_CHEST};
		
		
		NBTTagList
		tList = new NBTTagList();
		if (!aData.mGeneratedKeys[0]) {
			aData.mGeneratedKeys[0] = T;
			short tKeySlot = (short)aData.next(28);
			tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[0]), "s", tKeySlot));
			// Hint that there is something behind the Shelf.
			if (tKeySlot >= 14) {aData.cobble( 3, 1, 0); aData.cobble( 3, 2, 0); aData.cobble( 3, 3, 0);}
		}
		aData.set( 4, 1,  1, SIDE_UNKNOWN,  3010, UT.NBT.make("gt.dungeonloot"      , UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_KEY, aData.mKeyIDs[0]), T, T);
		aData.set( 3, 1,  1, SIDE_UNKNOWN,  7110, UT.NBT.make("gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tList), T, T);
		
		tList = new NBTTagList();
		if (!aData.mGeneratedKeys[1]) {
			aData.mGeneratedKeys[1] = T;
			short tKeySlot = (short)aData.next(28);
			tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[1]), "s", tKeySlot));
			// Hint that there is something behind the Shelf.
			if (tKeySlot >= 14) {aData.cobble( 3, 1,15); aData.cobble( 3, 2,15); aData.cobble( 3, 3,15);}
		}
		aData.set( 4, 1, 14, SIDE_UNKNOWN,  3010, UT.NBT.make("gt.dungeonloot"      , UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_KEY, aData.mKeyIDs[1]), T, T);
		aData.set( 3, 1, 14, SIDE_UNKNOWN,  7110, UT.NBT.make("gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tList), T, T);
		
		tList = new NBTTagList();
		if (!aData.mGeneratedKeys[3]) {
			aData.mGeneratedKeys[3] = T;
			short tKeySlot = (short)aData.next(28);
			tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[3]), "s", tKeySlot));
			// Hint that there is something behind the Shelf.
			if (tKeySlot >= 14) {aData.cobble(12, 1, 0); aData.cobble(12, 2, 0); aData.cobble(12, 3, 0);}
		}
		aData.set(11, 1,  1, SIDE_UNKNOWN,  3010, UT.NBT.make("gt.dungeonloot"      , UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_KEY, aData.mKeyIDs[3]), T, T);
		aData.set(12, 1,  1, SIDE_UNKNOWN,  7110, UT.NBT.make("gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tList), T, T);
		
		tList = new NBTTagList();
		if (!aData.mGeneratedKeys[4]) {
			aData.mGeneratedKeys[4] = T;
			short tKeySlot = (short)aData.next(28);
			tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[4]), "s", tKeySlot));
			// Hint that there is something behind the Shelf.
			if (tKeySlot >= 14) {aData.cobble(12, 1,15); aData.cobble(12, 2,15); aData.cobble(12, 3,15);}
		}
		aData.set(11, 1, 14, SIDE_UNKNOWN,  3010, UT.NBT.make("gt.dungeonloot"      , UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_KEY, aData.mKeyIDs[4]), T, T);
		aData.set(12, 1, 14, SIDE_UNKNOWN,  7110, UT.NBT.make("gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tList), T, T);
		
		if (aData.next1in2()) aData.coins( 4, 2,  1);
		if (aData.next1in2()) aData.coins( 4, 2, 14);
		if (aData.next1in2()) aData.coins(11, 2,  1);
		if (aData.next1in2()) aData.coins(11, 2, 14);
		
		return T;
	}
}
