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

import static gregapi.data.CS.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

/**
 * @author Gregorius Techneticies
 */
public interface IRenderedBlockObject {
	/** @return the Textures rendered by {@link RendererBlockTextured} */
	@SideOnly(Side.CLIENT)
	public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered);
	
	/** if this uses said Render Pass or if it can be skipped entirely. */
	@SideOnly(Side.CLIENT)
	public boolean usesRenderPass(int aRenderPass, boolean[] aShouldSideBeRendered);
	
	/** sets the Block Size rendered by {@link RendererBlockTextured} return false for letting it select the normal Block Bounds. */
	@SideOnly(Side.CLIENT)
	public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered);
	
	/** gets the Amount of Render Passes for this TileEntity or similar Handler. Only gets called once per Rendering. */
	@SideOnly(Side.CLIENT)
	public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered);
	
	/** returning true stops all the other Rendering from happening. */
	@SideOnly(Side.CLIENT)
	public boolean renderItem(Block aBlock, RenderBlocks aRenderer);
	
	/** returning true stops all the other Rendering from happening. */
	@SideOnly(Side.CLIENT)
	public boolean renderBlock(Block aBlock, RenderBlocks aRenderer, IBlockAccess aWorld, int aX, int aY, int aZ);
	
	/** return "this" if you want to use the functions above. */
	@SideOnly(Side.CLIENT)
	public IRenderedBlockObject passRenderingToObject(ItemStack aStack);
	
	/** return "this" if you want to use the functions above. */
	@SideOnly(Side.CLIENT)
	public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ);
	
	public static class ErrorRenderer implements IRenderedBlockObjectSideCheck, IRenderedBlockObject {
		public static final ErrorRenderer INSTANCE = new ErrorRenderer();
		public ITexture mErrorTexture = BlockTextureDefault.get("system/error", T);
		@Override public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return mErrorTexture;}
		@Override public boolean usesRenderPass(int aRenderPass, boolean[] aShouldSideBeRendered) {return T;}
		@Override public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {aBlock.setBlockBounds(-0.25F, -0.25F, -0.25F, 1.25F, 1.25F, 1.25F); return T;}
		@Override public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {return 1;}
		@Override public boolean renderItem(Block aBlock, RenderBlocks aRenderer) {return F;}
		@Override public boolean renderFullBlockSide(Block aBlock, RenderBlocks aRenderer, byte aSide) {return T;}
		@Override public IRenderedBlockObject passRenderingToObject(ItemStack aStack) {return this;}
		@Override public IRenderedBlockObject passRenderingToObject(IBlockAccess aWorld, int aX, int aY, int aZ) {return this;}
		
		@Override
		public boolean renderBlock(Block aBlock, RenderBlocks aRenderer, IBlockAccess aWorld, int aX, int aY, int aZ) {
			aBlock.setBlockBounds(-0.25F, -0.25F, -0.25F, 1.25F, 1.25F, 1.25F);
			RendererBlockTextured.renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, mErrorTexture, F, T, this);
			RendererBlockTextured.renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, mErrorTexture, F, T, this);
			RendererBlockTextured.renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, mErrorTexture, F, T, this);
			RendererBlockTextured.renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, mErrorTexture, F, T, this);
			RendererBlockTextured.renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, mErrorTexture, F, T, this);
			RendererBlockTextured.renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, mErrorTexture, F, T, this);
			return T;
		}
	}
}
