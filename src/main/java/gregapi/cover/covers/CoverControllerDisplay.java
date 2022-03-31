/**
 * Copyright (c) 2022 GregTech-6 Team
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
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.BlockTextureMulti;
import gregapi.render.ITexture;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntityRunningPassively;
import gregapi.tileentity.machines.ITileEntityRunningPossible;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 */
public class CoverControllerDisplay extends AbstractCoverAttachmentController {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof ITileEntityRunningPossible || aData.mTileEntity instanceof ITileEntitySwitchableOnOff);}
	
	@Override
	public long onToolClick(byte aSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_chisel) && sTexturesBase.length > 1) {
			aData.visual(aSide, (short)((aData.mVisuals[aSide] & 1023) | ((((aData.mVisuals[aSide] >>> 10) + 1) % sTexturesBase.length) << 10)));
			return 100;
		}
		return aData.mTileEntity.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSideClicked, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public void onTickPre(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		// Override usual Functionality
	}
	
	@Override
	public void onTickPost(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide) {
			short rVisuals = (short)(aData.mVisuals[aSide] & ~1023);
			if (aData.mTileEntity instanceof ITileEntityRunningPossible ) {rVisuals |= B[5]; if (((ITileEntityRunningPossible )aData.mTileEntity).getStateRunningPossible   ()) rVisuals |= B[0];}
			if (aData.mTileEntity instanceof ITileEntityRunningPassively) {rVisuals |= B[6]; if (((ITileEntityRunningPassively)aData.mTileEntity).getStateRunningPassively  ()) rVisuals |= B[1];}
			if (aData.mTileEntity instanceof ITileEntityRunningActively ) {rVisuals |= B[7]; if (((ITileEntityRunningActively )aData.mTileEntity).getStateRunningActively   ()) rVisuals |= B[2];}
			if (aData.mTileEntity instanceof ITileEntitySwitchableOnOff ) {rVisuals |= B[8]; if (((ITileEntitySwitchableOnOff )aData.mTileEntity).getStateOnOff             ()) rVisuals |= B[3];}
			// There is still space for a fifth indicator/switch with B[9] and B[4].
			aData.visual(aSide, rVisuals);
		}
	}
	
	@Override
	public boolean onCoverClickedRight(byte aSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aSide == aSideClicked && aData.mTileEntity instanceof ITileEntitySwitchableOnOff) {
			float[] tCoords = UT.Code.getFacingCoordsClicked(aSideClicked, aHitX, aHitY, aHitZ);
			switch((short)((aData.mVisuals[aSide] >>> 10) % sTexturesBase.length)) {
			case 0: if (tCoords[0] >= PX_N[6] && tCoords[1] >= PX_N[4]) {if (aData.mTileEntity.isServerSide()) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff(!((ITileEntitySwitchableOnOff)aData.mTileEntity).getStateOnOff()); return T;} break;
			case 1: if (tCoords[0] >= PX_N[6] && tCoords[1] <= PX_P[4]) {if (aData.mTileEntity.isServerSide()) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff(!((ITileEntitySwitchableOnOff)aData.mTileEntity).getStateOnOff()); return T;} break;
			}
		}
		return F;
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {short tVisuals = aData.mVisuals[aSide], tStyle = (short)((tVisuals >>> 10) % sTexturesBase.length); return BlockTextureMulti.get(sTexturesBase[tStyle], (tVisuals & B[5]) != 0 ? sTextures[tStyle][(tVisuals & B[0]) != 0 ? 1 : 0] : null, (tVisuals & B[6]) != 0 ? sTextures[tStyle][(tVisuals & B[1]) != 0 ? 3 : 2] : null, (tVisuals & B[7]) != 0 ? sTextures[tStyle][(tVisuals & B[2]) != 0 ? 5 : 4] : null, (tVisuals & B[8]) != 0 ? sTextures[tStyle][(tVisuals & B[3]) != 0 ? 7 : 6] : null, (tVisuals & B[9]) != 0 ? sTextures[tStyle][(tVisuals & B[4]) != 0 ? 9 : 8] : null);}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? BACKGROUND_COVER : BlockTextureMulti.get(BACKGROUND_COVER, getCoverTextureSurface(aSide, aData));}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
	@Override public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {super.addToolTips(aList, aStack, aF3_H); aList.add(LH.get(LH.TOOL_TO_CHANGE_DESIGN_CHISEL));}
	
	public static final ITexture[][] sTextures = new ITexture[][] {{
		  BlockTextureDefault.get("machines/covers/statusdisplay/bottom/1_off", T)
		, BlockTextureDefault.get("machines/covers/statusdisplay/bottom/1_on" , T)
		, BlockTextureDefault.get("machines/covers/statusdisplay/bottom/2_off", T)
		, BlockTextureDefault.get("machines/covers/statusdisplay/bottom/2_on" , T)
		, BlockTextureDefault.get("machines/covers/statusdisplay/bottom/3_off", T)
		, BlockTextureDefault.get("machines/covers/statusdisplay/bottom/3_on" , T)
		, BlockTextureDefault.get("machines/covers/statusdisplay/bottom/4_off", F)
		, BlockTextureDefault.get("machines/covers/statusdisplay/bottom/4_on" , F)
		, null
		, null
	}, {
		  BlockTextureDefault.get("machines/covers/statusdisplay/top/1_off", T)
		, BlockTextureDefault.get("machines/covers/statusdisplay/top/1_on" , T)
		, BlockTextureDefault.get("machines/covers/statusdisplay/top/2_off", T)
		, BlockTextureDefault.get("machines/covers/statusdisplay/top/2_on" , T)
		, BlockTextureDefault.get("machines/covers/statusdisplay/top/3_off", T)
		, BlockTextureDefault.get("machines/covers/statusdisplay/top/3_on" , T)
		, BlockTextureDefault.get("machines/covers/statusdisplay/top/4_off", F)
		, BlockTextureDefault.get("machines/covers/statusdisplay/top/4_on" , F)
		, null
		, null
	}};
	
	public static final ITexture[] sTexturesBase = {
		BlockTextureDefault.get("machines/covers/statusdisplay/bottom/base"),
		BlockTextureDefault.get("machines/covers/statusdisplay/top/base")
	};
	
	@Override
	public boolean getStateOnOff(byte aSide, CoverData aData) {
		return ((ITileEntitySwitchableOnOff)aData.mTileEntity).getStateOnOff();
	}
}
