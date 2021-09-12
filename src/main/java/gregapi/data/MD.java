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

import gregapi.code.ModData;
import gregapi.data.CS.ModIDs;

/**
 * @author Gregorius Techneticies
 */
public class MD {
	public static final ModData MC = new ModData(ModIDs.MC, "Minecraft").setLoaded(T)
	
	, GT                = new ModData(ModIDs.GT                 , "GregTech")
	, GAPI              = new ModData(ModIDs.GAPI               , "Greg-API")
	, GAPI_POST         = new ModData(ModIDs.GAPI_POST          , "Greg-API-Post")
	, GT5U              = new ModData(ModIDs.GT                 , "GregTech 5 Unofficial")
	, GT6U              = new ModData(ModIDs.GT                 , "GregTech 6 Unofficial")
	
	, QT                = new ModData(ModIDs.QT                 , "QwerTech")
	
	, IC2               = new ModData(ModIDs.IC2                , "IndustrialCraft 2")
	, IC2C              = new ModData(ModIDs.IC2C               , "IndustrialCraft 2 Classic")
	
	, NC                = new ModData(ModIDs.NC                 , "Nuclear Control")
	, IHL               = new ModData(ModIDs.IHL                , "IHL")
	
	, FMB               = new ModData(ModIDs.FMB                , "Forge Microblocks")
	, TRANSLOCATOR      = new ModData(ModIDs.TRANSLOCATOR       , "Translocator")
	, FUNK              = new ModData(ModIDs.FUNK               , "Funky Locomotion")
	, BAUBLES           = new ModData(ModIDs.BAUBLES            , "Baubles")
	
	, TC                = new ModData(ModIDs.TC                 , "Thaumcraft")
	, TCFM              = new ModData(ModIDs.TCFM               , "Forbidden Magic")
	, TECHNOM           = new ModData(ModIDs.TECHNOM            , "Technomancy")
	, BOTA              = new ModData(ModIDs.BOTA               , "Botania")
	, ALF               = new ModData(ModIDs.ALF                , "Alfheim")
	, PE                = new ModData(ModIDs.PE                 , "Project E")
	, WTCH              = new ModData(ModIDs.WTCH               , "Witchery")
	, HOWL              = new ModData(ModIDs.HOWL               , "Howling Moon")
	, TF                = new ModData(ModIDs.TF                 , "Twilight Forest")
	, ERE               = new ModData(ModIDs.ERE                , "Erebus")
	, ATUM              = new ModData(ModIDs.ATUM               , "Atum")
	, BTL               = new ModData(ModIDs.BTL                , "The Betweenlands")
	, AETHER            = new ModData(ModIDs.AETHER             , "The Aether")
	, TROPIC            = new ModData(ModIDs.TROPIC             , "Tropicraft")
	, MYST              = new ModData(ModIDs.MYST               , "Mystcraft")
	, WARPBOOK          = new ModData(ModIDs.WARPBOOK           , "Warpbook")
	, ARS               = new ModData(ModIDs.ARS                , "Ars Magica")
	, CANDY             = new ModData(ModIDs.CANDY              , "CandyCraft")
	, ABYSSAL           = new ModData(ModIDs.ABYSSAL            , "AbyssalCraft")
	, SOULFOREST        = new ModData(ModIDs.SOULFOREST         , "Soul Forest")
	
	, RC                = new ModData(ModIDs.RC                 , "Railcraft")
	
	, IE                = new ModData(ModIDs.IE                 , "Immersive Engineering")
	
	, TE                = new ModData(ModIDs.TE                 , "Thermal Expansion")
	, TE_FOUNDATION     = new ModData(ModIDs.TE_FOUNDATION      , "Thermal Foundation")
	, TE_DYNAMICS       = new ModData(ModIDs.TE_DYNAMICS        , "Thermal Dynamics")
	
	, AE                = new ModData(ModIDs.AE                 , "Applied Energistics")
	, MO                = new ModData(ModIDs.MO                 , "Matter Overdrive")
	
