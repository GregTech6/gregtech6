package gregtech.old;

import gregapi.old.GT_CircuitryBehavior;
import gregapi.old.IRedstoneCircuitBlock;

public class GT_Circuit_Randomizer extends GT_CircuitryBehavior {
	
	public GT_Circuit_Randomizer(int aIndex) {
		super(aIndex);
	}
	
	@Override
	public void initParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		aCircuitData[0] = 1;
		aCircuitData[4] = 0;
	}
	
	@Override
	public void validateParameters(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock) {
		if (aCircuitData[0] < 1) aCircuitData[0] = 1;
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
			aCircuitData[4] = 0;
			aRedstoneCircuitBlock.setRedstone((byte)aRedstoneCircuitBlock.getRandom(16), aRedstoneCircuitBlock.getOutputFacing());
		}
	}
	
	@Override
	public String getName() {
		return "Randomizer";
	}
	
	@Override
	public String getDescription() {
		return "Randomizes Redstone";
	}
	
	@Override
	public String getDataDescription(int[] aCircuitData, int aCircuitDataIndex) {
		switch(aCircuitDataIndex) {
		case  0: return "Delay";
		case  3: return aCircuitData[aCircuitDataIndex]==1?"RS => ON":"RS => OFF";
		case  4: return "Status";
		}
		return "";
	}
	
	@Override
	public boolean displayItemStack(int[] aCircuitData, IRedstoneCircuitBlock aRedstoneCircuitBlock, int aIndex) {
		return false;
	}
	
	@Override
	public String getDataDisplay(int[] aCircuitData, int aCircuitDataIndex) {
		if (aCircuitDataIndex != 0) return "";
		return null;
	}
}
