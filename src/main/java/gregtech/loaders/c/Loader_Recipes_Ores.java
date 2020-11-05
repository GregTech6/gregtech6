/**
 * Copyright (c) 2020 GregTech-6 Team
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

package gregtech.loaders.c;

import static gregapi.data.CS.*;
import static gregapi.data.OP.*;
import static gregapi.data.TD.Atomic.*;
import static gregapi.data.TD.Processing.*;
import static gregapi.data.TD.Properties.*;

import gregapi.code.ArrayListNoNulls;
import gregapi.data.CS.BlocksGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MT;
import gregapi.data.MT.OREMATS;
import gregapi.data.OP;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Loader_Recipes_Ores implements Runnable {
	@Override public void run() {
		RM.Centrifuge       .addRecipe1(T, 64,  144, OM.dust(MT.DarkAsh, U*2), OM.dust(MT.Ash), IL.TE_Slag.get(1, IL.IE_Slag.get(1, IL.FZ_Sludge.get(1, OM.dust(MT.Ash)))));
		
		for (FluidStack tWater : FL.array(FL.Water.make(1000), FL.SpDew.make(1000), FL.DistW.make(1000))) {
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(MT.SluiceSand, U9), FL.mul(tWater, 1,10, T), FL.Sluice.make(100), ZL_IS);
		RM.Mixer            .addRecipe1(T, 16,   16, OM.dust(MT.SluiceSand    ), FL.mul(tWater, 9,10, T), FL.Sluice.make(900), ZL_IS);
		}
		RM.Drying           .addRecipe0(T, 16,  100, FL.Sluice.make(100), FL.DistW.make(50), OM.dust(MT.SluiceSand,  U9));
		RM.Centrifuge       .addRecipe0(T, 64,   16, new long[] { 9640, 100, 100, 100, 100, 100}, FL.Sluice.make(100), FL.Water.make(50), dustTiny .mat(MT.Stone, 1), dustTiny.mat(MT.Fe, 1), dustTiny.mat(MT.Cu, 1), dustTiny.mat(MT.Sn, 1), dustTiny.mat(MT.Zn, 1), dustTiny.mat(MT.Cr, 1));
		RM.MagneticSeparator.addRecipe1(T, 16,   16, new long[] { 9640,  72,  72,  72,  72,  72}, dustTiny  .mat(MT.SluiceSand, 1)      , dustTiny .mat(MT.Stone, 1), dustTiny.mat(MT.Fe, 2), dustTiny.mat(MT.Nd, 1), dustTiny.mat(MT.Ni, 1), dustTiny.mat(MT.Co, 1), dustTiny.mat(MT.Mn, 1));
		RM.MagneticSeparator.addRecipe1(T, 16,   36, new long[] { 9640, 162, 162, 162, 162, 162}, dustSmall .mat(MT.SluiceSand, 1)      , dustSmall.mat(MT.Stone, 1), dustTiny.mat(MT.Fe, 2), dustTiny.mat(MT.Nd, 1), dustTiny.mat(MT.Ni, 1), dustTiny.mat(MT.Co, 1), dustTiny.mat(MT.Mn, 1));
		RM.MagneticSeparator.addRecipe1(T, 16,  144, new long[] { 9640, 648, 648, 648, 648, 648}, dust      .mat(MT.SluiceSand, 1)      , dust     .mat(MT.Stone, 1), dustTiny.mat(MT.Fe, 2), dustTiny.mat(MT.Nd, 1), dustTiny.mat(MT.Ni, 1), dustTiny.mat(MT.Co, 1), dustTiny.mat(MT.Mn, 1));
		RM.MagneticSeparator.addRecipe1(T, 16, 1296, new long[] { 9640,5832,5832,5832,5832,5832}, blockDust .mat(MT.SluiceSand, 1)      , dust     .mat(MT.Stone, 9), dustTiny.mat(MT.Fe, 2), dustTiny.mat(MT.Nd, 1), dustTiny.mat(MT.Ni, 1), dustTiny.mat(MT.Co, 1), dustTiny.mat(MT.Mn, 1));
		
		RM.MagneticSeparator.addRecipe1(T, 64,  144, new long[] { 7000,3000,3000,3000,3000,3000}, dustImpure.mat(MT.Bedrock, 1)         , dust     .mat(MT.Bedrock, 1), dustTiny.mat(MT.Adamantine, 1), dustTiny.mat(MT.Atl, 1), dustTiny.mat(MT.RareEarth, 2), dustTiny.mat(MT.Nd, 3), dustTiny.mat(MT.V2O5, 3));
		
		RM.MagneticSeparator.addRecipe1(T, 16,   16, new long[] { 3000,  12,  12,  12,  12,  12}, dustTiny  .mat(MT.MoonTurf, 1)        , dustTiny .mat(MT.Basalt, 1), dustTiny.mat(MT.MeteoricIron, 9), dustTiny.mat(MT.Nd, 1), dustTiny.mat(MT.Ni, 1), dustTiny.mat(MT.Co, 1), dustTiny.mat(MT.Mn, 1));
		RM.MagneticSeparator.addRecipe1(T, 16,   36, new long[] { 3000,  27,  27,  27,  27,  27}, dustSmall .mat(MT.MoonTurf, 1)        , dustSmall.mat(MT.Basalt, 1), dustTiny.mat(MT.MeteoricIron, 9), dustTiny.mat(MT.Nd, 1), dustTiny.mat(MT.Ni, 1), dustTiny.mat(MT.Co, 1), dustTiny.mat(MT.Mn, 1));
		RM.MagneticSeparator.addRecipe1(T, 16,  144, new long[] { 3000, 108, 108, 108, 108, 108}, dust      .mat(MT.MoonTurf, 1)        , dust     .mat(MT.Basalt, 1), dustTiny.mat(MT.MeteoricIron, 9), dustTiny.mat(MT.Nd, 1), dustTiny.mat(MT.Ni, 1), dustTiny.mat(MT.Co, 1), dustTiny.mat(MT.Mn, 1));
		RM.MagneticSeparator.addRecipe1(T, 16, 1296, new long[] { 3000, 972, 972, 972, 972, 972}, blockDust .mat(MT.MoonTurf, 1)        , dust     .mat(MT.Basalt, 9), dustTiny.mat(MT.MeteoricIron, 9), dustTiny.mat(MT.Nd, 1), dustTiny.mat(MT.Ni, 1), dustTiny.mat(MT.Co, 1), dustTiny.mat(MT.Mn, 1));
		// TODO MARS SAND, SPACE DUST
		
		RM.Centrifuge       .addRecipe1(T, 64,   16, new long[] {   72,  72,  72,  72,  72,  72}, dustTiny              .mat(MT.RareEarth, 1), dustTiny.mat(MT.Nd, 8), dustTiny.mat(MT.Y, 8), dustTiny.mat(MT.La, 8), dustTiny.mat(MT.Ce, 8), dustTiny.mat(MT.Cd, 8), dustTiny.mat(MT.Cs, 8));
		RM.Centrifuge       .addRecipe1(T, 64,   36, new long[] {  162, 162, 162, 162, 162, 162}, dustSmall             .mat(MT.RareEarth, 1), dustTiny.mat(MT.Nd, 8), dustTiny.mat(MT.Y, 8), dustTiny.mat(MT.La, 8), dustTiny.mat(MT.Ce, 8), dustTiny.mat(MT.Cd, 8), dustTiny.mat(MT.Cs, 8));
		RM.Centrifuge       .addRecipe1(T, 64,  144, new long[] {  648, 648, 648, 648, 648, 648}, dust                  .mat(MT.RareEarth, 1), dustTiny.mat(MT.Nd, 8), dustTiny.mat(MT.Y, 8), dustTiny.mat(MT.La, 8), dustTiny.mat(MT.Ce, 8), dustTiny.mat(MT.Cd, 8), dustTiny.mat(MT.Cs, 8));
		RM.Centrifuge       .addRecipe1(T, 64,   16, new long[] {   80,  80,  80,  80,  80,  80}, crushedPurifiedTiny   .mat(MT.RareEarth, 1), dustTiny.mat(MT.Nd, 8), dustTiny.mat(MT.Y, 8), dustTiny.mat(MT.La, 8), dustTiny.mat(MT.Ce, 8), dustTiny.mat(MT.Cd, 8), dustTiny.mat(MT.Cs, 8));
		RM.Centrifuge       .addRecipe1(T, 64,  144, new long[] {  720, 720, 720, 720, 720, 720}, crushedPurified       .mat(MT.RareEarth, 1), dustTiny.mat(MT.Nd, 8), dustTiny.mat(MT.Y, 8), dustTiny.mat(MT.La, 8), dustTiny.mat(MT.Ce, 8), dustTiny.mat(MT.Cd, 8), dustTiny.mat(MT.Cs, 8));
		RM.Centrifuge       .addRecipe1(T, 64,   16, new long[] {   80,  80,  80,  80,  80,  80}, crushedCentrifugedTiny.mat(MT.RareEarth, 1), dustTiny.mat(MT.Nd, 8), dustTiny.mat(MT.Y, 8), dustTiny.mat(MT.La, 8), dustTiny.mat(MT.Ce, 8), dustTiny.mat(MT.Cd, 8), dustTiny.mat(MT.Cs, 8));
		RM.Centrifuge       .addRecipe1(T, 64,  144, new long[] {  720, 720, 720, 720, 720, 720}, crushedCentrifuged    .mat(MT.RareEarth, 1), dustTiny.mat(MT.Nd, 8), dustTiny.mat(MT.Y, 8), dustTiny.mat(MT.La, 8), dustTiny.mat(MT.Ce, 8), dustTiny.mat(MT.Cd, 8), dustTiny.mat(MT.Cs, 8));
		
		RM.Centrifuge       .addRecipe1(T, 64,   16, new long[] {   72,  72,  72,  72,  72,  72}, dustTiny              .mat(MT.PlatinumGroupSludge, 1), dustTiny.mat(MT.Ru, 8), dustTiny.mat(MT.Rh, 8), dustTiny.mat(MT.Pd, 8), dustTiny.mat(MT.Os, 8), dustTiny.mat(MT.Ir, 8), dustTiny.mat(MT.Pt, 8));
		RM.Centrifuge       .addRecipe1(T, 64,   36, new long[] {  162, 162, 162, 162, 162, 162}, dustSmall             .mat(MT.PlatinumGroupSludge, 1), dustTiny.mat(MT.Ru, 8), dustTiny.mat(MT.Rh, 8), dustTiny.mat(MT.Pd, 8), dustTiny.mat(MT.Os, 8), dustTiny.mat(MT.Ir, 8), dustTiny.mat(MT.Pt, 8));
		RM.Centrifuge       .addRecipe1(T, 64,  144, new long[] {  648, 648, 648, 648, 648, 648}, dust                  .mat(MT.PlatinumGroupSludge, 1), dustTiny.mat(MT.Ru, 8), dustTiny.mat(MT.Rh, 8), dustTiny.mat(MT.Pd, 8), dustTiny.mat(MT.Os, 8), dustTiny.mat(MT.Ir, 8), dustTiny.mat(MT.Pt, 8));
		RM.Centrifuge       .addRecipe1(T, 64,   16, new long[] {   80,  80,  80,  80,  80,  80}, crushedPurifiedTiny   .mat(MT.PlatinumGroupSludge, 1), dustTiny.mat(MT.Ru, 8), dustTiny.mat(MT.Rh, 8), dustTiny.mat(MT.Pd, 8), dustTiny.mat(MT.Os, 8), dustTiny.mat(MT.Ir, 8), dustTiny.mat(MT.Pt, 8));
		RM.Centrifuge       .addRecipe1(T, 64,  144, new long[] {  720, 720, 720, 720, 720, 720}, crushedPurified       .mat(MT.PlatinumGroupSludge, 1), dustTiny.mat(MT.Ru, 8), dustTiny.mat(MT.Rh, 8), dustTiny.mat(MT.Pd, 8), dustTiny.mat(MT.Os, 8), dustTiny.mat(MT.Ir, 8), dustTiny.mat(MT.Pt, 8));
		RM.Centrifuge       .addRecipe1(T, 64,   16, new long[] {   80,  80,  80,  80,  80,  80}, crushedCentrifugedTiny.mat(MT.PlatinumGroupSludge, 1), dustTiny.mat(MT.Ru, 8), dustTiny.mat(MT.Rh, 8), dustTiny.mat(MT.Pd, 8), dustTiny.mat(MT.Os, 8), dustTiny.mat(MT.Ir, 8), dustTiny.mat(MT.Pt, 8));
		RM.Centrifuge       .addRecipe1(T, 64,  144, new long[] {  720, 720, 720, 720, 720, 720}, crushedCentrifuged    .mat(MT.PlatinumGroupSludge, 1), dustTiny.mat(MT.Ru, 8), dustTiny.mat(MT.Rh, 8), dustTiny.mat(MT.Pd, 8), dustTiny.mat(MT.Os, 8), dustTiny.mat(MT.Ir, 8), dustTiny.mat(MT.Pt, 8));
		
		RM.Centrifuge       .addRecipe0(T, 64,  128, new long[] {8000,  250,  250,  250,  250,  250}, FL.Lava           .make(100), NF, dustTiny.mat(MT.Basalt, 1), nugget.mat(MT.Cu, 8), nugget.mat(MT.Sn, 4), nugget.mat(MT.Fe, 4), nugget.mat(MT.Au, 1), nugget.mat(MT.Ag, 1));
		RM.Centrifuge       .addRecipe0(T, 64,  192, new long[] {9000,  300,  300,  300,  300,  300}, FL.Lava_Pahoehoe  .make(100), NF, dustTiny.mat(MT.Basalt, 1), nugget.mat(MT.Cu, 8), nugget.mat(MT.Sn, 4), nugget.mat(MT.Fe, 4), nugget.mat(MT.Au, 1), nugget.mat(MT.Ag, 1));
		RM.Centrifuge       .addRecipe1(T, 64,   16, new long[] {9000,10000, 1000                  }, ST.make(Blocks.sand, 1, 1), ST.make(Blocks.sand, 1, 0), dustTiny.mat(MT.Fe, 1), dustDiv72.mat(MT.Diamond, 1));
		if (IL.NePl_SoulSoil.exists())
		RM.Centrifuge       .addRecipe1(T, 64,  128, new long[] {9000, 8000, 2000, 2000            }, IL.NePl_SoulSoil        .get(1), NF, FL.Oil_Soulsand.make(50), ST.make(Blocks.sand, 1, 0), dustSmall.mat(MT.Niter, 1), dustTiny.mat(MT.Coal, 1), dustTiny.mat(MT.Bone, 1));
		if (IL.NeLi_SoulSoil.exists())
		RM.Centrifuge       .addRecipe1(T, 64,  128, new long[] {9000, 8000, 2000, 2000            }, IL.NeLi_SoulSoil        .get(1), NF, FL.Oil_Soulsand.make(50), ST.make(Blocks.sand, 1, 0), dustSmall.mat(MT.Niter, 1), dustTiny.mat(MT.Coal, 1), dustTiny.mat(MT.Bone, 1));
		RM.Centrifuge       .addRecipe1(T, 64,  128, new long[] {9000, 8000, 2000, 2000            }, ST.make(Blocks.soul_sand, 1, W), NF, FL.Oil_Soulsand.make(50), ST.make(Blocks.sand, 1, 0), dustSmall.mat(MT.Niter, 1), dustTiny.mat(MT.Coal, 1), dustTiny.mat(MT.Bone, 1));
		RM.Centrifuge       .addRecipe1(T, 64,  128, new long[] {9000, 8000, 2000, 2000            }, OM.dust(MT.SoulSand           ), NF, FL.Oil_Soulsand.make(50), ST.make(Blocks.sand, 1, 0), dustSmall.mat(MT.Niter, 1), dustTiny.mat(MT.Coal, 1), dustTiny.mat(MT.Bone, 1));
		RM.Centrifuge       .addRecipe1(T, 64,   64, new long[] {5625, 9900, 5625,  625            }, OM.dust(MT.Netherrack         ), dustTiny.mat(MT.Redstone, 1), dustSmall.mat(MT.S, 1), dustTiny.mat(MT.Coal, 1), dustTiny.mat(MT.Au, 1));
		RM.Centrifuge       .addRecipe1(T, 64,  128, new long[] {9000,  625, 2500                  }, OM.dust(MT.Endstone           ), ZL_FS, FL.array(MT.He.gas(U8, F), MT.He_3.gas(U20, F), MT.NO2.gas(U4, F), MT.CO2.gas(U4, F), MT.H.gas(U8, F), MT.D.gas(U8, F)), ST.make(Blocks.sand, 1, 0), dustTiny.mat(MT.Pt, 1), dustTiny.mat(MT.WO3, 1));
		RM.Centrifuge       .addRecipe1(T, 64,  128, new long[] {9000,  625,  625                  }, OM.dust(MT.SpaceRock          ), ZL_FS, FL.array(MT.He.gas(U8, F), MT.He_3.gas(U20, F), MT.NO2.gas(U4, F), MT.CO2.gas(U4, F), MT.H.gas(U8, F), MT.D.gas(U8, F)), ST.make(Blocks.sand, 1, 0), dustTiny.mat(MT.Pt, 1), dustTiny.mat(MT.MeteoricIron, 1));
		RM.Centrifuge       .addRecipe1(T, 64,  256, new long[] {9000, 9000, 9000, 9000, 9000, 1000}, OM.dust(MT.MoonTurf           ), ZL_FS, FL.array(MT.He.gas(U8, F), MT.He_3.gas(U20, F), MT.NO2.gas(U4, F), MT.CO2.gas(U4, F), MT.H.gas(U8, F), MT.D.gas(U8, F)), dustTiny.mat(MT.SiO2, 2), dustTiny.mat(MT.Basalt, 2), dustTiny.mat(MT.Al2O3, 1), dustTiny.mat(MT.OREMATS.Ilmenite, 1), dustTiny.mat(MT.MnO2, 1), dustTiny.mat(MT.MeteoricIron, 1));
		RM.Centrifuge       .addRecipe1(T, 64,  128, new long[] {9000, 5000, 4000, 3000, 1000,  500}, OM.dust(MT.MoonRock           ), NF, NF, dustSmall.mat(MT.Basalt, 3), dustTiny.mat(MT.SiO2, 1), dustTiny.mat(MT.Al2O3, 1), dustTiny.mat(OREMATS.Ilmenite, 1), dustTiny.mat(MT.RareEarth, 1), dustTiny.mat(MT.Pt, 1));
		// TODO MARS ROCKS AND SAND => Ice
		
		for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Mg, MT.Pyrope, MT.Olivine, MT.OREMATS.Magnesite}) {
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(tMat, 1), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.PinkVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(tMat, 9), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.PinkVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		}
		for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Ni, MT.Pt, MT.OREMATS.Cooperite, MT.OREMATS.Garnierite, MT.OREMATS.Pentlandite}) {
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(tMat, 1), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.CyanVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(tMat, 9), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.CyanVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		}
		for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Mn, MT.W, MT.Spessartine, MT.MnO2, MT.OREMATS.Huebnerite, MT.OREMATS.Tungstate, MT.OREMATS.Tantalite, MT.OREMATS.Scheelite}) {
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(tMat, 1), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.GrayVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(tMat, 9), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.GrayVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		}
		for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Fe, MT.Cr, MT.Ad, MT.Pyrite, MT.Andradite, MT.PigIron, MT.Steel, MT.DarkIron, MT.DeepIron, MT.ShadowIron, MT.MeteoricIron, MT.OREMATS.Ilmenite, MT.OREMATS.Wolframite, MT.OREMATS.Chromite, MT.OREMATS.Magnetite}) {
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(tMat, 1), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.GreenVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(tMat, 9), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.GreenVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		}
		for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Cu, MT.Au, MT.Nikolite, MT.Teslatite, MT.Electrotine, MT.Azurite, MT.OREMATS.Malachite, MT.OREMATS.Chalcopyrite, MT.OREMATS.Tetrahedrite}) {
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(tMat, 1), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.BlueVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(tMat, 9), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.BlueVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		}
		for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Co, MT.Co_60, MT.OREMATS.Cobaltite}) {
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(tMat, 1), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.RedVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(tMat, 9), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.RedVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		}
		for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Zn, MT.Sn, MT.Sb, MT.OREMATS.Sphalerite, MT.OREMATS.Smithsonite}) {
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(tMat, 1), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.WhiteVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(tMat, 9), FL.array(MT.H2SO4.fluid(7* U2, T)), FL.array(MT.WhiteVitriol.fluid(3*U, F), MT.H.gas(U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		}
		for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Al, MT.Almandine, MT.OREMATS.Bauxite}) {
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(tMat, 1), FL.array(MT.H2SO4.fluid(21* U2, T)), FL.array(MT.VitriolOfClay.fluid(17* U2, F), MT.H.gas(3*U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(tMat, 9), FL.array(MT.H2SO4.fluid(21* U2, T)), FL.array(MT.VitriolOfClay.fluid(17* U2, F), MT.H.gas(3*U, F)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2), crushedCentrifugedTiny.mat(tMat, 2));
		}
		for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Ru, MT.Rh, MT.Pd, MT.Os, MT.FakeOsmium, MT.Ir, MT.Pt, MT.Ni, MT.Mithril, MT.MeteoricIron, MT.OREMATS.Cooperite, MT.OREMATS.Sperrylite}) {
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(tMat, 1), FL.array(MT.AquaRegia.fluid(39*U4, T)), FL.array(MT.ChloroplatinicAcid.fluid(18*U4, F), MT.NO.gas(6*U4, F), FL.Water.make(4500)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(MT.PlatinumGroupSludge, 8), crushedCentrifugedTiny.mat(MT.PlatinumGroupSludge, 8), crushedCentrifugedTiny.mat(MT.PlatinumGroupSludge, 8), crushedCentrifugedTiny.mat(MT.PlatinumGroupSludge, 8), crushedCentrifugedTiny.mat(MT.PlatinumGroupSludge, 8));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(tMat, 9), FL.array(MT.AquaRegia.fluid(39*U4, T)), FL.array(MT.ChloroplatinicAcid.fluid(18*U4, F), MT.NO.gas(6*U4, F), FL.Water.make(4500)), crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(MT.PlatinumGroupSludge, 8), crushedCentrifugedTiny.mat(MT.PlatinumGroupSludge, 8), crushedCentrifugedTiny.mat(MT.PlatinumGroupSludge, 8), crushedCentrifugedTiny.mat(MT.PlatinumGroupSludge, 8), crushedCentrifugedTiny.mat(MT.PlatinumGroupSludge, 8));
		}
		for (OreDictMaterial tMat : new OreDictMaterial[] {MT.Graphite, MT.Coal, MT.Anthracite, MT.Lignite, MT.Prismane, MT.Lonsdaleite}) {
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(tMat, 1), FL.array(MT.HF.gas(U2, T)), ZL_FS, crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(MT.Graphite, 2), crushedCentrifugedTiny.mat(MT.Graphite, 2), crushedCentrifugedTiny.mat(MT.Graphite, 2), crushedCentrifugedTiny.mat(MT.Graphite, 2), crushedCentrifugedTiny.mat(MT.Graphite, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(tMat, 9), FL.array(MT.HF.gas(U2, T)), ZL_FS, crushedCentrifuged.mat(tMat, 1), crushedCentrifugedTiny.mat(MT.Graphite, 2), crushedCentrifugedTiny.mat(MT.Graphite, 2), crushedCentrifugedTiny.mat(MT.Graphite, 2), crushedCentrifugedTiny.mat(MT.Graphite, 2), crushedCentrifugedTiny.mat(MT.Graphite, 2));
		}
		for (OreDictMaterial tMat : OreDictMaterial.MATERIAL_ARRAY) if (tMat != null && !tMat.contains(ANTIMATTER) && tMat.mByProducts.contains(MT.MnO2)) {
		RM.Bath         .addRecipe1(T,  0,  256, crushedPurified    .mat(tMat, 1), FL.array(MT.HCl.gas(U * 4, T)), FL.array(MT.H2O.fluid(U * 3, F), MT.Cl.gas(U * 1, F)), crushedCentrifuged.mat(tMat, 1), OM.dust(MT.MnCl2, U2*3));
		RM.Bath         .addRecipe1(T,  0,  256, crushedPurifiedTiny.mat(tMat, 9), FL.array(MT.HCl.gas(U * 4, T)), FL.array(MT.H2O.fluid(U * 3, F), MT.Cl.gas(U * 1, F)), crushedCentrifuged.mat(tMat, 1), OM.dust(MT.MnCl2, U2*3));
		}
		
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(MT.Au                 , 1), FL.array(MT.AquaRegia.fluid(13*U2, T)), FL.array(MT.ChloroauricAcid.fluid( 3*U , F), MT.NO.gas(U, F), FL.Water.make(3000)), crushedCentrifuged.mat(MT.Au      , 1), crushedCentrifugedTiny.mat(MT.Cu, 2), crushedCentrifugedTiny.mat(MT.Cu, 2), crushedCentrifugedTiny.mat(MT.Cu, 2), crushedCentrifugedTiny.mat(MT.Cu, 2), crushedCentrifugedTiny.mat(MT.Cu, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(MT.Au                 , 9), FL.array(MT.AquaRegia.fluid(13*U2, T)), FL.array(MT.ChloroauricAcid.fluid( 3*U , F), MT.NO.gas(U, F), FL.Water.make(3000)), crushedCentrifuged.mat(MT.Au      , 1), crushedCentrifugedTiny.mat(MT.Cu, 2), crushedCentrifugedTiny.mat(MT.Cu, 2), crushedCentrifugedTiny.mat(MT.Cu, 2), crushedCentrifugedTiny.mat(MT.Cu, 2), crushedCentrifugedTiny.mat(MT.Cu, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(MT.Cu                 , 1), FL.array(MT.AquaRegia.fluid(13*U2, T)), FL.array(MT.ChloroauricAcid.fluid( 3*U , F), MT.NO.gas(U, F), FL.Water.make(3000)), crushedCentrifuged.mat(MT.Cu      , 1), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(MT.Cu                 , 9), FL.array(MT.AquaRegia.fluid(13*U2, T)), FL.array(MT.ChloroauricAcid.fluid( 3*U , F), MT.NO.gas(U, F), FL.Water.make(3000)), crushedCentrifuged.mat(MT.Cu      , 1), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(MT.Midasium           , 1), FL.array(MT.AquaRegia.fluid(13*U2, T)), FL.array(MT.ChloroauricAcid.fluid( 3*U , F), MT.NO.gas(U, F), FL.Water.make(3000)), crushedCentrifuged.mat(MT.Midasium, 1), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(MT.Midasium           , 9), FL.array(MT.AquaRegia.fluid(13*U2, T)), FL.array(MT.ChloroauricAcid.fluid( 3*U , F), MT.NO.gas(U, F), FL.Water.make(3000)), crushedCentrifuged.mat(MT.Midasium, 1), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2), crushedCentrifugedTiny.mat(MT.Co, 2));
		
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(MT.Sn                 , 1), FL.array(MT.AquaRegia.fluid(13*U , T)), FL.array(MT.StannicChloride.fluid( 5*U2, F), MT.HCl.gas(4*U, F), MT.NO2.gas(3*U, F)/*, MT.NO2.gas(3* U2, F), MT.NO.gas(U, F), FL.Water.make(4500)*/), crushedCentrifuged.mat(MT.Sn                 , 1), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(MT.Sn                 , 9), FL.array(MT.AquaRegia.fluid(13*U , T)), FL.array(MT.StannicChloride.fluid( 5*U2, F), MT.HCl.gas(4*U, F), MT.NO2.gas(3*U, F)/*, MT.NO2.gas(3* U2, F), MT.NO.gas(U, F), FL.Water.make(4500)*/), crushedCentrifuged.mat(MT.Sn                 , 1), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurified    .mat(MT.OREMATS.Cassiterite, 1), FL.array(MT.AquaRegia.fluid(13*U , T)), FL.array(MT.StannicChloride.fluid( 5*U2, F), MT.HCl.gas(4*U, F), MT.NO2.gas(3*U, F)/*, MT.NO2.gas(3* U2, F), MT.NO.gas(U, F), FL.Water.make(4500)*/), crushedCentrifuged.mat(MT.OREMATS.Cassiterite, 1), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2));
		RM.Bath         .addRecipe1(T,  0,  256, new long[] {10000, 5000, 5000, 5000, 5000, 5000}, crushedPurifiedTiny.mat(MT.OREMATS.Cassiterite, 9), FL.array(MT.AquaRegia.fluid(13*U , T)), FL.array(MT.StannicChloride.fluid( 5*U2, F), MT.HCl.gas(4*U, F), MT.NO2.gas(3*U, F)/*, MT.NO2.gas(3* U2, F), MT.NO.gas(U, F), FL.Water.make(4500)*/), crushedCentrifuged.mat(MT.OREMATS.Cassiterite, 1), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2), crushedCentrifugedTiny.mat(MT.Zn, 2));
		
		
		if (IL.BoP_Grass_Smoldering .exists())  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  2000                                                                      }, IL.BoP_Grass_Smoldering      .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), OM.dust(MT.Ash, U4));
		/* Grass                            */  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  2000,  1000,  1000,  1000,  1000,   500,   500,   500                     }, ST.make(Blocks.grass         ,  1, W)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		/* Grass                            */  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  2000,  1000,  1000,  1000,  1000,   500,   500,   500                     }, ST.make(BlocksGT.Grass       ,  1, W)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		if (IL.BoP_Grass_Origin     .exists())  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  3000,  1500,  1500,  1500,  1500,   750,   750,   750                     }, IL.BoP_Grass_Origin          .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		if (IL.BoP_Grass_Long       .exists())  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  4000,  2000,  2000,  2000,  2000,  1000,  1000,  1000                     }, IL.BoP_Grass_Long            .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		if (IL.BoP_Grass_Loamy      .exists())  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  2000,  1000,  1000,  1000,  1000,   500,   500,   500                     }, IL.BoP_Grass_Loamy           .get( 1)/*|*/, IL.BoP_Coarse_Loamy.get(1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		if (IL.BoP_Grass_Silty      .exists())  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  2000,  1000,  1000,  1000,  1000,   500,   500,   500                     }, IL.BoP_Grass_Silty           .get( 1)/*|*/, IL.BoP_Coarse_Silty.get(1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		if (IL.BoP_Grass_Sandy      .exists())  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  2000,  1000,  1000,  1000,  1000,   500,   500,   500                     }, IL.BoP_Grass_Sandy           .get( 1)/*|*/, IL.BoP_Coarse_Sandy.get(1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		if (IL.EB_Grass_Alfisol     .exists())  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  2000,  1000,  1000,  1000,  1000,   500,   500,   500                     }, IL.EB_Grass_Alfisol          .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		if (IL.EB_Grass_Andisol     .exists())  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  2000,  1000,  1000,  1000,  1000,   500,   500,   500                     }, IL.EB_Grass_Andisol          .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		if (IL.EB_Grass_Gelisol     .exists())  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  2000,  1000,  1000,  1000,  1000,   500,   500,   500                     }, IL.EB_Grass_Gelisol          .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		if (IL.EB_Grass_Histosol    .exists())  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  2000,  1000,  1000,  1000,  1000,   500,   500,   500                     }, IL.EB_Grass_Histosol         .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		if (IL.EB_Grass_Inceptisol  .exists())  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  2000,  1000,  1000,  1000,  1000,   500,   500,   500                     }, IL.EB_Grass_Inceptisol       .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		if (IL.EB_Grass_Mollisol    .exists())  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  2000,  1000,  1000,  1000,  1000,   500,   500,   500                     }, IL.EB_Grass_Mollisol         .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		if (IL.EB_Grass_Oxisol      .exists())  RM.Sifting.addRecipe1(T, 16, 150, new long[] { 8000,  2000,  1000,  1000,  1000,  1000,   500,   500,   500                     }, IL.EB_Grass_Oxisol           .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(2), IL.MaCu_Bait_Grasshopper.get(1), IL.MaCu_Bait_Ant.get(1));
		/* Dirt                             */  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 8000,   500,   250,   250,   250,   250,   500                                   }, ST.make(Blocks.dirt          ,  1, 0)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(1));
		if (IL.BoP_Dirt_Loamy       .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 8000,   500,   250,   250,   250,   250,   500                                   }, IL.BoP_Dirt_Loamy            .get( 1)/*|*/, IL.BoP_Coarse_Loamy.get(1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(1));
		if (IL.BoP_Dirt_Silty       .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 8000,   500,   250,   250,   250,   250,   500                                   }, IL.BoP_Dirt_Silty            .get( 1)/*|*/, IL.BoP_Coarse_Silty.get(1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(1));
		if (IL.BoP_Dirt_Sandy       .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 8000,   500,   250,   250,   250,   250,   500                                   }, IL.BoP_Dirt_Sandy            .get( 1)/*|*/, IL.BoP_Coarse_Sandy.get(1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(1));
		if (IL.EB_Dirt_Alfisol      .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 8000,   500,   250,   250,   250,   250,   500,  1000                            }, IL.EB_Dirt_Alfisol           .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(1), OM.dust(MT.Clay, U4));
		if (IL.EB_Dirt_Andisol      .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 8000,   500,   250,   250,   250,   250,   500,  1000                            }, IL.EB_Dirt_Andisol           .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(1), OM.dust(MT.VolcanicAsh, U4));
		if (IL.EB_Dirt_Gelisol      .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 8000,   500,   250,   250,   250,   250,   500,  1000                            }, IL.EB_Dirt_Gelisol           .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(1), OM.dust(MT.Ice, U4));
		if (IL.EB_Dirt_Histosol     .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 8000,   500,   250,   250,   250,   250,   500,  1000                            }, IL.EB_Dirt_Histosol          .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(1), OM.ingot(MT.Peat, U4));
		if (IL.EB_Dirt_Inceptisol   .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 8000,   500,   250,   250,   250,   250,   500                                   }, IL.EB_Dirt_Inceptisol        .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(1));
		if (IL.EB_Dirt_Mollisol     .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 8000,   500,   250,   250,   250,   250,   500                                   }, IL.EB_Dirt_Mollisol          .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 2, 0), ST.make(Items.melon_seeds, 2, 0), ST.make(Items.pumpkin_seeds, 2, 0), IL.BoP_Turnip_Seeds.get(2), IL.EtFu_Beet_Seeds.get(2, IL.GaSu_Beet_Seeds.get(2)), IL.MaCu_Bait_Worm.get(2));
		if (IL.EB_Dirt_Oxisol       .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 8000,   500,   250,   250,   250,   250,   500,  1000                            }, IL.EB_Dirt_Oxisol            .get( 1)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Items.wheat_seeds, 1, 0), ST.make(Items.melon_seeds, 1, 0), ST.make(Items.pumpkin_seeds, 1, 0), IL.BoP_Turnip_Seeds.get(1), IL.EtFu_Beet_Seeds.get(1, IL.GaSu_Beet_Seeds.get(1)), IL.MaCu_Bait_Worm.get(1), OM.dust(MT.Al2O3, U4));
		/* Coarse Dirt                      */  RM.Sifting.addRecipe1(T, 16, 100, new long[] {  500,  3000,   250,   100                                                        }, ST.make(Blocks.dirt          ,  1, 1)/*|*/, ST.make(Blocks.sand, 1, 0), OM.dust(MT.Stone     , U4), OM.dust(MT.ClayBrown), IL.Clay_Ball_Brown.get(1));
		if (IL.EtFu_Dirt            .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] {  500,  3000,   250,   100                                                        }, IL.EtFu_Dirt                 .wild(1)/*|*/, ST.make(Blocks.sand, 1, 0), OM.dust(MT.Stone     , U4), OM.dust(MT.ClayBrown), IL.Clay_Ball_Brown.get(1));
		if (IL.BoP_Coarse_Loamy     .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] {  500,  3000,  1000,   400                                                        }, IL.BoP_Coarse_Loamy          .get( 1)/*|*/, ST.make(Blocks.sand, 1, 0), OM.dust(MT.Stone     , U4), OM.dust(MT.Clay), ST.make(Items.clay_ball, 1, 0));
		if (IL.BoP_Coarse_Silty     .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] {  500,  6000,   250,   100                                                        }, IL.BoP_Coarse_Silty          .get( 1)/*|*/, ST.make(Blocks.sand, 1, 0), OM.dust(MT.Siltstone , U4), OM.dust(MT.Clay), ST.make(Items.clay_ball, 1, 0));
		if (IL.BoP_Coarse_Sandy     .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 1500,  3000,   250,   100                                                        }, IL.BoP_Coarse_Sandy          .get( 1)/*|*/, ST.make(Blocks.sand, 1, 0), OM.dust(MT.Stone     , U4), OM.dust(MT.ClayBrown), IL.Clay_Ball_Brown.get(1));
		if (IL.BoP_Dirt_Dried       .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 1000,  2500,   250,   100                                                        }, IL.BoP_Dirt_Dried            .get( 1)/*|*/, ST.make(Blocks.sand, 1, 0), OM.dust(MT.Stone     , U4), OM.dust(MT.ClayBrown), IL.Clay_Ball_Brown.get(1));
		if (IL.BoP_Dirt_Hard        .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 1000,  2500,   250,   100                                                        }, IL.BoP_Dirt_Hard             .get( 1)/*|*/, ST.make(Blocks.sand, 1, 0), OM.dust(MT.Stone     , U4), OM.dust(MT.ClayBrown), IL.Clay_Ball_Brown.get(1));
		/* Gravel                           */  RM.Sifting.addRecipe1(T, 16, 200, new long[] { 1000,  5000,  2500,   500,   200,  5000,   500,   100                            }, ST.make(Blocks.gravel        ,  1, W)/*|*/, ST.make(Blocks.sand, 1, 0), OM.dust(MT.Stone     , U4), rockGt.mat(MT.Stone     , 1), OM.dust(MT.Clay   ), ST.make(Items.clay_ball, 1, 0), ST.make(Items.flint, 1, 0), OM.dust(MT.MilkyQuartz , U4), OM.gem(MT.MilkyQuartz , U));
												RM.Sifting.addRecipe1(T, 16, 200, new long[] { 1000,  5000,  2500,   500,   200,  5000,   500,   100                            }, OM.dust(MT.Gravel                   )/*|*/, ST.make(Blocks.sand, 1, 0), OM.dust(MT.Stone     , U4), rockGt.mat(MT.Stone     , 1), OM.dust(MT.Clay   ), ST.make(Items.clay_ball, 1, 0), ST.make(Items.flint, 1, 0), OM.dust(MT.MilkyQuartz , U4), OM.gem(MT.MilkyQuartz , U));
		if (IL.EtFu_Gravel          .exists())  RM.Sifting.addRecipe1(T, 16, 200, new long[] { 1000,  5000,  2500,   500,   200,  5000,   500,   100                            }, IL.EtFu_Gravel               .wild(1)/*|*/, ST.make(Blocks.sand, 1, 0), OM.dust(MT.Stone     , U4), rockGt.mat(MT.Stone     , 1), OM.dust(MT.Clay   ), ST.make(Items.clay_ball, 1, 0), ST.make(Items.flint, 1, 0), OM.dust(MT.MilkyQuartz , U4), OM.gem(MT.MilkyQuartz , U));
		if (IL.NeLi_Gravel          .exists())  RM.Sifting.addRecipe1(T, 16, 200, new long[] { 1000,  5000,  2500,   500,   200,  5000,   500,   100                            }, IL.NeLi_Gravel               .wild(1)/*|*/, ST.make(Blocks.sand, 1, 1), OM.dust(MT.Blackstone, U4), rockGt.mat(MT.Blackstone, 1), OM.dust(MT.ClayRed), IL.Clay_Ball_Red       .get(1), ST.make(Items.flint, 1, 0), OM.dust(MT.NetherQuartz, U4), OM.gem(MT.NetherQuartz, U));
		/* Mud                              */  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 9000,  2000                                                                      }, ST.make(BlocksGT.Diggables   ,  1, 0)/*|*/, ST.make(Blocks.dirt, 1, 0), IL.MaCu_Bait_Worm.get(2));
		if (IL.BoP_Mud              .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 9000,  2000                                                                      }, IL.BoP_Mud                    .get(1)/*|*/, ST.make(Blocks.dirt, 1, 0), IL.MaCu_Bait_Worm.get(2));
		/* Misc                             */  RM.Sifting.addRecipe1(T, 16, 200, new long[] { 8000,  1000,   500,   500,   500                                                 }, ST.make(Blocks.dirt          ,  1, 2)/*|*/, ST.make(Blocks.dirt, 1, 1), OM.dust(MT.Ash, U4), IL.MaCu_Bait_Ant.get(2), IL.MaCu_Bait_Maggot.get(1), IL.MaCu_Bait_Worm.get(1));
												RM.Sifting.addRecipe1(T, 16, 200, new long[] { 8000,  1000,  1000                                                               }, ST.make(Blocks.mycelium      ,  1, W)/*|*/, ST.make(Blocks.dirt, 1, 1), ST.make(Blocks.brown_mushroom, 1, 0), ST.make(Blocks.red_mushroom, 1, 0));
												RM.Sifting.addRecipe1(T, 16, 300, new long[] { 9000,  4000,   500,   500,   200,  2000,  4000,   400,   200                     }, OM.dust(MT.SoulSand                 )/*|*/, OM.dust(MT.Netherrack, U4), rockGt.mat(MT.Netherrack, 1), OM.dust(MT.ClayRed), IL.Clay_Ball_Red.get(1), OM.dust(MT.Niter, U4), OM.dust(MT.NetherQuartz, U4), OM.gem(MT.NetherQuartz, U), ST.make(Items.bone, 1, 0), ST.make(Items.nether_wart, 1, 0));
												RM.Sifting.addRecipe1(T, 16, 300, new long[] { 9000,  4000,   500,   500,   200,  2000,  4000,   400,   200                     }, ST.make(Blocks.soul_sand     ,  1, W)/*|*/, OM.dust(MT.Netherrack, U4), rockGt.mat(MT.Netherrack, 1), OM.dust(MT.ClayRed), IL.Clay_Ball_Red.get(1), OM.dust(MT.Niter, U4), OM.dust(MT.NetherQuartz, U4), OM.gem(MT.NetherQuartz, U), ST.make(Items.bone, 1, 0), ST.make(Items.nether_wart, 1, 0));
		if (IL.NePl_SoulSoil        .exists())  RM.Sifting.addRecipe1(T, 16, 300, new long[] { 9000,  4000,   500,   500,   200,  2000,  4000,   400,   200                     }, IL.NePl_SoulSoil              .get(1)/*|*/, OM.dust(MT.Netherrack, U4), rockGt.mat(MT.Netherrack, 1), OM.dust(MT.ClayRed), IL.Clay_Ball_Red.get(1), OM.dust(MT.Niter, U4), OM.dust(MT.NetherQuartz, U4), OM.gem(MT.NetherQuartz, U), ST.make(Items.bone, 1, 0), ST.make(Items.nether_wart, 1, 0));
		if (IL.NeLi_SoulSoil        .exists())  RM.Sifting.addRecipe1(T, 16, 300, new long[] { 9000,  4000,   500,   500,   200,  2000,  4000,   400,   200                     }, IL.NeLi_SoulSoil              .get(1)/*|*/, OM.dust(MT.Netherrack, U4), rockGt.mat(MT.Netherrack, 1), OM.dust(MT.ClayRed), IL.Clay_Ball_Red.get(1), OM.dust(MT.Niter, U4), OM.dust(MT.NetherQuartz, U4), OM.gem(MT.NetherQuartz, U), ST.make(Items.bone, 1, 0), ST.make(Items.nether_wart, 1, 0));
		if (IL.AETHER_Sand          .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 9000,  2000                                                                      }, IL.AETHER_Sand                .get(1)/*|*/, ST.make(Blocks.sand, 1, 0), IL.MaCu_Bait_Ant.get(1));
		if (IL.BoP_Quicksand        .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] { 9000,  2000                                                                      }, IL.BoP_Quicksand              .get(1)/*|*/, ST.make(Blocks.sand, 1, 0), IL.MaCu_Bait_Ant.get(1));
		if (IL.BoP_Sand_Hard        .exists())  RM.Sifting.addRecipe1(T, 16, 100, new long[] {10000                                                                             }, IL.BoP_Sand_Hard              .get(1)/*|*/, ST.make(Blocks.sand, 1, 0));
		
		
		
		
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {10000, 10, 40, 150, 200, 400, 500}, crushedPurified    .mat(MT.Sn                      , 1), crushedCentrifuged.mat(MT.Sn                      , 1), gemExquisite.mat(MT.Zircon, 1), gemFlawless.mat(MT.Zircon, 1), gem.mat(MT.Zircon, 1), gemFlawed.mat(MT.Zircon, 1), gemChipped.mat(MT.Zircon, 1), dust.mat(MT.Zircon, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {10000, 10, 40, 150, 200, 400, 500}, crushedPurifiedTiny.mat(MT.Sn                      , 9), crushedCentrifuged.mat(MT.Sn                      , 1), gemExquisite.mat(MT.Zircon, 1), gemFlawless.mat(MT.Zircon, 1), gem.mat(MT.Zircon, 1), gemFlawed.mat(MT.Zircon, 1), gemChipped.mat(MT.Zircon, 1), dust.mat(MT.Zircon, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {10000, 10, 40, 150, 200, 400, 500}, crushedPurified    .mat(MT.OREMATS.Cassiterite     , 1), crushedCentrifuged.mat(MT.OREMATS.Cassiterite     , 1), gemExquisite.mat(MT.Zircon, 1), gemFlawless.mat(MT.Zircon, 1), gem.mat(MT.Zircon, 1), gemFlawed.mat(MT.Zircon, 1), gemChipped.mat(MT.Zircon, 1), dust.mat(MT.Zircon, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {10000, 10, 40, 150, 200, 400, 500}, crushedPurifiedTiny.mat(MT.OREMATS.Cassiterite     , 9), crushedCentrifuged.mat(MT.OREMATS.Cassiterite     , 1), gemExquisite.mat(MT.Zircon, 1), gemFlawless.mat(MT.Zircon, 1), gem.mat(MT.Zircon, 1), gemFlawed.mat(MT.Zircon, 1), gemChipped.mat(MT.Zircon, 1), dust.mat(MT.Zircon, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {10000, 10, 40, 150, 200, 400, 500}, crushedPurified    .mat(MT.OREMATS.CassiteriteSand , 1), crushedCentrifuged.mat(MT.OREMATS.CassiteriteSand , 1), gemExquisite.mat(MT.Zircon, 1), gemFlawless.mat(MT.Zircon, 1), gem.mat(MT.Zircon, 1), gemFlawed.mat(MT.Zircon, 1), gemChipped.mat(MT.Zircon, 1), dust.mat(MT.Zircon, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {10000, 10, 40, 150, 200, 400, 500}, crushedPurifiedTiny.mat(MT.OREMATS.CassiteriteSand , 9), crushedCentrifuged.mat(MT.OREMATS.CassiteriteSand , 1), gemExquisite.mat(MT.Zircon, 1), gemFlawless.mat(MT.Zircon, 1), gem.mat(MT.Zircon, 1), gemFlawed.mat(MT.Zircon, 1), gemChipped.mat(MT.Zircon, 1), dust.mat(MT.Zircon, 1));
		
		
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {7000, 1000, 5000}, crushedPurified    .mat(MT.ChargedCertusQuartz, 1), gem.mat(MT.ChargedCertusQuartz, 1), gem.mat(MT.CertusQuartz, 1), dust.mat(MT.CertusQuartz, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {7000, 1000, 5000}, crushedPurifiedTiny.mat(MT.ChargedCertusQuartz, 9), gem.mat(MT.ChargedCertusQuartz, 1), gem.mat(MT.CertusQuartz, 1), dust.mat(MT.CertusQuartz, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {1000, 7000, 5000}, crushedPurified    .mat(MT.CertusQuartz       , 1), gem.mat(MT.ChargedCertusQuartz, 1), gem.mat(MT.CertusQuartz, 1), dust.mat(MT.CertusQuartz, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {1000, 7000, 5000}, crushedPurifiedTiny.mat(MT.CertusQuartz       , 9), gem.mat(MT.ChargedCertusQuartz, 1), gem.mat(MT.CertusQuartz, 1), dust.mat(MT.CertusQuartz, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {      8000, 5000}, crushedPurified    .mat(MT.NetherQuartz       , 1), gem.mat(MT.NetherQuartz       , 1), dust.mat(MT.NetherQuartz, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {      8000, 5000}, crushedPurifiedTiny.mat(MT.NetherQuartz       , 9), gem.mat(MT.NetherQuartz       , 1), dust.mat(MT.NetherQuartz, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {      8000, 5000}, crushedPurified    .mat(MT.VoidQuartz         , 1), gem.mat(MT.VoidQuartz         , 1), dust.mat(MT.VoidQuartz, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {      8000, 5000}, crushedPurifiedTiny.mat(MT.VoidQuartz         , 9), gem.mat(MT.VoidQuartz         , 1), dust.mat(MT.VoidQuartz, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {      8000, 5000}, crushedPurified    .mat(MT.MilkyQuartz        , 1), gem.mat(MT.MilkyQuartz        , 1), dust.mat(MT.MilkyQuartz, 1));
		RM.Sifting      .addRecipe1(T, 16,  128, new long[] {      8000, 5000}, crushedPurifiedTiny.mat(MT.MilkyQuartz        , 9), gem.mat(MT.MilkyQuartz        , 1), dust.mat(MT.MilkyQuartz, 1));
		
		
		RM.Sifting              .addRecipe1(T, 16,  200, new long[] {9900, 500, 500}        , ST.make(BlocksGT.Sands, 1, 0), dust.mat(MT.OREMATS.Magnetite, 1), rockGt.mat(MT.Stone, 1), nugget.mat(MT.Au, 1));
		RM.MagneticSeparator    .addRecipe1(T, 16,  144, new long[] {9900, 500, 500, 500}   , ST.make(BlocksGT.Sands, 1, 0), dust.mat(MT.OREMATS.Magnetite, 1), rockGt.mat(MT.Stone, 1), nugget.mat(MT.Au, 1), dustTiny.mat(MT.Au, 2));
		RM.Centrifuge           .addRecipe1(T, 16,  256, new long[] {9000, 1000}            , ST.make(BlocksGT.Sands, 1, 0), dust.mat(MT.OREMATS.Magnetite, 1), dust.mat(MT.V2O5, 1));
		
		
		
		RM.Smelter      .addRecipe1(T, 64, 1440, 5000, OM.dust(MT.Monazite     ), NF, MT.He.gas(U2 , F), OM.dust(MT.RareEarth     ));
		RM.Smelter      .addRecipe1(T, 64,  360, 5000, OM.dust(MT.Monazite,  U4), NF, MT.He.gas(U8 , F), OM.dust(MT.RareEarth,  U4));
		RM.Smelter      .addRecipe1(T, 64,  160, 5000, OM.dust(MT.Monazite,  U9), NF, MT.He.gas(U18, F), OM.dust(MT.RareEarth,  U9));
		
		
		
		RM.Mixer        .addRecipe2(T, 16,  256, crushedPurified    .mat(MT.Yellorite           , 1), dust.mat(MT.Al, 1), crushedCentrifuged.mat(MT.Yellorium, 1));
		RM.Mixer        .addRecipe2(T, 16,  256, crushedPurifiedTiny.mat(MT.Yellorite           , 9), dust.mat(MT.Al, 1), crushedCentrifuged.mat(MT.Yellorium, 1));
		RM.Mixer        .addRecipe2(T, 16,  256, crushedPurified    .mat(MT.Yellorite           , 1), dust.mat(MT.Mg, 1), crushedCentrifuged.mat(MT.Yellorium, 1));
		RM.Mixer        .addRecipe2(T, 16,  256, crushedPurifiedTiny.mat(MT.Yellorite           , 9), dust.mat(MT.Mg, 1), crushedCentrifuged.mat(MT.Yellorium, 1));
		RM.Mixer        .addRecipe2(T, 16,  256, crushedPurified    .mat(MT.U_238               , 1), dust.mat(MT.Al, 1), crushedCentrifugedTiny.mat(MT.U_238, 12));
		RM.Mixer        .addRecipe2(T, 16,  256, crushedPurifiedTiny.mat(MT.U_238               , 9), dust.mat(MT.Al, 1), crushedCentrifugedTiny.mat(MT.U_238, 12));
		RM.Mixer        .addRecipe2(T, 16,  256, crushedPurified    .mat(MT.U_238               , 1), dust.mat(MT.Mg, 1), crushedCentrifugedTiny.mat(MT.U_238, 12));
		RM.Mixer        .addRecipe2(T, 16,  256, crushedPurifiedTiny.mat(MT.U_238               , 9), dust.mat(MT.Mg, 1), crushedCentrifugedTiny.mat(MT.U_238, 12));
		RM.Mixer        .addRecipe2(T, 16,  256, crushedPurified    .mat(MT.U_235               , 1), dust.mat(MT.Al, 1), crushedCentrifugedTiny.mat(MT.U_235, 12));
		RM.Mixer        .addRecipe2(T, 16,  256, crushedPurifiedTiny.mat(MT.U_235               , 9), dust.mat(MT.Al, 1), crushedCentrifugedTiny.mat(MT.U_235, 12));
		RM.Mixer        .addRecipe2(T, 16,  256, crushedPurified    .mat(MT.U_235               , 1), dust.mat(MT.Mg, 1), crushedCentrifugedTiny.mat(MT.U_235, 12));
		RM.Mixer        .addRecipe2(T, 16,  256, crushedPurifiedTiny.mat(MT.U_235               , 9), dust.mat(MT.Mg, 1), crushedCentrifugedTiny.mat(MT.U_235, 12));
		
		
		RM.Centrifuge   .addRecipe1(T, 256, 256, new long[] {10000,  150,  150}, crushedPurifiedTiny    .mat(MT.Be          , 1), crushedCentrifugedTiny .mat(MT.Be        , 1), crushedCentrifugedTiny .mat(MT.Be_7, 8), crushedCentrifugedTiny .mat(MT.Be_8, 8));
		RM.Centrifuge   .addRecipe1(T, 256,2304, new long[] {10000, 1350, 1350}, crushedPurified        .mat(MT.Be          , 1), crushedCentrifuged     .mat(MT.Be        , 1), crushedCentrifugedTiny .mat(MT.Be_7, 8), crushedCentrifugedTiny .mat(MT.Be_8, 8));
		RM.Centrifuge   .addRecipe1(T, 256, 256, new long[] {10000,  300      }, crushedPurifiedTiny    .mat(MT.Li          , 1), crushedCentrifugedTiny .mat(MT.Li        , 1), crushedCentrifugedTiny .mat(MT.Li_6, 8));
		RM.Centrifuge   .addRecipe1(T, 256,2304, new long[] {10000, 2700      }, crushedPurified        .mat(MT.Li          , 1), crushedCentrifuged     .mat(MT.Li        , 1), crushedCentrifugedTiny .mat(MT.Li_6, 8));
		RM.Centrifuge   .addRecipe1(T, 256, 256, new long[] {10000,  150,  150}, crushedPurifiedTiny    .mat(MT.C           , 1), crushedCentrifugedTiny .mat(MT.C         , 1), crushedCentrifugedTiny .mat(MT.C_13, 8), crushedCentrifugedTiny.mat(MT.C_14, 8));
		RM.Centrifuge   .addRecipe1(T, 256,2304, new long[] {10000, 1350, 1350}, crushedPurified        .mat(MT.C           , 1), crushedCentrifuged     .mat(MT.C         , 1), crushedCentrifugedTiny .mat(MT.C_13, 8), crushedCentrifugedTiny.mat(MT.C_14, 8));
		RM.Centrifuge   .addRecipe1(T, 512, 256, new long[] {10000,  300      }, crushedPurifiedTiny    .mat(MT.Co          , 1), crushedCentrifugedTiny .mat(MT.Co        , 1), crushedCentrifugedTiny .mat(MT.Co_60, 8));
		RM.Centrifuge   .addRecipe1(T, 512,2304, new long[] {10000, 2700      }, crushedPurified        .mat(MT.Co          , 1), crushedCentrifuged     .mat(MT.Co        , 1), crushedCentrifugedTiny .mat(MT.Co_60, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  300      }, crushedPurifiedTiny    .mat(MT.U_238       , 1), crushedCentrifugedTiny .mat(MT.U_238     , 1), crushedCentrifugedTiny .mat(MT.U_235, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 2700      }, crushedPurified        .mat(MT.U_238       , 1), crushedCentrifuged     .mat(MT.U_238     , 1), crushedCentrifugedTiny .mat(MT.U_235, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  150,  150}, crushedPurifiedTiny    .mat(MT.Pu          , 1), crushedCentrifugedTiny .mat(MT.Pu        , 1), crushedCentrifugedTiny .mat(MT.Pu_241, 8), crushedCentrifugedTiny.mat(MT.Pu_243, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 1350, 1350}, crushedPurified        .mat(MT.Pu          , 1), crushedCentrifuged     .mat(MT.Pu        , 1), crushedCentrifugedTiny .mat(MT.Pu_241, 8), crushedCentrifugedTiny.mat(MT.Pu_243, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  300      }, crushedPurifiedTiny    .mat(MT.Am          , 1), crushedCentrifugedTiny .mat(MT.Am        , 1), crushedCentrifugedTiny .mat(MT.Am_241, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 2700      }, crushedPurified        .mat(MT.Am          , 1), crushedCentrifuged     .mat(MT.Am        , 1), crushedCentrifugedTiny .mat(MT.Am_241, 8));
		RM.Centrifuge   .addRecipe1(T,1536, 256, new long[] {10000,  300      }, crushedPurifiedTiny    .mat(MT.Fl          , 1), crushedCentrifugedTiny .mat(MT.Fl        , 1), crushedCentrifugedTiny .mat(MT.Fl_298, 8));
		RM.Centrifuge   .addRecipe1(T,1536,2304, new long[] {10000, 2700      }, crushedPurified        .mat(MT.Fl          , 1), crushedCentrifuged     .mat(MT.Fl        , 1), crushedCentrifugedTiny .mat(MT.Fl_298, 8));
		RM.Centrifuge   .addRecipe1(T,2048, 256, new long[] {10000,  150,  150}, crushedPurifiedTiny    .mat(MT.Nq          , 1), crushedCentrifugedTiny .mat(MT.Nq        , 1), crushedCentrifugedTiny .mat(MT.Nq_522, 8), crushedCentrifugedTiny.mat(MT.Nq_528, 8));
		RM.Centrifuge   .addRecipe1(T,2048,2304, new long[] {10000, 1350, 1350}, crushedPurified        .mat(MT.Nq          , 1), crushedCentrifuged     .mat(MT.Nq        , 1), crushedCentrifugedTiny .mat(MT.Nq_522, 8), crushedCentrifugedTiny.mat(MT.Nq_528, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  600      }, crushedPurifiedTiny    .mat(MT.Cyanite     , 1), crushedCentrifugedTiny .mat(MT.Cyanite   , 1), crushedCentrifugedTiny .mat(MT.Cyanite, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 5400      }, crushedPurified        .mat(MT.Cyanite     , 1), crushedCentrifuged     .mat(MT.Cyanite   , 1), crushedCentrifugedTiny .mat(MT.Cyanite, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  600      }, crushedPurifiedTiny    .mat(MT.Yellorium   , 1), crushedCentrifugedTiny .mat(MT.Yellorium , 1), crushedCentrifugedTiny .mat(MT.Yellorium, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 5400      }, crushedPurified        .mat(MT.Yellorium   , 1), crushedCentrifuged     .mat(MT.Yellorium , 1), crushedCentrifugedTiny .mat(MT.Yellorium, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  600      }, crushedPurifiedTiny    .mat(MT.Blutonium   , 1), crushedCentrifugedTiny .mat(MT.Blutonium , 1), crushedCentrifugedTiny .mat(MT.Blutonium, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 5400      }, crushedPurified        .mat(MT.Blutonium   , 1), crushedCentrifuged     .mat(MT.Blutonium , 1), crushedCentrifugedTiny .mat(MT.Blutonium, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  600      }, crushedPurifiedTiny    .mat(MT.Ludicrite   , 1), crushedCentrifugedTiny .mat(MT.Ludicrite , 1), crushedCentrifugedTiny .mat(MT.Ludicrite, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 5400      }, crushedPurified        .mat(MT.Ludicrite   , 1), crushedCentrifuged     .mat(MT.Ludicrite , 1), crushedCentrifugedTiny .mat(MT.Ludicrite, 8));
		
		RM.Centrifuge   .addRecipe1(T, 256, 256, new long[] {10000,  150,  150}, crushedCentrifugedTiny .mat(MT.Be          , 1), dustTiny               .mat(MT.Be        , 1), dustTiny               .mat(MT.Be_7, 8), dustTiny.mat(MT.Be_8, 8));
		RM.Centrifuge   .addRecipe1(T, 256,2304, new long[] {10000, 1350, 1350}, crushedCentrifuged     .mat(MT.Be          , 1), dust                   .mat(MT.Be        , 1), dustTiny               .mat(MT.Be_7, 8), dustTiny.mat(MT.Be_8, 8));
		RM.Centrifuge   .addRecipe1(T, 256, 256, new long[] {10000,  300      }, crushedCentrifugedTiny .mat(MT.Li          , 1), dustTiny               .mat(MT.Li        , 1), dustTiny               .mat(MT.Li_6, 8));
		RM.Centrifuge   .addRecipe1(T, 256,2304, new long[] {10000, 2700      }, crushedCentrifuged     .mat(MT.Li          , 1), dust                   .mat(MT.Li        , 1), dustTiny               .mat(MT.Li_6, 8));
		RM.Centrifuge   .addRecipe1(T, 256, 256, new long[] {10000,  150,  150}, crushedCentrifugedTiny .mat(MT.C           , 1), dustTiny               .mat(MT.C         , 1), dustTiny               .mat(MT.C_13, 8), dustTiny.mat(MT.C_14, 8));
		RM.Centrifuge   .addRecipe1(T, 256,2304, new long[] {10000, 1350, 1350}, crushedCentrifuged     .mat(MT.C           , 1), dust                   .mat(MT.C         , 1), dustTiny               .mat(MT.C_13, 8), dustTiny.mat(MT.C_14, 8));
		RM.Centrifuge   .addRecipe1(T, 512, 256, new long[] {10000,  300      }, crushedCentrifugedTiny .mat(MT.Co          , 1), dustTiny               .mat(MT.Co        , 1), dustTiny               .mat(MT.Co_60, 8));
		RM.Centrifuge   .addRecipe1(T, 512,2304, new long[] {10000, 2700      }, crushedCentrifuged     .mat(MT.Co          , 1), dust                   .mat(MT.Co        , 1), dustTiny               .mat(MT.Co_60, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  300      }, crushedCentrifugedTiny .mat(MT.U_238       , 1), dustTiny               .mat(MT.U_238     , 1), dustTiny               .mat(MT.U_235, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 2700      }, crushedCentrifuged     .mat(MT.U_238       , 1), dust                   .mat(MT.U_238     , 1), dustTiny               .mat(MT.U_235, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  150,  150}, crushedCentrifugedTiny .mat(MT.Pu          , 1), dustTiny               .mat(MT.Pu        , 1), dustTiny               .mat(MT.Pu_241, 8), dustTiny.mat(MT.Pu_243, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 1350, 1350}, crushedCentrifuged     .mat(MT.Pu          , 1), dust                   .mat(MT.Pu        , 1), dustTiny               .mat(MT.Pu_241, 8), dustTiny.mat(MT.Pu_243, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  300      }, crushedCentrifugedTiny .mat(MT.Am          , 1), dustTiny               .mat(MT.Am        , 1), dustTiny               .mat(MT.Am_241, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 2700      }, crushedCentrifuged     .mat(MT.Am          , 1), dust                   .mat(MT.Am        , 1), dustTiny               .mat(MT.Am_241, 8));
		RM.Centrifuge   .addRecipe1(T,1536, 256, new long[] {10000,  300      }, crushedCentrifugedTiny .mat(MT.Fl          , 1), dustTiny               .mat(MT.Fl        , 1), dustTiny               .mat(MT.Fl_298, 8));
		RM.Centrifuge   .addRecipe1(T,1536,2304, new long[] {10000, 2700      }, crushedCentrifuged     .mat(MT.Fl          , 1), dust                   .mat(MT.Fl        , 1), dustTiny               .mat(MT.Fl_298, 8));
		RM.Centrifuge   .addRecipe1(T,2048, 256, new long[] {10000,  150,  150}, crushedCentrifugedTiny .mat(MT.Nq          , 1), dustTiny               .mat(MT.Nq        , 1), dustTiny               .mat(MT.Nq_522, 8), dustTiny.mat(MT.Nq_528, 8));
		RM.Centrifuge   .addRecipe1(T,2048,2304, new long[] {10000, 1350, 1350}, crushedCentrifuged     .mat(MT.Nq          , 1), dust                   .mat(MT.Nq        , 1), dustTiny               .mat(MT.Nq_522, 8), dustTiny.mat(MT.Nq_528, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  600      }, crushedCentrifugedTiny .mat(MT.Cyanite     , 1), dustTiny               .mat(MT.Cyanite   , 1), dustTiny               .mat(MT.Cyanite, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 5400      }, crushedCentrifuged     .mat(MT.Cyanite     , 1), dust                   .mat(MT.Cyanite   , 1), dustTiny               .mat(MT.Cyanite, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  600      }, crushedCentrifugedTiny .mat(MT.Yellorium   , 1), dustTiny               .mat(MT.Yellorium , 1), dustTiny               .mat(MT.Yellorium, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 5400      }, crushedCentrifuged     .mat(MT.Yellorium   , 1), dust                   .mat(MT.Yellorium , 1), dustTiny               .mat(MT.Yellorium, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  600      }, crushedCentrifugedTiny .mat(MT.Blutonium   , 1), dustTiny               .mat(MT.Blutonium , 1), dustTiny               .mat(MT.Blutonium, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 5400      }, crushedCentrifuged     .mat(MT.Blutonium   , 1), dust                   .mat(MT.Blutonium , 1), dustTiny               .mat(MT.Blutonium, 8));
		RM.Centrifuge   .addRecipe1(T,1024, 256, new long[] {10000,  600      }, crushedCentrifugedTiny .mat(MT.Ludicrite   , 1), dustTiny               .mat(MT.Ludicrite , 1), dustTiny               .mat(MT.Ludicrite, 8));
		RM.Centrifuge   .addRecipe1(T,1024,2304, new long[] {10000, 5400      }, crushedCentrifuged     .mat(MT.Ludicrite   , 1), dust                   .mat(MT.Ludicrite , 1), dustTiny               .mat(MT.Ludicrite, 8));
		
		
		final long[] tSluice = {10000, 300, 300, 300, 300, 300, 300, 300, 300}, tMagnet = {10000, 600, 600, 600, 600, 600};
		
		for (OreDictMaterial aMat : OreDictMaterial.MATERIAL_ARRAY) if (aMat != null && !aMat.contains(ANTIMATTER) && aMat != MT.Bedrock) {
			ItemStack tDust = dust.mat(aMat, 1);
			if (ST.invalid(tDust)) continue;
			
			ArrayListNoNulls<OreDictMaterial> tMagnetList = new ArrayListNoNulls<>();
			if (aMat.containsAny(MAGNETIC_PASSIVE, MAGNETIC_ACTIVE)) {
				for (OreDictMaterial tMaterial : aMat.mByProducts) if (!tMaterial.containsAny(MAGNETIC_PASSIVE, MAGNETIC_ACTIVE)) tMagnetList.add(tMaterial);
			} else {
				for (OreDictMaterial tMaterial : aMat.mByProducts) if ( tMaterial.containsAny(MAGNETIC_PASSIVE, MAGNETIC_ACTIVE)) tMagnetList.add(tMaterial);
			}
			
			ItemStack tCrushed = crushed.mat(aMat, 1), tCrushedTiny = crushedTiny.mat(aMat, 1);
			if (ST.valid(tCrushed) && ST.valid(tCrushedTiny)) {
				CR.shapeless(gemFlawed.mat(aMat, 1), CR.DEF_NAC_NCC | CR.ONLY_IF_HAS_RESULT, new Object[] {crushed.dat(aMat)});
				
				ItemStack tByProductMercury = null, tByproductPersulfate = null;
				for (OreDictMaterial tByProduct : aMat.mByProducts) if (tByProduct.contains(WASHING_MERCURY) && ST.valid(tByProductMercury = OP.crushedPurifiedTiny.mat(tByProduct, OM.dust(tByProduct, U9), 1))) break;
				if (ST.invalid(tByProductMercury) && aMat.contains(WASHING_MERCURY)) tByProductMercury = OP.crushedPurifiedTiny.mat(aMat, OM.dust(aMat, U9), 1);
				for (OreDictMaterial tByProduct : aMat.mByProducts) if (tByProduct.contains(WASHING_PERSULFATE) && ST.valid(tByproductPersulfate = OP.crushedPurifiedTiny.mat(tByProduct, OM.dust(tByProduct, U9), 1))) break;
				if (ST.invalid(tByproductPersulfate) && aMat.contains(WASHING_PERSULFATE)) tByproductPersulfate = OP.crushedPurifiedTiny.mat(aMat, OM.dust(aMat, U9), 1);
				
				if (ENABLE_ADDING_IC2_OREWASHER_RECIPES) {
				RM.ic2_orewasher(tCrushed                   , 1000                                          , crushedPurified       .mat(aMat, 1), crushedPurifiedTiny      .mat(UT.Code.select(0, aMat, aMat.mByProducts), 2), dust.mat(MT.Stone, 1));
				RM.ic2_orewasher(ST.amount(9,tCrushedTiny)  , 1000                                          , crushedPurified       .mat(aMat, 1), crushedPurifiedTiny      .mat(UT.Code.select(0, aMat, aMat.mByProducts), 2), dust.mat(MT.Stone, 1));
				}
				if (ENABLE_ADDING_IC2_CENTRIFUGE_RECIPES) {
				RM.ic2_centrifuge(tCrushed                  , Math.min(5000, Math.abs(aMat.getMass() * 20)) , crushedCentrifuged    .mat(aMat, 1), crushedCentrifugedTiny   .mat(UT.Code.select(1, aMat, aMat.mByProducts), 1), dust.mat(MT.Stone, 1));
				RM.ic2_centrifuge(ST.amount(9,tCrushedTiny) , Math.min(5000, Math.abs(aMat.getMass() * 20)) , crushedCentrifuged    .mat(aMat, 1), crushedCentrifugedTiny   .mat(UT.Code.select(1, aMat, aMat.mByProducts), 1), dust.mat(MT.Stone, 1));
				}
				
				OreDictMaterial[] tSluiceProducts = new OreDictMaterial[8];
				for (int i = 0; i < tSluiceProducts.length; i++) tSluiceProducts[i] = aMat.mByProducts.isEmpty()?aMat:aMat.mByProducts.get(i%aMat.mByProducts.size());
				RM.Sluice.addRecipe1(T, 16, 144, tSluice, tCrushed     , FL.Water.make(900), FL.Sluice.make(900), crushedPurified    .mat(aMat, 1, tDust), crushedPurifiedTiny.mat(tSluiceProducts[0], 9, dustTiny.mat(tSluiceProducts[0], 9)), crushedPurifiedTiny.mat(tSluiceProducts[1], 9, dustTiny.mat(tSluiceProducts[1], 9)), crushedPurifiedTiny.mat(tSluiceProducts[2], 9, dustTiny.mat(tSluiceProducts[2], 9)), crushedPurifiedTiny.mat(tSluiceProducts[3], 9, dustTiny.mat(tSluiceProducts[3], 9)), crushedPurifiedTiny.mat(tSluiceProducts[4], 9, dustTiny.mat(tSluiceProducts[4], 9)), crushedPurifiedTiny.mat(tSluiceProducts[5], 9, dustTiny.mat(tSluiceProducts[5], 9)), crushedPurifiedTiny.mat(tSluiceProducts[6], 9, dustTiny.mat(tSluiceProducts[6], 9)), crushedPurifiedTiny.mat(tSluiceProducts[7], 9, dustTiny.mat(tSluiceProducts[7], 9)));
				RM.Sluice.addRecipe1(T, 16, 144, tSluice, tCrushed     , FL.SpDew.make(900), FL.Sluice.make(900), crushedPurified    .mat(aMat, 1, tDust), crushedPurifiedTiny.mat(tSluiceProducts[0], 9, dustTiny.mat(tSluiceProducts[0], 9)), crushedPurifiedTiny.mat(tSluiceProducts[1], 9, dustTiny.mat(tSluiceProducts[1], 9)), crushedPurifiedTiny.mat(tSluiceProducts[2], 9, dustTiny.mat(tSluiceProducts[2], 9)), crushedPurifiedTiny.mat(tSluiceProducts[3], 9, dustTiny.mat(tSluiceProducts[3], 9)), crushedPurifiedTiny.mat(tSluiceProducts[4], 9, dustTiny.mat(tSluiceProducts[4], 9)), crushedPurifiedTiny.mat(tSluiceProducts[5], 9, dustTiny.mat(tSluiceProducts[5], 9)), crushedPurifiedTiny.mat(tSluiceProducts[6], 9, dustTiny.mat(tSluiceProducts[6], 9)), crushedPurifiedTiny.mat(tSluiceProducts[7], 9, dustTiny.mat(tSluiceProducts[7], 9)));
				RM.Sluice.addRecipe1(T, 16, 144, tSluice, tCrushed     , FL.DistW.make(900), FL.Sluice.make(900), crushedPurified    .mat(aMat, 1, tDust), crushedPurifiedTiny.mat(tSluiceProducts[0], 9, dustTiny.mat(tSluiceProducts[0], 9)), crushedPurifiedTiny.mat(tSluiceProducts[1], 9, dustTiny.mat(tSluiceProducts[1], 9)), crushedPurifiedTiny.mat(tSluiceProducts[2], 9, dustTiny.mat(tSluiceProducts[2], 9)), crushedPurifiedTiny.mat(tSluiceProducts[3], 9, dustTiny.mat(tSluiceProducts[3], 9)), crushedPurifiedTiny.mat(tSluiceProducts[4], 9, dustTiny.mat(tSluiceProducts[4], 9)), crushedPurifiedTiny.mat(tSluiceProducts[5], 9, dustTiny.mat(tSluiceProducts[5], 9)), crushedPurifiedTiny.mat(tSluiceProducts[6], 9, dustTiny.mat(tSluiceProducts[6], 9)), crushedPurifiedTiny.mat(tSluiceProducts[7], 9, dustTiny.mat(tSluiceProducts[7], 9)));
				RM.Sluice.addRecipe1(T, 16,  16, tSluice, tCrushedTiny , FL.Water.make(100), FL.Sluice.make(100), crushedPurifiedTiny.mat(aMat, 1, tDust), crushedPurifiedTiny.mat(tSluiceProducts[0], 1, dustTiny.mat(tSluiceProducts[0], 1)), crushedPurifiedTiny.mat(tSluiceProducts[1], 1, dustTiny.mat(tSluiceProducts[1], 1)), crushedPurifiedTiny.mat(tSluiceProducts[2], 1, dustTiny.mat(tSluiceProducts[2], 1)), crushedPurifiedTiny.mat(tSluiceProducts[3], 1, dustTiny.mat(tSluiceProducts[3], 1)), crushedPurifiedTiny.mat(tSluiceProducts[4], 1, dustTiny.mat(tSluiceProducts[4], 1)), crushedPurifiedTiny.mat(tSluiceProducts[5], 1, dustTiny.mat(tSluiceProducts[5], 1)), crushedPurifiedTiny.mat(tSluiceProducts[6], 1, dustTiny.mat(tSluiceProducts[6], 1)), crushedPurifiedTiny.mat(tSluiceProducts[7], 1, dustTiny.mat(tSluiceProducts[7], 1)));
				RM.Sluice.addRecipe1(T, 16,  16, tSluice, tCrushedTiny , FL.SpDew.make(100), FL.Sluice.make(100), crushedPurifiedTiny.mat(aMat, 1, tDust), crushedPurifiedTiny.mat(tSluiceProducts[0], 1, dustTiny.mat(tSluiceProducts[0], 1)), crushedPurifiedTiny.mat(tSluiceProducts[1], 1, dustTiny.mat(tSluiceProducts[1], 1)), crushedPurifiedTiny.mat(tSluiceProducts[2], 1, dustTiny.mat(tSluiceProducts[2], 1)), crushedPurifiedTiny.mat(tSluiceProducts[3], 1, dustTiny.mat(tSluiceProducts[3], 1)), crushedPurifiedTiny.mat(tSluiceProducts[4], 1, dustTiny.mat(tSluiceProducts[4], 1)), crushedPurifiedTiny.mat(tSluiceProducts[5], 1, dustTiny.mat(tSluiceProducts[5], 1)), crushedPurifiedTiny.mat(tSluiceProducts[6], 1, dustTiny.mat(tSluiceProducts[6], 1)), crushedPurifiedTiny.mat(tSluiceProducts[7], 1, dustTiny.mat(tSluiceProducts[7], 1)));
				RM.Sluice.addRecipe1(T, 16,  16, tSluice, tCrushedTiny , FL.DistW.make(100), FL.Sluice.make(100), crushedPurifiedTiny.mat(aMat, 1, tDust), crushedPurifiedTiny.mat(tSluiceProducts[0], 1, dustTiny.mat(tSluiceProducts[0], 1)), crushedPurifiedTiny.mat(tSluiceProducts[1], 1, dustTiny.mat(tSluiceProducts[1], 1)), crushedPurifiedTiny.mat(tSluiceProducts[2], 1, dustTiny.mat(tSluiceProducts[2], 1)), crushedPurifiedTiny.mat(tSluiceProducts[3], 1, dustTiny.mat(tSluiceProducts[3], 1)), crushedPurifiedTiny.mat(tSluiceProducts[4], 1, dustTiny.mat(tSluiceProducts[4], 1)), crushedPurifiedTiny.mat(tSluiceProducts[5], 1, dustTiny.mat(tSluiceProducts[5], 1)), crushedPurifiedTiny.mat(tSluiceProducts[6], 1, dustTiny.mat(tSluiceProducts[6], 1)), crushedPurifiedTiny.mat(tSluiceProducts[7], 1, dustTiny.mat(tSluiceProducts[7], 1)));
				
				if (tByProductMercury != null) {
				RM.Bath.addRecipe1(T, 0, 144, tCrushed    , MT.Hg.liquid(U , T), NF, crushedPurified    .mat(aMat, 1), ST.mul_(2, tByProductMercury), ST.mul_(2, tByProductMercury), ST.mul_(2, tByProductMercury), ST.mul_(2, tByProductMercury), tByProductMercury);
				RM.Bath.addRecipe1(T, 0,  16, tCrushedTiny, MT.Hg.liquid(U9, T), NF, crushedPurifiedTiny.mat(aMat, 1), tByProductMercury);
				}
				if (tByproductPersulfate != null) for (OreDictMaterial tPersulfate : new OreDictMaterial[] {MT.NaSO4, MT.KSO4}) {
				RM.Bath.addRecipe2(T, 0, 144, tCrushed                 , OP.dust    .mat(tPersulfate, 1), FL.Water.make(900), NF, crushedPurified.mat(aMat, 1), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), tByproductPersulfate);
				RM.Bath.addRecipe2(T, 0, 144, tCrushed                 , OP.dust    .mat(tPersulfate, 1), FL.SpDew.make(900), NF, crushedPurified.mat(aMat, 1), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), tByproductPersulfate);
				RM.Bath.addRecipe2(T, 0, 144, tCrushed                 , OP.dust    .mat(tPersulfate, 1), FL.DistW.make(900), NF, crushedPurified.mat(aMat, 1), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), tByproductPersulfate);
				RM.Bath.addRecipe2(T, 0, 144, ST.amount(9,tCrushedTiny), OP.dust    .mat(tPersulfate, 1), FL.Water.make(900), NF, crushedPurified.mat(aMat, 1), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), tByproductPersulfate);
				RM.Bath.addRecipe2(T, 0, 144, ST.amount(9,tCrushedTiny), OP.dust    .mat(tPersulfate, 1), FL.SpDew.make(900), NF, crushedPurified.mat(aMat, 1), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), tByproductPersulfate);
				RM.Bath.addRecipe2(T, 0, 144, ST.amount(9,tCrushedTiny), OP.dust    .mat(tPersulfate, 1), FL.DistW.make(900), NF, crushedPurified.mat(aMat, 1), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), tByproductPersulfate);
				RM.Bath.addRecipe2(T, 0, 144, tCrushed                 , OP.dustTiny.mat(tPersulfate, 9), FL.Water.make(900), NF, crushedPurified.mat(aMat, 1), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), tByproductPersulfate);
				RM.Bath.addRecipe2(T, 0, 144, tCrushed                 , OP.dustTiny.mat(tPersulfate, 9), FL.SpDew.make(900), NF, crushedPurified.mat(aMat, 1), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), tByproductPersulfate);
				RM.Bath.addRecipe2(T, 0, 144, tCrushed                 , OP.dustTiny.mat(tPersulfate, 9), FL.DistW.make(900), NF, crushedPurified.mat(aMat, 1), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), ST.mul_(2, tByproductPersulfate), tByproductPersulfate);
				RM.Bath.addRecipe2(T, 0,  16, tCrushedTiny             , OP.dustTiny.mat(tPersulfate, 1), FL.Water.make(100), NF, crushedPurifiedTiny.mat(aMat, 1), tByproductPersulfate);
				RM.Bath.addRecipe2(T, 0,  16, tCrushedTiny             , OP.dustTiny.mat(tPersulfate, 1), FL.SpDew.make(100), NF, crushedPurifiedTiny.mat(aMat, 1), tByproductPersulfate);
				RM.Bath.addRecipe2(T, 0,  16, tCrushedTiny             , OP.dustTiny.mat(tPersulfate, 1), FL.DistW.make(100), NF, crushedPurifiedTiny.mat(aMat, 1), tByproductPersulfate);
				}
			}
			
			
			ItemStack tPurified = crushedPurified.mat(aMat, 1), tPurifiedTiny = crushedPurifiedTiny.mat(aMat, 1);
			if (ST.valid(tPurified) && ST.valid(tPurifiedTiny)) {
				CR.shapeless(gemFlawed.mat(aMat, 1), CR.DEF_NAC_NCC | CR.ONLY_IF_HAS_RESULT, new Object[] {crushedPurified.dat(aMat)});
				
				if (ENABLE_ADDING_IC2_CENTRIFUGE_RECIPES) {
				RM.ic2_centrifuge(tPurified                     , (int)Math.min(5000, Math.abs(aMat.getMass() * 20))    , crushedCentrifuged    .mat(aMat, 1), crushedCentrifugedTiny   .mat(UT.Code.select(1, aMat, aMat.mByProducts), 1));
				RM.ic2_centrifuge(ST.amount(9,tPurifiedTiny)    , (int)Math.min(5000, Math.abs(aMat.getMass() * 20))    , crushedCentrifuged    .mat(aMat, 1), crushedCentrifugedTiny   .mat(UT.Code.select(1, aMat, aMat.mByProducts), 1));
				}
				if (!tMagnetList.isEmpty()) {
				RM.MagneticSeparator.addRecipe1(T, 16, 144, tMagnet, tPurified    , crushedCentrifuged    .mat(aMat, 1), crushedCentrifugedTiny.mat(tMagnetList.get(0),18, dustTiny.mat(tMagnetList.get(0),18)), tMagnetList.size()<=1?null:crushedCentrifugedTiny.mat(tMagnetList.get(1),18, dustTiny.mat(tMagnetList.get(1),18)), tMagnetList.size()<=2?null:crushedCentrifugedTiny.mat(tMagnetList.get(2),18, dustTiny.mat(tMagnetList.get(2),18)), tMagnetList.size()<=3?null:crushedCentrifugedTiny.mat(tMagnetList.get(3),18, dustTiny.mat(tMagnetList.get(3),18)), tMagnetList.size()<=4?null:crushedCentrifugedTiny.mat(tMagnetList.get(4),18, dustTiny.mat(tMagnetList.get(4),18)), tMagnetList.size()<=5?null:crushedCentrifugedTiny.mat(tMagnetList.get(5),18, dustTiny.mat(tMagnetList.get(5),18)));
				RM.MagneticSeparator.addRecipe1(T, 16,  16, tMagnet, tPurifiedTiny, crushedCentrifugedTiny.mat(aMat, 1), crushedCentrifugedTiny.mat(tMagnetList.get(0), 2, dustTiny.mat(tMagnetList.get(0), 2)), tMagnetList.size()<=1?null:crushedCentrifugedTiny.mat(tMagnetList.get(1), 2, dustTiny.mat(tMagnetList.get(1), 2)), tMagnetList.size()<=2?null:crushedCentrifugedTiny.mat(tMagnetList.get(2), 2, dustTiny.mat(tMagnetList.get(2), 2)), tMagnetList.size()<=3?null:crushedCentrifugedTiny.mat(tMagnetList.get(3), 2, dustTiny.mat(tMagnetList.get(3), 2)), tMagnetList.size()<=4?null:crushedCentrifugedTiny.mat(tMagnetList.get(4), 2, dustTiny.mat(tMagnetList.get(4), 2)), tMagnetList.size()<=5?null:crushedCentrifugedTiny.mat(tMagnetList.get(5), 2, dustTiny.mat(tMagnetList.get(5), 2)));
				}
				ItemStack tGem = gem.mat(aMat, 1);
				if (tGem != null) {
				RM.Sifting.addRecipe1(T, 16, 128, new long[] {1, 100, 400, 1500, 2000, 4000, 5000}, tPurified                   , gemLegendary.mat(aMat, tGem, 1), gemExquisite.mat(aMat, tGem, 1), gemFlawless.mat(aMat, tGem, 1), tGem, gemFlawed.mat(aMat, tGem, 1), gemChipped.mat(aMat, tGem, 1), tDust);
				RM.Sifting.addRecipe1(T, 16, 128, new long[] {1, 100, 400, 1500, 2000, 4000, 5000}, ST.amount(9,tPurifiedTiny)  , gemLegendary.mat(aMat, tGem, 1), gemExquisite.mat(aMat, tGem, 1), gemFlawless.mat(aMat, tGem, 1), tGem, gemFlawed.mat(aMat, tGem, 1), gemChipped.mat(aMat, tGem, 1), tDust);
				}
				
				OreDictMaterial tMat1 = UT.Code.select(0, aMat, aMat.mByProducts), tMat2 = UT.Code.select(1, aMat, aMat.mByProducts), tMat3 = UT.Code.select(2, aMat, aMat.mByProducts);
				FluidStack tFluid1 = tMat1.fluid(DEF_ENV_TEMP, U9, F), tFluid2 = tMat2.fluid(DEF_ENV_TEMP, U9, F), tFluid3 = tMat3.fluid(DEF_ENV_TEMP, U9, F);
				if (FL.Error.is(tFluid1)) tFluid1 = null;
				if (FL.Error.is(tFluid2)) tFluid2 = null;
				if (FL.Error.is(tFluid3)) tFluid3 = null;
				RM.Centrifuge   .addRecipe1(T, 16, 144 + 144 * aMat.mToolQuality, tPurified                     , ZL_FS, FL.array(tFluid1, tFluid2, tFluid3), dust    .mat(aMat, 1), tFluid1==null?crushedCentrifugedTiny.mat(tMat1, 1, dustTiny.mat(tMat1, 1)):null, tFluid2==null?crushedCentrifugedTiny.mat(tMat2, 1, dustTiny.mat(tMat2, 1)):null, tFluid3==null?crushedCentrifugedTiny.mat(tMat3, 1, dustTiny.mat(tMat3, 1)):null);
				RM.Centrifuge   .addRecipe1(T, 16, 144 + 144 * aMat.mToolQuality, ST.amount(9,tPurifiedTiny)    , ZL_FS, FL.array(tFluid1, tFluid2, tFluid3), dustTiny.mat(aMat, 9), tFluid1==null?crushedCentrifugedTiny.mat(tMat1, 1, dustTiny.mat(tMat1, 1)):null, tFluid2==null?crushedCentrifugedTiny.mat(tMat2, 1, dustTiny.mat(tMat2, 1)):null, tFluid3==null?crushedCentrifugedTiny.mat(tMat3, 1, dustTiny.mat(tMat3, 1)):null);
			}
			
			
			ItemStack tImpure = dustImpure.mat(aMat, 1);
			if (ST.valid(tImpure)) {
				OreDictMaterial tMaterial = UT.Code.select(0, aMat, aMat.mByProducts);
				FluidStack tFluid = tMaterial.fluid(DEF_ENV_TEMP, U9, F);
				if (FL.Error.is(tFluid)) tFluid = null;
				
				RM.Centrifuge   .addRecipe1(T, F, F, ST.isGT(tImpure), T, 16, 256 + 256 * aMat.mToolQuality            , tImpure, NF, tFluid, tDust, tFluid==null?OM.dust(tMaterial, U9):null);
				
				if (!tMagnetList.isEmpty())
				RM.MagneticSeparator.addRecipe1(T, 16, 144, tMagnet, tImpure, dust.mat(aMat, 1), dustTiny.mat(tMagnetList.get(0),18), tMagnetList.size()<=1?null:dustTiny.mat(tMagnetList.get(1),18), tMagnetList.size()<=2?null:dustTiny.mat(tMagnetList.get(2),18), tMagnetList.size()<=3?null:dustTiny.mat(tMagnetList.get(3),18), tMagnetList.size()<=4?null:dustTiny.mat(tMagnetList.get(4),18), tMagnetList.size()<=5?null:dustTiny.mat(tMagnetList.get(5),18));
				
				tMaterial = UT.Code.select(1, aMat, aMat.mByProducts);
				tFluid = tMaterial.fluid(DEF_ENV_TEMP, U9, F);
				if (FL.Error.is(tFluid)) tFluid = null;
				
				RM.Electrolyzer .addRecipe2(T, F, F, ST.isGT(tImpure), T, 16, 256 + 256 * aMat.mToolQuality, ST.tag(0) , tImpure, NF, tFluid, tDust, tFluid==null?OM.dust(tMaterial, U9):null);
			}
			
			
			ItemStack tPure = dustPure.mat(aMat, 1);
			if (ST.valid(tPure)) {
				OreDictMaterial tMaterial = UT.Code.select(1, aMat, aMat.mByProducts);
				FluidStack tFluid = tMaterial.fluid(DEF_ENV_TEMP, U9, F);
				if (FL.Error.is(tFluid)) tFluid = null;
				
				RM.Centrifuge   .addRecipe1(T, F, F, ST.isGT(tPure), T, 16, 256 + 256 * aMat.mToolQuality            , tPure, NF, tFluid, tDust, tFluid==null?OM.dust(tMaterial, U9):null);
				
				if (!tMagnetList.isEmpty())
				RM.MagneticSeparator.addRecipe1(T, 16, 144, tMagnet, tPure, dust.mat(aMat, 1), dustTiny.mat(tMagnetList.get(0),18), tMagnetList.size()<=1?null:dustTiny.mat(tMagnetList.get(1),18), tMagnetList.size()<=2?null:dustTiny.mat(tMagnetList.get(2),18), tMagnetList.size()<=3?null:dustTiny.mat(tMagnetList.get(3),18), tMagnetList.size()<=4?null:dustTiny.mat(tMagnetList.get(4),18), tMagnetList.size()<=5?null:dustTiny.mat(tMagnetList.get(5),18));
				
				tMaterial = UT.Code.select(2, aMat, aMat.mByProducts);
				tFluid = tMaterial.fluid(DEF_ENV_TEMP, U9, F);
				if (FL.Error.is(tFluid)) tFluid = null;
				
				RM.Electrolyzer .addRecipe2(T, F, F, ST.isGT(tPure), T, 16, 256 + 256 * aMat.mToolQuality, ST.tag(0) , tPure, NF, tFluid, tDust, tFluid==null?OM.dust(tMaterial, U9):null);
			}
			
			
			ItemStack tRefined = dustRefined.mat(aMat, 1);
			if (ST.valid(tRefined)) {
				OreDictMaterial tMaterial = UT.Code.select(2, aMat, aMat.mByProducts);
				FluidStack tFluid = tMaterial.fluid(DEF_ENV_TEMP, U9, F);
				if (FL.Error.is(tFluid)) tFluid = null;
				
				RM.Centrifuge   .addRecipe1(T, F, F, ST.isGT(tRefined), T, 16, 256 + 256 * aMat.mToolQuality            , tRefined, NF, tFluid, tDust, tFluid==null?OM.dust(tMaterial, U9):null);
				
				if (!tMagnetList.isEmpty())
				RM.MagneticSeparator.addRecipe1(T, 16, 144, tMagnet, tRefined, dust.mat(aMat, 1), dustTiny.mat(tMagnetList.get(0),18), tMagnetList.size()<=1?null:dustTiny.mat(tMagnetList.get(1),18), tMagnetList.size()<=2?null:dustTiny.mat(tMagnetList.get(2),18), tMagnetList.size()<=3?null:dustTiny.mat(tMagnetList.get(3),18), tMagnetList.size()<=4?null:dustTiny.mat(tMagnetList.get(4),18), tMagnetList.size()<=5?null:dustTiny.mat(tMagnetList.get(5),18));
				
				tMaterial = UT.Code.select(3, aMat, aMat.mByProducts);
				tFluid = tMaterial.fluid(DEF_ENV_TEMP, U9, F);
				if (FL.Error.is(tFluid)) tFluid = null;
				
				RM.Electrolyzer .addRecipe2(T, F, F, ST.isGT(tRefined), T, 16, 256 + 256 * aMat.mToolQuality, ST.tag(0) , tRefined, NF, tFluid, tDust, tFluid==null?OM.dust(tMaterial, U9):null);
			}
		}
	}
}
