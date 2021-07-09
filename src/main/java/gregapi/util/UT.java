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

package gregapi.util;

import static gregapi.data.CS.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.*;
import java.util.Map.Entry;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.GT_API;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.code.ModData;
import gregapi.code.ObjectStack;
import gregapi.code.TagData;
import gregapi.damage.DamageSources;
import gregapi.data.ANY;
import gregapi.data.CS.ArmorsGT;
import gregapi.data.CS.FluidsGT;
import gregapi.data.CS.IconsGT;
import gregapi.data.CS.ItemsGT;
import gregapi.data.CS.PotionsGT;
import gregapi.data.FL;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.OD;
import gregapi.data.RM;
import gregapi.data.TC.TC_AspectStack;
import gregapi.data.TD;
import gregapi.enchants.Enchantment_Radioactivity;
import gregapi.fluid.FluidGT;
import gregapi.fluid.FluidTankGT;
import gregapi.item.IItemProjectile;
import gregapi.network.packets.PacketSound;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictMaterialStack;
import gregapi.oredict.configurations.IOreDictConfigurationComponent;
import gregapi.recipes.Recipe.RecipeMap;
import gregapi.render.IIconContainer;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.wooddict.WoodDictionary;
import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.IMachineRecipeManagerExt;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTBase.NBTPrimitive;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import twilightforest.TFAchievementPage;

/**
 * @author Gregorius Techneticies
 * 
 * Utility for accessing the random Utility Functions in a more short manner. The Short Name is for ease of overview and stands for "UtiliTy". :P
 */
public class UT {
	@Deprecated public static class Fluids {
		@Deprecated public static int id (IFluidTank aTank) {return aTank == null ? -1 : id_(aTank);}
		@Deprecated public static int id_(IFluidTank aTank) {return id(aTank.getFluid());}
		@Deprecated public static int id (FluidStack aFluid) {return aFluid == null ? -1 : id_(aFluid);}
		@Deprecated public static int id_(FluidStack aFluid) {return id(aFluid.getFluid());}
		@Deprecated public static int id (Fluid aFluid) {return aFluid == null ? -1 : id_(aFluid);}
		@Deprecated public static int id_(Fluid aFluid) {return FluidRegistry.getFluidID(aFluid);}
		
		@Deprecated public static Fluid fluid (int aID) {return aID < 0 ? null : FluidRegistry.getFluid(aID);}
		@Deprecated public static Fluid fluid (String aFluidName) {return Code.stringInvalid(aFluidName) ? null : fluid_(aFluidName);}
		@Deprecated public static Fluid fluid_(String aFluidName) {return FluidRegistry.getFluid(aFluidName);}
		
		@Deprecated public static boolean equal(FluidStack aFluid1, FluidStack aFluid2) {return equal(aFluid1, aFluid2, F);}
		@Deprecated public static boolean equal(FluidStack aFluid1, FluidStack aFluid2, boolean aIgnoreNBT) {return aFluid1 != null && aFluid2 != null && aFluid1.getFluid() == aFluid2.getFluid() && (aIgnoreNBT || ((aFluid1.tag == null) == (aFluid2.tag == null)) && (aFluid1.tag == null || aFluid1.tag.equals(aFluid2.tag)));}
		
		@Deprecated public static boolean is(IFluidTank aTank, String... aNames) {return is(aTank.getFluid(), aNames);}
		@Deprecated public static boolean is(FluidStack aFluid, String... aNames) {return aFluid != null && is(aFluid.getFluid(), aNames);}
		@Deprecated public static boolean is(Fluid aFluid, String... aNames) {if (aFluid != null) for (String aName : aNames) if (aFluid.getName().equalsIgnoreCase(aName)) return T; return F;}
		
		@Deprecated public static ItemStack display(Fluid aFluid) {return aFluid == null ? null : display(make(aFluid, 0), F, F);}
		@Deprecated public static ItemStack display(FluidStack aFluid, boolean aUseStackSize, boolean aLimitStackSize) {return display(aFluid, aFluid == null ? 0 : aFluid.amount, aUseStackSize, aLimitStackSize);}
		@Deprecated public static ItemStack display(FluidTankGT aTank, boolean aUseStackSize, boolean aLimitStackSize) {return display(aTank.getFluid(), aTank.amount(), aUseStackSize, aLimitStackSize);}
		@Deprecated public static ItemStack display(FluidStack aFluid, long aAmount, boolean aUseStackSize, boolean aLimitStackSize) {
			if (aFluid == null || aFluid.getFluid() == null) return null;
			ItemStack rStack = IL.Display_Fluid.getWithMeta(aUseStackSize ? aLimitStackSize ? UT.Code.bind7(aAmount / 1000) : aAmount / 1000 : 1, id_(aFluid));
			if (rStack == null) return null;
			NBTTagCompound tNBT = NBT.makeString("f", aFluid.getFluid().getName());
			if (aAmount != 0) NBT.setNumber(tNBT, "a", aAmount);
			NBT.setNumber(tNBT, "h", temperature(aFluid));
			NBT.setBoolean(tNBT, "s", gas(aFluid));
			return NBT.set(rStack, tNBT);
		}
		
		/** @return if that Liquid is Water or Distilled Water */
		@Deprecated public static boolean water(IFluidTank aFluid) {return aFluid != null && water(aFluid.getFluid());}
		/** @return if that Liquid is Water or Distilled Water */
		@Deprecated public static boolean water(FluidStack aFluid) {return aFluid != null && water(aFluid.getFluid());}
		/** @return if that Liquid is Water or Distilled Water */
		@Deprecated public static boolean water(Fluid aFluid) {return aFluid != null && (aFluid == FluidRegistry.WATER || FL.DistW.is(aFluid));}
		
		/** @return if that Liquid is distilled Water */
		@Deprecated public static boolean distw(IFluidTank aFluid) {return aFluid != null && distw(aFluid.getFluid());}
		/** @return if that Liquid is distilled Water */
		@Deprecated public static boolean distw(FluidStack aFluid) {return aFluid != null && distw(aFluid.getFluid());}
		/** @return if that Liquid is distilled Water */
		@Deprecated public static boolean distw(Fluid aFluid) {return aFluid != null && FL.DistW.is(aFluid);}
		
		/** @return if that Liquid is Lava */
		@Deprecated public static boolean lava(IFluidTank aFluid) {return aFluid != null && lava(aFluid.getFluid());}
		/** @return if that Liquid is Lava */
		@Deprecated public static boolean lava(FluidStack aFluid) {return aFluid != null && lava(aFluid.getFluid());}
		/** @return if that Liquid is Lava */
		@Deprecated public static boolean lava(Fluid aFluid) {return aFluid != null && aFluid == FluidRegistry.LAVA;}
		
		/** @return if that Liquid is Steam */
		@Deprecated public static boolean steam(IFluidTank aFluid) {return aFluid != null && steam(aFluid.getFluid());}
		/** @return if that Liquid is Steam */
		@Deprecated public static boolean steam(FluidStack aFluid) {return aFluid != null && steam(aFluid.getFluid());}
		/** @return if that Liquid is Steam */
		@Deprecated public static boolean steam(Fluid aFluid) {return aFluid != null && FL.Steam.is(aFluid);}
		
		/** @return if that Liquid is Milk */
		@Deprecated public static boolean milk(IFluidTank aFluid) {return aFluid != null && milk(aFluid.getFluid());}
		/** @return if that Liquid is Milk */
		@Deprecated public static boolean milk(FluidStack aFluid) {return aFluid != null && milk(aFluid.getFluid());}
		/** @return if that Liquid is Milk */
		@Deprecated public static boolean milk(Fluid aFluid) {return aFluid != null && (FL.Milk.is(aFluid) || FL.MilkGrC.is(aFluid));}
		
		/** @return if that Liquid is Soy Milk */
		@Deprecated public static boolean soym(IFluidTank aFluid) {return aFluid != null && soym(aFluid.getFluid());}
		/** @return if that Liquid is Soy Milk */
		@Deprecated public static boolean soym(FluidStack aFluid) {return aFluid != null && soym(aFluid.getFluid());}
		/** @return if that Liquid is Soy Milk */
		@Deprecated public static boolean soym(Fluid aFluid) {return aFluid != null && FL.MilkSoy.is(aFluid);}
		
		/** @return if that Liquid is Steam */
		@Deprecated public static boolean anysteam(IFluidTank aFluid) {return aFluid != null && steam(aFluid.getFluid());}
		/** @return if that Liquid is Steam */
		@Deprecated public static boolean anysteam(FluidStack aFluid) {return aFluid != null && steam(aFluid.getFluid());}
		/** @return if that Liquid is Steam */
		@Deprecated public static boolean anysteam(Fluid aFluid) {return aFluid != null && FluidsGT.STEAM.contains(aFluid.getName());}
		
		/** @return if that Liquid is supposed to be conducting Power */
		@Deprecated public static boolean powerconducting(IFluidTank aFluid) {return aFluid != null && powerconducting(aFluid.getFluid());}
		/** @return if that Liquid is supposed to be conducting Power */
		@Deprecated public static boolean powerconducting(FluidStack aFluid) {return aFluid != null && powerconducting(aFluid.getFluid());}
		/** @return if that Liquid is supposed to be conducting Power */
		@Deprecated public static boolean powerconducting(Fluid aFluid) {return aFluid != null && FluidsGT.POWER_CONDUCTING.contains(aFluid.getName());}
		
		/** @return if that Liquid is early-game and easy to handle */
		@Deprecated public static boolean simple(IFluidTank aFluid) {return aFluid != null && simple(aFluid.getFluid());}
		/** @return if that Liquid is early-game and easy to handle */
		@Deprecated public static boolean simple(FluidStack aFluid) {return aFluid != null && simple(aFluid.getFluid());}
		/** @return if that Liquid is early-game and easy to handle */
		@Deprecated public static boolean simple(Fluid aFluid) {return aFluid != null && FluidsGT.SIMPLE.contains(aFluid.getName());}
		
		@Deprecated public static boolean acid(IFluidTank aFluid) {return aFluid != null && acid(aFluid.getFluid());}
		@Deprecated public static boolean acid(FluidStack aFluid) {return aFluid != null && acid(aFluid.getFluid());}
		@Deprecated public static boolean acid(Fluid aFluid) {return aFluid != null && FluidsGT.ACID.contains(aFluid.getName());}
		
		@Deprecated public static boolean plasma(IFluidTank aFluid) {return aFluid != null && plasma(aFluid.getFluid());}
		@Deprecated public static boolean plasma(FluidStack aFluid) {return aFluid != null && plasma(aFluid.getFluid());}
		@Deprecated public static boolean plasma(Fluid aFluid) {return aFluid != null && FluidsGT.PLASMA.contains(aFluid.getName());}
		
		@Deprecated public static boolean gas(IFluidTank aFluid, boolean aDefault) {return gas(aFluid.getFluid(), aDefault);}
		@Deprecated public static boolean gas(IFluidTank aFluid) {return gas(aFluid.getFluid(), F);}
		@Deprecated public static boolean gas(FluidStack aFluid, boolean aDefault) {return aFluid == null || aFluid.getFluid() == null ? aDefault : !FluidsGT.LIQUID.contains(aFluid.getFluid().getName()) && (aFluid.getFluid().isGaseous(aFluid) || FluidsGT.GAS.contains(aFluid.getFluid().getName()));}
		@Deprecated public static boolean gas(FluidStack aFluid) {return gas(aFluid, F);}
		@Deprecated public static boolean gas(Fluid aFluid, boolean aDefault) {return aFluid == null ? aDefault : !FluidsGT.LIQUID.contains(aFluid.getName()) && (aFluid.isGaseous() || FluidsGT.GAS.contains(aFluid.getName()));}
		@Deprecated public static boolean gas(Fluid aFluid) {return gas(aFluid, F);}
		
		@Deprecated public static boolean lighter(BlockFluidBase aFluid) {return aFluid != null && lighter(aFluid.getFluid());}
		@Deprecated public static boolean lighter(IFluidTank aFluid)     {return aFluid != null && lighter(aFluid.getFluid());}
		@Deprecated public static boolean lighter(FluidStack aFluid)     {return aFluid != null && aFluid.getFluid() != null && aFluid.getFluid().getDensity(aFluid)<0;}
		@Deprecated public static boolean lighter(Fluid aFluid)          {return aFluid != null && aFluid.getDensity(make(aFluid, 1000)) < 0;}
		
		@Deprecated public static int dir(BlockFluidBase aFluid) {return lighter(aFluid) ? +1 : -1;}
		@Deprecated public static int dir(IFluidTank aFluid)     {return lighter(aFluid) ? +1 : -1;}
		@Deprecated public static int dir(FluidStack aFluid)     {return lighter(aFluid) ? +1 : -1;}
		@Deprecated public static int dir(Fluid aFluid)          {return lighter(aFluid) ? +1 : -1;}
		
		@Deprecated public static long temperature(IFluidTank aFluid) {return temperature(aFluid.getFluid());}
		@Deprecated public static long temperature(IFluidTank aFluid, long aDefault) {return temperature(aFluid.getFluid(), aDefault);}
		
		@Deprecated public static long temperature(Fluid aFluid) {return temperature(aFluid, DEF_ENV_TEMP);}
		@Deprecated public static long temperature(Fluid aFluid, long aDefault) {
			if (aFluid == null) return aDefault;
			if (aFluid.getName().equals("steam")) return C+100;
			return aFluid.getTemperature(make(aFluid, 1));
		}
		
		@Deprecated public static long temperature(FluidStack aFluid) {return temperature(aFluid, DEF_ENV_TEMP);}
		@Deprecated public static long temperature(FluidStack aFluid, long aDefault) {
			if (aFluid == null || aFluid.getFluid() == null) return aDefault;
			if (aFluid.getFluid().getName().equals("steam")) return C+100;
			return aFluid.getFluid().getTemperature(aFluid);
		}
		
		@Deprecated public static FluidStack water(long aAmount) {return make(FluidRegistry.WATER, aAmount);}
		@Deprecated public static FluidStack distw(long aAmount) {return make("ic2distilledwater", aAmount);}
		@Deprecated public static FluidStack lava(long aAmount) {return make(FluidRegistry.LAVA, aAmount);}
		@Deprecated public static FluidStack steam(long aAmount) {return make("steam", aAmount);}
		@Deprecated public static FluidStack milk(long aAmount) {return make("milk", aAmount);}
		@Deprecated public static FluidStack soym(long aAmount) {return make("soymilk", aAmount);}
		@Deprecated public static boolean distilledwater(FluidStack aFluid) {return distw(aFluid);}
		@Deprecated public static boolean distilledwater(Fluid aFluid) {return distw(aFluid);}
		@Deprecated public static FluidStack distilledwater(long aAmount) {return distw(aAmount);}
		@Deprecated public static boolean soymilk(FluidStack aFluid) {return soym(aFluid);}
		@Deprecated public static boolean soymilk(Fluid aFluid) {return soym(aFluid);}
		@Deprecated public static FluidStack soymilk(long aAmount) {return soym(aAmount);}
		
		@Deprecated public static boolean exists(String aFluidName) {return FluidRegistry.getFluid(aFluidName) != null;}
		
		@Deprecated public static FluidStack make (FL aFluid, long aAmount) {return aFluid.make (aAmount);}
		@Deprecated public static FluidStack make_(FL aFluid, long aAmount) {return aFluid.make_(aAmount);}
		@Deprecated public static FluidStack make (FL aFluid, long aAmount, FL aReplacementFluid) {return aFluid.make (aAmount, aReplacementFluid);}
		@Deprecated public static FluidStack make_(FL aFluid, long aAmount, FL aReplacementFluid) {return aFluid.make_(aAmount, aReplacementFluid);}
		@Deprecated public static FluidStack make (FL aFluid, long aAmount, String aReplacementFluidName) {return aFluid.make (aAmount, aReplacementFluidName);}
		@Deprecated public static FluidStack make_(FL aFluid, long aAmount, String aReplacementFluidName) {return aFluid.make_(aAmount, aReplacementFluidName);}
		@Deprecated public static FluidStack make (FL aFluid, long aAmount, FL aReplacementFluid, long aReplacementAmount) {return aFluid.make (aAmount, aReplacementFluid, aReplacementAmount);}
		@Deprecated public static FluidStack make_(FL aFluid, long aAmount, FL aReplacementFluid, long aReplacementAmount) {return aFluid.make_(aAmount, aReplacementFluid, aReplacementAmount);}
		@Deprecated public static FluidStack make (FL aFluid, long aAmount, String aReplacementFluidName, long aReplacementAmount) {return aFluid.make (aAmount, aReplacementFluidName, aReplacementAmount);}
		@Deprecated public static FluidStack make_(FL aFluid, long aAmount, String aReplacementFluidName, long aReplacementAmount) {return aFluid.make_(aAmount, aReplacementFluidName, aReplacementAmount);}
		
		@Deprecated public static FluidStack make (int aFluid, long aAmount) {return aFluid < 0 ? null : new FluidStack(fluid(aFluid), Code.bindInt(aAmount));}
		@Deprecated public static FluidStack make (Fluid aFluid, long aAmount) {return aFluid == null ? null : new FluidStack(aFluid, Code.bindInt(aAmount));}
		@Deprecated public static FluidStack make (String aFluidName, long aAmount) {return make(FluidRegistry.getFluid(aFluidName), aAmount);}
		@Deprecated public static FluidStack make (String aFluidName, long aAmount, String aReplacementFluidName) {FluidStack rFluid = make(aFluidName, aAmount); return rFluid == null ? make(aReplacementFluidName, aAmount) : rFluid;}
		@Deprecated public static FluidStack make (String aFluidName, long aAmount, String aReplacementFluidName, long aReplacementAmount) {FluidStack rFluid = make(aFluidName, aAmount); return rFluid == null ? make(aReplacementFluidName, aReplacementAmount) : rFluid;}
		@Deprecated public static FluidStack make (String aFluidName, long aAmount, FluidStack aReplacementFluid) {FluidStack rFluid = make(aFluidName, aAmount); return rFluid == null ? aReplacementFluid : rFluid;}
		
		@Deprecated public static FluidStack make_(int aFluid, long aAmount) {return aFluid < 0 ? FL.Error.make(0) : new FluidStack(fluid(aFluid), Code.bindInt(aAmount));}
		@Deprecated public static FluidStack make_(Fluid aFluid, long aAmount) {return aFluid == null ? FL.Error.make(0) : new FluidStack(aFluid, Code.bindInt(aAmount));}
		@Deprecated public static FluidStack make_(String aFluidName, long aAmount) {return make_(FluidRegistry.getFluid(aFluidName), aAmount);}
		@Deprecated public static FluidStack make_(String aFluidName, long aAmount, String aReplacementFluidName) {FluidStack rFluid = make(aFluidName, aAmount); return rFluid == null ? make_(aReplacementFluidName, aAmount) : rFluid;}
		@Deprecated public static FluidStack make_(String aFluidName, long aAmount, String aReplacementFluidName, long aReplacementAmount) {FluidStack rFluid = make(aFluidName, aAmount); return rFluid == null ? make_(aReplacementFluidName, aReplacementAmount) : rFluid;}
		
		@Deprecated public static FluidStack amount(FluidStack aFluid, long aAmount) {return aFluid == null ? null : new FluidStack(aFluid, Code.bindInt(aAmount));}
		
		@Deprecated public static FluidStack mul(FluidStack aFluid, long aMultiplier) {return aFluid == null ? null : amount(aFluid, aFluid.amount * aMultiplier);}
		@Deprecated public static FluidStack mul(FluidStack aFluid, long aMultiplier, long aDivider, boolean aRoundUp) {return aFluid == null ? null : amount(aFluid, Code.units(aFluid.amount, aDivider, aMultiplier, aRoundUp));}
		
		@Deprecated public static long fill (@SuppressWarnings("rawtypes") DelegatorTileEntity aDelegator, FluidStack aFluid, boolean aDoFill) {return aDelegator != null && aDelegator.mTileEntity instanceof IFluidHandler && aFluid != null ? fill_(aDelegator, aFluid, aDoFill) : 0;}
		@Deprecated public static long fill_(@SuppressWarnings("rawtypes") DelegatorTileEntity aDelegator, FluidStack aFluid, boolean aDoFill) {return fill_((IFluidHandler)aDelegator.mTileEntity, aDelegator.mSideOfTileEntity, aFluid, aDoFill);}
		@Deprecated public static long fill (IFluidHandler aFluidHandler, byte aSide, FluidStack aFluid, boolean aDoFill) {return aFluidHandler != null && aFluid != null ? fill_(aFluidHandler, aSide, aFluid, aDoFill) : 0;}
		@Deprecated public static long fill_(IFluidHandler aFluidHandler, byte aSide, FluidStack aFluid, boolean aDoFill) {return aFluidHandler.fill(FORGE_DIR[aSide], aFluid, aDoFill);}
		@Deprecated public static long fill (IFluidHandler aFluidHandler, byte[] aSides, FluidStack aFluid, boolean aDoFill) {return aFluidHandler != null && aFluid != null ? fill_(aFluidHandler, aSides, aFluid, aDoFill) : 0;}
		@Deprecated public static long fill_(IFluidHandler aFluidHandler, byte[] aSides, FluidStack aFluid, boolean aDoFill) {for (byte tSide : aSides) {long rFilled = aFluidHandler.fill(FORGE_DIR[tSide], aFluid, aDoFill); if (rFilled > 0) return rFilled;} return 0;}
		
		@Deprecated public static boolean fillAll (@SuppressWarnings("rawtypes") DelegatorTileEntity aDelegator, FluidStack aFluid, boolean aDoFill) {return aDelegator != null && aDelegator.mTileEntity instanceof IFluidHandler && aFluid != null && fillAll_(aDelegator, aFluid, aDoFill);}
		@Deprecated public static boolean fillAll_(@SuppressWarnings("rawtypes") DelegatorTileEntity aDelegator, FluidStack aFluid, boolean aDoFill) {return fillAll_((IFluidHandler)aDelegator.mTileEntity, aDelegator.mSideOfTileEntity, aFluid, aDoFill);}
		@Deprecated public static boolean fillAll (IFluidHandler aFluidHandler, byte aSide, FluidStack aFluid, boolean aDoFill) {return aFluidHandler != null && aFluid != null && fillAll_(aFluidHandler, aSide, aFluid, aDoFill);}
		@Deprecated public static boolean fillAll_(IFluidHandler aFluidHandler, byte aSide, FluidStack aFluid, boolean aDoFill) {return aFluidHandler.fill(FORGE_DIR[aSide], aFluid, F) == aFluid.amount && (!aDoFill || aFluidHandler.fill(FORGE_DIR[aSide], aFluid, T) > 0);}
		@Deprecated public static boolean fillAll (IFluidHandler aFluidHandler, byte[] aSides, FluidStack aFluid, boolean aDoFill) {return aFluidHandler != null && aFluid != null && fillAll_(aFluidHandler, aSides, aFluid, aDoFill);}
		@Deprecated public static boolean fillAll_(IFluidHandler aFluidHandler, byte[] aSides, FluidStack aFluid, boolean aDoFill) {for (byte tSide : aSides) if (aFluidHandler.fill(FORGE_DIR[tSide], aFluid, F) == aFluid.amount && (!aDoFill || aFluidHandler.fill(FORGE_DIR[tSide], aFluid, T) > 0)) return T; return F;}
		
