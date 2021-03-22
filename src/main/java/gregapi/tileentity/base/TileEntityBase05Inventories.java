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

package gregapi.tileentity.base;

import static gregapi.data.CS.*;

import gregapi.block.multitileentity.IMultiTileEntity.IMTE_BreakBlock;
import gregapi.block.multitileentity.IMultiTileEntity.IMTE_OnBlockExploded;
import gregapi.block.multitileentity.MultiTileEntityRegistry;
import gregapi.data.CS.GarbageGT;
import gregapi.tileentity.ITileEntityInventoryGUI;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.Explosion;

/**
 * @author Gregorius Techneticies
 */
public abstract class TileEntityBase05Inventories extends TileEntityBase04MultiTileEntities implements IInventory, ITileEntityInventoryGUI, IMTE_OnBlockExploded, IMTE_BreakBlock {
	private ItemStack[] mInventory = ZL_IS;
	
	public boolean mInventoryChanged = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		// Standard readFromNBT process to load Inventory.
		mInventory = getDefaultInventory(aNBT);
		if (mInventory != null && mInventory.length > 0) {
			NBTTagList tList = aNBT.getTagList(NBT_INV_LIST, 10);
			for (int i = 0; i < tList.tagCount(); i++) {
				NBTTagCompound tNBT = tList.getCompoundTagAt(i);
				int tSlot = tNBT.getShort("s");
				if (tSlot >= 0 && tSlot < mInventory.length) mInventory[tSlot] = ST.load(tNBT, getDefaultStack(tSlot));
			}
		}
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		if (mInventory != null && mInventory.length > 0) {
			NBTTagList tList = new NBTTagList();
			for (short tSlot = 0; tSlot < mInventory.length; tSlot++) if (mInventory[tSlot] != null && canSave(tSlot)) tList.appendTag(UT.NBT.makeShort(ST.save(mInventory[tSlot]), "s", tSlot));
			aNBT.setTag(NBT_INV_LIST, tList);
		}
	}
	
	public ItemStack[] getDefaultInventory(NBTTagCompound aNBT) {
		short tSize = aNBT.getShort(NBT_INV_SIZE);
		return tSize > 0 ? new ItemStack[tSize] : ZL_IS;
	}
	
	@Override
	public void onTickResetChecks(long aTimer, boolean aIsServerSide) {
		super.onTickResetChecks(aTimer, aIsServerSide);
		mInventoryChanged = F;
	}
	
	@Override public final ItemStack slot(int aIndex, ItemStack aStack) {return mInventory[aIndex] = aStack;}
	@Override public final ItemStack slot(int aIndex) {return mInventory[aIndex];}
	@Override public final ItemStack slotTake(int aIndex) {ItemStack rStack = mInventory[aIndex]; mInventory[aIndex] = null; return rStack;}
	@Override public final boolean slotTrash(int aIndex) {return GarbageGT.trash(slotTake(aIndex)) > 0;}
	@Override public final boolean slotNull(int aIndex) {if (mInventory[aIndex] != null && mInventory[aIndex].stackSize <= 0) return slotKill(aIndex); return F;}
	@Override public final boolean slotKill(int aIndex) {mInventory[aIndex] = null; return T;}
	@Override public final boolean slotHas(int aIndex) {return mInventory[aIndex] != null;}
	@Override public final boolean invempty() {for (int i = 0; i < mInventory.length; i++) if (mInventory[i] != null) return F; return T;}
	@Override public final int invsize() {return mInventory.length;}
	@Override public final NBTTagCompound slotNBT(int aIndex) {return mInventory[aIndex] != null ? mInventory[aIndex].getTagCompound() : null;}
	
	@Override public void updateInventory() {mInventoryChanged = T;}
	@Override public boolean isUseableByPlayer(EntityPlayer aPlayer) {return !isDead() && allowInteraction(aPlayer) && aPlayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64D;}
	@Override public void openInventory () {/**/}
	@Override public void closeInventory() {/**/}
	@Override public int getInventoryStackLimit() {return 64;}
	@Override public void markDirty() {super.markDirty(); updateInventory();}
	@Override public ItemStack decrStackSize(int aSlot, int aDecrement) {updateInventory(); if (mInventory[aSlot] == null) return NI; if (mInventory[aSlot].stackSize <= aDecrement) {ItemStack tStack = ST.copy(mInventory[aSlot]); if (allowZeroStacks(aSlot)) mInventory[aSlot].stackSize = 0; else mInventory[aSlot] = NI; return tStack;} ItemStack rStack = mInventory[aSlot].splitStack(aDecrement); if (mInventory[aSlot].stackSize <= 0 && !allowZeroStacks(aSlot)) mInventory[aSlot] = NI; return rStack;}
	@Override public ItemStack getStackInSlotOnClosing(int aSlot) {ItemStack rStack = mInventory[aSlot]; mInventory[aSlot] = null; return rStack;}
	@Override public ItemStack getStackInSlot(int aSlot) {return mInventory[aSlot];}
	@Override public String getInventoryName() {String rName = getCustomName(); if (UT.Code.stringValid(rName)) return rName; MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry(getMultiTileEntityRegistryID()); return tRegistry==null?getClass().getName():tRegistry.getLocal(getMultiTileEntityID());}
	@Override public int getSizeInventory() {return mInventory==null?0:mInventory.length;}
	@Override public void setInventorySlotContents(int aSlot, ItemStack aStack) {updateInventory(); mInventory[aSlot] = OM.get(aStack);}
	@Override public boolean hasCustomInventoryName() {return getCustomName() != null;}
	@Override public boolean isItemValidForSlot(int aSlot, ItemStack aStack) {return T;}
	public boolean allowZeroStacks(int aSlot) {return F;}
	public ItemStack[] getInventory() {return mInventory;}
	public void setInventory(ItemStack[] aInventory) {mInventory = aInventory;}
	public void removeAllDroppableNullStacks() {for (int i = 0; i < mInventory.length; i++) if (canDrop(i) && mInventory[i] != null && mInventory[i].stackSize <= 0) mInventory[i] = NI;}
	
	public abstract boolean canDrop(int aSlot);
	public boolean breakDrop(int aSlot) {return T;}
	public boolean canSave  (int aSlot) {return T;}
	/** Returns a Stack to be put into that Slot in case of a Mod being uninstalled causing a Loading Error for the original ItemStack. For example Shelves just replace the missing Item with a normal Book instead. */
	public ItemStack getDefaultStack(int aSlot) {return null;}
	
	// These Functions are intentionally duplicates of the Functions above.
	@Override public int getSizeInventoryGUI() {return mInventory==null?0:mInventory.length;}
	@Override public ItemStack getStackInSlotGUI(int aSlot) {return mInventory[aSlot];}
	@Override public ItemStack decrStackSizeGUI(int aSlot, int aDecrement) {updateInventory(); if (mInventory[aSlot] == null) return NI; if (mInventory[aSlot].stackSize <= aDecrement) {ItemStack tStack = ST.copy(mInventory[aSlot]); if (allowZeroStacks(aSlot)) mInventory[aSlot].stackSize = 0; else mInventory[aSlot] = NI; return tStack;} ItemStack rStack = mInventory[aSlot].splitStack(aDecrement); if (mInventory[aSlot].stackSize <= 0 && !allowZeroStacks(aSlot)) mInventory[aSlot] = NI; return rStack;}
	@Override public ItemStack getStackInSlotOnClosingGUI(int aSlot) {ItemStack rStack = mInventory[aSlot]; mInventory[aSlot] = null; return rStack;}
	@Override public void setInventorySlotContentsGUI(int aSlot, ItemStack aStack) {updateInventory(); mInventory[aSlot] = OM.get(aStack);}
	@Override public String getInventoryNameGUI() {String rName = getCustomName(); if (UT.Code.stringValid(rName)) return rName; MultiTileEntityRegistry tRegistry = MultiTileEntityRegistry.getRegistry(getMultiTileEntityRegistryID()); return tRegistry==null?getClass().getName():tRegistry.getLocal(getMultiTileEntityID());}
	@Override public boolean hasCustomInventoryNameGUI() {return getCustomName() != null;}
	@Override public int getInventoryStackLimitGUI(int aSlot) {return getInventoryStackLimit();}
	@Override public void markDirtyGUI() {markDirty();}
	@Override public boolean isUseableByPlayerGUI(EntityPlayer aPlayer) {return !isDead() && allowInteraction(aPlayer) && aPlayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64D;}
	@Override public void openInventoryGUI() {openInventory();}
	@Override public void closeInventoryGUI() {closeInventory();}
	@Override public boolean isItemValidForSlotGUI(int aSlot, ItemStack aStack) {return isItemValidForSlot(aSlot, aStack);}
	@Override public boolean canTakeOutOfSlotGUI(int aSlot) {return T;}
	
	@Override
	public void onExploded(Explosion aExplosion) {
		for (int i = 0; i < mInventory.length; i++) if (RNGSUS.nextInt(3) != 0) GarbageGT.trash(mInventory, i);
		setToAir();
	}
	
	@Override
	public boolean breakBlock() {
		if (isServerSide()) for (short i = 0; i < mInventory.length; i++) if (mInventory[i] != null && canDrop(i) && !ST.debug(mInventory[i]) && breakDrop(i)) {
			ItemStack tDumpedStack = ST.amount(UT.Code.bind_(0, 512 * Math.max(1, mInventory[i].getMaxStackSize()), mInventory[i].stackSize), mInventory[i]);
			int tMaxSize = Math.max(1, mInventory[i].getMaxStackSize());
			
			while (tDumpedStack.stackSize > tMaxSize) {
				ST.drop(worldObj, getCoords(), ST.amount(tMaxSize, tDumpedStack));
				tDumpedStack.stackSize -= tMaxSize;
				mInventory[i].stackSize -= tMaxSize;
			}
			if (tDumpedStack.stackSize > 0) {
				mInventory[i].stackSize -= tDumpedStack.stackSize;
				ST.drop(worldObj, getCoords(), ST.copy(tDumpedStack));
			}
			
			GarbageGT.trash(mInventory, i);
		}
		return F;
	}
	
	public boolean addStackToSlot(int aIndex, ItemStack aStack) {
		if (ST.invalid(aStack)) return T;
		if (aIndex < 0 || aIndex >= getSizeInventory()) return F;
		ItemStack tStack = getStackInSlot(aIndex);
		if (ST.invalid(tStack)) {
			setInventorySlotContents(aIndex, aStack);
			return T;
		}
		aStack = OM.get_(aStack);
		if (ST.equal(tStack, aStack) && tStack.stackSize + aStack.stackSize <= Math.min(Math.max(1, tStack.getMaxStackSize()), getInventoryStackLimit())) {
			tStack.stackSize+=aStack.stackSize;
			updateInventory();
			return T;
		}
		return F;
	}
}
