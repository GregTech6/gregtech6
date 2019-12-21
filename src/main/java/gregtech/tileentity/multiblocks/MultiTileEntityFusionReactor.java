/**
 * Copyright (c) 2019 Gregorius Techneticies
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
public class MultiTileEntityFusionReactor extends TileEntityBase10MultiBlockMachine {
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing, 2)-9, tY = yCoord, tZ = getOffsetZN(mFacing, 2)-9;
		if (worldObj.blockExists(tX, tY, tZ) && worldObj.blockExists(tX+18, tY, tZ) && worldObj.blockExists(tX, tY, tZ+18) && worldObj.blockExists(tX+18, tY, tZ+18)) {
			boolean tSuccess = T;
			
			for (int i = 0; i < 19; i++) for (int j = 0; j < 19; j++) {
				if (OCTAGONS[0][i][j]) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+1, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY)) tSuccess = F;
					if ((i == 0 && (j == 9 || j == -9)) || (j == 0 && (i == 9 || i == -9))) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+2, tZ+j, 18003, getMultiTileEntityRegistryID(), 2, MultiTileEntityMultiBlockPart.ONLY_ENERGY_OUT)) tSuccess = F;
					} else {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+2, tZ+j, 18003, getMultiTileEntityRegistryID(), mActive ? 6 : 5, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					}
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+3, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY)) tSuccess = F;
				}
				if (OCTAGONS[1][i][j]) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+1, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+2, tZ+j, 18045, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+3, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+4, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY)) tSuccess = F;
				}
				if (OCTAGONS[2][i][j]) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+1, tZ+j, 18045, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (getAir(tX+i, tY+2, tZ+j)) worldObj.setBlockToAir(tX+i, tY+2, tZ+j); else tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+3, tZ+j, 18045, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+4, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID_ENERGY)) tSuccess = F;
				}
			}
			return tSuccess;
		}
		return mStructureOkay;
	}
	
	public static boolean[][][] OCTAGONS = {{
		{F,F,F,F,F,F,F,T,T,T,T,T,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,T,F,F,F,F,F,T,F,F,F,F,F,F},
		{F,F,F,F,F,T,F,F,F,F,F,F,F,T,F,F,F,F,F},
		{F,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,F},
		{F,F,F,T,F,F,F,T,T,T,T,T,F,F,F,T,F,F,F},
		{F,F,T,F,F,F,T,F,F,F,F,F,T,F,F,F,T,F,F},
		{F,T,F,F,F,T,F,F,F,F,F,F,F,T,F,F,F,T,F},
		{T,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,T},
		{T,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,T},
		{T,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,T},
		{T,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,T},
		{T,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,T},
		{F,T,F,F,F,T,F,F,F,F,F,F,F,T,F,F,F,T,F},
		{F,F,T,F,F,F,T,F,F,F,F,F,T,F,F,F,T,F,F},
		{F,F,F,T,F,F,F,T,T,T,T,T,F,F,F,T,F,F,F},
		{F,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,F},
		{F,F,F,F,F,T,F,F,F,F,F,F,F,T,F,F,F,F,F},
		{F,F,F,F,F,F,T,F,F,F,F,F,T,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,T,T,T,T,T,F,F,F,F,F,F,F},
	}, {
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,T,T,T,T,T,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,T,F,F,F,F,F,T,F,F,F,F,F,F},
		{F,F,F,F,F,T,F,T,T,T,T,T,F,T,F,F,F,F,F},
		{F,F,F,F,T,F,T,F,F,F,F,F,T,F,T,F,F,F,F},
		{F,F,F,T,F,T,F,F,F,F,F,F,F,T,F,T,F,F,F},
		{F,F,T,F,T,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
		{F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F},
		{F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F},
		{F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F},
		{F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F},
		{F,T,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,T,F},
		{F,F,T,F,T,F,F,F,F,F,F,F,F,F,T,F,T,F,F},
		{F,F,F,T,F,T,F,F,F,F,F,F,F,T,F,T,F,F,F},
		{F,F,F,F,T,F,T,F,F,F,F,F,T,F,T,F,F,F,F},
		{F,F,F,F,F,T,F,T,T,T,T,T,F,T,F,F,F,F,F},
		{F,F,F,F,F,F,T,F,F,F,F,F,T,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,T,T,T,T,T,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
	}, {
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,T,T,T,T,T,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,T,F,F,F,F,F,T,F,F,F,F,F,F},
		{F,F,F,F,F,T,F,F,F,F,F,F,F,T,F,F,F,F,F},
		{F,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,F},
		{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
		{F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F},
		{F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F},
		{F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F},
		{F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F},
		{F,F,T,F,F,F,F,F,F,F,F,F,F,F,F,F,T,F,F},
		{F,F,F,T,F,F,F,F,F,F,F,F,F,F,F,T,F,F,F},
		{F,F,F,F,T,F,F,F,F,F,F,F,F,F,T,F,F,F,F},
		{F,F,F,F,F,T,F,F,F,F,F,F,F,T,F,F,F,F,F},
		{F,F,F,F,F,F,T,F,F,F,F,F,T,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,T,T,T,T,T,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
		{F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F,F},
	}};
	
	static {
		LH.add("gt.tooltip.multiblock.fusionreactor.1", "For Construction Instructions read the Manual.");
		LH.add("gt.tooltip.multiblock.fusionreactor.2", "144 Iridium Coils, 576 Regular Tungstensteel Walls.");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN  + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fusionreactor.1"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fusionreactor.2"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing, 2), tY = yCoord, tZ = getOffsetZN(mFacing, 2);
		return aX >= tX - 9 && aY >= tY && aZ >= tZ - 9 && aX <= tX + 9 && aY <= tY + 5 && aZ <= tZ + 9;
	}
	
	@Override
	public DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput) {
		return getAdjacentTank(SIDE_BOTTOM);
	}
	
	@Override
	public DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide) {
		return getAdjacentTileEntity(SIDE_BOTTOM);
	}
	
	@Override
	public void doOutputEnergy() {
		int tX = getOffsetXN(mFacing, 2), tY = yCoord+2, tZ = getOffsetZN(mFacing, 2);
		for (byte tSide : ALL_SIDES_HORIZONTAL) if (ITileEntityEnergy.Util.insertEnergyInto(mEnergyTypeEmitted, mOutputEnergy, 1, this, WD.te(worldObj, tX+OFFSETS_X[tSide]*10, tY, tZ+OFFSETS_Z[tSide]*10, OPPOSITES[tSide], F)) > 0) return;
	}
	
	@Override public DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide) {return null;}
	
	@Override public boolean refreshStructureOnActiveStateChange() {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.fusionreactor";}
}
