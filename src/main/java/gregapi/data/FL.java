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

package gregapi.data;

import static gregapi.data.CS.*;
import static gregapi.data.CS.FluidsGT.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackMap;
import gregapi.data.CS.FluidsGT;
import gregapi.data.CS.IconsGT;
import gregapi.fluid.FluidGT;
import gregapi.fluid.FluidTankGT;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.UT.Code;
import gregapi.util.UT.NBT;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 *
 * Class containing most known Fluid Names and Fluid Utility.
 */
@SuppressWarnings("unchecked")
public enum FL {
	  Error                     ("error"                                                    , HIDDEN)
	  
	, UUM                       ("ic2uumatter"                                              , LIQUID, ENCHANTED_EFFECT)
	, MatterNeutral             ("neutralmatter"                                            , LIQUID, ENCHANTED_EFFECT)
	, MatterCharged             ("chargedmatter"                                            , LIQUID, ENCHANTED_EFFECT)
	
	, XP                        ("xpjuice"                                                  , SIMPLE, LIQUID, VOID_OVERFLOW)
	, Mob                       ("mobessence"                                               , SIMPLE, LIQUID, VOID_OVERFLOW)
	
	, Air                       ("air"                                                      , SIMPLE, GAS, AIR)
	, Air_End                   ("enderair"                                                 , SIMPLE, GAS, AIR)
	, Air_Nether                ("netherair"                                                , SIMPLE, GAS, AIR)
	
	, Oxygen                    ("oxygen"                                                   , SIMPLE, GAS, OXYGEN)
	, Reikygen                  ("rc oxygen"                                                , SIMPLE, GAS, OXYGEN)
	, Liquid_Oxygen             ("liquidoxygen"                                             , SIMPLE, LIQUID, LIQUID_OXYGEN)
	, Liquid_Reikygen           ("rc liquid oxygen"                                         , SIMPLE, LIQUID, LIQUID_OXYGEN)
	
	, Nitrogen                  ("nitrogen"                                                 , GAS)
	, Liquid_Nitrogen           ("liquidnitrogen"                                           , LIQUID)
	
	, Hydrogen                  ("hydrogen"                                                 , GAS)
	, Deuterium                 ("deuterium"                                                , GAS)
	, Tritium                   ("tritium"                                                  , GAS)
	, Helium                    ("helium"                                                   , GAS)
	, Helium_3                  ("helium-3"                                                 , GAS)
	, Neon                      ("neon"                                                     , GAS)
	, Argon                     ("argon"                                                    , GAS)
	, CarbonDioxide             ("carbondioxide"                                            , GAS)
	
	, Steam                     ("steam"                                                    , SIMPLE, GAS, STEAM, POWER_CONDUCTING)
	, Steam_IC2                 ("ic2steam"                                                 , SIMPLE, GAS, STEAM, POWER_CONDUCTING)
	, Steam_IC2_Superheated     ("ic2superheatedsteam"                                      , SIMPLE, GAS, STEAM, POWER_CONDUCTING)
	
	, Coolant_IC2               ("ic2coolant"                                               , SIMPLE, LIQUID)
	, Coolant_IC2_Hot           ("ic2hotcoolant"                                            , SIMPLE, LIQUID, POWER_CONDUCTING)
	, Freezing_Ooze             ("ooze"                                                     , SIMPLE, LIQUID, BROKEN)
	
	, Thorium_Salt              ("thoriumsalt"                                              , LIQUID)
	
	, Hot_Molten_Sodium         ("hotmoltensodium"                                          , LIQUID, POWER_CONDUCTING)
	, Hot_Molten_Tin            ("hotmoltentin"                                             , LIQUID, POWER_CONDUCTING)
	, Hot_Heavy_Water           ("hotheavywater"                                            , LIQUID, POWER_CONDUCTING)
	, Hot_Semi_Heavy_Water      ("hotsemiheavywater"                                        , LIQUID, POWER_CONDUCTING)
	, Hot_Tritiated_Water       ("hottritiatedwater"                                        , LIQUID, POWER_CONDUCTING)
	, Hot_Molten_LiCl           ("hotmoltenlicl"                                            , LIQUID, POWER_CONDUCTING)
	, Hot_Carbon_Dioxide        ("hotcarbondioxide"                                         , GAS, POWER_CONDUCTING)
	, Hot_Helium                ("hothelium"                                                , GAS, POWER_CONDUCTING)
	, Lava                      ("lava"                                                     , SIMPLE, LIQUID)
	, Lava_Pahoehoe             ("ic2pahoehoelava"                                          , SIMPLE, LIQUID)
	, Lava_Pure                 ("purelava"                                                 , SIMPLE, LIQUID, BROKEN, INFINITE) // Lycanite Lava, Warning: Infinite like vanilla Water!
	
	, Ender_Goo                 ("endergoo"                                                 , LIQUID)
	
