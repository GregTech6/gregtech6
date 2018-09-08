package gregtech.old;

import gregapi.old.GT_CircuitryBehavior;
import gregapi.old.IRedstoneCircuitBlock;

public class GT_Circuit_BitAnd extends GT_CircuitryBehavior {
	
	public GT_Circuit_BitAnd(int aIndex) {
		super(aIndex);
	}
	
	@Override
	public void initParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		aCircuitData[0] = 0;
		aCircuitData[1] = 0;
		aCircuitData[2] = 0;
		aCircuitData[3] = 0;
	}
	
	@Override
	public void validateParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		if (aCircuitData[0] < 0) aCircuitData[0] = 0;
		if (aCircuitData[1] < 0) aCircuitData[1] = 0;
		if (aCircuitData[2] < 0) aCircuitData[2] = 0;
		if (aCircuitData[3] < 0) aCircuitData[3] = 0;
		if (aCircuitData[0] > 1) aCircuitData[0] = 1;
		if (aCircuitData[1] > 1) aCircuitData[1] = 1;
		if (aCircuitData[2] > 1) aCircuitData[2] = 1;
		if (aCircuitData[3] > 1) aCircuitData[3] = 1;
	}
	
	@Override
	public void onTick(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		aRedstoneCircuitBlock.setRedstone(((getStrongestRedstone(aRedstoneCircuitBlock) & (aCircuitData[0] | (aCircuitData[1]<<1) | (aCircuitData[2]<<2) | (aCircuitData[3]<<3))) != 0 ? (byte)15 : 0), aRedstoneCircuitBlock.getOutputFacing());
	}
	
	@Override
	public String getName() {
		return "Hardcode Bit-AND";
	}
	
	@Override
	public String getDescription() {
		return "( signal & this ) != 0";
	}
	
	@Override
	public String getDataDescription(int[] aCircuitData, int aCircuitDataIndex) {
		return "Bit " + aCircuitDataIndex + ":";
	}
	
	@Override
	public boolean displayItemStack(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock, int aIndex) {
		return false;
	}
	
	@Override
	public String getDataDisplay(int[] aCircuitData, int aCircuitDataIndex) {
		return aCircuitData[aCircuitDataIndex]==0?"OFF":"ON";
	}
}