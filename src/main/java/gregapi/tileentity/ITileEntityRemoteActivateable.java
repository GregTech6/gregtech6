package gregapi.tileentity;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityRemoteActivateable extends ITileEntityUnloadable {
	/** @return if it should stay inside the List of activate-able things. False for things like Dynamite, that will vanish after use, for example. */
	public boolean remoteActivate();
}