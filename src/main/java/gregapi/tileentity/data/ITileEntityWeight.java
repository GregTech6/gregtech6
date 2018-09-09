package gregapi.tileentity.data;

import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityWeight extends ITileEntityUnloadable {
	/** Override for the Inventory Weight Sensor, in Kilogramme. */
	public double getWeightValue(byte aSide);
}