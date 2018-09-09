package gregapi.tileentity.machines;

import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityAnvil extends ITileEntityUnloadable {
	/** @return true to prevent the Hammer from crushing this TileEntity from that Side. */
    public boolean isAnvil(byte aSide);
}