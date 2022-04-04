/**
 * Copyright (c) 2022 GregTech-6 Team
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

import gregapi.code.ArrayListNoNulls;
import gregapi.code.TagData;

import java.util.Arrays;
import java.util.List;

import static gregapi.data.CS.F;

/**
 * @author Gregorius Techneticies
 * 
 * List of all default Tag Data. The Short Name is for ease of overview and stands for "Tag Data".
 */
public class TD {
	/** For NEI and Creative Mode Menus */
	public static class Creative {
		/** If this Item, Prefix or Material is hidden from Creative (unless Debug Mode is on) */
		public static final TagData HIDDEN                                  = TagData.createTagData("NEI.HIDDEN", "Hidden");
	}
	
	/** For the Projectile System. */
	public static class Projectiles {
		public static final TagData ARROW                                   = TagData.createTagData("PROJECTILES.ARROW", "Arrow");
		public static final TagData BULLET_SMALL                            = TagData.createTagData("PROJECTILES.BULLET_SMALL", "Small Bullet");
		public static final TagData BULLET_MEDIUM                           = TagData.createTagData("PROJECTILES.BULLET_MEDIUM", "Medium Bullet");
		public static final TagData BULLET_LARGE                            = TagData.createTagData("PROJECTILES.BULLET_LARGE", "Large Bullet");
	}
	
	/** For Pipe Networks. Mainly connectivity. */
	public static class Connectors {
		public static final TagData PNEUMATIC_ITEM                          = TagData.createTagData("CONNECTORS.PNEUMATIC_ITEM", "Pneumatic Item Pipe");
		public static final TagData PIPE_FLUID                              = TagData.createTagData("CONNECTORS.PIPE_FLUID", "Fluid Pipe");
		public static final TagData WIRE_REDSTONE                           = TagData.createTagData("CONNECTORS.WIRE_REDSTONE", "Redstone Wire");
		public static final TagData WIRE_ELECTRIC                           = TagData.createTagData("CONNECTORS.WIRE_ELECTRIC", "Electric Wire");
		public static final TagData WIRE_LASER                              = TagData.createTagData("CONNECTORS.WIRE_LASER", "Laser Wire");
		public static final TagData WIRE_LOGISTICS                          = TagData.createTagData("CONNECTORS.WIRE_LOGISTICS", "Logistics Wire");
		public static final TagData AXLE_ROTATION                           = TagData.createTagData("CONNECTORS.AXLE_ROTATION", "Rotational Axle");
		
		/** Contains all known Energy Tags, which are related to Item Transport. */
		public static final List<TagData> ALL_ITEM_TRANSPORT                = new ArrayListNoNulls<>(F, PNEUMATIC_ITEM);
	}
	
	/** For Energy Systems. You can create your own kind of Energy too of course, but I have added some Defaults in order to make things simpler. */
	public static class Energy {
		/**
		 * Energy Tag for Electricity. Units are IndustrialCraft EU.
		 * Size = Voltage
		 * Amount = Amperage
		 */
		public static final TagData ELECTRICITY                             = TagData.createTagData("ENERGY.ELECTRICITY", "EU", "Electric Energy", LH.Chat.BLUE), EU = ELECTRICITY;
		
		/**
		 * Energy Tag for Rotation Power. Units are IndustrialCraft EU (yes EU for easier conversion).
		 * Most converters have an Energy Loss of about 50% when converting from or to EU.
		 * Size = Speed, Positive = Clockwise, Negative = Counter Clockwise
		 * Amount = Power
		 * 
		 * Note:
		 * If an Emitter (Motor) looks visually like being clockwise, it will emit counterclockwise Energy and vice versa.
		 * This is because it is clockwise from the perspective of the Receiver and a Z-Mirroring would invert the direction!
		 * And all of that will be very confusing to normal people... Yeah I don't like it too, but I had to do it.
		 */
		public static final TagData KINETIC_ROTATION                        = TagData.createTagData("ENERGY.KINETIC_ROTATION", "RU", "Rotation Energy", LH.Chat.GREEN), RU = KINETIC_ROTATION;
		
		/**
		 * Energy Tag for Piston Power. Units are IndustrialCraft EU (yes EU for easier conversion).
		 * Most converters have an Energy Loss of about 50% when converting from or to EU.
		 * Size = Push (x > 0) or Pull (x < 0)
		 * Amount = Power
		 */
		public static final TagData KINETIC_PUSH                            = TagData.createTagData("ENERGY.KINETIC_PUSH", "KU", "Kinetic Energy", LH.Chat.DGREEN), KU = KINETIC_PUSH;
		
		/**
		 * Energy Tag for Heat. Units are IndustrialCraft EU (yes EU for easier conversion).
		 * Most converters have an Energy Loss of about 50% when converting from or to EU.
		 * Size = unused (always 1)
		 * Amount = Temperature
		 */
		public static final TagData HEAT                                    = TagData.createTagData("ENERGY.HEAT", "HU", "Heat Energy", LH.Chat.RED), HU = HEAT;
		
		/**
		 * Energy Tag for Cryo Energy. Units are IndustrialCraft EU (yes EU for easier conversion).
		 * Most converters have an Energy Loss of about 50% when converting from or to EU.
		 * Size = unused (always 1)
		 * Amount = Temperature
		 */
		public static final TagData CRYO                                    = TagData.createTagData("ENERGY.CRYO", "CU", "Cryo Energy", LH.Chat.CYAN), CU = CRYO;
		
		/**
		 * Energy Tag for Lasers and alike. Units are IndustrialCraft EU (yes EU for easier conversion).
		 * Most converters have an Energy Loss of about 50% when converting from or to EU.
		 * Size = Strength/Size/Diameter/etc. of the Beam.
		 * Amount = Amount of Energy Transmitted (Frequency).
		 */
		public static final TagData LIGHT                                   = TagData.createTagData("ENERGY.LIGHT", "LU", "Light Energy", LH.Chat.YELLOW), LU = LIGHT;
		
		/**
		 * Energy Tag for Magnets and alike. Units are IndustrialCraft EU (yes EU for easier conversion).
		 * Most converters have an Energy Loss of about 50% when converting from or to EU.
		 * Size = Strength of the Field. Positive for Electron Charge aka South, Negative for Proton Charge aka North. (Yes, I am again fixing the American stupidness of giving Electrons the negative charge!)
		 * Amount = unused (always 1)
		 */
		public static final TagData MAGNETIC                                = TagData.createTagData("ENERGY.MAGNETIC", "MU", "Magnetic Energy", LH.Chat.DGRAY), MU = MAGNETIC;
		
