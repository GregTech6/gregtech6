package gregapi.tileentity;

import gregapi.random.IHasWorldAndCoords;
import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntity extends IHasWorldAndCoords, ITileEntityUnloadable {
    /**
     * Sends a Block Event to the Client TileEntity, the byte Parameters are only for validation as Minecraft doesn't properly write Packet Data.
     * 
     * This should be used to send Block Sound Effects to the Client, as it is much less Network Heavy to have 2 Bytes rather than a String.
     */
    public void sendBlockEvent(byte aID, byte aValue);
    
	/** @return the amount of Time this TileEntity has been loaded. */
	public long getTimer();
	
	/** Sets an Error String. Should be used when Exceptions are thrown. */
	public void setError(String aError);
	
    /**
     * YOU MUST HAVE THIS INSIDE YOUR BLOCK CODE!!!
     * 
     * public void onNeighborChange(IBlockAccess aWorld, int aX, int aY, int aZ, int aTileX, int aTileY, int aTileZ) {
     *     TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
     *     if (tTileEntity instanceof ITileEntity) ((ITileEntity)tTileEntity).onAdjacentBlockChange(aTileX, aTileY, aTileZ);
     * }
     * 
	 * public void onNeighborBlockChange(World aWorld, int aX, int aY, int aZ, Block aBlock) {
	 * 	   TileEntity tTileEntity = aWorld.getTileEntity(aX, aY, aZ);
	 *     if (tTileEntity instanceof ITileEntity) ((ITileEntity)tTileEntity).onAdjacentBlockChange(aX, aY, aZ);
     * }
     */
	public void onAdjacentBlockChange(int aTileX, int aTileY, int aTileZ);
	
	/** Called after the TileEntity has been placed and set up. */
	public void onTileEntityPlaced();
	
	public boolean allowInteraction(Entity aEntity);
}