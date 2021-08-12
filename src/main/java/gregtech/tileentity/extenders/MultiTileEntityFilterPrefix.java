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

package gregtech.tileentity.extenders;

import static gregapi.data.CS.*;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackSet;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.gui.ContainerClient;
import gregapi.gui.ContainerCommon;
import gregapi.gui.Slot_Holo;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictPrefix;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.logistics.ITileEntityLogisticsSemiFilteredItem;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 * 
 * An example implementation of a Miniature Nether Portal with my MultiTileEntity System.
 */
public class MultiTileEntityFilterPrefix extends MultiTileEntityExtender implements ITileEntityLogisticsSemiFilteredItem {
	public OreDictPrefix mFilter = null;
	public ItemStack mCycle = null;
	public boolean mInverted = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		if (aNBT.hasKey(NBT_INVERTED)) mInverted = aNBT.getBoolean(NBT_INVERTED);
		mFilter = OreDictPrefix.sPrefixes.get(aNBT.getString(NBT_INV_FILTER));
		super.readFromNBT2(aNBT);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		UT.NBT.setBoolean(aNBT, NBT_INVERTED, mInverted);
		if (mFilter != null) aNBT.setString(NBT_INV_FILTER, mFilter.mNameInternal);
		super.writeToNBT2(aNBT);
	}
	
	@Override
	public NBTTagCompound writeItemNBT2(NBTTagCompound aNBT) {
		UT.NBT.setBoolean(aNBT, NBT_INVERTED, mInverted);
		if (mFilter != null) aNBT.setString(NBT_INV_FILTER, mFilter.mNameInternal);
		return super.writeItemNBT2(aNBT);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && aTimer % 10 == 1) {
			if (mFilter == null || mFilter.mRegisteredItems.isEmpty()) {
				mCycle = null;
			} else {
				mCycle = ST.make(UT.Code.select(null, mFilter.mRegisteredItems.toArray(ZL_ISC)), mFilter.mNameInternal, null);
				if (mCycle != null && ST.meta_(mCycle) == W) ST.meta_(mCycle, 0);
			}
		}
	}
	
	@Override public Object getGUIClient2(int aGUIID, EntityPlayer aPlayer) {return new MultiTileEntityGUIClientFilterPrefix(aPlayer.inventory, this, aGUIID);}
	@Override public Object getGUIServer2(int aGUIID, EntityPlayer aPlayer) {return new MultiTileEntityGUICommonFilterPrefix(aPlayer.inventory, this, aGUIID);}
	@Override public int getSizeInventoryGUI() {return 1;}
	@Override public ItemStack getStackInSlotGUI(int aSlot) {return mCycle;}
	@Override public ItemStack decrStackSizeGUI(int aSlot, int aDecrement) {return null;}
	@Override public ItemStack getStackInSlotOnClosingGUI(int aSlot) {return null;}
	@Override public void setInventorySlotContentsGUI(int aSlot, ItemStack aStack) {mCycle = aStack;}
	@Override public int getInventoryStackLimitGUI(int aSlot) {return 1;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.filter.prefix";}
	
	public boolean allowInput(ItemStack aStack) {
		return mFilter != null && (mFilter.contains(aStack) != mInverted);
	}
	public boolean allowInput(FluidStack aFluid) {
		return T;
	}
	public boolean allowInput(Fluid aFluid) {
		return T;
	}
	
	@Override
	public ItemStackSet<ItemStackContainer> getLogisticsFilter(byte aSide) {
		return mInverted || mFilter == null ? null : mFilter.mRegisteredItems;
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		aList.add(Chat.DGRAY + LH.get(LH.TOOL_TO_RESET_SOFT_HAMMER));
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isClientSide()) return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (aTool.equals(TOOL_screwdriver)) {
			mInverted = !mInverted;
			if (aChatReturn != null) aChatReturn.add(mInverted ? "Blacklist Filter" : "Whitelist Filter");
			return 2000;
		}
		if (aTool.equals(TOOL_softhammer)) {
			mInverted = F;
			mFilter = null;
			mCycle = null;
			if (aChatReturn != null) aChatReturn.add("Cleared the Filter");
			return 10000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) aChatReturn.add(mInverted ? "Blacklist Filter" : "Whitelist Filter");
			return 1;
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public boolean onBlockActivated3(EntityPlayer aPlayer, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (isServerSide() && isUseableByPlayerGUI(aPlayer)) openGUI(aPlayer);
		return T;
	}
	
	@Override
	public boolean isItemValidForSlot(int aSlot, ItemStack aStack) {
		if ((mModes & MODE_INV) != 0 && ST.valid(aStack) && (mLastSide == mFacing || allowInput(aStack))) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.isItemValidForSlot(aSlot, aStack);
		}
		return F;
	}
	
	@Override
	public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {
		mLastSide = aSide;
		if ((mModes & MODE_INV) != 0 && ST.valid(aStack) && (mLastSide == mFacing || allowInput(aStack))) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity instanceof ISidedInventory) return ((ISidedInventory)tTileEntity.mTileEntity).canInsertItem(aSlot, aStack, tTileEntity.mSideOfTileEntity);
			if (tTileEntity.mTileEntity != null) return T;
		}
		return F;
	}
	
	@Override
	public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {
		mLastSide = aSide;
		if ((mModes & MODE_INV) != 0 && ST.valid(aStack) && (mLastSide == mFacing || allowInput(aStack))) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity instanceof ISidedInventory) return ((ISidedInventory)tTileEntity.mTileEntity).canExtractItem(aSlot, aStack, tTileEntity.mSideOfTileEntity);
			if (tTileEntity.mTileEntity != null) return T;
		}
		return F;
	}
	
	@Override
	public int fill(ForgeDirection aDirection, FluidStack aFluid, boolean aDoFill) {
		byte aSide = UT.Code.side(aDirection);
		if ((mModes & MODE_TANK) != 0 && (aSide == mFacing || allowInput(aFluid))) {
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidFill(aSide, mCovers, aSide, aFluid)) return 0;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.fill(tTileEntity.getForgeSideOfTileEntity(), aFluid, aDoFill);
		}
		return 0;
	}
	@Override
	public FluidStack drain(ForgeDirection aDirection, FluidStack aFluid, boolean aDoDrain) {
		byte aSide = UT.Code.side(aDirection);
		if ((mModes & MODE_TANK) != 0 && (aSide == mFacing || allowInput(aFluid))) {
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidDrain(aSide, mCovers, aSide, aFluid)) return null;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.drain(tTileEntity.getForgeSideOfTileEntity(), aFluid, aDoDrain);
		}
		return null;
	}
	@Override
	public FluidStack drain(ForgeDirection aDirection, int aToDrain, boolean aDoDrain) {
		if ((mModes & MODE_TANK) != 0) {
			byte aSide = UT.Code.side(aDirection);
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidDrain(aSide, mCovers, aSide, null)) return null;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return (aSide == mFacing || allowInput(tTileEntity.mTileEntity.drain(tTileEntity.getForgeSideOfTileEntity(), aToDrain, F))) ? tTileEntity.mTileEntity.drain(tTileEntity.getForgeSideOfTileEntity(), aToDrain, aDoDrain) : null;
		}
		return null;
	}
	
	@Override
	public boolean canFill(ForgeDirection aDirection, Fluid aFluid) {
		byte aSide = UT.Code.side(aDirection);
		if ((mModes & MODE_TANK) != 0 && (aSide == mFacing || allowInput(aFluid))) {
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidFill(aSide, mCovers, aSide, FL.make(aFluid, 1))) return F;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.canFill(tTileEntity.getForgeSideOfTileEntity(), aFluid);
		}
		return F;
	}
	@Override
	public boolean canDrain(ForgeDirection aDirection, Fluid aFluid) {
		byte aSide = UT.Code.side(aDirection);
		if ((mModes & MODE_TANK) != 0 && (aSide == mFacing || allowInput(aFluid))) {
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidDrain(aSide, mCovers, aSide, FL.make(aFluid, 1))) return F;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.canDrain(tTileEntity.getForgeSideOfTileEntity(), aFluid);
		}
		return F;
	}
	
	public class MultiTileEntityGUICommonFilterPrefix extends ContainerCommon {
		public MultiTileEntityGUICommonFilterPrefix(InventoryPlayer aInventoryPlayer, MultiTileEntityFilterPrefix aTileEntity, int aGUIID) {
			super(aInventoryPlayer, aTileEntity, aGUIID);
		}
		
		@Override
		public int addSlots(InventoryPlayer aPlayerInventory) {
			addSlotToContainer(new Slot_Holo(mTileEntity, mOffset, 80, 35, F, F, 1));
			return 84;
		}
		
		@Override
		public ItemStack slotClick(int aSlotIndex, int aMouseclick, int aShifthold, EntityPlayer aPlayer) {
			if (aSlotIndex < 0 || aSlotIndex >= mTileEntity.getSizeInventoryGUI()) return super.slotClick(aSlotIndex, aMouseclick, aShifthold, aPlayer);
			
			ItemStack tStack = aPlayer.inventory.getItemStack();
			if (tStack == null) {
				((MultiTileEntityFilterPrefix)mTileEntity).mFilter = null;
			} else {
				OreDictItemData tData = OM.anyassociation_(tStack);
				if (tData == null || ((MultiTileEntityFilterPrefix)mTileEntity).mFilter == tData.mPrefix) {
					for (OreDictPrefix tPrefix : OreDictPrefix.VALUES_SORTED) if (((MultiTileEntityFilterPrefix)mTileEntity).mFilter != tPrefix && !tPrefix.containsAny(TD.Prefix.UNIFICATABLE, TD.Prefix.NO_PREFIX_FILTERING) && tPrefix.contains(tStack)) {
						((MultiTileEntityFilterPrefix)mTileEntity).mFilter = tPrefix;
						break;
					}
				} else {
					((MultiTileEntityFilterPrefix)mTileEntity).mFilter = tData.mPrefix;
				}
			}
			return null;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public class MultiTileEntityGUIClientFilterPrefix extends ContainerClient {
		public MultiTileEntityGUIClientFilterPrefix(InventoryPlayer aInventoryPlayer, MultiTileEntityFilterPrefix aTileEntity, int aGUIID) {
			super(new MultiTileEntityGUICommonFilterPrefix(aInventoryPlayer, aTileEntity, aGUIID), RES_PATH_GUI + "machines/FilterPrefix.png");
		}
	}
}