		/**
		 * Energy Tag for Neutron Rays. Units are IndustrialCraft EU (yes EU for easier conversion).
		 * Most converters have an Energy Loss of about 50% when converting from or to EU.
		 * Size = unused (always 1)
		 * Amount = Temperature
		 */
		public static final TagData NEUTRON                                 = TagData.createTagData("ENERGY.NEUTRON", "NU", "Neutron Energy", LH.Chat.BLACK), NU = NEUTRON;
		
		/**
		 * Energy Tag for Quantum Energy. Units are IndustrialCraft EU (yes EU for easier conversion).
		 * Nobody understands Quantum Energy, and everyone who claims to understand it, doesn't understand it.
		 * Size = Nobody knows
		 * Amount = Nobody knows
		 */
		public static final TagData QUANTUM                                 = TagData.createTagData("ENERGY.QUANTUM", "QU", "Quantum Energy", LH.Chat.PURPLE), QU = QUANTUM;
		
		/**
		 * Energy Tag for Time. Machines which simply run on time use this token. This can for example also be used to make a "Time"-Energy emitting thing that accelerates chemical procedures.
		 * Size = unused (always 1)
		 * Amount = Ticks
		 */
		public static final TagData TIME                                    = TagData.createTagData("ENERGY.TIME", "TU", "Time", LH.Chat.DBLUE), TU = TIME, TICK = TIME;
		
		/**
		 * Energy Tag for RedstoneFlux. Units are ThermalExpansion RastaFarians. 4 RF = 1 EU; 10 RF = 1 MJ;
		 * Size = unused (always 1)
		 * Amount = RF
		 */
		public static final TagData REDSTONE_FLUX                           = TagData.createTagData("ENERGY.REDSTONE_FLUX", "RF", "Redstone Flux", LH.Chat.DRED), RF = REDSTONE_FLUX;
		
		/**
		 * Energy Tag for MinecraftJoules. Units are BuildCraft MichaelJacksons. 1 MJ = 2.5 EU = 10 RF;
		 * Size = The Amount the Engine outputs in one push.
		 * Amount = MJ
		 */
		public static final TagData MINECRAFT_JOULES                        = TagData.createTagData("ENERGY.MINECRAFT_JOULES", "MJ", "Minecraft Joules", LH.Chat.DRED), MJ = MINECRAFT_JOULES;
		
		/**
		 * Energy Tag for Steam Power. Units are Litres of Steam. 2 SU = 1 EU
		 * Size = unused (always 1)
		 * Amount = Steam per Tick
		 */
		public static final TagData STEAM                                   = TagData.createTagData("ENERGY.STEAM", "Steam", "Steam", LH.Chat.GRAY);
		
		/**
		 * Energy Tag for Air Pressure Power
		 * Size = Pressure
		 * Amount = Amount of Air
		 */
		public static final TagData AIR                                     = TagData.createTagData("ENERGY.AIR", "AU", "Air Pressure", LH.Chat.WHITE), AU = AIR;
		
		/** Energy Tag for Ordo Vis. In MilliVis Units. 10 = 1 CentiVis. 1000 = 1 Full Vis. */
		public static final TagData VIS_ORDO                                = TagData.createTagData("ENERGY.VIS_ORDO", "Ordo", "Ordo Vis", LH.Chat.WHITE);
		/** Energy Tag for Aer Vis. In MilliVis Units. 10 = 1 CentiVis. 1000 = 1 Full Vis. */
		public static final TagData VIS_AER                                 = TagData.createTagData("ENERGY.VIS_AER", "Aer", "Aer Vis", LH.Chat.YELLOW);
		/** Energy Tag for Aqua Vis. In MilliVis Units. 10 = 1 CentiVis. 1000 = 1 Full Vis. */
		public static final TagData VIS_AQUA                                = TagData.createTagData("ENERGY.VIS_AQUA", "Aqua", "Aqua Vis", LH.Chat.CYAN);
		/** Energy Tag for Terra Vis. In MilliVis Units. 10 = 1 CentiVis. 1000 = 1 Full Vis. */
		public static final TagData VIS_TERRA                               = TagData.createTagData("ENERGY.VIS_TERRA", "Terra", "Terra Vis", LH.Chat.GREEN);
		/** Energy Tag for Ignis Vis. In MilliVis Units. 10 = 1 CentiVis. 1000 = 1 Full Vis. */
		public static final TagData VIS_IGNIS                               = TagData.createTagData("ENERGY.VIS_IGNIS", "Ignis", "Ignis Vis", LH.Chat.RED);
		/** Energy Tag for Perditio Vis. In MilliVis Units. 10 = 1 CentiVis. 1000 = 1 Full Vis. */
		public static final TagData VIS_PERDITIO                            = TagData.createTagData("ENERGY.VIS_PERDITIO", "Perditio", "Perditio Vis", LH.Chat.BLACK);
		/** Set of Energy Tags which are of the Vis Type. In MilliVis Units. 10 = 1 CentiVis. 1000 = 1 Full Vis. The Acronym "VIS" stands for "Very Interesting Stuff" */
		public static final List<TagData> VIS                               = new ArrayListNoNulls<>(F, VIS_ORDO, VIS_AER, VIS_AQUA, VIS_TERRA, VIS_IGNIS, VIS_PERDITIO);
		
