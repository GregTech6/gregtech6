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
import gregapi.data.ANY;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.oredict.event.OreDictListenerEvent_TwoNames;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Compat_Recipes_MineFactoryReloaded extends CompatMods {
	public Compat_Recipes_MineFactoryReloaded(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {
		OUT.println("GT_Mod: Doing MineFactory Reloaded Recipes.");
		CR.remove(OM.dust(MT.Plastic), OM.dust(MT.Plastic), NI, OM.dust(MT.Plastic), OM.dust(MT.Plastic));
		CR.remove(ST.make(Items.milk_bucket, 1, 0), ST.make(Items.bucket, 1, 0), IL.Dye_Cocoa.get(1));
		
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		
		for (byte i = 0; i < 16; i++) {
			final int aIndex = i;
			addListener(new OreDictListenerEvent_TwoNames(DYE_OREDICTS_MIXABLE[aIndex], "itemClay") {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
				RM.Mixer.addRecipe2(T, 16, 16, aStack1, aStack2, ST.make(MD.MFR, "ceramicdye", 4, 15-aIndex));
			}});
		}
		addListener(new OreDictListenerEvent_TwoNames("listAllwheats", OP.dust.dat(ANY.Wood)) {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
			RM.Mixer    .addRecipeX(T, 16,  512, ST.array(ST.amount(8, aStack1), aStack2, OM.dust(MT.Bone, U*4), OM.dust(MT.S, U*4)), IL.MFR_Fertilizer.get(48));
		}});
		addListener(new OreDictListenerEvent_TwoNames("listAllwheats", OP.dustSmall.dat(ANY.Wood)) {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
			RM.Mixer    .addRecipeX(T, 16,  128, ST.array(ST.amount(2, aStack1), aStack2, OM.dust(MT.Bone     ), OM.dust(MT.S     )), IL.MFR_Fertilizer.get(12));
		}});
		addListener("itemClay", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			for (byte i = 0; i < 16; i++) for (FluidStack tDye : DYE_FLUIDS[i])
			RM.Mixer    .addRecipe1(T, 16,   16, ST.make(Items.clay_ball, 1, W), tDye, NF, ST.make(MD.MFR, "ceramicdye", 4, 15-i));
		}});
		}};
		
		RM.Coagulator   .addRecipe0(T,  0, 2048, FL.Sewage.make(2000), NF, IL.MFR_Fertilizer.get(1));
		RM.Coagulator   .addRecipe0(T,  0,  256, FL.Slime_Pink.make(100), NF, OP.nugget.mat(MT.MeatRaw, 1));
	}
}
