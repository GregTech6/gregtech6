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

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.*;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 * 
 * Here is basically everything that I want to change to some better location later.
 */
public class Loader_Recipes_Hints implements Runnable {
	@Override public void run() {
		MultiTileEntityRegistry aRegistry = MultiTileEntityRegistry.getRegistry("gt.multitileentity");
		
		RM.Hammer.mRecipeMachineList.add(ToolsGT.sMetaTool.make(ToolsGT.HARDHAMMER));
		RM.Chisel.mRecipeMachineList.add(ToolsGT.sMetaTool.make(ToolsGT.CHISEL));
		RM.Chisel.mRecipeMachineList.add(ToolsGT.sMetaTool.make(ToolsGT.POCKET_CHISEL));
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(OP.dust.mat(MT.OREMATS.Cinnabar, 3), "Throw three Units of Cinnabar into Crucible")
		, IL.Ceramic_Crucible.getWithName(1, "Wait until it melts into Mercury")
		, IL.Bottle_Empty.getWithName(1, "Rightclick the Crucible with an Empty Bottle")
		, IL.TC_Shimmerleaf.getWithName(1, "Or just throw a Shimmerleaf into it")
		, ST.make(aRegistry.getItem(1199), "Heat up the Crucible using a Burning Box")
		, ST.make(Blocks.redstone_ore, 1, 0, "Using a Club to mine Vanilla Redstone Ore gives Cinnabar")
		), ST.array(IL.Bottle_Mercury.get(1), ST.make(OP.ingot.mat(MT.Hg, 1), "Pouring this into Molds only works with additional Cooling!"), ST.make(OP.nugget.mat(MT.Hg, 1), "Pouring this into Molds only works with additional Cooling!")), null, ZL_LONG, FL.array(MT.Hg.liquid(1, T)), FL.array(MT.Hg.liquid(1, T)), 0, 0, 0);
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  IL.Ceramic_Mold.getWithName(1, "Don't forget to shape the Mold to pour it")
		, IL.Ceramic_Crucible.getWithName(1, "Wait until it all turns into Steel")
		, ST.make(aRegistry.getItem(1302), "Point a running Engine into the Crucible to blow Air")
		, ST.make(OP.ingot.mat(MT.Fe, 1), "Throw some Iron into Crucible. Do not forget to leave space for Air!")
		, ST.make(aRegistry.getItem(1199), "Heat up the Crucible using a Burning Box")
		, ST.make(OP.ingot.mat(MT.WroughtIron, 1), "Or throw Wrought Iron into the Crucible, either works")
		), ST.array(OP.dust.mat(MT.Steel, 1), OP.ingot.mat(MT.Steel, 1), OP.plate.mat(MT.Steel, 1), OP.scrapGt.mat(MT.Steel, 1), OP.stick.mat(MT.Steel, 1), OP.gearGt.mat(MT.Steel, 1)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(OP.ingot.mat(MT.Zn, 1), "Dump some Zinc into the Crucible")
		, IL.Ceramic_Faucet.getWithName(1, "Pour Zinc using a Faucet attached to the Crucible")
		, IL.Ceramic_Crucible.getWithName(1, "Wait until the Zinc is molten")
		, ST.make(OP.plate.mat(MT.Steel, 1), "Put your Steel Object into the Bathing Pot")
		, ST.make(aRegistry.getItem(32707), "Place the Bathing Pot (Table) below the Faucet")
		, ST.make(aRegistry.getItem(1199), "Heat up the Crucible using a Burning Box")
		), ST.array(OP.plate.mat(MT.SteelGalvanized, 1), OP.plateCurved.mat(MT.SteelGalvanized, 1), OP.stick.mat(MT.SteelGalvanized, 1), OP.casingSmall.mat(MT.SteelGalvanized, 1), OP.gearGt.mat(MT.SteelGalvanized, 1), OP.screw.mat(MT.SteelGalvanized, 1)), null, ZL_LONG, FL.array(MT.Zn.liquid(1, T)), FL.array(MT.Zn.liquid(1, T)), 0, 0, 0);
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(aRegistry     .getItem(20271), "Get a cheap Printer and power it")
		, ST.make(ItemsGT.BOTTLES    , 1, 32116, "Fill it with expensive proprietary Ink")
		, ST.make(Items.book             , 1, 0, "Insert a basic empty Book to get a Manual")
		, NI
		, NI
		, NI
		), ST.array(ST.book("Manual_Printer", ST.make(Items.written_book, 1, 0))), null, ZL_LONG, FL.array(DYE_FLUIDS_CHEMICAL[DYE_INDEX_Black]), ZL_FS, 0, 0, 0);
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(aRegistry     .getItem( 7000), "Any of the GT6 Bookshelves")
		, ST.make(Blocks.enchanting_table, 1, 0, "Place the Shelves around the Enchanting Table")
		, ST.make(Items.book             , 1, 0, "Fill the Shelves with ANYTHING that looks like a Book or Scroll")
		, ST.make(aRegistry     .getItem( 7123), "Any of the GT6 Bookshelves")
		, ST.make(Items.experience_bottle, 1, 0, "Use your XP like normal")
		, ST.make(Items.enchanted_book   , 1, 0, "Even counts DOUBLE if it is a magical thing!")
		), ST.array(ST.make(Items.enchanted_book, 1, 0, "Get a more compact Enchantment Power Bonus!")), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(aRegistry     .getItem(32714), "Have a Barrel or Drum filled with Liquid")
		, ST.make(aRegistry     .getItem(32728), "Put a Tap on the Barrel/Drum")
		, IL.Bottle_Empty        .getWithName(1, "Rightclick the Tap with a Fluid Container to fill it")
		, ST.make(Items.cauldron         , 1, 0, "With enough Water, the Tap can fill vanilla Cauldrons below")
		, ST.make(aRegistry     .getItem(32740), "Or have another top open Fluid Container below the Tap")
		, ST.make(aRegistry     .getItem(32706), "Mixing Bowls and Bathing Pots are top open too!")
		), ST.array(ST.make(Items.potionitem, 1, 0, "Ahh, bottled Tap Water!")), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		
		
		
		// Tree Stuff
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB   , 1, 0, "Find a Rubber Tree in a Taiga Biome or similar")
		, ST.make(BlocksGT.Leaves_AB     , 1, 0, "Make sure its natural Leaves stay intact!")
		, ST.make(BlocksGT.LogA          , 1, 0, "Look for a possible Resin Hole at the Tree")
		, NI
		, NI
		, IL.Bag_Sap_Resin       .getWithName(1, "Place Resin Bag at the Hole")
		), ST.array(IL.Resin.get(1), IL.IC2_Resin.get(1)), null, ZL_LONG, ZL_FS, FL.array(FL.Resin_Rubber.make(250)), 0, 0, 0);
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 1, "Find a Maple Tree in a Forest")
		, ST.make(BlocksGT.Leaves_AB, 1, 1, "Make sure its natural Leaves stay intact!")
		, ST.make(BlocksGT.LogA, 1, 1, "Choose one of the Log Segments at the Base of the Tree")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.HAND_DRILL, "Drill only ONE Hole! Bronze is easiest to make this Tool")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.DRILL_LV  , "Drill only ONE Hole! Electric might be a bit Late-game")
		, IL.Bag_Sap_Resin.getWithName(1, "Place Sap Bag at the drilled Hole")
		), ZL_IS, null, ZL_LONG, ZL_FS, FL.array(FL.Sap_Maple.make(250)), 0, 0, 0);
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 2, "Find a Willow Tree in the Swamp")
		, ST.make(BlocksGT.Leaves_AB, 1, 2, "Harvest its Leaves for Sticks")
		, ST.make(BlocksGT.LogA, 1, 2, "Use its Logs in a Coke Oven for double the Charcoal")
		, NI
		, NI
		, NI
		), ST.array(OP.stick.mat(MT.WOODS.Willow, 1), OP.gem.mat(MT.Charcoal, 2), OP.ingot.mat(MT.Charcoal, 2)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 3, "Find a Blue Mahoe Tree in the Jungle")
		, ST.make(BlocksGT.Leaves_AB, 1, 3, "Harvest its Leaves for Sticks")
		, ST.make(BlocksGT.LogA, 1, 3, "Nothing special about its Logs")
		, NI
		, NI
		, NI
		), ST.array(OP.stick.mat(MT.WOODS.BlueMahoe, 1)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 4, "Find a Hazel Tree in the Plains")
		, ST.make(BlocksGT.Leaves_AB, 1, 4, "Harvest its Leaves for Hazelnuts and Sticks")
		, ST.make(BlocksGT.LogB, 1, 0, "Nothing special about its Logs")
		, NI
		, NI
		, NI
		), ST.array(IL.Food_Hazelnut.get(1), OP.stick.mat(MT.WOODS.Hazel, 1)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 5, "Find a Cinnamon Tree in the Jungle")
		, ST.make(BlocksGT.Leaves_AB, 1, 5, "Nothing special about its Leaves")
		, ST.make(BlocksGT.LogB, 1, 1, "The Bark does not regrow! Plant a new Tree for more")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.KNIFE, "Remove its edible Bark")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.AXE  , "Remove its edible Bark")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.SAW  , "Remove its edible Bark")
		), ST.array(OM.dust(MT.Cinnamon), IL.Food_Cinnamon.get(1), IL.HaC_Cinnamon.get(1)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 6, "Find a Coconut Tree near the Ocean")
		, ST.make(BlocksGT.Leaves_AB, 1, 6, "Harvest its Leaves for Coconuts")
		, ST.make(BlocksGT.LogB, 1, 2, "Nothing special about its Logs")
		, NI
		, NI
		, NI
		), ST.array(IL.Food_Coconut.get(1)), null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_AB, 1, 7, "Find a super rare Rainbow Tree")
		, ST.make(BlocksGT.Leaves_AB, 1, 7, "Make sure its natural Leaves stay intact!")
		, ST.make(BlocksGT.LogB, 1, 3, "Choose one of the Log Segments at the Base of the Tree")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.HAND_DRILL, "Drill only one Hole into the Tree")
		, ST.make(ToolsGT.sMetaTool, 1, ToolsGT.DRILL_LV  , "Drill only one Hole into the Tree")
		, IL.Bag_Sap_Resin.getWithName(1, "Place Sap Bag at the drilled Hole")
		), ZL_IS, null, ZL_LONG, ZL_FS, FL.array(FL.Sap_Rainbow.make(250)), 0, 0, 0);
		
		RM.DidYouKnow.addFakeRecipe(F, ST.array(
		  ST.make(BlocksGT.Saplings_CD, 1, 0, "Find a Blue Spruce Tree in the Mountains")
		, ST.make(BlocksGT.Leaves_CD, 1, 0, "Nothing special about its Leaves")
		, ST.make(BlocksGT.LogC, 1, 0, "Nothing special about its Logs")
		, NI
		, NI
		, NI
		), ZL_IS, null, ZL_LONG, ZL_FS, ZL_FS, 0, 0, 0);
	}
}
