/**
 * Copyright (c) 2019 Gregorius Techneticies
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

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
