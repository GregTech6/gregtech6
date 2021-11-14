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
import gregapi.data.FL;
import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Items;

public class Compat_Recipes_Lycanites extends CompatMods {
	public Compat_Recipes_Lycanites(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Lycanite Mobs Recipes.");
		CR.delate(MD.LycM_Inferno, "bucketpurelava");
		if (FL.Lava_Pure.exists()) {
			RM.Mixer.addRecipe1(T, 16, 16, ST.make(Items.ghast_tear, 1, W), FL.Lava         .make(1000), FL.Lava_Pure.make(1000), ZL_IS);
			RM.Mixer.addRecipe1(T, 16, 16, ST.make(Items.ghast_tear, 1, W), FL.Lava_Pahoehoe.make(1000), FL.Lava_Pure.make(1000), ZL_IS);
			if (FL.Lava_Volcanic.exists())
			RM.Mixer.addRecipe1(T, 16, 16, ST.make(Items.ghast_tear, 1, W), FL.Lava_Volcanic.make(1000), FL.Lava_Pure.make(1000), ZL_IS);
		}
	}
}
