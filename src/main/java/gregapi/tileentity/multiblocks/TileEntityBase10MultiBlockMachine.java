package gregapi.tileentity.multiblocks;

import static gregapi.data.CS.*;

import java.util.List;

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
		return onToolClick2(aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aSide, aHitX, aHitY, aHitZ);
	}
	
	@Override
	public boolean checkStructure(boolean aForceReset) {
		if (isClientSide()) return mStructureOkay;
		if (mStructureChanged || aForceReset) mStructureOkay = checkStructure2();
		mStructureChanged = F;
		return mStructureOkay;
	}
	
	@Override public int getDefaultTankCapacity() {return UT.Code.bindInt(Math.max(16000, mRecipes.mMaxFluidInputSize * 2L * mParallel));}
	@Override public void updateAdjacentToggleableEnergySources() {/**/}
	@Override public void onFacingChange(byte aPreviousFacing) {onStructureChange();}
	
	@Override public boolean doDefaultStructuralChecks() {return T;}
	
	@Override public void onStructureChange() {mStructureChanged = T;}
	
	public abstract boolean checkStructure2();
	
	@Override public abstract DelegatorTileEntity<IInventory> getItemInputTarget(byte aSide);
	@Override public abstract DelegatorTileEntity<TileEntity> getItemOutputTarget(byte aSide);
	@Override public abstract DelegatorTileEntity<IFluidHandler> getFluidInputTarget(byte aSide);
	@Override public abstract DelegatorTileEntity<IFluidHandler> getFluidOutputTarget(byte aSide, Fluid aOutput);
	@Override public abstract String getTileEntityName();
	
	@Override protected IFluidTank getFluidTankFillable		(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToFill) {return getFluidTankFillable2(aSide, aFluidToFill);}
	@Override protected IFluidTank getFluidTankDrainable	(MultiTileEntityMultiBlockPart aPart, byte aSide, FluidStack aFluidToDrain) {return getFluidTankDrainable2(aSide, aFluidToDrain);}
	@Override protected IFluidTank[] getFluidTanks			(MultiTileEntityMultiBlockPart aPart, byte aSide) {return getFluidTanks2(aSide);}
	
	@Override public int[] getAccessibleSlotsFromSide		(MultiTileEntityMultiBlockPart aPart, byte aSide) {return getAccessibleSlotsFromSide2(aSide);}
	@Override public boolean canInsertItem					(MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack, byte aSide) {return canInsertItem2(aSlot, aStack, aSide);}
	@Override public boolean canExtractItem					(MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack, byte aSide) {return canExtractItem2(aSlot, aStack, aSide);}
	@Override public int getSizeInventory					(MultiTileEntityMultiBlockPart aPart) {return getSizeInventory();}
	@Override public ItemStack getStackInSlot				(MultiTileEntityMultiBlockPart aPart, int aSlot) {return getStackInSlot(aSlot);}
	@Override public ItemStack decrStackSize				(MultiTileEntityMultiBlockPart aPart, int aSlot, int aDecrement) {return decrStackSize(aSlot, aDecrement);}
	@Override public ItemStack getStackInSlotOnClosing		(MultiTileEntityMultiBlockPart aPart, int aSlot) {return getStackInSlotOnClosing(aSlot);}
	@Override public void setInventorySlotContents			(MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack) {setInventorySlotContents(aSlot, aStack);}
	@Override public String getInventoryName				(MultiTileEntityMultiBlockPart aPart) {return getInventoryName();}
	@Override public boolean hasCustomInventoryName			(MultiTileEntityMultiBlockPart aPart) {return hasCustomInventoryName();}
	@Override public int getInventoryStackLimit				(MultiTileEntityMultiBlockPart aPart) {return getInventoryStackLimit();}
	@Override public void markDirty							(MultiTileEntityMultiBlockPart aPart) {markDirty();}
	@Override public boolean isUseableByPlayer				(MultiTileEntityMultiBlockPart aPart, EntityPlayer aPlayer) {return isUseableByPlayer(aPlayer);}
	@Override public void openInventory						(MultiTileEntityMultiBlockPart aPart) {openInventory();}
	@Override public void closeInventory					(MultiTileEntityMultiBlockPart aPart) {closeInventory();}
	@Override public boolean isItemValidForSlot				(MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack) {return isItemValidForSlot(aSlot, aStack);}
}