/**
 * Copyright (c) 2023 GregTech-6 Team
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

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.*;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Blocks;

import static gregapi.data.CS.OUT;
import static gregapi.data.CS.T;

public class Compat_Recipes_SaltyMod extends CompatMods {
	public Compat_Recipes_SaltyMod(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Salty Mod Recipes.");
		CR.delate(MD.Salt, "saltStar", "saltDirt", "mineralMud", "mudBlock", "mudBrickWet");
		
		RM.packunpack(ST.make(MD.Salt, "mineralMud", 4, 0), 4, ST.make(MD.Salt, "mudBlock", 1, 0));
		
		CR.shapeless(ST.make(MD.Salt, "mineralMud", 4, 0), CR.DEF_NCC, new Object[] {ST.make(MD.Salt, "mudBlock", 1, 0)});
		
		CR.shaped(ST.make(MD.Salt, "mudBlock"   , 1, 0), CR.DEF_NCC    , "BB", "BB", 'B', ST.make(MD.Salt, "mineralMud", 1, 0));
		CR.shaped(ST.make(MD.Salt, "mudBrickWet", 2, 0), CR.DEF_NCC_MIR, "XY", "YX", 'X', ST.make(MD.Salt, "mudBlock", 1, 0), 'Y', OD.itemGrass);
		CR.shaped(ST.make(MD.Salt, "mudBrickWet", 2, 0), CR.DEF_NCC_MIR, "XY", "YX", 'X', ST.make(MD.Salt, "mudBlock", 1, 0), 'Y', OD.itemGrassDry);
		CR.shaped(ST.make(MD.Salt, "mudBrickWet", 2, 0), CR.DEF_NCC_MIR, "XY", "YX", 'X', ST.make(MD.Salt, "mudBlock", 1, 0), 'Y', OD.cropGrain);
		
		RM.Mixer.addRecipe2(T, 16, 144, ST.make(Blocks.dirt, 1, 0), OP.dust.mat(MT.NaCl, 1), ST.make(MD.Salt, "saltDirt", 1, 0));
		RM.Mixer.addRecipeX(T, 16, 144, ST.array(OP.dust.mat(MT.Gunpowder, 1), OP.dust.mat(MT.NaHCO3, 4), OP.dust.mat(MT.NaCl, 4)), ST.make(MD.Salt, "saltStar", 1, 0));
		for (OreDictMaterial tClay : ANY.Clay.mToThis) for (OreDictMaterial tCarbon : ANY.C.mToThis)
		RM.Mixer.addRecipeX(T, 16, 64, ST.array(OP.dust.mat(MT.NaHCO3, 1), OP.dust.mat(MT.NaCl, 1), OP.dust.mat(tClay, 1), OP.dust.mat(tCarbon, 1)), ST.make(MD.Salt, "mineralMud", 1, 0));
	}
}
