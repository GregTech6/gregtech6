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
import gregapi.data.CS.*;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregapi.data.CS.*;

public class Compat_Recipes_TechGuns extends CompatMods {
	public Compat_Recipes_TechGuns(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing Tech Guns Recipes.");
		CR.delate(MD.TG, "TechgunsAmmo",  1);
		CR.delate(MD.TG, "TechgunsAmmo", 57);
		CR.delate(MD.TG, "TechgunsAmmo", 68);
		CR.delate(MD.TG, "TechgunsAmmo", 87);
		
		CR.delate(MD.TG, "goldenrevolver");
		
		RM.rem_smelting(ST.make(MD.TG, "TechgunsAmmo", 1, 71), ST.make(MD.TG, "TechgunsAmmo", 1, 72));
		
		
		CR.shapeless(ST.make(MD.TG, "TechgunsAmmo", 4, 0), CR.DEF_NCC, new Object[] {OP.dustSmall.dat(MT.Gunpowder), OP.rockGt.dat(ANY.Stone), OP.rockGt.dat(ANY.Stone), OP.rockGt.dat(ANY.Stone)});
		
		CR.shaped(ST.make(MD.TG, "TechgunsAmmo", 1, 57), CR.DEF_NCC, new Object[] {"X  ", "  x", 'X', OP.wireGt01.dat(ANY.Cu)});
		
		CR.shaped(ST.make(MD.TG, "TechgunsAmmo", 1,  1), CR.DEF_NCC, "  ", " X", 'X', OP.bulletGtSmall.dat(MT.Pb));
		CR.shaped(ST.make(MD.TG, "TechgunsAmmo", 1,  1), CR.DEF_NCC, "  ", " X", 'X', OP.bulletGtSmall.dat(MT.HSLA));
		CR.shaped(ST.make(MD.TG, "TechgunsAmmo", 1,  1), CR.DEF_NCC, "  ", " X", 'X', OP.bulletGtSmall.dat(ANY.Iron));
		CR.shaped(ST.make(MD.TG, "TechgunsAmmo", 1,  7), CR.DEF_NCC, "  ", " X", 'X', OP.bulletGtMedium.dat(MT.Pb));
		CR.shaped(ST.make(MD.TG, "TechgunsAmmo", 1,  7), CR.DEF_NCC, "  ", " X", 'X', OP.bulletGtMedium.dat(MT.HSLA));
		CR.shaped(ST.make(MD.TG, "TechgunsAmmo", 1,  7), CR.DEF_NCC, "  ", " X", 'X', OP.bulletGtMedium.dat(ANY.Iron));
		CR.shaped(ST.make(MD.TG, "TechgunsAmmo", 1, 19), CR.DEF_NCC, "  ", " X", 'X', OP.bulletGtLarge.dat(MT.Pb));
		CR.shaped(ST.make(MD.TG, "TechgunsAmmo", 1, 19), CR.DEF_NCC, "  ", " X", 'X', OP.bulletGtLarge.dat(MT.HSLA));
		CR.shaped(ST.make(MD.TG, "TechgunsAmmo", 1, 19), CR.DEF_NCC, "  ", " X", 'X', OP.bulletGtLarge.dat(ANY.Iron));
		CR.shaped(ST.make(MD.TG, "TechgunsAmmo", 1, 88), CR.DEF_NCC, "XX", "XX", 'X', OP.bulletGtMedium.dat(MT.Pb));
		CR.shaped(ST.make(MD.TG, "TechgunsAmmo", 1, 88), CR.DEF_NCC, "XX", "XX", 'X', OP.bulletGtMedium.dat(MT.HSLA));
		CR.shaped(ST.make(MD.TG, "TechgunsAmmo", 1, 88), CR.DEF_NCC, "XX", "XX", 'X', OP.bulletGtMedium.dat(ANY.Iron));
		
		RM.pack(ST.make(MD.TG, "TechgunsAmmo", 4, 7), ST.make(MD.TG, "TechgunsAmmo", 1, 88));
		RM.unpack(ST.make(MD.TG, "TechgunsAmmo", 1, 88), ST.make(MD.TG, "TechgunsAmmo", 4, 7));
		
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1, 18), ST.make(MD.TG, "TechgunsAmmo", 1, 17), OP.bulletGtLarge   .mat(MT.Pb, 2));
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1, 21), ST.make(MD.TG, "TechgunsAmmo", 1, 20), OP.bulletGtMedium  .mat(MT.Pb, 8));
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1, 13), ST.make(MD.TG, "TechgunsAmmo", 1, 12), OP.bulletGtMedium  .mat(MT.Pb, 3));
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1,  3), ST.make(MD.TG, "TechgunsAmmo", 1,  2), OP.bulletGtSmall   .mat(MT.Pb, 3));
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1,  6), ST.make(MD.TG, "TechgunsAmmo", 1,  5), OP.bulletGtSmall   .mat(MT.Pb, 2));
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1, 18), ST.make(MD.TG, "TechgunsAmmo", 1, 17), OP.bulletGtLarge   .mat(MT.HSLA, 2));
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1, 21), ST.make(MD.TG, "TechgunsAmmo", 1, 20), OP.bulletGtMedium  .mat(MT.HSLA, 8));
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1, 13), ST.make(MD.TG, "TechgunsAmmo", 1, 12), OP.bulletGtMedium  .mat(MT.HSLA, 3));
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1,  3), ST.make(MD.TG, "TechgunsAmmo", 1,  2), OP.bulletGtSmall   .mat(MT.HSLA, 3));
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1,  6), ST.make(MD.TG, "TechgunsAmmo", 1,  5), OP.bulletGtSmall   .mat(MT.HSLA, 2));
		for (OreDictMaterial tMat : ANY.Iron.mToThis) {
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1, 18), ST.make(MD.TG, "TechgunsAmmo", 1, 17), OP.bulletGtLarge   .mat(tMat, 2));
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1, 21), ST.make(MD.TG, "TechgunsAmmo", 1, 20), OP.bulletGtMedium  .mat(tMat, 8));
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1, 13), ST.make(MD.TG, "TechgunsAmmo", 1, 12), OP.bulletGtMedium  .mat(tMat, 3));
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1,  3), ST.make(MD.TG, "TechgunsAmmo", 1,  2), OP.bulletGtSmall   .mat(tMat, 3));
		RM.box(ST.make(MD.TG, "TechgunsAmmo", 1,  6), ST.make(MD.TG, "TechgunsAmmo", 1,  5), OP.bulletGtSmall   .mat(tMat, 2));
		}
		RM.boxunbox(ST.make(MD.TG, "TechgunsAmmo", 1, 24), ST.make(MD.TG, "TechgunsAmmo", 1, 23), ST.make(MD.TG, "TechgunsAmmo", 3, 22));
		RM.boxunbox(ST.make(MD.TG, "TechgunsAmmo", 1, 18), ST.make(MD.TG, "TechgunsAmmo", 1, 17), ST.make(MD.TG, "TechgunsAmmo", 2, 19));
		RM.boxunbox(ST.make(MD.TG, "TechgunsAmmo", 1, 21), ST.make(MD.TG, "TechgunsAmmo", 1, 20), ST.make(MD.TG, "TechgunsAmmo", 8,  7));
		RM.boxunbox(ST.make(MD.TG, "TechgunsAmmo", 1, 21), ST.make(MD.TG, "TechgunsAmmo", 1, 20), ST.make(MD.TG, "TechgunsAmmo", 2, 88));
		RM.boxunbox(ST.make(MD.TG, "TechgunsAmmo", 1, 13), ST.make(MD.TG, "TechgunsAmmo", 1, 12), ST.make(MD.TG, "TechgunsAmmo", 3,  7));
		RM.boxunbox(ST.make(MD.TG, "TechgunsAmmo", 1,  3), ST.make(MD.TG, "TechgunsAmmo", 1,  2), ST.make(MD.TG, "TechgunsAmmo", 3,  1));
		RM.boxunbox(ST.make(MD.TG, "TechgunsAmmo", 1,  6), ST.make(MD.TG, "TechgunsAmmo", 1,  5), ST.make(MD.TG, "TechgunsAmmo", 2,  1));
		
		ItemsGT.addNEIRedirects(ST.make(MD.TG, "TechgunsAmmo", 1, 24), ST.make(MD.TG, "TechgunsAmmo", 1, 23), ST.make(MD.TG, "TechgunsAmmo", 1, 22));
		ItemsGT.addNEIRedirects(ST.make(MD.TG, "TechgunsAmmo", 1, 18), ST.make(MD.TG, "TechgunsAmmo", 1, 17), ST.make(MD.TG, "TechgunsAmmo", 1, 19));
		ItemsGT.addNEIRedirects(ST.make(MD.TG, "TechgunsAmmo", 1, 13), ST.make(MD.TG, "TechgunsAmmo", 1, 12), ST.make(MD.TG, "TechgunsAmmo", 1, 21), ST.make(MD.TG, "TechgunsAmmo", 1, 20), ST.make(MD.TG, "TechgunsAmmo", 1,  7));
		ItemsGT.addNEIRedirects(ST.make(MD.TG, "TechgunsAmmo", 1,  6), ST.make(MD.TG, "TechgunsAmmo", 1,  5), ST.make(MD.TG, "TechgunsAmmo", 1,  3), ST.make(MD.TG, "TechgunsAmmo", 1,  2), ST.make(MD.TG, "TechgunsAmmo", 1,  1));
		
		RM.Lightning.addRecipe2(T, 16, 800, ST.tag(0), ST.make(MD.TG, "TechgunsAmmo", 1, 16), ST.make(MD.TG, "TechgunsAmmo", 1, 15));
		
		RM.Canner.addRecipe1(T, 16, 16, ST.make(MD.TG, "TechgunsAmmo", 1, 29), FL.Air           .make(16000), NF, ST.make(MD.TG, "TechgunsAmmo", 1, 28));
		RM.Canner.addRecipe1(T, 16, 16, ST.make(MD.TG, "TechgunsAmmo", 1, 29), FL.Air_Nether    .make(16000), NF, ST.make(MD.TG, "TechgunsAmmo", 1, 28));
		RM.Canner.addRecipe1(T, 16, 16, ST.make(MD.TG, "TechgunsAmmo", 1, 29), FL.Air_End       .make(16000), NF, ST.make(MD.TG, "TechgunsAmmo", 1, 28));
		
		RM.Loom.addRecipe2(T, 16, 16, ST.make(Blocks.wool, 4, W), ST.make(Items.leather, 1, W), ST.make(MD.TG, "TechgunsAmmo", 3, 39));
		
		RM.Bath.addRecipe1(T, 0, 128, ST.make(MD.TG, "revolver", 1, W), MT.Au.liquid(U*8, T), NF, ST.make(MD.TG, "goldenrevolver", 1, 0));
		
		RM.Press.addRecipe2(T, 16, 128, ST.make(MD.TG, "TechgunsAmmo", 1, 60), OP.casingSmall.mat(MT.ObsidianSteel, 2), ST.make(MD.TG, "TechgunsAmmo", 16, 22));
		
		for (OreDictMaterial tMat : ANY.Steel.mToThis) {ItemStack tStack = OP.plate.mat(tMat, 1); if (ST.valid(tStack)) {
		RM.Press.addRecipe2(T, 16, 256, tStack, OP.plate.mat(MT.Bronze, 1), ST.make(MD.TG, "TechgunsAmmo", 1, 67));
		}}
		RM.Press.addRecipe2(T, 16, 256, OP.plate.mat(MT.HSLA, 1), OP.plate.mat(MT.Bronze, 1), ST.make(MD.TG, "TechgunsAmmo", 1, 67));
		
		
		for (OreDictMaterial tMat : ANY.Iron.mToThis) {ItemStack tStack = OP.casingSmall.mat(tMat, 2); if (ST.valid(tStack)) {
		RM.Press.addRecipe2(T, 16, 128, tStack, ST.make(Items.flint, 1, W), ST.make(MD.TG, "TechgunsAmmo", 1, 30));
		RM.Press.addRecipe2(T, 16, 128, tStack, ST.make(Blocks.tnt, 1, W), ST.make(MD.TG, "TechgunsAmmo", 16, 93));
		}}
		RM.Press.addRecipe2(T, 16, 128, OP.casingSmall.mat(MT.HSLA, 2), ST.make(Items.flint, 1, W), ST.make(MD.TG, "TechgunsAmmo", 1, 30));
		RM.Press.addRecipe2(T, 16, 128, OP.casingSmall.mat(MT.HSLA, 2), ST.make(Blocks.tnt, 1, W), ST.make(MD.TG, "TechgunsAmmo", 16, 93));
		
		
		for (OreDictMaterial tMat : ANY.SiO2.mToThis) {ItemStack tStack = OP.gem.mat(tMat, 1); if (ST.valid(tStack)) {
		RM.Press.addRecipe2(T, 16, 128, OP.casingSmall.mat(MT.ObsidianSteel, 2), tStack, ST.make(MD.TG, "TechgunsAmmo", 1, 34));
		}}
		
		
		for (OreDictMaterial tMat : ANY.C.mToThis) {ItemStack tStack = OP.plate.mat(tMat, 1); if (ST.valid(tStack)) {
		RM.Press.addRecipe2(T, 16, 128, tStack, ST.make(Items.blaze_rod, 1, W), ST.make(MD.TG, "TechgunsAmmo", 2, 37));
		}}
	}
}
