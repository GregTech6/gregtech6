package gregapi.tileentity.machines;

import gregapi.tileentity.energy.ITileEntityEnergyElectricityAcceptor;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBasicMachineElectric extends MultiTileEntityBasicMachine implements ITileEntityEnergyElectricityAcceptor {
	@Override public String getTileEntityName() {return "gt.multitileentity.machine.basic.electric";}
}