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
		
		new PrefixItem(MD.GT, "gt.meta.dust"                        , OP.dust                           );
		new PrefixItem(MD.GT, "gt.meta.dustSmall"                   , OP.dustSmall                      );
		new PrefixItem(MD.GT, "gt.meta.dustTiny"                    , OP.dustTiny                       );
		new PrefixItem(MD.GT, "gt.meta.dustDiv72"                   , OP.dustDiv72                      );
		new PrefixItem(MD.GT, "gt.meta.dustImpure"                  , OP.dustImpure                     );
		new PrefixItem(MD.GT, "gt.meta.dustPure"                    , OP.dustPure                       );
		new PrefixItem(MD.GT, "gt.meta.crushed"                     , OP.crushed                        );
		new PrefixItem(MD.GT, "gt.meta.crushedTiny"                 , OP.crushedTiny                    );
		new PrefixItem(MD.GT, "gt.meta.crushedPurified"             , OP.crushedPurified                );
		new PrefixItem(MD.GT, "gt.meta.crushedPurifiedTiny"         , OP.crushedPurifiedTiny            );
		new PrefixItem(MD.GT, "gt.meta.crushedCentrifuged"          , OP.crushedCentrifuged             );
		new PrefixItem(MD.GT, "gt.meta.crushedCentrifugedTiny"      , OP.crushedCentrifugedTiny         );
		
		new PrefixItem(MD.GT, "gt.meta.gemChipped"                  , OP.gemChipped                     );
		new PrefixItem(MD.GT, "gt.meta.gemFlawed"                   , OP.gemFlawed                      );
		new PrefixItem(MD.GT, "gt.meta.gem"                         , OP.gem                            );
		new PrefixItem(MD.GT, "gt.meta.gemFlawless"                 , OP.gemFlawless                    );
		new PrefixItem(MD.GT, "gt.meta.gemExquisite"                , OP.gemExquisite                   );
		new PrefixItem(MD.GT, "gt.meta.gemLegendary"                , OP.gemLegendary                   );
		new PrefixItem(MD.GT, "gt.meta.boule"                       , OP.bouleGt                        );
		
		new PrefixItem(MD.GT, "gt.meta.nugget"                      , OP.nugget                         );
		new PrefixItem(MD.GT, "gt.meta.chunkGt"                     , OP.chunkGt                        );
		new PrefixItem(MD.GT, "gt.meta.ingot"                       , OP.ingot                          );
		new PrefixItem(MD.GT, "gt.meta.ingotHot"                    , OP.ingotHot                       );
		new PrefixItem(MD.GT, "gt.meta.ingotDouble"                 , OP.ingotDouble                    );
		new PrefixItem(MD.GT, "gt.meta.ingotTriple"                 , OP.ingotTriple                    );
		new PrefixItem(MD.GT, "gt.meta.ingotQuadruple"              , OP.ingotQuadruple                 );
		new PrefixItem(MD.GT, "gt.meta.ingotQuintuple"              , OP.ingotQuintuple                 );
		
		new PrefixItem(MD.GT, "gt.meta.plateGemTiny"                , OP.plateGemTiny                   );
		new PrefixItem(MD.GT, "gt.meta.plateGem"                    , OP.plateGem                       );
		new PrefixItem(MD.GT, "gt.meta.plateTiny"                   , OP.plateTiny                      );
		new PrefixItem(MD.GT, "gt.meta.plate"                       , OP.plate                          );
		new PrefixItem(MD.GT, "gt.meta.plateDouble"                 , OP.plateDouble                    );
		new PrefixItem(MD.GT, "gt.meta.plateTriple"                 , OP.plateTriple                    );
		new PrefixItem(MD.GT, "gt.meta.plateQuadruple"              , OP.plateQuadruple                 );
		new PrefixItem(MD.GT, "gt.meta.plateQuintuple"              , OP.plateQuintuple                 );
		new PrefixItem(MD.GT, "gt.meta.plateDense"                  , OP.plateDense                     );
		new PrefixItem(MD.GT, "gt.meta.plateCurved"                 , OP.plateCurved                    );
		
		new PrefixItem(MD.GT, "gt.meta.scrapGt"                     , OP.scrapGt                        );
		new PrefixItem(MD.GT, "gt.meta.rockGt"                      , OP.rockGt                         );
		new PrefixItem(MD.GT, "gt.meta.oreRaw"                      , OP.oreRaw                         );
		
		new PrefixItem(MD.GT, "gt.meta.gearGtSmall"                 , OP.gearGtSmall                    );
		new PrefixItem(MD.GT, "gt.meta.gearGt"                      , OP.gearGt                         );
		new PrefixItem(MD.GT, "gt.meta.rotor"                       , OP.rotor                          );
		new PrefixItem(MD.GT, "gt.meta.stick"                       , OP.stick                          );
		new PrefixItem(MD.GT, "gt.meta.stickLong"                   , OP.stickLong                      );
		new PrefixItem(MD.GT, "gt.meta.springSmall"                 , OP.springSmall                    );
		new PrefixItem(MD.GT, "gt.meta.spring"                      , OP.spring                         );
		new PrefixItem(MD.GT, "gt.meta.lens"                        , OP.lens                           );
		new PrefixItem(MD.GT, "gt.meta.round"                       , OP.round                          );
		new PrefixItem(MD.GT, "gt.meta.bolt"                        , OP.bolt                           );
		new PrefixItem(MD.GT, "gt.meta.screw"                       , OP.screw                          );
		new PrefixItemRing(MD.GT, "gt.meta.ring"                    , OP.ring                           );
		new PrefixItem(MD.GT, "gt.meta.chain"                       , OP.chain                          );
		new PrefixItem(MD.GT, "gt.meta.foil"                        , OP.foil                           );
		new PrefixItem(MD.GT, "gt.meta.casingSmall"                 , OP.casingSmall                    );
		new PrefixItem(MD.GT, "gt.meta.wireFine"                    , OP.wireFine                       );
		new PrefixItem(MD.GT, "gt.meta.minecartWheels"              , OP.minecartWheels                 );
		new PrefixItem(MD.GT, "gt.meta.railGt"                      , OP.railGt                         );
		
		new PrefixItem(MD.GT, "gt.meta.plantGtBerry"                , OP.plantGtBerry                   );
		new PrefixItem(MD.GT, "gt.meta.plantGtBlossom"              , OP.plantGtBlossom                 );
		new PrefixItem(MD.GT, "gt.meta.plantGtFiber"                , OP.plantGtFiber                   );
		new PrefixItem(MD.GT, "gt.meta.plantGtTwig"                 , OP.plantGtTwig                    );
		new PrefixItem(MD.GT, "gt.meta.plantGtWart"                 , OP.plantGtWart                    );
		
		tItem =
		new PrefixItem(MD.GT, "gt.meta.chemtube"                    , OP.chemtube                       );
		OP.chemtube.mContainerItem = ST.make(tItem, 1, MT.Empty.mID);
		tItem.mCraftingSound = MD.IC2.mID.toLowerCase() + ":" + "tools.Treetap";
		
		new PrefixItem(MD.GT, "gt.meta.toolHeadRawSword"            , OP.toolHeadRawSword               );
		new PrefixItem(MD.GT, "gt.meta.toolHeadSword"               , OP.toolHeadSword                  );
		new PrefixItem(MD.GT, "gt.meta.toolHeadRawPickaxe"          , OP.toolHeadRawPickaxe             );
		new PrefixItem(MD.GT, "gt.meta.toolHeadPickaxe"             , OP.toolHeadPickaxe                );
		new PrefixItem(MD.GT, "gt.meta.toolHeadPickaxeGem"          , OP.toolHeadPickaxeGem             );
		new PrefixItem(MD.GT, "gt.meta.toolHeadConstructionPickaxe" , OP.toolHeadConstructionPickaxe    );
		new PrefixItem(MD.GT, "gt.meta.toolHeadBuilderwand"         , OP.toolHeadBuilderwand            );
		new PrefixItem(MD.GT, "gt.meta.toolHeadRawShovel"           , OP.toolHeadRawShovel              );
		new PrefixItem(MD.GT, "gt.meta.toolHeadShovel"              , OP.toolHeadShovel                 );
		new PrefixItem(MD.GT, "gt.meta.toolHeadRawSpade"            , OP.toolHeadRawSpade               );
		new PrefixItem(MD.GT, "gt.meta.toolHeadSpade"               , OP.toolHeadSpade                  );
		new PrefixItem(MD.GT, "gt.meta.toolHeadRawAxe"              , OP.toolHeadRawAxe                 );
		new PrefixItem(MD.GT, "gt.meta.toolHeadAxe"                 , OP.toolHeadAxe                    );
		new PrefixItem(MD.GT, "gt.meta.toolHeadRawAxeDouble"        , OP.toolHeadRawAxeDouble           );
		new PrefixItem(MD.GT, "gt.meta.toolHeadAxeDouble"           , OP.toolHeadAxeDouble              );
		new PrefixItem(MD.GT, "gt.meta.toolHeadRawHoe"              , OP.toolHeadRawHoe                 );
		new PrefixItem(MD.GT, "gt.meta.toolHeadHoe"                 , OP.toolHeadHoe                    );
		new PrefixItem(MD.GT, "gt.meta.toolHeadHammer"              , OP.toolHeadHammer                 );
		new PrefixItem(MD.GT, "gt.meta.toolHeadFile"                , OP.toolHeadFile                   );
		new PrefixItem(MD.GT, "gt.meta.toolHeadRawChisel"           , OP.toolHeadRawChisel              );
		new PrefixItem(MD.GT, "gt.meta.toolHeadChisel"              , OP.toolHeadChisel                 );
		new PrefixItem(MD.GT, "gt.meta.toolHeadRawSaw"              , OP.toolHeadRawSaw                 );
		new PrefixItem(MD.GT, "gt.meta.toolHeadSaw"                 , OP.toolHeadSaw                    );
		new PrefixItem(MD.GT, "gt.meta.toolHeadDrill"               , OP.toolHeadDrill                  );
		new PrefixItem(MD.GT, "gt.meta.toolHeadChainsaw"            , OP.toolHeadChainsaw               );
		new PrefixItem(MD.GT, "gt.meta.toolHeadWrench"              , OP.toolHeadWrench                 );
		new PrefixItem(MD.GT, "gt.meta.toolHeadScrewdriver"         , OP.toolHeadScrewdriver            );
		new PrefixItem(MD.GT, "gt.meta.toolHeadRawUniversalSpade"   , OP.toolHeadRawUniversalSpade      );
		new PrefixItem(MD.GT, "gt.meta.toolHeadUniversalSpade"      , OP.toolHeadUniversalSpade         );
		new PrefixItem(MD.GT, "gt.meta.toolHeadRawSense"            , OP.toolHeadRawSense               );
		new PrefixItem(MD.GT, "gt.meta.toolHeadSense"               , OP.toolHeadSense                  );
		new PrefixItem(MD.GT, "gt.meta.toolHeadRawPlow"             , OP.toolHeadRawPlow                );
		new PrefixItem(MD.GT, "gt.meta.toolHeadPlow"                , OP.toolHeadPlow                   );
		new PrefixItem(MD.GT, "gt.meta.toolHeadBuzzSaw"             , OP.toolHeadBuzzSaw                );
		new PrefixItem(MD.GT, "gt.meta.toolHeadRawArrow"            , OP.toolHeadRawArrow               );
		new PrefixItem(MD.GT, "gt.meta.toolHeadArrow"               , OP.toolHeadArrow                  );
		new PrefixItemProjectile(MD.GT, "gt.meta.arrowGtWood"       , OP.arrowGtWood        , TD.Projectiles.ARROW          , EntityArrow_Material.class, 1.00F, 6.00F, T, T);
		new PrefixItemProjectile(MD.GT, "gt.meta.arrowGtPlastic"    , OP.arrowGtPlastic     , TD.Projectiles.ARROW          , EntityArrow_Material.class, 1.50F, 6.00F, T, T);
		new PrefixItemProjectile(MD.GT, "gt.meta.bulletGtSmall"     , OP.bulletGtSmall      , TD.Projectiles.BULLET_SMALL   , EntityArrow_Material.class, 1.50F, 3.00F, F, F);
		new PrefixItemProjectile(MD.GT, "gt.meta.bulletGtMedium"    , OP.bulletGtMedium     , TD.Projectiles.BULLET_MEDIUM  , EntityArrow_Material.class, 1.75F, 2.50F, F, F);
		new PrefixItemProjectile(MD.GT, "gt.meta.bulletGtLarge"     , OP.bulletGtLarge      , TD.Projectiles.BULLET_LARGE   , EntityArrow_Material.class, 2.00F, 2.00F, F, F);
	}
}
