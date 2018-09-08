package gregapi.tileentity;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityDecolorable extends ITileEntityUnloadable {
	/** @return if it got removed successfully. */
	public boolean removePaint(byte aSide);
}