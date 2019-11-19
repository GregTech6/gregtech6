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

import gregapi.code.TagData;
import gregapi.cover.CoverData;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.energy.ITileEntityEnergyDataCapacitor;
import gregapi.util.UT;
import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public class CoverDisplayEnergy extends AbstractCoverAttachmentDisplay {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity.canTick() && aData.mTileEntity instanceof ITileEntityEnergyDataCapacitor);}
	
	@Override
	public void onTickPost(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && aData.mTileEntity instanceof ITileEntityEnergyDataCapacitor && !((ITileEntityEnergyDataCapacitor)aData.mTileEntity).getEnergyCapacitorTypes(aSide).isEmpty()) {
			TagData tEnergyType = ((ITileEntityEnergyDataCapacitor)aData.mTileEntity).getEnergyCapacitorTypes(aSide).iterator().next();
			long tStored = ((ITileEntityEnergyDataCapacitor)aData.mTileEntity).getEnergyStored(tEnergyType, aSide), tCapacity = ((ITileEntityEnergyDataCapacitor)aData.mTileEntity).getEnergyCapacity(tEnergyType, aSide);
			aData.visual(aSide, (short)(tStored <= 0 || tCapacity <= 0 ? 0 : tStored >= tCapacity ? 10 : 9-(int)Math.max(0, Math.min(8, ((tCapacity-tStored)*9L) / tCapacity))));
		}
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return BlockTextureMulti.get(sTexturesBase, sTextures[(int)UT.Code.bind_(0, 10, aData.mVisuals[aSide])]);}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? BACKGROUND_COVER : BlockTextureMulti.get(BACKGROUND_COVER, getCoverTextureSurface(aSide, aData));}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
	
	public static final ITexture[] sTextures = new ITexture[] {
		  BlockTextureDefault.get("machines/covers/energydisplay/0", T)
		, BlockTextureDefault.get("machines/covers/energydisplay/1", T)
		, BlockTextureDefault.get("machines/covers/energydisplay/2", T)
		, BlockTextureDefault.get("machines/covers/energydisplay/3", T)
		, BlockTextureDefault.get("machines/covers/energydisplay/4", T)
		, BlockTextureDefault.get("machines/covers/energydisplay/5", T)
		, BlockTextureDefault.get("machines/covers/energydisplay/6", T)
		, BlockTextureDefault.get("machines/covers/energydisplay/7", T)
		, BlockTextureDefault.get("machines/covers/energydisplay/8", T)
		, BlockTextureDefault.get("machines/covers/energydisplay/9", T)
		, BlockTextureDefault.get("machines/covers/energydisplay/10", T)
	};
	
	public static final ITexture sTexturesBase = BlockTextureDefault.get("machines/covers/energydisplay/underlay");
}
