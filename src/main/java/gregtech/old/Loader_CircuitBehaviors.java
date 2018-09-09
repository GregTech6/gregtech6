package gregtech.old;

import static gregapi.data.CS.*;

public class Loader_CircuitBehaviors implements Runnable {
	@Override
	public void run() {
		OUT.println("GT_Mod: Register Redstone Circuit behaviours.");
		new GT_Circuit_Timer(0);
		new GT_Circuit_BasicLogic(1);
		new GT_Circuit_Repeater(2);
		new GT_Circuit_Pulser(3);
		new GT_Circuit_RedstoneMeter(4);
		
		new GT_Circuit_Randomizer(8);
		
		new GT_Circuit_CombinationLock(16);
		new GT_Circuit_BitAnd(17);
		new GT_Circuit_Equals(18);
	}
}