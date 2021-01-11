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
import gregapi.data.CS.ArmorsGT;
import gregapi.data.CS.BlocksGT;
import gregapi.data.CS.GarbageGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.CS.OreDictToolNames;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.render.IconContainerCopied;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.worldgen.StoneLayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

/**
 * @author Gregorius Techneticies
 * 
 * Loads the ItemList (IL.java) for most Items (everything except the GT ones).
 * This happens during the InitPhase, or if GregTech is installed within the PreInitPhase, since GregTech loads after all the Mods mentioned here, unlike the API.
 */
public class LoaderItemList implements Runnable {
	@Override public String toString() {return "Item List Loader";}
	
	@Override
	public void run() {
		GarbageGT.BLACKLIST.add(ST.make(MD.TC, "ItemThaumonomicon"              , 1,42));
		GarbageGT.BLACKLIST.add(ST.make(MD.RT, "opSpectreKey"                   , 1, W));
		
		ItemsGT.DEBUG_ITEMS.add(ST.make(MD.TC, "ItemThaumonomicon"              , 1,42));
		ItemsGT.DEBUG_ITEMS.add(ST.make(MD.RT, "opSpectreKey"                   , 1, W));
		
		ItemsGT.ILLEGAL_DROPS.add(ST.make(MD.TC, "ItemThaumonomicon"            , 1,42));
		ItemsGT.ILLEGAL_DROPS.add(ST.make(MD.RT, "opSpectreKey"                 , 1, W));
		
		ItemsGT.NON_AUTO_INSERT_ITEMS.add(ST.make(Items.arrow                   , 1, W));
		ItemsGT.NON_AUTO_INSERT_ITEMS.add(ST.make(Items.fire_charge             , 1, W));
		ItemsGT.NON_AUTO_INSERT_ITEMS.add(ST.make(MD.BWM, "bolt"                , 1, W));
		ItemsGT.NON_AUTO_INSERT_ITEMS.add(ST.make(MD.BWM, "bullet"              , 1, W));
		ItemsGT.NON_AUTO_INSERT_ITEMS.add(ST.make(MD.BWM, "shot"                , 1, W));
		ItemsGT.NON_AUTO_INSERT_ITEMS.add(ST.make(MD.BWM, "dart"                , 1, W));
		ItemsGT.NON_AUTO_INSERT_ITEMS.add(ST.make(MD.BWM, "dynamite"            , 1, W));
		ItemsGT.NON_AUTO_INSERT_ITEMS.add(ST.make(MD.BWM, "cannonball"          , 1, W));
		ItemsGT.NON_AUTO_INSERT_ITEMS.add(ST.make(MD.CANDY, "I42"               , 1, W));
		ItemsGT.NON_AUTO_INSERT_ITEMS.add(ST.make(MD.CANDY, "I85"               , 1, W));
		ItemsGT.NON_AUTO_INSERT_ITEMS.add(ST.make(MD.CANDY, "I89"               , 1, W));
		ItemsGT.NON_AUTO_INSERT_ITEMS.add(ST.make(MD.CANDY, "I100"              , 1, W));
		
		
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_12gauge"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_12gauge_incendiary"        , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_12gauge_shrapnel"          , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_12gauge_du"                , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_12gauge_marauder"          , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_20gauge"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_20gauge_slug"              , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_20gauge_flechette"         , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_20gauge_incendiary"        , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_20gauge_shrapnel"          , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_20gauge_explosive"         , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_20gauge_caustic"           , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_20gauge_shock"             , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_20gauge_wither"            , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_4gauge"                    , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_4gauge_slug"               , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_4gauge_explosive"          , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_5mm"                       , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_5mm_explosive"             , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_5mm_du"                    , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_5mm_star"                  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_9mm"                       , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_9mm_ap"                    , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_9mm_du"                    , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_9mm_rocket"                , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_556"                       , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_556_phosphorus"            , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_556_ap"                    , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_556_du"                    , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_556_star"                  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_556_tracer"                , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_556_flechette"             , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_556_flechette_incendiary"  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_556_flechette_phosphorus"  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_556_flechette_du"          , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_556_k"                     , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_50ae"                      , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_50ae_ap"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_50ae_du"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_50ae_star"                 , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_50bmg"                     , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_50bmg_incendiary"          , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_50bmg_phosphorus"          , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_50bmg_explosive"           , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_50bmg_ap"                  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_50bmg_du"                  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_50bmg_star"                , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_357_desh"                  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_44"                        , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_44_ap"                     , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_44_du"                     , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_44_phosphorus"             , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_44_star"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_44_pip"                    , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_44_bj"                     , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_44_silver"                 , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_44_rocket"                 , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_22lr"                      , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_22lr_ap"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_folly"                     , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_folly_nuclear"             , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_folly_du"                  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_rocket"                    , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_rocket_he"                 , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_rocket_incendiary"         , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_rocket_phosphorus"         , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_rocket_shrapnel"           , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_rocket_emp"                , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_rocket_glare"              , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_rocket_toxic"              , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_rocket_sleek"              , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_rocket_nuclear"            , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_rocket_rpc"                , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_grenade"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_grenade_he"                , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_grenade_incendiary"        , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_grenade_phosphorus"        , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_grenade_toxic"             , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_grenade_concussion"        , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_grenade_finned"            , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_grenade_sleek"             , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_grenade_nuclear"           , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_grenade_tracer"            , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_grenade_kampf"             , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_fuel"                      , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_fuel_napalm"               , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_fuel_phosphorus"           , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_fuel_vaporizer"            , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "ammo_fuel_gas"                  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_rpg_ammo"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_stinger_ammo"               , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_revolver_ammo"              , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_revolver_iron_ammo"         , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_revolver_gold_ammo"         , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_revolver_lead_ammo"         , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_revolver_schrabidium_ammo"  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_revolver_cursed_ammo"       , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_revolver_nightmare_ammo"    , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_revolver_nightmare2_ammo"   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_revolver_pip_ammo"          , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_revolver_nopip_ammo"        , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_calamity_ammo"              , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_lacunae_ammo"               , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_fatman_ammo"                , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_mirv_ammo"                  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_bf_ammo"                    , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_mp40_ammo"                  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_uzi_ammo"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_uboinik_ammo"               , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_lever_action_ammo"          , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_bolt_action_ammo"           , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_b92_ammo"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_xvl1456_ammo"               , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_osipr_ammo"                 , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_osipr_ammo2"                , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_immolator_ammo"             , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_cryolator_ammo"             , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_mp_ammo"                    , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_emp_ammo"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_jack_ammo"                  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_spark_ammo"                 , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_hp_ammo"                    , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_euthanasia_ammo"            , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_dash_ammo"                  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_twigun_ammo"                , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "gun_defabricator_ammo"          , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_revolver_iron"             , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_revolver"                  , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_revolver_gold"             , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_revolver_lead"             , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_revolver_schrabidium"      , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_revolver_cursed"           , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_revolver_nightmare"        , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_revolver_nightmare2"       , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_revolver_pip"              , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_revolver_nopip"            , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_rpg"                       , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_stinger"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_fatman"                    , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_mirv"                      , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_bf"                        , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_mp40"                      , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_uzi"                       , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_uboinik"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_lever_action"              , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_bolt_action"               , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_osipr"                     , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_immolator"                 , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_cryolator"                 , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_mp"                        , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_xvl1456"                   , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_emp"                       , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_jack"                      , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_spark"                     , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_hp"                        , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_euthanasia"                , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.HBM, "clip_defabricator"              , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(Items.arrow                              , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(Items.fire_charge                        , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.BWM, "bolt"                           , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.BWM, "bullet"                         , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.BWM, "shot"                           , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.BWM, "dart"                           , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.BWM, "dynamite"                       , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.BWM, "cannonball"                     , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.CANDY, "I42"                          , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.CANDY, "I85"                          , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.CANDY, "I89"                          , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.CANDY, "I100"                         , 1, W));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "stielGranate"                    , 1, 0));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "fragGrenade"                     , 1, 0));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1, 0));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1, 1));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1, 2));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1, 4));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1, 5));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1, 7));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1, 8));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,10));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,12));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,14));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,15));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,17));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,19));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,20));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,22));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,23));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,28));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,46));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,63));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,88));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,93));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,105));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,106));
		ItemsGT.AMMO_ITEMS.add(ST.make(MD.TG, "TechgunsAmmo"                    , 1,107));
		
		
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.coal_ore                     , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.diamond_ore                  , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.emerald_ore                  , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.gold_ore                     , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.iron_ore                     , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.lapis_ore                    , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.lit_redstone_ore             , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.redstone_ore                 , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.quartz_ore                   , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.dirt                         , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.grass                        , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.mycelium                     , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.gravel                       , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.sand                         , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.netherrack                   , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.nether_brick                 , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.end_stone                    , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.snow                         , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.ice                          , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.packed_ice                   , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.glowstone                    , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.redstone_lamp                , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.lit_redstone_lamp            , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.quartz_block                 , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.wool                         , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.glass                        , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.glass_pane                   , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.stained_glass                , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.stained_glass_pane           , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.clay                         , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.hardened_clay                , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.stained_hardened_clay        , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.leaves                       , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.leaves2                      , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.planks                       , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.log                          , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.log2                         , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.sandstone                    , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.stone                        , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.cobblestone                  , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.mossy_cobblestone            , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.stonebrick                   , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.brick_block                  , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.obsidian                     , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(Blocks.bedrock                      , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.EtFu, "crying_obsidian"          , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.NeLi, "CryingObsidian"           , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.NePl, "CryingObsidian"           , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.NePl, "AncientDebris"            , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.BTL, "bedrock"                   , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.OMT, "hardWallTierOne"           , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.OMT, "hardWallTierTwo"           , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.OMT, "hardWallTierThree"         , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.OMT, "hardWallTierFour"          , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.OMT, "hardWallTierFive"          , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.mkic("reinforcedGlass"                   , 1   ));
		ItemsGT.SHOW_RESISTANCE.add(ST.mkic("reinforcedStone"                   , 1   ));
		ItemsGT.SHOW_RESISTANCE.add(ST.mkic("constructionFoamWall"              , 1   ));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TG, "container"                  , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TG, "metalpanel"                 , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TG, "metalpanel2"                , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TG, "concrete"                   , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.HBM, "tile.reinforced_brick"     , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.HBM, "tile.reinforced_light"     , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.HBM, "tile.reinforced_glass"     , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.HBM, "tile.asphalt"              , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.HBM, "tile.brick_concrete"       , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.HBM, "tile.brick_obsidian"       , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.HBM, "tile.gravel_obsidian"      , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.HBM, "tile.cmb_brick"            , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.HBM, "tile.cmb_brick_reinforced" , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.HBM, "tile.factory_titanium_hull", 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.HBM, "tile.factory_advanced_hull", 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.HBM, "tile.block_meteor_cobble"  , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.HBM, "tile.crystal_hardened"     , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TC, "blockWarded"                , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.ICBM, "icbmCConcrete"            , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.ICBM, "icbmCRail"                , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.ICBM, "icbmCGlass"               , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.ICBM, "icbmCCamouflage"          , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.ELN, "Eln.Ore"                   , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.TFNagastone"           , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.TFMazestone"           , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.TFHedge"               , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.TFRoots"               , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.TFTowerStone"          , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.TFUnderBrick"          , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.AuroraBrick"           , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.AuroraPillar"          , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.TrollSteinn"           , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.TFDeadrock"            , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.CastleBrick"           , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.CastleMagic"           , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.ForceField"            , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.GiantLeaves"           , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.GiantCobble"           , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.GiantObsidian"         , 1, W));
		ItemsGT.SHOW_RESISTANCE.add(ST.make(MD.TF, "tile.GiantLog"              , 1, W));
		
		
		BlocksGT.harvestableSpade.add(ST.block(MD.EB, "enhancedbiomes.tile.dirtEB"  , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.EB, "enhancedbiomes.tile.grassEB" , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.TF, "tile.UberousSoil"            , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BoP, "mud"                        , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BoP, "driedDirt"                  , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BoP, "originGrass"                , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BoP, "longGrass"                  , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BoP, "bopGrass"                   , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BoP, "newBopDirt"                 , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BoP, "newBopGrass"                , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BoP, "overgrownNetherrack"        , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.ERE, "mud"                        , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BTL, "mud"                        , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BTL, "swampDirt"                  , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BTL, "swampGrass"                 , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BTL, "deadGrass"                  , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BTL, "slimyGrass"                 , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.BOTA, "altGrass"                  , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.AETHER, "aetherDirt"              , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.AETHER, "aetherGrass"             , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.AETHER, "enchantedAetherGrass"    , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.AETHER, "enchantedGrass"          , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.ABYSSAL, "darkgrass"              , null));
		BlocksGT.harvestableSpade.add(ST.block(MD.ABYSSAL, "dreadgrass"             , null));
		
		
		BlocksGT.plantableGreens.add(ST.block(MD.EB, "enhancedbiomes.tile.dirtEB"   , null));
		BlocksGT.plantableGreens.add(ST.block(MD.EB, "enhancedbiomes.tile.grassEB"  , null));
		BlocksGT.plantableGreens.add(ST.block(MD.TF, "tile.UberousSoil"             , null));
		BlocksGT.plantableGreens.add(ST.block(MD.BoP, "mud"                         , null));
		BlocksGT.plantableGreens.add(ST.block(MD.BoP, "originGrass"                 , null));
		BlocksGT.plantableGreens.add(ST.block(MD.BoP, "longGrass"                   , null));
		BlocksGT.plantableGreens.add(ST.block(MD.BoP, "bopGrass"                    , null));
		BlocksGT.plantableGreens.add(ST.block(MD.BoP, "newBopDirt"                  , null));
		BlocksGT.plantableGreens.add(ST.block(MD.BoP, "newBopGrass"                 , null));
		BlocksGT.plantableGreens.add(ST.block(MD.ERE, "mud"                         , null));
		BlocksGT.plantableGreens.add(ST.block(MD.BTL, "mud"                         , null));
		BlocksGT.plantableGreens.add(ST.block(MD.BTL, "swampDirt"                   , null));
		BlocksGT.plantableGreens.add(ST.block(MD.BTL, "swampGrass"                  , null));
		BlocksGT.plantableGreens.add(ST.block(MD.BTL, "slimyGrass"                  , null));
		BlocksGT.plantableGreens.add(ST.block(MD.BOTA, "altGrass"                   , null));
		BlocksGT.plantableGreens.add(ST.block(MD.AETHER, "aetherDirt"               , null));
		BlocksGT.plantableGreens.add(ST.block(MD.AETHER, "aetherGrass"              , null));
		BlocksGT.plantableGreens.add(ST.block(MD.AETHER, "enchantedAetherGrass"     , null));
		BlocksGT.plantableGreens.add(ST.block(MD.AETHER, "enchantedGrass"           , null));
		
		
		BlocksGT.plantableGrass.add(ST.block(MD.EB, "enhancedbiomes.tile.grassEB"   , null));
		BlocksGT.plantableGrass.add(ST.block(MD.BoP, "originGrass"                  , null));
		BlocksGT.plantableGrass.add(ST.block(MD.BoP, "longGrass"                    , null));
		BlocksGT.plantableGrass.add(ST.block(MD.BoP, "bopGrass"                     , null));
		BlocksGT.plantableGrass.add(ST.block(MD.BoP, "newBopGrass"                  , null));
		BlocksGT.plantableGrass.add(ST.block(MD.BTL, "swampGrass"                   , null));
		BlocksGT.plantableGrass.add(ST.block(MD.BTL, "slimyGrass"                   , null));
		BlocksGT.plantableGrass.add(ST.block(MD.BOTA, "altGrass"                    , null));
		BlocksGT.plantableGrass.add(ST.block(MD.AETHER, "aetherGrass"               , null));
		BlocksGT.plantableGrass.add(ST.block(MD.AETHER, "enchantedAetherGrass"      , null));
		BlocksGT.plantableGrass.add(ST.block(MD.AETHER, "enchantedGrass"            , null));
		
		
		BlocksGT.plantableTrees.add(ST.block(MD.EB, "enhancedbiomes.tile.grassEB"   , null));
		BlocksGT.plantableTrees.add(ST.block(MD.BoP, "originGrass"                  , null));
		BlocksGT.plantableTrees.add(ST.block(MD.BoP, "longGrass"                    , null));
		BlocksGT.plantableTrees.add(ST.block(MD.BoP, "bopGrass"                     , null));
		BlocksGT.plantableTrees.add(ST.block(MD.BoP, "newBopGrass"                  , null));
		BlocksGT.plantableTrees.add(ST.block(MD.BTL, "swampGrass"                   , null));
		BlocksGT.plantableTrees.add(ST.block(MD.BTL, "slimyGrass"                   , null));
		BlocksGT.plantableTrees.add(ST.block(MD.BOTA, "altGrass"                    , null));
		BlocksGT.plantableTrees.add(ST.block(MD.AETHER, "aetherGrass"               , null));
		BlocksGT.plantableTrees.add(ST.block(MD.AETHER, "enchantedAetherGrass"      , null));
		BlocksGT.plantableTrees.add(ST.block(MD.AETHER, "enchantedGrass"            , null));
		
		
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "glass_pane"                  , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "stained_glass_pane_brown"    , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "stained_glass_pane_red"      , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "stained_glass_pane_purple"   , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "stained_glass_pane_magenta"  , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "stained_glass_pane_yellow"   , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "stained_glass_pane_white"    , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "stained_glass_pane_pink"     , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "stained_glass_pane_lightgray", null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "stained_glass_brown"         , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "stained_glass_white"         , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "stained_glass_lightgray"     , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "stained_glass_yellow"        , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "glass"                       , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "glass2"                      , null));
		BlocksGT.breakableGlass.add(ST.block(MD.CHSL, "stained_glass_forestry"      , null));
		BlocksGT.breakableGlass.add(ST.block(MD.AETHER, "quicksoilGlass"            , null));
		BlocksGT.breakableGlass.add(ST.block(MD.AETHER, "quicksoilGlassPane"        , null));
		
		
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "EmptyLantern"                  , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "EmptyLanternEfrine"            , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "EmptyLanternGold"              , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "Lantern"                       , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "LanternEfrine"                 , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "LanternGold"                   , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "GlowstoneLantern"              , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "GlowstoneLanternEfrine"        , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "GlowstoneLanternGold"          , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "FoxfireLantern"                , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "FoxfireLanternEfrine"          , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "FoxfireLanternGold"            , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "SoulLantern"                   , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "SoulLanternEfrine"             , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "SoulLanternGold"               , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "RedstoneLantern"               , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "RedstoneLanternEfrine"         , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "RedstoneLanternGold"           , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "RedstoneLanternOn"             , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "RedstoneLanternEfrineOn"       , null));
		BlocksGT.instaharvest.add(ST.block(MD.NeLi, "RedstoneLanternGoldOn"         , null));
		
		
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.UB, "igneousStone"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.UB, "metamorphicStone"                 , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.UB, "sedimentaryStone"                 , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.UB, "igneousCobblestone"               , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.UB, "metamorphicCobblestone"           , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.PFAA, "weakStone"                      , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.PFAA, "mediumStone"                    , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.PFAA, "strongStone"                    , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.PFAA, "veryStrongStone"                , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.PFAA, "weakRubble"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.PFAA, "mediumCobble"                   , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.PFAA, "strongCobble"                   , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.PFAA, "veryStrongCobble"               , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.CHSL, "granite"                        , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.CHSL, "diorite"                        , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.CHSL, "andesite"                       , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.CHSL, "marble"                         , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.CHSL, "limestone"                      , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.EtFu, "stone"                          , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.GaSu, "18Stones"                       , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.GaSu, "basalt"                         , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.BOTA, "stone"                          , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IC2, "blockOreCopper"                  , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IC2, "blockOreLead"                    , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IC2, "blockOreTin"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IC2, "blockOreUran"                    , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IC2, "copperOre"                       , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IC2, "tinOre"                          , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IC2, "uraniumOre"                      , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.TG, "oreTitanIron"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.BR, "YelloriteOre"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.BoP, "gemOre"                          , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.BoP, "rocks"                           , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.EB, "enhancedbiomes.tile.stoneEB"      , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.EB, "enhancedbiomes.tile.stoneCobbleEB", null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.FR, "resources"                        , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.GC, "tile.gcBlockCore"                 , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.GC_GALAXYSPACE, "leadore"              , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.GC_GALAXYSPACE, "ores"                 , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IE, "ore"                              , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.TC, "blockCustomOre"                   , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.TE_FOUNDATION, "Ore"                   , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.ENVM, "flammablecoal"                  , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.HaC, "salt"                            , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.Mek, "OreBlock"                        , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.VULPES, "libVulpesore0"                , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MgC, "copper_ore"                      , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MgC, "uranium_ore"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MgC, "sulfur_ore"                      , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MgC, "salt_ore"                        , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MgC, "tungsten_ore"                    , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MgC, "zinc_ore"                        , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MgC, "thorium_ore"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreBischofite"                   , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreDatolite"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreStibnite"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreChromite"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreMica"                         , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreBauxite"                      , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreCinnabar"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreRockSalt"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreLimestone"                    , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreGypsum"                       , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreGyubnera"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreTrona"                        , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "orePotassiumFeldspar"            , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreApatite"                      , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.IHL, "oreSaltpeter"                    , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MF2, "oreCoalRich"                     , null)); // The other two MF2 Ore Blocks are not safe for removal due to lack of Material Data for them.
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MF2, "oreNitre"                        , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MF2, "oreSulfur"                       , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MF2, "oreTin"                          , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MF2, "oreCopper"                       , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MF2, "oreSilver"                       , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MF2, "oreTungsten"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MF2, "oreBorax"                        , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MF2, "oreKaolinite"                    , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "basalt"                          , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "granite"                         , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "andesite"                        , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "diorite"                         , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "limestone"                       , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "shale"                           , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "slate"                           , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "schist"                          , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "gneiss"                          , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "dolomite"                        , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "rhyolite"                        , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "pumice"                          , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "conglomerate"                    , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "pegmatite"                       , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "chert"                           , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "phosphorous_ore"                 , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "gypsum"                          , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "sulfur_ore"                      , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.MIN, "nitrate_ore"                     , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.ReC, "reactorcraft_block_fluoriteore"  , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.ReC, "reactorcraft_block_ore"          , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.ElC, "electricraft_block_ore"          , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.AA, "blockMisc"                        , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.HEX, "blockHexoriumOreRed"             , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.HEX, "blockHexoriumOreGreen"           , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.HEX, "blockHexoriumOreBlue"            , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.HEX, "blockHexoriumOreBlack"           , null));
		StoneLayer.REPLACEABLE_BLOCKS.add(ST.block(MD.HEX, "blockHexoriumOreWhite"           , null));
		
		
		IL.Bottle_Empty                         .set(ST.make(Items.glass_bottle, 1, 0));
		
		IL.Dye_Bonemeal                         .set(ST.make(Items.dye, 1, 15));
		IL.Dye_SquidInk                         .set(ST.make(Items.dye, 1, 0));
		IL.Dye_Cactus                           .set(ST.make(Items.dye, 1, 2));
		IL.Dye_Cocoa                            .set(ST.make(Items.dye, 1, 3), null, "cropCocoa");
		
		IL.Bale_Wheat                           .set(ST.make(Blocks.hay_block, 1, 0), null, "baleWheat");
		IL.Crop_Wheat                           .set(ST.make(Items.wheat, 1, 0), null, "cropWheat");
		IL.Food_Bread                           .set(ST.make(Items.bread, 1, 0), null, "foodBread");
		IL.Food_Carrot                          .set(ST.make(Items.carrot, 1, 0), null, "cropCarrot");
		IL.Food_Potato                          .set(ST.make(Items.potato, 1, 0), null, "cropPotato");
		IL.Food_Potato_Baked                    .set(ST.make(Items.baked_potato, 1, 0));
		IL.Food_Potato_Poisonous                .set(ST.make(Items.poisonous_potato, 1, 0));
		
		IL.TFC_Torch                            .set(ST.make(MD.TFCP.mLoaded?MD.TFCP:MD.TFC, "Torch"        , 1, 0), null, OD.blockTorch);
		IL.TFC_Stick                            .set(ST.make(MD.TFCP.mLoaded?MD.TFCP:MD.TFC, "item.stick"   , 1, 0));
		
		IL.Torch                                .set(IL.TFC_Torch.get(1, ST.make(Blocks.torch, 1, 0)));
		IL.Stick                                .set(IL.TFC_Stick.get(1, ST.make(Items.stick, 1, 0)));
		
		IL.Cell_Empty                           .set(ST.mkic("cell"                                         , 1   ), OP.cell.dat(MT.Empty));
		IL.Cell_Water                           .set(ST.mkic("waterCell"                                    , 1   ), OP.cell.dat(MT.H2O ), OD.container1000water);
		IL.Cell_Lava                            .set(ST.mkic("lavaCell"                                     , 1   ), OP.cell.dat(MT.Lava), OD.container1000lava);
		IL.Cell_Air                             .set(ST.mkic("airCell"                                      , 1   ), OP.cell.dat(MT.Air));
		OP.cell.mContainerItem =                     ST.mkic("cell"                                         , 1   , OreDictManager.INSTANCE.getFirstOre("cellEmpty", 1));
		
		
		IL.TE_Wrench                            .set(ST.make(MD.TE, "wrench"                                , 1, 0));
		IL.TE_Wrench_Battle                     .set(ST.make(MD.TE, "tool.battleWrenchInvar"                , 1, 0));
		IL.TE_Cinnabar                          .set(ST.make(MD.TE_FOUNDATION, "material"                   , 1,  20));
		IL.TE_Rod_Blizz                         .set(ST.make(MD.TE_FOUNDATION, "material"                   , 1,1024));
		IL.TE_Rod_Blitz                         .set(ST.make(MD.TE_FOUNDATION, "material"                   , 1,1026));
		IL.TE_Rod_Basalz                        .set(ST.make(MD.TE_FOUNDATION, "material"                   , 1,1028));
		IL.TE_Slag                              .set(ST.make(MD.TE, "material"                              , 1, 514), null, OD.itemSlag);
		IL.TE_Slag_Rich                         .set(ST.make(MD.TE, "material"                              , 1, 515), null, OD.itemSlag);
		IL.TE_Phyto_Gro                         .set(ST.make(MD.TE, "material"                              , 1, 516), null, OD.itemFertilizer);
		IL.TE_Phyto_Gro_Rich                    .set(ST.make(MD.TE, "material"                              , 1, 517), null, OD.itemFertilizer);
		
		
		IL.RC_ShuntingWire                      .set(ST.make(MD.RC, "machine.delta"                         , 1, 0));
		IL.RC_ShuntingWireFrame                 .set(ST.make(MD.RC, "frame"                                 , 1, 0));
		IL.RC_Post_Metal                        .set(ST.make(MD.RC, "post"                                  , 1, 2));
		IL.RC_Concrete                          .set(ST.make(MD.RC, "cube"                                  , 1, 1), new OreDictItemData(MT.Stone, 5*U8));
		IL.RC_Crushed_Obsidian                  .set(ST.make(MD.RC, "cube"                                  , 1, 4), new OreDictItemData(MT.Obsidian, U*8));
		IL.RC_Stone_Abyssal                     .set(ST.make(MD.RC, "cube"                                  , 1, 6), OP.stone.dat(MT.Basalt));
		IL.RC_Stone_Quarried                    .set(ST.make(MD.RC, "cube"                                  , 1, 7), OP.stone.dat(MT.Marble));
		IL.RC_Creosote_Wood                     .set(ST.make(MD.RC, "cube"                                  , 1, 8), new OreDictItemData(ANY.Wood, U*8));
		IL.RC_Rail_Standard                     .set(ST.make(MD.RC, "part.rail"                             , 1, 0));
		IL.RC_Rail_Adv                          .set(ST.make(MD.RC, "part.rail"                             , 1, 1));
		IL.RC_Rail_Wooden                       .set(ST.make(MD.RC, "part.rail"                             , 1, 2));
		IL.RC_Rail_HS                           .set(ST.make(MD.RC, "part.rail"                             , 1, 3));
		IL.RC_Rail_Reinforced                   .set(ST.make(MD.RC, "part.rail"                             , 1, 4));
		IL.RC_Rail_Electric                     .set(ST.make(MD.RC, "part.rail"                             , 1, 5));
		IL.RC_Tie_Wood                          .set(ST.make(MD.RC, "part.tie"                              , 1, 0), new OreDictItemData(ANY.Wood, 3*U2));
		IL.RC_Tie_Stone                         .set(ST.make(MD.RC, "part.tie"                              , 1, 1), new OreDictItemData(MT.Stone, 3*U2));
		IL.RC_Bed_Wood                          .set(ST.make(MD.RC, "part.railbed"                          , 1, 0), new OreDictItemData(ANY.Wood, U*6));
		IL.RC_Bed_Stone                         .set(ST.make(MD.RC, "part.railbed"                          , 1, 1), new OreDictItemData(MT.Stone, U*6));
		IL.RC_Rebar                             .set(ST.make(MD.RC, "part.rebar"                            , 1, 0));
		IL.RC_Firestone_Cut                     .set(ST.make(MD.RC, "firestone.cut"                         , 1, 0), new OreDictItemData(MT.Firestone, U));
		IL.RC_Crowbar_Iron                      .set(ST.make(MD.RC, "tool.crowbar"                          , 1, 0), new OreDictItemData(ANY.Fe, U*3).setUseVanillaDamage());
		IL.RC_Crowbar_Steel                     .set(ST.make(MD.RC, "tool.crowbar.reinforced"               , 1, 0), new OreDictItemData(ANY.Steel, U*3).setUseVanillaDamage());
		IL.RC_Crowbar_Thaumium                  .set(ST.make(MD.RC, "tool.crowbar.magic"                    , 1, 0), new OreDictItemData(MT.Thaumium, U*3).setUseVanillaDamage());
		IL.RC_Crowbar_Voidmetal                 .set(ST.make(MD.RC, "tool.crowbar.void"                     , 1, 0), new OreDictItemData(MT.VoidMetal, U*3).setUseVanillaDamage());
		IL.Tool_Sword_Steel                     .set(ST.make(MD.RC, "tool.steel.sword"                      , 1, 0), new OreDictItemData(ANY.Steel, OP.toolHeadSword  , ANY.Wood, U2).setUseVanillaDamage());
		IL.Tool_Pickaxe_Steel                   .set(ST.make(MD.RC, "tool.steel.pickaxe"                    , 1, 0), new OreDictItemData(ANY.Steel, OP.toolHeadPickaxe, ANY.Wood, U ).setUseVanillaDamage());
		IL.Tool_Shovel_Steel                    .set(ST.make(MD.RC, "tool.steel.shovel"                     , 1, 0), new OreDictItemData(ANY.Steel, OP.toolHeadShovel , ANY.Wood, U ).setUseVanillaDamage());
		IL.Tool_Axe_Steel                       .set(ST.make(MD.RC, "tool.steel.axe"                        , 1, 0), new OreDictItemData(ANY.Steel, OP.toolHeadAxe    , ANY.Wood, U ).setUseVanillaDamage());
		IL.Tool_Hoe_Steel                       .set(ST.make(MD.RC, "tool.steel.hoe"                        , 1, 0), new OreDictItemData(ANY.Steel, OP.toolHeadHoe    , ANY.Wood, U ).setUseVanillaDamage());
		IL.RC_Creosote_Bottle                   .set(ST.make(MD.RC, "fluid.creosote.bottle"                 , 1, 0), null, "bottleCreosote", OD.container1000creosote);
		IL.RC_Creosote_Bucket                   .set(ST.make(MD.RC, "fluid.creosote.bucket"                 , 1, 0), new OreDictItemData(ANY.Fe, U*3), "bucketCreosote", OD.container1000creosote);
		IL.RC_Creosote_Cell                     .set(ST.make(MD.RC, "fluid.creosote.cell"                   , 1, 0), null, OD.container1000creosote);
		IL.RC_Creosote_Can                      .set(ST.make(MD.RC, "fluid.creosote.can"                    , 1, 0), null, OD.container1000creosote);
		IL.RC_Creosote_Capsule                  .set(ST.make(MD.RC, "fluid.creosote.wax"                    , 1, 0), null, OD.container1000creosote);
		IL.RC_Creosote_RefractoryCapsule        .set(ST.make(MD.RC, "fluid.creosote.refactory"              , 1, 0), null, OD.container1000creosote);
		
		if (IL.RC_Creosote_Bottle.exists()) IL.RC_Creosote_Bottle.item().setContainerItem(Items.glass_bottle);
		if (IL.RC_Creosote_Bucket.exists()) IL.RC_Creosote_Bucket.item().setContainerItem(Items.bucket);
		if (IL.RC_Creosote_Cell.exists() && IL.Cell_Empty.exists()) IL.RC_Creosote_Cell.item().setContainerItem(IL.Cell_Empty.getItem());
		
		
		IL.IE_Creosote_Bottle                   .set(ST.make(MD.IE, "fluidContainers"                       , 1, 0), null, "bottleCreosote", OD.container1000creosote);
		IL.IE_Creosote_Bucket                   .set(ST.make(MD.IE, "fluidContainers"                       , 1, 1), new OreDictItemData(ANY.Fe, U*3), "bucketCreosote", OD.container1000creosote);
		IL.IE_Hammer                            .set(ST.make(MD.IE, "tool"                                  , 1, 0), new OreDictItemData(ANY.Fe, U*2, ANY.Wood, U));
		IL.IE_Slag                              .set(ST.make(MD.IE, "material"                              , 1,13), null, OD.itemSlag);
		IL.IE_Blueprint_Projectiles_Common      .set(ST.make(MD.IE, "blueprint"                             , 1, 0), new OreDictItemData(MT.Paper, 3*U));
		IL.IE_Blueprint_Projectiles_Specialized .set(ST.make(MD.IE, "blueprint"                             , 1, 1), new OreDictItemData(MT.Paper, 3*U));
		IL.IE_Blueprint_Projectiles_Electrodes  .set(ST.make(MD.IE, "blueprint"                             , 1, 2), new OreDictItemData(MT.Paper, 3*U));
		IL.IE_Treated_Planks                    .set(ST.make(MD.IE, "treatedWood"                           , 1, 0), new OreDictItemData(MT.WoodSealed, U));
		IL.IE_Treated_Slab                      .set(ST.make(MD.IE, "woodenDecoration"                      , 1, 2), new OreDictItemData(MT.WoodSealed, U2));
		IL.IE_Treated_Stairs                    .set(ST.make(MD.IE, "woodenStairs"                          , 1, 0), new OreDictItemData(MT.WoodSealed, 3*U4));
		IL.IE_Crate                             .set(ST.make(MD.IE, "woodenDevice"                          , 1, 4), new OreDictItemData(MT.WoodSealed, 8*U), OD.craftingChest);
		
		
		IL.FZ_Sludge                            .set(ST.make(MD.FZ, "sludge"                                , 1, 0), null, OD.itemSlag);
		
		
		IL.ABYSSAL_Crate                        .set(ST.make(MD.ABYSSAL, "Crate"                            , 1, 0), new OreDictItemData(ANY.Wood, 7*U), OD.craftingChest);
		IL.ABYSSAL_Lava                         .set(ST.make(MD.ABYSSAL, "solidlava"                        , 1, 0), new OreDictItemData(MT.Lava, 9*U));
		
		
		IL.Myst_Ink_Vial                        .set(ST.make(MD.MYST, "vial"                                , 1, 0));
		IL.Myst_Desk_Item                       .set(ST.make(MD.MYST, "writingdesk"                         , 1, 0));
		IL.Myst_Desk_Block                      .set(ST.make(MD.MYST, "WritingDesk"                         , 1, 0)); // 0L and 8R X POS, 1L and 9R Z NEG, 2L and 10R X NEG, 3L and 11R Z POS
		IL.Myst_Bookstand                       .set(ST.make(MD.MYST, "BlockBookstand"                      , 1, 0)); // Requires Rotation Calls
		IL.Myst_Lectern                         .set(ST.make(MD.MYST, "BlockLectern"                        , 1, 0)); // Requires Rotation Calls
		IL.Myst_Receptacle                      .set(ST.make(MD.MYST, "BlockBookReceptacle"                 , 1, 0)); // Front is using vanilla siding
		IL.Myst_Crystal                         .set(ST.make(MD.MYST, "BlockCrystal"                        , 1, 0));
		IL.Myst_Ink_Mixer                       .set(ST.make(MD.MYST, "BlockInkMixer"                       , 1, 0)); // Requires Rotation Calls
		IL.Myst_Book_Binder                     .set(ST.make(MD.MYST, "BlockBookBinder"                     , 1, 0)); // Requires Rotation Calls
		
		
		IL.LOOTBAGS_Bag_0                       .set(ST.make(MD.LOOTBAGS, "lootbag"                         , 1, 0));
		IL.LOOTBAGS_Bag_1                       .set(ST.make(MD.LOOTBAGS, "lootbag"                         , 1, 1));
		IL.LOOTBAGS_Bag_2                       .set(ST.make(MD.LOOTBAGS, "lootbag"                         , 1, 2));
		IL.LOOTBAGS_Bag_3                       .set(ST.make(MD.LOOTBAGS, "lootbag"                         , 1, 3));
		IL.LOOTBAGS_Bag_4                       .set(ST.make(MD.LOOTBAGS, "lootbag"                         , 1, 4));
		
		
		IL.CHSL_Present                         .set(ST.make(MD.CHSL, "present"                             , 1, W), new OreDictItemData(ANY.Wood, 8*U), OD.craftingChest);
		
		
		IL.BOTA_Paintslinger                    .set(ST.make(MD.BOTA, "lens"                                , 1,14));
		IL.BOTA_Red_String                      .set(ST.make(MD.BOTA, "manaResource"                        , 1,12), null, OD.itemString);
		IL.BOTA_Ender_Air_Bottle                .set(ST.make(MD.BOTA, "manaResource"                        , 1,15));
		IL.BOTA_Mana_String                     .set(ST.make(MD.BOTA, "manaResource"                        , 1,16), null, OD.itemString);
		
		
		IL.ALF_LivingCobble                     .set(ST.make(MD.ALF, "LivingCobble"                         , 1, 0), new OreDictItemData(MT.Livingrock, U), OP.cobblestone);
		IL.ALF_DreamWood                        .set(ST.make(MD.ALF, "DreamLog"                             , 1, 0), new OreDictItemData(MT.Dreamwood, U * 8, MT.Bark, U), OD.logWood);
		IL.ALF_DreamSapling                     .set(ST.make(MD.ALF, "DreamSapling"                         , 1, 0), null, OP.treeSapling);
		IL.ALF_DreamLeaves                      .set(ST.make(MD.ALF, "DreamLeaves"                          , 1, 0), null, OP.treeLeaves);
		IL.ALF_Ice                              .set(ST.make(MD.ALF, "NiflheimIce"                          , 1, 0), new OreDictItemData(MT.Ice, U));
		IL.ALF_Gateway_Core                     .set(ST.make(MD.ALF, "ElvenItems"                           , 1, 0), new OreDictItemData(MT.NetherStar, U));
		
		
		IL.TC_Nugget_Beef                       .set(ST.make(MD.TC, "ItemNuggetBeef"                        , 1, 0), new OreDictItemData(MT.MeatCooked, U9));
		IL.TC_Nugget_Chicken                    .set(ST.make(MD.TC, "ItemNuggetChicken"                     , 1, 0), new OreDictItemData(MT.MeatCooked, U9));
		IL.TC_Nugget_Fish                       .set(ST.make(MD.TC, "ItemNuggetFish"                        , 1, 0), new OreDictItemData(MT.FishCooked, U9));
		IL.TC_Nugget_Pork                       .set(ST.make(MD.TC, "ItemNuggetPork"                        , 1, 0), new OreDictItemData(MT.MeatCooked, U9));
		IL.TC_Triple_Meat_Treat                 .set(ST.make(MD.TC, "TripleMeatTreat"                       , 1, 0), new OreDictItemData(MT.MeatCooked, U3));
		IL.TC_Greatwood_Log                     .set(ST.make(MD.TC, "blockMagicalLog"                       , 1, 0), new OreDictItemData(MT.Greatwood , U * 8, MT.Bark, U), OD.logWood);
		IL.TC_Silverwood_Log                    .set(ST.make(MD.TC, "blockMagicalLog"                       , 1, 1), new OreDictItemData(MT.Silverwood, U * 8, MT.Bark, U), OD.logWood);
		IL.TC_Greatwood_Planks                  .set(ST.make(MD.TC, "blockWoodenDevice"                     , 1, 6), new OreDictItemData(MT.Greatwood , U), OP.plank.dat(MT.Greatwood));
		IL.TC_Silverwood_Planks                 .set(ST.make(MD.TC, "blockWoodenDevice"                     , 1, 7), new OreDictItemData(MT.Silverwood, U), OP.plank.dat(MT.Silverwood));
		IL.TC_Shimmerleaf                       .set(ST.make(MD.TC, "blockCustomPlant"                      , 1, 2), new OreDictItemData(MT.Hg, U), "flowerShimmerleaf");
		IL.TC_Cinderpearl                       .set(ST.make(MD.TC, "blockCustomPlant"                      , 1, 3), new OreDictItemData(MT.Blaze, U9), "flowerCinderpearl");
		IL.TC_Vishroom                          .set(ST.make(MD.TC, "blockCustomPlant"                      , 1, 5));
		IL.TC_Block_Amber                       .set(ST.make(MD.TC, "blockCosmeticOpaque"                   , 1, 0), new OreDictItemData(MT.Amber, 4*U));
		IL.TC_Block_Amber_Bricks                .set(ST.make(MD.TC, "blockCosmeticOpaque"                   , 1, 1), new OreDictItemData(MT.Amber, 4*U));
		IL.TC_Warded_Glass                      .set(ST.make(MD.TC, "blockCosmeticOpaque"                   , 1, 2));
		IL.TC_Thaumometer                       .set(ST.make(MD.TC, "ItemThaumometer"                       , 1, 0));
		IL.TC_Alumentum                         .set(ST.make(MD.TC, "ItemResource"                          , 1, 0));
		IL.TC_Nitor                             .set(ST.make(MD.TC, "ItemResource"                          , 1, 1));
		IL.TC_Tallow                            .set(ST.make(MD.TC, "ItemResource"                          , 1, 4), new OreDictItemData(MT.Tallow, U));
		IL.TC_Block_Tallow                      .set(ST.make(MD.TC, "blockCosmeticSolid"                    , 1, 5), new OreDictItemData(MT.Tallow, 9*U));
		IL.TC_Block_Flesh                       .set(ST.make(MD.TC, "blockTaint"                            , 1, 2), new OreDictItemData(MT.MeatRotten, 18*U, MT.Bone, U));
		IL.TC_Block_Air                         .set(ST.make(MD.TC, "blockAiry"                             , 1, 0));
		IL.TC_Phial                             .set(ST.make(MD.TC, "ItemEssence"                           , 1, 0), new OreDictItemData(ANY.Clay, U8, MT.Glass, 3*U8));
		IL.TC_Knowledge_Fragment                .set(ST.make(MD.TC, "ItemResource"                          , 1, 9), new OreDictItemData(MT.Paper, U9), "paperResearchFragment");
		IL.TC_Thaumonomicon                     .set(ST.make(MD.TC, "ItemThaumonomicon"                     , 1, 0), new OreDictItemData(MT.Paper, 9*U));
		IL.TC_Crimson_Rites                     .set(ST.make(MD.TC, "ItemEldritchObject"                    , 1, 1), new OreDictItemData(MT.Paper, 9*U));
		IL.TC_Bucket_Death                      .set(ST.make(MD.TC, "ItemBucketDeath"                       , 1, 0), new OreDictItemData(ANY.Fe, U*3), OD.itemPoison);
		IL.TC_Bucket_Pure                       .set(ST.make(MD.TC, "ItemBucketPure"                        , 1, 0), new OreDictItemData(ANY.Fe, U*3));
		
		if (IL.TC_Bucket_Death.exists()) IL.TC_Bucket_Death.item().setContainerItem(Items.bucket);
		if (IL.TC_Bucket_Pure .exists()) IL.TC_Bucket_Pure .item().setContainerItem(Items.bucket);
		
		
		IL.TF_LiveRoot                          .set(ST.make(MD.TF, "item.liveRoot"                         , 1, 0), new OreDictItemData(MT.LiveRoot, U));
		IL.TF_Torchberries                      .set(ST.make(MD.TF, "item.torchberries"                     , 1, 0));
		IL.TF_NagaScale                         .set(ST.make(MD.TF, "item.nagaScale"                        , 1, 0));
		IL.TF_BorerEssence                      .set(ST.make(MD.TF, "item.borerEssence"                     , 1, 0));
		IL.TF_Carminite                         .set(ST.make(MD.TF, "item.carminite"                        , 1, 0));
		IL.TF_Transformation_Powder             .set(ST.make(MD.TF, "item.transformPowder"                  , 1, 0));
		IL.TF_Vial_FieryBlood                   .set(ST.make(MD.TF, "item.fieryBlood"                       , 1, 0));
		IL.TF_Vial_FieryTears                   .set(ST.make(MD.TF, "item.fieryTears"                       , 1, 0));
		IL.TF_Hydrachop_Raw                     .set(ST.make(MD.TF, "item.hydraChop"                        , 1, 0), new OreDictItemData(MT.MeatRaw             , U*8, MT.Bone,U*2), "listAllhydraraw");
		IL.TF_Meef_Raw                          .set(ST.make(MD.TF, "item.meefRaw"                          , 1, 0), new OreDictItemData(MT.MeatRaw             , U*2, MT.Bone, U9), "listAllbeefraw");
		IL.TF_Meef_Cooked                       .set(ST.make(MD.TF, "item.meefSteak"                        , 1, 0), new OreDictItemData(MT.MeatCooked          , U*2, MT.Bone, U9), "listAllbeefcooked");
		IL.TF_Venison_Raw                       .set(ST.make(MD.TF, "item.venisonRaw"                       , 1, 0), new OreDictItemData(MT.MeatRaw             , U*2, MT.Bone, U9), "foodVenisonraw");
		IL.TF_Venison_Cooked                    .set(ST.make(MD.TF, "item.venisonCooked"                    , 1, 0), new OreDictItemData(MT.MeatCooked          , U*2, MT.Bone, U9), "foodVenisoncooked");
		IL.TF_Lamp_of_Cinders                   .set(ST.make(MD.TF, "item.lampOfCinders"                    , 1, 0), null, OD.craftingFirestarter, OreDictToolNames.flintandtinder);
		IL.TF_Cube_of_Annihilation              .set(ST.make(MD.TF, "item.cubeOfAnnihilation"               , 1, 0));
		IL.TF_Pick_Giant                        .set(ST.make(MD.TF, "item.giantPick"                        , 1, 0), new OreDictItemData(MT.Stone, U*192, MT.WOODS.Oak, U*1024, MT.Bark, U*128).setUseVanillaDamage());
		IL.TF_Sword_Giant                       .set(ST.make(MD.TF, "item.giantSword"                       , 1, 0), new OreDictItemData(MT.Stone, U*128, MT.WOODS.Oak, U* 512, MT.Bark, U* 64).setUseVanillaDamage());
		IL.TF_Fiddlehead                        .set(ST.make(MD.TF, "tile.TFPlant"                          , 1, 8));
		IL.TF_Mushgloom                         .set(ST.make(MD.TF, "tile.TFPlant"                          , 1, 9), new OreDictItemData(MT.Glowstone, U4), OD.listAllmushroom);
		IL.TF_Tall_Grass                        .set(ST.make(MD.TF, "tile.TFPlant"                          , 1,10), null, OD.itemGrassTall);
		IL.TF_Dry_Bush                          .set(ST.make(MD.TF, "tile.TFPlant"                          , 1,11));
		IL.TF_Roots                             .set(ST.make(MD.TF, "tile.TFRoots"                          , 1, 0), new OreDictItemData(MT.Wood, U*3));
		IL.TF_Liveroots                         .set(ST.make(MD.TF, "tile.TFRoots"                          , 1, 1), new OreDictItemData(MT.LiveRoot, U*2, MT.Wood, U));
		IL.TF_Deadrock_Weathered                .set(ST.make(MD.TF, "tile.TFDeadrock"                       , 1, 0), OP.stone.dat(MT.Deadrock));
		IL.TF_Deadrock_Cracked                  .set(ST.make(MD.TF, "tile.TFDeadrock"                       , 1, 1), OP.stone.dat(MT.Deadrock));
		IL.TF_Deadrock                          .set(ST.make(MD.TF, "tile.TFDeadrock"                       , 1, 2), OP.stone.dat(MT.Deadrock));
		IL.TF_Trollsteinn                       .set(ST.make(MD.TF, "tile.TrollSteinn"                      , 1, 0), OP.stone.dat(MT.Stone));
		IL.TF_Nagastone                         .set(ST.make(MD.TF, "tile.TFNagastone"                      , 1, 0), OP.stone.dat(MT.Stone));
		IL.TF_Mazestone                         .set(ST.make(MD.TF, "tile.TFMazestone"                      , 1, 0), OP.stone.dat(MT.Stone));
		IL.TF_Mazehedge                         .set(ST.make(MD.TF, "tile.TFHedge"                          , 1, 0));
		IL.TF_Uncrafting                        .set(ST.make(MD.TF, "tile.TFUncraftingTable"                , 1, 0));
		IL.TF_Giant_Leaves                      .set(ST.make(MD.TF, "tile.GiantLeaves"                      , 1, 0));
		IL.TF_Giant_Cobble                      .set(ST.make(MD.TF, "tile.GiantCobble"                      , 1, 0), new OreDictItemData(MT.Stone          , U *64));
		IL.TF_Giant_Obsidian                    .set(ST.make(MD.TF, "tile.GiantObsidian"                    , 1, 0), new OreDictItemData(MT.Obsidian       , U*576));
		IL.TF_Giant_Log                         .set(ST.make(MD.TF, "tile.GiantLog"                         , 1, 0), new OreDictItemData(MT.WOODS.Oak      , U*512, MT.Bark, U*64));
		IL.TF_Log_Oak                           .set(ST.make(MD.TF, "tile.TFLog"                            , 1, 0), new OreDictItemData(MT.WOODS.Oak      , U*8, MT.Bark, U), OD.logWood);
		IL.TF_Log_Canopy                        .set(ST.make(MD.TF, "tile.TFLog"                            , 1, 1), new OreDictItemData(MT.WOODS.Spruce   , U*8, MT.Bark, U), OD.logWood);
		IL.TF_Log_Mangrove                      .set(ST.make(MD.TF, "tile.TFLog"                            , 1, 2), new OreDictItemData(MT.WOODS.Birch    , U*8, MT.Bark, U), OD.logWood);
		IL.TF_Log_Darkwood                      .set(ST.make(MD.TF, "tile.TFLog"                            , 1, 3), new OreDictItemData(MT.WOODS.Towerwood, U*8, MT.Bark, U), OD.logWood);
		IL.TF_Log_Time                          .set(ST.make(MD.TF, "tile.TFMagicLog"                       , 1, 0), new OreDictItemData(MT.WOODS.Spruce   , U*8, MT.Bark, U), OD.logWood);
		IL.TF_Log_Trans                         .set(ST.make(MD.TF, "tile.TFMagicLog"                       , 1, 1), new OreDictItemData(MT.WOODS.Acacia   , U*8, MT.Bark, U), OD.logWood);
		IL.TF_Log_Mine                          .set(ST.make(MD.TF, "tile.TFMagicLog"                       , 1, 2), new OreDictItemData(MT.WOODS.Birch    , U*8, MT.Bark, U), OD.logWood);
		IL.TF_Log_Sorting                       .set(ST.make(MD.TF, "tile.TFMagicLog"                       , 1, 3), new OreDictItemData(MT.WOODS.DarkOak  , U*8, MT.Bark, U), OD.logWood);
		IL.TF_Core_Time                         .set(ST.make(MD.TF, "tile.TFMagicLogSpecial"                , 1, 0), new OreDictItemData(MT.WOODS.Spruce   , U*8, MT.Bark, U), OD.logWood);
		IL.TF_Core_Trans                        .set(ST.make(MD.TF, "tile.TFMagicLogSpecial"                , 1, 1), new OreDictItemData(MT.WOODS.Acacia   , U*8, MT.Bark, U), OD.logWood);
		IL.TF_Core_Mine                         .set(ST.make(MD.TF, "tile.TFMagicLogSpecial"                , 1, 2), new OreDictItemData(MT.WOODS.Birch    , U*8, MT.Bark, U), OD.logWood);
		IL.TF_Core_Sorting                      .set(ST.make(MD.TF, "tile.TFMagicLogSpecial"                , 1, 3), new OreDictItemData(MT.WOODS.DarkOak  , U*8, MT.Bark, U), OD.logWood);
		IL.TF_Trophy                            .set(ST.make(MD.TF, "tile.TFTrophy"                         , 1, 0));
		IL.TF_Trophy_Hydra                      .set(ST.make(MD.TF, "item.trophy"                           , 1, 0));
		IL.TF_Trophy_Naga                       .set(ST.make(MD.TF, "item.trophy"                           , 1, 1));
		IL.TF_Trophy_Lich                       .set(ST.make(MD.TF, "item.trophy"                           , 1, 2));
		IL.TF_Trophy_Urghast                    .set(ST.make(MD.TF, "item.trophy"                           , 1, 3));
		IL.TF_Trophy_Snowqueen                  .set(ST.make(MD.TF, "item.trophy"                           , 1, 4));
		
		// These Bottles should actually have an empty Variant.
		if (IL.TF_Vial_FieryBlood.exists()) IL.TF_Vial_FieryBlood.item().setContainerItem(Items.glass_bottle);
		if (IL.TF_Vial_FieryTears.exists()) IL.TF_Vial_FieryTears.item().setContainerItem(Items.glass_bottle);
		// Make this work as infinite Lighter in Crafting Recipes. It is absolutely useless otherwise since the Final Castle isn't finished.
		if (IL.TF_Lamp_of_Cinders.exists()) IL.TF_Lamp_of_Cinders.item().setContainerItem(IL.TF_Lamp_of_Cinders.getItem());
		
		
		IL.RH_Sand_Magnetite                    .set(ST.make(MD.RH, "globbypotato_rockhounding_beachSands"  , 1, 0), new OreDictItemData(MT.OREMATS.Magnetite, U));
		IL.RH_Sand_Olivine                      .set(ST.make(MD.RH, "globbypotato_rockhounding_beachSands"  , 1, 1), new OreDictItemData(MT.Olivine, U));
		IL.RH_Sand_Coral                        .set(ST.make(MD.RH, "globbypotato_rockhounding_beachSands"  , 1, 2), new OreDictItemData(MT.Sand, U));
		IL.RH_Sand_Gypsum                       .set(ST.make(MD.RH, "globbypotato_rockhounding_beachSands"  , 1, 3), new OreDictItemData(MT.Gypsum, U));
		
		
		IL.TROPIC_Sand_Coral                    .set(ST.make(MD.TROPIC, "tile.mineralSand"                  , 1, 0), new OreDictItemData(MT.Sand, U));
		IL.TROPIC_Sand_Foamy                    .set(ST.make(MD.TROPIC, "tile.mineralSand"                  , 1, 1), new OreDictItemData(MT.Sand, U));
		IL.TROPIC_Sand_Black                    .set(ST.make(MD.TROPIC, "tile.mineralSand"                  , 1, 2), new OreDictItemData(MT.OREMATS.Magnetite, U));
		IL.TROPIC_Sand_Mineral                  .set(ST.make(MD.TROPIC, "tile.mineralSand"                  , 1, 3), new OreDictItemData(MT.OREMATS.Cassiterite, U));
		IL.TROPIC_Sand_Pure                     .set(ST.make(MD.TROPIC, "tile.purifiedSand"                 , 1, 0), new OreDictItemData(MT.Sand, U));
		IL.TROPIC_Log_Palm                      .set(ST.make(MD.TROPIC, "tile.log"                          , 1, 0), new OreDictItemData(MT.WOODS.Palm    , U*8, MT.Bark, U), OD.logWood);
		IL.TROPIC_Log_Mahogany                  .set(ST.make(MD.TROPIC, "tile.log"                          , 1, 1), new OreDictItemData(MT.WOODS.Mahogany, U*8, MT.Bark, U), OD.logWood);
		IL.TROPIC_Bamboo                        .set(ST.make(MD.TROPIC, "bambooChute"                       , 1, 0), new OreDictItemData(MT.Bamboo, U), OD.bamboo);
		IL.TROPIC_Stick                         .set(ST.make(MD.TROPIC, "bambooStick"                       , 1, 0), OP.stick.dat(MT.Bamboo), OD.bamboo);
		IL.TROPIC_Chest                         .set(ST.make(MD.TROPIC, "tile.bambooChest"                  , 1, 0), new OreDictItemData(MT.Bamboo, 8*U), OD.craftingChest);
		IL.TROPIC_Sapling_Palm                  .set(ST.make(MD.TROPIC, "tile.sapling"                      , 1, 0), null, OP.treeSapling);
		IL.TROPIC_Sapling_Mahogany              .set(ST.make(MD.TROPIC, "tile.sapling"                      , 1, 1), null, OP.treeSapling);
		IL.TROPIC_Sapling_Grapefruit            .set(ST.make(MD.TROPIC, "tile.sapling"                      , 1, 2), null, OP.treeSapling);
		IL.TROPIC_Sapling_Lemon                 .set(ST.make(MD.TROPIC, "tile.sapling"                      , 1, 3), null, OP.treeSapling);
		IL.TROPIC_Sapling_Lime                  .set(ST.make(MD.TROPIC, "tile.sapling"                      , 1, 4), null, OP.treeSapling);
		IL.TROPIC_Sapling_Orange                .set(ST.make(MD.TROPIC, "tile.sapling"                      , 1, 5), null, OP.treeSapling);
		IL.TROPIC_Leaves_Palm                   .set(ST.make(MD.TROPIC, "tile.leafPalm"                     , 1, 0), null, OP.treeLeaves);
		IL.TROPIC_Leaves_Grapefruit             .set(ST.make(MD.TROPIC, "tile.leaf"                         , 1, 0), null, OP.treeLeaves);
		IL.TROPIC_Leaves_Lemon                  .set(ST.make(MD.TROPIC, "tile.leaf"                         , 1, 1), null, OP.treeLeaves);
		IL.TROPIC_Leaves_Lime                   .set(ST.make(MD.TROPIC, "tile.leaf"                         , 1, 2), null, OP.treeLeaves);
		IL.TROPIC_Leaves_Orange                 .set(ST.make(MD.TROPIC, "tile.leaf"                         , 1, 3), null, OP.treeLeaves);
		IL.TROPIC_Leaves_Kapok                  .set(ST.make(MD.TROPIC, "tile.leafRainforest"               , 1, 0), null, OP.treeLeaves);
		IL.TROPIC_Leaves_Mahogany               .set(ST.make(MD.TROPIC, "tile.leafRainforest"               , 1, 1), null, OP.treeLeaves);
		IL.TROPIC_Leaves_Fruit                  .set(ST.make(MD.TROPIC, "tile.leafRainforest"               , 1, 2), null, OP.treeLeaves);
		
		
		IL.CANDY_Comb                           .set(ST.make(MD.CANDY, "I37"                                , 1, 0), null, OD.beeComb, OD.materialHoneycomb, "foodFilledhoneycomb");
		IL.CANDY_Chest                          .set(ST.make(MD.CANDY, "B56X"                               , 1, 0), new OreDictItemData(MT.Marshmallow, 8*U), OD.craftingChest);
		IL.CANDY_Sapling_Chocolate              .set(ST.make(MD.CANDY, "B7"                                 , 1, 0), new OreDictItemData(MT.Chocolate, U2), OP.treeSapling);
		IL.CANDY_Sapling_Caramel                .set(ST.make(MD.CANDY, "B7"                                 , 1, 1), new OreDictItemData(MT.Sugar    , U2), OP.treeSapling);
		IL.CANDY_Sapling_White                  .set(ST.make(MD.CANDY, "B7"                                 , 1, 2), new OreDictItemData(MT.Chocolate, U2), OP.treeSapling);
		IL.CANDY_Sapling_Cherry                 .set(ST.make(MD.CANDY, "B7"                                 , 1, 3), null, OP.treeSapling);
		IL.CANDY_Leaves_Chocolate               .set(ST.make(MD.CANDY, "B6"                                 , 1, 0), new OreDictItemData(MT.Chocolate, U2), OP.treeLeaves);
		IL.CANDY_Leaves_Caramel                 .set(ST.make(MD.CANDY, "B6"                                 , 1, 1), new OreDictItemData(MT.Sugar    , U2), OP.treeLeaves);
		IL.CANDY_Leaves_White                   .set(ST.make(MD.CANDY, "B6"                                 , 1, 2), new OreDictItemData(MT.Chocolate, U2), OP.treeLeaves);
		IL.CANDY_Leaves_Cherry                  .set(ST.make(MD.CANDY, "B6"                                 , 1, 3), null, OP.treeLeaves);
		IL.CANDY_Log                            .set(ST.make(MD.CANDY, "B4"                                 , 1, 0), new OreDictItemData(MT.Marshmallow, 9*U), OD.logWood);
		IL.CANDY_Log_Dark                       .set(ST.make(MD.CANDY, "B4"                                 , 1, 1), new OreDictItemData(MT.Marshmallow, 9*U), OD.logWood);
		IL.CANDY_Log_Light                      .set(ST.make(MD.CANDY, "B4"                                 , 1, 2), new OreDictItemData(MT.Marshmallow, 9*U), OD.logWood);
		IL.CANDY_Plank                          .set(ST.make(MD.CANDY, "B3"                                 , 1, 0), new OreDictItemData(MT.Marshmallow, U), OP.plank.dat(MT.Marshmallow));
		IL.CANDY_Plank_Dark                     .set(ST.make(MD.CANDY, "B3"                                 , 1, 1), new OreDictItemData(MT.Marshmallow, U), OP.plank.dat(MT.Marshmallow));
		IL.CANDY_Plank_Light                    .set(ST.make(MD.CANDY, "B3"                                 , 1, 2), new OreDictItemData(MT.Marshmallow, U), OP.plank.dat(MT.Marshmallow));
		
		
		IL.ERE_Umberstone                       .set(ST.make(MD.ERE, "umberstone"                           , 1, 0), OP.stone.dat(MT.Umber));
		IL.ERE_Umbercobble                      .set(ST.make(MD.ERE, "umberstone"                           , 1, 1), OP.stone.dat(MT.Umber));
		IL.ERE_Mud_Brick                        .set(ST.make(MD.ERE, "materials"                            , 1,23));
		IL.ERE_Spray_Repellant                  .set(ST.make(MD.ERE, "sprayCan"                             , 1, 0));
		IL.ERE_Herbicide                        .set(ST.make(MD.ERE, "planticide"                           , 1, 0));
		IL.ERE_Compost                          .set(ST.make(MD.ERE, "compost"                              , 1, 0), null, OD.itemFertilizer);
		IL.ERE_Bamboo                           .set(ST.make(MD.ERE, "materials"                            , 1, 3), new OreDictItemData(MT.Bamboo, U4), OD.bamboo);
		IL.ERE_Gaean_Gem                        .set(ST.make(MD.ERE, "materials"                            , 1,39));
		IL.ERE_Gaean_Staff                      .set(ST.make(MD.ERE, "portalActivator"                      , 1, 0));
		IL.ERE_White_Planks                     .set(ST.make(MD.ERE, "planks"                               , 1, 7));
		IL.ERE_White_Slab                       .set(ST.make(MD.ERE, "slabPlanksWhite"                      , 1, 0));
		IL.ERE_White_Stairs                     .set(ST.make(MD.ERE, "plankStairWhite"                      , 1, 0));
		IL.ERE_Pole                             .set(ST.make(MD.ERE, "bambooPole"                           , 1, 0), new OreDictItemData(MT.Bamboo, U8));
		IL.ERE_Ladder                           .set(ST.make(MD.ERE, "bambooLadder"                         , 1, 0), new OreDictItemData(MT.Bamboo, 6*U4));
		IL.ERE_Crate                            .set(ST.make(MD.ERE, "bambooCrate"                          , 1, 0), new OreDictItemData(MT.Bamboo, 5*U), OD.craftingChest);
		IL.ERE_Bambucket_Empty                  .set(ST.make(MD.ERE, "bambucket"                            , 1, 0), new OreDictItemData(MT.Bamboo, 3*U4));
		IL.ERE_Bambucket_Water                  .set(ST.make(MD.ERE, "bambucketWater"                       , 1, 0), new OreDictItemData(MT.Bamboo, 3*U4), OD.container1000water);
		IL.ERE_Bambucket_Milk                   .set(ST.make(MD.ERE, "bambucketMilk"                        , 1, 0), new OreDictItemData(MT.Bamboo, 3*U4), OD.container1000milk);
		IL.ERE_Bambucket_Honey                  .set(ST.make(MD.ERE, "bambucketHoney"                       , 1, 0), new OreDictItemData(MT.Bamboo, 3*U4), OD.container1000honey);
		IL.ERE_Bambucket_AntiVenom              .set(ST.make(MD.ERE, "bambucketAntiVenom"                   , 1, 0), new OreDictItemData(MT.Bamboo, 3*U4));
		IL.ERE_Bambucket_FormicAcid             .set(ST.make(MD.ERE, "bambucketFormicAcid"                  , 1, 0), new OreDictItemData(MT.Bamboo, 3*U4));
		IL.ERE_Bambucket_BeetleJuice            .set(ST.make(MD.ERE, "bambucketBeetleJuice"                 , 1, 0), new OreDictItemData(MT.Bamboo, 3*U4));
		IL.ERE_Pot                              .set(ST.make(MD.ERE, "materials"                            , 1,62), new OreDictItemData(ANY.Fe, 7*U));
		IL.ERE_Pot_Raw                          .set(ST.make(MD.ERE, "materials"                            , 1,63), new OreDictItemData(ANY.Fe, 7*U));
		IL.ERE_Pot_Cooked                       .set(ST.make(MD.ERE, "food"                                 , 1,16), new OreDictItemData(ANY.Fe, 7*U));
		
		
		IL.BTL_Swamp_Talisman                   .set(ST.make(MD.BTL, "swampTalisman"                        , 1, 0));
		IL.BTL_Tainted_Potion                   .set(ST.make(MD.BTL, "taintedPotion"                        , 1, 0));
		IL.BTL_Betweenstone                     .set(ST.make(MD.BTL, "betweenstone"                         , 1, 0), OP.stone.dat(MT.Betweenstone));
		IL.BTL_Pitstone                         .set(ST.make(MD.BTL, "pitstone"                             , 1, 0), OP.stone.dat(MT.Pitstone));
		IL.BTL_Bedrock                          .set(ST.make(MD.BTL, "bedrock"                              , 1, 0), OP.stone.dat(MT.Bedrock));
		IL.BTL_Chest                            .set(ST.make(MD.BTL, "weedwoodChest"                        , 1, 0), new OreDictItemData(MT.Weedwood, 8*U), OD.craftingChest);
		IL.BTL_Weedwood_Leaves                  .set(ST.make(MD.BTL, "weedwoodLeaves"                       , 1, 0), null, OP.treeLeaves);
		IL.BTL_Weedwood_Sapling                 .set(ST.make(MD.BTL, "saplingWeedwood"                      , 1, 0), null, OP.treeSapling);
		IL.BTL_Weedwood_Planks                  .set(ST.make(MD.BTL, "weedwoodPlanks"                       , 1, 0), new OreDictItemData(MT.Weedwood, U), OD.plankWeedwood);
		IL.BTL_Weedwood_Beam                    .set(ST.make(MD.BTL, "weedwood"                             , 1, 0), new OreDictItemData(MT.Weedwood, U*8), OD.beamWood);
		IL.BTL_Weedwood_Log                     .set(ST.make(MD.BTL, "weedwoodLog"                          , 1, 0), new OreDictItemData(MT.Weedwood, U*8, MT.Bark, U)); OM.reg(IL.BTL_Weedwood_Log.wild(1), OD.logWood);
		IL.BTL_Weedwood_Bark                    .set(ST.make(MD.BTL, "weedwoodBark"                         , 1, 0), new OreDictItemData(MT.Bark, U*9));
		IL.BTL_Weedwood_RottenBark              .set(ST.make(MD.BTL, "rottenWeedwoodBark"                   , 1, 0), new OreDictItemData(MT.Bark, U));
		IL.BTL_Portal_Bark                      .set(ST.make(MD.BTL, "portalBark"                           , 1, 0), new OreDictItemData(MT.Bark, U*9));
		IL.BTL_Bark                             .set(ST.make(MD.BTL, "groundStuff"                          , 1,16), OP.dust.dat(MT.Bark));
		IL.BTL_Resin                            .set(ST.make(MD.BTL, "sapBall"                              , 1, 0), null, OD.itemResin);
		IL.BTL_Rubber                           .set(ST.make(MD.BTL, "unknownGeneric"                       , 1,26), new OreDictItemData(MT.Rubber, U));
		IL.BTL_Skin                             .set(ST.make(MD.BTL, "unknownGeneric"                       , 1, 7), null, OD.itemSkin);
		IL.BTL_Tar                              .set(ST.make(MD.BTL, "unknownGeneric"                       , 1,29), null, OD.itemTar);
		IL.BTL_Dry_Bark                         .set(ST.make(MD.BTL, "unknownGeneric"                       , 1,16), null, OD.itemBarkDry);
		
		
		IL.AETHER_Chest                         .set(ST.make(MD.AETHER, "skyrootChest"                      , 1, 0), new OreDictItemData(MT.Skyroot, 8*U), OD.craftingChest);
		IL.AETHER_Skyroot_Planks                .set(ST.make(MD.AETHER, "skyrootPlank"                      , 1, 0), new OreDictItemData(MT.Skyroot, U), OD.plankSkyroot);
		IL.AETHER_Skyroot_Log                   .set(ST.make(MD.AETHER, "aetherLog"                         , 1, 0), new OreDictItemData(MT.Skyroot, U*8, MT.Bark, U));
		IL.AETHER_Skyroot_Log_Gold              .set(ST.make(MD.AETHER, "aetherLog"                         , 1, 2), new OreDictItemData(MT.Skyroot, U*8, MT.Bark, U)); OM.reg(IL.AETHER_Skyroot_Log.wild(1), OD.logWood);
		IL.AETHER_Skyroot_Log_Small             .set(ST.make(MD.AETHER, "skyrootLogWall"                    , 1, 0), new OreDictItemData(MT.Skyroot, U*8, MT.Bark, U)); OM.reg(IL.AETHER_Skyroot_Log_Small.wild(1), OD.logWood);
		IL.AETHER_Bowl                          .set(ST.make(MD.AETHER, "shoyrootBowl"                      , 1, 0), new OreDictItemData(MT.Skyroot, U));
		IL.AETHER_Bucket_Empty                  .set(ST.make(MD.AETHER, "skyrootBucket"                     , 1, 0), new OreDictItemData(MT.Skyroot, U*3));
		IL.AETHER_Bucket_Water                  .set(ST.make(MD.AETHER, "skyrootWaterBucket"                , 1, 0), new OreDictItemData(MT.Skyroot, U*3), OD.container1000water);
		IL.AETHER_Bucket_Milk                   .set(ST.make(MD.AETHER, "skyrootMilkBucket"                 , 1, 0), new OreDictItemData(MT.Skyroot, U*3), OD.container1000milk);
		IL.AETHER_Bucket_Poison                 .set(ST.make(MD.AETHER, "skyrootPoisonBucket"               , 1, 0), new OreDictItemData(MT.Skyroot, U*3), OD.container1000poison);
		IL.AETHER_Torch_Ambrosium               .set(ST.make(MD.AETHER, "ambrosiumTorch"                    , 1, 0), new OreDictItemData(MT.Ambrosium, U8, MT.Skyroot, U16), OD.blockTorch);
		IL.AETHER_Apple                         .set(ST.make(MD.AETHER, "whiteApple"                        , 1, 0), null, "cropAppleWhite");
		IL.AETHER_Tall_Grass                    .set(ST.make(MD.AETHER, "tallAetherGrass"                   , 1, 0), null, OD.itemGrassTall);
		IL.AETHER_Sand                          .set(ST.make(MD.AETHER, "quicksoil"                         , 1, 0), new OreDictItemData(MT.Sand, U), OD.sand);
		IL.AETHER_Glass                         .set(ST.make(MD.AETHER, "quicksoilGlass"                    , 1, 0), null, OD.blockGlassColorless);
		IL.AETHER_Glass_Pane                    .set(ST.make(MD.AETHER, "quicksoilGlassPane"                , 1, 0), null, OD.paneGlassColorless);
		IL.AETHER_Dirt                          .set(ST.make(MD.AETHER, "aetherDirt"                        , 1, 0));
		IL.AETHER_Grass                         .set(ST.make(MD.AETHER, "aetherGrass"                       , 1, 0));
		IL.AETHER_Grass_Enchanted               .set(ST.make(MD.AETHER, "enchantedAetherGrass"              , 1, 0));
		IL.AETHER_Grass_Enchanted_Vanilla       .set(ST.make(MD.AETHER, "enchantedGrass"                    , 1, 0));
		IL.AETHER_Skyroot_Sapling_Gold          .set(ST.make(MD.AETHER, "goldenOakSapling"                  , 1, 0), null, OP.treeSapling);
		IL.AETHER_Skyroot_Sapling_Green         .set(ST.make(MD.AETHER, "greenSkyrootSapling"               , 1, 0), null, OP.treeSapling);
		IL.AETHER_Skyroot_Sapling_Blue          .set(ST.make(MD.AETHER, "blueSkyrootSapling"                , 1, 0), null, OP.treeSapling);
		IL.AETHER_Skyroot_Sapling_Dark          .set(ST.make(MD.AETHER, "darkBlueSkyrootSapling"            , 1, 0), null, OP.treeSapling);
		IL.AETHER_Skyroot_Sapling_Purple        .set(ST.make(MD.AETHER, "purpleCrystalSapling"              , 1, 0), null, OP.treeSapling);
		IL.AETHER_Skyroot_Leaves_Gold           .set(ST.make(MD.AETHER, "goldenOakLeaves"                   , 1, 1), null, OP.treeLeaves);
		IL.AETHER_Skyroot_Leaves_Green          .set(ST.make(MD.AETHER, "greenSkyrootLeaves"                , 1, 1), null, OP.treeLeaves);
		IL.AETHER_Skyroot_Leaves_Blue           .set(ST.make(MD.AETHER, "blueSkyrootLeaves"                 , 1, 1), null, OP.treeLeaves);
		IL.AETHER_Skyroot_Leaves_Dark           .set(ST.make(MD.AETHER, "darkBlueSkyrootLeaves"             , 1, 1), null, OP.treeLeaves);
		IL.AETHER_Skyroot_Leaves_Purple         .set(ST.make(MD.AETHER, "purpleCrystalLeaves"               , 1, 1), null, OP.treeLeaves);
		IL.AETHER_Skyroot_Leaves_Apple          .set(ST.make(MD.AETHER, "purpleFruitLeaves"                 , 1, 1), null, OP.treeLeaves);
		
		if (IL.AETHER_Dirt         .exists()) Textures.BlockIcons.DIRTS[1] = new IconContainerCopied(IL.AETHER_Dirt.block(), 0, SIDE_BOTTOM);
		if (IL.AETHER_Bucket_Water .exists()) IL.AETHER_Bucket_Water .item().setContainerItem(IL.AETHER_Bucket_Empty.getItem());
		if (IL.AETHER_Bucket_Milk  .exists()) IL.AETHER_Bucket_Milk  .item().setContainerItem(IL.AETHER_Bucket_Empty.getItem());
		if (IL.AETHER_Bucket_Poison.exists()) IL.AETHER_Bucket_Poison.item().setContainerItem(IL.AETHER_Bucket_Empty.getItem());
		
		
		IL.GrC_Honey_Jar                        .set(ST.make(MD.GrC_Bees, "grc.honeyJar"                    , 1, 0), null, OD.container1000honey);
		if (IL.GrC_Honey_Jar.exists()) IL.GrC_Honey_Jar.item().setContainerItem(Items.flower_pot);
		
		IL.GrC_Honey_Bucket                     .set(ST.make(MD.GrC_Bees, "grc.BucketFluidHoney"            , 1, 0), new OreDictItemData(ANY.Fe, U*3), OD.container1000honey);
		IL.GrC_Honey_Bottle                     .set(ST.make(MD.GrC_Bees, "grc.BottleFluidHoney"            , 1, 0), null, OD.container250honey);
		IL.GrC_Milk_Bucket                      .set(ST.make(MD.GrC_Milk, "grcmilk.BucketFluidMilk"         , 1, 0), new OreDictItemData(ANY.Fe, U*3), OD.container1000milk);
		IL.GrC_Milk_Bottle                      .set(ST.make(MD.GrC_Milk, "grcmilk.BottleFluidMilk"         , 1, 0), null, OD.container250milk);
		IL.GrC_Cheese_Cheddar                   .set(ST.make(MD.GrC_Milk, "grcmilk.Cheese"                  , 1, 0), null, "foodCheeseCheddar");
		IL.GrC_Cheese_Gorgonzola                .set(ST.make(MD.GrC_Milk, "grcmilk.Cheese"                  , 1, 1), null, "foodCheeseGorgonzola");
		IL.GrC_Cheese_Swiss                     .set(ST.make(MD.GrC_Milk, "grcmilk.Cheese"                  , 1, 2), null, "foodCheeseSwiss");
		IL.GrC_Cheese_Appenzeller               .set(ST.make(MD.GrC_Milk, "grcmilk.Cheese"                  , 1, 3), null, "foodCheeseAppenzeller");
		IL.GrC_Cheese_Asiago                    .set(ST.make(MD.GrC_Milk, "grcmilk.Cheese"                  , 1, 4), null, "foodCheeseAsiago");
		IL.GrC_Cheese_Parmesan                  .set(ST.make(MD.GrC_Milk, "grcmilk.Cheese"                  , 1, 5), null, "foodCheeseParmesan");
		IL.GrC_Cheese_Monterey                  .set(ST.make(MD.GrC_Milk, "grcmilk.Cheese"                  , 1, 6), null, "foodCheeseMonterey");
		IL.GrC_Cheese_Ricotta                   .set(ST.make(MD.GrC_Milk, "grcmilk.Cheese"                  , 1, 7), null, "foodCheeseRicotta");
		IL.GrC_Starter_Culture                  .set(ST.make(MD.GrC_Milk, "grcmilk.StarterCulture"          , 1, 0));
		IL.GrC_Applecore                        .set(ST.make(MD.GrC_Apples, "grc.appleSeeds"                , 1, 0), null, OD.itemPlantRemains);
		IL.GrC_Bamboo                           .set(ST.make(MD.GrC_Bamboo, "grc.bamboo"                    , 1, 0), OP.stick.dat(MT.Bamboo), OD.bamboo);
		IL.GrC_Bamboo_Charcoal                  .set(ST.make(MD.GrC_Bamboo, "grc.bambooCoal"                , 1, 0), new OreDictItemData(MT.Charcoal, U2));
		IL.GrC_Paddy                            .set(ST.make(MD.GrC_Rice, "grc.paddyField"                  , 1, 0));
		IL.GrC_Ice_Cream                        .set(ST.make(MD.GrC_Milk, "grcmilk.IceCream"                , 1, 0), null, "foodIcecream");
		IL.GrC_Ice_Cream_Chocolate              .set(ST.make(MD.GrC_Milk, "grcmilk.IceCream"                , 1, 1), null, "foodChocolateicecream");
		IL.GrC_Ice_Cream_Grape                  .set(ST.make(MD.GrC_Milk, "grcmilk.IceCream"                , 1, 2), null, "foodGrapeicecream");
		IL.GrC_Ice_Cream_Apple                  .set(ST.make(MD.GrC_Milk, "grcmilk.IceCream"                , 1, 3), null, "foodAppleicecream");
		IL.GrC_Ice_Cream_Honey                  .set(ST.make(MD.GrC_Milk, "grcmilk.IceCream"                , 1, 4), null, "foodHoneyicecream");
		IL.GrC_Ice_Cream_Melon                  .set(ST.make(MD.GrC_Milk, "grcmilk.IceCream"                , 1, 5), null, "foodMelonicecream");
		
		
		IL.GrC_Grape_Purple                     .set(ST.make(MD.GrC_Grapes, "grc.grapes"                    , 1, 0), null, "cropGrapePurple");
		IL.GrC_Grape_Green                      .set(ST.make(MD.GrC_Grapes, "grc.grapes"                    , 1, 1), null, "cropGrapeGreen");
		IL.GrC_Grape_Red                        .set(ST.make(MD.GrC_Grapes, "grc.grapes"                    , 1, 2), null, "cropGrapeRed");
		
		
		IL.MoCr_Crab_Raw                        .set(ST.make(MD.MoCr, "crabraw"                             , 1, 0), new OreDictItemData(MT.FishRaw     , 3*U2), "foodCrabraw");
		IL.MoCr_Crab_Cooked                     .set(ST.make(MD.MoCr, "crabcooked"                          , 1, 0), new OreDictItemData(MT.FishCooked  , 3*U2), "foodCrabcooked");
		IL.MoCr_Turkey_Raw                      .set(ST.make(MD.MoCr, "turkeyraw"                           , 1, 0), new OreDictItemData(MT.MeatRaw     , U*3, MT.Bone, U4), "foodTurkeyraw");
		IL.MoCr_Turkey_Cooked                   .set(ST.make(MD.MoCr, "turkeycooked"                        , 1, 0), new OreDictItemData(MT.MeatCooked  , U*3, MT.Bone, U4), "foodTurkeycooked");
		IL.MoCr_Rat_Raw                         .set(ST.make(MD.MoCr, "ratraw"                              , 1, 0), new OreDictItemData(MT.MeatRaw     , U*1, MT.Bone, U9), "foodRatraw");
		IL.MoCr_Rat_Cooked                      .set(ST.make(MD.MoCr, "ratcooked"                           , 1, 0), new OreDictItemData(MT.MeatCooked  , U*1, MT.Bone, U9), "foodRatcooked");
		IL.MoCr_Ostrich_Raw                     .set(ST.make(MD.MoCr, "ostrichraw"                          , 1, 0), new OreDictItemData(MT.MeatRaw     , U*2, MT.Bone, U4), "foodOstrichraw");
		IL.MoCr_Ostrich_Cooked                  .set(ST.make(MD.MoCr, "ostrichcooked"                       , 1, 0), new OreDictItemData(MT.MeatCooked  , U*2, MT.Bone, U4), "foodOstrichcooked");
		IL.MoCr_Turtle_Raw                      .set(ST.make(MD.MoCr, "turtleraw"                           , 1, 0), new OreDictItemData(MT.MeatRaw     , U*2, MT.Bone, U9), "foodTurtleraw");
		
		
		IL.MaCu_Dye_White                       .set(ST.make(MD.MaCu, "materials"                           , 1,27), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_White]);
		IL.MaCu_Dye_Blue                        .set(ST.make(MD.MaCu, "materials"                           , 1,28), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue]);
		IL.MaCu_Dye_Green                       .set(ST.make(MD.MaCu, "materials"                           , 1,29), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Green]);
		IL.MaCu_Dye_Yellow                      .set(ST.make(MD.MaCu, "materials"                           , 1,30), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Yellow]);
		IL.MaCu_Dye_Red                         .set(ST.make(MD.MaCu, "materials"                           , 1,31), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Red]);
		IL.MaCu_Dye_Brown                       .set(ST.make(MD.MaCu, "materials"                           , 1,32), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Brown]);
		IL.MaCu_Bait_Worm                       .set(ST.make(MD.MaCu, "bait"                                , 1, 0));
		IL.MaCu_Bait_Ant                        .set(ST.make(MD.MaCu, "bait"                                , 1, 1));
		IL.MaCu_Bait_Maggot                     .set(ST.make(MD.MaCu, "bait"                                , 1, 2));
		IL.MaCu_Bait_Grasshopper                .set(ST.make(MD.MaCu, "bait"                                , 1, 3));
		IL.MaCu_Bait_Bee                        .set(ST.make(MD.MaCu, "bait"                                , 1, 4));
		IL.MaCu_Polished_Planks                 .set(ST.make(MD.MaCu, "woods"                               , 1, 1), OP.plank.dat(MT.WoodPolished));
		IL.MaCu_Polished_Logs                   .set(ST.make(MD.MaCu, "woods"                               , 1, 2), new OreDictItemData(MT.WoodPolished, U*8, MT.Bark, U));
		
		
		IL.ENVM_Spoiled_Milk_Bucket             .set(ST.make(MD.ENVM, "spoiledMilk"                         , 1, 0), new OreDictItemData(ANY.Fe, U*3));
		IL.ENVM_Rotten_Food                     .set(ST.make(MD.ENVM, "rottenFood"                          , 1, 0), null, "foodRotten");
		IL.ENVM_Bottle_Water_Cold               .set(ST.make(MD.ENVM, "coldWaterBottle"                     , 1, 0));
		IL.ENVM_Bottle_Water_Dirty              .set(ST.make(MD.ENVM, "badWaterBottle"                      , 1, 0));
		IL.ENVM_Bottle_Water_Salty              .set(ST.make(MD.ENVM, "saltWaterBottle"                     , 1, 0));
		
		
		IL.NePl_Torch                           .set(ST.make(MD.NePl, "SoulTorch"                           , 1, 0), null, OD.blockSoulTorch);
		IL.NePl_SoulSoil                        .set(ST.make(MD.NePl, "SoulSoil"                            , 1, 0), null, OD.soulsand);
		IL.NePl_Ancient_Debris                  .set(ST.make(MD.NePl, "AncientDebris"                       , 1, 0));
		IL.NePl_Obsidian                        .set(ST.make(MD.NePl, "CryingObsidian"                      , 1, 0), new OreDictItemData(MT.Obsidian, U*9), OD.cryingObsidian);
		IL.NePl_Quartz_Bricks                   .set(ST.make(MD.NePl, "QuartzBricks"                        , 1, 0), new OreDictItemData(MT.NetherQuartz, U*4));
		IL.NePl_Blackstone                      .set(ST.make(MD.NePl, "Blackstone"                          , 1, 0), OP.stone.dat(MT.Blackstone), OP.cobblestone);
		IL.NePl_Blackstone_Polished             .set(ST.make(MD.NePl, "PolishedBlackstone"                  , 1, 0), OP.stone.dat(MT.Blackstone));
		IL.NePl_Blackstone_Chiseled             .set(ST.make(MD.NePl, "ChiseledPolishedBlackstone"          , 1, 0), OP.stone.dat(MT.Blackstone));
		IL.NePl_Blackstone_Bricks               .set(ST.make(MD.NePl, "PolishedBlackstoneBricks"            , 1, 0), OP.stone.dat(MT.Blackstone));
		IL.NePl_Blackstone_Cracked              .set(ST.make(MD.NePl, "CrackedPolishedBlackstoneBricks"     , 1, 0), OP.stone.dat(MT.Blackstone));
		IL.NePl_Basalt                          .set(ST.make(MD.NePl, "Basalt"                              , 1, 0), OP.stone.dat(MT.Basalt));
		IL.NePl_Basalt_Polished                 .set(ST.make(MD.NePl, "PolishedBasalt"                      , 1, 0), OP.stone.dat(MT.Basalt));
		
		
		IL.NeLi_Fire_Soul                       .set(ST.make(MD.NeLi, "SoulFire"                            , 1, 0));
		IL.NeLi_Fire_Shadow                     .set(ST.make(MD.NeLi, "ShadowFire"                          , 1, 0));
		IL.NeLi_Fire_Fox                        .set(ST.make(MD.NeLi, "FoxFire"                             , 1, 0));
		IL.NeLi_Torch_Soul                      .set(ST.make(MD.NeLi, "SoulTorch"                           , 1, 0), null, OD.blockSoulTorch);
		IL.NeLi_Torch_Shadow                    .set(ST.make(MD.NeLi, "ShadowTorch"                         , 1, 0), null, OD.blockShadowTorch);
		IL.NeLi_Torch_Fox                       .set(ST.make(MD.NeLi, "FoxfireTorch"                        , 1, 0), null, OD.blockFoxfireTorch);
		IL.Crop_AbyssalOats                     .set(ST.make(MD.NeLi, "AbyssalOatItem"                      , 1, 0), new OreDictItemData(MT.OatAbyssal, U), "cropAbyssalOats");
		IL.NeLi_Obsidian                        .set(ST.make(MD.NeLi, "CryingObsidian"                      , 1, 0), new OreDictItemData(MT.Obsidian, U*9), OD.cryingObsidian);
		IL.NeLi_Quartz_Bricks                   .set(ST.make(MD.NeLi, "QuartzBricks"                        , 1, 0), new OreDictItemData(MT.NetherQuartz   , U*4));
		IL.NeLi_Quartz_Smooth                   .set(ST.make(MD.NeLi, "QuartzBricks"                        , 1, 1), new OreDictItemData(MT.NetherQuartz   , U*4));
		IL.NeLi_Quartz_Chiseled_Pillar          .set(ST.make(MD.NeLi, "QuartzPillar"                        , 1, 0), new OreDictItemData(MT.NetherQuartz   , U*4));
		IL.NeLi_Void_Block                      .set(ST.make(MD.NeLi, "QuartzBricks"                        , 1, 2), new OreDictItemData(MT.VoidQuartz     , U*4));
		IL.NeLi_Void_Bricks                     .set(ST.make(MD.NeLi, "QuartzBricks"                        , 1, 4), new OreDictItemData(MT.VoidQuartz     , U*4));
		IL.NeLi_Void_Smooth                     .set(ST.make(MD.NeLi, "QuartzBricks"                        , 1, 5), new OreDictItemData(MT.VoidQuartz     , U*4));
		IL.NeLi_Void_Chiseled                   .set(ST.make(MD.NeLi, "QuartzBricks"                        , 1, 3), new OreDictItemData(MT.VoidQuartz     , U*4));
		IL.NeLi_Void_Pillar                     .set(ST.make(MD.NeLi, "QuartzPillar"                        , 1, 1), new OreDictItemData(MT.VoidQuartz     , U*4));
		IL.NeLi_Void_Chiseled_Pillar            .set(ST.make(MD.NeLi, "QuartzPillar"                        , 1, 2), new OreDictItemData(MT.VoidQuartz     , U*4));
		IL.NeLi_Crystal_White                   .set(ST.make(MD.NeLi, "NetherCrystal"                       , 1, 0), new OreDictItemData(MT.FluoriteWhite  , U*4));
		IL.NeLi_Crystal_Blue                    .set(ST.make(MD.NeLi, "NetherCrystal"                       , 1, 1), new OreDictItemData(MT.FluoriteBlue   , U*4));
		IL.NeLi_Crystal_Green                   .set(ST.make(MD.NeLi, "NetherCrystal"                       , 1, 2), new OreDictItemData(MT.FluoriteGreen  , U*4));
		IL.NeLi_Crystal_Yellow                  .set(ST.make(MD.NeLi, "NetherCrystal"                       , 1, 3), new OreDictItemData(MT.FluoriteYellow , U*4));
		IL.NeLi_Crystal_Magenta                 .set(ST.make(MD.NeLi, "NetherCrystal"                       , 1, 4), new OreDictItemData(MT.FluoriteMagenta, U*4));
		IL.NeLi_Magmatic_Netherrack             .set(ST.make(MD.NeLi, "MagmaBlock"                          , 1, 1), new OreDictItemData(MT.Netherrack, U));
		IL.NeLi_Magmatic_Blackstone             .set(ST.make(MD.NeLi, "MagmaBlock"                          , 1, 0), new OreDictItemData(MT.Blackstone, U));
		IL.NeLi_Blackstone_Crying               .set(ST.make(MD.NeLi, "CryingBlackstone"                    , 1, 0), new OreDictItemData(MT.Blackstone, U));
		IL.NeLi_Blackstone                      .set(ST.make(MD.NeLi, "Blackstone"                          , 1, 0), OP.stone.dat(MT.Blackstone), OP.cobblestone);
		IL.NeLi_Blackstone_Polished             .set(ST.make(MD.NeLi, "Blackstone"                          , 1, 1), OP.stone.dat(MT.Blackstone));
		IL.NeLi_Blackstone_Chiseled             .set(ST.make(MD.NeLi, "Blackstone"                          , 1, 2), OP.stone.dat(MT.Blackstone));
		IL.NeLi_Blackstone_Bricks               .set(ST.make(MD.NeLi, "Blackstone"                          , 1, 3), OP.stone.dat(MT.Blackstone));
		IL.NeLi_Blackstone_Cracked              .set(ST.make(MD.NeLi, "Blackstone"                          , 1, 4), OP.stone.dat(MT.Blackstone));
		IL.NeLi_Basalt                          .set(ST.make(MD.NeLi, "Basalt"                              , 1, 0), OP.stone.dat(MT.Basalt));
		IL.NeLi_Basalt_Polished                 .set(ST.make(MD.NeLi, "Basalt"                              , 1, 1), OP.stone.dat(MT.Basalt));
		IL.NeLi_Stem_Crimson                    .set(ST.make(MD.NeLi, "Stem"                                , 1, 0), null, OD.logWood);
		IL.NeLi_Stem_Warped                     .set(ST.make(MD.NeLi, "Stem"                                , 1, 2), null, OD.logWood);
		IL.NeLi_Stem_FoxFire                    .set(ST.make(MD.NeLi, "Stem2"                               , 1, 0), null, OD.logWood);
	//  IL.NeLi_Stem_                           .set(ST.make(MD.NeLi, "Stem2"                               , 1, 2), null, OD.logWood);
		IL.NeLi_Beam1_Crimson                   .set(ST.make(MD.NeLi, "Stem"                                , 1, 1), null, OD.beamWood);
		IL.NeLi_Beam1_Warped                    .set(ST.make(MD.NeLi, "Stem"                                , 1, 3), null, OD.beamWood);
		IL.NeLi_Beam1_FoxFire                   .set(ST.make(MD.NeLi, "Stem2"                               , 1, 1), null, OD.beamWood);
	//  IL.NeLi_Beam1_                          .set(ST.make(MD.NeLi, "Stem2"                               , 1, 3), null, OD.beamWood);
		IL.NeLi_Hyphae_Crimson                  .set(ST.make(MD.NeLi, "FullWood"                            , 1, 0), null, OD.logWood);
		IL.NeLi_Hyphae_Warped                   .set(ST.make(MD.NeLi, "FullWood"                            , 1, 2), null, OD.logWood);
		IL.NeLi_Hyphae_FoxFire                  .set(ST.make(MD.NeLi, "FullWood"                            , 1, 4), null, OD.logWood);
	//  IL.NeLi_Hyphae_                         .set(ST.make(MD.NeLi, "FullWood"                            , 1, 6), null, OD.logWood);
		IL.NeLi_Beam2_Crimson                   .set(ST.make(MD.NeLi, "FullWood"                            , 1, 1), null, OD.beamWood);
		IL.NeLi_Beam2_Warped                    .set(ST.make(MD.NeLi, "FullWood"                            , 1, 3), null, OD.beamWood);
		IL.NeLi_Beam2_FoxFire                   .set(ST.make(MD.NeLi, "FullWood"                            , 1, 5), null, OD.beamWood);
	//  IL.NeLi_Beam2_                          .set(ST.make(MD.NeLi, "FullWood"                            , 1, 7), null, OD.beamWood);
		IL.NeLi_SoulSoil                        .set(ST.make(MD.NeLi, "SoulSoil"                            , 1, 0), null, OD.soulsand);
		IL.NeLi_SoulFarm                        .set(ST.make(MD.NeLi, "SoulFarmland"                        , 1, 0), null, OD.soulsand);
		IL.NeLi_Gravel                          .set(ST.make(MD.NeLi, "Nether_Gravel"                       , 1, 0), null, OD.gravel);
		IL.NeLi_Foxfire_Lily                    .set(ST.make(MD.NeLi, "FoxfireLily"                         , 1, 0), null, OD.flower);
		IL.NeLi_Wither_Rose                     .set(ST.make(MD.NeLi, "WitherRose"                          , 1, 0), null, OD.flower);
		IL.NeLi_Strider_Flank_Raw               .set(ST.make(MD.NeLi, "StriderFlankRaw"                     , 1, 0), new OreDictItemData(MT.MeatRaw   , U*2, MT.Bone, U9), "listAllmeatraw");
		IL.NeLi_Strider_Flank_Cooked            .set(ST.make(MD.NeLi, "StriderFlankCooked"                  , 1, 0), new OreDictItemData(MT.MeatCooked, U*2, MT.Bone, U9), "listAllmeatcooked");
		IL.NeLi_Bread                           .set(ST.make(MD.NeLi, "AbyssalBread"                        , 1, 0), null, "foodBread");
		IL.NeLi_Cookie                          .set(ST.make(MD.NeLi, "CookieHellderberry"                  , 1, 0), null, "foodCookie");
		IL.NeLi_Bottle_Hellderberryjuice        .set(ST.make(MD.NeLi, "JuiceHellderberry"                   , 1, 0), null, "foodHellderberryjuice");
		IL.NeLi_Bucket_Spectral_Dew             .set(ST.make(MD.NeLi, "SpectralBucket"                      , 1, 0), new OreDictItemData(ANY.Fe, U*3), OD.container1000water);
		IL.NeLi_Bowl_DevilishMaize              .set(ST.make(MD.NeLi, "DevilishPopcornRaw"                  , 1, 0));
		IL.NeLi_Bowl_DevilishPopcorn            .set(ST.make(MD.NeLi, "DevilishPopcorn"                     , 1, 0));
		IL.NeLi_Bowl_CrimsonStew                .set(ST.make(MD.NeLi, "StewCrimson"                         , 1, 0));
		IL.NeLi_Bowl_WarpedStew                 .set(ST.make(MD.NeLi, "StewWarped"                          , 1, 0));
		IL.NeLi_Bowl_FoxfireStew                .set(ST.make(MD.NeLi, "StewFoxfire"                         , 1, 0));
		IL.NeLi_ShroomLight                     .set(ST.make(MD.NeLi, "ShroomLight"                         , 1, 0));
		IL.NeLi_Gloomstone                      .set(ST.make(MD.NeLi, "Gloomstone"                          , 1, 0), new OreDictItemData(MT.Gloomstone, U*4), OD.glowstone);
		IL.NeLi_Reed                            .set(ST.make(MD.NeLi, "InfernalReedItem"                    , 1, 0));
		IL.NeLi_Wart_Crimson                    .set(ST.make(MD.NeLi, "WartItem"                            , 1, 0));
		IL.NeLi_Wart_Warped                     .set(ST.make(MD.NeLi, "WartItem"                            , 1, 1));
		IL.NeLi_Wart_Soggy                      .set(ST.make(MD.NeLi, "WartItem"                            , 1, 2));
		IL.NeLi_Foxfire_Powder                  .set(ST.make(MD.NeLi, "FoxfirePowder"                       , 1, 0));
		
		if (IL.NeLi_Bowl_DevilishMaize      .exists()) IL.NeLi_Bowl_DevilishMaize      .item().setContainerItem(Items.bowl);
		if (IL.NeLi_Bowl_DevilishPopcorn    .exists()) IL.NeLi_Bowl_DevilishPopcorn    .item().setContainerItem(Items.bowl);
		if (IL.NeLi_Bowl_CrimsonStew        .exists()) IL.NeLi_Bowl_CrimsonStew        .item().setContainerItem(Items.bowl);
		if (IL.NeLi_Bowl_WarpedStew         .exists()) IL.NeLi_Bowl_WarpedStew         .item().setContainerItem(Items.bowl);
		if (IL.NeLi_Bowl_FoxfireStew        .exists()) IL.NeLi_Bowl_FoxfireStew        .item().setContainerItem(Items.bowl);
