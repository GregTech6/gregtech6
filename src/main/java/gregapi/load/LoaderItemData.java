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

package gregapi.load;

import static gregapi.data.CS.*;

import gregapi.data.ANY;
import gregapi.data.CS.BlocksGT;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.util.OM;
import gregapi.util.ST;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

/**
 * @author Gregorius Techneticies
 * 
 * Loads the ItemData for several Items.
 */
public class LoaderItemData implements Runnable {
	@Override public String toString() {return "Item Data Loader";}
	
	@Override
	public void run() {
		OM.reg(OP.glass, MT.UNUSED.Reinforced       , ST.mkic("reinforcedGlass", 1));
		OM.reg(OP.circuit, MT.Basic                 , ST.mkic("electronicCircuit", 1));
		OM.reg(OP.circuit, MT.Advanced              , ST.mkic("advancedCircuit", 1));
		OM.reg(OP.battery, MT.Basic                 , ST.mkic("reBattery", 1));
		OM.reg(OP.battery, MT.Basic                 , ST.mkic("chargedReBattery", 1, W));
		OM.reg(OP.battery, MT.Advanced              , ST.mkic("advBattery", 1, W));
		OM.reg("calclavia:BATTERY"                  , ST.mkic("reBattery", 1));
		OM.reg("calclavia:BATTERY"                  , ST.mkic("chargedReBattery", 1, W));
		OM.reg(OD.itemCertusQuartz                  , ST.make(MD.AE, "item.ItemMultiMaterial", 1, 1));
		OM.reg(OD.itemCertusQuartz                  , ST.make(MD.AE, "item.ItemMultiMaterial", 1, 10));
		OM.reg(OD.itemNetherQuartz                  , ST.make(MD.AE, "item.ItemMultiMaterial", 1, 11));
		OM.reg(OD.craftingWireCopper                , ST.mkic("insulatedCopperCableItem", 1));
		OM.reg(OD.craftingWireGold                  , ST.mkic("insulatedGoldCableItem", 1));
		OM.reg(OD.craftingWireIron                  , ST.mkic("insulatedIronCableItem", 1));
		OM.reg(OD.craftingWireTin                   , ST.mkic("insulatedTinCableItem", 1));
		
		
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "vines", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower1", 1, 1));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower1", 1, 2));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower1", 1, 3));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower1", 1, 4));
		OM.reg("listAllmushroom"                    , ST.make(MD.EBXL, "flower1", 1, 6));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower1", 1, 7));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1, 1));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1, 2));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1, 3));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1, 4));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1, 5));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1, 6));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1, 7));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1, 8));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1, 9));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1,11));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1,12));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1,13));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1,14));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower2", 1,15));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1, 1));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1, 2));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1, 3));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1, 4));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1, 5));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1, 6));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1, 7));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1, 8));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1, 9));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1,10));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1,11));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1,12));
		OM.reg(OD.flower                            , ST.make(MD.EBXL, "flower3", 1,13));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1, 1));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1, 2));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1, 3));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1, 4));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1, 5));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1, 6));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1, 7));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1, 8));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1, 9));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1,10));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1,11));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1,12));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1,13));
		OM.reg(OD.flower                            , ST.make(MD.ERE, "flowerSeeds", 1,14));
		OM.reg("foodChocolatebar"                   , ST.make(MD.EBXL, "extrabiomes.food", 1, 0));
		OM.reg("foodChocolatestrawberry"            , ST.make(MD.EBXL, "extrabiomes.crop", 1, 1));
		OM.reg("cropStrawberry"                     , ST.make(MD.EBXL, "extrabiomes.crop", 1, 0));
		OM.reg("seedStrawberry"                     , ST.make(MD.EBXL, "extrabiomes.seed", 1, 0));
		OM.reg("cropAcorn"                          , ST.make(MD.DYNAMIC_TREES, "oakseed"    , 1, 0));
		OM.reg("cropCatkin"                         , ST.make(MD.DYNAMIC_TREES, "birchseed"  , 1, 0));
		OM.reg("cropPinecone"                       , ST.make(MD.DYNAMIC_TREES, "spruceseed" , 1, 0));
