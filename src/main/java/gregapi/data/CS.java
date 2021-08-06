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

import java.io.File;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import gregapi.api.Abstract_Mod;
import gregapi.block.BlockBase;
import gregapi.block.IBlockBase;
import gregapi.block.IBlockPlacable;
import gregapi.block.IPrefixBlock;
import gregapi.block.fluid.BlockBaseFluid;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.BiomeNameSet;
import gregapi.code.HashSetNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackMap;
import gregapi.code.ItemStackSet;
import gregapi.code.ObjectStack;
import gregapi.code.TagData;
import gregapi.compat.buildcraft.ICompatBC;
import gregapi.compat.computercraft.ICompatCC;
import gregapi.compat.forestry.ICompatFR;
import gregapi.compat.galacticraft.ICompatGC;
import gregapi.compat.industrialcraft.ICompatIC2;
import gregapi.compat.industrialcraft.ICompatIC2EUItem;
import gregapi.compat.thaumcraft.ICompatTC;
import gregapi.config.Config;
import gregapi.dummies.DummyWorld;
import gregapi.fluid.FluidTankGT;
import gregapi.item.ItemArmorBase;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.item.multiitem.MultiItemTool;
import gregapi.item.multiitem.food.IFoodStat;
import gregapi.log.LogBuffer;
import gregapi.network.INetworkHandler;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.OreDictPrefix;
import gregapi.recipes.Recipe;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.render.IconContainerCopied;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.wooddict.PlankEntry;
import gregapi.worldgen.WorldgenObject;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author Gregorius Techneticies
 *
 * Class containing useful Constants and Lists.
 *
 * Mainly made for use with static imports.
 * I am doing this to have a better Table alike view on my Code, so I can change things faster using the Block Selection Mode of eclipse.
 *
 * For static imports in Eclipse go to "Window > Preferences > Java > Editor > Content Assist > Favorites" to set static importable Constant Classes such as this one as AutoCompleteable.
 */
public class CS {
	/** The Object containing the actual Mod GregTech and its API. */
	public static Abstract_Mod GT, GAPI, GAPI_POST;
	
	// unused: A, D, E, G, H, I, J, K, M, N, O, P, Q, R, S, X, Y, Z
	
	/** Because "true" and "false" are too long. Some Programmers might wanna kill me for that, but this looks much better than true and false, and also it is better to have something that is not 4 and 5 Characters long, because of symmetry */
	public static final boolean T = true, F = false;
	
	/** Character Set with all Numbers */
	public static final HashSet<Character> Ch_N = new HashSet<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
	/** Character Set with all lowercased Characters */
	public static final HashSet<Character> Ch_L = new HashSet<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
	/** Character Set with all uppercased Characters */
	public static final HashSet<Character> Ch_U = new HashSet<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
	
	/** The first 32 Bits */
	public static final int[] B = {1<<0,1<<1,1<<2,1<<3,1<<4,1<<5,1<<6,1<<7,1<<8,1<<9,1<<10,1<<11,1<<12,1<<13,1<<14,1<<15,1<<16,1<<17,1<<18,1<<19,1<<20,1<<21,1<<22,1<<23,1<<24,1<<25,1<<26,1<<27,1<<28,1<<29,1<<30,1<<31};

	/**
	 * Renamed from "MATERIAL_UNIT" to just "U"
	 *
	 * This is worth exactly one normal Item.
	 * This Constant can be divided by many commonly used Numbers such as
	 * 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 18, 20, 21, 22, 24, 25, ... 64, 81, 96, 144 or 1000
	 * without loosing precision and is for that reason used as Unit of Amount.
	 * But it is also small enough to be multiplied with larger Numbers.
	 *
	 * This is used to determine the amount of Material contained inside a prefixed Ore.
	 * For example Nugget = U / 9 as it contains out of 1/9th of an Ingot.
	 */
	public static final long U = 648648000, U2 = U/2, U3 = U/3, U4 = U/4, U5 = U/5, U6 = U/6, U7 = U/7, U8 = U/8, U9 = U/9, U10 = U/10, U11 = U/11, U12 = U/12, U13 = U/13, U14 = U/14, U15 = U/15, U16 = U/16, U17 = U/17, U18 = U/18, U20 = U/20, U24 = U/24, U25 = U/25, U32 = U/32, U36 = U/36, U40 = U/40, U48 = U/48, U50 = U/50, U64 = U/64, U72 = U/72, U80 = U/80, U96 = U/96, U100 = U/100, U128 = U/128, U144 = U/144, U192 = U/192, U200 = U/200, U240 = U/240, U256 = U/256, U288 = U/288, U480 = U/480, U500 = U/500, U512 = U/512, U1000 = U/1000, U1440 = U/1440;
	/** The Double Version of the Material Unit "U" */
	public static final double UD = U;

	/**
	 * Renamed from "FLUID_MATERIAL_UNIT" to just "L"
	 *
	 * Fluid per Material Unit (Prime Factors: 3 * 3 * 2 * 2 * 2 * 2)
	 */
	public static final long L = 144;

	/** The offset between Celsius and Kelvin. */
	public static final long C = 273; // 273.15 IRL

	/** The Default Environment Temperature in Kelvin */
	public static final long DEF_ENV_TEMP = C + 20, DEFAULT_ENVIRONMENT_TEMPERATURE = C + 20; // 293.15 IRL

	/** The Item WildCard Tag. Even shorter than the "-1" of the past */
	public static final short W = OreDictionary.WILDCARD_VALUE;

	/** Used Client Side as a placeholder for "is owned by someone other than you" */
	public static final UUID NOT_YOU = new UUID(+1, -1);

	/** The Size of an infinite NEI ItemStack */
	public static final byte NEI_INFINITE = 111;

	/** The Voltage Tiers. */
	public static final long[]
	VMIN    = { 1, 16,  64,  256, 1024,  4096, 16384,  65536,  262144, 1048576,  4194304, 16777216,  67108864,  268435456, 1073741824L,  4294967296L},
	VREC    = { 8, 32, 128,  512, 2048,  8192, 32768, 131072,  524288, 2097152,  8388608, 33554432, 134217728,  536870912, 2147483648L,  8589934592L},
	VMAX    = {16, 64, 256, 1024, 4096, 16384, 65536, 262144, 1048576, 4194304, 16777216, 67108864, 268435456, 1073741824, 4294967296L, 17179869184L},
	V       = { 8, 32, 128,  512, 2048,  8192, 32768, 131072,  524288, 2097152,  8388608, 33554432, 134217728,  536870912, 2147483648L,  8589934592L};

	/** The short Names for the Voltages */
	public static final String[] VN = {"ULV", "LV", "MV", "HV", "EV", "IV", "LuV", "ZPM", "UV", "PUV1", "PUV2", "PUV3", "PUV4", "PUV5", "\u03A9V", "\u03A9V"};

	/** The long Names for the Voltages */
	public static final String[] VOLTAGE_NAMES = {"Ultra Low Voltage", "Low Voltage", "Medium Voltage", "High Voltage", "Extreme Voltage", "Insane Voltage", "Ludicrous Voltage", "ZPM Voltage", "Ultimate Voltage", "Post Ultimate Voltage 1", "Post Ultimate Voltage 2", "Post Ultimate Voltage 3", "Post Ultimate Voltage 4", "Post Ultimate Voltage 5", "Omega Voltage", "Omega Voltage"};
	
	/** The short Names for the USB Sticks */
	public static final String[] OD_USB_STICKS = {"gt:usbstick0", "gt:usbstick1", "gt:usbstick2", "gt:usbstick3", "gt:usbstick4", "gt:usbstick5", "gt:usbstick6", "gt:usbstick7", "gt:usbstick8", "gt:usbstick9"};
	/** The short Names for the USB Cables */
	public static final String[] OD_USB_CABLES = {"gt:usbcable0", "gt:usbcable1", "gt:usbcable2", "gt:usbcable3", "gt:usbcable4", "gt:usbcable5", "gt:usbcable6", "gt:usbcable7", "gt:usbcable8", "gt:usbcable9"};
	/** The short Names for the USB Drives */
	public static final String[] OD_USB_DRIVES = {"gt:usbdrive0", "gt:usbdrive1", "gt:usbdrive2", "gt:usbdrive3", "gt:usbdrive4", "gt:usbdrive5", "gt:usbdrive6", "gt:usbdrive7", "gt:usbdrive8", "gt:usbdrive9"};
	/** The short Names for the Circuits */
	public static final String[] OD_CIRCUITS = {"gt:circuit0", "gt:circuit1", "gt:circuit2", "gt:circuit3", "gt:circuit4", "gt:circuit5", "gt:circuit6", "gt:circuit7", "gt:circuit8", "gt:circuit9"};
	
	/** Subtext Numbers from 0 to 1000. */
	public static final String[] NUM_SUB = {
	  "\u2080", "\u2081", "\u2082", "\u2083", "\u2084", "\u2085", "\u2086", "\u2087", "\u2088", "\u2089"
	, "\u2081\u2080", "\u2081\u2081", "\u2081\u2082", "\u2081\u2083", "\u2081\u2084", "\u2081\u2085", "\u2081\u2086", "\u2081\u2087", "\u2081\u2088", "\u2081\u2089"
	, "\u2082\u2080", "\u2082\u2081", "\u2082\u2082", "\u2082\u2083", "\u2082\u2084", "\u2082\u2085", "\u2082\u2086", "\u2082\u2087", "\u2082\u2088", "\u2082\u2089"
	, "\u2083\u2080", "\u2083\u2081", "\u2083\u2082", "\u2083\u2083", "\u2083\u2084", "\u2083\u2085", "\u2083\u2086", "\u2083\u2087", "\u2083\u2088", "\u2083\u2089"
	, "\u2084\u2080", "\u2084\u2081", "\u2084\u2082", "\u2084\u2083", "\u2084\u2084", "\u2084\u2085", "\u2084\u2086", "\u2084\u2087", "\u2084\u2088", "\u2084\u2089"
	, "\u2085\u2080", "\u2085\u2081", "\u2085\u2082", "\u2085\u2083", "\u2085\u2084", "\u2085\u2085", "\u2085\u2086", "\u2085\u2087", "\u2085\u2088", "\u2085\u2089"
	, "\u2086\u2080", "\u2086\u2081", "\u2086\u2082", "\u2086\u2083", "\u2086\u2084", "\u2086\u2085", "\u2086\u2086", "\u2086\u2087", "\u2086\u2088", "\u2086\u2089"
	, "\u2087\u2080", "\u2087\u2081", "\u2087\u2082", "\u2087\u2083", "\u2087\u2084", "\u2087\u2085", "\u2087\u2086", "\u2087\u2087", "\u2087\u2088", "\u2087\u2089"
	, "\u2088\u2080", "\u2088\u2081", "\u2088\u2082", "\u2088\u2083", "\u2088\u2084", "\u2088\u2085", "\u2088\u2086", "\u2088\u2087", "\u2088\u2088", "\u2088\u2089"
	, "\u2089\u2080", "\u2089\u2081", "\u2089\u2082", "\u2089\u2083", "\u2089\u2084", "\u2089\u2085", "\u2089\u2086", "\u2089\u2087", "\u2089\u2088", "\u2089\u2089"
	, "\u2081\u2080\u2080", "\u2081\u2080\u2081", "\u2081\u2080\u2082", "\u2081\u2080\u2083", "\u2081\u2080\u2084", "\u2081\u2080\u2085", "\u2081\u2080\u2086", "\u2081\u2080\u2087", "\u2081\u2080\u2088", "\u2081\u2080\u2089"
	, "\u2081\u2081\u2080", "\u2081\u2081\u2081", "\u2081\u2081\u2082", "\u2081\u2081\u2083", "\u2081\u2081\u2084", "\u2081\u2081\u2085", "\u2081\u2081\u2086", "\u2081\u2081\u2087", "\u2081\u2081\u2088", "\u2081\u2081\u2089"
	, "\u2081\u2082\u2080", "\u2081\u2082\u2081", "\u2081\u2082\u2082", "\u2081\u2082\u2083", "\u2081\u2082\u2084", "\u2081\u2082\u2085", "\u2081\u2082\u2086", "\u2081\u2082\u2087", "\u2081\u2082\u2088", "\u2081\u2082\u2089"
	, "\u2081\u2083\u2080", "\u2081\u2083\u2081", "\u2081\u2083\u2082", "\u2081\u2083\u2083", "\u2081\u2083\u2084", "\u2081\u2083\u2085", "\u2081\u2083\u2086", "\u2081\u2083\u2087", "\u2081\u2083\u2088", "\u2081\u2083\u2089"
	, "\u2081\u2084\u2080", "\u2081\u2084\u2081", "\u2081\u2084\u2082", "\u2081\u2084\u2083", "\u2081\u2084\u2084", "\u2081\u2084\u2085", "\u2081\u2084\u2086", "\u2081\u2084\u2087", "\u2081\u2084\u2088", "\u2081\u2084\u2089"
	, "\u2081\u2085\u2080", "\u2081\u2085\u2081", "\u2081\u2085\u2082", "\u2081\u2085\u2083", "\u2081\u2085\u2084", "\u2081\u2085\u2085", "\u2081\u2085\u2086", "\u2081\u2085\u2087", "\u2081\u2085\u2088", "\u2081\u2085\u2089"
	, "\u2081\u2086\u2080", "\u2081\u2086\u2081", "\u2081\u2086\u2082", "\u2081\u2086\u2083", "\u2081\u2086\u2084", "\u2081\u2086\u2085", "\u2081\u2086\u2086", "\u2081\u2086\u2087", "\u2081\u2086\u2088", "\u2081\u2086\u2089"
	, "\u2081\u2087\u2080", "\u2081\u2087\u2081", "\u2081\u2087\u2082", "\u2081\u2087\u2083", "\u2081\u2087\u2084", "\u2081\u2087\u2085", "\u2081\u2087\u2086", "\u2081\u2087\u2087", "\u2081\u2087\u2088", "\u2081\u2087\u2089"
	, "\u2081\u2088\u2080", "\u2081\u2088\u2081", "\u2081\u2088\u2082", "\u2081\u2088\u2083", "\u2081\u2088\u2084", "\u2081\u2088\u2085", "\u2081\u2088\u2086", "\u2081\u2088\u2087", "\u2081\u2088\u2088", "\u2081\u2088\u2089"
	, "\u2081\u2089\u2080", "\u2081\u2089\u2081", "\u2081\u2089\u2082", "\u2081\u2089\u2083", "\u2081\u2089\u2084", "\u2081\u2089\u2085", "\u2081\u2089\u2086", "\u2081\u2089\u2087", "\u2081\u2089\u2088", "\u2081\u2089\u2089"
	, "\u2082\u2080\u2080", "\u2082\u2080\u2081", "\u2082\u2080\u2082", "\u2082\u2080\u2083", "\u2082\u2080\u2084", "\u2082\u2080\u2085", "\u2082\u2080\u2086", "\u2082\u2080\u2087", "\u2082\u2080\u2088", "\u2082\u2080\u2089"
	, "\u2082\u2081\u2080", "\u2082\u2081\u2081", "\u2082\u2081\u2082", "\u2082\u2081\u2083", "\u2082\u2081\u2084", "\u2082\u2081\u2085", "\u2082\u2081\u2086", "\u2082\u2081\u2087", "\u2082\u2081\u2088", "\u2082\u2081\u2089"
	, "\u2082\u2082\u2080", "\u2082\u2082\u2081", "\u2082\u2082\u2082", "\u2082\u2082\u2083", "\u2082\u2082\u2084", "\u2082\u2082\u2085", "\u2082\u2082\u2086", "\u2082\u2082\u2087", "\u2082\u2082\u2088", "\u2082\u2082\u2089"
	, "\u2082\u2083\u2080", "\u2082\u2083\u2081", "\u2082\u2083\u2082", "\u2082\u2083\u2083", "\u2082\u2083\u2084", "\u2082\u2083\u2085", "\u2082\u2083\u2086", "\u2082\u2083\u2087", "\u2082\u2083\u2088", "\u2082\u2083\u2089"
	, "\u2082\u2084\u2080", "\u2082\u2084\u2081", "\u2082\u2084\u2082", "\u2082\u2084\u2083", "\u2082\u2084\u2084", "\u2082\u2084\u2085", "\u2082\u2084\u2086", "\u2082\u2084\u2087", "\u2082\u2084\u2088", "\u2082\u2084\u2089"
	, "\u2082\u2085\u2080", "\u2082\u2085\u2081", "\u2082\u2085\u2082", "\u2082\u2085\u2083", "\u2082\u2085\u2084", "\u2082\u2085\u2085", "\u2082\u2085\u2086", "\u2082\u2085\u2087", "\u2082\u2085\u2088", "\u2082\u2085\u2089"
	, "\u2082\u2086\u2080", "\u2082\u2086\u2081", "\u2082\u2086\u2082", "\u2082\u2086\u2083", "\u2082\u2086\u2084", "\u2082\u2086\u2085", "\u2082\u2086\u2086", "\u2082\u2086\u2087", "\u2082\u2086\u2088", "\u2082\u2086\u2089"
	, "\u2082\u2087\u2080", "\u2082\u2087\u2081", "\u2082\u2087\u2082", "\u2082\u2087\u2083", "\u2082\u2087\u2084", "\u2082\u2087\u2085", "\u2082\u2087\u2086", "\u2082\u2087\u2087", "\u2082\u2087\u2088", "\u2082\u2087\u2089"
	, "\u2082\u2088\u2080", "\u2082\u2088\u2081", "\u2082\u2088\u2082", "\u2082\u2088\u2083", "\u2082\u2088\u2084", "\u2082\u2088\u2085", "\u2082\u2088\u2086", "\u2082\u2088\u2087", "\u2082\u2088\u2088", "\u2082\u2088\u2089"
	, "\u2082\u2089\u2080", "\u2082\u2089\u2081", "\u2082\u2089\u2082", "\u2082\u2089\u2083", "\u2082\u2089\u2084", "\u2082\u2089\u2085", "\u2082\u2089\u2086", "\u2082\u2089\u2087", "\u2082\u2089\u2088", "\u2082\u2089\u2089"
	, "\u2083\u2080\u2080\u208A"
	};
	
	/** The value of how many Voltz/Mekanism Joules are worth an EU. */
	public static final int  J_PER_EU = 10;
	/** The value of how many RF are worth an MJ. */
	public static final int RF_PER_MJ = 10;
	/** The value of how many RF are worth an EU. */
	public static final int RF_PER_EU =  4;
	/** The value of how many Fuel Ticks a Furnace Smelt has. */
	public static int TICKS_PER_SMELT = 200;
	/** The value of how many Energy Units are worth a Furnace Tick in regards of Fuel -> Energy */
	public static int EU_PER_FURNACE_TICK = 25;
	/** The value of how many Energy Units are worth a Smelt Operation in regards of Energy -> Smelt, because many Mods including IC2 have cheaper smelting. In GT, I go for a Max Efficiency of 19.53125 times that value, so 256 GU per Furnace Operation. */
	public static int EU_PER_SMELT = 256;
	/** The value of how many Energy Units a Liter of Lava is worth. It is worth about 180 to 333 RF in TE. Well, I go for 320 RF in GT, meaning 80 GU/L or 80000 GU per Bucket. Cannot be above 200 GU/L or else MFR exploit with infinite Lava! */
	public static int EU_PER_LAVA = 80;
	/** The value of how many Energy Units a Liter of Hot Coolant also known as Heatant is worth. It is worth 20 EU in IC2 Experimental, so it's the same 20 GU in GT6. */
	public static int EU_PER_COOLANT = 20;
	/** The value of how many Energy Units a Liter of Semiheavy Water needs to turn into Hot Semiheavy Water. */
	public static int EU_PER_SEMI_HEAVY_WATER = 40;
	/** The value of how many Energy Units a Liter of Heavy Water needs to turn into Hot Heavy Water. */
	public static int EU_PER_HEAVY_WATER = 50;
	/** The value of how many Energy Units a Liter of Heavy Water needs to turn into Hot Tritiated Water. */
	public static int EU_PER_TRITIATED_WATER = 60;
	/** The value of how many Energy Units a Liter of Molten Sodium needs to turn into Hot Molten Sodium. */
	public static int EU_PER_SODIUM = 30;
	/** The value of how many Energy Units a Liter of Molten Tin needs to turn into Hot Molten Tin. */
	public static int EU_PER_TIN = 40;
	/** The value of how many Energy Units a Liter of Carbon Dioxide needs to turn into Hot Carbon Dioxide. */
	public static int EU_PER_CO2 = 20;
	/** The value of how many Energy Units a Liter of Helium needs to turn into Hot Helium. */
	public static int EU_PER_HELIUM = 30;
	/** The value of how many Energy Units a Liter of Lithium Chloride needs to turn into Hot Helium. */
	public static int EU_PER_LICL = 15;
	/** The value of how many Energy Units a Liter of Molten Thorium Salt needs to turn into Molten Salt. */
	public static int EU_PER_THORIUM_SALT = 10000;
	/** The value of how many Energy Units a Liter of Water needs to turn into Steam. */
	public static int EU_PER_WATER = 80;
	/** The value of how much Steam an Energy Unit is worth. The Standard is 2 Steam = 1 EU. */
	public static int STEAM_PER_EU = 2;
	/** The value of how much Steam a Liter of Water is worth. The Standard is 160 Steam = 1 Water. */
	public static int STEAM_PER_WATER = 160;

