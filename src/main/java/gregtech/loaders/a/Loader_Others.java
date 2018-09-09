package gregtech.loaders.a;

import static gregapi.data.CS.*;

import gregapi.block.MaterialMachines;
import gregapi.block.MaterialScoopable;
import gregapi.block.multitileentity.MultiTileEntityBlock;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.MD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Loader_Others implements Runnable {
	@Override
	public void run() {
		OUT.println("GT_Mod: Register own MultiTileEntity Stuff.");
		
		new MultiTileEntityRegistry("gt.multitileentity");
		
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "iron"			, Material.iron					, Block.soundTypeMetal	, TOOL_pickaxe		, 0, 0, 15, F, F);
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "iron"			, Material.iron					, Block.soundTypeMetal	, TOOL_shovel		, 0, 0, 15, F, F);
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "machine"		, MaterialMachines.instance		, Block.soundTypeMetal	, TOOL_cutter		, 0, 0, 15, F, F);
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "machine"		, MaterialMachines.instance		, Block.soundTypeMetal	, TOOL_wrench		, 0, 0, 15, F, F);
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "wood"			, Material.wood					, Block.soundTypeWood	, TOOL_axe			, 0, 0, 15, F, F);
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "leaves"		, Material.leaves				, Block.soundTypeGrass	, TOOL_axe			, 0, 0, 15, F, F);
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "rock"			, Material.rock					, Block.soundTypeStone	, TOOL_pickaxe		, 0, 0, 15, F, F);
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "cloth"			, Material.cloth				, Block.soundTypeCloth	, TOOL_shears		, 0, 0, 15, F, F);
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "tnt"			, Material.tnt					, Block.soundTypeGrass	, TOOL_pickaxe		, 0, 0, 15, F, F);
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "redstoneLight"	, Material.redstoneLight		, Block.soundTypeMetal	, TOOL_pickaxe		, 0, 0, 15, F, F).setMapColor(Material.iron.getMaterialMapColor());
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "redstoneLight"	, Material.redstoneLight		, Block.soundTypeStone	, TOOL_pickaxe		, 0, 0, 15, F, F).setMapColor(Material.rock.getMaterialMapColor());
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "redstoneLight"	, Material.redstoneLight		, Block.soundTypeWood	, TOOL_axe			, 0, 0, 15, F, F).setMapColor(Material.wood.getMaterialMapColor());
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "redstoneLight"	, Material.redstoneLight		, Block.soundTypeCloth	, TOOL_shears		, 0, 0, 15, F, F).setMapColor(Material.cloth.getMaterialMapColor());
		MultiTileEntityBlock.getOrCreate(MD.GT.mID, "rock"			, MaterialScoopable.instance	, Block.soundTypeWood	, TOOL_scoop		, 0, 0, 15, F, F);
	}
}