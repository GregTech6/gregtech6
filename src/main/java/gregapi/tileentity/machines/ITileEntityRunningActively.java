package gregapi.tileentity.machines;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityRunningActively extends ITileEntityRunningPassively {
	/**
	 * @return if the Machine is processing something/emitting energy/etc or not.
	 */
	public boolean getStateRunningActively();
}