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

package gregtech.tileentity.inventories;

import static gregapi.data.CS.*;

import gregapi.data.BI;
import gregapi.data.TD;
import gregapi.old.Textures;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.IIconContainer;
import gregapi.render.ITexture;
import gregapi.tileentity.inventories.MultiTileEntityMassStorage;
import net.minecraft.block.Block;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityMassStorageBox extends MultiTileEntityMassStorage {
	@Override
	public int getRenderPasses2(Block aBlock, boolean[] aShouldSideBeRendered) {
		return slotHas(1) && aShouldSideBeRendered[mFacing] && isFaceVisible()?5:1;
	}
	
	@Override
	public boolean setBlockBounds2(Block aBlock, int aRenderPass, boolean[] aShouldSideBeRendered) {
		if (aRenderPass == 0) return F;
		if (mFacing == SIDE_X_NEG) {
			switch (aRenderPass) {
			case 1: box(aBlock, PX_P[0]-PX_OFFSET, PX_P[12], PX_P[ 4], PX_N[ 0], PX_N[ 2], PX_N[10]); return T;
			case 2: box(aBlock, PX_P[0]-PX_OFFSET, PX_P[12], PX_P[ 6], PX_N[ 0], PX_N[ 2], PX_N[ 8]); return T;
			case 3: box(aBlock, PX_P[0]-PX_OFFSET, PX_P[12], PX_P[ 8], PX_N[ 0], PX_N[ 2], PX_N[ 6]); return T;
			case 4: box(aBlock, PX_P[0]-PX_OFFSET, PX_P[12], PX_P[10], PX_N[ 0], PX_N[ 2], PX_N[ 4]); return T;
			}
		}
		if (mFacing == SIDE_X_POS) {
			switch (aRenderPass) {
			case 1: box(aBlock, PX_P[ 0], PX_P[12], PX_P[10], PX_N[0]+PX_OFFSET, PX_N[ 2], PX_N[ 4]); return T;
			case 2: box(aBlock, PX_P[ 0], PX_P[12], PX_P[ 8], PX_N[0]+PX_OFFSET, PX_N[ 2], PX_N[ 6]); return T;
			case 3: box(aBlock, PX_P[ 0], PX_P[12], PX_P[ 6], PX_N[0]+PX_OFFSET, PX_N[ 2], PX_N[ 8]); return T;
			case 4: box(aBlock, PX_P[ 0], PX_P[12], PX_P[ 4], PX_N[0]+PX_OFFSET, PX_N[ 2], PX_N[10]); return T;
			}
		}
		if (mFacing == SIDE_Z_NEG) {
			switch (aRenderPass) {
			case 1: box(aBlock, PX_P[10], PX_P[12], PX_P[0]-PX_OFFSET, PX_N[ 4], PX_N[ 2], PX_N[ 0]); return T;
			case 2: box(aBlock, PX_P[ 8], PX_P[12], PX_P[0]-PX_OFFSET, PX_N[ 6], PX_N[ 2], PX_N[ 0]); return T;
			case 3: box(aBlock, PX_P[ 6], PX_P[12], PX_P[0]-PX_OFFSET, PX_N[ 8], PX_N[ 2], PX_N[ 0]); return T;
			case 4: box(aBlock, PX_P[ 4], PX_P[12], PX_P[0]-PX_OFFSET, PX_N[10], PX_N[ 2], PX_N[ 0]); return T;
			}
		}
		if (mFacing == SIDE_Z_POS) {
			switch (aRenderPass) {
			case 1: box(aBlock, PX_P[ 4], PX_P[12], PX_P[ 0], PX_N[10], PX_N[ 2], PX_N[0]+PX_OFFSET); return T;
			case 2: box(aBlock, PX_P[ 6], PX_P[12], PX_P[ 0], PX_N[ 8], PX_N[ 2], PX_N[0]+PX_OFFSET); return T;
			case 3: box(aBlock, PX_P[ 8], PX_P[12], PX_P[ 0], PX_N[ 6], PX_N[ 2], PX_N[0]+PX_OFFSET); return T;
			case 4: box(aBlock, PX_P[10], PX_P[12], PX_P[ 0], PX_N[ 4], PX_N[ 2], PX_N[0]+PX_OFFSET); return T;
			}
		}
		if (mFacing == SIDE_Y_NEG) {
			switch (aRenderPass) {
			case 1: box(aBlock, PX_P[ 4], PX_P[0]-PX_OFFSET, PX_P[12], PX_N[10], PX_N[ 0], PX_N[ 2]); return T;
			case 2: box(aBlock, PX_P[ 6], PX_P[0]-PX_OFFSET, PX_P[12], PX_N[ 8], PX_N[ 0], PX_N[ 2]); return T;
			case 3: box(aBlock, PX_P[ 8], PX_P[0]-PX_OFFSET, PX_P[12], PX_N[ 6], PX_N[ 0], PX_N[ 2]); return T;
			case 4: box(aBlock, PX_P[10], PX_P[0]-PX_OFFSET, PX_P[12], PX_N[ 4], PX_N[ 0], PX_N[ 2]); return T;
			}
		}
		if (mFacing == SIDE_Y_POS) {
			switch (aRenderPass) {
			case 1: box(aBlock, PX_P[ 4], PX_P[ 0], PX_P[ 2], PX_N[10], PX_N[0]+PX_OFFSET, PX_N[12]); return T;
			case 2: box(aBlock, PX_P[ 6], PX_P[ 0], PX_P[ 2], PX_N[ 8], PX_N[0]+PX_OFFSET, PX_N[12]); return T;
			case 3: box(aBlock, PX_P[ 8], PX_P[ 0], PX_P[ 2], PX_N[ 6], PX_N[0]+PX_OFFSET, PX_N[12]); return T;
			case 4: box(aBlock, PX_P[10], PX_P[ 0], PX_P[ 2], PX_N[ 4], PX_N[0]+PX_OFFSET, PX_N[12]); return T;
			}
		}
		return F;
	}
	
	@Override
	public ITexture getTexture2(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		if (!aShouldSideBeRendered[aSide]) return null;
		if (aRenderPass == 0) {
			int aIndex = aSide<2?aSide:aSide==mFacing?2:aSide==OPOS[mFacing]?3:4;
			return BlockTextureMulti.get(BlockTextureDefault.get(sColoreds[aIndex], mRGBa, mMaterial.contains(TD.Properties.GLOWING)), BlockTextureDefault.get(sOverlays[aIndex]), (mMode & B[3]) == 0 ? null : BlockTextureDefault.get(Textures.BlockIcons.DUCT_TAPE));
		}
		if (aSide == mFacing) {
			return BlockTextureDefault.get(BI.decimalDigit(slot(1).stackSize, 4-aRenderPass), slot(1).stackSize < mMaxStorage ? CA_WHITE : CA_RED_255, F, T, T, T);
		}
		return null;
	}
	
	@Override
	public boolean usesRenderPass2(int aRenderPass, boolean[] aShouldSideBeRendered) {
		switch(aRenderPass) {
		case 1: return slot(1).stackSize > 999;
		case 2: return slot(1).stackSize > 99;
		case 3: return slot(1).stackSize > 9;
		}
		return T;
	}
	
	// Icons
	public static IIconContainer sColoreds[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/massstorage/box/colored/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/massstorage/box/colored/top"),
		new Textures.BlockIcons.CustomIcon("machines/massstorage/box/colored/front"),
		new Textures.BlockIcons.CustomIcon("machines/massstorage/box/colored/back"),
		new Textures.BlockIcons.CustomIcon("machines/massstorage/box/colored/side"),
	}, sOverlays[] = new IIconContainer[] {
		new Textures.BlockIcons.CustomIcon("machines/massstorage/box/overlay/bottom"),
		new Textures.BlockIcons.CustomIcon("machines/massstorage/box/overlay/top"),
		new Textures.BlockIcons.CustomIcon("machines/massstorage/box/overlay/front"),
		new Textures.BlockIcons.CustomIcon("machines/massstorage/box/overlay/back"),
		new Textures.BlockIcons.CustomIcon("machines/massstorage/box/overlay/side"),
	};
	
	@Override public String getTileEntityName() {return "gt.multitileentity.massstorage.box";}
}
