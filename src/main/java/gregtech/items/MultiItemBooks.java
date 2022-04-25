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

package gregtech.items;

import gregapi.data.*;
import gregapi.item.CreativeTab;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.oredict.OreDictItemData;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

import java.util.List;
import java.util.Random;

import static gregapi.data.CS.*;

public class MultiItemBooks extends MultiItemRandom {
	public MultiItemBooks(String aModID, String aUnlocalized) {
		super(aModID, aUnlocalized);
		OM.reg(OD.craftingBook, ST.make(this, 1, W));
		BooksGT.BOOK_REGISTER.put(this, W, (byte)3);
		setCreativeTab(new CreativeTab(getUnlocalizedName(), "GregTech: Books", this, (short)32000));
	}
	
	@Override
	public void addItems() {
		for (int i = 0; i < 11; i++) {
			BooksGT.BOOK_REGISTER.put(addItem(     i, "Book"       , "", OD.bookWrittenSmall, TC.stack(TC.COGNITIO, 2), TICKS_PER_SMELT  , new OreDictItemData(MT.Paper, U * 3)), (byte)(i>8?39+i:i==8?1:3+i)); BooksGT.BOOKS_NORMAL.add(last());
			BooksGT.BOOK_REGISTER.put(addItem(1000+i, "Large Book" , "", OD.bookWrittenBig  , TC.stack(TC.COGNITIO, 4), TICKS_PER_SMELT*2, new OreDictItemData(MT.Paper, U * 6)), (byte)(i>8?39+i:i==8?1:3+i)); BooksGT.BOOKS_NORMAL.add(last());
		}
		
		BooksGT.BOOK_REGISTER.put(addItem(32000, "Book"                    , "With a Bronze Emblem on it"      , OD.bookWrittenSmall, TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 2), TICKS_PER_SMELT  , new OreDictItemData(MT.Paper, U * 3, MT.Bronze, U9)), (byte)12); BooksGT.BOOKS_NORMAL.add(last());
		BooksGT.BOOK_REGISTER.put(addItem(32001, "Large Book"              , "With a Bronze Emblem on it"      , OD.bookWrittenBig  , TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 4), TICKS_PER_SMELT*2, new OreDictItemData(MT.Paper, U * 6, MT.Bronze, U9)), (byte)12); BooksGT.BOOKS_NORMAL.add(last());
		
		BooksGT.BOOK_REGISTER.put(addItem(32002, "Material Dictionary"     , "Contains Data about a Material"  , OD.bookWrittenSmall, TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 2), TICKS_PER_SMELT  , new OreDictItemData(MT.Paper, U * 3)), (byte)11); BooksGT.BOOKS_NORMAL.add(last());
		BooksGT.BOOK_REGISTER.put(addItem(32003, "Material Dictionary"     , "Contains Data about a Material"  , OD.bookWrittenBig  , TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 4), TICKS_PER_SMELT*2, new OreDictItemData(MT.Paper, U * 6)), (byte)11); BooksGT.BOOKS_NORMAL.add(last());
		
		BooksGT.BOOK_REGISTER.put(addItem(32004, "Book"                    , "With a Radiation Symbol on it"   , OD.bookWrittenSmall, TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 4), TICKS_PER_SMELT*2, new OreDictItemData(MT.Paper, U * 3, MT.Tc, U9)), (byte)10); BooksGT.BOOKS_NORMAL.add(last());
		BooksGT.BOOK_REGISTER.put(addItem(32005, "Large Book"              , "With a Radiation Symbol on it"   , OD.bookWrittenBig  , TD.Creative.HIDDEN, TC.stack(TC.COGNITIO, 4), TICKS_PER_SMELT*2, new OreDictItemData(MT.Paper, U * 6, MT.Tc, U9)), (byte)10); BooksGT.BOOKS_NORMAL.add(last());
		
		CR.shapeless(ST.make(this, 1,     0), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenSmall, DYE_OREDICTS[DYE_INDEX_Black]});
		CR.shapeless(ST.make(this, 1,     1), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenSmall, DYE_OREDICTS[DYE_INDEX_White]});
		CR.shapeless(ST.make(this, 1,     2), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenSmall, DYE_OREDICTS[DYE_INDEX_Red]});
		CR.shapeless(ST.make(this, 1,     3), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenSmall, DYE_OREDICTS[DYE_INDEX_Green]});
		CR.shapeless(ST.make(this, 1,     4), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenSmall, DYE_OREDICTS[DYE_INDEX_Blue]});
		CR.shapeless(ST.make(this, 1,     5), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenSmall, DYE_OREDICTS[DYE_INDEX_Cyan]});
		CR.shapeless(ST.make(this, 1,     6), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenSmall, DYE_OREDICTS[DYE_INDEX_Magenta]});
		CR.shapeless(ST.make(this, 1,     7), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenSmall, DYE_OREDICTS[DYE_INDEX_Yellow]});
		CR.shapeless(ST.make(this, 1,     8), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenSmall, DYE_OREDICTS[DYE_INDEX_Brown]});
		CR.shapeless(ST.make(this, 1,     9), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenSmall, DYE_OREDICTS[DYE_INDEX_Orange]});
		CR.shapeless(ST.make(this, 1,    10), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenSmall, DYE_OREDICTS[DYE_INDEX_Purple]});
		
		CR.shapeless(ST.make(this, 1,  1000), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenBig, DYE_OREDICTS[DYE_INDEX_Black]});
		CR.shapeless(ST.make(this, 1,  1001), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenBig, DYE_OREDICTS[DYE_INDEX_White]});
		CR.shapeless(ST.make(this, 1,  1002), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenBig, DYE_OREDICTS[DYE_INDEX_Red]});
		CR.shapeless(ST.make(this, 1,  1003), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenBig, DYE_OREDICTS[DYE_INDEX_Green]});
		CR.shapeless(ST.make(this, 1,  1004), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenBig, DYE_OREDICTS[DYE_INDEX_Blue]});
		CR.shapeless(ST.make(this, 1,  1005), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenBig, DYE_OREDICTS[DYE_INDEX_Cyan]});
		CR.shapeless(ST.make(this, 1,  1006), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenBig, DYE_OREDICTS[DYE_INDEX_Magenta]});
		CR.shapeless(ST.make(this, 1,  1007), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenBig, DYE_OREDICTS[DYE_INDEX_Yellow]});
		CR.shapeless(ST.make(this, 1,  1008), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenBig, DYE_OREDICTS[DYE_INDEX_Brown]});
		CR.shapeless(ST.make(this, 1,  1009), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenBig, DYE_OREDICTS[DYE_INDEX_Orange]});
		CR.shapeless(ST.make(this, 1,  1010), CR.DEF_NCC | CR.KEEPNBT, new Object[] {OD.bookWrittenBig, DYE_OREDICTS[DYE_INDEX_Purple]});
		
		CR.shapeless(ST.make(this, 1,     0), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Black]});
		CR.shapeless(ST.make(this, 1,     1), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_White]});
		CR.shapeless(ST.make(this, 1,     2), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Red]});
		CR.shapeless(ST.make(this, 1,     3), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Green]});
		CR.shapeless(ST.make(this, 1,     4), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Blue]});
		CR.shapeless(ST.make(this, 1,     5), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Cyan]});
		CR.shapeless(ST.make(this, 1,     6), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Magenta]});
		CR.shapeless(ST.make(this, 1,     7), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Yellow]});
		CR.shapeless(ST.make(this, 1,     8), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Brown]});
		CR.shapeless(ST.make(this, 1,     9), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Orange]});
		CR.shapeless(ST.make(this, 1,    10), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Purple]});
		
		CR.shapeless(ST.make(this, 1,  1000), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Black]});
		CR.shapeless(ST.make(this, 1,  1001), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_White]});
		CR.shapeless(ST.make(this, 1,  1002), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Red]});
		CR.shapeless(ST.make(this, 1,  1003), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Green]});
		CR.shapeless(ST.make(this, 1,  1004), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Blue]});
		CR.shapeless(ST.make(this, 1,  1005), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Cyan]});
		CR.shapeless(ST.make(this, 1,  1006), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Magenta]});
		CR.shapeless(ST.make(this, 1,  1007), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Yellow]});
		CR.shapeless(ST.make(this, 1,  1008), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Brown]});
		CR.shapeless(ST.make(this, 1,  1009), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Orange]});
		CR.shapeless(ST.make(this, 1,  1010), CR.DEF_NCC | CR.KEEPNBT, new Object[] {IL.Paper_Printed_Pages_Many.get(1), OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Purple]});
		
		CR.shapeless(ST.make(this, 1,     0), CR.DEF_NCC, new Object[] {OD.bookEmptySmall, DYE_OREDICTS[DYE_INDEX_Black]});
		CR.shapeless(ST.make(this, 1,     1), CR.DEF_NCC, new Object[] {OD.bookEmptySmall, DYE_OREDICTS[DYE_INDEX_White]});
		CR.shapeless(ST.make(this, 1,     2), CR.DEF_NCC, new Object[] {OD.bookEmptySmall, DYE_OREDICTS[DYE_INDEX_Red]});
		CR.shapeless(ST.make(this, 1,     3), CR.DEF_NCC, new Object[] {OD.bookEmptySmall, DYE_OREDICTS[DYE_INDEX_Green]});
		CR.shapeless(ST.make(this, 1,     4), CR.DEF_NCC, new Object[] {OD.bookEmptySmall, DYE_OREDICTS[DYE_INDEX_Blue]});
		CR.shapeless(ST.make(this, 1,     5), CR.DEF_NCC, new Object[] {OD.bookEmptySmall, DYE_OREDICTS[DYE_INDEX_Cyan]});
		CR.shapeless(ST.make(this, 1,     6), CR.DEF_NCC, new Object[] {OD.bookEmptySmall, DYE_OREDICTS[DYE_INDEX_Magenta]});
		CR.shapeless(ST.make(this, 1,     7), CR.DEF_NCC, new Object[] {OD.bookEmptySmall, DYE_OREDICTS[DYE_INDEX_Yellow]});
		CR.shapeless(ST.make(this, 1,     8), CR.DEF_NCC, new Object[] {OD.bookEmptySmall, DYE_OREDICTS[DYE_INDEX_Brown]});
		CR.shapeless(ST.make(this, 1,     9), CR.DEF_NCC, new Object[] {OD.bookEmptySmall, DYE_OREDICTS[DYE_INDEX_Orange]});
		CR.shapeless(ST.make(this, 1,    10), CR.DEF_NCC, new Object[] {OD.bookEmptySmall, DYE_OREDICTS[DYE_INDEX_Purple]});
		
		CR.shapeless(ST.make(this, 1,  1000), CR.DEF_NCC, new Object[] {OD.bookEmptyBig, DYE_OREDICTS[DYE_INDEX_Black]});
		CR.shapeless(ST.make(this, 1,  1001), CR.DEF_NCC, new Object[] {OD.bookEmptyBig, DYE_OREDICTS[DYE_INDEX_White]});
		CR.shapeless(ST.make(this, 1,  1002), CR.DEF_NCC, new Object[] {OD.bookEmptyBig, DYE_OREDICTS[DYE_INDEX_Red]});
		CR.shapeless(ST.make(this, 1,  1003), CR.DEF_NCC, new Object[] {OD.bookEmptyBig, DYE_OREDICTS[DYE_INDEX_Green]});
		CR.shapeless(ST.make(this, 1,  1004), CR.DEF_NCC, new Object[] {OD.bookEmptyBig, DYE_OREDICTS[DYE_INDEX_Blue]});
		CR.shapeless(ST.make(this, 1,  1005), CR.DEF_NCC, new Object[] {OD.bookEmptyBig, DYE_OREDICTS[DYE_INDEX_Cyan]});
		CR.shapeless(ST.make(this, 1,  1006), CR.DEF_NCC, new Object[] {OD.bookEmptyBig, DYE_OREDICTS[DYE_INDEX_Magenta]});
		CR.shapeless(ST.make(this, 1,  1007), CR.DEF_NCC, new Object[] {OD.bookEmptyBig, DYE_OREDICTS[DYE_INDEX_Yellow]});
		CR.shapeless(ST.make(this, 1,  1008), CR.DEF_NCC, new Object[] {OD.bookEmptyBig, DYE_OREDICTS[DYE_INDEX_Brown]});
		CR.shapeless(ST.make(this, 1,  1009), CR.DEF_NCC, new Object[] {OD.bookEmptyBig, DYE_OREDICTS[DYE_INDEX_Orange]});
		CR.shapeless(ST.make(this, 1,  1010), CR.DEF_NCC, new Object[] {OD.bookEmptyBig, DYE_OREDICTS[DYE_INDEX_Purple]});
		
		CR.shapeless(ST.make(this, 1,     0), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Black]});
		CR.shapeless(ST.make(this, 1,     1), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_White]});
		CR.shapeless(ST.make(this, 1,     2), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Red]});
		CR.shapeless(ST.make(this, 1,     3), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Green]});
		CR.shapeless(ST.make(this, 1,     4), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Blue]});
		CR.shapeless(ST.make(this, 1,     5), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Cyan]});
		CR.shapeless(ST.make(this, 1,     6), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Magenta]});
		CR.shapeless(ST.make(this, 1,     7), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Yellow]});
		CR.shapeless(ST.make(this, 1,     8), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Brown]});
		CR.shapeless(ST.make(this, 1,     9), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Orange]});
		CR.shapeless(ST.make(this, 1,    10), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Purple]});
		
		CR.shapeless(ST.make(this, 1,  1000), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Black]});
		CR.shapeless(ST.make(this, 1,  1001), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_White]});
		CR.shapeless(ST.make(this, 1,  1002), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Red]});
		CR.shapeless(ST.make(this, 1,  1003), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Green]});
		CR.shapeless(ST.make(this, 1,  1004), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Blue]});
		CR.shapeless(ST.make(this, 1,  1005), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Cyan]});
		CR.shapeless(ST.make(this, 1,  1006), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Magenta]});
		CR.shapeless(ST.make(this, 1,  1007), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Yellow]});
		CR.shapeless(ST.make(this, 1,  1008), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Brown]});
		CR.shapeless(ST.make(this, 1,  1009), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Orange]});
		CR.shapeless(ST.make(this, 1,  1010), CR.DEF_NCC, new Object[] {OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.paperEmpty, OD.craftingLeather, DYE_OREDICTS[DYE_INDEX_Purple]});
	}
	
	@Override
	public WeightedRandomChestContent getChestGenBase(ChestGenHooks aChestGenHook, Random aRandom, WeightedRandomChestContent aOriginal) {
		if (aOriginal.theItemId.hasTagCompound()) return aOriginal;
		if (ST.meta_(aOriginal.theItemId) == 32002 || ST.meta_(aOriginal.theItemId) == 32003) return new WeightedRandomChestContent(ST.book(UT.Books.MATERIAL_DICTIONARIES.get(aRandom.nextInt(UT.Books.MATERIAL_DICTIONARIES.size()))), aOriginal.theMinimumChanceToGenerateItem, aOriginal.theMaximumChanceToGenerateItem, aOriginal.itemWeight);
		return aOriginal;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		UT.Books.display(aPlayer, aStack);
		return super.onItemRightClick(aStack, aWorld, aPlayer);
	}
	
	@Override
	public void addAdditionalToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addAdditionalToolTips(aList, aStack, aF3_H);
		String tTitle = UT.NBT.getBookTitle(aStack);
		if (UT.Code.stringValid(tTitle)) {
			aList.add(LH.Chat.CYAN + tTitle);
			aList.add(LH.Chat.CYAN + "by " + UT.NBT.getBookAuthor(aStack));
		} else {
			aList.add(LH.Chat.CYAN + "This Book is Empty");
		}
	}
}
