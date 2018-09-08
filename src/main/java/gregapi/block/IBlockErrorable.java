package gregapi.block;

import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 */
public interface IBlockErrorable {
	public void receiveBlockError(IBlockAccess aWorld, int aX, int aY, int aZ, String aError);
}