package gregapi.tileentity.energy;

import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.common.Optional;
import gregapi.data.CS.ModIDs;


/**
 * @author Gregorius Techneticies
 */
@Optional.InterfaceList(value = {
	@Optional.Interface(iface = "cofh.api.energy.IEnergyHandler", modid = ModIDs.COFH_API_ENERGY),
})
public interface ITileEntityEnergyFluxHandler extends ITileEntityEnergy, IEnergyHandler {
	//
}