	, TFC               = new ModData(ModIDs.TFC                , "TerraFirmaCraft")
	, TFCP              = new ModData(ModIDs.TFCP               , "TerraFirmaCraft Plus")
	
	, MET               = new ModData("Metallurgy"              , "Metallurgy")
	
	, Streams           = new ModData("streams"                 , "Streams")
	
	, ZTONES            = new ModData(ModIDs.ZTONES             , "Ztones")
	, CHSL              = new ModData(ModIDs.CHSL               , "Chisel")
	
	, NePl              = new ModData(ModIDs.NePl               , "Netherite Plus")
	, NeLi              = new ModData(ModIDs.NeLi               , "Netherlicious")
	, EtFu              = new ModData(ModIDs.EtFu               , "Et Futurum")
	, BB                = new ModData(ModIDs.BB                 , "Better Beginnings")
	, DYNAMIC_TREES     = new ModData(ModIDs.DYNAMIC_TREES      , "Dynamic Trees")
	
	, BbLC              = new ModData(ModIDs.BbLC               , "BiblioCraft")
	, CARP              = new ModData(ModIDs.CARP               , "Carpenters Blocks")
	, BETTER_RECORDS    = new ModData(ModIDs.BETTER_RECORDS     , "Better Records")
	, ENCHIRIDION       = new ModData(ModIDs.ENCHIRIDION        , "Enchiridion")
	, ENCHIRIDION2      = new ModData(ModIDs.ENCHIRIDION2       , "Enchiridion")
	, LOSTBOOKS         = new ModData(ModIDs.LOSTBOOKS          , "Lost Books")
	, LOOTBAGS          = new ModData(ModIDs.LOOTBAGS           , "Lootbags")
	, EUREKA            = new ModData(ModIDs.EUREKA             , "Eureka")
	
	, UB                = new ModData(ModIDs.UB                 , "Underground Biomes")
	, COG               = new ModData(ModIDs.COG                , "Custom Ore Generation")
	, PFAA              = new ModData(ModIDs.PFAA               , "Per Fabrica Ad Astra")
	, MIN               = new ModData(ModIDs.MIN                , "Mineralogy")
	, RH                = new ModData(ModIDs.RH                 , "Rockhounding")
	
	, FR                = new ModData(ModIDs.FR                 , "Forestry")
	, FRMB              = new ModData(ModIDs.FRMB               , "Magic Bees")
	, BINNIE            = new ModData(ModIDs.BINNIE             , "Binnie's Mods")
	, BINNIE_BEE        = new ModData(ModIDs.BINNIE_BEE         , "Binnie's Extra Bees")
	, BINNIE_TREE       = new ModData(ModIDs.BINNIE_TREE        , "Binnie's Extra Trees")
	, BINNIE_GENETICS   = new ModData(ModIDs.BINNIE_GENETICS    , "Binnie's Genetics")
	, BINNIE_BOTANY     = new ModData(ModIDs.BINNIE_BOTANY      , "Binnie's Botany")
	, BINNIE_PATCHER    = new ModData(ModIDs.BINNIE_PATCHER     , "Binnie Patcher")
	
	, MFR               = new ModData(ModIDs.MFR                , "MineFactory Reloaded")
	, PnC               = new ModData(ModIDs.PnC                , "PneumaticCraft")
	, FSP               = new ModData(ModIDs.FSP                , "Flaxbeard's Steam Power")
	, SC2               = new ModData(ModIDs.SC2                , "Steamcraft 2")
	, ExU               = new ModData(ModIDs.ExU                , "Extra Utilities")
	, ExS               = new ModData(ModIDs.ExS                , "Extra Simple")
	, EIO               = new ModData(ModIDs.EIO                , "Ender IO")
	, RT                = new ModData(ModIDs.RT                 , "Random Things")
	, AA                = new ModData(ModIDs.AA                 , "Actually Additions")
	
