/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregapi.cover.covers;

import gregapi.cover.CoverData;
import gregapi.cover.ITileEntityCoverable;
import gregapi.tileentity.connectors.MultiTileEntityWireRedstoneInsulated;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public abstract class AbstractCoverAttachmentTorch extends AbstractCoverAttachment {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof MultiTileEntityWireRedstoneInsulated);}
	@Override public boolean interceptConnect(byte aCoverSide, CoverData aData) {return T;}
	
	@Override
	public void onCoverPlaced(byte aSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		if (((MultiTileEntityWireRedstoneInsulated)aData.mTileEntity).connected(aSide)) ((MultiTileEntityWireRedstoneInsulated)aData.mTileEntity).disconnect(aSide, T);
		if (aPlayer != null) UT.Sounds.send(SFX.MC_DIG_WOOD, 1.0F, -1.0F, aData.mTileEntity);
	}
	
	@Override
	public void onAfterCrowbar(ITileEntityCoverable aTileEntity) {
		UT.Sounds.send(SFX.MC_DIG_WOOD, 1.0F, -1.0F, aTileEntity);
	}
	
	@Override
	public byte getRedstoneOutStrong(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {
		return (byte)(aData.mVisuals[aCoverSide] == 0 ? 15 : 0);
	}
	
	@Override
	public byte getRedstoneOutWeak(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {
		return (byte)(aData.mVisuals[aCoverSide] == 0 ? 15 : 0);
	}
	
	@Override
	public void onTickPost(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && aData.mTileEntity instanceof MultiTileEntityWireRedstoneInsulated) {
			if (condition(aSide, aData, aTimer, aIsServerSide, aReceivedBlockUpdate, aReceivedInventoryUpdate)) {
				if (aData.mVisuals[aSide] == 0) aData.visual(aSide, (short)1, T);
			} else {
				if (aData.mVisuals[aSide] != 0) aData.visual(aSide, (short)0, T);
			}
		}
	}
	
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	@Override public boolean isSolid(byte aSide, CoverData aData) {return F;}
	@Override public boolean isOpaque(byte aSide, CoverData aData) {return F;}
	@Override public boolean isFullTexture(byte aCoverSide, CoverData aData) {return F;}
	
	@Override public float[] getCoverBounds(byte aCoverSide, CoverData aData) {return BOXES_TORCHES[aCoverSide];}
	@Override public float[] getHolderBounds(byte aCoverSide, CoverData aData) {return BOXES_TORCHES[aCoverSide];}
	
	public abstract boolean condition(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate);
	
	public static final float[][] BOXES_TORCHES = new float[][] {{PX_P[ 7], PX_P[ 0], PX_P[ 7], PX_N[ 7], PX_N[ 8], PX_N[ 7]}, {PX_P[ 7], PX_P[ 8], PX_P[ 7], PX_N[ 7], PX_N[ 0], PX_N[ 7]}, {PX_P[ 7], PX_P[ 7], PX_P[ 0], PX_N[ 7], PX_N[ 7], PX_N[ 8]}, {PX_P[ 7], PX_P[ 7], PX_P[ 8], PX_N[ 7], PX_N[ 7], PX_N[ 0]}, {PX_P[ 0], PX_P[ 7], PX_P[ 7], PX_N[ 8], PX_N[ 7], PX_N[ 7]}, {PX_P[ 8], PX_P[ 7], PX_P[ 7], PX_N[ 0], PX_N[ 7], PX_N[ 7]}};
}
