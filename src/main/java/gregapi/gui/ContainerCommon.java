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

package gregapi.gui;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.tileentity.ITileEntityInventoryGUI;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public class ContainerCommon extends Container {
	public final int mOffset, mSlotCount, mGUIID;
	public ITileEntityInventoryGUI mTileEntity;
	public InventoryPlayer mInventoryPlayer;

	public ContainerCommon(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity) {
		this(aInventoryPlayer, aTileEntity, 0);
	}
	public ContainerCommon(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity, int aOffset, int aSlotCount) {
		this(aInventoryPlayer, aTileEntity, 0, aOffset, aSlotCount);
	}
	public ContainerCommon(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity, int aGUIID) {
		this(aInventoryPlayer, aTileEntity, aGUIID, 0, aTileEntity.getSizeInventoryGUI());
	}
	public ContainerCommon(InventoryPlayer aInventoryPlayer, ITileEntityInventoryGUI aTileEntity, int aGUIID, int aOffset, int aSlotCount) {
		mInventoryPlayer = aInventoryPlayer;
		mTileEntity = aTileEntity;
		mTileEntity.openInventoryGUI();
		mSlotCount = aSlotCount;
		mOffset = aOffset;
		mGUIID = aGUIID;
		
		int tOffset = addSlots(aInventoryPlayer);
		if (doesBindPlayerInventory()) bindPlayerInventory(aInventoryPlayer, tOffset);
		detectAndSendChanges();
	}
	
	/**
	 * To add the Slots to your GUI
	 */
	public int addSlots(InventoryPlayer aPlayerInventory) {
		int i = mOffset;
		if (useDefaultSlots()) switch(mSlotCount) {
		case  1:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 35));
			break;
		case  2:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 71, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 89, 35));
			break;
		case  3:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 35));
			break;
		case  4:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 71, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 89, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 71, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 89, 44));
			break;
		case  5:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 35));
			break;
		case  6:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 44));
			break;
		case  7:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 26, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,134, 35));
			break;
		case  8:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 53, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 71, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 89, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,107, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 53, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 71, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 89, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,107, 44));
			break;
		case  9:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 53));
			break;
		case 12:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 35, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 53, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 71, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 89, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,107, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,125, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 35, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 53, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 71, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 89, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,107, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,125, 44));
			break;
		case 14:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 26, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,134, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 26, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,134, 44));
			break;
		case 15:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 53));
			break;
		case 16:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 53,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 71,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 89,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,107,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 53, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 71, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 89, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,107, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 53, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 71, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 89, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,107, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 53, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 71, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 89, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,107, 62));
			break;
		case 18:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,  8, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 26, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,134, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,152, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,  8, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 26, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,134, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,152, 44));
			break;
		case 27:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,  8, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 26, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,134, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,152, 17));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,  8, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 26, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,134, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,152, 35));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,  8, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 26, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,134, 53));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,152, 53));
			break;
		case 36:
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,  8,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 26,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,134,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,152,  8));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,  8, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 26, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,134, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,152, 26));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,  8, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 26, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,134, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,152, 44));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,  8, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 26, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 44, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 62, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 80, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++, 98, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,116, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,134, 62));
			addSlotToContainer(new Slot_Normal(mTileEntity, i++,152, 62));
			break;
		}
		return 84;
	}
	
	public boolean useDefaultSlots() {
		return F;
	}
	
	/**
	 * Amount of regular Slots in the GUI (so, non-HoloSlots)
	 */
	public int getSlotCount() {return useDefaultSlots() ? mSlotCount : 0;}
	
	/**
	 * Amount of ALL Slots in the GUI including HoloSlots and ArmorSlots, but excluding regular Player Slots
	 */
	protected final int getAllSlotCount() {
		return inventorySlots!=null?doesBindPlayerInventory()?inventorySlots.size()-36:inventorySlots.size():getSlotCount();
	}
	
	/**
	 * Start-Index of the usable Slots (the first non-HoloSlot)
	 */
	public int getStartIndex() {return 0;}
	
	public int getShiftClickStartIndex() {return getStartIndex();}
	
	/**
	 * Amount of Slots in the GUI the player can Shift-Click into. Uses also getSlotStartIndex
	 */
	public int getShiftClickSlotCount() {return useDefaultSlots() ? mSlotCount : 0;}
	
	/**
	 * Is Player-Inventory visible?
	 */
	public boolean doesBindPlayerInventory() {return T;}
	
	@Override public boolean canInteractWith(EntityPlayer aPlayer) {return mTileEntity.isUseableByPlayerGUI(aPlayer);}
	
	protected void bindPlayerInventory(InventoryPlayer aInventoryPlayer, int aOffset) {
		for (int i = 0; i < 3; i++) for (int j = 0; j < 9; j++) {
			addSlotToContainer(new Slot(aInventoryPlayer, j + i * 9 + 9, 8 + j * 18, aOffset + i * 18));
		}
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(aInventoryPlayer, i, 8 + i * 18, aOffset + 58));
		}
	}
	
	@Override
	public ItemStack slotClick(int aIndex, int aMouse, int aShift, EntityPlayer aPlayer) {
		mTileEntity.markDirtyGUI();
		Slot aSlot = (aIndex >= 0 && aIndex < inventorySlots.size()) ? (Slot)inventorySlots.get(aIndex) : null;
		
		try {
			if (aSlot instanceof Slot_Base && mTileEntity.interceptClick(mGUIID, (Slot_Base)aSlot, aIndex, aSlot.getSlotIndex(), aPlayer, aShift == 1, aMouse != 0, aMouse, aShift)) {
				ItemStack rStack = mTileEntity.slotClick(mGUIID, (Slot_Base)aSlot, aIndex, aSlot.getSlotIndex(), aPlayer, aShift == 1, aMouse != 0, aMouse, aShift);
				detectAndSendChanges();
				return rStack;
			}
		} catch (Throwable e) {e.printStackTrace(ERR); return null;}
		
		if (aIndex >= 0) {
			if (aSlot == null || aSlot instanceof Slot_Holo) return null;
			if (!(aSlot instanceof Slot_Armor)) if (aIndex < getAllSlotCount()) if (aIndex < getStartIndex() || aIndex >= getStartIndex() + getSlotCount()) return null;
		}
		
		ItemStack rStack = null, tTempStack, aHoldStack;
		
		try {rStack = super.slotClick(aIndex, aMouse, aShift, aPlayer); detectAndSendChanges(); return rStack;} catch (Throwable e) {e.printStackTrace(ERR);}
		
		InventoryPlayer aPlayerInventory = aPlayer.inventory;
		int tTempStackSize;
		
		if ((aShift == 0 || aShift == 1) && (aMouse == 0 || aMouse == 1)) {
			if (aIndex == -999) {
				if (aPlayerInventory.getItemStack() != null && aIndex == -999) {
					if (aMouse == 0) {
						aPlayer.dropPlayerItemWithRandomChoice(aPlayerInventory.getItemStack(), T);
						aPlayerInventory.setItemStack(null);
					}
					if (aMouse == 1) {
						aPlayer.dropPlayerItemWithRandomChoice(aPlayerInventory.getItemStack().splitStack(1), T);
						if (aPlayerInventory.getItemStack().stackSize == 0) {
							aPlayerInventory.setItemStack(null);
						}
					}
				}
			} else if (aShift == 1) {
				if (aSlot != null && aSlot.canTakeStack(aPlayer)) {
					tTempStack = transferStackInSlot(aPlayer, aIndex);
					if (tTempStack != null) {
						rStack = ST.copy(tTempStack);
						if (aSlot.getStack() != null && aSlot.getStack().getItem() == tTempStack.getItem()) {
							slotClick(aIndex, aMouse, aShift, aPlayer);
						}
					}
				}
			} else {
				if (aIndex < 0) {
					detectAndSendChanges();
					return null;
				}
				if (aSlot != null) {
					tTempStack = aSlot.getStack();
					ItemStack tHeldStack = aPlayerInventory.getItemStack();
					if (tTempStack != null) {
						rStack = ST.copy(tTempStack);
					}
					if (tTempStack == null) {
						if (tHeldStack != null && aSlot.isItemValid(tHeldStack)) {
							tTempStackSize = (aMouse == 0 ? tHeldStack.stackSize : 1);
							if (tTempStackSize > aSlot.getSlotStackLimit()) {
								tTempStackSize = aSlot.getSlotStackLimit();
							}
							aSlot.putStack(tHeldStack.splitStack(tTempStackSize));

							if (tHeldStack.stackSize == 0) {
								aPlayerInventory.setItemStack(null);
							}
						}
					} else if (aSlot.canTakeStack(aPlayer)) {
						if (tHeldStack == null) {
							tTempStackSize = (aMouse == 0 ? tTempStack.stackSize : (tTempStack.stackSize + 1) / 2);
							aHoldStack = aSlot.decrStackSize(tTempStackSize);
							aPlayerInventory.setItemStack(aHoldStack);
							if (tTempStack.stackSize == 0) {
								aSlot.putStack(null);
							}
							aSlot.onPickupFromSlot(aPlayer, aPlayerInventory.getItemStack());
						} else if (aSlot.isItemValid(tHeldStack)) {
							if (tTempStack.getItem() == tHeldStack.getItem() && tTempStack.getItemDamage() == tHeldStack.getItemDamage() && ItemStack.areItemStackTagsEqual(tTempStack, tHeldStack)) {
								tTempStackSize = (aMouse == 0 ? tHeldStack.stackSize : 1);
								if (tTempStackSize > aSlot.getSlotStackLimit() - tTempStack.stackSize) {
									tTempStackSize = aSlot.getSlotStackLimit() - tTempStack.stackSize;
								}
								if (tTempStackSize > tHeldStack.getMaxStackSize() - tTempStack.stackSize) {
									tTempStackSize = tHeldStack.getMaxStackSize() - tTempStack.stackSize;
								}
								tHeldStack.splitStack(tTempStackSize);
								if (tHeldStack.stackSize == 0) {
									aPlayerInventory.setItemStack(null);
								}
								tTempStack.stackSize += tTempStackSize;
							} else if (tHeldStack.stackSize <= aSlot.getSlotStackLimit()) {
								aSlot.putStack(tHeldStack);
								aPlayerInventory.setItemStack(tTempStack);
							}
						} else if (tTempStack.getItem() == tHeldStack.getItem() && tHeldStack.getMaxStackSize() > 1 && (!tTempStack.getHasSubtypes() || tTempStack.getItemDamage() == tHeldStack.getItemDamage()) && ItemStack.areItemStackTagsEqual(tTempStack, tHeldStack)) {
							tTempStackSize = tTempStack.stackSize;

							if (tTempStackSize > 0 && tTempStackSize + tHeldStack.stackSize <= tHeldStack.getMaxStackSize()) {
								tHeldStack.stackSize += tTempStackSize;
								tTempStack = aSlot.decrStackSize(tTempStackSize);

								if (tTempStack.stackSize == 0) {
									aSlot.putStack(null);
								}

								aSlot.onPickupFromSlot(aPlayer, aPlayerInventory.getItemStack());
							}
						}
					}
					aSlot.onSlotChanged();
				}
			}
		} else if (aShift == 2 && aMouse >= 0 && aMouse < 9) {
			if (aSlot.canTakeStack(aPlayer)) {
				tTempStack = aPlayerInventory.getStackInSlot(aMouse);
				boolean var9 = tTempStack == null || aSlot.inventory == aPlayerInventory && aSlot.isItemValid(tTempStack);
				tTempStackSize = -1;

				if (!var9) {
					tTempStackSize = aPlayerInventory.getFirstEmptyStack();
					var9 |= tTempStackSize > -1;
				}

				if (aSlot.getHasStack() && var9) {
					aHoldStack = aSlot.getStack();
					aPlayerInventory.setInventorySlotContents(aMouse, aHoldStack);

					if ((aSlot.inventory != aPlayerInventory || !aSlot.isItemValid(tTempStack)) && tTempStack != null) {
						if (tTempStackSize > -1) {
							aPlayerInventory.addItemStackToInventory(tTempStack);
							aSlot.decrStackSize(aHoldStack.stackSize);
							aSlot.putStack(null);
							aSlot.onPickupFromSlot(aPlayer, aHoldStack);
						}
					} else {
						aSlot.decrStackSize(aHoldStack.stackSize);
						aSlot.putStack(tTempStack);
						aSlot.onPickupFromSlot(aPlayer, aHoldStack);
					}
				} else if (!aSlot.getHasStack() && tTempStack != null && aSlot.isItemValid(tTempStack)) {
					aPlayerInventory.setInventorySlotContents(aMouse, null);
					aSlot.putStack(tTempStack);
				}
			}
		} else if (aShift == 3 && UT.Entities.hasInfiniteItems(aPlayer) && aPlayerInventory.getItemStack() == null && aIndex >= 0) {
			if (aSlot != null && aSlot.getHasStack()) {
				tTempStack = ST.copy(aSlot.getStack());
				tTempStack.stackSize = tTempStack.getMaxStackSize();
				aPlayerInventory.setItemStack(tTempStack);
			}
		}
		detectAndSendChanges();
		return rStack;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer aPlayer, int aIndex) {
		ItemStack rStack = null;
		Slot tSlot = (Slot)inventorySlots.get(aIndex);
		
		mTileEntity.markDirtyGUI();
		
		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (getSlotCount() > 0 && tSlot != null && tSlot.getHasStack() && !(tSlot instanceof Slot_Holo)) {
			ItemStack tStack = tSlot.getStack();
			rStack = ST.copy(tStack);
			
			// TileEntity -> Player
			if (aIndex < getAllSlotCount()) {
				if (doesBindPlayerInventory() && !mergeItemStack(tStack, getAllSlotCount(), getAllSlotCount()+36, T)) {
					return null;
				}
			// Player -> TileEntity
			} else if (!mergeItemStack(tStack, getShiftClickStartIndex(), getShiftClickStartIndex()+getShiftClickSlotCount(), F)) {
				return null;
			}
			
			if (tStack.stackSize == 0) tSlot.putStack(null); else tSlot.onSlotChanged();
		}
		return rStack;
	}
	
	@Override
	protected boolean mergeItemStack(ItemStack aStack, int aStartIndex, int aSlotCount, boolean aReverse) {
		if (ST.meta(aStack) == W) return F;
		
		boolean rSuccess = F;
		int tIndex = aReverse?aSlotCount-1:aStartIndex;
		
		mTileEntity.markDirtyGUI();
		
		if (aStack.isStackable()) {
			while (aStack.stackSize > 0 && (aReverse ? tIndex >= aStartIndex : tIndex < aSlotCount)) {
				Slot tSlot = (Slot)inventorySlots.get(tIndex);
				int tLimit = Math.min(aStack.getMaxStackSize(), tSlot.getSlotStackLimit());
				ItemStack tStack = tSlot.getStack();
				if (!(tSlot instanceof Slot_Holo) && tSlot.isItemValid(aStack) && ST.meta(tStack) != W && ST.equal(aStack, tStack)) {
					int tSize = tStack.stackSize + aStack.stackSize;
					if (tSize <= tLimit) {
						aStack.stackSize = 0;
						tStack.stackSize = tSize;
						tSlot.onSlotChanged();
						rSuccess = T;
					} else if (tStack.stackSize < tLimit) {
						aStack.stackSize -= tLimit - tStack.stackSize;
						tStack.stackSize = tLimit;
						tSlot.onSlotChanged();
						rSuccess = T;
					}
				}
				if (aReverse) tIndex--; else tIndex++;
			}
		}
		if (aStack.stackSize > 0) {
			if (aReverse) tIndex = aSlotCount - 1; else tIndex = aStartIndex;
			while (aReverse ? tIndex >= aStartIndex : tIndex < aSlotCount) {
				Slot tSlot = (Slot)inventorySlots.get(tIndex);
				if (!(tSlot instanceof Slot_Holo) && tSlot.isItemValid(aStack)) {
					if (tSlot.getStack() == null) {
						ItemStack tStack = ST.amount(Math.min(aStack.stackSize, Math.min(aStack.getMaxStackSize(), tSlot.getSlotStackLimit())), aStack);
						tSlot.putStack(tStack);
						tSlot.onSlotChanged();
						aStack.stackSize -= tStack.stackSize;
						rSuccess = T;
						if (aStack.stackSize <= 0) break;
					}
				}
				if (aReverse) tIndex--; else tIndex++;
			}
		}
		return rSuccess;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected Slot addSlotToContainer(Slot aSlot) {
		if (aSlot == null) return null;
		aSlot.slotNumber = inventorySlots.size();
		inventorySlots.add(aSlot);
		inventoryItemStacks.add(null);
		return aSlot;
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting) {
		try {
			super.addCraftingToCrafters(par1ICrafting);
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ItemStack> getInventory() {
		try {
			return super.getInventory();
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
		return null;
	}
	
	@Override
	public void removeCraftingFromCrafters(ICrafting aCrafting) {
		try {
			super.removeCraftingFromCrafters(aCrafting);
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void detectAndSendChanges() {
		try {
			for (int i = 0; i < inventorySlots.size(); ++i) {
//              ItemStack tStack1 = ((Slot)inventorySlots.get(i)).getStack();
//              ItemStack tStack2 = (ItemStack)inventoryItemStacks.get(i);
//              if (!ItemStack.areItemStacksEqual(tStack2, tStack1)) {
//                  tStack2 = tStack1 == null ? null : tStack1.copy();
//                  inventoryItemStacks.set(i, tStack2);
//                  for (int j = 0; j < crafters.size(); ++j) ((ICrafting)crafters.get(j)).sendSlotContents(this, i, tStack2);
//              }
				ItemStack tStack = ((Slot)inventorySlots.get(i)).getStack();
				if (!ST.identical(tStack, (ItemStack)inventoryItemStacks.get(i))) {
					inventoryItemStacks.set(i, tStack = ST.copy(tStack));
					for (int j = 0; j < crafters.size(); ++j) ((ICrafting)crafters.get(j)).sendSlotContents(this, i, tStack);
				}
			}
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
	}
	
	@Override
	public boolean enchantItem(EntityPlayer par1EntityPlayer, int par2) {
		return F;
	}
	
	@Override
	public Slot getSlotFromInventory(IInventory aInventory, int aIndex) {
		try {
			for (int j = 0; j < inventorySlots.size(); ++j) {
				Slot slot = (Slot)inventorySlots.get(j);
				if (slot.isSlotInInventory(aInventory, aIndex)) return slot;
			}
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
		return null;
	}
	
	@Override
	public Slot getSlot(int aIndex) {
		try {
			if (aIndex < inventorySlots.size()) return (Slot)inventorySlots.get(aIndex);
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
		return null;
	}
	
	@Override
	public boolean func_94530_a(ItemStack aStack, Slot aSlot) {
		return T;
	}
	
	@Override
	protected void retrySlotClick(int aIndex, int aMouse, boolean aUnknown, EntityPlayer aPlayer) {
		try {
			slotClick(aIndex, aMouse, 1, aPlayer);
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
	}
	
	@Override
	public void onContainerClosed(EntityPlayer aPlayer) {
		try {
			mTileEntity.closeInventoryGUI();
			InventoryPlayer tPlayerInventory = aPlayer.inventory;
			if (tPlayerInventory.getItemStack() != null) {
				aPlayer.dropPlayerItemWithRandomChoice(tPlayerInventory.getItemStack(), F);
				tPlayerInventory.setItemStack(null);
			}
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory) {
		detectAndSendChanges();
	}
	
	@Override
	public void putStackInSlot(int aIndex, ItemStack aStack) {
		try {
			getSlot(aIndex).putStack(aStack);
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
	}
	
	@Override
	public void putStacksInSlots(ItemStack[] aStacks) {
		try {
			for (int i = 0; i < aStacks.length; ++i) getSlot(i).putStack(aStacks[i]);
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
	}
	
	@Override
	public void updateProgressBar(int aIndex, int aValue) {
		//
	}
	
	@Override
	public short getNextTransactionID(InventoryPlayer aPlayerInventory) {
		return super.getNextTransactionID(aPlayerInventory);
	}
	
	@Override
	public boolean isPlayerNotUsingContainer(EntityPlayer aPlayer) {
		return super.isPlayerNotUsingContainer(aPlayer);
	}
	
	@Override
	public void setPlayerIsPresent(EntityPlayer aPlayer, boolean aPresent) {
		super.setPlayerIsPresent(aPlayer, aPresent);
	}
	
	@Override
	public boolean canDragIntoSlot(Slot aSlot) {
		return T;
	}
}
