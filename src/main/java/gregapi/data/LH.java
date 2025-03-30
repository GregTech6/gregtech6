/**
 * Copyright (c) 2024 GregTech-6 Team
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

import gregapi.code.HashSetNoNulls;
import gregapi.code.TagData;
import gregapi.lang.LanguageHandler;
import gregapi.tileentity.behavior.TE_Behavior_Energy_Converter;
import gregapi.tileentity.behavior.TE_Behavior_Energy_Stats;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;
import java.util.Set;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 * 
 * Contains common translatable Strings.
 */
public class LH {
	public static final String
	  EFFICIENCY = "gt.lang.efficiency"
	, RECIPE = "gt.lang.recipe"
	, RECIPES = "gt.lang.recipes"
	, RECIPES_MOLD = "gt.lang.recipes.mold"
	, RECIPES_MOLD_SELECT = "gt.lang.recipes.mold.select"
	, RECIPES_MOLD_COINAGE = "gt.lang.recipes.mold.coinage"
	, RECIPES_ANVIL_USAGE = "gt.lang.recipes.anvil.usage"
	, RECIPES_SIFTER_USAGE = "gt.lang.recipes.sifter.usage"
	, RECIPES_JUICER_USAGE = "gt.lang.recipes.juicer.usage"
	, RECIPES_MORTAR_USAGE = "gt.lang.recipes.mortar.usage"
	, RECIPES_MIXINGBOWL_USAGE = "gt.lang.recipes.mixingbowl.usage"
	, RECIPES_BATHINGPOT_USAGE = "gt.lang.recipes.bathingsink.usage"
	, RECIPES_GRINDSTONE_USAGE = "gt.lang.recipes.grindstone.usage"
	, RECIPES_GRINDSTONE_INIT = "gt.lang.recipes.grindstone.init"
	, RECIPES_DUSTFUNNEL = "gt.lang.recipes.dustfunnel"
	, RECIPES_AUTOHAMMER = "gt.lang.recipes.autohammer"
	, RECIPES_IGNITER = "gt.lang.recipes.igniter"
	, RECIPES_QUALITY = "gt.lang.recipes.quality"
	, STRUCTURE = "gt.lang.structure"
	, ENERGY_CONTAINED = "gt.lang.energy.contained"
	, ENERGY_CAPACITY = "gt.lang.energy.capacity"
	, ENERGY_OUTPUT = "gt.lang.energy.output"
	, ENERGY_INPUT = "gt.lang.energy.input"
	, ITEM_OUTPUT = "gt.lang.item.output"
	, ITEM_INPUT = "gt.lang.item.input"
	, FLUID_OUTPUT = "gt.lang.fluid.output"
	, FLUID_INPUT = "gt.lang.fluid.input"
	, CONVERTS_FROM_X = "gt.lang.energy.convert.from"
	, CONVERTS_TO_Y = "gt.lang.energy.convert.to"
	, CONVERTS_USING_Z = "gt.lang.energy.convert.using"
	, CONVERTS_PER_Z = "gt.lang.energy.convert.per"
	, FACE_ANY = "gt.lang.face.any"
	, FACE_BOTTOM = "gt.lang.face.bottom"
	, FACE_TOP = "gt.lang.face.top"
	, FACE_LEFT = "gt.lang.face.left"
	, FACE_FRONT = "gt.lang.face.front"
	, FACE_RIGHT = "gt.lang.face.right"
	, FACE_BACK = "gt.lang.face.back"
	, FACE_NONE = "gt.lang.face.none"
	, FACES[] = {FACE_BOTTOM, FACE_TOP, FACE_LEFT, FACE_FRONT, FACE_RIGHT, FACE_BACK, FACE_NONE}
	, FACE_SIDES = "gt.lang.face.sides"
	, FACE_FRONT_BACK = "gt.lang.face.front.back"
	, FACE_ANYBUT_FRONT_BACK = "gt.lang.face.any.but.front.back"
	, FACE_ANYBUT_BOTTOM = "gt.lang.face.any.but.bottom"
	, FACE_ANYBUT_TOP = "gt.lang.face.any.but.top"
	, FACE_ANYBUT_LEFT = "gt.lang.face.any.but.left"
	, FACE_ANYBUT_FRONT = "gt.lang.face.any.but.front"
	, FACE_ANYBUT_RIGHT = "gt.lang.face.any.but.right"
	, FACE_ANYBUT_BACK = "gt.lang.face.any.but.back"
	, FACE_ANYBUT_SIDES = "gt.lang.face.any.but.sides"
	, REQUIREMENT_AIR_IN_FRONT = "gt.lang.requirement.air.front"
	, REQUIREMENT_EMPTY_ASHES = "gt.lang.requirement.empty.ashes"
	, REQUIREMENT_IGNITE_FIRE = "gt.lang.requirement.ignite.fire"
	, REQUIREMENT_MOLTEN_CALCITE = "gt.lang.requirement.molten.calcite"
	, REQUIREMENT_WATER = "gt.lang.requirement.water"
	, REQUIREMENT_WATER_ANY = "gt.lang.requirement.water.any"
	, REQUIREMENT_WATER_PURE = "gt.lang.requirement.water.pure"
	, REQUIREMENT_DISTILLED_WATER = "gt.lang.requirement.water.distilled"
	, REQUIREMENT_CHUNKLOADER = "gt.lang.requirement.chunk.loader"
	, REQUIREMENT_UNSTACKED = "gt.lang.requirement.unstacked"
	, EMITS_USED_STEAM = "gt.lang.emits.used.steam"
	, EMITS_REDSTONE_FLUX = "gt.lang.emits.redstoneflux.lossless"
	, EMITS_REDSTONE_FLUX_LOSS = "gt.lang.emits.redstoneflux.lossy"
	, ACCEPTS_REDSTONE_FLUX = "gt.lang.accepts.redstoneflux.lossless"
	, ACCEPTS_REDSTONE_FLUX_LOSS = "gt.lang.accepts.redstoneflux.lossy"
	, NO_GUI_CLICK_TO_LIMIT = "gt.lang.nogui.rightclick.tank.limit"
	, NO_GUI_CLICK_TO_INTERACT = "gt.lang.nogui.rightclick.interact"
	, NO_GUI_CLICK_TO_INVENTORY = "gt.lang.nogui.rightclick.inventory"
	, NO_GUI_CLICK_TO_TANK = "gt.lang.nogui.rightclick.tank"
	, NO_GUI_FUNNEL_TAP_TO_TANK = "gt.lang.nogui.funnel.tap.tank"
	, NO_GUI_FUNNEL_TO_TANK = "gt.lang.nogui.funnel.tank"
	, NO_GUI_TAP_TO_TANK = "gt.lang.nogui.tap.tank"
	, NO_POWER_CONDUCTING_FLUIDS = "gt.lang.no.powerconducting.fluids"
	, OWNER_CONTROLLED = "gt.lang.owner.controlled"
	, CHEAP_OVERCLOCKING = "gt.lang.cheap.overclocking"
	, KEY_CONTROLLED = "gt.lang.key.controlled"
	, COVER_TOOLTIP = "gt.lang.cover.tooltip"
	, TOOL_TO_SET_FACING_PRE = "gt.lang.use.x.to.toggle.facing.pre"
	, TOOL_TO_SET_FACING_POST = "gt.lang.use.x.to.toggle.facing.post"
	, TOOL_TO_SET_FACING2_PRE = "gt.lang.use.x.to.toggle.facing2.pre"
	, TOOL_TO_SET_FACING2_POST = "gt.lang.use.x.to.toggle.facing2.post"
	, TOOL_TO_SET_CONNECTIONS_PRE = "gt.lang.use.x.to.toggle.connection.pre"
	, TOOL_TO_SET_CONNECTIONS_POST = "gt.lang.use.x.to.toggle.connection.post"
	, TOOL_TO_SET_DIRECTION_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.toggle.direction"
	, TOOL_TO_SET_INPUT_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.set.input.side"
	, TOOL_TO_SET_OUTPUT_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.set.output.side"
	, TOOL_TO_TOGGLE_INPUTS_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.toggle.inputs"
	, TOOL_TO_TOGGLE_OUTPUTS_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.toggle.outputs"
	, TOOL_TO_TOGGLE_AUTO_INPUTS_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.toggle.auto.inputs"
	, TOOL_TO_TOGGLE_AUTO_OUTPUTS_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.toggle.auto.outputs"
	, TOOL_TO_TOGGLE_CONTROLLER_COVER = "gt.lang.use.controlcover.to.toggle"
	, TOOL_TO_TOGGLE_SCREWDRIVER = "gt.lang.use.screwdriver.to.toggle"
	, TOOL_TO_TOGGLE_MONKEY_WRENCH = "gt.lang.use.monkey.wrench.to.toggle"
	, TOOL_TO_TOGGLE_CUTTER = "gt.lang.use.cutter.to.toggle"
	, TOOL_TO_TOGGLE_SOFT_HAMMER = "gt.lang.use.soft.hammer.to.toggle"
	, TOOL_TO_RESET_SOFT_HAMMER = "gt.lang.use.soft.hammer.to.reset"
	, TOOL_TO_TAPE = "gt.lang.use.tape"
	, TOOL_TO_UNTAPE = "gt.lang.use.untape"
	, TOOL_TO_OPEN_CROWBAR = "gt.lang.use.crowbar.to.open"
	, TOOL_TO_UNCOVER_CROWBAR = "gt.lang.use.crowbar.to.uncover"
	, TOOL_TO_DECALCIFY_CHISEL = "gt.lang.use.chisel.to.decalcify"
	, TOOL_TO_DETAIL_MAGNIFYINGGLASS = "gt.lang.use.magnifyingglass.to.detail"
	, TOOL_TO_MEASURE_GEIGER_COUNTER = "gt.lang.use.geigercoutner.to.measure"
	, TOOL_TO_MEASURE_THERMOMETER = "gt.lang.use.thermometer.to.measure"
	, TOOL_TO_ACCESS_SCOOP = "gt.lang.use.scoop.to.access"
	, TOOL_TO_REMOVE_SHOVEL = "gt.lang.use.shovel.to.empty"
	, TOOL_TO_CHANGE_DESIGN_CHISEL = "gt.lang.use.chisel.to.switch.design"
	, TOOL_TO_HARVEST = "gt.lang.tool.to.harvest"
	, TOOL_TO_TAKE_PINCERS = "gt.lang.use.pincers.to.take"
	, TOOL_HINT_USE_SNEAK = "gt.lang.tool.hint.use.sneak"
	, WEAPON_SNEAK_RIGHTCLICK_TO_RELOAD = "gt.weapon.sneak.rightclick.reload"
	, WIRE_STATS_LOSSLESS = "gt.lang.wire.stats.lossless"
	, WIRE_STATS_LOSS = "gt.lang.wire.stats.loss"
	, WIRE_STATS_VOLTAGE = "gt.lang.wire.stats.voltage"
	, WIRE_STATS_AMPERAGE = "gt.lang.wire.stats.amperage"
	, PIPE_STATS_LOSS = "gt.lang.pipe.stats.loss"
	, PIPE_STATS_RANGE = "gt.lang.pipe.stats.range"
	, PIPE_STATS_STEPSIZE = "gt.lang.pipe.stats.stepsize"
	, PIPE_STATS_BANDWIDTH = "gt.lang.pipe.stats.bandwidth"
	, PIPE_STATS_CAPACITY = "gt.lang.pipe.stats.capacity"
	, PIPE_STATS_AMOUNT = "gt.lang.pipe.stats.amount"
	, AXLE_STATS_SPEED = "gt.lang.axle.stats.speed"
	, AXLE_STATS_POWER = "gt.lang.axle.stats.power"
	, HAZARD_FIRE = "gt.lang.hazard.fire"
	, HAZARD_EXPLOSION_STEAM = "gt.lang.hazard.explosion.steam"
	, HAZARD_MELTDOWN = "gt.lang.hazard.meltdown"
	, HAZARD_CONTACT = "gt.lang.hazard.contact"
	, HAZARD_LEAKING_GAS = "gt.lang.hazard.leak.gas"
	, TOOLTIP_GASPROOF = "gt.lang.proof.gas"
	, TOOLTIP_ACIDPROOF = "gt.lang.proof.acid"
	, TOOLTIP_MAGICPROOF = "gt.lang.proof.magic"
	, TOOLTIP_LIQUIDPROOF = "gt.lang.proof.liquid"
	, TOOLTIP_PLASMAPROOF = "gt.lang.proof.plasma"
	, TOOLTIP_HEATPROOF = "gt.lang.proof.heat"
	, TOOLTIP_THERMALMASS = "gt.lang.thermal.mass"
	, TOOLTIP_ONLY_SIMPLE = "gt.lang.only.simple"
	, TOOLTIP_REMINDER_EXTENDERS = "gt.lang.reminder.extenders"
	, TOOLTIP_SEALABLE_ANY = "gt.lang.sealable.any"
	, TOOLTIP_SEALABLE_SOME = "gt.lang.sealable.some"
	, TOOLTIP_SEALABLE_BUGGED = "gt.lang.sealable.bug"
	, TOOLTIP_PISTONPUSHABLE = "gt.lang.pistonpush"
	, TOOLTIP_SPAWNPROOF = "gt.lang.spawnproof"
	, TOOLTIP_SPAWNPROOF_OPTIFINE = "gt.lang.spawnproof.optifine"
	, TOOLTIP_SPAWNPROOF_MP_BUG = "gt.lang.spawnproof.mp.bug"
	, TOOLTIP_SPAWNPROOF_MP_BROKEN = "gt.lang.spawnproof.mp.broken"
	, TOOLTIP_SPAWNPROOF_SP_BUG = "gt.lang.spawnproof.sp.bug"
	, TOOLTIP_SPAWNPROOF_SP_BROKEN = "gt.lang.spawnproof.sp.broken"
	, TOOLTIP_BLASTPOWER = "gt.lang.blastpower"
	, TOOLTIP_BLASTRANGE = "gt.lang.blastrange"
	, TOOLTIP_BLASTFORTUNE = "gt.lang.blastfortune"
	, TOOLTIP_BLASTRESISTANCE = "gt.lang.blastresistance"
	, TOOLTIP_RAILSPEED = "gt.lang.railspeed"
	, TOOLTIP_WALKSPEED = "gt.lang.walkspeed"
	, TOOLTIP_GRAVITY = "gt.lang.gravity"
	, TOOLTIP_NEEDS_HANDLE = "gt.lang.needs.handle"
	, TOOLTIP_NEEDS_SHARPENING = "gt.lang.needs.sharpening"
	, TOOLTIP_SHAPELESS_CRAFT = "gt.lang.has.shapeless"
	, TOOLTIP_AUTOCOLLECT = "gt.lang.autocollect"
	, TOOLTIP_ARMOR_PENETRATING = "gt.lang.armorpenetrating"
	, TOOLTIP_BEACON_PAYMENT = "gt.lang.beacon.payment"
	, TOOLTIP_SHELFABLE = "gt.lang.shelfable"
	, TOOLTIP_SANDWICHABLE = "gt.lang.sandwichable"
	, TOOLTIP_POSSIBLE_ENCHANTS = "gt.lang.tool.possible.enchants"
	, TOOLTIP_POSSIBLE_TOOL_ENCHANTS = "gt.lang.tool.enchants"
	, TOOLTIP_POSSIBLE_WEAPON_ENCHANTS = "gt.lang.weapon.enchants"
	, TOOLTIP_POSSIBLE_AMMO_ENCHANTS = "gt.lang.ammo.enchants"
	, TOOLTIP_POSSIBLE_RANGED_ENCHANTS = "gt.lang.ranged.enchants"
	, TOOLTIP_POSSIBLE_FISHING_ENCHANTS = "gt.lang.fishing.enchants"
	, TOOLTIP_POSSIBLE_ARMOR_ENCHANTS = "gt.lang.armor.enchants"
	, TOOLTIP_TOO_MANY_TOOL_ENCHANTS = "gt.lang.tool.enchants.too.many"
	, TOOLTIP_TOO_MANY_ARMOR_ENCHANTS = "gt.lang.armor.enchants.too.many"
	, TOOLTIP_CONTAINED_MATERIALS = "gt.lang.contained.materials"
	, TOOLTIP_FLAMMABLE_AND_EXPLOSIVE = "gt.lang.flammable.explosive"
	, TOOLTIP_FLAMMABLE = "gt.lang.flammable"
	, TOOLTIP_EXPLOSIVE = "gt.lang.explosive"
	, TOOLTIP_UNBURNABLE = "gt.lang.unburnable"
	, TOOLTIP_BLAST_RESISTANCE_TERRIBLE = "gt.lang.blast.resist.terrible"
	, TOOLTIP_BLAST_RESISTANCE_GHAST = "gt.lang.blast.resist.ghast.proof"
	, TOOLTIP_BLAST_RESISTANCE_CREEPER = "gt.lang.blast.resist.creeper.proof"
	, TOOLTIP_BLAST_RESISTANCE_TNT = "gt.lang.blast.resist.tnt.proof"
	, TOOLTIP_BLAST_RESISTANCE_DYNAMITE = "gt.lang.blast.resist.dynamite.proof"
	, TOOLTIP_BLAST_RESISTANCE_NOT_NUKE = "gt.lang.blast.resist.nuke.not"
	, TOOLTIP_ENCHANT_BONUS = "gt.lang.enchantment.bonus"
	, TOOLTIP_THAUMCRAFT_WARP = "gt.lang.thaumcraft.warp"
	, TOOLTIP_BETWEENLANDS_RESISTANCE = "gt.lang.betweenlands.resist"
	, TOOLTIP_TWILIGHT_MAZE_BREAKING = "gt.lang.twilightforest.mazebreaking"
	, TOOLTIP_TWILIGHT_MAZE_HEDGE_BREAKING = "gt.lang.twilightforest.mazehedgebreaking"
	, TOOLTIP_TWILIGHT_MAZE_STONE_BREAKING = "gt.lang.twilightforest.mazestonebreaking"
	, TOOLTIP_TWILIGHT_TOWER_WOOD_BREAKING = "gt.lang.twilightforest.towerwoodbreaking"
	, PROSPECTING_LAVA = "gt.lang.prospecting.lava"
	, PROSPECTING_LIQUID = "gt.lang.prospecting.liquid"
	, PROSPECTING_AIR = "gt.lang.prospecting.air"
	, PROSPECTING_CHANGE = "gt.lang.prospecting.change"
	, PROSPECTING_TRACES = "gt.lang.prospecting.traces"
	, PROSPECTING_NOTHING = "gt.lang.prospecting.nothing"
	, AUTOCRAFTING_INSERT_BLUEPRINT = "gt.autocrafting.insert.blueprint"
	, ADVCRAFTING_INSERT_BLUEPRINT = "gt.advcrafting.insert.blueprint"
	, ADVCRAFTING_PUT_TO_STORAGE = "gt.advcrafting.put.to.storage"
	, ADVCRAFTING_AUTOMATION_ACCESS = "gt.advcrafting.automation.access"
	, ADVCRAFTING_NEUTRAL_SLOT = "gt.advcrafting.neutral.slot"
	, ADVCRAFTING_DROP_SLOT = "gt.advcrafting.drop.slot"
	, TIME_TICK = "gt.lang.time.tick"
	, TIME_SEC = "gt.lang.time.second"
	, TIME_MIN = "gt.lang.time.minute"
	, TIME_HOUR = "gt.lang.time.hour"
	, TIME_DAY = "gt.lang.time.day"
	, TIME_WEEK = "gt.lang.time.week"
	, TIME_TICKS = "gt.lang.time.ticks"
	, TIME_SECS = "gt.lang.time.seconds"
	, TIME_MINS = "gt.lang.time.minutes"
	, TIME_HOURS = "gt.lang.time.hours"
	, TIME_DAYS = "gt.lang.time.days"
	, TIME_WEEKS = "gt.lang.time.weeks"
	, ADMIN_ONLY_CREATION = "gt.lang.admin.only.creation"
	, WIP = "gt.lang.work.in.progress"
	;
	
