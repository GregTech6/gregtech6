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

package gregtech.compat;

import static gregapi.data.CS.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.CS.OreDictToolNames;
import gregapi.data.FL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Items;

public class Compat_Recipes_Witchery extends CompatMods {
	public Compat_Recipes_Witchery(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Witchery Recipes.");
		CR.remout(MD.WTCH, "ingredient", 26);
		
		CR.shaped(ST.make(MD.WTCH, "ingredient", 1,  7), CR.DEF_NCC, "B  ", "   ", "  k", 'B', Items.bone);
		CR.shaped(ST.make(MD.WTCH, "ingredient", 1, 26), CR.DEF_NCC, " C ", "   ", "k R", 'C', OD.itemClay, 'R', OreDictToolNames.rollingpin);
		
		RM.smash(ST.make(MD.WTCH, "perpetualice", 1, 0), OM.dust(MT.Ice, U));
		RM.Squeezer     .addRecipe1(T, 16, 128, ST.make(MD.WTCH, "perpetualice", 1, 0), NF, FL.Ice.make(1000), NI);
		RM.Juicer       .addRecipe1(T, 16, 128, ST.make(MD.WTCH, "perpetualice", 1, 0), NF, FL.Ice.make(1000), NI);
		RM.smash(ST.make(MD.WTCH, "icestairs", 1, 0), OM.dust(MT.Ice, 3*U4));
		RM.Squeezer     .addRecipe1(T, 16,  96, ST.make(MD.WTCH, "icestairs", 1, 0), NF, FL.Ice.make(750), NI);
		RM.Juicer       .addRecipe1(T, 16,  96, ST.make(MD.WTCH, "icestairs", 1, 0), NF, FL.Ice.make(750), NI);
		RM.smash(ST.make(MD.WTCH, "iceslab", 1, 0), OM.dust(MT.Ice, U2));
		RM.Squeezer     .addRecipe1(T, 16,  64, ST.make(MD.WTCH, "iceslab", 1, 0), NF, FL.Ice.make(500), NI);
		RM.Juicer       .addRecipe1(T, 16,  64, ST.make(MD.WTCH, "iceslab", 1, 0), NF, FL.Ice.make(500), NI);
		
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(MD.WTCH, "shadedglass", 1, W), OM.dust(MT.Glass));
		RM.Mortar       .addRecipe1(T, 16, 32, ST.make(MD.WTCH, "shadedglass_active", 1, W), OM.dust(MT.Glass));
	}
}