	, Water                     ("water"                                                    , SIMPLE, LIQUID, FOOD, WATER)
	, DistW                     ("ic2distilledwater"                                        , SIMPLE, LIQUID, FOOD, WATER)
	, River_Water               ("riverwater"                                               , SIMPLE, LIQUID, FOOD, WATER)
	, SpDew                     ("spectral_dew"                                             , SIMPLE, LIQUID, FOOD, WATER, INFINITE) // Something is broken with its Fluid Icon and its Bucket Registration. It is "Nether Water" from Netherlicious btw.
	, Water_Hot                 ("ic2hotwater"                                              , SIMPLE, LIQUID, FOOD, WATER, THERMOS)
	, Ice                       ("ice"                                                      , SIMPLE, LIQUID, FOOD, WATER, THERMOS)
	, Heavy_Reiker              ("rc heavy water"                                           , SIMPLE, LIQUID)
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
	, Ambrosia                  ("potion.ambrosia"                                          , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	, RoyalJelly                ("royaljelly"                                               , SIMPLE, LIQUID, FOOD)
	
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
	, Juice_Hellderberry        ("hellderberryjuice"                                        , SIMPLE, LIQUID, FOOD, JUICE, FRUIT_JUICE)
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
	
	, Turpentine                ("turpentine"                                               , SIMPLE, LIQUID)
	
	, Sap                       ("sap"                                                      , SIMPLE, LIQUID, FOOD)
	, Sap_Rainbow               ("rainbowsap"                                               , SIMPLE, LIQUID, FOOD)
	, Sap_Maple                 ("maplesap"                                                 , SIMPLE, LIQUID, FOOD)
	
	, Syrup_Maple               ("maplesyrup"                                               , SIMPLE, LIQUID, FOOD)
	
	, Nutbutter_Peanut          ("peanutbutter"                                             , SIMPLE, LIQUID, FOOD)
	
	, Cream                     ("grcmilk.cream"            , "cream"                       , SIMPLE, LIQUID, FOOD)
	, Cream_Chocolate           ("chocolatecream"                                           , SIMPLE, LIQUID, FOOD)
	, Cream_Coconut             ("coconutcream"                                             , SIMPLE, LIQUID, FOOD)
	, Cream_Nutella             ("nutella"                                                  , SIMPLE, LIQUID, FOOD)
	
	, Ketchup                   ("ketchup"                                                  , SIMPLE, LIQUID, FOOD)
	
	, Mayo                      ("mayo"                                                     , SIMPLE, LIQUID, FOOD)
	, Dressing                  ("potion.dressing"                                          , SIMPLE, LIQUID, FOOD)
	
	, Soup_Mushroom             ("mushroomsoup"                                             , SIMPLE, LIQUID, FOOD)
	
	, Sauce_Chili               ("chillysauce"              , "potion.chillysauce"          , SIMPLE, LIQUID, FOOD)
	, Sauce_Hot                 ("potion.hotsauce"                                          , SIMPLE, LIQUID, FOOD)
	, Sauce_Diabolo             ("potion.diabolosauce"                                      , SIMPLE, LIQUID, FOOD)
	, Sauce_Diablo              ("potion.diablosauce"                                       , SIMPLE, LIQUID, FOOD)
	, Sauce_Snitches            ("potion.diablosauce.strong"                                , SIMPLE, LIQUID, FOOD)
	, Sauce_BBQ                 ("bbqsauce"                                                 , SIMPLE, LIQUID, FOOD)
	
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
	, Pina_Colada               ("pina.colada"                                              , SIMPLE, LIQUID, FOOD, ALCOHOLIC, RUM)

	, Vodka                     ("binnie.vodka"             , "potion.vodka", "vodka"       , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	, Leninade                  ("potion.leninade"                                          , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	, Mead                      ("mead"                                                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	, ShortMead                 ("short.mead"                                               , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	, Sake                      ("potion.sake"                                              , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	, Tequila                   ("binnie.tequila"           , "tequila"                     , SIMPLE, LIQUID, FOOD, ALCOHOLIC)
	, Alcopops                  ("potion.alcopops"                                          , SIMPLE, LIQUID, FOOD, ALCOHOLIC)

	, Oil_Frying                ("hotfryingoil"                                             , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Seed                  ("seedoil"                                                  , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Plant                 ("plantoil"                                                 , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Sunflower             ("sunfloweroil"                                             , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Olive                 ("binnie.juiceolive"        , "oliveoil"                    , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Nut                   ("nutoil"                                                   , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Lin                   ("linoil"                   , "linseedoil"                  , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Hemp                  ("hempoil"                  , "hempseedoil"                 , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Fish                  ("fishoil"                                                  , SIMPLE, LIQUID, FOOD, COOKING_OIL, BATH)
	, Oil_Canola                ("canolaoil"                                                , SIMPLE, LIQUID)
	, Oil_Creosote              ("creosote"                                                 , SIMPLE, LIQUID, BATH)
	, Oil_Soulsand              ("soulsandoil"                                              , SIMPLE, LIQUID)
	, Oil_Light                 ("liquid_light_oil"                                         , SIMPLE, LIQUID)
	, Oil_Light2                ("lightoil"                                                 , SIMPLE, LIQUID)
	, Oil_Normal                ("oil"                      , "oilgc"                       , SIMPLE, LIQUID)
	, Oil_Medium                ("liquid_medium_oil"                                        , SIMPLE, LIQUID)
	, Oil_HotCrude              ("hotcrude"                                                 , SIMPLE, LIQUID)
	, Oil_Heavy                 ("liquid_heavy_oil"                                         , SIMPLE, LIQUID)
	, Oil_Heavy2                ("heavyoil"                                                 , SIMPLE, LIQUID)
	, Oil_ExtraHeavy            ("liquid_extra_heavy_oil"                                   , SIMPLE, LIQUID)
	
	, Gas_Natural               ("gas_natural_gas"          , "naturalgas", "gas.natural"   , SIMPLE, GAS)
	, Methane                   ("methane"                                                  , SIMPLE, GAS)
	, Liquid_Methane            ("liquidmethane"                                            , SIMPLE, LIQUID, THERMOS)
	, Propane                   ("propane"                                                  , SIMPLE, GAS)
	, Butane                    ("butane"                                                   , SIMPLE, GAS)
	, Ethylene                  ("ethylene"                 , "ethene"                      , GAS)
	, Propylene                 ("propylene"                , "propene"                     , GAS)
	, Petrol                    ("petrol"                   , "gasoline"                    , SIMPLE, LIQUID)
	, Fuel                      ("fuel"                                                     , SIMPLE, LIQUID)
	, Diesel                    ("diesel"                                                   , SIMPLE, LIQUID)
	, Kerosine                  ("kerosine"                 , "kerosene"                    , SIMPLE, LIQUID)
	, LPG                       ("lpg"                                                      , LIQUID)
	, JetFuel                   ("rc jet fuel"                                              , LIQUID)
	
	, BioFuel                   ("biofuel"                                                  , SIMPLE, LIQUID)
	, BioDiesel                 ("biodiesel"                                                , SIMPLE, LIQUID)
	, BioEthanol                ("bioethanol"               , "ethanol"                     , SIMPLE, LIQUID)
	, Reikanol                  ("rc ethanol"                                               , SIMPLE, LIQUID)
	
	, Glue                      ("glue"                                                     , SIMPLE, LIQUID)
	, Latex                     ("latex"                    , "molten.latex"                , SIMPLE, LIQUID)
	, Concrete                  ("concrete"                 , "molten.concrete"             , SIMPLE, LIQUID)
	, CFoam                     ("ic2constructionfoam"                                      , LIQUID) // 100 per Unit
	, Sewage                    ("sewage"                                                   , SIMPLE, LIQUID)
	, Sludge                    ("sludge"                                                   , SIMPLE, LIQUID)
	, Glass                     ("glass"                    , "molten.glass"                , SIMPLE, LIQUID)
	, Sluice                    ("sluicejuice"                                              , SIMPLE, LIQUID)
	
	, Indigo                    ("indigo"                                                   , SIMPLE, LIQUID, DYE)
	
	, InkSquid                  ("squidink"                                                 , SIMPLE, LIQUID, DYE)
	, InkMyst                   ("myst.ink.black"                                           , SIMPLE, LIQUID, ENCHANTED_EFFECT)
	
	, Blaze                     ("blaze"                    , "molten.blaze"                , LIQUID, ENCHANTED_EFFECT) // 144 per Unit
	, FieryBlood                ("fieryblood"                                               , LIQUID, ENCHANTED_EFFECT) // 144 per Unit
	, FieryTears                ("fierytears"                                               , LIQUID, ENCHANTED_EFFECT) // 144 per Unit
	, Pyrotheum                 ("pyrotheum"                                                , LIQUID) // 250 per Unit
	, Cryotheum                 ("cryotheum"                                                , LIQUID) // 250 per Unit
	, Petrotheum                ("petrotheum"                                               , LIQUID) // 250 per Unit
	, Aerotheum                 ("aerotheum"                                                , GAS) // 250 per Unit
	, Mana_TE                   ("mana"                                                     , LIQUID, ENCHANTED_EFFECT) // 250 per Unit
	, Ender                     ("molten.enderpearl"                                        , LIQUID, ENCHANTED_EFFECT) // 144 per Unit
	, Ender_TE                  ("ender"                                                    , LIQUID, ENCHANTED_EFFECT) // 250 per Unit
	, Redstone                  ("molten.redstone"                                          , LIQUID) // 144 per Unit
	, Redstone_TE               ("redstone"                                                 , LIQUID) // 100 per Unit
	, Glowstone_TE              ("glowstone"                                                , GAS) // 250 per Unit
	
	, Calcite                   ("molten.calcite"                                           , LIQUID) // 144 per Unit

	, Med_Heal                  ("medicine.heal"                                            , SIMPLE, LIQUID, BATH)
	, Med_Laxative              ("medicine.laxative"                                        , SIMPLE, LIQUID, BATH)

	, Rotten_Drink              ("rottendrink"                                              , SIMPLE, LIQUID, FOOD)

	, Dragon_Breath             ("dragonbreath"                                             , SIMPLE, GAS, BATH)

	, Potion_Tainted            ("potion.tainted"                                           , SIMPLE, LIQUID, POTION)
	, Potion_Awkward            ("potion.awkward"                                           , SIMPLE, LIQUID, POTION)
	, Potion_Thick              ("potion.thick"                                             , SIMPLE, LIQUID, POTION)
	, Potion_Mundane            ("potion.mundane"                                           , SIMPLE, LIQUID, POTION)
	, Potion_Harm_1             ("potion.damage"                                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Harm_2             ("potion.damage.strong"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Harm_1S            ("potion.damage.splash"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Harm_2S            ("potion.damage.strong.splash"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Harm_1D            ("potion.damage.lingering"                                  , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Harm_2D            ("potion.damage.strong.lingering"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Heal_1             ("potion.health"                                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Heal_2             ("potion.health.strong"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Heal_1S            ("potion.health.splash"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Heal_2S            ("potion.health.strong.splash"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Heal_1D            ("potion.health.lingering"                                  , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Heal_2D            ("potion.health.strong.lingering"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Jump_1             ("potion.jump"                                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Jump_2             ("potion.jump.strong"                                       , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Jump_1S            ("potion.jump.splash"                                       , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Jump_2S            ("potion.jump.strong.splash"                                , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Jump_1D            ("potion.jump.lingering"                                    , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Jump_2D            ("potion.jump.strong.lingering"                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Speed_1            ("potion.speed"                                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Speed_2            ("potion.speed.strong"                                      , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Speed_1L           ("potion.speed.long"                                        , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Speed_1S           ("potion.speed.splash"                                      , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Speed_2S           ("potion.speed.strong.splash"                               , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Speed_1LS          ("potion.speed.long.splash"                                 , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Speed_1D           ("potion.speed.lingering"                                   , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Speed_2D           ("potion.speed.strong.lingering"                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Speed_1LD          ("potion.speed.long.lingering"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Strength_1         ("potion.strength"                                          , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Strength_2         ("potion.strength.strong"                                   , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Strength_1L        ("potion.strength.long"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Strength_1S        ("potion.strength.splash"                                   , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Strength_2S        ("potion.strength.strong.splash"                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Strength_1LS       ("potion.strength.long.splash"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Strength_1D        ("potion.strength.lingering"                                , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Strength_2D        ("potion.strength.strong.lingering"                         , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Strength_1LD       ("potion.strength.long.lingering"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Regen_1            ("potion.regen"                                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Regen_2            ("potion.regen.strong"                                      , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Regen_1L           ("potion.regen.long"                                        , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Regen_1S           ("potion.regen.splash"                                      , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Regen_2S           ("potion.regen.strong.splash"                               , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Regen_1LS          ("potion.regen.long.splash"                                 , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Regen_1D           ("potion.regen.lingering"                                   , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Regen_2D           ("potion.regen.strong.lingering"                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Regen_1LD          ("potion.regen.long.lingering"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Poison_1           ("potion.poison"                                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Poison_2           ("potion.poison.strong"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Poison_1L          ("potion.poison.long"                                       , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Poison_1S          ("potion.poison.splash"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Poison_2S          ("potion.poison.strong.splash"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Poison_1LS         ("potion.poison.long.splash"                                , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Poison_1D          ("potion.poison.lingering"                                  , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Poison_2D          ("potion.poison.strong.lingering"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Poison_1LD         ("potion.poison.long.lingering"                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_FireResistance_1   ("potion.fireresistance"                                    , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_FireResistance_1L  ("potion.fireresistance.long"                               , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_FireResistance_1S  ("potion.fireresistance.splash"                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_FireResistance_1LS ("potion.fireresistance.long.splash"                        , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_FireResistance_1D  ("potion.fireresistance.lingering"                          , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_FireResistance_1LD ("potion.fireresistance.long.lingering"                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_NightVision_1      ("potion.nightvision"                                       , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_NightVision_1L     ("potion.nightvision.long"                                  , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_NightVision_1S     ("potion.nightvision.splash"                                , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_NightVision_1LS    ("potion.nightvision.long.splash"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_NightVision_1D     ("potion.nightvision.lingering"                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_NightVision_1LD    ("potion.nightvision.long.lingering"                        , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Weakness_1         ("potion.weakness"                                          , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Weakness_1L        ("potion.weakness.long"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Weakness_1S        ("potion.weakness.splash"                                   , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Weakness_1LS       ("potion.weakness.long.splash"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Weakness_1D        ("potion.weakness.lingering"                                , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Weakness_1LD       ("potion.weakness.long.lingering"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Slowness_1         ("potion.slowness"                                          , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Slowness_1L        ("potion.slowness.long"                                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Slowness_1S        ("potion.slowness.splash"                                   , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Slowness_1LS       ("potion.slowness.long.splash"                              , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Slowness_1D        ("potion.slowness.lingering"                                , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Slowness_1LD       ("potion.slowness.long.lingering"                           , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_WaterBreathing_1   ("potion.waterbreathing"                                    , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_WaterBreathing_1L  ("potion.waterbreathing.long"                               , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_WaterBreathing_1S  ("potion.waterbreathing.splash"                             , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_WaterBreathing_1LS ("potion.waterbreathing.long.splash"                        , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_WaterBreathing_1D  ("potion.waterbreathing.lingering"                          , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_WaterBreathing_1LD ("potion.waterbreathing.long.lingering"                     , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Invisibility_1     ("potion.invisibility"                                      , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Invisibility_1L    ("potion.invisibility.long"                                 , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT, BATH)
	, Potion_Invisibility_1S    ("potion.invisibility.splash"                               , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Invisibility_1LS   ("potion.invisibility.long.splash"                          , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Invisibility_1D    ("potion.invisibility.lingering"                            , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	, Potion_Invisibility_1LD   ("potion.invisibility.long.lingering"                       , SIMPLE, LIQUID, POTION, ENCHANTED_EFFECT)
	;
	
	public final String mName;
	
	private FL(String aName, Collection<String>... aFluidSets) {
		mName = aName;
		for (Collection<String> aFluidSet : aFluidSets) {aFluidSet.add(mName);}
	}
	private FL(String aName, String aOldName, Collection<String>... aFluidSets) {
		mName = aName;
		FluidsGT.HIDDEN.add(aOldName);
		FluidsGT.NONSTANDARD.add(aOldName);
		FluidsGT.FLUID_RENAMINGS.put(aOldName, mName);
		for (Collection<String> aFluidSet : aFluidSets) {aFluidSet.add(mName); aFluidSet.add(aOldName);}
	}
	private FL(String aName, String aOldName1, String aOldName2, Collection<String>... aFluidSets) {
		mName = aName;
		FluidsGT.HIDDEN.add(aOldName1);
		FluidsGT.HIDDEN.add(aOldName2);
		FluidsGT.NONSTANDARD.add(aOldName1);
		FluidsGT.NONSTANDARD.add(aOldName2);
		FluidsGT.FLUID_RENAMINGS.put(aOldName1, mName);
		FluidsGT.FLUID_RENAMINGS.put(aOldName2, mName);
		for (Collection<String> aFluidSet : aFluidSets) {aFluidSet.add(mName); aFluidSet.add(aOldName1); aFluidSet.add(aOldName2);}
	}
	
	
	
	
	public static FluidStack lube(long aAmount) {return LubRoCant.make(aAmount, Lubricant);}
	
	
	
	public int id() {return FluidRegistry.getFluidID(mName);}
	public Fluid fluid() {return fluid_(mName);}
	public boolean exists() {return fluid() != null;}
	public ItemStack display() {return display(make(0), F, F);}
	public ItemStack display(long aAmount) {return display(make(aAmount), aAmount, F, F);}
	
	public FluidStack make (long aAmount) {return make (mName, aAmount);}
	public FluidStack make_(long aAmount) {return make_(mName, aAmount);}
	public FluidStack make (long aAmount, String aReplacement) {return make (mName, aAmount, aReplacement);}
	public FluidStack make_(long aAmount, String aReplacement) {return make_(mName, aAmount, aReplacement);}
	public FluidStack make (long aAmount, FL aReplacement) {return make (mName, aAmount, aReplacement.mName);}
	public FluidStack make_(long aAmount, FL aReplacement) {return make_(mName, aAmount, aReplacement.mName);}
	public FluidStack make (long aAmount, String aReplacement, long aReplacementAmount) {return make (mName, aAmount, aReplacement, aReplacementAmount);}
	public FluidStack make_(long aAmount, String aReplacement, long aReplacementAmount) {return make_(mName, aAmount, aReplacement, aReplacementAmount);}
	public FluidStack make (long aAmount, FL aReplacement, long aReplacementAmount) {return make (mName, aAmount, aReplacement.mName, aReplacementAmount);}
	public FluidStack make_(long aAmount, FL aReplacement, long aReplacementAmount) {return make_(mName, aAmount, aReplacement.mName, aReplacementAmount);}
	
	public boolean is(IFluidTank aTank) {return is(aTank.getFluid());}
	public boolean is(FluidStack aFluid) {return aFluid != null && is(aFluid.getFluid());}
	public boolean is(Fluid aFluid) {return aFluid != null && is(aFluid.getName());}
	public boolean is(String aFluidName) {return mName.equalsIgnoreCase(aFluidName);}
	public boolean is(Collection<String> aFluidSet) {return aFluidSet.contains(mName);}
	
	
	
	
	
	
	public static FluidStack[] array(FluidStack... aFluids) {return aFluids;}
	
	public static String regName (IFluidTank aTank) {return aTank == null ? null : regName_(aTank);}
	public static String regName_(IFluidTank aTank) {return regName(aTank.getFluid());}
	public static String regName (FluidStack aFluid) {return aFluid == null ? null : regName_(aFluid);}
	public static String regName_(FluidStack aFluid) {return regName(aFluid.getFluid());}
	public static String regName (Fluid aFluid) {return aFluid == null ? null : regName_(aFluid);}
	public static String regName_(Fluid aFluid) {return aFluid.getName();}
	
	public static short id (IFluidTank aTank) {return aTank == null ? -1 : id_(aTank);}
	public static short id_(IFluidTank aTank) {return id(aTank.getFluid());}
	public static short id (FluidStack aFluid) {return aFluid == null ? -1 : id_(aFluid);}
	public static short id_(FluidStack aFluid) {return id(aFluid.getFluid());}
	public static short id (Fluid aFluid) {return aFluid == null ? -1 : id_(aFluid);}
	public static short id_(Fluid aFluid) {return (short)FluidRegistry.getFluidID(aFluid);} // catch(Throwable e) {ERR.println("What the fuck?! Why does the Fluid Registry Crash!? Who is responsible for this?!"); e.printStackTrace(ERR);} return -1;}
	
	public static Fluid fluid (int aID) {return aID < 0 ? null : FluidRegistry.getFluid(aID);}
	public static Fluid fluid (String aFluidName) {return Code.stringInvalid(aFluidName) ? null : fluid_(aFluidName);}
	public static Fluid fluid_(String aFluidName) {return FluidRegistry.getFluid(aFluidName);}
	
	public static boolean equal(FluidStack aFluid1, FluidStack aFluid2) {return equal(aFluid1, aFluid2, F);}
	public static boolean equal(FluidStack aFluid1, FluidStack aFluid2, boolean aIgnoreNBT) {return aFluid1 != null && aFluid2 != null && aFluid1.getFluid() == aFluid2.getFluid() && (aIgnoreNBT || ((aFluid1.tag == null) == (aFluid2.tag == null)) && (aFluid1.tag == null || aFluid1.tag.equals(aFluid2.tag)));}
	
	public static boolean is(IFluidTank aTank, String... aNames) {return is(aTank.getFluid(), aNames);}
	public static boolean is(FluidStack aFluid, String... aNames) {return aFluid != null && is(aFluid.getFluid(), aNames);}
	public static boolean is(Fluid aFluid, String... aNames) {if (aFluid != null) for (String aName : aNames) if (aFluid.getName().equalsIgnoreCase(aName)) return T; return F;}
	
	public static boolean exists(String aFluidName) {return aFluidName != null && fluid_(aFluidName) != null;}
	
	public static ItemStack display(Fluid aFluid) {return aFluid == null ? null : display(make(aFluid, 0), F, F, T);}
	public static ItemStack display(FluidStack aFluid, boolean aUseStackSize, boolean aLimitStackSize) {return display(aFluid, aUseStackSize, aLimitStackSize, T);}
	public static ItemStack display(FluidStack aFluid, boolean aUseStackSize, boolean aLimitStackSize, boolean aUseBucketSize) {return display(aFluid, aFluid == null ? 0 : aFluid.amount, aUseStackSize, aLimitStackSize, aUseBucketSize);}
	public static ItemStack display(FluidTankGT aTank, boolean aUseStackSize, boolean aLimitStackSize) {return display(aTank.getFluid(), aTank.amount(), aUseStackSize, aLimitStackSize);}
	public static ItemStack display(FluidStack aFluid, long aAmount, boolean aUseStackSize, boolean aLimitStackSize) {return display(aFluid, aAmount, aUseStackSize, aLimitStackSize, T);}
	public static ItemStack display(FluidStack aFluid, long aAmount, boolean aUseStackSize, boolean aLimitStackSize, boolean aUseBucketSize) {
		short aID = id(aFluid);
		if (aID < 0) return null;
		ItemStack rStack = IL.Display_Fluid.getWithMeta(Math.max(1, aUseStackSize ? aUseBucketSize ? aLimitStackSize ? UT.Code.bind7(aAmount / 1000) : aAmount / 1000 : aLimitStackSize ? UT.Code.bind7(aAmount) : aAmount : 1), aID);
		if (rStack == null) return null;
		NBTTagCompound tNBT = NBT.makeString("f", aFluid.getFluid().getName());
		if (aAmount != 0) NBT.setNumber(tNBT, "a", aAmount);
		NBT.setNumber(tNBT, "h", temperature(aFluid));
		NBT.setBoolean(tNBT, "s", gas(aFluid));
		return NBT.set(rStack, tNBT);
	}
	
	/** @return if that Liquid is Water or Distilled Water */
	public static boolean water(IFluidTank aFluid) {return aFluid != null && water(aFluid.getFluid());}
	/** @return if that Liquid is Water or Distilled Water */
	public static boolean water(FluidStack aFluid) {return aFluid != null && water(aFluid.getFluid());}
	/** @return if that Liquid is Water or Distilled Water */
	public static boolean water(Fluid aFluid) {return aFluid == FluidRegistry.WATER || FL.DistW.is(aFluid) || FL.SpDew.is(aFluid);}
	
	/** @return if that Liquid is distilled Water */
	public static boolean distw(IFluidTank aFluid) {return aFluid != null && distw(aFluid.getFluid());}
	/** @return if that Liquid is distilled Water */
	public static boolean distw(FluidStack aFluid) {return aFluid != null && distw(aFluid.getFluid());}
	/** @return if that Liquid is distilled Water */
	public static boolean distw(Fluid aFluid) {return FL.DistW.is(aFluid);}
	
	/** @return if that Liquid is Lava */
	public static boolean lava(IFluidTank aFluid) {return aFluid != null && lava(aFluid.getFluid());}
	/** @return if that Liquid is Lava */
	public static boolean lava(FluidStack aFluid) {return aFluid != null && lava(aFluid.getFluid());}
	/** @return if that Liquid is Lava */
	public static boolean lava(Fluid aFluid) {return aFluid == FluidRegistry.LAVA;}
	
	/** @return if that Liquid is Steam */
	public static boolean steam(IFluidTank aFluid) {return aFluid != null && steam(aFluid.getFluid());}
	/** @return if that Liquid is Steam */
	public static boolean steam(FluidStack aFluid) {return aFluid != null && steam(aFluid.getFluid());}
	/** @return if that Liquid is Steam */
	public static boolean steam(Fluid aFluid) {return FL.Steam.is(aFluid);}
	
	/** @return if that Liquid is Milk */
	public static boolean milk(IFluidTank aFluid) {return aFluid != null && milk(aFluid.getFluid());}
	/** @return if that Liquid is Milk */
	public static boolean milk(FluidStack aFluid) {return aFluid != null && milk(aFluid.getFluid());}
	/** @return if that Liquid is Milk */
	public static boolean milk(Fluid aFluid) {return FL.Milk.is(aFluid) || FL.MilkGrC.is(aFluid);}
	
	/** @return if that Liquid is Soy Milk */
	public static boolean soym(IFluidTank aFluid) {return aFluid != null && soym(aFluid.getFluid());}
	/** @return if that Liquid is Soy Milk */
	public static boolean soym(FluidStack aFluid) {return aFluid != null && soym(aFluid.getFluid());}
	/** @return if that Liquid is Soy Milk */
	public static boolean soym(Fluid aFluid) {return FL.MilkSoy.is(aFluid);}
	
	/** @return if that Liquid is Steam */
	public static boolean anysteam(IFluidTank aFluid) {return aFluid != null && steam(aFluid.getFluid());}
	/** @return if that Liquid is Steam */
	public static boolean anysteam(FluidStack aFluid) {return aFluid != null && steam(aFluid.getFluid());}
	/** @return if that Liquid is Steam */
	public static boolean anysteam(Fluid aFluid) {return aFluid != null && FluidsGT.STEAM.contains(aFluid.getName());}
	
	/** @return if that Liquid is supposed to be conducting Power */
	public static boolean powerconducting(IFluidTank aFluid) {return aFluid != null && powerconducting(aFluid.getFluid());}
	/** @return if that Liquid is supposed to be conducting Power */
	public static boolean powerconducting(FluidStack aFluid) {return aFluid != null && powerconducting(aFluid.getFluid());}
	/** @return if that Liquid is supposed to be conducting Power */
	public static boolean powerconducting(Fluid aFluid) {return aFluid != null && FluidsGT.POWER_CONDUCTING.contains(aFluid.getName());}
	
	/** @return if that Liquid is early-game and easy to handle */
	public static boolean simple(IFluidTank aFluid) {return aFluid != null && simple(aFluid.getFluid());}
	/** @return if that Liquid is early-game and easy to handle */
	public static boolean simple(FluidStack aFluid) {return aFluid != null && simple(aFluid.getFluid());}
	/** @return if that Liquid is early-game and easy to handle */
	public static boolean simple(Fluid aFluid) {return aFluid != null && FluidsGT.SIMPLE.contains(aFluid.getName());}
	
	public static boolean acid(IFluidTank aFluid) {return aFluid != null && acid(aFluid.getFluid());}
	public static boolean acid(FluidStack aFluid) {return aFluid != null && acid(aFluid.getFluid());}
	public static boolean acid(Fluid aFluid) {return aFluid != null && FluidsGT.ACID.contains(aFluid.getName());}
	
	public static boolean plasma(IFluidTank aFluid) {return aFluid != null && plasma(aFluid.getFluid());}
	public static boolean plasma(FluidStack aFluid) {return aFluid != null && plasma(aFluid.getFluid());}
	public static boolean plasma(Fluid aFluid) {return aFluid != null && FluidsGT.PLASMA.contains(aFluid.getName());}
	
	public static boolean gas(IFluidTank aFluid, boolean aDefault) {return gas(aFluid.getFluid(), aDefault);}
	public static boolean gas(IFluidTank aFluid) {return gas(aFluid.getFluid(), F);}
	public static boolean gas(FluidStack aFluid, boolean aDefault) {return aFluid == null || aFluid.getFluid() == null ? aDefault : !FluidsGT.LIQUID.contains(aFluid.getFluid().getName()) && (aFluid.getFluid().isGaseous(aFluid) || FluidsGT.GAS.contains(aFluid.getFluid().getName()));}
	public static boolean gas(FluidStack aFluid) {return gas(aFluid, F);}
	public static boolean gas(Fluid aFluid, boolean aDefault) {return aFluid == null ? aDefault : !FluidsGT.LIQUID.contains(aFluid.getName()) && (aFluid.isGaseous() || FluidsGT.GAS.contains(aFluid.getName()));}
	public static boolean gas(Fluid aFluid) {return gas(aFluid, F);}
	
	public static boolean lighter(BlockFluidBase aFluid) {return aFluid != null && lighter(aFluid.getFluid());}
	public static boolean lighter(IFluidTank aFluid)     {return aFluid != null && lighter(aFluid.getFluid());}
	public static boolean lighter(FluidStack aFluid)     {return aFluid != null && aFluid.getFluid() != null && aFluid.getFluid().getDensity(aFluid) < 0;}
	public static boolean lighter(Fluid aFluid)          {return aFluid != null && aFluid.getDensity(make(aFluid, 1000)) < 0;}
	
	public static boolean heavier(BlockFluidBase aFluid) {return aFluid != null && heavier(aFluid.getFluid());}
	public static boolean heavier(IFluidTank aFluid)     {return aFluid != null && heavier(aFluid.getFluid());}
	public static boolean heavier(FluidStack aFluid)     {return aFluid != null && aFluid.getFluid() != null && aFluid.getFluid().getDensity(aFluid) > 0;}
	public static boolean heavier(Fluid aFluid)          {return aFluid != null && aFluid.getDensity(make(aFluid, 1000)) > 0;}
	
	public static int dir(BlockFluidBase aFluid) {return lighter(aFluid) ? +1 : -1;}
	public static int dir(IFluidTank aFluid)     {return lighter(aFluid) ? +1 : -1;}
	public static int dir(FluidStack aFluid)     {return lighter(aFluid) ? +1 : -1;}
	public static int dir(Fluid aFluid)          {return lighter(aFluid) ? +1 : -1;}
	
	public static long temperature(IFluidTank aFluid) {return temperature(aFluid.getFluid());}
	public static long temperature(IFluidTank aFluid, long aDefault) {return temperature(aFluid.getFluid(), aDefault);}
	
	public static long temperature(Fluid aFluid) {return temperature(aFluid, DEF_ENV_TEMP);}
	public static long temperature(Fluid aFluid, long aDefault) {
		if (aFluid == null) return aDefault;
		if (aFluid.getName().equals("steam")) return C+100;
		return aFluid.getTemperature(make(aFluid, 1));
	}
	
	public static long temperature(FluidStack aFluid) {return temperature(aFluid, DEF_ENV_TEMP);}
	public static long temperature(FluidStack aFluid, long aDefault) {
		if (aFluid == null || aFluid.getFluid() == null) return aDefault;
		if (aFluid.getFluid().getName().equals("steam")) return C+100;
		return aFluid.getFluid().getTemperature(aFluid);
	}
	
	public static FluidStack make (int aFluid, long aAmount) {return aFluid < 0 ? null : new FluidStack(fluid(aFluid), Code.bindInt(aAmount));}
	public static FluidStack make (Fluid aFluid, long aAmount) {return aFluid == null ? null : new FluidStack(aFluid, Code.bindInt(aAmount));}
	public static FluidStack make (String aFluidName, long aAmount) {return make(fluid(aFluidName), aAmount);}
	public static FluidStack make (String aFluidName, long aAmount, String aReplacementFluidName) {FluidStack rFluid = make(aFluidName, aAmount); return rFluid == null ? make(aReplacementFluidName, aAmount) : rFluid;}
	public static FluidStack make (String aFluidName, long aAmount, String aReplacementFluidName, long aReplacementAmount) {FluidStack rFluid = make(aFluidName, aAmount); return rFluid == null ? make(aReplacementFluidName, aReplacementAmount) : rFluid;}
	public static FluidStack make (String aFluidName, long aAmount, FluidStack aReplacementFluid) {FluidStack rFluid = make(aFluidName, aAmount); return rFluid == null ? aReplacementFluid : rFluid;}
	
	public static FluidStack make_(int aFluid, long aAmount) {return aFluid < 0 ? FL.Error.make(0) : new FluidStack(fluid(aFluid), Code.bindInt(aAmount));}
	public static FluidStack make_(Fluid aFluid, long aAmount) {return aFluid == null ? FL.Error.make(0) : new FluidStack(aFluid, Code.bindInt(aAmount));}
	public static FluidStack make_(String aFluidName, long aAmount) {return make_(fluid(aFluidName), aAmount);}
	public static FluidStack make_(String aFluidName, long aAmount, String aReplacementFluidName) {FluidStack rFluid = make(aFluidName, aAmount); return rFluid == null ? make_(aReplacementFluidName, aAmount) : rFluid;}
	public static FluidStack make_(String aFluidName, long aAmount, String aReplacementFluidName, long aReplacementAmount) {FluidStack rFluid = make(aFluidName, aAmount); return rFluid == null ? make_(aReplacementFluidName, aReplacementAmount) : rFluid;}
	
	public static FluidStack amount(FluidStack aFluid, long aAmount) {return aFluid == null ? null : new FluidStack(aFluid, Code.bindInt(aAmount));}
	
	public static FluidStack mul(FluidStack aFluid, long aMultiplier) {return aFluid == null ? null : amount(aFluid, aFluid.amount * aMultiplier);}
	public static FluidStack mul(FluidStack aFluid, long aMultiplier, long aDivider, boolean aRoundUp) {return aFluid == null ? null : amount(aFluid, Code.units(aFluid.amount, aDivider, aMultiplier, aRoundUp));}
	
	public static long fill (@SuppressWarnings("rawtypes") DelegatorTileEntity aDelegator, FluidStack aFluid, boolean aDoFill) {return aDelegator != null && aDelegator.mTileEntity instanceof IFluidHandler && aFluid != null ? fill_(aDelegator, aFluid, aDoFill) : 0;}
	public static long fill_(@SuppressWarnings("rawtypes") DelegatorTileEntity aDelegator, FluidStack aFluid, boolean aDoFill) {return fill_((IFluidHandler)aDelegator.mTileEntity, aDelegator.mSideOfTileEntity, aFluid, aDoFill);}
	public static long fill (IFluidHandler aFluidHandler, byte aSide, FluidStack aFluid, boolean aDoFill) {return aFluidHandler != null && aFluid != null ? fill_(aFluidHandler, aSide, aFluid, aDoFill) : 0;}
	public static long fill_(IFluidHandler aFluidHandler, byte aSide, FluidStack aFluid, boolean aDoFill) {return aFluidHandler.fill(FORGE_DIR[aSide], aFluid, aDoFill);}
	public static long fill (IFluidHandler aFluidHandler, byte[] aSides, FluidStack aFluid, boolean aDoFill) {return aFluidHandler != null && aFluid != null ? fill_(aFluidHandler, aSides, aFluid, aDoFill) : 0;}
	public static long fill_(IFluidHandler aFluidHandler, byte[] aSides, FluidStack aFluid, boolean aDoFill) {for (byte tSide : aSides) {long rFilled = aFluidHandler.fill(FORGE_DIR[tSide], aFluid, aDoFill); if (rFilled > 0) return rFilled;} return 0;}
	
	public static boolean fillAll (@SuppressWarnings("rawtypes") DelegatorTileEntity aDelegator, FluidStack aFluid, boolean aDoFill) {return aDelegator != null && aDelegator.mTileEntity instanceof IFluidHandler && aFluid != null && fillAll_(aDelegator, aFluid, aDoFill);}
	public static boolean fillAll_(@SuppressWarnings("rawtypes") DelegatorTileEntity aDelegator, FluidStack aFluid, boolean aDoFill) {return fillAll_((IFluidHandler)aDelegator.mTileEntity, aDelegator.mSideOfTileEntity, aFluid, aDoFill);}
	public static boolean fillAll (IFluidHandler aFluidHandler, byte aSide, FluidStack aFluid, boolean aDoFill) {return aFluidHandler != null && aFluid != null && fillAll_(aFluidHandler, aSide, aFluid, aDoFill);}
	public static boolean fillAll_(IFluidHandler aFluidHandler, byte aSide, FluidStack aFluid, boolean aDoFill) {return aFluidHandler.fill(FORGE_DIR[aSide], aFluid, F) == aFluid.amount && (!aDoFill || aFluidHandler.fill(FORGE_DIR[aSide], aFluid, T) > 0);}
	public static boolean fillAll (IFluidHandler aFluidHandler, byte[] aSides, FluidStack aFluid, boolean aDoFill) {return aFluidHandler != null && aFluid != null && fillAll_(aFluidHandler, aSides, aFluid, aDoFill);}
	public static boolean fillAll_(IFluidHandler aFluidHandler, byte[] aSides, FluidStack aFluid, boolean aDoFill) {for (byte tSide : aSides) if (aFluidHandler.fill(FORGE_DIR[tSide], aFluid, F) == aFluid.amount && (!aDoFill || aFluidHandler.fill(FORGE_DIR[tSide], aFluid, T) > 0)) return T; return F;}
	
	public static long move (@SuppressWarnings("rawtypes") DelegatorTileEntity aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move (aFrom, aTo, Long.MAX_VALUE);}
	public static long move_(@SuppressWarnings("rawtypes") DelegatorTileEntity aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move_(aFrom, aTo, Long.MAX_VALUE);}
	public static long move (@SuppressWarnings("rawtypes") DelegatorTileEntity aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {return aFrom != null && aFrom.mTileEntity instanceof IFluidHandler && aTo != null && aTo.mTileEntity instanceof IFluidHandler ? move_(aFrom, aTo, aMaxMoved) : 0;}
	public static long move_(@SuppressWarnings("rawtypes") DelegatorTileEntity aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {if (aMaxMoved <= 0) return 0; FluidStack tDrained = ((IFluidHandler)aFrom.mTileEntity).drain(aFrom.getForgeSideOfTileEntity(), UT.Code.bindInt(aMaxMoved), F); if (tDrained == null || tDrained.amount <= 0) return 0; tDrained.amount = Code.bindInt(fill_(aTo, tDrained.copy(), T)); if (tDrained.amount <= 0) return 0; ((IFluidHandler)aFrom.mTileEntity).drain(aFrom.getForgeSideOfTileEntity(), tDrained, T); return tDrained.amount;}
	public static long move (@SuppressWarnings("rawtypes") DelegatorTileEntity aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, FluidStack aMoved) {return aFrom != null && aFrom.mTileEntity instanceof IFluidHandler && aTo != null && aTo.mTileEntity instanceof IFluidHandler ? move_(aFrom, aTo, aMoved) : 0;}
	public static long move_(@SuppressWarnings("rawtypes") DelegatorTileEntity aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, FluidStack aMoved) {if (aMoved == null || aMoved.amount <= 0) return 0; FluidStack tDrained = ((IFluidHandler)aFrom.mTileEntity).drain(aFrom.getForgeSideOfTileEntity(), aMoved, F); if (tDrained == null || tDrained.amount <= 0) return 0; tDrained.amount = Code.bindInt(fill_(aTo, tDrained.copy(), T)); if (tDrained.amount <= 0) return 0; ((IFluidHandler)aFrom.mTileEntity).drain(aFrom.getForgeSideOfTileEntity(), tDrained, T); return tDrained.amount;}
	public static long move (IFluidTank aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move (aFrom, aTo, Long.MAX_VALUE);}
	public static long move_(IFluidTank aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move_(aFrom, aTo, Long.MAX_VALUE);}
	public static long move (IFluidTank aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {return aFrom != null && aTo != null && aTo.mTileEntity instanceof IFluidHandler ? move_(aFrom, aTo, aMaxMoved) : 0;}
	public static long move_(IFluidTank aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {if (aMaxMoved <= 0) return 0; FluidStack tDrained = aFrom.drain(UT.Code.bindInt(aMaxMoved), F); if (tDrained == null || tDrained.amount <= 0) return 0; tDrained.amount = Code.bindInt(fill_(aTo, tDrained.copy(), T)); if (tDrained.amount <= 0) return 0; aFrom.drain(tDrained.amount, T); return tDrained.amount;}
	public static long move (IFluidTank[] aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move (aFrom, aTo, Long.MAX_VALUE);}
	public static long move_(IFluidTank[] aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move_(aFrom, aTo, Long.MAX_VALUE);}
	public static long move (IFluidTank[] aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {return aFrom != null && aTo != null && aTo.mTileEntity instanceof IFluidHandler ? move_(aFrom, aTo, aMaxMoved) : 0;}
	public static long move_(IFluidTank[] aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {if (aMaxMoved <= 0) return 0; long rAmount = 0; for (IFluidTank tFrom : aFrom) if (tFrom != null) rAmount += move_(tFrom, aTo, aMaxMoved-rAmount); return rAmount;}
	public static long move (@SuppressWarnings("rawtypes") Iterable aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move (aFrom, aTo, Long.MAX_VALUE);}
	public static long move_(@SuppressWarnings("rawtypes") Iterable aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move_(aFrom, aTo, Long.MAX_VALUE);}
	public static long move (@SuppressWarnings("rawtypes") Iterable aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {return aFrom != null && aTo != null && aTo.mTileEntity instanceof IFluidHandler ? move_(aFrom, aTo, aMaxMoved) : 0;}
	public static long move_(@SuppressWarnings("rawtypes") Iterable aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {if (aMaxMoved <= 0) return 0; long rAmount = 0; for (Object tFrom : aFrom) if (tFrom instanceof IFluidTank) rAmount += move_((IFluidTank)tFrom, aTo, aMaxMoved-rAmount); return rAmount;}
	
	
	public static String configName(FluidStack aFluid) {
		return aFluid == null || aFluid.getFluid() == null ? "" : aFluid.getFluid().getName();
	}
	
	public static String configNames(FluidStack... aFluids) {
		String rString = "";
		for (FluidStack tFluid : aFluids) rString += (tFluid == null ? "null;" : configName(tFluid) + ";");
		return rString;
	}
	
	public static String name(Fluid aFluid, boolean aLocalized) {
		if (aFluid == null) return "";
		if (!aLocalized) return aFluid.getUnlocalizedName();
		if (aFluid instanceof FluidGT) return LH.get(aFluid.getUnlocalizedName());
		String rName = aFluid.getLocalizedName(make(aFluid, 0));
		if (rName.startsWith("fluid.") || rName.startsWith("tile.") || rName.startsWith("rc ")) {
			rName = Code.capitaliseWords(rName.replaceFirst("fluid.", "").replaceFirst("tile.", "").replaceFirst("rc ", ""));
		}
		return aFluid.getName().startsWith("rc ") ? "Reika's " + rName : rName;
	}
	
	public static String name(FluidStack aFluid, boolean aLocalized) {
		return aFluid == null ? "" : name(aFluid.getFluid(), aLocalized);
	}
	
	public static String name(IFluidTank aTank, boolean aLocalized) {
		return aTank == null ? "" : name(aTank.getFluid(), aLocalized);
	}
	
	public static FluidStack[] copy(FluidStack... aFluids) {
		FluidStack[] rStacks = new FluidStack[aFluids.length];
		for (int i = 0; i < aFluids.length; i++) if (aFluids[i] != null) rStacks[i] = aFluids[i].copy();
		return rStacks;
	}
	
	public static final Map<ItemStackContainer, FluidContainerData> FULL_TO_DATA = new ItemStackMap<>();
	public static final Map<ItemStackContainer, Map<String, FluidContainerData>> EMPTY_TO_FLUID_TO_DATA = new ItemStackMap<>();
	
	public static void reg(FluidStack aFluid, ItemStack aFull, ItemStack aEmpty) {
		reg(aFluid, aFull, aEmpty, F);
	}
	public static void reg(FluidStack aFluid, ItemStack aFull, ItemStack aEmpty, boolean aOverrideFillingEmpty, boolean aOverrideDrainingFull) {
		reg(aFluid, aFull, aEmpty, F, aOverrideFillingEmpty, aOverrideDrainingFull);
	}
	public static void reg(FluidStack aFluid, ItemStack aFull, ItemStack aEmpty, boolean aNullEmpty) {
		reg(aFluid, aFull, aEmpty, aNullEmpty, F, F);
	}
	public static void reg(FluidStack aFluid, ItemStack aFull, ItemStack aEmpty, boolean aNullEmpty, boolean aOverrideFillingEmpty, boolean aOverrideDrainingFull) {
		if (aFluid == null || ST.invalid(aFull)) return;
		reg(new FluidContainerData(aFluid, aFull, aEmpty, aNullEmpty), aOverrideFillingEmpty, aOverrideDrainingFull);
	}
	public static void reg(FluidContainerData aData) {
		reg(aData, F, F);
	}
	public static void reg(FluidContainerData aData, boolean aOverrideFillingEmpty, boolean aOverrideDrainingFull) {
		set(aData, aOverrideFillingEmpty, aOverrideDrainingFull);
		FluidContainerRegistry.registerFluidContainer(aData);
	}
	
	public static void set(FluidContainerData aData) {
		set(aData, F, F);
	}
	public static void set(FluidContainerData aData, boolean aOverrideFillingEmpty, boolean aOverrideDrainingFull) {
		ItemStackContainer tFilled = new ItemStackContainer(aData.filledContainer), tEmpty = new ItemStackContainer(aData.emptyContainer);
		if (aOverrideDrainingFull || !FULL_TO_DATA.containsKey(tFilled)) FULL_TO_DATA.put(tFilled, aData);
		Map<String, FluidContainerData> tFluidToData = EMPTY_TO_FLUID_TO_DATA.get(tEmpty);
		if (tFluidToData == null) EMPTY_TO_FLUID_TO_DATA.put(tEmpty, tFluidToData = new HashMap<>());
		String tFluidName = aData.fluid.getFluid().getName();
		if (aOverrideFillingEmpty || !tFluidToData.containsKey(tFluidName)) tFluidToData.put(tFluidName, aData);
	}
	
	public static ItemStack fill(FluidStack aFluid, ItemStack aStack, boolean aRemoveFluidDirectly, boolean aCheckIFluidContainerItems) {
		return fill(aFluid, aStack, aRemoveFluidDirectly, aCheckIFluidContainerItems, F, T);
	}
	public static ItemStack fill(FluidStack aFluid, ItemStack aStack, boolean aRemoveFluidDirectly, boolean aCheckIFluidContainerItems, boolean aAllowPartialFilling) {
		return fill(aFluid, aStack, aRemoveFluidDirectly, aCheckIFluidContainerItems, aAllowPartialFilling, T);
	}
	public static ItemStack fill(FluidStack aFluid, ItemStack aStack, boolean aRemoveFluidDirectly, boolean aCheckIFluidContainerItems, boolean aAllowPartialFilling, boolean aIsNonCannerCheck) {
		if (ST.invalid(aStack) || aFluid == null) return NI;
		if (aFluid.getFluid() == FluidRegistry.WATER && ST.equal(aStack, Items.glass_bottle)) {
			if (aFluid.amount >= 250) {
				if (aRemoveFluidDirectly) aFluid.amount -= 250;
				return ST.make(Items.potionitem, 1, 0);
			}
			return NI;
		}
		if (aIsNonCannerCheck && IL.GC_Canister.exists() && (IL.GC_Canister.equal(aStack, T, T) || ST.equal(ST.container(aStack, T), IL.GC_Canister.wild(1)))) return aStack;
		if (aCheckIFluidContainerItems && aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) > 0 && (((IFluidContainerItem)aStack.getItem()).getFluid(aStack) == null || (equal(((IFluidContainerItem)aStack.getItem()).getFluid(aStack), aFluid) && ((IFluidContainerItem)aStack.getItem()).getFluid(aStack).amount < ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack))) && (aAllowPartialFilling || ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) <= aFluid.amount)) {
			if (IL.Cell_Universal_Fluid.equal(aStack, T, T) && (temperature(aFluid, DEF_ENV_TEMP) > MT.Sn.mMeltingPoint || !simple(aFluid) || acid(aFluid) || powerconducting(aFluid))) return aStack;
			if (aRemoveFluidDirectly)
				aFluid.amount -= ((IFluidContainerItem)aStack.getItem()).fill(aStack = ST.amount(1, aStack), aFluid, T);
			else
				((IFluidContainerItem)aStack.getItem()).fill(aStack = ST.amount(1, aStack), aFluid, T);
			return aStack;
		}
		Map<String, FluidContainerData> tFluidToContainer = EMPTY_TO_FLUID_TO_DATA.get(new ItemStackContainer(aStack));
		if (tFluidToContainer == null) return NI;
		FluidContainerData tData = tFluidToContainer.get(aFluid.getFluid().getName());
		if (tData == null || tData.fluid.amount > aFluid.amount) return NI;
		if (aRemoveFluidDirectly) aFluid.amount -= tData.fluid.amount;
		return ST.amount(1, tData.filledContainer);
	}
	
	public static ItemStack fill(IFluidTank aTank, ItemStack aStack, boolean aRemoveFluidDirectly, boolean aCheckIFluidContainerItems) {
		return fill(aTank, aStack, aRemoveFluidDirectly, aCheckIFluidContainerItems, F, T);
	}
	public static ItemStack fill(IFluidTank aTank, ItemStack aStack, boolean aRemoveFluidDirectly, boolean aCheckIFluidContainerItems, boolean aAllowPartialFilling) {
		return fill(aTank, aStack, aRemoveFluidDirectly, aCheckIFluidContainerItems, aAllowPartialFilling, T);
	}
	public static ItemStack fill(IFluidTank aTank, ItemStack aStack, boolean aRemoveFluidDirectly, boolean aCheckIFluidContainerItems, boolean aAllowPartialFilling, boolean aIsNonCannerCheck) {
		if (aTank == null) return NI;
		FluidStack aFluid = aTank.getFluid();
		if (ST.invalid(aStack) || aFluid == null) return NI;
		if (aFluid.getFluid() == FluidRegistry.WATER && ST.equal(aStack, Items.glass_bottle)) {
			if (aFluid.amount >= 250) {
				if (aRemoveFluidDirectly) aTank.drain(250, T);
				return ST.make(Items.potionitem, 1, 0);
			}
			return NI;
		}
		if (aIsNonCannerCheck && IL.GC_Canister.exists() && (IL.GC_Canister.equal(aStack, T, T) || ST.equal(ST.container(aStack, T), IL.GC_Canister.wild(1)))) return aStack;
		if (aCheckIFluidContainerItems && aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) > 0 && (((IFluidContainerItem)aStack.getItem()).getFluid(aStack) == null || (equal(((IFluidContainerItem)aStack.getItem()).getFluid(aStack), aFluid) && ((IFluidContainerItem)aStack.getItem()).getFluid(aStack).amount < ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack))) && (aAllowPartialFilling || ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) <= aFluid.amount)) {
			if (IL.Cell_Universal_Fluid.equal(aStack, T, T) && (temperature(aFluid, DEF_ENV_TEMP) > MT.Sn.mMeltingPoint || !simple(aFluid) || acid(aFluid) || powerconducting(aFluid))) return aStack;
			if (aRemoveFluidDirectly)
				aTank.drain(((IFluidContainerItem)aStack.getItem()).fill(aStack = ST.amount(1, aStack), aFluid, T), T);
			else
				((IFluidContainerItem)aStack.getItem()).fill(aStack = ST.amount(1, aStack), aFluid, T);
			return aStack;
		}
		Map<String, FluidContainerData> tFluidToContainer = EMPTY_TO_FLUID_TO_DATA.get(new ItemStackContainer(aStack));
		if (tFluidToContainer == null) return NI;
		FluidContainerData tData = tFluidToContainer.get(aFluid.getFluid().getName());
		if (tData == null || tData.fluid.amount > aFluid.amount) return NI;
		if (aRemoveFluidDirectly) aTank.drain(tData.fluid.amount, T);
		return ST.amount(1, tData.filledContainer);
	}
	
	public static boolean contains(ItemStack aStack, FluidStack aFluid, boolean aCheckIFluidContainerItems) {
		if (ST.invalid(aStack) || aFluid == null) return F;
		if (aCheckIFluidContainerItems && aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) > 0) return aFluid.isFluidEqual(((IFluidContainerItem)aStack.getItem()).getFluid(aStack = ST.amount(1, aStack)));
		FluidContainerData tData = FULL_TO_DATA.get(new ItemStackContainer(aStack));
		return tData!=null && tData.fluid.isFluidEqual(aFluid);
	}
	
	public static FluidStack getFluid(ItemStack aStack, boolean aCheckIFluidContainerItems) {
		if (ST.invalid(aStack)) return null;
		if (aCheckIFluidContainerItems && aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) > 0) {
			FluidStack rFluid = ((IFluidContainerItem)aStack.getItem()).drain(ST.amount(1, aStack), Integer.MAX_VALUE, T);
			if (IL.Cell_Universal_Fluid.equal(aStack, T, T) && (temperature(rFluid, DEF_ENV_TEMP) > MT.Sn.mMeltingPoint || !simple(rFluid) || acid(rFluid) || powerconducting(rFluid))) return NF;
			return rFluid;
		}
		FluidContainerData tData = FULL_TO_DATA.get(new ItemStackContainer(aStack));
		return tData==null?NF:tData.fluid.copy();
	}
	
	public static ItemStack getEmpty(ItemStack aStack, boolean aCheckIFluidContainerItems) {
		if (ST.invalid(aStack)) return NI;
		FluidContainerData tData = FULL_TO_DATA.get(new ItemStackContainer(aStack));
		if (tData != null) return ST.amount(1, tData.emptyContainer);
		if (aCheckIFluidContainerItems && aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) > 0) {
			((IFluidContainerItem)aStack.getItem()).drain(aStack = ST.amount(1, aStack), Integer.MAX_VALUE, T);
			if (aStack.getTagCompound() == null) return aStack;
			if (aStack.getTagCompound().hasNoTags()) aStack.setTagCompound(null);
			return aStack;
		}
		return NI;
	}
	
	
	
	
	/** Loads a FluidStack properly. */
	public static FluidStack load (NBTTagCompound aNBT, String aTagName) {return aNBT == null ? null : load(aNBT.getCompoundTag(aTagName));}
	/** Loads a FluidStack properly. */
	public static FluidStack load (NBTTagCompound aNBT) {return aNBT == null || aNBT.hasNoTags() ? null : load_(aNBT);}
	/** Loads a FluidStack properly. */
	public static FluidStack load_(NBTTagCompound aNBT) {
		if (aNBT == null) return null;
		String aName = aNBT.getString("FluidName");
		if (Code.stringInvalid(aName)) return null;
		String tName = FluidsGT.FLUID_RENAMINGS.get(aName);
		Fluid aFluid;
		if (Code.stringValid(tName) && (aFluid = fluid(tName)) != null) {
			aName = tName;
		} else {
			aFluid = fluid(aName);
		}
		if (aFluid == null) {
			if (FL.LubRoCant      .is(aName)) return FL.Lubricant    .make(aNBT.getInteger("Amount"));
			if (FL.Reikanol       .is(aName)) return FL.BioEthanol   .make(aNBT.getInteger("Amount"));
			if (FL.Liquid_Reikygen.is(aName)) return FL.Oxygen       .make(aNBT.getInteger("Amount"));
			if (FL.Reikygen       .is(aName)) return FL.Liquid_Oxygen.make(aNBT.getInteger("Amount"));
			return null;
		}
		FluidStack rFluid = new FluidStack(aFluid, aNBT.getInteger("Amount"));
		if (aNBT.hasKey("Tag")) rFluid.tag = aNBT.getCompoundTag("Tag");
		return rFluid;
	}
	
	/** Saves a FluidStack properly. */
	public static NBTTagCompound save(NBTTagCompound aNBT, String aTagName, FluidStack aFluid) {
		if (aNBT == null) aNBT = NBT.make();
		NBTTagCompound tNBT = save(aFluid);
		if (tNBT != null) aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves a FluidStack properly. */
	public static NBTTagCompound save (FluidStack aFluid) {return aFluid == null || aFluid.getFluid() == null ? null : save_(aFluid);}
	/** Saves a FluidStack properly. */
	public static NBTTagCompound save_(FluidStack aFluid) {return aFluid.writeToNBT(NBT.make());}
	
	
	@SafeVarargs public static Fluid createLiquid(OreDictMaterial aMaterial, Set<String>... aFluidList) {return createLiquid(aMaterial, aMaterial.mTextureSetsBlock.get(IconsGT.INDEX_BLOCK_MOLTEN), aFluidList);}
	@SafeVarargs public static Fluid createLiquid(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return create(aMaterial.mNameInternal.toLowerCase(), aTexture, aMaterial.mNameLocal, aMaterial, aMaterial.mRGBaLiquid, STATE_LIQUID, 1000, aMaterial.mMeltingPoint <= 0 ? 1000 : aMaterial.mMeltingPoint < 300 ? Math.min(300, aMaterial.mBoilingPoint - 1) : aMaterial.mMeltingPoint, null, null, 0, aFluidList);}

	@SafeVarargs public static Fluid createMolten(OreDictMaterial aMaterial, Set<String>... aFluidList) {return createMolten(aMaterial, L, aFluidList);}
	@SafeVarargs public static Fluid createMolten(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return createMolten(aMaterial, L, aTexture, aFluidList);}
	@SafeVarargs public static Fluid createMolten(OreDictMaterial aMaterial, long aAmount, Set<String>... aFluidList) {return createMolten(aMaterial, aAmount, aMaterial.mTextureSetsBlock.get(IconsGT.INDEX_BLOCK_MOLTEN), aFluidList);}
	@SafeVarargs public static Fluid createMolten(OreDictMaterial aMaterial, long aAmount, IIconContainer aTexture, Set<String>... aFluidList) {return create("molten."+aMaterial.mNameInternal.toLowerCase(), aTexture, "Molten " + aMaterial.mNameLocal, aMaterial, aMaterial.mRGBaLiquid, STATE_LIQUID, aAmount, aMaterial.mMeltingPoint <= 0 ? 1000 : aMaterial.mMeltingPoint < 300 ? Math.min(300, aMaterial.mBoilingPoint - 1) : aMaterial.mMeltingPoint, null, null, 0, aFluidList).setLuminosity(10);}
	
	@SafeVarargs public static Fluid createGas(OreDictMaterial aMaterial, Set<String>... aFluidList) {return createGas(aMaterial, aMaterial.mTextureSetsBlock.get(IconsGT.INDEX_BLOCK_GAS), aFluidList);}
	@SafeVarargs public static Fluid createGas(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return create(aMaterial.mNameInternal.toLowerCase(), aTexture, aMaterial.mNameLocal, aMaterial, aMaterial.mRGBaGas, STATE_GASEOUS, 1000, aMaterial.mBoilingPoint <= 0 ? 3000 : aMaterial.mBoilingPoint < 300 ? Math.min(300, aMaterial.mPlasmaPoint - 1) : aMaterial.mBoilingPoint, null, null, 0, aFluidList);}
	
	@SafeVarargs public static Fluid createVapour(OreDictMaterial aMaterial, Set<String>... aFluidList) {return createVapour(aMaterial, aMaterial.mTextureSetsBlock.get(IconsGT.INDEX_BLOCK_GAS), aFluidList);}
	@SafeVarargs public static Fluid createVapour(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return create("vapor."+aMaterial.mNameInternal.toLowerCase(), aTexture, "Vaporized " + aMaterial.mNameLocal, aMaterial, aMaterial.mRGBaGas, STATE_GASEOUS, 8*L, aMaterial.mBoilingPoint <= 0 ? 3000 : aMaterial.mBoilingPoint < 300 ? Math.min(300, aMaterial.mPlasmaPoint - 1) : aMaterial.mBoilingPoint, null, null, 0, aFluidList);}
	
	@SafeVarargs public static Fluid createPlasma(OreDictMaterial aMaterial, Set<String>... aFluidList) {return createPlasma(aMaterial, aMaterial.mTextureSetsBlock.get(IconsGT.INDEX_BLOCK_PLASMA), aFluidList);}
	@SafeVarargs public static Fluid createPlasma(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return create("plasma."+aMaterial.mNameInternal.toLowerCase(), aTexture, aMaterial.mNameLocal + " Plasma", aMaterial, aMaterial.mRGBaPlasma, STATE_PLASMA, L*L, aMaterial.mPlasmaPoint <= 0 ? 10000 : Math.max(300, aMaterial.mPlasmaPoint), null, null, 0, aFluidList);}
	
	@SafeVarargs public static Fluid create(String aName, String aLocalized, OreDictMaterial aMaterial, int aState, Set<String>... aFluidList) {return create(aName, aLocalized, aMaterial, aState, 1000, 300, null, null, 0, aFluidList);}
	@SafeVarargs public static Fluid create(String aName, String aLocalized, OreDictMaterial aMaterial, int aState, long aAmountPerUnit, long aTemperatureK, Set<String>... aFluidList) {return create(aName, aLocalized, aMaterial, aState, aAmountPerUnit, aTemperatureK, null, null, 0, aFluidList);}
	@SafeVarargs public static Fluid create(String aName, String aLocalized, OreDictMaterial aMaterial, int aState, long aAmountPerUnit, long aTemperatureK, ItemStack aFullContainer, ItemStack aEmptyContainer, int aFluidAmount, Set<String>... aFluidList) {return create(aName, new Textures.BlockIcons.CustomIcon("fluids/" + aName.toLowerCase()), aLocalized, aMaterial, null, aState, aAmountPerUnit, aTemperatureK, aFullContainer, aEmptyContainer, aFluidAmount, aFluidList);}
	
	@SafeVarargs
	public static Fluid create(String aName, IIconContainer aTexture, String aLocalized, OreDictMaterial aMaterial, short[] aRGBa, int aState, long aAmountPerUnit, long aTemperatureK, ItemStack aFullContainer, ItemStack aEmptyContainer, int aFluidAmount, Set<String>... aFluidList) {
		aName = aName.toLowerCase();
		aLocalized = (aLocalized==null?aMaterial==null||aMaterial==MT.NULL?UT.Code.capitaliseWords(aName):aMaterial.getLocal():aLocalized);
		
		Fluid rFluid = new FluidGT(aName, aTexture, aRGBa == null ? UNCOLOURED : aRGBa, aTemperatureK, aState == 2 || aState == 3);
		LH.add(rFluid.getUnlocalizedName(), aLocalized);
		LH.add(rFluid.getUnlocalizedName()+".name", aLocalized);
		
		for (Set<String> tSet : aFluidList) tSet.add(aName);
		
		switch (aState) {
		case STATE_SOLID  : rFluid.setViscosity(10000); break;
		case STATE_LIQUID : rFluid.setViscosity( 1000); FluidsGT.LIQUID.add(aName); break;
		case STATE_GASEOUS: rFluid.setViscosity(  200); rFluid.setDensity(   -100); FluidsGT.GAS.add(aName); break;
		case STATE_PLASMA : rFluid.setViscosity(   10); rFluid.setDensity(-100000); rFluid.setLuminosity(15); FluidsGT.PLASMA.add(aName); break;
		case 4            : rFluid.setViscosity( 1000); break;
		}
		
		if (FL.exists(aName) || !FluidRegistry.registerFluid(rFluid)) {
			rFluid = FluidRegistry.getFluid(aName);
			LH.add(rFluid.getUnlocalizedName(), aLocalized);
			if (rFluid.getTemperature() == new Fluid("test").getTemperature() || rFluid.getTemperature() <= 0) rFluid.setTemperature(UT.Code.bindInt(aTemperatureK));
			rFluid.setGaseous(aState == 2 || aState == 3);
		}
		
		if (aMaterial != null) {
			if (aMaterial.contains(TD.Properties.ACID    )) FluidsGT.ACID.add(aName);
			if (aMaterial.contains(TD.Properties.GLOWING )) rFluid.setLuminosity(Math.max(rFluid.getLuminosity(), 5));
			if (aMaterial.contains(TD.Properties.LIGHTING)) rFluid.setLuminosity(Math.max(rFluid.getLuminosity(), 15));
			switch (aState) {
			case STATE_LIQUID : aMaterial.liquid(make(rFluid, UT.Code.bindInt(aAmountPerUnit))); break;
			case STATE_GASEOUS: aMaterial.gas   (make(rFluid, UT.Code.bindInt(aAmountPerUnit))); break;
			case STATE_PLASMA : aMaterial.plasma(make(rFluid, UT.Code.bindInt(aAmountPerUnit))); break;
			}
			// Translating Real Life Density to that weird Integer based Density System.
			if (aMaterial.mGramPerCubicCentimeter > 0 && (aState == STATE_LIQUID || aState == STATE_GASEOUS)) {
				if (aMaterial.mGramPerCubicCentimeter > WEIGHT_AIR_G_PER_CUBIC_CENTIMETER) {
					rFluid.setDensity(UT.Code.bindInt((long)(1000 * aMaterial.mGramPerCubicCentimeter)));
				} else if (aMaterial.mGramPerCubicCentimeter < WEIGHT_AIR_G_PER_CUBIC_CENTIMETER) {
					rFluid.setDensity(UT.Code.bindInt((long)(-0.1 / aMaterial.mGramPerCubicCentimeter)));
				} else {
					rFluid.setDensity(0);
				}
			}
		}
		
		if (aFullContainer != null && aEmptyContainer != null && !FluidContainerRegistry.registerFluidContainer(make(rFluid, aFluidAmount), aFullContainer, aEmptyContainer)) {
			RM.Canner.addRecipe1(T, 16, Math.max(aFluidAmount / 64, 16), aFullContainer, NF, make(rFluid, aFluidAmount), ST.container(aFullContainer, F));
		}
		return rFluid;
	}
}
