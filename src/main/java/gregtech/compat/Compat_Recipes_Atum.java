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
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Compat_Recipes_Atum extends CompatMods {
	public Compat_Recipes_Atum(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Atum Recipes.");
		long[] tChances = new long[] {1666, 1666, 1666, 1666, 1666, 1666};
		for (int i = 0; i < 5; i++) {
		ItemStack[] tInputs = ST.array(ST.make(MD.ATUM, "item.loot", 1, 1+(i*32))), tOutputs = ST.array(ST.make(MD.ATUM, "item.loot", 1, 2+(i*32)), ST.make(MD.ATUM, "item.loot", 1, 4+(i*32)), ST.make(MD.ATUM, "item.loot", 1, 6+(i*32)), ST.make(MD.ATUM, "item.loot", 1, 8+(i*32)), ST.make(MD.ATUM, "item.loot", 1, 10+(i*32)), ST.make(MD.ATUM, "item.loot", 1, 12+(i*32)));
		RM.Bath.addFakeRecipe(F, tInputs, tOutputs, null, tChances, FL.array(FL.Water.make(100)), ZL_FS, 512, 0, 0);
		RM.Bath.addFakeRecipe(F, tInputs, tOutputs, null, tChances, FL.array(FL.SpDew.make(100)), ZL_FS, 512, 0, 0);
		RM.Bath.addFakeRecipe(F, tInputs, tOutputs, null, tChances, FL.array(FL.DistW.make(100)), ZL_FS, 512, 0, 0);
		for (int j = 0; j < 6; j++) {
		RM.Bath.addRecipe1(F, 0, 512, ST.make(MD.ATUM, "item.loot", 1, 3+2*j+(i*32)), FL.Water.make(100), NF, ST.make(MD.ATUM, "item.loot", 1, 2+2*j+(i*32)));
		RM.Bath.addRecipe1(F, 0, 512, ST.make(MD.ATUM, "item.loot", 1, 3+2*j+(i*32)), FL.SpDew.make(100), NF, ST.make(MD.ATUM, "item.loot", 1, 2+2*j+(i*32)));
		RM.Bath.addRecipe1(F, 0, 512, ST.make(MD.ATUM, "item.loot", 1, 3+2*j+(i*32)), FL.DistW.make(100), NF, ST.make(MD.ATUM, "item.loot", 1, 2+2*j+(i*32)));
		}
		}
		
		for (FluidStack tWater : FL.array(FL.Water.make(125), FL.SpDew.make(125), FL.DistW.make(125)))
		RM.Bath         .addRecipe1(T,  0,   16, ST.make(MD.ATUM, "item.papyrusPlant", 1, 0), tWater, NF, ST.make(Items.paper, 1, 0));
		RM.Shredder     .addRecipe1(T, 16,   16, ST.make(MD.ATUM, "item.papyrusPlant", 1, 0), IL.Remains_Plant.get(1));
		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(0), ST.make(MD.ATUM, "item.papyrusPlant", 1, 0), ST.make(Items.paper, 1, 0));
		RM.Squeezer     .addRecipe1(T, 16,   16,  4000, ST.make(MD.ATUM, "item.papyrusPlant", 1, 0), NF, FL.Juice_Reed.make(100), IL.Remains_Plant.get(1));
		RM.Juicer       .addRecipe1(T, 16,   16,  5000, ST.make(MD.ATUM, "item.papyrusPlant", 1, 0), NF, FL.Juice_Reed.make(75), IL.Remains_Plant.get(1));
		RM.pulverizing(ST.make(MD.ATUM, "item.papyrusPlant", 1, 0), IL.Remains_Plant.get(1), T);
		RM.biomass(ST.make(MD.ATUM, "item.papyrusPlant", 8, 0));
		
		RM.generify(ST.make(MD.ATUM, "tile.sand", 1, W), ST.make(Blocks.sand, 1, 0));
		
		RM.Mortar.addRecipe1(T, 16, 64, ST.make(MD.ATUM, "item.stoneChunk", 1, W), OM.dust(MT.STONES.Limestone, U));
		
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1,  6), ST.make(MD.ATUM, "item.loot", 1,  4), OP.gem.mat(MT.BlueSapphire, 2));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1,  8), ST.make(MD.ATUM, "item.loot", 1,  4), OP.gem.mat(MT.Ruby, 2));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1, 10), ST.make(MD.ATUM, "item.loot", 1,  4), OP.gem.mat(MT.Emerald, 2));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1, 12), ST.make(MD.ATUM, "item.loot", 1,  4), OP.gem.mat(MT.Diamond, 2));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1, 38), ST.make(MD.ATUM, "item.loot", 1, 36), OP.gem.mat(MT.BlueSapphire, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1, 40), ST.make(MD.ATUM, "item.loot", 1, 36), OP.gem.mat(MT.Ruby, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1, 42), ST.make(MD.ATUM, "item.loot", 1, 36), OP.gem.mat(MT.Emerald, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1, 44), ST.make(MD.ATUM, "item.loot", 1, 36), OP.gem.mat(MT.Diamond, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1, 70), ST.make(MD.ATUM, "item.loot", 1, 68), OP.gem.mat(MT.BlueSapphire, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1, 72), ST.make(MD.ATUM, "item.loot", 1, 68), OP.gem.mat(MT.Ruby, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1, 74), ST.make(MD.ATUM, "item.loot", 1, 68), OP.gem.mat(MT.Emerald, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1, 76), ST.make(MD.ATUM, "item.loot", 1, 68), OP.gem.mat(MT.Diamond, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1,102), ST.make(MD.ATUM, "item.loot", 1,100), OP.gem.mat(MT.BlueSapphire, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1,104), ST.make(MD.ATUM, "item.loot", 1,100), OP.gem.mat(MT.Ruby, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1,106), ST.make(MD.ATUM, "item.loot", 1,100), OP.gem.mat(MT.Emerald, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1,108), ST.make(MD.ATUM, "item.loot", 1,100), OP.gem.mat(MT.Diamond, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1,134), ST.make(MD.ATUM, "item.loot", 1,132), OP.gemFlawless.mat(MT.BlueSapphire, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1,136), ST.make(MD.ATUM, "item.loot", 1,132), OP.gemFlawless.mat(MT.Ruby, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1,138), ST.make(MD.ATUM, "item.loot", 1,132), OP.gemFlawless.mat(MT.Emerald, 1));
		RM.Unboxinator.addRecipe1(F, 16, 16, ST.make(MD.ATUM, "item.loot", 1,140), ST.make(MD.ATUM, "item.loot", 1,132), OP.gemFlawless.mat(MT.Diamond, 1));
		
		
		new OreDictListenerEvent_Names(OP.crop) {@Override public void addAllListeners() {
		addListener("cropFlax", new IOreDictListenerEvent() {@Override public void onOreRegistration(OreDictRegistrationContainer aEvent) {
		RM.Loom         .addRecipe2(T, 16,   16, ST.tag(3), ST.amount(3, aEvent.mStack), ST.make(MD.ATUM, "item.linen", 1));
		}});
		}};
		
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(4), ST.make(MD.ATUM, "item.linen", 5), ST.make(MD.ATUM, "item.wandererHelmet", 1));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(5), ST.make(MD.ATUM, "item.linen", 8), ST.make(MD.ATUM, "item.wandererChest", 1));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(6), ST.make(MD.ATUM, "item.linen", 7), ST.make(MD.ATUM, "item.wandererLegs", 1));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(7), ST.make(MD.ATUM, "item.linen", 4), ST.make(MD.ATUM, "item.wandererBoots", 1));
		
		RM.Loom         .addRecipe2(T, 16,  128, ST.make(Items.iron_helmet      , 1, 0), ST.make(MD.ATUM, "item.wandererHelmet" , 1), ST.make(MD.ATUM, "item.desertHelmet"  , 1));
		RM.Loom         .addRecipe2(T, 16,  128, ST.make(Items.iron_chestplate  , 1, 0), ST.make(MD.ATUM, "item.wandererChest"  , 1), ST.make(MD.ATUM, "item.desertChest"   , 1));
		RM.Loom         .addRecipe2(T, 16,  128, ST.make(Items.iron_leggings    , 1, 0), ST.make(MD.ATUM, "item.wandererLegs"   , 1), ST.make(MD.ATUM, "item.desertLegs"        , 1));
		RM.Loom         .addRecipe2(T, 16,  128, ST.make(Items.iron_boots       , 1, 0), ST.make(MD.ATUM, "item.wandererBoots"  , 1), ST.make(MD.ATUM, "item.desertBoots"   , 1));
		
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(4), ST.make(MD.ATUM, "item.clothScrap", 5), ST.make(MD.ATUM, "item.mummyHelmet", 1));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(5), ST.make(MD.ATUM, "item.clothScrap", 8), ST.make(MD.ATUM, "item.mummyChest", 1));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(6), ST.make(MD.ATUM, "item.clothScrap", 7), ST.make(MD.ATUM, "item.mummyLegs", 1));
		RM.Loom         .addRecipe2(T, 16,  128, ST.tag(7), ST.make(MD.ATUM, "item.clothScrap", 4), ST.make(MD.ATUM, "item.mummyBoots", 1));
	}
}
