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

package gregapi.gui;

import static gregapi.data.CS.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.tileentity.ITileEntityInventoryGUI;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * @author Gregorius Techneticies
 */
@SideOnly(Side.CLIENT)
public class ContainerClientDefault extends ContainerClient {
	public ContainerClientDefault(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity) {
		this(aInventoryPlayer, aTileEntity, RES_PATH_GUI + "chests/" + aTileEntity.getSizeInventoryGUI() + ".png");
	}
	public ContainerClientDefault(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity, String aBackgroundPath) {
		this(aInventoryPlayer, aTileEntity, 0, aBackgroundPath);
	}
	public ContainerClientDefault(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity, int aGUIID) {
		this(aInventoryPlayer, aTileEntity, aGUIID, RES_PATH_GUI + "chests/" + aTileEntity.getSizeInventoryGUI() + ".png");
	}
	public ContainerClientDefault(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity, int aGUIID, String aBackgroundPath) {
		super(new ContainerCommonDefault(aInventoryPlayer, aTileEntity, aGUIID), aBackgroundPath);
	}
	public ContainerClientDefault(ContainerCommonDefault aContainer) {
		super(aContainer, RES_PATH_GUI + "chests/" + aContainer.mSlotCount + ".png");
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		if (mContainer.mSlotCount != 16 && mContainer.mSlotCount <= 27) fontRendererObj.drawString(mContainer.mTileEntity.getInventoryNameGUI(), 8,  4, 4210752);
	}
}
