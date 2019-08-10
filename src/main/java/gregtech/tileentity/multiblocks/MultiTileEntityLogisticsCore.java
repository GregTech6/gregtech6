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
import java.util.Set;

import gregapi.code.ArrayListNoNulls;
import gregapi.code.HashSetNoNulls;
import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackSet;
import gregapi.code.TagData;
import gregapi.cover.CoverData;
import gregapi.cover.covers.*;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.fluid.FluidTankGT;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import gregapi.tileentity.logistics.ITileEntityLogistics;
import gregapi.tileentity.logistics.ITileEntityLogisticsSemiFilteredItem;
import gregapi.tileentity.multiblocks.IMultiBlockEnergy;
import gregapi.tileentity.multiblocks.IMultiBlockFluidHandler;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockBase;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
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
					} else if (ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+i, tY+j, tZ+k, 18008, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) {
						// Well someone's a cheapstake. ;P
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
		LH.add("gt.tooltip.multiblock.logisticscore.1" , "5x5x5 Frame made out of Galvanized Steel Walls (Power Input Here)");
		LH.add("gt.tooltip.multiblock.logisticscore.2" , "3x3x3 Core of any Multiblock Processor Units (Customizable)");
		LH.add("gt.tooltip.multiblock.logisticscore.3" , "The Six 3x3 Walls need to be Ventilation Units");
		LH.add("gt.tooltip.multiblock.logisticscore.4" , "Main centered at any Side facing outwards");
		LH.add("gt.tooltip.multiblock.logisticscore.5" , "At least one of each Processor Type needed (Or use a Versatile one)");
		LH.add("gt.tooltip.multiblock.logisticscore.6" , "You can replace CPUs with Walls should you not be able to afford that many.");
		LH.add("gt.tooltip.multiblock.logisticscore.7" , "Logic Processors increase Operation Count by 1");
		LH.add("gt.tooltip.multiblock.logisticscore.8" , "Control Processors increase Maximum Network Range by 1m (Cubic AoE)");
		LH.add("gt.tooltip.multiblock.logisticscore.9" , "Storage Processors increase Buffer Size by 1 Stack or 16000L");
		LH.add("gt.tooltip.multiblock.logisticscore.10", "Conversion Processors increase Throughput by 1 Stack or 16000L");
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
		aList.add(Chat.CYAN     + LH.get("gt.tooltip.multiblock.logisticscore.10"));
		aList.add(Chat.GREEN    + LH.get(LH.ENERGY_INPUT) + ": " + Chat.WHITE + "512 to 16384 " + mEnergyTypeAccepted.getChatFormat() + mEnergyTypeAccepted.getLocalisedNameShort() + Chat.WHITE + "/t");
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing, 2), tY = getOffsetYN(mFacing, 2), tZ = getOffsetZN(mFacing, 2);
		return aX >= tX - 2 && aY >= tY - 2 && aZ >= tZ - 2 && aX <= tX + 2 && aY <= tY + 2 && aZ <= tZ + 2;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			
			if (SERVER_TIME % 20 == 19 && checkStructure(F)) {
				int tX = getOffsetXN(mFacing, 2), tY = getOffsetYN(mFacing, 2), tZ = getOffsetZN(mFacing, 2);
				
				ItemStackSet<ItemStackContainer> tFilteredFor = new ItemStackSet<>();
				
				final List<LogisticsData>
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
				
				, tExports1[][] = new List[][] {
				  {tFluidExportsFiltered, tStackExportsFiltered}
				, {tFluidExportsSemi    , tStackExportsSemi    }
				, {tFluidExportsGeneric , tStackExportsGeneric }
				, {tFluidStorageFiltered, tStackStorageFiltered}
				, {tFluidStorageSemi    , tStackStorageSemi    }
				, {tFluidStorageGeneric , tStackStorageGeneric }
				}
				, tExports2[][] = new List[][] {
				  {tFluidExportsFiltered, tStackExportsFiltered}
				, {tFluidExportsSemi    , tStackExportsSemi    }
				, {tFluidExportsGeneric , tStackExportsGeneric }
				}
				, tImports1[][] = new List[][] {
				  {tFluidImportsGeneric , tStackImportsGeneric }
				, {tFluidImportsSemi    , tStackImportsSemi    }
				, {tFluidImportsFiltered, tStackImportsFiltered}
				}
				, tImports2[][] = new List[][] {
				  {tFluidStorageGeneric , tStackStorageGeneric }
				, {tFluidStorageSemi    , tStackStorageSemi    }
				, {tFluidStorageFiltered, tStackStorageFiltered}
				}
				;
				
				Set<ITileEntityLogistics> tScanning = new HashSetNoNulls<>(), tScanningNext = new HashSetNoNulls<>();
				Set<TileEntity> tScanned = new HashSetNoNulls<>();
				
				for (int i = -2; i <= 2; i++) for (int j = -2; j <= 2; j++) for (int k = -2; k <= 2; k++) {
					TileEntity tTileEntity = WD.te(worldObj, tX + i, tY + j, tZ + k, T);
					if (tScanned.add(tTileEntity) && tTileEntity instanceof ITileEntityLogistics) tScanning.add((ITileEntityLogistics)tTileEntity);
				}
				
				while (!tScanning.isEmpty()) {
					for (ITileEntityLogistics tLogistics : tScanning) {
						CoverData tCovers = tLogistics.getCoverData();
						if (tCovers != null && !tCovers.mStopped) {
							for (byte tSide : ALL_SIDES_VALID) if (tCovers.mBehaviours[tSide] instanceof AbstractCoverAttachmentLogistics) {
								DelegatorTileEntity<TileEntity> tAdjacent = tLogistics.getAdjacentTileEntity(tSide);
								if (tCovers.mBehaviours[tSide] == CoverLogisticsFluidExport.INSTANCE) {
									FluidStack tFluid = FL.load(tCovers.mNBTs[tSide], "gt.filter.fluid");
									if (tFluid != null && tFluid.getFluid() != null) {
										switch(tCovers.mValues[tSide]) {
										case  1: tFluidExportsGeneric .add(new LogisticsData(tAdjacent, tFluid.getFluid())); break;
										case  2: tFluidExportsSemi    .add(new LogisticsData(tAdjacent, tFluid.getFluid())); break;
										default: tFluidExportsFiltered.add(new LogisticsData(tAdjacent, tFluid.getFluid())); break;
										}
									}
									continue;
								}
								if (tCovers.mBehaviours[tSide] == CoverLogisticsFluidImport.INSTANCE) {
									FluidStack tFluid = FL.load(tCovers.mNBTs[tSide], "gt.filter.fluid");
									if (tFluid != null && tFluid.getFluid() != null) {
										switch(tCovers.mValues[tSide]) {
										case  1: tFluidImportsGeneric .add(new LogisticsData(tAdjacent, tFluid.getFluid())); break;
										case  2: tFluidImportsSemi    .add(new LogisticsData(tAdjacent, tFluid.getFluid())); break;
										default: tFluidImportsFiltered.add(new LogisticsData(tAdjacent, tFluid.getFluid())); break;
										}
									}
									continue;
								}
								if (tCovers.mBehaviours[tSide] == CoverLogisticsFluidStorage.INSTANCE) {
									FluidStack tFluid = FL.load(tCovers.mNBTs[tSide], "gt.filter.fluid");
									if (tFluid != null && tFluid.getFluid() != null) {
										switch(tCovers.mValues[tSide]) {
										case  1: tFluidStorageGeneric .add(new LogisticsData(tAdjacent, tFluid.getFluid())); break;
										case  2: tFluidStorageSemi    .add(new LogisticsData(tAdjacent, tFluid.getFluid())); break;
										default: tFluidStorageFiltered.add(new LogisticsData(tAdjacent, tFluid.getFluid())); break;
										}
									}
									continue;
								}
								if (tCovers.mBehaviours[tSide] == CoverLogisticsItemExport.INSTANCE) {
									ItemStack tStack = ST.load(tCovers.mNBTs[tSide], "gt.filter.item");
									if (ST.valid(tStack)) {
										tFilteredFor.add(tStack);
										switch(tCovers.mValues[tSide]) {
										case  1: tStackExportsGeneric .add(new LogisticsData(tAdjacent, tStack)); break;
										case  2: tStackExportsSemi    .add(new LogisticsData(tAdjacent, tStack)); break;
										default: tStackExportsFiltered.add(new LogisticsData(tAdjacent, tStack)); break;
										}
									}
									continue;
								}
								if (tCovers.mBehaviours[tSide] == CoverLogisticsItemImport.INSTANCE) {
									ItemStack tStack = ST.load(tCovers.mNBTs[tSide], "gt.filter.item");
									if (ST.valid(tStack)) {
										tFilteredFor.add(tStack);
										switch(tCovers.mValues[tSide]) {
										case  1: tStackImportsGeneric .add(new LogisticsData(tAdjacent, tStack)); break;
										case  2: tStackImportsSemi    .add(new LogisticsData(tAdjacent, tStack)); break;
										default: tStackImportsFiltered.add(new LogisticsData(tAdjacent, tStack)); break;
										}
									}
									continue;
								}
								if (tCovers.mBehaviours[tSide] == CoverLogisticsItemStorage.INSTANCE) {
									ItemStack tStack = ST.load(tCovers.mNBTs[tSide], "gt.filter.item");
									if (ST.valid(tStack)) {
										tFilteredFor.add(tStack);
										switch(tCovers.mValues[tSide]) {
										case  1: tStackStorageGeneric .add(new LogisticsData(tAdjacent, tStack)); break;
										case  2: tStackStorageSemi    .add(new LogisticsData(tAdjacent, tStack)); break;
										default: tStackStorageFiltered.add(new LogisticsData(tAdjacent, tStack)); break;
										}
									}
									continue;
								}
								LogisticsData tTarget = new LogisticsData(tAdjacent);
								if (tCovers.mBehaviours[tSide] == CoverLogisticsGenericDump.INSTANCE) {
									tStackDumps.add(tTarget);
									continue;
								}
								int tDefault = tCovers.mValues[tSide];
								boolean aAllowFluids = T;
								if (tAdjacent.mTileEntity instanceof ITileEntityLogisticsSemiFilteredItem) {
									aAllowFluids = F;
									ItemStackSet<ItemStackContainer> tFilter = ((ITileEntityLogisticsSemiFilteredItem)tAdjacent.mTileEntity).getLogisticsFilter(tAdjacent.mSideOfTileEntity);
									if (tFilter != null) {
										tFilteredFor.addAll(tFilter);
										if (tDefault == 0) tDefault = 2;
									}
								}
								if (tCovers.mBehaviours[tSide] == CoverLogisticsGenericExport.INSTANCE) {
									switch(tDefault) {
									default: if (aAllowFluids) tFluidExportsGeneric .add(tTarget); tStackExportsGeneric .add(tTarget); break;
									case  2: if (aAllowFluids) tFluidExportsSemi    .add(tTarget); tStackExportsSemi    .add(tTarget); break;
									case  3: if (aAllowFluids) tFluidExportsFiltered.add(tTarget); tStackExportsFiltered.add(tTarget); break;
									}
									continue;
								}
								if (tCovers.mBehaviours[tSide] == CoverLogisticsGenericImport.INSTANCE) {
									switch(tDefault) {
									default: if (aAllowFluids) tFluidImportsGeneric .add(tTarget); tStackImportsGeneric .add(tTarget); break;
									case  2: if (aAllowFluids) tFluidImportsSemi    .add(tTarget); tStackImportsSemi    .add(tTarget); break;
									case  3: if (aAllowFluids) tFluidImportsFiltered.add(tTarget); tStackImportsFiltered.add(tTarget); break;
									}
									continue;
								}
								if (tCovers.mBehaviours[tSide] == CoverLogisticsGenericStorage.INSTANCE) {
									switch(tDefault) {
									default: if (aAllowFluids) tFluidStorageGeneric .add(tTarget); tStackStorageGeneric .add(tTarget); break;
									case  2: if (aAllowFluids) tFluidStorageSemi    .add(tTarget); tStackStorageSemi    .add(tTarget); break;
									case  3: if (aAllowFluids) tFluidStorageFiltered.add(tTarget); tStackStorageFiltered.add(tTarget); break;
									}
									continue;
								}
							}
						}
						
						
						if (tLogistics.getWorld() == worldObj) for (byte tSide : ALL_SIDES_VALID) if (tLogistics.canLogistics(tSide)) {
							if (Math.abs(tLogistics.getOffsetX(tSide) - tX) <= mCPU_Control + 2 && Math.abs(tLogistics.getOffsetY(tSide) - tY) <= mCPU_Control + 2 && Math.abs(tLogistics.getOffsetZ(tSide) - tZ) <= mCPU_Control + 2) {
								DelegatorTileEntity<TileEntity> tAdjacent = tLogistics.getAdjacentTileEntity(tSide);
								if (tScanned.add(tAdjacent.mTileEntity) && tAdjacent.mTileEntity instanceof ITileEntityLogistics && ((ITileEntityLogistics)tAdjacent.mTileEntity).canLogistics(tAdjacent.mSideOfTileEntity)) tScanningNext.add((ITileEntityLogistics)tAdjacent.mTileEntity);
							}
						}
					}
					tScanning.clear();
					tScanning.addAll(tScanningNext);
					tScanningNext.clear();
				}
				
				
				
				
				
				
				
				
				
				
				for (int i = 0; i < mCPU_Logic; i++) {
					boolean tBreak = F;
					
					for (List<LogisticsData>[] tExports : tExports1) {
						for (List<LogisticsData>[] tImports : tImports1) {
							if (moveFluids(tImports[0], tExports[0])) {tBreak = T; break;}
							if (moveStacks(tImports[1], tExports[1])) {tBreak = T; break;}
						}
						if (tBreak) break;
					}
					if (tBreak) continue;
					
					for (List<LogisticsData>[] tExports : tExports2) {
						for (List<LogisticsData>[] tImports : tImports2) {
							if (moveFluids(tImports[0], tExports[0])) {tBreak = T; break;}
							if (moveStacks(tImports[1], tExports[1])) {tBreak = T; break;}
						}
						if (tBreak) break;
					}
					if (tBreak) continue;
					
					if (moveFluids(tFluidStorageGeneric, tFluidStorageFiltered)) continue;
					if (moveStacks(tStackStorageGeneric, tStackStorageFiltered)) continue;
					
					if (moveFluids(tFluidStorageGeneric, tFluidStorageSemi)) continue;
					if (moveStacks(tStackStorageGeneric, tStackStorageSemi)) continue;
					
					for (LogisticsData tImport : tStackStorageGeneric) {
						for (LogisticsData tExport : tStackDumps) {
							for (int j = 0; j < mCPU_Conversion; j++) {
								if (ST.move(tImport.mTarget, tExport.mTarget, tFilteredFor, F, F, T, 64, 1, 64, 1) > 0) {tBreak = T; continue;}
								break;
							}
							if (tBreak) break;
						}
						if (tBreak) break;
					}
					if (tBreak) continue;
					
					
					
					
					
					
					break;
				}
			}
		}
	}
	
	public boolean moveFluids(List<LogisticsData> aImports, List<LogisticsData> aExports) {
		for (LogisticsData aImport : aImports) for (LogisticsData aExport : aExports) if (moveFluids(aImport, aExport)) return T;
		return F;
	}
	
	public boolean moveStacks(List<LogisticsData> aImports, List<LogisticsData> aExports) {
		for (LogisticsData aImport : aImports) for (LogisticsData aExport : aExports) if (moveStacks(aImport, aExport)) return T;
		return F;
	}
	
	public boolean moveFluids(LogisticsData aImport, LogisticsData aExport) {
		if (aImport.mFluidFilter != null) {
			if (aExport.mFluidFilter != null) {
				if (aImport.mFluidFilter == aExport.mFluidFilter && FL.move(aImport.mTarget, aExport.mTarget, FL.make(aImport.mFluidFilter, 16000L * mCPU_Conversion)) > 0) return T;
			} else {
				if (FL.move(aImport.mTarget, aExport.mTarget, FL.make(aImport.mFluidFilter, 16000L * mCPU_Conversion)) > 0) return T;
			}
		} else {
			if (aExport.mFluidFilter != null) {
				if (FL.move(aImport.mTarget, aExport.mTarget, FL.make(aExport.mFluidFilter, 16000L * mCPU_Conversion)) > 0) return T;
			} else {
				if (FL.move(aImport.mTarget, aExport.mTarget, 16000L * mCPU_Conversion) > 0) return T;
			}
		}
		return F;
	}
	
	public boolean moveStacks(LogisticsData aImport, LogisticsData aExport) {
		boolean tReturn = F;
		if (aImport.mItemFilter != null) {
			if (aExport.mItemFilter != null) {
				if (ST.equal(aImport.mItemFilter, aExport.mItemFilter, T)) for (int j = 0; j < mCPU_Conversion; j++) {
					if (ST.move(aImport.mTarget, aExport.mTarget, new ItemStackSet<>(aImport.mItemFilter), F, F, F, 64, 1, 64, 1) > 0) {tReturn = T; continue;}
					break;
				}
			} else {
				for (int j = 0; j < mCPU_Conversion; j++) {
					if (ST.move(aImport.mTarget, aExport.mTarget, new ItemStackSet<>(aImport.mItemFilter), F, F, F, 64, 1, 64, 1) > 0) {tReturn = T; continue;}
					break;
				}
			}
		} else {
			if (aExport.mItemFilter != null) {
				for (int j = 0; j < mCPU_Conversion; j++) {
					if (ST.move(aImport.mTarget, aExport.mTarget, new ItemStackSet<>(aExport.mItemFilter), F, F, F, 64, 1, 64, 1) > 0) {tReturn = T; continue;}
					break;
				}
			} else {
				for (int j = 0; j < mCPU_Conversion; j++) {
					if (ST.move(aImport.mTarget, aExport.mTarget, null, F, F, F, 64, 1, 64, 1) > 0) {tReturn = T; continue;}
					break;
				}
			}
		}
		return tReturn;
	}
	
	static class LogisticsData {
		public final DelegatorTileEntity<TileEntity> mTarget;
		public final Fluid mFluidFilter;
		public final ItemStack mItemFilter;
		
		public LogisticsData(DelegatorTileEntity<TileEntity> aTarget, Fluid aFluidFilter, ItemStack aItemFilter) {
			mTarget = aTarget;
			mFluidFilter = aFluidFilter;
			mItemFilter = aItemFilter;
		}
		public LogisticsData(DelegatorTileEntity<TileEntity> aTarget, ItemStack aItemFilter) {
			mTarget = aTarget;
			mFluidFilter = null;
			mItemFilter = aItemFilter;
		}
		public LogisticsData(DelegatorTileEntity<TileEntity> aTarget, Fluid aFluidFilter) {
			mTarget = aTarget;
			mFluidFilter = aFluidFilter;
			mItemFilter = null;
		}
		public LogisticsData(DelegatorTileEntity<TileEntity> aTarget) {
			mTarget = aTarget;
			mFluidFilter = null;
			mItemFilter = null;
		}
	}
	
	@Override
	public void onMagnifyingGlass2(List<String> aChatReturn) {
		aChatReturn.add(mCPU_Logic + " Logic Processors");
		aChatReturn.add(mCPU_Control + " Control Processors (Range: "+(2+mCPU_Control)+"m, Cubic AoE)");
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
