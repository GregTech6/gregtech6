package gregapi.tileentity.machines;

import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntitySwitchableOnOff extends ITileEntityUnloadable {
	/**
	 * @param aOnOff true for ON false for OFF.
	 * @return the state of the Machine after it has switched, see getStateOnOff.
	 */
	public boolean setStateOnOff(boolean aOnOff);
	
	/**
	 * @return the ON/OFF State of the Machine. true for ON false for OFF.
	 */
	public boolean getStateOnOff();
}