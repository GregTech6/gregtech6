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

package gregtech.loaders.c;

import static gregapi.data.CS.*;

import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;

/**
 * @author Gregorius Techneticies
 * 
 * Here is basically everything that I want to change to something better later.
 */
public class Loader_Recipes_Temporary implements Runnable {
	@Override public void run() {OUT.println("GT_Mod: Doing Temporary Recipes for GregTech things that are not quite fleshed out yet.");
		// TODO: Graphite Electrodes are made from petroleum coke after it is mixed with coal tar pitch. They are then extruded and shaped, baked to carbonize the binder (pitch) and finally graphitized by heating it to temperatures approaching 3273K.
		RM.Extruder.addRecipe2(T, 512, 512, OP.dust.mat(MT.Graphite, 1), IL.Shape_Extruder_Rod.get(0), OP.stick.mat(MT.Graphite, 1));
		
		
		
	}
}
