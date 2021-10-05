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

package gregtech.tileentity.placeables;

import static gregapi.data.CS.*;

import gregapi.data.OP;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.IIconContainer;
import gregapi.tileentity.misc.MultiTileEntityPlaceable;
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityIngot extends MultiTileEntityPlaceable {
	public static IIconContainer
	sTextureSides       = new Textures.BlockIcons.CustomIcon("machines/placeables/ingot/sides"),
	sTextureTop         = new Textures.BlockIcons.CustomIcon("machines/placeables/ingot/top");
	
	@Override
	public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTextureSides = BlockTextureDefault.get(sTextureSides, mMaterial.fRGBaSolid, F, mMaterial.contains(TD.Properties.GLOWING), F, F);
		mTextureTop   = BlockTextureDefault.get(sTextureTop  , mMaterial.fRGBaSolid, F, mMaterial.contains(TD.Properties.GLOWING), F, T);
		return mSize;
	}
	
	@Override
	public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
		case  0: return box(aBlock, 0.03125, PX_P[ 0], 0.03125, 0.21875, PX_P[ 2], 0.46875);
		case  1: return box(aBlock, 0.28125, PX_P[ 0], 0.03125, 0.46875, PX_P[ 2], 0.46875);
		case  2: return box(aBlock, 0.53125, PX_P[ 0], 0.03125, 0.71875, PX_P[ 2], 0.46875);
		case  3: return box(aBlock, 0.78125, PX_P[ 0], 0.03125, 0.96875, PX_P[ 2], 0.46875);
		case  4: return box(aBlock, 0.03125, PX_P[ 0], 0.53125, 0.21875, PX_P[ 2], 0.96875);
		case  5: return box(aBlock, 0.28125, PX_P[ 0], 0.53125, 0.46875, PX_P[ 2], 0.96875);
		case  6: return box(aBlock, 0.53125, PX_P[ 0], 0.53125, 0.71875, PX_P[ 2], 0.96875);
		case  7: return box(aBlock, 0.78125, PX_P[ 0], 0.53125, 0.96875, PX_P[ 2], 0.96875);
		
		case  8: return box(aBlock, 0.03125, PX_P[ 2], 0.03125, 0.46875, PX_P[ 4], 0.21875);
		case  9: return box(aBlock, 0.03125, PX_P[ 2], 0.28125, 0.46875, PX_P[ 4], 0.46875);
		case 10: return box(aBlock, 0.03125, PX_P[ 2], 0.53125, 0.46875, PX_P[ 4], 0.71875);
		case 11: return box(aBlock, 0.03125, PX_P[ 2], 0.78125, 0.46875, PX_P[ 4], 0.96875);
		case 12: return box(aBlock, 0.53125, PX_P[ 2], 0.03125, 0.96875, PX_P[ 4], 0.21875);
		case 13: return box(aBlock, 0.53125, PX_P[ 2], 0.28125, 0.96875, PX_P[ 4], 0.46875);
		case 14: return box(aBlock, 0.53125, PX_P[ 2], 0.53125, 0.96875, PX_P[ 4], 0.71875);
		case 15: return box(aBlock, 0.53125, PX_P[ 2], 0.78125, 0.96875, PX_P[ 4], 0.96875);
		
		case 16: return box(aBlock, 0.03125, PX_P[ 4], 0.03125, 0.21875, PX_P[ 6], 0.46875);
		case 17: return box(aBlock, 0.28125, PX_P[ 4], 0.03125, 0.46875, PX_P[ 6], 0.46875);
		case 18: return box(aBlock, 0.53125, PX_P[ 4], 0.03125, 0.71875, PX_P[ 6], 0.46875);
		case 19: return box(aBlock, 0.78125, PX_P[ 4], 0.03125, 0.96875, PX_P[ 6], 0.46875);
		case 20: return box(aBlock, 0.03125, PX_P[ 4], 0.53125, 0.21875, PX_P[ 6], 0.96875);
		case 21: return box(aBlock, 0.28125, PX_P[ 4], 0.53125, 0.46875, PX_P[ 6], 0.96875);
		case 22: return box(aBlock, 0.53125, PX_P[ 4], 0.53125, 0.71875, PX_P[ 6], 0.96875);
		case 23: return box(aBlock, 0.78125, PX_P[ 4], 0.53125, 0.96875, PX_P[ 6], 0.96875);
		
		case 24: return box(aBlock, 0.03125, PX_P[ 6], 0.03125, 0.46875, PX_P[ 8], 0.21875);
		case 25: return box(aBlock, 0.03125, PX_P[ 6], 0.28125, 0.46875, PX_P[ 8], 0.46875);
		case 26: return box(aBlock, 0.03125, PX_P[ 6], 0.53125, 0.46875, PX_P[ 8], 0.71875);
		case 27: return box(aBlock, 0.03125, PX_P[ 6], 0.78125, 0.46875, PX_P[ 8], 0.96875);
		case 28: return box(aBlock, 0.53125, PX_P[ 6], 0.03125, 0.96875, PX_P[ 8], 0.21875);
		case 29: return box(aBlock, 0.53125, PX_P[ 6], 0.28125, 0.96875, PX_P[ 8], 0.46875);
		case 30: return box(aBlock, 0.53125, PX_P[ 6], 0.53125, 0.96875, PX_P[ 8], 0.71875);
		case 31: return box(aBlock, 0.53125, PX_P[ 6], 0.78125, 0.96875, PX_P[ 8], 0.96875);
		
		case 32: return box(aBlock, 0.03125, PX_P[ 8], 0.03125, 0.21875, PX_P[10], 0.46875);
		case 33: return box(aBlock, 0.28125, PX_P[ 8], 0.03125, 0.46875, PX_P[10], 0.46875);
		case 34: return box(aBlock, 0.53125, PX_P[ 8], 0.03125, 0.71875, PX_P[10], 0.46875);
		case 35: return box(aBlock, 0.78125, PX_P[ 8], 0.03125, 0.96875, PX_P[10], 0.46875);
		case 36: return box(aBlock, 0.03125, PX_P[ 8], 0.53125, 0.21875, PX_P[10], 0.96875);
		case 37: return box(aBlock, 0.28125, PX_P[ 8], 0.53125, 0.46875, PX_P[10], 0.96875);
		case 38: return box(aBlock, 0.53125, PX_P[ 8], 0.53125, 0.71875, PX_P[10], 0.96875);
		case 39: return box(aBlock, 0.78125, PX_P[ 8], 0.53125, 0.96875, PX_P[10], 0.96875);
		
		case 40: return box(aBlock, 0.03125, PX_P[10], 0.03125, 0.46875, PX_P[12], 0.21875);
		case 41: return box(aBlock, 0.03125, PX_P[10], 0.28125, 0.46875, PX_P[12], 0.46875);
		case 42: return box(aBlock, 0.03125, PX_P[10], 0.53125, 0.46875, PX_P[12], 0.71875);
		case 43: return box(aBlock, 0.03125, PX_P[10], 0.78125, 0.46875, PX_P[12], 0.96875);
		case 44: return box(aBlock, 0.53125, PX_P[10], 0.03125, 0.96875, PX_P[12], 0.21875);
		case 45: return box(aBlock, 0.53125, PX_P[10], 0.28125, 0.96875, PX_P[12], 0.46875);
		case 46: return box(aBlock, 0.53125, PX_P[10], 0.53125, 0.96875, PX_P[12], 0.71875);
		case 47: return box(aBlock, 0.53125, PX_P[10], 0.78125, 0.96875, PX_P[12], 0.96875);
		
		case 48: return box(aBlock, 0.03125, PX_P[12], 0.03125, 0.21875, PX_P[14], 0.46875);
		case 49: return box(aBlock, 0.28125, PX_P[12], 0.03125, 0.46875, PX_P[14], 0.46875);
		case 50: return box(aBlock, 0.53125, PX_P[12], 0.03125, 0.71875, PX_P[14], 0.46875);
		case 51: return box(aBlock, 0.78125, PX_P[12], 0.03125, 0.96875, PX_P[14], 0.46875);
		case 52: return box(aBlock, 0.03125, PX_P[12], 0.53125, 0.21875, PX_P[14], 0.96875);
		case 53: return box(aBlock, 0.28125, PX_P[12], 0.53125, 0.46875, PX_P[14], 0.96875);
		case 54: return box(aBlock, 0.53125, PX_P[12], 0.53125, 0.71875, PX_P[14], 0.96875);
		case 55: return box(aBlock, 0.78125, PX_P[12], 0.53125, 0.96875, PX_P[14], 0.96875);
		
		case 56: return box(aBlock, 0.03125, PX_P[14], 0.03125, 0.46875, PX_P[16], 0.21875);
		case 57: return box(aBlock, 0.03125, PX_P[14], 0.28125, 0.46875, PX_P[16], 0.46875);
		case 58: return box(aBlock, 0.03125, PX_P[14], 0.53125, 0.46875, PX_P[16], 0.71875);
		case 59: return box(aBlock, 0.03125, PX_P[14], 0.78125, 0.46875, PX_P[16], 0.96875);
		case 60: return box(aBlock, 0.53125, PX_P[14], 0.03125, 0.96875, PX_P[16], 0.21875);
		case 61: return box(aBlock, 0.53125, PX_P[14], 0.28125, 0.96875, PX_P[16], 0.46875);
		case 62: return box(aBlock, 0.53125, PX_P[14], 0.53125, 0.96875, PX_P[16], 0.71875);
		case 63: return box(aBlock, 0.53125, PX_P[14], 0.78125, 0.96875, PX_P[16], 0.96875);
		}
		return T;
	}
	
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, 0, 0, 0, 1, UT.Code.divup(mSize, 8) / 8.0F, 1);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(0, 0, 0, 1, UT.Code.divup(mSize, 8) / 8.0F, 1);}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return mSize < 8 ? null : box(0, 0, 0, 1, (mSize / 8) / 8.0F, 1);}
	
	@Override public ItemStack getPickBlock(MovingObjectPosition aTarget) {return OP.ingot.mat(mMaterial, 1);}
	@Override public ItemStack getStackFromBlock(byte aSide) {return OP.ingot.mat(mMaterial, 1);}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.ingot";}
}
