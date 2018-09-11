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
import gregapi.code.ItemStackContainer;
import gregapi.code.ModData;
import gregapi.data.CS;
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
	
	public static ItemStack update(ItemStack aStack) {
		return invalid(aStack)?aStack:update_(aStack);
	}
	public static ItemStack update_(ItemStack aStack) {
		if (aStack.hasTagCompound() && aStack.getTagCompound().hasNoTags()) aStack.setTagCompound(null);
		if (aStack.getItem() instanceof IItemUpdatable) ((IItemUpdatable)aStack.getItem()).updateItemStack(aStack);
		return aStack;
	}
	public static ItemStack update(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {
		return invalid(aStack)?aStack:update_(aStack, aWorld, aX, aY, aZ);
	}
	public static ItemStack update_(ItemStack aStack, World aWorld, int aX, int aY, int aZ) {
		if (aStack.hasTagCompound() && aStack.getTagCompound().hasNoTags()) aStack.setTagCompound(null);
		if (aStack.getItem() instanceof IItemUpdatable) ((IItemUpdatable)aStack.getItem()).updateItemStack(aStack);
		return aStack;
	}
	public static ItemStack update(ItemStack aStack, Entity aEntity) {
		return update(aStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
	}
	public static ItemStack update_(ItemStack aStack, Entity aEntity) {
		return update_(aStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ));
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
	
	public static ItemStack set(Object aSetStack, Object aToStack) {
		return set(aSetStack, aToStack, T, T);
	}
	
	public static ItemStack set(Object aSetStack, Object aToStack, boolean aCheckStacksize, boolean aCheckNBT) {
		if (invalid(aSetStack) || invalid(aToStack)) return null;
		((ItemStack)aSetStack).func_150996_a(((ItemStack)aToStack).getItem());
		if (aCheckStacksize) ((ItemStack)aSetStack).stackSize = ((ItemStack)aToStack).stackSize;
		meta((ItemStack)aSetStack, meta((ItemStack)aToStack));
		if (aCheckNBT) ((ItemStack)aSetStack).setTagCompound(((ItemStack)aToStack).getTagCompound());
		return (ItemStack)aSetStack;
	}
	
	public static ItemStack container(ItemStack aStack, boolean aCheckIFluidContainerItems) {
		if (invalid(aStack)) return NI;
		if (aStack.getItem().hasContainerItem(aStack)) return copy(aStack.getItem().getContainerItem(aStack));
		/** These are all special Cases, in which it is intended to have only GT Blocks outputting those Container Items */
		if (IL.Cell_Empty.equal(aStack, F, T)) return NI;
		
		if (aCheckIFluidContainerItems && aStack.getItem() instanceof IFluidContainerItem && ((IFluidContainerItem)aStack.getItem()).getCapacity(aStack) > 0) {
			ItemStack tStack = amount(1, aStack);
			((IFluidContainerItem)aStack.getItem()).drain(tStack, Integer.MAX_VALUE, T);
			if (tStack.stackSize <= 0) return NI;
			if (tStack.getTagCompound() == null) return tStack;
			if (tStack.getTagCompound().hasNoTags()) tStack.setTagCompound(null);
			return tStack;
		}
		
		if (IL.IC2_ForgeHammer.equal(aStack, T, T) || IL.IC2_WireCutter.equal(aStack, T, T)) return copyMeta(meta(aStack) + 1, aStack);
		return NI;
	}
	
	public static ItemStack container(ItemStack aStack, boolean aCheckIFluidContainerItems, int aStacksize) {
		return amount(aStacksize, container(aStack, aCheckIFluidContainerItems));
	}
	
	public static boolean equal(ItemStack aStack1, ItemStack aStack2) {
		return equal(aStack1, aStack2, F);
	}
	
	public static boolean equalTools(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {
		return aStack1 != null && aStack2 != null && equalTools_(aStack1, aStack2, aIgnoreNBT);
	}
	
	public static boolean equalTools_(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {
		return aStack1.getItem() == aStack2.getItem() && (aIgnoreNBT || aStack1.getItem() instanceof IItemGTContainerTool || ((aStack1.getTagCompound() == null) == (aStack2.getTagCompound() == null)) && (aStack1.getTagCompound() == null || aStack1.getTagCompound().equals(aStack2.getTagCompound()))) && (meta(aStack1) == meta(aStack2) || meta(aStack1) == CS.W || meta(aStack2) == CS.W);
	}
	
	public static boolean equal(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {
		return aStack1 != null && aStack2 != null && equal_(aStack1, aStack2, aIgnoreNBT);
	}
	
	public static boolean equal_(ItemStack aStack1, ItemStack aStack2, boolean aIgnoreNBT) {
		return aStack1.getItem() == aStack2.getItem() && (aIgnoreNBT || ((aStack1.getTagCompound() == null) == (aStack2.getTagCompound() == null)) && (aStack1.getTagCompound() == null || aStack1.getTagCompound().equals(aStack2.getTagCompound()))) && (meta(aStack1) == meta(aStack2) || meta(aStack1) == CS.W || meta(aStack2) == CS.W);
	}
	
	public static short id(Item aItem) {
		return aItem==null?0:(short)Item.getIdFromItem(aItem);
	}
	
	public static short id(ItemStack aStack) {
		return aStack==null?0:id(aStack.getItem());
	}
	
	public static Item item(ItemStack aStack) {
		return aStack==null?null:aStack.getItem();
	}
	
	public static short meta(ItemStack aStack) {
		return (short)Items.feather.getDamage(aStack);
	}
	
	public static ItemStack meta(ItemStack aStack, long aMeta) {
		Items.feather.setDamage(aStack, (short)aMeta);
		return aStack;
	}
	
	public static boolean rotten(ItemStack aStack) {
		if (invalid(aStack)) return F;
		if (aStack.getItem() instanceof MultiItemRandom) {
			IFoodStat tStat = ((MultiItemRandom)aStack.getItem()).mFoodStats.get(meta(aStack));
			return tStat != null && tStat.isRotten(aStack.getItem(), aStack, null);
		}
		return aStack.getItem() == Items.rotten_flesh || OM.materialcontained(aStack, MT.MeatRotten, MT.FishRotten);
	}
	
	public static int food(ItemStack aStack) {
		if (invalid(aStack)) return 0;
		if (aStack.getItem() instanceof ItemFood) {
			try {
				return ((ItemFood)aStack.getItem()).func_150905_g(aStack);
			} catch(Throwable e) {
				return 1;
			}
		}
		if (aStack.getItem() instanceof MultiItemRandom) {
			IFoodStat tStat = ((MultiItemRandom)aStack.getItem()).mFoodStats.get(meta(aStack));
			return tStat == null ? 0 : tStat.getFoodLevel(aStack.getItem(), aStack, null);
		}
		return 0;
	}
	
	public static float saturation(ItemStack aStack) {
		if (invalid(aStack)) return 0;
		if (aStack.getItem() instanceof ItemFood) {
			try {
				return ((ItemFood)aStack.getItem()).func_150906_h(aStack);
			} catch(Throwable e) {
				return 0.5F;
			}
		}
		if (aStack.getItem() instanceof MultiItemRandom) {
			IFoodStat tStat = ((MultiItemRandom)aStack.getItem()).mFoodStats.get(meta(aStack));
			return tStat == null ? 0 : tStat.getSaturation(aStack.getItem(), aStack, null);
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
		Item tItem = aStack.getItem();
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
	
	public static Item item(ModData aModID, String aItem) {return item(make(aModID, aItem, 1, null));}
	public static Item item(ModData aModID, String aItem, Item aReplacement) {Item rItem = item(aModID, aItem); return rItem == null ? aReplacement : rItem;}
	
	public static Block block(ModData aModID, String aBlock) {return block(make(aModID, aBlock, 1, null));}
	public static Block block(ModData aModID, String aBlock, Block aReplacement) {Block rBlock = block(aModID, aBlock); return rBlock == NB ? aReplacement : rBlock;}
	
	private static final Map<String, ItemStack> sIC2ItemMap = new HashMap<>();
	public static ItemStack mkic(String aItem, long aAmount, ItemStack aReplacement) {if (UT.Code.stringInvalid(aItem) || !GAPI_POST.mStartedPreInit) return null; if (!sIC2ItemMap.containsKey(aItem)) try {ItemStack tStack = IC2Items.getItem(aItem); sIC2ItemMap.put(aItem, tStack); if (tStack == null && D1 && MD.IC2.mLoaded) ERR.println(aItem + " is not found in the IC2 Items!");} catch (Throwable e) {/*Do nothing*/} return amount(aAmount, sIC2ItemMap.get(aItem), aReplacement);}
	public static ItemStack mkic(String aItem, long aAmount, long aMeta, ItemStack aReplacement) {ItemStack rStack = mkic(aItem, aAmount, aReplacement); if (rStack == null) return null; meta(rStack, aMeta); return rStack;}
	public static ItemStack mkic(String aItem, long aAmount, long aMeta) {return mkic(aItem, aAmount, aMeta, null);}
	public static ItemStack mkic(String aItem, long aAmount) {return mkic(aItem, aAmount, null);}
	public static ItemStack make(ModData aModID, String aItem, long aAmount) {return make(aModID, aItem, aAmount, null);}
	public static ItemStack make(ModData aModID, String aItem, long aAmount, ItemStack aReplacement) {if (!aModID.mLoaded || UT.Code.stringInvalid(aItem) || !GAPI_POST.mStartedPreInit) return aReplacement; if (aItem.length()>5&&aItem.charAt(0)=='t'&&aItem.charAt(1)=='i'&&aItem.charAt(2)=='l'&&aItem.charAt(3)=='e'&&aItem.charAt(4)=='.') return amount(aAmount, GameRegistry.findItemStack(aModID.mID, aItem, (int)aAmount), GameRegistry.findItemStack(aModID.mID, aItem.substring(5), (int)aAmount), aReplacement); return amount(aAmount, GameRegistry.findItemStack(aModID.mID, aItem, (int)aAmount), aReplacement);}
	public static ItemStack make(ModData aModID, String aItem, long aAmount, long aMeta) {ItemStack rStack = make(aModID, aItem, aAmount); if (rStack == null) return null; meta(rStack, aMeta); return rStack;}
	public static ItemStack make(ModData aModID, String aItem, long aAmount, long aMeta, ItemStack aReplacement) {ItemStack rStack = make(aModID, aItem, aAmount, aReplacement); if (rStack == null) return null; meta(rStack, aMeta); return rStack;}
	public static ItemStack make(long aItemID, long aStacksize, long aMetaData) {return aItemID==0?null:make(Item.getItemById((int)aItemID), aStacksize, aMetaData);}
	public static ItemStack make(Item aItem, long aStacksize, long aMetaData) {return aItem == null ? null : make(new ItemStack(aItem, UT.Code.bindInt(aStacksize), (int)aMetaData), null);}
	public static ItemStack make(Block aBlock, long aStacksize, long aMetaData) {return aBlock == null || aBlock == NB ? null : make(new ItemStack(aBlock, UT.Code.bindInt(aStacksize), (int)aMetaData), null);}
//	public static ItemStack make(IBlock aBlock, long aStacksize, long aMetaData) {return aBlock == null ? null : make(new ItemStack(aBlock.getBlock(), UT.Code.bindInt(aStacksize), (int)aMetaData), null);}
	public static ItemStack make(long aItemID, long aStacksize, long aMetaData, NBTTagCompound aNBT) {return aItemID==0?null:make(Item.getItemById((int)aItemID), aStacksize, aMetaData, aNBT);}
	public static ItemStack make(Item aItem, long aStacksize, long aMetaData, NBTTagCompound aNBT) {return aItem == null ? null : make(new ItemStack(aItem, UT.Code.bindInt(aStacksize), (int)aMetaData), aNBT);}
	public static ItemStack make(Block aBlock, long aStacksize, long aMetaData, NBTTagCompound aNBT) {return aBlock == null || aBlock == NB ? null : make(new ItemStack(aBlock, UT.Code.bindInt(aStacksize), (int)aMetaData), aNBT);}
//	public static ItemStack make(IBlock aBlock, long aStacksize, long aMetaData, NBTTagCompound aNBT) {return aBlock == null ? null : make(new ItemStack(aBlock.getBlock(), UT.Code.bindInt(aStacksize), (int)aMetaData), aNBT);}
	public static ItemStack make(ItemStack aStack, NBTTagCompound aNBT) {return make(aStack, null, aNBT);}
	public static ItemStack make(ItemStackContainer aStack, NBTTagCompound aNBT) {return make(aStack, null, aNBT);}
	public static ItemStack make(long aItemID, long aStacksize, long aMetaData, String aName) {return aItemID==0?null:make(Item.getItemById((int)aItemID), aStacksize, aMetaData, aName);}
	public static ItemStack make(Item aItem, long aStacksize, long aMetaData, String aName) {return aItem == null ? null : make(new ItemStack(aItem, UT.Code.bindInt(aStacksize), (int)aMetaData), aName, null);}
	public static ItemStack make(Block aBlock, long aStacksize, long aMetaData, String aName) {return aBlock == null || aBlock == NB ? null : make(new ItemStack(aBlock, UT.Code.bindInt(aStacksize), (int)aMetaData), aName, null);}
//	public static ItemStack make(IBlock aBlock, long aStacksize, long aMetaData, String aName) {return aBlock == null ? null : make(new ItemStack(aBlock.getBlock(), UT.Code.bindInt(aStacksize), (int)aMetaData), aName, null);}
	public static ItemStack make(long aItemID, long aStacksize, long aMetaData, String aName, NBTTagCompound aNBT) {return aItemID==0?null:make(Item.getItemById((int)aItemID), aStacksize, aMetaData, aName, aNBT);}
	public static ItemStack make(Item aItem, long aStacksize, long aMetaData, String aName, NBTTagCompound aNBT) {return aItem == null ? null : make(new ItemStack(aItem, UT.Code.bindInt(aStacksize), (int)aMetaData), aName, aNBT);}
	public static ItemStack make(Block aBlock, long aStacksize, long aMetaData, String aName, NBTTagCompound aNBT) {return aBlock == null || aBlock == NB ? null : make(new ItemStack(aBlock, UT.Code.bindInt(aStacksize), (int)aMetaData), aName, aNBT);}
//	public static ItemStack make(IBlock aBlock, long aStacksize, long aMetaData, String aName, NBTTagCompound aNBT) {return aBlock == null ? null : make(new ItemStack(aBlock.getBlock(), UT.Code.bindInt(aStacksize), (int)aMetaData), aName, aNBT);}
	public static ItemStack make(ItemStack aStack, String aName, NBTTagCompound aNBT) {if (aStack == null) return null; aStack = aStack.copy(); UT.NBT.set(aStack, aNBT); if (aName != null) aStack.setStackDisplayName(aName); return aStack;}
	public static ItemStack make(ItemStackContainer aStack, String aName, NBTTagCompound aNBT) {if (aStack == null) return null; ItemStack rStack = aStack.toStack(); if (rStack == null) return null; UT.NBT.set(rStack, aNBT); if (aName != null) rStack.setStackDisplayName(aName); return rStack;}
	
	public static EntityItem place	(World aWorld, double aX, double aY, double aZ, ModData aModID, String aItem, long aAmount, long aMetaData	) {ItemStack rStack = ST.make(aModID, aItem, aAmount, aMetaData)	; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place	(World aWorld, double aX, double aY, double aZ, Item aItem, long aStacksize, long aMetaData					) {ItemStack rStack = ST.make(aItem, aStacksize, aMetaData)			; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place	(World aWorld, double aX, double aY, double aZ, Block aBlock, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aBlock, aStacksize, aMetaData)		; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place	(World aWorld, double aX, double aY, double aZ, ItemStackContainer aStack									) {ItemStack rStack = aStack.toStack()								; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place	(World aWorld, double aX, double aY, double aZ, ItemStack aStack											) {ItemStack rStack = aStack										; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(World aWorld, double aX, double aY, double aZ, ModData aModID, String aItem, long aAmount, long aMetaData	) {ItemStack rStack = ST.make(aModID, aItem, aAmount, aMetaData)	; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(World aWorld, double aX, double aY, double aZ, Item aItem, long aStacksize, long aMetaData					) {ItemStack rStack = ST.make(aItem, aStacksize, aMetaData)			; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(World aWorld, double aX, double aY, double aZ, Block aBlock, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aBlock, aStacksize, aMetaData)		; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(World aWorld, double aX, double aY, double aZ, ItemStackContainer aStack									) {ItemStack rStack = aStack.toStack()								; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(World aWorld, double aX, double aY, double aZ, ItemStack aStack											) {ItemStack rStack = aStack										; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem entity	(World aWorld, double aX, double aY, double aZ, ModData aModID, String aItem, long aAmount, long aMetaData	) {ItemStack rStack = ST.make(aModID, aItem, aAmount, aMetaData)	; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); return rEntity;}
	public static EntityItem entity	(World aWorld, double aX, double aY, double aZ, Item aItem, long aStacksize, long aMetaData					) {ItemStack rStack = ST.make(aItem, aStacksize, aMetaData)			; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); return rEntity;}
	public static EntityItem entity	(World aWorld, double aX, double aY, double aZ, Block aBlock, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aBlock, aStacksize, aMetaData)		; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); return rEntity;}
	public static EntityItem entity	(World aWorld, double aX, double aY, double aZ, ItemStackContainer aStack									) {ItemStack rStack = aStack.toStack()								; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); return rEntity;}
	public static EntityItem entity	(World aWorld, double aX, double aY, double aZ, ItemStack aStack											) {ItemStack rStack = aStack										; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aX, aY, aZ, update_(rStack, aWorld, UT.Code.roundDown(aX), UT.Code.roundDown(aY), UT.Code.roundDown(aZ))); return rEntity;}
	
	public static EntityItem place	(Entity aEntity, ModData aModID, String aItem, long aAmount, long aMetaData	) {ItemStack rStack = ST.make(aModID, aItem, aAmount, aMetaData)	; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place	(Entity aEntity, Item aItem, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aItem, aStacksize, aMetaData)			; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place	(Entity aEntity, Block aBlock, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aBlock, aStacksize, aMetaData)		; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place	(Entity aEntity, ItemStackContainer aStack									) {ItemStack rStack = aStack.toStack()								; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place	(Entity aEntity, ItemStack aStack											) {ItemStack rStack = aStack										; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(Entity aEntity, ModData aModID, String aItem, long aAmount, long aMetaData	) {ItemStack rStack = ST.make(aModID, aItem, aAmount, aMetaData)	; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(Entity aEntity, Item aItem, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aItem, aStacksize, aMetaData)			; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(Entity aEntity, Block aBlock, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aBlock, aStacksize, aMetaData)		; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(Entity aEntity, ItemStackContainer aStack									) {ItemStack rStack = aStack.toStack()								; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(Entity aEntity, ItemStack aStack											) {ItemStack rStack = aStack										; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); return aEntity.worldObj.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem entity	(Entity aEntity, ModData aModID, String aItem, long aAmount, long aMetaData	) {ItemStack rStack = ST.make(aModID, aItem, aAmount, aMetaData)	; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); return rEntity;}
	public static EntityItem entity	(Entity aEntity, Item aItem, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aItem, aStacksize, aMetaData)			; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); return rEntity;}
	public static EntityItem entity	(Entity aEntity, Block aBlock, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aBlock, aStacksize, aMetaData)		; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); return rEntity;}
	public static EntityItem entity	(Entity aEntity, ItemStackContainer aStack									) {ItemStack rStack = aStack.toStack()								; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); return rEntity;}
	public static EntityItem entity	(Entity aEntity, ItemStack aStack											) {ItemStack rStack = aStack										; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aEntity.worldObj, aEntity.posX, aEntity.posY, aEntity.posZ, update_(rStack, aEntity.worldObj, UT.Code.roundDown(aEntity.posX), UT.Code.roundDown(aEntity.posY), UT.Code.roundDown(aEntity.posZ))); return rEntity;}
	
	public static EntityItem place	(World aWorld, ChunkCoordinates aCoords, ModData aModID, String aItem, long aAmount, long aMetaData	) {ItemStack rStack = ST.make(aModID, aItem, aAmount, aMetaData)	; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place	(World aWorld, ChunkCoordinates aCoords, Item aItem, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aItem, aStacksize, aMetaData)			; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place	(World aWorld, ChunkCoordinates aCoords, Block aBlock, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aBlock, aStacksize, aMetaData)		; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place	(World aWorld, ChunkCoordinates aCoords, ItemStackContainer aStack									) {ItemStack rStack = aStack.toStack()								; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem place	(World aWorld, ChunkCoordinates aCoords, ItemStack aStack											) {ItemStack rStack = aStack										; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); rEntity.motionX = rEntity.motionY = rEntity.motionZ = 0; return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(World aWorld, ChunkCoordinates aCoords, ModData aModID, String aItem, long aAmount, long aMetaData	) {ItemStack rStack = ST.make(aModID, aItem, aAmount, aMetaData)	; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(World aWorld, ChunkCoordinates aCoords, Item aItem, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aItem, aStacksize, aMetaData)			; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(World aWorld, ChunkCoordinates aCoords, Block aBlock, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aBlock, aStacksize, aMetaData)		; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(World aWorld, ChunkCoordinates aCoords, ItemStackContainer aStack									) {ItemStack rStack = aStack.toStack()								; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem drop	(World aWorld, ChunkCoordinates aCoords, ItemStack aStack											) {ItemStack rStack = aStack										; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); return aWorld.spawnEntityInWorld(rEntity) ? rEntity : null;}
	public static EntityItem entity	(World aWorld, ChunkCoordinates aCoords, ModData aModID, String aItem, long aAmount, long aMetaData	) {ItemStack rStack = ST.make(aModID, aItem, aAmount, aMetaData)	; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); return rEntity;}
	public static EntityItem entity	(World aWorld, ChunkCoordinates aCoords, Item aItem, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aItem, aStacksize, aMetaData)			; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); return rEntity;}
	public static EntityItem entity	(World aWorld, ChunkCoordinates aCoords, Block aBlock, long aStacksize, long aMetaData				) {ItemStack rStack = ST.make(aBlock, aStacksize, aMetaData)		; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); return rEntity;}
	public static EntityItem entity	(World aWorld, ChunkCoordinates aCoords, ItemStackContainer aStack									) {ItemStack rStack = aStack.toStack()								; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); return rEntity;}
	public static EntityItem entity	(World aWorld, ChunkCoordinates aCoords, ItemStack aStack											) {ItemStack rStack = aStack										; if (invalid(rStack)) return null; EntityItem rEntity = new EntityItem(aWorld, aCoords.posX+0.5, aCoords.posY+0.5, aCoords.posZ+0.5, update_(rStack, aWorld, aCoords.posX, aCoords.posY, aCoords.posZ)); return rEntity;}
	
	public static ItemStack[] copyArray(Object... aStacks) {
		ItemStack[] rStacks = new ItemStack[aStacks.length];
		for (int i = 0; i < aStacks.length; i++) rStacks[i] = copy(aStacks[i]);
		return rStacks;
	}
	
	public static ItemStack copy(Object... aStacks) {
		for (Object tStack : aStacks) if (valid(tStack)) return ((ItemStack)tStack).copy();
		return null;
	}
	
	public static ItemStack amountValidMeta(long aAmount, Object... aStacks) {
		ItemStack rStack = copy(aStacks);
		if (invalid(rStack)) return null;
		if (meta(rStack) == W) meta(rStack, 0);
		rStack.stackSize = (int)aAmount;
		return rStack;
	}
	
	public static ItemStack amount(long aAmount, Object... aStacks) {
		ItemStack rStack = copy(aStacks);
		if (invalid(rStack)) return null;
		rStack.stackSize = (int)aAmount;
		return rStack;
	}
	
	public static ItemStack copyMeta(long aMetaData, Object... aStacks) {
		ItemStack rStack = copy(aStacks);
		if (invalid(rStack)) return null;
		return meta(rStack, aMetaData);
	}
	
	public static ItemStack copyAmountAndMeta(long aAmount, long aMetaData, Object... aStacks) {
		ItemStack rStack = amount(aAmount, aStacks);
		if (invalid(rStack)) return null;
		return meta(rStack, aMetaData);
	}
	
	/**
	 * returns a copy of an ItemStack with its StackSize being multiplied by aMultiplier
	 */
	public static ItemStack mul(long aMultiplier, Object... aStacks) {
		ItemStack rStack = copy(aStacks);
		if (rStack == null) return null;
		rStack.stackSize *= aMultiplier;
		return rStack;
	}
	
	/**
	 * returns a copy of an ItemStack with its StackSize being divided by aDivider
	 */
	public static ItemStack div(long aDivider, Object... aStacks) {
		ItemStack rStack = copy(aStacks);
		if (rStack == null) return null;
		rStack.stackSize /= aDivider;
		return rStack;
	}
	
	public static int toInt(ItemStack aStack) {
		if (invalid(aStack)) return 0;
		return id(aStack) | (meta(aStack)<<16);
	}
	
	public static int toIntWildcard(ItemStack aStack) {
		if (invalid(aStack)) return 0;
		return id(aStack) | (W<<16);
	}
	
	public static ItemStack toStack(int aStack) {
		Item tItem = Item.getItemById(aStack&(~0>>>16));
		if (tItem != null) return make(tItem, 1, aStack>>>16);
		return null;
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
	
	public static Block block(Object aStack) {
		if (invalid(aStack)) return NB;
		return Block.getBlockFromItem(((ItemStack)aStack).getItem());
	}
	
	public static Block block_(Object aStack) {
		return Block.getBlockFromItem(((ItemStack)aStack).getItem());
	}
	
	public static boolean valid(Object aStack) {
		return	 aStack instanceof ItemStack  && ((ItemStack)aStack).stackSize >= 0 && ((ItemStack)aStack).getItem() != null;
	}
	
	public static boolean invalid(Object aStack) {
		return !(aStack instanceof ItemStack) || ((ItemStack)aStack).stackSize <  0 || ((ItemStack)aStack).getItem() == null;
	}
	
	public static String configName(ItemStack aStack) {
		if (invalid(aStack)) return "";
		Object rName = OreDictManager.INSTANCE.getAssociation_(aStack, T);
		if (rName != null) return rName.toString();
		try {if (UT.Code.stringValid(rName = aStack.getUnlocalizedName())) return rName.toString();} catch (Throwable e) {/*Do nothing*/}
		return aStack.getItem() + "." + meta(aStack);
	}
	
	public static String configNames(ItemStack... aStacks) {
		String rString = "";
		for (ItemStack tStack : aStacks) rString += (tStack == null ? "null;" : configName(tStack) + ";");
		return rString;
	}
	
	public static String regName(ItemStack aStack) {
		return invalid(aStack) ? "null" : Item.itemRegistry.getNameForObject(aStack.getItem());
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
	
	public static void hide(Item aItem, long aMetaData) {
		hide(make(aItem, 1, aMetaData));
	}
	
	public static void hide(Block aBlock) {
		hide(aBlock, W);
	}
	
	public static void hide(Block aBlock, long aMetaData) {
		hide(make(aBlock, 1, aMetaData));
	}
	
	public static void hide(ItemStack aStack) {
		if (aStack != null) try {codechicken.nei.api.API.hideItem(aStack);} catch(Throwable e) {/**/}
	}
	
	/**
	 * Loads an ItemStack properly.
	 */
	public static ItemStack load(NBTTagCompound aNBT, String aTagName) {
		return aNBT == null ? null : load(aNBT.getCompoundTag(aTagName));
	}
	
	/**
	 * Loads an ItemStack properly.
	 */
	public static ItemStack load(NBTTagCompound aNBT) {
		if (aNBT == null || aNBT.hasNoTags()) return null;
		ItemStack rStack = make(Item.getItemById(aNBT.getShort("id")), aNBT.getInteger("Count"), aNBT.getShort("Damage"), aNBT.hasKey("tag", 10)?aNBT.getCompoundTag("tag"):null);
		if (rStack == null) return null;
		if (rStack.getItem().getClass().getName().startsWith("ic2.core.migration")) rStack.getItem().onUpdate(rStack, DW, null, 0, F);
		return update_(OM.get_(rStack));
	}
	
	/**
	 * Saves an ItemStack properly.
	 */
	public static NBTTagCompound save(NBTTagCompound aNBT, String aTagName, ItemStack aStack) {
		if (aNBT == null) aNBT = UT.NBT.make();
		NBTTagCompound tNBT = save(aStack);
		if (tNBT != null) aNBT.setTag(aTagName, tNBT);
		return aNBT;
	}
	
	/**
	 * Saves an ItemStack properly.
	 */
	public static NBTTagCompound save(ItemStack aStack) {
		if (invalid(aStack)) return null;
		NBTTagCompound rNBT = UT.NBT.make();
		aStack = OM.get_(aStack);
		rNBT.setShort("id", (short)Item.getIdFromItem(aStack.getItem()));
		UT.NBT.setNumber(rNBT, "Count", aStack.stackSize);
		rNBT.setShort("Damage", meta(aStack));
		if (aStack.hasTagCompound()) rNBT.setTag("tag", aStack.getTagCompound());
		return rNBT;
	}
}
