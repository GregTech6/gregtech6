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
import static gregapi.data.CS.ToolsGT.*;
import static gregapi.data.OP.*;
import static gregapi.data.TD.Atomic.*;
import static gregapi.data.TD.Compounds.*;
import static gregapi.data.TD.Properties.*;
import static gregapi.oredict.OreDictMaterialCondition.*;

import cpw.mods.fml.common.registry.GameRegistry;
import gregapi.code.ICondition;
import gregapi.code.ICondition.And;
import gregapi.code.ICondition.Nor;
import gregapi.code.ICondition.Or;
import gregapi.code.IItemContainer;
import gregapi.config.ConfigCategories;
import gregapi.data.ANY;
import gregapi.data.CS.ArmorsGT;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.CS.OreDictToolNames;
import gregapi.data.CS.ToolsGT;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.TC;
import gregapi.data.TD;
import gregapi.item.IItemEnergy;
import gregapi.item.ItemArmorBase;
import gregapi.item.multiitem.MultiItemToolWithCompat;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.recipes.AdvancedCraftingTool;
import gregapi.util.CR;
import gregapi.util.ST;
import gregtech.items.tools.crafting.GT_Tool_BendingCylinder;
import gregtech.items.tools.crafting.GT_Tool_BendingCylinderSmall;
import gregtech.items.tools.crafting.GT_Tool_File;
import gregtech.items.tools.crafting.GT_Tool_RollingPin;
import gregtech.items.tools.early.*;
import gregtech.items.tools.electric.*;
import gregtech.items.tools.machine.*;
import gregtech.items.tools.pocket.GT_Tool_Pocket_Chisel;
import gregtech.items.tools.pocket.GT_Tool_Pocket_Cutter;
import gregtech.items.tools.pocket.GT_Tool_Pocket_File;
import gregtech.items.tools.pocket.GT_Tool_Pocket_Knife;
import gregtech.items.tools.pocket.GT_Tool_Pocket_Multitool;
import gregtech.items.tools.pocket.GT_Tool_Pocket_Saw;
import gregtech.items.tools.pocket.GT_Tool_Pocket_Scissors;
import gregtech.items.tools.pocket.GT_Tool_Pocket_Screwdriver;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Loader_Tools implements Runnable {
	@Override
	public void run() {
		// TODO Fix Temp Hazmat Recipes!
		ArmorsGT.HAZMATS_INSECTS    .add(ArmorsGT.HAZMAT_INSECTS    [0] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.insect.head"     , "Bumblehead"                       , "Full Set protects against any Insects"         , "hazard_insects"   , 0, new int[] {1, 1, 1, 1}, 128, 8, 0, F, T, "RRR", "RGR", "q l", 'R', foil.dat(MT.Rubber), 'G', ST.make(Blocks.stained_glass_pane, 1, 15-DYE_INDEX_Black)));
		ArmorsGT.HAZMATS_INSECTS    .add(ArmorsGT.HAZMAT_INSECTS    [1] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.insect.chest"    , "Bumbleshirt"                      , "Full Set protects against any Insects"         , "hazard_insects"   , 1, new int[] {1, 1, 1, 1}, 128, 8, 0, F, T, "RqR", "RRR", "RRR", 'R', foil.dat(MT.Rubber)));
		ArmorsGT.HAZMATS_INSECTS    .add(ArmorsGT.HAZMAT_INSECTS    [2] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.insect.legs"     , "Bumblepants"                      , "Full Set protects against any Insects"         , "hazard_insects"   , 2, new int[] {1, 1, 1, 1}, 128, 8, 0, F, T, "RRR", "RqR", "RlR", 'R', foil.dat(MT.Rubber)));
		ArmorsGT.HAZMATS_INSECTS    .add(ArmorsGT.HAZMAT_INSECTS    [3] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.insect.boots"    , "Bumbleboots"                      , "Full Set protects against any Insects"         , "hazard_insects"   , 3, new int[] {1, 1, 1, 1}, 128, 8, 0, F, T, "RlR", "RqR"       , 'R', foil.dat(MT.Rubber)));
		
		ArmorsGT.HAZMATS_FROST      .add(ArmorsGT.HAZMAT_FROST      [0] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.frost.head"      , "Frost Protection Suit Mask"       , "Full Set protects against Cold"                , "hazard_frost"     , 0, new int[] {1, 1, 1, 1}, 128, 8, 0, F, F, "AAA", "AGA", "q l", 'A', plate.dat(MT.Asbestos), 'G', ST.make(Blocks.stained_glass_pane, 1, 15-DYE_INDEX_Black)));
		ArmorsGT.HAZMATS_FROST      .add(ArmorsGT.HAZMAT_FROST      [1] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.frost.chest"     , "Frost Protection Suit Shirt"      , "Full Set protects against Cold"                , "hazard_frost"     , 1, new int[] {1, 1, 1, 1}, 128, 8, 0, F, F, "AqA", "AAA", "AAA", 'A', plate.dat(MT.Asbestos)));
		ArmorsGT.HAZMATS_FROST      .add(ArmorsGT.HAZMAT_FROST      [2] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.frost.legs"      , "Frost Protection Suit Pants"      , "Full Set protects against Cold"                , "hazard_frost"     , 2, new int[] {1, 1, 1, 1}, 128, 8, 0, F, F, "AAA", "AqA", "AlA", 'A', plate.dat(MT.Asbestos)));
		ArmorsGT.HAZMATS_FROST      .add(ArmorsGT.HAZMAT_FROST      [3] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.frost.boots"     , "Frost Protection Suit Boots"      , "Full Set protects against Cold"                , "hazard_frost"     , 3, new int[] {1, 1, 1, 1}, 128, 8, 0, F, F, "AlA", "AqA"       , 'A', plate.dat(MT.Asbestos)));
		
		ArmorsGT.HAZMATS_HEAT       .add(ArmorsGT.HAZMAT_HEAT       [0] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.heat.head"       , "Heat Protection Suit Mask"        , "Full Set protects against Heat"                , "hazard_heat"      , 0, new int[] {1, 1, 1, 1}, 128, 8, 0, T, F, "FFF", "FGF", "x l", 'F', foil.dat(MT.Al), 'G', ST.make(Blocks.stained_glass_pane, 1, 15-DYE_INDEX_Black)));
		ArmorsGT.HAZMATS_HEAT       .add(ArmorsGT.HAZMAT_HEAT       [1] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.heat.chest"      , "Heat Protection Suit Shirt"       , "Full Set protects against Heat"                , "hazard_heat"      , 1, new int[] {1, 1, 1, 1}, 128, 8, 0, T, F, "FxF", "FFF", "FFF", 'F', foil.dat(MT.Al)));
		ArmorsGT.HAZMATS_HEAT       .add(ArmorsGT.HAZMAT_HEAT       [2] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.heat.legs"       , "Heat Protection Suit Pants"       , "Full Set protects against Heat"                , "hazard_heat"      , 2, new int[] {1, 1, 1, 1}, 128, 8, 0, T, F, "FFF", "FxF", "FlF", 'F', foil.dat(MT.Al)));
		ArmorsGT.HAZMATS_HEAT       .add(ArmorsGT.HAZMAT_HEAT       [3] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.heat.boots"      , "Heat Protection Suit Boots"       , "Full Set protects against Heat"                , "hazard_heat"      , 3, new int[] {1, 1, 1, 1}, 128, 8, 0, T, F, "FlF", "FxF"       , 'F', foil.dat(MT.Al)));
		
		ArmorsGT.HAZMATS_RADIOACTIVE.add(ArmorsGT.HAZMAT_RADIOACTIVE[0] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.radiation.head"  , "Radiation Hazard Suit Mask"       , "Full Set protects against Radiation"           , "hazard_radiation" , 0, new int[] {1, 1, 1, 1}, 128, 8, 0, F, F, "LLL", "LGL", "x l", 'L', plate.dat(MT.Pb), 'G', Blocks.glass_pane));
		ArmorsGT.HAZMATS_RADIOACTIVE.add(ArmorsGT.HAZMAT_RADIOACTIVE[1] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.radiation.chest" , "Radiation Hazard Suit Shirt"      , "Full Set protects against Radiation"           , "hazard_radiation" , 1, new int[] {1, 1, 1, 1}, 128, 8, 0, F, F, "LxL", "LLL", "LLL", 'L', plate.dat(MT.Pb)));
		ArmorsGT.HAZMATS_RADIOACTIVE.add(ArmorsGT.HAZMAT_RADIOACTIVE[2] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.radiation.legs"  , "Radiation Hazard Suit Pants"      , "Full Set protects against Radiation"           , "hazard_radiation" , 2, new int[] {1, 1, 1, 1}, 128, 8, 0, F, F, "LLL", "LxL", "LlL", 'L', plate.dat(MT.Pb)));
		ArmorsGT.HAZMATS_RADIOACTIVE.add(ArmorsGT.HAZMAT_RADIOACTIVE[3] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.radiation.boots" , "Radiation Hazard Suit Boots"      , "Full Set protects against Radiation"           , "hazard_radiation" , 3, new int[] {1, 1, 1, 1}, 128, 8, 0, F, F, "LlL", "LxL"       , 'L', plate.dat(MT.Pb)));
		
										 ArmorsGT.HAZMAT_BIOCHEMGAS [0] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.biochemgas.head" , "Biochemical Gas Hazard Suit Mask" , "Full Set protects against Chemicals and Gases" , "hazard_biochemgas", 0, new int[] {1, 1, 1, 1}, 128, 8, 0, F, F, "RRR", "RGR", "q l", 'R', plate.dat(MT.Rubber), 'G', Blocks.glass_pane);
										 ArmorsGT.HAZMAT_BIOCHEMGAS [1] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.biochemgas.chest", "Biochemical Gas Hazard Suit Shirt", "Full Set protects against Chemicals and Gases" , "hazard_biochemgas", 1, new int[] {1, 1, 1, 1}, 128, 8, 0, F, F, "RqR", "RRR", "RRR", 'R', plate.dat(MT.Rubber));
										 ArmorsGT.HAZMAT_BIOCHEMGAS [2] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.biochemgas.legs" , "Biochemical Gas Hazard Suit Pants", "Full Set protects against Chemicals and Gases" , "hazard_biochemgas", 2, new int[] {1, 1, 1, 1}, 128, 8, 0, F, F, "RRR", "RqR", "RlR", 'R', plate.dat(MT.Rubber));
										 ArmorsGT.HAZMAT_BIOCHEMGAS [3] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.biochemgas.boots", "Biochemical Gas Hazard Suit Boots", "Full Set protects against Chemicals and Gases" , "hazard_biochemgas", 3, new int[] {1, 1, 1, 1}, 128, 8, 0, F, F, "RlR", "RqR"       , 'R', plate.dat(MT.Rubber));
		
										 ArmorsGT.HAZMAT_UNIVERSAL  [0] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.universal.head"  , "Universal Hazard Suit Mask"       , "Full Set protects against Hazards"             , "hazard_universal" , 0, new int[] {1, 1, 1, 1}, 128, 8, 0, T, T, "AlB", "CqD", "ExF", 'A', ST.make(ArmorsGT.HAZMAT_BIOCHEMGAS[0], 1, 0), 'B', ST.make(ArmorsGT.HAZMAT_INSECTS[0], 1, 0), 'C', ST.make(ArmorsGT.HAZMAT_FROST[0], 1, 0), 'D', ST.make(ArmorsGT.HAZMAT_HEAT[0], 1, 0), 'E', ST.make(ArmorsGT.HAZMAT_RADIOACTIVE[0], 1, 0), 'F', ST.make(Items.chainmail_helmet    , 1, 0));
										 ArmorsGT.HAZMAT_UNIVERSAL  [1] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.universal.chest" , "Universal Hazard Suit Shirt"      , "Full Set protects against Hazards"             , "hazard_universal" , 1, new int[] {1, 1, 1, 1}, 128, 8, 0, T, T, "AlB", "CqD", "ExF", 'A', ST.make(ArmorsGT.HAZMAT_BIOCHEMGAS[1], 1, 0), 'B', ST.make(ArmorsGT.HAZMAT_INSECTS[1], 1, 0), 'C', ST.make(ArmorsGT.HAZMAT_FROST[1], 1, 0), 'D', ST.make(ArmorsGT.HAZMAT_HEAT[1], 1, 0), 'E', ST.make(ArmorsGT.HAZMAT_RADIOACTIVE[1], 1, 0), 'F', ST.make(Items.chainmail_chestplate, 1, 0));
										 ArmorsGT.HAZMAT_UNIVERSAL  [2] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.universal.legs"  , "Universal Hazard Suit Pants"      , "Full Set protects against Hazards"             , "hazard_universal" , 2, new int[] {1, 1, 1, 1}, 128, 8, 0, T, T, "AlB", "CqD", "ExF", 'A', ST.make(ArmorsGT.HAZMAT_BIOCHEMGAS[2], 1, 0), 'B', ST.make(ArmorsGT.HAZMAT_INSECTS[2], 1, 0), 'C', ST.make(ArmorsGT.HAZMAT_FROST[2], 1, 0), 'D', ST.make(ArmorsGT.HAZMAT_HEAT[2], 1, 0), 'E', ST.make(ArmorsGT.HAZMAT_RADIOACTIVE[2], 1, 0), 'F', ST.make(Items.chainmail_leggings  , 1, 0));
										 ArmorsGT.HAZMAT_UNIVERSAL  [3] = new ItemArmorBase(MD.GT.mID, "gt.armor.hazmat.universal.boots" , "Universal Hazard Suit Boots"      , "Full Set protects against Hazards"             , "hazard_universal" , 3, new int[] {1, 1, 1, 1}, 128, 8, 0, T, T, "AlB", "CqD", "ExF", 'A', ST.make(ArmorsGT.HAZMAT_BIOCHEMGAS[3], 1, 0), 'B', ST.make(ArmorsGT.HAZMAT_INSECTS[3], 1, 0), 'C', ST.make(ArmorsGT.HAZMAT_FROST[3], 1, 0), 'D', ST.make(ArmorsGT.HAZMAT_HEAT[3], 1, 0), 'E', ST.make(ArmorsGT.HAZMAT_RADIOACTIVE[3], 1, 0), 'F', ST.make(Items.chainmail_boots     , 1, 0));
		
		for (ItemArmorBase tArmor : ArmorsGT.HAZMAT_UNIVERSAL) {
		ArmorsGT.HAZMATS_GAS        .add(tArmor);
		ArmorsGT.HAZMATS_BIO        .add(tArmor);
		ArmorsGT.HAZMATS_CHEM       .add(tArmor);
		ArmorsGT.HAZMATS_INSECTS    .add(tArmor);
		ArmorsGT.HAZMATS_FROST      .add(tArmor);
		ArmorsGT.HAZMATS_HEAT       .add(tArmor);
		ArmorsGT.HAZMATS_RADIOACTIVE.add(tArmor);
		ArmorsGT.HAZMATS_LIGHTNING  .add(tArmor);
		}
		for (ItemArmorBase tArmor : ArmorsGT.HAZMAT_BIOCHEMGAS) {
		ArmorsGT.HAZMATS_GAS        .add(tArmor);
		ArmorsGT.HAZMATS_BIO        .add(tArmor);
		ArmorsGT.HAZMATS_CHEM       .add(tArmor);
		}
		
		ToolsGT.sMetaTool = new MultiItemToolWithCompat(MD.GT.mID, "gt.metatool.01");
		
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.SWORD                     , "Sword"                           , ""                                                    , new GT_Tool_Sword()                                            .setMaterialAmount(toolHeadSword                     .mAmount), OreDictToolNames.sword, OreDictToolNames.blade                                                                                           , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.TELUM         , 4)                                                                ), TOOL_sword);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.PICKAXE                   , "Pickaxe"                         , ""                                                    , new GT_Tool_Pickaxe()                                          .setMaterialAmount(toolHeadPickaxe                   .mAmount), OreDictToolNames.pickaxe                                                                                                                 , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.PERFODIO      , 4)                                                                ), TOOL_pickaxe);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.SHOVEL                    , "Shovel"                          , ""                                                    , new GT_Tool_Shovel()                                           .setMaterialAmount(toolHeadShovel                    .mAmount), OreDictToolNames.shovel                                                                                                                  , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.PERFODIO      , 4)                                                                ), TOOL_shovel);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.SPADE                     , "Spade"                           , "for Snow, Grass, Mycelium, Podzol, Mud and Clay"     , new GT_Tool_Spade()                                            .setMaterialAmount(toolHeadSpade                     .mAmount), OreDictToolNames.shovel                                                                                                                  , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.PERFODIO      , 4)                                                                ), TOOL_shovel);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.AXE                       , "Axe"                             , "Faster on Logs. Chops down whole Trees."             , new GT_Tool_Axe()                                              .setMaterialAmount(toolHeadAxe                       .mAmount), OreDictToolNames.axe                                                                                                                     , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.METO          , 2), TC.stack(TC.ARBOR         , 2)                                ), TOOL_axe);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.DOUBLE_AXE                , "Double Axe"                      , "Chops down whole Trees and has a slow Attack Rate"   , new GT_Tool_AxeDouble()                                        .setMaterialAmount(toolHeadAxeDouble                 .mAmount), OreDictToolNames.axe                                                                                                                     , TC.stack(TC.INSTRUMENTUM  , 3), TC.stack(TC.METO          , 2), TC.stack(TC.ARBOR         , 2)                                ), TOOL_axe);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.HOE                       , "Hoe"                             , ""                                                    , new GT_Tool_Hoe()                                              .setMaterialAmount(toolHeadHoe                       .mAmount), OreDictToolNames.hoe                                                                                                                     , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.METO          , 2), TC.stack(TC.MESSIS        , 2)                                ), TOOL_hoe);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.SAW                       , "Saw"                             , "Faster on Planks. Slower on Logs. Can harvest Ice."  , new GT_Tool_Saw()                                              .setMaterialAmount(toolHeadSaw                       .mAmount), OreDictToolNames.saw                                                                                                                     , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.METO          , 2), TC.stack(TC.ARBOR         , 2)                                ), TOOL_saw);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.HARDHAMMER                , "Hammer"                          , "Crushes Ores instead of harvesting them"             , new GT_Tool_HardHammer()                                       .setMaterialAmount(toolHeadHammer                    .mAmount), OreDictToolNames.hammer                                                                                                                  , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.FABRICO       , 2), TC.stack(TC.ORDO          , 2)                                ), TOOL_hammer);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.SOFTHAMMER                , "Soft Hammer"                     , "Can rotate vanilla-ish things and toggle Lamps/Rails", new GT_Tool_SoftHammer()                                       .setMaterialAmount(toolHeadHammer                    .mAmount), OreDictToolNames.softhammer                                                                                                              , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.LIMUS         , 4)                                                                ), TOOL_softhammer);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.WRENCH                    , "Wrench"                          , "Hold Leftclick to dismantle Machines"                , new GT_Tool_Wrench()                                           .setMaterialAmount(6*U                                       ), OreDictToolNames.wrench                                                                                                                  , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.MACHINA       , 2), TC.stack(TC.ORDO          , 2)                                ), TOOL_wrench);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.FILE                      , "File"                            , "Harvests Iron Bars and similar faster"               , new GT_Tool_File()                                             .setMaterialAmount(toolHeadFile                      .mAmount), OreDictToolNames.file                                                                                                                    , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.FABRICO       , 2), TC.stack(TC.ORDO          , 2)                                ), TOOL_file);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.CROWBAR                   , "Crowbar"                         , "Harvests anything that cannot be harvested otherwise", new GT_Tool_Crowbar()                                          .setMaterialAmount(3*U2                                      ), OreDictToolNames.crowbar                                                                                                                 , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.FABRICO       , 2), TC.stack(TC.TELUM         , 2)                                ), TOOL_crowbar);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.SCREWDRIVER               , "Screwdriver"                     , ""                                                    , new GT_Tool_Screwdriver()                                      .setMaterialAmount(toolHeadScrewdriver               .mAmount), OreDictToolNames.screwdriver                                                                                                             , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.FABRICO       , 2), TC.stack(TC.ORDO          , 2)                                ), TOOL_screwdriver);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.CLUB                      , "Club"                            , "A blunt primitive weapon with a lot of Power"        , new GT_Tool_Club()                                             .setMaterialAmount(6*U                                       )                                                                                                                                           , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.HUMANUS       , 2), TC.stack(TC.STRONTIO      , 2)                                ), TOOL_hammer);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.WIRECUTTER                , "Wire Cutter"                     , "Harvests Cables and Wires faster"                    , new GT_Tool_WireCutter()                                       .setMaterialAmount(4*U                                       ), OreDictToolNames.wirecutter                                                                                                              , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.FABRICO       , 2), TC.stack(TC.ORDO          , 2)                                ), TOOL_cutter);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.SCOOP                     , "Scoop"                           , ""                                                    , new GT_Tool_Scoop()                                            .setMaterialAmount(3*U                                       ), OreDictToolNames.scoop                                                                                                                   , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.BESTIA        , 2), TC.stack(TC.PANNUS        , 2)                                ), TOOL_scoop);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.BRANCHCUTTER              , "Branch Cutter"                   , ""                                                    , new GT_Tool_BranchCutter()                                     .setMaterialAmount(5*U                                       ), OreDictToolNames.branchcutter                                                                                                            , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.METO          , 2), TC.stack(TC.HERBA         , 2)                                ), TOOL_grafter);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.UNIVERSALSPADE            , "Universal Spade"                 , ""                                                    , new GT_Tool_UniversalSpade()                                   .setMaterialAmount(toolHeadUniversalSpade            .mAmount), OreDictToolNames.blade, OreDictToolNames.shovel, OreDictToolNames.crowbar, OreDictToolNames.saw                                          , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.TELUM         , 1), TC.stack(TC.METO          , 1), TC.stack(TC.FABRICO       , 1)), TOOL_crowbar);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.KNIFE                     , "Knife"                           , ""                                                    , new GT_Tool_Knife()                                            .setMaterialAmount(1*U                                       ), OreDictToolNames.blade, OreDictToolNames.knife, OreDictToolNames.hac_cuttingboard                                                        , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.TELUM         , 2), TC.stack(TC.CORPUS        , 2)                                ), TOOL_knife);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.BUTCHERYKNIFE             , "Butchery Knife"                  , "Has a slow Attack Rate"                              , new GT_Tool_ButcheryKnife()                                    .setMaterialAmount(4*U                                       ), OreDictToolNames.blade                                                                                                                   , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.FABRICO       , 2), TC.stack(TC.CORPUS        , 4)                                ), TOOL_knife);
//      ToolsGT.add(ToolIDs.sMetaTool.addTool(ToolIDs.SICKLE                    , "Sickle"                          , "Harvests Crops more efficiently"                     , new GT_Tool_Sickle()                                           .setMaterialAmount(3*U                                       ), OreDictToolNames.blade                                                                                                                   , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.METO          , 2), TC.stack(TC.GRANUM        , 2)                                ), TOOL_sickle);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.SENSE                     , "Sense"                           , "Because a Scythe doesn't make Sense"                 , new GT_Tool_Sense()                                            .setMaterialAmount(toolHeadSense                     .mAmount), OreDictToolNames.blade                                                                                                                   , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.METO          , 2), TC.stack(TC.HERBA         , 2), TC.stack(TC.MORTUUS       , 2)), TOOL_sense);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.PLOW                      , "Plow"                            , "Used to get rid of Snow"                             , new GT_Tool_Plow()                                             .setMaterialAmount(toolHeadPlow                      .mAmount), OreDictToolNames.plow                                                                                                                    , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.METO          , 2), TC.stack(TC.GELUM         , 2)                                ), TOOL_plow);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.PLUNGER                   , "Plunger"                         , "Not as good at cleaning Pipes as flaming Flowers"    , new GT_Tool_Plunger()                                          .setMaterialAmount(0                                         ), OreDictToolNames.plunger                                                                                                                 , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.VACUOS        , 2), TC.stack(TC.ITER          , 2)                                ), TOOL_plunger);
					ToolsGT.sMetaTool.addTool(ToolsGT.ROLLING_PIN               , "Rolling Pin"                     , ""                                                    , new GT_Tool_RollingPin()                                       .setMaterialAmount(2*U                                       ), OreDictToolNames.rollingpin                                                                                                              , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.LIMUS         , 4)                                                                );
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.CHISEL                    , "Chisel"                          , "Be slow/careful with it on Servers because Ping!"    , new GT_Tool_Chisel()                                           .setMaterialAmount(toolHeadChisel                    .mAmount), OreDictToolNames.chisel                                                                                                                  , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.PERMUTATIO    , 1), TC.stack(TC.PERDITIO      , 1)                                ), TOOL_chisel);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.FLINT_AND_TINDER          , "Flint and Tinder"                , ""                                                    , new GT_Tool_FlintAndTinder()                                   .setMaterialAmount(0                                         ), OreDictToolNames.flintandtinder, OD.craftingFirestarter                                                                                  , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.IGNIS         , 2)                                                                ), TOOL_igniter);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.MONKEY_WRENCH             , "Monkey Wrench"                   , "Hold Leftclick to dismantle Machines"                , new GT_Tool_MonkeyWrench()                                     .setMaterialAmount(6*U                                       ), OreDictToolNames.monkeywrench                                                                                                            , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.MACHINA       , 2), TC.stack(TC.ORDO          , 2)                                ), TOOL_monkeywrench);
					ToolsGT.sMetaTool.addTool(ToolsGT.BENDING_CYLINDER          , "Bending Cylinder"                , ""                                                    , new GT_Tool_BendingCylinder()                                  .setMaterialAmount(6*U                                       ), OreDictToolNames.bendingcylinder                                                                                                         , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.METALLUM      , 4)                                                                );
					ToolsGT.sMetaTool.addTool(ToolsGT.BENDING_CYLINDER_SMALL    , "Small Bending Cylinder"          , ""                                                    , new GT_Tool_BendingCylinderSmall()                             .setMaterialAmount(3*U                                       ), OreDictToolNames.bendingcylindersmall                                                                                                    , TC.stack(TC.INSTRUMENTUM  , 1), TC.stack(TC.METALLUM      , 2)                                                                );
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.CONSTRUCTION_PICK         , "Construction Pick"               , "Good for Bricks and alike, terrible for mining"      , new GT_Tool_PickaxeConstruction()                              .setMaterialAmount(toolHeadConstructionPickaxe       .mAmount), OreDictToolNames.pickaxe                                                                                                                 , TC.stack(TC.INSTRUMENTUM  , 3), TC.stack(TC.PERFODIO      , 3)                                                                ), TOOL_pickaxe);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.MAGNIFYING_GLASS          , "Magnifying Glass"                , "Crafted with a Stick and a Lens"                     , new GT_Tool_MagnifyingGlass()                                  .setMaterialAmount(lens                              .mAmount), OreDictToolNames.magnifyingglass                                                                                                         , TC.stack(TC.INSTRUMENTUM  , 1), TC.stack(TC.SENSUS        , 2)                                                                ), TOOL_magnifyingglass);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.SCISSORS                  , "Scissors"                        , "Don't run around while holding them!"                , new GT_Tool_Scissors()                                         .setMaterialAmount(U*2+screw.mAmount+2*ring          .mAmount), OreDictToolNames.scissors, OreDictToolNames.shears                                                                                       , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.TELUM         , 2), TC.stack(TC.PANNUS        , 2)                                ), TOOL_scissors);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.PINCERS                   , "Pincers"                         , ""                                                    , new GT_Tool_Pincers()                                          .setMaterialAmount(U*2+screw.mAmount+2*stick         .mAmount), OreDictToolNames.pincers                                                                                                                 , TC.stack(TC.INSTRUMENTUM  , 4), TC.stack(TC.FABRICO       , 2)                                                                ), TOOL_pincers);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.GEM_PICK                  , "Gem tipped Pickaxe"              , "Quarter of the Durability of a full Gem Pick"        , new GT_Tool_PickaxeGem()                                       .setMaterialAmount(toolHeadPickaxeGem                .mAmount), OreDictToolNames.pickaxe                                                                                                                 , TC.stack(TC.INSTRUMENTUM  , 3), TC.stack(TC.PERFODIO      , 3)                                                                ), TOOL_pickaxe);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.HAND_DRILL                , "Hand Drill"                      , ""                                                    , new GT_Tool_HandDrill()                                        .setMaterialAmount(toolHeadArrow.mAmount+2*bolt      .mAmount)                                                                                                                                           , TC.stack(TC.INSTRUMENTUM  , 2), TC.stack(TC.PERFODIO      , 1)                                                                ), TOOL_drill);
					ToolsGT.sMetaTool.addTool(ToolsGT.BUILDERWAND               , "Builders Wand"                   , ""                                                    , new GT_Tool_Builderwand()                                      .setMaterialAmount(toolHeadBuilderwand               .mAmount)                                                                                                                                           , TC.stack(TC.INSTRUMENTUM  , 1), TC.stack(TC.ORDO          , 1), TC.stack(TC.PRAECANTIO    , 1)                                );
		
		
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.MININGDRILL_LV            , "Mining Drill (LV)"               , ""                                                    , new GT_Tool_MiningDrill_LV()                                   .setMaterialAmount(toolHeadDrill                     .mAmount), OreDictToolNames.miningdrill                                                                                                             , TC.stack(TC.MACHINA       , 2), TC.stack(TC.PERFODIO      , 4)                                                                ), TOOL_pickaxe);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.MININGDRILL_MV            , "Mining Drill (MV)"               , ""                                                    , new GT_Tool_MiningDrill_MV()                                   .setMaterialAmount(toolHeadDrill                     .mAmount), OreDictToolNames.miningdrill                                                                                                             , TC.stack(TC.MACHINA       , 2), TC.stack(TC.PERFODIO      , 4)                                                                ), TOOL_pickaxe);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.MININGDRILL_HV            , "Mining Drill (HV)"               , ""                                                    , new GT_Tool_MiningDrill_HV()                                   .setMaterialAmount(toolHeadDrill                     .mAmount), OreDictToolNames.miningdrill                                                                                                             , TC.stack(TC.MACHINA       , 2), TC.stack(TC.PERFODIO      , 4)                                                                ), TOOL_pickaxe);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.CHAINSAW_LV               , "Chainsaw (LV)"                   , "Can also harvest Ice"                                , new GT_Tool_Chainsaw_LV()                                      .setMaterialAmount(toolHeadChainsaw                  .mAmount), OreDictToolNames.saw                                                                                                                     , TC.stack(TC.MACHINA       , 2), TC.stack(TC.METO          , 2), TC.stack(TC.ARBOR         , 2)                                ), TOOL_saw);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.CHAINSAW_MV               , "Chainsaw (MV)"                   , "Can also harvest Ice"                                , new GT_Tool_Chainsaw_MV()                                      .setMaterialAmount(toolHeadChainsaw                  .mAmount), OreDictToolNames.saw                                                                                                                     , TC.stack(TC.MACHINA       , 2), TC.stack(TC.METO          , 2), TC.stack(TC.ARBOR         , 2)                                ), TOOL_saw);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.CHAINSAW_HV               , "Chainsaw (HV)"                   , "Can also harvest Ice"                                , new GT_Tool_Chainsaw_HV()                                      .setMaterialAmount(toolHeadChainsaw                  .mAmount), OreDictToolNames.saw                                                                                                                     , TC.stack(TC.MACHINA       , 2), TC.stack(TC.METO          , 2), TC.stack(TC.ARBOR         , 2)                                ), TOOL_saw);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.WRENCH_LV                 , "Wrench (LV)"                     , "Sneak Rightclick to switch to Monkey Wrench"         , new GT_Tool_Wrench_LV(ToolsGT.MONKEY_WRENCH_LV)                .setMaterialAmount(toolHeadWrench                    .mAmount), OreDictToolNames.wrench                                                                                                                  , TC.stack(TC.MACHINA       , 4), TC.stack(TC.ORDO          , 2)                                                                ), TOOL_wrench);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.WRENCH_MV                 , "Wrench (MV)"                     , "Sneak Rightclick to switch to Monkey Wrench"         , new GT_Tool_Wrench_MV(ToolsGT.MONKEY_WRENCH_MV)                .setMaterialAmount(toolHeadWrench                    .mAmount), OreDictToolNames.wrench                                                                                                                  , TC.stack(TC.MACHINA       , 4), TC.stack(TC.ORDO          , 2)                                                                ), TOOL_wrench);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.WRENCH_HV                 , "Wrench (HV)"                     , "Sneak Rightclick to switch to Monkey Wrench"         , new GT_Tool_Wrench_HV(ToolsGT.MONKEY_WRENCH_HV)                .setMaterialAmount(toolHeadWrench                    .mAmount), OreDictToolNames.wrench                                                                                                                  , TC.stack(TC.MACHINA       , 4), TC.stack(TC.ORDO          , 2)                                                                ), TOOL_wrench);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.JACKHAMMER_HV_Normal      , "JackHammer (HV, Normal Mode)"    , "Breaks Rocks into pieces"                            , new GT_Tool_JackHammer_HV_Normal(ToolsGT.JACKHAMMER_HV_No_Ores).setMaterialAmount(3*U2                                      ), OreDictToolNames.jackhammer                                                                                                              , TC.stack(TC.MACHINA       , 2), TC.stack(TC.PERFODIO      , 2), TC.stack(TC.PERDITIO      , 2)                                ), TOOL_pickaxe);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.JACKHAMMER_HV_No_Ores     , "JackHammer (HV, No Ores Mode)"   , "Doesn't break Ore Blocks, except Layer Ores"         , new GT_Tool_JackHammer_HV_No_Ores(ToolsGT.JACKHAMMER_HV_Normal).setMaterialAmount(3*U2                                      ), OreDictToolNames.jackhammer                                                                                                              , TC.stack(TC.MACHINA       , 2), TC.stack(TC.PERFODIO      , 2), TC.stack(TC.PERDITIO      , 2)                                ), TOOL_pickaxe);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.BUZZSAW_LV                , "Buzzsaw (LV)"                    , "Not suitable for harvesting Blocks"                  , new GT_Tool_BuzzSaw_LV()                                       .setMaterialAmount(toolHeadBuzzSaw                   .mAmount), OreDictToolNames.saw                                                                                                                     , TC.stack(TC.MACHINA       , 2), TC.stack(TC.FABRICO       , 2), TC.stack(TC.ARBOR         , 2)                                ), TOOL_saw);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.SCREWDRIVER_LV            , "Screwdriver (LV)"                , ""                                                    , new GT_Tool_Screwdriver_LV()                                   .setMaterialAmount(toolHeadScrewdriver               .mAmount), OreDictToolNames.screwdriver                                                                                                             , TC.stack(TC.MACHINA       , 2), TC.stack(TC.FABRICO       , 2), TC.stack(TC.ORDO          , 2)                                ), TOOL_screwdriver);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.DRILL_LV                  , "Hand Drill (LV)"                 , ""                                                    , new GT_Tool_Drill_LV()                                         .setMaterialAmount(stick                             .mAmount), OreDictToolNames.drill                                                                                                                   , TC.stack(TC.MACHINA       , 2), TC.stack(TC.FABRICO       , 2), TC.stack(TC.PERFODIO      , 1)                                ), TOOL_drill);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.MIXER_LV                  , "Hand Mixer (LV)"                 , "Doesn't consume exhaustion in the Mixing Bowls"      , new GT_Tool_Mixer_LV()                                         .setMaterialAmount(3*U2                                      ), OreDictToolNames.mixer                                                                                                                   , TC.stack(TC.MACHINA       , 2), TC.stack(TC.FABRICO       , 2), TC.stack(TC.PERMUTATIO    , 1)                                ), TOOL_mixer);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.MONKEY_WRENCH_LV          , "Monkey Wrench (LV)"              , "Sneak Rightclick to switch to Wrench"                , new GT_Tool_MonkeyWrench_LV(ToolsGT.WRENCH_LV)                 .setMaterialAmount(toolHeadWrench                    .mAmount), OreDictToolNames.monkeywrench                                                                                                            , TC.stack(TC.MACHINA       , 4), TC.stack(TC.ORDO          , 2)                                                                ), TOOL_monkeywrench);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.MONKEY_WRENCH_MV          , "Monkey Wrench (MV)"              , "Sneak Rightclick to switch to Wrench"                , new GT_Tool_MonkeyWrench_MV(ToolsGT.WRENCH_MV)                 .setMaterialAmount(toolHeadWrench                    .mAmount), OreDictToolNames.monkeywrench                                                                                                            , TC.stack(TC.MACHINA       , 4), TC.stack(TC.ORDO          , 2)                                                                ), TOOL_monkeywrench);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.MONKEY_WRENCH_HV          , "Monkey Wrench (HV)"              , "Sneak Rightclick to switch to Wrench"                , new GT_Tool_MonkeyWrench_HV(ToolsGT.WRENCH_HV)                 .setMaterialAmount(toolHeadWrench                    .mAmount), OreDictToolNames.monkeywrench                                                                                                            , TC.stack(TC.MACHINA       , 4), TC.stack(TC.ORDO          , 2)                                                                ), TOOL_monkeywrench);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.TRIMMER_LV                , "Trimmer (LV)"                    , ""                                                    , new GT_Tool_Trimmer_LV()                                       .setMaterialAmount(2*toolHeadSword                   .mAmount), OreDictToolNames.branchcutter                                                                                                            , TC.stack(TC.MACHINA       , 2), TC.stack(TC.METO          , 2), TC.stack(TC.HERBA         , 2)                                ), TOOL_grafter);
		
					ToolsGT.sMetaTool.addTool(ToolsGT.POCKET_MULTITOOL          , "Pocket Multitool"                , "7 useful Tools in one!"                              , new GT_Tool_Pocket_Multitool(ToolsGT.POCKET_KNIFE)             .setMaterialAmount(199*U18                                   )                                                                                                                                           , TC.stack(TC.INSTRUMENTUM  , 6), TC.stack(TC.FABRICO       , 3), TC.stack(TC.ORDO          , 3)                                );
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.POCKET_KNIFE              , "Pocket Multitool (Knife)"        , ""                                                    , new GT_Tool_Pocket_Knife(ToolsGT.POCKET_SAW)                   .setMaterialAmount(199*U18                                   ), OreDictToolNames.blade, OreDictToolNames.knife, OreDictToolNames.hac_cuttingboard                                                        , TC.stack(TC.INSTRUMENTUM  , 6), TC.stack(TC.FABRICO       , 3), TC.stack(TC.ORDO          , 3)                                ), TOOL_knife);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.POCKET_SAW                , "Pocket Multitool (Saw)"          , "Faster on Planks. Slower on Logs. Can harvest Ice."  , new GT_Tool_Pocket_Saw(ToolsGT.POCKET_FILE)                    .setMaterialAmount(199*U18                                   ), OreDictToolNames.saw                                                                                                                     , TC.stack(TC.INSTRUMENTUM  , 6), TC.stack(TC.FABRICO       , 3), TC.stack(TC.ORDO          , 3)                                ), TOOL_saw);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.POCKET_FILE               , "Pocket Multitool (File)"         , "Harvests Iron Bars and similar faster"               , new GT_Tool_Pocket_File(ToolsGT.POCKET_SCREWDRIVER)            .setMaterialAmount(199*U18                                   ), OreDictToolNames.file                                                                                                                    , TC.stack(TC.INSTRUMENTUM  , 6), TC.stack(TC.FABRICO       , 3), TC.stack(TC.ORDO          , 3)                                ), TOOL_file);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.POCKET_SCREWDRIVER        , "Pocket Multitool (Screwdriver)"  , ""                                                    , new GT_Tool_Pocket_Screwdriver(ToolsGT.POCKET_WIRECUTTER)      .setMaterialAmount(199*U18                                   ), OreDictToolNames.screwdriver                                                                                                             , TC.stack(TC.INSTRUMENTUM  , 6), TC.stack(TC.FABRICO       , 3), TC.stack(TC.ORDO          , 3)                                ), TOOL_screwdriver);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.POCKET_WIRECUTTER         , "Pocket Multitool (Wirecutter)"   , "Harvests Cables and Wires faster"                    , new GT_Tool_Pocket_Cutter(ToolsGT.POCKET_SCISSORS)             .setMaterialAmount(199*U18                                   ), OreDictToolNames.wirecutter                                                                                                              , TC.stack(TC.INSTRUMENTUM  , 6), TC.stack(TC.FABRICO       , 3), TC.stack(TC.ORDO          , 3)                                ), TOOL_cutter);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.POCKET_SCISSORS           , "Pocket Multitool (Scissors)"     , "Don't run around while holding them!"                , new GT_Tool_Pocket_Scissors(ToolsGT.POCKET_CHISEL)             .setMaterialAmount(199*U18                                   ), OreDictToolNames.scissors, OreDictToolNames.shears                                                                                       , TC.stack(TC.INSTRUMENTUM  , 6), TC.stack(TC.FABRICO       , 3), TC.stack(TC.ORDO          , 3)                                ), TOOL_scissors);
		ToolsGT.add(ToolsGT.sMetaTool.addTool(ToolsGT.POCKET_CHISEL             , "Pocket Multitool (Chisel)"       , "Be slow/careful with it on Servers because Ping!"    , new GT_Tool_Pocket_Chisel(ToolsGT.POCKET_MULTITOOL)            .setMaterialAmount(199*U18                                   ), OreDictToolNames.chisel                                                                                                                  , TC.stack(TC.INSTRUMENTUM  , 6), TC.stack(TC.FABRICO       , 3), TC.stack(TC.ORDO          , 3)                                ), TOOL_chisel);
		
		ItemsGT.addNEIRedirects(ST.make(ToolsGT.sMetaTool, 1, ToolsGT.JACKHAMMER_HV_Normal), ST.make(ToolsGT.sMetaTool, 1, ToolsGT.JACKHAMMER_HV_Normal+1), ST.make(ToolsGT.sMetaTool, 1, ToolsGT.JACKHAMMER_HV_No_Ores), ST.make(ToolsGT.sMetaTool, 1, ToolsGT.JACKHAMMER_HV_No_Ores+1));
		
		for (int i = 0; i < 8; i++) ItemsGT.addNEIRedirect(
			ST.make(ToolsGT.sMetaTool, 1, ToolsGT.POCKET_MULTITOOL+  i      *2),
			ST.make(ToolsGT.sMetaTool, 1, ToolsGT.POCKET_MULTITOOL+((i+1)%8)*2),
			ST.make(ToolsGT.sMetaTool, 1, ToolsGT.POCKET_MULTITOOL+((i+2)%8)*2),
			ST.make(ToolsGT.sMetaTool, 1, ToolsGT.POCKET_MULTITOOL+((i+3)%8)*2),
			ST.make(ToolsGT.sMetaTool, 1, ToolsGT.POCKET_MULTITOOL+((i+4)%8)*2),
			ST.make(ToolsGT.sMetaTool, 1, ToolsGT.POCKET_MULTITOOL+((i+5)%8)*2),
			ST.make(ToolsGT.sMetaTool, 1, ToolsGT.POCKET_MULTITOOL+((i+6)%8)*2),
			ST.make(ToolsGT.sMetaTool, 1, ToolsGT.POCKET_MULTITOOL+((i+7)%8)*2)
		);
		
		// TODO Guns: ToolsGT.sMetaTool.addTool(ToolsGT.PISTOL, "Pistol", "Single Shot, Moderate Damage", new GT_Tool_Gun().setMaterialAmount(U*3), TC.stack(TC.TELUM, 6), TC.stack(TC.FABRICO, 3), TC.stack(TC.IGNIS, 3));
		
		
		
		
		GAPI.mBeforePostInit.add(new Runnable() {@SuppressWarnings({"rawtypes", "unchecked"})
		@Override public void run() {
		
		CR.shapeless(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, 1, MT.Steel, MT.Flint), CR.DEF    , new Object[] {ST.make(Items.flint_and_steel, 1, 0)});
		CR.shaped(ST.make(Items.flint_and_steel, 1, 0)               , CR.DEL_OTHER_NATIVE_RECIPES | CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', nugget.dat(MT.Steel));
		for (OreDictMaterial tMaterial : ANY.Iron.mToThis) if (tMaterial != MT.Steel) {
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', nugget.dat(tMaterial));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', rockGt.dat(tMaterial));
		}
		for (OreDictMaterial tMaterial : ANY.Jasper.mToThis) {
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', gemChipped.dat(tMaterial));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', rockGt.dat(tMaterial));
		}
		for (OreDictMaterial tMaterial : ANY.TigerEye.mToThis) {
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', gemChipped.dat(tMaterial));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', rockGt.dat(tMaterial));
		}
		for (OreDictMaterial tMaterial : ANY.Aventurine.mToThis) {
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', gemChipped.dat(tMaterial));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', rockGt.dat(tMaterial));
		}
		for (OreDictMaterial tMaterial : ANY.Quartz.mToThis) {
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', gem.dat(tMaterial));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', rockGt.dat(tMaterial));
		}
		for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.Phosphorite, MT.Phosphorus, MT.PO4, MT.P, MT.Apatite}) {
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', gem.dat(tMaterial));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', rockGt.dat(tMaterial));
		}
		for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.Au, MT.As, MT.Co, MT.Co_60, MT.Ni, MT.HSLA, MT.Thaumium, MT.DamascusSteel, MT.Netherite, MT.NetherizedDiamond}) {
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', nugget.dat(tMaterial));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', rockGt.dat(tMaterial));
		}
		for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.Chert}) {
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', stone.dat(tMaterial));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', rockGt.dat(tMaterial));
		}
		for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.Pyrite, MT.Fe2O3, MT.OREMATS.Cobaltite, MT.OREMATS.Garnierite, MT.OREMATS.Pentlandite, MT.OREMATS.Chromite, MT.OREMATS.Ilmenite, MT.OREMATS.BrownLimonite, MT.OREMATS.YellowLimonite, MT.OREMATS.Magnetite, MT.OREMATS.BasalticMineralSand, MT.OREMATS.GraniticMineralSand, MT.OREMATS.Arsenopyrite, MT.OREMATS.Chalcopyrite}) {
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.FLINT_AND_TINDER, tMaterial, MT.Flint), CR.DEF_MIR, "T ", " F", 'F', OD.itemFlint, 'T', rockGt.dat(tMaterial));
		}
		
		
		for (OreDictMaterial tMaterial : new OreDictMaterial[] {MT.Plastic, MT.Syrmorite, MT.Au, MT.Al, MT.Cr, MT.StainlessSteel, MT.Netherite, MT.NetherizedDiamond})
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.ROLLING_PIN, tMaterial, tMaterial), CR.DEF_MIR, "  S", " I ", "S f", 'I', ingot.dat(tMaterial), 'S', stick.dat(tMaterial));
		
		for (Object[] tHandle : new Object[][] {{ANY.Wood, OD.stickAnyWood}, {MT.PetrifiedWood, stick.dat(MT.PetrifiedWood)}, {MT.Bamboo, OD.bamboo}, {MT.Bone, Items.bone}, {MT.Plastic, stick.dat(MT.Plastic)}}) {
		
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.KNIFE      , MT.Flint , (OreDictMaterial)tHandle[0]), CR.DEF_MIR, "SX"               , 'S', tHandle[1], 'X', OD.itemFlint);
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.AXE        , MT.Flint , (OreDictMaterial)tHandle[0]), CR.DEF_MIR, "XX" , "XS"        , 'S', tHandle[1], 'X', OD.itemFlint);
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SHOVEL     , MT.Flint , (OreDictMaterial)tHandle[0]), CR.DEF    ,  "X" ,  "S"        , 'S', tHandle[1], 'X', OD.itemFlint);
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.PICKAXE    , MT.Flint , (OreDictMaterial)tHandle[0]), CR.DEF    , "XXX", " S "       , 'S', tHandle[1], 'X', OD.itemFlint);
		
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CLUB       , MT.Bone  , (OreDictMaterial)tHandle[0]), CR.DEF_MIR, "  X", " X ", "S  ", 'S', tHandle[1], 'X', Items.bone);
		
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.Obsidian}) {
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.KNIFE      , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF_MIR, "SX"               , 'S', tHandle[1], 'X', rockGt.dat(tRock));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.AXE        , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF_MIR, "XX" , "XS"        , 'S', tHandle[1], 'X', rockGt.dat(tRock));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SHOVEL     , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF    ,  "X" ,  "S"        , 'S', tHandle[1], 'X', rockGt.dat(tRock));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.PICKAXE    , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF    , "XXX", " S "       , 'S', tHandle[1], 'X', rockGt.dat(tRock));
		}
		for (OreDictMaterial tRock : new OreDictMaterial[] {MT.PetrifiedWood}) {
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.AXE        , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF_MIR, "XX" , "XS"        , 'S', tHandle[1], 'X', rockGt.dat(tRock));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.HOE        , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF_MIR, "XX" , " S"        , 'S', tHandle[1], 'X', rockGt.dat(tRock));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SHOVEL     , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF    ,  "X" ,  "S"        , 'S', tHandle[1], 'X', rockGt.dat(tRock));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.PICKAXE    , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF    , "XXX", " S "       , 'S', tHandle[1], 'X', rockGt.dat(tRock));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CLUB       , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF_MIR, " XX", "XXX", "SX ", 'S', tHandle[1], 'X', rockGt.dat(tRock));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.HARDHAMMER , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF_MIR, "XX ", "XXS", "XX ", 'S', tHandle[1], 'X', rockGt.dat(tRock));
		}
		for (OreDictMaterial tRock : ANY.Stone.mToThis) {
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.AXE        , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF_MIR, "XX" , "XS"        , 'S', tHandle[1], 'X', rockGt.dat(tRock));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.HOE        , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF_MIR, "XX" , " S"        , 'S', tHandle[1], 'X', rockGt.dat(tRock));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.SHOVEL     , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF    ,  "X" ,  "S"        , 'S', tHandle[1], 'X', rockGt.dat(tRock));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.PICKAXE    , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF    , "XXX", " S "       , 'S', tHandle[1], 'X', rockGt.dat(tRock));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.CLUB       , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF_MIR, " XX", "XXX", "SX ", 'S', tHandle[1], 'X', rockGt.dat(tRock));
		CR.shaped(ToolsGT.sMetaTool.getToolWithStats(ToolsGT.HARDHAMMER , tRock    , (OreDictMaterial)tHandle[0]), CR.DEF_MIR, "XX ", "XXS", "XX ", 'S', tHandle[1], 'X', rockGt.dat(tRock));
		}
		
		// End of Tool Handle Loop
		}
		
		final String tCategory = ConfigCategories.Recipes.gregtechtools + ".";
		
		toolHeadBuilderwand        .addListener(new OreProcessing_Tool(BUILDERWAND           , tCategory + "Builderwand"         ,T,F,0,0, null, null                                                                                , new String[][] {{" P ", "f h", " s "}, {" C ", "f h", " s "}, {" G ", "f h", " s "}}, null         , null, null, null                              , null, new And(ANTIMATTER.NOT)));
		toolHeadConstructionPickaxe.addListener(new OreProcessing_Tool(CONSTRUCTION_PICK     , tCategory + "ConstructionPickaxe" ,T,F,0,0, null, null                                                                                , new String[][] {{"PIP", "f h"       }, {"CGC", "f  "       }                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT)));
		toolHeadPickaxe            .addListener(new OreProcessing_Tool(PICKAXE               , tCategory + "Pickaxe"             ,T,F,0,0, null, null                                                                                , new String[][] {{"PII", "f h"       }, {"CGG", "f  "       }                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT)));
		toolHeadShovel             .addListener(new OreProcessing_Tool(SHOVEL                , tCategory + "Shovel"              ,T,F,0,0, null, null                                                                                , new String[][] {{"fPh"              }, {"fC "              }                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT)));
		toolHeadSpade              .addListener(new OreProcessing_Tool(SPADE                 , tCategory + "Spade"               ,T,F,0,0, null, null                                                                                , new String[][] {{"fPh", " s "       }, {"fC ", " s "       }                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT)));
		toolHeadUniversalSpade     .addListener(new OreProcessing_Tool(UNIVERSALSPADE        , tCategory + "UniversalSpade"      ,F,F,0,0, null, new String[][] {{ "AT",  "Sd"       }}                                              , null                                                                                , null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, typemin(2))));
		toolHeadHoe                .addListener(new OreProcessing_Tool(HOE                   , tCategory + "Hoe"                 ,T,F,0,0, null, null                                                                                , new String[][] {{"PIh", "f  "       }, {"CG ", "f  "       }                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT)));
		toolHeadAxe                .addListener(new OreProcessing_Tool(AXE                   , tCategory + "Axe"                 ,T,F,0,0, null, null                                                                                , new String[][] {{"PIh", "P  ", "f  "}, {"CG ", "C  ", "f  "}                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT)));
		toolHeadAxeDouble          .addListener(new OreProcessing_Tool(DOUBLE_AXE            , tCategory + "DoubleAxe"           ,T,F,0,0, null, null                                                                                , new String[][] {{"PIP", "P P", "f h"}, {"CGC", "C C", "f  "}                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT, typemin(2))));
		toolHeadSense              .addListener(new OreProcessing_Tool(SENSE                 , tCategory + "Sense"               ,T,F,0,0, null, null                                                                                , new String[][] {{"PPI", "f h"       }, {"CCG", "f  "       }                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT, typemin(2))));
		toolHeadPlow               .addListener(new OreProcessing_Tool(PLOW                  , tCategory + "Plow"                ,T,F,0,0, null, null                                                                                , new String[][] {{"PPP", "PPP", "f h"}, {"CCC", "CCC", "f  "}                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT, typemin(2))));
		toolHeadFile               .addListener(new OreProcessing_Tool(FILE                  , tCategory + "File"                ,T,F,0,0, null, null                                                                                , new String[][] {{" P ", " Pk"       }}                                              , null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT, typemin(2), qualmax(2))));
		toolHeadChisel             .addListener(new OreProcessing_Tool(CHISEL                , tCategory + "Chisel"              ,T,F,0,0, null, null                                                                                , new String[][] {{"hPf", " S "       }, {"Cf" , "S "        }                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT, typemin(2))));
		toolHeadScrewdriver        .addListener(new OreProcessing_Tool(SCREWDRIVER           , tCategory + "Screwdriver"         ,T,F,0,0, null, null                                                                                , new String[][] {{"hS" , "Sf"        }}                                              , null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT, typemin(2))));
		toolHeadSaw                .addListener(new OreProcessing_Tool(SAW                   , tCategory + "Saw"                 ,T,F,0,0, null, null                                                                                , new String[][] {{"PP" , "fh"        }, {"CC" , "f "        }                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT, typemin(2))));
		toolHeadChainsaw           .addListener(new OreProcessing_Tool(CHAINSAW_LV           , tCategory + "Chainsaw"            ,F,F,0,0, null, null                                                                                , new String[][] {{"WVW", "XhX", "WVW"}}                                              , chain        , null, null, ring.dat(ANY.Steel),plate.dat(ANY.Steel), new And(ANTIMATTER.NOT, MT.Wood.NOT, typemin(2))));
		toolHeadDrill              .addListener(new OreProcessing_Tool(MININGDRILL_LV        , tCategory + "MiningDrill"         ,F,F,0,0, null, null                                                                                , new String[][] {{"PVP", "PVP", "VhV"}, {"CVC", "CVC", "VhV"}                       }, null         , null, null, plateCurved.dat(ANY.Steel)        , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, typemin(2))));
		toolHeadWrench             .addListener(new OreProcessing_Tool(WRENCH                , tCategory + "Wrench"              ,F,F,0,0, null, new String[][] {{"PhP", "PPP", " P "}, {"CfC", "CCC", " C "}}                       , new String[][] {{"hPW", "PVP", "WPd"}, {"hCW", "CVC", "WCd"}                       }, null         , null, null, ring.dat(ANY.Steel),screw.dat(ANY.Steel), new And(ANTIMATTER.NOT, MT.Wood.NOT, typemin(2), qualmin(1))));
		toolHeadWrench             .addListener(new OreProcessing_Tool(MONKEY_WRENCH         , tCategory + "MonkeyWrench"        ,F,F,0,0, null, new String[][] {{"PPd", "hPP", "PPT"}, {"CCd", "fCC", "CCT"}}                       , null                                                                                , null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, typemin(2), qualmin(1))));
		toolHeadWrench             .addListener(new OreProcessing_Tool(BENDING_CYLINDER      , tCategory + "BendingCylinder"     ,F,F,0,0, null, new String[][] {{"sfh", "III", "III"}}                                              , null                                                                                , null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, typemin(2))));
		toolHeadWrench             .addListener(new OreProcessing_Tool(BENDING_CYLINDER_SMALL, tCategory + "BendingCylinderSmall",F,F,0,0, null, new String[][] {{"sfh", "III", "   "}}                                              , null                                                                                , null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, typemin(2))));
		toolHeadWrench             .addListener(new OreProcessing_Tool(CROWBAR               , tCategory + "Crowbar"             ,F,F,0,0, null, new String[][] {{"hVS", "VSV", "SVf"}}                                              , null                                                                                , null         , null, null, DYE_OREDICTS[DYE_INDEX_Blue]      , null, new And(ANTIMATTER.NOT, MT.Wood.NOT)));
		toolHeadWrench             .addListener(new OreProcessing_Tool(PLUNGER               , tCategory + "Plunger"             ,F,F,0,0, null, new String[][] {{"xVV", " SV", "S f"}}                                              , null                                                                                , null         , null, null, plate.dat(MT.Rubber)              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT)));
		toolHeadWrench             .addListener(new OreProcessing_Tool(PINCERS               , tCategory + "Pincers"             ,F,F,0,0, null, new String[][] {{"XhX", " T ", "SdS"}}                                              , null                                                                                , plateCurved  , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, typemin(2))));
		toolHeadPickaxe            .addListener(new OreProcessing_Tool(SCOOP                 , tCategory + "Scoop"               ,F,F,0,0, null, new String[][] {{"SVS", "SSS", "xSh"}}                                              , null                                                                                , null         , null, null, Blocks.wool                       , null, new And(ANTIMATTER.NOT, MT.Wood.NOT)));
		toolHeadSword              .addListener(new OreProcessing_Tool(SWORD                 , tCategory + "Sword"               ,T,F,0,0, null, null                                                                                , new String[][] {{" P ", "fPh"       }, {" C ", "fC "       }                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT)));
		toolHeadSword              .addListener(new OreProcessing_Tool(KNIFE                 , tCategory + "Knife"               ,F,F,0,0, null, new String[][] {{"fP" , "hH"        }, {"fC" , "hH"        }}                       , null                                                                                , null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT)));
		toolHeadSword              .addListener(new OreProcessing_Tool(BUTCHERYKNIFE         , tCategory + "ButcheryKnife"       ,F,F,0,0, null, new String[][] {{"fPP", "hPP", "  H"}, {"fCC", " CC", "  H"}}                       , null                                                                                , null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, BOUNCY.NOT, STRETCHY.NOT, typemin(2))));
		toolHeadSword              .addListener(new OreProcessing_Tool(WIRECUTTER            , tCategory + "WireCutter"          ,F,F,0,0, null, new String[][] {{"PfP", "hPd", "STS"}, {"CfC", "hCd", "STS"}}                       , null                                                                                , null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, BOUNCY.NOT, STRETCHY.NOT, typemin(2))));
		toolHeadSword              .addListener(new OreProcessing_Tool(BRANCHCUTTER          , tCategory + "BranchCutter"        ,F,F,0,0, null, new String[][] {{"PfP", "PdP", "STS"}, {"CfC", "CdC", "STS"}}                       , null                                                                                , null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, BOUNCY.NOT, STRETCHY.NOT, typemin(2))));
		toolHeadSword              .addListener(new OreProcessing_Tool(SCISSORS              , tCategory + "Scissors"            ,F,F,0,0, null, new String[][] {{"PfP", " T ", "OdO"}, {"CfC", " T ", "OdO"}}                       , null                                                                                , null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, BOUNCY.NOT, STRETCHY.NOT, typemin(2))));
		toolHeadHammer             .addListener(new OreProcessing_Tool(HARDHAMMER            , tCategory + "HardHammer"          ,T,F,0,0, null, null                                                                                , new String[][] {{"II ", "IIh", "II "}, {"GG ", "GGf", "GG "}                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT, new Nor(WOOD, BOUNCY, STRETCHY))));
		toolHeadHammer             .addListener(new OreProcessing_Tool(SOFTHAMMER            , tCategory + "SoftHammer"          ,T,F,0,0, null, null                                                                                , new String[][] {{"II ", "IIr", "II "}, {"GG ", "GGr", "GG "}                       }, null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, COATED.NOT, new Or (WOOD, BOUNCY, STRETCHY))));
		toolHeadHammer             .addListener(new OreProcessing_Tool(CLUB                  , tCategory + "Club"                ,T,F,0,0, null, new String[][] {{" II", "III", "HI "}, {" GG", "GGG", "HG "}, {" RR", "RRR", "HR "}}, null                                                                                , null         , null, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT)));
		toolHeadArrow              .addListener(new OreProcessing_Tool(HAND_DRILL            , tCategory + "HandDrill"           ,T,F,0,0, null, new String[][] {{"  X", "HYH", "YH "}}                                              , null                                                                                , toolHeadArrow, bolt, null, null                              , null, new And(ANTIMATTER.NOT, MT.Wood.NOT, WOOD.NOT, BOUNCY.NOT, STRETCHY.NOT, typemin(2), qualmin(2))));
		
		GameRegistry.addRecipe(new AdvancedCraftingTool(MAGNIFYING_GLASS , lens                       , typemin(1), MT.Glass));
		GameRegistry.addRecipe(new AdvancedCraftingTool(HARDHAMMER       , toolHeadHammer             , new Nor(WOOD, BOUNCY, STRETCHY)));
		GameRegistry.addRecipe(new AdvancedCraftingTool(SOFTHAMMER       , toolHeadHammer             , new Or (WOOD, BOUNCY, STRETCHY), MT.Rubber));
		GameRegistry.addRecipe(new AdvancedCraftingTool(SWORD            , toolHeadSword              ));
		GameRegistry.addRecipe(new AdvancedCraftingTool(BUILDERWAND      , toolHeadBuilderwand        , MT.YellowSapphire)); // Suggests using Gems for those Wands since you usually only have one workable Gem per Type.
		GameRegistry.addRecipe(new AdvancedCraftingTool(CONSTRUCTION_PICK, toolHeadConstructionPickaxe));
		GameRegistry.addRecipe(new AdvancedCraftingTool(GEM_PICK         , toolHeadPickaxeGem         , MT.Amber)); // Amber, to show the Silk Touch usage first.
		GameRegistry.addRecipe(new AdvancedCraftingTool(PICKAXE          , toolHeadPickaxe            , MT.Bronze)); // Suggests Bronze for early Tools.
		GameRegistry.addRecipe(new AdvancedCraftingTool(SHOVEL           , toolHeadShovel             , MT.Bronze)); // Suggests Bronze for early Tools.
		GameRegistry.addRecipe(new AdvancedCraftingTool(SPADE            , toolHeadSpade              ));
		GameRegistry.addRecipe(new AdvancedCraftingTool(AXE              , toolHeadAxe                , MT.Bronze)); // Suggests Bronze for early Tools.
		GameRegistry.addRecipe(new AdvancedCraftingTool(DOUBLE_AXE       , toolHeadAxeDouble          ));
		GameRegistry.addRecipe(new AdvancedCraftingTool(HOE              , toolHeadHoe                , MT.WOODS.Birch)); // Suggests Wood for Hoes. Surely no particular reason to chose Birch there.
		GameRegistry.addRecipe(new AdvancedCraftingTool(SENSE            , toolHeadSense              ));
		GameRegistry.addRecipe(new AdvancedCraftingTool(PLOW             , toolHeadPlow               , MT.WOODS.Spruce)); // Suggests Wood for Plows.
		GameRegistry.addRecipe(new AdvancedCraftingTool(FILE             , toolHeadFile               , MT.Pb)); // Lead, to make it easier to see for Beginners.
		GameRegistry.addRecipe(new AdvancedCraftingTool(CHISEL           , toolHeadChisel             , MT.Pb)); // Lead, to make it easier to see for Beginners.
		GameRegistry.addRecipe(new AdvancedCraftingTool(SCREWDRIVER      , toolHeadScrewdriver        ));
		GameRegistry.addRecipe(new AdvancedCraftingTool(SAW              , toolHeadSaw                ));
		
		ICondition tCondition = new And(ANTIMATTER.NOT, WOOD.NOT, BOUNCY.NOT, STRETCHY.NOT, typemin(3), qualmin(1));
		
		toolHeadScrewdriver.addListener(new OreProcessing_Tool(POCKET_MULTITOOL    , tCategory + "PocketMultitool",F,F, 0,   0, MT.Blue  , new String[][] {{"AXO", "ZPV", "OWY"}}, null, toolHeadSaw, toolHeadChisel, toolHeadFile, toolHeadSword, toolHeadSword, tCondition));
		
		for (ItemStack tBattery : OreDictManager.getOres("gt:re-battery1", F)) {
		toolHeadDrill      .addListener(new OreProcessing_Tool(MIXER_LV            , tCategory + "MixerLV"        ,F,T,-1,V[1], MT.Orange, new String[][] {{"SSY", "SXW", "hVZ"}}, null, plateCurved.dat(MT.DATA.Electric_T[1]), ring .dat(MT.DATA.Electric_T[1]), plate    .dat(MT.DATA.Electric_T[1]), tBattery, IL.MOTORS [1], tCondition));
		toolHeadDrill      .addListener(new OreProcessing_Tool(DRILL_LV            , tCategory + "DrillLV"        ,F,T,-1,V[1], MT.Orange, new String[][] {{"fSY", "TXW", "dVZ"}}, null, plateCurved.dat(MT.DATA.Electric_T[1]), ring .dat(MT.DATA.Electric_T[1]), plate    .dat(MT.DATA.Electric_T[1]), tBattery, IL.MOTORS [1], tCondition));
		toolHeadScrewdriver.addListener(new OreProcessing_Tool(SCREWDRIVER_LV      , tCategory + "ScrewdriverLV"  ,F,T,-1,V[1], MT.Orange, new String[][] {{"XdA", "TWY", "VYX"}}, null, plateCurved.dat(MT.DATA.Electric_T[1]), ring .dat(MT.DATA.Electric_T[1]), null                                , tBattery, IL.MOTORS [1], tCondition));
		toolHeadBuzzSaw    .addListener(new OreProcessing_Tool(BUZZSAW_LV          , tCategory + "BuzzSawLV"      ,F,T,-1,V[1], MT.Orange, new String[][] {{"YXV", "TWX", "AdY"}}, null, plateCurved.dat(MT.DATA.Electric_T[1]), plate.dat(MT.DATA.Electric_T[1]), null                                , tBattery, IL.MOTORS [1], tCondition));
		toolHeadSword      .addListener(new OreProcessing_Tool(TRIMMER_LV          , tCategory + "TrimmerLV"      ,F,T,-1,V[1], MT.Orange, new String[][] {{"XAT", "ZYA", "VWd"}}, null, plateCurved.dat(MT.DATA.Electric_T[1]), ring .dat(MT.DATA.Electric_T[1]), stickLong.dat(MT.DATA.Electric_T[1]), tBattery, IL.PISTONS[1], tCondition));
		
		toolHeadWrench     .addListener(new OreProcessing_Tool(WRENCH_LV           , tCategory + "WrenchLV"       ,F,T,-1,V[1], MT.Orange, new String[][] {{"dAT", "XWX", "XVX"}}, null, plateCurved.dat(MT.DATA.Electric_T[1]), null, null, tBattery, IL.MOTORS[1], tCondition));
		toolHeadDrill      .addListener(new OreProcessing_Tool(MININGDRILL_LV      , tCategory + "MiningDrillLV"  ,F,T,-1,V[1], MT.Orange, new String[][] {{"dAT", "XWX", "XVX"}}, null, plateCurved.dat(MT.DATA.Electric_T[1]), null, null, tBattery, IL.MOTORS[1], tCondition));
		toolHeadChainsaw   .addListener(new OreProcessing_Tool(CHAINSAW_LV         , tCategory + "ChainsawLV"     ,F,T,-1,V[1], MT.Orange, new String[][] {{"dAT", "XWX", "XVX"}}, null, plateCurved.dat(MT.DATA.Electric_T[1]), null, null, tBattery, IL.MOTORS[1], tCondition));
		}
		for (ItemStack tBattery : OreDictManager.getOres("gt:re-battery2", F)) {
		toolHeadWrench     .addListener(new OreProcessing_Tool(WRENCH_MV           , tCategory + "WrenchMV"       ,F,T,-1,V[2], MT.Red   , new String[][] {{"dAT", "XWX", "XVX"}}, null, plateCurved.dat(MT.DATA.Electric_T[2]), null, null, tBattery, IL.MOTORS[2], tCondition));
		toolHeadDrill      .addListener(new OreProcessing_Tool(MININGDRILL_MV      , tCategory + "MiningDrillMV"  ,F,T,-1,V[2], MT.Red   , new String[][] {{"dAT", "XWX", "XVX"}}, null, plateCurved.dat(MT.DATA.Electric_T[2]), null, null, tBattery, IL.MOTORS[2], tCondition));
		toolHeadChainsaw   .addListener(new OreProcessing_Tool(CHAINSAW_MV         , tCategory + "ChainsawMV"     ,F,T,-1,V[2], MT.Red   , new String[][] {{"dAT", "XWX", "XVX"}}, null, plateCurved.dat(MT.DATA.Electric_T[2]), null, null, tBattery, IL.MOTORS[2], tCondition));
		}
		for (ItemStack tBattery : OreDictManager.getOres("gt:re-battery3", F)) {
		toolHeadWrench     .addListener(new OreProcessing_Tool(WRENCH_HV           , tCategory + "WrenchHV"       ,F,T,-1,V[3], MT.Blue  , new String[][] {{"dAT", "XWX", "XVX"}}, null, plateCurved.dat(MT.DATA.Electric_T[3]), null, null, tBattery, IL.MOTORS[3], tCondition));
		toolHeadDrill      .addListener(new OreProcessing_Tool(MININGDRILL_HV      , tCategory + "MiningDrillHV"  ,F,T,-1,V[3], MT.Blue  , new String[][] {{"dAT", "XWX", "XVX"}}, null, plateCurved.dat(MT.DATA.Electric_T[3]), null, null, tBattery, IL.MOTORS[3], tCondition));
		toolHeadChainsaw   .addListener(new OreProcessing_Tool(CHAINSAW_HV         , tCategory + "ChainsawHV"     ,F,T,-1,V[3], MT.Blue  , new String[][] {{"dAT", "XWX", "XVX"}}, null, plateCurved.dat(MT.DATA.Electric_T[3]), null, null, tBattery, IL.MOTORS[3], tCondition));
		
		toolHeadDrill      .addListener(new OreProcessing_Tool(JACKHAMMER_HV_Normal, tCategory + "JackhammerHV"   ,F,T,-1,V[3], MT.Blue  , new String[][] {{"SVS", "XWX", "YSY"}}, null, plateCurved.dat(MT.DATA.Electric_T[3]), spring.dat(MT.DATA.Electric_T[3]), null, tBattery, IL.PISTONS[3], tCondition));
		}
		}});
	}
	
	public static class OreProcessing_Tool implements IOreDictListenerEvent {
		private final ICondition<OreDictMaterial> mCondition;
		private final String[][] mToolRecipes, mToolHeadRecipes;
		private final String mCategoryName;
		private final Object mSpecialObjectV, mSpecialObjectW, mSpecialObjectX, mSpecialObjectY, mSpecialObjectZ;
		private final int mToolID;
		private final long mCapacity, mVoltage;
		private final boolean mUseNormalHandle, mDismantleable;
		private final OreDictMaterial mHandleOverride;
		
		/** 
		 * I = ingot
		 * P = plate
		 * G = gem
		 * B = plateCurved
		 * C = plateGem
		 * T = screw
		 * O = ring
		 * R = stone
		 * S = stick
		 * H = stick, made of Handle Material
		 * A = the tool head
		 * XYZVW
		 */
		public OreProcessing_Tool(int aToolID, String aCategoryName, boolean aUseNormalHandle, boolean aDismantleable, long aCapacity, long aVoltage, OreDictMaterial aHandleOverride, String[][] aToolRecipes, String[][] aToolHeadRecipes, Object aSpecialObjectX, Object aSpecialObjectY, Object aSpecialobjectZ, Object aSpecialObjectV, Object aSpecialObjectW, ICondition<OreDictMaterial> aCondition) {
			mSpecialObjectV = aSpecialObjectV;
			mSpecialObjectW = aSpecialObjectW;
			mSpecialObjectX = aSpecialObjectX;
			mSpecialObjectY = aSpecialObjectY;
			mSpecialObjectZ = aSpecialobjectZ;
			mToolHeadRecipes = aToolHeadRecipes;
			mToolRecipes = aToolRecipes;
			mCondition = aCondition;
			mToolID = aToolID;
			mCategoryName = aCategoryName;
			mHandleOverride = aHandleOverride;
			mUseNormalHandle = aUseNormalHandle;
			mDismantleable = aDismantleable;
			mCapacity = aCapacity;
			mVoltage = aVoltage;
		}
		
		@Override
		public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (aEvent.mNotAlreadyRegisteredName && aEvent.mMaterial.mToolTypes > 0 && mCondition.isTrue(aEvent.mMaterial)) {
				long tCapacity = mCapacity;
				if (tCapacity < 0) {
					tCapacity = 0;
					if (mSpecialObjectV instanceof IItemContainer) {
						ItemStack tBattery = ((IItemContainer)mSpecialObjectV).get(1);
						if (tBattery != null && tBattery.getItem() instanceof IItemEnergy) {
							tCapacity += ((IItemEnergy)tBattery.getItem()).getEnergyCapacity(TD.Energy.EU, tBattery);
						}
					} else if (mSpecialObjectV instanceof ItemStack) {
						if (((ItemStack)mSpecialObjectV).getItem() instanceof IItemEnergy) {
							tCapacity += ((IItemEnergy)(((ItemStack)mSpecialObjectV).getItem())).getEnergyCapacity(TD.Energy.EU, (ItemStack)mSpecialObjectV);
						}
					}
					if (mSpecialObjectW instanceof IItemContainer) {
						ItemStack tBattery = ((IItemContainer)mSpecialObjectW).get(1);
						if (tBattery != null && tBattery.getItem() instanceof IItemEnergy) {
							tCapacity += ((IItemEnergy)tBattery.getItem()).getEnergyCapacity(TD.Energy.EU, tBattery);
						}
					} else if (mSpecialObjectW instanceof ItemStack) {
						if (((ItemStack)mSpecialObjectW).getItem() instanceof IItemEnergy) {
							tCapacity += ((IItemEnergy)(((ItemStack)mSpecialObjectW).getItem())).getEnergyCapacity(TD.Energy.EU, (ItemStack)mSpecialObjectW);
						}
					}
				}
				ItemStack tTool = sMetaTool.getToolWithStats(mToolID, 1, aEvent.mMaterial, mHandleOverride==null?mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial:mHandleOverride, tCapacity, mVoltage), tStack = aEvent.mPrefix.mat(aEvent.mMaterial, 1);
				if (tTool != null) {
					if (mToolRecipes != null && mToolRecipes.length > 0) {
						for (int i = 0; i < mToolRecipes.length; i++) if (mToolRecipes[i] != null && mToolRecipes[i].length > 0 && (mCategoryName == null || ConfigsGT.RECIPES.get(mCategoryName + ".toolrecipes."+i, aEvent.mMaterial.mNameInternal, T))) {
								 if (mToolRecipes[i].length == 1)       CR.shaped(tTool, CR.DEF_NCC | (mDismantleable?CR.DISMANTLE:0), new Object[] {mToolRecipes[i][0]                                                     
								, 'G', gem.dat(aEvent.mMaterial)
								, 'I', aEvent.mMaterial==MT.Wood?OD.plankWood:ingot.dat(aEvent.mMaterial)
								, 'P', aEvent.mMaterial==MT.Wood?OD.plankWood:plate.dat(aEvent.mMaterial)
								, 'B', plateCurved.dat(aEvent.mMaterial)
								, 'C', plateGem.dat(aEvent.mMaterial)
								, 'S', stick.dat(aEvent.mMaterial)
								, 'R', aEvent.mMaterial==MT.Stone?ST.make(Blocks.stone, 1, W):stone.dat(aEvent.mMaterial)
								, 'T', screw.dat(aEvent.mMaterial)
								, 'O', ring.dat(aEvent.mMaterial)
								, 'N', nugget.dat(aEvent.mMaterial)
								, 'H', stick.dat(mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial)
								, 'A', aEvent.mPrefix.dat(aEvent.mMaterial)
								, 'V', mSpecialObjectV instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectV).dat(aEvent.mMaterial) : mSpecialObjectV==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectV
								, 'W', mSpecialObjectW instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectW).dat(aEvent.mMaterial) : mSpecialObjectW==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectW
								, 'X', mSpecialObjectX instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectX).dat(aEvent.mMaterial) : mSpecialObjectX==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectX
								, 'Y', mSpecialObjectY instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectY).dat(aEvent.mMaterial) : mSpecialObjectY==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectY
								, 'Z', mSpecialObjectZ instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectZ).dat(aEvent.mMaterial) : mSpecialObjectZ==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectZ
								});
							else if (mToolRecipes[i].length == 2)       CR.shaped(tTool, CR.DEF_NCC | (mDismantleable?CR.DISMANTLE:0), new Object[] {mToolRecipes[i][0], mToolRecipes[i][1]
								, 'G', gem.dat(aEvent.mMaterial)
								, 'I', aEvent.mMaterial==MT.Wood?OD.plankWood:ingot.dat(aEvent.mMaterial)
								, 'P', aEvent.mMaterial==MT.Wood?OD.plankWood:plate.dat(aEvent.mMaterial)
								, 'B', plateCurved.dat(aEvent.mMaterial)
								, 'C', plateGem.dat(aEvent.mMaterial)
								, 'S', stick.dat(aEvent.mMaterial)
								, 'R', aEvent.mMaterial==MT.Stone?ST.make(Blocks.stone, 1, W):stone.dat(aEvent.mMaterial)
								, 'T', screw.dat(aEvent.mMaterial)
								, 'O', ring.dat(aEvent.mMaterial)
								, 'N', nugget.dat(aEvent.mMaterial)
								, 'H', stick.dat(mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial)
								, 'A', aEvent.mPrefix.dat(aEvent.mMaterial)
								, 'V', mSpecialObjectV instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectV).dat(aEvent.mMaterial) : mSpecialObjectV==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectV
								, 'W', mSpecialObjectW instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectW).dat(aEvent.mMaterial) : mSpecialObjectW==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectW
								, 'X', mSpecialObjectX instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectX).dat(aEvent.mMaterial) : mSpecialObjectX==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectX
								, 'Y', mSpecialObjectY instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectY).dat(aEvent.mMaterial) : mSpecialObjectY==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectY
								, 'Z', mSpecialObjectZ instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectZ).dat(aEvent.mMaterial) : mSpecialObjectZ==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectZ
								});
							else                                        CR.shaped(tTool, CR.DEF_NCC | (mDismantleable?CR.DISMANTLE:0), new Object[] {mToolRecipes[i][0], mToolRecipes[i][1], mToolRecipes[i][2]
								, 'G', gem.dat(aEvent.mMaterial)
								, 'I', aEvent.mMaterial==MT.Wood?OD.plankWood:ingot.dat(aEvent.mMaterial)
								, 'P', aEvent.mMaterial==MT.Wood?OD.plankWood:plate.dat(aEvent.mMaterial)
								, 'B', plateCurved.dat(aEvent.mMaterial)
								, 'C', plateGem.dat(aEvent.mMaterial)
								, 'S', stick.dat(aEvent.mMaterial)
								, 'R', aEvent.mMaterial==MT.Stone?ST.make(Blocks.stone, 1, W):stone.dat(aEvent.mMaterial)
								, 'T', screw.dat(aEvent.mMaterial)
								, 'O', ring.dat(aEvent.mMaterial)
								, 'N', nugget.dat(aEvent.mMaterial)
								, 'H', stick.dat(mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial)
								, 'A', aEvent.mPrefix.dat(aEvent.mMaterial)
								, 'V', mSpecialObjectV instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectV).dat(aEvent.mMaterial) : mSpecialObjectV==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectV
								, 'W', mSpecialObjectW instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectW).dat(aEvent.mMaterial) : mSpecialObjectW==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectW
								, 'X', mSpecialObjectX instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectX).dat(aEvent.mMaterial) : mSpecialObjectX==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectX
								, 'Y', mSpecialObjectY instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectY).dat(aEvent.mMaterial) : mSpecialObjectY==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectY
								, 'Z', mSpecialObjectZ instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectZ).dat(aEvent.mMaterial) : mSpecialObjectZ==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectZ
								});
						}
					}
					if (mToolHeadRecipes != null && mToolHeadRecipes.length > 0) {
						for (int i = 0; i < mToolHeadRecipes.length; i++) if (mToolHeadRecipes[i] != null && mToolHeadRecipes[i].length > 0 && (mCategoryName == null || ConfigsGT.RECIPES.get(mCategoryName + ".toolheadrecipe."+i, aEvent.mMaterial.mNameInternal, T))) {
								 if (mToolHeadRecipes[i].length == 1)   CR.shaped(tStack, CR.DEF_NCC | (mDismantleable?CR.DISMANTLE:0), new Object[] {mToolHeadRecipes[i][0]
								, 'G', gem.dat(aEvent.mMaterial)
								, 'I', aEvent.mMaterial==MT.Wood?OD.plankWood:ingot.dat(aEvent.mMaterial)
								, 'P', aEvent.mMaterial==MT.Wood?OD.plankWood:plate.dat(aEvent.mMaterial)
								, 'B', plateCurved.dat(aEvent.mMaterial)
								, 'C', plateGem.dat(aEvent.mMaterial)
								, 'S', stick.dat(aEvent.mMaterial)
								, 'R', aEvent.mMaterial==MT.Stone?ST.make(Blocks.stone, 1, W):stone.dat(aEvent.mMaterial)
								, 'T', screw.dat(aEvent.mMaterial)
								, 'O', ring.dat(aEvent.mMaterial)
								, 'N', nugget.dat(aEvent.mMaterial)
								, 'H', stick.dat(mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial)
								, 'A', aEvent.mPrefix.dat(aEvent.mMaterial)
								, 'V', mSpecialObjectV instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectV).dat(aEvent.mMaterial) : mSpecialObjectV==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectV
								, 'W', mSpecialObjectW instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectW).dat(aEvent.mMaterial) : mSpecialObjectW==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectW
								, 'X', mSpecialObjectX instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectX).dat(aEvent.mMaterial) : mSpecialObjectX==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectX
								, 'Y', mSpecialObjectY instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectY).dat(aEvent.mMaterial) : mSpecialObjectY==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectY
								, 'Z', mSpecialObjectZ instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectZ).dat(aEvent.mMaterial) : mSpecialObjectZ==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectZ
								});
							else if (mToolHeadRecipes[i].length == 2)   CR.shaped(tStack, CR.DEF_NCC | (mDismantleable?CR.DISMANTLE:0), new Object[] {mToolHeadRecipes[i][0], mToolHeadRecipes[i][1]
								, 'G', gem.dat(aEvent.mMaterial)
								, 'I', aEvent.mMaterial==MT.Wood?OD.plankWood:ingot.dat(aEvent.mMaterial)
								, 'P', aEvent.mMaterial==MT.Wood?OD.plankWood:plate.dat(aEvent.mMaterial)
								, 'B', plateCurved.dat(aEvent.mMaterial)
								, 'C', plateGem.dat(aEvent.mMaterial)
								, 'S', stick.dat(aEvent.mMaterial)
								, 'R', aEvent.mMaterial==MT.Stone?ST.make(Blocks.stone, 1, W):stone.dat(aEvent.mMaterial)
								, 'T', screw.dat(aEvent.mMaterial)
								, 'O', ring.dat(aEvent.mMaterial)
								, 'N', nugget.dat(aEvent.mMaterial)
								, 'H', stick.dat(mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial)
								, 'A', aEvent.mPrefix.dat(aEvent.mMaterial)
								, 'V', mSpecialObjectV instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectV).dat(aEvent.mMaterial) : mSpecialObjectV==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectV
								, 'W', mSpecialObjectW instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectW).dat(aEvent.mMaterial) : mSpecialObjectW==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectW
								, 'X', mSpecialObjectX instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectX).dat(aEvent.mMaterial) : mSpecialObjectX==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectX
								, 'Y', mSpecialObjectY instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectY).dat(aEvent.mMaterial) : mSpecialObjectY==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectY
								, 'Z', mSpecialObjectZ instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectZ).dat(aEvent.mMaterial) : mSpecialObjectZ==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectZ
								});
							else                                        CR.shaped(tStack, CR.DEF_NCC | (mDismantleable?CR.DISMANTLE:0), new Object[] {mToolHeadRecipes[i][0], mToolHeadRecipes[i][1], mToolHeadRecipes[i][2]
								, 'G', gem.dat(aEvent.mMaterial)
								, 'I', aEvent.mMaterial==MT.Wood?OD.plankWood:ingot.dat(aEvent.mMaterial)
								, 'P', aEvent.mMaterial==MT.Wood?OD.plankWood:plate.dat(aEvent.mMaterial)
								, 'B', plateCurved.dat(aEvent.mMaterial)
								, 'C', plateGem.dat(aEvent.mMaterial)
								, 'S', stick.dat(aEvent.mMaterial)
								, 'R', aEvent.mMaterial==MT.Stone?ST.make(Blocks.stone, 1, W):stone.dat(aEvent.mMaterial)
								, 'T', screw.dat(aEvent.mMaterial)
								, 'O', ring.dat(aEvent.mMaterial)
								, 'N', nugget.dat(aEvent.mMaterial)
								, 'H', stick.dat(mUseNormalHandle?aEvent.mMaterial.mHandleMaterial:aEvent.mMaterial)
								, 'A', aEvent.mPrefix.dat(aEvent.mMaterial)
								, 'V', mSpecialObjectV instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectV).dat(aEvent.mMaterial) : mSpecialObjectV==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectV
								, 'W', mSpecialObjectW instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectW).dat(aEvent.mMaterial) : mSpecialObjectW==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectW
								, 'X', mSpecialObjectX instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectX).dat(aEvent.mMaterial) : mSpecialObjectX==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectX
								, 'Y', mSpecialObjectY instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectY).dat(aEvent.mMaterial) : mSpecialObjectY==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectY
								, 'Z', mSpecialObjectZ instanceof OreDictPrefix ? ((OreDictPrefix)mSpecialObjectZ).dat(aEvent.mMaterial) : mSpecialObjectZ==null ? plate.dat(aEvent.mMaterial) : mSpecialObjectZ
								});
						}
					}
				}
			}
		}
	}
}
