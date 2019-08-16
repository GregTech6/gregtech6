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
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class CoverLogisticsGenericDump extends AbstractCoverAttachmentLogistics {
	public static final CoverLogisticsGenericDump INSTANCE = new CoverLogisticsGenericDump();
	
	public CoverLogisticsGenericDump() {}
	
	@Override
	public long onToolClick(byte aCoverSide, CoverData aData, String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_magnifyingglass) || aTool.equals(TOOL_screwdriver)) {
			if (aChatReturn != null) aChatReturn.add("Priority: Always Last");
			return 1;
		}
		return 0;
	}
	
	@Override public ITexture getCoverTextureSurface(byte aCoverSide, CoverData aData) {return sTexture;}
	@Override public boolean usePriorities() {return F;}
	
	public static final ITexture sTexture = BlockTextureDefault.get("machines/covers/logistics/generic/dump");
}
