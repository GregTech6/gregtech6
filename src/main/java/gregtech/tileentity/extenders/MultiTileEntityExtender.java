/**
 * Copyright (c) 2023 GregTech-6 Team
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

import gregapi.GT_API;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetComparatorInputOverride;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.ITileEntityAdjacentInventoryUpdatable;
import gregapi.tileentity.base.TileEntityBase10FacingDouble;
import gregapi.tileentity.connectors.ITileEntityItemPipe;
import gregapi.tileentity.data.ITileEntityProgress;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.delegate.ITileEntityDelegating;
import gregapi.tileentity.machines.*;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityExtender extends TileEntityBase10FacingDouble implements ITileEntityDelegating, ITileEntityAdjacentInventoryUpdatable, IFluidHandler, IMTE_GetComparatorInputOverride {
	public byte mComparator = 0, mRedstoneIn = 0, mRedstoneOut = 0, mModes = 0;
	
	protected IIconContainer[] mTextures = L6_IICONCONTAINER;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_MODE)) mModes = aNBT.getByte(NBT_MODE);
		
		if (CODE_CLIENT) {
			if (GT_API.sBlockIcons == null && aNBT.hasKey(NBT_TEXTURE)) {
				String tTextureName = aNBT.getString(NBT_TEXTURE);
				mTextures = new IIconContainer[] {
				new Textures.BlockIcons.CustomIcon("machines/extenders/"+tTextureName+"/colored/in"),
				new Textures.BlockIcons.CustomIcon("machines/extenders/"+tTextureName+"/colored/out"),
				new Textures.BlockIcons.CustomIcon("machines/extenders/"+tTextureName+"/colored/side"),
				new Textures.BlockIcons.CustomIcon("machines/extenders/"+tTextureName+"/overlay/in"),
				new Textures.BlockIcons.CustomIcon("machines/extenders/"+tTextureName+"/overlay/out"),
				new Textures.BlockIcons.CustomIcon("machines/extenders/"+tTextureName+"/overlay/side")};
			} else {
				TileEntity tCanonicalTileEntity = MultiTileEntityRegistry.getCanonicalTileEntity(getMultiTileEntityRegistryID(), getMultiTileEntityID());
				if (tCanonicalTileEntity instanceof MultiTileEntityExtender) {
					mTextures = ((MultiTileEntityExtender)tCanonicalTileEntity).mTextures;
				}
			}
		}
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if ((mModes & EXTENDER_ALL) == EXTENDER_ALL) {
			aList.add(Chat.CYAN + LH.get(TOOLTIP_EXTENDER_ALL));
		} else {
			if ((mModes & EXTENDER_INV     ) == EXTENDER_INV     ) aList.add(Chat.CYAN + LH.get(TOOLTIP_EXTENDER_INVENTORY));
			if ((mModes & EXTENDER_TANK    ) == EXTENDER_TANK    ) aList.add(Chat.CYAN + LH.get(TOOLTIP_EXTENDER_TANK));
			if ((mModes & EXTENDER_REDSTONE) == EXTENDER_REDSTONE) aList.add(Chat.CYAN + LH.get(TOOLTIP_EXTENDER_REDSTONE));
			if ((mModes & EXTENDER_CONTROL ) == EXTENDER_CONTROL ) aList.add(Chat.CYAN + LH.get(TOOLTIP_EXTENDER_CONTROL));
			if ((mModes & EXTENDER_OTHER   ) == EXTENDER_OTHER   ) aList.add(Chat.CYAN + LH.get(TOOLTIP_EXTENDER_OTHER));
		}
		aList.add(Chat.ORANGE + LH.get(TOOLTIP_EXTENDER_EXCLUSIVE));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			byte oRedstoneIn = mRedstoneIn, oRedstoneOut = mRedstoneOut, oComparator = mComparator;
			if ((mModes & EXTENDER_REDSTONE) == 0) {
				mComparator = mRedstoneIn = mRedstoneOut = 0;
			} else {
				mRedstoneIn = getRedstoneIncoming  (mFacing);
				mComparator = getComparatorIncoming(mFacing);
				mRedstoneOut = 0;
				for (byte tSide : ALL_SIDES_VALID_BUT[mFacing]) mRedstoneOut = (byte)Math.max(mRedstoneOut, getRedstoneIncoming(tSide));
			}
			if (oRedstoneIn != mRedstoneIn || oRedstoneOut != mRedstoneOut || oComparator != mComparator) causeBlockUpdate();
		}
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		if (aSide == mFacing) return BlockTextureMulti.get(BlockTextureDefault.get(mTextures[0], mRGBa), BlockTextureDefault.get(mTextures[3]));
		if (aSide == mSecondFacing) return BlockTextureMulti.get(BlockTextureDefault.get(mTextures[1], mRGBa), BlockTextureDefault.get(mTextures[4]));
		return BlockTextureMulti.get(BlockTextureDefault.get(mTextures[2], mRGBa), BlockTextureDefault.get(mTextures[5]));
	}
	
	@Override
	public void adjacentInventoryUpdated(byte aSide, IInventory aTileEntity) {
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<TileEntity> tDelegate = getAdjacentTileEntity(getExtenderTargetSide(aSide), F, T);
			if (tDelegate.mTileEntity instanceof ITileEntityAdjacentInventoryUpdatable) ((ITileEntityAdjacentInventoryUpdatable)tDelegate.mTileEntity).adjacentInventoryUpdated(tDelegate.mSideOfTileEntity, aTileEntity);
		}
	}
	
	@Override public boolean useInversePlacementRotation() {return T;}
	@Override public String getTileEntityName() {return "gt.multitileentity.extender";}
	
	// Relay TileEntities
	
	@Override
	public DelegatorTileEntity<TileEntity> getDelegateTileEntity(byte aSide) {
		if ((mModes & EXTENDER_ALL) == EXTENDER_ALL) return getAdjacentTileEntity(getExtenderTargetSide(aSide), F, T);
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<TileEntity> rDelegator = getAdjacentTileEntity(getExtenderTargetSide(aSide), F, T);
			if (rDelegator.mTileEntity instanceof ITileEntityItemPipe) return rDelegator;
		}
		return delegator(aSide);
	}
	
	@Override
	public boolean isExtender(byte aSide) {
		return T;
	}
	
	// Relay Redstone
	
	@Override
	public int getComparatorInputOverride(byte aSide) {
		return mComparator;
	}
	
	@Override
	public byte isProvidingWeakPower2(byte aSide) {
		return OPOS[aSide] == mFacing ? mRedstoneOut : mRedstoneIn;
	}
	
	// Relay Inventories
	
	public byte mLastSide = SIDE_UNKNOWN;
	
	@Override
	public ItemStack decrStackSize(int aSlot, int aDecrement) {
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.decrStackSize(aSlot, aDecrement);
		}
		return null;
	}
	@Override
	public ItemStack getStackInSlotOnClosing(int aSlot) {
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.getStackInSlotOnClosing(aSlot);
		}
		return null;
	}
	@Override
	public ItemStack getStackInSlot(int aSlot) {
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.getStackInSlot(aSlot);
		}
		return null;
	}
	@Override
	public String getInventoryName() {
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.getInventoryName();
		}
		return super.getInventoryName();
	}
	@Override
	public int getSizeInventory() {
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.getSizeInventory();
		}
		return 0;
	}
	@Override
	public int getInventoryStackLimit() {
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.getInventoryStackLimit();
		}
		return 0;
	}
	@Override
	public void setInventorySlotContents(int aSlot, ItemStack aStack) {
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity != null) tTileEntity.mTileEntity.setInventorySlotContents(aSlot, aStack);
		}
	}
	@Override
	public boolean hasCustomInventoryName() {
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.hasCustomInventoryName();
		}
		return getCustomName() != null;
	}
	@Override
	public boolean isItemValidForSlot(int aSlot, ItemStack aStack) {
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.isItemValidForSlot(aSlot, aStack);
		}
		return F;
	}
	
	// Relay Sided Inventories
	
	@Override
	public int[] getAccessibleSlotsFromSide2(byte aSide) {
		mLastSide = aSide;
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity instanceof ISidedInventory) return ((ISidedInventory)tTileEntity.mTileEntity).getAccessibleSlotsFromSide(tTileEntity.mSideOfTileEntity);
			if (tTileEntity.mTileEntity != null) return UT.Code.getAscendingArray(tTileEntity.mTileEntity.getSizeInventory());
		}
		return ZL_INTEGER;
	}
	@Override
	public boolean canInsertItem2(int aSlot, ItemStack aStack, byte aSide) {
		mLastSide = aSide;
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity instanceof ISidedInventory) return ((ISidedInventory)tTileEntity.mTileEntity).canInsertItem(aSlot, aStack, tTileEntity.mSideOfTileEntity);
			if (tTileEntity.mTileEntity != null) return T;
		}
		return F;
	}
	@Override
	public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {
		mLastSide = aSide;
		if ((mModes & EXTENDER_INV) != 0) {
			DelegatorTileEntity<IInventory> tTileEntity = getAdjacentInventory(getExtenderTargetSide(mLastSide), F, T);
			if (tTileEntity.mTileEntity instanceof ISidedInventory) return ((ISidedInventory)tTileEntity.mTileEntity).canExtractItem(aSlot, aStack, tTileEntity.mSideOfTileEntity);
			if (tTileEntity.mTileEntity != null) return T;
		}
		return F;
	}
	
	// Relay Tanks
	
	@Override
	public int fill(ForgeDirection aDirection, FluidStack aFluid, boolean doFill) {
		if ((mModes & EXTENDER_TANK) != 0) {
			byte aSide = UT.Code.side(aDirection);
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidFill(aSide, mCovers, aSide, aFluid)) return 0;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.fill(tTileEntity.getForgeSideOfTileEntity(), aFluid, doFill);
		}
		return 0;
	}
	@Override
	public FluidStack drain(ForgeDirection aDirection, FluidStack aFluid, boolean doDrain) {
		if ((mModes & EXTENDER_TANK) != 0) {
			byte aSide = UT.Code.side(aDirection);
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidDrain(aSide, mCovers, aSide, aFluid)) return null;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.drain(tTileEntity.getForgeSideOfTileEntity(), aFluid, doDrain);
		}
		return null;
	}
	@Override
	public FluidStack drain(ForgeDirection aDirection, int maxDrain, boolean doDrain) {
		if ((mModes & EXTENDER_TANK) != 0) {
			byte aSide = UT.Code.side(aDirection);
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidDrain(aSide, mCovers, aSide, null)) return null;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.drain(tTileEntity.getForgeSideOfTileEntity(), maxDrain, doDrain);
		}
		return null;
	}
	@Override
	public boolean canFill(ForgeDirection aDirection, Fluid aFluid) {
		if ((mModes & EXTENDER_TANK) != 0) {
			byte aSide = UT.Code.side(aDirection);
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidFill(aSide, mCovers, aSide, FL.make(aFluid, 1))) return F;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.canFill(tTileEntity.getForgeSideOfTileEntity(), aFluid);
		}
		return F;
	}
	@Override
	public boolean canDrain(ForgeDirection aDirection, Fluid aFluid) {
		if ((mModes & EXTENDER_TANK) != 0) {
			byte aSide = UT.Code.side(aDirection);
			if (hasCovers() && SIDES_VALID[aSide] && mCovers.mBehaviours[aSide] != null && mCovers.mBehaviours[aSide].interceptFluidDrain(aSide, mCovers, aSide, FL.make(aFluid, 1))) return F;
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.canDrain(tTileEntity.getForgeSideOfTileEntity(), aFluid);
		}
		return F;
	}
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection aDirection) {
		if ((mModes & EXTENDER_TANK) != 0) {
			DelegatorTileEntity<IFluidHandler> tTileEntity = getAdjacentTank(getExtenderTargetSide(UT.Code.side(aDirection)), F, T);
			if (tTileEntity.mTileEntity != null) return tTileEntity.mTileEntity.getTankInfo(tTileEntity.getForgeSideOfTileEntity());
		}
		return ZL_FLUIDTANKINFO;
	}
	
	// Relay Control Covers and such
	
	public boolean getStateRunningPossible() {
		if ((mModes & EXTENDER_CONTROL) != 0) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(getExtenderTargetSide(SIDE_UNDEFINED), F, T);
			if (tTileEntity.mTileEntity instanceof ITileEntityRunningPossible) return ((ITileEntityRunningPossible)tTileEntity.mTileEntity).getStateRunningPossible();
		}
		return F;
	}
	
	public boolean getStateRunningPassively() {
		if ((mModes & EXTENDER_CONTROL) != 0) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(getExtenderTargetSide(SIDE_UNDEFINED), F, T);
			if (tTileEntity.mTileEntity instanceof ITileEntityRunningPassively) return ((ITileEntityRunningPassively)tTileEntity.mTileEntity).getStateRunningPassively();
		}
		return F;
	}
	
	public boolean getStateRunningActively() {
		if ((mModes & EXTENDER_CONTROL) != 0) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(getExtenderTargetSide(SIDE_UNDEFINED), F, T);
			if (tTileEntity.mTileEntity instanceof ITileEntityRunningActively) return ((ITileEntityRunningActively)tTileEntity.mTileEntity).getStateRunningActively();
		}
		return F;
	}
	
	public boolean getStateRunningSuccessfully() {
		if ((mModes & EXTENDER_CONTROL) != 0) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(getExtenderTargetSide(SIDE_UNDEFINED), F, T);
			if (tTileEntity.mTileEntity instanceof ITileEntityRunningSuccessfully) return ((ITileEntityRunningSuccessfully)tTileEntity.mTileEntity).getStateRunningSuccessfully();
		}
		return F;
	}
	
	public boolean getStateOnOff() {
		if ((mModes & EXTENDER_CONTROL) != 0) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(getExtenderTargetSide(SIDE_UNDEFINED), F, T);
			if (tTileEntity.mTileEntity instanceof ITileEntitySwitchableOnOff) return ((ITileEntitySwitchableOnOff)tTileEntity.mTileEntity).getStateOnOff();
		}
		return F;
	}
	
	public byte getStateMode() {
		if ((mModes & EXTENDER_CONTROL) != 0) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(getExtenderTargetSide(SIDE_UNDEFINED), F, T);
			if (tTileEntity.mTileEntity instanceof ITileEntitySwitchableMode) return ((ITileEntitySwitchableMode)tTileEntity.mTileEntity).getStateMode();
		}
		return 0;
	}
	
	public boolean setStateOnOff(boolean aOnOff) {
		if ((mModes & EXTENDER_CONTROL) != 0) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(getExtenderTargetSide(SIDE_UNDEFINED), F, T);
			if (tTileEntity.mTileEntity instanceof ITileEntitySwitchableOnOff) return ((ITileEntitySwitchableOnOff)tTileEntity.mTileEntity).setStateOnOff(aOnOff);
		}
		return F;
	}
	
	public byte setStateMode(byte aMode) {
		if ((mModes & EXTENDER_CONTROL) != 0) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(getExtenderTargetSide(SIDE_UNDEFINED), F, T);
			if (tTileEntity.mTileEntity instanceof ITileEntitySwitchableMode) return ((ITileEntitySwitchableMode)tTileEntity.mTileEntity).setStateMode(aMode);
		}
		return 0;
	}
	
	public long getProgressValue(byte aSide) {
		if ((mModes & EXTENDER_CONTROL) != 0) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity instanceof ITileEntityProgress) return ((ITileEntityProgress)tTileEntity.mTileEntity).getProgressValue(tTileEntity.mSideOfTileEntity);
		}
		return 0;
	}
	
	public long getProgressMax(byte aSide) {
		if ((mModes & EXTENDER_CONTROL) != 0) {
			DelegatorTileEntity<TileEntity> tTileEntity = getAdjacentTileEntity(getExtenderTargetSide(aSide), F, T);
			if (tTileEntity.mTileEntity instanceof ITileEntityProgress) return ((ITileEntityProgress)tTileEntity.mTileEntity).getProgressMax(tTileEntity.mSideOfTileEntity);
		}
		return 0;
	}
	
	
	public byte getExtenderTargetSide(byte aSide) {return aSide == mFacing ? mSecondFacing : mFacing;}
	
	@Override public boolean isUseableByPlayer(EntityPlayer aPlayer) {return aPlayer.getDistanceSq(xCoord+0.5, yCoord+0.5, zCoord+0.5) <= 64;}
	@Override public void openInventory() {/**/}
	@Override public void closeInventory() {/**/}
	@Override public boolean canDrop(int aInventorySlot) {return F;}
}
