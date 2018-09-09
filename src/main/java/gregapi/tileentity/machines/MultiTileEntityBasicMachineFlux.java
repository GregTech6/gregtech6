package gregapi.tileentity.machines;

import gregapi.tileentity.energy.ITileEntityEnergyFluxHandler;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBasicMachineFlux extends MultiTileEntityBasicMachine implements ITileEntityEnergyFluxHandler {
	@Override public String getTileEntityName() {return "gt.multitileentity.machine.basic.flux";}
}