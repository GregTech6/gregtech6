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

import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.oredict.OreDictPrefix;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;

/** 
 * @author Gregorius Techneticies
 */
public class BlockTextureDefault implements ITexture {
	private final IIconContainer mIconContainer;
	
	/**
	 *  DO NOT MANIPULATE THE VALUES INSIDE THIS ARRAY!!!
	 *  
	 *  Just set this variable to another different Array instead.
	 *  Otherwise some colored things will get Problems.
	 */
	public short[] fRGBa;
	
	private final boolean mAllowAlpha, mUseOwnBrightness, mUseConstantBrightness, mEnableAO;
	private final short mBrightness;

	public static BlockTextureDefault get(OreDictMaterial aMaterial, OreDictPrefix aPrefix, boolean aGlow, boolean aEnableAO) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aMaterial, aPrefix, aGlow, aEnableAO):null;
	}
	public static BlockTextureDefault get(OreDictMaterial aMaterial, OreDictPrefix aPrefix, short[] aRGBa, boolean aGlow, boolean aEnableAO) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aMaterial, aPrefix, aRGBa, aGlow, aEnableAO):null;
	}
	public static BlockTextureDefault get(OreDictMaterial aMaterial, int aTextureSetIndex, int aState, boolean aGlow, boolean aEnableAO) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aMaterial, aTextureSetIndex, aState, aGlow, aEnableAO):null;
	}
	public static BlockTextureDefault get(OreDictMaterial aMaterial, int aTextureSetIndex, short[] aRGBa, boolean aGlow, boolean aEnableAO) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aMaterial, aTextureSetIndex, aRGBa, aGlow, aEnableAO):null;
	}
	public static BlockTextureDefault get(OreDictMaterial aMaterial, OreDictPrefix aPrefix) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aMaterial, aPrefix):null;
	}
	public static BlockTextureDefault get(OreDictMaterial aMaterial, OreDictPrefix aPrefix, boolean aGlow) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aMaterial, aPrefix, aGlow):null;
	}
	public static BlockTextureDefault get(OreDictMaterial aMaterial, OreDictPrefix aPrefix, short[] aRGBa, boolean aGlow) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aMaterial, aPrefix, aRGBa, aGlow):null;
	}
	public static BlockTextureDefault get(OreDictMaterial aMaterial, int aTextureSetIndex, int aState, boolean aGlow) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aMaterial, aTextureSetIndex, aState, aGlow):null;
	}
	public static BlockTextureDefault get(OreDictMaterial aMaterial, int aTextureSetIndex, boolean aGlow, int aRGBa) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aMaterial, aTextureSetIndex, aGlow, aRGBa):null;
	}
	public static BlockTextureDefault get(OreDictMaterial aMaterial, int aTextureSetIndex, short[] aRGBa, boolean aGlow) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aMaterial, aTextureSetIndex, aRGBa, aGlow):null;
	}
	public static BlockTextureDefault get(OreDictMaterial aMaterial, int aTextureSetIndex, boolean aGlow) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aMaterial, aTextureSetIndex, aGlow):null;
	}
	public static BlockTextureDefault get(OreDictMaterial aMaterial, int aTextureSetIndex) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aMaterial, aTextureSetIndex):null;
	}
	public static BlockTextureDefault get(IIconContainer aIcon, int aRGBa, boolean aGlow) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aIcon, aRGBa, aGlow):null;
	}
	public static BlockTextureDefault get(IIconContainer aIcon, boolean aGlow) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aIcon, aGlow):null;
	}
	public static BlockTextureDefault get(IIconContainer aIcon, int aRGBa) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aIcon, aRGBa):null;
	}
	public static BlockTextureDefault get(IIconContainer aIcon, short[] aRGBa) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aIcon, aRGBa):null;
	}
	public static BlockTextureDefault get(IIconContainer aIcon, int aRGBa, boolean aAllowAlpha, boolean aUseMaxBrightness, boolean aUseConstantBrightness, boolean aEnableAO) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aIcon, aRGBa, aAllowAlpha, aUseMaxBrightness, aUseConstantBrightness, aEnableAO):null;
	}
	public static BlockTextureDefault get(IIconContainer aIcon, short[] aRGBa, boolean aAllowAlpha, boolean aUseMaxBrightness, boolean aUseConstantBrightness, boolean aEnableAO) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aIcon, aRGBa, aAllowAlpha, aUseMaxBrightness, aUseConstantBrightness, aEnableAO):null;
	}
	public static BlockTextureDefault get(IIconContainer aIcon) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aIcon):null;
	}
	public static BlockTextureDefault get(String aIcon, int aRGBa, boolean aGlow) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(new Textures.BlockIcons.CustomIcon(aIcon), aRGBa, aGlow):null;
	}
	public static BlockTextureDefault get(String aIcon, boolean aGlow) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(new Textures.BlockIcons.CustomIcon(aIcon), aGlow):null;
	}
	public static BlockTextureDefault get(String aIcon, int aRGBa) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(new Textures.BlockIcons.CustomIcon(aIcon), aRGBa):null;
	}
	public static BlockTextureDefault get(String aIcon, short[] aRGBa) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(new Textures.BlockIcons.CustomIcon(aIcon), aRGBa):null;
	}
	public static BlockTextureDefault get(String aIcon, int aRGBa, boolean aAllowAlpha, boolean aUseMaxBrightness, boolean aUseConstantBrightness, boolean aEnableAO) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(new Textures.BlockIcons.CustomIcon(aIcon), aRGBa, aAllowAlpha, aUseMaxBrightness, aUseConstantBrightness, aEnableAO):null;
	}
	public static BlockTextureDefault get(String aIcon, short[] aRGBa, boolean aAllowAlpha, boolean aUseMaxBrightness, boolean aUseConstantBrightness, boolean aEnableAO) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(new Textures.BlockIcons.CustomIcon(aIcon), aRGBa, aAllowAlpha, aUseMaxBrightness, aUseConstantBrightness, aEnableAO):null;
	}
	public static BlockTextureDefault get(String aIcon) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(new Textures.BlockIcons.CustomIcon(aIcon)):null;
	}
	public static BlockTextureDefault get(IIconContainer aIcon, short[] aRGBa, int aBrightness) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aIcon, aRGBa, aBrightness, F):null;
	}
	public static BlockTextureDefault get(IIconContainer aIcon, short[] aRGBa, int aBrightness, boolean aAllowAlpha) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aIcon, aRGBa, aBrightness, aAllowAlpha):null;
	}
	public static BlockTextureDefault get(IIconContainer aIcon, int aRGBa, int aBrightness) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aIcon, UT.Code.getRGBaArray(aRGBa), aBrightness, F):null;
	}
	public static BlockTextureDefault get(IIconContainer aIcon, int aRGBa, int aBrightness, boolean aAllowAlpha) {
		return CODE_CLIENT||CODE_UNCHECKED?new BlockTextureDefault(aIcon, UT.Code.getRGBaArray(aRGBa), aBrightness, aAllowAlpha):null;
	}
	
	public BlockTextureDefault(OreDictMaterial aMaterial, OreDictPrefix aPrefix, boolean aGlow, boolean aEnableAO) {
		this(aMaterial, aPrefix, aMaterial.fRGBa[aPrefix.mState], aGlow, aEnableAO);
	}
	public BlockTextureDefault(OreDictMaterial aMaterial, OreDictPrefix aPrefix, short[] aRGBa, boolean aGlow, boolean aEnableAO) {
		this(aMaterial, aPrefix.mIconIndexBlock, aRGBa, aGlow, aEnableAO);
	}
	public BlockTextureDefault(OreDictMaterial aMaterial, int aTextureSetIndex, int aState, boolean aGlow, boolean aEnableAO) {
		this(aMaterial, aTextureSetIndex, aMaterial.fRGBa[aState], aGlow, aEnableAO);
	}
	public BlockTextureDefault(OreDictMaterial aMaterial, int aTextureSetIndex, short[] aRGBa, boolean aGlow, boolean aEnableAO) {
		this(aMaterial.mTextureSetsBlock.get(aTextureSetIndex), aRGBa, F, aGlow, F, aEnableAO);
	}
	public BlockTextureDefault(OreDictMaterial aMaterial, OreDictPrefix aPrefix) {
		this(aMaterial, aPrefix, aMaterial.fRGBa[aPrefix.mState], aMaterial.contains(TD.Properties.GLOWING));
	}
	public BlockTextureDefault(OreDictMaterial aMaterial, OreDictPrefix aPrefix, boolean aGlow) {
		this(aMaterial, aPrefix, aMaterial.fRGBa[aPrefix.mState], aGlow);
	}
	public BlockTextureDefault(OreDictMaterial aMaterial, OreDictPrefix aPrefix, short[] aRGBa, boolean aGlow) {
		this(aMaterial, aPrefix.mIconIndexBlock, aRGBa, aGlow);
	}
	public BlockTextureDefault(OreDictMaterial aMaterial, int aTextureSetIndex, int aState, boolean aGlow) {
		this(aMaterial, aTextureSetIndex, aMaterial.fRGBa[aState], aGlow);
	}
	public BlockTextureDefault(OreDictMaterial aMaterial, int aTextureSetIndex, boolean aGlow, int aRGBa) {
		this(aMaterial, aTextureSetIndex, UT.Code.getRGBaArray(aRGBa), aGlow);
	}
	public BlockTextureDefault(OreDictMaterial aMaterial, int aTextureSetIndex, short[] aRGBa, boolean aGlow) {
		this(aMaterial.mTextureSetsBlock.get(aTextureSetIndex), aRGBa, F, aGlow, F, !aGlow);
	}
	public BlockTextureDefault(OreDictMaterial aMaterial, int aTextureSetIndex, boolean aGlow) {
		this(aMaterial, aTextureSetIndex, STATE_SOLID, aGlow);
	}
	public BlockTextureDefault(OreDictMaterial aMaterial, int aTextureSetIndex) {
		this(aMaterial, aTextureSetIndex, F);
	}
	public BlockTextureDefault(IIconContainer aIcon, int aRGBa, boolean aGlow) {
		this(aIcon, aRGBa, F, aGlow, F, !aGlow);
	}
	public BlockTextureDefault(IIconContainer aIcon, boolean aGlow) {
		this(aIcon, UNCOLOURED, F, aGlow, F, !aGlow);
	}
	public BlockTextureDefault(IIconContainer aIcon, int aRGBa) {
		this(aIcon, aRGBa, F, F, F, T);
	}
	public BlockTextureDefault(IIconContainer aIcon, short[] aRGBa) {
		this(aIcon, aRGBa, F, F, F, T);
	}
	public BlockTextureDefault(IIconContainer aIcon, int aRGBa, boolean aAllowAlpha, boolean aUseMaxBrightness, boolean aUseConstantBrightness, boolean aEnableAO) {
		this(aIcon, UT.Code.getRGBaArray(aRGBa), aAllowAlpha, aUseMaxBrightness, aUseConstantBrightness, aEnableAO);
	}
	public BlockTextureDefault(IIconContainer aIcon, short[] aRGBa, boolean aAllowAlpha, boolean aUseMaxBrightness, boolean aUseConstantBrightness, boolean aEnableAO) {
		if (aRGBa.length != 4) throw new IllegalArgumentException("RGBa doesn't have 4 Values @ BlockTextureDefault");
		mIconContainer = aIcon;
		mBrightness = 240;
		mUseOwnBrightness = aUseMaxBrightness;
		mUseConstantBrightness = aUseMaxBrightness || aUseConstantBrightness;
		mAllowAlpha = aAllowAlpha;
		mEnableAO = aEnableAO;
		fRGBa = aRGBa;
	}
	public BlockTextureDefault(IIconContainer aIcon, short[] aRGBa, int aBrightness, boolean aAllowAlpha) {
		if (aRGBa.length != 4) throw new IllegalArgumentException("RGBa doesn't have 4 Values @ BlockTextureDefault");
		mIconContainer = aIcon;
		mBrightness = (short)aBrightness;
		mUseOwnBrightness = T;
		mUseConstantBrightness = T;
		mAllowAlpha = aAllowAlpha;
		mEnableAO = F;
		fRGBa = aRGBa;
	}
	public BlockTextureDefault(IIconContainer aIcon) {
		this(aIcon, UNCOLOURED, F, F, F, T);
	}
	
	@Override
	public void renderXPos(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		for (int i = 0, j = mIconContainer.getIconPasses(); i < j; i++)
		ITexture.Util.renderSide(SIDE_X_POS, mIconContainer.getIcon(i), mIconContainer.isUsingColorModulation(i)?fRGBa:mIconContainer.getIconColor(i), mAllowAlpha, mUseConstantBrightness, mEnableAO, aRenderer, aBlock, aX, aY, aZ, mUseOwnBrightness?mBrightness:aBrightness, aChangedBlockBounds);
	}
	
	@Override
	public void renderXNeg(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		for (int i = 0, j = mIconContainer.getIconPasses(); i < j; i++)
		ITexture.Util.renderSide(SIDE_X_NEG, mIconContainer.getIcon(i), mIconContainer.isUsingColorModulation(i)?fRGBa:mIconContainer.getIconColor(i), mAllowAlpha, mUseConstantBrightness, mEnableAO, aRenderer, aBlock, aX, aY, aZ, mUseOwnBrightness?mBrightness:aBrightness, aChangedBlockBounds);
	}
	
	@Override
	public void renderYPos(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		for (int i = 0, j = mIconContainer.getIconPasses(); i < j; i++)
		ITexture.Util.renderSide(SIDE_Y_POS, mIconContainer.getIcon(i), mIconContainer.isUsingColorModulation(i)?fRGBa:mIconContainer.getIconColor(i), mAllowAlpha, mUseConstantBrightness, mEnableAO, aRenderer, aBlock, aX, aY, aZ, mUseOwnBrightness?mBrightness:aBrightness, aChangedBlockBounds);
	}
	
	@Override
	public void renderYNeg(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		for (int i = 0, j = mIconContainer.getIconPasses(); i < j; i++)
		ITexture.Util.renderSide(SIDE_Y_NEG, mIconContainer.getIcon(i), mIconContainer.isUsingColorModulation(i)?fRGBa:mIconContainer.getIconColor(i), mAllowAlpha, mUseConstantBrightness, mEnableAO, aRenderer, aBlock, aX, aY, aZ, mUseOwnBrightness?mBrightness:aBrightness, aChangedBlockBounds);
	}
	
	@Override
	public void renderZPos(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		for (int i = 0, j = mIconContainer.getIconPasses(); i < j; i++)
		ITexture.Util.renderSide(SIDE_Z_POS, mIconContainer.getIcon(i), mIconContainer.isUsingColorModulation(i)?fRGBa:mIconContainer.getIconColor(i), mAllowAlpha, mUseConstantBrightness, mEnableAO, aRenderer, aBlock, aX, aY, aZ, mUseOwnBrightness?mBrightness:aBrightness, aChangedBlockBounds);
	}
	
	@Override
	public void renderZNeg(RenderBlocks aRenderer, Block aBlock, int aX, int aY, int aZ, int aBrightness, boolean aChangedBlockBounds) {
		for (int i = 0, j = mIconContainer.getIconPasses(); i < j; i++)
		ITexture.Util.renderSide(SIDE_Z_NEG, mIconContainer.getIcon(i), mIconContainer.isUsingColorModulation(i)?fRGBa:mIconContainer.getIconColor(i), mAllowAlpha, mUseConstantBrightness, mEnableAO, aRenderer, aBlock, aX, aY, aZ, mUseOwnBrightness?mBrightness:aBrightness, aChangedBlockBounds);
	}
	
	public short[] getRGBA() {
		return fRGBa;
	}
	
	@Override
	public boolean isValidTexture() {
		return mIconContainer != null;
	}
}
