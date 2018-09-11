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

package gregtech.old;


public class Loader_UUMRecipes implements Runnable {
	@Override
	public void run() {
		/*
		GT_Log.out.println("GT_Mod: Adding/Removing/Overloading UUM Recipes.");
		
		String tMat = "craftingUUMatter";
		
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemDiamond"		, 1)	, new Object[] {"UUU", "UUU", "UUU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemEmerald"		, 2)	, new Object[] {"UUU", "UUU", " U ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemRuby"			, 2)	, new Object[] {" UU", "UUU", "UU ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemSapphire"		, 2)	, new Object[] {"UU ", "UUU", " UU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemGreenSapphire"	, 2)	, new Object[] {" UU", "UUU", " UU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("gemOlivine"		, 2)	, new Object[] {"UU ", "UUU", "UU ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustIron", 4)				, new Object[] {"U U", " U ", "U U", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustGold", 2)				, new Object[] {" U ", "UUU", " U ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustTin", 10)				, new Object[] {"	", "U U", "	 U", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustCopper", 10)			, new Object[] {"  U", "U U", "	  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustZinc", 10)				, new Object[] {"	", "U U", "U  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustNickel", 10)			, new Object[] {"U	", "U U", "	  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustLead", 14)				, new Object[] {"UUU", "UUU", "U  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustSilver", 14)			, new Object[] {" U ", "UUU", "UUU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustPlatinum", 1)			, new Object[] {"  U", "UUU", "UUU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustTungsten", 6)			, new Object[] {"U	", "UUU", "UUU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustGlowstone", 32)		, new Object[] {" U ", "U U", "UUU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustSmallOsmium", 1)		, new Object[] {"U U", "UUU", "U U", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustTitanium", 2)			, new Object[] {"UUU", " U ", " U ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustAluminium", 16)		, new Object[] {" U ", " U ", "UUU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.get("dustRedstone", 24)			, new Object[] {"	", " U ", "UUU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_OreDictUnificator.getFirstOre("dustNikolite", 12)	, new Object[] {"UUU", " U ", "	  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.coal, 8)							, new Object[] {"  U", "U  ", "	 U", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.enderPearl, 1)					, new Object[] {"UUU", "U U", "UUU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.blazeRod, 4)						, new Object[] {"U U", "UU ", "U U", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.waterlily, 32)					, new Object[] {"U U", "UUU", " U ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.leather, 32)						, new Object[] {"U U", " U ", "UUU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.silk, 32)							, new Object[] {"U U", "   ", " U ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.obsidian, 12)					, new Object[] {"U U", "U U", "	  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.netherrack, 16)					, new Object[] {"  U", " U ", "U  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.waterStill, 1)					, new Object[] {"	", " U ", " U ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.lavaStill, 1)					, new Object[] {" U ", " U ", " U ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.stone, 16)						, new Object[] {"	", " U ", "	  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.wood, 8, 0)						, new Object[] {" U ", "   ", "	  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.wood, 8, 1)						, new Object[] {"U	", "   ", "	  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.wood, 8, 2)						, new Object[] {"  U", "   ", "	  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.wood, 8, 3)						, new Object[] {"	", "U  ", "	  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.glass, 32)						, new Object[] {" U ", "U U", " U ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.grass, 16)						, new Object[] {"	", "U  ", "U  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.sandStone, 16)					, new Object[] {"	", "  U", " U ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.cobblestoneMossy, 16)			, new Object[] {"	", " U ", "U U", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.blockSnow, 16)					, new Object[] {"U U", "   ", "	  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.cactus, 48)						, new Object[] {" U ", "UUU", "U U", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.vine, 24)						, new Object[] {"U	", "U  ", "U  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.dyePowder, 9, 4)					, new Object[] {" U ", " U ", " UU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.feather, 32)						, new Object[] {" U ", " U ", "U U", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.gunpowder, 15)					, new Object[] {"UUU", "U  ", "UUU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.clay, 48)							, new Object[] {"UU ", "U  ", "UU ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.dyePowder, 32, 3)					, new Object[] {"UU ", "  U", "UU ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.dyePowder, 48, 0)					, new Object[] {" UU", " UU", " U ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.reed, 48)							, new Object[] {"U U", "U U", "U U", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.flint, 32)						, new Object[] {" U ", "UU ", "UU ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Item.bone, 32)							, new Object[] {"U	", "UU ", "U  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.stoneBrick, 48, 3)				, new Object[] {"UU ", "UU ", "U  ", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(new ItemStack(Block.mycelium, 24)					, new Object[] {"	", "U U", "UUU", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_ModHandler.getIC2Item("resin", 21)				, new Object[] {"U U", "   ", "U U", true, 'U', tMat});
		GT_ModHandler.addUUMRecipe(GT_ModHandler.getIC2Item("iridiumOre", 1)			, new Object[] {"UUU", " U ", "UUU", true, 'U', tMat});
		
		GT_ModHandler.removeRecipe(new ItemStack[] {null, null, null, null, null, null, GT_ModHandler.getIC2Item("matter", 1), GT_ModHandler.getIC2Item("matter", 1), GT_ModHandler.getIC2Item("matter", 1)});
		*/
	}
}
