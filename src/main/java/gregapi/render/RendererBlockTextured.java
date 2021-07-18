/**
 * Copyright (c) 2021 GregTech-6 Team
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

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;

/**
 * @author Gregorius Techneticies
 */
public class RendererBlockTextured implements ISimpleBlockRenderingHandler, IItemRenderer {
	public final int mRenderID;
	public static RendererBlockTextured INSTANCE;
	public static NBTTagCompound mUsedNBT = null;
	
	public RendererBlockTextured(int aRenderID) {
		INSTANCE = this;
		mRenderID = aRenderID;
	}
	
	@Override
	public void renderInventoryBlock(Block aBlock, int aMetaData, int aModelID, RenderBlocks aRenderer) {
		aRenderer.setRenderBoundsFromBlock(aBlock);
		
		ItemStack aStack = ST.make(aBlock, 1, aMetaData, mUsedNBT);
		
		ITexture.Util.startRendering(aRenderer, aBlock, null, 0, 0, 0);
		
		if (aBlock instanceof IRenderedBlock) {
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			boolean tNeedsToSetBounds = T;
			IRenderedBlockObject tRenderer = ((IRenderedBlock)aBlock).passRenderingToObject(aStack);
			if (tRenderer != null) tRenderer = tRenderer.passRenderingToObject(aStack);
			if (tRenderer == null) {
				for (int i = 0, j = ((IRenderedBlock)aBlock).getRenderPasses(aStack); i < j; i++) {
					if (((IRenderedBlock)aBlock).usesRenderPass(i, aStack)) {
						Tessellator.instance.startDrawingQuads();
						if (((IRenderedBlock)aBlock).setBlockBounds(i, aStack)) {tNeedsToSetBounds = T; aRenderer.setRenderBoundsFromBlock(aBlock);} else {if (tNeedsToSetBounds) aBlock.setBlockBounds(0, 0, 0, 1, 1, 1); aRenderer.setRenderBoundsFromBlock(aBlock); tNeedsToSetBounds = F;}
						Tessellator.instance.setNormal( 0, -1,  0); renderNegativeYFacing(null, aRenderer, aBlock, 0, 0, 0, ((IRenderedBlock)aBlock).getTexture(i, SIDE_Y_NEG, aStack), !tNeedsToSetBounds, T, aBlock);
						Tessellator.instance.setNormal( 0,  1,  0); renderPositiveYFacing(null, aRenderer, aBlock, 0, 0, 0, ((IRenderedBlock)aBlock).getTexture(i, SIDE_Y_POS, aStack), !tNeedsToSetBounds, T, aBlock);
						Tessellator.instance.setNormal( 0,  0, -1); renderNegativeZFacing(null, aRenderer, aBlock, 0, 0, 0, ((IRenderedBlock)aBlock).getTexture(i, SIDE_Z_NEG, aStack), !tNeedsToSetBounds, T, aBlock);
						Tessellator.instance.setNormal( 0,  0,  1); renderPositiveZFacing(null, aRenderer, aBlock, 0, 0, 0, ((IRenderedBlock)aBlock).getTexture(i, SIDE_Z_POS, aStack), !tNeedsToSetBounds, T, aBlock);
						Tessellator.instance.setNormal(-1,  0,  0); renderNegativeXFacing(null, aRenderer, aBlock, 0, 0, 0, ((IRenderedBlock)aBlock).getTexture(i, SIDE_X_NEG, aStack), !tNeedsToSetBounds, T, aBlock);
						Tessellator.instance.setNormal( 1,  0,  0); renderPositiveXFacing(null, aRenderer, aBlock, 0, 0, 0, ((IRenderedBlock)aBlock).getTexture(i, SIDE_X_POS, aStack), !tNeedsToSetBounds, T, aBlock);
						Tessellator.instance.draw();
					}
				}
			} else {
				if (!tRenderer.renderItem(aBlock, aRenderer)) for (int i = 0, j = tRenderer.getRenderPasses(aBlock, SIDES_ITEM_RENDER); i < j; i++) {
					if (tRenderer.usesRenderPass(i, SIDES_ITEM_RENDER)) {
						Tessellator.instance.startDrawingQuads();
						if (tRenderer.setBlockBounds(aBlock, i, SIDES_ITEM_RENDER)) {tNeedsToSetBounds = T; aRenderer.setRenderBoundsFromBlock(aBlock);} else {if (tNeedsToSetBounds) aBlock.setBlockBounds(0, 0, 0, 1, 1, 1); aRenderer.setRenderBoundsFromBlock(aBlock); tNeedsToSetBounds = F;}
						Tessellator.instance.setNormal( 0, -1,  0); renderNegativeYFacing(null, aRenderer, aBlock, 0, 0, 0, tRenderer.getTexture(aBlock, i, SIDE_Y_NEG, SIDES_ITEM_RENDER), !tNeedsToSetBounds, T, tRenderer);
						Tessellator.instance.setNormal( 0,  1,  0); renderPositiveYFacing(null, aRenderer, aBlock, 0, 0, 0, tRenderer.getTexture(aBlock, i, SIDE_Y_POS, SIDES_ITEM_RENDER), !tNeedsToSetBounds, T, tRenderer);
						Tessellator.instance.setNormal( 0,  0, -1); renderNegativeZFacing(null, aRenderer, aBlock, 0, 0, 0, tRenderer.getTexture(aBlock, i, SIDE_Z_NEG, SIDES_ITEM_RENDER), !tNeedsToSetBounds, T, tRenderer);
						Tessellator.instance.setNormal( 0,  0,  1); renderPositiveZFacing(null, aRenderer, aBlock, 0, 0, 0, tRenderer.getTexture(aBlock, i, SIDE_Z_POS, SIDES_ITEM_RENDER), !tNeedsToSetBounds, T, tRenderer);
						Tessellator.instance.setNormal(-1,  0,  0); renderNegativeXFacing(null, aRenderer, aBlock, 0, 0, 0, tRenderer.getTexture(aBlock, i, SIDE_X_NEG, SIDES_ITEM_RENDER), !tNeedsToSetBounds, T, tRenderer);
						Tessellator.instance.setNormal( 1,  0,  0); renderPositiveXFacing(null, aRenderer, aBlock, 0, 0, 0, tRenderer.getTexture(aBlock, i, SIDE_X_POS, SIDES_ITEM_RENDER), !tNeedsToSetBounds, T, tRenderer);
						Tessellator.instance.draw();
					}
				}
			}
			if (tNeedsToSetBounds) aBlock.setBlockBounds(0, 0, 0, 1, 1, 1);
		}
		aRenderer.setRenderBoundsFromBlock(aBlock);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		
		ITexture.Util.endRendering(aRenderer, aBlock, null, 0, 0, 0);
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess aWorld, int aX, int aY, int aZ, Block aBlock, int aModelID, RenderBlocks aRenderer) {
		Tessellator.instance.setBrightness(983055);
		boolean rReturn = F;
		aRenderer.setRenderBoundsFromBlock(aBlock);
		
		ITexture.Util.startRendering(aRenderer, aBlock, aWorld, aX, aY, aZ);
		
		if (aBlock instanceof IRenderedBlock) {
			IRenderedBlockObject tRenderer = ((IRenderedBlock)aBlock).passRenderingToObject(aWorld, aX, aY, aZ);
			if (tRenderer != null) tRenderer = tRenderer.passRenderingToObject(aWorld, aX, aY, aZ);
			if (tRenderer == null) {
				boolean tNeedsToSetBounds = T, tSides[] = new boolean[6];
				if (aBlock instanceof IRenderedBlockObjectSideCheck) {
					for (byte tSide : ALL_SIDES_VALID) tSides[tSide] = ((IRenderedBlockObjectSideCheck)aBlock).renderFullBlockSide(aBlock, aRenderer, tSide);
				} else {
					for (byte tSide : ALL_SIDES_VALID) tSides[tSide] = aBlock.shouldSideBeRendered(aWorld, aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide], tSide);
				}
				for (int i = 0, j = ((IRenderedBlock)aBlock).getRenderPasses(aWorld, aX, aY, aZ, tSides); i < j; i++) {
					if (((IRenderedBlock)aBlock).usesRenderPass(i, aWorld, aX, aY, aZ, tSides)) {
						if (((IRenderedBlock)aBlock).setBlockBounds(i, aWorld, aX, aY, aZ, tSides)) {tNeedsToSetBounds = T; aRenderer.setRenderBoundsFromBlock(aBlock);} else {if (tNeedsToSetBounds) aBlock.setBlockBounds(0, 0, 0, 1, 1, 1); aRenderer.setRenderBoundsFromBlock(aBlock); tNeedsToSetBounds = F;}
						if (renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, ((IRenderedBlock)aBlock).getTexture(i, SIDE_Y_NEG, tSides, aWorld, aX, aY, aZ), !tNeedsToSetBounds, tSides[SIDE_Y_NEG], aBlock)) rReturn = T;
						if (renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, ((IRenderedBlock)aBlock).getTexture(i, SIDE_Y_POS, tSides, aWorld, aX, aY, aZ), !tNeedsToSetBounds, tSides[SIDE_Y_POS], aBlock)) rReturn = T;
						if (renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, ((IRenderedBlock)aBlock).getTexture(i, SIDE_Z_NEG, tSides, aWorld, aX, aY, aZ), !tNeedsToSetBounds, tSides[SIDE_Z_NEG], aBlock)) rReturn = T;
						if (renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, ((IRenderedBlock)aBlock).getTexture(i, SIDE_Z_POS, tSides, aWorld, aX, aY, aZ), !tNeedsToSetBounds, tSides[SIDE_Z_POS], aBlock)) rReturn = T;
						if (renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, ((IRenderedBlock)aBlock).getTexture(i, SIDE_X_NEG, tSides, aWorld, aX, aY, aZ), !tNeedsToSetBounds, tSides[SIDE_X_NEG], aBlock)) rReturn = T;
						if (renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, ((IRenderedBlock)aBlock).getTexture(i, SIDE_X_POS, tSides, aWorld, aX, aY, aZ), !tNeedsToSetBounds, tSides[SIDE_X_POS], aBlock)) rReturn = T;
					}
				}
				if (tNeedsToSetBounds) aBlock.setBlockBounds(0, 0, 0, 1, 1, 1);
			} else {
				boolean tNeedsToSetBounds = T;
				if (tRenderer.renderBlock(aBlock, aRenderer, aWorld, aX, aY, aZ)) {
					rReturn = T;
				} else {
					boolean tSides[] = new boolean[6];
					if (tRenderer instanceof IRenderedBlockObjectSideCheck) {
						for (byte tSide : ALL_SIDES_VALID) tSides[tSide] = ((IRenderedBlockObjectSideCheck)tRenderer).renderFullBlockSide(aBlock, aRenderer, tSide);
					} else {
						for (byte tSide : ALL_SIDES_VALID) tSides[tSide] = aBlock.shouldSideBeRendered(aWorld, aX+OFFX[tSide], aY+OFFY[tSide], aZ+OFFZ[tSide], tSide);
					}
					for (int i = 0, j = tRenderer.getRenderPasses(aBlock, tSides); i < j; i++) {
						if (tRenderer.usesRenderPass(i, tSides)) {
							if (tRenderer.setBlockBounds(aBlock, i, tSides)) {tNeedsToSetBounds = T; aRenderer.setRenderBoundsFromBlock(aBlock);} else {if (tNeedsToSetBounds) aBlock.setBlockBounds(0, 0, 0, 1, 1, 1); aRenderer.setRenderBoundsFromBlock(aBlock); tNeedsToSetBounds = F;}
							if (renderNegativeYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tRenderer.getTexture(aBlock, i, SIDE_Y_NEG, tSides), !tNeedsToSetBounds, tSides[SIDE_Y_NEG], tRenderer)) rReturn = T;
							if (renderPositiveYFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tRenderer.getTexture(aBlock, i, SIDE_Y_POS, tSides), !tNeedsToSetBounds, tSides[SIDE_Y_POS], tRenderer)) rReturn = T;
							if (renderNegativeZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tRenderer.getTexture(aBlock, i, SIDE_Z_NEG, tSides), !tNeedsToSetBounds, tSides[SIDE_Z_NEG], tRenderer)) rReturn = T;
							if (renderPositiveZFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tRenderer.getTexture(aBlock, i, SIDE_Z_POS, tSides), !tNeedsToSetBounds, tSides[SIDE_Z_POS], tRenderer)) rReturn = T;
							if (renderNegativeXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tRenderer.getTexture(aBlock, i, SIDE_X_NEG, tSides), !tNeedsToSetBounds, tSides[SIDE_X_NEG], tRenderer)) rReturn = T;
							if (renderPositiveXFacing(aWorld, aRenderer, aBlock, aX, aY, aZ, tRenderer.getTexture(aBlock, i, SIDE_X_POS, tSides), !tNeedsToSetBounds, tSides[SIDE_X_POS], tRenderer)) rReturn = T;
						}
					}
				}
				if (tNeedsToSetBounds) aBlock.setBlockBounds(0, 0, 0, 1, 1, 1);
			}
		}
		aRenderer.setRenderBoundsFromBlock(aBlock);
		
		ITexture.Util.endRendering(aRenderer, aBlock, aWorld, aX, aY, aZ);
		
		return rReturn;
	}
	
	public static boolean renderNegativeYFacing(IBlockAccess aWorld, RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, ITexture aIcon, boolean aFullBlock, boolean aShouldSideBeRendered, Object aRenderedBlockObject) {
		if (aIcon == null || !aIcon.isValidTexture()) return F;
		int aBrightness = 240;
		if (aWorld != null) {
			if (aFullBlock && !aShouldSideBeRendered) return F;
			aBrightness = aBlock.getMixedBrightnessForBlock(aWorld, aX, aFullBlock?aY-1:aY, aZ);
			aRenderer.enableAO = T;
		}
		aIcon.renderYNeg(aRenderer, aBlock, aX, aY, aZ, aBrightness, !aFullBlock);
		aRenderer.flipTexture = F;
		aRenderer.colorRedTopLeft = aRenderer.colorRedBottomLeft = aRenderer.colorRedBottomRight = aRenderer.colorRedTopRight = aRenderer.colorGreenTopLeft = aRenderer.colorGreenBottomLeft = aRenderer.colorGreenBottomRight = aRenderer.colorGreenTopRight = aRenderer.colorBlueTopLeft = aRenderer.colorBlueBottomLeft = aRenderer.colorBlueBottomRight = aRenderer.colorBlueTopRight = 0.5F;
		return T;
	}
	
	public static boolean renderPositiveYFacing(IBlockAccess aWorld, RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, ITexture aIcon, boolean aFullBlock, boolean aShouldSideBeRendered, Object aRenderedBlockObject) {
		if (aIcon == null || !aIcon.isValidTexture()) return F;
		int aBrightness = 240;
		if (aWorld != null) {
			if (aFullBlock && !aShouldSideBeRendered) return F;
			aBrightness = aBlock.getMixedBrightnessForBlock(aWorld, aX, aFullBlock?aY+1:aY, aZ);
			aRenderer.enableAO = T;
		}
		aIcon.renderYPos(aRenderer, aBlock, aX, aY, aZ, aBrightness, !aFullBlock);
		aRenderer.flipTexture = F;
		aRenderer.colorRedTopLeft = aRenderer.colorRedBottomLeft = aRenderer.colorRedBottomRight = aRenderer.colorRedTopRight = aRenderer.colorGreenTopLeft = aRenderer.colorGreenBottomLeft = aRenderer.colorGreenBottomRight = aRenderer.colorGreenTopRight = aRenderer.colorBlueTopLeft = aRenderer.colorBlueBottomLeft = aRenderer.colorBlueBottomRight = aRenderer.colorBlueTopRight = 1.0F;
		return T;
	}
	
	public static boolean renderNegativeZFacing(IBlockAccess aWorld, RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, ITexture aIcon, boolean aFullBlock, boolean aShouldSideBeRendered, Object aRenderedBlockObject) {
		if (aIcon == null || !aIcon.isValidTexture()) return F;
		int aBrightness = 240;
		if (aWorld != null) {
			if (aFullBlock && !aShouldSideBeRendered) return F;
			aBrightness = aBlock.getMixedBrightnessForBlock(aWorld, aX, aY, aFullBlock?aZ-1:aZ);
			aRenderer.enableAO = T;
		}
		aRenderer.flipTexture = !aFullBlock;
		aIcon.renderZNeg(aRenderer, aBlock, aX, aY, aZ, aBrightness, !aFullBlock);
		aRenderer.flipTexture = F;
		aRenderer.colorRedTopLeft = aRenderer.colorRedBottomLeft = aRenderer.colorRedBottomRight = aRenderer.colorRedTopRight = aRenderer.colorGreenTopLeft = aRenderer.colorGreenBottomLeft = aRenderer.colorGreenBottomRight = aRenderer.colorGreenTopRight = aRenderer.colorBlueTopLeft = aRenderer.colorBlueBottomLeft = aRenderer.colorBlueBottomRight = aRenderer.colorBlueTopRight = 0.8F;
		return T;
	}
	
	public static boolean renderPositiveZFacing(IBlockAccess aWorld, RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, ITexture aIcon, boolean aFullBlock, boolean aShouldSideBeRendered, Object aRenderedBlockObject) {
		if (aIcon == null || !aIcon.isValidTexture()) return F;
		int aBrightness = 240;
		if (aWorld != null) {
			if (aFullBlock && !aShouldSideBeRendered) return F;
			aBrightness = aBlock.getMixedBrightnessForBlock(aWorld, aX, aY, aFullBlock?aZ+1:aZ);
			aRenderer.enableAO = T;
		}
		aIcon.renderZPos(aRenderer, aBlock, aX, aY, aZ, aBrightness, !aFullBlock);
		aRenderer.flipTexture = F;
		aRenderer.colorRedTopLeft = aRenderer.colorRedBottomLeft = aRenderer.colorRedBottomRight = aRenderer.colorRedTopRight = aRenderer.colorGreenTopLeft = aRenderer.colorGreenBottomLeft = aRenderer.colorGreenBottomRight = aRenderer.colorGreenTopRight = aRenderer.colorBlueTopLeft = aRenderer.colorBlueBottomLeft = aRenderer.colorBlueBottomRight = aRenderer.colorBlueTopRight = 0.8F;
		return T;
	}
	
	public static boolean renderNegativeXFacing(IBlockAccess aWorld, RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, ITexture aIcon, boolean aFullBlock, boolean aShouldSideBeRendered, Object aRenderedBlockObject) {
		if (aIcon == null || !aIcon.isValidTexture()) return F;
		int aBrightness = 240;
		if (aWorld != null) {
			if (aFullBlock && !aShouldSideBeRendered) return F;
			aBrightness = aBlock.getMixedBrightnessForBlock(aWorld, aFullBlock?aX-1:aX, aY, aZ);
			aRenderer.enableAO = T;
		}
		aIcon.renderXNeg(aRenderer, aBlock, aX, aY, aZ, aBrightness, !aFullBlock);
		aRenderer.flipTexture = F;
		aRenderer.colorRedTopLeft = aRenderer.colorRedBottomLeft = aRenderer.colorRedBottomRight = aRenderer.colorRedTopRight = aRenderer.colorGreenTopLeft = aRenderer.colorGreenBottomLeft = aRenderer.colorGreenBottomRight = aRenderer.colorGreenTopRight = aRenderer.colorBlueTopLeft = aRenderer.colorBlueBottomLeft = aRenderer.colorBlueBottomRight = aRenderer.colorBlueTopRight = 0.6F;
		return T;
	}
	
	public static boolean renderPositiveXFacing(IBlockAccess aWorld, RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, ITexture aIcon, boolean aFullBlock, boolean aShouldSideBeRendered, Object aRenderedBlockObject) {
		if (aIcon == null || !aIcon.isValidTexture()) return F;
		int aBrightness = 240;
		if (aWorld != null) {
			if (aFullBlock && !aShouldSideBeRendered) return F;
			aBrightness = aBlock.getMixedBrightnessForBlock(aWorld, aFullBlock?aX+1:aX, aY, aZ);
			aRenderer.enableAO = T;
		}
		aRenderer.flipTexture = !aFullBlock;
		aIcon.renderXPos(aRenderer, aBlock, aX, aY, aZ, aBrightness, !aFullBlock);
		aRenderer.flipTexture = F;
		aRenderer.colorRedTopLeft = aRenderer.colorRedBottomLeft = aRenderer.colorRedBottomRight = aRenderer.colorRedTopRight = aRenderer.colorGreenTopLeft = aRenderer.colorGreenBottomLeft = aRenderer.colorGreenBottomRight = aRenderer.colorGreenTopRight = aRenderer.colorBlueTopLeft = aRenderer.colorBlueBottomLeft = aRenderer.colorBlueBottomRight = aRenderer.colorBlueTopRight = 0.6F;
		return T;
	}
	
	@Override
	public boolean shouldRender3DInInventory(int aModel) {
		return T;
	}
	
	@Override
	public int getRenderId() {
		return mRenderID;
	}
	
	@Override
	public boolean handleRenderType(ItemStack aStack, ItemRenderType aType) {
		mUsedNBT = aStack.getTagCompound();
		return F;
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType aType, ItemStack aStack, ItemRendererHelper aHelper) {
		mUsedNBT = aStack.getTagCompound();
		return F;
	}
	
	@Override
	public void renderItem(ItemRenderType aType, ItemStack aStack, Object... data) {
		mUsedNBT = aStack.getTagCompound();
	}
}