		/** Contains all known Energy Tags. */
		public static final List<TagData> ALL                               = new ArrayListNoNulls<>(F, VIS_ORDO, VIS_AER, VIS_AQUA, VIS_TERRA, VIS_IGNIS, VIS_PERDITIO, STEAM, MJ, RF, AU, QU, MU, LU, HU, CU, KU, RU, EU, NU, TU);
		/** Contains all known Energy Tags, which can be converted to or from RF. */
		public static final List<TagData> ALL_RF                            = new ArrayListNoNulls<>(F, MJ, RF, KU);
		/** Contains all known Energy Tags, which are worth exactly 1 EU. */
		public static final List<TagData> ALL_EU                            = new ArrayListNoNulls<>(F, AU, QU, MU, LU, KU, RU, EU, HU, NU, CU);
		/** Contains all known Energy Tags, which are kinetic. */
		public static final List<TagData> ALL_KINETIC                       = new ArrayListNoNulls<>(F, KU, RU);
		/** Contains all known Energy Tags, which are electric. */
		public static final List<TagData> ALL_ELECTRIC                      = new ArrayListNoNulls<>(F, EU);
		/** Contains all known Energy Tags, which are electric. */
		public static final List<TagData> ALL_NEGATIVE_ALLOWED              = new ArrayListNoNulls<>(F, AU, QU, MU, KU, RU, EU);
		/** Contains all known Energy Tags, which are weak to Water, such as electricity. */
		public static final List<TagData> ALL_WEAK_TO_WATER                 = new ArrayListNoNulls<>(F, EU, HU, CU);
		/** Contains all known Energy Tags, which are weak to Thunder, such as electricity. */
		public static final List<TagData> ALL_WEAK_TO_THUNDER               = new ArrayListNoNulls<>(F, EU);
		/** Contains all known Energy Tags, which are weak to Fire, such as electricity. */
		public static final List<TagData> ALL_WEAK_TO_FIRE                  = new ArrayListNoNulls<>(F, EU, CU);
		/** Contains all known Energy Tags, which are Temperature based. */
		public static final List<TagData> ALL_HOT_COLD                      = new ArrayListNoNulls<>(F, HU, CU, VIS_IGNIS);
		/** Contains all known Energy Tags, which have a limited consumption of Packets. */
		public static final List<TagData> ALL_COMSUMPTION_LIMITED           = new ArrayListNoNulls<>(F, EU, RF, VIS_ORDO, VIS_AER, VIS_AQUA, VIS_TERRA, VIS_IGNIS, VIS_PERDITIO);
		/** Contains all known Energy Tags, which use cause Explosions when the Machines are overloaded. */
		public static final List<TagData> ALL_EXPLODING                     = new ArrayListNoNulls<>(F, AU, QU, MU, LU, KU, RU, EU, HU, NU, MJ, STEAM);
		/** Contains all known Energy Tags, which use alternating positive and negative Energy. Used in the Basic Machines to determine if Progress can be made. */
		public static final List<TagData> ALL_ALTERNATING                   = new ArrayListNoNulls<>(F, KU);
		/** Contains all known Energy Tags, which have an irrelevant packet Size. Used in TileEntityBase1 to check if the Energy can be accepted even with a too low Size. */
		public static final List<TagData> ALL_SIZE_IRRELEVANT               = new ArrayListNoNulls<>(F, VIS_ORDO, VIS_AER, VIS_AQUA, VIS_TERRA, VIS_IGNIS, VIS_PERDITIO, STEAM, MJ, RF, HU, CU, NU, QU, TU);
	}
	
	/** For Prefixes */
	public static class Prefix {
		/** If this Prefix is unused */
		public static final TagData PREFIX_UNUSED                           = TagData.createTagData("PREFIX.PREFIX_UNUSED", "Unused Prefix");
		/** If this Prefix is a Container */
		public static final TagData IS_CONTAINER                            = TagData.createTagData("PREFIX.IS_CONTAINER", "Container");
		/** If this Prefix is a Crate */
		public static final TagData IS_CRATE                                = TagData.createTagData("PREFIX.IS_CRATE", "Crate");
		/** If this Prefix is Material Based */
		public static final TagData MATERIAL_BASED                          = TagData.createTagData("PREFIX.MATERIAL_BASED", "Material Based");
		/** If this Prefix is normally used for Blocks */
		public static final TagData BLOCK_BASED                             = TagData.createTagData("PREFIX.BLOCK_BASED", "Block Based");
		/** If this Prefix is Storage Based */
		public static final TagData STORAGE_BASED                           = TagData.createTagData("PREFIX.STORAGE_BASED", "Storage Based");
		/** If this Prefix is normally used for Crop Drops */
		public static final TagData PLANT_DROP                              = TagData.createTagData("PREFIX.PLANT_DROP", "Plant Drop");
		/** If this Prefix is Unificatable in Inventory */
		public static final TagData UNIFICATABLE                            = TagData.createTagData("PREFIX.UNIFICATABLE", "Unificatable");
		/** If this Prefix has the Material Tooltip */
		public static final TagData TOOLTIP_MATERIAL                        = TagData.createTagData("PREFIX.TOOLTIP_MATERIAL", "Material Tooltip");
		/** If this Prefix has the Enchantment Tooltip */
		public static final TagData TOOLTIP_ENCHANTS                        = TagData.createTagData("PREFIX.TOOLTIP_ENCHANTS", "Enchantment Tooltip");
		/** If this Prefix is not Filterable inside the Prefix Filter */
		public static final TagData NO_PREFIX_FILTERING                     = TagData.createTagData("PREFIX.NO_PREFIX_FILTERING", "Not Prefix Filterable");
		/** If this Prefix is Gem based */
		public static final TagData GEM_BASED                               = TagData.createTagData("PREFIX.GEM_BASED", "Gem");
		/** If this Prefix is Dust based and a 100% pure Dust (so not used for Ore Processing or Crates) */
		public static final TagData DUST_BASED                              = TagData.createTagData("PREFIX.DUST_BASED", "Dust");
		/** If this Prefix is Ingot based and a simple Ingot thing (so not used for Multi-Ingots or Crates) */
		public static final TagData INGOT_BASED                             = TagData.createTagData("PREFIX.INGOT_BASED", "Ingot");
		/** If this Prefix is Wire based and a simple Wire thing (so not used for Cables) */
		public static final TagData WIRE_BASED                              = TagData.createTagData("PREFIX.WIRE_BASED", "Wire");
		/** If this Prefix is only Unificatable in Recipes */
		public static final TagData UNIFICATABLE_RECIPES                    = TagData.createTagData("PREFIX.UNIFICATABLE_RECIPES", "Recipe Unificatable");
		/** If this Prefix can be used in an Extruder. */
		public static final TagData EXTRUDER_FODDER                         = TagData.createTagData("PREFIX.EXTRUDER_FODDER", "Extruder Fodder");
		/** If this Prefix is for Ores */
		public static final TagData ORE                                     = TagData.createTagData("PREFIX.ORE", "Ore");
		/** If this Prefix is for Ores */
		public static final TagData STANDARD_ORE                            = TagData.createTagData("PREFIX.STANDARD_ORE", "Standard Ore");
		/** If this Prefix is for Dust alike Ores */
		public static final TagData DUST_ORE                                = TagData.createTagData("PREFIX.DUST_ORE", "Dust Ore");
		/** If this Prefix is for Dense Ores */
		public static final TagData DENSE_ORE                               = TagData.createTagData("PREFIX.DENSE_ORE", "Dense Ore");
		/** If this Prefix is used for Ore Processing */
		public static final TagData ORE_PROCESSING_DIRTY                    = TagData.createTagData("PREFIX.ORE_PROCESSING_DIRTY", "Ore Processing Dirty");
		/** If this Prefix is used for Ore Processing */
		public static final TagData ORE_PROCESSING_CLEAN                    = TagData.createTagData("PREFIX.ORE_PROCESSING_CLEAN", "Ore Processing Clean");
		/** If this Prefix is used for Ore Processing */
		public static final TagData ORE_PROCESSING_REFINED                  = TagData.createTagData("PREFIX.ORE_PROCESSING_REFINED", "Ore Processing Refined");
		/** If this Prefix is used for Ore Processing, like "crushed" for example */
		public static final TagData ORE_PROCESSING_BASED                    = TagData.createTagData("PREFIX.ORE_PROCESSING_BASED", "Ore Processing Based");
		/** If this Prefix is for Tool Heads. */
		public static final TagData TOOL_HEAD                               = TagData.createTagData("PREFIX.TOOL_HEAD", "Tool Head");
		/** If this Prefix is for Tool Heads that require a Handle. */
		public static final TagData NEEDS_HANDLE                            = TagData.createTagData("PREFIX.NEEDS_HANDLE", "Needs Handle");
		/** If this Prefix is for Tool Heads that require sharpening. */
		public static final TagData NEEDS_SHARPENING                        = TagData.createTagData("PREFIX.NEEDS_SHARPENING", "Needs Sharpening");
		/** If this Prefix is for Tools and alike */
		public static final TagData TOOL_ALIKE                              = TagData.createTagData("PREFIX.TOOL_ALIKE", "Tool");
		/** If this Prefix is for Weapons and alike */
		public static final TagData WEAPON_ALIKE                            = TagData.createTagData("PREFIX.WEAPON_ALIKE", "Weapon");
		/** If this Prefix is for Armors and alike */
		public static final TagData ARMOR_ALIKE                             = TagData.createTagData("PREFIX.ARMOR_ALIKE", "Armor");
		/** If this Prefix is for Ammo and alike */
		public static final TagData AMMO_ALIKE                              = TagData.createTagData("PREFIX.AMMO_ALIKE", "Ammo");
		/** If this Prefix can be recycled */
		public static final TagData RECYCLABLE                              = TagData.createTagData("PREFIX.RECYCLABLE", "Recyclable");
		/** If this Prefix can be scanned */
		public static final TagData SCANNABLE                               = TagData.createTagData("PREFIX.SCANNABLE", "Scannable");
		/** If this Prefix can be burned in a Furnace as Fuel, if the Material fits. */
		public static final TagData BURNABLE                                = TagData.createTagData("PREFIX.BURNABLE", "Burnable");
		/** If this Prefix can be burned in a Furnace as Fuel, if the Material fits. */
		public static final TagData SIMPLIFIABLE                            = TagData.createTagData("PREFIX.SIMPLIFIABLE", "Simplifiable");
		/** If this Prefix is self referencing and therefore needs no Material (it can still have a Material) */
		public static final TagData SELF_REFERENCING                        = TagData.createTagData("PREFIX.SELF_REFERENCING", "Self Referencing");
	}
	
