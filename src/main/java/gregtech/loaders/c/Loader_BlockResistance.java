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

import net.minecraft.init.Blocks;

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
	}
}
