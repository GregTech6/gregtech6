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
public class CoverSelectorTag extends AbstractCoverAttachmentSelector {
	public final byte mMode;
	
	public CoverSelectorTag(byte aMode) {mMode = UT.Code.bind4(aMode);}
	
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof ITileEntitySwitchableMode);}
	
	@Override
	public void onCoverPlaced(byte aSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		super.onCoverPlaced(aSide, aData, aPlayer, aCover);
		if (aData.mTileEntity instanceof ITileEntitySwitchableMode) ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(mMode);
	}
	@Override
	public void onCoverLoaded(byte aSide, CoverData aData) {
		super.onCoverLoaded(aSide, aData);
		if (aData.mTileEntity instanceof ITileEntitySwitchableMode) ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(mMode);
	}
	
	@Override
	public void onTickPre(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && aData.mTileEntity instanceof ITileEntitySwitchableMode) ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(mMode);
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return BlockTextureMulti.get(sTexturesBase, sTextures[mMode]);}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? BACKGROUND_COVER : BlockTextureMulti.get(BACKGROUND_COVER, getCoverTextureSurface(aSide, aData));}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	
	public static final ITexture[] sTextures = new ITexture[] {
		  BlockTextureDefault.get("machines/covers/selectortag/0", T)
		, BlockTextureDefault.get("machines/covers/selectortag/1", T)
		, BlockTextureDefault.get("machines/covers/selectortag/2", T)
		, BlockTextureDefault.get("machines/covers/selectortag/3", T)
		, BlockTextureDefault.get("machines/covers/selectortag/4", T)
		, BlockTextureDefault.get("machines/covers/selectortag/5", T)
		, BlockTextureDefault.get("machines/covers/selectortag/6", T)
		, BlockTextureDefault.get("machines/covers/selectortag/7", T)
		, BlockTextureDefault.get("machines/covers/selectortag/8", T)
		, BlockTextureDefault.get("machines/covers/selectortag/9", T)
		, BlockTextureDefault.get("machines/covers/selectortag/10", T)
		, BlockTextureDefault.get("machines/covers/selectortag/11", T)
		, BlockTextureDefault.get("machines/covers/selectortag/12", T)
		, BlockTextureDefault.get("machines/covers/selectortag/13", T)
		, BlockTextureDefault.get("machines/covers/selectortag/14", T)
		, BlockTextureDefault.get("machines/covers/selectortag/15", T)
	};
	
	public static final ITexture sTexturesBase = BlockTextureDefault.get("machines/covers/selectortag/underlay");
}
