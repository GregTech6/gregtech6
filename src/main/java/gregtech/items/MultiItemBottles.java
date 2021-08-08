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

package gregtech.items;

import static gregapi.data.CS.*;

import gregapi.data.CS.Sandwiches;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.data.TC;
import gregapi.data.TD;
import gregapi.item.CreativeTab;
import gregapi.item.IItemRottable;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.item.multiitem.food.FoodStatFluid;
import gregapi.oredict.OreDictItemData;
import gregapi.util.CR;
import gregapi.util.ST;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MultiItemBottles extends MultiItemRandom implements IItemRottable {
	public MultiItemBottles() {
		super(MD.GT.mID, "gt.multiitem.bottles");
		setCreativeTab(new CreativeTab(getUnlocalizedName(), "GregTech: Bottles", this, (short)1600));
	}
	
	@Override
	public void addItems() {
		@SuppressWarnings("unused")
		int tLastID = 0;
		
		addItem(tLastID =     0, "Mineral Water"                , ""                                    , FL.Mineralwater           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 2), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =     1, "Sea Water"                    , ""                                    , FL.Ocean                  .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.TEMPESTAS      , 2), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE, FL.OceanGrC.make(250), FL.Tropics_Water.make(250));
		addItem(tLastID =     2, "Soda"                         , "", "foodBubblywater"                 , FL.Soda                   .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.TEMPESTAS      , 2), TC.stack(TC.AQUA      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =     3, "Mineral Soda"                 , ""                                    , FL.Mineralsoda            .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.TEMPESTAS      , 2), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =     4, "Ice Water"                    , ""                                    , FL.Ice                    .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 2), TC.stack(TC.AQUA      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =     5, "Dirty Water"                  , ""                                    , FL.Dirty_Water            .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 2), TC.stack(TC.VENENUM   , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =     6, "Swampwater"                   , ""                                    , FL.Swampwater             .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 2), TC.stack(TC.MORTUUS   , 1), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN);
		addItem(tLastID =     7, "Stagnant Water"               , ""                                    , FL.Stagnant_Water         .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 2), TC.stack(TC.MORTUUS   , 1), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN);
		addItem(tLastID =     8, "Spectral Dew"                 , "", "listAllwater"                    , FL.SpDew                  .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 2), TC.stack(TC.SPIRITUS  , 1), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN);
		
		
		addItem(tLastID =   100, "Grape Juice"                  , "", "foodGrapejuice"                  , FL.Juice_Grape_White      .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   101, "White Wine"                   , "", "foodGrapewine"                   , FL.Wine_Grape_White       .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   102, "Grape Vinegar"                , "", "foodVinegar"                     , FL.Vinegar_Grape          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 2), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Lime);
		addItem(tLastID =   103, "Grape Juice"                  , "", "foodGrapejuice"                  , FL.Juice_Grape_Red        .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   104, "Red Wine"                     , "", "foodGrapewine"                   , FL.Wine_Grape_Red         .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   105, "Grape Smoothie"               , "", "foodGrapesmoothie"               , FL.Smoothie_Grape_White   .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   106, "Grape Smoothie"               , "", "foodGrapesmoothie"               , FL.Smoothie_Grape_Red     .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   107, "Grape Smoothie"               , "", "foodGrapesmoothie"               , FL.Smoothie_Grape_Green   .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   108, "Grape Smoothie"               , "", "foodGrapesmoothie"               , FL.Smoothie_Grape_Purple  .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   109, "Grape Juice"                  , "", "foodGrapejuice"                  , FL.Juice_Grape_Green      .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   110, "Grape Juice"                  , "", "foodGrapejuice"                  , FL.Juice_Grape_Purple     .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   111, "Wine"                         , "", "foodGrapewine"                   , FL.Wine_Grape_Green       .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   112, "Ricardo Sanchez"              , "", "foodGrapewine"                   , FL.Wine_Grape_Purple      .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.COGNITIO  , 1), FoodStatFluid.INSTANCE);
		
		
		addItem(tLastID =   200, "Lemon Juice"                  , "", "foodLemonjuice"                  , FL.Juice_Lemon            .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.PERFODIO  , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   201, "Lemonade"                     , "", "foodLemonade"                    , FL.Lemonade               .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.PERFODIO  , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   202, "Limoncello"                   , "", "foodLemonwine"                   , FL.Wine_Lemon             .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.PERFODIO  , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   203, "Alcopops"                     , "", "listAllbeverage"                 , FL.Alcopops               .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.VINCULUM  , 1), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN);
		addItem(tLastID =   204, "Cave Johnson's Grenade Juice" , ""                                    , FL.Grenade_Juice          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.MORTUUS        , 1), TC.stack(TC.PERDITIO  , 1), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Yellow);
		addItem(tLastID =   205, "Lemon Smoothie"               , "", "foodLemonsmoothie"               , FL.Smoothie_Lemon         .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.PERFODIO  , 1), FoodStatFluid.INSTANCE);
		
		
		addItem(tLastID =   300, "Potato Juice"                 , ""                                    , FL.Juice_Potato           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.MESSIS    , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   301, "Vodka"                        , "", "foodVodka"                       , FL.Vodka                  .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.TELUM     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   302, "Leninade"                     , "", "listAllbeverage"                 , FL.Leninade               .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 2), TC.stack(TC.TELUM     , 2), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN);
		
		
		addItem(tLastID =   400, "Reedwater"                    , ""                                    , FL.Juice_Reed             .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   401, "Rum"                          , "", "foodRum"                         , FL.Rum_White              .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.LUCRUM    , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   402, "Pirate Brew"                  , "", "foodRum"                         , FL.Rum_Dark               .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 2), TC.stack(TC.LUCRUM    , 2), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN);
		addItem(tLastID =   403, "Cane Vinegar"                 , "", "foodVinegar"                     , FL.Vinegar_Cane           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 2), TC.stack(TC.LUCRUM    , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   404, "Piña Colada"                  , ""                                    , FL.Pina_Colada            .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.HERBA     , 2), FoodStatFluid.INSTANCE);
		
		
		addItem(tLastID =   500, "Hops Mash"                    , ""                                    , FL.Mash_Hops              .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.MESSIS    , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   501, "Dark Beer"                    , "", "foodBeer"                        , FL.Beer_Dark              .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.POTENTIA  , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   502, "Dragon Blood"                 , "", "foodBeer"                        , FL.Beer_Dragonblood       .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 2), TC.stack(TC.POTENTIA  , 2), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN);
		
		
		addItem(tLastID =   600, "Wheaty Mash"                  , ""                                    , FL.Mash_Wheat             .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.MESSIS    , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   601, "Scotch"                       , "", "foodWhiskey"                     , FL.Whiskey_Scotch         .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.TUTAMEN   , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   602, "Glen McKenner"                , "", "foodWhiskey"                     , FL.Whiskey_GlenMcKenner   .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 2), TC.stack(TC.TUTAMEN   , 2), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN);
		
		
		addItem(tLastID =   700, "Wheaty Hops Mash"             , ""                                    , FL.Mash_WheatHops         .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.MESSIS    , 2), FoodStatFluid.INSTANCE);
		IL.Bottle_Beer.set(
		addItem(tLastID =   701, "Beer"                         , "", "foodBeer"                        , FL.Beer                   .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.PERFODIO  , 1), FoodStatFluid.INSTANCE));
		
		
		addItem(tLastID =   800, "Chili Sauce"                  , "", "foodHotsauce"                    , FL.Sauce_Chili            .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.IGNIS     , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Red);
		addItem(tLastID =   801, "Hot Sauce"                    , "", "foodHotsauce"                    , FL.Sauce_Hot              .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.IGNIS     , 2), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Red);
		addItem(tLastID =   802, "Diabolo Sauce"                , "", "foodHotsauce"                    , FL.Sauce_Diabolo          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 2), TC.stack(TC.IGNIS     , 3), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Red);
		addItem(tLastID =   803, "Diablo Sauce"                 , "", "foodHotsauce"                    , FL.Sauce_Diablo           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 2), TC.stack(TC.IGNIS     , 4), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Red);
		addItem(tLastID =   804, "Old Man Snitches glitchy Diablo Sauce", "", "foodHotsauce"            , FL.Sauce_Snitches         .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 3), TC.stack(TC.IGNIS     , 5), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Red);
		addItem(tLastID =   805, "Barbecue Sauce"               , "", "foodBarbecuesauce"               , FL.Sauce_BBQ              .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.LIMUS          , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Brown);
		
		
		addItem(tLastID =   900, "Apple Juice"                  , "", "foodApplejuice"                  , FL.Juice_Apple            .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE, FL.Juice_AppleGrC.make(250));
		addItem(tLastID =   901, "Cider"                        , "", "foodApplecider"                  , FL.Cider_Apple            .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.TUTAMEN   , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =   902, "Apple Cider Vinegar"          , "", "foodVinegar"                     , FL.Vinegar_Apple          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 2), TC.stack(TC.TUTAMEN   , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Lime);
		addItem(tLastID =   905, "Apple Smoothie"               , "", "foodApplesmoothie"               , FL.Smoothie_Apple         .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		
		addItem(tLastID =  1000, "Olive Oil"                    , "", OP.bottle.dat(MT.OliveOil)        , FL.Oil_Olive              .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.LUX       , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Lime);
		addItem(tLastID =  1001, "Sunflower Oil"                , "", OP.bottle.dat(MT.SunflowerOil)    , FL.Oil_Sunflower          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.LUX       , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Yellow);
		addItem(tLastID =  1002, "Nut Oil"                      , "", OP.bottle.dat(MT.NutOil)          , FL.Oil_Nut                .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.LUX       , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Yellow);
		addItem(tLastID =  1003, "Seed Oil"                     , "", OP.bottle.dat(MT.SeedOil)         , FL.Oil_Seed               .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.LUX       , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Lime);
		addItem(tLastID =  1004, "Hemp Oil"                     , "", OP.bottle.dat(MT.HempOil)         , FL.Oil_Hemp               .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.LUX       , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Lime);
		addItem(tLastID =  1005, "Lin Oil"                      , "", OP.bottle.dat(MT.LinOil)          , FL.Oil_Lin                .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.LUX       , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Lime);
		addItem(tLastID =  1006, "Fish Oil"                     , "", OP.bottle.dat(MT.FishOil)         , FL.Oil_Fish               .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.LUX       , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Yellow);
		
		
		addItem(tLastID =  1020, "Mayo"                         , "", "foodMayo"                        , FL.Mayo                   .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.LIMUS          , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_White);
		addItem(tLastID =  1021, "Dressing"                     , "", "foodSaladdressing"               , FL.Dressing               .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.HERBA          , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Lime);
		
		IL.Bottle_Milk.set(
		addItem(tLastID =  1100, "Milk"                         , "", OP.bottle.dat(MT.Milk)            , FL.Milk                   .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE, FL.MilkGrC.make(250)));
		addItem(tLastID =  1101, "Heavy Cream"                  , "", "bottleCream"                     , FL.Cream                  .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.LIMUS          , 1), TC.stack(TC.POTENTIA  , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_White);
		IL.Bottle_Milk_Spoiled.set(
		addItem(tLastID =  1102, "Milk"                         , ""                                    , FL.Milk_Spoiled           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.VENENUM   , 1), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN));
		
		CR.shapeless(make(4, 1100), CR.DEF, new Object[] {OD.container1000milk, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(3, 1100), CR.DEF, new Object[] {OD.container1000milk, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(2, 1100), CR.DEF, new Object[] {OD.container1000milk, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(1, 1100), CR.DEF, new Object[] {OD.container1000milk, OP.bottle.dat(MT.Empty)});
		
		
		addItem(tLastID =  1200, "Soy Milk"                     , "", "foodSoymilk"                     , FL.MilkSoy                .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.HERBA          , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		
		CR.shapeless(make(4, 1200), CR.DEF, new Object[] {OD.container1000soymilk, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(3, 1200), CR.DEF, new Object[] {OD.container1000soymilk, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(2, 1200), CR.DEF, new Object[] {OD.container1000soymilk, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(1, 1200), CR.DEF, new Object[] {OD.container1000soymilk, OP.bottle.dat(MT.Empty)});
		
		
		addItem(tLastID =  1300, "Honey", "Why does this Bottle look like a Bear and not a Bee?"        , FL.Honey                  .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.BESTIA         , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE, FL.HoneyGrC.make(250), FL.HoneyBoP.make(250), OP.bottle.dat(MT.Honey)); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Yellow);
		addItem(tLastID =  1301, "Honeydew"                     , "", OP.bottle.dat(MT.Honeydew)        , FL.Honeydew               .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.BESTIA         , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  1302, "Royal Jelly"                  , ""                                    , FL.RoyalJelly             .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.BESTIA         , 2), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Yellow);
		addItem(tLastID =  1303, "Ambrosia"                     , "", "foodAmbrosia"                    , FL.Ambrosia               .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.BESTIA         , 1), TC.stack(TC.SANO      , 2), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Purple);
		addItem(tLastID =  1304, "Short Mead"                   , "", "foodMead"                        , FL.ShortMead              .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  1305, "Mead"                         , "", "foodMead"                        , FL.Mead                   .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		
		CR.shapeless(make(4, 1300), CR.DEF, new Object[] {OD.container1000honey, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(3, 1300), CR.DEF, new Object[] {OD.container1000honey, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(2, 1300), CR.DEF, new Object[] {OD.container1000honey, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(1, 1300), CR.DEF, new Object[] {OD.container1000honey, OP.bottle.dat(MT.Empty)});
		
		
		addItem(tLastID =  1400, "Golden Apple Juice"           , "", "foodApplejuice"      , FL.make("potion.goldenapplejuice"  , 250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN);
		addItem(tLastID =  1401, "Golden Cider"                 , "", "foodApplecider"      , FL.make("potion.goldencider"       , 250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.TUTAMEN   , 1), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN);
		
		addItem(tLastID =  1500, "Idun's Apple Juice"           , "", "foodApplejuice"      , FL.make("potion.idunsapplejuice"   , 250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.NEBRISUM  , 9), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN);
		addItem(tLastID =  1501, "Notches Brew"                 , "", "foodApplecider"      , FL.make("potion.notchesbrew"       , 250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 2), TC.stack(TC.NEBRISUM  , 9), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN);
		
		IL.Bottle_Purple_Drink.set(
		addItem(tLastID =  1600, "Purple Drink"                 , ""                                    , FL.Purple_Drink           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.VINCULUM  , 1), FoodStatFluid.INSTANCE));
		IL.Bottle_Rotten_Drink.set(
		addItem(tLastID =  1601, "Rotten Drink"                 , ""                                    , FL.Rotten_Drink           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.MORTUUS   , 1), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN));
		
		IL.Bottle_Holy_Water.set(
		addItem(tLastID =  1700, "Holy Water"                   , "", OP.bottle.dat(MT.HolyWater)       , MT.HolyWater          .liquid(U4, F), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.AURAM     , 1), FoodStatFluid.INSTANCE));
		
		addItem(tLastID =  1800, "Rice Water"                   , ""                                    , FL.Mash_Rice              .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  1801, "Sake"                         , "", "foodSake"                        , FL.Sake                   .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.TEMPESTAS , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  1802, "Rice Vinegar"                 , "", "foodVinegar"                     , FL.Vinegar_Rice           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 2), TC.stack(TC.TEMPESTAS , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Lime);
		
		addItem(tLastID =  1900, "Chocolate Cream"              , "", "foodChocolatecream"              , FL.Cream_Chocolate        .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.LIMUS          , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Brown);
		addItem(tLastID =  1901, "Nutella"                      , "", "foodNutella"                     , FL.Cream_Nutella          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.LIMUS          , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Brown);
		
		addItem(tLastID =  2000, "Strawberry Juice"             , "", "foodStrawberryjuice"             , FL.Juice_Strawberry       .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  2005, "Strawberry Smoothie"          , "", "foodStrawberrysmoothie"          , FL.Smoothie_Strawberry    .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  2100, "Banana Juice"                 , "", "foodBananajuice"                 , FL.Juice_Banana           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  2105, "Banana Smoothie"              , "", "foodBananasmoothie"              , FL.Smoothie_Banana        .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  2200, "Green Slime Bottle"           , "Can be used as Glue too", "itemGlue" , FL.Slime_Green            .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.LIMUS     , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Lime);
		addItem(tLastID =  2201, "Pink Slime Bottle"            , "Can be used as Glue too", "itemGlue" , FL.Slime_Pink             .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.CORPUS         , 1), TC.stack(TC.LIMUS     , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Pink);
		addItem(tLastID =  2210, "BAWLS"                        , ""                                    , FL.BAWLS                  .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.TEMPESTAS      , 1), TC.stack(TC.LIMUS     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  2300, "Melon Juice"                  , "", "foodMelonjuice"                  , FL.Juice_Melon            .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 2), FoodStatFluid.INSTANCE);
		addItem(tLastID =  2305, "Melon Smoothie"               , "", "foodMelonsmoothie"               , FL.Smoothie_Melon         .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 2), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  2400, "Juice"                        , "", "foodJuice"                       , FL.Juice                  .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  2405, "Froot Smoothie"               , "", "foodFruitsmoothie"               , FL.Smoothie_Fruit         .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  2500, "Kiwi Juice"                   , "", "foodKiwijuice"                   , FL.Juice_Kiwi             .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.PERFODIO  , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  2505, "Kiwi Smoothie"                , "", "foodKiwismoothie"                , FL.Smoothie_Kiwi          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.PERFODIO  , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  2600, "Raspberry Juice"              , "", "foodRaspberryjuice"              , FL.Juice_Raspberry        .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  2605, "Raspberry Smoothie"           , "", "foodRaspberrysmoothie"           , FL.Smoothie_Raspberry     .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  2700, "Blackberry Juice"             , "", "foodBlackberryjuice"             , FL.Juice_Blackberry       .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  2705, "Blackberry Smoothie"          , "", "foodBlackberrysmoothie"          , FL.Smoothie_Blackberry    .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  2800, "Blueberry Juice"              , "", "foodBlueberryjuice"              , FL.Juice_Blueberry        .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE, DYE_OREDICTS[DYE_INDEX_Blue]);
		addItem(tLastID =  2805, "Blueberry Smoothie"           , "", "foodBlueberrysmoothie"           , FL.Smoothie_Blueberry     .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  2900, "Cranberry Juice"              , "", "foodCranberryjuice"              , FL.Juice_Cranberry        .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  2905, "Cranberry Smoothie"           , "", "foodCranberrysmoothie"           , FL.Smoothie_Cranberry     .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  3000, "Gooseberry Juice"             , "", "foodGooseberryjuice"             , FL.Juice_Gooseberry       .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  3005, "Gooseberry Smoothie"          , "", "foodGooseberrysmoothie"          , FL.Smoothie_Gooseberry    .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  3100, "Tomato Juice"                 , "", "foodTomatojuice"                 , FL.Juice_Tomato           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  3101, "Tomato Ketchup"               , "", "foodKetchup"                     , FL.Ketchup                .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.LIMUS          , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Red);
		
		addItem(tLastID =  3200, "Golden Carrot Juice"          , "", "foodCarrotjuice"                 , FL.make("goldencarrotjuice"   , 250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.SENSUS    , 2), FoodStatFluid.INSTANCE, TD.Creative.HIDDEN);
		
		addItem(tLastID =  3300, "Carrot Juice"                 , "", "foodCarrotjuice"                 , FL.Juice_Carrot           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.SENSUS    , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  3400, "Cactus Water"                 , ""                                    , FL.Juice_Cactus           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  3500, "Maple Sap"                    , ""                                    , FL.Sap_Maple              .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.MOTUS     , 1), FoodStatFluid.INSTANCE, OD.container250maplesap);
		addItem(tLastID =  3501, "Maple Syrup"                  , "", "foodMaplesyrup"                  , FL.Syrup_Maple            .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.LIMUS          , 1), TC.stack(TC.POTENTIA  , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Brown);
		
		CR.shapeless(make(4, 3500), CR.DEF, new Object[] {OD.container1000maplesap, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(3, 3500), CR.DEF, new Object[] {OD.container1000maplesap, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(2, 3500), CR.DEF, new Object[] {OD.container1000maplesap, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(1, 3500), CR.DEF, new Object[] {OD.container1000maplesap, OP.bottle.dat(MT.Empty)});
		
		addItem(tLastID =  3601, "Peanut Butter"                , "", "foodPeanutbutter"                , FL.Nutbutter_Peanut       .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.LIMUS          , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_Brown);
		
		addItem(tLastID =  3700, "Rainbow Sap", "Friendship in a Bottle, definitely not blood of a Tree", FL.Sap_Rainbow            .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.AURAM     , 1), FoodStatFluid.INSTANCE, OD.container250rainbowsap, "foodRainbowsap", TD.Creative.HIDDEN); Sandwiches.INGREDIENTS.put(last(), (byte)40);
		
		CR.shapeless(make(4, 3700), CR.DEF, new Object[] {OD.container1000rainbowsap, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(3, 3700), CR.DEF, new Object[] {OD.container1000rainbowsap, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(2, 3700), CR.DEF, new Object[] {OD.container1000rainbowsap, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(1, 3700), CR.DEF, new Object[] {OD.container1000rainbowsap, OP.bottle.dat(MT.Empty)});
		
		addItem(tLastID =  3800, "Cherry Juice"                 , "", "foodCherryjuice"                 , FL.Juice_Cherry           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  3805, "Cherry Smoothie"              , "", "foodCherrysmoothie"              , FL.Smoothie_Cherry        .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  3900, "Ananas Juice"                 , "", "foodAnanasjuice"                 , FL.Juice_Ananas           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.ARBOR     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  3901, "Ananas Cider"                 , "", "foodAnanascider"                 , FL.Cider_Ananas           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM        , 1), TC.stack(TC.TUTAMEN   , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  3905, "Ananas Smoothie"              , "", "foodAnanassmoothie"              , FL.Smoothie_Ananas        .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.ARBOR     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  4000, "Currant Juice"                , "", "foodCurrantjuice"                , FL.Juice_Currant          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  4005, "Currant Smoothie"             , "", "foodCurrantsmoothie"             , FL.Smoothie_Currant       .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  4100, "Plum Juice"                   , "", "foodPlumjuice"                   , FL.Juice_Plum             .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  4105, "Plum Smoothie"                , "", "foodPlumsmoothie"                , FL.Smoothie_Plum          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  4200, "Peach Juice"                  , "", "foodPeachjuice"                  , FL.Juice_Peach            .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.VICTUS    , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  4205, "Peach Smoothie"               , "", "foodPeachsmoothie"               , FL.Smoothie_Peach         .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.VICTUS    , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  4300, "Elderberry Juice"             , "", "foodElderberryjuice"             , FL.Juice_Elderberry       .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.STRONTIO  , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  4305, "Elderberry Smoothie"          , "", "foodElderberrysmoothie"          , FL.Smoothie_Elderberry    .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.STRONTIO  , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  4400, "Grapefruit Juice"             , "", "foodGrapefruitjuice"             , FL.Juice_Grapefruit       .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  4405, "Grapefruit Smoothie"          , "", "foodGrapefruitsmoothie"          , FL.Smoothie_Grapefruit    .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  4500, "Lime Juice"                   , "", "foodLimejuice"                   , FL.Juice_Lime             .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.PERFODIO  , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  4505, "Lime Smoothie"                , "", "foodLimesmoothie"                , FL.Smoothie_Lime          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.PERFODIO  , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  4600, "Orange Juice"                 , "", "foodOrangejuice"                 , FL.Juice_Orange           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.PERFODIO  , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  4605, "Orange Smoothie"              , "", "foodOrangesmoothie"              , FL.Smoothie_Orange        .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.PERFODIO  , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  4700, "Apricot Juice"                , "", "foodApricotjuice"                , FL.Juice_Apricot          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  4705, "Apricot Smoothie"             , "", "foodApricotsmoothie"             , FL.Smoothie_Apricot       .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  4800, "Pear Juice"                   , "", "foodPearjuice"                   , FL.Juice_Pear             .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  4805, "Pear Smoothie"                , "", "foodPearsmoothie"                , FL.Smoothie_Pear          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  4900, "Pumpkin Juice"                , "", "foodPumpkinjuice"                , FL.Juice_Pumpkin          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  5000, "Persimmon Juice"              , "", "foodPersimmonjuice"              , FL.Juice_Persimmon        .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.PERFODIO  , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  5005, "Persimmon Smoothie"           , "", "foodPersimmonsmoothie"           , FL.Smoothie_Persimmon     .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.PERFODIO  , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  5100, "Starfruit Juice"              , "", "foodStarfruitjuice"              , FL.Juice_Starfruit        .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  5105, "Starfruit Smoothie"           , "", "foodStarfruitsmoothie"           , FL.Smoothie_Starfruit     .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  5200, "Fig Juice"                    , "", "foodFigjuice"                    , FL.Juice_Fig              .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.GRANUM    , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  5205, "Fig Smoothie"                 , "", "foodFigsmoothie"                 , FL.Smoothie_Fig           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.GRANUM    , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  5300, "Pomegranate Juice"            , "", "foodPomegranatejuice"            , FL.Juice_Pomegranate      .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.GRANUM    , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  5305, "Pomegranate Smoothie"         , "", "foodPomegranatesmoothie"         , FL.Smoothie_Pomegranate   .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.GRANUM    , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  5400, "Mango Juice"                  , "", "foodMangojuice"                  , FL.Juice_Mango            .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  5405, "Mango Smoothie"               , "", "foodMangosmoothie"               , FL.Smoothie_Mango         .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  5500, "Papaya Juice"                 , "", "foodPapayajuice"                 , FL.Juice_Papaya           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  5505, "Papaya Smoothie"              , "", "foodPapayasmoothie"              , FL.Smoothie_Papaya        .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  5600, "Coconut Milk"                 , "", "foodCoconutmilk"                 , FL.Juice_Coconut          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		addItem(tLastID =  5604, "Coconut Cream"                , "", "foodCoconutcream"                , FL.Cream_Coconut          .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.LIMUS          , 1), TC.stack(TC.POTENTIA  , 1), FoodStatFluid.INSTANCE); Sandwiches.INGREDIENTS.put(last(), DYE_INDEX_White);
		addItem(tLastID =  5605, "Coconut Smoothie"             , "", "foodCoconutsmoothie"             , FL.Smoothie_Coconut       .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.GELUM          , 1), TC.stack(TC.SANO      , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID =  5700, "Beet Juice"                   , "", "foodBeetjuice"                   , FL.Juice_Beet             .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.AQUA           , 1), TC.stack(TC.HERBA     , 1), FoodStatFluid.INSTANCE);
		
		addItem(tLastID = 30000, "Medicine"                     , ""                                    , FL.Med_Heal               .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.SANO           , 2), FoodStatFluid.INSTANCE);
		addItem(tLastID = 30001, "Laxative"                     , ""                                    , FL.Med_Laxative           .make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.SANO           , 1), TC.stack(TC.FAMES     , 1), FoodStatFluid.INSTANCE);
		
		addItem(32000  , "Ink Bottle"           , "Color: " + DYE_NAMES[DYE_INDEX_Black ], DYE_OREDICTS[DYE_INDEX_Black ], TC.stack(TC.VITREUS, 1), TC.stack(TC.SENSUS, 1), FL.make("squidink", L));
		addItem(32001  , "Bottled Indigo Dye"   , "Color: " + DYE_NAMES[DYE_INDEX_Blue  ], DYE_OREDICTS[DYE_INDEX_Blue  ], TC.stack(TC.VITREUS, 1), TC.stack(TC.SENSUS, 1), FL.make("indigo", L));
		
		for (byte i = 0; i < 16; i++) {
		addItem(32100+i, "Bottled Water Dye"    , "Color: " + DYE_NAMES[i               ], DYE_OREDICTS[i               ], TC.stack(TC.VITREUS, 1), TC.stack(TC.SENSUS, 1), DYE_FLUIDS_WATER[i]);
		addItem(32116+i, "Bottled Chemical Dye" , "Color: " + DYE_NAMES[i               ], DYE_OREDICTS[i               ], TC.stack(TC.VITREUS, 1), TC.stack(TC.SENSUS, 1), DYE_FLUIDS_CHEMICAL[i]);
		addItem(32132+i, "Bottled Flower Dye"   , "Color: " + DYE_NAMES[i               ], DYE_OREDICTS[i               ], TC.stack(TC.VITREUS, 1), TC.stack(TC.SENSUS, 1), DYE_FLUIDS_FLOWER[i]);
		}
		
		IL.Bottle_Lubricant.set(
		addItem(tLastID = 32764, "Lubricant Bottle", "", OP.bottle.dat(MT.Lubricant), FL.LubRoCant.make(250), FL.Lubricant.make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.LIMUS, 2), FoodStatFluid.INSTANCE));
		
		CR.shapeless(make(4,32764), CR.DEF, new Object[] {OD.container1000lubricant, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(3,32764), CR.DEF, new Object[] {OD.container1000lubricant, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(2,32764), CR.DEF, new Object[] {OD.container1000lubricant, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(1,32764), CR.DEF, new Object[] {OD.container1000lubricant, OP.bottle.dat(MT.Empty)});
		
		IL.Bottle_Mercury.set(
		addItem(tLastID = 32765, "Mercury Bottle", "Also called Quicksilver", new OreDictItemData(MT.Hg, U, MT.Glass, U), OP.bottle.dat(MT.Hg).toString() /* <- yes that toString() is needed! */, OD.itemQuicksilver, MT.Hg.liquid( U, T), TC.stack(TC.VITREUS, 1), TC.stack(TC.VENENUM, 2), FoodStatFluid.INSTANCE));
		
		CR.shapeless(make(1,32765), CR.DEF, new Object[] {OD.itemQuicksilver, OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(1,32765), CR.DEF, new Object[] {OP.chunkGt.dat(MT.Hg), OP.chunkGt.dat(MT.Hg), OP.chunkGt.dat(MT.Hg), OP.chunkGt.dat(MT.Hg), OP.bottle.dat(MT.Empty)});
		
		IL.Bottle_Glue.set(
		addItem(tLastID = 32766, "Glue Bottle", "", OP.bottle.dat(MT.Glue), FL.Glue.make(250), TC.stack(TC.VITREUS, 1), TC.stack(TC.LIMUS, 2), FoodStatFluid.INSTANCE));
		
		CR.shapeless(make(4,32766), CR.DEF, new Object[] {OD.container1000glue, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(3,32766), CR.DEF, new Object[] {OD.container1000glue, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(2,32766), CR.DEF, new Object[] {OD.container1000glue, OP.bottle.dat(MT.Empty), OP.bottle.dat(MT.Empty)});
		CR.shapeless(make(1,32766), CR.DEF, new Object[] {OD.container1000glue, OP.bottle.dat(MT.Empty)});
		
		// TODO foodChocolatemilk
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack aStack) {
		return IL.Bottle_Empty.get(1);
	}
	
	@Override
	public int getDefaultStackLimit(ItemStack aStack) {
		return ST.meta_(aStack) >= 32000 ? 64 : OP.bottle.mDefaultStackSize;
	}
	
	@Override
	public ItemStack getRotten(ItemStack aStack) {
		short aMeta = ST.meta_(aStack);
		
		switch(aMeta) {
		case    5: case    6: case    7: case 1102: case 1601: case 1700: return aStack;
		case    0: case    1: case    2: case    3: case    4: return ST.make(this, aStack.stackSize, 5, aStack.getTagCompound());
		case 1100: case 1200: return ST.make(this, aStack.stackSize, 1102, aStack.getTagCompound());
		default: return aMeta < 30000 ? ST.make(this, aStack.stackSize, 1601, aStack.getTagCompound()) : aStack;
		}
	}
	
	@Override public ItemStack getRotten(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {return getRotten(aStack);}
}
