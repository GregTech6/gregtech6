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

package gregtech.loaders.c.mod;

import static gregapi.data.CS.*;

import gregapi.data.ANY;
import gregapi.data.MD;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.util.CR;
import gregapi.util.ST;


public class Loader_Recipes_ProjectRed implements Runnable {
	@Override
	public void run() {
		if (MD.PR.mLoaded) {OUT.println("GT_Mod: Doing PR Recipes.");
			CR.shaped(ST.make(MD.PR, "projectred.core.part", 1, 40), CR.DEF | CR.DEL_OTHER_NATIVE_RECIPES, " D ", "DID", " D ", 'D', OD.itemRedstone, 'I', OP.ingot.dat(ANY.Cu));
			
			
			
		}
	}
}