//      OM.reg("cropPinecone"                       , ST.make(MD.DYNAMIC_TREES, "jungleseed" , 1, 0));
//      OM.reg("cropPinecone"                       , ST.make(MD.DYNAMIC_TREES, "acaciaseed" , 1, 0));
		OM.reg("cropAcorn"                          , ST.make(MD.DYNAMIC_TREES, "darkoakseed", 1, 0));
		OM.reg("cropKelp"                           , ST.make(MD.BoP, "coral1", 1, 11));
		OM.reg("cropAlgae"                          , ST.make(MD.BoP, "coral2", 1, 8));
		OM.reg("cropFlax"                           , ST.make(MD.BoP, "foliage", 1, 3));
		OM.reg("cropIvy"                            , ST.make(MD.BoP, "foliage", 1, 7));
		OM.reg("cropPinecone"                       , ST.make(MD.BoP, "misc", 1, 13));
		OM.reg("cropGrapeRed"                       , ST.make(MD.HaC, "grapeItem", 1));
		OM.reg("cropBambooshoot"                    , ST.make(MD.BoP, "saplings", 1, 2));
		OM.reg("listAllmushroom"                    , ST.make(MD.BoP, "mushrooms", 1, 0));
		OM.reg("listAllmushroom"                    , ST.make(MD.BoP, "mushrooms", 1, 1));
		OM.reg("listAllmushroom"                    , ST.make(MD.BoP, "mushrooms", 1, 2));
		OM.reg("listAllmushroom"                    , ST.make(MD.BoP, "mushrooms", 1, 3));
		OM.reg("listAllmushroom"                    , ST.make(MD.BoP, "mushrooms", 1, 4));
		OM.reg("listAllmushroom"                    , ST.make(MD.BoP, "mushrooms", 1, 5));
		for (int i = 0; i < 16; i++) {
		OM.reg("listAllmushroom"                    , ST.make(MD.BOTA, "mushroom", 1, i));
		OM.reg(DYE_OREDICTS_MIXABLE[i]              , ST.make(MD.BOTA, "dye", 1, 15-i));
		}
		OM.reg(DYE_OREDICTS_MIXABLE[DYE_INDEX_Black], ST.make(MD.TCFM, "FMResource", 1, 1));
		OM.reg(DYE_OREDICTS_MIXABLE[DYE_INDEX_Black], ST.make(MD.NeLi, "dye", 1, 0));
		OM.reg(DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue ], ST.make(MD.NeLi, "dye", 1, 1));
		OM.reg(DYE_OREDICTS_MIXABLE[DYE_INDEX_White], ST.make(MD.NeLi, "dye", 1, 2));
		OM.reg(DYE_OREDICTS_MIXABLE[DYE_INDEX_White], ST.make(MD.EtFu, "dye", 1, 0));
		OM.reg(DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue ], ST.make(MD.EtFu, "dye", 1, 1));
		OM.reg(DYE_OREDICTS_MIXABLE[DYE_INDEX_Brown], ST.make(MD.EtFu, "dye", 1, 2));
		OM.reg(DYE_OREDICTS_MIXABLE[DYE_INDEX_Black], ST.make(MD.EtFu, "dye", 1, 3));
		OM.reg("foodCookie"                         , ST.make(Items.cookie, 1, 0));
		OM.reg("foodCookie"                         , ST.make(MD.BOTA, "manaCookie", 1, 0));
		OM.reg("cropAppleRed"                       , ST.make(Items.apple, 1, 0));
		OM.reg("cropMelon"                          , ST.make(Items.melon, 1, 0));
		OM.reg("cropPumpkin"                        , ST.make(Blocks.pumpkin, 1, 0));
		OM.reg("cropHops"                           , ST.mkic("hops", 1));
		OM.reg("cropCoffee"                         , ST.mkic("coffeeBeans", 1));
		OM.reg("cropLemon"                          , ST.make(MD.FR, "fruits", 1, 3));
		OM.reg("cropChilipepper"                    , ST.make(MD.MaCr, "magicalcrops_CropProduce", 1, 2));
		OM.reg("cropTomato"                         , ST.make(MD.MaCr, "magicalcrops_CropProduce", 1, 8));
		OM.reg("cropGrape"                          , ST.make(MD.MaCr, "magicalcrops_CropProduce", 1, 4));
		OM.reg("cropTea"                            , ST.make(MD.GaSu, "teaLeaves", 1, 0));
		OM.reg("cropHeartberry"                     , ST.make(MD.ERE, "heartBerries", 1, 0));
		OM.reg("cropBambooshoot"                    , ST.make(MD.ERE, "materials", 1, 11));
		OM.reg("cropTurnip"                         , ST.make(MD.ERE, "turnips", 1, 0));
		OM.reg("cropDarkFruit"                      , ST.make(MD.ERE, "food", 1, 11));
		OM.reg("cropSwampberry"                     , ST.make(MD.ERE, "food", 1, 14));
		OM.reg("cropCabbage"                        , ST.make(MD.ERE, "food", 1, 15));
		OM.reg("cropPricklyPair"                    , ST.make(MD.ERE, "food", 1, 17));
		OM.reg("cropGarlic"                         , ST.make(MD.WTCH, "garlic", 1, 0));
		OM.reg("cropRowanberry"                     , ST.make(MD.WTCH, "ingredient", 1, 63));
		OM.reg("cropPapaya"                         , ST.make(MD.BINNIE_TREE, "food", 1, 40));
		OM.reg("cropCurrants"                       , ST.make(MD.BINNIE_TREE, "food", 1, 41));
		OM.reg("cropCurrants"                       , ST.make(MD.BINNIE_TREE, "food", 1, 42));
		OM.reg("cropBlackberry"                     , ST.make(MD.BINNIE_TREE, "food", 1, 43));
		OM.reg("cropRaspberry"                      , ST.make(MD.BINNIE_TREE, "food", 1, 44));
		OM.reg("cropBlueberry"                      , ST.make(MD.BINNIE_TREE, "food", 1, 45));
		OM.reg("cropCranberry"                      , ST.make(MD.BINNIE_TREE, "food", 1, 46));
		OM.reg("cropJuniper"                        , ST.make(MD.BINNIE_TREE, "food", 1, 47));
		OM.reg("cropGooseberry"                     , ST.make(MD.BINNIE_TREE, "food", 1, 48));
		OM.reg("cropRaspberry"                      , ST.make(MD.BINNIE_TREE, "food", 1, 49));
		OM.reg("cropCoconut"                        , ST.make(MD.BINNIE_TREE, "food", 1, 50));
		OM.reg("cropCashew"                         , ST.make(MD.BINNIE_TREE, "food", 1, 51));
		OM.reg("cropAvocado"                        , ST.make(MD.BINNIE_TREE, "food", 1, 52));
		OM.reg("cropNutmeg"                         , ST.make(MD.BINNIE_TREE, "food", 1, 53));
		OM.reg("cropAllspice"                       , ST.make(MD.BINNIE_TREE, "food", 1, 54));
		OM.reg("cropChilipepper"                    , ST.make(MD.BINNIE_TREE, "food", 1, 55));
		OM.reg("cropStarAnise"                      , ST.make(MD.BINNIE_TREE, "food", 1, 56));
		OM.reg("cropMango"                          , ST.make(MD.BINNIE_TREE, "food", 1, 57));
		OM.reg("cropStarfruit"                      , ST.make(MD.BINNIE_TREE, "food", 1, 58));
		OM.reg("cropCandlenut"                      , ST.make(MD.BINNIE_TREE, "food", 1, 59));
		OM.reg("foodApplejuice"                     , ST.make(MD.BINNIE, "containerGlass", 1,256));
		OM.reg("foodApricotjuice"                   , ST.make(MD.BINNIE, "containerGlass", 1,257));
		OM.reg("foodBananajuice"                    , ST.make(MD.BINNIE, "containerGlass", 1,258));
		OM.reg("foodCherryjuice"                    , ST.make(MD.BINNIE, "containerGlass", 1,259));
		OM.reg("foodElderberryjuice"                , ST.make(MD.BINNIE, "containerGlass", 1,260));
		OM.reg("foodLemonjuice"                     , ST.make(MD.BINNIE, "containerGlass", 1,261));
		OM.reg("foodLimejuice"                      , ST.make(MD.BINNIE, "containerGlass", 1,262));
		OM.reg("foodOrangejuice"                    , ST.make(MD.BINNIE, "containerGlass", 1,263));
		OM.reg("foodPeachjuice"                     , ST.make(MD.BINNIE, "containerGlass", 1,264));
		OM.reg("foodPlumjuice"                      , ST.make(MD.BINNIE, "containerGlass", 1,265));
		OM.reg("foodCarrotjuice"                    , ST.make(MD.BINNIE, "containerGlass", 1,266));
		OM.reg("foodTomatojuice"                    , ST.make(MD.BINNIE, "containerGlass", 1,267));
		OM.reg("foodCranberryjuice"                 , ST.make(MD.BINNIE, "containerGlass", 1,268));
		OM.reg("foodGrapefruitjuice"                , ST.make(MD.BINNIE, "containerGlass", 1,269));
		OM.reg("foodOliveoil"                       , ST.make(MD.BINNIE, "containerGlass", 1,270));
		OM.reg("foodAnanasjuice"                    , ST.make(MD.BINNIE, "containerGlass", 1,271));
		OM.reg("foodPearjuice"                      , ST.make(MD.BINNIE, "containerGlass", 1,272));
		OM.reg("foodGrapejuice"                     , ST.make(MD.BINNIE, "containerGlass", 1,273));
		OM.reg("foodGrapejuice"                     , ST.make(MD.BINNIE, "containerGlass", 1,274));
		OM.reg("foodApplecider"                     , ST.make(MD.BINNIE, "containerGlass", 1,384));
		OM.reg("foodApricotwine"                    , ST.make(MD.BINNIE, "containerGlass", 1,385));
		OM.reg("foodBananawine"                     , ST.make(MD.BINNIE, "containerGlass", 1,386));
		OM.reg("foodCherrywine"                     , ST.make(MD.BINNIE, "containerGlass", 1,387));
		OM.reg("foodElderberrywine"                 , ST.make(MD.BINNIE, "containerGlass", 1,388));
		OM.reg("foodPeachcider"                     , ST.make(MD.BINNIE, "containerGlass", 1,389));
		OM.reg("foodPearcider"                      , ST.make(MD.BINNIE, "containerGlass", 1,390));
		OM.reg("foodPlumwine"                       , ST.make(MD.BINNIE, "containerGlass", 1,391));
		OM.reg("foodCarrotwine"                     , ST.make(MD.BINNIE, "containerGlass", 1,392));
		OM.reg("foodGrapewine"                      , ST.make(MD.BINNIE, "containerGlass", 1,393));
		OM.reg("foodGrapewine"                      , ST.make(MD.BINNIE, "containerGlass", 1,394));
		OM.reg("foodWine"                           , ST.make(MD.BINNIE, "containerGlass", 1,395));
		OM.reg("foodAgavewine"                      , ST.make(MD.BINNIE, "containerGlass", 1,396));
		OM.reg("foodCitruswine"                     , ST.make(MD.BINNIE, "containerGlass", 1,398));
		OM.reg("foodCranberrywine"                  , ST.make(MD.BINNIE, "containerGlass", 1,399));
		OM.reg("foodPineapplecider"                 , ST.make(MD.BINNIE, "containerGlass", 1,400));
		OM.reg("foodTomatowine"                     , ST.make(MD.BINNIE, "containerGlass", 1,401));
		OM.reg("foodWine"                           , ST.make(MD.BINNIE, "containerGlass", 1,402));
		OM.reg("foodAle"                            , ST.make(MD.BINNIE, "containerGlass", 1,403));
		OM.reg("foodBeer"                           , ST.make(MD.BINNIE, "containerGlass", 1,404));
		OM.reg("foodBeer"                           , ST.make(MD.BINNIE, "containerGlass", 1,405));
		OM.reg("foodBeer"                           , ST.make(MD.BINNIE, "containerGlass", 1,406));
		OM.reg("foodBeer"                           , ST.make(MD.BINNIE, "containerGlass", 1,407));
		OM.reg("foodBeer"                           , ST.make(MD.BINNIE, "containerGlass", 1,408));
		OM.reg("foodVodka"                          , ST.make(MD.BINNIE, "containerGlass", 1,513));
		OM.reg("foodRum"                            , ST.make(MD.BINNIE, "containerGlass", 1,514));
		OM.reg("foodRum"                            , ST.make(MD.BINNIE, "containerGlass", 1,515));
		OM.reg("foodWhiskey"                        , ST.make(MD.BINNIE, "containerGlass", 1,516));
		OM.reg("foodBourbon"                        , ST.make(MD.BINNIE, "containerGlass", 1,517));
		OM.reg("foodWhiskey"                        , ST.make(MD.BINNIE, "containerGlass", 1,518));
		OM.reg("foodWhiskey"                        , ST.make(MD.BINNIE, "containerGlass", 1,519));
		OM.reg("foodWine"                           , ST.make(MD.BINNIE, "containerGlass", 1,520));
		OM.reg("foodTequila"                        , ST.make(MD.BINNIE, "containerGlass", 1,521));
		OM.reg("foodBrandy"                         , ST.make(MD.BINNIE, "containerGlass", 1,522));
		OM.reg("foodBrandy"                         , ST.make(MD.BINNIE, "containerGlass", 1,523));
		OM.reg("foodBrandy"                         , ST.make(MD.BINNIE, "containerGlass", 1,524));
		OM.reg("foodBrandy"                         , ST.make(MD.BINNIE, "containerGlass", 1,525));
		OM.reg("foodBrandy"                         , ST.make(MD.BINNIE, "containerGlass", 1,526));
		OM.reg("foodBrandy"                         , ST.make(MD.BINNIE, "containerGlass", 1,527));
		OM.reg("foodBrandy"                         , ST.make(MD.BINNIE, "containerGlass", 1,528));
		OM.reg("foodBrandy"                         , ST.make(MD.BINNIE, "containerGlass", 1,529));
		OM.reg("foodBrandy"                         , ST.make(MD.BINNIE, "containerGlass", 1,530));
		OM.reg("foodCachaca"                        , ST.make(MD.BINNIE, "containerGlass", 1,531));
		OM.reg("foodGin"                            , ST.make(MD.BINNIE, "containerGlass", 1,532));
		OM.reg("foodLiquor"                         , ST.make(MD.BINNIE, "containerGlass", 1,533));
		OM.reg("foodLiquor"                         , ST.make(MD.BINNIE, "containerGlass", 1,534));
		OM.reg("foodLiquor"                         , ST.make(MD.BINNIE, "containerGlass", 1,535));
		OM.reg("foodLiquor"                         , ST.make(MD.BINNIE, "containerGlass", 1,536));
		OM.reg("foodLiquor"                         , ST.make(MD.BINNIE, "containerGlass", 1,537));
		OM.reg("foodLiquor"                         , ST.make(MD.BINNIE, "containerGlass", 1,538));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,640));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,641));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,642));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,643));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,644));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,645));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,646));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,647));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,648));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,649));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,650));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,651));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,652));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,653));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,654));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,655));
		OM.reg("foodLiqueur"                        , ST.make(MD.BINNIE, "containerGlass", 1,656));
		OM.reg("foodTitanraw"                       , ST.make(MD.ERE, "food", 1, 12));
		OM.reg("foodTitancooked"                    , ST.make(MD.ERE, "food", 1, 13));
		OM.reg("cropBambooshoot"                    , ST.make(MD.Bamboo, "blockbambooshoot", 1, 0));
		OM.reg(OP.treeSapling                       , ST.make(MD.Bamboo, "sakuraSapling", 1, W));
		OM.reg(OP.treeLeaves                        , ST.make(MD.Bamboo, "sakuraLeaves", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.Bamboo, "sakuraLog", 1, W));
		OM.reg(OD.slabWood                          , ST.make(MD.Bamboo, "halfTwoDirDeco", 1, 2));
		OM.reg("seedRice"                           , ST.make(MD.Bamboo, "seedrice", 1, 0));
		OM.reg("dustRice"                           , ST.make(MD.Bamboo, "rawrice", 1, 0));
		OM.reg("cropSoybean"                        , ST.make(MD.Bamboo, "itembean", 1, 0));
		OM.reg("cropStrawberry"                     , ST.make(MD.PMP, "berriesStraw", 1, 0));
		OM.reg("cropBlackberry"                     , ST.make(MD.PMP, "berriesBlack", 1, 0));
		OM.reg("cropBlueberry"                      , ST.make(MD.PMP, "berriesBlue", 1, 0));
		OM.reg("cropElderberry"                     , ST.make(MD.PMP, "berriesElder", 1, 0));
		OM.reg("cropGooseberry"                     , ST.make(MD.PMP, "berriesGoose", 1, 0));
		OM.reg("cropBerry"                          , ST.make(MD.PMP, "berriesHarlequinMistletoe", 1, 0));
		OM.reg("cropBerry"                          , ST.make(MD.PMP, "berriesBeauty", 1, 0));
		OM.reg("cropBerry"                          , ST.make(MD.PMP, "berriesHuckle", 1, 0));
		OM.reg("cropBerry"                          , ST.make(MD.PMP, "berriesOrange", 1, 0));
		OM.reg("cropBerry"                          , ST.make(MD.PMP, "berriesSnow", 1, 0));
		OM.reg("seedBeet"                           , ST.make(MD.PMP, "seedBeet", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedBellPepperOrange", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedBellPepperRed", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedBellPepperYellow", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedBroccoli", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedCassava", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedCelery", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedCorn", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedCucumber", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedEggplant", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedGreenBean", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedLeek", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedLettuce", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedOnion", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedSorrel", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedSpinach", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "seedTomato", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.PMP, "foodQuinoaSeeds", 1, 0));
		OM.reg("cropBeet"                           , ST.make(MD.PMP, "foodBeet", 1, 0));
		OM.reg("cropCorn"                           , ST.make(MD.PMP, "foodCorn", 1, 0));
		OM.reg("cropCucumber"                       , ST.make(MD.PMP, "foodCucumber", 1, 0));
		OM.reg("cropEggplant"                       , ST.make(MD.PMP, "foodEggplant", 1, 0));
		OM.reg("cropOnion"                          , ST.make(MD.PMP, "foodOnion", 1, 0));
		OM.reg("cropRice"                           , ST.make(MD.PMP, "foodRice", 1, 0));
		OM.reg("cropWildRice"                       , ST.make(MD.PMP, "foodWildRice", 1, 0));
		OM.reg("cropTomato"                         , ST.make(MD.PMP, "foodTomato", 1, 0));
		OM.reg("cropPeanut"                         , ST.make(MD.PMP, "foodPeanuts", 1, 0));
		OM.reg("foodPeanutbutter"                   , ST.make(MD.PMP, "foodPeanutButter", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerAchillea", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerAlpineThistle", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerAzalea", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerBegonia", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerBell", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerBirdofParadise", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerBlueStar", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerBlueStarItem", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerBroomSnakeweed", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerBurningLove", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerCandelabraAloe", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerCarnation", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerCattail", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerCelosia", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerColumbine", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerDahlia", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerDaisy", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerDandelion", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerDelphinium", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerDottedBlazingstar", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerElephantEars", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerFoamFlower", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerFuchsia", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerGeranium", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerGladiolus", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerHawkweed", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerHydrangea", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerJacobsLadder", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerLily", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerLionsTail", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerLupine", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerMarigold", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerMediterraneanSeaHolly", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerMezereon", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerNemesia", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerNewGuineaImpatiens", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerOcotillo", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerParrotsBeak", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerPeruvianLily", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerPurpleConeflower", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerQuinoa", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerRose", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerRoseCampion", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerStreamsideBluebells", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerTorchGinger", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerTorchLily", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerTulip", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerViolet", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerWildCarrot", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerWildDaffodil", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerWoodlandPinkroot", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerYellowToadflax", 1, 0));
		OM.reg(OD.flower                            , ST.make(MD.PMP, "flowerYellowToadflaxItem", 1, 0));
		OM.reg("foodCustard"                        , ST.make(MD.MaCu, "food", 1, 3));
		OM.reg("seedMisc"                           , ST.make(MD.WTCH, "seedsmandrake", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.WTCH, "seedswormwood", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.WTCH, "seedssnowbell", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.WTCH, "seedsartichoke", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.WTCH, "seedswolfsbane", 1, 0));
		OM.reg("seedMisc"                           , ST.make(MD.WTCH, "seedsbelladonna", 1, 0));
		OM.reg("seedPumpkin"                        , ST.make(MD.NeLi, "GhastlyGourdSeeds", 1, 0));
		OM.reg("seedAbyssalOats"                    , ST.make(MD.NeLi, "AbyssalOatSeeds", 1, 0));
		OM.reg("seedCorn"                           , ST.make(MD.NeLi, "DevilishMaizeSeeds", 1, 0));
		OM.reg("seedHellderberry"                   , ST.make(MD.NeLi, "HellderBerrySeeds", 1, 0));
		OM.reg("seedCanola"                         , ST.make(MD.RoC, "rotarycraft_item_canola", 1, 0));
		OM.reg("seedThistle"                        , ST.make(MD.GrC_Milk, "grcmilk.SeedThistle", 1, 0));
		OM.reg("seedCabbage"                        , ST.make(MD.ERE, "cabbageSeeds", 1, 0));
		OM.reg("seedDarkFruit"                      , ST.make(MD.ERE, "materials", 1, 33));
		OM.reg("seedWheat"                          , ST.make(Items.wheat_seeds, 1, 0));
		OM.reg("seedMelon"                          , ST.make(Items.melon_seeds, 1, 0));
		OM.reg("seedPumpkin"                        , ST.make(Items.pumpkin_seeds, 1, 0));
		OM.reg("seedCamellia"                       , ST.make(MD.GaSu, "camelliaSeeds", 1, 0));
		OM.reg("seedWitherShrub"                    , ST.make(MD.GaNe, "witherShrubSeeds", 1, 0));
		OM.reg("seedGhost"                          , ST.make(MD.GaNe, "ghostSeeds", 1, 0));
		OM.reg("seedQuartzBerry"                    , ST.make(MD.GaNe, "quarzBerrySeeds", 1, 0));
		OM.reg("seedHellBush"                       , ST.make(MD.GaNe, "hellBushSeeds", 1, 0));
		OM.reg("listAllegg"                         , ST.make(Items.egg, 1, 0));
		OM.reg("listAllbeefraw"                     , ST.make(Items.beef, 1, 0));
		OM.reg("listAllbeefcooked"                  , ST.make(Items.cooked_beef, 1, 0));
		OM.reg("listAllporkraw"                     , ST.make(Items.porkchop, 1, 0));
		OM.reg("listAllporkcooked"                  , ST.make(Items.cooked_porkchop, 1, 0));
		OM.reg("listAllchickenraw"                  , ST.make(Items.chicken, 1, 0));
		OM.reg("listAllchickencooked"               , ST.make(Items.cooked_chicken, 1, 0));
		for (int i = 0, j = (MD.MaCu.mLoaded?64:3); i <= j; i++)
		OM.reg("listAllfishraw"                     , ST.make(Items.fish, 1, i));
		OM.reg("listAllfishcooked"                  , ST.make(Items.cooked_fished, 1, 0));
		OM.reg("listAllfishcooked"                  , ST.make(Items.cooked_fished, 1, 1));
		OM.reg("listAllmushroom"                    , ST.make(Blocks.brown_mushroom, 1, W));
		OM.reg("listAllmushroom"                    , ST.make(Blocks.red_mushroom, 1, W));
		OM.reg(OD.pressurePlateWood                 , ST.make(Blocks.wooden_pressure_plate, 1, W));
		OM.reg(OD.pressurePlateStone                , ST.make(Blocks.stone_pressure_plate, 1, W));
		OM.reg(OD.pressurePlateIron                 , ST.make(Blocks.heavy_weighted_pressure_plate, 1, W));
		OM.reg(OD.pressurePlateGold                 , ST.make(Blocks.light_weighted_pressure_plate, 1, W));
		OM.reg(OD.paperEmpty                        , ST.make(Items.paper, 1, W));
		OM.reg(OD.paperMap                          , ST.make(Items.map, 1, W));
		OM.reg(OD.paperMap                          , ST.make(Items.filled_map, 1, W));
		OM.reg(OD.bookEmpty                         , ST.make(Items.book, 1, W));
		OM.reg(OD.bookWritable                      , ST.make(Items.writable_book, 1, W));
		OM.reg(OD.bookWritten                       , ST.make(Items.written_book, 1, W));
		OM.reg(OD.bookEnchanted                     , ST.make(Items.enchanted_book, 1, W));
		OM.reg(OD.craftingFirestarter               , ST.make(Items.fire_charge, 1, W));
		OM.reg(OD.craftingFirestarter               , ST.make(Items.flint_and_steel, 1, W));
		OM.reg("bucketWater"                        , ST.make(Items.water_bucket, 1, W));
		OM.reg("bucketLava"                         , ST.make(Items.lava_bucket, 1, W));
		OM.reg("bucketMilk"                         , ST.make(Items.milk_bucket, 1, W));
		OM.reg("bottleMilk"                         , ST.make(MD.MFR, "milkbottle", 1, 0));
		OM.reg(OD.glowstone                         , ST.make(Blocks.glowstone, 1, W));
		OM.reg(OD.dirt                              , ST.make(Blocks.dirt, 1, W));
		OM.reg(OD.sand                              , ST.make(Blocks.sand, 1, W));
		OM.reg(OD.gravel                            , ST.make(Blocks.gravel, 1, W));
		OM.reg(OD.soulsand                          , ST.make(Blocks.soul_sand, 1, W));
		OM.reg(OD.itemGrassTall                     , ST.make(Blocks.tallgrass, 1, 1));
		OM.reg(OD.itemGrassTall                     , ST.make(Blocks.tallgrass, 1, 2));
		OM.reg(OD.itemGhastTear                     , ST.make(Items.ghast_tear, 1, W));
		OM.reg(OD.itemCompass                       , ST.make(Items.compass, 1, W));
		OM.reg(OD.itemClay                          , ST.make(Items.clay_ball, 1, W));
		OM.reg(OD.itemFlint                         , ST.make(Items.flint, 1, W));
		OM.reg(OD.itemPearl                         , ST.make(MD.MaCu, "pearls", 1, W));
		OM.reg(OD.itemPearl                         , ST.make(MD.TROPIC, "pearl", 1, W));
		OM.reg(OD.itemFeather                       , ST.make(MD.TF, "item.tfFeather", 1, W));
		OM.reg(OD.itemFeather                       , ST.make(Items.feather, 1, W));
		OM.reg(OD.itemLeather                       , ST.make(Items.leather, 1, W));
		OM.reg(OD.itemLeatherTreated                , ST.make(MD.TG, "TechgunsAmmo", 1, 109));
		OM.reg(OD.itemLeatherTreated                , ST.make(MD.WTCH, "ingredient", 1, 131));
		OM.reg(OD.itemLeatherTreated                , ST.make(MD.WTCH, "ingredient", 1, 72));
		OM.reg(OD.itemLeatherHardened               , ST.make(MD.HaC, "hardenedleatherItem", 1));
		OM.reg(OD.itemLeather                       , ST.make(MD.ERE, "materials", 1, 49));
		OM.reg(OD.itemLeather                       , ST.make(MD.MoCr, "hide", 1, 0));
		OM.reg(OD.itemFur                           , ST.make(MD.MoCr, "fur", 1, 0));
		OM.reg(OD.itemFur                           , ST.make(MD.TF, "item.arcticFur", 1, 0));
		OM.reg(OD.itemFur                           , ST.make(MD.TF, "item.alphaFur", 1, 0));
		OM.reg(OD.itemString                        , ST.make(Items.string, 1, W));
		OM.reg(OD.itemString                        , ST.make(MD.MaCu, "crafting", 1, 0));
		OM.reg(OD.itemMoss                          , ST.make(MD.CHSL, "ballomoss", 1, 0));
		OM.reg(OD.itemMoss                          , ST.make(MD.ERE, "materials", 1, 34));
		OM.reg(OD.itemMoss                          , ST.make(MD.BTL, "plantDrop", 1, 29));
		OM.reg(OD.itemMoss                          , ST.make(MD.BTL, "plantDrop", 1, 30));
		OM.reg(OD.itemMoss                          , ST.make(MD.BoP, "moss", 1, 0));
		OM.reg(OD.blockGlass                        , ST.make(Blocks.stained_glass, 1, W));
		OM.reg(OD.blockGlassColorless               , ST.make(Blocks.glass, 1, W));
		OM.reg(OD.blockGlassColorless               , ST.make(MD.CHSL, "glass", 1, W));
		OM.reg(OD.paneGlass                         , ST.make(Blocks.stained_glass_pane, 1, W));
		OM.reg(OD.paneGlassColorless                , ST.make(Blocks.glass_pane, 1, W));
		OM.reg(OD.paneGlassColorless                , ST.make(MD.CHSL, "glass_pane", 1, W));
		OM.reg(OD.paneGlass                         , ST.make(MD.CHSL, "stained_glass_pane_brown", 1, W));
		OM.reg(OD.paneGlass                         , ST.make(MD.CHSL, "stained_glass_pane_red", 1, W));
		OM.reg(OD.paneGlass                         , ST.make(MD.CHSL, "stained_glass_pane_purple", 1, W));
		OM.reg(OD.paneGlass                         , ST.make(MD.CHSL, "stained_glass_pane_magenta", 1, W));
		OM.reg(OD.paneGlass                         , ST.make(MD.CHSL, "stained_glass_pane_yellow", 1, W));
		OM.reg(OD.paneGlass                         , ST.make(MD.CHSL, "stained_glass_pane_white", 1, W));
		OM.reg(OD.paneGlass                         , ST.make(MD.CHSL, "stained_glass_pane_pink", 1, W));
		OM.reg(OD.paneGlass                         , ST.make(MD.CHSL, "stained_glass_pane_lightgray", 1, W));
		OM.reg(OD.blockGlass                        , ST.make(MD.CHSL, "stained_glass_brown", 1, W));
		OM.reg(OD.blockGlass                        , ST.make(MD.CHSL, "stained_glass_white", 1, W));
		OM.reg(OD.blockGlass                        , ST.make(MD.CHSL, "stained_glass_lightgray", 1, W));
		OM.reg(OD.blockGlass                        , ST.make(MD.CHSL, "stained_glass_yellow", 1, W));
		OM.reg(OD.blockGlass                        , ST.make(MD.CHSL, "glass2", 1, W));
		OM.reg(OD.blockGlass                        , ST.make(MD.CHSL, "stained_glass_forestry", 1, W));
		OM.reg(OD.blockTorch                        , ST.make(MD.CHSL, "torch1", 1, W));
		OM.reg(OD.blockTorch                        , ST.make(MD.CHSL, "torch2", 1, W));
		OM.reg(OD.blockTorch                        , ST.make(MD.CHSL, "torch3", 1, W));
		OM.reg(OD.blockTorch                        , ST.make(MD.CHSL, "torch4", 1, W));
		OM.reg(OD.blockTorch                        , ST.make(MD.CHSL, "torch5", 1, W));
		OM.reg(OD.blockTorch                        , ST.make(MD.CHSL, "torch6", 1, W));
		OM.reg(OD.blockTorch                        , ST.make(MD.CHSL, "torch7", 1, W));
		OM.reg(OD.blockTorch                        , ST.make(MD.CHSL, "torch8", 1, W));
		OM.reg(OD.blockTorch                        , ST.make(MD.CHSL, "torch9", 1, W));
		OM.reg(OD.blockTorch                        , ST.make(MD.CHSL, "torch10", 1, W));
		OM.reg("gt:autocrafterinfinite"             , ST.make(MD.PE, "item.pe_philosophers_stone", 1, W));
		OM.reg("gt:autocrafterinfinite"             , ST.make(MD.PE, "item.pe_evertide_amulet", 1, W));
		OM.reg(OD.container1000water                , ST.make(MD.PE, "item.pe_evertide_amulet", 1, W));
		OM.reg(OD.container250water                 , ST.make(MD.PE, "item.pe_evertide_amulet", 1, W));
		OM.reg("gt:autocrafterinfinite"             , ST.make(MD.GaEn, "infiniteBucket", 1, W));
		OM.reg(OD.container1000water                , ST.make(MD.GaEn, "infiniteBucket", 1, W));
		OM.reg(OD.container250water                 , ST.make(MD.GaEn, "infiniteBucket", 1, W));
		OM.reg(OD.itemResin                         , ST.make(MD.ERE, "materials", 1, 41));
		OM.reg(OD.logWood                           , ST.make(MD.TC, "blockMagicalLog", 1, 4));
		OM.reg(OD.logWood                           , ST.make(MD.TC, "blockMagicalLog", 1, 5));
		OM.reg(OD.logWood                           , ST.make(MD.TC, "blockMagicalLog", 1, 8));
		OM.reg(OD.logWood                           , ST.make(MD.TC, "blockMagicalLog", 1, 9));
		OM.reg(OD.logWood                           , ST.make(MD.EBXL, "log1", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.EBXL, "log2", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.EBXL, "log_elbow_baldcypress", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.EBXL, "log_elbow_rainbow_eucalyptus", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.EBXL, "cornerlog_oak", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.EBXL, "cornerlog_fir", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.EBXL, "cornerlog_redwood", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.EBXL, "cornerlog_baldcypress", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.EBXL, "cornerlog_rainboweucalyptus", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.MoCr, "MoCLog", 1, 0));
		OM.reg(OD.logWood                           , ST.make(MD.MoCr, "MoCLog", 1, 1));
		OM.reg(OD.plankWood                         , ST.make(MD.MoCr, "MoCWoodPlank", 1, 0));
		OM.reg(OD.plankWood                         , ST.make(MD.MoCr, "MoCWoodPlank", 1, 1));
		OM.reg(OD.stairWood                         , ST.make(MD.BTL, "weedwoodPlankStairs", 1, 0));
		OM.reg(OD.slabWood                          , ST.make(MD.BTL, "Weedwood Planks Slab", 1, 0));
		OM.reg("seedAspectrus"                      , ST.make(MD.BTL, "aspectrusSeeds", 1, 0));
		OM.reg("seedPurplePear"                     , ST.make(MD.BTL, "middleFruitSeeds", 1, 0));
		OM.reg("foodMuttonraw"                      , ST.make(MD.GaSu, "mutton_raw", 1, 0));
		OM.reg("foodMuttonraw"                      , ST.make(MD.EtFu, "mutton_raw", 1, 0));
		OM.reg("foodMuttonraw"                      , ST.make(MD.WTCH, "ingredient", 1, 158));
		OM.reg("foodMuttoncooked"                   , ST.make(MD.GaSu, "mutton_cooked", 1, 0));
		OM.reg("foodMuttoncooked"                   , ST.make(MD.EtFu, "mutton_cooked", 1, 0));
		OM.reg("foodMuttoncooked"                   , ST.make(MD.WTCH, "ingredient", 1, 159));
		OM.reg("foodRabbitraw"                      , ST.make(MD.EtFu, "rabbit_raw", 1, 0));
		OM.reg("foodRabbitcooked"                   , ST.make(MD.EtFu, "rabbit_cooked", 1, 0));
		OM.reg(OD.sandstone                         , ST.make(MD.EtFu, "red_sandstone", 1, W));
		OM.reg("cropBerry"                          , ST.make(MD.EtFu, "sweet_berries", 1, 0));
		OM.reg(OD.container250poison                , ST.make(Items.potionitem, 1,  8196));
		OM.reg(OD.container250poison                , ST.make(Items.potionitem, 1,  8228));
		OM.reg(OD.container250poison                , ST.make(Items.potionitem, 1,  8260));
		OM.reg(OD.container250poison                , ST.make(Items.potionitem, 1, 16388));
		OM.reg(OD.container250poison                , ST.make(Items.potionitem, 1, 16420));
		OM.reg(OD.container250poison                , ST.make(Items.potionitem, 1, 16452));
		OM.reg(OD.container250poison                , ST.make(MD.EtFu, "lingering_potion", 1,  8196));
		OM.reg(OD.container250poison                , ST.make(MD.EtFu, "lingering_potion", 1,  8228));
		OM.reg(OD.container250poison                , ST.make(MD.EtFu, "lingering_potion", 1,  8260));
		OM.reg(OD.container1000rubbertreesap        , ST.make(MD.IHL, "bucket_fluidRubberTreeSap", 1, 0));
		OM.reg(OD.container1000spruceresin          , ST.make(MD.IHL, "bucket_SpruceResin", 1, 0));
		OM.reg("foodDough"                          , ST.make(MD.MF2, "MF_UFooddough", 1, 0));
		OM.reg("foodCheese"                         , ST.make(MD.GC, "item.cheeseCurd", 1, W));
		OM.reg("foodCheese"                         , ST.make(MD.AA, "itemFood", 1, 0));
		OM.reg("foodCarrotjuice"                    , ST.make(MD.AA, "itemFood", 1, 2));
		OM.reg("foodCookie"                         , ST.make(MD.AA, "itemFood", 1,12));
		OM.reg("foodBaconcooked"                    , ST.make(MD.AA, "itemFood", 1,20));
		OM.reg("foodDough"                          , ST.make(MD.AA, "itemMisc", 1, 4));
		OM.reg(OD.slimeballRice                     , ST.make(MD.AA, "itemMisc", 1,12));
		OM.reg(OD.blockClay                         , ST.make(Blocks.clay, 1, W));
		OM.reg(OD.craftingBook                      , ST.make(Items.book, 1, W));
		OM.reg(OD.craftingBook                      , ST.make(Items.writable_book, 1, W));
		OM.reg(OD.craftingBook                      , ST.make(Items.written_book, 1, W));
		OM.reg(OD.craftingBook                      , ST.make(Items.enchanted_book, 1, W));
		OM.reg(OD.enderChest                        , ST.make(Blocks.ender_chest, 1, W));
		OM.reg(OD.enderChest                        , ST.make(MD.GaEn, "anchoredEnderChest", 1, 0));
		OM.reg(OD.enderChest                        , ST.make(MD.GaEn, "enderBag", 1, 0));
		OM.reg(OD.craftingChest                     , ST.make(MD.NeLi, "CrimsonChest", 1, W));
		OM.reg(OD.craftingChest                     , ST.make(MD.NeLi, "WarpedChest", 1, W));
		OM.reg(OD.craftingChest                     , ST.make(MD.NeLi, "FoxfireChest", 1, W));
		OM.reg(OD.craftingChest                     , ST.make(MD.NeLi, "CrimsonBarrel", 1, W));
		OM.reg(OD.craftingChest                     , ST.make(MD.NeLi, "WarpedBarrel", 1, W));
		OM.reg(OD.craftingChest                     , ST.make(MD.NeLi, "FoxfireBarrel", 1, W));
		OM.reg(OD.craftingFurnace                   , ST.make(Blocks.furnace, 1, W));
		OM.reg(OD.craftingFurnace                   , ST.make(Blocks.lit_furnace, 1, W));
		OM.reg(OD.craftingFurnace                   , ST.make(MD.NeLi, "FurnaceBlackstone", 1, W));
		OM.reg(OD.craftingFurnace                   , ST.make(MD.NeLi, "FurnaceBlackstoneLit", 1, W));
		OM.reg(OD.craftingWorkBench                 , ST.make(Blocks.crafting_table, 1, W));
		OM.reg(OD.craftingWorkBench                 , ST.make(MD.NeLi, "NetherCraftingTable", 1, W));
		OM.reg(OD.craftingWorkBench                 , ST.make(MD.BC_FACTORY, "autoWorkbenchBlock", 1, 0));
		OM.reg(OD.buttonWood                        , ST.make(Blocks.wooden_button, 1, W));
		OM.reg(OD.buttonWood                        , ST.make(MD.TFC, "ButtonWood", 1, W));
		OM.reg(OD.buttonWood                        , ST.make(MD.TFCP, "ButtonWood", 1, W)); // This Item Name is speculation!
		OM.reg(OD.buttonWood                        , ST.make(MD.NeLi, "crimsonButton", 1, W));
		OM.reg(OD.buttonWood                        , ST.make(MD.NeLi, "warpedButton", 1, W));
		OM.reg(OD.buttonWood                        , ST.make(MD.NeLi, "foxfireButton", 1, W));
		OM.reg(OD.buttonWood                        , ST.make(MD.EtFu, "button_spruce", 1, W));
		OM.reg(OD.buttonWood                        , ST.make(MD.EtFu, "button_birch", 1, W));
		OM.reg(OD.buttonWood                        , ST.make(MD.EtFu, "button_jungle", 1, W));
		OM.reg(OD.buttonWood                        , ST.make(MD.EtFu, "button_dark_oak", 1, W));
		OM.reg(OD.buttonWood                        , ST.make(MD.EtFu, "button_acacia", 1, W));
		OM.reg(OD.buttonStone                       , ST.make(Blocks.stone_button, 1, W));
		OM.reg(OD.buttonStone                       , ST.make(MD.NeLi, "blackstoneButton", 1, W));
		OM.reg(OD.pressurePlateWood                 , ST.make(MD.NeLi, "pressurePlateCrimson", 1, W));
		OM.reg(OD.pressurePlateWood                 , ST.make(MD.NeLi, "pressurePlateWarped", 1, W));
		OM.reg(OD.pressurePlateWood                 , ST.make(MD.NeLi, "pressurePlateFoxfire", 1, W));
		OM.reg(OD.pressurePlateWood                 , ST.make(MD.EtFu, "pressure_plate_spruce", 1, W));
		OM.reg(OD.pressurePlateWood                 , ST.make(MD.EtFu, "pressure_plate_birch", 1, W));
		OM.reg(OD.pressurePlateWood                 , ST.make(MD.EtFu, "pressure_plate_jungle", 1, W));
		OM.reg(OD.pressurePlateWood                 , ST.make(MD.EtFu, "pressure_plate_dark_oak", 1, W));
		OM.reg(OD.pressurePlateWood                 , ST.make(MD.EtFu, "pressure_plate_acacia", 1, W));
		OM.reg(OD.pressurePlateStone                , ST.make(MD.NeLi, "pressurePlateBlackstone", 1, W));
		OM.reg(OD.lever                             , ST.make(Blocks.lever, 1, W));
		OM.reg(OD.craftingRedstoneTorch             , ST.make(Blocks.redstone_torch, 1, W));
		OM.reg(OD.craftingRedstoneTorch             , ST.make(Blocks.unlit_redstone_torch, 1, W));
		OM.reg(OD.blockTorch                        , ST.make(Blocks.torch, 1, W));
		OM.reg(OD.craftingPiston                    , ST.make(Blocks.piston, 1, W));
		OM.reg(OD.craftingPiston                    , ST.make(Blocks.sticky_piston, 1, W));
		OM.reg(OD.craftingAnvil                     , ST.make(Blocks.anvil, 1, 0));
		OM.reg(OD.craftingAnvil                     , ST.make(MD.RC, "anvil", 1, 0));
		OM.reg(OD.craftingHardenedClay              , ST.make(Blocks.hardened_clay, 1, W));
		OM.reg(OD.craftingHardenedClay              , ST.make(Blocks.stained_hardened_clay, 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamalmondSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamappleSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamapricotSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamavocadoSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pambananaSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamcashewSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamcherrySapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamchestnutSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamcinnamonSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamcoconutSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamdateSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamdragonfruitSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamdurianSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamfigSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamgooseberrySapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamgrapefruitSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamlemonSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamlimeSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pammangoSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pammapleSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamnutmegSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamoliveSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamorangeSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pampapayaSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pampaperbarkSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pampeachSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pampearSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pampecanSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pampeppercornSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pampersimmonSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pampistachioSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamplumSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pampomegranateSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamstarfruitSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamvanillabeanSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HaC, "pamwalnutSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "Fir Sapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_acaciaSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_ashSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_autumnOrangeSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_autumnYellowSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_beechSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_canopySapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_deadSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_decBushSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_evgBushSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_greatOakSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_ironwoodSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_japaneseMapleSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_mangroveSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_palmSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_poplarSapling", 1, W));
		OM.reg(OP.treeSapling                       , ST.make(MD.HiL, "tile.hl_redwoodSapling", 1, W));
		OM.reg(OP.treeLeaves                        , ST.make(MD.HiL, "tile.hl_acaciaLeaves", 1, W));
		OM.reg(OP.treeLeaves                        , ST.make(MD.HiL, "tile.hl_ashLeaves", 1, W));
		OM.reg(OP.treeLeaves                        , ST.make(MD.HiL, "tile.hl_autumnOrangeLeaves", 1, W));
		OM.reg(OP.treeLeaves                        , ST.make(MD.HiL, "tile.hl_autumnYellowLeaves", 1, W));
		OM.reg(OP.treeLeaves                        , ST.make(MD.HiL, "tile.hl_canopyLeaves", 1, W));
		OM.reg(OP.treeLeaves                        , ST.make(MD.HiL, "tile.hl_firLeaves", 1, W));
		OM.reg(OP.treeLeaves                        , ST.make(MD.HiL, "tile.hl_ironwoodLeaves", 1, W));
		OM.reg(OP.treeLeaves                        , ST.make(MD.HiL, "tile.hl_japaneseMapleLeaves", 1, W));
		OM.reg(OP.treeLeaves                        , ST.make(MD.HiL, "tile.hl_mangroveLeaves", 1, W));
		OM.reg(OP.treeLeaves                        , ST.make(MD.HiL, "tile.hl_palmLeaves", 1, W));
		OM.reg(OP.treeLeaves                        , ST.make(MD.HiL, "tile.hl_poplarLeaves", 1, W));
		OM.reg(OP.treeLeaves                        , ST.make(MD.HiL, "tile.hl_redwoodLeaves", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.HiL, "tile.hl_acaciaWood", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.HiL, "tile.hl_ashWood", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.HiL, "tile.hl_canopyWood", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.HiL, "tile.hl_firWood", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.HiL, "tile.hl_japaneseMapleWood", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.HiL, "tile.hl_mangroveWood", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.HiL, "tile.hl_palmWood", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.HiL, "tile.hl_poplarWood", 1, W));
		OM.reg(OD.logWood                           , ST.make(MD.HiL, "tile.hl_redwoodWood", 1, W));
		OM.reg(OD.flower                            , ST.make(MD.HiL, "tile.hl_blueFlower", 1, W));
		OM.reg(OD.flower                            , ST.make(MD.HiL, "tile.hl_lavender", 1, W));
		OM.reg(OD.flower                            , ST.make(MD.HiL, "tile.hl_whiteFlower", 1, W));
		OM.reg(OD.flower                            , ST.make(MD.HiL, "tile.hl_cotton", 1, W));
		OM.reg("cropBerry"                          , ST.make(MD.HiL, "hl_berries", 1, W));
		OM.reg("cropBerry"                          , ST.make(MD.AETHER, "enchantedBerry", 1, 0));
		OM.reg("cropStrawberry"                     , ST.make(MD.AETHER, "rainbowStrawberry", 1, 0));
		OM.reg("cropWyndberry"                      , ST.make(MD.AETHER, "wyndberry", 1, 0));
		OM.reg("cropBlueberry"                      , ST.make(MD.AETHER, "blueBerry", 1, 0));
		OM.reg("cropOrange"                         , ST.make(MD.AETHER, "orange", 1, 0));
		OM.reg("listAllegg"                         , ST.make(MD.AETHER, "moaEgg", 1, W));
		OM.reg(OD.slimeball                         , ST.make(MD.AETHER, "swetJelly", 1, 0));
		OM.reg(OD.slimeball                         , ST.make(MD.AETHER, "swetJelly", 1, 1));
		OM.reg(OD.slimeball                         , ST.make(MD.AETHER, "swetJelly", 1, 2));
		OM.reg(OD.slimeballSwet                     , ST.make(MD.AETHER, "swetJelly", 1, 0));
		OM.reg(OD.slimeballSwet                     , ST.make(MD.AETHER, "swetJelly", 1, 1));
		OM.reg(OD.slimeballSwet                     , ST.make(MD.AETHER, "swetJelly", 1, 2));
		OM.reg(OD.itemFeather                       , ST.make(MD.AETHER, "goldenFeather", 1, 0));
		OM.reg(OP.ingot.dat(MT.WaxBee)              , ST.make(MD.GrC_Bees, "grcbees.BeesWax", 1, 0));
		OM.reg(OP.ingot.dat(MT.WaxBee)              , ST.make(MD.GrC_Bees, "grcbees.BeesWax", 1, 1));
		OM.reg(OP.ingot.dat(MT.WaxBee)              , ST.make(MD.GrC_Bees, "grcbees.BeesWax", 1, 2));
		OM.reg(OP.ingot.dat(MT.Butter)              , ST.make(MD.GrC_Milk, "grcmilk.Butter", 1, 0));
		OM.reg(OP.ingot.dat(MT.ButterSalted)        , ST.make(MD.GrC_Milk, "grcmilk.Butter", 1, 1));
		OM.reg(OP.ingot.dat(MT.ButterSalted)        , ST.make(MD.HaC, "butterItem", 1, 0));
		OM.reg(OP.ingot.dat(MT.Cheese)              , ST.make(MD.HaC, "cheeseItem", 1, 0));
		OM.reg(OP.blockSolid, MT.Obsidian           , ST.make(MD.TC, "blockCosmeticSolid", 1, 0));
		OM.reg(OP.blockSolid, MT.Obsidian           , ST.make(MD.TC, "blockCosmeticSolid", 1, 1));
		OM.reg(OP.circuit.dat(MT.Basic)             , ST.mkic("electronicCircuit", 1));
		OM.reg(OP.circuit.dat(MT.Advanced)          , ST.mkic("advancedCircuit", 1));
		OM.reg("cropHemp"                           , ST.make(MD.IE, "material", 1, 3));
		OM.reg(OP.plate.dat(MT.WoodTreated)         , ST.make(MD.IE, "treatedWood", 1, 1));
		OM.reg(OP.plate.dat(MT.WoodTreated)         , ST.make(MD.IE, "treatedWood", 1, 2));
		OM.reg(OP.dust.dat(MT.NaCl)                 , ST.make(MD.MaCu, "materials", 1, 12));
		OM.reg(OP.dust.dat(MT.Obsidian)             , ST.make(MD.IC2, "item.itemObsidianDust", 1, 0));
		OM.reg(OP.dust.dat(MT.Wheat)                , ST.make(MD.IC2, "item.itemFlour", 1, 0));
		OM.reg("cropTea"                            , ST.make(MD.IC2, "item.itemTeaLeaf", 1, 0));
		OM.reg("cropGrapefruit"                     , ST.make(MD.TROPIC, "grapefruit", 1, 0));
		OM.reg("cropOrange"                         , ST.make(MD.TROPIC, "orange", 1, 0));
		OM.reg("cropLemon"                          , ST.make(MD.TROPIC, "lemon", 1, 0));
		OM.reg("cropLime"                           , ST.make(MD.TROPIC, "lime", 1, 0));
		OM.reg("cropAnanas"                         , ST.make(MD.TROPIC, "tile.pineapple", 1, W));
		OM.reg("cropCoconut"                        , ST.make(MD.TROPIC, "tile.coconut", 1, W));
		OM.reg("cropCoffee"                         , ST.make(MD.TROPIC, "coffeeBean", 1, W));
		OM.reg("cropHellderberry"                   , ST.make(MD.NeLi, "HellderBerryItem", 1, 0));
		OM.reg("foodHellderberryjuice"              , ST.make(MD.NeLi, "JuiceHellderberry", 1, 0));
		
		
		OM.reg(OP.dustSmall.dat(MT.Fe)              , ST.make(MD.BINNIE_BEE, "misc", 1, 6));
		OM.reg(OP.dustSmall.dat(MT.Au)              , ST.make(MD.BINNIE_BEE, "misc", 1, 7));
		OM.reg(OP.dustSmall.dat(MT.Ag)              , ST.make(MD.BINNIE_BEE, "misc", 1, 8));
		OM.reg(OP.dustSmall.dat(MT.Pt)              , ST.make(MD.BINNIE_BEE, "misc", 1, 9));
		OM.reg(OP.dustSmall.dat(MT.Cu)              , ST.make(MD.BINNIE_BEE, "misc", 1,10));
		OM.reg(OP.dustSmall.dat(MT.Sn)              , ST.make(MD.BINNIE_BEE, "misc", 1,11));
		OM.reg(OP.dustSmall.dat(MT.Ni)              , ST.make(MD.BINNIE_BEE, "misc", 1,12));
		OM.reg(OP.dustSmall.dat(MT.Pb)              , ST.make(MD.BINNIE_BEE, "misc", 1,13));
		OM.reg(OP.dustSmall.dat(MT.Zn)              , ST.make(MD.BINNIE_BEE, "misc", 1,14));
		OM.reg(OP.dustSmall.dat(MT.TiO2)            , ST.make(MD.BINNIE_BEE, "misc", 1,15));
		OM.reg(OP.dustSmall.dat(MT.OREMATS.Tungstate),ST.make(MD.BINNIE_BEE, "misc", 1,16));
		OM.reg(OP.dustSmall.dat(MT.U_238)           , ST.make(MD.BINNIE_BEE, "misc", 1,17));
		OM.reg(OP.dustSmall.dat(MT.Coal)            , ST.make(MD.BINNIE_BEE, "misc", 1,18));
		OM.reg(OP.dustSmall.dat(MT.Clay)            , ST.make(MD.BINNIE_BEE, "misc", 1,26));
		OM.reg(OP.dustSmall.dat(MT.Yellorium)       , ST.make(MD.BINNIE_BEE, "misc", 1,27));
		OM.reg(OP.dustSmall.dat(MT.Blutonium)       , ST.make(MD.BINNIE_BEE, "misc", 1,28));
		OM.reg(OP.dustSmall.dat(MT.Cyanite)         , ST.make(MD.BINNIE_BEE, "misc", 1,29));
		
		
		OM.reg(OD.record                            , ST.make(MD.HBM, "item.record_lc", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.HBM, "item.record_ss", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.HBM, "item.record_vc", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.HBM, "item.record_glass", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.NePl, "PigStep", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.MFR, "record.blank", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.RoC, "rotarycraft_item_disk", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BoP, "record_corruption", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BoP, "record_wanderer", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.MoCr, "recordshuffle", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BOTA, "recordGaia1", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BOTA, "recordGaia2", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.ALF, "FlugelDisc", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "record16612", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "recordAstatos", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "recordHagDance", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "recordWaterlogged", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "recordAncient", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "recordOnwards", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "recordStuckInTheMud", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "recordWanderingWisps", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "recordBeneathAGreenSky", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "recordChristmasOnTheMarsh", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "recordDJWightsMixtape", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "recordLonelyFire", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "recordBetweenYouAndMe", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.BTL, "recordTheExplorer", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.TROPIC, "record_buriedtreasure", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.TROPIC, "record_thetribe", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.TROPIC, "record_tradewinds", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.TROPIC, "record_lowtide", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.TROPIC, "record_easternisles", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.TROPIC, "record_summering", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.CANDY, "I11", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.CANDY, "I46", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.CANDY, "I98", 1, 0));
		OM.reg(OD.record                            , ST.make(MD.CANDY, "I101", 1, 0));
		
		
		OM.data(MD.BINNIE_BEE, "misc"                               , 1,   1, ANY.Diamond           , U9);
		OM.data(MD.BINNIE_BEE, "misc"                               , 1,   2, ANY.Emerald           , U9);
		OM.data(MD.BINNIE_BEE, "misc"                               , 1,   3, MT.Ruby               , U9);
		OM.data(MD.BINNIE_BEE, "misc"                               , 1,   4, ANY.Sapphire          , U9);
		OM.data(MD.BINNIE_BEE, "misc"                               , 1,   5, MT.Lapis              , U4);
		OM.data(MD.BINNIE_BOTANY, "encylopedia"                     , 1,   W, MT.Paper              , U*3);
		OM.data(MD.BINNIE_BOTANY, "encylopediaIron"                 , 1,   W, MT.Paper              , U*3, ANY.Fe, U);
		OM.data(MD.BC_BUILDERS, "blueprintItem"                     , 1,   W, MT.Paper              , U*8);
		OM.data(MD.BC_BUILDERS, "templateItem"                      , 1,   W, MT.Paper              , U*8);
		OM.data(MD.BC_ROBOTICS, "redstone_board"                    , 1,   W, MT.Paper              , U*8);
		OM.data(MD.BC, "mapLocation"                                , 1,   W, MT.Paper              , U*8);
		OM.data(MD.BC, "list"                                       , 1,   W, MT.Paper              , U*8);
		OM.data(MD.IE, "tool"                                       , 1,   3, MT.Paper              , U*3);
		OM.data(MD.AA, "itemBooklet"                                , 1,   W, MT.Paper              , U);
		OM.data(MD.RC, "routing.table"                              , 1,   W, MT.Paper              , U*3);
		OM.data(MD.FR, "catalogue"                                  , 1,   W, MT.Paper              , U*3);
		OM.data(MD.FR, "researchNote"                               , 1,   W, MT.Paper              , U);
		OM.data(MD.FR, "letters"                                    , 1,   W, MT.Paper              , U);
		OM.data(MD.FR, "stamps"                                     , 1,   W, MT.Paper              , U9);
		OM.data(MD.TE, "diagram"                                    , 1,   W, MT.Paper              , U*2);
		OM.data(MD.TF, "item.emptyMagicMap"                         , 1,   W, MT.Paper              , U*8);
		OM.data(MD.TF, "item.emptyMazeMap"                          , 1,   W, MT.Paper              , U*8);
		OM.data(MD.TF, "item.emptyOreMap"                           , 1,   W, MT.Paper              , U*8);
		OM.data(MD.TF, "item.magicMap"                              , 1,   W, MT.Paper              , U*8);
		OM.data(MD.TF, "item.mazeMap"                               , 1,   W, MT.Paper              , U*8);
		OM.data(MD.TF, "item.oreMap"                                , 1,   W, MT.Paper              , U*8);
		OM.data(MD.WTCH, "ingredient"                               , 1,  46, MT.Paper              , U*3);
		OM.data(MD.WTCH, "ingredient"                               , 1,  47, MT.Paper              , U*3);
		OM.data(MD.WTCH, "ingredient"                               , 1,  48, MT.Paper              , U*3);
		OM.data(MD.WTCH, "ingredient"                               , 1,  49, MT.Paper              , U*3);
		OM.data(MD.WTCH, "ingredient"                               , 1,  81, MT.Paper              , U*3);
		OM.data(MD.WTCH, "ingredient"                               , 1, 106, MT.Paper              , U*3);
		OM.data(MD.WTCH, "ingredient"                               , 1, 107, MT.Paper              , U*3);
		OM.data(MD.WTCH, "ingredient"                               , 1, 127, MT.Paper              , U*3);
		OM.data(MD.WTCH, "ingredient"                               , 1, 140, MT.Paper              , U);
		OM.data(MD.WTCH, "ingredient"                               , 1, 141, MT.Paper              , U);
		OM.data(MD.WTCH, "ingredient"                               , 1, 142, MT.Paper              , U);
		OM.data(MD.WTCH, "ingredient"                               , 1, 143, MT.Paper              , U);
		OM.data(MD.WTCH, "ingredient"                               , 1, 144, MT.Paper              , U);
		OM.data(MD.WTCH, "ingredient"                               , 1, 145, MT.Paper              , U);
		OM.data(MD.WTCH, "ingredient"                               , 1, 146, MT.Paper              , U);
		OM.data(MD.WTCH, "ingredient"                               , 1, 160, MT.Paper              , U);
		OM.data(MD.WTCH, "biomenote"                                , 1,   W, MT.Paper              , U);
		OM.data(MD.WTCH, "bookbiomes2"                              , 1,   W, MT.Paper              , U*3);
		OM.data(MD.WTCH, "cauldronbook"                             , 1,   W, MT.Paper              , U*3);
		OM.data(MD.WTCH, "vampirebook"                              , 1,   W, MT.Paper              , U*3);
		OM.data(MD.HOWL, "lycanthropeBook"                          , 1,   W, MT.Paper              , U*3);
		OM.data(MD.BbLC, "item.SlottedBook"                         , 1,   W, MT.Paper              , U*3);
		OM.data(MD.BbLC, "item.BiblioRedBook"                       , 1,   W, MT.Paper              , U*3);
		OM.data(MD.BbLC, "item.TesterItem"                          , 1,   W, MT.Paper              , U*3);
		OM.data(MD.BbLC, "item.RecipeBook"                          , 1,   W, MT.Paper              , U*3);
		OM.data(MD.BbLC, "item.StockroomCatalog"                    , 1,   W, MT.Paper              , U*10);
		OM.data(MD.BbLC, "item.BigBook"                             , 1,   W, MT.Paper              , U*11);
		OM.data(MD.BbLC, "item.AtlasBook"                           , 1,   W, MT.Paper              , U*17);
		OM.data(MD.OB, "infoBook"                                   , 1,   W, MT.Paper              , U*3);
		OM.data(MD.OC, "item"                                       , 1,  98, MT.Paper              , U*3);
		OM.data(MD.HQM, "quest_book"                                , 1,   W, MT.Paper              , U*3);
		OM.data(MD.TC, "ItemResearchNotes"                          , 1,   W, MT.Paper              , U);
		OM.data(MD.MoCr, "scrollofsale"                             , 1,   W, MT.Paper              , U);
		OM.data(MD.MoCr, "scrollofowner"                            , 1,   W, MT.Paper              , U);
		OM.data(MD.MoCr, "scrolloffreedom"                          , 1,   W, MT.Paper              , U);
		OM.data(MD.RoC, "rotarycraft_item_handbook"                 , 1,   W, MT.Paper              , U*6);
		OM.data(MD.ReC, "reactorcraft_item_book"                    , 1,   W, MT.Paper              , U*6);
		OM.data(MD.ElC, "electricraft_item_book"                    , 1,   W, MT.Paper              , U*6);
		OM.data(MD.CrC, "chromaticraft_item_help"                   , 1,   W, MT.Paper              , U*3);
		OM.data(MD.CrC, "chromaticraft_item_fragment"               , 1,   W, MT.Paper              , U);
		OM.data(MD.PR_EXPANSION, "projectred.expansion.plan"        , 1,   W, MT.Paper              , U);
		OM.data(MD.LOSTBOOKS, "randomBook"                          , 1,   W, MT.Paper              , U*3);
		OM.data(MD.WARPBOOK, "warpbook"                             , 1,   W, MT.Paper              , U*3);
		OM.data(MD.WARPBOOK, "warppage"                             , 1,   W, MT.Paper              , U);
		OM.data(MD.BOTA, "lexicon"                                  , 1,   W, MT.Paper              , U*3);
		OM.data(MD.MNTL, "mantleBook"                               , 1,   W, MT.Paper              , U*3);
		OM.data(MD.TiC, "manualBook"                                , 1,   W, MT.Paper              , U);
		OM.data(MD.CC, "printout"                                   , 1,   0, MT.Paper              , U);
		OM.data(MD.CC, "printout"                                   , 1,   1, MT.Paper              , U*2);
		OM.data(MD.CC, "printout"                                   , 1,   2, MT.Paper              , U);
		OM.data(MD.GC, "item.schematic"                             , 1,   W, MT.Paper              , U*8);
		OM.data(MD.GC_PLANETS, "item.schematic"                     , 1,   W, MT.Paper              , U*8);
		OM.data(MD.GC_GALAXYSPACE, "item.ItemSchematics"            , 1,   W, MT.Paper              , U*8);
		OM.data(MD.TROPIC, "encTropica"                             , 1,   W, MT.Paper              , U*3);
		
		for (byte i = 0; i < 16; i++) OM.reg(OP.dyeMixable.mNameInternal + DYE_OREDICTS_POST[i], ST.make(Items.dye, 1, i));
		
		if (MD.PFAA.mLoaded) {
			OM.reg(OD.blockClay, ST.make(MD.PFAA, "weakClay"   , 1,  0));
			OM.reg(OD.blockClay, ST.make(MD.PFAA, "weakOreClay", 1,  1));
			OM.reg(OD.blockClay, ST.make(MD.PFAA, "weakOreClay", 1,  2));
			OM.reg(OD.blockClay, ST.make(MD.PFAA, "weakOreClay", 1,  3));
			OM.data(MD.PFAA, "weakClay"   , 1,  0, MT.ClayBrown             , U*4);
			OM.data(MD.PFAA, "weakOreClay", 1,  1, MT.Bentonite             , U*4);
			OM.data(MD.PFAA, "weakOreClay", 1,  2, MT.Palygorskite          , U*4);
			OM.data(MD.PFAA, "weakOreClay", 1,  3, MT.Kaolinite             , U*4);
			
			OM.reg(OD.itemClay , ST.make(MD.PFAA, "earthyClump", 1, 45));
			OM.reg(OD.itemClay , ST.make(MD.PFAA, "earthyClump", 1, 47));
			OM.reg(OD.itemClay , ST.make(MD.PFAA, "earthyClump", 1, 48));
			OM.reg(OD.itemClay , ST.make(MD.PFAA, "earthyClump", 1, 49));
			OM.data(MD.PFAA, "earthyClump", 1, 37, MT.OREMATS.YellowLimonite, U);
			OM.data(MD.PFAA, "earthyClump", 1, 45, MT.ClayBrown             , U);
			OM.data(MD.PFAA, "earthyClump", 1, 46, MT.OREMATS.Bauxite       , U);
			OM.data(MD.PFAA, "earthyClump", 1, 47, MT.Bentonite             , U);
			OM.data(MD.PFAA, "earthyClump", 1, 48, MT.Palygorskite          , U);
			OM.data(MD.PFAA, "earthyClump", 1, 49, MT.Kaolinite             , U);
			OM.data(MD.PFAA, "earthyClump", 1, 50, MT.OREMATS.BrownLimonite , U);
			OM.data(MD.PFAA, "earthyClump", 1, 51, MT.OREMATS.YellowLimonite, U);
			OM.data(MD.PFAA, "earthyClump", 1, 52, MT.OREMATS.Vermiculite   , U);
			
			for (String tName : new String [] {"weakStone", "weakRubble"}) {
				OM.reg(OP.stone, MT.Stone                       , ST.make(MD.PFAA, tName, 1,  0)); // Breccia
				OM.reg(OP.stone, MT.Stone                       , ST.make(MD.PFAA, tName, 1,  1)); // Claystone
				OM.reg(OP.stone, MT.Stone                       , ST.make(MD.PFAA, tName, 1,  2)); // Carbonatite
				OM.reg(OP.stone, MT.Stone                       , ST.make(MD.PFAA, tName, 1,  3)); // Conglomerate
				OM.reg(OP.stone, MT.STONES.Shale                , ST.make(MD.PFAA, tName, 1,  4));
			}
			for (String tName : new String [] {"mediumStone", "mediumCobble", "mediumStoneBrick"}) {
				OM.reg(OP.stone, MT.STONES.Limestone            , ST.make(MD.PFAA, tName, 1,  0));
				OM.reg(OP.stone, MT.Stone                       , ST.make(MD.PFAA, tName, 1,  1)); // Light Red/Pink Schist
				OM.reg(OP.stone, MT.Stone                       , ST.make(MD.PFAA, tName, 1,  2)); // Serpentite
				OM.reg(OP.stone, MT.STONES.Slate                , ST.make(MD.PFAA, tName, 1,  3));
				OM.reg(OP.stone, MT.Stone                       , ST.make(MD.PFAA, tName, 1,  4)); // Skarn
			}
			for (String tName : new String [] {"strongStone", "strongCobble", "strongStoneBrick"}) {
				OM.reg(OP.stone, MT.STONES.Andesite             , ST.make(MD.PFAA, tName, 1,  0));
				OM.reg(OP.stone, MT.STONES.Basalt               , ST.make(MD.PFAA, tName, 1,  1));
				OM.reg(OP.stone, MT.STONES.Gneiss               , ST.make(MD.PFAA, tName, 1,  2));
				OM.reg(OP.stone, MT.STONES.GraniteBlack         , ST.make(MD.PFAA, tName, 1,  3)); // actually Gray
				OM.reg(OP.stone, MT.STONES.Greenschist          , ST.make(MD.PFAA, tName, 1,  4));
				OM.reg(OP.stone, MT.STONES.Marble               , ST.make(MD.PFAA, tName, 1,  5));
				OM.reg(OP.stone, MT.Stone                       , ST.make(MD.PFAA, tName, 1,  6)); // Pegmatite
				OM.reg(OP.stone, MT.STONES.Rhyolite             , ST.make(MD.PFAA, tName, 1,  7));
				OM.reg(OP.stone, MT.Sand                        , ST.make(MD.PFAA, tName, 1,  8));
				OM.reg(OP.stone, MT.RedSand                     , ST.make(MD.PFAA, tName, 1,  9));
			}
			for (String tName : new String [] {"veryStrongStone", "veryStrongCobble", "veryStrongStoneBrick"}) {
				OM.reg(OP.stone, MT.STONES.Diorite              , ST.make(MD.PFAA, tName, 1,  0));
				OM.reg(OP.stone, MT.STONES.Gabbro               , ST.make(MD.PFAA, tName, 1,  1));
				OM.reg(OP.stone, MT.Stone                       , ST.make(MD.PFAA, tName, 1,  2)); // Hornfels
				OM.reg(OP.stone, MT.Stone                       , ST.make(MD.PFAA, tName, 1,  3)); // Peridotite
				OM.reg(OP.stone, MT.STONES.Quartzite            , ST.make(MD.PFAA, tName, 1,  4));
			}
		}
		
		if (MD.UB.mLoaded) {
			for (String tName : new String [] {"sedimentaryStone"}) {
				OM.reg(OP.stone, MT.STONES.Limestone            , ST.make(MD.UB, tName, 1,  0));
				OM.reg(OP.stone, MT.Chalk                       , ST.make(MD.UB, tName, 1,  1));
				OM.reg(OP.stone, MT.STONES.Shale                , ST.make(MD.UB, tName, 1,  2));
				OM.reg(OP.stone, MT.STONES.Siltstone            , ST.make(MD.UB, tName, 1,  3));
				OM.reg(OP.stone, MT.Lignite                     , ST.make(MD.UB, tName, 1,  4));
				OM.reg(OP.stone, MT.Dolomite                    , ST.make(MD.UB, tName, 1,  5));
				OM.reg(OP.stone, MT.STONES.Greywacke            , ST.make(MD.UB, tName, 1,  6));
				OM.reg(OP.stone, MT.STONES.Chert                , ST.make(MD.UB, tName, 1,  7));
				OM.reg(OP.stone, MT.STONES.Limestone            , ST.make(MD.UB, tName, 1,  8));
				OM.reg(OP.stone, MT.Chalk                       , ST.make(MD.UB, tName, 1,  9));
				OM.reg(OP.stone, MT.STONES.Shale                , ST.make(MD.UB, tName, 1, 10));
				OM.reg(OP.stone, MT.STONES.Siltstone            , ST.make(MD.UB, tName, 1, 11));
				OM.reg(OP.stone, MT.Lignite                     , ST.make(MD.UB, tName, 1, 12));
				OM.reg(OP.stone, MT.Dolomite                    , ST.make(MD.UB, tName, 1, 13));
				OM.reg(OP.stone, MT.STONES.Greywacke            , ST.make(MD.UB, tName, 1, 14));
				OM.reg(OP.stone, MT.STONES.Chert                , ST.make(MD.UB, tName, 1, 15));
			}
			
			for (String tName : new String [] {"igneousStone", "igneousCobblestone", "igneousStoneBrick"}) {
				OM.reg(OP.stone, MT.STONES.GraniteRed           , ST.make(MD.UB, tName, 1,  0));
				OM.reg(OP.stone, MT.STONES.GraniteBlack         , ST.make(MD.UB, tName, 1,  1));
				OM.reg(OP.stone, MT.STONES.Rhyolite             , ST.make(MD.UB, tName, 1,  2));
				OM.reg(OP.stone, MT.STONES.Andesite             , ST.make(MD.UB, tName, 1,  3));
				OM.reg(OP.stone, MT.STONES.Gabbro               , ST.make(MD.UB, tName, 1,  4));
				OM.reg(OP.stone, MT.STONES.Basalt               , ST.make(MD.UB, tName, 1,  5));
				OM.reg(OP.stone, MT.STONES.Komatiite            , ST.make(MD.UB, tName, 1,  6));
				OM.reg(OP.stone, MT.STONES.Dacite               , ST.make(MD.UB, tName, 1,  7));
				OM.reg(OP.stone, MT.STONES.GraniteRed           , ST.make(MD.UB, tName, 1,  8));
				OM.reg(OP.stone, MT.STONES.GraniteBlack         , ST.make(MD.UB, tName, 1,  9));
				OM.reg(OP.stone, MT.STONES.Rhyolite             , ST.make(MD.UB, tName, 1, 10));
				OM.reg(OP.stone, MT.STONES.Andesite             , ST.make(MD.UB, tName, 1, 11));
				OM.reg(OP.stone, MT.STONES.Gabbro               , ST.make(MD.UB, tName, 1, 12));
				OM.reg(OP.stone, MT.STONES.Basalt               , ST.make(MD.UB, tName, 1, 13));
				OM.reg(OP.stone, MT.STONES.Komatiite            , ST.make(MD.UB, tName, 1, 14));
				OM.reg(OP.stone, MT.STONES.Dacite               , ST.make(MD.UB, tName, 1, 15));
			}
			
			for (String tName : new String [] {"metamorphicStone", "metamorphicCobblestone", "metamorphicStoneBrick"}) {
				OM.reg(OP.stone, MT.STONES.Gneiss               , ST.make(MD.UB, tName, 1,  0));
				OM.reg(OP.stone, MT.STONES.Eclogite             , ST.make(MD.UB, tName, 1,  1));
				OM.reg(OP.stone, MT.STONES.Marble               , ST.make(MD.UB, tName, 1,  2));
				OM.reg(OP.stone, MT.STONES.Quartzite            , ST.make(MD.UB, tName, 1,  3));
				OM.reg(OP.stone, MT.STONES.Blueschist           , ST.make(MD.UB, tName, 1,  4));
				OM.reg(OP.stone, MT.STONES.Greenschist          , ST.make(MD.UB, tName, 1,  5));
				OM.reg(OP.stone, MT.Talc                        , ST.make(MD.UB, tName, 1,  6));
				OM.reg(OP.stone, MT.STONES.Migmatite            , ST.make(MD.UB, tName, 1,  7));
				OM.reg(OP.stone, MT.STONES.Gneiss               , ST.make(MD.UB, tName, 1,  8));
				OM.reg(OP.stone, MT.STONES.Eclogite             , ST.make(MD.UB, tName, 1,  9));
				OM.reg(OP.stone, MT.STONES.Marble               , ST.make(MD.UB, tName, 1, 10));
				OM.reg(OP.stone, MT.STONES.Quartzite            , ST.make(MD.UB, tName, 1, 11));
				OM.reg(OP.stone, MT.STONES.Blueschist           , ST.make(MD.UB, tName, 1, 12));
				OM.reg(OP.stone, MT.STONES.Greenschist          , ST.make(MD.UB, tName, 1, 13));
				OM.reg(OP.stone, MT.Talc                        , ST.make(MD.UB, tName, 1, 14));
				OM.reg(OP.stone, MT.STONES.Migmatite            , ST.make(MD.UB, tName, 1, 15));
			}
		}
		
		if (MD.EB.mLoaded) {
			for (String tName : new String [] {"enhancedbiomes.tile.stoneEB", "enhancedbiomes.tile.stoneCobbleEB", "enhancedbiomes.tile.stoneBrickEB"}) {
				OM.reg(OP.stone, MT.STONES.Basalt               , ST.make(MD.EB, tName, 1, 0));
				OM.reg(OP.stone, MT.STONES.Shale                , ST.make(MD.EB, tName, 1, 1));
				OM.reg(OP.stone, MT.Sand                        , ST.make(MD.EB, tName, 1, 2));
				OM.reg(OP.stone, MT.STONES.Limestone            , ST.make(MD.EB, tName, 1, 3));
				OM.reg(OP.stone, MT.Stone                       , ST.make(MD.EB, tName, 1, 4)); // Slate
				OM.reg(OP.stone, MT.STONES.Rhyolite             , ST.make(MD.EB, tName, 1, 5));
				OM.reg(OP.stone, MT.Chalk                       , ST.make(MD.EB, tName, 1, 6));
				OM.reg(OP.stone, MT.STONES.Marble               , ST.make(MD.EB, tName, 1, 7));
				OM.reg(OP.stone, MT.Dolomite                    , ST.make(MD.EB, tName, 1, 8));
				OM.reg(OP.stone, MT.STONES.Blueschist           , ST.make(MD.EB, tName, 1, 9)); // More of a Neutral Gray
				OM.reg(OP.stone, MT.STONES.Chert                , ST.make(MD.EB, tName, 1,10));
				OM.reg(OP.stone, MT.STONES.Gabbro               , ST.make(MD.EB, tName, 1,11));
				OM.reg(OP.stone, MT.STONES.Dacite               , ST.make(MD.EB, tName, 1,12));
			}
		}
		
		if (MD.ERE.mLoaded) {
			OM.reg(OP.stone, MT.STONES.Umber                    , ST.make(MD.ERE, "umberstone"                  , 1, 2));
			OM.reg(OP.stone, MT.STONES.Umber                    , ST.make(MD.ERE, "umberstone"                  , 1, 3));
			OM.reg(OP.stone, MT.STONES.Umber                    , ST.make(MD.ERE, "umberstone"                  , 1, 4));
			OM.reg(OP.stone, MT.STONES.Umber                    , ST.make(MD.ERE, "umberstone"                  , 1, 5));
			OM.reg(OP.stone, MT.STONES.Umber                    , ST.make(MD.ERE, "umberstone"                  , 1, 6));
			OM.reg(OP.stone, MT.STONES.Gneiss                   , ST.make(MD.ERE, "gneiss"                      , 1, 0));
			OM.reg(OP.stone, MT.STONES.Gneiss                   , ST.make(MD.ERE, "gneiss"                      , 1, 1));
			OM.reg(OP.stone, MT.STONES.Gneiss                   , ST.make(MD.ERE, "gneiss"                      , 1, 2));
			OM.reg(OP.stone, MT.STONES.Gneiss                   , ST.make(MD.ERE, "gneiss"                      , 1, 3));
			OM.reg(OP.stone, MT.STONES.Gneiss                   , ST.make(MD.ERE, "gneiss"                      , 1, 4));
			OM.reg(OP.stone, MT.STONES.Gneiss                   , ST.make(MD.ERE, "gneiss"                      , 1, 5));
		}
		
		if (MD.AETHER.mLoaded) {
			OM.reg(OP.stone, MT.STONES.Holystone                , ST.make(MD.AETHER, "holystone"                , 1, 0));
			OM.reg(OP.stone, MT.STONES.Holystone                , ST.make(MD.AETHER, "holystone"                , 1, 1));
			OM.reg(OP.stone, MT.STONES.Holystone                , ST.make(MD.AETHER, "holystone"                , 1, 2));
			OM.reg(OP.stone, MT.STONES.Holystone                , ST.make(MD.AETHER, "holystone"                , 1, 3));
			OM.reg(OP.stone, MT.STONES.Holystone                , ST.make(MD.AETHER, "holystoneBrick"           , 1, 0));
			OM.reg(OP.stone, MT.STONES.Holystone                , ST.make(MD.AETHER, "holystoneKeystone"        , 1, 0));
			OM.reg(OP.stone, MT.STONES.Holystone                , ST.make(MD.AETHER, "holystoneHeadstone"       , 1, 0));
			OM.reg(OP.stone, MT.STONES.Holystone                , ST.make(MD.AETHER, "holystoneHighlight"       , 1, 0));
		}
		
		if (MD.GC.mLoaded) {
			BlocksGT.sDontGenerateOresIn.add(ST.make(MD.GC, "tile.moonBlock", 1, 5));
			
			OM.reg(OP.stone, MT.STONES.MoonRock                 , ST.make(MD.GC, "tile.moonBlock"               , 1, 4));
			OM.reg(OP.stone, MT.STONES.MoonTurf                 , ST.make(MD.GC, "tile.moonBlock"               , 1, 5));
		}
		if (MD.GC_PLANETS.mLoaded) {
			BlocksGT.sDontGenerateOresIn.add(ST.make(MD.GC_PLANETS, "tile.mars", 1, 5));
			
			OM.reg(OP.stone, MT.STONES.MarsRock                 , ST.make(MD.GC_PLANETS, "tile.mars"            , 1, 4));
			OM.reg(OP.stone, MT.STONES.MarsSand                 , ST.make(MD.GC_PLANETS, "tile.mars"            , 1, 5));
			OM.reg(OP.stone, MT.STONES.MarsRock                 , ST.make(MD.GC_PLANETS, "tile.mars"            , 1, 6));
			OM.reg(OP.stone, MT.STONES.MarsRock                 , ST.make(MD.GC_PLANETS, "tile.mars"            , 1, 9));
			
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_PLANETS, "tile.asteroidsBlock"  , 1, 0));
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_PLANETS, "tile.asteroidsBlock"  , 1, 1));
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_PLANETS, "tile.asteroidsBlock"  , 1, 2));
		}
		if (MD.GC_GALAXYSPACE.mLoaded) {
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_GALAXYSPACE, "mercuryblocks"    , 1, 2));
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_GALAXYSPACE, "ceresblocks"      , 1, 1));
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_GALAXYSPACE, "deimosblocks"     , 1, 1));
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_GALAXYSPACE, "ioblocks"         , 1, 2));
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_GALAXYSPACE, "oberonblocks"     , 1, 2));
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_GALAXYSPACE, "proteusblocks"    , 1, 2));
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_GALAXYSPACE, "tritonblocks"     , 1, 2));
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_GALAXYSPACE, "mirandablocks"    , 1, 2));
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_GALAXYSPACE, "venusblocks"      , 1, 1));
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_GALAXYSPACE, "phobosblocks"     , 1, 2));
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_GALAXYSPACE, "ganymedeblocks"   , 1, 1));
			OM.reg(OP.stone, MT.STONES.SpaceRock                , ST.make(MD.GC_GALAXYSPACE, "barnardaEsubgrunt", 1, 0));
		}
		
		OM.reg(OP.stone, MT.STONES.Redrock                      , ST.make(MD.EBXL, "terrain_blocks1"    , 1, 0));
		OM.reg(OP.stone, MT.STONES.Redrock                      , ST.make(MD.EBXL, "terrain_blocks1"    , 1, 1));
		OM.reg(OP.stone, MT.STONES.Redrock                      , ST.make(MD.EBXL, "terrain_blocks1"    , 1, 2));
