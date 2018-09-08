package gregapi.tileentity.machines;

import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntitySwitchableMode extends ITileEntityUnloadable {
	/**
	 * @param aMode Number between 0 and 15. This is supposed to be usable with Redstone.
	 * @return the state of the Machine after it has switched, see getStateMode.
	 */
	public byte setStateMode(byte aMode);
	
	/**
	 * @return the Mode of the Machine.
	 */
	public byte getStateMode();
}