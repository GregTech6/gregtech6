package gregtech.old;

import gregapi.old.GT_CircuitryBehavior;
import gregapi.old.IRedstoneCircuitBlock;

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