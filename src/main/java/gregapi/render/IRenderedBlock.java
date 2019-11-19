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
