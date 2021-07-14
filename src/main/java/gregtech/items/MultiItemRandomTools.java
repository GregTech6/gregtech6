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

package gregtech.items;

import static gregapi.data.CS.*;
import static gregapi.data.OP.*;

import gregapi.GT_API;
import gregapi.code.IItemContainer;
import gregapi.cover.covers.CoverTextureCanvas;
import gregapi.data.*;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.BooksGT;
import gregapi.data.CS.GarbageGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.CS.OreDictToolNames;
import gregapi.data.CS.ToolsGT;
import gregapi.item.CreativeTab;
import gregapi.item.IItemRottable;
import gregapi.item.multiitem.MultiItem;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.item.multiitem.behaviors.Behavior_Bucket_Simple;
import gregapi.item.multiitem.behaviors.Behavior_PrintedPages;
import gregapi.item.multiitem.behaviors.Behavior_Switch_Metadata;
import gregapi.item.multiitem.behaviors.Behavior_Tool;
import gregapi.item.multiitem.behaviors.IBehavior;
import gregapi.item.multiitem.energy.EnergyStat;
import gregapi.item.multiitem.energy.EnergyStatDebug;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.BlockTextureDefault;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregtech.items.behaviors.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class MultiItemRandomTools extends MultiItemRandom implements IItemRottable {
	public MultiItemRandomTools() {
		super(MD.GT.mID, "gt.multiitem.randomtools");
		setCreativeTab(new CreativeTab(getUnlocalizedName(), "GregTech: Equipment", this, (short)5008));
	}
	
	@Override
	public void addItems() {
		IBehavior<MultiItem> tBehaviour;
		
		IL.Compound_Bronze       .set(addItem(  0, "Bronze Compound"        , "Put in Furnace to smelt it" , new OreDictItemData(MT.Bronze       , U9), TC.stack(TC.METALLUM, 1)));
		IL.Compound_Brass        .set(addItem(  1, "Brass Compound"         , "Put in Furnace to smelt it" , new OreDictItemData(MT.Brass        , U9), TC.stack(TC.METALLUM, 1)));
		IL.Compound_BismuthBronze.set(addItem(  2, "Bismuth Bronze Compound", "Put in Furnace to smelt it" , new OreDictItemData(MT.BismuthBronze, U9), TC.stack(TC.METALLUM, 1)));
		
		RM.add_smelting(IL.Compound_Bronze       .get(1), OP.nugget.mat(MT.Bronze       , 1), F, F, T);
		RM.add_smelting(IL.Compound_Brass        .get(1), OP.nugget.mat(MT.Brass        , 1), F, F, T);
		RM.add_smelting(IL.Compound_BismuthBronze.get(1), OP.nugget.mat(MT.BismuthBronze, 1), F, F, T);
		
		IL.Porcelain_Cup_Raw               .set(addItem(899, "Modeled Porcelain Cup"    , "Put in Furnace to harden", new OreDictItemData(MT.Porcelain, U), TC.stack(TC.TERRA, 2), TC.stack(TC.VACUOS, 1))); CR.shapeless(OP.dust.mat(MT.Porcelain, 1), CR.DEF_NCC, new Object[] {last()});
		
		CR.shaped(IL.Porcelain_Cup_Raw   .get(1), CR.DEF_NCC, "kPR"              , 'P', OP.dust.dat(MT.Porcelain), 'R', OreDictToolNames.rollingpin);
		
		IL.Ceramic_Ingot_Mold_Raw          .set(addItem(900, "Clay Ingot Mold"          , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Chunk_Mold_Raw          .set(addItem(901, "Clay Chunk Mold"          , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Plate_Mold_Raw          .set(addItem(902, "Clay Plate Mold"          , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Tiny_Plate_Mold_Raw     .set(addItem(903, "Clay Tiny Plate Mold"     , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Bolt_Mold_Raw           .set(addItem(904, "Clay Bolt Mold"           , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Rod_Mold_Raw            .set(addItem(905, "Clay Rod Mold"            , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Long_Rod_Mold_Raw       .set(addItem(906, "Clay Long Rod Mold"       , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Item_Casing_Mold_Raw    .set(addItem(907, "Clay Item Casing Mold"    , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Ring_Mold_Raw           .set(addItem(908, "Clay Ring Mold"           , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Gear_Mold_Raw           .set(addItem(909, "Clay Gear Mold"           , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Small_Gear_Mold_Raw     .set(addItem(910, "Clay Small Gear Mold"     , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Sword_Mold_Raw          .set(addItem(911, "Clay Sword Mold"          , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Pickaxe_Mold_Raw        .set(addItem(912, "Clay Pickaxe Mold"        , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Spade_Mold_Raw          .set(addItem(913, "Clay Spade Mold"          , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Shovel_Mold_Raw         .set(addItem(914, "Clay Shovel Mold"         , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Universal_Spade_Mold_Raw.set(addItem(915, "Clay Universal Spade Mold", "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Axe_Mold_Raw            .set(addItem(916, "Clay Axe Mold"            , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Double_Axe_Mold_Raw     .set(addItem(917, "Clay Double Axe Mold"     , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Saw_Mold_Raw            .set(addItem(918, "Clay Saw Mold"            , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Hammer_Mold_Raw         .set(addItem(919, "Clay Hammer Mold"         , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_File_Mold_Raw           .set(addItem(920, "Clay File Mold"           , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Screwdriver_Mold_Raw    .set(addItem(921, "Clay Screwdriver Mold"    , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Chisel_Mold_Raw         .set(addItem(922, "Clay Chisel Mold"         , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Arrow_Mold_Raw          .set(addItem(923, "Clay Arrow Mold"          , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Hoe_Mold_Raw            .set(addItem(924, "Clay Hoe Mold"            , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Sense_Mold_Raw          .set(addItem(925, "Clay Sense Mold"          , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Plow_Mold_Raw           .set(addItem(926, "Clay Plow Mold"           , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Builderwand_Mold_Raw    .set(addItem(927, "Clay Builder's Wand Mold" , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Nugget_Mold_Raw         .set(addItem(928, "Clay Nugget Mold"         , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		
		IL.Ceramic_Tap_Raw                 .set(addItem(987, "Clay Tap"                 , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*3), TC.stack(TC.TERRA, 2), TC.stack(TC.MOTUS     , 1))); CR.shapeless(ST.make(Items.clay_ball, 3, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Funnel_Raw              .set(addItem(988, "Clay Funnel"              , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*3), TC.stack(TC.TERRA, 2), TC.stack(TC.MOTUS     , 1))); CR.shapeless(ST.make(Items.clay_ball, 3, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Crucible_Raw            .set(addItem(989, "Clay Crucible"            , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*7), TC.stack(TC.TERRA, 2), TC.stack(TC.IGNIS     , 1))); CR.shapeless(ST.make(Items.clay_ball, 7, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Basin_Raw               .set(addItem(990, "Clay Basin"               , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Mold_Raw                .set(addItem(991, "Clay Mold"                , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.GELUM     , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Faucet_Raw              .set(addItem(992, "Clay Faucet"              , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*3), TC.stack(TC.TERRA, 2), TC.stack(TC.MOTUS     , 1))); CR.shapeless(ST.make(Items.clay_ball, 3, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Crossing_Raw            .set(addItem(993, "Clay Crossing"            , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.ITER      , 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Juicer_Raw                      .set(addItem(994, "Clay Juicer"              , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*4), TC.stack(TC.TERRA, 2), TC.stack(TC.LIMUS     , 1))); CR.shapeless(ST.make(Items.clay_ball, 4, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Bowl_Raw                .set(addItem(995, "Clay Bowl"                , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*5), TC.stack(TC.TERRA, 2), TC.stack(TC.PERMUTATIO, 1))); CR.shapeless(ST.make(Items.clay_ball, 5, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Ceramic_Jug_Raw                 .set(addItem(996, "Clay Jug"                 , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*6), TC.stack(TC.TERRA, 2), TC.stack(TC.VACUOS    , 1))); CR.shapeless(ST.make(Items.clay_ball, 6, 0), CR.DEF_NCC, new Object[] {last()});
		IL.Measuring_Pot_Raw               .set(addItem(997, "Clay Measuring Pot"       , "Put in Furnace to harden", new OreDictItemData(MT.Clay, U*4), TC.stack(TC.TERRA, 2), TC.stack(TC.VACUOS    , 1))); CR.shapeless(ST.make(Items.clay_ball, 4, 0), CR.DEF_NCC, new Object[] {last()});
		
		CR.shaped(IL.Ceramic_Tap_Raw     .get(1), CR.DEF_NCC, "CCR", "kC "       , 'C', OD.itemClay, 'R', OreDictToolNames.rollingpin);
		CR.shaped(IL.Ceramic_Funnel_Raw  .get(1), CR.DEF_NCC, "CRC", "kC "       , 'C', OD.itemClay, 'R', OreDictToolNames.rollingpin);
		CR.shaped(IL.Ceramic_Crucible_Raw.get(1), CR.DEF_NCC, "CkC", "CRC", "CCC", 'C', OD.itemClay, 'R', OreDictToolNames.rollingpin);
		CR.shaped(IL.Ceramic_Basin_Raw   .get(1), CR.DEF_NCC, "CkC", "CRC", " C ", 'C', OD.itemClay, 'R', OreDictToolNames.rollingpin);
		CR.shaped(IL.Ceramic_Mold_Raw    .get(1), CR.DEF_NCC, "C C", "CCC", "k R", 'C', OD.itemClay, 'R', OreDictToolNames.rollingpin);
		CR.shaped(IL.Ceramic_Faucet_Raw  .get(1), CR.DEF_NCC, "C C", "kCR"       , 'C', OD.itemClay, 'R', OreDictToolNames.rollingpin);
		CR.shaped(IL.Ceramic_Crossing_Raw.get(1), CR.DEF_NCC, "kCR", "CCC", " C ", 'C', OD.itemClay, 'R', OreDictToolNames.rollingpin);
		CR.shaped(IL.Juicer_Raw          .get(1), CR.DEF_NCC, "kCR", "CCC"       , 'C', OD.itemClay, 'R', OreDictToolNames.rollingpin);
		CR.shaped(IL.Ceramic_Bowl_Raw    .get(1), CR.DEF_NCC, "k R", "C C", "CCC", 'C', OD.itemClay, 'R', OreDictToolNames.rollingpin);
		CR.shaped(IL.Ceramic_Jug_Raw     .get(1), CR.DEF_NCC, "kCR", "C C", "CCC", 'C', OD.itemClay, 'R', OreDictToolNames.rollingpin);
		CR.shaped(IL.Measuring_Pot_Raw   .get(1), CR.DEF_NCC, "CkC", "CCR"       , 'C', OD.itemClay, 'R', OreDictToolNames.rollingpin);
		
		CR.shapeless(IL.Ceramic_Ingot_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, IL.BoP_Mud_Brick});
		CR.shapeless(IL.Ceramic_Ingot_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, IL.ERE_Mud_Brick});
		CR.shapeless(IL.Ceramic_Ingot_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.ingot.dat(MT.UNUSED.Mud)});
		CR.shapeless(IL.Ceramic_Ingot_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.ingot.dat(MT.Brick)});
		CR.shapeless(IL.Ceramic_Plate_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OD.paneGlass});
		CR.shapeless(IL.Ceramic_Plate_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OD.plankAnyWood});
		CR.shapeless(IL.Ceramic_Arrow_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OD.itemFlint});
		CR.shapeless(IL.Ceramic_Arrow_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, Items.arrow});
		CR.shapeless(IL.Ceramic_Sword_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, Items.wooden_sword});
		CR.shapeless(IL.Ceramic_Pickaxe_Mold_Raw        .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, Items.wooden_pickaxe});
		CR.shapeless(IL.Ceramic_Shovel_Mold_Raw         .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, Items.wooden_shovel});
		CR.shapeless(IL.Ceramic_Axe_Mold_Raw            .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, Items.wooden_axe});
		CR.shapeless(IL.Ceramic_Hoe_Mold_Raw            .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, Items.wooden_hoe});
		CR.shapeless(IL.Ceramic_Sword_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.SWORD)});
		CR.shapeless(IL.Ceramic_Pickaxe_Mold_Raw        .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.PICKAXE)});
		CR.shapeless(IL.Ceramic_Pickaxe_Mold_Raw        .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.GEM_PICK)});
		CR.shapeless(IL.Ceramic_Pickaxe_Mold_Raw        .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.CONSTRUCTION_PICK)});
		CR.shapeless(IL.Ceramic_Spade_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.SPADE)});
		CR.shapeless(IL.Ceramic_Shovel_Mold_Raw         .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.SHOVEL)});
		CR.shapeless(IL.Ceramic_Universal_Spade_Mold_Raw.get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.UNIVERSALSPADE)});
		CR.shapeless(IL.Ceramic_Axe_Mold_Raw            .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.AXE)});
		CR.shapeless(IL.Ceramic_Double_Axe_Mold_Raw     .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.DOUBLE_AXE)});
		CR.shapeless(IL.Ceramic_Saw_Mold_Raw            .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.SAW)});
		CR.shapeless(IL.Ceramic_Hammer_Mold_Raw         .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.SOFTHAMMER)});
		CR.shapeless(IL.Ceramic_Hammer_Mold_Raw         .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.HARDHAMMER)});
		CR.shapeless(IL.Ceramic_File_Mold_Raw           .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.FILE)});
		CR.shapeless(IL.Ceramic_Screwdriver_Mold_Raw    .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.SCREWDRIVER)});
		CR.shapeless(IL.Ceramic_Chisel_Mold_Raw         .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.CHISEL)});
		CR.shapeless(IL.Ceramic_Hoe_Mold_Raw            .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.HOE)});
		CR.shapeless(IL.Ceramic_Sense_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.SENSE)});
		CR.shapeless(IL.Ceramic_Plow_Mold_Raw           .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.PLOW)});
		CR.shapeless(IL.Ceramic_Builderwand_Mold_Raw    .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, ToolsGT.sMetaTool.make(ToolsGT.BUILDERWAND)});
		
		for (OreDictMaterial tMat : new OreDictMaterial[] {ANY.WoodPlastic, ANY.Stone, MT.Glass, ANY.Wax, ANY.Iron, ANY.Cu, MT.Sn, MT.Zn, MT.Pb, MT.Bi, MT.Brass, MT.Bronze, MT.BismuthBronze, MT.Au}) {
		CR.shapeless(IL.Ceramic_Ingot_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.ingot.dat(tMat)});
		CR.shapeless(IL.Ceramic_Chunk_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.chunkGt.dat(tMat)});
		CR.shapeless(IL.Ceramic_Nugget_Mold_Raw         .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.nugget.dat(tMat)});
		CR.shapeless(IL.Ceramic_Plate_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.plate.dat(tMat)});
		CR.shapeless(IL.Ceramic_Plate_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.plateGem.dat(tMat)});
		CR.shapeless(IL.Ceramic_Tiny_Plate_Mold_Raw     .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.plateTiny.dat(tMat)});
		CR.shapeless(IL.Ceramic_Tiny_Plate_Mold_Raw     .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.plateGemTiny.dat(tMat)});
		CR.shapeless(IL.Ceramic_Bolt_Mold_Raw           .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.bolt.dat(tMat)});
		CR.shapeless(IL.Ceramic_Rod_Mold_Raw            .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.stick.dat(tMat)});
		CR.shapeless(IL.Ceramic_Long_Rod_Mold_Raw       .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.stick.dat(tMat), OP.stick.dat(tMat)});
		CR.shapeless(IL.Ceramic_Long_Rod_Mold_Raw       .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.stickLong.dat(tMat)});
		CR.shapeless(IL.Ceramic_Item_Casing_Mold_Raw    .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.casingSmall.dat(tMat)});
		CR.shapeless(IL.Ceramic_Ring_Mold_Raw           .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.ring.dat(tMat)});
		CR.shapeless(IL.Ceramic_Gear_Mold_Raw           .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.gearGt.dat(tMat)});
		CR.shapeless(IL.Ceramic_Small_Gear_Mold_Raw     .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.gearGtSmall.dat(tMat)});
		CR.shapeless(IL.Ceramic_Sword_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadSword.dat(tMat)});
		CR.shapeless(IL.Ceramic_Sword_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawSword.dat(tMat)});
		CR.shapeless(IL.Ceramic_Pickaxe_Mold_Raw        .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadPickaxe.dat(tMat)});
		CR.shapeless(IL.Ceramic_Pickaxe_Mold_Raw        .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawPickaxe.dat(tMat)});
		CR.shapeless(IL.Ceramic_Spade_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadSpade.dat(tMat)});
		CR.shapeless(IL.Ceramic_Spade_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawSpade.dat(tMat)});
		CR.shapeless(IL.Ceramic_Shovel_Mold_Raw         .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadShovel.dat(tMat)});
		CR.shapeless(IL.Ceramic_Shovel_Mold_Raw         .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawShovel.dat(tMat)});
		CR.shapeless(IL.Ceramic_Universal_Spade_Mold_Raw.get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadUniversalSpade.dat(tMat)});
		CR.shapeless(IL.Ceramic_Universal_Spade_Mold_Raw.get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawUniversalSpade.dat(tMat)});
		CR.shapeless(IL.Ceramic_Axe_Mold_Raw            .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadAxe.dat(tMat)});
		CR.shapeless(IL.Ceramic_Axe_Mold_Raw            .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawAxe.dat(tMat)});
		CR.shapeless(IL.Ceramic_Double_Axe_Mold_Raw     .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadAxe.dat(tMat), OP.toolHeadAxe.dat(tMat)});
		CR.shapeless(IL.Ceramic_Double_Axe_Mold_Raw     .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawAxe.dat(tMat), OP.toolHeadRawAxe.dat(tMat)});
		CR.shapeless(IL.Ceramic_Double_Axe_Mold_Raw     .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadAxeDouble.dat(tMat)});
		CR.shapeless(IL.Ceramic_Double_Axe_Mold_Raw     .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawAxeDouble.dat(tMat)});
		CR.shapeless(IL.Ceramic_Saw_Mold_Raw            .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadSaw.dat(tMat)});
		CR.shapeless(IL.Ceramic_Saw_Mold_Raw            .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawSaw.dat(tMat)});
		CR.shapeless(IL.Ceramic_Hammer_Mold_Raw         .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadHammer.dat(tMat)});
		CR.shapeless(IL.Ceramic_File_Mold_Raw           .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadFile.dat(tMat)});
		CR.shapeless(IL.Ceramic_Screwdriver_Mold_Raw    .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadScrewdriver.dat(tMat)});
		CR.shapeless(IL.Ceramic_Chisel_Mold_Raw         .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadChisel.dat(tMat)});
		CR.shapeless(IL.Ceramic_Chisel_Mold_Raw         .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawChisel.dat(tMat)});
		CR.shapeless(IL.Ceramic_Arrow_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadArrow.dat(tMat)});
		CR.shapeless(IL.Ceramic_Arrow_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawArrow.dat(tMat)});
		CR.shapeless(IL.Ceramic_Hoe_Mold_Raw            .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadHoe.dat(tMat)});
		CR.shapeless(IL.Ceramic_Hoe_Mold_Raw            .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawHoe.dat(tMat)});
		CR.shapeless(IL.Ceramic_Sense_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadSense.dat(tMat)});
		CR.shapeless(IL.Ceramic_Sense_Mold_Raw          .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawSense.dat(tMat)});
		CR.shapeless(IL.Ceramic_Plow_Mold_Raw           .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadPlow.dat(tMat)});
		CR.shapeless(IL.Ceramic_Plow_Mold_Raw           .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadRawPlow.dat(tMat)});
		CR.shapeless(IL.Ceramic_Builderwand_Mold_Raw    .get(1), CR.DEF_NCC, new Object[] {IL.Ceramic_Mold_Raw, OP.toolHeadBuilderwand.dat(tMat)});
		}
		
		IL.Food_Can_Empty.set(addItem(998, "Empty Food Can" , "Used for canning Food" , new OreDictItemData(MT.TinAlloy, OP.plateCurved.mAmount), TC.stack(TC.VACUOS, 1), TC.stack(TC.FABRICO, 1)));
		IL.Spray_Empty   .set(addItem(999, "Empty Spray Can", "Used for making Sprays", new OreDictItemData(MT.Sn, OP.plateCurved.mAmount, MT.Redstone, OP.dust.mAmount), TC.stack(TC.VACUOS, 1), TC.stack(TC.MOTUS, 1)));
		
		RM.RollBender.addRecipe1(T, 16, 64, OP.plateCurved.mat(MT.TinAlloy, 1), IL.Food_Can_Empty.get(1));
