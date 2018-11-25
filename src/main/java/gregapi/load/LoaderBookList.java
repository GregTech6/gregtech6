/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregapi.load;

import static gregapi.data.CS.*;

import gregapi.data.CS.BooksGT;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class LoaderBookList implements Runnable {
	@Override
	public void run() {
		BooksGT.BOOK_REGISTER.put(IL.Circuit_Selector.wild(1), (byte) 43);
		
		BooksGT.BOOK_REGISTER.put(ST.make(Items.paper           , 1,   W), (byte) 25);
		BooksGT.BOOK_REGISTER.put(ST.make(Items.name_tag        , 1,   W), (byte) 25);
		BooksGT.BOOK_REGISTER.put(ST.make(Items.map             , 1,   W), (byte) 25);
		BooksGT.BOOK_REGISTER.put(ST.make(Items.filled_map      , 1,   W), (byte) 26);
		BooksGT.BOOK_REGISTER.put(ST.make(Items.book            , 1,   W), (byte)  1);
		BooksGT.BOOK_REGISTER.put(ST.make(Items.writable_book   , 1,   W), (byte)  1);
		BooksGT.BOOK_REGISTER.put(ST.make(Items.written_book    , 1,   W), (byte)  1);
		BooksGT.BOOK_REGISTER.put(ST.make(Items.enchanted_book  , 1,   W), (byte)  2);
		BooksGT.BOOK_REGISTER.put(ST.make(Items.item_frame      , 1,   W), (byte) 34);
		BooksGT.BOOK_REGISTER.put(ST.make(Items.painting        , 1,   W), (byte) 34);
		BooksGT.BOOK_REGISTER.put(ST.make(Blocks.wooden_button  , 1,   W), (byte)  1);
		BooksGT.BOOK_REGISTER.put(ST.make(Blocks.stone_button   , 1,   W), (byte)  2);
		BooksGT.BOOK_REGISTER.put(ST.make(Blocks.lever          , 1,   W), (byte)  1);
		BooksGT.BOOK_REGISTER.put(ST.make(Blocks.redstone_torch , 1,   W), (byte)  2);
		BooksGT.BOOK_REGISTER.put(ST.make(Blocks.cobblestone    , 1,   W), (byte)255);
		
		if (MD.FR.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "catalogue"                            , 1,   W), (byte) 32);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,   0), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,   1), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,   2), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,   3), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,   4), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,   5), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,   6), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,   7), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,   8), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,   9), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  10), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  11), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  12), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  13), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  14), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  15), (byte) 33);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  16), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  17), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  18), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  19), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  20), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  21), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  22), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  23), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  24), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  25), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  26), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  27), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  28), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  29), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  30), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  31), (byte) 51);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  32), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  33), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  34), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  35), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  36), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  37), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  38), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  39), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  40), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  41), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  42), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  43), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  44), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  45), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  46), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,  47), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "letters"                              , 1,   W), (byte) 52);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "frameUntreated"                       , 1,   W), (byte) 34);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "frameImpregnated"                     , 1,   W), (byte) 34);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "frameProven"                          , 1,   W), (byte) 34);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FR, "researchNote"                         , 1,   W), (byte) 25);
		}
		if (MD.BINNIE_BEE.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BINNIE_BEE, "hiveFrame.clay"               , 1,   W), (byte) 34);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BINNIE_BEE, "hiveFrame.cocoa"              , 1,   W), (byte) 34);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BINNIE_BEE, "hiveFrame.cage"               , 1,   W), (byte) 34);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BINNIE_BEE, "hiveFrame.soul"               , 1,   W), (byte) 34);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BINNIE_BEE, "hiveFrame.debug"              , 1,   W), (byte) 34);
		}
		if (MD.BINNIE_BOTANY.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BINNIE_BOTANY, "encylopedia"               , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BINNIE_BOTANY, "encylopediaIron"           , 1,   W), (byte)  1);
		}
		if (MD.BC.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BC, "mapLocation"                          , 1,   W), (byte) 26);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BC, "list"                                 , 1,   W), (byte) 26);
		}
		if (MD.BC_BUILDERS.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BC_BUILDERS, "blueprintItem"               , 1,   W), (byte) 28);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BC_BUILDERS, "templateItem"                , 1,   W), (byte) 28);
		}
		if (MD.BC_ROBOTICS.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BC_ROBOTICS, "redstone_board"              , 1,   W), (byte) 26);
		}
		if (MD.BOTA.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BOTA, "lexicon"                            , 1,   W), (byte) 42);
		}
		if (MD.ARS.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "bookAffinity"                        , 1,   0), (byte)  3);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "bookAffinity"                        , 1,   1), (byte) 49);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "bookAffinity"                        , 1,   2), (byte)  7);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "bookAffinity"                        , 1,   3), (byte)  5);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "bookAffinity"                        , 1,   4), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "bookAffinity"                        , 1,   5), (byte)  4);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "bookAffinity"                        , 1,   6), (byte)  4);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "bookAffinity"                        , 1,   7), (byte)  7);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "bookAffinity"                        , 1,   8), (byte)  6);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "bookAffinity"                        , 1,   9), (byte)  5);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "bookAffinity"                        , 1,  10), (byte)  3);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "evilBook"                            , 1,   W), (byte)  3);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "ArcaneCompendium"                    , 1,   W), (byte) 49);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "arcane_spellbook"                    , 1,   W), (byte)  3);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "spellBook"                           , 1,   W), (byte)  3);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "spellParchment"                      , 1,   W), (byte) 18);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "playerjournal"                       , 1,   W), (byte)  1);
			for (int i = 0; i < 17; i++)
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "rune"                                , 1,   i), (byte) 15);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ARS, "rune"                                , 1,  20), (byte) 15);
		}
		if (MD.TC.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TC, "ItemThaumometer"                      , 1,   W), (byte) 56);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TC, "ItemThaumonomicon"                    , 1,   W), (byte) 13);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TC, "ItemEldritchObject"                   , 1,   1), (byte) 14);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TC, "ItemEldritchObject"                   , 1,   2), (byte) 15);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TC, "ItemResearchNotes"                    , 1,   W), (byte) 18);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TC, "ItemResource"                         , 1,  13), (byte) 26);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TC, "ItemResource"                         , 1,   9), (byte) 25);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TC, "ArcaneDoorKey"                        , 1,   W), (byte)  1);
		}
		if (MD.PE.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.PE, "item.pe_manual"                       , 1,   W), (byte)  3);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.PE, "item.pe_tome"                         , 1,   W), (byte)  6);
		}
		if (MD.TE_FOUNDATION.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TE_FOUNDATION, "lexicon"                   , 1,   W), (byte)  7);
		}
		if (MD.TE.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TE, "diagram"                              , 1,   0), (byte) 28);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TE, "diagram"                              , 1,   1), (byte) 26);
		}
		if (MD.TF.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TF, "item.towerKey"                        , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TF, "item.emptyMagicMap"                   , 1,   W), (byte) 25);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TF, "item.emptyMazeMap"                    , 1,   W), (byte) 25);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TF, "item.emptyOreMap"                     , 1,   W), (byte) 25);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TF, "item.magicMap"                        , 1,   W), (byte) 26);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TF, "item.mazeMap"                         , 1,   W), (byte) 26);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TF, "item.oreMap"                          , 1,   W), (byte) 26);
		}
		if (MD.BTL.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BTL, "manual"                              , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BTL, "manualHL"                            , 1,   W), (byte)  6);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BTL, "lore"                                , 1,   W), (byte) 25);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BTL, "unknownGeneric"                      , 1,  35), (byte) 25);
		}
		if (MD.ATUM.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ATUM, "item.loot"                          , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ATUM, "item.scroll"                        , 1,   W), (byte) 18);
		}
		if (MD.TROPIC.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TROPIC, "encTropica"                       , 1,   W), (byte)  1);
		}
		if (MD.IHL.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.IHL, "guidebook"                           , 1,   W), (byte) 48);
		}
		if (MD.FZ.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FZ, "colossusGuide"                        , 1,   W), (byte) 26);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FZ, "docbook"                              , 1,   W), (byte) 39);
		}
		if (MD.AA.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AA, "itemBooklet"                          , 1,   W), (byte)  7);
		}
		if (MD.RC.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.RC, "routing.table"                        , 1,   W), (byte) 19);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.RC, "routing.ticket"                       , 1,   W), (byte) 26);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.RC, "routing.ticket.gold"                  , 1,   W), (byte) 26);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.RC, "emblem"                               , 1,   W), (byte) 27);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.RC, "tool.signal.label"                    , 1,   W), (byte) 25);
		}
		if (MD.RoC.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.RoC, "rotarycraft_item_handbook"           , 1,   W), (byte) 24);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.RoC, "rotarycraft_item_slide"              , 1,   W), (byte) 27);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.RoC, "rotarycraft_item_key"                , 1,   W), (byte)  1);
		}
		if (MD.ReC.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ReC, "reactorcraft_item_book"              , 1,   W), (byte) 24);
		}
		if (MD.ElC.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ElC, "electricraft_item_book"              , 1,   W), (byte) 24);
		}
		if (MD.CrC.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CrC, "chromaticraft_item_help"             , 1,   W), (byte) 24);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CrC, "chromaticraft_item_fragment"         , 1,   W), (byte) 28);
		}
		if (MD.IE.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.IE, "blueprint"                            , 1,   W), (byte) 28);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.IE, "tool"                                 , 1,   3), (byte) 41);
		}
		if (MD.ExU.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ExU, "scanner"                             , 1,   W), (byte) 43);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ExU, "divisionSigil"                       , 1,   W), (byte) 50);
		}
		if (MD.RT.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.RT, "spectreKey"                           , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.RT, "opSpectreKey"                         , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.RT, "enderLetter"                          , 1,   W), (byte) 33);
		}
		if (MD.CC.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CC, "pocketComputer"                       , 1,   W), (byte) 43);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CC, "pocketComputer"                       , 1,   1), (byte) 44);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CC, "printout"                             , 1,   W), (byte) 26);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CC, "printout"                             , 1,   2), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CC, "diskExpanded"                         , 1,   W), (byte) 35);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CC, "disk"                                 , 1,   W), (byte) 35);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CC, "treasureDisk"                         , 1,   W), (byte) 35);
		}
		if (MD.OC.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.OC, "item"                                 , 1,   4), (byte) 35);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.OC, "item"                                 , 1,  98), (byte) 40);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.OC, "item"                                 , 1,  68), (byte) 43);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.OC, "item"                                 , 1,  74), (byte) 43);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.OC, "item"                                 , 1,  92), (byte) 43);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.OC, "item"                                 , 1,  93), (byte) 43);
		}
		if (MD.OB.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.OB, "infoBook"                             , 1,   W), (byte)  5);
		}
		if (MD.PR.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.PR, "projectred.core.datacard"             , 1,   W), (byte) 35);
		}
		if (MD.PR_EXPANSION.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.PR_EXPANSION, "projectred.expansion.plan"  , 1,   W), (byte) 28);
		}
		if (MD.AE.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ToolPortableCell"                , 1,   W), (byte) 47);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ToolWirelessTerminal"            , 1,   W), (byte) 47);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemViewCell"                    , 1,   W), (byte) 47);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ToolMemoryCard"                  , 1,   W), (byte) 47);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ToolBiometricCard"               , 1,   W), (byte) 37);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemBasicStorageCell.1k"         , 1,   W), (byte) 46);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemBasicStorageCell.4k"         , 1,   W), (byte) 46);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemBasicStorageCell.16k"        , 1,   W), (byte) 46);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemBasicStorageCell.64k"        , 1,   W), (byte) 46);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemCreativeStorageCell"         , 1,   W), (byte) 46);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemSpatialStorageCell.2Cubed"   , 1,   W), (byte) 46);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemSpatialStorageCell.16Cubed"  , 1,   W), (byte) 46);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemSpatialStorageCell.128Cubed" , 1,   W), (byte) 46);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemMultiMaterial"               , 1,  39), (byte) 46);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemMultiMaterial"               , 1,  13), (byte) 38);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemMultiMaterial"               , 1,  14), (byte) 38);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemMultiMaterial"               , 1,  15), (byte) 38);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemMultiMaterial"               , 1,  19), (byte) 38);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.AE, "item.ItemMultiMaterial"               , 1,  21), (byte) 38);
		}
		if (MD.FM.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FM, "MeteorTimeDet"                        , 1,   W), (byte) 43);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FM, "MeteorProxDet"                        , 1,   W), (byte) 43);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.FM, "MeteorCrashDet"                       , 1,   W), (byte) 44);
		}
		if (MD.GC.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.GC, "item.key"                             , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.GC, "item.schematic"                       , 1,   W), (byte) 28);
		}
		if (MD.GC_PLANETS.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.GC_PLANETS, "item.key"                     , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.GC_PLANETS, "item.schematic"               , 1,   W), (byte) 28);
		}
		if (MD.GC_GALAXYSPACE.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.GC_GALAXYSPACE, "item.TierKeys"            , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.GC_GALAXYSPACE, "item.ItemSchematics"      , 1,   W), (byte) 28);
		}
		if (MD.CookBook.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CookBook, "recipebook"                     , 1,   0), (byte)  6);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CookBook, "recipebook"                     , 1,   1), (byte)  5);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CookBook, "recipebook"                     , 1,   3), (byte)  3);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CookBook, "recipebook"                     , 1,   W), (byte)  4);
		}
		if (MD.MaCu.mLoaded && MD.ENCHIRIDION.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MaCu, "guide"                              , 1,   0), (byte)  3);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MaCu, "guide"                              , 1,   1), (byte)  7);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MaCu, "guide"                              , 1,   2), (byte)  3);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MaCu, "guide"                              , 1,   3), (byte)  8);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MaCu, "guide"                              , 1,   4), (byte) 49);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MaCu, "guide"                              , 1,   5), (byte) 48);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MaCu, "scanner"                            , 1,   W), (byte) 43);
		}
		if (MD.DE.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.DE, "key"                                  , 1,   W), (byte)  1);
		}
		if (MD.LycM.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.LycM, "soulkey"                            , 1,   W), (byte)  1);
		}
		if (MD.CrGu.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.CrGu, "craftguide_item"                    , 1,   W), (byte) 17);
		}
		if (MD.MYST.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MYST, "unlinkedbook"                       , 1,   W), (byte)  3);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MYST, "linkbook"                           , 1,   W), (byte)  6);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MYST, "agebook"                            , 1,   W), (byte) 48);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MYST, "page"                               , 1,   W), (byte) 25);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MYST, "portfolio"                          , 1,   W), (byte) 26);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MYST, "booster"                            , 1,   W), (byte) 27);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MYST, "folder"                             , 1,   W), (byte) 28);
		}
		if (MD.WARPBOOK.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WARPBOOK, "warpbook"                       , 1,   W), (byte) 49);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WARPBOOK, "warppage"                       , 1,   W), (byte) 26);
		}
		if (MD.LOSTBOOKS.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.LOSTBOOKS, "randomBook"                    , 1,   W), (byte) 53);
		}
		if (MD.EUREKA.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.EUREKA, "engineeringDiary"                 , 1,   W), (byte)  3);
		}
		if (MD.ENCHIRIDION.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ENCHIRIDION, "items"                       , 1,   W), (byte)  4);
		}
		if (MD.ENCHIRIDION2.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.ENCHIRIDION2, "book"                       , 1,   W), (byte)  4);
		}
		if (MD.SmAc.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.SmAc, "sa.achievementBook"                 , 1,   W), (byte) 49);
		}
		if (MD.HQM.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.HQM, "quest_book"                          , 1,   W), (byte)  1);
		}
		if (MD.MNTL.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MNTL, "mantleBook"                         , 1,   W), (byte)  7);
		}
		if (MD.TiC.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TiC, "manualBook"                          , 1,   W), (byte)  4);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TiC, "manualBook"                          , 1,   0), (byte) 10);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TiC, "manualBook"                          , 1,   1), (byte)  5);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TiC, "manualBook"                          , 1,   2), (byte)  3);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TiC, "manualBook"                          , 1,   3), (byte)  7);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TiC, "manualBook"                          , 1,   4), (byte)  6);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.TiC, "materials"                           , 1,   0), (byte) 25);
		}
		if (MD.MoCr.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MoCr, "key"                                , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MoCr, "scrollofsale"                       , 1,   W), (byte) 18);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MoCr, "scrollofowner"                      , 1,   W), (byte) 18);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.MoCr, "scrolloffreedom"                    , 1,   W), (byte) 18);
		}
		if (MD.HOWL.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.HOWL, "lycanthropeBook"                    , 1,   W), (byte) 20);
		}
		if (MD.WTCH.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1,  46), (byte) 21);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1,  47), (byte) 21);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1,  48), (byte) 21);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1,  49), (byte) 21);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1,  54), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1,  81), (byte) 21);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1,  84), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1, 106), (byte) 16);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1, 107), (byte) 21);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1, 127), (byte) 21);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1, 140), (byte) 18);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1, 141), (byte) 18);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1, 142), (byte) 18);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1, 143), (byte) 18);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1, 144), (byte) 18);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1, 145), (byte) 18);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1, 146), (byte) 18);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "ingredient"                         , 1, 160), (byte) 25);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "biomenote"                          , 1,   W), (byte) 26);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "bookbiomes2"                        , 1,   W), (byte) 16);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "cauldronbook"                       , 1,   W), (byte) 22);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.WTCH, "vampirebook"                        , 1,   W), (byte) 23);
		}
		if (MD.BbLC.mLoaded) {
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BbLC, "item.SlottedBook"                   , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BbLC, "item.BiblioRedBook"                 , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BbLC, "item.BigBook"                       , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BbLC, "item.TesterItem"                    , 1,   W), (byte)  1);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BbLC, "item.StockroomCatalog"              , 1,   W), (byte)  6);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BbLC, "item.AtlasBook"                     , 1,   W), (byte) 16);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BbLC, "item.RecipeBook"                    , 1,   W), (byte) 17);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BbLC, "item.BiblioClipboard"               , 1,   W), (byte) 29);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BbLC, "item.PrintPlate"                    , 1,   W), (byte) 31);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BbLC, "item.EnchantedPlate"                , 1,   W), (byte) 31);
			BooksGT.BOOK_REGISTER.put(ST.make(MD.BbLC, "item.AtlasPlate"                    , 1,   W), (byte) 31);
		}
	}
}
