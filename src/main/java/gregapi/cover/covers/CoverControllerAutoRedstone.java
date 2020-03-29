/**
 * Copyright (c) 2020 GregTech-6 Team
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
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntityRunningSuccessfully;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class CoverControllerAutoRedstone extends AbstractCoverAttachmentController {
	@Override
	public long onToolClick(byte aSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_screwdriver)) {
			aData.value(aSide, (short)(aData.mValues[aSide] ^ B[0]));
			if (aChatReturn != null) aChatReturn.add((aData.mValues[aSide] & B[0]) != 0 ? "Runs when Input is OFF" : "Runs when Input is ON");
			return 1000;
		}
		if (aTool.equals(TOOL_magnifyingglass)) {
			if (aChatReturn != null) aChatReturn.add((aData.mValues[aSide] & B[0]) != 0 ? "Runs when Input is OFF" : "Runs when Input is ON");
			return 1;
		}
		return aData.mTileEntity.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSideClicked, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		super.addToolTips(aList, aStack, aF3_H);
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_TOGGLE_SCREWDRIVER));
		aList.add(LH.Chat.DGRAY + LH.get(LH.TOOL_TO_DETAIL_MAGNIFYINGGLASS));
	}
	
	@Override public ITexture getCoverTextureSurface(byte aSide, CoverData aData) {return sTextureForeground;}
	@Override public ITexture getCoverTextureAttachment(byte aSide, CoverData aData, byte aTextureSide) {return aSide != aTextureSide ? BACKGROUND_COVER : BlockTextureMulti.get(BACKGROUND_COVER, sTextureForeground);}
	@Override public ITexture getCoverTextureHolder(byte aSide, CoverData aData, byte aTextureSide) {return BACKGROUND_COVER;}
	
	public static final ITexture sTextureForeground = BlockTextureDefault.get("machines/covers/autoredstoneswitch/circuit");
	
	@Override
	public boolean getStateOnOff(byte aSide, CoverData aData) {
		return ((aData.mTileEntity instanceof ITileEntityRunningActively && ((ITileEntityRunningActively)aData.mTileEntity).getStateRunningActively()) && !(aData.mTileEntity instanceof ITileEntityRunningSuccessfully && ((ITileEntityRunningSuccessfully)aData.mTileEntity).getStateRunningSuccessfully())) || UT.Code.bind1(aData.mTileEntity.getRedstoneIncoming(aSide)) != (aData.mValues[aSide] & B[0]);
	}
}