	/** For Atomic Materials */
	public static class Atomic {
		/** If this Material is an Atomic Element (Antimatter Elements include this too!) */
		public static final TagData ELEMENT                                 = TagData.createTagData("ATOMIC.ELEMENT", "Element");
		/** If this Material is an Atomic Particle */
		public static final TagData PARTICLE                                = TagData.createTagData("ATOMIC.PARTICLE", "Particle");
		/** If this Material is a Molecule, as opposed to being just random Materials mixed together. */
		public static final TagData MOLECULE                                = TagData.createTagData("ATOMIC.MOLECULE", "Molecule");
		/** If this Material is Antimatter (Antimatter Molecules include this too!) */
		public static final TagData ANTIMATTER                              = TagData.createTagData("ATOMIC.ANTIMATTER", "Antimatter");
		
		/** If this Material is some kind of Metal (so added if any of the six Types below is the case!) */
		public static final TagData METAL                                   = TagData.createTagData("ATOMIC.METAL", "Metal");
		/** If this Material is an Alkali Metal according to the periodic Table of Elements */
		public static final TagData ALKALI_METAL                            = TagData.createTagData("ATOMIC.ALKALI_METAL", "Alkali Metal");
		/** If this Material is an Alkaline Earth Metal according to the periodic Table of Elements */
		public static final TagData ALKALINE_EARTH_METAL                    = TagData.createTagData("ATOMIC.ALKALINE_EARTH_METAL", "Alkaline Earth Metal");
		/** If this Material is of the Lanthanide Group according to the periodic Table of Elements */
		public static final TagData LANTHANIDE                              = TagData.createTagData("ATOMIC.LANTHANIDE", "Lanthanide");
		/** If this Material is of the Actinide Group according to the periodic Table of Elements */
		public static final TagData ACTINIDE                                = TagData.createTagData("ATOMIC.ACTINIDE", "Actinide");
		/** If this Material is a Transition Metal according to the periodic Table of Elements */
		public static final TagData TRANSITION_METAL                        = TagData.createTagData("ATOMIC.TRANSITION_METAL", "Transition Metal");
		/** If this Material is a Post Transition Metal according to the periodic Table of Elements */
		public static final TagData POST_TRANSITION_METAL                   = TagData.createTagData("ATOMIC.POST_TRANSITION_METAL", "Post Transition Metal");
		
		/** If this Material is Metalloid according to the periodic Table of Elements */
		public static final TagData METALLOID                               = TagData.createTagData("ATOMIC.METALLOID", "Metalloid");
		
