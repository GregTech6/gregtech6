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

import gregapi.block.metatype.BlockStones;
import gregapi.config.ConfigCategories;
import gregapi.data.*;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static gregapi.data.CS.*;
import static gregapi.data.OP.*;
import static gregapi.util.CR.*;

public class Loader_Recipes_Vanilla implements Runnable {
	@Override public void run() {
		RM.rem_smelting(ST.make(Items.bone, 1, W), ST.make(Items.slime_ball, 1, W));
		RM.rem_smelting(ST.make(Items.dye , 1, W), ST.make(Items.slime_ball, 1, W));
		
		CR.remove(ST.make(Items.reeds, 1, 0));
		CR.remove(ST.make(Items.reeds, 1, 0), ST.make(Items.reeds, 1, 0), ST.make(Items.reeds, 1, 0));
		CR.remove(ST.make(Blocks.cobblestone, 1, 0), ST.make(Items.quartz, 1, 0), NI, ST.make(Items.quartz, 1, 0), ST.make(Blocks.cobblestone, 1, 0));
		CR.remove(ST.make(Items.blaze_rod, 1, 0));
		CR.remove(ST.make(Items.bone, 1, 0));
		
		CR.shapeless(dust.mat(MT.White, 1), DEF, new Object[] {Items.bone});
		CR.shaped(ST.make(Items.paper, 1, 0), DEF, "XXX", 'X', Items.reeds);
		CR.shapeless(ST.make(Items.book, 1, 0), DEF, new Object[] {OD.craftingLeather, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty});
		
		CR.shaped(ST.make(Blocks.furnace, 1, 0), DEF_NCC, "XXX", "XFX", "XXX", 'X', cobblestone                , 'F', OD.craftingFirestarter);
		CR.shaped(ST.make(Blocks.furnace, 1, 0), DEF_NCC, "XXX", "XFX", "XXX", 'X', stone.dat(MT.STONES.Basalt), 'F', OD.craftingFirestarter);
		CR.shaped(ST.make(Blocks.furnace, 1, 0), DEF_NCC, "XXX", "XFX", "XXX", 'X', rockGt.dat(ANY.Stone)      , 'F', OD.craftingFirestarter);
		
		CR.delate(ST.make(Blocks.enchanting_table, 1, 0));
		CR.delate(ST.make(Blocks.ender_chest, 1, 0));
		CR.delate(ST.make(Blocks.furnace, 1, 0));
		CR.delate(ST.make(Items.saddle, 1, 0));
		CR.delate(ST.make(Items.magma_cream, 1, 0));
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "enchantmenttable", F)) {
			OUT.println("GT_Mod: Removing the Recipe of the Enchantment Table, to have Fun enchanting with the Anvil and Books from Dungeons.");
		} else {
			CR.shaped(ST.make(Blocks.enchanting_table, 1, 0), CR.DEF_NCC, " B ", "DOD", "OOO", 'B', IL.TC_Thaumonomicon.get(1, ST.make(Items.book, 1, W)), 'O', blockSolid.dat(MT.Obsidian), 'D', gem.dat(ANY.Diamond));
		}
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "enderchest", F)) {
			OUT.println("GT_Mod: Removing the Recipe of the Enderchest.");
		} else {
			CR.shaped(ST.make(Blocks.ender_chest, 1, 0), CR.DEF_NCC, "OOO", "OEO", "OOO", 'O', blockSolid.dat(MT.Obsidian), 'E', gem.dat(MT.EnderEye));
		}
		
		CR.shaped(ST.make(Items.bucket, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES, "XhX", " Y ", 'Y', plate.dat(ANY.Fe), 'X', plateCurved.dat(ANY.Fe));
		if (!ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.Bucket", T))
		CR.shaped(ST.make(Items.bucket, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES, "X X", " X ", 'X', ingot.dat(ANY.Fe));
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.Anvil", T)) {
			CR.shaped(ST.make(Blocks.anvil, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES, "BBB", " Ih", "III", 'B', blockIngot.dat(ANY.Fe), 'I', ingot.dat(ANY.Fe));
		} else {
			CR.shaped(ST.make(Blocks.anvil, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES, "BBB", " I ", "III", 'B', blockIngot.dat(ANY.Fe), 'I', ingot.dat(ANY.Fe));
		}
		
		ItemStack tMat = ST.make(Items.iron_ingot, 1, 0), tStack;
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.PressurePlate", T))    if (null != (tStack = CR.remove(tMat, tMat, null, null, null, null, null, null, null))) {
			CR.shaped(tStack, DEF | DEL_OTHER_SHAPED_RECIPES, "XXh", 'X', plate.dat(ANY.Fe), 'S', OD.stickAnyWood, 'I', ingot.dat(ANY.Fe));
		}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.Compass", T)) {
			CR.shaped(ST.make(Items.compass, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES | ONLY_IF_HAS_OTHER_RECIPES, "sSR", "CIC", "dPh", 'P', plate.dat(ANY.Fe), 'R', gem.dat(MT.Redstone), 'C', plateCurved.dat(ANY.Fe), 'I', stick.dat(ANY.Fe), 'S', stick.dat(MT.IronMagnetic));
		} else {
			CR.shaped(ST.make(Items.compass, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES | ONLY_IF_HAS_OTHER_RECIPES, " X ", "XRX", " X ", 'X', ingot.dat(ANY.Fe), 'R', OD.itemRedstone);
		}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.Door", T)) {
			CR.shaped(ST.make(Items.iron_door, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES | ONLY_IF_HAS_OTHER_RECIPES, "XX ", "XXh", "XX ", 'X', plate.dat(ANY.Fe));
		} else {
			CR.shaped(ST.make(Items.iron_door, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES | ONLY_IF_HAS_OTHER_RECIPES, "II" , "II" , "II" , 'I', ingot.dat(ANY.Fe));
		}
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.Cauldron", T))         if (null != (tStack = CR.remove(tMat, null, tMat, tMat, null, tMat, tMat, tMat, tMat))) {
			CR.shaped(tStack, DEF | DEL_OTHER_SHAPED_RECIPES, "X X", "XhX", "XXX", 'X', plate.dat(ANY.Fe), 'S', OD.stickAnyWood, 'I', ingot.dat(ANY.Fe));
		}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.Hopper", T))           if (null != (tStack = CR.remove(tMat, null, tMat, tMat, ST.make(Blocks.chest, 1, 0), tMat, null, tMat, null))) {
			CR.shaped(tStack, DEF | DEL_OTHER_SHAPED_RECIPES, "XwX", "XCX", " X ", 'X', plate.dat(ANY.Iron), 'S', OD.stickAnyWood, 'I', ingot.dat(ANY.Iron), 'C', OD.craftingChest);
		}
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Iron.Bars", T)) CR.remove(tMat, tMat, tMat, tMat, tMat, tMat, null, null, null);
		CR.shaped(ST.make(Blocks.iron_bars, 8, 0), DEF, " w ", "XXX", "XXX", 'X', stick.dat(ANY.Fe), 'S', OD.stickAnyWood, 'I', ingot.dat(ANY.Fe));
		
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Gold.Clock", T)) {
			CR.shaped(ST.make(Items.clock, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES | ONLY_IF_HAS_OTHER_RECIPES, "sGr", "CQC", "dPR", 'P', plate.dat(MT.Au), 'R', OD.itemRedstone, 'C', plateCurved.dat(MT.Au), 'Q', OD.craftingQuartz, 'G', gearGtSmall.dat(MT.Au));
		} else {
			CR.shaped(ST.make(Items.clock, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES | ONLY_IF_HAS_OTHER_RECIPES, " X ", "XRX", " X ", 'X', ingot.dat(MT.Au), 'R', OD.itemRedstone);
		}
		tMat = ST.make(Items.gold_ingot, 1, 0);
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Gold.PressurePlate", T))    if (null != (tStack = CR.remove(tMat, tMat, null, null, null, null, null, null, null))) {
			CR.shaped(tStack, DEF | DEL_OTHER_SHAPED_RECIPES, "XXh", 'X', plate.dat(MT.Au), 'S', OD.stickAnyWood, 'I', ingot.dat(MT.Au));
		}
		tMat = ingot.mat(MT.Rubber, 1);
		if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.recipereplacements, "Rubber.Sheet", T))          if (null != (tStack = CR.remove(tMat, tMat, tMat, tMat, tMat, tMat, null, null, null))) {
			CR.shaped(tStack, DEF | DEL_OTHER_SHAPED_RECIPES, "XXX", "XXX", 'X', plate.dat(MT.Rubber));
		}
		
		CR.shaped(ST.make(Blocks.wooden_pressure_plate, 1, 0)   , DEF, "PP", 'P', OD.plankAnyWood);
		CR.shaped(ST.make(Blocks.stone_button, 2, 0)            , DEF, "S", "S", 'S', Blocks.stone);
		CR.shaped(ST.make(Blocks.stone_button, 2, 0)            , DEF, "S", "S", 'S', stone);
		CR.shaped(ST.make(Blocks.stone_pressure_plate, 1, 0)    , DEF, "SS", 'S', Blocks.stone);
		CR.shaped(ST.make(Blocks.stone_pressure_plate, 1, 0)    , DEF, "SS", 'S', stone);
		
		CR.shapeless(stick    .mat(MT.IronMagnetic, 1), DEF, new Object[] {stick    .dat(ANY.Fe), OD.itemRedstone, OD.itemRedstone, OD.itemRedstone, OD.itemRedstone});
		CR.shapeless(stickLong.mat(MT.IronMagnetic, 1), DEF, new Object[] {stickLong.dat(ANY.Fe), OD.itemRedstone, OD.itemRedstone, OD.itemRedstone, OD.itemRedstone, OD.itemRedstone, OD.itemRedstone, OD.itemRedstone, OD.itemRedstone});
		
		CR.shaped(IL.Dye_Bonemeal.get(1), DEF, "h", "X", 'X', Items.bone);
		
		
		
		// Making sure Nether Quartz Blocks cannot be made using OreDict Nether Quartz to ensure Compat with Mods that have different Quartzes.
		CR.remove(ST.make(Items.quartz, 1, 0), ST.make(Items.quartz, 1, 0), NI, ST.make(Items.quartz, 1, 0), ST.make(Items.quartz, 1, 0));
		CR.shaped(ST.make(Blocks.quartz_block               , 1, 0), DEF    ,         "XX",  "XX", 'X', Items.quartz);
		CR.shaped(ST.make(Blocks.quartz_stairs              , 1, 0), DEF_MIR, "  X", " XX", "XXX", 'X', Items.quartz);
		CR.shaped(ST.make(Blocks.stone_slab                 , 1, 7), DEF    ,                "XX", 'X', Items.quartz);
		
		// Making sure Glowstone Blocks cannot be made using OreDict Glowstone to ensure Compat with Mods that have different Glowstones.
		CR.remove(ST.make(Items.glowstone_dust, 1, 0), ST.make(Items.glowstone_dust, 1, 0), NI, ST.make(Items.glowstone_dust, 1, 0), ST.make(Items.glowstone_dust, 1, 0));
		CR.shaped(ST.make(Blocks.glowstone                  , 1, 0), DEF    , "XX", "XX", 'X', Items.glowstone_dust);
		
		// Make Rock Blocks out of small Rocks.
		RM.pack(rockGt.mat(MT.Stone     , 4), ST.make(Blocks.cobblestone, 1, 0));
		RM.pack(rockGt.mat(MT.Netherrack, 4), ST.make(Blocks.netherrack , 1, 0));
		RM.pack(rockGt.mat(MT.Endstone  , 4), ST.make(Blocks.end_stone  , 1, 0));
		CR.shaped(ST.make(Blocks.netherrack                 , 1, 0), DEF    , "XX", "XX", 'X', rockGt.dat(MT.Netherrack));
		CR.shaped(ST.make(Blocks.end_stone                  , 1, 0), DEF    , "XX", "XX", 'X', rockGt.dat(MT.Endstone));
		CR.shaped(ST.make(Blocks.cobblestone                , 1, 0), DEF    , "XX", "XX", 'X', rockGt.dat(MT.Stone));
		CR.shaped(ST.make(Blocks.stone_stairs               , 1, 0), DEF_MIR, " X", "XX", 'X', rockGt.dat(MT.Stone));
		CR.shaped(ST.make(Blocks.stone_slab                 , 1, 3), DEF    , "  ", "XX", 'X', rockGt.dat(MT.Stone));
		CR.shaped(ST.make(Blocks.stone_stairs               , 4, 0), DEF_MIR, " X", "XX", 'X', Blocks.cobblestone);
		CR.shaped(ST.make(Blocks.stone_slab                 , 4, 3), DEF    , "  ", "XX", 'X', Blocks.cobblestone);
		
		// Hammering and Filing Stones into different Stones.
		CR.shaped(ST.make(Blocks.gravel                     , 1, 0), DEF, "h", "X", 'X', Blocks.cobblestone);
		CR.shaped(ST.make(Blocks.cobblestone                , 1, 0), DEF, "h", "X", 'X', Blocks.stone);
		CR.shaped(ST.make(Blocks.stonebrick                 , 1, 2), DEF, "h", "X", 'X', ST.make(Blocks.stonebrick, 1, 0));
		CR.shaped(ST.make(Blocks.stonebrick                 , 1, 3), DEF, "f", "X", 'X', ST.make(Blocks.double_stone_slab, 1, 8));
		
		// Slab Stacking back to full Blocks.
		CR.shaped(ST.make(Blocks.double_stone_slab          , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 0));
		CR.shaped(ST.make(Blocks.cobblestone                , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 3));
		CR.shaped(ST.make(Blocks.brick_block                , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 4));
		CR.shaped(ST.make(Blocks.stonebrick                 , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 5));
		CR.shaped(ST.make(Blocks.nether_brick               , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 6));
		CR.shaped(ST.make(Blocks.quartz_block               , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 7));
		CR.shaped(ST.make(Blocks.double_stone_slab          , 1, 8), DEF, "B", "B", 'B', ST.make(Blocks.stone_slab, 1, 8));
		CR.shaped(ST.make(Blocks.planks                     , 1, 0), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 0));
		CR.shaped(ST.make(Blocks.planks                     , 1, 1), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 1));
		CR.shaped(ST.make(Blocks.planks                     , 1, 2), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 2));
		CR.shaped(ST.make(Blocks.planks                     , 1, 3), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 3));
		CR.shaped(ST.make(Blocks.planks                     , 1, 4), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 4));
		CR.shaped(ST.make(Blocks.planks                     , 1, 5), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 5));
		CR.shaped(ST.make(Blocks.planks                     , 1, 6), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 6));
		CR.shaped(ST.make(Blocks.planks                     , 1, 7), DEF, "B", "B", 'B', ST.make(Blocks.wooden_slab, 1, 7));
		
		// Other Slab Stuff
		CR.shaped(ST.make(Blocks.stone_slab                 , 1, 4), DEF, "BB", 'B', ingot.dat(MT.Brick));
		CR.shapeless(ST.make(Blocks.double_stone_slab       , 1, 8), DEF, new Object[] {ST.make(Blocks.double_stone_slab, 1, 0)});
		CR.shapeless(ST.make(Blocks.double_stone_slab       , 1, 0), DEF, new Object[] {ST.make(Blocks.double_stone_slab, 1, 8)});
		
		// Make Sticks from Saplings and Dead Bushes.
		CR.shaped(stick.mat(MT.WOODS.Dead, 2), DEF, "s", "X", 'X', ST.make(Blocks.deadbush, 1, W));
		CR.shaped(stick.mat(MT.WOODS.Dead, 2), DEF, "k", "X", 'X', ST.make(Blocks.deadbush, 1, W));
		CR.shaped(stick.mat(MT.WOODS.Dead, 2), DEF, "s", "X", 'X', ST.make(Blocks.tallgrass, 1, 0));
		CR.shaped(stick.mat(MT.WOODS.Dead, 2), DEF, "k", "X", 'X', ST.make(Blocks.tallgrass, 1, 0));
		CR.shaped(IL.Stick.get(1), DEF, "s", "X", 'X', treeSapling);
		CR.shaped(IL.Stick.get(1), DEF, "k", "X", 'X', treeSapling);
		
		// Normal Torches need to be the absolute last in this Array!
		IL[] tItems = new IL[] {IL.NeLi_Bonetorch, IL.TiC_Stonetorch, IL.Torch};
		Object[] tSticks = new Object[] {Items.bone, OP.stick.dat(ANY.Stone), OD.stickAnyWood};
		
		for (int i = 0; i < tItems.length; i++) if (tItems[i].exists()) {
			// Torches, lots and lots of Torches.
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', oreRaw            .dat(MT.S));
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', gem               .dat(MT.S));
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', dust              .dat(MT.S));
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', crushed           .dat(MT.S));
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', crushedPurified   .dat(MT.S));
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', crushedCentrifuged.dat(MT.S));
			
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', dustTiny          .dat(MT.Blaze));
			
			CR.shaped(tItems[i].get(6), DEF, "X", "S", 'S', tSticks[i], 'X', oreRaw            .dat(MT.P));
			CR.shaped(tItems[i].get(6), DEF, "X", "S", 'S', tSticks[i], 'X', gem               .dat(MT.P));
			CR.shaped(tItems[i].get(6), DEF, "X", "S", 'S', tSticks[i], 'X', dust              .dat(MT.P));
			CR.shaped(tItems[i].get(6), DEF, "X", "S", 'S', tSticks[i], 'X', crushed           .dat(MT.P));
			CR.shaped(tItems[i].get(6), DEF, "X", "S", 'S', tSticks[i], 'X', crushedPurified   .dat(MT.P));
			CR.shaped(tItems[i].get(6), DEF, "X", "S", 'S', tSticks[i], 'X', crushedCentrifuged.dat(MT.P));
			
			CR.shaped(tItems[i].get(6), DEF, "X", "S", 'S', tSticks[i], 'X', oreRaw            .dat(ANY.Phosphorus));
			CR.shaped(tItems[i].get(6), DEF, "X", "S", 'S', tSticks[i], 'X', gem               .dat(ANY.Phosphorus));
			CR.shaped(tItems[i].get(6), DEF, "X", "S", 'S', tSticks[i], 'X', dust              .dat(ANY.Phosphorus));
			CR.shaped(tItems[i].get(6), DEF, "X", "S", 'S', tSticks[i], 'X', crushed           .dat(ANY.Phosphorus));
			CR.shaped(tItems[i].get(6), DEF, "X", "S", 'S', tSticks[i], 'X', crushedPurified   .dat(ANY.Phosphorus));
			CR.shaped(tItems[i].get(6), DEF, "X", "S", 'S', tSticks[i], 'X', crushedCentrifuged.dat(ANY.Phosphorus));
			
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', rockGt            .dat(MT.Peat));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', oreRaw            .dat(MT.Peat));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', ingot             .dat(MT.Peat));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', gem               .dat(MT.Peat));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', dust              .dat(MT.Peat));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', crushed           .dat(MT.Peat));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', crushedPurified   .dat(MT.Peat));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', crushedCentrifuged.dat(MT.Peat));
			
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', rockGt            .dat(MT.PeatBituminous));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', oreRaw            .dat(MT.PeatBituminous));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', ingot             .dat(MT.PeatBituminous));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', gem               .dat(MT.PeatBituminous));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', dust              .dat(MT.PeatBituminous));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', crushed           .dat(MT.PeatBituminous));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', crushedPurified   .dat(MT.PeatBituminous));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', crushedCentrifuged.dat(MT.PeatBituminous));
			
			CR.shaped(tItems[i].get(6), DEF, "X", "S", 'S', tSticks[i], 'X', IL.TF_Torchberries);
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', IL.NeLi_ShroomLight);
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', OD.itemResin);
			CR.shaped(tItems[i].get(1), DEF, "X", "S", 'S', tSticks[i], 'X', OD.itemGrassDry);
			
			CR.shaped(tItems[i].get(6), DEF,  "WC",  "S ", 'S', tSticks[i], 'C', OD.container1000rubbertreesap, 'W', Blocks.wool);
			CR.shaped(tItems[i].get(6), DEF,  "WC",  "S ", 'S', tSticks[i], 'C', OD.container250rubbertreesap , 'W', Blocks.wool);
			CR.shaped(tItems[i].get(6), DEF,  "WC",  "S ", 'S', tSticks[i], 'C', OD.container1000spruceresin  , 'W', Blocks.wool);
			CR.shaped(tItems[i].get(6), DEF,  "WC",  "S ", 'S', tSticks[i], 'C', OD.container250spruceresin   , 'W', Blocks.wool);
			CR.shaped(tItems[i].get(6), DEF,  "WC",  "S ", 'S', tSticks[i], 'C', OD.container1000maplesap     , 'W', Blocks.wool);
			CR.shaped(tItems[i].get(2), DEF,  "WC",  "S ", 'S', tSticks[i], 'C', OD.container250maplesap      , 'W', Blocks.wool);
			CR.shaped(tItems[i].get(8), DEF,  "WC",  "S ", 'S', tSticks[i], 'C', OD.container1000rainbowsap   , 'W', Blocks.wool);
			CR.shaped(tItems[i].get(4), DEF,  "WC",  "S ", 'S', tSticks[i], 'C', OD.container250rainbowsap    , 'W', Blocks.wool);
			CR.shaped(tItems[i].get(6), DEF,  "WC",  "S ", 'S', tSticks[i], 'C', OD.container1000creosote     , 'W', Blocks.wool);
			CR.shaped(tItems[i].get(2), DEF,  "WC",  "S ", 'S', tSticks[i], 'C', OD.container250creosote      , 'W', Blocks.wool);
			
			CR.shaped(tItems[i].get(6), DEF, "WCW", "WSW", 'S', tSticks[i], 'C', OD.container1000rubbertreesap, 'W', OD.itemString);
			CR.shaped(tItems[i].get(6), DEF, "WCW", "WSW", 'S', tSticks[i], 'C', OD.container250rubbertreesap , 'W', OD.itemString);
			CR.shaped(tItems[i].get(6), DEF, "WCW", "WSW", 'S', tSticks[i], 'C', OD.container1000spruceresin  , 'W', OD.itemString);
			CR.shaped(tItems[i].get(6), DEF, "WCW", "WSW", 'S', tSticks[i], 'C', OD.container250spruceresin   , 'W', OD.itemString);
			CR.shaped(tItems[i].get(6), DEF, "WCW", "WSW", 'S', tSticks[i], 'C', OD.container1000maplesap     , 'W', OD.itemString);
			CR.shaped(tItems[i].get(2), DEF, "WCW", "WSW", 'S', tSticks[i], 'C', OD.container250maplesap      , 'W', OD.itemString);
			CR.shaped(tItems[i].get(8), DEF, "WCW", "WSW", 'S', tSticks[i], 'C', OD.container1000rainbowsap   , 'W', OD.itemString);
			CR.shaped(tItems[i].get(4), DEF, "WCW", "WSW", 'S', tSticks[i], 'C', OD.container250rainbowsap    , 'W', OD.itemString);
			CR.shaped(tItems[i].get(6), DEF, "WCW", "WSW", 'S', tSticks[i], 'C', OD.container1000creosote     , 'W', OD.itemString);
			CR.shaped(tItems[i].get(2), DEF, "WCW", "WSW", 'S', tSticks[i], 'C', OD.container250creosote      , 'W', OD.itemString);
			
			if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.disabledrecipes, "torchesFromCoal", F)) {
			CR.remove(ST.make(Items.coal, 1, 0), NI, NI, ST.make(Items.stick, 1, 0));
			CR.remove(ST.make(Items.coal, 1, 1), NI, NI, ST.make(Items.stick, 1, 0));
			} else {
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', oreRaw            .dat(MT.Charcoal));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', ingot             .dat(MT.Charcoal));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', gem               .dat(MT.Charcoal));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', dust              .dat(MT.Charcoal));
			
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', rockGt            .dat(MT.Coal));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', oreRaw            .dat(MT.Coal));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', ingot             .dat(MT.Coal));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', gem               .dat(MT.Coal));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', dust              .dat(MT.Coal));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', crushed           .dat(MT.Coal));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', crushedPurified   .dat(MT.Coal));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', crushedCentrifuged.dat(MT.Coal));
			
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', rockGt            .dat(MT.CoalCoke));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', oreRaw            .dat(MT.CoalCoke));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', ingot             .dat(MT.CoalCoke));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', gem               .dat(MT.CoalCoke));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', dust              .dat(MT.CoalCoke));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', crushed           .dat(MT.CoalCoke));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', crushedPurified   .dat(MT.CoalCoke));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', crushedCentrifuged.dat(MT.CoalCoke));
			
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', rockGt            .dat(MT.Anthracite));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', oreRaw            .dat(MT.Anthracite));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', ingot             .dat(MT.Anthracite));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', gem               .dat(MT.Anthracite));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', dust              .dat(MT.Anthracite));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', crushed           .dat(MT.Anthracite));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', crushedPurified   .dat(MT.Anthracite));
			CR.shaped(tItems[i].get(8), DEF, "X", "S", 'S', tSticks[i], 'X', crushedCentrifuged.dat(MT.Anthracite));
			
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', rockGt            .dat(MT.Lignite));
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', oreRaw            .dat(MT.Lignite));
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', ingot             .dat(MT.Lignite));
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', gem               .dat(MT.Lignite));
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', dust              .dat(MT.Lignite));
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', crushed           .dat(MT.Lignite));
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', crushedPurified   .dat(MT.Lignite));
			CR.shaped(tItems[i].get(2), DEF, "X", "S", 'S', tSticks[i], 'X', crushedCentrifuged.dat(MT.Lignite));
			
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', rockGt            .dat(MT.LigniteCoke));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', oreRaw            .dat(MT.LigniteCoke));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', ingot             .dat(MT.LigniteCoke));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', gem               .dat(MT.LigniteCoke));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', dust              .dat(MT.LigniteCoke));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', crushed           .dat(MT.LigniteCoke));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', crushedPurified   .dat(MT.LigniteCoke));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', crushedCentrifuged.dat(MT.LigniteCoke));
			
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', rockGt            .dat(MT.Oilshale));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', oreRaw            .dat(MT.Oilshale));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', ingot             .dat(MT.Oilshale));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', gem               .dat(MT.Oilshale));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', dust              .dat(MT.Oilshale));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', crushed           .dat(MT.Oilshale));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', crushedPurified   .dat(MT.Oilshale));
			CR.shaped(tItems[i].get(4), DEF, "X", "S", 'S', tSticks[i], 'X', crushedCentrifuged.dat(MT.Oilshale));
			}
		}
		
		
		
		// Charcoal Brick-ification
		CR.shaped(ST.make(Items.coal, 1, 1), DEF, "  ", " X", 'X', ingot.dat(MT.Charcoal));
		CR.shaped(ingot.mat(MT.Charcoal, 1), DEF, "  ", " X", 'X', ST.make(Items.coal, 1, 1));
		RM.generify(ingot.mat(MT.Charcoal, 1), ST.make(Items.coal, 1, 1));
		RM.generify(ST.make(Items.coal, 1, 1), ingot.mat(MT.Charcoal, 1));
		
		// Coal Brick-ification
		CR.shaped(ST.make(Items.coal, 1, 0), DEF, "  ", " X", 'X', ingot.dat(MT.Coal));
		CR.shaped(ingot.mat(MT.Coal, 1), DEF, "  ", " X", 'X', ST.make(Items.coal, 1, 0));
		RM.generify(ingot.mat(MT.Coal, 1), ST.make(Items.coal, 1, 0));
		RM.generify(ST.make(Items.coal, 1, 0), ingot.mat(MT.Coal, 1));
		
		// Other Coal Brick-ification
		for (OreDictMaterial tCoal : new OreDictMaterial[] {MT.CoalCoke, MT.Lignite, MT.LigniteCoke, MT.PetCoke, MT.Anthracite, MT.Prismane, MT.Lonsdaleite}) {
			CR.shaped(gem.mat(tCoal, 1), DEF, "  ", " X", 'X', ingot.dat(tCoal));
			CR.shaped(ingot.mat(tCoal, 1), DEF, "  ", " X", 'X', gem.dat(tCoal));
			RM.generify(ingot.mat(tCoal, 1), gem.mat(tCoal, 1));
			RM.generify(gem.mat(tCoal, 1), ingot.mat(tCoal, 1));
		}
		
		// Netherite Interchange.
		CR.shaped(gem  .mat(MT.NetherizedDiamond, 1), DEF, "  ", " X", 'X', ingot.dat(MT.NetherizedDiamond));
		CR.shaped(ingot.mat(MT.NetherizedDiamond, 1), DEF, "  ", " X", 'X', gem  .dat(MT.NetherizedDiamond));
		RM.generify(gem  .mat(MT.NetherizedDiamond, 1), ingot.mat(MT.NetherizedDiamond, 1));
		RM.generify(ingot.mat(MT.NetherizedDiamond, 1), gem  .mat(MT.NetherizedDiamond, 1));
		
		
		CR.delate(plateDouble.mat(MT.Paper, 1));
		CR.shapeless(plateDouble.mat(MT.Paper, 1), DEF, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.itemGlue});
		CR.shapeless(plateDouble.mat(MT.Paper, 2), DEF, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.itemGlue});
		CR.shapeless(plateDouble.mat(MT.Paper, 3), DEF, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.itemGlue});
		CR.shapeless(plateDouble.mat(MT.Paper, 4), DEF, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.itemGlue});
		
		
		CR.shaped(ST.make(Items.lead                    , 1, 0), DEF_MIR                        , " SS", " GS", "S  ", 'S', OD.itemString, 'G', OD.itemTar);
		CR.shaped(ST.make(Items.lead                    , 1, 0), DEF_MIR                        , " SS", " GS", "S  ", 'S', OD.itemString, 'G', OD.itemGlue);
		CR.shaped(ST.make(Items.lead                    , 1, 0), DEF_MIR                        , " SS", " GS", "S  ", 'S', OD.itemString, 'G', OD.slimeball);
		
		CR.shaped(ST.make(Blocks.lever                  , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES ,         "W" ,  "S" , 'W', OD.stickAnyWood         , 'S', cobblestone);
		CR.shaped(ST.make(Blocks.lever                  , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES ,         "W" ,  "S" , 'W', OD.stickAnyWood         , 'S', plate.dat(ANY.Stone));
		CR.shaped(ST.make(Blocks.lever                  , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES ,         "W" ,  "S" , 'W', OD.stickAnyWood         , 'S', rockGt.dat(ANY.Stone));
		CR.shaped(ST.make(Blocks.redstone_torch         , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES ,         "D" ,  "W" , 'W', OD.stickAnyWood         , 'D', OD.itemRedstone);
		CR.shaped(ST.make(Blocks.redstone_torch         , 1, 0), DEF                            ,  "D" ,  "D" ,  "W" , 'W', OD.stickAnyWood         , 'D', gemFlawed  .dat(MT.Redstone));
		CR.shaped(ST.make(Blocks.redstone_torch         , 2, 0), DEF                            ,  "D" ,  "W" ,  "W" , 'W', OD.stickAnyWood         , 'D', gemFlawless.dat(MT.Redstone));
		CR.shaped(ST.make(Items.repeater                , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , "D D", "WDW", "SSS", 'W', OD.stickAnyWood         , 'D', OD.itemRedstone  , 'S', stoneSmooth         );
		CR.shaped(ST.make(Items.repeater                , 1, 0), DEF                            , "D D", "WDW", "SSS", 'W', OD.stickAnyWood         , 'D', OD.itemRedstone  , 'S', plate.dat(ANY.Stone));
		CR.shaped(ST.make(Items.repeater                , 1, 0), DEF                            ,        "TDT", "SSS", 'T', OD.craftingRedstoneTorch, 'D', OD.itemRedstone  , 'S', stoneSmooth         );
		CR.shaped(ST.make(Items.repeater                , 1, 0), DEF                            ,        "TDT", "SSS", 'T', OD.craftingRedstoneTorch, 'D', OD.itemRedstone  , 'S', plate.dat(ANY.Stone));
		CR.shaped(ST.make(Items.comparator              , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , " T ", "TQT", "SSS", 'T', OD.craftingRedstoneTorch, 'Q', OD.craftingQuartz, 'S', stoneSmooth         );
		CR.shaped(ST.make(Items.comparator              , 1, 0), DEF                            , " T ", "TQT", "SSS", 'T', OD.craftingRedstoneTorch, 'Q', OD.craftingQuartz, 'S', plate.dat(ANY.Stone));
		CR.shaped(ST.make(Blocks.daylight_detector      , 1, 0), DEF                            , "GGG", "QQQ", "PPP", 'P', OD.slabWood             , 'Q', OD.craftingQuartz, 'G', plate.dat(MT.Glass ));
		CR.shaped(ST.make(Blocks.piston                 , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , "WWW", "CBC", "CRC", 'W', OD.plankAnyWood, 'C', stoneCobble, 'B', OD.craftingPistonIngot, 'R', OD.itemRedstone);
		CR.shaped(ST.make(Blocks.sticky_piston          , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES ,  "G" ,  "P"        , 'G', OD.craftingPistonGlue, 'P', Blocks.piston);
		CR.shaped(ST.make(Items.bow                     , 1, 0), DEF_MIR                        , " WS", "WkS", " WS", 'S', OD.itemString, 'W', OD.stickAnyWood);
		CR.shaped(ST.make(Blocks.dropper                , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , "CCC", "C C", "CRC", 'C', stoneCobble, 'R', OD.itemRedstone);
		CR.shaped(ST.make(Blocks.dispenser              , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , "CCC", "CBC", "CRC", 'C', stoneCobble, 'R', OD.itemRedstone, 'B', ST.make(Items.bow, 1, 0));
		CR.shaped(ST.make(Blocks.dispenser              , 1, 0), DEF_MIR                        , " WS", "WDS", " WS", 'S', OD.itemString, 'W', OD.stickAnyWood, 'D', Blocks.dropper);
		CR.shaped(ST.make(Blocks.dispenser              , 1, 0), DEF                            ,  "B" ,  "D"        , 'B', ST.make(Items.bow, 1, 0), 'D', Blocks.dropper);
		CR.shaped(ST.make(Blocks.noteblock              , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , "WWW", "WRW", "WWW", 'W', OD.plankAnyWood, 'R', OD.itemRedstone);
		CR.shaped(ST.make(Blocks.redstone_lamp          , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , " R ", "RGR", " R ", 'G', Blocks.glowstone, 'R', OD.itemRedstone);
		CR.shaped(ST.make(Items.name_tag                , 1, 0), DEF_MIR                        , " S" ,  "Pq"       , 'S', OD.itemString, 'P', OD.paperEmpty);
		
		CR.shaped(ST.make(Blocks.tnt                    , 1, 0), DEF                            , "GSG", "SGS", "GSG", 'G', dust.dat(MT.Gunpowder), 'S', dust.dat(ANY.SiO2));
		CR.shaped(ST.make(Blocks.tnt                    , 9, 0), DEF                            , "GSG", "SGS", "GSG", 'G', blockDust.dat(MT.Gunpowder), 'S', blockDust.dat(ANY.SiO2));
		
		CR.shaped(ST.make(Items.minecart                , 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES , " h ", "PwP", "WPW", 'P', plate.dat(ANY.Iron), 'W', minecartWheels.dat(ANY.Iron));
		
		CR.shaped(ST.make(Items.chest_minecart          , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "X", "C", 'C', Items.minecart, 'X', Blocks.chest);
		CR.shaped(ST.make(Items.furnace_minecart        , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "X", "C", 'C', Items.minecart, 'X', OD.craftingFurnace);
		CR.shaped(ST.make(Items.hopper_minecart         , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "X", "C", 'C', Items.minecart, 'X', Blocks.hopper);
		CR.shaped(ST.make(Items.tnt_minecart            , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "X", "C", 'C', Items.minecart, 'X', Blocks.tnt);
		
		RM.boxunbox(ST.make(Items.minecart, 1, 0), ST.make(Items.chest_minecart  , 1, 0), ST.make(Blocks.chest  , 1, 0));
		RM.boxunbox(ST.make(Items.minecart, 1, 0), ST.make(Items.furnace_minecart, 1, 0), ST.make(Blocks.furnace, 1, 0));
		RM.boxunbox(ST.make(Items.minecart, 1, 0), ST.make(Items.hopper_minecart , 1, 0), ST.make(Blocks.hopper , 1, 0));
		RM.boxunbox(ST.make(Items.minecart, 1, 0), ST.make(Items.tnt_minecart    , 1, 0), ST.make(Blocks.tnt    , 1, 0));
		
		
		RM.boxunbox(stick.mat(MT.Wood, 1), ST.make(Items.wooden_sword     , 1, 0), toolHeadSword  .mat(MT.Wood   , 1));
		RM.boxunbox(stick.mat(MT.Wood, 1), ST.make(Items.stone_sword      , 1, 0), toolHeadSword  .mat(MT.Stone  , 1));
		RM.boxunbox(stick.mat(MT.Wood, 1), ST.make(Items.iron_sword       , 1, 0), toolHeadSword  .mat(MT.Fe     , 1));
		RM.boxunbox(stick.mat(MT.Wood, 1), ST.make(Items.golden_sword     , 1, 0), toolHeadSword  .mat(MT.Au     , 1));
		RM.boxunbox(stick.mat(MT.Wood, 1), ST.make(Items.diamond_sword    , 1, 0), toolHeadSword  .mat(MT.Diamond, 1));
		RM.boxunbox(stick.mat(MT.Wood, 1), IL.Tool_Sword_Bronze  .getUndamaged(1), toolHeadSword  .mat(MT.Bronze , 1));
		RM.boxunbox(stick.mat(MT.Wood, 1), IL.Tool_Sword_Steel   .getUndamaged(1), toolHeadSword  .mat(MT.Steel  , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.wooden_pickaxe   , 1, 0), toolHeadPickaxe.mat(MT.Wood   , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.stone_pickaxe    , 1, 0), toolHeadPickaxe.mat(MT.Stone  , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.iron_pickaxe     , 1, 0), toolHeadPickaxe.mat(MT.Fe     , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.golden_pickaxe   , 1, 0), toolHeadPickaxe.mat(MT.Au     , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.diamond_pickaxe  , 1, 0), toolHeadPickaxe.mat(MT.Diamond, 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), IL.Tool_Pickaxe_Bronze.getUndamaged(1), toolHeadPickaxe.mat(MT.Bronze , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), IL.Tool_Pickaxe_Steel .getUndamaged(1), toolHeadPickaxe.mat(MT.Steel  , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.wooden_shovel    , 1, 0), toolHeadShovel .mat(MT.Wood   , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.stone_shovel     , 1, 0), toolHeadShovel .mat(MT.Stone  , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.iron_shovel      , 1, 0), toolHeadShovel .mat(MT.Fe     , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.golden_shovel    , 1, 0), toolHeadShovel .mat(MT.Au     , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.diamond_shovel   , 1, 0), toolHeadShovel .mat(MT.Diamond, 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), IL.Tool_Shovel_Bronze .getUndamaged(1), toolHeadShovel .mat(MT.Bronze , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), IL.Tool_Shovel_Steel  .getUndamaged(1), toolHeadShovel .mat(MT.Steel  , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.wooden_axe       , 1, 0), toolHeadAxe    .mat(MT.Wood   , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.stone_axe        , 1, 0), toolHeadAxe    .mat(MT.Stone  , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.iron_axe         , 1, 0), toolHeadAxe    .mat(MT.Fe     , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.golden_axe       , 1, 0), toolHeadAxe    .mat(MT.Au     , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.diamond_axe      , 1, 0), toolHeadAxe    .mat(MT.Diamond, 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), IL.Tool_Axe_Bronze    .getUndamaged(1), toolHeadAxe    .mat(MT.Bronze , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), IL.Tool_Axe_Steel     .getUndamaged(1), toolHeadAxe    .mat(MT.Steel  , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.wooden_hoe       , 1, 0), toolHeadHoe    .mat(MT.Wood   , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.stone_hoe        , 1, 0), toolHeadHoe    .mat(MT.Stone  , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.iron_hoe         , 1, 0), toolHeadHoe    .mat(MT.Fe     , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.golden_hoe       , 1, 0), toolHeadHoe    .mat(MT.Au     , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), ST.make(Items.diamond_hoe      , 1, 0), toolHeadHoe    .mat(MT.Diamond, 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), IL.Tool_Hoe_Bronze    .getUndamaged(1), toolHeadHoe    .mat(MT.Bronze , 1));
		RM.boxunbox(stick.mat(MT.Wood, 2), IL.Tool_Hoe_Steel     .getUndamaged(1), toolHeadHoe    .mat(MT.Steel  , 1));
		
		
		CR.shaped(ST.make(Items.chainmail_helmet        , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "RRR", "RhR"       , 'R', ring.dat(ANY.Steel));
		CR.shaped(ST.make(Items.chainmail_chestplate    , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "RhR", "RRR", "RRR", 'R', ring.dat(ANY.Steel));
		CR.shaped(ST.make(Items.chainmail_leggings      , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "RRR", "RhR", "R R", 'R', ring.dat(ANY.Steel));
		CR.shaped(ST.make(Items.chainmail_boots         , 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "R R", "RhR"       , 'R', ring.dat(ANY.Steel));
		
		for (int i = 1; i < 16; i++)
		CR.shapeless(ST.make(Blocks.wool, 1, i), DEF, new Object[] {ST.make(Blocks.wool, 1, 0), DYE_OREDICTS[15-i]});
		
		CR.delate(ST.make(Items.arrow, 1, 0));
		CR.delate(ST.make(Items.cookie, 1, 0));
		CR.delate(ST.make(Items.golden_apple, 1, 0));
		CR.delate(ST.make(Items.golden_apple, 1, 1));
		CR.delate(ST.make(Items.golden_carrot, 1, 0));
		
		CR.shapeless(ST.make(Items.arrow, 1, 0), DEF_NCC, new Object[] {OD.itemFlint, arrowGtWood.dat(MT.Empty)});
		CR.shapeless(arrowGtWood .mat(MT.Empty, 1), DEF_NCC, new Object[] {Items.arrow});
		
		CR.shaped(arrowGtWood    .mat(MT.Empty, 1), CR.DEF_NCC_MIR, " S", "F ", 'S', stick.dat(ANY.Wood), 'F', OD.craftingFeather);
		CR.shaped(arrowGtPlastic .mat(MT.Empty, 1), CR.DEF_NCC_MIR, " S", "F ", 'S', stick.dat(MT.Plastic), 'F', OD.craftingFeather);
		CR.shaped(arrowGtWood    .mat(MT.Empty, 1), CR.DEF_NCC_MIR, "PS", "sP", 'S', stick.dat(ANY.Wood), 'P', plateTiny.dat(MT.Plastic));
		CR.shaped(arrowGtPlastic .mat(MT.Empty, 1), CR.DEF_NCC_MIR, "PS", "sP", 'S', stick.dat(MT.Plastic), 'P', plateTiny.dat(MT.Plastic));
		
		CR.shaped(ST.make(Blocks.stained_glass, 8, 0), DEF, "GGG", "GDG", "GGG", 'G', Blocks.glass, 'D', DYE_OREDICTS[15]);
		CR.shaped(ST.make(Items.speckled_melon, 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES, "GGG", "GMG", "GGG", 'M', "cropMelon", 'G', nugget.dat(MT.Au));
		
		CR.remove(ST.make(Items.dye, 1, 1), ST.make(Items.dye, 1, 4));
		CR.remove(ST.make(Items.dye, 1, 2), ST.make(Items.dye, 1, 4));
		CR.remove(ST.make(Items.dye, 1, 5), ST.make(Items.dye, 1, 9));
		CR.remove(ST.make(Items.dye, 1, 1), ST.make(Items.dye, 1,11));
		CR.remove(ST.make(Items.dye, 1, 1), ST.make(Items.dye, 1,15));
		CR.remove(ST.make(Items.dye, 1, 2), ST.make(Items.dye, 1,15));
		CR.remove(ST.make(Items.dye, 1, 4), ST.make(Items.dye, 1,15));
		CR.remove(ST.make(Items.dye, 1, 8), ST.make(Items.dye, 1,15));
		CR.remove(ST.make(Items.dye, 1, 0), ST.make(Items.dye, 1,15));
		CR.remove(ST.make(Items.dye, 1, 0), ST.make(Items.dye, 1, 0), ST.make(Items.dye, 1,15));
		CR.remove(ST.make(Items.dye, 1, 1), ST.make(Items.dye, 1, 4), ST.make(Items.dye, 1, 9));
		CR.remove(ST.make(Items.dye, 1, 1), ST.make(Items.dye, 1, 1), ST.make(Items.dye, 1, 4), ST.make(Items.dye, 1,15));
		
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Purple     ), DEF, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Red    ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue  ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Cyan       ), DEF, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Green  ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue  ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Pink       ), DEF, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Red    ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Lime       ), DEF, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Green  ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_LightBlue  ), DEF, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue   ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Magenta    ), DEF, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Purple ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Pink  ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Orange     ), DEF, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Red    ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Yellow]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_Gray       ), DEF, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Black  ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White ]});
		CR.shapeless(ST.make(Items.dye, 2, DYE_INDEX_LightGray  ), DEF, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Gray   ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White ]});
		CR.shapeless(ST.make(Items.dye, 3, DYE_INDEX_LightGray  ), DEF, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Black  ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White ], DYE_OREDICTS_MIXABLE[DYE_INDEX_White]});
		CR.shapeless(ST.make(Items.dye, 3, DYE_INDEX_Magenta    ), DEF, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue   ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Red   ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Pink]});
		CR.shapeless(ST.make(Items.dye, 4, DYE_INDEX_Magenta    ), DEF, new Object[] {DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue   ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Red   ], DYE_OREDICTS_MIXABLE[DYE_INDEX_Red], DYE_OREDICTS_MIXABLE[DYE_INDEX_White]});
		
		CR.shaped(toolHeadArrow.mat(MT.Flint, 6), DEF, "fX", 'X', OD.itemFlint);
		CR.shaped(toolHeadArrow.mat(MT.Flint, 4), DEF, "RX", 'X', OD.itemFlint, 'R', OD.itemRock);
		RM.Sharpening   .addRecipe1(T, 16,  64, ST.make(Items.flint, 1, W), toolHeadArrow.mat(MT.Flint, 8), dustTiny.mat(MT.Flint, 1));
		RM.Sharpening   .addRecipe1(T, 16,  64, ST.make(Blocks.glass_pane, 1, W), lens.mat(MT.Glass, 1));
		
		RM.Lathe        .addRecipe1(T, 16,  16, ST.make(Blocks.glass_pane, 1, W), lens.mat(MT.Glass, 1), dustSmall.mat(MT.Glass, 1));
		RM.Lathe        .addRecipe1(T, 16,  16, ST.make(Blocks.stone, 1, W), stickLong.mat(MT.Stone, 1));
		RM.Lathe        .addRecipe1(T, 16,  32, IL.Module_Stone_Generator     .get(0), stickLong.mat(MT.Stone, 1));
		RM.Lathe        .addRecipe1(T, 16,  32, IL.Module_Basalt_Generator    .get(0), stickLong.mat(MT.STONES.Basalt, 1));
		RM.Lathe        .addRecipe1(T, 16,  32, IL.Module_Blackstone_Generator.get(0), stickLong.mat(MT.STONES.Blackstone, 1));
		
		RM.smash(ST.make(Blocks.brown_mushroom_block , 1, W), ST.make(Blocks.brown_mushroom, 1, 0));
		RM.smash(ST.make(Blocks.red_mushroom_block   , 1, W), ST.make(Blocks.red_mushroom, 1, 0));
		RM.smash(ST.make(Blocks.quartz_block         , 1, W), gem.mat(MT.NetherQuartz, 4));
		RM.smash(ST.make(Blocks.double_stone_slab    , 1, 7), gem.mat(MT.NetherQuartz, 4));
		RM.smash(ST.make(Blocks.quartz_stairs        , 1, W), gem.mat(MT.NetherQuartz, 6));
		RM.smash(ST.make(Blocks.stone_slab           , 1, 7), gem.mat(MT.NetherQuartz, 2));
		RM.smash(ST.make(Blocks.stonebrick           , 1, 0), ST.make(Blocks.stonebrick, 1, 2));
		RM.smash(ST.make(Blocks.stonebrick           , 1, 1), ST.make(Blocks.cobblestone, 1, 0));
		RM.smash(ST.make(Blocks.stonebrick           , 1, 2), ST.make(Blocks.cobblestone, 1, 0));
		RM.smash(ST.make(Blocks.stonebrick           , 1, 3), ST.make(Blocks.cobblestone, 1, 0));
		RM.smash(ST.make(Blocks.stone                , 1, 0), ST.make(Blocks.cobblestone, 1, 0));
		RM.smash(IL.Module_Stone_Generator.get(           0), ST.make(Blocks.cobblestone, 1, 0));
		RM.smash(IL.Module_Basalt_Generator.get(          0), IL.NeLi_Basalt.get(1, IL.NePl_Basalt.get(1, ST.make(BlocksGT.Basalt, 1, BlockStones.COBBL))));
		RM.smash(IL.Module_Blackstone_Generator.get(      0), IL.NeLi_Blackstone.get(1, IL.NePl_Blackstone.get(1, ST.make(BlocksGT.Basalt, 1, BlockStones.COBBL))));
		RM.smash(ST.make(Blocks.cobblestone          , 1, 0), ST.make(Blocks.gravel, 1, 0));
		RM.smash(ST.make(Blocks.sandstone            , 1, W), ST.make(Blocks.sand, 1, 0));
		RM.smash(ST.make(Blocks.ice                  , 1, W), OM.dust(MT.Ice));
		RM.smash(ST.make(Blocks.packed_ice           , 1, W), OM.dust(MT.Ice, 2*U));
		RM.smash(ST.make(Blocks.hardened_clay        , 1, W), OM.dust(MT.Clay, 2*U));
		RM.smash(ST.make(Blocks.stained_hardened_clay, 1, W), OM.dust(MT.Clay, 2*U));
		RM.smash(ST.make(Blocks.glass                , 1, W), OM.dust(MT.Glass, U*9));
		RM.smash(ST.make(Blocks.stained_glass        , 1, W), OM.dust(MT.Glass, U*9));
		RM.smash(ST.make(Blocks.glass_pane           , 1, W), OM.dust(MT.Glass, U));
		RM.smash(ST.make(Blocks.stained_glass_pane   , 1, W), OM.dust(MT.Glass, U));
		
		RM.Hammer .addRecipe1(T, 16,  16, ST.make(Blocks.brick_block      , 1, W), ST.make(Items.brick, 3, 0));
		RM.Hammer .addRecipe1(T, 16,  16, ST.make(Blocks.double_stone_slab, 1, 4), ST.make(Items.brick, 3, 0));
		RM.Hammer .addRecipe1(T, 16,  16, ST.make(Blocks.brick_stairs     , 1, W), ST.make(Items.brick, 5, 0));
		RM.Hammer .addRecipe1(T, 16,  16, ST.make(Blocks.stone_slab       , 1, 4), ST.make(Items.brick, 1, 0));
		RM.Crusher.addRecipe1(T, 16,  64, new long[] {10000, 9000, 8000, 7000}, ST.make(Blocks.brick_block      , 1, W), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0));
		RM.Crusher.addRecipe1(T, 16,  64, new long[] {10000, 9000, 8000, 7000}, ST.make(Blocks.double_stone_slab, 1, 4), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0));
		RM.Crusher.addRecipe1(T, 16,  64, new long[] {10000, 9000, 8000, 7000}, ST.make(Blocks.brick_stairs     , 1, W), ST.make(Items.brick, 2, 0), ST.make(Items.brick, 2, 0), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0));
		RM.Crusher.addRecipe1(T, 16,  32, new long[] {10000, 8000            }, ST.make(Blocks.stone_slab       , 1, 4), ST.make(Items.brick, 1, 0), ST.make(Items.brick, 1, 0));
		
		RM.glowstone(ST.make(Blocks.glowstone, 1, 0), MT.Glowstone);
		
		for (OreDictMaterial tMaterial : ANY.Wood.mToThis) {
		RM.sawing(16,  16, F,   3, stick.mat(tMaterial, 1), bolt.mat(tMaterial, 4));
		RM.sawing(16,  16, F,   1, stickLong.mat(tMaterial, 1), stick.mat(tMaterial, 2));
		}
		for (byte i = 0; i < 16; i++) {
		RM.sawing(16,  32, F,  50, ST.make(Blocks.stained_glass             , 3, i), ST.make(Blocks.stained_glass_pane, 8, i));
		RM.sawing(16,  32, F,  50, ST.make(Blocks.wool                      , 2, i), ST.make(Blocks.carpet, 3, i));
		}
		RM.sawing(16,  32, F,  50, ST.make(Blocks.glass                     , 1, 0), ST.make(Blocks.glass_pane, 9, 0));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.double_stone_slab         , 1, 0), plate.mat(MT.Stone, 8), dust.mat(MT.Stone, 1));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.double_stone_slab         , 1, 8), plate.mat(MT.Stone, 8), dust.mat(MT.Stone, 1));
		RM.sawing(16,  16, F,  50, ST.make(Blocks.stone_slab                , 1, 0), plate.mat(MT.Stone, 4), dustSmall.mat(MT.Stone, 2));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.stone                     , 1, 0), ST.make(Blocks.stone_slab, 2, 0));
		RM.sawing(16,  16, F, 100, IL.Module_Stone_Generator.get(                0), ST.make(Blocks.stone_slab, 1, 0));
		RM.sawing(16,  16, F, 100, IL.Module_Basalt_Generator.get(               0), ST.make(Blocks.stone_slab, 1, 0)); // TODO Basalt Slabs
		RM.sawing(16,  16, F, 100, IL.Module_Blackstone_Generator.get(           0), ST.make(Blocks.stone_slab, 1, 0)); // TODO Blackstone Slabs
		RM.sawing(16,  16, F, 100, ST.make(Blocks.sandstone                 , 1, 0), ST.make(Blocks.stone_slab, 2, 1));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.cobblestone               , 1, 0), ST.make(Blocks.stone_slab, 2, 3));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.brick_block               , 1, 0), ST.make(Blocks.stone_slab, 2, 4));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.stonebrick                , 1, 0), ST.make(Blocks.stone_slab, 2, 5));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.nether_brick              , 1, 0), ST.make(Blocks.stone_slab, 2, 6));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.quartz_block              , 1, W), ST.make(Blocks.stone_slab, 2, 7));
		RM.sawing(16,  16, F, 100, ST.make(Blocks.wooden_button             , 1, W), IL.Plank.get(1));
		RM.sawing(16,  32, F, 100, ST.make(Blocks.wooden_pressure_plate     , 1, W), IL.Plank.get(2));
		RM.sawing(16,  32, F, 100, ST.make(Items.sign                       , 1, W), IL.Plank.get(2), OM.dust(MT.Wood, stick.mAmount / 3));
		RM.sawing(16,  32, F, 100, ST.make(Items.wooden_door                , 1, W), IL.Plank.get(2));
		RM.sawing(16,  32, F, 100, ST.make(Blocks.fence_gate                , 1, W), IL.Plank.get(2), OM.dust(MT.Wood, stick.mAmount * 4));
		RM.sawing(16,  48, F, 100, ST.make(Blocks.trapdoor                  , 1, W), IL.Plank.get(3));
		RM.sawing(16,  48, F, 100, ST.make(Items.bed                        , 1, W), IL.Plank.get(3), ST.make(Blocks.wool, 3, 0));
		RM.sawing(16,  64, F, 100, ST.make(Blocks.crafting_table            , 1, W), IL.Plank.get(4));
		RM.sawing(16,  80, F, 100, ST.make(Items.boat                       , 1, W), IL.Plank.get(5));
		RM.sawing(16,  96, F, 100, ST.make(Blocks.bookshelf                 , 1, W), IL.Plank.get(6), ST.make(Items.book, 3, 0));
		RM.sawing(16, 128, F, 100, ST.make(Blocks.chest                     , 1, W), IL.Plank.get(8));
		RM.sawing(16, 128, F, 100, ST.make(Blocks.trapped_chest             , 1, W), IL.Plank.get(8), ST.make(Blocks.tripwire_hook, 1, 0));
		RM.sawing(16, 128, F, 100, ST.make(Blocks.noteblock                 , 1, W), IL.Plank.get(8), dust.mat(MT.Redstone, 1));
		RM.sawing(16, 128, F, 100, ST.make(Blocks.jukebox                   , 1, W), IL.Plank.get(8), gem.mat(MT.Diamond, 1));
		RM.sawing(16,  64, F, 100, ST.make(Items.painting                   , 1, W), IL.Stick.get(8));
		RM.sawing(16,  64, F, 100, ST.make(Items.item_frame                 , 1, W), IL.Stick.get(8));
		RM.sawing(16,  40, F, 100, ST.make(Blocks.ladder                    , 1, W), IL.Stick.get(2), OM.dust(MT.Wood, stick.mAmount / 3));
		RM.sawing(16,  16, T,  50, ST.make(Blocks.melon_block               , 1, W), ST.make(Items.melon, 8, 0), ST.make(Items.melon_seeds, 1, 0));
		
		CR.shapeless(IL.Plank.get(1), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Blocks.wooden_button        });
		CR.shapeless(IL.Plank.get(2), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Blocks.wooden_pressure_plate});
		CR.shapeless(IL.Plank.get(2), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Items.sign                  });
		CR.shapeless(IL.Plank.get(2), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Items.wooden_door           });
		CR.shapeless(IL.Plank.get(2), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Blocks.fence_gate           });
		CR.shapeless(IL.Plank.get(3), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Blocks.trapdoor             });
		CR.shapeless(IL.Plank.get(3), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Items.bed                   });
		CR.shapeless(IL.Plank.get(4), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Blocks.crafting_table       });
		CR.shapeless(IL.Plank.get(5), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Items.boat                  });
		CR.shapeless(IL.Plank.get(6), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Blocks.bookshelf            });
		CR.shapeless(IL.Plank.get(8), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Blocks.chest                });
		CR.shapeless(IL.Plank.get(8), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Blocks.trapped_chest        });
		CR.shapeless(IL.Plank.get(8), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Blocks.noteblock            });
		CR.shapeless(IL.Plank.get(8), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, Blocks.jukebox              });
		
		RM.Slicer       .addRecipe2(T, 16,   16, ST.make(Blocks.melon_block, 1, W), IL.Shape_Slicer_Eigths.get(0), ST.make(Items.melon, 8, 0), ST.make(Items.melon_seeds, 1, 0));
		
		RM.Compressor   .addRecipe1(T, 64,   32, ST.make(Blocks.ice, 2, W), ST.make(Blocks.packed_ice, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.Ice), ST.make(Blocks.ice, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, OM.dust(MT.Ice, U4), gemChipped.mat(MT.Ice, 1));
		RM.Compressor   .addRecipe1(T, 16,   16, gemChipped  .mat(MT.Ice, 4), ST.make(Blocks.ice, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, gemFlawed   .mat(MT.Ice, 2), ST.make(Blocks.ice, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, gem         .mat(MT.Ice, 1), ST.make(Blocks.ice, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   32, ST.make(Blocks.snow, 1, W), ST.make(Blocks.ice, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   32, ST.make(Items.snowball, 4, W), ST.make(Blocks.snow, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, ST.make(Items.quartz, 4, 0), ST.make(Blocks.quartz_block, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   16, IL.Crop_Wheat.get(9), IL.Bale_Wheat.get(1));
		RM.Compressor   .addRecipe1(T, 16,   32, ST.make(Blocks.sand, 4, 0), ST.make(Blocks.sandstone, 1, 0));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.Lapis), plateGem.mat(MT.Lapis, 1));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.Asbestos), plate.mat(MT.Asbestos, 1));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.Lazurite), plateGem.mat(MT.Lazurite, 1));
		RM.Compressor   .addRecipe1(T, 16,   32, OM.dust(MT.Sodalite), plateGem.mat(MT.Sodalite, 1));
		
		
		RM.Freezer      .addRecipe1(T, 16,   16, ST.tag(0), FL.Water.make( 250), FL.Ice.make(250), ZL_IS);
		RM.Freezer      .addRecipe1(T, 16,   64, ST.tag(0), FL.SpDew.make( 250), FL.Ice.make(250), ZL_IS);
		RM.Freezer      .addRecipe1(T, 16,   16, ST.tag(0), FL.DistW.make( 250), FL.Ice.make(250), ZL_IS);
		RM.Freezer      .addRecipe1(T, 16,   16, ST.tag(1), FL.Water.make( 250), NF, ST.make(Items.snowball, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,   64, ST.tag(1), FL.SpDew.make( 250), NF, ST.make(Items.snowball, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,   16, ST.tag(1), FL.DistW.make( 250), NF, ST.make(Items.snowball, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,   32, ST.tag(2), FL.Water.make( 500), NF, ST.make(Blocks.snow_layer, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(2), FL.SpDew.make( 500), NF, ST.make(Blocks.snow_layer, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,   32, ST.tag(2), FL.DistW.make( 500), NF, ST.make(Blocks.snow_layer, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,   64, ST.tag(3), FL.Water.make(1000), NF, ST.make(Blocks.snow, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,  256, ST.tag(3), FL.SpDew.make(1000), NF, ST.make(Blocks.snow, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,   64, ST.tag(3), FL.DistW.make(1000), NF, ST.make(Blocks.snow, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(4), FL.Water.make(1000), NF, ST.make(Blocks.ice, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,  512, ST.tag(4), FL.SpDew.make(1000), NF, ST.make(Blocks.ice, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(4), FL.DistW.make(1000), NF, ST.make(Blocks.ice, 1, 0));
		RM.Freezer      .addRecipe1(T, 16,   32, ST.tag(5), FL.Water.make( 250), NF, gemChipped.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(5), FL.SpDew.make( 250), NF, gemChipped.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,   32, ST.tag(5), FL.DistW.make( 250), NF, gemChipped.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,   64, ST.tag(6), FL.Water.make( 500), NF, gemFlawed.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  256, ST.tag(6), FL.SpDew.make( 500), NF, gemFlawed.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,   64, ST.tag(6), FL.DistW.make( 500), NF, gemFlawed.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(7), FL.Water.make(1000), NF, gem.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  512, ST.tag(7), FL.SpDew.make(1000), NF, gem.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(7), FL.DistW.make(1000), NF, gem.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(8), FL.Water.make(1000), NF, dust.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  512, ST.tag(8), FL.SpDew.make(1000), NF, dust.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(8), FL.DistW.make(1000), NF, dust.mat(MT.Ice, 1));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(9), FL.Water.make(1000), NF, dust.mat(MT.Snow, 1));
		RM.Freezer      .addRecipe1(T, 16,  512, ST.tag(9), FL.SpDew.make(1000), NF, dust.mat(MT.Snow, 1));
		RM.Freezer      .addRecipe1(T, 16,  128, ST.tag(9), FL.DistW.make(1000), NF, dust.mat(MT.Snow, 1));
		
		
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(Blocks.glass             , 1, W), OM.dust(MT.Glass, U*9));
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(Blocks.stained_glass     , 1, W), OM.dust(MT.Glass, U*9));
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(Blocks.glass_pane        , 1, W), OM.dust(MT.Glass, U));
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(Blocks.stained_glass_pane, 1, W), OM.dust(MT.Glass, U));
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(Items.bone, 1, W), IL.Dye_Bonemeal.get(2));
		RM.Mortar       .addRecipe1(T, 16, 16, ST.make(Items.flint, 1, W), dust.mat(MT.Flint, 1));
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(Blocks.gravel, 1, W), ST.make(Items.flint, 1, 0));
		RM.Mortar       .addRecipe1(T, 16, 16, ST.make(Items.coal, 1, 0), OM.dust(MT.Coal));
		RM.Mortar       .addRecipe1(T, 16, 16, ST.make(Items.coal, 1, 1), OM.dust(MT.Charcoal));
		RM.Mortar       .addRecipe1(T, 16, 16, ST.make(Items.rotten_flesh, 1, W), dust.mat(MT.MeatRotten, 1));
		
		
		RM.Shredder     .addRecipe1(T, 16, 16, ST.make(Items.flint, 1, W), dust.mat(MT.Flint, 1));
		RM.Shredder     .addRecipe1(T, 16, 16, ST.make(Blocks.gravel, 1, W), ST.make(Blocks.sand, 1, 0));
		RM.Shredder     .addRecipe1(T, 16, 16, ST.make(Blocks.web, 1, W), ST.make(Items.string, 1, 0));
		RM.Shredder     .addRecipe1(T, 16, 16, ST.make(Items.reeds, 1, W), IL.Remains_Plant.get(1));
		RM.Shredder     .addRecipe1(T, 16, 16, ST.make(Blocks.cobblestone, 1, W), OM.dust(MT.Stone, U*9));
		RM.Shredder     .addRecipe1(T, 16, 16, ST.make(Blocks.stone, 1, W), OM.dust(MT.Stone, U*9));
		RM.Shredder     .addRecipe1(T, 16, 16, IL.Module_Stone_Generator.get(0), OM.dust(MT.Stone));
		RM.Shredder     .addRecipe1(T, 16, 16, IL.Module_Basalt_Generator.get(0), OM.dust(MT.STONES.Basalt));
		RM.Shredder     .addRecipe1(T, 16, 16, IL.Module_Blackstone_Generator.get(0), OM.dust(MT.STONES.Blackstone));
		RM.Shredder     .addRecipe1(T, 16, 32, ST.make(Items.bone, 1, W), IL.Dye_Bonemeal.get(4));
		RM.Shredder     .addRecipe1(T, 16,128,  6000, ST.make(Blocks.melon_block, 1, W), IL.Remains_Fruit.get(9));
		
		
		for (OreDictMaterial tBlaze : ANY.Blaze.mToThis) {
		CR.shapeless(dustTiny.mat(tBlaze, 2), CR.DEF, new Object[] {stick.dat(tBlaze)});
		CR.shapeless(dustTiny.mat(tBlaze, 4), CR.DEF, new Object[] {stickLong.dat(tBlaze)});
		RM.Mortar    .addRecipe1(T, 16, 32, stick    .mat(tBlaze, 1), dustTiny .mat(tBlaze, 3));
		RM.Mortar    .addRecipe1(T, 16, 64, stickLong.mat(tBlaze, 1), dustTiny .mat(tBlaze, 6));
		RM.Shredder  .addRecipe1(T, 16, 32, stick    .mat(tBlaze, 1), dustSmall.mat(tBlaze, 2));
		RM.Shredder  .addRecipe1(T, 16, 64, stickLong.mat(tBlaze, 1), dust     .mat(tBlaze, 1));
		RM.Compressor.addRecipe1(T, 16, 32, dust     .mat(tBlaze, 1), plate    .mat(tBlaze, 1));
		RM.Compressor.addRecipe1(T, 16, 32, dustSmall.mat(tBlaze, 4), plate    .mat(tBlaze, 1));
		RM.Compressor.addRecipe1(T, 16, 32, dustTiny .mat(tBlaze, 9), plate    .mat(tBlaze, 1));
		RM.ic2_compressor(                  dust     .mat(tBlaze, 1), plate    .mat(tBlaze, 1));
		RM.ic2_compressor(                  dustSmall.mat(tBlaze, 4), plate    .mat(tBlaze, 1));
		RM.ic2_compressor(                  dustTiny .mat(tBlaze, 9), plate    .mat(tBlaze, 1));
		}
		
		
		for (byte i = 0; i < 16; i++) {
		RM.Shredder     .addRecipe1(T, 16,   16,  9000, ST.make(Blocks.wool, 1, i), i==0?ST.make(Items.string, 4, 0):plantGtFiber.mat(MT.DATA.Dye_Materials[15-i], 4));
		CR.shaped(ST.make(Blocks.wool, 1, i), DEF, "XX", "XX", 'X', plantGtFiber.dat(MT.DATA.Dye_Materials[15-i]));
		
		if (i != DYE_INDEX_White) {
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.wool  , 1, 15-i), MT.Cl.fluid(U20, T), NF, ST.make(Blocks.wool  , 1, 0));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.carpet, 1, 15-i), MT.Cl.fluid(U40, T), NF, ST.make(Blocks.carpet, 1, 0));
		}
		
		for (FluidStack tDye : DYE_FLUIDS[i]) {
		if (i != DYE_INDEX_White) {
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.wool         , 1, 0), FL.mul(tDye, 1, 16, T), NF, ST.make(Blocks.wool                 , 1, 15-i));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.carpet       , 1, 0), FL.mul(tDye, 1, 32, T), NF, ST.make(Blocks.carpet               , 1, 15-i));
		}
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.hardened_clay, 1, 0), FL.mul(tDye, 1, 16, T), NF, ST.make(Blocks.stained_hardened_clay, 1, 15-i));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.glass        , 1, 0), FL.mul(tDye, 1, 16, T), NF, ST.make(Blocks.stained_glass        , 1, 15-i));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.glass_pane   , 1, 0), FL.mul(tDye, 3,128, T), NF, ST.make(Blocks.stained_glass_pane   , 1, 15-i));
		}
		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(0), plantGtFiber.mat(MT.DATA.Dye_Materials[15-i], 4), ST.make(Blocks.wool, 1, i));
		}
		
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.stained_hardened_clay, 1, W), MT.Cl.fluid(U20, T), NF, ST.make(Blocks.hardened_clay, 1, 0));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.stained_glass        , 1, W), MT.Cl.fluid(U20, T), NF, ST.make(Blocks.glass        , 1, 0));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Blocks.stained_glass_pane   , 1, W), MT.Cl.fluid(U50, T), NF, ST.make(Blocks.glass_pane   , 1, 0));
		
		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(0), plantGtFiber.mat(MT.Cu, 4), ST.make(Blocks.wool, 1, 1, "ORANGE WOOOOOOL!!!"));
		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(0), ST.make(Items.string, 4, W), ST.make(Blocks.wool, 1, 0));
		RM.Loom         .addRecipe2(T, 16,   64, ST.tag(1), ST.make(Items.string, 4, W), ST.make(Blocks.web, 1, 0));
		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(0), ST.make(Items.reeds, 1, W), ST.make(Items.paper, 1, 0));
		for (FluidStack tWater : FL.waters(125, 100))
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Items.reeds, 1, W), tWater, NF, ST.make(Items.paper, 1, 0));
		
		for (OreDictMaterial tMat2 : ANY.Iron.mToThis)
		RM.Loom         .addRecipe2(T, 64,  128, ST.make(Items.leather, 6, W), (tMat2==MT.Enori?plateGem:plate).mat(tMat2     , 8), ST.make(Items.iron_horse_armor, 1, 0));
		RM.Loom         .addRecipe2(T, 64,  128, ST.make(Items.leather, 6, W), plate                           .mat(MT.Au     , 8), ST.make(Items.golden_horse_armor, 1, 0));
		RM.Loom         .addRecipe2(T, 64,  128, ST.make(Items.leather, 6, W), plateGem                        .mat(MT.Diamond, 8), ST.make(Items.diamond_horse_armor, 1, 0));
		for (OreDictMaterial tMat2 : ANY.Steel.mToThis)
		RM.Loom         .addRecipeX(T, 64,  128, ST.array(ST.make(Items.leather, 6, W), ring.mat(tMat2, 2), stick.mat(tMat2, 3)), ST.make(Items.saddle, 1, 0));
		
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(4), ST.make(Items.leather, 5, W), ST.make(Items.leather_helmet, 1, 0));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(5), ST.make(Items.leather, 8, W), ST.make(Items.leather_chestplate, 1, 0));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(6), ST.make(Items.leather, 7, W), ST.make(Items.leather_leggings, 1, 0));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(7), ST.make(Items.leather, 4, W), ST.make(Items.leather_boots, 1, 0));
		
		for (OreDictMaterial tMat2 : ANY.Steel.mToThis) {
		RM.Loom         .addRecipe2(T, 96,  128, ST.tag(4), ring.mat(tMat2, 5), ST.make(Items.chainmail_helmet, 1, 0));
		RM.Loom         .addRecipe2(T, 96,  128, ST.tag(5), ring.mat(tMat2, 8), ST.make(Items.chainmail_chestplate, 1, 0));
		RM.Loom         .addRecipe2(T, 96,  128, ST.tag(6), ring.mat(tMat2, 7), ST.make(Items.chainmail_leggings, 1, 0));
		RM.Loom         .addRecipe2(T, 96,  128, ST.tag(7), ring.mat(tMat2, 4), ST.make(Items.chainmail_boots, 1, 0));
		}
		
		for (OreDictMaterial tMat2 : ANY.Fe.mToThis) if (tMat2 != MT.Enori)
		RM.RollBender   .addRecipe1(T, 16,  256, plateCurved.mat(tMat2, 3), ST.make(Items.bucket, 1, 0));
		
		RM.Chisel       .addRecipe1(T, 16,   16, ST.make(Blocks.stone, 1, W), ST.make(Blocks.stonebrick, 1, 3));
		RM.Chisel       .addRecipe1(T, 16,   16, ST.make(Blocks.stonebrick, 1, 0), ST.make(Blocks.stonebrick, 1, 2));
		
		
		for (OreDictMaterial tMat2 : ANY.Glowstone.mToThis) {
		RM.Press        .addRecipeX(T, 16,  144, ST.array(ST.tag(1), blockDust.mat(MT.Redstone ,  4), blockDust.mat(tMat2,  4)), ST.make(Blocks.redstone_lamp, 9, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dust     .mat(MT.Redstone ,  4), dust     .mat(tMat2,  4)), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dust     .mat(MT.Redstone , 16), dust     .mat(tMat2,  4)), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dust     .mat(MT.Redstone , 36), dust     .mat(tMat2,  4)), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dust     .mat(MT.Redstone ,  4), dustSmall.mat(tMat2, 16)), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dust     .mat(MT.Redstone , 16), dustSmall.mat(tMat2, 16)), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dust     .mat(MT.Redstone , 36), dustSmall.mat(tMat2, 16)), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dust     .mat(MT.Redstone ,  4), dustTiny .mat(tMat2, 36)), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dust     .mat(MT.Redstone , 16), dustTiny .mat(tMat2, 36)), ST.make(Blocks.redstone_lamp, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dust     .mat(MT.Redstone , 36), dustTiny .mat(tMat2, 36)), ST.make(Blocks.redstone_lamp, 1, 0));
		}
		for (OreDictMaterial tMat2 : ANY.SiO2.mToThis) {
		RM.Press        .addRecipeX(T, 16,  144, ST.array(ST.tag(1), blockDust.mat(MT.Gunpowder,  4), blockDust.mat(tMat2,  4)), ST.make(Blocks.tnt, 9, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dust     .mat(MT.Gunpowder,  4), dust     .mat(tMat2,  4)), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dustSmall.mat(MT.Gunpowder, 16), dust     .mat(tMat2,  4)), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dustTiny .mat(MT.Gunpowder, 36), dust     .mat(tMat2,  4)), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dust     .mat(MT.Gunpowder,  4), dustSmall.mat(tMat2, 16)), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dustSmall.mat(MT.Gunpowder, 16), dustSmall.mat(tMat2, 16)), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dustTiny .mat(MT.Gunpowder, 36), dustSmall.mat(tMat2, 16)), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dust     .mat(MT.Gunpowder,  4), dustTiny .mat(tMat2, 36)), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dustSmall.mat(MT.Gunpowder, 16), dustTiny .mat(tMat2, 36)), ST.make(Blocks.tnt, 1, 0));
		RM.Press        .addRecipeX(T, 16,   16, ST.array(ST.tag(1), dustTiny .mat(MT.Gunpowder, 36), dustTiny .mat(tMat2, 36)), ST.make(Blocks.tnt, 1, 0));
		}
		
		
		RM.Squeezer     .addRecipe1(T, 16,  128,  6000, ST.make(Blocks.melon_block      , 1, W), NF, FL.Juice_Melon.make(2250), IL.Remains_Fruit.get(9));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 0), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Red          ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 1), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue    ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 2), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta      ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 3), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray    ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 4), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Red          ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 5), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Orange       ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 6), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray    ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 7), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Pink         ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.red_flower       , 1, 8), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray    ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  2000, ST.make(Blocks.yellow_flower    , 1, 0), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow       ], 2), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  4000, ST.make(Blocks.double_plant     , 1, 1), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta      ], 3), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  4000, ST.make(Blocks.double_plant     , 1, 4), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Red          ], 3), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16,  4000, ST.make(Blocks.double_plant     , 1, 5), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Pink         ], 3), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   16, 10000, ST.make(Blocks.double_plant     , 1, 0), NF, FL.Oil_Sunflower.make(100), ST.make(Items.dye, 2, DYE_INDEX_Yellow));
		RM.Squeezer     .addRecipe1(T, 16,   16,  7000, ST.make(Blocks.cactus           , 1, W), NF, FL.Juice_Cactus.make(100), IL.Dye_Cactus.get(2));
		RM.Squeezer     .addRecipe1(T, 16,   16,  4000, ST.make(Items.reeds             , 1, W), NF, FL.Juice_Reed.make(100), IL.Remains_Plant.get(1));
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Ice, U9)                    , NF, FL.Ice.make( 111), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Ice, U4)                    , NF, FL.Ice.make( 250), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Ice)                        , NF, FL.Ice.make(1000), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, gemChipped.mat(MT.Ice           , 1   ), NF, FL.Ice.make( 250), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, gemFlawed.mat(MT.Ice            , 1   ), NF, FL.Ice.make( 500), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, gem.mat(MT.Ice                  , 1   ), NF, FL.Ice.make(1000), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, ST.make(Blocks.ice              , 1, W), NF, FL.Ice.make(1000), NI);
		RM.Squeezer     .addRecipe1(T, 16,  128, 10000, ST.make(Blocks.packed_ice       , 1, W), NF, FL.Ice.make(2000), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Snow, U9)                   , NF, FL.Ice.make( 111), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Snow, U4)                   , NF, FL.Ice.make( 250), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Snow)                       , NF, FL.Ice.make(1000), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, ST.make(Items.snowball          , 1, W), NF, FL.Ice.make( 250), NI);
		RM.Squeezer     .addRecipe1(T, 16,   64, 10000, ST.make(Blocks.snow             , 1, W), NF, FL.Ice.make(1000), NI);
		RM.Squeezer     .addRecipe1(T, 16,   16, 10000, ST.make(Blocks.red_mushroom     , 1, W), NF, FL.Potion_Poison_1.make(250), NI);
		RM.Squeezer     .addRecipe1(T, 16,   16, 10000, ST.make(Items.poisonous_potato  , 1, W), NF, FL.Potion_Poison_1.make(250), NI);
		RM.Squeezer     .addRecipe1(T, 16,   16, 10000, ST.make(Items.spider_eye        , 1, W), NF, FL.Potion_Poison_1.make(250), OM.dust(MT.MeatRaw, U2));
		RM.Squeezer     .addRecipe1(T, 16,   32, 10000, ST.make(Items.fish              , 1, 3), NF, FL.Potion_Poison_2.make(250), OM.dust(MT.FishRaw, U));
		RM.Squeezer     .addRecipe1(T, 16,  128, 10000, ST.make(Items.golden_apple      , 1, 0), NF, FL.make("potion.goldenapplejuice", 250), OM.dust(MT.Au, U*2));
		RM.Squeezer     .addRecipe1(T, 16,  128, 10000, ST.make(Items.golden_apple      , 1, 1), NF, FL.make("potion.idunsapplejuice", 250), OM.dust(MT.Au, U*18));
		RM.Squeezer     .addRecipe1(T, 16,  128, 10000, ST.make(Items.golden_carrot     , 1, 0), NF, FL.make("goldencarrotjuice", 250), OM.dust(MT.Au, 2*U9));
		RM.Squeezer     .addRecipe1(T, 16,   16, 10000, IL.Dye_SquidInk                 .get(1), NF, FL.make("squidink", 2*L), NI);
		
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 0), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Red          ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 1), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue    ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 2), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta      ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 3), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray    ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 4), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Red          ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 5), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Orange       ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 6), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray    ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 7), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Pink         ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.red_flower       , 1, 8), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray    ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  3000, ST.make(Blocks.yellow_flower    , 1, 0), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow       ], 1), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  6000, ST.make(Blocks.double_plant     , 1, 1), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta      ], 2), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  6000, ST.make(Blocks.double_plant     , 1, 4), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Red          ], 2), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  6000, ST.make(Blocks.double_plant     , 1, 5), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Pink         ], 2), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16, 10000, ST.make(Blocks.double_plant     , 1, 0), NF, FL.Oil_Sunflower.make(75), ST.make(Items.dye, 2, 11));
		RM.Juicer       .addRecipe1(T, 16,   16,  9000, ST.make(Blocks.cactus           , 1, W), NF, FL.Juice_Cactus.make(75), IL.Dye_Cactus.get(2));
		RM.Juicer       .addRecipe1(T, 16,   16,  5000, ST.make(Items.reeds             , 1, W), NF, FL.Juice_Reed.make(75), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Ice, U9)                    , NF, FL.Ice.make( 111), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Ice, U4)                    , NF, FL.Ice.make( 250), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Ice)                        , NF, FL.Ice.make(1000), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, gemChipped.mat(MT.Ice           , 1   ), NF, FL.Ice.make( 250), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, gemFlawed.mat(MT.Ice            , 1   ), NF, FL.Ice.make( 500), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, gem.mat(MT.Ice                  , 1   ), NF, FL.Ice.make(1000), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, ST.make(Blocks.ice              , 1, W), NF, FL.Ice.make(1000), NI);
		RM.Juicer       .addRecipe1(T, 16,  128, 10000, ST.make(Blocks.packed_ice       , 1, W), NF, FL.Ice.make(2000), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Snow, U9)                   , NF, FL.Ice.make( 111), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Snow, U4)                   , NF, FL.Ice.make( 250), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, OM.dust(MT.Snow)                       , NF, FL.Ice.make(1000), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, ST.make(Items.snowball          , 1, W), NF, FL.Ice.make( 250), NI);
		RM.Juicer       .addRecipe1(T, 16,   64, 10000, ST.make(Blocks.snow             , 1, W), NF, FL.Ice.make(1000), NI);
		RM.Juicer       .addRecipe1(T, 16,   16, 10000, ST.make(Blocks.red_mushroom     , 1, W), NF, FL.Potion_Poison_1.make(125), NI);
		RM.Juicer       .addRecipe1(T, 16,   16, 10000, ST.make(Items.poisonous_potato  , 1, W), NF, FL.Potion_Poison_1.make(125), NI);
		RM.Juicer       .addRecipe1(T, 16,   16, 10000, ST.make(Items.spider_eye        , 1, W), NF, FL.Potion_Poison_1.make(125), OM.dust(MT.MeatRaw, U2));
		RM.Juicer       .addRecipe1(T, 16,   32, 10000, ST.make(Items.fish              , 1, 3), NF, FL.Potion_Poison_2.make(125), OM.dust(MT.FishRaw, U));
		RM.Juicer       .addRecipe1(T, 16,   16, 10000, IL.Dye_SquidInk                 .get(1), NF, FL.make("squidink", 3*L/2), NI);
		
		
		RM.Bath         .addRecipe1(T,  0,  128, ST.make(Items.golden_apple , 1, 0), MT.Au.liquid(U*64, T), NF, ST.make(Items.golden_apple, 1, 1));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Items.carrot       , 1, W), MT.Au.liquid(8*U9, T), NF, ST.make(Items.golden_carrot, 1, 0));
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(Items.melon        , 1, W), MT.Au.liquid(8*U9, T), NF, ST.make(Items.speckled_melon, 1, 0));
		RM.Mixer        .addRecipe2(T, 16,   16, ST.make(Items.melon        , 1, W), nugget.mat(MT.Au, 8), ST.make(Items.speckled_melon, 1, 0));
		for (FluidStack tWater : FL.waters(50)) {
		RM.Mixer        .addRecipe0(T, 16,   16, FL.array(tWater, FL.Lava.make(1000)), NF, ST.make(Blocks.obsidian, 1, 0));
		if (FL.Lava_Volcanic.exists())
		RM.Mixer        .addRecipe0(T, 16,   16, FL.array(tWater, FL.Lava_Volcanic.make(1000)), NF, ST.make(BlocksGT.Basalt, 1, BlockStones.STONE));
		RM.Mixer        .addRecipe0(T, 16,   16, FL.array(tWater, FL.Lava_Pahoehoe.make(1000)), NF, ST.make(BlocksGT.Basalt, 1, BlockStones.STONE));
		}
		for (FluidStack tWater : FL.waters(3000))
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.STONES.Redrock), tWater, NF, IL.Clay_Ball_Red.get(4));
		RM.Mixer        .addRecipe2(T, 16,   16, OM.dust(MT.EnderPearl     ), OM.dust(MT.Blaze, U9), OM.dust(MT.EnderEye     ));
		RM.Mixer        .addRecipe2(T, 16,  144, OM.dust(MT.EnderPearl, U*9), OM.dust(MT.Blaze    ), OM.dust(MT.EnderEye, U*9));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Sugar              ), ST.make(Items.spider_eye, 1, W), ST.make(Blocks.brown_mushroom, 1, W)), ST.make(Items.fermented_spider_eye, 1, 0));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(gemChipped.mat(MT.Sugar    , 4), ST.make(Items.spider_eye, 1, W), ST.make(Blocks.brown_mushroom, 1, W)), ST.make(Items.fermented_spider_eye, 1, 0));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Coal               ), OM.dust(MT.Blaze, U9), OM.dust(MT.Gunpowder)), ST.make(Items.fire_charge, 3, 0));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Charcoal           ), OM.dust(MT.Blaze, U9), OM.dust(MT.Gunpowder)), ST.make(Items.fire_charge, 3, 0));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.CoalCoke           ), OM.dust(MT.Blaze, U9), OM.dust(MT.Gunpowder)), ST.make(Items.fire_charge, 3, 0));
		RM.Mixer        .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.LigniteCoke        ), OM.dust(MT.Blaze, U9), OM.dust(MT.Gunpowder)), ST.make(Items.fire_charge, 3, 0));
		
		
		RM.Electrolyzer .addRecipe2(T, 64,  576, ST.tag(0), ST.make(Blocks.sand, 1, 0), OM.dust(MT.SiO2, U*9));
		RM.Electrolyzer .addRecipe2(T, 64,   64, ST.tag(0), OM.dust(MT.Sand), OM.dust(MT.SiO2));
		
		RM.Centrifuge   .addRecipe1(T, 16,   16, OM.dust(MT.SlimyBone), NF, FL.Slime_Green.make(250), OM.dust(MT.Bone));
		RM.Centrifuge   .addRecipe1(T, 16,   16, ST.make(Items.magma_cream, 1, W), NF, FL.Slime_Green.make(125), ST.make(Items.blaze_powder, 1, 0));
		for (String tFluid : FluidsGT.SLIME) if (FL.exists(tFluid)) {
		RM.Centrifuge   .addRecipe0(T, 16,   64, FL.make(tFluid, 250), FL.Latex.make(L/2), FL.Glue.make(250));
		RM.Mixer        .addRecipe1(T, 16,   16, OM.dust(MT.Blaze, U9), FL.make(tFluid, 250), NF, ST.make(Items.magma_cream, 1, 0));
		RM.Mixer        .addRecipe1(T, 16,  144, OM.dust(MT.Blaze    ), FL.make(tFluid,2250), NF, ST.make(Items.magma_cream, 9, 0));
		}
		
		
		if (ENABLE_ADDING_IC2_EXTRACTOR_RECIPES) {
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 0), ST.make(Items.dye, 2,  1));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 1), ST.make(Items.dye, 2, 12));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 2), ST.make(Items.dye, 2, 13));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 3), ST.make(Items.dye, 2,  7));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 4), ST.make(Items.dye, 2,  1));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 5), ST.make(Items.dye, 2, 14));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 6), ST.make(Items.dye, 2,  7));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 7), ST.make(Items.dye, 2,  9));
		RM.ic2_extractor(ST.make(Blocks.red_flower, 1, 8), ST.make(Items.dye, 2,  7));
		RM.ic2_extractor(ST.make(Blocks.yellow_flower,1, 0), ST.make(Items.dye, 2, 11));
		RM.ic2_extractor(ST.make(Blocks.double_plant, 1, 0), ST.make(Items.dye, 3, 11));
		RM.ic2_extractor(ST.make(Blocks.double_plant, 1, 1), ST.make(Items.dye, 3, 13));
		RM.ic2_extractor(ST.make(Blocks.double_plant, 1, 4), ST.make(Items.dye, 3,  1));
		RM.ic2_extractor(ST.make(Blocks.double_plant, 1, 5), ST.make(Items.dye, 3,  9));
		RM.ic2_extractor(ST.make(Blocks.cactus, 1, W), IL.Dye_Cactus.get(2));
		}
		
		RM.ic2_compressor(ST.make(Blocks.red_flower, 8, W), IL.IC2_Plantball.get(1));
		RM.ic2_compressor(ST.make(Blocks.yellow_flower, 8, W), IL.IC2_Plantball.get(1));
		RM.ic2_compressor(ST.make(Blocks.double_plant, 8, W), IL.IC2_Plantball.get(1));
		
		RM.pulverizing(ST.make(Items.reeds, 1, W), IL.Remains_Plant.get(1), T);
		
		RM.biomass(ST.make(Blocks.brown_mushroom_block, 8, W));
		RM.biomass(ST.make(Blocks.red_mushroom_block, 8, W));
		RM.biomass(ST.make(Blocks.brown_mushroom, 8, W));
		RM.biomass(ST.make(Blocks.red_mushroom, 8, W));
		RM.biomass(ST.make(Blocks.red_flower, 8, W));
		RM.biomass(ST.make(Blocks.yellow_flower, 8, W));
		RM.biomass(ST.make(Blocks.double_plant, 8, W));
		RM.biomass(ST.make(Blocks.melon_block, 1, W));
		RM.biomass(ST.make(Blocks.pumpkin, 2, W));
		RM.biomass(ST.make(Blocks.cactus, 8, W));
		RM.biomass(ST.make(Blocks.vine, 8, W));
		RM.biomass(ST.make(Items.reeds, 8, W));
		RM.biomass(ST.make(Items.melon, 9, W));
		RM.biomass(ST.make(Items.wheat, 9, W));
		RM.biomass(ST.make(Items.carrot, 9, W));
		RM.biomass(ST.make(Items.potato, 9, W));
		RM.biomass(ST.make(Items.poisonous_potato, 9, W));
		RM.biomass(IL.Dye_Cactus.get(16));
		RM.biomass(IL.Dye_Cocoa.get(16));
		
		RM.biomass(blockDust.mat(MT.MeatRotten, 1), 16);
		RM.biomass(blockDust.mat(MT.FishRotten, 1), 16);
		RM.biomass(dust.mat(MT.MeatRotten, 9), 16);
		RM.biomass(dust.mat(MT.FishRotten, 9), 16);
		
		RM.add_smelting(ST.make(Blocks.sticky_piston, 1, W), ST.make(Blocks.piston    , 1, 0), F, T, F);
		RM.add_smelting(ST.make(Items.glass_bottle  , 1, W), ST.make(Blocks.glass_pane, 1, 0), F, F, F);
		
		RM.unbox(IL.Plank.get(3), ST.make(Blocks.bookshelf, 1, W), ST.make(Items.book, 3, 0));
		
		RM.pack(ST.make(Items.brick, 4, W), ST.make(Blocks.brick_block, 1, 0));
		RM.pack(ST.make(Items.netherbrick, 4, W), ST.make(Blocks.nether_brick, 1, 0));
		
		RM.generify(plantGtFiber.mat(MT.Black    , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.Red      , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.Green    , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.Brown    , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.Blue     , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.Purple   , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.Cyan     , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.LightGray, 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.Gray     , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.Pink     , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.Lime     , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.Yellow   , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.LightBlue, 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.Magenta  , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.Orange   , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.White    , 1), ST.make(Items.string, 1, 0));
		RM.generify(plantGtFiber.mat(MT.Cu       , 1), ST.make(Items.string, 1, 0));
		
		new Loader_Recipes_OreDict();
	}
}
