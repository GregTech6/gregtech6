package gregapi.tileentity;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityServerTickPost extends ITileEntityUnloadable {
	public void onServerTickPost(boolean aFirst);
	public void onUnregisterPost();
}