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

package gregtech.loaders.a;

import static gregapi.data.CS.*;

import gregapi.data.CS.ItemsGT;
import gregapi.data.CS.SFX;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.item.prefixitem.PrefixItem;
import gregapi.item.prefixitem.PrefixItemProjectile;
import gregapi.item.prefixitem.PrefixItemRing;
import gregapi.util.ST;
import gregtech.entities.projectiles.EntityArrow_Material;
import gregtech.items.MultiItemBooks;
import gregtech.items.MultiItemBottles;
import gregtech.items.MultiItemBumbles;
import gregtech.items.MultiItemCans;
import gregtech.items.MultiItemFood;
import gregtech.items.MultiItemRandomTools;
import gregtech.items.MultiItemTechnological;

public class Loader_Items implements Runnable {
	@Override
	public void run() {
		PrefixItem tItem;
		
		ItemsGT.TECH       = new MultiItemTechnological();
		ItemsGT.TOOLS      = new MultiItemRandomTools();
		ItemsGT.CANS       = new MultiItemCans();
		ItemsGT.FOOD       = new MultiItemFood();
		ItemsGT.BOTTLES    = new MultiItemBottles();
		ItemsGT.BOOKS      = new MultiItemBooks();
		ItemsGT.BUMBLEBEES = new MultiItemBumbles();
		
		ItemsGT.ALL_MULTI_ITEMS[0] = ItemsGT.TECH;
		ItemsGT.ALL_MULTI_ITEMS[1] = ItemsGT.TOOLS;
		ItemsGT.ALL_MULTI_ITEMS[2] = ItemsGT.CANS;
		ItemsGT.ALL_MULTI_ITEMS[3] = ItemsGT.FOOD;
		ItemsGT.ALL_MULTI_ITEMS[4] = ItemsGT.BOTTLES;
		ItemsGT.ALL_MULTI_ITEMS[5] = ItemsGT.BOOKS;
		ItemsGT.ALL_MULTI_ITEMS[6] = ItemsGT.BUMBLEBEES;
		
		tItem = new PrefixItem(MD.GT, "gt.meta.dust"                        , OP.dust                           ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.dustSmall"                   , OP.dustSmall                      ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.dustTiny"                    , OP.dustTiny                       ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.dustDiv72"                   , OP.dustDiv72                      ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.dustImpure"                  , OP.dustImpure                     ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.crushed"                     , OP.crushed                        ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.crushedTiny"                 , OP.crushedTiny                    ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.crushedPurified"             , OP.crushedPurified                ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.crushedPurifiedTiny"         , OP.crushedPurifiedTiny            ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.crushedCentrifuged"          , OP.crushedCentrifuged             ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.crushedCentrifugedTiny"      , OP.crushedCentrifugedTiny         ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		
		tItem = new PrefixItem(MD.GT, "gt.meta.gemChipped"                  , OP.gemChipped                     ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.gemFlawed"                   , OP.gemFlawed                      ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.gem"                         , OP.gem                            ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.gemFlawless"                 , OP.gemFlawless                    ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.gemExquisite"                , OP.gemExquisite                   ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.gemLegendary"                , OP.gemLegendary                   ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.boule"                       , OP.bouleGt                        );
		
		tItem = new PrefixItem(MD.GT, "gt.meta.nugget"                      , OP.nugget                         ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.chunkGt"                     , OP.chunkGt                        ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.billet"                      , OP.billet                         ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.ingot"                       , OP.ingot                          ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.ingotHot"                    , OP.ingotHot                       );
		tItem = new PrefixItem(MD.GT, "gt.meta.ingotDouble"                 , OP.ingotDouble                    );
		tItem = new PrefixItem(MD.GT, "gt.meta.ingotTriple"                 , OP.ingotTriple                    );
		tItem = new PrefixItem(MD.GT, "gt.meta.ingotQuadruple"              , OP.ingotQuadruple                 );
		tItem = new PrefixItem(MD.GT, "gt.meta.ingotQuintuple"              , OP.ingotQuintuple                 );
		
		tItem = new PrefixItem(MD.GT, "gt.meta.plateGemTiny"                , OP.plateGemTiny                   ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.plateGem"                    , OP.plateGem                       ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.plateTiny"                   , OP.plateTiny                      ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.plate"                       , OP.plate                          ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.plateDouble"                 , OP.plateDouble                    ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.plateTriple"                 , OP.plateTriple                    ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.plateQuadruple"              , OP.plateQuadruple                 ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.plateQuintuple"              , OP.plateQuintuple                 ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.plateDense"                  , OP.plateDense                     ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.plateCurved"                 , OP.plateCurved                    ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		
		tItem = new PrefixItem(MD.GT, "gt.meta.scrapGt"                     , OP.scrapGt                        );
		tItem = new PrefixItem(MD.GT, "gt.meta.rockGt"                      , OP.rockGt                         ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.oreRaw"                      , OP.oreRaw                         ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		
		tItem = new PrefixItem(MD.GT, "gt.meta.gearGtSmall"                 , OP.gearGtSmall                    );
		tItem = new PrefixItem(MD.GT, "gt.meta.gearGt"                      , OP.gearGt                         );
		tItem = new PrefixItem(MD.GT, "gt.meta.rotor"                       , OP.rotor                          );
		tItem = new PrefixItem(MD.GT, "gt.meta.stick"                       , OP.stick                          ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("forester", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.stickLong"                   , OP.stickLong                      ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("forester", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.springSmall"                 , OP.springSmall                    );
		tItem = new PrefixItem(MD.GT, "gt.meta.spring"                      , OP.spring                         );
		tItem = new PrefixItem(MD.GT, "gt.meta.lens"                        , OP.lens                           );
		tItem = new PrefixItem(MD.GT, "gt.meta.round"                       , OP.round                          );
		tItem = new PrefixItem(MD.GT, "gt.meta.bolt"                        , OP.bolt                           );
		tItem = new PrefixItem(MD.GT, "gt.meta.screw"                       , OP.screw                          );
		tItem = new PrefixItemRing(MD.GT, "gt.meta.ring"                    , OP.ring                           );
		tItem = new PrefixItem(MD.GT, "gt.meta.chain"                       , OP.chain                          );
		tItem = new PrefixItem(MD.GT, "gt.meta.foil"                        , OP.foil                           ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.casingSmall"                 , OP.casingSmall                    );
		tItem = new PrefixItem(MD.GT, "gt.meta.wireFine"                    , OP.wireFine                       );
		tItem = new PrefixItem(MD.GT, "gt.meta.minecartWheels"              , OP.minecartWheels                 );
		tItem = new PrefixItem(MD.GT, "gt.meta.railGt"                      , OP.railGt                         );
		
		tItem = new PrefixItem(MD.GT, "gt.meta.plantGtBerry"                , OP.plantGtBerry                   );
		tItem = new PrefixItem(MD.GT, "gt.meta.plantGtBlossom"              , OP.plantGtBlossom                 );
		tItem = new PrefixItem(MD.GT, "gt.meta.plantGtFiber"                , OP.plantGtFiber                   );
		tItem = new PrefixItem(MD.GT, "gt.meta.plantGtTwig"                 , OP.plantGtTwig                    );
		tItem = new PrefixItem(MD.GT, "gt.meta.plantGtWart"                 , OP.plantGtWart                    );
		
		tItem = new PrefixItem(MD.GT, "gt.meta.chemtube"                    , OP.chemtube                       ); tItem.mCraftingSound = SFX.IC_TREETAP;
		OP.chemtube.mContainerItem = ST.make(tItem, 1, MT.Empty.mID);
		
		
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadRawSword"            , OP.toolHeadRawSword               );
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadSword"               , OP.toolHeadSword                  ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("hunter", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadRawPickaxe"          , OP.toolHeadRawPickaxe             );
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadPickaxe"             , OP.toolHeadPickaxe                ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadPickaxeGem"          , OP.toolHeadPickaxeGem             ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadConstructionPickaxe" , OP.toolHeadConstructionPickaxe    ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadBuilderwand"         , OP.toolHeadBuilderwand            ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadRawShovel"           , OP.toolHeadRawShovel              );
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadShovel"              , OP.toolHeadShovel                 ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadRawSpade"            , OP.toolHeadRawSpade               );
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadSpade"               , OP.toolHeadSpade                  ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadRawAxe"              , OP.toolHeadRawAxe                 );
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadAxe"                 , OP.toolHeadAxe                    ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("forester", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadRawAxeDouble"        , OP.toolHeadRawAxeDouble           );
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadAxeDouble"           , OP.toolHeadAxeDouble              ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("forester", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadRawHoe"              , OP.toolHeadRawHoe                 );
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadHoe"                 , OP.toolHeadHoe                    ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("forester", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadHammer"              , OP.toolHeadHammer                 ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadFile"                , OP.toolHeadFile                   ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadRawChisel"           , OP.toolHeadRawChisel              );
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadChisel"              , OP.toolHeadChisel                 ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadRawSaw"              , OP.toolHeadRawSaw                 );
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadSaw"                 , OP.toolHeadSaw                    ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("forester", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadDrill"               , OP.toolHeadDrill                  ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("miner", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadChainsaw"            , OP.toolHeadChainsaw               ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("forester", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadWrench"              , OP.toolHeadWrench                 ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadScrewdriver"         , OP.toolHeadScrewdriver            ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("builder", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadRawUniversalSpade"   , OP.toolHeadRawUniversalSpade      );
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadUniversalSpade"      , OP.toolHeadUniversalSpade         ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadRawSense"            , OP.toolHeadRawSense               );
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadSense"               , OP.toolHeadSense                  ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("forester", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadRawPlow"             , OP.toolHeadRawPlow                );
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadPlow"                , OP.toolHeadPlow                   ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("digger", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadBuzzSaw"             , OP.toolHeadBuzzSaw                ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("forester", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadRawArrow"            , OP.toolHeadRawArrow               ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("hunter", ST.make(tItem, 1, W));
		tItem = new PrefixItem(MD.GT, "gt.meta.toolHeadArrow"               , OP.toolHeadArrow                  ); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("hunter", ST.make(tItem, 1, W));
		tItem = new PrefixItemProjectile(MD.GT, "gt.meta.arrowGtWood"       , OP.arrowGtWood        , TD.Projectiles.ARROW          , EntityArrow_Material.class, 1.00F, 6.00F, T, T); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("hunter", ST.make(tItem, 1, W));
		tItem = new PrefixItemProjectile(MD.GT, "gt.meta.arrowGtPlastic"    , OP.arrowGtPlastic     , TD.Projectiles.ARROW          , EntityArrow_Material.class, 1.50F, 6.00F, T, T); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("hunter", ST.make(tItem, 1, W));
		tItem = new PrefixItemProjectile(MD.GT, "gt.meta.bulletGtSmall"     , OP.bulletGtSmall      , TD.Projectiles.BULLET_SMALL   , EntityArrow_Material.class, 1.50F, 3.00F, F, F); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("hunter", ST.make(tItem, 1, W));
		tItem = new PrefixItemProjectile(MD.GT, "gt.meta.bulletGtMedium"    , OP.bulletGtMedium     , TD.Projectiles.BULLET_MEDIUM  , EntityArrow_Material.class, 1.75F, 2.50F, F, F); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("hunter", ST.make(tItem, 1, W));
		tItem = new PrefixItemProjectile(MD.GT, "gt.meta.bulletGtLarge"     , OP.bulletGtLarge      , TD.Projectiles.BULLET_LARGE   , EntityArrow_Material.class, 2.00F, 2.00F, F, F); if (COMPAT_FR != null) COMPAT_FR.addToBackpacks("hunter", ST.make(tItem, 1, W));
	}
}
