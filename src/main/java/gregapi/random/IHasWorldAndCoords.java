package gregapi.random;

import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.delegate.ITileEntityCanDelegate;
import gregapi.tileentity.delegate.ITileEntityDelegating;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author Gregorius Techneticies
 * 
 * Contains simple Utility Functions based on the In-World-Coordinates of the Implementor.
 */
public interface IHasWorldAndCoords extends IHasWorld, IHasCoords {
    public TileEntity getTileEntityOffset(int aX, int aY, int aZ);
	public TileEntity getTileEntityAtSideAndDistance(byte aSide, int aDistance);
	
	/** Do not return null! See {@link ITileEntityDelegating} */
	public DelegatorTileEntity<TileEntity> getAdjacentTileEntity(byte aSide);
	/** Do not return null! See {@link ITileEntityDelegating} */
	public DelegatorTileEntity<IInventory> getAdjacentInventory(byte aSide);
	/** Do not return null! See {@link ITileEntityDelegating} */
	public DelegatorTileEntity<ISidedInventory> getAdjacentSidedInventory(byte aSide);
	/** Do not return null! See {@link ITileEntityDelegating} */
	public DelegatorTileEntity<IFluidHandler> getAdjacentTank(byte aSide);
	
	/** Do not return null! See {@link ITileEntityDelegating} if aAllowDelegates is false, it will check of the TileEntity can delegate, see {@link ITileEntityCanDelegate}, and if it can, then this will output a Delegator Object with a null TileEntity instead. */
	public DelegatorTileEntity<TileEntity> getAdjacentTileEntity(byte aSide, boolean aAllowDelegates, boolean aNotConnectToDelegators);
	/** Do not return null! See {@link ITileEntityDelegating} if aAllowDelegates is false, it will check of the TileEntity can delegate, see {@link ITileEntityCanDelegate}, and if it can, then this will output a Delegator Object with a null TileEntity instead. */
	public DelegatorTileEntity<IInventory> getAdjacentInventory(byte aSide, boolean aAllowDelegates, boolean aNotConnectToDelegators);
	/** Do not return null! See {@link ITileEntityDelegating} if aAllowDelegates is false, it will check of the TileEntity can delegate, see {@link ITileEntityCanDelegate}, and if it can, then this will output a Delegator Object with a null TileEntity instead. */
	public DelegatorTileEntity<ISidedInventory> getAdjacentSidedInventory(byte aSide, boolean aAllowDelegates, boolean aNotConnectToDelegators);
	/** Do not return null! See {@link ITileEntityDelegating} if aAllowDelegates is false, it will check of the TileEntity can delegate, see {@link ITileEntityCanDelegate}, and if it can, then this will output a Delegator Object with a null TileEntity instead. */
	public DelegatorTileEntity<IFluidHandler> getAdjacentTank(byte aSide, boolean aAllowDelegates, boolean aNotConnectToDelegators);
	
    public Block getBlockOffset(int aX, int aY, int aZ);
    public Block getBlockAtSide(byte aSide);
    public Block getBlockAtSideAndDistance(byte aSide, int aDistance);
    
    public byte getMetaDataOffset(int aX, int aY, int aZ);
    public byte getMetaDataAtSide(byte aSide);
    public byte getMetaDataAtSideAndDistance(byte aSide, int aDistance);
    
    public byte getLightLevelOffset(int aX, int aY, int aZ);
    public byte getLightLevelAtSide(byte aSide);
    public byte getLightLevelAtSideAndDistance(byte aSide, int aDistance);
    
    public boolean getOpacityOffset(int aX, int aY, int aZ);
    public boolean getOpacityAtSide(byte aSide);
    public boolean getOpacityAtSideAndDistance(byte aSide, int aDistance);
    
    public boolean getSkyOffset(int aX, int aY, int aZ);
    public boolean getSkyAtSide(byte aSide);
    public boolean getSkyAtSideAndDistance(byte aSide, int aDistance);
    
    public boolean getRainOffset(int aX, int aY, int aZ);
    public boolean getRainAtSide(byte aSide);
    public boolean getRainAtSideAndDistance(byte aSide, int aDistance);
    
    public boolean getAirOffset(int aX, int aY, int aZ);
    public boolean getAirAtSide(byte aSide);
    public boolean getAirAtSideAndDistance(byte aSide, int aDistance);
    
    public BiomeGenBase getBiome();
    
	public boolean hasRedstoneIncoming();
	public byte getRedstoneIncoming(byte aSide);
	public byte getComparatorIncoming(byte aSide);
}