//      if (IL.NeLi_Bucket_Spectral_Dew     .exists()) IL.NeLi_Bucket_Spectral_Dew     .item().setContainerItem(Items.bucket); Should be fixed on the Netherlicious Side now. ^^
		if (IL.NeLi_Bottle_Hellderberryjuice.exists()) IL.NeLi_Bottle_Hellderberryjuice.item().setContainerItem(Items.glass_bottle);
		
		
		IL.EtFu_Chorus_Fruit                    .set(ST.make(MD.EtFu, "chorus_fruit"                        , 1, 0));
		IL.EtFu_Chorus_Popped                   .set(ST.make(MD.EtFu, "chorus_fruit_popped"                 , 1, 0));
		IL.EtFu_Rabbit_Foot                     .set(ST.make(MD.EtFu, "rabbit_foot"                         , 1, 0));
		IL.EtFu_Beet_Seeds                      .set(ST.make(MD.EtFu, "beetroot_seeds"                      , 1, 0), null, "seedBeet");
		IL.EtFu_Dragon_Breath                   .set(ST.make(MD.EtFu, "dragon_breath"                       , 1, 0));
		IL.EtFu_Lingering_Potion                .set(ST.make(MD.EtFu, "lingering_potion"                    , 1, 0));
		IL.EtFu_Obsidian                        .set(ST.make(MD.EtFu, "crying_obsidian"                     , 1, 0), new OreDictItemData(MT.Obsidian, U*9, MT.Lapis, U), OD.cryingObsidian);
		IL.EtFu_Sandstone                       .set(ST.make(MD.EtFu, "red_sandstone"                       , 1, 0));
		IL.EtFu_Path                            .set(ST.make(MD.EtFu, "grass_path"                          , 1, 0));
		IL.EtFu_Dirt                            .set(ST.make(MD.EtFu, "coarse_dirt"                         , 1, 0));
		IL.EtFu_Gravel                          .set(ST.make(MD.EtFu, "old_gravel"                          , 1, 0), null, OD.gravel);
		IL.EtFu_Granite                         .set(ST.make(MD.EtFu, "stone"                               , 1, 1), new OreDictItemData(MT.Granite , U), OP.stone.dat(MT.Granite ));
		IL.EtFu_Granite_Smooth                  .set(ST.make(MD.EtFu, "stone"                               , 1, 2), new OreDictItemData(MT.Granite , U), OP.stone.dat(MT.Granite ));
		IL.EtFu_Diorite                         .set(ST.make(MD.EtFu, "stone"                               , 1, 3), new OreDictItemData(MT.Diorite , U), OP.stone.dat(MT.Diorite ));
		IL.EtFu_Diorite_Smooth                  .set(ST.make(MD.EtFu, "stone"                               , 1, 4), new OreDictItemData(MT.Diorite , U), OP.stone.dat(MT.Diorite ));
		IL.EtFu_Andesite                        .set(ST.make(MD.EtFu, "stone"                               , 1, 5), new OreDictItemData(MT.Andesite, U), OP.stone.dat(MT.Andesite));
		IL.EtFu_Andesite_Smooth                 .set(ST.make(MD.EtFu, "stone"                               , 1, 6), new OreDictItemData(MT.Andesite, U), OP.stone.dat(MT.Andesite));
		
		if (IL.EtFu_Lingering_Potion.exists()) IL.EtFu_Lingering_Potion.item().setContainerItem(Items.glass_bottle);
		
		
		IL.GaSu_Granite                         .set(ST.make(MD.GaSu, "18Stones"                            , 1, 1), new OreDictItemData(MT.Granite , U), OP.stone.dat(MT.Granite ));
		IL.GaSu_Granite_Smooth                  .set(ST.make(MD.GaSu, "18Stones"                            , 1, 2), new OreDictItemData(MT.Granite , U), OP.stone.dat(MT.Granite ));
		IL.GaSu_Diorite                         .set(ST.make(MD.GaSu, "18Stones"                            , 1, 3), new OreDictItemData(MT.Diorite , U), OP.stone.dat(MT.Diorite ));
		IL.GaSu_Diorite_Smooth                  .set(ST.make(MD.GaSu, "18Stones"                            , 1, 4), new OreDictItemData(MT.Diorite , U), OP.stone.dat(MT.Diorite ));
		IL.GaSu_Andesite                        .set(ST.make(MD.GaSu, "18Stones"                            , 1, 5), new OreDictItemData(MT.Andesite, U), OP.stone.dat(MT.Andesite));
		IL.GaSu_Andesite_Smooth                 .set(ST.make(MD.GaSu, "18Stones"                            , 1, 6), new OreDictItemData(MT.Andesite, U), OP.stone.dat(MT.Andesite));
		IL.GaSu_Basalt                          .set(ST.make(MD.GaSu, "basalt"                              , 1, 0), new OreDictItemData(MT.Basalt  , U), OP.stone.dat(MT.Basalt  ));
		IL.GaSu_Basalt_Smooth                   .set(ST.make(MD.GaSu, "basalt"                              , 1, 1), new OreDictItemData(MT.Basalt  , U), OP.stone.dat(MT.Basalt  ));
		IL.GaSu_Beet_Seeds                      .set(ST.make(MD.GaSu, "beetrootSeeds"                       , 1, 0), null, "seedBeet");
		
		
		IL.CHSL_Granite                         .set(ST.make(MD.CHSL, "granite"                             , 1, 0), new OreDictItemData(MT.Granite , U), OP.stone.dat(MT.Granite ));
		IL.CHSL_Diorite                         .set(ST.make(MD.CHSL, "diorite"                             , 1, 0), new OreDictItemData(MT.Diorite , U), OP.stone.dat(MT.Diorite ));
		IL.CHSL_Andesite                        .set(ST.make(MD.CHSL, "andesite"                            , 1, 0), new OreDictItemData(MT.Andesite, U), OP.stone.dat(MT.Andesite));
		IL.CHSL_Granite_Smooth                  .set(ST.make(MD.CHSL, "granite"                             , 1, 1), new OreDictItemData(MT.Granite , U), OP.stone.dat(MT.Granite ));
		IL.CHSL_Diorite_Smooth                  .set(ST.make(MD.CHSL, "diorite"                             , 1, 1), new OreDictItemData(MT.Diorite , U), OP.stone.dat(MT.Diorite ));
		IL.CHSL_Andesite_Smooth                 .set(ST.make(MD.CHSL, "andesite"                            , 1, 1), new OreDictItemData(MT.Andesite, U), OP.stone.dat(MT.Andesite));
		
		
		IL.BOTA_Andesite                        .set(ST.make(MD.BOTA, "stone"                               , 1, 0), new OreDictItemData(MT.Andesite, U), OP.stone.dat(MT.Andesite));
		IL.BOTA_Basalt                          .set(ST.make(MD.BOTA, "stone"                               , 1, 1), new OreDictItemData(MT.Basalt  , U), OP.stone.dat(MT.Basalt  ));
		IL.BOTA_Diorite                         .set(ST.make(MD.BOTA, "stone"                               , 1, 2), new OreDictItemData(MT.Diorite , U), OP.stone.dat(MT.Diorite ));
		IL.BOTA_Granite                         .set(ST.make(MD.BOTA, "stone"                               , 1, 3), new OreDictItemData(MT.Granite , U), OP.stone.dat(MT.Granite ));
		IL.BOTA_Andesite_Smooth                 .set(ST.make(MD.BOTA, "stone"                               , 1, 4), new OreDictItemData(MT.Andesite, U), OP.stone.dat(MT.Andesite));
		IL.BOTA_Basalt_Smooth                   .set(ST.make(MD.BOTA, "stone"                               , 1, 5), new OreDictItemData(MT.Basalt  , U), OP.stone.dat(MT.Basalt  ));
		IL.BOTA_Diorite_Smooth                  .set(ST.make(MD.BOTA, "stone"                               , 1, 6), new OreDictItemData(MT.Diorite , U), OP.stone.dat(MT.Diorite ));
		IL.BOTA_Granite_Smooth                  .set(ST.make(MD.BOTA, "stone"                               , 1, 7), new OreDictItemData(MT.Granite , U), OP.stone.dat(MT.Granite ));
		IL.BOTA_Andesite_Bricks                 .set(ST.make(MD.BOTA, "stone"                               , 1, 8), new OreDictItemData(MT.Andesite, U), OP.stone.dat(MT.Andesite));
		IL.BOTA_Basalt_Bricks                   .set(ST.make(MD.BOTA, "stone"                               , 1, 9), new OreDictItemData(MT.Basalt  , U), OP.stone.dat(MT.Basalt  ));
		IL.BOTA_Diorite_Bricks                  .set(ST.make(MD.BOTA, "stone"                               , 1,10), new OreDictItemData(MT.Diorite , U), OP.stone.dat(MT.Diorite ));
		IL.BOTA_Granite_Bricks                  .set(ST.make(MD.BOTA, "stone"                               , 1,11), new OreDictItemData(MT.Granite , U), OP.stone.dat(MT.Granite ));
		IL.BOTA_Andesite_Chiseled               .set(ST.make(MD.BOTA, "stone"                               , 1,12), new OreDictItemData(MT.Andesite, U), OP.stone.dat(MT.Andesite));
		IL.BOTA_Basalt_Chiseled                 .set(ST.make(MD.BOTA, "stone"                               , 1,13), new OreDictItemData(MT.Basalt  , U), OP.stone.dat(MT.Basalt  ));
		IL.BOTA_Diorite_Chiseled                .set(ST.make(MD.BOTA, "stone"                               , 1,14), new OreDictItemData(MT.Diorite , U), OP.stone.dat(MT.Diorite ));
		IL.BOTA_Granite_Chiseled                .set(ST.make(MD.BOTA, "stone"                               , 1,15), new OreDictItemData(MT.Granite , U), OP.stone.dat(MT.Granite ));
		IL.BOTA_Livingrock                      .set(ST.make(MD.BOTA, "livingrock"                          , 1, 0), new OreDictItemData(MT.Livingrock     , U), OP.stone.dat(MT.Livingrock     ));
		IL.BOTA_Prismarine                      .set(ST.make(MD.BOTA, "prismarine"                          , 1, 0), new OreDictItemData(MT.PrismarineLight, U), OP.stone.dat(MT.PrismarineLight));
		IL.BOTA_Prismarine_Bricks               .set(ST.make(MD.BOTA, "prismarine"                          , 1, 1), new OreDictItemData(MT.PrismarineLight, U), OP.stone.dat(MT.PrismarineLight));
		IL.BOTA_Prismarine_Dark                 .set(ST.make(MD.BOTA, "prismarine"                          , 1, 2), new OreDictItemData(MT.PrismarineDark , U), OP.stone.dat(MT.PrismarineDark ));
		
		
		IL.EBXL_Cactus_Paste                    .set(ST.make(MD.EBXL, "extrabiomes.paste"                   , 1, 0));
		IL.EBXL_Dye_Black                       .set(ST.make(MD.EBXL, "extrabiomes.dye"                     , 1, 0), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Black]);
		IL.EBXL_Dye_Blue                        .set(ST.make(MD.EBXL, "extrabiomes.dye"                     , 1, 1), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue]);
		IL.EBXL_Dye_Brown                       .set(ST.make(MD.EBXL, "extrabiomes.dye"                     , 1, 2), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Brown]);
		IL.EBXL_Dye_White                       .set(ST.make(MD.EBXL, "extrabiomes.dye"                     , 1, 3), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_White]);
		
		
		IL.EB_Dirt_Alfisol                      .set(ST.make(MD.EB, "enhancedbiomes.tile.dirtEB"            , 1, 0));
		IL.EB_Dirt_Andisol                      .set(ST.make(MD.EB, "enhancedbiomes.tile.dirtEB"            , 1, 1));
		IL.EB_Dirt_Gelisol                      .set(ST.make(MD.EB, "enhancedbiomes.tile.dirtEB"            , 1, 3));
		IL.EB_Dirt_Histosol                     .set(ST.make(MD.EB, "enhancedbiomes.tile.dirtEB"            , 1, 4));
		IL.EB_Dirt_Inceptisol                   .set(ST.make(MD.EB, "enhancedbiomes.tile.dirtEB"            , 1, 5));
		IL.EB_Dirt_Mollisol                     .set(ST.make(MD.EB, "enhancedbiomes.tile.dirtEB"            , 1, 6));
		IL.EB_Dirt_Oxisol                       .set(ST.make(MD.EB, "enhancedbiomes.tile.dirtEB"            , 1, 7));
		IL.EB_Grass_Alfisol                     .set(ST.make(MD.EB, "enhancedbiomes.tile.grassEB"           , 1, 0));
		IL.EB_Grass_Andisol                     .set(ST.make(MD.EB, "enhancedbiomes.tile.grassEB"           , 1, 1));
		IL.EB_Grass_Gelisol                     .set(ST.make(MD.EB, "enhancedbiomes.tile.grassEB"           , 1, 3));
		IL.EB_Grass_Histosol                    .set(ST.make(MD.EB, "enhancedbiomes.tile.grassEB"           , 1, 4));
		IL.EB_Grass_Inceptisol                  .set(ST.make(MD.EB, "enhancedbiomes.tile.grassEB"           , 1, 5));
		IL.EB_Grass_Mollisol                    .set(ST.make(MD.EB, "enhancedbiomes.tile.grassEB"           , 1, 6));
		IL.EB_Grass_Oxisol                      .set(ST.make(MD.EB, "enhancedbiomes.tile.grassEB"           , 1, 7));
		
		if (IL.EB_Dirt_Alfisol   .exists()) Textures.BlockIcons.DIRTS[ 5] = new IconContainerCopied(IL.EB_Dirt_Alfisol   .block(), 0, SIDE_BOTTOM);
		if (IL.EB_Dirt_Andisol   .exists()) Textures.BlockIcons.DIRTS[ 6] = new IconContainerCopied(IL.EB_Dirt_Andisol   .block(), 1, SIDE_BOTTOM);
		if (IL.EB_Dirt_Gelisol   .exists()) Textures.BlockIcons.DIRTS[ 7] = new IconContainerCopied(IL.EB_Dirt_Gelisol   .block(), 3, SIDE_BOTTOM);
		if (IL.EB_Dirt_Histosol  .exists()) Textures.BlockIcons.DIRTS[ 8] = new IconContainerCopied(IL.EB_Dirt_Histosol  .block(), 4, SIDE_BOTTOM);
		if (IL.EB_Dirt_Inceptisol.exists()) Textures.BlockIcons.DIRTS[ 9] = new IconContainerCopied(IL.EB_Dirt_Inceptisol.block(), 5, SIDE_BOTTOM);
		if (IL.EB_Dirt_Mollisol  .exists()) Textures.BlockIcons.DIRTS[10] = new IconContainerCopied(IL.EB_Dirt_Mollisol  .block(), 6, SIDE_BOTTOM);
		if (IL.EB_Dirt_Oxisol    .exists()) Textures.BlockIcons.DIRTS[11] = new IconContainerCopied(IL.EB_Dirt_Oxisol    .block(), 7, SIDE_BOTTOM);
		
		
		IL.BoP_Limestone                        .set(ST.make(MD.BoP, "rocks"                                , 1, 0), OP.stone.dat(MT.Limestone));
		IL.BoP_Limestone_Polished               .set(ST.make(MD.BoP, "rocks"                                , 1, 1), OP.stone.dat(MT.Limestone));
		IL.BoP_Siltstone                        .set(ST.make(MD.BoP, "rocks"                                , 1, 2), OP.stone.dat(MT.Siltstone));
		IL.BoP_Siltstone_Polished               .set(ST.make(MD.BoP, "rocks"                                , 1, 3), OP.stone.dat(MT.Siltstone));
		IL.BoP_Shale                            .set(ST.make(MD.BoP, "rocks"                                , 1, 4), OP.stone.dat(MT.Shale));
		IL.BoP_Shale_Polished                   .set(ST.make(MD.BoP, "rocks"                                , 1, 5), OP.stone.dat(MT.Shale));
		IL.BoP_Quicksand                        .set(ST.make(MD.BoP, "mud"                                  , 1, 1));
		IL.BoP_Mud                              .set(ST.make(MD.BoP, "mud"                                  , 1, 0));
		IL.BoP_Mud_Ball                         .set(ST.make(MD.BoP, "mudball"                              , 1, 0), null, OD.itemMud);
		IL.BoP_Mud_Brick                        .set(ST.make(MD.BoP, "misc"                                 , 1, 0));
		IL.BoP_Mud_Bricks                       .set(ST.make(MD.BoP, "mudBricks"                            , 1, 0));
		IL.BoP_Ashes                            .set(ST.make(MD.BoP, "misc"                                 , 1, 1), new OreDictItemData(MT.VolcanicAsh, U), DYE_OREDICTS_MIXABLE[DYE_INDEX_Gray]);
		IL.BoP_Ashes_Block                      .set(ST.make(MD.BoP, "ash"                                  , 1, 0), new OreDictItemData(MT.VolcanicAsh, U*4));
		IL.BoP_Ashstone                         .set(ST.make(MD.BoP, "ashStone"                             , 1, 0), new OreDictItemData(MT.VolcanicAsh, U));
		IL.BoP_Comb                             .set(ST.make(MD.BoP, "misc"                                 , 1, 2), null, OD.beeComb, OD.materialWaxcomb);
		IL.BoP_Flesh                            .set(ST.make(MD.BoP, "misc"                                 , 1, 3), new OreDictItemData(MT.MeatRaw, U));
		IL.BoP_Flesh_Block                      .set(ST.make(MD.BoP, "flesh"                                , 1, 0), new OreDictItemData(MT.MeatRaw, U*4));
		IL.BoP_Celestial                        .set(ST.make(MD.BoP, "misc"                                 , 1, 4));
		IL.BoP_Celestial_Block                  .set(ST.make(MD.BoP, "crystal"                              , 1, 0));
		IL.BoP_Dye_Blue                         .set(ST.make(MD.BoP, "misc"                                 , 1, 5), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue]);
		IL.BoP_Dye_Brown                        .set(ST.make(MD.BoP, "misc"                                 , 1, 6), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Brown]);
		IL.BoP_Dye_Green                        .set(ST.make(MD.BoP, "misc"                                 , 1, 7), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Green]);
		IL.BoP_Dye_White                        .set(ST.make(MD.BoP, "misc"                                 , 1, 8), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_White]);
		IL.BoP_Dye_Black                        .set(ST.make(MD.BoP, "misc"                                 , 1, 9), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Black]);
		IL.BoP_GhastlySoul                      .set(ST.make(MD.BoP, "misc"                                 , 1,10));
		IL.BoP_PixieDust                        .set(ST.make(MD.BoP, "misc"                                 , 1,11));
		IL.BoP_Ichor                            .set(ST.make(MD.BoP, "misc"                                 , 1,12));
		IL.BoP_Pinecone                         .set(ST.make(MD.BoP, "misc"                                 , 1,13));
		IL.BoP_Bamboo                           .set(ST.make(MD.BoP, "bamboo"                               , 1, 0), OP.stick.dat(MT.Bamboo), OD.bamboo);
		IL.BoP_Hard_Ice                         .set(ST.make(MD.BoP, "hardIce"                              , 1, 0), new OreDictItemData(MT.Ice, U*2));
		IL.BoP_Crag_Rock                        .set(ST.make(MD.BoP, "cragRock"                             , 1, 0), new OreDictItemData(MT.Stone, U), OP.cobblestone);
		IL.BoP_Dirt_Dried                       .set(ST.make(MD.BoP, "driedDirt"                            , 1, 0));
		IL.BoP_Dirt_Hard                        .set(ST.make(MD.BoP, "hardDirt"                             , 1, 0));
		IL.BoP_Sand_Hard                        .set(ST.make(MD.BoP, "hardSand"                             , 1, 0));
		IL.BoP_Grass_Origin                     .set(ST.make(MD.BoP, "originGrass"                          , 1, 0));
		IL.BoP_Grass_Long                       .set(ST.make(MD.BoP, "longGrass"                            , 1, 0));
		IL.BoP_Grass_Netherrack                 .set(ST.make(MD.BoP, "overgrownNetherrack"                  , 1, 0), new OreDictItemData(MT.Netherrack, U));
		IL.BoP_Grass_Endstone                   .set(ST.make(MD.BoP, "bopGrass"                             , 1, 0), new OreDictItemData(MT.Endstone, U));
		IL.BoP_Grass_Smoldering                 .set(ST.make(MD.BoP, "bopGrass"                             , 1, 1));
		IL.BoP_Grass_Loamy                      .set(ST.make(MD.BoP, "newBopGrass"                          , 1, 0));
		IL.BoP_Grass_Sandy                      .set(ST.make(MD.BoP, "newBopGrass"                          , 1, 1));
		IL.BoP_Grass_Silty                      .set(ST.make(MD.BoP, "newBopGrass"                          , 1, 2));
		IL.BoP_Dirt_Loamy                       .set(ST.make(MD.BoP, "newBopDirt"                           , 1, 0));
		IL.BoP_Dirt_Sandy                       .set(ST.make(MD.BoP, "newBopDirt"                           , 1, 2));
		IL.BoP_Dirt_Silty                       .set(ST.make(MD.BoP, "newBopDirt"                           , 1, 4));
		IL.BoP_Coarse_Loamy                     .set(ST.make(MD.BoP, "newBopDirt"                           , 1, 1));
		IL.BoP_Coarse_Sandy                     .set(ST.make(MD.BoP, "newBopDirt"                           , 1, 3));
		IL.BoP_Coarse_Silty                     .set(ST.make(MD.BoP, "newBopDirt"                           , 1, 5));
		IL.BoP_Bone_Small                       .set(ST.make(MD.BoP, "bones"                                , 1, 0), new OreDictItemData(MT.Bone, U* 3));
		IL.BoP_Bone_Medium                      .set(ST.make(MD.BoP, "bones"                                , 1, 1), new OreDictItemData(MT.Bone, U* 6));
		IL.BoP_Bone_Large                       .set(ST.make(MD.BoP, "bones"                                , 1, 2), new OreDictItemData(MT.Bone, U*12));
		
		if (IL.BoP_Dirt_Loamy.exists()) Textures.BlockIcons.DIRTS[2] = new IconContainerCopied(IL.BoP_Dirt_Loamy.block(), 0, SIDE_BOTTOM);
		if (IL.BoP_Dirt_Sandy.exists()) Textures.BlockIcons.DIRTS[3] = new IconContainerCopied(IL.BoP_Dirt_Sandy.block(), 2, SIDE_BOTTOM);
		if (IL.BoP_Dirt_Silty.exists()) Textures.BlockIcons.DIRTS[4] = new IconContainerCopied(IL.BoP_Dirt_Silty.block(), 4, SIDE_BOTTOM);
		
		IL.BoP_Berry                            .set(ST.make(MD.BoP, "food"                                 , 1, 0), null, "cropBerry");
		IL.BoP_ShroomPowder                     .set(ST.make(MD.BoP, "food"                                 , 1, 1));
		IL.BoP_WildCarrots                      .set(ST.make(MD.BoP, "food"                                 , 1, 2), null, "cropWildcarrots");
		IL.BoP_Peach                            .set(ST.make(MD.BoP, "food"                                 , 1, 3), null, "cropPeach");
		IL.BoP_Persimmon                        .set(ST.make(MD.BoP, "food"                                 , 1, 8), null, "cropPersimmon");
		IL.BoP_HoneyComb                        .set(ST.make(MD.BoP, "food"                                 , 1, 9), null, OD.beeComb, OD.materialHoneycomb, "foodFilledhoneycomb");
		IL.BoP_Ambrosia                         .set(ST.make(MD.BoP, "food"                                 , 1,10), null, "foodAmbrosia");
		IL.BoP_Turnip                           .set(ST.make(MD.BoP, "food"                                 , 1,11), null, "cropTurnip");
		IL.BoP_Pear                             .set(ST.make(MD.BoP, "food"                                 , 1,12), null, "cropPear");
		IL.BoP_Turnip_Seeds                     .set(ST.make(MD.BoP, "turnipSeeds"                          , 1, 0), null, "seedTurnip");
		
		IL.BoP_Jar_Empty                        .set(ST.make(MD.BoP, "jarEmpty"                             , 1, 0), new OreDictItemData(MT.Glass, 7*U3));
		IL.BoP_Jar_Honey                        .set(ST.make(MD.BoP, "jarFilled"                            , 1, 0), new OreDictItemData(MT.Glass, 7*U3), "foodHoneydrop");
		IL.BoP_Jar_Poison                       .set(ST.make(MD.BoP, "jarFilled"                            , 1, 1), new OreDictItemData(MT.Glass, 7*U3), OD.itemPoison);
		IL.BoP_Jar_Pixie                        .set(ST.make(MD.BoP, "jarFilled"                            , 1, 2), new OreDictItemData(MT.Glass, 7*U3));
		
		if (IL.BoP_Jar_Honey.item() != null && IL.BoP_Jar_Empty.item() != null) IL.BoP_Jar_Honey.item().setContainerItem(IL.BoP_Jar_Empty.item());
		
		
		IL.HiL_Ironwood                         .set(ST.make(MD.HiL, "tile.hl_ironwoodWood"                 , 1, 0), new OreDictItemData(ANY.Wood, U*8, MT.LiveRoot, U4), OD.logWood);
		
		
		IL.HaC_Log_Cinnamon                     .set(ST.make(MD.HaC, "pamCinnamon"                          , 1, 0), null, OD.logWood);
		IL.HaC_Log_Maple                        .set(ST.make(MD.HaC, "pamMaple"                             , 1, 0), null, OD.logWood);
		IL.HaC_Log_Paperbark                    .set(ST.make(MD.HaC, "pamPaperbark"                         , 1, 0), null, OD.logWood);
		IL.HaC_Cinnamon                         .set(ST.make(MD.HaC, "cinnamonItem"                         , 1, 0), null, "cropCinnamon");
		IL.HaC_Royal_Jelly                      .set(ST.make(MD.HaC, "royaljellyItem"                       , 1, 0), null, "dropRoyalJelly");
		
		
		IL.RoC_Comb_Slippery                    .set(ST.make(MD.RoC, "rotarycraft_item_modinterface"        , 1, 0), null, OD.beeComb);
		IL.RoC_Propolis_Slippery                .set(ST.make(MD.RoC, "rotarycraft_item_modinterface"        , 1, 1), null, OD.listAllpropolis);
		IL.RoC_Ethanol_Extract                  .set(ST.make(MD.RoC, "rotarycraft_item_powders"             , 1,16), new OreDictItemData(MT.Ethanol, U));
		IL.RoC_Ethanol_Crystal                  .set(ST.make(MD.RoC, "rotarycraft_item_ethanol"             , 1, 0), new OreDictItemData(MT.Ethanol, U));
		
		
		IL.JABBA_Dolly                          .set(ST.make(MD.JABBA, "mover"                              , 1, 0));
		IL.JABBA_Dolly_Diamond                  .set(ST.make(MD.JABBA, "moverDiamond"                       , 1, 0));
		
		
		IL.GC_Infinite_Oxygen                   .set(ST.make(MD.GC, "item.infiniteOxygen"                   , 1, 0)); ItemsGT.DEBUG_ITEMS.add(IL.GC_Infinite_Oxygen.wild(1)); ItemsGT.ILLEGAL_DROPS.add(IL.GC_Infinite_Oxygen.wild(1));
		IL.GC_Infinite_Battery                  .set(ST.make(MD.GC, "item.infiniteBattery"                  , 1, 0)); ItemsGT.DEBUG_ITEMS.add(IL.GC_Infinite_Battery.wild(1)); ItemsGT.ILLEGAL_DROPS.add(IL.GC_Infinite_Battery.wild(1));
		IL.GC_Torch_Glowstone                   .set(ST.make(MD.GC, "tile.glowstoneTorch"                   , 1, 0), new OreDictItemData(ANY.Glowstone, U4, ANY.Wood, U8), OD.blockTorch);
		IL.GC_Canister                          .set(ST.make(MD.GC, "item.oilCanisterPartial"               , 1, 1001));
		IL.GC_OxyTank_1                         .set(ST.make(MD.GC, "item.oxygenTankLightFull"              , 1, 0));
		IL.GC_OxyTank_2                         .set(ST.make(MD.GC, "item.oxygenTankMedFull"                , 1, 0));
		IL.GC_OxyTank_3                         .set(ST.make(MD.GC, "item.oxygenTankHeavyFull"              , 1, 0));
		IL.GC_OxyTank_4                         .set(ST.make(MD.GC_GALAXYSPACE, "item.oxygentank_t4"        , 1, 0));
		IL.GC_OxyTank_5                         .set(ST.make(MD.GC_GALAXYSPACE, "item.oxygentank_t5"        , 1, 0));
		IL.GC_OxyTank_6                         .set(ST.make(MD.GC_GALAXYSPACE, "item.oxygentank_t6"        , 1, 0));
		IL.GC_OxyTank_Env                       .set(ST.make(MD.GC_GALAXYSPACE, "item.oxygentank_epp_t1"    , 1, 0));
		IL.GC_Schematic_1                       .set(ST.make(MD.GC, "item.schematic"                        , 1, 0));
		IL.GC_Schematic_2                       .set(ST.make(MD.GC_PLANETS, "item.schematic"                , 1, 0));
		IL.GC_Schematic_3                       .set(ST.make(MD.GC_GALAXYSPACE, "item.ItemSchematics"       , 1, 0));
		
		
		IL.TG_Ore_Cluster_1                     .set(ST.make(MD.TG, "oreCluster"                            , 1, 0)); ItemsGT.DEBUG_ITEMS.add(IL.TG_Ore_Cluster_1.wild(1)); ItemsGT.ILLEGAL_DROPS.add(IL.TG_Ore_Cluster_1.wild(1));
		IL.TG_Ore_Cluster_2                     .set(ST.make(MD.TG, "oreCluster2"                           , 1, 0)); ItemsGT.DEBUG_ITEMS.add(IL.TG_Ore_Cluster_2.wild(1)); ItemsGT.ILLEGAL_DROPS.add(IL.TG_Ore_Cluster_2.wild(1));
		IL.TG_Spawner_Bug                       .set(ST.make(MD.TG, "TGMonsterSpawner"                      , 1, 0));
		IL.TG_Spawner_Zombie                    .set(ST.make(MD.TG, "TGMonsterSpawner"                      , 1, 1));
		
		if (IL.TG_Ore_Cluster_1.block() != NB) IL.TG_Ore_Cluster_1.block().setHardness(1000).setResistance(6000000).setHarvestLevel(TOOL_pickaxe, 3);
		if (IL.TG_Ore_Cluster_2.block() != NB) IL.TG_Ore_Cluster_2.block().setHardness(1000).setResistance(6000000).setHarvestLevel(TOOL_pickaxe, 3);
		
		
		IL.MFR_Fertilizer                       .set(ST.make(MD.MFR, "fertilizer"                           , 1, 0), null, OD.itemFertilizer, DYE_OREDICTS_MIXABLE[DYE_INDEX_Brown]);
		IL.MFR_Log_Rubber                       .set(ST.make(MD.MFR, "rubberwood.log"                       , 1, 0), null, OD.logRubber);
		IL.MFR_Sapling_Rubber                   .set(ST.make(MD.MFR, "rubberwood.sapling"                   , 1, 0), null, OP.treeSapling);
		IL.MFR_Sapling_Rubber_Sacred            .set(ST.make(MD.MFR, "rubberwood.sapling"                   , 1, 1), null, OP.treeSapling);
		IL.MFR_Sapling_Rubber_Mega              .set(ST.make(MD.MFR, "rubberwood.sapling"                   , 1, 2), null, OP.treeSapling);
		IL.MFR_Sapling_Rubber_Sacred_Mega       .set(ST.make(MD.MFR, "rubberwood.sapling"                   , 1, 3), null, OP.treeSapling);
		IL.MFR_Leaves_Rubber                    .set(ST.make(MD.MFR, "rubberwood.leaves"                    , 1, 0), null, OP.treeLeaves);
		IL.MFR_Leaves_Rubber_Dry                .set(ST.make(MD.MFR, "rubberwood.leaves"                    , 1, 1), null, OP.treeLeaves);
		
		
		IL.AA_Fertilizer                        .set(ST.make(MD.AA, "itemFertilizer"                        , 1, 0), null, OD.itemFertilizer);
		IL.AA_Dye_Black                         .set(ST.make(MD.AA, "itemMisc"                              , 1,17), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Black]);
		IL.AA_Dough_Rice                        .set(ST.make(MD.AA, "itemMisc"                              , 1, 9), null, "foodRiceDough");
		IL.AA_Bread_Rice                        .set(ST.make(MD.AA, "itemFood"                              , 1,17), null, "foodBread");
		IL.AA_XP                                .set(ST.make(MD.AA, "itemSolidifiedExperience"              , 1, 0));
		
		
		IL.HBM_Mercury_Bottle                   .set(ST.make(MD.HBM, "item.bottle_mercury"                  , 1, 0), new OreDictItemData(MT.Hg, U, MT.Glass, U), OP.bottle.dat(MT.Hg));
		IL.HBM_Mercury_Drop                     .set(ST.make(MD.HBM, "item.nugget_mercury"                  , 1, 0), new OreDictItemData(MT.Hg, U8));
		
		
		IL.ICBM_Concrete                        .set(ST.make(MD.ICBM, "icbmCConcrete"                       , 1, 0));
		
		
		IL.BC_Wrench                            .set(ST.make(MD.BC, "wrenchItem"                            , 1, 0));
		
		
		IL.AE_Wrench_Certus                     .set(ST.make(MD.AE, "item.ToolCertusQuartzWrench"           , 1, 0));
		IL.AE_Wrench_Quartz                     .set(ST.make(MD.AE, "item.ToolNetherQuartzWrench"           , 1, 0));
		
		
		IL.FR_Wrench                            .set(ST.make(MD.FR, "wrench"                                , 1, 0));
		IL.FR_Planks_Fireproof                  .set(ST.make(MD.FR, "planksFireproof"                       , 1, 0));
		IL.FR_Planks                            .set(ST.make(MD.FR, "planks"                                , 1, 0));
		IL.FR_Slabs_Fireproof                   .set(ST.make(MD.FR, "slabsFireproof"                        , 1, 0));
		IL.FR_Slabs                             .set(ST.make(MD.FR, "slabs"                                 , 1, 0));
		IL.FR_Logs_Fireproof                    .set(ST.make(MD.FR, "logsFireproof"                         , 1, 0));
		IL.FR_Logs                              .set(ST.make(MD.FR, "logs"                                  , 1, 0));
		IL.FR_Phosphor                          .set(ST.make(MD.FR, "phosphor"                              , 1, 0), new OreDictItemData(MT.P, U));
		IL.FR_Royal_Jelly                       .set(ST.make(MD.FR, "royalJelly"                            , 1, 0), null, "dropRoyalJelly");
		IL.FR_Propolis                          .set(ST.make(MD.FR, "propolis"                              , 1, 0), null, OD.listAllpropolis);
		IL.FR_Propolis_Sticky                   .set(ST.make(MD.FR, "propolis"                              , 1, 1), null, OD.listAllpropolis);
		IL.FR_Propolis_Pulsating                .set(ST.make(MD.FR, "propolis"                              , 1, 2), null, OD.listAllpropolis);
		IL.FR_Propolis_Silky                    .set(ST.make(MD.FR, "propolis"                              , 1, 3), null, OD.listAllpropolis);
		IL.FRMB_Propolis_Unstable               .set(ST.make(MD.FRMB, "propolis"                            , 1, 0), null, OD.listAllpropolis);
		IL.FRMB_Propolis_Breezey                .set(ST.make(MD.FRMB, "propolis"                            , 1, 1), null, OD.listAllpropolis);
		IL.FRMB_Propolis_Burning                .set(ST.make(MD.FRMB, "propolis"                            , 1, 2), null, OD.listAllpropolis);
		IL.FRMB_Propolis_Flowing                .set(ST.make(MD.FRMB, "propolis"                            , 1, 3), null, OD.listAllpropolis);
		IL.FRMB_Propolis_Stony                  .set(ST.make(MD.FRMB, "propolis"                            , 1, 4), null, OD.listAllpropolis);
		IL.FRMB_Propolis_Ordered                .set(ST.make(MD.FRMB, "propolis"                            , 1, 5), null, OD.listAllpropolis);
		IL.FRMB_Propolis_Chaotic                .set(ST.make(MD.FRMB, "propolis"                            , 1, 6), null, OD.listAllpropolis);
		if (MD.BINNIE_PATCHER.mLoaded) {
		IL.BINNIE_Propolis_Water                .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 0), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Oil                  .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 1), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Petroleum            .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 2), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Milk                 .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 3), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Fruit                .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 4), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Seed                 .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 5), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Alcohol              .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 6), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Creosote             .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 7), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Glacial              .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 8), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Peat                 .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 9), null, OD.listAllpropolis);
		} else {
		IL.BINNIE_Propolis_Water                .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 0), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Oil                  .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 1), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Petroleum            .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 2), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Creosote             .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 3), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Fruit                .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 4), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Seed                 .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 5), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Alcohol              .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 6), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Milk                 .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 7), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Glacial              .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 8), null, OD.listAllpropolis);
		IL.BINNIE_Propolis_Peat                 .set(ST.make(MD.BINNIE_BEE, "propolis"                      , 1, 9), null, OD.listAllpropolis);
		}
		IL.FR_Comb_Honey                        .set(ST.make(MD.FR, "beeCombs"                              , 1, 0), null, OD.beeComb, OD.materialHoneycomb, "foodFilledhoneycomb");
		IL.FR_Comb_Cocoa                        .set(ST.make(MD.FR, "beeCombs"                              , 1, 1), null, OD.beeComb);
		IL.FR_Comb_Simmering                    .set(ST.make(MD.FR, "beeCombs"                              , 1, 2), null, OD.beeComb);
		IL.FR_Comb_Stringy                      .set(ST.make(MD.FR, "beeCombs"                              , 1, 3), null, OD.beeComb);
		IL.FR_Comb_Frozen                       .set(ST.make(MD.FR, "beeCombs"                              , 1, 4), null, OD.beeComb);
		IL.FR_Comb_Dripping                     .set(ST.make(MD.FR, "beeCombs"                              , 1, 5), null, OD.beeComb);
		IL.FR_Comb_Silky                        .set(ST.make(MD.FR, "beeCombs"                              , 1, 6), null, OD.beeComb);
		IL.FR_Comb_Parched                      .set(ST.make(MD.FR, "beeCombs"                              , 1, 7), null, OD.beeComb);
		IL.FR_Comb_Mysterious                   .set(ST.make(MD.FR, "beeCombs"                              , 1, 8), null, OD.beeComb);
		IL.FR_Comb_Irradiated                   .set(ST.make(MD.FR, "beeCombs"                              , 1, 9), null, OD.beeComb);
		IL.FR_Comb_Powdery                      .set(ST.make(MD.FR, "beeCombs"                              , 1,10), null, OD.beeComb);
		IL.FR_Comb_Reddened                     .set(ST.make(MD.FR, "beeCombs"                              , 1,11), null, OD.beeComb);
		IL.FR_Comb_Darkened                     .set(ST.make(MD.FR, "beeCombs"                              , 1,12), null, OD.beeComb);
		IL.FR_Comb_Omega                        .set(ST.make(MD.FR, "beeCombs"                              , 1,13), null, OD.beeComb);
		IL.FR_Comb_Wheaten                      .set(ST.make(MD.FR, "beeCombs"                              , 1,14), null, OD.beeComb);
		IL.FR_Comb_Mossy                        .set(ST.make(MD.FR, "beeCombs"                              , 1,15), null, OD.beeComb);
		IL.FR_Comb_Mellow                       .set(ST.make(MD.FR, "beeCombs"                              , 1,16), null, OD.beeComb);
		if (MD.BINNIE_PATCHER.mLoaded) {
		IL.BINNIE_Comb_Barren                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 0), null, OD.beeComb);
		IL.BINNIE_Comb_Rotten                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 1), null, OD.beeComb);
		IL.BINNIE_Comb_Bone                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 2), null, OD.beeComb);
		IL.BINNIE_Comb_Oil                      .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 3), null, OD.beeComb);
		IL.BINNIE_Comb_Fossil                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 4), null, OD.beeComb);
		IL.BINNIE_Comb_Petroleum                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 5), null, OD.beeComb);
		IL.BINNIE_Comb_Damp                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 6), null, OD.beeComb);
		IL.BINNIE_Comb_Milk                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 7), null, OD.beeComb);
		IL.BINNIE_Comb_Fruit                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 8), null, OD.beeComb);
		IL.BINNIE_Comb_Seed                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 9), null, OD.beeComb);
		IL.BINNIE_Comb_Alcohol                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,10), null, OD.beeComb);
		IL.BINNIE_Comb_Rock                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,11), null, OD.beeComb);
		IL.BINNIE_Comb_Energetic                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,12), null, OD.beeComb);
		IL.BINNIE_Comb_Amber                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,13), null, OD.beeComb);
		IL.BINNIE_Comb_Static                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,14), null, OD.beeComb);
		IL.BINNIE_Comb_Iron                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,15), null, OD.beeComb);
		IL.BINNIE_Comb_Gold                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,16), null, OD.beeComb);
		IL.BINNIE_Comb_Copper                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,17), null, OD.beeComb);
		IL.BINNIE_Comb_Tin                      .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,18), null, OD.beeComb);
		IL.BINNIE_Comb_Silver                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,19), null, OD.beeComb);
		IL.BINNIE_Comb_Bronze                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,20), null, OD.beeComb);
		IL.BINNIE_Comb_Uranium                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,21), null, OD.beeComb);
		IL.BINNIE_Comb_Clay                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,22), null, OD.beeComb);
		IL.BINNIE_Comb_Old                      .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,23), null, OD.beeComb);
		IL.BINNIE_Comb_Fungal                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,24), null, OD.beeComb);
		IL.BINNIE_Comb_Tar                      .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,25), null, OD.beeComb);
		IL.BINNIE_Comb_Latex                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,26), null, OD.beeComb);
		IL.BINNIE_Comb_Brimstone                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,27), null, OD.beeComb);
		IL.BINNIE_Comb_Venom                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,28), null, OD.beeComb);
		IL.BINNIE_Comb_Mucous                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,29), null, OD.beeComb);
		IL.BINNIE_Comb_Blaze                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,30), null, OD.beeComb);
		IL.BINNIE_Comb_Coffee                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,31), null, OD.beeComb);
		IL.BINNIE_Comb_Glacial                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,32), null, OD.beeComb);
		IL.BINNIE_Comb_Mint                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,33), null, OD.beeComb);
		IL.BINNIE_Comb_Citrus                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,34), null, OD.beeComb);
		IL.BINNIE_Comb_Peat                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,35), null, OD.beeComb);
		IL.BINNIE_Comb_Shadow                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,36), null, OD.beeComb);
		IL.BINNIE_Comb_Lead                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,37), null, OD.beeComb);
		IL.BINNIE_Comb_Brass                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,38), null, OD.beeComb);
		IL.BINNIE_Comb_Electrum                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,39), null, OD.beeComb);
		IL.BINNIE_Comb_Zinc                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,40), null, OD.beeComb);
		IL.BINNIE_Comb_Titanium                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,41), null, OD.beeComb);
		IL.BINNIE_Comb_Tungsten                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,42), null, OD.beeComb);
		IL.BINNIE_Comb_Steel                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,43), null, OD.beeComb);
		IL.BINNIE_Comb_Iridium                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,44), null, OD.beeComb);
		IL.BINNIE_Comb_Platinum                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,45), null, OD.beeComb);
		IL.BINNIE_Comb_Lapis                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,46), null, OD.beeComb);
		IL.BINNIE_Comb_Sodalite                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,47), null, OD.beeComb);
		IL.BINNIE_Comb_Pyrite                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,48), null, OD.beeComb);
		IL.BINNIE_Comb_Bauxite                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,49), null, OD.beeComb);
		IL.BINNIE_Comb_Cinnabar                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,50), null, OD.beeComb);
		IL.BINNIE_Comb_Sphalerite               .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,51), null, OD.beeComb);
		IL.BINNIE_Comb_Emerald                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,52), null, OD.beeComb);
		IL.BINNIE_Comb_Ruby                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,53), null, OD.beeComb);
		IL.BINNIE_Comb_Sapphire                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,54), null, OD.beeComb);
		IL.BINNIE_Comb_Olivine                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,55), null, OD.beeComb);
		IL.BINNIE_Comb_Diamond                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,56), null, OD.beeComb);
		IL.BINNIE_Comb_Red                      .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,57), null, OD.beeComb);
		IL.BINNIE_Comb_Yellow                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,58), null, OD.beeComb);
		IL.BINNIE_Comb_Blue                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,59), null, OD.beeComb);
		IL.BINNIE_Comb_Green                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,60), null, OD.beeComb);
		IL.BINNIE_Comb_Black                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,61), null, OD.beeComb);
		IL.BINNIE_Comb_White                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,62), null, OD.beeComb);
		IL.BINNIE_Comb_Brown                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,63), null, OD.beeComb);
		IL.BINNIE_Comb_Orange                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,64), null, OD.beeComb);
		IL.BINNIE_Comb_Cyan                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,65), null, OD.beeComb);
		IL.BINNIE_Comb_Purple                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,66), null, OD.beeComb);
		IL.BINNIE_Comb_Gray                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,67), null, OD.beeComb);
		IL.BINNIE_Comb_LightBlue                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,68), null, OD.beeComb);
		IL.BINNIE_Comb_Pink                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,69), null, OD.beeComb);
		IL.BINNIE_Comb_Lime                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,70), null, OD.beeComb);
		IL.BINNIE_Comb_Magenta                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,71), null, OD.beeComb);
		IL.BINNIE_Comb_LightGray                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,72), null, OD.beeComb);
		IL.BINNIE_Comb_Nickel                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,73), null, OD.beeComb);
		IL.BINNIE_Comb_Invar                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,74), null, OD.beeComb);
		IL.BINNIE_Comb_Glowing                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,75), null, OD.beeComb);
		IL.BINNIE_Comb_Unstable                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,76), null, OD.beeComb);
		IL.BINNIE_Comb_Pulp                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,77), null, OD.beeComb);
		IL.BINNIE_Comb_Mulch                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,78), null, OD.beeComb);
		IL.BINNIE_Comb_Decomposed               .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,79), null, OD.beeComb);
		IL.BINNIE_Comb_Dusty                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,80), null, OD.beeComb);
		IL.BINNIE_Comb_Certus                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,81), null, OD.beeComb);
		IL.BINNIE_Comb_Shimmering               .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,82), null, OD.beeComb);
		IL.BINNIE_Comb_Yellorium                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,83), null, OD.beeComb);
		IL.BINNIE_Comb_Cyanite                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,84), null, OD.beeComb);
		IL.BINNIE_Comb_Blutonium                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,85), null, OD.beeComb);
		} else {
		IL.BINNIE_Comb_Barren                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 0), null, OD.beeComb);
		IL.BINNIE_Comb_Rotten                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 1), null, OD.beeComb);
		IL.BINNIE_Comb_Bone                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 2), null, OD.beeComb);
		IL.BINNIE_Comb_Oil                      .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 3), null, OD.beeComb);
		IL.BINNIE_Comb_Fossil                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 4), null, OD.beeComb);
		IL.BINNIE_Comb_Petroleum                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 5), null, OD.beeComb);
		IL.BINNIE_Comb_Damp                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 6), null, OD.beeComb);
		IL.BINNIE_Comb_Milk                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 7), null, OD.beeComb);
		IL.BINNIE_Comb_Fruit                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 8), null, OD.beeComb);
		IL.BINNIE_Comb_Seed                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1, 9), null, OD.beeComb);
		IL.BINNIE_Comb_Alcohol                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,10), null, OD.beeComb);
		IL.BINNIE_Comb_Rock                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,11), null, OD.beeComb);
		IL.BINNIE_Comb_Energetic                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,12), null, OD.beeComb);
		IL.BINNIE_Comb_Amber                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,13), null, OD.beeComb);
		IL.BINNIE_Comb_Static                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,14), null, OD.beeComb);
		IL.BINNIE_Comb_Iron                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,15), null, OD.beeComb);
		IL.BINNIE_Comb_Gold                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,16), null, OD.beeComb);
		IL.BINNIE_Comb_Copper                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,17), null, OD.beeComb);
		IL.BINNIE_Comb_Tin                      .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,18), null, OD.beeComb);
		IL.BINNIE_Comb_Silver                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,19), null, OD.beeComb);
		IL.BINNIE_Comb_Uranium                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,20), null, OD.beeComb);
		IL.BINNIE_Comb_Clay                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,21), null, OD.beeComb);
		IL.BINNIE_Comb_Old                      .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,22), null, OD.beeComb);
		IL.BINNIE_Comb_Fungal                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,23), null, OD.beeComb);
		IL.BINNIE_Comb_Tar                      .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,24), null, OD.beeComb);
		IL.BINNIE_Comb_Latex                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,25), null, OD.beeComb);
		IL.BINNIE_Comb_Brimstone                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,26), null, OD.beeComb);
		IL.BINNIE_Comb_Venom                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,27), null, OD.beeComb);
		IL.BINNIE_Comb_Mucous                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,28), null, OD.beeComb);
		IL.BINNIE_Comb_Blaze                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,29), null, OD.beeComb);
		IL.BINNIE_Comb_Coffee                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,30), null, OD.beeComb);
		IL.BINNIE_Comb_Glacial                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,31), null, OD.beeComb);
		IL.BINNIE_Comb_Shadow                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,32), null, OD.beeComb);
		IL.BINNIE_Comb_Lead                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,33), null, OD.beeComb);
		IL.BINNIE_Comb_Brass                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,34), null, OD.beeComb);
		IL.BINNIE_Comb_Titanium                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,35), null, OD.beeComb);
		IL.BINNIE_Comb_Tungsten                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,36), null, OD.beeComb);
		IL.BINNIE_Comb_Platinum                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,37), null, OD.beeComb);
		IL.BINNIE_Comb_Lapis                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,38), null, OD.beeComb);
		IL.BINNIE_Comb_Emerald                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,39), null, OD.beeComb);
		IL.BINNIE_Comb_Ruby                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,40), null, OD.beeComb);
		IL.BINNIE_Comb_Sapphire                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,41), null, OD.beeComb);
		IL.BINNIE_Comb_Diamond                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,42), null, OD.beeComb);
		IL.BINNIE_Comb_Red                      .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,43), null, OD.beeComb);
		IL.BINNIE_Comb_Yellow                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,44), null, OD.beeComb);
		IL.BINNIE_Comb_Blue                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,45), null, OD.beeComb);
		IL.BINNIE_Comb_Green                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,46), null, OD.beeComb);
		IL.BINNIE_Comb_Black                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,47), null, OD.beeComb);
		IL.BINNIE_Comb_White                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,48), null, OD.beeComb);
		IL.BINNIE_Comb_Brown                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,49), null, OD.beeComb);
		IL.BINNIE_Comb_Orange                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,50), null, OD.beeComb);
		IL.BINNIE_Comb_Cyan                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,51), null, OD.beeComb);
		IL.BINNIE_Comb_Purple                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,52), null, OD.beeComb);
		IL.BINNIE_Comb_Gray                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,53), null, OD.beeComb);
		IL.BINNIE_Comb_LightBlue                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,54), null, OD.beeComb);
		IL.BINNIE_Comb_Pink                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,55), null, OD.beeComb);
		IL.BINNIE_Comb_Lime                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,56), null, OD.beeComb);
		IL.BINNIE_Comb_Magenta                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,57), null, OD.beeComb);
		IL.BINNIE_Comb_LightGray                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,58), null, OD.beeComb);
		IL.BINNIE_Comb_Nickel                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,59), null, OD.beeComb);
		IL.BINNIE_Comb_Glowing                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,60), null, OD.beeComb);
		IL.BINNIE_Comb_Unstable                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,61), null, OD.beeComb);
		IL.BINNIE_Comb_Decomposed               .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,62), null, OD.beeComb);
		IL.BINNIE_Comb_Dusty                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,63), null, OD.beeComb);
		IL.BINNIE_Comb_Certus                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,64), null, OD.beeComb);
		IL.BINNIE_Comb_Shimmering               .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,65), null, OD.beeComb);
		IL.BINNIE_Comb_Yellorium                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,66), null, OD.beeComb);
		IL.BINNIE_Comb_Cyanite                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,67), null, OD.beeComb);
		IL.BINNIE_Comb_Blutonium                .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,68), null, OD.beeComb);
		IL.BINNIE_Comb_Bronze                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,69), null, OD.beeComb);
		IL.BINNIE_Comb_Mint                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,70), null, OD.beeComb);
		IL.BINNIE_Comb_Citrus                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,71), null, OD.beeComb);
		IL.BINNIE_Comb_Peat                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,72), null, OD.beeComb);
		IL.BINNIE_Comb_Electrum                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,73), null, OD.beeComb);
		IL.BINNIE_Comb_Zinc                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,74), null, OD.beeComb);
		IL.BINNIE_Comb_Steel                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,75), null, OD.beeComb);
		IL.BINNIE_Comb_Iridium                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,76), null, OD.beeComb);
		IL.BINNIE_Comb_Sodalite                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,77), null, OD.beeComb);
		IL.BINNIE_Comb_Pyrite                   .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,78), null, OD.beeComb);
		IL.BINNIE_Comb_Bauxite                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,79), null, OD.beeComb);
		IL.BINNIE_Comb_Cinnabar                 .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,80), null, OD.beeComb);
		IL.BINNIE_Comb_Sphalerite               .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,81), null, OD.beeComb);
		IL.BINNIE_Comb_Olivine                  .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,82), null, OD.beeComb);
		IL.BINNIE_Comb_Invar                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,83), null, OD.beeComb);
		IL.BINNIE_Comb_Pulp                     .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,84), null, OD.beeComb);
		IL.BINNIE_Comb_Mulch                    .set(ST.make(MD.BINNIE_BEE, "honeyComb"                     , 1,85), null, OD.beeComb);
		}
		IL.FR_Pollen_Cluster                    .set(ST.make(MD.FR, "pollen"                                , 1, 0));
		IL.FR_Pollen_Cluster_Crystalline        .set(ST.make(MD.FR, "pollen"                                , 1, 1));
		IL.FR_Mulch                             .set(ST.make(MD.FR, "mulch"                                 , 1, 0));
		IL.FR_Fertilizer                        .set(ST.make(MD.FR, "fertilizerCompound"                    , 1, 0), null, OD.itemFertilizer);
		IL.FR_Compost                           .set(ST.make(MD.FR, "fertilizerBio"                         , 1, 0));
		IL.FR_Pulsating_Dust                    .set(ST.make(MD.FR, "craftingMaterial"                      , 1, 0), new OreDictItemData(MT.EnderPearl, U));
		IL.FR_Pulsating_Mesh                    .set(ST.make(MD.FR, "craftingMaterial"                      , 1, 1));
		IL.FR_Silk                              .set(ST.make(MD.FR, "craftingMaterial"                      , 1, 2));
		IL.FR_Silk_Woven                        .set(ST.make(MD.FR, "craftingMaterial"                      , 1, 3));
		IL.FR_Dissipation_Capsule               .set(ST.make(MD.FR, "craftingMaterial"                      , 1, 4));
		IL.FR_Ice_Shard                         .set(ST.make(MD.FR, "craftingMaterial"                      , 1, 5), new OreDictItemData(MT.Ice, U));
		IL.FR_Iodine_Capsule                    .set(ST.make(MD.FR, "iodineCapsule"                         , 1, 0));
		IL.FR_HoneyPot                          .set(ST.make(MD.FR, "honeyPot"                              , 1, 0));
		IL.FR_Ambrosia                          .set(ST.make(MD.FR, "ambrosia"                              , 1, 0), null, "foodAmbrosia");
		IL.FR_TinCapsule                        .set(ST.make(MD.FR, "canEmpty"                              , 1, 0), new OreDictItemData(MT.Sn, 3*U12));
		IL.FR_WaxCapsule                        .set(ST.make(MD.FR, "waxCapsule"                            , 1, 0), new OreDictItemData(MT.WaxBee, 3*U4));
		IL.FR_RefractoryCapsule                 .set(ST.make(MD.FR, "refractoryEmpty"                       , 1, 0), new OreDictItemData(MT.WaxRefractory, 3*U4));
		IL.FR_MagicCapsule                      .set(ST.make(MD.FRMB, "capsule.magic"                       , 1, 0), new OreDictItemData(MT.WaxMagic, 3*U4));
		IL.FR_Honey_Bucket                      .set(ST.make(MD.FR, "bucketHoney"                           , 1, 0), new OreDictItemData(ANY.Fe, U*3), OD.container1000honey, "bucketHoney");
		IL.FR_Honey_Can                         .set(ST.make(MD.FR, "canHoney"                              , 1, 0), null, OD.container1000honey);
		IL.FR_Honey_Capsule                     .set(ST.make(MD.FR, "waxCapsuleHoney"                       , 1, 0), null, OD.container1000honey);
		IL.FR_Honey_RefractoryCapsule           .set(ST.make(MD.FR, "refractoryHoney"                       , 1, 0), null, OD.container1000honey);
		IL.FR_Water_Can                         .set(ST.make(MD.FR, "canWater"                              , 1, 0), null, OD.container1000water);
		IL.FR_Water_Capsule                     .set(ST.make(MD.FR, "waxCapsuleWater"                       , 1, 0), null, OD.container1000water);
		IL.FR_Water_RefractoryCapsule           .set(ST.make(MD.FR, "refractoryWater"                       , 1, 0), null, OD.container1000water);
		IL.FR_Lava_RefractoryCapsule            .set(ST.make(MD.FR, "refractoryLava"                        , 1, 0), null, OD.container1000lava);
		IL.FR_Candle                            .set(ST.make(MD.FR, "candle"                                , 1, 0), null, OD.blockCandle);
		IL.FR_Bee_Drone                         .set(ST.make(MD.FR, "beeDroneGE"                            , 1, 0));
		IL.FR_Bee_Princess                      .set(ST.make(MD.FR, "beePrincessGE"                         , 1, 0));
		IL.FR_Bee_Queen                         .set(ST.make(MD.FR, "beeQueenGE"                            , 1, 0));
		IL.FR_Tree_Sapling                      .set(ST.make(MD.FR, "sapling"                               , 1, 0, ST.make(MD.FR, "saplingGE", 1)));
		IL.FR_Butterfly                         .set(ST.make(MD.FR, "butterflyGE"                           , 1, 0));
		IL.FR_Larvae                            .set(ST.make(MD.FR, "beeLarvaeGE"                           , 1, 0));
		IL.FR_Serum                             .set(ST.make(MD.FR, "serumGE"                               , 1, 0));
		IL.FR_Caterpillar                       .set(ST.make(MD.FR, "caterpillarGE"                         , 1, 0));
		IL.FR_PollenFertile                     .set(ST.make(MD.FR, "pollenFertile"                         , 1, 0));
		IL.FR_Stick                             .set(ST.make(MD.FR, "oakStick"                              , 1, 0));
		IL.FR_Scoop                             .set(ST.make(MD.FR, "scoop"                                 , 1, 0));
		IL.FR_Casing_Impregnated                .set(ST.make(MD.FR, "impregnatedCasing"                     , 1, 0), new OreDictItemData(ANY.Wood   , U*64));
		IL.FR_Casing_Sturdy                     .set(ST.make(MD.FR, "sturdyMachine"                         , 1, 0), new OreDictItemData(MT.Bronze  , U* 8));
		IL.FR_Casing_Hardened                   .set(ST.make(MD.FR, "hardenedMachine"                       , 1, 0), new OreDictItemData(MT.Bronze  , U* 8, ANY.Diamond, U * 4));
		IL.FR_Chipset_Tin                       .set(ST.make(MD.FR, "chipsets"                              , 1, 0), new OreDictItemData(MT.Sn      , U* 1, MT.Redstone, U * 6));
		IL.FR_Chipset_Bronze                    .set(ST.make(MD.FR, "chipsets"                              , 1, 1), new OreDictItemData(MT.Bronze  , U* 3, MT.Redstone, U * 6));
		IL.FR_Chipset_Iron                      .set(ST.make(MD.FR, "chipsets"                              , 1, 2), new OreDictItemData(ANY.Fe     , U* 3, MT.Redstone, U * 6));
		IL.FR_Chipset_Gold                      .set(ST.make(MD.FR, "chipsets"                              , 1, 3), new OreDictItemData(MT.Au      , U* 3, MT.Redstone, U * 6));
		IL.FR_ElectronTube_Copper               .set(ST.make(MD.FR, "thermionicTubes"                       , 1, 0), new OreDictItemData(MT.Cu      , 5*U4, MT.Redstone, U2, MT.Glass, U8));
		IL.FR_ElectronTube_Tin                  .set(ST.make(MD.FR, "thermionicTubes"                       , 1, 1), new OreDictItemData(MT.Sn      , 5*U4, MT.Redstone, U2, MT.Glass, U8));
		IL.FR_ElectronTube_Bronze               .set(ST.make(MD.FR, "thermionicTubes"                       , 1, 2), new OreDictItemData(MT.Bronze  , 5*U4, MT.Redstone, U2, MT.Glass, U8));
		IL.FR_ElectronTube_Iron                 .set(ST.make(MD.FR, "thermionicTubes"                       , 1, 3), new OreDictItemData(ANY.Fe     , 5*U4, MT.Redstone, U2, MT.Glass, U8));
		IL.FR_ElectronTube_Gold                 .set(ST.make(MD.FR, "thermionicTubes"                       , 1, 4), new OreDictItemData(MT.Au      , 5*U4, MT.Redstone, U2, MT.Glass, U8));
		IL.FR_ElectronTube_Diamond              .set(ST.make(MD.FR, "thermionicTubes"                       , 1, 5), new OreDictItemData(ANY.Diamond, 5*U4, MT.Redstone, U2, MT.Glass, U8));
		IL.FR_ElectronTube_Obsidian             .set(ST.make(MD.FR, "thermionicTubes"                       , 1, 6), new OreDictItemData(MT.Obsidian, 5*U4, MT.Redstone, U2, MT.Glass, U8));
		IL.FR_ElectronTube_Blaze                .set(ST.make(MD.FR, "thermionicTubes"                       , 1, 7), new OreDictItemData(MT.Blaze   , 5*U4, MT.Redstone, U2, MT.Glass, U8));
		IL.FR_ElectronTube_Rubber               .set(ST.make(MD.FR, "thermionicTubes"                       , 1, 8), new OreDictItemData(MT.Rubber  , 5*U4, MT.Redstone, U2, MT.Glass, U8));
		IL.FR_ElectronTube_Emerald              .set(ST.make(MD.FR, "thermionicTubes"                       , 1, 9), new OreDictItemData(ANY.Emerald, 5*U4, MT.Redstone, U2, MT.Glass, U8));
		IL.FR_ElectronTube_Apatite              .set(ST.make(MD.FR, "thermionicTubes"                       , 1,10), new OreDictItemData(MT.Apatite , 5*U4, MT.Redstone, U2, MT.Glass, U8));
		IL.FR_ElectronTube_Lapis                .set(ST.make(MD.FR, "thermionicTubes"                       , 1,11), new OreDictItemData(MT.Lapis   , 5*U4, MT.Redstone, U2, MT.Glass, U8));
		IL.FR_ElectronTube_Ender                .set(ST.make(MD.FR, "thermionicTubes"                       , 1,12), new OreDictItemData(MT.Endstone, 5*U4, MT.EnderEye, U2, MT.Glass, U8));
		IL.BINNIE_Dye_Red                       .set(ST.make(MD.BINNIE_BEE, "misc"                          , 1,19), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Red]);
		IL.BINNIE_Dye_Yellow                    .set(ST.make(MD.BINNIE_BEE, "misc"                          , 1,20), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Yellow]);
		IL.BINNIE_Dye_Blue                      .set(ST.make(MD.BINNIE_BEE, "misc"                          , 1,21), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Blue]);
		IL.BINNIE_Dye_Green                     .set(ST.make(MD.BINNIE_BEE, "misc"                          , 1,22), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Green]);
		IL.BINNIE_Dye_White                     .set(ST.make(MD.BINNIE_BEE, "misc"                          , 1,23), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_White]);
		IL.BINNIE_Dye_Black                     .set(ST.make(MD.BINNIE_BEE, "misc"                          , 1,24), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Black]);
		IL.BINNIE_Dye_Brown                     .set(ST.make(MD.BINNIE_BEE, "misc"                          , 1,25), null, DYE_OREDICTS_MIXABLE[DYE_INDEX_Brown]);
		
		ST.item(MD.BINNIE, "containerGlass", Items.potionitem).setContainerItem(Items.glass_bottle);
		
		if (MD.IC2C.mLoaded) {
		IL.IC2_Iridium_Shard                    .set(NI);
		IL.IC2_Energium_Dust                    .set(NI);
		IL.IC2_Fuel_Rod_Empty                   .set(NI);
		IL.IC2_Biochaff                         .set(ST.mkic("plantBall"                                    , 1   ));
		IL.IC2_ShaftIron                        .set(NI);
		IL.IC2_ShaftSteel                       .set(NI);
		IL.IC2_ForgeHammer                      .set(NI);
		IL.IC2_Debug                            .set(NI);
		IL.IC2_AdvBattery                       .set(NI);
		IL.Cell_Universal_Fluid                 .set(NI);
		IL.Cell_UUM                             .set(NI);
		IL.Cell_CFoam                           .set(NI);
		} else {
		IL.IC2_Iridium_Shard                    .set(ST.mkic("iridiumShard"                                 , 1   ), new OreDictItemData(MT.Ir, U9));
		IL.IC2_Energium_Dust                    .set(ST.mkic("energiumDust"                                 , 1   ));
		IL.IC2_Fuel_Rod_Empty                   .set(ST.mkic("fuelRod"                                      , 1   ), new OreDictItemData(MD.GT.mLoaded?MT.Zr:ANY.Fe, U));
		IL.IC2_Biochaff                         .set(ST.mkic("biochaff"                                     , 1   ));
		IL.IC2_ShaftIron                        .set(ST.mkic("ironshaft"                                    , 1   ), new OreDictItemData(ANY.Fe, U*9));
		IL.IC2_ShaftSteel                       .set(ST.mkic("steelshaft"                                   , 1   ), new OreDictItemData(ANY.Steel, U*9));
		IL.IC2_ForgeHammer                      .set(ST.mkic("ForgeHammer"                                  , 1   ));
		IL.IC2_Debug                            .set(ST.mkic("debug"                                        , 1   )); ItemsGT.DEBUG_ITEMS.add(IL.IC2_Debug.wild(1)); ItemsGT.ILLEGAL_DROPS.add(IL.IC2_Debug.wild(1));
		IL.IC2_AdvBattery                       .set(ST.mkic("advBattery"                                   , 1   ));
		IL.Cell_Universal_Fluid                 .set(ST.mkic("FluidCell"                                    , 1   ), new OreDictItemData(MT.Sn, U*2, OM.stack(MT.Glass, 3*U8)));
		IL.Cell_UUM                             .set(ST.mkic("uuMatterCell"                                 , 1   ), OP.cell.dat(MT.UUMatter));
		IL.Cell_CFoam                           .set(ST.mkic("CFCell"                                       , 1   ), OP.cell.dat(MT.ConstructionFoam));
		}
		
		IL.IC2_ITNT                             .set(ST.mkic("industrialTnt"                                , 1   ));
		IL.IC2_Iridium_Ore                      .set(ST.mkic("iridiumOre"                                   , 1   ), new OreDictItemData(MT.Ir, U));
		IL.IC2_Scaffold                         .set(ST.mkic("scaffold"                                     , 1   ), new OreDictItemData(ANY.Wood, 9*U8));
		IL.IC2_Scaffold_Iron                    .set(ST.mkic("ironScaffold"                                 , 1   ), new OreDictItemData(ANY.Fe, 15*U32));
		IL.IC2_Foam                             .set(ST.mkic("constructionFoam"                             , 1   ));
		IL.IC2_Foam_Reinforced                  .set(ST.mkic("constructionreinforcedFoam"                   , 1   ));
		IL.IC2_Wall                             .set(ST.mkic("constructionFoamWall"                         , 1   ));
		IL.IC2_Wall_Reinforced                  .set(ST.mkic("reinforcedStone"                              , 1   ));
		IL.IC2_Glass_Reinforced                 .set(ST.mkic("reinforcedGlass"                              , 1   ));
		IL.IC2_Spray_WeedEx                     .set(ST.mkic("weedEx"                                       , 1   ), new OreDictItemData(MT.Sn, U, MT.Redstone, U));
		IL.IC2_Mixed_Metal_Ingot                .set(ST.mkic("mixedMetalIngot"                              , 1   ));
		IL.IC2_Fertilizer                       .set(ST.mkic("fertilizer"                                   , 1   ), null, OD.itemFertilizer);
		IL.IC2_Resin                            .set(ST.mkic("resin"                                        , 1   ), null, OD.itemResin);
		IL.IC2_Log_Rubber                       .set(ST.mkic("rubberWood"                                   , 1   ), new OreDictItemData(MT.WoodRubber, U*8, MT.Bark, U), OD.logRubber);
		IL.IC2_Leaves_Rubber                    .set(ST.mkic("rubberLeaves"                                 , 1   ));
		IL.IC2_Sapling_Rubber                   .set(ST.mkic("rubberSapling"                                , 1   ));
		IL.IC2_Plantball                        .set(ST.mkic("plantBall"                                    , 1   ));
		IL.IC2_Crop_Seeds                       .set(ST.mkic("cropSeed"                                     , 1   ), null, "seedCrop");
		IL.IC2_Grin_Powder                      .set(ST.mkic("grinPowder"                                   , 1   ));
		IL.IC2_Scrap                            .set(ST.mkic("scrap"                                        , 1   ));
		IL.IC2_Scrapbox                         .set(ST.mkic("scrapBox"                                     , 1   ));
		IL.IC2_Food_Can_Empty                   .set(ST.mkic("tinCan"                                       , 1   ), new OreDictItemData(MT.Sn, U2));
		IL.IC2_Food_Can_Filled                  .set(ST.mkic("filledTinCan"                                 , 1, 0));
		IL.IC2_Food_Can_Spoiled                 .set(ST.mkic("filledTinCan"                                 , 1, 1));
		IL.IC2_Food_Can_Poisonous               .set(ST.mkic("filledTinCan"                                 , 1, 2));
		IL.IC2_Food_Can_Salmonella              .set(ST.mkic("filledTinCan"                                 , 1, 3));
		IL.IC2_Industrial_Diamond               .set(ST.mkic("industrialDiamond"                            , 1   ));
		IL.IC2_Coal_Ball                        .set(ST.mkic("coalBall"                                     , 1   ), new OreDictItemData(MT.Coal, U*8));
		IL.IC2_Compressed_Coal_Ball             .set(ST.mkic("compressedCoalBall"                           , 1   ), new OreDictItemData(MT.Coal, U*8));
		IL.IC2_Compressed_Coal_Chunk            .set(ST.mkic("coalChunk"                                    , 1   ), new OreDictItemData(MT.Coal, U*64, MT.Obsidian, U*9));
		IL.IC2_Carbon_Fiber                     .set(ST.mkic("carbonFiber"                                  , 1   ));
		IL.IC2_Carbon_Mesh                      .set(ST.mkic("carbonMesh"                                   , 1   ));
		IL.IC2_Carbon_Plate                     .set(ST.mkic("carbonPlate"                                  , 1   ));
		IL.IC2_Advanced_Alloy                   .set(ST.mkic("advancedAlloy"                                , 1   ));
		IL.IC2_Iridium_Alloy                    .set(ST.mkic("iridiumPlate"                                 , 1   ), new OreDictItemData(MT.Ir, U*4, ANY.Diamond, U*1));
		IL.IC2_Machine                          .set(ST.mkic("machine"                                      , 1   ), new OreDictItemData(ANY.Fe, U*8));
		IL.IC2_Machine_Adv                      .set(ST.mkic("advancedMachine"                              , 1   ), new OreDictItemData(ANY.Fe, U*8));
		IL.IC2_Generator                        .set(ST.mkic("generator"                                    , 1   ), new OreDictItemData(ANY.Fe, U*8));
		
		IL.IC2_SuBattery                        .set(ST.mkic("suBattery"                                    , 1   ));
		IL.IC2_ReBattery                        .set(ST.mkic("reBattery"                                    , 1   ));
		IL.IC2_EnergyCrystal                    .set(ST.mkic("energyCrystal"                                , 1   ), new OreDictItemData(MT.EnergiumRed, U*9));
		IL.IC2_LapotronCrystal                  .set(ST.mkic("lapotronCrystal"                              , 1   ), new OreDictItemData(MT.EnergiumRed, U*9));
		
		IL.Tool_Sword_Bronze                    .set(ST.mkic("bronzeSword"                                  , 1, 0), new OreDictItemData(MT.Bronze, OP.toolHeadSword  , ANY.Wood, U2).setUseVanillaDamage());
		IL.Tool_Pickaxe_Bronze                  .set(ST.mkic("bronzePickaxe"                                , 1, 0), new OreDictItemData(MT.Bronze, OP.toolHeadPickaxe, ANY.Wood, U ).setUseVanillaDamage());
		IL.Tool_Shovel_Bronze                   .set(ST.mkic("bronzeShovel"                                 , 1, 0), new OreDictItemData(MT.Bronze, OP.toolHeadShovel , ANY.Wood, U ).setUseVanillaDamage());
		IL.Tool_Axe_Bronze                      .set(ST.mkic("bronzeAxe"                                    , 1, 0), new OreDictItemData(MT.Bronze, OP.toolHeadAxe    , ANY.Wood, U ).setUseVanillaDamage());
		IL.Tool_Hoe_Bronze                      .set(ST.mkic("bronzeHoe"                                    , 1, 0), new OreDictItemData(MT.Bronze, OP.toolHeadHoe    , ANY.Wood, U ).setUseVanillaDamage());
		IL.IC2_WireCutter                       .set(ST.mkic("cutter"                                       , 1   ));
		
		IL.Credit_Iron                          .set(ST.mkic("coin"                                         , 1   ));
		
		IL.Upgrade_Overclocker                  .set(ST.mkic("overclockerUpgrade"                           , 1   ));
		IL.Upgrade_Battery                      .set(ST.mkic("energyStorageUpgrade"                         , 1   ));
		
		ArmorsGT.HAZMATS_FROST                  .add(ST.make(MD.TF, "item.arcticHelm"                       , 1, W));
		ArmorsGT.HAZMATS_FROST                  .add(ST.make(MD.TF, "item.arcticPlate"                      , 1, W));
		ArmorsGT.HAZMATS_FROST                  .add(ST.make(MD.TF, "item.arcticLegs"                       , 1, W));
		ArmorsGT.HAZMATS_FROST                  .add(ST.make(MD.TF, "item.arcticBoots"                      , 1, W));
		ArmorsGT.HAZMATS_FROST                  .add(ST.make(MD.TF, "item.yetiHelm"                         , 1, W));
		ArmorsGT.HAZMATS_FROST                  .add(ST.make(MD.TF, "item.yetiPlate"                        , 1, W));
		ArmorsGT.HAZMATS_FROST                  .add(ST.make(MD.TF, "item.yetiLegs"                         , 1, W));
		ArmorsGT.HAZMATS_FROST                  .add(ST.make(MD.TF, "item.yetiBoots"                        , 1, W));
		
		ArmorsGT.HAZMATS_HEAT                   .add(ST.make(MD.HBM, "item.asbestos_helmet"                 , 1, W));
		ArmorsGT.HAZMATS_HEAT                   .add(ST.make(MD.HBM, "item.asbestos_plate"                  , 1, W));
		ArmorsGT.HAZMATS_HEAT                   .add(ST.make(MD.HBM, "item.asbestos_legs"                   , 1, W));
		ArmorsGT.HAZMATS_HEAT                   .add(ST.make(MD.HBM, "item.asbestos_boots"                  , 1, W));
		
		ArmorsGT.HAZMATS_INSECTS                .add(ST.make(MD.FR, "apiaristHelmet"                        , 1, W));
		ArmorsGT.HAZMATS_INSECTS                .add(ST.make(MD.FR, "apiaristChest"                         , 1, W));
		ArmorsGT.HAZMATS_INSECTS                .add(ST.make(MD.FR, "apiaristLegs"                          , 1, W));
		ArmorsGT.HAZMATS_INSECTS                .add(ST.make(MD.FR, "apiaristBoots"                         , 1, W));
		
		ArmorsGT.HAZMATS_BIO                    .add(ST.mkic("hazmatHelmet"                                 , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.mkic("hazmatChestplate"                             , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.mkic("hazmatLeggings"                               , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.mkic("hazmatBoots"                                  , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.make(MD.HBM, "item.hazmat_helmet"                   , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.make(MD.HBM, "item.hazmat_plate"                    , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.make(MD.HBM, "item.hazmat_legs"                     , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.make(MD.HBM, "item.hazmat_boots"                    , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.make(MD.HBM, "item.hazmat_paa_helmet"               , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.make(MD.HBM, "item.hazmat_paa_plate"                , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.make(MD.HBM, "item.hazmat_paa_legs"                 , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.make(MD.HBM, "item.hazmat_paa_boots"                , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.make(MD.ATSCI, "hazmat_helm"                        , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.make(MD.ATSCI, "hazmat_chest"                       , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.make(MD.ATSCI, "hazmat_legs"                        , 1, W));
		ArmorsGT.HAZMATS_BIO                    .add(ST.make(MD.ATSCI, "hazmat_boots"                       , 1, W));
		
		ArmorsGT.HAZMATS_CHEM                   .add(ST.mkic("hazmatHelmet"                                 , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.mkic("hazmatChestplate"                             , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.mkic("hazmatLeggings"                               , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.mkic("hazmatBoots"                                  , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.make(MD.HBM, "item.hazmat_helmet"                   , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.make(MD.HBM, "item.hazmat_plate"                    , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.make(MD.HBM, "item.hazmat_legs"                     , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.make(MD.HBM, "item.hazmat_boots"                    , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.make(MD.HBM, "item.hazmat_paa_helmet"               , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.make(MD.HBM, "item.hazmat_paa_plate"                , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.make(MD.HBM, "item.hazmat_paa_legs"                 , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.make(MD.HBM, "item.hazmat_paa_boots"                , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.make(MD.ATSCI, "hazmat_helm"                        , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.make(MD.ATSCI, "hazmat_chest"                       , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.make(MD.ATSCI, "hazmat_legs"                        , 1, W));
		ArmorsGT.HAZMATS_CHEM                   .add(ST.make(MD.ATSCI, "hazmat_boots"                       , 1, W));
		
		ArmorsGT.HAZMATS_GAS                    .add(ST.mkic("hazmatHelmet"                                 , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.mkic("hazmatChestplate"                             , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.mkic("hazmatLeggings"                               , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.mkic("hazmatBoots"                                  , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.make(MD.HBM, "item.hazmat_helmet"                   , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.make(MD.HBM, "item.hazmat_plate"                    , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.make(MD.HBM, "item.hazmat_legs"                     , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.make(MD.HBM, "item.hazmat_boots"                    , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.make(MD.HBM, "item.hazmat_paa_helmet"               , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.make(MD.HBM, "item.hazmat_paa_plate"                , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.make(MD.HBM, "item.hazmat_paa_legs"                 , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.make(MD.HBM, "item.hazmat_paa_boots"                , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.make(MD.ATSCI, "hazmat_helm"                        , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.make(MD.ATSCI, "hazmat_chest"                       , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.make(MD.ATSCI, "hazmat_legs"                        , 1, W));
		ArmorsGT.HAZMATS_GAS                    .add(ST.make(MD.ATSCI, "hazmat_boots"                       , 1, W));
		
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.mkic("hazmatHelmet"                                 , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.mkic("hazmatChestplate"                             , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.mkic("hazmatLeggings"                               , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.mkic("hazmatBoots"                                  , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.ReC, "reactorcraft_item_hazhelmet"          , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.ReC, "reactorcraft_item_hazchest"           , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.ReC, "reactorcraft_item_hazlegs"            , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.ReC, "reactorcraft_item_hazboots"           , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.GC_GALAXYSPACE, "item.lead_helmet"          , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.GC_GALAXYSPACE, "item.lead_plate"           , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.GC_GALAXYSPACE, "item.lead_leg"             , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.GC_GALAXYSPACE, "item.lead_boots"           , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.TE_FOUNDATION, "armor.helmetLead"           , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.TE_FOUNDATION, "armor.plateLead"            , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.TE_FOUNDATION, "armor.legsLead"             , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.TE_FOUNDATION, "armor.bootsLead"            , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_helmet"                   , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_plate"                    , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_legs"                     , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_boots"                    , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_helmet_red"               , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_plate_red"                , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_legs_red"                 , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_boots_red"                , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_helmet_grey"              , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_plate_grey"               , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_legs_grey"                , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_boots_grey"               , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_paa_helmet"               , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_paa_plate"                , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_paa_legs"                 , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.hazmat_paa_boots"                , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.paa_plate"                       , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.paa_legs"                        , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.HBM, "item.paa_boots"                       , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.ATSCI, "hazmat_helm"                        , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.ATSCI, "hazmat_chest"                       , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.ATSCI, "hazmat_legs"                        , 1, W));
		ArmorsGT.HAZMATS_RADIOACTIVE            .add(ST.make(MD.ATSCI, "hazmat_boots"                       , 1, W));
		
		ArmorsGT.HAZMATS_LIGHTNING              .add(ST.mkic("hazmatHelmet"                                 , 1, W));
		ArmorsGT.HAZMATS_LIGHTNING              .add(ST.mkic("hazmatChestplate"                             , 1, W));
		ArmorsGT.HAZMATS_LIGHTNING              .add(ST.mkic("hazmatLeggings"                               , 1, W));
		ArmorsGT.HAZMATS_LIGHTNING              .add(ST.mkic("hazmatBoots"                                  , 1, W));
		ArmorsGT.HAZMATS_LIGHTNING              .add(ST.make(Items.chainmail_helmet                         , 1, W));
		ArmorsGT.HAZMATS_LIGHTNING              .add(ST.make(Items.chainmail_chestplate                     , 1, W));
		ArmorsGT.HAZMATS_LIGHTNING              .add(ST.make(Items.chainmail_leggings                       , 1, W));
		ArmorsGT.HAZMATS_LIGHTNING              .add(ST.make(Items.chainmail_boots                          , 1, W));
	}
}
