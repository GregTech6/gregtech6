package gregapi.tileentity.render;

import gregapi.tileentity.ITileEntityUnloadable;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityOnDrawBlockHighlight extends ITileEntityUnloadable {
    /** Gets called Client Side, when you mouse over this TileEntity. return true to prevent other things from rendering. */
    public boolean onDrawBlockHighlight(DrawBlockHighlightEvent aEvent);
}