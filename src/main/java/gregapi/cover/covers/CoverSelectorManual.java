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
public class CoverSelectorManual extends AbstractCoverAttachmentSelector {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof ITileEntitySwitchableMode);}
	
	@Override
	public void onCoverPlaced(byte aSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		super.onCoverPlaced(aSide, aData, aPlayer, aCover);
		if (aData.mTileEntity instanceof ITileEntitySwitchableMode) aData.visual(aSide, UT.Code.bind4(((ITileEntitySwitchableMode)aData.mTileEntity).getStateMode()));
	}
	@Override
	public void onCoverLoaded(byte aSide, CoverData aData) {
		super.onCoverLoaded(aSide, aData);
		if (aData.mTileEntity instanceof ITileEntitySwitchableMode) ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(UT.Code.bind4(aData.mVisuals[aSide] & 15));
	}
	@Override
	public void onBlockUpdate(byte aSide, CoverData aData) {
		if (!aData.mStopped && aData.mTileEntity instanceof ITileEntitySwitchableMode) aData.visual(aSide, UT.Code.bind4(((ITileEntitySwitchableMode)aData.mTileEntity).getStateMode()));
	}
	
	@Override
	public boolean onCoverClickedRight(byte aSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (!aData.mStopped && aSide == aSideClicked && aData.mTileEntity instanceof ITileEntitySwitchableMode) {
			boolean rReturn = F;
			byte tMode = UT.Code.bind4(aData.mVisuals[aSide]);
			float[] tCoords = UT.Code.getFacingCoordsClicked(aSideClicked, aHitX, aHitY, aHitZ);
			if (tCoords[1] >= PX_P[1] && tCoords[1] <= PX_P[4]) {
				if (tCoords[0] >= PX_P[1] && tCoords[0] <= PX_P[4]) {
					tMode--;
					if (tMode < 0) tMode = 15;
					rReturn = T;
				} else
				if (tCoords[0] >= PX_N[4] && tCoords[0] <= PX_N[1]) {
					tMode++;
					if (tMode > 15) tMode = 0;
					rReturn = T;
				}
			} else
			if (tCoords[1] >= PX_N[7] && tCoords[1] <= PX_N[4]) {
				if (tCoords[0] >= PX_P[2] && tCoords[0] <= PX_N[2]) {
					if (tCoords[0] <= PX_P[5]) {
						tMode ^= 8;
					} else
					if (tCoords[0] <= PX_P[8]) {
						tMode ^= 4;
					} else
					if (tCoords[0] <= PX_N[5]) {
						tMode ^= 2;
					} else {
						tMode ^= 1;
					}
					rReturn = T;
				}
			}
			if (aData.mTileEntity.isServerSide()) aData.visual(aSide, ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(tMode));
			return rReturn;
		}
		return F;
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return BlockTextureMulti.get(sTexturesBase, sTextures[UT.Code.bind4(aData.mVisuals[aSide])]);}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? BACKGROUND_COVER : BlockTextureMulti.get(BACKGROUND_COVER, getCoverTextureSurface(aSide, aData));}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	
	public static final ITexture[] sTextures = new ITexture[] {
		  BlockTextureDefault.get("machines/covers/manualselector/0", T)
		, BlockTextureDefault.get("machines/covers/manualselector/1", T)
		, BlockTextureDefault.get("machines/covers/manualselector/2", T)
		, BlockTextureDefault.get("machines/covers/manualselector/3", T)
		, BlockTextureDefault.get("machines/covers/manualselector/4", T)
		, BlockTextureDefault.get("machines/covers/manualselector/5", T)
		, BlockTextureDefault.get("machines/covers/manualselector/6", T)
		, BlockTextureDefault.get("machines/covers/manualselector/7", T)
		, BlockTextureDefault.get("machines/covers/manualselector/8", T)
		, BlockTextureDefault.get("machines/covers/manualselector/9", T)
		, BlockTextureDefault.get("machines/covers/manualselector/10", T)
		, BlockTextureDefault.get("machines/covers/manualselector/11", T)
		, BlockTextureDefault.get("machines/covers/manualselector/12", T)
		, BlockTextureDefault.get("machines/covers/manualselector/13", T)
		, BlockTextureDefault.get("machines/covers/manualselector/14", T)
		, BlockTextureDefault.get("machines/covers/manualselector/15", T)
	};
	
	public static final ITexture sTexturesBase = BlockTextureDefault.get("machines/covers/manualselector/underlay");
}
