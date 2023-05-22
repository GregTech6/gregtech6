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
		
		RM.compactunpack(IL.Salt_Mud_Ball.get(4), 4, IL.Salt_Mud.get(1));
		CR.shapeless(IL.Salt_Mud_Ball.get(4), CR.DEF_NCC, new Object[] {IL.Salt_Mud.get(1)});
		CR.shaped(IL.Salt_Mud.get(1), CR.DEF_NCC, "BB", "BB", 'B', IL.Salt_Mud_Ball);
		CR.shaped(ST.make(MD.Salt, "mudBrickWet", 2, 0), CR.DEF_NCC_MIR, "XY", "YX", 'X', OD.blockMud, 'Y', OD.itemGrass);
		CR.shaped(ST.make(MD.Salt, "mudBrickWet", 2, 0), CR.DEF_NCC_MIR, "XY", "YX", 'X', OD.blockMud, 'Y', OD.itemGrassDry);
		CR.shaped(ST.make(MD.Salt, "mudBrickWet", 2, 0), CR.DEF_NCC_MIR, "XY", "YX", 'X', OD.blockMud, 'Y', OD.cropGrain);
		
		RM.mortarize(16, ST.make(MD.Salt, "saltCrystal", 1, 0), OP.dust.mat(MT.NaCl, 16));
		
		RM.Mixer.addRecipe2(T, 16,  64, ST.make(Blocks.dirt, 1, 0), OP.dustSmall.mat(MT.NaCl, 2), IL.Salt_Dirt_1.get(1));
		RM.Mixer.addRecipe2(T, 16,  64, ST.make(Blocks.dirt, 2, 0), OP.dust     .mat(MT.NaCl, 1), IL.Salt_Dirt_1.get(2));
		RM.Mixer.addRecipe2(T, 16,  64, IL.Salt_Dirt_1     .get(1), OP.dustSmall.mat(MT.NaCl, 2), IL.Salt_Dirt_2.get(1));
		RM.Mixer.addRecipe2(T, 16,  64, IL.Salt_Dirt_1     .get(2), OP.dust     .mat(MT.NaCl, 1), IL.Salt_Dirt_2.get(2));
		RM.Mixer.addRecipe2(T, 16,  64, IL.Salt_Dirt_2     .get(1), OP.dust     .mat(MT.NaCl, 1), IL.Salt_Dirt_3.get(1));
		
		RM.Mixer.addRecipeX(T, 16, 144, ST.array(OP.dust.mat(MT.NaHCO3, 4), OP.dust.mat(MT.NaCl, 4), OP.dust.mat(MT.Gunpowder, 1)), ST.make(MD.Salt, "saltStar", 1, 0));
		for (OreDictMaterial tCarbon : ANY.C.mToThis) if (ST.valid(OP.dust.mat(tCarbon, 1))) for (OreDictMaterial tClay : ANY.Clay.mToThis) {
		RM.Mixer.addRecipeX(T, 16,  16, ST.array(OP.dustSmall.mat(MT.NaHCO3, 1), OP.dustSmall.mat(MT.NaCl, 1), OP.dustSmall.mat(tClay, 1), OP.dustSmall.mat(tCarbon, 1)), IL.Salt_Mud_Ball.get(1));
		RM.Mixer.addRecipeX(T, 16,  64, ST.array(OP.dust     .mat(MT.NaHCO3, 1), OP.dust     .mat(MT.NaCl, 1), OP.dust     .mat(tClay, 1), OP.dust     .mat(tCarbon, 1)), IL.Salt_Mud_Ball.get(4));
		}
	}
}
