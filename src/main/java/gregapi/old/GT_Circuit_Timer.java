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

public class GT_Circuit_Timer extends GT_CircuitryBehavior {
	
	public GT_Circuit_Timer(int aIndex) {
		super(aIndex);
	}
	
	@Override
	public void initParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		aCircuitData[0] = 2;
		aCircuitData[1] = 1;
		aCircuitData[2] = 2;
		aCircuitData[4] = 0;
	}
	
	@Override
	public void validateParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		if (aCircuitData[0] < 2) aCircuitData[0] = 2;
		if (aCircuitData[1] < 1) aCircuitData[1] = 1;
		if (aCircuitData[2] < 2) aCircuitData[2] = 2;
		if (aCircuitData[3] < 0) aCircuitData[3] = 0;
		if (aCircuitData[3] > 1) aCircuitData[3] = 1;
		if (aCircuitData[4] < 0) aCircuitData[4] = 0;
	}
	
	@Override
	public void onTick(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		if (aCircuitData[3] == 1) {
			if (getAnyRedstone(aRedstoneCircuitBlock)) {
				aCircuitData[4]++;
			} else {
				aCircuitData[4] = 0;
			}
		} else {
			if (getAnyRedstone(aRedstoneCircuitBlock)) {
				aCircuitData[4] = 0;
			} else {
				aCircuitData[4]++;
			}
		}
		
		if (aCircuitData[4]>=aCircuitData[0]) {
			if (aCircuitData[1] > 1) {
				if (aCircuitData[4] >= aCircuitData[0]+(aCircuitData[1]-1)*aCircuitData[2]) {
					aRedstoneCircuitBlock.setRedstone((byte)15, aRedstoneCircuitBlock.getOutputFacing());
					aCircuitData[4] = 0;
				} else {
					aRedstoneCircuitBlock.setRedstone((aCircuitData[4]-aCircuitData[0])%aCircuitData[2]==0?(byte)15:0, aRedstoneCircuitBlock.getOutputFacing());
				}
			} else {
				aRedstoneCircuitBlock.setRedstone((byte)15, aRedstoneCircuitBlock.getOutputFacing());
				aCircuitData[4] = 0;
			}
		} else {
			aRedstoneCircuitBlock.setRedstone((byte)0, aRedstoneCircuitBlock.getOutputFacing());
		}
	}
	
	@Override
	public String getName() {
		return "Timer";
	}
	
	@Override
	public String getDescription() {
		return "Pulses Redstone";
	}
	
	@Override
	public String getDataDescription(int[] aCircuitData, int aCircuitDataIndex) {
		switch(aCircuitDataIndex) {
		case  0: return "Delay";
		case  1: return "Pulses";
		case  2: return "Length";
		case  3: return aCircuitData[aCircuitDataIndex]==1?"RS => ON":"RS => OFF";
		case  4: return "Time";
		}
		return "";
	}
	
	@Override
	public boolean displayItemStack(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock, int aIndex) {
		return false;
	}
	
	@Override
	public String getDataDisplay(int[] aCircuitData, int aCircuitDataIndex) {
		if (aCircuitDataIndex == 3) return "";
		return null;
	}
}