	/** A few Default Values for Light Opacity. */
	public static final int LIGHT_OPACITY_NONE = 0, LIGHT_OPACITY_LEAVES = 1, LIGHT_OPACITY_WATER = 3, LIGHT_OPACITY_MAX = 255;
	
	public static final Set<String>
	  BIOMES_RIVER          = new BiomeNameSet(BiomeGenBase.river, BiomeGenBase.frozenRiver, "Lush River", "Estuary", "Twilight Stream", "Tropical River", "Riparian Zone", "Sandstone Canyon", "Sandstone Canyon 2", "Creek Bed", "rwg_riverIce", "rwg_riverCold", "rwg_riverTemperate", "rwg_riverHot", "rwg_riverWet", "rwg_riverOasis")
	, BIOMES_RIVER_LAKE     = new BiomeNameSet(BiomeGenBase.river, BiomeGenBase.frozenRiver, "Lush River", "Estuary", "Twilight Stream", "Tropical River", "Riparian Zone", "Sandstone Canyon", "Sandstone Canyon 2", "Creek Bed", "rwg_riverIce", "rwg_riverCold", "rwg_riverTemperate", "rwg_riverHot", "rwg_riverWet", "rwg_riverOasis", "Tropical Lake", "Twilight Lake", "Lake", "Oasis", "Woodland Lake", "Woodland Lake Edge") // "Ephemeral Lake", "Ephemeral Lake Edge" those are vapourizing Lakes that vanish depending on Season.
	, BIOMES_LAKE           = new BiomeNameSet("Tropical Lake", "Twilight Lake", "Lake", "Oasis", "Woodland Lake", "Woodland Lake Edge", "Ephemeral Lake", "Ephemeral Lake Edge")
	, BIOMES_OCEAN          = new BiomeNameSet(BiomeGenBase.ocean, BiomeGenBase.frozenOcean, BiomeGenBase.deepOcean, "Coral Reef", "Kelp Forest", "Mangrove", "Ocean Oil Field", "Improved Oceans", "Tropical Ocean", "rwg_oceanIce", "rwg_oceanCold", "rwg_oceanTemperate", "rwg_oceanHot", "rwg_oceanWet", "rwg_oceanOasis")
	, BIOMES_OCEAN_BEACH    = new BiomeNameSet(BiomeGenBase.ocean, BiomeGenBase.frozenOcean, BiomeGenBase.deepOcean, BiomeGenBase.beach, BiomeGenBase.coldBeach, BiomeGenBase.stoneBeach, BiomeGenBase.mushroomIslandShore, "Coral Reef", "Kelp Forest", "Mangrove", "Ocean Oil Field", "Improved Oceans", "Tropical Ocean", "rwg_oceanIce", "rwg_oceanCold", "rwg_oceanTemperate", "rwg_oceanHot", "rwg_oceanWet", "rwg_oceanOasis", "Tropical Beach")
	, BIOMES_INFINITE_WATER = new BiomeNameSet(BiomeGenBase.ocean, BiomeGenBase.frozenOcean, BiomeGenBase.deepOcean, BiomeGenBase.beach, BiomeGenBase.coldBeach, BiomeGenBase.stoneBeach, BiomeGenBase.mushroomIslandShore, "Coral Reef", "Kelp Forest", "Mangrove", "Ocean Oil Field", "Improved Oceans", "Tropical Ocean", "rwg_oceanIce", "rwg_oceanCold", "rwg_oceanTemperate", "rwg_oceanHot", "rwg_oceanWet", "rwg_oceanOasis", "Tropical Beach", BiomeGenBase.river, BiomeGenBase.frozenRiver, "Lush River", "Estuary", "Twilight Stream", "Tropical River", "Riparian Zone", "Sandstone Canyon", "Sandstone Canyon 2", "Creek Bed", "rwg_riverIce", "rwg_riverCold", "rwg_riverTemperate", "rwg_riverHot", "rwg_riverWet", "rwg_riverOasis", "Tropical Lake", "Twilight Lake", "Lake", "Oasis", "Woodland Lake", "Woodland Lake Edge")
	
	, BIOMES_JUNGLE         = new BiomeNameSet(BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.jungleEdge, "Undergound Jungle", "Jungle Island", "Extreme Jungle", "Mini Jungle", "Rainforest Hills")
	, BIOMES_CINNAMON       = new BiomeNameSet(BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.jungleEdge, "Undergound Jungle", "Jungle Island", "Extreme Jungle", "Mini Jungle", "Rainforest Hills")
	, BIOMES_BLUEMAHOE      = new BiomeNameSet(BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.jungleEdge, "Undergound Jungle", "Jungle Island", "Extreme Jungle", "Mini Jungle", "Rainforest Hills")
	
	, BIOMES_DESERT         = new BiomeNameSet(BiomeGenBase.desert, BiomeGenBase.desertHills, "Sahara", "Red Desert", "Desert Archipelago", "Oasis", "Sandstone Canyon", "Sandstone Canyon 2", "Sandstone Ranges", "Sahel", "Lush Desert", "Desert Oil Field", "Desert Island", "Mountainous Desert", "Desert Mountains", "Volcanic Desert", "Ulterior Outback")
	, BIOMES_MESA           = new BiomeNameSet(BiomeGenBase.mesa, BiomeGenBase.mesaPlateau, BiomeGenBase.mesaPlateau_F, "Canyon", "Mesa (Bryce)", "Mesa", "Clay Hills")
	, BIOMES_SAVANNA        = new BiomeNameSet(BiomeGenBase.savanna, BiomeGenBase.savannaPlateau, "Steppe", "Subterranean Savannah", "Oak Savanna", "Savannah", "Savanna", "Shrubland", "Shrublands", "Roofed Shrublands", "Xeric Savanna", "Xeric Shrubland", "Prairie")
	
	, BIOMES_SWAMP          = new BiomeNameSet(BiomeGenBase.swampland, "Green Swamplands", "DeepSwamp", "Land of Lakes Marsh", "Marsh", "Lush Swamp", "Moor", "Mire", "Bog", "Twilight Swamp", "Submerged Swamp", "Fire Swamp")
	, BIOMES_WILLOW         = new BiomeNameSet(BiomeGenBase.swampland, "Green Swamplands", "DeepSwamp", "Land of Lakes Marsh", "Marsh", "Lush Swamp", "Moor", "Mire", "Bog", "Twilight Swamp", "Submerged Swamp", "Fire Swamp")
	
	, BIOMES_TAIGA          = new BiomeNameSet(BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.coldTaiga, BiomeGenBase.coldTaigaHills, BiomeGenBase.megaTaiga, BiomeGenBase.megaTaigaHills, "Mountain Taiga", "Pinelands", "Tall Pine Forest", "Shield", "Cold Boreal Forest", "Cold Cypress Forest", "Cold Fir Forest", "Cold Pine Forest", "Boreal Archipelago", "Boreal Forest", "Boreal Plateau", "Twilight Highlands")
	, BIOMES_RUBBER         = new BiomeNameSet(BiomeGenBase.taiga, BiomeGenBase.taigaHills, BiomeGenBase.coldTaiga, BiomeGenBase.coldTaigaHills, BiomeGenBase.megaTaiga, BiomeGenBase.megaTaigaHills, "Mountain Taiga", "Pinelands", "Tall Pine Forest", "Shield", "Cold Boreal Forest", "Cold Cypress Forest", "Cold Fir Forest", "Cold Pine Forest", "Boreal Archipelago", "Boreal Forest", "Boreal Plateau", "Twilight Highlands")
	
	, BIOMES_FROZEN         = new BiomeNameSet(BiomeGenBase.icePlains, BiomeGenBase.iceMountains, BiomeGenBase.coldTaiga, BiomeGenBase.coldTaigaHills, "Snow Island", "Ice Plains Spikes", "Ice Wasteland", "Frost Forest", "Snowy Rainforest", "Snow Forest", "Snowy Forest", "Twilight Glacier", "Alpine", "Glacier", "Tundra", "Snowy Desert", "Snowy Plateau", "Snowy Ranges", "Snowy Wastelands", "Polar Desert", "Ice Sheet", "Frozen Archipelago", "Alpine Mountains", "Alpine Mountains Edge", "Alpine Tundra")
	
	, BIOMES_WOODS          = new BiomeNameSet(BiomeGenBase.forest, BiomeGenBase.forestHills, "Autumn Forest", "Elysian Forest", "Meadow Forest", "Seasonal Forest", "Seasonal Forest Clearing", "Forested Hills", "Forested Island", "Snow Forest", "Forest Island", "Forested Archipelago", "Forested Mountains", "Forested Valley", "Redwood Forest", "Woodlands", "Woodland Mountains", "Woodland Field", "Woodland Hills", "Woodland Lake", "Woodland Lake Edge", "Dark Forest", "Dark Forest Center", "Dense Twilight Forest", "Twilight Forest", "Firefly Forest", "Maple Woods", BiomeGenBase.roofedForest, BiomeGenBase.birchForest, BiomeGenBase.birchForestHills, "Pine Forest", "Rainforest", "Rainforest Valley", "Redwood Forest", "Lush Redwoods", "Spruce Woods", "Autumn Woods", "Flower Forest", "Birch Hills", "Woodlands", "Temperate Rainforest", "Pinelands", "Tall Pine Forest", "Shield", "Mystic Grove", "Ominous Woods", "Blossom Hills", "Blossom Woods", "Aspen Forest", "Aspen Hills", "Cypress Forest", "Silver Pine Forest", "Silver Pine Hills", "Fir Forest", "Flowery Archipelago", "Oak Forest", "Pine Forest", "Pine Forest Archipelago", "Rainforest Hills", "Rainforest Mountains", "Extreme Rainforest Mountains")
	, BIOMES_FOREST         = new BiomeNameSet(BiomeGenBase.forest, BiomeGenBase.forestHills, "Autumn Forest", "Elysian Forest", "Meadow Forest", "Seasonal Forest", "Seasonal Forest Clearing", "Forested Hills", "Forested Island", "Snow Forest", "Forest Island", "Forested Archipelago", "Forested Mountains", "Forested Valley", "Redwood Forest", "Woodlands", "Woodland Mountains", "Woodland Field", "Woodland Hills", "Woodland Lake", "Woodland Lake Edge", "Dark Forest", "Dark Forest Center", "Dense Twilight Forest", "Twilight Forest", "Firefly Forest")
	, BIOMES_MAPLE          = new BiomeNameSet(BiomeGenBase.forest, BiomeGenBase.forestHills, "Autumn Forest", "Elysian Forest", "Meadow Forest", "Seasonal Forest", "Seasonal Forest Clearing", "Forested Hills", "Forested Island", "Snow Forest", "Forest Island", "Forested Archipelago", "Forested Mountains", "Forested Valley", "Maple Woods", "Firefly Forest")
	, BIOMES_DARK_FOREST    = new BiomeNameSet(BiomeGenBase.roofedForest, "Dark Forest", "Dark Forest Center")
	
	, BIOMES_PLAINS         = new BiomeNameSet(BiomeGenBase.plains, "Meadow", "Grassland", "Flower Field", "Sunflower Plains", "Clearing", "Twilight Clearing", "Elysian Fields", "Lowlands", "Origin Valley", "Grassy Archipelago", "Alfheim", "Rainforest Plains", "Tropics", "Highlands", "Bald Hill", "Tundra", "Low Hills", "Mining Biome")
	, BIOMES_HAZEL          = new BiomeNameSet(BiomeGenBase.plains, "Meadow", "Grassland", "Flower Field", "Sunflower Plains", "Clearing", "Twilight Clearing", "Elysian Fields", "Lowlands", "Origin Valley", "Grassy Archipelago", "Alfheim")
	
	, BIOMES_COCONUT        = new BiomeNameSet(BiomeGenBase.beach, "Tropical Ocean", "Tropical Beach", "Tropical River", "Tropical Lake", "Tropical Archipelago", "Tropical Islands", "Tropics", "Oasis")
	
	, BIOMES_MOUNTAINS      = new BiomeNameSet(BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.extremeHillsPlus, BiomeGenBase.stoneBeach, "Mountainous Archipelago", "Mountains", "Mountains Edge", "Plateau", "Highlands", "Highlands Center", "Twilight Highlands", "Thornlands", "Alps", "Cliffs", "Flying Mountains", "Rock Mountains", "Snow Mountains", "Rock Island", "Valley", "Alpine Mountains", "Alpine Mountains Edge", "Alpine Tundra", "Stone Canyon", "Stone Canyon 2", "Rocky Desert", "Rocky Hills", "Rainforest Mountains", "Extreme Rainforest Mountains")
	, BIOMES_BLUESPRUCE     = new BiomeNameSet(BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.extremeHillsPlus, BiomeGenBase.stoneBeach, "Mountainous Archipelago", "Mountains", "Mountains Edge", "Plateau", "Highlands", "Highlands Center", "Twilight Highlands", "Thornlands", "Alps", "Cliffs", "Flying Mountains", "Rock Mountains", "Snow Mountains", "Rock Island", "Valley", "Alpine Mountains", "Alpine Mountains Edge", "Alpine Tundra", "Stone Canyon", "Stone Canyon 2", "Rocky Desert", "Rocky Hills", "Rainforest Mountains", "Extreme Rainforest Mountains")
	
	, BIOMES_VOLCANIC       = new BiomeNameSet("Fire Swamp", "Volcano", "Volcano Island", "Volcanic Desert")
	
	, BIOMES_SHROOM         = new BiomeNameSet(BiomeGenBase.mushroomIsland, BiomeGenBase.mushroomIslandShore, "Fungal Forest", "Mushroom Forest", "Deep Mushroom Forest")
	
	, BIOMES_MAGICAL        = new BiomeNameSet("Magical Forest", "Eldritch", "Enchanted Forest", "Mystic Grove", "Alfheim", "Tainted Land", "Eerie", "WyvernBiome", "Ominous Woods")
	, BIOMES_RAINBOWOOD     = new BiomeNameSet("Enchanted Forest")
	, BIOMES_MAGICAL_GOOD   = new BiomeNameSet("Magical Forest", "Eldritch", "Enchanted Forest", "Mystic Grove", "Alfheim")
	, BIOMES_MAGICAL_BAD    = new BiomeNameSet("Tainted Land", "Eerie", "WyvernBiome", "Ominous Woods")
	
	, BIOMES_WASTELANDS     = new BiomeNameSet("Wasteland", "Wastelands", "Wasteland Mountains", "Wasteland Forest", "Radioactive Wasteland")
	, BIOMES_RADIOACTIVE    = new BiomeNameSet("Radioactive Wasteland")
	
	, BIOMES_NETHER         = new BiomeNameSet(BiomeGenBase.hell, "Ruptured Chasm", "Abyssal Shadowland", "Crystalline Crag", "Basalt Deltas", "Crimson Forest", "Soul Sand Valley", "Warped Forest", "Foxfire Swamp")
	, BIOMES_END            = new BiomeNameSet(BiomeGenBase.sky)
	, BIOMES_EREBUS         = new BiomeNameSet("Undergound Jungle", "Volcanic Desert", "Subterranean Savannah", "Elysian Fields", "Ulterior Outback", "Fungal Forest", "Submerged Swamp", "Elysian Forest")
	, BIOMES_VOID           = new BiomeNameSet("Space", "space")
	, BIOMES_MOON           = new BiomeNameSet("Moon", "moon")
	, BIOMES_MARS           = new BiomeNameSet("Mars", "mars", "marsFlat")
	, BIOMES_ASTEROIDS      = new BiomeNameSet("Asteroids", "asteroids")
	, BIOMES_SPACE          = new BiomeNameSet("Space", "Alien Forest", "Moon", "mercury", "venus", "jupiter", "saturn", "uranus", "neptune", "pluto", "ceres", "eris", "europa", "io", "deimos", "phobos", "triton", "callisto", "ganymede", "rhea", "titan", "Hot Dry Rock", "Stormland", "CrystalChasms", "moon", "marsFlat", "Asteroids", "asteroids", "space", "DeepSwamp", "Marsh", "OceanSpires", "SpacePartiallySubmerged", "SpaceLowIslands", "SpaceRockyWaters", "SpaceMidHills", "SpaceHighPlateaus", "SpaceLowHills", "SpaceMidPlains", "SpaceLowPlains", "SpaceDeepOceans", "SpaceOceans", "SpaceShallowWaters", "SpaceDefault", "Pluto", "Pluto2", "Pluto3", "Pluto4", "Kuiper Belt", "Io", "IoAsh", "Haumea");
	
	// "Wasteland City", "Fens", "Carr", "Kakadu", "Scree", "Scrub", "Basin", "Badlands", "Outback", "Windy Island"
	
	/** Stores the Coordinates that any given Player last interacted with. */
	public static final Map<EntityPlayer, ChunkCoordinates> PLAYER_LAST_CLICKED = new IdentityHashMap<>();
	
	/** a Random generator so I don't need to instantiate a new one all the time. */
	public static final Random RNGSUS = new Random(), RANDOM = RNGSUS;
	
	/** Current Time on the Server, since the last Reboot. */
	public static long SERVER_TIME = 0;
	/** Synchronizes all Server to Client Updates to be at the same time. */
	public static boolean SYNC_SECOND = T;
	/** Current Time on the Client. Used for Animations. */
	public static long CLIENT_TIME = 0;
	/** Is locked updateEntities and similar are running on the tick. */
	public static final ReentrantLock TICK_LOCK = new ReentrantLock();
	
	/** If I ever need to talk in Chat. XD */
	public static final String CHAT_GREG = LH.Chat.WHITE+"<"+LH.Chat.BLUE+"GregoriusT"+LH.Chat.WHITE+"> ";
	/** The Colour White as RGB Short Array. */
	public static final short[] UNCOLOURED = {255, 255, 255, 255};
	/** The Colour White as simple Integer (0x00ffffff). */
	public static final int UNCOLORED = 16777215;
	public static final int ALL_NON_ALPHA_COLOR = 0x00ffffff;
	
	public static final int[] RAINBOW_ARRAY = {
		0xff0000,
		0xff4000,
		0xff8000,
		0xffc000,
		0xffff00,
		0xc0ff00,
		0x80ff00,
		0x40ff00,
		0x00ff00,
		0x00ff40,
		0x00ff80,
		0x00ffc0,
		0x00ffff,
		0x00c0ff,
		0x0080ff,
		0x0040ff,
		0x0000ff,
		0x4000ff,
		0x8000ff,
		0xc000ff,
		0xff00ff,
		0xff00c0,
		0xff0080,
		0xff0040,
	};
	
	/** Some Colour Arrays */
	public static final short[]
	  CA_WHITE              = {255, 255, 255, 255}
	, CA_GRAY_192           = {192, 192, 192, 255}
	, CA_GRAY_128           = {128, 128, 128, 255}
	, CA_GRAY_64            = { 64,  64,  64, 255}
	, CA_GRAY_32            = { 32,  32,  32, 255}
	, CA_RED_255            = {255,   0,   0, 255}
	, CA_RED_192            = {192,   0,   0, 255}
	, CA_RED_128            = {128,   0,   0, 255}
	, CA_RED_64             = { 64,   0,   0, 255}
	, CA_RED_32             = { 32,   0,   0, 255}
	, CA_GREEN_255          = {  0, 255,   0, 255}
	, CA_GREEN_192          = {  0, 192,   0, 255}
	, CA_GREEN_128          = {  0, 128,   0, 255}
	, CA_GREEN_64           = {  0,  64,   0, 255}
	, CA_GREEN_32           = {  0,  32,   0, 255}
	, CA_CYAN_255           = {  0, 255, 255, 255}
	, CA_CYAN_192           = {  0, 192, 192, 255}
	, CA_CYAN_128           = {  0, 128, 128, 255}
	, CA_CYAN_64            = {  0,  64,  64, 255}
	, CA_CYAN_32            = {  0,  32,  32, 255}
	, CA_BLUE_255           = {  0,   0, 255, 255}
	, CA_BLUE_192           = {  0,   0, 192, 255}
	, CA_BLUE_128           = {  0,   0, 128, 255}
	, CA_BLUE_64            = {  0,   0,  64, 255}
	, CA_BLUE_32            = {  0,   0,  32, 255}
	, CA_LIGHT_BLUE_255     = {128, 128, 255, 255}
	, CA_LIGHT_BLUE_192     = { 96,  96, 192, 255}
	, CA_LIGHT_BLUE_128     = { 64,  64, 128, 255}
	, CA_LIGHT_BLUE_64      = { 32,  32,  64, 255}
	, CA_LIGHT_BLUE_32      = { 16,  16,  32, 255}
	, CA_YELLOW_255         = {255, 255,   0, 255}
	, CA_YELLOW_192         = {192, 192,   0, 255}
	, CA_YELLOW_128         = {128, 128,   0, 255}
	, CA_YELLOW_64          = { 64,  64,   0, 255}
	, CA_YELLOW_32          = { 32,  32,   0, 255}
	, CA_LIGHT_YELLOW_255   = {255, 255, 128, 255}
	, CA_LIGHT_YELLOW_192   = {192, 192,  96, 255}
	, CA_LIGHT_YELLOW_128   = {128, 128,  64, 255}
	, CA_LIGHT_YELLOW_64    = { 64,  64,  32, 255}
	, CA_LIGHT_YELLOW_32    = { 32,  32,  16, 255}
	;
	
