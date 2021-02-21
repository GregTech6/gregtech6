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

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import gregapi.data.FL;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;

public class RendererBlockFluid implements ISimpleBlockRenderingHandler {
	public static int RENDER_ID = FluidRegistry.renderIdFluid;
	public static RendererBlockFluid INSTANCE;
	
	public RendererBlockFluid(int aRenderID) {
		INSTANCE = this;
		RENDER_ID = aRenderID;
	}
	
	private static final float MAX_FLUID_HEIGHT = 0.8888889F, LIGHT_Y_NEG = 0.5F, LIGHT_Y_POS = 1.0F, LIGHT_XZ_NEG = 0.8F, LIGHT_XZ_POS = 0.6F;
	static final double RENDER_OFFSET = 0.0010000000474974513D;
	
	public float getFluidHeightAverage(float[] aFlow) {
		float total = 0, end = 0;
		int count = 0;
		for (int i = 0; i < aFlow.length; i++) {
			if (aFlow[i] >= MAX_FLUID_HEIGHT && end != 1F) end = aFlow[i];
			if (aFlow[i] >= 0) {
				total += aFlow[i];
				count++;
			}
		}
		if (end == 0) end = total / count;
		return end;
	}
	
	public float getFluidHeightForRender(IBlockAccess aWorld, int aX, int aY, int aZ, BlockFluidBase aFluidBlock, Block aBlock) {
		if (aBlock == null) aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == aFluidBlock) {
			Block tBlockAbove = aWorld.getBlock(aX, aY - FL.dir(aFluidBlock), aZ);
			if (tBlockAbove.getMaterial().isLiquid() || tBlockAbove instanceof IFluidBlock) return 1;
			return UT.Code.bindF(aFluidBlock.getQuantaPercentage(aWorld, aX, aY, aZ)) * MAX_FLUID_HEIGHT;
		}
		return !aBlock.getMaterial().isSolid() && aWorld.getBlock(aX, aY - FL.dir(aFluidBlock), aZ) == aFluidBlock ? 1 : UT.Code.bindF(aFluidBlock.getQuantaPercentage(aWorld, aX, aY, aZ)) * MAX_FLUID_HEIGHT;
	}
	
	@Override public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {/**/}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess aWorld, int aX, int aY, int aZ, Block aBlock, int aModelID, RenderBlocks aRenderer) {
		if (!(aBlock instanceof BlockFluidBase)) return F;
		BlockFluidBase aFluid = (BlockFluidBase)aBlock;
		
		Tessellator tTesselator = Tessellator.instance;
		int color = aFluid.colorMultiplier(aWorld, aX, aY, aZ);
		float red = (color >> 16 & 255) / 255.0F;
		float green = (color >> 8 & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		
		int bMeta = aWorld.getBlockMetadata(aX, aY, aZ), aDir = FL.dir(aFluid);
		
		boolean renderTop = aWorld.getBlock(aX, aY - aDir, aZ) != aFluid;
		boolean renderBottom = aFluid.shouldSideBeRendered(aWorld, aX, aY + aDir, aZ, 0) && aWorld.getBlock(aX, aY + aDir, aZ) != aFluid;
		
		boolean[] renderSides = new boolean[] {
			aFluid.shouldSideBeRendered(aWorld, aX, aY, aZ - 1, 2),
			aFluid.shouldSideBeRendered(aWorld, aX, aY, aZ + 1, 3),
			aFluid.shouldSideBeRendered(aWorld, aX - 1, aY, aZ, 4),
			aFluid.shouldSideBeRendered(aWorld, aX + 1, aY, aZ, 5)
		};
		
		if (!renderTop && !renderBottom && !renderSides[0] && !renderSides[1] && !renderSides[2] && !renderSides[3]) return F;
		
		double heightNW, heightSW, heightSE, heightNE;
		float flow11 = getFluidHeightForRender(aWorld, aX, aY, aZ, aFluid, aFluid);
		boolean rRendered = F;
		
		if (flow11 != 1) {
			float flow00 = getFluidHeightForRender(aWorld, aX - 1, aY, aZ - 1, aFluid, null);
			float flow01 = getFluidHeightForRender(aWorld, aX - 1, aY, aZ,     aFluid, null);
			float flow02 = getFluidHeightForRender(aWorld, aX - 1, aY, aZ + 1, aFluid, null);
			float flow10 = getFluidHeightForRender(aWorld, aX,     aY, aZ - 1, aFluid, null);
			float flow12 = getFluidHeightForRender(aWorld, aX,     aY, aZ + 1, aFluid, null);
			float flow20 = getFluidHeightForRender(aWorld, aX + 1, aY, aZ - 1, aFluid, null);
			float flow21 = getFluidHeightForRender(aWorld, aX + 1, aY, aZ,     aFluid, null);
			float flow22 = getFluidHeightForRender(aWorld, aX + 1, aY, aZ + 1, aFluid, null);
			
			heightNW = getFluidHeightAverage(new float[] {flow00, flow01, flow10, flow11});
			heightSW = getFluidHeightAverage(new float[] {flow01, flow02, flow12, flow11});
			heightSE = getFluidHeightAverage(new float[] {flow12, flow21, flow22, flow11});
			heightNE = getFluidHeightAverage(new float[] {flow10, flow20, flow21, flow11});
		} else {
			heightNW = flow11;
			heightSW = flow11;
			heightSE = flow11;
			heightNE = flow11;
		}
		
		if (aRenderer.renderAllFaces || renderTop) {
			rRendered = T;
			IIcon iconStill = aFluid.getIcon(1, bMeta);
			float flowDir = (float)BlockFluidBase.getFlowDirection(aWorld, aX, aY, aZ);
			
			if (flowDir > -999.0F) iconStill = aFluid.getIcon(2, bMeta);
			
			heightNW -= RENDER_OFFSET;
			heightSW -= RENDER_OFFSET;
			heightSE -= RENDER_OFFSET;
			heightNE -= RENDER_OFFSET;
			
			double u1, u2, u3, u4, v1, v2, v3, v4;
			
			if (flowDir < -999.0F) {
				u2 = iconStill.getInterpolatedU(0.0D);
				v2 = iconStill.getInterpolatedV(0.0D);
				u1 = u2;
				v1 = iconStill.getInterpolatedV(16.0D);
				u4 = iconStill.getInterpolatedU(16.0D);
				v4 = v1;
				u3 = u4;
				v3 = v2;
			} else {
				float xFlow = MathHelper.sin(flowDir) * 0.25F;
				float zFlow = MathHelper.cos(flowDir) * 0.25F;
				u2 = iconStill.getInterpolatedU(8.0F + (-zFlow - xFlow) * 16.0F);
				v2 = iconStill.getInterpolatedV(8.0F + (-zFlow + xFlow) * 16.0F);
				u1 = iconStill.getInterpolatedU(8.0F + (-zFlow + xFlow) * 16.0F);
				v1 = iconStill.getInterpolatedV(8.0F + (+zFlow + xFlow) * 16.0F);
				u4 = iconStill.getInterpolatedU(8.0F + (+zFlow + xFlow) * 16.0F);
				v4 = iconStill.getInterpolatedV(8.0F + (+zFlow - xFlow) * 16.0F);
				u3 = iconStill.getInterpolatedU(8.0F + (+zFlow - xFlow) * 16.0F);
				v3 = iconStill.getInterpolatedV(8.0F + (-zFlow - xFlow) * 16.0F);
			}
			
			tTesselator.setBrightness(aFluid.getMixedBrightnessForBlock(aWorld, aX, aY, aZ));
			tTesselator.setColorOpaque_F(LIGHT_Y_POS * red, LIGHT_Y_POS * green, LIGHT_Y_POS * blue);
			
			if (aDir < 0) {
				tTesselator.addVertexWithUV(aX + 0, aY + heightNW, aZ + 0, u2, v2);
				tTesselator.addVertexWithUV(aX + 0, aY + heightSW, aZ + 1, u1, v1);
				tTesselator.addVertexWithUV(aX + 1, aY + heightSE, aZ + 1, u4, v4);
				tTesselator.addVertexWithUV(aX + 1, aY + heightNE, aZ + 0, u3, v3);
				
				tTesselator.addVertexWithUV(aX + 0, aY + heightNW, aZ + 0, u2, v2);
				tTesselator.addVertexWithUV(aX + 1, aY + heightNE, aZ + 0, u3, v3);
				tTesselator.addVertexWithUV(aX + 1, aY + heightSE, aZ + 1, u4, v4);
				tTesselator.addVertexWithUV(aX + 0, aY + heightSW, aZ + 1, u1, v1);
			} else {
				tTesselator.addVertexWithUV(aX + 1, aY + 1 - heightNE, aZ + 0, u3, v3);
				tTesselator.addVertexWithUV(aX + 1, aY + 1 - heightSE, aZ + 1, u4, v4);
				tTesselator.addVertexWithUV(aX + 0, aY + 1 - heightSW, aZ + 1, u1, v1);
				tTesselator.addVertexWithUV(aX + 0, aY + 1 - heightNW, aZ + 0, u2, v2);
				
				tTesselator.addVertexWithUV(aX + 1, aY + 1 - heightNE, aZ + 0, u3, v3);
				tTesselator.addVertexWithUV(aX + 0, aY + 1 - heightNW, aZ + 0, u2, v2);
				tTesselator.addVertexWithUV(aX + 0, aY + 1 - heightSW, aZ + 1, u1, v1);
				tTesselator.addVertexWithUV(aX + 1, aY + 1 - heightSE, aZ + 1, u4, v4);
			}
		}
		
		if (aRenderer.renderAllFaces || renderBottom) {
			rRendered = T;
			tTesselator.setBrightness(aFluid.getMixedBrightnessForBlock(aWorld, aX, aY - 1, aZ));
			if (aDir < 0) {
				tTesselator.setColorOpaque_F(LIGHT_Y_NEG * red, LIGHT_Y_NEG * green, LIGHT_Y_NEG * blue);
				aRenderer.renderFaceYNeg(aFluid, aX, aY + RENDER_OFFSET, aZ, aFluid.getIcon(0, bMeta));
			} else {
				tTesselator.setColorOpaque_F(LIGHT_Y_POS * red, LIGHT_Y_POS * green, LIGHT_Y_POS * blue);
				aRenderer.renderFaceYPos(aFluid, aX, aY + RENDER_OFFSET, aZ, aFluid.getIcon(1, bMeta));
			}
		}
		
		for (int side = 0; side < 4; ++side) {
			int x2 = aX;
			int z2 = aZ;
			
			switch (side) {
				case 0: --z2; break;
				case 1: ++z2; break;
				case 2: --x2; break;
				case 3: ++x2; break;
			}
			
			IIcon iconFlow = aFluid.getIcon(side + 2, bMeta);
			if (aRenderer.renderAllFaces || renderSides[side]) {
				double ty1, tx1, ty2, tx2, tz1, tz2;
				rRendered = T;
				
				if (side == 0) {
					ty1 = heightNW;
					ty2 = heightNE;
					tx1 = aX;
					tx2 = aX + 1;
					tz1 = aZ + RENDER_OFFSET;
					tz2 = aZ + RENDER_OFFSET;
				} else if (side == 1) {
					ty1 = heightSE;
					ty2 = heightSW;
					tx1 = aX + 1;
					tx2 = aX;
					tz1 = aZ + 1 - RENDER_OFFSET;
					tz2 = aZ + 1 - RENDER_OFFSET;
				} else if (side == 2) {
					ty1 = heightSW;
					ty2 = heightNW;
					tx1 = aX + RENDER_OFFSET;
					tx2 = aX + RENDER_OFFSET;
					tz1 = aZ + 1;
					tz2 = aZ;
				} else {
					ty1 = heightNE;
					ty2 = heightSE;
					tx1 = aX + 1 - RENDER_OFFSET;
					tx2 = aX + 1 - RENDER_OFFSET;
					tz1 = aZ;
					tz2 = aZ + 1;
				}
				
				float u1Flow = iconFlow.getInterpolatedU(0.0D);
				float u2Flow = iconFlow.getInterpolatedU(8.0D);
				float v1Flow = iconFlow.getInterpolatedV((1.0D - ty1) * 16.0D * 0.5D);
				float v2Flow = iconFlow.getInterpolatedV((1.0D - ty2) * 16.0D * 0.5D);
				float v3Flow = iconFlow.getInterpolatedV(8.0D);
				tTesselator.setBrightness(aFluid.getMixedBrightnessForBlock(aWorld, x2, aY, z2));
				float sideLighting = (side < 2?LIGHT_XZ_NEG:LIGHT_XZ_POS);
				
				tTesselator.setColorOpaque_F(LIGHT_Y_POS * sideLighting * red, LIGHT_Y_POS * sideLighting * green, LIGHT_Y_POS * sideLighting * blue);
				
				if (aDir < 0) {
					tTesselator.addVertexWithUV(tx1, aY + ty1, tz1, u1Flow, v1Flow);
					tTesselator.addVertexWithUV(tx2, aY + ty2, tz2, u2Flow, v2Flow);
					tTesselator.addVertexWithUV(tx2, aY +   0, tz2, u2Flow, v3Flow);
					tTesselator.addVertexWithUV(tx1, aY +   0, tz1, u1Flow, v3Flow);

					tTesselator.addVertexWithUV(tx1, aY + ty1, tz1, u1Flow, v1Flow);
					tTesselator.addVertexWithUV(tx1, aY +   0, tz1, u1Flow, v3Flow);
					tTesselator.addVertexWithUV(tx2, aY +   0, tz2, u2Flow, v3Flow);
					tTesselator.addVertexWithUV(tx2, aY + ty2, tz2, u2Flow, v2Flow);
				} else {
					tTesselator.addVertexWithUV(tx1, aY + 1 -   0, tz1, u1Flow, v3Flow);
					tTesselator.addVertexWithUV(tx2, aY + 1 -   0, tz2, u2Flow, v3Flow);
					tTesselator.addVertexWithUV(tx2, aY + 1 - ty2, tz2, u2Flow, v2Flow);
					tTesselator.addVertexWithUV(tx1, aY + 1 - ty1, tz1, u1Flow, v1Flow);

					tTesselator.addVertexWithUV(tx1, aY + 1 -   0, tz1, u1Flow, v3Flow);
					tTesselator.addVertexWithUV(tx1, aY + 1 - ty1, tz1, u1Flow, v1Flow);
					tTesselator.addVertexWithUV(tx2, aY + 1 - ty2, tz2, u2Flow, v2Flow);
					tTesselator.addVertexWithUV(tx2, aY + 1 -   0, tz2, u2Flow, v3Flow);
				}
			}
		}
		aRenderer.renderMinY = 0;
		aRenderer.renderMaxY = 1;
		return rRendered;
	}
	
	@Override public boolean shouldRender3DInInventory(int modelId){ return F; }
	@Override public int getRenderId() {return RENDER_ID;}
}