	public static final String add(String aKey, String aEnglish) {LanguageHandler.add(aKey, aEnglish); return aKey;}
	public static final String get(String aKey) {return LanguageHandler.translate(aKey);}
	public static final String get(String aKey, String aDefault) {return LanguageHandler.translate(aKey, aDefault);}
	
	public static final String percent(long aNumber) {return (aNumber/100) + ((aNumber%100)>9?"."+aNumber%100:".0"+(aNumber%100));}
	
	public static final String getToolTipBlastResistance(Block aBlock, double aResistance) {return Chat.WHITE + get(LH.TOOLTIP_BLASTRESISTANCE) + Chat.ORANGE + ((int)aResistance) + "." + (((int)(aResistance * 10)) % 10) + (aResistance < 4 ? Chat.BLINKING_RED + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_TERRIBLE) : aResistance < 12 ? Chat.RED + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_GHAST) : aResistance < 16 ? Chat.YELLOW + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_CREEPER) : aResistance <= 40 ? Chat.GREEN + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_TNT) : aResistance < 3330 || COMPAT_IC2 == null || COMPAT_IC2.isExplosionWhitelisted(aBlock) ? Chat.GREEN + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_DYNAMITE) : Chat.BLINKING_CYAN + " " + get(LH.TOOLTIP_BLAST_RESISTANCE_NOT_NUKE));}
	
	public static final String getToolTipHarvest(Material aMaterial, String aHarvestTool, int aHarvestLevel) {
		if (aMaterial.isAdventureModeExempt()) {
			if (UT.Code.stringValid(aHarvestTool))
			return LH.Chat.DGRAY + "Hand-Harvestable, but " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + LH.Chat.DGRAY + " is faster";
			return LH.Chat.DGRAY + "Hand-Harvestable";
		}
		if (UT.Code.stringValid(aHarvestTool)) {
			if (aHarvestLevel > 1) switch (aHarvestLevel) {
			case  1: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+", "+MT.Stone.getLocal()+")";
			case  2: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+", "+MT.Fe.getLocal()+")";
			case  3: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+", "+MT.Diamond.getLocal()+")";
			case  4: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+", "+MT.Netherite.getLocal()+")";
			case  5: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+", "+MT.Ad.getLocal()+")";
			case  6: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case  7: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case  8: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case  9: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case 10: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case 11: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case 12: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case 13: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			case 14: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+")";
			default: return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool)) + " ("+aHarvestLevel+", "+MT.Infinity.getLocal()+")";
			}
			return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + aHarvestTool, UT.Code.capitalise(aHarvestTool));
		}
		if (aMaterial == Material.rock || aMaterial == Material.iron || aMaterial == Material.anvil || aMaterial == Material.glass)
		return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + "pickaxe") + "?";
		if (aMaterial == Material.craftedSnow || aMaterial == Material.snow || aMaterial == Material.sand || aMaterial == Material.grass || aMaterial == Material.ground || aMaterial == Material.clay)
		return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + "shovel") + "?";
		if (aMaterial == Material.wood || aMaterial == Material.plants || aMaterial == Material.vine || aMaterial == Material.gourd || aMaterial == Material.cactus)
		return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + "axe") + "?";
		if (aMaterial == Material.leaves || aMaterial == Material.cloth || aMaterial == Material.carpet || aMaterial == Material.web)
		return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + LH.get(TOOL_LOCALISER_PREFIX + "sword") + "?";
		return LH.Chat.DGRAY + LH.get(LH.TOOL_TO_HARVEST) + ": " + LH.Chat.WHITE + "Unknown";
	}
	
	public static final String getToolTipEfficiency(long aEfficiency) {aEfficiency = Math.abs(aEfficiency); return Chat.YELLOW + get(EFFICIENCY) + ": " + Chat.WHITE + percent(aEfficiency) + "%";}
	
	public static final void addToolTipsEfficiency(List<String> aList, ItemStack aStack, boolean aF3_H, TE_Behavior_Energy_Converter aConverter) {
		addToolTipsEfficiency(aList, aStack, aF3_H, aConverter.mEnergyIN, aConverter.mEnergyOUT, aConverter.mMultiplier);
	}
	public static final void addToolTipsEfficiency(List<String> aList, ItemStack aStack, boolean aF3_H, TE_Behavior_Energy_Stats aEnergyIN, TE_Behavior_Energy_Stats aEnergyOUT, long aMultiplier) {
		if (TD.Energy.ALL_EU.contains(aEnergyIN.mType)) {
			if (TD.Energy.ALL_EU.contains(aEnergyOUT.mType)) {
				aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*aMultiplier, F)));
			} else {
				if (aEnergyOUT.mType == TD.Energy.RF  ) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec*RF_PER_EU, aEnergyOUT.mRec*aMultiplier, F)));
			}
		} else {
			if (TD.Energy.ALL_EU.contains(aEnergyOUT.mType)) {
				if (aEnergyIN.mType == TD.Energy.RF   ) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*aMultiplier*RF_PER_EU, F)));
				if (aEnergyIN.mType == TD.Energy.STEAM) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*aMultiplier*STEAM_PER_EU, F)));
			}
		}
	}
	
	public static final void addToolTipsEfficiency(List<String> aList, ItemStack aStack, boolean aF3_H, TE_Behavior_Energy_Stats aEnergyIN, TE_Behavior_Energy_Stats aEnergyOUT, TE_Behavior_Energy_Stats aEnergyOUT2, long aMultiplier) {
		if (TD.Energy.ALL_EU.contains(aEnergyIN.mType)) {
			if (TD.Energy.ALL_EU.contains(aEnergyOUT.mType)) {
				aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec, F)));
			} else {
				if (aEnergyOUT.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec*RF_PER_EU, aEnergyOUT.mRec, F)));
			}
			if (TD.Energy.ALL_EU.contains(aEnergyOUT2.mType)) {
				aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec, F)));
			} else {
				if (aEnergyOUT2.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec*RF_PER_EU, aEnergyOUT.mRec, F)));
			}
		} else {
			if (TD.Energy.ALL_EU.contains(aEnergyOUT .mType) && aEnergyIN.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*8, F)));
			if (TD.Energy.ALL_EU.contains(aEnergyOUT2.mType) && aEnergyIN.mType == TD.Energy.RF) aList.add(LH.getToolTipEfficiency(UT.Code.units(10000, aEnergyIN.mRec, aEnergyOUT.mRec*8, F)));
		}
	}
	
	public static final void addEnergyToolTips(ITileEntityEnergy aTileEntity, List<String> aToolTips, TagData aEnergyTypeIN, TagData aEnergyTypeOUT, String aSidesIN, String aSidesOUT) {
		if (aEnergyTypeIN != null) {
			long tMin = aTileEntity.getEnergySizeInputMin(aEnergyTypeOUT, SIDE_ANY), tRec = aTileEntity.getEnergySizeInputRecommended(aEnergyTypeOUT, SIDE_ANY), tMax = aTileEntity.getEnergySizeInputMax(aEnergyTypeOUT, SIDE_ANY);
			aToolTips.add(Chat.GREEN + LH.get(LH.ENERGY_INPUT ) + ": " + Chat.WHITE + tRec + " " + aEnergyTypeIN .getLocalisedChatNameShort() + Chat.WHITE + (tRec == tMin && tRec == tMax ? "/t" : (tMin <= 1 ? "/t (up to " : "/t ("+tMin+" to ")+tMax+(UT.Code.stringInvalid(aSidesIN )?"":", "+aSidesIN )+")"));
			aToolTips.add(getToolTipRedstoneFluxAccept(aEnergyTypeIN));
		}
		if (aEnergyTypeOUT != null) {
			long tMin = aTileEntity.getEnergySizeOutputMin(aEnergyTypeOUT, SIDE_ANY), tRec = aTileEntity.getEnergySizeOutputRecommended(aEnergyTypeOUT, SIDE_ANY), tMax = aTileEntity.getEnergySizeOutputMax(aEnergyTypeOUT, SIDE_ANY);
			aToolTips.add(Chat.RED   + LH.get(LH.ENERGY_OUTPUT) + ": " + Chat.WHITE + tRec + " " + aEnergyTypeOUT.getLocalisedChatNameShort() + Chat.WHITE + (tRec == tMin && tRec == tMax ? "/t" : (tMin <= 1 ? "/t (up to " : "/t ("+tMin+" to ")+tMax+(UT.Code.stringInvalid(aSidesOUT)?"":", "+aSidesOUT)+")"));
			aToolTips.add(getToolTipRedstoneFluxEmit(aEnergyTypeOUT));
		}
	}
	
	public static final String getToolTipRedstoneFluxEmit(TagData aEnergyType) {
		if (aEnergyType == TD.Energy.KU) return Chat.ORANGE + LH.get(LH.EMITS_REDSTONE_FLUX_LOSS)+" 50%";
		if (aEnergyType == TD.Energy.RF) return Chat.ORANGE + LH.get(LH.EMITS_REDSTONE_FLUX);
		if (aEnergyType == TD.Energy.MJ) return Chat.ORANGE + LH.get(LH.EMITS_REDSTONE_FLUX);
		return null;
	}
	
	public static final String getToolTipRedstoneFluxAccept(TagData aEnergyType) {
//      if (aEnergyType == TD.Energy.KU) return Chat.ORANGE + LH.get(LH.ACCEPTS_REDSTONE_FLUX_LOSS)+" 50%";
		if (aEnergyType == TD.Energy.RF) return Chat.ORANGE + LH.get(LH.ACCEPTS_REDSTONE_FLUX);
		if (aEnergyType == TD.Energy.MJ) return Chat.ORANGE + LH.get(LH.ACCEPTS_REDSTONE_FLUX);
		return null;
	}
	
	static {
		add("enchantment.level.11"                      , "XI");
		add("enchantment.level.12"                      , "XII");
		add("enchantment.level.13"                      , "XIII");
		add("enchantment.level.14"                      , "XIV");
		add("enchantment.level.15"                      , "XV");
		add("enchantment.level.16"                      , "XVI");
		add("enchantment.level.17"                      , "XVII");
		add("enchantment.level.18"                      , "XVIII");
		add("enchantment.level.19"                      , "XIX");
		add("enchantment.level.20"                      , "XX");
		add("enchantment.level.21"                      , "XXI");
		add("enchantment.level.22"                      , "XXII");
		add("enchantment.level.23"                      , "XXIII");
		add("enchantment.level.24"                      , "XXIV");
		add("enchantment.level.25"                      , "XXV");
		add("enchantment.level.26"                      , "XXVI");
		add("enchantment.level.27"                      , "XXVII");
		add("enchantment.level.28"                      , "XXVIII");
		add("enchantment.level.29"                      , "XXIX");
		add("enchantment.level.30"                      , "XXX");
		
		add("loot.mineshaftCorridor"                    , "+Mineshaft+");
		add("loot.pyramidDesertyChest"                  , "*Desert Pyramid*");
		add("loot.pyramidJungleChest"                   , "*Jungle Temple*");
		add("loot.pyramidJungleDispenser"               , "-Dispenser-");
		add("loot.strongholdCorridor"                   , "-Corridor-");
		add("loot.strongholdLibrary"                    , "+Library+");
		add("loot.strongholdCrossing"                   , "+Storage+");
		add("loot.villageBlacksmith"                    , "*Blacksmith*");
		add("loot.bonusChest"                           , "-Bonus Chest-");
		add("loot.dungeonChest"                         , "*Dungeon*");
		add("loot.twilightforest:hill1"                 , "-Hollow Hill-");
		add("loot.twilightforest:hill2"                 , "+Hollow Hill+");
		add("loot.twilightforest:hill3"                 , "*Hollow Hill*");
		add("loot.twilightforest:hedgemaze"             , "-Hedge Maze-");
		add("loot.twilightforest:tree_cache"            , "-Tree Cache-");
		add("loot.twilightforest:basement"              , "-Basement Cache-");
		add("loot.twilightforest:labyrinth_room"        , "-Labyrinth-");
		add("loot.twilightforest:labyrinth_deadend"     , "+Labyrinth+");
		add("loot.twilightforest:labyrinth_vault"       , "*Labyrinth*");
		add("loot.twilightforest:tower_room"            , "-Magic Tower-");
		add("loot.twilightforest:tower_library"         , "+Magic Tower+");
		add("loot.twilightforest:stronghold_cache"      , "-Stronghold-");
		add("loot.twilightforest:stronghold_room"       , "+Stronghold+");
		add("loot.twilightforest:stronghold_boss"       , "*Stronghold*");
		add("loot.twilightforest:darktower_cache"       , "-Dark Tower-");
		add("loot.twilightforest:darktower_key"         , "+Dark Tower+");
		add("loot.twilightforest:darktower_boss"        , "*Dark Tower*");
		add("loot.twilightforest:aurora_cache"          , "-Aurora Tower-");
		add("loot.twilightforest:aurora_room"           , "+Aurora Tower+");
		add("loot.twilightforest:aurora_boss"           , "*Aurora Tower*");
		add("loot.twilightforest:troll_garden"          , "-Troll Cave-");
		add("loot.twilightforest:troll_vault"           , "+Troll Cave+");
		
		add(EFFICIENCY                                  , "Efficiency");
		add(RECIPE                                      , "Recipe");
		add(RECIPES                                     , "Recipes");
		add(RECIPES_MOLD                                , "This Mold produces");
		add(RECIPES_MOLD_SELECT                         , "Use a Chisel in order to select the Shape of the Mold");
		add(RECIPES_MOLD_COINAGE                        , "Place tiny Metal Plate ontop, hammer it and retrieve Coin");
		add(RECIPES_ANVIL_USAGE                         , "Place Input on Top and use the Hammer on either the Top or the Sides");
		add(RECIPES_MORTAR_USAGE                        , "Rightclick with the Item you want to turn into Dust");
		add(RECIPES_JUICER_USAGE                        , "Rightclick with the Item you want to get Juice from");
		add(RECIPES_SIFTER_USAGE                        , "Place Input on Top and rightclick it");
		add(RECIPES_MIXINGBOWL_USAGE                    , "Place Input in Center, Fill with Fluid on the Rim, then rightclick it");
		add(RECIPES_BATHINGPOT_USAGE                    , "Place Input in Center, Fill with Fluid on the Rim, then rightclick it");
		add(RECIPES_GRINDSTONE_USAGE                    , "Click multiple times with the Object to Sharpen or remove Enchantments");
		add(RECIPES_GRINDSTONE_INIT                     , "Add Sandstone Block in order to be able to use this");
		add(RECIPES_DUSTFUNNEL                          , "Turns all differently sized Dusts into the specified Size");
		add(RECIPES_AUTOHAMMER                          , "Performs Hammer Rightclicks and crushes Blocks");
		add(RECIPES_IGNITER                             , "Performs Ignition Rightclicks and incinerates Blocks");
		add(RECIPES_QUALITY                             , "This has a Tool Quality of");
		add(STRUCTURE                                   , "Structure");
		add(ENERGY_CONTAINED                            , "Stored Energy");
		add(ENERGY_CAPACITY                             , "Capacity");
		add(ENERGY_OUTPUT                               , "Energy OUT");
		add(ENERGY_INPUT                                , "Energy IN");
		add(ITEM_OUTPUT                                 , "Items OUT");
		add(ITEM_INPUT                                  , "Items IN");
		add(FLUID_OUTPUT                                , "Fluids OUT");
		add(FLUID_INPUT                                 , "Fluids IN");
		add(CONVERTS_FROM_X                             , "Converts");
		add(CONVERTS_TO_Y                               , "into");
		add(CONVERTS_USING_Z                            , "using");
		add(CONVERTS_PER_Z                              , "per");
		add(FACE_ANY                                    , "Any Side");
		add(FACE_BOTTOM                                 , "Bottom");
		add(FACE_TOP                                    , "Top");
		add(FACE_LEFT                                   , "Left");
		add(FACE_FRONT                                  , "Front");
		add(FACE_RIGHT                                  , "Right");
		add(FACE_BACK                                   , "Back");
		add(FACE_NONE                                   , "None");
		add(FACE_SIDES                                  , "Sides");
		add(FACE_FRONT_BACK                             , "Front and Back");
		add(FACE_ANYBUT_FRONT_BACK                      , "Any but Front and Back");
		add(FACE_ANYBUT_BOTTOM                          , "Any but Bottom");
		add(FACE_ANYBUT_TOP                             , "Any but Top");
		add(FACE_ANYBUT_LEFT                            , "Any but Left");
		add(FACE_ANYBUT_FRONT                           , "Any but Front");
		add(FACE_ANYBUT_RIGHT                           , "Any but Right");
		add(FACE_ANYBUT_BACK                            , "Any but Back");
		add(FACE_ANYBUT_SIDES                           , "Any but Sides");
		add(REQUIREMENT_AIR_IN_FRONT                    , "Requires Air in front to work!");
		add(REQUIREMENT_EMPTY_ASHES                     , "Requires Ashes to be extracted regularly!");
		add(REQUIREMENT_IGNITE_FIRE                     , "Requires ignition by Flint and Tinder or similar!");
		add(REQUIREMENT_MOLTEN_CALCITE                  , "Requires to be filled with Molten Calcite!");
		add(REQUIREMENT_WATER                           , "Requires input of Water!");
		add(REQUIREMENT_WATER_ANY                       , "Requires input of any Water!");
		add(REQUIREMENT_WATER_PURE                      , "Requires any Water. Use distilled Water for best efficiency!");
		add(REQUIREMENT_DISTILLED_WATER                 , "Requires input of distilled Water!");
		add(REQUIREMENT_CHUNKLOADER                     , "Needs to be in a loaded Chunk to work properly!");
		add(REQUIREMENT_UNSTACKED                       , "Does not work when stacked!");
		add(EMITS_USED_STEAM                            , "Emits used Steam");
		add(EMITS_REDSTONE_FLUX                         , "Can emit Redstone Flux (RF) losslessly");
		add(EMITS_REDSTONE_FLUX_LOSS                    , "Can emit Redstone Flux (RF) with a Loss of");
		add(ACCEPTS_REDSTONE_FLUX                       , "Can accept Redstone Flux (RF) losslessly");
		add(ACCEPTS_REDSTONE_FLUX_LOSS                  , "Can accept Redstone Flux (RF) with a Loss of");
		add(NO_GUI_CLICK_TO_LIMIT                       , "Click on Side with Empty Hand to set Limit (Height and Sneak Sensitive)");
		add(NO_GUI_CLICK_TO_INTERACT                    , "No GUI. Click to interact!");
		add(NO_GUI_CLICK_TO_INVENTORY                   , "No GUI. Click to insert/extract Items!");
		add(NO_GUI_CLICK_TO_TANK                        , "No GUI. Click with Fluid Container to interact!");
		add(NO_GUI_FUNNEL_TAP_TO_TANK                   , "No GUI. Use Tiny Funnels and Taps to interact!");
		add(NO_GUI_FUNNEL_TO_TANK                       , "No GUI. Use Tiny Funnels to interact!");
		add(NO_GUI_TAP_TO_TANK                          , "No GUI. Use Taps to interact!");
		add(NO_POWER_CONDUCTING_FLUIDS                  , "All entering Power Conductor Fluids will be voided!");
		add(OWNER_CONTROLLED                            , "This Block can only be interacted with by its Owner!");
		add(CHEAP_OVERCLOCKING                          , "Can be overclocked without additional Energy Loss");
		add(KEY_CONTROLLED                              , "This Block can only be opened with a Key!");
		add(COVER_TOOLTIP                               , "This Item can be used as Cover");
		add(TOOL_TO_REMOVE_SHOVEL                       , "Use Shovel to empty this");
		add(TOOL_TO_CHANGE_DESIGN_CHISEL                , "Use Chisel to change Design");
		add(TOOL_TO_TOGGLE_CONTROLLER_COVER             , "Use Cover Controller Cover to toggle ON/OFF");
		add(TOOL_TO_TOGGLE_SCREWDRIVER                  , "Use Screwdriver to toggle Modes");
		add(TOOL_TO_TOGGLE_MONKEY_WRENCH                , "Use Monkey Wrench to toggle Modes");
		add(TOOL_TO_TOGGLE_CUTTER                       , "Use Cutter to toggle Modes");
		add(TOOL_TO_OPEN_CROWBAR                        , "Use Crowbar to open this by harvesting");
		add(TOOL_TO_UNCOVER_CROWBAR                     , "Use Crowbar to remove Covers");
		add(TOOL_TO_DECALCIFY_CHISEL                    , "Use Chisel to decalcify");
		add(TOOL_TO_DETAIL_MAGNIFYINGGLASS              , "Use Magnifying Glass to see Details");
		add(TOOL_TO_MEASURE_GEIGER_COUNTER              , "Use Geiger Counter to Measure");
		add(TOOL_TO_MEASURE_THERMOMETER                 , "Use Thermometer to Measure");
		add(TOOL_TO_ACCESS_SCOOP                        , "Use Scoop to extract (Bumble-)Bees");
		add(TOOL_TO_TOGGLE_INPUTS_MONKEY_WRENCH         , "Use Monkey Wrench to toggle Inputs");
		add(TOOL_TO_TOGGLE_OUTPUTS_MONKEY_WRENCH        , "Use Monkey Wrench to toggle Outputs");
		add(TOOL_TO_TOGGLE_AUTO_INPUTS_MONKEY_WRENCH    , "Use Monkey Wrench to toggle automatic Inputs");
		add(TOOL_TO_TOGGLE_AUTO_OUTPUTS_MONKEY_WRENCH   , "Use Monkey Wrench to toggle automatic Outputs");
		add(TOOL_TO_TOGGLE_SOFT_HAMMER                  , "Use Soft Hammer to toggle States");
		add(TOOL_TO_RESET_SOFT_HAMMER                   , "Use Soft Hammer to Reset");
		add(TOOL_TO_TAPE                                , "Use Duct Tape to do anything Duct Tape can do!");
		add(TOOL_TO_UNTAPE                              , "Use Scissors or a Knife to remove Tape");
		add(TOOL_TO_SET_INPUT_MONKEY_WRENCH             , "Use Monkey Wrench to set Input Side");
		add(TOOL_TO_SET_OUTPUT_MONKEY_WRENCH            , "Use Monkey Wrench to set Output Side");
		add(TOOL_TO_SET_DIRECTION_MONKEY_WRENCH         , "Use Monkey Wrench to set Direction");
		add(TOOL_TO_SET_FACING_PRE                      , "Use ");
		add(TOOL_TO_SET_FACING_POST                     , " to set Facing");
		add(TOOL_TO_SET_FACING2_PRE                     , "Use ");
		add(TOOL_TO_SET_FACING2_POST                    , " to set secondary Facing");
		add(TOOL_TO_SET_CONNECTIONS_PRE                 , "Use ");
		add(TOOL_TO_SET_CONNECTIONS_POST                , " to set Connections");
		add(TOOL_TO_HARVEST                             , "Tool to Harvest");
		add(TOOL_TO_TAKE_PINCERS                        , "Use Pincers to extract Items");
		add(TOOL_HINT_USE_SNEAK                         , "Use Tool and Sneak for more options");
		add(WEAPON_SNEAK_RIGHTCLICK_TO_RELOAD           , "Sneak Rightclick to Unload/Reload");
		add(WIRE_STATS_LOSSLESS                         , "Transfers Power losslessly");
		add(WIRE_STATS_LOSS                             , "Loss: ");
		add(WIRE_STATS_VOLTAGE                          , "Voltage: ");
		add(WIRE_STATS_AMPERAGE                         , "Amperage: ");
		add(PIPE_STATS_LOSS                             , "Loss: ");
		add(PIPE_STATS_RANGE                            , "Range: ");
		add(PIPE_STATS_STEPSIZE                         , "Stepsize: ");
		add(PIPE_STATS_BANDWIDTH                        , "Bandwidth: ");
		add(PIPE_STATS_CAPACITY                         , "Capacity: ");
		add(PIPE_STATS_AMOUNT                           , "Amount of Pipes: ");
		add(AXLE_STATS_SPEED                            , "Speed: ");
		add(AXLE_STATS_POWER                            , "Power: ");
		add(HAZARD_FIRE                                 , "Can put Blocks around it on Fire!");
		add(HAZARD_EXPLOSION_STEAM                      , "Explodes when Steam Pressure is too high!");
		add(HAZARD_MELTDOWN                             , "Melts down when stored Heat is too much!");
		add(HAZARD_CONTACT                              , "Causes Damage when touched while active!");
		add(HAZARD_LEAKING_GAS                          , "Leaks when used with Gases!");
		add(TOOLTIP_GASPROOF                            , "Can handle Gases");
		add(TOOLTIP_ACIDPROOF                           , "Can handle Acids");
		add(TOOLTIP_MAGICPROOF                          , "Can handle Magic");
		add(TOOLTIP_LIQUIDPROOF                         , "Can handle Liquids");
		add(TOOLTIP_PLASMAPROOF                         , "Can handle Plasma");
		add(TOOLTIP_HEATPROOF                           , "Can handle Temperatures up to: ");
		add(TOOLTIP_THERMALMASS                         , "Thermal Mass: ");
		add(TOOLTIP_ONLY_SIMPLE                         , "Only accepts simple Fluids!");
		add(TOOLTIP_REMINDER_EXTENDERS                  , "Remember to use Universal Extenders if you need to literally cut Corners");
		add(TOOLTIP_SEALABLE_ANY                        , "This Block can seal Air at any Side");
		add(TOOLTIP_SEALABLE_SOME                       , "This Block can seal Air at some Sides");
		add(TOOLTIP_SEALABLE_BUGGED                     , "Shouldn't seal Air, but sometimes does because opaque");
		add(TOOLTIP_PISTONPUSHABLE                      , "Pistons can push this Block");
		add(TOOLTIP_SPAWNPROOF                          , "Mobs cannot Spawn on this Block");
		add(TOOLTIP_SPAWNPROOF_OPTIFINE                 , "Mob Protection broken because Optifine has removed Metadata Support from Vanilla.");
		add(TOOLTIP_SPAWNPROOF_MP_BUG                   , "Mobs should Spawn on this Block, even if the NEI Overlay says the opposite.");
		add(TOOLTIP_SPAWNPROOF_MP_BROKEN                , "Mobs cannot Spawn on this Block, even if the NEI Overlay says the opposite.");
		add(TOOLTIP_SPAWNPROOF_SP_BUG                   , "Mobs cannot, but should Spawn on this Block.");
		add(TOOLTIP_SPAWNPROOF_SP_BROKEN                , "Mobs can, but shouldn't Spawn on this Block.");
		add(TOOLTIP_BLASTPOWER                          , "Blast Power: ");
		add(TOOLTIP_BLASTRANGE                          , "Blast Range: ");
		add(TOOLTIP_BLASTFORTUNE                        , "Blast Fortune Level: ");
		add(TOOLTIP_BLASTRESISTANCE                     , "Blast Resistance: ");
		add(TOOLTIP_RAILSPEED                           , "Rail Speed: ");
		add(TOOLTIP_WALKSPEED                           , "This Block alters the Walk Speed");
		add(TOOLTIP_GRAVITY                             , "This Block is affected by Gravity");
		add(TOOLTIP_NEEDS_HANDLE                        , "Requires Handle made of: ");
		add(TOOLTIP_NEEDS_SHARPENING                    , "Needs to be sharpened before use");
		add(TOOLTIP_SHAPELESS_CRAFT                     , "Has Shapeless Recipes with Amounts: ");
		add(TOOLTIP_AUTOCOLLECT                         , "Can Auto-Collect Items when harvesting Block");
		add(TOOLTIP_ARMOR_PENETRATING                   , "Can Penetrate Armor");
		add(TOOLTIP_BEACON_PAYMENT                      , "Can be used as a Beacon Payment");
		add(TOOLTIP_SHELFABLE                           , "Can be placed inside a GT Bookshelf");
		add(TOOLTIP_SANDWICHABLE                        , "Is a valid Sandwich Ingredient");
		add(TOOLTIP_POSSIBLE_ENCHANTS                   , "Possible Enchantments: ");
		add(TOOLTIP_POSSIBLE_TOOL_ENCHANTS              , "Tool: ");
		add(TOOLTIP_POSSIBLE_WEAPON_ENCHANTS            , "Weapon: ");
		add(TOOLTIP_POSSIBLE_AMMO_ENCHANTS              , "Ammo: ");
		add(TOOLTIP_POSSIBLE_RANGED_ENCHANTS            , "Ranged: ");
		add(TOOLTIP_POSSIBLE_FISHING_ENCHANTS           , "Fishing: ");
		add(TOOLTIP_POSSIBLE_ARMOR_ENCHANTS             , "Armor: ");
		add(TOOLTIP_TOO_MANY_TOOL_ENCHANTS              , "Too Many Tool Enchantments to List");
		add(TOOLTIP_TOO_MANY_ARMOR_ENCHANTS             , "Too Many Armor Enchantments to List");
		add(TOOLTIP_CONTAINED_MATERIALS                 , "Contained Materials:");
		add(TOOLTIP_FLAMMABLE_AND_EXPLOSIVE             , "Flammable and Explosive!");
		add(TOOLTIP_FLAMMABLE                           , "Flammable!");
		add(TOOLTIP_EXPLOSIVE                           , "Explosive!");
		add(TOOLTIP_UNBURNABLE                          , "Unburnable!");
		add(TOOLTIP_BLAST_RESISTANCE_TERRIBLE           , "(Terrible)");
		add(TOOLTIP_BLAST_RESISTANCE_GHAST              , "(Ghast Proof)");
		add(TOOLTIP_BLAST_RESISTANCE_CREEPER            , "(Creeper Proof)");
		add(TOOLTIP_BLAST_RESISTANCE_TNT                , "(TNT Proof)");
		add(TOOLTIP_BLAST_RESISTANCE_DYNAMITE           , "(Strong Dynamite Proof)");
		add(TOOLTIP_BLAST_RESISTANCE_NOT_NUKE           , "(IC2 Nukes can still go through!)");
		add(TOOLTIP_ENCHANT_BONUS                       , "Can influence an Enchanting Table like a Bookshelf");
		add(TOOLTIP_THAUMCRAFT_WARP                     , "Tools made of this cause Warp to the Wielder");
		add(TOOLTIP_BETWEENLANDS_RESISTANCE             , "Resistant to the rotting Effects of the Betweenlands");
		add(TOOLTIP_TWILIGHT_MAZE_BREAKING              , "Tools made of this can break Twilight Forest Mazes");
		add(TOOLTIP_TWILIGHT_MAZE_HEDGE_BREAKING        , "Can easily break Twilight Forest Maze Hedges");
		add(TOOLTIP_TWILIGHT_MAZE_STONE_BREAKING        , "Can easily break Twilight Forest Mazestone");
		add(TOOLTIP_TWILIGHT_TOWER_WOOD_BREAKING        , "Can easily break Twilight Forest Towerwood");
		add(PROSPECTING_LAVA                            , "There is Lava behind this Rock");
		add(PROSPECTING_LIQUID                          , "There is a Fluid behind this Rock");
		add(PROSPECTING_AIR                             , "There is an Air Pocket behind this Rock");
		add(PROSPECTING_CHANGE                          , "Material is changing behind this Rock");
		add(PROSPECTING_TRACES                          , "Found traces of ");
		add(PROSPECTING_NOTHING                         , "No traces of Ore found");
		add(AUTOCRAFTING_INSERT_BLUEPRINT               , "Insert an autocraftable Blueprint here");
		add(ADVCRAFTING_INSERT_BLUEPRINT                , "Insert Blueprint or Selector Tag 2 through 9");
		add(ADVCRAFTING_PUT_TO_STORAGE                  , "Move Crafting Grid content to Storage Slots");
		add(ADVCRAFTING_AUTOMATION_ACCESS               , "Allow Automation to extract from the Crafting Grid");
		add(ADVCRAFTING_NEUTRAL_SLOT                    , "This Slot does ABSOLUTELY NOTHING! park Items here!");
		add(ADVCRAFTING_DROP_SLOT                       , "Automation can extract Items you drop into this Slot");
		add(TIME_TICK                                   , "Tick");
		add(TIME_SEC                                    , "Second");
		add(TIME_MIN                                    , "Minute");
		add(TIME_HOUR                                   , "Hour");
		add(TIME_DAY                                    , "Day");
		add(TIME_WEEK                                   , "Week");
		add(TIME_TICKS                                  , "Ticks");
		add(TIME_SECS                                   , "Seconds");
		add(TIME_MINS                                   , "Minutes");
		add(TIME_HOURS                                  , "Hours");
		add(TIME_DAYS                                   , "Days");
		add(TIME_WEEKS                                  , "Weeks");
		add(ADMIN_ONLY_CREATION                         , "Admins have to spawn this in. (or you MineTweaker a Recipe in)");
		add(WIP                                         , Chat.RESET + Chat.WHITE + Chat.BOLD + "WIP" + Chat.RESET_TOOLTIP + ", This may not be as functional as you expect it to be!");
	}
	
	public static class Chat {
		public static final Set<String> BASICALLY_EMPTY_STRINGS = new HashSetNoNulls<>(F, "", " ", "  ", "   ", "    ");
		
		static {
			for (EnumChatFormatting tEnum1 : EnumChatFormatting.values()) {
				// Literally just formatting without Text in it.
				BASICALLY_EMPTY_STRINGS.add(    tEnum1.toString()    );
				BASICALLY_EMPTY_STRINGS.add(    tEnum1.toString()+" ");
				BASICALLY_EMPTY_STRINGS.add(" "+tEnum1.toString()    );
				for (EnumChatFormatting tEnum2 : EnumChatFormatting.values()) {
					// Literally just formatting without Text in it.
					BASICALLY_EMPTY_STRINGS.add(    tEnum1.toString()+     tEnum2.toString()    );
					BASICALLY_EMPTY_STRINGS.add(    tEnum1.toString()+     tEnum2.toString()+" ");
					BASICALLY_EMPTY_STRINGS.add(" "+tEnum1.toString()+     tEnum2.toString()    );
					BASICALLY_EMPTY_STRINGS.add(" "+tEnum1.toString()+     tEnum2.toString()+" ");
					BASICALLY_EMPTY_STRINGS.add(    tEnum1.toString()+" " +tEnum2.toString()    );
					BASICALLY_EMPTY_STRINGS.add(    tEnum1.toString()+" " +tEnum2.toString()+" ");
					BASICALLY_EMPTY_STRINGS.add(" "+tEnum1.toString()+" " +tEnum2.toString()    );
					BASICALLY_EMPTY_STRINGS.add(" "+tEnum1.toString()+" " +tEnum2.toString()+" ");
					BASICALLY_EMPTY_STRINGS.add(    tEnum1.toString()+"  "+tEnum2.toString()    );
					BASICALLY_EMPTY_STRINGS.add(    tEnum1.toString()+"  "+tEnum2.toString()+" ");
					BASICALLY_EMPTY_STRINGS.add(" "+tEnum1.toString()+"  "+tEnum2.toString()    );
					BASICALLY_EMPTY_STRINGS.add(" "+tEnum1.toString()+"  "+tEnum2.toString()+" ");
				}
			}
		}
		
		public static final String
		   BLACK          = EnumChatFormatting.BLACK.toString()
		,  DBLUE          = EnumChatFormatting.DARK_BLUE.toString()
		,  DGREEN         = EnumChatFormatting.DARK_GREEN.toString()
		,  DCYAN          = EnumChatFormatting.DARK_AQUA.toString()
		,  DRED           = EnumChatFormatting.DARK_RED.toString()
		,  PURPLE         = EnumChatFormatting.DARK_PURPLE.toString()
		,  ORANGE         = EnumChatFormatting.GOLD.toString()
		,  GOLD           = EnumChatFormatting.GOLD.toString()
		,  GRAY           = EnumChatFormatting.GRAY.toString()
		,  DGRAY          = EnumChatFormatting.DARK_GRAY.toString()
		,  BLUE           = EnumChatFormatting.BLUE.toString()
		,  GREEN          = EnumChatFormatting.GREEN.toString()
		,  CYAN           = EnumChatFormatting.AQUA.toString()
		,  RED            = EnumChatFormatting.RED.toString()
		,  PINK           = EnumChatFormatting.LIGHT_PURPLE.toString()
		,  YELLOW         = EnumChatFormatting.YELLOW.toString()
		,  WHITE          = EnumChatFormatting.WHITE.toString()
		,  OBFUSCATED     = EnumChatFormatting.OBFUSCATED.toString()
		,  BOLD           = EnumChatFormatting.BOLD.toString()
		,  STRIKETHROUGH  = EnumChatFormatting.STRIKETHROUGH.toString()
		,  UNDERLINE      = EnumChatFormatting.UNDERLINE.toString()
		,  ITALIC         = EnumChatFormatting.ITALIC.toString()
		,  RESET          = EnumChatFormatting.RESET.toString()
		,  RESET_TOOLTIP  = RESET + GRAY
		;
		
		public static final String
		  _BLACK          = " " + BLACK
		, _DBLUE          = " " + DBLUE
		, _DGREEN         = " " + DGREEN
		, _DCYAN          = " " + DCYAN
		, _DRED           = " " + DRED
		, _PURPLE         = " " + PURPLE
		, _ORANGE         = " " + ORANGE
		, _GOLD           = " " + GOLD
		, _GRAY           = " " + GRAY
		, _DGRAY          = " " + DGRAY
		, _BLUE           = " " + BLUE
		, _GREEN          = " " + GREEN
		, _CYAN           = " " + CYAN
		, _RED            = " " + RED
		, _PINK           = " " + PINK
		, _YELLOW         = " " + YELLOW
		, _WHITE          = " " + WHITE
		, _OBFUSCATED     = " " + OBFUSCATED
		, _BOLD           = " " + BOLD
		, _STRIKETHROUGH  = " " + STRIKETHROUGH
		, _UNDERLINE      = " " + UNDERLINE
		, _ITALIC         = " " + ITALIC
		, _RESET          = " " + RESET
		, _RESET_TOOLTIP  = " " + RESET_TOOLTIP
		;
		
		public static String
		  RAINBOW_FAST = BLACK
		, RAINBOW = BLACK
		, RAINBOW_SLOW = BLACK
		, BLINKING_CYAN = CYAN
		, BLINKING_RED = RED
		, BLINKING_ORANGE = ORANGE
		, BLINKING_GRAY = GRAY
		;
	}
}
