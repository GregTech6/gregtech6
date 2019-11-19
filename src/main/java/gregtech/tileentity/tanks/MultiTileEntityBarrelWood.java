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

package gregtech.tileentity.tanks;

import static gregapi.data.CS.*;

import gregapi.data.FL;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.tank.TileEntityBase08Barrel;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.FluidStack;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBarrelWood extends TileEntityBase08Barrel {
	@Override public boolean allowCovers(byte aSide) {return F;}
	
	@Override
	public boolean allowFluid(FluidStack aFluid) {
		return super.allowFluid(aFluid) && FL.simple(aFluid);
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return aShouldSideBeRendered[aSide] ? BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[FACES_TBS[aSide]], mRGBa), BlockTextureDefault.get(sOverlays[FACES_TBS[aSide]])) : null;
	}
	
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/barrel/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/barrel/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/barrel/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/tanks/barrel/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/barrel/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/tanks/barrel/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.tank.barrel.wood";}
}
