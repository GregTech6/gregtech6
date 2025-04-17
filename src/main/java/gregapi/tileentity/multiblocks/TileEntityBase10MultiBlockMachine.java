/**
 * Copyright (c) 2025 GregTech-6 Team
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

package gregapi.tileentity.multiblocks;

import gregapi.data.LH;
import gregapi.data.TD;
import gregapi.tileentity.delegate.DelegatorTileEntity;
import gregapi.tileentity.machines.MultiTileEntityBasicMachine;
import gregapi.util.UT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

import java.util.List;

import static gregapi.data.CS.*;

/**
 * @author Gregorius Techneticies
 * 
 * Some Defaults for MultiBlock Machines.
 */
public abstract class TileEntityBase10MultiBlockMachine extends MultiTileEntityBasicMachine implements IMultiBlockFluidHandler, IMultiBlockInventory, IMultiBlockEnergy {
	public boolean mStructureChanged = F, mStructureOkay = F;
	
	@Override
	public void readFromNBT2(NBTTagCompound aNBT) {
		super.readFromNBT2(aNBT);
		if (aNBT.hasKey(NBT_STATE+".str")) mStructureOkay = aNBT.getBoolean(NBT_STATE+".str");
	}
	
	@Override
	public void writeToNBT2(NBTTagCompound aNBT) {
		super.writeToNBT2(aNBT);
		UT.NBT.setBoolean(aNBT, NBT_STATE+".str", mStructureOkay);
	}
	
	@Override
	public long onToolClickMultiBlock(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ, ChunkCoordinates aFrom) {
		if (aTool.equals(TOOL_builderwand)) {
			if (isClientSide()) return 0;
			checkStructure2(aFrom, aPlayer, aPlayerInventory);
			checkStructure(T);
			return 10;
		}
		return onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public long onToolClick2(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, byte aSide, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_builderwand)) {
			if (isClientSide()) return 0;
			checkStructure2(getCoords(), aPlayer, aPlayerInventory);
			checkStructure(T);
			return 10;
		}
		return super.onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public void onMagnifyingGlass(List<String> aChatReturn) {
		super.onMagnifyingGlass(aChatReturn);
		if (checkStructure(F)) {
			onMagnifyingGlass2(aChatReturn);
		} else {
			if (checkStructure(T)) {
				aChatReturn.add("Structure did form just now!");
			} else {
				aChatReturn.add("Structure did not form!");
			}
		}
	}
	
	public void onMagnifyingGlass2(List<String> aChatReturn) {
		aChatReturn.add("Structure is formed already!");
	}
	
	@Override
	public boolean onTickCheck(long aTimer) {
		if (refreshStructureOnActiveStateChange() && (mActive != oActive || mRunning != oRunning)) checkStructure(T);
		return super.onTickCheck(aTimer);
	}
	
	@Override
	public boolean checkStructure(boolean aForceReset) {
		if (isClientSide()) return mStructureOkay;
		if ((mStructureChanged || aForceReset) && mStructureOkay != checkStructure2(null, null, null)) {
			mStructureOkay = !mStructureOkay;
			updateClientData();
		}
		mStructureChanged = F;
		return mStructureOkay;
	}
	
	@Override
	public void addToolTipsSided(List<String> aList, ItemStack aStack, boolean aF3_H) {
		if (mEnergyTypeAccepted != TD.Energy.TU) LH.addEnergyToolTips(this, aList, mEnergyTypeAccepted, null, null, null);
	}
	
	@Override public void onFacingChange(byte aPreviousFacing) {onStructureChange();}
	@Override public final byte getDirectionData() {return (byte)((mFacing & 7) | (mStructureOkay ? 8 : 0));}
	@Override public final void setDirectionData(byte aData) {mFacing = (byte)(aData & 7); mStructureOkay = ((aData & 8) != 0);}
	
	@Override public void updateAdjacentToggleableEnergySources() {/**/}
	
	@Override public boolean doDefaultStructuralChecks() {return T;}
	
	@Override public void onStructureChange() {mStructureChanged = T;}
	
	/** New Version of the MultiBlock Structure Check, which can't be made abstract for backwards compat reasons. */
	public boolean checkStructure2(ChunkCoordinates aCoordinates, Entity aPlayer, IInventory aInventory) {return checkStructure2();}
	/** Previous Version of the MultiBlock Structure Check without Builder Wand Support. Overriding this formerly abstract function will still work for regular checks but is not recommended. */
	@Deprecated public boolean checkStructure2() {return T;}
	
	public boolean refreshStructureOnActiveStateChange() {return F;}
	
	@Override public abstract DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide);
	@Override public abstract DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide);
	@Override public abstract DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide);
	@Override public abstract DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput);
	@Override public abstract String getTileEntityName();
	
	@Override protected IFluidTank getFluidTankFillable     (MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToFill) {return getFluidTankFillable2(aSide, aFluidToFill);}
	@Override protected IFluidTank getFluidTankDrainable    (MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToDrain) {return getFluidTankDrainable2(aSide, aFluidToDrain);}
	@Override protected IFluidTank[] getFluidTanks          (MultiTileEntityMultiBlockPart aPart, byte aSide) {return getFluidTanks2(aSide);}
	
	@Override public int[] getAccessibleSlotsFromSide       (MultiTileEntityMultiBlockPart aPart, byte aSide) {return getAccessibleSlotsFromSide2(aSide);}
	@Override public boolean canInsertItem                  (MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack, byte aSide) {return canInsertItem2(aSlot, aStack, aSide);}
	@Override public boolean canExtractItem                 (MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack, byte aSide) {return canExtractItem2(aSlot, aStack, aSide);}
	@Override public int getSizeInventory                   (MultiTileEntityMultiBlockPart aPart) {return getSizeInventory();}
	@Override public ItemStack getStackInSlot               (MultiTileEntityMultiBlockPart aPart, int aSlot) {return getStackInSlot(aSlot);}
	@Override public ItemStack decrStackSize                (MultiTileEntityMultiBlockPart aPart, int aSlot, int aDecrement) {return decrStackSize(aSlot, aDecrement);}
	@Override public ItemStack getStackInSlotOnClosing      (MultiTileEntityMultiBlockPart aPart, int aSlot) {return getStackInSlotOnClosing(aSlot);}
	@Override public void setInventorySlotContents          (MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack) {setInventorySlotContents(aSlot, aStack);}
	@Override public String getInventoryName                (MultiTileEntityMultiBlockPart aPart) {return getInventoryName();}
	@Override public boolean hasCustomInventoryName         (MultiTileEntityMultiBlockPart aPart) {return hasCustomInventoryName();}
	@Override public int getInventoryStackLimit             (MultiTileEntityMultiBlockPart aPart) {return getInventoryStackLimit();}
	@Override public void markDirty                         (MultiTileEntityMultiBlockPart aPart) {markDirty();}
	@Override public boolean isUseableByPlayer              (MultiTileEntityMultiBlockPart aPart, EntityPlayer aPlayer) {return isUseableByPlayer(aPlayer);}
	@Override public void openInventory                     (MultiTileEntityMultiBlockPart aPart) {openInventory();}
	@Override public void closeInventory                    (MultiTileEntityMultiBlockPart aPart) {closeInventory();}
	@Override public boolean isItemValidForSlot             (MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack) {return isItemValidForSlot(aSlot, aStack);}
}
