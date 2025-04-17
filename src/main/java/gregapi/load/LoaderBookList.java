/**
 * Copyright (c) 2025 GregTech-6 Team
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

import gregapi.data.CS.BooksGT;
import gregapi.data.IL;
import gregapi.data.MD;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static gregapi.data.CS.W;

public class LoaderBookList implements Runnable {
	@Override
	public void run() {
		BooksGT.BOOK_REGISTER  .put(IL.Circuit_Selector.wild(1), (byte) 43);
		
		BooksGT.BOOKS_NORMAL   .add(Items.book           , W);
		BooksGT.BOOKS_NORMAL   .add(Items.writable_book  , W);
		BooksGT.BOOKS_NORMAL   .add(Items.written_book   , W);
		BooksGT.BOOKS_ENCHANTED.add(Items.enchanted_book , W);
		BooksGT.BOOK_REGISTER  .put(Items.paper          , W, (byte) 25);
		BooksGT.BOOK_REGISTER  .put(Items.name_tag       , W, (byte) 25);
		BooksGT.BOOK_REGISTER  .put(Items.map            , W, (byte) 25);
		BooksGT.BOOK_REGISTER  .put(Items.filled_map     , W, (byte) 26);
		BooksGT.BOOK_REGISTER  .put(Items.book           , W, (byte)  1);
		BooksGT.BOOK_REGISTER  .put(Items.writable_book  , W, (byte)  1);
		BooksGT.BOOK_REGISTER  .put(Items.written_book   , W, (byte)  1);
		BooksGT.BOOK_REGISTER  .put(Items.enchanted_book , W, (byte)  2);
		BooksGT.BOOK_REGISTER  .put(Items.item_frame     , W, (byte) 34);
		BooksGT.BOOK_REGISTER  .put(Items.painting       , W, (byte) 34);
		BooksGT.BOOK_REGISTER  .put(Blocks.wooden_button , W, (byte)  1);
		BooksGT.BOOK_REGISTER  .put(Blocks.stone_button  , W, (byte)  2);
		BooksGT.BOOK_REGISTER  .put(Blocks.lever         , W, (byte)  1);
		BooksGT.BOOK_REGISTER  .put(Blocks.redstone_torch, W, (byte)  2);
		BooksGT.BOOK_REGISTER  .put(Blocks.cobblestone   , W, (byte)255);
		
		if (MD.FR.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.FR, "catalogue"                            ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "catalogue"                            ,   W, (byte) 32);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,   0, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,   1, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,   2, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,   3, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,   4, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,   5, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,   6, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,   7, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,   8, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,   9, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  10, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  11, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  12, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  13, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  14, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  15, (byte) 33);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  16, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  17, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  18, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  19, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  20, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  21, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  22, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  23, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  24, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  25, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  26, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  27, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  28, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  29, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  30, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  31, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  32, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  33, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  34, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  35, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  36, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  37, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  38, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  39, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  40, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  41, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  42, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  43, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  44, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  45, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  46, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,  47, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "letters"                              ,   W, (byte) 52);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "frameUntreated"                       ,   W, (byte) 34);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "frameImpregnated"                     ,   W, (byte) 34);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "frameProven"                          ,   W, (byte) 34);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "researchNote"                         ,   W, (byte) 25);
			BooksGT.BOOK_REGISTER  .put(MD.FR, "stamps"                               ,   W, (byte) 26);
		}
		if (MD.BINNIE_BEE.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.BINNIE_BEE, "hiveFrame.clay"               ,   W, (byte) 34);
			BooksGT.BOOK_REGISTER  .put(MD.BINNIE_BEE, "hiveFrame.cocoa"              ,   W, (byte) 34);
			BooksGT.BOOK_REGISTER  .put(MD.BINNIE_BEE, "hiveFrame.cage"               ,   W, (byte) 34);
			BooksGT.BOOK_REGISTER  .put(MD.BINNIE_BEE, "hiveFrame.soul"               ,   W, (byte) 34);
			BooksGT.BOOK_REGISTER  .put(MD.BINNIE_BEE, "hiveFrame.debug"              ,   W, (byte) 34);
		}
		if (MD.BINNIE_BOTANY.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.BINNIE_BOTANY, "encylopedia"               ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.BINNIE_BOTANY, "encylopediaIron"           ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.BINNIE_BOTANY, "encylopedia"               ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.BINNIE_BOTANY, "encylopediaIron"           ,   W, (byte)  1);
		}
		if (MD.BC.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.BC, "mapLocation"                          ,   W, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.BC, "list"                                 ,   W, (byte) 26);
		}
		if (MD.BC_BUILDERS.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.BC_BUILDERS, "blueprintItem"               ,   W, (byte) 28);
			BooksGT.BOOK_REGISTER  .put(MD.BC_BUILDERS, "templateItem"                ,   W, (byte) 28);
		}
		if (MD.BC_ROBOTICS.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.BC_ROBOTICS, "redstone_board"              ,   W, (byte) 26);
		}
		if (MD.BOTA.mLoaded) {
			BooksGT.BOOKS_ENCHANTED.add(MD.BOTA, "lexicon"                            ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.BOTA, "lexicon"                            ,   W, (byte) 42);
		}
		if (MD.ARS.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.ARS, "bookAffinity"                        ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.ARS, "playerjournal"                       ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.ARS, "evilBook"                            ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.ARS, "ArcaneCompendium"                    ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.ARS, "arcane_spellbook"                    ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.ARS, "spellBook"                           ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "bookAffinity"                        ,   W, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "bookAffinity"                        ,   1, (byte) 49);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "bookAffinity"                        ,   2, (byte)  7);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "bookAffinity"                        ,   3, (byte)  5);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "bookAffinity"                        ,   4, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "bookAffinity"                        ,   5, (byte)  4);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "bookAffinity"                        ,   6, (byte)  4);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "bookAffinity"                        ,   7, (byte)  7);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "bookAffinity"                        ,   8, (byte)  6);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "bookAffinity"                        ,   9, (byte)  5);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "bookAffinity"                        ,  10, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "playerjournal"                       ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "evilBook"                            ,   W, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "ArcaneCompendium"                    ,   W, (byte) 49);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "arcane_spellbook"                    ,   W, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "spellBook"                           ,   W, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "spellParchment"                      ,   W, (byte) 18);
			for (int i = 0; i < 17; i++)
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "rune"                                ,   i, (byte) 15);
			BooksGT.BOOK_REGISTER  .put(MD.ARS, "rune"                                ,  20, (byte) 15);
		}
		if (MD.TC.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.TC, "ItemResource"                         ,   9);
			BooksGT.BOOKS_NORMAL   .add(MD.TC, "ItemResearchNotes"                    ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.TC, "ItemThaumonomicon"                    ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.TC, "ItemEldritchObject"                   ,   1);
			BooksGT.BOOKS_ENCHANTED.add(MD.TC, "ItemEldritchObject"                   ,   2);
			BooksGT.BOOK_REGISTER  .put(MD.TC, "ItemThaumometer"                      ,   W, (byte) 56);
			BooksGT.BOOK_REGISTER  .put(MD.TC, "ItemThaumonomicon"                    ,   W, (byte) 13);
			BooksGT.BOOK_REGISTER  .put(MD.TC, "ItemEldritchObject"                   ,   1, (byte) 14);
			BooksGT.BOOK_REGISTER  .put(MD.TC, "ItemEldritchObject"                   ,   2, (byte) 15);
			BooksGT.BOOK_REGISTER  .put(MD.TC, "ItemResearchNotes"                    ,   W, (byte) 18);
			BooksGT.BOOK_REGISTER  .put(MD.TC, "ItemResource"                         ,  13, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.TC, "ItemResource"                         ,   9, (byte) 25);
			BooksGT.BOOK_REGISTER  .put(MD.TC, "ArcaneDoorKey"                        ,   W, (byte)  1);
		}
		if (MD.TECHNOM.mLoaded) {
			BooksGT.BOOKS_ENCHANTED.add(MD.TECHNOM, "ritualTome"                      ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.TECHNOM, "ritualTome"                      ,   W, (byte) 10);
		}
		if (MD.PE.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.PE, "item.pe_manual"                       ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.PE, "item.pe_tome"                         ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.PE, "item.pe_manual"                       ,   W, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.PE, "item.pe_tome"                         ,   W, (byte)  6);
		}
		if (MD.TE_FOUNDATION.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.TE_FOUNDATION, "lexicon"                   ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.TE_FOUNDATION, "lexicon"                   ,   W, (byte)  7);
		}
		if (MD.TE.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.TE, "diagram"                              ,   0, (byte) 28);
			BooksGT.BOOK_REGISTER  .put(MD.TE, "diagram"                              ,   1, (byte) 26);
		}
		if (MD.TF.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.TF, "item.towerKey"                        ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.TF, "item.emptyMagicMap"                   ,   W, (byte) 25);
			BooksGT.BOOK_REGISTER  .put(MD.TF, "item.emptyMazeMap"                    ,   W, (byte) 25);
			BooksGT.BOOK_REGISTER  .put(MD.TF, "item.emptyOreMap"                     ,   W, (byte) 25);
			BooksGT.BOOK_REGISTER  .put(MD.TF, "item.magicMap"                        ,   W, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.TF, "item.mazeMap"                         ,   W, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.TF, "item.oreMap"                          ,   W, (byte) 26);
		}
		if (MD.BTL.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.BTL, "manual"                              ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.BTL, "manualHL"                            ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.BTL, "lore"                                ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.BTL, "manual"                              ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.BTL, "manualHL"                            ,   W, (byte)  6);
			BooksGT.BOOK_REGISTER  .put(MD.BTL, "lore"                                ,   W, (byte) 25);
			BooksGT.BOOK_REGISTER  .put(MD.BTL, "unknownGeneric"                      ,  35, (byte) 25);
		}
		if (MD.ATUM.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.ATUM, "item.loot"                          ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.ATUM, "item.scroll"                        ,   W, (byte) 18);
		}
		if (MD.TROPIC.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.TROPIC, "encTropica"                       ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.TROPIC, "encTropica"                       ,   W, (byte)  1);
		}
		if (MD.CANDY.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.CANDY, "I17"                               ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.CANDY, "I20"                               ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.CANDY, "I47"                               ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.CANDY, "I67"                               ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.CANDY, "I68"                               ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.CANDY, "I96"                               ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.CANDY, "I102"                              ,   W, (byte)  1);
		}
		if (MD.ABYSSAL.mLoaded) {
			BooksGT.BOOKS_ENCHANTED.add(MD.ABYSSAL, "abyssalnomicon"                  ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.ABYSSAL, "necronomicon"                    ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.ABYSSAL, "necronomicon_omt"                ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.ABYSSAL, "necronomicon_dre"                ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.ABYSSAL, "necronomicon_cor"                ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.ABYSSAL, "portalplacer"                    ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.ABYSSAL, "dreadkey"                        ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.ABYSSAL, "portalplacerjzh"                 ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.ABYSSAL, "portalplacerdl"                  ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.ABYSSAL, "abyssalnomicon"                  ,   W, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.ABYSSAL, "necronomicon"                    ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.ABYSSAL, "necronomicon_omt"                ,   W, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.ABYSSAL, "necronomicon_dre"                ,   W, (byte)  5);
			BooksGT.BOOK_REGISTER  .put(MD.ABYSSAL, "necronomicon_cor"                ,   W, (byte)  6);
		}
		if (MD.AETHER.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.AETHER, "bronzeKey"                        ,   W, (byte)  1);
		}
		if (MD.AETHEL.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.AETHEL, "dungeon_key"                      ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.AETHEL, "lore_book"                        ,   W, (byte)  3);
		}
		if (MD.IHL.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.IHL, "guidebook"                           ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.IHL, "guidebook"                           ,   W, (byte) 48);
		}
		if (MD.FZ.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.FZ, "colossusGuide"                        ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.FZ, "docbook"                              ,   W);
			
			BooksGT.BOOK_REGISTER  .put(MD.FZ, "colossusGuide"                        ,   W, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.FZ, "docbook"                              ,   W, (byte) 39);
		}
		if (MD.AA.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.AA, "itemBooklet"                          ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.AA, "itemBooklet"                          ,   W, (byte)  7);
		}
		if (MD.SD.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.SD, "shroudKey"                            ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.SD, "upgradeLock"                          ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.SD, "personalKey"                          ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.SD, "tape"                                 ,   W, (byte) 57);
		}
		if (MD.BTRS.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.BTRS, "lock"                               ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.BTRS, "key"                                ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.BTRS, "masterKey"                          ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.BTRS, "keyring"                            ,   W, (byte)  1);
		}
		if (MD.RC.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.RC, "routing.table"                        ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.RC, "routing.table"                        ,   W, (byte) 19);
			BooksGT.BOOK_REGISTER  .put(MD.RC, "routing.ticket"                       ,   W, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.RC, "routing.ticket.gold"                  ,   W, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.RC, "emblem"                               ,   W, (byte) 27);
			BooksGT.BOOK_REGISTER  .put(MD.RC, "tool.signal.label"                    ,   W, (byte) 25);
		}
		if (MD.RoC.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.RoC, "rotarycraft_item_handbook"           ,   W);
			
			BooksGT.BOOK_REGISTER  .put(MD.RoC, "rotarycraft_item_handbook"           ,   W, (byte) 24);
			BooksGT.BOOK_REGISTER  .put(MD.RoC, "rotarycraft_item_slide"              ,   W, (byte) 27);
			BooksGT.BOOK_REGISTER  .put(MD.RoC, "rotarycraft_item_key"                ,   W, (byte)  1);
		}
		if (MD.ReC.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.ReC, "reactorcraft_item_book"              ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.ReC, "reactorcraft_item_book"              ,   W, (byte) 24);
		}
		if (MD.ElC.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.ElC, "electricraft_item_book"              ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.ElC, "electricraft_item_book"              ,   W, (byte) 24);
		}
		if (MD.CrC.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.CrC, "chromaticraft_item_help"             ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.CrC, "chromaticraft_item_help"             ,   W, (byte) 24);
			BooksGT.BOOK_REGISTER  .put(MD.CrC, "chromaticraft_item_fragment"         ,   W, (byte) 28);
		}
		if (MD.RH.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.RH, "globbypotato_rockhounding_miscItems"  ,   8);
			BooksGT.BOOKS_NORMAL   .add(MD.RH, "globbypotato_rockhounding_modBooks"   ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.RH, "globbypotato_rockhounding_miscItems"  ,   8, (byte)  7);
			BooksGT.BOOK_REGISTER  .put(MD.RH, "globbypotato_rockhounding_modBooks"   ,   W, (byte)  1);
		}
		if (MD.IE.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.IE, "tool"                                 ,   3);
			BooksGT.BOOK_REGISTER  .put(MD.IE, "tool"                                 ,   3, (byte) 41);
			BooksGT.BOOK_REGISTER  .put(MD.IE, "blueprint"                            ,   W, (byte) 28);
		}
		if (MD.ExS.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.ExS, "scanner"                             ,   W, (byte) 43);
			BooksGT.BOOK_REGISTER  .put(MD.ExS, "divisionSigil"                       ,   W, (byte) 50);
		}
		if (MD.ExU.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.ExU, "scanner"                             ,   W, (byte) 43);
			BooksGT.BOOK_REGISTER  .put(MD.ExU, "divisionSigil"                       ,   W, (byte) 50);
		}
		if (MD.RT.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.RT, "spectreKey"                           ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.RT, "opSpectreKey"                         ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.RT, "enderLetter"                          ,   W, (byte) 33);
		}
		if (MD.CC.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.CC, "pocketComputer"                       ,   W, (byte) 43);
			BooksGT.BOOK_REGISTER  .put(MD.CC, "pocketComputer"                       ,   1, (byte) 44);
			BooksGT.BOOK_REGISTER  .put(MD.CC, "printout"                             ,   W, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.CC, "printout"                             ,   2, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.CC, "diskExpanded"                         ,   W, (byte) 35);
			BooksGT.BOOK_REGISTER  .put(MD.CC, "disk"                                 ,   W, (byte) 35);
			BooksGT.BOOK_REGISTER  .put(MD.CC, "treasureDisk"                         ,   W, (byte) 35);
		}
		if (MD.OC.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.OC, "item"                                 ,  98);
			BooksGT.BOOK_REGISTER  .put(MD.OC, "item"                                 ,  98, (byte) 40);
			BooksGT.BOOK_REGISTER  .put(MD.OC, "item"                                 ,   4, (byte) 35);
			BooksGT.BOOK_REGISTER  .put(MD.OC, "item"                                 ,  68, (byte) 43);
			BooksGT.BOOK_REGISTER  .put(MD.OC, "item"                                 ,  74, (byte) 43);
			BooksGT.BOOK_REGISTER  .put(MD.OC, "item"                                 ,  92, (byte) 43);
			BooksGT.BOOK_REGISTER  .put(MD.OC, "item"                                 ,  93, (byte) 43);
		}
		if (MD.OB.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.OB, "infoBook"                             ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.OB, "infoBook"                             ,   W, (byte)  5);
		}
		if (MD.PR.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.PR, "projectred.core.datacard"             ,   W, (byte) 35);
		}
		if (MD.PR_EXPANSION.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.PR_EXPANSION, "projectred.expansion.plan"  ,   W, (byte) 28);
		}
		if (MD.AE.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ToolPortableCell"                ,   W, (byte) 47);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ToolWirelessTerminal"            ,   W, (byte) 47);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemViewCell"                    ,   W, (byte) 47);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ToolMemoryCard"                  ,   W, (byte) 47);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ToolBiometricCard"               ,   W, (byte) 37);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemBasicStorageCell.1k"         ,   W, (byte) 46);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemBasicStorageCell.4k"         ,   W, (byte) 46);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemBasicStorageCell.16k"        ,   W, (byte) 46);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemBasicStorageCell.64k"        ,   W, (byte) 46);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemCreativeStorageCell"         ,   W, (byte) 46);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemSpatialStorageCell.2Cubed"   ,   W, (byte) 46);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemSpatialStorageCell.16Cubed"  ,   W, (byte) 46);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemSpatialStorageCell.128Cubed" ,   W, (byte) 46);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemMultiMaterial"               ,  39, (byte) 46);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemMultiMaterial"               ,  13, (byte) 38);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemMultiMaterial"               ,  14, (byte) 38);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemMultiMaterial"               ,  15, (byte) 38);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemMultiMaterial"               ,  19, (byte) 38);
			BooksGT.BOOK_REGISTER  .put(MD.AE, "item.ItemMultiMaterial"               ,  21, (byte) 38);
		}
		if (MD.FM.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.FM, "MeteorTimeDet"                        ,   W, (byte) 43);
			BooksGT.BOOK_REGISTER  .put(MD.FM, "MeteorProxDet"                        ,   W, (byte) 43);
			BooksGT.BOOK_REGISTER  .put(MD.FM, "MeteorCrashDet"                       ,   W, (byte) 44);
		}
		if (MD.FSP.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.FSP, "book"                                ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.FSP, "book"                                ,   W, (byte)  1);
		}
		if (MD.SC2.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.SC2, "ItemHandbook"                        ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.SC2, "ItemLoreBook"                        ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.SC2, "ItemHandbook"                        ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.SC2, "ItemLoreBook"                        ,   W, (byte)  1);
		}
		if (MD.HBM.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.HBM, "item.bobmazon_materials"             ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.HBM, "item.bobmazon_machines"              ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.HBM, "item.bobmazon_weapons"               ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.HBM, "item.bobmazon_tools"                 ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.HBM, "item.bobmazon_hidden"                ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.HBM, "item.book_guide"                     ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.HBM, "item.book_secret"                    ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.HBM, "item.book_of_"                       ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_stone_flat"               ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_stone_plate"              ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_stone_wire"               ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_stone_circuit"            ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_iron_flat"                ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_iron_plate"               ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_iron_wire"                ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_iron_circuit"             ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_steel_flat"               ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_steel_plate"              ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_steel_wire"               ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_steel_circuit"            ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_desh_flat"                ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_desh_plate"               ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_desh_wire"                ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_desh_circuit"             ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_titanium_flat"            ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_titanium_plate"           ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_titanium_wire"            ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_titanium_circuit"         ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_obsidian_flat"            ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_obsidian_plate"           ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_obsidian_wire"            ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_obsidian_circuit"         ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_schrabidium_flat"         ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_schrabidium_plate"        ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_schrabidium_wire"         ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_schrabidium_circuit"      ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_357"                      ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_44"                       ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_9"                        ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.stamp_50"                       ,   W, (byte) 55);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.template_folder"                ,   W, (byte) 17);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.assembly_template"              ,   W, (byte) 28);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.chemistry_template"             ,   W, (byte) 28);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.fluid_identifier"               ,   W, (byte) 27);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.siren_track"                    ,   W, (byte) 36);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.bobmazon_materials"             ,   W, (byte) 48); // Technically Beige instead of Orange
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.bobmazon_machines"              ,   W, (byte)  5);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.bobmazon_weapons"               ,   W, (byte)  3); // Technically Gray instead of Black
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.bobmazon_tools"                 ,   W, (byte)  6);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.bobmazon_hidden"                ,   W, (byte)  9);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.book_guide"                     ,   W, (byte)  3); // Technically Gray instead of Black
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.book_secret"                    ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.book_of_"                       ,   W, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.key"                            ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.key_red"                        ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.key_kit"                        ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.key_fake"                       ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.pin"                            ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.padlock_rusty"                  ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.padlock"                        ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.padlock_reinforced"             ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.padlock_unbreakable"            ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.polaroid"                       ,   W, (byte) 34);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.flame_pony"                     ,   W, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.flame_opinion"                  ,   W, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.flame_politics"                 ,   W, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.HBM, "item.flame_conspiracy"               ,   W, (byte) 26);
		}
		if (MD.GC.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.GC, "item.key"                             ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.GC, "item.schematic"                       ,   W, (byte) 28);
		}
		if (MD.GC_PLANETS.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.GC_PLANETS, "item.key"                     ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.GC_PLANETS, "item.schematic"               ,   W, (byte) 28);
		}
		if (MD.GC_GALAXYSPACE.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.GC_GALAXYSPACE, "item.TierKeys"            ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.GC_GALAXYSPACE, "item.ItemSchematics"      ,   W, (byte) 28);
		}
		if (MD.CookBook.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.CookBook, "recipebook"                     ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.CookBook, "recipebook"                     ,   0, (byte)  6);
			BooksGT.BOOK_REGISTER  .put(MD.CookBook, "recipebook"                     ,   1, (byte)  5);
			BooksGT.BOOK_REGISTER  .put(MD.CookBook, "recipebook"                     ,   3, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.CookBook, "recipebook"                     ,   W, (byte)  4);
		}
		if (MD.MaCu.mLoaded && MD.ENCHIRIDION.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.MaCu, "guide"                              ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.MaCu, "guide"                              ,   0, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.MaCu, "guide"                              ,   1, (byte)  7);
			BooksGT.BOOK_REGISTER  .put(MD.MaCu, "guide"                              ,   2, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.MaCu, "guide"                              ,   3, (byte)  8);
			BooksGT.BOOK_REGISTER  .put(MD.MaCu, "guide"                              ,   4, (byte) 49);
			BooksGT.BOOK_REGISTER  .put(MD.MaCu, "guide"                              ,   5, (byte) 48);
			BooksGT.BOOK_REGISTER  .put(MD.MaCu, "scanner"                            ,   W, (byte) 43);
		}
		if (MD.DE.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.DE, "key"                                  ,   W, (byte)  1);
		}
		if (MD.LycM.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.LycM, "soulkey"                            ,   W, (byte)  1);
		}
		if (MD.CrGu.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.CrGu, "craftguide_item"                    ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.CrGu, "craftguide_item"                    ,   W, (byte) 17);
		}
		if (MD.MYST.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.MYST, "page"                               ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MYST, "portfolio"                          ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MYST, "booster"                            ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MYST, "folder"                             ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MYST, "unlinkedbook"                       ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.MYST, "linkbook"                           ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.MYST, "agebook"                            ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.MYST, "page"                               ,   W, (byte) 25);
			BooksGT.BOOK_REGISTER  .put(MD.MYST, "portfolio"                          ,   W, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.MYST, "booster"                            ,   W, (byte) 27);
			BooksGT.BOOK_REGISTER  .put(MD.MYST, "folder"                             ,   W, (byte) 28);
			BooksGT.BOOK_REGISTER  .put(MD.MYST, "unlinkedbook"                       ,   W, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.MYST, "linkbook"                           ,   W, (byte)  6);
			BooksGT.BOOK_REGISTER  .put(MD.MYST, "agebook"                            ,   W, (byte) 48);
		}
		if (MD.WARPBOOK.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.WARPBOOK, "warppage"                       ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.WARPBOOK, "warpbook"                       ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.WARPBOOK, "warppage"                       ,   W, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.WARPBOOK, "warpbook"                       ,   W, (byte) 49);
		}
		if (MD.LOSTBOOKS.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.LOSTBOOKS, "randomBook"                    ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.LOSTBOOKS, "randomBook"                    ,   W, (byte) 53);
		}
		if (MD.EUREKA.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.EUREKA, "engineeringDiary"                 ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.EUREKA, "engineeringDiary"                 ,   W, (byte)  3);
		}
		if (MD.ENCHIRIDION.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.ENCHIRIDION, "items"                       ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.ENCHIRIDION, "items"                       ,   W, (byte)  4);
		}
		if (MD.ENCHIRIDION2.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.ENCHIRIDION2, "book"                       ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.ENCHIRIDION2, "book"                       ,   W, (byte)  4);
		}
		if (MD.SmAc.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.SmAc, "sa.achievementBook"                 ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.SmAc, "sa.achievementBook"                 ,   W, (byte) 49);
		}
		if (MD.HQM.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.HQM, "quest_book"                          ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.HQM, "quest_book"                          ,   W, (byte)  1);
		}
		if (MD.MNTL.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.MNTL, "mantleBook"                         ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.MNTL, "mantleBook"                         ,   W, (byte)  7);
		}
		if (MD.TiC.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.TiC, "manualBook"                          ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.TiC, "manualBook"                          ,   W, (byte)  4);
			BooksGT.BOOK_REGISTER  .put(MD.TiC, "manualBook"                          ,   0, (byte) 10);
			BooksGT.BOOK_REGISTER  .put(MD.TiC, "manualBook"                          ,   1, (byte)  5);
			BooksGT.BOOK_REGISTER  .put(MD.TiC, "manualBook"                          ,   2, (byte)  3);
			BooksGT.BOOK_REGISTER  .put(MD.TiC, "manualBook"                          ,   3, (byte)  7);
			BooksGT.BOOK_REGISTER  .put(MD.TiC, "manualBook"                          ,   4, (byte)  6);
			BooksGT.BOOK_REGISTER  .put(MD.TiC, "materials"                           ,   0, (byte) 25);
		}
		if (MD.MF2.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.MF2, "MF_ResearchBook"                     ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MF2, "MF_Com_skillbook_artisanry"          ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MF2, "MF_Com_skillbook_provisioning"       ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MF2, "MF_Com_skillbook_engineering"        ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MF2, "MF_Com_skillbook_construction"       ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MF2, "MF_Com_skillbook_combat"             ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MF2, "MF_Com_skillbook_artisanry2"         ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MF2, "MF_Com_skillbook_provisioning2"      ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MF2, "MF_Com_skillbook_engineering2"       ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MF2, "MF_Com_skillbook_construction2"      ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.MF2, "MF_Com_skillbook_combat2"            ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.MF2, "MF_ResearchBook"                     ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.MF2, "MF_Com_skillbook_artisanry"          ,   W, (byte)  5);
			BooksGT.BOOK_REGISTER  .put(MD.MF2, "MF_Com_skillbook_provisioning"       ,   W, (byte)  6);
			BooksGT.BOOK_REGISTER  .put(MD.MF2, "MF_Com_skillbook_engineering"        ,   W, (byte)  8);
			BooksGT.BOOK_REGISTER  .put(MD.MF2, "MF_Com_skillbook_construction"       ,   W, (byte) 10);
			BooksGT.BOOK_REGISTER  .put(MD.MF2, "MF_Com_skillbook_combat"             ,   W, (byte) 49);
			BooksGT.BOOK_REGISTER  .put(MD.MF2, "MF_Com_skillbook_artisanry2"         ,   W, (byte)  4);
			BooksGT.BOOK_REGISTER  .put(MD.MF2, "MF_Com_skillbook_provisioning2"      ,   W, (byte)  4);
			BooksGT.BOOK_REGISTER  .put(MD.MF2, "MF_Com_skillbook_engineering2"       ,   W, (byte)  4);
			BooksGT.BOOK_REGISTER  .put(MD.MF2, "MF_Com_skillbook_construction2"      ,   W, (byte)  4);
			BooksGT.BOOK_REGISTER  .put(MD.MF2, "MF_Com_skillbook_combat2"            ,   W, (byte)  4);
		}
		if (MD.MoCr.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.MoCr, "key"                                ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.MoCr, "scrollofsale"                       ,   W, (byte) 18);
			BooksGT.BOOK_REGISTER  .put(MD.MoCr, "scrollofowner"                      ,   W, (byte) 18);
			BooksGT.BOOK_REGISTER  .put(MD.MoCr, "scrolloffreedom"                    ,   W, (byte) 18);
		}
		if (MD.Birb.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.Birb, "bird_book"                          ,   W, (byte)  8);
		}
		if (MD.ChocoCraft.mLoaded) {
			BooksGT.BOOK_REGISTER  .put(MD.ChocoCraft, "Chocopedia"                   ,   W, (byte)  4);
			BooksGT.BOOK_REGISTER  .put(MD.ChocoCraft, "Loverly_Gysahl"               ,   W, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.ChocoCraft, "Red_Gysahl"                   ,   W, (byte) 51);
			BooksGT.BOOK_REGISTER  .put(MD.ChocoCraft, "Golden_Gysahl"                ,   W, (byte) 51);
		}
		if (MD.HOWL.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.HOWL, "lycanthropeBook"                    ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.HOWL, "lycanthropeBook"                    ,   W, (byte) 20);
		}
		if (MD.WTCH.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.WTCH, "ingredient"                         ,  46);
			BooksGT.BOOKS_NORMAL   .add(MD.WTCH, "ingredient"                         ,  47);
			BooksGT.BOOKS_NORMAL   .add(MD.WTCH, "ingredient"                         ,  48);
			BooksGT.BOOKS_NORMAL   .add(MD.WTCH, "ingredient"                         ,  49);
			BooksGT.BOOKS_NORMAL   .add(MD.WTCH, "ingredient"                         ,  54);
			BooksGT.BOOKS_NORMAL   .add(MD.WTCH, "ingredient"                         ,  81);
			BooksGT.BOOKS_NORMAL   .add(MD.WTCH, "ingredient"                         ,  84);
			BooksGT.BOOKS_NORMAL   .add(MD.WTCH, "ingredient"                         , 107);
			BooksGT.BOOKS_NORMAL   .add(MD.WTCH, "ingredient"                         , 127);
			BooksGT.BOOKS_ENCHANTED.add(MD.WTCH, "bookbiomes2"                        ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.WTCH, "cauldronbook"                       ,   W);
			BooksGT.BOOKS_ENCHANTED.add(MD.WTCH, "vampirebook"                        ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         ,  46, (byte) 21);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         ,  47, (byte) 21);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         ,  48, (byte) 21);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         ,  49, (byte) 21);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         ,  54, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         ,  81, (byte) 21);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         ,  84, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         , 106, (byte) 16);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         , 107, (byte) 21);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         , 127, (byte) 21);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         , 140, (byte) 18);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         , 141, (byte) 18);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         , 142, (byte) 18);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         , 143, (byte) 18);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         , 144, (byte) 18);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         , 145, (byte) 18);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         , 146, (byte) 18);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "ingredient"                         , 160, (byte) 25);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "biomenote"                          ,   W, (byte) 26);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "bookbiomes2"                        ,   W, (byte) 16);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "cauldronbook"                       ,   W, (byte) 22);
			BooksGT.BOOK_REGISTER  .put(MD.WTCH, "vampirebook"                        ,   W, (byte) 23);
		}
		if (MD.BbLC.mLoaded) {
			BooksGT.BOOKS_NORMAL   .add(MD.BbLC, "item.SlottedBook"                   ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.BbLC, "item.BiblioRedBook"                 ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.BbLC, "item.BigBook"                       ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.BbLC, "item.TesterItem"                    ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.BbLC, "item.StockroomCatalog"              ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.BbLC, "item.AtlasBook"                     ,   W);
			BooksGT.BOOKS_NORMAL   .add(MD.BbLC, "item.RecipeBook"                    ,   W);
			BooksGT.BOOK_REGISTER  .put(MD.BbLC, "item.SlottedBook"                   ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.BbLC, "item.BiblioRedBook"                 ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.BbLC, "item.BigBook"                       ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.BbLC, "item.TesterItem"                    ,   W, (byte)  1);
			BooksGT.BOOK_REGISTER  .put(MD.BbLC, "item.StockroomCatalog"              ,   W, (byte)  6);
			BooksGT.BOOK_REGISTER  .put(MD.BbLC, "item.AtlasBook"                     ,   W, (byte) 16);
			BooksGT.BOOK_REGISTER  .put(MD.BbLC, "item.RecipeBook"                    ,   W, (byte) 17);
			BooksGT.BOOK_REGISTER  .put(MD.BbLC, "item.BiblioClipboard"               ,   W, (byte) 29);
			BooksGT.BOOK_REGISTER  .put(MD.BbLC, "item.PrintPlate"                    ,   W, (byte) 31);
			BooksGT.BOOK_REGISTER  .put(MD.BbLC, "item.EnchantedPlate"                ,   W, (byte) 31);
			BooksGT.BOOK_REGISTER  .put(MD.BbLC, "item.AtlasPlate"                    ,   W, (byte) 31);
		}
	}
}
