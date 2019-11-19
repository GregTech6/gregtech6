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

package gregapi.cover;

import java.util.List;

import gregapi.tileentity.ITileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityCoverable extends ITileEntity {
	public boolean setCoverItem(byte aSide, ItemStack aStack, Entity aPlayer, boolean aForce, boolean aBlockUpdate);
	public ItemStack getCoverItem(byte aSide);
	public CoverData getCoverData();
	public void openCoverGUI(byte aSide, EntityPlayer aPlayer);
	public void receiveBlockUpdateFromCover();
	public void sendBlockUpdateFromCover();
	public void updateCoverVisuals();
	public boolean canTick();
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ);
}
