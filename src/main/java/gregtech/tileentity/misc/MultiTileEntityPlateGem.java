/**
 * Copyright (c) 2018 Gregorius Techneticies
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
import gregapi.util.UT;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPlateGem extends MultiTileEntityPlaceable {
	public static IIconContainer
	sTextureSides       = new Textures.BlockIcons.CustomIcon("machines/placeables/plateGem/sides"),
	sTextureTop         = new Textures.BlockIcons.CustomIcon("machines/placeables/plateGem/top");
	
	@Override
	public int getRenderPasses(Block aBlock, boolean[] aShouldSideBeRendered) {
		mTextureSides = BlockTextureDefault.get(sTextureSides, UT.Code.getRGBaInt(mMaterial.fRGBaSolid), mMaterial.contains(TD.Properties.GLOWING));
		mTextureTop   = BlockTextureDefault.get(sTextureTop  , UT.Code.getRGBaInt(mMaterial.fRGBaSolid), mMaterial.contains(TD.Properties.GLOWING));
		return mSize;
	}
	
	@Override
	public boolean setBlockBounds(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch (aRenderPass) {
		case  0: return box(aBlock, 0.03125, PX_P[ 0], 0.03125, 0.46875, PX_P[ 1], 0.46875);
		case  1: return box(aBlock, 0.03125, PX_P[ 0], 0.53125, 0.46875, PX_P[ 1], 0.96875);
		case  2: return box(aBlock, 0.53125, PX_P[ 0], 0.03125, 0.96875, PX_P[ 1], 0.46875);
		case  3: return box(aBlock, 0.53125, PX_P[ 0], 0.53125, 0.96875, PX_P[ 1], 0.96875);
		
		case  4: return box(aBlock, 0.03125, PX_P[ 1], 0.03125, 0.46875, PX_P[ 2], 0.46875);
		case  5: return box(aBlock, 0.03125, PX_P[ 1], 0.53125, 0.46875, PX_P[ 2], 0.96875);
		case  6: return box(aBlock, 0.53125, PX_P[ 1], 0.03125, 0.96875, PX_P[ 2], 0.46875);
		case  7: return box(aBlock, 0.53125, PX_P[ 1], 0.53125, 0.96875, PX_P[ 2], 0.96875);
		
		case  8: return box(aBlock, 0.03125, PX_P[ 2], 0.03125, 0.46875, PX_P[ 3], 0.46875);
		case  9: return box(aBlock, 0.03125, PX_P[ 2], 0.53125, 0.46875, PX_P[ 3], 0.96875);
		case 10: return box(aBlock, 0.53125, PX_P[ 2], 0.03125, 0.96875, PX_P[ 3], 0.46875);
		case 11: return box(aBlock, 0.53125, PX_P[ 2], 0.53125, 0.96875, PX_P[ 3], 0.96875);
		
		case 12: return box(aBlock, 0.03125, PX_P[ 3], 0.03125, 0.46875, PX_P[ 4], 0.46875);
		case 13: return box(aBlock, 0.03125, PX_P[ 3], 0.53125, 0.46875, PX_P[ 4], 0.96875);
		case 14: return box(aBlock, 0.53125, PX_P[ 3], 0.03125, 0.96875, PX_P[ 4], 0.46875);
		case 15: return box(aBlock, 0.53125, PX_P[ 3], 0.53125, 0.96875, PX_P[ 4], 0.96875);
		
		case 16: return box(aBlock, 0.03125, PX_P[ 4], 0.03125, 0.46875, PX_P[ 5], 0.46875);
		case 17: return box(aBlock, 0.03125, PX_P[ 4], 0.53125, 0.46875, PX_P[ 5], 0.96875);
		case 18: return box(aBlock, 0.53125, PX_P[ 4], 0.03125, 0.96875, PX_P[ 5], 0.46875);
		case 19: return box(aBlock, 0.53125, PX_P[ 4], 0.53125, 0.96875, PX_P[ 5], 0.96875);
		
		case 20: return box(aBlock, 0.03125, PX_P[ 5], 0.03125, 0.46875, PX_P[ 6], 0.46875);
		case 21: return box(aBlock, 0.03125, PX_P[ 5], 0.53125, 0.46875, PX_P[ 6], 0.96875);
		case 22: return box(aBlock, 0.53125, PX_P[ 5], 0.03125, 0.96875, PX_P[ 6], 0.46875);
		case 23: return box(aBlock, 0.53125, PX_P[ 5], 0.53125, 0.96875, PX_P[ 6], 0.96875);
		
		case 24: return box(aBlock, 0.03125, PX_P[ 6], 0.03125, 0.46875, PX_P[ 7], 0.46875);
		case 25: return box(aBlock, 0.03125, PX_P[ 6], 0.53125, 0.46875, PX_P[ 7], 0.96875);
		case 26: return box(aBlock, 0.53125, PX_P[ 6], 0.03125, 0.96875, PX_P[ 7], 0.46875);
		case 27: return box(aBlock, 0.53125, PX_P[ 6], 0.53125, 0.96875, PX_P[ 7], 0.96875);
		
		case 28: return box(aBlock, 0.03125, PX_P[ 7], 0.03125, 0.46875, PX_P[ 8], 0.46875);
		case 29: return box(aBlock, 0.03125, PX_P[ 7], 0.53125, 0.46875, PX_P[ 8], 0.96875);
		case 30: return box(aBlock, 0.53125, PX_P[ 7], 0.03125, 0.96875, PX_P[ 8], 0.46875);
		case 31: return box(aBlock, 0.53125, PX_P[ 7], 0.53125, 0.96875, PX_P[ 8], 0.96875);
		
		case 32: return box(aBlock, 0.03125, PX_P[ 8], 0.03125, 0.46875, PX_P[ 9], 0.46875);
		case 33: return box(aBlock, 0.03125, PX_P[ 8], 0.53125, 0.46875, PX_P[ 9], 0.96875);
		case 34: return box(aBlock, 0.53125, PX_P[ 8], 0.03125, 0.96875, PX_P[ 9], 0.46875);
		case 35: return box(aBlock, 0.53125, PX_P[ 8], 0.53125, 0.96875, PX_P[ 9], 0.96875);
		
		case 36: return box(aBlock, 0.03125, PX_P[ 9], 0.03125, 0.46875, PX_P[10], 0.46875);
		case 37: return box(aBlock, 0.03125, PX_P[ 9], 0.53125, 0.46875, PX_P[10], 0.96875);
		case 38: return box(aBlock, 0.53125, PX_P[ 9], 0.03125, 0.96875, PX_P[10], 0.46875);
		case 39: return box(aBlock, 0.53125, PX_P[ 9], 0.53125, 0.96875, PX_P[10], 0.96875);
		
		case 40: return box(aBlock, 0.03125, PX_P[10], 0.03125, 0.46875, PX_P[11], 0.46875);
		case 41: return box(aBlock, 0.03125, PX_P[10], 0.53125, 0.46875, PX_P[11], 0.96875);
		case 42: return box(aBlock, 0.53125, PX_P[10], 0.03125, 0.96875, PX_P[11], 0.46875);
		case 43: return box(aBlock, 0.53125, PX_P[10], 0.53125, 0.96875, PX_P[11], 0.96875);
		
		case 44: return box(aBlock, 0.03125, PX_P[11], 0.03125, 0.46875, PX_P[12], 0.46875);
		case 45: return box(aBlock, 0.03125, PX_P[11], 0.53125, 0.46875, PX_P[12], 0.96875);
		case 46: return box(aBlock, 0.53125, PX_P[11], 0.03125, 0.96875, PX_P[12], 0.46875);
		case 47: return box(aBlock, 0.53125, PX_P[11], 0.53125, 0.96875, PX_P[12], 0.96875);
		
		case 48: return box(aBlock, 0.03125, PX_P[12], 0.03125, 0.46875, PX_P[13], 0.46875);
		case 49: return box(aBlock, 0.03125, PX_P[12], 0.53125, 0.46875, PX_P[13], 0.96875);
		case 50: return box(aBlock, 0.53125, PX_P[12], 0.03125, 0.96875, PX_P[13], 0.46875);
		case 51: return box(aBlock, 0.53125, PX_P[12], 0.53125, 0.96875, PX_P[13], 0.96875);
		
		case 52: return box(aBlock, 0.03125, PX_P[13], 0.03125, 0.46875, PX_P[14], 0.46875);
		case 53: return box(aBlock, 0.03125, PX_P[13], 0.53125, 0.46875, PX_P[14], 0.96875);
		case 54: return box(aBlock, 0.53125, PX_P[13], 0.03125, 0.96875, PX_P[14], 0.46875);
		case 55: return box(aBlock, 0.53125, PX_P[13], 0.53125, 0.96875, PX_P[14], 0.96875);
		
		case 56: return box(aBlock, 0.03125, PX_P[14], 0.03125, 0.46875, PX_P[15], 0.46875);
		case 57: return box(aBlock, 0.03125, PX_P[14], 0.53125, 0.46875, PX_P[15], 0.96875);
		case 58: return box(aBlock, 0.53125, PX_P[14], 0.03125, 0.96875, PX_P[15], 0.46875);
		case 59: return box(aBlock, 0.53125, PX_P[14], 0.53125, 0.96875, PX_P[15], 0.96875);
		
		case 60: return box(aBlock, 0.03125, PX_P[15], 0.03125, 0.46875, PX_P[16], 0.46875);
		case 61: return box(aBlock, 0.03125, PX_P[15], 0.53125, 0.46875, PX_P[16], 0.96875);
		case 62: return box(aBlock, 0.53125, PX_P[15], 0.03125, 0.96875, PX_P[16], 0.46875);
		case 63: return box(aBlock, 0.53125, PX_P[15], 0.53125, 0.96875, PX_P[16], 0.96875);
		}
		return T;
	}
	
	@Override public ItemStack getPickBlock(MovingObjectPosition aTarget) {return OP.plateGem.mat(mMaterial, 1);}
	@Override public ItemStack getStackFromBlock(byte aSide) {return OP.plateGem.mat(mMaterial, 1);}
	
	@Override public void setBlockBoundsBasedOnState(Block aBlock) {box(aBlock, 0, 0, 0, 1, UT.Code.divup(mSize, 4) / 16.0F, 1);}
	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool () {return box(0, 0, 0, 1, UT.Code.divup(mSize, 4) / 16.0F, 1);}
	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool() {return mSize < 4 ? null : box(0, 0, 0, 1, (mSize / 4) / 16.0F, 1);}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.plategem";}
}
