package gregapi.tileentity;

import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityFoamable extends ITileEntityUnloadable {
	/** @return if it got applied successfully. */
	public boolean applyFoam(byte aSide, Entity aPlayer, short[] aCFoamRGB, byte aVanillaColor, boolean aOwnedFoam);
	
	/** @return if it got dried successfully. */
	public boolean dryFoam(byte aSide, Entity aPlayer);
	
	/** @return if it got removed successfully. */
	public boolean removeFoam(byte aSide, Entity aPlayer);
	
	/** @return if it is foamed. */
	public boolean hasFoam(byte aSide);
	
	/** @return if it is dried. */
	public boolean driedFoam(byte aSide);
	
	/** @return if it is owned. */
	public boolean ownedFoam(byte aSide);
}