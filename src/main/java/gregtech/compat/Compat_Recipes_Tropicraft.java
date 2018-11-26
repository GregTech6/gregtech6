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
import static gregapi.data.OP.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.RM;
import gregapi.oredict.IOreDictListenerEvent;
import gregapi.oredict.OreDictListenerEvent_Names;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Items;

public class Compat_Recipes_Tropicraft extends CompatMods {
	public Compat_Recipes_Tropicraft(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Tropicraft Recipes.");
		CR.delate(MD.TROPIC, "pineappleCubes", "tile.blockOre", "tile.singleSlabs", "tile.plank", "ore");
		RM.biomass(ST.make(MD.TROPIC, "tile.flower", 16, W), 64);
		
		RM.Sifting          .addRecipe1(T, 16, 200, new long[] {9900, 500, 500}     , IL.TROPIC_Sand_Black.get(1), dust.mat(MT.OREMATS.Magnetite, 1), rockGt.mat(MT.Stone, 1), nugget.mat(MT.Au, 1));
		RM.MagneticSeparator.addRecipe1(T, 16, 144, new long[] {9900, 500, 500, 500}, IL.TROPIC_Sand_Black.get(1), dust.mat(MT.OREMATS.Magnetite, 1), rockGt.mat(MT.Stone, 1), nugget.mat(MT.Au, 1), dustTiny.mat(MT.Au, 2));
		RM.Centrifuge       .addRecipe1(T, 16, 256, new long[] {9000, 1000}         , IL.TROPIC_Sand_Black.get(1), dust.mat(MT.OREMATS.Magnetite, 1), dust.mat(MT.V2O5, 1));
		
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
