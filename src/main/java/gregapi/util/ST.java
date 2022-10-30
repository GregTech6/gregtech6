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

package gregapi.util;

import cpw.mods.fml.common.registry.GameRegistry;
import gregapi.block.ItemBlockBase;
import gregapi.code.IItemContainer;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackSet;
import gregapi.code.ModData;
import gregapi.data.IL;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.TD;
import gregapi.item.IItemGT;
import gregapi.item.IItemGTContainerTool;
import gregapi.item.IItemUpdatable;
import gregapi.item.multiitem.MultiItemRandom;
import gregapi.item.multiitem.food.IFoodStat;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictManager;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.delegate.ITileEntityCanDelegate;
import ic2.api.item.IC2Items;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.IFluidContainerItem;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class ST {
	public static boolean TE_PIPES = F, BC_PIPES = F;
	
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void checkAvailabilities() {
		try {
			cofh.api.transport.IItemDuct.class.getCanonicalName();
			TE_PIPES = T;
		} catch(Throwable e) {/**/}
		try {
			buildcraft.api.transport.IInjectable.class.getCanonicalName();
			BC_PIPES = T;
		} catch(Throwable e) {/**/}
	}
	
	public static boolean equal (ItemStack aStack1, ItemStack aStack2) {return equal(aStack1, aStack2, F);}
	public static boolean equal (ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {return aStack1 != null && aStack2 != null && equal_(aStack1, aStack2, aIgnoreNBT);}
	public static boolean equal_(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {return item_(aStack1) == item_(aStack2) && equal(meta_(aStack1), meta_(aStack2)) && (aIgnoreNBT || (((nbt_(aStack1) == null) == (nbt_(aStack2) == null)) && (nbt_(aStack1) == null || nbt_(aStack1).equals(nbt_(aStack2)))));}
	
	public static boolean equal (ItemStack aStack, Item  aItem                             ) {return aStack != null && aItem  != null && equal_(aStack, aItem );}
	public static boolean equal (ItemStack aStack, Block aBlock                            ) {return aStack != null && aBlock != null && equal_(aStack, aBlock);}
	public static boolean equal_(ItemStack aStack, Item  aItem                             ) {return item_ (aStack) == aItem ;}
	public static boolean equal_(ItemStack aStack, Block aBlock                            ) {return block_(aStack) == aBlock;}
	public static boolean equal (ItemStack aStack, Item  aItem                 , long aMeta) {return aStack != null && aItem  != null && equal_(aStack, aItem , aMeta);}
	public static boolean equal (ItemStack aStack, Block aBlock                , long aMeta) {return aStack != null && aBlock != null && equal_(aStack, aBlock, aMeta);}
	public static boolean equal_(ItemStack aStack, Item  aItem                 , long aMeta) {return equal(meta_(aStack), aMeta) && item_ (aStack) == aItem ;}
	public static boolean equal_(ItemStack aStack, Block aBlock                , long aMeta) {return equal(meta_(aStack), aMeta) && block_(aStack) == aBlock;}
	public static boolean equal (ItemStack aStack, ModData aModID, String aItem            ) {return equal(aStack, GameRegistry.findItem(aModID.mID, aItem));}
	public static boolean equal (ItemStack aStack, ModData aModID, String aItem, long aMeta) {return equal(aStack, GameRegistry.findItem(aModID.mID, aItem), aMeta);}
	
	public static boolean equal (ItemStack aStack, Item  aItem                             , boolean aAllowNBT) {return aStack != null && aItem  != null && equal_(aStack, aItem , aAllowNBT);}
	public static boolean equal (ItemStack aStack, Block aBlock                            , boolean aAllowNBT) {return aStack != null && aBlock != null && equal_(aStack, aBlock, aAllowNBT);}
	public static boolean equal_(ItemStack aStack, Item  aItem                             , boolean aAllowNBT) {return item_ (aStack) == aItem  && aAllowNBT == aStack.hasTagCompound();}
	public static boolean equal_(ItemStack aStack, Block aBlock                            , boolean aAllowNBT) {return block_(aStack) == aBlock && aAllowNBT == aStack.hasTagCompound();}
	public static boolean equal (ItemStack aStack, Item  aItem                 , long aMeta, boolean aAllowNBT) {return aStack != null && aItem  != null && equal_(aStack, aItem , aMeta, aAllowNBT);}
	public static boolean equal (ItemStack aStack, Block aBlock                , long aMeta, boolean aAllowNBT) {return aStack != null && aBlock != null && equal_(aStack, aBlock, aMeta, aAllowNBT);}
	public static boolean equal_(ItemStack aStack, Item  aItem                 , long aMeta, boolean aAllowNBT) {return equal(meta_(aStack), aMeta) && item_ (aStack) == aItem  && aAllowNBT == aStack.hasTagCompound();}
	public static boolean equal_(ItemStack aStack, Block aBlock                , long aMeta, boolean aAllowNBT) {return equal(meta_(aStack), aMeta) && block_(aStack) == aBlock && aAllowNBT == aStack.hasTagCompound();}
	public static boolean equal (ItemStack aStack, ModData aModID, String aItem            , boolean aAllowNBT) {return equal(aStack, GameRegistry.findItem(aModID.mID, aItem), aAllowNBT);}
	public static boolean equal (ItemStack aStack, ModData aModID, String aItem, long aMeta, boolean aAllowNBT) {return equal(aStack, GameRegistry.findItem(aModID.mID, aItem), aMeta, aAllowNBT);}
	
	public static boolean equal (long aMeta1, long aMeta2) {return aMeta1 == aMeta2 || aMeta1 == W || aMeta2 == W;}
	
	public static boolean equalTools (ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {return aStack1 != null && aStack2 != null && equalTools_(aStack1, aStack2, aIgnoreNBT);}
	public static boolean equalTools_(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {return item_(aStack1) == item_(aStack2) && equal(meta_(aStack1), meta_(aStack2)) && (aIgnoreNBT || item_(aStack1) instanceof IItemGTContainerTool || (((nbt_(aStack1) == null) == (nbt_(aStack2) == null)) && (nbt_(aStack1) == null || nbt_(aStack1).equals(nbt_(aStack2)))));}
	
	public static boolean identical (ItemStack aStack1, ItemStack aStack2) {return aStack1 == aStack2 || (aStack1 != null && aStack2 != null && identical_(aStack1, aStack2));}
	public static boolean identical_(ItemStack aStack1, ItemStack aStack2) {return aStack1.stackSize == aStack2.stackSize && equal_(aStack1, aStack2, F);}
	
	public static boolean isGT (Item aItem) {return aItem instanceof IItemGT;}
	public static boolean isGT (Block aBlock) {return aBlock instanceof IItemGT;}
	public static boolean isGT (ItemStack aStack) {return aStack != null && isGT_(aStack);}
	public static boolean isGT_(ItemStack aStack) {return isGT(aStack.getItem());}
	
	public static boolean   valid(Block aBlock) {return aBlock != null && aBlock != NB;}
	public static boolean invalid(Block aBlock) {return aBlock == null || aBlock == NB;}
	public static boolean   valid(ItemStack aStack) {return aStack != null && aStack.stackSize >= 0 && item_(aStack) != null;}
	public static boolean invalid(ItemStack aStack) {return aStack == null || aStack.stackSize <  0 || item_(aStack) == null;}
	
	public static ItemStack validate(ItemStack aStack) {return valid(aStack)                         ? aStack : null;}
	public static ItemStack valisize(ItemStack aStack) {return valid(aStack) && aStack.stackSize > 0 ? aStack : null;}
	
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
	
	public static Block nullair(Block aBlock) {return aBlock != NB ? aBlock : null;}
	
	public static int toInt(Item aItem, long aMeta) {return aItem == null ? 0 : id_(aItem) | (((short)aMeta)<<16);}
	public static int toInt(ItemStack aStack) {return aStack != null ? toInt(item_(aStack), meta_(aStack)) : 0;}
	public static int toInt(ItemStack aStack, long aMeta) {return aStack != null ? toInt(item_(aStack), aMeta) : 0;}
	
	public static ItemStack toStack(int aStack) {return make(toItem(aStack), 1, toMeta(aStack));}
	public static Block     toBlock(int aStack) {return block(aStack&(~0>>>16));}
	public static Item      toItem (int aStack) {return item(aStack&(~0>>>16));}
	public static short     toMeta (int aStack) {return (short)(aStack>>>16);}
	
	public static String regName (ItemStack aStack) {return regName(item(aStack));}
	public static String regName (Block     aBlock) {return regName(item(aBlock));}
	public static String regName (Item      aItem ) {return aItem == null ? null : regName_(aItem);}
	public static String regName_(Item      aItem ) {return Item.itemRegistry.getNameForObject(aItem);}
	
	public static boolean ownedBy (ModData aMod, IBlockAccess aWorld, int aX, int aY, int aZ) {return aMod.mLoaded && ownedBy(aMod.mID, aWorld, aX, aY, aZ);}
	public static boolean ownedBy (ModData aMod, ItemStack    aStack                        ) {return aMod.mLoaded && ownedBy(aMod.mID, aStack);}
	public static boolean ownedBy (ModData aMod, Block        aBlock                        ) {return aMod.mLoaded && ownedBy(aMod.mID, aBlock);}
	public static boolean ownedBy (ModData aMod, Item         aItem                         ) {return aMod.mLoaded && ownedBy(aMod.mID, aItem);}
	public static boolean ownedBy (ModData aMod, String       aRegName                      ) {return aMod.mLoaded && ownedBy(aMod.mID, aRegName);}
	public static boolean ownedBy (String  aMod, IBlockAccess aWorld, int aX, int aY, int aZ) {return ownedBy(aMod, aWorld.getBlock(aX, aY, aZ));}
	public static boolean ownedBy (String  aMod, ItemStack    aStack                        ) {return ownedBy(aMod, regName(aStack));}
	public static boolean ownedBy (String  aMod, Block        aBlock                        ) {return ownedBy(aMod, regName(aBlock));}
	public static boolean ownedBy (String  aMod, Item         aItem                         ) {return ownedBy(aMod, regName(aItem));}
	public static boolean ownedBy (String  aMod, String       aRegName                      ) {return aRegName != null && aMod != null && ownedBy_(aMod, aRegName);}
	public static boolean ownedBy_(String  aMod, String       aRegName                      ) {return aRegName.startsWith(aMod);}
	
	public static void register(Item aItem, String aRegistryName) {
		GameRegistry.registerItem(aItem, aRegistryName);
	}
	public static void register(Block aBlock, String aRegistryName) {register(aBlock, aRegistryName, null);}
	public static void register(Block aBlock, String aRegistryName, Class<? extends ItemBlock> aItemClass) {
		GameRegistry.registerBlock(aBlock, aItemClass == null ? ItemBlockBase.class : aItemClass, aRegistryName);
		if (COMPAT_IC2 != null) COMPAT_IC2.addToExplosionWhitelist(aBlock);
	}
	
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
		if (item_(aStack) instanceof IItemUpdatable) ((IItemUpdatable)item_(aStack)).updateItemStack(aStack, aWorld, aX, aY, aZ);
		return aStack;
	}
	public static ItemStack update (ItemStack aStack, Entity aEntity) {
		return update(aStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
	}
	public static ItemStack update_(ItemStack aStack, Entity aEntity) {
		return update_(aStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
	}
	
	public static boolean update(Entity aPlayer) {
		if (aPlayer instanceof EntityPlayer && !aPlayer.worldObj.isRemote && ((EntityPlayer)aPlayer).openContainer != null) ((EntityPlayer)aPlayer).openContainer.detectAndSendChanges();
		return T;
	}
	
	public static boolean use(Entity aPlayer, ItemStack aStack) {
		return use(aPlayer, F, aStack, 1);
	}
	public static boolean use(Entity aPlayer, ItemStack aStack, long aAmount) {
		return use(aPlayer, F, aStack, aAmount);
	}
	public static boolean use(Entity aPlayer, boolean aRemove, ItemStack aStack) {
		return use(aPlayer, aRemove, aStack, 1);
	}
	public static boolean use(Entity aPlayer, boolean aRemove, ItemStack aStack, long aAmount) {
		if (UT.Entities.hasInfiniteItems(aPlayer)) return T;
		if (aStack.stackSize < aAmount) return F;
		aStack.stackSize -= aAmount;
		if (!(aPlayer instanceof EntityPlayer)) return T;
		if (aStack.stackSize <= 0) {
			ForgeEventFactory.onPlayerDestroyItem((EntityPlayer)aPlayer, aStack);
			if (aRemove) for (int i = 0; i < ((EntityPlayer)aPlayer).inventory.mainInventory.length; i++) {
				if (((EntityPlayer)aPlayer).inventory.mainInventory[i] == aStack) {
					((EntityPlayer)aPlayer).inventory.mainInventory[i] = null;
					break;
				}
			}
		}
		ST.update(aPlayer);
		return T;
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
	
	public static boolean hasValid(ItemStack... aStacks) {if (aStacks != null) for (ItemStack aStack : aStacks) if (valid(aStack)) return T; return F;}
	
	
	public static ItemStack[] array(ItemStack... aStacks) {return aStacks;}
	
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
	public static EntityItem entity_(Entity aEntity, ItemStack aStack                                    ) {return new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(aStack, aEntity));}
	
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
	
	@SuppressWarnings("rawtypes")
	public static int move(DelegatorTileEntity aFrom, DelegatorTileEntity aTo) {return move(aFrom, aTo, null, F, F, F, T, 64, 1, 64, 1);}
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static int move(DelegatorTileEntity aFrom, DelegatorTileEntity aTo, ItemStackSet<ItemStackContainer> aFilter, boolean aIgnoreSideFrom, boolean aIgnoreSideTo, boolean aInvertFilter, boolean aEjectItems, int aMaxSize, int aMinSize, int aMaxMove, int aMinMove) {
		if (!(aFrom.mTileEntity instanceof IInventory)) return 0;
		aFrom = getPotentialDoubleChest(aFrom);
		int[] aSlotsFrom = (!aIgnoreSideFrom && aFrom.mTileEntity instanceof ISidedInventory ? ((ISidedInventory)aFrom.mTileEntity).getAccessibleSlotsFromSide(aFrom.mSideOfTileEntity) : UT.Code.getAscendingArray(((IInventory)aFrom.mTileEntity).getSizeInventory()));
		if (!(aTo.mTileEntity instanceof IInventory)) return put(aFrom, aSlotsFrom, aTo, aFilter, aIgnoreSideFrom, aInvertFilter, aEjectItems, aMaxMove, aMinMove);
		aTo = getPotentialDoubleChest(aTo);
		int[] aSlotsTo   = (!aIgnoreSideTo   && aTo  .mTileEntity instanceof ISidedInventory ? ((ISidedInventory)aTo  .mTileEntity).getAccessibleSlotsFromSide(aTo  .mSideOfTileEntity) : UT.Code.getAscendingArray(((IInventory)aTo  .mTileEntity).getSizeInventory()));
		
		for (int aSlotFrom : aSlotsFrom) {
			ItemStack aStackFrom = ((IInventory)aFrom.mTileEntity).getStackInSlot(aSlotFrom);
			if (aStackFrom == null || aStackFrom.stackSize < aMinMove || (aFilter != null && aFilter.contains(aStackFrom, T) == aInvertFilter) || !canTake((IInventory)aFrom.mTileEntity, aIgnoreSideFrom ? SIDE_ANY : aFrom.mSideOfTileEntity, aFrom.mSideOfTileEntity, aSlotFrom, aStackFrom)) continue;
			for (int aSlotTo : aSlotsTo) {
				ItemStack aStackTo = ((IInventory)aTo.mTileEntity).getStackInSlot(aSlotTo);
				int tMovable = Math.min(aMaxMove, canPut((IInventory)aTo.mTileEntity, aIgnoreSideTo ? SIDE_ANY : aTo.mSideOfTileEntity, aTo.mSideOfTileEntity, aSlotTo, aStackFrom, aStackTo, Math.min(aMaxSize, aStackFrom.getMaxStackSize())));
				if (tMovable < aMinMove || tMovable + (aStackTo == null ? 0 : aStackTo.stackSize) < aMinSize) continue;
				// Actually Moving the Stack
				return move_((IInventory)aFrom.mTileEntity, (IInventory)aTo.mTileEntity, aStackFrom, aStackTo, aSlotFrom, aSlotTo, tMovable);
			}
		}
		return 0;
	}
	
	
	@SuppressWarnings("rawtypes")
	public static int moveAll(DelegatorTileEntity aFrom, DelegatorTileEntity aTo) {return moveAll(aFrom, aTo, null, F, F, F, T, 64, 1, 64, 1);}
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static int moveAll(DelegatorTileEntity aFrom, DelegatorTileEntity aTo, ItemStackSet<ItemStackContainer> aFilter, boolean aIgnoreSideFrom, boolean aIgnoreSideTo, boolean aInvertFilter, boolean aEjectItems, int aMaxSize, int aMinSize, int aMaxMove, int aMinMove) {
		if (!(aFrom.mTileEntity instanceof IInventory)) return 0;
		aFrom = getPotentialDoubleChest(aFrom);
		int[] aSlotsFrom = (!aIgnoreSideFrom && aFrom.mTileEntity instanceof ISidedInventory ? ((ISidedInventory)aFrom.mTileEntity).getAccessibleSlotsFromSide(aFrom.mSideOfTileEntity) : UT.Code.getAscendingArray(((IInventory)aFrom.mTileEntity).getSizeInventory()));
		if (!(aTo.mTileEntity instanceof IInventory)) return put(aFrom, aSlotsFrom, aTo, aFilter, aIgnoreSideFrom, aInvertFilter, aEjectItems, aMaxMove, aMinMove);
		aTo = getPotentialDoubleChest(aTo);
		int[] aSlotsTo   = (!aIgnoreSideTo   && aTo  .mTileEntity instanceof ISidedInventory ? ((ISidedInventory)aTo  .mTileEntity).getAccessibleSlotsFromSide(aTo  .mSideOfTileEntity) : UT.Code.getAscendingArray(((IInventory)aTo  .mTileEntity).getSizeInventory()));
		
		int rMoved = 0;
		
		for (int aSlotFrom : aSlotsFrom) {
			ItemStack aStackFrom = ((IInventory)aFrom.mTileEntity).getStackInSlot(aSlotFrom);
			if (aStackFrom == null || aStackFrom.stackSize < aMinMove || (aFilter != null && aFilter.contains(aStackFrom, T) == aInvertFilter) || !canTake((IInventory)aFrom.mTileEntity, aIgnoreSideFrom ? SIDE_ANY : aFrom.mSideOfTileEntity, aFrom.mSideOfTileEntity, aSlotFrom, aStackFrom)) continue;
			for (int aSlotTo : aSlotsTo) {
				ItemStack aStackTo = ((IInventory)aTo.mTileEntity).getStackInSlot(aSlotTo);
				int tMovable = Math.min(aMaxMove, canPut((IInventory)aTo.mTileEntity, aIgnoreSideTo ? SIDE_ANY : aTo.mSideOfTileEntity, aTo.mSideOfTileEntity, aSlotTo, aStackFrom, aStackTo, Math.min(aMaxSize, aStackFrom.getMaxStackSize())));
				if (tMovable < aMinMove || tMovable + (aStackTo == null ? 0 : aStackTo.stackSize) < aMinSize) continue;
				// Actually Moving the Stack
				rMoved += move_((IInventory)aFrom.mTileEntity, (IInventory)aTo.mTileEntity, aStackFrom, aStackTo, aSlotFrom, aSlotTo, tMovable);
				break;
			}
		}
		return rMoved;
	}
	
	
	@SuppressWarnings("rawtypes")
	public static int moveFrom(DelegatorTileEntity aFrom, DelegatorTileEntity aTo, int aSlotFrom) {return moveFrom(aFrom, aTo, aSlotFrom, null, F, F, F, T, 64, 1, 64, 1);}
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static int moveFrom(DelegatorTileEntity aFrom, DelegatorTileEntity aTo, int aSlotFrom, ItemStackSet<ItemStackContainer> aFilter, boolean aIgnoreSideFrom, boolean aIgnoreSideTo, boolean aInvertFilter, boolean aEjectItems, int aMaxSize, int aMinSize, int aMaxMove, int aMinMove) {
		if (aSlotFrom < 0) return 0;
		if (!(aFrom.mTileEntity instanceof IInventory)) return 0;
		aFrom = getPotentialDoubleChest(aFrom);
		if (aSlotFrom >= ((IInventory)aFrom.mTileEntity).getSizeInventory()) return 0;
		if (!(aTo.mTileEntity instanceof IInventory)) return put(aFrom, new int[] {aSlotFrom}, aTo, aFilter, aIgnoreSideFrom, aInvertFilter, aEjectItems, aMaxMove, aMinMove);
		aTo = getPotentialDoubleChest(aTo);
		int[] aSlotsTo   = (!aIgnoreSideTo   && aTo  .mTileEntity instanceof ISidedInventory ? ((ISidedInventory)aTo  .mTileEntity).getAccessibleSlotsFromSide(aTo  .mSideOfTileEntity) : UT.Code.getAscendingArray(((IInventory)aTo  .mTileEntity).getSizeInventory()));
		
		ItemStack aStackFrom = ((IInventory)aFrom.mTileEntity).getStackInSlot(aSlotFrom);
		if (aStackFrom == null || aStackFrom.stackSize < aMinMove || (aFilter != null && aFilter.contains(aStackFrom, T) == aInvertFilter) || !canTake((IInventory)aFrom.mTileEntity, aIgnoreSideFrom ? SIDE_ANY : aFrom.mSideOfTileEntity, aFrom.mSideOfTileEntity, aSlotFrom, aStackFrom)) return 0;
		for (int aSlotTo : aSlotsTo) {
			ItemStack aStackTo = ((IInventory)aTo.mTileEntity).getStackInSlot(aSlotTo);
			int tMovable = Math.min(aMaxMove, canPut((IInventory)aTo.mTileEntity, aIgnoreSideTo ? SIDE_ANY : aTo.mSideOfTileEntity, aTo.mSideOfTileEntity, aSlotTo, aStackFrom, aStackTo, Math.min(aMaxSize, aStackFrom.getMaxStackSize())));
			if (tMovable < aMinMove || tMovable + (aStackTo == null ? 0 : aStackTo.stackSize) < aMinSize) continue;
			// Actually Moving the Stack
			return move_((IInventory)aFrom.mTileEntity, (IInventory)aTo.mTileEntity, aStackFrom, aStackTo, aSlotFrom, aSlotTo, tMovable);
		}
		return 0;
	}
	
	@SuppressWarnings("rawtypes")
	public static int moveTo(DelegatorTileEntity aFrom, DelegatorTileEntity aTo, int aSlotTo) {return moveTo(aFrom, aTo, aSlotTo, null, F, F, F, T, 64, 1, 64, 1);}
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static int moveTo(DelegatorTileEntity aFrom, DelegatorTileEntity aTo, int aSlotTo, ItemStackSet<ItemStackContainer> aFilter, boolean aIgnoreSideFrom, boolean aIgnoreSideTo, boolean aInvertFilter, boolean aEjectItems, int aMaxSize, int aMinSize, int aMaxMove, int aMinMove) {
		if (aSlotTo < 0) return 0;
		if (!(aFrom.mTileEntity instanceof IInventory)) return 0;
		aFrom = getPotentialDoubleChest(aFrom);
		int[] aSlotsFrom = (!aIgnoreSideFrom && aFrom.mTileEntity instanceof ISidedInventory ? ((ISidedInventory)aFrom.mTileEntity).getAccessibleSlotsFromSide(aFrom.mSideOfTileEntity) : UT.Code.getAscendingArray(((IInventory)aFrom.mTileEntity).getSizeInventory()));
		if (!(aTo.mTileEntity instanceof IInventory)) return put(aFrom, aSlotsFrom, aTo, aFilter, aIgnoreSideFrom, aInvertFilter, aEjectItems, aMaxMove, aMinMove);
		aTo = getPotentialDoubleChest(aTo);
		if (aSlotTo >= ((IInventory)aTo.mTileEntity).getSizeInventory()) return 0;
		
		for (int aSlotFrom : aSlotsFrom) {
			ItemStack aStackFrom = ((IInventory)aFrom.mTileEntity).getStackInSlot(aSlotFrom);
			if (aStackFrom == null || aStackFrom.stackSize < aMinMove || (aFilter != null && aFilter.contains(aStackFrom, T) == aInvertFilter) || !canTake((IInventory)aFrom.mTileEntity, aIgnoreSideFrom ? SIDE_ANY : aFrom.mSideOfTileEntity, aFrom.mSideOfTileEntity, aSlotFrom, aStackFrom)) continue;
			ItemStack aStackTo = ((IInventory)aTo.mTileEntity).getStackInSlot(aSlotTo);
			int tMovable = Math.min(aMaxMove, canPut((IInventory)aTo.mTileEntity, aIgnoreSideTo ? SIDE_ANY : aTo.mSideOfTileEntity, aTo.mSideOfTileEntity, aSlotTo, aStackFrom, aStackTo, Math.min(aMaxSize, aStackFrom.getMaxStackSize())));
			if (tMovable < aMinMove || tMovable + (aStackTo == null ? 0 : aStackTo.stackSize) < aMinSize) continue;
			// Actually Moving the Stack
			return move_((IInventory)aFrom.mTileEntity, (IInventory)aTo.mTileEntity, aStackFrom, aStackTo, aSlotFrom, aSlotTo, tMovable);
		}
		return 0;
	}
	
	@SuppressWarnings("rawtypes")
	public static int move(DelegatorTileEntity aFrom, DelegatorTileEntity aTo, int aSlotFrom, int aSlotTo) {return move(aFrom, aTo, aSlotFrom, aSlotTo, null, F, F, F, T, 64, 1, 64, 1);}
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static int move(DelegatorTileEntity aFrom, DelegatorTileEntity aTo, int aSlotFrom, int aSlotTo, ItemStackSet<ItemStackContainer> aFilter, boolean aIgnoreSideFrom, boolean aIgnoreSideTo, boolean aInvertFilter, boolean aEjectItems, int aMaxSize, int aMinSize, int aMaxMove, int aMinMove) {
		if (aSlotFrom < 0 || aSlotTo < 0) return 0;
		if (aFrom.mTileEntity instanceof IInventory) {
			aFrom = getPotentialDoubleChest(aFrom);
			if (aSlotFrom >= ((IInventory)aFrom.mTileEntity).getSizeInventory()) return 0;
			if (aTo.mTileEntity instanceof IInventory) {
				aTo = getPotentialDoubleChest(aTo);
				if (aSlotTo >= ((IInventory)aTo.mTileEntity).getSizeInventory()) return 0;
				ItemStack aStackFrom = ((IInventory)aFrom.mTileEntity).getStackInSlot(aSlotFrom);
				if (aStackFrom == null || aStackFrom.stackSize < aMinMove || (aFilter != null && aFilter.contains(aStackFrom, T) == aInvertFilter) || !canTake((IInventory)aFrom.mTileEntity, aIgnoreSideFrom ? SIDE_ANY : aFrom.mSideOfTileEntity, aFrom.mSideOfTileEntity, aSlotFrom, aStackFrom)) return 0;
				ItemStack aStackTo = ((IInventory)aTo.mTileEntity).getStackInSlot(aSlotTo);
				int tMovable = Math.min(aMaxMove, canPut((IInventory)aTo.mTileEntity, aIgnoreSideTo ? SIDE_ANY : aTo.mSideOfTileEntity, aTo.mSideOfTileEntity, aSlotTo, aStackFrom, aStackTo, Math.min(aMaxSize, aStackFrom.getMaxStackSize())));
				if (tMovable < aMinMove || tMovable + (aStackTo == null ? 0 : aStackTo.stackSize) < aMinSize) return 0;
				// Actually Moving the Stack
				return move_((IInventory)aFrom.mTileEntity, (IInventory)aTo.mTileEntity, aStackFrom, aStackTo, aSlotFrom, aSlotTo, tMovable);
			}
			// Maybe the Recipient is a Pipe or something that causes Auto-Trash.
			return put(aFrom, new int[] {aSlotFrom}, aTo, aFilter, aIgnoreSideFrom, aInvertFilter, aEjectItems, Math.min(aMaxSize, aMaxMove), Math.max(aMinSize, aMinMove));
		}
		return 0;
	}
	
	public static int move(IInventory aInventory, int aSlotFrom, int aSlotTo) {
		if (aSlotFrom == aSlotTo) return 0;
		ItemStack aStackFrom = aInventory.getStackInSlot(aSlotFrom), aStackTo = aInventory.getStackInSlot(aSlotTo);
		return aStackFrom != null && (aStackTo == null || equal_(aStackFrom, aStackTo, F)) ? move_(aInventory, aStackFrom, aStackTo, aSlotFrom, aSlotTo, Math.min(aStackFrom.stackSize, Math.min(aInventory.getInventoryStackLimit(), aStackTo == null ? aStackFrom.getMaxStackSize() : aStackTo.getMaxStackSize() - aStackTo.stackSize))) : 0;
	}
	public static int move(IInventory aInventory, int aSlotFrom, int aSlotTo, int aCount) {
		return move(aInventory, aInventory.getStackInSlot(aSlotFrom), aInventory.getStackInSlot(aSlotTo), aSlotFrom, aSlotTo, aCount);
	}
	public static int move(IInventory aInventory, ItemStack aStackFrom, ItemStack aStackTo, int aSlotFrom, int aSlotTo, int aCount) {
		return aStackFrom != null && (aStackTo == null || equal_(aStackFrom, aStackTo, F)) ? move_(aInventory, aStackFrom, aStackTo, aSlotFrom, aSlotTo, aCount) : 0;
	}
	public static int move_(IInventory aInventory, ItemStack aStackFrom, ItemStack aStackTo, int aSlotFrom, int aSlotTo, int aCount) {
		aCount = Math.min(aCount, aStackFrom.stackSize);
		if (aCount < 0) return 0;
		ItemStack tStack = aInventory.decrStackSize(aSlotFrom, aCount);
		if (tStack == null || tStack.stackSize <= 0) return 0;
		aCount = Math.min(aCount, tStack.stackSize);
		if (aStackTo == null) aInventory.setInventorySlotContents(aSlotTo, amount(aCount, aStackFrom)); else aStackTo.stackSize += aCount;
		aInventory.markDirty();
		WD.mark(aInventory);
		return aCount;
	}
	public static int move(IInventory aFrom, IInventory aTo, int aSlotFrom, int aSlotTo) {
		ItemStack aStackFrom = aFrom.getStackInSlot(aSlotFrom), aStackTo = aTo.getStackInSlot(aSlotTo);
		return aStackFrom != null && (aStackTo == null || equal_(aStackFrom, aStackTo, F)) ? move_(aFrom, aTo, aStackFrom, aStackTo, aSlotFrom, aSlotTo, Math.min(aStackFrom.stackSize, Math.min(aTo.getInventoryStackLimit(), aStackTo == null ? aStackFrom.getMaxStackSize() : aStackTo.getMaxStackSize() - aStackTo.stackSize))) : 0;
	}
	public static int move(IInventory aFrom, IInventory aTo, int aSlotFrom, int aSlotTo, int aCount) {
		return move(aFrom, aTo, aFrom.getStackInSlot(aSlotFrom), aTo.getStackInSlot(aSlotTo), aSlotFrom, aSlotTo, aCount);
	}
	public static int move(IInventory aFrom, IInventory aTo, ItemStack aStackFrom, ItemStack aStackTo, int aSlotFrom, int aSlotTo, int aCount) {
		return aStackFrom != null && (aStackTo == null || equal_(aStackFrom, aStackTo, F)) ? move_(aFrom, aTo, aStackFrom, aStackTo, aSlotFrom, aSlotTo, aCount) : 0;
	}
	public static int move_(IInventory aFrom, IInventory aTo, ItemStack aStackFrom, ItemStack aStackTo, int aSlotFrom, int aSlotTo, int aCount) {
		if (aStackFrom == aStackTo) return 0;
		if (aFrom == aTo && aSlotFrom == aSlotTo) return 0;
		aCount = Math.min(aCount, aStackFrom.stackSize);
		if (aCount < 0) return 0;
		ItemStack tStack = aFrom.decrStackSize(aSlotFrom, aCount);
		if (tStack == null || tStack.stackSize <= 0) return 0;
		aCount = Math.min(aCount, tStack.stackSize);
		if (aStackTo == null) aTo.setInventorySlotContents(aSlotTo, amount(aCount, aStackFrom)); else aStackTo.stackSize += aCount;
		aFrom.markDirty();
		aTo  .markDirty();
		WD.mark(aFrom);
		WD.mark(aTo);
		return aCount;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static DelegatorTileEntity getPotentialDoubleChest(DelegatorTileEntity aPotentialChest) {
		if (aPotentialChest.mTileEntity instanceof TileEntityChest) {
			Block aChestBlock = aPotentialChest.getBlock();
			if (aPotentialChest.getBlockAtSide(SIDE_X_NEG) == aChestBlock) {
				TileEntity tAdjacentChest = aPotentialChest.getTileEntityAtSideAndDistance(SIDE_X_NEG, 1);
				if (tAdjacentChest instanceof TileEntityChest) return new DelegatorTileEntity(new InventoryLargeChest("fucking mojang hax", (IInventory)tAdjacentChest, (IInventory)aPotentialChest.mTileEntity), aPotentialChest);
			}
			if (aPotentialChest.getBlockAtSide(SIDE_X_POS) == aChestBlock) {
				TileEntity tAdjacentChest = aPotentialChest.getTileEntityAtSideAndDistance(SIDE_X_POS, 1);
				if (tAdjacentChest instanceof TileEntityChest) return new DelegatorTileEntity(new InventoryLargeChest("fucking mojang hax", (IInventory)aPotentialChest.mTileEntity, (IInventory)tAdjacentChest), aPotentialChest);
			}
			if (aPotentialChest.getBlockAtSide(SIDE_Z_NEG) == aChestBlock) {
				TileEntity tAdjacentChest = aPotentialChest.getTileEntityAtSideAndDistance(SIDE_Z_NEG, 1);
				if (tAdjacentChest instanceof TileEntityChest) return new DelegatorTileEntity(new InventoryLargeChest("fucking mojang hax", (IInventory)tAdjacentChest, (IInventory)aPotentialChest.mTileEntity), aPotentialChest);
			}
			if (aPotentialChest.getBlockAtSide(SIDE_Z_POS) == aChestBlock) {
				TileEntity tAdjacentChest = aPotentialChest.getTileEntityAtSideAndDistance(SIDE_Z_POS, 1);
				if (tAdjacentChest instanceof TileEntityChest) return new DelegatorTileEntity(new InventoryLargeChest("fucking mojang hax", (IInventory)aPotentialChest.mTileEntity, (IInventory)tAdjacentChest), aPotentialChest);
			}
		}
		return aPotentialChest;
	}
	
	public static boolean canConnect(@SuppressWarnings("rawtypes") DelegatorTileEntity aDelegator) {
		if (aDelegator.mTileEntity == null) return F;
		if (TE_PIPES && aDelegator.mTileEntity instanceof cofh.api.transport.IItemDuct) return T;
		if (BC_PIPES && aDelegator.mTileEntity instanceof buildcraft.api.transport.IInjectable) return ((buildcraft.api.transport.IInjectable)aDelegator.mTileEntity).canInjectItems(aDelegator.getForgeSideOfTileEntity());
		if (aDelegator.mTileEntity instanceof ITileEntityCanDelegate && ((ITileEntityCanDelegate)aDelegator.mTileEntity).isExtender(aDelegator.mSideOfTileEntity)) return T;
		if (aDelegator.mTileEntity instanceof IInventory && ((IInventory)aDelegator.mTileEntity).getSizeInventory() > 0) return T;
		return F;
	}
	
	@Deprecated public static boolean canTake (IInventory      aFrom, byte aSideFrom, int aSlotFrom, ItemStack aStackFrom) {return canTake(aFrom, aSideFrom, aSideFrom, aSlotFrom, aStackFrom);}
	@Deprecated public static boolean canTake_(ISidedInventory aFrom, byte aSideFrom, int aSlotFrom, ItemStack aStackFrom) {return canTake(aFrom, aSideFrom, aSideFrom, aSlotFrom, aStackFrom);}
	
	public static boolean canTake(IInventory aFrom, byte aSideFrom, byte aSideFallbackFrom, int aSlotFrom, ItemStack aStackFrom) {
		if (aFrom instanceof ISidedInventory) {
			if (SIDES_VALID[aSideFrom]) return ((ISidedInventory)aFrom).canExtractItem(aSlotFrom, aStackFrom, aSideFrom);
			for (byte tSideFrom : ALL_SIDES_VALID) if (((ISidedInventory)aFrom).canExtractItem(aSlotFrom, aStackFrom, tSideFrom)) {
				// Important fallback to prevent crashes with things coded like my Inventory Extenders!
				((ISidedInventory)aFrom).canExtractItem(aSlotFrom, aStackFrom, aSideFallbackFrom);
				return T;
			}
			return F;
		}
		return T;
	}
	
	@Deprecated public static int canPut(IInventory aTo, byte aSideTo, int aSlotTo, ItemStack aStackFrom) {return canPut(aTo, aSideTo, aSlotTo, aStackFrom, aStackFrom.getMaxStackSize());}
	@Deprecated public static int canPut(IInventory aTo, byte aSideTo, int aSlotTo, ItemStack aStackFrom, int aMaxSize) {return canPut(aTo, aSideTo, aSlotTo, aStackFrom, aTo.getStackInSlot(aSlotTo));}
	@Deprecated public static int canPut(IInventory aTo, byte aSideTo, int aSlotTo, ItemStack aStackFrom, ItemStack aStackTo) {return canPut(aTo, aSideTo, aSlotTo, aStackFrom, aStackTo, aStackFrom.getMaxStackSize());}
	@Deprecated public static int canPut(IInventory aTo, byte aSideTo, int aSlotTo, ItemStack aStackFrom, ItemStack aStackTo, int aMaxSize) {return canPut(aTo, aSideTo, aSideTo, aSlotTo, aStackFrom, aStackTo, aMaxSize);}
	
	public static int canPut(IInventory aTo, byte aSideTo, byte aSideFallbackTo, int aSlotTo, ItemStack aStackFrom) {return canPut(aTo, aSideTo, aSideFallbackTo, aSlotTo, aStackFrom, aStackFrom.getMaxStackSize());}
	public static int canPut(IInventory aTo, byte aSideTo, byte aSideFallbackTo, int aSlotTo, ItemStack aStackFrom, int aMaxSize) {return canPut(aTo, aSideTo, aSideFallbackTo, aSlotTo, aStackFrom, aTo.getStackInSlot(aSlotTo));}
	public static int canPut(IInventory aTo, byte aSideTo, byte aSideFallbackTo, int aSlotTo, ItemStack aStackFrom, ItemStack aStackTo) {return canPut(aTo, aSideTo, aSideFallbackTo, aSlotTo, aStackFrom, aStackTo, aStackFrom.getMaxStackSize());}
	public static int canPut(IInventory aTo, byte aSideTo, byte aSideFallbackTo, int aSlotTo, ItemStack aStackFrom, ItemStack aStackTo, int aMaxSize) {
		int rMaxMove = (aStackTo == null ? Math.min(aMaxSize, aTo.getInventoryStackLimit()) : equal_(aStackTo, aStackFrom, F) ? Math.min(aMaxSize, aTo.getInventoryStackLimit()) - aStackTo.stackSize : 0);
		if (rMaxMove <= 0 || !aTo.isItemValidForSlot(aSlotTo, aStackFrom)) return 0;
		if (!(aTo instanceof ISidedInventory)) return rMaxMove;
		if (SIDES_VALID[aSideTo]) return ((ISidedInventory)aTo).canInsertItem(aSlotTo, aStackFrom, aSideTo) ? rMaxMove : 0;
		for (byte tSideTo : ALL_SIDES_VALID) if (((ISidedInventory)aTo).canInsertItem(aSlotTo, aStackFrom, tSideTo)) {
			// Important fallback to prevent crashes with things coded like my Inventory Extenders!
			((ISidedInventory)aTo).canInsertItem(aSlotTo, aStackFrom, aSideFallbackTo);
			return rMaxMove;
		}
		return 0;
	}
	
	
	public static int put(DelegatorTileEntity<IInventory> aFrom, int[] aSlotsFrom, @SuppressWarnings("rawtypes") DelegatorTileEntity aTo, ItemStackSet<ItemStackContainer> aFilter, boolean aIgnoreSideFrom, boolean aInvertFilter, boolean aEjectItems, int aMaxMove, int aMinMove) {
		if (aTo.mTileEntity != null) {
			if (TE_PIPES && aTo.mTileEntity instanceof cofh.api.transport.IItemDuct) {
				for (int aSlotFrom : aSlotsFrom) {
					ItemStack aStackFrom = aFrom.mTileEntity.getStackInSlot(aSlotFrom);
					if (aStackFrom != null && aMinMove <= aStackFrom.stackSize && (aFilter == null || aFilter.contains(aStackFrom, T) != aInvertFilter) && canTake(aFrom.mTileEntity, aIgnoreSideFrom ? SIDE_ANY : aFrom.mSideOfTileEntity, aFrom.mSideOfTileEntity, aSlotFrom, aStackFrom)) {
						// Actually Moving the Stack
						ItemStack tStackMoved = amount(Math.min(aStackFrom.stackSize, aMaxMove), aStackFrom);
						ItemStack rStackMoved = ((cofh.api.transport.IItemDuct)aTo.mTileEntity).insertItem(aTo.getForgeSideOfTileEntity(), copy(tStackMoved));
						int rMoved = (tStackMoved.stackSize - (rStackMoved == null ? 0 : rStackMoved.stackSize));
						if (rMoved > 0) {
							aFrom.mTileEntity.decrStackSize(aSlotFrom, rMoved);
							aFrom.mTileEntity.markDirty();
							WD.mark(aFrom);
							WD.mark(aTo);
							return rMoved;
						}
					}
				}
				return 0;
			}
			if (BC_PIPES && aTo.mTileEntity instanceof buildcraft.api.transport.IInjectable) {
				for (int aSlotFrom : aSlotsFrom) {
					ItemStack aStackFrom = aFrom.mTileEntity.getStackInSlot(aSlotFrom);
					if (aStackFrom != null && aMinMove <= aStackFrom.stackSize && (aFilter == null || aFilter.contains(aStackFrom, T) != aInvertFilter) && canTake(aFrom.mTileEntity, aIgnoreSideFrom ? SIDE_ANY : aFrom.mSideOfTileEntity, aFrom.mSideOfTileEntity, aSlotFrom, aStackFrom)) {
						// Actually Moving the Stack
						ItemStack tStackMoved = amount(Math.min(aStackFrom.stackSize, aMaxMove), aStackFrom);
						int rMoved = ((buildcraft.api.transport.IInjectable)aTo.mTileEntity).injectItem(copy(tStackMoved), F, aTo.getForgeSideOfTileEntity(), null);
						if (rMoved >= aMinMove) {
							rMoved = (((buildcraft.api.transport.IInjectable)aTo.mTileEntity).injectItem(amount(rMoved, tStackMoved), T, aTo.getForgeSideOfTileEntity(), null));
							aFrom.mTileEntity.decrStackSize(aSlotFrom, rMoved);
							aFrom.mTileEntity.markDirty();
							WD.mark(aFrom);
							WD.mark(aTo);
							return rMoved;
						}
					}
				}
				return 0;
			}
		}
		
		Block aBlock = aTo.getBlock();
		if (aBlock instanceof BlockRailBase) {
			// Do not eject shit onto Rails directly.
		} else if (aBlock.getMaterial() == Material.lava || aBlock instanceof BlockFire || (invalid(aBlock) && aTo.mY < 1)) {
			for (int aSlotFrom : aSlotsFrom) {
				ItemStack aStackFrom = aFrom.mTileEntity.getStackInSlot(aSlotFrom);
				if (aStackFrom != null && aMinMove <= aStackFrom.stackSize && (aFilter == null || aFilter.contains(aStackFrom, T) != aInvertFilter) && canTake(aFrom.mTileEntity, aIgnoreSideFrom ? SIDE_ANY : aFrom.mSideOfTileEntity, aFrom.mSideOfTileEntity, aSlotFrom, aStackFrom)) {
					// Actually Moving the Stack
					int rMoved = GarbageGT.trash(amount(Math.min(aStackFrom.stackSize, aMaxMove), aStackFrom));
					aFrom.mTileEntity.decrStackSize(aSlotFrom, rMoved);
					aFrom.mTileEntity.markDirty();
					WD.mark(aFrom);
					return rMoved;
				}
			}
		} else if (!WD.hasCollide(aTo.mWorld, aTo.mX, aTo.mY, aTo.mZ, aBlock)) {
			if (aEjectItems) for (int aSlotFrom : aSlotsFrom) {
				ItemStack aStackFrom = aFrom.mTileEntity.getStackInSlot(aSlotFrom);
				if (aStackFrom != null && aMinMove <= aStackFrom.stackSize && (aFilter == null || aFilter.contains(aStackFrom, T) != aInvertFilter) && canTake(aFrom.mTileEntity, aIgnoreSideFrom ? SIDE_ANY : aFrom.mSideOfTileEntity, aFrom.mSideOfTileEntity, aSlotFrom, aStackFrom)) {
					// Actually Moving the Stack
					ItemStack tStack = amount(Math.min(aStackFrom.stackSize, aMaxMove), aStackFrom);
					place(aTo.mWorld, aTo.mX+0.5, aTo.mY+0.5, aTo.mZ+0.5, tStack);
					aFrom.mTileEntity.decrStackSize(aSlotFrom, tStack.stackSize);
					aFrom.mTileEntity.markDirty();
					WD.mark(aFrom);
					return tStack.stackSize;
				}
			}
		}
		return 0;
	}
	
	public static ItemStack emptySlot() {
		return IL.Empty_Slot.get(0);
	}
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
	
	public static boolean instaharvest(Block aBlock) {
		return torch(aBlock) || BlocksGT.instaharvest.contains(aBlock);
	}
	public static boolean instaharvest(Block aBlock, long aMeta) {
		return torch(aBlock, aMeta) || BlocksGT.instaharvest.contains(aBlock);
	}
	
	public static boolean torch(Block aBlock) {
		return torch(aBlock, 1); // that "1" is totally not hacky at all. XD
	}
	public static boolean torch(Block aBlock, long aMeta) {
		if (IL.TFC_Torch.equal(aBlock) || IL.NePl_Torch.equal(aBlock) || IL.GC_Torch_Glowstone.equal(aBlock) || IL.AETHER_Torch_Ambrosium.equal(aBlock) || (aMeta == 1 && IL.TC_Block_Air.equal(aBlock))) return T;
		return aBlock instanceof BlockTorch && !(aBlock instanceof BlockRedstoneTorch);
	}
	public static boolean torch(ItemStack aStack) {
		return IL.TC_Nitor.equal(aStack, F, T) || torch(block(aStack), meta(aStack));
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
		// Decrease Durability by 1 for these Items.
		if (ItemsGT.CONTAINER_DURABILITY.contains(aStack, T)) return copyMeta(meta_(aStack) + 1, aStack);
		// Use normal Container Item Mechanics.
		if (item_(aStack).hasContainerItem(aStack)) return copy(item_(aStack).getContainerItem(aStack));
		// These are all special Cases, in which it is intended to have only GT Blocks outputting those Container Items.
		if (IL.Cell_Empty.exists()) {
			if (IL.Cell_Empty.equal(aStack, F, T)) return NI;
			if (IL.Cell_Empty.equal(aStack, T, T)) return IL.Cell_Empty.get(1);
		}
		if (IL.SC2_Teapot_Empty.exists()) {
			if (IL.SC2_Teapot_Empty.equal(aStack, F, T)) return NI;
			if (IL.SC2_Teapot_Empty.equal(aStack, T, T)) return IL.SC2_Teapot_Empty.get(1);
		}
		if (IL.SC2_Teacup_Empty.exists()) {
			if (IL.SC2_Teacup_Empty.equal(aStack, F, T)) return NI;
			if (IL.SC2_Teacup_Empty.equal(aStack, T, T)) return IL.SC2_Teacup_Empty.get(1);
		}
		if (aCheckIFluidContainerItems && item_(aStack) instanceof IFluidContainerItem && ((IFluidContainerItem)item_(aStack)).getCapacity(aStack) > 0) {
			ItemStack tStack = amount(1, aStack);
			((IFluidContainerItem)item_(aStack)).drain(tStack, Integer.MAX_VALUE, T);
			if (tStack.stackSize <= 0) return NI;
			if (tStack.getTagCompound() == null) return tStack;
			if (tStack.getTagCompound().hasNoTags()) tStack.setTagCompound(null);
			return tStack;
		}
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
		if (item_(aStack) instanceof ItemFood) {try {return ((ItemFood)item_(aStack)).func_150905_g(aStack);} catch(Throwable e) {return 1;}}
		if (item_(aStack) instanceof MultiItemRandom) {
			IFoodStat tStat = ((MultiItemRandom)item_(aStack)).mFoodStats.get(meta_(aStack));
			return tStat == null ? 0 : tStat.getFoodLevel(item_(aStack), aStack, null);
		}
		return 0;
	}
	public static float saturation(ItemStack aStack) {
		if (invalid(aStack)) return 0;
		if (item_(aStack) instanceof ItemFood) {try {return ((ItemFood)item_(aStack)).func_150906_h(aStack);} catch(Throwable e) {return 0.5F;}}
		if (item_(aStack) instanceof MultiItemRandom) {
			IFoodStat tStat = ((MultiItemRandom)item_(aStack)).mFoodStats.get(meta_(aStack));
			return tStat == null ? 0 : tStat.getSaturation(item_(aStack), aStack, null);
		}
		return 0;
	}
	public static float hydration(ItemStack aStack) {
		if (invalid(aStack)) return 0;
		if (item_(aStack) instanceof MultiItemRandom) {
			IFoodStat tStat = ((MultiItemRandom)item_(aStack)).mFoodStats.get(meta_(aStack));
			return tStat == null ? 0 : tStat.getHydration(item_(aStack), aStack, null);
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
		if (tItem instanceof ItemTool  && ((ItemTool )tItem).getToolMaterialName().equals("WOOD")) return 200;
		if (tItem instanceof ItemSword && ((ItemSword)tItem).getToolMaterialName().equals("WOOD")) return 200;
		if (tItem instanceof ItemHoe   && ((ItemHoe  )tItem).getToolMaterialName().equals("WOOD")) return 200;
		if (tItem == Items.stick) return 100;
		if (tItem == Items.coal) return 1600;
		if (tItem == Items.blaze_rod) return 2400;
		if (tItem == Items.lava_bucket) return 20000;
		Block tBlock = ST.block_(tItem);
		if (tBlock == Blocks.sapling) return 100;
		if (tBlock == Blocks.wooden_slab) return 150;
		if (tBlock == Blocks.coal_block) return 16000;
		if (tBlock.getMaterial() == Material.wood) return 300;
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
		for (int i = 0; i < 16; i++) hide(aItem, i);
		hide(aItem, W);
	}
	public static void hide(Item aItem, long aMeta) {
		hide(make(aItem, 1, aMeta));
	}
	public static void hide(Block aBlock) {
		for (int i = 0; i < 16; i++) hide(aBlock, i);
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
		return aNBT == null ? null : load(aNBT.getCompoundTag(aTagName), NI);
	}
	/** Loads an ItemStack properly. */
	public static ItemStack load(NBTTagCompound aNBT, String aTagName, ItemStack aDefault) {
		return aNBT == null ? null : load(aNBT.getCompoundTag(aTagName), aDefault);
	}
	
	/** Loads an ItemStack properly. */
	public static ItemStack load(NBTTagCompound aNBT) {
		return load(aNBT, NI);
	}
	/** Loads an ItemStack properly. */
	public static ItemStack load(NBTTagCompound aNBT, ItemStack aDefault) {
		if (aNBT == null || aNBT.hasNoTags()) return null;
		ItemStack rStack = make(Item.getItemById(aNBT.getShort("id")), aNBT.getInteger("Count"), aNBT.getShort("Damage"));
		if (rStack == null) if (aNBT.hasKey("od")) {
			rStack = OreDictManager.INSTANCE.getStack(aNBT.getString("od"), aNBT.getInteger("Count"));
			if (rStack == null) return aDefault == null ? null : update_(OM.get_(aDefault));
		} else return aDefault == null ? null : update_(OM.get_(aDefault));
		// Has to use setTagCompound instead of putting it into make()
		// because it would delete certain Tags on load, making stuff like unscanned Forestry Bees unstackable.
		// But update_() will still delete a completely empty NBT later on.
		rStack.setTagCompound(aNBT.hasKey("tag", 10)?aNBT.getCompoundTag("tag"):null);
		// Does anyone even migrate IC2exp Items anymore? This is only used when updating from IC2-Non-Exp to IC2-Exp.
	//  if (item_(rStack).getClass().getName().startsWith("ic2.core.migration")) item_(rStack).onUpdate(rStack, DW, null, 0, F); // I do not think this could possibly happen anymore
		return update_(OM.get_(rStack));
	}

	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(String aTagName, Block aBlock) {
		NBTTagCompound aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(ST.make(aBlock, 1, 0));
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(String aTagName, Block aBlock, long aStackSize) {
		NBTTagCompound aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(ST.make(aBlock, aStackSize, 0));
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(String aTagName, Block aBlock, long aStackSize, long aMeta) {
		NBTTagCompound aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(ST.make(aBlock, aStackSize, aMeta));
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(String aTagName, Item aItem) {
		NBTTagCompound aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(ST.make(aItem, 1, 0));
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(String aTagName, Item aItem, long aStackSize) {
		NBTTagCompound aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(ST.make(aItem, aStackSize, 0));
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(String aTagName, Item aItem, long aStackSize, long aMeta) {
		NBTTagCompound aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(ST.make(aItem, aStackSize, aMeta));
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(String aTagName, ItemStack aStack) {
		NBTTagCompound aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(aStack);
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(NBTTagCompound aNBT, String aTagName, Block aBlock) {
		if (aNBT == null) aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(ST.make(aBlock, 1, 0));
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(NBTTagCompound aNBT, String aTagName, Block aBlock, long aStackSize) {
		if (aNBT == null) aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(ST.make(aBlock, aStackSize, 0));
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(NBTTagCompound aNBT, String aTagName, Block aBlock, long aStackSize, long aMeta) {
		if (aNBT == null) aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(ST.make(aBlock, aStackSize, aMeta));
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(NBTTagCompound aNBT, String aTagName, Item aItem) {
		if (aNBT == null) aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(ST.make(aItem, 1, 0));
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(NBTTagCompound aNBT, String aTagName, Item aItem, long aStackSize) {
		if (aNBT == null) aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(ST.make(aItem, aStackSize, 0));
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(NBTTagCompound aNBT, String aTagName, Item aItem, long aStackSize, long aMeta) {
		if (aNBT == null) aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(ST.make(aItem, aStackSize, aMeta));
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(NBTTagCompound aNBT, String aTagName, ItemStack aStack) {
		if (aNBT == null) aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(aStack);
		if (tNBT == null) aNBT.removeTag(aTagName); else aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	/** Saves an ItemStack properly. */
	public static NBTTagCompound save(ItemStack aStack) {
		if (aStack == null || item_(aStack) == null || aStack.stackSize < 0) return null;
		NBTTagCompound rNBT = UT.NBT.make();
		aStack = OM.get_(aStack);
		rNBT.setShort("id", id(aStack));
		UT.NBT.setNumber(rNBT, "Count", aStack.stackSize);
		UT.NBT.setNumber(rNBT, "Damage", meta_(aStack));
		if (aStack.hasTagCompound()) rNBT.setTag("tag", aStack.getTagCompound());
		OreDictItemData tData = OM.anyassociation_(aStack);
		if (tData != null) rNBT.setString("od", tData.toString());
		return rNBT;
	}
}
