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

public class GT_Circuit_RedstoneMeter extends GT_CircuitryBehavior {
	public GT_Circuit_RedstoneMeter(int aIndex) {
		super(aIndex);
	}
	
	@Override
	public void initParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		aCircuitData[0] =  1;
		aCircuitData[1] = 15;
		aCircuitData[2] =  0;
		aCircuitData[3] = 15;
	}
	
	@Override
	public void validateParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		if (aCircuitData[0] <  0) aCircuitData[0] =  0;
		if (aCircuitData[0] > 15) aCircuitData[0] = 15;
		if (aCircuitData[1] <  0) aCircuitData[1] =  0;
		if (aCircuitData[1] > 15) aCircuitData[1] = 15;
		if (aCircuitData[1] < aCircuitData[0]) aCircuitData[1] = aCircuitData[0];
		if (aCircuitData[2] <  0) aCircuitData[2] =  0;
		if (aCircuitData[2] >  1) aCircuitData[2] =  1;
		if (aCircuitData[3] <  0) aCircuitData[3] =  0;
		if (aCircuitData[3] > 15) aCircuitData[3] = 15;
	}
	
	@Override
	public void onTick(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		byte tRedstone = getStrongestRedstone(aRedstoneCircuitBlock);
		aRedstoneCircuitBlock.setRedstone((tRedstone>=aCircuitData[0]&&tRedstone<=aCircuitData[1])!=(aCircuitData[2]!=0)?(byte)aCircuitData[3]:0, aRedstoneCircuitBlock.getOutputFacing());
	}
	
	@Override
	public String getName() {
		return "Redstone Meter";
	}
	
	@Override
	public String getDescription() {
		return "Checks Boundaries";
	}
	
	@Override
	public String getDataDescription(int[] aCircuitData, int aCircuitDataIndex) {
		switch(aCircuitDataIndex) {
		case  0: return "Lower";
		case  1: return "Upper";
		case  2: return "Invert:";
		case  3: return "RS Out:";
		}
		return "";
	}
	
	@Override
	public boolean displayItemStack(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock, int aIndex) {
		return false;
	}
	
	@Override
	public String getDataDisplay(int[] aCircuitData, int aCircuitDataIndex) {
		if (aCircuitDataIndex == 2) return aCircuitData[2]==0?"OFF":"ON";
		return null;
	}
}
