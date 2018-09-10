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

import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Blocks;

public class Loader_Recipes_Chisel implements Runnable {
	@Override
	public void run() {if (MD.CHSL.mLoaded) {OUT.println("GT_Mod: Doing Chisel Mod Recipes.");
		RM.rem_smelting(ST.make(Blocks.gravel, 1, 0));
		
		CR.remout(IL.CHSL_Granite.get(1));
		CR.remout(IL.CHSL_Diorite.get(1));
		CR.remout(IL.CHSL_Andesite.get(1));
		CR.remout(IL.CHSL_Granite_Smooth.get(1));
		CR.remout(IL.CHSL_Diorite_Smooth.get(1));
		CR.remout(IL.CHSL_Andesite_Smooth.get(1));
	}}
}
