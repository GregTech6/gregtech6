/**
 * Copyright (c) 2019 Gregorius Techneticies
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

package gregapi.old;

public class GT_Circuit_Repeater extends GT_CircuitryBehavior {
	
	public GT_Circuit_Repeater(int aIndex) {
		super(aIndex);
	}
	
	@Override
	public void initParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		aCircuitData[0] = 1;
		aCircuitData[4] = 0;
		aCircuitData[5] =-1;
	}
	
	@Override
	public void validateParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		if (aCircuitData[0] < 1) aCircuitData[0] = 1;
		if (aCircuitData[4] < 0) aCircuitData[4] = 0;
		if (aCircuitData[5] <-1) aCircuitData[5] =-1;
	}
	
	@Override
	public void onTick(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		if (getAnyRedstone(aRedstoneCircuitBlock)) {
			aCircuitData[4]++;
			if (aCircuitData[5] < 0) {
				aCircuitData[5] = 0;
			}
		}
		if (aCircuitData[5] >= 0 && aCircuitData[5] < aCircuitData[0]) {
			aCircuitData[5]++;
		}
		if (aCircuitData[4] > 0) {
			if (aCircuitData[5]>=aCircuitData[0]) {
				aCircuitData[4]--;
				aRedstoneCircuitBlock.setRedstone((byte)15, aRedstoneCircuitBlock.getOutputFacing());
			} else {
				aRedstoneCircuitBlock.setRedstone((byte) 0, aRedstoneCircuitBlock.getOutputFacing());
			}
		} else {
			aRedstoneCircuitBlock.setRedstone((byte) 0, aRedstoneCircuitBlock.getOutputFacing());
			aCircuitData[5] = -1;
		}
	}
	
	@Override
	public String getName() {
		return "Repeater";
	}
	
	@Override
	public String getDescription() {
		return "Delays RS-Signal";
	}
	
	@Override
	public String getDataDescription(int[] aCircuitData, int aCircuitDataIndex) {
		switch(aCircuitDataIndex) {
		case  0: return "Delay";
		}
		return "";
	}
	
	@Override
	public boolean displayItemStack(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock, int aIndex) {
		return false;
	}
	
	@Override
	public String getDataDisplay(int[] aCircuitData, int aCircuitDataIndex) {
		if (aCircuitDataIndex > 0) return "";
		return null;
	}
}
