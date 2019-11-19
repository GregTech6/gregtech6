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

package gregtech.tileentity.panels;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_AddToolTips;
import gregapi.data.CS.PlankData;
import gregapi.data.LH;
import gregapi.render.BlockTextureDefault;
import gregapi.render.ITexture;
import gregapi.util.ST;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityPanelWood extends MultiTileEntityPanel implements IMTE_AddToolTips {
	public short mIndex = 0;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		mIndex = (short)(aNBT.getShort(NBT_TEXTURE) % PlankData.PLANK_ICONS.length);
	}
	
	@Override
	public void addToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		ItemStack tStack = PlankData.PLANKS[mIndex % PlankData.PLANKS.length];
		if (ST.valid(tStack)) aList.add(LH.Chat.CYAN + tStack.getDisplayName());
	}
	
	@Override
	public ITexture getTexture(Block aBlock, int aRenderPass, byte aSide, boolean[] aShouldSideBeRendered) {
		return BlockTextureDefault.get(PlankData.PLANK_ICONS[mIndex]);
	}
	
	@Override public String getTileEntityName() {return "gt.multitileentity.panel.wood";}
}
