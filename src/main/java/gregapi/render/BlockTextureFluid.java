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

import gregapi.data.FL;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

/** 
 * @author Gregorius Techneticies
 */
public class BlockTextureFluid implements ITexture {
	private final boolean mAllowAlpha;
	private final int mLuminosity;
	private final IIcon mIcon;
	
	/**
	 *  DO NOT MANIPULATE THE VALUES INSIDE THIS ARRAY!!!
	 *  
	 *  Just set this variable to another different Array instead.
	 *  Otherwise some coloured things will get Problems.
	 */
	public short[] mRGBa;
	
	public static BlockTextureFluid get(IFluidTank aTank, boolean aAllowAlpha) {
		return CODE_CLIENT?new BlockTextureFluid(aTank.getFluid(), aAllowAlpha):null;
	}
	public static BlockTextureFluid get(IFluidTank aTank) {
		return CODE_CLIENT?new BlockTextureFluid(aTank.getFluid()):null;
	}
	public static BlockTextureFluid get(FluidStack aFluidStack, boolean aAllowAlpha) {
		return CODE_CLIENT?new BlockTextureFluid(aFluidStack, aAllowAlpha):null;
	}
	public static BlockTextureFluid get(FluidStack aFluidStack) {
		return CODE_CLIENT?new BlockTextureFluid(aFluidStack):null;
	}
	public static BlockTextureFluid get(Fluid aFluid, boolean aAllowAlpha) {
		return CODE_CLIENT?new BlockTextureFluid(aFluid, aAllowAlpha):null;
	}
	public static BlockTextureFluid get(Fluid aFluid) {
		return CODE_CLIENT?new BlockTextureFluid(aFluid):null;
	}
	public static BlockTextureFluid get(FL aFluid) {
		return CODE_CLIENT?new BlockTextureFluid(aFluid.fluid()):null;
	}
	
	public BlockTextureFluid(FluidStack aFluid, boolean aAllowAlpha) {
		if (aFluid == null || aFluid.getFluid() == null) {
			mLuminosity = 0;
			mRGBa = UNCOLOURED;
			mIcon = null;
		} else {
			mLuminosity = aFluid.getFluid().getLuminosity(aFluid) * 16;
			Block tBlock = aFluid.getFluid().getBlock();
			if (tBlock != null && tBlock != NB) {
				mIcon = tBlock.getIcon(0, 0);
				mRGBa = UT.Code.getRGBaArray(tBlock.getRenderColor(0));
			} else {
				mIcon = aFluid.getFluid().getStillIcon();
				mRGBa = UT.Code.getRGBaArray(aFluid.getFluid().getColor(aFluid));
			}
		}
		mAllowAlpha = aAllowAlpha;
	}
	
	public BlockTextureFluid(FluidStack aFluidStack) {
		this(aFluidStack, F);
	}
	
	public BlockTextureFluid(Fluid aFluid, boolean aAllowAlpha) {
		this(FL.make(aFluid, 0), aAllowAlpha);
	}
	
	public BlockTextureFluid(Fluid aFluid) {
		this(aFluid, F);
	}
	
	private IIcon getIcon(int aSide) {
		return mIcon;
	}
	
	@Override
	public void renderXPos(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
//      aRenderer.flipTexture = !aRenderer.flipTexture;
		ITexture.Util.renderSide(SIDE_X_POS, getIcon(5), mRGBa, mAllowAlpha, mLuminosity > aBrightness, T, aRenderer, aBlock, aX, aY, aZ, Math.max(mLuminosity, aBrightness), aChangedBlockBounds);
//      aRenderer.flipTexture = !aRenderer.flipTexture;
	}
	
	@Override
	public void renderXNeg(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		ITexture.Util.renderSide(SIDE_X_NEG, getIcon(4), mRGBa, mAllowAlpha, mLuminosity > aBrightness, T, aRenderer, aBlock, aX, aY, aZ, Math.max(mLuminosity, aBrightness), aChangedBlockBounds);
	}
	
	@Override
	public void renderYPos(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		ITexture.Util.renderSide(SIDE_Y_POS, getIcon(1), mRGBa, mAllowAlpha, mLuminosity > aBrightness, T, aRenderer, aBlock, aX, aY, aZ, Math.max(mLuminosity, aBrightness), aChangedBlockBounds);
	}
	
	@Override
	public void renderYNeg(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		ITexture.Util.renderSide(SIDE_Y_NEG, getIcon(0), mRGBa, mAllowAlpha, mLuminosity > aBrightness, T, aRenderer, aBlock, aX, aY, aZ, Math.max(mLuminosity, aBrightness), aChangedBlockBounds);
	}
	
	@Override
	public void renderZPos(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		ITexture.Util.renderSide(SIDE_Z_POS, getIcon(3), mRGBa, mAllowAlpha, mLuminosity > aBrightness, T, aRenderer, aBlock, aX, aY, aZ, Math.max(mLuminosity, aBrightness), aChangedBlockBounds);
	}
	
	@Override
	public void renderZNeg(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
//      aRenderer.flipTexture = !aRenderer.flipTexture;
		ITexture.Util.renderSide(SIDE_Z_NEG, getIcon(2), mRGBa, mAllowAlpha, mLuminosity > aBrightness, T, aRenderer, aBlock, aX, aY, aZ, Math.max(mLuminosity, aBrightness), aChangedBlockBounds);
//      aRenderer.flipTexture = !aRenderer.flipTexture;
	}
	
	@Override
	public boolean isValidTexture() {
		return mIcon != null;
	}
}
