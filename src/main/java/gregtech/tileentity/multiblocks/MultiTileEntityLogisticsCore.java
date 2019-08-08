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

import java.util.Collection;
import java.util.List;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.ItemStackSet;
import gregapi.code.TagData;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.fluid.FluidTankGT;
import gregapi.tileentity.connectors.ITileEntityLogistics;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import gregapi.tileentity.multiblocks.IMultiBlockEnergy;
import gregapi.tileentity.multiblocks.IMultiBlockFluidHandler;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockBase;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityLogisticsCore extends TileEntityBase10MultiBlockBase implements ITileEntityEnergy, ITileEntityLogistics, ITileEntityEnergyDataCapacitor, IMultiBlockEnergy, IMultiBlockFluidHandler, IFluidHandler {
	public static final int MAX_STORAGE_CPU_COUNT = 108;
	
	public long mEnergy = 0;
	public TagData mEnergyTypeAccepted = TD.Energy.EU;
	public int mCPU_Logic = 0, mCPU_Control = 0, mCPU_Storage = 0, mCPU_Conversion = 0;
	public FluidTankGT[] mTanks = new FluidTankGT[MAX_STORAGE_CPU_COUNT];
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyTypeAccepted = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
		mEnergy = aNBT.getLong(NBT_ENERGY);
		mCPU_Logic = aNBT.getInteger("gt.cpu.logic");
		mCPU_Control = aNBT.getInteger("gt.cpu.control");
		mCPU_Storage = aNBT.getInteger("gt.cpu.storage");
		mCPU_Conversion = aNBT.getInteger("gt.cpu.conversion");
		for (int i = 0; i < mTanks.length; i++) mTanks[i] = new FluidTankGT(16000).readFromNBT(aNBT, NBT_TANK+"."+i);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, "gt.cpu.logic", mCPU_Logic);
		UT.NBT.setNumber(aNBT, "gt.cpu.control", mCPU_Control);
		UT.NBT.setNumber(aNBT, "gt.cpu.storage", mCPU_Storage);
		UT.NBT.setNumber(aNBT, "gt.cpu.conversion", mCPU_Conversion);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		for (int i = 0; i < mTanks.length; i++) mTanks[i].writeToNBT(aNBT, NBT_TANK+"." +i);
	}
	
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing, 2), tY = getOffsetYN(mFacing, 2), tZ = getOffsetZN(mFacing, 2);
		if (worldObj.blockExists(tX-2, tY, tZ-2) && worldObj.blockExists(tX+2, tY, tZ-2) && worldObj.blockExists(tX-2, tY, tZ+2) && worldObj.blockExists(tX+2, tY, tZ+2)) {
			boolean tSuccess = T;
			mCPU_Logic = 0;
			mCPU_Control = 0;
			mCPU_Storage = 0;
			mCPU_Conversion = 0;
			for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) for (int k = -2; k <= 2; k++) {
				if (i*i + j*j + k*k < 4) {
					if (ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18200, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) {
						mCPU_Logic++;
						mCPU_Control++;
						mCPU_Storage++;
						mCPU_Conversion++;
					} else if (ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18201, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) {
						mCPU_Logic += 4;
					} else if (ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18202, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) {
						mCPU_Control += 4;
					} else if (ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18203, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) {
						mCPU_Storage += 4;
					} else if (ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18204, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) {
						mCPU_Conversion += 4;
					} else {
						tSuccess = F;
					}
				} else if (i*i + j*j + k*k > 6) {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18008, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_LOGISTICS & MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
				} else {
					if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18299, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_LOGISTICS)) tSuccess = F;
				}
			}
			if (mCPU_Storage > MAX_STORAGE_CPU_COUNT) mCPU_Storage = MAX_STORAGE_CPU_COUNT;
			return tSuccess && mCPU_Logic > 0 && mCPU_Control > 0 && mCPU_Storage > 0 && mCPU_Conversion > 0;
		}
		return mStructureOkay;
	}
	
	static {
		LH.add("gt.tooltip.multiblock.logisticscore.1", "5x5x5 Frame made out of Galvanized Steel Walls (Power Input Here)");
		LH.add("gt.tooltip.multiblock.logisticscore.2", "3x3x3 Core of any Multiblock Processor Units (Customizable)");
		LH.add("gt.tooltip.multiblock.logisticscore.3", "The Six 3x3 Walls need to be Ventilation Units");
		LH.add("gt.tooltip.multiblock.logisticscore.4", "Main centered at any Side facing outwards");
		LH.add("gt.tooltip.multiblock.logisticscore.5", "At least one of each Processor Type needed (Or use a Versatile one)");
		LH.add("gt.tooltip.multiblock.logisticscore.6", "Logic Processors increase Operation Count by 1");
		LH.add("gt.tooltip.multiblock.logisticscore.7", "Control Processors increase Maximum Network Range by 1m");
		LH.add("gt.tooltip.multiblock.logisticscore.8", "Storage Processors increase Buffer Size by 1 Stack or 16000L");
		LH.add("gt.tooltip.multiblock.logisticscore.9", "Conversion Processors increase Throughput by 1 Stack or 16000L");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.logisticscore.1"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.logisticscore.2"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.logisticscore.3"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.logisticscore.4"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.logisticscore.5"));
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.multiblock.logisticscore.6"));
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.multiblock.logisticscore.7"));
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.multiblock.logisticscore.8"));
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.multiblock.logisticscore.9"));
		aList.add(Chat.GREEN    + LH.get(LH.ENERGY_INPUT) + ": " + Chat.WHITE + "512 to 16384 " + mEnergyTypeAccepted.getChatFormat() + mEnergyTypeAccepted.getLocalisedNameShort() + Chat.WHITE + "/t");
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing, 2), tY = getOffsetYN(mFacing, 2), tZ = getOffsetZN(mFacing, 2);
		return aX >= tX - 2 && aY >= tY - 2 && aZ >= tZ - 2 && aX <= tX + 2 && aY <= tY + 2 && aZ <= tZ + 2;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			
			if (SERVER_TIME % 20 == 19 && checkStructure(F)) {
				// Scan for Network.
				// TODO: Make sure Covers that are turned off are ignored!
				// TODO: Interface for Mass Storages and Filter Blocks.
				// TODO: Filtered Priority Mode.
				@SuppressWarnings("unused")
				int tX = getOffsetXN(mFacing, 2), tY = getOffsetYN(mFacing, 2), tZ = getOffsetZN(mFacing, 2);
				
				@SuppressWarnings("unused")
				List<DelegatorTileEntity<TileEntity>>
				  tStackImportsGeneric  = new ArrayListNoNulls<>()
				, tStackImportsSemi     = new ArrayListNoNulls<>()
				, tStackImportsFiltered = new ArrayListNoNulls<>()
				, tStackExportsGeneric  = new ArrayListNoNulls<>()
				, tStackExportsSemi     = new ArrayListNoNulls<>()
				, tStackExportsFiltered = new ArrayListNoNulls<>()
				, tStackStorageGeneric  = new ArrayListNoNulls<>()
				, tStackStorageSemi     = new ArrayListNoNulls<>()
				, tStackStorageFiltered = new ArrayListNoNulls<>()
				, tStackDumps           = new ArrayListNoNulls<>()
				, tFluidImportsGeneric  = new ArrayListNoNulls<>()
				, tFluidImportsSemi     = new ArrayListNoNulls<>()
				, tFluidImportsFiltered = new ArrayListNoNulls<>()
				, tFluidExportsGeneric  = new ArrayListNoNulls<>()
				, tFluidExportsSemi     = new ArrayListNoNulls<>()
				, tFluidExportsFiltered = new ArrayListNoNulls<>()
				, tFluidStorageGeneric  = new ArrayListNoNulls<>()
				, tFluidStorageSemi     = new ArrayListNoNulls<>()
				, tFluidStorageFiltered = new ArrayListNoNulls<>()
				, tFluidDumps           = new ArrayListNoNulls<>()
				;
				
				
				
				
				
				
				
				for (int i = 0; i < mCPU_Logic; i++) {
					
					// Something along these lines, but I still need to figure out the Filtering better.
					
					boolean tBreak = F;
					for (DelegatorTileEntity<TileEntity> tImport : tFluidImportsGeneric) {
						for (DelegatorTileEntity<TileEntity> tExport : tFluidExportsFiltered) {
							// TODO REQUIRES FILTER!
							if (FL.move(tImport, tExport, 16000L * mCPU_Conversion) > 0) {tBreak = T; break;}
						}
						if (tBreak) break;
					}
					if (tBreak) continue;
					for (DelegatorTileEntity<TileEntity> tImport : tStackImportsGeneric) {
						for (DelegatorTileEntity<TileEntity> tExport : tStackExportsFiltered) {
							for (int j = 0; j < mCPU_Conversion; j++) {
								// TODO REPLACE FILTER!
								if (ST.move(tImport, tExport, new ItemStackSet<>(ST.make(Blocks.air, 1, 0)), F, F, F, 64, 1, 64, 1) > 0) {tBreak = T; continue;}
								break;
							}
							if (tBreak) break;
						}
						if (tBreak) break;
					}
					if (tBreak) continue;
					
					
					
					
					
					
					// Generic  Import  -> Filtered Export
					// Filtered Import  -> Filtered Export
					// Generic  Import  -> Semi-Filtered Export (Mass Storages and Filter Blocks for example)
					// Filtered Import  -> Semi-Filtered Export (Mass Storages and Filter Blocks for example)
					// Generic  Import  -> Generic Export
					// Filtered Import  -> Generic Export
					// Generic  Import  -> Filtered Storage
					// Filtered Import  -> Filtered Storage
					// Generic  Import  -> Semi-Filtered Storage (Mass Storages for example)
					// Filtered Import  -> Semi-Filtered Storage (Mass Storages for example)
					// Generic  Import  -> Generic Storage
					// Filtered Import  -> Generic Storage
					// Generic  Storage -> Filtered Export
					// Filtered Storage -> Filtered Export
					// Generic  Storage -> Semi-Filtered Export (Mass Storages and Filter Blocks for example)
					// Filtered Storage -> Semi-Filtered Export (Mass Storages and Filter Blocks for example)
					// Generic  Storage -> Generic Export
					// Filtered Storage -> Generic Export
					// Generic  Storage -> Filtered Storage
					// Generic  Storage -> Semi-Filtered Storage (Mass Storages for example)
					// Unknown  Stuff   -> Dump Bus (that is where random Stuff goes, that got inserted by accident or so)
					
					break;
				}
			}
		}
	}
	
	@Override
	public void onMagnifyingGlass2(List<String> aChatReturn) {
		aChatReturn.add(mCPU_Logic + " Logic Processors");
		aChatReturn.add(mCPU_Control + " Control Processors");
		aChatReturn.add(mCPU_Storage + " Storage Processors");
		aChatReturn.add(mCPU_Conversion + " Conversion Processors");
	}
	
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_VALID;}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		if (mEnergy > 1000000) return 0;
		aSize = Math.abs(aSize);
		if (!aDoInject) return aAmount;
		if (aSize > getEnergySizeInputMax(aEnergyType, aSide)) {explode(6); return aAmount;}
		mEnergy += aAmount * aSize;
		return aAmount;
	}
	
	@Override public boolean canLogistics(byte aSide) {return T;}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && aEnergyType == mEnergyTypeAccepted;}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, F);}
	@Override public boolean isEnergyCapacitorType(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted;}
	@Override public long getEnergyDemanded(TagData aEnergyType, byte aSide, long aSize) {return 4096;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return 8192;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return   512;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return 16384;}
	@Override public long getEnergyStored(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted ? mEnergy : 0;}
	@Override public long getEnergyCapacity(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted ? 1000000 : 0;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	@Override public Collection<TagData> getEnergyCapacitorTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	
	@Override protected IFluidTank getFluidTankFillable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToFill) {return null;}
	@Override protected IFluidTank getFluidTankDrainable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToDrain) {return null;}
	@Override protected IFluidTank[] getFluidTanks(MultiTileEntityMultiBlockPart aPart, byte aSide) {return mTanks;}
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return null;}
	@Override protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return null;}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTanks;}
	
	// Inventory Stuff
	@Override public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {return new ItemStack[MAX_STORAGE_CPU_COUNT];}
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	@Override public int[] getAccessibleSlotsFromSide2(byte aSide) {return ZL_INTEGER;}
	@Override public boolean canInsertItem2 (int aSlot, ItemStack aStack, byte aSide) {return F;}
	@Override public boolean canExtractItem2(int aSlot, ItemStack aStack, byte aSide) {return F;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.logisticscore";}
}
