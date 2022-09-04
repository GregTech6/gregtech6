/**
 * Copyright (c) 2022 GregTech-6 Team
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

import gregapi.block.MaterialAdventure;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import static gregapi.data.CS.*;

public class Loader_BlockResistance implements Runnable {
	@Override
	public void run() {
		Blocks.stone.setResistance(10);
		Blocks.cobblestone.setResistance(10);
		Blocks.stonebrick.setResistance(10);
		Blocks.brick_block.setResistance(20);
		Blocks.hardened_clay.setResistance(15);
		Blocks.stained_hardened_clay.setResistance(15);
		Blocks.iron_block.setResistance(30);
		Blocks.diamond_block.setResistance(60);
		Blocks.obsidian.setResistance(60);
		Blocks.enchanting_table.setResistance(60);
		Blocks.ender_chest.setResistance(60);
		Blocks.anvil.setResistance(60);
		Blocks.water.setResistance(30);
		Blocks.flowing_water.setResistance(30);
		Blocks.lava.setResistance(30);
		
		if (MD.SD.mLoaded) {
			Block
			tBlock = ST.block(MD.SD, "fullDrawers1"); if (tBlock != NB) {UT.Reflection.setFieldContent(Block.class, tBlock, "field_149764_J", MaterialAdventure.WOOD, T, F); UT.Reflection.setFieldContent(Block.class, tBlock, "blockMaterial", MaterialAdventure.WOOD, T, F);}
			tBlock = ST.block(MD.SD, "fullDrawers2"); if (tBlock != NB) {UT.Reflection.setFieldContent(Block.class, tBlock, "field_149764_J", MaterialAdventure.WOOD, T, F); UT.Reflection.setFieldContent(Block.class, tBlock, "blockMaterial", MaterialAdventure.WOOD, T, F);}
			tBlock = ST.block(MD.SD, "fullDrawers4"); if (tBlock != NB) {UT.Reflection.setFieldContent(Block.class, tBlock, "field_149764_J", MaterialAdventure.WOOD, T, F); UT.Reflection.setFieldContent(Block.class, tBlock, "blockMaterial", MaterialAdventure.WOOD, T, F);}
			tBlock = ST.block(MD.SD, "halfDrawers2"); if (tBlock != NB) {UT.Reflection.setFieldContent(Block.class, tBlock, "field_149764_J", MaterialAdventure.WOOD, T, F); UT.Reflection.setFieldContent(Block.class, tBlock, "blockMaterial", MaterialAdventure.WOOD, T, F);}
			tBlock = ST.block(MD.SD, "halfDrawers4"); if (tBlock != NB) {UT.Reflection.setFieldContent(Block.class, tBlock, "field_149764_J", MaterialAdventure.WOOD, T, F); UT.Reflection.setFieldContent(Block.class, tBlock, "blockMaterial", MaterialAdventure.WOOD, T, F);}
			tBlock = ST.block(MD.SD, "fullCustom1" ); if (tBlock != NB) {UT.Reflection.setFieldContent(Block.class, tBlock, "field_149764_J", MaterialAdventure.WOOD, T, F); UT.Reflection.setFieldContent(Block.class, tBlock, "blockMaterial", MaterialAdventure.WOOD, T, F);}
			tBlock = ST.block(MD.SD, "fullCustom2" ); if (tBlock != NB) {UT.Reflection.setFieldContent(Block.class, tBlock, "field_149764_J", MaterialAdventure.WOOD, T, F); UT.Reflection.setFieldContent(Block.class, tBlock, "blockMaterial", MaterialAdventure.WOOD, T, F);}
			tBlock = ST.block(MD.SD, "fullCustom4" ); if (tBlock != NB) {UT.Reflection.setFieldContent(Block.class, tBlock, "field_149764_J", MaterialAdventure.WOOD, T, F); UT.Reflection.setFieldContent(Block.class, tBlock, "blockMaterial", MaterialAdventure.WOOD, T, F);}
			tBlock = ST.block(MD.SD, "halfCustom2" ); if (tBlock != NB) {UT.Reflection.setFieldContent(Block.class, tBlock, "field_149764_J", MaterialAdventure.WOOD, T, F); UT.Reflection.setFieldContent(Block.class, tBlock, "blockMaterial", MaterialAdventure.WOOD, T, F);}
			tBlock = ST.block(MD.SD, "halfCustom4" ); if (tBlock != NB) {UT.Reflection.setFieldContent(Block.class, tBlock, "field_149764_J", MaterialAdventure.WOOD, T, F); UT.Reflection.setFieldContent(Block.class, tBlock, "blockMaterial", MaterialAdventure.WOOD, T, F);}
		}
		
		Block
		tBlock = IL.EtFu_Obsidian      .block(); if (tBlock != null && tBlock != NB) tBlock.setResistance(60);
		tBlock = IL.NeLi_Obsidian      .block(); if (tBlock != null && tBlock != NB) tBlock.setResistance(60);
		tBlock = IL.NePl_Obsidian      .block(); if (tBlock != null && tBlock != NB) tBlock.setResistance(60);
		tBlock = IL.NePl_Ancient_Debris.block(); if (tBlock != null && tBlock != NB) tBlock.setResistance(60);
		tBlock = IL.EtFu_Ancient_Debris.block(); if (tBlock != null && tBlock != NB) tBlock.setResistance(60);
	}
}
