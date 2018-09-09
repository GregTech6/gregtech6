package gregapi.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;

/**
 * @author Gregorius Techneticies
 */
public interface IRenderedBlockObjectSideCheck {
    /** returning true stops all the other Rendering from happening on that Side. */
    @SideOnly(Side.CLIENT)
    public boolean renderFullBlockSide(Block aBlock, RenderBlocks aRenderer, byte aSide);
}