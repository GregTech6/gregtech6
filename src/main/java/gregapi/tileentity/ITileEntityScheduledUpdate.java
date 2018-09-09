package gregapi.tileentity;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityScheduledUpdate extends ITileEntityUnloadable {
	public void onScheduledUpdate();
}