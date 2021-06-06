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

package gregtech.tileentity.misc;

import static gregapi.data.CS.*;

import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.tileentity.misc.MultiTileEntityPlaceable;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityScrap extends MultiTileEntityPlaceable {
	public static IIconContainer
	sTextureSides       = new Textures.BlockIcons.CustomIcon("machines/placeables/scrap/sides"),
	sTextureTop         = new Textures.BlockIcons.CustomIcon("machines/placeables/scrap/top");
	
	@Override
	public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTextureSides = BlockTextureDefault.get(sTextureSides, mMaterial.fRGBaSolid, F, mMaterial.contains(TD.Properties.GLOWING), F, F);
		mTextureTop   = BlockTextureDefault.get(sTextureTop  , mMaterial.fRGBaSolid, F, mMaterial.contains(TD.Properties.GLOWING), F, T);
		return 1;
	}
	
	@Override public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {return box(aBlock, 0, 0, 0, 1, PX_OFFSET, 1);}
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, 0, 0, 0, 1, PX_P[1], 1);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(0, 0, 0, 1, PX_P[1], 1);}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return null;}
	
	@Override public ItemStack getPickBlock(MovingObjectPosition aTarget) {return OP.scrapGt.mat(mMaterial, 1);}
	@Override public ItemStack getStackFromBlock(byte aSide) {return OP.scrapGt.mat(mMaterial, 1);}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.scrap";}
}
