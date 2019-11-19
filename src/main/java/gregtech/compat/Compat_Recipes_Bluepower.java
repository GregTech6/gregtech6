/**
 * Copyright (c) 2019 Gregorius Techneticies
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
import gregapi.data.ANY;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.ST;

public class Compat_Recipes_Bluepower extends CompatMods {
	public Compat_Recipes_Bluepower(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing BP Recipes.");
		for (OreDictMaterial tMat : ANY.Iron.mToThis) RM.Press.addRecipeX(T, 16, 256, ST.array((tMat==MT.Enori?OP.plateGem:OP.plate).mat(tMat, 1), OP.foil.mat(MT.Zn, 2)), ST.make(MD.BP, "zincplate", 2, 0));
		
		CR.shaped(ST.make(MD.BP, "iron_wire"        , 1, 0), CR.DEF_REV_NCC, "XXX", "X X", "XXX", 'X', OP.wireFine.dat(ANY.Fe));
		CR.shaped(ST.make(MD.BP, "copper_wire"      , 1, 0), CR.DEF_REV_NCC, "XXX", "X X", "XXX", 'X', OP.wireFine.dat(ANY.Cu));
		CR.shaped(ST.make(MD.BP, "silicon_wafer"    , 4, 0), CR.DEF_NCC, "X ", " s", 'X', OP.plateGem.dat(ANY.Si));
		CR.shaped(ST.make(MD.BP, "red_doped_wafer"  , 4, 0), CR.DEF_NCC, "X ", " s", 'X', OP.plateGem.dat(MT.RedstoneAlloy));
		CR.shaped(ST.make(MD.BP, "blue_doped_wafer" , 4, 0), CR.DEF_NCC, "X ", " s", 'X', OP.plateGem.dat(MT.TeslatineAlloy));
		CR.shaped(ST.make(MD.BP, "blue_doped_wafer" , 4, 0), CR.DEF_NCC, "X ", " s", 'X', OP.plateGem.dat(MT.NikolineAlloy));
		
		RM.biomass(ST.make(MD.BP, "indigo_flower", 16, 0));
		
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BP, "indigo_flower", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(MD.BP, "indigo_dye", 1, 0));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.BP, "indigo_flower", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], ST.make(MD.BP, "indigo_dye", 1, 0));
		RM.ic2_extractor(ST.make(MD.BP, "indigo_flower", 1, 0), ST.make(MD.BP, "indigo_dye", 1, 0));
		
		RM.sawing(64, 64, F, 1000, ST.make(MD.BP, "silicon_boule", 1, 0), ST.make(MD.BP, "silicon_wafer", 16, 0));
	}
}