		@Deprecated public static long move (@SuppressWarnings("rawtypes") DelegatorTileEntity aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move (aFrom, aTo, Integer.MAX_VALUE);}
		@Deprecated public static long move_(@SuppressWarnings("rawtypes") DelegatorTileEntity aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move_(aFrom, aTo, Integer.MAX_VALUE);}
		@Deprecated public static long move (@SuppressWarnings("rawtypes") DelegatorTileEntity aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {return aFrom != null && aFrom.mTileEntity instanceof IFluidHandler && aTo != null && aTo.mTileEntity instanceof IFluidHandler ? move_(aFrom, aTo, aMaxMoved) : 0;}
		@Deprecated public static long move_(@SuppressWarnings("rawtypes") DelegatorTileEntity aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {if (aMaxMoved <= 0) return 0; FluidStack tDrained = ((IFluidHandler)aFrom.mTileEntity).drain(aFrom.getForgeSideOfTileEntity(), UT.Code.bindInt(aMaxMoved), F); if (tDrained == null || tDrained.amount <= 0) return 0; tDrained.amount = Code.bindInt(UT.Fluids.fill_(aTo, tDrained.copy(), T)); if (tDrained.amount <= 0) return 0; ((IFluidHandler)aFrom.mTileEntity).drain(aFrom.getForgeSideOfTileEntity(), tDrained, T); return tDrained.amount;}
		@Deprecated public static long move (@SuppressWarnings("rawtypes") DelegatorTileEntity aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, FluidStack aMoved) {return aFrom != null && aFrom.mTileEntity instanceof IFluidHandler && aTo != null && aTo.mTileEntity instanceof IFluidHandler ? move_(aFrom, aTo, aMoved) : 0;}
		@Deprecated public static long move_(@SuppressWarnings("rawtypes") DelegatorTileEntity aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, FluidStack aMoved) {if (aMoved == null || aMoved.amount <= 0) return 0; FluidStack tDrained = ((IFluidHandler)aFrom.mTileEntity).drain(aFrom.getForgeSideOfTileEntity(), aMoved, F); if (tDrained == null || tDrained.amount <= 0) return 0; tDrained.amount = Code.bindInt(UT.Fluids.fill_(aTo, tDrained.copy(), T)); if (tDrained.amount <= 0) return 0; ((IFluidHandler)aFrom.mTileEntity).drain(aFrom.getForgeSideOfTileEntity(), tDrained, T); return tDrained.amount;}
		@Deprecated public static long move (IFluidTank aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move (aFrom, aTo, Integer.MAX_VALUE);}
		@Deprecated public static long move_(IFluidTank aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move_(aFrom, aTo, Integer.MAX_VALUE);}
		@Deprecated public static long move (IFluidTank aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {return aFrom != null && aTo != null && aTo.mTileEntity instanceof IFluidHandler ? move_(aFrom, aTo, aMaxMoved) : 0;}
		@Deprecated public static long move_(IFluidTank aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {if (aMaxMoved <= 0) return 0; FluidStack tDrained = aFrom.drain(UT.Code.bindInt(aMaxMoved), F); if (tDrained == null || tDrained.amount <= 0) return 0; tDrained.amount = Code.bindInt(UT.Fluids.fill_(aTo, tDrained.copy(), T)); if (tDrained.amount <= 0) return 0; aFrom.drain(tDrained.amount, T); return tDrained.amount;}
		@Deprecated public static long move (IFluidTank[] aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move (aFrom, aTo, Integer.MAX_VALUE);}
		@Deprecated public static long move_(IFluidTank[] aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move_(aFrom, aTo, Integer.MAX_VALUE);}
		@Deprecated public static long move (IFluidTank[] aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {return aFrom != null && aTo != null && aTo.mTileEntity instanceof IFluidHandler ? move_(aFrom, aTo, aMaxMoved) : 0;}
		@Deprecated public static long move_(IFluidTank[] aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {if (aMaxMoved <= 0) return 0; long rAmount = 0; for (IFluidTank tFrom : aFrom) if (tFrom != null) rAmount += move_(tFrom, aTo, aMaxMoved); return rAmount;}
		@Deprecated public static long move (@SuppressWarnings("rawtypes") Iterable aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move (aFrom, aTo, Integer.MAX_VALUE);}
		@Deprecated public static long move_(@SuppressWarnings("rawtypes") Iterable aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo) {return move_(aFrom, aTo, Integer.MAX_VALUE);}
		@Deprecated public static long move (@SuppressWarnings("rawtypes") Iterable aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {return aFrom != null && aTo != null && aTo.mTileEntity instanceof IFluidHandler ? move_(aFrom, aTo, aMaxMoved) : 0;}
		@Deprecated public static long move_(@SuppressWarnings("rawtypes") Iterable aFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, long aMaxMoved) {if (aMaxMoved <= 0) return 0; long rAmount = 0; for (Object tFrom : aFrom) if (tFrom instanceof IFluidTank) rAmount += move_((IFluidTank)tFrom, aTo, aMaxMoved); return rAmount;}
		
		
		@Deprecated public static String configName(FluidStack aFluid) {
			return aFluid == null || aFluid.getFluid() == null ? "" : aFluid.getFluid().getName();
		}
		
		@Deprecated public static String configNames(FluidStack... aFluids) {
			String rString = "";
			for (FluidStack tFluid : aFluids) rString += (tFluid == null ? "null;" : configName(tFluid) + ";");
			return rString;
		}
		
		@Deprecated public static String name(Fluid aFluid, boolean aLocalized) {
			if (aFluid == null) return "";
			if (!aLocalized) return aFluid.getUnlocalizedName();
			if (aFluid instanceof FluidGT) return LH.get(aFluid.getUnlocalizedName());
			String rName = aFluid.getLocalizedName(make(aFluid, 0));
			if (rName.contains("fluid.") || rName.contains("tile.")) return Code.capitalise(rName.replaceAll("fluid.", "").replaceAll("tile.", ""));
			return rName;
		}
		
		@Deprecated public static String name(FluidStack aFluid, boolean aLocalized) {
			return aFluid == null ? "" : name(aFluid.getFluid(), aLocalized);
		}
		
		@Deprecated public static String name(IFluidTank aTank, boolean aLocalized) {
			return aTank == null ? "" : name(aTank.getFluid(), aLocalized);
		}
		
		@Deprecated public static FluidStack[] copyArray(FluidStack... aFluids) {
			FluidStack[] rStacks = new FluidStack[aFluids.length];
			for (int i = 0; i < aFluids.length; i++) if (aFluids[i] != null) rStacks[i] = aFluids[i].copy();
			return rStacks;
		}
		
		@Deprecated public static final Map<ItemStackContainer, FluidContainerData> sFilled2Data = FL.FULL_TO_DATA;
		@Deprecated public static final Map<ItemStackContainer, Map<String, FluidContainerData>> sEmpty2Fluid2Data = FL.EMPTY_TO_FLUID_TO_DATA;
		
		@Deprecated public static void registerFluidContainer(FluidStack aFluid, ItemStack aFull, ItemStack aEmpty) {
			registerFluidContainer(aFluid, aFull, aEmpty, F);
		}
		@Deprecated public static void registerFluidContainer(FluidStack aFluid, ItemStack aFull, ItemStack aEmpty, boolean aOverrideFillingEmpty, boolean aOverrideDrainingFull) {
			registerFluidContainer(aFluid, aFull, aEmpty, F, aOverrideFillingEmpty, aOverrideDrainingFull);
		}
		@Deprecated public static void registerFluidContainer(FluidStack aFluid, ItemStack aFull, ItemStack aEmpty, boolean aNullEmpty) {
			registerFluidContainer(aFluid, aFull, aEmpty, aNullEmpty, F, F);
		}
		@Deprecated public static void registerFluidContainer(FluidStack aFluid, ItemStack aFull, ItemStack aEmpty, boolean aNullEmpty, boolean aOverrideFillingEmpty, boolean aOverrideDrainingFull) {
			if (aFluid == null || ST.invalid(aFull)) return;
			registerFluidContainer(new FluidContainerData(aFluid, aFull, aEmpty, aNullEmpty), aOverrideFillingEmpty, aOverrideDrainingFull);
		}
		@Deprecated public static void registerFluidContainer(FluidContainerData aData) {
			setFluidContainerData(aData);
			FluidContainerRegistry.registerFluidContainer(aData);
		}
		@Deprecated public static void registerFluidContainer(FluidContainerData aData, boolean aOverrideFillingEmpty, boolean aOverrideDrainingFull) {
			setFluidContainerData(aData, aOverrideFillingEmpty, aOverrideDrainingFull);
			FluidContainerRegistry.registerFluidContainer(aData);
		}
		
		@Deprecated public static void setFluidContainerData(FluidContainerData aData) {
			setFluidContainerData(aData, F, F);
		}
		
		@Deprecated public static void setFluidContainerData(FluidContainerData aData, boolean aOverrideFillingEmpty, boolean aOverrideDrainingFull) {
			ItemStackContainer tFilled = new ItemStackContainer(aData.filledContainer), tEmpty = new ItemStackContainer(aData.emptyContainer);
			if (aOverrideDrainingFull || !sFilled2Data.containsKey(tFilled)) sFilled2Data.put(tFilled, aData);
			Map<String, FluidContainerData> tFluidToData = sEmpty2Fluid2Data.get(tEmpty);
			if (tFluidToData == null) sEmpty2Fluid2Data.put(tEmpty, tFluidToData = new HashMap<>());
			String tFluidName = aData.fluid.getFluid().getName();
			if (aOverrideFillingEmpty || !tFluidToData.containsKey(tFluidName)) tFluidToData.put(tFluidName, aData);
		}
		
		@Deprecated public static ItemStack fillFluidContainer(FluidStack aFluid, ItemStack aStack, boolean aRemoveFluidDirectly, boolean aCheckIFluidContainerItems) {
			return fillFluidContainer(aFluid, aStack, aRemoveFluidDirectly, aCheckIFluidContainerItems, F, T);
		}
		@Deprecated public static ItemStack fillFluidContainer(FluidStack aFluid, ItemStack aStack, boolean aRemoveFluidDirectly, boolean aCheckIFluidContainerItems, boolean aAllowPartialFilling) {
			return fillFluidContainer(aFluid, aStack, aRemoveFluidDirectly, aCheckIFluidContainerItems, aAllowPartialFilling, T);
		}
		@Deprecated public static ItemStack fillFluidContainer(FluidStack aFluid, ItemStack aStack, boolean aRemoveFluidDirectly, boolean aCheckIFluidContainerItems, boolean aAllowPartialFilling, boolean aIsNonCannerCheck) {
			if (ST.invalid(aStack) || aFluid == null) return NI;
			if (aFluid.getFluid() == FluidRegistry.WATER && ST.equal(aStack, Items.glass_bottle)) {
				if (aFluid.amount >= 250) {
					if (aRemoveFluidDirectly) aFluid.amount -= 250;
					return ST.make(Items.potionitem, 1, 0);
				}
				return NI;
			}
			if (aIsNonCannerCheck && IL.GC_Canister.exists() && (IL.GC_Canister.equal(aStack, T, T) || ST.equal(ST.container(aStack, T), IL.GC_Canister.wild(1)))) return aStack;
			if (aCheckIFluidContainerItems && aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) > 0 && (((IFluidContainerItem)aStack.getItem()).getFluid(aStack) == null || (Fluids.equal(((IFluidContainerItem)aStack.getItem()).getFluid(aStack), aFluid) && ((IFluidContainerItem)aStack.getItem()).getFluid(aStack).amount < ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack))) && (aAllowPartialFilling || ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) <= aFluid.amount)) {
				if (IL.Cell_Universal_Fluid.equal(aStack, T, T) && (temperature(aFluid, DEF_ENV_TEMP) > MT.Sn.mMeltingPoint || !simple(aFluid) || acid(aFluid) || powerconducting(aFluid))) return aStack;
				if (aRemoveFluidDirectly)
					aFluid.amount -= ((IFluidContainerItem)aStack.getItem()).fill(aStack = ST.amount(1, aStack), aFluid, T);
				else
					((IFluidContainerItem)aStack.getItem()).fill(aStack = ST.amount(1, aStack), aFluid, T);
				return aStack;
			}
			Map<String, FluidContainerData> tFluidToContainer = sEmpty2Fluid2Data.get(new ItemStackContainer(aStack));
			if (tFluidToContainer == null) return NI;
			FluidContainerData tData = tFluidToContainer.get(aFluid.getFluid().getName());
			if (tData == null || tData.fluid.amount > aFluid.amount) return NI;
			if (aRemoveFluidDirectly) aFluid.amount -= tData.fluid.amount;
			return ST.amount(1, tData.filledContainer);
		}
		
		@Deprecated public static ItemStack fillFluidContainer(IFluidTank aTank, ItemStack aStack, boolean aRemoveFluidDirectly, boolean aCheckIFluidContainerItems) {
			return fillFluidContainer(aTank, aStack, aRemoveFluidDirectly, aCheckIFluidContainerItems, F, T);
		}
		@Deprecated public static ItemStack fillFluidContainer(IFluidTank aTank, ItemStack aStack, boolean aRemoveFluidDirectly, boolean aCheckIFluidContainerItems, boolean aAllowPartialFilling) {
			return fillFluidContainer(aTank, aStack, aRemoveFluidDirectly, aCheckIFluidContainerItems, aAllowPartialFilling, T);
		}
		@Deprecated public static ItemStack fillFluidContainer(IFluidTank aTank, ItemStack aStack, boolean aRemoveFluidDirectly, boolean aCheckIFluidContainerItems, boolean aAllowPartialFilling, boolean aIsNonCannerCheck) {
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
			if (aCheckIFluidContainerItems && aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) > 0 && (((IFluidContainerItem)aStack.getItem()).getFluid(aStack) == null || (Fluids.equal(((IFluidContainerItem)aStack.getItem()).getFluid(aStack), aFluid) && ((IFluidContainerItem)aStack.getItem()).getFluid(aStack).amount < ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack))) && (aAllowPartialFilling || ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) <= aFluid.amount)) {
				if (IL.Cell_Universal_Fluid.equal(aStack, T, T) && (temperature(aFluid, DEF_ENV_TEMP) > MT.Sn.mMeltingPoint || !simple(aFluid) || acid(aFluid) || powerconducting(aFluid))) return aStack;
				if (aRemoveFluidDirectly)
					aTank.drain(((IFluidContainerItem)aStack.getItem()).fill(aStack = ST.amount(1, aStack), aFluid, T), T);
				else
					((IFluidContainerItem)aStack.getItem()).fill(aStack = ST.amount(1, aStack), aFluid, T);
				return aStack;
			}
			Map<String, FluidContainerData> tFluidToContainer = sEmpty2Fluid2Data.get(new ItemStackContainer(aStack));
			if (tFluidToContainer == null) return NI;
			FluidContainerData tData = tFluidToContainer.get(aFluid.getFluid().getName());
			if (tData == null || tData.fluid.amount > aFluid.amount) return NI;
			if (aRemoveFluidDirectly) aTank.drain(tData.fluid.amount, T);
			return ST.amount(1, tData.filledContainer);
		}
		
		@Deprecated public static boolean containsFluid(ItemStack aStack, FluidStack aFluid, boolean aCheckIFluidContainerItems) {
			if (ST.invalid(aStack) || aFluid == null) return F;
			if (aCheckIFluidContainerItems && aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) > 0) return aFluid.isFluidEqual(((IFluidContainerItem)aStack.getItem()).getFluid(aStack = ST.amount(1, aStack)));
			FluidContainerData tData = sFilled2Data.get(new ItemStackContainer(aStack));
			return tData!=null && tData.fluid.isFluidEqual(aFluid);
		}
		
		@Deprecated public static FluidStack getFluidForFilledItem(ItemStack aStack, boolean aCheckIFluidContainerItems) {
			if (ST.invalid(aStack)) return null;
			if (aCheckIFluidContainerItems && aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) > 0) {
				FluidStack rFluid = ((IFluidContainerItem)aStack.getItem()).drain(ST.amount(1, aStack), Integer.MAX_VALUE, T);
				if (IL.Cell_Universal_Fluid.equal(aStack, T, T) && (temperature(rFluid, DEF_ENV_TEMP) > MT.Sn.mMeltingPoint || !simple(rFluid) || acid(rFluid) || powerconducting(rFluid))) return NF;
				return rFluid;
			}
			FluidContainerData tData = sFilled2Data.get(new ItemStackContainer(aStack));
			return tData==null?NF:tData.fluid.copy();
		}
		
		@Deprecated public static ItemStack getContainerForFilledItem(ItemStack aStack, boolean aCheckIFluidContainerItems) {
			if (ST.invalid(aStack)) return NI;
			FluidContainerData tData = sFilled2Data.get(new ItemStackContainer(aStack));
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
		@Deprecated public static FluidStack load (NBTTagCompound aNBT, String aTagName) {return aNBT == null ? null : load(aNBT.getCompoundTag(aTagName));}
		/** Loads a FluidStack properly. */
		@Deprecated public static FluidStack load (NBTTagCompound aNBT) {return aNBT == null || aNBT.hasNoTags() ? null : load_(aNBT);}
		/** Loads a FluidStack properly. */
		@Deprecated public static FluidStack load_(NBTTagCompound aNBT) {
			if (aNBT == null) return null;
			String aName = aNBT.getString("FluidName");
			if (Code.stringInvalid(aName)) return null;
			String tName = FluidsGT.FLUID_RENAMINGS.get(aName);
			if (Code.stringValid(tName)) aName = tName;
			Fluid aFluid = FluidRegistry.getFluid(aName);
			if (aFluid == null) return FL.LubRoCant.is(aName) ? FL.Lubricant.make(aNBT.getInteger("Amount")) : null;
			FluidStack rFluid = new FluidStack(aFluid, aNBT.getInteger("Amount"));
			if (aNBT.hasKey("Tag")) rFluid.tag = aNBT.getCompoundTag("Tag");
			return rFluid;
		}
		
		/** Saves a FluidStack properly. */
		@Deprecated public static NBTTagCompound save(NBTTagCompound aNBT, String aTagName, FluidStack aFluid) {
			if (aNBT == null) aNBT = NBT.make();
			NBTTagCompound tNBT = save(aFluid);
			if (tNBT != null) aNBT.setTag(aTagName, tNBT);
			return aNBT;
		}
		/** Saves a FluidStack properly. */
		@Deprecated public static NBTTagCompound save (FluidStack aFluid) {return aFluid == null || aFluid.getFluid() == null ? null : save_(aFluid);}
		/** Saves a FluidStack properly. */
		@Deprecated public static NBTTagCompound save_(FluidStack aFluid) {return aFluid.writeToNBT(NBT.make());}
		
		
		@Deprecated @SafeVarargs public static Fluid createLiquid(OreDictMaterial aMaterial, Set<String>... aFluidList) {return createLiquid(aMaterial, aMaterial.mTextureSetsBlock.get(IconsGT.INDEX_BLOCK_MOLTEN), aFluidList);}
		@Deprecated @SafeVarargs public static Fluid createLiquid(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return create(aMaterial.mNameInternal.toLowerCase(), aTexture, aMaterial.mNameLocal, aMaterial, aMaterial.mRGBaLiquid, STATE_LIQUID, 1000, aMaterial.mMeltingPoint <= 0 ? 1000 : aMaterial.mMeltingPoint < 300 ? Math.min(300, aMaterial.mBoilingPoint - 1) : aMaterial.mMeltingPoint, null, null, 0, aFluidList);}
		
		@Deprecated @SafeVarargs public static Fluid createMolten(OreDictMaterial aMaterial, Set<String>... aFluidList) {return createMolten(aMaterial, L, aFluidList);}
		@Deprecated @SafeVarargs public static Fluid createMolten(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return createMolten(aMaterial, L, aTexture, aFluidList);}
		@Deprecated @SafeVarargs public static Fluid createMolten(OreDictMaterial aMaterial, long aAmount, Set<String>... aFluidList) {return createMolten(aMaterial, aAmount, aMaterial.mTextureSetsBlock.get(IconsGT.INDEX_BLOCK_MOLTEN), aFluidList);}
		@Deprecated @SafeVarargs public static Fluid createMolten(OreDictMaterial aMaterial, long aAmount, IIconContainer aTexture, Set<String>... aFluidList) {return create("molten."+aMaterial.mNameInternal.toLowerCase(), aTexture, "Molten " + aMaterial.mNameLocal, aMaterial, aMaterial.mRGBaLiquid, STATE_LIQUID, aAmount, aMaterial.mMeltingPoint <= 0 ? 1000 : aMaterial.mMeltingPoint < 300 ? Math.min(300, aMaterial.mBoilingPoint - 1) : aMaterial.mMeltingPoint, null, null, 0, aFluidList).setLuminosity(10);}
		
		@Deprecated @SafeVarargs public static Fluid createGas(OreDictMaterial aMaterial, Set<String>... aFluidList) {return createGas(aMaterial, aMaterial.mTextureSetsBlock.get(IconsGT.INDEX_BLOCK_GAS), aFluidList);}
		@Deprecated @SafeVarargs public static Fluid createGas(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return create(aMaterial.mNameInternal.toLowerCase(), aTexture, aMaterial.mNameLocal, aMaterial, aMaterial.mRGBaGas, STATE_GASEOUS, 1000, aMaterial.mBoilingPoint <= 0 ? 3000 : aMaterial.mBoilingPoint < 300 ? Math.min(300, aMaterial.mPlasmaPoint - 1) : aMaterial.mBoilingPoint, null, null, 0, aFluidList);}
		
		@Deprecated @SafeVarargs public static Fluid createVapour(OreDictMaterial aMaterial, Set<String>... aFluidList) {return createVapour(aMaterial, aMaterial.mTextureSetsBlock.get(IconsGT.INDEX_BLOCK_GAS), aFluidList);}
		@Deprecated @SafeVarargs public static Fluid createVapour(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return create("vapor."+aMaterial.mNameInternal.toLowerCase(), aTexture, "Vaporized " + aMaterial.mNameLocal, aMaterial, aMaterial.mRGBaGas, STATE_GASEOUS, 8*L, aMaterial.mBoilingPoint <= 0 ? 3000 : aMaterial.mBoilingPoint < 300 ? Math.min(300, aMaterial.mPlasmaPoint - 1) : aMaterial.mBoilingPoint, null, null, 0, aFluidList);}
		
		@Deprecated @SafeVarargs public static Fluid createPlasma(OreDictMaterial aMaterial, Set<String>... aFluidList) {return createPlasma(aMaterial, aMaterial.mTextureSetsBlock.get(IconsGT.INDEX_BLOCK_PLASMA), aFluidList);}
		@Deprecated @SafeVarargs public static Fluid createPlasma(OreDictMaterial aMaterial, IIconContainer aTexture, Set<String>... aFluidList) {return create("plasma."+aMaterial.mNameInternal.toLowerCase(), aTexture, aMaterial.mNameLocal + " Plasma", aMaterial, aMaterial.mRGBaPlasma, STATE_PLASMA, L*L, aMaterial.mPlasmaPoint <= 0 ? 10000 : Math.max(300, aMaterial.mPlasmaPoint), null, null, 0, aFluidList);}
		
		@Deprecated @SafeVarargs public static Fluid create(String aName, String aLocalized, OreDictMaterial aMaterial, int aState, long aAmountPerUnit, long aTemperatureK, Set<String>... aFluidList) {return create(aName, aLocalized, aMaterial, aState, aAmountPerUnit, aTemperatureK, null, null, 0, aFluidList);}
		@Deprecated @SafeVarargs public static Fluid create(String aName, String aLocalized, OreDictMaterial aMaterial, int aState, long aAmountPerUnit, long aTemperatureK, ItemStack aFullContainer, ItemStack aEmptyContainer, int aFluidAmount, Set<String>... aFluidList) {return create(aName, new Textures.BlockIcons.CustomIcon("fluids/" + aName.toLowerCase()), aLocalized, aMaterial, null, aState, aAmountPerUnit, aTemperatureK, aFullContainer, aEmptyContainer, aFluidAmount, aFluidList);}
		
		@Deprecated @SafeVarargs
		public static Fluid create(String aName, IIconContainer aTexture, String aLocalized, OreDictMaterial aMaterial, short[] aRGBa, int aState, long aAmountPerUnit, long aTemperatureK, ItemStack aFullContainer, ItemStack aEmptyContainer, int aFluidAmount, Set<String>... aFluidList) {
			aName = aName.toLowerCase();
			Fluid rFluid = new FluidGT(aName, aTexture, aRGBa == null ? UNCOLOURED : aRGBa, aTemperatureK, aState == 2 || aState == 3);
			LH.add(rFluid.getUnlocalizedName(), aLocalized==null?aName:aLocalized);
			LH.add(rFluid.getUnlocalizedName()+".name", aLocalized==null?aName:aLocalized);
			
			for (Set<String> tSet : aFluidList) tSet.add(aName);
			
			switch (aState) {
			case STATE_SOLID:   rFluid.setViscosity(10000); break;
			case STATE_LIQUID:  rFluid.setViscosity( 1000); FluidsGT.LIQUID.add(aName); break;
			case STATE_GASEOUS: rFluid.setViscosity(  200); rFluid.setDensity(   -100); FluidsGT.GAS.add(aName); break;
			case STATE_PLASMA:  rFluid.setViscosity(   10); rFluid.setDensity(-100000); rFluid.setLuminosity(15); FluidsGT.PLASMA.add(aName); break;
			case 4:             rFluid.setViscosity( 1000); break;
			}
			
			if (!FluidRegistry.registerFluid(rFluid)) {
				rFluid = FluidRegistry.getFluid(aName);
				LH.add(rFluid.getUnlocalizedName(), aLocalized==null?aName:aLocalized);
				if (rFluid.getTemperature() == new Fluid("test").getTemperature() || rFluid.getTemperature() <= 0) rFluid.setTemperature(UT.Code.bindInt(aTemperatureK));
				rFluid.setGaseous(aState == 2 || aState == 3);
			}
			
			if (aMaterial != null) {
				if (aMaterial.contains(TD.Properties.ACID    )) FluidsGT.ACID.add(aName);
				if (aMaterial.contains(TD.Properties.GLOWING )) rFluid.setLuminosity(Math.max(rFluid.getLuminosity(), 5));
				if (aMaterial.contains(TD.Properties.LIGHTING)) rFluid.setLuminosity(Math.max(rFluid.getLuminosity(), 15));
				switch (aState) {
				case STATE_LIQUID:  aMaterial.liquid(UT.Fluids.make(rFluid, UT.Code.bindInt(aAmountPerUnit))); break;
				case STATE_GASEOUS: aMaterial.gas   (UT.Fluids.make(rFluid, UT.Code.bindInt(aAmountPerUnit))); break;
				case STATE_PLASMA:  aMaterial.plasma(UT.Fluids.make(rFluid, UT.Code.bindInt(aAmountPerUnit))); break;
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
			
			if (aFullContainer != null && aEmptyContainer != null && !FluidContainerRegistry.registerFluidContainer(UT.Fluids.make(rFluid, aFluidAmount), aFullContainer, aEmptyContainer)) {
				RM.Canner.addRecipe1(T, 16, Math.max(aFluidAmount / 64, 16), aFullContainer, NF, UT.Fluids.make(rFluid, aFluidAmount), ST.container(aFullContainer, F));
			}
			return rFluid;
		}
	}
	
	public static class Books {
		public static final Map<String, ItemStack> BOOK_MAP = new HashMap<>();
		public static final List<String> BOOK_LIST = new ArrayListNoNulls<>();
		public static final List<String> MATERIAL_DICTIONARIES = new ArrayListNoNulls<>();
		
		public static void display(EntityPlayer aPlayer, ItemStack aStack) {
			String aMapping = NBT.getBookMapping(aStack);
			if (Code.stringValid(aMapping)) display(aPlayer, aMapping); else display(aPlayer, F, aStack);
		}
		public static void display(EntityPlayer aPlayer, String aMapping) {
			aPlayer.displayGUIBook(getWrittenBook(aMapping, T, ST.make(Items.written_book, 1, 0)));
		}
		public static void display(EntityPlayer aPlayer, boolean aWritable, ItemStack aStack) {
			if (ST.invalid(aStack)) return;
			display(aPlayer, aWritable, aStack.getTagCompound());
		}
		public static void display(EntityPlayer aPlayer, boolean aWritable, NBTTagCompound aNBT) {
			if (aNBT == null || UT.Code.stringInvalid(UT.NBT.getBookTitle(aNBT))) return;
			aPlayer.displayGUIBook(ST.make(aWritable?Items.writable_book:Items.written_book, 1, 0, aNBT));
		}
		
		@Deprecated public static ItemStack getWrittenBook(String aMapping) {return getWrittenBook(aMapping, F, null);}
		@Deprecated public static ItemStack getWrittenBook(String aMapping, ItemStack aStackToPutNBT) {return getWrittenBook(aMapping, F, aStackToPutNBT);}
		
		public static ItemStack getWrittenBook(String aMapping, boolean aForceRecreation) {
			return getWrittenBook(aMapping, aForceRecreation, null);
		}
		public static ItemStack getWrittenBook(String aMapping, boolean aForceRecreation, ItemStack aStackToPutNBT) {
			if (Code.stringInvalid(aMapping)) return null;
			if (aForceRecreation && aMapping.startsWith("Material_Dictionary_")) UT.Books.createMaterialDictionary(OreDictMaterial.MATERIAL_MAP.get(aMapping.replaceFirst("Material_Dictionary_", "")), NI, NI);
			ItemStack tStack = BOOK_MAP.get(aMapping);
			if (tStack == null) return aStackToPutNBT==null?ST.make(Items.written_book, 1, 0):aStackToPutNBT;
			if (aStackToPutNBT == null) aStackToPutNBT = ST.copy(tStack);
			return NBT.set(aStackToPutNBT, (NBTTagCompound)tStack.getTagCompound().copy());
		}
		
		public static ItemStack getBookWithTitle(String aMapping) {
			return getBookWithTitle(aMapping, null);
		}
		public static ItemStack getBookWithTitle(String aMapping, ItemStack aStackToPutNBT) {
			ItemStack tStack = BOOK_MAP.get(aMapping);
			if (tStack == null) return aStackToPutNBT==null?ST.make(Items.written_book, 1, 0):aStackToPutNBT;
			if (aStackToPutNBT == null) aStackToPutNBT = ST.copy(tStack);
			return NBT.set(aStackToPutNBT, NBT.make("title", NBT.getBookTitle(tStack), "author", NBT.getBookAuthor(tStack), "book", aMapping));
		}
		
		public static ItemStack createWrittenBook(String aMapping, String aTitle, String aAuthor, ItemStack aDefaultBook, String... aPages) {
			return createWrittenBook(aMapping, aTitle, aAuthor, aDefaultBook, T, aPages);
		}
		public static ItemStack createWrittenBook(String aMapping, String aTitle, String aAuthor, ItemStack aDefaultBook, boolean aLogging, String... aPages) {
			if (Code.stringInvalid(aMapping)) return null;
			ItemStack rStack = BOOK_MAP.get(aMapping);
			if (rStack == null) rStack = aDefaultBook==null?ST.make(Items.written_book, 1, 0):ST.amount(1, aDefaultBook);
			if (Code.stringInvalid(aTitle) || Code.stringInvalid(aAuthor) || aPages.length <= 0) return null;
			NBTTagCompound rNBT = NBT.make();
			rNBT.setString("title", aTitle);
			rNBT.setString("author", aAuthor);
			NBTTagList tNBTList = new NBTTagList();
			for (short i = 0; i < aPages.length; i++) {
				if (aPages[i].length() < 256) tNBTList.appendTag(new NBTTagString(aPages[i])); else if (aLogging) ERR.println("WARNING: String for Page of written Book too long! ->\n" + aPages[i]);
			}
			rNBT.setTag("pages", tNBTList);
			NBT.set(rStack, rNBT);
			BOOK_MAP.put(aMapping, ST.copy(rStack));
			if (!BOOK_LIST.contains(aMapping)) BOOK_LIST.add(aMapping);
			return ST.copy(rStack);
		}
		
		public static ItemStack addMaterialDictionary(OreDictMaterial aMat) {
			boolean temp = F;
			int tCounter = 0, tPages = 6 + aMat.mAlloyCreationRecipes.size();
			
			if (aMat.mComponents == null && !aMat.contains(TD.Atomic.ELEMENT)) tPages--;
			
			if (!aMat.mByProducts.isEmpty()) tPages++;
			if (aMat.mToolTypes > 0 || !aMat.mEnchantmentTools.isEmpty() || !aMat.mEnchantmentArmors.isEmpty()) tPages++;
			if (aMat.mDescription != null) for (int i = 0; i < aMat.mDescription.length; i++) if (Code.stringValid(aMat.mDescription[i])) tPages++;
			
			for (TagData tTag : TD.Properties.ALL_RELEVANTS) if (aMat.contains(tTag)) {tPages++; break;}
			for (TagData tTag : TD.Processing.ALL_MACHINES ) if (aMat.contains(tTag)) {tPages++; break;}
			for (TagData tTag : TD.Processing.ALL_ORES     ) if (aMat.contains(tTag)) {tPages++; break;}
			
			tCounter = 0;
			for (OreDictMaterial tMat : OreDictMaterial.MATERIAL_MAP.values()) if (tMat.mComponents != null && tMat.contains(TD.Compounds.DECOMPOSABLE)) {
				for (OreDictMaterialStack tMt2 : tMat.mComponents.getUndividedComponents()) if (tMt2.mMaterial == aMat) {
					temp=!(tCounter++%6==5);
					if (!temp) tPages++;
					break;
				}
			}
			if (temp) {tPages++; temp=F;}
			
			tCounter = 0;
			for (OreDictMaterial tMat : OreDictMaterial.MATERIAL_MAP.values()) if (tMat.mByProducts.contains(aMat)) {
				temp=!(tCounter++%6==5);
				if (!temp) tPages++;
			}
			if (temp) {tPages++; temp=F;}
			
			tCounter = 0;
			for (OreDictMaterial tMat : aMat.mTargetedSmelting) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetSmelting.has(aMat)) {
				temp=!(tCounter++%6==5);
				if (!temp) tPages++;
			}
			if (temp) {tPages++; temp=F;}
			
			tCounter = 0;
			for (OreDictMaterial tMat : aMat.mTargetedSolidifying) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetSolidifying.has(aMat)) {
				temp=!(tCounter++%6==5);
				if (!temp) tPages++;
			}
			if (temp) {tPages++; temp=F;}
			
			tCounter = 0;
			for (OreDictMaterial tMat : aMat.mTargetedBurning) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetBurning.has(aMat)) {
				temp=!(tCounter++%6==5);
				if (!temp) tPages++;
			}
			if (temp) {tPages++; temp=F;}
			
			tCounter = 0;
			for (OreDictMaterial tMat : aMat.mTargetedPulver) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetPulver.has(aMat)) {
				temp=!(tCounter++%6==5);
				if (!temp) tPages++;
			}
			if (temp) {tPages++; temp=F;}
			
