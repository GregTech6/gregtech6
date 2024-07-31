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

package gregtech.tileentity.energy.storage;

import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import net.minecraft.block.Block;

public class MultiTileEntityCrystalChargerLarge extends MultiTileEntityCrystalCharger {
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aSide==mFacing?0:1], mRGBa), BlockTextureDefault.get(sOverlays[0][aSide==mFacing?0:1]), (mActiveState&3)==0?null:BlockTextureDefault.get(sOverlays[mActiveState & 3][aSide==mFacing?0:1],true));
	}
	
	// Icons
	private static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/colored/side"),
	}, sOverlays[][] = new IIconContainer[][] {{
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/overlay/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/overlay_active/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/overlay_blinking/front"),
		new Textures.BlockIcons.CustomIcon("machines/energystorages/crystal_laser_large/overlay_blinking/side"),
	}};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.energystorages.crystal_laser_large";}
}
