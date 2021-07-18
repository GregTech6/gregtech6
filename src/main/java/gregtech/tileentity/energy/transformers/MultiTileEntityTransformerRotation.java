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

import gregapi.data.LH;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.energy.ITileEntityEnergyElectricityAcceptor;
import gregapi.tileentity.energy.TileEntityBase11Bidirectional;
import net.minecraft.block.Block;

public class MultiTileEntityTransformerRotation extends TileEntityBase11Bidirectional implements ITileEntityEnergyElectricityAcceptor {
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?mNegativeInput?0:1:aSide==OPOS[mFacing]?mNegativeInput?1:0:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[mActivity.mState == 1 ? 1 : 0][aIndex], mRGBa), BlockTextureDefault.get(sOverlays[mActivity.mState == 1 ? 1 : 0][aIndex]));
	}
	
	@Override public boolean isInput (byte aSide) {return mReversed ? aSide == OPOS[mFacing] : aSide == mFacing;}
	@Override public boolean isOutput(byte aSide) {return mReversed ? aSide == mFacing : aSide == OPOS[mFacing];}
	@Override public String getLocalisedInputSide () {return LH.get(LH.FACE_FRONT);}
	@Override public String getLocalisedOutputSide() {return LH.get(LH.FACE_BACK);}
	
	// Icons
	public static IIconContainer sColoreds[][] = new IIconContainer[][] {{
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/colored/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/colored_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/colored_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/colored_active/side"),
	}}, sOverlays[][] = new IIconContainer[][] {{
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/overlay/side"),
	}, {
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/transformers/transformer_rotation/overlay_active/side"),
	}};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.transformers.transformer_rotation";}
}
