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
import gregapi.tileentity.connectors.MultiTileEntityPipeItem;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class CoverRobotArm extends AbstractCoverAttachment {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity.canTick() && aData.mTileEntity instanceof IInventory);}
	
	public final int mTiming;
	
	public CoverRobotArm(int aTiming) {
		mTiming = Math.max(1, aTiming);
	}
	
	@Override
	public void onCoverPlaced(byte aSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		if (aData.mTileEntity instanceof MultiTileEntityPipeItem) {
			aData.visual(aSide, (short)1);
			aData.value(aSide, (short)-1);
		}
		super.onCoverPlaced(aSide, aData, aPlayer, aCover);
	}
	
	@Override
	public long onToolClick(byte aSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_monkeywrench)) {
			if (aData.mTileEntity instanceof MultiTileEntityPipeItem) {
				aData.visual(aSide, (short)1);
				if (aData.mValues[aSide] >= 0) aData.value(aSide, (short)(-1-aData.mValues[aSide]));
				return 1000;
			}
			aData.visual(aSide, (short)(aData.mVisuals[aSide] == 0 ? 1 : 0));
			return 1000;
		}
		if (aTool.equals(TOOL_screwdriver)) {
			aData.value(aSide, (short)UT.Code.bind(Short.MIN_VALUE, aData.mTileEntity instanceof MultiTileEntityPipeItem ? -1 : Short.MAX_VALUE, aData.mValues[aSide] + (aSneaking?-1:+1)));
			if (aChatReturn != null) aChatReturn.add(aData.mValues[aSide] < 0 ? "Takes from Slot: " + (-1-aData.mValues[aSide]) : "Puts into Slot: " + aData.mValues[aSide]);
			return 200;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) aChatReturn.add(aData.mValues[aSide] < 0 ? "Takes from Slot: " + (-1-aData.mValues[aSide]) : "Puts into Slot: " + aData.mValues[aSide]);
			return 1;
		}
		return 0;
	}
	
	@Override
	public void onTickPre(byte aSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && SERVER_TIME % mTiming == 0 && !aData.mStopped && aData.mTileEntity instanceof IInventory) {
			if (aData.mValues[aSide] < 0) {
				if (aData.mVisuals[aSide] == 0) {
					ST.moveFrom(aData.delegator(aSide), aData.mTileEntity.getAdjacentTileEntity(aSide), -1-aData.mValues[aSide], null, T, F, F, T, 64, 1, 64, 1);
				} else {
					ST.moveFrom(aData.mTileEntity.getAdjacentTileEntity(aSide), aData.delegator(aSide), -1-aData.mValues[aSide], null, T, F, F, T, 64, 1, 64, 1);
				}
			} else {
				if (aData.mVisuals[aSide] == 0) {
					ST.moveTo(aData.delegator(aSide), aData.mTileEntity.getAdjacentTileEntity(aSide), aData.mValues[aSide], null, F, T, F, T, 64, 1, 64, 1);
				} else {
					ST.moveTo(aData.mTileEntity.getAdjacentTileEntity(aSide), aData.delegator(aSide), aData.mValues[aSide], null, F, T, F, T, 64, 1, 64, 1);
				}
			}
		}
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.CYAN + "Transfers a Stack every " + (mTiming==1?"Tick from/to a specific Slot":mTiming+" Ticks from/to a specific Slot"));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_MONKEY_WRENCH));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_CONTROLLER_COVER));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
	}
	
	@Override public ITexture getCoverTextureSurface(byte aCoverSide, CoverData aData) {return aData.mVisuals[aCoverSide]==0?sTextureOut:sTextureIn;}
	@Override public ITexture getCoverTextureAttachment(byte aCoverSide, CoverData aData, byte aTextureSide) {return aCoverSide == aTextureSide ? BlockTextureMulti.get(BACKGROUND_COVER, aData.mVisuals[aCoverSide]==0?sTextureOut:sTextureIn) : aCoverSide == OPPOSITES[aTextureSide] ? BlockTextureMulti.get(BACKGROUND_COVER, aData.mVisuals[aCoverSide]!=0?sTextureOut:sTextureIn) : BACKGROUND_COVER;}
	@Override public ITexture getCoverTextureHolder(byte aCoverSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	@Override public boolean needsVisualsSaved(byte aCoverSide, CoverData aData) {return T;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
	@Override public boolean interceptItemInsert (byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide) {return aCoverSide == aSide && aData.mVisuals[aSide]==0;}
	@Override public boolean interceptItemExtract(byte aCoverSide, CoverData aData, int aSlot, ItemStack aStack, byte aSide) {return aCoverSide == aSide && aData.mVisuals[aSide]!=0;}
	
	public static final ITexture
	sTextureIn = BlockTextureDefault.get("machines/covers/robotarm/in"),
	sTextureOut = BlockTextureDefault.get("machines/covers/robotarm/out");
}
