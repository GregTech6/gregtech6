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
import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.ST;

public class Compat_Recipes_OpenComputers extends CompatMods {
	public Compat_Recipes_OpenComputers(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Open Computers Recipes.");
		RM.rem_smelting(ST.make(MD.OC, "item", 1, 30));
		CR.delate(MD.OC, "item", 30);
		CR.delate(MD.OC, "wrench");
		CR.shapeless(ST.make(MD.OC, "item", 1, 32), new Object[] {OD_CIRCUITS[3]});
	}
}
