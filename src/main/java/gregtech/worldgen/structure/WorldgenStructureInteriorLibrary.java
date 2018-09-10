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
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.fluid.FluidTankGT;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStructureInteriorLibrary extends WorldgenStructure {
	public static boolean generate(World aWorld, Random aRandom, int aChunkX, int aChunkZ, StructureData aData) {
		int tWoodType = aRandom.nextInt(6);
		
		String[] tLoots = new String[] {ChestGenHooks.STRONGHOLD_LIBRARY, ChestGenHooks.STRONGHOLD_CORRIDOR, ChestGenHooks.STRONGHOLD_CROSSING, ChestGenHooks.PYRAMID_DESERT_CHEST, ChestGenHooks.PYRAMID_JUNGLE_CHEST, ChestGenHooks.VILLAGE_BLACKSMITH, ChestGenHooks.MINESHAFT_CORRIDOR, ChestGenHooks.DUNGEON_CHEST, ChestGenHooks.BONUS_CHEST};
		
		for (int tX = 2; tX <= 13; tX++) for (int tZ = 2; tZ <= 13; tZ++) {
			setBlock(aWorld, aChunkX+tX, aData.mOffsetY+1, aChunkZ+tZ, Blocks.carpet, aData.mColorInversed, 2);
		}
		
		for (int tX = 1; tX <= 14; tX++) for (int tZ = 1; tZ <= 14; tZ++) {
			if (tX == 1 || tX == 14 || tZ == 1 || tZ == 14) {
				setBlock(aWorld, aChunkX+tX, aData.mOffsetY+6, aChunkZ+tZ, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+tX, aData.mOffsetY+5, aChunkZ+tZ, Blocks.wooden_slab, tWoodType+8, 2);
			} else if ((tX == 3 || tX == 6 || tX == 9 || tX == 12) && (tZ == 3 || tZ == 6 || tZ == 9 || tZ == 12)) {
				setSmoothBlock(aWorld, aChunkX+tX, aData.mOffsetY+7, aChunkZ+tZ, aData, aRandom);
				setLampBlock(aWorld, aChunkX+tX, aData.mOffsetY+6, aChunkZ+tZ, aData, aRandom, +1);
			} else {
				setBlock(aWorld, aChunkX+tX, aData.mOffsetY+6, aChunkZ+tZ, Blocks.wooden_slab, tWoodType+8, 2);
			}
		}
		
		setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+ 4, Blocks.wooden_slab, tWoodType+8, 2);
		setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+ 3, Blocks.wooden_slab, tWoodType+8, 2);
		setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+ 2, Blocks.planks, tWoodType, 2);
		setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+3, aChunkZ+ 2, Blocks.wooden_slab, tWoodType+8, 2);
		setBlock(aWorld, aChunkX+ 4, aData.mOffsetY+3, aChunkZ+ 2, Blocks.wooden_slab, tWoodType+8, 2);
		
		setBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+ 4, Blocks.wooden_slab, tWoodType+8, 2);
		setBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+ 3, Blocks.wooden_slab, tWoodType+8, 2);
		setBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+ 2, Blocks.planks, tWoodType, 2);
		setBlock(aWorld, aChunkX+12, aData.mOffsetY+3, aChunkZ+ 2, Blocks.wooden_slab, tWoodType+8, 2);
		setBlock(aWorld, aChunkX+11, aData.mOffsetY+3, aChunkZ+ 2, Blocks.wooden_slab, tWoodType+8, 2);
		
		setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+11, Blocks.wooden_slab, tWoodType+8, 2);
		setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+12, Blocks.wooden_slab, tWoodType+8, 2);
		setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+3, aChunkZ+13, Blocks.planks, tWoodType, 2);
		setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+3, aChunkZ+13, Blocks.wooden_slab, tWoodType+8, 2);
		setBlock(aWorld, aChunkX+ 4, aData.mOffsetY+3, aChunkZ+13, Blocks.wooden_slab, tWoodType+8, 2);
		
		setBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+11, Blocks.wooden_slab, tWoodType+8, 2);
		setBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+12, Blocks.wooden_slab, tWoodType+8, 2);
		setBlock(aWorld, aChunkX+13, aData.mOffsetY+3, aChunkZ+13, Blocks.planks, tWoodType, 2);
		setBlock(aWorld, aChunkX+12, aData.mOffsetY+3, aChunkZ+13, Blocks.wooden_slab, tWoodType+8, 2);
		setBlock(aWorld, aChunkX+11, aData.mOffsetY+3, aChunkZ+13, Blocks.wooden_slab, tWoodType+8, 2);
		
		for (int tY = 1; tY <= 5; tY++) {
			setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+ 1, Blocks.planks, tWoodType, 2);
			setBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+ 1, Blocks.planks, tWoodType, 2);
			setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+14, Blocks.planks, tWoodType, 2);
			setBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+14, Blocks.planks, tWoodType, 2);
			
			if (tY == 3) {
				setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+tY, aChunkZ+ 1, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+tY, aChunkZ+ 1, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+ 4, aData.mOffsetY+tY, aChunkZ+ 1, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+11, aData.mOffsetY+tY, aChunkZ+ 1, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+12, aData.mOffsetY+tY, aChunkZ+ 1, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+13, aData.mOffsetY+tY, aChunkZ+ 1, Blocks.planks, tWoodType, 2);
				
				setBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+ 2, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+ 3, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+ 4, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+11, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+12, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+13, Blocks.planks, tWoodType, 2);
				
				setBlock(aWorld, aChunkX+ 2, aData.mOffsetY+tY, aChunkZ+14, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+ 3, aData.mOffsetY+tY, aChunkZ+14, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+ 4, aData.mOffsetY+tY, aChunkZ+14, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+11, aData.mOffsetY+tY, aChunkZ+14, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+12, aData.mOffsetY+tY, aChunkZ+14, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+13, aData.mOffsetY+tY, aChunkZ+14, Blocks.planks, tWoodType, 2);
				
				setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+ 2, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+ 3, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+ 4, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+11, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+12, Blocks.planks, tWoodType, 2);
				setBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+13, Blocks.planks, tWoodType, 2);
			} else {
				int tIndex = aRandom.nextInt(24), tKeyIndex = aRandom.nextInt(aData.mGeneratedKeys.length * 2);
				NBTTagList tList = null;
				if (tKeyIndex < aData.mGeneratedKeys.length) {
					aData.mGeneratedKeys[tKeyIndex] = T;
					tList = new NBTTagList();
					tList.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[tKeyIndex]), "s", (short)aRandom.nextInt(14)));
				}
				
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+tY, aChunkZ+ 1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 3, aData.mOffsetY+tY, aChunkZ+ 1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 4, aData.mOffsetY+tY, aChunkZ+ 1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+11, aData.mOffsetY+tY, aChunkZ+ 1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+12, aData.mOffsetY+tY, aChunkZ+ 1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+13, aData.mOffsetY+tY, aChunkZ+ 1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+ 2, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+ 3, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+ 4, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+11, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+12, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+14, aData.mOffsetY+tY, aChunkZ+13, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 2, aData.mOffsetY+tY, aChunkZ+14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 3, aData.mOffsetY+tY, aChunkZ+14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 4, aData.mOffsetY+tY, aChunkZ+14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+11, aData.mOffsetY+tY, aChunkZ+14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+12, aData.mOffsetY+tY, aChunkZ+14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+13, aData.mOffsetY+tY, aChunkZ+14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_Z_NEG, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+ 2, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+ 3, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+ 4, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+11, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+12, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
				aData.mRegistry.mBlock.placeBlock(aWorld, aChunkX+ 1, aData.mOffsetY+tY, aChunkZ+13, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", UT.Code.select("", tLoots), NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tIndex--==0?tList:null), T, T);
			}
		}
		
		boolean tDidntGenerateSpecial = T, tDidntGenerateZPM = T;
		switch (aRandom.nextInt(3)) {
		case 0:
			if (!MD.TC.mLoaded) break;
			tDidntGenerateSpecial = F;
			Block tThaumcraftTable = ST.block(MD.TC, "blockTable"), tThaumcraftCandle = ST.block(MD.TC, "blockCandle");
			if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
				setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 5, tThaumcraftTable, 1, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 6, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
				setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 7, tThaumcraftTable, 1, 2);
				setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 8, tThaumcraftTable, 1, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 9, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
				setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+10, tThaumcraftTable, 1, 2);
				
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 5, aData, aRandom);
				setBlock							(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 6, tThaumcraftCandle, aData.mColorInversed, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 7+aRandom.nextInt(2), SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				setBlock							(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 9, tThaumcraftCandle, aData.mColorInversed, 2);
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+10, aData, aRandom);
			}
			if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
				setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 5, tThaumcraftTable, 1, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 6, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
				setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 7, tThaumcraftTable, 1, 2);
				setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 8, tThaumcraftTable, 1, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 9, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
				setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+10, tThaumcraftTable, 1, 2);
				
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 5, aData, aRandom);
				setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 6, tThaumcraftCandle, aData.mColorInversed, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 7+aRandom.nextInt(2), SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 9, tThaumcraftCandle, aData.mColorInversed, 2);
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+10, aData, aRandom);
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
				setBlock							(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+14, tThaumcraftTable, 0, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
				setBlock							(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+14, tThaumcraftTable, 0, 2);
				setBlock							(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+14, tThaumcraftTable, 0, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
				setBlock							(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+14, tThaumcraftTable, 0, 2);
				
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+14, aData, aRandom);
				setBlock							(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+14, tThaumcraftCandle, aData.mColorInversed, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 7+aRandom.nextInt(2), aData.mOffsetY+2, aChunkZ+14, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				setBlock							(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+14, tThaumcraftCandle, aData.mColorInversed, 2);
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+14, aData, aRandom);
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
				setBlock							(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 1, tThaumcraftTable, 0, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
				setBlock							(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 1, tThaumcraftTable, 0, 2);
				setBlock							(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 1, tThaumcraftTable, 0, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short) 7046, UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
				setBlock							(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 1, tThaumcraftTable, 0, 2);
				
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 1, aData, aRandom);
				setBlock							(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+ 1, tThaumcraftCandle, aData.mColorInversed, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 7+aRandom.nextInt(2), aData.mOffsetY+2, aChunkZ+ 1, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				setBlock							(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+ 1, tThaumcraftCandle, aData.mColorInversed, 2);
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+ 1, aData, aRandom);
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
					setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 5, tInkMixer		, 0, 2, 1);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 6, SIDE_UNKNOWN, (short)32705, new FluidTankGT(FL.Myst_Ink.make(1000+1000*aRandom.nextInt(8))).writeToNBT(UT.NBT.make(), NBT_TANK+".out.0"), T, T);
					setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 7, tWritingTable	, 0, 2);
					setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 8, tWritingTable	, 8, 2);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 9, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
					setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+10, tBookBinder	, 0, 2, 1);
					
					setBlock							(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 9, tLectern		, 0, 2, 1);
				} else {
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 5, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
					setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 6, tStand			, 0, 2, 2);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 7, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 8, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
					setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 9, tStand			, 0, 2, 2);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+10, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
					
					setBlock							(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 5, tLectern		, 0, 2, 1);
					setBlock							(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 7, tLectern		, 0, 2, 1);
					setBlock							(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 8, tLectern		, 0, 2, 1);
					setBlock							(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+10, tLectern		, 0, 2, 1);
				}
			}
			if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
				if (temp) {
					temp = F;
					setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 5, tBookBinder		, 0, 2, 3);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 6, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
					setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 7, tWritingTable	,10, 2);
					setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 8, tWritingTable	, 2, 2);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 9, SIDE_UNKNOWN, (short)32705, UT.NBT.make(), T, T);
					setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+10, tInkMixer		, 0, 2, 3);
					
					setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 6, tLectern		, 0, 2, 3);
				} else {
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 5, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
					setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 6, tStand			, 0, 2, 6);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 7, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 8, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
					setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 9, tStand			, 0, 2, 6);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+10, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
					
					setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 5, tLectern		, 0, 2, 3);
					setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 7, tLectern		, 0, 2, 3);
					setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 8, tLectern		, 0, 2, 3);
					setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+10, tLectern		, 0, 2, 3);
				}
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
				if (temp) {
					temp = F;
					setBlock							(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+14, tBookBinder	, 0, 2, 2);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
					setBlock							(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+14, tWritingTable	, 9, 2);
					setBlock							(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+14, tWritingTable	, 1, 2);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short)32705, UT.NBT.make(), T, T);
					setBlock							(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+14, tInkMixer		, 0, 2, 2);
					
					setBlock							(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+14, tLectern		, 0, 2, 2);
				} else {
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
					setBlock							(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+14, tStand			, 0, 2, 4);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
					setBlock							(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+14, tStand			, 0, 2, 4);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
					
					setBlock							(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+14, tLectern		, 0, 2, 2);
					setBlock							(aWorld, aChunkX+ 7, aData.mOffsetY+2, aChunkZ+14, tLectern		, 0, 2, 2);
					setBlock							(aWorld, aChunkX+ 8, aData.mOffsetY+2, aChunkZ+14, tLectern		, 0, 2, 2);
					setBlock							(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+14, tLectern		, 0, 2, 2);
				}
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
				if (temp) {
					temp = F;
					setBlock							(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 1, tInkMixer		, 0, 2, 0);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short)32705, UT.NBT.make(), T, T);
					setBlock							(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 1, tWritingTable	, 3, 2);
					setBlock							(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 1, tWritingTable	,11, 2);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
					setBlock							(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 1, tBookBinder	, 0, 2, 0);
					
					setBlock							(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+ 1, tLectern		, 0, 2, 0);
				} else {
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
					setBlock							(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 1, tStand			, 0, 2, 0);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
					setBlock							(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 1, tStand			, 0, 2, 0);
					aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
					
					setBlock							(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 1, tLectern		, 0, 2, 0);
					setBlock							(aWorld, aChunkX+ 7, aData.mOffsetY+2, aChunkZ+ 1, tLectern		, 0, 2, 0);
					setBlock							(aWorld, aChunkX+ 8, aData.mOffsetY+2, aChunkZ+ 1, tLectern		, 0, 2, 0);
					setBlock							(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+ 1, tLectern		, 0, 2, 0);
				}
			}
			break;
		case 2:
			break;
		}
		
		if (tDidntGenerateSpecial) {
			if (aData.mRoomLayout[aData.mRoomX+1][aData.mRoomZ] == 0) {
				setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 5, Blocks.wooden_slab, tWoodType+8, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 6, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
				setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 7, Blocks.wooden_slab, tWoodType+8, 2);
				setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 8, Blocks.wooden_slab, tWoodType+8, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+ 9, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_NEG), T, T);
				setBlock							(aWorld, aChunkX+14, aData.mOffsetY+1, aChunkZ+10, Blocks.wooden_slab, tWoodType+8, 2);
				
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 5, aData, aRandom);
				if (tDidntGenerateZPM && aRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 6, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else setFlowerPot					(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 7+aRandom.nextInt(2), SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				if (tDidntGenerateZPM && aRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 9, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else setFlowerPot					(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+14, aData.mOffsetY+2, aChunkZ+10, aData, aRandom);
			}
			if (aData.mRoomLayout[aData.mRoomX-1][aData.mRoomZ] == 0) {
				setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 5, Blocks.wooden_slab, tWoodType+8, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 6, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
				setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 7, Blocks.wooden_slab, tWoodType+8, 2);
				setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 8, Blocks.wooden_slab, tWoodType+8, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+ 9, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_X_POS), T, T);
				setBlock							(aWorld, aChunkX+ 1, aData.mOffsetY+1, aChunkZ+10, Blocks.wooden_slab, tWoodType+8, 2);
				
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 5, aData, aRandom);
				if (tDidntGenerateZPM && aRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 6, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else setFlowerPot					(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 6, aData, aRandom);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 7+aRandom.nextInt(2), SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				if (tDidntGenerateZPM && aRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 9, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else setFlowerPot					(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+ 9, aData, aRandom);
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+ 1, aData.mOffsetY+2, aChunkZ+10, aData, aRandom);
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ+1] == 0) {
				setBlock							(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+14, Blocks.wooden_slab, tWoodType+8, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
				setBlock							(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+14, Blocks.wooden_slab, tWoodType+8, 2);
				setBlock							(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+14, Blocks.wooden_slab, tWoodType+8, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+14, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_NEG), T, T);
				setBlock							(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+14, Blocks.wooden_slab, tWoodType+8, 2);
				
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+14, aData, aRandom);
				if (tDidntGenerateZPM && aRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+14, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else setFlowerPot					(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+14, aData, aRandom);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 7+aRandom.nextInt(2), aData.mOffsetY+2, aChunkZ+14, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				if (tDidntGenerateZPM && aRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+14, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else setFlowerPot					(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+14, aData, aRandom);
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+14, aData, aRandom);
			}
			if (aData.mRoomLayout[aData.mRoomX][aData.mRoomZ-1] == 0) {
				setBlock							(aWorld, aChunkX+ 5, aData.mOffsetY+1, aChunkZ+ 1, Blocks.wooden_slab, tWoodType+8, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 6, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
				setBlock							(aWorld, aChunkX+ 7, aData.mOffsetY+1, aChunkZ+ 1, Blocks.wooden_slab, tWoodType+8, 2);
				setBlock							(aWorld, aChunkX+ 8, aData.mOffsetY+1, aChunkZ+ 1, Blocks.wooden_slab, tWoodType+8, 2);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 9, aData.mOffsetY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short)(7000+tWoodType), UT.NBT.make(null, "gt.dungeonloot.front", ChestGenHooks.STRONGHOLD_LIBRARY, NBT_FACING, SIDE_Z_POS), T, T);
				setBlock							(aWorld, aChunkX+10, aData.mOffsetY+1, aChunkZ+ 1, Blocks.wooden_slab, tWoodType+8, 2);
				
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+ 5, aData.mOffsetY+2, aChunkZ+ 1, aData, aRandom);
				if (tDidntGenerateZPM && aRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+ 1, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else setFlowerPot					(aWorld, aChunkX+ 6, aData.mOffsetY+2, aChunkZ+ 1, aData, aRandom);
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 7+aRandom.nextInt(2), aData.mOffsetY+2, aChunkZ+ 1, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
				if (tDidntGenerateZPM && aRandom.nextInt(16)==0 && aData.mStructure.mZPM) {
				aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+ 1, SIDE_UNKNOWN, (short)14999, UT.NBT.make(null, NBT_ACTIVE_ENERGY, aRandom.nextBoolean()), T, T);
				tDidntGenerateZPM = F;
				} else setFlowerPot					(aWorld, aChunkX+ 9, aData.mOffsetY+2, aChunkZ+ 1, aData, aRandom);
				if (aRandom.nextInt(4)==0) setCoins	(aWorld, aChunkX+10, aData.mOffsetY+2, aChunkZ+ 1, aData, aRandom);
			}
		}
		
		switch(aRandom.nextInt(4)) {
		case 0:
			setBlock							(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+ 3, Blocks.enchanting_table, 0, 2);
			setBlock							(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+12, Blocks.crafting_table, 0, 2);
			setBlock							(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+ 3, Blocks.jukebox, 0, 2);
			setBlock							(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+12, Blocks.ender_chest, 0, 2);
			aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 3, aData.mOffsetY+2, aChunkZ+12, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			break;
		case 1:
			setBlock							(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+ 3, Blocks.ender_chest, 0, 2);
			setBlock							(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+12, Blocks.enchanting_table, 0, 2);
			setBlock							(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+ 3, Blocks.crafting_table, 0, 2);
			setBlock							(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+12, Blocks.jukebox, 0, 2);
			aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+12, aData.mOffsetY+2, aChunkZ+ 3, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			break;
		case 2:
			setBlock							(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+ 3, Blocks.jukebox, 0, 2);
			setBlock							(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+12, Blocks.ender_chest, 0, 2);
			setBlock							(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+ 3, Blocks.enchanting_table, 0, 2);
			setBlock							(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+12, Blocks.crafting_table, 0, 2);
			aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+12, aData.mOffsetY+2, aChunkZ+12, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			break;
		case 3:
			setBlock							(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+ 3, Blocks.crafting_table, 0, 2);
			setBlock							(aWorld, aChunkX+ 3, aData.mOffsetY+1, aChunkZ+12, Blocks.jukebox, 0, 2);
			setBlock							(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+ 3, Blocks.ender_chest, 0, 2);
			setBlock							(aWorld, aChunkX+12, aData.mOffsetY+1, aChunkZ+12, Blocks.enchanting_table, 0, 2);
			aData.mRegistry.mBlock.placeBlock	(aWorld, aChunkX+ 3, aData.mOffsetY+2, aChunkZ+ 3, SIDE_UNKNOWN, (short)32739, new FluidTankGT(FL.Potion_NightVision_1L.make(250)).writeToNBT(UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), NBT_TANK), T, T);
			break;
		}
		return T;
	}
}
