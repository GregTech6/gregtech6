package gregapi.block.multitileentity;

import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityContainer {
	public final TileEntity mTileEntity;
	public final MultiTileEntityBlock mBlock;
	public final byte mBlockMetaData;
	
	public MultiTileEntityContainer(TileEntity aTileEntity, MultiTileEntityBlock aBlock, byte aBlockMetaData) {
		mBlockMetaData = aBlockMetaData;
		mTileEntity = aTileEntity;
		mBlock = aBlock;
	}
}