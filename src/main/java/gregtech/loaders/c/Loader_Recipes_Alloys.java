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

package gregtech.loaders.c;

import gregapi.data.ANY;
import gregapi.data.FL;
import gregapi.data.RM;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;

import static gregapi.data.CS.T;
import static gregapi.data.CS.ZL_IS;

public class Loader_Recipes_Alloys implements Runnable {
	@Override public void run() {
		
		RM.Smelter.addRecipe0(T, 16, 16, FL.make("molten.copper", 1), FL.make("molten.annealedcopper", 1), ZL_IS);
		RM.Smelter.addRecipe0(T, 16, 16, FL.make("molten.iron"  , 1), FL.make("molten.wroughtiron"   , 1), ZL_IS);
		
		for (String tCopper : new String[] {"molten.copper", "molten.annealedcopper"}) {
		mix(tCopper                 ,  1, "molten.nickel"         ,  1                                                            , "molten.constantan"       ,  2);
		mix(tCopper                 ,  3, "molten.arsenic"        ,  1                                                            , "molten.arseniccopper"    ,  4);
		mix(tCopper                 ,  3, "molten.tin"            ,  1                                                            , "molten.bronze"           ,  4);
		mix(tCopper                 ,  3, "molten.zinc"           ,  1                                                            , "molten.brass"            ,  4);
		mix(tCopper                 ,  1, "molten.aluminium"      ,  3                                                            , "molten.aluminiumbrass"   ,  4);
		mix(tCopper                 ,  1, "molten.redstone"       ,  4                                                            , "molten.redalloy"         ,  1);
		mix(tCopper                 ,  1, "molten.silver"         ,  4                                                            , "molten.sterlingsilver"   ,  5);
		mix(tCopper                 ,  1, "molten.silver"         ,  1, "molten.redstone"       ,  4, "molten.nikolite"       ,  4, "molten.purplealloy"      ,  2);
		mix(tCopper                 ,  3, "molten.silver"         ,  1, "molten.redstone"       , 10                              , "molten.signalum"         ,  4);
		mix(tCopper                 ,  1, "molten.silver"         ,  2, "molten.redalloy"       ,  5                              , "molten.signalum"         ,  8);
		mix(tCopper                 ,  3, "molten.silver"         ,  1, "molten.gold"           ,  1                              , "molten.blackbronze"      ,  5);
		mix(tCopper                 ,  3, "molten.tin"            ,  1, "molten.gold"           ,  4                              , "molten.hepatizon"        ,  8);
		mix(tCopper                 ,  3, "molten.electrum"       ,  2                                                            , "molten.blackbronze"      ,  5);
		mix(tCopper                 ,  1, "molten.gold"           ,  4                                                            , "molten.rosegold"         ,  5);
		mix(tCopper                 ,  3, "molten.tin"            ,  1, "molten.arsenic"        ,  1                              , "molten.arsenicbronze"    ,  5);
		mix(tCopper                 ,  3, "molten.zinc"           ,  1, "molten.bismuth"        ,  1                              , "molten.bismuthbronze"    ,  5);
		}
		for (String tIron : new String[] {"molten.iron", "molten.wroughtiron", "molten.pigiron", "molten.meteoriciron"}) {
		mix(tIron                   ,  2, "molten.nickel"         ,  1                                                            , "molten.invar"            ,  3);
		mix(tIron                   ,  1, "molten.aluminium"      ,  1, "molten.chromium"       ,  1                              , "molten.kanthal"          ,  3);
		mix(tIron                   ,  1, "molten.tin"            ,  1                                                            , "molten.tinalloy"         ,  2);
		mix(tIron                   ,  1, "molten.gold"           ,  1                                                            , "molten.angmallen"        ,  2);
		mix(tIron                   ,  6, "molten.nickel"         ,  1, "molten.chromium"       ,  1, "molten.manganese"      ,  1, "molten.stainlesssteel"   ,  9);
		mix(tIron                   ,  4, "molten.invar"          ,  3, "molten.chromium"       ,  1, "molten.manganese"      ,  1, "molten.stainlesssteel"   ,  9);
		mix(tIron                   , 24, "molten.nichrome"       ,  5, "molten.chromium"       ,  3, "molten.manganese"      ,  4, "molten.stainlesssteel"   , 36);
		mix(tIron                   ,  1, "molten.nikolite"       ,  8                                                            , "molten.electrotinealloy" ,  1);
		}
		for (String tSteel : new String[] {"molten.steel", "molten.meteoricsteel", "molten hsla", "molten.knightmetal"}) {
		mix(tSteel                  ,  1, "molten.tungsten"       ,  1                                                            , "molten.tungstensteel"    ,  2);
		}
		for (OreDictMaterial tMat : ANY.Glowstone.mToThis) {
		mix("molten.tin"            ,  3, "molten.silver"         ,  1, "molten."+tMat.mNameInternal.toLowerCase(), 4             , "molten.lumium"           ,  4);
		}
		mix("molten.nikolite"       ,  4, "molten.silver"         ,  1                                                            , "molten.bluealloy"        ,  1);
		mix("molten.redalloy"       ,  1, "molten.bluealloy"      ,  1                                                            , "molten.purplealloy"      ,  1);
		mix("molten.silicon"        ,  1, "molten.redstone"       ,  1                                                            , "molten.redstonealloy"    ,  1);
		mix("molten.silicon"        ,  1, "molten.nikolite"       ,  1                                                            , "molten.nikolinealloy"    ,  1);
		mix("molten.chromium"       ,  1, "molten.nickel"         ,  4                                                            , "molten.nichrome"         ,  5);
		mix("molten.gold"           ,  4, "molten.ancientdebris"  ,  4                                                            , "molten.netherite"        ,  1);
		mix("molten.gold"           ,  1, "molten.titanium"       ,  3                                                            , "molten.titaniumgold"     ,  4);
		mix("molten.gold"           ,  1, "molten.bronze"         ,  1                                                            , "molten.hepatizon"        ,  2);
		mix("molten.gold"           ,  1, "molten.silver"         ,  1                                                            , "molten.electrum"         ,  2);
		mix("molten.gold"           ,  1, "molten.redstone"       ,  1                                                            , "molten.goldinductive"    ,  2);
		mix("molten.tin"            ,  9, "molten.antimony"       ,  1                                                            , "molten.solderingalloy"   , 10);
		mix("molten.lead"           ,  4, "molten.antimony"       ,  1                                                            , "molten.batteryalloy"     ,  5);
		mix("molten.orichalcum"     ,  1, "molten.platinum"       ,  1                                                            , "molten.celenegil"        ,  2);
		mix("molten.ardite"         ,  1, "molten.cobalt"         ,  1                                                            , "molten.manyullyn"        ,  2);
		mix("molten.magnesium"      ,  1, "molten.aluminium"      ,  2                                                            , "molten.magnalium"        ,  3);
		mix("molten.brass"          ,  7, "molten.aluminium"      ,  1, "molten.cobalt"         ,  1                              , "molten.cobaltbrass"      ,  9);
		mix("molten.brass"          ,  4, "molten.bismuth"        ,  1                                                            , "molten.bismuthbronze"    ,  5);
		mix("molten.bronze"         ,  4, "molten.arsenic"        ,  1                                                            , "molten.arsenicbronze"    ,  5);
		mix("molten.cobalt"         ,  5, "molten.chromium"       ,  2, "molten.nickel"         ,  1, "molten.molybdenum"     ,  1, "molten.ultimet"          ,  9);
		mix("molten.cobalt"         , 20, "molten.chromium"       ,  7, "molten.nichrome"       ,  5, "molten.molybdenum"     ,  4, "molten.ultimet"          , 36);
		mix("molten.osmiumelemental",  1, "molten.iridium"        ,  1                                                            , "molten.osmiridium"       ,  2);
		mix("molten.tungstensteel"  ,  5, "molten.chromium"       ,  1, "molten.molybdenum"     ,  2, "molten.vanadium"       ,  1, "molten.hssg"             ,  9);
		mix("molten.hssg"           ,  6, "molten.cobalt"         ,  1, "molten.manganese"      ,  1, "molten.silicon"        ,  1, "molten.hsse"             ,  9);
		mix("molten.hssg"           ,  6, "molten.osmiridium"     ,  2, "molten.iridium"        ,  1                              , "molten.hsss"             ,  9);
		mix("molten.hssg"           ,  6, "molten.osmiumelemental",  1, "molten.iridium"        ,  2                              , "molten.hsss"             ,  9);
	}
	