		/** If this Material is some kind of Nonmetal (so added if any of the three Types below is the case!) */
		public static final TagData NONMETAL                                = TagData.createTagData("ATOMIC.NONMETAL", "Non-Metal");
		/** If this Material is a Polyatomic Nonmetal according to the periodic Table of Elements */
		public static final TagData POLYATOMIC_NONMETAL                     = TagData.createTagData("ATOMIC.POLYATOMIC_NONMETAL", "Polyatomic Non-Metal");
		/** If this Material is a Diatomic Nonmetal according to the periodic Table of Elements */
		public static final TagData DIATOMIC_NONMETAL                       = TagData.createTagData("ATOMIC.DIATOMIC_NONMETAL", "Diatomic Non-Metal");
		/** If this Material is a Noble Gas according to the periodic Table of Elements */
		public static final TagData NOBLE_GAS                               = TagData.createTagData("ATOMIC.NOBLE_GAS", "Noble Gas");
		/** If this Material is a Halogen according to the periodic Table of Elements */
		public static final TagData HALOGEN                                 = TagData.createTagData("ATOMIC.HALOGEN", "Halogen");
		/** If this Material is a Chalcogen according to the periodic Table of Elements */
		public static final TagData CHALCOGEN                               = TagData.createTagData("ATOMIC.CHALCOGEN", "Chalcogen");
		/** If this Material is a Pnictogen according to the periodic Table of Elements */
		public static final TagData PNICTOGEN                               = TagData.createTagData("ATOMIC.PNICTOGEN", "Pnictogen");
		/** If this Material is a Crystallogen according to the periodic Table of Elements */
		public static final TagData CRYSTALLOGEN                            = TagData.createTagData("ATOMIC.CRYSTALLOGEN", "Crystallogen");
		/** If this Material is a Icosagen according to the periodic Table of Elements */
		public static final TagData ICOSAGEN                                = TagData.createTagData("ATOMIC.ICOSAGEN", "Icosagen");
		/** If this Material is in the Zinc Group according to the periodic Table of Elements */
		public static final TagData ZINC_GROUP                              = TagData.createTagData("ATOMIC.ZINC_GROUP", "Zinc Group");
		/** If this Material is in the Copper Group according to the periodic Table of Elements */
		public static final TagData COPPER_GROUP                            = TagData.createTagData("ATOMIC.COPPER_GROUP", "Copper Group");
		/** If this Material is in the Nickel Group according to the periodic Table of Elements */
		public static final TagData NICKEL_GROUP                            = TagData.createTagData("ATOMIC.NICKEL_GROUP", "Nickel Group");
		/** If this Material is in the Cobalt Group according to the periodic Table of Elements */
		public static final TagData COBALT_GROUP                            = TagData.createTagData("ATOMIC.COBALT_GROUP", "Cobalt Group");
		/** If this Material is in the Iron Group according to the periodic Table of Elements */
		public static final TagData IRON_GROUP                              = TagData.createTagData("ATOMIC.IRON_GROUP", "Iron Group");
		/** If this Material is in the Manganese Group according to the periodic Table of Elements */
		public static final TagData MANGANESE_GROUP                         = TagData.createTagData("ATOMIC.MANGANESE_GROUP", "Manganese Group");
		/** If this Material is in the Chromium Group according to the periodic Table of Elements */
		public static final TagData CHROMIUM_GROUP                          = TagData.createTagData("ATOMIC.CHROMIUM_GROUP", "Chromium Group");
		/** If this Material is in the Vanadium Group according to the periodic Table of Elements */
		public static final TagData VANADIUM_GROUP                          = TagData.createTagData("ATOMIC.VANADIUM_GROUP", "Vanadium Group");
		/** If this Material is in the Titanium Group according to the periodic Table of Elements */
		public static final TagData TITANIUM_GROUP                          = TagData.createTagData("ATOMIC.TITANIUM_GROUP", "Titanium Group");
		/** If this Material is in the Scandium Group according to the periodic Table of Elements */
		public static final TagData SCANDIUM_GROUP                          = TagData.createTagData("ATOMIC.SCANDIUM_GROUP", "Scandium Group");
		
		/** If this Material is a Noble Metal according to the periodic Table of Elements: Only for Gold, Silver and Copper */
		public static final TagData NOBLE_METAL                             = TagData.createTagData("ATOMIC.NOBLE_METAL", "Noble Metal");
		/** If this Material is a Refractory Metal according to the periodic Table of Elements */
		public static final TagData REFRACTORY_METAL                        = TagData.createTagData("ATOMIC.REFRACTORY_METAL", "Refractory Metal");
		/** If this Material is a Precious Metal according to the periodic Table of Elements */
		public static final TagData PRECIOUS_METAL                          = TagData.createTagData("ATOMIC.PRECIOUS_METAL", "Precious Metal");
		/** If this Material is in the Platinum Group according to the periodic Table of Elements */
		public static final TagData PLATINUM_GROUP                          = TagData.createTagData("ATOMIC.PLATINUM_GROUP", "Platinum Group");
		
		/** Contains all known Atomic Tags. */
		public static final List<TagData> ALL                               = new ArrayListNoNulls<>(Arrays.asList(ELEMENT, PARTICLE, MOLECULE, ANTIMATTER, METAL, ALKALI_METAL, ALKALINE_EARTH_METAL, LANTHANIDE, ACTINIDE, TRANSITION_METAL, POST_TRANSITION_METAL, METALLOID, NONMETAL, POLYATOMIC_NONMETAL, DIATOMIC_NONMETAL, NOBLE_GAS));
	}
	
