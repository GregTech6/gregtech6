package gregapi.tileentity.data;

import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityProgress extends ITileEntityUnloadable {
	/** The Progress this Object has right now. Should always return a >= Zero. */
	public long getProgressValue(byte aSide);
	/** The Progress this Object needs to so its Job. Should always return a Number > Zero. */
	public long getProgressMax(byte aSide);
}