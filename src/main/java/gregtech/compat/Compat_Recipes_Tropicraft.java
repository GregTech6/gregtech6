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

package gregtech.compat;

import static gregapi.data.CS.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.RM;
import gregapi.oredict.IOreDictListenerEvent;
import gregapi.oredict.OreDictListenerEvent_Names;
import gregapi.util.CR;
import gregapi.util.ST;

public class Compat_Recipes_Tropicraft extends CompatMods {
	public Compat_Recipes_Tropicraft(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Tropicraft Recipes.");
		CR.delate(MD.TROPIC, "pineappleCubes", "tile.blockOre", "tile.singleSlabs", "tile.plank");
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener("cropCoconut", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Split.get(0), ST.make(MD.TROPIC, "coconutChunk", 4, 0));
		}});
		addListener("cropAnanas", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Slicer.addRecipe2(T, 16, 16, aEvent.mStack, IL.Shape_Slicer_Grid.get(0), ST.make(MD.TROPIC, "pineappleCubes", 4, 0));
		}});
		}};
	}
}