	/** General Material Properties */
	public static class Properties {
		/** If this Material is some kind of Acid */
		public static final TagData ACID                                    = TagData.createTagData("PROPERTIES.ACID", "Acid");
		/** If this Material is some kind of Wood */
		public static final TagData WOOD                                    = TagData.createTagData("PROPERTIES.WOOD", "Wood");
		/** If this Material is some kind of Food (or edible at all) */
		public static final TagData FOOD                                    = TagData.createTagData("PROPERTIES.FOOD", "Food");
		/** If this Material is some kind of Meat */
		public static final TagData MEAT                                    = TagData.createTagData("PROPERTIES.MEAT", "Meat");
		/** If this Material is some kind of Rotten */
		public static final TagData ROTTEN                                  = TagData.createTagData("PROPERTIES.ROTTEN", "Rotten");
		/** If this Material is some kind of Coal */
		public static final TagData COAL                                    = TagData.createTagData("PROPERTIES.COAL", "Coal");
		/** If this Material is some kind of Stone */
		public static final TagData STONE                                   = TagData.createTagData("PROPERTIES.STONE", "Stone");
		/** If this Material is some kind of Pearl */
		public static final TagData PEARL                                   = TagData.createTagData("PROPERTIES.PEARL", "Pearl");
		/** If this Material is some kind of Quartz */
		public static final TagData QUARTZ                                  = TagData.createTagData("PROPERTIES.QUARTZ", "Quartz");
		/** If this Material is some kind of Crystal */
		public static final TagData CRYSTAL                                 = TagData.createTagData("PROPERTIES.CRYSTAL", "Crystal");
		/** If this Material is some typically called valuable for the sake of being luxury. */
		public static final TagData VALUABLE                                = TagData.createTagData("PROPERTIES.VALUABLE", "Valuable");
		/** If this Material is some kind of Magical */
		public static final TagData MAGICAL                                 = TagData.createTagData("PROPERTIES.MAGICAL", "Magical");
		/** If this Material is useable in the Betweenlands */
		public static final TagData BETWEENLANDS                            = TagData.createTagData("PROPERTIES.BETWEENLANDS", "Betweenlands");
		/** If this Material can break Twilight Mazes */
		public static final TagData MAZEBREAKER                             = TagData.createTagData("PROPERTIES.MAZEBREAKER", "Maze Breaker");
		/** If this Material is having a constantly burning Aura (like Blaze Rods) */
		public static final TagData BURNING                                 = TagData.createTagData("PROPERTIES.BURNING", "Burning Aura");
		/** If this Material is some kind of flammable */
		public static final TagData FLAMMABLE                               = TagData.createTagData("PROPERTIES.FLAMMABLE", "Flammable");
		/** If this Material is not burnable at all or Fire Proof */
		public static final TagData UNBURNABLE                              = TagData.createTagData("PROPERTIES.UNBURNABLE", "Unburnable");
		/** If this Material is some kind of explosive */
		public static final TagData EXPLOSIVE                               = TagData.createTagData("PROPERTIES.EXPLOSIVE", "Explosive");
		/** If this Material is bouncy */
		public static final TagData BOUNCY                                  = TagData.createTagData("PROPERTIES.BOUNCY", "Bouncy");
		/** If this Material is glowing in the dark */
		public static final TagData GLOWING                                 = TagData.createTagData("PROPERTIES.GLOWING", "Glowing");
		/** If this Material is lighting up the area around it */
		public static final TagData LIGHTING                                = TagData.createTagData("PROPERTIES.LIGHTING", "Lighting");
		/** If this Material is brittle */
		public static final TagData BRITTLE                                 = TagData.createTagData("PROPERTIES.BRITTLE", "Brittle");
		/** If this Material is stretchable */
		public static final TagData STRETCHY                                = TagData.createTagData("PROPERTIES.STRETCHY", "Stretchy");
		/** If this Material is invisible */
		public static final TagData INVISIBLE                               = TagData.createTagData("PROPERTIES.INVISIBLE", "Invisible");
		/** If this Material is transparent */
		public static final TagData TRANSPARENT                             = TagData.createTagData("PROPERTIES.TRANSPARENT", "Transparent");
		/** If this Material is passively Magnetic */
		public static final TagData MAGNETIC_PASSIVE                        = TagData.createTagData("PROPERTIES.MAGNETIC_PASSIVE", "Passively Magnetic");
		/** If this Material is actively Magnetic */
		public static final TagData MAGNETIC_ACTIVE                         = TagData.createTagData("PROPERTIES.MAGNETIC_ACTIVE", "Actively Magnetic");
		/** If this Material is Ender Dragon Proof. */
		public static final TagData ENDER_DRAGON_PROOF                      = TagData.createTagData("PROPERTIES.ENDER_DRAGON_PROOF", "Ender Dragon Proof");
		/** If this Material is Wither Proof. */
		public static final TagData WITHER_PROOF                            = TagData.createTagData("PROPERTIES.WITHER_PROOF", "Wither Proof");
		/** Unused, there is Tool Type checks instead. */
		public static final TagData NO_ADVANCED_TOOLS                       = TagData.createTagData("PROPERTIES.NO_ADVANCED_TOOLS", "No Advanced Tools");
		/** If this Material has Tool Stats. */
		public static final TagData HAS_TOOL_STATS                          = TagData.createTagData("PROPERTIES.HAS_TOOL_STATS", "Has Tool Stats");
		/** If this Material has a Color. */
		public static final TagData HAS_COLOR                               = TagData.createTagData("PROPERTIES.HAS_COLOR", "Has Color");
		/** If this Material is typically used by Mods or Vanilla Minecraft, and where the "ore" Prefix is important. */
		public static final TagData COMMON_ORE                              = TagData.createTagData("PROPERTIES.COMMON_ORE", "Common Ore");
		/** If this Material is generated among the random small Gem Ores. */
		public static final TagData RANDOM_SMALL_GEM_ORE                    = TagData.createTagData("PROPERTIES.RANDOM_SMALL_GEM_ORE", "Random Small Gem Ore");
		/** If this Material is only Unificatable in Recipes (for example Wood, which has many variations depending on from which Tree it comes from) */
		public static final TagData AUTO_BLACKLIST                          = TagData.createTagData("PROPERTIES.AUTO_BLACKLIST", "Auto Blacklist");
		/** If this Material is not a valid Material Name and therefore doesn't get unificated at all. This mainly gets used for technical Materials. */
		public static final TagData AUTO_MATERIAL                           = TagData.createTagData("PROPERTIES.AUTO_MATERIAL", "Automatic Material");
		/** If this Material is not a valid Material Name and therefore doesn't get unificated at all. This mainly gets used for technical Materials. */
		public static final TagData INVALID_MATERIAL                        = TagData.createTagData("PROPERTIES.INVALID_MATERIAL", "Invalid Material");
		public static final TagData UNUSED_MATERIAL                         = TagData.createTagData("PROPERTIES.UNUSED_MATERIAL", "Unused Material");
		public static final TagData IGNORE_IN_COLOR_LOG                     = TagData.createTagData("PROPERTIES.IGNORE_IN_COLOR_LOG", "Ignored in Color Log");
		/** If this Material should not be shown with the Contains X Tooltip. */
		public static final TagData DONT_SHOW_THIS_COMPONENT                = TagData.createTagData("PROPERTIES.DONT_SHOW_THIS_COMPONENT", "Not shown as Component");
		
		/** Contains all known Property Tags. */
		public static final List<TagData> ALL                               = new ArrayListNoNulls<>(Arrays.asList(ACID, WOOD, FOOD, MEAT, ROTTEN, COAL, STONE, PEARL, QUARTZ, CRYSTAL, MAGICAL, VALUABLE, BURNING, FLAMMABLE, UNBURNABLE, EXPLOSIVE, BOUNCY, GLOWING, BETWEENLANDS, LIGHTING, BRITTLE, STRETCHY, INVISIBLE, TRANSPARENT, ENDER_DRAGON_PROOF, WITHER_PROOF, HAS_COLOR, AUTO_BLACKLIST, AUTO_MATERIAL, INVALID_MATERIAL, IGNORE_IN_COLOR_LOG, UNUSED_MATERIAL, DONT_SHOW_THIS_COMPONENT));
		/** Contains all relevant Property Tags. */
		public static final List<TagData> ALL_RELEVANTS                     = new ArrayListNoNulls<>(Arrays.asList(ACID, WOOD, FOOD, MEAT, ROTTEN, COAL, STONE, PEARL, QUARTZ, CRYSTAL, MAGICAL, VALUABLE, BURNING, FLAMMABLE, UNBURNABLE, EXPLOSIVE, BOUNCY, GLOWING, BETWEENLANDS, BRITTLE, STRETCHY, INVISIBLE, TRANSPARENT, ENDER_DRAGON_PROOF, WITHER_PROOF));
	}
	
	/** Describing the kind of binding the Compound Material is having */
	public static class Compounds {
		/** If this Material is some kind of Metallurgic Alloy (For Mods which automatically create Alloys in Smelteries or similar if the Components are there) */
		public static final TagData ALLOY                                   = TagData.createTagData("COMPOUNDS.ALLOY", "Alloy");
		
