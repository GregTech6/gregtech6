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

import gregapi.data.CS.GarbageGT;
import gregapi.data.CS.SFX;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.fluid.FluidTankGT;
import gregapi.tileentity.ITileEntityFunnelAccessible;
import gregapi.tileentity.ITileEntityTapAccessible;
import gregapi.tileentity.multiblocks.IMultiBlockFluidHandler;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockBase;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntityTank3x3x3 extends TileEntityBase10MultiBlockBase implements IMultiBlockFluidHandler, IFluidHandler, ITileEntityFunnelAccessible, ITileEntityTapAccessible {
	public FluidTankGT mTank = new FluidTankGT(432000);
	public short mTankWalls = 18002;
	public boolean mGasProof = F, mAcidProof = F, mPlasmaProof = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_DESIGN)) mTankWalls = aNBT.getShort(NBT_DESIGN);
		if (aNBT.hasKey(NBT_GASPROOF)) mGasProof = aNBT.getBoolean(NBT_GASPROOF);
		if (aNBT.hasKey(NBT_ACIDPROOF)) mAcidProof = aNBT.getBoolean(NBT_ACIDPROOF);
		if (aNBT.hasKey(NBT_PLASMAPROOF)) mPlasmaProof = aNBT.getBoolean(NBT_PLASMAPROOF);
		mTank.setCapacity(aNBT.getInteger(NBT_TANK_CAPACITY));
		mTank.readFromNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		mTank.writeToNBT(aNBT, NBT_TANK);
	}
	
	static {
		LH.add("gt.tooltip.multiblock.tank3x3x3.1", "3x3x3 Hollow of the Block you crafted this one with");
		LH.add("gt.tooltip.multiblock.tank3x3x3.2", "This Block centered on Side/Top/Bottom and facing outwards");
		LH.add("gt.tooltip.multiblock.tank3x3x3.3", "Auto-Emits Fluids from the Main Block if not against Gravity");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + "Max: " + mTank.getCapacity() + " L");
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.tank3x3x3.1"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.tank3x3x3.2"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.tank3x3x3.3"));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_FUNNEL_TAP_TO_TANK));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_POWER_CONDUCTING_FLUIDS));
		if (mGasProof       ) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_GASPROOF));
		if (mAcidProof      ) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_ACIDPROOF));
		if (mPlasmaProof    ) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_PLASMAPROOF));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_MELTDOWN) + " (" + mMaterial.mMeltingPoint + " K)");
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing), tZ = getOffsetZN(mFacing);
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;
			for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
				if (i == 0 && j == 0 && k == 0) {
					if (getAir(tX+i, tY+j, tZ+k)) worldObj.setBlockToAir(tX+i, tY+j, tZ+k); else tSuccess = F;
				} else {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, mTankWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID)) tSuccess = F;
				}
			}
			return tSuccess;
		}
		return mStructureOkay;
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing), tZ = getOffsetZN(mFacing);
		return aX >= tX - 1 && aY >= tY - 1 && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 1 && aZ <= tZ + 1;
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_plunger)) return GarbageGT.trash(mTank, 1000);
		return 0;
	}
	
	@Override
	public void onMagnifyingGlass2(List<String> aChatReturn) {
		FluidStack tFluid = mTank.getFluid();
		if (tFluid == null) {
			aChatReturn.add("Tank is empty");
		} else {
			aChatReturn.add("Contains: " + tFluid.amount + " L of " + UT.Fluids.name(tFluid, T) + " (" + (UT.Fluids.gas(tFluid) ? "Gaseous" : "Liquid") + ")");
		}
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide && checkStructure(F)) {
			FluidStack tFluid = mTank.getFluid();
			if (tFluid != null && tFluid.amount > 0) {
				if (UT.Fluids.temperature(tFluid) >= mMaterial.mMeltingPoint && meltdown()) return;
				if (!mAcidProof && UT.Fluids.acid(tFluid)) {
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 0.5F, getCoords());
					GarbageGT.trash(mTank);
					int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing), tZ = getOffsetZN(mFacing);
					for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
						if (rng(3) == 0) worldObj.setBlockToAir(tX+i, tY+j, tZ+k);
					}
					setToAir();
					return;
				}
				if (!mPlasmaProof && UT.Fluids.plasma(tFluid)) {
					GarbageGT.trash(mTank);
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 1.0F, getCoords());
				} else
				if (!mGasProof && UT.Fluids.gas(tFluid)) {
					GarbageGT.trash(mTank);
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 1.0F, getCoords());
				} else
				if (!allowFluid(tFluid)) {
					GarbageGT.trash(mTank);
					UT.Sounds.send(worldObj, SFX.MC_FIZZ, 1.0F, 1.0F, getCoords());
				} else
				if (SIDES_HORIZONTAL[mFacing] || UT.Fluids.gas(tFluid) || (tFluid.getFluid().getDensity(tFluid)<0?SIDES_TOP:SIDES_BOTTOM)[mFacing]) {
					if (UT.Fluids.move(mTank, getAdjacentTileEntity(mFacing)) > 0) updateInventory();
				}
			}
		}
	}
	
	public boolean allowFluid(FluidStack aFluid) {
		return !UT.Fluids.powerconducting(aFluid) && UT.Fluids.temperature(aFluid) < mMaterial.mMeltingPoint;
	}
	
	public boolean meltdown() {
		int tX = getOffsetXN(mFacing), tY = getOffsetYN(mFacing), tZ = getOffsetZN(mFacing);
		for (int i = -1; i <= 1; i++) for (int j = -1; j <= 1; j++) for (int k = -1; k <= 1; k++) {
			WD.burn(worldObj, tX+i, tY+j, tZ+k, F, F);
			if (rng(4) == 0) worldObj.setBlock(tX+i, tY+j, tZ+k, Blocks.fire, 0, 3);
		}
		if (UT.Fluids.lava(mTank.getFluid()) && mTank.getFluidAmount() >= 1000) {
			mTank.remove(1000);
			GarbageGT.trash(mTank);
			worldObj.setBlock(tX, tY, tZ, Blocks.flowing_lava, 0, 3);
		} else {
			GarbageGT.trash(mTank);
			setToFire();
		}
		return T;
	}
	
	@Override
	public boolean breakBlock() {
		GarbageGT.trash(mTank);
		return super.breakBlock();
	}
	
	@Override
	public int funnelFill(byte aSide, FluidStack aFluid, boolean aDoFill) {
		return mTank.fill(aFluid, aDoFill);
	}
	
	@Override
	public FluidStack tapDrain(byte aSide, int aMaxDrain, boolean aDoDrain) {
		return mTank.drain(aMaxDrain, aDoDrain);
	}
	
	@Override protected IFluidTank getFluidTankFillable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToFill) {return mTank;}
	@Override protected IFluidTank getFluidTankDrainable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToDrain) {return mTank;}
	@Override protected IFluidTank[] getFluidTanks(MultiTileEntityMultiBlockPart aPart, byte aSide) {return mTank.AS_ARRAY;}
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return null;}
	@Override protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return mTank;}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTank.AS_ARRAY;}
}
