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

import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.data.TD;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import gregapi.tileentity.multiblocks.IMultiBlockEnergy;
import gregapi.tileentity.multiblocks.ITileEntityMultiBlockController;
import gregapi.tileentity.multiblocks.MultiTileEntityMultiBlockPart;
import gregapi.tileentity.multiblocks.TileEntityBase10MultiBlockBase;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityLightningRod extends TileEntityBase10MultiBlockBase implements ITileEntityEnergy, ITileEntityEnergyDataCapacitor, IMultiBlockEnergy {
	public long mEnergy = 0, mCapacity = 0;
	public byte mSize = 0;
	public TagData mEnergyTypeEmitted = TD.Energy.EU;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mEnergy = aNBT.getLong(NBT_ENERGY);
		mCapacity = aNBT.getLong(NBT_CAPACITY);
		if (aNBT.hasKey(NBT_ENERGY_EMITTED)) mEnergyTypeEmitted = TagData.createTagData(aNBT.getString(NBT_ENERGY_EMITTED));
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setNumber(aNBT, NBT_ENERGY, mEnergy);
	}
	
	@Override
	public boolean checkStructure2() {
		boolean tSuccess = T;
		mSize = 0;
		
		for (int i = -1; i < 2; i++) for (int j = -1; j < 2; j++) {
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 0, j, 18004, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 1, j, 18041, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 2, j, 18004, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 3, j, 18041, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
			if (!ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, i, 4, j, 18004, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) tSuccess = F;
		}
		
		// TODO: Specify Proper Rod
		for (int i = 0; i < 100; i++) if (ITileEntityMultiBlockController.Util.checkAndSetTargetOffset(this, 0, 5+i, 0, 18026, getMultiTileEntityRegistryID(), 0, MultiTileEntityMultiBlockPart.NOTHING)) mSize++; else break;
		return tSuccess;
	}
	
	static {
		LH.add("gt.tooltip.multiblock.lightningrod.1", "Bottom: 3x3 of Tungsten Walls with Main at Center");
		LH.add("gt.tooltip.multiblock.lightningrod.2", "Then: Full 3x3 of Large Niobium-Titanium Coils");
		LH.add("gt.tooltip.multiblock.lightningrod.3", "Then: Full 3x3 of Tungsten Walls");
		LH.add("gt.tooltip.multiblock.lightningrod.4", "Then: Full 3x3 of Large Niobium-Titanium Coils");
		LH.add("gt.tooltip.multiblock.lightningrod.5", "Top: 3x3 of Tungsten Walls");
		LH.add("gt.tooltip.multiblock.lightningrod.6", "Requires an actual Rod made of Metal ontop"); // TODO specify Rod Material.
		LH.add("gt.tooltip.multiblock.lightningrod.7", "Optimum Efficiency at a Rod Length of 100 Meters");
		LH.add("gt.tooltip.multiblock.lightningrod.8", "Reduced Efficiency if too close to another Lightning Rod");
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		aList.add(Chat.CYAN     + LH.get(LH.STRUCTURE) + ":");
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.lightningrod.1"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.lightningrod.2"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.lightningrod.3"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.lightningrod.4"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.lightningrod.5"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.lightningrod.6"));
		aList.add(Chat.WHITE    + LH.get("gt.tooltip.multiblock.lightningrod.7"));
		aList.add(Chat.ORANGE   + LH.get("gt.tooltip.multiblock.lightningrod.8"));
		aList.add(Chat.GREEN    + LH.get(LH.ENERGY_OUTPUT) + ": " + Chat.WHITE + VREC[6] + mEnergyTypeEmitted.getChatFormat() + mEnergyTypeEmitted.getLocalisedNameShort() + Chat.WHITE + "/t");
		aList.add(Chat.WHITE    + mCapacity + mEnergyTypeEmitted.getChatFormat() + mEnergyTypeEmitted.getLocalisedNameShort() + Chat.GRAY + " per Lightning Strike");
		super.addToolTips(aList, aStack, aF3_H);
	}
	
	@Override
	public boolean isInsideStructure(int aX, int aY, int aZ) {
		return aX >= xCoord - 1 && aY < yCoord + 5 && aZ >= zCoord - 1 && aX <= xCoord + 1 && aY >= yCoord && aZ <= zCoord + 1;
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		super.onTick2(aTimer, aIsServerSide);
		if (aIsServerSide) {
			if (checkStructure(F)) {
				if (mEnergy >= VREC[6]) {
					mEnergy -= Math.max(1, ITileEntityEnergy.Util.emitEnergyToSide(mEnergyTypeEmitted, SIDE_BOTTOM, VREC[6], 16, this)) * VREC[6];
				} else {
					mEnergy = 0;
					
					// TODO: Lightning Strike Logic
					if (T) {
						mEnergy = mCapacity;
					}
				}
			} else {
				mEnergy = 0;
			}
		}
	}
	
	@Override public byte getDefaultSide() {return SIDE_BOTTOM;}
	@Override public boolean[] getValidSides() {return SIDES_BOTTOM;}
	
	@Override public boolean isEnergyType(TagData aEnergyType, byte aSide, boolean aEmitting) {return aEmitting && aEnergyType == mEnergyTypeEmitted;}
	@Override public boolean isEnergyEmittingTo(TagData aEnergyType, byte aSide, boolean aTheoretical) {return isEnergyType(aEnergyType, aSide, F);}
	@Override public boolean isEnergyCapacitorType(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeEmitted;}
	@Override public long getEnergySizeOutputRecommended(TagData aEnergyType, byte aSide) {return 2048;}
	@Override public long getEnergySizeOutputMin(TagData aEnergyType, byte aSide) {return 1024;}
	@Override public long getEnergySizeOutputMax(TagData aEnergyType, byte aSide) {return 4096;}
	@Override public long getEnergyStored(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeEmitted ? mEnergy : 0;}
	@Override public long getEnergyCapacity(TagData aEnergyType, byte aSide) {return aEnergyType == mEnergyTypeEmitted ? mCapacity : 0;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return mEnergyTypeEmitted.AS_LIST;}
	@Override public Collection<TagData> getEnergyCapacitorTypes(byte aSide) {return mEnergyTypeEmitted.AS_LIST;}
	
	@Override public boolean canDrop(int aInventorySlot) {return T;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.multiblock.lightningrod";}
}
