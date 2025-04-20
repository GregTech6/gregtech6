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
import twilightforest.item.TFItems;

import static gregapi.data.CS.*;
import static gregapi.util.CR.DEF;

public class Compat_Recipes_TwilightForest extends CompatMods {
	public Compat_Recipes_TwilightForest(ModData aMod, Abstract_Mod aGTMod) {super(aMod, aGTMod);}
	
	@Override public void onPostLoad(FMLPostInitializationEvent aInitEvent) {OUT.println("GT_Mod: Doing TF Recipes.");
		CR.shaped(IL.Stick.get(2), DEF, "s", "X", 'X', IL.TF_Dry_Bush);
		CR.shaped(IL.Stick.get(2), DEF, "k", "X", 'X', IL.TF_Dry_Bush);
		
		// Hide that damn thing so people wont get the Idea to use it.
		ST.hide(IL.TF_Uncrafting.item());
		// To get the Glass Bottle back, which is the Container Item.
		CR.shapeless(IL.TF_Firefly.get(1), new Object[] {IL.TF_Firefly_Jar});
		// Since the Cube gives all Progress related TF Achievements with GT6, it is not that bad that you use up the 5 Trophies in co-op Multiplayer. Oh and yes I know the Lamp of Cinders has itself as Container Item, that is intended as you just need to "have" it.
		CR.shapeless(IL.TF_Cube_of_Annihilation.get(1), new Object[] {IL.TF_Lamp_of_Cinders, ST.make(MD.TF, "tile.TFTowerDevice", 1, 12), IL.TF_Transformation_Powder, IL.TF_Giant_Obsidian, IL.TF_Trophy_Hydra, IL.TF_Trophy_Urghast, IL.TF_Trophy_Naga, IL.TF_Trophy_Lich, IL.TF_Trophy_Snowqueen});
		// Make it as resistant as Obsidian after GT6/IC2 nerfed it. This way Strong Dynamite can break it.
		IL.TF_Deadrock.block().setResistance(60);
		// registered under the Ore Prefix, which causes duplication Issues.
		CR.delate(MD.TF, "item.shardCluster", "item.ironwoodRaw");
		
		
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
		
		RM.mortarize(IL.TF_LiveRoot .get(1), OP.dust     .mat(MT.LiveRoot , 1));
		RM.mortarize(IL.TF_Mushgloom.get(1), OP.dustSmall.mat(MT.Glowstone, 1));
		
		CR.remove(IL.TF_Giant_Log     .get(1));
		CR.remove(IL.TF_Giant_Cobble  .get(1));
		CR.remove(IL.TF_Giant_Obsidian.get(1));
		CR.remove(IL.TF_Giant_Leaves  .get(1));
		
		RM.sawing(64, 64, F, 50, IL.TF_Giant_Log     .get(1), ST.make(Blocks.log        , 64, 0));
		RM.sawing(64,256, F, 50, IL.TF_Giant_Cobble  .get(1), ST.make(Blocks.cobblestone, 64, 0));
		RM.sawing(64,512, F, 50, IL.TF_Giant_Obsidian.get(1), ST.make(Blocks.obsidian   , 64, 0));
		RM.sawing(64, 16, F, 50, IL.TF_Giant_Leaves  .get(1), ST.make(Blocks.leaves     , 64, 0));
		
		RM.Boxinator.addRecipe2(T,128,128, ST.make(Blocks.obsidian         , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Obsidian.get(1));
		
		RM.Boxinator.addRecipe2(T,128,128, ST.make(Blocks.stone            , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Cobble  .get(1));
		RM.Boxinator.addRecipe2(T,128,128, ST.make(Blocks.cobblestone      , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Cobble  .get(1));
		
		RM.Boxinator.addRecipe2(T,128,128, ST.make(Blocks.log              , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Log     .get(1));
		RM.Boxinator.addRecipe2(T,128,128, ST.make(Blocks.log2             , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Log     .get(1));
		RM.Boxinator.addRecipe2(T,128,128, ST.make(BlocksGT.Log1           , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Log     .get(1));
		RM.Boxinator.addRecipe2(T,128,128, ST.make(BlocksGT.LogA           , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Log     .get(1));
		RM.Boxinator.addRecipe2(T,128,128, ST.make(BlocksGT.LogB           , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Log     .get(1));
		RM.Boxinator.addRecipe2(T,128,128, ST.make(BlocksGT.LogC           , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Log     .get(1));
	////RM.Boxinator.addRecipe2(T,128,128, ST.make(BlocksGT.LogD           , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Log     .get(1));
		RM.Boxinator.addRecipe2(T,128,128, ST.make(BlocksGT.Log1FireProof  , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Log     .get(1));
		RM.Boxinator.addRecipe2(T,128,128, ST.make(BlocksGT.LogAFireProof  , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Log     .get(1));
		RM.Boxinator.addRecipe2(T,128,128, ST.make(BlocksGT.LogBFireProof  , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Log     .get(1));
		RM.Boxinator.addRecipe2(T,128,128, ST.make(BlocksGT.LogCFireProof  , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Log     .get(1));
	////RM.Boxinator.addRecipe2(T,128,128, ST.make(BlocksGT.LogDFireProof  , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Log     .get(1));
		
		RM.Boxinator.addRecipe2(T,128,128, ST.make(Blocks.leaves           , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Leaves  .get(1));
		RM.Boxinator.addRecipe2(T,128,128, ST.make(Blocks.leaves2          , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Leaves  .get(1));
		RM.Boxinator.addRecipe2(T,128,128, ST.make(BlocksGT.Leaves_AB      , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Leaves  .get(1));
		RM.Boxinator.addRecipe2(T,128,128, ST.make(BlocksGT.Leaves_CD      , 64, W), IL.TF_Pick_Giant.getWildcard(0), IL.TF_Giant_Leaves  .get(1));
		
		RM.Mixer.addRecipeX(T, 16, 64, ST.array(OM.dust(MT.Redstone, 4*U), IL.TF_BorerEssence.get(4), ST.make(Items.ghast_tear, 1, W)), OP.dust.mat(MT.Carminite, 4));
		
		RM.Loom.addRecipe2(T, 16,  128, ST.tag(4), ST.make(MD.TF, "item.arcticFur", 5), ST.make(MD.TF, "item.arcticHelm" , 1));
		RM.Loom.addRecipe2(T, 16,  128, ST.tag(5), ST.make(MD.TF, "item.arcticFur", 8), ST.make(MD.TF, "item.arcticPlate", 1));
		RM.Loom.addRecipe2(T, 16,  128, ST.tag(6), ST.make(MD.TF, "item.arcticFur", 7), ST.make(MD.TF, "item.arcticLegs" , 1));
		RM.Loom.addRecipe2(T, 16,  128, ST.tag(7), ST.make(MD.TF, "item.arcticFur", 4), ST.make(MD.TF, "item.arcticBoots", 1));
		
		RM.Boxinator.addRecipe2(T, 16, 16, ST.make(Items.paper, 8, W), IL.TF_Magic_Map_Focus.get(1), NF, NF, IL.TF_Magic_Map_Empty.get(1));
		RM.Boxinator.addRecipe2(T, 16, 16, ST.make(Items.paper, 8, W), IL.TF_Maze_Map_Focus .get(1), NF, NF, IL.TF_Maze_Map_Empty .get(1));
		
		RM.Replicator.addRecipe1(F, 16, 288000, IL.TF_Trophy_Naga     .get(0), FL.Biomass   .make(16000), NF                   , IL.TF_NagaScale.get(1));
		RM.Replicator.addRecipe1(F, 16, 288000, IL.TF_Trophy_Naga     .get(0), FL.BiomassIC2.make(16000), NF                   , IL.TF_NagaScale.get(1));
		RM.Replicator.addRecipe1(F, 16, 288000, IL.TF_Trophy_Lich     .get(0), FL.Milk      .make(16000), NF                   , IL.TF_Transformation_Powder.get(1), ST.make(MD.TF, "item.charmOfLife1", 1, 0));
		RM.Replicator.addRecipe1(F, 16, 288000, IL.TF_Trophy_Hydra    .get(0), FL.Blaze     .make(16000), FL.FieryBlood.make(L), IL.TF_Hydrachop_Raw.get(1), ST.make(MD.TF, "item.charmOfKeeping1", 1, 0));
		RM.Replicator.addRecipe1(F, 16, 288000, IL.TF_Trophy_Urghast  .get(0), FL.Propane   .make(16000), FL.FieryTears.make(L), IL.TF_BorerEssence.get(8));
		RM.Replicator.addRecipe1(F, 16, 288000, IL.TF_Trophy_Urghast  .get(0), FL.Butane    .make(16000), FL.FieryTears.make(L), IL.TF_BorerEssence.get(8));
		RM.Replicator.addRecipe1(F, 16, 288000, IL.TF_Trophy_Snowqueen.get(0), FL.DistW     .make(32000), NF                   , ST.make(MD.TF, "item.iceBomb", 1, 0), IL.TF_Maze_Map_Focus.get(1));
		
		RM.LaserEngraver.addRecipe2(T, 16, 128, IL.Module_Stone_Generator        .get(0), IL.TF_Trophy_Naga.get(0), ST.make(MD.TF, "tile.TFNagastone", 1, 13));
		RM.LaserEngraver.addRecipe2(T, 16,  64, ST.make(Blocks.stone             , 1, W), IL.TF_Trophy_Naga.get(0), ST.make(MD.TF, "tile.TFNagastone", 1, 13));
		RM.LaserEngraver.addRecipe2(T, 16,  64, ST.make(Blocks.stonebrick        , 1, W), IL.TF_Trophy_Naga.get(0), ST.make(MD.TF, "tile.TFNagastone", 1,  1));
		RM.LaserEngraver.addRecipe2(T, 16,  64, ST.make(MD.TF, "tile.TFNagastone", 1, W), IL.TF_Trophy_Naga.get(0), ST.make(MD.TF, "tile.TFNagastone", 1,  1));
		
		RM.Freezer.addRecipe1(T, 16, 144, IL.TF_Trophy_Snowqueen.get(0), FL.Ice.make(9000), NF, ST.make(MD.TF, "tile.TFAuroraBrick", 1, 0));
		
		
		
		RM.moss(ST.make(MD.TF, "tile.TFMazestone" , 1, 1), ST.make(MD.TF, "tile.TFMazestone" , 1, 5));
		RM.moss(ST.make(MD.TF, "tile.TFUnderBrick", 1, 0), ST.make(MD.TF, "tile.TFUnderBrick", 1, 1));
		
		RM.stonetypes(null, F, OP.rockGt.mat(MT.STONES.Redrock, 4), OP.blockDust.mat(MT.STONES.Redrock, 1)
		, NI
		, NI
		, ST.make(MD.TF, "tile.TFUnderBrick", 1, 0)
		, ST.make(MD.TF, "tile.TFUnderBrick", 1, 2)
		, NI
		, NI
		, NI
		, NI
		);
		
		RM.stonetypes(MT.STONES.Mazestone, T, OP.rockGt.mat(MT.STONES.Mazestone, 4), OP.blockDust.mat(MT.STONES.Mazestone, 1)
		, ST.make(MD.TF, "tile.TFMazestone", 1, 0)
		, NI
		, ST.make(MD.TF, "tile.TFMazestone", 1, 1)
		, ST.make(MD.TF, "tile.TFMazestone", 1, 4)
		, ST.make(MD.TF, "tile.TFMazestone", 1, 3)
		, ST.make(MD.TF, "tile.TFMazestone", 1, 2)
		, ST.make(MD.TF, "tile.TFMazestone", 1, 6)
		, ST.make(MD.TF, "tile.TFMazestone", 1, 7)
		);
		
		RM.stonetypes(MT.STONES.Castlerock, T, OP.rockGt.mat(MT.STONES.Castlerock, 4), OP.blockDust.mat(MT.STONES.Castlerock, 1)
		, ST.make(MD.TF, "tile.CastleBrick", 1, 1)
		, NI
		, ST.make(MD.TF, "tile.CastleBrick", 1, 0)
		, ST.make(MD.TF, "tile.CastleBrick", 1, 2)
		, NI
		, NI
		, ST.make(MD.TF, "tile.CastleBrick", 1, 3)
		, NI
		);
		
		RM.stonetypes(MT.Ice, F, OP.gem.mat(MT.Ice, 9), OP.blockDust.mat(MT.Ice, 1)
		, ST.make(MD.TF, "tile.TFAuroraBrick", 1, 0)
		, NI
		, NI
		, NI
		, RM.stoneshapes(MT.Ice, F, ST.make(MD.TF, "tile.AuroraPillar", 1, 0), NI, ST.make(MD.TF, "tile.AuroraSlab", 1, 0), NI, NI)
		, NI
		, NI
		, NI
		);
		
		
		// Those things are quite rare, so their low Durability is making them a tad bit too useless otherwise.
		if (ConfigsGT.GREGTECH.get("general", "BetterTwilightDurability", T)) try {
			TFItems.crumbleHorn.setMaxDamage(10000);
			TFItems.peacockFan .setMaxDamage(10000);
			TFItems.oreMagnet  .setMaxDamage(10000); // Okay 12 is definitely ridiculous, that is sometimes even less than ONE Ore Vein!
			TFItems.giantPick  .setMaxDamage(10000); // Makes way more sense to actually have some Durability due to using 64 per harvest.
		} catch(Throwable e) {e.printStackTrace(ERR);}
		
		RM.Bath.addRecipe1(T, 0, 5*144, ST.make(Items.iron_helmet          , 1, W), FL.FieryBlood.make(5*L), NF, ST.make(MD.TF, "item.fieryHelm" , 1));
		RM.Bath.addRecipe1(T, 0, 5*144, ST.make(Items.iron_helmet          , 1, W), FL.FieryTears.make(5*L), NF, ST.make(MD.TF, "item.fieryHelm" , 1));
		RM.Bath.addRecipe1(T, 0, 8*144, ST.make(Items.iron_chestplate      , 1, W), FL.FieryBlood.make(8*L), NF, ST.make(MD.TF, "item.fieryPlate", 1));
		RM.Bath.addRecipe1(T, 0, 8*144, ST.make(Items.iron_chestplate      , 1, W), FL.FieryTears.make(8*L), NF, ST.make(MD.TF, "item.fieryPlate", 1));
		RM.Bath.addRecipe1(T, 0, 7*144, ST.make(Items.iron_leggings        , 1, W), FL.FieryBlood.make(7*L), NF, ST.make(MD.TF, "item.fieryLegs" , 1));
		RM.Bath.addRecipe1(T, 0, 7*144, ST.make(Items.iron_leggings        , 1, W), FL.FieryTears.make(7*L), NF, ST.make(MD.TF, "item.fieryLegs" , 1));
		RM.Bath.addRecipe1(T, 0, 4*144, ST.make(Items.iron_boots           , 1, W), FL.FieryBlood.make(4*L), NF, ST.make(MD.TF, "item.fieryBoots", 1));
		RM.Bath.addRecipe1(T, 0, 4*144, ST.make(Items.iron_boots           , 1, W), FL.FieryTears.make(4*L), NF, ST.make(MD.TF, "item.fieryBoots", 1));
		RM.Bath.addRecipe1(T, 0, 2*144, ST.make(Items.iron_sword           , 1, W), FL.FieryBlood.make(2*L), NF, ST.make(MD.TF, "item.fierySword", 1));
		RM.Bath.addRecipe1(T, 0, 2*144, ST.make(Items.iron_sword           , 1, W), FL.FieryTears.make(2*L), NF, ST.make(MD.TF, "item.fierySword", 1));
		RM.Bath.addRecipe1(T, 0, 3*144, ST.make(Items.iron_pickaxe         , 1, W), FL.FieryBlood.make(3*L), NF, ST.make(MD.TF, "item.fieryPick" , 1));
		RM.Bath.addRecipe1(T, 0, 3*144, ST.make(Items.iron_pickaxe         , 1, W), FL.FieryTears.make(3*L), NF, ST.make(MD.TF, "item.fieryPick" , 1));
		
		RM.Bath.addRecipe1(T, 0, 5*144, ST.make(MD.TF, "item.knightlyHelm" , 1, W), FL.FieryBlood.make(5*L), NF, ST.make(MD.TF, "item.fieryHelm" , 1));
		RM.Bath.addRecipe1(T, 0, 5*144, ST.make(MD.TF, "item.knightlyHelm" , 1, W), FL.FieryTears.make(5*L), NF, ST.make(MD.TF, "item.fieryHelm" , 1));
		RM.Bath.addRecipe1(T, 0, 8*144, ST.make(MD.TF, "item.knightlyPlate", 1, W), FL.FieryBlood.make(8*L), NF, ST.make(MD.TF, "item.fieryPlate", 1));
		RM.Bath.addRecipe1(T, 0, 8*144, ST.make(MD.TF, "item.knightlyPlate", 1, W), FL.FieryTears.make(8*L), NF, ST.make(MD.TF, "item.fieryPlate", 1));
		RM.Bath.addRecipe1(T, 0, 7*144, ST.make(MD.TF, "item.knightlyLegs" , 1, W), FL.FieryBlood.make(7*L), NF, ST.make(MD.TF, "item.fieryLegs" , 1));
		RM.Bath.addRecipe1(T, 0, 7*144, ST.make(MD.TF, "item.knightlyLegs" , 1, W), FL.FieryTears.make(7*L), NF, ST.make(MD.TF, "item.fieryLegs" , 1));
		RM.Bath.addRecipe1(T, 0, 4*144, ST.make(MD.TF, "item.knightlyBoots", 1, W), FL.FieryBlood.make(4*L), NF, ST.make(MD.TF, "item.fieryBoots", 1));
		RM.Bath.addRecipe1(T, 0, 4*144, ST.make(MD.TF, "item.knightlyBoots", 1, W), FL.FieryTears.make(4*L), NF, ST.make(MD.TF, "item.fieryBoots", 1));
		RM.Bath.addRecipe1(T, 0, 2*144, ST.make(MD.TF, "item.knightlySword", 1, W), FL.FieryBlood.make(2*L), NF, ST.make(MD.TF, "item.fierySword", 1));
		RM.Bath.addRecipe1(T, 0, 2*144, ST.make(MD.TF, "item.knightlySword", 1, W), FL.FieryTears.make(2*L), NF, ST.make(MD.TF, "item.fierySword", 1));
		RM.Bath.addRecipe1(T, 0, 3*144, ST.make(MD.TF, "item.knightlyPick" , 1, W), FL.FieryBlood.make(3*L), NF, ST.make(MD.TF, "item.fieryPick" , 1));
		RM.Bath.addRecipe1(T, 0, 3*144, ST.make(MD.TF, "item.knightlyPick" , 1, W), FL.FieryTears.make(3*L), NF, ST.make(MD.TF, "item.fieryPick" , 1));
		
		RM.Bath.addRecipe1(T, 0, 5*144, ST.make(MD.TF, "item.steeleafHelm" , 1, W), FL.FieryBlood.make(5*L), NF, ST.make(MD.TF, "item.fieryHelm" , 1));
		RM.Bath.addRecipe1(T, 0, 5*144, ST.make(MD.TF, "item.steeleafHelm" , 1, W), FL.FieryTears.make(5*L), NF, ST.make(MD.TF, "item.fieryHelm" , 1));
		RM.Bath.addRecipe1(T, 0, 8*144, ST.make(MD.TF, "item.steeleafPlate", 1, W), FL.FieryBlood.make(8*L), NF, ST.make(MD.TF, "item.fieryPlate", 1));
		RM.Bath.addRecipe1(T, 0, 8*144, ST.make(MD.TF, "item.steeleafPlate", 1, W), FL.FieryTears.make(8*L), NF, ST.make(MD.TF, "item.fieryPlate", 1));
		RM.Bath.addRecipe1(T, 0, 7*144, ST.make(MD.TF, "item.steeleafLegs" , 1, W), FL.FieryBlood.make(7*L), NF, ST.make(MD.TF, "item.fieryLegs" , 1));
		RM.Bath.addRecipe1(T, 0, 7*144, ST.make(MD.TF, "item.steeleafLegs" , 1, W), FL.FieryTears.make(7*L), NF, ST.make(MD.TF, "item.fieryLegs" , 1));
		RM.Bath.addRecipe1(T, 0, 4*144, ST.make(MD.TF, "item.steeleafBoots", 1, W), FL.FieryBlood.make(4*L), NF, ST.make(MD.TF, "item.fieryBoots", 1));
		RM.Bath.addRecipe1(T, 0, 4*144, ST.make(MD.TF, "item.steeleafBoots", 1, W), FL.FieryTears.make(4*L), NF, ST.make(MD.TF, "item.fieryBoots", 1));
		RM.Bath.addRecipe1(T, 0, 2*144, ST.make(MD.TF, "item.steeleafSword", 1, W), FL.FieryBlood.make(2*L), NF, ST.make(MD.TF, "item.fierySword", 1));
		RM.Bath.addRecipe1(T, 0, 2*144, ST.make(MD.TF, "item.steeleafSword", 1, W), FL.FieryTears.make(2*L), NF, ST.make(MD.TF, "item.fierySword", 1));
		RM.Bath.addRecipe1(T, 0, 3*144, ST.make(MD.TF, "item.steeleafPick" , 1, W), FL.FieryBlood.make(3*L), NF, ST.make(MD.TF, "item.fieryPick" , 1));
		RM.Bath.addRecipe1(T, 0, 3*144, ST.make(MD.TF, "item.steeleafPick" , 1, W), FL.FieryTears.make(3*L), NF, ST.make(MD.TF, "item.fieryPick" , 1));
	}
}
