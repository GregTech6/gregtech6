package gregtech.loaders.a;

import static gregapi.data.CS.*;

import gregapi.block.prefixblock.PrefixBlock_;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.BlockTextureDefault;
import gregapi.util.OM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Loader_PrefixBlocks implements Runnable {
	@Override
	public void run() {
        OUT.println("GT_Mod: Register PrefixBlocks.");
        
		BlocksGT.blockGem					= new PrefixBlock_(MD.GT, "gt.meta.storage.gem"			, OP.blockGem				, null					, null, null, null													, Material.rock	, Block.soundTypeStone	, TOOL_pickaxe	, 1.5F, 4.5F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockDust					= new PrefixBlock_(MD.GT, "gt.meta.storage.dust"		, OP.blockDust				, null					, null, null, null													, Material.sand	, Block.soundTypeSand	, TOOL_shovel	, 0.5F, 4.5F,  -2,   0, 999, 0,0,0,1,1,1, T,F,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockIngot					= new PrefixBlock_(MD.GT, "gt.meta.storage.ingot"		, OP.blockIngot				, null					, null, null, null													, Material.iron	, Block.soundTypeMetal	, TOOL_pickaxe	, 1.0F, 3.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockPlate					= new PrefixBlock_(MD.GT, "gt.meta.storage.plate"		, OP.blockPlate				, null					, null, null, null													, Material.iron	, Block.soundTypeMetal	, TOOL_pickaxe	, 1.0F, 3.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockPlateGem				= new PrefixBlock_(MD.GT, "gt.meta.storage.plateGem"	, OP.blockPlateGem			, null					, null, null, null													, Material.iron	, Block.soundTypeMetal	, TOOL_pickaxe	, 1.0F, 3.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockSolid					= new PrefixBlock_(MD.GT, "gt.meta.storage.solid"		, OP.blockSolid				, null					, null, null, null													, Material.iron	, Block.soundTypeMetal	, TOOL_pickaxe	, 1.7F, 5.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		
		BlocksGT.casingMachine				= new PrefixBlock_(MD.GT, "gt.meta.machine"				, OP.casingMachine			, null					, null, null, null													, Material.iron	, Block.soundTypeMetal	, TOOL_wrench	, 1.0F, 3.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.casingMachineDouble		= new PrefixBlock_(MD.GT, "gt.meta.machine.double"		, OP.casingMachineDouble	, null					, null, null, null													, Material.iron	, Block.soundTypeMetal	, TOOL_wrench	, 2.0F, 6.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.casingMachineQuadruple		= new PrefixBlock_(MD.GT, "gt.meta.machine.quadruple"	, OP.casingMachineQuadruple	, null					, null, null, null													, Material.iron	, Block.soundTypeMetal	, TOOL_wrench	, 4.0F,12.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.casingMachineDense			= new PrefixBlock_(MD.GT, "gt.meta.machine.dense"		, OP.casingMachineDense		, null					, null, null, null													, Material.iron	, Block.soundTypeMetal	, TOOL_wrench	, 9.0F,18.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		
		boolean b = ConfigsGT.CLIENT.get("blocks.crates", "UseOverlayIcon", F);
		
		BlocksGT.crateGtGem					= new PrefixBlock_(MD.GT, "gt.meta.crate.gem"			, OP.crateGtGem				, OM.stack(MT.Wood, U)	, null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)	, Material.wood	, Block.soundTypeWood	, TOOL_crowbar	, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGtDust				= new PrefixBlock_(MD.GT, "gt.meta.crate.dust"			, OP.crateGtDust			, OM.stack(MT.Wood, U)	, null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)	, Material.wood	, Block.soundTypeWood	, TOOL_crowbar	, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGtIngot				= new PrefixBlock_(MD.GT, "gt.meta.crate.ingot"			, OP.crateGtIngot			, OM.stack(MT.Wood, U)	, null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)	, Material.wood	, Block.soundTypeWood	, TOOL_crowbar	, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGtPlate				= new PrefixBlock_(MD.GT, "gt.meta.crate.plate"			, OP.crateGtPlate			, OM.stack(MT.Wood, U)	, null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)	, Material.wood	, Block.soundTypeWood	, TOOL_crowbar	, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGtPlateGem			= new PrefixBlock_(MD.GT, "gt.meta.crate.plateGem"		, OP.crateGtPlateGem		, OM.stack(MT.Wood, U)	, null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE)	, Material.wood	, Block.soundTypeWood	, TOOL_crowbar	, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
	}
}