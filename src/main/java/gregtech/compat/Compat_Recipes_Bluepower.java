/**
 * Copyright (c) 2023 GregTech-6 Team
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

package gregtech.compat;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.*;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static gregapi.data.CS.*;

public class Compat_Recipes_Bluepower extends CompatMods {
	public Compat_Recipes_Bluepower(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing BP Recipes.");
		for (OreDictMaterial tMat : ANY.Iron.mToThis) RM.Press.addRecipeX(T, 16, 256, ST.array((tMat==MT.Enori?OP.plateGem:OP.plate).mat(tMat, 1), OP.foil.mat(MT.Zn, 2)), ST.make(MD.BP, "zincplate", 2, 0));
		
		// The Project Table is a liability more than a useful thing. Use a less laggy Table like the one from Better Storage if you need to.
		CR.remout(MD.BP, "project_table", "auto_project_table", "circuit_table", "tiles");
		
		CR.shaped(ST.make(MD.BP, "iron_wire"        , 1, 0), CR.DEF_REV_NCC, "XXX", "X X", "XXX", 'X', OP.wireFine.dat(ANY.Fe));
		CR.shaped(ST.make(MD.BP, "copper_wire"      , 1, 0), CR.DEF_REV_NCC, "XXX", "X X", "XXX", 'X', OP.wireFine.dat(ANY.Cu));
		CR.shaped(ST.make(MD.BP, "silicon_wafer"    , 4, 0), CR.DEF_NCC    ,  "X ", " s"        , 'X', OP.plateGem.dat(ANY.Si));
		CR.shaped(ST.make(MD.BP, "red_doped_wafer"  , 4, 0), CR.DEF_NCC    ,  "X ", " s"        , 'X', OP.plateGem.dat(MT.RedstoneAlloy));
		CR.shaped(ST.make(MD.BP, "blue_doped_wafer" , 4, 0), CR.DEF_NCC    ,  "X ", " s"        , 'X', OP.plateGem.dat(MT.NikolineAlloy));
		
		CR.shaped(ST.make(MD.BP, "tiles"            , 1, 0), CR.DEF_NCC_MIR,  "XY", "YX"        , 'X', OP.plate.dat(MT.STONES.Basalt), 'Y', OP.plate.dat(MT.STONES.Marble));
		CR.shaped(ST.make(MD.BP, "circuit_table"    , 1, 0), CR.DEF_NCC    , "PDP", "PCP", "PWP", 'P', OD.plankAnyWood, 'C', OD.craftingChest, 'W', OD.craftingWorkBench, 'D', OP.plate.dat(MT.NikolineAlloy));
		
		RM.biomass(ST.make(MD.BP, "indigo_flower", 16, 0));
		
		RM.Mixer.addRecipe0(T, 16, 16, FL.array(MT.Glass.liquid(U*9, T), MT.Al2O3.liquid(U4, T)), ZL_FS, ST.make(MD.BP, "sapphire_glass", 1, 0));
		RM.Mixer.addRecipe1(T, 16, 16, ST.make(MD.BP, "sapphire_glass", 1, 0), FL.Lava         .make(1000), NF, ST.make(MD.BP, "reinforced_sapphire_glass", 1, 0));
		RM.Mixer.addRecipe1(T, 16, 16, ST.make(MD.BP, "sapphire_glass", 1, 0), FL.Lava_Pahoehoe.make(1000), NF, ST.make(MD.BP, "reinforced_sapphire_glass", 1, 0));
		if (FL.Lava_Volcanic.exists())
		RM.Mixer.addRecipe1(T, 16, 16, ST.make(MD.BP, "sapphire_glass", 1, 0), FL.Lava_Volcanic.make(1000), NF, ST.make(MD.BP, "reinforced_sapphire_glass", 1, 0));
		
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BP, "indigo_flower", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(MD.BP, "indigo_dye", 1, 0));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.BP, "indigo_flower", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(MD.BP, "indigo_dye", 1, 0));
		RM.ic2_extractor(ST.make(MD.BP, "indigo_flower", 1, 0), ST.make(MD.BP, "indigo_dye", 1, 0));
		
		RM.sawing(64, 64, F, 1000, ST.make(MD.BP, "silicon_boule", 1, 0), ST.make(MD.BP, "silicon_wafer", 16, 0));
		
		
		RM.stonetypes(MT.STONES.Marble, F, OP.rockGt.mat(MT.STONES.Marble, 4), OP.blockDust.mat(MT.STONES.Marble, 1)
		, ST.make(MD.BP, "marble", 1, 0)
		, NI
		, ST.make(MD.BP, "marble_brick", 1, 0)
		, NI
		, ST.make(MD.BP, "fancy_marble", 1, 0)
		, ST.make(MD.BP, "marble_paver", 1, 0)
		, ST.make(MD.BP, "marble_tile", 1, 0)
		, ST.make(MD.BP, "marble_brick_small", 1, 0)
		);
		
		RM.stonetypes(MT.STONES.Basalt, F, OP.rockGt.mat(MT.STONES.Basalt, 4), OP.blockDust.mat(MT.STONES.Basalt, 1)
		, ST.make(MD.BP, "basalt", 1, 0)
		, ST.make(MD.BP, "basalt_cobble", 1, 0)
		, ST.make(MD.BP, "basalt_brick", 1, 0)
		, ST.make(MD.BP, "basaltbrick_cracked", 1, 0)
		, ST.make(MD.BP, "fancy_basalt", 1, 0)
		, ST.make(MD.BP, "basalt_paver", 1, 0)
		, ST.make(MD.BP, "basalt_tile", 1, 0)
		, ST.make(MD.BP, "basalt_brick_small", 1, 0)
		);
		
		
		try {
			// Remove Tungsten Block from Volcanos
			((Object[])UT.Reflection.getFieldContent("com.bluepowermod.world.WorldGenVolcano", "ALTAR_BLOCKS"))[3] = Blocks.gold_block;
			// Make sure the Chest in those Volcanos cannot spawn any Tungsten Ingots.
			UT.Reflection.setFieldContent("com.bluepowermod.init.BPItems", "tungsten_ingot", Items.gold_ingot);
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
	}
}
