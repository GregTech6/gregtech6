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
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.util.ST;

public class Compat_Recipes_Factorization extends CompatMods {
	public Compat_Recipes_Factorization(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Factorization Recipes.");
		RM.Canner.addRecipe1(T, 16, 16, IL.Bottle_Empty.get(1), MT.H2SO4    .fluid(U, T), NF, ST.make(MD.FZ, "acid", 1, 0));
		RM.Canner.addRecipe1(T, 16, 16, IL.Bottle_Empty.get(1), MT.AquaRegia.fluid(U, T), NF, ST.make(MD.FZ, "acid", 1, 1));
		
		RM.sawing(16, 800, F, 5000, ST.make(MD.FZ, "daybarrel", 1, W), IL.Plank.get(42), OP.dustSmall.mat(MT.Wood, 30));
		
		RM.generify(ST.make(MD.FZ, "diamond_shard", 1, W), OP.gemFlawed.mat(MT.Diamond, 1));
	}
}
