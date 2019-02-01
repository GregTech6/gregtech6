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
	public boolean onCoverClickedRight(byte aSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (!aData.mStopped && aSide == aSideClicked && aData.mTileEntity instanceof ITileEntitySwitchableMode) {
			boolean rReturn = F;
			float[] tCoords = UT.Code.getFacingCoordsClicked(aSideClicked, aHitX, aHitY, aHitZ);
			byte tMode = UT.Code.bind4(((int)(tCoords[0] * 4) % 4) + ((int)(tCoords[1] * 4) % 4) * 4);
			if (aData.mTileEntity.isServerSide()) aData.visual(aSide, ((ITileEntitySwitchableMode)aData.mTileEntity).setStateMode(tMode));
			return rReturn;
		}
		return F;
	}
	
	@Override
	public long onToolClick(byte aSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_chisel)) {
			aData.visual(aSide, (short)((aData.mVisuals[aSide] + 16) % 64));
			return 100;
		}
		return 0;
	}
	
	@Override
	public void onTickPost(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide) aData.visual(aSide, (short)((aData.mVisuals[aSide] & ~15) | UT.Code.bind4(((ITileEntitySwitchableMode)aData.mTileEntity).getStateMode())));
	}
	
	@Override public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {super.addToolTips(aList, aStack, aF3_H); aList.add(LH.get(LH.TOOL_TO_CHANGE_DESIGN_CHISEL));}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return BlockTextureMulti.get(sTexturesBase[(aData.mVisuals[aSide] >> 4) & 3], sTextures[UT.Code.bind4(aData.mVisuals[aSide] & 15)]);}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? sTextureBackground : BlockTextureMulti.get(sTextureBackground, getCoverTextureSurface(aSide, aData));}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return sTextureBackground;}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	
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
		  BlockTextureDefault.get("machines/covers/buttonselector/underlay_blank", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/underlay_numeric", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/underlay_hex", F)
		, BlockTextureDefault.get("machines/covers/buttonselector/underlay_bits", F)
	};
	public static final ITexture sTextureBackground = BlockTextureDefault.get("machines/covers/buttonselector/base");
}
