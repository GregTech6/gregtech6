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
import gregapi.data.ANY;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Compat_Recipes_Reika extends CompatMods {
	public Compat_Recipes_Reika(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {
		if (MD.RoC.mLoaded) {
			OUT.println("GT_Mod: Doing RotaryCraft Recipes.");
			CR.delate(MD.RoC, "rotarycraft_item_machine"   , 116);
			CR.delate(MD.RoC, "rotarycraft_item_borecraft" , 13, 14);
			CR.delate(MD.RoC, "rotarycraft_item_shaftcraft", 0, 2, 9, 10);
			new OreDictListenerEvent_Names(OP.seed) {@Override public void addAllListeners() {
			addListener("seedCanola", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.Shredder         .addRecipe1(T, 16,   16, aEvent.mStack, ST.make(MD.RoC, "rotarycraft_item_canola", 1, 2));
			}});
			}};
			RM.Shredder         .addRecipe1(T, 16,  144, ST.make(MD.RoC, "rotarycraft_item_canola", 1, 1), ST.make(MD.RoC, "rotarycraft_item_canola", 9, 2));
			RM.Squeezer         .addRecipe1(T, 16,  144, ST.make(MD.RoC, "rotarycraft_item_canola", 1, 1), NF, FL.lube(405), NI);
			RM.Squeezer         .addRecipe1(T, 16,   16, ST.make(MD.RoC, "rotarycraft_item_canola", 1, 2), NF, FL.lube( 90), NI);
			RM.Juicer           .addRecipe1(T, 16,  144, ST.make(MD.RoC, "rotarycraft_item_canola", 1, 1), NF, FL.lube(270), NI);
			RM.Juicer           .addRecipe1(T, 16,   16, ST.make(MD.RoC, "rotarycraft_item_canola", 1, 2), NF, FL.lube( 60), NI);
			
			RM.Compressor       .addRecipe1(T,512, 1024, OM.dust(MT.Bedrock, U*8), ST.make(Blocks.bedrock, 1, 0));
			
			for (FluidStack tRedstone : FL.array(FL.Redstone.make(L), FL.Redstone_TE.make(100))) if (tRedstone != null) {
			RM.Bath             .addRecipe1(T,  0,   64, IL.Circuit_Board_HSLA_Circuit.get(1), FL.mul(tRedstone, 2, 9, T), NF, ST.make(MD.RoC, "rotarycraft_item_borecraft", 1, 4));
			RM.Bath             .addRecipe1(T,  0,   64, IL.Circuit_Board_Power_Module.get(1), FL.mul(tRedstone, 2, 9, T), NF, ST.make(MD.RoC, "rotarycraft_item_misccraft", 1, 2));
			}
			
			RM.Centrifuge       .addRecipe1(T, 16,   64,  8000, IL.RoC_Comb_Slippery    .get(1), NF, FL.lube( 50), IL.RoC_Propolis_Slippery.get(1));
			RM.Centrifuge       .addRecipe1(T, 16,   64       , IL.RoC_Propolis_Slippery.get(1), NF, FL.lube(150), ZL_IS);
			
			
			RM.Drying           .addRecipe0(T, 16,   64, FL.Oil_ExtraHeavy.make(100), NF, ST.make(MD.RoC, "rotarycraft_item_powders", 1, 1));
			RM.Drying           .addRecipe0(T, 16,   64, FL.Oil_Heavy     .make(150), NF, ST.make(MD.RoC, "rotarycraft_item_powders", 1, 1)); if (FL.Oil_Heavy2.exists())
			RM.Drying           .addRecipe0(T, 16,   64, FL.Oil_Heavy2    .make(150), NF, ST.make(MD.RoC, "rotarycraft_item_powders", 1, 1));
			RM.Drying           .addRecipe0(T, 16,   64, FL.Oil_Medium    .make(200), NF, ST.make(MD.RoC, "rotarycraft_item_powders", 1, 1));
			RM.Drying           .addRecipe0(T, 16,   64, FL.Oil_Normal    .make(200), NF, ST.make(MD.RoC, "rotarycraft_item_powders", 1, 1)); if (FL.Oil_HotCrude.exists())
			RM.Drying           .addRecipe0(T, 16,   64, FL.Oil_HotCrude  .make(200), NF, ST.make(MD.RoC, "rotarycraft_item_powders", 1, 1));
			RM.Drying           .addRecipe0(T, 16,   64, FL.Oil_Light     .make(250), NF, ST.make(MD.RoC, "rotarycraft_item_powders", 1, 1)); if (FL.Oil_Light2.exists())
			RM.Drying           .addRecipe0(T, 16,   64, FL.Oil_Light2    .make(250), NF, ST.make(MD.RoC, "rotarycraft_item_powders", 1, 1));
			RM.Drying           .addRecipe0(T, 16,   64, FL.Oil_Soulsand  .make( 50), NF, ST.make(MD.RoC, "rotarycraft_item_powders", 1, 1));
			
			if (FL.exists("rc co2"))
			RM.Freezer          .addRecipe1(T, 64,   64, ST.tag(0), FL.make("rc co2" ,  200), NF, ST.make(MD.RoC, "rotarycraft_item_powders", 1, 11));
			RM.Freezer          .addRecipe1(T, 64,   64, ST.tag(0), MT.CO2       .gas(U , T), NF, ST.make(MD.RoC, "rotarycraft_item_powders", 1, 11));
			RM.Freezer          .addRecipe1(T, 16,   16, ST.tag(0), FL.BioEthanol.make(1000), NF, IL.RoC_Ethanol_Crystal.get(1));
			RM.Freezer          .addRecipe1(T, 16,   16, ST.tag(0), FL.Reikanol  .make(1000), NF, IL.RoC_Ethanol_Crystal.get(1));
			RM.Freezer          .addRecipe1(T, 64,  128, ST.tag(0), MT.N         .gas(U , T), FL.make("rc liquid nitrogen", 1000), ZL_IS);
			
			RM.Freezer          .addRecipe1(T, 16,   16, IL.RoC_Ethanol_Extract.get(1), IL.RoC_Ethanol_Crystal.get(1));
			
			
			RM.Smelter          .addRecipe1(T, 16,   16, IL.RoC_Ethanol_Extract.get(1), NF, FL.Reikanol.make(1000), ZL_IS);
			RM.Smelter          .addRecipe1(T, 16,   16, IL.RoC_Ethanol_Crystal.get(1), NF, FL.Reikanol.make(1000), ZL_IS);
			
			
			for (FluidStack tFuel : FL.array(FL.Kerosine.make(8000), FL.make("kerosene", 8000), FL.Fuel.make(16000), FL.BioEthanol.make(16000), FL.Reikanol.make(16000), FL.make("ethanol", 16000))) if (tFuel != null) {
			RM.Mixer            .addRecipeX(T, 16, 2048, ST.array(OM.dust(MT.Blaze, U9), OM.dust(MT.Netherrack), ST.make(MD.RoC, "rotarycraft_item_powders", 1, 1), ST.make(Items.magma_cream, 1, W)), tFuel, FL.JetFuel.make(4000), ZL_IS);
			}
			
			CR.delate(MD.RoC, "rotarycraft_item_powders", 6, 7);
			RM.Mixer            .addRecipeX(T, 16,   64, ST.array(OM.dust(MT.Redstone, U ), OM.dust(MT.Coal, U ), OM.dust(MT.NaCl,U ), OM.dust(MT.Gunpowder, U )), ST.make(MD.RoC, "rotarycraft_item_powders", 4, 6));
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Redstone, U4), OM.dust(MT.Coal, U4), OM.dust(MT.NaCl,U4), OM.dust(MT.Gunpowder, U4)), ST.make(MD.RoC, "rotarycraft_item_powders", 1, 6));
			RM.Mixer            .addRecipeX(T, 16,   64, ST.array(OM.dust(MT.Redstone, U ), OM.dust(MT.Coal, U ), OM.dust(MT.KCl, U ), OM.dust(MT.Gunpowder, U )), ST.make(MD.RoC, "rotarycraft_item_powders", 4, 6));
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Redstone, U4), OM.dust(MT.Coal, U4), OM.dust(MT.KCl, U4), OM.dust(MT.Gunpowder, U4)), ST.make(MD.RoC, "rotarycraft_item_powders", 1, 6));
		}
		if (MD.ReC.mLoaded) {
			OUT.println("GT_Mod: Doing ReactorCraft Recipes.");
			final ItemStack tQuicklime = ST.make(MD.ReC, "reactorcraft_item_raw", 1, 4);
			new OreDictListenerEvent_Names(OP.seed) {@Override public void addAllListeners() {
			addListener("gemAnyCalcite", "dustAnyCalcite", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			RM.add_smelting(aEvent.mStack, tQuicklime, F, F, T);
			}});
			}};
		}
		if (MD.ElC.mLoaded) {
			OUT.println("GT_Mod: Doing ElectriCraft Recipes.");
			CR.delate(MD.ElC, "electricraft_item_crafting", 3);
			for (OreDictMaterial tMat1 : ANY.Glowstone.mToThis) for (OreDictMaterial tMat2 : ANY.SiO2.mToThis) for (OreDictMaterial tMat3 : ANY.Diamond.mToThis) {
			RM.Mixer            .addRecipeX(T, 16,   32, ST.array(OM.dust(MT.Redstone, U*4), OM.dust(tMat1, U*1), OM.dust(MT.Lapis, U*1), OM.dust(tMat2, U*1), OM.dust(tMat3, U*1)), ST.make(MD.ElC, "electricraft_item_crafting", 2, 3));
			RM.Mixer            .addRecipeX(T, 16,   16, ST.array(OM.dust(MT.Redstone, U*2), OM.dust(tMat1, U2 ), OM.dust(MT.Lapis, U2 ), OM.dust(tMat2, U2 ), OM.dust(tMat3, U2 )), ST.make(MD.ElC, "electricraft_item_crafting", 1, 3));
			}
		}
		if (MD.CrC.mLoaded) {
			OUT.println("GT_Mod: Doing ChromatiCraft Recipes.");
		}
	}
}
