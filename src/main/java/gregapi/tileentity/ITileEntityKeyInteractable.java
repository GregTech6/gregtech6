package gregapi.tileentity;

import net.minecraft.entity.player.EntityPlayer;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityKeyInteractable extends ITileEntityUnloadable {
	/** @return if it got opened successfully. aKeys is the List of IDs the Key has (if you have a key bundle of some sort, for example) */
	public boolean useKey(EntityPlayer aPlayer, byte aSide, float hitX, float hitY, float hitZ, long... aKeys);
}