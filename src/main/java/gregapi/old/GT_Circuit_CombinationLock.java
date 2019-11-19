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

public class GT_Circuit_CombinationLock extends GT_CircuitryBehavior {
	
	public GT_Circuit_CombinationLock(int aIndex) {
		super(aIndex);
	}
	
	@Override
	public void initParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		aCircuitData[0] = 1;
		aCircuitData[1] = 0;
		aCircuitData[2] = 0;
		aCircuitData[3] = 0;
		aCircuitData[4] = 0;
		aCircuitData[5] = 0;
	}
	
	@Override
	public void validateParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		if (aCircuitData[0] <  1) aCircuitData[0] =  1;
		if (aCircuitData[1] <  0) aCircuitData[1] =  0;
		if (aCircuitData[2] <  0) aCircuitData[2] =  0;
		if (aCircuitData[3] <  0) aCircuitData[3] =  0;
		if (aCircuitData[0] > 15) aCircuitData[0] = 15;
		if (aCircuitData[1] > 15) aCircuitData[1] = 15;
		if (aCircuitData[2] > 15) aCircuitData[2] = 15;
		if (aCircuitData[3] > 15) aCircuitData[3] = 15;
		if (aCircuitData[4] <  0) aCircuitData[4] =  0;
		if (aCircuitData[4] >  3) aCircuitData[4] =  3;
		if (aCircuitData[5] <  0) aCircuitData[5] =  0;
	}
	
	@Override
	public void onTick(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		while (aCircuitData[aCircuitData[4]] == 0 && aCircuitData[4] < 4) aCircuitData[4]++;
		if (aCircuitData[4]<4) {
			int tRedstone = getStrongestRedstone(aRedstoneCircuitBlock);
			if (tRedstone > 0) {
				if (aCircuitData[5] == 0) {
					if (tRedstone == aCircuitData[aCircuitData[4]]) {
						aCircuitData[4]++;
					} else {
						aCircuitData[4]=0;
					}
				}
				aCircuitData[5] = 1;
			} else {
				aCircuitData[5] = 0;
			}
			aRedstoneCircuitBlock.setRedstone((byte) 0, aRedstoneCircuitBlock.getOutputFacing());
		} else {
			aRedstoneCircuitBlock.setRedstone((byte)15, aRedstoneCircuitBlock.getOutputFacing());
			aCircuitData[4]=0;
		}
	}
	
	@Override
	public String getName() {
		return "Combination Lock";
	}
	
	@Override
	public String getDescription() {
		return "Checks Combinations";
	}
	
	@Override
	public String getDataDescription(int[] aCircuitData, int aCircuitDataIndex) {
		return "Power " + aCircuitDataIndex;
	}
	
	@Override
	public boolean displayItemStack(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock, int aIndex) {
		return false;
	}
	
	@Override
	public String getDataDisplay(int[] aCircuitData, int aCircuitDataIndex) {
		return null;
	}
}
