package gregapi.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

/** 
 * @author Gregorius Techneticies
 */
public interface IIconContainer {
	/**
	 * @return A regular Icon.
	 */
    @SideOnly(Side.CLIENT)
	public IIcon getIcon(int aRenderPass);
    
	/**
	 * @return if this Render Pass uses Color Modulation.
	 */
    @SideOnly(Side.CLIENT)
	public boolean isUsingColorModulation(int aRenderPass);
    
	/**
	 * @return the Color Modulation of the Icon.
	 */
    @SideOnly(Side.CLIENT)
	public short[] getIconColor(int aRenderPass);
	
	/**
	 * @return the Amount of Render Passes for this Icon.
	 */
    @SideOnly(Side.CLIENT)
	public int getIconPasses();
	
	/**
	 * @return the Default Texture File for this Icon.
	 */
    @SideOnly(Side.CLIENT)
	public ResourceLocation getTextureFile();
	
	/**
	 * Registers the Icon of this IconContainer.
	 */
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister aIconRegister);
}