	, SD                = new ModData(ModIDs.SD                 , "Storage Drawers")
	, BTRS              = new ModData(ModIDs.BTRS               , "Better Storage")
	, JABBA             = new ModData(ModIDs.JABBA              , "JABBA")
	
	, MgC               = new ModData(ModIDs.MgC                , "Magneticraft")
	, BR                = new ModData(ModIDs.BR                 , "Big Reactors")
	, HBM               = new ModData(ModIDs.HBM                , "HBM's Nuclear Tech")
	, ELN               = new ModData(ModIDs.ELN                , "Electrical Age")
	
	, DRGN              = new ModData(ModIDs.DRGN               , "Dragon API")
	, RoC               = new ModData(ModIDs.RoC                , "RotaryCraft")
	, ReC               = new ModData(ModIDs.ReC                , "ReactorCraft")
	, ElC               = new ModData(ModIDs.ElC                , "ElectriCraft")
	, CrC               = new ModData(ModIDs.CrC                , "ChromatiCraft")
	
	, VOLTZ             = new ModData(ModIDs.VOLTZ              , "Voltz Engine")
	, MFFS              = new ModData(ModIDs.MFFS               , "Modular Force Field System")
	, ICBM              = new ModData(ModIDs.ICBM               , "ICBM")
	, ATSCI             = new ModData(ModIDs.ATSCI              , "Atomic Science")
	
	, Mek               = new ModData(ModIDs.Mek                , "Mekanism")
	, Mek_Tools         = new ModData(ModIDs.Mek_Tools          , "Mekanism Tools")
	, Mek_Generators    = new ModData(ModIDs.Mek_Generators     , "Mekanism Generators")
	
	, OC                = new ModData(ModIDs.OC                 , "Open Computers")
	, CC                = new ModData(ModIDs.CC                 , "ComputerCraft")
	
	, TreeCap           = new ModData(ModIDs.TreeCap            , "Treecapitator")
	, HaC               = new ModData(ModIDs.HaC                , "HarvestCraft")
	, CookBook          = new ModData(ModIDs.CookBook           , "Cooking for Blockheads")
	, APC               = new ModData(ModIDs.APC                , "AppleCore")
	, HO                = new ModData(ModIDs.HO                 , "Hunger Overhaul")
	, ENVM              = new ModData(ModIDs.ENVM               , "Enviromine")
	, MaCr              = new ModData(ModIDs.MaCr               , "Magical Crops")
	, MaCu              = new ModData(ModIDs.MaCu               , "Mariculture")
	, MoCr              = new ModData(ModIDs.MoCr               , "Mo'Creatures")
	, GoG               = new ModData(ModIDs.GoG                , "Grimoire of Gaia")
	, PdC               = new ModData(ModIDs.PdC                , "Psychedelicraft") // Wait, why did I add some compat to this Drug Mod again!?!
	, Bamboo            = new ModData(ModIDs.Bamboo             , "Bamboo Mod")
	, PMP               = new ModData(ModIDs.PMP                , "Plant Mega Pack")
	, Fossil            = new ModData(ModIDs.Fossil             , "Fossils and Archeology")
	, GrC               = new ModData(ModIDs.GrC                , "Growthcraft")
	, GrC_Apples        = new ModData(ModIDs.GrC_Apples         , "Growthcraft Apples")
	, GrC_Cellar        = new ModData(ModIDs.GrC_Cellar         , "Growthcraft Cellar")
	, GrC_Bamboo        = new ModData(ModIDs.GrC_Bamboo         , "Growthcraft Bamboo")
	, GrC_Bees          = new ModData(ModIDs.GrC_Bees           , "Growthcraft Bees")
	, GrC_Fish          = new ModData(ModIDs.GrC_Fish           , "Growthcraft Fishtrap")
	, GrC_Grapes        = new ModData(ModIDs.GrC_Grapes         , "Growthcraft Grapes")
	, GrC_Hops          = new ModData(ModIDs.GrC_Hops           , "Growthcraft Hops")
	, GrC_Milk          = new ModData(ModIDs.GrC_Milk           , "Growthcraft Milk")
	, GrC_Rice          = new ModData(ModIDs.GrC_Rice           , "Growthcraft Rice")
	
