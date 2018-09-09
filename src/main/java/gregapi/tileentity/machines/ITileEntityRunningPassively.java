package gregapi.tileentity.machines;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityRunningPassively extends ITileEntityRunningPossible {
	/**
	 * @return if the Machine is running or not. Regardless if it processes something or not.
	 */
	public boolean getStateRunningPassively();
}