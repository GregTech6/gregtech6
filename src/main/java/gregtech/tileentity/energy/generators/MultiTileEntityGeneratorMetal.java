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

package gregtech.tileentity.energy.generators;

import static gregapi.data.CS.*;

import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import net.minecraft.block.Block;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityGeneratorMetal extends MultiTileEntityGeneratorSolid {
	
	@Override public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACING_ROTATIONS[mFacing][aSide]], mRGBa), BlockTextureDefault.get((mBurning?sOverlaysActive:sOverlays)[FACING_ROTATIONS[mFacing][aSide]])): null;}
	
	// Icons
	public static IIconContainer[] sColoreds = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/colored/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/colored/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/colored/back")
	}, sOverlays = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay/back")
	}, sOverlaysActive = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay_active/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay_active/top"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay_active/left"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay_active/right"),
		new Textures.BlockIcons.CustomIcon("machines/generators/burning_solid/overlay_active/back")
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.generator.burning_solid";}
}
