package gregapi.tileentity.inventories;

import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityBookShelf extends ITileEntityUnloadable {
	public boolean isShelfFace(byte aSide);
}