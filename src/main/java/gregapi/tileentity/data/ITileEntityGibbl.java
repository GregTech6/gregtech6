package gregapi.tileentity.data;

import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityGibbl extends ITileEntityUnloadable {
	/** The Pressure this Object has right now. Measured in Milligibbl. 1 Gibbl is 1m³ per 1m³. A full BuildCraft Tank would have a Pressure of 16 Gibbl. */
	public long getGibblValue(byte aSide);
	/** The Pressure this Object can have at most before breaking. Measured in Milligibbl. 1 Gibbl is 1m³ per 1m³. A full BuildCraft Tank would have a Pressure of 16 Gibbl. */
	public long getGibblMax(byte aSide);
}