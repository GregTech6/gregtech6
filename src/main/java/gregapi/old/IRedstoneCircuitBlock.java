package gregapi.old;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

/**
 * Implemented by the MetaTileEntity of the Redstone Circuit Block
 */
public interface IRedstoneCircuitBlock {
	/**
	 * The Output Direction the Circuit Block is Facing
	 */
    public byte getOutputFacing();
	
    /**
     * sets Output Redstone State at Side
     */
	public boolean setRedstone(byte aStrength, byte aSide);
	
	/**
	 * returns Output Redstone State at Side
	 * Note that setRedstone checks if there is a Difference between the old and the new Setting before consuming any Energy
	 */
	public byte getOutputRedstone(byte aSide);
	
	/**
	 * returns Input Redstone Signal at Side
	 */
	public byte getInputRedstone(byte aSide);
	
	public int getCoverID(byte aSide);
	
	public int getCoverVariable(byte aSide);
	
	/**
	 * returns whatever Block-ID is adjacent to the Redstone Circuit Block
	 */
	public Block getBlockAtSide(byte aSide);
	
	/**
	 * returns whatever Meta-Value is adjacent to the Redstone Circuit Block
	 */
	public byte getMetaIDAtSide(byte aSide);
	
	/**
	 * returns whatever TileEntity is adjacent to the Redstone Circuit Block
	 */
	public TileEntity getTileEntityAtSide(byte aSide);
	
	/**
	 * returns worldObj.rand.nextInt(aRange)
	 */
	public int getRandom(int aRange);
}
