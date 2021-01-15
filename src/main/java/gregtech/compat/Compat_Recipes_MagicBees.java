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
import gregapi.data.RM;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Items;

public class Compat_Recipes_MagicBees extends CompatMods {
	public Compat_Recipes_MagicBees(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Magic Bees Recipes.");
		RM.Centrifuge   .addRecipe1(T, 16,   64                                                , IL.FRMB_Propolis_Unstable .get(1), ZL_FS, FL.array(FL.Latex.make(L  ), FL.Glue.make( 250)), ZL_IS);
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 9000}                            , IL.FRMB_Propolis_Breezey  .get(1), ZL_FS, FL.array(FL.Latex.make(L  ), FL.Glue.make( 250)), OM.dust(MT.InfusedAir, U3));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 9000}                            , IL.FRMB_Propolis_Burning  .get(1), ZL_FS, FL.array(FL.Latex.make(L  ), FL.Glue.make( 250)), OM.dust(MT.InfusedFire, U3));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 9000}                            , IL.FRMB_Propolis_Flowing  .get(1), ZL_FS, FL.array(FL.Latex.make(L  ), FL.Glue.make( 250)), OM.dust(MT.InfusedWater, U3));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 9000}                            , IL.FRMB_Propolis_Stony    .get(1), ZL_FS, FL.array(FL.Latex.make(L  ), FL.Glue.make( 250)), OM.dust(MT.InfusedEarth, U3));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 9000}                            , IL.FRMB_Propolis_Ordered  .get(1), ZL_FS, FL.array(FL.Latex.make(L  ), FL.Glue.make( 250)), OM.dust(MT.InfusedOrder, U3));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] { 9000}                            , IL.FRMB_Propolis_Chaotic  .get(1), ZL_FS, FL.array(FL.Latex.make(L  ), FL.Glue.make( 250)), OM.dust(MT.InfusedEntropy, U3));
		
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000, 10000}                     , ST.make(MD.FRMB, "comb", 1, 0), NF, FL.Honey   .make( 60), OM.dust(MT.WaxBee, 8*U9), OM.dust(MT.WaxMagic, U9));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000, 10000}                     , ST.make(MD.FRMB, "comb", 1, 1), NF, FL.Honey   .make( 10), OM.dust(MT.WaxRefractory, 8*U9));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000}                            , ST.make(MD.FRMB, "comb", 1, 2), NF, FL.Honey   .make( 60), OM.dust(MT.WaxMagic));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000, 10000}                     , ST.make(MD.FRMB, "comb", 1, 3), NF, FL.Honey   .make(100), OM.dust(MT.WaxBee, U2), OM.dust(MT.WaxMagic, U4));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000, 10000,  1500}              , ST.make(MD.FRMB, "comb", 1, 4), NF, NF, OM.dust(MT.WaxMagic, 3*U4), OM.dust(MT.WaxBee, 3*U4), IL.FRMB_Propolis_Unstable.get(1));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000, 10000,  1000}              , ST.make(MD.FRMB, "comb", 1, 5), NF, NF, OM.dust(MT.WaxBee, 3*U4), OM.dust(MT.WaxMagic, U4), ST.make(Items.paper, 1, 0));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000, 10000}                     , ST.make(MD.FRMB, "comb", 1, 6), NF, FL.Honeydew.make( 25), OM.dust(MT.WaxSoulful));
//      RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000, 10000}                     , ST.make(MD.FRMB, "comb", 1, 7), NF, NF);
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  1200}                     , ST.make(MD.FRMB, "comb", 1, 8), NF, FL.Honeydew.make( 40), OM.dust(MT.WaxMagic), ST.make(MD.FRMB, "drop", 1, 1));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,   600}                     , ST.make(MD.FRMB, "comb", 1, 9), NF, FL.Honeydew.make( 60), OM.dust(MT.WaxMagic), ST.make(MD.FRMB, "pollen", 1, 1));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  5000}                     , ST.make(MD.FRMB, "comb", 1,10), NF, FL.Honey   .make( 40), OM.dust(MT.WaxAmnesic, U2), IL.FR_Propolis.get(1));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  6000}                     , ST.make(MD.FRMB, "comb", 1,11), NF, NF, OM.dust(MT.WaxMagic), ST.make(Items.feather, 1, 0));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  6000}                     , ST.make(MD.FRMB, "comb", 1,12), NF, NF, OM.dust(MT.WaxMagic), OM.dust(MT.Blaze, U9));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  6000}                     , ST.make(MD.FRMB, "comb", 1,13), NF, NF, OM.dust(MT.WaxMagic), IL.Dye_SquidInk.get(1));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  2000,2000,2000,2000,2000} , ST.make(MD.FRMB, "comb", 1,14), NF, NF, OM.dust(MT.WaxMagic), IL.Clay_Ball_Brown.get(1), IL.Clay_Ball_Red.get(1), IL.Clay_Ball_Yellow.get(1), IL.Clay_Ball_Blue.get(1), IL.Clay_Ball_White.get(1));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  8000,  6000}              , ST.make(MD.FRMB, "comb", 1,15), NF, NF, OM.dust(MT.WaxMagic), IL.FRMB_Propolis_Breezey.get(1), ST.make(Items.feather, 1, 0));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  8000,  6000}              , ST.make(MD.FRMB, "comb", 1,16), NF, NF, OM.dust(MT.WaxMagic), IL.FRMB_Propolis_Burning.get(1), OM.dust(MT.Blaze, U9));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  8000,  6000}              , ST.make(MD.FRMB, "comb", 1,17), NF, NF, OM.dust(MT.WaxMagic), IL.FRMB_Propolis_Flowing.get(1), IL.Dye_SquidInk.get(1));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  8000,  6000}              , ST.make(MD.FRMB, "comb", 1,18), NF, NF, OM.dust(MT.WaxMagic), IL.FRMB_Propolis_Stony  .get(1), ST.make(Items.clay_ball, 1, 0));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  8000,  6000}              , ST.make(MD.FRMB, "comb", 1,19), NF, NF, OM.dust(MT.WaxMagic), IL.FRMB_Propolis_Ordered.get(1), OM.dust(MT.Redstone));
		RM.Centrifuge   .addRecipe1(T, 16,   64, new long[] {10000,  8000,  6000}              , ST.make(MD.FRMB, "comb", 1,20), NF, NF, OM.dust(MT.WaxMagic), IL.FRMB_Propolis_Chaotic.get(1), OM.dust(MT.Gunpowder));
	}
}
