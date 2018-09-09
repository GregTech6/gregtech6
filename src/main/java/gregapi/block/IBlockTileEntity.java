package gregapi.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 * 
 * This Interface is used for MultiBlocks in order to provide "stupid" Blocks, which have no TileEntity with the Main TileEntity of a MultiBlock.
 * Do not implement this Interface for real TileEntity Blocks, just for Blocks which should not have an own TileEntity in MC, but which have a TileEntity registered inside a Custom Handler.
 */
public interface IBlockTileEntity {
	/** can return a TileEntity for this Block even if it has no actual one, in order to provide an Interface to the Main-Block of a MultiBlock Contraption. */
	public TileEntity getTileEntity(IBlockAccess aWorld, int aX, int aY, int aZ);
}