package gregapi.tileentity.machines;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityRunningSuccessfully extends ITileEntityRunningActively {
	/**
	 * @return if the Machine has just processed something successfully. Can be a constant true for fast Machines that produce every tick.
	 */
	public boolean getStateRunningSuccessfully();
}