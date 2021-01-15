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
import static gregapi.data.OP.*;
import static gregapi.util.CR.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.event.IOreDictListenerEvent;
import gregapi.oredict.event.OreDictListenerEvent_Names;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class Compat_Recipes_Tropicraft extends CompatMods {
	public Compat_Recipes_Tropicraft(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Tropicraft Recipes.");
		Item tChair = ST.item(MD.TROPIC, "chair"), tUmbrella = ST.item(MD.TROPIC, "umbrella"), tTorch = ST.item(MD.TROPIC, "tikiTorch");
		CR.delate(MD.TROPIC, "pineappleCubes", "tile.blockOre", "tile.singleSlabs", "tile.plank", "tile.sifter", "ore", "tikiTorch", "chair", "umbrella", "waterWand", "blowGun", "portalEnchanter", "coconutBomb");
		RM.biomass(ST.make(MD.TROPIC, "tile.flower", 16, W));
		
		RM.Sifting          .addRecipe1(T, 16, 200, new long[] {9900, 500, 500}     , IL.TROPIC_Sand_Black.get(1), dust.mat(MT.OREMATS.BasalticMineralSand, 1), rockGt.mat(MT.Basalt, 1), nugget.mat(MT.Au, 1));
		RM.MagneticSeparator.addRecipe1(T, 16, 144, new long[] {9900, 500, 500, 500}, IL.TROPIC_Sand_Black.get(1), dust.mat(MT.OREMATS.BasalticMineralSand, 1), rockGt.mat(MT.Basalt, 1), nugget.mat(MT.Au, 1), dustTiny.mat(MT.Au, 2));
		RM.Centrifuge       .addRecipe1(T, 16, 256, new long[] {9000, 1000}         , IL.TROPIC_Sand_Black.get(1), dust.mat(MT.OREMATS.BasalticMineralSand, 1), dust.mat(MT.V2O5, 1));
		
		RM.Sifting          .addRecipe1(T, 16, 200, new long[] {9900, 500, 500}     , IL.TROPIC_Sand_Mineral.get(1), dust.mat(MT.OREMATS.Cassiterite, 1), rockGt.mat(MT.Basalt, 1), nugget.mat(MT.Sn, 1));
		RM.MagneticSeparator.addRecipe1(T, 16, 144, new long[] {9900, 500, 500, 500}, IL.TROPIC_Sand_Mineral.get(1), dust.mat(MT.OREMATS.Cassiterite, 1), rockGt.mat(MT.Basalt, 1), nugget.mat(MT.Ni, 1), dustTiny.mat(MT.Ni, 2));
		RM.Centrifuge       .addRecipe1(T, 16, 256, new long[] {9000, 1000}         , IL.TROPIC_Sand_Mineral.get(1), dust.mat(MT.OREMATS.Cassiterite, 1), dust.mat(MT.Sn, 1));
		
		// Solonox Shell, Frox Conch, Pab Shell, Rube Nautilus, Starfish, Turtle Shell
		RM.Sifting          .addRecipe1(T, 16, 200, new long[] {9900, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 200, 100, 50}, IL.TROPIC_Sand_Foamy    .get(1), IL.TROPIC_Sand_Pure.get(1), ST.make(MD.TROPIC, "shell", 1, 0), ST.make(MD.TROPIC, "shell", 1, 1), ST.make(MD.TROPIC, "shell", 1, 2), ST.make(MD.TROPIC, "shell", 1, 3), ST.make(MD.TROPIC, "shell", 1, 4), ST.make(MD.TROPIC, "shell", 1, 5), ST.make(MD.TROPIC, "pearl", 1, 0), ST.make(MD.TROPIC, "pearl", 1, 1), OP.gem.mat(MT.Azurite, 1), OP.gem.mat(MT.Eudialyte, 1), OP.gem.mat(MT.Zr, 1));
		RM.Sifting          .addRecipe1(T, 16, 200, new long[] {9900, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 200, 100, 50}, IL.TROPIC_Sand_Coral    .get(1), IL.TROPIC_Sand_Pure.get(1), ST.make(MD.TROPIC, "shell", 1, 0), ST.make(MD.TROPIC, "shell", 1, 1), ST.make(MD.TROPIC, "shell", 1, 2), ST.make(MD.TROPIC, "shell", 1, 3), ST.make(MD.TROPIC, "shell", 1, 4), ST.make(MD.TROPIC, "shell", 1, 5), ST.make(MD.TROPIC, "pearl", 1, 0), ST.make(MD.TROPIC, "pearl", 1, 1), OP.gem.mat(MT.Azurite, 1), OP.gem.mat(MT.Eudialyte, 1), OP.gem.mat(MT.Zr, 1));
		RM.Sifting          .addRecipe1(T, 16, 200, new long[] {9900, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 200, 100, 50}, ST.make(MD.TROPIC, "ore", 1, 5), IL.TROPIC_Sand_Pure.get(1), ST.make(MD.TROPIC, "shell", 1, 0), ST.make(MD.TROPIC, "shell", 1, 1), ST.make(MD.TROPIC, "shell", 1, 2), ST.make(MD.TROPIC, "shell", 1, 3), ST.make(MD.TROPIC, "shell", 1, 4), ST.make(MD.TROPIC, "shell", 1, 5), ST.make(MD.TROPIC, "pearl", 1, 0), ST.make(MD.TROPIC, "pearl", 1, 1), OP.gem.mat(MT.Azurite, 1), OP.gem.mat(MT.Eudialyte, 1), OP.gem.mat(MT.Zr, 1));
		
		RM.unpack(ST.make(MD.TROPIC, "shell", 1, 0), ST.make(MD.TROPIC, "pearl", 1, 0));
		RM.unpack(ST.make(MD.TROPIC, "shell", 1, 1), ST.make(MD.TROPIC, "pearl", 1, 1));
		
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.TROPIC, "tile.flower", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], ST.make(Items.dye, 2, DYE_INDEX_LightBlue));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.TROPIC, "tile.flower", 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow   ], ST.make(Items.dye, 2, DYE_INDEX_Yellow   ));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.TROPIC, "tile.flower", 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange   ], ST.make(Items.dye, 2, DYE_INDEX_Orange   ));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.TROPIC, "tile.flower", 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red      ], ST.make(Items.dye, 2, DYE_INDEX_Red      ));
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.TROPIC, "tile.flower", 1,12), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Green    ], ST.make(Items.dye, 2, DYE_INDEX_Green    ));
		
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.TROPIC, "tile.flower", 1, 0), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_LightBlue], ST.make(Items.dye, 2, DYE_INDEX_LightBlue));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.TROPIC, "tile.flower", 1, 3), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Yellow   ], ST.make(Items.dye, 2, DYE_INDEX_Yellow   ));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.TROPIC, "tile.flower", 1, 5), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Orange   ], ST.make(Items.dye, 2, DYE_INDEX_Orange   ));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.TROPIC, "tile.flower", 1, 6), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Red      ], ST.make(Items.dye, 2, DYE_INDEX_Red      ));
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.TROPIC, "tile.flower", 1,12), NF, DYE_FLUIDS_FLOWER[DYE_INDEX_Green    ], ST.make(Items.dye, 2, DYE_INDEX_Green    ));
		
		if (ENABLE_ADDING_IC2_EXTRACTOR_RECIPES) {
		RM.ic2_extractor(ST.make(MD.TROPIC, "tile.flower", 1, 0), ST.make(Items.dye, 3, DYE_INDEX_LightBlue));
		RM.ic2_extractor(ST.make(MD.TROPIC, "tile.flower", 1, 3), ST.make(Items.dye, 3, DYE_INDEX_Yellow   ));
		RM.ic2_extractor(ST.make(MD.TROPIC, "tile.flower", 1, 5), ST.make(Items.dye, 3, DYE_INDEX_Orange   ));
		RM.ic2_extractor(ST.make(MD.TROPIC, "tile.flower", 1, 6), ST.make(Items.dye, 3, DYE_INDEX_Red      ));
		RM.ic2_extractor(ST.make(MD.TROPIC, "tile.flower", 1,12), ST.make(Items.dye, 3, DYE_INDEX_Green    ));
		}
		
		CR.shaped(ST.make(tTorch, 2, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.gem.dat(MT.Charcoal)                      , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 2, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.dust.dat(MT.Charcoal)                     , 'S', OP.stick.dat(MT.Bamboo));
		
		CR.shaped(ST.make(tTorch, 1, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.rockGt.dat(MT.Coal)                       , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 2, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.gem.dat(MT.Coal)                          , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 2, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.dust.dat(MT.Coal)                         , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 2, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.crushed.dat(MT.Coal)                      , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 2, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.crushedPurified.dat(MT.Coal)              , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 2, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.crushedCentrifuged.dat(MT.Coal)           , 'S', OP.stick.dat(MT.Bamboo));
		
		CR.shaped(ST.make(tTorch, 2, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.rockGt.dat(MT.CoalCoke)                   , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 4, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.gem.dat(MT.CoalCoke)                      , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 4, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.dust.dat(MT.CoalCoke)                     , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 4, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.crushed.dat(MT.CoalCoke)                  , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 4, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.crushedPurified.dat(MT.CoalCoke)          , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 4, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.crushedCentrifuged.dat(MT.CoalCoke)       , 'S', OP.stick.dat(MT.Bamboo));
		
		CR.shaped(ST.make(tTorch, 1, 0), DEF_NAC, " X ", " SX", "S  ", 'X', OP.rockGt.dat(MT.Lignite)                    , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 1, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.gem.dat(MT.Lignite)                       , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 1, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.dust.dat(MT.Lignite)                      , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 1, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.crushed.dat(MT.Lignite)                   , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 1, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.crushedPurified.dat(MT.Lignite)           , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 1, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.crushedCentrifuged.dat(MT.Lignite)        , 'S', OP.stick.dat(MT.Bamboo));
		
		CR.shaped(ST.make(tTorch, 1, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.rockGt.dat(MT.LigniteCoke)                , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 2, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.gem.dat(MT.LigniteCoke)                   , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 2, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.dust.dat(MT.LigniteCoke)                  , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 2, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.crushed.dat(MT.LigniteCoke)               , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 2, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.crushedPurified.dat(MT.LigniteCoke)       , 'S', OP.stick.dat(MT.Bamboo));
		CR.shaped(ST.make(tTorch, 2, 0), DEF_NAC, "  X", " S ", "S  ", 'X', OP.crushedCentrifuged.dat(MT.LigniteCoke)    , 'S', OP.stick.dat(MT.Bamboo));
		
		for (int i = 0; i < 16; i++) {
		CR.shaped(ST.make(tChair   , 1, 15-i), CR.DEF_NCC_MIR, "BWB", "BWB", "BWB", 'B', OP.stick.dat(MT.Bamboo), 'W', ST.make(Blocks.wool, 1, i));
		CR.shaped(ST.make(tUmbrella, 1, 15-i), CR.DEF_NCC_MIR, "WWW", " B ", " B ", 'B', OP.stick.dat(MT.Bamboo), 'W', ST.make(Blocks.wool, 1, i));
		}
		
		CR.shaped(ST.make(MD.TROPIC, "portalEnchanter", 1, 0), CR.DEF_NCC_MIR, "ABZ", "ZBA", " B ", 'B', OP.stick.dat(MT.Bamboo), 'A', OP.gem.dat(MT.Azurite), 'Z', OP.gem.dat(MT.Zr));
		CR.shaped(ST.make(MD.TROPIC, "blowGun"        , 1, 0), CR.DEF_NCC_MIR, "B  ", " C ", "  B", 'B', OP.stick.dat(MT.Bamboo), 'C', ST.make(MD.TROPIC, "curare", 1, 0));
		CR.shaped(ST.make(MD.TROPIC, "waterWand"      , 1, 0), CR.DEF_NCC_MIR, "  A", " S ", "S  ", 'S', OP.stickLong.dat(MT.Au), 'A', OP.gem.dat(MT.Azurite));
		CR.shaped(ST.make(MD.TROPIC, "coconutBomb"    , 1, 0), CR.DEF_NCC_MIR, " G ", "GCG", " G ", 'G', OP.dust.dat(MT.Gunpowder), 'C', "cropCoconut");
		
		CR.shaped(ST.make(MD.TROPIC, "coconutChunk"   , 3, 0), CR.DEF_NAC_NCC, "v", "C", 'C', "cropCoconut");
		CR.shaped(ST.make(MD.TROPIC, "pineappleCubes" , 3, 0), CR.DEF_NAC_NCC, "v", "A", 'A', "cropAnanas");
		
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
