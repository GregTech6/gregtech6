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

package gregtech.tileentity.multiblocks;

import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.CS.*;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.fluid.FluidTankGT;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityLargeTurbineSteam extends MultiTileEntityLargeTurbine {
	public FluidTankGT[] mTanks = new FluidTankGT[] {new FluidTankGT(), new FluidTankGT()};
	public long mSteamCounter = 0, mEnergyProducedNextTick = 0; 
	public static final int STEAM_PER_WATER = 170;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_ENERGY_SU)) mSteamCounter = aNBT.getLong(NBT_ENERGY_SU);
		if (aNBT.hasKey(NBT_OUTPUT_SU)) mEnergyProducedNextTick = aNBT.getLong(NBT_OUTPUT_SU);

		for (int i = 0; i < mTanks.length; i++) mTanks[i].readFromNBT(aNBT, NBT_TANK+"."+i);
		mTanks[0].setCapacity(mEnergyIN.mMax*4);
		mTanks[1].setCapacity(mEnergyIN.mMax*4).setVoidExcess();
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY_SU, mSteamCounter);
		UT.NBT.setNumber(aNBT, NBT_OUTPUT_SU, mEnergyProducedNextTick);
		for (int i = 0; i < mTanks.length; i++) mTanks[i].writeToNBT(aNBT, NBT_TANK+"."+i);
	}
	
	@Override
	public boolean checkStructure2() {
		int
		tMinX = xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?3:1),
		tMinY = yCoord-(SIDE_Y_NEG==mFacing?0:SIDE_Y_POS==mFacing?3:1),
		tMinZ = zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?3:1),
		tMaxX = xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?3:1),
		tMaxY = yCoord+(SIDE_Y_POS==mFacing?0:SIDE_Y_NEG==mFacing?3:1),
		tMaxZ = zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?3:1),
		tOutX = getOffsetXN(mFacing, 3),
		tOutY = getOffsetYN(mFacing, 3),
		tOutZ = getOffsetZN(mFacing, 3);
		
		if (worldObj.blockExists(tMinX, tMinY, tMinZ) && worldObj.blockExists(tMaxX, tMaxY, tMaxZ)) {
			mEmitter = null;
			boolean tSuccess = T;
			for (int tX = tMinX; tX <= tMaxX; tX++) for (int tY = tMinY; tY <= tMaxY; tY++) for (int tZ = tMinZ; tZ <= tMaxZ; tZ++) {
				int tBits = 0;
				if (tX == tOutX && tY == tOutY && tZ == tOutZ) {
					tBits = MultiTileEntityMultiBlockPart.ONLY_ENERGY_OUT;
				} else {
					if (SIDES_AXIS_X[mFacing] && tX == xCoord || SIDES_AXIS_Y[mFacing] && tY == yCoord || SIDES_AXIS_Z[mFacing] && tZ == zCoord) {
						tBits = (tY == tMinY ? MultiTileEntityMultiBlockPart.ONLY_FLUID : MultiTileEntityMultiBlockPart.ONLY_FLUID_IN);
					} else {
						tBits = (tY == tMinY ? MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT : MultiTileEntityMultiBlockPart.NOTHING);
					}
				}
				if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX, tY, tZ, mTurbineWalls, getMultiTileEntityRegistryID(), tX == tOutX && tY == tOutY && tZ == tOutZ ? 3 : 0, tBits)) tSuccess = F;
			}
			return tSuccess;
		}
		return mStructureOkay;
	}
	
	static {
		LH.add("gt.tooltip.multiblock.steamturbine.1", "3x3x4 of 35 ");
		LH.add("gt.tooltip.multiblock.steamturbine.2", "Main centered on the 3x3 facing outwards");
		LH.add("gt.tooltip.multiblock.steamturbine.3", "Input only possible at frontal 3x3");
		LH.add("gt.tooltip.multiblock.steamturbine.4", "Distilled Water can be pumped out at Bottom Layer");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.steamturbine.1") + MultiTileEntityRegistry.getRegistry(getMultiTileEntityRegistryID()).getLocal(mTurbineWalls));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.steamturbine.2"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.steamturbine.3"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.steamturbine.4"));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public void addToolTipsEnergy(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTipsEnergy(aList, aStack, aF3_H);
		aList.add(Chat.ORANGE   + LH.get(LH.EMITS_USED_STEAM) + " ("+LH.get(LH.FACE_SIDES)+", 95%)");
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_plunger)) {
			if (mTanks[0].has()) return GarbageGT.trash(mTanks[0]);
			return GarbageGT.trash(mTanks[1]);
		}
		
		return 0;
	}
	
	@Override
	public void doConversion(long aTimer) {
		if (mEnergyProducedNextTick > 0) {
			mStorage.mEnergy += mEnergyProducedNextTick;
			mEnergyProducedNextTick = 0;
		} else if (!mStopped && mTanks[0].has(getEnergySizeInputMin(mEnergyIN.mType, SIDE_ANY) * 2)) {
			long tSteam = mTanks[0].amount();
			mSteamCounter += tSteam;
			mStorage.mEnergy += tSteam / 2;
			mEnergyProducedNextTick += tSteam / 2;
			mTanks[0].setEmpty();
			if (mSteamCounter >= STEAM_PER_WATER) {
				mTanks[1].fillAll(FL.DistW.make(mSteamCounter / STEAM_PER_WATER));
				mSteamCounter %= STEAM_PER_WATER;
			}
		}
		super.doConversion(aTimer);
	}
	
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return !mStopped && FL.steam(aFluidToFill) ? mTanks[0] : null;}
	@Override protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return mTanks[1];}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTanks;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.turbine.steam";}
}