		/** If this Material is some kind of coated Alloy, like galvanized Stuff. */
		public static final TagData COATED                                  = TagData.createTagData("COMPOUNDS.COATED", "Coated");
		
		/** If this Material is some kind of layered Alloy, like layers of Plates. */
		public static final TagData LAYERED                                 = TagData.createTagData("COMPOUNDS.LAYERED", "Layered");
		
		/** If this Material is only vaguely decomposable. An example for that would be Gravel, Dirt or Clay, where no one knows the exact Components or hidden things. */
		public static final TagData APPROXIMATE                             = TagData.createTagData("COMPOUNDS.APPROXIMATE", "Approximate Components");
		
		/** If this Material is decomposable by any means. This must be added when the Material can be reversed by any means, to prevent people from making it acquirable by cheaper means, causing exploits. */
		public static final TagData DECOMPOSABLE                            = TagData.createTagData("COMPOUNDS.DECOMPOSABLE", "Decomposable");
	}
	
	/** For Material Processing */
	public static class Processing {
		/** If this Material is not recyclable in the IC2 Recycler or similar. This means it is blacklisted automatically.  */
		public static final TagData UNRECYCLABLE                            = TagData.createTagData("PROCESSING.UNRECYCLABLE", "Not Recyclable");
		/** If this Material is decomposable by a Centrifuge. Requires Compounds.DECOMPOSABLE too */
		public static final TagData CENTRIFUGE                              = TagData.createTagData("PROCESSING.CENTRIFUGABLE", "Centrifugable");
		/** If this Material is decomposable by an Electrolyser. Requires Compounds.DECOMPOSABLE too */
		public static final TagData ELECTROLYSER                            = TagData.createTagData("PROCESSING.ELECTROLYSABLE", "Electrolysable");
		/** If this Material can be alloyed in a Crucible. */
		public static final TagData CRUCIBLE_ALLOY                          = TagData.createTagData("PROCESSING.CRUCIBLE_ALLOY", "Crucible Alloyable");
		/** If this Material can be used in an Extruder. */
		public static final TagData EXTRUDER                                = TagData.createTagData("PROCESSING.EXTRUDABLE", "Extrudable");
		/** If this Material can be used in a low Tech Extruder. */
		public static final TagData EXTRUDER_SIMPLE                         = TagData.createTagData("PROCESSING.EXTRUDABLE_SIMPLE", "Extrudable (Low Tech)");
		/** If this Material can be created using UU-Matter or UU-Anti-Matter respectively */
		public static final TagData UUM                                     = TagData.createTagData("PROCESSING.UUM_SYNTHESISABLE", "UUM Synthesisable");
		/** If this Material can be created using Nuclear Fusion */
		public static final TagData FUSION                                  = TagData.createTagData("PROCESSING.FUSION_SYNTHESISABLE", "Fusion Synthesisable");
		/** If this Material can be ground by a Mortar. */
		public static final TagData MORTAR                                  = TagData.createTagData("PROCESSING.MORTAR_GRINDABLE", "Mortar Grindable");
		
		public static final TagData SMITHABLE                               = TagData.createTagData("PROCESSING.SMITHABLE", "Smithable");
		/** If this Material can be directly cooked in a regular Furnace */
		public static final TagData FURNACE                                 = TagData.createTagData("PROCESSING.FURNACE", "Furnace Smeltable");
		/** If this Material can be molten in a Crucible (every Material can, this is just for NEI Stuff) */
		public static final TagData MELTING                                 = TagData.createTagData("PROCESSING.MELTING", "Meltable");
		/** If this Material can not be molten in a Smelter (the Machine, not the Crucible) */
		public static final TagData BLACKLISTED_SMELTER                     = TagData.createTagData("PROCESSING.BLACKLISTED_SMELTER", "Not Meltable in a Smelter");
		
		public static final TagData CRYSTALLISABLE                          = TagData.createTagData("PROCESSING.CRYSTALLISABLE", "Crystallisable");
		
		public static final TagData REACTS_WITH_GLASS                       = TagData.createTagData("PROCESSING.REACTS_WITH_GLASS", "Reacts with Glass");
		
		public static final TagData SOLDERING_MATERIAL                      = TagData.createTagData("PROCESSING.SOLDERING_MATERIAL", "Soldering Metal");
		public static final TagData SOLDERING_MATERIAL_BAD                  = TagData.createTagData("PROCESSING.SOLDERING_MATERIAL_BAD", "Bad Soldering Metal");
		public static final TagData SOLDERING_MATERIAL_GOOD                 = TagData.createTagData("PROCESSING.SOLDERING_MATERIAL_GOOD", "Good Soldering Metal");
		
		public static final TagData WASHING_FIRESTONE                       = TagData.createTagData("PROCESSING.WASHING_FIRESTONE", "Firestone Washable");
		public static final TagData WASHING_PERSULFATE                      = TagData.createTagData("PROCESSING.WASHING_PERSULFATE", "Persulfate Washable"), WASHING_SODIUMPERSULFATE = WASHING_PERSULFATE;
		public static final TagData WASHING_MERCURY                         = TagData.createTagData("PROCESSING.WASHING_MERCURY", "Mercury Washable");
		
		public static final TagData PULVERIZING_CINNABAR                    = TagData.createTagData("PROCESSING.PULVERIZING_CINNABAR", "Cinnabar Crystal from Pulverisation");
		
		public static final List<TagData> ALL_MACHINES                      = new ArrayListNoNulls<>(Arrays.asList(CRYSTALLISABLE, CRUCIBLE_ALLOY, FURNACE, SMITHABLE, MELTING, MORTAR, FUSION, UUM, ELECTROLYSER, CENTRIFUGE, UNRECYCLABLE, SOLDERING_MATERIAL));
		public static final List<TagData> ALL_ORES                          = new ArrayListNoNulls<>(Arrays.asList(CRYSTALLISABLE, WASHING_FIRESTONE, WASHING_PERSULFATE, WASHING_MERCURY, PULVERIZING_CINNABAR));
	}
	
	/** For Materials and the Item Generator */
	public static class ItemGenerator {
		/** For Materials and the Item Generator */
		public static final TagData
		LIQUID                                                              = TagData.createTagData("ITEMGENERATOR.LIQUID"),
		MOLTEN                                                              = TagData.createTagData("ITEMGENERATOR.MOLTEN"),
		GASES                                                               = TagData.createTagData("ITEMGENERATOR.GASES"), GASSES = GASES,
		VAPORS                                                              = TagData.createTagData("ITEMGENERATOR.VAPORS"),
		PLASMA                                                              = TagData.createTagData("ITEMGENERATOR.PLASMA"),
		
