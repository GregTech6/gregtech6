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
import gregapi.tileentity.energy.ITileEntityEnergyElectricityAcceptor;
import gregapi.tileentity.energy.TileEntityBase11Bipolar;
import net.minecraft.block.Block;

public class MultiTileEntityEngineRotation extends TileEntityBase11Bipolar implements ITileEntityEnergyElectricityAcceptor {
	@Override
	public void doConversion(long aTimer) {
		mActivity.mActive = mConverter.doBipolar(aTimer, this, aTimer % 32 < 16 ? OPPOSITES[mFacing] : mFacing, aTimer % 32 < 16 ? mFacing : OPPOSITES[mFacing], mMode);
		if (mConverter.mOverloaded) {
			overload(mStorage.mEnergy, mConverter.mEnergyOUT.mType);
			mConverter.mOverloaded = F;
			mStorage.mEnergy = 0;
		}
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPPOSITES[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get((mActivity.mState>0?sColoredsActive:sColoreds)[aIndex], mRGBa), BlockTextureDefault.get((mActivity.mState>0?sOverlaysActive:sOverlays)[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_rotation/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_rotation/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_rotation/colored/side"),
	}, sColoredsActive[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_rotation/colored_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_rotation/colored_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_rotation/colored_active/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_rotation/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_rotation/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_rotation/overlay/side"),
	}, sOverlaysActive[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_rotation/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_rotation/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/engines/kinetic_rotation/overlay_active/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.engines.kinetic_rotation";}
}
