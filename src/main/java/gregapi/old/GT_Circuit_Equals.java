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

public class GT_Circuit_Equals extends GT_CircuitryBehavior {
	
	public GT_Circuit_Equals(int aIndex) {
		super(aIndex);
	}
	
	@Override
	public void initParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		aCircuitData[0] = 0;
		aCircuitData[1] = 0;
	}
	
	@Override
	public void validateParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		if (aCircuitData[0] <  0) aCircuitData[0] =  0;
		if (aCircuitData[0] > 15) aCircuitData[0] = 15;
		if (aCircuitData[1] < 0) aCircuitData[3] = 0;
		if (aCircuitData[1] > 1) aCircuitData[3] = 1;
	}
	
	@Override
	public void onTick(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		aRedstoneCircuitBlock.setRedstone((aCircuitData[1]==0 ? getStrongestRedstone(aRedstoneCircuitBlock) == aCircuitData[0] : getStrongestRedstone(aRedstoneCircuitBlock) != aCircuitData[0]) ? (byte)15 : 0, aRedstoneCircuitBlock.getOutputFacing());
	}
	
	@Override
	public String getName() {
		return "Equals";
	}
	
	@Override
	public String getDescription() {
		return "signal == this";
	}
	
	@Override
	public String getDataDescription(int[] aCircuitData, int aCircuitDataIndex) {
		switch(aCircuitDataIndex) {
		case  0: return "Signal";
		case  1: return aCircuitData[1]==0?"Equal":"Unequal";
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
