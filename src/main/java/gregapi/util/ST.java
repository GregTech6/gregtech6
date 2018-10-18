/**
 * Copyright (c) 2018 Gregorius Techneticies
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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cpw.mods.fml.common.registry.GameRegistry;
import gregapi.code.IItemContainer;
import gregapi.code.ItemStackContainer;
import gregapi.code.ModData;
import gregapi.data.CS.ItemsGT;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.TD;
import gregapi.item.IItemGTContainerTool;
import gregapi.item.IItemUpdatable;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.item.multiitem.food.IFoodStat;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import ic2.api.item.IC2Items;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidContainerItem;

/**
 * @author Gregorius Techneticies
 */
public class ST {
	public static boolean equal (ItemStack aStack1, ItemStack aStack2) {return equal(aStack1, aStack2, F);}
	public static boolean equal (ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {return aStack1 != null && aStack2 != null && equal_(aStack1, aStack2, aIgnoreNBT);}
	public static boolean equal_(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {return item_(aStack1) == item_(aStack2) && equal(meta_(aStack1), meta_(aStack2)) && (aIgnoreNBT || (((nbt_(aStack1) == null) == (nbt_(aStack2) == null)) && (nbt_(aStack1) == null || nbt_(aStack1).equals(nbt_(aStack2)))));}
	
	public static boolean equal (ItemStack aStack, Item  aItem ) {return aStack != null && aItem  != null && equal_(aStack, aItem );}
	public static boolean equal (ItemStack aStack, Block aBlock) {return aStack != null && aBlock != null && equal_(aStack, aBlock);}
	public static boolean equal_(ItemStack aStack, Item  aItem ) {return item_ (aStack) == aItem ;}
	public static boolean equal_(ItemStack aStack, Block aBlock) {return block_(aStack) == aBlock;}
	public static boolean equal (ItemStack aStack, Item  aItem , long aMeta) {return aStack != null && aItem  != null && equal_(aStack, aItem , aMeta);}
	public static boolean equal (ItemStack aStack, Block aBlock, long aMeta) {return aStack != null && aBlock != null && equal_(aStack, aBlock, aMeta);}
	public static boolean equal_(ItemStack aStack, Item  aItem , long aMeta) {return equal(meta_(aStack), aMeta) && item_ (aStack) == aItem ;}
	public static boolean equal_(ItemStack aStack, Block aBlock, long aMeta) {return equal(meta_(aStack), aMeta) && block_(aStack) == aBlock;}
	public static boolean equal (ItemStack aStack, ModData aModID, String aItem            ) {return equal(aStack, GameRegistry.findItem(aModID.mID, aItem));}
	public static boolean equal (ItemStack aStack, ModData aModID, String aItem, long aMeta) {return equal(aStack, GameRegistry.findItem(aModID.mID, aItem), aMeta);}
	
	public static boolean equal (long aMeta1, long aMeta2) {return aMeta1 == aMeta2 || aMeta1 == W || aMeta2 == W;}
	
	public static boolean equalTools (ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {return aStack1 != null && aStack2 != null && equalTools_(aStack1, aStack2, aIgnoreNBT);}
	public static boolean equalTools_(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {return item_(aStack1) == item_(aStack2) && equal(meta_(aStack1), meta_(aStack2)) && (aIgnoreNBT || item_(aStack1) instanceof IItemGTContainerTool || (((nbt_(aStack1) == null) == (nbt_(aStack2) == null)) && (nbt_(aStack1) == null || nbt_(aStack1).equals(nbt_(aStack2)))));}
	
	public static boolean   valid(ItemStack aStack) {return aStack != null && aStack.stackSize >= 0 && item_(aStack) != null;}
	public static boolean invalid(ItemStack aStack) {return aStack == null || aStack.stackSize <  0 || item_(aStack) == null;}
	
	public static ItemStack validate(ItemStack aStack) {return valid(aStack) ? aStack : null;}
	
	public static short id (Item      aItem ) {return aItem  == null ? 0 : id_(aItem);}
	public static short id_(Item      aItem ) {return (short)Item.getIdFromItem(aItem);}
	public static short id (Block     aBlock) {return aBlock == null ? 0 : id_(aBlock);}
	public static short id_(Block     aBlock) {return aBlock == NB   ? 0 : (short)Block.getIdFromBlock(aBlock);}
	public static short id (ItemStack aStack) {return aStack == null ? 0 : id(item_(aStack));}
	
	public static Item item (ModData aModID, String aItem) {return item(make(aModID, aItem, 1, 0));}
	public static Item item (ModData aModID, String aItem, Item aReplacement) {Item rItem = item(aModID, aItem); return rItem == null ? aReplacement : rItem;}
	public static Item item (Block aBlock) {return aBlock == null ? null : item_(aBlock);}
	public static Item item_(Block aBlock) {return aBlock == NB   ? null : Item.getItemFromBlock(aBlock);}
	public static Item item (ItemStack aStack) {return aStack == null ? null : item_(aStack);}
	public static Item item_(ItemStack aStack) {return aStack.getItem();}
	public static Item item (long aID) {return aID > 0 && aID < 65536 ? item_(aID) : null;}
	public static Item item_(long aID) {return Item.getItemById((int)aID);}
	
	public static Block block (ModData aModID, String aBlock) {return block(make(aModID, aBlock, 1, 0));}
	public static Block block (ModData aModID, String aBlock, Block aReplacement) {Block rBlock = block(aModID, aBlock); return rBlock == NB ? aReplacement : rBlock;}
	public static Block block (Item aItem) {return aItem != null ? block_(aItem) : NB;}
	public static Block block_(Item aItem) {return Block.getBlockFromItem(aItem);}
	public static Block block (ItemStack aStack) {return aStack != null ? block(item_(aStack)) : NB;}
	public static Block block_(ItemStack aStack) {return block_(item_(aStack));}
	public static Block block (long aID) {return aID > 0 && aID < 65536 ? block_(aID) : NB;}
	public static Block block_(long aID) {return Block.getBlockById((int)aID);}
	
	public static short     meta (ItemStack aStack) {return aStack == null ? 0 : meta_(aStack);}
	public static short     meta_(ItemStack aStack) {return (short)Items.feather.getDamage(aStack);}
	public static ItemStack meta (ItemStack aStack, long aMeta) {return aStack == null ? null : meta_(aStack, aMeta);}
	public static ItemStack meta_(ItemStack aStack, long aMeta) {Items.feather.setDamage(aStack, (short)aMeta); return aStack;}
	
	public static byte size (ItemStack aStack) {return aStack == null || item_(aStack) == null || aStack.stackSize < 0 ? 0 : UT.Code.bindByte(aStack.stackSize);}
	public static ItemStack size (long aSize, ItemStack aStack) {return aStack == null || item_(aStack) == null ? null : size_(aSize, aStack);}
	public static ItemStack size_(long aSize, ItemStack aStack) {aStack.stackSize = (int)aSize; return aStack;}
	
	public static ItemStack copy (ItemStack aStack) {return aStack == null || item_(aStack) == null ? null : copy_(aStack);}
	public static ItemStack copy_(ItemStack aStack) {return aStack.copy();}
	
	public static ItemStack name (ItemStack aStack, String aName) {return aStack == null || aName == null ? aStack : name_(aStack, aName);}
	public static ItemStack name_(ItemStack aStack, String aName) {aStack.setStackDisplayName(aName); return aStack;}
	
	public static NBTTagCompound nbt (ItemStack aStack) {return aStack == null ? null : nbt_(aStack);}
	public static NBTTagCompound nbt_(ItemStack aStack) {return aStack.getTagCompound();}
	public static ItemStack      nbt (ItemStack aStack, NBTTagCompound aNBT) {return aStack == null ? null : nbt_(aStack, aNBT);}
	public static ItemStack      nbt_(ItemStack aStack, NBTTagCompound aNBT) {return UT.NBT.set(aStack, aNBT);}
	
	public static ItemStack amount (long aSize, ItemStack aStack) {return aStack == null || item_(aStack) == null ? null : amount_(aSize, aStack);}
	public static ItemStack amount_(long aSize, ItemStack aStack) {return size_(aSize, copy_(aStack));}
	
	public static ItemStack mul (long aMultiplier, ItemStack aStack) {return aStack == null || item_(aStack) == null ? null : mul_(aMultiplier, aStack);}
	public static ItemStack mul_(long aMultiplier, ItemStack aStack) {return amount_(aStack.stackSize * aMultiplier, aStack);}
	
	public static ItemStack div (long aDivider, ItemStack aStack) {return aStack == null || item_(aStack) == null ? null : div_(aDivider, aStack);}
	public static ItemStack div_(long aDivider, ItemStack aStack) {return amount_(aStack.stackSize / aDivider, aStack);}
	
	public static ItemStack validMeta (long aSize, ItemStack aStack) {return aStack == null || item_(aStack) == null ? null : validMeta_(aSize, aStack);}
	public static ItemStack validMeta_(long aSize, ItemStack aStack) {return size_(aSize, validMeta_(aStack));}
	public static ItemStack validMeta (ItemStack aStack) {return aStack == null || item_(aStack) == null ? null : validMeta_(aStack);}
	public static ItemStack validMeta_(ItemStack aStack) {return meta_(aStack) == W ? meta_(copy_(aStack), 0) : copy_(aStack);}
	
	public static int toInt(Item aItem, long aMeta) {return aItem == null ? 0 : id_(aItem) | (((short)aMeta)<<16);}
	public static int toInt(ItemStack aStack) {return aStack != null ? toInt(item_(aStack), meta_(aStack)) : 0;}
	public static int toInt(ItemStack aStack, long aMeta) {return aStack != null ? toInt(item_(aStack), aMeta) : 0;}
	
	public static ItemStack toStack(int aStack) {return make(toItem(aStack), 1, toMeta(aStack));}
	public static Block     toBlock(int aStack) {return block(aStack&(~0>>>16));}
	public static Item      toItem (int aStack) {return item(aStack&(~0>>>16));}
	public static short     toMeta (int aStack) {return (short)(aStack>>>16);}
	
	public static ItemStack set(ItemStack aSetStack, ItemStack aToStack) {
		return set(aSetStack, aToStack, T, T);
	}
	public static ItemStack set(ItemStack aSetStack, ItemStack aToStack, boolean aCheckStacksize, boolean aCheckNBT) {
		if (invalid(aSetStack) || invalid(aToStack)) return null;
		aSetStack.func_150996_a(item_(aToStack));
		if (aCheckStacksize) aSetStack.stackSize = aToStack.stackSize;
		meta_(aSetStack, meta_(aToStack));
		if (aCheckNBT) aSetStack.setTagCompound(aToStack.getTagCompound());
		return aSetStack;
	}
	
	public static ItemStack update (ItemStack aStack) {
		return invalid(aStack)?aStack:update_(aStack);
	}
	public static ItemStack update_(ItemStack aStack) {
		if (aStack.hasTagCompound() && aStack.getTagCompound().hasNoTags()) aStack.setTagCompound(null);
		if (item_(aStack) instanceof IItemUpdatable) ((IItemUpdatable)item_(aStack)).updateItemStack(aStack);
		return aStack;
	}
	public static ItemStack update (ItemStack aStack, World aWorld, int aX, int aY, int aZ) {
		return invalid(aStack)?aStack:update_(aStack, aWorld, aX, aY, aZ);
	}
	public static ItemStack update_(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {
		if (aStack.hasTagCompound() && aStack.getTagCompound().hasNoTags()) aStack.setTagCompound(null);
		if (item_(aStack) instanceof IItemUpdatable) ((IItemUpdatable)item_(aStack)).updateItemStack(aStack);
		return aStack;
	}
	public static ItemStack update (ItemStack aStack, Entity aEntity) {
		return update(aStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
	}
	public static ItemStack update_(ItemStack aStack, Entity aEntity) {
		return update_(aStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
	}
	
	public static ItemStack[] copyArray(ItemStack... aStacks) {
		ItemStack[] rStacks = new ItemStack[aStacks.length];
		for (int i = 0; i < aStacks.length; i++) rStacks[i] = copy(aStacks[i]);
		return rStacks;
	}
	
	public static ItemStack copyFirst(Object... aStacks) {
		return copy(get(aStacks));
	}
	
	public static ItemStack copyMeta(long aMeta, ItemStack aStack) {
		return aStack == null || item_(aStack) == null ? null : meta_(copy_(aStack), aMeta);
	}
	public static ItemStack copyAmountAndMeta(long aSize, long aMeta, ItemStack aStack) {
		return aStack == null || item_(aStack) == null ? null : meta_(amount_(aSize, aStack), aMeta);
	}
	public static ItemStack get(Object... aStacks) {
		for (Object aStack : aStacks) {
			if (aStack instanceof ItemStack         ) {if (valid((ItemStack)aStack)) return (ItemStack)aStack; continue;}
			if (aStack instanceof Item              ) return make_((Item)aStack, 1, 0);
			if (aStack instanceof Block             ) return make((Block)aStack, 1, 0);
			if (aStack instanceof IItemContainer    ) {ItemStack rStack = ((IItemContainer    )aStack).get(   1); if (valid(rStack)) return rStack; continue;}
			if (aStack instanceof ItemStackContainer) {ItemStack rStack = ((ItemStackContainer)aStack).toStack(); if (valid(rStack)) return rStack; continue;}
		}
		return null;
	}
	
	
	
	public static ItemStack make_(Item  aItem , long aSize, long aMeta) {return new ItemStack(aItem , UT.Code.bindInt(aSize), UT.Code.bindShort(aMeta));}
	public static ItemStack make_(Block aBlock, long aSize, long aMeta) {return new ItemStack(aBlock, UT.Code.bindInt(aSize), UT.Code.bindShort(aMeta));}
	public static ItemStack make(ModData aModID, String aItem, long aSize) {
		if (!aModID.mLoaded || UT.Code.stringInvalid(aItem) || !GAPI_POST.mStartedPreInit) return null;
		ItemStack
		rStack = GameRegistry.findItemStack(aModID.mID, aItem, (int)aSize);
		if (valid(rStack)) return rStack;
		if (aItem.length() < 5 || aItem.charAt(4) != '.' || !aItem.startsWith("tile")) return null;
		return validate(GameRegistry.findItemStack(aModID.mID, aItem.substring(5), (int)aSize));
	}
	public static ItemStack mkic(String aItem, long aSize) {
		if (UT.Code.stringInvalid(aItem) || !GAPI_POST.mStartedPreInit) return null;
		if (!sIC2ItemMap.containsKey(aItem)) try {
			ItemStack tStack = validate(IC2Items.getItem(aItem));
			sIC2ItemMap.put(aItem, tStack);
			if (tStack == null && MD.IC2.mLoaded && !aItem.startsWith("rubber")) ERR.println(aItem + " is not found in the IC2 Items!");
		} catch (Throwable e) {
			sIC2ItemMap.put(aItem, null);
		}
		return amount(aSize, sIC2ItemMap.get(aItem));
	}
	
	private static final Map<String, ItemStack> sIC2ItemMap = new HashMap<>();
	
	public static ItemStack mkic(String aItem                , long aSize, long aMeta                                   ) {return     meta(mkic(aItem, aSize), aMeta);}
	public static ItemStack mkic(String aItem                , long aSize            , ItemStack aReplacement           ) {return get(     mkic(aItem, aSize)        , aReplacement);}
	public static ItemStack mkic(String aItem                , long aSize, long aMeta, Object    aReplacement           ) {return get(meta(mkic(aItem, aSize), aMeta), aReplacement);}
	public static ItemStack make(ModData aModID, String aItem, long aSize, long aMeta                                   ) {return     meta(make(aModID, aItem, aSize), aMeta);}
	public static ItemStack make(ModData aModID, String aItem, long aSize, long aMeta, Object    aReplacement           ) {return get(meta(make(aModID, aItem, aSize), aMeta), aReplacement);}
	public static ItemStack make(long   aItemID              , long aSize, long aMeta                                   ) {return make(item(aItemID), aSize, aMeta);}
	public static ItemStack make(long   aItemID              , long aSize, long aMeta              , NBTTagCompound aNBT) {return make(item(aItemID), aSize, aMeta, aNBT);}
	public static ItemStack make(long   aItemID              , long aSize, long aMeta, String aName                     ) {return make(item(aItemID), aSize, aMeta, aName);}
	public static ItemStack make(long   aItemID              , long aSize, long aMeta, String aName, NBTTagCompound aNBT) {return make(item(aItemID), aSize, aMeta, aName, aNBT);}
	public static ItemStack make(Item   aItem                , long aSize, long aMeta                                   ) {return aItem   == null                 ? null :          make_(aItem            , aSize, aMeta);}
	public static ItemStack make(Block  aBlock               , long aSize, long aMeta                                   ) {return aBlock  == null || aBlock == NB ? null :          make_(aBlock           , aSize, aMeta);}
//  public static ItemStack make(IBlock aBlock               , long aSize, long aMeta                                   ) {return aBlock  == null                 ? null :          make_(aBlock.getBlock(), aSize, aMeta);}
	public static ItemStack make(Item   aItem                , long aSize, long aMeta              , NBTTagCompound aNBT) {return aItem   == null                 ? null :      nbt(make_(aItem            , aSize, aMeta), aNBT);}
	public static ItemStack make(Block  aBlock               , long aSize, long aMeta              , NBTTagCompound aNBT) {return aBlock  == null || aBlock == NB ? null :      nbt(make_(aBlock           , aSize, aMeta), aNBT);}
//  public static ItemStack make(IBlock aBlock               , long aSize, long aMeta              , NBTTagCompound aNBT) {return aBlock  == null                 ? null :      nbt(make_(aBlock.getBlock(), aSize, aMeta), aNBT);}
	public static ItemStack make(Item   aItem                , long aSize, long aMeta, String aName                     ) {return aItem   == null                 ? null : name(    make_(aItem            , aSize, aMeta)       , aName);}
	public static ItemStack make(Block  aBlock               , long aSize, long aMeta, String aName                     ) {return aBlock  == null || aBlock == NB ? null : name(    make_(aBlock           , aSize, aMeta)       , aName);}
//  public static ItemStack make(IBlock aBlock               , long aSize, long aMeta, String aName                     ) {return aBlock  == null                 ? null : name(    make_(aBlock.getBlock(), aSize, aMeta)       , aName);}
	public static ItemStack make(Item   aItem                , long aSize, long aMeta, String aName, NBTTagCompound aNBT) {return aItem   == null                 ? null : name(nbt(make_(aItem            , aSize, aMeta), aNBT), aName);}
	public static ItemStack make(Block  aBlock               , long aSize, long aMeta, String aName, NBTTagCompound aNBT) {return aBlock  == null || aBlock == NB ? null : name(nbt(make_(aBlock           , aSize, aMeta), aNBT), aName);}
//  public static ItemStack make(IBlock aBlock               , long aSize, long aMeta, String aName, NBTTagCompound aNBT) {return aBlock  == null                 ? null : name(nbt(make_(aBlock.getBlock(), aSize, aMeta), aNBT), aName);}
	public static ItemStack make(ItemStack          aStack                                         , NBTTagCompound aNBT) {return aStack == null ? null :      nbt_(aStack.copy(), aNBT);}
	public static ItemStack make(ItemStack          aStack                           , String aName                     ) {return aStack == null ? null : name(     aStack.copy()       , aName);}
	public static ItemStack make(ItemStack          aStack                           , String aName, NBTTagCompound aNBT) {return aStack == null ? null : name(nbt_(aStack.copy(), aNBT), aName);}
	public static ItemStack make(ItemStackContainer aStack                                         , NBTTagCompound aNBT) {return      nbt(aStack.toStack(), aNBT);}
	public static ItemStack make(ItemStackContainer aStack                           , String aName                     ) {return name(    aStack.toStack()       , aName);}
	public static ItemStack make(ItemStackContainer aStack                           , String aName, NBTTagCompound aNBT) {return name(nbt(aStack.toStack(), aNBT), aName);}
	
	
	
	public static EntityItem place  (World aWorld, double aX, double aY, double aZ, ModData aModID, String aItem, long aSize, long aMeta) {ItemStack rStack = make(aModID, aItem, aSize, aMeta); if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aX, aY, aZ, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place  (World aWorld, double aX, double aY, double aZ, Item aItem, long aSize, long aMeta                  ) {ItemStack rStack = make(aItem, aSize, aMeta)        ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aX, aY, aZ, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place  (World aWorld, double aX, double aY, double aZ, Block aBlock, long aSize, long aMeta                ) {ItemStack rStack = make(aBlock, aSize, aMeta)       ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aX, aY, aZ, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place  (World aWorld, double aX, double aY, double aZ, ItemStackContainer aStack                           ) {ItemStack rStack = aStack.toStack()                 ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aX, aY, aZ, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place  (World aWorld, double aX, double aY, double aZ, ItemStack aStack                                    ) {ItemStack rStack = aStack                           ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aX, aY, aZ, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (World aWorld, double aX, double aY, double aZ, ModData aModID, String aItem, long aSize, long aMeta) {ItemStack rStack = make(aModID, aItem, aSize, aMeta); if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aX, aY, aZ, rStack); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (World aWorld, double aX, double aY, double aZ, Item aItem, long aSize, long aMeta                  ) {ItemStack rStack = make(aItem, aSize, aMeta)        ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aX, aY, aZ, rStack); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (World aWorld, double aX, double aY, double aZ, Block aBlock, long aSize, long aMeta                ) {ItemStack rStack = make(aBlock, aSize, aMeta)       ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aX, aY, aZ, rStack); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (World aWorld, double aX, double aY, double aZ, ItemStackContainer aStack                           ) {ItemStack rStack = aStack.toStack()                 ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aX, aY, aZ, rStack); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (World aWorld, double aX, double aY, double aZ, ItemStack aStack                                    ) {ItemStack rStack = aStack                           ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aX, aY, aZ, rStack); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem entity (World aWorld, double aX, double aY, double aZ, ModData aModID, String aItem, long aSize, long aMeta) {ItemStack rStack = make(aModID, aItem, aSize, aMeta); if (invalid(rStack)) return null; return               entity_(aWorld, aX, aY, aZ, rStack);}
	public static EntityItem entity (World aWorld, double aX, double aY, double aZ, Item aItem, long aSize, long aMeta                  ) {ItemStack rStack = make(aItem, aSize, aMeta)        ; if (invalid(rStack)) return null; return               entity_(aWorld, aX, aY, aZ, rStack);}
	public static EntityItem entity (World aWorld, double aX, double aY, double aZ, Block aBlock, long aSize, long aMeta                ) {ItemStack rStack = make(aBlock, aSize, aMeta)       ; if (invalid(rStack)) return null; return               entity_(aWorld, aX, aY, aZ, rStack);}
	public static EntityItem entity (World aWorld, double aX, double aY, double aZ, ItemStackContainer aStack                           ) {ItemStack rStack = aStack.toStack()                 ; if (invalid(rStack)) return null; return               entity_(aWorld, aX, aY, aZ, rStack);}
	public static EntityItem entity (World aWorld, double aX, double aY, double aZ, ItemStack aStack                                    ) {ItemStack rStack = aStack                           ; if (invalid(rStack)) return null; return               entity_(aWorld, aX, aY, aZ, rStack);}
	public static EntityItem entity_(World aWorld, double aX, double aY, double aZ, ItemStack aStack                                    ) {return new EntityItem(aWorld, aX, aY, aZ, update_(aStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ)));}
	
	public static EntityItem place  (Entity aEntity, ModData aModID, String aItem, long aSize, long aMeta) {ItemStack rStack = make(aModID, aItem, aSize, aMeta); if (invalid(rStack)) return null; EntityItem rEntity = entity_(aEntity, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place  (Entity aEntity, Item aItem, long aSize, long aMeta                  ) {ItemStack rStack = make(aItem, aSize, aMeta)        ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aEntity, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place  (Entity aEntity, Block aBlock, long aSize, long aMeta                ) {ItemStack rStack = make(aBlock, aSize, aMeta)       ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aEntity, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place  (Entity aEntity, ItemStackContainer aStack                           ) {ItemStack rStack = aStack.toStack()                 ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aEntity, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place  (Entity aEntity, ItemStack aStack                                    ) {ItemStack rStack = aStack                           ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aEntity, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (Entity aEntity, ModData aModID, String aItem, long aSize, long aMeta) {ItemStack rStack = make(aModID, aItem, aSize, aMeta); if (invalid(rStack)) return null; EntityItem rEntity = entity_(aEntity, rStack); return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (Entity aEntity, Item aItem, long aSize, long aMeta                  ) {ItemStack rStack = make(aItem, aSize, aMeta)        ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aEntity, rStack); return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (Entity aEntity, Block aBlock, long aSize, long aMeta                ) {ItemStack rStack = make(aBlock, aSize, aMeta)       ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aEntity, rStack); return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (Entity aEntity, ItemStackContainer aStack                           ) {ItemStack rStack = aStack.toStack()                 ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aEntity, rStack); return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (Entity aEntity, ItemStack aStack                                    ) {ItemStack rStack = aStack                           ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aEntity, rStack); return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem entity (Entity aEntity, ModData aModID, String aItem, long aSize, long aMeta) {ItemStack rStack = make(aModID, aItem, aSize, aMeta); if (invalid(rStack)) return null; return               entity_(aEntity, rStack);}
	public static EntityItem entity (Entity aEntity, Item aItem, long aSize, long aMeta                  ) {ItemStack rStack = make(aItem, aSize, aMeta)        ; if (invalid(rStack)) return null; return               entity_(aEntity, rStack);}
	public static EntityItem entity (Entity aEntity, Block aBlock, long aSize, long aMeta                ) {ItemStack rStack = make(aBlock, aSize, aMeta)       ; if (invalid(rStack)) return null; return               entity_(aEntity, rStack);}
	public static EntityItem entity (Entity aEntity, ItemStackContainer aStack                           ) {ItemStack rStack = aStack.toStack()                 ; if (invalid(rStack)) return null; return               entity_(aEntity, rStack);}
	public static EntityItem entity (Entity aEntity, ItemStack aStack                                    ) {ItemStack rStack = aStack                           ; if (invalid(rStack)) return null; return               entity_(aEntity, rStack);}
	public static EntityItem entity_(Entity aEntity, ItemStack aStack                                    ) {return new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(aStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ)));}
	
	public static EntityItem place  (World aWorld, ChunkCoordinates aCoords, ModData aModID, String aItem, long aSize, long aMeta) {ItemStack rStack = make(aModID, aItem, aSize, aMeta); if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aCoords, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place  (World aWorld, ChunkCoordinates aCoords, Item aItem, long aSize, long aMeta                  ) {ItemStack rStack = make(aItem, aSize, aMeta)        ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aCoords, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place  (World aWorld, ChunkCoordinates aCoords, Block aBlock, long aSize, long aMeta                ) {ItemStack rStack = make(aBlock, aSize, aMeta)       ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aCoords, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place  (World aWorld, ChunkCoordinates aCoords, ItemStackContainer aStack                           ) {ItemStack rStack = aStack.toStack()                 ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aCoords, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place  (World aWorld, ChunkCoordinates aCoords, ItemStack aStack                                    ) {ItemStack rStack = aStack                           ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aCoords, rStack); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (World aWorld, ChunkCoordinates aCoords, ModData aModID, String aItem, long aSize, long aMeta) {ItemStack rStack = make(aModID, aItem, aSize, aMeta); if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aCoords, rStack); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (World aWorld, ChunkCoordinates aCoords, Item aItem, long aSize, long aMeta                  ) {ItemStack rStack = make(aItem, aSize, aMeta)        ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aCoords, rStack); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (World aWorld, ChunkCoordinates aCoords, Block aBlock, long aSize, long aMeta                ) {ItemStack rStack = make(aBlock, aSize, aMeta)       ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aCoords, rStack); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (World aWorld, ChunkCoordinates aCoords, ItemStackContainer aStack                           ) {ItemStack rStack = aStack.toStack()                 ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aCoords, rStack); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop   (World aWorld, ChunkCoordinates aCoords, ItemStack aStack                                    ) {ItemStack rStack = aStack                           ; if (invalid(rStack)) return null; EntityItem rEntity = entity_(aWorld, aCoords, rStack); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem entity (World aWorld, ChunkCoordinates aCoords, ModData aModID, String aItem, long aSize, long aMeta) {ItemStack rStack = make(aModID, aItem, aSize, aMeta); if (invalid(rStack)) return null; return               entity_(aWorld, aCoords, rStack);}
	public static EntityItem entity (World aWorld, ChunkCoordinates aCoords, Item aItem, long aSize, long aMeta                  ) {ItemStack rStack = make(aItem, aSize, aMeta)        ; if (invalid(rStack)) return null; return               entity_(aWorld, aCoords, rStack);}
	public static EntityItem entity (World aWorld, ChunkCoordinates aCoords, Block aBlock, long aSize, long aMeta                ) {ItemStack rStack = make(aBlock, aSize, aMeta)       ; if (invalid(rStack)) return null; return               entity_(aWorld, aCoords, rStack);}
	public static EntityItem entity (World aWorld, ChunkCoordinates aCoords, ItemStackContainer aStack                           ) {ItemStack rStack = aStack.toStack()                 ; if (invalid(rStack)) return null; return               entity_(aWorld, aCoords, rStack);}
	public static EntityItem entity (World aWorld, ChunkCoordinates aCoords, ItemStack aStack                                    ) {ItemStack rStack = aStack                           ; if (invalid(rStack)) return null; return               entity_(aWorld, aCoords, rStack);}
	public static EntityItem entity_(World aWorld, ChunkCoordinates aCoords, ItemStack aStack                                    ) {return new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(aStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ));}
	
	public static ItemStack tag(long aNumber) {
		return IL.Circuit_Selector.getWithDamage(0, aNumber);
	}
	
	public static ItemStack book(String aMapping) {
		return UT.Books.getBookWithTitle(aMapping);
	}
	public static ItemStack book(String aMapping, ItemStack aBook) {
		return UT.Books.getBookWithTitle(aMapping, aBook);
	}
	
	public static boolean debug(ItemStack aStack) {
		return ItemsGT.DEBUG_ITEMS.contains(aStack, T);
	}
	
	public static boolean torch(ItemStack aStack) {
		if (IL.GC_Torch_Glowstone.equal(aStack, T, T) || IL.AETHER_Torch_Ambrosium.equal(aStack, T, T) || IL.TC_Nitor.equal(aStack, F, T)) return T;
		Block tBlock = block(aStack);
		return tBlock instanceof BlockTorch && !(tBlock instanceof BlockRedstoneTorch);
	}
	
	public static boolean ammo(ItemStack aStack) {
		if (ItemsGT.AMMO_ITEMS.contains(aStack, T)) return T;
		OreDictItemData tData = OM.anydata(aStack);
		return tData != null && tData.mPrefix != null && tData.mPrefix.contains(TD.Prefix.AMMO_ALIKE);
	}
	
	public static boolean nonautoinsert(ItemStack aStack) {
		if (ItemsGT.NON_AUTO_INSERT_ITEMS.contains(aStack, T) || torch(aStack)) return T;
		OreDictItemData tData = OM.anydata(aStack);
		return tData != null && tData.mPrefix != null && tData.mPrefix.contains(TD.Prefix.AMMO_ALIKE);
	}
	
	public static boolean listed(Collection<ItemStack> aList, ItemStack aStack, boolean aTrueIfListEmpty, boolean aInvertFilter) {
		if (aStack == null || aStack.stackSize < 1) return F;
		if (aList == null) return aTrueIfListEmpty;
		while (aList.contains(null)) aList.remove(null);
		if (aList.size() < 1) return aTrueIfListEmpty;
		Iterator<ItemStack> tIterator = aList.iterator();
		ItemStack tStack = null;
		while (tIterator.hasNext()) if ((tStack = tIterator.next())!= null && equal(aStack, tStack)) return !aInvertFilter;
		return aInvertFilter;
	}
	
	public static ItemStack container(ItemStack aStack, boolean aCheckIFluidContainerItems) {
		if (invalid(aStack)) return NI;
		if (item_(aStack).hasContainerItem(aStack)) return copy(item_(aStack).getContainerItem(aStack));
		/** These are all special Cases, in which it is intended to have only GT Blocks outputting those Container Items */
		if (IL.Cell_Empty.equal(aStack, F, T)) return NI;
		
		if (aCheckIFluidContainerItems && item_(aStack) instanceof IFluidContainerItem && ((IFluidContainerItem)item_(aStack)).getCapacity(aStack) > 0) {
			ItemStack tStack = amount(1, aStack);
			((IFluidContainerItem)item_(aStack)).drain(tStack, Integer.MAX_VALUE, T);
			if (tStack.stackSize <= 0) return NI;
			if (tStack.getTagCompound() == null) return tStack;
			if (tStack.getTagCompound().hasNoTags()) tStack.setTagCompound(null);
			return tStack;
		}
		
		if (IL.IC2_ForgeHammer.equal(aStack, T, T) || IL.IC2_WireCutter.equal(aStack, T, T)) return copyMeta(meta_(aStack) + 1, aStack);
		return NI;
	}
	
	public static ItemStack container(ItemStack aStack, boolean aCheckIFluidContainerItems, int aSize) {
		return amount(aSize, container(aStack, aCheckIFluidContainerItems));
	}
	
	public static boolean rotten(ItemStack aStack) {
		if (invalid(aStack)) return F;
		if (item_(aStack) instanceof MultiItemRandom) {
			IFoodStat tStat = ((MultiItemRandom)item_(aStack)).mFoodStats.get(meta_(aStack));
			return tStat != null && tStat.isRotten(item_(aStack), aStack, null);
		}
		return item_(aStack) == Items.rotten_flesh || OM.materialcontained(aStack, MT.MeatRotten, MT.FishRotten);
	}
	
	public static int food(ItemStack aStack) {
		if (invalid(aStack)) return 0;
		if (item_(aStack) instanceof ItemFood) {
			try {
				return ((ItemFood)item_(aStack)).func_150905_g(aStack);
			} catch(Throwable e) {
				return 1;
			}
		}
		if (item_(aStack) instanceof MultiItemRandom) {
			IFoodStat tStat = ((MultiItemRandom)item_(aStack)).mFoodStats.get(meta_(aStack));
			return tStat == null ? 0 : tStat.getFoodLevel(item_(aStack), aStack, null);
		}
		return 0;
	}
	
	public static float saturation(ItemStack aStack) {
		if (invalid(aStack)) return 0;
		if (item_(aStack) instanceof ItemFood) {
			try {
				return ((ItemFood)item_(aStack)).func_150906_h(aStack);
			} catch(Throwable e) {
				return 0.5F;
			}
		}
		if (item_(aStack) instanceof MultiItemRandom) {
			IFoodStat tStat = ((MultiItemRandom)item_(aStack)).mFoodStats.get(meta_(aStack));
			return tStat == null ? 0 : tStat.getSaturation(item_(aStack), aStack, null);
		}
		return 0;
	}
	
	/** @param aValue the Value of this Stack, when burning inside a Furnace (200 = 1 Burn Process = 5000 HU, max = 32767 (that is 819175 HU)), limited to Short because the vanilla Furnace otherwise can't handle it properly, stupid Mojang... */
	public static ItemStack fuel(ItemStack aStack, short aValue) {aStack.setTagCompound(UT.NBT.makeShort(aStack.getTagCompound(), NBT_FUEL_VALUE, aValue)); return aStack;}
	/** @return the Value of this Stack, when burning inside a Furnace (200 = 1 Burn Process = 5000 HU, max = 32767 (that is 819175 HU)), limited to Short because the vanilla Furnace otherwise can't handle it properly, stupid Mojang... */
	public static long fuel(ItemStack aStack) {
		if (invalid(aStack)) return 0;
		long rFuelValue = GameRegistry.getFuelValue(aStack);
		if (rFuelValue > 0) return rFuelValue;
		Item tItem = item_(aStack);
		if (tItem instanceof ItemBlock && Block.getBlockFromItem(tItem) != NB) {
			Block tBlock = Block.getBlockFromItem(tItem);
			if (tBlock == Blocks.sapling) return 100;
			if (tBlock == Blocks.wooden_slab) return 150;
			if (tBlock == Blocks.coal_block) return 16000;
			if (tBlock.getMaterial() == Material.wood) return 300;
		}
		if (tItem instanceof ItemTool && ((ItemTool)tItem).getToolMaterialName().equals("WOOD")) return 200;
		if (tItem instanceof ItemSword && ((ItemSword)tItem).getToolMaterialName().equals("WOOD")) return 200;
		if (tItem instanceof ItemHoe && ((ItemHoe)tItem).getToolMaterialName().equals("WOOD")) return 200;
		if (tItem == Items.stick) return 100;
		if (tItem == Items.coal) return 1600;
		if (tItem == Items.blaze_rod) return 2400;
		if (tItem == Items.lava_bucket) return 20000;
		return 0;
	}
	
	public static Integer[] toIntegerArray(ItemStack... aStacks) {
		Integer[] rArray = new Integer[aStacks.length];
		for (int i = 0; i < rArray.length; i++) rArray[i] = toInt(aStacks[i]);
		return rArray;
	}
	
	public static int[] toIntArray(ItemStack... aStacks) {
		int[] rArray = new int[aStacks.length];
		for (int i = 0; i < rArray.length; i++) rArray[i] = toInt(aStacks[i]);
		return rArray;
	}
	
	public static String configName(ItemStack aStack) {
		if (invalid(aStack)) return "";
		Object rName = OreDictManager.INSTANCE.getAssociation_(aStack, T);
		if (rName != null) return rName.toString();
		try {if (UT.Code.stringValid(rName = aStack.getUnlocalizedName())) return rName.toString();} catch (Throwable e) {/*Do nothing*/}
		return item_(aStack) + "." + meta_(aStack);
	}
	public static String configNames(ItemStack... aStacks) {
		String rString = "";
		for (ItemStack tStack : aStacks) rString += (tStack == null ? "null;" : configName(tStack) + ";");
		return rString;
	}
	public static String regName(ItemStack aStack) {
		return invalid(aStack) ? "null" : Item.itemRegistry.getNameForObject(item_(aStack));
	}
	public static String names(ItemStack... aStacks) {
		String rString = "";
		for (ItemStack tStack : aStacks) rString += (tStack == null ? "null; " : tStack.getDisplayName() + "; ");
		return rString;
	}
	public static String namesAndSizes(ItemStack... aStacks) {
		String rString = "";
		for (ItemStack tStack : aStacks) rString += (tStack == null ? "null; " : tStack.getDisplayName() + " " + tStack.stackSize + "; ");
		return rString;
	}
	
	public static void hide(Item aItem) {
		hide(aItem, W);
	}
	public static void hide(Item aItem, long aMeta) {
		hide(make(aItem, 1, aMeta));
	}
	public static void hide(Block aBlock) {
		hide(aBlock, W);
	}
	public static void hide(Block aBlock, long aMeta) {
		hide(make(aBlock, 1, aMeta));
	}
	public static void hide(ItemStack aStack) {
		if (aStack != null) try {codechicken.nei.api.API.hideItem(aStack);} catch(Throwable e) {/**/}
	}
	
	/** Loads an ItemStack properly. */
	public static ItemStack load(NBTTagCompound aNBT, String aTagName) {
		return aNBT == null ? null : load(aNBT.getCompoundTag(aTagName));
	}
	
	/** Loads an ItemStack properly. */
	public static ItemStack load(NBTTagCompound aNBT) {
		if (aNBT == null || aNBT.hasNoTags()) return null;
		ItemStack rStack = make(Item.getItemById(aNBT.getShort("id")), aNBT.getInteger("Count"), aNBT.getShort("Damage"), aNBT.hasKey("tag", 10)?aNBT.getCompoundTag("tag"):null);
		if (rStack == null) return null;
		if (item_(rStack).getClass().getName().startsWith("ic2.core.migration")) item_(rStack).onUpdate(rStack, DW, null, 0, F);
		return update_(OM.get_(rStack));
	}
	
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(NBTTagCompound aNBT, String aTagName, ItemStack aStack) {
		if (aNBT == null) aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(aStack);
		if (tNBT != null) aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(ItemStack aStack) {
		if (invalid(aStack)) return null;
		NBTTagCompound rNBT = UT.NBT.make();
		aStack = OM.get_(aStack);
		rNBT.setShort("id", (short)Item.getIdFromItem(item_(aStack)));
		UT.NBT.setNumber(rNBT, "Count", aStack.stackSize);
		rNBT.setShort("Damage", meta_(aStack));
		if (aStack.hasTagCompound()) rNBT.setTag("tag", aStack.getTagCompound());
		return rNBT;
	}
}
