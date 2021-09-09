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

package gregtech.tileentity.multiblocks;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityAdjacentOnOff;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockMachine;
import gregapi.util.WD;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityCentrifuge extends TileEntityBase10MultiBlockMachine {
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing)-1, tY = yCoord, tZ = getOffsetZN(mFacing)-1;
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ  , 18100, getMultiTileEntityRegistryID(), 1, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ  , 18100, getMultiTileEntityRegistryID(), 2, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ  , 18100, getMultiTileEntityRegistryID(), 3, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+1, 18100, getMultiTileEntityRegistryID(), 4, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+1, 18100, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN )) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+1, 18100, getMultiTileEntityRegistryID(), 5, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+2, 18100, getMultiTileEntityRegistryID(), 6, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+2, 18100, getMultiTileEntityRegistryID(), 7, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY  , tZ+2, 18100, getMultiTileEntityRegistryID(), 8, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ  , 18100, getMultiTileEntityRegistryID(), 1, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ  , 18100, getMultiTileEntityRegistryID(), 2, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ  , 18100, getMultiTileEntityRegistryID(), 3, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+1, 18100, getMultiTileEntityRegistryID(), 4, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+1, 18100, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN )) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+1, 18100, getMultiTileEntityRegistryID(), 5, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+1, tZ+2, 18100, getMultiTileEntityRegistryID(), 6, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+1, tZ+2, 18100, getMultiTileEntityRegistryID(), 7, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+2, tY+1, tZ+2, 18100, getMultiTileEntityRegistryID(), 8, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
			
			return tSuccess;
		}
		return mStructureOkay;
	}
	
	static {
		LH.add("gt.tooltip.multiblock.centrifuge.1", "3x3x2 of Centrifuge Parts");
		LH.add("gt.tooltip.multiblock.centrifuge.2", "Main Block centered on Side-Bottom and facing outwards");
		LH.add("gt.tooltip.multiblock.centrifuge.3", "Input and Output at any Blocks");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.centrifuge.1"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.centrifuge.2"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.centrifuge.3"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing), tY = yCoord, tZ = getOffsetZN(mFacing);
		return aX >= tX - 1 && aY >= tY && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 1 && aZ <= tZ + 1;
	}
	
	@Override
	public void updateAdjacentToggleableEnergySources() {
		DelegatorTileEntity<TileEntity>
		tDelegator = WD.te(worldObj, getOffsetXN(mFacing), yCoord-1, getOffsetZN(mFacing), SIDE_TOP, F);
		if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) {
			((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
		}
		tDelegator = WD.te(worldObj, getOffsetXN(mFacing), yCoord+2, getOffsetZN(mFacing), SIDE_BOTTOM, F);
		if (tDelegator.mTileEntity instanceof ITileEntityAdjacentOnOff && tDelegator.mTileEntity instanceof ITileEntityEnergy && ((ITileEntityEnergy)tDelegator.mTileEntity).isEnergyEmittingTo(mEnergyTypeAccepted, tDelegator.mSideOfTileEntity, T)) {
			((ITileEntityAdjacentOnOff)tDelegator.mTileEntity).setAdjacentOnOff(getStateOnOff());
		}
	}
	
	@Override
	public DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput) {
		return getAdjacentTank(SIDE_BOTTOM);
	}
	
	@Override
	public DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide) {
		return getAdjacentTileEntity(SIDE_BOTTOM);
	}
	
	@Override public DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide) {return null;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.centrifuge";}
}
