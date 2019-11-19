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

import gregapi.GT_API_Proxy;
import gregapi.cover.CoverData;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class CoverRedstoneConductorOUT extends AbstractCoverAttachment {
	@Override
	public byte getRedstoneOutWeak(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {
		return UT.Code.bind4(aData.mValues[aCoverSide]);
	}
	
	@Override
	public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		super.onCoverPlaced(aCoverSide, aData, aPlayer, aCover);
		onBlockUpdate(aCoverSide, aData);
	}
	
	@Override
	public void onBlockUpdate(byte aCoverSide, CoverData aData) {
		byte tEmitted = 0;
		for (byte tSide : ALL_SIDES_VALID) if (aData.mBehaviours[tSide] instanceof CoverRedstoneConductorIN) tEmitted = (byte)Math.max(tEmitted, aData.mTileEntity.getRedstoneIncoming(tSide));
		tEmitted = UT.Code.bind4(tEmitted);
		if (aData.mValues[aCoverSide] != tEmitted) {
			aData.mValues[aCoverSide] = tEmitted;
			if (!GT_API_Proxy.DELAYED_BLOCK_UPDATES.contains(aData.mTileEntity)) GT_API_Proxy.DELAYED_BLOCK_UPDATES.add(aData.mTileEntity);
		}
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return sTexture;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? BACKGROUND_COVER : BlockTextureMulti.get(BACKGROUND_COVER, sTexture);}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	
	public static final ITexture sTexture = BlockTextureDefault.get("machines/covers/redstoneconductor/out");
}
