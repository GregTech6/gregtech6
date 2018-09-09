package gregapi.tileentity.delegate;

import gregapi.tileentity.ITileEntityUnloadable;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityCanDelegate extends ITileEntityUnloadable {
    /** If this TileEntity can delegate anything to another TileEntity. This is more of a marker Interface, if you don't want endless chains of Delegators connected to each other. */
    public boolean isExtender(byte aSide);
}