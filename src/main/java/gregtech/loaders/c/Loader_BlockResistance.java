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