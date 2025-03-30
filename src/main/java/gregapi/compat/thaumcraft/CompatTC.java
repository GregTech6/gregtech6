/**
 * Copyright (c) 2024 GregTech-6 Team
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

package gregapi.compat.thaumcraft;

import cpw.mods.fml.common.event.FMLModIdMappingEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.compat.CompatBase;
import gregapi.config.ConfigCategories;
import gregapi.data.LH;
import gregapi.data.OD;
import gregapi.data.TC;
import gregapi.data.TC.TC_AspectStack;
import gregapi.oredict.OreDictManager;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.wooddict.WoodDictionary;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.internal.WeightedRandomLoot;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.items.equipment.ItemElementalAxe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gregapi.data.CS.*;

public class CompatTC extends CompatBase implements ICompatTC {
	public CompatTC() {
		LH.add("tc.aspect.strontio" , "Stupidness, Incompetence");
		LH.add("tc.aspect.nebrisum" , "Cheatiness, Raiding, Hoarding");
		LH.add("tc.aspect.electrum" , "Electricity, Lightning");
		LH.add("tc.aspect.magneto"  , "Magnetism, Attraction");
		LH.add("tc.aspect.radio"    , "Radiation");
//      LH.add("tc.aspect.reflexio" , "Reflection");
		
		ThaumcraftApi                    .class.getCanonicalName();
		ThaumcraftApiHelper              .class.getCanonicalName();
		Aspect                           .class.getCanonicalName();
		AspectList                       .class.getCanonicalName();
		CrucibleRecipe                   .class.getCanonicalName();
		IArcaneRecipe                    .class.getCanonicalName();
		InfusionEnchantmentRecipe        .class.getCanonicalName();
		InfusionRecipe                   .class.getCanonicalName();
		ResearchCategories               .class.getCanonicalName();
		ResearchCategoryList             .class.getCanonicalName();
		ResearchItem                     .class.getCanonicalName();
		ResearchPage                     .class.getCanonicalName();
		thaumcraft.common.lib.utils.Utils.class.getCanonicalName();
		
		TC.AER              .mAspect = Aspect.AIR;
		TC.ALIENIS          .mAspect = Aspect.ELDRITCH;
		TC.AQUA             .mAspect = Aspect.WATER;
		TC.ARBOR            .mAspect = Aspect.TREE;
		TC.AURAM            .mAspect = Aspect.AURA;
		TC.BESTIA           .mAspect = Aspect.BEAST;
		TC.COGNITIO         .mAspect = Aspect.MIND;
		TC.CORPUS           .mAspect = Aspect.FLESH;
		TC.EXAMINIS         .mAspect = Aspect.UNDEAD;
		TC.FABRICO          .mAspect = Aspect.CRAFT;
		TC.FAMES            .mAspect = Aspect.HUNGER;
		TC.GELUM            .mAspect = Aspect.COLD;
		TC.GRANUM           .mAspect = Aspect.PLANT;
		TC.HERBA            .mAspect = Aspect.PLANT;
		TC.HUMANUS          .mAspect = Aspect.MAN;
		TC.IGNIS            .mAspect = Aspect.FIRE;
		TC.INSTRUMENTUM     .mAspect = Aspect.TOOL;
		TC.ITER             .mAspect = Aspect.TRAVEL;
		TC.LIMUS            .mAspect = Aspect.SLIME;
		TC.LUCRUM           .mAspect = Aspect.GREED;
		TC.LUX              .mAspect = Aspect.LIGHT;
		TC.MACHINA          .mAspect = Aspect.MECHANISM;
		TC.MESSIS           .mAspect = Aspect.CROP;
		TC.METALLUM         .mAspect = Aspect.METAL;
		TC.METO             .mAspect = Aspect.HARVEST;
		TC.MORTUUS          .mAspect = Aspect.DEATH;
		TC.MOTUS            .mAspect = Aspect.MOTION;
		TC.ORDO             .mAspect = Aspect.ORDER;
		TC.PANNUS           .mAspect = Aspect.CLOTH;
		TC.PERDITIO         .mAspect = Aspect.ENTROPY;
		TC.PERFODIO         .mAspect = Aspect.MINE;
		TC.PERMUTATIO       .mAspect = Aspect.EXCHANGE;
		TC.POTENTIA         .mAspect = Aspect.ENERGY;
		TC.PRAECANTIO       .mAspect = Aspect.MAGIC;
		TC.SANO             .mAspect = Aspect.HEAL;
		TC.SENSUS           .mAspect = Aspect.SENSES;
		TC.SPIRITUS         .mAspect = Aspect.SOUL;
		TC.TELUM            .mAspect = Aspect.WEAPON;
		TC.TERRA            .mAspect = Aspect.EARTH;
		TC.TEMPESTAS        .mAspect = Aspect.WEATHER;
		TC.TENEBRAE         .mAspect = Aspect.DARKNESS;
		TC.TUTAMEN          .mAspect = Aspect.ARMOR;
		TC.VACUOS           .mAspect = Aspect.VOID;
		TC.VENEMUM          .mAspect = Aspect.POISON;
		TC.VICTUS           .mAspect = Aspect.LIFE;
		TC.VINCULUM         .mAspect = Aspect.TRAP;
		TC.VITIUM           .mAspect = Aspect.TAINT;
		TC.VITREUS          .mAspect = Aspect.CRYSTAL;
		TC.VOLATUS          .mAspect = Aspect.FLIGHT;
		
		TC.STRONTIO         .mAspect = new Aspect("strontio"    , 0xeec2b3, new Aspect[] {Aspect.MIND, Aspect.ENTROPY}      , new ResourceLocation(RES_PATH_ASPECTS + "STRONTIO.png"), 1);
		TC.NEBRISUM         .mAspect = new Aspect("nebrisum"    , 0xeeee7e, new Aspect[] {Aspect.MINE, Aspect.GREED}        , new ResourceLocation(RES_PATH_ASPECTS + "NEBRISUM.png"), 1);
		TC.ELECTRUM         .mAspect = new Aspect("electrum"    , 0xc0eeee, new Aspect[] {Aspect.ENERGY, Aspect.MECHANISM}  , new ResourceLocation(RES_PATH_ASPECTS + "ELECTRUM.png"), 1);
		TC.MAGNETO          .mAspect = new Aspect("magneto"     , 0xc0c0c0, new Aspect[] {Aspect.METAL, Aspect.TRAVEL}      , new ResourceLocation(RES_PATH_ASPECTS + "MAGNETO.png"), 1);
		TC.RADIO            .mAspect = new Aspect("radio"       , 0xc0ffc0, new Aspect[] {Aspect.LIGHT, Aspect.ENERGY}      , new ResourceLocation(RES_PATH_ASPECTS + "RADIO.png"), 1);
//      TC.REFLEXIO         .mAspect = new Aspect("reflexio"    , 0xf0f0f0, new Aspect[] {Aspect.ENERGY, Aspect.EXCHANGE}   , new ResourceLocation(RES_PATH_ASPECTS + "REFLEXIO.png"), 1);
		
		TC.REFLEXIO         .mAspect = Aspect.EXCHANGE;
		
		ThaumcraftApi.registerEntityTag("TwilightForest.Forest Squirrel"             , new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Forest Bunny"                , new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Tiny Bird"                   , new AspectList().add(Aspect.BEAST, 2).add(Aspect.FLIGHT, 2).add(Aspect.AIR, 1));
		ThaumcraftApi.registerEntityTag("TwilightForest.Glacier Penguin"             , new AspectList().add(Aspect.BEAST, 2).add(Aspect.FLIGHT, 1).add(Aspect.COLD, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Forest Raven"                , new AspectList().add(Aspect.BEAST, 2).add(Aspect.FLIGHT, 2).add(Aspect.AIR, 1).add(Aspect.SENSES, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Wild Boar"                   , new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Bighorn Sheep"               , new AspectList().add(Aspect.BEAST, 2).add(Aspect.EARTH, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Questing Ram"                , new AspectList().add(Aspect.BEAST, 5).add(Aspect.CLOTH, 5).add(Aspect.SENSES, 5));
		ThaumcraftApi.registerEntityTag("TwilightForest.Wild Deer"                   , new AspectList().add(Aspect.BEAST, 3).add(Aspect.EARTH, 3));
		ThaumcraftApi.registerEntityTag("TwilightForest.Firefly"                     , new AspectList().add(Aspect.BEAST, 1).add(Aspect.FLIGHT, 1).add(Aspect.LIGHT, 1));
		ThaumcraftApi.registerEntityTag("TwilightForest.Skeleton Druid"              , new AspectList().add(Aspect.UNDEAD, 3).add(Aspect.MAN, 1).add(Aspect.MAGIC, 1));
		ThaumcraftApi.registerEntityTag("TwilightForest.Loyal Zombie"                , new AspectList().add(Aspect.UNDEAD, 2).add(Aspect.MAN, 1).add(Aspect.EARTH, 1));
		ThaumcraftApi.registerEntityTag("TwilightForest.Lich Minion"                 , new AspectList().add(Aspect.UNDEAD, 2).add(Aspect.MAN, 1).add(Aspect.EARTH, 1));
		ThaumcraftApi.registerEntityTag("TwilightForest.Giant Miner"                 , new AspectList().add(Aspect.MAN, 5).add(Aspect.MINE, 3).add(Aspect.GREED, 3));
		ThaumcraftApi.registerEntityTag("TwilightForest.Armored Giant"               , new AspectList().add(Aspect.MAN, 5).add(Aspect.WEAPON, 3).add(Aspect.ARMOR, 3));
		ThaumcraftApi.registerEntityTag("TwilightForest.Redcap"                      , new AspectList().add(Aspect.MAN, 2).add(Aspect.MINE, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Redcap Sapper"               , new AspectList().add(Aspect.MAN, 2).add(Aspect.ENERGY, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.WinterWolf"                  , new AspectList().add(Aspect.BEAST, 3).add(Aspect.EARTH, 3));
		ThaumcraftApi.registerEntityTag("TwilightForest.Mist Wolf"                   , new AspectList().add(Aspect.BEAST, 3).add(Aspect.EARTH, 3));
		ThaumcraftApi.registerEntityTag("TwilightForest.Hostile Wolf"                , new AspectList().add(Aspect.BEAST, 3).add(Aspect.EARTH, 3));
		ThaumcraftApi.registerEntityTag("TwilightForest.Maze Slime"                  , new AspectList().add(Aspect.SLIME, 2).add(Aspect.WATER, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.King Spider"                 , new AspectList().add(Aspect.BEAST, 3).add(Aspect.ENTROPY, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Redscale Broodling"          , new AspectList().add(Aspect.BEAST, 3).add(Aspect.ENTROPY, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Hedge Spider"                , new AspectList().add(Aspect.BEAST, 3).add(Aspect.ENTROPY, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Swarm Spider"                , new AspectList().add(Aspect.BEAST, 2).add(Aspect.ENTROPY, 1));
		ThaumcraftApi.registerEntityTag("TwilightForest.Fire Beetle"                 , new AspectList().add(Aspect.BEAST, 3).add(Aspect.ENTROPY, 2).add(Aspect.FIRE, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Slime Beetle"                , new AspectList().add(Aspect.BEAST, 3).add(Aspect.ENTROPY, 2).add(Aspect.SLIME, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Pinch Beetle"                , new AspectList().add(Aspect.BEAST, 3).add(Aspect.ENTROPY, 2).add(Aspect.TRAP, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Mini Ghast"                  , new AspectList().add(Aspect.UNDEAD, 2).add(Aspect.FIRE, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Tower Ghast"                 , new AspectList().add(Aspect.UNDEAD, 6).add(Aspect.FIRE, 6));
		ThaumcraftApi.registerEntityTag("TwilightForest.Tower Boss"                  , new AspectList().add(Aspect.UNDEAD, 20).add(Aspect.FIRE, 20));
		ThaumcraftApi.registerEntityTag("TwilightForest.Tower Termite"               , new AspectList().add(Aspect.BEAST, 1).add(Aspect.TREE, 1));
		ThaumcraftApi.registerEntityTag("TwilightForest.Helmet Crab"                 , new AspectList().add(Aspect.BEAST, 1).add(Aspect.WATER, 1).add(Aspect.ARMOR, 1));
		ThaumcraftApi.registerEntityTag("TwilightForest.Mosquito Swarm"              , new AspectList().add(Aspect.BEAST, 1).add(Aspect.FLIGHT, 1).add(Aspect.POISON, 1));
		ThaumcraftApi.registerEntityTag("TwilightForest.Stable Ice Core"             , new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.COLD, 1));
		ThaumcraftApi.registerEntityTag("TwilightForest.Unstable Ice Core"           , new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.COLD, 1).add(Aspect.EXCHANGE, 1));
		ThaumcraftApi.registerEntityTag("TwilightForest.Ice Crystal"                 , new AspectList().add(Aspect.ELDRITCH, 4).add(Aspect.COLD, 1));
		ThaumcraftApi.registerEntityTag("TwilightForest.SnowGuardian"                , new AspectList().add(Aspect.SOUL, 3).add(Aspect.ARMOR, 2).add(Aspect.WEAPON, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Death Tome"                  , new AspectList().add(Aspect.MIND, 3).add(Aspect.FLIGHT, 1).add(Aspect.MAGIC, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Twilight Wraith"             , new AspectList().add(Aspect.SOUL, 3).add(Aspect.FLIGHT, 1).add(Aspect.MAGIC, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Tower Golem"                 , new AspectList().add(Aspect.TREE, 4).add(Aspect.MECHANISM, 3));
		ThaumcraftApi.registerEntityTag("TwilightForest.Block&Chain Goblin"          , new AspectList().add(Aspect.MAN, 4).add(Aspect.WEAPON, 3));
		ThaumcraftApi.registerEntityTag("TwilightForest.Upper Goblin Knight"         , new AspectList().add(Aspect.MAN, 4).add(Aspect.WEAPON, 3));
		ThaumcraftApi.registerEntityTag("TwilightForest.Lower Goblin Knight"         , new AspectList().add(Aspect.MAN, 4).add(Aspect.ARMOR, 3));
		ThaumcraftApi.registerEntityTag("TwilightForest.Troll"                       , new AspectList().add(Aspect.MAN, 4).add(Aspect.CRYSTAL, 3).add(Aspect.DARKNESS, 3));
		ThaumcraftApi.registerEntityTag("TwilightForest.Twilight Kobold"             , new AspectList().add(Aspect.MAN, 1).add(Aspect.BEAST, 1));
		ThaumcraftApi.registerEntityTag("TwilightForest.Naga"                        , new AspectList().add(Aspect.BEAST, 20).add(Aspect.ARMOR, 20).add(Aspect.TRAVEL, 20));
		ThaumcraftApi.registerEntityTag("TwilightForest.Naga Segment"                , new AspectList().add(Aspect.BEAST, 20).add(Aspect.ARMOR, 20).add(Aspect.TRAVEL, 20));
		ThaumcraftApi.registerEntityTag("TwilightForest.Hydra"                       , new AspectList().add(Aspect.BEAST, 20).add(Aspect.HEAL, 20).add(Aspect.FIRE, 20));
		ThaumcraftApi.registerEntityTag("TwilightForest.HydraHead"                   , new AspectList().add(Aspect.BEAST, 20).add(Aspect.MIND, 20).add(Aspect.FIRE, 20));
		ThaumcraftApi.registerEntityTag("TwilightForest.Twilight Lich"               , new AspectList().add(Aspect.UNDEAD, 20).add(Aspect.MAN, 20).add(Aspect.MAGIC, 20));
		ThaumcraftApi.registerEntityTag("TwilightForest.Harbinger Cube"              , new AspectList().add(Aspect.ELDRITCH, 20).add(Aspect.DEATH, 20).add(Aspect.ENTROPY, 20));
		ThaumcraftApi.registerEntityTag("TwilightForest.Adherent"                    , new AspectList().add(Aspect.ELDRITCH, 20).add(Aspect.DEATH, 20).add(Aspect.ENTROPY, 20));
		ThaumcraftApi.registerEntityTag("TwilightForest.RovingCube"                  , new AspectList().add(Aspect.ELDRITCH, 20).add(Aspect.DEATH, 20).add(Aspect.ENTROPY, 20));
		ThaumcraftApi.registerEntityTag("TwilightForest.Minotaur"                    , new AspectList().add(Aspect.BEAST, 4).add(Aspect.MAN, 4).add(Aspect.WEAPON, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Minoshroom"                  , new AspectList().add(Aspect.BEAST, 20).add(Aspect.MAN, 20).add(Aspect.WEAPON, 20));
		ThaumcraftApi.registerEntityTag("TwilightForest.Knight Phantom"              , new AspectList().add(Aspect.SOUL, 20).add(Aspect.ARMOR, 20).add(Aspect.WEAPON, 20));
		ThaumcraftApi.registerEntityTag("TwilightForest.Yeti"                        , new AspectList().add(Aspect.BEAST, 4).add(Aspect.MAN, 4).add(Aspect.COLD, 2));
		ThaumcraftApi.registerEntityTag("TwilightForest.Yeti Boss"                   , new AspectList().add(Aspect.BEAST, 20).add(Aspect.MAN, 20).add(Aspect.COLD, 20));
	}
	
	@Override public void onServerStarting(FMLServerStartingEvent aEvent) {
		// These ItemStacks are Enchanted BEFORE being copied in Thaumcraft, which leads to them always having the SAME Enchantment...
		for (WeightedRandomLoot tLoot : WeightedRandomLoot.lootBagCommon) {
			if (tLoot != null && (ST.equal(tLoot.item, Items.book) || ST.equal(tLoot.item, Items.enchanted_book))) {
				ST.REVERT_TO_BOOK_TO_FIX_STUPID.add(tLoot.item);
			}
		}
	}
	
	@Override
	public ItemStack[] lootbag(long aMeta) {
		ST.fixBookStacks();
		ItemStack[] rStacks = ST.array(8+RNGSUS.nextInt(5));
		for (int i = 0; i < rStacks.length; i++) {
			rStacks[i] = thaumcraft.common.lib.utils.Utils.generateLoot(UT.Code.bind2(aMeta), RNGSUS);
			ST.fixBookStacks();
		}
		return rStacks;
	}
	
	@Override
	public void onIDChanging(FMLModIdMappingEvent aEvent) {
		try {
			// This fixes the frikkin Axe, so it can use OreDict Logs, which is broken in way too many ways... especially after the Block ID change of Vanilla MC that did not get ported properly into Thaumcraft.
			ItemElementalAxe.oreDictLogs.clear();
			// Wildcard Logs of this type are registered with all 16 MetaDatas so this should take care of most.
			for (ItemStack tStack : OreDictManager.getOres(OD.woodLog, T)) {
				Block tBlock = ST.block(tStack);
				if (tBlock != NB) {
					ItemElementalAxe.oreDictLogs.add(Arrays.asList(new Object[] {tBlock, Integer.valueOf(ST.meta(tStack))}));
				}
			}
			// While not taking care of most Log Rotations, this should still work and fill in some gaps.
			for (ItemStackContainer tStack : WoodDictionary.WOODS.keySet()) if (tStack.mBlock != NB) {
				if (tStack.mMetaData == W) {
					for (int i = 0; i < 16; i++)
					ItemElementalAxe.oreDictLogs.add(Arrays.asList(new Object[] {tStack.mBlock, Integer.valueOf(i)}));
				} else {
					ItemElementalAxe.oreDictLogs.add(Arrays.asList(new Object[] {tStack.mBlock, Integer.valueOf(tStack.mMetaData)}));
				}
			}
		} catch (NoClassDefFoundError e) {
			// Well, Axe doesn't exist.
		}
	}
	
	@Override
	public Object getAspectList(TC_AspectStack... aAspects) {
		AspectList rAspects = new AspectList();
		if (aAspects != null) for (TC_AspectStack tAspect : aAspects) rAspects.add((Aspect)tAspect.mAspect.mAspect, (int)tAspect.mAmount);
		return rAspects;
	}
	
	@Override
	public Object getAspectList(List<TC_AspectStack> aAspects) {
		AspectList rAspects = new AspectList();
		if (aAspects != null) for (TC_AspectStack tAspect : aAspects) rAspects.add((Aspect)tAspect.mAspect.mAspect, (int)tAspect.mAmount);
		return rAspects;
	}
	
	@Override
	public Object addResearch(String aResearch, String aName, String aText, String[] aParentResearches, String aCategory, ItemStack aIcon, int aComplexity, int aType, int aX, int aY, List<TC_AspectStack> aAspects, ItemStack[] aResearchTriggers, Object... aPages) {
		if (!ConfigsGT.RECIPES.get(ConfigCategories.Recipes.researches, aResearch, T)) return null;
		
		ResearchCategoryList tCategory = ResearchCategories.getResearchList(aCategory);
		if (tCategory == null) return null;
		
		for (boolean temp = T; temp;) {temp = F; for (ResearchItem tResearch : tCategory.research.values()) if (tResearch.displayColumn == aX && tResearch.displayRow == aY) {aX += (aX>0?+5:-5); aY += (aY>0?+5:-5); temp = T;}}
		
		ResearchItem rResearch = new ResearchItem(aResearch, aCategory, (AspectList)getAspectList(aAspects), aX, aY, aComplexity, aIcon);
		ArrayList<ResearchPage> tPages = new ArrayListNoNulls<>(aPages.length);
		LH.add("tc.research_name."+aResearch, aName);
		LH.add("tc.research_text."+aResearch, "[GT] " + aText);
		for (Object tPage : aPages) if (tPage != null) {
			if (tPage instanceof String                     ) {tPages.add(new ResearchPage((String                          )tPage)); continue;}
			if (tPage instanceof IRecipe                    ) {tPages.add(new ResearchPage((IRecipe                         )tPage)); continue;}
			if (tPage instanceof IArcaneRecipe              ) {tPages.add(new ResearchPage((IArcaneRecipe                   )tPage)); continue;}
			if (tPage instanceof CrucibleRecipe             ) {tPages.add(new ResearchPage((CrucibleRecipe                  )tPage)); continue;}
			if (tPage instanceof InfusionRecipe             ) {tPages.add(new ResearchPage((InfusionRecipe                  )tPage)); continue;}
			if (tPage instanceof InfusionEnchantmentRecipe  ) {tPages.add(new ResearchPage((InfusionEnchantmentRecipe       )tPage)); continue;}
		}
		if ((aType & RESEARCH_TYPE_AUTOUNLOCK   ) != 0) rResearch.setAutoUnlock();
		if ((aType & RESEARCH_TYPE_SECONDARY    ) != 0) rResearch.setSecondary();
		if ((aType & RESEARCH_TYPE_SPECIAL      ) != 0) rResearch.setSpecial();
		if ((aType & RESEARCH_TYPE_VIRTUAL      ) != 0) rResearch.setVirtual();
		if ((aType & RESEARCH_TYPE_HIDDEN       ) != 0) rResearch.setHidden();
		if ((aType & RESEARCH_TYPE_ROUND        ) != 0) rResearch.setRound();
		if ((aType & RESEARCH_TYPE_FREE         ) != 0) rResearch.setStub();
		if ((aType & RESEARCH_TYPE_LOST         ) != 0) rResearch.setLost();
		
		if (aParentResearches != null) {
			ArrayList<String> tParentResearches = new ArrayListNoNulls<>();
			for (String tParent : aParentResearches) if (ConfigsGT.RECIPES.get(ConfigCategories.Recipes.researches, aResearch, T)) tParentResearches.add(tParent);
			if (tParentResearches.size() > 0) {
				rResearch.setParents(tParentResearches.toArray(ZL_STRING));
				rResearch.setConcealed();
			}
		}
		if (aResearchTriggers != null && aResearchTriggers.length > 0) {
			rResearch.setItemTriggers(aResearchTriggers);
			rResearch.setHidden();
		}
		rResearch.setPages(tPages.toArray(new ResearchPage[tPages.size()]));
		return rResearch.registerResearchItem();
	}
	
	@Override
	public Object addCrucibleRecipe(String aResearch, Object aInput, ItemStack aOutput, List<TC_AspectStack> aAspects) {
		if (UT.Code.stringInvalid(aResearch) || aInput == null || aOutput == null || aAspects == null || aAspects.isEmpty()) return null;
		return ThaumcraftApi.addCrucibleRecipe(aResearch, ST.copy_(aOutput), aInput instanceof ItemStack || aInput instanceof ArrayList ? aInput : aInput.toString(), (AspectList)getAspectList(aAspects));
	}
	@Override
	public Object addCrucibleRecipe(String aResearch, Object aInput, ItemStack aOutput, TC_AspectStack... aAspects) {
		if (UT.Code.stringInvalid(aResearch) || aInput == null || aOutput == null || aAspects == null || aAspects.length <= 0) return null;
		return ThaumcraftApi.addCrucibleRecipe(aResearch, ST.copy_(aOutput), aInput instanceof ItemStack || aInput instanceof ArrayList ? aInput : aInput.toString(), (AspectList)getAspectList(aAspects));
	}
	
	@Override @SuppressWarnings("deprecation")
	public boolean registerThaumcraftAspectsToItem(ItemStack aExampleStack, List<TC_AspectStack> aAspects, String aOreDict) {
		return F;
	}
	@Override @SuppressWarnings("deprecation")
	public boolean registerThaumcraftAspectsToItem(ItemStack aExampleStack, String aOreDict, TC_AspectStack... aAspects) {
		return F;
	}
	
	@Override
	public boolean registerThaumcraftAspectsToItem(ItemStack aStack, List<TC_AspectStack> aAspects, boolean aAdditive) {
		if (aAspects.isEmpty() || ST.invalid(aStack)) return F;
		if (aAdditive) {
			ThaumcraftApi.registerComplexObjectTag(aStack, (AspectList)getAspectList(aAspects));
			return T;
		}
		@SuppressWarnings("rawtypes")
		List aKey = Arrays.asList(aStack.getItem(), Integer.valueOf(ST.meta(aStack)));
		if (!ThaumcraftApi.objectTags.containsKey(aKey)) ThaumcraftApi.objectTags.put(aKey, (AspectList)getAspectList(aAspects));
		return T;
	}
	@Override
	public boolean registerThaumcraftAspectsToItem(ItemStack aStack, boolean aAdditive, TC_AspectStack... aAspects) {
		if (aAspects.length <= 0 || ST.invalid(aStack)) return F;
		if (aAdditive) {
			ThaumcraftApi.registerComplexObjectTag(aStack, (AspectList)getAspectList(aAspects));
			return T;
		}
		@SuppressWarnings("rawtypes")
		List aKey = Arrays.asList(aStack.getItem(), Integer.valueOf(ST.meta(aStack)));
		if (!ThaumcraftApi.objectTags.containsKey(aKey)) ThaumcraftApi.objectTags.put(aKey, (AspectList)getAspectList(aAspects));
		return T;
	}
	
	@Override
	public boolean registerPortholeBlacklistedBlock(Block aBlock) {
		ThaumcraftApi.portableHoleBlackList.add(aBlock);
		return T;
	}
}
