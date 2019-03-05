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

package gregtech.tileentity.multiblocks;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.code.TagData;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.fluid.FluidTankGT;
import gregapi.tileentity.ITileEntityUnloadable;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.tileentity.multiblocks.IMultiBlockFluidHandler;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase11MultiBlockConverter;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityLargeTurbineSteam extends TileEntityBase11MultiBlockConverter implements IMultiBlockFluidHandler, IFluidHandler, ITileEntitySwitchableOnOff {
	public FluidTankGT[] mTanks = new FluidTankGT[] {new FluidTankGT(Integer.MAX_VALUE), new FluidTankGT(Integer.MAX_VALUE)};
	public long mSteamCounter = 0, mEnergyProducedNextTick = 0; 
	public short mTurbineWalls = 18022;
	public static final int STEAM_PER_WATER = 170;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_DESIGN)) mTurbineWalls = aNBT.getShort(NBT_DESIGN);
		if (aNBT.hasKey(NBT_ENERGY_SU)) mSteamCounter = aNBT.getLong(NBT_ENERGY_SU);
		if (aNBT.hasKey(NBT_OUTPUT_SU)) mEnergyProducedNextTick = aNBT.getLong(NBT_OUTPUT_SU);
		
		for (int i = 0; i < mTanks.length; i++) mTanks[i].readFromNBT(aNBT, NBT_TANK+"."+i);
		mTanks[0].setCapacity((int)UT.Code.bind_(1, Integer.MAX_VALUE, mEnergyIN.mMax*4));
		mTanks[1].setCapacity((int)UT.Code.bind_(1, Integer.MAX_VALUE, mEnergyIN.mMax)).setVoidExcess();
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
				byte tBits = 0;
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
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		return
		aX >= xCoord-(SIDE_X_NEG==mFacing?0:SIDE_X_POS==mFacing?3:1) &&
		aY >= yCoord-(SIDE_Y_NEG==mFacing?0:SIDE_Y_POS==mFacing?3:1) &&
		aZ >= zCoord-(SIDE_Z_NEG==mFacing?0:SIDE_Z_POS==mFacing?3:1) &&
		aX <= xCoord+(SIDE_X_POS==mFacing?0:SIDE_X_NEG==mFacing?3:1) &&
		aY <= yCoord+(SIDE_Y_POS==mFacing?0:SIDE_Y_NEG==mFacing?3:1) &&
		aZ <= zCoord+(SIDE_Z_POS==mFacing?0:SIDE_Z_NEG==mFacing?3:1);
	}
	
	static {
		LH.add("gt.tooltip.multiblock.steamturbine.1", "3x3x4 of the Block you crafted this one with");
		LH.add("gt.tooltip.multiblock.steamturbine.2", "Main centered on the 3x3 facing outwards");
		LH.add("gt.tooltip.multiblock.steamturbine.3", "Input only possible at Main Block");
		LH.add("gt.tooltip.multiblock.steamturbine.4", "Distilled Water can be pumped out at Bottom Layer");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.steamturbine.1"));
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
	public void doConversion(long aTimer) {
		if (mEnergyProducedNextTick > 0) {
			mStorage.mEnergy += mEnergyProducedNextTick;
			mEnergyProducedNextTick = 0;
		} else if (mTanks[0].getFluidAmount() >= UT.Code.bindInt(getEnergySizeInputMin(mEnergyIN.mType, SIDE_ANY)) * 2) {
			int tSteam = mTanks[0].getFluidAmount();
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
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (mStructureOkay) switch(mFacing) {
		case SIDE_X_NEG: case SIDE_X_POS: return box(aBlock, -0.01, -0.99, -0.99,  1.01,  1.99,  1.99);
		case SIDE_Y_NEG: case SIDE_Y_POS: return box(aBlock, -0.99, -0.01, -0.99,  1.99,  1.01,  1.99);
		case SIDE_Z_NEG: case SIDE_Z_POS: return box(aBlock, -0.99, -0.99, -0.01,  1.99,  1.99,  1.01);
		}
		return F;
	}
	
	public ITileEntityUnloadable mEmitter = null;
	
	@Override public TileEntity getEmittingTileEntity() {if (mEmitter == null || mEmitter.isDead()) {mEmitter = null; TileEntity tTileEntity = getTileEntityAtSideAndDistance(OPPOSITES[mFacing], 3); if (tTileEntity instanceof ITileEntityUnloadable) mEmitter = (ITileEntityUnloadable)tTileEntity;} return mEmitter == null ? this : (TileEntity)mEmitter;}
	@Override public byte getEmittingSide() {return OPPOSITES[mFacing];}
	@Override public boolean isInput (byte aSide) {return aSide == mFacing;}
	@Override public boolean isOutput(byte aSide) {return aSide == OPPOSITES[mFacing];}
	
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_VALID;}
	
	@Override protected IFluidTank getFluidTankFillable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToFill) {return !mStopped && UT.Fluids.steam(aFluidToFill) ? mTanks[0] : null;}
	@Override protected IFluidTank getFluidTankDrainable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToDrain) {return mTanks[1];}
	@Override protected IFluidTank[] getFluidTanks(MultiTileEntityMultiBlockPart aPart, byte aSide) {return mTanks;}
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return !mStopped && UT.Fluids.steam(aFluidToFill) ? mTanks[0] : null;}
	@Override protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return mTanks[1];}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTanks;}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting && mEnergyOUT.isType(aEnergyType);}
	@Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return F;}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.turbine.steam";}
}
