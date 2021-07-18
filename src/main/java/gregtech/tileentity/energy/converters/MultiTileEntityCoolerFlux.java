/**
 * Copyright (c) 2021 GregTech-6 Team
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

package gregtech.tileentity.energy.converters;

import static gregapi.data.CS.*;

import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.energy.ITileEntityEnergyFluxHandler;
import gregapi.tileentity.energy.TileEntityBase11Twotypes;
import net.minecraft.block.Block;

public class MultiTileEntityCoolerFlux extends TileEntityBase11Twotypes implements ITileEntityEnergyFluxHandler {
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPOS[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get((mActivity.mState>0?sOverlaysActive:sOverlays)[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/overlay/side"),
	}, sOverlaysActive[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/cooler/cryo_flux/overlay_active/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.cooler.cryo_flux";}
}