			tCounter = 0;
			for (OreDictMaterial tMat : aMat.mTargetedBending) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetBending.has(aMat)) {
				temp=!(tCounter++%6==5);
				if (!temp) tPages++;
			}
			if (temp) {tPages++; temp=F;}
			
			tCounter = 0;
			for (OreDictMaterial tMat : aMat.mTargetedCompressing) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetCompressing.has(aMat)) {
				temp=!(tCounter++%6==5);
				if (!temp) tPages++;
			}
			if (temp) {tPages++; temp=F;}
			
			tCounter = 0;
			for (OreDictMaterial tMat : aMat.mTargetedCrushing) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetCrushing.has(aMat)) {
				temp=!(tCounter++%6==5);
				if (!temp) tPages++;
			}
			if (temp) {tPages++; temp=F;}
			
			tCounter = 0;
			for (OreDictMaterial tMat : aMat.mTargetedCutting) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetCutting.has(aMat)) {
				temp=!(tCounter++%6==5);
				if (!temp) tPages++;
			}
			if (temp) {tPages++; temp=F;}
			
			tCounter = 0;
			for (OreDictMaterial tMat : aMat.mTargetedForging) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetForging.has(aMat)) {
				temp=!(tCounter++%6==5);
				if (!temp) tPages++;
			}
			if (temp) {tPages++; temp=F;}
			
			tCounter = 0;
			for (OreDictMaterial tMat : aMat.mTargetedSmashing) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetSmashing.has(aMat)) {
				temp=!(tCounter++%6==5);
				if (!temp) tPages++;
			}
			if (temp) {tPages++; temp=F;}
			
			tCounter = 0;
			for (OreDictMaterial tMat : aMat.mTargetedWorking) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetWorking.has(aMat)) {
				temp=!(tCounter++%6==5);
				if (!temp) tPages++;
			}
			if (temp) {tPages++; temp=F;}
			
			for (OreDictMaterial tMat : aMat.mAlloyComponentReferences) {
				for (IOreDictConfigurationComponent tConfig : tMat.mAlloyCreationRecipes) {
					for (OreDictMaterialStack tMatStack : tConfig.getUndividedComponents()) {
						if (tMatStack.mMaterial == aMat) {tPages++; break;}
					}
				}
			}
			
			MATERIAL_DICTIONARIES.add("Material_Dictionary_"+aMat.mNameInternal);
			createWrittenBook("Material_Dictionary_"+aMat.mNameInternal, aMat.getLocal(), "Material Dictionary Foundation", ST.make(ItemsGT.BOOKS, 1, tPages<=50?32002:32003), F, "If you can read this in a legitimate Material Dictionary, even if it is old, then this is a Bug, please report this to me!\n\nGregorius\nTechneticies\n\n2021");
			return ST.copy(aMat.mDictionaryBook = ST.book("Material_Dictionary_"+aMat.mNameInternal));
		}
		
		public static boolean createMaterialDictionary(OreDictMaterial aMat, ItemStack aDefaultBook, ItemStack aDefaultLargeBook) {
			if (aMat == null) return F;
			
			String tPage = "";
			List<String> tBook = new ArrayListNoNulls<>();
			boolean temp = F;
			int tCounter = 0;
			
			tBook.add("===================\n"+aMat.getLocal()+"\n===================\nID: "+(aMat.mID<0?"NONE":aMat.mID)+"\nMelting: "+aMat.mMeltingPoint+" K\nBoiling: "+aMat.mBoilingPoint+" K\nPlasma: "+aMat.mPlasmaPoint+" K\n===================\nDensity:\n"+(aMat.mGramPerCubicCentimeter == 0 ? "???" : aMat.mGramPerCubicCentimeter)+" g/cm3\n"+aMat.getWeight(U)+" kg/unit\n===================\n");
			
			//----------
			
			if (aMat.mComponents == null) {
				if (aMat.contains(TD.Atomic.ELEMENT)) {
					temp = T;
					tPage="Atomic Structure:\nProtons: "+aMat.mProtons+"\nElectrons: " + aMat.mElectrons + "\nNeutrons: " + aMat.mNeutrons + "\nMass: "+aMat.mMass+"\n===================\n";
					for (TagData tTag : TD.Atomic.ALL) if (tTag != TD.Atomic.ELEMENT && tTag != TD.Atomic.NONMETAL && aMat.contains(tTag)) tPage += tTag.getLocalisedNameLong() + "\n";
				}
			} else {
				temp = T;
				tPage="Components per "+aMat.mComponents.getCommonDivider() + "\n===================\n";
				for (OreDictMaterialStack tMaterial : aMat.mComponents.getUndividedComponents()) tPage += (tMaterial.mAmount / U)+" "+tMaterial.mMaterial.getLocal()+"\n";
			}
			
			if (temp) {tBook.add(tPage); temp = F;}
			
			//----------
			
			tPage="Tool Properties\n===================\n";
			
			if (aMat.mToolTypes > 0) {temp = T; tPage+="Durability:\n"+aMat.mToolDurability+"\nQuality:\n"+aMat.mToolQuality+"\nSpeed:\n"+aMat.mToolSpeed+"\nHandle:\n"+aMat.mHandleMaterial.getLocal()+"\n";}
			if (!aMat.mEnchantmentTools.isEmpty()) tPage += "Tool Enchantments:\n";
			for (ObjectStack<Enchantment> tEnchantment : aMat.mEnchantmentTools ) {temp = T; tPage += tEnchantment.mObject.getTranslatedName((int)tEnchantment.mAmount) + "\n";}
			if (!aMat.mEnchantmentArmors.isEmpty()) tPage += "Armor Enchantments:\n";
			for (ObjectStack<Enchantment> tEnchantment : aMat.mEnchantmentArmors) {temp = T; tPage += tEnchantment.mObject.getTranslatedName((int)tEnchantment.mAmount) + "\n";}
			
			if (temp) {tBook.add(tPage+"===================\n"); temp = F;}
			
			//----------
			
			tPage="Properties\n===================\n";
			
			for (TagData tTag : TD.Properties.ALL_RELEVANTS) if (aMat.contains(tTag)) {temp = T; tPage += tTag.getLocalisedNameLong() + "\n";}
			
			if (temp) {tBook.add(tPage); temp = F;}
			
			//----------
			
			tPage="Machine Processing\n===================\n";
			
			for (TagData tTag : TD.Processing.ALL_MACHINES) if (aMat.contains(tTag)) {temp = T; tPage += tTag.getLocalisedNameLong() + "\n";}
			
			if (temp) {tBook.add(tPage); temp = F;}
			
			//----------
			
			tPage="Materials which can be decomposed to this\n===================\n";
			tCounter = 0;
			for (OreDictMaterial tMat : OreDictMaterial.MATERIAL_MAP.values()) if (tMat.mComponents != null && tMat.contains(TD.Compounds.DECOMPOSABLE)) {
				for (OreDictMaterialStack tMt2 : tMat.mComponents.getUndividedComponents()) if (tMt2.mMaterial == aMat) {
					temp=!(tCounter++%6==5);
					tPage += tMat.getLocal()+"\n";
					if (!temp) {
						tBook.add(tPage);
						tPage="Materials which can be decomposed to this\n===================\n";
					}
					break;
				}
			}
			
			if (temp) {tBook.add(tPage); temp = F;}
			
			//----------
			
			tPage="Ore Processing\n===================\n";
			
			for (TagData tTag : TD.Processing.ALL_ORES) if (aMat.contains(tTag)) {temp = T; tPage += tTag.getLocalisedNameLong() + "\n";}
			
			if (temp) {tBook.add(tPage); temp = F;}
			
			//----------
			
			tPage="Ore Byproducts\n===================\n";
			
			for (OreDictMaterial tMat : aMat.mByProducts) {temp = T; tPage += tMat.getLocal() + "\n";}
			
			if (temp) {tBook.add(tPage); temp = F;}
			
			//----------
			
			tPage="Ores with this as Byproduct\n===================\n";
			tCounter = 0;
			for (OreDictMaterial tMat : OreDictMaterial.MATERIAL_MAP.values()) if (tMat.mByProducts.contains(aMat)) {
				temp=!(tCounter++%6==5);
				tPage += tMat.getLocal()+"\n";
				if (!temp) {
					tBook.add(tPage);
					tPage="Ores with this as Byproduct\n===================\n";
				}
			}
			
			if (temp) {tBook.add(tPage); temp = F;}
			
			//----------
			
			tPage = "Processing Data\n===================\n";
			tPage += "Smelting:\n"      +(aMat.mTargetSmelting   .mAmount / U) + "." + ((int)(((double)(aMat.mTargetSmelting   .mAmount % U) / (double)U) * 1000))+" "+(aMat.mTargetSmelting   .mAmount <= 0 ? "nothing" : aMat.mTargetSmelting   .mMaterial == aMat ? "itself" : aMat.mTargetSmelting   .mMaterial.getLocal())+"\n";
			tPage += "Solidifying:\n"   +(aMat.mTargetSolidifying.mAmount / U) + "." + ((int)(((double)(aMat.mTargetSolidifying.mAmount % U) / (double)U) * 1000))+" "+(aMat.mTargetSolidifying.mAmount <= 0 ? "nothing" : aMat.mTargetSolidifying.mMaterial == aMat ? "itself" : aMat.mTargetSolidifying.mMaterial.getLocal())+"\n";
			tPage += "Burning:\n"       +(aMat.mTargetBurning    .mAmount / U) + "." + ((int)(((double)(aMat.mTargetBurning    .mAmount % U) / (double)U) * 1000))+" "+(aMat.mTargetBurning    .mAmount <= 0 ? "nothing" : aMat.mTargetBurning    .mMaterial == aMat ? "itself" : aMat.mTargetBurning    .mMaterial.getLocal())+"\n";
			tPage += "Pulverising:\n"   +(aMat.mTargetPulver     .mAmount / U) + "." + ((int)(((double)(aMat.mTargetPulver     .mAmount % U) / (double)U) * 1000))+" "+(aMat.mTargetPulver     .mAmount <= 0 ? "nothing" : aMat.mTargetPulver     .mMaterial == aMat ? "itself" : aMat.mTargetPulver     .mMaterial.getLocal())+"\n";
			tPage += "Crushing:\n"      +(aMat.mTargetCrushing   .mAmount / U) + "." + ((int)(((double)(aMat.mTargetCrushing   .mAmount % U) / (double)U) * 1000))+" "+(aMat.mTargetCrushing   .mAmount <= 0 ? "nothing" : aMat.mTargetCrushing   .mMaterial == aMat ? "itself" : aMat.mTargetCrushing   .mMaterial.getLocal())+"\n";
			
			tBook.add(tPage);
			
			//----------
			
			tPage = "Processing Data\n===================\n";
			tPage += "Bending:\n"       +(aMat.mTargetBending    .mAmount / U) + "." + ((int)(((double)(aMat.mTargetBending    .mAmount % U) / (double)U) * 1000))+" "+(aMat.mTargetBending    .mAmount <= 0 ? "nothing" : aMat.mTargetBending    .mMaterial == aMat ? "itself" : aMat.mTargetBending    .mMaterial.getLocal())+"\n";
			tPage += "Compressing:\n"   +(aMat.mTargetCompressing.mAmount / U) + "." + ((int)(((double)(aMat.mTargetCompressing.mAmount % U) / (double)U) * 1000))+" "+(aMat.mTargetCompressing.mAmount <= 0 ? "nothing" : aMat.mTargetCompressing.mMaterial == aMat ? "itself" : aMat.mTargetCompressing.mMaterial.getLocal())+"\n";
			tPage += "Cutting:\n"       +(aMat.mTargetCutting    .mAmount / U) + "." + ((int)(((double)(aMat.mTargetCutting    .mAmount % U) / (double)U) * 1000))+" "+(aMat.mTargetCutting    .mAmount <= 0 ? "nothing" : aMat.mTargetCutting    .mMaterial == aMat ? "itself" : aMat.mTargetCutting    .mMaterial.getLocal())+"\n";
			tPage += "Forging:\n"       +(aMat.mTargetForging    .mAmount / U) + "." + ((int)(((double)(aMat.mTargetForging    .mAmount % U) / (double)U) * 1000))+" "+(aMat.mTargetForging    .mAmount <= 0 ? "nothing" : aMat.mTargetForging    .mMaterial == aMat ? "itself" : aMat.mTargetForging    .mMaterial.getLocal())+"\n";
			tPage += "Smashing:\n"      +(aMat.mTargetSmashing   .mAmount / U) + "." + ((int)(((double)(aMat.mTargetSmashing   .mAmount % U) / (double)U) * 1000))+" "+(aMat.mTargetSmashing   .mAmount <= 0 ? "nothing" : aMat.mTargetSmashing   .mMaterial == aMat ? "itself" : aMat.mTargetSmashing   .mMaterial.getLocal())+"\n";
			
			tBook.add(tPage);
			
			//----------
			
			tPage = "Processing Data\n===================\n";
			tPage += "Working:\n"       +(aMat.mTargetWorking    .mAmount / U) + "." + ((int)(((double)(aMat.mTargetWorking    .mAmount % U) / (double)U) * 1000))+" "+(aMat.mTargetWorking    .mAmount <= 0 ? "nothing" : aMat.mTargetWorking    .mMaterial == aMat ? "itself" : aMat.mTargetWorking    .mMaterial.getLocal())+"\n";
			
			tBook.add(tPage);
			
			//----------
			
			tPage = "Thaumaturgic Data\n===================\nAspects:\n";
			
			if (aMat.mAspects.isEmpty()) {
				tPage += "None\n";
			} else {
				for (TC_AspectStack tAspect : aMat.mAspects) tPage += tAspect.mAmount + "x " + tAspect.mAspect.mName + "\n";
			}
			
			tPage += "===================\n";
			
			tBook.add(tPage);
			
			//----------
			
			Map<OreDictMaterial, Long> tMap;
			
			tMap = new HashMap<>(); for (OreDictMaterial tMat : aMat.mTargetedSmelting) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetSmelting.has(aMat)) tMap.put(tMat, tMat.mTargetSmelting.mAmount);
			tMap = Code.sortByValuesDescending(tMap);
			tPage = "Resources to smelt for getting "+aMat.getLocal()+"\n===================\n";
			tCounter = 0;
			for (Entry<OreDictMaterial, Long> tEntry : tMap.entrySet()) {
				temp=!(tCounter++%6==5);
				tPage+=(tEntry.getValue() / U) + "." + ((int)(((double)(tEntry.getValue() % U) / (double)U) * 1000))+" from 1 "+tEntry.getKey().getLocal()+"\n";
				if (!temp) {
					tBook.add(tPage);
					tPage = "Resources to smelt for getting "+aMat.getLocal()+"\n===================\n";
				}
			}
			
			if (temp) {tBook.add(tPage); temp=F;}
			
			//----------
			
			tMap = new HashMap<>(); for (OreDictMaterial tMat : aMat.mTargetedSolidifying) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetSolidifying.has(aMat)) tMap.put(tMat, tMat.mTargetSolidifying.mAmount);
			tMap = Code.sortByValuesDescending(tMap);
			tPage = "Resources to smelt and solidify for getting "+aMat.getLocal()+"\n===================\n";
			tCounter = 0;
			for (Entry<OreDictMaterial, Long> tEntry : tMap.entrySet()) {
				temp=!(tCounter++%6==5);
				tPage+=(tEntry.getValue() / U) + "." + ((int)(((double)(tEntry.getValue() % U) / (double)U) * 1000))+" from 1 "+tEntry.getKey().getLocal()+"\n";
				if (!temp) {
					tBook.add(tPage);
					tPage = "Resources to smelt and solidify for getting "+aMat.getLocal()+"\n===================\n";
				}
			}
			
			if (temp) {tBook.add(tPage); temp=F;}
			
			//----------
			
			tMap = new HashMap<>(); for (OreDictMaterial tMat : aMat.mTargetedBurning) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetBurning.has(aMat)) tMap.put(tMat, tMat.mTargetBurning.mAmount);
			tMap = Code.sortByValuesDescending(tMap);
			tPage = "Resources to burn for getting "+aMat.getLocal()+"\n===================\n";
			tCounter = 0;
			for (Entry<OreDictMaterial, Long> tEntry : tMap.entrySet()) {
				temp=!(tCounter++%6==5);
				tPage+=(tEntry.getValue() / U) + "." + ((int)(((double)(tEntry.getValue() % U) / (double)U) * 1000))+" from 1 "+tEntry.getKey().getLocal()+"\n";
				if (!temp) {
					tBook.add(tPage);
					tPage = "Resources to burn for getting "+aMat.getLocal()+"\n===================\n";
				}
			}
			
			if (temp) {tBook.add(tPage); temp=F;}
			
			//----------
			
			tMap = new HashMap<>(); for (OreDictMaterial tMat : aMat.mTargetedPulver) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetPulver.has(aMat)) tMap.put(tMat, tMat.mTargetPulver.mAmount);
			tMap = Code.sortByValuesDescending(tMap);
			tPage = "Resources to pulverise for getting "+aMat.getLocal()+"\n===================\n";
			tCounter = 0;
			for (Entry<OreDictMaterial, Long> tEntry : tMap.entrySet()) {
				temp=!(tCounter++%6==5);
				tPage+=(tEntry.getValue() / U) + "." + ((int)(((double)(tEntry.getValue() % U) / (double)U) * 1000))+" from 1 "+tEntry.getKey().getLocal()+"\n";
				if (!temp) {
					tBook.add(tPage);
					tPage = "Resources to pulverise for getting "+aMat.getLocal()+"\n===================\n";
				}
			}
			
			if (temp) {tBook.add(tPage); temp=F;}
			
			//----------
			
			tMap = new HashMap<>(); for (OreDictMaterial tMat : aMat.mTargetedBending) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetBending.has(aMat)) tMap.put(tMat, tMat.mTargetBending.mAmount);
			tMap = Code.sortByValuesDescending(tMap);
			tPage = "Resources to bend for getting "+aMat.getLocal()+"\n===================\n";
			tCounter = 0;
			for (Entry<OreDictMaterial, Long> tEntry : tMap.entrySet()) {
				temp=!(tCounter++%6==5);
				tPage+=(tEntry.getValue() / U) + "." + ((int)(((double)(tEntry.getValue() % U) / (double)U) * 1000))+" from 1 "+tEntry.getKey().getLocal()+"\n";
				if (!temp) {
					tBook.add(tPage);
					tPage = "Resources to bend for getting "+aMat.getLocal()+"\n===================\n";
				}
			}
			
			if (temp) {tBook.add(tPage); temp=F;}
			
			//----------
			
			tMap = new HashMap<>(); for (OreDictMaterial tMat : aMat.mTargetedCompressing) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetCompressing.has(aMat)) tMap.put(tMat, tMat.mTargetCompressing.mAmount);
			tMap = Code.sortByValuesDescending(tMap);
			tPage = "Resources to compress for getting "+aMat.getLocal()+"\n===================\n";
			tCounter = 0;
			for (Entry<OreDictMaterial, Long> tEntry : tMap.entrySet()) {
				temp=!(tCounter++%6==5);
				tPage+=(tEntry.getValue() / U) + "." + ((int)(((double)(tEntry.getValue() % U) / (double)U) * 1000))+" from 1 "+tEntry.getKey().getLocal()+"\n";
				if (!temp) {
					tBook.add(tPage);
					tPage = "Resources to compress for getting "+aMat.getLocal()+"\n===================\n";
				}
			}
			
			if (temp) {tBook.add(tPage); temp=F;}
			
			//----------
			
			tMap = new HashMap<>(); for (OreDictMaterial tMat : aMat.mTargetedCrushing) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetCrushing.has(aMat)) tMap.put(tMat, tMat.mTargetCrushing.mAmount);
			tMap = Code.sortByValuesDescending(tMap);
			tPage = "Resources to crush for getting "+aMat.getLocal()+"\n===================\n";
			tCounter = 0;
			for (Entry<OreDictMaterial, Long> tEntry : tMap.entrySet()) {
				temp=!(tCounter++%6==5);
				tPage+=(tEntry.getValue() / U) + "." + ((int)(((double)(tEntry.getValue() % U) / (double)U) * 1000))+" from 1 "+tEntry.getKey().getLocal()+"\n";
				if (!temp) {
					tBook.add(tPage);
					tPage = "Resources to crush for getting "+aMat.getLocal()+"\n===================\n";
				}
			}
			
			if (temp) {tBook.add(tPage); temp=F;}
			
			//----------
			
			tMap = new HashMap<>(); for (OreDictMaterial tMat : aMat.mTargetedCutting) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetCutting.has(aMat)) tMap.put(tMat, tMat.mTargetCutting.mAmount);
			tMap = Code.sortByValuesDescending(tMap);
			tPage = "Resources to cut for getting "+aMat.getLocal()+"\n===================\n";
			tCounter = 0;
			for (Entry<OreDictMaterial, Long> tEntry : tMap.entrySet()) {
				temp=!(tCounter++%6==5);
				tPage+=(tEntry.getValue() / U) + "." + ((int)(((double)(tEntry.getValue() % U) / (double)U) * 1000))+" from 1 "+tEntry.getKey().getLocal()+"\n";
				if (!temp) {
					tBook.add(tPage);
					tPage = "Resources to cut for getting "+aMat.getLocal()+"\n===================\n";
				}
			}
			
			if (temp) {tBook.add(tPage); temp=F;}
			
			//----------
			
			tMap = new HashMap<>(); for (OreDictMaterial tMat : aMat.mTargetedForging) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetForging.has(aMat)) tMap.put(tMat, tMat.mTargetForging.mAmount);
			tMap = Code.sortByValuesDescending(tMap);
			tPage = "Resources to forge for getting "+aMat.getLocal()+"\n===================\n";
			tCounter = 0;
			for (Entry<OreDictMaterial, Long> tEntry : tMap.entrySet()) {
				temp=!(tCounter++%6==5);
				tPage+=(tEntry.getValue() / U) + "." + ((int)(((double)(tEntry.getValue() % U) / (double)U) * 1000))+" from 1 "+tEntry.getKey().getLocal()+"\n";
				if (!temp) {
					tBook.add(tPage);
					tPage = "Resources to forge for getting "+aMat.getLocal()+"\n===================\n";
				}
			}
			
			if (temp) {tBook.add(tPage); temp=F;}
			
			//----------
			
			tMap = new HashMap<>(); for (OreDictMaterial tMat : aMat.mTargetedSmashing) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetSmashing.has(aMat)) tMap.put(tMat, tMat.mTargetSmashing.mAmount);
			tMap = Code.sortByValuesDescending(tMap);
			tPage = "Resources to smash for getting "+aMat.getLocal()+"\n===================\n";
			tCounter = 0;
			for (Entry<OreDictMaterial, Long> tEntry : tMap.entrySet()) {
				temp=!(tCounter++%6==5);
				tPage+=(tEntry.getValue() / U) + "." + ((int)(((double)(tEntry.getValue() % U) / (double)U) * 1000))+" from 1 "+tEntry.getKey().getLocal()+"\n";
				if (!temp) {
					tBook.add(tPage);
					tPage = "Resources to smash for getting "+aMat.getLocal()+"\n===================\n";
				}
			}
			
			if (temp) {tBook.add(tPage); temp=F;}
			
			//----------
			
			tMap = new HashMap<>(); for (OreDictMaterial tMat : aMat.mTargetedWorking) if (tMat.mTargetRegistration == tMat && tMat != aMat && tMat.mTargetWorking.has(aMat)) tMap.put(tMat, tMat.mTargetWorking.mAmount);
			tMap = Code.sortByValuesDescending(tMap);
			tPage = "Resources to use in other ways for getting "+aMat.getLocal()+"\n===================\n";
			tCounter = 0;
			for (Entry<OreDictMaterial, Long> tEntry : tMap.entrySet()) {
				temp=!(tCounter++%6==5);
				tPage+=(tEntry.getValue() / U) + "." + ((int)(((double)(tEntry.getValue() % U) / (double)U) * 1000))+" from 1 "+tEntry.getKey().getLocal()+"\n";
				if (!temp) {
					tBook.add(tPage);
					tPage = "Resources to use in other ways for getting "+aMat.getLocal()+"\n===================\n";
				}
			}
			
			if (temp) {tBook.add(tPage); temp=F;}
			
			//----------
			
			for (IOreDictConfigurationComponent tConfig : aMat.mAlloyCreationRecipes) {
				tPage="Alloy:\n"+aMat.getLocal()+"\n===================\nMelting: "+aMat.mMeltingPoint+" K\nBoiling: "+aMat.mBoilingPoint+" K\n===================\nComponents per "+tConfig.getCommonDivider() + "\n";
				for (OreDictMaterialStack tMt2 : tConfig.getUndividedComponents()) tPage += (tMt2.mAmount / U)+" "+tMt2.mMaterial.getLocal()+"\n";
				tBook.add(tPage);
			}
			
			//----------
			
			for (OreDictMaterial tMat : aMat.mAlloyComponentReferences) {
				for (IOreDictConfigurationComponent tConfig : tMat.mAlloyCreationRecipes) {
					for (OreDictMaterialStack tMatStack : tConfig.getUndividedComponents()) {
						if (tMatStack.mMaterial == aMat) {
							tPage="Alloy:\n"+tMat.getLocal()+"\n===================\nMelting: "+tMat.mMeltingPoint+" K\nBoiling: "+tMat.mBoilingPoint+" K\n===================\nComponents per "+tConfig.getCommonDivider() + "\n";
							for (OreDictMaterialStack tMt2 : tConfig.getUndividedComponents()) tPage += (tMt2.mAmount / U)+" "+tMt2.mMaterial.getLocal()+"\n";
							tBook.add(tPage);
							break;
						}
					}
				}
			}
			
			//----------
			
			if (aMat.mDescription != null) for (int i = 0, j = 0; i < aMat.mDescription.length; i++) if (Code.stringValid(aMat.mDescription[i])) tBook.add("Description Pg "+(++j)+"\n===================\n" + aMat.mDescription[i]);
			
			//----------
			
			return null != createWrittenBook("Material_Dictionary_"+aMat.mNameInternal, aMat.getLocal(), "Material Dictionary Foundation", tBook.size()<=50?(ST.valid(aDefaultBook)?ST.amount(1, aDefaultBook):ST.make(ItemsGT.BOOKS, 1, 32002)):(ST.valid(aDefaultLargeBook)?ST.amount(1, aDefaultLargeBook):ST.make(ItemsGT.BOOKS, 1, 32003)), F, tBook.toArray(ZL_STRING));
		}
	}
	
	
	public static class Code {
		/** Note: Does not work on huge amounts of Bytes. */
		public static byte averageBytes(byte... aBytes) {
			if (aBytes == null || aBytes.length <= 0) return 0;
			return (byte)(sum(aBytes) / aBytes.length);
		}
		
		/** Note: Does not work on huge amounts of Bytes. */
		public static byte averageUnsignedBytes(byte... aBytes) {
			if (aBytes == null || aBytes.length <= 0) return 0;
			long rValue = 0;
			for (byte aByte : aBytes) rValue += unsignB(aByte);
			return (byte)(rValue / aBytes.length);
		}
		
		/** Note: Does not work on huge amounts of Bytes. */
		public static short averageShorts(short... aShorts) {
			if (aShorts == null || aShorts.length <= 0) return 0;
			return (short)(sum(aShorts) / aShorts.length);
		}
		
		/** Note: Does not work on huge amounts of Shorts. */
		public static short averageUnsignedShorts(short... aShorts) {
			if (aShorts == null || aShorts.length <= 0) return 0;
			long rValue = 0;
			for (short aShort : aShorts) rValue += unsignS(aShort);
			return (short)(rValue / aShorts.length);
		}
		
		/** Note: Does not work on huge amounts of Integers. */
		public static int averageInts(int... aInts) {
			if (aInts == null || aInts.length <= 0) return 0;
			return bindInt(sum(aInts) / aInts.length);
		}
		
		/** Note: Does not work on huge amounts of Integers. */
		public static int averageUnsignedInts(int... aInts) {
			if (aInts == null || aInts.length <= 0) return 0;
			long rValue = 0;
			for (int aInt : aInts) rValue += unsignI(aInt);
			return bindInt(rValue / aInts.length);
		}
		
		/** Note: Does not work on huge amounts of Longs. */
		public static long averageLongs(long... aLongs) {
			if (aLongs == null || aLongs.length <= 0) return 0;
			return sum(aLongs) / aLongs.length;
		}
		
		public static int roundDown(double aNumber) {
			int rRounded = (int)aNumber;
			return rRounded > aNumber ? rRounded-1 : rRounded;
		}
		public static int roundUp(double aNumber) {
			int rRounded = (int)aNumber;
			return rRounded < aNumber ? rRounded+1 : rRounded;
		}
		
		/** @return an unsigned representation of this Byte. */
		public static short unsignB(byte aByte) {
			return aByte < 0 ? (short)(aByte + 256) : aByte;
		}
		
		/** @return an unsigned representation of this Short. */
		public static int unsignS(short aShort) {
			return aShort < 0 ? aShort + 65536 : aShort;
		}
		
		/** @return an unsigned representation of this Integer. */
		public static long unsignI(int aInteger) {
			return aInteger < 0 ? aInteger + 4294967296L : aInteger;
		}
		
		public static byte toByteS(short aValue, int aIndex) {return (byte)(aValue >> (aIndex<<3));}
		public static byte toByteI(int   aValue, int aIndex) {return (byte)(aValue >> (aIndex<<3));}
		public static byte toByteL(long  aValue, int aIndex) {return (byte)(aValue >> (aIndex<<3));}
		
		public static short combine(byte aValue1, byte aValue2)                                                                                     {return (short) ((0xff & aValue1) | aValue2 << 8);}
		public static int   combine(byte aValue1, byte aValue2, byte aValue3, byte aValue4)                                                         {return          (0xff & aValue1) | (0xff & aValue2) << 8 | (0xff & aValue3) << 16 | aValue4 << 24;}
		public static long  combine(byte aValue1, byte aValue2, byte aValue3, byte aValue4, byte aValue5, byte aValue6, byte aValue7, byte aValue8) {return ((long)aValue1 & 0xff) | ((long)aValue2 & 0xff) << 8 | ((long)aValue3 & 0xff) << 16 | ((long)aValue4 & 0xff) << 24 | ((long)aValue5 & 0xff) << 32 | ((long)aValue6 & 0xff) << 40 | ((long)aValue7 & 0xff) << 48 | (long)aValue8 << 56;}
		
		public static long getBits(boolean... aBits) {
			long rBits = 0;
			for (int i = 0; i < 64 && i < aBits.length; i++) if (aBits[i]) rBits |= (1 << i);
			return rBits;
		}
		
		public static boolean[] getBitsB(byte aBits) {
			boolean[] rBits = new boolean[8];
			for (int i = 0; i < rBits.length; i++) if ((aBits & (1 << i)) != 0) rBits[i] = T;
			return rBits;
		}
		
		public static boolean[] getBitsS(short aBits) {
			boolean[] rBits = new boolean[16];
			for (int i = 0; i < rBits.length; i++) if ((aBits & (1 << i)) != 0) rBits[i] = T;
			return rBits;
		}
		
		public static boolean[] getBitsI(int aBits) {
			boolean[] rBits = new boolean[32];
			for (int i = 0; i < rBits.length; i++) if ((aBits & (1 << i)) != 0) rBits[i] = T;
			return rBits;
		}
		
		public static boolean[] getBitsL(long aBits) {
			boolean[] rBits = new boolean[64];
			for (int i = 0; i < rBits.length; i++) if ((aBits & (1 << i)) != 0) rBits[i] = T;
			return rBits;
		}
		
		public static ItemStack toStack(int aStack) {
			Item tItem = Item.getItemById(aStack&(~0>>>16));
			if (tItem != null) return ST.make(tItem, 1, aStack>>>16);
			return null;
		}
		
		public static UUID getUUID(byte[] aData, int aOffset) {
			return aData.length - aOffset < 16 ? null : new UUID(combine(aData[aOffset], aData[aOffset+1], aData[aOffset+2], aData[aOffset+3], aData[aOffset+4], aData[aOffset+5], aData[aOffset+6], aData[aOffset+7]), combine(aData[aOffset+8], aData[aOffset+9], aData[aOffset+10], aData[aOffset+11], aData[aOffset+12], aData[aOffset+13], aData[aOffset+14], aData[aOffset+15]));
		}
		
		public static byte[] getBytes(UUID aData, int aOffset) {
			if (aData == null) return new byte[aOffset];
			byte[] rData = new byte[aOffset+16];
			for (int i = 0; i < 8; i++) {
				rData[aOffset+  i] = toByteL(aData.getMostSignificantBits(), i);
				rData[aOffset+8+i] = toByteL(aData.getLeastSignificantBits(), i);
			}
			return rData;
		}
		
		/** Converts a Number to a String with Underscores as Decimal Separators. Ignores Numbers with 4 Digits or less. */
		public static String makeString(long aNumber) {
			if (aNumber > -10000 && aNumber < 10000) return Long.toString(aNumber);
			StringBuilder rString = new StringBuilder();
			if (aNumber < 0) {
				aNumber *= -1;
				rString.append('-');
			}
			boolean temp = T;
			for (long i = 1000000000000000000L; i > 0; i /= 10) {
				long tDigit = (aNumber / i) % 10;
				if ( temp && tDigit != 0) temp = F;
				if (!temp) {
					rString.append(tDigit);
					if (i != 1) for (long j = i; j > 0; j /= 1000) if (j == 1) rString.append('_');
				}
			}
			return rString.toString();
		}
		
		@SafeVarargs
		public static <E> boolean contains(E aTarget, E... aArray) {
			if (aArray != null) for (E tValue : aArray) if (tValue == aTarget || (tValue != null && aTarget != null && tValue.equals(aTarget))) return T;
			return F;
		}
		
		public static boolean containsBoolean(boolean aTarget, boolean... aArray) {
			if (aArray != null) for (boolean tValue : aArray) if (tValue == aTarget) return T;
			return F;
		}
		
		@SafeVarargs
		public static <E> boolean containsSomething(E... aArray) {
			if (aArray != null) for (Object tObject : aArray) if (tObject != null) return T;
			return F;
		}
		
		public static <E> E[] fill(E aToFillIn, E[] rArray) {
			for (int i = 0; i < rArray.length; i++) rArray[i] = aToFillIn;
			return rArray;
		}
		
		@SafeVarargs
		public static <E> E[] makeArray(E[] rArray, E... aArray) {
			for (int i = 0; i < rArray.length && i < aArray.length; i++) rArray[i] = aArray[i];
			return rArray;
		}
		
		@SafeVarargs
		public static <E> long getNonNulls(E... aArray) {
			long rAmount = 0;
			if (aArray != null) for (Object tObject : aArray) if (tObject != null) rAmount++;
			return rAmount;
		}
		
		@SafeVarargs
		public static <E> ArrayList<E> getWithoutNulls(E... aArray) {
			if (aArray == null) return new ArrayListNoNulls<>();
			ArrayList<E> rList = new ArrayListNoNulls<>(Arrays.asList(aArray));
			return rList;
		}
		
		@SafeVarargs
		public static <E> ArrayList<E> getWithoutTrailingNulls(E... aArray) {
			if (aArray == null) return new ArrayList<>(1);
			ArrayList<E> rList = new ArrayList<>(Arrays.asList(aArray));
			for (int i = rList.size() - 1; i >= 0 && rList.get(i) == null;) rList.remove(i--);
			return rList;
		}
		
		@SafeVarargs
		public static <E> E getFirstNonNull(E... aArray) {
			if (aArray != null) for (E tObject : aArray) if (tObject != null) return tObject;
			return null;
		}
		
		private static final DateFormat sDateFormat = DateFormat.getInstance();
		public static String dateAndTime() {
			return sDateFormat.format(new Date());
		}
		
		public static byte tier(long aSize) {
			return tierMax(aSize);
		}
		
		public static byte tierMax(long aSize) {
			byte i = -1;
			aSize = Math.abs(aSize);
			while (++i < V.length) if (aSize <= V[i]) return i;
			return i;
		}
		
		public static byte tierMin(long aSize) {
			byte i = -1;
			aSize = Math.abs(aSize);
			while (++i < V.length) if (aSize < V[i]) return (byte)Math.max(0, i-1);
			return --i;
		}
		
		public static long voltMax(long aSize) {
			aSize = Math.abs(aSize);
			for (int i = 0; i < VMAX.length; i++) if (aSize < VMAX[i]) return VMAX[i];
			return VMAX[VMAX.length-1];
		}
		
		public static long voltMin(long aSize) {
			aSize = Math.abs(aSize);
			for (int i = 0; i < VMAX.length; i++) if (aSize < VMAX[i]) return VMIN[i];
			return VMIN[VMIN.length-1];
		}
		
		public static boolean haveOneCommonElement(Iterable<?> aCollection1, Collection<?> aCollection2) {
			if (aCollection1 == aCollection2) return T;
			for (Object tObject : aCollection1) if (aCollection2.contains(tObject)) return T;
			return F;
		}
		
		/** re-maps all Keys of a Map after the Keys were weakened. */
		public static <X, Y> Map<X, Y> reMap(Map<X, Y> aMap) {
			Map<X, Y> tMap = new HashMap<>();
			tMap.putAll(aMap);
			aMap.clear();
			aMap.putAll(tMap);
			return aMap;
		}
		
		/** re-maps all Keys of a (Hash)-Set after the Keys were weakened. */
		public static <X> Set<X> reMap(Set<X> aSet) {
			Set<X> tSet = new HashSet<>();
			tSet.addAll(aSet);
			aSet.clear();
			aSet.addAll(aSet);
			return aSet;
		}
		
		/** Why the fuck do neither Java nor Guava have a Function to do this? */
		@SuppressWarnings("rawtypes")
		public static <X, Y extends Comparable> LinkedHashMap<X,Y> sortByValuesAcending(Map<X,Y> aMap) {
			List<Map.Entry<X,Y>> tEntrySet = new LinkedList<>(aMap.entrySet());
			Collections.sort(tEntrySet, new Comparator<Map.Entry<X,Y>>() {@SuppressWarnings("unchecked") @Override public int compare(Entry<X, Y> aValue1, Entry<X, Y> aValue2) {return aValue1.getValue().compareTo(aValue2.getValue());}});
			LinkedHashMap<X,Y> rMap = new LinkedHashMap<>();
			for (Map.Entry<X,Y> tEntry : tEntrySet) rMap.put(tEntry.getKey(), tEntry.getValue());
			return rMap;
		}
		
		/** Why the fuck do neither Java nor Guava have a Function to do this? */
		@SuppressWarnings("rawtypes")
		public static <X, Y extends Comparable> LinkedHashMap<X,Y> sortByValuesDescending(Map<X,Y> aMap) {
			List<Map.Entry<X,Y>> tEntrySet = new LinkedList<>(aMap.entrySet());
			Collections.sort(tEntrySet, new Comparator<Map.Entry<X,Y>>() {@SuppressWarnings("unchecked") @Override public int compare(Entry<X, Y> aValue1, Entry<X, Y> aValue2) {return -aValue1.getValue().compareTo(aValue2.getValue());}});
			LinkedHashMap<X,Y> rMap = new LinkedHashMap<>();
			for (Map.Entry<X,Y> tEntry : tEntrySet) rMap.put(tEntry.getKey(), tEntry.getValue());
			return rMap;
		}
		
		public static <E> E select(long aIndex, E aReplacement, List<E> aList) {
			if (aList == null || aList.isEmpty()) return aReplacement;
			if (aList.size() <= aIndex) return aList.get(aList.size() - 1);
			if (aIndex < 0) return aList.get(0);
			return aList.get((int)aIndex);
		}
		
		public static <E> E select(E aReplacement, List<E> aList) {
			if (aList == null || aList.isEmpty()) return aReplacement;
			return aList.get(RNGSUS.nextInt(aList.size()));
		}
		
		@SafeVarargs
		public static <E> E select(long aIndex, E aReplacement, E... aArray) {
			if (aArray == null || aArray.length <= 0) return aReplacement;
			if (aArray.length <= aIndex) return aArray[aArray.length - 1];
			if (aIndex < 0) return aArray[0];
			return aArray[(int)aIndex];
		}
		
		@SafeVarargs
		public static <E> E select(E aReplacement, E... aArray) {
			if (aArray == null || aArray.length <= 0) return aReplacement;
			return aArray[RNGSUS.nextInt(aArray.length)];
		}
		
		public static boolean inArray(Object aObject, Object... aObjects) {
			return inList(aObject, Arrays.asList(aObjects));
		}
		
		public static boolean inList(Object aObject, Collection<?> aObjects) {
			if (aObjects == null) return F;
			return aObjects.contains(aObject);
		}
		
		public static final int[][] ASCENDING_ARRAYS = new int[1024][];
		
		public static int[] getAscendingArray(int aLength) {
			if (aLength <= 0) return ZL_INTEGER;
			if (aLength < ASCENDING_ARRAYS.length) {
				if (ASCENDING_ARRAYS[aLength] == null) {
					ASCENDING_ARRAYS[aLength] = new int[aLength];
					for (int i = 0; i < aLength; i++) ASCENDING_ARRAYS[aLength][i] = i;
				}
				return ASCENDING_ARRAYS[aLength];
			}
			int[] rArray = new int[aLength];
			for (int i = 0; i < aLength; i++) rArray[i] = i;
			return rArray;
		}
		
		public static String stringValidate(Object aString) {
			if (aString == null) return "";
			String rString = aString.toString();
			return rString == null ? "" : rString;
		}
		
		public static boolean stringValid(Object aString) {
			return aString != null && !aString.toString().isEmpty();
		}
		
		public static boolean stringInvalid(Object aString) {
			return aString == null || aString.toString().isEmpty();
		}
		
		public static byte side(ForgeDirection aDirection) {
			return (byte)(aDirection==null?SIDE_INVALID:aDirection.ordinal());
		}
		
		public static byte side(int aSide) {
			return aSide > 5 || aSide < 0 ? SIDE_INVALID : (byte)aSide;
		}
		
		/** If this Index exists inside the passed Array and if it is != null */
		public static <E> boolean exists(int aIndex, E[] aArray) {
			return aIndex >= 0 && aIndex < aArray.length && aArray[aIndex] != null;
		}
		
		/** @return a Value for a Scale between 0 and aMax with aScale+1 possible Steps. 0 is only returned if the aValue is <= 0, aScale is only returned if the Value is >= aMax. The remaining values between ]0:aScale[ are returned for each Step of the Scale. This Function finds use in Displays such as the Barometer, but also in Redstone. */
		public static long scale(long aValue, long aMax, long aScale, boolean aInvert) {
			long rScale = (aValue <= 0 ? 0 : aValue >= aMax ? aScale : aScale <= 2 ? 1 : 1 + (aValue * (aScale-1)) / aMax);
			return aInvert ? aScale - rScale : rScale;
		}
		
		/** @return a Value for a Scale between aMin and aMax with aScale+1 possible Steps. 0 is only returned if the aValue is <= aMin, aScale is only returned if the Value is >= aMax. The remaining values between ]0:aScale[ are returned for each Step of the Scale. This Function finds use in Displays such as the Barometer, but also in Redstone. */
		public static long scale(long aValue, long aMin, long aMax, long aScale, boolean aInvert) {
			return scale(aValue-aMin, aMax-aMin, aScale, aInvert);
		}
		
		public static long bind(long aMin, long aMax, long aBoundValue) {
			return aMin > aMax ? Math.max(aMax, Math.min(aMin, aBoundValue)) : Math.max(aMin, Math.min(aMax, aBoundValue));
		}
		public static long bind_(long aMin, long aMax, long aBoundValue) {
			return Math.max(aMin, Math.min(aMax, aBoundValue));
		}
		
		public static float  bindF    (float  aBoundValue) {return        Math.max(0, Math.min(         1, aBoundValue));}
		public static double bindD    (double aBoundValue) {return        Math.max(0, Math.min(         1, aBoundValue));}
		public static byte   bind1    (long   aBoundValue) {return (byte) Math.max(0, Math.min(         1, aBoundValue));}
		public static byte   bind2    (long   aBoundValue) {return (byte) Math.max(0, Math.min(         3, aBoundValue));}
		public static byte   bind3    (long   aBoundValue) {return (byte) Math.max(0, Math.min(         7, aBoundValue));}
		public static byte   bind4    (long   aBoundValue) {return (byte) Math.max(0, Math.min(        15, aBoundValue));}
		public static byte   bind5    (long   aBoundValue) {return (byte) Math.max(0, Math.min(        31, aBoundValue));}
		public static byte   bind6    (long   aBoundValue) {return (byte) Math.max(0, Math.min(        63, aBoundValue));}
		public static byte   bind7    (long   aBoundValue) {return (byte) Math.max(0, Math.min(       127, aBoundValue));}
		public static short  bind8    (long   aBoundValue) {return (short)Math.max(0, Math.min(       255, aBoundValue));}
		public static short  bind15   (long   aBoundValue) {return (short)Math.max(0, Math.min(     32767, aBoundValue));}
		public static int    bind16   (long   aBoundValue) {return (int)  Math.max(0, Math.min(     65535, aBoundValue));}
		public static int    bind24   (long   aBoundValue) {return (int)  Math.max(0, Math.min(  16777215, aBoundValue));}
		public static int    bind31   (long   aBoundValue) {return (int)  Math.max(0, Math.min(2147483647, aBoundValue));}
		public static int    bindInt  (long   aBoundValue) {return (int)  Math.max(Integer.MIN_VALUE, Math.min(Integer.MAX_VALUE, aBoundValue));}
		public static short  bindShort(long   aBoundValue) {return (short)Math.max(Short.MIN_VALUE, Math.min(Short.MAX_VALUE, aBoundValue));}
		public static byte   bindByte (long   aBoundValue) {return (byte) Math.max(Byte.MIN_VALUE, Math.min(Byte.MAX_VALUE, aBoundValue));}
		public static byte   bindStack(long   aBoundValue) {return (byte) Math.max(1, Math.min(64, aBoundValue));}
		
		public static short[] bindRGBa(short[] aColors) {
			if (aColors == null) return new short[] {255,255,255,255};
			for (int i = 0; i < aColors.length; i++) aColors[i] = bind8(aColors[i]);
			return aColors;
		}
		
		public static int mixRGBInt(int aRGB1, int aRGB2) {
			return getRGBInt(new short[] {(short)((getR(aRGB1) + getR(aRGB2)) >> 1), (short)((getG(aRGB1) + getG(aRGB2)) >> 1), (short)((getB(aRGB1) + getB(aRGB2)) >> 1)});
		}
		
		public static int getRGBInt(short[] aColors) {
			return aColors == null ? 16777215 : (bind8(aColors[0]) << 16) | (bind8(aColors[1]) << 8) | bind8(aColors[2]);
		}
		
		public static int getRGBaInt(short[] aColors) {
			return aColors == null ? 16777215 : (bind8(aColors[0]) << 16) | (bind8(aColors[1]) << 8) | bind8(aColors[2]) | (bind8(aColors[3]) << 24);
		}
		
		public static int getRGBInt(long aR, long aG, long aB) {
			return (bind8(aR) << 16) | (bind8(aG) << 8) | bind8(aB);
		}
		
		public static int getRGBaInt(long aR, long aG, long aB, long aA) {
			return (bind8(aR) << 16) | (bind8(aG) << 8) | bind8(aB) | (bind8(aA) << 24);
		}
		
		public static short[] getRGBaArray(int aColors) {
			return new short[] {(short)((aColors >>> 16) & 255), (short)((aColors >>> 8) & 255), (short)(aColors & 255), (short)((aColors >>> 24) & 255)};
		}
		
		public static short getR(int aColors) {return (short)((aColors >>> 16) & 255);}
		public static short getG(int aColors) {return (short)((aColors >>>  8) & 255);}
		public static short getB(int aColors) {return (short) (aColors         & 255);}
		public static short getA(int aColors) {return (short)((aColors >>> 24) & 255);}
		
		@SideOnly(Side.CLIENT)
		/** estebes helped with the code for this one, and yes that cast down there is fucking necessary... */
		public static short[] color(ItemStack aStack) {
			if (ST.invalid(aStack)) return UNCOLOURED;
			IIcon tIcon = null;
			try {tIcon = aStack.getIconIndex();} catch(Throwable e) {return UNCOLOURED;} // And ofcourse some Mod needs to crash here...
			if (tIcon == null) return UNCOLOURED;
			String tResourceLocation = tIcon.getIconName();
			if (stringInvalid(tResourceLocation)) return UNCOLOURED;
			short[] rColor = color(tResourceLocation);
			if (rColor == null) return UNCOLOURED;
			short[] rModulation = getRGBaArray(aStack.getItem().getColorFromItemStack(aStack, 0));
			for (byte i = 0; i < 3; i++) rColor[i] = (short)((rColor[i] * rModulation[i]) / 255);
			return rColor;
		}
		
		@SideOnly(Side.CLIENT)
		/** estebes helped with the code for this one */
		public static short[] color(String aResourceLocation) {
			ResourceLocation aux = null;
			if (aResourceLocation.contains(":")) {
				String[] modid_itemid = aResourceLocation.split(":");
				aux = new ResourceLocation(modid_itemid[0], "textures/items/" + modid_itemid[1] + ".png");
			} else {
				aux = new ResourceLocation("minecraft", "textures/items/" + aResourceLocation + ".png");
			}
			java.awt.image.BufferedImage tIcon = null;
			try {tIcon = javax.imageio.ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(aux).getInputStream());} catch (IOException e) {/**/}
			return tIcon == null ? null : color(tIcon);
		}
		
		@SideOnly(Side.CLIENT)
		/** estebes helped with the code for this one */
		public static short[] color(java.awt.image.BufferedImage icon) {
			long tR = 0, tG = 0, tB = 0, tPixels = 0;
			for (int tWidth = 0; tWidth < icon.getWidth(); tWidth++) for (int tHeight = 0; tHeight < icon.getHeight(); tHeight++) {
				int tPixel = icon.getRGB(tWidth, tHeight);
				if ((     (tPixel >>> 24) & 255) > 128) {
					tR += (tPixel >>> 16) & 255;
					tG += (tPixel >>>  8) & 255;
					tB +=  tPixel         & 255;
					tPixels++;
				}
			}
			return new short[] {(short)(tR / tPixels), (short)(tG / tPixels), (short)(tB / tPixels)};
		}
		
		/** toUpperCases the first Character of the String and returns it */
		public static String capitalise(String aString) {
			return aString == null ? "" : aString.length() <= 1 ? aString.toUpperCase() : aString.substring(0, 1).toUpperCase() + aString.substring(1);
		}
		
		/** toUpperCases the first Character of each Word in the String and returns it */
		public static String capitaliseWords(String aString) {
			StringBuilder rString = new StringBuilder();
			for (String tString : aString.split(" ")) if (!tString.isEmpty()) rString.append(capitalise(tString)).append(" ");
			return rString.toString().trim();
		}
		
		/** @return the opposite facing of this Side of a Block, with a boundary check. */
		public static byte opposite(int aSide) {
			return aSide < OPPOSITES.length && aSide >= 0 ? OPPOSITES[aSide] : 6;
		}
		
		/** Turns the Amount of Stuff into a more readable String. */
		public static String displayUnits(long aAmount) {
			if (aAmount < 0) return "?.???";
			long tDigits = ((aAmount % U) * 1000) / U;
			return (aAmount / U) + "." + (tDigits<1?"000":tDigits<10?"00"+tDigits:tDigits<100?"0"+tDigits:tDigits);
		}
		
		/** Translates Amount of aUnit1 to Amount of aUnit2. */
		public static long units(long aAmount, long aOriginalUnit, long aTargetUnit, boolean aRoundUp) {
			if (aTargetUnit == 0) return 0;
			if (aOriginalUnit == aTargetUnit || aOriginalUnit == 0) return aAmount;
			if (aOriginalUnit %   aTargetUnit == 0) {aOriginalUnit /=   aTargetUnit;   aTargetUnit = 1;} else
			if (aTargetUnit   % aOriginalUnit == 0) {  aTargetUnit /= aOriginalUnit; aOriginalUnit = 1;}
			return Math.max(0, ((aAmount * aTargetUnit) / aOriginalUnit) + (aRoundUp && (aAmount * aTargetUnit) % aOriginalUnit > 0 ? 1 : 0));
		}
		
		/** Translates Amount of aUnit1 to Amount of aUnit2. With additional checks to avoid 64 Bit Overflow. */
		public static long units_(long aAmount, long aOriginalUnit, long aTargetUnit, boolean aRoundUp) {
			if (aTargetUnit == 0) return 0;
			if (aOriginalUnit == aTargetUnit || aOriginalUnit == 0) return aAmount;
			if (aOriginalUnit %   aTargetUnit == 0) {aOriginalUnit /=   aTargetUnit;   aTargetUnit = 1;} else
			if (aTargetUnit   % aOriginalUnit == 0) {  aTargetUnit /= aOriginalUnit; aOriginalUnit = 1;} else {
			if (aOriginalUnit %  648 == 0 && aTargetUnit %  648 == 0) {aOriginalUnit /=  648; aTargetUnit /=  648;}
			if (aOriginalUnit % 1000 == 0 && aTargetUnit % 1000 == 0) {aOriginalUnit /= 1000; aTargetUnit /= 1000;}}
			return Math.max(0, ((aAmount * aTargetUnit) / aOriginalUnit) + (aRoundUp && (aAmount * aTargetUnit) % aOriginalUnit > 0 ? 1 : 0));
		}
		
		/** Divides but rounds up. */
		public static long divup(long aNumber, long aDivider) {
			return aNumber / aDivider + (aNumber % aDivider == 0 ? 0 : 1);
		}
		
		public static long sum(byte... aArray) {
			long rAmount = 0;
			for (byte tNumber : aArray) rAmount += tNumber;
			return rAmount;
		}
		
		public static long sum(short... aArray) {
			long rAmount = 0;
			for (short tNumber : aArray) rAmount += tNumber;
			return rAmount;
		}
		
		public static long sum(int... aArray) {
			long rAmount = 0;
			for (int tNumber : aArray) rAmount += tNumber;
			return rAmount;
		}
		
		public static long sum(long... aArray) {
			long rAmount = 0;
			for (long tNumber : aArray) rAmount += tNumber;
			return rAmount;
		}
		
		public static boolean abs_greater(long aAmount1, long aAmount2) {return Math.abs(aAmount1) > Math.abs(aAmount2);}
		public static boolean abs_smaller(long aAmount1, long aAmount2) {return Math.abs(aAmount1) < Math.abs(aAmount2);}
		public static boolean abs_greater_equal(long aAmount1, long aAmount2) {return Math.abs(aAmount1) >= Math.abs(aAmount2);}
		public static boolean abs_smaller_equal(long aAmount1, long aAmount2) {return Math.abs(aAmount1) <= Math.abs(aAmount2);}
		
		public static boolean inside(long aMin, long aMax, long aNumber) {return aMin < aMax ? aMin <= aNumber && aNumber <= aMax : aMax <= aNumber && aNumber <= aMin;}
		public static boolean inside_(double aMin, double aMax, double aNumber) {return aMin < aMax ? aMin <= aNumber && aNumber <= aMax : aMax <= aNumber && aNumber <= aMin;}
		
		/** @return an Array containing the X and the Y Coordinate of the clicked Point, with the top left Corner as Origin, like on the Texture Sheet. return values should always be between [0.0F and 0.99F]. */
		public static float[] getFacingCoordsClicked(byte aSide, float aHitX, float aHitY, float aHitZ) {
			switch (aSide) {
			case  0: return new float[] {Math.min(0.99F, Math.max(0,  aHitX)), Math.min(0.99F, Math.max(0,1-aHitZ))};
			case  1: return new float[] {Math.min(0.99F, Math.max(0,  aHitX)), Math.min(0.99F, Math.max(0,  aHitZ))};
			case  2: return new float[] {Math.min(0.99F, Math.max(0,1-aHitX)), Math.min(0.99F, Math.max(0,1-aHitY))};
			case  3: return new float[] {Math.min(0.99F, Math.max(0,  aHitX)), Math.min(0.99F, Math.max(0,1-aHitY))};
			case  4: return new float[] {Math.min(0.99F, Math.max(0,  aHitZ)), Math.min(0.99F, Math.max(0,1-aHitY))};
			case  5: return new float[] {Math.min(0.99F, Math.max(0,1-aHitZ)), Math.min(0.99F, Math.max(0,1-aHitY))};
			default: return new float[] {0.5F, 0.5F};
			}
		}
		
		public static byte getSideForPlayerPlacing(Entity aPlayer) {
			if (aPlayer.rotationPitch >=  65) return SIDE_UP;
			if (aPlayer.rotationPitch <= -65) return SIDE_DOWN;
			return COMPASS_DIRECTIONS[UT.Code.roundDown(4*aPlayer.rotationYaw/360+0.5)&3];
		}
		
		public static byte getSideForPlayerPlacing(Entity aPlayer, byte aDefaultFacing, boolean[] aAllowedFacings) {
			if (aPlayer.rotationPitch >=  65 && aAllowedFacings[SIDE_UP]) return SIDE_UP;
			if (aPlayer.rotationPitch <= -65 && aAllowedFacings[SIDE_DOWN]) return SIDE_DOWN;
			byte rFacing = COMPASS_DIRECTIONS[UT.Code.roundDown(0.5+4*aPlayer.rotationYaw/360)&3];
			if (aAllowedFacings[rFacing]) return rFacing;
			for (byte tSide : ALL_SIDES_VALID) if (aAllowedFacings[tSide]) return tSide;
			return aDefaultFacing;
		}
		
		public static byte getOppositeSideForPlayerPlacing(Entity aPlayer, byte aDefaultFacing, boolean[] aAllowedFacings) {
			if (aPlayer.rotationPitch >=  65 && aAllowedFacings[SIDE_DOWN]) return SIDE_DOWN;
			if (aPlayer.rotationPitch <= -65 && aAllowedFacings[SIDE_UP]) return SIDE_UP;
			byte rFacing = OPPOSITES[COMPASS_DIRECTIONS[UT.Code.roundDown(0.5+4*aPlayer.rotationYaw/360)&3]];
			if (aAllowedFacings[rFacing]) return rFacing;
			for (byte tSide : ALL_SIDES_VALID) if (aAllowedFacings[tSide]) return tSide;
			return aDefaultFacing;
		}
		
		/**
		 * This Function determines the direction a Block gets when being Wrenched.
		 */
		public static byte getSideWrenching(byte aSide, float aHitX, float aHitY, float aHitZ) {
			switch (aSide) {
			case  0: case  1:
				if (aHitX < 0.25) return aHitZ < 0.25 || aHitZ > 0.75 ? OPPOSITES[aSide] : 4;
				if (aHitX > 0.75) return aHitZ < 0.25 || aHitZ > 0.75 ? OPPOSITES[aSide] : 5;
				if (aHitZ < 0.25) return 2;
				if (aHitZ > 0.75) return 3;
				return aSide;
			case  2: case  3:
				if (aHitX < 0.25) return aHitY < 0.25 || aHitY > 0.75 ? OPPOSITES[aSide] : 4;
				if (aHitX > 0.75) return aHitY < 0.25 || aHitY > 0.75 ? OPPOSITES[aSide] : 5;
				if (aHitY < 0.25) return 0;
				if (aHitY > 0.75) return 1;
				return aSide;
			case  4: case  5:
				if (aHitZ < 0.25) return aHitY < 0.25 || aHitY > 0.75 ? OPPOSITES[aSide] : 2;
				if (aHitZ > 0.75) return aHitY < 0.25 || aHitY > 0.75 ? OPPOSITES[aSide] : 3;
				if (aHitY < 0.25) return 0;
				if (aHitY > 0.75) return 1;
				return aSide;
			}
			return SIDE_INVALID;
		}
	}
	
	public static class NBT {
		public static NBTTagCompound make() {
			return new NBTTagCompound();
		}
		
		/** Turns each Object -> Object Pair into a Part of the passed NBT as Object-toString()-Key -> Value Pair */
		public static NBTTagCompound make(String aFirstKey, Object aFirstValue, Object... aTags) {
			NBTTagCompound rNBT = make();
			
			if (aFirstValue == null) {/* Nothing */}
			else if (aFirstValue instanceof Boolean)           rNBT.setBoolean(aFirstKey, (Boolean)                aFirstValue);
			else if (aFirstValue instanceof Byte)              rNBT.setByte(   aFirstKey, (Byte)                   aFirstValue);
			else if (aFirstValue instanceof Short)             rNBT.setShort(  aFirstKey, (Short)                  aFirstValue);
			else if (aFirstValue instanceof Integer)           rNBT.setInteger(aFirstKey, (Integer)                aFirstValue);
			else if (aFirstValue instanceof Long)              rNBT.setLong(   aFirstKey, (Long)                   aFirstValue);
			else if (aFirstValue instanceof Float)             rNBT.setFloat(  aFirstKey, (Float)                  aFirstValue);
			else if (aFirstValue instanceof Double)            rNBT.setDouble( aFirstKey, (Double)                 aFirstValue);
			else if (aFirstValue instanceof String)            rNBT.setString( aFirstKey, (String)                 aFirstValue);
			else if (aFirstValue instanceof NBTBase)           rNBT.setTag(    aFirstKey, (NBTBase)                aFirstValue);
			else if (aFirstValue instanceof FluidStack)        rNBT.setTag(    aFirstKey, FL.save((FluidStack)     aFirstValue));
			else if (aFirstValue instanceof OreDictMaterial)   rNBT.setString( aFirstKey, ((OreDictMaterial)       aFirstValue).mNameInternal);
			else if (aFirstValue instanceof RecipeMap)         rNBT.setString( aFirstKey, ((RecipeMap)             aFirstValue).mNameInternal);
			else                                               rNBT.setString( aFirstKey, aFirstValue.toString());
			
			for (int i = 1; i < aTags.length; i+=2) {
				if (aTags[i] == null) {/* Nothing */}
				else if (aTags[i] instanceof Boolean)          rNBT.setBoolean(aTags[i-1].toString(), (Boolean)                aTags[i]);
				else if (aTags[i] instanceof Byte)             rNBT.setByte(   aTags[i-1].toString(), (Byte)                   aTags[i]);
				else if (aTags[i] instanceof Short)            rNBT.setShort(  aTags[i-1].toString(), (Short)                  aTags[i]);
				else if (aTags[i] instanceof Integer)          rNBT.setInteger(aTags[i-1].toString(), (Integer)                aTags[i]);
				else if (aTags[i] instanceof Long)             rNBT.setLong(   aTags[i-1].toString(), (Long)                   aTags[i]);
				else if (aTags[i] instanceof Float)            rNBT.setFloat(  aTags[i-1].toString(), (Float)                  aTags[i]);
				else if (aTags[i] instanceof Double)           rNBT.setDouble( aTags[i-1].toString(), (Double)                 aTags[i]);
				else if (aTags[i] instanceof String)           rNBT.setString( aTags[i-1].toString(), (String)                 aTags[i]);
				else if (aTags[i] instanceof NBTBase)          rNBT.setTag(    aTags[i-1].toString(), (NBTBase)                aTags[i]);
				else if (aTags[i] instanceof FluidStack)       rNBT.setTag(    aTags[i-1].toString(), FL.save((FluidStack)     aTags[i]));
				else if (aTags[i] instanceof OreDictMaterial)  rNBT.setString( aTags[i-1].toString(), ((OreDictMaterial)       aTags[i]).mNameInternal);
				else if (aTags[i] instanceof RecipeMap)        rNBT.setString( aTags[i-1].toString(), ((RecipeMap)             aTags[i]).mNameInternal);
				else                                           rNBT.setString( aTags[i-1].toString(), aTags[i].toString());
			}
			return rNBT;
		}
		
		/** Turns each Object -> Object Pair into a Part of the passed NBT as Object-toString()-Key -> Value Pair */
		public static NBTTagCompound make(NBTTagCompound aNBT, Object... aTags) {
			if (aNBT == null) aNBT = make();
			for (int i = 1; i < aTags.length; i+=2) {
				if (aTags[i] == null) {/* Nothing */}
				else if (aTags[i] instanceof Boolean)          aNBT.setBoolean(    aTags[i-1].toString(), (Boolean)                aTags[i]);
				else if (aTags[i] instanceof Byte)             aNBT.setByte(       aTags[i-1].toString(), (Byte)                   aTags[i]);
				else if (aTags[i] instanceof Short)            aNBT.setShort(      aTags[i-1].toString(), (Short)                  aTags[i]);
				else if (aTags[i] instanceof Integer)          aNBT.setInteger(    aTags[i-1].toString(), (Integer)                aTags[i]);
				else if (aTags[i] instanceof Long)             aNBT.setLong(       aTags[i-1].toString(), (Long)                   aTags[i]);
				else if (aTags[i] instanceof Float)            aNBT.setFloat(      aTags[i-1].toString(), (Float)                  aTags[i]);
				else if (aTags[i] instanceof Double)           aNBT.setDouble(     aTags[i-1].toString(), (Double)                 aTags[i]);
				else if (aTags[i] instanceof String)           aNBT.setString(     aTags[i-1].toString(), (String)                 aTags[i]);
				else if (aTags[i] instanceof NBTBase)          aNBT.setTag(        aTags[i-1].toString(), (NBTBase)                aTags[i]);
				else if (aTags[i] instanceof FluidStack)       aNBT.setTag(        aTags[i-1].toString(), FL.save((FluidStack)     aTags[i]));
				else if (aTags[i] instanceof OreDictMaterial)  aNBT.setString(     aTags[i-1].toString(), ((OreDictMaterial)       aTags[i]).mNameInternal);
				else if (aTags[i] instanceof RecipeMap)        aNBT.setString(     aTags[i-1].toString(), ((RecipeMap)             aTags[i]).mNameInternal);
				else                                           aNBT.setString(     aTags[i-1].toString(), aTags[i].toString());
			}
			return aNBT;
		}
		
		/** Fuses two NBT Compounds together with the Priority lying on the content of the first NBT */
		public static NBTTagCompound fuse(NBTTagCompound aNBT1, NBTTagCompound aNBT2) {
			if (aNBT1 == null) return aNBT2==null?make():(NBTTagCompound)aNBT2.copy();
			NBTTagCompound rNBT = (NBTTagCompound)aNBT1.copy();
			if (aNBT2 == null) return rNBT;
			for (Object tKey : aNBT2.func_150296_c()) if (!rNBT.hasKey(tKey.toString())) rNBT.setTag(tKey.toString(), aNBT2.getTag(tKey.toString()));
			return rNBT;
		}
		
		public static NBTTagList makeInv(ItemStack... aStacks) {
			NBTTagList rInventory = new NBTTagList();
			for (int i = 0; i < aStacks.length; i++) rInventory.appendTag(makeShort(ST.save(aStacks[i]), "s", (short)i));
			return rInventory;
		}
		
		public static NBTTagCompound makeBool(Object aTag, boolean aValue) {
			NBTTagCompound aNBT = make();
			aNBT.setBoolean(aTag.toString(), aValue);
			return aNBT;
		}
		public static NBTTagCompound makeBool(NBTTagCompound aNBT, Object aTag, boolean aValue) {
			if (aNBT == null) aNBT = make();
			aNBT.setBoolean(aTag.toString(), aValue);
			return aNBT;
		}
		
		public static NBTTagCompound makeByte(Object aTag, byte aValue) {
			NBTTagCompound aNBT = make();
			aNBT.setByte(aTag.toString(), aValue);
			return aNBT;
		}
		public static NBTTagCompound makeByte(NBTTagCompound aNBT, Object aTag, byte aValue) {
			if (aNBT == null) aNBT = make();
			aNBT.setByte(aTag.toString(), aValue);
			return aNBT;
		}
		
		public static NBTTagCompound makeShort(Object aTag, short aValue) {
			NBTTagCompound aNBT = make();
			aNBT.setShort(aTag.toString(), aValue);
			return aNBT;
		}
		public static NBTTagCompound makeShort(NBTTagCompound aNBT, Object aTag, short aValue) {
			if (aNBT == null) aNBT = make();
			aNBT.setShort(aTag.toString(), aValue);
			return aNBT;
		}
		
		public static NBTTagCompound makeInt(Object aTag, int aValue) {
			NBTTagCompound aNBT = make();
			aNBT.setInteger(aTag.toString(), aValue);
			return aNBT;
		}
		public static NBTTagCompound makeInt(NBTTagCompound aNBT, Object aTag, int aValue) {
			if (aNBT == null) aNBT = make();
			aNBT.setInteger(aTag.toString(), aValue);
			return aNBT;
		}
		
		public static NBTTagCompound makeLong(Object aTag, long aValue) {
			NBTTagCompound aNBT = make();
			setNumber(aNBT, aTag.toString(), aValue);
			return aNBT;
		}
		public static NBTTagCompound makeLong(NBTTagCompound aNBT, Object aTag, long aValue) {
			if (aNBT == null) aNBT = make();
			setNumber(aNBT, aTag.toString(), aValue);
			return aNBT;
		}
		
		public static NBTTagCompound makeFloat(Object aTag, float aValue) {
			NBTTagCompound aNBT = make();
			aNBT.setFloat(aTag.toString(), aValue);
			return aNBT;
		}
		public static NBTTagCompound makeFloat(NBTTagCompound aNBT, Object aTag, float aValue) {
			if (aNBT == null) aNBT = make();
			aNBT.setFloat(aTag.toString(), aValue);
			return aNBT;
		}
		
		public static NBTTagCompound makeDouble(Object aTag, double aValue) {
			NBTTagCompound aNBT = make();
			aNBT.setDouble(aTag.toString(), aValue);
			return aNBT;
		}
		public static NBTTagCompound makeDouble(NBTTagCompound aNBT, Object aTag, double aValue) {
			if (aNBT == null) aNBT = make();
			aNBT.setDouble(aTag.toString(), aValue);
			return aNBT;
		}
		
		public static NBTTagCompound makeString(Object aTag, Object aValue) {
			NBTTagCompound aNBT = make();
			if (aValue == null) return aNBT;
			aNBT.setString(aTag.toString(), aValue.toString());
			return aNBT;
		}
		public static NBTTagCompound makeString(NBTTagCompound aNBT, Object aTag, Object aValue) {
			if (aNBT == null) aNBT = make();
			if (aValue == null) return aNBT;
			aNBT.setString(aTag.toString(), aValue.toString());
			return aNBT;
		}
		
		@Deprecated public static NBTTagCompound getNBTs(NBTTagCompound aNBT, Object... aTags) {return make(aNBT, aTags);}
		@Deprecated public static NBTTagCompound getNBTBoolean(NBTTagCompound aNBT, Object aTag, boolean aValue) {return makeBool(aNBT, aTag, aValue);}
		@Deprecated public static NBTTagCompound getNBTByte(NBTTagCompound aNBT, Object aTag, byte aValue) {return makeByte(aNBT, aTag, aValue);}
		@Deprecated public static NBTTagCompound getNBTShort(NBTTagCompound aNBT, Object aTag, short aValue) {return makeShort(aNBT, aTag, aValue);}
		@Deprecated public static NBTTagCompound getNBTInteger(NBTTagCompound aNBT, Object aTag, int aValue) {return makeInt(aNBT, aTag, aValue);}
		@Deprecated public static NBTTagCompound getNBTLong(NBTTagCompound aNBT, Object aTag, long aValue) {return makeLong(aNBT, aTag, aValue);}
		@Deprecated public static NBTTagCompound getNBTFloat(NBTTagCompound aNBT, Object aTag, float aValue) {return makeFloat(aNBT, aTag, aValue);}
		@Deprecated public static NBTTagCompound getNBTDouble(NBTTagCompound aNBT, Object aTag, double aValue) {return makeDouble(aNBT, aTag, aValue);}
		@Deprecated public static NBTTagCompound getNBTString(NBTTagCompound aNBT, Object aTag, Object aValue) {return makeString(aNBT, aTag, aValue);}
		
		/** Saves on Data Size by simply not adding "false" Booleans. */
		public static NBTTagCompound setBoolean(NBTTagCompound aNBT, Object aTag, boolean aValue) {
			if (aValue) {
				aNBT.setBoolean(aTag.toString(), aValue);
			} else {
				aNBT.removeTag(aTag.toString());
			}
			return aNBT;
		}
		
		/** Saves on Data Size by choosing the smallest possible Data Type, and by also not adding zeros. The regular getLong() Function can also get the other Number Types. */
		public static NBTTagCompound setNumber(NBTTagCompound aNBT, Object aTag, long aValue) {
			if (aValue == 0) {aNBT.removeTag(aTag.toString()); return aNBT;}
			if (aValue > Integer.MAX_VALUE || aValue < Integer.MIN_VALUE) {aNBT.setLong(aTag.toString(), aValue); return aNBT;}
			if (aValue > Short.MAX_VALUE || aValue < Short.MIN_VALUE) {aNBT.setInteger(aTag.toString(), (int)aValue); return aNBT;}
			if (aValue > Byte.MAX_VALUE || aValue < Byte.MIN_VALUE) {aNBT.setShort(aTag.toString(), (short)aValue); return aNBT;}
			aNBT.setByte(aTag.toString(), (byte)aValue);
			return aNBT;
		}
		
		/** Saves on Data Size by choosing the smallest possible Data Type, and by also not adding zeros or negative Numbers. The regular getLong() Function can also get the other Number Types. */
		public static NBTTagCompound setPosNum(NBTTagCompound aNBT, Object aTag, long aValue) {
			if (aValue <= 0) {aNBT.removeTag(aTag.toString()); return aNBT;}
			if (aValue > Integer.MAX_VALUE || aValue < Integer.MIN_VALUE) {aNBT.setLong(aTag.toString(), aValue); return aNBT;}
			if (aValue > Short.MAX_VALUE || aValue < Short.MIN_VALUE) {aNBT.setInteger(aTag.toString(), (int)aValue); return aNBT;}
			if (aValue > Byte.MAX_VALUE || aValue < Byte.MIN_VALUE) {aNBT.setShort(aTag.toString(), (short)aValue); return aNBT;}
			aNBT.setByte(aTag.toString(), (byte)aValue);
			return aNBT;
		}
		
		public static ItemStack set(ItemStack aStack, NBTTagCompound aNBT) {
			if (aNBT == null || aNBT.hasNoTags()) {aStack.setTagCompound(null); return aStack;}
			ArrayList<String> tTagsToRemove = new ArrayListNoNulls<>();
			for (Object tKey : aNBT.func_150296_c()) {
				NBTBase tValue = aNBT.getTag((String)tKey);
				if (tValue == null || (tValue instanceof NBTTagCompound && ((NBTTagCompound)tValue).hasNoTags()) || (tValue instanceof NBTPrimitive && ((NBTPrimitive)tValue).func_150291_c() == 0) || (tValue instanceof NBTTagString && Code.stringInvalid(((NBTTagString)tValue).func_150285_a_()))) tTagsToRemove.add((String)tKey);
			}
			for (Object tKey : tTagsToRemove) aNBT.removeTag((String)tKey);
			aStack.setTagCompound(aNBT.hasNoTags()?null:aNBT);
			return aStack;
		}
		
		public static NBTTagCompound getNBT(ItemStack aStack) {
			NBTTagCompound rNBT = aStack.getTagCompound();
			return rNBT==null?make():rNBT;
		}
		
		public static NBTTagCompound getOrCreate(ItemStack aStack) {
			NBTTagCompound rNBT = aStack.getTagCompound();
			if (rNBT == null) aStack.setTagCompound(rNBT = make());
			return rNBT;
		}
		
		public static NBTTagCompound setPunchCardData(ItemStack aStack, String aPunchCardData) {
			NBTTagCompound tNBT = getNBT(aStack);
			tNBT.setString("gt.punchcard", aPunchCardData);
			set(aStack, tNBT);
			return tNBT;
		}
		public static String getPunchCardData(ItemStack aStack) {
			NBTTagCompound tNBT = getNBT(aStack);
			return tNBT.getString("gt.punchcard");
		}
		public static NBTTagCompound setPunchCardData(NBTTagCompound aNBT, String aPunchCardData) {
			aNBT.setString("gt.punchcard", aPunchCardData);
			return aNBT;
		}
		public static String getPunchCardData(NBTTagCompound aNBT) {
			return aNBT.getString("gt.punchcard");
		}
		
		public static NBTTagCompound setBlueprintCrafting(ItemStack aStack, ItemStack... aBlueprint) {
			NBTTagCompound tNBT = getNBT(aStack);
			setBlueprintCrafting(tNBT, aBlueprint);
			set(aStack, tNBT);
			return tNBT;
		}
		public static ItemStack[] getBlueprintCrafting(ItemStack aStack) {
			return getBlueprintCrafting(getNBT(aStack));
		}
		public static NBTTagCompound setBlueprintCrafting(NBTTagCompound aNBT, ItemStack... aBlueprint) {
			NBTTagCompound tList = make();
			boolean temp = F;
			for (int i = 0; i < aBlueprint.length; i++) if (ST.valid(aBlueprint[i])) {
				ST.save(tList, ""+i, ST.amount(1, aBlueprint[i]));
				temp = T;
			}
			if (temp) aNBT.setTag("gt.blueprint.craft", tList);
			return aNBT;
		}
		public static ItemStack[] getBlueprintCrafting(NBTTagCompound aNBT) {
			NBTTagCompound tList = aNBT.hasKey("gt.blueprint.craft")?aNBT.getCompoundTag("gt.blueprint.craft"):null;
			if (tList != null) {
				ItemStack[] rRecipe = new ItemStack[9];
				for (int i = 0; i < rRecipe.length; i++) rRecipe[i] = ST.amount(1, ST.load(tList, ""+i));
				return rRecipe;
			}
			return ZL_IS;
		}
		
		public static NBTTagCompound setLighterFuel(ItemStack aStack, long aFuel) {
			NBTTagCompound tNBT = getNBT(aStack);
			setNumber(tNBT, "gt.lighter", aFuel);
			set(aStack, tNBT);
			return tNBT;
		}
		public static long getLighterFuel(ItemStack aStack) {
			NBTTagCompound tNBT = getNBT(aStack);
			return tNBT.getLong("gt.lighter");
		}
		public static NBTTagCompound setLighterFuel(NBTTagCompound aNBT, long aFuel) {
			setNumber(aNBT, "gt.lighter", aFuel);
			return aNBT;
		}
		public static long getLighterFuel(NBTTagCompound aNBT) {
			return aNBT.getLong("gt.lighter");
		}
		
		public static NBTTagCompound setMapID(ItemStack aStack, short aMapID) {
			NBTTagCompound tNBT = getNBT(aStack);
			tNBT.setShort("map_id", aMapID);
			set(aStack, tNBT);
			return tNBT;
		}
		public static short getMapID(ItemStack aStack) {
			NBTTagCompound tNBT = getNBT(aStack);
			if (!tNBT.hasKey("map_id")) return -1;
			return tNBT.getShort("map_id");
		}
		public static NBTTagCompound setMapID(NBTTagCompound aNBT, short aMapID) {
			aNBT.setShort("map_id", aMapID);
			return aNBT;
		}
		public static short getMapID(NBTTagCompound aNBT) {
			if (!aNBT.hasKey("map_id")) return -1;
			return aNBT.getShort("map_id");
		}
		
		public static NBTTagCompound setBookMapping(ItemStack aStack, String aTitle) {
			NBTTagCompound tNBT = getNBT(aStack);
			tNBT.setString("book", aTitle);
			set(aStack, tNBT);
			return tNBT;
		}
		public static String getBookMapping(ItemStack aStack) {
			NBTTagCompound tNBT = getNBT(aStack);
			return tNBT.getString("book");
		}
		public static NBTTagCompound setBookMapping(NBTTagCompound aNBT, String aTitle) {
			aNBT.setString("book", aTitle);
			return aNBT;
		}
		public static String getBookMapping(NBTTagCompound aNBT) {
			return aNBT.getString("book");
		}
		
		public static NBTTagCompound setBookTitle(ItemStack aStack, String aTitle) {
			NBTTagCompound tNBT = getNBT(aStack);
			tNBT.setString("title", aTitle);
			set(aStack, tNBT);
			return tNBT;
		}
		public static String getBookTitle(ItemStack aStack) {
			NBTTagCompound tNBT = getNBT(aStack);
			return tNBT.getString("title");
		}
		public static NBTTagCompound setBookTitle(NBTTagCompound aNBT, String aTitle) {
			aNBT.setString("title", aTitle);
			return aNBT;
		}
		public static String getBookTitle(NBTTagCompound aNBT) {
			return aNBT.getString("title");
		}
		
		public static NBTTagCompound setBookAuthor(ItemStack aStack, String aAuthor) {
			NBTTagCompound tNBT = getNBT(aStack);
			tNBT.setString("author", aAuthor);
			set(aStack, tNBT);
			return tNBT;
		}
		public static String getBookAuthor(ItemStack aStack) {
			NBTTagCompound tNBT = getNBT(aStack);
			return tNBT.getString("author");
		}
		public static NBTTagCompound setBookAuthor(NBTTagCompound aNBT, String aAuthor) {
			aNBT.setString("author", aAuthor);
			return aNBT;
		}
		public static String getBookAuthor(NBTTagCompound aNBT) {
			return aNBT.getString("author");
		}
		
		public static List<String> getDataToolTip(NBTTagCompound aData, List<String> aList, boolean aAllDetails) {
			if (aData.hasKey(NBT_REACTOR_SETUP)) {
				aList.add(LH.Chat.CYAN + "Reactor Setup: " + aData.getString(NBT_REACTOR_SETUP_NAME));
				return aList;
			}
			if (aData.hasKey(NBT_CANVAS_BLOCK)) {
				aList.add(LH.Chat.CYAN + "Block Image: " + ST.names(ST.make(Block.getBlockById(aData.getInteger(NBT_CANVAS_BLOCK)), 1, aData.getInteger(NBT_CANVAS_META))));
				return aList;
			}
			if (aData.hasKey(NBT_REPLICATOR_DATA)) {
				short tIndex = aData.getShort(NBT_REPLICATOR_DATA);
				if (Code.exists(tIndex, OreDictMaterial.MATERIAL_ARRAY)) {
					OreDictMaterial tMaterial = OreDictMaterial.MATERIAL_ARRAY[tIndex];
					if (tMaterial.contains(TD.Processing.UUM)) {
						if (aAllDetails) {
							aList.add(LH.Chat.CYAN + "Material Data: " + LH.Chat.WHITE + tMaterial.getLocal());
							aList.add(LH.Chat.CYAN + "Can be Replicated using");
							if (tMaterial.contains(TD.Atomic.ANTIMATTER)) {
								aList.add(LH.Chat.WHITE + "Neutral Antimatter: " + LH.Chat.YELLOW + tMaterial.mNeutrons);
								aList.add(LH.Chat.WHITE + "Charged Antimatter: " + LH.Chat.RED + tMaterial.mProtons);
							} else {
								aList.add(LH.Chat.WHITE + "Neutral Matter: " + LH.Chat.YELLOW + tMaterial.mNeutrons);
								aList.add(LH.Chat.WHITE + "Charged Matter: " + LH.Chat.RED + tMaterial.mProtons);
							}
							aList.add(LH.Chat.WHITE + "Energy: " + TD.Energy.QU.getChatFormat() + ((tMaterial.mNeutrons+tMaterial.mProtons)*65536) + " " + TD.Energy.QU.getLocalisedNameShort());
						} else {
							aList.add(LH.Chat.CYAN + "Mat Data: " + LH.Chat.WHITE + tMaterial.getLocal() + (aAllDetails ? "" : " ("+LH.Chat.YELLOW+tMaterial.mNeutrons+LH.Chat.WHITE+"/"+LH.Chat.RED+tMaterial.mProtons+LH.Chat.WHITE+"/"+TD.Energy.QU.getChatFormat()+((tMaterial.mNeutrons+tMaterial.mProtons)*65536)+LH.Chat.WHITE+")"));
						}
					} else {
						aList.add(LH.Chat.CYAN + "Material Data: " + LH.Chat.WHITE + tMaterial.getLocal() + LH.Chat.ORANGE + " (Not Replicatable)");
					}
				}
				return aList;
			}
			if (IL.GC_Schematic_1.exists() && aData.hasKey("gc_schematics_1")) {
				aList.add(LH.Chat.CYAN + IL.GC_Schematic_1.getWithMeta(1, aData.getShort("gc_schematics_1")).getDisplayName());
				return aList;
			}
			if (IL.GC_Schematic_2.exists() && aData.hasKey("gc_schematics_2")) {
				aList.add(LH.Chat.CYAN + IL.GC_Schematic_2.getWithMeta(1, aData.getShort("gc_schematics_2")).getDisplayName());
				return aList;
			}
			if (IL.GC_Schematic_3.exists() && aData.hasKey("gc_schematics_3")) {
				aList.add(LH.Chat.CYAN + IL.GC_Schematic_3.getWithMeta(1, aData.getShort("gc_schematics_3")).getDisplayName());
				return aList;
			}
			if (IL.IE_Blueprint_Projectiles_Common.exists() && aData.hasKey("ie_blueprint")) {
				short tMeta = aData.getShort("ie_blueprint");
				aList.add(LH.Chat.CYAN + IL.IE_Blueprint_Projectiles_Common.getWithMeta(1, tMeta).getDisplayName());
				switch(tMeta) {
				case 0: aList.add(LH.Chat.GREEN + "Common Projectiles"); break;
				case 1: aList.add(LH.Chat.GREEN + "Specialized Projectiles"); break;
				case 2: aList.add(LH.Chat.GREEN + "Arc Furnace Electrodes"); break;
				}
				return aList;
			}
			String tString = getBookTitle(aData);
			if (Code.stringValid(tString)) {
				aList.add(LH.Chat.CYAN + "Book: " + tString);
				if (aAllDetails) {
					tString = getBookAuthor(aData);
					if (Code.stringValid(tString)) aList.add(LH.Chat.CYAN + "by " + tString);
				}
				return aList;
			}
			short tMapID = getMapID(aData);
			if (tMapID >= 0) {
				aList.add(LH.Chat.CYAN + "Map ID: " + tMapID);
				return aList;
			}
			tString = getPunchCardData(aData);
			if (Code.stringValid(tString)) {
				aList.add(LH.Chat.CYAN + "Punch Card Data");
				if (aAllDetails) for (int i = 0, j = tString.length(); i < j; i += 64) aList.add(LH.Chat.GREEN + tString.substring(i, Math.min(i+64, j)));
				return aList;
			}
			ItemStack[] tBlueprint = getBlueprintCrafting(aData);
			if (tBlueprint != ZL_IS) {
				ItemStack tCrafted = CR.getany(DW, tBlueprint);
				if (ST.invalid(tCrafted)) {
					aList.add(LH.Chat.CYAN + "Blueprint with random Items");
				} else {
					if (aAllDetails) {
						aList.add(LH.Chat.CYAN + "Blueprint for " + tCrafted.getDisplayName());
					} else {
						aList.add(LH.Chat.CYAN + "Blueprint: " + tCrafted.getDisplayName());
					}
				}
				return aList;
			}
			return aList;
		}
		
		public static ItemStack addEnchantment(ItemStack aStack, Enchantment aEnchantment, long aLevel) {
			NBTTagCompound tNBT = getNBT(aStack), tEnchantmentTag;
			if (!tNBT.hasKey("ench", 9)) tNBT.setTag("ench", new NBTTagList());
			NBTTagList tList = tNBT.getTagList("ench", 10);
			
			boolean temp = T;
			
			for (int i = 0; i < tList.tagCount(); i++) {
				tEnchantmentTag = tList.getCompoundTagAt(i);
				if (tEnchantmentTag.getShort("id") == aEnchantment.effectId) {
					tEnchantmentTag.setShort("id", (short)aEnchantment.effectId);
					tEnchantmentTag.setShort("lvl", (byte)aLevel);
					temp = F;
					break;
				}
			}
			
			if (temp) {
				tEnchantmentTag = make();
				tEnchantmentTag.setShort("id", (short)aEnchantment.effectId);
				tEnchantmentTag.setShort("lvl", (byte)aLevel);
				tList.appendTag(tEnchantmentTag);
			}
			
			return set(aStack, tNBT);
		}
	}
	
	/**
	 * THIS IS BULLSHIT!!! WHY DO I HAVE TO DO THIS SHIT JUST TO HAVE ENCHANTS PROPERLY!?!
	 */
	public static class Enchantments {
		private static final BullshitIteratorA mBullshitIteratorA = new BullshitIteratorA();
		private static final BullshitIteratorB mBullshitIteratorB = new BullshitIteratorB();
		
		private static void applyBullshit(IBullshit aBullshitModifier, ItemStack aStack) {
			if (aStack != null) {
				NBTTagList nbttaglist = aStack.getEnchantmentTagList();
				if (nbttaglist != null) {
					for (int i = 0; i < nbttaglist.tagCount(); ++i) {
						try {
							short short1 = nbttaglist.getCompoundTagAt(i).getShort("id");
							short short2 = nbttaglist.getCompoundTagAt(i).getShort("lvl");
							if (Enchantment.enchantmentsList[short1] != null) aBullshitModifier.calculateModifier(Enchantment.enchantmentsList[short1], short2);
						} catch(Throwable e) {
							//
						}
					}
				}
			}
		}
		
		private static void applyArrayOfBullshit(IBullshit aBullshitModifier, ItemStack[] aStacks) {
			for (int i = 0; i < aStacks.length; i++) applyBullshit(aBullshitModifier, aStacks[i]);
		}
		
		public static void applyBullshitA(EntityLivingBase aPlayer, Entity aEntity, ItemStack aStack) {
			mBullshitIteratorA.mPlayer = aPlayer;
			mBullshitIteratorA.mEntity = aEntity;
			if (aPlayer != null) applyArrayOfBullshit(mBullshitIteratorA, aPlayer.getLastActiveItems());
			if (aStack != null) applyBullshit(mBullshitIteratorA, aStack);
		}
		
		public static void applyBullshitB(EntityLivingBase aPlayer, Entity aEntity, ItemStack aStack) {
			mBullshitIteratorB.mPlayer = aPlayer;
			mBullshitIteratorB.mEntity = aEntity;
			if (aPlayer != null) applyArrayOfBullshit(mBullshitIteratorB, aPlayer.getLastActiveItems());
			if (aStack != null) applyBullshit(mBullshitIteratorB, aStack);
		}
		
		static final class BullshitIteratorA implements IBullshit {
			public EntityLivingBase mPlayer;
			public Entity mEntity;
			BullshitIteratorA() {}
			
			@Override
			public void calculateModifier(Enchantment aEnchantment, int aLevel) {
				aEnchantment.func_151367_b(mPlayer, mEntity, aLevel);
			}
		}
		
		static final class BullshitIteratorB implements IBullshit {
			public EntityLivingBase mPlayer;
			public Entity mEntity;
			BullshitIteratorB() {}
			
			@Override
			public void calculateModifier(Enchantment aEnchantment, int aLevel) {
				aEnchantment.func_151368_a(mPlayer, mEntity, aLevel);
			}
		}
		
		interface IBullshit {
			void calculateModifier(Enchantment aEnchantment, int aLevel);
		}
	}
	
	public static class Reflection {
		public static String getClassName(Object aObject) {
			return aObject == null ? "" : aObject.getClass().getName().substring(aObject.getClass().getName().lastIndexOf(".")+1);
		}
		public static String getLowercaseClass(Object aObject) {
			return aObject == null ? "" : aObject.getClass().getName().substring(aObject.getClass().getName().lastIndexOf(".")+1).toLowerCase();
		}
		
		public static Field getPublicField(Object aObject, String aField) {
			Field rField = null;
			try {
				rField = aObject.getClass().getDeclaredField(aField);
			} catch (Throwable e) {/*Do nothing*/}
			return rField;
		}
		
		public static Field setField(Object aObject, String aField, Object aValue) {
			return setField(aObject.getClass(), aObject, aField, aValue, T);
		}
		public static Field setField(Object aObject, String aField, Object aValue, boolean aLogErrors) {
			return setField(aObject.getClass(), aObject, aField, aValue, aLogErrors);
		}
		public static Field setField(Class<?> aClass, Object aObject, String aField, Object aValue) {
			return setField(aClass, aObject, aField, aValue, T);
		}
		public static Field setField(Class<?> aClass, Object aObject, String aField, Object aValue, boolean aLogErrors) {
			Field rField = null;
			try {
				rField = aClass.getDeclaredField(aField);
				rField.setAccessible(T);
				rField.set(aObject, aValue);
			} catch (Throwable e) {if (aLogErrors) e.printStackTrace(ERR);}
			return rField;
		}
		
		public static Field getField(Object aObject, String aField) {
			Field rField = null;
			try {
				rField = aObject.getClass().getDeclaredField(aField);
				rField.setAccessible(T);
			} catch (Throwable e) {/*Do nothing*/}
			return rField;
		}
		
		public static Field getField(Class<?> aObject, String aField) {
			Field rField = null;
			try {
				rField = aObject.getDeclaredField(aField);
				rField.setAccessible(T);
			} catch (Throwable e) {/*Do nothing*/}
			return rField;
		}
		
		public static Method getMethod(Class<?> aObject, String aMethod, Class<?>... aParameterTypes) {
			Method rMethod = null;
			try {
				rMethod = aObject.getMethod(aMethod, aParameterTypes);
				rMethod.setAccessible(T);
			} catch (Throwable e) {/*Do nothing*/}
			return rMethod;
		}
		
		public static Method getMethod(Object aObject, String aMethod, Class<?>... aParameterTypes) {
			Method rMethod = null;
			try {
				rMethod = aObject.getClass().getMethod(aMethod, aParameterTypes);
				rMethod.setAccessible(T);
			} catch (Throwable e) {/*Do nothing*/}
			return rMethod;
		}
		
		public static Field getField(Object aObject, String aField, boolean aPrivate, boolean aLogErrors) {
			try {
				Field tField = (aObject instanceof Class)?((Class<?>)aObject).getDeclaredField(aField):(aObject instanceof String)?Class.forName((String)aObject).getDeclaredField(aField):aObject.getClass().getDeclaredField(aField);
				if (aPrivate) tField.setAccessible(T);
				return tField;
			} catch (Throwable e) {
				if (aLogErrors) e.printStackTrace(ERR);
			}
			return null;
		}
		
		public static Object getFieldContent(Object aObject, String aField) {return getFieldContent(aObject, aField, T, T);}
		public static Object getFieldContent(Object aObject, String aField, boolean aPrivate, boolean aLogErrors) {
			try {
				Field tField = (aObject instanceof Class)?((Class<?>)aObject).getDeclaredField(aField):(aObject instanceof String)?Class.forName((String)aObject).getDeclaredField(aField):aObject.getClass().getDeclaredField(aField);
				if (aPrivate) tField.setAccessible(T);
				return tField.get(aObject instanceof Class || aObject instanceof String ? null : aObject);
			} catch (Throwable e) {
				if (aLogErrors) e.printStackTrace(ERR);
			}
			return null;
		}
		
		public static boolean setFieldContent(Object aObject, String aField, Object aValue) {return setFieldContent(aObject, aField, aValue, T, T);}
		public static boolean setFieldContent(Object aObject, String aField, Object aValue, boolean aPrivate, boolean aLogErrors) {
			try {
				Field tField = (aObject instanceof Class)?((Class<?>)aObject).getDeclaredField(aField):(aObject instanceof String)?Class.forName((String)aObject).getDeclaredField(aField):aObject.getClass().getDeclaredField(aField);
				if (aPrivate) tField.setAccessible(T);
				tField.set(aObject instanceof Class || aObject instanceof String ? null : aObject, aValue);
				return T;
			} catch (Throwable e) {
				if (aLogErrors) e.printStackTrace(ERR);
			}
			return F;
		}
		
		public static Object callPublicMethod(Object aObject, String aMethod, Object... aParameters) {
			return callMethod(aObject, aMethod, F, F, T, aParameters);
		}
		
		public static Object callPrivateMethod(Object aObject, String aMethod, Object... aParameters) {
			return callMethod(aObject, aMethod, T, F, T, aParameters);
		}
		public static Object callMethod(Object aObject, String aMethod, boolean aPrivate, boolean aUseUpperCasedDataTypes, boolean aLogErrors, Object... aParameters) {
			return callMethod(aObject, new String[] {aMethod}, aPrivate, aUseUpperCasedDataTypes, aLogErrors, aParameters);
		}
		public static Object callMethod(Object aObject, String[] aMethods, boolean aPrivate, boolean aUseUpperCasedDataTypes, boolean aLogErrors, Object... aParameters) {
			try {
				Class<?>[] tParameterTypes = new Class<?>[aParameters.length];
				for (byte i = 0; i < aParameters.length; i++) {
					if (aParameters[i] instanceof Class) {
						tParameterTypes[i] = (Class<?>)aParameters[i];
						aParameters[i] = null;
					} else {
						tParameterTypes[i] = aParameters[i].getClass();
					}
					if (!aUseUpperCasedDataTypes) {
						if (tParameterTypes[i] == Boolean.class) tParameterTypes[i] = boolean.class; else
						if (tParameterTypes[i] == Byte.class   ) tParameterTypes[i] = byte.class;    else
						if (tParameterTypes[i] == Short.class  ) tParameterTypes[i] = short.class;   else
						if (tParameterTypes[i] == Integer.class) tParameterTypes[i] = int.class;     else
						if (tParameterTypes[i] == Long.class   ) tParameterTypes[i] = long.class;    else
						if (tParameterTypes[i] == Float.class  ) tParameterTypes[i] = float.class;   else
						if (tParameterTypes[i] == Double.class ) tParameterTypes[i] = double.class;
					}
				}
				for (String aMethod : aMethods) {
					try {
						Method tMethod = aPrivate?
						(aObject instanceof Class)?((Class<?>)aObject).getDeclaredMethod(aMethod, tParameterTypes):aObject.getClass().getDeclaredMethod(aMethod, tParameterTypes):
						(aObject instanceof Class)?((Class<?>)aObject).getMethod        (aMethod, tParameterTypes):aObject.getClass().getMethod        (aMethod, tParameterTypes);
						if (aPrivate) tMethod.setAccessible(T);
						return tMethod.invoke(aObject, aParameters);
					} catch(Throwable e) {
						if (aLogErrors) e.printStackTrace(ERR);
					}
				}
			} catch (Throwable e) {
				if (aLogErrors) e.printStackTrace(ERR);
			}
			return null;
		}
		
		public static Object callConstructor(String aClass, int aConstructorIndex, Object aReplacementObject, boolean aLogErrors, Object... aParameters) {
			try {return callConstructor(Class.forName(aClass), aConstructorIndex, aReplacementObject, aLogErrors, aParameters);} catch (Throwable e) {if (aLogErrors) e.printStackTrace(ERR);} return aReplacementObject;
		}
		
		public static Object callConstructor(Class<?> aClass, int aConstructorIndex, Object aReplacementObject, boolean aLogErrors, Object... aParameters) {
			if (aConstructorIndex < 0) {
				try {
					for (Constructor<?> tConstructor : aClass.getConstructors()) {
						try {
							return tConstructor.newInstance(aParameters);
						} catch (Throwable e) {/*Do nothing*/}
					}
				} catch (Throwable e) {
					if (aLogErrors) e.printStackTrace(ERR);
				}
			} else {
				try {
					return aClass.getConstructors()[aConstructorIndex].newInstance(aParameters);
				} catch (Throwable e) {
					if (aLogErrors) e.printStackTrace(ERR);
				}
			}
			return aReplacementObject;
		}
	}
	
	public static class Inventories {
		@Deprecated public static boolean isConnectableNonInventoryPipe(Object aTileEntity, int aSide) {return F;}
		@Deprecated public static byte moveStackIntoPipe(IInventory aTileEntity1, Object aTarget, int[] aGrabSlots, byte aGrabFrom, byte aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, int aMaxTargetStackSize, int aMinTargetStackSize, int aMaxMoveAtOnce, int aMinMoveAtOnce) {return 0;}
		@Deprecated public static byte moveStackFromSlotAToSlotB(IInventory aTileEntity, IInventory aTarget, int aGrabFrom, int aPutTo, int aMaxTargetStackSize, int aMinTargetStackSize, int aMaxMoveAtOnce, int aMinMoveAtOnce) {return 0;}
		@Deprecated public static boolean isAllowedToTakeFromSlot(IInventory aTileEntity, int aSlot, byte aSide, ItemStack aStack) {return F;}
		@Deprecated public static boolean isAllowedToPutIntoSlot(IInventory aTileEntity, int aSlot, byte aSide, ItemStack aStack, int aMaxStackSize) {return F;}
		@Deprecated public static byte moveOneItemStack(Object aTileEntity1, Object aTileEntity2, byte aGrabFrom, byte aPutTo) {return 0;}
		@Deprecated public static byte moveOneItemStack(Object aTileEntity1, Object aTileEntity2, byte aGrabFrom, byte aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, int aMaxTargetStackSize, int aMinTargetStackSize, int aMaxMoveAtOnce, int aMinMoveAtOnce) {return 0;}
		@Deprecated public static byte moveOneItemStackIntoSlot(Object aTileEntity1, Object aTarget, byte aGrabFrom, int aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, int aMaxTargetStackSize, int aMinTargetStackSize, int aMaxMoveAtOnce, int aMinMoveAtOnce) {return 0;}
		@Deprecated public static byte moveFromSlotToSlot(IInventory aTileEntity1, IInventory aTileEntity2, int aGrabFrom, int aPutTo, List<ItemStack> aFilter, boolean aInvertFilter, int aMaxTargetStackSize, int aMinTargetStackSize, int aMaxMoveAtOnce, int aMinMoveAtOnce) {return 0;}
		
		public static void removeNullStacksFromInventory(IInventory aInventory) {
			if (aInventory != null) for (int i = 0, j = aInventory.getSizeInventory(); i < j; i++) {
				ItemStack tStack = aInventory.getStackInSlot(i);
				if (tStack != null && (tStack.stackSize == 0 || tStack.getItem() == null)) aInventory.setInventorySlotContents(i, null);
			}
		}
		
		public static boolean checkAchievements(EntityPlayer aPlayer, ItemStack aStack) {
			if (aPlayer == null) return F;
			
			if (aPlayer.worldObj.provider.dimensionId == DIM_NETHER) {
				aPlayer.triggerAchievement(AchievementList.openInventory);
				aPlayer.triggerAchievement(AchievementList.mineWood);
				aPlayer.triggerAchievement(AchievementList.buildWorkBench);
				aPlayer.triggerAchievement(AchievementList.buildPickaxe);
				aPlayer.triggerAchievement(AchievementList.buildFurnace);
				aPlayer.triggerAchievement(AchievementList.acquireIron);
				aPlayer.triggerAchievement(AchievementList.diamonds);
				aPlayer.triggerAchievement(AchievementList.portal);
			}
			
			if (ST.invalid(aStack)) return F;
			
			OreDictItemData tData = OM.association_(aStack);
			Item aItem = ST.item(aStack);
			Block aBlock = ST.block(aItem);
			String aRegName = ST.regName(aItem);
			
			if (WoodDictionary.WOODS.containsKey(aStack, T) || WoodDictionary.BEAMS.containsKey(aStack, T) || WoodDictionary.PLANKS_ANY.containsKey(aStack, T) || OD.logWood.is_(aStack) || OD.logRubber.is_(aStack)) {
				aPlayer.triggerAchievement(AchievementList.openInventory);
				aPlayer.triggerAchievement(AchievementList.mineWood);
			}
			
			if (aItem instanceof ItemHoe) {
				aPlayer.triggerAchievement(AchievementList.openInventory);
				aPlayer.triggerAchievement(AchievementList.mineWood);
				aPlayer.triggerAchievement(AchievementList.buildWorkBench);
				aPlayer.triggerAchievement(AchievementList.buildHoe);
			} else
			if (aItem instanceof ItemSword) {
				aPlayer.triggerAchievement(AchievementList.openInventory);
				aPlayer.triggerAchievement(AchievementList.mineWood);
				aPlayer.triggerAchievement(AchievementList.buildWorkBench);
				aPlayer.triggerAchievement(AchievementList.buildSword);
			} else
			if (aItem instanceof ItemPickaxe) {
				aPlayer.triggerAchievement(AchievementList.openInventory);
				aPlayer.triggerAchievement(AchievementList.mineWood);
				aPlayer.triggerAchievement(AchievementList.buildWorkBench);
				aPlayer.triggerAchievement(AchievementList.buildPickaxe);
				if (aItem != Items.wooden_pickaxe)
				aPlayer.triggerAchievement(AchievementList.buildBetterPickaxe);
			}
			
			if (MD.MC.owns(aRegName)) {
				if (aItem == Items.cooked_fished) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildPickaxe);
					aPlayer.triggerAchievement(AchievementList.buildFurnace);
					aPlayer.triggerAchievement(AchievementList.cookFish);
				} else
				if (aItem == Items.bread) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildHoe);
					aPlayer.triggerAchievement(AchievementList.makeBread);
				} else
				if (aItem == Items.leather || aItem == Items.beef || aItem == Items.cooked_beef || aItem == Items.saddle) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildSword);
					aPlayer.triggerAchievement(AchievementList.killCow);
				} else
				if (aBlock == Blocks.cake || aItem == Items.cake) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildHoe);
					aPlayer.triggerAchievement(AchievementList.bakeCake);
				} else
				if (aBlock == Blocks.furnace || aBlock == Blocks.lit_furnace) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildPickaxe);
					aPlayer.triggerAchievement(AchievementList.buildFurnace);
				} else
				if (aItem == Items.ghast_tear || aItem == Items.blaze_rod || aItem == Items.blaze_powder) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildPickaxe);
					aPlayer.triggerAchievement(AchievementList.buildFurnace);
					aPlayer.triggerAchievement(AchievementList.acquireIron);
					aPlayer.triggerAchievement(AchievementList.diamonds);
					aPlayer.triggerAchievement(AchievementList.portal);
				} else
				if (aItem == Items.brewing_stand || aBlock == Blocks.brewing_stand || aItem == Items.ender_eye) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildPickaxe);
					aPlayer.triggerAchievement(AchievementList.buildFurnace);
					aPlayer.triggerAchievement(AchievementList.acquireIron);
					aPlayer.triggerAchievement(AchievementList.diamonds);
					aPlayer.triggerAchievement(AchievementList.portal);
					aPlayer.triggerAchievement(AchievementList.blazeRod);
				} else
				if (aBlock == Blocks.enchanting_table) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildPickaxe);
					aPlayer.triggerAchievement(AchievementList.buildFurnace);
					aPlayer.triggerAchievement(AchievementList.acquireIron);
					aPlayer.triggerAchievement(AchievementList.diamonds);
					aPlayer.triggerAchievement(AchievementList.enchantments);
				} else
				if (aBlock == Blocks.bookshelf) {
					aPlayer.triggerAchievement(AchievementList.bookcase);
				}
			}
			
			if (MD.TF.owns(aRegName)) try {
				if (IL.TF_Trophy_Naga.equal(aStack, F, T)) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildSword);
					aPlayer.triggerAchievement(AchievementList.killEnemy);
					aPlayer.triggerAchievement(TFAchievementPage.twilightPortal);
					aPlayer.triggerAchievement(TFAchievementPage.twilightArrival);
					aPlayer.triggerAchievement(TFAchievementPage.twilightHunter);
					aPlayer.triggerAchievement(TFAchievementPage.twilightKillNaga);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressNaga);
				} else
				if (IL.TF_Trophy_Lich.equal(aStack, F, T)) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildSword);
					aPlayer.triggerAchievement(AchievementList.killEnemy);
					aPlayer.triggerAchievement(TFAchievementPage.twilightPortal);
					aPlayer.triggerAchievement(TFAchievementPage.twilightArrival);
					aPlayer.triggerAchievement(TFAchievementPage.twilightHunter);
					aPlayer.triggerAchievement(TFAchievementPage.twilightKillLich);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressLich);
				} else
				if (IL.TF_Trophy_Hydra.equal(aStack, F, T)) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildSword);
					aPlayer.triggerAchievement(AchievementList.killEnemy);
					aPlayer.triggerAchievement(TFAchievementPage.twilightPortal);
					aPlayer.triggerAchievement(TFAchievementPage.twilightArrival);
					aPlayer.triggerAchievement(TFAchievementPage.twilightHunter);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressLabyrinth);
					aPlayer.triggerAchievement(TFAchievementPage.twilightKillHydra);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressHydra);
				} else
				if (IL.TF_Trophy_Urghast.equal(aStack, F, T)) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildSword);
					aPlayer.triggerAchievement(AchievementList.killEnemy);
					aPlayer.triggerAchievement(TFAchievementPage.twilightPortal);
					aPlayer.triggerAchievement(TFAchievementPage.twilightArrival);
					aPlayer.triggerAchievement(TFAchievementPage.twilightHunter);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressTrophyPedestal);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressKnights);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressUrghast);
				} else
				if (IL.TF_Trophy_Snowqueen.equal(aStack, F, T)) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildSword);
					aPlayer.triggerAchievement(AchievementList.killEnemy);
					aPlayer.triggerAchievement(TFAchievementPage.twilightPortal);
					aPlayer.triggerAchievement(TFAchievementPage.twilightArrival);
					aPlayer.triggerAchievement(TFAchievementPage.twilightHunter);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressYeti);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressGlacier);
				} else
				if (IL.TF_Lamp_of_Cinders.equal(aStack, T, T)) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildSword);
					aPlayer.triggerAchievement(AchievementList.killEnemy);
					aPlayer.triggerAchievement(TFAchievementPage.twilightPortal);
					aPlayer.triggerAchievement(TFAchievementPage.twilightArrival);
					aPlayer.triggerAchievement(TFAchievementPage.twilightHunter);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressTroll);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressThorns);
				} else
				if (IL.TF_Cube_of_Annihilation.equal(aStack, T, T)) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildSword);
					aPlayer.triggerAchievement(AchievementList.killEnemy);
					aPlayer.triggerAchievement(TFAchievementPage.twilightPortal);
					aPlayer.triggerAchievement(TFAchievementPage.twilightArrival);
					aPlayer.triggerAchievement(TFAchievementPage.twilightHunter);
					aPlayer.triggerAchievement(TFAchievementPage.twilightKillNaga);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressNaga);
					aPlayer.triggerAchievement(TFAchievementPage.twilightKillLich);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressLich);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressLabyrinth);
					aPlayer.triggerAchievement(TFAchievementPage.twilightKillHydra);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressHydra);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressTrophyPedestal);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressKnights);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressUrghast);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressYeti);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressGlacier);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressTroll);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressThorns);
					aPlayer.triggerAchievement(TFAchievementPage.twilightProgressCastle);
				}
			} catch(Throwable e) {e.printStackTrace(ERR);}
			
			if (tData != null && !tData.mPrefix.containsAny(TD.Prefix.ORE_PROCESSING_BASED, TD.Prefix.ORE)) {
				if (ANY.Diamond.mToThis.contains(tData.mMaterial.mMaterial) && tData.mPrefix.contains(TD.Prefix.GEM_BASED)) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildPickaxe);
					aPlayer.triggerAchievement(AchievementList.buildFurnace);
					aPlayer.triggerAchievement(AchievementList.acquireIron);
					aPlayer.triggerAchievement(AchievementList.diamonds);
				}
				if (ANY.Iron.mToThis.contains(tData.mMaterial.mMaterial)) {
					aPlayer.triggerAchievement(AchievementList.openInventory);
					aPlayer.triggerAchievement(AchievementList.mineWood);
					aPlayer.triggerAchievement(AchievementList.buildWorkBench);
					aPlayer.triggerAchievement(AchievementList.buildPickaxe);
					aPlayer.triggerAchievement(AchievementList.buildFurnace);
					aPlayer.triggerAchievement(AchievementList.acquireIron);
				}
			}
			return T;
		}
		
		public static boolean addStackToPlayerInventory(EntityPlayer aPlayer, ItemStack aStack) {
			return addStackToPlayerInventory(aPlayer, aStack, F);
		}
		public static boolean addStackToPlayerInventory(EntityPlayer aPlayer, ItemStack aStack, boolean aCurrentSlotFirst) {
			return addStackToPlayerInventory(aPlayer, aPlayer.inventory, aStack, aCurrentSlotFirst);
		}
		public static boolean addStackToPlayerInventory(EntityPlayer aPlayer, IInventory aInventory, ItemStack aStack, boolean aCurrentSlotFirst) {
			if (aInventory != null && ST.valid(aStack)) {
				UT.Inventories.checkAchievements(aPlayer, aStack);
				
				for (int i = 0; i < 36; i++) if (aPlayer == null || i != aPlayer.inventory.currentItem) {
					ItemStack tStack = aInventory.getStackInSlot(i);
					if (ST.equal(tStack, aStack) && aStack.stackSize + tStack.stackSize <= tStack.getMaxStackSize()) {
						tStack.stackSize += aStack.stackSize;
						ST.update(aPlayer);
						return T;
					}
				}
				if (aCurrentSlotFirst && aPlayer != null) {
					ItemStack tStack = aInventory.getStackInSlot(aPlayer.inventory.currentItem);
					if (tStack == null || tStack.stackSize == 0) {
						aInventory.setInventorySlotContents(aPlayer.inventory.currentItem, aStack);
						ST.update(aPlayer);
						return T;
					} else if (ST.equal(tStack, aStack) && aStack.stackSize + tStack.stackSize <= tStack.getMaxStackSize()) {
						tStack.stackSize += aStack.stackSize;
						ST.update(aPlayer);
						return T;
					}
				}
				for (int i = 0; i < 36; i++) if (aPlayer == null || i != aPlayer.inventory.currentItem) {
					ItemStack tStack = aInventory.getStackInSlot(i);
					if (tStack == null || tStack.stackSize <= 0) {
						aInventory.setInventorySlotContents(i, aStack);
						ST.update(aPlayer);
						return T;
					}
				}
				if (!aCurrentSlotFirst && aPlayer != null) {
					ItemStack tStack = aInventory.getStackInSlot(aPlayer.inventory.currentItem);
					if (tStack == null || tStack.stackSize == 0) {
						aInventory.setInventorySlotContents(aPlayer.inventory.currentItem, aStack);
						ST.update(aPlayer);
						return T;
					} else if (ST.equal(tStack, aStack) && aStack.stackSize + tStack.stackSize <= tStack.getMaxStackSize()) {
						tStack.stackSize += aStack.stackSize;
						ST.update(aPlayer);
						return T;
					}
				}
			}
			return F;
		}
		
		public static boolean addStackToPlayerInventoryOrDrop(EntityPlayer aPlayer, ItemStack aStack) {
			return addStackToPlayerInventoryOrDrop(aPlayer, aStack, F, aPlayer.worldObj, aPlayer.posX, aPlayer.posY, aPlayer.posZ);
		}
		
		public static boolean addStackToPlayerInventoryOrDrop(EntityPlayer aPlayer, ItemStack aStack, boolean aCurrentSlotFirst) {
			return addStackToPlayerInventoryOrDrop(aPlayer, aStack, aCurrentSlotFirst, aPlayer.worldObj, aPlayer.posX, aPlayer.posY, aPlayer.posZ);
		}
		
		public static boolean addStackToPlayerInventoryOrDrop(EntityPlayer aPlayer, ItemStack aStack, World aWorld, double aX, double aY, double aZ) {
			return addStackToPlayerInventoryOrDrop(aPlayer, aStack, F, aWorld, aX, aY, aZ);
		}
		
		public static boolean addStackToPlayerInventoryOrDrop(EntityPlayer aPlayer, ItemStack aStack, boolean aCurrentSlotFirst, World aWorld, double aX, double aY, double aZ) {
			if (ST.valid(aStack) && !addStackToPlayerInventory(aPlayer, aStack, aCurrentSlotFirst)) ST.drop(aWorld, aX, aY, aZ, aStack);
			return T;
		}
		
		public static boolean addStackToPlayerInventoryOrDrop(EntityPlayer aPlayer, IInventory aInventory, ItemStack aStack, boolean aCurrentSlotFirst, World aWorld, double aX, double aY, double aZ) {
			if (ST.valid(aStack) && !addStackToPlayerInventory(aPlayer, aInventory, aStack, aCurrentSlotFirst)) ST.drop(aWorld, aX, aY, aZ, aStack);
			return T;
		}
		
		public static ItemStack getProjectile(TagData aProjectileType, IInventory aInventory) {
			if (aInventory != null) for (int i = 0, j = aInventory.getSizeInventory(); i < j; i++) {
				ItemStack rStack = aInventory.getStackInSlot(i);
				if (ST.valid(rStack) && rStack.getItem() instanceof IItemProjectile && ((IItemProjectile)rStack.getItem()).hasProjectile(aProjectileType, rStack)) return ST.update(rStack);
			}
			return null;
		}
	}
	
	public static class Sounds {
		public static boolean MULTITHREADED = F;
		public static List<PlayedSound> sPlayedSounds = new ArrayListNoNulls<>();
		
		public static boolean play(String aSoundName, int aTimeUntilNextSound, float aSoundStrength) {
			if (!CODE_CLIENT || cpw.mods.fml.common.FMLCommonHandler.instance().getEffectiveSide().isServer()) return F;
			return play(aSoundName, aTimeUntilNextSound, aSoundStrength, GT_API.api_proxy.getThePlayer());
		}
		
		public static boolean play(String aSoundName, int aTimeUntilNextSound, float aSoundStrength, Entity aEntity) {
			if (!CODE_CLIENT || aEntity == null || cpw.mods.fml.common.FMLCommonHandler.instance().getEffectiveSide().isServer()) return F;
			return play(aSoundName, aTimeUntilNextSound, aSoundStrength, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
		}
		
		public static boolean play(String aSoundName, int aTimeUntilNextSound, float aSoundStrength, int aX, int aY, int aZ) {
			return play(aSoundName, aTimeUntilNextSound, aSoundStrength, new ChunkCoordinates(aX, aY, aZ));
		}
		
		public static boolean play(String aSoundName, int aTimeUntilNextSound, float aSoundStrength, ChunkCoordinates aCoords) {
			if (aCoords == null) return play(aSoundName, aTimeUntilNextSound, aSoundStrength);
			if (!CODE_CLIENT || cpw.mods.fml.common.FMLCommonHandler.instance().getEffectiveSide().isServer()) return F;
			return play(aSoundName, aTimeUntilNextSound, aSoundStrength, 0.9F + RNGSUS.nextFloat() * 0.2F, aCoords.posX, aCoords.posY, aCoords.posZ);
		}
		
		public static boolean play(String aSoundName, int aTimeUntilNextSound, float aSoundStrength, float aSoundModulation, int aX, int aY, int aZ) {
			return play(aSoundName, aTimeUntilNextSound, aSoundStrength, aSoundModulation, new ChunkCoordinates(aX, aY, aZ));
		}
		
		public static boolean play(String aSoundName, int aTimeUntilNextSound, float aSoundStrength, float aSoundModulation, ChunkCoordinates aCoords) {
			if (!CODE_CLIENT || cpw.mods.fml.common.FMLCommonHandler.instance().getEffectiveSide().isServer()) return F;
			EntityPlayer aPlayer = GT_API.api_proxy.getThePlayer();
			if (aPlayer == null || !aPlayer.worldObj.isRemote || Code.stringInvalid(aSoundName)) return F;
			if (MULTITHREADED)
				new Thread(new ThreadedSound(aPlayer.worldObj, UT.Code.roundDown(aCoords.posX), UT.Code.roundDown(aCoords.posY), UT.Code.roundDown(aCoords.posZ), aTimeUntilNextSound, aSoundName, aSoundStrength, aSoundModulation), "Sound Effect").start();
			else
				new ThreadedSound(aPlayer.worldObj, UT.Code.roundDown(aCoords.posX), UT.Code.roundDown(aCoords.posY), UT.Code.roundDown(aCoords.posZ), aTimeUntilNextSound, aSoundName, aSoundStrength, aSoundModulation).run();
			return T;
		}
		
		public static boolean send(World aWorld, String aSoundName, float aSoundStrength, float aSoundModulation, int aX, int aY, int aZ) {
			return send(aWorld, aSoundName, aSoundStrength, aSoundModulation, new ChunkCoordinates(aX, aY, aZ));
		}
		public static boolean send(String aSoundName, World aWorld, ChunkCoordinates aCoords) {
			return send(aWorld, aSoundName, 1.0F, 1.0F, aCoords);
		}
		public static boolean send(String aSoundName, TileEntity aTileEntity) {
			return send(aTileEntity.getWorldObj(), aSoundName, 1.0F, 1.0F, aTileEntity.xCoord, aTileEntity.yCoord, aTileEntity.zCoord);
		}
		public static boolean send(String aSoundName, float aSoundStrength, float aSoundModulation, TileEntity aTileEntity) {
			return send(aTileEntity.getWorldObj(), aSoundName, aSoundStrength, aSoundModulation, aTileEntity.xCoord, aTileEntity.yCoord, aTileEntity.zCoord);
		}
		public static boolean send(String aSoundName, Entity aEntity) {
			return send(aEntity.worldObj, aSoundName, 1.0F, 1.0F, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
		}
		public static boolean send(String aSoundName, float aSoundStrength, float aSoundModulation, Entity aEntity) {
			return send(aEntity.worldObj, aSoundName, aSoundStrength, aSoundModulation, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
		}
		public static boolean send(World aWorld, String aSoundName, float aSoundStrength, float aSoundModulation, Entity aEntity) {
			return send(aWorld, aSoundName, aSoundStrength, aSoundModulation, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
		}
		
		public static boolean send(World aWorld, String aSoundName, float aSoundStrength, float aSoundModulation, ChunkCoordinates aCoords) {
			if (Code.stringInvalid(aSoundName) || aWorld == null || aWorld.isRemote) return F;
			NW_API.sendToAllPlayersInRange(new PacketSound(aSoundName, aSoundStrength, aSoundModulation, aCoords), aWorld, aCoords);
			return T;
		}
		
		public static class PlayedSound {
			public final String mSoundName;
			public final int mX, mY, mZ;
			public int mTimer = 0;
			
			public PlayedSound(String aSoundName, int aX, int aY, int aZ, int aTimer) {
				mSoundName = aSoundName==null?"":aSoundName;
				mX = aX;
				mY = aY;
				mZ = aZ;
			}
			
			@Override
			public boolean equals(Object aObject) {
				if (aObject != null && aObject instanceof PlayedSound) {
					return ((PlayedSound)aObject).mX == mX && ((PlayedSound)aObject).mY == mY && ((PlayedSound)aObject).mZ == mZ && ((PlayedSound)aObject).mSoundName.equals(mSoundName);
				}
				return F;
			}
			
			@Override
			public int hashCode() {
				return mX+mY+mZ+mSoundName.hashCode();
			}
		}
		
		public static class ThreadedSound implements Runnable {
			private final int mX, mY, mZ, mTimeUntilNextSound;
			private final World mWorld;
			private final String mSoundName;
			private final float mSoundStrength, mSoundModulation;
			
			public ThreadedSound(World aWorld, int aX, int aY, int aZ, int aTimeUntilNextSound, String aSoundName, float aSoundStrength, float aSoundModulation) {
				mWorld = aWorld; mX = aX; mY = aY; mZ = aZ; mTimeUntilNextSound = aTimeUntilNextSound; mSoundName = aSoundName; mSoundStrength = aSoundStrength; mSoundModulation = aSoundModulation;
			}
			
			@Override
			public void run() {
				try {
					PlayedSound tSound = new PlayedSound(mSoundName, mX, mY, mZ, mTimeUntilNextSound);
					if (sPlayedSounds.contains(tSound)) return;
					mWorld.playSound(mX+0.5, mY+0.5, mZ+0.5, mSoundName, mSoundStrength, mSoundModulation, T);
					sPlayedSounds.add(tSound);
				} catch(Throwable e) {/**/}
			}
		}
	}
	
	public static class Entities {
		/** Sends Messages to a Player */
		public static void sendchat(Object aPlayer, String... aChatMessages) {
			if (aPlayer instanceof EntityPlayerMP) for (String aMessage : aChatMessages) ((EntityPlayerMP)aPlayer).addChatComponentMessage(new ChatComponentText(aMessage));
		}
		
		/** Sends Messages to a Player */
		public static void sendchat(Object aPlayer, IChatComponent... aChatMessages) {
			if (aPlayer instanceof EntityPlayerMP) for (IChatComponent aMessage : aChatMessages) ((EntityPlayerMP)aPlayer).addChatComponentMessage(aMessage);
		}
		
		/** Sends Messages to a Player */
		public static void sendchat(Object aPlayer, @SuppressWarnings("rawtypes") List aChatMessages, boolean aSkipFirst) {
			if (aChatMessages != null && aPlayer instanceof EntityPlayerMP) for (Object aMessage : aChatMessages) if (aSkipFirst) aSkipFirst=F; else ((EntityPlayerMP)aPlayer).addChatComponentMessage(aMessage instanceof IChatComponent ? (IChatComponent)aMessage : new ChatComponentText(aMessage.toString()));
		}
		
		public static void chat(Object aPlayer, String... aChatMessages) {
			if (aPlayer == null) aPlayer = GT_API.api_proxy.getThePlayer();
			if (aPlayer instanceof EntityPlayer) for (String aMessage : aChatMessages) ((EntityPlayer)aPlayer).addChatComponentMessage(new ChatComponentText(aMessage));
		}
		
		public static void chat(Object aPlayer, IChatComponent... aChatMessages) {
			if (aPlayer == null) aPlayer = GT_API.api_proxy.getThePlayer();
			if (aPlayer instanceof EntityPlayer) for (IChatComponent aMessage : aChatMessages) ((EntityPlayer)aPlayer).addChatComponentMessage(aMessage);
		}
		
		public static void chat(Object aPlayer, @SuppressWarnings("rawtypes") List aChatMessages, boolean aSkipFirst) {
			if (aPlayer == null) aPlayer = GT_API.api_proxy.getThePlayer();
			if (aChatMessages != null && aPlayer instanceof EntityPlayer) for (Object aMessage : aChatMessages) if (aSkipFirst) aSkipFirst=F; else ((EntityPlayer)aPlayer).addChatComponentMessage(aMessage instanceof IChatComponent ? (IChatComponent)aMessage : new ChatComponentText(aMessage.toString()));
		}
		
		
		
		public static boolean isWearingFullFrostHazmat(EntityLivingBase aEntity) {
			if (isCreative(aEntity)) return T;
			for (byte i = 1; i < 5; i++) if (!ArmorsGT.HAZMATS_FROST.contains(aEntity.getEquipmentInSlot(i), T)) return F;
			return T;
		}
		
		public static boolean isWearingFullHeatHazmat(EntityLivingBase aEntity) {
			if (isCreative(aEntity) || aEntity.getClass() == EntityWither.class || aEntity.getClass() == EntityBlaze.class || aEntity.getClass() == EntityPigZombie.class || aEntity.getClass() == EntityMagmaCube.class || aEntity.getClass() == EntityGhast.class) return T;
			for (byte i = 1; i < 5; i++) if (!ArmorsGT.HAZMATS_HEAT.contains(aEntity.getEquipmentInSlot(i), T)) return F;
			return T;
		}
		
		public static boolean isWearingFullBioHazmat(EntityLivingBase aEntity) {
			if (isCreative(aEntity) || aEntity.getClass() == EntityWither.class || aEntity.getClass() == EntityIronGolem.class) return T;
			for (byte i = 1; i < 5; i++) if (!ArmorsGT.HAZMATS_BIO.contains(aEntity.getEquipmentInSlot(i), T)) return F;
			return T;
		}
		
		public static boolean isWearingFullChemHazmat(EntityLivingBase aEntity) {
			if (isCreative(aEntity)) return T;
			for (byte i = 1; i < 5; i++) if (!ArmorsGT.HAZMATS_CHEM.contains(aEntity.getEquipmentInSlot(i), T)) return F;
			return T;
		}
		
		public static boolean isWearingFullInsectHazmat(EntityLivingBase aEntity) {
			if (isCreative(aEntity) || aEntity.getClass() == EntityWither.class || aEntity.getClass() == EntityIronGolem.class) return T;
			for (byte i = 1; i < 5; i++) if (!ArmorsGT.HAZMATS_INSECTS.contains(aEntity.getEquipmentInSlot(i), T)) return F;
			return T;
		}
		
		public static boolean isWearingFullRadioHazmat(EntityLivingBase aEntity) {
			if (isCreative(aEntity) || aEntity.getClass() == EntityWither.class || aEntity.getClass() == EntityIronGolem.class) return T;
			for (byte i = 1; i < 5; i++) if (!ArmorsGT.HAZMATS_RADIOACTIVE.contains(aEntity.getEquipmentInSlot(i), T)) return F;
			return T;
		}
		
		public static boolean isWearingFullElectroHazmat(EntityLivingBase aEntity) {
			if (isCreative(aEntity)) return T;
			for (byte i = 1; i < 5; i++) if (!ArmorsGT.HAZMATS_LIGHTNING.contains(aEntity.getEquipmentInSlot(i), T)) return F;
			return T;
		}
		
		public static boolean isWearingFullGasHazmat(EntityLivingBase aEntity) {
			if (isCreative(aEntity) || aEntity.getClass() == EntityWither.class || aEntity.getClass() == EntityIronGolem.class) return T;
			for (byte i = 1; i < 5; i++) if (!ArmorsGT.HAZMATS_GAS.contains(aEntity.getEquipmentInSlot(i), T)) return F;
			return T;
		}
		
		
		
		public static boolean isSlimeCreature(EntityLivingBase aEntity) {
			return aEntity instanceof EntitySlime || UT.Reflection.getLowercaseClass(aEntity).contains("slime");
		}
		public static boolean isEnderCreature(EntityLivingBase aEntity) {
			return aEntity instanceof EntityEnderman || UT.Reflection.getLowercaseClass(aEntity).contains("ender");
		}
		public static boolean isZombieCreature(EntityLivingBase aEntity) {
			return aEntity instanceof EntityZombie || UT.Reflection.getLowercaseClass(aEntity).contains("zombie");
		}
		public static boolean isWereCreature(EntityLivingBase aEntity) {
			if (aEntity instanceof EntityPlayer) {
				if ("Bear989Sr".equalsIgnoreCase(aEntity.getCommandSenderName())) return T;
				IExtendedEntityProperties tWerewolfProperty = aEntity.getExtendedProperties("WerewolfPlayer");
				if (tWerewolfProperty == null) return F;
				Object tReturned = UT.Reflection.callPublicMethod(tWerewolfProperty, "getWerewolf");
				return tReturned instanceof Boolean && (Boolean)tReturned;
			}
			if (aEntity.getClass().getName().indexOf(".") < 0) return F;
			String tClassName = UT.Reflection.getLowercaseClass(aEntity);
			return tClassName.contains("wwolf") || tClassName.contains("villagerwere") || tClassName.contains("wolfman") || tClassName.contains("werewolf") || tClassName.contains("alphawolf") || tClassName.contains("tamewere");
		}
		
		public static float getHeatDamageFromItem(ItemStack aStack) {
			OreDictItemData tData = OM.anydata(aStack);
			return tData==null?0:(tData.mPrefix==null?0:tData.mPrefix.mHeatDamage) + (tData.hasValidMaterialData()?tData.mMaterial.mMaterial.mHeatDamage:0);
		}
		
		public static int getRadioactivityLevel(ItemStack aStack) {
			return getRadioactivityLevel(aStack, OM.anydata(aStack));
		}
		public static int getRadioactivityLevel(ItemStack aStack, OreDictItemData aData) {
			long rLevel = 0;
			if (aData != null && aData.hasValidMaterialData()) {
				for (ObjectStack<Enchantment> tEnchantment : aData.mMaterial.mMaterial.mEnchantmentTools ) if (tEnchantment.mObject instanceof Enchantment_Radioactivity) rLevel = Math.max(rLevel, tEnchantment.mAmount);
				for (ObjectStack<Enchantment> tEnchantment : aData.mMaterial.mMaterial.mEnchantmentArmors) if (tEnchantment.mObject instanceof Enchantment_Radioactivity) rLevel = Math.max(rLevel, tEnchantment.mAmount);
			}
			rLevel = Math.max(rLevel, EnchantmentHelper.getEnchantmentLevel(Enchantment_Radioactivity.INSTANCE.effectId, aStack));
			return Code.bindInt(rLevel);
		}
		
		public static boolean isImmuneToBreathingGases(EntityLivingBase aEntity) {
			return isWearingFullGasHazmat(aEntity);
		}
		
		public static boolean applyTemperatureDamage(Entity aEntity, long aTemperature, float aMultiplier) {
			if (aTemperature > 320) return applyHeatDamage (aEntity, (aMultiplier * (aTemperature - 300)) / 50.0F);
			if (aTemperature < 260) return applyFrostDamage(aEntity, (aMultiplier * (270 - aTemperature)) / 25.0F);
			return F;
		}
		
		public static boolean applyChemDamage(Entity aEntity, float aDamage) {
			if (aDamage > 0 && aEntity instanceof EntityLivingBase && ((EntityLivingBase)aEntity).isEntityAlive() && aEntity.getClass() != EntitySkeleton.class && !isWearingFullChemHazmat(((EntityLivingBase)aEntity))) {
				aEntity.attackEntityFrom(DamageSources.getChemDamage(), (MD.TFC.mLoaded || MD.TFCP.mLoaded) ? TFC_DAMAGE_MULTIPLIER * aDamage : aDamage);
				PotionEffect tEffect = null;
				((EntityLivingBase)aEntity).addPotionEffect(new PotionEffect(Potion.poison.id, Math.max(20, (int)(aDamage * 100 + Math.max(0, ((tEffect = ((EntityLivingBase)aEntity).getActivePotionEffect(Potion.poison))==null?0:tEffect.getDuration())))), 1));
				return T;
			}
			return F;
		}
		
		public static boolean applyHeatDamage(Entity aEntity, float aDamage) {
			if (aDamage > 0 && aEntity instanceof EntityLivingBase && ((EntityLivingBase)aEntity).isEntityAlive() && aEntity.getClass() != EntityBlaze.class && ((EntityLivingBase)aEntity).getActivePotionEffect(Potion.fireResistance) == null && !isWearingFullHeatHazmat(((EntityLivingBase)aEntity))) {
				aEntity.attackEntityFrom(DamageSources.getHeatDamage(), (MD.TFC.mLoaded || MD.TFCP.mLoaded) ? TFC_DAMAGE_MULTIPLIER * aDamage : aDamage);
				return T;
			}
			return F;
		}
		
		public static boolean applyFrostDamage(Entity aEntity, float aDamage) {
			if (aDamage > 0 && aEntity instanceof EntityLivingBase && ((EntityLivingBase)aEntity).isEntityAlive() && !isWearingFullFrostHazmat(((EntityLivingBase)aEntity))) {
				aEntity.attackEntityFrom(DamageSources.getFrostDamage(), (MD.TFC.mLoaded || MD.TFCP.mLoaded) ? TFC_DAMAGE_MULTIPLIER * aDamage : aDamage);
				return T;
			}
			return F;
		}
		
		public static boolean applyElectricityDamage(Entity aEntity, long aVoltage, long aAmperage) {
			long aDamage = Code.tierMax(aVoltage) * aAmperage * 4;
			if (aDamage > 0 && aEntity instanceof EntityLivingBase && ((EntityLivingBase)aEntity).isEntityAlive() && !isWearingFullElectroHazmat(((EntityLivingBase)aEntity))) {
				aEntity.attackEntityFrom(DamageSources.getElectricDamage(), (MD.TFC.mLoaded || MD.TFCP.mLoaded) ? TFC_DAMAGE_MULTIPLIER * aDamage : aDamage);
				return T;
			}
			return F;
		}
		
		public static boolean applyElectricityDamage(Entity aEntity, long aWattage) {
			long aDamage = Code.tierMax(aWattage) * 4;
			if (aDamage > 0 && aEntity instanceof EntityLivingBase && ((EntityLivingBase)aEntity).isEntityAlive() && !isWearingFullElectroHazmat(((EntityLivingBase)aEntity))) {
				aEntity.attackEntityFrom(DamageSources.getElectricDamage(), (MD.TFC.mLoaded || MD.TFCP.mLoaded) ? TFC_DAMAGE_MULTIPLIER * aDamage : aDamage);
				return T;
			}
			return F;
		}
		
		public static boolean applyRadioactivity(Entity aEntity, int aLevel, int aAmountOfItems) {
			if (aLevel > 0 && aEntity instanceof EntityLivingBase && ((EntityLivingBase)aEntity).isEntityAlive() && ((EntityLivingBase)aEntity).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD && ((EntityLivingBase)aEntity).getCreatureAttribute() != EnumCreatureAttribute.ARTHROPOD && !isWearingFullRadioHazmat(((EntityLivingBase)aEntity))) {
				PotionEffect tEffect = null;
				((EntityLivingBase)aEntity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id , aLevel * 140 * aAmountOfItems + Math.max(0, ((tEffect = ((EntityLivingBase)aEntity).getActivePotionEffect(Potion.moveSlowdown                         ))==null?0:tEffect.getDuration())), (int)UT.Code.bind(0, 5, (5*aLevel) / 7)));
				((EntityLivingBase)aEntity).addPotionEffect(new PotionEffect(Potion.digSlowdown.id  , aLevel * 150 * aAmountOfItems + Math.max(0, ((tEffect = ((EntityLivingBase)aEntity).getActivePotionEffect(Potion.digSlowdown                          ))==null?0:tEffect.getDuration())), (int)UT.Code.bind(0, 5, (5*aLevel) / 7)));
				((EntityLivingBase)aEntity).addPotionEffect(new PotionEffect(Potion.confusion.id    , aLevel * 130 * aAmountOfItems + Math.max(0, ((tEffect = ((EntityLivingBase)aEntity).getActivePotionEffect(Potion.confusion                            ))==null?0:tEffect.getDuration())), (int)UT.Code.bind(0, 5, (5*aLevel) / 7)));
				((EntityLivingBase)aEntity).addPotionEffect(new PotionEffect(Potion.weakness.id     , aLevel * 150 * aAmountOfItems + Math.max(0, ((tEffect = ((EntityLivingBase)aEntity).getActivePotionEffect(Potion.weakness                             ))==null?0:tEffect.getDuration())), (int)UT.Code.bind(0, 5, (5*aLevel) / 7)));
				((EntityLivingBase)aEntity).addPotionEffect(new PotionEffect(Potion.hunger.id       , aLevel * 130 * aAmountOfItems + Math.max(0, ((tEffect = ((EntityLivingBase)aEntity).getActivePotionEffect(Potion.hunger                               ))==null?0:tEffect.getDuration())), (int)UT.Code.bind(0, 5, (5*aLevel) / 7)));
				if (PotionsGT.ID_RADIATION > 0)
				((EntityLivingBase)aEntity).addPotionEffect(new PotionEffect(PotionsGT.ID_RADIATION , aLevel * 180 * aAmountOfItems + Math.max(0, ((tEffect = ((EntityLivingBase)aEntity).getActivePotionEffect(Potion.potionTypes[PotionsGT.ID_RADIATION]  ))==null?0:tEffect.getDuration())), (int)UT.Code.bind(0, 4, (5*aLevel) / 7))); // can only be between 0 and 4, or else IC2 WILL crash!!!
				return T;
			}
			return F;
		}
		
		/** checks if a Player is actually a Player and not a FakePlayer or something. */
		public static boolean isPlayer(Object aPlayer) {
			return aPlayer instanceof EntityPlayerMP && !(aPlayer instanceof FakePlayer);
		}
		
		public static boolean isCreative(Object aPlayer) {
			return aPlayer instanceof EntityPlayer && ((EntityPlayer)aPlayer).capabilities.isCreativeMode;
		}
		
		public static boolean isInvincible(Object aPlayer) {
			return aPlayer instanceof EntityPlayer && ((EntityPlayer)aPlayer).capabilities.isCreativeMode;
		}
		
		public static boolean hasInfiniteItems(Object aPlayer) {
			return aPlayer instanceof EntityPlayer && ((EntityPlayer)aPlayer).capabilities.isCreativeMode;
		}
		
		public static boolean consumeCurrentItem(EntityPlayer aPlayer) {
			if (aPlayer == null) return F;
			if (hasInfiniteItems(aPlayer)) return T;
			ItemStack aStack = aPlayer.inventory.getStackInSlot(aPlayer.inventory.currentItem);
			if (ST.invalid(aStack)) return F;
			if (aStack.stackSize != NEI_INFINITE && --aStack.stackSize <= 0) aPlayer.inventory.setInventorySlotContents(aPlayer.inventory.currentItem, NI);
			Inventories.addStackToPlayerInventoryOrDrop(aPlayer, ST.container(aStack, T), F);
			return T;
		}
	}
	
	@Deprecated public static class Worlds {
		@Deprecated public static ItemStack suckOneItemStackAt(World aWorld, double aX, double aY, double aZ, double aL, double aH, double aW) {return WD.suck(aWorld, aX, aY, aZ, aL, aH, aW);}
		@Deprecated public static boolean isSideObstructed(World aWorld, int aX, int aY, int aZ, byte aSide) {return WD.obstructed(aWorld, aX, aY, aZ, aSide);}
		@Deprecated public static MovingObjectPosition getMovingObjectPositionFromPlayer(World aWorld, EntityPlayer aPlayer, boolean aFlag) {return WD.getMOP(aWorld, aPlayer, aFlag);}
		@Deprecated public static boolean isRealDimension(int aDimensionID) {return T;}
		@Deprecated public static boolean moveEntityToDimensionAtCoords(Entity aEntity, int aDimension, double aX, double aY, double aZ) {return WD.move(aEntity, aDimension, aX, aY, aZ);}
		@Deprecated public static DelegatorTileEntity<TileEntity> getTileEntity(World aWorld, ChunkCoordinates aCoords, byte aSide, boolean aLoadUnloadedChunks) {return WD.te(aWorld, aCoords, aSide, aLoadUnloadedChunks);}
		@Deprecated public static DelegatorTileEntity<TileEntity> getTileEntity(World aWorld, int aX, int aY, int aZ, byte aSide, boolean aLoadUnloadedChunks) {return WD.te(aWorld, aX, aY, aZ, aSide, aLoadUnloadedChunks);}
		@Deprecated public static TileEntity getTileEntity(World aWorld, ChunkCoordinates aCoords, boolean aLoadUnloadedChunks) {return WD.te(aWorld, aCoords, aLoadUnloadedChunks);}
		@Deprecated public static TileEntity getTileEntity(World aWorld, int aX, int aY, int aZ, boolean aLoadUnloadedChunks) {return WD.te(aWorld, aX, aY, aZ, aLoadUnloadedChunks);}
		@Deprecated public static TileEntity setTileEntity(World aWorld, int aX, int aY, int aZ, TileEntity aTileEntity, boolean aCauseTileEntityUpdates) {return WD.te(aWorld, aX, aY, aZ, aTileEntity, aCauseTileEntityUpdates);}
		@Deprecated public static long getEnvironmentalTemperature(World aWorld, int aX, int aY, int aZ) {return WD.envTemp(aWorld, aX, aY, aZ);}
		@Deprecated public static long getTemperature(World aWorld, int aX, int aY, int aZ) {return WD.temperature(aWorld, aX, aY, aZ);}
		@Deprecated public static ItemStack getStack(World aWorld, int aX, int aY, int aZ) {return WD.stack(aWorld, aX, aY, aZ);}
		@Deprecated public static Block getBlock(World aWorld, int aX, int aY, int aZ, boolean aIgnoreUnloadedChunks) {return WD.block(aWorld, aX, aY, aZ, aIgnoreUnloadedChunks);}
		@Deprecated public static boolean setBlock(World aWorld, int aX, int aY, int aZ, Block aBlock, long aMeta, long aFlags) {return WD.set(aWorld, aX, aY, aZ, aBlock, aMeta, aFlags);}
		@Deprecated public static boolean crossedChunkBorder(int aFromX, int aFromZ, int aToX, int aToZ) {return WD.border(aFromX, aFromZ, aToX, aToZ);}
		@Deprecated public static boolean areCoordsEven(TileEntity aTileEntity) {return WD.even(aTileEntity);}
		@Deprecated public static boolean areCoordsEven(ChunkCoordinates aCoords) {return WD.even(aCoords);}
		@Deprecated public static boolean areCoordsEven(int... aCoords) {return WD.even(aCoords);}
		@Deprecated public static boolean setBlockIfDifferent(World aWorld, int aX, int aY, int aZ, Block aBlock, int aMetaData, int aFlags) {return WD.setIfDiff(aWorld, aX, aY, aZ, aBlock, aMetaData, aFlags);}
		@Deprecated public static boolean setBlock(World aWorld, int aX, int aY, int aZ, ItemStack aStack) {return WD.set(aWorld, aX, aY, aZ, aStack);}
		@Deprecated public static boolean isRegularStoneBlock(Block aBlock, short aMetaData) {return WD.stone(aBlock, aMetaData);}
		@Deprecated public static boolean isOreBlock(Block aBlock, short aMetaData) {return WD.ore(aBlock, aMetaData);}
		@Deprecated public static boolean isOreOrRegularStoneBlock(Block aBlock, short aMetaData) {return WD.ore_stone(aBlock, aMetaData);}
		@Deprecated public static boolean isVisuallyOccluded(World aWorld, int aX, int aY, int aZ, boolean aIgnoreUnloadedChunks, boolean aDefault) {return WD.visOcc(aWorld, aX, aY, aZ, aIgnoreUnloadedChunks, aDefault);}
		@Deprecated public static boolean isVisuallyOpaque(World aWorld, int aX, int aY, int aZ, boolean aIgnoreUnloadedChunks, boolean aDefault) {return WD.visOpq(aWorld, aX, aY, aZ, aIgnoreUnloadedChunks, aDefault);}
		@Deprecated public static boolean isVisuallyOpaque(Block aBlock) {return WD.visOpq(aBlock);}
		@Deprecated public static boolean isOccluded(World aWorld, int aX, int aY, int aZ, boolean aIgnoreUnloadedChunks, boolean aDefault) {return WD.occ(aWorld, aX, aY, aZ, aIgnoreUnloadedChunks, aDefault);}
		@Deprecated public static boolean isOpaque(World aWorld, int aX, int aY, int aZ, boolean aIgnoreUnloadedChunks, boolean aDefault) {return WD.opq(aWorld, aX, aY, aZ, aIgnoreUnloadedChunks, aDefault);}
		@Deprecated public static boolean isAir(World aWorld, int aX, int aY, int aZ) {return WD.air(aWorld, aX, aY, aZ);}
		@Deprecated public static boolean isEasilyReplaceable(World aWorld, int aX, int aY, int aZ) {return WD.easyRep(aWorld, aX, aY, aZ);}
		@Deprecated public static boolean hasCollisionBox(World aWorld, int aX, int aY, int aZ) {return aWorld.getBlock(aX, aY, aZ).getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ) != null;}
		@Deprecated public static void setOnFire(World aWorld, int aX, int aY, int aZ, boolean aReplaceCenter, boolean aCheckFlammability) {WD.burn(aWorld, aX, aY, aZ, aReplaceCenter, aCheckFlammability);}
		@Deprecated public static void setOnFire(World aWorld, ChunkCoordinates aCoords, boolean aReplaceCenter, boolean aCheckFlammability) {WD.burn(aWorld, aCoords, aReplaceCenter, aCheckFlammability);}
		@Deprecated public static boolean setToFire(World aWorld, int aX, int aY, int aZ, boolean aCheckFlammability) {return WD.fire(aWorld, aX, aY, aZ, aCheckFlammability);}
		@Deprecated public static boolean setToFire(World aWorld, ChunkCoordinates aCoords, boolean aCheckFlammability) {return WD.fire(aWorld, aCoords, aCheckFlammability);}
		@Deprecated public static boolean getCoordsOnFire(World aWorld, int aX, int aY, int aZ) {return WD.burning(aWorld, aX, aY, aZ);}
		@Deprecated public static long getCoordinateScan(ArrayList<String> aList, EntityPlayer aPlayer, World aWorld, int aScanLevel, int aX, int aY, int aZ, byte aSide, float aClickX, float aClickY, float aClickZ) {return WD.scan(aList, aPlayer, aWorld, aScanLevel, aX, aY, aZ, aSide, aClickX, aClickY, aClickZ);}
	}
	
	@Deprecated public static class Stacks {
		@Deprecated public static boolean debugItem(ItemStack aStack) {return ST.debug(aStack);}
		@Deprecated public static ItemStack update(ItemStack aStack) {return ST.update(aStack);}
		@Deprecated public static ItemStack update_(ItemStack aStack) {return ST.update_(aStack);}
		@Deprecated public static boolean inList(Collection<ItemStack> aList, ItemStack aStack, boolean aTrueIfListEmpty, boolean aInvertFilter) {return ST.listed(aList, aStack, aTrueIfListEmpty, aInvertFilter);}
		@Deprecated public static ItemStack set(Object aSetStack, Object aToStack) {return ST.set((ItemStack)aSetStack, (ItemStack)aToStack);}
		@Deprecated public static ItemStack set(Object aSetStack, Object aToStack, boolean aCheckStacksize, boolean aCheckNBT) {return ST.set((ItemStack)aSetStack, (ItemStack)aToStack, aCheckStacksize, aCheckNBT);}
		@Deprecated public static ItemStack container(ItemStack aStack, boolean aCheckIFluidContainerItems) {return ST.container(aStack, aCheckIFluidContainerItems);}
		@Deprecated public static ItemStack container(ItemStack aStack, boolean aCheckIFluidContainerItems, int aStacksize) {return ST.container(aStack, aCheckIFluidContainerItems, aStacksize);}
		@Deprecated public static boolean equal(ItemStack aStack1, ItemStack aStack2) {return ST.equal(aStack1, aStack2);}
		@Deprecated public static boolean equalTools(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {return ST.equalTools(aStack1, aStack2, aIgnoreNBT);}
		@Deprecated public static boolean equalTools_(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {return ST.equalTools_(aStack1, aStack2, aIgnoreNBT);}
		@Deprecated public static boolean equal(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {return ST.equal(aStack1, aStack2, aIgnoreNBT);}
		@Deprecated public static boolean equal_(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {return ST.equal_(aStack1, aStack2, aIgnoreNBT);}
		@Deprecated public static short id(Item aItem) {return ST.id(aItem);}
		@Deprecated public static short id(ItemStack aStack) {return ST.id(aStack);}
		@Deprecated public static Item item(ItemStack aStack) {return ST.item(aStack);}
		@Deprecated public static short meta(ItemStack aStack) {return ST.meta_(aStack);}
		@Deprecated public static ItemStack meta(ItemStack aStack, long aMeta) {return ST.meta_(aStack, aMeta);}
		@Deprecated public static boolean rotten(ItemStack aStack) {return ST.rotten(aStack);}
		@Deprecated public static int food(ItemStack aStack) {return ST.food(aStack);}
		@Deprecated public static float saturation(ItemStack aStack) {return ST.saturation(aStack);}
		@Deprecated public static ItemStack fuel(ItemStack aStack, short aValue) {return ST.fuel(aStack, aValue);}
		@Deprecated public static long fuel(ItemStack aStack) {return ST.fuel(aStack);}
		@Deprecated public static ItemStack makeIC2(String aItem, long aAmount, ItemStack aReplacement) {return ST.mkic(aItem, aAmount, aReplacement);}
		@Deprecated public static ItemStack makeIC2(String aItem, long aAmount, int aMeta, ItemStack aReplacement) {return ST.mkic(aItem, aAmount, aMeta, aReplacement);}
		@Deprecated public static ItemStack makeIC2(String aItem, long aAmount, int aMeta) {return ST.mkic(aItem, aAmount, aMeta);}
		@Deprecated public static ItemStack makeIC2(String aItem, long aAmount) {return ST.mkic(aItem, aAmount);}
		@Deprecated public static Item item(ModData aModID, String aItem) {return item(make(aModID, aItem, 1, null));}
		@Deprecated public static Item item(ModData aModID, String aItem, Item aReplacement) {Item rItem = item(aModID, aItem); return rItem == null ? aReplacement : rItem;}
		@Deprecated public static Item item(String aModID, String aItem) {return item(make(aModID, aItem, 1, null));}
		@Deprecated public static Item item(String aModID, String aItem, Item aReplacement) {Item rItem = item(aModID, aItem); return rItem == null ? aReplacement : rItem;}
		@Deprecated public static Block block(ModData aModID, String aBlock) {return block(make(aModID, aBlock, 1, null));}
		@Deprecated public static Block block(ModData aModID, String aBlock, Block aReplacement) {Block rBlock = block(aModID, aBlock); return rBlock == NB ? aReplacement : rBlock;}
		@Deprecated public static Block block(String aModID, String aBlock) {return block(make(aModID, aBlock, 1, null));}
		@Deprecated public static Block block(String aModID, String aBlock, Block aReplacement) {Block rBlock = block(aModID, aBlock); return rBlock == NB ? aReplacement : rBlock;}
		@Deprecated public static ItemStack make(ModData aModID, String aItem, long aAmount) {return make(aModID, aItem, aAmount, null);}
		@Deprecated public static ItemStack make(ModData aModID, String aItem, long aAmount, ItemStack aReplacement) {if (!aModID.mLoaded || Code.stringInvalid(aItem) || !GAPI_POST.mStartedPreInit) return null; if (aItem.length()>5&&aItem.charAt(0)=='t'&&aItem.charAt(1)=='i'&&aItem.charAt(2)=='l'&&aItem.charAt(3)=='e'&&aItem.charAt(4)=='.') return amount(aAmount, GameRegistry.findItemStack(aModID.mID, aItem, (int)aAmount), GameRegistry.findItemStack(aModID.mID, aItem.substring(5), (int)aAmount), aReplacement); return amount(aAmount, GameRegistry.findItemStack(aModID.mID, aItem, (int)aAmount), aReplacement);}
		@Deprecated public static ItemStack make(ModData aModID, String aItem, long aAmount, int aMeta) {ItemStack rStack = make(aModID, aItem, aAmount); if (rStack == null) return null; meta(rStack, aMeta); return rStack;}
		@Deprecated public static ItemStack make(ModData aModID, String aItem, long aAmount, int aMeta, ItemStack aReplacement) {ItemStack rStack = make(aModID, aItem, aAmount, aReplacement); if (rStack == null) return null; meta(rStack, aMeta); return rStack;}
		@Deprecated public static ItemStack make(String aModID, String aItem, long aAmount) {return make(aModID, aItem, aAmount, null);}
		@Deprecated public static ItemStack make(String aModID, String aItem, long aAmount, ItemStack aReplacement) {if (Code.stringInvalid(aItem) || !GAPI_POST.mStartedPreInit) return null; if (aItem.length()>5&&aItem.charAt(0)=='t'&&aItem.charAt(1)=='i'&&aItem.charAt(2)=='l'&&aItem.charAt(3)=='e'&&aItem.charAt(4)=='.') return amount(aAmount, GameRegistry.findItemStack(aModID, aItem, (int)aAmount), GameRegistry.findItemStack(aModID, aItem.substring(5), (int)aAmount), aReplacement); return amount(aAmount, GameRegistry.findItemStack(aModID, aItem, (int)aAmount), aReplacement);}
		@Deprecated public static ItemStack make(String aModID, String aItem, long aAmount, int aMeta) {ItemStack rStack = make(aModID, aItem, aAmount); if (rStack == null) return null; meta(rStack, aMeta); return rStack;}
		@Deprecated public static ItemStack make(String aModID, String aItem, long aAmount, int aMeta, ItemStack aReplacement) {ItemStack rStack = make(aModID, aItem, aAmount, aReplacement); if (rStack == null) return null; meta(rStack, aMeta); return rStack;}
		@Deprecated public static ItemStack make(long aItemID, long aStacksize, long aMetaData) {return aItemID==0?null:make(Item.getItemById((int)aItemID), aStacksize, aMetaData);}
		@Deprecated public static ItemStack make(Item aItem, long aStacksize, long aMetaData) {return aItem == null ? null : make(new ItemStack(aItem, Code.bindInt(aStacksize), (int)aMetaData), null);}
		@Deprecated public static ItemStack make(Block aBlock, long aStacksize, long aMetaData) {return aBlock == null || aBlock == NB ? null : make(new ItemStack(aBlock, Code.bindInt(aStacksize), (int)aMetaData), null);}
		@Deprecated public static ItemStack make(long aItemID, long aStacksize, long aMetaData, NBTTagCompound aNBT) {return aItemID==0?null:make(Item.getItemById((int)aItemID), aStacksize, aMetaData, aNBT);}
		@Deprecated public static ItemStack make(Item aItem, long aStacksize, long aMetaData, NBTTagCompound aNBT) {return aItem == null ? null : make(new ItemStack(aItem, Code.bindInt(aStacksize), (int)aMetaData), aNBT);}
		@Deprecated public static ItemStack make(Block aBlock, long aStacksize, long aMetaData, NBTTagCompound aNBT) {return aBlock == null || aBlock == NB ? null : make(new ItemStack(aBlock, Code.bindInt(aStacksize), (int)aMetaData), aNBT);}
		@Deprecated public static ItemStack make(ItemStack aStack, NBTTagCompound aNBT) {return make(aStack, null, aNBT);}
		@Deprecated public static ItemStack make(ItemStackContainer aStack, NBTTagCompound aNBT) {return make(aStack, null, aNBT);}
		@Deprecated public static ItemStack make(long aItemID, long aStacksize, long aMetaData, String aName) {return aItemID==0?null:make(Item.getItemById((int)aItemID), aStacksize, aMetaData, aName);}
		@Deprecated public static ItemStack make(Item aItem, long aStacksize, long aMetaData, String aName) {return aItem == null ? null : make(new ItemStack(aItem, Code.bindInt(aStacksize), (int)aMetaData), aName, null);}
		@Deprecated public static ItemStack make(Block aBlock, long aStacksize, long aMetaData, String aName) {return aBlock == null || aBlock == NB ? null : make(new ItemStack(aBlock, Code.bindInt(aStacksize), (int)aMetaData), aName, null);}
		@Deprecated public static ItemStack make(long aItemID, long aStacksize, long aMetaData, String aName, NBTTagCompound aNBT) {return aItemID==0?null:make(Item.getItemById((int)aItemID), aStacksize, aMetaData, aName, aNBT);}
		@Deprecated public static ItemStack make(Item aItem, long aStacksize, long aMetaData, String aName, NBTTagCompound aNBT) {return aItem == null ? null : make(new ItemStack(aItem, Code.bindInt(aStacksize), (int)aMetaData), aName, aNBT);}
		@Deprecated public static ItemStack make(Block aBlock, long aStacksize, long aMetaData, String aName, NBTTagCompound aNBT) {return aBlock == null || aBlock == NB ? null : make(new ItemStack(aBlock, Code.bindInt(aStacksize), (int)aMetaData), aName, aNBT);}
		@Deprecated public static ItemStack make(ItemStack aStack, String aName, NBTTagCompound aNBT) {if (aStack == null) return null; aStack = aStack.copy(); NBT.set(aStack, aNBT); if (aName != null) aStack.setStackDisplayName(aName); return aStack;}
		@Deprecated public static ItemStack make(ItemStackContainer aStack, String aName, NBTTagCompound aNBT) {if (aStack == null) return null; ItemStack rStack = aStack.toStack(); if (rStack == null) return null; NBT.set(rStack, aNBT); if (aName != null) rStack.setStackDisplayName(aName); return rStack;}
		@Deprecated public static ItemStack[] copyArray(Object... aStacks) {return ST.copyArray((ItemStack[])aStacks);}
		@Deprecated public static ItemStack copy(Object... aStacks) {return ST.copyFirst(aStacks);}
		@Deprecated public static ItemStack amount(long aAmount, Object... aStacks) {return ST.amount(aAmount, (ItemStack)aStacks[0]);}
		@Deprecated public static ItemStack copyAmount(long aAmount, Object... aStacks) {return ST.amount(aAmount, (ItemStack)aStacks[0]);}
		@Deprecated public static ItemStack copyMeta(long aMetaData, Object... aStacks) {return copyMeta(aMetaData, aStacks);}
		@Deprecated public static ItemStack copyAmountAndMeta(long aAmount, long aMetaData, Object... aStacks) {return ST.copyAmountAndMeta(aAmount, aMetaData, (ItemStack)aStacks[0]);}
		@Deprecated public static ItemStack mul(long aMultiplier, Object... aStacks) {return ST.mul(aMultiplier, (ItemStack)aStacks[0]);}
		@Deprecated public static ItemStack div(long aDivider, Object... aStacks) {return ST.div(aDivider, (ItemStack)aStacks[0]);}
		@Deprecated public static int toInt(ItemStack aStack) {return ST.toInt(aStack);}
		@Deprecated public static int toIntWildcard(ItemStack aStack) {return ST.toInt(aStack, W);}
		@Deprecated public static ItemStack toStack(int aStack) {return ST.toStack(aStack);}
		@Deprecated public static Integer[] toIntegerArray(ItemStack... aStacks) {return ST.toIntegerArray(aStacks);}
		@Deprecated public static int[] toIntArray(ItemStack... aStacks) {return ST.toIntArray(aStacks);}
		@Deprecated public static Block block(Object aStack) {return ST.block((ItemStack)aStack);}
		@Deprecated public static Block block_(Object aStack) {return ST.block_((ItemStack)aStack);}
		@Deprecated public static boolean valid(Object aStack) {return ST.valid((ItemStack)aStack);}
		@Deprecated public static boolean invalid(Object aStack) {return ST.invalid((ItemStack)aStack);}
		@Deprecated public static String configName(ItemStack aStack) {return ST.configName(aStack);}
		@Deprecated public static String configNames(ItemStack... aStacks) {return ST.configNames(aStacks);}
		@Deprecated public static String regName(ItemStack aStack) {return ST.regName(aStack);}
		@Deprecated public static String names(ItemStack... aStacks) {return ST.names(aStacks);}
		@Deprecated public static String namesAndSizes(ItemStack... aStacks) {return ST.namesAndSizes(aStacks);}
		@Deprecated public static void hide(Item aItem) {ST.hide(aItem);}
		@Deprecated public static void hide(Item aItem, long aMetaData) {ST.hide(aItem, aMetaData);}
		@Deprecated public static void hide(Block aBlock) {ST.hide(aBlock);}
		@Deprecated public static void hide(Block aBlock, long aMetaData) {ST.hide(aBlock, aMetaData);}
		@Deprecated public static void hide(ItemStack aStack) {ST.hide(aStack);}
		@Deprecated public static ItemStack load(NBTTagCompound aNBT, String aTagName) {return ST.load(aNBT, aTagName);}
		@Deprecated public static ItemStack load(NBTTagCompound aNBT) {return ST.load(aNBT);}
		@Deprecated public static NBTTagCompound save(NBTTagCompound aNBT, String aTagName, ItemStack aStack) {return ST.save(aNBT, aTagName, aStack);}
		@Deprecated public static NBTTagCompound save(ItemStack aStack) {return ST.save(aStack);}
	}
	
	@Deprecated public static class Crafting {
		@Deprecated public static class Bits {@Deprecated public static final long NONE = 0, MIR = B[0], BUF = B[1], REV = B[5], KEEPNBT = B[2], DISMANTLE = B[3], NO_REM = B[4], NO_AUTO = B[14], NO_COLLISION_CHECK = B[10], DEL_OTHER_RECIPES = B[6], DEL_OTHER_RECIPES_IF_SAME_NBT = B[7], DEL_OTHER_SHAPED_RECIPES = B[8], DEL_OTHER_NATIVE_RECIPES = B[9], DEL_IF_NO_DYES = B[13], ONLY_IF_HAS_OTHER_RECIPES = B[11], ONLY_IF_HAS_RESULT = B[12], DEFAULT = BUF|NO_REM, DEFAULT_MIR = DEFAULT|MIR, DEFAULT_REV = DEFAULT|REV, DEFAULT_NCC = DEFAULT|NO_COLLISION_CHECK, DEFAULT_REV_NCC = DEFAULT_REV|NO_COLLISION_CHECK, DEFAULT_NAC = DEFAULT|NO_AUTO, DEFAULT_NAC_NCC = DEFAULT_NCC|NO_AUTO, DEFAULT_NAC_REV = DEFAULT_REV|NO_AUTO, DEFAULT_NAC_REV_NCC = DEFAULT_REV_NCC|NO_AUTO, DEFAULT_REM = DEFAULT|DEL_OTHER_RECIPES, DEFAULT_REM_REV = DEFAULT_REM|REV, DEFAULT_REM_NCC = DEFAULT_REM|NO_COLLISION_CHECK, DEFAULT_REM_REV_NCC = DEFAULT_REM_REV|NO_COLLISION_CHECK, DEFAULT_REM_NAC = DEFAULT_REM|NO_AUTO, DEFAULT_REM_NAC_NCC = DEFAULT_REM_NCC|NO_AUTO, DEFAULT_REM_NAC_REV = DEFAULT_REM_REV|NO_AUTO, DEFAULT_REM_NAC_REV_NCC = DEFAULT_REM_REV_NCC|NO_AUTO;}
		@Deprecated public static boolean shaped(ItemStack aResult, Enchantment[] aEnchantmentsAdded, int[] aEnchantmentLevelsAdded, Object[] aRecipe) {return CR.shaped(aResult, aEnchantmentsAdded, aEnchantmentLevelsAdded, aRecipe);}
		@Deprecated public static boolean shaped(ItemStack aResult, Object[] aRecipe) {return CR.shaped(aResult, aRecipe);}
		@Deprecated public static boolean shaped(ItemStack aResult, long aBitMask, Object[] aRecipe) {return CR.shaped(aResult, aBitMask, aRecipe);}
		@Deprecated public static boolean shapeless(ItemStack aResult, Enchantment[] aEnchantmentsAdded, int[] aEnchantmentLevelsAdded, Object[] aRecipe) {return CR.shapeless(aResult, aEnchantmentsAdded, aEnchantmentLevelsAdded, aRecipe);}
		@Deprecated public static boolean shapeless(ItemStack aResult, Object[] aRecipe) {return CR.shapeless(aResult, aRecipe);}
		@Deprecated public static boolean shapeless(ItemStack aResult, long aBitMask, Object[] aRecipe) {return CR.shapeless(aResult, aBitMask, aRecipe);}
		@Deprecated public static ItemStack getany(World aWorld, ItemStack... aRecipe) {return CR.getany(aWorld, aRecipe);}
		@Deprecated public static ItemStack get(ItemStack... aRecipe) {return CR.get(aRecipe);}
		@Deprecated public static ItemStack get(boolean aUncopiedStack, ItemStack... aRecipe) {return CR.get(aUncopiedStack, aRecipe);}
		@Deprecated public static boolean has(ItemStack aOutput) {return CR.has(aOutput);}
		@Deprecated public static boolean remout(ItemStack aOutput, boolean aIgnoreNBT, boolean aNotRemoveShapelessRecipes, boolean aOnlyRemoveNativeHandlers, boolean aDontRemoveDyeingRecipes) {return CR.remout(aOutput, aIgnoreNBT, aNotRemoveShapelessRecipes, aOnlyRemoveNativeHandlers, aDontRemoveDyeingRecipes);}
		@Deprecated public static boolean remout(ItemStack aOutput) {return CR.remout(aOutput);}
		@Deprecated public static boolean remout(ModData aMod, String... aNames) {return CR.remout(aMod, aNames);}
		@Deprecated public static boolean remout(ModData aMod, String aName, int aMetaData) {return CR.remout(aMod, aName, aMetaData);}
		@Deprecated public static ItemStack remove(ItemStack... aRecipe) {return CR.remove(aRecipe);}
	}
	
	public static synchronized boolean removeSimpleIC2MachineRecipe(ItemStack aInput, @SuppressWarnings("rawtypes") Map aRecipeList, ItemStack aOutput) {
		if (!MD.IC2.mLoaded || (ST.invalid(aInput) && ST.invalid(aOutput)) || aRecipeList == null || aRecipeList.isEmpty()) return F;
		boolean rReturn = F;
		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<IRecipeInput, RecipeOutput>> tIterator = aRecipeList.entrySet().iterator();
		aOutput = OM.get_(aOutput);
		while (tIterator.hasNext()) {
			Map.Entry<IRecipeInput, RecipeOutput> tEntry = tIterator.next();
			if (aInput == null || tEntry.getKey().matches(aInput)) {
				List<ItemStack> tList = tEntry.getValue().items;
				if (tList != null) for (ItemStack tOutput : tList) if (ST.invalid(aOutput) || ST.equal(OM.get(tOutput), aOutput)) {
					tIterator.remove();
					rReturn = T;
					break;
				}
			}
		}
		return rReturn;
	}
	
	public static boolean addSimpleIC2MachineRecipe(IMachineRecipeManager aRecipeManager, ItemStack aInput, NBTTagCompound aNBT, Object... aOutput) {
		if (!MD.IC2.mLoaded || ST.invalid(aInput) || aOutput == null || aRecipeManager == null) return F;
		try {
			aOutput = Code.getWithoutNulls(aOutput).toArray(ZL);
			if (aOutput.length == 0) return F;
			OreDictItemData tOreName = OM.association_(aInput);
			if (aRecipeManager instanceof IMachineRecipeManagerExt) {
				if (tOreName != null && !tOreName.mBlackListed && !OreDictManager.INSTANCE.isBlacklisted(aInput)) {
					((IMachineRecipeManagerExt)aRecipeManager).addRecipe((IRecipeInput)COMPAT_IC2.makeInput(tOreName.toString(), aInput.stackSize), aNBT, T, OreDictManager.INSTANCE.getStackArray(T, aOutput));
				} else {
					((IMachineRecipeManagerExt)aRecipeManager).addRecipe((IRecipeInput)COMPAT_IC2.makeInput(aInput), aNBT, T, OreDictManager.INSTANCE.getStackArray(T, aOutput));
				}
			} else {
				if (tOreName != null && !tOreName.mBlackListed && !OreDictManager.INSTANCE.isBlacklisted(aInput)) {
					aRecipeManager.addRecipe((IRecipeInput)COMPAT_IC2.makeInput(tOreName.toString(), aInput.stackSize), aNBT, OreDictManager.INSTANCE.getStackArray(T, aOutput));
				} else {
					aRecipeManager.addRecipe((IRecipeInput)COMPAT_IC2.makeInput(aInput), aNBT, OreDictManager.INSTANCE.getStackArray(T, aOutput));
				}
			}
		} catch(Throwable e) {/**/}
		return T;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean addSimpleIC2MachineRecipe(ItemStack aInput, @SuppressWarnings("rawtypes") Map aRecipeList, NBTTagCompound aNBT, Object... aOutput) {
		if (!MD.IC2.mLoaded || ST.invalid(aInput) || aOutput.length == 0 || aRecipeList == null) return F;
		OreDictItemData tOreName = OM.association_(aInput);
		if (tOreName != null) {
			aRecipeList.put(COMPAT_IC2.makeInput(tOreName.toString(), aInput.stackSize), COMPAT_IC2.makeOutput(aNBT, OreDictManager.INSTANCE.getStackArray(T, aOutput)));
		} else {
			aRecipeList.put(COMPAT_IC2.makeInput(aInput), COMPAT_IC2.makeOutput(aNBT, OreDictManager.INSTANCE.getStackArray(T, aOutput)));
		}
		return T;
	}
	
	/**
	 * Yes, I have read all those warning that it might break. But I don't expect any further development on this Function by Forge during 1.7.10.
	 * 
	 * That said, I've put a try/catch around this Stuff in case of random Errors.
	 */
	public static class LoadingBar {
		public static boolean mEnabled = T;
		public static Object mBar = null;
		public static int mSize = 0, mCount = 0;
		public static Field mMessage = null, mStep = null;
		
		@SuppressWarnings("deprecation")
		public static boolean start(String aTitle, int aSize) {
			if (mBar == null && mEnabled && aSize > 0) {
				try {
					mBar = cpw.mods.fml.common.ProgressManager.push(aTitle, aSize, F);
					mMessage = UT.Reflection.getField(mBar, "message", T, T);
					mStep = UT.Reflection.getField(mBar, "step", T, T);
					mSize = aSize;
					mCount = 0;
					return T;
				} catch(NoClassDefFoundError e) {
					mEnabled = F;
				} catch(Throwable e) {e.printStackTrace(ERR);}
			}
			return F;
		}
		
		public static boolean step(Object aStepName) {
			if (mBar != null && mEnabled) {
				if (mCount++ < mSize) {
					try {
						mMessage.set(mBar, aStepName == null ? "Error: NULL" : aStepName.toString());
						mStep.setInt(mBar, mCount);
						return T;
					} catch(Throwable e) {e.printStackTrace(ERR);}
					return F;
				}
				ERR.println("ERROR: Progress Bar needed a forced Finish, because of too many Steps.");
				finish();
				return F;
			}
			return F;
		}
		
		@SuppressWarnings("deprecation")
		public static boolean finish() {
			if (mBar != null && mEnabled) {
				if (mCount != mSize) ERR.println("ERROR: Progress Bar needed a forced Finish, because of too few Steps.");
				try {
					cpw.mods.fml.common.ProgressManager.pop((cpw.mods.fml.common.ProgressManager.ProgressBar)mBar);
					mBar = null;
					mSize = 0;
					mCount = 0;
					return T;
				} catch(NoClassDefFoundError e) {
					mEnabled = F;
				} catch(Throwable e) {e.printStackTrace(ERR);}
				mBar = null;
				mSize = 0;
				mCount = 0;
				return F;
			}
			return F;
		}
	}
}
