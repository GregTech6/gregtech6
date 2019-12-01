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

package gregapi.cover.covers;

import static gregapi.data.CS.*;

import gregapi.cover.CoverData;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class CoverSelectorRedstone extends AbstractCoverAttachmentSelector {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof ITileEntitySwitchableMode);}
	
	@Override
	public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		super.onCoverPlaced(aCoverSide, aData, aPlayer, aCover);
		if (!aData.mStopped && aData.mTileEntity instanceof ITileEntitySwitchableMode) aData.visual(aCoverSide, ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(aData.mTileEntity.getRedstoneIncoming(aCoverSide)));
	}
	@Override
	public void onCoverLoaded(byte aSide, CoverData aData) {
		super.onCoverLoaded(aSide, aData);
		if (aData.mTileEntity instanceof ITileEntitySwitchableMode) ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(aData.mTileEntity.getRedstoneIncoming(aSide));
	}
	@Override
	public void onBlockUpdate(byte aSide, CoverData aData) {
		if (!aData.mStopped && aData.mTileEntity instanceof ITileEntitySwitchableMode) aData.visual(aSide, ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(aData.mTileEntity.getRedstoneIncoming(aSide)));
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return BlockTextureMulti.get(sTexturesBase, sTextures[UT.Code.bind4(aData.mVisuals[aSide])]);}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? BACKGROUND_COVER : BlockTextureMulti.get(BACKGROUND_COVER, getCoverTextureSurface(aSide, aData));}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	
	public static final ITexture[] sTextures = new ITexture[] {
		  BlockTextureDefault.get("machines/covers/redstoneselector/0", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/1", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/2", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/3", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/4", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/5", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/6", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/7", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/8", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/9", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/10", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/11", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/12", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/13", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/14", T)
		, BlockTextureDefault.get("machines/covers/redstoneselector/15", T)
	};
	
	public static final ITexture sTexturesBase = BlockTextureDefault.get("machines/covers/redstoneselector/underlay");
}
