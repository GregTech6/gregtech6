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
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.ST;

public class Compat_Recipes_CandyCraft extends CompatMods {
	public Compat_Recipes_CandyCraft(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing CandyCraft Recipes.");
		CR.delate(MD.CANDY, "I48", "I49");
		CR.remove(NI, ST.make(MD.CANDY, "I49", 1, 0), NI, ST.make(MD.CANDY, "I49", 1, 0));
		
		RM.packunpack(ST.make(MD.CANDY, "B48", 4, 0), ST.make(MD.CANDY, "B18", 1, 0)); // Mint
		RM.packunpack(ST.make(MD.CANDY, "I60", 1, 0), ST.make(MD.CANDY, "B67", 1, 0)); // Cotton Candy
		RM.packunpack(ST.make(MD.CANDY, "I91", 4, 0), ST.make(MD.CANDY, "B97", 1, 0)); // Chewing Gum
		RM.packunpack(ST.make(MD.CANDY, "B82", 4, 0), ST.make(MD.CANDY, "B96", 1, 0)); // Banana Seaweeds
		RM.packunpack(ST.make(MD.CANDY, "B47", 4, 0), ST.make(MD.CANDY, "B19", 1, 0)); // Raspberry Chain
		RM.packunpack(ST.make(MD.CANDY, "I12", 2, 0), ST.make(MD.CANDY, "BarleyBlock", 1, 0));
		RM.packunpack(OP.dust.mat(MT.Sugar, 4), ST.make(MD.CANDY, "B39", 1, 0));
	}
}