//      OM.reg(OP.stone, MT.CrackedSand                         , ST.make(MD.EBXL, "terrain_blocks2"    , 1, 0));
		OM.reg(OP.stone, MT.STONES.Basalt                       , ST.make(MD.TROPIC, "tile.chunk"       , 1, W));
		OM.reg(OP.stone, MT.STONES.Basalt                       , ST.make(MD.IC2, "blockBasalt"         , 1, 0));
		OM.reg(OP.stone, MT.STONES.Basalt                       , ST.make(MD.RC, "brick.abyssal"        , 1, W));
		OM.reg(OP.stone, MT.STONES.Marble                       , ST.make(MD.RC, "brick.quarried"       , 1, W));
		OM.reg(OP.stone, MT.STONES.Limestone                    , ST.make(MD.MF2, "limestone"           , 1, W));
		OM.reg(OP.blockSolid, MT.Obsidian                       , ST.make(Blocks.obsidian               , 1, W));
		OM.reg(OP.stoneMossy                                    , ST.make(Blocks.mossy_cobblestone      , 1, W));
		OM.reg(OP.stoneCobble                                   , ST.make(Blocks.mossy_cobblestone      , 1, W));
		OM.reg(OP.stoneCobble                                   , ST.make(Blocks.cobblestone            , 1, W));
		OM.reg(OP.stoneSmooth                                   , ST.make(Blocks.stone                  , 1, W));
		OM.reg(OP.stoneBricks                                   , ST.make(Blocks.stonebrick             , 1, W));
		OM.reg(OP.stoneMossy                                    , ST.make(Blocks.stonebrick             , 1, 1));
		OM.reg(OP.stoneMossyBricks                              , ST.make(Blocks.stonebrick             , 1, 1));
		OM.reg(OP.stoneCracked                                  , ST.make(Blocks.stonebrick             , 1, 2));
		OM.reg(OP.stoneChiseled                                 , ST.make(Blocks.stonebrick             , 1, 3));
		OM.reg(OP.stone, MT.Sand                                , ST.make(Blocks.sandstone              , 1, W));
		OM.reg(OP.stone, MT.Bedrock                             , ST.make(Blocks.bedrock                , 1, W));
		OM.reg(OP.stone, MT.Netherrack                          , ST.make(Blocks.netherrack             , 1, W));
		OM.reg(OP.stone, MT.NetherBrick                         , ST.make(Blocks.nether_brick           , 1, W));
		OM.reg(OP.stone, MT.Endstone                            , ST.make(Blocks.end_stone              , 1, W));
		OM.reg(OP.stone, MT.SoulSand                            , ST.make(MD.NeLi, "SoulSandstone"      , 1, W));
		
		
		OM.data(MD.LycM_Demon, "demoncrystal"               , 1,   W, MT.Glowstone          , U*4);
		
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,   0, MT.HSLA               , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,   1, MT.Sn                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,   2, MT.Ni                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,   3, MT.Al                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,   4, MT.Cu                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,   5, MT.Ag                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,   6, MT.Au                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,   7, MT.Pt                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,   8, MT.HSLA               , 2*U16, ANY.W  , 2* U16, MT.Ag                 , 2* U16, MT.Au                 , 1* U16, MT.Redstone           , 1* U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,  16, MT.HSLA               , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,  17, MT.Sn                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,  18, MT.Ni                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,  19, MT.Al                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,  20, MT.Cu                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,  21, MT.Ag                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,  22, MT.Au                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,  23, MT.Pt                 , 3*U16);
		OM.data(MD.ElC, "electricraft_item_wire"            , 1,  24, MT.HSLA               , 2*U16, ANY.W  , 2* U16, MT.Ag                 , 2* U16, MT.Au                 , 1* U16, MT.Redstone           , 1* U16);
		
		OM.data(MD.ElC, "electricraft_item_placer"          , 1,   1, MT.HSLA               , 96* U9, MT.Au                 ,  U * 8, MT.Ni                 ,  U * 1, MT.Cu                 ,  U * 1, MT.Sn                 ,  U * 1);
		OM.data(MD.ElC, "electricraft_item_placer"          , 1,   2, MT.HSLA               ,  U * 7, MT.Au                 ,  U * 8, MT.Ag                 ,  U * 2, MT.Cu                 ,  U * 2);
		OM.data(MD.ElC, "electricraft_item_placer"          , 1,   3, MT.HSLA               ,  U * 1, MT.Coal               ,  U2);
		OM.data(MD.ElC, "electricraft_item_placer"          , 1,   4, MT.HSLA               ,  3* U4, MT.Cu                 ,  3* U4);
		OM.data(MD.ElC, "electricraft_item_placer"          , 1,   6, ANY.Diamond           , 1* U16, MT.Redstone           ,36* U16, MT.EnderPearl         , 1* U16, MT.Au                 , 9* U16);
		OM.data(MD.ElC, "electricraft_item_placer"          , 1,   7, MT.HSLA               ,  U *11, MT.Ag                 , 6* U16, MT.Redstone           ,  4* U3, MT.Glass              ,  3* U8);
		OM.data(MD.ElC, "electricraft_item_placer"          , 1,   9, MT.HSLA               ,  U * 6, MT.InductiveAlloy     ,  U * 2);
		OM.data(MD.ElC, "electricraft_item_placer"          , 1,  10, MT.HSLA               ,  U * 5, MT.Au                 ,  U * 4, MT.Cu                 ,  U * 4);
		
		
		OM.data(MD.RoC, "rotarycraft_block_deco"            , 1,   3, MT.HSLA               ,  U    , MT.Obsidian           ,  9* U4);
		OM.data(MD.RoC, "rotarycraft_block_decotank"        , 1,   0, MT.HSLA               ,  U    , MT.Glass              ,15* U32);
		OM.data(MD.RoC, "rotarycraft_item_screwdriver"      , 1,   0, MT.HSLA               ,  U    , ANY.Wood      ,  3* U2);
		OM.data(MD.RoC, "rotarycraft_item_meter"            , 1,   0, MT.HSLA               ,  U * 3, MT.Redstone           ,  U * 1, ANY.Wood      ,  U * 3);
		OM.data(MD.RoC, "rotarycraft_item_spring"           , 1,   W, MT.HSLA               ,  U * 4);
		OM.data(MD.RoC, "rotarycraft_item_ultrasound"       , 1,   W, MT.HSLA               ,  U *13, MT.Redstone           , 13* U3, ANY.Wood      ,  U * 8, MT.Glass              ,  3* U8);
		OM.data(MD.RoC, "rotarycraft_item_motion"           , 1,   W, MT.HSLA               ,  U *22, MT.Redstone           , 25* U3, MT.Au                 ,  U * 1, ANY.Wood      ,  U * 8, MT.Glass              ,3* U8);
		OM.data(MD.RoC, "rotarycraft_item_vacuum"           , 1,   W, MT.HSLA               , 87* U9);
		OM.data(MD.RoC, "rotarycraft_item_stungun"          , 1,   W, MT.HSLA               ,  U *12, MT.Redstone           , 11* U3, ANY.Wood      ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_gravelgun"        , 1,   W, MT.HSLA               , 57* U9, MT.Redstone           ,  U * 1, ANY.Stone     ,  U * 7, ANY.Wood      ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_fireball"         , 1,   W, MT.HSLA               ,  U *16, MT.Redstone           ,  U * 3, MT.Au                 ,  U * 1, MT.Blaze              ,  U9, ANY.Wood      ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_nvg"              , 1,   W, MT.HSLA               ,  U * 8, MT.EnderEye           ,  U * 2, MT.Redstone           ,  2* U3, MT.Glass              ,  3* U8);
		OM.data(MD.RoC, "rotarycraft_item_handcraft"        , 1,   0, MT.HSLA               ,  U * 2, MT.Au                 ,  U * 2, ANY.Wood      ,  U * 4);
		OM.data(MD.RoC, "rotarycraft_item_railgun"          , 1,   0, MT.HSLA               ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_key"              , 1,   0, MT.HSLA               ,  U * 3);
		OM.data(MD.RoC, "rotarycraft_item_shell"            , 1,   0, MT.HSLA               ,  U4); // +  U16 AmmoniumNitrate
		OM.data(MD.RoC, "rotarycraft_item_target"           , 1,   0, MT.HSLA               ,  U * 4, MT.EnderPearl         ,  U * 1, MT.Redstone           ,  U * 1, MT.Lapis              ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_tileselector"     , 1,   0, MT.HSLA               ,  U * 4, MT.EnderPearl         ,  U * 1, MT.Redstone           ,  U * 1, MT.Lapis              ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_iogoggles"        , 1,   W, MT.HSLA               ,  U * 3, MT.EnderPearl         ,  U * 1, MT.Redstone           ,  U * 2);
		OM.data(MD.RoC, "rotarycraft_item_jetpack"          , 1,   W, MT.HSLA               ,552* U9, MT.Redstone           ,  U * 4, MT.Au                 ,  U * 2);
		OM.data(MD.RoC, "rotarycraft_item_bedpack"          , 1,   W, MT.HSLA               ,552* U9, MT.Redstone           ,  U * 4, MT.Au                 ,  U * 2, MT.Bedrock_HSLA_Alloy ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_strongcoil"       , 1,   W, MT.HSLA               ,  U * 8, ANY.Diamond           ,  U * 2, MT.Bedrock            ,  U * 2);
		OM.data(MD.RoC, "rotarycraft_item_pump"             , 1,   W, MT.HSLA               ,337* U24,MT.Glass              , 3* U16);
		OM.data(MD.RoC, "rotarycraft_item_jump"             , 1,   W, MT.HSLA               ,204* U9);
		OM.data(MD.RoC, "rotarycraft_item_fuel"             , 1,   0, MT.HSLA               ,  U * 7, MT.Glass              ,  U * 2);
		OM.data(MD.RoC, "rotarycraft_item_disk"             , 1,   0, MT.HSLA               ,  U4, MT.Redstone          ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_craftpattern"     , 1,   0, MT.HSLA               ,  3* U4);
		OM.data(MD.RoC, "rotarycraft_item_grafter"          , 1,   W, MT.HSLA               ,  U * 7, ANY.Wood      ,  U2);
		
		
		OM.data(MD.RoC, "rotarycraft_item_upgrade"          , 1,   0, MT.HSLA               ,251* U24,MT.Au                 ,  U * 5, MT.Glass              , 9* U16, MT.AluminiumAlloy     ,  U * 2); // Upgrade Performance Engine
		OM.data(MD.RoC, "rotarycraft_item_upgrade"          , 1,   1, MT.HSLA               , 33* U9, MT.Redstone           ,  U * 3, MT.Au                 ,  U * 2); // Upgrade Redstone Cooling
		OM.data(MD.RoC, "rotarycraft_item_upgrade"          , 1,   2, MT.HSLA               ,  U * 9, MT.Au                 ,  U * 4, MT.InductiveAlloy     ,  U * 2, MT.TungstenAlloy      ,  U * 2); // Upgrade Magnetic Coil
		OM.data(MD.RoC, "rotarycraft_item_upgrade"          , 1,   3, MT.HSLA               ,  U * 5, MT.Redstone           ,  2* U3, ANY.W                 ,  U * 1, MT.InductiveAlloy     ,  U * 3); // Upgrade Flux Conductance
		OM.data(MD.RoC, "rotarycraft_item_upgrade"          , 1,   4, MT.HSLA               ,  U * 2, ANY.W                 ,  U * 4, MT.Bedrock_HSLA_Alloy ,  U * 1); // Upgrade Thermal Stability
		OM.data(MD.RoC, "rotarycraft_item_upgrade"          , 1,   5, MT.HSLA               , 37* U8, MT.SpringSteel        ,  U * 4, MT.Bedrock            ,  U2   ); // Upgrade Torsion Resistance
		OM.data(MD.RoC, "rotarycraft_item_upgrade"          , 1,   6, MT.HSLA               ,885* U9, MT.Bedrock            ,  U * 4, MT.Redstone           ,  U * 6, MT.Au                 ,  U * 4, MT.InductiveAlloy     ,  U * 6); // Upgrade Afterburner
		OM.data(MD.RoC, "rotarycraft_item_upgrade"          , 1,   7, MT.HSLA               ,  U *18, MT.Redstone           ,  2* U3, MT.Au                 ,  U * 8, MT.InductiveAlloy     ,  U * 2, MT.SpringSteel        ,  U * 6, ANY.W ,  U * 1, MT.SteelMagnetic      ,  U * 2); // Upgrade Resonance Stability
		OM.data(MD.RoC, "rotarycraft_item_upgrade"          , 1,   8, MT.HSLA               ,  U * 4, MT.Electrum           ,  U * 2, ANY.W                 ,  U * 2, MT.Enderium           ,  U * 1); // Flux Amplitude Upgrade
		
		
		OM.data(MD.RoC, "rotarycraft_item_engine"           , 1,   0, MT.HSLA               ,  U * 7, MT.Redstone           ,  U * 2); // DC Engine
		OM.data(MD.RoC, "rotarycraft_item_engine"           , 1,   3, MT.HSLA               ,171* U9, MT.Redstone           ,  U * 1, MT.Au                 ,  U * 2); // Gasoline Engine
		
		
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,   0, ANY.W                 ,  U * 1, ANY.Glowstone ,  U * 4, MT.Obsidian           ,  U *54); // Heat Ray Barrel
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,   1, ANY.Diamond           ,  U * 4); // Lens
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,   2, MT.HSLA               ,  U * 1, MT.Redstone           ,  2* U3, MT.Au                 ,  U * 1); // Power Module
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,   3, MT.NetherStar         ,  U * 1, MT.Redstone           ,  U * 1,ANY.Glowstone  ,  U *12, MT.Blaze              ,  U9* 2); // Heat Ray Core
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,   4, MT.HSLA               ,  U * 5, MT.Au                 ,  U * 8, MT.Redstone           ,  U * 1); // Linear Induction Motor
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,   5, MT.HSLA               ,  U * 3); // Propeller Blade
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,   6, MT.HSLA               , 51* U9); // Hub
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,   7, MT.Glass              ,  U * 3); // Mirror
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,   8, MT.HSLA               ,  U * 5, MT.Au                 ,  U * 8); // Generator
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,   9, MT.HSLA               ,  U *41, MT.Au                 ,  U *65, MT.Redstone           , 26* U3); // Railgun Accelerator
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,  10, MT.HSLA               ,  U * 9); // Turret Base
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,  11, MT.HSLA               ,  U *21, ANY.Diamond           ,  U * 1, MT.Au                 ,  U * 9, MT.Redstone           , 16* U3); // Turret Aiming Unit
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,  12, MT.HSLA               ,624* U9, ANY.W                 ,  U * 4, MT.TungstenAlloy      ,  U * 1); // Compound Turbine
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,  13, MT.HSLA               ,  U *65, MT.Bedrock            ,  U *16, ANY.Diamond           ,  U *16); // Bedrock Tension Coil
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,  14, MT.HSLA               ,  5* U4); // Chain Link
		OM.data(MD.RoC, "rotarycraft_item_misccraft"        , 1,  15, MT.Bedrock_HSLA_Alloy ,  U * 7); // Bedrock Drill
		
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,   0, MT.HSLA               , 15* U9); // Impeller
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,   1, MT.HSLA               , 87* U9); // Compressor
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,   2, MT.HSLA               ,303* U9); // Turbine
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,   3, MT.HSLA               ,  U * 5); // Diffuser
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,   4, MT.HSLA               ,  U *12, MT.Redstone           ,  U * 2, MT.Au                 ,  U * 1); // Combustor
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,   5, MT.HSLA               ,  U * 4); // Cylinder
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,   6, MT.HSLA               , 33* U8, MT.Au                 ,  U * 3, MT.Glass              , 9* U16); // Radiator
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,   7, MT.HSLA               , 13* U4, MT.Glass              ,  3* U8); // Condenser
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,   8, MT.HSLA               ,  U * 1, MT.Au                 ,  U * 4); // Gold Coil
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,   9, ANY.Wood              ,  U * 5); // Wood Gear
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,  10, ANY.Stone             ,  5* U2); // Stone Gear
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,  11, ANY.Diamond           ,  5* U8); // Diamond Gear
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,  12, MT.HSLA               ,  5* U8, MT.Bedrock            ,  U2); // Bedrock Gear
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,  13, MT.HSLA               ,  U * 6, MT.SpringSteel        ,  U * 3); // PaddlePanel
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,  14, MT.HSLA               ,  U * 3); // Shaft Core
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,  15, MT.HSLA               ,  U * 5, MT.Redstone           ,  U * 1, MT.Au                 ,  U * 1); // Ignition Unit
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,  16, ANY.Diamond           ,  U * 3); // Diamond Shaft Core
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,  17, MT.HSLA               ,192* U9, ANY.W                 ,  U * 4, MT.TungstenAlloy      ,  U * 1); // Compound Compressor
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,  18, MT.AluminiumAlloy     ,  U * 4); // Aluminium Alloy Cylinder
		OM.data(MD.RoC, "rotarycraft_item_enginecraft"      , 1,  19, MT.HSLA               ,  U * 9, MT.Redstone           ,  U * 2, MT.Au                 ,  U * 1, MT.InductiveAlloy     ,  U * 3); // HT Combustor
		
		OM.data(MD.RoC, "rotarycraft_item_shaftcraft"       , 1,   3, MT.HSLA               ,  U * 1); // Mount
		OM.data(MD.RoC, "rotarycraft_item_shaftcraft"       , 1,   4, MT.HSLA               , 15* U9); // Gear
		OM.data(MD.RoC, "rotarycraft_item_shaftcraft"       , 1,   5, MT.HSLA               , 48* U9); // 2x Gear
		OM.data(MD.RoC, "rotarycraft_item_shaftcraft"       , 1,   6, MT.HSLA               ,114* U9); // 4x Gear
		OM.data(MD.RoC, "rotarycraft_item_shaftcraft"       , 1,   7, MT.HSLA               ,180* U9); // 8x Gear
		OM.data(MD.RoC, "rotarycraft_item_shaftcraft"       , 1,   8, MT.HSLA               ,246* U9); // 16x Gear
		OM.data(MD.RoC, "rotarycraft_item_shaftcraft"       , 1,   9, MT.HSLA               ,     U9); // HSLA Scrap
		OM.data(MD.RoC, "rotarycraft_item_shaftcraft"       , 1,  10, ANY.Fe                ,  U * 1); // Iron Scrap
		OM.data(MD.RoC, "rotarycraft_item_shaftcraft"       , 1,  11, MT.HSLA               , 15* U9, ANY.Wood              ,  U * 8); // Wood Flywheel Core
		OM.data(MD.RoC, "rotarycraft_item_shaftcraft"       , 1,  12, MT.HSLA               , 15* U9, ANY.Stone             ,  U * 8); // Stone Flywheel Core
		OM.data(MD.RoC, "rotarycraft_item_shaftcraft"       , 1,  13, MT.HSLA               , 15* U9, ANY.Fe                ,  U * 8); // Iron Flywheel Core
		OM.data(MD.RoC, "rotarycraft_item_shaftcraft"       , 1,  14, MT.HSLA               , 15* U9, MT.Au                 ,  U * 8); // Gold Flywheel Core
		OM.data(MD.RoC, "rotarycraft_item_shaftcraft"       , 1,  15, MT.HSLA               , 33* U9); // Worm Gear
		
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,   0, ANY.Wood              ,  U *11); // 2x Wood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,   1, ANY.Wood              ,  U *23); // 4x Wood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,   2, ANY.Wood              ,  U *35); // 8x Wood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,   3, ANY.Wood              ,  U *47); // 16x Wood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,   4, ANY.Stone             ,  U * 7); // 2x Stone Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,   5, ANY.Stone             ,  U *16); // 4x Stone Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,   6, ANY.Stone             ,  U *25); // 8x Stone Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,   7, ANY.Stone             ,  U *34); // 16x Stone Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,   8, ANY.Diamond           , 13* U4); // 2x Diamond Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,   9, ANY.Diamond           , 34* U4); // 4x Diamond Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,  10, ANY.Diamond           , 57* U4); // 8x Diamond Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,  11, ANY.Diamond           , 78* U4); // 16x Diamond Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,  12, MT.HSLA               ,  7* U4, MT.Bedrock            ,  U * 3); // 2x Bedrock Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,  13, MT.HSLA               , 16* U4, MT.Bedrock            ,  U * 8); // 4x Bedrock Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,  14, MT.HSLA               , 25* U4, MT.Bedrock            ,  U *13); // 8x Bedrock Gear
		OM.data(MD.RoC, "rotarycraft_item_gearunits"        , 1,  15, MT.HSLA               , 34* U4, MT.Bedrock            ,  U *18); // 16x Bedrock Gear
		
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,   1, MT.HSLA               , 15* U9); // Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,   2, MT.HSLA               , 48* U9); // 2x Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,   3, MT.HSLA               ,114* U9); // 4x Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,   4, MT.HSLA               ,180* U9); // 8x Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,   5, MT.HSLA               ,246* U9); // 16x Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,   6, MT.HSLA               ,  U * 1); // Shaft Bearing
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,   7, MT.HSLA               ,  U * 3); // Shaft Core
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  17, ANY.Wood              ,  U * 5); // Wood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  18, ANY.Wood              ,  U *11); // 2x Wood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  19, ANY.Wood              ,  U *23); // 4x Wood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  20, ANY.Wood              ,  U *35); // 8x Wood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  21, ANY.Wood              ,  U *47); // 16x Wood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  22, ANY.Wood              ,  U * 1); // Wood Bearing
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  23, ANY.Wood              ,  U * 2); // Wood Shaft Core
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  33, ANY.Stone             ,  5* U2); // Stone Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  34, ANY.Stone             ,  U * 7); // 2x Stone Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  35, ANY.Stone             ,  U *16); // 4x Stone Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  36, ANY.Stone             ,  U *25); // 8x Stone Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  37, ANY.Stone             ,  U *34); // 16x Stone Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  38, ANY.Stone             ,  U * 1); // Stone Bearing
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  39, ANY.Stone             ,  U * 3); // Stone Shaft Core
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  49, ANY.Diamond           ,     U2, MT.TungstenAlloy      ,  U8   ); // Diamond Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  50, ANY.Diamond           ,  U * 3, MT.TungstenAlloy      ,  U8* 2); // 2x Diamond Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  51, ANY.Diamond           ,  U * 8, MT.TungstenAlloy      ,  U8* 4); // 4x Diamond Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  52, ANY.Diamond           ,  U *13, MT.TungstenAlloy      ,  U8* 6); // 8x Diamond Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  53, ANY.Diamond           ,  U *18, MT.TungstenAlloy      ,  U8* 8); // 16x Diamond Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  54, ANY.Diamond           ,     U2, MT.TungstenAlloy      ,  U8   ); // Diamond Bearing
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  55, ANY.Diamond           ,  U * 3); // Diamond Shaft Core
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  64, MT.HSLA               ,     U4, MT.Bedrock            ,  U * 1); // Bedrock Shaft Unit
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  65, MT.HSLA               ,  5* U8, MT.Bedrock            ,     U2); // Bedrock Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  66, MT.HSLA               ,  7* U4, MT.Bedrock            ,  U * 3); // 2x Bedrock Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  67, MT.HSLA               , 16* U4, MT.Bedrock            ,  U * 8); // 4x Bedrock Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  68, MT.HSLA               , 25* U4, MT.Bedrock            ,  U *13); // 8x Bedrock Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  69, MT.HSLA               , 34* U4, MT.Bedrock            ,  U *18); // 16x Bedrock Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  70, MT.HSLA               ,     U , MT.Bedrock            ,  U * 8); // Bedrock Bearing
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  71, MT.HSLA               ,     U2, MT.Bedrock            ,  U * 2, MT.Bedrock_HSLA_Alloy , U * 1); // Bedrock Shaft Core
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  81, MT.Livingwood         ,  U * 5); // Livingwood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  82, MT.Livingwood         ,  U *11); // 2x Livingwood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  83, MT.Livingwood         ,  U *23); // 4x Livingwood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  84, MT.Livingwood         ,  U *35); // 8x Livingwood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  85, MT.Livingwood         ,  U *47); // 16x Livingwood Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  86, MT.Livingwood         ,  U * 1); // Livingwood Bearing
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  87, MT.Livingwood         ,  U * 3); // Livingwood Shaft Core
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  97, MT.STONES.Livingrock  ,  5* U2); // Livingrock Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  98, MT.STONES.Livingrock  ,  U * 7); // 2x Livingrock Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1,  99, MT.STONES.Livingrock  ,  U *16); // 4x Livingrock Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1, 100, MT.STONES.Livingrock  ,  U *25); // 8x Livingrock Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1, 101, MT.STONES.Livingrock  ,  U *34); // 16x Livingrock Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1, 102, MT.STONES.Livingrock  ,  U * 1); // Livingrock Bearing
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1, 103, MT.STONES.Livingrock  ,  U * 3); // Livingrock Shaft Core
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1, 113, MT.TungstenAlloy      ,  U    ); // Tungsten Alloy Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1, 114, MT.TungstenAlloy      ,  U * 2, MT.HSLA               ,  U * 2); // 2x Tungsten Alloy Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1, 115, MT.TungstenAlloy      ,  U * 4, MT.HSLA               ,  U * 6); // 4x Tungsten Alloy Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1, 116, MT.TungstenAlloy      ,  U * 6, MT.HSLA               ,  U *10); // 8x Tungsten Alloy Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1, 117, MT.TungstenAlloy      ,  U * 8, MT.HSLA               ,  U *14); // 16x Tungsten Alloy Gear
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1, 118, MT.TungstenAlloy      ,  U    , MT.HSLA               ,  U * 2); // Tungsten Alloy Bearing
		OM.data(MD.RoC, "rotarycraft_item_gearcraft"        , 1, 119, MT.TungstenAlloy      ,  U    , MT.HSLA               ,  U * 2); // Tungsten Alloy Shaft Core
		
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,   0, MT.HSLA               ,  U * 7); // Drill
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,   1, MT.HSLA               ,  U * 1, ANY.Diamond           ,  U * 3, MT.Bedrock            ,  U * 3, MT.Obsidian           ,  U *18); // Pressure Head
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,   2, MT.HSLA               ,  U *10, MT.Redstone           ,  4*U  , MT.Au                 ,  U * 1); // Radar Unit
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,   3, MT.HSLA               ,  U * 4, MT.Redstone           , 11* U3, ANY.Wood      ,  U * 8); // Sonar Unit
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,   4, MT.HSLA               ,  U * 1, MT.Redstone           ,  2* U3); // Circuit Board
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,   5, MT.HSLA               ,  U * 5, MT.Redstone           ,  2* U3, MT.Glass              ,  3* U8); // Screen
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,   6, MT.HSLA               , 51* U9); // Mixer
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,   7, MT.HSLA               , 51* U9); // Saw
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,   8, MT.HSLA               ,  U * 3); // Shaft Bearing
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,   9, MT.HSLA               ,  U8, MT.Leather           ,  U * 1); // Belt
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,  10, MT.HSLA               ,  U4); // Ball Bearing
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,  11, MT.HSLA               ,  U *12); // Brake Disk
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,  12, MT.HSLA               ,  U *33); // Tension Coil
		OM.data(MD.RoC, "rotarycraft_item_borecraft"        , 1,  15, MT.HSLA               ,  U4, MT.Bedrock           ,  U * 1); // Bedrock Shaft Unit
		
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,   0/* Bedrock Breaker              */  , MT.HSLA               ,  U * 4, ANY.W ,  U * 2, ANY.Diamond           ,  U * 2, MT.Obsidian           ,  U * 9);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,   4/* Bevel Gears                  */  , MT.HSLA               , 29*U12);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,   6/* Shaft Junction               */  , MT.HSLA               , 29* U6);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,   7/* Fermenter                    */  , MT.HSLA               , 51* U9);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,   8/* Floodlight                   */  , MT.HSLA               ,  U * 2, MT.Obsidian           ,  U * 9, ANY.Glowstone ,  U * 4, MT.Glass              ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,   9/* Clutch                       */  , MT.HSLA               ,  U * 2, MT.Redstone           ,  U * 2);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  10/* Dynamometer                  */  , MT.HSLA               ,  7* U4, MT.Redstone           ,  1* U6, MT.Glass              , 3* U32);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  11/* Grinder                      */  , MT.HSLA               ,  U *18);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  12/* Heat Ray                     */  , MT.HSLA               ,  U * 3, MT.NetherStar         ,  U * 1, ANY.W ,  U * 1, ANY.Diamond           ,  U * 4, ANY.Glowstone ,  U *16, MT.Blaze              ,  U9* 2, MT.Redstone           ,  5* U3, MT.Au                 ,  U * 1, MT.Obsidian           ,  U *81);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  13/* Lubricant Hose               */  , ANY.Wood      ,  3* U8, MT.Glass              , 3* U16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  14/* Boring Machine               */  , MT.HSLA               ,174* U9, MT.Redstone           ,  2* U3);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  15/* Light Bridge                 */  , MT.HSLA               ,  U * 4, ANY.Diamond           ,  U * 1, MT.Au                 ,  U * 2, MT.Glass              ,  U * 2);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  16/* Pump                         */  , MT.HSLA               ,163* U24,MT.Glass              ,15* U16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  17/* Liquid Pipe                  */  , MT.HSLA               ,  3* U8, MT.Glass              , 3* U16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  18/* Reservoir                    */  , MT.HSLA               ,  U * 7);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  19/* Aerosolizer                  */  , MT.HSLA               ,303* U9);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  20/* Extractor                    */  , MT.HSLA               ,114* U9, ANY.Stone     ,  U * 1, MT.Netherrack         ,  U * 1, ANY.Wood      ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  21/* Pulse jet Furnace            */  , MT.HSLA               ,721* U24,MT.Redstone           ,  U * 2, MT.Au                 ,  U * 1, MT.Obsidian           ,  U *18, MT.Glass              , 3* U16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  22/* Compactor                    */  , MT.HSLA               ,318* U9, ANY.Diamond           ,  U *12, MT.Bedrock            ,  U *12, MT.Obsidian           ,  U *72);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  23/* Fan                          */  , MT.HSLA               , 43* U9, ANY.Wood      ,  U * 5);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  24/* Fuel Line                    */  , MT.Obsidian           , 27* U8, MT.Glass              , 3* U16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  25/* Fractation Unit              */  , MT.HSLA               , 60* U9, MT.Au                 ,  U * 6, MT.Obsidian           , 27* U8, MT.Glass              , 3* U16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  26/* Ground Penetrating Radar     */  , MT.HSLA               ,  U *22, MT.Au                 ,  U * 1, MT.Redstone           , 16* U3, MT.Glass              ,  3* U8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  27/* Obsidian Factory             */  , MT.HSLA               ,113* U12,MT.Glass              ,  3* U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  28/* Pile Driver                  */  , MT.HSLA               ,312* U9, ANY.Fe        ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  29/* Item Vacuum                  */  , MT.HSLA               , 51* U9, ANY.Wood      ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  30/* Firework Display             */  , MT.HSLA               ,  U * 6, MT.Redstone           ,  U * 2, MT.EnderEye           ,  U * 1, ANY.Stone     ,  U * 7);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  31/* Sprinkler                    */  , MT.HSLA               ,49* U96, MT.Glass              , 3* U64);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  32/* Wood Cutter                  */  , MT.HSLA               ,195* U9);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  33/* Spawner Controller           */  , MT.HSLA               ,  U * 3, MT.Redstone           ,  2* U3, MT.Au                 ,  U * 1, ANY.Glowstone ,  U * 8, MT.Obsidian           ,  U *18);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  34/* Player Detector              */  , MT.HSLA               ,  U *11, MT.Redstone           ,  U * 4, MT.Au                 ,  U * 2, MT.Obsidian           ,  U *36, MT.Lapis              ,  U * 2);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  35/* Heater                       */  , MT.HSLA               ,  U *18, ANY.W ,  U * 1, MT.Redstone           ,  U * 2, MT.Au                 ,  U * 1, ANY.Fe        ,  3* U8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  36/* Bait Box                     */  , MT.HSLA               ,  U * 5, ANY.Fe        ,  U * 3);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  37/* Auto Breeder                 */  , MT.HSLA               ,  U * 5);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  38/* Engine Control Unit          */  , MT.HSLA               ,  U * 1, MT.Redstone           ,  5* U3, MT.Au                 ,  U2);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  39/* Smoke Detector               */  , MT.Redstone           ,  U * 4, ANY.Wood      ,  U * 8, ANY.Stone     ,  U2);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  40/* Mob Radar                    */  , MT.HSLA               ,210* U9, MT.Redstone           , 16* U3, MT.Glass              ,  3* U8, MT.Au                 ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  41/* Coil Winder                  */  , MT.HSLA               ,102* U9);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  43/* TNT Cannon                   */  , MT.HSLA               , 81* U9, MT.Redstone           , 31* U3, ANY.Wood      ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  44/* Sonic Weapon                 */  , MT.HSLA               ,195* U9, MT.Redstone           , 44* U3, ANY.Wood      ,  U *32);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  45/* Blast Furnace Brick          */  , MT.Redstone           ,  U * 1, ANY.Stone     ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  46/* Force Field                  */  , MT.HSLA               ,  U * 2, MT.NetherStar         ,  U * 1, ANY.Diamond           ,  U * 3, MT.Au                 ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  47/* Music Box                    */  , MT.HSLA               ,  U4, MT.Redstone          , 5* U12, ANY.Wood      ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  48/* Liquid Spiller               */  , MT.HSLA               , 35* U8, MT.Glass              , 3* U16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  49/* Chunk Loader                 */  , MT.HSLA               ,  U *13, MT.NetherStar         ,  U * 2, MT.Bedrock            ,  U *20);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  50/* Mob Harvester                */  , MT.HSLA               ,  U * 9, MT.Redstone           ,  U * 1, MT.Au                 ,  U * 1, MT.EnderPearl         ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  52/* Projector                    */  , MT.HSLA               ,  U * 7, MT.Redstone           ,  2* U3, ANY.Glowstone ,  U * 4, MT.Glass              ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  53/* Railgun                      */  , MT.HSLA               ,  U *71, MT.Au                 ,  U *74, MT.Redstone           , 42* U3, ANY.Diamond           ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  54/* Silver Iodide Cannon         */  , MT.HSLA               ,  U * 7, MT.Redstone           ,  2* U3);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  55/* Item Refresher               */  , MT.HSLA               ,  U * 4, MT.Lapis              ,  U * 4, MT.EnderPearl         ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  56/* Freeze Gun                   */  , MT.HSLA               ,156* U9, MT.Ice                ,  U * 2);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  57/* Cave Scanner                 */  , MT.HSLA               ,  U *12, MT.Redstone           , 13* U3, ANY.Wood      ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  58/* Scaleable Chest              */  , MT.HSLA               ,  U * 8, ANY.Wood      ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  59/* Firestarter                  */  , MT.HSLA               ,  U *12, MT.Redstone           ,  U * 2, MT.Au                 ,  U * 5, MT.Obsidian           ,  U *36);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  60/* Magnetizing Unit             */  , MT.HSLA               ,  U * 7, MT.Redstone           ,  U * 1, MT.Au                 ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  61/* Containment Field            */  , MT.HSLA               ,  U * 2, MT.NetherStar         ,  U * 1, ANY.Diamond           ,  U * 3, MT.Au                 ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  62/* CCTV Screen                  */  , MT.HSLA               ,  U *13, MT.Redstone           ,  4* U3, MT.Glass              ,  3* U8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  63/* Steel Purifier               */  , MT.HSLA               ,  U * 7, MT.Redstone           ,  U * 1, ANY.Fe        ,  3* U8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  64/* Laser Gun                    */  , MT.HSLA               ,327* U9, MT.NetherStar         ,  U * 1, ANY.W ,  U * 1, ANY.Diamond           ,  U * 5, MT.Redstone           , 21* U3, MT.Au                 ,  U *10, ANY.Glowstone ,  U *16, MT.Blaze              ,  U9* 2, MT.Obsidian           ,  U *54);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  65/* Item Cannon                  */  , MT.HSLA               ,219* U9, ANY.Wood      ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  66/* Landmine                     */  , MT.HSLA               ,  U * 7, MT.Redstone           ,  U * 3, MT.Au                 ,  U * 2);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  67/* Friction Heater              */  , MT.HSLA               ,  U * 7);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  68/* Block Cannon                 */  , MT.HSLA               ,141* U9, MT.Redstone           ,  4* U3, ANY.Wood      ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  69/* Bucket Filler                */  , MT.HSLA               , 44* U8, MT.Glass              ,12* U16, ANY.Wood      ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  70/* Solar Mirror                 */  , MT.HSLA               , 42* U9, MT.Redstone           ,  2* U3, MT.Glass              ,  U * 3);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  71/* Solar Mirror                 */  , MT.HSLA               , 45* U8, MT.Glass              , 9* U16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  72/* Aerial Camera                */  , MT.HSLA               ,  U * 7, MT.Redstone           ,  5* U3, MT.Glass              ,  3* U8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  73/* Self Detruct Mechanism       */  , MT.HSLA               ,  U * 6, MT.Redstone           ,  2* U3);
//      OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  74/* Cooling Fin                  */  ); // yep no value, too different Recipes, which have nothing in common
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  75/* Worktable                    */  , MT.HSLA               ,  U * 2, MT.Redstone           ,  U * 1, ANY.Wood      ,  U * 4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  78/* Display Screen               */  , MT.HSLA               ,  U * 6, MT.Redstone           ,  2* U3, ANY.Si    ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  79/* Bright Lamp                  */  , MT.HSLA               ,  U * 4, ANY.Glowstone ,  U * 4, MT.Glass              ,  U * 4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  80/* EMP Machine                  */  , MT.HSLA               ,  U * 9, MT.NetherStar         ,  U * 1, ANY.Diamond           ,  U * 9, MT.Au                 ,  U *16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  81/* Block Ram                    */  , MT.HSLA               ,102* U9, MT.Bedrock_HSLA_Alloy ,  U * 1, ANY.Stone     ,  U * 4, ANY.Wood      ,  U * 3);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  82/* Beam Mirror                  */  , MT.HSLA               ,  U * 2, MT.Glass              ,  U * 3);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  83/* Multidirectional Clutch      */  , MT.HSLA               ,102* U9, MT.Redstone           ,  U * 2);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  84/* Terraformer                  */  , MT.HSLA               ,  U *15, MT.Redstone           ,  4* U3, MT.Glass              ,  3* U8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  85/* Sorting Machine              */  , MT.HSLA               ,  U * 5, MT.Redstone           ,  2* U3, ANY.Fe        ,  U * 5, ANY.Wood      ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  86/* Fuel Enchancer               */  , MT.HSLA               , 51* U9, MT.Au                 ,  U * 2, MT.Glass              ,  6* U8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  87/* Arrow Gun                    */  , MT.HSLA               ,  U * 8, ANY.Stone     ,  U * 7, MT.Redstone           ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  88/* Friction Boiler              */  , MT.HSLA               ,145* U24,MT.Glass              ,35* U16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  89/* Steam Turbine                */  , MT.HSLA               ,348* U9, ANY.Diamond           ,  U * 3, MT.Glass              ,  U * 2);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  90/* Fertilizer                   */  , MT.HSLA               ,65* U12, ANY.Wood      ,  U * 8, MT.Glass              , 6* U16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  91/* Rock Melter                  */  , MT.HSLA               ,141* U9);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  92/* Electric Generator           */  , MT.HSLA               ,150* U9, MT.Au                 ,  U * 9);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  93/* Electric Motor               */  , MT.HSLA               ,  U * 8, ANY.Diamond           ,  U * 3, MT.Au                 ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  94/* Valve Pipe                   */  , MT.HSLA               ,  U * 1, MT.Redstone           ,  9* U2, MT.Glass              ,  3* U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  95/* Bypass Pipe                  */  , MT.Sand               ,  6* U4, MT.Glass              ,  3* U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  96/* Separation Pipe              */  , MT.HSLA               ,  U * 1, MT.Lapis              ,  9* U2, MT.Glass              ,  3* U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  97/* Dew Point Aggregator         */  , MT.HSLA               , 69* U9, MT.Glass              ,  6* U8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  98/* Air Pressure Gun             */  , MT.HSLA               ,105* U9, MT.Redstone           , 11* U3, ANY.Wood      ,  U * 8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1,  99/* Sonic Borer                  */  , MT.HSLA               ,361* U24,ANY.Fe        ,  3* U8, MT.Glass              , 3* U16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 100/* Fuel Powered Engine          */  , MT.HSLA               ,  U *40, ANY.W ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 101/* Filling Station              */  , MT.HSLA               , 73* U6, MT.Glass              ,  3* U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 102/* Belt Hub                     */  , MT.HSLA               , 41* U6);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 103/* Van De Graaff Generator      */  , MT.HSLA               ,515* U24,MT.Leather            ,  U * 1, MT.Glass              ,  3* U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 104/* Defoliation Machine          */  , MT.HSLA               ,163* U24, MT.Glass             , 9* U16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 105/* Lava Smeltery                */  , MT.HSLA               ,  U *18, ANY.Stone     ,  U *24);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 106/* Liquid Distillery            */  , MT.HSLA               , 87* U9,ANY.Fe     ,  U * 2, MT.Glass              ,  3* U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 107/* Suction Pipe                 */  , MT.NetherBrick        ,  6* U4, MT.Glass              ,  3* U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 108/* Rotational Dynamo            */  , MT.HSLA               , 57* U9, MT.Ag                 ,  U * 1, MT.Redstone           ,  U * 3);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 109/* Magnetostatic Engine         */  , MT.HSLA               ,  U * 2, ANY.Diamond           ,  U * 3, MT.Ag                 ,  U * 2, MT.Pb                 ,  U * 2, MT.Cu                 ,  U * 1, MT.Au                 ,  U * 1, MT.Redstone           ,  U * 2);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 110/* Fluid Crystallizer           */  , MT.HSLA               , 60* U9);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 111/* Shaft Power Bus Controller   */  , MT.HSLA               ,  U *17, MT.Redstone           ,  2* U3);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 112/* Shaft Power Bus              */  , MT.HSLA               ,65* U16, MT.Leather            ,  U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 113/* Particle Display             */  , MT.HSLA               ,  7* U6, MT.Redstone           , 5* U12, ANY.Stone     ,  7* U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 114/* Ground Sprinkler             */  , MT.HSLA               , 42* U9, MT.Glass              ,  3* U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 115/* Grindstone                   */  , MT.HSLA               ,  U * 7, ANY.Stone     ,  U * 1);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 116/* Pneumatic Item Pump          */  , MT.HSLA               ,101* U192,MT.Glass             ,3* U128);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 118/* Refrigeration Unit           */  , MT.HSLA               ,204* U9, MT.Glass              ,  3* U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 119/* Fluid Compression Chamber    */  , MT.HSLA               ,141* U9);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 120/* AutoCrafting Unit            */  , MT.HSLA               ,  U * 8, MT.Redstone           ,  2* U3, ANY.Wood      ,  U * 4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 121/* Composter                    */  , MT.HSLA               ,  U * 3);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 122/* Anti-Air Gun                 */  , MT.HSLA               ,521* U12,ANY.Diamond           ,  U * 1, MT.Au                 ,  U * 9, MT.Redstone           , 16* U3, MT.Glass              ,  3* U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 123/* Pipe Pump                    */  , MT.HSLA               ,101* U12,MT.Glass              ,  3* U4);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 124/* Chain Drive                  */  , MT.HSLA               , 23* U6);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 125/* Centrifuge                   */  , MT.HSLA               ,168* U9, MT.Glass              ,  3* U8);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 126/* Bedrock Pipe                 */  , MT.Bedrock_HSLA_Alloy ,  3* U8, MT.Glass              , 3* U16);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 127/* Drying Bed                   */  , MT.HSLA               ,  U * 7);
		OM.data(MD.RoC, "rotarycraft_item_machine"  , 1, 128/* Liquefaction Machine         */  , MT.HSLA               , 96* U9, MT.Glass              ,  3* U4);
		
		
		OM.data(MD.BC, "woodenGearItem"                     , 1,   0, ANY.Wood              ,  U * 2);
		OM.data(MD.BC, "stoneGearItem"                      , 1,   0, ANY.Stone             ,  U * 4);
		OM.data(MD.BC, "ironGearItem"                       , 1,   0, ANY.Fe                ,  U * 4);
		OM.data(MD.BC, "goldGearItem"                       , 1,   0, MT.Au                 ,  U * 4);
		OM.data(MD.BC, "diamondGearItem"                    , 1,   0, ANY.Diamond           ,  U * 4);
		
		OM.data(MD.BC_FACTORY, "tankBlock"                  , 1,   W, MT.Glass              ,  U * 8);
		
		
		OM.data(MD.OB, "tank"                               , 1,   W, MT.Obsidian           ,  U *18, MT.Glass              ,15* U16);
		
		
		OM.data(MD.EIO, "blockTank"                         , 1,   0, ANY.Fe                , 11* U2, MT.Glass              ,  U * 1);
		OM.data(MD.EIO, "blockTank"                         , 1,   1, MT.ObsidianSteel          , 44* U3);
		OM.data(MD.EIO, "blockDarkIronBars"                 , 1,   W, MT.ObsidianSteel          ,  3* U8);
		
		
		OM.data(MD.FR, "gearTin"                            , 1,   0, MT.Sn                 ,  U * 4);
		OM.data(MD.FR, "gearCopper"                         , 1,   0, ANY.Cu                ,  U * 4);
		OM.data(MD.FR, "gearBronze"                         , 1,   0, MT.Bronze             ,  U * 4);
		
		
		
		OM.data(MD.FRMB, "miscResources"                    , 1,  18, MT.InfusedAir         ,  U4);
		OM.data(MD.FRMB, "miscResources"                    , 1,  19, MT.InfusedWater       ,  U4);
		OM.data(MD.FRMB, "miscResources"                    , 1,  20, MT.InfusedFire        ,  U4);
		OM.data(MD.FRMB, "miscResources"                    , 1,  21, MT.InfusedEarth       ,  U4);
		OM.data(MD.FRMB, "miscResources"                    , 1,  22, MT.InfusedOrder       ,  U4);
		OM.data(MD.FRMB, "miscResources"                    , 1,  23, MT.InfusedEntropy     ,  U4);
		
		
		OM.data(MD.FUNK, "frame"                            , 1,   W, ANY.Steel             ,  U * 1);
		OM.data(MD.FUNK, "frame2"                           , 1,   W, ANY.Steel             ,  U * 1);
		OM.data(MD.FUNK, "frame3"                           , 1,   W, ANY.Steel             ,  U * 1);
		OM.data(MD.FUNK, "frame4"                           , 1,   W, ANY.Steel             ,  U * 1);
		
		
		OM.data(MD.TE, "material"                           , 1,   0, ANY.Fe                ,  U * 2, MT.Redstone           ,  U * 1, MT.Glass          ,  U * 2);
		OM.data(MD.TE, "material"                           , 1,   1, MT.Au                 ,  U * 1, MT.Redstone           ,  U * 2);
		OM.data(MD.TE, "material"                           , 1,   2, MT.Ag                 ,  U * 1, MT.Redstone           ,  U * 2);
		OM.data(MD.TE, "material"                           , 1,   3, MT.Electrum           ,  U * 1, MT.Redstone           ,  U * 2);
		
		
		if (MD.IE.mLoaded) {
		OM.data(MD.TE_FOUNDATION, "material"                , 1,  12, ANY.Fe                ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1,  13, MT.Au                 ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 128, ANY.Cu                ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 129, MT.Sn                 ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 130, MT.Ag                 ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 131, MT.Pb                 ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 132, MT.Ni                 ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 133, MT.Pt                 ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 134, MT.Mithril            ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 135, MT.Electrum           ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 136, MT.Invar              ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 137, MT.Bronze             ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 138, MT.Signalum           ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 139, MT.Lumium             ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 140, MT.Enderium           ,  U * 4);
		} else {
		OM.data(MD.TE_FOUNDATION, "material"                , 1,  12, ANY.Fe                ,  U * 5);
		OM.data(MD.TE_FOUNDATION, "material"                , 1,  13, ANY.Fe                ,  U * 1, MT.Au                 ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 128, ANY.Fe                ,  U * 1, ANY.Cu                ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 129, ANY.Fe                ,  U * 1, MT.Sn                 ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 130, ANY.Fe                ,  U * 1, MT.Ag                 ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 131, ANY.Fe                ,  U * 1, MT.Pb                 ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 132, ANY.Fe                ,  U * 1, MT.Ni                 ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 133, ANY.Fe                ,  U * 1, MT.Pt                 ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 134, ANY.Fe                ,  U * 1, MT.Mithril            ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 135, ANY.Fe                ,  U * 1, MT.Electrum           ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 136, ANY.Fe                ,  U * 1, MT.Invar              ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 137, ANY.Fe                ,  U * 1, MT.Bronze             ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 138, ANY.Fe                ,  U * 1, MT.Signalum           ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 139, ANY.Fe                ,  U * 1, MT.Lumium             ,  U * 4);
		OM.data(MD.TE_FOUNDATION, "material"                , 1, 140, ANY.Fe                ,  U * 1, MT.Enderium           ,  U * 4);
		}
		
		
		OM.data(MD.ExU, "drum"                              , 1,   0, ANY.Fe                ,  U *17);
		OM.data(MD.ExU, "drum"                              , 1,   1, ANY.Fe                ,  U * 7, MT.Au                 ,  U * 4, MT.Bedrockium         , U * 6);
		OM.data(MD.ExU, "spike_base"                        , 1,   0, ANY.Fe                ,  18*U4);
		OM.data(MD.ExU, "spike_base_gold"                   , 1,   0, MT.Au                 ,  27*U4);
		OM.data(MD.ExU, "spike_base_diamond"                , 1,   0, ANY.Diamond           ,  15*U4, MT.Au                 , 81*U16);
		
		
		OM.data(MD.FZ, "daybarrel"                          , 1,   W, ANY.Wood              ,113* U2);
		OM.data(MD.FZ, "chainLink"                          , 1,   W, MT.DarkIron           ,  U3   , MT.Pb                 ,  U15);
		OM.data(MD.FZ, "shortChain"                         , 1,   W, MT.DarkIron           ,  U * 3, MT.Pb                 ,  3*U5);
		OM.data(MD.FZ, "darkIronChain"                      , 1,   W, MT.DarkIron           ,  U *15, MT.Pb                 ,  U * 3);
		OM.data(MD.FZ, "servo/sprocket"                     , 1,   W, MT.DarkIron           ,  U * 2, MT.Ag                 ,  U2);
		OM.data(MD.FZ, "ArtifactForge"                      , 1,   W, MT.DarkIron           ,  U *31);
		OM.data(MD.FZ, "FzBlock"                            , 1,  27, MT.DarkIron           ,  U8   , MT.Pb                 ,  U4);
		
		
		OM.data(MD.BTRS, "lock"                             , 1,   W, MT.Au                 ,  U * 5, ANY.Fe                ,  U);
		OM.data(MD.BTRS, "key"                              , 1,   W, MT.Au                 ,  29*U9);
		OM.data(MD.BTRS, "keyring"                          , 1,   W, MT.Au                 ,   8*U9);
		
		
		OM.data(MD.JABBA, "barrel"                          , 1,   W, ANY.Wood              ,129* U2);
		
		
		OM.data(MD.IE, "woodenDevice"                       , 1,   6, MT.WoodTreated        , 13* U2);
		OM.data(MD.IE, "metalDevice2"                       , 1,   7, ANY.Fe                ,  U * 8);
		OM.data(MD.IE, "metalDecoration2"                   , 1,   0, MT.Al                 ,  U * 1);
		OM.data(MD.IE, "metalDecoration2"                   , 1,   1, MT.Pb                 ,  U * 1);
		OM.data(MD.IE, "metalDecoration2"                   , 1,   2, ANY.Steel             ,  U * 1);
		OM.data(MD.IE, "metalDecoration2"                   , 1,  10, ANY.Fe                ,  U * 1);
		OM.data(MD.IE, "woodenStairs"                       , 1,   0, MT.WoodTreated        ,  2* U3);
		OM.data(MD.IE, "woodenStairs1"                      , 1,   0, MT.WoodTreated        ,  2* U3);
		OM.data(MD.IE, "woodenStairs2"                      , 1,   0, MT.WoodTreated        ,  2* U3);
		OM.data(MD.IE, "woodenDecoration"                   , 1,   1, MT.WoodTreated        ,  3* U2);
		OM.data(MD.IE, "woodenDecoration"                   , 1,   2, MT.WoodTreated        ,  U2);
		OM.data(MD.IE, "woodenDecoration"                   , 1,   4, MT.WoodTreated        ,  U * 1);
		OM.data(MD.IE, "woodenDecoration"                   , 1,   5, MT.WoodTreated        ,  5* U4);
		OM.data(MD.IE, "storageSlab"                        , 1,   0, MT.Cu                 ,  9* U2);
		OM.data(MD.IE, "storageSlab"                        , 1,   1, MT.Al                 ,  9* U2);
		OM.data(MD.IE, "storageSlab"                        , 1,   2, MT.Pb                 ,  9* U2);
		OM.data(MD.IE, "storageSlab"                        , 1,   3, MT.Ag                 ,  9* U2);
		OM.data(MD.IE, "storageSlab"                        , 1,   4, MT.Ni                 ,  9* U2);
		OM.data(MD.IE, "storageSlab"                        , 1,   5, MT.Constantan         ,  9* U2);
		OM.data(MD.IE, "storageSlab"                        , 1,   6, MT.Electrum           ,  9* U2);
		OM.data(MD.IE, "storageSlab"                        , 1,   7, MT.Steel              ,  9* U2);
		
		
		OM.data(MD.Mek, "CompressedCarbon"                  , 1,   0, MT.C                  ,  U * 1);
		OM.data(MD.Mek, "CompressedRedstone"                , 1,   0, MT.Redstone           ,  U * 1); OM.reg(OD.itemRedstone, ST.make(MD.Mek, "CompressedRedstone", 1, 0));
		OM.data(MD.Mek, "CompressedDiamond"                 , 1,   0, ANY.Diamond           ,  U * 1);
		OM.data(MD.Mek, "CompressedObsidian"                , 1,   0, MT.RefinedObsidian    ,  U * 1);
		
		
		OM.data(MD.MaCu, "tanks"                            , 1,   0, ANY.Cu                ,  U * 4, ANY.Wood              ,  U * 4, MT.Glass              ,  U * 1);
		OM.data(MD.MaCu, "tanks"                            , 1,   1, MT.Al                 ,  U * 4, MT.Glass              ,  U * 4);
		OM.data(MD.MaCu, "tanks"                            , 1,   5, MT.Al                 ,  U * 4, ANY.Stone             ,  U * 4);
		OM.data(MD.MaCu, "tanks"                            , 1,   6, MT.Ti                 ,  U * 4, ANY.Quartz            ,  U *16);
		OM.data(MD.MaCu, "tanks"                            , 1,   7, MT.Ti                 ,  U * 8, MT.Al                 ,  U *54, ANY.Fe                ,  U * 4, ANY.Quartz        ,  U *18);
		
		
		OM.data(MD.MFR, "upgrade.radius"                    , 1,   0, MT.Lapis              ,  U * 3, MT.Plastic            ,  U * 3, MT.Redstone           ,  U * 2, MT.Au             ,  U9);
		OM.data(MD.MFR, "upgrade.radius"                    , 1,   1, MT.Sn                 ,  U * 3, MT.Plastic            ,  U * 3, MT.Redstone           ,  U * 2, MT.Au             ,  U9);
		OM.data(MD.MFR, "upgrade.radius"                    , 1,   2, ANY.Fe                ,  U * 3, MT.Plastic            ,  U * 3, MT.Redstone           ,  U * 2, MT.Au             ,  U9);
		OM.data(MD.MFR, "upgrade.radius"                    , 1,   3, ANY.Cu                ,  U * 3, MT.Plastic            ,  U * 3, MT.Redstone           ,  U * 2, MT.Au             ,  U9);
		OM.data(MD.MFR, "upgrade.radius"                    , 1,   4, MT.Bronze             ,  U * 3, MT.Plastic            ,  U * 3, MT.Redstone           ,  U * 2, MT.Au             ,  U9);
		OM.data(MD.MFR, "upgrade.radius"                    , 1,   5, MT.Ag                 ,  U * 3, MT.Plastic            ,  U * 3, MT.Redstone           ,  U * 2, MT.Au             ,  U9);
		OM.data(MD.MFR, "upgrade.radius"                    , 1,   6, MT.Au                 , 28* U9, MT.Plastic            ,  U * 3, MT.Redstone           ,  U * 2);
		OM.data(MD.MFR, "upgrade.radius"                    , 1,   7, ANY.Quartz            ,  U * 3, MT.Plastic            ,  U * 3, MT.Redstone           ,  U * 2, MT.Au             ,  U9);
		OM.data(MD.MFR, "upgrade.radius"                    , 1,   8, ANY.Diamond           ,  U * 3, MT.Plastic            ,  U * 3, MT.Redstone           ,  U * 2, MT.Au             ,  U9);
		OM.data(MD.MFR, "upgrade.radius"                    , 1,   9, MT.Pt                 ,  U * 3, MT.Plastic            ,  U * 3, MT.Redstone           ,  U * 2, MT.Au             ,  U9);
		OM.data(MD.MFR, "upgrade.radius"                    , 1,  10, ANY.Emerald           ,  U * 3, MT.Plastic            ,  U * 3, MT.Redstone           ,  U * 2, MT.Au             ,  U9);
		OM.data(MD.MFR, "upgrade.radius"                    , 1,  11, ANY.Stone             ,  U * 3, MT.Plastic            ,  U * 3, MT.Redstone           ,  U * 2, MT.Au             ,  U9);
		OM.data(MD.MFR, "syringe.empty"                     , 1,   0, ANY.Fe                ,  U * 1, MT.Plastic            ,  U * 4, MT.Rubber             ,  U * 1);
		OM.data(MD.MFR, "syringe.health"                    , 1,   0, ANY.Fe                ,  U * 1, MT.Plastic            ,  U * 4, MT.Rubber             ,  U * 1);
		OM.data(MD.MFR, "syringe.growth"                    , 1,   0, ANY.Fe                ,  U * 1, MT.Plastic            ,  U * 4, MT.Rubber             ,  U * 1);
		OM.data(MD.MFR, "syringe.cure"                      , 1,   0, ANY.Fe                ,  U * 1, MT.Plastic            ,  U * 4, MT.Rubber             ,  U * 1);
		OM.data(MD.MFR, "syringe.slime"                     , 1,   0, ANY.Fe                ,  U * 1, MT.Plastic            ,  U * 4, MT.Rubber             ,  U * 1);
		OM.data(MD.MFR, "syringe.zombie"                    , 1,   0, ANY.Fe                ,  U * 1, MT.Plastic            ,  U * 4, MT.Rubber             ,  U * 1);
		OM.data(MD.MFR, "needlegun.ammo.empty"              , 1,   0, ANY.Fe                ,  U4   , MT.Plastic            ,  7* U4);
		OM.data(MD.MFR, "needlegun.ammo.fire"               , 1,   0, ANY.Fe                ,  U4   , MT.Plastic            ,  7* U4);
		OM.data(MD.MFR, "needlegun.ammo.lava"               , 1,   0, ANY.Fe                ,  U4   , MT.Plastic            ,  7* U4);
		OM.data(MD.MFR, "needlegun.ammo.pierce"             , 1,   0, ANY.Fe                ,  U4   , MT.Plastic            ,  7* U4);
		OM.data(MD.MFR, "needlegun.ammo.standard"           , 1,   0, ANY.Fe                ,  U4   , MT.Plastic            ,  7* U4);
		OM.data(MD.MFR, "needlegun.ammo.anvil"              , 1,   0, ANY.Fe                ,  U4   , MT.Plastic            ,  7* U4);
		OM.data(MD.MFR, "needlegun.ammo.sewage"             , 1,   0, ANY.Fe                ,  U4   , MT.Plastic            ,  7* U4);
		OM.data(MD.MFR, "needlegun.ammo.sludge"             , 1,   0, ANY.Fe                ,  U4   , MT.Plastic            ,  7* U4);
		OM.data(MD.MFR, "record.blank"                      , 1,   0, MT.Plastic            ,  U * 8, MT.Paper              ,  U * 1);
		OM.data(MD.MFR, "ruler"                             , 1,   0, MT.Plastic            ,  U * 2, MT.Paper              ,  U * 1);
		OM.data(MD.MFR, "straw"                             , 1,   0, MT.Plastic            ,  U * 4);
		OM.data(MD.MFR, "tank"                              , 1,   0, MT.Plastic            ,  U * 7);
		OM.data(MD.MFR, "plastic.bag"                       , 1,   0, MT.Plastic            ,  U * 2);
		OM.data(MD.MFR, "plastic.cup"                       , 1,   0, MT.Plastic            , 3* U16);
		OM.dat2(MD.MFR, "plastic.helm"                      , 1     , MT.Plastic            ,  U * 5);
		OM.dat2(MD.MFR, "plastic.chest"                     , 1     , MT.Plastic            ,  U * 8);
		OM.dat2(MD.MFR, "plastic.legs"                      , 1     , MT.Plastic            ,  U * 7);
		OM.dat2(MD.MFR, "plastic.boots"                     , 1     , MT.Plastic            ,  U * 4);
		OM.dat2(MD.MFR, "hammer"                            , 1     , MT.Plastic            ,  U * 3, ANY.Wood      ,  U * 1);
		
		
		OM.data(MD.RC, "part.gear"                          , 1,   3, MT.Sn                 ,  U * 2);
		OM.data(MD.RC, "part.gear"                          , 1,   0, MT.Sn                 ,  U * 2, MT.Au                 ,  4* U9);
		OM.data(MD.RC, "part.gear"                          , 1,   1, MT.Sn                 ,  U * 2, ANY.Fe        ,  U * 4);
		OM.data(MD.RC, "part.gear"                          , 1,   2, MT.Sn                 ,  U * 2, ANY.Steel ,  U * 4);
		OM.data(MD.RC, "anvil", 1, 0                                                                , ANY.Steel ,  U *30);
		OM.data(MD.RC, "anvil", 1, 1                                                                , ANY.Steel ,  U *25);
		OM.data(MD.RC, "anvil", 1, 2                                                                , ANY.Steel ,  U *20);
		
		
		OM.dat2(MD.RoC, "rotarycraft_item_bedsword"         , 1     , MT.Bedrock_HSLA_Alloy ,  U * 2, MT.HSLA               ,  U * 1);
		OM.dat2(MD.RoC, "rotarycraft_item_bedpick"          , 1     , MT.Bedrock_HSLA_Alloy ,  U * 3, MT.HSLA               ,  U * 2);
		OM.dat2(MD.RoC, "rotarycraft_item_bedshovel"        , 1     , MT.Bedrock_HSLA_Alloy ,  U * 1, MT.HSLA               ,  U * 2);
		OM.dat2(MD.RoC, "rotarycraft_item_bedaxe"           , 1     , MT.Bedrock_HSLA_Alloy ,  U * 3, MT.HSLA               ,  U * 2);
		OM.dat2(MD.RoC, "rotarycraft_item_bedhoe"           , 1     , MT.Bedrock_HSLA_Alloy ,  U * 2, MT.HSLA               ,  U * 2);
		OM.dat2(MD.RoC, "rotarycraft_item_bedgrafter"       , 1     , MT.Bedrock_HSLA_Alloy ,  U * 1, MT.HSLA               ,  U * 2);
		OM.dat2(MD.RoC, "rotarycraft_item_bedsaw"           , 1     , MT.Bedrock_HSLA_Alloy ,  U * 2, MT.HSLA               ,  U * 3);
		OM.dat2(MD.RoC, "rotarycraft_item_bedsickle"        , 1     , MT.Bedrock_HSLA_Alloy ,  U * 3, MT.HSLA               ,  U * 1);
		OM.dat2(MD.RoC, "rotarycraft_item_bedshears"        , 1     , MT.Bedrock_HSLA_Alloy ,  U * 2);
		OM.dat2(MD.RoC, "rotarycraft_item_bedhelm"          , 1     , MT.Bedrock_HSLA_Alloy ,  U * 5);
		OM.dat2(MD.RoC, "rotarycraft_item_bedchest"         , 1     , MT.Bedrock_HSLA_Alloy ,  U * 8);
		OM.dat2(MD.RoC, "rotarycraft_item_bedlegs"          , 1     , MT.Bedrock_HSLA_Alloy ,  U * 7);
		OM.dat2(MD.RoC, "rotarycraft_item_bedboots"         , 1     , MT.Bedrock_HSLA_Alloy ,  U * 4);
		
		
		OM.dat2(MD.EBXL, "extrabiomes.logturner"            , 1     , ANY.Wood      ,  5* U2);
		
		
		OM.data(MD.UB, "fossilPiece"                        , 1,   W, MT.Bone               ,  U * 1);
		
		
		OM.data(MD.Fossil, "skullBlock"                     , 1,   W, MT.Bone               ,  U * 5);
		OM.data(MD.Fossil, "skullLantern"                   , 1,   W, MT.Bone               ,  U * 5);
		
		
		OM.data(MD.BB, "boneShard"                          , 1,   W, MT.Bone               ,  U * 2);
		
		OM.data(MD.EtFu, "red_sandstone_slab"               , 1,   0, MT.RedSand            ,  U2* 9);
		OM.data(MD.EtFu, "red_sandstone"                    , 1,   W, MT.RedSand            ,  U * 9);
		OM.data(MD.EtFu, "rabbit_raw"                       , 1,   W, MT.MeatRaw            ,  U * 2, MT.Bone, U4);
		OM.data(MD.EtFu, "rabbit_cooked"                    , 1,   W, MT.MeatCooked         ,  U * 2, MT.Bone, U4);
		OM.data(MD.EtFu, "mutton_raw"                       , 1,   W, MT.MeatRaw            ,  U * 2, MT.Bone, U4);
		OM.data(MD.EtFu, "mutton_cooked"                    , 1,   W, MT.MeatCooked         ,  U * 2, MT.Bone, U4);
		
		OM.data(MD.GaSu, "mutton_raw"                       , 1,   W, MT.MeatRaw            ,  U * 2, MT.Bone, U4);
		OM.data(MD.GaSu, "mutton_cooked"                    , 1,   W, MT.MeatCooked         ,  U * 2, MT.Bone, U4);
		
		
		OM.dat2(MD.NePl, "ItemNetheriteSword"               , 1     , MT.NetherizedDiamond  ,  U * 2, ANY.Wood, U2);
		OM.dat2(MD.NePl, "NetheritePickaxe"                 , 1     , MT.NetherizedDiamond  ,  U * 3, ANY.Wood, U );
		OM.dat2(MD.NePl, "ItemNetheriteShovel"              , 1     , MT.NetherizedDiamond  ,  U * 1, ANY.Wood, U );
		OM.dat2(MD.NePl, "ItemNetheriteAxe"                 , 1     , MT.NetherizedDiamond  ,  U * 3, ANY.Wood, U );
		OM.dat2(MD.NePl, "ItemNetheriteHoe"                 , 1     , MT.NetherizedDiamond  ,  U * 2, ANY.Wood, U );
		OM.dat2(MD.NePl, "NetheriteHelm"                    , 1     , MT.NetherizedDiamond  ,  U * 5);
		OM.dat2(MD.NePl, "NetheriteChest"                   , 1     , MT.NetherizedDiamond  ,  U * 8);
		OM.dat2(MD.NePl, "NetheriteLegg"                    , 1     , MT.NetherizedDiamond  ,  U * 7);
		OM.dat2(MD.NePl, "NetheriteBoots"                   , 1     , MT.NetherizedDiamond  ,  U * 4);
		
		
		OM.dat2(MD.TF, "item.minotaurAxe"                   , 1     , MT.Diamond            ,  U * 4, ANY.Wood, OP.stick.mAmount * 2);
		OM.data(MD.TF, "item.armorShards"                   , 1,   0, MT.Knightmetal        ,  U9);
		OM.data(MD.TF, "item.shardCluster"                  , 1,   0, MT.Knightmetal        ,  U);
		OM.data(MD.TF, "item.knightmetalRing"               , 1,   0, MT.Knightmetal        ,  U * 4);
		OM.dat2(MD.TF, "item.chainBlock"                    , 1     , MT.Knightmetal        ,  U *16);
		OM.data(MD.TF, "tile.TFNagastone"                   , 1,   W, ANY.Stone             ,  U);
		OM.data(MD.TF, "tile.TFMazestone"                   , 1,   W, ANY.Stone             ,  U);
		
		
		OM.data(MD.ERE, "fireBloom"                         , 1,   W, MT.Blaze              ,  U72);
		OM.data(MD.ERE, "redGem"                            , 1,   W, MT.Redstone           ,  U * 8);
		OM.data(MD.ERE, "glowGemBlock"                      , 1,   W, MT.Redstone           ,  U3* 2);
		OM.data(MD.ERE, "amber"                             , 1,   W, MT.Amber              ,  U);
		OM.data(MD.ERE, "wallErebus"                        , 1,   8, MT.Amber              ,  U);
		OM.data(MD.ERE, "amberBrickStairs"                  , 1,   W, MT.Amber              ,  U);
		OM.data(MD.ERE, "door_amber"                        , 1,   W, MT.Amber              ,  U * 2);
		OM.data(MD.ERE, "materials"                         , 1,  42, MT.Amber              ,  U * 1);
		OM.data(MD.ERE, "idols"                             , 1,   1, ANY.Fe                ,  U *36);
		OM.data(MD.ERE, "idols"                             , 1,   5, ANY.Fe                ,  U *72);
		OM.data(MD.ERE, "idols"                             , 1,   2, MT.Au                 ,  U *36);
		OM.data(MD.ERE, "idols"                             , 1,   6, MT.Au                 ,  U *72);
		OM.data(MD.ERE, "idols"                             , 1,   3, MT.Jade               ,  U *36);
		OM.data(MD.ERE, "idols"                             , 1,   7, MT.Jade               ,  U *72);
		OM.data(MD.ERE, "materials"                         , 1,  56, MT.Jade               ,  U9);
		OM.data(MD.ERE, "materials"                         , 1,  27, MT.Paper              ,  U * 2);
		OM.data(MD.ERE, "materials"                         , 1,   2, MT.Bone               ,  U * 1);
		OM.data(MD.ERE, "materials"                         , 1,   0, MT.Bone               ,  U * 1);
		OM.data(MD.ERE, "materials"                         , 1,  16, MT.Bone               ,  U * 9);
		OM.data(MD.ERE, "reinExo"                           , 1,   0, MT.Bone               ,  U *36);
		
		
		OM.data(MD.BTL, "bronzeCircleBrick"                 , 1,   0, MT.Bronze             ,  U * 1);
		OM.data(MD.BTL, "silverCircleBrick"                 , 1,   0, MT.Ag                 ,  U * 1);
		OM.data(MD.BTL, "goldCircleBrick"                   , 1,   0, MT.Au                 ,  U * 1);
		OM.data(MD.BTL, "walkway"                           , 1,   W, MT.Weedwood           ,  U3* 4);
		OM.data(MD.BTL, "weedwoodPlanksButton"              , 1,   W, MT.Weedwood           ,  U * 1);
		OM.data(MD.BTL, "weedwoodPlanksPressurePlate"       , 1,   W, MT.Weedwood           ,  U * 2);
		OM.data(MD.BTL, "weedwoodSign"                      , 1,   W, MT.Weedwood           ,  U6*13);
		OM.data(MD.BTL, "weedwoodPlanksFenceGate"           , 1,   W, MT.Weedwood           ,  U * 4);
		OM.data(MD.BTL, "mossBedItem"                       , 1,   W, MT.Weedwood           ,  U * 3);
		OM.data(MD.BTL, "weedwoodTrapDoor"                  , 1,   W, MT.Weedwood           ,  U * 3);
		OM.data(MD.BTL, "weedwoodCraftingTable"             , 1,   W, MT.Weedwood           ,  U * 4);
		OM.data(MD.BTL, "weedwoodRowboat"                   , 1,   W, MT.Weedwood           ,  U * 5);
		OM.data(MD.BTL, "door_weedwood"                     , 1,   W, MT.Weedwood           ,  U * 6);
		OM.data(MD.BTL, "weedwoodChest"                     , 1,   W, MT.Weedwood           ,  U * 8);
		OM.data(MD.BTL, "weedwoodJukebox"                   , 1,   W, MT.Weedwood           ,  U * 8, MT.Valonite, U);
		
		
		OM.data(MD.CANDY, "B13"                             , 1,   0, MT.Licorice           ,  U3* 4);
		OM.data(MD.CANDY, "B39"                             , 1,   0, MT.Sugar              ,  U * 4);
		OM.data(MD.CANDY, "B35"                             , 1,   0, MT.Sugar              ,  U * 4); // Caramel
		OM.data(MD.CANDY, "I60"                             , 1,   0, MT.Sugar              ,  U9   ); // Cotton Candy
		OM.data(MD.CANDY, "B67"                             , 1,   0, MT.Sugar              ,  U9   ); // Cotton Candy
		OM.data(MD.CANDY, "B60"                             , 1,   0, MT.Honey              ,  U * 9);
		OM.data(MD.CANDY, "B95"                             , 1,   W, MT.Nougat             ,  U * 9);
		OM.data(MD.CANDY, "B18"                             , 1,   0, MT.Mint               ,  U * 4);
		OM.data(MD.CANDY, "B48"                             , 1,   0, MT.Mint               ,  U * 1);
		OM.data(MD.CANDY, "B43"                             , 1,   0, MT.Chocolate          ,  U * 1);
		OM.data(MD.CANDY, "B44"                             , 1,   0, MT.Chocolate          ,  U * 1);
		OM.data(MD.CANDY, "I27"                             , 1,   0, MT.Chocolate          ,  U * 1);
		OM.data(MD.CANDY, "B2"                              , 1,   0, MT.Barley             ,  U * 1);
		
		
		OM.data(MD.AETHER, "skyrootSignItem"                , 1,   W, MT.Skyroot            ,  U6*13);
		OM.data(MD.AETHER, "skyrootFenceGate"               , 1,   W, MT.Skyroot            ,  U * 4);
		OM.data(MD.AETHER, "skyrootBedItem"                 , 1,   W, MT.Skyroot            ,  U * 3);
		OM.data(MD.AETHER, "skyrootTrapDoor"                , 1,   W, MT.Skyroot            ,  U * 3);
		OM.data(MD.AETHER, "skyrootCraftingTable"           , 1,   W, MT.Skyroot            ,  U * 4);
		OM.data(MD.AETHER, "skyrootDoorItem"                , 1,   W, MT.Skyroot            ,  U * 6);
		OM.data(MD.AETHER, "skyrootBookshelf"               , 1,   W, MT.Skyroot            ,  U * 6, MT.Paper, U * 9);
		OM.data(MD.AETHER, "skyrootChest"                   , 1,   W, MT.Skyroot            ,  U * 8);
		
		
		OM.data(MD.HaC, "turkeyrawItem"                     , 1,   W, MT.MeatRaw            ,  U * 3, MT.Bone,  U4);
		OM.data(MD.HaC, "turkeycookedItem"                  , 1,   W, MT.MeatCooked         ,  U * 3, MT.Bone,  U4);
		OM.data(MD.HaC, "rabbitrawItem"                     , 1,   W, MT.MeatRaw            ,  U * 1, MT.Bone,  U4);
		OM.data(MD.HaC, "rabbitcookedItem"                  , 1,   W, MT.MeatCooked         ,  U * 1, MT.Bone,  U4);
		OM.data(MD.HaC, "venisonrawItem"                    , 1,   W, MT.MeatRaw            ,  U * 2, MT.Bone,  U9);
		OM.data(MD.HaC, "venisoncookedItem"                 , 1,   W, MT.MeatCooked         ,  U * 2, MT.Bone,  U9);
		
		
		OM.data(MD.HBM, "item.apple_lead"                   , 1,   0, MT.Pb                 ,  OP.nugget.mAmount * 8);
		OM.data(MD.HBM, "item.apple_lead"                   , 1,   1, MT.Pb                 ,  OP.ingot.mAmount * 8);
		OM.data(MD.HBM, "item.apple_lead"                   , 1,   2, MT.Pb                 ,  OP.blockIngot.mAmount * 8);
		OM.data(MD.HBM, "item.apple_schrabidium"            , 1,   0, MT.UNUSED.Schrabidium ,  OP.nugget.mAmount * 8);
		OM.data(MD.HBM, "item.apple_schrabidium"            , 1,   1, MT.UNUSED.Schrabidium ,  OP.ingot.mAmount * 8);
		OM.data(MD.HBM, "item.apple_schrabidium"            , 1,   2, MT.UNUSED.Schrabidium ,  OP.blockIngot.mAmount * 8);
		OM.data(MD.HBM, "item.apple_euphemium"              , 1,   0, MT.UNUSED.Euphemium   ,  OP.nugget.mAmount * 8);
		
		
		OM.data(MD.TG, "TechgunsAmmo"                       , 1,  25, ANY.Stone             ,  U * 6);
		OM.data(MD.TG, "TechgunsAmmo"                       , 1,  27, ANY.Iron              ,  U * 6);
		OM.data(MD.TG, "TechgunsAmmo"                       , 1,  30, ANY.Iron              ,  U2   , MT.Flint, U);
		OM.data(MD.TG, "TechgunsAmmo"                       , 1,  33, MT.ObsidianSteel      ,  U * 6);
		OM.data(MD.TG, "TechgunsAmmo"                       , 1,  34, MT.ObsidianSteel      ,  U * 1, ANY.Quartz,  U * 1);
		OM.data(MD.TG, "TechgunsAmmo"                       , 1,  37, MT.C                  ,  U2   , MT.Blaze,  U18);
		OM.data(MD.TG, "TechgunsAmmo"                       , 1,  61, MT.C                  ,  U * 1);
		OM.data(MD.TG, "TechgunsAmmo"                       , 1,  99, ANY.Quartz            ,  U * 3);
		OM.data(MD.TG, "TechgunsAmmo"                       , 1,  57, ANY.Cu                ,  U4);
		OM.data(MD.TG, "TechgunsAmmo"                       , 1,  86, MT.Au                 ,  U);
		
		
		OM.data(MD.AE, "item.ItemMultiMaterial"             , 1,  10, MT.CertusQuartz       ,  U2);
		OM.data(MD.AE, "item.ItemMultiMaterial"             , 1,  11, MT.NetherQuartz       ,  U2);
		OM.data(MD.AE, "item.ItemMultiMaterial"             , 1,  12, MT.Fluix              ,  U2);
		OM.data(MD.AE, "item.ItemMultiMaterial"             , 1,  13, ANY.Fe                ,  U * 9);
		OM.data(MD.AE, "item.ItemMultiMaterial"             , 1,  14, ANY.Fe                ,  U * 9);
		OM.data(MD.AE, "item.ItemMultiMaterial"             , 1,  15, ANY.Fe                ,  U * 9);
		OM.data(MD.AE, "item.ItemMultiMaterial"             , 1,  19, ANY.Fe                ,  U * 9);
		OM.data(MD.AE, "tile.BlockFluix"                    , 1,   W, MT.Fluix              ,  U * 4);
		OM.data(MD.AE, "tile.BlockQuartz"                   , 1,   W, MT.CertusQuartz       ,  U * 4);
		OM.data(MD.AE, "tile.BlockQuartzPillar"             , 1,   W, MT.CertusQuartz       ,  U * 4);
		OM.data(MD.AE, "tile.BlockQuartzChiseled"           , 1,   W, MT.CertusQuartz       ,  U * 4);
		OM.data(MD.AE, "tile.FluixStairBlock"               , 1,   W, MT.Fluix              ,  U * 6);
		OM.data(MD.AE, "tile.QuartzStairBlock"              , 1,   W, MT.CertusQuartz       ,  U * 6);
		OM.data(MD.AE, "tile.QuartzPillarStairBlock"        , 1,   W, MT.CertusQuartz       ,  U * 6);
		OM.data(MD.AE, "tile.ChiseledQuartzStairBlock"      , 1,   W, MT.CertusQuartz       ,  U * 6);
		OM.data(MD.AE, "tile.FluixSlabBlock"                , 1,   W, MT.Fluix              ,  U * 2);
		OM.data(MD.AE, "tile.QuartzSlabBlock"               , 1,   W, MT.CertusQuartz       ,  U * 2);
		OM.data(MD.AE, "tile.QuartzPillarSlabBlock"         , 1,   W, MT.CertusQuartz       ,  U * 2);
		OM.data(MD.AE, "tile.ChiseledQuartzSlabBlock"       , 1,   W, MT.CertusQuartz       ,  U * 2);
		
		
		OM.data(MD.NeLi, "QuartzWall"                       , 1,   0, MT.NetherQuartz       ,  U * 4);
		OM.data(MD.NeLi, "QuartzWall"                       , 1,   1, MT.NetherQuartz       ,  U * 4);
		OM.data(MD.NeLi, "QuartzWall"                       , 1,   2, MT.NetherQuartz       ,  U * 4);
		OM.data(MD.NeLi, "QuartzWall"                       , 1,   3, MT.VoidQuartz         ,  U * 4);
		OM.data(MD.NeLi, "QuartzWall"                       , 1,   4, MT.VoidQuartz         ,  U * 4);
		OM.data(MD.NeLi, "QuartzWall"                       , 1,   5, MT.VoidQuartz         ,  U * 4);
		OM.data(MD.NeLi, "QuartzSingleSlab"                 , 1,   0, MT.NetherQuartz       ,  U * 2);
		OM.data(MD.NeLi, "QuartzSingleSlab"                 , 1,   1, MT.NetherQuartz       ,  U * 2);
		OM.data(MD.NeLi, "QuartzSingleSlab"                 , 1,   2, MT.VoidQuartz         ,  U * 2);
		OM.data(MD.NeLi, "QuartzSingleSlab"                 , 1,   3, MT.VoidQuartz         ,  U * 2);
		OM.data(MD.NeLi, "QuartzSingleSlab"                 , 1,   4, MT.VoidQuartz         ,  U * 2);
		OM.data(MD.NeLi, "QuartzDoubleSlab"                 , 1,   0, MT.NetherQuartz       ,  U * 4);
		OM.data(MD.NeLi, "QuartzDoubleSlab"                 , 1,   1, MT.NetherQuartz       ,  U * 4);
		OM.data(MD.NeLi, "QuartzDoubleSlab"                 , 1,   2, MT.VoidQuartz         ,  U * 4);
		OM.data(MD.NeLi, "QuartzDoubleSlab"                 , 1,   3, MT.VoidQuartz         ,  U * 4);
		OM.data(MD.NeLi, "QuartzDoubleSlab"                 , 1,   4, MT.VoidQuartz         ,  U * 4);
		OM.data(MD.NeLi, "QuartzSmoothStairs"               , 1,   W, MT.NetherQuartz       ,  U * 6);
		OM.data(MD.NeLi, "QuartzBrickStairs"                , 1,   W, MT.NetherQuartz       ,  U * 6);
		OM.data(MD.NeLi, "QuartzVoidSmoothStairs"           , 1,   W, MT.VoidQuartz         ,  U * 6);
		OM.data(MD.NeLi, "QuartzVoidBrickStairs"            , 1,   W, MT.VoidQuartz         ,  U * 6);
		OM.data(MD.NeLi, "QuartzVoidStairs"                 , 1,   W, MT.VoidQuartz         ,  U * 6);
		
		
		OM.data(MD.AA, "blockMisc"                          , 1,   0, MT.BlackQuartz        ,  U * 2);
		OM.data(MD.AA, "blockMisc"                          , 1,   1, MT.BlackQuartz        ,  U * 4);
		OM.data(MD.AA, "blockMisc"                          , 1,   2, MT.BlackQuartz        ,  U * 4);
		OM.data(MD.AA, "blockPillarQuartzSlab"              , 1,   0, MT.BlackQuartz        ,  U * 1);
		OM.data(MD.AA, "blockChiseledQuartzSlab"            , 1,   0, MT.BlackQuartz        ,  U * 2);
		OM.data(MD.AA, "blockQuartzSlab"                    , 1,   0, MT.BlackQuartz        ,  U * 2);
		OM.data(MD.AA, "blockPillarQuartzStair"             , 1,   0, MT.BlackQuartz        ,  U * 2);
		OM.data(MD.AA, "blockChiseledQuartzStair"           , 1,   0, MT.BlackQuartz        ,  U * 4);
		OM.data(MD.AA, "blockQuartzStair"                   , 1,   0, MT.BlackQuartz        ,  U * 4);
		OM.data(MD.AA, "blockPillarQuartzWall"              , 1,   0, MT.BlackQuartz        ,  U * 2);
		OM.data(MD.AA, "blockChiseledQuartzWall"            , 1,   0, MT.BlackQuartz        ,  U * 4);
		OM.data(MD.AA, "blockQuartzWall"                    , 1,   0, MT.BlackQuartz        ,  U * 4);
		OM.data(MD.AA, "blockTestifiBucksWhiteWall"         , 1,   0, MT.NetherQuartz       ,  U * 4);
		OM.data(MD.AA, "blockTestifiBucksWhiteFence"        , 1,   0, MT.NetherQuartz       ,  U * 4);
		OM.data(MD.AA, "blockTestifiBucksWhiteStairs"       , 1,   0, MT.NetherQuartz       ,  U * 4);
		OM.data(MD.AA, "blockTestifiBucksWhiteSlab"         , 1,   0, MT.NetherQuartz       ,  U * 2);
		OM.data(MD.AA, "blockTestifiBucksGreenWall"         , 1,   0, MT.NetherQuartz       ,  U * 4);
		OM.data(MD.AA, "blockTestifiBucksGreenFence"        , 1,   0, MT.NetherQuartz       ,  U * 4);
		OM.data(MD.AA, "blockTestifiBucksGreenStairs"       , 1,   0, MT.NetherQuartz       ,  U * 4);
		OM.data(MD.AA, "blockTestifiBucksGreenSlab"         , 1,   0, MT.NetherQuartz       ,  U * 2);
		OM.data(MD.AA, "blockMisc"                          , 1,   6, MT.EnderPearl         ,  U * 4);
		OM.data(MD.AA, "itemMisc"                           , 1,  10, MT.Coal               ,  U8);
		OM.data(MD.AA, "itemMisc"                           , 1,  11, MT.Charcoal           ,  U8);
		
		
		OM.data(MD.HEX, "blockHexoriumMonolithRed"               , 1,   W, MT.HexoriumRed        ,  U *16);
		OM.data(MD.HEX, "blockHexoriumMonolithGreen"             , 1,   W, MT.HexoriumGreen      ,  U *16);
		OM.data(MD.HEX, "blockHexoriumMonolithBlue"              , 1,   W, MT.HexoriumBlue       ,  U *16);
		OM.data(MD.HEX, "blockHexoriumMonolithBlack"             , 1,   W, MT.HexoriumBlack      ,  U *12);
		OM.data(MD.HEX, "blockHexoriumMonolithWhite"             , 1,   W, MT.HexoriumWhite      ,  U *12);
		OM.data(MD.HEX, "blockHexoriumNetherMonolithRed"         , 1,   W, MT.HexoriumRed        ,  U *12);
		OM.data(MD.HEX, "blockHexoriumNetherMonolithGreen"       , 1,   W, MT.HexoriumGreen      ,  U *12);
		OM.data(MD.HEX, "blockHexoriumNetherMonolithBlue"        , 1,   W, MT.HexoriumBlue       ,  U *12);
		OM.data(MD.HEX, "blockHexoriumNetherMonolithBlack"       , 1,   W, MT.HexoriumBlack      ,  U *16);
		OM.data(MD.HEX, "blockHexoriumNetherMonolithWhite"       , 1,   W, MT.HexoriumWhite      ,  U *16);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithRed"      , 1,   W, MT.HexoriumRed        ,  U * 8);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithGreen"    , 1,   W, MT.HexoriumGreen      ,  U * 8);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithBlue"     , 1,   W, MT.HexoriumBlue       ,  U * 8);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithBlack"    , 1,   W, MT.HexoriumBlack      ,  U * 8);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithWhite"    , 1,   W, MT.HexoriumWhite      ,  U * 8);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithDarkGray" , 1,   W, MT.HexoriumBlack      ,  U * 6, MT.HexoriumWhite      ,  U * 2);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithGray"     , 1,   W, MT.HexoriumBlack      ,  U * 4, MT.HexoriumWhite      ,  U * 4);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithLightGray", 1,   W, MT.HexoriumBlack      ,  U * 2, MT.HexoriumWhite      ,  U * 6);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithOrange"   , 1,   W, MT.HexoriumRed        ,  U * 6, MT.HexoriumGreen      ,  U * 2);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithYellow"   , 1,   W, MT.HexoriumRed        ,  U * 4, MT.HexoriumGreen      ,  U * 4);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithLime"     , 1,   W, MT.HexoriumRed        ,  U * 2, MT.HexoriumGreen      ,  U * 6);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithTurquoise", 1,   W, MT.HexoriumGreen      ,  U * 6, MT.HexoriumBlue       ,  U * 2);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithCyan"     , 1,   W, MT.HexoriumGreen      ,  U * 4, MT.HexoriumBlue       ,  U * 4);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithSkyBlue"  , 1,   W, MT.HexoriumGreen      ,  U * 2, MT.HexoriumBlue       ,  U * 6);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithPurple"   , 1,   W, MT.HexoriumBlue       ,  U * 6, MT.HexoriumRed        ,  U * 2);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithMagenta"  , 1,   W, MT.HexoriumBlue       ,  U * 4, MT.HexoriumRed        ,  U * 4);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithPink"     , 1,   W, MT.HexoriumBlue       ,  U * 2, MT.HexoriumRed        ,  U * 6);
		OM.data(MD.HEX, "blockEnergizedHexoriumMonolithRainbow"  , 1,   W, MT.HexoriumWhite      ,  U * 2, MT.HexoriumRed        ,  U * 2, MT.HexoriumGreen      ,  U * 2, MT.HexoriumBlue       ,  U * 2);
		OM.data(MD.HEX, "blockEnergizedHexoriumRed"              , 1,   W, MT.HexoriumRed        ,  U * 8);
		OM.data(MD.HEX, "blockEnergizedHexoriumGreen"            , 1,   W, MT.HexoriumGreen      ,  U * 8);
		OM.data(MD.HEX, "blockEnergizedHexoriumBlue"             , 1,   W, MT.HexoriumBlue       ,  U * 8);
		OM.data(MD.HEX, "blockEnergizedHexoriumBlack"            , 1,   W, MT.HexoriumBlack      ,  U * 8);
		OM.data(MD.HEX, "blockEnergizedHexoriumWhite"            , 1,   W, MT.HexoriumWhite      ,  U * 8);
		OM.data(MD.HEX, "blockEnergizedHexoriumDarkGray"         , 1,   W, MT.HexoriumBlack      ,  U * 6, MT.HexoriumWhite      ,  U * 2);
		OM.data(MD.HEX, "blockEnergizedHexoriumGray"             , 1,   W, MT.HexoriumBlack      ,  U * 4, MT.HexoriumWhite      ,  U * 4);
		OM.data(MD.HEX, "blockEnergizedHexoriumLightGray"        , 1,   W, MT.HexoriumBlack      ,  U * 2, MT.HexoriumWhite      ,  U * 6);
		OM.data(MD.HEX, "blockEnergizedHexoriumOrange"           , 1,   W, MT.HexoriumRed        ,  U * 6, MT.HexoriumGreen      ,  U * 2);
		OM.data(MD.HEX, "blockEnergizedHexoriumYellow"           , 1,   W, MT.HexoriumRed        ,  U * 4, MT.HexoriumGreen      ,  U * 4);
		OM.data(MD.HEX, "blockEnergizedHexoriumLime"             , 1,   W, MT.HexoriumRed        ,  U * 2, MT.HexoriumGreen      ,  U * 6);
		OM.data(MD.HEX, "blockEnergizedHexoriumTurquoise"        , 1,   W, MT.HexoriumGreen      ,  U * 6, MT.HexoriumBlue       ,  U * 2);
		OM.data(MD.HEX, "blockEnergizedHexoriumCyan"             , 1,   W, MT.HexoriumGreen      ,  U * 4, MT.HexoriumBlue       ,  U * 4);
		OM.data(MD.HEX, "blockEnergizedHexoriumSkyBlue"          , 1,   W, MT.HexoriumGreen      ,  U * 2, MT.HexoriumBlue       ,  U * 6);
		OM.data(MD.HEX, "blockEnergizedHexoriumPurple"           , 1,   W, MT.HexoriumBlue       ,  U * 6, MT.HexoriumRed        ,  U * 2);
		OM.data(MD.HEX, "blockEnergizedHexoriumMagenta"          , 1,   W, MT.HexoriumBlue       ,  U * 4, MT.HexoriumRed        ,  U * 4);
		OM.data(MD.HEX, "blockEnergizedHexoriumPink"             , 1,   W, MT.HexoriumBlue       ,  U * 2, MT.HexoriumRed        ,  U * 6);
		OM.data(MD.HEX, "blockEnergizedHexoriumRainbow"          , 1,   W, MT.HexoriumWhite      ,  U * 2, MT.HexoriumRed        ,  U * 2, MT.HexoriumGreen      ,  U * 2, MT.HexoriumBlue       ,  U * 2);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumRed"          , 1,   W, MT.HexoriumRed        ,  U8* 8);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumGreen"        , 1,   W, MT.HexoriumGreen      ,  U8* 8);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumBlue"         , 1,   W, MT.HexoriumBlue       ,  U8* 8);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumBlack"        , 1,   W, MT.HexoriumBlack      ,  U8* 8);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumWhite"        , 1,   W, MT.HexoriumWhite      ,  U8* 8);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumDarkGray"     , 1,   W, MT.HexoriumBlack      ,  U8* 6, MT.HexoriumWhite      ,  U8* 2);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumGray"         , 1,   W, MT.HexoriumBlack      ,  U8* 4, MT.HexoriumWhite      ,  U8* 4);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumLightGray"    , 1,   W, MT.HexoriumBlack      ,  U8* 2, MT.HexoriumWhite      ,  U8* 6);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumOrange"       , 1,   W, MT.HexoriumRed        ,  U8* 6, MT.HexoriumGreen      ,  U8* 2);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumYellow"       , 1,   W, MT.HexoriumRed        ,  U8* 4, MT.HexoriumGreen      ,  U8* 4);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumLime"         , 1,   W, MT.HexoriumRed        ,  U8* 2, MT.HexoriumGreen      ,  U8* 6);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumTurquoise"    , 1,   W, MT.HexoriumGreen      ,  U8* 6, MT.HexoriumBlue       ,  U8* 2);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumCyan"         , 1,   W, MT.HexoriumGreen      ,  U8* 4, MT.HexoriumBlue       ,  U8* 4);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumSkyBlue"      , 1,   W, MT.HexoriumGreen      ,  U8* 2, MT.HexoriumBlue       ,  U8* 6);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumPurple"       , 1,   W, MT.HexoriumBlue       ,  U8* 6, MT.HexoriumRed        ,  U8* 2);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumMagenta"      , 1,   W, MT.HexoriumBlue       ,  U8* 4, MT.HexoriumRed        ,  U8* 4);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumPink"         , 1,   W, MT.HexoriumBlue       ,  U8* 2, MT.HexoriumRed        ,  U8* 6);
		OM.data(MD.HEX, "blockMiniEnergizedHexoriumRainbow"      , 1,   W, MT.HexoriumWhite      ,  U8* 2, MT.HexoriumRed        ,  U8* 2, MT.HexoriumGreen      ,  U8* 2, MT.HexoriumBlue       ,  U8* 2);
		
		
		
		
		OM.data(MD.BOTA, "quartzSlabManaHalf"               , 1,   W, MT.ManaQuartz         ,  U * 2);
		OM.data(MD.BOTA, "quartzSlabManaFull"               , 1,   W, MT.ManaQuartz         ,  U * 4);
		OM.data(MD.BOTA, "quartzTypeMana"                   , 1,   W, MT.ManaQuartz         ,  U * 4);
		OM.data(MD.BOTA, "quartzStairsMana"                 , 1,   W, MT.ManaQuartz         ,  U * 6);
		OM.data(MD.BOTA, "quartzSlabDarkHalf"               , 1,   W, MT.SmokeyQuartz       ,  U * 2);
		OM.data(MD.BOTA, "quartzSlabDarkFull"               , 1,   W, MT.SmokeyQuartz       ,  U * 4);
		OM.data(MD.BOTA, "quartzTypeDark"                   , 1,   W, MT.SmokeyQuartz       ,  U * 4);
		OM.data(MD.BOTA, "quartzStairsDark"                 , 1,   W, MT.SmokeyQuartz       ,  U * 6);
		OM.data(MD.BOTA, "quartzSlabBlazeHalf"              , 1,   W, MT.BlazeQuartz        ,  U * 2);
		OM.data(MD.BOTA, "quartzSlabBlazeFull"              , 1,   W, MT.BlazeQuartz        ,  U * 4);
		OM.data(MD.BOTA, "quartzTypeBlaze"                  , 1,   W, MT.BlazeQuartz        ,  U * 4);
		OM.data(MD.BOTA, "quartzStairsBlaze"                , 1,   W, MT.BlazeQuartz        ,  U * 6);
		OM.data(MD.BOTA, "quartzSlabLavenderHalf"           , 1,   W, MT.LavenderQuartz     ,  U * 2);
		OM.data(MD.BOTA, "quartzSlabLavenderFull"           , 1,   W, MT.LavenderQuartz     ,  U * 4);
		OM.data(MD.BOTA, "quartzTypeLavender"               , 1,   W, MT.LavenderQuartz     ,  U * 4);
		OM.data(MD.BOTA, "quartzStairsLavender"             , 1,   W, MT.LavenderQuartz     ,  U * 6);
		OM.data(MD.BOTA, "quartzSlabRedHalf"                , 1,   W, MT.RedQuartz          ,  U * 2);
		OM.data(MD.BOTA, "quartzSlabRedFull"                , 1,   W, MT.RedQuartz          ,  U * 4);
		OM.data(MD.BOTA, "quartzTypeRed"                    , 1,   W, MT.RedQuartz          ,  U * 4);
		OM.data(MD.BOTA, "quartzStairsRed"                  , 1,   W, MT.RedQuartz          ,  U * 6);
		OM.data(MD.BOTA, "quartzSlabElfHalf"                , 1,   W, MT.ElvenQuartz        ,  U * 2);
		OM.data(MD.BOTA, "quartzSlabElfFull"                , 1,   W, MT.ElvenQuartz        ,  U * 4);
		OM.data(MD.BOTA, "quartzTypeElf"                    , 1,   W, MT.ElvenQuartz        ,  U * 4);
		OM.data(MD.BOTA, "quartzStairsElf"                  , 1,   W, MT.ElvenQuartz        ,  U * 6);
		OM.data(MD.BOTA, "quartzSlabSunnyHalf"              , 1,   W, MT.SunnyQuartz        ,  U * 2);
		OM.data(MD.BOTA, "quartzSlabSunnyFull"              , 1,   W, MT.SunnyQuartz        ,  U * 4);
		OM.data(MD.BOTA, "quartzTypeSunny"                  , 1,   W, MT.SunnyQuartz        ,  U * 4);
		OM.data(MD.BOTA, "quartzStairsSunny"                , 1,   W, MT.SunnyQuartz        ,  U * 6);
		OM.data(MD.BOTA, "livingrock"                       , 1,   W, MT.STONES.Livingrock  ,  U * 1);
		OM.data(MD.BOTA, "livingrock0SlabFull"              , 1,   W, MT.STONES.Livingrock  ,  U * 1);
		OM.data(MD.BOTA, "livingrock1SlabFull"              , 1,   W, MT.STONES.Livingrock  ,  U * 1);
		OM.data(MD.BOTA, "livingrock0Wall"                  , 1,   W, MT.STONES.Livingrock  ,  U * 1);
		OM.data(MD.BOTA, "livingrock0Stairs"                , 1,   W, MT.STONES.Livingrock  ,  U2* 3);
		OM.data(MD.BOTA, "livingrock1Stairs"                , 1,   W, MT.STONES.Livingrock  ,  U2* 3);
		OM.data(MD.BOTA, "livingrock0Slab"                  , 1,   W, MT.STONES.Livingrock  ,  U2);
		OM.data(MD.BOTA, "livingrock1Slab"                  , 1,   W, MT.STONES.Livingrock  ,  U2);
		OM.dat2(MD.BOTA, "terraAxe"                         , 1     , MT.Terrasteel         ,  U * 4, OM.stack(MT.Livingwood, OP.stick.mAmount * 2), OM.stack(ANY.Glowstone, U * 4));
		OM.dat2(MD.BOTA, "terraPick"                        , 1     , MT.Terrasteel         ,  U * 4, OM.stack(MT.Livingwood, OP.stick.mAmount * 2), OM.stack(MT.STONES.Livingrock, U * 8));
		OM.dat2(MD.BOTA, "glassPick"                        , 1     , MT.Manasteel          ,  U * 1, OM.stack(MT.Livingwood, OP.stick.mAmount * 2), OM.stack(MT.Glass, U * 2));
		
		
		OM.data(MD.WTCH, "perpetualice"                     , 1,   W, MT.Ice                ,  U);
		OM.data(MD.WTCH, "icestairs"                        , 1,   W, MT.Ice                ,  3* U4);
		OM.data(MD.WTCH, "iceslab"                          , 1,   W, MT.Ice                ,  U2);
		OM.data(MD.WTCH, "shadedglass"                      , 1,   W, MT.Glass              ,  U*9);
		OM.data(MD.WTCH, "shadedglass_active"               , 1,   W, MT.Glass              ,  U*9);
		OM.data(MD.WTCH, "ingredient"                       , 1,  26, ANY.Clay              ,  U);
		OM.data(MD.WTCH, "ingredient"                       , 1,  27, MT.Ceramic            ,  U);
		
		
