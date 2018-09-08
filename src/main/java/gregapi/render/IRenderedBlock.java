package gregapi.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 */
public interface IRenderedBlock {
	/** @return the Textures rendered by {@link RendererBlockTextured} for the Inventory Rendering */
    @SideOnly(Side.CLIENT)
	public ITexture getTexture(int aRenderPass, byte aSide, ItemStack aStack);
    
	/** @return the Textures rendered by {@link RendererBlockTextured} for the World Rendering */
    @SideOnly(Side.CLIENT)
	public ITexture getTexture(int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered, IBlockAccess aWorld, int aX, int aY, int aZ);
    
	/** if this uses said Render Pass or if it can be skipped entirely. */
    @SideOnly(Side.CLIENT)
	public boolean usesRenderPass(int aRenderPass, ItemStack aStack);
    
	/** if this uses said Render Pass or if it can be skipped entirely. */
    @SideOnly(Side.CLIENT)
	public boolean usesRenderPass(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered);
    
	/** sets the Block Size rendered by {@link RendererBlockTextured} for the Inventory Rendering. return false for letting it select the normal Block Bounds. */
    @SideOnly(Side.CLIENT)
	public boolean setBlockBounds(int aRenderPass, ItemStack aStack);
    
	/** sets the Block Size rendered by {@link RendererBlockTextured} for the World Rendering. return false for letting it select the normal Block Bounds. */
    @SideOnly(Side.CLIENT)
	public boolean setBlockBounds(int aRenderPass, IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered);
    
    /** gets the Amount of Render Passes for this Block for the Inventory Rendering */
    @SideOnly(Side.CLIENT)
    public int getRenderPasses(ItemStack aStack);
    
    /** gets the Amount of Render Passes for this Block for the World Rendering */
    @SideOnly(Side.CLIENT)
    public int getRenderPasses(IBlockAccess aWorld, int aX, int aY, int aZ, boolean[] aShouldSideBeRendered);
    
    /** if this Block lets the TileEntity or a similar Handler do all the Inventory Render work. */
    @SideOnly(Side.CLIENT)
    public IRenderedBlockObject passRenderingToObject(ItemStack aStack);
    
    /** if this Block lets the TileEntity or a similar Handler do all the World Render work. */
    @SideOnly(Side.CLIENT)
    public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ);
}