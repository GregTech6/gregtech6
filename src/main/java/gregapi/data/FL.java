/**
 * Copyright (c) 2018 Gregorius Techneticies
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

package gregapi.data;

import static gregapi.data.CS.FluidsGT.*;

import java.util.Collection;

import gregapi.data.CS.FluidsGT;
import gregapi.util.UT;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 * 
 * Class containing all known Fluid Names
 */
@SuppressWarnings("unchecked")
public enum FL {
	  Error                     ("error")
	, UUM                       ("ic2uumatter"                                              , SIMPLE, LIQUID)
	, XP                        ("xpjuice"                                                  , SIMPLE, LIQUID)
	
	, Air                       ("air"                                                      , SIMPLE, GAS, AIR)
	, Air_Nether                ("netherair"                                                , SIMPLE, GAS, AIR)
	, Air_End                   ("enderair"                                                 , SIMPLE, GAS, AIR)
	, Oxygen                    ("oxygen"                                                   , SIMPLE, GAS)
	, Liquid_Oxygen             ("liquidoxygen"                                             , SIMPLE, LIQUID)
	
	, Hydrogen                  ("hydrogen"                                                 , GAS)
	, Deuterium                 ("deuterium"                                                , GAS)
	, Tritium                   ("tritium"                                                  , GAS)
	
	, Helium                    ("helium"                                                   , GAS)
	, Helium_3                  ("helium-3"                                                 , GAS)
	
	, Steam                     ("steam"                                                    , SIMPLE, GAS, STEAM, POWER_CONDUCTING)
	, Steam_IC2                 ("ic2steam"                                                 , SIMPLE, GAS, STEAM, POWER_CONDUCTING)
	, Steam_IC2_Superheated     ("ic2superheatedsteam"                                      , SIMPLE, GAS, STEAM, POWER_CONDUCTING)
	
	, Coolant_IC2               ("ic2coolant"                                               , SIMPLE, LIQUID)
	, Freezing_Ooze             ("ooze"                                                     , SIMPLE, LIQUID, BROKEN)
	
	, Lava                      ("lava"                                                     , SIMPLE, LIQUID)
	, Lava_Pahoehoe             ("ic2pahoehoelava"                                          , SIMPLE, LIQUID)
	, Lava_Pure                 ("purelava"                                                 , SIMPLE, LIQUID, BROKEN) // Lycanite Lava, Warning: Infinite like vanilla Water!
	
	, Water                     ("water"                                                    , SIMPLE, LIQUID, FOOD, WATER)
	, DistW                     ("ic2distilledwater"                                        , SIMPLE, LIQUID, FOOD, WATER)
	, River_Water               ("riverwater"                                               , SIMPLE, LIQUID, FOOD, WATER)
	, Ice                       ("ice"                                                      , SIMPLE, LIQUID, FOOD, WATER, THERMOS)
	, Mineralwater              ("potion.mineralwater"                                      , SIMPLE, LIQUID, FOOD)
	, Mineralsoda               ("mineralsoda"                                              , SIMPLE, LIQUID, FOOD)
	, Soda                      ("soda"                                                     , SIMPLE, LIQUID, FOOD)
	, Tropics_Water             ("tropicswater"                                             , SIMPLE, LIQUID)
	, Ocean                     ("seawater"                                                 , SIMPLE, LIQUID)
	, OceanGrC                  ("grccore.saltwater"                                        , SIMPLE, LIQUID)
	, Dirty_Water               ("waterdirty"                                               , SIMPLE, LIQUID)
	, Stagnant_Water            ("stagnantwater"                                            , SIMPLE, LIQUID)
	, Swampwater                ("swampwater"                                               , SIMPLE, LIQUID)
	, Saltwater                 ("saltwater"                                                , SIMPLE, LIQUID)
	, Holywater                 ("holywater"                                                , SIMPLE, LIQUID)
	
	, Milk                      ("milk"                                                     , SIMPLE, LIQUID, FOOD, MILK)
	, MilkSoy                   ("soymilk"                  , "potion.soymilk"              , SIMPLE, LIQUID, FOOD, MILK)
	, MilkGrC                   ("grcmilk.milk"                                             , SIMPLE, LIQUID, FOOD, MILK, NONSTANDARD)
	, Milk_Spoiled              ("spoiledmilk"                                              , SIMPLE, LIQUID, FOOD, MILK)
	
