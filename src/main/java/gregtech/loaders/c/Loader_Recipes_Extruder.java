/**
 * Copyright (c) 2019 Gregorius Techneticies
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

package gregtech.loaders.c;

import static gregapi.data.CS.*;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class Loader_Recipes_Extruder implements Runnable {
	@Override
	@SuppressWarnings("unused")
	public void run() {
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Plate        .get(0), OP.plate.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Plate_Curved .get(0), OP.plateCurved.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Rod          .get(0), OP.stick.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Rod_Long     .get(0), OP.stickLong.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   16, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Bolt         .get(0), OP.bolt.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Ingot        .get(0), ST.make(Blocks.stonebrick, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Block        .get(0), ST.make(Blocks.stone, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Shovel       .get(0), OP.toolHeadRawShovel.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  128, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Sword        .get(0), OP.toolHeadRawSword.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  128, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Hoe          .get(0), OP.toolHeadRawHoe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  192, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Pickaxe      .get(0), OP.toolHeadRawPickaxe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  192, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Axe          .get(0), OP.toolHeadRawAxe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  256, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Gear         .get(0), OP.gearGt.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Gear_Small   .get(0), OP.gearGtSmall.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  384, IL.Module_Stone_Generator  .get(0), IL.Shape_Extruder_Hammer       .get(0), OP.toolHeadHammer.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Plate        .get(0), OP.plate.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Plate_Curved .get(0), OP.plateCurved.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Rod          .get(0), OP.stick.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Rod_Long     .get(0), OP.stickLong.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   16, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Bolt         .get(0), OP.bolt.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Ingot        .get(0), ST.make(Blocks.stonebrick, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Block        .get(0), ST.make(Blocks.stone, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Shovel       .get(0), OP.toolHeadRawShovel.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  128, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Sword        .get(0), OP.toolHeadRawSword.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  128, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Hoe          .get(0), OP.toolHeadRawHoe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  192, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Pickaxe      .get(0), OP.toolHeadRawPickaxe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  192, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Axe          .get(0), OP.toolHeadRawAxe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  256, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Gear         .get(0), OP.gearGt.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Gear_Small   .get(0), OP.gearGtSmall.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  384, IL.Module_Stone_Generator  .get(0), IL.Shape_SimpleEx_Hammer       .get(0), OP.toolHeadHammer.mat(MT.Stone, 1));
		
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_Extruder_Plate        .get(0), OP.plate.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_Extruder_Plate_Curved .get(0), OP.plateCurved.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_Extruder_Rod          .get(0), OP.stick.mat(MT.Stone, 2));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_Extruder_Rod_Long     .get(0), OP.stickLong.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,    8, ST.make(Blocks.stone       , 1, W), IL.Shape_Extruder_Bolt         .get(0), OP.bolt.mat(MT.Stone, 8));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_Extruder_Ingot        .get(0), ST.make(Blocks.stonebrick, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_Extruder_Block        .get(0), ST.make(Blocks.stone, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_Extruder_Shovel       .get(0), OP.toolHeadRawShovel.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, ST.make(Blocks.stone       , 2, W), IL.Shape_Extruder_Sword        .get(0), OP.toolHeadRawSword.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, ST.make(Blocks.stone       , 2, W), IL.Shape_Extruder_Hoe          .get(0), OP.toolHeadRawHoe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   96, ST.make(Blocks.stone       , 3, W), IL.Shape_Extruder_Pickaxe      .get(0), OP.toolHeadRawPickaxe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   96, ST.make(Blocks.stone       , 3, W), IL.Shape_Extruder_Axe          .get(0), OP.toolHeadRawAxe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  128, ST.make(Blocks.stone       , 4, W), IL.Shape_Extruder_Gear         .get(0), OP.gearGt.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_Extruder_Gear_Small   .get(0), OP.gearGtSmall.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  192, ST.make(Blocks.stone       , 6, W), IL.Shape_Extruder_Hammer       .get(0), OP.toolHeadHammer.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_SimpleEx_Plate        .get(0), OP.plate.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_SimpleEx_Plate_Curved .get(0), OP.plateCurved.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_SimpleEx_Rod          .get(0), OP.stick.mat(MT.Stone, 2));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_SimpleEx_Rod_Long     .get(0), OP.stickLong.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,    8, ST.make(Blocks.stone       , 1, W), IL.Shape_SimpleEx_Bolt         .get(0), OP.bolt.mat(MT.Stone, 8));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_SimpleEx_Ingot        .get(0), ST.make(Blocks.stonebrick, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_SimpleEx_Block        .get(0), ST.make(Blocks.stone, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_SimpleEx_Shovel       .get(0), OP.toolHeadRawShovel.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, ST.make(Blocks.stone       , 2, W), IL.Shape_SimpleEx_Sword        .get(0), OP.toolHeadRawSword.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, ST.make(Blocks.stone       , 2, W), IL.Shape_SimpleEx_Hoe          .get(0), OP.toolHeadRawHoe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   96, ST.make(Blocks.stone       , 3, W), IL.Shape_SimpleEx_Pickaxe      .get(0), OP.toolHeadRawPickaxe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   96, ST.make(Blocks.stone       , 3, W), IL.Shape_SimpleEx_Axe          .get(0), OP.toolHeadRawAxe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  128, ST.make(Blocks.stone       , 4, W), IL.Shape_SimpleEx_Gear         .get(0), OP.gearGt.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.stone       , 1, W), IL.Shape_SimpleEx_Gear_Small   .get(0), OP.gearGtSmall.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  192, ST.make(Blocks.stone       , 6, W), IL.Shape_SimpleEx_Hammer       .get(0), OP.toolHeadHammer.mat(MT.Stone, 1));
		
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_Extruder_Plate        .get(0), OP.plate.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_Extruder_Plate_Curved .get(0), OP.plateCurved.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_Extruder_Rod          .get(0), OP.stick.mat(MT.Stone, 2));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_Extruder_Rod_Long     .get(0), OP.stickLong.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,    8, ST.make(Blocks.cobblestone , 1, W), IL.Shape_Extruder_Bolt         .get(0), OP.bolt.mat(MT.Stone, 8));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_Extruder_Ingot        .get(0), ST.make(Blocks.stonebrick, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_Extruder_Block        .get(0), ST.make(Blocks.stone, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_Extruder_Shovel       .get(0), OP.toolHeadRawShovel.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, ST.make(Blocks.cobblestone , 2, W), IL.Shape_Extruder_Sword        .get(0), OP.toolHeadRawSword.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, ST.make(Blocks.cobblestone , 2, W), IL.Shape_Extruder_Hoe          .get(0), OP.toolHeadRawHoe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   96, ST.make(Blocks.cobblestone , 3, W), IL.Shape_Extruder_Pickaxe      .get(0), OP.toolHeadRawPickaxe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   96, ST.make(Blocks.cobblestone , 3, W), IL.Shape_Extruder_Axe          .get(0), OP.toolHeadRawAxe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  128, ST.make(Blocks.cobblestone , 4, W), IL.Shape_Extruder_Gear         .get(0), OP.gearGt.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_Extruder_Gear_Small   .get(0), OP.gearGtSmall.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  192, ST.make(Blocks.cobblestone , 6, W), IL.Shape_Extruder_Hammer       .get(0), OP.toolHeadHammer.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_SimpleEx_Plate        .get(0), OP.plate.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_SimpleEx_Plate_Curved .get(0), OP.plateCurved.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_SimpleEx_Rod          .get(0), OP.stick.mat(MT.Stone, 2));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_SimpleEx_Rod_Long     .get(0), OP.stickLong.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,    8, ST.make(Blocks.cobblestone , 1, W), IL.Shape_SimpleEx_Bolt         .get(0), OP.bolt.mat(MT.Stone, 8));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_SimpleEx_Ingot        .get(0), ST.make(Blocks.stonebrick, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_SimpleEx_Block        .get(0), ST.make(Blocks.stone, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_SimpleEx_Shovel       .get(0), OP.toolHeadRawShovel.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, ST.make(Blocks.cobblestone , 2, W), IL.Shape_SimpleEx_Sword        .get(0), OP.toolHeadRawSword.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, ST.make(Blocks.cobblestone , 2, W), IL.Shape_SimpleEx_Hoe          .get(0), OP.toolHeadRawHoe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   96, ST.make(Blocks.cobblestone , 3, W), IL.Shape_SimpleEx_Pickaxe      .get(0), OP.toolHeadRawPickaxe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   96, ST.make(Blocks.cobblestone , 3, W), IL.Shape_SimpleEx_Axe          .get(0), OP.toolHeadRawAxe.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  128, ST.make(Blocks.cobblestone , 4, W), IL.Shape_SimpleEx_Gear         .get(0), OP.gearGt.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   32, ST.make(Blocks.cobblestone , 1, W), IL.Shape_SimpleEx_Gear_Small   .get(0), OP.gearGtSmall.mat(MT.Stone, 1));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,  192, ST.make(Blocks.cobblestone , 6, W), IL.Shape_SimpleEx_Hammer       .get(0), OP.toolHeadHammer.mat(MT.Stone, 1));
		
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, ST.make(Blocks.sand, 1, W), IL.Shape_Extruder_Block.get(0), ST.make(Blocks.glass, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, ST.make(Blocks.sand, 1, W), IL.Shape_SimpleEx_Block.get(0), ST.make(Blocks.glass, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, ST.make(Blocks.sand, 1, W), IL.Shape_Extruder_Ingot.get(0), ST.make(Blocks.glass, 1, 0));
		RM.Extruder     .addRecipe2(T, F, F, F, T, 16,   64, ST.make(Blocks.sand, 1, W), IL.Shape_SimpleEx_Ingot.get(0), ST.make(Blocks.glass, 1, 0));
		
		for (ItemStack tStack : new ArrayListNoNulls<ItemStack>(F, ST.make(Blocks.glass, 1, W), ST.make(Blocks.sand, 1, W))) {
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_Extruder_Plate                .get(0), OP.plateGem.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64   ,            tStack , IL.Shape_Extruder_Plate_Curved         .get(0), OP.plateCurved.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_Extruder_Rod                  .get(0), OP.stick.mat(MT.Glass, 2));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_Extruder_Rod_Long             .get(0), OP.stickLong.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_Extruder_Bolt                 .get(0), OP.bolt.mat(MT.Glass, 8));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_Extruder_Ring                 .get(0), OP.ring.mat(MT.Glass, 4));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64   ,            tStack , IL.Shape_Extruder_Wire                 .get(0), OP.wireGt01.mat(MT.Glass, 2));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_Extruder_Casing               .get(0), OP.casingSmall.mat(MT.Glass, 2));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_Extruder_Shovel               .get(0), OP.toolHeadRawShovel.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64   ,            tStack , IL.Shape_Extruder_Pipe_Tiny            .get(0), OP.pipeTiny.mat(MT.Glass, 2));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64   ,            tStack , IL.Shape_Extruder_Pipe_Small           .get(0), OP.pipeSmall.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_Extruder_Cell                 .get(0), OP.chemtube.mat(MT.Empty, 3));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_Extruder_Bottle               .get(0), IL.Bottle_Empty.get(1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64* 2, ST.mul_( 2,tStack), IL.Shape_Extruder_Sword                .get(0), OP.toolHeadRawSword.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64* 2, ST.mul_( 2,tStack), IL.Shape_Extruder_Hoe                  .get(0), OP.toolHeadRawHoe.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64* 2, ST.mul_( 2,tStack), IL.Shape_Extruder_Saw                  .get(0), OP.toolHeadRawSaw.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64* 3, ST.mul_( 3,tStack), IL.Shape_Extruder_Pickaxe              .get(0), OP.toolHeadRawPickaxe.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64* 3, ST.mul_( 3,tStack), IL.Shape_Extruder_Axe                  .get(0), OP.toolHeadRawAxe.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64* 3, ST.mul_( 3,tStack), IL.Shape_Extruder_File                 .get(0), OP.toolHeadFile.mat(MT.Glass, 2));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64* 3, ST.mul_( 3,tStack), IL.Shape_Extruder_Pipe_Medium          .get(0), OP.pipeMedium.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64* 4, ST.mul_( 4,tStack), IL.Shape_Extruder_Gear                 .get(0), OP.gearGt.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_Extruder_Gear_Small           .get(0), OP.gearGtSmall.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64* 6, ST.mul_( 6,tStack), IL.Shape_Extruder_Hammer               .get(0), OP.toolHeadHammer.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64* 6, ST.mul_( 6,tStack), IL.Shape_Extruder_Pipe_Large           .get(0), OP.pipeLarge.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64* 9, ST.mul_( 9,tStack), IL.Shape_Extruder_Block                .get(0), OP.blockSolid.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64*12, ST.mul_(12,tStack), IL.Shape_Extruder_Pipe_Huge            .get(0), OP.pipeHuge.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_SimpleEx_Plate                .get(0), OP.plateGem.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64   ,            tStack , IL.Shape_SimpleEx_Plate_Curved         .get(0), OP.plateCurved.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_SimpleEx_Rod                  .get(0), OP.stick.mat(MT.Glass, 2));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_SimpleEx_Rod_Long             .get(0), OP.stickLong.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_SimpleEx_Bolt                 .get(0), OP.bolt.mat(MT.Glass, 8));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_SimpleEx_Ring                 .get(0), OP.ring.mat(MT.Glass, 4));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64   ,            tStack , IL.Shape_SimpleEx_Wire                 .get(0), OP.wireGt01.mat(MT.Glass, 2));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_SimpleEx_Casing               .get(0), OP.casingSmall.mat(MT.Glass, 2));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_SimpleEx_Shovel               .get(0), OP.toolHeadRawShovel.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64   ,            tStack , IL.Shape_SimpleEx_Pipe_Tiny            .get(0), OP.pipeTiny.mat(MT.Glass, 2));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64   ,            tStack , IL.Shape_SimpleEx_Pipe_Small           .get(0), OP.pipeSmall.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_SimpleEx_Cell                 .get(0), OP.chemtube.mat(MT.Empty, 3));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_SimpleEx_Bottle               .get(0), IL.Bottle_Empty.get(1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64* 2, ST.mul_( 2,tStack), IL.Shape_SimpleEx_Sword                .get(0), OP.toolHeadRawSword.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64* 2, ST.mul_( 2,tStack), IL.Shape_SimpleEx_Hoe                  .get(0), OP.toolHeadRawHoe.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64* 2, ST.mul_( 2,tStack), IL.Shape_SimpleEx_Saw                  .get(0), OP.toolHeadRawSaw.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64* 3, ST.mul_( 3,tStack), IL.Shape_SimpleEx_Pickaxe              .get(0), OP.toolHeadRawPickaxe.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64* 3, ST.mul_( 3,tStack), IL.Shape_SimpleEx_Axe                  .get(0), OP.toolHeadRawAxe.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64* 3, ST.mul_( 3,tStack), IL.Shape_SimpleEx_File                 .get(0), OP.toolHeadFile.mat(MT.Glass, 2));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64* 3, ST.mul_( 3,tStack), IL.Shape_SimpleEx_Pipe_Medium          .get(0), OP.pipeMedium.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64* 4, ST.mul_( 4,tStack), IL.Shape_SimpleEx_Gear                 .get(0), OP.gearGt.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, T, 16, 64   ,            tStack , IL.Shape_SimpleEx_Gear_Small           .get(0), OP.gearGtSmall.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64* 6, ST.mul_( 6,tStack), IL.Shape_SimpleEx_Hammer               .get(0), OP.toolHeadHammer.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64* 6, ST.mul_( 6,tStack), IL.Shape_SimpleEx_Pipe_Large           .get(0), OP.pipeLarge.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64* 9, ST.mul_( 9,tStack), IL.Shape_SimpleEx_Block                .get(0), OP.blockSolid.mat(MT.Glass, 1));
			RM.Extruder.addRecipe2(T, F, F, F, F, 16, 64*12, ST.mul_(12,tStack), IL.Shape_SimpleEx_Pipe_Huge            .get(0), OP.pipeHuge.mat(MT.Glass, 1));
		}
		
		for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.Zr, MT.WaxBee, MT.WaxPlant, MT.WaxParaffin, MT.WaxRefractory, MT.WaxMagic, MT.WaxAmnesic, MT.Fe, MT.WroughtIron, MT.Glass, MT.Sn, MT.TinAlloy, MT.Plastic}) {
			boolean tSimple = tMaterial.contains(TD.Processing.EXTRUDER_SIMPLE);
			long tEUt = tSimple ? 16 : 96, tCostsPerIngot = tSimple ? 64 : Math.max(64, (long)((tMaterial.mMeltingPoint - CS.DEF_ENV_TEMP) * (1 + (tMaterial.getWeight(OP.ingot.mAmount) / 6144))));
			for (ItemStack tStack : new ArrayListNoNulls<ItemStack>(F, OP.scrapGt.mat(tMaterial, 9), OP.round.mat(tMaterial, 9), OP.nugget.mat(tMaterial, 9), OP.chunkGt.mat(tMaterial, 4), OP.ingot.mat(tMaterial, 1), OP.gem.mat(tMaterial, 1), OP.gemFlawed.mat(tMaterial, 2), OP.gemChipped.mat(tMaterial, 4), OP.bolt.mat(tMaterial, 8), OP.ring.mat(tMaterial, 4), OP.stick.mat(tMaterial, 2), OP.stickLong.mat(tMaterial, 1), OP.casingSmall.mat(tMaterial, 2), OP.gearGtSmall.mat(tMaterial, 1), OP.wireGt01.mat(tMaterial, 2), OP.wireGt02.mat(tMaterial, 1), OP.plateTiny.mat(tMaterial, 9), OP.plateGemTiny.mat(tMaterial, 9), OP.plate.mat(tMaterial, 1), OP.plateGem.mat(tMaterial, 1), OP.dustTiny.mat(tMaterial, 9), OP.dustSmall.mat(tMaterial, 4), OP.dust.mat(tMaterial, 1))) {
				if (tMaterial == MT.WaxBee || tMaterial == MT.WaxPlant || tMaterial == MT.WaxParaffin) {
				RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_Extruder_Bottle             .get(0), IL.Pill_Empty.get(1));
				RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_SimpleEx_Bottle             .get(0), IL.Pill_Empty.get(1));
				} else
				if (tMaterial == MT.Zr) {
				RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_Extruder_Cell               .get(0), IL.Reactor_Rod_Empty.get(1));
				} else
				if (tMaterial == MT.Glass) {
				if (!ST.equal(tStack, OP.plateGem.mat(MT.Glass, 1), T)) {
				RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_Extruder_Plate              .get(0), OP.plateGem.mat(MT.Glass, 1));
				RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_SimpleEx_Plate              .get(0), OP.plateGem.mat(MT.Glass, 1));
				}
				RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_Extruder_Cell               .get(0), OP.chemtube.mat(MT.Empty, 3));
				RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_SimpleEx_Cell               .get(0), OP.chemtube.mat(MT.Empty, 3));
				RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_Extruder_Bottle             .get(0), IL.Bottle_Empty.get(1));
				RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_SimpleEx_Bottle             .get(0), IL.Bottle_Empty.get(1));
				RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_Extruder_Block              .get(0), ST.make(Blocks.glass, 1, 0));
				RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_SimpleEx_Block              .get(0), ST.make(Blocks.glass, 1, 0));
				} else
				if (tMaterial == MT.Sn) {
				RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_Extruder_Cell               .get(0), IL.Cell_Empty.get(1));
				RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_SimpleEx_Cell               .get(0), IL.Cell_Empty.get(1));
				RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_Extruder_Bottle             .get(0), IL.IC2_Food_Can_Empty.get(2));
				RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_SimpleEx_Bottle             .get(0), IL.IC2_Food_Can_Empty.get(2));
				} else
				if (tMaterial == MT.TinAlloy) {
				RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_Extruder_Cell               .get(0), IL.Food_Can_Empty.get(1));
				RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerIngot   ,            tStack , IL.Shape_SimpleEx_Cell               .get(0), IL.Food_Can_Empty.get(1));
				}
				if (tStack.stackSize *  3 <= tStack.getMaxStackSize()) {
				if (tMaterial == MT.WaxBee) {
				RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerIngot* 3, ST.mul_( 3,tStack), IL.Shape_Extruder_Cell               .get(0), IL.FR_WaxCapsule.get(4));
				RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerIngot* 3, ST.mul_( 3,tStack), IL.Shape_SimpleEx_Cell               .get(0), IL.FR_WaxCapsule.get(4));
				} else
				if (tMaterial == MT.WaxRefractory) {
				RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerIngot* 3, ST.mul_( 3,tStack), IL.Shape_Extruder_Cell               .get(0), IL.FR_RefractoryCapsule.get(4));
				RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerIngot* 3, ST.mul_( 3,tStack), IL.Shape_SimpleEx_Cell               .get(0), IL.FR_RefractoryCapsule.get(4));
				} else
				if (tMaterial == MT.WaxMagic || tMaterial == MT.WaxAmnesic) {
				RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerIngot* 3, ST.mul_( 3,tStack), IL.Shape_Extruder_Cell               .get(0), IL.FR_MagicCapsule.get(4));
				RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerIngot* 3, ST.mul_( 3,tStack), IL.Shape_SimpleEx_Cell               .get(0), IL.FR_MagicCapsule.get(4));
				}
				}
				if (tStack.stackSize *  6 <= tStack.getMaxStackSize()) {
				if (tMaterial == MT.Plastic) {
				RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerIngot* 6, ST.mul_( 6,tStack), IL.Shape_Extruder_Cell               .get(0), IL.PlasticCan.get(1));
				RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerIngot* 6, ST.mul_( 6,tStack), IL.Shape_SimpleEx_Cell               .get(0), IL.PlasticCan.get(1));
				}
				}
			}
		}
	}
}
