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
		int tX = getOffsetXN(mFacing, 2), tY = yCoord, tZ = getOffsetZN(mFacing, 2);
		if (worldObj.blockExists(tX-9, tY, tZ-9) && worldObj.blockExists(tX+9, tY, tZ-9) && worldObj.blockExists(tX-9, tY, tZ+9) && worldObj.blockExists(tX+9, tY, tZ+9)) {
			boolean tSuccess = T;
			
			int tVersatile = 3, tLogic = 12, tControl = 12;
			
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) for (int k = -2; k <= 2; k++) {
				if (i*i + j*j + k*k < 4) {
					if (ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18200, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) {
						tVersatile--;
					} else if (ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18201, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) {
						tLogic--;
					} else if (ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18202, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) {
						tControl--;
					} else {
						tSuccess = F;
					}
				} else if (i*i + j*j + k*k > 6 || (j == 0 && (((i == -2 || i == 2) && k == 0) || (((k == -2 || k == 2) && i == 0))))) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18008, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				} else {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				}
			}
			
			if (tVersatile > 0 || tLogic > 0 || tControl > 0) tSuccess = F;
			
			if (mFacing != SIDE_X_NEG) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-3, tY, tZ  , 18008, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-4, tY, tZ  , 18008, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			}
			if (mFacing != SIDE_X_POS) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+3, tY, tZ  , 18008, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+4, tY, tZ  , 18008, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			}
			if (mFacing != SIDE_Z_NEG) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ-3, 18008, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ-4, 18008, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			}
			if (mFacing != SIDE_Z_POS) {
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ+3, 18008, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY, tZ+4, 18008, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			}
			
			tX -= 9; tZ -= 9;
			
			for (int i = 0; i < 19; i++) for (int j = 0; j < 19; j++) {
				if (OCTAGONS[0][i][j]) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-1, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
					if ((i == 9 && (j == 0 || j == 18)) || (j == 9 && (i == 0 || i == 18))) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18003, getMultiTileEntityRegistryID(), 2, MultiTileEntityMultiBlockPart.ONLY_ENERGY_OUT)) tSuccess = F;
					} else {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18003, getMultiTileEntityRegistryID(), mActive ? 6 : 5, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
					}
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+1, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
				}
				if (OCTAGONS[1][i][j]) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-2, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-1, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18045, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+1, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+2, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
				}
				if (OCTAGONS[2][i][j]) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-2, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY-1, tZ+j, 18045, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY  , tZ+j, 18002, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+1, tZ+j, 18045, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
					
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+2, tZ+j, 18003, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ITEM_FLUID)) tSuccess = F;
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
		LH.add("gt.tooltip.multiblock.fusionreactor.1", "For Assembly Instructions read the Manual in the GUI.");
		LH.add("gt.tooltip.multiblock.fusionreactor.2", "144 Iridium Coils, 576 Regular Tungstensteel Walls, 50 Ventilation Units.");
		LH.add("gt.tooltip.multiblock.fusionreactor.3", "36 Regular Stainless Steel Walls, 53 Galvanized Steel Walls.");
		LH.add("gt.tooltip.multiblock.fusionreactor.4", "3 Versatile, 12 Logic and 12 Control Quadcore Processing Units.");
		LH.add("gt.tooltip.multiblock.fusionreactor.5", "Energy Output at the Electric Interfaces");
		LH.add("gt.tooltip.multiblock.fusionreactor.6", "Laser Input at the 'Glass' Ring");
		LH.add("gt.tooltip.multiblock.fusionreactor.7", "Items and Fluids are handeled at the normal Walls");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN  + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fusionreactor.1"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fusionreactor.2"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fusionreactor.3"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fusionreactor.4"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fusionreactor.5"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fusionreactor.6"));
		aList.add(Chat.WHITE + LH.get("gt.tooltip.multiblock.fusionreactor.7"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing, 2), tY = yCoord-2, tZ = getOffsetZN(mFacing, 2);
		return aX >= tX - 9 && aY >= tY && aZ >= tZ - 9 && aX <= tX + 9 && aY <= tY + 5 && aZ <= tZ + 9;
	}
	
	@Override
	public DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput) {
		return null;
	}
	
	@Override
	public DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide) {
		return null;
	}
	
	@Override
	public void doOutputEnergy() {
		int tX = getOffsetXN(mFacing, 2), tY = yCoord, tZ = getOffsetZN(mFacing, 2);
		for (byte tSide : ALL_SIDES_HORIZONTAL) if (ITileEntityEnergy.Util.insertEnergyInto(mEnergyTypeEmitted, mOutputEnergy, 1, this, WD.te(worldObj, tX+OFFSETS_X[tSide]*10, tY, tZ+OFFSETS_Z[tSide]*10, OPPOSITES[tSide], F)) > 0) return;
	}
	
	@Override public DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide) {return null;}
	@Override public DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide) {return null;}
	
	@Override public boolean refreshStructureOnActiveStateChange() {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.fusionreactor";}
}
