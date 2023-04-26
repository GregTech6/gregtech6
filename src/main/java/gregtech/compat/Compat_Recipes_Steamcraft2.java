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
import gregapi.code.ArrayListNoNulls;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.*;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import static gregapi.data.CS.*;

public class Compat_Recipes_Steamcraft2 extends CompatMods {
	public Compat_Recipes_Steamcraft2(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Steamcraft 2 Recipes.");
		CR.delate(MD.SC2, "BlockSlate", "BlockLightSlate", "BlockRawBlackSlateStairs", "BlockRawBlueSlateStairs", "BlockRawGreySlateStairs", "BlockRawLightBlueSlateStairs", "BlockRawLightRedSlateStairs", "BlockRawRedSlateStairs", "BlockBrickBlackSlateStairs", "BlockBrickBlueSlateStairs", "BlockBrickGreySlateStairs", "BlockBrickLightBlueSlateStairs", "BlockBrickLightRedSlateStairs", "BlockBrickRedSlateStairs", "BlockCobbleBlackSlateStairs", "BlockCobbleBlueSlateStairs", "BlockCobbleGreySlateStairs", "BlockCobbleLightBlueSlateStairs", "BlockCobbleLightRedSlateStairs", "BlockCobbleRedSlateStairs");
		CR.delate(ST.make(MD.SC2, "ItemTeapot"  , 1, 1));
		CR.delate(ST.make(MD.SC2, "ItemResource", 1, 2));
		if (MD.FZ.mLoaded) {
			RM.generify(ST.make(MD.FZ , "acid"        , 1, 0), ST.make(MD.SC2, "ItemResource", 1, 2));
			RM.generify(ST.make(MD.SC2, "ItemResource", 1, 2), ST.make(MD.FZ , "acid"        , 1, 0));
			CR.shaped(ST.make(MD.SC2, "ItemResource", 1, 2), CR.DEF_NAC, "  ", " X", 'X', ST.make(MD.FZ , "acid"        , 1, 0));
			CR.shaped(ST.make(MD.FZ , "acid"        , 1, 0), CR.DEF_NAC, "  ", " X", 'X', ST.make(MD.SC2, "ItemResource", 1, 2));
		} else {
			RM.Canner.addRecipe1(T, 16, 16, IL.Bottle_Empty.get(1), MT.H2SO4.fluid(U, T), NF, ST.make(MD.SC2, "ItemResource", 1, 2));
		}
		
		CR.shaped(ST.make(MD.SC2, "ItemMachinePart"     , 1, 8), CR.DEF_REM_REV, "CRC", 'C', OP.plate.dat(ANY.Cu), 'R', OP.plate.dat(ANY.Rubber));
		CR.shaped(ST.make(MD.SC2, "ItemElectricJarSmall", 1, 0), CR.DEF_REM_REV, "RCR", "GCG", "GGG", 'C', OP.stickLong.dat(ANY.Cu), 'R', OP.plate.dat(ANY.Rubber), 'G', OP.plate.dat(MT.Glass));
		
		RM.add_smelting(ST.make(MD.SC2, "BlockSlate"     , 1, 3), ST.make(MD.SC2, "BlockSlate"     , 1, 0));
		RM.add_smelting(ST.make(MD.SC2, "BlockSlate"     , 1, 4), ST.make(MD.SC2, "BlockSlate"     , 1, 1));
		RM.add_smelting(ST.make(MD.SC2, "BlockSlate"     , 1, 5), ST.make(MD.SC2, "BlockSlate"     , 1, 2));
		RM.add_smelting(ST.make(MD.SC2, "BlockLightSlate", 1, 3), ST.make(MD.SC2, "BlockLightSlate", 1, 0));
		RM.add_smelting(ST.make(MD.SC2, "BlockLightSlate", 1, 4), ST.make(MD.SC2, "BlockLightSlate", 1, 1));
		RM.add_smelting(ST.make(MD.SC2, "BlockLightSlate", 1, 5), ST.make(MD.SC2, "BlockLightSlate", 1, 2));
		
		CR.shaped(ST.make(MD.SC2, "BlockSlate"                     , 4, 6), CR.DEF    , "SS", "SS", 'S', ST.make(MD.SC2, "BlockSlate"     , 1, 0));
		CR.shaped(ST.make(MD.SC2, "BlockSlate"                     , 4, 7), CR.DEF    , "SS", "SS", 'S', ST.make(MD.SC2, "BlockSlate"     , 1, 1));
		CR.shaped(ST.make(MD.SC2, "BlockSlate"                     , 4, 8), CR.DEF    , "SS", "SS", 'S', ST.make(MD.SC2, "BlockSlate"     , 1, 2));
		CR.shaped(ST.make(MD.SC2, "BlockLightSlate"                , 4, 6), CR.DEF    , "SS", "SS", 'S', ST.make(MD.SC2, "BlockLightSlate", 1, 0));
		CR.shaped(ST.make(MD.SC2, "BlockLightSlate"                , 4, 7), CR.DEF    , "SS", "SS", 'S', ST.make(MD.SC2, "BlockLightSlate", 1, 1));
		CR.shaped(ST.make(MD.SC2, "BlockLightSlate"                , 4, 8), CR.DEF    , "SS", "SS", 'S', ST.make(MD.SC2, "BlockLightSlate", 1, 2));
		
		CR.shaped(ST.make(MD.SC2, "BlockRawBlueSlateStairs"        , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockSlate"     , 1, 0));
		CR.shaped(ST.make(MD.SC2, "BlockRawBlackSlateStairs"       , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockSlate"     , 1, 1));
		CR.shaped(ST.make(MD.SC2, "BlockRawRedSlateStairs"         , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockSlate"     , 1, 2));
		CR.shaped(ST.make(MD.SC2, "BlockCobbleBlueSlateStairs"     , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockSlate"     , 1, 3));
		CR.shaped(ST.make(MD.SC2, "BlockCobbleBlackSlateStairs"    , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockSlate"     , 1, 4));
		CR.shaped(ST.make(MD.SC2, "BlockCobbleRedSlateStairs"      , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockSlate"     , 1, 5));
		CR.shaped(ST.make(MD.SC2, "BlockBrickBlueSlateStairs"      , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockSlate"     , 1, 6));
		CR.shaped(ST.make(MD.SC2, "BlockBrickBlackSlateStairs"     , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockSlate"     , 1, 7));
		CR.shaped(ST.make(MD.SC2, "BlockBrickRedSlateStairs"       , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockSlate"     , 1, 8));
		CR.shaped(ST.make(MD.SC2, "BlockRawLightBlueSlateStairs"   , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockLightSlate", 1, 0));
		CR.shaped(ST.make(MD.SC2, "BlockRawGreySlateStairs"        , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockLightSlate", 1, 1));
		CR.shaped(ST.make(MD.SC2, "BlockRawLightRedSlateStairs"    , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockLightSlate", 1, 2));
		CR.shaped(ST.make(MD.SC2, "BlockCobbleLightBlueSlateStairs", 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockLightSlate", 1, 3));
		CR.shaped(ST.make(MD.SC2, "BlockCobbleGreySlateStairs"     , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockLightSlate", 1, 4));
		CR.shaped(ST.make(MD.SC2, "BlockCobbleLightRedSlateStairs" , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockLightSlate", 1, 5));
		CR.shaped(ST.make(MD.SC2, "BlockBrickLightBlueSlateStairs" , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockLightSlate", 1, 6));
		CR.shaped(ST.make(MD.SC2, "BlockBrickGreySlateStairs"      , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockLightSlate", 1, 7));
		CR.shaped(ST.make(MD.SC2, "BlockBrickLightRedSlateStairs"  , 4, 0), CR.DEF_MIR, " S", "SS", 'S', ST.make(MD.SC2, "BlockLightSlate", 1, 8));
		
		RM.Smelter.addRecipe1(T, 16, 64, ST.make(MD.SC2, "ItemWhaleBlubber", 1, 0), NF, FL.Oil_Whale.make(1000), ZL_IS);
		
		RM.packunpack(ST.make(MD.SC2, "ItemSlimeRubber", 9, 0), ST.make(MD.SC2, "BlockCongealedSlime", 1, 0));
		
		if (IL.SC2_Hammer.exists()) {
			ArrayListNoNulls tRecipesToRemove = new ArrayListNoNulls();
			for (Object tRecipe : CR.list()) {
				if (tRecipe instanceof ShapelessOreRecipe) {
					for (Object tInput : ((ShapelessOreRecipe)tRecipe).getInput()) {
						if (tInput instanceof Iterable) {
							for (Object iInput : (Iterable)tInput) {
								if (iInput instanceof ItemStack) {
									if (IL.SC2_Hammer.equal(iInput, T, T)) {
										tRecipesToRemove.add(tRecipe);
										break;
									}
								}
							}
						} else if (tInput instanceof ItemStack) {
							if (IL.SC2_Hammer.equal(tInput, T, T)) {
								tRecipesToRemove.add(tRecipe);
								break;
							}
						}
					}
				}
			}
			CR.list().removeAll(tRecipesToRemove);
		}
	}
}
