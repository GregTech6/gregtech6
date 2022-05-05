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

package gregapi.tileentity.energy;

import gregapi.block.multitileentity.IMultiTileEntity.*;
import gregapi.block.multitileentity.MultiTileEntityBlockInternal;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.code.HashSetNoNulls;
import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.TD;
import gregapi.item.IItemEnergy;
import gregapi.tileentity.ITileEntityQuickObstructionCheck;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase08Battery extends TileEntityBase07Paintable implements ITileEntityQuickObstructionCheck, IMTE_SetBlockBoundsBasedOnState, IMTE_GetCollisionBoundingBoxFromPool, IMTE_GetSelectedBoundingBoxFromPool, IItemEnergy, IMTE_GetMaxStackSize, IMTE_OnlyPlaceableWhenSneaking, IMTE_AddToolTips {
	public long mEnergy = 0, mSizeMin = 0, mSizeRec = 0, mSizeMax = 0, mCapacity = 0;
	public byte mDisplayedEnergy = 0;
	public TagData mType = TD.Energy.EU;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_INPUT)) mSizeRec = aNBT.getLong(NBT_INPUT);
		mSizeMin = mSizeRec / 2; mSizeMax = mSizeRec * 2;
		if (mSizeMin <= 8 && mSizeRec > 0) mSizeMin = 1;
		if (aNBT.hasKey(NBT_INPUT_MIN)) mSizeMin = aNBT.getLong(NBT_INPUT_MIN);
		if (aNBT.hasKey(NBT_INPUT_MAX)) mSizeMax = aNBT.getLong(NBT_INPUT_MAX);
		if (aNBT.hasKey(NBT_CAPACITY)) mCapacity = aNBT.getLong(NBT_CAPACITY);
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mType = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
		
		if (aNBT.getBoolean(NBT_ACTIVE_ENERGY)) {
			mEnergy = mCapacity;
		} else {
			if (aNBT.hasKey(NBT_ENERGY)) mEnergy = aNBT.getLong(NBT_ENERGY);
		}
		
		if (mEnergy > mCapacity) mEnergy = mCapacity;
		mDisplayedEnergy = (byte)UT.Code.scale(mEnergy, mCapacity, getDisplayScaleMax(), F);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		super.writeToNBT2(aNBT);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		return super.writeItemNBT2(aNBT);
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition aTarget) {
		MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry(getMultiTileEntityRegistryID());
		if (tRegistry == null) return null;
		long oEnergy = mEnergy;
		mEnergy = UT.Code.units(mCapacity, getDisplayScaleMax(), mDisplayedEnergy, T);
		ItemStack rStack = tRegistry.getItem(getMultiTileEntityID(), writeItemNBT(UT.NBT.make()));
		mEnergy = oEnergy;
		return rStack;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (mCapacity > 0) aList.add(LH.Chat.WHITE + UT.Code.makeString(Math.min(mCapacity, mEnergy)) + " / " + UT.Code.makeString(mCapacity) + " " + mType.getLocalisedChatNameShort() + LH.Chat.WHITE + " - Size: " + mSizeRec);
	}
	
	@Override
	public boolean getSubItems(MultiTileEntityBlockInternal aBlock, Item aItem, CreativeTabs aTab, List<ItemStack> aList, short aID) {
		aList.add(aBlock.mMultiTileEntityRegistry.getItem(aID));
		if (mCapacity > 0)
		aList.add(setEnergyStored(mType, aBlock.mMultiTileEntityRegistry.getItem(aID), mCapacity));
		return F;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0 || isClientSide()) return rReturn;
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) aChatReturn.add(UT.Code.makeString(mEnergy) + " of " + UT.Code.makeString(mCapacity) + " " + mType.getLocalisedNameShort());
			return 1;
		}
		return 0;
	}
	
	@Override public float getSurfaceSize           (byte aSide) {return 0.0F;}
	@Override public float getSurfaceSizeAttachable (byte aSide) {return 0.0F;}
	@Override public float getSurfaceDistance       (byte aSide) {return 0.0F;}
	@Override public boolean isSurfaceSolid         (byte aSide) {return F;}
	@Override public boolean isSurfaceOpaque2       (byte aSide) {return F;}
	@Override public boolean isSideSolid2           (byte aSide) {return F;}
	@Override public boolean allowCovers            (byte aSide) {return F;}
	@Override public boolean attachCoversFirst      (byte aSide) {return F;}
	@Override public boolean isObstructingBlockAt   (byte aSide) {return F;}
	
	@Override public boolean onlyPlaceableWhenSneaking() {return T;}
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	@Override public int getLightOpacity() {return LIGHT_OPACITY_NONE;}
	@Override public byte getMaxStackSize(ItemStack aStack, byte aDefault) {return mEnergy > 0 ? 1 : aDefault;}
	@Override public byte getVisualData() {return mDisplayedEnergy;}
	@Override public void setVisualData(byte aData) {mDisplayedEnergy = aData;}
	
	public byte getDisplayScaleMax() {return 16;}
	
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return mDisplayedEnergy > 0 ? 2 : 1;
	}
	
	@Override
	public long doEnergyInjection(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoInject) {
		if (aAmount < 1 || mSizeRec < 1) return 0;
		if (!canEnergyInjection(aEnergyType, aStack, aSize = Math.abs(aSize))) return 0;
		if (mEnergy >= mCapacity) return 0;
		long rAmount = Math.min(mSizeRec, aAmount);
		while (rAmount > 1 && mEnergy + rAmount * aSize > mCapacity) rAmount--;
		if (aDoInject) setEnergyStored(mType, aStack, mEnergy + rAmount * aSize);
		return rAmount;
	}
	
	@Override
	public long doEnergyExtraction(TagData aEnergyType, ItemStack aStack, long aSize, long aAmount, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoExtract) {
		if (aAmount < 1 || mSizeRec < 1) return 0;
		if (!canEnergyExtraction(aEnergyType, aStack, aSize = Math.abs(aSize))) return 0;
		if (mEnergy < aSize) return 0;
		long rAmount = Math.min(mSizeRec, aAmount);
		while (rAmount > 1 && mEnergy - rAmount * aSize < 0) rAmount--;
		if (aDoExtract) setEnergyStored(mType, aStack, mEnergy - rAmount * aSize);
		return rAmount;
	}
	
	public ItemStack rechargeFromPlayer(TagData aEnergyType, ItemStack aStack, EntityLivingBase aPlayer, IInventory aInventory, World aWorld, int aX, int aY, int aZ) {
		if (COMPAT_EU_ITEM == null || aPlayer == null || aPlayer.worldObj.isRemote || aEnergyType != mType || aEnergyType != TD.Energy.EU) return aStack;
		long tMinInput = getEnergySizeInputMin(aEnergyType, aStack);
		boolean temp = F;
		try {for (int i = 1; i < 5; i++) {
			if (mEnergy >= mCapacity) return aStack;
			ItemStack tArmor = aPlayer.getEquipmentInSlot(i);
			if (tArmor == aStack || ST.invalid(tArmor) || !COMPAT_EU_ITEM.is(tArmor) || VMAX[COMPAT_EU_ITEM.tier(tArmor)] < tMinInput || !COMPAT_EU_ITEM.provider(tArmor)) continue;
			setEnergyStored(aEnergyType, aStack, mEnergy+COMPAT_EU_ITEM.decharge(tArmor, mCapacity-mEnergy, T));
			temp = T;
		}} catch(Throwable e) {e.printStackTrace(ERR);}
		if (temp) ST.update(aPlayer);
		return aStack;
	}
	
	@Override
	public boolean useEnergy(TagData aEnergyType, ItemStack aStack, long aEnergyAmount, EntityLivingBase aPlayer, IInventory aInventory, World aWorld, int aX, int aY, int aZ, boolean aDoUse) {
		if (aPlayer instanceof EntityPlayer && ((EntityPlayer)aPlayer).capabilities.isCreativeMode) return T;
		if (aEnergyType != mType && aEnergyType != null) return F;
		rechargeFromPlayer(mType, aStack, aPlayer, aInventory, aWorld, aX, aY, aZ);
		if (mEnergy >= aEnergyAmount) {
			if (aDoUse) {
				setEnergyStored(mType, aStack, mEnergy-aEnergyAmount);
				rechargeFromPlayer(mType, aStack, aPlayer, aInventory, aWorld, aX, aY, aZ);
			}
			return T;
		}
		if (aDoUse) {
			setEnergyStored(mType, aStack, 0);
			rechargeFromPlayer(mType, aStack, aPlayer, aInventory, aWorld, aX, aY, aZ);
		}
		return F;
	}
	
	@Override
	public ItemStack setEnergyStored(TagData aEnergyType, ItemStack aStack, long aAmount) {
		if ((aEnergyType != mType && aEnergyType != null) || ST.size(aStack) <= 0) return aStack;
		mEnergy = aAmount;
		UT.NBT.set(aStack, writeItemNBT(aStack.hasTagCompound() ? aStack.getTagCompound() : UT.NBT.make()));
		return ST.update_(aStack);
	}
	
	@Override public long getEnergyStored(TagData aEnergyType, ItemStack aStack) {return aEnergyType == mType || aEnergyType == null ? mEnergy : 0;}
	@Override public long getEnergyCapacity(TagData aEnergyType, ItemStack aStack) {return aEnergyType == mType || aEnergyType == null ? mCapacity : 0;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, ItemStack aStack) {return aEnergyType == mType || aEnergyType == null ? mSizeMin : 0;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, ItemStack aStack) {return aEnergyType == mType || aEnergyType == null ? mSizeMin : 0;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, ItemStack aStack) {return aEnergyType == mType || aEnergyType == null ? mSizeRec : 0;}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, ItemStack aStack) {return aEnergyType == mType || aEnergyType == null ? mSizeRec : 0;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, ItemStack aStack) {return aEnergyType == mType || aEnergyType == null ? mSizeMax : 0;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, ItemStack aStack) {return aEnergyType == mType || aEnergyType == null ? mSizeMax : 0;}
	@Override public Collection<TagData> getEnergyTypes(ItemStack aStack) {return new HashSetNoNulls<>(F, mType);}
	@Override public boolean isEnergyType(TagData aEnergyType, ItemStack aStack, boolean aEmitting) {return (aEnergyType == mType || aEnergyType == null);}
	@Override public boolean canEnergyInjection (TagData aEnergyType, ItemStack aStack, long aSize) {return (aEnergyType == mType || aEnergyType == null) && aStack.stackSize == 1 && aSize <= getEnergySizeInputMax (aEnergyType, aStack) && aSize >= getEnergySizeInputMin (aEnergyType, aStack);}
	@Override public boolean canEnergyExtraction(TagData aEnergyType, ItemStack aStack, long aSize) {return (aEnergyType == mType || aEnergyType == null) && aStack.stackSize == 1 && aSize <= getEnergySizeOutputMax(aEnergyType, aStack) && aSize >= getEnergySizeOutputMin(aEnergyType, aStack);}
}
