package gregapi.block;

import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public interface IBlockDecolorable {
	/** @return if it got removed successfully. */
	public boolean removePaint(World aWorld, int aX, int aY, int aZ, byte aSide);
}