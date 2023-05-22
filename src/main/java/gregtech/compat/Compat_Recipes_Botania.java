/**
 * Copyright (c) 2023 GregTech-6 Team
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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;

import static gregapi.data.CS.*;
import static gregapi.util.CR.DEF_REV;
import static gregapi.util.CR.DEL_OTHER_SHAPED_RECIPES;

public class Compat_Recipes_Botania extends CompatMods {
	public Compat_Recipes_Botania(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {
		OUT.println("GT_Mod: Doing Botania Recipes.");
		CR.delate(MD.BOTA, "stone", 0, 2, 3, 4, 6, 7, 8, 10, 11, 12, 14, 15);
		
		CR.shaped(ST.make(MD.BOTA, "openBucket", 1, 0), DEF_REV | DEL_OTHER_SHAPED_RECIPES, "XhX", " Y ", 'Y', OP.plate.dat(MT.ElvenElementium), 'X', OP.plateCurved.dat(MT.ElvenElementium));
		
		if (MD.ALF.mLoaded) {
		RM.smash(IL.ALF_Ice.get(1), OM.dust(MT.Ice, U));
		RM.Squeezer.addRecipe1(T, 16, 128, IL.ALF_Ice.get(1), NF, FL.Ice.make(1000), NI);
		RM.Juicer  .addRecipe1(T, 16, 128, IL.ALF_Ice.get(1), NF, FL.Ice.make(1000), NI);
		}
		
		RM.Squeezer.addRecipe1(T, 16, 16, ST.make(MD.BOTA, "mushroom", 1, W), NF, FL.make("mushroomsoup", 500), ZL_IS);
		RM.Juicer  .addRecipe1(T, 16, 16, ST.make(MD.BOTA, "mushroom", 1, W), NF, FL.make("mushroomsoup", 500), ZL_IS);
		for (int i = 0; i < 16; i++) {
		ItemStack tPetal = ST.make(MD.BOTA, "petal", 1, i), tDye = ST.make(MD.BOTA, "dye", 1, i);
		RM.Squeezer.addRecipe1(T, 16, 16, tPetal, NF, DYE_FLUIDS_FLOWER[15-i], tDye);
		RM.Juicer  .addRecipe1(T, 16, 16, tPetal, NF, DYE_FLUIDS_FLOWER[15-i], tDye);
		RM.ic2_extractor(tPetal, tDye);
		RM.mortarize(tPetal, tDye);
		}
		
		RM.packunpack(ST.make(Items.blaze_rod, 9, 0), ST.make(MD.BOTA, "blazeBlock", 1, 0));
		
		try {
		BotaniaAPI.registerManaInfusionRecipe(OP.ingot.mat(MT.Manasteel, 1), OP.ingot.dat(ANY.Fe   ).toString(), 3000);
		BotaniaAPI.registerManaInfusionRecipe(OP.ingot.mat(MT.Manasteel, 1), OP.ingot.dat(ANY.Steel).toString(), 1500);
		} catch(Throwable e) {e.printStackTrace(ERR);}
		
		RM.smash(ST.make(MD.BOTA, "quartzSlabManaHalf"    , 1, W), OP.gem.mat(MT.ManaQuartz, 2));
		RM.smash(ST.make(MD.BOTA, "quartzSlabManaFull"    , 1, W), OP.gem.mat(MT.ManaQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzTypeMana"        , 1, W), OP.gem.mat(MT.ManaQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzStairsMana"      , 1, W), OP.gem.mat(MT.ManaQuartz, 6));
		RM.smash(ST.make(MD.BOTA, "quartzSlabDarkHalf"    , 1, W), OP.gem.mat(MT.SmokeyQuartz, 2));
		RM.smash(ST.make(MD.BOTA, "quartzSlabDarkFull"    , 1, W), OP.gem.mat(MT.SmokeyQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzTypeDark"        , 1, W), OP.gem.mat(MT.SmokeyQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzStairsDark"      , 1, W), OP.gem.mat(MT.SmokeyQuartz, 6));
		RM.smash(ST.make(MD.BOTA, "quartzSlabBlazeHalf"   , 1, W), OP.gem.mat(MT.BlazeQuartz, 2));
		RM.smash(ST.make(MD.BOTA, "quartzSlabBlazeFull"   , 1, W), OP.gem.mat(MT.BlazeQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzTypeBlaze"       , 1, W), OP.gem.mat(MT.BlazeQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzStairsBlaze"     , 1, W), OP.gem.mat(MT.BlazeQuartz, 6));
		RM.smash(ST.make(MD.BOTA, "quartzSlabLavenderHalf", 1, W), OP.gem.mat(MT.LavenderQuartz, 2));
		RM.smash(ST.make(MD.BOTA, "quartzSlabLavenderFull", 1, W), OP.gem.mat(MT.LavenderQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzTypeLavender"    , 1, W), OP.gem.mat(MT.LavenderQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzStairsLavender"  , 1, W), OP.gem.mat(MT.LavenderQuartz, 6));
		RM.smash(ST.make(MD.BOTA, "quartzSlabRedHalf"     , 1, W), OP.gem.mat(MT.RedQuartz, 2));
		RM.smash(ST.make(MD.BOTA, "quartzSlabRedFull"     , 1, W), OP.gem.mat(MT.RedQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzTypeRed"         , 1, W), OP.gem.mat(MT.RedQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzStairsRed"       , 1, W), OP.gem.mat(MT.RedQuartz, 6));
		RM.smash(ST.make(MD.BOTA, "quartzSlabElfHalf"     , 1, W), OP.gem.mat(MT.ElvenQuartz, 2));
		RM.smash(ST.make(MD.BOTA, "quartzSlabElfFull"     , 1, W), OP.gem.mat(MT.ElvenQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzTypeElf"         , 1, W), OP.gem.mat(MT.ElvenQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzStairsElf"       , 1, W), OP.gem.mat(MT.ElvenQuartz, 6));
		RM.smash(ST.make(MD.BOTA, "quartzSlabSunnyHalf"   , 1, W), OP.gem.mat(MT.SunnyQuartz, 2));
		RM.smash(ST.make(MD.BOTA, "quartzSlabSunnyFull"   , 1, W), OP.gem.mat(MT.SunnyQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzTypeSunny"       , 1, W), OP.gem.mat(MT.SunnyQuartz, 4));
		RM.smash(ST.make(MD.BOTA, "quartzStairsSunny"     , 1, W), OP.gem.mat(MT.SunnyQuartz, 6));
		
		RM.Hammer.addRecipe1(T, 16, 48, 8000, ST.make(MD.BOTA, "livingrock"         , 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 4));
		RM.Hammer.addRecipe1(T, 16, 48, 8000, ST.make(MD.BOTA, "livingrock0SlabFull", 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 4));
		RM.Hammer.addRecipe1(T, 16, 48, 8000, ST.make(MD.BOTA, "livingrock1SlabFull", 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 4));
		RM.Hammer.addRecipe1(T, 16, 48, 8000, ST.make(MD.BOTA, "livingrock0Wall"    , 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 4));
		RM.Hammer.addRecipe1(T, 16, 48, 8000, ST.make(MD.BOTA, "livingrock0Stairs"  , 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 6));
		RM.Hammer.addRecipe1(T, 16, 48, 8000, ST.make(MD.BOTA, "livingrock1Stairs"  , 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 6));
		RM.Hammer.addRecipe1(T, 16, 48, 8000, ST.make(MD.BOTA, "livingrock0Slab"    , 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 2));
		RM.Hammer.addRecipe1(T, 16, 48, 8000, ST.make(MD.BOTA, "livingrock1Slab"    , 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 2));
		RM.Crusher.addRecipe1(T, 16, 48, ST.make(MD.BOTA, "livingrock"         , 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 4));
		RM.Crusher.addRecipe1(T, 16, 48, ST.make(MD.BOTA, "livingrock0SlabFull", 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 4));
		RM.Crusher.addRecipe1(T, 16, 48, ST.make(MD.BOTA, "livingrock1SlabFull", 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 4));
		RM.Crusher.addRecipe1(T, 16, 48, ST.make(MD.BOTA, "livingrock0Wall"    , 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 4));
		RM.Crusher.addRecipe1(T, 16, 48, ST.make(MD.BOTA, "livingrock0Stairs"  , 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 6));
		RM.Crusher.addRecipe1(T, 16, 48, ST.make(MD.BOTA, "livingrock1Stairs"  , 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 6));
		RM.Crusher.addRecipe1(T, 16, 48, ST.make(MD.BOTA, "livingrock0Slab"    , 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 2));
		RM.Crusher.addRecipe1(T, 16, 48, ST.make(MD.BOTA, "livingrock1Slab"    , 1, W), OP.rockGt.mat(MT.STONES.Livingrock, 2));
	}
}
