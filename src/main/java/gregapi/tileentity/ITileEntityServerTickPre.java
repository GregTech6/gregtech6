package gregapi.tileentity;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityServerTickPre extends ITileEntityUnloadable {
	public void onServerTickPre(boolean aFirst);
	public void onUnregisterPre();
}