//      OM.data(MD.TCFM, "WandCaps"                         , 1,   0, MT.Alchemical         ,  5* U9);
		OM.data(MD.TCFM, "WandCaps"                         , 1,   1, MT.Vinteum            ,  5* U9);
		OM.data(MD.TCFM, "WandCaps"                         , 1,   2, MT.Terrasteel         ,  5* U9);
		OM.data(MD.TCFM, "WandCaps"                         , 1,   3, MT.Manasteel          ,  5* U9);
		OM.data(MD.TCFM, "WandCaps"                         , 1,   4, MT.Manasteel          ,  5* U9);
		OM.data(MD.TCFM, "WandCaps"                         , 1,   5, MT.ElvenElementium    ,  5* U9);
		OM.data(MD.TCFM, "WandCaps"                         , 1,   6, MT.ElvenElementium    ,  5* U9);
		
		
		OM.data(MD.TC, "WandCap"                            , 1,   0, ANY.Fe                ,  5* U9);
		OM.data(MD.TC, "WandCap"                            , 1,   1, MT.Au                 ,  5* U9);
		OM.data(MD.TC, "WandCap"                            , 1,   2, MT.Thaumium           ,  5* U9);
		OM.data(MD.TC, "WandCap"                            , 1,   3, ANY.Cu                ,  5* U9);
		OM.data(MD.TC, "WandCap"                            , 1,   4, MT.Ag                 ,  5* U9);
		OM.data(MD.TC, "WandCap"                            , 1,   5, MT.Ag                 ,  5* U9);
		OM.data(MD.TC, "WandCap"                            , 1,   6, MT.Thaumium           ,  5* U9);
		OM.data(MD.TC, "WandCap"                            , 1,   7, MT.VoidMetal          ,  5* U9);
		OM.data(MD.TC, "WandCap"                            , 1,   8, MT.VoidMetal          ,  5* U9);
		OM.data(MD.TC, "ItemBaubleBlanks"                   , 1,   0, MT.Au                 ,  U    );
		OM.data(MD.TC, "ItemBaubleBlanks"                   , 1,   1, MT.Au                 ,  4* U9);
		OM.data(MD.TC, "ItemBaubleBlanks"                   , 1,   2, MT.Au                 ,  U    , MT.Leather, U * 3);
		OM.data(MD.TC, "ItemBaubleBlanks"                   , 1,   3, MT.Au                 ,  4* U9, MT.InfusedAir     , U4);
		OM.data(MD.TC, "ItemBaubleBlanks"                   , 1,   4, MT.Au                 ,  4* U9, MT.InfusedEarth   , U4);
		OM.data(MD.TC, "ItemBaubleBlanks"                   , 1,   5, MT.Au                 ,  4* U9, MT.InfusedFire    , U4);
		OM.data(MD.TC, "ItemBaubleBlanks"                   , 1,   6, MT.Au                 ,  4* U9, MT.InfusedWater   , U4);
		OM.data(MD.TC, "ItemBaubleBlanks"                   , 1,   7, MT.Au                 ,  4* U9, MT.InfusedOrder   , U4);
		OM.data(MD.TC, "ItemBaubleBlanks"                   , 1,   8, MT.Au                 ,  4* U9, MT.InfusedEntropy , U4);
		OM.data(MD.TC, "blockCandle"                        , 1,   W, MT.Tallow             ,  2* U3);
		OM.data(MD.TC, "ItemZombieBrain"                    , 1,   W, MT.MeatRotten         ,  U * 1);
		OM.data(MD.TC, "ItemGolemPlacer"                    , 1,   0, MT.Wheat              ,  U * 9);
		OM.data(MD.TC, "ItemGolemPlacer"                    , 1,   1, MT.Greatwood          ,  U * 8);
		OM.data(MD.TC, "ItemGolemPlacer"                    , 1,   2, MT.Tallow             ,  U * 9);
		OM.data(MD.TC, "ItemGolemPlacer"                    , 1,   3, MT.Ceramic            ,  U * 4);
		OM.data(MD.TC, "ItemGolemPlacer"                    , 1,   4, MT.MeatRotten         ,  U * 9);
		OM.data(MD.TC, "ItemGolemPlacer"                    , 1,   5, ANY.Stone             ,  U * 1);
		OM.data(MD.TC, "ItemGolemPlacer"                    , 1,   6, ANY.Fe                ,  U * 9);
		OM.data(MD.TC, "ItemGolemPlacer"                    , 1,   7, MT.Thaumium           ,  U * 9);
		OM.data(MD.TC, "blockCrystal"                       , 1,   0, MT.InfusedAir         ,  U * 6);
		OM.data(MD.TC, "blockCrystal"                       , 1,   1, MT.InfusedFire        ,  U * 6);
		OM.data(MD.TC, "blockCrystal"                       , 1,   2, MT.InfusedWater       ,  U * 6);
		OM.data(MD.TC, "blockCrystal"                       , 1,   3, MT.InfusedEarth       ,  U * 6);
		OM.data(MD.TC, "blockCrystal"                       , 1,   4, MT.InfusedOrder       ,  U * 6);
		OM.data(MD.TC, "blockCrystal"                       , 1,   5, MT.InfusedEntropy     ,  U * 6);
		OM.data(MD.TC, "blockCrystal"                       , 1,   6, MT.InfusedAir         ,  U, MT.InfusedFire, U, MT.InfusedWater, U, MT.InfusedEarth, U, MT.InfusedOrder, U, MT.InfusedEntropy, U);
		
		
		OM.data(MD.ZTONES, "minicoal"                       , 1,   W, MT.Coal               ,  U8);
		OM.data(MD.ZTONES, "minicharcoal"                   , 1,   W, MT.Charcoal           ,  U8);
		
		
		OM.data(MD.CHSL, "tallow"                           , 1,   W, MT.Tallow             ,  U * 9);
		
		
		OM.data(MD.FM, "MeteorChips"                        , 1,   W, MT.Meteorite          ,  U * 1);
		OM.data(MD.FM, "KreknoChip"                         , 1,   W, MT.Kreknorite         ,  U * 1);
		
		
		if (ST.valid(ST.make(MD.GC_GALAXYSPACE, "dungeonglowstone", 1, 0))) {
		OM.data(MD.GC_GALAXYSPACE, "dungeonglowstone"       , 1,   0, MT.GlowstoneCeres     , U*4); OM.reg(ST.make(MD.GC_GALAXYSPACE, "dungeonglowstone", 1, 0), OD.glowstone);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   0, MT.Co                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   1, MT.Mg                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   2, MT.Ni                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   3, MT.Cu                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "futureglass"            , 1,   W, MT.Glass              ,  9*U , ANY.Steel,3*U16);
		OM.data(MD.GC_GALAXYSPACE, "futureglasses"          , 1,   W, MT.Glass              ,  9*U , ANY.Steel,3*U16);
		OM.data(MD.GC_GALAXYSPACE, "item.steelPole"         , 1,   W, ANY.Steel             ,  3*U2);
		OM.data(MD.GC_GALAXYSPACE, "item.BasicItems"        , 1,   4, MT.Dolomite           ,  6*U );
		OM.data(MD.GC_GALAXYSPACE, "item.BasicItems"        , 1,   8, MT.Fe2O3              ,  5*U );
		OM.data(MD.GC_GALAXYSPACE, "item.BasicItems"        , 1,  16, MT.OREMATS.Uraninite  ,  1*U );
		} else {
		OM.data(MD.GC_GALAXYSPACE, "ceresglowstone"         , 1,   W, MT.GlowstoneCeres     , U*4); OM.reg(ST.make(MD.GC_GALAXYSPACE, "ceresglowstone"    , 1, W), OD.glowstone);
		OM.data(MD.GC_GALAXYSPACE, "ioglowstone"            , 1,   W, MT.GlowstoneIo        , U*4); OM.reg(ST.make(MD.GC_GALAXYSPACE, "ioglowstone"       , 1, W), OD.glowstone);
		OM.data(MD.GC_GALAXYSPACE, "enceladusglowstone"     , 1,   W, MT.GlowstoneEnceladus , U*4); OM.reg(ST.make(MD.GC_GALAXYSPACE, "enceladusglowstone", 1, W), OD.glowstone);
		OM.data(MD.GC_GALAXYSPACE, "proteusglowstone"       , 1,   W, MT.GlowstoneProteus   , U*4); OM.reg(ST.make(MD.GC_GALAXYSPACE, "proteusglowstone"  , 1, W), OD.glowstone);
		OM.data(MD.GC_GALAXYSPACE, "plutoglowstone"         , 1,   W, MT.GlowstonePluto     , U*4); OM.reg(ST.make(MD.GC_GALAXYSPACE, "plutoglowstone"    , 1, W), OD.glowstone);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   0, MT.Pb                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   1, MT.Adamantite         ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   2, MT.Co                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   3, MT.Mg                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   4, MT.Mithril            ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   5, MT.Ni                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   6, MT.Oriharukon         ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   7, MT.Pt                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   8, MT.W                  ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "decometalsblock"        , 1,   9, MT.Cu                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC_GALAXYSPACE, "futureglass"            , 1,   W, MT.Glass              ,  9*U , MT.Desh, U16);
		OM.data(MD.GC_GALAXYSPACE, "futureglasses"          , 1,   W, MT.Glass              ,  9*U , MT.Desh, U16);
		}
		
		OM.data(MD.GC, "tile.gcBlockCore"                   , 1,   3, MT.Sn                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC, "tile.gcBlockCore"                   , 1,   4, MT.Sn                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC, "tile.wallGC"                        , 1,   0, MT.Sn                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC, "tile.wallGC"                        , 1,   1, MT.Sn                 ,    U4, ANY.Stone,   U4);
		OM.data(MD.GC, "tile.tinStairs1"                    , 1,   W, MT.Sn                 ,  3*U8, ANY.Stone, 3*U8);
		OM.data(MD.GC, "tile.tinStairs2"                    , 1,   W, MT.Sn                 ,  3*U8, ANY.Stone, 3*U8);
		OM.data(MD.GC, "slabGCHalf"                         , 1,   0, MT.Sn                 ,    U8, ANY.Stone,   U8);
		OM.data(MD.GC, "slabGCHalf"                         , 1,   1, MT.Sn                 ,    U8, ANY.Stone,   U8);
		
		OM.data(MD.GC, "item.oilCanisterPartial"            , 1,   W, MT.Sn                 , 19*U2, ANY.Steel, U, MT.Glass, 9*U);
		OM.data(MD.GC, "item.canister"                      , 1,   0, MT.Sn                 ,  7*U2);
		OM.data(MD.GC, "item.canister"                      , 1,   1, ANY.Cu                ,  7*U2);
		OM.data(MD.GC, "item.meteoricIronRaw"               , 1,   W, MT.MeteoricIron       ,  U * 1);
		OM.data(MD.GC, "tile.cheeseBlock"                   , 1,   W, MT.Cheese             ,  U * 8);
		OM.data(MD.GC, "item.null"                          , 1,   0, MT.MeteoricIron       ,  U3);
		OM.data(MD.GC, "item.null"                          , 1,   1, MT.MeteoricIron       ,  U3);
		OM.data(MD.GC, "item.heavyPlating"                  , 1,   0, ANY.Steel             ,  U * 1, MT.Al, U * 1, MT.Bronze, U * 1);
		OM.data(MD.GC, "item.spaceship"                     , 1,   W, ANY.Steel             ,  U *39, MT.Al, U *31, MT.Bronze, U *31);
		
		OM.data(MD.GC_PLANETS, "item.carbonFragments"       , 1,   0, MT.Charcoal           ,  U8);
		OM.data(MD.GC_PLANETS, "tile.denseIce"              , 1,   W, MT.Ice                ,  U * 4);
		OM.data(MD.GC_PLANETS, "item.null"                  , 1,   0, MT.Desh               ,  U * 1);
		OM.data(MD.GC_PLANETS, "item.null"                  , 1,   3, ANY.Steel             ,  U * 1, MT.Al, U * 1, MT.Bronze, U * 1, MT.MeteoricIron, U * 1);
		OM.data(MD.GC_PLANETS, "item.spaceshipTier2"        , 1,   0, ANY.Steel             ,  U *45, MT.Al, U *37, MT.Bronze, U *37, MT.MeteoricIron, U *18);
		OM.data(MD.GC_PLANETS, "item.spaceshipTier2"        , 1,   1, ANY.Steel             ,  U *45, MT.Al, U *37, MT.Bronze, U *37, MT.MeteoricIron, U *18);
		OM.data(MD.GC_PLANETS, "item.spaceshipTier2"        , 1,   2, ANY.Steel             ,  U *45, MT.Al, U *37, MT.Bronze, U *37, MT.MeteoricIron, U *18);
		OM.data(MD.GC_PLANETS, "item.spaceshipTier2"        , 1,   3, ANY.Steel             ,  U *45, MT.Al, U *37, MT.Bronze, U *37, MT.MeteoricIron, U *18);
		OM.data(MD.GC_PLANETS, "item.spaceshipTier2"        , 1,   4, ANY.Steel             ,  U *45, MT.Al, U *37, MT.Bronze, U *37, MT.MeteoricIron, U *18);
		OM.data(MD.GC_PLANETS, "item.spaceshipTier2"        , 1,  11, ANY.Steel             ,  U *37, MT.Al, U *29, MT.Bronze, U *29, MT.MeteoricIron, U * 6);
		OM.data(MD.GC_PLANETS, "item.spaceshipTier2"        , 1,  12, ANY.Steel             ,  U *37, MT.Al, U *29, MT.Bronze, U *29, MT.MeteoricIron, U * 6);
		OM.data(MD.GC_PLANETS, "item.spaceshipTier2"        , 1,  13, ANY.Steel             ,  U *37, MT.Al, U *29, MT.Bronze, U *29, MT.MeteoricIron, U * 6);
		OM.data(MD.GC_PLANETS, "item.spaceshipTier2"        , 1,  14, ANY.Steel             ,  U *37, MT.Al, U *29, MT.Bronze, U *29, MT.MeteoricIron, U * 6);
		OM.data(MD.GC_PLANETS, "item.itemBasicAsteroids"    , 1,   0, ANY.Steel             ,  U * 1, MT.Al, U * 1, MT.Bronze, U * 1, MT.MeteoricIron, U * 1, MT.Desh, U * 1);
		OM.data(MD.GC_PLANETS, "item.itemTier3Rocket"       , 1,   W, ANY.Steel             ,  U *37, MT.Al, U *37, MT.Bronze, U *37, MT.MeteoricIron, U *41, MT.Desh, U *33);
		
		
		OM.data(ST.mkic("ironFence", 1)                     , ANY.Fe                ,  U2);
		OM.data(ST.mkic("ironFurnace", 1)                   , ANY.Fe                ,  U * 5);
		OM.data(ST.mkic("crop", 1)                          , ANY.Wood              ,  U * 1);
		if (MD.IC2C.mLoaded) {
		OM.data(ST.mkic("smallIronDust", 1)                 , MT.Fe                 ,  U2);
		} else {
		OM.data(ST.mkic("Uran238", 1)                       , MT.U_238              ,  U * 1);
		OM.data(ST.mkic("smallUran235", 1)                  , MT.U_235              ,  U9);
		OM.data(ST.mkic("Uran235", 1)                       , MT.U_235              ,  U * 1);
		OM.data(ST.mkic("smallPlutonium", 1)                , MT.Pu                 ,  U9);
		OM.data(ST.mkic("Plutonium", 1)                     , MT.Pu                 ,  U * 1);
		}
		
		
		OM.dat2(ST.make(Items.wooden_sword, 1, 0)                   , ANY.Wood              ,  OP.toolHeadSword  .mAmount + U2);
		OM.dat2(ST.make(Items.wooden_pickaxe, 1, 0)                 , ANY.Wood              ,  OP.toolHeadPickaxe.mAmount + U );
		OM.dat2(ST.make(Items.wooden_shovel, 1, 0)                  , ANY.Wood              ,  OP.toolHeadShovel .mAmount + U );
		OM.dat2(ST.make(Items.wooden_axe, 1, 0)                     , ANY.Wood              ,  OP.toolHeadAxe    .mAmount + U );
		OM.dat2(ST.make(Items.wooden_hoe, 1, 0)                     , ANY.Wood              ,  OP.toolHeadHoe    .mAmount + U );
		OM.dat2(ST.make(Items.stone_sword, 1, 0)                    , ANY.Stone             ,  OP.toolHeadSword  .mAmount, ANY.Wood, U2);
		OM.dat2(ST.make(Items.stone_pickaxe, 1, 0)                  , ANY.Stone             ,  OP.toolHeadPickaxe.mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.stone_shovel, 1, 0)                   , ANY.Stone             ,  OP.toolHeadShovel .mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.stone_axe, 1, 0)                      , ANY.Stone             ,  OP.toolHeadAxe    .mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.stone_hoe, 1, 0)                      , ANY.Stone             ,  OP.toolHeadHoe    .mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.iron_sword, 1, 0)                     , ANY.Fe                ,  OP.toolHeadSword  .mAmount, ANY.Wood, U2);
		OM.dat2(ST.make(Items.iron_pickaxe, 1, 0)                   , ANY.Fe                ,  OP.toolHeadPickaxe.mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.iron_shovel, 1, 0)                    , ANY.Fe                ,  OP.toolHeadShovel .mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.iron_axe, 1, 0)                       , ANY.Fe                ,  OP.toolHeadAxe    .mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.iron_hoe, 1, 0)                       , ANY.Fe                ,  OP.toolHeadHoe    .mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.golden_sword, 1, 0)                   , MT.Au                 ,  OP.toolHeadSword  .mAmount, ANY.Wood, U2);
		OM.dat2(ST.make(Items.golden_pickaxe, 1, 0)                 , MT.Au                 ,  OP.toolHeadPickaxe.mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.golden_shovel, 1, 0)                  , MT.Au                 ,  OP.toolHeadShovel .mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.golden_axe, 1, 0)                     , MT.Au                 ,  OP.toolHeadAxe    .mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.golden_hoe, 1, 0)                     , MT.Au                 ,  OP.toolHeadHoe    .mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.diamond_sword, 1, 0)                  , ANY.Diamond           ,  OP.toolHeadSword  .mAmount, ANY.Wood, U2);
		OM.dat2(ST.make(Items.diamond_pickaxe, 1, 0)                , ANY.Diamond           ,  OP.toolHeadPickaxe.mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.diamond_shovel, 1, 0)                 , ANY.Diamond           ,  OP.toolHeadShovel .mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.diamond_axe, 1, 0)                    , ANY.Diamond           ,  OP.toolHeadAxe    .mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.diamond_hoe, 1, 0)                    , ANY.Diamond           ,  OP.toolHeadHoe    .mAmount, ANY.Wood, U );
		OM.dat2(ST.make(Items.leather_helmet, 1, 0)                 , MT.Leather            ,  U * 5);
		OM.dat2(ST.make(Items.leather_chestplate, 1, 0)             , MT.Leather            ,  U * 8);
		OM.dat2(ST.make(Items.leather_leggings, 1, 0)               , MT.Leather            ,  U * 7);
		OM.dat2(ST.make(Items.leather_boots, 1, 0)                  , MT.Leather            ,  U * 4);
		OM.dat2(ST.make(Items.chainmail_helmet, 1, 0)               , ANY.Steel             ,  5* U4);
		OM.dat2(ST.make(Items.chainmail_chestplate, 1, 0)           , ANY.Steel             ,  8* U4);
		OM.dat2(ST.make(Items.chainmail_leggings, 1, 0)             , ANY.Steel             ,  7* U4);
		OM.dat2(ST.make(Items.chainmail_boots, 1, 0)                , ANY.Steel             ,  4* U4);
		OM.dat2(ST.make(Items.iron_helmet, 1, 0)                    , ANY.Fe                ,  U * 5);
		OM.dat2(ST.make(Items.iron_chestplate, 1, 0)                , ANY.Fe                ,  U * 8);
		OM.dat2(ST.make(Items.iron_leggings, 1, 0)                  , ANY.Fe                ,  U * 7);
		OM.dat2(ST.make(Items.iron_boots, 1, 0)                     , ANY.Fe                ,  U * 4);
		OM.dat2(ST.make(Items.golden_helmet, 1, 0)                  , MT.Au                 ,  U * 5);
		OM.dat2(ST.make(Items.golden_chestplate, 1, 0)              , MT.Au                 ,  U * 8);
		OM.dat2(ST.make(Items.golden_leggings, 1, 0)                , MT.Au                 ,  U * 7);
		OM.dat2(ST.make(Items.golden_boots, 1, 0)                   , MT.Au                 ,  U * 4);
		OM.dat2(ST.make(Items.diamond_helmet, 1, 0)                 , ANY.Diamond           ,  U * 5);
		OM.dat2(ST.make(Items.diamond_chestplate, 1, 0)             , ANY.Diamond           ,  U * 8);
		OM.dat2(ST.make(Items.diamond_leggings, 1, 0)               , ANY.Diamond           ,  U * 7);
		OM.dat2(ST.make(Items.diamond_boots, 1, 0)                  , ANY.Diamond           ,  U * 4);
		OM.dat2(ST.make(Items.shears, 1, 0)                         , ANY.Fe                ,  U * 2);
		OM.dat2(ST.make(Items.saddle, 1, W)                         , ANY.Steel             ,  U * 2, MT.Leather, U * 6);
		OM.dat2(ST.make(Items.iron_horse_armor, 1, W)               , ANY.Fe                ,  U * 8, MT.Leather, U * 6);
		OM.dat2(ST.make(Items.golden_horse_armor, 1, W)             , MT.Au                 ,  U * 8, MT.Leather, U * 6);
		OM.dat2(ST.make(Items.diamond_horse_armor, 1, W)            , ANY.Diamond           ,  U * 8, MT.Leather, U * 6);
		OM.data(ST.make(Items.flint, 1, W)                          , MT.Flint              ,  U * 1);
		OM.data(ST.make(Blocks.ice, 1, W)                           , MT.Ice                ,  U * 1);
		OM.data(ST.make(Blocks.packed_ice, 1, W)                    , MT.Ice                ,  U * 2);
		OM.data(ST.make(Items.snowball, 1, W)                       , MT.Snow               ,  U4   );
		OM.data(ST.make(Blocks.snow, 1, W)                          , MT.Snow               ,  U * 1);
		OM.data(ST.make(Blocks.snow_layer, 1, W)                    , MT.Snow               ,     -1);
		OM.data(ST.make(Blocks.redstone_lamp, 1, W)                 , ANY.Glowstone         ,  U * 4, MT.Redstone, U * 4);
		OM.data(ST.make(Blocks.lit_redstone_lamp, 1, W)             , ANY.Glowstone         ,  U * 4, MT.Redstone, U * 4);
		OM.data(ST.make(Items.bone                      , 1, W)     , MT.Bone               ,  U * 2);
		OM.data(ST.make(Items.clay_ball                 , 1, W)     , MT.Clay               ,  U * 1);
		OM.data(ST.make(Blocks.clay                     , 1, W)     , MT.Clay               ,  U * 4);
		OM.data(ST.make(Blocks.hardened_clay            , 1, W)     , MT.Ceramic            ,  U * 4);
		OM.data(ST.make(Blocks.stained_hardened_clay    , 1, W)     , MT.Ceramic            ,  U * 4);
		OM.data(ST.make(Blocks.flower_pot               , 1, W)     , MT.Brick              ,  U * 3);
		OM.data(ST.make(Items.flower_pot                , 1, W)     , MT.Brick              ,  U * 3);
		OM.data(ST.make(Blocks.brick_block              , 1, W)     , MT.Brick              ,  U * 4);
		OM.data(ST.make(Blocks.double_stone_slab        , 1, 4)     , MT.Brick              ,  U * 4);
		OM.data(ST.make(Blocks.brick_stairs             , 1, W)     , MT.Brick              ,  U * 6);
		OM.data(ST.make(Blocks.stone_slab               , 1, 4)     , MT.Brick              ,  U * 2);
		OM.data(ST.make(Blocks.quartz_block             , 1, W)     , MT.NetherQuartz       ,  U * 4);
		OM.data(ST.make(Blocks.double_stone_slab        , 1, 7)     , MT.NetherQuartz       ,  U * 4);
		OM.data(ST.make(Blocks.quartz_stairs            , 1, W)     , MT.NetherQuartz       ,  U * 6);
		OM.data(ST.make(Blocks.stone_slab               , 1, 7)     , MT.NetherQuartz       ,  U * 2);
		OM.data(ST.make(Items.firework_charge           , 1, W)     , MT.Gunpowder          ,  U * 1);
		OM.data(ST.make(Items.fireworks                 , 1, W)     , MT.Gunpowder          ,  U * 1);
		OM.data(ST.make(Items.book, 1, W)                           , MT.Paper              ,  U * 3);
		OM.data(ST.make(Items.written_book, 1, W)                   , MT.Paper              ,  U * 3);
		OM.data(ST.make(Items.writable_book, 1, W)                  , MT.Paper              ,  U * 3);
		OM.data(ST.make(Items.enchanted_book, 1, W)                 , MT.Paper              ,  U * 3);
		OM.data(ST.make(Items.map, 1, W)                            , MT.Paper              ,  U * 8);
		OM.data(ST.make(Items.filled_map, 1, W)                     , MT.Paper              ,  U * 8);
		OM.data(ST.make(Items.golden_apple, 1, 1)                   , MT.Au                 ,  OP.blockIngot.mAmount * 8);
		OM.data(ST.make(Items.golden_apple, 1, 0)                   , MT.Au                 ,  OP.ingot.mAmount * 8);
		OM.data(ST.make(Items.golden_carrot, 1, 0)                  , MT.Au                 ,  OP.nugget.mAmount * 8);
		OM.data(ST.make(Items.speckled_melon, 1, 0)                 , MT.Au                 ,  OP.nugget.mAmount * 8);
		OM.data(ST.make(Items.bucket, 1, W)                         , ANY.Fe                ,  U * 3);
		OM.data(ST.make(Items.minecart, 1, 0)                       , ANY.Fe                ,  U * 5);
		OM.data(ST.make(Items.iron_door, 1, W)                      , ANY.Fe                ,  U * 2);
		OM.data(ST.make(Items.cauldron, 1, W)                       , ANY.Fe                ,  U * 7);
		OM.data(ST.make(Blocks.iron_bars, 1, W)                     , ANY.Fe                ,  3* U8);
		OM.data(ST.make(Blocks.light_weighted_pressure_plate, 1, W) , MT.Au                 ,  U * 2);
		OM.data(ST.make(Blocks.heavy_weighted_pressure_plate, 1, W) , ANY.Fe                ,  U * 2);
		OM.data(ST.make(Blocks.anvil, 1, 0)                         , ANY.Fe                ,  U *30);
		OM.data(ST.make(Blocks.anvil, 1, 1)                         , ANY.Fe                ,  U *25);
		OM.data(ST.make(Blocks.anvil, 1, 2)                         , ANY.Fe                ,  U *20);
		OM.data(ST.make(Blocks.hopper, 1, W)                        , ANY.Fe                ,  U * 5 , ANY.Wood, U * 5);
		OM.data(ST.make(Blocks.tripwire_hook, 1, W)                 , ANY.Fe                ,  OP.ring.mAmount * 2, ANY.Wood, U);
		OM.data(ST.make(Blocks.stained_glass        , 1, W)         , MT.Glass              ,  U*9);
		OM.data(ST.make(Blocks.glass                , 1, W)         , MT.Glass              ,  U*9);
		OM.data(ST.make(Blocks.stained_glass_pane   , 1, W)         , MT.Glass              ,  U);
		OM.data(ST.make(Blocks.glass_pane           , 1, W)         , MT.Glass              ,  U);
		OM.data(ST.make(Items.clock, 1, W)                          , MT.Au                 ,  U * 4, MT.Redstone, U);
		OM.data(ST.make(Items.compass, 1, W)                        , ANY.Fe                ,  U * 4, MT.Redstone, U);
		OM.data(ST.make(Items.leather, 1, W)                        , MT.Leather            ,  U * 1);
		OM.data(ST.make(Blocks.beacon, 1, W)                        , MT.NetherStar         ,  U * 1, OM.stack(MT.Obsidian, U *27), OM.stack(MT.Glass, U * 5));
		OM.data(ST.make(Blocks.enchanting_table, 1, W)              , ANY.Diamond           ,  U * 2, OM.stack(MT.Obsidian, U *36), OM.stack(MT.Paper, U * 3));
		OM.data(ST.make(Blocks.ender_chest, 1, W)                   , MT.EnderEye           ,  U * 1, MT.Obsidian, U *72);
		OM.data(ST.make(Blocks.bookshelf, 1, W)                     , MT.Paper              ,  U * 9, ANY.Wood, U * 6);
		OM.data(ST.make(Blocks.lever, 1, W)                         , ANY.Stone             ,  U * 1, ANY.Wood, U2);
		OM.data(ST.make(Blocks.sand, 1, 0)                          , MT.Sand               ,  U * 9);
		OM.data(ST.make(Blocks.sand, 1, 1)                          , MT.Sand               ,  U * 9, MT.Fe2O3, U);
		OM.data(ST.make(Blocks.sandstone, 1, W)                     , MT.Sand               ,  U * 9);
		OM.data(ST.make(Blocks.stone_slab, 1, 0)                    , ANY.Stone             ,  U2* 9);
		OM.data(ST.make(Blocks.stone_slab, 1, 8)                    , ANY.Stone             ,  U2* 9);
		OM.data(ST.make(Blocks.double_stone_slab, 1, 0)             , ANY.Stone             ,  U * 9);
		OM.data(ST.make(Blocks.double_stone_slab, 1, 8)             , ANY.Stone             ,  U * 9);
		OM.data(ST.make(Blocks.stone_slab, 1, 1)                    , MT.Sand               ,  U2* 9);
		OM.data(ST.make(Blocks.stone_slab, 1, 9)                    , MT.Sand               ,  U2* 9);
		OM.data(ST.make(Blocks.double_stone_slab, 1, 1)             , MT.Sand               ,  U * 9);
		OM.data(ST.make(Blocks.double_stone_slab, 1, 9)             , MT.Sand               ,  U * 9);
		OM.data(ST.make(Blocks.stone_slab, 1,  2)                   , MT.PetrifiedWood      ,  U2);
		OM.data(ST.make(Blocks.stone_slab, 1, 10)                   , MT.PetrifiedWood      ,  U2);
		OM.data(ST.make(Blocks.double_stone_slab, 1,  2)            , MT.PetrifiedWood      ,  U * 1);
		OM.data(ST.make(Blocks.double_stone_slab, 1, 10)            , MT.PetrifiedWood      ,  U * 1);
		OM.data(ST.make(Blocks.stone_slab, 1,  3)                   , ANY.Stone             ,  U2* 9);
		OM.data(ST.make(Blocks.stone_slab, 1, 11)                   , ANY.Stone             ,  U2* 9);
		OM.data(ST.make(Blocks.double_stone_slab, 1,  3)            , ANY.Stone             ,  U * 9);
		OM.data(ST.make(Blocks.double_stone_slab, 1, 11)            , ANY.Stone             ,  U * 9);
		OM.data(ST.make(Blocks.stone_slab, 1,  5)                   , ANY.Stone             ,  U2* 9);
		OM.data(ST.make(Blocks.stone_slab, 1, 13)                   , ANY.Stone             ,  U2* 9);
		OM.data(ST.make(Blocks.double_stone_slab, 1,  5)            , ANY.Stone             ,  U * 9);
		OM.data(ST.make(Blocks.double_stone_slab, 1, 13)            , ANY.Stone             ,  U * 9);
		OM.data(ST.make(Blocks.stone, 1, W)                         , ANY.Stone             ,  U * 9);
		OM.data(ST.make(Blocks.furnace, 1, W)                       , ANY.Stone             ,  U * 8);
		OM.data(ST.make(Blocks.lit_furnace, 1, W)                   , ANY.Stone             ,  U * 8);
		OM.data(ST.make(Blocks.stonebrick, 1, W)                    , ANY.Stone             ,  U * 9);
		OM.data(ST.make(Blocks.cobblestone, 1, W)                   , ANY.Stone             ,  U * 9);
		OM.data(ST.make(Blocks.mossy_cobblestone, 1, W)             , ANY.Stone             ,  U * 9);
		OM.data(ST.make(Blocks.stone_button, 1, W)                  , ANY.Stone             ,  U * 1);
		OM.data(ST.make(Blocks.stone_pressure_plate, 1, W)          , ANY.Stone             ,  U * 2);
		OM.data(ST.make(Blocks.deadbush, 1, W)                      , MT.WOODS.Dead         ,  U * 1);
		OM.data(ST.make(Blocks.tallgrass, 1, 0)                     , MT.WOODS.Dead         ,  U * 1);
		OM.data(ST.make(Blocks.ladder, 1, W)                        , ANY.Wood              ,  3 *U7);
		OM.data(ST.make(Blocks.wooden_button, 1, W)                 , ANY.Wood              ,  U * 1);
		OM.data(ST.make(Blocks.wooden_pressure_plate, 1, W)         , ANY.Wood              ,  U * 2);
		OM.data(ST.make(Blocks.fence, 1, W)                         , ANY.Wood              ,  3 *U2);
		OM.data(ST.make(Blocks.fence_gate, 1, W)                    , ANY.Wood              ,  U * 4);
		OM.data(ST.make(Blocks.trapdoor, 1, W)                      , ANY.Wood              ,  U * 3);
		OM.data(ST.make(Items.bowl, 1, W)                           , ANY.Wood              ,  U * 1);
		OM.data(ST.make(Items.sign, 1, W)                           , ANY.Wood              ,  U * 2);
		OM.data(ST.make(Items.painting, 1, W)                       , ANY.Wood              ,  U * 4);
		OM.data(ST.make(Items.item_frame, 1, W)                     , ANY.Wood              ,  U * 4);
		OM.data(ST.make(Items.boat, 1, W)                           , ANY.Wood              ,  U * 5);
		OM.data(ST.make(Items.wooden_door, 1, W)                    , ANY.Wood              ,  U * 2);
		OM.data(ST.make(Blocks.chest, 1, W)                         , ANY.Wood              ,  U * 8);                              OM.reg(OD.craftingChest, ST.make(Blocks.chest, 1, W));
		OM.data(ST.make(Blocks.trapped_chest, 1, W)                 , ANY.Wood              ,  U * 9, ANY.Fe, OP.ring.mAmount * 2); OM.reg(OD.craftingChest, ST.make(Blocks.trapped_chest, 1, W));
		OM.data(ST.make(Blocks.unlit_redstone_torch, 1, W)          , ANY.Wood              ,  U2, MT.Redstone, U);
		OM.data(ST.make(Blocks.redstone_torch, 1, W)                , ANY.Wood              ,  U2, MT.Redstone, U);
		OM.data(ST.make(Blocks.noteblock, 1, W)                     , ANY.Wood              ,  U * 8, MT.Redstone, U);
		OM.data(ST.make(Blocks.jukebox, 1, W)                       , ANY.Wood              ,  U * 8, ANY.Diamond, U);
		OM.data(ST.make(Blocks.crafting_table, 1, W)                , ANY.Wood              ,  U * 4);
		OM.data(ST.make(Blocks.piston, 1, W)                        , ANY.Stone             ,  U * 4, ANY.Wood, U * 3);
		OM.data(ST.make(Blocks.sticky_piston, 1, W)                 , ANY.Stone             ,  U * 4, ANY.Wood, U * 3);
		OM.data(ST.make(Blocks.dispenser, 1, W)                     , ANY.Stone             ,  U * 7, MT.Redstone, U);
		OM.data(ST.make(Blocks.dropper, 1, W)                       , ANY.Stone             ,  U * 7, MT.Redstone, U);
		OM.data(ST.make(Items.porkchop, 1, W)                       , MT.MeatRaw            ,  U * 2, MT.Bone, U9);
		OM.data(ST.make(Items.beef, 1, W)                           , MT.MeatRaw            ,  U * 2, MT.Bone, U9);
		OM.data(ST.make(Items.chicken, 1, W)                        , MT.MeatRaw            ,  U * 2, MT.Bone, U9);
		OM.data(ST.make(Items.rotten_flesh, 1, W)                   , MT.MeatRotten         ,  U * 2, MT.Bone, U9);
		OM.data(ST.make(Items.cooked_fished, 1, W)                  , MT.FishCooked         ,  U * 2, MT.Bone, U9);
		OM.data(ST.make(Items.cooked_porkchop, 1, W)                , MT.MeatCooked         ,  U * 2, MT.Bone, U9);
		OM.data(ST.make(Items.cooked_beef, 1, W)                    , MT.MeatCooked         ,  U * 2, MT.Bone, U9);
		OM.data(ST.make(Items.cooked_chicken, 1, W)                 , MT.MeatCooked         ,  U * 2, MT.Bone, U9);
		OM.data(ST.make(Items.fish, 1, 0)                           , MT.FishRaw            ,  U * 2, MT.Bone, U9, MT.FishOil, U * 2);
		OM.data(ST.make(Items.fish, 1, 1)                           , MT.FishRaw            ,  U * 2, MT.Bone, U9, MT.FishOil, U * 4);
		OM.data(ST.make(Items.fish, 1, 2)                           , MT.FishRaw            ,  U * 2, MT.Bone, U9, MT.FishOil, U * 1);
		OM.data(ST.make(Items.fish, 1, 3)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U * 1);
		if (MD.MaCu.mLoaded && MD.ENCHIRIDION.mLoaded) {
		OM.data(ST.make(Items.fish, 1, 4)                           , MT.FishRaw            ,  U * 2, MT.Bone, U9, MT.FishOil, U * 2, ANY.Glowstone, U72);
		OM.data(ST.make(Items.fish, 1, 5)                           , MT.FishRaw            ,  U * 1, MT.Bone, U9, MT.FishOil, U * 1, MT.Blaze, U9);
		OM.data(ST.make(Items.fish, 1, 6)                           , MT.FishRaw            ,  U2, MT.Bone, U3, MT.FishOil, U3);
		OM.data(ST.make(Items.fish, 1, 7)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2);
		OM.data(ST.make(Items.fish, 1, 8)                           , MT.FishRaw            ,  U * 4, MT.Bone, U2, MT.FishOil, U * 7);
		OM.data(ST.make(Items.fish, 1, 9)                           , MT.FishRaw            ,  U2, MT.Bone, U3, MT.FishOil, U4);
		OM.data(ST.make(Items.fish, 1,10)                           , MT.FishRaw            ,  U * 2, MT.Bone, U9, MT.FishOil, U4);
		OM.data(ST.make(Items.fish, 1,11)                           , MT.FishRaw            ,  U * 2, MT.Bone, U9, MT.FishOil, U * 4, MT.Gunpowder,  U9);
		OM.data(ST.make(Items.fish, 1,12)                           , MT.FishRaw            ,  U2, MT.Bone, U3, MT.FishOil, U40);
		OM.data(ST.make(Items.fish, 1,13)                           , MT.FishRaw            ,  U * 3, MT.Bone, U3, MT.FishOil, U * 2);
		OM.data(ST.make(Items.fish, 1,14)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U * 3);
		OM.data(ST.make(Items.fish, 1,15)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U * 2);
		OM.data(ST.make(Items.fish, 1,16)                           , MT.FishRaw            ,  U * 3, MT.Bone, U3, MT.FishOil, U * 6);
		OM.data(ST.make(Items.fish, 1,17)                           , MT.FishRaw            ,  U * 2, MT.Bone, U3, MT.FishOil, U * 6);
		OM.data(ST.make(Items.fish, 1,18)                           , MT.FishRaw            ,  U2, MT.Bone, U3, MT.FishOil, U * 1);
		OM.data(ST.make(Items.fish, 1,19)                           , MT.FishRaw            ,  U2, MT.Bone, U3, MT.FishOil, U * 1);
		OM.data(ST.make(Items.fish, 1,20)                           , MT.FishRaw            ,  U2, MT.Bone, U3, MT.FishOil, U2);
		OM.data(ST.make(Items.fish, 1,21)                           , MT.FishRaw            ,  U * 3, MT.FishOil, U * 5);
		OM.data(ST.make(Items.fish, 1,22)                           , MT.FishOil            ,  U * 5);
		OM.data(ST.make(Items.fish, 1,23)                           , MT.FishOil            ,  U * 5);
		OM.data(ST.make(Items.fish, 1,24)                           , MT.FishRaw            ,  U2, MT.Bone, U9, MT.FishOil, U * 1, MT.Au, U72);
		OM.data(ST.make(Items.fish, 1,25)                           , MT.FishRaw            ,  U2, MT.Bone, U3, MT.FishOil, U100);
		OM.data(ST.make(Items.fish, 1,26)                           , MT.FishRaw            ,  U * 3, MT.Bone, U3, MT.FishOil, U * 5);
		OM.data(ST.make(Items.fish, 1,27)                           , MT.FishRaw            ,  U2, MT.Bone, U9, MT.FishOil, U10);
		OM.data(ST.make(Items.fish, 1,28)                           , MT.FishRaw            ,  U2, MT.Bone, U9, MT.FishOil, U * 1, MT.Lapis,  U9);
		OM.data(ST.make(Items.fish, 1,29)                           , MT.FishRaw            ,  U * 3, MT.Bone, U3, MT.FishOil, U * 3);
		OM.data(ST.make(Items.fish, 1,30)                           , MT.FishRaw            ,  U2, MT.Bone, U3, MT.FishOil, U2);
		OM.data(ST.make(Items.fish, 1,31)                           , MT.FishRaw            ,  U * 2, MT.Bone, U3, MT.FishOil, U * 4);
		OM.data(ST.make(Items.fish, 1,32)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U * 2);
		OM.data(ST.make(Items.fish, 1,33)                           , MT.FishRaw            ,  U2, MT.Bone, U9, MT.FishOil, U * 1);
		OM.data(ST.make(Items.fish, 1,34)                           , MT.FishRotten         ,  U * 2, MT.Bone, U3, MT.FishOil, U * 1);
		OM.data(ST.make(Items.fish, 1,35)                           , MT.Bone               ,  U * 6);
		OM.data(ST.make(Items.fish, 1,36)                           , MT.FishRaw            ,  U * 2, MT.Bone, U3, MT.FishOil, U * 4);
		OM.data(ST.make(Items.fish, 1,37)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U * 3);
		OM.data(ST.make(Items.fish, 1,38)                           , MT.FishRaw            ,  U * 1, MT.Bone, U9, MT.FishOil, U * 1, MT.Redstone,  U4);
		OM.data(ST.make(Items.fish, 1,39)                           , MT.FishRaw            ,  U * 2, MT.Bone, U9, MT.FishOil, U * 2);
		OM.data(ST.make(Items.fish, 1,40)                           , MT.FishRaw            ,  U * 1, MT.Bone, U9, MT.FishOil, U * 1);
		OM.data(ST.make(Items.fish, 1,41)                           , MT.FishRaw            ,  U * 1, MT.Bone, U9, MT.FishOil, U2);
		OM.data(ST.make(Items.fish, 1,42)                           , MT.FishRaw            ,  U2   , MT.Bone, U3, MT.FishOil, U20);
		OM.data(ST.make(Items.fish, 1,43)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U * 1);
		OM.data(ST.make(Items.fish, 1,44)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U * 3);
		OM.data(ST.make(Items.fish, 1,45)                           , MT.FishRaw            ,  U * 2, MT.Bone, U3, MT.FishOil, U * 4);
		OM.data(ST.make(Items.fish, 1,46)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U * 3);
		OM.data(ST.make(Items.fish, 1,47)                           , MT.FishRaw            ,  U * 2, MT.Bone, U3, MT.FishOil, U * 5);
		OM.data(ST.make(Items.fish, 1,48)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U20);
		OM.data(ST.make(Items.fish, 1,49)                           , MT.FishRaw            ,  U2, MT.Bone, U3, MT.FishOil, U * 2);
		OM.data(ST.make(Items.fish, 1,50)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Fe, U72);
		OM.data(ST.make(Items.fish, 1,51)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Au, U72);
		OM.data(ST.make(Items.fish, 1,52)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Cu, U72);
		OM.data(ST.make(Items.fish, 1,53)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Al2O3, U72);
		OM.data(ST.make(Items.fish, 1,54)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.TiO2, U72);
		OM.data(ST.make(Items.fish, 1,55)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Mg, U72);
		OM.data(ST.make(Items.fish, 1,56)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Ag, U72);
		OM.data(ST.make(Items.fish, 1,57)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Pb, U72);
		OM.data(ST.make(Items.fish, 1,58)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Sn, U72);
		OM.data(ST.make(Items.fish, 1,59)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Pt, U72);
		OM.data(ST.make(Items.fish, 1,60)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Ni, U72);
		OM.data(ST.make(Items.fish, 1,61)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Co, U72);
		OM.data(ST.make(Items.fish, 1,62)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Ardite, U72);
		OM.data(ST.make(Items.fish, 1,63)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Os, U72);
		OM.data(ST.make(Items.fish, 1,64)                           , MT.FishRaw            ,  U * 1, MT.Bone, U3, MT.FishOil, U2, MT.Zn, U72);
		}
		OM.data(ST.make(Items.fish, 1, W)                           , MT.FishRaw            ,  U * 2, MT.Bone, U9, MT.FishOil, U * 1);
	}
}
