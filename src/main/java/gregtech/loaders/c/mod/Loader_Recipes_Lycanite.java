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
import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Items;

public class Loader_Recipes_Lycanite implements Runnable {
	@Override
	public void run() {if (MD.LycM.mLoaded) {OUT.println("GT_Mod: Doing Lycanite Mobs Recipes.");
		CR.remout(MD.LycM_Inferno, "bucketpurelava");
		if (FL.Lava_Pure.exists()) RM.Mixer.addRecipe1(T, 16, 16, ST.make(Items.ghast_tear, 1, W), FL.Lava.make(1000), FL.Lava_Pure.make(1000), ZL_IS);
	}}
}
