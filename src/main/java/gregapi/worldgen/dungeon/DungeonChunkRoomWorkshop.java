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

import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.ToolsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.fluid.FluidTankGT;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.util.UT;
import gregtech.tileentity.tools.MultiTileEntityMold;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class DungeonChunkRoomWorkshop extends DungeonChunkRoomEmpty {
	public static OreDictMaterial[] sMetals = {MT.DamascusSteel, MT.DamascusSteel, MT.DamascusSteel, MT.BlackSteel, MT.RedSteel, MT.BlueSteel, MT.VanadiumSteel, MT.Steel, MT.Fe, MT.Brass, MT.Bronze, MT.BismuthBronze, MT.BlackBronze};
	
	@Override
	public boolean generate(DungeonData aData) {
		if (aData.mTags.contains(WorldgenDungeonGT.TAG_WORKSHOP) || !super.generate(aData)) return F;
		aData.mTags.add(WorldgenDungeonGT.TAG_WORKSHOP);
		
		aData.set             ( 5, 1,  1, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red, NBT_PAINTED, T), NBT_TANK), T, T);
		
		aData.set             ( 3, 1,  1, SIDE_UNKNOWN,    11, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, "gt.dungeonloot", ChestGenHooks.MINESHAFT_CORRIDOR      ), T, T);
		
		aData.set             ( 2, 1,  1, SIDE_UNKNOWN,    11, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, "gt.dungeonloot", ChestGenHooks.STRONGHOLD_CROSSING     ), T, T);
		
		aData.set             ( 1, 1,  1, Blocks.crafting_table, 0, 2);
		aData.set             ( 1, 2,  1, SIDE_UNKNOWN, 32735, T, T);
		
		aData.set             ( 1, 1,  2, SIDE_UNKNOWN,    11, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_X_POS, "gt.dungeonloot", ChestGenHooks.DUNGEON_CHEST           ), T, T);
		
		
		NBTTagList
		tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.StainlessSteel  , 32+aData.next(33))), "s", (short)     aData.next(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.StainlessSteel  , 32+aData.next(33))), "s", (short)     aData.next(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.StainlessSteel  , 32+aData.next(33))), "s", (short)     aData.next(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.StainlessSteel  , 16+aData.next(49))), "s", (short)     aData.next(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.StainlessSteel  , 16+aData.next(49))), "s", (short)     aData.next(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.StainlessSteel  ,  8+aData.next(25))), "s", (short)     aData.next(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.StainlessSteel  ,  1+aData.next( 4))), "s", (short)     aData.next(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.StainlessSteel  ,  8+aData.next(25))), "s", (short)     aData.next(36) ));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.Bronze          , 32+aData.next(33))), "s", (short)( 36+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.Bronze          , 32+aData.next(33))), "s", (short)( 36+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.Bronze          , 32+aData.next(33))), "s", (short)( 36+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.Bronze          , 16+aData.next(49))), "s", (short)( 36+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.Bronze          , 16+aData.next(49))), "s", (short)( 36+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.Bronze          ,  8+aData.next(25))), "s", (short)( 36+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.Bronze          ,  1+aData.next( 4))), "s", (short)( 36+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.Bronze          ,  8+aData.next(25))), "s", (short)( 36+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.Invar           , 32+aData.next(33))), "s", (short)( 72+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.Invar           , 32+aData.next(33))), "s", (short)( 72+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.Invar           , 32+aData.next(33))), "s", (short)( 72+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.Invar           , 16+aData.next(49))), "s", (short)( 72+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.Invar           , 16+aData.next(49))), "s", (short)( 72+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.Invar           ,  8+aData.next(25))), "s", (short)( 72+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.Invar           ,  1+aData.next( 4))), "s", (short)( 72+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.Invar           ,  8+aData.next(25))), "s", (short)( 72+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.Brass           , 32+aData.next(33))), "s", (short)(108+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.Brass           , 32+aData.next(33))), "s", (short)(108+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.Brass           , 32+aData.next(33))), "s", (short)(108+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.Brass           , 16+aData.next(49))), "s", (short)(108+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.Brass           , 16+aData.next(49))), "s", (short)(108+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.Brass           ,  8+aData.next(25))), "s", (short)(108+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.Brass           ,  1+aData.next( 4))), "s", (short)(108+aData.next(36))));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.Brass           ,  8+aData.next(25))), "s", (short)(108+aData.next(36))));
		aData.set             ( 1, 1,  3, SIDE_UNKNOWN, (short) 4011, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tInventory), T, T);
		aData.set             ( 1, 2,  3, SIDE_UNKNOWN, (short) 2010, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_X_POS, "gt.dungeonloot", ChestGenHooks.PYRAMID_JUNGLE_CHEST    ), T, T);
		aData.coins           ( 1, 3,  3);
		
		
		tInventory = new NBTTagList();
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.stick          .mat(MT.Steel, 32+aData.next(33))), "s", (short)aData.next(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ingot          .mat(MT.Steel, 32+aData.next(33))), "s", (short)aData.next(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plate          .mat(MT.Steel, 32+aData.next(33))), "s", (short)aData.next(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.plateCurved    .mat(MT.Steel, 16+aData.next(49))), "s", (short)aData.next(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.screw          .mat(MT.Steel, 16+aData.next(49))), "s", (short)aData.next(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.ring           .mat(MT.Steel,  8+aData.next(25))), "s", (short)aData.next(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGt         .mat(MT.Steel,  1+aData.next( 4))), "s", (short)aData.next(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(OP.gearGtSmall    .mat(MT.Steel,  8+aData.next(25))), "s", (short)aData.next(16)));
		tInventory.appendTag(UT.NBT.makeShort(ST.save(aData.next1in2()?IL.Tool_Lighter_Invar_Full.get(1):IL.Tool_Lighter_Invar_Empty.get(1)), "s", (short)(35+aData.next(36))));
		aData.set             ( 1, 1,  4, SIDE_UNKNOWN,  5011, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_X_POS, NBT_INV_LIST, tInventory), T, T);
		aData.set             ( 1, 2,  4, SIDE_UNKNOWN, 32738, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T), T, T);
		
		
		aData.set             ( 4, 1,  1, SIDE_UNKNOWN,  6011, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, UT.NBT.makeInv(NI, ST.make(aData.mPrimary  , 10000+aData.next(90001), 1))), T, T);
		aData.set             ( 4, 2,  1, SIDE_UNKNOWN,  6011, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, UT.NBT.makeInv(NI, ST.make(aData.mSecondary,  1000+aData.next( 9001), 1))), T, T);
		tInventory = UT.NBT.makeInv(ST.book("Manual_Elements"), ST.book("Manual_Alloys"), ST.book("Manual_Smeltery"), ST.book("Manual_Random"), ST.book("Manual_Extenders"), ST.book("Manual_Steam"), ST.book("Manual_Tools"), ST.book("Manual_Printer"), IL.Duct_Tape.get(1), IL.Duct_Tape.get(1));
		int tKeyIndex = aData.next(aData.mGeneratedKeys.length * 2);
		if (tKeyIndex < aData.mGeneratedKeys.length) {
			aData.mGeneratedKeys[tKeyIndex] = T;
			tInventory.appendTag(UT.NBT.makeShort(ST.save(aData.mKeyStacks[tKeyIndex]), "s", (short)(10+aData.next(18))));
		}
		aData.set             ( 4, 3,  1, SIDE_UNKNOWN,  7111, UT.NBT.make(NBT_COLOR, DYES_INT[aData.mColor], NBT_PAINTED, T, NBT_FACING, SIDE_Z_POS, NBT_INV_LIST, tInventory, "gt.dungeonloot.front", ChestGenHooks.VILLAGE_BLACKSMITH), T, T);
		
		
		//-----
		
		ArrayListNoNulls<Integer> tMoldShapes = new ArrayListNoNulls<>(MultiTileEntityMold.MOLD_RECIPES.keySet());
		int tCrucibleType = aData.next(3);
		
		aData.set             (14, 1,  1, SIDE_UNKNOWN,    11, UT.NBT.make("gt.dungeonloot", ChestGenHooks.VILLAGE_BLACKSMITH , NBT_FACING, SIDE_X_NEG), T, T);
		aData.smooth          (14, 1,  2);
		aData.set             (14, 1,  3, SIDE_UNKNOWN, (1102+tCrucibleType), UT.NBT.make(NBT_FACING, SIDE_X_NEG), T, T);
		aData.smooth          (14, 1,  4);
		aData.ingots_or_plates(14, 1,  5, 0, sMetals);
		aData.ingots_or_plates(10, 1,  1, 0, sMetals);
		aData.set             (11, 1,  1, Blocks.anvil, 3 | (aData.next(3) << 2), 0);
		aData.set             (12, 1,  1, SIDE_UNKNOWN, 32703, UT.NBT.make(NBT_FACING, SIDE_Z_POS, NBT_STATE, 1+aData.next(4)), T, T);
		aData.set             (11, 1,  4, SIDE_UNKNOWN,(32034+aData.next(4)), UT.NBT.make(NBT_INV_LIST, UT.NBT.makeInv(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.HARDHAMMER, MT.VanadiumSteel, MT.WOODS.Spruce))), T, T);
		aData.ingots_or_plates(11, 1,  5, 0, sMetals);
		
		aData.set             (14, 2,  2, SIDE_UNKNOWN, (1070+tCrucibleType), UT.NBT.make("gt.mold", tMoldShapes.isEmpty()?0:tMoldShapes.get(aData.next(tMoldShapes.size()))), T, T);
		aData.set             (14, 2,  3, SIDE_UNKNOWN, (1020+tCrucibleType), UT.NBT.make(NBT_FACING, SIDE_X_NEG), T, T);
		aData.set             (14, 2,  4, SIDE_UNKNOWN, (1070+tCrucibleType), UT.NBT.make("gt.mold", tMoldShapes.isEmpty()?0:tMoldShapes.get(aData.next(tMoldShapes.size()))), T, T);
		
		//-----
		
		aData.set             (13, 1, 14, SIDE_UNKNOWN, 32705, null, T, T);
		aData.smooth          (14, 1, 14);
		aData.set             (14, 1, 13, Blocks.cauldron, aData.next(4), 0);
		aData.set             (14, 1, 11, SIDE_UNKNOWN, 32707, null, T, T);
		
		aData.set             (13, 2, 14, SIDE_UNKNOWN, 32730, UT.NBT.make(NBT_FACING, SIDE_X_POS), T, T);
		aData.set             (14, 2, 14, SIDE_UNKNOWN, 32716, new FluidTankGT(FL.Water.make(64000)).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
		aData.set             (14, 2, 13, SIDE_UNKNOWN, 32730, UT.NBT.make(NBT_FACING, SIDE_Z_POS), T, T);
		
		aData.set             (14, 3, 14, SIDE_UNKNOWN, 32725, UT.NBT.make(NBT_FACING, SIDE_BOTTOM), T, T);
		
		//-----
		
		int tAmount = (aData.next1in3() ? 32000 : 16000);
		short tID = (short)(tAmount > 16000 ? 32734 : 32714);
		FluidStack[] tDrinks = FL.array(FL.Purple_Drink.make(tAmount), FL.Purple_Drink.make(tAmount), FL.Purple_Drink.make(tAmount), FL.Vodka.make(tAmount), FL.Mead.make(tAmount), FL.Whiskey_GlenMcKenner.make(tAmount), FL.Wine_Grape_Purple.make(tAmount));
		
		for (int i = 0; i < 2; i++) for (int j = 0; j < 2; j++) {
			if (aData.next1in2()) for (int k = 0; k < 3; k++) {
				aData.set(1+i, 1+k, 12+j, SIDE_UNKNOWN, tID, new FluidTankGT(UT.Code.select(NF, tDrinks)).writeToNBT(UT.NBT.make(), NBT_TANK), T, T);
				if (aData.next1in2()) break;
			} else if (aData.next1in2()) {
				switch(aData.next(3)) {
				case 0: aData.set(1+i, 1, 12+j, SIDE_UNKNOWN, 32055, new FluidTankGT(FL.Propane.make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Red      , NBT_PAINTED, T), NBT_TANK), T, T); break;
				case 1: aData.set(1+i, 1, 12+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Oxygen .make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_LightBlue, NBT_PAINTED, T), NBT_TANK), T, T); break;
				case 2: aData.set(1+i, 1, 12+j, SIDE_UNKNOWN, 32056, new FluidTankGT(FL.Helium .make(8000)).writeToNBT(UT.NBT.make(NBT_COLOR, DYE_INT_Yellow   , NBT_PAINTED, T), NBT_TANK), T, T); break;
				}
			}
		}
		
		return T;
	}
}
