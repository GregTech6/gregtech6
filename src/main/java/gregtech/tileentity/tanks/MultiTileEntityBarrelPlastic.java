/**
 * Copyright (c) 2023 GregTech-6 Team
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

package gregtech.tileentity.tanks;

import gregapi.cover.ICover;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.tank.TileEntityBase08Barrel;
import net.minecraft.block.Block;

import static gregapi.data.CS.FACES_TBS;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBarrelPlastic extends TileEntityBase08Barrel {
	@Override public boolean allowCover(byte aSide, ICover aCover) {return aCover.isDecorative(aSide, getCoverData());}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlays[FACES_TBS[aSide]])) : null;
	}
	
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/plasticcan/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.tank.barrel.plastic";}
}