	public static final short[]
	  DYE_None              = {255, 255, 255, 255}
	, DYE_Black             = { 32,  32,  32, 255}
	, DYE_Red               = {255,   0,   0, 255}
	, DYE_Green             = {  0, 255,   0, 255}
	, DYE_Brown             = { 96,  64,   0, 255}
	, DYE_Blue              = {  0,   0, 255, 255}
	, DYE_Purple            = {128,   0, 128, 255}
	, DYE_Cyan              = {  0, 255, 255, 255}
	, DYE_LightGray         = {192, 192, 192, 255}
	, DYE_Gray              = {128, 128, 128, 255}
	, DYE_Pink              = {255, 192, 192, 255}
	, DYE_Lime              = {128, 255, 128, 255}
	, DYE_Yellow            = {255, 255,   0, 255}
	, DYE_LightBlue         = {128, 128, 255, 255}
	, DYE_Magenta           = {255,   0, 255, 255}
	, DYE_Orange            = {255, 128,   0, 255}
	, DYE_White             = {255, 255, 255, 255}
	;
	
	public static final int
	  DYE_INT_None            = UT.Code.getRGBInt(DYE_None)
	, DYE_INT_Black           = UT.Code.getRGBInt(DYE_Black)
	, DYE_INT_Red             = UT.Code.getRGBInt(DYE_Red)
	, DYE_INT_Green           = UT.Code.getRGBInt(DYE_Green)
	, DYE_INT_Brown           = UT.Code.getRGBInt(DYE_Brown)
	, DYE_INT_Blue            = UT.Code.getRGBInt(DYE_Blue)
	, DYE_INT_Purple          = UT.Code.getRGBInt(DYE_Purple)
	, DYE_INT_Cyan            = UT.Code.getRGBInt(DYE_Cyan)
	, DYE_INT_LightGray       = UT.Code.getRGBInt(DYE_LightGray)
	, DYE_INT_Gray            = UT.Code.getRGBInt(DYE_Gray)
	, DYE_INT_Pink            = UT.Code.getRGBInt(DYE_Pink)
	, DYE_INT_Lime            = UT.Code.getRGBInt(DYE_Lime)
	, DYE_INT_Yellow          = UT.Code.getRGBInt(DYE_Yellow)
	, DYE_INT_LightBlue       = UT.Code.getRGBInt(DYE_LightBlue)
	, DYE_INT_Magenta         = UT.Code.getRGBInt(DYE_Magenta)
	, DYE_INT_Orange          = UT.Code.getRGBInt(DYE_Orange)
	, DYE_INT_White           = UT.Code.getRGBInt(DYE_White)
	;
	
	public static final byte
	DYE_INDEX_Black         =  0,
	DYE_INDEX_Red           =  1,
	DYE_INDEX_Green         =  2,
	DYE_INDEX_Brown         =  3,
	DYE_INDEX_Blue          =  4,
	DYE_INDEX_Purple        =  5,
	DYE_INDEX_Cyan          =  6,
	DYE_INDEX_LightGray     =  7,
	DYE_INDEX_Gray          =  8,
	DYE_INDEX_Pink          =  9,
	DYE_INDEX_Lime          = 10,
	DYE_INDEX_Yellow        = 11,
	DYE_INDEX_LightBlue     = 12,
	DYE_INDEX_Magenta       = 13,
	DYE_INDEX_Orange        = 14,
	DYE_INDEX_White         = 15;
	
	public static final String[] DYE_NAMES                  = {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Gray", "Gray", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White"};
	public static final String[] DYE_OREDICTS               = {"dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite"};
	public static final String[] DYE_OREDICTS_CERAMIC       = {"dyeCeramicBlack", "dyeCeramicRed", "dyeCeramicGreen", "dyeCeramicBrown", "dyeCeramicBlue", "dyeCeramicPurple", "dyeCeramicCyan", "dyeCeramicLightGray", "dyeCeramicGray", "dyeCeramicPink", "dyeCeramicLime", "dyeCeramicYellow", "dyeCeramicLightBlue", "dyeCeramicMagenta", "dyeCeramicOrange", "dyeCeramicWhite"};
	public static final String[] DYE_OREDICTS_MIXABLE       = {"dyeMixableBlack", "dyeMixableRed", "dyeMixableGreen", "dyeMixableBrown", "dyeMixableBlue", "dyeMixablePurple", "dyeMixableCyan", "dyeMixableLightGray", "dyeMixableGray", "dyeMixablePink", "dyeMixableLime", "dyeMixableYellow", "dyeMixableLightBlue", "dyeMixableMagenta", "dyeMixableOrange", "dyeMixableWhite"};
	public static final String[] DYE_OREDICTS_LENS          = {"craftingLensBlack", "craftingLensRed", "craftingLensGreen", "craftingLensBrown", "craftingLensBlue", "craftingLensPurple", "craftingLensCyan", "craftingLensLightGray", "craftingLensGray", "craftingLensPink", "craftingLensLime", "craftingLensYellow", "craftingLensLightBlue", "craftingLensMagenta", "craftingLensOrange", "craftingLensWhite"};
	public static final String[] DYE_OREDICTS_POST          = {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "White"};
	public static final String[] HEXORIUM_MONOLITHS         = {"blockEnergizedHexoriumMonolithBlack", "blockEnergizedHexoriumMonolithRed", "blockEnergizedHexoriumMonolithGreen", "blockEnergizedHexoriumMonolithDarkGray", "blockEnergizedHexoriumMonolithBlue", "blockEnergizedHexoriumMonolithPurple", "blockEnergizedHexoriumMonolithCyan", "blockEnergizedHexoriumMonolithLightGray", "blockEnergizedHexoriumMonolithGray", "blockEnergizedHexoriumMonolithPink", "blockEnergizedHexoriumMonolithLime", "blockEnergizedHexoriumMonolithYellow", "blockEnergizedHexoriumMonolithSkyBlue", "blockEnergizedHexoriumMonolithMagenta", "blockEnergizedHexoriumMonolithOrange", "blockEnergizedHexoriumMonolithWhite", "blockEnergizedHexoriumMonolithTurquoise", "blockEnergizedHexoriumMonolithRainbow"};
	
	public static final short[][] DYES                      = {DYE_Black, DYE_Red, DYE_Green, DYE_Brown, DYE_Blue, DYE_Purple, DYE_Cyan, DYE_LightGray, DYE_Gray, DYE_Pink, DYE_Lime, DYE_Yellow, DYE_LightBlue, DYE_Magenta, DYE_Orange, DYE_White};
	public static final short[][] DYES_INVERTED             = {DYES[15], DYES[14], DYES[13], DYES[12], DYES[11], DYES[10], DYES[ 9], DYES[ 8], DYES[ 7], DYES[ 6], DYES[ 5], DYES[ 4], DYES[ 3], DYES[ 2], DYES[ 1], DYES[ 0]};
	
	public static final int[] DYES_INT                      = {DYE_INT_Black, DYE_INT_Red, DYE_INT_Green, DYE_INT_Brown, DYE_INT_Blue, DYE_INT_Purple, DYE_INT_Cyan, DYE_INT_LightGray, DYE_INT_Gray, DYE_INT_Pink, DYE_INT_Lime, DYE_INT_Yellow, DYE_INT_LightBlue, DYE_INT_Magenta, DYE_INT_Orange, DYE_INT_White};
	public static final int[] DYES_INT_INVERTED             = {DYES_INT[15], DYES_INT[14], DYES_INT[13], DYES_INT[12], DYES_INT[11], DYES_INT[10], DYES_INT[ 9], DYES_INT[ 8], DYES_INT[ 7], DYES_INT[ 6], DYES_INT[ 5], DYES_INT[ 4], DYES_INT[ 3], DYES_INT[ 2], DYES_INT[ 1], DYES_INT[ 0]};
	
	public static final FluidStack[] DYED_C_FOAMS           = new FluidStack[16];
	public static final FluidStack[] DYED_C_FOAMS_OWNED     = new FluidStack[16];
	
	public static final FluidStack[] DYE_FLUIDS_WATER       = new FluidStack[16];
	public static final FluidStack[] DYE_FLUIDS_FLOWER      = new FluidStack[16];
	public static final FluidStack[] DYE_FLUIDS_CHEMICAL    = new FluidStack[16];
	public static final FluidStack[][] DYE_FLUIDCATEGORIES  = {DYE_FLUIDS_WATER, DYE_FLUIDS_FLOWER, DYE_FLUIDS_CHEMICAL};
	
	
	@SuppressWarnings("unchecked")
	public static final ArrayListNoNulls<FluidStack>[] DYE_FLUIDS = new ArrayListNoNulls[] {new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>(), new ArrayListNoNulls<FluidStack>()};
	
	/** Offset for Rendering Text on Surfaces. */
	public static final float PX_OFFSET = 0.005F;
	
	/** Default Size Box. */
	public static final float[] PX_BOX = {0,0,0,1,1,1};
	
	/** Array with length 17 containing the Coordinates of Pixels from 0/16 to 16/16 */
	public static final float[] PX_P = {
		  0.0000F, 0.0625F, 0.1250F, 0.1875F
		, 0.2500F, 0.3125F, 0.3750F, 0.4375F
		, 0.5000F, 0.5625F, 0.6250F, 0.6875F
		, 0.7500F, 0.8125F, 0.8750F, 0.9375F
		, 1.0000F, 1.0625F, 1.1250F, 1.1875F
		, 1.2500F, 1.3125F, 1.3750F, 1.4375F
		, 1.5000F, 1.5625F, 1.6250F, 1.6875F
		, 1.7500F, 1.8125F, 1.8750F, 1.9375F
		, 2.0000F}, PIXELS_POS = PX_P;
	
	/** Array with length 17 containing the Coordinates of Pixels from 16/16 to 0/16 */
	public static final float[] PX_N = {
		  1.0000F, 0.9375F, 0.8750F, 0.8125F
		, 0.7500F, 0.6875F, 0.6250F, 0.5625F
		, 0.5000F, 0.4375F, 0.3750F, 0.3125F
		, 0.2500F, 0.1875F, 0.1250F, 0.0625F
		, 0.0000F,-0.0625F,-0.1250F,-0.1875F
		,-0.2500F,-0.3125F,-0.3750F,-0.4375F
		,-0.5000F,-0.5625F,-0.6250F,-0.6875F
		,-0.7500F,-0.8125F,-0.8750F,-0.9375F
		,-1.0000F}, PIXELS_NEG = PX_N;
	
	/** Different Side Variables for easier comprehension. */
	public static final byte        SIDE_Y_NEG  = 0, SIDE_BOTTOM    = 0, SIDE_DOWN      = 0,
									SIDE_Y_POS  = 1, SIDE_TOP       = 1, SIDE_UP        = 1,
									SIDE_Z_NEG  = 2, SIDE_NORTH     = 2, // Also a Side with a stupidly mirrored Texture
									SIDE_Z_POS  = 3, SIDE_SOUTH     = 3,
									SIDE_X_NEG  = 4, SIDE_WEST      = 4,
									SIDE_X_POS  = 5, SIDE_EAST      = 5, // Also a Side with a stupidly mirrored Texture
									SIDE_ANY    = 6, SIDE_UNKNOWN   = 6, SIDE_INVALID = 6, SIDE_INSIDE = 6, SIDE_UNDEFINED = 6;
	
	/**
	 * [Facing,Side]->Side Mappings for Blocks, which don't face up- and downwards.
	 * 0 = bottom, 1 = top, 2 = left, 3 = front, 4 = right, 5 = back, 6 = undefined.
	 */
	public static final byte[][] FACING_ROTATIONS = {
		{0,1,2,3,4,5,6},
		{0,1,2,3,4,5,6},
		{0,1,3,5,4,2,6},
		{0,1,5,3,2,4,6},
		{0,1,2,4,3,5,6},
		{0,1,4,2,5,3,6},
		{0,1,2,3,4,5,6}
	};
	
	/**
	 * [Facing,Side]->Orientation Mappings for Blocks, which don't face up- and downwards.
	 * 0 = bottom, 1 = top, 2 = left, 3 = front, 4 = right, 5 = back, 6 = undefined.
	 */
	public static final byte[][] FACING_TO_SIDE = {
		{0,1,2,3,4,5,6},
		{0,1,2,3,4,5,6},
		{0,1,5,2,4,3,6},
		{0,1,4,3,5,2,6},
		{0,1,2,4,3,5,6},
		{0,1,3,5,2,4,6},
		{0,1,2,3,4,5,6}
	};

	/** Gives you the Sides, which are not the Front nor the Back of the Facing. */
	public static final byte[][] FACING_SIDES = {{2,3,4,5},{2,3,4,5},{0,1,4,5},{0,1,4,5},{0,1,2,3},{0,1,2,3},{}};
	/** Gives you the Sides, which are the Front and the Back, with the Front being the first one. */
	public static final byte[][] FACING_FRONT_BACK = {{0,1},{1,0},{2,3},{3,2},{4,5},{5,4},{}};
	/** Gives you the Sides, which are the Front and the Back, with the Back being the first one. */
	public static final byte[][] FACING_BACK_FRONT = {{1,0},{0,1},{3,2},{2,3},{5,4},{4,5},{}};

	/** Checks if two Sides are along the same Axis */
	public static final boolean[][] ALONG_AXIS = {
		{T,T,F,F,F,F,F},
		{T,T,F,F,F,F,F},
		{F,F,T,T,F,F,F},
		{F,F,T,T,F,F,F},
		{F,F,F,F,T,T,F},
		{F,F,F,F,T,T,F},
		{F,F,F,F,F,F,T}
	};

	/** Checks if two Sides are along the shifted Axis */
	public static final boolean[][] ALONG_AXIS_1 = {
		{F,F,T,T,F,F,F},
		{F,F,T,T,F,F,F},
		{F,F,F,F,T,T,F},
		{F,F,F,F,T,T,F},
		{T,T,F,F,F,F,F},
		{T,T,F,F,F,F,F},
		{F,F,F,F,F,F,F}
	};

	/** Checks if two Sides are along the double shifted Axis */
	public static final boolean[][] ALONG_AXIS_2 = {
		{F,F,F,F,T,T,F},
		{F,F,F,F,T,T,F},
		{T,T,F,F,F,F,F},
		{T,T,F,F,F,F,F},
		{F,F,T,T,F,F,F},
		{F,F,T,T,F,F,F},
		{F,F,F,F,F,F,F}
	};

