package gregapi.block;

import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 */
public interface IBlockExtendedMetaData {
	public void setExtendedMetaData(IBlockAccess aWorld, int aX, int aY, int aZ, short aMetaData);
	public short getExtendedMetaData(IBlockAccess aWorld, int aX, int aY, int aZ);
}