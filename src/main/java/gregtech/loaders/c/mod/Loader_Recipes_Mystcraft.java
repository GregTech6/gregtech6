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

import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.util.CR;
import net.minecraftforge.fluids.FluidStack;

public class Loader_Recipes_Mystcraft implements Runnable {
	@Override
	public void run() {
		if (MD.MYST.mLoaded) {OUT.println("GT_Mod: Doing Mystcraft Recipes.");
			
			CR.remout(IL.Myst_Ink_Vial.get(1));
			
			for (FluidStack tDye : DYE_FLUIDS[DYE_INDEX_Black]) {
			RM.Mixer			.addRecipe0(T, 16,	 16, new FluidStack[] {tDye, FL.Water.make(125)}, FL.Myst_Ink.make(500), ZL_IS);
			RM.Mixer			.addRecipe0(T, 16,	 16, new FluidStack[] {tDye, FL.DistW.make(125)}, FL.Myst_Ink.make(500), ZL_IS);
			}
		}
	}
}
