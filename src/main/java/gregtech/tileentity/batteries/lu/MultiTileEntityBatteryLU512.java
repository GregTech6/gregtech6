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

package gregtech.tileentity.batteries.lu;

import static gregapi.data.CS.*;

import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.energy.TileEntityBase08Battery;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityBatteryLU512 extends TileEntityBase08Battery {
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return 1;
	}
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return BlockTextureDefault.get(sTextures[FACES_TBS[aSide]], mRGBa, 113+mDisplayedEnergy);
	}
	public static IIconContainer sTextures[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/batteries/lu/512/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/batteries/lu/512/top"),
		new Textures.BlockIcons.CustomIcon("machines/batteries/lu/512/sides"),
	};
	
	@Override public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {box(aBlock    , PX_P[ 3], PX_P[ 0], PX_P[ 3], PX_N[ 3], PX_N[ 6], PX_N[ 3]); return T;}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return box                                            ( PX_P[ 3], PX_P[ 0], PX_P[ 3], PX_N[ 3], PX_N[ 6], PX_N[ 3]);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box                                            ( PX_P[ 3], PX_P[ 0], PX_P[ 3], PX_N[ 3], PX_N[ 6], PX_N[ 3]);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock                                              , PX_P[ 3], PX_P[ 0], PX_P[ 3], PX_N[ 3], PX_N[ 6], PX_N[ 3]);}
	
	@Override public byte getDisplayScaleMax() {return 127;}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.battery.lu.512";}
}