	private static void mix(String aInput1, int aIn1Amount, String aInput2, int aIn2Amount, String aOutput, int aOutAmount) {
		RM.Mixer.addRecipe1(T, 16, Math.max(aOutAmount, aIn1Amount+aIn2Amount), ST.tag(2), FL.array(FL.make_(aInput1, aIn1Amount), FL.make_(aInput2, aIn2Amount)), FL.make_(aOutput, aOutAmount), ZL_IS);
	}
	private static void mix(String aInput1, int aIn1Amount, String aInput2, int aIn2Amount, String aInput3, int aIn3Amount, String aOutput, int aOutAmount) {
		RM.Mixer.addRecipe1(T, 16, Math.max(aOutAmount, aIn1Amount+aIn2Amount+aIn3Amount), ST.tag(3), FL.array(FL.make_(aInput1, aIn1Amount), FL.make_(aInput2, aIn2Amount), FL.make_(aInput3, aIn3Amount)), FL.make_(aOutput, aOutAmount), ZL_IS);
	}
	private static void mix(String aInput1, int aIn1Amount, String aInput2, int aIn2Amount, String aInput3, int aIn3Amount, String aInput4, int aIn4Amount, String aOutput, int aOutAmount) {
		RM.Mixer.addRecipe1(T, 16, Math.max(aOutAmount, aIn1Amount+aIn2Amount+aIn3Amount+aIn4Amount), ST.tag(4), FL.array(FL.make_(aInput1, aIn1Amount), FL.make_(aInput2, aIn2Amount), FL.make_(aInput3, aIn3Amount), FL.make_(aInput4, aIn4Amount)), FL.make_(aOutput, aOutAmount), ZL_IS);
	}
}
