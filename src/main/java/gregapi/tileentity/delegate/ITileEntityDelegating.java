package gregapi.tileentity.delegate;

import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityDelegating extends ITileEntityCanDelegate {
    /** Gets the TileEntity which is responsible for handling this Side. DO NOT RETURN NULL! Return a DelegatorTileEntity Object without TileEntity, or a DelegatorTileEntity with the 'this' Object in order to not delegate. */
    public DelegatorTileEntity<TileEntity> getDelegateTileEntity(byte aSide);
}