	, CrGu              = new ModData(ModIDs.CrGu               , "Craft Guide")
	, SmAc              = new ModData(ModIDs.SmAc               , "Simple Achievements")
	, HQM               = new ModData(ModIDs.HQM                , "Hardcore Questing Mode")
	
	, HEX               = new ModData(ModIDs.HEX                , "HEXCraft")
	, DE                = new ModData(ModIDs.DE                 , "Draconic Evolution")
	, AV                = new ModData(ModIDs.AV                 , "Avaritia")
	
	, EB                = new ModData(ModIDs.EB                 , "Enhanced Biomes")
	, EBXL              = new ModData(ModIDs.EBXL               , "Extra Biomes XL")
	, BoP               = new ModData(ModIDs.BoP                , "Biomes O' Plenty")
	, HiL               = new ModData(ModIDs.HiL                , "Highlands")
	
	, ATG               = new ModData(ModIDs.ATG                , "Alternate Terrain Generation")
	, RTG               = new ModData(ModIDs.RTG                , "Realistic Terrain Generation")
	, RWG               = new ModData(ModIDs.RWG                , "Realistic World Generation")
	
	, A97               = new ModData(ModIDs.A97                , "Aroma1997 Core")
	, A97_MINING        = new ModData(ModIDs.A97_MINING         , "Aroma1997's Mining Dimension")
	
	, CW2               = new ModData(ModIDs.CW2                , "Cave World 2")
	
	, GaSu              = new ModData(ModIDs.GaSu               , "Ganys Surface")
	, GaNe              = new ModData(ModIDs.GaNe               , "Ganys Nether")
	, GaEn              = new ModData(ModIDs.GaEn               , "Ganys End")
	, WdSt              = new ModData(ModIDs.WdSt               , "Ganys Wood Stuff")
	
	, HEE               = new ModData(ModIDs.HEE                , "Hardcore Ender Expansion")
	
	, LycM              = new ModData(ModIDs.LycM               , "Lycanites Mobs")
	, LycM_Fresh        = new ModData(ModIDs.LycM_Fresh         , "Lycanites Mobs (Freshwater)")
	, LycM_Salt         = new ModData(ModIDs.LycM_Salt          , "Lycanites Mobs (Saltwater)")
	, LycM_Swamp        = new ModData(ModIDs.LycM_Swamp         , "Lycanites Mobs (Swamp)")
	, LycM_Plains       = new ModData(ModIDs.LycM_Plains        , "Lycanites Mobs (Plains)")
	, LycM_Forest       = new ModData(ModIDs.LycM_Forest        , "Lycanites Mobs (Forest)")
	, LycM_Jungle       = new ModData(ModIDs.LycM_Jungle        , "Lycanites Mobs (Jungle)")
	, LycM_Mountain     = new ModData(ModIDs.LycM_Mountain      , "Lycanites Mobs (Mountain)")
	, LycM_Desert       = new ModData(ModIDs.LycM_Desert        , "Lycanites Mobs (Desert)")
	, LycM_Arctic       = new ModData(ModIDs.LycM_Arctic        , "Lycanites Mobs (Arctic)")
	, LycM_Inferno      = new ModData(ModIDs.LycM_Inferno       , "Lycanites Mobs (Inferno)")
	, LycM_Demon        = new ModData(ModIDs.LycM_Demon         , "Lycanites Mobs (Demon)")
	, LycM_Shadow       = new ModData(ModIDs.LycM_Shadow        , "Lycanites Mobs (Shadow)")
	
