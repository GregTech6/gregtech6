/**
 * Copyright (c) 2020 GregTech-6 Team
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

import static gregapi.data.CS.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.util.ST;
import net.minecraft.init.Items;

public class Compat_Recipes_ExtraBiomesXL extends CompatMods {
	public Compat_Recipes_ExtraBiomesXL(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing EBXL Recipes.");
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "vines"      , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "waterplant1", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Green], IL.Dye_Cactus.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower1"    , 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], ST.make(Items.dye, 1, DYE_INDEX_LightBlue));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower1"    , 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], ST.make(Items.dye, 1, DYE_INDEX_Yellow));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower1"    , 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower1"    , 1, 4), IL.EBXL_Cactus_Paste.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower1"    , 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Brown], IL.EBXL_Dye_Brown.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower1"    , 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray], ST.make(Items.dye, 1, DYE_INDEX_LightGray));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta], ST.make(Items.dye, 1, DYE_INDEX_Magenta));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 1, DYE_INDEX_Pink));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], IL.EBXL_Dye_White.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], IL.EBXL_Dye_Blue.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Lime], ST.make(Items.dye, 1, DYE_INDEX_Lime));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], ST.make(Items.dye, 1, DYE_INDEX_LightBlue));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Black], IL.EBXL_Dye_Black.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 8), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], IL.EBXL_Dye_White.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1,11), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray], ST.make(Items.dye, 1, DYE_INDEX_LightGray));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1,12), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange], ST.make(Items.dye, 1, DYE_INDEX_Orange));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1,13), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 1, DYE_INDEX_Pink));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1,14), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1,15), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], ST.make(Items.dye, 1, DYE_INDEX_Yellow));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 1, DYE_INDEX_Pink));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], IL.EBXL_Dye_Blue.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta], ST.make(Items.dye, 1, DYE_INDEX_Magenta));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], ST.make(Items.dye, 1, DYE_INDEX_Yellow));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], ST.make(Items.dye, 1, DYE_INDEX_Yellow));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 8), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange], ST.make(Items.dye, 1, DYE_INDEX_Orange));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 9), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange], ST.make(Items.dye, 1, DYE_INDEX_Orange));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1,10), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1,11), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], ST.make(Items.dye, 1, DYE_INDEX_Yellow));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1,12), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], IL.EBXL_Dye_Blue.get(1));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1,13), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Cyan], ST.make(Items.dye, 1, DYE_INDEX_Cyan));
		
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "vines"      , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "waterplant1", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Green], IL.Dye_Cactus.get(1));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower1"    , 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], ST.make(Items.dye, 1, DYE_INDEX_LightBlue));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower1"    , 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], ST.make(Items.dye, 1, DYE_INDEX_Yellow));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower1"    , 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower1"    , 1, 4), IL.EBXL_Cactus_Paste.get(1));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower1"    , 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Brown], IL.EBXL_Dye_Brown.get(1));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower1"    , 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray], ST.make(Items.dye, 1, DYE_INDEX_LightGray));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta], ST.make(Items.dye, 1, DYE_INDEX_Magenta));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 1, DYE_INDEX_Pink));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], IL.EBXL_Dye_White.get(1));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], IL.EBXL_Dye_Blue.get(1));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Lime], ST.make(Items.dye, 1, DYE_INDEX_Lime));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], ST.make(Items.dye, 1, DYE_INDEX_LightBlue));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Black], IL.EBXL_Dye_Black.get(1));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1, 8), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_White], IL.EBXL_Dye_White.get(1));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1,11), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightGray], ST.make(Items.dye, 1, DYE_INDEX_LightGray));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1,12), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange], ST.make(Items.dye, 1, DYE_INDEX_Orange));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1,13), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 1, DYE_INDEX_Pink));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1,14), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower2"    , 1,15), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], ST.make(Items.dye, 1, DYE_INDEX_Yellow));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Pink], ST.make(Items.dye, 1, DYE_INDEX_Pink));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 1), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], IL.EBXL_Dye_Blue.get(1));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 2), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Magenta], ST.make(Items.dye, 1, DYE_INDEX_Magenta));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 4), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], ST.make(Items.dye, 1, DYE_INDEX_Yellow));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], ST.make(Items.dye, 1, DYE_INDEX_Yellow));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 7), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red], ST.make(Items.dye, 1, DYE_INDEX_Red));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 8), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange], ST.make(Items.dye, 1, DYE_INDEX_Orange));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1, 9), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange], ST.make(Items.dye, 1, DYE_INDEX_Orange));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1,10), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1,11), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow], ST.make(Items.dye, 1, DYE_INDEX_Yellow));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1,12), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Blue], IL.EBXL_Dye_Blue.get(1));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.EBXL, "flower3"    , 1,13), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Cyan], ST.make(Items.dye, 1, DYE_INDEX_Cyan));
		
		if (ENABLE_ADDING_IC2_EXTRACTOR_RECIPES) {
		RM.ic2_extractor(ST.make(MD.EBXL, "vines"       , 1, 0), ST.make(Items.dye, 2, DYE_INDEX_Red));
		RM.ic2_extractor(ST.make(MD.EBXL, "waterplant1" , 1, 0), IL.Dye_Cactus.get(2));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower1"     , 1, 1), ST.make(Items.dye, 2, DYE_INDEX_LightBlue));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower1"     , 1, 2), ST.make(Items.dye, 2, DYE_INDEX_Yellow));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower1"     , 1, 3), ST.make(Items.dye, 2, DYE_INDEX_Purple));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower1"     , 1, 4), IL.EBXL_Cactus_Paste.get(1));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower1"     , 1, 6), IL.EBXL_Dye_Brown.get(2));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower1"     , 1, 7), ST.make(Items.dye, 2, DYE_INDEX_LightGray));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1, 0), ST.make(Items.dye, 2, DYE_INDEX_Magenta));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1, 1), ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1, 2), ST.make(Items.dye, 2, DYE_INDEX_Red));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1, 3), IL.EBXL_Dye_White.get(2));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1, 4), IL.EBXL_Dye_Blue.get(2));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1, 5), ST.make(Items.dye, 2, DYE_INDEX_Lime));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1, 6), ST.make(Items.dye, 2, DYE_INDEX_LightBlue));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1, 7), IL.EBXL_Dye_Black.get(2));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1, 8), IL.EBXL_Dye_White.get(2));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1,11), ST.make(Items.dye, 2, DYE_INDEX_LightGray));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1,12), ST.make(Items.dye, 2, DYE_INDEX_Orange));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1,13), ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1,14), ST.make(Items.dye, 2, DYE_INDEX_Red));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower2"     , 1,15), ST.make(Items.dye, 2, DYE_INDEX_Yellow));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1, 0), ST.make(Items.dye, 2, DYE_INDEX_Pink));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1, 1), IL.EBXL_Dye_Blue.get(2));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1, 2), ST.make(Items.dye, 2, DYE_INDEX_Purple));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1, 3), ST.make(Items.dye, 2, DYE_INDEX_Magenta));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1, 4), ST.make(Items.dye, 2, DYE_INDEX_Yellow));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1, 5), ST.make(Items.dye, 2, DYE_INDEX_Yellow));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1, 6), ST.make(Items.dye, 2, DYE_INDEX_Red));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1, 7), ST.make(Items.dye, 2, DYE_INDEX_Red));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1, 8), ST.make(Items.dye, 2, DYE_INDEX_Orange));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1, 9), ST.make(Items.dye, 2, DYE_INDEX_Orange));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1,10), ST.make(Items.dye, 2, DYE_INDEX_Purple));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1,11), ST.make(Items.dye, 2, DYE_INDEX_Yellow));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1,12), IL.EBXL_Dye_Blue.get(2));
		RM.ic2_extractor(ST.make(MD.EBXL, "flower3"     , 1,13), ST.make(Items.dye, 2, DYE_INDEX_Cyan));
		}
	}
}
