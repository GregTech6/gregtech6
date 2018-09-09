package gregapi.tileentity.data;

import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityTemperature extends ITileEntityUnloadable {
	/** The Temperature this Object has right now. Measured in Kelvin. */
	public long getTemperatureValue(byte aSide);
	/** The Temperature this Object can have at most before breaking. Measured in Kelvin. */
	public long getTemperatureMax(byte aSide);
}