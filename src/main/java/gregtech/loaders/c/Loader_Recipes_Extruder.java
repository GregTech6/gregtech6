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

package gregtech.loaders.c;

import static gregapi.data.CS.*;

import gregapi.block.metatype.BlockStones;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.BlocksGT;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.data.TD;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class Loader_Recipes_Extruder implements Runnable {
	@Override
	public void run() {
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Plate       .get(0), OP.plate.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Plate_Curved.get(0), OP.plateCurved.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Rod         .get(0), OP.stick.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Rod_Long    .get(0), OP.stickLong.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  16, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Bolt        .get(0), OP.bolt.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Ingot       .get(0), IL.NeLi_Blackstone_Bricks.get(1, IL.NePl_Blackstone_Bricks.get(1, ST.make(BlocksGT.Basalt, 1, BlockStones.BRICK))));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Block       .get(0), IL.NeLi_Blackstone_Polished.get(1, IL.NePl_Blackstone_Polished.get(1, ST.make(BlocksGT.Basalt, 1, BlockStones.STONE))));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Shovel      .get(0), OP.toolHeadRawShovel.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Sword       .get(0), OP.toolHeadRawSword.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Hoe         .get(0), OP.toolHeadRawHoe.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Axe         .get(0), OP.toolHeadRawAxe.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 256, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Gear        .get(0), OP.gearGt.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Gear_Small  .get(0), OP.gearGtSmall.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 384, IL.Module_Blackstone_Generator.get(0), IL.Shape_Extruder_Hammer      .get(0), OP.toolHeadHammer.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Plate       .get(0), OP.plate.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Plate_Curved.get(0), OP.plateCurved.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Rod         .get(0), OP.stick.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Rod_Long    .get(0), OP.stickLong.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  16, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Bolt        .get(0), OP.bolt.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Ingot       .get(0), IL.NeLi_Blackstone_Bricks.get(1, IL.NePl_Blackstone_Bricks.get(1, ST.make(BlocksGT.Basalt, 1, BlockStones.BRICK))));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Block       .get(0), IL.NeLi_Blackstone_Polished.get(1, IL.NePl_Blackstone_Polished.get(1, ST.make(BlocksGT.Basalt, 1, BlockStones.STONE))));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Shovel      .get(0), OP.toolHeadRawShovel.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Sword       .get(0), OP.toolHeadRawSword.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Hoe         .get(0), OP.toolHeadRawHoe.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Axe         .get(0), OP.toolHeadRawAxe.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 256, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Gear        .get(0), OP.gearGt.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Gear_Small  .get(0), OP.gearGtSmall.mat(MT.STONES.Blackstone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 384, IL.Module_Blackstone_Generator.get(0), IL.Shape_SimpleEx_Hammer      .get(0), OP.toolHeadHammer.mat(MT.STONES.Blackstone, 1));
		
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Plate       .get(0), OP.plate.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Plate_Curved.get(0), OP.plateCurved.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Rod         .get(0), OP.stick.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Rod_Long    .get(0), OP.stickLong.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  16, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Bolt        .get(0), OP.bolt.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Ingot       .get(0), IL.NeLi_Basalt_Polished.get(1, IL.NePl_Basalt_Polished.get(1, ST.make(BlocksGT.Basalt, 1, BlockStones.BRICK))));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Block       .get(0), IL.NeLi_Basalt.get(1, IL.NePl_Basalt.get(1, ST.make(BlocksGT.Basalt, 1, BlockStones.STONE))));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Shovel      .get(0), OP.toolHeadRawShovel.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Sword       .get(0), OP.toolHeadRawSword.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Hoe         .get(0), OP.toolHeadRawHoe.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Axe         .get(0), OP.toolHeadRawAxe.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 256, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Gear        .get(0), OP.gearGt.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Gear_Small  .get(0), OP.gearGtSmall.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 384, IL.Module_Basalt_Generator.get(0), IL.Shape_Extruder_Hammer      .get(0), OP.toolHeadHammer.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Plate       .get(0), OP.plate.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Plate_Curved.get(0), OP.plateCurved.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Rod         .get(0), OP.stick.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Rod_Long    .get(0), OP.stickLong.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  16, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Bolt        .get(0), OP.bolt.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Ingot       .get(0), IL.NeLi_Basalt_Polished.get(1, IL.NePl_Basalt_Polished.get(1, ST.make(BlocksGT.Basalt, 1, BlockStones.BRICK))));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Block       .get(0), IL.NeLi_Basalt.get(1, IL.NePl_Basalt.get(1, ST.make(BlocksGT.Basalt, 1, BlockStones.STONE))));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Shovel      .get(0), OP.toolHeadRawShovel.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Sword       .get(0), OP.toolHeadRawSword.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Hoe         .get(0), OP.toolHeadRawHoe.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Axe         .get(0), OP.toolHeadRawAxe.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 256, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Gear        .get(0), OP.gearGt.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Gear_Small  .get(0), OP.gearGtSmall.mat(MT.STONES.Basalt, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 384, IL.Module_Basalt_Generator.get(0), IL.Shape_SimpleEx_Hammer      .get(0), OP.toolHeadHammer.mat(MT.STONES.Basalt, 1));
		
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Plate       .get(0), OP.plate.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Plate_Curved.get(0), OP.plateCurved.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Rod         .get(0), OP.stick.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Rod_Long    .get(0), OP.stickLong.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  16, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Bolt        .get(0), OP.bolt.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Ingot       .get(0), ST.make(Blocks.stonebrick, 1, 0));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Block       .get(0), ST.make(Blocks.stone, 1, 0));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Shovel      .get(0), OP.toolHeadRawShovel.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Sword       .get(0), OP.toolHeadRawSword.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Hoe         .get(0), OP.toolHeadRawHoe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Axe         .get(0), OP.toolHeadRawAxe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 256, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Gear        .get(0), OP.gearGt.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Gear_Small  .get(0), OP.gearGtSmall.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 384, IL.Module_Stone_Generator.get(0), IL.Shape_Extruder_Hammer      .get(0), OP.toolHeadHammer.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Plate       .get(0), OP.plate.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Plate_Curved.get(0), OP.plateCurved.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Rod         .get(0), OP.stick.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Rod_Long    .get(0), OP.stickLong.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  16, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Bolt        .get(0), OP.bolt.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Ingot       .get(0), ST.make(Blocks.stonebrick, 1, 0));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Block       .get(0), ST.make(Blocks.stone, 1, 0));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Shovel      .get(0), OP.toolHeadRawShovel.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Sword       .get(0), OP.toolHeadRawSword.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Hoe         .get(0), OP.toolHeadRawHoe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Axe         .get(0), OP.toolHeadRawAxe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 256, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Gear        .get(0), OP.gearGt.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Gear_Small  .get(0), OP.gearGtSmall.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 384, IL.Module_Stone_Generator.get(0), IL.Shape_SimpleEx_Hammer      .get(0), OP.toolHeadHammer.mat(MT.Stone, 1));
		
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_Extruder_Plate       .get(0), OP.plate.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_Extruder_Plate_Curved.get(0), OP.plateCurved.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_Extruder_Rod         .get(0), OP.stick.mat(MT.Stone, 2));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_Extruder_Rod_Long    .get(0), OP.stickLong.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,   8, ST.make(Blocks.stone, 1, W), IL.Shape_Extruder_Bolt        .get(0), OP.bolt.mat(MT.Stone, 8));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_Extruder_Ingot       .get(0), ST.make(Blocks.stonebrick, 1, 0));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_Extruder_Block       .get(0), ST.make(Blocks.stone, 1, 0));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_Extruder_Shovel      .get(0), OP.toolHeadRawShovel.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.make(Blocks.stone, 2, W), IL.Shape_Extruder_Sword       .get(0), OP.toolHeadRawSword.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.make(Blocks.stone, 2, W), IL.Shape_Extruder_Hoe         .get(0), OP.toolHeadRawHoe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.make(Blocks.stone, 3, W), IL.Shape_Extruder_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.make(Blocks.stone, 3, W), IL.Shape_Extruder_Axe         .get(0), OP.toolHeadRawAxe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, ST.make(Blocks.stone, 4, W), IL.Shape_Extruder_Gear        .get(0), OP.gearGt.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_Extruder_Gear_Small  .get(0), OP.gearGtSmall.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, ST.make(Blocks.stone, 6, W), IL.Shape_Extruder_Hammer      .get(0), OP.toolHeadHammer.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_SimpleEx_Plate       .get(0), OP.plate.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_SimpleEx_Plate_Curved.get(0), OP.plateCurved.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_SimpleEx_Rod         .get(0), OP.stick.mat(MT.Stone, 2));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_SimpleEx_Rod_Long    .get(0), OP.stickLong.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,   8, ST.make(Blocks.stone, 1, W), IL.Shape_SimpleEx_Bolt        .get(0), OP.bolt.mat(MT.Stone, 8));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_SimpleEx_Ingot       .get(0), ST.make(Blocks.stonebrick, 1, 0));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_SimpleEx_Block       .get(0), ST.make(Blocks.stone, 1, 0));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_SimpleEx_Shovel      .get(0), OP.toolHeadRawShovel.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.make(Blocks.stone, 2, W), IL.Shape_SimpleEx_Sword       .get(0), OP.toolHeadRawSword.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.make(Blocks.stone, 2, W), IL.Shape_SimpleEx_Hoe         .get(0), OP.toolHeadRawHoe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.make(Blocks.stone, 3, W), IL.Shape_SimpleEx_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.make(Blocks.stone, 3, W), IL.Shape_SimpleEx_Axe         .get(0), OP.toolHeadRawAxe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, ST.make(Blocks.stone, 4, W), IL.Shape_SimpleEx_Gear        .get(0), OP.gearGt.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.stone, 1, W), IL.Shape_SimpleEx_Gear_Small  .get(0), OP.gearGtSmall.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, ST.make(Blocks.stone, 6, W), IL.Shape_SimpleEx_Hammer      .get(0), OP.toolHeadHammer.mat(MT.Stone, 1));
		
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_Extruder_Plate       .get(0), OP.plate.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_Extruder_Plate_Curved.get(0), OP.plateCurved.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_Extruder_Rod         .get(0), OP.stick.mat(MT.Stone, 2));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_Extruder_Rod_Long    .get(0), OP.stickLong.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,   8, ST.make(Blocks.cobblestone, 1, W), IL.Shape_Extruder_Bolt        .get(0), OP.bolt.mat(MT.Stone, 8));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_Extruder_Ingot       .get(0), ST.make(Blocks.stonebrick, 1, 0));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_Extruder_Block       .get(0), ST.make(Blocks.stone, 1, 0));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_Extruder_Shovel      .get(0), OP.toolHeadRawShovel.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.make(Blocks.cobblestone, 2, W), IL.Shape_Extruder_Sword       .get(0), OP.toolHeadRawSword.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.make(Blocks.cobblestone, 2, W), IL.Shape_Extruder_Hoe         .get(0), OP.toolHeadRawHoe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.make(Blocks.cobblestone, 3, W), IL.Shape_Extruder_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.make(Blocks.cobblestone, 3, W), IL.Shape_Extruder_Axe         .get(0), OP.toolHeadRawAxe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, ST.make(Blocks.cobblestone, 4, W), IL.Shape_Extruder_Gear        .get(0), OP.gearGt.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_Extruder_Gear_Small  .get(0), OP.gearGtSmall.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, ST.make(Blocks.cobblestone, 6, W), IL.Shape_Extruder_Hammer      .get(0), OP.toolHeadHammer.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_SimpleEx_Plate       .get(0), OP.plate.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_SimpleEx_Plate_Curved.get(0), OP.plateCurved.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_SimpleEx_Rod         .get(0), OP.stick.mat(MT.Stone, 2));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_SimpleEx_Rod_Long    .get(0), OP.stickLong.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,   8, ST.make(Blocks.cobblestone, 1, W), IL.Shape_SimpleEx_Bolt        .get(0), OP.bolt.mat(MT.Stone, 8));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_SimpleEx_Ingot       .get(0), ST.make(Blocks.stonebrick, 1, 0));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_SimpleEx_Block       .get(0), ST.make(Blocks.stone, 1, 0));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_SimpleEx_Shovel      .get(0), OP.toolHeadRawShovel.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.make(Blocks.cobblestone, 2, W), IL.Shape_SimpleEx_Sword       .get(0), OP.toolHeadRawSword.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  64, ST.make(Blocks.cobblestone, 2, W), IL.Shape_SimpleEx_Hoe         .get(0), OP.toolHeadRawHoe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.make(Blocks.cobblestone, 3, W), IL.Shape_SimpleEx_Pickaxe     .get(0), OP.toolHeadRawPickaxe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  96, ST.make(Blocks.cobblestone, 3, W), IL.Shape_SimpleEx_Axe         .get(0), OP.toolHeadRawAxe.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 128, ST.make(Blocks.cobblestone, 4, W), IL.Shape_SimpleEx_Gear        .get(0), OP.gearGt.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16,  32, ST.make(Blocks.cobblestone, 1, W), IL.Shape_SimpleEx_Gear_Small  .get(0), OP.gearGtSmall.mat(MT.Stone, 1));
		RM.Extruder.addRecipe2(T, F, F, F, T, 16, 192, ST.make(Blocks.cobblestone, 6, W), IL.Shape_SimpleEx_Hammer      .get(0), OP.toolHeadHammer.mat(MT.Stone, 1));
		
		
		// Iterate over all possible Extruder Fodder that, for simplicity sake, is not too big or small.
		ArrayListNoNulls<OreDictPrefix> tPrefixList = new ArrayListNoNulls<>();
		for (OreDictPrefix tPrefix : OreDictPrefix.VALUES) if (tPrefix.containsAny(TD.Prefix.EXTRUDER_FODDER, TD.Prefix.INGOT_BASED, TD.Prefix.GEM_BASED, TD.Prefix.DUST_BASED) && tPrefix.mAmount >= U64 && tPrefix.mAmount <= U && U % tPrefix.mAmount == 0) tPrefixList.add(tPrefix);
		// Yes I know the for loops inside of this only iterate over one or two things, but this looks way better than having two different Styles.
		for (OreDictPrefix tPrefix : tPrefixList) {
			// Do not add MT.Wax to this, would lead to an Exploit that allows non-food-grade Wax to be used for Pills.
			for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.WaxPlant, MT.WaxParaffin}) {
				long tEUt = 16, tCostsPerUnit = 64;
				ItemStack tStack = tPrefix.mat(tMaterial, U / tPrefix.mAmount);
				if (ST.valid(tStack)) {
					RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_Extruder_Bottle.get(0), IL.Pill_Empty.get(1));
					RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_SimpleEx_Bottle.get(0), IL.Pill_Empty.get(1));
				}
			}
			for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.WaxBee}) {
				long tEUt = 16, tCostsPerUnit = 64;
				ItemStack tStack = tPrefix.mat(tMaterial, U / tPrefix.mAmount);
				if (ST.valid(tStack)) {
					RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_Extruder_Bottle.get(0), IL.Pill_Empty.get(1));
					RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_SimpleEx_Bottle.get(0), IL.Pill_Empty.get(1));
					if (tStack.stackSize * 3 <= tStack.getMaxStackSize()) {
					RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerUnit* 3, ST.mul_( 3,tStack), IL.Shape_Extruder_Cell  .get(0), IL.FR_WaxCapsule.get(4));
					RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerUnit* 3, ST.mul_( 3,tStack), IL.Shape_SimpleEx_Cell  .get(0), IL.FR_WaxCapsule.get(4));
					}
				}
			}
			for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.WaxRefractory}) {
				long tEUt = 16, tCostsPerUnit = 64;
				ItemStack tStack = tPrefix.mat(tMaterial, U / tPrefix.mAmount);
				if (ST.valid(tStack)) {
					if (tStack.stackSize * 3 <= tStack.getMaxStackSize()) {
					RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerUnit* 3, ST.mul_( 3,tStack), IL.Shape_Extruder_Cell  .get(0), IL.FR_RefractoryCapsule.get(4));
					RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerUnit* 3, ST.mul_( 3,tStack), IL.Shape_SimpleEx_Cell  .get(0), IL.FR_RefractoryCapsule.get(4));
					}
				}
			}
			for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.WaxMagic, MT.WaxAmnesic}) {
				long tEUt = 16, tCostsPerUnit = 64;
				ItemStack tStack = tPrefix.mat(tMaterial, U / tPrefix.mAmount);
				if (ST.valid(tStack)) {
					if (tStack.stackSize * 3 <= tStack.getMaxStackSize()) {
					RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerUnit* 3, ST.mul_( 3,tStack), IL.Shape_Extruder_Cell  .get(0), IL.FR_MagicCapsule.get(4));
					RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerUnit* 3, ST.mul_( 3,tStack), IL.Shape_SimpleEx_Cell  .get(0), IL.FR_MagicCapsule.get(4));
					}
				}
			}
			for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.Glass}) {
				long tEUt = 16, tCostsPerUnit = 64;
				ItemStack tStack = tPrefix.mat(tMaterial, U / tPrefix.mAmount), tPlate = OP.plateGem.mat(tMaterial, 1);
				if (ST.valid(tStack)) {
					if (!ST.equal(tStack, tPlate, T)) {
					RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_Extruder_Plate .get(0), tPlate);
					RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_SimpleEx_Plate .get(0), tPlate);
					}
					RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_Extruder_Cell  .get(0), OP.chemtube.mat(MT.Empty, 3));
					RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_SimpleEx_Cell  .get(0), OP.chemtube.mat(MT.Empty, 3));
					RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_Extruder_Bottle.get(0), IL.Bottle_Empty.get(1));
					RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_SimpleEx_Bottle.get(0), IL.Bottle_Empty.get(1));
				}
			}
			for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.Plastic}) {
				long tEUt = 16, tCostsPerUnit = 64;
				ItemStack tStack = tPrefix.mat(tMaterial, U / tPrefix.mAmount);
				if (ST.valid(tStack)) {
					if (tStack.stackSize * 3 <= tStack.getMaxStackSize()) {
					RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerUnit* 3, ST.mul_( 3,tStack), IL.Shape_Extruder_Cell  .get(0), IL.PlasticCan.get(1));
					RM.Extruder.addRecipe2(T, F, F, F, T, tEUt, tCostsPerUnit* 3, ST.mul_( 3,tStack), IL.Shape_SimpleEx_Cell  .get(0), IL.PlasticCan.get(1));
					}
				}
			}
			for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.Sn}) {
				long tEUt = 16, tCostsPerUnit = 64;
				ItemStack tStack = tPrefix.mat(tMaterial, U / tPrefix.mAmount);
				if (ST.valid(tStack)) {
					RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_Extruder_Cell               .get(0), IL.Cell_Empty.get(1));
					RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_SimpleEx_Cell               .get(0), IL.Cell_Empty.get(1));
					RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_Extruder_Bottle             .get(0), IL.IC2_Food_Can_Empty.get(2));
					RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_SimpleEx_Bottle             .get(0), IL.IC2_Food_Can_Empty.get(2));
				}
			}
			for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.TinAlloy}) {
				long tEUt = 16, tCostsPerUnit = 64;
				ItemStack tStack = tPrefix.mat(tMaterial, U / tPrefix.mAmount);
				if (ST.valid(tStack)) {
					RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_Extruder_Cell               .get(0), IL.Food_Can_Empty.get(1));
					RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_SimpleEx_Cell               .get(0), IL.Food_Can_Empty.get(1));
				}
			}
			for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.Zr}) {
				long tEUt = 96, tCostsPerUnit = Math.max(64, (long)(((tMaterial.mMeltingPoint - DEF_ENV_TEMP) * (1 + tMaterial.getWeight(U))) / 6144));
				ItemStack tStack = tPrefix.mat(tMaterial, U / tPrefix.mAmount);
				if (ST.valid(tStack)) {
					RM.Extruder.addRecipe2(T, F, F, F, F, tEUt, tCostsPerUnit   ,            tStack , IL.Shape_Extruder_Cell               .get(0), IL.Reactor_Rod_Empty.get(1));
				}
			}
		}
	}
}
