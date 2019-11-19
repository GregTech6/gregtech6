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

import gregapi.old.Textures;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;

/** 
 * @author Gregorius Techneticies
 */
public class BlockTextureCopied implements ITexture {
	private final Block mBlock;
	private final byte mSide, mMeta;
	
	/**
	 *  DO NOT MANIPULATE THE VALUES INSIDE THIS ARRAY!!!
	 *  
	 *  Just set this variable to another different Array instead.
	 *  Otherwise some colored things will get Problems.
	 */
	public short[] mRGBa;
	
	private final boolean mAllowAlpha, mUseMaxBrightness, mUseConstantBrightness;
	
	public static BlockTextureCopied get(Block aBlock, int aSide, int aMeta, short[] aRGBa, boolean aAllowAlpha, boolean aUseMaxBrightness, boolean aUseConstantBrightness) {
		return (CODE_CLIENT||CODE_UNCHECKED)&&aBlock!=null&&aBlock!=NB?new BlockTextureCopied(aBlock, aSide, aMeta, aRGBa, aAllowAlpha, aUseMaxBrightness, aUseConstantBrightness):null;
	}
	
	public static BlockTextureCopied get(Block aBlock, int aSide, int aMeta, int aRGBa, boolean aAllowAlpha, boolean aUseMaxBrightness, boolean aUseConstantBrightness) {
		return (CODE_CLIENT||CODE_UNCHECKED)&&aBlock!=null&&aBlock!=NB?new BlockTextureCopied(aBlock, aSide, aMeta, aRGBa, aAllowAlpha, aUseMaxBrightness, aUseConstantBrightness):null;
	}
	
	public static BlockTextureCopied get(Block aBlock, int aSide, int aMeta) {
		return (CODE_CLIENT||CODE_UNCHECKED)&&aBlock!=null&&aBlock!=NB?new BlockTextureCopied(aBlock, aSide, aMeta):null;
	}
	
	public static BlockTextureCopied get(Block aBlock, int aMeta) {
		return (CODE_CLIENT||CODE_UNCHECKED)&&aBlock!=null&&aBlock!=NB?new BlockTextureCopied(aBlock, SIDE_ANY, aMeta):null;
	}
	
	public static BlockTextureCopied get(Block aBlock) {
		return (CODE_CLIENT||CODE_UNCHECKED)&&aBlock!=null&&aBlock!=NB?new BlockTextureCopied(aBlock, SIDE_ANY, 0):null;
	}
	
	public BlockTextureCopied(Block aBlock, int aSide, int aMeta, short[] aRGBa, boolean aAllowAlpha, boolean aUseMaxBrightness, boolean aUseConstantBrightness) {
		if (aRGBa.length != 4) throw new IllegalArgumentException("RGBa doesn't have 4 Values @ BlockTextureCopied");
		mBlock = aBlock;
		mRGBa = aRGBa;
		mSide = (byte)aSide;
		mMeta = (byte)aMeta;
		mAllowAlpha = aAllowAlpha;
		mUseMaxBrightness = aUseMaxBrightness;
		mUseConstantBrightness = aUseConstantBrightness;
	}
	
	public BlockTextureCopied(Block aBlock, int aSide, int aMeta, int aRGBa, boolean aAllowAlpha, boolean aUseMaxBrightness, boolean aUseConstantBrightness) {
		mBlock = aBlock;
		mRGBa = UT.Code.getRGBaArray(aRGBa);
		mSide = (byte)aSide;
		mMeta = (byte)aMeta;
		mAllowAlpha = aAllowAlpha;
		mUseMaxBrightness = aUseMaxBrightness;
		mUseConstantBrightness = aUseConstantBrightness;
	}
	
	public BlockTextureCopied(Block aBlock, int aSide, int aMeta, int aRGBa, boolean aAllowAlpha, boolean aGlow) {
		mBlock = aBlock;
		mRGBa = UT.Code.getRGBaArray(aRGBa);
		mSide = (byte)aSide;
		mMeta = (byte)aMeta;
		mAllowAlpha = aAllowAlpha;
		mUseMaxBrightness = aGlow;
		mUseConstantBrightness = aGlow;
	}
	
	public BlockTextureCopied(Block aBlock, int aSide, int aMeta) {
		this(aBlock, aSide, aMeta, aBlock.getRenderColor(aMeta), F, aBlock == Blocks.fire || aBlock == Blocks.lava || aBlock == Blocks.flowing_lava || aBlock == Blocks.glowstone || aBlock == Blocks.lit_redstone_lamp);
	}
	
	private IIcon getIcon(int aSide) {
		try {
			if (mSide == SIDE_ANY) return mBlock.getIcon(aSide, mMeta);
			return mBlock.getIcon(mSide, mMeta);
		} catch(Throwable e) {
			return Textures.BlockIcons.RENDERING_ERROR.getIcon(0);
		}
	}
	
	@Override
	public void renderXPos(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		ITexture.Util.renderSide(SIDE_X_POS, getIcon(5), mRGBa, mAllowAlpha, mUseConstantBrightness, !mUseMaxBrightness, aRenderer, aBlock, aX, aY, aZ, mUseMaxBrightness?240:aBrightness, aChangedBlockBounds);
	}
	
	@Override
	public void renderXNeg(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		ITexture.Util.renderSide(SIDE_X_NEG, getIcon(4), mRGBa, mAllowAlpha, mUseConstantBrightness, !mUseMaxBrightness, aRenderer, aBlock, aX, aY, aZ, mUseMaxBrightness?240:aBrightness, aChangedBlockBounds);
	}
	
	@Override
	public void renderYPos(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		ITexture.Util.renderSide(SIDE_Y_POS, getIcon(1), mRGBa, mAllowAlpha, mUseConstantBrightness, !mUseMaxBrightness, aRenderer, aBlock, aX, aY, aZ, mUseMaxBrightness?240:aBrightness, aChangedBlockBounds);
	}
	
	@Override
	public void renderYNeg(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		ITexture.Util.renderSide(SIDE_Y_NEG, getIcon(0), mRGBa, mAllowAlpha, mUseConstantBrightness, !mUseMaxBrightness, aRenderer, aBlock, aX, aY, aZ, mUseMaxBrightness?240:aBrightness, aChangedBlockBounds);
	}
	
	@Override
	public void renderZPos(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		ITexture.Util.renderSide(SIDE_Z_POS, getIcon(3), mRGBa, mAllowAlpha, mUseConstantBrightness, !mUseMaxBrightness, aRenderer, aBlock, aX, aY, aZ, mUseMaxBrightness?240:aBrightness, aChangedBlockBounds);
	}
	
	@Override
	public void renderZNeg(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		ITexture.Util.renderSide(SIDE_Z_NEG, getIcon(2), mRGBa, mAllowAlpha, mUseConstantBrightness, !mUseMaxBrightness, aRenderer, aBlock, aX, aY, aZ, mUseMaxBrightness?240:aBrightness, aChangedBlockBounds);
	}
	
	@Override
	public boolean isValidTexture() {
		return mBlock != null && mBlock != NB;
	}
}
