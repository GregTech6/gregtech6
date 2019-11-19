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

import gregapi.cover.CoverData;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public abstract class AbstractCoverAttachmentController extends AbstractCoverAttachment {
	@Override public boolean interceptCoverPlacement(byte aCoverSide, CoverData aData, Entity aPlayer) {return !(aData.mTileEntity instanceof ITileEntitySwitchableOnOff);}
	
	@Override
	public void onCoverRemove(byte aCoverSide, CoverData aData, Entity aPlayer) {
		super.onCoverRemove(aCoverSide, aData, aPlayer);
		if (aData.mTileEntity instanceof ITileEntitySwitchableOnOff && aData.mTileEntity.getWorld() != null) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff(T);
	}
	@Override
	public void onCoverLoaded(byte aCoverSide, CoverData aData) {
		super.onCoverLoaded(aCoverSide, aData);
		if (aData.mTileEntity instanceof ITileEntitySwitchableOnOff && aData.mTileEntity.getWorld() != null) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff(getStateOnOff(aCoverSide, aData));
	}
	@Override
	public void onCoverPlaced(byte aCoverSide, CoverData aData, Entity aPlayer, ItemStack aCover) {
		super.onCoverPlaced(aCoverSide, aData, aPlayer, aCover);
		if (aData.mTileEntity instanceof ITileEntitySwitchableOnOff && aData.mTileEntity.getWorld() != null) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff(getStateOnOff(aCoverSide, aData));
	}
	
	@Override
	public void onBlockUpdate(byte aCoverSide, CoverData aData) {
		if (aData.mTileEntity instanceof ITileEntitySwitchableOnOff && aData.mTileEntity.getWorld() != null) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff(getStateOnOff(aCoverSide, aData));
	}
	
	@Override
	public void onTickPre(byte aCoverSide, CoverData aData, long aTimer, boolean aIsServerSide, boolean aReceivedBlockUpdate, boolean aReceivedInventoryUpdate) {
		if (aIsServerSide && aData.mTileEntity instanceof ITileEntitySwitchableOnOff) ((ITileEntitySwitchableOnOff)aData.mTileEntity).setStateOnOff(getStateOnOff(aCoverSide, aData));
	}
	
	public abstract boolean getStateOnOff(byte aCoverSide, CoverData aData);
}
