/**
 * Copyright (c) 2022 GregTech-6 Team
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

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.*;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.oredict.event.OreDictListenerEvent_TwoNames;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import static gregapi.data.CS.*;

public class Compat_Recipes_MineFactoryReloaded extends CompatMods {
	public Compat_Recipes_MineFactoryReloaded(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {
		OUT.println("GT_Mod: Doing MineFactory Reloaded Recipes.");
		CR.delate(MD.MFR, "machine.2", 0, 1);
		CR.remove(OM.dust(MT.Plastic), OM.dust(MT.Plastic), NI, OM.dust(MT.Plastic), OM.dust(MT.Plastic));
		CR.remove(ST.make(Items.milk_bucket, 1, 0), ST.make(Items.bucket, 1, 0), IL.Dye_Cocoa.get(1));
		
		CR.shaped(ST.make(MD.MFR, "machine.2" , 1, 0), CR.DEF_REV_NCC, new Object[] {"CDC", "DMD", "CDC", 'C', OD_CIRCUITS[6], 'D', OP.lens.dat(ANY.Diamond), 'M', OP.casingMachineQuadruple.dat(MT.Ad)});
		CR.shaped(ST.make(MD.MFR, "machine.2" , 1, 1), CR.DEF_REV_NCC, new Object[] {"CSC", "LML", "CDC", 'C', OD_CIRCUITS[6], 'D', OP.lens.dat(ANY.Diamond), 'M', OP.casingMachineQuadruple.dat(MT.Ad), 'L', IL.Comp_Laser_Gas_CO2, 'S', ST.make(MD.MFR, "pinkslime", 1, 1)});
		
		for (String tFluid : FluidsGT.MILK) if (FL.exists(tFluid)) {
			RM.Mixer.addRecipe1(T, 16, 16, ST.make(Blocks.dirt, 1, 2), FL.array(FL.make(tFluid, 200), FL.Sewage.make(2000)), ZL_FS, ST.make(MD.MFR, "farmland", 1, 0));
			RM.Mixer.addRecipe2(T, 16, 16, ST.make(Blocks.dirt, 1, 2), IL.MFR_Fertilizer.get(1), FL.make(tFluid, 200), NF, ST.make(MD.MFR, "farmland", 1, 0));
		}
		
		RM.Coagulator.addRecipe0(T, 0, 2048, FL.Sewage.make(2000), NF, IL.MFR_Fertilizer.get(1));
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		for (byte i = 0; i < 16; i++) {
			final int aIndex = i;
			addListener(new OreDictListenerEvent_TwoNames(DYE_OREDICTS_MIXABLE[aIndex], OD.itemClay) {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
				RM.Mixer.addRecipe2(T, 16, 16, aStack1, aStack2, ST.make(MD.MFR, "ceramicdye", 4, 15-aIndex));
			}});
		}
		addListener(new OreDictListenerEvent_TwoNames(OD.listAllwheats, OP.dust.dat(ANY.Wood)) {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
			RM.Mixer.addRecipeX(T, 16, 512, ST.array(ST.amount(8, aStack1), aStack2, OM.dust(MT.Bone, U*4), OM.dust(MT.S    , U *4)), IL.MFR_Fertilizer.get(48));
			RM.Mixer.addRecipeX(T, 16, 512, ST.array(ST.amount(8, aStack1), aStack2, OM.dust(MT.Bone, U*4), OM.dust(MT.Blaze, U9*4)), IL.MFR_Fertilizer.get(48));
		}});
		addListener(new OreDictListenerEvent_TwoNames(OD.listAllwheats, OP.dustSmall.dat(ANY.Wood)) {@Override public void onOreRegistration(ItemStack aStack1, ItemStack aStack2) {
			RM.Mixer.addRecipeX(T, 16, 128, ST.array(ST.amount(2, aStack1), aStack2, OM.dust(MT.Bone     ), OM.dust(MT.S          )), IL.MFR_Fertilizer.get(12));
			RM.Mixer.addRecipeX(T, 16, 128, ST.array(ST.amount(2, aStack1), aStack2, OM.dust(MT.Bone     ), OM.dust(MT.Blaze, U9  )), IL.MFR_Fertilizer.get(12));
		}});
		addListener(OD.itemClay, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			for (byte i = 0; i < 16; i++) for (FluidStack tDye : DYE_FLUIDS[i])
			RM.Mixer.addRecipe1(T, 16, 16, aEvent.mStack, tDye, NF, ST.make(MD.MFR, "ceramicdye", 4, 15-i));
		}});
		addListener(OD.slimeballPink, new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.packunpack(aEvent.mStack, 9, ST.make(MD.MFR, "pinkslime.block", 1, 0));
		}});
		}};
	}
}
