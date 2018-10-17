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

package gregtech.tileentity.energy;

import static gregapi.data.CS.*;

import java.util.Collection;
import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.code.TagData;
import gregapi.data.TD;
import gregapi.render.ITexture;
import gregapi.tileentity.base.TileEntityBase07Paintable;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityGearBox extends TileEntityBase07Paintable implements ITileEntityEnergy, ITileEntityRunningActively, ITileEntitySwitchableOnOff, IMTE_AddToolTips {
	public boolean mJammed = F, mCheck = F;
	public long mMaxThroughPut = 64;// TODO
	public byte mAxleGear = 0, mRotationData = 0, oRotationData = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_STOPPED)) mJammed = aNBT.getBoolean(NBT_STOPPED);
		// TODO
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_STOPPED, mJammed);
		// TODO
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		// TODO
	}
	
	@Override
	public void onTick2(long aTimer, boolean aIsServerSide) {
		if (aIsServerSide) {
			mCheck = F;
			// TODO
		}
	}
	
	@Override public boolean onTickCheck(long aTimer) {return mRotationData != oRotationData || super.onTickCheck(aTimer);}
	@Override public void onTickResetChecks(long aTimer, boolean aIsServerSide) {super.onTickResetChecks(aTimer, aIsServerSide); oRotationData = mRotationData;}
	@Override public void setVisualData(byte aData) {mRotationData = aData;}
	@Override public byte getVisualData() {return mRotationData;}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		switch (mAxleGear >>> 6) {
		case  1: return null;// TODO
		case  2: return null;
		case  3: return null;
		default: return null;
		}
	}
	
	@Override
	public long doInject(TagData aEnergyType, byte aSide, long aSize, long aAmount, boolean aDoInject) {
		if (mCheck) {
			//byte tRotationData = 0;
			// TODO
			
			// If the Side is Unknown the Power should go through the Axle inside and emit in both directions, if there is an Axle.
		} else {
			mCheck = T;
		}
		return 0;
	}
	
	@Override public boolean isEnergyType                   (TagData aEnergyType, byte aSide, boolean aEmitting) {return TD.Energy.RU == aEnergyType;}
	@Override public boolean isEnergyAcceptingFrom          (TagData aEnergyType, byte aSide, boolean aTheoretical) {return (aTheoretical || !mJammed) && super.isEnergyAcceptingFrom(aEnergyType, aSide, aTheoretical);}
	@Override public boolean isEnergyEmittingTo             (TagData aEnergyType, byte aSide, boolean aTheoretical) {return                               super.isEnergyEmittingTo   (aEnergyType, aSide, aTheoretical);}
	@Override public long getEnergySizeOutputMin            (TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeOutputRecommended    (TagData aEnergyType, byte aSide) {return mMaxThroughPut/2;}
	@Override public long getEnergySizeOutputMax            (TagData aEnergyType, byte aSide) {return mMaxThroughPut;}
	@Override public long getEnergySizeInputMin             (TagData aEnergyType, byte aSide) {return 0;}
	@Override public long getEnergySizeInputRecommended     (TagData aEnergyType, byte aSide) {return mMaxThroughPut/2;}
	@Override public long getEnergySizeInputMax             (TagData aEnergyType, byte aSide) {return mMaxThroughPut;}
	@Override public Collection<TagData> getEnergyTypes(byte aSide) {return TD.Energy.RU.AS_LIST;}
	
	@Override public boolean canDrop(int aInventorySlot) {return F;}
	
	@Override public boolean getStateRunningPossible () {return !mJammed;}
	@Override public boolean getStateRunningPassively() {return !mJammed;}
	@Override public boolean getStateRunningActively () {return !mJammed;}
	@Override public boolean setStateOnOff(boolean aOnOff) {mJammed = !aOnOff; return !mJammed;}
	@Override public boolean getStateOnOff() {return !mJammed;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.gearbox.custom";}
}
