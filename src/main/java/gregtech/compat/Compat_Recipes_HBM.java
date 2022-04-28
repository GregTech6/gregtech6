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
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraftforge.fluids.FluidStack;

import static gregapi.data.CS.*;

public class Compat_Recipes_HBM extends CompatMods {
	public Compat_Recipes_HBM(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing HBM Recipes.");
		CR.delate(MD.HBM, "item.apple_lead", "item.apple_schrabidium", "item.apple_euphemium");
		
		CR.shapeless(IL.HBM_Mercury_Drop.get(8), CR.DEF_NCC, new Object[] {IL.Bottle_Mercury});
		RM.generify(IL.HBM_Mercury_Bottle.get(1), IL.Bottle_Mercury.get(1));
		RM.generify(IL.Bottle_Mercury.get(1), IL.HBM_Mercury_Bottle.get(1));
		RM.packunpack(OP.round.mat(MT.Pb, 6), ST.make(MD.HBM, "item.pellet_buckshot", 1, 0));
		
		
		RM.Crusher.addRecipe1(F, 16, 512, ST.make(MD.HBM, "tile.ore_nether_coal", 1, 0), ST.make(MD.HBM, "item.coal_infernal", 1, 0), ST.make(MD.HBM, "item.coal_infernal", 1, 0), ST.make(MD.HBM, "item.coal_infernal", 1, 0), OP.dust.mat(MT.Netherrack, 1), OP.dust.mat(MT.Netherrack, 1));
		
		RM.Bath.addRecipe1(T, 0, 128, ST.make(MD.HBM, "item.apple_lead", 1, 0), MT.Pb.liquid(64*U9, T), NF, ST.make(MD.HBM, "item.apple_lead", 1, 1));
		RM.Bath.addRecipe1(T, 0, 128, ST.make(MD.HBM, "item.apple_lead", 1, 1), MT.Pb.liquid(64*U , T), NF, ST.make(MD.HBM, "item.apple_lead", 1, 2));
		
		RM.Bath.addRecipe1(T, 0, 128, ST.make(MD.HBM, "item.apple_schrabidium", 1, 0), MT.UNUSED.Schrabidium.liquid(64*U9, T), NF, ST.make(MD.HBM, "item.apple_schrabidium", 1, 1));
		RM.Bath.addRecipe1(T, 0, 128, ST.make(MD.HBM, "item.apple_schrabidium", 1, 1), MT.UNUSED.Schrabidium.liquid(64*U , T), NF, ST.make(MD.HBM, "item.apple_schrabidium", 1, 2));
		
		RM.Bath.addRecipe1(T, 0, 512, ST.make(MD.HBM, "item.powder_desh_ready", 1, 0), MT.H2O2.fluid(U2, T), NF, OM.dust(MT.DeshAlloy, U*1));
		
		RM.Mixer.addRecipe2(T, 16, 32, OP.dust.mat(MT.Desh                      , 1), OP.dust.mat(MT.C, 1), MT.Hg.fluid(U4, T), NF, ST.make(MD.HBM, "item.powder_desh_ready", 1, 0));
		RM.Mixer.addRecipe2(T, 16, 32, ST.make(MD.HBM, "item.powder_desh_mix", 1, 0), OP.dust.mat(MT.C, 1), MT.Hg.fluid(U4, T), NF, ST.make(MD.HBM, "item.powder_desh_ready", 1, 0));
		
		RM.Injector.addRecipe1(T, 16, 256, ST.make(MD.HBM, "item.powder_desh_mix", 1, 0), FL.array(MT.Hg.fluid(U, T), FL.Fuel.make(400)), ZL_FS, OM.dust(MT.DeshAlloy));
		
		for (FluidStack tWater : FL.waters(250))
		RM.Mixer.addRecipe1(T, 16, 16, IL.HBM_Poison_Powder.get(1), tWater, FL.Potion_Poison_2.make(250), ZL_IS);
		if (IL.ERE_Herbicide.exists())
		RM.pulverizing(IL.ERE_Herbicide                  .get(1), IL.HBM_Poison_Powder.get(1));
		RM.pulverizing(IL.Food_Potato_Poisonous          .get(1), IL.HBM_Poison_Powder.get(1));
		RM.pulverizing(ST.make(Blocks.red_mushroom       , 1, W), IL.HBM_Poison_Powder.get(1));
		RM.pulverizing(ST.make(Items.spider_eye          , 1, W), IL.HBM_Poison_Powder.get(2));
		RM.pulverizing(ST.make(Items.fermented_spider_eye, 1, W), IL.HBM_Poison_Powder.get(3));
		if (IL.ERE_Herbicide.exists())
		RM.Shredder.addRecipe1(T, 16, 16, IL.ERE_Herbicide                  .get(1), IL.HBM_Poison_Powder.get(1));
		RM.Shredder.addRecipe1(T, 16, 16, IL.Food_Potato_Poisonous          .get(1), IL.HBM_Poison_Powder.get(1));
		RM.Shredder.addRecipe1(T, 16, 16, ST.make(Blocks.red_mushroom       , 1, W), IL.HBM_Poison_Powder.get(1));
		RM.Shredder.addRecipe1(T, 16, 16, ST.make(Items.spider_eye          , 1, W), IL.HBM_Poison_Powder.get(2));
		RM.Shredder.addRecipe1(T, 16, 16, ST.make(Items.fermented_spider_eye, 1, W), IL.HBM_Poison_Powder.get(3));
		RM.generify(IL.IC2_Grin_Powder.get(1), IL.HBM_Poison_Powder.get(1));
		RM.generify(IL.HBM_Poison_Powder.get(1), IL.IC2_Grin_Powder.get(1));
		
		
		RM.Compressor.addRecipe1(T, 16, 16, IL.HBM_Biomass           .get(1), IL.HBM_Biomass_Compressed.get(1));
		RM.Shredder  .addRecipe1(T, 16, 16, IL.HBM_Biomass           .get(1), ST.make(Blocks.dirt, 1, 0));
		RM.Shredder  .addRecipe1(T, 16, 16, IL.HBM_Biomass_Compressed.get(1), ST.make(Blocks.dirt, 1, 0));
		RM.biomass(IL.HBM_Biomass           .get(1), 32);
		RM.biomass(IL.HBM_Biomass_Compressed.get(1), 32);
		RM.generify(IL.IC2_Plantball.get(1), IL.HBM_Biomass.get(1));
		RM.generify(IL.HBM_Biomass.get(1), IL.IC2_Plantball.get(1));
		
		
		new OreDictListenerEvent_Names() {@Override public void addAllListeners() {
		addListener("cropApple", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
			if (OM.is("cropAppleWhite", aEvent.mStack) || OM.is("cropCrabapple", aEvent.mStack)) return;
			RM.Bath.addRecipe1(T, 0, 128, aEvent.mStack, MT.Pb                .liquid(8*U9, T), NF, ST.make(MD.HBM, "item.apple_lead"       , 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, aEvent.mStack, MT.UNUSED.Schrabidium.liquid(8*U9, T), NF, ST.make(MD.HBM, "item.apple_schrabidium", 1, 0));
			RM.Bath.addRecipe1(T, 0, 128, aEvent.mStack, MT.UNUSED.Euphemium  .liquid(8*U9, T), NF, ST.make(MD.HBM, "item.apple_euphemium"  , 1, 0));
		}});
		}};
	}
}
