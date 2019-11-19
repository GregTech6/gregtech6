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

public class GT_Circuit_Pulser extends GT_CircuitryBehavior {
	
	public GT_Circuit_Pulser(int aIndex) {
		super(aIndex);
	}
	
	@Override
	public void initParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		aCircuitData[0] =  1;
		aCircuitData[1] = 16;
		aCircuitData[4] =  0;
	}
	
	@Override
	public void validateParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		if (aCircuitData[0] <  1) aCircuitData[0] =  1;
		if (aCircuitData[1] <  0) aCircuitData[1] =  0;
		if (aCircuitData[1] > 16) aCircuitData[1] = 16;
		if (aCircuitData[4] <  0) aCircuitData[4] =  0;
	}
	
	@Override
	public void onTick(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		byte tRedstone = aCircuitData[1]==0?getWeakestNonZeroRedstone(aRedstoneCircuitBlock):getStrongestRedstone(aRedstoneCircuitBlock);
		if (aCircuitData[4]==0) aCircuitData[5] = tRedstone;
		if ((tRedstone>0||aCircuitData[4]>0)&&(aCircuitData[4]++>=aCircuitData[0]&&tRedstone<=0)) aCircuitData[4]=0;
		aRedstoneCircuitBlock.setRedstone((aCircuitData[4]>0&&aCircuitData[4]<=aCircuitData[0])?aCircuitData[1]<=0||aCircuitData[1]>15?(byte)aCircuitData[5]:(byte)aCircuitData[1]:0, aRedstoneCircuitBlock.getOutputFacing());
	}
	
	@Override
	public String getName() {
		return "Pulser";
	}
	
	@Override
	public String getDescription() {
		return "Limits&Enlengths";
	}
	
	@Override
	public String getDataDescription(int[] aCircuitData, int aCircuitDataIndex) {
		switch(aCircuitDataIndex) {
		case  0: return "Length";
		case  1: return "RS Out";
		}
		return "";
	}
	
	@Override
	public boolean displayItemStack(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock, int aIndex) {
		return false;
	}
	
	@Override
	public String getDataDisplay(int[] aCircuitData, int aCircuitDataIndex) {
		if (aCircuitDataIndex == 1) {
			if (aCircuitData[aCircuitDataIndex] == 16) return "HIGHEST";
			if (aCircuitData[aCircuitDataIndex] ==  0) return "LOWEST";
		}
		return aCircuitDataIndex>1?"":null;
	}
}
