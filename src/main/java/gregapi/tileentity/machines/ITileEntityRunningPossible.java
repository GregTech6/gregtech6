package gregapi.tileentity.machines;

import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityRunningPossible extends ITileEntityUnloadable {
	/**
	 * @return if the Machine could be running actively or not. Used to check if there is Recipe compatible content in slots. Should return true if the Machine is already running ACTIVELY too!
	 */
	public boolean getStateRunningPossible();
}