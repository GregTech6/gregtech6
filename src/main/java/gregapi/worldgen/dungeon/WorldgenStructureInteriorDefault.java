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

import java.util.Random;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.FL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.fluid.FluidTankGT;
import gregapi.util.ST;
import gregapi.util.UT;
import gregtech.tileentity.tools.MultiTileEntityMold;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class WorldgenStructureInteriorDefault extends WorldgenDungeonGT {
	public static boolean generate(World aWorld, Random aRandom, int aChunkX, int aChunkZ, DungeonChunkData aData) {
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+ 3, aData.mY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short)   11, UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, "gt.dungeonloot", ChestGenHooks.MINESHAFT_CORRIDOR      ), T, T);
		
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+ 2, aData.mY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short)   11, UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, "gt.dungeonloot", ChestGenHooks.STRONGHOLD_CROSSING     ), T, T);
		
		setBlock                                    (aWorld, aChunkX+ 1, aData.mY+1, aChunkZ+ 1, Blocks.crafting_table, 0, 2);
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+ 1, aData.mY+2, aChunkZ+ 1, SIDE_UNKNOWN, (short)32735, UT.NBT.make(), T, T);
		
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+ 1, aData.mY+1, aChunkZ+ 2, SIDE_UNKNOWN, (short)   11, UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_X_POS, "gt.dungeonloot", ChestGenHooks.DUNGEON_CHEST           ), T, T);
		
		
		NBTTagList
		tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.StainlessSteel  , 32+aRandom.nextInt(33))), "s", (short)     aRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.StainlessSteel  , 32+aRandom.nextInt(33))), "s", (short)     aRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.StainlessSteel  , 32+aRandom.nextInt(33))), "s", (short)     aRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.StainlessSteel  , 16+aRandom.nextInt(49))), "s", (short)     aRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.StainlessSteel  , 16+aRandom.nextInt(49))), "s", (short)     aRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.StainlessSteel  ,  8+aRandom.nextInt(25))), "s", (short)     aRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.StainlessSteel  ,  1+aRandom.nextInt( 4))), "s", (short)     aRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.StainlessSteel  ,  8+aRandom.nextInt(25))), "s", (short)     aRandom.nextInt(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.Bronze          , 32+aRandom.nextInt(33))), "s", (short)( 36+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.Bronze          , 32+aRandom.nextInt(33))), "s", (short)( 36+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.Bronze          , 32+aRandom.nextInt(33))), "s", (short)( 36+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.Bronze          , 16+aRandom.nextInt(49))), "s", (short)( 36+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.Bronze          , 16+aRandom.nextInt(49))), "s", (short)( 36+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.Bronze          ,  8+aRandom.nextInt(25))), "s", (short)( 36+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.Bronze          ,  1+aRandom.nextInt( 4))), "s", (short)( 36+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.Bronze          ,  8+aRandom.nextInt(25))), "s", (short)( 36+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.Invar           , 32+aRandom.nextInt(33))), "s", (short)( 72+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.Invar           , 32+aRandom.nextInt(33))), "s", (short)( 72+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.Invar           , 32+aRandom.nextInt(33))), "s", (short)( 72+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.Invar           , 16+aRandom.nextInt(49))), "s", (short)( 72+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.Invar           , 16+aRandom.nextInt(49))), "s", (short)( 72+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.Invar           ,  8+aRandom.nextInt(25))), "s", (short)( 72+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.Invar           ,  1+aRandom.nextInt( 4))), "s", (short)( 72+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.Invar           ,  8+aRandom.nextInt(25))), "s", (short)( 72+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.Brass           , 32+aRandom.nextInt(33))), "s", (short)(108+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.Brass           , 32+aRandom.nextInt(33))), "s", (short)(108+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.Brass           , 32+aRandom.nextInt(33))), "s", (short)(108+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.Brass           , 16+aRandom.nextInt(49))), "s", (short)(108+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.Brass           , 16+aRandom.nextInt(49))), "s", (short)(108+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.Brass           ,  8+aRandom.nextInt(25))), "s", (short)(108+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.Brass           ,  1+aRandom.nextInt( 4))), "s", (short)(108+aRandom.nextInt(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.Brass           ,  8+aRandom.nextInt(25))), "s", (short)(108+aRandom.nextInt(36))));
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+ 1, aData.mY+1, aChunkZ+ 3, SIDE_UNKNOWN, (short) 4011, UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tInventory), T, T);
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+ 1, aData.mY+2, aChunkZ+ 3, SIDE_UNKNOWN, (short) 2010, UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_X_POS, "gt.dungeonloot", ChestGenHooks.PYRAMID_JUNGLE_CHEST    ), T, T);
		setCoins                                    (aWorld, aChunkX+ 1, aData.mY+3, aChunkZ+ 3, aData, aRandom);
		
		
		tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.Steel, 32+aRandom.nextInt(33))), "s", (short)aRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.Steel, 32+aRandom.nextInt(33))), "s", (short)aRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.Steel, 32+aRandom.nextInt(33))), "s", (short)aRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.Steel, 16+aRandom.nextInt(49))), "s", (short)aRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.Steel, 16+aRandom.nextInt(49))), "s", (short)aRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.Steel,  8+aRandom.nextInt(25))), "s", (short)aRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.Steel,  1+aRandom.nextInt( 4))), "s", (short)aRandom.nextInt(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.Steel,  8+aRandom.nextInt(25))), "s", (short)aRandom.nextInt(16)));
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+ 1, aData.mY+1, aChunkZ+ 4, SIDE_UNKNOWN, (short) 5011, UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tInventory), T, T);
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+ 1, aData.mY+2, aChunkZ+ 4, SIDE_UNKNOWN, (short)32738, UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		
		
		tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(aData.mPrimary    , 10001+aRandom.nextInt(90000), 1)), "s", (short)1));
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+ 4, aData.mY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short) 6011, UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory), T, T);
		tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.make(aData.mSecondary  ,  1001+aRandom.nextInt( 9000), 1)), "s", (short)1));
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+ 4, aData.mY+2, aChunkZ+ 1, SIDE_UNKNOWN, (short) 6011, UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory), T, T);
		tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Elements"     )), "s", (short)0));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Alloys"       )), "s", (short)1));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Smeltery"     )), "s", (short)2));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Random"       )), "s", (short)3));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Extenders"    )), "s", (short)4));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Steam"        )), "s", (short)5));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Tools"        )), "s", (short)6));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(ST.book("Manual_Printer"      )), "s", (short)7));
		int tKeyIndex = aRandom.nextInt(aData.mGeneratedKeys.length * 2);
		if (tKeyIndex < aData.mGeneratedKeys.length) {
			aData.mGeneratedKeys[tKeyIndex] = T;
			tInventory.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[tKeyIndex]), "s", (short)(8+aRandom.nextInt(20))));
		}
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+ 4, aData.mY+3, aChunkZ+ 1, SIDE_UNKNOWN, (short) 7111, UT.NBT.make(null, NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory, "gt.dungeonloot.front", ChestGenHooks.VILLAGE_BLACKSMITH), T, T);
		
		
		//-----
		
		ArrayListNoNulls<Integer> tMoldShapes = new ArrayListNoNulls<>(MultiTileEntityMold.MOLD_RECIPES.keySet());
		int tCrucibleType = aRandom.nextInt(3);
		
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+14, aData.mY+1, aChunkZ+ 1, SIDE_UNKNOWN, (short)   11, UT.NBT.make(null, "gt.dungeonloot", ChestGenHooks.VILLAGE_BLACKSMITH , NBT_FACING, SIDE_X_NEG), T, T);
		setSmoothBlock                              (aWorld, aChunkX+14, aData.mY+1, aChunkZ+ 2, aData, aRandom);
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+14, aData.mY+1, aChunkZ+ 3, SIDE_UNKNOWN, (short)(1102+tCrucibleType), UT.NBT.make(null, NBT_FACING, SIDE_X_NEG), T, T);
		setSmoothBlock                              (aWorld, aChunkX+14, aData.mY+1, aChunkZ+ 4, aData, aRandom);
		setBlock                                    (aWorld, aChunkX+11, aData.mY+1, aChunkZ+ 1, Blocks.anvil, 3 | (aRandom.nextInt(3) << 2), 0);
		
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+14, aData.mY+2, aChunkZ+ 2, SIDE_UNKNOWN, (short)(1070+tCrucibleType), UT.NBT.make(null, "gt.mold", tMoldShapes.isEmpty()?0:tMoldShapes.get(aRandom.nextInt(tMoldShapes.size()))), T, T);
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+14, aData.mY+2, aChunkZ+ 3, SIDE_UNKNOWN, (short)(1020+tCrucibleType), UT.NBT.make(null, NBT_FACING, SIDE_X_NEG), T, T);
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+14, aData.mY+2, aChunkZ+ 4, SIDE_UNKNOWN, (short)(1070+tCrucibleType), UT.NBT.make(null, "gt.mold", tMoldShapes.isEmpty()?0:tMoldShapes.get(aRandom.nextInt(tMoldShapes.size()))), T, T);
		
		//-----
		
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+13, aData.mY+1, aChunkZ+14, SIDE_UNKNOWN, (short)32705, null, T, T);
		setSmoothBlock                              (aWorld, aChunkX+14, aData.mY+1, aChunkZ+14, aData, aRandom);
		setBlock                                    (aWorld, aChunkX+14, aData.mY+1, aChunkZ+13, Blocks.cauldron, aRandom.nextInt(4), 0);
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+14, aData.mY+1, aChunkZ+11, SIDE_UNKNOWN, (short)32707, null, T, T);
		
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+13, aData.mY+2, aChunkZ+14, SIDE_UNKNOWN, (short)32730, UT.NBT.make(null, NBT_FACING, SIDE_X_POS), T, T);
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+14, aData.mY+2, aChunkZ+14, SIDE_UNKNOWN, (short)32716, new FluidTankGT(FL.Water.make(64000)).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+14, aData.mY+2, aChunkZ+13, SIDE_UNKNOWN, (short)32730, UT.NBT.make(null, NBT_FACING, SIDE_Z_POS), T, T);
		
		aData.mMTERegistryGT.mBlock.placeBlock           (aWorld, aChunkX+14, aData.mY+3, aChunkZ+14, SIDE_UNKNOWN, (short)32725, UT.NBT.make(null, NBT_FACING, SIDE_BOTTOM), T, T);
		
		//-----
		
		int tAmount = (aRandom.nextInt(3) == 0 ? 32000 : 16000);
		short tID = (short)(tAmount > 16000 ? 32734 : 32714);
		FluidStack[] tDrinks = new FluidStack[] {FL.Purple_Drink.make(tAmount), FL.Purple_Drink.make(tAmount), FL.Purple_Drink.make(tAmount), FL.Vodka.make(tAmount), FL.Mead.make(tAmount), FL.Whiskey_GlenMcKenner.make(tAmount), FL.Wine_Grape_Purple.make(tAmount)};
		
		for (int i = 0; i < 2; i++) for (int j = 0; j < 2; j++) if (aRandom.nextInt(3) > 0) for (int k = 0; k < 3; k++) {
			aData.mMTERegistryGT.mBlock.placeBlock(aWorld, aChunkX+1+i, aData.mY+1+k, aChunkZ+12+j, SIDE_UNKNOWN, tID, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
			if (aRandom.nextInt(3) == 0) break;
		}
		
		return T;
	}
}
