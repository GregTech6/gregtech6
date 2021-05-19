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

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_GetCollisionBoundingBoxFromPool;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnEntityCollidedWithBlock;
import gregapi.data.LH;
import gregapi.data.LH.Chat;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.energy.ITileEntityEnergyFluxHandler;
import gregapi.tileentity.energy.TileEntityBase10EnergyConverter;
import gregapi.tileentity.machines.ITileEntityAdjacentOnOff;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

public class MultiTileEntityHeaterFlux extends TileEntityBase10EnergyConverter implements ITileEntityEnergyFluxHandler, ITileEntityAdjacentOnOff, IMTE_GetCollisionBoundingBoxFromPool, IMTE_OnEntityCollidedWithBlock, ITileEntitySwitchableMode {
	@Override public void onEntityCollidedWithBlock(Entity aEntity) {if (mActivity.mState>0) UT.Entities.applyHeatDamage(aEntity, Math.min(10.0F, mConverter.mEnergyOUT.mRec / 10.0F));}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box((mFacing==SIDE_X_NEG?PX_P[2]:0), (mFacing==SIDE_Y_NEG?PX_P[2]:0), (mFacing==SIDE_Z_NEG?PX_P[2]:0), (mFacing==SIDE_X_POS?PX_N[2]:1), (mFacing==SIDE_Y_POS?PX_N[2]:1), (mFacing==SIDE_Z_POS?PX_N[2]:1));}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(Chat.DRED     + LH.get(LH.HAZARD_CONTACT) + " (" + LH.get(LH.FACE_FRONT) + ")");
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		int aIndex = aSide==mFacing?0:aSide==OPPOSITES[mFacing]?1:2;
		return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa), BlockTextureDefault.get((mActivity.mState>0?sOverlaysActive:sOverlays)[aIndex]));
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/heaters/heat_flux/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/heaters/heat_flux/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/heaters/heat_flux/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/heaters/heat_flux/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/heaters/heat_flux/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/heaters/heat_flux/overlay/side"),
	}, sOverlaysActive[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/heaters/heat_flux/overlay_active/front"),
		new Textures.BlockIcons.CustomIcon("machines/heaters/heat_flux/overlay_active/back"),
		new Textures.BlockIcons.CustomIcon("machines/heaters/heat_flux/overlay_active/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.heater.heat_flux";}
}
