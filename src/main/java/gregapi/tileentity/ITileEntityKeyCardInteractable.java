package gregapi.tileentity;

import net.minecraft.entity.player.EntityPlayer;


/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityKeyCardInteractable extends ITileEntityUnloadable {
	/** @return if it got opened successfully. aKeyCards is the List of IDs the Key has (if you have a Multi-Card of some sort, for example) */
	public boolean useKeyCard(EntityPlayer aPlayer, byte aSide, float hitX, float hitY, float hitZ, long... aKeyCards);
}