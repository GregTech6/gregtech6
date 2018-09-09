package gregapi.tileentity.energy;

import gregapi.code.TagData;
import gregapi.oredict.OreDictMaterial;
import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 * 
 * Informative Class.
 * 
 * Interface for things like Data Panels and Stuff.
 * Not all Data might be reliable. This is just for Information sake.
 */
public interface ITileEntityEnergyDataConductor extends ITileEntityUnloadable {
	/**
	 * @return if this is a Conductor for this Type of Energy.
	 */
	public boolean isEnergyConducting(TagData aEnergyType);
	
	/**
	 * @return the maximum Packet Size of the Conductor.
	 */
	public long getEnergyMaxSize(TagData aEnergyType);
	
	/**
	 * @return the maximum Packet Amount of the Conductor.
	 */
	public long getEnergyMaxPackets(TagData aEnergyType);
	
	/**
	 * @return the Loss of the Conductor, per Meter.
	 */
	public long getEnergyLossPerMeter(TagData aEnergyType);
	
	/**
	 * @return the Material the Conductor consists of. (may return Materials._NULL)
	 */
	public OreDictMaterial getEnergyConductorMaterial();
	
	/**
	 * @return the Material the Conductor Insulation/Hull consists of. (may return Materials._NULL)
	 */
	public OreDictMaterial getEnergyConductorInsulation();
}