	, BC                = new ModData(ModIDs.BC                 , "BuildCraft")
	, BC_SILICON        = new ModData(ModIDs.BC_SILICON         , "BuildCraft Silicon")
	, BC_TRANSPORT      = new ModData(ModIDs.BC_TRANSPORT       , "BuildCraft Transport")
	, BC_FACTORY        = new ModData(ModIDs.BC_FACTORY         , "BuildCraft Factory")
	, BC_ENERGY         = new ModData(ModIDs.BC_ENERGY          , "BuildCraft Energy")
	, BC_BUILDERS       = new ModData(ModIDs.BC_BUILDERS        , "BuildCraft Builders")
	, BC_ROBOTICS       = new ModData(ModIDs.BC_ROBOTICS        , "BuildCraft Robotics")
	
	, RP                = new ModData(ModIDs.RP                 , "Redpower")
	, BP                = new ModData(ModIDs.BP                 , "Blue Power")
	, PR                = new ModData(ModIDs.PR                 , "Project Red")
	, PR_TRANSPORT      = new ModData(ModIDs.PR_TRANSPORT       , "Project Red Transport")
	, PR_INTEGRATION    = new ModData(ModIDs.PR_INTEGRATION     , "Project Red Integration")
	, PR_EXPANSION      = new ModData(ModIDs.PR_EXPANSION       , "Project Red Expansion")
	, PR_TRANSMISSION   = new ModData(ModIDs.PR_TRANSMISSION    , "Project Red Transmission")
	, PR_EXPLORATION    = new ModData(ModIDs.PR_EXPLORATION     , "Project Red Exploration")
	, PR_COMPATIBILITY  = new ModData(ModIDs.PR_COMPATIBILITY   , "Project Red Compatibility")
	, PR_FABRICATION    = new ModData(ModIDs.PR_FABRICATION     , "Project Red Fabrication")
	, PR_ILLUMINATION   = new ModData(ModIDs.PR_ILLUMINATION    , "Project Red Illumination")
	
	, WR_CBE_C          = new ModData(ModIDs.WR_CBE_C           , "Wireless Redstone Chickenbones Edition")
	, WR_CBE_A          = new ModData(ModIDs.WR_CBE_A           , "Wireless Redstone Chickenbones Edition")
	, WR_CBE_L          = new ModData(ModIDs.WR_CBE_L           , "Wireless Redstone Chickenbones Edition")
	
	, COFH_API          = new ModData(ModIDs.COFH_API           , "CoFH-API")
	, COFH_API_ENERGY   = new ModData(ModIDs.COFH_API_ENERGY    , "CoFH-API Energy")
	, COFH_CORE         = new ModData(ModIDs.COFH_CORE          , "CoFH-Core")
	
	, OB                = new ModData(ModIDs.OB                 , "Open Blocks")
	, PA                = new ModData(ModIDs.PA                 , "Progressive Automation")
	, MNTL              = new ModData(ModIDs.MNTL               , "Mantle")
	, TiC               = new ModData(ModIDs.TiC                , "Tinkers Construct")
	, Natura            = new ModData(ModIDs.Natura             , "Natura")
	, MF2               = new ModData(ModIDs.MF2                , "MineFantasy II")
	, FZ                = new ModData(ModIDs.FZ                 , "Factorization")
	, BWM               = new ModData(ModIDs.BWM                , "Balkon's Weapon Mod")
	, BG2               = new ModData(ModIDs.BG2                , "Battlegear 2")
	, OMT               = new ModData(ModIDs.OMT                , "Open Modular Turrets")
	, TG                = new ModData(ModIDs.TG                 , "Tech Guns")
	
	, FM                = new ModData(ModIDs.FM                 , "Falling Meteors")
	, GC                = new ModData(ModIDs.GC                 , "Galacticraft")
	, GC_PLANETS        = new ModData(ModIDs.GC_PLANETS         , "Galacticraft Planets")
	, GC_GALAXYSPACE    = new ModData(ModIDs.GC_GALAXYSPACE     , "Galaxy Space")
	, GC_ADV_ROCKETRY   = new ModData(ModIDs.GC_ADV_ROCKETRY    , "Advanced Rocketry")
	, VULPES            = new ModData(ModIDs.VULPES             , "Lib Vulpes")
	, MD8               = new ModData(ModIDs.MD8                , "Micdoodle8 Core")
	;
}
