package gregapi.tileentity.multiblocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * @author Gregorius Techneticies
 */
public interface IMultiBlockInventory extends ITileEntityMultiBlockController {
	public int[] getAccessibleSlotsFromSide		(MultiTileEntityMultiBlockPart aPart, byte aSide);
	public boolean canInsertItem				(MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack, byte aSide);
	public boolean canExtractItem				(MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack, byte aSide);
	public int getSizeInventory					(MultiTileEntityMultiBlockPart aPart);
	public ItemStack getStackInSlot				(MultiTileEntityMultiBlockPart aPart, int aSlot);
	public ItemStack decrStackSize				(MultiTileEntityMultiBlockPart aPart, int aSlot, int aDecrement);
	public ItemStack getStackInSlotOnClosing	(MultiTileEntityMultiBlockPart aPart, int aSlot);
	public void setInventorySlotContents		(MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack);
	public String getInventoryName				(MultiTileEntityMultiBlockPart aPart);
	public boolean hasCustomInventoryName		(MultiTileEntityMultiBlockPart aPart);
	public int getInventoryStackLimit			(MultiTileEntityMultiBlockPart aPart);
	public void markDirty						(MultiTileEntityMultiBlockPart aPart);
	public boolean isUseableByPlayer			(MultiTileEntityMultiBlockPart aPart, EntityPlayer aPlayer);
	public void openInventory					(MultiTileEntityMultiBlockPart aPart);
	public void closeInventory					(MultiTileEntityMultiBlockPart aPart);
	public boolean isItemValidForSlot			(MultiTileEntityMultiBlockPart aPart, int aSlot, ItemStack aStack);
}