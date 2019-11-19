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
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntityRunningPossible;
import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public class CoverControllerAuto extends AbstractCoverAttachmentController {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof ITileEntityRunningPossible) || !aData.mTileEntity.canTick() || super.interceptCoverPlacement(aCoverSide, aData, aPlayer);}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return sTextureForeground;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? BACKGROUND_COVER : BlockTextureMulti.get(BACKGROUND_COVER, sTextureForeground);}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	
	public static final ITexture sTextureForeground = BlockTextureDefault.get("machines/covers/autoswitch/circuit");
	
	@Override
	public boolean getStateOnOff(byte aSide, CoverData aData) {
		return (aData.mTileEntity instanceof ITileEntityRunningPossible && ((ITileEntityRunningPossible)aData.mTileEntity).getStateRunningPossible()) || (aData.mTileEntity instanceof ITileEntityRunningActively && ((ITileEntityRunningActively)aData.mTileEntity).getStateRunningActively());
	}
}
