package gregapi.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityGUI extends ITileEntityUnloadable {
	/** Gets the GUI Elements. Negative GUIIDs are internal Usage. For example -1, -2, -3, -4, -5 and -6 are the Covers on the Side -GUIID-1 */
	@SideOnly(Side.CLIENT)
	public Object getGUIClient(int aGUIID, EntityPlayer aPlayer);
	/** Gets the GUI Elements. Negative GUIIDs are internal Usage. For example -1, -2, -3, -4, -5 and -6 are the Covers on the Side -GUIID-1 */
	public Object getGUIServer(int aGUIID, EntityPlayer aPlayer);
	
	/** Opens the GUI with this ID of this TileEntity */
	public boolean openGUI(EntityPlayer aPlayer, int aID);
	/** Opens the GUI with the ID = 0 of this TileEntity */
	public boolean openGUI(EntityPlayer aPlayer);
}