// TODO RA.addAssemblerRecipe(OP.dust.mat(MT.Redstone, 1), OP.cell.mat(MT.Empty, 1), IL.Spray_Empty.get(1), 800, 1);
		CR.shaped(IL.Food_Can_Empty      .get(1), CR.DEF_NCC, "fh" , "oP"        , 'P', OP.plateCurved.dat(MT.TinAlloy));
		CR.shaped(IL.Spray_Empty         .get(1), CR.DEF_NCC, "Rf" , "Cs"        , 'R', OD.itemRedstone, 'C', OP.plateCurved.dat(MT.Sn));
		
		for (byte i = 0; i < 16; i++) {
		IL.SPRAY_CAN_DYES[i]               .set(addItem( 1000+2*i, "Spray Paint ("+DYE_NAMES[i]+")", "Full", TC.stack(TC.SENSUS, 4)));
		IL.SPRAY_CAN_DYES_USED[i]          .set(addItem(mLastID+1, "Spray Paint ("+DYE_NAMES[i]+")", "Used", TC.stack(TC.SENSUS, 3), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Color(IL.Spray_Empty.get(1), IL.SPRAY_CAN_DYES_USED[i].get(1), IL.SPRAY_CAN_DYES[i].get(1), 512, i);
		addItemBehavior(mLastID, tBehaviour); addItemBehavior(mLastID-1, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), FL.mul(DYE_FLUIDS_CHEMICAL[i], 16), NF, IL.SPRAY_CAN_DYES[i].get(1));
		ItemsGT.addNEIRedirects(IL.SPRAY_CAN_DYES_USED[i].get(1), IL.SPRAY_CAN_DYES[i].get(1));
		RM.Other.addFakeRecipe(F, ST.array(IL.SPRAY_CAN_DYES[i].get(1), IL.SPRAY_CAN_DYES_USED[i].get(1), ST.make(Blocks.wool, 1, 0), ST.make(Blocks.glass_pane, 1, 0), ST.make(Blocks.glass, 1, 0), ST.make(Blocks.hardened_clay, 1, 0)), ST.array(NI, NI, ST.make(Blocks.wool, 1, 15-i), ST.make(Blocks.stained_glass_pane, 1, 15-i), ST.make(Blocks.stained_glass, 1, 15-i), ST.make(Blocks.stained_hardened_clay, 1, 15-i)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		
		IL.SPRAY_CAN_FOAM[i]               .set(addItem( 1100+2*i, "C-Foam Spray ("+DYE_NAMES[i]+")", "Full", TC.stack(TC.TERRA, 2), TC.stack(TC.FABRICO, 2)));
		IL.SPRAY_CAN_FOAM_USED[i]          .set(addItem(mLastID+1, "C-Foam Spray ("+DYE_NAMES[i]+")", "Used", TC.stack(TC.TERRA, 1), TC.stack(TC.FABRICO, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Foam(IL.Spray_Empty.get(1), IL.SPRAY_CAN_FOAM_USED[i].get(1), IL.SPRAY_CAN_FOAM[i].get(1), 256, i, F);
		addItemBehavior(mLastID, tBehaviour); addItemBehavior(mLastID-1, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), FL.mul(DYED_C_FOAMS[i], 256), NF, IL.SPRAY_CAN_FOAM[i].get(1));
		ItemsGT.addNEIRedirects(IL.SPRAY_CAN_FOAM_USED[i].get(1), IL.SPRAY_CAN_FOAM[i].get(1), ST.make(BlocksGT.CFoamFresh, 1, i), ST.make(BlocksGT.CFoam, 1, i));
		RM.Other.addFakeRecipe(F, ST.array(IL.SPRAY_CAN_FOAM[i].get(1), IL.SPRAY_CAN_FOAM_USED[i].get(1)), ST.array(ST.make(BlocksGT.CFoamFresh, 1, i), ST.make(BlocksGT.CFoam, 1, i)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		
		IL.SPRAY_CAN_FOAM_OWNED[i]         .set(addItem( 1132+2*i, "Advanced C-Foam Spray ("+DYE_NAMES[i]+")", "Full (C-Foam only breakable by Owner once dry)", TC.stack(TC.TERRA, 2), TC.stack(TC.FABRICO, 2), TC.stack(TC.SPIRITUS, 2)));
		IL.SPRAY_CAN_FOAM_OWNED_USED[i]    .set(addItem(mLastID+1, "Advanced C-Foam Spray ("+DYE_NAMES[i]+")", "Used (C-Foam only breakable by Owner once dry)", TC.stack(TC.TERRA, 1), TC.stack(TC.FABRICO, 1), TC.stack(TC.SPIRITUS, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Foam(IL.Spray_Empty.get(1), IL.SPRAY_CAN_FOAM_OWNED_USED[i].get(1), IL.SPRAY_CAN_FOAM_OWNED[i].get(1), 256, i, T);
		addItemBehavior(mLastID, tBehaviour); addItemBehavior(mLastID-1, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), FL.mul(DYED_C_FOAMS_OWNED[i], 256), NF, IL.SPRAY_CAN_FOAM_OWNED[i].get(1));
		ItemsGT.addNEIRedirects(IL.SPRAY_CAN_FOAM_OWNED_USED[i].get(1), IL.SPRAY_CAN_FOAM_OWNED[i].get(1));
		RM.Other.addFakeRecipe(F, ST.array(IL.SPRAY_CAN_FOAM_OWNED[i].get(1), IL.SPRAY_CAN_FOAM_OWNED_USED[i].get(1)), ST.array(ST.make(BlocksGT.CFoamFresh, 1, i, "Player-Owned C-Foam"), ST.make(BlocksGT.CFoam, 1, i, "Player-Owned C-Foam")), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		}
		
		
		
		IL.Spray_Color_Remover             .set(addItem(1096, "Paint Removal Spray", "Full", TC.stack(TC.SENSUS, 2), TC.stack(TC.PERDITIO, 2)));
		IL.Spray_Color_Remover_Used        .set(addItem(1097, "Paint Removal Spray", "Used", TC.stack(TC.SENSUS, 1), TC.stack(TC.PERDITIO, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Color_Remover(IL.Spray_Empty.get(1), IL.Spray_Color_Remover_Used.get(1), IL.Spray_Color_Remover.get(1), 256);
		addItemBehavior(1096, tBehaviour); addItemBehavior(1097, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), MT.Cl.fluid(16*U, T), NF, IL.Spray_Color_Remover.get(1));
		ItemsGT.addNEIRedirects(IL.Spray_Color_Remover_Used.get(1), IL.Spray_Color_Remover.get(1));
		
		
		
		IL.Spray_Foam_Remover              .set(addItem(1196, "C-Foam Removal Spray", "Full", TC.stack(TC.TERRA, 2), TC.stack(TC.PERDITIO, 2)));
		IL.Spray_Foam_Remover_Used         .set(addItem(1197, "C-Foam Removal Spray", "Used", TC.stack(TC.TERRA, 1), TC.stack(TC.PERDITIO, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Foam_Remover(IL.Spray_Empty.get(1), IL.Spray_Foam_Remover_Used.get(1), IL.Spray_Foam_Remover.get(1), 256);
		addItemBehavior(1196, tBehaviour); addItemBehavior(1197, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), MT.H2SO4.fluid(16*U, T), NF, IL.Spray_Foam_Remover.get(1));
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), MT.HNO3 .fluid(16*U, T), NF, IL.Spray_Foam_Remover.get(1));
		ItemsGT.addNEIRedirects(IL.Spray_Foam_Remover_Used.get(1), IL.Spray_Foam_Remover.get(1));
		
		
		
		IL.Spray_Foam_Hardener             .set(addItem(1198, "Hardening Spray", "Full", TC.stack(TC.TERRA, 2), TC.stack(TC.TUTAMEN, 2)));
		IL.Spray_Foam_Hardener_Used        .set(addItem(1199, "Hardening Spray", "Used", TC.stack(TC.TERRA, 1), TC.stack(TC.TUTAMEN, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Foam_Hardener(IL.Spray_Empty.get(1), IL.Spray_Foam_Hardener_Used.get(1), IL.Spray_Foam_Hardener.get(1), 256);
		addItemBehavior(1198, tBehaviour); addItemBehavior(1199, tBehaviour);
		RM.Canner.addRecipe2(T, 16, 256, ST.make(Blocks.sand, 16, W), IL.Spray_Empty.get(1), IL.Spray_Foam_Hardener.get(1));
		for (OreDictMaterial tMat : ANY.SiO2.mToThis)
		RM.Canner.addRecipe2(T, 16, 256, OM.dust(tMat, 16*U), IL.Spray_Empty.get(1), IL.Spray_Foam_Hardener.get(1));
		ItemsGT.addNEIRedirects(IL.Spray_Foam_Hardener_Used.get(1), IL.Spray_Foam_Hardener.get(1));
		
		
		
		IL.Spray_Extinguisher              .set(addItem(1998, "Fire Extinguisher (CO2)", "Full", TC.stack(TC.PERDITIO, 2), TC.stack(TC.IGNIS, 2)));
		IL.Spray_Extinguisher_Used         .set(addItem(1999, "Fire Extinguisher (CO2)", "Used", TC.stack(TC.PERDITIO, 1), TC.stack(TC.IGNIS, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Spray_Extinguisher(IL.Spray_Empty.get(1), IL.Spray_Extinguisher_Used.get(1), IL.Spray_Extinguisher.get(1), 256);
		addItemBehavior(1998, tBehaviour); addItemBehavior(1999, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 256, IL.Spray_Empty.get(1), MT.CO2.fluid(16*U, T), NF, IL.Spray_Extinguisher.get(1));
		ItemsGT.addNEIRedirects(IL.Spray_Extinguisher_Used.get(1), IL.Spray_Extinguisher.get(1));
		
		
		
		OreDictMaterial[] tBucketMaterials = new OreDictMaterial[] {ANY.Cu, MT.Sn, MT.Zn, MT.Pb, MT.Bi, MT.Brass, MT.Bronze, MT.BismuthBronze, MT.Au};
		for (int i = 0; i < 9; i++) {
			OreDictItemData tData = new OreDictItemData(ANY.Wood, U*3, OM.stack(tBucketMaterials[i], U*1));
			ItemStack tBucket = addItem(2000 + i*100, "Wooden Bucket", "Empty", TC.stack(TC.ARBOR, 2), TC.stack(TC.VACUOS, 2), Behavior_Bucket_Simple.INSTANCE, tData);
			CR.shaped(tBucket, CR.DEF_NCC, "WPW", " Wh", 'P', OP.plate.dat(tBucketMaterials[i]), 'W', OD.plankAnyWood);
			ItemsGT.addNEIRedirects(tBucket
			, addItem(mLastID+1, "Wooden Bucket", "Water"                                  , TC.stack(TC.ARBOR, 2), TC.stack(TC.AQUA     , 2), new Behavior_Bucket_Simple(ST.make(Items.water_bucket, 1, 0))                       , tData.copy(), FL.Water.make(1000), FL.DistW.make(1000), FL.River_Water.make(1000)     , OD.container1000water)
			, addItem(mLastID+1, "Wooden Bucket", "Milk (you cannot drink out of Buckets!)", TC.stack(TC.ARBOR, 2), TC.stack(TC.SANO     , 2), new Behavior_Bucket_Simple(ST.make(Items.milk_bucket, 1, 0))                        , tData.copy(), FL.Milk.make(1000), FL.MilkGrC.make(1000)                               , OD.container1000milk)
			, addItem(mLastID+1, "Wooden Bucket", "Latex"                                  , TC.stack(TC.ARBOR, 2), TC.stack(TC.LIMUS    , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.Latex.make(1000), FL.make("molten.latex", 1000)                      , OD.container1000latex)
			, addItem(mLastID+1, "Wooden Bucket", "Creosote Oil"                           , TC.stack(TC.ARBOR, 2), TC.stack(TC.IGNIS    , 2), new Behavior_Bucket_Simple(IL.RC_Creosote_Bucket.get(1))                            , tData.copy(), FL.Oil_Creosote.make(1000)                                              , OD.container1000creosote)
			, addItem(mLastID+1, "Wooden Bucket", "Sea Water (you cannot place this!)"     , TC.stack(TC.ARBOR, 2), TC.stack(TC.TEMPESTAS, 2), new Behavior_Switch_Metadata(2000 + i*100, F, F)                                    , tData.copy(), FL.Ocean.make(1000), FL.OceanGrC.make(1000), FL.Tropics_Water.make(1000))
			, addItem(mLastID+1, "Wooden Bucket", "Rubber Tree Sap"                        , TC.stack(TC.ARBOR, 2), TC.stack(TC.LIMUS    , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.Resin_Rubber.make(1000)                                              , OD.container1000rubbertreesap)
			, addItem(mLastID+1, "Wooden Bucket", "Spruce Tree Resin"                      , TC.stack(TC.ARBOR, 3), TC.stack(TC.LIMUS    , 1), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.Resin_Spruce.make(1000)                                              , OD.container1000spruceresin)
			, addItem(mLastID+1, "Wooden Bucket", "Honey"                                  , TC.stack(TC.ARBOR, 3), TC.stack(TC.BESTIA   , 1), new Behavior_Bucket_Simple(ST.make(MD.ERE, "bucketHoney", 1, 0, IL.FR_Honey_Bucket)), tData.copy(), FL.Honey.make(1000), FL.HoneyGrC.make(1000), FL.HoneyBoP.make(1000)     , OD.container1000honey)
			, addItem(mLastID+1, "Wooden Bucket", "Dirty Water (you cannot place this!)"   , TC.stack(TC.ARBOR, 2), TC.stack(TC.AQUA     , 1), TC.stack(TC.VENENUM, 1), new Behavior_Switch_Metadata(2000 + i*100, F, F)           , tData.copy(), FL.Dirty_Water.make(1000)                                               )
			, addItem(mLastID+1, "Wooden Bucket", "Lubricant"                              , TC.stack(TC.ARBOR, 2), TC.stack(TC.LIMUS    , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.LubRoCant.make(1000), FL.Lubricant.make(1000)                        , OD.container1000lubricant)
			, addItem(mLastID+1, "Wooden Bucket", "Milk (you cannot drink out of Buckets!)", TC.stack(TC.ARBOR, 2), TC.stack(TC.VENENUM  , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.Milk_Spoiled.make(1000)                                              )
			, addItem(mLastID+1, "Wooden Bucket", "Maple Sap"                              , TC.stack(TC.ARBOR, 2), TC.stack(TC.FAMES    , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.Sap_Maple.make(1000)                                                 , OD.container1000maplesap)
			, addItem(mLastID+1, "Wooden Bucket", "Rainbow Sap"                            , TC.stack(TC.ARBOR, 2), TC.stack(TC.AURAM    , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.Sap_Rainbow.make(1000)                                               , OD.container1000rainbowsap)
			, addItem(mLastID+1, "Wooden Bucket", "Soy Milk"                               , TC.stack(TC.ARBOR, 2), TC.stack(TC.HERBA    , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.MilkSoy.make(1000)                                                   , OD.container1000soymilk)
			, addItem(mLastID+1, "Wooden Bucket", "Glue"                                   , TC.stack(TC.ARBOR, 2), TC.stack(TC.LIMUS    , 2), Behavior_Bucket_Simple.INSTANCE                                                     , tData.copy(), FL.Glue.make(1000)                                                      , OD.container1000glue)
			, addItem(mLastID+1, "Wooden Bucket", "Spectral Dew"                           , TC.stack(TC.ARBOR, 2), TC.stack(TC.SPIRITUS , 2), new Behavior_Bucket_Simple(IL.NeLi_Bucket_Spectral_Dew.get(1))                      , tData.copy(), FL.SpDew.make(1000)                                                     , OD.container1000water)
			);
		}
		
		IL.Wooden_Bucket_Copper       .set(this, 2000);
		IL.Wooden_Bucket_Tin          .set(this, 2100);
		IL.Wooden_Bucket_Zinc         .set(this, 2200);
		IL.Wooden_Bucket_Lead         .set(this, 2300);
		IL.Wooden_Bucket_Bismuth      .set(this, 2400);
		IL.Wooden_Bucket_Brass        .set(this, 2500);
		IL.Wooden_Bucket_Bronze       .set(this, 2600);
		IL.Wooden_Bucket_BismuthBronze.set(this, 2700);
		IL.Wooden_Bucket_Gold         .set(this, 2800);
		
		
		
		IL.Tool_Matches                    .set(addItem(5000, "Match"                            , ""                                            , new Behavior_Lighter(9000), TC.stack(TC.IGNIS, 1), TC.stack(TC.VACUOS, 1), OD.craftingFirestarter));
		for (OreDictMaterial tWood : ANY.Wood.mToThis) {
		RM.Assembler.addRecipe2(T, 16, 16, OP.bolt.mat(tWood, 1), OP.dustSmall.mat(MT.P           , 1), IL.Tool_Matches.get(1));
		RM.Assembler.addRecipe2(T, 16, 16, OP.bolt.mat(tWood, 1), OP.dustSmall.mat(MT.Phosphorus  , 1), IL.Tool_Matches.get(1));
		RM.Assembler.addRecipe2(T, 16, 64, OP.bolt.mat(tWood, 4), OP.dust     .mat(MT.P           , 1), IL.Tool_Matches.get(4));
		RM.Assembler.addRecipe2(T, 16, 64, OP.bolt.mat(tWood, 4), OP.dust     .mat(MT.Phosphorus  , 1), IL.Tool_Matches.get(4));
		}
		CR.shaped(IL.Tool_Matches.get(1), CR.DEF, "P", "S", 'P', OP.dustSmall.dat(MT.P                  ), 'S', OP.bolt.dat(ANY.Wood));
		CR.shaped(IL.Tool_Matches.get(1), CR.DEF, "P", "S", 'P', OP.dustSmall.dat(MT.Phosphorus         ), 'S', OP.bolt.dat(ANY.Wood));
		CR.shaped(IL.Tool_Matches.get(4), CR.DEF, " S ", "SPS", " S ", 'P', OP.dust.dat(MT.P            ), 'S', OP.bolt.dat(ANY.Wood));
		CR.shaped(IL.Tool_Matches.get(4), CR.DEF, " S ", "SPS", " S ", 'P', OP.dust.dat(MT.Phosphorus   ), 'S', OP.bolt.dat(ANY.Wood));
		IL.Tool_MatchBox_Used              .set(addItem(5002, "Match Box"                        , "This is not a Car"                           , TC.stack(TC.IGNIS, 2), TC.stack(TC.POTENTIA, 1), OD.craftingFirestarter, TD.Creative.HIDDEN));
		IL.Tool_MatchBox_Full              .set(addItem(5003, "Match Box (Full)"                 , "This is not a Car"                           , TC.stack(TC.IGNIS, 1), TC.stack(TC.POTENTIA, 2), OD.craftingFirestarter));
		tBehaviour = new Behavior_Lighter(null, IL.Tool_MatchBox_Used.get(1), IL.Tool_MatchBox_Full.get(1), 64, 9000);
		addItemBehavior(5002, tBehaviour); addItemBehavior(5003, tBehaviour);
		ItemsGT.addNEIRedirects(IL.Tool_Matches.get(1), IL.Tool_MatchBox_Used.get(1), IL.Tool_MatchBox_Full.get(1));
		RM.Boxinator.addRecipe2(T, 16, 64, IL.Tool_Matches.get(64), OP.plateDouble.mat(MT.Paper, 1), IL.Tool_MatchBox_Full.get(1));
		RM.Unboxinator.addRecipe1(T, 16, 32, IL.Tool_MatchBox_Full.get(1), IL.Tool_Matches.get(64), OP.scrapGt.mat(MT.Paper, 16));
		
		
		
		IL.Tool_Lighter_Invar_Empty        .set(addItem(5004, "Lighter (Empty)"                  , "Requires Canning Machine to be filled"       , new OreDictItemData(MT.Invar, OP.plateCurved.mAmount * 2), TC.stack(TC.IGNIS, 1), TC.stack(TC.VACUOS, 1)));
		IL.Tool_Lighter_Invar_Used         .set(addItem(5005, "Lighter"                          , ""                                            , new OreDictItemData(MT.Invar, OP.plateCurved.mAmount * 2), TC.stack(TC.IGNIS, 2), TC.stack(TC.POTENTIA, 1), OD.craftingFirestarter, TD.Creative.HIDDEN));
		IL.Tool_Lighter_Invar_Full         .set(addItem(5006, "Lighter (Full)"                   , ""                                            , new OreDictItemData(MT.Invar, OP.plateCurved.mAmount * 2), TC.stack(TC.IGNIS, 1), TC.stack(TC.POTENTIA, 2), OD.craftingFirestarter));
		tBehaviour = new Behavior_Lighter(IL.Tool_Lighter_Invar_Empty.get(1), IL.Tool_Lighter_Invar_Used.get(1), IL.Tool_Lighter_Invar_Full.get(1), 100, 10000);
		addItemBehavior(5005, tBehaviour); addItemBehavior(5006, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 16, IL.Tool_Lighter_Invar_Empty.get(1), FL.Butane .make(100), NF, IL.Tool_Lighter_Invar_Full.get(1));
		RM.Canner.addRecipe1(T, 16, 16, IL.Tool_Lighter_Invar_Empty.get(1), FL.Propane.make(100), NF, IL.Tool_Lighter_Invar_Full.get(1));
		ItemsGT.addNEIRedirects(IL.Tool_Lighter_Invar_Empty.get(1), IL.Tool_Lighter_Invar_Used.get(1), IL.Tool_Lighter_Invar_Full.get(1));
		
		
		
		IL.Tool_Lighter_Platinum_Empty     .set(addItem(5007, "Shiny Lighter (Empty)"            , "Requires Canning Machine to be filled"       , new OreDictItemData(MT.Pt, OP.plateCurved.mAmount * 2), TC.stack(TC.IGNIS, 1), TC.stack(TC.NEBRISUM, 1), TC.stack(TC.VACUOS, 1)));
		IL.Tool_Lighter_Platinum_Used      .set(addItem(5008, "Shiny Lighter"                    , "A known Master of Pranks is engraved on it"  , new OreDictItemData(MT.Pt, OP.plateCurved.mAmount * 2), TC.stack(TC.IGNIS, 2), TC.stack(TC.NEBRISUM, 1), TC.stack(TC.POTENTIA, 1), OD.craftingFirestarter, TD.Creative.HIDDEN));
		IL.Tool_Lighter_Platinum_Full      .set(addItem(5009, "Shiny Lighter (Full)"             , "A known Master of Pranks is engraved on it"  , new OreDictItemData(MT.Pt, OP.plateCurved.mAmount * 2), TC.stack(TC.IGNIS, 1), TC.stack(TC.NEBRISUM, 1), TC.stack(TC.POTENTIA, 2), OD.craftingFirestarter));
		tBehaviour = new Behavior_Lighter(IL.Tool_Lighter_Platinum_Empty.get(1), IL.Tool_Lighter_Platinum_Used.get(1), IL.Tool_Lighter_Platinum_Full.get(1), 1000, 10000);
		addItemBehavior(5008, tBehaviour); addItemBehavior(5009, tBehaviour);
		RM.Canner.addRecipe1(T, 16, 64, IL.Tool_Lighter_Platinum_Empty.get(1), FL.Butane .make(1000), NF, IL.Tool_Lighter_Platinum_Full.get(1));
		RM.Canner.addRecipe1(T, 16, 64, IL.Tool_Lighter_Platinum_Empty.get(1), FL.Propane.make(1000), NF, IL.Tool_Lighter_Platinum_Full.get(1));
		ItemsGT.addNEIRedirects(IL.Tool_Lighter_Platinum_Empty.get(1), IL.Tool_Lighter_Platinum_Used.get(1), IL.Tool_Lighter_Platinum_Full.get(1));
		
		
		
		IL.Tool_Lighter_Plastic_Empty      .set(addItem(5010, "Plastic Lighter (Empty)"          , "Requires Canning Machine to be filled"       , new OreDictItemData(MT.Plastic, OP.plateCurved.mAmount * 2, ANY.Fe, OP.screw.mAmount), TC.stack(TC.IGNIS, 1), TC.stack(TC.VACUOS, 1)));
		IL.Tool_Lighter_Plastic_Used       .set(addItem(5011, "Plastic Lighter"                  , ""                                            , new OreDictItemData(MT.Plastic, OP.plateCurved.mAmount * 2, ANY.Fe, OP.screw.mAmount), TC.stack(TC.IGNIS, 2), TC.stack(TC.POTENTIA, 1), OD.craftingFirestarter, TD.Creative.HIDDEN));
		IL.Tool_Lighter_Plastic_Full       .set(addItem(5012, "Plastic Lighter (Full)"           , ""                                            , new OreDictItemData(MT.Plastic, OP.plateCurved.mAmount * 2, ANY.Fe, OP.screw.mAmount), TC.stack(TC.IGNIS, 1), TC.stack(TC.POTENTIA, 2), OD.craftingFirestarter));
		IL.Tool_Lighter_Plastic_Broken     .set(addItem(5013, "Plastic Lighter (Broken)"         , ""                                            , new OreDictItemData(MT.Plastic, OP.plateCurved.mAmount * 2, ANY.Fe, OP.screw.mAmount), TC.stack(TC.IGNIS, 1), TC.stack(TC.PERDITIO, 1), TD.Creative.HIDDEN));
		tBehaviour = new Behavior_Lighter(IL.Tool_Lighter_Plastic_Broken.get(1), IL.Tool_Lighter_Plastic_Used.get(1), IL.Tool_Lighter_Plastic_Full.get(1), 100, 9000);
		addItemBehavior(5011, tBehaviour); addItemBehavior(5012, tBehaviour);
		CR.shaped(IL.Tool_Lighter_Plastic_Empty.get(1), CR.DEF_NCC, "IF", "dP", "xP", 'F', OD.itemFlint, 'P', OP.plateCurved.dat(MT.Plastic), 'I', OP.screw.dat(ANY.Iron));
		RM.Canner.addRecipe1(T, 16, 16, IL.Tool_Lighter_Plastic_Empty.get(1), FL.Butane .make(100), NF, IL.Tool_Lighter_Plastic_Full.get(1));
		RM.Canner.addRecipe1(T, 16, 16, IL.Tool_Lighter_Plastic_Empty.get(1), FL.Propane.make(100), NF, IL.Tool_Lighter_Plastic_Full.get(1));
		ItemsGT.addNEIRedirects(IL.Tool_Lighter_Plastic_Empty.get(1), IL.Tool_Lighter_Plastic_Used.get(1), IL.Tool_Lighter_Plastic_Full.get(1), IL.Tool_Lighter_Plastic_Broken.get(1));
		
		
		
		IL.Tool_Fire_Starter               .set(addItem(5014, "Fire Starter"                     , "(Made with Dry Grass)"                       , new OreDictItemData(ANY.Wood, U), new Behavior_Lighter(5000), TC.stack(TC.IGNIS, 1), TC.stack(TC.ARBOR, 1), TC.stack(TC.HERBA, 1), OD.craftingFirestarter));
		CR.shaped(IL.Tool_Fire_Starter.get(1)       , CR.DEF_NCC_MIR, "S ", "GS", 'S', OP.stick.dat(ANY.Wood), 'G', OD.itemGrassDry);
		
		IL.Tool_Fire_Starter_Bark          .set(addItem(5015, "Fire Starter"                     , "(Made with Dry Tree Bark)"                   , new OreDictItemData(ANY.Wood, U), new Behavior_Lighter(5500), TC.stack(TC.IGNIS, 1), TC.stack(TC.ARBOR, 2), OD.craftingFirestarter));
		CR.shaped(IL.Tool_Fire_Starter_Bark.get(1)  , CR.DEF_NCC_MIR, "S ", "GS", 'S', OP.stick.dat(ANY.Wood), 'G', OD.itemBarkDry);
		
		
		IL.Pellet_Wood                     .set(addItem(5999, "Wood Pellet"                      , ""                                            , new OreDictItemData(ANY.Wood, U), TICKS_PER_SMELT, TC.stack(TC.POTENTIA, 1)));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.LiveRoot   , U2), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 32, OM.dust(MT.LiveRoot       ), FL.Glue.make(250), NF, IL.Pellet_Wood.get(2));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.Livingwood , U2), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 32, OM.dust(MT.Livingwood     ), FL.Glue.make(250), NF, IL.Pellet_Wood.get(2));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.Greatwood  , U2), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 32, OM.dust(MT.Greatwood      ), FL.Glue.make(250), NF, IL.Pellet_Wood.get(2));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.Dreamwood  , U4), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 64, OM.dust(MT.Dreamwood      ), FL.Glue.make(500), NF, IL.Pellet_Wood.get(4));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.Shimmerwood, U4), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 64, OM.dust(MT.Shimmerwood    ), FL.Glue.make(500), NF, IL.Pellet_Wood.get(4));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.Silverwood , U4), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 64, OM.dust(MT.Silverwood     ), FL.Glue.make(500), NF, IL.Pellet_Wood.get(4));
		for (OreDictMaterial tWood : ANY.Wood.mToThis)
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(tWood             ), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Mixer.addRecipe1(T, 16, 16, OM.dust(MT.Bark           ), FL.Glue.make(125), NF, IL.Pellet_Wood.get(1));
		RM.Compressor.addRecipe1(T, 16, 16, IL.Pellet_Wood.get(2), ST.make(BlocksGT.Planks, 1, 8));
		RM.CokeOven.addRecipe1(T, 0, 900, IL.Pellet_Wood.get(1), NF, FL.Oil_Creosote.make(25), chunkGt.mat(MT.Charcoal, 1));
		
		
		IL.Module_Stone_Generator          .set(addItem(6000, "Stone Generator Module"           , "Generates Stone for Recipes, also known as a Cobble Generator", TC.stack(TC.MACHINA, 1), TC.stack(TC.FABRICO, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.TERRA, 1), TC.stack(TC.AQUA, 1)));
		IL.Module_Basalt_Generator         .set(addItem(6001, "Basalt Generator Module"          , "Generates Basalt for Recipes"                                 , TC.stack(TC.MACHINA, 1), TC.stack(TC.FABRICO, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.TERRA, 1), TC.stack(TC.AQUA, 1)));
		IL.Module_Blackstone_Generator     .set(addItem(6002, "Blackstone Generator Module"      , "Generates Blackstone for Recipes"                             , TC.stack(TC.MACHINA, 1), TC.stack(TC.FABRICO, 1), TC.stack(TC.IGNIS, 1), TC.stack(TC.TERRA, 1), TC.stack(TC.AQUA, 1)));
		CR.shaped(IL.Module_Stone_Generator     .get(1), CR.DEF_REV_NCC, "CPC", "LMW", "COC", 'M', OP.casingMachine.dat(MT.SteelGalvanized), 'O', IL.Shape_Extruder_Block, 'C', OD_CIRCUITS[4], 'P', OD.craftingPiston, 'L', OD.container1000lava, 'W', OD.container1000water);
		CR.shaped(IL.Module_Basalt_Generator    .get(1), CR.DEF_REV_NCC, "S", "M", "I", 'M', IL.Module_Stone_Generator, 'S', OD.soulsand, 'I', Blocks.packed_ice);
		CR.shaped(IL.Module_Blackstone_Generator.get(1), CR.DEF_REV_NCC, "S", "M", "O", 'M', IL.Module_Stone_Generator, 'S', OD.soulsand, 'O', OD.obsidian);
		
		
		IL.Paper_Printed_Pages             .set(addItem(7002, "Printed Pages"                    , ""                                                            , Behavior_PrintedPages.INSTANCE, new OreDictItemData(MT.Paper, U*3), TC.stack(TC.COGNITIO, 3)));
		IL.Paper_Printed_Pages_Many        .set(addItem(7003, "Many Printed Pages"               , ""                                                            , Behavior_PrintedPages.INSTANCE, new OreDictItemData(MT.Paper, U*6), TC.stack(TC.COGNITIO, 6)));
		BooksGT.BOOK_REGISTER.put(IL.Paper_Printed_Pages     , (byte)26);
		BooksGT.BOOK_REGISTER.put(IL.Paper_Printed_Pages_Many, (byte)26);
		
		IL.Paper_Blueprint_Empty           .set(addItem(7010, "Empty Blueprint"                  , "Place in Blueprint Slot and Shiftclick it, to assign Recipe" , new OreDictItemData(MT.Paper, U), TC.stack(TC.COGNITIO, 1)));
		IL.Paper_Blueprint_Used            .set(addItem(7011, "Blueprint"                        , "Blueprint containing a Crafting Recipe"                      , new OreDictItemData(MT.Paper, U), TC.stack(TC.COGNITIO, 2), "gt:autocrafterblueprintitem"));
		for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Blue])
		RM.Bath.addRecipe1(T, 0, 16, ST.make(Items.paper, 1, W), tDye, NF, IL.Paper_Blueprint_Empty.get(1));
		BooksGT.BOOK_REGISTER.put(IL.Paper_Blueprint_Empty, (byte)25);
		BooksGT.BOOK_REGISTER.put(IL.Paper_Blueprint_Used , (byte)28);
		ItemsGT.addNEIRedirects(IL.Paper_Blueprint_Empty.get(1), IL.Paper_Blueprint_Used.get(1));
		
		
		for (int i = 0; i < 16; i++)            addItem(i       +  7030, DYE_NAMES[i] + " Canvas"           , "Can be used together with the Obscurator"    , "gt:canvas", new CoverTextureCanvas(BlockTextureDefault.get("machines/covers/canvas", DYES[i])), new OreDictItemData(MT.Paper, U), TC.stack(TC.PANNUS, 1));
		for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_White])
		RM.Bath.addRecipe1(T, 0, 16, ST.make(Items.paper, 1, W), tDye, NF, make(7030+DYE_INDEX_White));
		for (int i = 0; i < 16; i++) if (i != DYE_INDEX_White) for (FluidStack tDye : DYE_FLUIDS[i])
		RM.Bath.addRecipe1(T, 0, 16, make(7030+DYE_INDEX_White), tDye, NF, make(7030+i));
		for (int i = 0; i < 16; i++)
		BooksGT.BOOK_REGISTER.put(this, 7030+i, (byte)27);
		
		
		IL.Paper_Magic_Research_0          .set(addItem(7990, "Magic Research Paper (Introduction)", "", new OreDictItemData(MT.Paper, U), TC.TERRA   .get(8), TC.AQUA        .get(8), TC.AER     .get(8), TC.IGNIS   .get(8), TC.PERDITIO .get(8), TC.ORDO      .get(8)));
		IL.Paper_Magic_Research_1          .set(addItem(7991, "Magic Research Paper (Page 1 / 8)"  , "", new OreDictItemData(MT.Paper, U), TC.LUX     .get(8), TC.MOTUS       .get(8), TC.VACUOS  .get(8), TC.POTENTIA.get(8), TC.VITREUS  .get(8), TC.VICTUS    .get(8)));
		IL.Paper_Magic_Research_2          .set(addItem(7992, "Magic Research Paper (Page 2 / 8)"  , "", new OreDictItemData(MT.Paper, U), TC.VOLATUS .get(8), TC.ITER        .get(8), TC.MORTUUS .get(8), TC.BESTIA  .get(8), TC.TENEBRAE .get(8), TC.PRAECANTIO.get(8)));
		IL.Paper_Magic_Research_3          .set(addItem(7993, "Magic Research Paper (Page 3 / 8)"  , "", new OreDictItemData(MT.Paper, U), TC.ALIENIS .get(8), TC.EXAMINIS    .get(8), TC.SPIRITUS.get(8), TC.VITIUM  .get(8), TC.METALLUM .get(8), TC.GELUM     .get(8)));
		IL.Paper_Magic_Research_4          .set(addItem(7994, "Magic Research Paper (Page 4 / 8)"  , "", new OreDictItemData(MT.Paper, U), TC.COGNITIO.get(8), TC.SENSUS      .get(8), TC.HERBA   .get(8), TC.SANO    .get(8), TC.LIMUS    .get(8), TC.TEMPESTAS .get(8)));
		IL.Paper_Magic_Research_5          .set(addItem(7995, "Magic Research Paper (Page 5 / 8)"  , "", new OreDictItemData(MT.Paper, U), TC.HUMANUS .get(8), TC.VINCULUM    .get(8), TC.ARBOR   .get(8), TC.FAMES   .get(8), TC.CORPUS   .get(8), TC.AURAM     .get(8)));
		IL.Paper_Magic_Research_6          .set(addItem(7996, "Magic Research Paper (Page 6 / 8)"  , "", new OreDictItemData(MT.Paper, U), TC.PERFODIO.get(8), TC.INSTRUMENTUM.get(8), TC.MESSIS  .get(8), TC.LUCRUM  .get(8), TC.VENENUM  .get(8), TC.PERMUTATIO.get(8)));
		IL.Paper_Magic_Research_7          .set(addItem(7997, "Magic Research Paper (Page 7 / 8)"  , "", new OreDictItemData(MT.Paper, U), TC.TELUM   .get(8), TC.TUTAMEN     .get(8), TC.PANNUS  .get(8), TC.FABRICO .get(8), TC.METO     .get(8), TC.MACHINA   .get(8)));
		IL.Paper_Magic_Research_8          .set(addItem(7998, "Magic Research Paper (Page 8 / 8)"  , "", new OreDictItemData(MT.Paper, U), TC.ELECTRUM.get(8), TC.RADIO       .get(8), TC.MAGNETO .get(8), TC.NEBRISUM.get(8), TC.STRONTIO .get(8), TC.REFLEXIO  .get(8)));
		
		for (int i = 0; i < IL.MAGIC_RESEARCH_PAPERS.length; i++) {
			BooksGT.BOOK_REGISTER.put(IL.MAGIC_RESEARCH_PAPERS[i], (byte)18);
			if (IL.TC_Knowledge_Fragment.exists()) {
				RM.generify(IL.MAGIC_RESEARCH_PAPERS[i].get(1), IL.TC_Knowledge_Fragment.get(Math.max(1, i)));
				CR.shapeless(IL.TC_Knowledge_Fragment.get(Math.max(1, i)), CR.DEF, new Object[] {IL.MAGIC_RESEARCH_PAPERS[i]});
			}
		}
		
		IL.Robot_Tip_Wrench                .set(addItem(8000, "Robot Arm Wrench Tip"         , "Infinitely usable inside an Autocrafter", OreDictToolNames.wrench                                                          , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Screwdriver           .set(addItem(8001, "Robot Arm Screwdriver Tip"    , "Infinitely usable inside an Autocrafter", OreDictToolNames.screwdriver                                                     , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Saw                   .set(addItem(8002, "Robot Arm Saw Tip"            , "Infinitely usable inside an Autocrafter", OreDictToolNames.saw                                                             , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Hammer                .set(addItem(8003, "Robot Arm Hammer Tip"         , "Infinitely usable inside an Autocrafter", OreDictToolNames.hammer, OreDictToolNames.hac_mortar                             , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Cutter                .set(addItem(8004, "Robot Arm Cutter Tip"         , "Infinitely usable inside an Autocrafter", OreDictToolNames.wirecutter, OreDictToolNames.scissors                           , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Chisel                .set(addItem(8005, "Robot Arm Chisel Tip"         , "Infinitely usable inside an Autocrafter", OreDictToolNames.chisel                                                          , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Rubber                .set(addItem(8006, "Robot Arm Rubber Hammer Tip"  , "Infinitely usable inside an Autocrafter", OreDictToolNames.softhammer                                                      , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Blade                 .set(addItem(8007, "Robot Arm Blade Tip"          , "Infinitely usable inside an Autocrafter", OreDictToolNames.sword, OreDictToolNames.blade, OreDictToolNames.hac_cuttingboard, "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_Drill                 .set(addItem(8008, "Robot Arm Drill Tip"          , "Infinitely usable inside an Autocrafter", OreDictToolNames.drill, OreDictToolNames.handdrill                               , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		IL.Robot_Tip_File                  .set(addItem(8009, "Robot Arm File Tip"           , "Infinitely usable inside an Autocrafter", OreDictToolNames.file                                                            , "gt:autocrafterinfinite", TC.stack(TC.INSTRUMENTUM, 4)));
		
		CR.shaped(IL.Robot_Tip_Wrench       .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.MOTORS      [3], 'X', OP.toolHeadWrench.dat(MT.Cr));
		CR.shaped(IL.Robot_Tip_Screwdriver  .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.MOTORS      [3], 'X', OP.toolHeadScrewdriver.dat(MT.StainlessSteel));
		CR.shaped(IL.Robot_Tip_Saw          .get(1), CR.DEF_REV, "wPh", "CMC", "DXd", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.MOTORS      [3], 'X', OP.toolHeadBuzzSaw.dat(MT.CobaltBrass), 'D', OP.dust.dat(ANY.Diamond));
		CR.shaped(IL.Robot_Tip_Hammer       .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.PISTONS     [3], 'X', OP.toolHeadHammer.dat(MT.TungstenCarbide));
		CR.shaped(IL.Robot_Tip_Cutter       .get(1), CR.DEF_REV, "wPh", "CMC", "XfX", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.MOTORS      [3], 'X', OP.plate.dat(MT.StainlessSteel));
		CR.shaped(IL.Robot_Tip_Chisel       .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.PISTONS     [3], 'X', OP.toolHeadChisel.dat(MT.TungstenSteel));
		CR.shaped(IL.Robot_Tip_Rubber       .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.PISTONS     [3], 'X', OP.toolHeadHammer.dat(MT.Rubber));
		CR.shaped(IL.Robot_Tip_Blade        .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.PISTONS     [3], 'X', OP.toolHeadSword.dat(MT.Bronze));
		CR.shaped(IL.Robot_Tip_Drill        .get(1), CR.DEF_REV, "wPh", "CMC", "fX ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.MOTORS      [3], 'X', OP.stick.dat(ANY.Steel));
		CR.shaped(IL.Robot_Tip_File         .get(1), CR.DEF_REV, "wPh", "CMC", " X ", 'P', OP.plateCurved.dat(MT.SteelGalvanized), 'C', OD_CIRCUITS[3], 'M', IL.CONVEYERS   [3], 'X', OP.dust.dat(ANY.Diamond));
		
		IL.Tool_Remote_Activator           .set(addItem(9000, "Remote Activator"         , "", Behavior_Remote.INSTANCE, TC.stack(TC.MOTUS, 1), TC.stack(TC.PERMUTATIO, 1)));
		IL.Tool_Cheat                      .set(addItem(9001, "Debug Scanner"            , "", Behavior_Cropnalyzer.INSTANCE, ItemsGT.DEBUG_ITEMS, ItemsGT.ILLEGAL_DROPS, GarbageGT.BLACKLIST, new Behavior_Scanner(Integer.MAX_VALUE), EnergyStatDebug.INSTANCE, TC.stack(TC.SENSUS,10), TC.stack(TC.INSTRUMENTUM,10)));
		IL.Tool_Scanner                    .set(addItem(9002, "Portable Scanner"         , "", Behavior_Cropnalyzer.INSTANCE, new Behavior_Scanner(2), EnergyStat.makeTool(TD.Energy.EU, V[3]*8000, V[3], 64, next()), TC.stack(TC.SENSUS,10), TC.stack(TC.INSTRUMENTUM,10)));
		IL.Tool_Cropnalyzer                .set(addItem(9003, "Portable Cropnalyzer"     , "", Behavior_Cropnalyzer.INSTANCE, EnergyStat.makeTool(TD.Energy.EU, V[2]*8000, V[2], 64, next()), TC.stack(TC.SENSUS, 5), TC.stack(TC.INSTRUMENTUM, 5), TC.stack(TC.HERBA, 5)));
		IL.Tool_Worldgen_Debugger          .set(addItem(9999, "Worldgen Debug Wand"      , "", Behavior_Worldgen_Debugger.INSTANCE, ItemsGT.DEBUG_ITEMS, ItemsGT.ILLEGAL_DROPS, GarbageGT.BLACKLIST, TC.stack(TC.TERRA,10), TC.stack(TC.PRAECANTIO,10), TC.stack(TC.INSTRUMENTUM,10)));
		
		CR.shaped(IL.Tool_Remote_Activator      .get(1), CR.DEF_REV, "TPE", "BCd", "xPT", 'P', OP.plate.dat(MT.Cr), 'T', OP.screw.dat(MT.Cr), 'C', OD_CIRCUITS[4], 'E', IL.EMITTERS[4], 'B', OD.button);
		BooksGT.BOOK_REGISTER.put(IL.Tool_Remote_Activator, (byte)1);
//      Moved to the Battery Section of the MTE Loader.
//      CR.shaped(IL.Tool_Scanner               .get(1), CR.DEF_REV, "EXR", "CPU", "BXB", 'B', IL.Battery_Alkaline_HV, 'X', OP.plate.dat(MT.Cr), 'U', OD_USB_STICKS[3], 'C', OD_USB_CABLES[3], 'E', IL.EMITTERS[4], 'R', IL.SENSORS[4], 'P', IL.Processor_Crystal_Sapphire);
//      CR.shaped(IL.Tool_Cropnalyzer           .get(1), CR.DEF_REV, "EXR", "CPU", "BXB", 'B', IL.Battery_Alkaline_MV, 'X', OP.plate.dat(MT.Al), 'U', OD_USB_STICKS[1], 'C', OD_USB_CABLES[1], 'E', IL.EMITTERS[2], 'R', IL.SENSORS[2], 'P', OD_CIRCUITS[6]);
		
		IL.Thermometer_Quicksilver         .set(addItem(10000, "Quicksilver Thermometer"  , "", new Behavior_Tool(TOOL_thermometer  ), TC.stack(TC.VENENUM, 1), TC.stack(TC.VITREUS, 1)));
		IL.Geiger_Counter_Empty            .set(addItem(10001, "Geiger Counter (Empty)"   , "Fill with proper inert Gas"             , TC.stack(TC.SENSUS, 1), TC.stack(TC.RADIO, 1)));
		IL.Geiger_Counter                  .set(addItem(10002, "Geiger Counter"           , "", new Behavior_Tool(TOOL_geigercounter), TC.stack(TC.SENSUS, 5), TC.stack(TC.RADIO, 5)));
		
		RM.Canner.addRecipe1(T, 16, 64, IL.Geiger_Counter_Empty.get(1), FL.Helium.make(1000), NF, IL.Geiger_Counter.get(1));
		RM.Canner.addRecipe1(T, 16, 64, IL.Geiger_Counter_Empty.get(1), FL.Neon  .make(1000), NF, IL.Geiger_Counter.get(1));
		RM.Canner.addRecipe1(T, 16, 64, IL.Geiger_Counter_Empty.get(1), FL.Argon .make(1000), NF, IL.Geiger_Counter.get(1));
		
		CR.shaped(IL.Thermometer_Quicksilver    .get(1), CR.DEF_REV, " GD", "GQG", "PG ", 'P', OP.plate.dat(ANY.Cu), 'G', OP.plate.dat(MT.Glass), 'D', DYE_OREDICTS[DYE_INDEX_Red], 'Q', OD.itemQuicksilver);
		CR.shaped(IL.Geiger_Counter_Empty       .get(1), CR.DEF_REV, "TUT", "PCP", "TdT", 'U', OP.capcellcon.dat(MT.Al), 'P', OP.plate.dat(MT.Al), 'T', OP.screw.dat(MT.Al), 'C', OD_CIRCUITS[1]);
		OM.data(IL.Geiger_Counter.get(1), OM.data(IL.Geiger_Counter_Empty.get(1)));
		
		
		IL.Compass_North                   .set(addItem(11000, "Compass (NORTH)" , "Points to Zed Negative"                             , OD.itemCompass, new Behavior_Switch_Metadata(11001, T, T), OM.data(ST.make(Items.compass, 1, 0)), TC.stack(TC.METALLUM, 2), TC.stack(TC.MAGNETO                                              , 2))); CR.shapeless(ST.make(Items.compass, 1, 0), CR.DEF, new Object[] {last()});
		IL.Compass_Face                    .set(addItem(11001, "Compass (FACE)"  , "Points into the direction you look"                 , OD.itemCompass, new Behavior_Switch_Metadata(11002, T, T), OM.data(ST.make(Items.compass, 1, 0)), TD.Creative.HIDDEN, TC.stack(TC.METALLUM, 2), TC.stack(TC.MAGNETO, 1), TC.stack(TC.COGNITIO, 1))); CR.shapeless(ST.make(Items.compass, 1, 0), CR.DEF, new Object[] {last()});
		IL.Compass_Spawn                   .set(addItem(11002, "Compass (SPAWN)" , "Points to the World Spawn like a normal Compass"    , OD.itemCompass, new Behavior_Switch_Metadata(11003, T, T), OM.data(ST.make(Items.compass, 1, 0)), TD.Creative.HIDDEN, TC.stack(TC.METALLUM, 2), TC.stack(TC.MAGNETO, 1), TC.stack(TC.HUMANUS , 1))); CR.shapeless(ST.make(Items.compass, 1, 0), CR.DEF, new Object[] {last()});
		IL.Compass_Center                  .set(addItem(11003, "Compass (CENTER)", "Points towards the middle of the World"             , OD.itemCompass, new Behavior_Switch_Metadata(11004, T, T), OM.data(ST.make(Items.compass, 1, 0)), TD.Creative.HIDDEN, TC.stack(TC.METALLUM, 2), TC.stack(TC.MAGNETO, 1), TC.stack(TC.TERRA   , 1))); CR.shapeless(ST.make(Items.compass, 1, 0), CR.DEF, new Object[] {last()});
		IL.Compass_Death                   .set(addItem(11004, "Compass (DEATH)" , "Points to your last Death, if you did not Rage Quit", OD.itemCompass, new Behavior_Switch_Metadata(11000, T, T), OM.data(ST.make(Items.compass, 1, 0)), TD.Creative.HIDDEN, TC.stack(TC.METALLUM, 2), TC.stack(TC.MAGNETO, 1), TC.stack(TC.MORTUUS , 1))); CR.shapeless(ST.make(Items.compass, 1, 0), CR.DEF, new Object[] {last()});
		ItemsGT.addNEIRedirects(IL.Compass_North.get(1), IL.Compass_Face.get(1), IL.Compass_Spawn.get(1), IL.Compass_Center.get(1), IL.Compass_Death.get(1));
		CR.shapeless(IL.Compass_North.get(1), CR.DEF, new Object[] {Items.compass});
		
		
		IL.Tape                            .set(addItem(12000, "Tape", "Full Roll", TC.stack(TC.PANNUS, 1), TC.stack(TC.LIMUS, 1)));
		IL.Tape_Used                       .set(addItem(12001, "Tape", "Used Roll", TC.stack(TC.PANNUS, 1), TC.stack(TC.LIMUS, 1)));
		CR.shaped   (IL.Tape.get(1), CR.DEF, "PPP", " G ", 'P', OD.paperEmpty, 'G', OD.itemGlue);
		CR.shapeless(IL.Tape.get(1), CR.DEF, new Object[] {IL.Tape_Used, IL.Tape_Used, IL.Tape_Used, IL.Tape_Used});
		tBehaviour = new Behavior_Duct_Tape(null, IL.Tape_Used.get(1), IL.Tape.get(1), 0, 10000);
		ItemsGT.addNEIRedirects(IL.Tape.get(1), IL.Tape_Used.get(1));
		BooksGT.BOOK_REGISTER.put(IL.Tape           , (byte)57);
		BooksGT.BOOK_REGISTER.put(IL.Tape_Used      , (byte)57);
		addItemBehavior(12000, tBehaviour);
		addItemBehavior(12001, tBehaviour);
		
		IL.Duct_Tape                       .set(addItem(12002, "Duct Tape", "Full Roll", TC.stack(TC.FABRICO, 1), TC.stack(TC.LIMUS, 1), OD.craftingDuctTape));
		IL.Duct_Tape_Used                  .set(addItem(12003, "Duct Tape", "Used Roll", TC.stack(TC.FABRICO, 1), TC.stack(TC.LIMUS, 1)));
		CR.shaped   (IL.Duct_Tape.get(1), CR.DEF, "PPP", " G ", 'P', OP.foil.dat(MT.Plastic), 'G', OD.itemGlue);
		CR.shapeless(IL.Duct_Tape.get(1), CR.DEF, new Object[] {IL.Duct_Tape_Used, IL.Duct_Tape_Used, IL.Duct_Tape_Used, IL.Duct_Tape_Used});
		tBehaviour = new Behavior_Duct_Tape(null, IL.Duct_Tape_Used.get(1), IL.Duct_Tape.get(1), 1, 100000);
		ItemsGT.addNEIRedirects(IL.Duct_Tape.get(1), IL.Duct_Tape_Used.get(1));
		BooksGT.BOOK_REGISTER.put(IL.Duct_Tape      , (byte)58);
		BooksGT.BOOK_REGISTER.put(IL.Duct_Tape_Used , (byte)58);
		addItemBehavior(12002, tBehaviour);
		addItemBehavior(12003, tBehaviour);
		
		IL.Brain_Tape                      .set(addItem(12008, "BrainTech Aerospace Advanced Reinforced Duct Tape FAL-84", "Full Roll", TC.stack(TC.TUTAMEN, 1), TC.stack(TC.LIMUS, 1), OD.craftingDuctTape));
		IL.Brain_Tape_Used                 .set(addItem(12009, "BrainTech Aerospace Advanced Reinforced Duct Tape FAL-84", "Used Roll", TC.stack(TC.TUTAMEN, 1), TC.stack(TC.LIMUS, 1), OD.craftingDuctTape));
		CR.shaped   (IL.Brain_Tape.get(1), CR.DEF, "PPP", " G ", 'P', OP.foil.dat(ANY.W), 'G', OD.itemGlue);
		CR.shapeless(IL.Brain_Tape.get(1), CR.DEF, new Object[] {IL.Brain_Tape_Used, IL.Brain_Tape_Used, IL.Brain_Tape_Used, IL.Brain_Tape_Used});
		tBehaviour = new Behavior_Duct_Tape(null, IL.Brain_Tape_Used.get(1), IL.Brain_Tape.get(1), 2, 10000000);
		ItemsGT.addNEIRedirects(IL.Brain_Tape.get(1), IL.Brain_Tape_Used.get(1));
		BooksGT.BOOK_REGISTER.put(IL.Brain_Tape     , (byte)59);
		BooksGT.BOOK_REGISTER.put(IL.Brain_Tape_Used, (byte)59);
		addItemBehavior(12008, tBehaviour);
		addItemBehavior(12009, tBehaviour);
		
		
		
		IL.Key_Iron                        .set(addItem(30000, "Iron Key"    , "", OD.itemKey, new OreDictItemData(ANY.Iron  , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Iron    .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(ANY.Iron));
		IL.Key_Gold                        .set(addItem(30001, "Gold Key"    , "", OD.itemKey, new OreDictItemData(MT.Au     , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Gold    .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Au));
		IL.Key_Copper                      .set(addItem(30002, "Copper Key"  , "", OD.itemKey, new OreDictItemData(ANY.Cu    , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Copper  .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(ANY.Cu));
		IL.Key_Tin                         .set(addItem(30003, "Tin Key"     , "", OD.itemKey, new OreDictItemData(MT.Sn     , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Tin     .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Sn));
		IL.Key_Bronze                      .set(addItem(30004, "Bronze Key"  , "", OD.itemKey, new OreDictItemData(MT.Bronze , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Bronze  .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Bronze));
		IL.Key_Brass                       .set(addItem(30005, "Brass Key"   , "", OD.itemKey, new OreDictItemData(MT.Brass  , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Brass   .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Brass));
		IL.Key_Silver                      .set(addItem(30006, "Silver Key"  , "", OD.itemKey, new OreDictItemData(MT.Ag     , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Silver  .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Ag));
		IL.Key_Platinum                    .set(addItem(30007, "Platinum Key", "", OD.itemKey, new OreDictItemData(MT.Pt     , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Platinum.get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Pt));
		IL.Key_Lead                        .set(addItem(30008, "Lead Key"    , "", OD.itemKey, new OreDictItemData(MT.Pb     , U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Lead    .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Pb));
		IL.Key_Plastic                     .set(addItem(30009, "Plastic Key" , "", OD.itemKey, new OreDictItemData(MT.Plastic, U4), Behavior_Key.INSTANCE, TC.stack(TC.MACHINA, 1))); CR.shaped(IL.Key_Plastic .get(3), CR.DEF_NCC, "fPx", 'P', OP.plate.dat(MT.Plastic));
		
		// In order to be able to hide Keys inside Bookshelves
		for (IItemContainer tContainer : IL.KEYS) BooksGT.BOOK_REGISTER.put(tContainer, (byte)1);
	}
	
	@Override public int getRenderPasses(int aMetaData) {return UT.Code.inside(11000, 11004, aMetaData) ? 2 : 1;}
	@Override public boolean requiresMultipleRenderPasses() {return T;}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass) {
		if (aRenderPass == 0) return getIconIndex(aStack);
		EntityPlayer aPlayer = GT_API.api_proxy.getThePlayer();
		if (aPlayer == null) return getIconIndex(aStack);
		ChunkCoordinates aTarget;
		switch(ST.meta_(aStack)) {
		case 11000: return Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361260+aPlayer.rotationYaw)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
		case 11001: return Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361260-aPlayer.rotationYaw)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
		case 11003: return Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361170+aPlayer.rotationYaw-Math.atan2(-aPlayer.posZ, -aPlayer.posX)*180/Math.PI)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
		case 11002: aTarget = aPlayer.worldObj.getSpawnPoint(); break;
		case 11004: aTarget = LAST_DEATH_OF_THE_PLAYER; break;
		default: return getIconIndex(aStack);
		}
		return aTarget == null ? Textures.ItemIcons.COMPASS[(int)(CLIENT_TIME % Textures.ItemIcons.COMPASS.length)].getIcon(0) : Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361170+aPlayer.rotationYaw-Math.atan2(aTarget.posZ+0.5-aPlayer.posZ, aTarget.posX+0.5-aPlayer.posX)*180/Math.PI)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
	}
	
	@Override
	public IIcon getIconFromDamageForRenderPass(int aMetaData, int aRenderPass) {
		if (aRenderPass == 0) return getIconFromDamage(aMetaData);
		EntityPlayer aPlayer = GT_API.api_proxy.getThePlayer();
		if (aPlayer == null) return getIconFromDamage(aMetaData);
		ChunkCoordinates aTarget;
		switch(aMetaData) {
		case 11000: return Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361260+aPlayer.rotationYaw)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
		case 11001: return Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361260-aPlayer.rotationYaw)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
		case 11003: return Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361170+aPlayer.rotationYaw-Math.atan2(-aPlayer.posZ, -aPlayer.posX)*180/Math.PI)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
		case 11002: aTarget = aPlayer.worldObj.getSpawnPoint(); break;
		case 11004: aTarget = LAST_DEATH_OF_THE_PLAYER; break;
		default: return getIconFromDamage(aMetaData);
		}
		return aTarget == null ? Textures.ItemIcons.COMPASS[(int)(CLIENT_TIME % Textures.ItemIcons.COMPASS.length)].getIcon(0) : Textures.ItemIcons.COMPASS[UT.Code.roundDown(0.5+Textures.ItemIcons.COMPASS.length*(361170+aPlayer.rotationYaw-Math.atan2(aTarget.posZ+0.5-aPlayer.posZ, aTarget.posX+0.5-aPlayer.posX)*180/Math.PI)/360)%Textures.ItemIcons.COMPASS.length].getIcon(0);
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack aStack) {
		short aMeta = ST.meta_(aStack);
		if (aMeta >=  1000 && aMeta <=  1999) return make(  999);
		if (aMeta >   2000 && aMeta <=  2099) return make( 2000);
		if (aMeta >   2100 && aMeta <=  2199) return make( 2100);
		if (aMeta >   2200 && aMeta <=  2299) return make( 2200);
		if (aMeta >   2300 && aMeta <=  2399) return make( 2300);
		if (aMeta >   2400 && aMeta <=  2499) return make( 2400);
		if (aMeta >   2500 && aMeta <=  2599) return make( 2500);
		if (aMeta >   2600 && aMeta <=  2699) return make( 2600);
		if (aMeta >   2700 && aMeta <=  2799) return make( 2700);
		if (aMeta >   2800 && aMeta <=  2899) return make( 2800);
		if (aMeta >   2900 && aMeta <=  2999) return make( 2900);
		if (aMeta ==  5005 || aMeta ==  5008) return make(aMeta - 1);
		if (aMeta ==  5006 || aMeta ==  5009) return make(aMeta - 2);
		if (aMeta ==  5011) return make( 5013);
		if (aMeta ==  5012) return make( 5010);
		return super.getContainerItem(aStack);
	}
	
	@Override
	public boolean canBeStoredInToolbox(ItemStack aStack) {
		short aMeta = ST.meta_(aStack);
		return aMeta >= 9000 || aMeta == 6000 || aMeta == 6001 || aMeta == 6002 || (aMeta >= 1000 && aMeta < 5999);
	}
	
	@Override
	public int getDefaultStackLimit(ItemStack aStack) {
		short aMeta = ST.meta_(aStack);
		switch (aMeta) {
		case 5002: case 5005: case 5008: case 5011: case 5014: case 5015: case 9001: case 9002: return 1;
		default: return (aMeta >= 2000 && aMeta < 3000) ? 1 : 64;
		}
	}
	
	@Override
	public ItemStack getRotten(ItemStack aStack) {
		switch(ST.meta_(aStack)) {
		case 2002: return ST.make(this, aStack.stackSize, 2011, aStack.getTagCompound());
		case 2102: return ST.make(this, aStack.stackSize, 2111, aStack.getTagCompound());
		case 2202: return ST.make(this, aStack.stackSize, 2211, aStack.getTagCompound());
		case 2302: return ST.make(this, aStack.stackSize, 2311, aStack.getTagCompound());
		case 2402: return ST.make(this, aStack.stackSize, 2411, aStack.getTagCompound());
		case 2502: return ST.make(this, aStack.stackSize, 2511, aStack.getTagCompound());
		case 2602: return ST.make(this, aStack.stackSize, 2611, aStack.getTagCompound());
		case 2702: return ST.make(this, aStack.stackSize, 2711, aStack.getTagCompound());
		case 2802: return ST.make(this, aStack.stackSize, 2811, aStack.getTagCompound());
		case 2902: return ST.make(this, aStack.stackSize, 2911, aStack.getTagCompound());
		}
		return aStack;
	}
	
	@Override public ItemStack getRotten(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {return getRotten(aStack);}
}
