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

package gregapi.tileentity.energy;

import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.tileentity.behavior.TE_Behavior_Energy_Converter;
import gregapi.tileentity.machines.ITileEntityAdjacentOnOff;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

import static gregapi.data.CS.*;

public abstract class TileEntityBase11Motor extends TileEntityBase10EnergyConverter implements ITileEntityAdjacentOnOff {
	protected boolean mCounterClockwise = F, oCounterClockwise = F, oFast = F;
	
	public TE_Behavior_Energy_Converter mConRevert = null;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_VISUAL)) mConverter.mFast = aNBT.getBoolean(NBT_VISUAL);
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_REVERSED, mCounterClockwise);
		UT.NBT.setBoolean(aNBT, NBT_VISUAL, mConverter.mFast);
	}
	
	@Override
	public void readEnergyConverter(NBTTagCompound aNBT) {
		if (aNBT.hasKey(NBT_REVERSED)) mCounterClockwise = aNBT.getBoolean(NBT_REVERSED);
		long tMultiplier = (aNBT.hasKey(NBT_MULTIPLIER) ? aNBT.getLong(NBT_MULTIPLIER) : 1);
		if (mCounterClockwise) {
			mConRevert  = new TE_Behavior_Energy_Converter(this, aNBT, mStorage, mEnergyIN, mEnergyOUT, tMultiplier, aNBT.getBoolean(NBT_WASTE_ENERGY), F, aNBT.getBoolean(NBT_LIMIT_CONSUMPTION));
			mConverter  = new TE_Behavior_Energy_Converter(this, aNBT, mStorage, mEnergyIN, mEnergyOUT, tMultiplier, aNBT.getBoolean(NBT_WASTE_ENERGY), T, aNBT.getBoolean(NBT_LIMIT_CONSUMPTION));
		} else {
			mConverter  = new TE_Behavior_Energy_Converter(this, aNBT, mStorage, mEnergyIN, mEnergyOUT, tMultiplier, aNBT.getBoolean(NBT_WASTE_ENERGY), F, aNBT.getBoolean(NBT_LIMIT_CONSUMPTION));
			mConRevert  = new TE_Behavior_Energy_Converter(this, aNBT, mStorage, mEnergyIN, mEnergyOUT, tMultiplier, aNBT.getBoolean(NBT_WASTE_ENERGY), T, aNBT.getBoolean(NBT_LIMIT_CONSUMPTION));
		}
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(Chat.DGRAY    + LH.get(LH.TOOL_TO_SET_DIRECTION_MONKEY_WRENCH));
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		long rReturn = super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
		if (rReturn > 0) return rReturn;
		
		if (isClientSide()) return 0;
		
		if (aTool.equals(TOOL_monkeywrench)) {
			mCounterClockwise=!mCounterClockwise;
			TE_Behavior_Energy_Converter tConverter = mConverter;
			mConverter = mConRevert;
			mConRevert = tConverter;
			if (aChatReturn != null) aChatReturn.add(mCounterClockwise ? "Counterclockwise" : "Clockwise");
			causeBlockUpdate();
			doEnetUpdate();
			return 10000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) aChatReturn.add(mCounterClockwise ? "Counterclockwise" : "Clockwise");
			return 1;
		}
		
		return 0;
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		return super.onTickCheck(aTimer) || mCounterClockwise != oCounterClockwise || mConverter.mFast != oFast;
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		oCounterClockwise = mCounterClockwise;
		oFast = mConverter.mFast;
	}
	
	@Override public byte getVisualData() {return (byte)(super.getVisualData() | (mCounterClockwise?4:0) | (mConverter.mFast?8:0));}
	@Override public void setVisualData(byte aData) {super.setVisualData((byte)(aData & 3)); mCounterClockwise = ((aData & 4) != 0); mConverter.mFast = ((aData & 8) != 0);}
}
