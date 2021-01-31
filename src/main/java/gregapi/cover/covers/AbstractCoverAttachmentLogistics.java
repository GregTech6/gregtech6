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
import gregapi.render.ITexture;
import gregapi.tileentity.connectors.ITileEntityConnector;
import gregapi.tileentity.logistics.ITileEntityLogistics;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public abstract class AbstractCoverAttachmentLogistics extends AbstractCoverAttachment {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof ITileEntityLogistics && ((ITileEntityLogistics)aData.mTileEntity).canLogistics(SIDE_ANY));}
	@Override public boolean interceptConnect(byte aCoverSide, CoverData aData) {return T;}
	
	@Override
	public void onCoverPlaced(byte aSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		if (aData.mTileEntity instanceof ITileEntityConnector && ((ITileEntityConnector)aData.mTileEntity).connected(aSide)) ((ITileEntityConnector)aData.mTileEntity).disconnect(aSide, T);
		super.onCoverPlaced(aSide, aData, aPlayer, aCover);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_CONTROLLER_COVER));
		if (usePriorities()) aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		if (useTargetStackSize()) aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_CUTTER));
	}
	
	@Override
	public long onToolClick(byte aCoverSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_screwdriver) && usePriorities()) {
			aData.value(aCoverSide, (short)((aData.mValues[aCoverSide]&~3)|((aData.mValues[aCoverSide] + 1) & 3)));
			if (aChatReturn != null) {
				switch(aData.mValues[aCoverSide]) {
				case 0: aChatReturn.add("Priority: " + aData.mValues[aCoverSide] + " (Unmodified)"); break;
				case 1: aChatReturn.add("Priority: " + aData.mValues[aCoverSide] + " (Generic)"); break;
				case 2: aChatReturn.add("Priority: " + aData.mValues[aCoverSide] + " (Semi-Filtered)"); break;
				case 3: aChatReturn.add("Priority: " + aData.mValues[aCoverSide] + " (Filtered)"); break;
				}
			}
			return 10000;
		}
		if (aTool.equals(TOOL_cutter) && useTargetStackSize()) {
			aData.value(aCoverSide, (short)((aData.mValues[aCoverSide]&3)|(((((aData.mValues[aCoverSide] >> 2) + 1) % 65) << 2))));
			if (aChatReturn != null) {
				int tTargetSize = ((aData.mValues[aCoverSide] >> 2) & 127);
				if (tTargetSize == 0) {
					aChatReturn.add("Variable Target Stacksize");
				} else {
					aChatReturn.add("Target Stacksize: " + tTargetSize);
				}
			}
			return 1000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null && usePriorities()) {
				switch(aData.mValues[aCoverSide]) {
				case 0: aChatReturn.add("Priority: " + aData.mValues[aCoverSide] + " (Unmodified)"); break;
				case 1: aChatReturn.add("Priority: " + aData.mValues[aCoverSide] + " (Generic)"); break;
				case 2: aChatReturn.add("Priority: " + aData.mValues[aCoverSide] + " (Semi-Filtered)"); break;
				case 3: aChatReturn.add("Priority: " + aData.mValues[aCoverSide] + " (Filtered)"); break;
				}
			}
			if (aChatReturn != null && useTargetStackSize()) {
				int tTargetSize = ((aData.mValues[aCoverSide] >> 2) & 127);
				if (tTargetSize == 0) {
					aChatReturn.add("Variable Target Stacksize");
				} else {
					aChatReturn.add("Target Stacksize: " + tTargetSize);
				}
			}
			return 1;
		}
		return super.onToolClick(aCoverSide, aData, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSideClicked, aHitX, aHitY, aHitZ);
	}
	
	@Override public ITexture getCoverTextureAttachment(byte aCoverSide, CoverData aData, byte aTextureSide) {return aCoverSide == aTextureSide ? getCoverTextureSurface(aCoverSide, aData) : sTextureBase;}
	@Override public ITexture getCoverTextureHolder(byte aCoverSide, CoverData aData, byte aTextureSide) {return sTextureBase;}
	@Override public boolean showsConnectorFront(byte aCoverSide, CoverData aData) {return F;}
	
	public boolean useTargetStackSize() {return F;}
	public boolean usePriorities() {return T;}
	
	public static final ITexture sTextureBase = BlockTextureDefault.get("machines/covers/logistics/base");
}
