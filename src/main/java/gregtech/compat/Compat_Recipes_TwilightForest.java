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
import static gregapi.util.CR.*;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gregapi.api.Abstract_Mod;
import gregapi.code.ModData;
import gregapi.compat.CompatMods;
import gregapi.data.CS.ConfigsGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.RM;
import gregapi.util.CR;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import twilightforest.item.TFItems;

public class Compat_Recipes_TwilightForest extends CompatMods {
	public Compat_Recipes_TwilightForest(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing TF Recipes.");
		CR.shaped(IL.Stick.get(2), DEF, "s", "X", 'X', IL.TF_Dry_Bush);
		CR.shaped(IL.Stick.get(2), DEF, "k", "X", 'X', IL.TF_Dry_Bush);
		
		// Since the Cube gives all Progress related TF Achievements with GT6, it is not that bad that you use up the 5 Trophies in co-op Multiplayer. Oh and yes I know the Lamp of Cinders has itself as Container Item, that is intended as you just need to "have" it.
		CR.shapeless(IL.TF_Cube_of_Annihilation.get(1), new Object[] {IL.TF_Lamp_of_Cinders, ST.make(MD.TF, "tile.TFTowerDevice", 1, 12), IL.TF_Transformation_Powder, IL.TF_Giant_Obsidian, IL.TF_Trophy_Hydra, IL.TF_Trophy_Urghast, IL.TF_Trophy_Naga, IL.TF_Trophy_Lich, IL.TF_Trophy_Snowqueen});
		// Make it as resistant as Obsidian is, after GT6/IC2 nerfed it. This way Strong Dynamite can break it.
		IL.TF_Deadrock.block().setResistance(60);
		
		if (COMPAT_IC2 != null) {
			COMPAT_IC2.addToExplosionWhitelist(IL.TF_Deadrock.block());
			COMPAT_IC2.addToExplosionWhitelist(IL.TF_Giant_Obsidian.block());
		}
		
		ItemsGT.addNEIRedirects(ST.make(MD.TF, "item.charmOfKeeping1", 1, W), ST.make(MD.TF, "item.charmOfKeeping2", 1, W), ST.make(MD.TF, "item.charmOfKeeping3", 1, W));
		ItemsGT.addNEIRedirects(ST.make(MD.TF, "item.charmOfLife1"   , 1, W), ST.make(MD.TF, "item.charmOfLife2"   , 1, W));
		
		RM.pack  (ST.make(MD.TF, "item.charmOfKeeping1", 4, W), ST.make(MD.TF, "item.charmOfKeeping2", 1, 0));
		RM.pack  (ST.make(MD.TF, "item.charmOfKeeping1",16, W), ST.make(MD.TF, "item.charmOfKeeping3", 1, 0));
		RM.pack  (ST.make(MD.TF, "item.charmOfKeeping2", 4, W), ST.make(MD.TF, "item.charmOfKeeping3", 1, 0));
		RM.pack  (ST.make(MD.TF, "item.charmOfLife1"   , 4, W), ST.make(MD.TF, "item.charmOfLife2"   , 1, 0));
		RM.unpack(ST.make(MD.TF, "item.charmOfKeeping2", 1, W), ST.make(MD.TF, "item.charmOfKeeping1", 4, 0));
		RM.unpack(ST.make(MD.TF, "item.charmOfKeeping3", 1, W), ST.make(MD.TF, "item.charmOfKeeping2", 4, 0));
		RM.unpack(ST.make(MD.TF, "item.charmOfLife2"   , 1, W), ST.make(MD.TF, "item.charmOfLife1"   , 4, 0));
		
		RM.sawing(16, 16, F,  2, IL.TF_Roots    .get(1), IL.Stick.get(6));
		RM.sawing(16, 16, F,  6, IL.TF_Liveroots.get(1), IL.TF_LiveRoot.get(2), IL.Stick.get(2));
		
		CR.remove(IL.TF_Giant_Log     .get(1));
		CR.remove(IL.TF_Giant_Cobble  .get(1));
		CR.remove(IL.TF_Giant_Obsidian.get(1));
		CR.remove(IL.TF_Giant_Leaves  .get(1));
		
		RM.sawing(64, 64, F, 50, IL.TF_Giant_Log     .get(1), ST.make(Blocks.log, 64, 0));
		RM.sawing(64,256, F, 50, IL.TF_Giant_Cobble  .get(1), ST.make(Blocks.cobblestone, 64, 0));
		RM.sawing(64,512, F, 50, IL.TF_Giant_Obsidian.get(1), ST.make(Blocks.obsidian, 64, 0));
		RM.sawing(64, 16, F, 50, IL.TF_Giant_Leaves  .get(1), ST.make(Blocks.leaves, 64, 0));
		
		RM.Mixer.addRecipeX(T, 16, 64, ST.array(OM.dust(MT.Redstone, 4*U), IL.TF_BorerEssence.get(4), ST.make(Items.ghast_tear, 1, W)), IL.TF_Carminite.get(1));
		
		RM.Loom.addRecipe2(T, 16,  128, ST.tag(4), ST.make(MD.TF, "item.arcticFur", 5), ST.make(MD.TF, "item.arcticHelm" , 1));
		RM.Loom.addRecipe2(T, 16,  128, ST.tag(5), ST.make(MD.TF, "item.arcticFur", 8), ST.make(MD.TF, "item.arcticPlate", 1));
		RM.Loom.addRecipe2(T, 16,  128, ST.tag(6), ST.make(MD.TF, "item.arcticFur", 7), ST.make(MD.TF, "item.arcticLegs" , 1));
		RM.Loom.addRecipe2(T, 16,  128, ST.tag(7), ST.make(MD.TF, "item.arcticFur", 4), ST.make(MD.TF, "item.arcticBoots", 1));
		
		// Those things are quite rare, so their low Durability is making them a tad bit too useless.
		if (ConfigsGT.GREGTECH.get("general", "BetterTwilightDurability", T)) try {
			TFItems.crumbleHorn.setMaxDamage(10000);
			TFItems.peacockFan .setMaxDamage(10000);
			TFItems.oreMagnet  .setMaxDamage(10000); // Okay 12 is definitely ridiculous, that is sometimes even less than ONE Ore Vein!
			TFItems.giantPick  .setMaxDamage(10000); // Makes way more sense to actually have some Durability due to using 64 per harvest.
		} catch(Throwable e) {e.printStackTrace(ERR);}
		
//      Doesnt work right, stuff will miss the enchants.
//      RM.Bath.addRecipe1(T, 0, 5*144, ST.make(Items.iron_helmet       , 1, 0), FL.FieryBlood.make(5*L), NF, ST.make(MD.TF, "item.fieryHelm" , 1));
//      RM.Bath.addRecipe1(T, 0, 5*144, ST.make(Items.iron_helmet       , 1, 0), FL.FieryTears.make(5*L), NF, ST.make(MD.TF, "item.fieryHelm" , 1));
//      RM.Bath.addRecipe1(T, 0, 8*144, ST.make(Items.iron_chestplate   , 1, 0), FL.FieryBlood.make(8*L), NF, ST.make(MD.TF, "item.fieryPlate", 1));
//      RM.Bath.addRecipe1(T, 0, 8*144, ST.make(Items.iron_chestplate   , 1, 0), FL.FieryTears.make(8*L), NF, ST.make(MD.TF, "item.fieryPlate", 1));
//      RM.Bath.addRecipe1(T, 0, 7*144, ST.make(Items.iron_leggings     , 1, 0), FL.FieryBlood.make(7*L), NF, ST.make(MD.TF, "item.fieryLegs" , 1));
//      RM.Bath.addRecipe1(T, 0, 7*144, ST.make(Items.iron_leggings     , 1, 0), FL.FieryTears.make(7*L), NF, ST.make(MD.TF, "item.fieryLegs" , 1));
//      RM.Bath.addRecipe1(T, 0, 4*144, ST.make(Items.iron_boots        , 1, 0), FL.FieryBlood.make(4*L), NF, ST.make(MD.TF, "item.fieryBoots", 1));
//      RM.Bath.addRecipe1(T, 0, 4*144, ST.make(Items.iron_boots        , 1, 0), FL.FieryTears.make(4*L), NF, ST.make(MD.TF, "item.fieryBoots", 1));
//      RM.Bath.addRecipe1(T, 0, 2*144, ST.make(Items.iron_sword        , 1, 0), FL.FieryBlood.make(2*L), NF, ST.make(MD.TF, "item.fierySword", 1));
//      RM.Bath.addRecipe1(T, 0, 2*144, ST.make(Items.iron_sword        , 1, 0), FL.FieryTears.make(2*L), NF, ST.make(MD.TF, "item.fierySword", 1));
//      RM.Bath.addRecipe1(T, 0, 3*144, ST.make(Items.iron_pickaxe      , 1, 0), FL.FieryBlood.make(3*L), NF, ST.make(MD.TF, "item.fieryPick" , 1));
//      RM.Bath.addRecipe1(T, 0, 3*144, ST.make(Items.iron_pickaxe      , 1, 0), FL.FieryTears.make(3*L), NF, ST.make(MD.TF, "item.fieryPick" , 1));
	}
}
