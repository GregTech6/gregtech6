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

package gregtech.loaders.a;

import static gregapi.data.CS.*;

import gregapi.block.prefixblock.PrefixBlock_;
import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.MD;
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
		BlocksGT.blockRaw                   = new PrefixBlock_(MD.GT, "gt.meta.storage.raw"         , OP.blockRaw               , null                 , null, null, null                                              , Material.rock , Block.soundTypeGravel, TOOL_pickaxe, 1.5F, 4.5F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockGem                   = new PrefixBlock_(MD.GT, "gt.meta.storage.gem"         , OP.blockGem               , null                 , null, null, null                                              , Material.rock , Block.soundTypeStone , TOOL_pickaxe, 1.5F, 4.5F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockDust                  = new PrefixBlock_(MD.GT, "gt.meta.storage.dust"        , OP.blockDust              , null                 , null, null, null                                              , Material.sand , Block.soundTypeSand  , TOOL_shovel , 0.5F, 4.5F,  -2,   0, 999, 0,0,0,1,1,1, T,F,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockIngot                 = new PrefixBlock_(MD.GT, "gt.meta.storage.ingot"       , OP.blockIngot             , null                 , null, null, null                                              , Material.iron , Block.soundTypeMetal , TOOL_pickaxe, 1.0F, 3.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockPlate                 = new PrefixBlock_(MD.GT, "gt.meta.storage.plate"       , OP.blockPlate             , null                 , null, null, null                                              , Material.iron , Block.soundTypeMetal , TOOL_pickaxe, 1.0F, 3.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockPlateGem              = new PrefixBlock_(MD.GT, "gt.meta.storage.plateGem"    , OP.blockPlateGem          , null                 , null, null, null                                              , Material.iron , Block.soundTypeMetal , TOOL_pickaxe, 1.0F, 3.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.blockSolid                 = new PrefixBlock_(MD.GT, "gt.meta.storage.solid"       , OP.blockSolid             , null                 , null, null, null                                              , Material.iron , Block.soundTypeMetal , TOOL_pickaxe, 1.7F, 5.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		
		BlocksGT.casingMachine              = new PrefixBlock_(MD.GT, "gt.meta.machine"             , OP.casingMachine          , null                 , null, null, null                                              , Material.iron , Block.soundTypeMetal , TOOL_wrench , 1.0F, 3.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.casingMachineDouble        = new PrefixBlock_(MD.GT, "gt.meta.machine.double"      , OP.casingMachineDouble    , null                 , null, null, null                                              , Material.iron , Block.soundTypeMetal , TOOL_wrench , 2.0F, 6.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.casingMachineQuadruple     = new PrefixBlock_(MD.GT, "gt.meta.machine.quadruple"   , OP.casingMachineQuadruple , null                 , null, null, null                                              , Material.iron , Block.soundTypeMetal , TOOL_wrench , 4.0F,12.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.casingMachineDense         = new PrefixBlock_(MD.GT, "gt.meta.machine.dense"       , OP.casingMachineDense     , null                 , null, null, null                                              , Material.iron , Block.soundTypeMetal , TOOL_wrench , 9.0F,18.0F,   0,   0, 999, 0,0,0,1,1,1, F,T,F,F,T,T,T,T,T,T,T,T,T,T, OreDictMaterial.MATERIAL_ARRAY);
		
		boolean b = ConfigsGT.CLIENT.get("blocks.crates", "UseOverlayIcon", F);
		
		BlocksGT.crateGtRaw                 = new PrefixBlock_(MD.GT, "gt.meta.crate.raw"           , OP.crateGtRaw             , OM.stack(ANY.Wood, U), null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE), Material.wood , Block.soundTypeWood  , TOOL_crowbar, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGtGem                 = new PrefixBlock_(MD.GT, "gt.meta.crate.gem"           , OP.crateGtGem             , OM.stack(ANY.Wood, U), null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE), Material.wood , Block.soundTypeWood  , TOOL_crowbar, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGtDust                = new PrefixBlock_(MD.GT, "gt.meta.crate.dust"          , OP.crateGtDust            , OM.stack(ANY.Wood, U), null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE), Material.wood , Block.soundTypeWood  , TOOL_crowbar, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGtIngot               = new PrefixBlock_(MD.GT, "gt.meta.crate.ingot"         , OP.crateGtIngot           , OM.stack(ANY.Wood, U), null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE), Material.wood , Block.soundTypeWood  , TOOL_crowbar, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGtPlate               = new PrefixBlock_(MD.GT, "gt.meta.crate.plate"         , OP.crateGtPlate           , OM.stack(ANY.Wood, U), null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE), Material.wood , Block.soundTypeWood  , TOOL_crowbar, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGtPlateGem            = new PrefixBlock_(MD.GT, "gt.meta.crate.plateGem"      , OP.crateGtPlateGem        , OM.stack(ANY.Wood, U), null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE), Material.wood , Block.soundTypeWood  , TOOL_crowbar, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		
		BlocksGT.crateGt64Raw               = new PrefixBlock_(MD.GT, "gt.meta.crate.64.raw"        , OP.crateGt64Raw           , OM.stack(ANY.Wood, U), null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE), Material.wood , Block.soundTypeWood  , TOOL_crowbar, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGt64Gem               = new PrefixBlock_(MD.GT, "gt.meta.crate.64.gem"        , OP.crateGt64Gem           , OM.stack(ANY.Wood, U), null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE), Material.wood , Block.soundTypeWood  , TOOL_crowbar, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGt64Dust              = new PrefixBlock_(MD.GT, "gt.meta.crate.64.dust"       , OP.crateGt64Dust          , OM.stack(ANY.Wood, U), null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE), Material.wood , Block.soundTypeWood  , TOOL_crowbar, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGt64Ingot             = new PrefixBlock_(MD.GT, "gt.meta.crate.64.ingot"      , OP.crateGt64Ingot         , OM.stack(ANY.Wood, U), null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE), Material.wood , Block.soundTypeWood  , TOOL_crowbar, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGt64Plate             = new PrefixBlock_(MD.GT, "gt.meta.crate.64.plate"      , OP.crateGt64Plate         , OM.stack(ANY.Wood, U), null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE), Material.wood , Block.soundTypeWood  , TOOL_crowbar, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		BlocksGT.crateGt64PlateGem          = new PrefixBlock_(MD.GT, "gt.meta.crate.64.plateGem"   , OP.crateGt64PlateGem      , OM.stack(ANY.Wood, U), null, null, BlockTextureDefault.get(Textures.BlockIcons.CRATE), Material.wood , Block.soundTypeWood  , TOOL_crowbar, 1.0F, 0.2F,   0,   0,   0, 0,0,0,1,1,1, T,F,F,F,T,T,F,T,T,T,b,F,F,T, OreDictMaterial.MATERIAL_ARRAY);
		
		BlocksGT.openableCrowbar.add(BlocksGT.blockRaw         );
		BlocksGT.openableCrowbar.add(BlocksGT.blockGem         );
		BlocksGT.openableCrowbar.add(BlocksGT.blockDust        );
		BlocksGT.openableCrowbar.add(BlocksGT.blockIngot       );
		BlocksGT.openableCrowbar.add(BlocksGT.blockPlate       );
		BlocksGT.openableCrowbar.add(BlocksGT.blockPlateGem    );
		
		BlocksGT.openableCrowbar.add(BlocksGT.crateGtRaw       );
		BlocksGT.openableCrowbar.add(BlocksGT.crateGtGem       );
		BlocksGT.openableCrowbar.add(BlocksGT.crateGtDust      );
		BlocksGT.openableCrowbar.add(BlocksGT.crateGtIngot     );
		BlocksGT.openableCrowbar.add(BlocksGT.crateGtPlate     );
		BlocksGT.openableCrowbar.add(BlocksGT.crateGtPlateGem  );
		
		BlocksGT.openableCrowbar.add(BlocksGT.crateGt64Raw     );
		BlocksGT.openableCrowbar.add(BlocksGT.crateGt64Gem     );
		BlocksGT.openableCrowbar.add(BlocksGT.crateGt64Dust    );
		BlocksGT.openableCrowbar.add(BlocksGT.crateGt64Ingot   );
		BlocksGT.openableCrowbar.add(BlocksGT.crateGt64Plate   );
		BlocksGT.openableCrowbar.add(BlocksGT.crateGt64PlateGem);
	}
}