	, Honey                     ("for.honey"                                                , SIMPLE, LIQUID, FOOD, HONEY)
	, HoneyGrC                  ("grc.honey"                                                , SIMPLE, LIQUID, FOOD, HONEY, NONSTANDARD)
	, HoneyBoP                  ("honey"                                                    , SIMPLE, LIQUID, FOOD, HONEY, NONSTANDARD)
	, Honeydew                  ("honeydew"                 , "for.honeydew"                , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	, Ambrosia                  ("potion.ambrosia"                                          , SIMPLE, LIQUID, FOOD)
	
	, Biomass                   ("biomass"                                                  , SIMPLE, LIQUID)
	, BiomassIC2                ("ic2biomass"                                               , SIMPLE, LIQUID)
	
	, Lubricant                 ("lubricant"                                                , SIMPLE, LIQUID, LUBRICANT)
	, LubRoCant                 ("rc lubricant"                                             , SIMPLE, LIQUID, LUBRICANT)
	
	, Juice                     ("juice"                                                    , SIMPLE, LIQUID, FOOD, JUICE)
	, Juice_Kiwi                ("kiwijuice"                                                , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE, CITRUS_JUICE)
	, Juice_Lime                ("binnie.juicelime"                                         , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE, CITRUS_JUICE)
	, Juice_Lemon               ("binnie.juicelemon"        , "potion.lemonjuice"           , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE, CITRUS_JUICE)
	, Juice_Orange              ("binnie.juiceorange"                                       , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE, CITRUS_JUICE)
	, Juice_Persimmon           ("persimmonjuice"                                           , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE, CITRUS_JUICE)
	, Juice_Melon               ("melonjuice"                                               , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Currant             ("currantjuice"                                             , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Raspberry           ("raspberryjuice"                                           , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Blackberry          ("blackberryjuice"                                          , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Blueberry           ("blueberryjuice"                                           , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Gooseberry          ("gooseberryjuice"                                          , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Strawberry          ("strawberryjuice"          , "potion.strawberryjuice"      , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Plum                ("binnie.juiceplum"                                         , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Peach               ("binnie.juicepeach"                                        , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Elderberry          ("binnie.juiceelderberry"                                   , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Grapefruit          ("binnie.juicegrapefruit"                                   , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Apricot             ("binnie.juiceapricot"                                      , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Pear                ("binnie.juicepear"                                         , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Grape_Green         ("grapejuice"                                               , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Grape_Purple        ("grc.grapewine0"                                           , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Grape_Red           ("binnie.juiceredgrape"                                     , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Grape_White         ("binnie.juicewhitegrape"   , "potion.grapejuice"           , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Apple               ("binnie.juiceapple"        , "potion.applejuice"           , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_AppleGrC            ("grc.applecider0"                                          , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE, NONSTANDARD)
	, Juice_Ananas              ("binnie.juicepineapple"    , "pineapplejuice"              , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Banana              ("binnie.juicebanana"       , "potion.bananajuice"          , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Cherry              ("binnie.juicecherry"       , "cherryjuice"                 , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Cranberry           ("binnie.juicecranberry"    , "cranberryjuice"              , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_CactusFruit         ("cactusfruitjuice"                                         , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Mango               ("mangojuice"                                               , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Pomegranate         ("pomegranatejuice"                                         , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Starfruit           ("starfruitjuice"                                           , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Papaya              ("papayajuice"                                              , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Fig                 ("figjuice"                                                 , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Coconut             ("coconutmilk"                                              , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	, Juice_Date                ("datejuice"                                                , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
	
	, Smoothie_Fruit            ("fruitsmoothie"                                            , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Melon            ("melonsmoothie"                                            , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Kiwi             ("kiwismoothie"                                             , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Currant          ("currantsmoothie"                                          , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Raspberry        ("raspberrysmoothie"                                        , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Blackberry       ("blackberrysmoothie"                                       , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Blueberry        ("blueberrysmoothie"                                        , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Gooseberry       ("gooseberrysmoothie"                                       , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Strawberry       ("strawberrysmoothie"                                       , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Plum             ("plumsmoothie"                                             , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Peach            ("peachsmoothie"                                            , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Elderberry       ("elderberrysmoothie"                                       , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Grapefruit       ("grapefruitsmoothie"                                       , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Lime             ("limesmoothie"                                             , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Orange           ("orangesmoothie"                                           , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Persimmon        ("persimmonsmoothie"                                        , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Apricot          ("apricotsmoothie"                                          , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Pear             ("pearsmoothie"                                             , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Grape_Red        ("redgrapesmoothie"                                         , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Grape_White      ("whitegrapesmoothie"                                       , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Grape_Green      ("grapesmoothie"                                            , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Grape_Purple     ("purplegrapesmoothie"                                      , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Apple            ("applesmoothie"                                            , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Ananas           ("pineapplesmoothie"                                        , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Banana           ("bananasmoothie"                                           , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Cherry           ("cherrysmoothie"                                           , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Cranberry        ("cranberrysmoothie"                                        , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Lemon            ("lemonsmoothie"                                            , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Mango            ("mangosmoothie"                                            , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Pomegranate      ("pomegranatesmoothie"                                      , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Starfruit        ("starfruitsmoothie"                                        , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Papaya           ("papayasmoothie"                                           , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Fig              ("figsmoothie"                                              , SIMPLE, LIQUID, FOOD, THERMOS)
	, Smoothie_Coconut          ("coconutsmoothie"                                          , SIMPLE, LIQUID, FOOD, THERMOS)
	
	, Juice_Carrot              ("binnie.juicecarrot"       , "carrotjuice"                 , SIMPLE, LIQUID, FOOD, JUICE)
	, Juice_Tomato              ("binnie.juicetomato"       , "tomatojuice"                 , SIMPLE, LIQUID, FOOD, JUICE)
	, Juice_Beet                ("beetjuice"                                                , SIMPLE, LIQUID, FOOD, JUICE)
	, Juice_Pumpkin             ("pumpkinjuice"                                             , SIMPLE, LIQUID, FOOD, JUICE)
	, Juice_Cucumber            ("cucumberjuice"                                            , SIMPLE, LIQUID, FOOD, JUICE)
	, Juice_Onion               ("onionjuice"                                               , SIMPLE, LIQUID, FOOD, JUICE)
	, Juice_Potato              ("potatojuice"              , "potion.potatojuice"          , SIMPLE, LIQUID, FOOD, JUICE)
	, Juice_Reed                ("reedwater"                , "potion.reedwater"            , SIMPLE, LIQUID, FOOD, JUICE)
	, Juice_Cactus              ("cactuswater"                                              , SIMPLE, LIQUID, FOOD, JUICE)
	
	, Mash_Rice                 ("ricewater"                , "potion.ricewater"            , SIMPLE, LIQUID, FOOD)
	, Mash_Hops                 ("hopsmash"                 , "potion.hopsjuice"            , SIMPLE, LIQUID, FOOD)
	, Mash_WheatHops            ("wheathopsmash"            , "potion.wheatyhopsjuice"      , SIMPLE, LIQUID, FOOD)
	, Mash_Wheat                ("binnie.mashwheat"         , "potion.wheatyjuice"          , SIMPLE, LIQUID, FOOD)
	, Mash_Corn                 ("binnie.mashcorn"                                          , SIMPLE, LIQUID, FOOD)
	, Mash_Rye                  ("binnie.mashrye"                                           , SIMPLE, LIQUID, FOOD)
	, Mash_Grain                ("binnie.mashgrain"                                         , SIMPLE, LIQUID, FOOD)
	
	, Resin                     ("resin"                                                    , SIMPLE, LIQUID)
	, Resin_Spruce              ("spruceresin"                                              , SIMPLE, LIQUID)
	, Resin_Rubber              ("fluidrubbertreesap"                                       , SIMPLE, LIQUID)
	
	, Sap                       ("sap"                                                      , SIMPLE, LIQUID, FOOD)
	, Sap_Rainbow               ("rainbowsap"                                               , SIMPLE, LIQUID, FOOD)
	, Sap_Maple                 ("maplesap"                                                 , SIMPLE, LIQUID, FOOD)
	
	, Syrup_Maple               ("maplesyrup"                                               , SIMPLE, LIQUID, FOOD)
	
	, Nutbutter_Peanut          ("peanutbutter"                                             , SIMPLE, LIQUID, FOOD)
	
	, Cream                     ("grcmilk.cream"            , "cream"                       , SIMPLE, LIQUID, FOOD)
	, Cream_Chocolate           ("chocolatecream"                                           , SIMPLE, LIQUID, FOOD)
	, Cream_Coconut             ("coconutcream"                                             , SIMPLE, LIQUID, FOOD)
	, Cream_Nutella             ("nutella"                                                  , SIMPLE, LIQUID, FOOD)
	
	, Mayo                      ("mayo"                                                     , SIMPLE, LIQUID, FOOD)
	, Dressing                  ("potion.dressing"                                          , SIMPLE, LIQUID, FOOD)
	
	, Soup_Mushroom             ("mushroomsoup"                                             , SIMPLE, LIQUID, FOOD)
	
	, Sauce_Chili               ("chillysauce"              , "potion.chillysauce"          , SIMPLE, LIQUID, FOOD)
	, Sauce_Hot                 ("potion.hotsauce"                                          , SIMPLE, LIQUID, FOOD)
	, Sauce_Diabolo             ("potion.diabolosauce"                                      , SIMPLE, LIQUID, FOOD)
	, Sauce_Diablo              ("potion.diablosauce"                                       , SIMPLE, LIQUID, FOOD)
	, Sauce_Snitches            ("potion.diablosauce.strong"                                , SIMPLE, LIQUID, FOOD)
	
	, Slime_Pink                ("pinkslime"                                                , SIMPLE, LIQUID, FOOD, SLIME)
	, Slime_Green               ("slime"                                                    , SIMPLE, LIQUID, FOOD, SLIME)
	, BAWLS                     ("bawls"                                                    , SIMPLE, LIQUID, FOOD)
	
	, Tea                       ("tea"                      , "potion.tea"                  , SIMPLE, LIQUID, FOOD, THERMOS, TEA)
	, Tea_Sweet                 ("sweettea"                 , "potion.sweettea"             , SIMPLE, LIQUID, FOOD, THERMOS, TEA)
	, Tea_Ice                   ("icetea"                   , "potion.icetea"               , SIMPLE, LIQUID, FOOD, THERMOS, TEA)
	
	, Purple_Drink              ("purpledrink"              , "potion.purpledrink"          , SIMPLE, LIQUID, FOOD)
	, Lemonade                  ("potion.lemonade"                                          , SIMPLE, LIQUID, FOOD)
	, Grenade_Juice             ("potion.cavejohnsonsgrenadejuice"                          , SIMPLE, LIQUID, FOOD)
	
	, Vinegar_Grape             ("vinegar"                  , "potion.vinegar"              , SIMPLE, LIQUID, FOOD, ALCOHOLIC, VINEGAR)
	, Vinegar_Apple             ("applevinegar"                                             , SIMPLE, LIQUID, FOOD, ALCOHOLIC, VINEGAR)
	, Vinegar_Cane              ("canevinegar"                                              , SIMPLE, LIQUID, FOOD, ALCOHOLIC, VINEGAR)
	, Vinegar_Rice              ("ricevinegar"                                              , SIMPLE, LIQUID, FOOD, ALCOHOLIC, VINEGAR)
	
	, Wine_Fruit                ("binnie.juice"                                             , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Lemon                ("limoncello"               , "potion.limoncello"           , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Agave                ("binnie.wineagave"                                         , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Apricot              ("binnie.wineapricot"                                       , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Banana               ("binnie.winebanana"                                        , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Carrot               ("binnie.winecarrot"                                        , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Cherry               ("binnie.winecherry"                                        , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Citrus               ("binnie.winecitrus"                                        , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Cranberry            ("binnie.winecranberry"                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Elderberry           ("binnie.wineelderberry"                                    , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Plum                 ("binnie.wineplum"                                          , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Sparkling            ("binnie.winesparkling"                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Tomato               ("binnie.winetomato"                                        , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Grape_Green          ("wine"                                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Grape_Purple         ("ricardosanchez"                                           , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Grape_Red            ("binnie.winered"           , "potion.wine"                 , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Grape_White          ("binnie.winewhite"                                         , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	, Wine_Fortified            ("binnie.winefortified"                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WINE)
	
	, Whiskey                   ("binnie.whiskey"           , "whiskey"                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WHISKEY)
	, Whiskey_Rye               ("binnie.whiskeyrye"                                        , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WHISKEY)
	, Whiskey_Corn              ("binnie.whiskeycorn"                                       , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WHISKEY)
	, Whiskey_Scotch            ("binnie.whiskeywheat"      , "potion.scotch"               , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WHISKEY)
	, Whiskey_GlenMcKenner      ("glenmckenner"             , "potion.glenmckenner"         , SIMPLE, LIQUID, FOOD, ALCOHOLIC, WHISKEY)
	
	, Liqueur_Chocolate         ("binnie.liqueurchocolate"  , "liqueur"                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Almond            ("binnie.liqueuralmond"                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Anise             ("binnie.liqueuranise"                                      , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Banana            ("binnie.liqueurbanana"                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Blackberry        ("binnie.liqueurblackberry"                                 , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Blackcurrant      ("binnie.liqueurblackcurrant"                               , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Cherry            ("binnie.liqueurcherry"                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Cinnamon          ("binnie.liqueurcinnamon"                                   , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Coffee            ("binnie.liqueurcoffee"                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Hazelnut          ("binnie.liqueurhazelnut"                                   , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Herbal            ("binnie.liqueurherbal"                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Lemon             ("binnie.liqueurlemon"                                      , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Melon             ("binnie.liqueurmelon"                                      , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Mint              ("binnie.liqueurmint"                                       , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Orange            ("binnie.liqueurorange"                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Peach             ("binnie.liqueurpeach"                                      , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	, Liqueur_Raspberry         ("binnie.liqueurraspberry"                                  , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUEUR)
	
	, Liquor                    ("binnie.liquorfruit"       , "liquor"                      , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUOR)
	, Liquor_Apple              ("binnie.liquorapple"                                       , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUOR)
	, Liquor_Apricot            ("binnie.liquorapricot"                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUOR)
	, Liquor_Cherry             ("binnie.liquorcherry"                                      , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUOR)
	, Liquor_Elderberry         ("binnie.liquorelderberry"                                  , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUOR)
	, Liquor_Pear               ("binnie.liquorpear"                                        , SIMPLE, LIQUID, FOOD, ALCOHOLIC, LIQUOR)
	
	, Spirit_Gin                ("binnie.spiritgin"         , "gin"                         , SIMPLE, LIQUID, FOOD, ALCOHOLIC, SPIRIT)
	, Spirit_Cane               ("binnie.spiritneutral"                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, SPIRIT)
	, Spirit_Neutral            ("binnie.spiritsugarcane"                                   , SIMPLE, LIQUID, FOOD, ALCOHOLIC, SPIRIT)
	
	, Brandy                    ("binnie.brandyfruit"       , "brandy"                      , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BRANDY)
	, Brandy_Apple              ("binnie.brandyapple"                                       , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BRANDY)
	, Brandy_Apricot            ("binnie.brandyapricot"                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BRANDY)
	, Brandy_Cherry             ("binnie.brandycherry"                                      , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BRANDY)
	, Brandy_Citrus             ("binnie.brandycitrus"                                      , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BRANDY)
	, Brandy_Elderberry         ("binnie.brandyelderberry"                                  , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BRANDY)
	, Brandy_Grape              ("binnie.brandygrape"                                       , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BRANDY)
	, Brandy_Pear               ("binnie.brandypear"                                        , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BRANDY)
	, Brandy_Plum               ("binnie.brandyplum"                                        , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BRANDY)
	
	, Cider_Apple               ("binnie.ciderapple"        , "potion.cider", "cider"       , SIMPLE, LIQUID, FOOD, ALCOHOLIC, CIDER)
	, Cider_Pear                ("binnie.ciderpear"                                         , SIMPLE, LIQUID, FOOD, ALCOHOLIC, CIDER)
	, Cider_Peach               ("binnie.ciderpeach"                                        , SIMPLE, LIQUID, FOOD, ALCOHOLIC, CIDER)
	, Cider_Ananas              ("binnie.winepineapple"                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC, CIDER)
	
	, Beer                      ("beer"                     , "potion.beer"                 , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BEER)
	, Beer_Dark                 ("darkbeer"                 , "potion.darkbeer"             , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BEER)
	, Beer_Dragonblood          ("potion.dragonblood"                                       , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BEER)
	, Beer_Ale                  ("binnie.beerale"                                           , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BEER)
	, Beer_Corn                 ("binnie.beercorn"                                          , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BEER)
	, Beer_Lager                ("binnie.beerlager"                                         , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BEER)
	, Beer_Rye                  ("binnie.beerrye"                                           , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BEER)
	, Beer_Stout                ("binnie.beerstout"                                         , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BEER)
	, Beer_Wheat                ("binnie.beerwheat"                                         , SIMPLE, LIQUID, FOOD, ALCOHOLIC, BEER)
	
	, Rum_White                 ("binnie.rumwhite"          , "potion.rum", "rum"           , SIMPLE, LIQUID, FOOD, ALCOHOLIC, RUM)
	, Rum_Dark                  ("binnie.rumdark"           , "potion.piratebrew"           , SIMPLE, LIQUID, FOOD, ALCOHOLIC, RUM)
	
	, Vodka                     ("binnie.vodka"             , "potion.vodka", "vodka"       , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	, Leninade                  ("potion.leninade"                                          , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	, Mead                      ("short.mead"               , "mead"                        , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	, Sake                      ("potion.sake"                                              , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	, Tequila                   ("binnie.tequila"           , "tequila"                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	, Alcopops                  ("potion.alcopops"                                          , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	
	, Oil_Sunflower             ("sunfloweroil"                                             , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Olive                 ("binnie.juiceolive"        , "oliveoil"                    , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Nut                   ("nutoil"                                                   , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Lin                   ("linoil"                   , "linseedoil"                  , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Hemp                  ("hempoil"                  , "hempseedoil"                 , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Seed                  ("seedoil"                                                  , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Fish                  ("fishoil"                                                  , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Frying                ("hotfryingoil"                                             , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Canola                ("canolaoil"                                                , SIMPLE, LIQUID)
	, Oil_Plant                 ("plantoil"                                                 , SIMPLE, LIQUID)
	, Oil_Creosote              ("creosote"                                                 , SIMPLE, LIQUID, BATH)
	, Oil_Soulsand              ("soulsandoil"                                              , SIMPLE, LIQUID)
	, Oil_Normal                ("oil"                      , "oilgc"                       , SIMPLE, LIQUID)
	, Oil_Light                 ("liquid_light_oil"                                         , SIMPLE, LIQUID)
	, Oil_Medium                ("liquid_medium_oil"                                        , SIMPLE, LIQUID)
	, Oil_Heavy                 ("liquid_heavy_oil"                                         , SIMPLE, LIQUID)
	, Oil_ExtraHeavy            ("liquid_extra_heavy_oil"                                   , SIMPLE, LIQUID)
	, Oil_Light2                ("lightoil"                                                 , SIMPLE, LIQUID)
	, Oil_Heavy2                ("heavyoil"                                                 , SIMPLE, LIQUID)
	, Oil_HotCrude              ("hotcrude"                                                 , SIMPLE, LIQUID)
	
	, Methane                   ("methane"                                                  , SIMPLE, GAS)
	, Propane                   ("propane"                                                  , SIMPLE, GAS)
	, Butane                    ("butane"                                                   , SIMPLE, GAS)
	, Ethylene                  ("ethylene"                 , "ethene"                      , GAS)
	, Propylene                 ("propylene"                , "propene"                     , GAS)
	, Petrol                    ("petrol"                   , "gasoline"                    , SIMPLE, LIQUID)
	, Fuel                      ("fuel"                                                     , SIMPLE, LIQUID)
	, Diesel                    ("diesel"                                                   , SIMPLE, LIQUID)
	, Kerosine                  ("kerosine"                 , "kerosene"                    , SIMPLE, LIQUID)
	, LPG                       ("lpg"                                                      , LIQUID)
	
	, BioFuel                   ("biofuel"                                                  , SIMPLE, LIQUID)
	, BioDiesel                 ("biodiesel"                                                , SIMPLE, LIQUID)
	, BioEthanol                ("bioethanol"                                               , SIMPLE, LIQUID)
	
	, Glue                      ("glue"                                                     , SIMPLE, LIQUID)
	, Latex                     ("latex"                    , "molten.latex"                , SIMPLE, LIQUID)
	, Concrete                  ("concrete"                 , "molten.concrete"             , SIMPLE, LIQUID)
	, CFoam                     ("ic2constructionfoam"                                      , LIQUID) // 100 per Unit
	, Sewage                    ("sewage"                                                   , SIMPLE, LIQUID)
	, Sludge                    ("sludge"                                                   , SIMPLE, LIQUID)
	, Glass                     ("glass"                    , "molten.glass"                , SIMPLE, LIQUID)
	, Sluice                    ("sluicejuice"                                              , SIMPLE, LIQUID)
	
	, Myst_Ink                  ("myst.ink.black"                                           , SIMPLE, LIQUID)
	
	, Blaze                     ("blaze"                    , "molten.blaze"                , LIQUID) // 144 per Unit
	, FieryBlood                ("fieryblood"                                               , LIQUID) // 144 per Unit
	, FieryTears                ("fierytears"                                               , LIQUID) // 144 per Unit
	, Mana_TE                   ("mana"                                                     , LIQUID) // 250 per Unit
	, Ender                     ("molten.enderpearl"                                        , LIQUID) // 144 per Unit
	, Ender_TE                  ("ender"                                                    , LIQUID) // 250 per Unit
	, Redstone                  ("molten.redstone"                                          , LIQUID) // 144 per Unit
	, Redstone_TE               ("redstone"                                                 , LIQUID) // 100 per Unit
	, Glowstone_TE              ("glowstone"                                                , GAS) // 250 per Unit
	
	, Calcite                   ("molten.calcite"                                           , LIQUID) // 144 per Unit
	
	, Med_Heal                  ("medicine.heal"                                            , SIMPLE, LIQUID, BATH)
	, Med_Laxative              ("medicine.laxative"                                        , SIMPLE, LIQUID, BATH)
	
	, Rotten_Drink              ("rottendrink"                                              , SIMPLE, LIQUID, FOOD)
	
	, Dragon_Breath             ("dragonbreath"                                             , SIMPLE, GAS, BATH)
	
	, Potion_Tainted            ("potion.tainted"                                           , SIMPLE, LIQUID, POTION, BATH)
	, Potion_Awkward            ("potion.awkward"                                           , SIMPLE, LIQUID, POTION, BATH)
	, Potion_Thick              ("potion.thick"                                             , SIMPLE, LIQUID, POTION, BATH)
	, Potion_Mundane            ("potion.mundane"                                           , SIMPLE, LIQUID, POTION, BATH)
	, Potion_Harm_1             ("potion.damage"                                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Harm_2             ("potion.damage.strong"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Harm_1S            ("potion.damage.splash"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Harm_2S            ("potion.damage.strong.splash"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Harm_1D            ("potion.damage.lingering"                                  , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Harm_2D            ("potion.damage.strong.lingering"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Heal_1             ("potion.health"                                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Heal_2             ("potion.health.strong"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Heal_1S            ("potion.health.splash"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Heal_2S            ("potion.health.strong.splash"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Heal_1D            ("potion.health.lingering"                                  , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Heal_2D            ("potion.health.strong.lingering"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Jump_1             ("potion.jump"                                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Jump_2             ("potion.jump.strong"                                       , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Jump_1S            ("potion.jump.splash"                                       , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Jump_2S            ("potion.jump.strong.splash"                                , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Jump_1D            ("potion.jump.lingering"                                    , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Jump_2D            ("potion.jump.strong.lingering"                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Speed_1            ("potion.speed"                                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Speed_2            ("potion.speed.strong"                                      , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Speed_1L           ("potion.speed.long"                                        , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Speed_1S           ("potion.speed.splash"                                      , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Speed_2S           ("potion.speed.strong.splash"                               , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Speed_1LS          ("potion.speed.long.splash"                                 , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Speed_1D           ("potion.speed.lingering"                                   , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Speed_2D           ("potion.speed.strong.lingering"                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Speed_1LD          ("potion.speed.long.lingering"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Strength_1         ("potion.strength"                                          , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Strength_2         ("potion.strength.strong"                                   , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Strength_1L        ("potion.strength.long"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Strength_1S        ("potion.strength.splash"                                   , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Strength_2S        ("potion.strength.strong.splash"                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Strength_1LS       ("potion.strength.long.splash"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Strength_1D        ("potion.strength.lingering"                                , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Strength_2D        ("potion.strength.strong.lingering"                         , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Strength_1LD       ("potion.strength.long.lingering"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Regen_1            ("potion.regen"                                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Regen_2            ("potion.regen.strong"                                      , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Regen_1L           ("potion.regen.long"                                        , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Regen_1S           ("potion.regen.splash"                                      , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Regen_2S           ("potion.regen.strong.splash"                               , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Regen_1LS          ("potion.regen.long.splash"                                 , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Regen_1D           ("potion.regen.lingering"                                   , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Regen_2D           ("potion.regen.strong.lingering"                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Regen_1LD          ("potion.regen.long.lingering"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Poison_1           ("potion.poison"                                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Poison_2           ("potion.poison.strong"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Poison_1L          ("potion.poison.long"                                       , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Poison_1S          ("potion.poison.splash"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Poison_2S          ("potion.poison.strong.splash"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Poison_1LS         ("potion.poison.long.splash"                                , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Poison_1D          ("potion.poison.lingering"                                  , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Poison_2D          ("potion.poison.strong.lingering"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Poison_1LD         ("potion.poison.long.lingering"                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_FireResistance_1   ("potion.fireresistance"                                    , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_FireResistance_1L  ("potion.fireresistance.long"                               , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_FireResistance_1S  ("potion.fireresistance.splash"                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_FireResistance_1LS ("potion.fireresistance.long.splash"                        , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_FireResistance_1D  ("potion.fireresistance.lingering"                          , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_FireResistance_1LD ("potion.fireresistance.long.lingering"                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_NightVision_1      ("potion.nightvision"                                       , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_NightVision_1L     ("potion.nightvision.long"                                  , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_NightVision_1S     ("potion.nightvision.splash"                                , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_NightVision_1LS    ("potion.nightvision.long.splash"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_NightVision_1D     ("potion.nightvision.lingering"                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_NightVision_1LD    ("potion.nightvision.long.lingering"                        , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Weakness_1         ("potion.weakness"                                          , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Weakness_1L        ("potion.weakness.long"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Weakness_1S        ("potion.weakness.splash"                                   , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Weakness_1LS       ("potion.weakness.long.splash"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Weakness_1D        ("potion.weakness.lingering"                                , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Weakness_1LD       ("potion.weakness.long.lingering"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Slowness_1         ("potion.slowness"                                          , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Slowness_1L        ("potion.slowness.long"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Slowness_1S        ("potion.slowness.splash"                                   , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Slowness_1LS       ("potion.slowness.long.splash"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Slowness_1D        ("potion.slowness.lingering"                                , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Slowness_1LD       ("potion.slowness.long.lingering"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_WaterBreathing_1   ("potion.waterbreathing"                                    , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_WaterBreathing_1L  ("potion.waterbreathing.long"                               , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_WaterBreathing_1S  ("potion.waterbreathing.splash"                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_WaterBreathing_1LS ("potion.waterbreathing.long.splash"                        , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_WaterBreathing_1D  ("potion.waterbreathing.lingering"                          , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_WaterBreathing_1LD ("potion.waterbreathing.long.lingering"                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Invisibility_1     ("potion.invisibility"                                      , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Invisibility_1L    ("potion.invisibility.long"                                 , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Invisibility_1S    ("potion.invisibility.splash"                               , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Invisibility_1LS   ("potion.invisibility.long.splash"                          , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Invisibility_1D    ("potion.invisibility.lingering"                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Invisibility_1LD   ("potion.invisibility.long.lingering"                       , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	;
	
	public final String mName;
	
	private FL(String aName, Collection<String>... aFluidSets) {
		mName = aName;
		for (Collection<String> aFluidSet : aFluidSets) {aFluidSet.add(mName);}
	}
	private FL(String aName, String aOldName, Collection<String>... aFluidSets) {
		mName = aName;
		FluidsGT.NONSTANDARD.add(aOldName);
		FluidsGT.FLUID_RENAMINGS.put(aOldName, mName);
		for (Collection<String> aFluidSet : aFluidSets) {aFluidSet.add(mName); aFluidSet.add(aOldName);}
	}
	private FL(String aName, String aOldName1, String aOldName2, Collection<String>... aFluidSets) {
		mName = aName;
		FluidsGT.NONSTANDARD.add(aOldName1);
		FluidsGT.NONSTANDARD.add(aOldName2);
		FluidsGT.FLUID_RENAMINGS.put(aOldName1, mName);
		FluidsGT.FLUID_RENAMINGS.put(aOldName2, mName);
		for (Collection<String> aFluidSet : aFluidSets) {aFluidSet.add(mName); aFluidSet.add(aOldName1); aFluidSet.add(aOldName2);}
	}
	
	public static FluidStack lube(long aAmount) {return LubRoCant.make(aAmount, Lubricant);}
	
	public int id() {return FluidRegistry.getFluidID(mName);}
	public Fluid fluid() {return FluidRegistry.getFluid(mName);}
	public boolean exists() {return FluidRegistry.getFluid(mName) != null;}
	
	public FluidStack make (long aAmount) {return UT.Fluids.make (mName, aAmount);}
	public FluidStack make_(long aAmount) {return UT.Fluids.make_(mName, aAmount);}
	public FluidStack make (long aAmount, String aReplacement) {return UT.Fluids.make (mName, aAmount, aReplacement);}
	public FluidStack make_(long aAmount, String aReplacement) {return UT.Fluids.make_(mName, aAmount, aReplacement);}
	public FluidStack make (long aAmount, FL aReplacement) {return UT.Fluids.make (mName, aAmount, aReplacement.mName);}
	public FluidStack make_(long aAmount, FL aReplacement) {return UT.Fluids.make_(mName, aAmount, aReplacement.mName);}
	public FluidStack make (long aAmount, String aReplacement, long aReplacementAmount) {return UT.Fluids.make (mName, aAmount, aReplacement, aReplacementAmount);}
	public FluidStack make_(long aAmount, String aReplacement, long aReplacementAmount) {return UT.Fluids.make_(mName, aAmount, aReplacement, aReplacementAmount);}
	public FluidStack make (long aAmount, FL aReplacement, long aReplacementAmount) {return UT.Fluids.make (mName, aAmount, aReplacement.mName, aReplacementAmount);}
	public FluidStack make_(long aAmount, FL aReplacement, long aReplacementAmount) {return UT.Fluids.make_(mName, aAmount, aReplacement.mName, aReplacementAmount);}
	
	public boolean is(IFluidTank aTank) {return is(aTank.getFluid());}
	public boolean is(FluidStack aFluid) {return aFluid != null && is(aFluid.getFluid());}
	public boolean is(Fluid aFluid) {return aFluid != null && aFluid.getName().equalsIgnoreCase(mName);}
	public boolean is(String aFluidName) {return aFluidName != null && aFluidName.equalsIgnoreCase(mName);}
	public boolean is(Collection<String> aFluidSet) {return aFluidSet.contains(mName);}
}
