package gregapi.tileentity.energy;

import cpw.mods.fml.common.Optional;
import gregapi.data.CS.ModIDs;
import ic2.api.energy.tile.IEnergySink;


/**
 * @author Gregorius Techneticies
 */
@Optional.InterfaceList(value = {
	@Optional.Interface(iface = "ic2.api.energy.tile.IEnergySink", modid = ModIDs.IC2)
})
public interface ITileEntityEnergyElectricityAcceptor extends ITileEntityEnergy, IEnergySink {
	//
}