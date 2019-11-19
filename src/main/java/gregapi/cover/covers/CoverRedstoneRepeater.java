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

import gregapi.cover.CoverData;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.tileentity.connectors.MultiTileEntityWireRedstoneInsulated;

/**
 * @author Gregorius Techneticies
 */
public class CoverRedstoneRepeater extends AbstractCoverAttachmentTorch {
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return aData.mVisuals[aSide] == 0 ? sTextureFrontON : sTextureFrontOFF;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide == aTextureSide ? aData.mVisuals[aSide] == 0 ? sTextureFrontON : sTextureFrontOFF : null;}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return aSide == aTextureSide ? null : aData.mVisuals[aSide] == 0 ? sTextureSideON : sTextureSideOFF;}
	
	public static final ITexture
	sTextureFrontON     = BlockTextureDefault.get("machines/covers/redstonerepeater/on/front"),
	sTextureSideON      = BlockTextureDefault.get("machines/covers/redstonerepeater/on/side"),
	sTextureFrontOFF    = BlockTextureDefault.get("machines/covers/redstonerepeater/off/front"),
	sTextureSideOFF     = BlockTextureDefault.get("machines/covers/redstonerepeater/off/side");
	
	@Override
	public boolean condition(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		return ((MultiTileEntityWireRedstoneInsulated)aData.mTileEntity).mRedstone <= 0;
	}
}