	/** Insert Facing and a Connectivity BitMask to see if it is connecting to that Side. Technically this is a simple Bit Operation, but accessing an Array with "FACE_CONNECTED[aSide][aConnections]" just looks nicer than "(aConnections & (1 << aSide) != 0)". */
	public static final boolean[][] FACE_CONNECTED = {
		{F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T,F,T},
		{F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T,F,F,T,T},
		{F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T,F,F,F,F,T,T,T,T},
		{F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T},
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T},
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T},
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T,T}
	};
	
	/** Fast lookup to see how many Connections a Mask has. It is recommended to do either &63 or &127 on the Index depending on how you use it. */
	public static final byte[]              FACE_CONNECTION_COUNT = {0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,2,3,3,4,3,4,4,5,3,4,4,5,4,5,5,6,1,2,2,3,2,3,3,4,2,3,3,4,3,4,4,5,2,3,3,4,3,4,4,5,3,4,4,5,4,5,5,6,2,3,3,4,3,4,4,5,3,4,4,5,4,5,5,6,3,4,4,5,4,5,5,6,4,5,5,6,5,6,6,7};
	
	/** Side Bits for quick reference.*/
	public static final byte                SBIT[] = { 1, 2, 4, 8,16,32,64}, SIDE_BITS[] = SBIT, SBIT_D = 1, SBIT_U = 2, SBIT_L = 4, SBIT_F = 8, SBIT_R = 16, SBIT_B = 32, SBIT_N = 4, SBIT_S = 8, SBIT_W = 16, SBIT_E = 32, SBIT_A = 64, SBIT_I = 64;
	
	/** Those are not representing actual directions! They are for the "FACING_ROTATIONS" Array-Map */
	public static final byte                SIDE_LEFT = 2, SIDE_FRONT = 3, SIDE_RIGHT = 4, SIDE_BACK = 5;
	
	/** Converts Sides to a Top-Bottom-Side Value, this limits the Range to a Number between [0 and 2] */
	public static final byte[]              FACES_TBS = { 0, 1, 2, 2, 2, 2, 2};
	/** Side->Opposite Mappings. */
	public static final byte[]              OPOS = { 1, 0, 3, 2, 5, 4, 6};
	/** Side->Offset Mappings. */
	public static final byte[]              OFFX = { 0, 0, 0, 0,-1,+1, 0},
											OFFY = {-1,+1, 0, 0, 0, 0, 0},
											OFFZ = { 0, 0,-1,+1, 0, 0, 0};
	@Deprecated
	public static final byte[]              OPPOSITES = OPOS, OFFSETS_X = OFFX, OFFSETS_Y = OFFY, OFFSETS_Z = OFFZ;
	/** 3x3x3 Mappings. */
	public static final byte[]              CUBE_3_X = {0, 0, 0, 0, 0,-1,+1, 0, 0,-1,+1, 0, 0,-1,+1,-1,+1,+1,-1,-1,+1,+1,-1,-1,+1,+1,-1},
											CUBE_3_Y = {0,-1,+1, 0, 0, 0, 0,-1,-1,-1,-1,+1,+1,+1,+1, 0, 0, 0, 0,-1,-1,-1,-1,+1,+1,+1,+1},
											CUBE_3_Z = {0, 0, 0,-1,+1, 0, 0,-1,+1, 0, 0,-1,+1, 0, 0,-1,+1,-1,+1,-1,+1,-1,+1,-1,+1,-1,+1},
											CUBE_3[] = {{ 0, 0, 0}, { 0,-1, 0}, { 0,+1, 0}, { 0, 0,-1}, { 0, 0,+1}, {-1, 0, 0}, {+1, 0, 0}, { 0,-1,-1}, { 0,-1,+1}, {-1,-1, 0}, {+1,-1, 0}, { 0,+1,-1}, { 0,+1,+1}, {-1,+1, 0}, {+1,+1, 0}, {-1, 0,-1}, {+1, 0,+1}, {+1, 0,-1}, {-1, 0,+1}, {-1,-1,-1}, {+1,-1,+1}, {+1,-1,-1}, {-1,-1,+1}, {-1,+1,-1}, {+1,+1,+1}, {+1,+1,-1}, {-1,+1,+1}};
	
	/** Side->ForgeDirection Mappings. */
	public static final ForgeDirection[]    FORGE_DIR = {ForgeDirection.DOWN, ForgeDirection.UP, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.WEST, ForgeDirection.EAST, ForgeDirection.UNKNOWN};
	/** Side->Opposite Mappings with ForgeDirection. */
	public static final ForgeDirection[]    FORGE_DIR_OPPOSITES = {ForgeDirection.UP, ForgeDirection.DOWN, ForgeDirection.SOUTH, ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.WEST, ForgeDirection.UNKNOWN};
	
	/** Compass alike Array for the proper ordering of North, East, South and West. */
	public static final byte[]              COMPASS_DIRECTIONS      = {SIDE_NORTH, SIDE_EAST, SIDE_SOUTH, SIDE_WEST};
	/** Side -> Compass Direction. Defaults to North if wrong value. */
	public static final byte[]              COMPASS_FROM_SIDE       = { 0, 0, 0, 2, 3, 1, 0};
	
	/** Used for Meta => Side */
	public static final byte[]              VALIDATE                = { 0, 1, 2, 3, 4, 5, 0, 0, 0, 1, 2, 3, 4, 5, 0, 0},
											VALIDATE_VERTICAL       = { 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
											VALIDATE_HORIZONTAL     = { 3, 3, 2, 3, 4, 5, 3, 3, 3, 3, 2, 3, 4, 5, 3, 3};
	
	/** An Array containing all Sides which follow the Condition, in order to iterate over them for example. */
	public static final byte[]              ALL_SIDES                   =  {0,1,2,3,4,5,6},
											ALL_SIDES_MIDDLE            =  {6,0,1,2,3,4,5},
											ALL_SIDES_MIDDLE_UP         =  {6,1,2,3,4,5,0},
											ALL_SIDES_MIDDLE_DOWN       =  {6,0,2,3,4,5,1},
											ALL_SIDES_VALID             =  {0,1,2,3,4,5  },
											ALL_SIDES_VALID_ORDER[]     = {{0,1,2,3,4,5  },{1,2,3,4,5,0  },{2,3,4,5,0,1  },{3,4,5,0,1,2  },{4,5,0,1,2,3  },{5,0,1,2,3,4  },{0,1,2,3,4,5  }},
											ALL_SIDES_VALID_FIRST[]     = {{0,1,2,3,4,5  },{1,0,2,3,4,5  },{2,0,1,3,4,5  },{3,0,1,2,4,5  },{4,0,1,2,3,5  },{5,0,1,2,3,4  },{0,1,2,3,4,5  }},
											ALL_SIDES_VALID_ONLY[]      = {{0            },{1            },{2            },{3            },{4            },{5            },{0,1,2,3,4,5  }},
											ALL_SIDES_VALID_BUT[]       = {{  1,2,3,4,5  },{0  ,2,3,4,5  },{0,1  ,3,4,5  },{0,1,2  ,4,5  },{0,1,2,3  ,5  },{0,1,2,3,4    },{0,1,2,3,4,5  }},
											ALL_SIDES_VALID_BUT_AXIS[]  = {{    2,3,4,5  },{    2,3,4,5  },{0,1    ,4,5  },{0,1    ,4,5  },{0,1,2,3      },{0,1,2,3      },{0,1,2,3,4,5  }},
											ALL_SIDES_THIS_AND_ANY[]    = {{0          ,6},{1          ,6},{2          ,6},{3          ,6},{4          ,6},{5          ,6},{0,1,2,3,4,5,6}},
											ALL_SIDES_VERTICAL          =  {0,1},
											ALL_SIDES_BOTTOM            =  {0},
											ALL_SIDES_TOP               =  {1},
											ALL_SIDES_HORIZONTAL        =  {2,3,4,5},
											ALL_SIDES_HORIZONTAL_UP     =  {2,3,4,5,1},
											ALL_SIDES_HORIZONTAL_DOWN   =  {2,3,4,5,0},
											ALL_SIDES_BUT_TOP           =  {0,2,3,4,5},
											ALL_SIDES_BUT_BOTTOM        =  {1,2,3,4,5},
											ALL_SIDES_X                 =  {4,5},
											ALL_SIDES_Y                 =  {0,1},
											ALL_SIDES_Z                 =  {2,3};
	
	/** For Facing Checks. */
	public static final boolean[]           SIDES_BOTTOM            = {T,F,F,F,F,F,F},
											SIDES_TOP               = {F,T,F,F,F,F,F},
											SIDES_LEFT              = {F,F,T,F,F,F,F},
											SIDES_FRONT             = {F,F,F,T,F,F,F},
											SIDES_RIGHT             = {F,F,F,F,T,F,F},
											SIDES_BACK              = {F,F,F,F,F,T,F},
											SIDES_INVALID           = {F,F,F,F,F,F,T},
											SIDES_VALID             = {T,T,T,T,T,T,F},
											SIDES_ALL               = {T,T,T,T,T,T,T},
											SIDES_NONE              = {F,F,F,F,F,F,F},
											SIDES_LEFT_RIGHT        = {F,F,T,F,T,F,F},
											SIDES_FRONT_BACK        = {F,F,F,T,F,T,F},
											SIDES_AXIS_X            = {F,F,F,F,T,T,F},
											SIDES_AXIS_Y            = {T,T,F,F,F,F,F},
											SIDES_AXIS_Z            = {F,F,T,T,F,F,F},
											SIDES_COMPASS           = {F,F,T,T,T,T,F},
											SIDES_VERTICAL          = {T,T,F,F,F,F,F},
											SIDES_HORIZONTAL        = {F,F,T,T,T,T,F},
											SIDES_TOP_HORIZONTAL    = {F,T,T,T,T,T,F},
											SIDES_BOTTOM_HORIZONTAL = {T,F,T,T,T,T,F},
											SIDES_ITEM_RENDER       = {T,T,T,T,T,T,F};
	
	/** For Facing Checks. */
	public static final boolean[][]
	SIDES_ANY_BUT = {
		{F,T,T,T,T,T,F},
		{T,F,T,T,T,T,F},
		{T,T,F,T,T,T,F},
		{T,T,T,F,T,T,F},
		{T,T,T,T,F,T,F},
		{T,T,T,T,T,F,F},
		{T,T,T,T,T,T,F}
	},
	SIDES_THIS = {
		{T,F,F,F,F,F,F},
		{F,T,F,F,F,F,F},
		{F,F,T,F,F,F,F},
		{F,F,F,T,F,F,F},
		{F,F,F,F,T,F,F},
		{F,F,F,F,F,T,F},
		{F,F,F,F,F,F,F}
	},
	SIDES_EQUAL = {
		{T,F,F,F,F,F,T},
		{F,T,F,F,F,F,T},
		{F,F,T,F,F,F,T},
		{F,F,F,T,F,F,T},
		{F,F,F,F,T,F,T},
		{F,F,F,F,F,T,T},
		{T,T,T,T,T,T,T}
	},
	SIDES_UNEQUAL = {
		{F,T,T,T,T,T,F},
		{T,F,T,T,T,T,F},
		{T,T,F,T,T,T,F},
		{T,T,T,F,T,T,F},
		{T,T,T,T,F,T,F},
		{T,T,T,T,T,F,F},
		{F,F,F,F,F,F,F}
	},
	AXIS_XYZ = {
		SIDES_NONE,
		SIDES_AXIS_X,
		SIDES_AXIS_Y,
		SIDES_AXIS_Z
	};
	
	/** Pillar Stuff for more understandable references. */
	public static final byte PILLAR_X = 4, PILLAR_Y = 0, PILLAR_Z = 8, PILLAR_BITS = 12, PILLAR_DATA = 3, PILLAR_RENDER = 31
	, PILLARS_X[] = {4,5,6,7}, PILLARS_Y[] = {0,1,2,3}, PILLARS_Z[] = {8,9,10,11}
	, PILLAR_BITS_SIDE[] = {0,0,8,8,4,4,0}
	, PILLAR_DATA_SIDE[][] = {
	  {0,0,8,8,4,4,0}, {1,1,9,9,5,5,1}, {2,2,10,10,6,6,2}, {3,3,11,11,7,7,3}
	, {0,0,8,8,4,4,0}, {1,1,9,9,5,5,1}, {2,2,10,10,6,6,2}, {3,3,11,11,7,7,3}
	, {0,0,8,8,4,4,0}, {1,1,9,9,5,5,1}, {2,2,10,10,6,6,2}, {3,3,11,11,7,7,3}
	// Pillar Blocks that are fully covered in "Bark" (if they were Logs)
	, {12,12,12,12,12,12,12}
	, {13,13,13,13,13,13,13}
	, {14,14,14,14,14,14,14}
	, {15,15,15,15,15,15,15}
	};
	/** Pillar Axis Stuff for more understandable references. */
	public static final boolean[][] PILLAR_TO_AXIS = {
		SIDES_AXIS_Y, SIDES_AXIS_Y, SIDES_AXIS_Y, SIDES_AXIS_Y,
		SIDES_AXIS_X, SIDES_AXIS_X, SIDES_AXIS_X, SIDES_AXIS_X,
		SIDES_AXIS_Z, SIDES_AXIS_Z, SIDES_AXIS_Z, SIDES_AXIS_Z,
		SIDES_NONE  , SIDES_NONE  , SIDES_NONE  , SIDES_NONE  ,
	};
	
	/** Used for Networking Covers, for the most Part. */
	public static final boolean[] TRUE_6 = {T,T,T,T,T,T};
	
	/** To Scan Coordinates in a somewhat "close stuff gets scanned first" order. */
	public static final int[]
	  SCAN_NEG_0 = {0}
	, SCAN_NEG_1 = {0, -1, +1}
	, SCAN_NEG_2 = {0, -1, +1, -2, +2}
	, SCAN_NEG_3 = {0, -1, +1, -2, +2, -3, +3}
	, SCAN_NEG_4 = {0, -1, +1, -2, +2, -3, +3, -4, +4}
	, SCAN_NEG_5 = {0, -1, +1, -2, +2, -3, +3, -4, +4, -5, +5}
	, SCAN_NEG_6 = {0, -1, +1, -2, +2, -3, +3, -4, +4, -5, +5, -6, +6}
	, SCAN_NEG_7 = {0, -1, +1, -2, +2, -3, +3, -4, +4, -5, +5, -6, +6, -7, +7}
	, SCAN_NEG_8 = {0, -1, +1, -2, +2, -3, +3, -4, +4, -5, +5, -6, +6, -7, +7, -8, +8}
	, SCAN_NEG_9 = {0, -1, +1, -2, +2, -3, +3, -4, +4, -5, +5, -6, +6, -7, +7, -8, +8, -9, +9}

	, SCAN_POS_0 = {0}
	, SCAN_POS_1 = {0, +1, -1}
	, SCAN_POS_2 = {0, +1, -1, +2, -2}
	, SCAN_POS_3 = {0, +1, -1, +2, -2, +3, -3}
	, SCAN_POS_4 = {0, +1, -1, +2, -2, +3, -3, +4, -4}
	, SCAN_POS_5 = {0, +1, -1, +2, -2, +3, -3, +4, -4, +5, -5}
	, SCAN_POS_6 = {0, +1, -1, +2, -2, +3, -3, +4, -4, +5, -5, +6, -6}
	, SCAN_POS_7 = {0, +1, -1, +2, -2, +3, -3, +4, -4, +5, -5, +6, -6, +7, -7}
	, SCAN_POS_8 = {0, +1, -1, +2, -2, +3, -3, +4, -4, +5, -5, +6, -6, +7, -7, +8, -8}
	, SCAN_POS_9 = {0, +1, -1, +2, -2, +3, -3, +4, -4, +5, -5, +6, -6, +7, -7, +8, -8, +9, -9}

	, SCANS_POS[] = {SCAN_POS_0, SCAN_POS_1, SCAN_POS_2, SCAN_POS_3, SCAN_POS_4, SCAN_POS_5, SCAN_POS_6, SCAN_POS_7, SCAN_POS_8, SCAN_POS_9}
	, SCANS_NEG[] = {SCAN_NEG_0, SCAN_NEG_1, SCAN_NEG_2, SCAN_NEG_3, SCAN_NEG_4, SCAN_NEG_5, SCAN_NEG_6, SCAN_NEG_7, SCAN_NEG_8, SCAN_NEG_9}
	;

	/** Zero-Length Array to save on Memory. */ public static final Object                  [] ZL                   = new Object[0], ZL_OBJECT = ZL;
	/** Zero-Length Array to save on Memory. */ public static final byte                    [] ZL_BYTE              = new byte[0];
	/** Zero-Length Array to save on Memory. */ public static final short                   [] ZL_SHORT             = new short[0];
	/** Zero-Length Array to save on Memory. */ public static final int                     [] ZL_INTEGER           = new int[0];
	/** Zero-Length Array to save on Memory. */ public static final long                    [] ZL_LONG              = new long[0];
	/** Zero-Length Array to save on Memory. */ public static final float                   [] ZL_FLOAT             = new float[0];
	/** Zero-Length Array to save on Memory. */ public static final double                  [] ZL_DOUBLE            = new double[0];
	/** Zero-Length Array to save on Memory. */ public static final String                  [] ZL_STRING            = new String[0];
	/** Zero-Length Array to save on Memory. */ public static final ItemStack               [] ZL_IS                = new ItemStack[0], ZL_ITEMSTACK = ZL_IS;
	/** Zero-Length Array to save on Memory. */ public static final ItemStackContainer      [] ZL_ISC               = new ItemStackContainer[0];
	/** Zero-Length Array to save on Memory. */ public static final FluidStack              [] ZL_FS                = new FluidStack[0], ZL_FLUIDSTACK = ZL_FS;
	/** Zero-Length Array to save on Memory. */ public static final FluidTankGT             [] ZL_FT                = new FluidTankGT[0], ZL_FLUIDTANKGT = ZL_FT;
	/** Zero-Length Array to save on Memory. */ public static final TagData                 [] ZL_TD                = new TagData[0], ZL_TAGDATA = ZL_TD;
	/** Zero-Length Array to save on Memory. */ public static final OreDictMaterial         [] ZL_MT                = new OreDictMaterial[0], ZL_MATERIAL = ZL_MT;
	/** Zero-Length Array to save on Memory. */ public static final OreDictMaterialStack    [] ZL_MS                = new OreDictMaterialStack[0], ZL_MATERIALSTACK = ZL_MS;
	/** Zero-Length Array to save on Memory. */ public static final Enchantment             [] ZL_ENCHANTMENT       = new Enchantment[0];
	/** Zero-Length Array to save on Memory. */ public static final FluidTank               [] ZL_FLUIDTANK         = new FluidTank[0];
	/** Zero-Length Array to save on Memory. */ public static final IFluidTank              [] ZL_IFLUIDTANK        = new IFluidTank[0];
	/** Zero-Length Array to save on Memory. */ public static final FluidTankInfo           [] ZL_FLUIDTANKINFO     = new FluidTankInfo[0], L1_FLUIDTANKINFO_DUMMY = new FluidTankInfo[] {new FluidTankInfo(null, Integer.MAX_VALUE)};
	/** Zero-Length Array to save on Memory. */ public static final OreDictItemData         [] ZL_OREDICTITEMDATA   = new OreDictItemData[0];
	/** Zero-Length Array to save on Memory. */ public static final OreDictPrefix           [] ZL_OREDICTPREFIX     = new OreDictPrefix[0];
	/** Zero-Length Array to save on Memory. */ public static final ObjectStack<?>          [] ZL_OBJECTSTACK       = new ObjectStack[0];
	/** Zero-Length Array to save on Memory. */ public static final ForgeDirection          [] ZL_FORGEDIRECTION    = new ForgeDirection[0];
	/** Zero-Length Array to save on Memory. */ public static final ChunkCoordinates        [] ZL_COORDS            = new ChunkCoordinates[0];
	/** Zero-Length Array to save on Memory. */ public static final Recipe                  [] ZL_RECIPE            = new Recipe[0];
	/** Zero-Length Array to save on Memory. */ public static final IIconContainer          [] ZL_IICONCONTAINER    = new IIconContainer[0], L6_IICONCONTAINER  = new IIconContainer[6], L1L6_IICONCONTAINER[] = new IIconContainer[][] {L6_IICONCONTAINER};

	/** This way it is possible to have a Call Hierarchy of NullPointers in ItemStack based Functions, and also because most of the time I don't know what kind of Data Type the "null" stands for, when there are shitloads of Parameters for a Function */
	public static final ItemStack NI = null;

	/** This way it is possible to have a Call Hierarchy of NullPointers in FluidStack based Functions, and also because most of the time I don't know what kind of Data Type the "null" stands for, when there are shitloads of Parameters for a Function */
	public static final FluidStack NF = null;

	/** This way it is possible to have a Call Hierarchy of NullPointers in Block based Functions, and also because most of the time I don't know what kind of Data Type the "null" stands for, when there are shitloads of Parameters for a Function */
	public static final Block NB = Blocks.air;
	
	/** The Logs: Debug, Output, Error, OreDict and Material List. */
	public static PrintStream DEB = new LogBuffer(), OUT = new LogBuffer(), ERR = new LogBuffer(), ORD = new LogBuffer(), MAT_LOG = null;
	
	/** States of Matter */
	public static final byte STATE_SOLID = 0, STATE_LIQUID = 1, STATE_GASEOUS = 2, STATE_PLASMA = 3;
	
	/** The weight of Air at Atmospheric Pressure per Cubic Centimetre. */
	public static final double WEIGHT_AIR_G_PER_CUBIC_CENTIMETER = 0.0012;
	/** The weight of Air at Atmospheric Pressure per Cubic Meter. */
	public static final double WEIGHT_AIR_KG_PER_CUBIC_METER = 1.2;
	/** The weight of Air at Atmospheric Pressure per Material Unit. */
	public static final double WEIGHT_AIR_KG_PER_UNIT = WEIGHT_AIR_KG_PER_CUBIC_METER / 9;
	
	/** Not really Constants, but they set using the Config and therefore should be constant. */
	public static boolean D1 = F, D2 = F, D3 = F, ALWAYS_TRUE = T, ALWAYS_FALSE = F, CLIENT_BLOCKUPDATE_SOUNDS = F, NEI = F, TOOL_SOUNDS = T, EMIT_EU_AS_RF = F, DISABLE_GT6_CRAFTING_RECIPES = F, ENABLE_ADDING_IC2_MACERATOR_RECIPES = T, DISABLE_ALL_IC2_MACERATOR_RECIPES = F, ENABLE_ADDING_IC2_EXTRACTOR_RECIPES = T, DISABLE_ALL_IC2_EXTRACTOR_RECIPES = F, ENABLE_ADDING_IC2_COMPRESSOR_RECIPES = T, DISABLE_ALL_IC2_COMPRESSOR_RECIPES = F, ENABLE_ADDING_IC2_OREWASHER_RECIPES = T, DISABLE_ALL_IC2_OREWASHER_RECIPES = F, ENABLE_ADDING_IC2_CENTRIFUGE_RECIPES = T, DISABLE_ALL_IC2_CENTRIFUGE_RECIPES = F, FAST_LEAF_DECAY = T, FORCE_GRAVEL_NO_FLINT = F, NERFED_WOOD = T, FOOD_OVERDOSE_DEATH = T, NUTRITION_SYSTEM = T, OBSTRUCTION_CHECKS = T, OWNERSHIP_RESET = F, SPAWN_ZONE_MOB_PROTECTION = T, SPAWN_NO_BATS = T, SPAWN_HOSTILES_ONLY_IN_DARKNESS = T, CONSTANT_ENERGY = T, RAIN_EXPLOSIONS = F, WATER_EXPLOSIONS = F, THUNDER_EXPLOSIONS = F, FIRE_EXPLOSIONS = F, OVERCHARGE_EXPLOSIONS = F, FIRE_BREAKING = F, RAIN_BREAKING = F, WATER_BREAKING = F, THUNDER_BREAKING = F, OVERCHARGE_BREAKING = F, SHOW_MICROBLOCKS = F, SHOW_CHEM_FORMULAS = T, SHOW_INTERNAL_NAMES = F, SHOW_HIDDEN_MATERIALS = F, SHOW_HIDDEN_PREFIXES = F, SHOW_ORE_BLOCK_PREFIXES = F, SHOW_HIDDEN_ITEMS = F, DRINKS_ALWAYS_DRINKABLE = F, HUNGER_BY_INVENTORY_WEIGHT = F, INVENTORY_UNIFICATION = T, XP_ORB_COMBINING = T, ADVENTURE_MODE_KIT = F, SURVIVAL_INTO_ADVENTURE_MODE = F, ZOMBIES_DIG_WITH_TOOLS = F, ZOMBIES_DIG_TILEENTITIES = F, ZOMBIES_HOLD_PICKAXES = T, ZOMBIES_HOLD_TNT = T, ZOMBIES_IGNITE_HELD_TNT = T, DISPLAY_TEMP_TOOLTIP = T, GENERATE_STONE = T, GENERATE_STREETS = F, GENERATE_NEXUS = F, GENERATE_TESTING = F, GENERATE_BEACON = F, GENERATE_BIOMES = F;
	/** Date based Shenanigans */
	@SuppressWarnings("deprecation")
	public static boolean
	APRIL_FOOLS      = (new Date().getMonth() ==  3 && new Date().getDate() <=  2),
	WOODMANS_BDAY    = (new Date().getMonth() ==  5 && new Date().getDate() >= 21),
	XMAS_IN_JULY     = (new Date().getMonth() ==  6 && new Date().getDate() >= 23),
	XMAS_IN_DECEMBER = (new Date().getMonth() == 11 && new Date().getDate() >=  6);
	/** This means that Client or Server specific Base Files are definitely existing and loaded! Not if the World is actually client side or server side! */
	public static boolean CODE_UNCHECKED = T, CODE_CLIENT = F, CODE_SERVER = F;
	
	/** Not really Constants, but they set using the Config and therefore should be constant. */
	public static double HARDNESS_MULTIPLIER_SAND = 1.0, HARDNESS_MULTIPLIER_ROCK = 1.0, HARDNESS_MULTIPLIER_ORES = 1.0;
	/** Those are the values derived directly by the Configuration File. DO NOT USE THEM, USE THE VALUES ABOVE INSTEAD!!! */
	public static double CONFIG_HARDNESS_MULTIPLIER_SAND = 1.0, CONFIG_HARDNESS_MULTIPLIER_ROCK = 1.0, CONFIG_HARDNESS_MULTIPLIER_ORES = 1.0;
	/** Tree Growth Time Multiplier. */
	public static int TREE_GROWTH_TIME = 1;
	/** TFC Damage Multiplier. */
	public static int TFC_DAMAGE_MULTIPLIER = 80;
	/** Entity Cramming */
	public static int ENTITY_CRAMMING = 3;
	/** Item Related */
	public static int ITEM_DESPAWN_TIME = 6000;
	
	/** Gets set when the Player dies. Only works Client Side and gets lost when the Client restarts, but not when the Client just relogs. */
	public static ChunkCoordinates LAST_DEATH_OF_THE_PLAYER = null;
	
	/** Gets set when a TileEntity gets broken, in order to be able to access it for Drops, even though it just got deleted. */
	public static ThreadLocal<TileEntity> LAST_BROKEN_TILEENTITY = new ThreadLocal<>();
	
	/** If you have to give something a World Parameter but there is no World... (Dummy World) */
	public static DummyWorld DW;
	/** Dimension Types that I use as parameter for my WorldGenerators, aside from the Vanilla Dimension IDs none of these IDs is accurate as they are just the Defaults of their Respective Mods! */
	public static final int DIM_UNKNOWN = Integer.MAX_VALUE
	, DIM_OVERWORLD = 0
	, DIM_NETHER = -1
	, DIM_END = 1
	, DIM_ENVM = -2
	, DIM_A97 = -6 // Collides with Aether by default
	, DIM_CW2_AquaCavern = -32
	, DIM_CW2_Caveland = -33
	, DIM_CW2_Cavenia = -34
	, DIM_CW2_Cavern = -31
	, DIM_CW2_Caveworld = -30
	, DIM_MOON = 2
	, DIM_MARS = 3
	, DIM_ASTEROIDS = 4
	, DIM_PLANETS = 5
	, DIM_AETHER = 6
	, DIM_TWILIGHT = 7
	, DIM_ATUM = 17
	, DIM_BETWEENLANDS = 20
	, DIM_FROZEN_HEARTH = 21
	, DIM_SOUL_FOREST = 22
	, DIM_CANDY = 23
	, DIM_EREBUS = 66
	, DIM_ALFHEIM = 105
	, DIM_DEEPDARK = -100
	, DIM_LASTMILLENIUM = -112
	, DIM_TROPICS = -127
	;
	
	/** Lists of all the active World generation Features by Dimension Type, these are getting initialised in Load! */
	@SuppressWarnings("unchecked")
	public static final List<WorldgenObject>
	  GEN_OVERWORLD         = new ArrayListNoNulls<>()
	, GEN_GT                = new ArrayListNoNulls<>()
	, GEN_PFAA              = new ArrayListNoNulls<>()
	, GEN_TFC               = new ArrayListNoNulls<>()
	, GEN_NETHER            = new ArrayListNoNulls<>()
	, GEN_AETHER            = new ArrayListNoNulls<>()
	, GEN_END               = new ArrayListNoNulls<>()
	, GEN_MOON              = new ArrayListNoNulls<>()
	, GEN_MARS              = new ArrayListNoNulls<>()
	, GEN_PLANETS           = new ArrayListNoNulls<>()
	, GEN_ASTEROIDS         = new ArrayListNoNulls<>()
	, GEN_TWILIGHT          = new ArrayListNoNulls<>()
	, GEN_EREBUS            = new ArrayListNoNulls<>()
	, GEN_BETWEENLANDS      = new ArrayListNoNulls<>()
	, GEN_ATUM              = new ArrayListNoNulls<>()
	, GEN_DEEPDARK          = new ArrayListNoNulls<>()
	, GEN_ENVM              = new ArrayListNoNulls<>()
	, GEN_ENVM_GT           = new ArrayListNoNulls<>()
	, GEN_A97               = new ArrayListNoNulls<>()
	, GEN_A97_GT            = new ArrayListNoNulls<>()
	, GEN_CW2_AquaCavern    = new ArrayListNoNulls<>()
	, GEN_CW2_AquaCavern_GT = new ArrayListNoNulls<>()
	, GEN_CW2_Caveland      = new ArrayListNoNulls<>()
	, GEN_CW2_Caveland_GT   = new ArrayListNoNulls<>()
	, GEN_CW2_Cavenia       = new ArrayListNoNulls<>()
	, GEN_CW2_Cavenia_GT    = new ArrayListNoNulls<>()
	, GEN_CW2_Cavern        = new ArrayListNoNulls<>()
	, GEN_CW2_Cavern_GT     = new ArrayListNoNulls<>()
	, GEN_CW2_Caveworld     = new ArrayListNoNulls<>()
	, GEN_CW2_Caveworld_GT  = new ArrayListNoNulls<>()
	, GEN_ALFHEIM           = new ArrayListNoNulls<>()
	, GEN_TROPICS           = new ArrayListNoNulls<>()
	, GEN_CANDY             = new ArrayListNoNulls<>()
	, GEN_GEMS[]            = new List[] {GEN_OVERWORLD        , GEN_PFAA, GEN_ENVM,              GEN_A97,             GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_MARS, GEN_AETHER}
	, GEN_FLOOR[]           = new List[] {GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_ENVM_GT, GEN_A97, GEN_A97_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_MARS, GEN_DEEPDARK, GEN_TFC, GEN_NETHER, GEN_MOON, GEN_TWILIGHT, GEN_ALFHEIM, GEN_TROPICS, GEN_CANDY, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT}
	, GEN_ALL[]             = new List[] {GEN_OVERWORLD, GEN_GT, GEN_PFAA, GEN_ENVM, GEN_ENVM_GT, GEN_A97, GEN_A97_GT, GEN_EREBUS, GEN_BETWEENLANDS, GEN_ATUM, GEN_MARS, GEN_DEEPDARK, GEN_TFC, GEN_NETHER, GEN_MOON, GEN_TWILIGHT, GEN_ALFHEIM, GEN_TROPICS, GEN_CANDY, GEN_CW2_AquaCavern, GEN_CW2_AquaCavern_GT, GEN_CW2_Caveland, GEN_CW2_Caveland_GT, GEN_CW2_Cavenia, GEN_CW2_Cavenia_GT, GEN_CW2_Cavern, GEN_CW2_Cavern_GT, GEN_CW2_Caveworld, GEN_CW2_Caveworld_GT, GEN_AETHER, GEN_END, GEN_PLANETS, GEN_ASTEROIDS}
	;
	
	/** Lists of all the active Large Ore Vein generation by Dimension Type, these are getting initialised in Load! */
	@SuppressWarnings("unchecked")
	public static final List<WorldgenObject>
	  ORE_OVERWORLD         = new ArrayListNoNulls<>()
	, ORE_PFAA              = new ArrayListNoNulls<>()
	, ORE_TFC               = new ArrayListNoNulls<>()
	, ORE_NETHER            = new ArrayListNoNulls<>()
	, ORE_AETHER            = new ArrayListNoNulls<>()
	, ORE_END               = new ArrayListNoNulls<>()
	, ORE_MOON              = new ArrayListNoNulls<>()
	, ORE_MARS              = new ArrayListNoNulls<>()
	, ORE_PLANETS           = new ArrayListNoNulls<>()
	, ORE_ASTEROIDS         = new ArrayListNoNulls<>()
	, ORE_TWILIGHT          = new ArrayListNoNulls<>()
	, ORE_EREBUS            = new ArrayListNoNulls<>()
	, ORE_BETWEENLANDS      = new ArrayListNoNulls<>()
	, ORE_ATUM              = new ArrayListNoNulls<>()
	, ORE_DEEPDARK          = new ArrayListNoNulls<>()
	, ORE_ENVM              = new ArrayListNoNulls<>()
	, ORE_A97               = new ArrayListNoNulls<>()
	, ORE_CW2_AquaCavern    = new ArrayListNoNulls<>()
	, ORE_CW2_Caveland      = new ArrayListNoNulls<>()
	, ORE_CW2_Cavenia       = new ArrayListNoNulls<>()
	, ORE_CW2_Cavern        = new ArrayListNoNulls<>()
	, ORE_CW2_Caveworld     = new ArrayListNoNulls<>()
	, ORE_ALFHEIM           = new ArrayListNoNulls<>()
	, ORE_TROPICS           = new ArrayListNoNulls<>()
	, ORE_CANDY             = new ArrayListNoNulls<>()
	, ORE_FLOOR[]           = new List[] {ORE_OVERWORLD, ORE_PFAA, ORE_ENVM, ORE_A97, ORE_TFC, ORE_NETHER, ORE_MOON, ORE_MARS, ORE_TWILIGHT, ORE_EREBUS, ORE_BETWEENLANDS, ORE_ATUM, ORE_ALFHEIM, ORE_DEEPDARK, ORE_TROPICS, ORE_CANDY, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld}
	, ORE_ALL[]             = new List[] {ORE_OVERWORLD, ORE_PFAA, ORE_ENVM, ORE_A97, ORE_TFC, ORE_NETHER, ORE_MOON, ORE_MARS, ORE_TWILIGHT, ORE_EREBUS, ORE_BETWEENLANDS, ORE_ATUM, ORE_ALFHEIM, ORE_DEEPDARK, ORE_TROPICS, ORE_CANDY, ORE_CW2_AquaCavern, ORE_CW2_Caveland, ORE_CW2_Cavenia, ORE_CW2_Cavern, ORE_CW2_Caveworld, ORE_AETHER, ORE_END, ORE_PLANETS, ORE_ASTEROIDS}
	;
	
	/** For Internal Usage. Even though after 2 years I still don't use this one... */
	public static INetworkHandler NW_GT;

	/** For using the API internal Network Functionality for the already existing Packets. */
	public static INetworkHandler NW_API, NW_AP2;

	/** Used to register IC2 Stuff, this Object might be null if IC2 isn't installed. */
	public static ICompatIC2        COMPAT_IC2;
	/** Used to register IC2 Stuff, this Object might be null if IC2 isn't installed. */
	public static ICompatIC2EUItem  COMPAT_EU_ITEM;
	/** Used to register Aspects to ThaumCraft, this Object might be null if ThaumCraft isn't installed. */
	public static ICompatTC         COMPAT_TC;
	/** Used to register BuildCraft Stuff, this Object might be null if BuildCraft isn't installed. */
	public static ICompatBC         COMPAT_BC;
	/** Used to register ComputerCraft Stuff, this Object might be null if ComputerCraft isn't installed. */
	public static ICompatCC         COMPAT_CC;
	/** Used to register OpenComputers Stuff, this Object might be null if OpenComputers isn't installed. */
//  public static ICompatOC         COMPAT_OC;
	/** Used to register Forestry Stuff, this Object might be null if Forestry isn't installed. */
	public static ICompatFR         COMPAT_FR;
	/** Used to register GalactiCraft Stuff, this Object might be null if GalactiCraft isn't installed. */
	public static ICompatGC         COMPAT_GC;

	/** Date and Time of when the Game launched. */
	public static final String DATE_OF_GAME_START = UT.Code.dateAndTime();

	/** A Set of different Tool Names. */
	public static final String
	  TOOL_LOCALISER_PREFIX = "gt.lang.tool.name."
	, TOOL_TOOLTIP_PREFIX   = "gt.lang.tool.tooltip."
	, TOOL_rotator          = "rotator"
	, TOOL_igniter          = "igniter"
	, TOOL_extinguisher     = "extinguisher"
	, TOOL_whacker          = "whacker"
	, TOOL_wrench           = "wrench"
	, TOOL_monkeywrench     = "monkeywrench"
	, TOOL_crowbar          = "crowbar"
	, TOOL_pincers          = "pincers"
	, TOOL_axe              = "axe"
	, TOOL_pickaxe          = "pickaxe"
	, TOOL_knife            = "knife"
	, TOOL_sword            = "sword"
	, TOOL_shovel           = "shovel"
	, TOOL_hoe              = "hoe"
	, TOOL_grafter          = "grafter"
	, TOOL_saw              = "saw"
	, TOOL_file             = "file"
	, TOOL_hammer           = "hammer"
	, TOOL_plow             = "plow"
	, TOOL_plunger          = "plunger"
	, TOOL_scoop            = "scoop"
	, TOOL_shears           = "shears"
	, TOOL_scissors         = "scissors"
	, TOOL_screwdriver      = "screwdriver"
	, TOOL_drill            = "drill"
	, TOOL_mixer            = "mixer"
	, TOOL_chisel           = "chisel"
	, TOOL_sense            = "sense"
	, TOOL_scythe           = "scythe"
	, TOOL_softhammer       = "softhammer"
	, TOOL_cutter           = "cutter"
	, TOOL_plasmatorch      = "plasmatorch"
	, TOOL_solderingtool    = "solderingtool"
	, TOOL_solderingmetal   = "solderingmetal"
	, TOOL_thermometer      = "thermometer"
	, TOOL_magnifyingglass  = "magnifyingglass"
	, TOOL_geigercounter    = "geigercounter"
	, TOOL_prospector       = "prospector"
	, TOOL_ducttape         = "ducttape"
	;

	static {
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_rotator         , "Rotation Tool");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_igniter         , "Igniting Tool");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_extinguisher    , "Extinguishing Tool");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_whacker         , "Whacking Tool");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_wrench          , "Wrench");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_monkeywrench    , "Monkey Wrench");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_crowbar         , "Crowbar");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_pincers         , "Pincers");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_axe             , "Axe");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_pickaxe         , "Pickaxe");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_sword           , "Sword");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_knife           , "Knife");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_shovel          , "Shovel");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_hoe             , "Hoe");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_grafter         , "Grafter");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_saw             , "Saw");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_file            , "File");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_hammer          , "Hammer");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_plow            , "Plow");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_plunger         , "Plunger");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_scoop           , "Scoop");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_shears          , "Shears");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_scissors        , "Scissors");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_screwdriver     , "Screwdriver");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_drill           , "Drill");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_chisel          , "Chisel");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_sense           , "Sense");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_scythe          , "Scythe");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_softhammer      , "Soft Hammer");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_cutter          , "Cutter");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_plasmatorch     , "Plasma Torch");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_solderingtool   , "Soldering Tool");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_solderingmetal  , "Soldering Metal");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_thermometer     , "Thermometer");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_magnifyingglass , "Magnifying Glass");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_geigercounter   , "Geiger Counter");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_prospector      , "Prospector");
		LH.add(TOOL_LOCALISER_PREFIX + TOOL_ducttape        , "Duct Tape");

		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_rotator           , "Rotating things");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_igniter           , "Igniting things");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_extinguisher      , "Extinguishing things");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_whacker           , "Whacking things");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_wrench            , "Rotates Blocks on Rightclick, dismantles IC2 Blocks");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_monkeywrench      , "A special Wrench for secondary Facings and Pipes");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_magnifyingglass   , "Used to investigate the Scene");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_crowbar           , "Dismounts Covers and Rotates Rails");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_pincers           , "Used to grab things without touching them");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_axe               , "Can remove Bark from Logs");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_pickaxe           , "");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_sword             , "");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_knife             , "Cuts things off of Blocks");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_shovel            , "");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_hoe               , "Can till Dirt and similar Earths");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_grafter           , "");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_saw               , "Can remove Bark from Logs");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_file              , "");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_hammer            , "Can hammer diverse things");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_plow              , "");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_plunger           , "For emptying clogged Pipes");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_scoop             , "To capture Bees and other Insects");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_shears            , "");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_scissors          , "");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_screwdriver       , "Adjusts Covers, Machines and Redstone Diodes");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_drill             , "Drills Holes and reinforces Bricks");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_chisel            , "Chisels Storage Blocks and chiselable Blocks");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_sense             , "Used to mass harvest Crops and Leaves");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_scythe            , "Used to mass harvest Crops and Leaves");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_softhammer        , "Toggles the States of Machines");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_cutter            , "Cuts and Connects Wires");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_plasmatorch       , "");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_solderingtool     , "");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_solderingmetal    , "");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_thermometer       , "Measuring Temperature");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_prospector        , "Prospecting for Ores in an Area");
		LH.add(TOOL_TOOLTIP_PREFIX + TOOL_ducttape          , "");
	}

	/** A Set of different NBT Keys I use for Stuff. */
	public static final String
	  NBT_HIDDEN                    = "gt.hidden"                   // Boolean
	, NBT_COLOR                     = "gt.color"                    // Integer
	, NBT_PAINTED                   = "gt.painted"                  // Boolean
	, NBT_TEXTURE                   = "gt.texture"                  // String
	, NBT_DESIGNS                   = "gt.designs"                  // Number
	, NBT_DESIGN                    = "gt.design"                   // Number
	, NBT_GUI                       = "gt.gui"                      // String
	, NBT_OWNABLE                   = "gt.ownable"                  // Boolean
	, NBT_OWNER                     = "gt.owner"                    // String
	, NBT_KEY                       = "gt.key"                      // Number
	, NBT_OPEN                      = "gt.open"                     // Boolean
	, NBT_FLUSH                     = "gt.flush"                    // Boolean
	, NBT_MODE                      = "gt.mode"                     // Number
	, NBT_STATE                     = "gt.state"                    // Number
	, NBT_MINENERGY                 = "gt.minenergy"                // Number
	, NBT_PROGRESS                  = "gt.progress"                 // Number
	, NBT_MAXPROGRESS               = "gt.maxprogress"              // Number
	, NBT_DISTANCE                  = "gt.distance"                 // Number
	, NBT_THROUGHPUT                = "gt.througput"                // Number
	, NBT_PARALLEL                  = "gt.parallel"                 // Number
	, NBT_PARALLEL_DURATION         = "gt.paradura"                 // Boolean
	, NBT_NUCLEAR_SELF              = "gt.nuclear.self"             // Number
	, NBT_NUCLEAR_OTHER             = "gt.nuclear.other"            // Number
	, NBT_NUCLEAR_DIV               = "gt.nuclear.div"              // Number
	, NBT_NUCLEAR_MAX               = "gt.nuclear.max"             // Number
	, NBT_NUCLEAR_MOD               = "gt.nuclear.mod"             // Number
	, NBT_DURABILITY                = "gt.durability"               // Number
	, NBT_MAXDURABILITY             = "gt.maxdurability"            // Number
	, NBT_QUALITY                   = "gt.quality"                  // Byte
	, NBT_FORTUNE                   = "gt.fortune"                  // Byte
	, NBT_FACING                    = "gt.facing"                   // Byte
	, NBT_CONNECTION                = "gt.connection"               // Byte
	, NBT_DIAMETER                  = "gt.diameter"                 // Double
	, NBT_PIPELOSS                  = "gt.pipeloss"                 // Long
	, NBT_PIPERANGE                 = "gt.piperange"                // Long
	, NBT_PIPEBANDWIDTH             = "gt.pipebandwidth"            // Long
	, NBT_PIPESIZE                  = "gt.pipesize"                 // Long
	, NBT_PIPERENDER                = "gt.piperender"               // Byte
	, NBT_LIQUIDPROOF               = "gt.liquidproof"              // Boolean
	, NBT_GASPROOF                  = "gt.gasproof"                 // Boolean
	, NBT_ACIDPROOF                 = "gt.acidproof"                // Boolean
	, NBT_PLASMAPROOF               = "gt.plasmaproof"              // Boolean
	, NBT_CONTACTDAMAGE             = "gt.contactdamage"            // Boolean
	, NBT_OPAQUE                    = "gt.opaque"                   // Boolean
	, NBT_TRANSPARENT               = "gt.transparent"              // Boolean
	, NBT_WASTE_ENERGY              = "gt.wasteenergy"              // Boolean
	, NBT_LIMIT_CONSUMPTION         = "gt.limit.consumption"        // Boolean
	, NBT_IGNITION                  = "gt.ignite"                   // Boolean or Number
	, NBT_NEEDS_IGNITION            = "gt.needignite"               // Boolean
	, NBT_USE_OUTPUT_TANK           = "gt.useouttank"               // Boolean
	, NBT_FOAMED                    = "gt.foamed"                   // Boolean
	, NBT_FOAMDRIED                 = "gt.foamdried"                // Boolean
	, NBT_VISUAL                    = "gt.visual"                   // Number or Boolean
	, NBT_PISTON                    = "gt.piston"                   // Number
	, NBT_VALUE                     = "gt.value"                    // Number
	, NBT_TARGET                    = "gt.target"                   // Boolean
	, NBT_TARGET_X                  = "gt.target.x"                 // Number
	, NBT_TARGET_Y                  = "gt.target.y"                 // Number
	, NBT_TARGET_Z                  = "gt.target.z"                 // Number
	, NBT_ACTIVE                    = "gt.active"                   // Boolean containing the active State of a Block.
	, NBT_ACTIVE_DATA               = "gt.active.data"              // Number
	, NBT_ACTIVE_ENERGY             = "gt.active.energy"            // Boolean
	, NBT_CAN_ENERGY                = "gt.can.energy"               // Boolean
	, NBT_HARDNESS                  = "gt.hardness"                 // Float
	, NBT_RESISTANCE                = "gt.resistance"               // Float
	, NBT_FLAMMABILITY              = "gt.flammability"             // Integer
	, NBT_COOLDOWN                  = "gt.cooldown"                 // Number
	, NBT_REDSTONE                  = "gt.redstone"                 // Byte
	, NBT_REDSTONE_0                = "gt.redstone.0"               // Byte
	, NBT_REDSTONE_1                = "gt.redstone.1"               // Byte
	, NBT_REDSTONE_2                = "gt.redstone.2"               // Byte
	, NBT_REDSTONE_3                = "gt.redstone.3"               // Byte
	, NBT_REDSTONE_4                = "gt.redstone.4"               // Byte
	, NBT_REDSTONE_5                = "gt.redstone.5"               // Byte
	, NBT_INVERTED                  = "gt.inverted"                 // Boolean signalising if the Machine is set to inverse.
	, NBT_REVERSED                  = "gt.reversed"                 // Boolean signalising if the Machine is set to reverse.
	, NBT_STOPPED                   = "gt.stopped"                  // Boolean signalising if the Machine is set to stop.
	, NBT_RUNNING                   = "gt.running"                  // Boolean signalising if the Machine is set up to run.
	, NBT_SPECIAL_IS_START_ENERGY   = "gt.special.start.energy"     // Boolean
	, NBT_CHEAP_OVERCLOCKING        = "gt.cheap.overclocking"       // Boolean
	, NBT_NO_CONSTANT_POWER         = "gt.no.constant.power"        // Boolean
	, NBT_EFFICIENCY                = "gt.eff"                      // Short from 0 to 10000 describing the Efficiency of a Generator or Converter.
	, NBT_MULTIPLIER                = "gt.multiplier"               // Number
	, NBT_INV_SIZE                  = "gt.invsize"                  // Short
	, NBT_INV_LIST                  = "gt.invlist"                  // NBT List
	, NBT_INV_FILTER                = "gt.invfilter"                // NBT List
	, NBT_INV_OUT                   = "gt.invout"                   // NBT containing an Output Item. usually postfixed with ".i", 'i' being the index.
	, NBT_INV_SIDE_IN               = "gt.invsidein"                // Byte
	, NBT_INV_SIDE_OUT              = "gt.invsideout"               // Byte
	, NBT_INV_SIDE_AUTO_IN          = "gt.invsideautoin"            // Byte
	, NBT_INV_SIDE_AUTO_OUT         = "gt.invsideautoout"           // Byte
	, NBT_INV_DISABLED_IN           = "gt.invdisabledin"            // Boolean
	, NBT_INV_DISABLED_OUT          = "gt.invdisabledout"           // Boolean
	, NBT_TANK                      = "gt.tank"                     // NBT containing a Tank. usually postfixed with ".i", 'i' being the index.
	, NBT_TANK_FILTER               = "gt.tankfilter"               // NBT List
	, NBT_TANK_OUT                  = "gt.tankout"                  // NBT containing an Output Fluid. usually postfixed with ".i", 'i' being the index.
	, NBT_TANK_COUNT                = "gt.tankcount"                // Number
	, NBT_TANK_CAPACITY             = "gt.tankcap"                  // Number
	, NBT_TANK_SIDE_IN              = "gt.tanksidein"               // Byte
	, NBT_TANK_SIDE_OUT             = "gt.tanksideout"              // Byte
	, NBT_TANK_SIDE_AUTO_IN         = "gt.tanksideautoin"           // Byte
	, NBT_TANK_SIDE_AUTO_OUT        = "gt.tanksideautoout"          // Byte
	, NBT_TANK_DISABLED_IN          = "gt.tankdisabledin"           // Boolean
	, NBT_TANK_DISABLED_OUT         = "gt.tankdisabledout"          // Boolean
	, NBT_COVERS                    = "gt.covers"                   // Tag Compound with 24 Tags.
	, NBT_RECIPEMAP                 = "gt.recipemap"                // String containing the Recipe Map Name.
	, NBT_FUELMAP                   = "gt.fuelmap"                  // String containing the Fuel Map Name.
	, NBT_TEMPERATURE               = "gt.temperature"              // Long containing a generic Temperature Variable.
	, NBT_MTE_REG                   = "gt.mte.reg"                  // Containing the MTE Registry ID
	, NBT_MTE_ID                    = "gt.mte.id"                   // Containing the MTE ID
	, NBT_USB_DIRECTION             = "gt.usb.dir"                  // Compound
	, NBT_USB_TIER                  = "gt.usb.tier"                 // Byte
	, NBT_USB_DATA                  = "gt.usb.data"                 // Compound
	, NBT_USB_DRIVE                 = "gt.usb.drive"                // Compound
	, NBT_CANVAS_BLOCK              = "gt.canvas.block"             // Short
	, NBT_CANVAS_META               = "gt.canvas.meta"              // Short
	, NBT_REPLICATOR_DATA           = "gt.replicator.data"          // Short
	, NBT_REACTOR_SETUP             = "gt.reactor.setup"            // Short
	, NBT_REACTOR_SETUP_NAME        = "gt.reactor.setup.name"       // String

	, NBT_RECYCLING_COMPS           = "gt.recycling.comps"          // List of Components for the Disassembler.
	, NBT_RECYCLING_MATS            = "gt.recycling.mats"           // List of Materials this Item recycles into. OM.anydata(ItemStack aStack) will check for this NBT, but only AFTER the Server already started!
	, NBT_FUEL_VALUE                = "gt.fuelvalue"                // Short
	, NBT_EFFECTS                   = "gt.effects"                  // Compound

	, NBT_MATERIAL                  = "gt.material"                 // String containing the Material Name.
	, NBT_MATERIALS                 = "gt.materials"                // Containing the Data of an Array of Material Stacks.

	, NBT_ENERGY_EMITTED            = "gt.energy.emitted"           // String containing the Type of emitted Energy.
	, NBT_ENERGY_EMITTED_2          = "gt.energy.emitted.2"         // String containing the Type of emitted Energy.
	, NBT_ENERGY_EMITTED_SIDES      = "gt.energy.emitted.sides"     // String containing the Type of emitted Energy.
	, NBT_ENERGY_EMITTED_TOP        = "gt.energy.emitted.top"       // String containing the Type of emitted Energy.
	, NBT_ENERGY_EMITTED_BOTTOM     = "gt.energy.emitted.bottom"    // String containing the Type of emitted Energy.
	, NBT_ENERGY_EMITTED_FRONT      = "gt.energy.emitted.front"     // String containing the Type of emitted Energy.
	, NBT_ENERGY_EMITTED_BACK       = "gt.energy.emitted.back"      // String containing the Type of emitted Energy.
	, NBT_ENERGY_EMITTED_LEFT       = "gt.energy.emitted.left"      // String containing the Type of emitted Energy.
	, NBT_ENERGY_EMITTED_RIGHT      = "gt.energy.emitted.right"     // String containing the Type of emitted Energy.

	, NBT_ENERGY_ACCEPTED           = "gt.energy.accepted"          // String containing the Type of accepted Energy.
	, NBT_ENERGY_ACCEPTED_2         = "gt.energy.accepted.2"        // String containing the Type of accepted Energy.
	, NBT_ENERGY_ACCEPTED_SIDES     = "gt.energy.accepted.sides"    // String containing the Type of accepted Energy.
	, NBT_ENERGY_ACCEPTED_TOP       = "gt.energy.accepted.top"      // String containing the Type of accepted Energy.
	, NBT_ENERGY_ACCEPTED_BOTTOM    = "gt.energy.accepted.bottom"   // String containing the Type of accepted Energy.
	, NBT_ENERGY_ACCEPTED_FRONT     = "gt.energy.accepted.front"    // String containing the Type of accepted Energy.
	, NBT_ENERGY_ACCEPTED_BACK      = "gt.energy.accepted.back"     // String containing the Type of accepted Energy.
	, NBT_ENERGY_ACCEPTED_LEFT      = "gt.energy.accepted.left"     // String containing the Type of accepted Energy.
	, NBT_ENERGY_ACCEPTED_RIGHT     = "gt.energy.accepted.right"    // String containing the Type of accepted Energy.

	, NBT_INPUT                     = "gt.input"                    // Long containing a generic Energy Variable.
	, NBT_INPUT_MIN                 = "gt.input.min"                // Long containing a generic Energy Variable.
	, NBT_INPUT_MAX                 = "gt.input.max"                // Long containing a generic Energy Variable.
	, NBT_INPUT_EU                  = "gt.input.eu"                 // Long containing a specified Energy Variable.
	, NBT_INPUT_RU                  = "gt.input.ru"                 // Long containing a specified Energy Variable.
	, NBT_INPUT_KU                  = "gt.input.ku"                 // Long containing a specified Energy Variable.
	, NBT_INPUT_HU                  = "gt.input.hu"                 // Long containing a specified Energy Variable.
	, NBT_INPUT_LU                  = "gt.input.lu"                 // Long containing a specified Energy Variable.
	, NBT_INPUT_SU                  = "gt.input.su"                 // Long containing a specified Energy Variable.
	, NBT_INPUT_AU                  = "gt.input.au"                 // Long containing a specified Energy Variable.
	, NBT_INPUT_MJ                  = "gt.input.mj"                 // Long containing a specified Energy Variable.
	, NBT_INPUT_RF                  = "gt.input.rf"                 // Long containing a specified Energy Variable.

	, NBT_OUTPUT                    = "gt.output"                   // Long containing a generic Energy Variable.
	, NBT_OUTPUT_MIN                = "gt.output.min"               // Long containing a generic Energy Variable.
	, NBT_OUTPUT_MAX                = "gt.output.max"               // Long containing a generic Energy Variable.
	, NBT_OUTPUT_EU                 = "gt.output.eu"                // Long containing a specified Energy Variable.
	, NBT_OUTPUT_RU                 = "gt.output.ru"                // Long containing a specified Energy Variable.
	, NBT_OUTPUT_KU                 = "gt.output.ku"                // Long containing a specified Energy Variable.
	, NBT_OUTPUT_HU                 = "gt.output.hu"                // Long containing a specified Energy Variable.
	, NBT_OUTPUT_LU                 = "gt.output.lu"                // Long containing a specified Energy Variable.
	, NBT_OUTPUT_SU                 = "gt.output.su"                // Long containing a specified Energy Variable.
	, NBT_OUTPUT_AU                 = "gt.output.au"                // Long containing a specified Energy Variable.
	, NBT_OUTPUT_MJ                 = "gt.output.mj"                // Long containing a specified Energy Variable.
	, NBT_OUTPUT_RF                 = "gt.output.rf"                // Long containing a specified Energy Variable.

	, NBT_ENERGY                    = "gt.energy"                   // Long containing a generic Energy Variable.
	, NBT_ENERGY_EU                 = "gt.energy.eu"                // Long containing a specified Energy Variable.
	, NBT_ENERGY_RU                 = "gt.energy.ru"                // Long containing a specified Energy Variable.
	, NBT_ENERGY_KU                 = "gt.energy.ku"                // Long containing a specified Energy Variable.
	, NBT_ENERGY_HU                 = "gt.energy.hu"                // Long containing a specified Energy Variable.
	, NBT_ENERGY_LU                 = "gt.energy.lu"                // Long containing a specified Energy Variable.
	, NBT_ENERGY_SU                 = "gt.energy.su"                // Long containing a specified Energy Variable.
	, NBT_ENERGY_AU                 = "gt.energy.au"                // Long containing a specified Energy Variable.
	, NBT_ENERGY_MJ                 = "gt.energy.mj"                // Long containing a specified Energy Variable.
	, NBT_ENERGY_RF                 = "gt.energy.rf"                // Long containing a specified Energy Variable.

	, NBT_CAPACITY                  = "gt.capacity"                 // Long containing a generic Energy Variable as Capacity.
	, NBT_CAPACITY_EU               = "gt.capacity.eu"              // Long containing a specified Energy Variable as Capacity.
	, NBT_CAPACITY_RU               = "gt.capacity.ru"              // Long containing a specified Energy Variable as Capacity.
	, NBT_CAPACITY_KU               = "gt.capacity.ku"              // Long containing a specified Energy Variable as Capacity.
	, NBT_CAPACITY_HU               = "gt.capacity.hu"              // Long containing a specified Energy Variable as Capacity.
	, NBT_CAPACITY_LU               = "gt.capacity.lu"              // Long containing a specified Energy Variable as Capacity.
	, NBT_CAPACITY_SU               = "gt.capacity.su"              // Long containing a specified Energy Variable as Capacity.
	, NBT_CAPACITY_AU               = "gt.capacity.au"              // Long containing a specified Energy Variable as Capacity.
	, NBT_CAPACITY_MJ               = "gt.capacity.mj"              // Long containing a specified Energy Variable as Capacity.
	, NBT_CAPACITY_RF               = "gt.capacity.rf"              // Long containing a specified Energy Variable as Capacity.
	;

	/** List of Visually Full Opaque Blocks. For minor Render optimisations. */
	public static final HashSetNoNulls<Block> VISUALLY_OPAQUE_BLOCKS = new HashSetNoNulls<>(F, Blocks.bedrock, Blocks.command_block, Blocks.hardened_clay, Blocks.stained_hardened_clay, Blocks.gravel, Blocks.sand, Blocks.sandstone, Blocks.end_stone, Blocks.nether_brick, Blocks.netherrack, Blocks.obsidian, Blocks.planks, Blocks.log, Blocks.log2, Blocks.stone, Blocks.cobblestone, Blocks.mossy_cobblestone, Blocks.grass, Blocks.dirt, Blocks.clay, Blocks.stonebrick, Blocks.redstone_block, Blocks.glowstone, Blocks.redstone_lamp, Blocks.lit_redstone_lamp, Blocks.lit_redstone_ore, Blocks.pumpkin, Blocks.melon_block);


	public static class GarbageGT {
		public static ItemStackSet<ItemStackContainer> BLACKLIST = new ItemStackSet<>();
		public static ItemStackMap<ItemStackContainer, ItemStack> GARBAGE_MAP_ITEMS = new ItemStackMap<>();
		public static ArrayListNoNulls<ItemStack> GARBAGE_ITEMS = new ArrayListNoNulls<>();
		public static ArrayListNoNulls<FluidTankGT> GARBAGE_FLUIDS = new ArrayListNoNulls<>();
		
		public static int trash(ItemStack aStack) {
			if (ST.invalid(aStack) || aStack.stackSize <= 0 || BLACKLIST.contains(aStack, T)) return 0;
			if (aStack.hasTagCompound()) {
				for (ItemStack tGarbage : GARBAGE_ITEMS) if (ST.equal(aStack, tGarbage)) {
					tGarbage.stackSize = UT.Code.bindInt((long)tGarbage.stackSize + (long)aStack.stackSize);
					return aStack.stackSize;
				}
				GARBAGE_ITEMS.add(aStack.copy());
				return aStack.stackSize;
			}
			ItemStack tGarbage = GARBAGE_MAP_ITEMS.get(aStack);
			if (ST.valid(tGarbage)) {
				tGarbage.stackSize = UT.Code.bindInt((long)tGarbage.stackSize + (long)aStack.stackSize);
				return aStack.stackSize;
			}
			aStack = aStack.copy();
			GARBAGE_MAP_ITEMS.put(aStack, aStack);
			GARBAGE_ITEMS.add(aStack);
			return aStack.stackSize;
		}
		public static long trash(ItemStack[] aInventory) {
			if (aInventory == null) return 0;
			long rTrashed = 0;
			for (int i = 0; i < aInventory.length; i++) {rTrashed += trash(aInventory[i]); aInventory[i] = NI;}
			return rTrashed;
		}
		public static int trash(ItemStack[] aInventory, int aIndex) {
			if (aInventory == null || aIndex < 0 || aIndex >= aInventory.length) return 0;
			int rTrashed = trash(aInventory[aIndex]);
			aInventory[aIndex] = NI;
			return rTrashed;
		}
		
		public static int trash(OreDictMaterialStack aMaterial) {
			if (aMaterial == null || aMaterial.mAmount < OP.scrapGt.mAmount) return 0;
			return trash(OP.scrapGt.mat(aMaterial.mMaterial, aMaterial.mAmount / OP.scrapGt.mAmount));
		}
		public static long trash(Iterable<OreDictMaterialStack> aMaterials) {
			if (aMaterials == null) return 0;
			long rTrashed = 0;
			Iterator<OreDictMaterialStack> tIterator = aMaterials.iterator();
			while (tIterator.hasNext()) {rTrashed += trash(tIterator.next()); tIterator.remove();};
			return rTrashed;
		}
		
		public static long trash(FluidStack aFluid) {
			return aFluid == null ? 0 : trash(aFluid, aFluid.amount);
		}
		public static long trash(FluidStack aFluid, long aAmount) {
			if (aFluid == null || aAmount <= 0) return 0;
			for (FluidTankGT tGarbage : GARBAGE_FLUIDS) if (tGarbage.contains(aFluid)) {
				tGarbage.add(aAmount);
				return aAmount;
			}
			GARBAGE_FLUIDS.add(new FluidTankGT(aFluid, aAmount, Long.MAX_VALUE).setPreventDraining().setVoidExcess());
			return aAmount;
		}
		public static long trash(IFluidTank aTank) {
			return trash(aTank, Long.MAX_VALUE);
		}
		public static long trash(IFluidTank aTank, long aTrashed) {
			if (aTank == null || aTrashed <= 0) return 0;
			return aTank instanceof FluidTankGT ? trash(aTank.getFluid(), ((FluidTankGT)aTank).remove(aTrashed)) : trash(aTank.drain(UT.Code.bindInt(aTrashed), T));
		}
		public static long trash(IFluidTank[] aTanks) {
			if (aTanks == null) return 0;
			long rTrashed = 0;
			for (int i = 0; i < aTanks.length; i++) rTrashed += trash(aTanks[i]);
			return rTrashed;
		}
		public static long trash(IFluidTank[] aTanks, int aIndex) {
			if (aTanks == null || aIndex < 0 || aIndex >= aTanks.length) return 0;
			return trash(aTanks[aIndex]);
		}


		public static void onServerSave(File aSaveLocation) {
			File aTargetFile = new File(new File(aSaveLocation, "gregtech"), "endergarbage.items.dat");
			if (!aTargetFile.exists()) {try {aTargetFile.createNewFile();} catch (Throwable e) {e.printStackTrace(ERR);}}
			NBTTagCompound aNBT = UT.NBT.make();
			for (int i = 0; i < GARBAGE_ITEMS.size(); i++) ST.save(aNBT, ""+i, GARBAGE_ITEMS.get(i));
			try {CompressedStreamTools.write(aNBT, aTargetFile);} catch (Throwable e) {e.printStackTrace(ERR);}

			aTargetFile = new File(new File(aSaveLocation, "gregtech"), "endergarbage.fluids.dat");
			if (!aTargetFile.exists()) {try {aTargetFile.createNewFile();} catch (Throwable e) {e.printStackTrace(ERR);}}
			aNBT = UT.NBT.make();
			for (int i = 0; i < GARBAGE_FLUIDS.size(); i++) GARBAGE_FLUIDS.get(i).writeToNBT(aNBT, ""+i);
			try {CompressedStreamTools.write(aNBT, aTargetFile);} catch (Throwable e) {e.printStackTrace(ERR);}
		}

		public static void onServerLoad(File aSaveLocation) {
			GARBAGE_ITEMS.clear();
			File aTargetFile = new File(new File(aSaveLocation, "gregtech"), "endergarbage.items.dat");
			if (aTargetFile.exists()) {
				NBTTagCompound aNBT = UT.NBT.make();
				try {aNBT = CompressedStreamTools.read(aTargetFile);} catch (Throwable e) {e.printStackTrace(ERR);}
				for (int i = 0; i < Integer.MAX_VALUE; i++) {
					if (!aNBT.hasKey(""+i)) break;
					ItemStack aStack = ST.load(aNBT, ""+i);
					if (aStack == null || aStack.stackSize <= 0 || BLACKLIST.contains(aStack, T)) continue;
					GARBAGE_ITEMS.add(aStack);
				}
			}
			
			GARBAGE_FLUIDS.clear();
			aTargetFile = new File(new File(aSaveLocation, "gregtech"), "endergarbage.fluids.dat");
			if (aTargetFile.exists()) {
				NBTTagCompound aNBT = UT.NBT.make();
				try {aNBT = CompressedStreamTools.read(aTargetFile);} catch (Throwable e) {e.printStackTrace(ERR);}
				for (int i = 0; i < Integer.MAX_VALUE; i++) {
					if (!aNBT.hasKey(""+i)) break;
					FluidTankGT tTank = new FluidTankGT().setPreventDraining().setVoidExcess();
					tTank.readFromNBT(aNBT, ""+i);
					if (!tTank.has()) continue;
					GARBAGE_FLUIDS.add(tTank);
				}
			}
		}
	}

	public static class DrinksGT {
		public static Map<String, IFoodStat> REGISTER = new HashMap<>();
	}

	public static class FluidsGT {
		public static Map<String, String> FLUID_RENAMINGS = new HashMap<>();
		
		public static Set<String>
		  SIMPLE = new HashSetNoNulls<>(F, "poison")
		, ACID = new HashSetNoNulls<>(F, "acid", "creeper_acid", "sulfuricacid", "nitricacid", "aquaregia", "hydrochloricacid", "mutagen", "liquiddna", "binnie.dna.raw", "binnie.bacteriavector", "binnie.bacteriapoly", "binnie.bacteria")
		, LIQUID = new HashSetNoNulls<>(F, "poison", "liquidnitrogen", "liquiddna")
		, GAS = new HashSetNoNulls<>()
		, PLASMA = new HashSetNoNulls<>(F, "rc fusion plasma")
		, HIDDEN = new HashSetNoNulls<>(F, "heliumplasma", "nitrogenplasma")
		, AIR = new HashSetNoNulls<>()
		, OXYGEN = new HashSetNoNulls<>()
		, LIQUID_OXYGEN = new HashSetNoNulls<>()
		, ENCHANTED_EFFECT = new HashSetNoNulls<>()
		, VOID_OVERFLOW = new HashSetNoNulls<>()
		, NONSTANDARD = new HashSetNoNulls<>()
		, BROKEN = new HashSetNoNulls<>()
		, INFINITE = new HashSetNoNulls<>() // Marks things that are stupidly easy to obtain, except Water and Milk.
		, BATH = new HashSetNoNulls<>()
		, DYE = new HashSetNoNulls<>()
		
		, STEAM = new HashSetNoNulls<>()
		, POWER_CONDUCTING = new HashSetNoNulls<>(F, "rc fusion plasma")
		, LUBRICANT = new HashSetNoNulls<>()
		, THERMOS = new HashSetNoNulls<>(F, "coffee")
		, POTION = new HashSetNoNulls<>()
		
		, FOOD = new HashSetNoNulls<>(F, "coffee")
		, JUICE = new HashSetNoNulls<>()
		, FRUIT_JUICE = new HashSetNoNulls<>()
		, CITRUS_JUICE = new HashSetNoNulls<>()
		, WATER = new HashSetNoNulls<>()
		, HONEY = new HashSetNoNulls<>()
		, MILK = new HashSetNoNulls<>()
		, TEA = new HashSetNoNulls<>()
		, COOKING_OIL = new HashSetNoNulls<>()
		, SLIME = new HashSetNoNulls<>()
		
		, ALCOHOLIC = new HashSetNoNulls<>()
		, VINEGAR = new HashSetNoNulls<>()
		, RUM = new HashSetNoNulls<>()
		, WINE = new HashSetNoNulls<>()
		, BEER = new HashSetNoNulls<>()
		, CIDER = new HashSetNoNulls<>()
		, SPIRIT = new HashSetNoNulls<>()
		, BRANDY = new HashSetNoNulls<>()
		, LIQUOR = new HashSetNoNulls<>()
		, LIQUEUR = new HashSetNoNulls<>()
		, WHISKEY = new HashSetNoNulls<>()
		;
		
		static {
			SIMPLE.addAll(FOOD);
			FLUID_RENAMINGS.put("molten.teslatite", "molten.nikolite");
			FLUID_RENAMINGS.put("molten.electrotine", "molten.nikolite");
			FLUID_RENAMINGS.put("molten.teslatinealloy", "molten.nikolinealloy");
		}
	}
	
	/** Contains Potion Effects of other Mods. The real IDs are to be set on API postInit, many things such as Food will detect the negative Numbers and replace them on demand. */
	public static class PotionsGT {
		public static int
		  ID_RADIATION = -2
		, ID_HYPOTHERMIA = -3, ID_HEATSTROKE = -4, ID_FROSTBITE = -5, ID_DEHYDRATION = -6, ID_INSANITY = -7
		, ID_FLAMMABLE = -8, ID_SLIPPERY = -9, ID_CONDUCTIVE = -10, ID_STICKY = -11
		;
	}
	
	public static class BushesGT {
		public static final ItemStackMap<ItemStackContainer, int[]> MAP = new ItemStackMap<>();
		public static int[] get(ItemStack aStack) {return MAP.containsKey(aStack, F) ? MAP.get(aStack) : MAP.get(new ItemStackContainer(aStack, 1, W));}
		/** Bush Color, Stage 1, Stage 2, Stage 3 */
		public static void put(ItemStack aStack, int aBush, int aBloom, int aImmature, int aBerry) {if (!MAP.containsKey(aStack, F)) MAP.put(aStack, new int[] {aBush, aBloom, aImmature, aBerry});}
		public static void override(ItemStack aStack, int... aColors) {MAP.put(aStack, aColors);}
	}
	
	public static class FoodsGT {
		public static final ItemStackMap<ItemStackContainer, int[]> MAP = new ItemStackMap<>();
		public static int[] get(ItemStack aStack) {return MAP.containsKey(aStack, F) ? MAP.get(aStack) : MAP.get(new ItemStackContainer(aStack, 1, W));}
		/** Alcohol, Caffeine, Dehydration, Sugar, Fat */
		public static void put(ItemStack aStack, int aAlcohol, int aCaffeine, int aDehydration, int aSugar, int aFat) {if (!MAP.containsKey(aStack, F)) MAP.put(aStack, new int[] {aAlcohol, aCaffeine, aDehydration, aSugar, aFat});}
		public static void override(ItemStack aStack, int... aStats) {MAP.put(aStack, aStats);}
	}
	
	/** Contains GT Items and Item related References. */
	public static class ItemsGT {
		/** The MultiItems */
		public static MultiItemRandom TECH, TOOLS, CANS, FOOD, BOTTLES, BOOKS, BUMBLEBEES;
		public static MultiItemRandom[] ALL_MULTI_ITEMS = new MultiItemRandom[] {TECH, TOOLS, CANS, FOOD, BOTTLES, BOOKS, BUMBLEBEES};
		public static final ItemStackSet<ItemStackContainer> ILLEGAL_DROPS = new ItemStackSet<>();
		public static final ItemStackSet<ItemStackContainer> DEBUG_ITEMS = new ItemStackSet<>();
		public static final ItemStackSet<ItemStackContainer> AMMO_ITEMS = new ItemStackSet<>();
		public static final ItemStackSet<ItemStackContainer> NON_AUTO_INSERT_ITEMS = new ItemStackSet<>();
		public static final ItemStackSet<ItemStackContainer> CONTAINER_DURABILITY = new ItemStackSet<>();
		public static final ItemStackSet<ItemStackContainer> SPECIAL_CASE_TOOLS = new ItemStackSet<>();
		public static final ItemStackSet<ItemStackContainer> SHOW_RESISTANCE = new ItemStackSet<>();
		public static final ItemStackSet<ItemStackContainer> RECIPE_REMOVED_USE_TRASH_BIN_INSTEAD = new ItemStackSet<>();
		public static final ItemStackSet<ItemStackContainer> NEI_DONT_SHOW_FLUIDS = new ItemStackSet<>();
		
		public static boolean addNEIRedirect(ItemStack aStack, ItemStack... aRedirects) {if (aStack == null) return F; ArrayListNoNulls<ItemStack> tList = sNEIRedirects.get(new ItemStackContainer(aStack)); if (tList == null) sNEIRedirects.put(new ItemStackContainer(aStack), tList = new ArrayListNoNulls<>()); return tList.addAll(Arrays.asList(aRedirects));}
		public static boolean addNEIRedirects(Block aBlock) {ItemStack[] tRedirects = new ItemStack[16]; for (int i = 0; i < tRedirects.length; i++) tRedirects[i] = ST.make(aBlock, 1, i); return addNEIRedirects(tRedirects);}
		public static boolean addNEIRedirects(ItemStack... aRedirects) {for (ItemStack tStack : aRedirects) if (ST.valid(tStack)) {ArrayListNoNulls<ItemStack> tList = sNEIRedirects.get(new ItemStackContainer(tStack)); if (tList == null) sNEIRedirects.put(new ItemStackContainer(tStack), tList = new ArrayListNoNulls<>()); tList.addAll(Arrays.asList(aRedirects));} return T;}
		public static final Map<ItemStackContainer, ArrayListNoNulls<ItemStack>> sNEIRedirects = new ItemStackMap<>();
	}
	
	/** Contains GT Blocks and Block related References. */
	public static class BlocksGT {
		public static IPrefixBlock
		casingMachine, casingMachineDouble, casingMachineQuadruple, casingMachineDense,
		blockGem    , blockDust    , blockIngot    , blockPlate    , blockPlateGem    , blockRaw    , blockSolid,
		crateGtGem  , crateGtDust  , crateGtIngot  , crateGtPlate  , crateGtPlateGem  , crateGtRaw  ,
		crateGt64Gem, crateGt64Dust, crateGt64Ingot, crateGt64Plate, crateGt64PlateGem, crateGt64Raw;
		
		/** Ore Blocks */
		public static IPrefixBlock
		ore      , oreSandstone      , oreNetherrack      , oreEndstone      , oreAtumLimestone      , oreAtumSand, oreGravel, oreMud, oreSand, oreRedSand, oreBedrock,
		oreBroken, oreBrokenSandstone, oreBrokenNetherrack, oreBrokenEndstone, oreBrokenAtumLimestone,
		oreSmall , oreSmallSandstone , oreSmallNetherrack , oreSmallEndstone , oreSmallAtumLimestone , oreSmallAtumSand, oreSmallGravel, oreSmallMud, oreSmallSand, oreSmallRedSand, oreSmallBedrock;
		
		public static BlockBaseFluid OilLight, OilMedium, OilHeavy, OilExtraHeavy, GasNatural;
		public static BlockFluidClassic Ocean, Swamp, River;
		
		public static BlockBase Sands, Diggables, Grass, Paths, RockOres, CrystalOres, VanillaOresA;
		
		public static IBlockBase FlowersA, FlowersB;
		public static BlockBase Glowtus, BalesGrass, BalesCrop;
		public static BlockBase Saplings_AB, Saplings_CD, Leaves_AB, Leaves_CD;
		public static BlockBase Log1, Log1FireProof, LogA, LogAFireProof, LogB, LogBFireProof, LogC, LogCFireProof, LogD, LogDFireProof;
		public static BlockBase Beam1, Beam1FireProof, Beam2, Beam2FireProof, Beam3, Beam3FireProof, BeamA, BeamAFireProof, BeamB, BeamBFireProof, BeamC, BeamCFireProof, BeamD, BeamDFireProof;
		public static BlockBase Planks, PlanksFireProof, Planks2, Planks2FireProof;
		
		public static BlockBase CFoam, CFoamFresh, Concrete, ConcreteReinforced, Asphalt, Glass, GlowGlass;
		public static BlockBase Bars_Wood, Bars_Steel, Bars_Titanium, Bars_TungstenSteel, Bars_Adamantium;
		public static BlockBase Spikes_Sharp, Spikes_Steel, Spikes_Super, Spikes_Metal, Spikes_Fancy;
		public static BlockBase LongDistWire01, LongDistPipe01;
		
		public static IBlockBase
		RailAluminium, RailMagnalium, RailBronze, RailStainlessSteel, RailSteel, RailTitanium, RailTungsten, RailTungstenCarbide, RailTungstenSteel,
		RailAluminiumBooster, RailMagnaliumBooster, RailBronzeBooster, RailStainlessSteelBooster, RailSteelBooster, RailTitaniumBooster, RailTungstenBooster, RailTungstenCarbideBooster, RailTungstenSteelBooster,
		RailAluminiumDetector, RailMagnaliumDetector, RailBronzeDetector, RailStainlessSteelDetector, RailSteelDetector, RailTitaniumDetector, RailTungstenDetector, RailTungstenCarbideDetector, RailTungstenSteelDetector;
		
		/** GT6 Stone Type. */
		public static BlockBase GraniteBlack, GraniteRed, Basalt, Marble, Limestone, Granite, Diorite, Andesite, Komatiite, SchistGreen, SchistBlue, Kimberlite, Quartzite, PrismarineLight, PrismarineDark, Slate, Shale;
		/** Contains all GT6 Stone Types. */
		// Yes, I know those assignments inside the Array are not actually working, but this gives a good overview of the Content, once it got initialised.
		public static BlockBase[] stones = {GraniteBlack, GraniteRed, Basalt, Marble, Limestone, Granite, Diorite, Andesite, Komatiite, SchistGreen, SchistBlue, Kimberlite, Quartzite, PrismarineLight, PrismarineDark, Slate, Shale};
		/** Contains the Ore Blocks for all the GT6 Type Stones, corresponding to the Array above. */
		public static IPrefixBlock[] ores_normal = new IPrefixBlock[stones.length], ores_broken = new IPrefixBlock[stones.length], ores_small = new IPrefixBlock[stones.length];
		
		/** Mappings for Stones to their correspondent Ores. No WildCard allowed! Register all 16 MetaData Values here instead. */
		public static Map<ItemStackContainer, IBlockPlacable>
		  stoneToNormalOres = new ItemStackMap<>()
		, stoneToBrokenOres = new ItemStackMap<>()
		, stoneToSmallOres  = new ItemStackMap<>()
		;
		
		/** Mappings for changing Drops of Blocks. */
		public static ItemStackMap<ItemStackContainer, ItemStack>
		  blockToDrop = new ItemStackMap<>()
		, blockToSilk = new ItemStackMap<>()
		;
		
		public static final Set<Object> stoneOverridable  = new HashSetNoNulls<>(F);
		public static final Set<Object> instaharvest      = new HashSetNoNulls<Object>(F, Blocks.torch, Blocks.redstone_torch, Blocks.unlit_redstone_torch, Blocks.redstone_wire, Blocks.powered_comparator, Blocks.unpowered_comparator, Blocks.powered_repeater, Blocks.unpowered_repeater, Blocks.skull, Blocks.monster_egg);
		public static final Set<Object> breakableGlass    = new HashSetNoNulls<Object>(F, Blocks.glass, Blocks.glass_pane, Blocks.stained_glass, Blocks.stained_glass_pane);
		public static final Set<Object> openableCrowbar   = new HashSetNoNulls<Object>(F, Blocks.iron_block, Blocks.gold_block, Blocks.lapis_block, Blocks.diamond_block, Blocks.emerald_block, Blocks.redstone_block, Blocks.coal_block);
		public static final Set<Object> drillableDynamite = new HashSetNoNulls<Object>(F, Blocks.grass, Blocks.dirt, Blocks.mycelium, Blocks.clay, Blocks.snow, Blocks.gravel, Blocks.sandstone, Blocks.cobblestone, Blocks.mossy_cobblestone, Blocks.netherrack, Blocks.end_stone, Blocks.hardened_clay, Blocks.stained_hardened_clay, Blocks.iron_ore, Blocks.gold_ore, Blocks.lapis_ore, Blocks.diamond_ore, Blocks.emerald_ore, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.coal_ore, Blocks.quartz_ore, Blocks.monster_egg);
		public static final Set<Object> harvestableSpade  = new HashSetNoNulls<Object>(F, Blocks.grass, Blocks.dirt, Blocks.mycelium, Blocks.clay, Blocks.snow, Blocks.gravel);
		public static final Set<Object> plantableGreens   = new HashSetNoNulls<Object>(F, Blocks.grass, Blocks.dirt, Blocks.farmland);
		public static final Set<Object> plantableTrees    = new HashSetNoNulls<Object>(F, Blocks.grass, Blocks.dirt);
		public static final Set<Object> plantableGrass    = new HashSetNoNulls<Object>(F, Blocks.grass);
		
		/** Blocks to not generate Ores in. */
		public static ItemStackSet<ItemStackContainer> sDontGenerateOresIn = new ItemStackSet<>();
		
		public static final Set<Object> FLOWERS = new HashSetNoNulls<Object>(F, Blocks.yellow_flower, Blocks.red_flower);
		
		public static final Block[] POT_FLOWER_TILES = {Blocks.cactus, Blocks.brown_mushroom, Blocks.red_mushroom, Blocks.yellow_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower};
		public static final byte [] POT_FLOWER_METAS = {0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8};
		
		public static final Block[] FLOWER_TILES = {Blocks.yellow_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower, Blocks.red_flower};
		public static final byte [] FLOWER_METAS = {0, 0, 1, 2, 3, 4, 5, 6, 7, 8};
		
		@Deprecated public static BlockBase Sapling = Saplings_AB, Leaves = Leaves_AB;
	}

	public static class ArmorsGT {
		/** The List of Hazmat Armors */
		public static final ItemStackSet<ItemStackContainer>
		  HAZMATS_GAS = new ItemStackSet<>()
		, HAZMATS_BIO = new ItemStackSet<>()
		, HAZMATS_CHEM = new ItemStackSet<>()
		, HAZMATS_INSECTS = new ItemStackSet<>()
		, HAZMATS_FROST = new ItemStackSet<>()
		, HAZMATS_HEAT = new ItemStackSet<>()
		, HAZMATS_RADIOACTIVE = new ItemStackSet<>()
		, HAZMATS_LIGHTNING = new ItemStackSet<>()
		;

		public static ItemArmorBase[]
		  HAZMAT_BIOCHEMGAS = new ItemArmorBase[4]
		, HAZMAT_INSECTS = new ItemArmorBase[4]
		, HAZMAT_FROST = new ItemArmorBase[4]
		, HAZMAT_HEAT = new ItemArmorBase[4]
		, HAZMAT_RADIOACTIVE = new ItemArmorBase[4]
		, HAZMAT_UNIVERSAL = new ItemArmorBase[4]
		;
	}

	/** Contains the Tool IDs for my MetaTool. */
	public static class ToolsGT {
		public static final short
		  SWORD = 0, PICKAXE = 2, SHOVEL = 4, AXE = 6, HOE = 8, SAW = 10, HARDHAMMER = 12, SOFTHAMMER = 14, WRENCH = 16, FILE = 18, CROWBAR = 20, SCREWDRIVER = 22, CLUB = 24, WIRECUTTER = 26, SCOOP = 28, BRANCHCUTTER = 30, UNIVERSALSPADE = 32, KNIFE = 34, BUTCHERYKNIFE = 36, SICKLE = 38, SENSE = 40, PLOW = 42, PLUNGER = 44, ROLLING_PIN = 46, CHISEL = 48, FLINT_AND_TINDER = 50, MONKEY_WRENCH = 52, BENDING_CYLINDER = 54, BENDING_CYLINDER_SMALL = 56, DOUBLE_AXE = 58, CONSTRUCTION_PICK = 60, MAGNIFYING_GLASS = 62, SCISSORS = 64, PINCERS = 66, SPADE = 68, GEM_PICK = 70, HAND_DRILL = 72, BUILDERWAND = 74
		, MININGDRILL_LV = 100, MININGDRILL_MV = 102, MININGDRILL_HV = 104, CHAINSAW_LV = 110, CHAINSAW_MV = 112, CHAINSAW_HV = 114, WRENCH_LV = 120, WRENCH_MV = 122, WRENCH_HV = 124, JACKHAMMER_HV_Normal = 130, JACKHAMMER_HV_No_Ores = 132, BUZZSAW_LV = 140, SCREWDRIVER_LV = 150, DRILL_LV = 160, MIXER_LV = 170, MONKEY_WRENCH_LV = 180, MONKEY_WRENCH_MV = 182, MONKEY_WRENCH_HV = 184, TRIMMER_LV = 190
		, POCKET_MULTITOOL = 1000, POCKET_KNIFE = 1002, POCKET_SAW = 1004, POCKET_FILE = 1006, POCKET_SCREWDRIVER = 1008, POCKET_WIRECUTTER = 1010, POCKET_SCISSORS = 1012, POCKET_CHISEL = 1014
		, PISTOL = 5000
		;
		
		public static MultiItemTool sMetaTool;
		
		// If you plan do use this to detect Crafting Items, DON'T! Use OreDict for detecting Crafting Items being Tools!
		
		private static final ItemStackSet<ItemStackContainer> TOOL_LIST = new ItemStackSet<>();
		private static final Map<String, ItemStackSet<ItemStackContainer>> TOOL_LISTS = new HashMap<>();
		private static ItemStackSet<ItemStackContainer> get(String aToolType) {ItemStackSet<ItemStackContainer> rSet = TOOL_LISTS.get(aToolType); if (rSet == null) TOOL_LISTS.put(aToolType, rSet = new ItemStackSet<>()); return rSet;}
		public static boolean contains(String aToolType, ItemStack aStack) {return get(aToolType).contains(aStack, T);}
		public static boolean contains(String aToolType, ItemStackContainer aStack) {return get(aToolType).contains(aStack, T);}
		public static boolean add(String aToolType, ItemStackContainer aStack) {if (TOOL_LIST.add(aStack)) return get(aToolType).add(aStack); return F;}
		public static boolean add(ItemStackContainer aStack, String aToolType) {if (TOOL_LIST.add(aStack)) return get(aToolType).add(aStack); return F;}
		public static boolean add(String aToolType, ItemStack aStack) {if (TOOL_LIST.add(aStack)) return get(aToolType).add(aStack); return F;}
		public static boolean add(ItemStack aStack, String aToolType) {if (TOOL_LIST.add(aStack)) return get(aToolType).add(aStack); return F;}
	}
	
	public static class PlankData {
		public static ItemStack      PLANKS       [] = new ItemStack     [300];
		public static IIconContainer PLANK_ICONS  [] = new IIconContainer[300];
		public static PlankEntry     PLANK_ENTRIES[] = new PlankEntry    [300];
		
		static {
			PLANKS[0] = ST.make(Blocks.planks, 1, 0);
			Arrays.fill(PLANK_ICONS, new IconContainerCopied(Blocks.planks, 0, 0));
		}
	}
	
	/** Contains the Registry Stuff for my Sandwiches. */
	public static class Sandwiches {
		/** 
		 *   1 = 1 pixel distance from rim
		 *   2 = 2 pixel distance from rim
		 *   3 = 3 pixel distance from rim
		 *  
		 *  14 = 4 Slices of Stuff
		 * 
		 * Olive/Grape on a Toothpick maybe?
		 * 
		 * 252 = Condiment (copies roughly whatever is below it in shape)
		 * 253 = Toasted Toast
		 * 254 = Sandwich Toast
		 * 255 = NULL
		 */
		public static final byte    [] INGREDIENT_MODEL_IDS       = new byte    [256];
		public static final byte    [] INGREDIENT_MODEL_THICKNESS = new byte    [256];
		public static final ITexture[] INGREDIENT_TEXTURES_TOP    = new ITexture[256];
		public static final ITexture[] INGREDIENT_TEXTURES_SIDES  = new ITexture[256];
		
		public static final ItemStackMap<ItemStackContainer, Byte> INGREDIENTS = new ItemStackMap<>();
	}
	
	/** Contains the Registry Stuff for my Book Shelves. */
	public static class BooksGT {
		//   0 = null
		//   1 = Book/Written Book/Writable Book/Default
		//   2 = Enchanted Book
		//   3 = Black, 4 = White, 5 = Red, 6 = Green, 7 = Blue, 8 = Cyan, 9 = Magenta, 10 = Yellow, 48 = Orange, 49 = Purple, 53 = Dusty
		//  11 = Material Dictionary
		//  12 = GT Book
		//  13 = Thaumonomicon
		//  14 = Crimson Rites
		//  15 = Stone Tablets
		//  16 = Maps
		//  17 = Crafting
		//  18 = Scrolls
		//  19 = Rails
		//  20 = Wolves
		//  21 = Witches
		//  22 = Brewing
		//  23 = Vampires
		//  24 = Reika
		//  25 = Default Black Folder, 26 = Red Folder, 27 = Green Folder, 28 = Blue Folder
		//  29 = Clipboard
		//  30 = Records
		//  31 = Printing Plates
		//  32 = Catalogues
		//  33 = Letters
		//  34 = Frame
		//  35 = Floppy Disk
		//  36 = VHS Tape
		//  37 = ID Card
		//  38 = AE Press
		//  39 = FZ Manual
		//  40 = OC Manual
		//  41 = IE Manual
		//  42 = Lexica Botania
		//  43 = Metallic Tablet Computer
		//  44 = Golden Tablet Computer
		//  45 = Extruder Shapes
		//  46 = AE Cells
		//  47 = AE Handhelds
		//  48 = Orange, 49 = Purple
		//  50 = Division Sigil
		//  51 = XL Letters, 52 = XXL Letters
		//  53 = Dusty Books
		//  54 = Hard Drives
		//  55 = Simple Extruder Shapes
		//  56 = Thaumometer
		//  57 = Tape_White
		//  58 = Tape_Gray
		//  59 = Tape_Black
		// 255 = Stone (this one is supposed to just fill the Shelf)
		public static final ITexture[] BOOK_TEXTURES_BACK = new ITexture[256];
		public static final ITexture[] BOOK_TEXTURES_SIDE = new ITexture[256];
		
		public static final ItemStackMap<ItemStackContainer, Byte> BOOK_REGISTER = new ItemStackMap<>();
		
		public static final ItemStackSet<ItemStackContainer> BOOKS_NORMAL = new ItemStackSet<>();
		public static final ItemStackSet<ItemStackContainer> BOOKS_ENCHANTED = new ItemStackSet<>();
	}

	/** Contains typical Tool OreDict Names. */
	public static class OreDictToolNames {
		public static final String
		hac_mortar = "toolMortarandpestle",
		hac_cuttingboard = "toolCuttingboard",
		saw = "craftingToolSaw",
		hoe = "craftingToolHoe",
		axe = "craftingToolAxe",
		file = "craftingToolFile",
		plow = "craftingToolPlow",
		mixer = "craftingToolMixer",
		sword = "craftingToolSword",
		scoop = "craftingToolScoop",
		knife = "craftingToolKnife",
		blade = "craftingToolBlade",
		mortar = "craftingToolMortar",
		shovel = "craftingToolShovel",
		wrench = "craftingToolWrench",
		chisel = "craftingToolChisel",
		shears = "craftingToolShears",
		sawaxe = "craftingToolSawAxe",
		pincers = "craftingToolPincers",
		plunger = "craftingToolPlunger",
		crowbar = "craftingToolCrowbar",
		pickaxe = "craftingToolPickaxe",
		scissors = "craftingToolScissors",
		drawplate = "craftingToolDrawplate",
		drill = "craftingToolDrill",
		handdrill = "craftingToolHandDrill",
		rollingpin = "craftingToolRollingPin",
		wirecutter = "craftingToolWireCutter",
		hammer = "craftingToolHardHammer",
		softhammer = "craftingToolSoftHammer",
		jackhammer = "craftingToolJackHammer",
		forgehammer = "craftingToolForgeHammer",
		miningdrill = "craftingToolMiningDrill",
		screwdriver = "craftingToolScrewdriver",
		monkeywrench = "craftingToolMonkeyWrench",
		branchcutter = "craftingToolBranchCutter",
		solderingiron = "craftingToolSolderingIron",
		flintandtinder = "craftingToolFlintAndTinder",
		solderingmetal = "craftingToolSolderingMetal",
		magnifyingglass = "craftingToolMagnifyingGlass",
		bendingcylinder = "craftingToolBendingCylinder",
		bendingcylindersmall = "craftingToolBendingCylinderSmall";
	}

	/** Contains TextureSet Indices for non OreDictPrefixed Stuff. */
	public static class IconsGT {
		public static int INDEX_BLOCK_MOLTEN = 0, INDEX_BLOCK_GAS = 0, INDEX_BLOCK_PLASMA = 0, INDEX_BLOCK_PIPE_SIDE = 0;
	}

	/** Files */
	public static class DirectoriesGT {
		public static File
		MINECRAFT,
		LOGS,
		CONFIG,
		CONFIG_GT,
		CONFIG_RECIPES;
	}

	/** Configs */
	public static class ConfigsGT {
		public static Config
		CLIENT,
		RECIPES,
		GREGTECH,
		WORLDGEN,
		MATERIAL,
		OREPROCESSING;
		
		@Deprecated public static Config SPECIAL, MACHINES, OVERPOWERED;
	}

	/** Class Containing MOD ID Strings used in GT, since they are very common Parameters. */
	public static class ModIDs {
		/** MOD ID Strings */
		@SuppressWarnings("hiding")
		public static final String
		  MC                = "minecraft"
		
		, GT                = "gregtech"
		, GAPI              = "gregapi"
		, GAPI_POST         = "gregapi_post"
		
		, QT                = "qwertech"
		
		, WAILA             = "Waila"
		
		, IC2               = "IC2"
		, IC2C              = "IC2-Classic-Spmod"
		
		, NC                = "IC2NuclearControl"
		, IHL               = "ihl"
		
		, FMB               = "ForgeMicroblock"
		, TRANSLOCATOR      = "Translocator"
		, FUNK              = "funkylocomotion"
		, BAUBLES           = "Baubles"
		
		, TC                = "Thaumcraft"
		, TCFM              = "ForbiddenMagic"
		, TECHNOM           = "technom"
		, BOTA              = "Botania"
		, ALF               = "alfheim"
		, PE                = "ProjectE"
		, WTCH              = "witchery"
		, HOWL              = "howlingmoon"
		, TF                = "TwilightForest"
		, ERE               = "erebus"
		, ATUM              = "atum"
		, BTL               = "thebetweenlands"
		, AETHER            = "aether"
		, TROPIC            = "tropicraft"
		, MYST              = "Mystcraft"
		, WARPBOOK          = "warpbook"
		, ARS               = "arsmagica2"
		, CANDY             = "candycraftmod"
		, ABYSSAL           = "abyssalcraft"
		, SOULFOREST        = "soulforest"
		
		, RC                = "Railcraft"
		
		, IE                = "ImmersiveEngineering"
		
		, TE                = "ThermalExpansion"
		, TE_FOUNDATION     = "ThermalFoundation"
		, TE_DYNAMICS       = "ThermalDynamics"
		
		, AE                = "appliedenergistics2"
		, MO                = "mo"
		
		, TFC               = "terrafirmacraft"
		, TFCP              = "terrafirmacraftplus"
		
		, ZTONES            = "Ztones"
		, CHSL              = "chisel"
		
		, NePl              = "netheriteplus"
		, NeLi              = "netherlicious"
		, EtFu              = "etfuturum"
		, BB                = "betterbeginnings"
		, DYNAMIC_TREES     = "dynamictrees"
		
		, BbLC              = "BiblioCraft"
		, CARP              = "CarpentersBlocks"
		, BETTER_RECORDS    = "betterrecords"
		, ENCHIRIDION       = "Enchiridion"
		, ENCHIRIDION2      = "Enchiridion2"
		, LOSTBOOKS         = "LostBooks"
		, LOOTBAGS          = "lootbags"
		, EUREKA            = "eureka"
		
		, UB                = "UndergroundBiomes"
		, COG               = "CustomOreGen"
		, PFAA              = "PFAAGeologica"
		, MIN               = "mineralogy"
		, RH                = "globbypotato_rockhounding"
		
		, FR                = "Forestry"
		, FRMB              = "MagicBees"
		, BINNIE            = "BinnieCore"
		, BINNIE_BEE        = "ExtraBees"
		, BINNIE_TREE       = "ExtraTrees"
		, BINNIE_GENETICS   = "Genetics"
		, BINNIE_BOTANY     = "Botany"
		, BINNIE_PATCHER    = "BinniePatcher"
		
		, MFR               = "MineFactoryReloaded"
		, PnC               = "PneumaticCraft"
		, ExU               = "ExtraUtilities"
		, ExS               = "ExtraSimple"
		, EIO               = "EnderIO"
		, RT                = "RandomThings"
		, AA                = "ActuallyAdditions"
		
		, SD                = "StorageDrawers"
		, BTRS              = "betterstorage"
		, JABBA             = "JABBA"
		
		, MgC               = "Magneticraft"
		, BR                = "BigReactors"
		, HBM               = "hbm"
		, ELN               = "Eln"
		
		, DRGN              = "DragonAPI"
		, RoC               = "RotaryCraft"
		, ReC               = "ReactorCraft"
		, ElC               = "ElectriCraft"
		, CrC               = "ChromatiCraft"
		
		, VOLTZ             = "voltzengine"
		, MFFS              = "mffs"
		, ICBM              = "icbmclassic"
		, ATSCI             = "atomicscience"
		
		, Mek               = "Mekanism"
		, Mek_Tools         = "MekanismTools"
		, Mek_Generators    = "MekanismGenerators"
		
		, OC                = "OpenComputers"
		, CC                = "ComputerCraft"
		
		, TreeCap           = "Treecapitator"
		, HaC               = "harvestcraft"
		, CookBook          = "cookingbook"
		, APC               = "AppleCore"
		, HO                = "HungerOverhaul"
		, ENVM              = "enviromine"
		, MaCr              = "magicalcrops"
		, MaCu              = "Mariculture"
		, MoCr              = "MoCreatures"
		, GoG               = "GrimoireOfGaia"
		, PdC               = "psychedelicraft"
		, Bamboo            = "BambooMod"
		, PMP               = "plantmegapack"
		, Fossil            = "fossil"
		, GrC               = "Growthcraft"
		, GrC_Apples        = "Growthcraft|Apples"
		, GrC_Cellar        = "Growthcraft|Cellar"
		, GrC_Bamboo        = "Growthcraft|Bamboo"
		, GrC_Bees          = "Growthcraft|Bees"
		, GrC_Fish          = "Growthcraft|Fishtrap"
		, GrC_Grapes        = "Growthcraft|Grapes"
		, GrC_Hops          = "Growthcraft|Hops"
		, GrC_Milk          = "Growthcraft|Milk"
		, GrC_Rice          = "Growthcraft|Rice"
		
		, CrGu              = "craftguide"
		, SmAc              = "SimpleAchievements"
		, HQM               = "HardcoreQuesting"
		
		, HEX               = "hexcraft"
		, DE                = "DraconicEvolution"
		, AV                = "Avaritia"
		
		, EB                = "enhancedbiomes"
		, EBXL              = "ExtrabiomesXL"
		, BoP               = "BiomesOPlenty"
		, HiL               = "Highlands"
		
		, ATG               = "ATG"
		, RTG               = "RTG"
		, RWG               = "RWG"
		
		, A97               = "Aroma1997Core"
		, A97_MINING        = "Aroma1997sDimension"
		
		, CW2               = "caveworld"
		
		, GaSu              = "ganyssurface"
		, GaNe              = "ganysnether"
		, GaEn              = "ganysend"
		, WdSt              = "woodstuff"
		
		, HEE               = "HardcoreEnderExpansion"
		
		, LycM              = "lycanitesmobs"
		, LycM_Fresh        = "freshwatermobs"
		, LycM_Salt         = "saltwatermobs"
		, LycM_Swamp        = "swampmobs"
		, LycM_Plains       = "plainsmobs"
		, LycM_Forest       = "forestmobs"
		, LycM_Jungle       = "junglemobs"
		, LycM_Mountain     = "mountainmobs"
		, LycM_Desert       = "desertmobs"
		, LycM_Arctic       = "arcticmobs"
		, LycM_Inferno      = "infernomobs"
		, LycM_Demon        = "demonmobs"
		, LycM_Shadow       = "shadowmobs"
		
		, BC                = "BuildCraft|Core"
		, BC_SILICON        = "BuildCraft|Silicon"
		, BC_TRANSPORT      = "BuildCraft|Transport"
		, BC_FACTORY        = "BuildCraft|Factory"
		, BC_ENERGY         = "BuildCraft|Energy"
		, BC_BUILDERS       = "BuildCraft|Builders"
		, BC_ROBOTICS       = "BuildCraft|Robotics"
		
		, RP                = "Redpower"
		, BP                = "bluepower"
		, PR                = "ProjRed|Core"
		, PR_TRANSPORT      = "ProjRed|Transportation"
		, PR_INTEGRATION    = "ProjRed|Integration"
		, PR_EXPANSION      = "ProjRed|Expansion"
		, PR_TRANSMISSION   = "ProjRed|Transmission"
		, PR_EXPLORATION    = "ProjRed|Exploration"
		, PR_COMPATIBILITY  = "ProjRed|Compatibility"
		, PR_FABRICATION    = "ProjRed|Fabrication"
		, PR_ILLUMINATION   = "ProjRed|Illumination"
		
		, WR_CBE_C          = "WR-CBE|Core"
		, WR_CBE_A          = "WR-CBE|Addons"
		, WR_CBE_L          = "WR-CBE|Logic"
		
		, COFH_API          = "CoFHAPI"
		, COFH_API_ENERGY   = "CoFHAPI|energy"
		, COFH_CORE         = "CoFHCore"
		
		, OB                = "OpenBlocks"
		, PA                = "progressiveautomation"
		, MNTL              = "Mantle"
		, TiC               = "TConstruct"
		, Natura            = "Natura"
		, MF2               = "minefantasy2"
		, FZ                = "factorization"
		, BWM               = "weaponmod"
		, BG2               = "battlegear2"
		, OMT               = "openmodularturrets"
		, TG                = "Techguns"
		
		, FM                = "meteors"
		, GC                = "GalacticraftCore"
		, GC_PLANETS        = "GalacticraftMars"
		, GC_GALAXYSPACE    = "GalaxySpace"
		, GC_ADV_ROCKETRY   = "advancedRocketry"
		, VULPES            = "libVulpes"
		, MD8               = "Micdoodlecore"
		;
	}

	/** Class Containing Sound Strings used in GT. */
	public static class SFX {
		/** Sound Strings */
		public static final String
		  MC_BREAK              = "random.break"
		, MC_ANVIL_USE          = "random.anvil_use"
		, MC_ANVIL_BREAK        = "random.anvil_break"
		, MC_ANVIL_LAND         = "random.anvil_land"
		, MC_CLICK              = "random.click"
		, MC_COLLECT            = "random.pop"
		, MC_FIZZ               = "random.fizz"
		, MC_EXPLODE            = "random.explode"
		, MC_EAT                = "random.eat"
		, MC_DRINK              = "random.drink"
		, MC_XP                 = "random.orb"
		, MC_TNT_IGNITE         = "game.tnt.primed"
		, MC_IGNITE             = "fire.ignite"
		, MC_DIG_CLOTH          = "dig.cloth"
		, MC_DIG_ROCK           = "dig.stone"
		, MC_DIG_GRAVEL         = "dig.gravel"
		, MC_DIG_GRASS          = "dig.grass"
		, MC_DIG_SAND           = "dig.sand"
		, MC_DIG_WOOD           = "dig.wood"
		, MC_DIG_SNOW           = "dig.snow"
		, MC_FIREWORK_LAUNCH    = "fireworks.launch"
		, MC_FIREWORK_BLAST     = "fireworks.blast"
		, MC_FIREWORK_BLAST_FAR = "fireworks.blast_far"
		, MC_FIREWORK_LARGE     = "fireworks.largeBlast"
		, MC_FIREWORK_LARGE_FAR = "fireworks.largeBlast_far"
		, MC_LIQUID_WATER       = "liquid.water"
		, MC_HMM                = "mob.villager.idle"
		, MC_AHA                = "mob.villager.haggle"
		, MC_SHEARS             = "mob.sheep.shear"
		, MC_SLIME_BIG          = "mob.slime.big"
		, MC_SLIME_SMALL        = "mob.slime.small"

		, GT_BEEP               = MD.GAPI.mID.toLowerCase() + ":" + "gt.beep"
		, GT_WRENCH             = MD.GAPI.mID.toLowerCase() + ":" + "gt.wrench"
		, GT_SCREWDRIVER        = MD.GAPI.mID.toLowerCase() + ":" + "gt.screwdriver"

		, IC_WRENCH             = GT_WRENCH
		, IC_TREETAP            = MD.IC2.mID.toLowerCase() + ":" + "tools.Treetap"
		, IC_TRAMPOLINE         = MD.IC2.mID.toLowerCase() + ":" + "tools.RubberTrampoline"
		, IC_PAINT              = MD.IC2.mID.toLowerCase() + ":" + "tools.Painter"
		, IC_SPRAY              = MD.IC2.mID.toLowerCase() + ":" + "tools.Painter"
		, IC_BATTERYUSE         = MD.IC2.mID.toLowerCase() + ":" + "tools.BatteryUse"
		, IC_SOLDERING          = MD.IC2.mID.toLowerCase() + ":" + "tools.BatteryUse"
		, IC_CHAINSAW_01        = MD.IC2.mID.toLowerCase() + ":" + "tools.chainsaw.ChainsawUseOne"
		, IC_CHAINSAW_02        = MD.IC2.mID.toLowerCase() + ":" + "tools.chainsaw.ChainsawUseTwo"
		, IC_DRILL_SOFT         = MD.IC2.mID.toLowerCase() + ":" + "tools.drill.DrillSoft"
		, IC_DRILL_HARD         = MD.IC2.mID.toLowerCase() + ":" + "tools.drill.DrillHard"
		, IC_SCANNER            = MD.IC2.mID.toLowerCase() + ":" + "tools.ODScanner"

		, IC_MACHINE_OVERLOAD   = MC_EXPLODE
		, IC_MACHINE_INTERRUPT  = MC_BREAK
		, IC_MACHINE_KA_CHING   = MD.IC2.mID.toLowerCase() + ":" + "machines.KaChing"

		, MISSING = null;
	}
	
	public static final Map<String, String> TRANSFORMATION_POWDER_SPAWNER_MAP = new HashMap<>();
	
	static {
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Minotaur"          , "PigZombie"    );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Wild Deer"         , "Cow"          );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Bighorn Sheep"     , "Sheep"        );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Wild Boar"         , "Pig"          );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Forest Raven"      , "Bat"          );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Hostile Wolf"      , "Wolf"         );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Glacier Penguin"   , "Chicken"      );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Hedge Spider"      , "Spider"       );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Swarm Spider"      , "CaveSpider"   );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Twilight Wraith"   , "Blaze"        );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Redcap"            , "Zombie"       );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Skeleton Druid"    , "Witch"        );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Tower Termite"     , "Silverfish"   );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Redcap Sapper"     , "Creeper"      );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Mini Ghast"        , "Ghast"        );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Mist Wolf"         , "Enderman"     );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.King Spider"       , "Skeleton"     );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Slime Beetle"      , "Slime"        );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Fire Beetle"       , "LavaSlime"    );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Tower Golem"       , "VillagerGolem");
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Firefly"           , "Squid"        );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Redscale Broodling", "MushroomCow"  );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Pinch Beetle"      , "EntityHorse"  );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Forest Squirrel"   , "Ozelot"       );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("TwilightForest.Forest Bunny"      , "SnowMan"      );
		
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("PigZombie"    , "TwilightForest.Minotaur"          );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Cow"          , "TwilightForest.Wild Deer"         );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Sheep"        , "TwilightForest.Bighorn Sheep"     );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Pig"          , "TwilightForest.Wild Boar"         );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Bat"          , "TwilightForest.Forest Raven"      );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Wolf"         , "TwilightForest.Hostile Wolf"      );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Chicken"      , "TwilightForest.Glacier Penguin"   );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Spider"       , "TwilightForest.Hedge Spider"      );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("CaveSpider"   , "TwilightForest.Swarm Spider"      );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Blaze"        , "TwilightForest.Twilight Wraith"   );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Zombie"       , "TwilightForest.Redcap"            );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Witch"        , "TwilightForest.Skeleton Druid"    );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Silverfish"   , "TwilightForest.Tower Termite"     );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Creeper"      , "TwilightForest.Redcap Sapper"     );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Ghast"        , "TwilightForest.Mini Ghast"        );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Enderman"     , "TwilightForest.Mist Wolf"         );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Skeleton"     , "TwilightForest.King Spider"       );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Slime"        , "TwilightForest.Slime Beetle"      );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("LavaSlime"    , "TwilightForest.Fire Beetle"       );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("VillagerGolem", "TwilightForest.Tower Golem"       );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Squid"        , "TwilightForest.Firefly"           );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("MushroomCow"  , "TwilightForest.Redscale Broodling");
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("EntityHorse"  , "TwilightForest.Pinch Beetle"      );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("Ozelot"       , "TwilightForest.Forest Squirrel"   );
		TRANSFORMATION_POWDER_SPAWNER_MAP.put("SnowMan"      , "TwilightForest.Forest Bunny"      );
	}
	
	/** File Paths and Resource Paths */
	public static final String
	  TEX_DIR                   = "textures/"
	, TEX_DIR_GUI               = TEX_DIR + "gui/"
	, TEX_DIR_ITEM              = TEX_DIR + "items/"
	, TEX_DIR_MODEL             = TEX_DIR + "model/"
	, TEX_DIR_BLOCK             = TEX_DIR + "blocks/"
	, TEX_DIR_ARMOR             = TEX_DIR + "armor/"
	, TEX_DIR_ENTITY            = TEX_DIR + "entity/"
	, TEX_DIR_ASPECTS           = TEX_DIR + "aspects/"

	, RES_PATH                  = MD.GT.mID + ":" + TEX_DIR
	, RES_PATH_GUI              = MD.GT.mID + ":" + TEX_DIR_GUI
	, RES_PATH_ITEM             = MD.GT.mID + ":"
	, RES_PATH_BLOCK            = MD.GT.mID + ":"
	, RES_PATH_MODEL            = MD.GT.mID + ":" + TEX_DIR_MODEL
	, RES_PATH_ARMOR            = MD.GT.mID + ":" + TEX_DIR_ARMOR
	, RES_PATH_ENTITY           = MD.GT.mID + ":" + TEX_DIR_ENTITY
	, RES_PATH_API_ITEM         = MD.GAPI.mID + ":"
	, RES_PATH_API_BLOCK        = MD.GAPI.mID + ":"
	, RES_PATH_ASPECTS          = MD.GAPI.mID + ":" + TEX_DIR_ASPECTS

	, RES_PATH_IC2              = MD.IC2.mID.toLowerCase() + ":"
	;
}
