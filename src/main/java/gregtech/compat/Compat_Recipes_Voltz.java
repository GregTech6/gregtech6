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
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.item.Item;

public class Compat_Recipes_Voltz extends CompatMods {
	public Compat_Recipes_Voltz(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Voltz Mod Recipes.");
		if (CR.remout(MD.ICBM, "icbmCAntidote")) {
			RM.generify(MD.GC_GALAXYSPACE.mLoaded ? ST.make(MD.GC_GALAXYSPACE, "item.BasicItems", 1, 11) : IL.Pill_Iodine.get(1), ST.make(MD.ICBM, "icbmCAntidote", 1, 0));
			CR.shapeless(ST.make(MD.ICBM, "icbmCAntidote", 1, 0), new Object[] {MD.GC_GALAXYSPACE.mLoaded ? ST.make(MD.GC_GALAXYSPACE, "item.BasicItems", 1, 11) : IL.Pill_Iodine});
			
			Item tSheet = ST.item(MD.VOLTZ, "veSheetMetal");
			if (tSheet != null) {
				CR.delate(MD.ICBM, "icbmCMissile", 24);
				CR.shaped(ST.make(MD.ICBM, "icbmCMissile", 1, 24), CR.DEF_REV_NCC_MIR, "CAR", "FOR", "NON", 'A', ST.make(tSheet, 1, 7), 'O', ST.make(tSheet, 1, 12), 'R', ST.make(tSheet, 1, 15), 'N', ST.make(tSheet, 1, 17), 'F', OD.itemFlint, 'C', MT.DATA.CIRCUITS[1]);
			}
		}
	}
}
