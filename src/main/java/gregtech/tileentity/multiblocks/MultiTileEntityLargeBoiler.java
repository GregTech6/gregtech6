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

import java.util.Collection;
import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_RemovedByPlayer;
import gregapi.code.TagData;
import gregapi.data.BI;
import gregapi.data.CS.GarbageGT;
import gregapi.data.FL;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.fluid.FluidTankGT;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.data.ITileEntityGibbl;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import gregapi.tileentity.multiblocks.IMultiBlockEnergy;
import gregapi.tileentity.multiblocks.IMultiBlockFluidHandler;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockBase;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityLargeBoiler extends TileEntityBase10MultiBlockBase implements ITileEntityEnergy, ITileEntityGibbl, ITileEntityEnergyDataCapacitor, IMultiBlockEnergy, IMultiBlockFluidHandler, IFluidHandler, IMTE_RemovedByPlayer {
	public short mBoilerWalls = 18002;
	public byte mBarometer = 0, oBarometer = 0;
	public short mEfficiency = 10000, mCoolDownResetTimer = 128;
	public long mEnergy = 0, mCapacity = 20480000, mOutput = 204800;
	public TagData mEnergyTypeAccepted = TD.Energy.HU;
	public FluidTankGT[] mTanks = new FluidTankGT[] {new FluidTankGT(128000), new FluidTankGT(2048000)};
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		if (aNBT.hasKey(NBT_DESIGN)) mBoilerWalls = aNBT.getShort(NBT_DESIGN);
		if (aNBT.hasKey(NBT_VISUAL)) mBarometer = aNBT.getByte(NBT_VISUAL);
		if (aNBT.hasKey(NBT_CAPACITY)) mCapacity = aNBT.getLong(NBT_CAPACITY);
		if (aNBT.hasKey(NBT_CAPACITY_SU)) mTanks[1].setCapacity(aNBT.getLong(NBT_CAPACITY_SU));
		if (aNBT.hasKey(NBT_OUTPUT_SU)) mOutput = aNBT.getLong(NBT_OUTPUT_SU);
		if (aNBT.hasKey(NBT_EFFICIENCY)) mEfficiency = (short)UT.Code.bind_(0, 10000, aNBT.getShort(NBT_EFFICIENCY));
		if (aNBT.hasKey(NBT_ENERGY_ACCEPTED)) mEnergyTypeAccepted = TagData.createTagData(aNBT.getString(NBT_ENERGY_ACCEPTED));
		for (int i = 0; i < mTanks.length; i++) mTanks[i].readFromNBT(aNBT, NBT_TANK+"."+i);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
		if (mEfficiency != 10000) aNBT.setShort(NBT_EFFICIENCY, mEfficiency);
		for (int i = 0; i < mTanks.length; i++) mTanks[i].writeToNBT(aNBT, NBT_TANK+"."+i);
	}
	
	@Override
	public boolean checkStructure2() {
		int tX = getOffsetXN(mFacing), tY = yCoord, tZ = getOffsetZN(mFacing);
		if (worldObj.blockExists(tX-1, tY, tZ-1) && worldObj.blockExists(tX+1, tY, tZ-1) && worldObj.blockExists(tX-1, tY, tZ+1) && worldObj.blockExists(tX+1, tY, tZ+1)) {
			boolean tSuccess = T;
			
			if (getAir(tX, tY+1, tZ)) worldObj.setBlockToAir(tX, tY+1, tZ); else tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY-1, tZ-1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY-1, tZ-1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY-1, tZ-1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY-1, tZ  , 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY-1, tZ  , 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY-1, tZ  , 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY-1, tZ+1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY-1, tZ+1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY-1, tZ+1, 18101, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_ENERGY_IN)) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY  , tZ-1, mBoilerWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ-1, mBoilerWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ-1, mBoilerWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY  , tZ  , mBoilerWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ  , mBoilerWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ  , mBoilerWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY  , tZ+1, mBoilerWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY  , tZ+1, mBoilerWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY  , tZ+1, mBoilerWalls, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_IN)) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+2, tZ  , mBoilerWalls, getMultiTileEntityRegistryID(), 1, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			for (int i = 1; i < 3; i++) {
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY+i, tZ-1, mBoilerWalls, getMultiTileEntityRegistryID(),                0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+i, tZ-1, mBoilerWalls, getMultiTileEntityRegistryID(),   i == 1 ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+i, tZ-1, mBoilerWalls, getMultiTileEntityRegistryID(),                0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY+i, tZ  , mBoilerWalls, getMultiTileEntityRegistryID(),   i == 1 ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+i, tZ  , mBoilerWalls, getMultiTileEntityRegistryID(),   i == 1 ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX-1, tY+i, tZ+1, mBoilerWalls, getMultiTileEntityRegistryID(),                0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX  , tY+i, tZ+1, mBoilerWalls, getMultiTileEntityRegistryID(),   i == 1 ? 1 : 0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTarget(this, tX+1, tY+i, tZ+1, mBoilerWalls, getMultiTileEntityRegistryID(),                0, MultiTileEntityMultiBlockPart.ONLY_FLUID_OUT)) tSuccess = F;
			}
			
			return tSuccess;
		}
		return mStructureOkay;
	}
	
	static {
		LH.add("gt.tooltip.multiblock.largeboiler.1", "3x3 Base of Heat Transmitters");
		LH.add("gt.tooltip.multiblock.largeboiler.2", "3x3x3 Hollow of the Block you crafted this one with");
		LH.add("gt.tooltip.multiblock.largeboiler.3", "Main centered on Side-Bottom of Boiler facing outwards");
		LH.add("gt.tooltip.multiblock.largeboiler.4", "Input only possible at Bottom Layer of Boiler");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.largeboiler.1"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.largeboiler.2"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.largeboiler.3"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.largeboiler.4"));
		aList.add(Chat.CYAN     + LH.get(LH.CONVERTS_FROM_X)        + " 1 L " + FL.name(FluidRegistry.WATER, true) + " " + LH.get(LH.CONVERTS_TO_Y) + " 160 L " + FL.name(FL.Steam.make(0), T) + " " + LH.get(LH.CONVERTS_USING_Z) + " 80 " + mEnergyTypeAccepted.getLocalisedNameShort());
		aList.add(LH.getToolTipEfficiency(mEfficiency));
		aList.add(Chat.GREEN    + LH.get(LH.ENERGY_INPUT)           + ": " + Chat.WHITE + (mOutput/STEAM_PER_EU)                        + " " + mEnergyTypeAccepted.getChatFormat() + mEnergyTypeAccepted.getLocalisedNameShort()   + Chat.WHITE + "/t (Heat Transmitters)");
		aList.add(Chat.GREEN    + LH.get(LH.ENERGY_CAPACITY)        + ": " + Chat.WHITE + mCapacity                                     + " " + mEnergyTypeAccepted.getChatFormat() + mEnergyTypeAccepted.getLocalisedNameShort()   + Chat.WHITE);
		aList.add(Chat.RED      + LH.get(LH.ENERGY_OUTPUT)          + ": " + Chat.WHITE + UT.Code.units(mOutput, 10000, mEfficiency, F) + " " + TD.Energy.STEAM.getChatFormat()     + TD.Energy.STEAM.getLocalisedNameLong()        + Chat.WHITE + "/t (Pipe Holes)");
		aList.add(Chat.RED      + LH.get(LH.ENERGY_CAPACITY)        + ": " + Chat.WHITE + mCapacity                                     + " " + TD.Energy.STEAM.getChatFormat()     + TD.Energy.STEAM.getLocalisedNameLong()        + Chat.WHITE);
		aList.add(Chat.ORANGE   + LH.get(LH.REQUIREMENT_WATER_PURE));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_EXPLOSION_STEAM));
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_MELTDOWN));
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_DECALCIFY_CHISEL));
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		int tX = getOffsetXN(mFacing), tY = yCoord, tZ = getOffsetZN(mFacing);
		return aX >= tX - 1 && aY >= tY - 1 && aZ >= tZ - 1 && aX <= tX + 1 && aY <= tY + 2 && aZ <= tZ + 1;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			// Convert Water to Steam
			long tConversions = Math.min(mTanks[1].capacity() / 2560, Math.min(mEnergy / 80, mTanks[0].amount()));
			if (tConversions > 0) {
				mTanks[0].remove(tConversions);
				if (rng(10) == 0 && mEfficiency > 5000 && mTanks[0].has() && !FL.distw(mTanks[0])) {
					mEfficiency -= tConversions;
					if (mEfficiency < 5000) mEfficiency = 5000;
				}
				mTanks[1].setFluid(FL.Steam.make(mTanks[1].amount() + UT.Code.units(tConversions, 10000, mEfficiency * 160, F)));
				mEnergy -= tConversions * 80;
				mCoolDownResetTimer = 128;
			}
			
			// Remove Steam and Heat during the process of cooling down.
			if (mCoolDownResetTimer-- <= 0) {
				mCoolDownResetTimer = 0;
				mEnergy -= (mOutput * 64) / STEAM_PER_EU;
				GarbageGT.trash(mTanks[1], mOutput * 64);
				if (mEnergy <= 0) {
					mEnergy = 0;
					mCoolDownResetTimer = 128;
				}
			}
			
			long tAmount = mTanks[1].amount() - mTanks[1].capacity() / 2;
			
			// Emit Steam
			if (tAmount > 0) {
				FluidStack tDrainableSteam = mTanks[1].drain(UT.Code.bindInt(Math.min(tAmount > mTanks[1].capacity() / 4 ? mOutput * 2 : mOutput, tAmount)), F);
				
				if (tDrainableSteam != null) {
					int tTargets = 0;
					
					@SuppressWarnings("unchecked")
					DelegatorTileEntity<TileEntity>[] tDelegators = new DelegatorTileEntity[] {
					  WD.te(worldObj, getOffsetXN(mFacing, 1)  , yCoord+3, getOffsetZN(mFacing, 1)  , SIDE_Y_NEG, F)
					, WD.te(worldObj, getOffsetXN(mFacing, 1)-2, yCoord+1, getOffsetZN(mFacing, 1)  , SIDE_X_POS, F)
					, WD.te(worldObj, getOffsetXN(mFacing, 1)+2, yCoord+1, getOffsetZN(mFacing, 1)  , SIDE_X_NEG, F)
					, WD.te(worldObj, getOffsetXN(mFacing, 1)  , yCoord+1, getOffsetZN(mFacing, 1)-2, SIDE_Z_POS, F)
					, WD.te(worldObj, getOffsetXN(mFacing, 1)  , yCoord+1, getOffsetZN(mFacing, 1)+2, SIDE_Z_NEG, F)
					};
					
					long[] tTargetAmounts = new long[tDelegators.length];
					
					for (int i = 0; i < tDelegators.length; i++) if (tDelegators[i].mTileEntity instanceof IFluidHandler && (tTargetAmounts[i] = FL.fill_(tDelegators[i], tDrainableSteam, F)) > 0) tTargets++; else tDelegators[i] = null;
					
					if (tTargets == 1) {
						for (int i = 0; i < tDelegators.length; i++) if (tDelegators[i] != null) {
							FL.move_(mTanks[1], tDelegators[i], tDrainableSteam.amount);
							break;
						}
					} else if (tTargets > 1 && tDrainableSteam.amount >= tTargets) {
						if (UT.Code.sum(tTargetAmounts) > tDrainableSteam.amount) {
							int tMoveable = tDrainableSteam.amount, tOriginalTargets = tTargets;
							for (int i = 0; i < tDelegators.length; i++) if (tDelegators[i] != null) {
								if (tTargetAmounts[i] <= tDrainableSteam.amount / tOriginalTargets) {
									tMoveable -= FL.move_(mTanks[1], tDelegators[i], tDrainableSteam.amount / tOriginalTargets);
									tDelegators[i] = null;
									if (--tTargets < 2) break;
								}
							}
							if (tTargets == 1) {
								for (int i = 0; i < tDelegators.length; i++) if (tDelegators[i] != null) {
									FL.move_(mTanks[1], tDelegators[i], tMoveable);
									break;
								}
							} else if (tTargets > 1 && tMoveable >= tTargets) {
								for (int i = 0; i < tDelegators.length; i++) if (tDelegators[i] != null) {
									tMoveable -= FL.move_(mTanks[1], tDelegators[i], tMoveable / tTargets);
									if (--tTargets < 1) break;
								}
							}
						} else {
							for (int i = 0; i < tDelegators.length; i++) if (tDelegators[i] != null) FL.move_(mTanks[1], tDelegators[i], tTargetAmounts[i]);
						}
					}
				}
			}
			
			// Set Barometer
			mBarometer = (byte)UT.Code.scale(mTanks[1].amount(), mTanks[1].capacity(), 31, F);
			
			// Well the Boiler gets structural Damage when being too hot, or when being too full of Steam.
			if ((mBarometer > 4 && !checkStructure(F)) || mEnergy > mCapacity || mTanks[1].isFull()) {
				explode(F);
			}
		}
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
		if (aTool.equals(TOOL_chisel)) {
			int rResult = 10000 - mEfficiency;
			if (rResult > 0) {
				if (mBarometer > 15) {
					explode(F);
				} else {
					if (mEnergy+mTanks[1].amount()/STEAM_PER_EU > 2000) UT.Entities.applyHeatDamage(aPlayer, (mEnergy+mTanks[1].amount()/2) / 2000.0F);
					mTanks[1].setEmpty();
					mEfficiency = 10000;
					mEnergy = 0;
					return rResult;
				}
			}
			return 0;
		}
		
		if (aTool.equals(TOOL_thermometer)) {
			if (aChatReturn != null) aChatReturn.add("Stored Heat Units: " + mEnergy + " / " + mCapacity + " HU");
			return 10000;
		}
		return 0;
	}
	
	@Override
	public void onMagnifyingGlass2(List<String> aChatReturn) {
		if (mEfficiency < 10000) {
			aChatReturn.add("Calcification: " + LH.percent(10000 - mEfficiency) + "%");
		} else {
			aChatReturn.add("No Calcification in this Boiler");
		}
		aChatReturn.add(mTanks[0].content("WARNING: NO WATER!!!"));
	}
	
	@Override
	public boolean removedByPlayer(World aWorld, EntityPlayer aPlayer, boolean aWillHarvest) {
		if (isServerSide() && !UT.Entities.isCreative(aPlayer) && mBarometer > 4) explode(T);
		return worldObj.setBlockToAir(xCoord, yCoord, zCoord);
	}
	
	@Override
	public void onExploded(Explosion aExplosion) {
		super.onExploded(aExplosion);
		if (isServerSide() && mBarometer > 4) explode(T);
	}
	
	@Override
	public void explode(boolean aInstant) {
		explode(2+Math.max(1, Math.sqrt(mTanks[1].amount()) / 1000.0));
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		mBarometer = UT.Code.bind5(mBarometer);
		return mBarometer != oBarometer || super.onTickCheck(aTimer);
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oBarometer = mBarometer;
	}
	
	@Override
	public void setVisualData(byte aData) {
		mBarometer = (byte)(aData&31);
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/boiler_steam/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/boiler_steam/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/boiler_steam/colored/side")
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/boiler_steam/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/boiler_steam/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/boiler_steam/overlay/side")
	};
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		ITexture rTexture = super.getTexture2(aBlock, aRenderPass, aSide, aShouldSideBeRendered);
		return aSide != mFacing || rTexture == null ? rTexture : BlockTextureMulti.get(rTexture, BlockTextureDefault.get(BI.BAROMETER), BlockTextureDefault.get(BI.BAROMETER_SCALE[mBarometer], CA_RED_64));
	}
	
	@Override public byte getVisualData() {return mBarometer;}
	@Override public byte getDefaultSide() {return SIDE_FRONT;}
	@Override public boolean[] getValidSides() {return SIDES_HORIZONTAL;}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return !aEmitting && aEnergyType == mEnergyTypeAccepted;}
	@Override public boolean isEnergyAcceptingFrom(TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, F);}
	@Override public boolean isEnergyCapacitorType(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted;}
	@Override public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {if (aDoInject) {mEnergy += Math.abs(aAmount * aSize); mCoolDownResetTimer = (short)Math.max(mCoolDownResetTimer, 32);} return aAmount;}
	@Override public long getEnergyDemanded(TagData aEnergyType, byte aSide, long aSize) {return mOutput/2;}
	@Override public long getEnergySizeInputRecommended(TagData aEnergyType, byte aSide) {return mOutput/2;}
	@Override public long getEnergySizeInputMin(TagData aEnergyType, byte aSide) {return 1;}
	@Override public long getEnergySizeInputMax(TagData aEnergyType, byte aSide) {return Long.MAX_VALUE;}
	@Override public long getEnergyStored(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted ? mEnergy : 0;}
	@Override public long getEnergyCapacity(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeAccepted ? mCapacity : 0;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	@Override public Collection<TagData> getEnergyCapacitorTypes(byte aSide) {return mEnergyTypeAccepted.AS_LIST;}
	
	@Override protected IFluidTank getFluidTankFillable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToFill) {return FL.water(aFluidToFill) ? mTanks[0] : null;}
	@Override protected IFluidTank getFluidTankDrainable(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToDrain) {return mTanks[1];}
	@Override protected IFluidTank[] getFluidTanks(MultiTileEntityMultiBlockPart aPart, byte aSide) {return mTanks;}
	@Override protected IFluidTank getFluidTankFillable2(byte aSide, FluidStack aFluidToFill) {return FL.water(aFluidToFill) ? mTanks[0] : null;}
	@Override protected IFluidTank getFluidTankDrainable2(byte aSide, FluidStack aFluidToDrain) {return mTanks[1];}
	@Override protected IFluidTank[] getFluidTanks2(byte aSide) {return mTanks;}
	
	@Override public long getGibblValue(byte aSide) {return mTanks[1].amount();}
	@Override public long getGibblMax(byte aSide) {return mTanks[1].capacity();}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.boiler.steam";}
}