		GEMS                                                                = TagData.createTagData("ITEMGENERATOR.GEMS"),
		ORES                                                                = TagData.createTagData("ITEMGENERATOR.ORES"),
		EMPTY                                                               = TagData.createTagData("ITEMGENERATOR.EMPTY"),
		DUSTS                                                               = TagData.createTagData("ITEMGENERATOR.DUSTS"),
		TOOLS                                                               = TagData.createTagData("ITEMGENERATOR.TOOLS"), // NO LONGER USED
		PARTS                                                               = TagData.createTagData("ITEMGENERATOR.PARTS"),
		PIPES                                                               = TagData.createTagData("ITEMGENERATOR.PIPES"),
		WIRES                                                               = TagData.createTagData("ITEMGENERATOR.WIRES"),
		FOILS                                                               = TagData.createTagData("ITEMGENERATOR.FOILS"),
		RAILS                                                               = TagData.createTagData("ITEMGENERATOR.RAILS"),
		LENSES                                                              = TagData.createTagData("ITEMGENERATOR.LENSES"),
		STICKS                                                              = TagData.createTagData("ITEMGENERATOR.STICKS"),
		ARMORS                                                              = TagData.createTagData("ITEMGENERATOR.ARMORS"),
		INGOTS                                                              = TagData.createTagData("ITEMGENERATOR.INGOTS"),
		PLATES                                                              = TagData.createTagData("ITEMGENERATOR.PLATES"),
		PLANTS                                                              = TagData.createTagData("ITEMGENERATOR.PLANTS"),
		WEAPONS                                                             = TagData.createTagData("ITEMGENERATOR.WEAPONS"), // NO LONGER USED
		PROJECTILES                                                         = TagData.createTagData("ITEMGENERATOR.PROJECTILES"),
		INGOTS_HOT                                                          = TagData.createTagData("ITEMGENERATOR.INGOTS_HOT"),
		CONTAINERS                                                          = TagData.createTagData("ITEMGENERATOR.CONTAINERS"),
		DIRTY_DUSTS                                                         = TagData.createTagData("ITEMGENERATOR.DIRTY_DUSTS"),
		MULTIINGOTS                                                         = TagData.createTagData("ITEMGENERATOR.MULTIINGOTS"),
		MULTIPLATES                                                         = TagData.createTagData("ITEMGENERATOR.MULTIPLATES"),
		DENSEPLATES                                                         = TagData.createTagData("ITEMGENERATOR.DENSEPLATES"),
		CONTAINERS_GAS                                                      = TagData.createTagData("ITEMGENERATOR.CONTAINERS_GAS"),
		CONTAINERS_SOLID                                                    = TagData.createTagData("ITEMGENERATOR.CONTAINERS_SOLID"),
		CONTAINERS_FLUID                                                    = TagData.createTagData("ITEMGENERATOR.CONTAINERS_FLUID"),
		CONTAINERS_PLASMA                                                   = TagData.createTagData("ITEMGENERATOR.CONTAINERS_PLASMA");
		
		/** Item Generator Presets as Shortcut */
		public static final TagData[]
		G_ALL                       = new TagData[] {GEMS, ORES, EMPTY, DUSTS, PARTS, PIPES, WIRES, FOILS, RAILS, LENSES, STICKS, ARMORS, INGOTS, INGOTS_HOT, PLATES, PLANTS, PROJECTILES, CONTAINERS, MULTIINGOTS, MULTIPLATES, DENSEPLATES, CONTAINERS_GAS, CONTAINERS_SOLID, CONTAINERS_FLUID, CONTAINERS_PLASMA},
		G_CONTAINERS                = new TagData[] {CONTAINERS, PLANTS},
		G_DUST                      = new TagData[] {DUSTS, PLANTS},
		G_DUST_ORES                 = new TagData[] {DUSTS, PLANTS, ORES},
		G_CRYSTAL                   = new TagData[] {PROJECTILES, DUSTS, PLANTS, GEMS},
		G_CRYSTAL_ORES              = new TagData[] {PROJECTILES, DUSTS, PLANTS, GEMS, ORES},
		G_BLAZE                     = new TagData[] {PROJECTILES, DUSTS, PLANTS, STICKS},
		G_PEARL                     = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, GEMS},
		G_PEARL_TRANSPARENT         = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, GEMS, LENSES, TD.Properties.TRANSPARENT},
		G_GLASS                     = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS, GEMS, ARMORS, LENSES, TD.Properties.TRANSPARENT},
		G_QUARTZ                    = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS, GEMS},
		G_QUARTZ_ORES               = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS, GEMS, ORES},
		G_GEM                       = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS, GEMS, ARMORS},
		G_GEM_ORES                  = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS, GEMS, ARMORS, ORES},
		G_GEM_TRANSPARENT           = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS, GEMS, ARMORS, LENSES, TD.Properties.TRANSPARENT},
		G_GEM_ORES_TRANSPARENT      = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS, GEMS, ARMORS, LENSES, TD.Properties.TRANSPARENT, ORES},
		G_WOOD                      = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS, FOILS},
		G_STONE                     = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS},
		G_BRICK                     = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS, INGOTS},
		G_INGOT                     = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS, ARMORS, INGOTS, INGOTS_HOT, MULTIINGOTS, DENSEPLATES, MULTIPLATES, FOILS},
		G_INGOT_ORES                = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS, ARMORS, INGOTS, INGOTS_HOT, MULTIINGOTS, DENSEPLATES, MULTIPLATES, FOILS, ORES},
		G_INGOT_MACHINE             = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS, ARMORS, INGOTS, INGOTS_HOT, MULTIINGOTS, DENSEPLATES, MULTIPLATES, FOILS, PARTS},
		G_INGOT_MACHINE_ORES        = new TagData[] {PROJECTILES, DUSTS, PLANTS, PLATES, STICKS, ARMORS, INGOTS, INGOTS_HOT, MULTIINGOTS, DENSEPLATES, MULTIPLATES, FOILS, PARTS, ORES},
		G_INGOT_ND                  = new TagData[] {PROJECTILES, PLATES, STICKS, ARMORS, INGOTS, INGOTS_HOT, MULTIINGOTS, DENSEPLATES, MULTIPLATES, FOILS},
		G_INGOT_ND_MACHINE          = new TagData[] {PROJECTILES, PLATES, STICKS, ARMORS, INGOTS, INGOTS_HOT, MULTIINGOTS, DENSEPLATES, MULTIPLATES, FOILS, PARTS},
		G_MACHINE                   = new TagData[] {PROJECTILES, PLATES, STICKS, ARMORS, WIRES, FOILS, PARTS};
	}
}
