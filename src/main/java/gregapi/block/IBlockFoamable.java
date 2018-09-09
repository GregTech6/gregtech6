package gregapi.block;

import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public interface IBlockFoamable {
	/** @return if it got applied successfully. */
	public boolean applyFoam(World aWorld, int aX, int aY, int aZ, byte aSide, short[] aCFoamRGB, byte aVanillaColor);
	
	/** @return if it got dried successfully. */
	public boolean dryFoam(World aWorld, int aX, int aY, int aZ, byte aSide);
	
	/** @return if it got removed successfully. */
	public boolean removeFoam(World aWorld, int aX, int aY, int aZ, byte aSide);
	
	/** @return if it is foamed. */
	public boolean hasFoam(World aWorld, int aX, int aY, int aZ, byte aSide);
	
	/** @return if it is dried. */
	public boolean driedFoam(World aWorld, int aX, int aY, int aZ, byte aSide);
}