package gregapi.tileentity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityQuickObstructionCheck extends ITileEntityUnloadable {
	public boolean isObstructingBlockAt(byte aSide);
}