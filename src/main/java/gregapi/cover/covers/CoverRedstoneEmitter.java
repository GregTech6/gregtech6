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

package gregapi.cover.covers;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.cover.CoverData;
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class CoverRedstoneEmitter extends AbstractCoverAttachment {
	@Override
	public long onToolClick(byte aSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_cutter)) {
			aData.value(aSide, (short)(aData.mValues[aSide] ^ B[0]), T);
			if (aChatReturn != null) aChatReturn.add(aData.mValues[aSide] != 0 ? "Emits strong Redstone" : "Emits weak Redstone");
			return 1000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) aChatReturn.add(aData.mValues[aSide] != 0 ? "Emits strong Redstone" : "Emits weak Redstone");
			return 1;
		}
		return aData.mTileEntity.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSideClicked, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public byte getRedstoneOutStrong(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {
		return aData.mValues[aCoverSide] != 0 ? getRedstoneOutWeak(aCoverSide, aData, aDefaultRedstone) : 0;
	}
	
	@Override
	public byte getRedstoneOutWeak(byte aCoverSide, CoverData aData, byte aDefaultRedstone) {
		return UT.Code.bind4(aData.mVisuals[aCoverSide]);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_CUTTER));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
	}
	
	@Override
	public boolean onCoverClickedRight(byte aSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aSide == aSideClicked) {
			boolean rReturn = F;
			byte tMode = UT.Code.bind4(aData.mVisuals[aSide]);
			float[] tCoords = UT.Code.getFacingCoordsClicked(aSideClicked, aHitX, aHitY, aHitZ);
			if (tCoords[1] >= PX_P[1] && tCoords[1] <= PX_P[4]) {
				if (tCoords[0] >= PX_P[1] && tCoords[0] <= PX_P[4]) {
					tMode--;
					if (tMode < 0) tMode = 15;
					rReturn = T;
				} else
				if (tCoords[0] >= PX_N[4] && tCoords[0] <= PX_N[1]) {
					tMode++;
					if (tMode > 15) tMode = 0;
					rReturn = T;
				}
			} else
			if (tCoords[1] >= PX_N[7] && tCoords[1] <= PX_N[4]) {
				if (tCoords[0] >= PX_P[2] && tCoords[0] <= PX_N[2]) {
					if (tCoords[0] <= PX_P[5]) {
						tMode ^= 8;
					} else
					if (tCoords[0] <= PX_P[8]) {
						tMode ^= 4;
					} else
					if (tCoords[0] <= PX_N[5]) {
						tMode ^= 2;
					} else {
						tMode ^= 1;
					}
					rReturn = T;
				}
			}
			if (aData.mTileEntity.isServerSide()) aData.visual(aSide, tMode, T);
			return rReturn;
		}
		return F;
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return BlockTextureMulti.get(sTexturesBase, sTextures[UT.Code.bind4(aData.mVisuals[aSide])]);}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? BACKGROUND_COVER : BlockTextureMulti.get(BACKGROUND_COVER, getCoverTextureSurface(aSide, aData));}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	
	public static final ITexture[] sTextures = new ITexture[] {
		  BlockTextureDefault.get("machines/covers/redstoneemitter/0", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/1", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/2", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/3", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/4", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/5", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/6", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/7", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/8", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/9", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/10", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/11", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/12", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/13", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/14", T)
		, BlockTextureDefault.get("machines/covers/redstoneemitter/15", T)
	};
	
	public static final ITexture sTexturesBase = BlockTextureDefault.get("machines/covers/redstoneemitter/underlay");
}
