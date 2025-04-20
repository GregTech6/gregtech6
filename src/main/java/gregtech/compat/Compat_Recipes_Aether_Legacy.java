/**
 * Copyright (c) 2025 GregTech-6 Team
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
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

import static gregapi.data.CS.*;
import static gregapi.util.CR.DEF;
import static gregapi.util.CR.DEL_OTHER_SHAPED_RECIPES;

public class Compat_Recipes_Aether_Legacy extends CompatMods {
	public Compat_Recipes_Aether_Legacy(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Aether Recipes.");
		ST.item(MD.AETHEL, "moa_egg").setMaxStackSize(64);
		
		CR.shaped(ST.make(MD.AETHER, "zanite_ring", 1, 0), DEF | DEL_OTHER_SHAPED_RECIPES, " X ", "X X", " X ", 'X', OP.gem.dat(MT.Zanite));
		
		RM.moss(ST.make(MD.AETHEL, "holystone_stairs", 1, 0), ST.make(MD.AETHEL, "mossy_holystone_stairs", 1, 0));
		RM.moss(ST.make(MD.AETHEL, "holystone_slab"  , 1, 0), ST.make(MD.AETHEL, "mossy_holystone_slab"  , 1, 0));
		RM.moss(ST.make(MD.AETHEL, "holystone_wall"  , 1, 0), ST.make(MD.AETHEL, "mossy_holystone_wall"  , 1, 0));
		RM.moss(ST.make(MD.AETHEL, "holystone"       , 1, 1), ST.make(MD.AETHEL, "mossy_holystone"       , 1, 1));
		RM.moss(ST.make(MD.AETHEL, "holystone"       , 1, 0), ST.make(MD.AETHEL, "mossy_holystone"       , 1, 0));
		
		RM.stoneshapes(MT.STONES.Holystone, F, ST.make(MD.AETHEL, "mossy_holystone", 1, 1), ST.make(MD.AETHEL, "mossy_holystone_stairs", 1, 0), ST.make(MD.AETHEL, "mossy_holystone_slab", 1, 0), ST.make(MD.AETHEL, "mossy_holystone_wall", 1, 0), NI);
		RM.stonetypes(MT.STONES.Holystone, T, OP.rockGt.mat(MT.STONES.Holystone, 4), OP.blockDust.mat(MT.STONES.Holystone, 1)
		, RM.stoneshapes(MT.STONES.Holystone, F, ST.make(MD.AETHEL, "holystone"      , 1, 1), ST.make(MD.AETHEL, "holystone_stairs"      , 1, 0), ST.make(MD.AETHEL, "holystone_slab"      , 1, 0), ST.make(MD.AETHEL, "holystone_wall"      , 1, 0), NI)
		, NI
		, RM.stoneshapes(MT.STONES.Holystone, F, ST.make(MD.AETHEL, "holystone_brick", 1, 0), ST.make(MD.AETHEL, "holystone_brick_stairs", 1, 0), ST.make(MD.AETHEL, "holystone_brick_slab", 1, 0), ST.make(MD.AETHEL, "holystone_brick_wall", 1, 0), NI)
		, NI
		, NI
		, NI
		, RM.stoneshapes(MT.STONES.Holystone, F, ST.make(MD.AETHEL, "angelic_stone" , 1, 0), ST.make(MD.AETHEL, "angelic_stairs" , 1, 0), ST.make(MD.AETHEL, "angelic_slab" , 1, 0), ST.make(MD.AETHEL, "angelic_wall" , 1, 0), NI)
		, RM.stoneshapes(MT.STONES.Holystone, F, ST.make(MD.AETHEL, "hellfire_stone", 1, 0), ST.make(MD.AETHEL, "hellfire_stairs", 1, 0), ST.make(MD.AETHEL, "hellfire_slab", 1, 0), ST.make(MD.AETHEL, "hellfire_wall", 1, 0), NI)
		);
		
		RM.mortarize(ST.make(MD.AETHEL, "icestone", 1, W), OP.dustTiny.mat(MT.Blizz, 8));
		RM.smash    (ST.make(MD.AETHEL, "icestone", 1, W), OP.dustTiny.mat(MT.Blizz, 6));
		
		RM.sawing(16,  32, F, 100, ST.make(MD.AETHEL, "skyroot_fence"     , 1, W), IL.AETHER_Skyroot_Planks.get(1));
		RM.sawing(16,  32, F, 100, ST.make(MD.AETHEL, "skyroot_fence_gate", 1, W), IL.AETHER_Skyroot_Planks.get(2), OM.dust(MT.Skyroot, OP.stick.mAmount * 4));
		RM.sawing(16,  48, F, 100, ST.make(MD.AETHEL, "skyroot_bed_item"  , 1, W), IL.AETHER_Skyroot_Planks.get(3), ST.make(Blocks.wool, 3, 0));
		RM.sawing(16,  96, F, 100, ST.make(MD.AETHEL, "skyroot_bookshelf" , 1, W), IL.AETHER_Skyroot_Planks.get(6), ST.make(Items.book, 3, 0));
		
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(1), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.AETHEL, "skyroot_fence"     )});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(2), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.AETHEL, "skyroot_fence_gate")});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(3), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.AETHEL, "skyroot_bed_item"  )});
		CR.shapeless(IL.AETHER_Skyroot_Planks.get(6), CR.DEF_NCC, new Object[] {OreDictToolNames.saw, ST.item(MD.AETHEL, "skyroot_bookshelf" )});
		
		RM.unbox(IL.AETHER_Skyroot_Planks.get(3), ST.make(MD.AETHEL, "skyrootBookshelf", 1, W), ST.make(Items.book, 3, 0));
		
		// TODO: Magical Infuser
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 4), OP.dustTiny    .mat(MT.Gravitite, 9), ST.make(MD.AETHEL, "enchanted_gravitite", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 4), OP.dustSmall   .mat(MT.Gravitite, 4), ST.make(MD.AETHEL, "enchanted_gravitite", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 4), OP.dust        .mat(MT.Gravitite, 1), ST.make(MD.AETHEL, "enchanted_gravitite", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 4), OP.gemChipped  .mat(MT.Gravitite, 4), ST.make(MD.AETHEL, "enchanted_gravitite", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 4), OP.gemFlawed   .mat(MT.Gravitite, 2), ST.make(MD.AETHEL, "enchanted_gravitite", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 3), OP.gem         .mat(MT.Gravitite, 1), ST.make(MD.AETHEL, "enchanted_gravitite", 1, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 5), OP.gemFlawless .mat(MT.Gravitite, 1), ST.make(MD.AETHEL, "enchanted_gravitite", 2, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium, 9), OP.gemExquisite.mat(MT.Gravitite, 1), ST.make(MD.AETHEL, "enchanted_gravitite", 4, 0));
		RM.Injector.addRecipe2(T, 16, 16, OP.gem.mat(MT.Ambrosium,16), OP.gemLegendary.mat(MT.Gravitite, 1), ST.make(MD.AETHEL, "enchanted_gravitite", 8, 0));
		
		RM.biomass(IL.AETHER_Flower_Purple.wild(16));
		RM.biomass(IL.AETHER_Flower_White .wild(16));
		RM.Squeezer.addRecipe1(T, 16, 16, IL.AETHER_Flower_Purple.wild(1), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], 2), ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Squeezer.addRecipe1(T, 16, 16, IL.AETHER_Flower_White .wild(1), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_White ], 2), OM.dust(MT.White));
		RM.Juicer  .addRecipe1(T, 16, 16, IL.AETHER_Flower_Purple.wild(1), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_Purple], 2), ST.make(Items.dye, 1, DYE_INDEX_Purple));
		RM.Juicer  .addRecipe1(T, 16, 16, IL.AETHER_Flower_White .wild(1), NF, FL.mul(DYE_FLUIDS_FLOWER[DYE_INDEX_White ], 2), OM.dust(MT.White));
		RM.ic2_extractor(IL.AETHER_Flower_Purple.wild(1), ST.make(Items.dye, 3, DYE_INDEX_Purple));
		RM.ic2_extractor(IL.AETHER_Flower_White .wild(1), OM.dust(MT.White, U*3));
	}
}
