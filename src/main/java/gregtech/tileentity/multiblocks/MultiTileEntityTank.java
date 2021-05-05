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

import gregapi.data.CS.GarbageGT;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.fluid.FluidTankGT;
import gregapi.tileentity.ITileEntityFunnelAccessible;
import gregapi.tileentity.ITileEntityTapAccessible;
import gregapi.tileentity.multiblocks.IMultiBlockFluidHandler;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockBase;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public abstract class MultiTileEntityTank extends TileEntityBase10MultiBlockBase implements IMultiBlockFluidHandler, IFluidHandler, ITileEntityFunnelAccessible, ITileEntityTapAccessible {
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
		mTank.setCapacity(aNBT.getLong(NBT_TANK_CAPACITY));
		mTank.readFromNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		mTank.writeToNBT(aNBT, NBT_TANK);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + mTank.contentcap());
		aList.add(Chat.ORANGE   + LH.get(LH.NO_GUI_FUNNEL_TAP_TO_TANK));
		aList.add(Chat.ORANGE   + LH.get(LH.NO_POWER_CONDUCTING_FLUIDS));
		if (onlySimple()) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_ONLY_SIMPLE));
		if (mGasProof   ) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_GASPROOF));
		if (mAcidProof  ) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_ACIDPROOF));
		if (mPlasmaProof) aList.add(Chat.ORANGE + LH.get(LH.TOOLTIP_PLASMAPROOF));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_MELTDOWN) + " (" + mMaterial.mMeltingPoint + " K)");
		super.addToolTips(aList, aStack, aF3_H);
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
		aChatReturn.add(mTank.content("Tank is empty"));
	}
	
	public boolean allowFluid(FluidStack aFluid) {
		return !FL.powerconducting(aFluid) && FL.temperature(aFluid) < mMaterial.mMeltingPoint && (!onlySimple() || FL.simple(aFluid));
	}
	
	public boolean onlySimple() {return F;}
	
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
