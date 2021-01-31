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

package gregapi.cover.covers;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.cover.CoverData;
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.machines.ITileEntitySwitchableMode;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class CoverSelectorButtonPanel extends AbstractCoverAttachmentSelector {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof ITileEntitySwitchableMode);}
	
	@Override
	public void onCoverPlaced(byte aSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		super.onCoverPlaced(aSide, aData, aPlayer, aCover);
		if (aData.mTileEntity instanceof ITileEntitySwitchableMode) aData.visual(aSide, UT.Code.bind4(((ITileEntitySwitchableMode)aData.mTileEntity).getStateMode()));
	}
	@Override
	public void onCoverLoaded(byte aSide, CoverData aData) {
		super.onCoverLoaded(aSide, aData);
		if (aData.mTileEntity instanceof ITileEntitySwitchableMode) ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(UT.Code.bind4(aData.mVisuals[aSide] & 15));
	}
	@Override
	public void onBlockUpdate(byte aSide, CoverData aData) {
		if (!aData.mStopped && aData.mTileEntity instanceof ITileEntitySwitchableMode) aData.visual(aSide, UT.Code.bind4(((ITileEntitySwitchableMode)aData.mTileEntity).getStateMode()));
	}
	
	@Override
	public boolean onCoverClickedRight(byte aSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (!aData.mStopped && aSide == aSideClicked && aData.mTileEntity instanceof ITileEntitySwitchableMode) {
			float[] tCoords = UT.Code.getFacingCoordsClicked(aSideClicked, aHitX, aHitY, aHitZ);
			byte tMode = UT.Code.bind4(((int)(tCoords[0] * 4) % 4) + ((int)(tCoords[1] * 4) % 4) * 4);
			if (aData.mTileEntity.isServerSide()) aData.visual(aSide, (short)((aData.mVisuals[aSide] & ~15) | ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(tMode)));
			if (aData.mValues[aSide] > 0) aData.value(aSide, (short)10);
			return T;
		}
		return F;
	}
	
	@Override
	public long onToolClick(byte aSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_chisel)) {
			aData.visual(aSide, (short)((aData.mVisuals[aSide] + 16) & 127));
			return 100;
		}
		if (aTool.equals(TOOL_screwdriver)) {
			aData.value(aSide, (short)(aData.mValues[aSide] > 0 ? 0 : 1));
			if (aChatReturn != null) aChatReturn.add(aData.mValues[aSide] > 0 ? "Buttons will reset" : "Buttons stay pressed");
			return 10000;
		}
		return 0;
	}
	
	@Override
	public void onTickPost(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (!aData.mStopped && aIsServerSide) {
			if (aData.mValues[aSide] > 1) {
				aData.value(aSide, (short)(aData.mValues[aSide]-1));
				if (aData.mValues[aSide] == 1) ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode((byte)0);
			}
			aData.visual(aSide, (short)((aData.mVisuals[aSide] & ~15) | UT.Code.bind4(((ITileEntitySwitchableMode)aData.mTileEntity).getStateMode())));
		}
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_CHANGE_DESIGN_CHISEL));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_CONTROLLER_COVER));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return BlockTextureMulti.get(sTexturesBase[(aData.mVisuals[aSide] >> 4) & 7], sTextures[UT.Code.bind4(aData.mVisuals[aSide] & 15)]);}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? sTexturesBase[0] : BlockTextureMulti.get(sTexturesBase[0], getCoverTextureSurface(aSide, aData));}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return sTexturesBase[0];}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	@Override public boolean isOpaque(byte aSide, CoverData aData) {return T;}
	
	public static final ITexture[] sTextures = new ITexture[] {
		  BlockTextureDefault.get("machines/covers/buttonselector/0", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/1", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/2", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/3", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/4", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/5", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/6", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/7", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/8", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/9", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/10", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/11", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/12", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/13", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/14", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/15", F)
	};
	public static final ITexture[] sTexturesBase = new ITexture[] {
		  BlockTextureDefault.get("machines/covers/buttonselector/underlay", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/underlay_0_to_15", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/underlay_0_to_F", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/underlay_1_to_16", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/underlay_16_1_to_15", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/underlay_keypad_1_to_9", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/underlay_keypad_9_to_1", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/underlay_bits", F)
	};
}
