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

package gregtech.tileentity.energy.transformers;

import static gregapi.data.CS.*;

import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.energy.ITileEntityEnergyElectricityAcceptor;
import gregapi.tileentity.energy.TileEntityBase11Bidirectional;
import net.minecraft.block.Block;

public class MultiTileEntityTransformerElectric extends TileEntityBase11Bidirectional implements ITileEntityEnergyElectricityAcceptor {
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPOS[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get(sOverlays[mActivity.mState][aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/colored/side"),
	}, sOverlays[][] = new IIconContainer[][] {{
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay_active/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay_blinking/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay_blinking/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_electric/overlay_blinking/side"),
	}};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.transformers.transformer_electric";}
}
