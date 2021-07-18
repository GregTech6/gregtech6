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
import gregapi.tileentity.energy.TileEntityBase11Motor;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;

public class MultiTileEntityMotorElectric extends TileEntityBase11Motor implements ITileEntityEnergyElectricityAcceptor, ITileEntitySwitchableMode {
	@Override public void onWalkOver2(EntityLivingBase aEntity) {if (SIDES_TOP[mFacing] && mActivity.mState>0) {aEntity.rotationYaw=aEntity.rotationYaw+(mCounterClockwise?-5:+5)*(mConverter.mFast?2:1); aEntity.rotationYawHead=aEntity.rotationYawHead+(mCounterClockwise?-5:+5)*(mConverter.mFast?2:1);}}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPOS[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get((mActivity.mState>0?mCounterClockwise?(mConverter.mFast?sOverlaysActiveLF:sOverlaysActiveLS):(mConverter.mFast?sOverlaysActiveRF:sOverlaysActiveRS):sOverlays)[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay/side"),
	}, sOverlaysActiveLS[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay_active_ls/front"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay_active_ls/back"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay_active_ls/side"),
	}, sOverlaysActiveLF[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay_active_lf/front"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay_active_lf/back"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay_active_lf/side"),
	}, sOverlaysActiveRS[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay_active_rs/front"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay_active_rs/back"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay_active_rs/side"),
	}, sOverlaysActiveRF[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay_active_rf/front"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay_active_rf/back"),
		new Textures.BlockIcons.CustomIcon("machines/motors/rotation_electric/overlay_active_rf/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.motors.rotation_electric";}
}
