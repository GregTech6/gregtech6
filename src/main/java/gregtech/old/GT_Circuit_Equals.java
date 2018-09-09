package gregtech.old;

import gregapi.old.GT_CircuitryBehavior;
import gregapi.old.IRedstoneCircuitBlock;

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