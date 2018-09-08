package gregapi.tileentity;

import net.minecraft.inventory.IInventory;

/**
 * @author Gregorius Techneticies
 */
public interface ITileEntityAdjacentInventoryUpdatable extends ITileEntityUnloadable {
    /**
     * Gets called by some GT things like Pipes to notify about changes inside the Inventory of the caller.
     * 
     * This is only for important Inventory Updates, like when a Pipe has more free space than before.
     */
    public void adjacentInventoryUpdated(byte aSide, IInventory aTileEntity);
}