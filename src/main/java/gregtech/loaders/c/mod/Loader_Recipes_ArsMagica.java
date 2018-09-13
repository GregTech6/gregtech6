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
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictListenerEvent_Names;
import gregapi.oredict.OreDictListenerEvent_ThreeNames;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Loader_Recipes_ArsMagica implements Runnable {
	@Override
	public void run() {if (MD.ARS.mLoaded) {OUT.println("GT_Mod: Doing Ars Magica Recipes.");
		CR.remout(MD.ARS, "manacake");
		
		CR.shaped(ST.make(MD.ARS, "crystal_wrench", 1, 0), CR.DEF_REV_MIR | CR.DEL_OTHER_NATIVE_RECIPES, "I I", "CVD", " I ", 'C', "flowerCerublossom", 'D', "flowerDesertNova", 'V', OP.dust.dat(MT.Vinteum), 'I', OP.ingot.dat(ANY.Fe));
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener(new OreDictListenerEvent_ThreeNames("flowerCerublossom", "flowerDesertNova", "foodSugarDough") {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2, ItemStack aStack3) {
		RM.Mixer.addRecipeX(T, 16, 16, new ItemStack[] {aStack1, aStack2, ST.amount(2, aStack3)}, ST.make(MD.ARS, "manaCake", 3, 0));
		}});
		}};
		
		RM.Injector.addRecipe2(T, 16, 16, IL.Bottle_Empty.get(1)                                , ST.make(Items.wheat_seeds, 1, W), UT.Fluids.make("potion.mundane", 250), NF, ST.make(MD.ARS, "lesserManaPotion", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, ST.make(Items.potionitem                  , 1,   64)  , ST.make(Items.wheat_seeds, 1, W), ST.make(MD.ARS, "lesserManaPotion", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, ST.make(Items.potionitem                  , 1,16384)  , ST.make(Items.wheat_seeds, 1, W), ST.make(MD.ARS, "standardManaPotion", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, ST.make(MD.ARS, "lesserManaPotion"        , 1,    0)  , OM.dust(MT.Gunpowder), ST.make(MD.ARS, "standardManaPotion", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, ST.make(MD.ARS, "standardManaPotion"      , 1,    0)  , OM.dust(MT.Vinteum), ST.make(MD.ARS, "greaterManaPotion", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, ST.make(MD.ARS, "greaterManaPotion"       , 1,    0)  , OM.dust(MT.ArcaneAsh), ST.make(MD.ARS, "epicManaPotion", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, ST.make(MD.ARS, "epicManaPotion"          , 1,    0)  , OM.dust(MT.VinteumPurified), ST.make(MD.ARS, "legendaryManaPotion", 1, 0));
	}}
}
