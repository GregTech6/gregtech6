/**
 * Copyright (c) 2020 GregTech-6 Team
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
import static gregapi.data.TD.Atomic.*;
import static gregapi.data.TD.Compounds.*;
import static gregapi.data.TD.ItemGenerator.*;
import static gregapi.data.TD.Processing.*;
import static gregapi.data.TD.Properties.*;
import static gregapi.render.TextureSet.*;

import gregapi.code.HashSetNoNulls;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.configurations.OreDictConfigurationComponent;
import gregapi.render.TextureSet;
import gregapi.util.OM;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.stats.AchievementList;

/**
 * @author Gregorius Techneticies
 * 
 * List of all Materials. The Short Name is for ease of overview and stands for "MaTerial".
 * 
 * Note: I wrote those shortcuts not only because of overview Reasons. I have hit the 65536 Limit of the static initialiser multiple times by now.
 */
public class MT {
	/** This Set is for GregTech usage only, do not add your Materials to this! */
	public static final HashSetNoNulls<OreDictMaterial> ALL_MATERIALS_REGISTERED_HERE = new HashSetNoNulls<>();
	
	/** Making the Table a little bit more overviewable. DO NOT USE THESE FUNCTIONS YOURSELF!!! Use "OreDictMaterial.createMaterial(YOUR-ID-AS-SPECIFIED-IN-THE-ID-RANGES, OREDICT-NAME, LOCALISED-NAME)" */
	static OreDictMaterial tier         (String aNameOreDict) {return create(-1, aNameOreDict).put(UNUSED_MATERIAL, DONT_SHOW_THIS_COMPONENT, IGNORE_IN_COLOR_LOG).setAllToTheOutputOf(null, 0, 1);}
	static OreDictMaterial unused       (String aNameOreDict) {return create(-1, aNameOreDict).put(UNUSED_MATERIAL, DONT_SHOW_THIS_COMPONENT);}
	static OreDictMaterial depricated   (String aNameOreDict) {return create(-1, aNameOreDict).put(UNUSED_MATERIAL, DONT_SHOW_THIS_COMPONENT);}
	static OreDictMaterial invalid      (String aNameOreDict) {return unused(aNameOreDict).put(INVALID_MATERIAL);}
	static OreDictMaterial create       (int aID, String aNameOreDict) {if (aID >= 10000) return null; OreDictMaterial rMaterial = OreDictMaterial.createMaterial(aID, aNameOreDict, aNameOreDict); ALL_MATERIALS_REGISTERED_HERE.add(rMaterial); return rMaterial;}
	static OreDictMaterial create       (int aID, String aNameOreDict, TextureSet[] aSets) {return create(aID, aNameOreDict).setTextures(aSets);}
	static OreDictMaterial create       (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA) {return create(aID, aNameOreDict, aSets).setRGBa(aR, aG, aB, aA).put(aR==256?UNUSED_MATERIAL:null).hide(aR==256);}
	
	/** Making the Table a little bit more overviewable. DO NOT USE THESE FUNCTIONS YOURSELF!!! Use "OreDictMaterial.createMaterial(YOUR-ID-AS-SPECIFIED-IN-THE-ID-RANGES, OREDICT-NAME, LOCALISED-NAME)" */
	static OreDictMaterial element      (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return create      (aID, aNameOreDict, aSets, aR, aG, aB, aA).setStats(aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter).put(ELEMENT).setTooltip(aSymbol);}
	static OreDictMaterial metal        (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return element     (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(METAL, SMITHABLE, MELTING, EXTRUDER, aMeltingPoint < 1200 ? new Object[] {EXTRUDER_SIMPLE, FURNACE, MORTAR} : null);}
	static OreDictMaterial metalloid    (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return element     (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(METALLOID, SMITHABLE, MELTING, MOLTEN, EXTRUDER, aMeltingPoint < 1200 ? new Object[] {EXTRUDER_SIMPLE, FURNACE, MORTAR} : null);}
	static OreDictMaterial nonmetal     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return element     (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(NONMETAL);}
	static OreDictMaterial diatomic     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return nonmetal    (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(DIATOMIC_NONMETAL);}
	static OreDictMaterial diatomicgas  (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return diatomic    (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(GASSES);}
	static OreDictMaterial polyatomic   (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return nonmetal    (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(POLYATOMIC_NONMETAL);}
	static OreDictMaterial noblegas     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return nonmetal    (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(NOBLE_GAS, GASSES);}
	static OreDictMaterial alkali       (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(ALKALI_METAL, MOLTEN);}
	static OreDictMaterial alkaline     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(ALKALINE_EARTH_METAL, MOLTEN);}
	static OreDictMaterial lanthanide   (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(LANTHANIDE);}
	static OreDictMaterial actinide     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(ACTINIDE);}
	static OreDictMaterial transmetal   (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(TRANSITION_METAL);}
	static OreDictMaterial precmetal    (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return transmetal  (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(PRECIOUS_METAL);}
	static OreDictMaterial noblemetal   (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return precmetal   (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(NOBLE_METAL);}
	static OreDictMaterial refractmetal (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return transmetal  (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(REFRACTORY_METAL);}
	static OreDictMaterial platingroup  (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return precmetal   (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(PLATINUM_GROUP);}
	static OreDictMaterial posttrans    (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets, long aR, long aG, long aB, long aA)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, aR, aG, aB, aA).put(POST_TRANSITION_METAL);}
	
	static OreDictMaterial element      (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return create      (aID, aNameOreDict, aSets, 256, 256, 256, 255).setStats(aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter).put(ELEMENT).setTooltip(aSymbol);}
	static OreDictMaterial unknown      (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons                                                                                            )   {return element     (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, 1000, 3000, 0, SET_SHINY).hide().aspects(TC.RADIO, 1);}
	static OreDictMaterial metalloid    (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return element     (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(METALLOID, SMITHABLE, MELTING, MOLTEN, EXTRUDER, aMeltingPoint < 1200 ? new Object[] {EXTRUDER_SIMPLE, FURNACE, MORTAR} : null);}
	static OreDictMaterial alkali       (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(ALKALI_METAL, MOLTEN);}
	static OreDictMaterial alkaline     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(ALKALINE_EARTH_METAL, MOLTEN);}
	static OreDictMaterial lanthanide   (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(LANTHANIDE);}
	static OreDictMaterial actinide     (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(ACTINIDE);}
	static OreDictMaterial transmetal   (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(TRANSITION_METAL);}
	static OreDictMaterial posttrans    (int aID, String aNameOreDict, String aSymbol, long aProtonsAndElectrons, long aNeutrons, long aMeltingPoint, long aBoilingPoint, double aGramPerCubicCentimeter, TextureSet[] aSets)   {return metal       (aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, aMeltingPoint, aBoilingPoint, aGramPerCubicCentimeter, aSets, 256, 256, 256, 255).put(POST_TRANSITION_METAL);}
	
	/** Making the Table a little bit more overviewable. DO NOT USE THESE FUNCTIONS YOURSELF!!! Use "OreDictMaterial.createMaterial(YOUR-ID-AS-SPECIFIED-IN-THE-ID-RANGES, OREDICT-NAME, LOCALISED-NAME)" */
	static OreDictMaterial dcmp         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial cent         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dcmp            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial elec         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dcmp            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial gas          (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_GAS);}
	static OreDictMaterial gasdcmp      (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gas             (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial gasflam      (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gas             (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial gasexpl      (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gas             (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial gascent      (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gasdcmp         (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial gaselec      (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gasdcmp         (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial lqud         (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_FLUID);}
	static OreDictMaterial lqudflam     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lqud            (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(FLAMMABLE);}
	static OreDictMaterial lqudexpl     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lqud            (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(FLAMMABLE, EXPLOSIVE);}
	static OreDictMaterial lquddcmp     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lqud            (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial lqudcent     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lquddcmp        (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial lqudelec     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lquddcmp        (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial gaschem      (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_GAS);}
	static OreDictMaterial gaschemdcmp  (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gaschem         (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial gaschemflam  (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gaschem         (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(FLAMMABLE);}
	static OreDictMaterial gaschemexpl  (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gaschem         (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(FLAMMABLE, EXPLOSIVE);}
	static OreDictMaterial gaschemcent  (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gaschemdcmp     (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial gaschemelec  (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gaschemdcmp     (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial lqudchem     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_FLUID);}
	static OreDictMaterial lqudchemdcmp (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lqudchem        (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial lqudchemflam (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lqudchem        (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(FLAMMABLE);}
	static OreDictMaterial lqudchemexpl (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lqudchem        (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(FLAMMABLE, EXPLOSIVE);}
	static OreDictMaterial lqudchemcent (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lqudchemdcmp    (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial lqudchemelec (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lqudchemdcmp    (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial gasacid      (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_GAS, ACID);}
	static OreDictMaterial gasaciddcmp  (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gasacid         (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial gasacidflam  (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gasacid         (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(FLAMMABLE);}
	static OreDictMaterial gasacidexpl  (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gasacid         (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(FLAMMABLE, EXPLOSIVE);}
	static OreDictMaterial gasacidcent  (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gasaciddcmp     (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial gasacidelec  (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return gasaciddcmp     (aID, aNameOreDict, SET_GAS         , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial lqudacid     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_FLUID, ACID);}
	static OreDictMaterial lqudaciddcmp (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lqudacid        (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial lqudacidflam (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lqudacid        (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(FLAMMABLE);}
	static OreDictMaterial lqudacidexpl (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lqudacid        (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(FLAMMABLE, EXPLOSIVE);}
	static OreDictMaterial lqudacidcent (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lqudaciddcmp    (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial lqudacidelec (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return lqudaciddcmp    (aID, aNameOreDict, SET_FLUID       , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial gas          (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_GAS);}
	static OreDictMaterial gasdcmp      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gas             (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial gascent      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gasdcmp         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial gaselec      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gasdcmp         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial lqud         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_FLUID);}
	static OreDictMaterial lquddcmp     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqud            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial lqudcent     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lquddcmp        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial lqudelec     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lquddcmp        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial gaschem      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_GAS);}
	static OreDictMaterial gaschemdcmp  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gaschem         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial gaschemcent  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gaschemdcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial gaschemelec  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gaschemdcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial lqudchem     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_FLUID);}
	static OreDictMaterial lqudchemdcmp (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqudchem        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial lqudchemcent (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqudchemdcmp    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial lqudchemelec (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqudchemdcmp    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial gasacid      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_GAS, ACID);}
	static OreDictMaterial gasaciddcmp  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gasacid         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial gasacidcent  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gasaciddcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial gasacidelec  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gasaciddcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial lqudacid     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_CONTAINERS, CONTAINERS_FLUID, ACID);}
	static OreDictMaterial lqudaciddcmp (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqudacid        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial lqudacidcent (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqudaciddcmp    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial lqudacidelec (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return lqudaciddcmp    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	
	
	
	static OreDictMaterial dust         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_DUST, MORTAR).setPriorityPrefix(2);}
	static OreDictMaterial dustdcmp     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dust            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial dustcent     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dustdcmp        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial dustelec     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dustdcmp        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial glowstone    (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return oredustcent     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(PLATES, STICKS, MORTAR, BRITTLE, UNBURNABLE, MELTING, CRYSTAL, G_GEM_ORES_TRANSPARENT, ANY.Glowstone, GLOWING, LIGHTING).setPriorityPrefix(2).setOreMultiplier(4);}
	static OreDictMaterial redstone     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return oredustcent     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(PLATES, STICKS, MORTAR, BRITTLE, UNBURNABLE, MELTING, CRYSTAL, G_GEM_ORES_TRANSPARENT).setPriorityPrefix(2).setOreMultiplier(4);}
	static OreDictMaterial coal         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return elec            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_GEM_ORES, BRITTLE, FLAMMABLE, MORTAR, INGOTS, COAL).setPriorityPrefix(1);}
	static OreDictMaterial wax          (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return dust            (aID, aNameOreDict, SET_FOOD        , aR, aG, aB, aA).put(ANY.Wax, FOILS, PLATES, INGOTS, PARTS, FURNACE, MELTING, BRITTLE, MORTAR, EXTRUDER, EXTRUDER_SIMPLE);}
	static OreDictMaterial meat         (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return dustfood        (aID, aNameOreDict, SET_FINE        , aR, aG, aB, aA).put(MEAT, INGOTS, MELTING, EXTRUDER, EXTRUDER_SIMPLE);}
	static OreDictMaterial grain        (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return dustfood        (aID, aNameOreDict, SET_POWDER      , aR, aG, aB, aA).put(ANY.Grains, ANY.FlourGrains, FLAMMABLE);}
	static OreDictMaterial food         (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial orefood      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return oredust         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial dustfood     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dust            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial mixfood      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return mixdust         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial food         (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_FOOD        , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial orefood      (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return oredust         (aID, aNameOreDict, SET_FINE        , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial dustfood     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return dust            (aID, aNameOreDict, SET_FINE        , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial mixfood      (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return mixdust         (aID, aNameOreDict, SET_FINE        , aR, aG, aB, aA).put(FOOD, MORTAR);}
	static OreDictMaterial dye          (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return dust            (aID, aNameOreDict, SET_FOOD        , aR, aG, aB,255).aspects(TC.SENSUS, 1).put(DONT_SHOW_THIS_COMPONENT);}
	static OreDictMaterial quartz       (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_QUARTZ      , aR, aG, aB, aA).put(G_QUARTZ_ORES, ANY.Quartz, ANY.SiO2, MORTAR, BRITTLE, QUARTZ, BLACKLISTED_SMELTER).setSmelting(SiO2, U).setPriorityPrefix(1);}
	static OreDictMaterial gem          (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_GEM_ORES).setPriorityPrefix(1);}
	static OreDictMaterial gemdcmp      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gem             (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial gemcent      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gemdcmp         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial gemelec      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gemdcmp         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial stone        (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_STONE, ANY.Stone, STONE, BRITTLE, MORTAR, FURNACE, EXTRUDER, EXTRUDER_SIMPLE);}
	static OreDictMaterial stonedcmp    (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return stone           (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial stonecent    (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return stonedcmp       (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial stoneelec    (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return stonedcmp       (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial stone        (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_STONE       , aR, aG, aB, aA).put(G_STONE, ANY.Stone, STONE, BRITTLE, MORTAR, FURNACE, EXTRUDER, EXTRUDER_SIMPLE);}
	static OreDictMaterial stonedcmp    (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return stone           (aID, aNameOreDict, SET_STONE       , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial stonecent    (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return stonedcmp       (aID, aNameOreDict, SET_STONE       , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial stoneelec    (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return stonedcmp       (aID, aNameOreDict, SET_STONE       , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial crystal      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_GEM_ORES_TRANSPARENT, CRYSTAL).setPriorityPrefix(1);}
	static OreDictMaterial crystaldcmp  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return crystal         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial crystalcent  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return crystaldcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial crystalelec  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return crystaldcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial crystal_tc   (int aID, String aNameOreDict                    , long aR, long aG, long aB, byte aColor)  {return crystal         (aID, aNameOreDict, SET_SHARDS      , aR, aG, aB,255).lens(aColor).put(MD.TC , ANY.ThaumCrystal, COMMON_ORE, MAGICAL, UNBURNABLE).setOreMultiplier(2).visDefault();}
	static OreDictMaterial hexorium     (int aID                                         , long aR, long aG, long aB, byte aColor)  {return crystal(aID, "Hexorium"+DYE_NAMES[aColor], SET_HEX  , aR, aG, aB,127).lens(aColor).put(MD.HEX, ANY.Hexorium    , COMMON_ORE, GLOWING, MORTAR, BRITTLE, CRYSTALLISABLE, BLACKLISTED_SMELTER).setSmelting(null, 0).setOreMultiplier(aColor == DYE_INDEX_Black || aColor == DYE_INDEX_White ? 3 : 4).aspects(TC.VITREUS, 3, aColor == DYE_INDEX_Black ? TC.TENEBRAE : aColor == DYE_INDEX_White ? TC.LUX : TC.SENSUS, 4).setLocal(DYE_NAMES[aColor] + " Hexorium").qual(2, 5.0F, aColor == DYE_INDEX_Black ? 512 : aColor == DYE_INDEX_White ? 384 : 256, 2).visDefault();}
	static OreDictMaterial valgem       (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return gem             (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_GEM_ORES_TRANSPARENT, CRYSTAL, VALUABLE);}
	static OreDictMaterial valgemdcmp   (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return valgem          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE).setSmelting(null, 0);}
	static OreDictMaterial valgemcent   (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return valgemdcmp      (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial valgemelec   (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return valgemdcmp      (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial diamond      (int aID, String aNameOreDict                    , long aR, long aG, long aB, byte aColor)  {return valgemdcmp      (aID, aNameOreDict, SET_DIAMOND     , aR, aG, aB,127).lens(aColor).put(ANY.Diamond   , COMMON_ORE).steal(C).aspects(TC.VITREUS, 3, TC.LUCRUM , 4                ).qual(3, 8.0,1280, 3).setSmelting(C    , 2*U  ).setBurning(Ash, U);}
	static OreDictMaterial sapphire     (int aID, String aNameOreDict                    , long aR, long aG, long aB, byte aColor)  {return valgemcent      (aID, aNameOreDict, SET_GEM_VERTICAL, aR, aG, aB,127).lens(aColor).put(ANY.Sapphire  , COMMON_ORE, MELTING).aspects(TC.VITREUS, 3, TC.LUCRUM , 4                ).qual(3, 7.0, 512, 3).setSmelting(Al2O3, 3*U4 );}
	static OreDictMaterial emerald      (int aID, String aNameOreDict                    , long aR, long aG, long aB, byte aColor)  {return valgemelec      (aID, aNameOreDict, SET_EMERALD     , aR, aG, aB,127).lens(aColor).put(ANY.Emerald   , COMMON_ORE, MELTING).aspects(TC.VITREUS, 3, TC.LUCRUM , 3                ).qual(3, 9.0, 128, 2).setSmelting(Be   ,   U36);}
	static OreDictMaterial garnet       (int aID, String aNameOreDict                    , long aR, long aG, long aB, byte aColor)  {return valgemelec      (aID, aNameOreDict, SET_RUBY        , aR, aG, aB,127).lens(aColor).put(ANY.Garnet    , MD.GT              ).aspects(TC.VITREUS, 3, TC.LUCRUM , 1                ).qual(3, 7.0, 128, 2);}
	static OreDictMaterial jasper       (int aID, String aNameOreDict                    , long aR, long aG, long aB, byte aColor)  {return valgemelec      (aID, aNameOreDict, SET_GLASS       , aR, aG, aB,150).lens(aColor).put(ANY.Jasper    , MD.RH              ).aspects(TC.VITREUS, 2, TC.LUCRUM , 2, TC.METALLUM, 1).qual(3, 7.0, 256, 2).uumMcfg( 0, SiO2, 2*U, Fe, 1*U);}
	static OreDictMaterial tigereye     (int aID, String aNameOreDict                    , long aR, long aG, long aB, byte aColor)  {return valgemelec      (aID, aNameOreDict, SET_GLASS       , aR, aG, aB,200).lens(aColor).put(ANY.TigerEye  , MD.RH              ).aspects(TC.VITREUS, 2, TC.LUCRUM , 1, TC.TERRA   , 1).qual(3, 7.0, 256, 2).uumMcfg( 0, SiO2, 1*U);}
	static OreDictMaterial aventurine   (int aID, String aNameOreDict                    , long aR, long aG, long aB, byte aColor)  {return valgemelec      (aID, aNameOreDict, SET_GLASS       , aR, aG, aB,200).lens(aColor).put(ANY.Aventurine, MD.RH              ).aspects(TC.VITREUS, 2, TC.LUCRUM , 1, TC.POTENTIA, 1).qual(3, 7.0, 256, 2).uumMcfg( 0, SiO2, 1*U);}
	static OreDictMaterial fluorite     (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return gem             (aID, aNameOreDict, SET_RUBY        , aR, aG, aB,255)             .put(ANY.CaF2      , COMMON_ORE, MD.ReC ).aspects(TC.VITREUS, 2, TC.VENENUM, 2                )                     .uumMcfg( 0, Ca, 1*U, F, 2*U).heat(1633).put(DECOMPOSABLE, ACID, MELTING, MOLTEN, MORTAR, INGOTS, BRITTLE, CRYSTALLISABLE);}
	static OreDictMaterial mix          (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dcmp            (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial mixdust      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return dustcent        (aID, aNameOreDict, aSets           , aR, aG, aB, aA);}
	static OreDictMaterial oredust      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_DUST_ORES);}
	static OreDictMaterial oredustdcmp  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return oredust         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(DECOMPOSABLE);}
	static OreDictMaterial oredustcent  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return oredustdcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(CENTRIFUGE);}
	static OreDictMaterial oredustelec  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return oredustdcmp     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ELECTROLYSER);}
	static OreDictMaterial metal        (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_INGOT, SMITHABLE, MELTING, EXTRUDER);}
	static OreDictMaterial metalore     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_INGOT_ORES);}
	static OreDictMaterial metalmachine (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_INGOT_MACHINE);}
	static OreDictMaterial metalmachore (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalmachine    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_INGOT_MACHINE_ORES);}
	static OreDictMaterial metal        (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(G_INGOT, SMITHABLE, MELTING, EXTRUDER);}
	static OreDictMaterial metalore     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(G_INGOT_ORES);}
	static OreDictMaterial metalmachine (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(G_INGOT_MACHINE);}
	static OreDictMaterial metalmachore (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalmachine    (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(G_INGOT_MACHINE_ORES);}
	static OreDictMaterial setal        (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(G_INGOT, SMITHABLE, MELTING, EXTRUDER);}
	static OreDictMaterial setalore     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(G_INGOT_ORES);}
	static OreDictMaterial setalmachine (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(G_INGOT_MACHINE);}
	static OreDictMaterial setalmachore (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalmachine    (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(G_INGOT_MACHINE_ORES);}
	static OreDictMaterial alloy        (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloyore     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalore        (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachine (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalmachine    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachore (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalmachore    (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloy        (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloyore     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalore        (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachine (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalmachine    (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachore (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalmachore    (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial slloy        (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metal           (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial slloyore     (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalore        (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial slloymachine (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalmachine    (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial slloymachore (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return metalmachore    (aID, aNameOreDict, SET_SHINY       , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial metalnd      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_INGOT_ND, SMITHABLE, MELTING, EXTRUDER);}
	static OreDictMaterial metalmachnd  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalnd         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(G_INGOT_ND_MACHINE);}
	static OreDictMaterial alloynd      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalnd         (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachnd  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA    )  {return metalmachnd     (aID, aNameOreDict, aSets           , aR, aG, aB, aA).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial metal        (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB             )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB,255).put(G_INGOT, SMITHABLE, MELTING, EXTRUDER);}
	static OreDictMaterial metalore     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB             )  {return metal           (aID, aNameOreDict, aSets           , aR, aG, aB,255).put(G_INGOT_ORES);}
	static OreDictMaterial metalmachine (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB             )  {return metal           (aID, aNameOreDict, aSets           , aR, aG, aB,255).put(G_INGOT_MACHINE);}
	static OreDictMaterial metalmachore (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB             )  {return metalmachine    (aID, aNameOreDict, aSets           , aR, aG, aB,255).put(G_INGOT_MACHINE_ORES);}
	static OreDictMaterial metal        (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return create          (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB,255).put(G_INGOT, SMITHABLE, MELTING, EXTRUDER);}
	static OreDictMaterial metalore     (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metal           (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB,255).put(G_INGOT_ORES);}
	static OreDictMaterial metalmachine (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metal           (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB,255).put(G_INGOT_MACHINE);}
	static OreDictMaterial metalmachore (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metalmachine    (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB,255).put(G_INGOT_MACHINE_ORES);}
	static OreDictMaterial setal        (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return create          (aID, aNameOreDict, SET_SHINY       , aR, aG, aB,255).put(G_INGOT, SMITHABLE, MELTING, EXTRUDER);}
	static OreDictMaterial setalore     (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metal           (aID, aNameOreDict, SET_SHINY       , aR, aG, aB,255).put(G_INGOT_ORES);}
	static OreDictMaterial setalmachine (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metal           (aID, aNameOreDict, SET_SHINY       , aR, aG, aB,255).put(G_INGOT_MACHINE);}
	static OreDictMaterial setalmachore (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metalmachine    (aID, aNameOreDict, SET_SHINY       , aR, aG, aB,255).put(G_INGOT_MACHINE_ORES);}
	static OreDictMaterial alloy        (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB             )  {return metal           (aID, aNameOreDict, aSets           , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloyore     (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB             )  {return metalore        (aID, aNameOreDict, aSets           , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachine (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB             )  {return metalmachine    (aID, aNameOreDict, aSets           , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachore (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB             )  {return metalmachore    (aID, aNameOreDict, aSets           , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloy        (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metal           (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloyore     (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metalore        (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachine (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metalmachine    (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachore (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metalmachore    (aID, aNameOreDict, SET_METALLIC    , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial slloy        (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metal           (aID, aNameOreDict, SET_SHINY       , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial slloyore     (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metalore        (aID, aNameOreDict, SET_SHINY       , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial slloymachine (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metalmachine    (aID, aNameOreDict, SET_SHINY       , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial slloymachore (int aID, String aNameOreDict                    , long aR, long aG, long aB             )  {return metalmachore    (aID, aNameOreDict, SET_SHINY       , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial metalnd      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB             )  {return create          (aID, aNameOreDict, aSets           , aR, aG, aB,255).put(G_INGOT_ND, SMITHABLE, MELTING, EXTRUDER);}
	static OreDictMaterial metalmachnd  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB             )  {return metalnd         (aID, aNameOreDict, aSets           , aR, aG, aB,255).put(G_INGOT_ND_MACHINE);}
	static OreDictMaterial alloynd      (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB             )  {return metalnd         (aID, aNameOreDict, aSets           , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial alloymachnd  (int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB             )  {return metalmachnd     (aID, aNameOreDict, aSets           , aR, aG, aB,255).put(ALLOY, DECOMPOSABLE);}
	static OreDictMaterial wood         (int aID, String aNameOreDict                    , long aR, long aG, long aB, long aA    )  {return wood            (aID, aNameOreDict, SET_WOOD        , aR, aG, aB, aA);}
	
	static OreDictMaterial wood(int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA)  {
		OreDictMaterial rMaterial = create(aID, aNameOreDict, aSets, aR, aG, aB, aA).put(G_WOOD, ANY.Wood, ANY.WoodPlastic, WOOD, MORTAR);
		String tPlank = "plank"+rMaterial.mNameInternal;
		OreDictManager.INSTANCE.addAutoBlackListing(tPlank);
		OreDictManager.INSTANCE.addReRegistration(tPlank, OD.plankAnyWood);
		if ("Wood".equalsIgnoreCase(rMaterial.mNameInternal)) return rMaterial;
		OreDictManager.INSTANCE.setAutomaticItemData(tPlank, new OreDictItemData(rMaterial, U));
		OreDictManager.INSTANCE.addReRegistrationWithReversal("plate"+rMaterial.mNameInternal, tPlank);
		return rMaterial;
	}
	
	static OreDictMaterial gem_aa(int aID, String aNameOreDict, TextureSet[] aSets, long aR, long aG, long aB, long aA, OreDictMaterial aCopy) {
		return dcmp(aID, aNameOreDict, aSets, aR, aG, aB, aA).put(G_GEM_TRANSPARENT, CRYSTAL, BRITTLE, MD.AA).uumMcfg(0, aCopy, U).steal(aCopy).setAllToTheOutputOf(aCopy).visDefault();
	}
	
	static OreDictMaterial unknown(int aID, long aNeutrons) {
		int aProtonsAndElectrons = aID / 10;
		String aNameOreDict, aSymbol;
		
		switch (aProtonsAndElectrons / 100) {
		case 1: aNameOreDict  = "Un"     ; aSymbol  = "U"; break;
		case 2: aNameOreDict  = "Bi"     ; aSymbol  = "B"; break;
		case 3: aNameOreDict  = "Tri"    ; aSymbol  = "T"; break;
		case 4: aNameOreDict  = "Quad"   ; aSymbol  = "Q"; break;
		case 5: aNameOreDict  = "Pent"   ; aSymbol  = "P"; break;
		case 6: aNameOreDict  = "Hex"    ; aSymbol  = "H"; break;
		case 7: aNameOreDict  = "Sept"   ; aSymbol  = "S"; break;
		case 8: aNameOreDict  = "Oct"    ; aSymbol  = "O"; break;
		case 9: aNameOreDict  = "Enn"    ; aSymbol  = "E"; break;
		default: throw new IllegalArgumentException("Disallowed Parameter");
		}
		switch ((aProtonsAndElectrons / 10) % 10) {
		case 0: aNameOreDict += "nil"    ; aSymbol += "n"; break;
		case 1: aNameOreDict += "un"     ; aSymbol += "u"; break;
		case 2: aNameOreDict += "bi"     ; aSymbol += "b"; break;
		case 3: aNameOreDict += "tri"    ; aSymbol += "t"; break;
		case 4: aNameOreDict += "quad"   ; aSymbol += "q"; break;
		case 5: aNameOreDict += "pent"   ; aSymbol += "p"; break;
		case 6: aNameOreDict += "hex"    ; aSymbol += "h"; break;
		case 7: aNameOreDict += "sept"   ; aSymbol += "s"; break;
		case 8: aNameOreDict += "oct"    ; aSymbol += "o"; break;
		case 9: aNameOreDict += "enn"    ; aSymbol += "e"; break;
		}
		switch (aProtonsAndElectrons % 10) {
		case 0: aNameOreDict += "nilium" ; aSymbol += "n"; break;
		case 1: aNameOreDict += "unium"  ; aSymbol += "u"; break;
		case 2: aNameOreDict += "bium"   ; aSymbol += "b"; break;
		case 3: aNameOreDict += "trium"  ; aSymbol += "t"; break;
		case 4: aNameOreDict += "quadium"; aSymbol += "q"; break;
		case 5: aNameOreDict += "pentium"; aSymbol += "p"; break;
		case 6: aNameOreDict += "hexium" ; aSymbol += "h"; break;
		case 7: aNameOreDict += "septium"; aSymbol += "s"; break;
		case 8: aNameOreDict += "octium" ; aSymbol += "o"; break;
		case 9: aNameOreDict += "ennium" ; aSymbol += "e"; break;
		}
		return element(aID, aNameOreDict, aSymbol, aProtonsAndElectrons, aNeutrons, 1000, 3000, 0, SET_SHINY).hide().aspects(TC.RADIO, aProtonsAndElectrons / 50);
	}
	
	/** Technical Material describing a Nullpointer */
	public static final OreDictMaterial NULL = create(-1, "NULL").setStatsElement(0,0,0,0,0).put(INVALID_MATERIAL, DONT_SHOW_THIS_COMPONENT);
	
	/** Material for empty Containers. */
	public static final OreDictMaterial Empty = create(0, "Empty").setStatsElement(0,0,0,0,0).put(EMPTY, AUTO_BLACKLIST, DONT_SHOW_THIS_COMPONENT);
	
	/** Some subatomic Particles and, this might be useful if Projectile Materials have to be determined and you shoot a Laser (Photons in that case) or something Radioactive. */
	public static final OreDictMaterial
	y       , Photon                    =   y       = create(   1, "Photon"     ).setStatsElement( 0, 0, 0, 0, 0).heat(0,0,0).setRGBa(255, 255, 255, 255).put(PARTICLE).setTooltip("y").hide(),
	v       , Neutrino                  =   v       = create(   2, "Neutrino"   ).setStatsElement( 0, 0, 0, 0, 0).heat(0,0,0).setRGBa(180, 180, 180,   0).put(PARTICLE).setTooltip("v").hide(),
	n       , Neutron                   =   n       = create(   3, "Neutron"    ).setStatsElement( 0, 0, 1, 0, 0).heat(0,0,0).setRGBa(128, 128, 128,   0).put(PARTICLE).setTooltip("n").hide(),
	p       , Proton                    =   p       = create(   4, "Proton"     ).setStatsElement( 1, 0, 0, 0, 0).heat(0,0,0).setRGBa(255,   0,   0,   0).put(PARTICLE).setTooltip("p").hide(),
	e       , Electron                  =   e       = create(   5, "Electron"   ).setStatsElement( 0, 1, 0, 0, 0).heat(0,0,0).setRGBa(  0,   0, 255,   0).put(PARTICLE).setTooltip("e").hide();
	
	/** Yes, I consider Magic as some kind of Element. I placed it at the Spot of "Anti-Empty", since Slot 0 is for the "Empty" Material, and "Anti-Empty" = "Magically-Full", or some crazy shit like that. Also Magic has negative Mass. */
	public static final OreDictMaterial
	Ma      , Magic                     =   Ma      = create( 4000, "Magic"     ).setStatsElement( 0, 0, 0,-1, 0).heat(0,0,0).setRGBa(255,   0, 255,   0).setTextures(SET_SHINY).put(ELEMENT, MAGICAL, UNBURNABLE).setTooltip("Ma").hide().qual(3, 10.0, 5120, 5);
	
	/**
	 * All the Elements and a few of their Isotopes.
	 * 
	 * in setStatsEnergetic I guessed some of the Values, because they were not listed in Wikipedia at that time.
	 * Those guessed Values are postfixed with an empty Space.
	 */
	public static final OreDictMaterial
	H       = diatomicgas   (   10, "Hydrogen"              , "H"       ,   1,   0,    14,    20,  0.00008988, SET_DULL             ,   0,   0, 255,  15).put(  CONTAINERS_GAS              , UUM                         ).aspects(TC.AQUA, 1                                     ),
	D       = diatomicgas   (   11, "Deuterium"             , "D"       ,   1,   1,    14,    20,  0.00008988, SET_SHINY            , 255, 255,   0,  15).put(  CONTAINERS_GAS              , FUSION                      ).aspects(TC.AQUA, 2                                     ), H_2=D,
	T       = diatomicgas   (   12, "Tritium"               , "T"       ,   1,   2,    14,    20,  0.00008988, SET_SHINY            , 255,   0,   0,  15).put(  CONTAINERS_GAS              , FUSION                      ).aspects(TC.AQUA, 3                                     ), H_3=T,
	He      = noblegas      (   20, "Helium"                , "He"      ,   2,   2,     1,     4,  0.0001785 , SET_SHINY            , 255, 255, 120,  15).put(  CONTAINERS_GAS              , FUSION, UUM                 ).aspects(TC.AER, 2                                      ),
	He_3    = noblegas      (   21, "Helium-3"              , "He-3"    ,   2,   1,     1,     4,  0.0001785 , SET_SHINY            , 255, 255, 140,  15).put(  CONTAINERS_GAS              , FUSION                      ).aspects(TC.AER, 3                                      ),
	Li      = alkali        (   30, "Lithium"               , "Li"      ,   3,   4,   453,  1560,  0.534     , SET_ROUGH            , 225, 220, 255, 255).put(  G_INGOT_ORES                , FUSION, UUM                 ).aspects(TC.VITREUS, 1, TC.POTENTIA, 2                  ).put(TICKS_PER_SMELT*40, WASHING_MERCURY),
	Li_6    = alkali        (   31, "Lithium-6"             , "Li-6"    ,   3,   3,   453,  1560,  0.534     , SET_ROUGH            , 230, 225, 255, 255).put(  G_INGOT_ORES                , FUSION                      ).aspects(TC.VITREUS, 1, TC.POTENTIA, 1, TC.RADIO, 1     ).put(TICKS_PER_SMELT*40, WASHING_MERCURY),
	Be      = alkaline      (   40, "Beryllium"             , "Be"      ,   4,   5,  1560,  2742,  1.85      , SET_METALLIC         , 100, 180, 100, 255).put(  G_INGOT_ORES                , FUSION, UUM                 ).aspects(TC.METALLUM, 2, TC.LUCRUM, 1                   ).put(MORTAR).qual(2, 14.0, 64, 2),
	Be_7    = alkaline      (   41, "Beryllium-7"           , "Be-7"    ,   4,   3,  1560,  2742,  1.85      , SET_METALLIC         , 110, 190, 110, 255).put(  G_INGOT_ORES                , FUSION                      ).aspects(TC.METALLUM, 2, TC.LUCRUM, 1, TC.RADIO, 1      ),
	Be_8    = alkaline      (   42, "Beryllium-8"           , "Be-8"    ,   4,   4,  1560,  2742,  1.85      , SET_METALLIC         , 110, 200, 110, 255).put(  G_INGOT_ORES                , FUSION                      ).aspects(TC.METALLUM, 2, TC.LUCRUM, 1, TC.RADIO, 1      ),
	B       = metalloid     (   50, "Boron"                 , "B"       ,   5,   5,  2349,  4200,  2.34      , SET_DULL             , 250, 250, 250, 255).put(  G_INGOT_ORES                , FUSION, UUM, ICOSAGEN       ).aspects(TC.METALLUM, 1, TC.VITREUS, 1, TC.ELECTRUM, 1  ),
	B_11    = metalloid     (   51, "Boron-11"              , "B-11"    ,   5,   6,  2349,  4200,  2.34      , SET_DULL             , 240, 240, 240, 255).put(  G_INGOT_ORES                , FUSION, ICOSAGEN            ).aspects(TC.METALLUM, 1, TC.VITREUS, 1, TC.RADIO, 1     ),
	C       = polyatomic    (   60, "Carbon"                , "C"       ,   6,   6,  3800,  4300,  2.267     , SET_FINE             ,  20,  20,  20, 255).put(  G_DUST_ORES                 , FUSION, UUM, CRYSTALLOGEN   ).aspects(TC.VITREUS, 1, TC.IGNIS, 1                     ).put(FLAMMABLE, MELTING, MOLTEN, STICKS),
	C_13    = polyatomic    (   61, "Carbon-13"             , "C-13"    ,   6,   7,  3800,  4300,  2.267     , SET_FINE             ,  25,  25,  25, 255).put(  G_DUST_ORES                 , FUSION, CRYSTALLOGEN        ).aspects(TC.VITREUS, 1, TC.IGNIS, 1, TC.RADIO, 1        ).put(FLAMMABLE, MELTING, MOLTEN),
	C_14    = polyatomic    (   62, "Carbon-14"             , "C-14"    ,   6,   8,  3800,  4300,  2.267     , SET_FINE             ,  30,  30,  30, 255).put(  G_DUST_ORES                 , FUSION, CRYSTALLOGEN        ).aspects(TC.VITREUS, 1, TC.IGNIS, 1, TC.RADIO, 1        ).put(FLAMMABLE, MELTING, MOLTEN),
	N       = diatomicgas   (   70, "Nitrogen"              , "N"       ,   7,   7,    63,    77,  0.0012506 , SET_DULL             ,   0, 150, 200,  15).put(  CONTAINERS_GAS              , FUSION, UUM, PNICTOGEN      ).aspects(TC.AER, 1                                      ),
	O       = diatomicgas   (   80, "Oxygen"                , "O"       ,   8,   8,    54,    90,  0.001429  , SET_DULL             ,   0, 100, 200,  15).put(  CONTAINERS_GAS              , FUSION, UUM, CHALCOGEN      ).aspects(TC.AER, 1                                      ),
	F       = diatomicgas   (   90, "Fluorine"              , "F"       ,   9,   9,    53,    85,  0.001696  , SET_DULL             ,  64, 192,   0, 255).put(  G_CRYSTAL_ORES              , FUSION, UUM, HALOGEN        ).aspects(TC.PERDITIO, 2                                 ),
	Ne      = noblegas      (  100, "Neon"                  , "Ne"      ,  10,  10,    24,    27,  0.0008999 , SET_SHINY            , 250, 180, 180,  15).put(  CONTAINERS_GAS              , FUSION, UUM                 ).aspects(TC.AER, 3                                      ),
	Na      = alkali        (  110, "Sodium"                , "Na"      ,  11,  11,   370,  1156,  0.971     , SET_ROUGH            ,   0,   0, 150, 255).put(  G_INGOT_ORES                , FUSION, UUM                 ).aspects(TC.VITREUS, 2, TC.LUX, 1                       ).put(TICKS_PER_SMELT*20, "Natrium"),
	Mg      = alkaline      (  120, "Magnesium"             , "Mg"      ,  12,  12,   923,  1363,  1.738     , SET_METALLIC         , 255, 200, 200, 255).put(  G_INGOT_ORES                , FUSION, UUM                 ).aspects(TC.METALLUM, 2, TC.SANO, 1                     ).put(FLAMMABLE),
	Al      = posttrans     (  130, "Aluminium"             , "Al"      ,  13,  13,   933,  2792,  2.698     , SET_METALLIC         , 128, 200, 240, 255).put(  G_INGOT_MACHINE_ORES        , FUSION, UUM, ICOSAGEN       ).aspects(TC.METALLUM, 2, TC.VOLATUS, 1                  ).qual(2, 10.0, 128, 2).put(RAILS, MOLTEN, "Aluminum", "NaturalAluminum"),
	Si      = metalloid     (  140, "Silicon"               , "Si"      ,  14,  14,  1687,  3538,  2.3296    , SET_METALLIC         ,  60,  60,  80, 255).put(  G_INGOT_ORES                , FUSION, UUM, CRYSTALLOGEN   ).aspects(TC.METALLUM, 2, TC.TENEBRAE, 1                 ).setPriorityPrefix(5),
	P       = polyatomic    (  150, "Phosphor"              , "P"       ,  15,  15,   317,   550,  1.82      , SET_FINE             , 255, 255,   0, 255).put(  G_CRYSTAL_ORES              , FUSION, UUM, PNICTOGEN      ).aspects(TC.IGNIS, 2, TC.POTENTIA, 1                    ).put(FLAMMABLE, EXPLOSIVE, BRITTLE, MORTAR),
	S       = polyatomic    (  160, "Sulfur"                , "S"       ,  16,  16,   388,   717,  2.067     , SET_FINE             , 234, 234,   0, 255).put(  G_CRYSTAL_ORES              , FUSION, UUM, CHALCOGEN      ).aspects(TC.IGNIS, 1                                    ).put(FLAMMABLE, MELTING, MOLTEN, BRITTLE, MORTAR, TICKS_PER_SMELT*5, "Sulphur").setPriorityPrefix(2),
	Cl      = diatomicgas   (  170, "Chlorine"              , "Cl"      ,  17,  18,   171,   239,  0.003214  , SET_DULL             ,   0, 240, 255, 255).put(  CONTAINERS_FLUID            , FUSION, UUM, HALOGEN        ).aspects(TC.AQUA, 2, TC.PANNUS, 1                       ),
	Ar      = noblegas      (  180, "Argon"                 , "Ar"      ,  18,  22,    83,    87,  0.0017837 , SET_SHINY            ,   0, 255,   0,  15).put(  CONTAINERS_GAS              , FUSION, UUM                 ).aspects(TC.AER, 3                                      ),
	K       = alkali        (  190, "Potassium"             , "K"       ,  19,  20,   336,  1032,  0.862     , SET_ROUGH            , 250, 250, 250, 255).put(  G_INGOT_ORES                , FUSION, UUM                 ).aspects(TC.VITREUS, 1, TC.POTENTIA, 1                  ).put("Kalium"),
	Ca      = alkaline      (  200, "Calcium"               , "Ca"      ,  20,  20,  1115,  1757,  1.54      , SET_METALLIC         , 255, 245, 245, 255).put(  G_INGOT_ORES                , FUSION, UUM                 ).aspects(TC.SANO, 1, TC.TUTAMEN, 1                      ),
	Sc      = transmetal    (  210, "Scandium"              , "Sc"      ,  21,  24,  1814,  3109,  2.989     , SET_METALLIC                             ).put(  G_INGOT_ORES                , FUSION, UUM, SCANDIUM_GROUP ).aspects(TC.METALLUM, 2, TC.GELUM, 1                    ),
	Ti      = refractmetal  (  220, "Titanium"              , "Ti"      ,  22,  26,  1941,  3560,  4.54      , SET_METALLIC         , 220, 160, 240, 255).put(  G_INGOT_MACHINE_ORES        , FUSION, UUM, TITANIUM_GROUP ).aspects(TC.METALLUM, 2, TC.TUTAMEN, 1                  ).qual(3,  8.0, 2560, 3).put(RAILS, MOLTEN, "Titan"),
	V       = refractmetal  (  230, "Vanadium"              , "V"       ,  23,  28,  2183,  3680,  6.11      , SET_METALLIC         ,  50,  50,  50, 255).put(  G_INGOT_ORES                , FUSION, UUM, VANADIUM_GROUP ).aspects(TC.METALLUM, 2, TC.ELECTRUM, 1                 ).put(MOLTEN),
	Cr      = refractmetal  (  240, "Chromium"              , "Cr"      ,  24,  28,  2180,  2944,  7.15      , SET_SHINY            , 255, 230, 230, 255).put(  G_INGOT_MACHINE_ORES        , FUSION, UUM, CHROMIUM_GROUP ).aspects(TC.METALLUM, 2, TC.MACHINA, 1                  ).qual(3, 11.0,  256, 3).put(MOLTEN, "Chrome"),
	Mn      = transmetal    (  250, "Manganese"             , "Mn"      ,  25,  30,  1519,  2334,  7.44      , SET_DULL             , 250, 250, 250, 255).put(  G_INGOT_ORES                , FUSION, UUM, MANGANESE_GROUP).aspects_met_rad(3, 0                                   ).qual(3,  7.0,  256, 2).put(MAGNETIC_PASSIVE, MOLTEN),
	Fe      = transmetal    (  260, "Iron"                  , "Fe"      ,  26,  30,  1811,  3134,  7.874     , SET_METALLIC         , 200, 200, 200, 255).put(  G_INGOT_MACHINE_ORES        , FUSION, UUM, IRON_GROUP     ).aspects_met_rad(3, 0                                   ).qual(3,  6.0,  256, 2).put(MOLTEN, RAILS, MORTAR, MAGNETIC_PASSIVE).setRGBaLiquid(255, 64, 32, 255),
	Co      = transmetal    (  270, "Cobalt"                , "Co"      ,  27,  32,  1768,  3200,  8.86      , SET_METALLIC         ,  80,  80, 250, 255).put(  G_INGOT_ORES                , UUM, COBALT_GROUP           ).aspects(TC.METALLUM, 2, TC.INSTRUMENTUM, 1             ).qual(3,  5.0,  256, 3).put(WASHING_PERSULFATE, MORTAR, MAGNETIC_PASSIVE, MOLTEN),
	Co_60   = transmetal    (  278, "Cobalt-60"             , "Co-60"   ,  27,  43,  1768,  3200,  8.86      , SET_SHINY            ,  90,  90, 250, 255).put(  G_INGOT_ORES                , COBALT_GROUP                ).aspects(TC.METALLUM, 1, TC.INSTRUMENTUM, 1, TC.RADIO, 1).qual(3,  5.0,  256, 3).put(WASHING_PERSULFATE, MORTAR, MAGNETIC_PASSIVE),
	Ni      = transmetal    (  280, "Nickel"                , "Ni"      ,  28,  30,  1728,  3186,  8.912     , SET_METALLIC         , 250, 250, 200, 255).put(  G_INGOT_ORES                , UUM, NICKEL_GROUP           ).aspects(TC.METALLUM, 2, TC.IGNIS, 1                    ).qual(2,  6.0,   64, 2).put(WASHING_PERSULFATE, MORTAR, MAGNETIC_PASSIVE, MOLTEN),
	Cu      = noblemetal    (  290, "Copper"                , "Cu"      ,  29,  34,  1357,  2835,  8.96      , SET_SHINY            , 255, 100,   0, 255).put(  G_INGOT_MACHINE_ORES        , UUM, COPPER_GROUP           ).aspects(TC.METALLUM, 2, TC.PERMUTATIO, 1               ).qual(2,  4.0,   64, 0).put(WASHING_PERSULFATE, FURNACE, EXTRUDER_SIMPLE, MORTAR, MOLTEN, RAILS),
	Zn      = transmetal    (  300, "Zinc"                  , "Zn"      ,  30,  35,   692,  1180,  7.134     , SET_METALLIC         , 250, 240, 240, 255).put(  G_INGOT_ORES                , UUM, ZINC_GROUP             ).aspects(TC.METALLUM, 2, TC.SANO, 1                     ).put(WASHING_PERSULFATE, WASHING_MERCURY, MOLTEN),
	Ga      = posttrans     (  310, "Gallium"               , "Ga"      ,  31,  39,   302,  2477,  5.907     , SET_DULL             , 220, 220, 255, 255).put(  G_INGOT_ORES                , UUM, ICOSAGEN               ).aspects(TC.METALLUM, 2, TC.ELECTRUM, 1                 ).put(BRITTLE),
	Ge      = metalloid     (  320, "Germanium"             , "Ge"      ,  32,  40,  1211,  3106,  5.323     , SET_SHINY            , 212, 212, 212, 255).put(  G_INGOT_MACHINE_ORES        , UUM, CRYSTALLOGEN           ).aspects(TC.METALLUM, 2, TC.ELECTRUM, 1                 ).put(FURNACE, EXTRUDER_SIMPLE, MORTAR),
	As      = metalloid     (  330, "Arsenic"               , "As"      ,  33,  42,   887,  1090,  5.776     , SET_SHINY            , 103, 103,  86, 255).put(  G_INGOT_ORES                , UUM, PNICTOGEN              ).aspects(TC.METALLUM, 1, TC.VENENUM, 2                  ).qual(2,  4.0,  128, 1),
	Se      = polyatomic    (  340, "Selenium"              , "Se"      ,  34,  45,   453,   958,  4.809     , SET_DULL             , 111,  20,  20, 255).put(  G_CRYSTAL_ORES              , UUM, CHALCOGEN              ).aspects(TC.VITREUS, 1, TC.SPIRITUS, 2                  ).put(BRITTLE),
	Br      = diatomic      (  350, "Bromine"               , "Br"      ,  35,  45,   265,   332,  3.122     , SET_FLUID            ,  80,  10,  10, 255).put(  CONTAINERS_FLUID            , UUM, HALOGEN                ).aspects(TC.METALLUM, 1, TC.AQUA, 1, TC.TEMPESTAS, 1    ).put(LIQUID),
	Kr      = noblegas      (  360, "Krypton"               , "Kr"      ,  36,  48,   115,   119,  0.003733  , SET_DIAMOND          , 128, 255, 128,  15).put(  CONTAINERS_GAS              , UUM                         ).aspects(TC.AER, 3                                      ),
	Rb      = alkali        (  370, "Rubidium"              , "Rb"      ,  37,  48,   312,   961,  1.532     , SET_SHINY            , 240,  30,  30, 255).put(  G_INGOT_ORES                , UUM                         ).aspects(TC.METALLUM, 2, TC.VITREUS, 1                  ),
	Sr      = alkaline      (  380, "Strontium"             , "Sr"      ,  38,  49,  1050,  1655,  2.64      , SET_METALLIC         , 200, 200, 200, 255).put(  G_INGOT_ORES                , UUM                         ).aspects(TC.METALLUM, 2, TC.STRONTIO, 1                 ),
	Y       = transmetal    (  390, "Yttrium"               , "Y"       ,  39,  50,  1799,  3609,  4.469     , SET_METALLIC         , 220, 250, 220, 255).put(  G_INGOT_ORES                , UUM, SCANDIUM_GROUP         ).aspects_met_rad(3, 0                                   ),
	Zr      = refractmetal  (  400, "Zirconium"             , "Zr"      ,  40,  51,  2128,  4682,  6.506     , SET_DIAMOND          , 200, 255, 255, 127).put(  G_GEM_ORES_TRANSPARENT      , UUM, TITANIUM_GROUP         ).aspects(TC.METALLUM, 2, TC.VITREUS, 1                  ).qual(3,  6.0,  512, 3).lens(DYE_INDEX_White).put(G_INGOT_ORES, CRYSTAL, VALUABLE),
	Nb      = refractmetal  (  410, "Niobium"               , "Nb"      ,  41,  53,  2750,  5017,  8.57      , SET_METALLIC         , 190, 180, 200, 255).put(  G_INGOT_ORES                , UUM, VANADIUM_GROUP         ).aspects(TC.METALLUM, 2, TC.IGNIS, 1                    ),
	Mo      = refractmetal  (  420, "Molybdenum"            , "Mo"      ,  42,  53,  2896,  4912, 10.22      , SET_SHINY            , 180, 180, 220, 255).put(  G_INGOT_ORES                , UUM, CHROMIUM_GROUP         ).aspects(TC.METALLUM, 2, TC.INSTRUMENTUM, 1             ).qual(3,  7.0,  512, 2).put(MOLTEN),
	Tc      = transmetal    (  430, "Technetium"            , "Tc"      ,  43,  55,  2430,  4538, 11.5       , SET_METALLIC         ,  66,  66,  99, 255).put(  G_INGOT_ORES                , MANGANESE_GROUP             ).aspects_met_rad(2, 1                                   ).qual(3, 10.0, 1280, 1), Gregorium=Tc.put("Gregorium"),
	Ru      = platingroup   (  440, "Ruthenium"             , "Ru"      ,  44,  57,  2607,  4423, 12.37      , SET_SHINY            , 155, 155, 155, 255).put(  G_INGOT_ORES                , UUM, IRON_GROUP             ).aspects_met_rad(3, 0                                   ),
	Rh      = platingroup   (  450, "Rhodium"               , "Rh"      ,  45,  58,  2237,  3968, 12.41      , SET_SHINY            , 144, 144, 144, 255).put(  G_INGOT_ORES                , UUM, COBALT_GROUP           ).aspects_met_rad(3, 0                                   ),
	Pd      = platingroup   (  460, "Palladium"             , "Pd"      ,  46,  60,  1828,  3236, 12.02      , SET_SHINY            , 128, 128, 128, 255).put(  G_INGOT_MACHINE_ORES        , UUM, NICKEL_GROUP           ).aspects_met_rad(3, 0                                   ).qual(3,  8.0,  512, 2),
	Ag      = noblemetal    (  470, "Silver"                , "Ag"      ,  47,  60,  1234,  2435, 10.501     , SET_SHINY            , 220, 220, 255, 255).put(  G_INGOT_MACHINE_ORES        , UUM, COPPER_GROUP           ).aspects(TC.METALLUM, 2, TC.LUCRUM, 1                   ).qual(3, 10.0,   64, 2).put(RAILS, WASHING_MERCURY, MORTAR, MOLTEN, VALUABLE, ENDER_DRAGON_PROOF),
	Cd      = transmetal    (  480, "Cadmium"               , "Cd"      ,  48,  64,   594,  1040,  8.69      , SET_SHINY            ,  50,  50,  60, 255).put(  G_INGOT_ORES                , UUM, ZINC_GROUP             ).aspects(TC.METALLUM, 1, TC.POTENTIA, 1, TC.VENENUM, 1  ),
	In      = posttrans     (  490, "Indium"                , "In"      ,  49,  65,   429,  2345,  7.31      , SET_SHINY            ,  64,   0, 128, 255).put(  G_INGOT_ORES                , UUM, ICOSAGEN               ).aspects_met_rad(3, 0                                   ),
	Sn      = posttrans     (  500, "Tin"                   , "Sn"      ,  50,  68,   505,  2875,  7.287     , SET_DULL             , 220, 220, 220, 255).put(  G_INGOT_MACHINE_ORES        , UUM, CRYSTALLOGEN           ).aspects(TC.METALLUM, 2, TC.VITREUS, 1                  ).put(SOLDERING_MATERIAL, MOLTEN),
	Sb      = metalloid     (  510, "Antimony"              , "Sb"      ,  51,  70,   903,  1860,  6.685     , SET_SHINY            , 220, 220, 240, 255).put(  G_INGOT_MACHINE_ORES        , UUM, PNICTOGEN              ).aspects(TC.METALLUM, 2, TC.AQUA, 1                     ),
	Te      = metalloid     (  520, "Tellurium"             , "Te"      ,  52,  75,   722,  1261,  6.232     , SET_SHINY                                ).put(  G_INGOT_ORES                , UUM, CHALCOGEN              ).aspects(TC.METALLUM, 2, TC.ELECTRUM, 1                 ),
	I       = diatomic      (  530, "Iodine"                , "I"       ,  53,  74,   386,   457,  4.93      , SET_DULL             , 255, 240, 240, 255).put(  G_CRYSTAL_ORES              , UUM, HALOGEN                ).aspects(TC.VITREUS, 2, TC.TEMPESTAS, 1                 ),
	Xe      = noblegas      (  540, "Xenon"                 , "Xe"      ,  54,  77,   161,   165,  0.005887  , SET_DULL             ,   0, 255, 255,  15).put(  CONTAINERS_GAS              , UUM                         ).aspects(TC.AER, 3                                      ),
	Cs      = alkali        (  550, "Caesium"               , "Cs"      ,  55,  77,   301,   944,  1.873     , SET_SHINY            , 128,  98,  11, 255).put(  G_INGOT_ORES                , UUM                         ).aspects_met_rad(3, 0                                   ),
	Ba      = alkaline      (  560, "Barium"                , "Ba"      ,  56,  81,  1000,  2170,  3.594     , SET_METALLIC                             ).put(  G_INGOT_ORES                , UUM                         ).aspects(TC.VINCULUM, 3                                 ),
	La      = lanthanide    (  570, "Lanthanum"             , "La"      ,  57,  81,  1193,  3737,  6.145     , SET_METALLIC         ,  93, 117, 117, 255).put(  G_INGOT_ORES                , UUM                         ).aspects_met_rad(3, 0                                   ).put("Lantanum", "Lantanium", "Lanthanium"),
	Ce      = lanthanide    (  580, "Cerium"                , "Ce"      ,  58,  82,  1068,  3716,  6.77      , SET_SHINY            , 255, 255, 190, 255).put(  G_INGOT_ORES                , UUM                         ).aspects_met_rad(3, 0                                   ),
	Pr      = lanthanide    (  590, "Praseodymium"          , "Pr"      ,  59,  81,  1208,  3793,  6.773     , SET_METALLIC                             ).put(  G_INGOT_ORES                , UUM                         ).aspects_met_rad(3, 0                                   ),
	Nd      = lanthanide    (  600, "Neodymium"             , "Nd"      ,  60,  84,  1297,  3347,  7.007     , SET_SHINY            , 100, 100, 100, 255).put(  G_INGOT_ORES                , UUM                         ).aspects(TC.METALLUM, 2, TC.MAGNETO, 2                  ).qual(3, 6.0, 512, 3).put(MAGNETIC_PASSIVE, MOLTEN),
	Pm      = lanthanide    (  610, "Promethium"            , "Pm"      ,  61,  83,  1315,  3273,  7.26      , SET_SHINY                                ).put(  G_INGOT_ORES                                              ).aspects_met_rad(1, 2                                   ),
	Sm      = lanthanide    (  620, "Samarium"              , "Sm"      ,  62,  88,  1345,  2067,  7.52      , SET_METALLIC                             ).put(  G_INGOT_ORES                , UUM                         ).aspects_met_rad(3, 0                                   ),
	Eu      = lanthanide    (  630, "Europium"              , "Eu"      ,  63,  88,  1099,  1802,  5.243     , SET_METALLIC                             ).put(  G_INGOT_ORES                , UUM                         ).aspects_met_rad(3, 0                                   ),
	Gd      = lanthanide    (  640, "Gadolinium"            , "Gd"      ,  64,  93,  1585,  3546,  7.895     , SET_METALLIC                             ).put(  G_INGOT_ORES                , UUM                         ).aspects_met_rad(3, 0                                   ),
	Tb      = lanthanide    (  650, "Terbium"               , "Tb"      ,  65,  93,  1629,  3503,  8.229     , SET_METALLIC                             ).put(  G_INGOT_ORES                , UUM                         ).aspects_met_rad(3, 0                                   ),
	Dy      = lanthanide    (  660, "Dysprosium"            , "Dy"      ,  66,  96,  1680,  2840,  8.55      , SET_METALLIC                             ).put(  G_INGOT_ORES                , UUM                         ).aspects_met_rad(3, 0                                   ),
	Ho      = lanthanide    (  670, "Holmium"               , "Ho"      ,  67,  97,  1734,  2993,  8.795     , SET_METALLIC                             ).put(  G_INGOT_ORES                , UUM                         ).aspects_met_rad(3, 0                                   ),
	Er      = lanthanide    (  680, "Erbium"                , "Er"      ,  68,  99,  1802,  3141,  9.066     , SET_METALLIC                             ).put(  G_INGOT_ORES                , UUM                         ).aspects_met_rad(3, 0                                   ),
	Tm      = lanthanide    (  690, "Thulium"               , "Tm"      ,  69,  99,  1818,  2223,  9.321     , SET_METALLIC                             ).put(  G_INGOT_ORES                , UUM                         ).aspects_met_rad(3, 0                                   ),
	Yb      = lanthanide    (  700, "Ytterbium"             , "Yb"      ,  70, 103,  1097,  1469,  6.965     , SET_METALLIC         , 167, 167, 167, 255).put(  G_INGOT_ORES                , UUM                         ).aspects_met_rad(3, 0                                   ),
	Lu      = lanthanide    (  710, "Lutetium"              , "Lu"      ,  71, 103,  1925,  3675,  9.84      , SET_METALLIC                             ).put(  G_INGOT_ORES                , UUM, SCANDIUM_GROUP         ).aspects_met_rad(3, 0                                   ),
	Hf      = refractmetal  (  720, "Hafnium"               , "Hf"      ,  72, 106,  2506,  4876, 13.31      , SET_METALLIC         , 140, 140, 150, 255).put(  G_INGOT_ORES                , UUM, TITANIUM_GROUP         ).aspects_met_rad(3, 0                                   ).put(FLAMMABLE, EXPLOSIVE),
	Ta      = refractmetal  (  730, "Tantalum"              , "Ta"      ,  73, 107,  3290,  5731, 16.654     , SET_METALLIC         , 120, 120, 140, 255).put(  G_INGOT_ORES                , UUM, VANADIUM_GROUP         ).aspects(TC.METALLUM, 2, TC.VINCULUM, 1                 ),
	W       = refractmetal  (  740, "Tungsten"              , "W"       ,  74, 109,  3695,  5828, 19.25      , SET_METALLIC         ,  50,  50,  50, 255).put(  G_INGOT_MACHINE_ORES        , UUM, CHROMIUM_GROUP         ).aspects(TC.METALLUM, 3, TC.TUTAMEN, 1                  ).qual(3,  8.0, 5120, 3).put(MOLTEN, RAILS, "Wolframium", "Wolfram", "ElnTungsten"),
	Re      = precmetal     (  750, "Rhenium"               , "Re"      ,  75, 111,  3459,  5869, 21.02      , SET_SHINY            , 255, 255, 200, 255).put(  G_INGOT_ORES                , UUM, MANGANESE_GROUP        ).aspects_met_rad(3, 0                                   ),
	Os      = platingroup   (  760, "OsmiumElemental"       , "Os"      ,  76, 114,  3306,  5285, 22.61      , SET_METALLIC         ,  50,  50, 255, 255).put(  G_INGOT_MACHINE_ORES        , UUM, IRON_GROUP             ).aspects(TC.METALLUM, 2, TC.MACHINA, 1, TC.NEBRISUM, 1  ).qual(3, 16.0, 1280, 4).put(MOLTEN, VALUABLE).setLocal("Osmium"),
	Ir      = platingroup   (  770, "Iridium"               , "Ir"      ,  77, 115,  2719,  4701, 22.56      , SET_DULL             , 240, 240, 245, 255).put(  G_INGOT_MACHINE_ORES        , UUM, COBALT_GROUP           ).aspects(TC.METALLUM, 2, TC.MACHINA, 1                  ).qual(3,  6.0, 5120, 4).put(MOLTEN, VALUABLE).setRGBaLiquid(255, 128, 200, 255),
	Pt      = platingroup   (  780, "Platinum"              , "Pt"      ,  78, 117,  2041,  4098, 21.46      , SET_SHINY            , 100, 180, 250, 255).put(  G_INGOT_MACHINE_ORES        , UUM, NICKEL_GROUP           ).aspects(TC.METALLUM, 2, TC.NEBRISUM, 1                 ).qual(3, 15.0, 64, 2).put(WASHING_MERCURY, RAILS, MORTAR, MOLTEN, VALUABLE),
	Au      = noblemetal    (  790, "Gold"                  , "Au"      ,  79, 117,  1337,  3129, 19.282     , SET_SHINY            , 255, 230,  80, 255).put(  G_INGOT_MACHINE_ORES        , UUM, COPPER_GROUP           ).aspects(TC.METALLUM, 2, TC.LUCRUM, 2                   ).qual(3, 12.5, 64, 2).put(WASHING_MERCURY, RAILS, MORTAR, MOLTEN, VALUABLE, WITHER_PROOF),
	Hg      = precmetal     (  800, "Mercury"               , "Hg"      ,  80, 120,   234,   629, 13.5336    , SET_SHINY            , 230, 220, 220, 255).put(  G_INGOT_ORES                , UUM, ZINC_GROUP             ).aspects(TC.METALLUM, 1, TC.AQUA, 1, TC.VENENUM, 1      ).put("Quicksilver", "QuickSilver"),
	Tl      = posttrans     (  810, "Thallium"              , "Tl"      ,  81, 123,   577,  1746, 11.85      , SET_METALLIC                             ).put(  G_INGOT_ORES                , UUM, ICOSAGEN               ).aspects_met_rad(3, 0                                   ),
	Pb      = posttrans     (  820, "Lead"                  , "Pb"      ,  82, 125,   600,  2022, 11.342     , SET_DULL             ,  60,  40, 110, 255).put(  G_INGOT_MACHINE_ORES        , UUM, CRYSTALLOGEN           ).aspects(TC.METALLUM, 2, TC.ORDO, 1                     ).qual(2,  8.0, 64, 1).put(SOLDERING_MATERIAL, SOLDERING_MATERIAL_BAD, MOLTEN),
	Bi      = posttrans     (  830, "Bismuth"               , "Bi"      ,  83, 125,   544,  1837,  9.807     , SET_METALLIC         , 100, 160, 160, 255).put(  G_INGOT_MACHINE_ORES        , PNICTOGEN                   ).aspects(TC.METALLUM, 2, TC.MAGNETO, 1                  ).qual(2,  6.0, 64, 1).put(MAGNETIC_PASSIVE, MOLTEN),
	Po      = posttrans     (  840, "Polonium"              , "Po"      ,  84, 124,   527,  1235,  9.32      , SET_METALLIC                             ).put(  G_INGOT_ORES                , CHALCOGEN                   ).aspects_met_rad(2, 1                                   ),
	At      = metalloid     (  850, "Astatine"              , "At"      ,  85, 124,   575,   610,  7.0       , SET_METALLIC         ,  33,  33,  33, 255).put(  G_INGOT_ORES, CONTAINERS_GAS, HALOGEN                     ).aspects(TC.POTENTIA, 2, TC.RADIO, 1                    ).put(GASSES),
	Rn      = noblegas      (  860, "Radon"                 , "Rn"      ,  86, 134,   202,   211,  0.00973   , SET_DULL             , 255,   0, 255,  15).put(  CONTAINERS_GAS                                            ).aspects(TC.AER, 2, TC.RADIO, 1                         ),
	Fr      = alkali        (  870, "Francium"              , "Fr"      ,  87, 134,   300,   950,  1.87      , SET_METALLIC                             ).put(  G_INGOT_ORES                                              ).aspects_met_rad(2, 1                                   ),
	Ra      = alkaline      (  880, "Radium"                , "Ra"      ,  88, 136,   973,  2010,  5.5       , SET_METALLIC         , 255, 255, 205, 255).put(  G_INGOT_ORES                                              ).aspects_met_rad(2, 1                                   ),
	Ac      = actinide      (  890, "Actinium"              , "Ac"      ,  89, 136,  1323,  3471, 10.07      , SET_METALLIC         , 125, 113, 113, 255).put(  G_INGOT_ORES                                              ).aspects_met_rad(2, 1                                   ),
	Th      = actinide      (  900, "Thorium"               , "Th"      ,  90, 142,  2115,  5061, 11.72      , SET_SHINY            ,   0,  30,   0, 255).put(  G_INGOT_ORES                                              ).aspects_met_rad(2, 1                                   ).qual(3, 6.0, 512, 2).put("Thorium232"),
	Pa      = actinide      (  910, "Protactinium"          , "Pa"      ,  91, 138,  1841,  4300, 15.37      , SET_METALLIC                             ).put(  G_INGOT_ORES                                              ).aspects_met_rad(2, 1                                   ),
	U_238   = actinide      (  920, "Uranium"               , "U"       ,  92, 146,  1405,  4404, 18.95      , SET_METALLIC         ,  50, 240,  50, 255).put(  G_INGOT_ORES                                              ).aspects_met_rad(2, 1                                   ).qual(3, 6.0, 512, 3).put(MELTING, MOLTEN, "Uranium238", "Uran"), // No, I could not make a Variable named "U" here. That would collide with the Material Unit.
	U_235   = actinide      (  921, "Uranium-235"           , "U-235"   ,  92, 143,  1405,  4404, 18.95      , SET_SHINY            ,  70, 250,  70, 255).put(  G_INGOT_ORES                                              ).aspects_met_rad(1, 2                                   ).qual(3, 6.0, 512, 3).put(MELTING, MOLTEN, "UraniumEnriched"),
	U_233   = actinide      (  922, "Uranium-233"           , "U-233"   ,  92, 141,  1405,  4404, 18.95      , SET_SHINY            ,  70, 250,  50, 255).put(  G_INGOT_ORES                                              ).aspects_met_rad(1, 3                                   ).qual(3, 6.0, 512, 3),
	Np      = actinide      (  930, "Neptunium"             , "Np"      ,  93, 144,   917,  4273, 20.45      , SET_DULL             ,  78,  90,  78, 255).put(  G_INGOT_ORES                                              ).aspects_met_rad(2, 1                                   ),
	Pu      = actinide      (  940, "Plutonium"             , "Pu"      ,  94, 150,   912,  3501, 19.84      , SET_METALLIC         , 240,  50,  50, 255).put(  G_INGOT_ORES                                              ).aspects_met_rad(2, 1                                   ).qual(3, 6.0, 512, 3).put("Plutonium244"),
	Pu_241  = actinide      (  943, "Plutonium-241"         , "Pu-241"  ,  94, 147,   912,  3501, 19.84      , SET_SHINY            , 245,  70,  70, 255).put(  G_INGOT_ORES                                              ).aspects_met_rad(1, 3                                   ).qual(3, 6.0, 512, 3),
	Pu_243  = actinide      (  945, "Plutonium-243"         , "Pu-243"  ,  94, 149,   912,  3501, 19.84      , SET_SHINY            , 250,  70,  70, 255).put(  G_INGOT_ORES                                              ).aspects_met_rad(1, 2                                   ).qual(3, 6.0, 512, 3),
	Pu_239  = actinide      (  947, "Plutonium-239"         , "Pu-239"  ,  94, 145,   912,  3501, 19.84      , SET_SHINY            , 235,  50,  50, 255).put(  G_INGOT_ORES                                              ).aspects_met_rad(1, 3                                   ).qual(3, 6.0, 512, 3),
	Am      = actinide      (  950, "Americium"             , "Am"      ,  95, 150,  1449,  2880, 13.69      , SET_METALLIC         , 200, 200, 200, 255).put(  G_INGOT_ORES                                              ).aspects_met_rad(2, 1                                   ).qual(3, 4.0, 256, 2),
	Am_241  = actinide      (  951, "Americium-241"         , "Am-241"  ,  95, 146,  1449,  2880, 13.69      , SET_SHINY            , 210, 210, 210, 255).put(  G_INGOT_ORES                                              ).aspects_met_rad(1, 3                                   ).qual(3, 4.0, 256, 2),
	Cm      = actinide      (  960, "Curium"                , "Cm"      ,  96, 153,  1613,  3383, 13.51      , SET_METALLIC                             ).put(  G_INGOT_ORES                                              ).aspects_met_rad(2, 1                                   ),
	Bk      = actinide      (  970, "Berkelium"             , "Bk"      ,  97, 152,  1259,  2900, 14.79      , SET_METALLIC                             ).put(  G_INGOT_ORES                                              ).aspects_met_rad(2, 1                                   ),
	Cf      = actinide      (  980, "Californium"           , "Cf"      ,  98, 153,  1173,  1743, 15.1       , SET_METALLIC                             ).put(  G_INGOT_ORES                                              ).aspects_met_rad(2, 1                                   ),
	Es      = actinide      (  990, "Einsteinium"           , "Es"      ,  99, 153,  1133,  1269,  8.84      , SET_METALLIC                             ).put(  G_INGOT_ORES                                              ).aspects_met_rad(2, 1                                   ),
	Fm      = actinide      ( 1000, "Fermium"               , "Fm"      , 100, 157,  1125, 3000 ,           0, SET_METALLIC                             ).put(  G_INGOT_ORES                                              ).aspects_met_rad(1, 2                                   ),
	Md      = actinide      ( 1010, "Mendelevium"           , "Md"      , 101, 157,  1100, 3000 ,           0, SET_METALLIC                             ).put(  G_INGOT_ORES                                              ).aspects_met_rad(1, 2                                   ),
	No      = actinide      ( 1020, "Nobelium"              , "No"      , 102, 157,  1100, 3000 ,           0, SET_METALLIC                             ).put(  G_INGOT_ORES                                              ).aspects_met_rad(1, 2                                   ),
	Lr      = actinide      ( 1030, "Lawrencium"            , "Lr"      , 103, 159,  1900, 3000 ,           0, SET_METALLIC                             ).put(  G_INGOT_ORES                , SCANDIUM_GROUP              ).aspects_met_rad(1, 2                                   ),
	Rf      = transmetal    ( 1040, "Rutherfordium"         , "Rf"      , 104, 161,  2400,  5800, 23.2       , SET_METALLIC                             ).put(  G_INGOT_ORES                , TITANIUM_GROUP              ).aspects_met_rad(1, 2                                   ),
	Db      = transmetal    ( 1050, "Dubnium"               , "Db"      , 105, 163, 1000 , 3000 , 29.3       , SET_METALLIC                             ).put(  G_INGOT_ORES                , VANADIUM_GROUP              ).aspects_met_rad(1, 2                                   ),
	Sg      = transmetal    ( 1060, "Seaborgium"            , "Sg"      , 106, 165, 1000 , 3000 , 35.0       , SET_METALLIC                             ).put(  G_INGOT_ORES                , CHROMIUM_GROUP              ).aspects_met_rad(1, 2                                   ),
	Bh      = transmetal    ( 1070, "Bohrium"               , "Bh"      , 107, 163, 1000 , 3000 , 37.1       , SET_METALLIC                             ).put(  G_INGOT_ORES                , MANGANESE_GROUP             ).aspects_met_rad(1, 2                                   ),
	Hs      = transmetal    ( 1080, "Hassium"               , "Hs"      , 108, 169, 1000 , 3000 , 40.7       , SET_METALLIC                             ).put(  G_INGOT_ORES                , IRON_GROUP                  ).aspects_met_rad(1, 2                                   ),
	Mt      = element       ( 1090, "Meitnerium"            , "Mt"      , 109, 167, 1000 , 3000 , 37.4       , SET_METALLIC                             ).put(  G_INGOT_ORES                , COBALT_GROUP                ).aspects_met_rad(1, 2                                   ),
	Ds      = element       ( 1100, "Darmstadtium"          , "Ds"      , 110, 171, 1000 , 3000 , 34.8       , SET_METALLIC                             ).put(  G_INGOT_ORES                , NICKEL_GROUP                ).aspects_met_rad(1, 2                                   ),
	Rg      = element       ( 1110, "Roentgenium"           , "Rg"      , 111, 169, 1000 , 3000 , 28.7       , SET_METALLIC                             ).put(  G_INGOT_ORES                , COPPER_GROUP                ).aspects_met_rad(1, 2                                   ),
	Cn      = transmetal    ( 1120, "Copernicium"           , "Cn"      , 112, 173,  150 ,   357, 23.7       , SET_METALLIC                             ).put(  G_INGOT_ORES                , ZINC_GROUP                  ).aspects_met_rad(1, 2                                   ),
	Nh      = element       ( 1130, "Nihonium"              , "Nh"      , 113, 171,   700,  1400, 16.0       , SET_METALLIC                             ).put(  G_INGOT_ORES                , ICOSAGEN                    ).aspects_met_rad(1, 2                                   ),
	Fl      = posttrans     ( 1140, "Flerovium"             , "Fl"      , 114, 175,   340,   420, 14.0       , SET_METALLIC                             ).put(  G_INGOT_ORES                , CRYSTALLOGEN                ).aspects_met_rad(1, 3                                   ),
	Fl_298  = posttrans     ( 1148, "Flerovium-298"         , "Fl-298"  , 114, 184,   340,   420, 14.0       , SET_SHINY                                ).put(  G_INGOT_ORES                , CRYSTALLOGEN                ).aspects_met_rad(1, 2                                   ),
	Mc      = element       ( 1150, "Moscovium"             , "Mc"      , 115, 174,   700,  1400, 13.5       , SET_METALLIC                             ).put(  G_INGOT_ORES                , PNICTOGEN                   ).aspects_met_rad(1, 2                                   ),
	Lv      = element       ( 1160, "Livermorium"           , "Lv"      , 116, 177,   708,  1085, 12.9       , SET_METALLIC                             ).put(  G_INGOT_ORES                , CHALCOGEN                   ).aspects_met_rad(1, 2                                   ),
	Fa      = element       ( 1170, "Farnsium"              , "Fa"      , 117, 177,   673,   823,  7.2       , SET_SHINY            ,  60,  70,  80, 255).put(  G_INGOT_ORES                , HALOGEN                     ).aspects_met_rad(1, 2                                   ).put("Tennessine"), Ts = Fa,
	Og      = element       ( 1180, "Oganesson"             , "Og"      , 118, 176,   258,   263,  5.0       , SET_METALLIC                             ).put(  G_INGOT_ORES                                              ).aspects_met_rad(1, 2                                   ),
	Uue     = element       ( 1190, "Ununennium"            , "Uue"     , 119, 178,   290,   903,           0, SET_METALLIC                             ).put(  G_INGOT_ORES                                              ).aspects_met_rad(1, 2                                   ),
	Ubn     = element       ( 1200, "Unbinilium"            , "Ubn"     , 120, 180,   953,  1973,           0, SET_METALLIC                             ).put(  G_INGOT_ORES                                              ).aspects_met_rad(1, 2                                   ),
	
	// The following Data is in no way reliable, but it is there in case someone wants to use it.
	Ubu     = unknown( 1210,  182),
	Ubb     = unknown( 1220,  184),
	Ubt     = unknown( 1230,  186),
	Ubq     = unknown( 1240,  188),
	Tn      = element( 1250, "TritaniumElemental"    , "Tn"      , 125, 198, 1500, 2500, 25.0     , SET_DULL     ,  55, 155, 155, 255).put(G_DUST_ORES, UUM, METAL, SMITHABLE, MELTING, MOLTEN, EXTRUDER, "Unbipentium").aspects(TC.METALLUM, 2, TC.TUTAMEN, 2, TC.VITREUS, 1).setLocal("Elemental Tritanium"), Ubp = Tn,
	Ke      = element( 1260, "Trinium"               , "Ke"      , 126, 192, 2645, 4523,  1.06874 , SET_METALLIC , 234, 234, 234, 255).put(G_INGOT_MACHINE_ORES, UUM, METAL, SMITHABLE, MELTING, MOLTEN, EXTRUDER, "Unbihexium").aspects(TC.METALLUM, 3, TC.TUTAMEN, 1, TC.VITREUS, 1).qual(3, 12.0, 2560, 4), Ubh = Ke,
	Ubs     = unknown( 1270,  194),
	Ubo     = unknown( 1280,  196),
	Ube     = unknown( 1290,  198),
	Utn     = unknown( 1300,  200),
	Utu     = unknown( 1310,  203),
	Utb     = unknown( 1320,  206),
	Utt     = unknown( 1330,  209),
	Utq     = unknown( 1340,  212),
	Utp     = unknown( 1350,  215),
	Uth     = unknown( 1360,  218),
	Uts     = unknown( 1370,  221),
	Uto     = unknown( 1380,  224),
	Ute     = unknown( 1390,  227),
	Uqn     = unknown( 1400,  230),
	Uqu     = unknown( 1410,  233),
	Uqb     = unknown( 1420,  236),
	Uqt     = unknown( 1430,  239),
	Uqq     = unknown( 1440,  242),
	Dn      = element( 1450, "DuraniumElemental"     , "Dn"      , 145, 190,  1200, 2100, 20.0, SET_DULL,  75, 175, 175, 255).put(G_DUST_ORES, UUM, METAL, SMITHABLE, MELTING, MOLTEN, EXTRUDER, "Unquadpentium").aspects(TC.METALLUM, 2, TC.TUTAMEN, 1).setLocal("Elemental Duranium"), Uqp = Dn, // material refined from dolamide ore
	Uqh     = unknown( 1460,  248),
	Uqs     = unknown( 1470,  251),
	Uqo     = unknown( 1480,  254),
	Uqe     = unknown( 1490,  257),
	Upn     = unknown( 1500,  260),
	Upu     = unknown( 1510,  263),
	Vb      = element( 1520, "Vibranium"             , "Vb"      , 152, 266,  4852, 9415, 3.23978365, SET_EMERALD, 200, 128, 255, 100).put(G_GEM_ORES_TRANSPARENT, UUM, VALUABLE, GLOWING, "Unpentbium").aspects(TC.VITREUS, 10, TC.SENSUS, 10).setPriorityPrefix(1).qual(3, 1000.0, 512, 15), Upb = Vb,
	Upt     = unknown( 1530,  269),
	Upq     = unknown( 1540,  272),
	Upp     = unknown( 1550,  276),
	Uph     = unknown( 1560,  280),
	Ups     = unknown( 1570,  284),
	Upo     = unknown( 1580,  288),
	Upe     = unknown( 1590,  292),
	Uhn     = unknown( 1600,  296),
	Uhu     = unknown( 1610,  300),
	Uhb     = unknown( 1620,  304),
	Uht     = unknown( 1630,  308),
	Uhq     = unknown( 1640,  312),
	Uhp     = unknown( 1650,  316),
	Uhh     = unknown( 1660,  320),
	Uhs     = unknown( 1670,  324),
	Uho     = unknown( 1680,  328),
	Uhe     = unknown( 1690,  332),
	Usn     = unknown( 1700,  336),
	Usu     = unknown( 1710,  340),
	Usb     = unknown( 1720,  344),
	Ust     = unknown( 1730,  348),
	Nq      = element( 1740, "Naquadah"              , "Nq"      , 174, 352, 1500, 3000, 21.0, SET_METALLIC  ,  50,  50,  50, 255).put(G_INGOT_MACHINE_ORES, UUM, METAL, SMITHABLE, MELTING, MOLTEN, EXTRUDER, "Unseptquadium").aspects(TC.METALLUM, 3, TC.RADIO, 1, TC.NEBRISUM, 1).setRGBaLiquid(  0, 255,   0, 255).qual(3, 6.0, 1280, 4), Usq = Nq,
	Nq_528  = element( 1741, "Naquadah-Enriched"     , "Nq-528"  , 174, 354, 1500, 3000, 22.0, SET_METALLIC  ,  60,  60,  60, 255).put(G_INGOT_ORES, METAL, SMITHABLE, MELTING, MOLTEN, EXTRUDER, EXPLOSIVE).aspects(TC.METALLUM, 3, TC.RADIO, 2, TC.NEBRISUM, 2).setRGBaLiquid( 64, 255,  64, 255).setLocal("Enriched Naquadah").qual(3, 6.0, 1280, 4),
	Nq_522  = element( 1742, "Naquadria"             , "Nq-522"  , 174, 348, 1500, 3000, 20.0, SET_SHINY     ,  30,  30,  30, 255).put(G_INGOT_ORES, METAL, SMITHABLE, MELTING, MOLTEN, EXTRUDER, EXPLOSIVE).aspects(TC.METALLUM, 4, TC.RADIO, 3, TC.NEBRISUM, 3).setRGBaLiquid(128, 255, 128, 255).qual(3, 1.0, 512, 4),
	Usp     = unknown( 1750,  356),
	Ush     = unknown( 1760,  360),
	Uss     = unknown( 1770,  364),
	Uso     = unknown( 1780,  368),
	Use     = unknown( 1790,  372),
	Uon     = unknown( 1800,  376),
	Uou     = unknown( 1810,  380),
	Uob     = unknown( 1820,  384),
	Uot     = unknown( 1830,  388),
	Uoq     = unknown( 1840,  392),
	An      = element( 1850, "Abyssalnite"           , "An"      , 185, 396, 1500, 3000, 15.0, SET_METALLIC  ,  90,  40, 170, 255).qual(3, 8.0, 1600, 3).put(G_INGOT_MACHINE_ORES, UUM, METAL, SMITHABLE, MELTING, MOLTEN, EXTRUDER, "Unoctpentium").aspects(TC.METALLUM, 3, TC.VACUOS, 2), Uop = An,
	Cor     = element( 1860, "Coralium"              , "Cor"     , 186, 400, 2000, 4000, 20.0, SET_RUBY      ,  20, 160, 110, 255).qual(3,12.0, 3200, 3).put(G_INGOT_MACHINE_ORES, UUM, METAL, SMITHABLE, MELTING, MOLTEN, EXTRUDER, G_GEM_ORES, "Unocthexium", "LiquifiedCoralium").aspects(TC.METALLUM, 3, TC.PERDITIO, 2), Uoh = Cor,
	Dr      = element( 1870, "Dreadium"              , "Dr"      , 187, 405, 2500, 5000, 25.0, SET_METALLIC  , 170,   0,   0, 255).qual(3,16.0, 4800, 4).put(G_INGOT_MACHINE_ORES, UUM, METAL, SMITHABLE, MELTING, MOLTEN, EXTRUDER, "Unoctseptium").aspects(TC.METALLUM, 3, TC.TENEBRAE, 2), Uos = Dr,
	Etx     = element( 1880, "Ethaxium"              , "Etx"     , 188, 410, 3000, 6000, 30.0, SET_METALLIC  , 160, 170, 150, 255).qual(3,20.0, 6400, 4).put(G_INGOT_MACHINE_ORES, UUM, METAL, SMITHABLE, MELTING, MOLTEN, EXTRUDER, "Unoctoctium").aspects(TC.METALLUM, 3, TC.VITIUM, 2), Uoo = Etx,
	Uoe     = unknown( 1890,  415),
	Uen     = unknown( 1900,  420),
	Ueu     = unknown( 1910,  425),
	Ueb     = unknown( 1920,  430),
	Uet     = unknown( 1930,  435),
	Ueq     = unknown( 1940,  440),
	Uep     = unknown( 1950,  445),
	Ueh     = unknown( 1960,  450),
	Ues     = unknown( 1970,  455),
	Ueo     = unknown( 1980,  460),
	Uee     = unknown( 1990,  465),
	Bnn     = unknown( 2000,  470),
	Bnu     = unknown( 2010,  475),
	Bnb     = unknown( 2020,  480),
	Bnt     = unknown( 2030,  485),
	Bnq     = unknown( 2040,  490),
	Bnp     = unknown( 2050,  495),
	Bnh     = unknown( 2060,  500),
	Bns     = unknown( 2070,  505),
	Bno     = unknown( 2080,  510),
	Bne     = unknown( 2090,  515),
	Bun     = unknown( 2100,  520),
	Buu     = unknown( 2110,  525),
	Bub     = unknown( 2120,  530),
	But     = unknown( 2130,  535),
	Buq     = unknown( 2140,  540),
	Bup     = unknown( 2150,  545),
	Buh     = unknown( 2160,  550),
	Bus     = unknown( 2170,  555),
	Buo     = unknown( 2180,  560),
	Bue     = unknown( 2190,  565),
	Bbn     = unknown( 2200,  570),
	Atl     = element( 2210, "Atlarus"               , "Atl"     , 221, 575, 3276, 11524, 21.24625421, SET_METALLIC, 204, 179,   0, 255).qual(3,  4.0, 4480, 4).put(G_INGOT_MACHINE_ORES, UUM, SMITHABLE, MELTING, MOLTEN, EXTRUDER, MAGICAL, "Bibiunium").aspects(TC.METALLUM, 2, TC.COGNITO, 1), Bbu = Atl, Atlarus = Atl,
	Ad      = element( 2220, "Adamantium"            , "Ad"      , 222, 580, 5225, 14528, 13.35624762, SET_SHINY   , 255, 255, 255, 255).qual(3, 10.0, 5120, 5).put(G_INGOT_MACHINE_ORES, UUM, SMITHABLE, MELTING, MOLTEN, EXTRUDER, MAGICAL, MAGNETIC_PASSIVE, WITHER_PROOF, ENDER_DRAGON_PROOF, "Adamant", "Bibibium").aspects(TC.METALLUM, 10, TC.PRAECANTIO, 10), Bbb = Ad,
	Bbt     = unknown( 2230,  585),
	Bbq     = unknown( 2240,  590),
	Bbp     = unknown( 2250,  595),
	Bbh     = unknown( 2260,  600),
	Bbs     = unknown( 2270,  605),
	Bbo     = unknown( 2280,  610),
	Bbe     = unknown( 2290,  615),
	Btn     = unknown( 2300,  620),
	Btu     = unknown( 2310,  625),
	Btb     = unknown( 2320,  630),
	Btt     = unknown( 2330,  635),
	Btq     = unknown( 2340,  640),
	Btp     = unknown( 2350,  645),
	Bth     = unknown( 2360,  650),
	Bts     = unknown( 2370,  655),
	Bto     = unknown( 2380,  660),
	Mcg     = element( 2390, "Mac-Guffium"           , "Mcg"     , 239, 665, 200,  1000,  3.122, SET_SHINY, 200,  50, 150, 255).put(G_CONTAINERS, UUM, VALUABLE, GLOWING, LIGHTING, "Bitriennium").aspects(TC.ALIENIS, 8, TC.PERMUTATIO, 8, TC.SPIRITUS, 8, TC.AURAM, 8, TC.VITIUM, 8, TC.RADIO, 8, TC.MAGNETO, 8, TC.ELECTRUM, 8, TC.NEBRISUM, 8, TC.STRONTIO, 8), Bte = Mcg,
	Bqn     = unknown( 2400,  670),
	Bqu     = unknown( 2410,  675),
	Bqb     = unknown( 2420,  680),
	Bqt     = unknown( 2430,  685),
	Bqq     = unknown( 2440,  690),
	Bqp     = unknown( 2450,  695),
	Bqh     = unknown( 2460,  700),
	Bqs     = unknown( 2470,  705),
	Bqo     = unknown( 2480,  710),
	Bqe     = unknown( 2490,  715),
	Bpn     = unknown( 2500,  720),
	Bpu     = unknown( 2510,  725),
	Bpb     = unknown( 2520,  730),
	Bpt     = unknown( 2530,  735),
	Bpq     = unknown( 2540,  740),
	Bpp     = unknown( 2550,  745),
	Bph     = unknown( 2560,  750),
	Bps     = unknown( 2570,  755),
	Bpo     = unknown( 2580,  760),
	Bpe     = unknown( 2590,  765),
	Bhn     = unknown( 2600,  770),
	Bhu     = unknown( 2610,  775),
	Bhb     = unknown( 2620,  780),
	Bht     = unknown( 2630,  785),
	Bhq     = unknown( 2640,  790),
	Bhp     = unknown( 2650,  795),
	Bhh     = unknown( 2660,  800),
	Bhs     = unknown( 2670,  805),
	Bho     = unknown( 2680,  810),
	Bhe     = unknown( 2690,  815),
	Bsn     = unknown( 2700,  820),
	Bsu     = unknown( 2710,  825),
	Bsb     = unknown( 2720,  830),
	Bst     = unknown( 2730,  835),
	Bsq     = unknown( 2740,  840),
	Bsp     = unknown( 2750,  845),
	Bsh     = unknown( 2760,  850),
	Bss     = unknown( 2770,  855),
	Bso     = unknown( 2780,  860),
	Bse     = unknown( 2790,  865),
	Bon     = unknown( 2800,  870),
	Bou     = unknown( 2810,  875),
	Bob     = unknown( 2820,  880),
	Bot     = unknown( 2830,  885),
	Boq     = unknown( 2840,  890),
	Bop     = unknown( 2850,  895),
	Boh     = unknown( 2860,  900),
	Bos     = unknown( 2870,  905),
	Boo     = unknown( 2880,  910),
	Boe     = unknown( 2890,  915),
	Ben     = unknown( 2900,  920),
	Beu     = unknown( 2910,  925),
	Beb     = unknown( 2920,  930),
	Bet     = unknown( 2930,  935),
	Beq     = unknown( 2940,  940),
	Bep     = unknown( 2950,  945),
	Beh     = unknown( 2960,  950),
	Bes     = unknown( 2970,  955),
	Beo     = unknown( 2980,  960),
	Bee     = unknown( 2990,  965),
	Tnn     = unknown( 3000,  970),
	Tnu     = unknown( 3010,  975),
	Tnb     = unknown( 3020,  980),
	Tnt     = unknown( 3030,  985),
	Tnq     = unknown( 3040,  990),
	Tnp     = unknown( 3050,  995),
	Tnh     = unknown( 3060, 1000),
	Tns     = unknown( 3070, 1005),
	Tno     = unknown( 3080, 1010),
	Tne     = unknown( 3090, 1015),
	Tun     = unknown( 3100, 1020),
	Tuu     = unknown( 3110, 1025),
	Tub     = unknown( 3120, 1030),
	Tut     = unknown( 3130, 1035),
	Tuq     = unknown( 3140, 1040),
	Tup     = unknown( 3150, 1045),
	Tuh     = unknown( 3160, 1050),
	Tus     = unknown( 3170, 1055),
	Tuo     = unknown( 3180, 1060),
	Tue     = unknown( 3190, 1065),
	Tbn     = unknown( 3200, 1070),
	Tbu     = unknown( 3210, 1075),
	Tbb     = unknown( 3220, 1080),
	Tbt     = unknown( 3230, 1085),
	Tbq     = unknown( 3240, 1090),
	Tbp     = unknown( 3250, 1095),
	Tbh     = unknown( 3260, 1100),
	Tbs     = unknown( 3270, 1105),
	Tbo     = unknown( 3280, 1110),
	Tbe     = unknown( 3290, 1115),
	Ttn     = unknown( 3300, 1120),
	Ttu     = unknown( 3310, 1125),
	Ttb     = unknown( 3320, 1130),
	Ttt     = unknown( 3330, 1135),
	Ttq     = unknown( 3340, 1140),
	Ttp     = unknown( 3350, 1145),
	Tth     = unknown( 3360, 1150),
	Tts     = unknown( 3370, 1155),
	Tto     = unknown( 3380, 1160),
	Tte     = unknown( 3390, 1165),
	Tqn     = unknown( 3400, 1170),
	Tqu     = unknown( 3410, 1175),
	Tqb     = unknown( 3420, 1180),
	Tqt     = unknown( 3430, 1185),
	Tqq     = unknown( 3440, 1190),
	Tqp     = unknown( 3450, 1195),
	Tqh     = unknown( 3460, 1200),
	Tqs     = unknown( 3470, 1205),
	Tqo     = unknown( 3480, 1210),
	Tqe     = unknown( 3490, 1215),
	Tpn     = unknown( 3500, 1220),
	Tpu     = unknown( 3510, 1225),
	Tpb     = unknown( 3520, 1230),
	Tpt     = unknown( 3530, 1235),
	Tpq     = unknown( 3540, 1240),
	Tpp     = unknown( 3550, 1245),
	Tph     = unknown( 3560, 1250),
	Tps     = unknown( 3570, 1255),
	Tpo     = unknown( 3580, 1260),
	Tpe     = unknown( 3590, 1265),
	Thn     = unknown( 3600, 1270),
	Thu     = unknown( 3610, 1275),
	Thb     = unknown( 3620, 1280),
	Tht     = unknown( 3630, 1285),
	Thq     = unknown( 3640, 1290),
	Thp     = unknown( 3650, 1295),
	Thh     = unknown( 3660, 1300),
	Ths     = unknown( 3670, 1305),
	Tho     = unknown( 3680, 1310),
	The     = unknown( 3690, 1315),
	Tsn     = unknown( 3700, 1320),
	Tsu     = unknown( 3710, 1325),
	Gt      = element( 3720, "Gravitonium"           , "Gt"      , 372,1330, 112, 1275, 1768.866761, SET_SHINY,   0,  50,   0, 255).put(G_CONTAINERS, UUM, "Triseptbium").aspects(TC.TERRA, 10, TC.POTENTIA, 10), Tsb = Gt,
	Tst     = unknown( 3730, 1335),
	Tsq     = unknown( 3740, 1340),
	Tsp     = unknown( 3750, 1345),
	Tsh     = unknown( 3760, 1350),
	Tss     = unknown( 3770, 1355),
	Tso     = unknown( 3780, 1360),
	Tse     = unknown( 3790, 1365),
	Ton     = unknown( 3800, 1370),
	Tou     = unknown( 3810, 1375),
	Tob     = unknown( 3820, 1380),
	Tot     = unknown( 3830, 1385),
	Toq     = unknown( 3840, 1390),
	Top     = unknown( 3850, 1395),
	Toh     = unknown( 3860, 1400),
	Tos     = unknown( 3870, 1405),
	Too     = unknown( 3880, 1410),
	Toe     = unknown( 3890, 1415),
	Ten     = unknown( 3900, 1420),
	Teu     = unknown( 3910, 1425),
	Teb     = unknown( 3920, 1430),
	Tet     = unknown( 3930, 1435),
	Teq     = unknown( 3940, 1440),
	Tep     = unknown( 3950, 1445),
	Teh     = unknown( 3960, 1450),
	Tes     = unknown( 3970, 1455),
	Teo     = unknown( 3980, 1460),
	Tee     = unknown( 3990, 1465),
	
	Neutronium = unused("Neutronium").qual(3, 6.0, 81920, 6).put(IGNORE_IN_COLOR_LOG).setTooltip("Nt"),
	
	
	
	Primitive      = tier("Primitive"     ).aspects(TC.MACHINA , 1),
	Basic          = tier("Basic"         ).aspects(TC.MACHINA , 2),
	Good           = tier("Good"          ).aspects(TC.MACHINA , 3),
	Advanced       = tier("Advanced"      ).aspects(TC.MACHINA , 4),
	Data           = tier("Data"          ).aspects(TC.MACHINA , 4),
	Elite          = tier("Elite"         ).aspects(TC.MACHINA , 5),
	Master         = tier("Master"        ).aspects(TC.MACHINA , 6),
	Ultimate       = tier("Ultimate"      ).aspects(TC.MACHINA , 7),
	Quantum        = tier("Quantum"       ).aspects(TC.ORDO    , 8),
	Superconductor = tier("Superconductor").aspects(TC.ELECTRUM, 8),
	Infinite       = tier("Infinite"      ),
	
	Black          = dye( 8250, "Black"     ,  32,  32,  32),
	Red            = dye( 8251, "Red"       , 255,   0,   0),
	Green          = dye( 8252, "Green"     ,   0, 255,   0),
	Brown          = dye( 8253, "Brown"     ,  96,  64,   0),
	Blue           = dye( 8254, "Blue"      ,   0,   0, 255),
	Purple         = dye( 8255, "Purple"    , 128,   0, 128),
	Cyan           = dye( 8256, "Cyan"      ,   0, 255, 255),
	LightGray      = dye( 8257, "Light Gray", 192, 192, 192),
	Gray           = dye( 8258, "Gray"      , 128, 128, 128),
	Pink           = dye( 8259, "Pink"      , 255, 192, 192),
	Lime           = dye( 8260, "Lime"      , 128, 255, 128),
	Yellow         = dye( 8261, "Yellow"    , 255, 255,   0),
	LightBlue      = dye( 8262, "Light Blue", 128, 128, 255),
	Magenta        = dye( 8263, "Magenta"   , 255,   0, 255),
	Orange         = dye( 8264, "Orange"    , 255, 128,   0),
	White          = dye( 8265, "White"     , 255, 255, 255),
	
	
	H2O                     = lquddcmp      ( 9800, "Water"                                         , 100, 100, 255, 255).put(UNRECYCLABLE, FOOD, MELTING)                                                                                                              .uumMcfg( 0, H              , 2*U, O                , 1*U)                                                                                                  .aspects(TC.AQUA, 2                 ).heat(CS.C  , CS.C+100).setDensity(1.0000).liquid(FL.Water.make(1000)), Water = H2O,
	HDO                     = lquddcmp      ( 9811, "Semiheavy Water"                               , 200, 200, 155, 255).put(UNRECYCLABLE, FOOD, MELTING, LIQUID)                                                                                                      .setMcfg( 0, H              , 1*U, D                , 1*U, O                , 1*U)                                                                          .aspects(TC.AQUA, 2, TC.TEMPESTAS, 1).heat(CS.C+2, CS.C+101).setDensity(1.0540),
	D2O                     = lquddcmp      ( 9812, "Heavy Water"                                   , 255, 255, 100, 255).put(UNRECYCLABLE, FOOD, MELTING, LIQUID)                                                                                                      .setMcfg( 0, D              , 2*U, O                , 1*U)                                                                                                  .aspects(TC.AQUA, 2, TC.TEMPESTAS, 2).heat(CS.C+4, CS.C+102).setDensity(1.1056),
	T2O                     = lquddcmp      ( 9813, "Tritiated Water"                               , 255, 100, 100, 255).put(UNRECYCLABLE, FOOD, MELTING, LIQUID)                                                                                                      .setMcfg( 0, T              , 2*U, O                , 1*U)                                                                                                  .aspects(TC.AQUA, 2, TC.RADIO    , 1).heat(CS.C+7, CS.C+104).setDensity(1.2112),
	Steam                   = gasdcmp       ( 9814, "Steam"                                         , 200, 200, 200, 255).put(UNRECYCLABLE)                                                                                                                             .uumMcfg( 0, H              , 2*U, O                , 1*U)                                                                                                  .aspects(TC.AQUA, 2, TC.AER      , 1).heat(CS.C  , CS.C+100).setDensity(0.0010),
	Snow                    = dust          ( 9801, "Snow"                  , SET_FINE              , 250, 250, 250, 255).put(UNRECYCLABLE, FOOD, MORTAR)                                                                                                               .uumMcfg( 0, H              , 2*U, O                , 1*U)                                                                                                  .aspects(TC.GELUM, 1).setSmelting(H2O, U).heat(CS.C, CS.C+100).setDensity(1.0),
	Ice                     = create        ( 9802, "Ice"                   , SET_CUBE_SHINY        , 200, 200, 255, 255).put(G_GEM_TRANSPARENT, CONTAINERS, UNRECYCLABLE, FOOD, BRITTLE, MORTAR)                                                                       .uumMcfg( 0, H              , 2*U, O                , 1*U)                                                                                                  .aspects(TC.GELUM, 2).setSmelting(H2O, U).heat(CS.C, CS.C+100).setDensity(1.0).qual(1, 2.0, 4, 0),
	FreshWater              = lqud          ( 9803, "Fresh Water"                                   , 110, 110, 255, 255).put(UNRECYCLABLE, FOOD)                                                                                                                       .uumMcfg( 0, H              , 2*U, O                , 1*U)                                                                                                  .aspects(TC.AQUA, 2).heat(CS.C, CS.C+100).setDensity(1.0),
	HolyWater               = lqud          ( 9805, "Holy Water"                                    , 120, 120, 255, 255).put(GLOWING)                                                                                                                                  .setMcfg( 0, H              , 2*U, O                , 1*U)                                                                                                  .aspects(TC.AQUA, 2, TC.AURAM, 1).heat(CS.C, CS.C+100).setDensity(1.0),
	SeaWater                = lqud          ( 9806, "Sea Water"                                     ,  90,  90, 255, 255).put(UNRECYCLABLE, LIQUID)                                                                                                                     .setMcfg( 0, H              , 2*U, O                , 1*U)                                                                                                  .aspects(TC.TEMPESTAS, 2).heat(CS.C, CS.C+100).setDensity(1.0),
	DirtyWater              = lqud          ( 9807, "WaterDirty"                                    ,  70, 150, 200, 255).put(UNRECYCLABLE, LIQUID)                                                                                                                     .setMcfg( 0, H              , 2*U, O                , 1*U)                                                                                                  .aspects(TC.AQUA, 2).heat(CS.C, CS.C+100).setDensity(1.0).setLocal("Dirty Water"),
	DistWater               = lquddcmp      ( 9808, "WaterDistilled"                                , 110, 110, 255, 255).put(UNRECYCLABLE, FOOD, MELTING)                                                                                                              .uumMcfg( 0, H              , 2*U, O                , 1*U)                                                                                                  .aspects(TC.AQUA, 2).heat(CS.C, CS.C+100).setDensity(1.0).setLocal("Distilled Water"),
	Air                     = gas           ( 9830, "Air"                                           , 169, 208, 245,  15).put(TRANSPARENT, GASSES)                                                                                                                      .uumMcfg( 0, N              ,40*U, O                ,11*U, Ar               , 1*U)                                                                          .heat( 100,  200).setDensity(WEIGHT_AIR_G_PER_CUBIC_CENTIMETER),
	
	
	CO                      = gaschemelec   ( 9838, "Carbon Monoxide"                               ,  10,  10,  10, 255).put(GASSES)                                                                                                                                   .uumMcfg( 0, C              , 1*U, O                , 1*U)                                                                                                  .heat( 100,  200),
	CO2                     = gaschemelec   ( 9836, "Carbon Dioxide"                                ,  40,  40,  40, 255).put(GASSES)                                                                                                                                   .uumMcfg( 0, C              , 1*U, O                , 2*U)                                                                                                  .heat( 100,  200),
	CO3                     = gaschemelec   ( 9843, "Carbon Trioxide"                               ,  45,  45,  45, 255).put(GASSES)                                                                                                                                   .uumMcfg( 0, C              , 1*U, O                , 3*U)                                                                                                  .heat( 100,  200),
	NO                      = gaschemelec   ( 9837, "Nitrogen Monoxide"                             , 100, 175, 255, 255).put(GASSES)                                                                                                                                   .uumMcfg( 0, N              , 1*U, O                , 1*U)                                                                                                  .heat( 100,  200),
	NO2                     = gaschemelec   ( 9831, "Nitrogen Dioxide"                              , 120, 190, 255, 255).put(GASSES)                                                                                                                                   .uumMcfg( 0, N              , 1*U, O                , 2*U)                                                                                                  .heat( 100,  200),
	SO2                     = gaschemdcmp   ( 9834, "Sulfur Dioxide"                                , 255, 200,   0, 255).put(GASSES, "SulphurDioxide")                                                                                                                 .uumMcfg( 0, S              , 1*U, O                , 2*U)                                                                                                  .heat( 100,  200),
	SO3                     = gaschemdcmp   ( 9835, "Sulfur Trioxide"                               , 255, 220,   0, 255).put(GASSES, "SulphurTrioxide")                                                                                                                .uumMcfg( 0, S              , 1*U, O                , 3*U)                                                                                                  .heat( 100,  200),
	CH4                     = gaschemelec   ( 9832, "Methane"                                       , 250, 200, 250, 255).put(GASSES, FLAMMABLE)                                                                                                                        .uumMcfg( 0, C              , 1*U, H                , 4*U)                                                                                                  .heat( 100,  200),
	HCl                     = gasaciddcmp   ( 9826, "Hydrochloric Acid"                             ,   0, 255, 128, 255).put(GASSES)                                                                                                                                   .uumMcfg( 0, H              , 1*U, Cl               , 1*U)                                                                                                  .heat( 100,  200),
	H2S                     = gasaciddcmp   ( 8024, "Hydrosulfuric Acid"                            , 241, 188, 133, 255).put(GASSES, FLAMMABLE)                                                                                                                        .uumMcfg( 0, H              , 2*U, S                , 1*U)                                                                                                  .heat( 191,  213),
	HF                      = gasaciddcmp   ( 9829, "Hydrogen Fluoride"                             ,   0, 240, 240, 255).put(GASSES)                                                                                                                                   .uumMcfg( 0, H              , 1*U, F                , 1*U)                                                                                                  .heat( 189,  292),
	NH3                     = gasaciddcmp   ( 8025, "Ammonia"                                       , 114, 223, 232, 255).put(GASSES)                                                                                                                                   .uumMcfg( 0, N              , 1*U, H                , 3*U)                                                                                                  .heat( 195,  239),
	
	
	Sugar                   = dustdcmp      ( 9703, "Sugar"                 , SET_CUBE              , 250, 250, 250, 255).put(FURNACE, MELTING, FLAMMABLE, BRITTLE, MORTAR, FOOD)                                                                                       .uumMcfg( 0, C              ,12*U, H                ,22*U, O                ,11*U)                                                                          .aspects(TC.HERBA, 1, TC.AQUA, 1, TC.AER, 1).heat(459),
	IodineSalt              = oredustelec   ( 8242, "Iodine Salt"           , SET_CUBE              , 240, 200, 240, 255).put(BRITTLE, MORTAR)                                                                                                                          .uumMcfg( 0, K              , 1*U, I                , 1*U, O                , 3*U)                                                                          .aspects(TC.TEMPESTAS, 1).heat(300, 370), KIO3 = IodineSalt,
	SilverIodide            = oredustelec   ( 8243, "Silver Iodide"         , SET_CUBE              , 240, 200, 100, 255).put(BRITTLE, MORTAR)                                                                                                                          .uumMcfg( 0, Ag             , 1*U, I                , 1*U)                                                                                                  .aspects(TC.TEMPESTAS, 2).heat(831, 1779), AgI = SilverIodide,
	SiliconDioxide          = oredustdcmp   ( 8000, "Silicon Dioxide"       , SET_QUARTZ            , 200, 200, 200, 255).put(BRITTLE, QUARTZ, CRYSTALLISABLE, FURNACE)                                                                                                 .uumMcfg( 0, Si             , 1*U, O                , 2*U)                                                                                                  .aspects(TC.VITREUS, 2).heat(1986, 3220), SiO2 = SiliconDioxide,
	SiC                     = metalore      ( 8003, "Carborundum"           , SET_QUARTZ            ,  77,  77,  77     ).put(BRITTLE, QUARTZ, DECOMPOSABLE)                                                                                                            .uumMcfg( 0, Si             , 1*U, C                , 1*U)                                                                                                  .aspects(TC.VITREUS, 1).alloyElectrolyzer(3000, 3100).qual(3, 8.0, 1280, 3),
	Datolite                = oredustelec   ( 8006, "Datolite"              , SET_ROUGH             , 222, 255, 222, 255)                                                                                                                                               .uumMcfg( 0, H              , 2*U, Ca               , 2*U, B                , 2*U, Si               , 2*U, O                ,10*U)                          , H2Ca2B2Si2O10 = Datolite,
	HydrogenBorate          = dustelec      ( 8007, "Hydrogen Borate"       , SET_FINE              , 234, 234, 255, 255).put("BoricAcid")                                                                                                                              .uumMcfg( 0, H              , 3*U, B                , 1*U, O                , 3*U)                                                                          , H3BO3 = HydrogenBorate, BoricAcid = H3BO3,
	Alumina                 = oredustdcmp   ( 8008, "Alumina"               , SET_METALLIC          , 120, 195, 235, 255).put(MELTING, INGOTS)                                                                                                                          .uumMcfg( 0, Al             , 2*U, O                , 3*U)                                                                                                  .heat(2345, 3250), Al2O3 = Alumina,
	AluminiumFluoride       = oredustdcmp   ( 8010, "Aluminium Fluoride"    , SET_DULL              , 200, 190, 190, 255).put(MELTING, MOLTEN, INGOTS, ACID)                                                                                                            .uumMcfg( 0, Al             , 1*U, F                , 3*U)                                                                                                  .heat(1560), AlF3 = AluminiumFluoride,
	AluminiumHydroxide      = oredustdcmp   ( 8014, "Aluminium Hydroxide"   , SET_DULL              , 190, 190, 200, 255).put(MELTING, "Gibbsite")                                                                                                                      .uumMcfg( 0, Al             , 1*U, O                , 3*U, H                , 3*U)                                                                          .heat( 573).setSmelting(Al2O3, 5*U14), AlO3H3 = AluminiumHydroxide, Gibbsite = AlO3H3,
	VanadiumPentoxide       = oredustelec   ( 8234, "Vanadium Pentoxide"    , SET_FINE              ,  50,  50,  50, 255).put(MAGNETIC_PASSIVE)                                                                                                                         .uumMcfg( 0, V              , 2*U, O                , 5*U)                                                                                                  .setSmelting(V, U7), V2O5 = VanadiumPentoxide,
	PO4                     = oredustelec   ( 8207, "Phosphate"             , SET_DULL              , 255, 255,   0, 255).put(FLAMMABLE, EXPLOSIVE, BRITTLE, MORTAR)                                                                                                    .uumMcfg( 0, P              , 1*U, O                , 4*U)                                                                                                  .heat( 400,  800),
	WO3                     = oredustdcmp   ( 8026, "Tungsten Trioxide"     , SET_DULL              , 199, 211,   0, 255).put(MELTING, MOLTEN, INGOTS, MORTAR, "TungstenOxide")                                                                                         .uumMcfg( 0, W              , 1*U, O                , 3*U)                                                                                                  .heat(1746, 1970),
	H2WO4                   = dustdcmp      ( 8027, "Tungstic Acid"         , SET_SHINY             , 188, 200,   0, 255).put(MELTING, ACID)                                                                                                                            .uumMcfg( 0, H              , 2*U, W                , 1*U, O                , 4*U)                                                                          .heat( 373, 1746).setSmelting(WO3, 4*U7),
	
	
	TiO2                    = oredustdcmp   ( 9192, "Rutile"                , SET_METALLIC          , 110,  80, 120, 255).put(MELTING, MOLTEN, INGOTS)                                                                                                                  .uumMcfg( 1, Ti             , 1*U, O                , 2*U)                                                                                                  .heat(Ti.mMeltingPoint + 100, Ti.mBoilingPoint).qual(2),
	
	
	MnO2                    = oredustdcmp   ( 9126, "Pyrolusite"            , SET_DULL              ,  50,  50,  70, 255).put(MELTING, MOLTEN, INGOTS, MORTAR, MAGNETIC_PASSIVE).setSmelting(Mn, 3*U4)                                                                  .uumMcfg( 1, Mn             , 1*U, O                , 2*U)                                                                                                  .heat( 808, 2334),
	
	
	Fe2O3                   = oredustelec   ( 9104, "Hematite"              , SET_DULL              , 145,  90,  90, 255).put(MORTAR, MELTING, MOLTEN, MAGNETIC_PASSIVE, "BandedIron", "IronOxide")                                                                     .uumMcfg( 0, Fe             , 2*U, O                , 3*U)                                                                                                  .qual(0).heat(2 * Fe.mMeltingPoint / 3),
	FeCl2                   = dustelec      ( 8030, "Ferrous Chloride"      , SET_CUBE              , 199, 233, 199, 255).put(MELTING, MOLTEN, INGOTS)                                                                                                                  .uumMcfg( 0, Fe             , 1*U, Cl               , 2*U)                                                                                                  .heat( 950, 1296),
	FeCl3                   = dustelec      ( 8017, "Ferric Chloride"       , SET_METALLIC          , 180, 180, 120, 255).put(MELTING)                                                                                                                                  .uumMcfg( 0, Fe             , 1*U, Cl               , 3*U)                                                                                                  .heat( 580,  589).setSmelting(FeCl2, 3*U4),
	
	
	MnCl2                   = dustelec      ( 8031, "Manganese Chloride"    , SET_CUBE              , 255, 213, 213, 255).put(MELTING, MOLTEN, INGOTS)                                                                                                                  .uumMcfg( 0, Mn             , 1*U, Cl               , 2*U)                                                                                                  .heat( 927, 1498),
	
	
	MgCl2                   = oredustelec   ( 8018, "Magnesium Chloride"    , SET_CUBE              , 235, 235, 250, 255).put(MELTING, MOLTEN, INGOTS)                                                                                                                  .uumMcfg( 0, Mg             , 1*U, Cl               , 2*U)                                                                                                  .aspects(TC.FAMES, 1).heat( 987, 1685), // Can be electrolyzed for real
	MgCO3                   = oredustelec   ( 8016, "Magnesium Carbonate"   , SET_DULL              , 240, 230, 230, 255).put(MELTING, MOLTEN, INGOTS)                                                                                                                  .uumMcfg( 0, Mg             , 1*U, CO3              , 4*U)                                                                                                  .heat( 623, 3000),
	
	
	CaCl2                   = oredustelec   ( 8028, "Calcium Chloride"      , SET_CUBE              , 235, 235, 250, 255).put(MELTING, MOLTEN, INGOTS)                                                                                                                  .uumMcfg( 0, Ca             , 1*U, Cl               , 2*U)                                                                                                  .aspects(TC.FAMES, 1).heat(1048, 2208), // Can be electrolyzed for real
	CaSO4                   = dustdcmp      ( 8274, "Calcium Sulfate"       , SET_CUBE              , 240, 220, 210, 255).put("CalciumSulphate")                                                                                                                        .uumMcfg( 0, Ca             , 1*U, S                , 1*U, O                , 4*U)                                                                          .heat(1730, 3000),
	CaCO3                   = oredustdcmp   ( 9107, "Calcite"               , SET_DULL              , 250, 230, 220, 255).put(MELTING, MOLTEN, INGOTS, MORTAR, "Valerite", "Aragonite")                                                                                 .uumMcfg( 0, Ca             , 1*U, CO3              , 4*U)                                                                                                  .heat(1612, 3000),
	CaF2                    = fluorite      ( 9215, "Fluorite"                                      , 225, 185, 140).put(MD.RoC)                                                                                                                                                                                                                                                                                                    , Fluorite = CaF2,
	FluoriteRed             = fluorite      ( 8436, "Red Fluorite"                                  , 226,  56,  65)                                                                                                                                                                                                                                                                                                                ,
	FluoritePink            = fluorite      ( 8437, "Pink Fluorite"                                 , 226, 117, 148)                                                                                                                                                                                                                                                                                                                ,
	FluoriteBlue            = fluorite      ( 8438, "Blue Fluorite"                                 ,  88, 172, 180)                                                                                                                                                                                                                                                                                                                ,
	FluoriteGreen           = fluorite      ( 8439, "Green Fluorite"                                ,  37, 168,  35)                                                                                                                                                                                                                                                                                                                ,
	FluoriteBlack           = fluorite      ( 8440, "Black Fluorite"                                ,  48,  48,  48).put(MD.RH)                                                                                                                                                                                                                                                                                                     ,
	FluoriteWhite           = fluorite      ( 8441, "White Fluorite"                                , 180, 180, 180)                                                                                                                                                                                                                                                                                                                ,
	FluoriteYellow          = fluorite      ( 8442, "Yellow Fluorite"                               , 206, 182,  80)                                                                                                                                                                                                                                                                                                                ,
	FluoriteOrange          = fluorite      ( 8443, "Orange Fluorite"                               , 255, 189,  88)                                                                                                                                                                                                                                                                                                                ,
	FluoriteMagenta         = fluorite      ( 8444, "Magenta Fluorite"                              , 204,  88, 255)                                                                                                                                                                                                                                                                                                                ,
	
	
	LiCl                    = oredustdcmp   ( 8029, "Lithium Chloride"      , SET_CUBE              , 222, 222, 250, 255).put(MELTING, MOLTEN, INGOTS, WASHING_MERCURY)                                                                                                 .uumMcfg( 0, Li             , 1*U, Cl               , 1*U)                                                                                                  .aspects(TC.FAMES, 1).setPriorityPrefix(2).heat( 880, 1655), // Can be electrolyzed for real
	LiClO3                  = dustdcmp      ( 8033, "Lithium Chlorate"      , SET_ROUGH             , 222, 233, 250, 255).put(MELTING, MOLTEN, INGOTS)                                                                                                                  .uumMcfg( 0, Li             , 1*U, Cl               , 1*U, O                , 3*U)                                                                          .heat( 400),
	LiClO4                  = dustdcmp      ( 8034, "Lithium Perchlorate"   , SET_ROUGH             , 222, 244, 250, 255).put(MELTING, MOLTEN, INGOTS)                                                                                                                  .uumMcfg( 0, Li             , 1*U, Cl               , 1*U, O                , 4*U)                                                                          .heat( 509,  703),
	Li2O                    = dustelec      ( 8004, "Lithium Oxide"         , SET_ROUGH             , 222, 222, 234, 255)                                                                                                                                               .uumMcfg( 0, Li             , 2*U, O                , 1*U)                                                                                                  ,
	Li2Fe2O4                = metalore      ( 8005, "Ferrite"               , SET_METALLIC          , 120, 120, 130     ).put(DECOMPOSABLE, ELECTROLYSER, MAGNETIC_PASSIVE)                                                                                             .uumMcfg( 0, Li             , 2*U, Fe               , 2*U, O                , 4*U)                                                                          ,
	LiOH                    = dustelec      ( 8032, "Lithium Hydroxide"     , SET_CUBE              , 222, 202, 250, 255)                                                                                                                                               .uumMcfg( 0, Li             , 1*U, O                , 1*U, H                , 1*U)                                                                          .heat( 735, 1197),
	
	
	NaCl                    = oredustdcmp   ( 8204, "Salt"                  , SET_CUBE              , 250, 250, 250, 255).put(BRITTLE, MORTAR, FOOD)                                                                                                                    .uumMcfg( 0, Na             , 1*U, Cl               , 1*U)                                                                                                  .aspects(TC.FAMES, 1).setPriorityPrefix(2).heat(1074, 1686),
	NaNO3                   = oredustelec   ( 8019, "Sodium Nitrate"        , SET_FINE              , 230, 230, 230, 255).put(FLAMMABLE, BRITTLE, MORTAR)                                                                                                               .uumMcfg( 0, Na             , 1*U, N                , 1*U, O                , 3*U)                                                                          .aspects(TC.IGNIS, 2).setPriorityPrefix(2).heat(607),
	NaOH                    = dustelec      ( 8268, "Sodium Hydroxide"      , SET_CUBE              , 220, 250, 220, 255)                                                                                                                                               .uumMcfg( 0, Na             , 1*U, O                , 1*U, H                , 1*U)                                                                          ,
	NaHSO4                  = dustdcmp      ( 8230, "Sodium Bisulfate"      , SET_FINE              , 240, 240, 255, 255).put("SodiumBisulphate", "SodiumHydrogenSulfate", "SodiumHydrogenSulphate")                                                                    .uumMcfg( 0, Na             , 1*U, H                , 1*U, S                , 1*U, O                , 4*U)                                                  ,
	NaSO4                   = dustdcmp      ( 9822, "Sodium Persulfate"     , SET_CUBE              , 130, 180, 250, 255).put("SodiumPersulphate")                                                                                                                      .uumMcfg( 0, Na             , 1*U, S                , 1*U, O                , 4*U)                                                                          ,
	Na2S                    = dustdcmp      ( 9823, "Sodium Sulfide"        , SET_CUBE              , 220, 220, 100, 255).put("SodiumSulphide")                                                                                                                         .uumMcfg( 0, Na             , 2*U, S                , 1*U)                                                                                                  ,
	Na2SO3                  = dustdcmp      ( 8269, "Sodium Sulfite"        , SET_CUBE              , 190, 190, 140, 255).put("SodiumSulphite")                                                                                                                         .uumMcfg( 0, Na             , 2*U, S                , 1*U, O                , 3*U)                                                                          .heat( 306),
	Na2SO4                  = dustdcmp      ( 8270, "Sodium Sulfate"        , SET_CUBE              , 190, 190, 140, 255).put("SodiumSulphate")                                                                                                                         .uumMcfg( 0, Na             , 2*U, S                , 1*U, O                , 4*U)                                                                          .heat(1157, 1702), // Can be used to store Heat very well.
	Na2S2O7                 = dustdcmp      ( 8231, "Sodium Pyrosulfate"    , SET_FINE              , 240, 240, 255, 255).put("SodiumPyrosulphate")                                                                                                                     .uumMcfg( 0, Na             , 2*U, S                , 2*U, O                , 7*U)                                                                          .heat( 674),
	Na2CO3                  = dustelec      ( 8013, "Sodium Carbonate"      , SET_FINE              , 230, 230, 230, 255).put(MELTING, MOLTEN, INGOTS)                                                                                                                  .uumMcfg( 0, Na             , 2*U, CO3              , 4*U)                                                                                                  .heat(1124),
	NaAlO2                  = dustdcmp      ( 8012, "Sodium Aluminate"      , SET_CUBE              , 230, 230, 250, 255)                                                                                                                                               .uumMcfg( 0, Na             , 1*U, Al               , 1*U, O                , 2*U)                                                                          .heat(1920),
	Na3AlF6                 = oredustdcmp   ( 8009, "Cryolite"              , SET_DULL              , 200, 190, 190, 255).put(MELTING, MOLTEN, INGOTS, ACID)                                                                                                            .uumMcfg( 0, Na             , 3*U, Al               , 1*U, F                , 6*U)                                                                          .heat(1285), Cryolite = Na3AlF6,
	
	
	KCl                     = oredustdcmp   ( 8203, "Rock Salt"             , SET_CUBE              , 240, 200, 200, 255).put(BRITTLE, MORTAR)                                                                                                                          .uumMcfg( 0, K              , 1*U, Cl               , 1*U)                                                                                                  .aspects(TC.FAMES, 1).setPriorityPrefix(2).heat(1040, 1690),
	KNO3                    = oredustelec   ( 8205, "Potassium Nitrate"     , SET_FINE              , 230, 230, 230, 255).put(FLAMMABLE, BRITTLE, MORTAR, "Saltpeter", "Nitrate", "Salpeter")                                                                           .uumMcfg( 0, K              , 1*U, N                , 1*U, O                , 3*U)                                                                          .aspects(TC.IGNIS, 2).setPriorityPrefix(2).heat(607),
	KOH                     = dustelec      ( 8015, "Potassium Hydroxide"   , SET_CUBE              , 250, 220, 220, 255)                                                                                                                                               .uumMcfg( 0, K              , 1*U, O                , 1*U, H                , 1*U)                                                                          ,
	KHSO4                   = dustdcmp      ( 8232, "Potassium Bisulfate"   , SET_FINE              , 255, 240, 240, 255).put("PotassiumBisulphate")                                                                                                                    .uumMcfg( 0, K              , 1*U, H                , 1*U, S                , 1*U, O                , 4*U)                                                  ,
	KSO4                    = dustdcmp      ( 8022, "Potassium Persulfate"  , SET_CUBE              , 250, 180, 130, 255).put("PotassiumPersulphate")                                                                                                                   .uumMcfg( 0, K              , 1*U, S                , 1*U, O                , 4*U)                                                                          ,
	K2S                     = dustdcmp      ( 8272, "Potassium Sulfide"     , SET_CUBE              , 100, 220, 220, 255).put("PotassiumSulphide")                                                                                                                      .uumMcfg( 0, K              , 2*U, S                , 1*U)                                                                                                  ,
	K2SO3                   = dustdcmp      ( 8021, "Potassium Sulfite"     , SET_CUBE              , 140, 190, 190, 255).put("PotassiumSulphite")                                                                                                                      .uumMcfg( 0, K              , 2*U, S                , 1*U, O                , 3*U)                                                                          .heat( 306),
	K2SO4                   = dustdcmp      ( 8271, "Potassium Sulfate"     , SET_CUBE              , 140, 190, 190, 255).put("PotassiumSulphate")                                                                                                                      .uumMcfg( 0, K              , 2*U, S                , 1*U, O                , 4*U)                                                                          .heat(1342, 1962),
	K2S2O7                  = dustdcmp      ( 8233, "Potassium Pyrosulfate" , SET_FINE              , 255, 240, 240, 255).put("PotassiumPyrosulphate")                                                                                                                  .uumMcfg( 0, K              , 2*U, S                , 2*U, O                , 7*U)                                                                          .heat( 598),
	K2CO3                   = dustelec      ( 8020, "Potassium Carbonate"   , SET_FINE              , 230, 225, 225, 255).put(MELTING, MOLTEN, INGOTS)                                                                                                                  .uumMcfg( 0, K              , 2*U, CO3              , 4*U)                                                                                                  .heat(1164),
	KAlO2                   = dustdcmp      ( 8023, "Potassium Aluminate"   , SET_CUBE              , 250, 230, 230, 255)                                                                                                                                               .uumMcfg( 0, K              , 1*U, Al               , 1*U, O                , 2*U)                                                                          .heat(1920),
	
	
	H2O2                    = lquddcmp      ( 9809, "Hydrogen Peroxide"                             ,  20,  20, 255, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, H              , 2*U, O                , 2*U)                                                                                                  .aspects(TC.AQUA, 3).setDensity(1.0).heat(CS.C, CS.C+150),
	Glyceryl                = lqudchemelec  ( 9821, "Glyceryl"                                      ,   0, 150, 150, 255).put(LIQUID, FLAMMABLE, EXPLOSIVE)                                                                                                             .uumMcfg( 0, C              , 3*U, H                , 5*U, N                , 3*U, O                , 9*U)                                                  .setDensity(1.5).heat( 287,  323),
	Glycerol                = lqudchemelec  ( 9828, "Glycerol"                                      ,   0, 180, 180, 255).put(LIQUID, FLAMMABLE)                                                                                                                        .uumMcfg( 0, C              , 3*U, H                , 8*U, O                , 3*U)                                                                          .setDensity(1.5).heat( 291,  563),
	H2SO4                   = lqudaciddcmp  ( 9824, "Sulfuric Acid"                                 , 255, 128,   0, 255).put(LIQUID, "SulphuricAcid")                                                                                                                  .uumMcfg( 0, H              , 2*U, S                , 1*U, O                , 4*U)                                                                          .setDensity(1.5).heat( 200,  400), SulfuricAcid = H2SO4,
	H2S2O7                  = lqudaciddcmp  ( 9844, "Disulfuric Acid"                               , 255, 150,   0, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, H              , 2*U, S                , 2*U, O                , 7*U)                                                                          .setDensity(1.5).heat( 200,  400),
	HNO3                    = lqudaciddcmp  ( 9825, "Nitric Acid"                                   , 128, 255,   0, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, H              , 1*U, N                , 1*U, O                , 3*U)                                                                          .setDensity(1.5).heat( 231,  356), NitricAcid = HNO3,
	H2SiF6                  = lqudacidelec  ( 8011, "Hexafluorosilicic Acid"                        , 190, 200, 190, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, H              , 2*U, Si               , 1*U, F                , 6*U)                                                                          .setDensity(1.5).heat( 250,  381), HexafluorosilicicAcid = H2SiF6,
	
	
	ChloroauricAcid         = lqudaciddcmp  ( 8400, "Chloroauric Acid"                              , 255, 200,  70, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, Au             , 1*U, Cl               , 4*U, H                , 1*U)                                                                          .heat( 200,  400),
	ChloroplatinicAcid      = lqudaciddcmp  ( 8401, "Chloroplatinic Acid"                           , 255,  70,  70, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, Pt             , 1*U, Cl               , 6*U, H                , 2*U)                                                                          .heat( 200,  400),
	StannicChloride         = lqudaciddcmp  ( 8402, "Stannic Chloride"                              , 210, 250, 250, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, Sn             , 1*U, Cl               , 4*U)                                                                                                  .heat( 200,  400),
	TiCl4                   = lqudaciddcmp  ( 8413, "Titanium Tetrachloride"                        , 233, 244, 222, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, Ti             , 1*U, Cl               , 4*U)                                                                                                  .heat( 249,  409), TitaniumTetrachloride = TiCl4,
	
	
	BlackVitriol            = lqudacidelec  ( 8403, "Black Vitriol"                                 ,  66,  66,  66, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, Fe             , 1*U, S                , 1*U)                                                                                                  .heat( 200,  400),
	BlueVitriol             = lqudaciddcmp  ( 8404, "Blue Vitriol"                                  ,  66,  66, 222, 255).put(LIQUID, "RomanVitriol", "CyprusVitriol")                                                                                                  .uumMcfg( 0, Cu             , 1*U, S                , 1*U, O                , 4*U)                                                                          .heat( 200,  400),
	GreenVitriol            = lqudaciddcmp  ( 8405, "Green Vitriol"                                 ,  66, 222,  66, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, Fe             , 1*U, S                , 1*U, O                , 4*U)                                                                          .heat( 200,  400),
	RedVitriol              = lqudaciddcmp  ( 8406, "Red Vitriol"                                   , 222,  66,  66, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, Co             , 1*U, S                , 1*U, O                , 4*U)                                                                          .heat( 200,  400),
	PinkVitriol             = lqudaciddcmp  ( 8407, "Pink Vitriol"                                  , 222, 111, 111, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, Mg             , 1*U, S                , 1*U, O                , 4*U)                                                                          .heat( 200,  400),
	CyanVitriol             = lqudaciddcmp  ( 8408, "Cyan Vitriol"                                  , 111, 222, 222, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, Ni             , 1*U, S                , 1*U, O                , 4*U)                                                                          .heat( 200,  400),
	WhiteVitriol            = lqudaciddcmp  ( 8409, "White Vitriol"                                 , 222, 222, 222, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, Zn             , 1*U, S                , 1*U, O                , 4*U)                                                                          .heat( 200,  400),
	GrayVitriol             = lqudaciddcmp  ( 8410, "Gray Vitriol"                                  , 111, 111, 111, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, Mn             , 1*U, S                , 1*U, O                , 4*U)                                                                          .heat( 200,  400),
	MartianVitriol          = lqudaciddcmp  ( 8411, "Martian Vitriol"                               , 222,  66, 222, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, Fe             , 2*U, S                , 3*U, O                ,12*U)                                                                          .heat( 200,  400),
	VitriolOfClay           = lqudaciddcmp  ( 8412, "Vitriol Of Clay"                               ,  66, 222, 222, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, Al2O3          , 5*U, S                , 3*U, O                , 9*U)                                                                          .heat( 200,  400),
	
	
	HeliumNeon              = gaschemcent   ( 9839, "Helium-Neon"                                   , 255,   0, 128, 255).put(GASSES)                                                                                                                                   .uumMcfg( 0, He             , 1*U, Ne               , 1*U)                                                                                                  , HeNe = HeliumNeon,
	
	
	AquaRegia               = lqudacidcent  ( 9827, "Aqua Regia"                                    ,  64, 255,  64, 255).put(LIQUID)                                                                                                                                   .uumMcfg( 0, HNO3           , 5*U, HCl              , 8*U)                                                                                                  .heat( 200,  400),
	SaltWater               = lqudelec      ( 9804, "SaltWater"                                     , 255,   0, 255, 255).put(LIQUID, UNRECYCLABLE)                                                                                                                     .uumMcfg( 0, H2O            , 3*U, NaCl             , 1*U)                                                                                                  .aspects(TC.AQUA, 2).heat(CS.C, CS.C+100).setDensity(1.0).setLocal("Saltwater"),
	
	
	UF4                     = dustdcmp      ( 9007, "Uranium Tetrafluoride"    , SET_SHARDS         ,  86, 118, 105, 255).put(MELTING, MOLTEN)                                                                                                                          .setMcfg( 0, U_238          , 1*U, F                , 4*U)                                                                                                  .aspects(TC.RADIO, 1, TC.PERDITIO, 2).setPriorityPrefix(2).heat(1309, 1690),
	UF6                     = gaschemdcmp   ( 9008, "Uranium Hexafluoride"                          ,  66,  98,  85, 255).put(GASSES)                                                                                                                                   .setMcfg( 0, U_238          , 1*U, F                , 6*U)                                                                                                  .aspects(TC.RADIO, 1, TC.PERDITIO, 3).setPriorityPrefix(2).heat( 100,  329),
	U238F4                  = dustdcmp      ( 9009, "Uranium-238 Tetrafluoride", SET_SHARDS         ,  86, 118, 105, 255).put(MELTING, MOLTEN)                                                                                                                          .setMcfg( 0, U_238          , 1*U, F                , 4*U)                                                                                                  .aspects(TC.RADIO, 1, TC.PERDITIO, 2).setPriorityPrefix(2).heat(1309, 1690),
	U238F6                  = gaschemdcmp   ( 9010, "Uranium-238 Hexafluoride"                      ,  66,  98,  85, 255).put(GASSES)                                                                                                                                   .setMcfg( 0, U_238          , 1*U, F                , 6*U)                                                                                                  .aspects(TC.RADIO, 1, TC.PERDITIO, 3).setPriorityPrefix(2).heat( 100,  329),
	U235F4                  = dustdcmp      ( 9011, "Uranium-235 Tetrafluoride", SET_SHARDS         ,  86, 118, 105, 255).put(MELTING, MOLTEN)                                                                                                                          .setMcfg( 0, U_235          , 1*U, F                , 4*U)                                                                                                  .aspects(TC.RADIO, 1, TC.PERDITIO, 2).setPriorityPrefix(2).heat(1309, 1690),
	U235F6                  = gaschemdcmp   ( 9012, "Uranium-235 Hexafluoride"                      ,  66,  98,  85, 255).put(GASSES)                                                                                                                                   .setMcfg( 0, U_235          , 1*U, F                , 6*U)                                                                                                  .aspects(TC.RADIO, 1, TC.PERDITIO, 3).setPriorityPrefix(2).heat( 100,  329),
	
	
	CobaltHexahydrate       = dustcent      ( 8229, "Cobalt Hexahydrate"    , SET_ROUGH             ,  80,  80, 250, 255)                                                                                                                                               .uumMcfg( 0, Co             , 1*U, H2O              , 6*U)                                                                                                  ,
	MethaneIce              = dustcent      ( 9833, "Methane Ice"           , SET_SHINY             , 225, 200, 250, 255).put(G_CONTAINERS, FLAMMABLE)                                                                                                                  .setMcfg( 2, CH4            , 1*U, Ice              , 2*U)                                                                                                  ,
	NitroCarbon             = elec          ( 9820, "Nitro Carbon"          , SET_FLUID             ,   0,  75, 100, 255).put(G_CONTAINERS, EXPLOSIVE, FLAMMABLE)                                                                                                       .uumMcfg( 0, N              , 1*U, C                , 1*U)                                                                                                  ,
	
	
	Lava                    = lqud          ( 9810, "Lava"                  , SET_STONE             , 255,  64,   0, 255).put(MELTING, GLOWING, LIGHTING)                                                                                                                                                                                                                                                                           .heat(1300, 4000).liquid(FL.Lava.make(1000), U*9),
	Biomass                 = lqudflam      ( 9840, "Biomass"                                       ,   0, 255,   0, 255).put("BioMass")                                                                                                                                                                                                                                                                                            .heat( 200,  400).aspects(TC.HERBA, 2).setOriginalMod(MD.FR),
	BioFuel                 = lqudflam      ( 9841, "Bio Fuel"                                      , 200, 128,   0, 255)                                                                                                                                                                                                                                                                                                           .heat( 100,  400).aspects(TC.HERBA, 1, TC.POTENTIA, 1).setOriginalMod(MD.FR).setLocal("Bio Diesel"),
	Ethanol                 = lqudflam      ( 9842, "Ethanol"                                       , 255, 128,   0, 255)                                                                                                                                                                                                                                                                                                           .heat( 100,  400).aspects(TC.HERBA, 1, TC.POTENTIA, 1).setOriginalMod(MD.FR),
	Oil                     = lqudflam      ( 9850, "Oil"                                           ,  10,  10,  10, 255)                                                                                                                                                                                                                                                                                                           .heat( 100,  400).aspects(TC.MORTUUS, 2, TC.LUX, 1),
	Oilsands                = oredust       ( 9851, "Oilsands"              , SET_SAND              ,  10,  10,  10, 255)                                                                                                                                                                                                                                                                                                           .heat( 100,  400).aspects(TC.MORTUUS, 2, TC.LUX, 1),
	CrudeOil                = oredust       ( 9852, "Crude Oil"             , SET_DULL              ,  10,  10,  10, 255)                                                                                                                                                                                                                                                                                                           .heat( 100,  400).aspects(TC.MORTUUS, 2, TC.LUX, 1),
	Fuel                    = lqudexpl      ( 9860, "Fuel"                                          , 255, 255,   0, 255).put("FuelOil")                                                                                                                                                                                                                                                                                            .heat( 100,  400).aspects(TC.MORTUUS, 1, TC.POTENTIA, 1).setLocal("Fuel Oil"),
	NitroFuel               = lqudexpl      ( 9861, "Nitro-Fuel"                                    , 200, 255,   0, 255)                                                                                                                                               .setMcfg( 0, Glyceryl       , 1*U, Fuel             , 4*U)                                                                                                  .heat( 100,  400).aspects(TC.MORTUUS, 1, TC.POTENTIA, 2),
	Kerosine                = lqudexpl      ( 9862, "Kerosine"                                      ,   0,   0, 255, 255)                                                                                                                                                                                                                                                                                                           .heat( 100,  400).aspects(TC.VOLATUS, 1, TC.POTENTIA, 1),
	Diesel                  = lqudexpl      ( 9863, "Diesel"                                        , 255, 255,   0, 255)                                                                                                                                                                                                                                                                                                           .heat( 100,  400).aspects(TC.MORTUUS, 1, TC.POTENTIA, 1),
	Petrol                  = lqudexpl      ( 9864, "Petrol"                                        , 255,   0,   0, 255).put("Gasoline")                                                                                                                                                                                                                                                                                           .heat( 100,  400).aspects(TC.MORTUUS, 1, TC.POTENTIA, 1),
	Propane                 = gasexpl       ( 9865, "Propane"                                       , 255,  20,  20, 255)                                                                                                                                                                                                                                                                                                           .heat( 100,  200).aspects(TC.MORTUUS, 1, TC.POTENTIA, 1),
	Butane                  = gasexpl       ( 9866, "Butane"                                        , 255,  40,  40, 255)                                                                                                                                                                                                                                                                                                           .heat( 100,  200).aspects(TC.MORTUUS, 1, TC.POTENTIA, 1),
	Propylene               = gasexpl       ( 9867, "Propylene"                                     ,  90,  60, 140, 255)                                                                                                                                                                                                                                                                                                           .heat( 100,  200).aspects(TC.MORTUUS, 1, TC.POTENTIA, 1),
	Ethylene                = gasexpl       ( 9868, "Ethylene"                                      ,  64,  40, 100, 255)                                                                                                                                                                                                                                                                                                           .heat( 100,  200).aspects(TC.MORTUUS, 1, TC.POTENTIA, 1),
	Creosote                = lqudflam      ( 9870, "Creosote"                                      , 128,  64,   0, 255).put(LIQUID, "Creosote Oil")                                                                                                                                                                                                                                                                               .heat( 100,  400).aspects(TC.ARBOR, 2, TC.LUX, 1),
	FishOil                 = lqudflam      ( 9871, "Fish Oil"                                      , 255, 196,   0, 255).put(FOOD)                                                                                                                                                                                                                                                                                                 .heat( 100,  400).aspects(TC.CORPUS, 2, TC.LUX, 1),
	SeedOil                 = lqudflam      ( 9872, "Seed Oil"                                      , 196, 255,   0, 255).put(FOOD)                                                                                                                                                                                                                                                                                                 .heat( 100,  400).aspects(TC.GRANUM, 2, TC.LUX, 1).setOriginalMod(MD.FR),
	HempOil                 = lqudflam      ( 9873, "Hemp Oil"                                      , 196, 255,   0, 255).put(FOOD)                                                                                                                                                                                                                                                                                                 .heat( 100,  400).aspects(TC.GRANUM, 2, TC.LUX, 1),
	LinOil                  = lqudflam      ( 9874, "Lin Oil"                                       , 196, 255,   0, 255).put(FOOD)                                                                                                                                                                                                                                                                                                 .heat( 100,  400).aspects(TC.GRANUM, 2, TC.LUX, 1),
	SunflowerOil            = lqudflam      ( 9875, "Sunflower Oil"                                 , 216, 189,  17, 255).put(FOOD)                                                                                                                                                                                                                                                                                                 .heat( 100,  400).aspects(TC.GRANUM, 2, TC.LUX, 1),
	NutOil                  = lqudflam      ( 9876, "Nut Oil"                                       , 235, 173,  70, 255).put(FOOD)                                                                                                                                                                                                                                                                                                 .heat( 100,  400).aspects(TC.GRANUM, 2, TC.LUX, 1),
	OliveOil                = lqudflam      ( 9877, "Olive Oil"                                     ,  63, 146,   0, 255).put(FOOD)                                                                                                                                                                                                                                                                                                 .heat( 100,  400).aspects(TC.HERBA, 2, TC.LUX, 1),
	FryingOilHot            = lqudflam      ( 9880, "FryingOilHot"                                  , 200, 196,   0, 255).put(FOOD)                                                                                                                                                                                                                                                                                                 .heat( 100,  400).aspects(TC.AQUA, 1, TC.IGNIS, 1).setLocal("Hot Frying Oil"),
	Glue                    = lqudflam      ( 9881, "Glue"                                          , 200, 196,   0, 255)                                                                                                                                                                                                                                                                                                           .heat( 150,  500).aspects(TC.LIMUS, 2),
	Lubricant               = lqud          ( 9882, "Lubricant"                                     , 255, 196,   0, 255)                                                                                                                                                                                                                                                                                                           .heat( 150,  500).aspects(TC.AQUA, 1, TC.MACHINA, 1),
	ConstructionFoam        = lqud          ( 9883, "Construction Foam"     , SET_DULL              , 128, 128, 128, 255).put(DUSTS, STONE, BRITTLE, MELTING)                                                                                                                                                                                                                                                                       .heat( 500, 2000).setLocal("C-Foam"),
	UUAmplifier             = lqud          ( 9884, "UU-Amplifier"                                  , 196,   0, 255, 255)                                                                                                                                                                                                                                                                                                           .heat(  50,  500),
	UUMatter                = lqud          ( 9885, "UU-Matter"                                     ,  96,   0, 128, 255).put(GLOWING)                                                                                                                                                                                                                                                                                              .heat(  50,  500),
	Latex                   = lqud          ( 9886, "Latex"                                         , 250, 250, 250, 255)                                                                                                                                                                                                                                                                                                           .heat( 150,  500).aspects(TC.AQUA, 1, TC.LIMUS, 1),
	
	
	Glass                   = cent          ( 8001, "Glass"                 , SET_GLASS             , 250, 250, 250,  35).put(G_GLASS, FURNACE, MORTAR, CRYSTAL, UNRECYCLABLE, MELTING, EXTRUDER, EXTRUDER_SIMPLE, BRITTLE).lens(DYE_INDEX_White)                       .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.VITREUS, 2).qual(1, 1.0, 1,  0).heat(1200),
	Flint                   = cent          ( 8002, "Flint"                 , SET_FLINT             ,   0,  32,  64, 255).put(G_GEM, STONE, BRITTLE, DIRTY_DUSTS, MORTAR)                                                                                               .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.TERRA, 1, TC.INSTRUMENTUM, 1).qual(1, 2.5, 48,  1).setSmelting(SiO2, U),
	
	
	Ash                     = dust          ( 8200, "Ashes"                 , SET_DULL              , 120, 120, 120, 255).put(MORTAR, BRITTLE, "Ash", MD.FR)                                                                                                                                                                                                                                                                        .aspects(TC.PERDITIO, 1),
	DarkAsh                 = dust          ( 8201, "Dark Ashes"            , SET_DULL              ,  50,  50,  50, 255).put(MORTAR, BRITTLE, "DarkAsh", "AshDark", "AshesDark")                                                                                                                                                                                                                                                   .aspects(TC.IGNIS, 1, TC.PERDITIO, 1),
	VolcanicAsh             = dustcent      ( 8202, "Volcanic Ashes"        , SET_FLINT             ,  60,  50,  50, 255).put(MORTAR, BRITTLE, "VolcanicAsh", "AshVolcanic", "AshesVolcanic")                                                                           .setMcfg( 0, Flint          , 6*U, Fe2O3            , 1*U, Mg               , 1*U)                                                                          .aspects(TC.IGNIS, 2, TC.PERDITIO, 1),
	
	
	RareEarth               = oredust       ( 9100, "Rare Earth"            , SET_FINE              , 128, 128, 100, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.VITREUS, 1, TC.LUCRUM, 1),
	Chalk                   = oredustelec   ( 9112, "Chalk"                 , SET_FINE              , 250, 250, 250, 255).put(FURNACE, MORTAR).setSmelting(CaCO3, 2*U3)                                                                                                 .setMcfg( 0, CaCO3          , 1*U)                                                                                                                          ,
	Dolomite                = oredustcent   ( 9163, "Dolomite"              , SET_FLINT             , 225, 205, 205, 255).put(FURNACE, MORTAR).setSmelting(CaCO3, U2)                                                                                                   .setMcfg( 0, CaCO3          , 1*U, MgCO3            , 1*U)                                                                                                  , // CaMg(CO3)2
	Asbestos                = oredustelec   ( 9103, "Asbestos"              , SET_LAPIS             , 230, 230, 230, 255).put(PLATES, "Chrysotile")                                                                                                                     .uumMcfg( 0, Mg             , 3*U, SiO2             , 6*U, H2O              , 6*U, O                , 3*U)                                                  , // Mg3Si2O5(OH)4
	Talc                    = oredustelec   ( 9168, "Talc"                  , SET_DULL              ,  90, 180,  90, 255)                                                                                                                                               .uumMcfg( 0, Mg             , 3*U, SiO2             ,12*U, H2O              , 3*U, O                , 3*U)                                                  , // H2Mg3(SiO3)4
	Soapstone               = oredustelec   ( 9169, "Soapstone"             , SET_DULL              ,  95, 145,  95, 255)                                                                                                                                               .uumMcfg( 0, Mg             , 3*U, SiO2             ,12*U, H2O              , 3*U, O                , 3*U)                                                  , // H2Mg3(SiO3)4
	Pyrite                  = oredustdcmp   ( 9125, "Pyrite"                , SET_SHINY             , 255, 230,  80, 255).put(BLACKLISTED_SMELTER, MORTAR, MAGNETIC_PASSIVE)                                                                                            .uumMcfg( 0, Fe             , 1*U, S                , 2*U)                                                                                                  .qual(0),
	PotassiumFeldspar       = oredustelec   ( 9140, "Potassium Feldspar"    , SET_FINE              , 120,  40,  40, 255)                                                                                                                                               .uumMcfg( 0, K              , 2*U, Al2O3            , 5*U, SiO2             ,18*U, O                , 1*U)                                                  ,
	Biotite                 = oredustelec   ( 9141, "Biotite"               , SET_METALLIC          ,  20,  30,  20, 255)                                                                                                                                               .setMcfg( 0, K              , 2*U, Mg               , 6*U, Al2O3            ,15*U, F                , 4*U, SiO2             ,18*U)                          ,
	Emery                   = oredust       ( 9183, "Emery"                 , SET_STONE             , 128, 128, 128, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA, 1),
	
	
	Bark                    = dust          ( 8275, "Bark"                  , SET_ROUGH             ,  80,  40,   0, 255).put(TICKS_PER_SMELT/ 2, WOOD, MORTAR, FLAMMABLE, APPROXIMATE, MD.BINNIE)                                                                      .uumMcfg( 0, C              , 6*U, H2O              ,15*U)                                                                                                  .aspects(TC.ARBOR, 1                   ).setBurning(Ash, U9).setSmelting(Ash, U4).heat(400, 500),
	Wood                    = wood          ( 8221, "Wood"                                          , 100,  50,   0, 255).put(TICKS_PER_SMELT/ 2, FLAMMABLE, APPROXIMATE)                                                                                               .uumMcfg( 0, C              , 6*U, H2O              ,15*U)                                                                                                  .aspects(TC.ARBOR, 1                   ).setBurning(Ash, U9).setSmelting(Ash, U4).qual(1, 2.0, 16, 0).heat(400, 500),
	WoodSealed              = wood          ( 8222, "WoodSealed"                                    ,  80,  40,   0, 255).put(TICKS_PER_SMELT/ 2, FLAMMABLE, COATED)                                                                                                    .setMcfg( 0, Wood           , 1*U)                                                                                                                          .aspects(TC.ARBOR, 1, TC.FABRICO    , 1).setAllToTheOutputOf(Wood).qual(1, 3.0, 24, 0).heat(500, 600).setLocal("Treated Wood"),
	WoodPolished            = wood          ( 8267, "WoodPolished"                                  ,  60,  30,   0, 255).put(TICKS_PER_SMELT/ 2, FLAMMABLE, COATED)                                                                                                    .setMcfg( 0, Wood           , 1*U)                                                                                                                          .aspects(TC.ARBOR, 1, TC.FABRICO    , 1).setAllToTheOutputOf(Wood).qual(1, 3.0, 24, 0).heat(500, 600).setLocal("Polished Wood"),
	WoodRubber              = wood          ( 8224, "WoodRubber"                                    , 120,  60,   0, 255).put(TICKS_PER_SMELT/ 4, FLAMMABLE, APPROXIMATE)                                                                                               .setMcfg( 0, C              , 6*U, H2O              ,15*U)                                                                                                  .aspects(TC.ARBOR, 1                   ).setBurning(Ash, U4).setSmelting(Ash, U2).qual(1, 1.5, 12, 0).heat(350, 450).setLocal("Rubber Wood"),
	Bamboo                  = wood          ( 8418, "Bamboo"                                        , 100, 200, 100, 255).put(TICKS_PER_SMELT/ 2, FLAMMABLE, APPROXIMATE)                                                                                               .setMcfg( 0, C              , 6*U, H2O              ,15*U)                                                                                                  .aspects(TC.HERBA, 1                   ).setBurning(Ash, U4).setSmelting(Ash, U2).qual(1, 2.0, 32, 0).heat(350, 450),
	Skyroot                 = wood          ( 8291, "Skyroot"                                       ,  50,  80,  80, 255).put(TICKS_PER_SMELT/ 2, FLAMMABLE, APPROXIMATE)                                                                                               .setMcfg( 0, C              , 6*U, H2O              ,15*U)                                                                                                  .aspects(TC.ARBOR, 1, TC.VOLATUS    , 1).setBurning(Ash, U4).setSmelting(Ash, U2).qual(1, 4.0, 64, 0).heat(350, 450),
	Weedwood                = wood          ( 8286, "Weedwood"                                      ,  80,  50,   0, 255).put(TICKS_PER_SMELT/ 2, FLAMMABLE, APPROXIMATE)                                                                                               .setMcfg( 0, C              , 6*U, H2O              ,15*U)                                                                                                  .aspects(TC.ARBOR, 1, TC.MORTUUS    , 1).setBurning(Ash, U4).setSmelting(Ash, U2).qual(1, 2.0, 32, 0).heat(350, 450),
	Livingwood              = wood          ( 8289, "Livingwood"                                    ,  60,  30,   0, 255).put(TICKS_PER_SMELT   , FLAMMABLE, APPROXIMATE)                                                                                               .setMcfg( 0, C              , 6*U, H2O              ,15*U)                                                                                                  .aspects(TC.ARBOR, 1, TC.VICTUS     , 1).setBurning(Ash, U4).setSmelting(Ash, U2).qual(1, 4.0, 64, 0).heat(350, 500),
	Dreamwood               = wood          ( 8290, "Dreamwood"                                     , 200, 240, 240, 255).put(TICKS_PER_SMELT* 2, FLAMMABLE, APPROXIMATE)                                                                                               .setMcfg( 0, C              , 6*U, H2O              ,15*U)                                                                                                  .aspects(TC.ARBOR, 1, TC.SPIRITUS   , 1).setBurning(Ash, U4).setSmelting(Ash, U2).qual(1, 4.0,128, 1).heat(350, 550),
	Shimmerwood             = wood          ( 8414, "Shimmerwood"                                   , 234, 234, 234, 255).put(TICKS_PER_SMELT* 2, FLAMMABLE, APPROXIMATE)                                                                                               .setMcfg( 0, C              , 6*U, H2O              ,15*U)                                                                                                  .aspects(TC.ARBOR, 1, TC.LUX        , 1).setBurning(Ash, U4).setSmelting(Ash, U2).qual(1, 4.0,128, 1).heat(350, 550),
	Greatwood               = wood          ( 8296, "Greatwood"                                     ,  60,  30,  25, 255).put(TICKS_PER_SMELT* 2, FLAMMABLE, APPROXIMATE)                                                                                               .setMcfg( 0, C              , 6*U, H2O              ,15*U)                                                                                                  .aspects(TC.ARBOR, 1                   ).setBurning(Ash, U4).setSmelting(Ash, U2).qual(1, 6.0, 64, 1).heat(400, 600),
	Silverwood              = wood          ( 8297, "Silverwood"                                    , 234, 222, 210, 255).put(TICKS_PER_SMELT* 4, FLAMMABLE, APPROXIMATE)                                                                                               .setMcfg( 0, C              , 6*U, H2O              ,15*U)                                                                                                  .aspects(TC.ARBOR, 1, TC.PRAECANTIO , 1).setBurning(Ash,  0).setSmelting(Ash,  0).qual(1, 8.0,128, 1).heat(450, 650),
	Peanutwood              = wood          ( 8227, "Peanut Wood"                                   , 120,  60,   0, 255).put(TICKS_PER_SMELT/ 2, FLAMMABLE, "Peanutwood")                                                                                              .setMcfg( 0, Wood           , 1*U)                                                                                                                          .aspects(TC.ARBOR, 1                   ).steal(Wood).heat(350, 450),
	Marshmallow             = wood          ( 9715, "Marshmallow"           , SET_FINE              , 255, 220, 220, 255).put(FOOD, MD.CANDY)                                                                                                                                                                                                                                                                                       .aspects(TC.FAMES, 1                   ).qual(1, 3.0, 24, 0),
	LiveRoot                = dustcent      ( 8223, "LiveRoot"              , SET_WOOD              , 220, 200,   0, 255).put(TICKS_PER_SMELT   , WOOD, MORTAR, MAGICAL, MORTAR, MD.TF)                                                                                 .setMcfg( 3, Wood           , 3*U, Ma               , 1*U)                                                                                                  .aspects(TC.ARBOR, 1, TC.VICTUS     , 1).setLocal("Liveroot").setBurning(Ash, U9).heat(1178, 2465),
	PetrifiedWood           = create        ( 8277, "Petrified Wood"        , SET_WOOD              , 110,  50,  35, 255).put(TICKS_PER_SMELT/ 4, G_STONE, STONE, WOOD, MORTAR, FLAMMABLE, MD.ERE)                                                                      .setMcfg( 0, Wood           , 1*U)                                                                                                                          .aspects(TC.ARBOR, 1, TC.TERRA      , 1).qual(1, 2.0, 24, 1).heat(350, 450),
	
	
	Wax                     = wax           ( 8235, "Wax"                                           , 250, 250, 250, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.PERDITIO    , 1).heat( 350),
	WaxBee                  = wax           ( 8236, "WaxBee"                                        , 250, 250, 100, 255).put(MD.FR, FOOD, "BeesWax", "Beeswax", "BeeWax", "Beewax")                                                                                                                                                                                                                                                .aspects(TC.BESTIA      , 1).heat( 350).setLocal("Bees Wax"),
	WaxRefractory           = wax           ( 8237, "WaxRefractory"                                 , 250,  50,  50, 255).put(MD.FR, UNBURNABLE, "RefractoryWax", "Refractorywax")                                                                                                                                                                                                                                                  .aspects(TC.GELUM       , 1).heat(2600).setLocal("Refractory Wax"),
	WaxParaffin             = wax           ( 8238, "WaxParaffin"                                   , 210, 210, 250, 255).put("ParaffinWax", "Paraffinwax")                                                                                                                                                                                                                                                                         .aspects(TC.FABRICO     , 1).heat( 400).setLocal("Paraffin Wax"),
	WaxPlant                = wax           ( 8239, "WaxPlant"                                      , 210, 250, 210, 255).put(MD.HaC, FOOD)                                                                                                                                                                                                                                                                                         .aspects(TC.HERBA       , 1).heat( 350).setLocal("Plant Wax"),
	WaxMagic                = wax           ( 8240, "WaxMagic"                                      , 200,  80, 200, 255).put(MD.FRMB, MAGICAL)                                                                                                                                                                                                                                                                                     .aspects(TC.PRAECANTIO  , 1).heat( 350).setLocal("Magic Wax"),
	WaxAmnesic              = wax           ( 8280, "WaxAmnesic"                                    , 180,  70, 250, 255).put(MD.FRMB, MAGICAL)                                                                                                                                                                                                                                                                                     .aspects(TC.STRONTIO    , 1).heat( 350).setLocal("Amnesic Wax"),
	WaxSoulful              = wax           ( 8281, "WaxSoulful"                                    ,  90,  40,  10, 255).put(MD.FRMB, MAGICAL)                                                                                                                                                                                                                                                                                     .aspects(TC.SPIRITUS    , 1).heat( 350).setLocal("Soulful Wax"),
	
	
	Graphite                = oredustdcmp   ( 9174, "Graphite"              , SET_DULL              , 128, 128, 128, 255).put(BLACKLISTED_SMELTER, BRITTLE, MORTAR, STICKS)                                                                                             .uumMcfg( 0, C              , 1*U)                                                                                                                          .qual(1, 5.0, 32, 2).setSmelting(C, U2).setBurning(Ash, U4).heat(1700, C.mBoilingPoint),
	Niter                   = oredustcent   ( 8206, "Niter"                 , SET_FLINT             , 255, 200, 200, 255).put(G_GEM_ORES, FLAMMABLE, BRITTLE, MORTAR, CRYSTAL, "Nitre")                                                                                 .uumMcfg( 0, KNO3           , 1*U, NaNO3            , 1*U)                                                                                                  .heat(607),
	Phosphorus              = cent          ( 8208, "Phosphorus"            , SET_FLINT             , 255, 255,   0, 255).put(G_GEM_ORES, FLAMMABLE, BRITTLE, MORTAR, EXPLOSIVE, MD.FR, "Phosphorous")                                                                  .uumMcfg( 0, Ca             , 3*U, PO4              , 2*U)                                                                                                  ,
	Apatite                 = elec          ( 8209, "Apatite"               , SET_DIAMOND           , 120, 180, 250, 255).put(G_GEM_ORES, FLAMMABLE, BRITTLE, MORTAR, CRYSTAL, CRYSTALLISABLE, MD.FR)                                                                   .uumMcfg( 0, Ca             , 5*U, PO4              , 3*U, Cl               , 1*U)                                                                          .aspects(TC.MESSIS, 2),
	Phosphorite             = elec          ( 8226, "Phosphorite"           , SET_DIAMOND           ,  50,  50,  65, 255).put(G_GEM_ORES, FLAMMABLE, BRITTLE, MORTAR, CRYSTAL, CRYSTALLISABLE)                                                                          .uumMcfg( 0, Ca             , 5*U, PO4              , 3*U, F                , 1*U)                                                                          .aspects(TC.MESSIS, 2),
	Basalz                  = create        ( 8247, "Basalz"                , SET_SHINY             , 100,  81,  81, 255).put(G_BLAZE, GLOWING, MAGICAL, BRITTLE, MORTAR)                                                                                                                                                                                                                                                           .aspects(TC.PRAECANTIO, 2, TC.POTENTIA, 4),
	Blitz                   = create        ( 8248, "Blitz"                 , SET_SHINY             , 214, 189,  21, 255).put(G_BLAZE, GLOWING, MAGICAL, BRITTLE, MORTAR)                                                                                                                                                                                                                                                           .aspects(TC.PRAECANTIO, 2, TC.AER, 4),
	Blizz                   = create        ( 8210, "Blizz"                 , SET_SHINY             , 220, 233, 255, 255).put(G_BLAZE, GLOWING, MAGICAL, BRITTLE, MORTAR)                                                                                                                                                                                                                                                           .aspects(TC.PRAECANTIO, 2, TC.GELUM, 4),
	Blaze                   = cent          ( 8211, "Blaze"                 , SET_POWDER            , 255, 200,   0, 255).put(G_BLAZE, GLOWING, MAGICAL, BRITTLE, MORTAR, UNBURNABLE, BURNING, MELTING)                                                                 .setMcfg( 2, Ash            , 1*U, S                , 1*U, Ma               , 1*U)                                                                          .aspects(TC.PRAECANTIO, 2, TC.IGNIS, 4).qual(1, 2.0, 16, 1).heat(4000),
	Obsidian                = elec          ( 8214, "Obsidian"              , SET_STONE             ,  80,  50, 100, 255).put(G_STONE, STONE, BRITTLE, MORTAR)                                                                                                          .setMcfg(64, Mg             , 1*U, Fe               , 1*U, SiO2             , 6*U, O                , 4*U)                                                  .setSmelting(Lava, U).heat(1300, 4000).qual(1, 3.0, 32, 3),
	Clay                    = oredustelec   ( 8215, "Clay"                  , SET_ROUGH             , 200, 200, 220, 255).put(MORTAR)                                                                                                                                   .uumMcfg( 7, Li             , 1*U, Al2O3            , 2*U, SiO2             , 2*U, Na               , 2*U)                                                  .heat(2000),
	ClayBrown               = oredustelec   ( 8276, "ClayBrown"             , SET_ROUGH             , 230, 140,  75, 255).put(MORTAR)                                                                                                                                   .uumMcfg( 7, Li             , 1*U, Al2O3            , 2*U, SiO2             , 2*U, K                , 2*U)                                                  .heat(2000).setLocal("Brown Clay"),
	Ceramic                 = dust          ( 8225, "Ceramic"               , SET_ROUGH             , 220, 130,  70, 255).put(PLATES, BRITTLE, MORTAR)                                                                                                                  .uumMcfg( 7, Li             , 1*U, Al2O3            , 2*U, SiO2             , 2*U)                                                                          .steal(Clay).heat(2000).setPulver(Clay, U2).setCompressing(null, 0).setBending(null, 0).setForging(null, 0).setSmashing(null, 0),
	Porcelain               = mixdust       ( 8273, "Porcelain"             , SET_ROUGH             , 195, 195, 222, 255).put(PLATES, BRITTLE, MORTAR)                                                                                                                  .uumMcfg( 0, Clay           , 2*U, SiO2             , 1*U, PotassiumFeldspar, 1*U)                                                                          .heat(1800).setCompressing(null, 0).setBending(null, 0).setForging(null, 0).setSmashing(null, 0),
	Paper                   = dust          ( 8216, "Paper"                 , SET_PAPER             , 250, 250, 250, 255).put(TICKS_PER_SMELT/ 8, MULTIPLATES, MORTAR)                                                                                                                                                                                                                                                              .aspects(TC.COGNITO, 1).setBurning(Ash, U9),
	Rubber                  = create        ( 8217, "Rubber"                , SET_RUBBER            ,  20,  20,  20, 255).put(G_INGOT_MACHINE, APPROXIMATE, FLAMMABLE, EXTRUDER, EXTRUDER_SIMPLE, WIRES, MORTAR, BOUNCY, STRETCHY, FURNACE)                             .uumMcfg( 0, C              , 5*U, H                , 8*U)                                                                                                  .aspects(TC.MOTUS, 2).heat(410).setBurning(Ash, U9).setSmelting(null, 2*U3).qual(1, 1.5, 16, 0),
	Plastic                 = create        ( 8218, "Plastic"               , SET_DULL              , 200, 200, 200, 255).put(G_INGOT_MACHINE, APPROXIMATE, FLAMMABLE, EXTRUDER, EXTRUDER_SIMPLE, WIRES, MORTAR, BOUNCY, BRITTLE, FURNACE, MD.MFR)                      .uumMcfg( 0, C              , 1*U, H                , 2*U)                                                                                                  .aspects(TC.MOTUS, 2).heat(423).setBurning(Ash, U9).setSmelting(null, 2*U3).qual(1, 3.0, 32, 1),
	Bone                    = dustelec      ( 8219, "Bone"                  , SET_DULL              , 250, 250, 250, 255).put(MORTAR, "Fossil")                                                                                                                         .uumMcfg( 8, Ca             , 1*U)                                                                                                                          .aspects(TC.MORTUUS, 2, TC.CORPUS, 1),
	SlimyBone               = gem           ( 8287, "Slimy Bone"            , SET_DULL              , 230, 250, 230, 255).put(MORTAR)                                                                                                                                   .uumMcfg( 8, Ca             , 1*U)                                                                                                                          .aspects(TC.MORTUUS, 2, TC.LIMUS, 1).qual(1, 5.0, 128, 1),
	Gunpowder               = dust          ( 8220, "Gunpowder"             , SET_DULL              , 128, 128, 128, 255).put(EXPLOSIVE, FLAMMABLE)                                                                                                                     .uumMcfg( 3, C              , 2*U, S                , 1*U, NaNO3            , 1*U)                                                                          .aspects(TC.PERDITIO, 3, TC.IGNIS, 4).setBurning(Ash, U9),
	Dynamite                = dust          ( 8249, "Dynamite"              , SET_ROUGH             , 111, 131, 111, 255).put(EXPLOSIVE, FLAMMABLE)                                                                                                                     .uumMcfg( 0, Glyceryl       , 1*U, Wood             , 1*U)                                                                                                  .aspects(TC.PERDITIO, 4, TC.IGNIS, 3).setBurning(Ash, U9),
	Asphalt                 = dust          ( 8266, "Asphalt"               , SET_ROUGH             ,  88,  88,  99, 255).put(FURNACE, MELTING, EXTRUDER, EXTRUDER_SIMPLE, MOLTEN)                                                                                                                                                                                                                                                  .aspects(TC.TERRA, 1, TC.ITER, 1),
	Tallow                  = dust          ( 8244, "Tallow"                , SET_FOOD              , 220, 200, 100, 255).put(MELTING, MAGICAL, MOLTEN)                                                                                                                                                                                                                                                                             .aspects(TC.CORPUS, 2, TC.HUMANUS, 1, TC.PRAECANTIO, 1).heat(350).setLocal("Magic Tallow"),
	Leather                 = create        ( 8241, "Leather"               , SET_ROUGH             , 141,  65,  37, 255).put(FLAMMABLE)                                                                                                                                                                                                                                                                                            .aspects(TC.PANNUS, 1, TC.TUTAMEN, 1).setBurning(Ash, U9).setSmelting(Ash, U9),
	Indigo                  = dust          ( 8228, "Indigo"                , SET_LEAF              , 255, 128, 255, 255).put(FLAMMABLE)                                                                                                                                                                                                                                                                                            .aspects(TC.SENSUS, 1),
	
	
	MeatCooked              = meat          ( 9701, "MeatCooked"                                    , 150,  60,  20, 255).put("Meat", MD.MFR)                                                                                                                                                                                                                                                                                       .aspects(TC.CORPUS, 2).heat(477, 550).setLocal("Cooked Meat"),
	MeatRaw                 = meat          ( 9700, "MeatRaw"                                       , 255, 100, 100, 255).put(FURNACE, MD.MFR)                                                                                                                                                                                                                                                                                      .aspects(TC.CORPUS, 1).heat(477, 550).setLocal("Raw Meat").setSmelting(MeatCooked, U).setForging(MeatCooked, U),
	MeatRotten              = meat          ( 9710, "MeatRotten"                                    , 255, 150, 100, 255).put(ROTTEN)                                                                                                                                                                                                                                                                                               .aspects(TC.CORPUS, 1, TC.MORTUUS, 1).heat(477, 550).setLocal("Rotten Meat"),
	FishCooked              = meat          ( 9711, "FishCooked"                                    , 150, 120,  20, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.CORPUS, 2).heat(477, 550).setLocal("Cooked Fishmeal"),
	FishRaw                 = meat          ( 9712, "FishRaw"                                       , 255, 150, 100, 255).put(FURNACE)                                                                                                                                                                                                                                                                                              .aspects(TC.CORPUS, 1).heat(477, 550).setLocal("Raw Fishmeal").setSmelting(FishCooked, U).setForging(FishCooked, U),
	FishRotten              = meat          ( 9713, "FishRotten"                                    , 220, 200, 100, 255).put(ROTTEN)                                                                                                                                                                                                                                                                                               .aspects(TC.CORPUS, 1, TC.MORTUUS, 1).heat(477, 550).setLocal("Rotten Fishmeal"),
	Wheat                   = grain         ( 9702, "Wheat"                                         , 255, 255, 196, 255).put("Flour")                                                                                                                                                                                                                                                                                              .aspects(TC.MESSIS, 2).setBurning(Ash, U9),
	Barley                  = grain         ( 9704, "Barley"                                        , 196, 255, 196, 255).put(MD.HaC)                                                                                                                                                                                                                                                                                               .aspects(TC.MESSIS, 2).setBurning(Ash, U9),
	Rye                     = grain         ( 9705, "Rye"                                           , 255, 230, 180, 255).put(MD.HaC)                                                                                                                                                                                                                                                                                               .aspects(TC.MESSIS, 2).setBurning(Ash, U9),
	Rice                    = grain         ( 9706, "Rice"                                          , 252, 252, 240, 255).put(MD.HaC)                                                                                                                                                                                                                                                                                               .aspects(TC.MESSIS, 2).setBurning(Ash, U9),
	Oat                     = grain         ( 9707, "Oat"                                           , 240, 240, 222, 255).put(MD.HaC, "Oats")                                                                                                                                                                                                                                                                                       .aspects(TC.MESSIS, 2).setBurning(Ash, U9),
	Corn                    = grain         ( 9708, "Corn"                                          , 250, 240, 111, 255).put(MD.HaC)                                                                                                                                                                                                                                                                                               .aspects(TC.MESSIS, 2).setBurning(Ash, U9),
	Potato                  = dustfood      ( 9709, "Potato"                , SET_POWDER            , 240, 240, 164, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.MESSIS, 2).setBurning(Ash, U9),
	Tofu                    = dustfood      ( 9778, "Tofu"                  , SET_FOOD              , 222, 222, 222, 255).put(INGOTS, MELTING, EXTRUDER, EXTRUDER_SIMPLE)                                                                                                                                                                                                                                                           .aspects(TC.FAMES, 1, TC.HERBA, 1).setOriginalMod(MD.HaC).heat(422, 500),
	SoylentGreen            = dustfood      ( 9779, "Soylent Green"         , SET_FOOD              ,   0, 222,   0, 255).put(INGOTS, MELTING, EXTRUDER, EXTRUDER_SIMPLE)                                                                                                                                                                                                                                                           .aspects(TC.FAMES, 1, TC.CORPUS, 1).heat(422, 500).setLocal("Emerald Green"),
	Cheese                  = orefood       ( 9780, "Cheese"                                        , 255, 234,   0, 255).put(INGOTS, MELTING, EXTRUDER, EXTRUDER_SIMPLE, FURNACE)                                                                                                                                                                                                                                                  .aspects(TC.FAMES, 1).heat(320, 500),
	Chili                   = dustfood      ( 9781, "Chili"                                         , 200,   0,   0, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.IGNIS, 1),
	Cocoa                   = dustfood      ( 9782, "Cocoa"                                         , 190,  95,   0, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.SANO, 1),
	Chocolate               = mixfood       ( 9783, "Chocolate"                                     , 100,  50,   0, 255).put(FURNACE, INGOTS, MELTING, EXTRUDER, EXTRUDER_SIMPLE)                                                                                      .setMcfg( 0, Cocoa          , 1*U, Sugar            , 1*U)                                                                                                  .aspects(TC.SANO, 1, TC.FAMES, 1).heat(CS.C+40, 400),
	Coffee                  = dustfood      ( 9784, "Coffee"                                        , 150,  75,   0, 255).put("CoffeeDust")                                                                                                                                                                                                                                                                                         .aspects(TC.MOTUS, 1),
	Cinnamon                = dustfood      ( 9785, "Cinnamon"                                      , 122,  83,  53, 255).put(TICKS_PER_SMELT/ 2)                                                                                                                                                                                                                                                                                   .aspects(TC.FAMES, 1).setOriginalMod(MD.HaC),
	Nutmeg                  = dustfood      ( 9786, "Nutmeg"                                        , 240, 220, 180, 255).put(TICKS_PER_SMELT/ 2)                                                                                                                                                                                                                                                                                   .aspects(TC.FAMES, 1).setOriginalMod(MD.HaC),
	Peanut                  = dustfood      ( 9795, "Peanut"                                        , 240, 210, 160, 255).put(TICKS_PER_SMELT/ 2)                                                                                                                                                                                                                                                                                   .aspects(TC.FAMES, 1).setOriginalMod(MD.HaC),
	Hazelnut                = dustfood      ( 9796, "Hazelnut"                                      , 240, 200, 140, 255).put(TICKS_PER_SMELT/ 2)                                                                                                                                                                                                                                                                                   .aspects(TC.FAMES, 1).setOriginalMod(MD.BINNIE),
	Pistachio               = dustfood      ( 9797, "Pistachio"                                     , 200, 250, 140, 255).put(TICKS_PER_SMELT/ 2)                                                                                                                                                                                                                                                                                   .aspects(TC.FAMES, 1).setOriginalMod(MD.HaC),
	Almond                  = dustfood      ( 9714, "Almond"                                        , 200, 160, 140, 255).put(TICKS_PER_SMELT/ 2)                                                                                                                                                                                                                                                                                   .aspects(TC.FAMES, 1).setOriginalMod(MD.HaC),
	PEZ                     = orefood       ( 9716, "PEZ"                                           , 210, 210, 210, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.FAMES, 1).setOriginalMod(MD.CANDY).qual(2,  8.0,  512, 3),
	Licorice                = orefood       ( 9717, "Licorice"                                      ,  30,  30,  30, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.FAMES, 1).setOriginalMod(MD.CANDY).qual(2,  6.0,  128, 2),
	Nougat                  = orefood       ( 9718, "Nougat"                                        , 240, 200, 150, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.FAMES, 1).setOriginalMod(MD.CANDY),
	Vanilla                 = dustfood      ( 9787, "Vanilla"                                       , 110,  80,  40, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.FAMES, 1).setOriginalMod(MD.HaC),
	PepperBlack             = dustfood      ( 9788, "PepperBlack"                                   ,  40,  40,  40, 255).put("Pepper")                                                                                                                                                                                                                                                                                             .aspects(TC.FAMES, 1).setOriginalMod(MD.HaC).setLocal("Black Pepper"),
	Curry                   = dustfood      ( 9789, "Curry"                                         , 240, 190, 100, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.FAMES, 1).setOriginalMod(MD.HaC),
	Milk                    = food          ( 9790, "Milk"                  , SET_FINE              , 254, 254, 254, 255).put(G_CONTAINERS, DUSTS)                                                                                                                                                                                                                                                                                  .aspects(TC.SANO, 2).heat(CS.C, CS.C+100),
	Butter                  = food          ( 9798, "Butter"                                        , 230, 230, 100, 255).put(INGOTS, MELTING)                                                                                                                          .setMcfg( 1, Milk           , 1*U)                                                                                                                          .aspects(TC.FAMES, 3).heat(CS.C+40, 500).setOriginalMod(MD.GrC),
	ButterSalted            = food          ( 9799, "Salted Butter"                                 , 230, 230, 110, 255).put(INGOTS, MELTING)                                                                                                                          .setMcfg( 1, Milk           , 1*U, NaCl             , 1*U)                                                                                                  .aspects(TC.FAMES, 3).heat(CS.C+40, 500).setOriginalMod(MD.HaC),
	Honey                   = orefood       ( 9791, "Honey"                                         , 250, 200,   0, 255).put(G_CONTAINERS)                                                                                                                                                                                                                                                                                         .aspects(TC.SANO, 1).heat(CS.C, CS.C+100).setOriginalMod(MD.FR),
	Honeydew                = orefood       ( 9793, "Honeydew"                                      , 210, 100,   0, 255).put(G_CONTAINERS)                                                                                                                                                                                                                                                                                         .aspects(TC.SANO, 1).heat(CS.C, CS.C+100).setOriginalMod(MD.FR),
	Tea                     = dustfood      ( 9792, "Tea"                                           , 100, 250, 100, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.SANO, 1),
	Mint                    = dustfood      ( 9794, "Mint"                                          , 150, 250, 150, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.HERBA, 1),
	
	
	Sand                    = create        ( 8100, "Sand"                  , SET_SAND              , 250, 250, 200, 255).put(FURNACE, UNRECYCLABLE)                                                                                                                                                                                                                                                                                .setSmelting(Glass, U),
	SoulSand                = oredust       ( 8101, "Soulsand"              , SET_SAND              , 100, 100,  80, 255)                                                                                                                                                                                                                                                                                                           ,
	SluiceSand              = oredust       ( 8102, "Sluice Sand"           , SET_SAND              , 165, 165, 120, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA, 1),
	PlatinumGroupSludge     = oredust       ( 8103, "Platinum Group Sludge" , SET_SAND              ,  50,  50,  80, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.LUCRUM, 2),
	
	
	
	Diamond                 = diamond       ( 8300, "Diamond"                                       , 200, 255, 255, DYE_INDEX_White      )                                                                                                                             .uumMcfg( 1, C              , 4*U)                                                                                                                                                     .setDensity(3.53).heat(4200, C.mBoilingPoint),
	DiamondPink             = diamond       ( 8424, "Pink Diamond"                                  , 240, 160, 160, DYE_INDEX_Pink       )                                                                                                                             .uumMcfg( 1, C              , 4*U)                                                                                                                          .aspects(TC.AURAM      , 4).setDensity(3.53).heat(4200, C.mBoilingPoint).qual(3, 12.0,  1600,  3),
	DiamondIndustrial       = diamond       ( 8423, "DiamondIndustrial"                             , 255, 255, 210, DYE_INDEX_Yellow     )                                                                                                                             .uumMcfg( 1, C              , 4*U)                                                                                                                          .aspects(TC.FABRICO    , 2).setDensity(3.53).heat(4200, C.mBoilingPoint).qual(3,  9.0,  1440,  3).setLocal("Industrial Diamond"),
	ManaDiamond             = diamond       ( 8278, "Mana Diamond"                                  , 128, 255, 255, DYE_INDEX_Cyan       ).put(MAGICAL, UNBURNABLE)                                                                                                    .setMcfg( 1, C              , 4*U, Ma               , 1*U)                                                                                                  .aspects(TC.PRAECANTIO , 2).setDensity(3.53).heat(4200, C.mBoilingPoint).qual(3, 10.0,  1280,  3),
	ElvenDragonstone        = diamond       ( 8279, "Elven Dragonstone"                             , 240, 140, 240, DYE_INDEX_Magenta    ).put(MAGICAL, UNBURNABLE)                                                                                                    .setMcfg( 1, C              , 4*U, Ma               , 2*U)                                                                                                  .aspects(TC.PRAECANTIO , 4).setDensity(3.53).heat(4200, C.mBoilingPoint).qual(3, 12.0,  1280,  3).setLocal("Dragonstone"),
	
	
	Emerald                 = emerald       ( 8301, "Emerald"                                       ,  80, 255,  80, DYE_INDEX_Green      )                                                                                                                             .uumMcfg( 0, Al2O3          , 5*U, Be               , 3*U, SiO2             ,18*U, O                , 3*U)                                                  .aspects(TC.LUCRUM     , 2),
	Aquamarine              = emerald       ( 8323, "Aquamarine"                                    , 200, 220, 255, DYE_INDEX_Cyan       )                                                                                                                             .uumMcfg( 0, Al2O3          , 5*U, Be               , 3*U, SiO2             ,18*U, O                , 3*U)                                                  .aspects(TC.AQUA       , 2),
	Morganite               = emerald       ( 8324, "Morganite"                                     , 255, 200, 200, DYE_INDEX_Pink       )                                                                                                                             .uumMcfg( 0, Al2O3          , 5*U, Be               , 3*U, SiO2             ,18*U, O                , 3*U)                                                  .aspects(TC.TEMPESTAS  , 2),
	Heliodor                = emerald       ( 8384, "Heliodor"                                      , 255, 255, 150, DYE_INDEX_Yellow     )                                                                                                                             .uumMcfg( 0, Al2O3          , 5*U, Be               , 3*U, SiO2             ,18*U, O                , 3*U)                                                  .aspects(TC.LUX        , 2),
	Goshenite               = emerald       ( 8385, "Goshenite"                                     , 240, 240, 240, DYE_INDEX_White      )                                                                                                                             .uumMcfg( 0, Al2O3          , 5*U, Be               , 3*U, SiO2             ,18*U, O                , 3*U)                                                  .aspects(TC.VACUOS     , 2),
	Bixbite                 = emerald       ( 8386, "Bixbite"                                       , 255,  80,  80, DYE_INDEX_Red        ).put("ScarletEmerald")                                                                                                       .uumMcfg( 0, Al2O3          , 5*U, Be               , 3*U, SiO2             ,18*U, O                , 3*U)                                                  .aspects(TC.SANO       , 2),
	Maxixe                  = emerald       ( 8387, "Maxixe"                                        ,  80,  80, 255, DYE_INDEX_Blue       )                                                                                                                             .uumMcfg( 0, Al2O3          , 5*U, Be               , 3*U, SiO2             ,18*U, O                , 3*U)                                                  .aspects(TC.RADIO      , 2),
	
	
	Sapphire                = sapphire      ( 8304, "Sapphire"                                      , 120, 120, 160, DYE_INDEX_Blue       ).put("Saphire")                                                                                                              .uumMcfg( 6, Al2O3          , 5*U)                                                                                                                          .aspects(TC.LUCRUM     , 1),
	GreenSapphire           = sapphire      ( 8305, "Green Sapphire"                                , 100, 200, 130, DYE_INDEX_Green      )                                                                                                                             .uumMcfg( 6, Al2O3          , 5*U, Mg               , 1*U)                                                                                                  .aspects(TC.SANO       , 1),
	YellowSapphire          = sapphire      ( 8315, "Yellow Sapphire"                               , 220, 220,  50, DYE_INDEX_Yellow     )                                                                                                                             .uumMcfg( 6, Al2O3          , 5*U, TiO2             , 1*U)                                                                                                  .aspects(TC.TUTAMEN    , 1),
	OrangeSapphire          = sapphire      ( 8314, "Orange Sapphire"                               , 220, 150,  50, DYE_INDEX_Orange     )                                                                                                                             .uumMcfg( 6, Al2O3          , 5*U, Cu               , 1*U)                                                                                                  .aspects(TC.PERMUTATIO , 1),
	BlueSapphire            = sapphire      ( 8328, "Blue Sapphire"                                 , 100, 100, 200, DYE_INDEX_Blue       )                                                                                                                             .uumMcfg( 6, Al2O3          , 5*U, Fe               , 1*U)                                                                                                  .aspects(TC.METALLUM   , 1),
	PurpleSapphire          = sapphire      ( 8383, "Purple Sapphire"                               , 220,  50, 255, DYE_INDEX_Purple     )                                                                                                                             .uumMcfg( 6, Al2O3          , 5*U, V                , 1*U)                                                                                                  .aspects(TC.ELECTRUM   , 1),
	Ruby                    = sapphire      ( 8302, "Ruby"                                          , 255, 100, 100, DYE_INDEX_Red        )                                                                                                                             .uumMcfg( 6, Al2O3          , 5*U, Cr               , 1*U)                                                                                                  .aspects(TC.MACHINA    , 1),
	
	
	Spinel                  = valgemelec    ( 8326, "Spinel"                , SET_GEM_VERTICAL      ,   0, 100,   0, 127).lens(DYE_INDEX_Green      )                                                                                                                   .uumMcfg( 0, Al2O3          , 5*U, Mg               , 1*U, O                , 1*U)                                                                          .aspects(TC.VITREUS, 2, TC.LUCRUM                   , 3).qual(3,  7.0,   384,  2),
	BalasRuby               = valgemelec    ( 8303, "Balas Ruby"            , SET_GEM_VERTICAL      , 255, 100, 100, 127).lens(DYE_INDEX_Red        ).put("FoolsRuby").setLocal("Ruby").steal(Ruby)                                                                     .uumMcfg( 0, Cr             , 2*U, Mg               , 1*U, O                , 4*U)                                                                          .aspects(TC.VITREUS, 2, TC.LUCRUM, 2, TC.STRONTIO   , 2).qual(3,  7.0,   384,  2),
	
	
	Almandine               = garnet        ( 9101, "Almandine"                                     , 255,   0,   0, DYE_INDEX_Red        ).put("GarnetRed")                                                                                                            .uumMcfg( 0, Al2O3          , 5*U, Fe               , 3*U, SiO2             , 9*U, O                , 3*U)                                                  .aspects(TC.METALLUM   , 1),
	Grossular               = garnet        ( 9119, "Grossular"                                     , 200, 100,   0, DYE_INDEX_Orange     ).put("GarnetOrange")                                                                                                         .uumMcfg( 0, Al2O3          , 5*U, Ca               , 3*U, SiO2             , 9*U, O                , 3*U)                                                  .aspects(TC.VOLATUS    , 1),
	Pyrope                  = garnet        ( 9127, "Pyrope"                                        , 120,  50, 100, DYE_INDEX_Purple     ).put("GarnetPurple")                                                                                                         .uumMcfg( 0, Al2O3          , 5*U, Mg               , 3*U, SiO2             , 9*U, O                , 3*U)                                                  .aspects(TC.SANO       , 1),
	Spessartine             = garnet        ( 9129, "Spessartine"                                   , 255, 100, 100, DYE_INDEX_Red        ).put("Garnet")                                                                                                               .uumMcfg( 0, Al2O3          , 5*U, Mn               , 3*U, SiO2             , 9*U, O                , 3*U)                                                  .aspects(TC.METALLUM   , 1),
	Andradite               = garnet        ( 9102, "Andradite"                                     , 150, 120,   0, DYE_INDEX_Yellow     ).put("GarnetYellow")                                                                                                         .uumMcfg( 0, Ca             , 3*U, Fe               , 2*U, SiO2             , 9*U, O                , 6*U)                                                  .aspects(TC.TUTAMEN    , 1),
	Uvarovite               = garnet        ( 9135, "Uvarovite"                                     , 180, 255, 180, DYE_INDEX_Lime       ).put("GarnetGreen")                                                                                                          .uumMcfg( 0, Ca             , 3*U, Cr               , 2*U, SiO2             , 9*U, O                , 6*U)                                                  .aspects(TC.MACHINA    , 1),
	
	
	Jasper                  = jasper        ( 8309, "Red Jasper"                                    , 200,  80,  80, DYE_INDEX_Red        ).put("Jasper")                                                                                                                                                                                                                                                                           ,
	JasperOcean             = jasper        ( 8425, "Ocean Jasper"                                  , 139, 115,  86, DYE_INDEX_Orange     )                                                                                                                                                                                                                                                                                         ,
	JasperRainforest        = jasper        ( 8426, "Rainforest Jasper"                             , 129, 123,  55, DYE_INDEX_Yellow     )                                                                                                                                                                                                                                                                                         ,
	JasperBlue              = jasper        ( 8427, "Blue Jasper"                                   ,  60, 124, 151, DYE_INDEX_Blue       )                                                                                                                                                                                                                                                                                         ,
	JasperGreen             = jasper        ( 8428, "Green Jasper"                                  ,  91, 131, 108, DYE_INDEX_Green      )                                                                                                                                                                                                                                                                                         ,
	JasperYellow            = jasper        ( 8429, "Yellow Jasper"                                 , 156, 128,  39, DYE_INDEX_Yellow     )                                                                                                                                                                                                                                                                                         ,
	
	
	TigerEyeYellow          = tigereye      ( 8430, "Tiger Eye"                                     , 142, 116,  61, DYE_INDEX_Yellow     ).put("YellowTigerEye")                                                                                                                                                                                                                                                                   ,
	TigerEyeGreen           = tigereye      ( 8431, "Cat's Eye"                                     ,  77, 116,  81, DYE_INDEX_Green      ).put("GreenTigerEye" )                                                                                                                                                                                                                                                                   ,
	TigerEyeRed             = tigereye      ( 8432, "Dragon Eye"                                    , 164,  95,  83, DYE_INDEX_Red        ).put("RedTigerEye"   )                                                                                                                                                                                                                                                                   ,
	TigerEyeBlue            = tigereye      ( 8433, "Hawk's Eye"                                    ,  76,  94, 109, DYE_INDEX_Blue       ).put("BlueTigerEye"  )                                                                                                                                                                                                                                                                   ,
	TigerEyeBlack           = tigereye      ( 8434, "Black Eye"                                     ,  66,  68,  66, DYE_INDEX_Black      ).put("BlackTigerEye" )                                                                                                                                                                                                                                                                   ,
	TigerIron               = tigereye      ( 8435, "Tiger Iron"                                    , 106,  86,  66, DYE_INDEX_Yellow     )                                                                                                                                                                                                                                                                                         ,
	
	
	AventurineGreen         = aventurine    ( 8448, "Green Aventurine"                              ,  66, 189, 133, DYE_INDEX_Green      ).put("Aventurine"),
	AventurineBrown         = aventurine    ( 8446, "Brown Aventurine"                              , 185, 110,  35, DYE_INDEX_Brown      ),
	AventurineYellow        = aventurine    ( 8447, "Yellow Aventurine"                             , 200, 179, 121, DYE_INDEX_Yellow     ),
	AventurineBlack         = aventurine    ( 8449, "Black Aventurine"                              ,  50,  50,  50, DYE_INDEX_Black      ),
	AventurineBlue          = aventurine    ( 8450, "Blue Aventurine"                               ,  67, 103, 138, DYE_INDEX_Blue       ),
	AventurineRed           = aventurine    ( 8451, "Red Aventurine"                                , 116,  46,  33, DYE_INDEX_Red        ),
	
	
	Topaz                   = valgemelec    ( 8306, "Topaz"                 , SET_GEM_HORIZONTAL    , 255, 128,   0, 127).lens(DYE_INDEX_Orange     )                                                                                                                   .uumMcfg( 0, Al2O3          , 5*U, SiO2             , 3*U, F                , 2*U, H2O              , 3*U)                                                  .aspects(TC.VITREUS, 4, TC.LUCRUM, 4, TC.POTENTIA   , 2).qual(3,  7.0,   256,  3),
	BlueTopaz               = valgemelec    ( 8307, "Blue Topaz"            , SET_GEM_HORIZONTAL    , 123, 150, 220, 127).lens(DYE_INDEX_Blue       )                                                                                                                   .uumMcfg( 0, Al2O3          , 5*U, SiO2             , 3*U, F                , 2*U, H2O              , 3*U)                                                  .aspects(TC.VITREUS, 4, TC.LUCRUM, 4, TC.POTENTIA   , 2).qual(3,  7.0,   256,  3),
	Tanzanite               = valgemelec    ( 8308, "Tanzanite"             , SET_GEM_HORIZONTAL    ,  64,   0, 200, 127).lens(DYE_INDEX_Purple     )                                                                                                                   .uumMcfg( 0, Al2O3          ,15*U, SiO2             ,18*U, Ca               , 4*U, H2O              , 3*U, O                , 4*U)                          .aspects(TC.VITREUS, 3, TC.LUCRUM                   , 5).qual(3,  7.0,   256,  2),
	Amazonite               = valgemelec    ( 8417, "Amazonite"             , SET_LAPIS             ,  71, 241, 170, 255).lens(DYE_INDEX_Green      ).put(MD.VOLTZ)                                                                                                     .uumMcfg( 0, Al2O3          , 5*U, SiO2             ,18*U, K                , 2*U, O                , 1*U)                                                  .aspects(TC.VITREUS, 3, TC.LUCRUM                   , 5).qual(3,  7.0,   256,  2),
	Alexandrite             = valgemelec    ( 8388, "Alexandrite"           , SET_OPAL              , 255, 255,   0, 127).lens(DYE_INDEX_Yellow     )                                                                                                                   .uumMcfg( 0, Al2O3          , 1*U, Be               , 1*U, O                , 1*U)                                                                          .aspects(TC.VITREUS, 4, TC.LUCRUM, 2, TC.AURAM      , 1).qual(3,  7.0,   256,  2),
	Opal                    = valgemelec    ( 8312, "Opal"                  , SET_OPAL              ,   0,   0, 255, 127).lens(DYE_INDEX_Blue       )                                                                                                                   .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.VITREUS, 4, TC.LUCRUM, 2, TC.AURAM      , 1).qual(3,  7.0,   256,  2),
	OnyxRed                 = valgemelec    ( 8415, "OnyxRed"               , SET_LAPIS             , 255,  88,  77, 255).lens(DYE_INDEX_Red        ).put(MD.VOLTZ)                                                                                                     .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.VITREUS, 4, TC.LUCRUM                   , 2).qual(3,  7.0,   256,  2).setLocal("Red Onyx"),
	OnyxBlack               = valgemelec    ( 8416, "OnyxBlack"             , SET_LAPIS             ,  50,  50,  50, 255).lens(DYE_INDEX_Black      ).put(MD.VOLTZ, "Onyx")                                                                                             .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.VITREUS, 4, TC.LUCRUM                   , 2).qual(3,  7.0,   256,  2).setLocal("Black Onyx"),
	Olivine                 = valgemelec    ( 8311, "Olivine"               , SET_RUBY              , 150, 255, 150, 127).lens(DYE_INDEX_Lime       ).put("Peridot")                                                                                                    .uumMcfg( 0, SiO2           , 2*U, Fe               , 1*U, Mg               , 2*U)                                                                          .aspects(TC.VITREUS, 2, TC.LUCRUM                   , 4).qual(3,  7.0,   256,  2),
	Amethyst                = valgemelec    ( 8313, "Amethyst"              , SET_RUBY              , 200,  50, 200, 127).lens(DYE_INDEX_Pink       )                                                                                                                   .uumMcfg( 0, SiO2           , 4*U, Fe               , 1*U)                                                                                                  .aspects(TC.VITREUS, 4, TC.LUCRUM                   , 6).qual(3,  7.0,   256,  3),
	Dioptase                = valgemelec    ( 8325, "Dioptase"              , SET_EMERALD           ,   0, 180, 180, 127).lens(DYE_INDEX_Cyan       )                                                                                                                   .uumMcfg( 0, SiO2           , 3*U, Cu               , 1*U, O                , 1*U, H2O              , 3*U)                                                  .aspects(TC.VITREUS, 3, TC.LUCRUM, 3, TC.PERMUTATIO , 2).qual(3,  7.0,   256,  2),
	Amber                   = valgem        ( 8310, "Amber"                 , SET_RUBY              , 255, 180,   0, 127).lens(DYE_INDEX_Orange     ).put(MORTAR, CRYSTALLISABLE)                                                                                                                                                                                                                                                   .aspects(TC.VITREUS, 1, TC.VINCULUM                 , 2).qual(3,  4.0,   128,  2),
	AmberDominican          = valgem        ( 8422, "DominicanAmber"        , SET_RUBY              ,  80,  80, 240, 127).lens(DYE_INDEX_Blue       ).put(MORTAR, CRYSTALLISABLE)                                                                                                                                                                                                                                                   .aspects(TC.VITREUS, 1, TC.VINCULUM                 , 2).qual(3,  4.0,   256,  2),
	Craponite               = valgem        ( 8322, "Craponite"             , SET_FLINT             , 255, 170, 185, 127).lens(DYE_INDEX_Magenta    ).put(MORTAR, CRYSTALLISABLE)                                                                                                                                                                                                                                                   .aspects(TC.VITREUS, 3, TC.LUCRUM, 3, TC.STRONTIO   , 2).qual(3,  7.0,   256,  2),
	Jade                    = valgem        ( 8321, "Jade"                  , SET_LAPIS             , 100, 255, 125, 255).lens(DYE_INDEX_Green      ).put(MD.ERE)                                                                                                                                                                                                                                                                   .aspects(TC.VITREUS, 3, TC.LUCRUM                   , 6).qual(3,  8.0,   512,  2),
	
	
	/*
	Sunstone                = valgem        (00000, "Sunstone"              , SET_RUBY              , 194, 136, 108, 127),
	Moonstone               = valgem        (00000, "Moonstone"             , SET_RUBY              , 166, 174, 171, 127),
	Bloodstone              = valgem        (00000, "Bloodstone"            , SET_RUBY              ,  71,  98,  70, 127),
	Wonderstone             = valgem        (00000, "Wonderstone"           , SET_RUBY              , 166, 136, 115, 127),
	
	Jet                     = valgem        (00000, "Jet"                   , SET_RUBY              ,  39,  39,  39, 127),
	LapisLazuli             = valgem        (00000, "Lapis Lazuli"          , SET_RUBY              ,  44,  52, 101, 127),
	SnowflakeObsidian       = valgem        (00000, "Snowflake Obsidian"    , SET_RUBY              ,  70,  66,  63, 127),
	BlackBeryl              = valgem        (00000, "Black Beryl"           , SET_RUBY              ,  57,  57,  57, 127),
	
	Rhodonite               = valgem        (00000, "Rhodonite"             , SET_RUBY              , 178, 121, 135, 127),
	Labradorite             = valgem        (00000, "Labradorite"           , SET_RUBY              ,  27, 133, 152, 127),
	Dumortierite            = valgem        (00000, "Dumortierite"          , SET_RUBY              ,  54,  57, 100, 127),
	Moissanite              = valgem        (00000, "Moissanite"            , SET_RUBY              , 164, 165, 165, 127),
	Mookaite                = valgem        (00000, "Mookaite"              , SET_RUBY              , 143,  80,  29, 127),
	Larimar                 = valgem        (00000, "Larimar"               , SET_RUBY              , 129, 196, 196, 127),
	Charoite                = valgem        (00000, "Charoite"              , SET_RUBY              , 125,  95, 161, 127),
	Carnelian               = valgem        (00000, "Carnelian"             , SET_RUBY              , 136,  40,  14, 127),
	Unakite                 = valgem        (00000, "Unakite"               , SET_RUBY              , 153, 140,  97, 127),
	Selenite                = valgem        (00000, "Selenite"              , SET_RUBY              , 168, 168, 168, 127),
	Pietersite              = valgem        (00000, "Pietersite"            , SET_RUBY              , 127,  97, 117, 127),
	Chrysocolla             = valgem        (00000, "Chrysocolla"           , SET_RUBY              ,  95, 179, 180, 127),
	Coral                   = valgem        (00000, "Coral"                 , SET_RUBY              , 152,  40,  38, 127),
	Basanite                = valgem        (00000, "Basanite"              , SET_RUBY              ,  50,  48,  40, 127),
	Kunzite                 = valgem        (00000, "Kunzite"               , SET_RUBY              , 189, 166, 192, 127),
	Angelite                = valgem        (00000, "Angelite"              , SET_RUBY              ,  96, 119, 139, 127),
	Scapolite               = valgem        (00000, "Scapolite"             , SET_RUBY              , 167, 168, 130, 127),
	Sugilite                = valgem        (00000, "Sugilite"              , SET_RUBY              , 154, 100, 154, 127),
	Chrysoprase             = valgem        (00000, "Chrysoprase"           , SET_RUBY              ,  68,  93,  49, 127),
	Ametrine                = valgem        (00000, "Ametrine"              , SET_RUBY              , 182, 141, 149, 127),
	*/
	
	Vinteum                 = dcmp          ( 8316, "Vinteum"               , SET_EMERALD           ,  80,  80, 255, 255).put(G_GEM_ORES, MAGICAL, CRYSTAL, MORTAR, BRITTLE, UNBURNABLE)                                                                                .setMcfg( 0, Ma             , 1*U)                                                                                                                          .aspects(TC.VITREUS, 1, TC.PRAECANTIO, 2).qual(3, 10.0, 128,  3),
	VinteumPurified         = dcmp          ( 8327, "VinteumPurified"       , SET_EMERALD           , 230, 100, 255, 255).put(G_GEM_ORES, MAGICAL, CRYSTAL, MORTAR, BRITTLE, UNBURNABLE)                                                                                .setMcfg( 0, Ma             , 1*U)                                                                                                                          .aspects(TC.ORDO, 2, TC.PRAECANTIO, 6).qual(3, 12.0, 256,  3).setLocal("Purified Vinteum"),
	ArcaneAsh               = dust          ( 8367, "Arcane Ashes"          , SET_FINE              , 150,  50, 180, 255).put(MAGICAL, "ArcaneAsh")                                                                                                                                                                                                                                                                                 .aspects(TC.PRAECANTIO, 1, TC.IGNIS, 1),
	ArcaneCompound          = dust          ( 8366, "Arcane Compound"       , SET_ROUGH             , 180, 140,  50, 255).put(MAGICAL, FURNACE)                                                                                                                                                                                                                                                                                     .aspects(TC.PRAECANTIO, 3).setSmelting(ArcaneAsh, U*2),
	Moonstone               = gem           ( 8452, "Moonstone"             , SET_RUBY              , 210, 210, 255, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.ALIENIS, 1, TC.PRAECANTIO, 1, TC.VACUOS, 1),
	Sunstone                = gem           ( 8453, "Sunstone"              , SET_RUBY              , 255, 150,   0, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.VITREUS, 1, TC.IGNIS, 3),
	Chimerite               = gem           ( 8454, "Chimerite"             , SET_RUBY              , 255, 255, 255, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.SENSUS, 3, TC.PERMUTATIO, 1),
	
	
	CrimsonMiddle           = gem           ( 8282, "Crimson Middle"        , SET_DIAMOND           , 240,  50,  50, 127).lens(DYE_INDEX_Red        ).put(GLOWING, LIGHTING, MORTAR, BRITTLE)                                                                                                                                                                                                                                       .aspects(TC.VITREUS, 2, TC.LUX, 2, TC.IGNIS         , 2),
	GreenMiddle             = gem           ( 8283, "Green Middle"          , SET_DIAMOND           ,  50, 240,  50, 127).lens(DYE_INDEX_Green      ).put(GLOWING, LIGHTING, MORTAR, BRITTLE)                                                                                                                                                                                                                                       .aspects(TC.VITREUS, 2, TC.LUX, 2, TC.HERBA         , 2),
	AquaMiddle              = gem           ( 8284, "Aqua Middle"           , SET_DIAMOND           ,  50,  50, 240, 127).lens(DYE_INDEX_Blue       ).put(GLOWING, LIGHTING, MORTAR, BRITTLE)                                                                                                                                                                                                                                       .aspects(TC.VITREUS, 2, TC.LUX, 2, TC.AQUA          , 2),
	Valonite                = gem           ( 8285, "Valonite"              , SET_SHARDS            , 255, 205, 240, 127).lens(DYE_INDEX_Pink       )                                                                                                                                                                                                                                                                               .aspects(TC.VITREUS, 3, TC.LUCRUM                   , 4).qual(3,  9.0,  2560,  3),
	Scabyst                 = gem           ( 8288, "Scabyst"               , SET_SHARDS            , 110, 165, 165, 127).lens(DYE_INDEX_Cyan       )                                                                                                                                                                                                                                                                               .aspects(TC.VITREUS, 2, TC.SANO                     , 2),
	
	
	Zanite                  = valgem        ( 8292, "Zanite"                , SET_REDSTONE          , 146,  73, 255, 127).lens(DYE_INDEX_Purple     ).put(CRYSTALLISABLE)                                                                                                                                                                                                                                                           .aspects(TC.VITREUS, 3, TC.PERFODIO                 , 4).qual(3, 16.0,   512,  2),
	Ambrosium               = gem           ( 8293, "Ambrosium"             , SET_DIAMOND           , 244, 242,  96, 127).lens(DYE_INDEX_Yellow     ).put(CRYSTALLISABLE, GLOWING, LIGHTING)                                                                                                                                                                                                                                        .aspects(TC.VITREUS, 2, TC.LUX, 2, TC.PRAECANTIO    , 2).qual(3,  4.0,    16,  2),
	Gravitite               = valgem        ( 8294, "Gravitite"             , SET_DIAMOND           , 182,  91, 159, 127).lens(DYE_INDEX_Magenta    ).put(CRYSTALLISABLE)                                                                                               .setMcfg( 1, Gt             , 1*U, Ma               , 1*U)                                                                                                  .aspects(TC.VITREUS, 2, TC.VOLATUS                  , 4).qual(3,  8.0,   128,  1),
	Continuum               = gem           ( 8295, "Continuum"             , SET_RUBY              , 222, 129,  40, 127).lens(DYE_INDEX_Orange     ).put(CRYSTALLISABLE)                                                                                                                                                                                                                                                           .aspects(TC.VITREUS, 4, TC.NEBRISUM                 , 4).qual(3,  4.0,    64,  3),
	
	
	EnderAmethyst           = valgemelec    ( 8329, "AmethystEnder"         , SET_FLINT             , 210,  50, 210, 127).lens(DYE_INDEX_Pink       )                                                                                                                   .setMcfg( 5, SiO2           , 4*U, Fe               , 1*U, Ma               , 1*U)                                                                          .aspects(TC.VITREUS, 4, TC.LUCRUM, 4, TC.ALIENIS, 2).qual(3, 10.0, 2560, 3).setLocal("Ender Amethyst"),
	EnderPearl              = elec          ( 8318, "EnderPearl"            , SET_SHINY             , 108, 220, 200, 255).put(G_PEARL_TRANSPARENT, CRYSTAL, BRITTLE, MAGICAL, PEARL, MELTING, "Ender")                                                                  .setMcfg(10, Be             , 1*U, K                , 4*U, N                , 5*U, Ma               , 6*U)                                                  .aspects(TC.ALIENIS, 4, TC.ITER, 4, TC.PRAECANTIO, 2).qual(3,  1.0,  16,  1).setLocal("Enderpearl").heat(2723, 3785),
	EnderEye                = cent          ( 8319, "EnderEye"              , SET_SHINY             , 160, 250, 230, 255).put(G_PEARL_TRANSPARENT, CRYSTAL, BRITTLE, MAGICAL, PEARL, MELTING)                                                                           .setMcfg( 1, EnderPearl     , 1*U, Blaze            , 1*U)                                                                                                  .aspects(TC.SENSUS, 4, TC.ALIENIS, 4, TC.ITER, 4, TC.PRAECANTIO, 3, TC.IGNIS, 2).qual(3,  1.0,   16,  1).setLocal("Endereye").heat(3447, 4978),
	NetherStar              = crystal       ( 8320, "Nether Star"           , SET_NETHERSTAR        , 255, 255, 255, 255).put(BRITTLE, UNBURNABLE, MAGICAL, GLOWING, MELTING)                                                                                                                                                                                                                                                       .qual(3,  8.0,   5120,  4).heat(3896, 5127),
	
	
	Frezarite               = create        ( 8391, "Frezarite"             , SET_NETHERSTAR        , 255, 255, 255, 255).put(G_GEM, CRYSTAL, BRITTLE, MAGICAL)                                                                                                                                                                                                                                                                     .aspects(TC.VITREUS, 3, TC.GELUM, 3).qual(3, 4.0, 128, 2),
	RedMeteor               = create        ( 8392, "Red Meteor"            , SET_RUBY              , 255,  60,  60, 255).put(G_GEM, MAGICAL, GLOWING, UNBURNABLE)                                                                                                                                                                                                                                                                  .aspects(TC.VITREUS, 3, TC.ALIENIS, 3).qual(3, 8.0, 512, 3),
	
	
	Dilithium               = crystal       ( 8317, "Dilithium"             , SET_DIAMOND           , 153, 255, 255, 127).lens(DYE_INDEX_White      ).put(CRYSTALLISABLE, QUARTZ, MD.MO)                                                                                                                                                                                                                                            .setSmelting(null, 0),
	
	
	Zircon                  = valgemelec    ( 8419, "Zircon"                , SET_EMERALD           ,  99,  24,  29, 255).lens(DYE_INDEX_Red        )                                                                                                                   .uumMcfg( 0, Zr             , 1*U, SiO2             , 3*U, O                , 2*U)                                                                          .aspects(TC.VITREUS, 4).setSmelting(Zr, U9).qual(3, 8.0, 384, 2),
	Azurite                 = elec          ( 8420, "Azurite"               , SET_QUARTZ            , 109, 164, 247, 255).put(G_GEM_ORES, CRYSTAL, MORTAR, BRITTLE, FURNACE)                                                                                            .uumMcfg( 0, Cu             , 3*U, CO3              , 8*U, O                , 1*U, H2O              , 3*U)                                                  .aspects(TC.SENSUS , 1).setSmelting(Cu, U9),
	Eudialyte               = elec          ( 8421, "Eudialyte"             , SET_LAPIS             , 155,  96, 114, 255).put(G_GEM_ORES, CRYSTAL, MORTAR, BRITTLE)                                                                                                     .uumMcfg(64, Zircon         ,18*U, MnO2             , 3*U, Na               ,13*U, Ca               , 6*U, NaCl, U*4, SiO2, 75*U, O, 12*U)                  .aspects(TC.VITREUS, 1), // Na15Ca6(Fe,Mn)3Zr3SiO(O,OH,H2O)3 (Si3O9)2(Si9O27)2(OH,Cl)2
	
	
	Lazurite                = elec          ( 8330, "Lazurite"              , SET_LAPIS             , 100, 120, 255, 255).put(G_GEM_ORES, DENSEPLATES, CRYSTAL, CRYSTALLISABLE, MORTAR, BRITTLE)                                                                        .uumMcfg( 0, Al2O3          , 6*U, SiO2             , 6*U, Ca               , 8*U, Na               , 8*U)                                                  .aspects(TC.SENSUS, 1),
	Sodalite                = elec          ( 8331, "Sodalite"              , SET_LAPIS             ,  20,  20, 255, 255).put(G_GEM_ORES, DENSEPLATES, CRYSTAL, CRYSTALLISABLE, MORTAR, BRITTLE)                                                                        .uumMcfg( 0, Al2O3          , 3*U, SiO2             , 3*U, Na               , 4*U, Cl               , 1*U)                                                  .aspects(TC.SENSUS, 1),
	Lapis                   = cent          ( 8332, "Lapis"                 , SET_LAPIS             ,  70,  70, 220, 255).put(G_GEM_ORES, DENSEPLATES, CRYSTAL, CRYSTALLISABLE, MORTAR, BRITTLE)                                                                        .uumMcfg( 0, Lazurite       ,12*U, Sodalite         , 2*U, Pyrite           , 1*U, CaCO3            , 1*U)                                                  .aspects(TC.SENSUS, 1),
	
	
	Charcoal                = coal          ( 8336, "Charcoal"              , SET_LIGNITE           , 100,  70,  70, 255).put(TICKS_PER_SMELT* 8).setBurning(Ash, U4)                                                                                                   .uumMcfg( 0, C              , 1*U)                                                                                                                          .setSmelting(C, U2).heat(1700, C.mBoilingPoint).setDensity(0.929).aspects(TC.POTENTIA, 2, TC.IGNIS, 2),
	Coal                    = coal          ( 8334, "Coal"                  , SET_LIGNITE           ,  70,  70,  70, 255).put(TICKS_PER_SMELT* 8).setBurning(DarkAsh, U4)                                                                                               .uumMcfg( 1, C              , 2*U)                                                                                                                          .setSmelting(C, U2).heat(1700, C.mBoilingPoint).setDensity(0.929).aspects(TC.POTENTIA, 2, TC.IGNIS, 2),
	CoalCoke                = coal          ( 8349, "Coal Coke"             , SET_LIGNITE           , 140, 140, 170, 255).put(TICKS_PER_SMELT*16, "Coke").setBurning(DarkAsh, U9)                                                                                       .uumMcfg( 1, C              , 2*U)                                                                                                                          .setSmelting(C, U ).heat(1700, C.mBoilingPoint).setDensity(0.929).aspects(TC.POTENTIA, 3, TC.IGNIS, 1).steal(Coal),
	Anthracite              = coal          ( 8362, "Anthracite"            , SET_LIGNITE           ,  90,  90,  90, 255).put(TICKS_PER_SMELT*24).setBurning(DarkAsh, U2)                                                                                               .uumMcfg( 1, C              , 4*U)                                                                                                                          .setSmelting(C, U2).heat(1700, C.mBoilingPoint).aspects(TC.POTENTIA, 3, TC.IGNIS, 3),
	Prismane                = coal          ( 8363, "Prismane"              , SET_LIGNITE           , 115, 110, 110, 255).put(TICKS_PER_SMELT*48).setBurning(DarkAsh, U)                                                                                                .uumMcfg( 1, C              , 8*U)                                                                                                                          .aspects(TC.POTENTIA, 4, TC.IGNIS, 4),
	Lonsdaleite             = coal          ( 8364, "Lonsdaleite"           , SET_DIAMOND           , 140, 130, 130, 255).put(TICKS_PER_SMELT*96).setBurning(DarkAsh, U*2)                                                                                              .uumMcfg( 1, C              ,16*U)                                                                                                                          .aspects(TC.POTENTIA, 6, TC.IGNIS, 6),
	Lignite                 = coal          ( 8337, "Lignite"               , SET_LIGNITE           , 100,  70,  70, 255).put(TICKS_PER_SMELT* 2).setBurning(DarkAsh, U9)                                                                                               .setMcfg( 7, C              , 2*U, H2O              , 4*U, DarkAsh          , 1*U)                                                                          .setDensity(0.865).setLocal("Lignite Coal"),
	LigniteCoke             = coal          ( 8365, "Lignite Coke"          , SET_LIGNITE           , 140, 100, 100, 255).put(TICKS_PER_SMELT* 4).setBurning(DarkAsh, U18)                                                                                              .setMcfg( 7, C              , 2*U, DarkAsh          , 1*U)                                                                                                  .setDensity(0.865),
	PetCoke                 = coal          ( 8390, "Petroleum Coke"        , SET_LIGNITE           , 150, 150, 180, 255).put(TICKS_PER_SMELT*32, "PetCoke").setBurning(DarkAsh, U9)                                                                                    .uumMcfg( 1, C              , 2*U, S                , 1*U)                                                                                                  .setDensity(0.929).aspects(TC.POTENTIA, 3, TC.IGNIS, 1).steal(Coal),
	Peat                    = dust          ( 8360, "Peat"                  , SET_LIGNITE           ,  64,  40,  14, 255).put(TICKS_PER_SMELT*10, INGOTS, MULTIINGOTS, BRITTLE, FLAMMABLE, MORTAR).setBurning(Ash, U2)                                                                                                                                                                                                              .aspects(TC.POTENTIA, 2, TC.IGNIS, 2).setOriginalMod(MD.FR),
	PeatBituminous          = dust          ( 8361, "PeatBituminous"        , SET_LIGNITE           ,  80,  40,  10, 255).put(TICKS_PER_SMELT*12, INGOTS, MULTIINGOTS, BRITTLE, FLAMMABLE, MORTAR)                                                                                                                                                                                                                                  .aspects(TC.POTENTIA, 3, TC.IGNIS, 3, TC.PERDITIO, 3).setOriginalMod(MD.FR).setLocal("Bituminous Peat"),
	
	
	HydratedCoal            = mixdust       ( 8335, "Hydrated Coal"         , SET_LIGNITE           ,  70,  70, 100, 255).put(BRITTLE, FURNACE, MORTAR, COAL)                                                                                                           .uumMcfg( 8, Coal           , 8*U, H2O              , 1*U)                                                                                                  ,
	Graphene                = dcmp          ( 9175, "Graphene"              , SET_DULL              , 128, 128, 128, 255).put(G_MACHINE, BLACKLISTED_SMELTER, MORTAR, STICKS).setBurning(Ash, U4)                                                                       .uumMcfg( 0, C              , 1*U)                                                                                                                          .setSmelting(C, U2).heat(4300, 4400).setPulver(C, U),
	Ectoplasm               = dust          ( 8373, "Ectoplasm"             , SET_FOOD              , 220, 255, 255, 200).put(MAGICAL, LIQUID, GLOWING)                                                                                                                                                                                                                                                                             .aspects(TC.SPIRITUS, 4).heat(400, 3000),
	Firestone               = create        ( 8342, "Firestone"             , SET_QUARTZ            , 200,  20,   0, 255).put(G_QUARTZ_ORES, CRYSTAL, BRITTLE, CRYSTALLISABLE, MAGICAL, QUARTZ, UNBURNABLE, BURNING).qual(3,  6.0, 1280, 3)                                                                                                                                                                                         .aspects(TC.IGNIS, 8),
	
	
	Force                   = create        ( 8343, "Force"                 , SET_REDSTONE          , 255, 255,   0, 255).put(G_GEM_ORES, G_INGOT_MACHINE_ORES).put(CRYSTAL, MAGICAL, UNBURNABLE, GLOWING).qual(3, 10.0, 128, 3)                                                                                                                                                                                                    .aspects(TC.POTENTIA, 4),
	Forcicium               = create        ( 8344, "Forcicium"             , SET_REDSTONE          ,  50,  50,  70, 255).put(G_QUARTZ_ORES, CRYSTAL, BRITTLE, CRYSTALLISABLE, MAGICAL)                                                                                                                                                                                                                                             .aspects(TC.POTENTIA, 2),
	Forcillium              = create        ( 8345, "Forcillium"            , SET_REDSTONE          ,  50,  50,  70, 255).put(G_QUARTZ_ORES, CRYSTAL, BRITTLE, CRYSTALLISABLE, MAGICAL)                                                                                                                                                                                                                                             .aspects(TC.POTENTIA, 2),
	Monazite                = elec          ( 8338, "Monazite"              , SET_REDSTONE          ,  50,  70,  50, 255).put(G_GEM_ORES, CRYSTAL, BRITTLE, CRYSTALLISABLE, BLACKLISTED_SMELTER)                                                                        .setMcfg( 0, RareEarth      , 1*U, PO4              , 1*U)                                                                                                  , // Wikipedia: (Ce, La, Nd, Th, Sm, Gd)PO4 Monazite also smelt-extract to Helium, it is brown like the rare earth Item Monazite sand deposits are inevitably of the monazite-(Ce) composition. Typically, the lanthanides in such monazites contain about 45�48% cerium, about 24% lanthanum, about 17% neodymium, about 5% praseodymium, and minor quantities of samarium, gadolinium, and yttrium. Europium concentrations tend to be low, about 0.05% Thorium content of monazite is variable and sometimes can be up to 20�30%
	
	
	Redstone                = redstone      ( 8333, "Redstone"              , SET_REDSTONE          , 200,   0,   0, 255).put(PULVERIZING_CINNABAR).lens(DYE_INDEX_Red)                                                                                                 .uumMcfg( 0, Pyrite         , 5*U, Hg               , 3*U, SiO2             , 1*U, Ruby             , 1*U)                                                  .aspects(TC.MACHINA, 1, TC.POTENTIA, 2).heat(500, 1500).qual(0),
	Teslatite               = redstone      ( 8339, "Teslatite"             , SET_REDSTONE          ,  60, 180, 200, 255).put(MOLTEN).lens(DYE_INDEX_Cyan)                                                                                                              .uumMcfg( 0, Sodalite       , 5*U, Cu               , 3*U, SiO2             , 1*U, Ar               , 1*U)                                                  .aspects(TC.ELECTRUM, 2).heat(1500, 3000).qual(0),
	Nikolite                = redstone      ( 8340, "Nikolite"              , SET_REDSTONE          ,  60, 180, 200, 255).put(MOLTEN).lens(DYE_INDEX_Cyan)                                                                                                              .uumMcfg( 0, Sodalite       , 5*U, Cu               , 3*U, SiO2             , 1*U, Ar               , 1*U)                                                  .aspects(TC.ELECTRUM, 2).heat(1500, 3000).qual(0),
	Electrotine             = redstone      ( 8359, "Electrotine"           , SET_REDSTONE          ,  60, 160, 230, 255).put(MOLTEN).lens(DYE_INDEX_Cyan)                                                                                                              .uumMcfg( 0, Sodalite       , 5*U, Cu               , 3*U, SiO2             , 1*U, Ar               , 1*U)                                                  .aspects(TC.ELECTRUM, 2).heat(1500, 3000).qual(0),
	
	
	Glowstone               = glowstone     ( 8341, "Glowstone"             , SET_REDSTONE          , 255, 255,   0, 255)                                                                                                                                               .uumMcfg( 0, Phosphorite    , 5*U, Au               , 3*U, SiO2             , 1*U, He               , 1*U)                                                  .aspects(TC.LUX, 2, TC.SENSUS, 1).heat(500, 600),
	GlowstoneCeres          = glowstone     ( 8368, "GlowstoneCeres"        , SET_REDSTONE          ,  70,  90,  70, 255)                                                                                                                                               .uumMcfg( 0, Phosphorite    , 5*U, Au               , 3*U, SiO2             , 1*U, He               , 1*U)                                                  .aspects(TC.LUX, 2, TC.SENSUS, 1, TC.HERBA      , 1).heat(500, 600).setLocal("Ceres Glowstone"),
	GlowstoneIo             = glowstone     ( 8369, "GlowstoneIo"           , SET_REDSTONE          , 180,  20,   0, 255)                                                                                                                                               .uumMcfg( 0, Phosphorite    , 5*U, Au               , 3*U, SiO2             , 1*U, He               , 1*U)                                                  .aspects(TC.LUX, 2, TC.SENSUS, 1, TC.IGNIS      , 1).heat(500, 600).setLocal("Io Glowstone"),
	GlowstoneEnceladus      = glowstone     ( 8370, "GlowstoneEnceladus"    , SET_REDSTONE          ,   0, 250, 250, 255)                                                                                                                                               .uumMcfg( 0, Phosphorite    , 5*U, Au               , 3*U, SiO2             , 1*U, He               , 1*U)                                                  .aspects(TC.LUX, 2, TC.SENSUS, 1, TC.AQUA       , 1).heat(500, 600).setLocal("Enceladus Glowstone"),
	GlowstoneProteus        = glowstone     ( 8371, "GlowstoneProteus"      , SET_REDSTONE          ,  62,  62,  62, 255)                                                                                                                                               .uumMcfg( 0, Phosphorite    , 5*U, Au               , 3*U, SiO2             , 1*U, He               , 1*U)                                                  .aspects(TC.LUX, 2, TC.SENSUS, 1, TC.TENEBRAE   , 1).heat(500, 600).setLocal("Proteus Glowstone"),
	GlowstonePluto          = glowstone     ( 8372, "GlowstonePluto"        , SET_REDSTONE          , 123, 150, 220, 255)                                                                                                                                               .uumMcfg( 0, Phosphorite    , 5*U, Au               , 3*U, SiO2             , 1*U, He               , 1*U)                                                  .aspects(TC.LUX, 2, TC.SENSUS, 1, TC.GELUM      , 1).heat(500, 600).setLocal("Pluto Glowstone"),
	
	
	NetherQuartz            = quartz        ( 8346, "Nether Quartz"                                 , 230, 210, 210, 255).put(CRYSTALLISABLE, "Quartz")                                                                                                                 .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.POTENTIA, 1, TC.VITREUS                     , 1).qual(1, 2.5, 32, 1),
	SunnyQuartz             = quartz        ( 8393, "Sunny Quartz"                                  , 255, 255, 200, 255).put(CRYSTALLISABLE)                                                                                                                           .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.POTENTIA, 1, TC.VITREUS, 1, TC.LUX          , 1).qual(1, 2.5, 32, 1),
	LavenderQuartz          = quartz        ( 8394, "Lavender Quartz"                               , 255, 200, 255, 255).put(CRYSTALLISABLE)                                                                                                                           .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.POTENTIA, 1, TC.VITREUS, 1, TC.SENSUS       , 1).qual(1, 2.5, 32, 1),
	RedQuartz               = quartz        ( 8395, "Red Quartz"                                    , 255, 210, 210, 255).put(CRYSTALLISABLE)                                                                                                                           .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.POTENTIA, 1, TC.VITREUS, 1, TC.MACHINA      , 1).qual(1, 2.5, 32, 1),
	BlazeQuartz             = quartz        ( 8396, "Blaze Quartz"                                  , 255, 230, 200, 255).put(CRYSTALLISABLE)                                                                                                                           .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.POTENTIA, 1, TC.VITREUS, 1, TC.IGNIS        , 1).qual(1, 2.5, 32, 1),
	SmokeyQuartz            = quartz        ( 8397, "Smokey Quartz"                                 ,  20,  20,  20, 255).put(CRYSTALLISABLE, "QuartzSmoky", "SmokyQuartz")                                                                                             .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.POTENTIA, 1, TC.VITREUS, 1, TC.TENEBRAE     , 1).qual(1, 2.5, 32, 1),
	ManaQuartz              = quartz        ( 8398, "Mana Quartz"                                   , 210, 210, 255, 255).put(CRYSTALLISABLE)                                                                                                                           .setMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.POTENTIA, 1, TC.VITREUS, 1, TC.PRAECANTIO   , 1).qual(1, 2.5, 64, 2),
	ElvenQuartz             = quartz        ( 8399, "Elven Quartz"                                  , 210, 255, 210, 255).put(CRYSTALLISABLE)                                                                                                                           .setMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.POTENTIA, 1, TC.VITREUS, 1, TC.ALIENIS      , 1).qual(1, 2.5, 64, 2),
	BlackQuartz             = quartz        ( 8374, "QuartzBlack"                                   ,  20,  20,  20, 255).put(CRYSTALLISABLE, DECOMPOSABLE, CENTRIFUGE)                                                                                                 .uumMcfg( 1, SiO2           , 1*U, C                , 1*U)                                                                                                  .aspects(TC.POTENTIA, 1, TC.VITREUS, 1, TC.TENEBRAE     , 1).qual(1, 2.5, 32, 1).setLocal("Black Quartz"),
	MilkyQuartz             = quartz        ( 8445, "Milky Quartz"                                  , 210, 210, 210, 255).put(CRYSTALLISABLE)                                                                                                                           .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.POTENTIA, 1, TC.VITREUS                     , 1).qual(1, 2.5, 32, 1),
	CertusQuartz            = quartz        ( 8347, "Certus Quartz"                                 , 210, 210, 230, 255).put(CRYSTALLISABLE)                                                                                                                           .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.POTENTIA, 1, TC.VITREUS                     , 1).qual(1, 5.0, 32, 1),
	ChargedCertusQuartz     = quartz        ( 8348, "Charged Certus Quartz"                         , 210, 210, 230, 255).put(GLOWING)                                                                                                                                  .uumMcfg( 0, SiO2           , 1*U)                                                                                                                          .aspects(TC.POTENTIA, 2, TC.VITREUS                     , 1).steal(CertusQuartz).setPulver(CertusQuartz, U),
	Fluix                   = quartz        ( 8389, "Fluix"                                         , 120,  70, 140, 255).put(CRYSTALLISABLE)                                                                                                                           .uumMcfg( 2, SiO2           , 2*U, Redstone         , 1*U)                                                                                                  .aspects(TC.POTENTIA, 2, TC.VITREUS, 1, TC.LUX          , 1),
	
	
	Petrotheum              = mixdust       ( 8245, "Petrotheum"            , SET_DULL              ,  86,  76,  82, 255).put(CONTAINERS, MELTING, MORTAR)                                                                                                              .setMcfg( 2, Clay           , 1*U, Obsidian         , 1*U, Redstone         , 1*U, Basalz           , 1*U)                                                  .aspects(TC.PRAECANTIO, 2, TC.POTENTIA, 1).heat(400, 2000),
	Aerotheum               = dust          ( 8246, "Aerotheum"             , SET_SHINY             , 200, 176,  83, 255).put(CONTAINERS, MELTING, MORTAR)                                                                                                              .setMcfg( 2, Sand           , 1*U, KNO3             , 1*U, Redstone         , 1*U, Blitz            , 1*U)                                                  .aspects(TC.PRAECANTIO, 2, TC.AER, 1).heat(299, 300),
	Pyrotheum               = mixdust       ( 8212, "Pyrotheum"             , SET_FIERY             , 255, 200, 120, 255).put(CONTAINERS, MELTING, MORTAR)                                                                                                              .setMcfg( 2, Coal           , 1*U, S                , 1*U, Redstone         , 1*U, Blaze            , 1*U)                                                  .aspects(TC.PRAECANTIO, 2, TC.IGNIS, 1).heat(3800, 6400),
	Cryotheum               = dust          ( 8213, "Cryotheum"             , SET_SHINY             , 200, 220, 255, 255).put(CONTAINERS, MELTING, MORTAR)                                                                                                              .setMcfg( 8, Snow           , 1*U, KNO3             , 4*U, Redstone         , 4*U, Blizz            , 4*U)                                                  .aspects(TC.PRAECANTIO, 2, TC.GELUM, 1).heat(40, 1000),
	
	
	Redstonia               = gem_aa        ( 8375, "Redstonia"             , SET_EMERALD           , 255,   0,   0, 127, Redstone)                                                                                                                                                                                                                                                                                                 .aspects(TC.VITREUS, 2, TC.MACHINA  , 2).qual(3,  6.0,  300,  2),
	Palis                   = gem_aa        ( 8376, "Palis"                 , SET_EMERALD           ,   0,   0, 255, 127, Lapis   )                                                                                                                                                                                                                                                                                                 .aspects(TC.VITREUS, 2, TC.SENSUS   , 2).qual(3,  6.0,  300,  2),
	Diamantine              = gem_aa        ( 8377, "Diamantine"            , SET_DIAMOND           , 128, 128, 255, 127, Diamond ).put(VALUABLE)                                                                                                                                                                                                                                                                                   .aspects(TC.VITREUS, 2, TC.LUCRUM   , 2).qual(3, 10.0, 1600,  4),
	VoidCrystal             = gem_aa        ( 8378, "VoidCrystal"           , SET_RUBY              ,  10,  10,  10, 127, Coal    ).put(TICKS_PER_SMELT*16)                                                                                                                                                                                                                                                                         .aspects(TC.VITREUS, 2, TC.POTENTIA , 2).qual(3,  6.0,  280,  2).setDensity(0.929).setLocal("Void"),
	Emeradic                = gem_aa        ( 8379, "Emeradic"              , SET_EMERALD           ,   0, 255,   0, 127, Emerald ).put(VALUABLE)                                                                                                                                                                                                                                                                                   .aspects(TC.VITREUS, 2, TC.LUCRUM   , 2).qual(3,  8.0, 2200,  3),
	Enori                   = gem_aa        ( 8380, "Enori"                 , SET_NETHERSTAR        , 255, 255, 255, 127, Fe      )                                                                                                                                                                                                                                                                                                 .aspects(TC.VITREUS, 2, TC.METALLUM , 2).qual(3,  6.0,  280,  3),
	
	
	DarkMatter              = create        ( 8381, "Dark Matter"           , SET_RUBY              ,  40,  20,  40, 255).put(G_GEM, MAGICAL, UNBURNABLE, VALUABLE)                                                                                                                                                                                                                                                                 .aspects(TC.POTENTIA, 10, TC.TENEBRAE   , 10).qual(3, 20.0, 12800, 5),
	RedMatter               = create        ( 8382, "Red Matter"            , SET_RUBY              , 255,   0,   0, 255).put(G_GEM, MAGICAL, UNBURNABLE, VALUABLE)                                                                                                                                                                                                                                                                 .aspects(TC.POTENTIA, 10, TC.LUX        , 10).qual(3, 30.0, 25600, 6),
	
	
	EnergiumRed             = crystalcent   ( 8298, "EnergiumRed"           , SET_DIAMOND           , 255,   0,   0, 255).put(CRYSTALLISABLE)                                                                                                                           .uumMcfg( 0, Sapphire       , 1*U, Redstone         , 1*U)                                                                                                  .aspects(TC.LUX, 2, TC.POTENTIA, 2).setLocal("Red Energium"),
	EnergiumCyan            = crystalcent   ( 8299, "EnergiumCyan"          , SET_DIAMOND           ,   0, 255, 255, 255).put(CRYSTALLISABLE)                                                                                                                           .uumMcfg( 0, Sapphire       , 1*U, Teslatite        , 1*U)                                                                                                  .aspects(TC.LUX, 2, TC.POTENTIA, 4).setLocal("Cyan Energium"),
	
	
	InfusedDull             = crystal_tc    ( 8350, "Infused Dull"                                  , 100, 100, 100, DYE_INDEX_Gray        )                                                                                                                                                                                                                                                                                        .aspects(TC.PRAECANTIO, 1, TC.VACUOS    , 2).qual(3, 32.0,   64,  3),
	InfusedVis              = crystal_tc    ( 8351, "Infused Vis"                                   , 255,   0, 255, DYE_INDEX_Magenta     ).put(GLOWING)                                                                                                                                                                                                                                                                           .aspects(TC.PRAECANTIO, 1, TC.AURAM     , 2).qual(3,  8.0,   64,  3),
	InfusedAir              = crystal_tc    ( 8352, "Infused Air"                                   , 255, 255,   0, DYE_INDEX_Yellow      ).put(GLOWING)                                                                                                                                                                                                                                                                           .aspects(TC.PRAECANTIO, 1, TC.AER       , 2).qual(3,  8.0,   64,  3),
	InfusedFire             = crystal_tc    ( 8353, "Infused Fire"                                  , 255,   0,   0, DYE_INDEX_Red         ).put(GLOWING)                                                                                                                                                                                                                                                                           .aspects(TC.PRAECANTIO, 1, TC.IGNIS     , 2).qual(3,  8.0,   64,  3),
	InfusedEarth            = crystal_tc    ( 8354, "Infused Earth"                                 ,   0, 255,   0, DYE_INDEX_Green       ).put(GLOWING)                                                                                                                                                                                                                                                                           .aspects(TC.PRAECANTIO, 1, TC.TERRA     , 2).qual(3,  8.0,  256,  3),
	InfusedWater            = crystal_tc    ( 8355, "Infused Water"                                 ,   0,   0, 255, DYE_INDEX_Blue        ).put(GLOWING)                                                                                                                                                                                                                                                                           .aspects(TC.PRAECANTIO, 1, TC.AQUA      , 2).qual(3,  8.0,   64,  3),
	InfusedEntropy          = crystal_tc    ( 8356, "Infused Entropy"                               ,  62,  62,  62, DYE_INDEX_Black       ).put(GLOWING)                                                                                                                                                                                                                                                                           .aspects(TC.PRAECANTIO, 1, TC.PERDITIO  , 2).qual(3, 32.0,   64,  4),
	InfusedOrder            = crystal_tc    ( 8357, "Infused Order"                                 , 252, 252, 252, DYE_INDEX_White       ).put(GLOWING)                                                                                                                                                                                                                                                                           .aspects(TC.PRAECANTIO, 1, TC.ORDO      , 2).qual(3,  8.0,   64,  3),
	InfusedBalance          = crystal_tc    ( 8358, "Infused Balance"                               , 252, 252, 252, DYE_INDEX_LightGray   ).put(GLOWING)                                                                                                                                                                                                                                                                           .aspects(TC.PRAECANTIO, 1, TC.ORDO, 1, TC.PERDITIO, 1, TC.IGNIS, 1, TC.AER, 1, TC.AQUA, 1, TC.TERRA, 1).qual(3, 32.0,  256,  4),
	
	
	HexoriumBlack           = hexorium      ( 9224,  32,  32,  32, DYE_INDEX_Black    ),
	HexoriumRed             = hexorium      ( 9225, 128,   0,   0, DYE_INDEX_Red      ),
	HexoriumGreen           = hexorium      ( 9226,   0, 128,   0, DYE_INDEX_Green    ),
//  HexoriumBrown           = hexorium      ( 9227,  48,  32,   0, DYE_INDEX_Brown    ),
	HexoriumBlue            = hexorium      ( 9228,   0,   0, 128, DYE_INDEX_Blue     ),
//  HexoriumPurple          = hexorium      ( 9229,  64,   0,  64, DYE_INDEX_Purple   ),
//  HexoriumCyan            = hexorium      ( 9230,   0, 128, 128, DYE_INDEX_Cyan     ),
//  HexoriumLightGray       = hexorium      ( 9231,  96,  96,  96, DYE_INDEX_LightGray),
//  HexoriumGray            = hexorium      ( 9232,  64,  64,  64, DYE_INDEX_Gray     ),
//  HexoriumPink            = hexorium      ( 9233, 128,  96,  96, DYE_INDEX_Pink     ),
//  HexoriumLime            = hexorium      ( 9234,  64, 128,  64, DYE_INDEX_Lime     ),
//  HexoriumYellow          = hexorium      ( 9235, 128, 128,   0, DYE_INDEX_Yellow   ),
//  HexoriumLightBlue       = hexorium      ( 9236,  64,  64, 128, DYE_INDEX_LightBlue),
//  HexoriumMagenta         = hexorium      ( 9237, 128,   0, 128, DYE_INDEX_Magenta  ),
//  HexoriumOrange          = hexorium      ( 9238, 128,  64,   0, DYE_INDEX_Orange   ),
	HexoriumWhite           = hexorium      ( 9239, 224, 224, 224, DYE_INDEX_White    ),
//  HexoriumDarkGray        = hexorium      ( 9240,  48,  48,  48, DYE_INDEX_Gray     ),
//  HexoriumTurquoise       = hexorium      ( 9241,   0, 128,  64, DYE_INDEX_Cyan     ),
//  HexoriumRainbow         = hexorium      ( 9242, 224, 224, 224, DYE_INDEX_White    ),
	
	
	Bedrock                 = create        ( 8599, "Bedrock"               , SET_STONE             ,  64,  64,  64, 255).put(G_STONE, DIRTY_DUSTS, STONE, BRITTLE, MELTING)                                                                                                                                                                                                                                                        .aspects(TC.TERRA       , 5).qual(1, 8.0, 2048, 5).heat(4000).setRGBaLiquid(128,  96,  64, 255).visDefault(),
	
	
	Stone                   = stone         ( 8500, "Stone"                                         , 205, 205, 205, 255).put(MELTING, MOLTEN, UNRECYCLABLE)                                                                                                                                                                                                                                                                        .aspects(TC.TERRA       , 1).qual(1, 2.0, 16, 1).heat(1100).setRGBaLiquid(192,  96,  64, 255),
	Concrete                = stone         ( 8501, "Concrete"                                      , 100, 100, 100, 255).put(MELTING)                                                                                                                                  .setMcfg( 0, Stone          , 1*U)                                                                                                                          .aspects(TC.TERRA       , 1).qual(1, 2.5, 32, 0).heat(500).setSmelting(Stone, U),
	Netherrack              = stone         ( 8502, "Netherrack"                                    , 200,   0,   0, 255).put(UNBURNABLE, FLAMMABLE, BLACKLISTED_SMELTER)                                                                                                                                                                                                                                                           .aspects(TC.IGNIS       , 1).qual(1, 2.0,  8, 0).heat(1500, 3000),
	NetherBrick             = stone         ( 8503, "Nether Brick"                                  , 100,   0,   0, 255).put(UNBURNABLE, "BrickNether")                                                                                                                                                                                                                                                                            .aspects(TC.IGNIS       , 1).qual(1, 2.0, 24, 1).heat(1800, 3000).setPulver(Netherrack, U),
	Endstone                = stone         ( 8504, "Endstone"                                      , 217, 222, 158, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TENEBRAE    , 1).qual(1, 3.0, 16, 1).heat(1200),
	SpaceRock               = stone         ( 8512, "Space Stone"           , SET_SPACE             ,  99,  99,  99, 255).put(MELTING, MOLTEN)                                                                                                                                                                                                                                                                                      .aspects(TC.ALIENIS     , 1).qual(1, 5.0, 32, 1).setLocal("Space"),
	MoonRock                = stone         ( 8513, "Moon Stone"                                    , 189, 189, 189, 255).put(MELTING, MOLTEN)                                                                                                                                                                                                                                                                                      .aspects(TC.ALIENIS     , 1).qual(1, 5.0, 32, 1).setLocal("Moon"),
	MoonTurf                = stone         ( 8514, "Moon Turf"                                     , 207, 207, 207, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.ALIENIS     , 1).qual(1, 3.0, 16, 1),
	MarsRock                = stone         ( 8515, "Mars Stone"                                    , 189,  77,  77, 255).put(MELTING, MOLTEN)                                                                                                                                                                                                                                                                                      .aspects(TC.ALIENIS     , 1).qual(1, 5.0, 32, 1).setLocal("Mars"),
	MarsSand                = stone         ( 8516, "Mars Sand"                                     , 207,  66,  66, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.ALIENIS     , 1).qual(1, 3.0, 16, 1),
	Umber                   = stone         ( 8517, "Umber"                                         , 111,  77,  11, 255).put(MD.ERE)                                                                                                                                                                                                                                                                                               .aspects(TC.BESTIA      , 1).qual(1, 3.0, 32, 1).heat( 987).setLocal("Umberstone"),
	Holystone               = stone         ( 8522, "Holystone"                                     , 172, 172, 172, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.LUX         , 1).qual(1, 5.0,128, 1).heat(2000),
	Livingrock              = stone         ( 8521, "Livingrock"                                    , 195, 205, 195, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.VICTUS      , 1).qual(1, 5.0,128, 2).heat(1800),
	Betweenstone            = stone         ( 8519, "Betweenstone"                                  , 100, 160, 110, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.MORTUUS     , 1).qual(1, 3.0, 32, 1).heat(1000),
	Pitstone                = stone         ( 8520, "Pitstone"                                      , 120, 160,  50, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.MORTUUS     , 1).qual(1, 3.0, 32, 1).heat(1200),
	Redrock                 = stonecent     ( 8509, "Redrock"                                       , 255,  80,  50, 255).put("RedRock")                                                                                                                                .setMcfg( 0, CaCO3          , 2*U, Flint            , 1*U, ClayBrown        , 1*U)                                                                          .aspects(TC.TERRA       , 1).qual(1, 2.5, 16, 1),
	Gabbro                  = stonecent     ( 9176, "Gabbro"                                        ,  65,  60,  60, 255)                                                                                                                                               .setMcfg( 0, Olivine        , 1*U, CaCO3            , 3*U, Flint            , 8*U, DarkAsh          , 4*U)                                                  .aspects(TC.TENEBRAE    , 1).qual(1, 3.0, 32, 2).heat(1673),
	Komatiite               = stonecent     ( 9177, "Komatiite"                                     , 190, 190, 105, 255)                                                                                                                                               .setMcfg( 0, Olivine        , 1*U, MgCO3            , 2*U, Flint            , 6*U, DarkAsh          , 3*U)                                                  .aspects(TC.SANO        , 1).qual(1, 3.0, 32, 2).heat(1673),
	Basalt                  = stonecent     ( 8505, "Basalt"                                        ,  60,  50,  50, 255)                                                                                                                                               .setMcfg( 0, Olivine        , 1*U, CaCO3            , 3*U, Flint            , 8*U, DarkAsh          , 4*U)                                                  .aspects(TC.TENEBRAE    , 1).qual(1, 3.0, 32, 2).heat(1673),
	Marble                  = stonecent     ( 8506, "Marble"                                        , 200, 200, 200, 255)                                                                                                                                               .setMcfg( 0, Mg             , 1*U, CaCO3            , 7*U)                                                                                                  .aspects(TC.PERFODIO    , 1).qual(1, 2.5, 16, 1).setSmelting(CaCO3, 2*U3),
	Limestone               = stonecent     ( 9189, "Limestone"                                     , 230, 200, 130, 255)                                                                                                                                               .setMcfg( 0, CaCO3          , 1*U)                                                                                                                          .aspects(TC.TERRA       , 1).qual(1, 2.5, 16, 1).setSmelting(CaCO3, U2),
	Greenschist             = stone         ( 9171, "Greenschist"                                   , 105, 190, 105, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1).qual(1, 2.0, 24, 1).setLocal("Green Schist"),
	Blueschist              = stone         ( 9184, "Blueschist"                                    , 105, 105, 190, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1).qual(1, 2.0, 24, 1).setLocal("Blue Schist"),
	Kimberlite              = stone         ( 9218, "Kimberlite"                                    , 100,  70,  10, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.VITREUS     , 1).qual(1, 2.0, 24, 2),
	Quartzite               = stone         ( 9180, "Quartzite"             , SET_QUARTZ            , 230, 205, 205, 255).put(G_QUARTZ_ORES, CRYSTALLISABLE, QUARTZ, BLACKLISTED_SMELTER)                                                                                                                                                                                                                                           .aspects(TC.POTENTIA    , 1).qual(1, 1.7, 32, 1).setSmelting(SiO2, U),
	GraniteRed              = stoneelec     ( 8507, "GraniteRed"                                    , 160,  60,  70, 255)                                                                                                                                               .setMcfg( 0, Al2O3          , 5*U, PotassiumFeldspar, 1*U)                                                                                                  .aspects(TC.TUTAMEN     , 1).qual(1, 3.0, 64, 3).heat(1500).setLocal("Red Granite"),
	GraniteBlack            = stonecent     ( 8508, "GraniteBlack"                                  ,  20,  20,  20, 255)                                                                                                                                               .setMcfg( 0, SiO2           , 4*U, Biotite          , 1*U)                                                                                                  .aspects(TC.TUTAMEN     , 1).qual(1, 3.0, 64, 3).heat(1500).setLocal("Black Granite"),
	Granite                 = stonecent     ( 8518, "Granite"                                       , 160, 120, 130, 255)                                                                                                                                               .setMcfg( 0, SiO2           , 4*U, Biotite          , 1*U)                                                                                                  .aspects(TC.TERRA       , 1).qual(1, 3.0, 64, 1).heat(1500),
	Andesite                = stone         ( 9188, "Andesite"                                      , 191, 191, 191, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1).qual(1, 2.5, 16, 1),
	Diorite                 = stone         ( 8511, "Diorite"                                       , 240, 240, 240, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1).qual(1, 2.5, 16, 1),
	Blackstone              = stone         ( 9223, "Blackstone"                                    ,  30,  20,  20, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TENEBRAE    , 1).qual(1, 5.0, 64, 1),
	Gravel                  = dust          ( 8510, "Gravel"                , SET_ROUGH             , 205, 205, 205, 255).put(STONE, BRITTLE, MORTAR)                                                                                                                                                                                                                                                                               .aspects(TC.PERDITIO    , 1).qual(1, 1.0,  8, 0),
	Gneiss                  = stone         ( 9170, "Gneiss"                                        , 255, 201, 134, 255).put(MD.ERE)                                                                                                                                                                                                                                                                                               .aspects(TC.TERRA       , 1).qual(1, 2.0, 24, 1),
	Greywacke               = stone         ( 9173, "Greywacke"                                     , 176, 176, 176, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1).qual(1, 2.0, 16, 1),
	Siltstone               = stone         ( 9178, "Siltstone"                                     , 250, 205, 205, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1).qual(1, 2.0, 16, 0),
	Rhyolite                = stone         ( 9179, "Rhyolite"                                      , 121, 121, 121, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1).qual(1, 2.0, 16, 1),
	Migmatite               = stone         ( 9181, "Migmatite"                                     ,  70,  40,  40, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1).qual(1, 2.0, 16, 1),
	Chert                   = stone         ( 9186, "Chert"                                         , 105,  10,  10, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1).qual(1, 2.0, 16, 0),
	Dacite                  = stone         ( 9187, "Dacite"                                        , 131, 131, 131, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1).qual(1, 2.0, 16, 1),
	Shale                   = stone         ( 9190, "Shale"                                         , 142, 142, 168, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1).qual(1, 2.0, 16, 0),
	Slate                   = stone         ( 9222, "Slate"                                         , 148, 151, 156, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1).qual(1, 2.0, 16, 0),
	Eclogite                = stone         ( 9191, "Eclogite"                                      ,  90,  40,  40, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1).qual(1, 2.0, 16, 1),
	
	PrismarineLight         = stone         ( 9219, "Prismarine"            , SET_PRISMARINE        , 110, 178, 165, 255).put(G_GEM_ORES, CRYSTAL, CRYSTALLISABLE)                                                                                                                                                                                                                                                                  .aspects(TC.TEMPESTAS   , 1).qual(1, 4.0, 48, 1).setLocal("Light Prismarine"),
	PrismarineDark          = stone         ( 9220, "PrismarineDark"        , SET_PRISMARINE        ,  88, 125, 108, 255).put(G_GEM_ORES, CRYSTAL, CRYSTALLISABLE)                                                                                                                                                                                                                                                                  .aspects(TC.TEMPESTAS   , 1).qual(1, 4.0, 48, 1).setLocal("Dark Prismarine"),
	
	Greenstone              = stone         ( 9172, "Greenstone"                                    ,  52, 252,  52, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1),
	Bluestone               = stone         ( 9185, "Bluestone"                                     ,  52,  52, 252, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1),
	Epidote                 = stone         ( 9182, "Epidote"                                       , 128, 128, 128, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.TERRA       , 1),
	
	Oilshale                = oredust       ( 9853, "Oilshale"              , SET_STONE             ,  50,  50,  60, 255).put(FLAMMABLE, TICKS_PER_SMELT* 2).setBurning(Stone, U2)                                                                                                                                                                                                                                                  .heat( 500, 1000).aspects(TC.MORTUUS, 2, TC.LUX, 1),
	
	
	
	
	WroughtIron             = metalmachnd   ( 8643, "Wrought Iron"          , SET_METALLIC          , 200, 180, 180     ).put(RAILS, MORTAR, MAGNETIC_PASSIVE, MOLTEN, "WrougtIron")                                                                                    .uumMcfg( 0, Fe             , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.INSTRUMENTUM, 1).setPulver(Fe, U).steal(Fe).qual(3,  6.0,  384,  2).setRGBaLiquid(255, 80, 40, 255).alloyCentrifuge(Fe.mMeltingPoint + 200, Fe.mBoilingPoint),
	
	
	Alduorite               = setalore      ( 8760, "Alduorite"                                     , 159, 180, 180     )                                                                                                                                                                                                                                                                                                           .aspects(TC.METALLUM, 2, TC.PERMUTATIO                          , 1).heat(1567),
	Infuscolium             = metalore      ( 8761, "Infuscolium"                                   , 146,  33,  86     )                                                                                                                                                                                                                                                                                                           .aspects(TC.METALLUM, 2, TC.PRAECANTIO                          , 1).heat(1828),
	Rubracium               = metalore      ( 8762, "Rubracium"                                     , 151,  45,  45     )                                                                                                                                                                                                                                                                                                           .aspects(TC.METALLUM, 2, TC.LUCRUM                              , 1).heat(1847),
	Meutoite                = metalore      ( 8763, "Meutoite"                                      ,  95,  82, 105     )                                                                                                                                                                                                                                                                                                           .aspects(TC.METALLUM, 2, TC.ALIENIS                             , 1).heat(1837),
	Lemurite                = metalore      ( 8764, "Lemurite"                                      , 219, 219, 219     )                                                                                                                                                                                                                                                                                                           .aspects(TC.METALLUM, 2, TC.MOTUS                               , 1).heat(1179),
	Ceruclase               = metalore      ( 8765, "Ceruclase"                                     , 140, 189, 208     ).qual(3,  6.0, 1280, 2)                                                                                                                                                                                                                                                                                    .aspects(TC.METALLUM, 2, TC.TEMPESTAS                           , 1).heat(1867),
	// 8766 is free now
	Oureclase               = metalore      ( 8767, "Oureclase"                                     , 183,  98,  21     ).qual(3,  6.0, 1920, 3)                                                                                                                                                                                                                                                                                    .aspects(TC.METALLUM, 2, TC.AER                                 , 1).heat(2789),
	Kalendrite              = metalore      ( 8768, "Kalendrite"                                    , 170,  91, 189     ).qual(3,  5.0, 2560, 3)                                                                                                                                                                                                                                                                                    .aspects(TC.METALLUM, 2, TC.ORDO                                , 1).heat(2679),
	Orichalcum              = metalore      ( 8769, "Orichalcum"                                    ,  84, 122,  56     ).qual(3,  4.5, 3456, 3)                                                                                                                                                                                                                                                                                    .aspects(TC.METALLUM, 2, TC.TERRA                               , 1).heat(2897),
	Carmot                  = metalore      ( 8770, "Carmot"                                        , 217, 205, 140     ).qual(3, 16.0,  128, 1)                                                                                                                                                                                                                                                                                    .aspects(TC.METALLUM, 2, TC.NEBRISUM                            , 1).heat(1178),
	Sanguinite              = metalore      ( 8771, "Sanguinite"                                    , 185,   0,   0     ).qual(3,  3.0, 4480, 4)                                                                                                                                                                                                                                                                                    .aspects(TC.METALLUM, 2, TC.SANO                                , 1).heat(3104),
	Vyroxeres               = metalore      ( 8772, "Vyroxeres"                                     ,  85, 224,   1     ).qual(3,  9.0,  768, 3)                                                                                                                                                                                                                                                                                    .aspects(TC.METALLUM, 2, TC.VENENUM                             , 1).heat(2348),
	Eximite                 = metalore      ( 8773, "Eximite"                                       , 124,  90, 150     ).qual(3,  5.0, 2560, 3)                                                                                                                                                                                                                                                                                    .aspects(TC.METALLUM, 2, TC.ITER                                , 1).heat(2758),
	Prometheum              = metalore      ( 8774, "Prometheum"                                    ,  90, 129,  86     ).qual(3,  8.0,  512, 1)                                                                                                                                                                                                                                                                                    .aspects(TC.METALLUM, 2, TC.POTENTIA                            , 1).heat(1067),
	Ignatius                = metalore      ( 8775, "Ignatius"                                      , 255, 169,  83     ).qual(3, 12.0,  512, 2)                                                                                                                                                                                                                                                                                    .aspects(TC.METALLUM, 2, TC.IGNIS                               , 1).heat(1978),
	Vulcanite               = metalore      ( 8776, "Vulcanite"                                     , 255, 132,  72     ).qual(3,  5.0, 3840, 3)                                                                                                                                                                                                                                                                                    .aspects(TC.METALLUM, 2, TC.VITREUS                             , 1).heat(2978),
	DeepIron                = metalore      ( 8641, "Deep Iron"                                     ,  73,  91, 105     ).qual(3,  6.0,  384, 2).put(MAGNETIC_PASSIVE)                                                                                                                                                                                                                                                              .aspects(TC.METALLUM, 2, TC.MAGNETO                             , 1).heat(Fe),
	ShadowIron              = metalore      ( 8670, "Shadow Iron"                                   ,  95,  76,  63     ).qual(3,  6.0,  384, 2).put(MAGNETIC_PASSIVE)                                                                                                                                                                                                                                                              .aspects(TC.METALLUM, 2, TC.TENEBRAE                            , 1).heat(WroughtIron),
	Adamantine              = metalore      ( 8784, "Adamantine"                                    , 255,   0,  64     ).qual(3, 10.0, 4500, 5).put(MAGNETIC_PASSIVE, DECOMPOSABLE, ELECTROLYSER, WITHER_PROOF, ENDER_DRAGON_PROOF)                                    .uumMcfg( 0, Ad             , 3*U, O                , 4*U)                                                                                                  .aspects(TC.METALLUM, 5, TC.PRAECANTIO                          , 5).heat(Ad),
	AstralSilver            = slloymachore  ( 8676, "Astral Silver"                                 , 230, 230, 255     ).qual(3, 10.0,   64, 2).put(MAGICAL, MOLTEN, WASHING_MERCURY, VALUABLE, CENTRIFUGE, ENDER_DRAGON_PROOF)                                        .setMcfg( 2, Ag             , 2*U, Ma               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.LUCRUM          , 1, TC.PRAECANTIO  , 1).heat(Ag),
	Midasium                = slloymachore  ( 8677, "Midasium"                                      , 255, 200,  40     ).qual(3, 12.0,   64, 2).put(MAGICAL, MOLTEN, WASHING_MERCURY, VALUABLE, WITHER_PROOF)                                                          .setMcfg( 2, Au             , 2*U, Ma               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.LUCRUM          , 2, TC.PRAECANTIO  , 1).heat(Au),
	Mithril                 = slloymachore  ( 8678, "Mithril"                                       , 100, 140, 250     ).qual(3, 14.0,   64, 3).put(MAGICAL, MOLTEN, WASHING_MERCURY, VALUABLE, CENTRIFUGE)                                                            .setMcfg( 2, Pt             , 2*U, Ma               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.LUCRUM          , 3, TC.PRAECANTIO  , 1).heat(Pt),
	ShadowSteel             = alloy         ( 8671, "Shadow Steel"                                  , 136, 115,  98     ).qual(3,  6.0,  768, 2).put(MAGNETIC_PASSIVE)                                                                                                  .setMcfg( 0, ShadowIron     , 1*U, Lemurite         , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.TENEBRAE        , 1, TC.TELUM       , 1).alloyCentrifuge().visDefault(ShadowIron, Lemurite),
	Inolashite              = alloy         ( 8777, "Inolashite"                                    , 148, 216, 187     ).qual(3,  8.0, 2304, 3)                                                                                                                        .setMcfg( 0, Alduorite      , 1*U, Ceruclase        , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.AER             , 1, TC.AQUA        , 1).alloyCentrifuge().visDefault(Alduorite, Ceruclase),
	Haderoth                = alloy         ( 8778, "Haderoth"                                      , 119,  52,  30     ).qual(3, 10.0, 3200, 3)                                                                                                                        .setMcfg( 0, Mithril        , 1*U, Rubracium        , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.LUCRUM          , 1, TC.PRAECANTIO  , 1).alloyCentrifuge().visDefault(Rubracium),
	Celenegil               = alloy         ( 8779, "Celenegil"                                     , 148, 204,  72     ).qual(3, 10.0, 4096, 3)                                                                                                                        .setMcfg( 0, Pt             , 1*U, Orichalcum       , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.NEBRISUM        , 1, TC.VITREUS     , 1).alloyCentrifuge().visDefault(Orichalcum),
	Desichalkos             = alloy         ( 8781, "Desichalkos"                                   , 114,  47, 168     ).qual(3, 11.0, 4608, 4)                                                                                                                        .setMcfg( 0, Eximite        , 1*U, Meutoite         , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.ITER            , 1, TC.ALIENIS     , 1).alloyCentrifuge().visDefault(Eximite, Meutoite),
	Tartarite               = alloy         ( 8782, "Tartarite"                                     , 255, 118,  60     ).qual(3, 20.0, 7680, 5).put(MAGNETIC_PASSIVE, WITHER_PROOF, ENDER_DRAGON_PROOF)                                                                .uumMcfg( 0, Adamantine     , 1*U, Atl              , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.INSTRUMENTUM    , 1, TC.COGNITO     , 1).alloyCentrifuge(),
	Amordrine               = alloy         ( 8783, "Amordrine"                                     , 169, 141, 177     ).qual(3,  7.0, 3328, 3)                                                                                                                        .setMcfg( 0, Prometheum     , 1*U, Kalendrite       , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.ORDO            , 1, TC.POTENTIA    , 1).alloyCentrifuge().visDefault(Prometheum, Kalendrite),
	
	
	Electrum                = slloymachore  ( 8600, "Electrum"                                      , 255, 255, 100     ).put(MORTAR, VALUABLE, ENDER_DRAGON_PROOF, RAILS, WASHING_MERCURY, MOLTEN, WITHER_PROOF).qual(3, 12.0,   64, 2)                                .uumMcfg( 0, Ag             , 1*U, Au               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.LUCRUM, 2).alloyCentrifuge(),
	SterlingSilver          = slloymachine  ( 8601, "Sterling Silver"                               , 250, 220, 225     ).put(MORTAR, VALUABLE, ENDER_DRAGON_PROOF).qual(3, 13.0,  128, 2)                                                                              .uumMcfg( 0, Cu             , 1*U, Ag               , 4*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.LUCRUM, 2).alloyCentrifuge(),
	RoseGold                = slloymachine  ( 8602, "Rose Gold"                                     , 255, 230,  30     ).put(MORTAR, VALUABLE, WITHER_PROOF, "Tumbaga").qual(3, 14.0,  128, 2)                                                                         .uumMcfg( 0, Cu             , 1*U, Au               , 4*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.LUCRUM, 2).alloyCentrifuge(),
	Angmallen               = slloymachine  ( 8603, "Angmallen"                                     , 215, 225, 138     ).put(MORTAR, MAGNETIC_PASSIVE, MOLTEN).qual(3, 10.0,  128, 2)                                                                                  .uumMcfg( 0, Au             , 1*U, WroughtIron      , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.LUCRUM, 1).alloyCentrifuge(),
	InductiveAlloy          = slloymachine  ( 8604, "GoldInductive"                                 , 255, 150,  75     ).put(MORTAR, MAGNETIC_PASSIVE, MOLTEN)                                                                                                         .uumMcfg( 0, Au             , 1*U, Redstone         , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.MAGNETO, 1).alloyCentrifuge().setLocal("Inductive Alloy"),
	Cd_In_Ag_Alloy          = alloymachine  ( 8605, "Cd-In-Ag-Alloy"                                , 100, 100, 128     )                                                                                                                                               .uumMcfg( 0, Cd             , 1*U, In               , 1*U, Ag               , 1*U)                                                                          .aspects(TC.METALLUM, 2, TC.TENEBRAE, 1).alloyCentrifuge(),
	GildedIron              = slloymachine  ( 8606, "Gilded Iron"                                   , 255, 230,  80     ).put(COATED, CENTRIFUGE, MAGNETIC_PASSIVE, WITHER_PROOF).qual(3, 12.0,  256, 2)                                                                .setMcfg( 9, Fe             , 9*U, Au               , 1*U)                                                                                                  .aspects(TC.METALLUM, 3, TC.LUCRUM, 1).setSmelting(Fe, U).setForging(Fe, U), // from Flaxbeard, obviously not an Alloy, but coated in Gold. This also gives an example on how to make such things as coated metals.
	
	
	Brass                   = alloymachine  ( 8620, "Brass"                                         , 255, 180,   0     ).put(FURNACE, EXTRUDER_SIMPLE, MORTAR, MOLTEN).qual(2, 7.0, 96, 1)                                                                             .uumMcfg( 0, Cu             , 3*U, Zn               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.INSTRUMENTUM, 1).alloyCentrifuge(1160, Cu.mBoilingPoint),
	CobaltBrass             = alloymachine  ( 8621, "Cobalt Brass"                                  , 180, 180, 160     ).put(MOLTEN).qual(3,  8.0, 256, 2)                                                                                                             .uumMcfg( 0, Brass          , 7*U, Al               , 1*U, Co               , 1*U)                                                                          .aspects(TC.METALLUM, 2, TC.FABRICO, 1).alloyCentrifuge(),
	AluminiumAlloy          = alloymachine  ( 8622, "Aluminium Alloy"                               , 200, 200, 180     ).put(CENTRIFUGE)                                                                                                                               .uumMcfg(45, Al             ,45*U, Si               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.FABRICO, 1).qual(Al),
	
	
	Bronze                  = alloymachine  ( 8610, "Bronze"                                        , 210, 130,  60     ).put(RAILS, FURNACE, EXTRUDER_SIMPLE, MORTAR, MOLTEN).qual(3, 6.0,  192, 2)                                                                    .uumMcfg( 0, Cu             , 3*U, Sn               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.INSTRUMENTUM, 1).alloyCentrifuge(Cu.mMeltingPoint, Cu.mBoilingPoint),
	BlackBronze             = alloymachine  ( 8611, "Black Bronze"          , SET_DULL              , 100,  50, 125     ).put(MORTAR, MOLTEN).qual(3, 12.0,  256, 2)                                                                                                    .uumMcfg( 0, Cu             , 3*U, Electrum         , 2*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.INSTRUMENTUM, 1).alloyCentrifuge(),
	BismuthBronze           = alloymachine  ( 8612, "Bismuth Bronze"        , SET_DULL              , 100, 125, 125     ).put(FURNACE, EXTRUDER_SIMPLE, MORTAR, MOLTEN).qual(3,  8.0,  256, 2)                                                                          .setMcfg( 0, Bi             , 1*U, Brass            , 4*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.INSTRUMENTUM, 1).alloyCentrifuge(),
	Hepatizon               = alloymachine  ( 8613, "Hepatizon"                                     , 117,  94, 117     ).put(MORTAR, MOLTEN).qual(3, 12.0,  128, 2)                                                                                                    .uumMcfg( 0, Au             , 1*U, Bronze           , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.INSTRUMENTUM, 1).alloyCentrifuge(),
	
	
	Steel                   = alloymachore  ( 8630, "Steel"                                         , 130, 130, 130     ).put(MOLTEN, RAILS, MORTAR, MAGNETIC_PASSIVE).qual(3,  6.0,  512, 2)                                                                           .uumMcfg( 0, WroughtIron    , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.ORDO, 1).heat(2046, Fe.mBoilingPoint, Fe.mPlasmaPoint).setRGBaLiquid(255, 20, 10, 255),
	BlackSteel              = alloymachine  ( 8631, "Black Steel"                                   ,  90,  90,  90     ).put(MOLTEN).qual(3,  6.5,  768, 2)                                                                                                            .uumMcfg( 0, Ni             , 1*U, BlackBronze      , 1*U, Steel            , 3*U)                                                                          .aspects(TC.METALLUM, 2, TC.ORDO, 1, TC.INSTRUMENTUM, 1).alloyCentrifuge(),
	RedSteel                = alloymachine  ( 8632, "Red Steel"                                     , 140, 100, 100     ).put(MOLTEN).qual(3,  7.0,  896, 2)                                                                                                            .setMcfg( 0, SterlingSilver , 1*U, BismuthBronze    , 1*U, Steel            , 2*U, BlackSteel       , 4*U)                                                  .aspects(TC.METALLUM, 2, TC.ORDO, 1, TC.INSTRUMENTUM, 1).alloyCentrifuge(),
	BlueSteel               = alloymachine  ( 8633, "Blue Steel"                                    , 100, 100, 140     ).put(MOLTEN).qual(3,  7.5, 1024, 2)                                                                                                            .uumMcfg( 0, RoseGold       , 1*U, Brass            , 1*U, Steel            , 2*U, BlackSteel       , 4*U)                                                  .aspects(TC.METALLUM, 2, TC.ORDO, 1, TC.INSTRUMENTUM, 1).alloyCentrifuge(),
	DamascusSteel           = alloymachine  ( 8634, "Damascus Steel"                                , 110, 110, 110     ).put(MOLTEN, CENTRIFUGE, MAGNETIC_PASSIVE).qual(3,  8.0, 1280, 2)                                                                              .uumMcfg(50, Steel          ,50*U, V                , 1*U, W                , 1*U)                                                                          .aspects(TC.METALLUM, 2, TC.ORDO, 1, TC.INSTRUMENTUM, 1, TC.TELUM, 1),
	VanadiumSteel           = alloymachine  ( 8653, "VanadiumSteel"                                 , 100, 100, 100     ).put(MOLTEN, MAGNETIC_PASSIVE).qual(3, 7.0, 512, 3)                                                                                            .uumMcfg( 0, Steel          , 4*U, V                , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.ORDO, 1, TC.INSTRUMENTUM, 1).setLocal("Vanadiumsteel").alloyCentrifuge(),
	TungstenSteel           = alloymachine  ( 8635, "Tungstensteel"                                 , 100, 100, 160     ).put(MOLTEN, RAILS, MAGNETIC_PASSIVE, "TungstenSteel", "Wolframsteel", "WolframSteel").qual(3, 10.0, 5120, 4)                                  .uumMcfg( 0, Steel          , 1*U, W                , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.ORDO, 1, TC.TUTAMEN, 1).alloyCentrifuge(),
	TungstenCarbide         = alloymachine  ( 8638, "Tungsten Carbide"                              , 123, 123, 123     ).put(RAILS, "Carbide", "WolframCarbide").qual(3, 10.0, 5120, 4)                                                                                .uumMcfg( 0, W              , 1*U, C                , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.ORDO, 1, TC.TUTAMEN, 1).setDensity(15.6).alloyCentrifuge(3070, 6270),
	HSLA                    = alloymachine  ( 8637, "HSLA-Steel"                                    , 210, 210, 255     ).put(MOLTEN, RAILS, CENTRIFUGE, MORTAR, MAGNETIC_PASSIVE, "HSLA")                                                                              .uumMcfg( 0, WroughtIron    , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.ORDO, 1).steal(Steel).heat(1873, Fe.mBoilingPoint, Fe.mPlasmaPoint),
	SpringSteel             = alloymachine  ( 8639, "HSLA-SpringSteel"                              , 220, 100, 100     ).put(CENTRIFUGE, MAGNETIC_PASSIVE)                                                                                                             .uumMcfg(45, HSLA           ,45*U, Redstone         , 2*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.ORDO, 1).steal(HSLA).setLocal("Spring Steel"),
	AnnealedCopper          = metalnd       ( 8640, "Annealed Copper"       , SET_SHINY             , 255, 120,  20     ).put(MOLTEN, FURNACE, EXTRUDER_SIMPLE, MORTAR, WIRES, RAILS)                                                                                   .uumMcfg( 0, Cu             , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.PERMUTATIO, 1           ).setPulver(Cu      , U)                                                                                                                                                        .steal(Cu).alloyCentrifuge(2800, Cu.mBoilingPoint),
	PigIron                 = metalmachore  ( 8642, "Pig Iron"                                      , 200, 180, 180     ).put(MOLTEN, MORTAR, MAGNETIC_PASSIVE)                                                                                                         .uumMcfg( 0, WroughtIron    , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.INSTRUMENTUM, 1         ).setPulver(Fe      , U).setSmelting(WroughtIron    , U)                                                                                                                        .steal(WroughtIron).qual(3,  6.0,  384,  2),
	IronCompressed          = alloymachnd   ( 8644, "IronCompressed"        , SET_METALLIC          , 128, 128, 128     ).put(CENTRIFUGE, MORTAR, MAGNETIC_PASSIVE)                                                                                                     .uumMcfg( 0, Fe             , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.TERRA, 1                ).setPulver(Fe      , U).setSmelting(Fe             , U)                                                                                                                        .steal(Fe).setLocal("Compressed Iron"),
	IronMagnetic            = metalmachnd   ( 8645, "IronMagnetic"          , SET_MAGNETIC          , 200, 200, 200     ).put(LAYERED, MORTAR, MAGNETIC_ACTIVE)                                                                                                         .uumMcfg( 0, Fe             , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.MAGNETO, 1              ).setBending(Fe     , U).setCompressing(Fe      , U).setPulver(Fe       , U).setSmashing(Fe     , U).setSmelting(Fe     , U).setWorking(Fe      , U).setForging(Fe      , U)    .steal(Fe).setLocal("Magnetic Iron"),
	SteelMagnetic           = metalmachnd   ( 8646, "SteelMagnetic"         , SET_MAGNETIC          , 128, 128, 128     ).put(LAYERED, MORTAR, MAGNETIC_ACTIVE)                                                                                                         .uumMcfg( 0, Steel          , 1*U)                                                                                                                          .aspects(TC.METALLUM, 1, TC.ORDO, 1, TC.MAGNETO, 1  ).setBending(Steel  , U).setCompressing(Steel   , U).setPulver(Steel    , U).setSmashing(Steel  , U).setSmelting(Steel  , U).setWorking(Steel   , U).setForging(Steel   , U)    .steal(Steel).setLocal("Magnetic Steel"),
	NeodymiumMagnetic       = metalmachnd   ( 8647, "NeodymiumMagnetic"     , SET_MAGNETIC          , 100, 100, 100     ).put(LAYERED, MORTAR, MAGNETIC_ACTIVE)                                                                                                         .uumMcfg( 0, Nd             , 1*U)                                                                                                                          .aspects(TC.METALLUM, 1, TC.MAGNETO, 3              ).setBending(Fe     , U).setCompressing(Nd      , U).setPulver(Nd       , U).setSmashing(Nd     , U).setSmelting(Nd     , U).setWorking(Nd      , U).setForging(Nd      , U)    .steal(Nd).setLocal("Magnetic Neodymium"),
	DarkIron                = metalmachore  ( 8648, "Dark Iron"             , SET_DULL              ,  55,  40,  60     ).put(MAGNETIC_PASSIVE, "FzDarkIron", "FZDarkIron")                                                                                             .setMcfg( 0, Fe             , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.IGNIS, 1).steal(Fe).qual(3, 7.0, 384, 3).heat(Steel.mMeltingPoint + 200, Fe.mBoilingPoint),
	MeteoricIron            = metalmachore  ( 8649, "Meteoric Iron"         , SET_SPACE             , 150, 140, 120     ).put(MOLTEN, MAGNETIC_ACTIVE, RAILS, DECOMPOSABLE)                                                                                             .uumMcfg( 0, Fe             , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.MAGNETO, 1).steal(WroughtIron).qual(3, 7.0, 400, 2).heat(Fe.mMeltingPoint + 200, Fe.mBoilingPoint + 200),
	MeteoricSteel           = alloymachine  ( 8650, "Meteoric Steel"        , SET_SPACE             , 130, 120, 100     ).put(MOLTEN, MAGNETIC_ACTIVE, RAILS)                                                                                                           .uumMcfg( 0, MeteoricIron   , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.MAGNETO, 1, TC.ORDO, 1).steal(Steel).qual(3, 7.0, 800, 2).heat(Steel.mMeltingPoint + 200, Steel.mBoilingPoint + 200),
	SteelGalvanized         = alloymachine  ( 8651, "SteelGalvanized"                               , 250, 240, 240     ).put(COATED, CENTRIFUGE).qual(3,  7.0, 768, 2)                                                                                                 .setMcfg( 9, Steel          , 9*U, Zn               , 1*U)                                                                                                  .aspects(TC.METALLUM, 3, TC.SANO, 1).setSmelting(Steel, U).setForging(Steel, U).setLocal("Galvanized Steel"),
	TungstenSintered        = alloymachnd   ( 8652, "TungstenSintered"      , SET_METALLIC          ,  70,  70,  70     ).put(RAILS).qual(3, 8.0, 5120, 3)                                                                                                              .uumMcfg( 0, W              , 1*U)                                                                                                                          .aspects(TC.METALLUM, 3, TC.TUTAMEN, 1).steal(W).setAllToTheOutputOf(W).setForging(null, U).setCutting(null, U).setWorking(null, U).setSmashing(null, U).setLocal("Sintered Tungsten"),
	TitaniumGold            = alloymachine  ( 8654, "Titanium-Gold"                                 , 222, 222, 255     ).put(MOLTEN).qual(3, 12.0, 5120, 4)                                                                                                            .uumMcfg( 0, Ti             , 3*U, Au               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.TUTAMEN, 3, TC.LUCRUM, 1).alloyCentrifuge(),
	Ta4HfC5                 = alloymachine  ( 8802, "Tantalum Hafnium Carbide"                      ,  32, 128,  32     )                                                                                                                                               .uumMcfg( 0, Ta             , 4*U, Hf               , 1*U, C                , 5*U)                                                                          .aspects(TC.METALLUM, 2, TC.GELUM, 2).qual(2).heat(4263).alloyCentrifuge(),
	
	ElectrotineAlloy        = alloy         ( 8658, "Electrotine Alloy"     , SET_DULL              , 100, 180, 255     ).put(WIRES, MOLTEN, FURNACE, EXTRUDER_SIMPLE)                                                                                                  .uumMcfg( 1, WroughtIron    , 1*U, Electrotine      , 8*U)                                                                                                  .aspects(TC.ELECTRUM, 3).alloyCentrifuge(1400, Fe.mBoilingPoint),
	RedAlloy                = alloy         ( 8660, "Red Alloy"             , SET_DULL              , 200,   0,   0     ).put(WIRES, MOLTEN, FURNACE, EXTRUDER_SIMPLE)                                                                                                  .uumMcfg( 1, Cu             , 1*U, Redstone         , 4*U)                                                                                                  .aspects(TC.MACHINA, 3).alloyCentrifuge(1400, Cu.mBoilingPoint),
	BlueAlloy               = alloy         ( 8659, "Blue Alloy"            , SET_DULL              , 100, 180, 255     ).put(WIRES, MOLTEN, FURNACE, EXTRUDER_SIMPLE)                                                                                                  .uumMcfg( 1, Ag             , 1*U, Nikolite         , 4*U)                                                                                                  .aspects(TC.ELECTRUM, 3).alloyCentrifuge(1400, Ag.mBoilingPoint),
	PurpleAlloy             = alloy         ( 8657, "Purple Alloy"          , SET_DULL              , 255, 120, 255     ).put(WIRES, MOLTEN, FURNACE, EXTRUDER_SIMPLE)                                                                                                  .uumMcfg( 1, RedAlloy       , 1*U, BlueAlloy        , 1*U)                                                                                                  .aspects(TC.MACHINA, 3, TC.ELECTRUM, 3).alloyCentrifuge(1400, Ag.mBoilingPoint),
	Invar                   = alloymachine  ( 8661, "Invar"                                         , 220, 220, 150     ).put(MORTAR, MAGNETIC_PASSIVE, MOLTEN).qual(3,  6.0,    256,  2)                                                                               .uumMcfg( 0, WroughtIron    , 2*U, Ni               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.GELUM, 1).alloyCentrifuge(),
	Constantan              = alloymachine  ( 8662, "Constantan"                                    , 227, 150, 128     ).put(MORTAR, MAGNETIC_PASSIVE, MOLTEN, "Cupronickel").qual(3,  6.0,  64,  1)                                                                   .uumMcfg( 0, Cu             , 1*U, Ni               , 1*U)                                                                                                  .alloyCentrifuge(), Cupronickel = Constantan,
	Nichrome                = alloymachine  ( 8663, "Nichrome"                                      , 205, 206, 246     ).put(MOLTEN).qual(3,  6.0,   64,  2)                                                                                                           .uumMcfg( 0, Ni             , 4*U, Cr               , 1*U)                                                                                                  .alloyCentrifuge(),
	Kanthal                 = alloymachine  ( 8664, "Kanthal"                                       , 194, 210, 223     ).put(MOLTEN).qual(3,  6.0,   64,  2)                                                                                                           .uumMcfg( 0, WroughtIron    , 1*U, Al               , 1*U, Cr               , 1*U)                                                                          .alloyCentrifuge(),
	Magnalium               = alloymachine  ( 8665, "Magnalium"             , SET_DULL              , 200, 190, 255     ).put(MOLTEN).qual(3,  6.0,  256,  2)                                                                                                           .uumMcfg( 0, Mg             , 1*U, Al               , 2*U)                                                                                                  .alloyCentrifuge(),
	StainlessSteel          = slloymachine  ( 8636, "Stainless Steel"                               , 200, 200, 220     ).put(MOLTEN, RAILS).qual(3,  7.0, 480,  2)                                                                                                     .uumMcfg( 0, WroughtIron    , 4*U, Invar            , 3*U, Cr               , 1*U, Mn               , 1*U)                                                  .aspects(TC.METALLUM, 2, TC.ORDO, 1, TC.VITREUS, 1).alloyCentrifuge(),
	Ultimet                 = slloymachine  ( 8666, "Ultimet"                                       , 180, 180, 230     ).put(MOLTEN).qual(3,  8.0,1024,  3)                                                                                                            .uumMcfg( 0, Co             , 5*U, Cr               , 2*U, Ni               , 1*U, Mo               , 1*U)                                                  .alloyCentrifuge(), // 54% Co, 26% Cr, 9% Ni, 5% Mo, 3% Fe, 2% W, 0.8% Mn, 0.3% Si, 0.08% Ni and 0.06% C
	TinAlloy                = alloymachine  ( 8667, "Tin Alloy"                                     , 200, 200, 200     ).put(MORTAR, MOLTEN, FURNACE, EXTRUDER_SIMPLE).qual(3,  6.5,    96,  2)                                                                        .uumMcfg( 0, Sn             , 1*U, WroughtIron      , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.FABRICO, 1).alloyCentrifuge(),
	BatteryAlloy            = alloy         ( 8668, "Battery Alloy"         , SET_DULL              , 156, 124, 160     ).put(MORTAR, MOLTEN, FURNACE, EXTRUDER_SIMPLE)                                                                                                 .uumMcfg( 0, Pb             , 4*U, Sb               , 1*U)                                                                                                  .alloyCentrifuge(),
	SolderingAlloy          = alloy         ( 8669, "Soldering Alloy"       , SET_DULL              , 220, 220, 230     ).put(MORTAR, MOLTEN, BRITTLE, SOLDERING_MATERIAL, SOLDERING_MATERIAL_GOOD, WIRES)                                                              .uumMcfg( 0, Sn             , 9*U, Sb               , 1*U)                                                                                                  .alloyCentrifuge(),
	IronWood                = alloymachine  ( 8672, "IronWood"              , SET_WOOD              , 150, 140, 110     ).put(MAGICAL, WOOD, FURNACE, EXTRUDER_SIMPLE, MORTAR, MAGNETIC_PASSIVE, MOLTEN, MD.TF).qual(2, 6.0,  384,  2).setLocal("Ironwood")             .setMcfg(18, WroughtIron    , 8*U, LiveRoot         , 9*U, Angmallen        , 2*U)                                                                          .aspects(TC.METALLUM, 2, TC.ARBOR, 1, TC.PRAECANTIO, 1).alloyCentrifuge(),
	Steeleaf                = alloymachine  ( 8673, "Steeleaf"              , SET_LEAF              ,  50, 127,  50     ).put(MAGICAL, CENTRIFUGE, MAGNETIC_PASSIVE, WOOD, MORTAR, MD.TF).qual(2, 8.0,  768,  3).setSmelting(Steel, U4)                                 .setMcfg( 1, Steel          , 1*U, Ma               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.HERBA, 1, TC.PRAECANTIO, 1),
	Knightmetal             = alloymachine  ( 8674, "Knightmetal"                                   , 210, 240, 200     ).put(MAGICAL, CENTRIFUGE, MAGNETIC_PASSIVE, MOLTEN, MORTAR, "KnightMetal", MD.TF).qual(3,  8.0, 1024,  3)                                      .setMcfg( 2, Steel          , 2*U, Ma               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.TELUM, 1, TC.PRAECANTIO, 1).heat(Steel.mMeltingPoint+100, Steel.mBoilingPoint+100).visDefault(),
	FierySteel              = alloymachine  ( 8675, "Fiery Steel"           , SET_FIERY             ,  64,   0,   0     ).put(MAGICAL, CENTRIFUGE, MAGNETIC_PASSIVE, MOLTEN, UNBURNABLE, BURNING, GLOWING, MD.TF).qual(3,  8.0, 1024,  3)                               .setMcfg( 1, Steel          , 1*U, Ma               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.IGNIS, 3, TC.PRAECANTIO, 1).heat(Steel.mBoilingPoint-200, Steel.mBoilingPoint+500).visDefault(),
	Thaumium                = alloymachine  ( 8679, "Thaumium"                                      , 150, 100, 200     ).put(MAGICAL, CENTRIFUGE, MAGNETIC_PASSIVE, MOLTEN).qual(3, 12.0,   256,  3)                                                                   .setMcfg( 1, Fe             , 1*U, Ma               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.PRAECANTIO, 1).heat(Fe.mMeltingPoint+500, Fe.mBoilingPoint+1000),
	DarkThaumium            = alloymachine  ( 8680, "Dark Thaumium"                                 , 100,  75,  75     ).put(MAGICAL, CENTRIFUGE, MAGNETIC_PASSIVE, MOLTEN).qual(3, 12.0,   512,  3)                                                                                                                                                                                                                               ,
	VoidMetal               = alloymachine  ( 8681, "Void Metal"                                    ,  30,  10,  30, 100).put(MAGICAL, CENTRIFUGE, MOLTEN, "Void").qual(3, 12.0,    2048,  4)                                                                                                                                                                                                                                       .aspects(TC.METALLUM, 7, TC.TENEBRAE, 8, TC.VACUOS, 8, TC.ALIENIS, 2, TC.HERBA, 1).heat(3000, 5000),
	Osmiridium              = alloymachine  ( 8682, "Osmiridium"                                    , 100, 100, 255     ).put(VALUABLE, MOLTEN).qual(3, 11.0, 3840, 4)                                                                                                  .uumMcfg( 0, Os             , 1*U, Ir               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.MACHINA, 2, TC.NEBRISUM, 1).alloyCentrifuge(),
	Sunnarium               = slloymachine  ( 8683, "Sunnarium"                                     , 255, 255,   0     ).put(GLOWING, LIGHTING)                                                                                                                                                                                                                                                                                    ,
	ChromiumDioxide         = alloy         ( 8685, "Chromium Dioxide"      , SET_DULL              ,  10,  20,  10     ).put(ELECTROLYSER, MAGNETIC_PASSIVE)                                                                                                           .uumMcfg( 1, Cr             , 1*U, O                , 2*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.FABRICO, 1).qual(3, 11.0,  256,  3).heat(650), CrO2 = ChromiumDioxide,
	VanadiumGallium         = slloy         ( 8686, "Vanadium-Gallium"                              , 128, 128, 140     ).put(BRITTLE)                                                                                                                                  .uumMcfg( 0, V              , 3*U, Ga               , 1*U)                                                                                                  .alloyCentrifuge(),
	YttriumBariumCuprate    = alloy         ( 8687, "Yttrium-Barium-Cuprate"                        ,  80,  64,  70     ).put(BRITTLE, ELECTROLYSER, LAYERED)                                                                                                           .uumMcfg( 6, Y              , 1*U, Ba               , 2*U, Cu               , 3*U, O                , 7*U)                                                  .heat(1200),
	NiobiumNitride          = alloy         ( 8688, "Niobium Nitride"       , SET_DULL              ,  29,  41,  29     ).put(BRITTLE, ELECTROLYSER)                                                                                                                    .uumMcfg( 0, Nb             , 1*U, N                , 1*U)                                                                                                  .heat(2573), // Anti-Reflective Material
	NiobiumTitanium         = alloy         ( 8689, "Niobium Titanium"      , SET_DULL              ,  29,  29,  41     ).put(BRITTLE)                                                                                                                                  .uumMcfg( 0, Nb             , 1*U, Ti               , 1*U)                                                                                                  .alloyCentrifuge(),
	AluminiumBrass          = alloymachine  ( 8700, "Aluminium Brass"                               , 220, 220, 130     ).put(MOLTEN, "AluminumBrass").qual(2, 6.0,  64,  2)                                                                                            .uumMcfg( 0, Al             , 3*U, Cu               , 1*U)                                                                                                  .aspects(TC.STRONTIO, 1),
	Ardite                  = metalore      ( 8707, "Ardite"                                        , 200, 120,  20     ).qual(2, 6.0,  64,  2)                                                                                                                                                                                                                                                                                     .aspects(TC.STRONTIO, 1),
	Aredrite                = metalore      ( 8701, "Aredrite"                                      , 255, 255,   0     ).put(MOLTEN).qual(2, 6.0,   64,  2)                                                                                                                                                                                                                                                                        .aspects(TC.STRONTIO, 1).visDefault(),
	Alumite                 = alloymachine  ( 8702, "Alumite"                                       , 230, 100, 230     ).put(MOLTEN).qual(2, 1.5,   64,  0)                                                                                                            .setMcfg( 9, Al             , 5*U, WroughtIron      , 2*U, Obsidian         ,18*U)                                                                          .aspects(TC.STRONTIO, 2).alloyCentrifuge(),
	Manyullyn               = alloymachine  ( 8703, "Manyullyn"                                     , 175, 100, 175     ).put(MOLTEN).qual(2, 2.0,   96,  1)                                                                                                            .setMcfg( 0, Co             , 1*U, Ardite           , 1*U)                                                                                                  .aspects(TC.STRONTIO, 2).alloyCentrifuge(),
	VibraniumSteel          = slloymachine  ( 8704, "Vibranium Steel"                               ,  40,  24,  50     ).put(CENTRIFUGE, MAGNETIC_PASSIVE).qual(3,   50.0, 2048, 10)                                                                                   .uumMcfg( 0, Vb             , 1*U, Steel            , 3*U)                                                                                                  .aspects(TC.SENSUS, 1, TC.VITREUS, 1, TC.METALLUM, 1).alloyCentrifuge(),
	VibraniumSilver         = slloy         ( 8705, "Vibranium Silver"                              , 240, 240, 255     ).put(CENTRIFUGE, ENDER_DRAGON_PROOF).qual(3,  100.0,  512,  9)                                                                                 .uumMcfg( 0, Vb             , 1*U, Ag               , 3*U)                                                                                                  .aspects(TC.SENSUS, 1, TC.VITREUS, 1, TC.LUCRUM, 1).alloyCentrifuge(),
	Vibramantium            = slloymachine  ( 8706, "Vibramantium"                                  , 250, 250, 250     ).put(CENTRIFUGE, MAGNETIC_PASSIVE).qual(3, 1000.0, 5120, 15)                                                                                   .uumMcfg( 0, Vb             , 1*U, Ad               , 3*U)                                                                                                  .aspects(TC.SENSUS, 1, TC.VITREUS, 1, TC.PRAECANTIO, 1, TC.METALLUM, 1).alloyCentrifuge(),
	Signalum                = alloymachine  ( 8708, "Signalum"                                      , 255,  64,   0     ).put(MOLTEN, FURNACE, EXTRUDER_SIMPLE)                                                                                                         .uumMcfg( 8, Cu             , 1*U, Ag               , 2*U, RedAlloy         , 5*U)                                                                          .aspects(TC.METALLUM, 3, TC.POTENTIA, 1).alloyCentrifuge(),
	Lumium                  = slloymachine  ( 8709, "Lumium"                                        , 255, 255,  80     ).put(MOLTEN, FURNACE, EXTRUDER_SIMPLE, LIGHTING, GLOWING)                                                                                      .uumMcfg( 4, Sn             , 3*U, Ag               , 1*U, Glowstone        , 4*U)                                                                          .aspects(TC.METALLUM, 3, TC.LUX, 1).alloyCentrifuge(),
	EnderiumBase            = alloymachine  ( 8729, "Enderium Base"                                 ,  53,  85, 108     ).put(MOLTEN, MD.EIO)                                                                                                                           .uumMcfg( 4, Sn             , 2*U, Ag               , 1*U, Pt               , 1*U)                                                                          .aspects(TC.METALLUM, 3).alloyCentrifuge(),
	Enderium                = alloymachine  ( 8710, "Enderium"                                      ,  60, 125, 115     ).put(MAGICAL, MOLTEN).qual(3,  8.0, 256, 3)                                                                                                    .setMcfg( 1, EnderiumBase   , 1*U, EnderPearl       , 1*U)                                                                                                  .aspects(TC.METALLUM, 3, TC.ALIENIS, 1).alloyCentrifuge(),
//  InfusedGold             = slloymachore  ( 8712, "Infused Gold"                                  , 255, 200,  60     ).qual(2, 12.0, 64, 3)                                                                                                                                                                                                                                                                                      ,
	FakeOsmium              = alloy         ( 8719, "Osmium"                                        , 160, 160, 255     ).put(G_INGOT_MACHINE_ORES, MOLTEN, FURNACE, EXTRUDER_SIMPLE).setLocal("'Osmium'").steal(Os).qual(2, 6.0, 256, 2)                               .uumMcfg( 0, Os             , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.MACHINA, 2),
	RefinedGlowstone        = alloy         ( 8713, "GlowstoneRefined"      , SET_REDSTONE          , 255, 240, 100     ).put("RefinedGlowstone", GLOWING).setLocal("Refined Glowstone").qual(2, 8.0, 256, 2)                                                           .setMcfg( 1, Glowstone      , 1*U)                                                                                                                          .aspects(TC.METALLUM, 4, TC.LUX, 4).setPulver(MT.Glowstone, U).setSmelting(MT.Glowstone, U),
	RefinedObsidian         = alloy         ( 8714, "ObsidianRefined"       , SET_REDSTONE          , 120,  90, 140     ).put("RefinedObsidian").setLocal("Refined Obsidian").qual(2, 8.0, 512, 3)                                                                      .setMcfg( 1, Obsidian       , 1*U)                                                                                                                          .aspects(TC.VITREUS, 4, TC.IGNIS, 4).setPulver(MT.Obsidian, U).setSmelting(MT.Obsidian, U),
	Yellorium               = setalore      ( 8715, "Yellorium"                                     , 140, 130,  20     )                                                                                                                                                                                                                                                                                                           .aspects(TC.RADIO, 2),
	Blutonium               = setalore      ( 8716, "Blutonium"                                     ,  60,  60, 180     )                                                                                                                                                                                                                                                                                                           .aspects(TC.RADIO, 3),
	Cyanite                 = setalore      ( 8717, "Cyanite"                                       ,  50, 110, 150     )                                                                                                                                                                                                                                                                                                           .aspects(TC.RADIO, 1),
	Ludicrite               = setalore      ( 8723, "Ludicrite"                                     , 180, 120, 150     )                                                                                                                                                                                                                                                                                                           .aspects(TC.RADIO, 5),
	Yellorite               = oredustelec   ( 8724, "Yellorite"             , SET_METALLIC          , 150, 140,  40, 255).put(BLACKLISTED_SMELTER).setSmelting(Yellorium,U3)                                                                                            .setMcfg( 1, Yellorium      , 1*U, O                , 2*U)                                                                                                  .aspects(TC.RADIO, 1),
	Bedrock_HSLA_Alloy      = alloy         ( 8718, "Bedrock-HSLA-Alloy"    , SET_STONE             ,  64,  64,  64     ).put(CENTRIFUGE, MAGNETIC_PASSIVE)                                                                                                             .setMcfg( 1, Bedrock        , 4*U, HSLA             , 1*U)                                                                                                  .aspects(TC.TERRA, 5).qual(3, 10.0, 2560, 5).heat(4000),
	PulsatingIron           = alloy         ( 8725, "Pulsating Iron"                                , 100, 160, 110     ).put(MAGNETIC_PASSIVE, "PhasedIron").qual(WroughtIron)                                                                                         .setMcfg( 1, WroughtIron    , 1*U, EnderPearl       , 1*U)                                                                                                  .aspects(TC.METALLUM, 3, TC.ALIENIS, 1).setOriginalMod(MD.EIO).alloyCentrifuge(),
	ConductiveIron          = alloy         ( 8727, "Conductive Iron"                               , 170, 140, 140     ).put(MAGNETIC_PASSIVE).qual(WroughtIron)                                                                                                       .uumMcfg( 1, WroughtIron    , 1*U, Redstone         , 1*U)                                                                                                  .aspects(TC.METALLUM, 3, TC.POTENTIA, 1).setOriginalMod(MD.EIO).alloyCentrifuge(),
	EnergeticAlloy          = alloy         ( 8728, "Energetic Alloy"       , SET_DULL              , 200, 120,  50     ).qual(Au)                                                                                                                                      .uumMcfg( 1, InductiveAlloy , 2*U, Glowstone        , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.LUCRUM, 1, TC.LUX, 1).setOriginalMod(MD.EIO).alloyCentrifuge(),
	VibrantAlloy            = slloy         ( 8726, "Vibrant Alloy"                                 , 160, 170,  70     ).put("PhasedGold").qual(EnergeticAlloy)                                                                                                        .setMcfg( 1, EnergeticAlloy , 1*U, EnderPearl       , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.LUCRUM, 1, TC.LUX, 1, TC.ALIENIS, 1).setOriginalMod(MD.EIO).alloyCentrifuge(),
	ElectrumFlux            = slloy         ( 8711, "Electrum Flux"                                 , 255, 255, 120     ).qual(3, 14.0, 64, 2)                                                                                                                          .uumMcfg( 1, Electrum       , 1*U, Redstone         , 2*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.PERMUTATIO, 2).alloyCentrifuge(),
	ElectricalSteel         = alloy         ( 8730, "Electrical Steel"                              , 140, 140, 140     ).put(MAGNETIC_PASSIVE).qual(Steel)                                                                                                             .uumMcfg( 1, Steel          , 1*U, Si               , 1*U)                                                                                                  .aspects(TC.METALLUM, 3, TC.ELECTRUM, 1).setOriginalMod(MD.EIO).alloyCentrifuge(),
	ObsidianSteel           = alloy         ( 8731, "Obsidian Steel"                                ,  60,  60,  60     ).put(MAGNETIC_PASSIVE, RAILS, "DarkSteel").qual(Steel)                                                                                         .setMcfg( 1, Steel          , 1*U, Obsidian         , 9*U)                                                                                                  .aspects(TC.METALLUM, 3, TC.TENEBRAE, 1).setOriginalMod(MD.TG).alloyCentrifuge(),
	Soularium               = alloy         ( 8732, "Soularium"             , SET_DULL              ,  90,  70,  50     ).qual(Au)                                                                                                                                      .setMcfg( 1, SoulSand       , 1*U, Au               , 1*U)                                                                                                  .aspects(TC.METALLUM, 3, TC.SPIRITUS, 1).setOriginalMod(MD.EIO).alloyCentrifuge(),
	RedstoneAlloy           = alloy         ( 8733, "Redstone Alloy"                                , 140,  50,  50     ).put(MOLTEN)                                                                                                                                   .uumMcfg( 1, Si             , 1*U, Redstone         , 1*U)                                                                                                  .aspects(TC.METALLUM, 3, TC.MACHINA, 1).setOriginalMod(MD.EIO).alloyCentrifuge().setPriorityPrefix(5),
	NikolineAlloy           = alloy         ( 8737, "Nikoline Alloy"                                ,  50,  90, 140     ).put(MOLTEN)                                                                                                                                   .uumMcfg( 1, Si             , 1*U, Nikolite         , 1*U)                                                                                                  .aspects(TC.METALLUM, 3, TC.POTENTIA, 1).alloyCentrifuge().setPriorityPrefix(5),
	TeslatineAlloy          = alloy         ( 8738, "Teslatine Alloy"                               ,  50,  90, 140     ).put(MOLTEN)                                                                                                                                   .uumMcfg( 1, Si             , 1*U, Teslatite        , 1*U)                                                                                                  .aspects(TC.METALLUM, 3, TC.POTENTIA, 1).alloyCentrifuge().setPriorityPrefix(5),
	SpectreIron             = slloymachine  ( 8734, "Spectre Iron"                                  , 150, 200, 200, 200).put(MAGNETIC_PASSIVE, MAGICAL, MOLTEN, GLOWING).qual(3,  8.5, 768, 2)                                                                         .setMcfg( 1, WroughtIron    , 1*U, Ectoplasm        , 1*U)                                                                                                  .aspects(TC.METALLUM, 3, TC.SPIRITUS, 3).alloyCentrifuge(Fe),
	Manasteel               = slloymachine  ( 8720, "Manasteel"                                     , 110, 200, 250     ).put(MAGICAL)                                                                                                                                                                                                                                                                                              .aspects(TC.METALLUM, 2, TC.PRAECANTIO, 2).steal(Steel).qual(3, 12.0, 256, 3).heat(Fe.mMeltingPoint+ 500, Fe.mBoilingPoint+1000),
	Terrasteel              = slloymachine  ( 8721, "Terrasteel"                                    , 110, 200,  50     ).put(MAGICAL)                                                                                                                                                                                                                                                                                              .aspects(TC.METALLUM, 2, TC.PRAECANTIO, 2).steal(Steel).qual(3, 16.0,2048, 4).heat(Fe.mMeltingPoint+ 750, Fe.mBoilingPoint+1500),
	ElvenElementium         = slloymachore  ( 8722, "Elven Elementium"                              , 250, 120, 250     ).put(MAGICAL)                                                                                                                                                                                                                                                                                              .aspects(TC.METALLUM, 2, TC.PRAECANTIO, 2).steal(Steel).qual(3, 14.0, 512, 3).heat(Fe.mMeltingPoint+1000, Fe.mBoilingPoint+2000).setLocal("Elementium"),
	GaiaSpirit              = slloymachine  ( 8735, "Gaia Spirit"                                   , 250, 250, 250     ).put(MAGICAL, GLOWING).qual(3, 20.0,2048, 4)                                                                                                                                                                                                                                                               .aspects(TC.AURAM, 4, TC.SPIRITUS, 4, TC.PRAECANTIO, 4).heat(W.mMeltingPoint+250, W.mBoilingPoint+500),
	Endium                  = metalore      ( 8736, "Endium"                                        ,  50,  10,  50     ).put(MAGICAL).qual(3, 12.0, 256, 3)                                                                                                                                                                                                                                                                        .aspects(TC.METALLUM, 1, TC.ALIENIS, 3),
	Mauftrium               = slloymachore  ( 8739, "Mauftrium"                                     , 250, 225, 121     ).put(MAGICAL).qual(3, 12.0,1024, 3)                                                                                                                                                                                                                                                                        .aspects(TC.METALLUM, 2, TC.PRAECANTIO, 1).heat(Fe.mMeltingPoint, Fe.mBoilingPoint),
	Elvorium                = slloymachore  ( 8740, "Elvorium"                                      , 235, 164,  77     ).put(MAGICAL).qual(3, 14.0,2048, 3)                                                                                                            .setMcfg( 1, ElvenElementium, 1*U, ElvenDragonstone , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.PRAECANTIO, 4).heat(Fe.mMeltingPoint+1000, Fe.mBoilingPoint+2000),
	NiflheimPower           = slloy         ( 8741, "Niflheim Power"                                ,  94,  94, 174     ).put(MAGICAL, GLOWING, UNBURNABLE).qual(3, 18.0,2048, 3)                                                                                       .setMcfg( 1, Elvorium       , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.GELUM     , 2, TC.POTENTIA, 2),
	MuspelheimPower         = slloy         ( 8742, "Muspelheim Power"                              , 174,  94,  94     ).put(MAGICAL, GLOWING, UNBURNABLE, BURNING).qual(3, 18.0,2048, 3)                                                                              .setMcfg( 1, Elvorium       , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.IGNIS     , 2, TC.POTENTIA, 2),
	Iffesal                 = oredust       ( 8743, "Iffesal"               , SET_SHINY             ,  14,  25, 171, 255).put(MAGICAL)                                                                                                                                                                                                                                                                                              .aspects(TC.METALLUM, 2, TC.PRAECANTIO, 4),
	
	
	AncientDebris           = metalore      ( 8744, "Ancient Debris"        , SET_SPACE             , 110,  80,  90, 255).put("Ancient", UNBURNABLE, WITHER_PROOF, VALUABLE, MOLTEN, MELTING, WASHING_MERCURY).qual(0, 1.0,   16,  3)                                                                                                                                                                                               .aspects(TC.METALLUM, 2, TC.ALIENIS, 2).heat(MeteoricIron),
	Netherite               = alloymachine  ( 8745, "Netherite"                                     ,  80,  70,  80     ).put(           UNBURNABLE, WITHER_PROOF, VALUABLE, MOLTEN).qual(2, 10.0,  500,  4)                                                            .setMcfg( 1, Au             , 4*U, AncientDebris    , 4*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.ALIENIS, 2, TC.LUCRUM, 2).alloyCentrifuge(MeteoricSteel),
	NetherizedDiamond       = alloymachine  ( 8746, "Netherized Diamond"    , SET_DIAMOND           ,  90,  80,  90, 255).put(G_GEM    , UNBURNABLE, WITHER_PROOF, VALUABLE, COATED).qual(3, 12.0, 2560,  4)                                                            .setMcfg( 4, Netherite      , 1*U, Diamond          , 4*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.ALIENIS, 2, TC.LUCRUM, 2, TC.VITREUS, 2).heat(MeteoricSteel),
	
	
	Desh                    = metalmachore  ( 8750, "Desh"                  , SET_DULL              ,  40,  40,  40     ).qual(3,  4.0,   1280,  3)                                                                                                                                                                                                                                                                                 .aspects(TC.METALLUM, 2, TC.ALIENIS, 1, TC.TELUM, 1).heat(Steel),
	DuraniumAlloy           = alloymachine  ( 8751, "Duranium"                                      ,  75, 175, 175     ).qual(3,  8.0,   1280,  4)                                                                                                                     .uumMcfg( 0, Dn             , 7*U, Mg               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.TUTAMEN, 2).setOriginalMod(MD.MO).setLocal("Duranium Alloy").alloyCentrifuge(), // That Info with Magnesium/Magnesite was pretty hard to find, but I found it!
	TritaniumAlloy          = alloymachine  ( 8752, "Tritanium"                                     ,  55, 155, 155     ).qual(3, 12.0,   2560,  5)                                                                                                                     .uumMcfg( 0, Tn             , 3*U, Dn               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.TUTAMEN, 3, TC.VITREUS, 1).setOriginalMod(MD.MO).setLocal("Tritanium Alloy").alloyCentrifuge(),
	Dolamide                = oredust       ( 8753, "Dolamide"              , SET_METALLIC          , 188, 100, 122, 255)                                                                                                                                                                                                                                                                                                           .aspects(TC.POTENTIA, 3, TC.RADIO, 2),
	Oriharukon              = metalmachore  ( 8754, "Oriharukon"                                    , 220, 220, 240     ).qual(3,  8.0,   2560,  2)                                                                                                                                                                                                                                                                                 .aspects(TC.METALLUM, 2, TC.MACHINA, 2),
	Adamantite              = metalmachore  ( 8755, "Adamantite"                                    , 255, 255, 190     ).qual(3,  6.0,   2560,  3)                                                                                                                                                                                                                                                                                 .aspects(TC.METALLUM, 2, TC.TUTAMEN, 2),
	Duralumin               = alloymachine  ( 8756, "Duralumin"             , SET_DULL              , 255, 255, 220     ).qual(3, 10.0,    512,  2)                                                                                                                     .uumMcfg( 0, Al             , 1*U, Cu               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.PERMUTATIO, 1, TC.VOLATUS, 1),
	Meteorite               = metalmachore  ( 8757, "Meteorite"             , SET_SPACE             , 222, 100, 222     ).put(MOLTEN, MAGNETIC_ACTIVE, RAILS, DECOMPOSABLE)                                                                                             .uumMcfg( 0, Fe             , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.MAGNETO, 1, TC.ALIENIS, 1).steal(MeteoricIron).qual(3, 8.0, 1200, 3),
	FrozenIron              = metalmachore  ( 8758, "Frozen Iron"           , SET_DULL              , 235, 235, 255     ).put(DECOMPOSABLE, MAGNETIC_PASSIVE)                                                                                                           .uumMcfg( 0, Fe             , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.GELUM, 2).steal(Fe).setSmelting(Fe, U).setForging(Fe, U),
	Kreknorite              = metalmachore  ( 8759, "Kreknorite"            , SET_SPACE             , 128,   0,   0     ).put(MOLTEN, TICKS_PER_SMELT*18)                                                                                                                                                                                                                                                                           .aspects(TC.METALLUM, 2, TC.IGNIS, 2).qual(3, 8.0, 1200, 3).heat(MeteoricSteel.mMeltingPoint + 200, MeteoricSteel.mBoilingPoint + 200),
	Syrmorite               = metalmachore  ( 8785, "Syrmorite"             , SET_DULL              ,  80,  80, 199     ).put(MOLTEN)                                                                                                                                                                                                                                                                                               .aspects(TC.METALLUM, 2, TC.MORTUUS, 1).qual(2, 6.0,  500, 1).heat(Au),
	Octine                  = metalmachore  ( 8786, "Octine"                                        , 255, 128,  32     ).put(MOLTEN, MAGICAL, GLOWING, UNBURNABLE, BURNING)                                                                                                                                                                                                                                                        .aspects(TC.METALLUM, 2, TC.MORTUUS, 1).qual(3, 8.0,  900, 2).heat(Steel.mBoilingPoint-200, Steel.mBoilingPoint+500),
	
	
	HSSG                    = alloymachine  ( 8796, "HSS-G"                                         , 153, 153,   0     ).put(MOLTEN).qual(3, 10.0F, 4000, 3)                                                                                                           .uumMcfg( 0, TungstenSteel  , 5*U, Cr               , 1*U, Mo               , 2*U, V                , 1*U)                                                  .aspects(TC.METALLUM, 2, TC.TUTAMEN, 1, TC.MACHINA, 1).alloyCentrifuge(),
	HSSE                    = alloymachine  ( 8797, "HSS-E"                                         ,  51, 102,   0     ).put(MOLTEN).qual(3, 10.0F, 5120, 4)                                                                                                           .uumMcfg( 0, HSSG           , 6*U, Co               , 1*U, Mn               , 1*U, Si               , 1*U)                                                  .aspects(TC.METALLUM, 2, TC.TUTAMEN, 2, TC.MACHINA, 2).alloyCentrifuge(),
	HSSS                    = alloymachine  ( 8798, "HSS-S"                                         , 102,   0,  51     ).put(MOLTEN).qual(3, 14.0F, 3000, 4)                                                                                                           .uumMcfg( 0, HSSG           , 6*U, Osmiridium       , 2*U, Ir               , 1*U)                                                                          .aspects(TC.METALLUM, 2, TC.TUTAMEN, 2, TC.MACHINA, 2, TC.NEBRISUM, 1).alloyCentrifuge(),
	
	
	Bedrockium              = metalmachore  ( 8795, "Bedrockium"            , SET_ROUGH             ,  88,  88,  88     ).put(WITHER_PROOF, ENDER_DRAGON_PROOF)                                                                                                                                                                                                                                                                     .aspects(TC.METALLUM, 1, TC.TERRA, 10).heat(2000),
	
	
	Draconium               = metalmachore  ( 8791, "Draconium"                                     , 150,  50, 250     ).qual(3, 16.0F,  5000, 4).put(MOLTEN, WITHER_PROOF, ENDER_DRAGON_PROOF)                                                                                                                                                                                                                                    .aspects(TC.METALLUM, 2, TC.ALIENIS, 1).heat(4500),
	DraconiumAwakened       = alloymachine  ( 8792, "DraconiumAwakened"                             , 250, 150,  50     ).qual(3, 24.0F, 10000, 5).put(MOLTEN, WITHER_PROOF, ENDER_DRAGON_PROOF, GLOWING)                                                               .setMcfg( 1, Draconium      , 1*U)                                                                                                                          .aspects(TC.METALLUM, 2, TC.ALIENIS, 4).heat(5500).setLocal("Awakened Draconium"),
	
	
	CrystalMatrix           = slloymachine  ( 8799, "Crystal Matrix"                                ,  83, 231, 234     ).qual(3,        20.0,     25600,  4).put(VALUABLE, UNBURNABLE, MAGICAL, WITHER_PROOF, ENDER_DRAGON_PROOF)                                      .setMcfg( 1, Diamond        ,20*U, NetherStar       , 2*U)                                                                                                  .aspects(TC.VITREUS, 8, TC.PRAECANTIO, 4, TC.NEBRISUM, 4).heat(3896, 5127),
	CosmicNeutronium        = setalmachine  ( 8800, "Cosmic Neutronium"                             ,  30,  10,  40     ).qual(3,        50.0,    100000, 10).put(VALUABLE, UNBURNABLE, MAGICAL, WITHER_PROOF, ENDER_DRAGON_PROOF)                                                                                                                                                                                                  .aspects(TC.POTENTIA, 10, TC.NEBRISUM, 10, TC.RADIO, 10, TC.TENEBRAE, 10, TC.TERRA, 10).heat(100000),
	Infinity                = setalmachine  ( 8801, "Infinity"                                      , 250, 250, 250     ).qual(3,1000000000.0,1000000000, 15).put(VALUABLE, UNBURNABLE, MAGICAL, WITHER_PROOF, ENDER_DRAGON_PROOF, GLOWING, LIGHTING, MAGNETIC_ACTIVE)                                                                                                                                                              .aspects(TC.POTENTIA, 10, TC.NEBRISUM, 10, TC.PERMUTATIO, 10, TC.MAGNETO, 10, TC.PRAECANTIO, 10, TC.AURAM, 10).heat(100000),
	
	
	Trinaquadalloy          = alloymachine  ( 8684, "Trinaquadalloy"                                , 146, 186, 146     ).put(CENTRIFUGE, "NaquadahAlloy").qual(3, 14.0,  20480,  5)                                                                                    .uumMcfg( 0, Ke             , 6*U, Nq               , 2*U, C                , 1*U)                                                                          .aspects(TC.METALLUM, 3, TC.TUTAMEN, 3, TC.NEBRISUM, 1).alloyCentrifuge(),
	Trinitanium             = alloymachine  ( 8790, "Trinitanium"                                   , 235, 175, 255     ).qual(3, 16.0,   5120,  4)                                                                                                                     .uumMcfg( 0, Ke             , 2*U, Ti               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.TUTAMEN, 2, TC.VITREUS, 2).alloyCentrifuge(),
	
	
	Iritanium               = alloymachine  ( 8793, "Titanium Iridium"                              , 220, 220, 255     ).put("Iritanium").qual(3,  8.0,   7680,  4)                                                                                                    .uumMcfg( 0, Ir             , 1*U, Ti               , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.TUTAMEN, 1, TC.MACHINA, 1).alloyCentrifuge().setLocal("Iritanium"),
	TitaniumAluminide       = alloymachine  ( 8794, "Titanium Aluminide"                            , 200, 200, 255     ).qual(3, 12.0,   3840,  3)                                                                                                                     .uumMcfg( 3, Ti             , 3*U, Al               , 7*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.TUTAMEN, 1, TC.VOLATUS, 1).alloyCentrifuge();
	
	
	static {
		H2O.setSolidifying(Ice, U);
		Lava.setSolidifying(Obsidian, U).setDensity(Obsidian.mGramPerCubicCentimeter);
		Clay.setSmelting(Ceramic, U);
		ClayBrown.setSmelting(Ceramic, U);
		Netherrack.setSmelting(NetherBrick, U);
		
		ANY.init();
		
		// Making sure shit is statically loaded, damn it.
		TECH.Brick.getClass();
		DATA.Dye_Materials.getClass();
	}
	
	/** I had to remove the full length names of Elements from this List, but in order to keep Compat with Mods that used some, such as IHL or Tinkers Gregworks, I got a few of them here. */
	@Deprecated public static final OreDictMaterial Trinium = Ke, Vibranium = Vb, Naquadah = Nq, NaquadahEnriched = Nq_528, Naquadria = Nq_522, Adamantium = Ad, Silver = Ag, Aluminium = Al, Bismuth = Bi, Lead = Pb, Argon = Ar, Copper = Cu, Gold = Au, Iron = Fe, Titanium = Ti, Calcite = CaCO3, Tungsten = W, Beryllium = Be, Chromium = Cr, Manganese = Mn, Cobalt = Co, Cobalt60 = Co_60, Nickel = Ni, Arsenic = As, Zirconium = Zr, Molybdenum = Mo, Technetium = Tc, Palladium = Pd, Neodymium = Nd, Osmium = Os, Iridium = Ir, Platinum = Pt, Thorium = Th, Uranium = U_238, Uranium235 = U_235, Plutonium = Pu, Plutonium241 = Pu_241, Plutonium243 = Pu_243, Americium = Am, Americium241 = Am_241;
	
	/** Technical Materials, which are only there for Recipes and such. */
	public static class TECH {
		@Deprecated public static final OreDictMaterial AnyGlowstone = ANY.Glowstone, AnyWax = ANY.Wax, AnyWood = ANY.Wood, AnyStone = ANY.Stone, AnyClay = ANY.Clay, AnyIron = ANY.Fe, AnyIronSteel = ANY.Steel, AnyCopper = ANY.Cu, AnySilicon = ANY.Si, AnyTungsten = ANY.W, AnyThaumicCrystal = ANY.ThaumCrystal, AnySalt = ANY.Salt, AnySteel = ANY._Steel, AnyBronze = ANY._Bronze, AnyMetal = ANY._Metal;
		
		public static final OreDictMaterial
		Brick                       = invalid("Brick"                       ).put(IGNORE_IN_COLOR_LOG, DONT_SHOW_THIS_COMPONENT).put(STONE, BRITTLE, MORTAR).heat(2000).setRGBa(183, 90, 64, 255),
		Organic                     = invalid("Organic"                     ).put(IGNORE_IN_COLOR_LOG, DONT_SHOW_THIS_COMPONENT),
		Crystal                     = invalid("Crystal"                     ).put(IGNORE_IN_COLOR_LOG, DONT_SHOW_THIS_COMPONENT).put(BRITTLE, CRYSTAL),
		Unknown                     = invalid("Unknown"                     ).put(IGNORE_IN_COLOR_LOG, DONT_SHOW_THIS_COMPONENT),
		Cobblestone                 = invalid("Cobblestone"                 ).put(IGNORE_IN_COLOR_LOG, DONT_SHOW_THIS_COMPONENT).put(UNRECYCLABLE),
		
		RefinedIron                 = invalid("RefinedIron"                 ).stealLooks(HSLA).steal(WroughtIron).setLocal("Refined Iron").setAllToTheOutputOf(Fe).put(IGNORE_IN_COLOR_LOG, SMITHABLE, MELTING).addReRegistrationToThis(WroughtIron);
		
		static {
			OreDictMaterial.MATERIAL_ARRAY[9142] = MT.Asbestos;
			
			Ad                      .visDefault(Adamantine);
			
			Fe                      .setOreMultiplier( 3).setCrushing(Fe2O3, U);
			Al                      .setOreMultiplier( 2).setCrushing(Al2O3, U);
			Ti                      .setOreMultiplier( 2).setCrushing(TiO2, U);
			W                       .setOreMultiplier( 2).setCrushing(OREMATS.Scheelite, U);
			U_238                   .setOreMultiplier( 2).setCrushing(OREMATS.Uraninite, U);
			Amber                   .setOreMultiplier( 2);
			Zircon                  .setOreMultiplier( 2);
			Draconium               .setOreMultiplier( 2);
			OREMATS.Cassiterite     .setOreMultiplier( 2);
			NetherQuartz            .setOreMultiplier( 2);
			CertusQuartz            .setOreMultiplier( 2);
			ChargedCertusQuartz     .setOreMultiplier( 2);
			Monazite                .setOreMultiplier( 2);
			Scabyst                 .setOreMultiplier( 2);
			Phosphorus              .setOreMultiplier( 3);
			NaNO3                   .setOreMultiplier( 3);
			KNO3                    .setOreMultiplier( 3);
			Apatite                 .setOreMultiplier( 4);
			Lapis                   .setOreMultiplier( 5);
			Sodalite                .setOreMultiplier( 5);
			Lazurite                .setOreMultiplier( 5);
			Azurite                 .setOreMultiplier( 5);
			Eudialyte               .setOreMultiplier( 5);
			Moonstone               .setOreMultiplier( 2);
			Sunstone                .setOreMultiplier( 4);
			Chimerite               .setOreMultiplier( 3);
			
			Wood                    .put(MD.MC);
			Stone                   .put(MD.MC);
			Fe                      .put(MD.MC, COMMON_ORE);
			Au                      .put(MD.MC, COMMON_ORE);
			Diamond                 .put(MD.MC, COMMON_ORE);
			Emerald                 .put(MD.MC, COMMON_ORE);
			NetherQuartz            .put(MD.MC, COMMON_ORE);
			Lapis                   .put(MD.MC, COMMON_ORE);
			Redstone                .put(MD.MC, COMMON_ORE);
			Glowstone               .put(MD.MC, COMMON_ORE);
			Coal                    .put(MD.MC, COMMON_ORE);
			Charcoal                .put(MD.MC);
			EnderPearl              .put(MD.MC);
			EnderEye                .put(MD.MC);
			Blaze                   .put(MD.MC);
			Gunpowder               .put(MD.MC);
			Sugar                   .put(MD.MC);
			Cocoa                   .put(MD.MC);
			Milk                    .put(MD.MC);
			Paper                   .put(MD.MC);
			Clay                    .put(MD.MC);
			Netherrack              .put(MD.MC);
			NetherBrick             .put(MD.MC);
			SoulSand                .put(MD.MC);
			NetherStar              .put(MD.MC);
			Endstone                .put(MD.MC);
			Bedrock                 .put(MD.MC);
			Sand                    .put(MD.MC);
			Glass                   .put(MD.MC);
			Gravel                  .put(MD.MC);
			H2O                     .put(MD.MC);
			Snow                    .put(MD.MC);
			Ice                     .put(MD.MC);
			Bone                    .put(MD.MC);
			Lava                    .put(MD.MC);
			Flint                   .put(MD.MC);
			Obsidian                .put(MD.MC);
			Leather                 .put(MD.MC);
			MeatRotten              .put(MD.MC);
			Wheat                   .put(MD.MC);
			Potato                  .put(MD.MC);
			
			
			Black                   .put(MD.MC);
			Red                     .put(MD.MC);
			Green                   .put(MD.MC);
			Brown                   .put(MD.MC);
			Blue                    .put(MD.MC);
			Purple                  .put(MD.MC);
			Cyan                    .put(MD.MC);
			LightGray               .put(MD.MC);
			Gray                    .put(MD.MC);
			Pink                    .put(MD.MC);
			Lime                    .put(MD.MC);
			Yellow                  .put(MD.MC);
			LightBlue               .put(MD.MC);
			Magenta                 .put(MD.MC);
			Orange                  .put(MD.MC);
			White                   .put(MD.MC);
			
			
			Granite                 .put(MD.EtFu);
			Diorite                 .put(MD.EtFu);
			Andesite                .put(MD.EtFu);
			PrismarineLight         .put(MD.EtFu);
			PrismarineDark          .put(MD.EtFu);
			
			
			NaCl                    .put(MD.HaC, COMMON_ORE);
			
			
			Basalt                  .put(MD.NePl);
			Netherite               .put(MD.NePl, COMMON_ORE);
			NetherizedDiamond       .put(MD.NePl);
			AncientDebris           .put(MD.NePl, COMMON_ORE);
			
			
			Zn                      .put(MD.GT, COMMON_ORE);
			Craponite               .put(MD.GT);
			NitroCarbon             .put(MD.GT);
			NitroFuel               .put(MD.GT);
			SoylentGreen            .put(MD.GT);
			ClayBrown               .put(MD.GT);
			Ceramic                 .put(MD.GT);
			SluiceSand              .put(MD.GT);
			Nichrome                .put(MD.GT);
			Kanthal                 .put(MD.GT);
			Magnalium               .put(MD.GT);
			BatteryAlloy            .put(MD.GT);
			SolderingAlloy          .put(MD.GT);
			AnnealedCopper          .put(MD.GT);
			IronMagnetic            .put(MD.GT);
			SteelMagnetic           .put(MD.GT);
			NeodymiumMagnetic       .put(MD.GT);
			StainlessSteel          .put(MD.GT);
			TungstenSteel           .put(MD.GT);
			Ta4HfC5                 .put(MD.GT);
			UUAmplifier             .put(MD.GT);
			
			
			HSSG                    .put(MD.GT5U);
			HSSE                    .put(MD.GT5U);
			HSSS                    .put(MD.GT5U);
			PlatinumGroupSludge     .put(MD.GT5U);
			
			
			Os                      .put(COMMON_ORE).setOriginalMod("gravisuite", "Gravisuite (Old IC2 Addon)");
			
			
			Cu                      .put(MD.IC2, COMMON_ORE);
			Sn                      .put(MD.IC2, COMMON_ORE);
			Bronze                  .put(MD.IC2);
			RefinedIron             .put(MD.IC2);
			Ir                      .put(MD.IC2, COMMON_ORE);
			U_238                   .put(MD.IC2);
			U_235                   .put(MD.IC2);
			Pu                      .put(MD.IC2);
			DistWater               .put(MD.IC2);
			SiO2                    .put(MD.IC2);
			ConstructionFoam        .put(MD.IC2);
			UUMatter                .put(MD.IC2);
			HydratedCoal            .put(MD.IC2);
			Coffee                  .put(MD.IC2);
			Rubber                  .put(MD.IC2);
			WoodRubber              .put(MD.IC2);
			
			
			Oil                     .put(MD.BC);
			Fuel                    .put(MD.BC);
			
			
			SiC                     .put(MD.IHL);
			H2Ca2B2Si2O10           .put(MD.IHL);
			H3BO3                   .put(MD.IHL);
			Li2O                    .put(MD.IHL);
			NaOH                    .put(MD.IHL);
			NaHSO4                  .put(MD.IHL);
			H2O2                    .put(MD.IHL);
			Li2Fe2O4                .put(MD.IHL);
			Porcelain               .put(MD.IHL);
			
			
			Bi                      .put(MD.TFC, COMMON_ORE);
			Jasper                  .put(MD.TFC);
			WroughtIron             .put(MD.TFC);
			RoseGold                .put(MD.TFC);
			SterlingSilver          .put(MD.TFC);
			BlackBronze             .put(MD.TFC);
			BismuthBronze           .put(MD.TFC);
			BlackSteel              .put(MD.TFC);
			RedSteel                .put(MD.TFC);
			BlueSteel               .put(MD.TFC);
			DamascusSteel           .put(MD.TFC);
			
			
			S                       .put(MD.RC, COMMON_ORE);
			KNO3                    .put(MD.RC, COMMON_ORE);
			Firestone               .put(MD.RC, COMMON_ORE).visDefault();
			Creosote                .put(MD.RC);
			TinAlloy                .put(MD.RC);
			Steel                   .put(MD.RC);
			CoalCoke                .put(MD.RC);
			
			
			Constantan              .put(MD.IE);
			
			
			Ni                      .put(MD.TE, COMMON_ORE);
			Pt                      .put(MD.TE, COMMON_ORE);
			Invar                   .put(MD.TE);
			Electrum                .put(MD.TE);
			Enderium                .put(MD.TE);
			Signalum                .put(MD.TE);
			Lumium                  .put(MD.TE);
			RareEarth               .put(MD.TE, COMMON_ORE);
			Niter                   .put(MD.TE, COMMON_ORE);
			Basalz                  .put(MD.TE);
			Blitz                   .put(MD.TE);
			Blizz                   .put(MD.TE);
			Petrotheum              .put(MD.TE);
			Aerotheum               .put(MD.TE);
			Pyrotheum               .put(MD.TE);
			Cryotheum               .put(MD.TE);
			
			
			CertusQuartz            .put(MD.AE, COMMON_ORE);
			ChargedCertusQuartz     .put(MD.AE, COMMON_ORE);
			Fluix                   .put(MD.AE, COMMON_ORE);
			
			
			IronCompressed          .put(MD.PnC).visDefault();
			
			
			Al                      .put(MD.TiC);
			Co                      .put(MD.TiC, COMMON_ORE);
			Ardite                  .put(MD.TiC, COMMON_ORE).visDefault();
			Alumite                 .put(MD.TiC);
			Manyullyn               .put(MD.TiC).visDefault(Ardite);
			AluminiumBrass          .put(MD.TiC);
			
			
			BlackQuartz             .put(MD.AA).visDefault();
			
			
			EnderiumBase            .put(MD.EIO);
			
			
			TungstenCarbide         .put(MD.ReC);
			
			
			AgI                     .put(MD.RoC);
			InductiveAlloy          .put(MD.RoC);
			Prismane                .put(MD.RoC);
			Lonsdaleite             .put(MD.RoC);
			Cd_In_Ag_Alloy          .put(MD.RoC);
			HSLA                    .put(MD.RoC).visDefault();
			SpringSteel             .put(MD.RoC).visDefault();
			AluminiumAlloy          .put(MD.RoC).visDefault();
			TungstenSintered        .put(MD.RoC).visDefault();
			Bedrock_HSLA_Alloy      .put(MD.RoC).visDefault(HSLA);
			
			
			RefinedGlowstone        .put(MD.Mek).visDefault();
			RefinedObsidian         .put(MD.Mek).visDefault();
			FakeOsmium              .put(MD.Mek, COMMON_ORE).visDefault();
			

			InfusedVis              .put(MD.TC).visDefault();
			Silverwood              .put(MD.TC).visDefault();
			Greatwood               .put(MD.TC).visDefault();
			Thaumium                .put(MD.TC).visDefault();
			VoidMetal               .put(MD.TC).visDefault();
			Tallow                  .put(MD.TC).visDefault();
			Amber                   .put(MD.TC, COMMON_ORE);
			Hg                      .put(MD.TC, COMMON_ORE);
			
			
			DarkThaumium            .put(MD.TCFM).visDefault();
			
			
			Livingwood              .put(MD.BOTA).visDefault();
			Livingrock              .put(MD.BOTA).visDefault();
			Dreamwood               .put(MD.BOTA).visDefault();
			Shimmerwood             .put(MD.BOTA).visDefault();
			Manasteel               .put(MD.BOTA).visDefault();
			ManaDiamond             .put(MD.BOTA).visDefault();
			ElvenElementium         .put(MD.BOTA).visDefault();
			ElvenDragonstone        .put(MD.BOTA).visDefault();
			GaiaSpirit              .put(MD.BOTA).visDefault();
			Terrasteel              .put(MD.BOTA).visDefault();
			SunnyQuartz             .put(MD.BOTA).visDefault();
			LavenderQuartz          .put(MD.BOTA).visDefault();
			RedQuartz               .put(MD.BOTA).visDefault();
			BlazeQuartz             .put(MD.BOTA).visDefault();
			SmokeyQuartz            .put(MD.BOTA).visDefault();
			ManaQuartz              .put(MD.BOTA).visDefault();
			ElvenQuartz             .put(MD.BOTA).visDefault();
			
			
			Mauftrium               .put(MD.ALF).visDefault();
			Elvorium                .put(MD.ALF).visDefault();
			MuspelheimPower         .put(MD.ALF).visDefault();
			NiflheimPower           .put(MD.ALF).visDefault();
			Iffesal                 .put(MD.ALF).visDefault();
			
			
			Iritanium               .put(MD.GC_ADV_ROCKETRY);
			TitaniumAluminide       .put(MD.GC_ADV_ROCKETRY);
			
			
			Anthracite              .put(MD.RoC, COMMON_ORE).visDefault();
			Prismane                .put(MD.RoC).visDefault();
			Lonsdaleite             .put(MD.RoC).visDefault();
			
			
			Endium                  .put(MD.GaEn, COMMON_ORE);
			
			
			FishCooked              .put(MD.MaCu);
			FishRaw                 .put(MD.MaCu);
			FishRotten              .put(MD.MaCu);
			TiO2                    .put(MD.MaCu, COMMON_ORE);
			Ti                      .put(MD.MaCu).visDefault();
			
			
			An                      .put(MD.ABYSSAL, COMMON_ORE).visDefault();
			Cor                     .put(MD.ABYSSAL, COMMON_ORE).visDefault();
			Dr                      .put(MD.ABYSSAL, COMMON_ORE).visDefault();
			Etx                     .put(MD.ABYSSAL, COMMON_ORE).visDefault();
			
			
			AmberDominican          .put(MD.Fossil, COMMON_ORE).visDefault();
			
			
			Draconium               .put(MD.DE, COMMON_ORE).visDefault();
			DraconiumAwakened       .put(MD.DE).visDefault();
			
			
			CosmicNeutronium        .put(MD.AV).visDefault();
			Infinity                .put(MD.AV).visDefault();
			
			
			DarkMatter              .put(MD.PE).visDefault();
			RedMatter               .put(MD.PE).visDefault();
			
			
			Topaz                   .put(MD.BoP, COMMON_ORE);
			Olivine                 .put(MD.BoP, COMMON_ORE);
			Amethyst                .put(MD.BoP, COMMON_ORE);
			EnderAmethyst           .put(MD.BoP, COMMON_ORE).visDefault();
			
			
			Meteorite               .put(MD.FM, COMMON_ORE).visDefault();
			FrozenIron              .put(MD.FM).visDefault();
			Kreknorite              .put(MD.FM).visDefault();
			RedMeteor               .put(MD.FM).visDefault();
			Frezarite               .put(MD.FM).visDefault();
			
			
			Vinteum                 .put(MD.ARS, COMMON_ORE).visDefault();
			VinteumPurified         .put(MD.ARS).visDefault();
			ArcaneAsh               .put(MD.ARS).visDefault();
			ArcaneCompound          .put(MD.ARS).visDefault();
			Moonstone               .put(MD.ARS, COMMON_ORE).visDefault();
			Sunstone                .put(MD.ARS, COMMON_ORE).visDefault();
			Chimerite               .put(MD.ARS, COMMON_ORE).visDefault();
			BlueTopaz               .put(MD.ARS, COMMON_ORE);
			
			
			Desh                    .put(MD.GC, COMMON_ORE).visDefault();
			MoonTurf                .put(MD.GC).visDefault();
			MoonRock                .put(MD.GC).visDefault();
			MarsSand                .put(MD.GC).visDefault();
			MarsRock                .put(MD.GC).visDefault();
			SpaceRock               .put(MD.GC).visDefault();
			
			
			Duralumin               .put(MD.GC_GALAXYSPACE, COMMON_ORE);
			Oriharukon              .put(MD.GC_GALAXYSPACE, COMMON_ORE).visDefault();
			Adamantite              .put(MD.GC_GALAXYSPACE, COMMON_ORE).visDefault();
			GlowstoneCeres          .put(MD.GC_GALAXYSPACE).visDefault();
			GlowstoneIo             .put(MD.GC_GALAXYSPACE).visDefault();
			GlowstoneEnceladus      .put(MD.GC_GALAXYSPACE).visDefault();
			GlowstoneProteus        .put(MD.GC_GALAXYSPACE).visDefault();
			GlowstonePluto          .put(MD.GC_GALAXYSPACE).visDefault();
			
			
			SpectreIron             .put(MD.RT);
			Ectoplasm               .put(MD.RT);
			
			
			Bedrockium              .put(MD.ExU).visDefault();
			
			
			CrimsonMiddle           .put(MD.BTL, BETWEENLANDS).visDefault();
			GreenMiddle             .put(MD.BTL, BETWEENLANDS).visDefault();
			AquaMiddle              .put(MD.BTL, BETWEENLANDS).visDefault();
			Valonite                .put(MD.BTL, BETWEENLANDS).visDefault();
			Scabyst                 .put(MD.BTL, BETWEENLANDS).visDefault();
			SlimyBone               .put(MD.BTL, BETWEENLANDS).visDefault();
			Betweenstone            .put(MD.BTL, BETWEENLANDS).visDefault();
			Pitstone                .put(MD.BTL, BETWEENLANDS).visDefault();
			Weedwood                .put(MD.BTL, BETWEENLANDS).visDefault();
			Syrmorite               .put(MD.BTL, BETWEENLANDS, COMMON_ORE).visDefault();
			Octine                  .put(MD.BTL, BETWEENLANDS, COMMON_ORE).visDefault();
			
			
			Skyroot                 .put(MD.AETHER).visDefault();
			Holystone               .put(MD.AETHER).visDefault();
			Zanite                  .put(MD.AETHER, COMMON_ORE).visDefault();
			Ambrosium               .put(MD.AETHER, COMMON_ORE).visDefault();
			Gravitite               .put(MD.AETHER, COMMON_ORE).visDefault();
			Continuum               .put(MD.AETHER, COMMON_ORE).visDefault();
			
			
			W                       .put(MD.RP);
			Ag                      .put(MD.RP, COMMON_ORE);
			Indigo                  .put(MD.RP);
			Sapphire                .put(MD.RP);
			GreenSapphire           .put(MD.RP);
			BlueSapphire            .put(MD.RP);
			Ruby                    .put(MD.RP);
			BalasRuby               .put(MD.RP);
			Marble                  .put(MD.RP);
			Brass                   .put(MD.RP);
			RedAlloy                .put(MD.RP);
			Nikolite                .put(MD.RP, COMMON_ORE).visDefault();
			NikolineAlloy           .put(MD.RP).visDefault(Nikolite);
			BlueAlloy               .put(MD.RP).visDefault(Nikolite, Teslatite);
			
			
			Electrotine             .put(MD.PR, COMMON_ORE).visDefault();
			ElectrotineAlloy        .put(MD.PR).visDefault(Electrotine);
			
			
			Teslatite               .put(MD.BP, COMMON_ORE).visDefault();
			TeslatineAlloy          .put(MD.BP).visDefault(Teslatite);
			PurpleAlloy             .put(MD.BP).visDefault(Teslatite, Nikolite);
			
			
			Pb                      .put(MD.FZ, COMMON_ORE);
			OREMATS.Galena          .put(MD.FZ, COMMON_ORE);
			H2SO4                   .put(MD.FZ);
			AquaRegia               .put(MD.FZ);
			DarkIron                .put(MD.FZ).visDefault();
			
			
			Yellorium               .put(MD.BR, COMMON_ORE).visDefault();
			Blutonium               .put(MD.BR, COMMON_ORE).visDefault();
			Cyanite                 .put(MD.BR, COMMON_ORE).visDefault();
			Ludicrite               .put(MD.BR, COMMON_ORE).visDefault();
			Yellorite               .put(MD.BR, COMMON_ORE).visDefault();
			
			
			Angmallen               .put(MD.MET);
			Hepatizon               .put(MD.MET);
			Alduorite               .put(MD.MET).visDefault();
			Infuscolium             .put(MD.MET).visDefault();
			Rubracium               .put(MD.MET).visDefault();
			Meutoite                .put(MD.MET).visDefault();
			Lemurite                .put(MD.MET).visDefault();
			Ceruclase               .put(MD.MET).visDefault();
			Atl                     .put(MD.MET);
			Oureclase               .put(MD.MET).visDefault();
			Kalendrite              .put(MD.MET).visDefault();
			Orichalcum              .put(MD.MET).visDefault();
			Carmot                  .put(MD.MET).visDefault();
			Sanguinite              .put(MD.MET).visDefault();
			Vyroxeres               .put(MD.MET).visDefault();
			Eximite                 .put(MD.MET).visDefault();
			Prometheum              .put(MD.MET).visDefault();
			Ignatius                .put(MD.MET).visDefault();
			Vulcanite               .put(MD.MET).visDefault();
			DeepIron                .put(MD.MET).visDefault();
			ShadowIron              .put(MD.MET).visDefault();
			Adamantine              .put(MD.MET);
			AstralSilver            .put(MD.MET);
			Midasium                .put(MD.MET);
			Mithril                 .put(MD.MET);
			ShadowSteel             .put(MD.MET).visDefault();
			Inolashite              .put(MD.MET).visDefault();
			Haderoth                .put(MD.MET).visDefault();
			Celenegil               .put(MD.MET).visDefault();
			Desichalkos             .put(MD.MET).visDefault();
			Tartarite               .put(MD.MET);
			Amordrine               .put(MD.MET).visDefault();
			
			
			Force                   .put(COMMON_ORE).visDefault();
			Forcicium               .put(COMMON_ORE).visDefault();
			Forcillium              .put(COMMON_ORE).visDefault();
			
			
			Plastic                 .addEnchantmentForTools(Enchantment.knockback, 1);
			Rubber                  .addEnchantmentForTools(Enchantment.knockback, 2);
			Kalendrite              .addEnchantmentForTools(Enchantment.knockback, 2);
			InfusedAir              .addEnchantmentForTools(Enchantment.knockback, 2);
			Gravitite               .addEnchantmentForTools(Enchantment.knockback, 3);
			DarkIron                .addEnchantmentForTools(Enchantment.knockback, 3);
			Tartarite               .addEnchantmentForTools(Enchantment.knockback, 3);
			DarkMatter              .addEnchantmentForTools(Enchantment.knockback, 3);
			RedMeteor               .addEnchantmentForTools(Enchantment.knockback, 3);
			Infinity                .addEnchantmentForTools(Enchantment.knockback,10);
			
			Skyroot                 .addEnchantmentForTools(Enchantment.fortune, 1);
			IronWood                .addEnchantmentForTools(Enchantment.fortune, 1);
			Steeleaf                .addEnchantmentForTools(Enchantment.fortune, 2);
			Soularium               .addEnchantmentForTools(Enchantment.fortune, 2);
			Midasium                .addEnchantmentForTools(Enchantment.fortune, 2);
			Mithril                 .addEnchantmentForTools(Enchantment.fortune, 3);
			ElvenQuartz             .addEnchantmentForTools(Enchantment.fortune, 3);
			Vinteum                 .addEnchantmentForTools(Enchantment.fortune, 1);
			Thaumium                .addEnchantmentForTools(Enchantment.fortune, 2);
			DarkThaumium            .addEnchantmentForTools(Enchantment.fortune, 3);
			InfusedWater            .addEnchantmentForTools(Enchantment.fortune, 3);
			Eximite                 .addEnchantmentForTools(Enchantment.fortune, 3);
			DarkMatter              .addEnchantmentForTools(Enchantment.fortune, 3);
			RedMatter               .addEnchantmentForTools(Enchantment.fortune, 3);
			Jade                    .addEnchantmentForTools(Enchantment.fortune, 3);
			Continuum               .addEnchantmentForTools(Enchantment.fortune, 3);
			Ma                      .addEnchantmentForTools(Enchantment.fortune, 4);
			Haderoth                .addEnchantmentForTools(Enchantment.fortune, 4);
			VibraniumSteel          .addEnchantmentForTools(Enchantment.fortune, 4);
			Vb                      .addEnchantmentForTools(Enchantment.fortune, 5);
			Infinity                .addEnchantmentForTools(Enchantment.fortune,10);
			
			Flint                   .addEnchantmentForTools(Enchantment.fireAspect, 1);
			Netherrack              .addEnchantmentForTools(Enchantment.fireAspect, 1);
			Obsidian                .addEnchantmentForTools(Enchantment.fireAspect, 1);
			Gneiss                  .addEnchantmentForTools(Enchantment.fireAspect, 2);
			NetherBrick             .addEnchantmentForTools(Enchantment.fireAspect, 2);
			ObsidianSteel           .addEnchantmentForTools(Enchantment.fireAspect, 2);
			Ignatius                .addEnchantmentForTools(Enchantment.fireAspect, 2);
			Prometheum              .addEnchantmentForTools(Enchantment.fireAspect, 2);
			Octine                  .addEnchantmentForTools(Enchantment.fireAspect, 3);
			Kreknorite              .addEnchantmentForTools(Enchantment.fireAspect, 3);
			Firestone               .addEnchantmentForTools(Enchantment.fireAspect, 3);
			FierySteel              .addEnchantmentForTools(Enchantment.fireAspect, 3);
			Pyrotheum               .addEnchantmentForTools(Enchantment.fireAspect, 3);
			Blaze                   .addEnchantmentForTools(Enchantment.fireAspect, 3);
			InfusedFire             .addEnchantmentForTools(Enchantment.fireAspect, 3);
			Vulcanite               .addEnchantmentForTools(Enchantment.fireAspect, 3);
			Amordrine               .addEnchantmentForTools(Enchantment.fireAspect, 3);
			MuspelheimPower         .addEnchantmentForTools(Enchantment.fireAspect, 3);
			RedMatter               .addEnchantmentForTools(Enchantment.fireAspect, 3);
			Netherite               .addEnchantmentForTools(Enchantment.fireAspect, 3);
			NetherizedDiamond       .addEnchantmentForTools(Enchantment.fireAspect, 3);
			Infinity                .addEnchantmentForTools(Enchantment.fireAspect,10);
			
			Ad                      .addEnchantmentForTools(Enchantment.silkTouch, 1);
			Adamantine              .addEnchantmentForTools(Enchantment.silkTouch, 1);
			Force                   .addEnchantmentForTools(Enchantment.silkTouch, 1);
			Amber                   .addEnchantmentForTools(Enchantment.silkTouch, 1);
			AmberDominican          .addEnchantmentForTools(Enchantment.silkTouch, 1);
			Ambrosium               .addEnchantmentForTools(Enchantment.silkTouch, 1);
			ManaQuartz              .addEnchantmentForTools(Enchantment.silkTouch, 1);
			EnderPearl              .addEnchantmentForTools(Enchantment.silkTouch, 1);
			Enderium                .addEnchantmentForTools(Enchantment.silkTouch, 1);
			Endium                  .addEnchantmentForTools(Enchantment.silkTouch, 1);
			Frezarite               .addEnchantmentForTools(Enchantment.silkTouch, 1);
			Inolashite              .addEnchantmentForTools(Enchantment.silkTouch, 1);
			Sanguinite              .addEnchantmentForTools(Enchantment.silkTouch, 1);
			SpectreIron             .addEnchantmentForTools(Enchantment.silkTouch, 1);
			NetherStar              .addEnchantmentForTools(Enchantment.silkTouch, 1);
			InfusedOrder            .addEnchantmentForTools(Enchantment.silkTouch, 1);
			InfusedBalance          .addEnchantmentForTools(Enchantment.silkTouch, 1);
			NiflheimPower           .addEnchantmentForTools(Enchantment.silkTouch, 1);
			Vibramantium            .addEnchantmentForTools(Enchantment.silkTouch, 1);
			Infinity                .addEnchantmentForTools(Enchantment.silkTouch,10);
			
			Hepatizon               .addEnchantmentForTools(Enchantment.smite, 2);
			BlackBronze             .addEnchantmentForTools(Enchantment.smite, 2);
			BlueSteel               .addEnchantmentForTools(Enchantment.smite, 3);
			Au                      .addEnchantmentForTools(Enchantment.smite, 3);
			TitaniumGold            .addEnchantmentForTools(Enchantment.smite, 3);
			Electrum                .addEnchantmentForTools(Enchantment.smite, 3);
			GildedIron              .addEnchantmentForTools(Enchantment.smite, 3);
			Holystone               .addEnchantmentForTools(Enchantment.smite, 3);
			RoseGold                .addEnchantmentForTools(Enchantment.smite, 4);
			EnergeticAlloy          .addEnchantmentForTools(Enchantment.smite, 4);
			SpectreIron             .addEnchantmentForTools(Enchantment.smite, 5);
			VibrantAlloy            .addEnchantmentForTools(Enchantment.smite, 5);
			Mauftrium               .addEnchantmentForTools(Enchantment.smite, 5);
			Carmot                  .addEnchantmentForTools(Enchantment.smite, 5);
			Pt                      .addEnchantmentForTools(Enchantment.smite, 5);
			Mithril                 .addEnchantmentForTools(Enchantment.smite, 5);
			InfusedVis              .addEnchantmentForTools(Enchantment.smite, 5);
			Infinity                .addEnchantmentForTools(Enchantment.smite,10);
			
			Pb                      .addEnchantmentForTools(Enchantment.baneOfArthropods, 2);
			Ni                      .addEnchantmentForTools(Enchantment.baneOfArthropods, 2);
			Constantan              .addEnchantmentForTools(Enchantment.baneOfArthropods, 2);
			Nichrome                .addEnchantmentForTools(Enchantment.baneOfArthropods, 2);
			Invar                   .addEnchantmentForTools(Enchantment.baneOfArthropods, 3);
			Sb                      .addEnchantmentForTools(Enchantment.baneOfArthropods, 3);
			BatteryAlloy            .addEnchantmentForTools(Enchantment.baneOfArthropods, 4);
			Bi                      .addEnchantmentForTools(Enchantment.baneOfArthropods, 4);
			Orichalcum              .addEnchantmentForTools(Enchantment.baneOfArthropods, 4);
			BismuthBronze           .addEnchantmentForTools(Enchantment.baneOfArthropods, 4);
			InfusedEarth            .addEnchantmentForTools(Enchantment.baneOfArthropods, 5);
			Celenegil               .addEnchantmentForTools(Enchantment.baneOfArthropods, 5);
			Infinity                .addEnchantmentForTools(Enchantment.baneOfArthropods,10);
			
			
			Fe                      .addEnchantmentForTools(Enchantment.sharpness, 1);
			IronMagnetic            .addEnchantmentForTools(Enchantment.sharpness, 1);
			Ice                     .addEnchantmentForTools(Enchantment.sharpness, 1);
			Glass                   .addEnchantmentForTools(Enchantment.sharpness, 1);
			Bronze                  .addEnchantmentForTools(Enchantment.sharpness, 1);
			GildedIron              .addEnchantmentForTools(Enchantment.sharpness, 1);
			PulsatingIron           .addEnchantmentForTools(Enchantment.sharpness, 1);
			ConductiveIron          .addEnchantmentForTools(Enchantment.sharpness, 1);
			RedstoneAlloy           .addEnchantmentForTools(Enchantment.sharpness, 1);
			ElectricalSteel         .addEnchantmentForTools(Enchantment.sharpness, 2);
			Brass                   .addEnchantmentForTools(Enchantment.sharpness, 2);
			CobaltBrass             .addEnchantmentForTools(Enchantment.sharpness, 2);
			HSLA                    .addEnchantmentForTools(Enchantment.sharpness, 2);
			Steel                   .addEnchantmentForTools(Enchantment.sharpness, 2);
			SteelMagnetic           .addEnchantmentForTools(Enchantment.sharpness, 2);
			SteelGalvanized         .addEnchantmentForTools(Enchantment.sharpness, 2);
			Syrmorite               .addEnchantmentForTools(Enchantment.sharpness, 2);
			WroughtIron             .addEnchantmentForTools(Enchantment.sharpness, 2);
			PigIron                 .addEnchantmentForTools(Enchantment.sharpness, 2);
			DeepIron                .addEnchantmentForTools(Enchantment.sharpness, 2);
			Meteorite               .addEnchantmentForTools(Enchantment.sharpness, 2);
			FierySteel              .addEnchantmentForTools(Enchantment.sharpness, 2);
			FrozenIron              .addEnchantmentForTools(Enchantment.sharpness, 2);
			MeteoricIron            .addEnchantmentForTools(Enchantment.sharpness, 2);
			MeteoricSteel           .addEnchantmentForTools(Enchantment.sharpness, 3);
			VanadiumSteel           .addEnchantmentForTools(Enchantment.sharpness, 3);
			StainlessSteel          .addEnchantmentForTools(Enchantment.sharpness, 3);
			Knightmetal             .addEnchantmentForTools(Enchantment.sharpness, 3);
			ShadowIron              .addEnchantmentForTools(Enchantment.sharpness, 3);
			BlackSteel              .addEnchantmentForTools(Enchantment.sharpness, 3);
			RedSteel                .addEnchantmentForTools(Enchantment.sharpness, 3);
			BlueSteel               .addEnchantmentForTools(Enchantment.sharpness, 3);
			Ti                      .addEnchantmentForTools(Enchantment.sharpness, 3);
			TitaniumGold            .addEnchantmentForTools(Enchantment.sharpness, 3);
			TungstenSteel           .addEnchantmentForTools(Enchantment.sharpness, 3);
			NetherizedDiamond       .addEnchantmentForTools(Enchantment.sharpness, 4);
			HSSG                    .addEnchantmentForTools(Enchantment.sharpness, 4);
			HSSE                    .addEnchantmentForTools(Enchantment.sharpness, 4);
			HSSS                    .addEnchantmentForTools(Enchantment.sharpness, 4);
			ShadowSteel             .addEnchantmentForTools(Enchantment.sharpness, 4);
			Zanite                  .addEnchantmentForTools(Enchantment.sharpness, 4);
			DamascusSteel           .addEnchantmentForTools(Enchantment.sharpness, 5);
			Elvorium                .addEnchantmentForTools(Enchantment.sharpness, 5);
			InfusedEntropy          .addEnchantmentForTools(Enchantment.sharpness, 5);
			Ke                      .addEnchantmentForTools(Enchantment.sharpness, 6);
			Trinitanium             .addEnchantmentForTools(Enchantment.sharpness, 7);
			Trinaquadalloy          .addEnchantmentForTools(Enchantment.sharpness, 8);
			Infinity                .addEnchantmentForTools(Enchantment.sharpness,10);
			
			Oureclase               .addEnchantmentForArmors(Enchantment.respiration, 3);
			InfusedAir              .addEnchantmentForArmors(Enchantment.respiration, 3);
			Infinity                .addEnchantmentForArmors(Enchantment.respiration,10);
			
			Atl                     .addEnchantmentForArmors(Enchantment.featherFalling, 4);
			InfusedFire             .addEnchantmentForArmors(Enchantment.featherFalling, 4);
			Infinity                .addEnchantmentForArmors(Enchantment.featherFalling,10);
			
			Steeleaf                .addEnchantmentForArmors(Enchantment.protection, 2);
			Knightmetal             .addEnchantmentForArmors(Enchantment.protection, 1);
			Celenegil               .addEnchantmentForArmors(Enchantment.protection, 4);
			InfusedEarth            .addEnchantmentForArmors(Enchantment.protection, 4);
			InfusedVis              .addEnchantmentForArmors(Enchantment.protection, 4);
			InfusedBalance          .addEnchantmentForArmors(Enchantment.protection, 4);
			Tartarite               .addEnchantmentForArmors(Enchantment.protection, 5);
			Adamantine              .addEnchantmentForArmors(Enchantment.protection, 5);
			DarkMatter              .addEnchantmentForArmors(Enchantment.protection, 5);
			VibraniumSilver         .addEnchantmentForArmors(Enchantment.protection, 6);
			VibraniumSteel          .addEnchantmentForArmors(Enchantment.protection, 8);
			Vibramantium            .addEnchantmentForArmors(Enchantment.protection,10);
			Vb                      .addEnchantmentForArmors(Enchantment.protection,10);
			Ad                      .addEnchantmentForArmors(Enchantment.protection,10);
			Infinity                .addEnchantmentForArmors(Enchantment.protection,10);
			
			Netherite               .addEnchantmentForArmors(Enchantment.fireProtection, 3);
			NetherizedDiamond       .addEnchantmentForArmors(Enchantment.fireProtection, 5);
			
			Vyroxeres               .addEnchantmentForArmors(Enchantment.thorns, 3);
			InfusedEntropy          .addEnchantmentForArmors(Enchantment.thorns, 3);
			Infinity                .addEnchantmentForArmors(Enchantment.thorns,10);
			
			InfusedWater            .addEnchantmentForArmors(Enchantment.aquaAffinity, 1);
			IronWood                .addEnchantmentForArmors(Enchantment.aquaAffinity, 1);
			Ceruclase               .addEnchantmentForArmors(Enchantment.aquaAffinity, 1);
			Inolashite              .addEnchantmentForArmors(Enchantment.aquaAffinity, 1);
			Infinity                .addEnchantmentForArmors(Enchantment.aquaAffinity,10);
			
			InfusedOrder            .addEnchantmentForArmors(Enchantment.projectileProtection, 4);
			Infinity                .addEnchantmentForArmors(Enchantment.projectileProtection,10);
			
			Obsidian                .addEnchantmentForArmors(Enchantment.blastProtection, 3);
			InfusedDull             .addEnchantmentForArmors(Enchantment.blastProtection, 4);
			Amordrine               .addEnchantmentForArmors(Enchantment.blastProtection, 5);
			RedMatter               .addEnchantmentForArmors(Enchantment.blastProtection, 5);
			Infinity                .addEnchantmentForArmors(Enchantment.blastProtection,10);
			
			/*
			FryingOilHot            .setHeatDamage(1.0);
			Lava                    .setHeatDamage(3.0);
			Firestone               .setHeatDamage(5.0);
			Pyrotheum               .setHeatDamage(5.0);
			*/
			
			Yellorite                       .addOreByProducts(Pb                    , Cyanite                   , Yellorium             , Ra                    , RareEarth             );
			Yellorium                       .addOreByProducts(Pb                    , Cyanite                   , Blutonium             , Am                    );
			Blutonium                       .addOreByProducts(Pb                    , Yellorium                 , Am                    );
			Ludicrite                       .addOreByProducts(Pb                    , Blutonium                 , Yellorium             );
			
			OREMATS.Pitchblende             .addOreByProducts(Pb                    , Th                        , U_238                 , Ra                    , RareEarth             );
			OREMATS.Uraninite               .addOreByProducts(Pb                    , Th                        , U_238                 , Ra                    , RareEarth             );
			U_238                           .addOreByProducts(Pb                    , Th                        , Pu                    , Am                    );
			Am                              .addOreByProducts(Pb                    , U_238                     , Pu                    );
			Pu                              .addOreByProducts(Pb                    , U_238                     , Am                    );
			Th                              .addOreByProducts(Pb                    , U_238                     );
			
			for (OreDictMaterial tMat : ANY.CaF2.mToThis)
			tMat                            .addOreByProducts(OREMATS.Huebnerite    , Y                         , Ce                    , Fe2O3                 , Na                    , Ba                    );
			
			S                               .addOreByProducts(Pyrite                , OREMATS.Sphalerite        , OREMATS.Cinnabar      , MT.OREMATS.Chalcopyrite, MT.OREMATS.Arsenopyrite, OREMATS.Galena      , OREMATS.Stibnite      , OREMATS.Gypsum);
			Se                              .addOreByProducts(Pyrite                , OREMATS.Galena            , OREMATS.Sphalerite    , In                    , Ga                    , Cd                    );
			OREMATS.Chalcopyrite            .addOreByProducts(Pyrite                , Co                        , Cd                    , Au                    , OREMATS.Sperrylite    , OREMATS.Stannite      , In                    );
			OREMATS.Sperrylite              .addOreByProducts(Sb                    , Cu                        , Fe2O3                 , Rh                    , Pt                    );
			OREMATS.Pentlandite             .addOreByProducts(Fe2O3                 , S                         , Co                    , OREMATS.Sperrylite    );
			OREMATS.Sphalerite              .addOreByProducts(Cd                    , Ga                        , Zn                    , OREMATS.Kesterite     , Se                    , In                    );
			OREMATS.Tetrahedrite            .addOreByProducts(Cu                    , Sb                        , Zn                    , OREMATS.Kesterite     );
			Pyrite                          .addOreByProducts(S                     , Phosphorus                , Fe2O3                 , OREMATS.Stannite      , Se                    );
			Sn                              .addOreByProducts(OREMATS.Molybdenite   , OREMATS.Wolframite        , CaF2                  , OREMATS.Arsenopyrite  , OREMATS.Stannite      , OREMATS.Sperrylite    , OREMATS.Huebnerite    , Apatite); // Tourmaline
			OREMATS.Cassiterite             .addOreByProducts(OREMATS.Molybdenite   , OREMATS.Wolframite        , CaF2                  , OREMATS.Arsenopyrite  , OREMATS.Stannite      , OREMATS.Sperrylite    , OREMATS.Huebnerite    , Apatite); // Tourmaline
			OREMATS.Huebnerite              .addOreByProducts(OREMATS.Molybdenite   , OREMATS.Wolframite        , CaF2                  , OREMATS.Arsenopyrite  , OREMATS.Cassiterite   , Topaz                 ); // Tourmaline, Rhodochrosite
			OREMATS.Bauxite                 .addOreByProducts(OREMATS.Kaolinite     , OREMATS.Ilmenite          , Fe2O3                 , Al2O3                 , AlO3H3                );
			AlO3H3                          .addOreByProducts(OREMATS.Bauxite       , OREMATS.Ilmenite          , Fe2O3                 , Al2O3                 );
			OREMATS.Ilmenite                .addOreByProducts(TiO2                  , Fe2O3                     , Mg                    , MnO2                  );
			TiO2                            .addOreByProducts(Fe2O3                 , Nb                        , OREMATS.Tantalite     , Zircon                );
			OREMATS.Arsenopyrite            .addOreByProducts(Au                    , CaF2                      , OREMATS.Cassiterite   , OREMATS.Huebnerite    );
			Ti                              .addOreByProducts(Fe2O3                 , Nb                        , OREMATS.Tantalite     , Zircon                );
			Fe2O3                           .addOreByProducts(OREMATS.Ilmenite      , OREMATS.Magnetite         , MnO2                  , ClayBrown             );
			OREMATS.Zeolite                 .addOreByProducts(Na                    , K                         , Ca                    , Mg                    );
			OREMATS.YellowLimonite          .addOreByProducts(Ni                    , OREMATS.BrownLimonite     , Co                    );
			Cu                              .addOreByProducts(Co                    , Au                        , Ni                    );
			Ni                              .addOreByProducts(Co                    , Pt                        , Fe2O3                 );
			OREMATS.Stannite                .addOreByProducts(Ge                    , Pyrite                    , OREMATS.Kesterite     );
			OREMATS.Kesterite               .addOreByProducts(Ge                    , Pyrite                    , OREMATS.Stannite      );
			OREMATS.GlauconiteSand          .addOreByProducts(Na                    , Al2O3                     , Fe2O3                 );
			OREMATS.Glauconite              .addOreByProducts(Na                    , Al2O3                     , Fe2O3                 );
			OREMATS.Vermiculite             .addOreByProducts(Fe2O3                 , Al2O3                     , Mg                    );
			OREMATS.FullersEarth            .addOreByProducts(Al2O3                 , SiO2                      , Mg                    );
			OREMATS.Bentonite               .addOreByProducts(Al2O3                 , Ca                        , Mg                    );
			OREMATS.Galena                  .addOreByProducts(S                     , Ag                        , Pb                    , Se                    );
			Lapis                           .addOreByProducts(Lazurite              , Sodalite                  , Pyrite                );
			OREMATS.Cooperite               .addOreByProducts(Pd                    , Ni                        , Ir                    );
			OREMATS.Cinnabar                .addOreByProducts(Redstone              , S                         , Glowstone             , Se                    );
			OREMATS.Tantalite               .addOreByProducts(MnO2                  , Nb                        , Ta                    );
			Mn                              .addOreByProducts(MnO2                  , Fe2O3                     , OREMATS.Tantalite     , OREMATS.Chromite      );
			MnO2                            .addOreByProducts(OREMATS.Bromargyrite  , Fe2O3                     , OREMATS.Tantalite     , OREMATS.Chromite      );
			OREMATS.Chromite                .addOreByProducts(MnO2                  , Fe2O3                     , Mg                    , OREMATS.Bromargyrite  );
			OREMATS.Bromargyrite            .addOreByProducts(MnO2                  , Ag                        , OREMATS.Chromite      , OREMATS.Smithsonite   );
			OREMATS.Pollucite               .addOreByProducts(Cs                    , Al2O3                     , Rb                    );
			Asbestos                        .addOreByProducts(Asbestos              , SiO2                      , Mg                    );
			Phosphorus                      .addOreByProducts(Phosphorite           , Apatite                   , CaF2                  , PO4                   );
			Apatite                         .addOreByProducts(Phosphorite           , Phosphorus                , CaF2                  , PO4                   );
			Sapphire                        .addOreByProducts(OrangeSapphire        , YellowSapphire            , GreenSapphire         );
			BlueSapphire                    .addOreByProducts(OrangeSapphire        , YellowSapphire            , GreenSapphire         );
			GreenSapphire                   .addOreByProducts(BlueSapphire          , OrangeSapphire            , YellowSapphire        );
			YellowSapphire                  .addOreByProducts(BlueSapphire          , OrangeSapphire            , GreenSapphire         );
			OrangeSapphire                  .addOreByProducts(BlueSapphire          , YellowSapphire            , GreenSapphire         );
			HexoriumRed                     .addOreByProducts(HexoriumWhite         , HexoriumBlack             );
			HexoriumGreen                   .addOreByProducts(HexoriumWhite         , HexoriumBlack             );
			HexoriumBlue                    .addOreByProducts(HexoriumWhite         , HexoriumBlack             );
			HexoriumBlack                   .addOreByProducts(HexoriumRed           , HexoriumGreen             , HexoriumBlue          );
			HexoriumWhite                   .addOreByProducts(HexoriumRed           , HexoriumGreen             , HexoriumBlue          );
			OREMATS.Kaolinite               .addOreByProducts(OREMATS.Bauxite       , ClayBrown                 , Al2O3                 );
			OREMATS.Barite                  .addOreByProducts(CertusQuartz          , Quartzite                 );
			OREMATS.QuartzSand              .addOreByProducts(CertusQuartz          , Quartzite                 , OREMATS.Barite        );
			OREMATS.Wollastonite            .addOreByProducts(Fe2O3                 , Mg                        , MnO2                  );
			BlackQuartz                     .addOreByProducts(OREMATS.Barite        );
			NetherQuartz                    .addOreByProducts(Netherrack            , OREMATS.Barite            );
			Quartzite                       .addOreByProducts(CertusQuartz          , OREMATS.Barite            , Fe2O3);
			MilkyQuartz                     .addOreByProducts(CertusQuartz          , OREMATS.Barite            );
			CertusQuartz                    .addOreByProducts(MilkyQuartz           , OREMATS.Barite            );
			ChargedCertusQuartz             .addOreByProducts(MilkyQuartz           , OREMATS.Barite            );
			Redstone                        .addOreByProducts(OREMATS.Cinnabar      , RareEarth                 , Glowstone             );
			Os                              .addOreByProducts(Ir                    , Pt                        , Ru                    );
			Ir                              .addOreByProducts(Pt                    , Os                        , Rh                    );
			Pt                              .addOreByProducts(Ni                    , Ir                        , Pd                    );
			MeteoricIron                    .addOreByProducts(Ni                    , Ir                        , Pt                    );
			Au                              .addOreByProducts(Cu                    , Ni                        , OREMATS.Cinnabar      );
			Ag                              .addOreByProducts(Pb                    , S                         , OREMATS.Bromargyrite  );
			Ke                              .addOreByProducts(Sn                    , TiO2                      , Fe2O3                 );
			Monazite                        .addOreByProducts(Th                    , Nd                        , RareEarth             );
			Forcicium                       .addOreByProducts(Th                    , Nd                        , RareEarth             );
			Forcillium                      .addOreByProducts(Th                    , Nd                        , RareEarth             );
			FakeOsmium                      .addOreByProducts(Fe2O3                 , Sn                        , Cr                    );
			Cd                              .addOreByProducts(OREMATS.Chalcopyrite  , OREMATS.Sphalerite        , Se                    );
			OREMATS.Powellite               .addOreByProducts(OREMATS.Molybdenite   , OREMATS.Scheelite         );
			OREMATS.Molybdenite             .addOreByProducts(OREMATS.Powellite     , OREMATS.Scheelite         , Re                    , Os);
			OREMATS.Malachite               .addOreByProducts(Cu                    , OREMATS.BrownLimonite     , CaCO3                 , MT.Azurite);
			OREMATS.Scheelite               .addOreByProducts(MnO2                  , Mo                        , Ca                    );
			OREMATS.Tungstate               .addOreByProducts(MnO2                  , Ag                        , LiCl                  );
			OREMATS.Wolframite              .addOreByProducts(OREMATS.Tungstate     , Fe2O3                     , OREMATS.Stannite      );
			OREMATS.Stolzite                .addOreByProducts(OREMATS.Tungstate     , Pb                        );
			OREMATS.Russellite              .addOreByProducts(OREMATS.Tungstate     , Bi                        );
			OREMATS.Pinalite                .addOreByProducts(OREMATS.Tungstate     , Pb                        );
			OREMATS.BrownLimonite           .addOreByProducts(OREMATS.Malachite     , OREMATS.YellowLimonite    );
			OREMATS.Garnierite              .addOreByProducts(Ni                    , OREMATS.Sperrylite        );
			OREMATS.Bastnasite              .addOreByProducts(Nd                    , RareEarth                 );
			Nd                              .addOreByProducts(Monazite              , RareEarth                 );
			NaCl                            .addOreByProducts(KCl                   , KIO3                      );
			KCl                             .addOreByProducts(KIO3                  , NaCl                      );
			KIO3                            .addOreByProducts(NaCl                  , KCl                       );
			Glowstone                       .addOreByProducts(Redstone              , Au                        );
			GlowstoneCeres                  .addOreByProducts(Redstone              , Au                        , Glowstone             );
			GlowstoneIo                     .addOreByProducts(Redstone              , Au                        , Glowstone             );
			GlowstoneEnceladus              .addOreByProducts(Redstone              , Au                        , Glowstone             );
			GlowstoneProteus                .addOreByProducts(Redstone              , Au                        , Glowstone             );
			GlowstonePluto                  .addOreByProducts(Redstone              , Au                        , Glowstone             );
			Ga                              .addOreByProducts(Zn                    , Se                        );
			Zn                              .addOreByProducts(Sn                    , Ga                        );
			W                               .addOreByProducts(MnO2                  , Mo                        );
			OREMATS.Diatomite               .addOreByProducts(Fe2O3                 , BlueSapphire              );
			Jasper                          .addOreByProducts(BalasRuby             , Spinel                    );
			BalasRuby                       .addOreByProducts(Jasper                , Spinel                    );
			Spinel                          .addOreByProducts(Jasper                , BalasRuby                 );
			Fe                              .addOreByProducts(Ni                    , Sn                        );
			OREMATS.Lepidolite              .addOreByProducts(LiCl                  , Cs                        );
			Sb                              .addOreByProducts(Zn                    , Fe2O3                     );
			OREMATS.Smithsonite             .addOreByProducts(Zn                    , OREMATS.Bromargyrite      );
			Pb                              .addOreByProducts(Ag                    , S                         );
			Electrum                        .addOreByProducts(Au                    , Ag                        );
			Bronze                          .addOreByProducts(Cu                    , Sn                        );
			Brass                           .addOreByProducts(Cu                    , Zn                        );
			Coal                            .addOreByProducts(Lignite               , S                         );
			Lignite                         .addOreByProducts(Coal                  , S                         , Ge                    );
			Emerald                         .addOreByProducts(Be                    , Al2O3                     , Aquamarine            , Morganite             , Goshenite             , Bixbite               , Heliodor              , Maxixe    );
			Aquamarine                      .addOreByProducts(Be                    , Al2O3                     , Emerald               , Morganite             , Goshenite             , Bixbite               , Heliodor              , Maxixe    );
			Morganite                       .addOreByProducts(Be                    , Al2O3                     , Emerald               , Aquamarine            , Goshenite             , Bixbite               , Heliodor              , Maxixe    );
			Goshenite                       .addOreByProducts(Be                    , Al2O3                     , Emerald               , Aquamarine            , Morganite             , Bixbite               , Heliodor              , Maxixe    );
			Bixbite                         .addOreByProducts(Be                    , Al2O3                     , Emerald               , Aquamarine            , Morganite             , Goshenite             , Heliodor              , Maxixe    );
			Heliodor                        .addOreByProducts(Be                    , Al2O3                     , Emerald               , Aquamarine            , Morganite             , Goshenite             , Bixbite               , Maxixe    );
			Maxixe                          .addOreByProducts(Be                    , Al2O3                     , Emerald               , Aquamarine            , Morganite             , Goshenite             , Bixbite               , Heliodor  );
			Be                              .addOreByProducts(Emerald               , Aquamarine                , Morganite             );
			Al                              .addOreByProducts(OREMATS.Bauxite       , Al2O3                     , AlO3H3                );
			Al2O3                           .addOreByProducts(OREMATS.Bauxite       , Al2O3                     , AlO3H3                );
			Cr                              .addOreByProducts(Fe2O3                 , Mg                        );
			OREMATS.Ferrovanadium           .addOreByProducts(OREMATS.Magnetite     , VanadiumPentoxide         );
			OREMATS.Magnetite               .addOreByProducts(Fe2O3                 , Au                        );
			OREMATS.GraniticMineralSand     .addOreByProducts(Granite               , OREMATS.Magnetite         );
			OREMATS.BasalticMineralSand     .addOreByProducts(Basalt                , OREMATS.Magnetite         );
			Basalt                          .addOreByProducts(Olivine               , VolcanicAsh               );
			OREMATS.Celestine               .addOreByProducts(Sr                    , S                         );
			Lazurite                        .addOreByProducts(Sodalite              , Lapis                     );
			Sodalite                        .addOreByProducts(Lazurite              , Lapis                     );
			OREMATS.Spodumene               .addOreByProducts(Al2O3                 , LiCl                      );
			Co_60                           .addOreByProducts(OREMATS.Cobaltite     , Co                        );
			Zr                              .addOreByProducts(TiO2                  , Hf                        );
			Zircon                          .addOreByProducts(TiO2                  , Hf                        , OREMATS.Uraninite     );
			Eudialyte                       .addOreByProducts(Zircon                , RareEarth                 , Hf                    , Ta                    , Nb                    , Pb                    );
			Azurite                         .addOreByProducts(Zircon                , OREMATS.Malachite         , Hf                    ); // TODO: Niccolite
			OREMATS.DiduraniumTrioxide      .addOreByProducts(Dn                    , Tn                        );
			OREMATS.DuraniumHexafluoride    .addOreByProducts(Dn                    );
			OREMATS.DuraniumHexachloride    .addOreByProducts(Dn                    );
			OREMATS.DuraniumHexabromide     .addOreByProducts(Dn                    );
			OREMATS.DuraniumHexaiodide      .addOreByProducts(Dn                    , I                         );
			OREMATS.DuraniumHexaastatide    .addOreByProducts(Dn                    , At                        );
			OREMATS.TritaniumDioxide        .addOreByProducts(Tn                    , Dn                        );
			OREMATS.TritaniumHexafluoride   .addOreByProducts(Tn                    );
			OREMATS.TritaniumHexachloride   .addOreByProducts(Tn                    );
			OREMATS.TritaniumHexabromide    .addOreByProducts(Tn                    );
			OREMATS.TritaniumHexaiodide     .addOreByProducts(Tn                    , I                         );
			OREMATS.TritaniumHexaastatide   .addOreByProducts(Tn                    , At                        );
			Nq_528                          .addOreByProducts(Nq                    , Nq_522                    );
			Nq_522                          .addOreByProducts(Nq                    , Nq_528                    );
			Nq                              .addOreByProducts(Nq_528                );
			Ad                              .addOreByProducts(OREMATS.Magnetite     , Adamantine                );
			Adamantine                      .addOreByProducts(OREMATS.Magnetite     , Ad                        );
			AncientDebris                   .addOreByProducts(SoulSand              , NetherQuartz              );
			SoulSand                        .addOreByProducts(Coal                  , NetherQuartz              , Niter                 );
			OREMATS.Stibnite                .addOreByProducts(Sb                    );
			Diamond                         .addOreByProducts(Graphite              );
			Pyrope                          .addOreByProducts(Mg                    );
			Almandine                       .addOreByProducts(Al2O3                 );
			Spessartine                     .addOreByProducts(MnO2                  );
			Andradite                       .addOreByProducts(Fe2O3                 );
			Grossular                       .addOreByProducts(Ca                    );
			Uvarovite                       .addOreByProducts(Cr                    );
			Ruby                            .addOreByProducts(Cr                    );
			Olivine                         .addOreByProducts(Mg                    );
			Nikolite                        .addOreByProducts(Cu                    );
			Teslatite                       .addOreByProducts(Cu                    );
			Electrotine                     .addOreByProducts(Cu                    );
			OREMATS.Magnesite               .addOreByProducts(Mg                    );
			PigIron                         .addOreByProducts(Fe2O3                 );
			DarkIron                        .addOreByProducts(Fe2O3                 );
			Steel                           .addOreByProducts(Fe2O3                 );
			MeteoricSteel                   .addOreByProducts(Fe2O3                 );
			Graphite                        .addOreByProducts(C                     );
			CaCO3                           .addOreByProducts(OREMATS.Malachite     );
			OREMATS.Gypsum                  .addOreByProducts(S                     );
			OREMATS.Borax                   .addOreByProducts(B                     );
			Netherrack                      .addOreByProducts(S                     );
			Flint                           .addOreByProducts(Obsidian              );
			NaNO3                           .addOreByProducts(KNO3                  , Niter                     );
			KNO3                            .addOreByProducts(NaNO3                 , Niter                     );
			Niter                           .addOreByProducts(KNO3                  , NaNO3                     );
			Endstone                        .addOreByProducts(He_3                  );
			Hf                              .addOreByProducts(Zircon                );
			Mg                              .addOreByProducts(Olivine               );
			OREMATS.Cobaltite               .addOreByProducts(Co                    );
			Co                              .addOreByProducts(OREMATS.Cobaltite     );
			Ardite                          .addOreByProducts(Co                    );
			Obsidian                        .addOreByProducts(Olivine               );
			Redrock                         .addOreByProducts(ClayBrown             );
			Limestone                       .addOreByProducts(CaCO3                 );
			Marble                          .addOreByProducts(CaCO3                 );
			Clay                            .addOreByProducts(ClayBrown             );
			ClayBrown                       .addOreByProducts(Clay                  );
			GraniteBlack                    .addOreByProducts(Biotite               );
			GraniteRed                      .addOreByProducts(PotassiumFeldspar     );
			Granite                         .addOreByProducts(PotassiumFeldspar     );
			Eclogite                        .addOreByProducts(TiO2                  );
			Limestone                       .addOreByProducts(Phosphorite           );
			PO4                             .addOreByProducts(P                     );
			P                               .addOreByProducts(PO4                   );
			Tanzanite                       .addOreByProducts(Opal                  );
			Opal                            .addOreByProducts(Tanzanite             );
			Topaz                           .addOreByProducts(BlueTopaz             );
			BlueTopaz                       .addOreByProducts(Topaz                 );
			Si                              .addOreByProducts(SiO2                  );
			In                              .addOreByProducts(Se                    );
			Li                              .addOreByProducts(LiCl                  );
			LiCl                            .addOreByProducts(Li                    );
			
			Dn                              .addOreByProducts(Tn                    );
			Tn                              .addOreByProducts(Dn                    );
			Dolamide                        .addOreByProducts(MT.Dilithium          , OREMATS.DiduraniumTrioxide, OREMATS.DuraniumHexafluoride, OREMATS.DuraniumHexaastatide, OREMATS.DuraniumHexabromide);
			
			Alduorite                       .addOreByProducts(Cd                    );
			Infuscolium                     .addOreByProducts(Cu                    );
			Rubracium                       .addOreByProducts(Cr                    );
			Meutoite                        .addOreByProducts(VanadiumPentoxide     );
			Lemurite                        .addOreByProducts(Mg                    );
			Ceruclase                       .addOreByProducts(Sb                    );
			Atl                             .addOreByProducts(TiO2                  );
			Oureclase                       .addOreByProducts(Ni                    );
			Kalendrite                      .addOreByProducts(Os                    );
			Orichalcum                      .addOreByProducts(Cs                    );
			Carmot                          .addOreByProducts(Zn                    );
			Sanguinite                      .addOreByProducts(Hg                    );
			Vyroxeres                       .addOreByProducts(Ir                    );
			Eximite                         .addOreByProducts(Pd                    );
			Prometheum                      .addOreByProducts(Co                    );
			Ignatius                        .addOreByProducts(Se                    , In);
			Vulcanite                       .addOreByProducts(OREMATS.Wolframite    );
			DeepIron                        .addOreByProducts(Fe2O3                 );
			ShadowIron                      .addOreByProducts(Fe2O3                 );
			AstralSilver                    .addOreByProducts(Ag                    );
			Midasium                        .addOreByProducts(Au                    );
			Mithril                         .addOreByProducts(Pt                    );
			
			Fe                      .addAlloyingRecipe(new OreDictConfigurationComponent( 2, OM.stack(OREMATS.YellowLimonite        , 8*U), OM.stack(C                  , 1*U)));
			Fe                      .addAlloyingRecipe(new OreDictConfigurationComponent( 2, OM.stack(OREMATS.BrownLimonite         , 8*U), OM.stack(C                  , 1*U)));
// TODO     Fe                      .addAlloyingRecipe(new OreDictConfigurationComponent( 2, OM.stack(OREMATS.Chalcopyrite          , 8*U), OM.stack(C                  , 1*U)));
			Fe                      .addAlloyingRecipe(new OreDictConfigurationComponent( 2, OM.stack(Fe2O3                         , 5*U), OM.stack(C                  , 1*U), OM.stack(CaCO3, 1*U)));
			Fe                      .addAlloyingRecipe(new OreDictConfigurationComponent( 6, OM.stack(OREMATS.Magnetite             ,14*U), OM.stack(C                  , 3*U)));
			Fe                      .addAlloyingRecipe(new OreDictConfigurationComponent( 6, OM.stack(OREMATS.Ferrovanadium         ,28*U), OM.stack(C                  , 3*U)));
			Fe                      .addAlloyingRecipe(new OreDictConfigurationComponent( 6, OM.stack(OREMATS.BasalticMineralSand   ,28*U), OM.stack(C                  , 3*U)));
			Fe                      .addAlloyingRecipe(new OreDictConfigurationComponent( 6, OM.stack(OREMATS.GraniticMineralSand   ,28*U), OM.stack(C                  , 3*U)));
			
			Si                      .addAlloyingRecipe(new OreDictConfigurationComponent( 1, OM.stack(SiO2                          , 3*U), OM.stack(C                  , 1*U)));
			
//          Al                      .addAlloyingRecipe(new OreDictConfigurationComponent( 1, OM.stack(OREMATS.Bauxite               , 2*U), OM.stack(Na                 , 1*U)));
//          Al                      .addAlloyingRecipe(new OreDictConfigurationComponent( 1, OM.stack(OREMATS.Bauxite               , 2*U), OM.stack(K                  , 1*U)));
			
			Fe                      .addAlloyingRecipe(new OreDictConfigurationComponent( 2, OM.stack(ShadowIron                    , 1*U), OM.stack(Ignatius           , 1*U)));
			Fe                      .addAlloyingRecipe(new OreDictConfigurationComponent( 2, OM.stack(DeepIron                      , 1*U), OM.stack(Prometheum         , 1*U)));
			BlackSteel              .addAlloyingRecipe(new OreDictConfigurationComponent( 2, OM.stack(DeepIron                      , 1*U), OM.stack(Infuscolium        , 1*U)));
			
			Steel                   .addAlloyingRecipe(new OreDictConfigurationComponent( 1, OM.stack(WroughtIron                   , 1*U), OM.stack(Air                , 1*U)));
			MeteoricSteel           .addAlloyingRecipe(new OreDictConfigurationComponent( 1, OM.stack(MeteoricIron                  , 1*U), OM.stack(Air                , 1*U)));
			
			
			for (OreDictMaterial tMat : ANY.Glowstone.mToThis) if (tMat != Glowstone) {
			EnergeticAlloy          .addAlloyingRecipe(new OreDictConfigurationComponent( 1, OM.stack(InductiveAlloy                , 2*U), OM.stack(tMat               , 1*U)));
			Lumium                  .addAlloyingRecipe(new OreDictConfigurationComponent( 4, OM.stack(Sn                            , 3*U), OM.stack(Ag                 , 1*U), OM.stack(tMat, 4*U)));
			}
			BlackSteel              .addAlloyingRecipe(new OreDictConfigurationComponent( 5, OM.stack(Ni                            , 1*U), OM.stack(BlackBronze        , 1*U), OM.stack(HSLA   , 3*U)));
			RedSteel                .addAlloyingRecipe(new OreDictConfigurationComponent( 8, OM.stack(SterlingSilver                , 1*U), OM.stack(BismuthBronze      , 1*U), OM.stack(HSLA   , 2*U), OM.stack(BlackSteel , 4*U)));
			BlueSteel               .addAlloyingRecipe(new OreDictConfigurationComponent( 8, OM.stack(RoseGold                      , 1*U), OM.stack(Brass              , 1*U), OM.stack(HSLA   , 2*U), OM.stack(BlackSteel , 4*U)));
			TungstenSteel           .addAlloyingRecipe(new OreDictConfigurationComponent( 2, OM.stack(HSLA                          , 1*U), OM.stack(W                  , 1*U)));
			TungstenSteel           .addAlloyingRecipe(new OreDictConfigurationComponent( 2, OM.stack(HSLA                          , 1*U), OM.stack(TungstenSintered   , 1*U)));
			TungstenSteel           .addAlloyingRecipe(new OreDictConfigurationComponent( 2, OM.stack(Steel                         , 1*U), OM.stack(TungstenSintered   , 1*U)));
			VanadiumSteel           .addAlloyingRecipe(new OreDictConfigurationComponent( 5, OM.stack(HSLA                          , 4*U), OM.stack(V                  , 1*U)));
			ElectricalSteel         .addAlloyingRecipe(new OreDictConfigurationComponent( 1, OM.stack(HSLA                          , 1*U), OM.stack(Si                 , 1*U)));
			ObsidianSteel           .addAlloyingRecipe(new OreDictConfigurationComponent( 1, OM.stack(HSLA                          , 1*U), OM.stack(Lava               , 9*U)));
			ObsidianSteel           .addAlloyingRecipe(new OreDictConfigurationComponent( 1, OM.stack(Steel                         , 1*U), OM.stack(Lava               , 9*U)));
			Alumite                 .addAlloyingRecipe(new OreDictConfigurationComponent( 5, OM.stack(Al                            , 5*U), OM.stack(WroughtIron        , 2*U), OM.stack(Lava,18*U)));
			BlueAlloy               .addAlloyingRecipe(new OreDictConfigurationComponent( 1, OM.stack(Ag                            , 1*U), OM.stack(Teslatite          , 4*U)));
			RedAlloy                .addAlloyingRecipe(new OreDictConfigurationComponent( 1, OM.stack(AnnealedCopper                , 1*U), OM.stack(Redstone           , 4*U)));
			SterlingSilver          .addAlloyingRecipe(new OreDictConfigurationComponent( 5, OM.stack(AnnealedCopper                , 1*U), OM.stack(Ag                 , 4*U)));
			RoseGold                .addAlloyingRecipe(new OreDictConfigurationComponent( 5, OM.stack(AnnealedCopper                , 1*U), OM.stack(Au                 , 4*U)));
			AluminiumBrass          .addAlloyingRecipe(new OreDictConfigurationComponent( 4, OM.stack(AnnealedCopper                , 1*U), OM.stack(Al                 , 3*U)));
			Brass                   .addAlloyingRecipe(new OreDictConfigurationComponent( 4, OM.stack(AnnealedCopper                , 3*U), OM.stack(Zn                 , 1*U)));
			Bronze                  .addAlloyingRecipe(new OreDictConfigurationComponent( 4, OM.stack(AnnealedCopper                , 3*U), OM.stack(Sn                 , 1*U)));
			BlackBronze             .addAlloyingRecipe(new OreDictConfigurationComponent( 5, OM.stack(AnnealedCopper                , 3*U), OM.stack(Electrum           , 2*U)));
			Signalum                .addAlloyingRecipe(new OreDictConfigurationComponent( 8, OM.stack(AnnealedCopper                , 1*U), OM.stack(Ag                 , 2*U), OM.stack(RedAlloy, 5*U)));
			Constantan              .addAlloyingRecipe(new OreDictConfigurationComponent( 2, OM.stack(AnnealedCopper                , 1*U), OM.stack(Ni                 , 1*U)));
			YttriumBariumCuprate    .addAlloyingRecipe(new OreDictConfigurationComponent( 6, OM.stack(AnnealedCopper                , 3*U), OM.stack(Ba                 , 2*U), OM.stack(Y, U)));
			YttriumBariumCuprate    .addAlloyingRecipe(new OreDictConfigurationComponent( 6, OM.stack(Cu                            , 3*U), OM.stack(Ba                 , 2*U), OM.stack(Y, U)));
			Li2Fe2O4                .addAlloyingRecipe(new OreDictConfigurationComponent( 8, OM.stack(Fe2O3                         , 5*U), OM.stack(Li2O               , 3*U)));
			
			String tMakeSignalum =
			  "The Alloy Signalum can be a little bit hard to produce as you could end up with unwanted Sterling Silver in the Process. To prevent that, insert the Copper last, and always in small amounts at a time.";
			String tMakeSteel =
			  "In order to make Steel you just need to melt Iron or Wrought Iron in a Smelting Crucible and apply Air to it using an Engine.";
			String tMakeWroughtIron =
			  "Wrought Iron is created by heating up Iron until " + WroughtIron.mMeltingPoint + " Kelvin to dissolve most unwanted impurities.";
			String tMakeAnnealedCopper =
			  "Annealed Copper is created by heating up Copper until " + AnnealedCopper.mMeltingPoint + " Kelvin to dissolve most unwanted impurities.";
			String tMakeAluminium =
			  "Making Aluminium is a very complicated chemical Process. You will need an LV Electrolyzer, a Mixer, a Corrosion Resistant Crucible or a Smelter, Fluorite, Saltwater, Alumina and a bit more to do it.";
			String tKillWerewolf =
			  "It is also very useful in order to kill Werewolves and alike, since everyone knows how Werewolves are allergic to Silver! It also works on Armor like a kind of Thorns (without the stupid extra armor damage)";
			String tKillSlime =
			  "Somehow this Material dissolves Slimey substances and therefore causes severe damage to Slimes and similar Creatures!";
			
			
			Signalum.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  ""
			, tMakeSignalum
			};
			
			Redstone.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Redstone consists out of many things and has a lot in common with Cinnabar, even though containing much more than just Mercury and Sulfur. Redstone is usually found at places with Rare Earths."
			, tMakeSignalum
			};
			
			RedAlloy.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  ""
			, tMakeSignalum
			};
			
			Ag.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Silver, the Material Endermen fear the most. Somehow this Material can interfere with the ability of Endermen to teleport, and even damages them dramatically, as if it were poisonous to them."
			, tKillWerewolf
			, tMakeSignalum
			};
			
			Cu.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Copper is a Material, which is needed in almost every electrical Device. But not only that, it is also used to make Bronze, Brass, Cupronickel and some other Alloys."
			, tKillSlime
			, tMakeAnnealedCopper
			, tMakeSignalum
			};
			
			AnnealedCopper.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Annealed Copper is just Copper cleaned from impurities. Therefore some of Coppers natural properties are better than in unpurified Copper."
			, tKillSlime
			, tMakeAnnealedCopper
			, tMakeSignalum
			};
			
			Al.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Aluminium is a shiny rare Metal, that is extremely difficult to setup a production for. You will need to convert Alumina into Aluminium using Chemicals and an Electrolyzer."
			, tMakeAluminium
			};
			
			Alumina.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Aluminas are the Materials used to create Aluminium out of. It is basically corroded Aluminium and removing said corrosion can be quite difficult and even requires electricity."
			, tMakeAluminium
			};
			
			OREMATS.Bauxite.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  ""
			, tMakeAluminium
			};
			
			Fe.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Iron, the most needed Material in Minecraft, unless you have an Iron Titan or something. Nearly every Mod needs it in order to proceed with its Machines. It exists in many Shapes and Forms, such as"
			, "Compressed Iron, Wrought Iron, Steel, HSLA Steel, Dark Iron, Deep Iron, Meteoric Iron, Conductive Iron, Electrical Steel, Pulsating Iron, Mutated Iron, Shadow Iron, Ironwood and many many more things."
			, "It is a very useful Material as it is required to make Steel, the Stuff almost everything Technological is made of. You can also make Wrought Iron, to craft Tools slightly better than Iron Tools."
			, tMakeWroughtIron
			, tMakeSteel
			};
			
			WroughtIron.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Wrought Iron, a Material that is worth as much as Iron. It is just a step between Iron itself and Steel, and is just a cleaner Version of Iron. It is also a slightly better Tool Material than Iron."
			, tMakeWroughtIron
			, tMakeSteel
			};
			
			Steel.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Steel, the Material Standard Machinery is made of. Cheap, abundant and common, as it only requires Iron and some source of Carbon. There are many different Ways of making Steel supplied by many Mods."
			, tMakeSteel
			};
			
			C.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Carbon. Not much to say about it. It is used to make Steel. Uhhm, maybe it has some other uses explained in a later Edition of this Book."
			, tMakeSteel
			};
			
			Diamond.mDescription = DiamondIndustrial.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Diamonds, the shiny Stuff every Minecrafter looks for. Some people may even create living Horses made of Diamonds."
			, tMakeSteel
			};
			
			Graphite.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Graphite is a Source of Carbon. It can also be used to make Steel directly."
			, tMakeSteel
			};
			
			DamascusSteel.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Damascus Steel is a very rare kind of Steel. Rare meaning actually nobody knows how to produce it anymore as they are all dead. Maybe ask a Zombie or a Skeleton on how to make it?"
			, "Because of that, it can only be found in old Structures such as Dungeons. Sometimes Villagers stumble upon this rare Steel or things made of it and take it home to give it to their Blacksmith."
			, "It is a very good Tool Material and even surpasses Blue Steel in its Quality. It has Vanadium and Tungsten impurities which improve the stability of the Carbides when Forging at higher Temperatures."
			};
			
			Craponite.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Craponite is a very exquisite Material used only in the fanciest Jewelry. It looks best in combination with Peridot. That is why Teleshopping Channels often sell Peridot Craponite Earrings and alike."
			};
			
			Ir.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Iridium, a very rare Metal used in only the most advanced Technology. Its properties are very versatile and used in many advanced Devices."
			, "Iridium is a Metal that can stabilise a Tesseract, so that it can transfer much larger Amounts of Matter and Energy."
			, "As Weapon it is very useful in order to kill Shapeshifters, since it is highly Toxic to them. Now, if there were Shapeshifters in our World this Information would be more useful."
			};
			
			Tc.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Technetium, named after Gregorius Techneticies, is the first Element of the Periodic System, which has no stable Isotope. Its usage is mostly limited to Medical Applications."
			, "Fun Fact:\nEvery Element, which has no Elemental Stats assigned to, will automatically default to the Elemental Stats of Technetium."
			};
			
			Mcg.mDescription = new String[] {
			// ======================================================================================================================================================================================================== 
			  "Mac-Guffium, the most useful Material of them all. It can be used for everything, as long as its underlying Science is too obscure to be understood."
			, "Possible Applications are: Time Travel, Infinite Impossibility Drives, World Peace, World Domination, World War, World War II, World War IV, Curing all Diseases, Stopping Global Warming,"
			, "Controlling the Weather, Turning everything into Gold, Turning you into a God, Killing a God, Making Dark Matter an Energy Source, Tesseracts, Wormholes, Black Holes, Bending Space and Time, Tardis,"
			, "Ascension to a higher Level of existence, Invisibility, Killing Chuck Norris, Cloning, Making Profit of collecting Underpants, Making the Impossible possible, Love Rays, Hate Rays, Perpetuum Mobiles,"
			, "Making you able to hold and use Thors Hammer, and ofcourse baking delicious Cookies."
			};
			
			Nq.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "This extremly rare Material is called Naquadah. Its properties make it very useful in highly advanced Technology."
			, "It is a Superconductor even at very high Temperatures, what makes it seeming Ideal for electric Wiring as long as the amount of transferred Electricity is not too large."
			, "Another property of it is that it emits Gamma Radiation when supplied with enough Electricity, due to creating Positrons out of said Energy, which then collide with Electrons very quickly."
			, "However that property makes it very unstable and can result in a Nuclear Explosion when not regulated properly, especially with molten Naquadah."
			};
			
			Nq_528.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "This is a heavy Isotope of Naquadah mainly used to generate Energy, or to create Bombs."
			, "Due to being less stable than regular Naquadah, supplying this Isotope with Electricity results in a very strong Nuclear Reaction, which when regulated properly can result in a constant Energy Source."
			, "The instability increases when this Isotope is molten, resulting in a larger Energy Output when used in Reactors."
			, "Its instability is also often used to make Naquadah Bombs, which are much more destructive than the regular Plutonium Nukes. It doesn't make any difference to use the molten or the solid Isotope"
			, "for Bombs, since its explosion heats up the whole Bomb far above the melting Point anyways."
			};
			
			Nq_522.mDescription = new String[] {
			// ========================================================================================================================================================================================================
			  "Naquadria, a light and extremely unstable Isotope of Naquadah. It can be used for Reactors as well as Bombs and is much stronger than heavy Naquadah."
			, "It is created when Naquadah is receiving too large amounts of Gamma Radiation. During its creation it emits more Gamma Radiation turning adjacent Naquadah into Naquadria too."
			, "The Natural origin of this Isotope is a Gamma Ray Burst, which hits a Naquadah Vein directly. Planets to which that happens are then turning into a Desert due to the enormous Heat created by the Ray."
			, "Gamma Bombs which are exploding far enough away from a Naquadah Vein, or just a strong enough Gamma Ray, can also trigger a Chain Reaction turing Naquadah into Naquadria."
			, "However most of the time a Gamma Ray hits a Naquadah Vein, it just explodes, what is the main Factor of Naquadria being very rare. Also Naquadria turns back into Naquadah after a few thousand years."
			};
			
			for (OreDictMaterial tMaterial : ALL_MATERIALS_REGISTERED_HERE) {
				tMaterial.mHandleMaterial = ANY.WoodPlastic;
				if (tMaterial.mOriginalMod == null && !tMaterial.contains(INVALID_MATERIAL)) tMaterial.setOriginalMod(MD.GAPI);
			}
			
			Diamond.put(AchievementList.openInventory, AchievementList.mineWood, AchievementList.buildWorkBench, AchievementList.buildPickaxe, AchievementList.buildFurnace, AchievementList.acquireIron, AchievementList.diamonds);
			ManaDiamond.put(AchievementList.openInventory, AchievementList.mineWood, AchievementList.buildWorkBench, AchievementList.buildPickaxe, AchievementList.buildFurnace, AchievementList.acquireIron, AchievementList.diamonds);
			
			Mauftrium.mHandleMaterial = Elvorium.mHandleMaterial = MuspelheimPower.mHandleMaterial = NiflheimPower.mHandleMaterial = ElvenElementium.mHandleMaterial = ElvenDragonstone.mHandleMaterial = Manasteel.mHandleMaterial = Terrasteel.mHandleMaterial = ManaDiamond.mHandleMaterial = DarkThaumium.mHandleMaterial = Thaumium.mHandleMaterial = VoidMetal.mHandleMaterial = InfusedAir.mHandleMaterial = InfusedBalance.mHandleMaterial = InfusedDull.mHandleMaterial = InfusedEarth.mHandleMaterial = InfusedEntropy.mHandleMaterial = InfusedFire.mHandleMaterial = InfusedOrder.mHandleMaterial = InfusedVis.mHandleMaterial = InfusedWater.mHandleMaterial = ANY.WoodMagical;
			GaiaSpirit.mHandleMaterial = MT.ElvenElementium;
			FierySteel.mHandleMaterial = Firestone.mHandleMaterial = Blaze.mHandleMaterial = Blaze;
			Endium.mHandleMaterial = Endstone;
			SpectreIron.mHandleMaterial = Obsidian;
			EnderAmethyst.mHandleMaterial = Meteorite.mHandleMaterial = Kreknorite.mHandleMaterial = ANY.Iron;
			DarkMatter.mHandleMaterial = Diamond;
			RedMatter.mHandleMaterial = DarkMatter;
			Desh.mHandleMaterial = Desh;
			Etx.mHandleMaterial = Etx;
			Vb.mHandleMaterial = VibraniumSteel.mHandleMaterial = VibraniumSilver.mHandleMaterial = VibraniumSteel;
			Vibramantium.mHandleMaterial = Vibramantium;
			VoidMetal.mHandleMaterial = InfusedAir.mHandleMaterial = InfusedBalance.mHandleMaterial = InfusedDull.mHandleMaterial = InfusedEarth.mHandleMaterial = InfusedEntropy.mHandleMaterial = InfusedFire.mHandleMaterial = InfusedOrder.mHandleMaterial = InfusedWater.mHandleMaterial = InfusedVis.mHandleMaterial = MD.TC.mLoaded?Thaumium:DamascusSteel;
		}
	}
	
	public static class DATA {
		public static final OreDictItemData[]
		WIRES_01 = {
			OP.wireGt01.dat(Pb),
			OP.wireGt01.dat(Sn),
			OP.wireGt01.dat(ANY.Cu),
			OP.wireGt01.dat(Au),
			OP.wireGt01.dat(Al),
			OP.wireGt01.dat(Pt),
			OP.wireGt01.dat(Graphene),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor)
		},
		WIRES_04 = {
			OP.wireGt04.dat(Pb),
			OP.wireGt04.dat(Sn),
			OP.wireGt04.dat(ANY.Cu),
			OP.wireGt04.dat(Au),
			OP.wireGt04.dat(Al),
			OP.wireGt04.dat(Pt),
			OP.wireGt04.dat(Graphene),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor)
		},
		CABLES_01 = {
			OP.cableGt01.dat(Pb),
			OP.cableGt01.dat(Sn),
			OP.cableGt01.dat(ANY.Cu),
			OP.cableGt01.dat(Au),
			OP.cableGt01.dat(Al),
			OP.cableGt01.dat(Pt),
			OP.wireGt01.dat(Graphene),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor),
			OP.wireGt01.dat(Superconductor)
		},
		CABLES_04 = {
			OP.cableGt04.dat(Pb),
			OP.cableGt04.dat(Sn),
			OP.cableGt04.dat(ANY.Cu),
			OP.cableGt04.dat(Au),
			OP.cableGt04.dat(Al),
			OP.cableGt04.dat(Pt),
			OP.wireGt04.dat(Graphene),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor),
			OP.wireGt04.dat(Superconductor)
		},
		CIRCUITS = {
			OP.circuit.dat(Primitive),
			OP.circuit.dat(Basic),
			OP.circuit.dat(Good),
			OP.circuit.dat(Advanced),
			OP.circuit.dat(Elite),
			OP.circuit.dat(Master),
			OP.circuit.dat(Ultimate),
			OP.circuit.dat(Quantum),
			OP.circuit.dat(Quantum),
			OP.circuit.dat(Quantum),
			OP.circuit.dat(Quantum),
			OP.circuit.dat(Quantum),
			OP.circuit.dat(Quantum),
			OP.circuit.dat(Quantum),
			OP.circuit.dat(Quantum),
			OP.circuit.dat(Quantum)
		};
		
		
		
		public static final OreDictMaterial
		Dye_Materials[]             = {Black, Red, Green, Brown, Blue, Purple, Cyan, LightGray, Gray, Pink, Lime, Yellow, LightBlue, Magenta, Orange, White},
		
		Heat_T[]                    = {ANY.Stone    , ANY.Steel         , Invar     , Ti                , TungstenCarbide   , ANY.W     , ANY.W             , ANY.W             , ANY.W             , ANY.W             , ANY.W             , ANY.W             , ANY.W             , ANY.W             , ANY.W             , ANY.W             },
		Kinetic_T[]                 = {ANY.Wood     , Bronze            , ANY.Steel , Ti                , TungstenSteel     , Ir        , Os                , Os                , Os                , Os                , Os                , Os                , Os                , Os                , Os                , Os                },
		Electric_T[]                = {TinAlloy     , SteelGalvanized   , Al        , StainlessSteel    , Cr                , Ti        , Ir                , Os                , Trinitanium       , Trinaquadalloy    , Neutronium        , Neutronium        , Neutronium        , Neutronium        , Neutronium        , Neutronium        },
		Flux_T[]                    = {Sn           , Pb                , Invar     , Electrum          , EnderiumBase      , Enderium  , TungstenCarbide   , TungstenCarbide   , TungstenCarbide   , TungstenCarbide   , TungstenCarbide   , TungstenCarbide   , TungstenCarbide   , TungstenCarbide   , TungstenCarbide   , TungstenCarbide   };
	}
	
	/** The Section where I place regular Ores that are only used in advanced processing anyways due to complexity. */
	public static class OREMATS {
		public static final OreDictMaterial
		Magnetite               = oredustelec   ( 9122, "Magnetite"                 , SET_METALLIC          ,  30,  30,  30, 255).put(MORTAR, MELTING, MOLTEN, MAGNETIC_PASSIVE                                 )                           .uumMcfg( 0, Fe             , 3*U, O                , 4*U)                                                                                                  .qual(0).heat(Fe.mMeltingPoint).aspects(TC.METALLUM, 2, TC.MAGNETO, 1),
		Cassiterite             = oredustelec   ( 9108, "Cassiterite"               , SET_METALLIC          , 220, 220, 220, 255).put(MORTAR, FURNACE, "CassiteriteSand"                                        ).setSmelting(Sn, 3*U4)     .uumMcfg( 1, Sn             , 1*U, O                , 2*U)                                                                                                  .heat(3 * Sn.mMeltingPoint / 2), CassiteriteSand = Cassiterite,
//      CassiteriteSand         = oredustelec   ( 9109, "Cassiterite Sand"          , SET_SAND              , 220, 220, 220, 255).put(MORTAR, FURNACE                                                           ).setSmelting(Sn, 3*U4)     .uumMcfg( 1, Sn             , 1*U, O                , 2*U)                                                                                                  .heat(3 * Sn.mMeltingPoint / 2),
		Garnierite              = oredustelec   ( 9118, "Garnierite"                , SET_METALLIC          ,  50, 200,  70, 255).put(MORTAR, BLACKLISTED_SMELTER, MAGNETIC_PASSIVE, WASHING_PERSULFATE         ).setSmelting(Ni, 3*U4)     .uumMcfg( 1, Ni             , 1*U, O                , 1*U)                                                                                                  .qual(0),
		Uraninite               = oredustelec   ( 9134, "Uraninite"                 , SET_METALLIC          ,  35,  35,  35, 255).put(BLACKLISTED_SMELTER                                                       ).setSmelting(U_238,U3)     .setMcfg( 1, U_238          , 1*U, O                , 2*U)                                                                                                  ,
		Pyrolusite              = MnO2,
		Rutile                  = TiO2,
		Hematite                = Fe2O3,
		
		Cinnabar                = oredustcent   ( 9114, "Cinnabar"                  , SET_ROUGH             , 150,   0,   0, 255).put(MORTAR, FURNACE                                                           ).setSmelting(Hg,   U3)     .uumMcfg( 0, Hg             , 1*U, S                , 1*U)                                                                                                  ,
		Molybdenite             = oredustdcmp   ( 9123, "Molybdenite"               , SET_METALLIC          ,  25,  25,  25, 255).put(MORTAR, BLACKLISTED_SMELTER                                               ).setSmelting(Mo,   U4)     .uumMcfg( 0, Mo             , 1*U, S                , 2*U)                                                                                                  ,
		Sphalerite              = oredustdcmp   ( 9130, "Sphalerite"                , SET_DULL              , 222, 222,   0, 255).put(MORTAR, BLACKLISTED_SMELTER, WASHING_PERSULFATE                           ).setSmelting(Zn,   U3)     .uumMcfg( 0, Zn             , 1*U, S                , 1*U)                                                                                                  ,
		Stibnite                = oredustdcmp   ( 9131, "Stibnite"                  , SET_METALLIC          ,  70,  70,  70, 255).put(MORTAR, BLACKLISTED_SMELTER                                               ).setSmelting(Sb,   U4)     .uumMcfg( 0, Sb             , 2*U, S                , 3*U)                                                                                                  ,
		Pentlandite             = oredustdcmp   ( 9145, "Pentlandite"               , SET_DULL              , 165, 150,   5, 255).put(MORTAR, BLACKLISTED_SMELTER, MAGNETIC_PASSIVE, WASHING_PERSULFATE         ).setSmelting(Ni,   U3)     .uumMcfg( 0, Ni             , 9*U, S                , 8*U)                                                                                                  .qual(0), // (Fe,Ni)9S8
		Chalcopyrite            = oredustdcmp   ( 9111, "Chalcopyrite"              , SET_DULL              , 160, 120,  40, 255).put(MORTAR, FURNACE                                                           ).setSmelting(Cu, 2*U9)     .uumMcfg( 0, Cu             , 1*U, Fe               , 1*U, S                , 2*U)                                                                          .qual(0),
		Arsenopyrite            = oredustdcmp   ( 9216, "Arsenopyrite"              , SET_CUBE_SHINY        , 250, 240,  30, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .uumMcfg( 0, Fe             , 1*U, As               , 1*U, S                , 1*U)                                                                          .qual(0),
		Cobaltite               = oredustdcmp   ( 9115, "Cobaltite"                 , SET_METALLIC          ,  80,  80, 250, 255).put(MORTAR, BLACKLISTED_SMELTER, MAGNETIC_PASSIVE, WASHING_PERSULFATE         ).setSmelting(Co,   U4)     .uumMcfg( 0, Co             , 1*U, As               , 1*U, S                , 1*U)                                                                          .qual(0),
		Galena                  = oredustdcmp   ( 9117, "Galena"                    , SET_DULL              , 100,  60, 100, 255).put(MORTAR, FURNACE                                                           ).setSmelting(Pb,   U3)     .uumMcfg( 0, Pb             , 3*U, Ag               , 3*U, S                , 2*U)                                                                          ,
		Cooperite               = oredustdcmp   ( 9116, "Cooperite"                 , SET_METALLIC          , 130, 160, 230, 255).put(MORTAR, BLACKLISTED_SMELTER, WASHING_MERCURY, "Sheldonite"                ).setSmelting(Pt,   U3)     .uumMcfg( 0, Pt             , 3*U, Ni               , 1*U, Pd               , 1*U, S                , 1*U)                                                  .setLocal("Sheldonite"),
		Tetrahedrite            = oredustdcmp   ( 9132, "Tetrahedrite"              , SET_DULL              , 200,  32,   0, 255).put(MORTAR, FURNACE, WASHING_PERSULFATE                                       ).setSmelting(Cu,   U4)     .uumMcfg( 0, Cu             , 3*U, Sb               , 1*U, Fe               , 1*U, S                , 3*U)                                                  , // Cu3SbS3 + x(Fe,Zn)6Sb2S9
		Kesterite               = oredustdcmp   ( 9213, "Kesterite"                 , SET_DULL              , 105, 155, 105, 255).put(MORTAR, BLACKLISTED_SMELTER                                               ).setSmelting(Cu,   U9)     .uumMcfg( 0, Cu             , 2*U, Zn               , 1*U, Sn               , 1*U, S                , 4*U)                                                  ,
		Stannite                = oredustdcmp   ( 9214, "Stannite"                  , SET_METALLIC          , 155, 145,  55, 255).put(MORTAR, BLACKLISTED_SMELTER                                               ).setSmelting(Cu,   U9)     .uumMcfg( 0, Cu             , 2*U, Fe               , 1*U, Sn               , 1*U, S                , 4*U)                                                  ,
		Barite                  = oredustelec   ( 9160, "Barite"                    , SET_DULL              , 230, 235, 255, 255).put(MORTAR, BLACKLISTED_SMELTER                                               ).setSmelting(Ba,   U9)     .uumMcfg( 0, Ba             , 1*U, S                , 1*U, O                , 4*U)                                                                          ,
		Celestine               = oredustelec   ( 9110, "Celestine"                 , SET_DULL              , 200, 205, 240, 255).put(MORTAR, BLACKLISTED_SMELTER                                               ).setSmelting(Sr,   U9)     .uumMcfg( 0, Sr             , 1*U, S                , 1*U, O                , 4*U)                                                                          ,
		
		Scheelite               = oredustdcmp   ( 9128, "Scheelite"                 , SET_DULL              , 200, 140,  20, 255).put(BLACKLISTED_SMELTER, "CalciumTungstate"                                   )                           .uumMcfg( 0, Ca             , 1*U, WO3              , 4*U, O                , 1*U)                                                                          .qual(3),
		Wolframite              = oredustdcmp   ( 9217, "Wolframite"                , SET_DULL              ,  55,  50,  35, 255).put(BLACKLISTED_SMELTER                                                       )                           .uumMcfg( 0, Mg             , 1*U, WO3              , 4*U, O                , 1*U)                                                                          .qual(3),
		Ferberite               = oredustdcmp   ( 9194, "Ferberite"                 , SET_DULL              ,  55,  50,  35, 255).put(BLACKLISTED_SMELTER                                                       )                           .uumMcfg( 0, Fe             , 1*U, WO3              , 4*U, O                , 1*U)                                                                          .qual(3),
		Huebnerite              = oredustdcmp   ( 9195, "Huebnerite"                , SET_DULL              ,  55,  50,  35, 255).put(BLACKLISTED_SMELTER, "Gyubnera"                                           )                           .uumMcfg( 0, Mn             , 1*U, WO3              , 4*U, O                , 1*U)                                                                          .qual(3),
		Tungstate               = oredustdcmp   ( 9133, "Tungstate"                 , SET_DULL              ,  55,  50,  35, 255).put(BLACKLISTED_SMELTER                                                       )                           .uumMcfg( 0, Li             , 2*U, WO3              , 4*U, O                , 1*U)                                                                          .qual(3),
		
		Stolzite                = oredustdcmp   ( 9193, "Stolzite"                  , SET_DULL              ,  55,  50,  35, 255).put(BLACKLISTED_SMELTER, "Raspite"                                            ).setSmelting(WO3,4* U6)    .uumMcfg( 0, Pb             , 1*U, WO3              , 4*U, O                , 1*U)                                                                          .qual(3), // TODO Actual Processing, but I don't know what could do it
		Russellite              = oredustdcmp   ( 9196, "Russellite"                , SET_DULL              ,  55,  50,  35, 255).put(BLACKLISTED_SMELTER                                                       ).setSmelting(WO3,4* U9)    .setMcfg( 0, Bi             , 2*U, WO3              , 4*U, O                , 3*U)                                                                          .qual(3), // TODO Actual Processing, but I don't know what could do it
		Pinalite                = oredustdcmp   ( 9197, "Pinalite"                  , SET_DULL              ,  55,  50,  35, 255).put(BLACKLISTED_SMELTER                                                       ).setSmelting(WO3,4*U11)    .uumMcfg( 0, Pb             , 3*U, WO3              , 4*U, Cl               , 2*U, O                , 2*U)                                                  .qual(3), // TODO Actual Processing, but I don't know what could do it
		
		Wollastonite            = oredustelec   ( 9164, "Wollastonite"              , SET_DULL              , 240, 240, 240, 255).put(BLACKLISTED_SMELTER                                                       )                           .setMcfg( 0, Ca             , 1*U, Si               , 1*U, O                , 3*U)                                                                          , // CaSiO3
		
		Ilmenite                = oredustdcmp   ( 9120, "Ilmenite"                  , SET_METALLIC          ,  70,  55,  50, 255).put(MORTAR, MELTING, MOLTEN, MAGNETIC_PASSIVE, "Illmenite", "TitaniumIron"    )                           .uumMcfg( 0, Fe             , 1*U, Ti               , 1*U, O                , 3*U)                                                                          .qual(2),
		Bauxite                 = oredustdcmp   ( 9105, "Bauxite"                   , SET_DULL              , 200, 100,   0, 255).put(MORTAR, BLACKLISTED_SMELTER, APPROXIMATE                                  )                           .setMcfg( 0, TiO2           , 1*U, Ilmenite         , 2*U, Al2O3            , 2*U)                                                                          .qual(2).heat(2800),
		BrownLimonite           = oredustelec   ( 9106, "Brown Limonite"            , SET_METALLIC          , 200, 100,   0, 255).put(MORTAR, MELTING, MOLTEN, MAGNETIC_PASSIVE                                 )                           .setMcfg( 0, Fe             , 1*U, H                , 1*U, O                , 2*U)                                                                          .qual(0).heat(2 * Fe.mMeltingPoint / 3), // FeO(OH)
		YellowLimonite          = oredustelec   ( 9137, "Yellow Limonite"           , SET_METALLIC          , 200, 200,   0, 255).put(MORTAR, MELTING, MOLTEN, MAGNETIC_PASSIVE                                 )                           .setMcfg( 0, Fe             , 1*U, H                , 1*U, O                , 2*U)                                                                          .qual(0).heat(2 * Fe.mMeltingPoint / 3), // FeO(OH) + a bit Ni and Co
		Chromite                = oredustelec   ( 9113, "Chromite"                  , SET_METALLIC          ,  35,  20,  15, 255).put(MORTAR, BLACKLISTED_SMELTER                                               ).setSmelting(Cr, 2*U9)     .setMcfg( 0, Fe             , 1*U, Cr               , 2*U, O                , 4*U)                                                                          .qual(0),
		Magnesite               = oredustelec   ( 9121, "Magnesite"                 , SET_METALLIC          , 250, 250, 180, 255).put(MORTAR, BLACKLISTED_SMELTER                                               ).setSmelting(Mg,   U5)     .setMcfg( 0, Mg             , 1*U, C                , 1*U, O                , 3*U)                                                                          ,
		Powellite               = oredustcent   ( 9124, "Powellite"                 , SET_DULL              , 255, 255,   0, 255).put(MORTAR, BLACKLISTED_SMELTER                                               ).setSmelting(Mo,   U9)     .setMcfg( 0, Ca             , 1*U, Mo               , 1*U, O                , 4*U)                                                                          ,
		Wulfenite               = oredustcent   ( 9136, "Wulfenite"                 , SET_DULL              , 255, 128,   0, 255).put(MORTAR, BLACKLISTED_SMELTER                                               ).setSmelting(Mo,   U9)     .setMcfg( 0, Pb             , 1*U, Mo               , 1*U, O                , 4*U)                                                                          ,
		Perlite                 = oredustcent   ( 9138, "Perlite"                   , SET_DULL              ,  30,  20,  30, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Obsidian       , 2*U, H2O              , 1*U)                                                                                                  ,
		Borax                   = oredustdcmp   ( 9139, "Borax"                     , SET_FINE              , 250, 250, 250, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Na             , 2*U, B                , 4*U, H2O              ,30*U, O                , 7*U)                                                  ,
		Ferrovanadium           = oredustcent   ( 9143, "Ferrovanadium"             , SET_METALLIC          ,  35,  35,  60, 255).put(MORTAR, MELTING, MOLTEN, MAGNETIC_PASSIVE, "Vanadium Magnetite"           )                           .setMcfg( 0, Magnetite      , 1*U, V2O5             , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.MAGNETO, 1), // Mixture of Fe3O4 and V2O5
		Bastnasite              = oredustelec   ( 9144, "Bastnasite"                , SET_FINE              , 200, 110,  45, 255).put(MORTAR, BLACKLISTED_SMELTER                                               ).setSmelting(Ce,   U9)     .setMcfg( 0, Ce             , 1*U, C                , 1*U, F                , 1*U, O                , 3*U)                                                  , // (Ce, La, Y)CO3F
		Spodumene               = oredustelec   ( 9146, "Spodumene"                 , SET_DULL              , 190, 170, 170, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Al2O3          , 5*U, Li               , 2*U, SiO2             ,12*U, O                , 1*U)                                                  , // LiAl(SiO3)2
		Pollucite               = oredustelec   ( 9147, "Pollucite"                 , SET_DULL              , 240, 210, 210, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Al2O3          , 5*U, Cs               , 2*U, SiO2             ,12*U, H2O              , 2*U, O                , 1*U)                          , // (Cs,Na)2Al2Si4O12 2H2O (also a source of Rb)
		Tantalite               = oredustelec   ( 9148, "Tantalite"                 , SET_METALLIC          , 145,  80,  40, 255).put(MORTAR, BLACKLISTED_SMELTER                                               ).setSmelting(Ta,   U9)     .setMcfg( 0, Mn             , 1*U, Ta               , 2*U, O                , 6*U)                                                                          , // (Fe, Mn)Ta2O6 (also source of Nb)
		Lepidolite              = oredustelec   ( 9149, "Lepidolite"                , SET_FINE              , 240,  50, 140, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Al2O3          ,10*U, K                , 1*U, Li               , 3*U, F                , 2*U, O                , 6*U)                          , // K(Li,Al,Rb)3(Al,Si)4O10(F,OH)2
		Glauconite              = oredustelec   ( 9150, "Glauconite"                , SET_DULL              , 130, 180,  60, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Al2O3          ,10*U, K                , 1*U, Mg               , 2*U, H2O              , 3*U, O                , 7*U)                          , // (K,Na)(Fe3+,Al,Mg)2(Si,Al)4O10(OH)2
		GlauconiteSand          = oredustelec   ( 9151, "Glauconite Sand"           , SET_DULL              , 130, 180,  60, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Al2O3          ,10*U, K                , 1*U, Mg               , 2*U, H2O              , 3*U, O                , 7*U)                          , // (K,Na)(Fe3+,Al,Mg)2(Si,Al)4O10(OH)2
		Vermiculite             = oredustelec   ( 9152, "Vermiculite"               , SET_METALLIC          , 200, 180,  15, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Al2O3          ,10*U, Fe               , 3*U, SiO2             ,12*U, H2O              , 4*U, H                , 2*U)                          , // (Mg+2, Fe+2, Fe+3)3 [(AlSi)4O10] (OH)2 4H2O)
		Bentonite               = oredustelec   ( 9153, "Bentonite"                 , SET_ROUGH             , 245, 215, 210, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg(33, Na             , 1*U, Mg               , 6*U, SiO2             ,36*U, H2O              ,14*U, O                , 9*U)                          , // (Na,Ca)0.33(Al,Mg)2(Si4O10)(OH)2 nH2O
		FullersEarth            = oredustelec   ( 9154, "Fullers Earth"             , SET_FINE              , 160, 160, 120, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Mg             , 1*U, SiO2             ,12*U, H                , 1*U, H2O              , 4*U, O                , 3*U)                          , // (Mg,Al)2Si4O10(OH) 4(H2O)
		Pitchblende             = oredustcent   ( 9155, "Pitchblende"               , SET_DULL              , 200, 210,   0, 255).put(MORTAR, BLACKLISTED_SMELTER                                               ).setSmelting(U_238,U5)     .setMcfg( 0, Uraninite      , 3*U, Th               , 1*U, Pb               , 1*U)                                                                          ,
		Malachite               = oredustelec   ( 9156, "Malachite"                 , SET_LAPIS             ,   5,  95,   5, 255).put(MORTAR, G_GEM_ORES, FURNACE, WASHING_PERSULFATE                           ).setSmelting(Cu,   U6)     .setMcfg( 0, Cu             , 2*U, C                , 1*U, H2O              , 3*U, O                , 4*U)                                                  , // Cu2CO3(OH)2
		Mirabilite              = oredustcent   ( 9157, "Mirabilite"                , SET_DULL              , 240, 250, 210, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Na2SO4         , 7*U, H2O              ,10*U)                                                                                                  , // Na2SO4 10H2O
		Mica                    = oredustelec   ( 9158, "Mica"                      , SET_FINE              , 195, 195, 205, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Al2O3          ,15*U, K                , 2*U, SiO2             ,18*U, F                , 4*U)                                                  , // KAl2(AlSi3O10)(F,OH)2
		Trona                   = oredustelec   ( 9159, "Trona"                     , SET_METALLIC          , 135, 135,  95, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Na             , 3*U, C                , 2*U, H                , 1*U, H2O              , 2*U, O                , 6*U)                          , // Na3(CO3)(HCO3) 2H2O
		Gypsum                  = oredustcent   ( 9161, "Gypsum"                    , SET_POWDER            , 240, 240, 240, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, CaSO4          , 6*U, H2O              , 2*U)                                                                                                  , // CaSO4 2H2O
		Bischofite              = oredustdcmp   ( 9221, "Bischofite"                , SET_ROUGH             ,  99, 104, 118, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 1, MgCl2          , 1*U, H2O              , 6*U)                                                                                                  ,
		Alunite                 = oredustelec   ( 9162, "Alunite"                   , SET_METALLIC          , 225, 180,  65, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Al2O3          ,15*U, K                , 2*U, SiO2             ,12*U, H2O              ,18*U, O                , 5*U)                          , // KAl3(SO4)2(OH)6
		Zeolite                 = oredustelec   ( 9165, "Zeolite"                   , SET_DULL              , 240, 230, 230, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Al2O3          , 5*U, Na               , 2*U, SiO2             , 9*U, H2O              , 6*U, O                , 1*U)                          ,
		Kyanite                 = oredustelec   ( 9166, "Kyanite"                   , SET_FLINT             , 110, 110, 250, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Al2O3          , 5*U, SiO2             , 3*U)                                                                                                  , // Al2SiO5
		Kaolinite               = oredustelec   ( 9167, "Kaolinite"                 , SET_DULL              , 245, 235, 235, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Al2O3          , 5*U, SiO2             , 6*U, H2O              , 6*U)                                                                          , // Al2Si2O5(OH)4
		DiduraniumTrioxide      = oredustelec   ( 9198, "Diduranium Trioxide"       , SET_DULL              ,  45, 145, 145, 255).put(BLACKLISTED_SMELTER                                                       )                           .setMcfg( 0, Dn             , 2*U, O                , 3*U)                                                                                                  .qual(4),
		DuraniumHexafluoride    = oredustelec   ( 9199, "Duranium Hexafluoride"     , SET_DULL              ,  25, 175, 125, 255).put(BLACKLISTED_SMELTER                                                       )                           .setMcfg( 0, Dn             , 1*U, F                , 6*U)                                                                                                  .qual(4),
		DuraniumHexachloride    = oredustelec   ( 9200, "Duranium Hexachloride"     , SET_DULL              ,  75, 175, 145, 255).put(BLACKLISTED_SMELTER                                                       )                           .setMcfg( 0, Dn             , 1*U, Cl               , 6*U)                                                                                                  .qual(4),
		DuraniumHexabromide     = oredustelec   ( 9201, "Duranium Hexabromide"      , SET_DULL              ,  45, 125, 175, 255).put(BLACKLISTED_SMELTER                                                       )                           .setMcfg( 0, Dn             , 1*U, Br               , 6*U)                                                                                                  .qual(4),
		DuraniumHexaiodide      = oredustelec   ( 9202, "Duranium Hexaiodide"       , SET_DULL              ,  75, 125, 175, 255).put(BLACKLISTED_SMELTER                                                       )                           .setMcfg( 0, Dn             , 1*U, I                , 6*U)                                                                                                  .qual(4),
		DuraniumHexaastatide    = oredustelec   ( 9203, "Duranium Hexaastatide"     , SET_DULL              ,  25, 145, 175, 255).put(BLACKLISTED_SMELTER                                                       )                           .setMcfg( 0, Dn             , 1*U, At               , 6*U)                                                                                                  .qual(4),
		TritaniumDioxide        = oredustelec   ( 9204, "Tritanium Dioxide"         , SET_DULL              ,  25, 185, 125, 255).put(BLACKLISTED_SMELTER                                                       )                           .setMcfg( 0, Tn             , 1*U, O                , 2*U)                                                                                                  .qual(4),
		TritaniumHexafluoride   = oredustelec   ( 9205, "Tritanium Hexafluoride"    , SET_DULL              ,  85, 125, 125, 255).put(BLACKLISTED_SMELTER                                                       )                           .setMcfg( 0, Tn             , 1*U, F                , 6*U)                                                                                                  .qual(4),
		TritaniumHexachloride   = oredustelec   ( 9206, "Tritanium Hexachloride"    , SET_DULL              ,  55, 185, 155, 255).put(BLACKLISTED_SMELTER                                                       )                           .setMcfg( 0, Tn             , 1*U, Cl               , 6*U)                                                                                                  .qual(4),
		TritaniumHexabromide    = oredustelec   ( 9207, "Tritanium Hexabromide"     , SET_DULL              ,  55, 125, 155, 255).put(BLACKLISTED_SMELTER                                                       )                           .setMcfg( 0, Tn             , 1*U, Br               , 6*U)                                                                                                  .qual(4),
		TritaniumHexaiodide     = oredustelec   ( 9208, "Tritanium Hexaiodide"      , SET_DULL              ,  85, 185, 185, 255).put(BLACKLISTED_SMELTER                                                       )                           .setMcfg( 0, Tn             , 1*U, I                , 6*U)                                                                                                  .qual(4),
		TritaniumHexaastatide   = oredustelec   ( 9209, "Tritanium Hexaastatide"    , SET_DULL              ,  25, 125, 185, 255).put(BLACKLISTED_SMELTER                                                       )                           .setMcfg( 0, Tn             , 1*U, At               , 6*U)                                                                                                  .qual(4),
		Bromargyrite            = oredustelec   ( 9210, "Bromargyrite"              , SET_DULL              ,  90,  45,  10, 255).put(MORTAR, BLACKLISTED_SMELTER, WASHING_MERCURY                              ).setSmelting(Ag,   U3)     .setMcfg( 0, Ag             , 1*U, Br               , 1*U)                                                                                                  ,
		Smithsonite             = oredustelec   ( 9211, "Smithsonite"               , SET_DULL              , 110, 223, 210, 255).put(MORTAR, BLACKLISTED_SMELTER, WASHING_MERCURY, WASHING_PERSULFATE          ).setSmelting(Zn,   U6)     .setMcfg( 0, Zn             , 1*U, C                , 1*U, O                , 3*U)                                                                          ,
		Sperrylite              = oredustelec   ( 9212, "Sperrylite"                , SET_SHINY             , 105, 105, 105, 255).put(MORTAR, BLACKLISTED_SMELTER, WASHING_MERCURY                              ).setSmelting(Pt,   U4)     .setMcfg( 0, Pt             , 1*U, As               , 2*U)                                                                                                  ,
		Pumice                  = oredustcent   ( 9000, "Pumice"                    , SET_DULL              , 230, 185, 185, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Stone          , 1*U)                                                                                                                          ,
		Diatomite               = oredustcent   ( 9001, "Diatomite"                 , SET_DULL              , 225, 225, 225, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Flint          , 8*U, Fe2O3            , 1*U, Sapphire         , 1*U)                                                                          ,
		BasalticMineralSand     = oredustcent   ( 9003, "Basaltic Mineral Sand"     , SET_SAND              ,  40,  50,  40, 255).put(MORTAR, MELTING, MOLTEN, MAGNETIC_PASSIVE                                 )                           .setMcfg( 0, Magnetite      , 1*U, Basalt           , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.MAGNETO, 1),
		GraniticMineralSand     = oredustcent   ( 9004, "Granitic Mineral Sand"     , SET_SAND              ,  40,  60,  60, 255).put(MORTAR, MELTING, MOLTEN, MAGNETIC_PASSIVE                                 )                           .setMcfg( 0, Magnetite      , 1*U, Granite          , 1*U)                                                                                                  .aspects(TC.METALLUM, 2, TC.MAGNETO, 1),
		GarnetSand              = oredustcent   ( 9005, "Garnet Sand"               , SET_SAND              , 200, 100,   0, 255).put(MORTAR, BLACKLISTED_SMELTER                                               )                           .setMcfg( 0, Almandine      , 2*U, Andradite        , 2*U, Grossular        , 2*U, Pyrope           , 2*U, Spessartine      , 2*U, Uvarovite        , 1*U)  ,
		QuartzSand              = oredustcent   ( 9006, "Quartz Sand"               , SET_SAND              , 200, 200, 200, 255).put(MORTAR, BLACKLISTED_SMELTER, QUARTZ                                       ).setSmelting(SiO2, U3)     .setMcfg( 0, CertusQuartz   , 1*U, MilkyQuartz      , 1*U)                                                                                                  ;
	}
	
	/** The "I don't care" Section, everything I don't want to do anything with right now. Just to make the Material Finder shut up about them. But I do see potential uses in some of these Materials. */
	public static class UNUSED {
		public static final OreDictMaterial
		OsmiumTetroxide             = unused        ("Osmium Tetroxide"             ).setMcfg( 0, Os, 1*U, O, 4*U),
		SodiumPeroxide              = unused        ("Sodium Peroxide"              ),
		IridiumSodiumOxide          = unused        ("Iridium Sodium Oxide"         ),
		SolutionBlueVitriol         = unused        ("SolutionBlueVitriol"          ).setLocal("Blue Vitriol Solution"),
		SolutionNickelSulfate       = unused        ("SolutionNickelSulfate"        ).setLocal("Nickel Sulfate Solution").put("SolutionNickelSulphate"),
		Iridiron                    = unused        ("IridiumIron"                  ).setPriorityPrefix(3).put(G_INGOT).setMcfg( 0, Ir, 1*U, Fe, 1*U).setLocal("Iridiron"),
		IridironReinforced          = unused        ("IridiumIronReinforced"        ).setPriorityPrefix(3).put(G_INGOT).setMcfg( 0, Ir, 1*U, Fe, 1*U).setLocal("Reinforced Iridiron"),
		Quicklime                   = unused        ("Quicklime"                    ).setPriorityPrefix(2).put(G_DUST).setMcfg( 0, Ca, 1*U, O, 1*U),
		LimePure                    = unused        ("LimePure"                     ).setLocal("Pure Lime"),
		TNT                         = unused        ("TNT"                          ).put(EXPLOSIVE, FLAMMABLE).aspects(TC.PERDITIO, 3, TC.IGNIS, 1).setOriginalMod(MD.MC),
		TerrasteelAlloyRaw          = unused        ("TerrasteelAlloyRaw"           ).setPriorityPrefix(3).put(G_INGOT, MAGICAL, "RawTerrasteelAlloy").setLocal("Raw Terrasteel Alloy"),
		TerrasteelAlloyStrengthened = unused        ("TerrasteelAlloyStrengthened"  ).setPriorityPrefix(3).put(G_INGOT, MAGICAL, "StrengthenedTerrasteelAlloy").setLocal("Strengthened Terrasteel Alloy"),
		Vis                         = unused        ("Vis"                          ).put(DECOMPOSABLE).setMcfg( 0, Ma, 1*U).aspects(TC.AURAM, 2, TC.PRAECANTIO, 1),
		Unstable                    = unused        ("Unstable"                     ).put(AUTO_BLACKLIST, "Unstableingot", MD.ExU).aspects(TC.PERDITIO, 4),
		Voidstone                   = unused        ("Voidstone"                    ).aspects(TC.VITREUS, 1, TC.VACUOS, 1),
		Mercassium                  = unused        ("Mercassium"                   ).setPriorityPrefix(3).qual(3,  6.0,  64,  1).put(G_INGOT_ORES),
		Osmonium                    = unused        ("Osmonium"                     ).setPriorityPrefix(3).qual(3,  6.0,  64,  1).put(G_INGOT_ORES),
		Phoenixite                  = unused        ("Phoenixite"                   ).setPriorityPrefix(3).qual(3,  6.0,  64,  1).put(G_INGOT_ORES),
		Antimatter                  = unused        ("Antimatter"                   ).put(ANTIMATTER),
		Starconium                  = unused        ("Starconium"                   ).setPriorityPrefix(3).put(G_INGOT_ORES),
		Thyrium                     = unused        ("Thyrium"                      ).setPriorityPrefix(3).put(G_INGOT_ORES),
		Zectium                     = unused        ("Zectium"                      ).setPriorityPrefix(3).put(G_INGOT_ORES),
		Draconic                    = depricated    ("Draconic"                     ).setPriorityPrefix(2).put(G_DUST),
		InfusedTeslatite            = unused        ("InfusedTeslatite"             ).setPriorityPrefix(2).put(G_DUST), // 1 Redstone + 1 Teslatite = 1 Infused
		IrridantUranium             = unused        ("Irridant Uranium"             ).setPriorityPrefix(3).put(G_INGOT),
		IrridantReinforced          = unused        ("IrridantReinforced"           ).setPriorityPrefix(3).put(G_INGOT),
		IronSharp                   = unused        ("IronSharp"                    ).setPriorityPrefix(3).put(G_INGOT).setLocal("Sharp Iron"),
		ObsidianFlux                = unused        ("Obsidian Flux"                ).setPriorityPrefix(3).put(G_INGOT),
		CrystalFlux                 = unused        ("Crystal Flux"                 ).setPriorityPrefix(1).put(G_GEM, CRYSTAL, BRITTLE),
		Mimichite                   = unused        ("Mimichite"                    ).setPriorityPrefix(1).put(G_GEM_ORES, CRYSTAL, BRITTLE),
		Infernal                    = unused        ("Infernal"                     ),
		Invisium                    = unused        ("Invisium"                     ).setPriorityPrefix(2).put(G_DUST),
		Lodestone                   = unused        ("Lodestone"                    ).setPriorityPrefix(2).put(G_DUST_ORES),
		Luminite                    = unused        ("Luminite"                     ).setPriorityPrefix(2).put(G_DUST_ORES),
		Magma                       = unused        ("Magma"                        ),
		Mawsitsit                   = unused        ("Mawsitsit"                    ).setPriorityPrefix(2).put(G_DUST),
		Nether                      = unused        ("Nether"                       ),
		Onyx                        = unused        ("Onyx"                         ).setPriorityPrefix(2).put(G_DUST),
		Painite                     = unused        ("Painite"                      ),
		Petroleum                   = unused        ("Petroleum"                    ).setPriorityPrefix(2).put(G_DUST_ORES),
		Pewter                      = unused        ("Pewter"                       ),
		Potash                      = unused        ("Potash"                       ),
		Randomite                   = unused        ("Randomite"                    ).setPriorityPrefix(2).put(G_DUST_ORES),
		RyuDragonRyder              = unused        ("RyuDragonRyder"               ),
		Sugilite                    = unused        ("Sugilite"                     ).setPriorityPrefix(2).put(G_DUST),
		Tar                         = unused        ("Tar"                          ),
		Tapazite                    = unused        ("Tapazite"                     ).setPriorityPrefix(2).put(G_DUST),
		Tourmaline                  = unused        ("Tourmaline"                   ).setPriorityPrefix(2).put(G_DUST),
		Turquoise                   = unused        ("Turquoise"                    ).setPriorityPrefix(2).put(G_DUST),
		Wimalite                    = unused        ("Wimalite"                     ).setPriorityPrefix(2).put(G_DUST_ORES),
		Adamite                     = unused        ("Adamite"                      ).setPriorityPrefix(2).put(G_DUST_ORES),
		Adluorite                   = unused        ("Adluorite"                    ).setPriorityPrefix(2).put(G_DUST_ORES),
		Agate                       = unused        ("Agate"                        ).setPriorityPrefix(2).put(G_DUST),
		Ammonium                    = unused        ("Ammonium"                     ).setPriorityPrefix(2).put(G_DUST),
		Bitumen                     = unused        ("Bitumen"                      ).setPriorityPrefix(2).put(G_DUST_ORES),
		Bloodstone                  = unused        ("Bloodstone"                   ).setPriorityPrefix(2).put(G_DUST),
		Citrine                     = unused        ("Citrine"                      ).setPriorityPrefix(2).put(G_DUST),
		Coral                       = unused        ("Coral"                        ).setPriorityPrefix(2).put(G_DUST),
		Chrysocolla                 = unused        ("Chrysocolla"                  ).setPriorityPrefix(2).put(G_DUST),
		DarkStone                   = unused        ("Dark Stone"                   ).setPriorityPrefix(2).put(G_DUST),
		Demonite                    = unused        ("Demonite"                     ).setPriorityPrefix(2).put(G_DUST),
		InfusedGold                 = unused        ("Infused Gold"                 ).setPriorityPrefix(3).put(G_INGOT),
		Daffergon                   = unused        ("Daffergon"                    ).setPriorityPrefix(3).put(G_INGOT_ORES, MD.HBM), // Dellite Ore
		Reiium                      = unused        ("Reiium"                       ).setPriorityPrefix(3).put(G_INGOT_ORES, MD.HBM), // Reiite Ore
		Weidanium                   = unused        ("Weidanium"                    ).setPriorityPrefix(3).put(G_INGOT_ORES, MD.HBM), // Weidite Ore
		Verticium                   = unused        ("Verticium"                    ).setPriorityPrefix(3).put(G_INGOT_ORES, MD.HBM),
		Australium                  = unused        ("Australium"                   ).setPriorityPrefix(3).put(G_INGOT_ORES, MD.HBM),
		Schrabidium                 = unused        ("Schrabidium"                  ).setPriorityPrefix(3).put(G_INGOT_ORES, MD.HBM, MAGNETIC_ACTIVE, MELTING, MOLTEN).setRGBa( 50, 255, 255, 255),
		Starmetal                   = unused        ("Starmetal"                    ).setPriorityPrefix(3).put(G_INGOT_MACHINE_ORES, MD.HBM),
		Unobtainium                 = unused        ("Unobtainium"                  ).setPriorityPrefix(3).put(G_INGOT_MACHINE_ORES, MD.HBM),
		CMBSteel                    = unused        ("CMB Steel"                    ).setPriorityPrefix(3).put(G_INGOT_MACHINE, MD.HBM),
		DuraSteel                   = unused        ("DuraSteel"                    ).setPriorityPrefix(3).put(G_INGOT_MACHINE, MD.HBM).setLocal("High-Speed Steel"),
		AdvancedAlloy               = unused        ("Advanced Alloy"               ).setPriorityPrefix(3).put(G_INGOT_MACHINE, MD.HBM),
		Saturnite                   = unused        ("Saturnite"                    ).setPriorityPrefix(3).put(G_INGOT_MACHINE, MD.HBM),
		Dineutronium                = unused        ("Dineutronium"                 ).setPriorityPrefix(3).put(G_INGOT_MACHINE, MD.HBM),
		MagnetizedTungsten          = unused        ("Magnetized Tungsten"          ).setPriorityPrefix(3).put(G_INGOT, MD.HBM, MAGNETIC_ACTIVE),
		Euphemium                   = unused        ("Euphemium"                    ).setPriorityPrefix(3).put(G_INGOT, MD.HBM, MELTING, MOLTEN).setRGBa(255, 150, 255, 255),
		Plutonium240                = unused        ("Plutonium-240"                ).setPriorityPrefix(3).put(G_INGOT, MD.HBM),
		Plutonium238                = unused        ("Plutonium-238"                ).setPriorityPrefix(3).put(G_INGOT, MD.HBM),
		Polymer                     = unused        ("Polymer"                      ).put(MD.HBM),
		Energized                   = unused        ("Energized"                    ),
		Reinforced                  = unused        ("Reinforced"                   ),
		Mud                         = unused        ("Mud"                          ),
		Cluster                     = unused        ("Cluster"                      ),
		Sweet                       = unused        ("Sweet"                        ),
		Gelatine                    = unused        ("Gelatine"                     ),
		TarPitch                    = unused        ("Tar Pitch"                    ),
		Satinspar                   = unused        ("Satinspar"                    ),
		Selenite                    = unused        ("Selenite"                     ),
		Jet                         = unused        ("Jet"                          ),
		Microcline                  = unused        ("Microcline"                   ),
		Serpentine                  = unused        ("Serpentine"                   ),
		Sylvite                     = unused        ("Sylvite"                      ),
		Flux                        = unused        ("Flux"                         ),
		Goshen                      = unused        ("Goshen"                       ),
		Joshen                      = unused        ("Joshen"                       ),
		Itarius                     = unused        ("Itarius"                      ),
		Legendary                   = unused        ("Legendary"                    ),
		MutatedIron                 = unused        ("Mutated Iron"                 ),
		Witheria                    = unused        ("Witheria"                     ),
		RubberTreeSap               = unused        ("Rubber Tree Sap"              ),
		GraveyardDirt               = unused        ("Graveyard Dirt"               ),
		Cocaine                     = unused        ("Cocaine"                      ),
		Vile                        = unused        ("Vile"                         ),
		Dull                        = unused        ("Dull"                         ),
		Dark                        = unused        ("Dark"                         ),
		Soulium                     = unused        ("Soulium"                      ),
		Tennantite                  = unused        ("Tennantite"                   ),
		Alfium                      = unused        ("Alfium"                       ),
		Ryu                         = unused        ("Ryu"                          ),
		Mutation                    = unused        ("Mutation"                     ),
		EnrichedCopper              = unused        ("Enriched Copper"              ),
		DiamondCopper               = unused        ("Diamond Copper"               ),
		Fairy                       = unused        ("Fairy"                        ),
		Pokefennium                 = unused        ("Pokefennium"                  );
	}
}
